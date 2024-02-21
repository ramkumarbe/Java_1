package com.ramkumarbe.consoleApplication.cricketscoreboard.scorecardmanager;

import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Player;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Team;

public class ScoreCardManagerView extends BaseView {
	private ScoreCardManagerViewModel viewModel;

	public ScoreCardManagerView() {
		viewModel = new ScoreCardManagerViewModel(this);
	}

	public void startMatch() {
		System.out.println("\t\t\t  India vs Australia");
		setTotalOversOfTheMatch();
		toss();
		viewModel.setPlayers();
		startInnings();
		displayInningsScoreboard();
		viewModel.setSecondInnings();
		startInnings();
		result();
	}

	private void result() {
		displayMatchScoreboard();
		Team battingTeam = viewModel.getBattingTeam();
		Team bowlingTeam = viewModel.getBowlingTeam();
		if (battingTeam.getScore() > bowlingTeam.getScore()) {
			System.out.println(battingTeam.getName() + " won the match by " 
					+ (10 - battingTeam.getWickets()) + " wickets.");
		}
		else if (bowlingTeam.getScore() == battingTeam.getScore()) {
			System.out.println("Match Tied.");
		} 
		else {
			System.out.println(bowlingTeam.getName() + " won the match by "
					+ (bowlingTeam.getScore() - battingTeam.getScore()) + " runs.");
		}
	}

	private void startInnings() {
		viewModel.selectOpeners();
		int choice;
		do {
			displayMenu();
			choice = getInt();
			switch (choice) {
			case 1 -> {
				start();
			}
			case 2 -> {
				displayLiveScoreboard();
			}
			case 3 -> {
				displayInningsScoreboard();
			}
			case 4 -> {
				displayMatchScoreboard();
			}
			case 5 -> {
				System.out.println("Match has been dropped.");
				System.exit(0);
			}
			default -> {
				System.out.println("Invalid choice. Please enter a valid option.");
			}
			}
		} while (!viewModel.isInningsOver());
	}

	private void start() {
		do {
			System.out.println("Enter the result of the ball: ");
			showResultsOfTheBall();
			System.out.print(viewModel.batterAndBowlerDetails());
			int resultOfTheBall = getInt();
			if (resultOfTheBall == -1) {
				break;
			} else if (resultOfTheBall < 10) {
				viewModel.updateBall(resultOfTheBall);
			} else {
				System.out.println("Enter the valid Input.");
			}
		} while (!viewModel.isInningsOver());
	}

	Player selectBatsman() {
		boolean isValidInput;
		int selectedNumber, battedCount = 0;
		Player[] batsmen = viewModel.getBatsmen();
		do {
			System.out.println("Select the Batsman: ");
			int playerNumber = 0;
			for (Player player : batsmen) {
				if (!player.isBatted()) {
					System.out.println(++playerNumber + ". " + player.getName());
				} else {
					battedCount++;
				}
			}
			System.out.println();
			selectedNumber = getInt();
			isValidInput = selectedNumber <= playerNumber;
			if (!isValidInput) {
				System.out.println("Enter valid input.");
				getString();
			}
		} while (!isValidInput);
		Player batsman = batsmen[battedCount + selectedNumber - 1];
		batsman.setBatted(true);
		System.out.println("Selected Batsman: "+batsman.getName());
		return batsman;
	}

	Player selectBowler() {
		boolean isValidInput;
		int selectedNumber;
		Player[] bowlers = viewModel.getBowlers();
		do {
			System.out.println("Select the Bowler: ");
			int playerNumber = 0;
			for (Player player : bowlers) {
				if (player.canBowl()) {
					System.out.println(++playerNumber + ". " + player.getName());
				}
			}
			selectedNumber = getInt();
			isValidInput = selectedNumber <= playerNumber;
			if (!isValidInput) {
				System.out.println("Enter valid input.");
				getString();
			}
		} while (!isValidInput);
		Player bowler = null;
		for (Player player : bowlers) {
			if (player.canBowl()) {
				bowler = player;
				if (--selectedNumber == 0) {
					break;
				}
			}
		}
		System.out.println("Selected Bowler: "+bowler.getName());
		return bowler;
	}

	private void showResultsOfTheBall() {
		System.out.println("+-----------------------------------+");
		System.out.println("│0-6 to mention runs.       	    │");
		System.out.println("│7. Wide                    	    │");
		System.out.println("│8. No-Ball                 	    │");
		System.out.println("│9. Wicket                  	    │");
		System.out.println("│Enter -1 to view the current score.│");
		System.out.println("+-----------------------------------+");
	}

	private void setTotalOversOfTheMatch() {
		int numberOfOvers;
		do {
			System.out.print("Enter the total Number of Overs: ");
			numberOfOvers = getInt();
			if (numberOfOvers <= 0) {
				System.out.println("Number of overs cannot be zero or negative value.");
			}
		} while (numberOfOvers <= 0);
		viewModel.setTotalOversOfTheMatch(numberOfOvers);
	}

	private void toss() {
		System.out.println("Choose the first batting team: ");
		System.out.println("1.India \n2.Australia");
		int choice;
		boolean isValidChoice;
		do {
			choice = getInt();
			viewModel.setInnings(choice);
			isValidChoice = choice == 1 ^ choice == 2;
			if (!isValidChoice) {
				System.err.println("Enter valid choice.");
			}
		} while (!isValidChoice);
	}

	private void displayMenu() {
	    System.out.println("+-------------------------------+");
	    System.out.println("|          MENU OPTIONS         |");
	    System.out.println("+-------------------------------+");
	    System.out.println("| 1. Start/Resume match         |");
	    System.out.println("| 2. Show live scoreboard       |");
	    System.out.println("| 3. Show Innings ScoreBoard    |");
	    System.out.println("| 4. Show Match ScoreBoard      |");
	    System.out.println("| 5. Exit                       |");
	    System.out.println("+-------------------------------+");
	}

	private void displayLiveScoreboard() {
		System.out.println(viewModel.buildLiveScoreboard());
	}

	private void displayInningsScoreboard() {
		System.out.println("Innings Scoreboard:");
		System.out.println(viewModel.buildInningsScoreboard());
	}

	private void displayMatchScoreboard() {
		System.out.println("Match Scoreboard:");
		System.out.println(viewModel.matchScoreboard());
	}

	public void printResult(String result) {
		System.out.println(result);
	}
}
