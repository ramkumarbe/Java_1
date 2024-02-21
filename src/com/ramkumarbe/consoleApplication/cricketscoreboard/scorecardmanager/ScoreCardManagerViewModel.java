package com.ramkumarbe.consoleApplication.cricketscoreboard.scorecardmanager;

import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Ball;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Innings;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Over;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Player;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Team;
import com.ramkumarbe.consoleApplication.cricketscoreboard.repository.ScoreCardRepository;

public class ScoreCardManagerViewModel {

	ScoreCardRepository repository = ScoreCardRepository.getInstance();

	private ScoreCardManagerView view;

	ScoreCardManagerViewModel(ScoreCardManagerView scoreCardManager) {
		view = scoreCardManager;
		repository.load();
	}

	private Innings firstInnings, secondInnings, currentInnings;

	private int totalOvers, ballOfTheOver;

	private boolean isInningsOver, isFirstInnings;

	private Player[] batsmen, bowlers;

	private Player striker, nonStriker, bowler, previousBowler;

	private Over currentOver;

	private static final int WIDE = 7, NO_BALL = 8, WICKET = 9;

	public void setPlayers() {
		batsmen = currentInnings.getBattingTeam().getPlayers();
		bowlers = currentInnings.getBowlingTeam().getPlayers();
	}

	public void setInnings(int choice) {
		Team firstBattingTeam, secondBattingTeam;
		if (choice == 1) {
			firstBattingTeam = repository.getIndia();
			secondBattingTeam = repository.getAustralia();
		} else {
			firstBattingTeam = repository.getAustralia();
			secondBattingTeam = repository.getIndia();
		}
		firstInnings = new Innings(firstBattingTeam, secondBattingTeam);
		secondInnings = new Innings(secondBattingTeam, firstBattingTeam);
		currentInnings = firstInnings;
		isFirstInnings = true;
		currentOver = new Over(1);
		ballOfTheOver = 0;
	}

	public void updateBall(int resultOfTheBall) {
		switch (resultOfTheBall) {
		case 0, 1, 2, 3, 4, 5, 6 -> {
			ballOfTheOver++;
			updateScore(resultOfTheBall);
			if (resultOfTheBall % 2 != 0) {
				rotateStrike();
			}
		}
		case WIDE, NO_BALL -> {
			updateExtras();
		}
		case WICKET -> {
			ballOfTheOver++;
			updateScore(0);
			updateWicket();
		}
		}
		updateCurrentBall(resultOfTheBall);
		if (isOverCompleted(resultOfTheBall)) {
			updateOver();
		}
		if (!isFirstInnings) {
			checkIsTargetReached();
		}
	}

	private void checkIsTargetReached() {
		if (getBattingTeam().getScore() > getBowlingTeam().getScore()) {
			isInningsOver = true;
		} 
	}

	private void updateOver() {
		if (currentOver.getNumberOfOver() != totalOvers) {
			switchOver();
			bowler = view.selectBowler();
			currentOver = new Over(currentOver.getNumberOfOver() + 1);
			currentInnings.getOversOfTheInnings().add(currentOver);
			rotateStrike();
		} else {
			isInningsOver = true;
		}
		ballOfTheOver = 0;
	}

	private void switchOver() {
		if (previousBowler != null && previousBowler.getBallsBowled() / 6 < totalOvers / 5) {
			previousBowler.setCanBowl(true);
		}
		getBowler().setCanBowl(false);
		previousBowler = getBowler();
	}

	private void updateCurrentBall(int resultOfTheBall) {
		Ball ball = new Ball(getStriker(), getBowler(), resultOfTheBall, currentOver, ballOfTheOver);
		currentOver.getBalls().add(ball);
	}

	private void updateWicket() {
		getBowler().setWickets(getBowler().getWickets() + 1);
		getBattingTeam().setWickets(getBattingTeam().getWickets() + 1);
		if (getBattingTeam().getWickets() != 10) {
			striker = view.selectBatsman();
		} else {
			isInningsOver = true;
		}
	}

