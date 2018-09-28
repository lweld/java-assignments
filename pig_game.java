/*
 * This is a game of pig between a human player and a computer.
 * The human rolls first and the first player to 100 points wins.
 * 
 * Author: Liam Weld
 */

import java.util.Random;
import java.util.Scanner;

public class pig_game {
	
	static Random generator = new Random(System.currentTimeMillis());
	static Scanner screen = new Scanner(System.in);
	
	// This method uses the result of the two dice rolls to take an action for the human
	public static void humanBank(int humanTurnScore, int humanTotalScore, int computerTurnScore, int computerTotalScore, int counter) {
		int[] turn = turnOutcome("Player");
		// When two sixes are rolled
		if (turn[1] == 0) {
			System.out.println("Ouch! Player's total sum was set to zero and their turn is over!");
			humanTurnScore = 0;
			humanTotalScore = 0;
			transitionRoles("Player", humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// When one six is rolled
		else if (turn[1] == 1) {
			System.out.println("Player's turn sum is zero and their turn is over! :(");
			humanTurnScore = 0;
			transitionRoles("Player", humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// When both dice roll the same number, except for six
		else if (turn[1] == 2) {
			humanTurnScore = humanTurnScore + turn[0];
			System.out.println("Player's turn sum is: " + humanTurnScore + " and total sum would be: " + (humanTurnScore + humanTotalScore));
			humanBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// Dice roll has no special conditions
		else {
			humanTurnScore += turn[0];
			System.out.println("Player's turn sum is: " + humanTurnScore + " and total sum would be: " + (humanTurnScore + humanTotalScore));
			if (humanTurnScore + humanTotalScore >= 100)
				gameOver((humanTurnScore + humanTotalScore), computerTotalScore);
			humanPlayAgain(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
	}
	
	// This method uses the result of the two dice rolls to take an action for the computer
	public static void computerBank(int humanTurnScore, int humanTotalScore, int computerTurnScore, int computerTotalScore, int counter) {
		int[] turn = turnOutcome("Computer");
		// When two sixes are rolled
		if (turn[1] == 0) {
			System.out.println("Ouch! Computer's total score was set to zero and their turn is over!");
			computerTurnScore = 0;
			computerTotalScore = 0;
			transitionRoles("Computer", humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// When one six is rolled
		else if (turn[1] == 1) {
			System.out.println("Computer's turn sum is zero and their turn is over! :(");
			computerTurnScore = 0;
			transitionRoles("Computer", humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// When both dice roll the same number, except for six
		else if (turn[1] == 2) {
			computerTurnScore += turn[0];
			System.out.println("Computer's turn sum is: " + computerTurnScore + " and total sum would be: " + (computerTurnScore + computerTotalScore));
			computerBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		else {
			computerTurnScore += turn[0];
			System.out.println("Computer's turn sum is: " + computerTurnScore + " and total sum would be: " + (computerTurnScore + computerTotalScore));
			computerStrategy(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
	}
	
	// This method determines if the human wants to roll again
	public static void humanPlayAgain(int humanTurnScore, int humanTotalScore, int computerTurnScore, int computerTotalScore, int counter) {
		char userResponse;
		System.out.print("Would you like to roll again? Enter Y for yes, N for no: ");
		userResponse = screen.next().charAt(0);
		switch (userResponse) {
			case 'Y': case 'y':
				humanBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
			case 'N': case 'n':
				humanTotalScore += humanTurnScore;
				humanTurnScore = 0;
				transitionRoles("Player", humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
			default:
				System.out.println("\"" + userResponse + "\" is not valid.");
				humanPlayAgain(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
	}
	
	// This method determines the computer's rolling strategy
	public static void computerStrategy(int humanTurnScore, int humanTotalScore, int computerTurnScore, int computerTotalScore, int counter) {
		int computerTotal = computerTurnScore + computerTotalScore;
		// If the human has a major lead, play aggressive
		if (humanTotalScore >= (computerTotal * 3) && computerTurnScore < 30) {
			computerBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// If the computer has a major lead, play safe
		if (computerTotal >= (humanTotalScore * 3) && computerTurnScore >=10 ) {
			computerTotalScore += computerTurnScore;
			computerTurnScore = 0;
			transitionRoles("Computer", humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// Normal strategy
		else if (computerTurnScore <= 15 && (computerTurnScore + computerTotalScore) < 100) {
			computerBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		// Transition to human
		else {
			computerTotalScore += computerTurnScore;
			computerTurnScore = 0;
			transitionRoles("Computer", humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
	}
	
	// This method changes the turn between the human and computer
	public static void transitionRoles(String player, int humanTurnScore, int humanTotalScore, int computerTurnScore, int computerTotalScore, int counter) {
		// First check if either player won
		gameOver(humanTotalScore, computerTotalScore);
		// Counter is used in the first if statement to calculate the round number
		counter++;
		System.out.println("\nPlayer's sum is: " + humanTotalScore + ", Computer's sum is: " + computerTotalScore + ".");
		if (counter % 2 == 0) {
			int round = (counter / 2) + 1;
			System.out.println("Round " + round + " is begining.");
		}
		if (player.equals("Player")) {
			System.out.println("\nComputer's turn:");
			computerBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
		else {
			System.out.println("\nPlayer's turn: ");
			humanBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
		}
	}
	
	// This method determines the result of the two dice rolls
	public static int[] turnOutcome(String player) {
		int turnScore = 0;
		// Calls dice roll method
		int[] rollResults = roll();
		System.out.println(player + " rolled " + rollResults[0] + " and " + rollResults[1]);
		// For every turn, an array is passed to the respective bank. Index 0 of the array 
		// is the turn score, index 1 indicates which of the four types of turns it was
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
	
	// This method rolls two dice
	public static int[] roll() {
		int rollOne = generator.nextInt(6) + 1;
		int rollTwo = generator.nextInt(6) + 1;
		int[] score = {rollOne, rollTwo};
		return score;
	}
	
	// This statement ends the game if a winner has been found
	public static void gameOver(int humanTotalScore, int computerTotalScore) {
		if (humanTotalScore >= 100) {
			System.out.print("\nCongratulations, Player! You won with a score of " + humanTotalScore + "!!");
			System.exit(0);
		}
		else if (computerTotalScore >= 100) {
			System.out.print("\nThe Computer won with a score of " + computerTotalScore + ". Better luck next time Player.");
			System.exit(0);
		}
		else
			return;
	}

	// main method
	public static void main(String[] args) {
		System.out.println("Welcome to Pig! First user to score 100 wins.\nRound 1 is begining.\n");
		int humanTurnScore = 0;
		int humanTotalScore = 0;
		int computerTurnScore = 0;
		int computerTotalScore = 0;
		// counter is used to calculate the current round
		int counter = 0;
		humanBank(humanTurnScore, humanTotalScore, computerTurnScore, computerTotalScore, counter);
	}
}