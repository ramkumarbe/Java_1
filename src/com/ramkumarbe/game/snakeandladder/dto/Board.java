package com.ramkumarbe.game.snakeandladder.dto;

public class Board {
	private int row;
	private Cell[][] board;
	
	public Board(int row) {
		board = new Cell[row][row];
		int cellNumber=1;
		boolean asc = true;
		for(int i=row-1; i>=0; i--) {
			if(asc) {
				for(int j=0; j<row; j++) {
					board[i][j] = new Cell(cellNumber++);
				}
				asc = false;
			}
			else {
				for(int j=row-1; j>=0; j--) {
					board[i][j] = new Cell(cellNumber++);
				}
				asc = true;
			}
		}
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Cell[][] getBoard() {
		return board;
	}
	
	public String toString() {
		StringBuilder board = new StringBuilder();
		for(Cell[] cells:this.board) {
			for(Cell cell:cells) {
				board.append(cell).append("\t");
			}
			board.append("\n\n");
		}
		return board.toString();
	}
}
