package com.mustafabatmaz.genetikAlgoritma;

import java.util.ArrayList;
import java.util.Random;

public class Kromozom implements Comparable<Kromozom> {

	private ArrayList<Integer> genler = new ArrayList<>();

	private int[][] mutasyonSinirlari = { { 0, 5 }, { 1, 40 }, { 0, 6 }, { 0, 5 }, { 0, 2 }, { 4, 25 }, { 0, 9 },
			{ 0, 10 }, { 0, 5 } };

	public Kromozom(int dizi[]) {
		for (int i = 0; i < dizi.length; i++) {
			genler.add(dizi[i]);
		}
	}

	public Kromozom(Kromozom k) {
		this.genler = new ArrayList<>(k.genler);
	}

	public static void caprazla(Kromozom k1, Kromozom k2) {
		Random rdm = new Random();
		int secilenGrup = rdm.nextInt(3);
		if (secilenGrup == 0) {
			for (int i = 0; i < 2; i++) {
				int gen1 = k1.genler.get(i);
				k1.genler.set(i, k2.genler.get(i));
				k2.genler.set(i, gen1);
			}
		} else if (secilenGrup == 1) {
			for (int k = 2; k < 6; k++) {
				int gen2 = k1.genler.get(k);
				k1.genler.set(k, k2.genler.get(k));
				k2.genler.set(k, gen2);

			}
		} else if (secilenGrup == 2) {
			for (int l = 6; l < 9; l++) {
				int gen3 = k1.genler.get(l);
				k1.genler.set(l, k2.genler.get(l));
				k2.genler.set(l, gen3);
			}
		}

	}

	public double uygunlukFonk() {
		double sonuc = 0.316 + 0.342 * genler.get(0) - 0.669 * genler.get(3) + 0.247 * genler.get(5)
				- 0.517 * genler.get(2) + 0.570 * genler.get(8) - 0.449 * genler.get(6);
		return sonuc;
	}

	public void mutasyonaUgra() {
		Random rdm = new Random();
		int index = rdm.nextInt(genler.size());
		int altSinir = mutasyonSinirlari[index][0];
		int ustSinir = mutasyonSinirlari[index][1];
		int yeniDeger;
		do {
			yeniDeger = rdm.nextInt(ustSinir - altSinir) + altSinir;
		} while (yeniDeger == genler.get(index));
		genler.set(index, yeniDeger);
	}

	public String toString() {
		String str = "";
		for (Integer gen : genler) {
			str += " " + gen;
		}
		return str;
	}

	@Override
	public int compareTo(Kromozom o) {
		// TODO Auto-generated method stub
		if (this.uygunlukFonk() < o.uygunlukFonk())
			return -1;
		else if (this.uygunlukFonk() > o.uygunlukFonk())
			return 1;
		else
			return 0;
	}

}
