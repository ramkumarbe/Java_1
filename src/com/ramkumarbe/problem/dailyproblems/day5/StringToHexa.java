package com.ramkumarbe.problem.dailyproblems.day5;

import java.util.Scanner;

/*
 * 5. Write a Java program to convert a string to its equivalent hexadecimal representation.
 * Input: "hello world"
 * Output: "68656C6C6F20776F726C64"
 */
public class StringToHexa {

	public static void main(String[] args) {
		new StringToHexa().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the String: ");
		String str = sc.nextLine();
		String hexaValue = StringToHexaConverter(str);
		System.out.println(hexaValue);
	}

	private String StringToHexaConverter(String str) {
		StringBuilder res = new StringBuilder();
		for (char c : str.toCharArray()) {
			res.append(hexaValueOf(c));
		}
		return res.toString();
	}

	private String hexaValueOf(int c) {
		String[] hexaValues = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
		StringBuilder res = new StringBuilder();
		while(c>0) {
			res.append(hexaValues[c%16]);
			c /= 16;
		}
		return res.reverse().toString();
	}

}
