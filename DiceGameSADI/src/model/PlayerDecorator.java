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
public class PlayerDecorator implements Player{
	
	private Player player;
	
	public Player getPlayer(){
		return this.player;
	}
	
	public PlayerDecorator(Player player){
		this.player = player;
	}

	@Override
	public String getPlayerName() {
		return this.player.getPlayerName();
	}

	@Override
	public void setPlayerName(String playerName) {
		this.player.setPlayerName(playerName);
		
	}

	@Override
	public int getPoints() {
		return this.player.getPoints();
	}

	@Override
	public void setPoints(int points) {
		this.player.setPoints(points);
		
	}

	@Override
	public String getPlayerId() {
		return this.player.getPlayerId();
	}

	@Override
	public boolean placeBet(int bet) {
		return this.player.placeBet(bet);
	}

	@Override
	public int getBet() {
		return this.player.getBet();
	}

	@Override
	public DicePair getRollResult() {
		return this.player.getRollResult();
	}

	@Override
	public void setRollResult(DicePair rollResult) {
		this.player.setRollResult(rollResult);
		
	}
	
}
