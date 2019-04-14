package maxsum;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSum {
	public static int calSum(int[] a) {
		int len = a.length;
		int sum = 0;
		int thisSum = 0;
		for(int i = 0; i < len; i++) {
			thisSum += a[i];
			if (thisSum > sum) {
				sum = thisSum;
			}else {
				thisSum = 0;
			}
		}
		return sum;
	}

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		Scanner x = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please Input Size of Arrays :");
		int m = scanner.nextInt();
		System.out.print("Please Input Your number :");
		int[] n = new int[m];
		for (int i = 0; i < m; i++) {
			n[i] = scanner.nextInt();
		}
		scanner.close();
		System.out.println("Your Input is :" + Arrays.toString(n));
		int out = calSum(n);
		System.out.println("The Result is : " + out);
	}
}
