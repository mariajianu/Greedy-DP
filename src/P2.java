import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P2 {
	private static List<Long> list = new ArrayList<Long>();
	private static int N;
	private static int k;

	public static long findMaxTwo() {
		/* 
		daca numarul de elemente ce trebuiesc eliminate
		este egal cu totalul numerelor, le scoatem pe toate 
		deci diferenta dintre scoruri este 0
		*/
		if (k == N)
			return 0;
		/*
		sortez si inversez lista de numele ca sa le am in
		ordine descrescatoare
		*/
		Collections.sort(list);
		Collections.reverse(list);
		/*
		matricea in care voi tine rezultatele partiale
		(explic in README mai in detaliu cum este ea umpluta)
		*/
		long dp[][] = new long[N - k][k + 1];
		int i, j;
		for (i = 0; i < (N - k); i++) {
			for (j = 0; j <= k; j++) {
				/* pe prima linie pun maximul din vector */
				if (i == 0) 
					dp[i][j] = list.get(0);
				/*
				 * completez prima coloana cu diferentele dintre jucatori daca ar juca acelasi
				 * joc ca la prima problema
				 */
				else if (j == 0 && i % 2 == 0) // randul lui T
					dp[i][j] = dp[i - 1][j] + list.get(i + j);
				else if (j == 0 && i % 2 == 1) // randul lui R
					dp[i][j] = dp[i - 1][j] - list.get(i + j);
				/*
				 * umplu matricea dupa regula: la fiecare pas cea mai buna alegere este fie sa
				 * elimin numarul, fie sa nu-l elimin (deci fac max dintre cele 2 posibilitati)
				 */
				else if (i % 2 == 0)
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j] + list.get(i + j));
				else if (i % 2 == 1)
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j] - list.get(i + j));
			}
		}
		/* diferenta maxima dintre scorurile lor este ultimul elem din matrice */
		return dp[N - k - 1][k];
	}

	public static void main(String[] args) {
		Scanner reader = null;
		/* deschid fisierul de input */
		try {
			reader = new Scanner(new File("p2.in"));
			N = reader.nextInt();
			k = reader.nextInt();

			while (reader.hasNext()) {
				list.add(reader.nextLong());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		/* calculez diferenta maxima dintre scorurile jucatorilor */
		long rez = findMaxTwo();
		/* deschid si scriu in fisierul de output */
		try {
			FileWriter writer = new FileWriter("p2.out");
			writer.write(String.valueOf(rez));
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
