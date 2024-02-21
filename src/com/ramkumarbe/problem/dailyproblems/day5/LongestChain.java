package com.ramkumarbe.problem.dailyproblems.day5;

import java.util.Arrays;
import java.util.Scanner;
/*
 * 2. You are given n pairs of numbers. In every pair, 
 * the first number is always smaller than the second number. 
 * A pair (c, d) can follow another pair (a, b) if b < c. 
 * Chain of pairs can be formed in this fashion. 
 * Find the longest chain which can be formed from a given set of pairs.
 * Input : {{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90} }
 * Output : 3
 */
public class LongestChain {

	public static void main(String[] args) {
		new LongestChain().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of pairs: ");
		int numberOfPairs = sc.nextInt();
		int[][] pairs = new int[numberOfPairs][2];
		for(int i=0; i<numberOfPairs; i++) {
			System.out.print("Enter the first number of Pair"+(i+1)+": ");
			pairs[i][0] = sc.nextInt();
			System.out.print("Enter the Second number of Pair"+(i+1)+": ");
			pairs[i][1] = sc.nextInt();
		}
		int longestChain = getLongestChain(pairs);
		System.out.println("Output: "+longestChain);
	}

	private int getLongestChain(int[][] pairs) {
		int count=1;
		Arrays.sort(pairs, (a,b) -> a[0]-b[0]);
//		System.out.println(Arrays.deepToString(pairs));
		int prev = 0;
		for(int i=1; i<pairs.length; i++) {
			if(pairs[i][0]>pairs[prev][1]) {
				prev = i;
				count++;
			}
		}
		return count;
	}
}
