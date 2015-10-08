package br.ufc.arida.bcl.rp20152.entidades;

import java.util.ArrayList;
import java.util.List;

public class Vetor {
	
	private String nome;
	
	private double[] vetor;

	public Vetor(int size) {
		this.vetor = new double[size];
	}

	public Vetor(double[] vetor) {
		this.vetor = vetor;
	}

	public double[] getVetor() {
		return vetor;
	}

	public void setVetor(double[] vetor) {
		this.vetor = vetor;
	}
	
	/**
	 * Calcula o valor médio do vetor(mean ou u(grego)).
	 * @return valor mean do vetor.
	 */
	public double getMean() {
		double soma = 0;
		for (int i = 0; i < vetor.length; i++) {
			soma += vetor[i];
		}
		double mean = soma/vetor.length;
		return mean;
	}
	
	/**
	 * Calcula a variancia do vetor.
	 * @return o valor da variancia.
	 */
	public double getVariance() {
		double mean = getMean();
		int n = vetor.length;
		double somatorio = 0;
		
		for (int i = 0; i < n; i++) {
			somatorio += Math.pow((vetor[i] - mean),2);
		}
		
		double variance = somatorio / n;
		return variance;
	}
	
	/**
	 * Calcula a covariancia entre o vetor e outro vetor dado.
	 * @param y o outro vetor dado.
	 * @return o valor da covariancia dos dois vetores.
	 */
	public double getCovariance(Vetor y) {
		double eX = this.getMean();
		double eY = y.getMean();
		int n = vetor.length;
		
		double somaAux = 0;
		for (int i = 0; i < vetor.length; i++) {
			double sX = vetor[i] - eX;
			double sY = y.getVetor()[i] - eY;
			somaAux += sX * sY;
		}
		
		double covariance = somaAux / (n-1);
		return covariance;
	}
	
	public double getMinValue() {
		double min = vetor[0];
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] < min) {
				min = vetor[i];
			}
		}
		return min;
	}
	
	public double getMaxValue() {
		double max = vetor[0];
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] > max) {
				max = vetor[i];
			}
		}
		return max;
	}
	
	public List<Double> getPontosDeReferencia(int quantidade) {
		ArrayList<Double> pontosDeReferencia = new ArrayList<>();
		
		double pontoA = getMinValue();
		double pontoB = getMaxValue();
		double distancia = calcularDistancia(pontoA, pontoB);
		double espaco = distancia / quantidade;
		
		/*
		 * centralizar o ponto no espaco
		 */
		double ponto = pontoA + (espaco / 2);
		pontosDeReferencia.add(ponto);
		for (int i = 0; i < quantidade; i++) {
			ponto = ponto + espaco;
			pontosDeReferencia.add(ponto);
		}
		
		return pontosDeReferencia;
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
	
	public int getSize() {
		return vetor.length;
	}	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		String resultado = nome + "[";
		for (int i = 0; i < (vetor.length - 1); i++) {
			resultado += vetor[i] + " , ";
		}
		resultado += vetor[vetor.length -1] + " ]";
		return resultado;
	}

}
