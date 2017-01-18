package com.mustafabatmaz.genetikAlgoritma;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;

public class Uygulama {

	public static URL drawChart(List<Double>... fitness) {
		// Defining Line
		double minFitness = Double.MAX_VALUE;
		double maxFitness = Double.MIN_VALUE;
		List<Color> colors = new ArrayList<>();
		colors.addAll(Arrays.asList(new Color[] { Color.RED, Color.BLUE, Color.ORANGE, Color.CYAN }));
		for (List<Double> fit : fitness) {
			if (minFitness > Collections.min(fit))
				minFitness = Collections.min(fit);
			if (maxFitness < Collections.max(fit))
				maxFitness = Collections.max(fit);
		}
		List<Plot> plots = new ArrayList<>();
		int i = 1;
		for (List<Double> fit : fitness) {
			Line line = Plots.newLine(DataUtil.scaleWithinRange(minFitness - 0.5, maxFitness + 0.5, fit));
			line.setColor(colors.get(0));
			line.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
			line.setLegend(i++ + ".Sonuc");
			colors.remove(0);
			plots.add(line);
		}
		// Defining chart.
		final LineChart chart = GCharts.newLineChart(plots);
		chart.setSize(400, 400);
		chart.setTitle("Genetik Algoritma");
		chart.addXAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, fitness[0].size(), fitness[0].size() / 4));
		chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(minFitness - 0.5, maxFitness + 0.5));
		chart.setGrid(25, 25, 5, 5);

		// Defining background and chart fills.
		chart.setBackgroundFill(Fills.newSolidFill(Color.LIGHTGREY));

		String urlString = chart.toURLString();
		System.out.println(urlString);
		try {
			URL url = new URL(urlString);
			return url;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Kromozom> ilkPopulasyonUyeleri = new ArrayList<Kromozom>();
		int[][] genler = { { 0, 28, 2, 0, 0, 21, 5, 5, 0 }, { 0, 18, 0, 2, 0, 11, 1, 3, 0 },
				{ 1, 23, 1, 1, 0, 17, 2, 3, 0 }, { 0, 12, 0, 2, 0, 8, 1, 3, 0 }, { 1, 8, 1, 0, 0, 7, 0, 1, 0 },
				{ 1, 3, 1, 0, 0, 6, 0, 1, 3 }, { 1, 15, 0, 1, 0, 11, 0, 2, 0 }, { 0, 6, 2, 0, 0, 4, 0, 4, 0 },
				{ 0, 6, 0, 0, 0, 4, 1, 2, 0 }, { 0, 8, 1, 0, 0, 4, 0, 2, 0 } };
		ilkPopulasyonUyeleri.add(new Kromozom(genler[0]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[1]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[2]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[3]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[4]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[5]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[6]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[7]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[8]));
		ilkPopulasyonUyeleri.add(new Kromozom(genler[9]));

		List<Populasyon> populasyonlar = new ArrayList<>();
		populasyonlar.add(new Populasyon(ilkPopulasyonUyeleri));
		for (int i = 0; i < 10; i++) {
			Populasyon yeniNesil = populasyonlar.get(i).yeniNesilOlustur();
			populasyonlar.add(yeniNesil);
		}

		List<Populasyon> populasyonlar2 = new ArrayList<>();
		populasyonlar2.add(new Populasyon(ilkPopulasyonUyeleri));
		for (int i = 0; i < 10; i++) {
			Populasyon yeniNesil = populasyonlar2.get(i).yeniNesilOlustur();
			populasyonlar2.add(yeniNesil);
		}

		List<Populasyon> populasyonlar3 = new ArrayList<>();
		populasyonlar3.add(new Populasyon(ilkPopulasyonUyeleri));
		for (int i = 0; i < 10; i++) {
			Populasyon yeniNesil = populasyonlar3.get(i).yeniNesilOlustur();
			populasyonlar3.add(yeniNesil);
		}
		List<Double> populasyonBirGucluler = new ArrayList<>();
		List<Double> populasyonIkiGucluler = new ArrayList<>();
		List<Double> populasyonUcGucluler = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			System.out.print(populasyonlar.get(i).enGucluBirey().uygunlukFonk() + "\t\t\t\t");
			System.out.print(populasyonlar2.get(i).enGucluBirey().uygunlukFonk() + "\t\t\t\t");
			System.out.println(populasyonlar3.get(i).enGucluBirey().uygunlukFonk());
			populasyonBirGucluler.add(populasyonlar.get(i).enGucluBirey().uygunlukFonk());
			populasyonIkiGucluler.add(populasyonlar2.get(i).enGucluBirey().uygunlukFonk());
			populasyonUcGucluler.add(populasyonlar3.get(i).enGucluBirey().uygunlukFonk());
		}

		URL url = drawChart(populasyonBirGucluler, populasyonIkiGucluler, populasyonUcGucluler);

	}

}
