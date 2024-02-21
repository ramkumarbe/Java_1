package com.ramkumarbe.problem.dailyproblems.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
2. Write a program to sort the given array according to frequency of elements.
Examples :
Input : {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}
Output : {3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5}
Input : {0, 2, 1, -1, 1, 2, 0, 4, -1, 4}
Output : {-1, -1, 0, 0, 1, 1, 2, 2, 4, 4}
Input : { 4, 6, 8, 2, 5, 7, 9 }
Output : { 2, 4, 5, 6, 7, 8, 9 }
 */
public class SortByFrequency {

	public static void main(String[] args) {
		new SortByFrequency().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
//		int[] arr = {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12};
		System.out.print("Enter the number of Elements: ");
		int numberOfElements = sc.nextInt();
	    int[] arr = new int[numberOfElements];
	    for(int i=0; i<numberOfElements; i++) {
	    	System.out.print("Enter the Number"+(i+1)+" :");
	    	arr[i] = sc.nextInt();
	    }
	    sortByFrequency(arr);
	    System.out.println(Arrays.toString(arr));
	}

	private void sortByFrequency(int[] arr) {
		Map<Integer, Integer> freq = new HashMap<>();
		for(int i:arr) {
			freq.put(i, freq.getOrDefault(i, 0)+1);
		}
		System.out.println(freq);
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());
		Collections.sort(list, (a, b) ->{
			if(a.getValue()==b.getValue())
				return a.getKey()-b.getKey();
			return b.getValue()-a.getValue();
		});
		int index = 0;
		for(Map.Entry<Integer, Integer> entry:list) {
			int k = entry.getKey();
			int n = entry.getValue();
			for(int i=0; i<n; i++) {
				arr[index++] = k;
			}
		}
	}

}
