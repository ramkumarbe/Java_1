package com.ramkumarbe.game.snakeandladder.ui;

import java.util.Random;
import java.util.Scanner;

import com.ramkumarbe.game.snakeandladder.dto.Board;
import com.ramkumarbe.game.snakeandladder.dto.Player;
import com.ramkumarbe.game.snakeandladder.viewmodel.GameViewModel;

public class Game {

	private GameViewModel viewModel;

	public Game() {
		viewModel = new GameViewModel(this);
	}

	Scanner sc = new Scanner(System.in);

	public void startGame() {
		createBoard();
//		printBoard();
		addSnakes();
//		printBoard();
		addLadders();
//		printBoard();
		addPlayers();
		printBoard();
		play();
//		findMinMoves();
	}

	private void findMinMoves() {
		int minMoves = viewModel.findMinMoves();
		System.out.println("Mininum moves required to finish the game: " + minMoves);
	}

	private void createBoard() {
		System.out.print("Enter the number of Rows: ");
		int row = sc.nextInt();
		viewModel.createNewBoard(row);
	}

	private void play() {
		boolean isMatchOver = false;
		int sixCount = 0;
		Player player = viewModel.getNextPlayer();
		while (!isMatchOver) {
			System.out.println("Roll a dice..");
			sc.nextLine();
			int move = new Random().nextInt(7);
			int oldPosition = player.getCell().getCellNumber();
			int newPosition = oldPosition;
			if (viewModel.canMove(oldPosition, move)) {
				newPosition = oldPosition + move;
			}
			viewModel.changePlayerPosition(player, newPosition);
			System.out.println(player.getPlayerName() + " rolled a " + move + " and moved " + oldPosition + " -> "
					+ player.getCell().getCellNumber() + ".");
			if (viewModel.isHeWon(player.getCell().getCellNumber())) {
				System.out.println(player.getPlayerName() + " is wins the match");
				isMatchOver = true;
			}

			sixCount = move == 6 ? sixCount + 1 : 0;
			if(move!=6 || sixCount>=6) {
				player = viewModel.getNextPlayer();
			}
		}
	}

	private void addSnakes() {
		System.out.print("Enter the number of Snakes: ");
		int numberOfSnakes = sc.nextInt();
		int[][] snakes = new int[numberOfSnakes][2];
		for (int i = 0; i < numberOfSnakes; i++) {
			System.out.print("Enter the head position of the snake" + (i + 1) + ": ");
			snakes[i][0] = sc.nextInt();
			System.out.print("Enter the tail position of the snake" + (i + 1) + ": ");
			snakes[i][1] = sc.nextInt();
		}
		viewModel.addSnakes(snakes);
	}

	private void addLadders() {
		System.out.print("Enter the number of Ladders: ");
		int numberOfLadders = sc.nextInt();
		int[][] ladders = new int[numberOfLadders][2];
		for (int i = 0; i < numberOfLadders; i++) {
			System.out.print("Enter the bottom position of the ladder" + (i + 1) + ": ");
			ladders[i][0] = sc.nextInt();
			System.out.print("Enter the top position of the ladder" + (i + 1) + ": ");
			ladders[i][1] = sc.nextInt();
		}
		viewModel.addLadders(ladders);
	}

	private void addPlayers() {
		System.out.print("Enter the number of players:");
		int numberOfPlayers = sc.nextInt();
		sc.nextLine();
		Player[] players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.print("Enter of Name of the Player" + (i + 1) + ": ");
			String name = sc.nextLine();
			Player player = new Player(name);
			players[i] = player;
		}
		viewModel.addPlayers(players);
	}

	private void printBoard() {
		Board board = viewModel.getBoard();
		System.out.println(board);
	}

	public void printMessage(String message) {
		System.out.println(message);
	}
}
