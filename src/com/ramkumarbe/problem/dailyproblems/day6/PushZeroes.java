package com.ramkumarbe.problem.dailyproblems.day6;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 1.Given an array of random numbers, Push all the zero’s of a given array to the end of the array.
 * For The order of all other elements should be same.
 * Exmples :
 * Input : {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}
 * Output : {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}.
 * Input : {1, 0, 1, 0, 1, 0, 1, 0}
 * Output : {1, 1 , 1 , 1, 0, 0, 0, 0}
 */
public class PushZeroes {

	public static void main(String[] args) {
		new PushZeroes().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
//		int[] arr = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
		System.out.print("Enter the number of Elements: ");
		int numberOfElements = sc.nextInt();
	    int[] arr = new int[numberOfElements];
	    for(int i=0; i<numberOfElements; i++) {
	    	System.out.print("Enter the Number"+(i+1)+" :");
	    	arr[i] = sc.nextInt();
	    }
	    pushZeroes(arr, numberOfElements);
	    System.out.println(Arrays.toString(arr));
	}

	private void pushZeroes(int[] arr, int n) {
		for(int i=0, j=0; i<n; i++) {
			while(i<n && arr[i]==0)  i++;
			while(j<i && arr[j]!=0)  j++;
			if(i!=n && i!=j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}

}
