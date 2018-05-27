/**
 * 
 */
package model;

import java.awt.Component;

import javax.swing.JOptionPane;

import model.interfaces.DicePair;

/**
 * @author Navod Bopitiya
 *
 */
public class GameHelperMethods {
	/* This class contains helper methods for the game */

	public static String calculateResults(DicePair houseDice, DicePair playerDice) {
		String resultText;

		if (calculateDicePair(houseDice) > calculateDicePair(playerDice)) {
			resultText = GameConstants.HOUSE_WON;
		} else if (calculateDicePair(houseDice) < calculateDicePair(playerDice)) {
			resultText = GameConstants.PLAYER_WON;
		} else {
			resultText = GameConstants.DRAW;
		}
		return resultText;
	}

	public static int calculateDicePair(DicePair dicePair) {
		/*
		 * This method is already there in gameEngineImpl, but I cannot change
		 * it due to assignment specification
		 */
		// returns the sum of the dice pair
		if (dicePair != null) {
			return dicePair.getDice1() + dicePair.getDice2();
		} else {
			return 0;
		}
	}

	public static void showErrorMessage(String errorMessage, Component component) {
		JOptionPane.showMessageDialog(component, errorMessage);
	}

}
