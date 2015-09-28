package br.ufc.arida.bcl.rp20152.aplicacao;

import br.ufc.arida.bcl.rp20152.arquivos.FileHandler;
import br.ufc.arida.bcl.rp20152.entidades.Matriz;
import br.ufc.arida.bcl.rp20152.entidades.Vetor;

public class Teste {

	public static void main(String[] args) {
		
		FileHandler fileHandler = new FileHandler("dados.csv", ";");
		Matriz matriz = new Matriz(fileHandler.getMatrizDeEntrada().getMatriz(),
									fileHandler.getNumeroDeLinhas(), fileHandler.getNumeroDeColunas());
		
		Vetor x1 = new Vetor(matriz.getColuna(0).getVetor());
		System.out.println(x1);
		
		Vetor x2 = new Vetor(matriz.getColuna(1).getVetor());
		System.out.println(x2);
		
		Vetor x3 = new Vetor(matriz.getColuna(2).getVetor());
		System.out.println(x3);		
	}

}
