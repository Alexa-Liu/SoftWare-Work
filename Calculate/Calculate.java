package mycalculate;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculate {
	public static void main(String[] args) throws IOException {
		WriteFile wr = new WriteFile();
		Scanner in = new Scanner(System.in);
		System.out.println("��������ֵ����");
		int M = in.nextInt();
		System.out.println("��������Ҫ��ӡ���ٵ��⣺");
		int N = in.nextInt();
		System.out.println("��ѡ�������ʽ��0����̨��1�ļ�");
		int w = in.nextInt();
		System.out.println("��ѡ��0����Ҫ������㣬1��Ҫ�������");
		int L = in.nextInt();
		if (L == 0) {
			System.out.println("��ѡ��0�ӷ���1������2�˷���3����");
			int i = in.nextInt();
			System.out.println("��ѡ��0����������С����1��������С��");
			int y = in.nextInt();
			if (i == 0) {
				Add add = new Add();
				add.setM(M);
				add.setN(N);
				if (y == 0) {wr.select(w);add.showMe();}
				if (y == 1) {wr.select(w);add.showMePoint();}
			}
			if (i == 1) {
				Sub sub = new Sub();
				sub.setM(M);
				sub.setN(N);
				if (y == 0) {wr.select(w);sub.showMe();}
				if (y == 1) {wr.select(w);sub.showMePoint();}
			}
			if (i == 2) {
				Mul mul = new Mul();
				mul.setM(M);
				mul.setN(N);
				if (y == 0) {wr.select(w);mul.showMe();}
				if (y == 1) {wr.select(w);mul.showMePoint();}
			}
			if (i == 3) {
				Div div = new Div();
				div.setM(M);
				div.setN(N);
				if (y == 0) {wr.select(w);div.showMe();}
				if (y == 1) {wr.select(w);div.showMePoint();}
			}
		}
		if (L == 1) {
			System.out.println("��ѡ��0�Ӽ���ϣ�1�˳���ϣ�2������");
			int k = in.nextInt();
			System.out.println("��ѡ��0����������С����1��������С��");
			int r = in.nextInt();
			if (k == 0) {
				AddSub addsub = new AddSub();
				addsub.setM(M);
				addsub.setN(N);
				if (r == 0) {wr.select(w);addsub.showMe();}
				if (r == 1) {wr.select(w);addsub.showMePoint();}
			}
			if (k == 1) {
				MulDiv muldiv = new MulDiv();
				muldiv.setM(M);
				muldiv.setN(N);
				if (r == 0) {wr.select(w);muldiv.showMe();}
				if (r == 1) {wr.select(w);muldiv.showMePoint();}
			}
			if (k == 2) {
				CalAll calall = new CalAll();
				calall.setM(M);
				calall.setN(N);
				if (r == 0) {wr.select(w);calall.showMe();}
				if (r == 1) {wr.select(w);calall.showMePoint();}
			}
		}
		in.close();
	}
}

class Calculate_All {
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

class WriteFile {
	public void select(int k) throws IOException {
		if (k == 1) {
			WriteFile.writeFile();
		}
	}

