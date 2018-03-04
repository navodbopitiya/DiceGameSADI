/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author Navod Bopitiya
 *
 */
public class GameEngineImpl implements GameEngine {
	
	Collection<Player> allPlayers = new ArrayList<Player>();
	Collection<GameEngineCallback> allGameEngineCallbacks = new ArrayList<GameEngineCallback>();

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub
		
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

}
