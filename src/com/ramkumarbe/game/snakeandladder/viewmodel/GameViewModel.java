package com.ramkumarbe.game.snakeandladder.viewmodel;

import java.util.LinkedList;
import java.util.Queue;

import com.ramkumarbe.game.snakeandladder.dto.Board;
import com.ramkumarbe.game.snakeandladder.dto.Cell;
import com.ramkumarbe.game.snakeandladder.dto.Player;
import com.ramkumarbe.game.snakeandladder.repository.Repository;
import com.ramkumarbe.game.snakeandladder.ui.Game;

public class GameViewModel {

	private Game view;
	Player[] players;

	public GameViewModel(Game view) {
		this.view = view;
	}

	private Board board;

	public void createNewBoard(int row) {
		board = new Board(row);
		Repository.getInstance().setBoard(board);
	}

	public void addSnakes(int[][] snakes) {
		Cell[][] board = Repository.getInstance().getBoard();
		for (int i = 0; i < snakes.length; i++) {
			int headCellNumber = snakes[i][0];
			int tailCellNumber = snakes[i][1];
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[row].length; col++) {
					if (board[row][col].getCellNumber() == headCellNumber) {
						board[row][col].setSnake(true);
						board[row][col].setToCell(tailCellNumber);
						break;
					}
				}
			}
		}
	}

	public void addLadders(int[][] ladders) {
		Cell[][] board = Repository.getInstance().getBoard();
		for (int i = 0; i < ladders.length; i++) {
			int bottomCellNumber = ladders[i][0];
			int topCellNumber = ladders[i][1];
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[row].length; col++) {
					if (board[row][col].getCellNumber() == bottomCellNumber) {
						board[row][col].setLadder(true);
						board[row][col].setToCell(topCellNumber);
						break;
					}
				}
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public void addPlayers(Player[] players) {
		this.players = players;
		Repository.getInstance().setPlayers(players);
	}

	int playCount;

	public Player getNextPlayer() {
		return players[playCount++ % players.length];
	}

	public void changePlayerPosition(Player player, int newPosition) {
		for (Cell[] cells : Repository.getInstance().getBoard()) {
			for (Cell cell : cells) {
				if (cell.getCellNumber() == newPosition) {
					if (cell.isSnake()) {
						view.printMessage(
								player.getPlayerName() + " was bitten by a snake on position " + cell.getCellNumber());
						changePlayerPosition(player, cell.getToCell());
					} else if (cell.isLadder()) {
						view.printMessage(
								player.getPlayerName() + " climbed a ladder at position " + cell.getCellNumber());
						changePlayerPosition(player, cell.getToCell());
					} else {
						player.setCell(cell);
					}
				}
			}
		}
	}

	public boolean canMove(int oldPosition, int move) {
		int row = Repository.getInstance().getBoard().length;
		return oldPosition + move <= row * row;
	}

	public boolean isHeWon(int newPosition) {
		int row = Repository.getInstance().getBoard().length;
		return newPosition == row * row;
	}

	public int findMinMoves() {
		int row = Repository.getInstance().getBoard().length;
		int end = row * row;
		Queue<Cell> que = new LinkedList<>();
		boolean[] visited = new boolean[end + 1];
		que.offer(new Cell(0));
		while (!que.isEmpty()) {
			Cell cell = que.poll();
			for (int i = 1; i <= 6; i++) {
				int cellNumber = cell.getCellNumber();
				int nextCellNumber = cellNumber + i;
				if(nextCellNumber == end) {
					return cell.getMinMove()+1;
				}
				else if(nextCellNumber > end) {
					break;
				}
				else if (!visited[nextCellNumber] && canMove(cellNumber, i)) {
					Cell nextCell = getCell(nextCellNumber);
					nextCell.setMinMove(cell.getMinMove() + 1);
					visited[nextCellNumber] = true;
					if (nextCell.isLadder() || nextCell.isSnake()) {
						nextCell = getCell(nextCell.getToCell());
						nextCell.setMinMove(cell.getMinMove() + 1);
						visited[nextCellNumber] = true;
					}
					que.offer(nextCell);
				}
			}
		}
		return 0;
	}

	private Cell getCell(int nextCellNumber) {
		for (Cell[] cells : board.getBoard()) {
			for (Cell cell : cells) {
				if (cell.getCellNumber() == nextCellNumber) {
					return cell;
				}
			}
		}
		return null;
	}
}