	public static void writeFile() throws IOException {
		File f = new File("Calculate.txt");
		OutputStream fileOutputStream = new FileOutputStream(f);
		PrintStream printStream = new PrintStream(fileOutputStream);
		System.setOut(printStream);
	}
}

class Add extends Calculate_All {
	public void showMe() {
		for (int i = 0; i < N; i++) {
			int a = (int) (Math.random() * M) + 1;
			int b = (int) (Math.random() * M) + 1;
			System.out.print(a + "+" + b + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	public void showMePoint() {
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < N; i++) {
			double a = (Math.random() * M);
			double b = (Math.random() * M);
			double c = (Math.random() * M);
			System.out.print("(" + df.format(a) + "+" + df.format(b) + ")" + "+" + df.format(c) + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}

class Sub extends Calculate_All {
	public void showMe() {
		for (int i = 0; i < N; i++) {
			int a = (int) (Math.random() * M) + 1;
			int b = (int) (Math.random() * M) + 1;
			System.out.print(a + "-" + b + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	public void showMePoint() {
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < N; i++) {
			double a = (Math.random() * M);
			double b = (Math.random() * M);
			double c = (Math.random() * M);
			System.out.print("(" + df.format(a) + "-" + df.format(b) + ")" + "-" + df.format(c) + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}

class Mul extends Calculate_All {
	public void showMe() {
		for (int i = 0; i < N; i++) {
			int a = (int) (Math.random() * M) + 1;
			int b = (int) (Math.random() * M) + 1;
			System.out.print(a + "*" + b + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	public void showMePoint() {
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < N; i++) {
			double a = (Math.random() * M);
			double b = (Math.random() * M);
			double c = (Math.random() * M);
			System.out.print("(" + df.format(a) + "*" + df.format(b) + ")" + "*" + df.format(c) + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}

class Div extends Calculate_All {
	public void showMe() {
		for (int i = 0; i < N; i++) {
			int a = (int) (Math.random() * M) + 1;
			int b = (int) (Math.random() * M) + 1;
			System.out.print(a + "/" + b + "= ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	public void showMePoint() {
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < N; i++) {
			double a = (Math.random() * M);
			double b = (Math.random() * M);
			double c = (Math.random() * M);
			System.out.print("(" + df.format(a) + "/" + df.format(b) + ")" + "/" + df.format(c) + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}

class AddSub extends Calculate_All {
	public char o[] = { '+', '-' };

	public void showMe() {
		for (int i = 0; i < N; i++) {
			int a = (int) (Math.random() * M) + 1;
			int b = (int) (Math.random() * M) + 1;
			int c = (int) (Math.random() * M) + 1;
			int d = (int) (Math.random() * 2);
			int e = (int) (Math.random() * 2);
			System.out.print(a);
			System.out.print(o[d]);
			System.out.print(b);
			System.out.print(o[e]);
			System.out.print(c + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	public void showMePoint() {
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < N; i++) {
			double a = (Math.random() * M);
			double b = (Math.random() * M);
			double c = (Math.random() * M);
			int d = (int) (Math.random() * 2);
			System.out.print("(" + df.format(a) + o[d] + df.format(b) + ")" + o[d] + df.format(c) + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}

class MulDiv extends Calculate_All {
	public char o[] = { '*', '/' };

	public void showMe() {
		for (int i = 0; i < N; i++) {
			int a = (int) (Math.random() * M) + 1;
			int b = (int) (Math.random() * M) + 1;
			int c = (int) (Math.random() * M) + 1;
			int d = (int) (Math.random() * 2);
			int e = (int) (Math.random() * 2);
			System.out.print(a);
			System.out.print(o[d]);
			System.out.print(b);
			System.out.print(o[e]);
			System.out.print(c + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	public void showMePoint() {
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < N; i++) {
			double a = (Math.random() * M);
			double b = (Math.random() * M);
			double c = (Math.random() * M);
			int d = (int) (Math.random() * 2);
			System.out.print("(" + df.format(a) + o[d] + df.format(b) + ")" + o[d] + df.format(c) + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}

class CalAll extends Calculate_All {
	public char o[] = { '+', '-', '*', '/' };

	public void showMe() {
		for (int i = 0; i < N; i++) {
			int a = (int) (Math.random() * M) + 1;
			int b = (int) (Math.random() * M) + 1;
			int c = (int) (Math.random() * M) + 1;
			int d = (int) (Math.random() * M) + 1;
			int e = (int) (Math.random() * 4);
			int f = (int) (Math.random() * 4);
			int g = (int) (Math.random() * 4);
			System.out.print(a);
			System.out.print(o[e]);
			System.out.print(b);
			System.out.print(o[f]);
			System.out.print(c);
			System.out.print(o[g]);
			System.out.print(d + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}

	public void showMePoint() {
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < N; i++) {
			double a = (Math.random() * M);
			double b = (Math.random() * M);
			double c = (Math.random() * M);
			double d = (Math.random() * M);
			int e = (int) (Math.random() * 4);
			int f = (int) (Math.random() * 4);
			int g = (int) (Math.random() * 4);
			System.out.print(
					df.format(a) + o[e] + "(" + df.format(b) + o[f] + df.format(c) + ")" + o[g] + df.format(d) + "=  ");
			System.out.println();
		}
		System.out.println("��Ŀ���ɽ���");
	}
}