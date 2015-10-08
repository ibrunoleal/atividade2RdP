package br.ufc.arida.bcl.rp20152.knn;

import java.util.ArrayList;
import java.util.List;

import br.ufc.arida.bcl.rp20152.entidades.Vetor;

public class KNN {
	
	private Vetor vetor;
	
	private int K;
	
	/**
	 * Numero de elementos do vetor.
	 */
	private int N;
	
	/**
	 * 
	 * @param vetor vetor de valores.
	 * @param K numero de vizinhos mais proximos para o algoritmo KNN.
	 */
	public KNN(Vetor vetor, int K) {
		this.vetor = vetor;
		this.K = K;
		this.N = vetor.getSize();
	}
	
	/**
	 * Calcula P(x) atraves da densidade no ponto x por meio do KNN.
	 * Utiliza a formula p(x) = K / (V*N).
	 * @param x o ponto de referencia para o qual será calculado a probabilidade(densidade).
	 * @return o valor de P(x).
	 */
	public double getDensidade(double x)  {
		double px = K / (getV(x) * N);
		return px;
	}
	
	/**
	 * Calcula o valor de V a ser utilizado no calculo da densidade.
	 * @param referencePoint ponto de referencia para pegar os K vizinhos mais
	 * proximos e calcular V.
	 * @return o valor de V em relacao ao ponto de referencia dado.
	 */
	public double getV(double referencePoint) {
		List<Double> listaDeVizinhosAEsquerda = new ArrayList<>();
		List<Double> listaDeVizinhosADireita = new ArrayList<>();
		
		/*
		 * Recupera os valores dos K vizinhos mais próximos e dividem esses valores
		 * em listas dos que estao a esquerda do ponto de referencia (menores ou iguais) e dos que estao
		 * a direita (maiores)
		 */
		List<Integer> listaDeIndices = getTheIndexOfTheKNearestNeighbour(referencePoint);
		for (Integer indice : listaDeIndices) {
			double ponto = vetor.getVetor()[indice];
			if (ponto <= referencePoint) {
				listaDeVizinhosAEsquerda.add(ponto);
			} else {
				listaDeVizinhosADireita.add(ponto);
			}
		}
		
		/*
		 * verifica o ponto mais a esquerda e o mais a direita para calcular V
		 */
		double pontoEsquerda;
		double pontoDireita;
		double v = 0;
		if (listaDeVizinhosAEsquerda.size() > 0 && listaDeVizinhosADireita.size() > 0) {
			pontoEsquerda = getSmallerFromList(listaDeVizinhosAEsquerda);
			pontoDireita = getBiggerFromList(listaDeVizinhosADireita);
			v = calcularDistancia(pontoEsquerda, pontoDireita);
		} else {
			if (listaDeVizinhosAEsquerda.size() > 0 && listaDeVizinhosADireita.size() == 0) {
				pontoEsquerda = getSmallerFromList(listaDeVizinhosAEsquerda);
				v = calcularDistancia(referencePoint, pontoEsquerda);
			} else {
				if (listaDeVizinhosAEsquerda.size() == 0 && listaDeVizinhosADireita.size() > 0) {
					pontoDireita = getBiggerFromList(listaDeVizinhosADireita);
					v = calcularDistancia(referencePoint, pontoDireita);
				}
			}
		}
		return v;
	}
	
	/**
	 * Dado um ponto qualquer no espaço do vetor, computa os K vizinhos mais proximos deste ponto.
	 * @param localPoint o ponto de referencia para pegar os K vizinhos mais proximos.
	 * @return uma lista com os K indices do vetor dos pontos mais proximos
	 * do dado referencial em ordem crescente de distancia.
	 */
	public List<Integer> getTheIndexOfTheKNearestNeighbour(double localPoint) {
		List<Double> listaDeDistancias = new ArrayList<>();
		for (int i = 0; i < vetor.getSize(); i++) {
			double distancia = calcularDistancia(localPoint, vetor.getVetor()[i]);
			listaDeDistancias.add(distancia);
		}
		
		List<Integer> indicesSelecionados = new ArrayList<>();
		int cont = 0;
		while(cont < K) {
			int indice = getIndexOfTheSmallerFromList(listaDeDistancias, indicesSelecionados);
			indicesSelecionados.add(indice);
			cont++;
		}
		return indicesSelecionados;
	}
	
	/**
	 * Dado uma lista recupera o indice do elemento com menor valor.
	 * @param lista lista na qual será pesquisada o menor valor.
	 * @param impedidos lista de indices que devem ser excluídos da pesquisa.
	 * @return o índice do elemento da lista com menor valor, excluido-se 
	 * os elementos da lista de impedidos.
	 */
	private int getIndexOfTheSmallerFromList(List<Double> lista, List<Integer> impedidos) {
		double menorNumero = lista.get(0);
		int menorIndice = 0;
		for (int i = 0; i < lista.size(); i++) {
			double num = lista.get(i);
			if (num < menorNumero && !listaContemNumero(impedidos, i)) {
				menorNumero = num;
				menorIndice = i;
			}
		}
		return menorIndice;
	}
	
	private double getSmallerFromList(List<Double> lista) {
		double menorNumero = lista.get(0);
		for (Double num : lista) {
			if (num < menorNumero){
				menorNumero = num;
			}
		}
		return menorNumero;
	}
	
	private double getBiggerFromList(List<Double> lista) {
		double maiorNumero = lista.get(0);
		for (Double num : lista) {
			if (num > maiorNumero){
				maiorNumero = num;
			}
		}
		return maiorNumero;
	}
	
	/**
	 * Metodo auxiliar. Verifica se uma dada lista contem um valor.
	 * @param lista de valores.
	 * @param numero valor a ser verficado se existe na lista.
	 * @return true se o valor se encontra na lista, false c.c.
	 */
	private boolean listaContemNumero(List<Integer> lista, int numero) {
		for (Integer integer : lista) {
			if (integer == numero) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Dados dois pontos do vetor, calcula a distancia entre eles.
	 * @param pontoA ponto A
	 * @param pontoB ponto B
	 * @return o valor da distancia entre os pontoA e o pontoB
	 */
	private double calcularDistancia(double pontoA, double pontoB) {
		if (pontoA < 0 && pontoB < 0) {
			return Math.abs(pontoA - pontoB);
		} else {
			if (pontoA > 0 && pontoB > 0) {
				return Math.abs(pontoA - pontoB);
			} else {
				return Math.abs(pontoA) + Math.abs(pontoB);
			}
		}
	}

}
