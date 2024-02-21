package com.ramkumarbe.game.snakeandladder.dto;

public class Player {
	private String playerName;
	private Cell cell;
	
	public Player(String playerName) {
		this.setPlayerName(playerName);
		cell= new Cell(0);
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
