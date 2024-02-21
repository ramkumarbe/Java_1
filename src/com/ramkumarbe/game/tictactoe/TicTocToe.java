package com.ramkumarbe.game.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTocToe {

	private char[][] board;
	private static final char COMPUTER = 'O';
	private static final char PLAYER = 'X';
	private static final char EMPTY = ' ';
	private int row, col;

	public static void main(String[] args) {
		new TicTocToe().startGame();
	}

	private void startGame() {
		Scanner sc = new Scanner(System.in);
		setBoard();
		System.out.println("Choose who plays first: ");
		System.out.println("1.Computer\n2.Player");
		int choice = sc.nextInt();
		char currentPlayer = choice==1 ? COMPUTER:PLAYER;
		
		while (isMovesLeft()) {
			if (currentPlayer == COMPUTER) {
				selectBestPosition();
				waitForMove();
				System.out.println("Computer selected position "+(row*3+col+1));
				board[row][col] = COMPUTER;
				currentPlayer = PLAYER;
			} 
			else {
				System.out.println("Your Turn: ");
				System.out.println("Select the position(1-9): ");
				int pos = sc.nextInt()-1;
				board[pos/3][pos%3] = PLAYER;
				currentPlayer = COMPUTER;
			}
			printBoard();
			if (evaluate() == 1) {
				announceWinner(COMPUTER);  return;
			} 
			else if (evaluate() == -1){
				announceWinner(PLAYER);   return;
			}
		}
		System.out.println("Match Tied.");
		sc.close();
	}

	private void printBoard() {
		System.out.println("  -------------");
		for(char[] arr:board) {
			System.out.print("  |");
			for(char c:arr) {
				System.out.printf("%2s", c);
				System.out.print(" |");
			}
			System.out.println("\n  -------------");
		}
	}

	private void announceWinner(int player) {
		String playerName = player == COMPUTER ? "Computer" : "Player";
		System.out.println(playerName + " won the Game.");
	}

	private void selectBestPosition() {
		int best = -1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(board[i][j] == EMPTY) {
					board[i][j] = COMPUTER;
					int val = minMax(false);
					board[i][j] = EMPTY;
					if(val>best) {
						best = val;
						row = i;
						col = j;
					}
				}
			}
		}
	}

	private int minMax(boolean flag) {
		int score = evaluate();
		if(score == 1) {
			return 1;
		}
		if(score == -1) {
			return -1;
		}
		if(!isMovesLeft()) {
			return 0;
		}
		
		if(flag) {
			int best = -1;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if(board[i][j] == EMPTY) {
						board[i][j] = PLAYER;
						best = Math.max(best, minMax(!flag));
						board[i][j] = EMPTY;
					}
				}
			}
			return best;
		}
		else {
			int best = 1;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if(board[i][j] == EMPTY) {
						board[i][j] = COMPUTER;
						best = Math.min(best, minMax(!flag));
						board[i][j] = EMPTY;
					}
				}
			}
			return best;
		}
	}

	private int evaluate() {
		for(int i=0; i<3; i++) {
			if(board[i][0]==EMPTY || board[i][1]==EMPTY || board[i][2]==EMPTY) {
				continue;
			}
			if(board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				if(board[i][0] == COMPUTER) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
		for(int i=0; i<3; i++) {
			if(board[0][i]==EMPTY || board[1][i]==EMPTY || board[2][i]==EMPTY) {
				continue;
			}
			if(board[0][i]==board[1][i] && board[1][i]==board[2][i]) {
				if(board[0][i] == COMPUTER) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
		if(board[0][0]!=EMPTY && board[1][1]!=EMPTY && board[2][2]!=EMPTY) {
			if(board[0][0]==board[1][1] && board[1][1]==board[2][2]) {
				if(board[0][0] == COMPUTER) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
		
		if(board[0][2]!=EMPTY && board[1][1]!=EMPTY && board[2][0]!=EMPTY) {
			if(board[0][2]==board[1][1] && board[1][1]==board[2][0]) {
				if(board[0][2] == COMPUTER) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
		
		return 0;
	}

	private boolean isMovesLeft() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(board[i][j]==EMPTY) {
					return true;
				}
			}
		}
		return false;
	}

	private void setBoard() {
		board = new char[3][3];
		for(char[] arr:board) {
			Arrays.fill(arr, EMPTY);
		}
	}
	
	private void waitForMove() {
		System.out.print("Wait for Opponents move");
		for(int i=0; i<3; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print(".");
		}
		System.out.println();
	}

}
