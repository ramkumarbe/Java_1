package com.ramkumarbe.game.snakeandladder.dto;

public class Cell{
	private int cellNumber, toCell, minMove;
	private boolean snake, ladder;
	private Player player;
	
	public Cell(int cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	public String toString() {
		String cell = String.valueOf(cellNumber);
		return this.ladder?cell+"(L)":this.snake?cell+"(S)":cell;
	}
	public int getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(int cellNumber) {
		this.cellNumber = cellNumber;
	}

	public boolean isSnake() {
		return snake;
	}

	public void setSnake(boolean snake) {
		this.snake = snake;
	}

	public boolean isLadder() {
		return ladder;
	}

	public void setLadder(boolean ladder) {
		this.ladder = ladder;
	}

	public int getToCell() {
		return toCell;
	}

	public void setToCell(int toCell) {
		this.toCell = toCell;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMinMove() {
		return minMove;
	}

	public void setMinMove(int minMove) {
		this.minMove = minMove;
	}

}

