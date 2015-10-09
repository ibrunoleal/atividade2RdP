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
		/*
		 * Recupera os valores dos K vizinhos mais próximos e dividem esses valores
		 * em listas dos que estao a esquerda do ponto de referencia (menores ou iguais) e dos que estao
		 * a direita (maiores)
		 */
		List<Integer> listaDeIndices = getTheIndexOfTheKNearestNeighbour(referencePoint);
		
		/*
		 * O V da formula p(x) = K / (N*V)
		 */
		double v = 0;
		
		int indiceDoMaisDistante = listaDeIndices.get((K-1));
		double pontoMaisDistante = getPontoDoVetorPeloIndice(indiceDoMaisDistante);
		
		v = 2 * calcularDistancia(pontoMaisDistante, referencePoint);
		
		/*
		 * verifica o ponto mais a esquerda e o mais a direita para calcular V
		 * descomentar essa parte para considear v como o espaco compreendido entre
		 * os K pontos mais proximos e o ponto de referencia considerando o mais a esquerda
		 * e o mais a direita
		 */
//		List<Double> listaDeVizinhosAEsquerda = new ArrayList<>();
//		List<Double> listaDeVizinhosADireita = new ArrayList<>();
//		for (Integer indice : listaDeIndices) {
//			double ponto = vetor.getVetor()[indice];
//			if (ponto <= referencePoint) {
//				listaDeVizinhosAEsquerda.add(ponto);
//			} else {
//				listaDeVizinhosADireita.add(ponto);
//			}
//		}
//				
//		double pontoEsquerda;
//		double pontoDireita;
//		
//		if (listaDeVizinhosAEsquerda.size() > 0 && listaDeVizinhosADireita.size() > 0) {
//			pontoEsquerda = getSmallerFromList(listaDeVizinhosAEsquerda);
//			pontoDireita = getBiggerFromList(listaDeVizinhosADireita);
//			v = calcularDistancia(pontoEsquerda, pontoDireita);
//		} else {
//			if (listaDeVizinhosAEsquerda.size() > 0 && listaDeVizinhosADireita.size() == 0) {
//				pontoEsquerda = getSmallerFromList(listaDeVizinhosAEsquerda);
//				v = calcularDistancia(referencePoint, pontoEsquerda);
//			} else {
//				if (listaDeVizinhosAEsquerda.size() == 0 && listaDeVizinhosADireita.size() > 0) {
//					pontoDireita = getBiggerFromList(listaDeVizinhosADireita);
//					v = calcularDistancia(referencePoint, pontoDireita);
//				}
//			}
//		}
		
		return v;
	}
	
	/**
	 * Dado um ponto qualquer no espaço do vetor, computa os K vizinhos mais proximos deste ponto.
	 * @param localPoint o ponto de referencia para pegar os K vizinhos mais proximos.
	 * @return uma lista com os K indices do vetor dos pontos mais proximos
	 * do dado referencial em ordem crescente de distancia.
	 */
	public List<Integer> getTheIndexOfTheKNearestNeighbour(double localPoint) {
		
		/*
		 * Forma uma lista com todas as distancias do localPoint em relacao aos pontos do vetor
		 */
		List<Double> listaDeDistancias = new ArrayList<>();
		for (int i = 0; i < vetor.getSize(); i++) {
			double distancia = calcularDistancia(localPoint, vetor.getVetor()[i]);
			listaDeDistancias.add(distancia);
		}
		
		/*
		 * Forma a lista com os indices das K menores distancias em ordem crescente
		 */
		List<Integer> indicesSelecionados = new ArrayList<>();
		List<Double> listaDeDistanciasOrdenadas = getListaOrdenada(listaDeDistancias);
		int cont = 0;
		while(cont < K) {
			double distancia = listaDeDistanciasOrdenadas.get(cont);
			for (int i = 0; i < listaDeDistancias.size(); i++) {
				if (distancia == listaDeDistancias.get(i)) {
					indicesSelecionados.add(i);
					cont++;
					break;
				}
			}
		}
		
		return indicesSelecionados;
	}
	
	private double getPontoDoVetorPeloIndice(int indice) {
		return vetor.getVetor()[indice];
	}
	
	
	/**
	 * Dados dois pontos do vetor, calcula a distancia entre eles.
	 * @param pontoA ponto A
	 * @param pontoB ponto B
	 * @return o valor da distancia entre os pontoA e o pontoB
	 */
	private double calcularDistancia(double pontoA, double pontoB) {
		if (pontoA >= pontoB) {
			return pontoA - pontoB;
		} else {
			return pontoB - pontoA;
		}
	}
	
	public List<Double> getListaOrdenada(List<Double> lista) {	
		int j;
        double key;
        int i;
        List<Double> listaAux = new ArrayList<>(lista);

        for (j = 1; j < listaAux.size(); j++) {
               key = listaAux.get(j);
               for (i = j - 1; (i >= 0) && (listaAux.get(i) > key); i--) {
                      listaAux.set(i + 1, listaAux.get(i));
               }
               listaAux.set(i + 1,key);
        }
        
        return listaAux;
	}

}
