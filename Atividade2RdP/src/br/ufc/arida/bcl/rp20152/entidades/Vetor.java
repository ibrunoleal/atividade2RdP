package br.ufc.arida.bcl.rp20152.entidades;

public class Vetor {
	
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
	
	public String toString() {
		String resultado = "[";
		for (int i = 0; i < (vetor.length - 1); i++) {
			resultado += vetor[i] + " , ";
		}
		resultado += vetor[vetor.length -1] + " ]";
		return resultado;
	}

}
