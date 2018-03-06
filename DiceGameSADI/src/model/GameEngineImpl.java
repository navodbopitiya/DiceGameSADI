/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author Navod Bopitiya
 *
 */
public class GameEngineImpl implements GameEngine {
	
	private Collection<Player> allPlayers = new ArrayList<Player>();
	private Collection<GameEngineCallback> allGameEngineCallbacks = new ArrayList<GameEngineCallback>();

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub
		int delay = initialDelay;
		while(delay < finalDelay){
			DicePair tempDice = new DicePairImpl(randomNumber(NUM_FACES),randomNumber(NUM_FACES),NUM_FACES);
			delay += delayIncrement; //Increasing delay
			for(GameEngineCallback gameEngineCallback : allGameEngineCallbacks){ //calling intermediateResult on all gameEngineCallbacks
				gameEngineCallback.intermediateResult(player, tempDice, this);
			}
			player.setRollResult(tempDice); // Sets player roll result
			delayDiceRoll(delay);
			
		}
		
		for(GameEngineCallback gameEngineCallback : allGameEngineCallbacks){ //calling result on all gameEngineCallbacks
			gameEngineCallback.result(player, player.getRollResult(), this);
		}
		
		
	}

	private void delayDiceRoll(int delay) {
		try{ //Puts thread to sleep, dice rolling
			Thread.sleep(delay);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub
		int delay = initialDelay;
		DicePair houseDice = null;
		while(delay < finalDelay){
			DicePair tempDice = new DicePairImpl(randomNumber(NUM_FACES),randomNumber(NUM_FACES),NUM_FACES);
			delay += delayIncrement;
			for(GameEngineCallback gameEngineCallback : allGameEngineCallbacks){ //calling intermediateHouseResult on all gameEngineCallbacks
				gameEngineCallback.intermediateHouseResult(tempDice, this);
			}
			houseDice = tempDice;
			delayDiceRoll(delay);
			
		}
		for(GameEngineCallback gameEngineCallback : allGameEngineCallbacks){ //calling result on all gameEngineCallbacks
			gameEngineCallback.houseResult(houseDice, this);
		}
		
		calculateResults(houseDice);
		
		
		
	}

	private void calculateResults(DicePair houseDice) {
		//Calculate winner
		for(Player checkPlayer : allPlayers){
			if(checkPlayer.getBet() != 0){
				//Player has a bet
				//Check player's dice against house's dice
				if(calculateDicePair(checkPlayer.getRollResult()) < calculateDicePair(houseDice)){
					//If player's dice has a value less than the house
					//Remove bet from current points of the player
					checkPlayer.setPoints(checkPlayer.getPoints() - checkPlayer.getBet());
					//Reset player's bet to zero
					checkPlayer.placeBet(0);
				}else if(calculateDicePair(checkPlayer.getRollResult()) > calculateDicePair(houseDice)){
					//If player's dice has a value more than the house
					//Add bet to the current points of the player
					checkPlayer.setPoints(checkPlayer.getPoints() + checkPlayer.getBet());
					//Reset player's bet to zero
					checkPlayer.placeBet(0);
				}else{
					//If draw, players bets are returned - TODO
					checkPlayer.placeBet(0);
				}
			}
		}
		
		for(GameEngineCallback gameEngineCallback : allGameEngineCallbacks){
			gameEngineCallback.houseResult(houseDice, this);
		}
	}
	
	public int calculateDicePair(DicePair dicePair){
		return dicePair.getDice1() + dicePair.getDice2();
	}

	@Override
	public void addPlayer(Player player) {
		allPlayers.add(player); //Add player to allPlayers ArrayList
		
	}

	@Override
	public Player getPlayer(String id) {
		Player checkPlayer = null;
		for(Player player : allPlayers){ // Iterate through the Array List
			if(player.getPlayerId().equals(id)){ //Check if player ID matches the ID of the player we want to get
				checkPlayer = player; //If true, set checkPlayer to the player we got
			}
		}
		return checkPlayer; //return the player if found, or return null
	}

	@Override
	public boolean removePlayer(Player player) {
		return allPlayers.remove(player); //return false if no player found, true if player removed
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		allGameEngineCallbacks.add(gameEngineCallback); //Add gameEngineCallback to ArrayList allGameEngineCallbacks
		
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return allGameEngineCallbacks.remove(gameEngineCallback); //Remove gameEngineCallback from ArrayList allGameEngineCallbacks
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return allPlayers;
	}
	
	public int randomNumber(int num_faces){
		Random rn = new Random();
		return rn.nextInt(num_faces)+1;
		
	}

}
