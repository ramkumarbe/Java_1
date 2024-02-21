package com.ramkumarbe.problem.dailyproblems.day6;

import java.util.Scanner;

/*
4. Write a program to compress the given string by using the below rules,
i) Replace all the consecutive occurrence of a character with that 
character followed by count.
ii) If the consecutive occurrence not found, then use the character 
alone.(if the character present only once, then don't include the count)
Examples:
Input: aaabbcdd
Output: a3b2cd2
Input: xyyyzzzabb
Output: xy3z3ab2
*/
public class CompressString {

	public static void main(String[] args) {
		new CompressString().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the String: ");
		String str = sc.nextLine();
		String res = getCompressString(str);
		System.out.println(res);
	}

	private String getCompressString(String str) {
		char[] arr = str.toCharArray();
		int n = arr.length;
		StringBuilder res = new StringBuilder();
		int[] freq = new int[26];
		for(char c:arr) {
			freq[c-'a']++;
		}
		for(int i=0; i<n; ) {
			char c = arr[i];
			int f = freq[c-'a'];
			if(f!=1) {
				res.append(c).append(f);
			}
			else {
				res.append(c);
			}
			i+=f;
		}
		return res.toString();
	}

}
