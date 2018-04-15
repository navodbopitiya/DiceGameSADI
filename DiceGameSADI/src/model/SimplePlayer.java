/**
 * 
 */
package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

/**
 * @author Navod Bopitiya
 *
 */
public class SimplePlayer implements Player {
	private String playerId, playerName;
	private int points, bet;
	private DicePair dicePair;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() {
		// Returns Player Name
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		//Sets Player Name
		this.playerName = playerName;
		
	}

	@Override
	public int getPoints() {
		//Returns total points
		return points;
	}

	@Override
	public void setPoints(int points) {
		//Sets total points
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		//Returns player ID
		return this.playerId;
	}

	@Override
	public boolean placeBet(int bet) {
		//Places bet if player's current points >= bet
		if(this.points < bet){
			return false;
		}else{
			this.bet = bet; //setting the current bet to placed bet
			return true;
		}
	}

	@Override
	public int getBet() {
		//returns bet
		return this.bet;
	}

	@Override
	public DicePair getRollResult() {
		//returns roll result
		return this.dicePair;
	}

	@Override
	public void setRollResult(DicePair rollResult) {
		//sets roll result
		this.dicePair = rollResult;
		
	}
	
	@Override
	public String toString(){
		//Returns a human readable string
		return "ID = "+playerId+", Name = "+playerName+", Points = "+points;
		
	}

}