	private boolean isOverCompleted(int resultOfTheBall) {
		return bowler.getBallsBowled()%6 == 0 && resultOfTheBall != WIDE && resultOfTheBall != NO_BALL;
	}

	private void updateExtras() {
		Team battingTeam = getBattingTeam();
		getBowler().setConceededRuns(getBowler().getConceededRuns() + 1);
		getBowler().setEconomy((float) getBowler().getConceededRuns() / getBowler().getBallsBowled() * 6);
		battingTeam.setScore(battingTeam.getScore() + 1);
		battingTeam.setExtras(battingTeam.getExtras() + 1);
		battingTeam.setRunRate((float) battingTeam.getScore() / getTotalballsBowled() * 6);
	}

	private int getTotalballsBowled() {
		return (currentOver.getNumberOfOver() - 1) * 6 + ballOfTheOver;
	}

	Team getBattingTeam() {
		return currentInnings.getBattingTeam();
	}

	private void updateScore(int resultOfTheBall) {
		updateBatsman(resultOfTheBall);
		updateBowler(resultOfTheBall);
		updateTeams(resultOfTheBall);
	}

	private void updateTeams(int resultOfTheBall) {
		Team battingTeam = getBattingTeam();
		Team bowlingTeam = getBowlingTeam();
		battingTeam.setScore(battingTeam.getScore() + resultOfTheBall);
		if (isFirstInnings) {
			battingTeam.setRunRate(((float) battingTeam.getScore() / getTotalballsBowled()) * 6);
			battingTeam.setProjectedScore((int) battingTeam.getRunRate() * totalOvers);
		} else {
			battingTeam.setRunRate((((float) bowlingTeam.getScore() - battingTeam.getScore()+1)
					/ (totalOvers * 6 - getTotalballsBowled())) * 6);
		}
		System.out.println("RunRate : "+battingTeam.getRunRate());
	}

	Team getBowlingTeam() {
		return currentInnings.getBowlingTeam();
	}

	private void updateBowler(int resultOfTheBall) {
		getBowler().setBallsBowled(getBowler().getBallsBowled() + 1);
		getBowler().setConceededRuns(getBowler().getConceededRuns() + resultOfTheBall);
		getBowler().setEconomy((float) getBowler().getConceededRuns() / getBowler().getBallsBowled() * 6);
	}

	private void updateBatsman(int resultOfTheBall) {
		getStriker().setBallsFaced(getStriker().getBallsFaced() + 1);
		getStriker().setRuns(getStriker().getRuns() + resultOfTheBall);
		if (resultOfTheBall == 4) {
			getStriker().setFours(getStriker().getFours() + 1);
		} else if (resultOfTheBall == 6) {
			getStriker().setSixes(getStriker().getSixes() + 1);
		}
		getStriker().setStrikeRate((float) getStriker().getRuns() / getStriker().getBallsFaced() * 100);
	}

	private void rotateStrike() {
		Player temp = getStriker();
		striker = getNonStriker();
		nonStriker = temp;
	}

	public boolean isInningsOver() {
		return isInningsOver;
	}

	public void setInningsOver(boolean isInningsOver) {
		this.isInningsOver = isInningsOver;
	}

	public Player[] getBatsmen() {
		return batsmen;
	}

	public Player[] getBowlers() {
		return bowlers;
	}

	public void selectOpeners() {
		striker = view.selectBatsman();
		nonStriker = view.selectBatsman();
		bowler = view.selectBowler();
	}

	public void setTotalOversOfTheMatch(int overs) {
		totalOvers = overs;
	}

