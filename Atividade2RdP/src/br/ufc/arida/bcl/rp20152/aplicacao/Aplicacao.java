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
		
		//Alterar para 1 a 3
		int numVetor = 1;
		
		//Alterar para o valor desejado
		int discretizacao = 1000;
		
		//Alterar para o valor desejado
		int k = 1;
		
		FileHandler fileHandler = new FileHandler("dados.csv", ";");
		Matriz matriz = new Matriz(fileHandler.getMatrizDeEntrada().getMatriz(),
									fileHandler.getNumeroDeLinhas(), fileHandler.getNumeroDeColunas());
		
		System.out.println(matriz);
		
		String tituloDaAplicacao = "K-nearest-neighbour density estimation";
		String tituloDoGrafico = "Grafico de densidade = X" + numVetor + "; discretizaçao = " + discretizacao + "; K = " + k;
		Grafico g = new Grafico(tituloDaAplicacao, tituloDoGrafico);
		Vetor vetor = matriz.getColuna(numVetor-1);
		
		List<PontoDoGrafico> listaDePontos = new ArrayList<PontoDoGrafico>();
		List<Double> pontosDeReferencia = vetor.getPontosDeReferencia(discretizacao);
		
		for(int i = 0; i < pontosDeReferencia.size(); i++) {
			KNN knn = new KNN(vetor, k);
			double x = pontosDeReferencia.get(i);
			double y = knn.getDensidade(x);
			PontoDoGrafico pontoDoGrafico = new PontoDoGrafico(x, y);
			System.out.println(pontoDoGrafico);
			listaDePontos.add(pontoDoGrafico);
		}
		g.adicionarSerie(listaDePontos, "X" + numVetor);
		g.exibirGrafico();
	}

}
