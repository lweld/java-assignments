/* 
 * This is a class to create human players used the game, Pig.
 * 
 * Author: Liam Weld
 */

package pigGame;

public class player {

	int k;
	int turnSum;
	int totalSum;
	float i;
	long timeDelta = 0L;
	String player_name;
	
	// Instantiate the class by supplying the player's name
	public player(String name) {
		player_name = name;
	}
	
	public String getName() {
		return player_name;
	}
	
	public int getTurnSum() {
		return turnSum;
	}
	
	public int getTotalSum() {
		return totalSum;
	}
	
	public float getNumTurns() {
		return i;
	}
	
	public long getTimeDelta() {
		return timeDelta;
	}
	
	public void addTurnSum(int turn) {
		turnSum += turn;
	}
	
	public void addTotalSum(int turnSum) {
		totalSum += turnSum;
	}
	
	public float numTurnsCounter() {
		return i++;
	}
	
	public int playerDecidesCounter() {
		return k++;
	}
	
	public long startTime() {
		return System.currentTimeMillis();
	}

	public void sumTimeDeltas(long startTime) {
		timeDelta += System.currentTimeMillis() - startTime;
	}
	
	public void eraseTurnSum() {
		turnSum = 0;
	}
	
	public void eraseTotalSum() {
		totalSum = 0;
	}
	
	public void eraseNumTurnsCounter() {
		i = 0f;
	}
	
	public void erasePlayerDecidesCounter() {
		k = 0;
	}
}