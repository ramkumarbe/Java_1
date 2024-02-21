package com.ramkumarbe.problem.dailyproblems.day5;

import java.util.Scanner;
/*
 * 1. Find the total number of days within the time limit given. Include the from date. 
 * and exclude the to date
 * Input - 25-08-1998  & 27.09.2020
 * Output - 8069
 */
public class NumberOfDays {

	public static void main(String[] args) {
		new NumberOfDays().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Date1(DD/MM/YYYY): ");
		String date1 = sc.nextLine();
		System.out.print("Enter Date2(DD/MM/YYYY): ");
		String date2 = sc.nextLine();
		int numberOfDays = getDaysInBetween(date1, date2);
		System.out.println(numberOfDays);
	}

	private int getDaysInBetween(String date1, String date2) {
		
		int fromDate = Integer.parseInt(date1.substring(0, 2));
		int toDate = Integer.parseInt(date2.substring(0, 2));
		int fromMonth = Integer.parseInt(date1.substring(3,5));
		int toMonth = Integer.parseInt(date2.substring(3,5));
		int fromYear = Integer.parseInt(date1.substring(6));
		int toYear = Integer.parseInt(date2.substring(6));
		
		int totalDaysOfDate1 = getNumberOfDays(fromDate, fromMonth, fromYear);
		int totalDaysOfDate2 = getNumberOfDays(toDate, toMonth, toYear);
//		System.out.println(totalDaysOfDate1);
//		System.out.println(totalDaysOfDate2);
//		
		return totalDaysOfDate2-totalDaysOfDate1;
	}

	private int getNumberOfDays(int date, int month, int year) {
		int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int totalDays = (year%400==0||(year%100!=0&&year%4==0))&&month>2 ? date+1:date;
		for(int i=1; i<month; i++) {
			totalDays += days[i];
		}
		totalDays += yearToDays(year-1);
		return totalDays;
	}

	private int yearToDays(int year) {
		int days = year * 365;
		int leapYears = year/400 + year/4 - year/100;
		return days+leapYears;
	}

}
