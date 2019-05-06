import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P1 {

	private static List<Integer> list = new ArrayList<Integer>();
	private static int N;

	static int findMax() {
		/*
		sortez lista ca sa am elemntele in ordine
		*/
		Collections.sort(list);
		/*
		la inceput, scorul ambilor jucatori este 0
		*/
		int tuzgu = 0, ritza = 0;
		/*
		daca este un singur numar, il va alege
		Tuzgu si jocul se termina
		*/
		if (N == 1)
			return list.get(0);
		/*
		daca sunt 2 numere o sa ia fiecare cate unul,
		dar Tuzgu il va lua pe cel mai mare
		*/
		if (N == 2)
			return list.get(1) - list.get(0);
		/*
		daca totalul numerelor din sir este par,
		Tuzgu va alege mereu elem de pe pozitiile
		pare pentru ca el alege primul
		*/
		if (N % 2 != 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i % 2 == 0)
					tuzgu += list.get(i);
				else
					ritza += list.get(i);
			}
			return tuzgu - ritza;
		/*
		daca totalul numerelor din sir este impar,
		se va intampla opusul
		*/
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (i % 2 == 0)
					ritza += list.get(i);
				else
					tuzgu += list.get(i);
			}
			/* returnez mereu diferenta dintr scorurile lor */
			return tuzgu - ritza;
		}
	}

	public static void main(String[] args)  throws IOException {
		/* deschid fisierul de input */
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("p1.in"));
			String nr = reader.readLine();
			N = Integer.parseInt(nr);
			String line = reader.readLine();
			String [] v = new String[N];
			v = line.split(" ");
			for(int i = 0; i < N; i++)
				list.add(Integer.parseInt(v[i]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		/* calculez diferenta maxima dintre scorurile jucatorilor */
		int rez = findMax();
		/* deschid si scriu in fisierul de output */
		try {
			FileWriter writer = new FileWriter("p1.out");
			writer.write(String.valueOf(rez));
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
