package com.ramkumarbe.problem.dailyproblems.day5;

import java.util.Arrays;
import java.util.Scanner;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		new LongestCommonPrefix().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of Elements: ");
		int numberOfElements = sc.nextInt(); sc.nextLine();
		String[] arr = new String[numberOfElements];
		for(int i=0; i<numberOfElements; i++) {
			System.out.println("Enter String"+(i+1)+": ");
			arr[i] = sc.nextLine();
		}
		String longestCommonPrefix = getLongestCommonPrefix(arr);
		System.out.println(longestCommonPrefix);
	}

	private String getLongestCommonPrefix(String[] arr) {
		Arrays.sort(arr);
		StringBuilder str = new StringBuilder();
		String first = arr[0];
		String last = arr[arr.length-1];
		for(int i=0; i<first.length()&&first.charAt(i)==last.charAt(i); i++) {
			str.append(first.charAt(i));
		}
		return str.toString();
	}

}
