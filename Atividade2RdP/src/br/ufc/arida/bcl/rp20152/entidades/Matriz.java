package br.ufc.arida.bcl.rp20152.entidades;

public class Matriz {
	
	private int numeroDeLinhas;
	
	private int numeroDeColunas;
	
	private double[][] matriz;

	public Matriz(int numeroDeLinhas, int numeroDeColunas) {
		this.numeroDeLinhas = numeroDeLinhas;
		this.numeroDeColunas = numeroDeColunas;
		this.matriz = new double[this.numeroDeLinhas][this.numeroDeColunas];
	}
	

	public Matriz(double[][] matriz, int numeroDeLinhas, int numeroDeColunas) {
		this.matriz = matriz;
		this.numeroDeLinhas = numeroDeLinhas;
		this.numeroDeColunas = numeroDeColunas;
	}


	/**
	 * Recupera a requerida linha da matriz na forma de um Vetor.
	 * @param linha numero da linha a ser recuperada.\nObs.: a primeira linha é 0.
	 * @return a linha requerida na forma de Vetor.
	 */
	public Vetor getLinha(int linha) {
		Vetor vetorResultado = new Vetor(numeroDeColunas);
		for (int j = 0; j < numeroDeColunas; j++) {
			vetorResultado.getVetor()[j] = matriz[linha][j];
		}
		return vetorResultado;
	}
	
	/**
	 * Recupera a requerida coluna da matriz na forma de um Vetor.
	 * @param coluna numero da coluna a ser recuperada.\nObs.: a primeira coluna é 0.
	 * @return a coluna requerida na forma de Vetor.
	 */
	public Vetor getColuna(int coluna) {
		Vetor vetorResultado = new Vetor(numeroDeLinhas);
		for (int i = 0; i < numeroDeLinhas; i++) {
			vetorResultado.getVetor()[i] = matriz[i][coluna];
		}
		return vetorResultado;
	}
	
	/**
	 * Insere o vetor coluna na matriz na posicao especificada.
	 * @param j posicao especificada na qual a coluna sera inserida.\nObs.: a primeira coluna e 0.
	 * @param vetorColuna o vetor coluna a ser inserido na matriz.
	 */
	public void setColuna(int j, Vetor vetorColuna) {
		if (vetorColuna.getVetor().length == this.numeroDeLinhas) {
			for (int i = 0; i < vetorColuna.getVetor().length; i++) {
				matriz[i][j] = vetorColuna.getVetor()[i];
			}
		} else {
			System.out.println("Erro ao definir coluna: numero de elementos invalidos.");
		}
	}
	
	/**
	 * Insere o vetor linha na matriz na posicao especificada.
	 * @param i posicao especificada na qual a linha sera inserida.\nObs.: a primeira linha e 0;
	 * @param vetorLinha
	 */
	public void setLinha(int i, Vetor vetorLinha) {
		if (vetorLinha.getVetor().length == this.numeroDeColunas) {
			 for (int j = 0; j < vetorLinha.getVetor().length; j++) {
				matriz[i][j] = vetorLinha.getVetor()[j];
			}
		} else {
			System.out.println("Erro ao definir linha: numero de elementos invalidos.");
		}
	}


	public int getNumeroDeLinhas() {
		return numeroDeLinhas;
	}


	public void setNumeroDeLinhas(int numeroDeLinhas) {
		this.numeroDeLinhas = numeroDeLinhas;
	}


	public int getNumeroDeColunas() {
		return numeroDeColunas;
	}


	public void setNumeroDeColunas(int numeroDeColunas) {
		this.numeroDeColunas = numeroDeColunas;
	}


	public double[][] getMatriz() {
		return matriz;
	}


	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}
	
	
	public Matriz getMatrizDeCovariancia() {
		Matriz matrizResultado = new Matriz(numeroDeColunas, numeroDeColunas);
		
		/*
		 * Cada elemento da matriz resultante é dado pela covariancia entre
		 * os vetores Xi e Xj.
		 */
		for (int i = 0; i < numeroDeColunas; i++) {
			for (int j = 0; j < numeroDeColunas; j++) {
				Vetor vetorAuxI = getColuna(i);
				
				/*
				 * Na diagonal a covariancia entre Xi e Xi eh dado pela propria
				 * variancia de Xi
				 */
				if (i == j) {
					double variancia = vetorAuxI.getVariance();
					matrizResultado.getMatriz()[i][j] = variancia;
				} else {
					Vetor vetorAuxJ = getColuna(j);
					double covariancia = vetorAuxI.getCovariance(vetorAuxJ);
					matrizResultado.getMatriz()[i][j] = covariancia;
				}
			}
		}
		
		return matrizResultado;
	}
	
	public String toString() {
		String texto = "";
		for (int i = 0; i < numeroDeLinhas; i++) {
			texto += getLinha(i) + "\n";
		}
		return texto;
	}
}
