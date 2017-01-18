package com.mustafabatmaz.genetikAlgoritma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Populasyon {
	private List<Kromozom> topluluk;
	private float mutasyonOrani = 0.15f;
	private int maxBoyut = 10;
	private Random rdm;

	public Populasyon(List<Kromozom> ilkGelen) {
		topluluk = new ArrayList<>(ilkGelen);
		rdm = new Random();
	}

	/**
	 * @return a
	 */
	public Populasyon yeniNesilOlustur() {
		List<Kromozom> yeniNesil = new ArrayList<>();
		while (yeniNesil.size() < maxBoyut) {
			List<Kromozom> secilenler;
			List<Integer> secimIndex;
			do {
				secilenler = new ArrayList<>();
				secimIndex = new ArrayList<>();
				for (int i = 0; i < 2; i++) {
					int uretilen1, uretilen2;
					do {
						uretilen1 = rdm.nextInt(10);
						uretilen2 = rdm.nextInt(10);
					} while (uretilen1 == uretilen2);
					double uyg1, uyg2;
					uyg1 = topluluk.get(uretilen1).uygunlukFonk();
					uyg2 = topluluk.get(uretilen2).uygunlukFonk();
					if (uyg1 < uyg2) {
						secilenler.add(topluluk.get(uretilen2));
						secimIndex.add(uretilen2);
					} else {
						secilenler.add(topluluk.get(uretilen1));
						secimIndex.add(uretilen1);
					}
				}
			} while (secimIndex.get(0) == secimIndex.get(1));

			Kromozom k1 = new Kromozom(secilenler.get(0));
			Kromozom k2 = new Kromozom(secilenler.get(1));
			Kromozom.caprazla(k1, k2);
			yeniNesil.add(k1);
			yeniNesil.add(k2);
		}

		for (int i = 0; i < ((float) maxBoyut) * mutasyonOrani; i++) {
			int cizilen = rdm.nextInt(maxBoyut);
			yeniNesil.get(cizilen).mutasyonaUgra();
		}
		return new Populasyon(yeniNesil);
	}

	public void sirala() {
		Collections.sort(topluluk);
	}

	public Kromozom enGucluBirey() {
		sirala();
		return topluluk.get(topluluk.size() - 1);
	}

}
