package br.ufc.arida.bcl.rp20152.aplicacao;

import java.util.ArrayList;
import java.util.List;

import br.ufc.arida.bcl.rp20152.arquivos.FileHandler;
import br.ufc.arida.bcl.rp20152.entidades.Matriz;
import br.ufc.arida.bcl.rp20152.entidades.Vetor;
import br.ufc.arida.bcl.rp20152.grafico.Grafico;
import br.ufc.arida.bcl.rp20152.grafico.PontoDoGrafico;
import br.ufc.arida.bcl.rp20152.knn.KNN;

public class Aplicacao {

	public static void main(String[] args) {
	
		/*
		 * Carregamento dos dados de entrada para a variavel matriz
		 */
		FileHandler fileHandler = new FileHandler("dados.csv", ";");
		Matriz matriz = new Matriz(fileHandler.getMatrizDeEntrada().getMatriz(),
									fileHandler.getNumeroDeLinhas(), fileHandler.getNumeroDeColunas());
		
	/*
	 * EXERCICIO 2	
	 */
	System.out.println("Exercício 2");
	Matriz matrizDeCovariancia = matriz.getMatrizDeCovariancia();
	System.out.println("a) Matriz de covariancia:\n" + matrizDeCovariancia);
	
	System.out.println("b) e c) feitos somente no R\n");
	
	System.out.println("d)");
	Vetor x1 = new Vetor(matriz.getColuna(0).getVetor());
	Vetor x2 = new Vetor(matriz.getColuna(1).getVetor());
	Vetor x3 = new Vetor(matriz.getColuna(2).getVetor());
	System.out.println("Variancia de X1 = Covariancia(X1,X1): " + x1.getVariance());
	System.out.println("Variancia de X2 = Covariancia(X2,X2)): " + x2.getVariance());
	System.out.println("Variancia de X3 = Covariancia(X3,X3)): " + x3.getVariance());
	
	System.out.println("e)");
	System.out.println("Mean(X1): " + x1.getMean());
	System.out.println("Mean(X2): " + x2.getMean());
	System.out.println("Mean(X3): " + x3.getMean());
	
	/*
	 * EXERCICIO 3
	 */
	System.out.println("\nExercício 3 feitos no R\n");
	
	/*
	 *EXERCICIO 4
	 */
	System.out.println("Exercicio 4\nalterar os parametros numVetor, discretizacao e k para geracao do grafico desejado");
		/*
		 * alterar para 1 a 3
		 * 1 para X1, 2 para X2 e 3 para X3
		 */
		int numVetor = 3;
		
		/*
		 * Discretizacao: entrar valor inteiro positivo.
		 */
		int discretizacao = 1000;
		
		/*
		 * Valor de K, sendo K o número de vizinhos.
		 */
		int k = 15;
		
		String tituloDaAplicacao = "K-nearest-neighbour density estimation";
		String tituloDoGrafico = "Grafico de densidade = X" + numVetor + "; discretizaçao = " + discretizacao + "; K = " + k;
		Grafico g = new Grafico(tituloDaAplicacao, tituloDoGrafico);
		
		/*
		 * Vetor da matriz utilizado no algoritmo
		 */
		Vetor vetor = matriz.getColuna(numVetor-1);
		
		List<PontoDoGrafico> listaDePontos = new ArrayList<PontoDoGrafico>();
		List<Double> pontosDeReferencia = vetor.getPontosDeReferencia(discretizacao);
		
		for(int i = 0; i < pontosDeReferencia.size(); i++) {
			KNN knn = new KNN(vetor, k);
			double x = pontosDeReferencia.get(i);
			double y = knn.getDensidade(x);
			PontoDoGrafico pontoDoGrafico = new PontoDoGrafico(x, y);
			/*
			 * Descomentar para visualizar os pontos do grafico
			 */
			System.out.println(pontoDoGrafico);
			listaDePontos.add(pontoDoGrafico);
		}
		g.adicionarSerie(listaDePontos, "X" + numVetor);
		g.exibirGrafico();
		//g.exportarGraficoComoFigura();
	}

}
