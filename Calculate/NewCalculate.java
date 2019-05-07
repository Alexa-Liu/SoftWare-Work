package mycalculate;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class NewCalculate {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws IOException {
		Write wr = new Write();
		char t[] = { '(', ')' };
		char o[] = { '+', '-', '*', '/' };
		int a[] = new int[5];
		double b[] = new double[5];
		int p[] = new int[4];
		ArrayList list = new ArrayList();
		Cal cal = new Cal();
		System.out.println("��������ֵ�����Լ���Ŀ����");
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		int N = in.nextInt();
		cal.setM(M);
		cal.setN(N);
		System.out.println("������ѡ����Ҫ�������(+ - * /),1��Ҫ��0����Ҫ");
		for (int i = 0; i < 4; i++) {
			p[i] = in.nextInt();
			if (p[i] == 1) {
				list.add(o[i]);
			}
		}
		System.out.println("������ѡ���Ƿ���ҪС�������ţ�1��Ҫ��0����Ҫ");
		int x = in.nextInt();
		int y = in.nextInt();
		System.out.println("��ѡ��ʽ��1�ļ���0����̨");
		int z = in.nextInt();
		if (z == 1) {
			wr.writeFile();
			if (x == 0 && y == 0)
				cal.showMe(list, a);
			if (x == 0 && y == 1)
				cal.showMe(list, t, a);
			if (x == 1 && y == 0)
				cal.showMe(list, b);
			if (x == 1 && y == 1)
				cal.showMe(list, t, b);
		}
		if (z == 0) {
			if (x == 0 && y == 0)
				cal.showMe(list, a);
			if (x == 0 && y == 1)
				cal.showMe(list, t, a);
			if (x == 1 && y == 0)
				cal.showMe(list, b);
			if (x == 1 && y == 1)
				cal.showMe(list, t, b);
		}
		in.close();
	}
}

class Calculate_New {
	public int N;

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int M;

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}
}

class Write {
	public void writeFile() throws IOException {
		File f = new File("Calculate.txt");
		OutputStream fileOutputStream = new FileOutputStream(f);
		PrintStream printStream = new PrintStream(fileOutputStream);
		System.setOut(printStream);
	}
}

class Cal extends Calculate_New {
	@SuppressWarnings("rawtypes")
	public void showMe(ArrayList list, int a[]) {
		int l = list.size();
		a = new int[l + 1];
		int b[] = new int[l + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0, k = 0; j < l + 1; j++, k++) {
				a[j] = (int) (Math.random() * M) + 1;
				b[k] = (int) (Math.random() * l);
				if (j == l)
					System.out.print(a[j] + "= ");
				else
					System.out.print(a[j] + "" + list.get(b[k]));
			}
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	@SuppressWarnings("rawtypes")
	public void showMe(ArrayList list, char t[], int a[]) {
		int l = list.size();
		a = new int[l + 1];
		int b[] = new int[l + 1];
		for (int i = 0; i < N; i++) {
			System.out.print(t[0]);
			for (int j = 0, k = 0; j < l + 1; j++, k++) {
				a[j] = (int) (Math.random() * M) + 1;
				b[k] = (int) (Math.random() * l);
				if (l == 1) {
					if (j == 0)
						System.out.print(a[j] + "" + list.get(b[k]));
					else
						System.out.print(a[j] + "" + t[1] + "=");
				} else {
					if (j == 1)
						System.out.print(a[j] + "" + t[1] + "" + list.get(b[k]));
					else if (j == l)
						System.out.print(a[j] + "= ");
					else
						System.out.print(a[j] + "" + list.get(b[k]));
				}
			}
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	DecimalFormat df = new DecimalFormat("0.00");

	@SuppressWarnings("rawtypes")
	public void showMe(ArrayList list, double a[]) {
		int l = list.size();
		a = new double[l + 1];
		int b[] = new int[l + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0, k = 0; j < l + 1; j++, k++) {
				df.format(a[j] = (Math.random() * M) + 1);
				b[k] = (int) (Math.random() * l);
				if (j == l)
					System.out.print(df.format(a[j]) + "= ");
				else
					System.out.print(df.format(a[j]) + "" + list.get(b[k]));
			}
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	@SuppressWarnings("rawtypes")
	public void showMe(ArrayList list, char t[], double a[]) {
		int l = list.size();
		a = new double[l + 1];
		int b[] = new int[l + 1];
		for (int i = 0; i < N; i++) {
			System.out.print(t[0]);
			for (int j = 0, k = 0; j < l + 1; j++, k++) {
				a[j] = (Math.random() * M) + 1;
				b[k] = (int) (Math.random() * l);
				if (l == 1) {
					if (j == 0)
						System.out.print(df.format(a[j]) + "" + list.get(b[k]));
					else
						System.out.print(df.format(a[j]) + "" + t[1] + "=");
				} else {
					if (j == 1)
						System.out.print(df.format(a[j]) + "" + t[1] + "" + list.get(b[k]));
					else if (j == l)
						System.out.print(df.format(a[j]) + "= ");
					else
						System.out.print(df.format(a[j]) + "" + list.get(b[k]));
				}
			}
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}