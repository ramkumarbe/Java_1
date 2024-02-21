package com.ramkumarbe.game.snakeandladder.repository;

import com.ramkumarbe.game.snakeandladder.dto.Board;
import com.ramkumarbe.game.snakeandladder.dto.Cell;
import com.ramkumarbe.game.snakeandladder.dto.Player;

public class Repository {

	private static Repository repository;
	private Repository() {  }
	public static Repository getInstance() {
		if(repository == null) {
			repository = new Repository();
		}
		return repository;
	}
	
	Cell[][] board;
	Player[] players;
	
	
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public void setBoard(Board board) {
		this.board = board.getBoard();
	}
	public Cell[][] getBoard() {
		return board;
	}
	
}
