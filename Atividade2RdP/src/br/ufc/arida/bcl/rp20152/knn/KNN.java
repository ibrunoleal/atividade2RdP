package br.ufc.arida.bcl.rp20152.knn;

import java.util.ArrayList;
import java.util.List;

import br.ufc.arida.bcl.rp20152.entidades.Vetor;

public class KNN {
	
	private Vetor vetor;
	
	private int K;
	
	private int N;
	
	public KNN(Vetor vetor, int K) {
		this.vetor = vetor;
		this.K = K;
		this.N = vetor.getSize();
	}
	
	public double getValueV(double point) {
		
		return 0;
	}
	
	/**
	 * Dado um ponto qualquer no espaço do vetor, computa os K vizinhos mais proximos deste ponto.
	 * @param localPoint o ponto de referencia para pegar os K vizinhos mais proximos.
	 * @return uma lista com os K indices do vetor dos pontos mais proximos do dado referencial.
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
	 * @return o valor da distancia absoluta entre os pontoA e o pontoB
	 */
	private double calcularDistancia(double pontoA, double pontoB) {
		return Math.abs(pontoA - pontoB);
	}


}
