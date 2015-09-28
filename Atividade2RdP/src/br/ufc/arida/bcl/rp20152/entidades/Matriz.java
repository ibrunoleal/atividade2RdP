package br.ufc.arida.bcl.rp20152.entidades;

public class Matriz {
	
	private int numeroDeLinhas;
	
	private int numeroDeColunas;
	
	private Elemento[][] matriz;

	public Matriz(int numeroDeLinhas, int numeroDeColunas) {
		this.numeroDeLinhas = numeroDeLinhas;
		this.numeroDeColunas = numeroDeColunas;
		this.matriz = new Elemento[this.numeroDeLinhas][this.numeroDeColunas];
	}
	
	public Vetor getLinha(int linha) {
		Vetor vetorResultado = new Vetor(numeroDeColunas);
		for (int j = 0; j < numeroDeColunas; j++) {
			vetorResultado.getVetor()[j] = matriz[linha][j];
		}
		return vetorResultado;
	}
	
	public Vetor getColuna(int coluna) {
		Vetor vetorResultado = new Vetor(numeroDeLinhas);
		for (int i = 0; i < numeroDeLinhas; i++) {
			vetorResultado.getVetor()[i] = matriz[i][coluna];
		}
		return vetorResultado;
	}
	
	public void setColuna(int j, Vetor vetorColuna) {
		if (vetorColuna.getVetor().length == this.numeroDeLinhas) {
			for (int i = 0; i < vetorColuna.getVetor().length; i++) {
				matriz[i][j] = vetorColuna.getVetor()[i];
			}
		} else {
			System.out.println("Erro ao definir coluna: numero de elementos invalidos.");
		}
		
	}
	
	public void setLinha(int i, Vetor vetorLinha) {
		if (vetorLinha.getVetor().length == this.numeroDeColunas) {
			 for (int j = 0; j < vetorLinha.getVetor().length; j++) {
				matriz[i][j] = vetorLinha.getVetor()[j];
			}
		} else {
			System.out.println("Erro ao definir linha: numero de elementos invalidos.");
		}
	}

}