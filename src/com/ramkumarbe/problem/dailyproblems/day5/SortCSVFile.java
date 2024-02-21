package com.ramkumarbe.problem.dailyproblems.day5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 3. Write a Java program that reads a list of stock prices from a CSV file,
 *  sorts the prices in descending order,
 *  and writes the sorted list to a new CSV file.
 */
public class SortCSVFile {

	public static void main(String[] args) throws IOException {
		new SortCSVFile().init();
	}

	private void init() throws IOException {
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\problem\\dailyproblems\\day5\\stocks.csv");
//		System.out.println(file.exists());
		List<Integer> list = readFromFile(file);
		System.out.println(list);
		Collections.sort(list, (a,b) -> b-a);
		System.out.println(list);
		writeInFile(list, new File("sortedStocks.csv"));
	}

	private void writeInFile(List<Integer> list, File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.append("prices");
		writer.newLine();
		for(int i:list) {
			writer.append(String.valueOf(i));
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}

	private List<Integer> readFromFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		List<Integer> list = new ArrayList<>();
		while((line=reader.readLine())!=null) {
			list.add(Integer.parseInt(line));
		}
		reader.close();
		return list;
	}

}
