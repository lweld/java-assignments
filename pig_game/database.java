/* 
 * This class inserts the performance of the human players and the computer in 
 * the game, Pig, into the tables, pig_game_winners and pig_game_player_stats, 
 * in the database, fun.
 * 
 * Author: Liam Weld
 */

package pigGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class database {
	
	static String dbDriver;
	static String dbUrl;
	static String dbUsername;
	static String dbPass;
	static int gameID;
	
	public database() {
		dbDriver = "com.mysql.jdbc.Driver";
		dbUrl = "ADD_YOUR_DATABASE_URL";
		dbUsername = "ADD_YOUR_DATABASE_USERNAME";
		dbPass = "ADD_YOUR_DATABASE_PASSWORD";
	}
	
	// Create a sql date object to add to each row of data
	private static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
	// If a human player won, inserts them into the winners table
	public static void insertPlayer(String name, int winner_score, float avg_num_turns_per_round, long avg_turn_delta_millisec) {
		try {
			// creating a mysql database connection
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPass);

	        // mysql insert statement
	        String query = " insert into pig_game_winners (date_played, name_of_winner, winner_score, avg_num_turns_per_round, "
	        		+ "avg_turn_delta_millisec)" + " values (?, ?, ?, ?, ?)";
	        
	        PreparedStatement preparedStmt = conn.prepareStatement(query); // create the mysql insert preparedstatement
	        preparedStmt.setTimestamp(1, getCurrentTimeStamp());
	        preparedStmt.setString (2, String.valueOf(name));
	        preparedStmt.setInt    (3, winner_score);
	        preparedStmt.setFloat  (4, avg_num_turns_per_round);
	        preparedStmt.setLong   (5, avg_turn_delta_millisec);
	        preparedStmt.execute(); // execute the preparedstatement
	        conn.close(); // close the database connection
	    } catch (Exception e) {
	        System.err.println("\nGot an exception!\n" + e.getMessage());
	    }
	}
	
	// If the computer won, inserts it into the winners table
	public static void insertComp(String name, int winner_score, float avg_num_turns_per_round) {
		try {
	        // creating a mysql database connection
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPass);

	        // mysql insert statement
	        String query = " insert into pig_game_winners (date_played, name_of_winner, winner_score, avg_num_turns_per_round)" 
	        				+ " values (?, ?, ?, ?)";
	        
	        PreparedStatement preparedStmt = conn.prepareStatement(query); // create the mysql insert preparedstatement
	        preparedStmt.setTimestamp(1, getCurrentTimeStamp());
	        preparedStmt.setString (2, String.valueOf(name));
	        preparedStmt.setInt    (3, winner_score);
	        preparedStmt.setFloat  (4, avg_num_turns_per_round);
	        preparedStmt.execute(); // execute the preparedstatement
	        conn.close(); // close the database connection
	    } catch (Exception e) {
	        System.err.println("\nGot an exception!\n" + e.getMessage());
	    }
	}
	
	// For every game, inserts the performance of each human player into a player summary table
	public static void insertPlayerStats(String name, Boolean win, int score, int points_awarded, float avg_num_turns_per_round, long avg_turn_delta_millisec) {
		try {
			// creating a mysql database connection
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPass);
			
	        // mysql insert statement
	        String query = " insert into pig_game_player_stats (date_played, name, win, score, points_awarded, avg_num_turns_per_round, "
	        		+ "avg_turn_delta_millisec)" + " values (?, ?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement preparedStmt = conn.prepareStatement(query); // create the mysql insert preparedstatement
	        preparedStmt.setTimestamp(1, getCurrentTimeStamp());
	        preparedStmt.setString   (2, String.valueOf(name));
	        preparedStmt.setBoolean  (3, win);
	        preparedStmt.setInt      (4, score);
	        preparedStmt.setInt      (5, points_awarded);
	        preparedStmt.setFloat    (6, avg_num_turns_per_round);
	        preparedStmt.setLong     (7, avg_turn_delta_millisec);
	        preparedStmt.execute(); // execute the preparedstatement
	        conn.close(); // close the database connection
	    } catch (Exception e) {
	        System.err.println("\nGot an exception!\n" + e.getMessage());
	    }
	}
}