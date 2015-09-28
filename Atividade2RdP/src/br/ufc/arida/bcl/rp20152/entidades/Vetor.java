package br.ufc.arida.bcl.rp20152.entidades;

public class Vetor {
	
	private Elemento[] vetor;

	public Vetor(Elemento[] vetor) {
		this.vetor = vetor;
	}
	
	public Vetor(int elementos) {
		this.vetor = new Elemento[elementos];
	}

	public Elemento[] getVetor() {
		return vetor;
	}

	public void setVetor(Elemento[] vetor) {
		this.vetor = vetor;
	}
	
	

}
