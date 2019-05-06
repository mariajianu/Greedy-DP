import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P3 {
	private static List<Long> list = new ArrayList<Long>();
	private static int N;

	static long findMaxThree() {
		/* 
		calculez suma tuturor numerelor ca sa pot 
		determina ulterior diferenta de scoruri
		*/
		long sum = 0;
		long tuzgu, ritza;
		long var1, var2;
		int count = 0;
		int i, k;
		long pos1, pos2, pos3;
		for(long l : list)
			sum += l;
		/* 
		dp[i][j] contine valoarea maxima pe care
		Tuzgu o poate avea de la mutarea i la mutarea j
		unde i e valoarea din stanga vectorului si j
		valoarea din dreapta
		*/
		long dp[][] = new long[N][N];
		while(count < N) {
			i = 0;
			for (k = count; k < N; k++) {
				/*
				pos1, pos2, pos3 sunt rezultatele intermediare
				care ne ajuta sa calculam ce trebuie sa aleaga
				Ritza ca sa aiba scorul cat mai mic in fct
				de ce a ales Tuzgu 
				 */
				if(i + 2 < k + 1)
					pos1 = dp[i + 2][k];
				else
					pos1 = 0;
				if(i + 1 < k)
					pos2 =  dp[i + 1][k - 1];
				else
					pos2 = 0;
				if(i < k - 1)
					pos3 = dp[i][k - 2];
				else
					pos3 = 0;
				/* 
				la fiecare mutare Tuzgu alege maximul dintre
				valoarea curenta din vector (cea mai din stanga
				sau cea mai din dreapta) si minimul dintre ce
				ar putea sa alega Ritza -> folosesim minimul
				pentru ca el vrea ca Ritza sa fie obligata sa
				ia un numar cat mai mic in urma mutarii lui
				 */ 
				var1 = Long.min(pos1, pos2) + list.get(i);
				var2 = Long.min(pos2, pos3) + list.get(k);
				dp[i][k] = Long.max(var1, var2);
				i++;
			}
			count++;
		}
		
		tuzgu = dp[0][N - 1];
		ritza = sum - tuzgu;
		return tuzgu - ritza;
	}

	public static void main(String[] args) {
		/* deschid fisierul de input */
		Scanner reader = null;
		try {
			reader = new Scanner(new File("p3.in"));
			N = reader.nextInt();
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
		long rez = findMaxThree();
		/* deschid si scriu in fisierul de output */
		try {
			FileWriter writer = new FileWriter("p3.out");
			writer.write(String.valueOf(rez));
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
