package br.ufc.arida.bcl.rp20152.grafico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafico extends ApplicationFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private DefaultCategoryDataset dataset;
	
	private JFreeChart chart;
	
	private XYDataset dataset;
	
	private List<XYSeries> series;
	
	public Grafico(String tituloDaAplicacao) {
		super(tituloDaAplicacao);
		series = new ArrayList<XYSeries>();
	}
	
	public void construirParaExibicao() {
		dataset = createDataset();
		chart = createChart(dataset, "Grafico de P(x) por densidade", "x", "P(x)");

		//chart = ChartFactory.createBarChart(tituloDoGrafico, legendaX, tipoDeValorX, dataset);
		//chart = ChartFactory.createLineChart(tituloDoGrafico, legendaX, tipoDeValorX, dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		setContentPane(chartPanel);
	}
	
	public void adicionarSerie(List<PontoDoGrafico> listaDePontos, String nomeDaSerie) {
		XYSeries serieXY = new XYSeries(nomeDaSerie);
		for (PontoDoGrafico pontoDoGrafico : listaDePontos) {
			serieXY.add(pontoDoGrafico.getX(), pontoDoGrafico.getY());
		}
		series.add(serieXY);
	}

	private XYDataset createDataset() {
		final XYSeriesCollection dataset = new XYSeriesCollection();
		for (XYSeries xySerie : series) {
			dataset.addSeries(xySerie);
		}
		return dataset;
	}
	
	private JFreeChart createChart(XYDataset dataset, String tituloDoGrafico, String labelX, String labelY) {
		
		JFreeChart chart = ChartFactory.createXYLineChart(tituloDoGrafico,// chart title
				labelX, // x axis label
				labelY, // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
		);
		
		/*
		 * Inserir customizacao do grafico aqui
		 */
		
		return chart;
	}

	/**
	 * Gera e exporta uma imagem JPG do grafico. Alem disso escreve o link na tela para a figura
	 * ser acessada remotamente
	 */
	public void exportarGraficoComoFigura() {
		construirParaExibicao();
		
		double numeroaletorio = Math.random() * 1000000;
		String nomeAux = "" + numeroaletorio;
		String nomeAleatorio = "grafico" + nomeAux.substring(0,5) + ".jpg";

		try {
			ChartUtilities.saveChartAsJPEG(new java.io.File(nomeAleatorio), chart, 700, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Exibe o grafico na tela em uma janela de aplicacao
	 */
	public void exibirGrafico() {
		construirParaExibicao();
		
		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}
}