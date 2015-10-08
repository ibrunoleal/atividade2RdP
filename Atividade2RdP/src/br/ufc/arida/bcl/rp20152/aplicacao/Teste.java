package br.ufc.arida.bcl.rp20152.aplicacao;

import java.util.ArrayList;
import java.util.List;

import br.ufc.arida.bcl.rp20152.arquivos.FileHandler;
import br.ufc.arida.bcl.rp20152.entidades.Matriz;
import br.ufc.arida.bcl.rp20152.entidades.Vetor;
import br.ufc.arida.bcl.rp20152.grafico.Grafico;
import br.ufc.arida.bcl.rp20152.grafico.PontoDoGrafico;
import br.ufc.arida.bcl.rp20152.knn.KNN;


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
		
//		System.out.println("Matriz teste");
//		double[] x1 = {5.0,6.0,2.0,4.0,8.0};
//		Vetor vetorV1 = new Vetor(x1);
//		double[] x2 = {5.0,4.0,2.0,1.0,8.0};
//		Vetor vetorV2 = new Vetor(x2);
//		double[] x3 = {0.0,1.0,0.0,2.0,2.0};
//		Vetor vetorV3 = new Vetor(x3);
//		Matriz matrizTeste = new Matriz(5, 3);
//		matrizTeste.setColuna(0, vetorV1);
//		matrizTeste.setColuna(1, vetorV2);
//		matrizTeste.setColuna(2, vetorV3);
//		
//		for (int i = 0; i < matrizTeste.getNumeroDeLinhas(); i++) {
//			System.out.println(matrizTeste.getLinha(i));
//		}
		
		System.out.println("x1 mean = " + matriz.getColuna(0).getMean());
		System.out.println("x2 mean = " + matriz.getColuna(1).getMean());
		System.out.println("x3 mean = " + matriz.getColuna(2).getMean());
		
		System.out.println("x1,x2 covariance = " + matriz.getColuna(0).getCovariance(matriz.getColuna(1)));
		System.out.println("x1,x3 covariance = " + matriz.getColuna(0).getCovariance(matriz.getColuna(2)));
		System.out.println("x2,x3 covariance = " + matriz.getColuna(1).getCovariance(matriz.getColuna(2)));
		
//		System.out.println("v1 variance = " + vetorV1.getVariance());
//		System.out.println("v2 variance = " + vetorV2.getVariance());
//		System.out.println("v3 variance = " + vetorV3.getVariance());
//		
//		System.out.println("covariancia v1 e v2 = " + vetorV1.getCovariance(vetorV2));
//		System.out.println("covariancia v1 e v3 = " + vetorV1.getCovariance(vetorV3));
//		System.out.println("covariancia v2 e v3 = " + vetorV2.getCovariance(vetorV3));
		
//		System.out.println("Matriz de covariancia da matriz teste");
//		Matriz matrizTesteCovariancia = new Matriz(matrizTeste.getMatrizDeCovariancia().getMatriz(), matrizTeste.getNumeroDeColunas(), matrizTeste.getNumeroDeColunas());
//		for (int i = 0; i < matrizTesteCovariancia.getNumeroDeLinhas(); i++) {
//			System.out.println(matrizTesteCovariancia.getLinha(i));
//		}
		
//		PontoDoGrafico p1 = new PontoDoGrafico(1.0,2.0);
//		PontoDoGrafico p2 = new PontoDoGrafico(2.0,4.0);
//		PontoDoGrafico p3 = new PontoDoGrafico(3.0,6.0);
//		List<PontoDoGrafico> lista = new ArrayList<PontoDoGrafico>();
//		lista.add(p1); lista.add(p2); lista.add(p3);
//		Grafico graf = new Grafico("teste");
//		graf.adicionarSerie(lista, "serie teste");
//		graf.exibirGrafico();
		
		Grafico g = new Grafico("Grafico de densidade de X1");
		Vetor x1 = matriz.getColuna(1);
		List<PontoDoGrafico> listaDePontos = new ArrayList<PontoDoGrafico>();
		List<Double> pontosDeReferencia = x1.getPontosDeReferencia(100);
		for(int i = 0; i < pontosDeReferencia.size(); i++) {
			KNN knn = new KNN(x1, 5);
			double x = pontosDeReferencia.get(i);
			double y = knn.getDensidade(x);
			PontoDoGrafico pontoDoGrafico = new PontoDoGrafico(x, y);
			listaDePontos.add(pontoDoGrafico);
		}
		g.adicionarSerie(listaDePontos, "X1");
		g.exibirGrafico();
		
	}

}
