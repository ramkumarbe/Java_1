package com.ramkumarbe.problem.dailyproblems.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
3. Given a N*N square matrix, return an array of its anti-diagonals.
Look at the example for more details.
Example  Input:
1 2 3
4 5 6
7 8 9
Return the following :
[[1],[2, 4],[3, 5, 7],[6, 8],[9]]
Input :
1 2
3 4
Return the following :
[[1],[2, 3],[4]]
 */
public class AntiDiagonals {

	public static void main(String[] args) {
		new AntiDiagonals().init();
	}

	private void init() {
		Scanner sc = new Scanner(System.in);
//		int[][] arr = {{1,2,3}, {4,5,6}, {7,8,9}};
//		int rows = 3;
		System.out.print("Enter the number of rows: ");
		int rows = sc.nextInt();
	    int[][] arr = new int[rows][rows];
	    for(int i=0; i<rows; i++) {
	    	for(int j=0; j<rows; j++) {
		    	System.out.print("Enter the Number at["+i+"]["+j+"]: ");
		    	arr[i][j] = sc.nextInt();
	    	}
	    }
	    List<List<Integer>> res = new ArrayList<>();
	    for(int i=0; i<2*rows-1; i++) {
	    	res.add(new ArrayList<>());
	    }
	    for(int i=0; i<rows; i++) {
	    	for(int j=0; j<rows; j++) {
	    		res.get(i+j).add(arr[i][j]);
	    	}
	    }
	    System.out.println(res);
	}

}
