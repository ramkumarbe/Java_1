package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.ui;

import java.util.Scanner;

public abstract class BaseView {
	Scanner sc = new Scanner(System.in);
	
	public int getInt() {
		while(true) {
			if(sc.hasNextInt()) {
				int n= sc.nextInt(); sc.nextLine();
				return n;
			}
			System.out.println("Enter valid Integer value.");
			sc.nextLine();
		}
	}
	
	public String getString() {
		return sc.nextLine();
	}
}
