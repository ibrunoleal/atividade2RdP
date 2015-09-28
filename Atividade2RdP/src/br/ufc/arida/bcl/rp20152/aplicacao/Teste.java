package br.ufc.arida.bcl.rp20152.aplicacao;

import br.ufc.arida.bcl.rp20152.arquivos.FileHandler;
import br.ufc.arida.bcl.rp20152.entidades.Matriz;


public class Teste {

	public static void main(String[] args) {
		
		FileHandler fileHandler = new FileHandler("dados.csv", ";");
		Matriz matriz = new Matriz(fileHandler.getMatrizDeEntrada().getMatriz(),
									fileHandler.getNumeroDeLinhas(), fileHandler.getNumeroDeColunas());
		
		//imprimir matriz
		for (int i = 0; i < matriz.getNumeroDeLinhas(); i++) {
			System.out.println(matriz.getLinha(i));
		}
	}

}
