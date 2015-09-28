package br.ufc.arida.bcl.rp20152.entidades;

public class Elemento {
	
	private double valor;

	public Elemento(double valor) {
		super();
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "" + valor;
	}
	
}
