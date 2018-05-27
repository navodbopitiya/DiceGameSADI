/**
 * 
 */
package model;

import model.interfaces.DicePair;

/**
 * @author Navod Bopitiya
 *
 */
public class GameHelperMethods {

	public static String calculateResults(DicePair houseDice, DicePair playerDice){
		String resultText;
		
		if(calculateDicePair(houseDice) > calculateDicePair(playerDice)){
			resultText = GameConstants.HOUSE_WON;
		}else if(calculateDicePair(houseDice) < calculateDicePair(playerDice)){
			resultText = GameConstants.PLAYER_WON;
		}else{
			resultText = GameConstants.DRAW;
		}
		return resultText;
	}
	
	public static int calculateDicePair(DicePair dicePair){
		//returns the sum of the dice pair
		return dicePair.getDice1() + dicePair.getDice2();
	}

	
}
