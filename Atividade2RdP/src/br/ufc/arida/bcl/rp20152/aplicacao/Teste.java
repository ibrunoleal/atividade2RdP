package br.ufc.arida.bcl.rp20152.aplicacao;

import br.ufc.arida.bcl.rp20152.arquivos.FileHandler;
import br.ufc.arida.bcl.rp20152.entidades.Matriz;
import br.ufc.arida.bcl.rp20152.entidades.Vetor;


public class Teste {

	public static void main(String[] args) {
		
		FileHandler fileHandler = new FileHandler("dados.csv", ";");
		Matriz matriz = new Matriz(fileHandler.getMatrizDeEntrada().getMatriz(),
									fileHandler.getNumeroDeLinhas(), fileHandler.getNumeroDeColunas());
		
//		System.out.println("Matriz do dataset");
//		for (int i = 0; i < matriz.getNumeroDeLinhas(); i++) {
//			System.out.println(matriz.getLinha(i));
//		}
		
		System.out.println("------------------------");
		
		System.out.println("Matriz de covariancia do dataset");
		Matriz matrizDeCovariancia = new Matriz(matriz.getMatrizDeCovariancia().getMatriz(), matriz.getNumeroDeColunas(), matriz.getNumeroDeColunas());
		//imprimir matriz de covariancia
		for (int i = 0; i < matrizDeCovariancia.getNumeroDeLinhas(); i++) {
			System.out.println(matrizDeCovariancia.getLinha(i));
		}
		
		System.out.println("-----------------------");
		
		System.out.println("Matriz teste");
		double[] x1 = {5.0,6.0,2.0,4.0,8.0};
		Vetor vetorX1 = new Vetor(x1);
		double[] x2 = {5.0,4.0,2.0,1.0,8.0};
		Vetor vetorX2 = new Vetor(x2);
		double[] x3 = {0.0,1.0,0.0,2.0,2.0};
		Vetor vetorX3 = new Vetor(x3);
		Matriz matrizTeste = new Matriz(5, 3);
		matrizTeste.setColuna(0, vetorX1);
		matrizTeste.setColuna(1, vetorX2);
		matrizTeste.setColuna(2, vetorX3);
		
		for (int i = 0; i < matrizTeste.getNumeroDeLinhas(); i++) {
			System.out.println(matrizTeste.getLinha(i));
		}
		
		System.out.println("x1 variance = " + vetorX1.getVariance());
		System.out.println("x2 variance = " + vetorX2.getVariance());
		System.out.println("x3 variance = " + vetorX3.getVariance());
		
		System.out.println("covariancia x1 e x2 = " + vetorX1.getCovariance(vetorX2));
		System.out.println("covariancia x1 e x3 = " + vetorX1.getCovariance(vetorX3));
		System.out.println("covariancia x2 e x3 = " + vetorX2.getCovariance(vetorX3));
		
		System.out.println("Matriz de covariancia da matriz teste");
		Matriz matrizTesteCovariancia = new Matriz(matrizTeste.getMatrizDeCovariancia().getMatriz(), matrizTeste.getNumeroDeColunas(), matrizTeste.getNumeroDeColunas());
		for (int i = 0; i < matrizTesteCovariancia.getNumeroDeLinhas(); i++) {
			System.out.println(matrizTesteCovariancia.getLinha(i));
		}
	}

}
