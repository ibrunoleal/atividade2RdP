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
	
	public String toString() {
		String resultado = "[";
		for (int i = 0; i < (vetor.length - 1); i++) {
			resultado += vetor[i] + " , ";
		}
		resultado += vetor[vetor.length -1] + " ]";
		return resultado;
	}

}
