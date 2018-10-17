/* 
 * This is a game of pig between a human player and a computer.
 * The human rolls first and the first player to 100 points wins.
 * This game of pig places an emphasis on OOP concepts.
 * 
 * Author: Liam Weld
 */

package pigGame;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class pig_game {
	
	static Random generator = new Random(System.currentTimeMillis());
	static Scanner screen = new Scanner(System.in);
	static database db = new database();
	static player nameOfPlayer;
	static char userResponse;
	static int numPlayers;
	static int computerTotalScore;
	static int computerTurnScore;
	static int round = 1;
	static int gameCount;
	static int points;
	static float computerNumTurns;
	static long startTimeDelta;
	
	// Gets the number of human players
	public static void getNumPlayers() {
		do {
			try {
				System.out.print("Enter the number of players (max of 5): ");
				numPlayers = screen.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter an integer between 1 and 5 inclusive.");
			}
			screen.nextLine();
		} while (numPlayers <= 0 || numPlayers > 5);
		getPlayers(numPlayers);
	}
	
	// Instantiates each player by asking for their name
	public static void getPlayers(int numPlayers) {
		String name;
		player[] playerName = new player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("Enter Player " + i + "'s name: ");
			name = screen.nextLine();
			playerName[i] = new player(name);
		}
		playersRoll(playerName, numPlayers);
	}
	
	// Every round this method allows the human players to roll the dice and check if they've won
	// This method executes much of the game
	public static void playersRoll(player[] playerName, int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			humanBank(playerName, playerName[i], numPlayers);
			gameOver(playerName, playerName[i], numPlayers);
		}
		computerBank(playerName, playerName[0], numPlayers); // Computer rolls
	}
	
	// Uses the result of the two dice rolls to take an action for the human
	public static void humanBank(player[] playerName, player nameOfPlayer, int numPlayers) {
		nameOfPlayer.numTurnsCounter(); // Increments the number of turns this player made this game
		int[] turn = turnOutcome(nameOfPlayer.getName());
		// When two sixes are rolled
		if (turn[1] == 0) {
			System.out.println(nameOfPlayer.getName() + "'s total sum was set to zero and their turn is over! Ouch!");
			nameOfPlayer.eraseTurnSum();
			nameOfPlayer.eraseTotalSum();
			return;
		}
		// When one six is rolled
		else if (turn[1] == 1) {
			System.out.println(nameOfPlayer.getName() + "'s turn sum is zero and their turn is over! :(");
			nameOfPlayer.eraseTurnSum();
			return;
		}
		// When both dice roll the same number, except for six
		else if (turn[1] == 2) {
			nameOfPlayer.addTurnSum(turn[0]);
			System.out.println(nameOfPlayer.getName() + "'s turn sum is: " + nameOfPlayer.getTurnSum() + " and total sum would be: " + (nameOfPlayer.getTurnSum() + nameOfPlayer.getTotalSum()));
			humanBank(playerName, nameOfPlayer, numPlayers);
		}
		// When the dice roll has no special conditions
		else {
			nameOfPlayer.addTurnSum(turn[0]);
			System.out.println(nameOfPlayer.getName() + "'s turn sum is: " + nameOfPlayer.getTurnSum() + " and total sum would be: " + (nameOfPlayer.getTurnSum() + nameOfPlayer.getTotalSum()));
			if (nameOfPlayer.getTurnSum() + nameOfPlayer.getTotalSum() >= 100) {
				nameOfPlayer.addTotalSum(nameOfPlayer.getTurnSum());
				nameOfPlayer.eraseTurnSum();
				gameOver(playerName, nameOfPlayer, numPlayers);
			}
			humanPlayAgain(playerName, nameOfPlayer, numPlayers);
		}
	}

	// Uses the result of the two dice rolls to take an action for the computer
	public static void computerBank(player[] playerName, player nameOfPlayer, int numPlayers) {
		computerNumTurns++;
		int[] turn = turnOutcome("Computer");
		// When two sixes are rolled
		if (turn[1] == 0) {
			System.out.println("Ouch! Computer's total score was set to zero and their turn is over!");
			computerTurnScore = 0;
			computerTotalScore = 0;
			changeRounds(playerName, nameOfPlayer, numPlayers);
		}
		// When one six is rolled
		else if (turn[1] == 1) {
			System.out.println("Computer's turn sum is zero and their turn is over! :(");
			computerTurnScore = 0;
			changeRounds(playerName, nameOfPlayer, numPlayers);
		}
		// When both dice roll the same number, except for six
		else if (turn[1] == 2) {
			computerTurnScore += turn[0];
			System.out.println("Computer's turn sum is: " + computerTurnScore + " and total sum would be: " + (computerTurnScore + computerTotalScore));
			computerBank(playerName, nameOfPlayer, numPlayers);
		}
		// When the dice roll has no special conditions
		else {
			computerTurnScore += turn[0];
			System.out.println("Computer's turn sum is: " + computerTurnScore + " and total sum would be: " + (computerTurnScore + computerTotalScore));
			computerStrategy(playerName, nameOfPlayer, numPlayers);
		}
	}
	
	// Determines the result of the two dice rolls.
	// For every turn, an array is passed to the respective bank. Index 0 of the array is 
	// the turn score, index 1 indicates which of the four types of turn outcomes it was.
	public static int[] turnOutcome(String user) {
		int turnScore = 0;
		int[] rollResults = roll(); // Calls dice roll method
		System.out.println(user + " rolled " + rollResults[0] + " and " + rollResults[1]);
		if (rollResults[0] == 6 && rollResults[1] == 6) {
			int[] outcome = {0, 0};
			return outcome;
		}
		else if (rollResults[0] == 6 || rollResults[1] == 6) {
			int[] outcome = {0, 1};
			return outcome;
		}
		else if (rollResults[0] == rollResults[1]) {
			turnScore += (rollResults[0] + rollResults[1]) * 2;
			int[] outcome = {turnScore, 2};
			return outcome;
		}
		else {
			turnScore += rollResults[0] + rollResults[1];
			int[] outcome = {turnScore, 3};
			return outcome;
		}
	}
	
	// Rolls two dice
	public static int[] roll() {
		int rollOne = generator.nextInt(6) + 1;
		int rollTwo = generator.nextInt(6) + 1;
		int[] score = {rollOne, rollTwo};
		return score;
	}

	// Determines if the human wants to roll again
	public static void humanPlayAgain(player[] playerName, player nameOfPlayer, int numPlayers) {
		nameOfPlayer.playerDecidesCounter();
		System.out.print(nameOfPlayer.getName() + ", would you like to roll again? Enter Y for yes, N for no: ");
		startTimeDelta = nameOfPlayer.startTime(); // Begins timing how long it takes player to decide to roll again
		userResponse = screen.next().charAt(0);
		nameOfPlayer.sumTimeDeltas(startTimeDelta); // Ends timing
		switch (userResponse) {
			case 'Y': case 'y':
				humanBank(playerName, nameOfPlayer, numPlayers);
			case 'N': case 'n':
				nameOfPlayer.addTotalSum(nameOfPlayer.getTurnSum());
				nameOfPlayer.eraseTurnSum();
				return;
			default:
				System.out.println("\"" + userResponse + "\" is not valid.");
				humanPlayAgain(playerName, nameOfPlayer, numPlayers);
		}
	}

	// Computer's dice rolling strategy
	public static void computerStrategy(player[] playerName, player nameOfPlayer, int numPlayers) {
		if (computerTurnScore <= 14 && (computerTurnScore + computerTotalScore) < 100)
			computerBank(playerName, nameOfPlayer, numPlayers);
		else {
			computerTotalScore += computerTurnScore;
			computerTurnScore = 0;
			changeRounds(playerName, nameOfPlayer, numPlayers);
		}
	}

	// Changes the turn between the human and computer
	public static void changeRounds(player[] playerName, player nameOfPlayer, int numPlayers) {
		gameOver(playerName, nameOfPlayer, numPlayers); // Check if anyone won
		round++;
		for (int i = 0; i < numPlayers; i++)
			System.out.print("\n" + playerName[i].getName() + "'s sum is: " + playerName[i].getTotalSum() + ", ");
		System.out.println("\nComputer's sum is: " + computerTotalScore + ".");
		System.out.println("\nRound " + round + " is begining.\n");
		playersRoll(playerName, numPlayers);
	}
	
	// Ends the game if a winner has been found and writes the results to a database
	public static void gameOver(player[] playerName, player nameOfPlayer, int numPlayers) {
		if (nameOfPlayer.getTotalSum() >= 100) {
			System.out.println("\nCongratulations, " + nameOfPlayer.getName() + "! You won with a score of " + nameOfPlayer.getTotalSum() + "!!");
			database.insertPlayer(nameOfPlayer.getName(), nameOfPlayer.getTotalSum(), nameOfPlayer.numTurnsCounter()/round, nameOfPlayer.getTimeDelta()/nameOfPlayer.playerDecidesCounter());
			addPlayerStats(playerName, nameOfPlayer, numPlayers);
			playAgain(playerName, numPlayers);
		}
		else if (computerTotalScore >= 100) {
			System.out.println("\nThe Computer won with a score of " + computerTotalScore + ".");
			database.insertComp("Computer", computerTotalScore, computerNumTurns/round);
			addPlayerStats(playerName, nameOfPlayer, numPlayers);
			playAgain(playerName, numPlayers);
		}
		else
			return;
	}
	
	// Inserts the performance of each human player into a player summary table
	public static void addPlayerStats(player[] playerName, player nameOfPlayer, int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			Boolean win = didWin(playerName[i]);
			points = pointsAwarded(playerName[i], nameOfPlayer, win, numPlayers);
			database.insertPlayerStats(playerName[i].getName(), win, playerName[i].getTotalSum(), points, playerName[i].numTurnsCounter()/round, playerName[i].getTimeDelta()/playerName[i].playerDecidesCounter());
		}
	}
	
	// Checks if a human player won
	public static Boolean didWin(player playerName) {
		if (playerName.getTotalSum() >= 100)
			return true;
		else
			return false;
	}
	
	// Awards a human player points if they won the game. Takes into account the number of opponents the winner defeated.
	public static int pointsAwarded(player playerName, player nameOfPlayer, Boolean ifWon, int numPlayers) {
		// First checks if any of the human players won
		if (ifWon) {
			// Assuming a human player won, awards the winner points
			if (playerName.equals(nameOfPlayer)) {
				if (numPlayers == 1)
					return 10;
				else if (numPlayers == 2)
					return 15;
				else if (numPlayers == 3)
					return 20;
				else if (numPlayers == 4)
					return 25;
				else
					return 30;
			} else {
				return 0;
			}
		} else
			return 0;
	}
	
	// Asks the user if they want to play again
	public static void playAgain(player[] playerName, int numPlayers) {
		System.out.print("\n\nPlay again? Enter Y for yes, N for no: ");
		userResponse = screen.next().charAt(0);
		switch (userResponse) {
			case 'Y': case 'y':
				gameCount++;
				for (int i = 0; i < numPlayers; i++) {
					playerName[i].eraseTotalSum();
					playerName[i].eraseNumTurnsCounter();
					playerName[i].erasePlayerDecidesCounter();
				}
				computerTotalScore = 0;
				round = 1;
				computerNumTurns = 0f;
				System.out.println("\nLet's get playing!\nRound 1 of Game " + gameCount + " is begining.\n");
				playersRoll(playerName, numPlayers);
			case 'N': case 'n':
				System.exit(0);
			default:
				System.out.println("\"" + userResponse + "\" is not valid.");
				playAgain(playerName, numPlayers);
		}
	}
	
	// Starts the game
	public static void main(String[] args) {
		gameCount++;
		System.out.println("Welcome to Pig! First player to score 100 wins.\nRound 1 of Game " + gameCount + " is begining.\n");
		getNumPlayers();
	}
}