	public void setSecondInnings() {
		currentInnings = secondInnings;
		isFirstInnings = false;
		isInningsOver = false;
		setPlayers();
		currentOver = new Over(1);
	}
	StringBuilder buildLiveScoreboard() {
	    StringBuilder scoreboard = new StringBuilder();
	    scoreboard.append(striker.getName()).append(" - ").append(striker.getRuns()).append("(")
	            .append(striker.getBallsFaced()).append(")\n");
	    scoreboard.append(nonStriker.getName()).append(" - ").append(nonStriker.getRuns()).append("(")
	            .append(nonStriker.getBallsFaced()).append(")\n");
	    scoreboard.append(bowler.getName()).append(" - ").append(bowler.getBallsBowled() / 6).append(".")
	            .append(bowler.getBallsBowled() % 6).append("-").append(bowler.getConceededRuns()).append("-")
	            .append(bowler.getWickets()).append("-").append(bowler.getEconomy()).append("\n");
	    if(isFirstInnings) {
	    	scoreboard.append("CR - ").append(getBattingTeam().getRunRate()).append("\n");
	    	scoreboard.append("Projected Score - ").append(getBattingTeam().getProjectedScore()).append("\n");
	    	scoreboard.append(getBattingTeam().getName()).append(" won the toss and chose to bat first.\n");
	    }
	    else {
	    	scoreboard.append("RR - ").append(getBattingTeam().getRunRate()).append("\n");
	    	scoreboard.append("Target - ").append(getBowlingTeam().getScore()+1).append("\n");
	    	scoreboard.append(getBattingTeam().getName()).append(" needs ").append(getBowlingTeam().getScore()
	    			-getBattingTeam().getScore()+1).append(" runs in ").append(totalOvers*6-getTotalballsBowled())
	    	.append(" balls to win.");
	    }
	    return scoreboard;
	}

	StringBuilder buildInningsScoreboard() {
		return buildInningsScoreboard(currentInnings, false);
	}
	
	private StringBuilder buildInningsScoreboard(Innings innings, boolean isMatchResult) {
	    StringBuilder scoreboard = new StringBuilder();
	    Team battingTeam = innings.getBattingTeam();
	    scoreboard.append("Batting Team: ").append(battingTeam.getName()).append("\n");
	    scoreboard.append("Score: ").append(battingTeam.getScore()).append("\n");
	    scoreboard.append("Wickets: ").append(battingTeam.getWickets()).append("\n");
	    if(!isMatchResult) {
	    	scoreboard.append("Run-Rate: ").append(battingTeam.getRunRate()).append("\n\n");
	    }
	    scoreboard.append("Batsmen:\n");
	    for (Player batsman : battingTeam.getPlayers()) {
	        if (batsman.isBatted()) {
	            scoreboard.append("Name: ").append(batsman.getName()).append("\n");
	            scoreboard.append("Runs: ").append(batsman.getRuns()).append("\n");
	            scoreboard.append("Balls Faced: ").append(batsman.getBallsFaced()).append("\n");
	            scoreboard.append("Fours: ").append(batsman.getFours()).append("\n");
	            scoreboard.append("Sixes: ").append(batsman.getSixes()).append("\n");
	            scoreboard.append("Strike Rate: ").append(batsman.getStrikeRate()).append("\n\n");
	        }
	    }
	    scoreboard.append("Bowlers:\n");
	    Team bowlingTeam = innings.getBowlingTeam();
	    for (Player bowler : bowlingTeam.getPlayers()) {
	        if (bowler.getBallsBowled() > 0) {
	            scoreboard.append("Name: ").append(bowler.getName()).append("\n");
	            scoreboard.append("Balls Bowled: ").append(bowler.getBallsBowled()).append("\n");
	            scoreboard.append("Runs Conceeded: ").append(bowler.getConceededRuns()).append("\n");
	            scoreboard.append("Wickets: ").append(bowler.getWickets()).append("\n");
	            scoreboard.append("Economy: ").append(bowler.getEconomy()).append("\n\n");
	        }
	    }
	    return scoreboard;
	}

	public Player getStriker() {
		return striker;
	}

	public Player getNonStriker() {
		return nonStriker;
	}

	public Player getBowler() {
		return bowler;
	}

	public String batterAndBowlerDetails() {
		return (currentOver.getNumberOfOver()-1)+"."+(ballOfTheOver+1)+" -> "
				+ bowler.getName()+" to "+striker.getName()+": ";
	}

	public StringBuilder matchScoreboard() {
		if(isFirstInnings) {
			return buildInningsScoreboard(currentInnings, false);
		}
		return buildInningsScoreboard(firstInnings, true).append(buildInningsScoreboard(secondInnings, true));
	}
}
