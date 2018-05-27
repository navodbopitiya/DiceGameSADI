/**
 * 
 */
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.GameConstants;
import model.GameHelperMethods;
import model.interfaces.Player;
import view.Menubar;
import view.Toolbar;

/**
 * @author Navod Bopitiya
 *
 */
public class ButtonListener implements ActionListener{
	private Toolbar toolbar = null;
	private GameController gameController = null;
	private Menubar menuBar = null;
	
	public ButtonListener(Toolbar toolbar, GameController gameController){
		this.toolbar = toolbar;
		this.gameController = gameController;
	}
	
	public ButtonListener(GameController gameController, Menubar menuBar) {
		this.gameController = gameController;
		this.menuBar = menuBar;
	}

	private boolean placeBet(int betAmount, Player player) {
		return gameController.placeBet(betAmount, player);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		
		if(actionCommand.equals(GameConstants.BET_BUTTON_ACTION)){
			/*Bet Action - Toolbar*/
			String betString = toolbar.getBetText();
			betAction(betString,toolbar);
			
		}else if(actionCommand.equals(GameConstants.ROLL_BUTTON_ACTION)){
			/*Roll Action - Toolbar
			 * run on a new thread*/
			if (gameController.getCurrentPlayer() != null) {
				new Thread() {
					public void run() {
						gameController.roll();
					}
				}.start();
			} else {
				JOptionPane.showMessageDialog(null, "Please select a player first");
			}
		}else if(actionCommand.equals(GameConstants.ADD_PLAYER_ACTION)){
			/* Add Player Item - Menubar*/
			gameController.switchToMainMenu();
			
		}else if (actionCommand.equals(GameConstants.BET_BUTTON_MENU_ACTION)) {
			/*Bet Item Menu Action - Menubar*/
			String betString = menuBar.showBetDialog();
			betAction(betString,menuBar);
		}
	}

	private void betAction(String betString,Component component) {
		int betAmount;
		if (!betString.isEmpty()) {
			if (GameConstants.PATTERN.matcher(betString).matches()) {
				betAmount = Integer.parseInt(betString);
				if (betAmount != GameConstants.BET_ZERO) {
					if (gameController.getCurrentPlayer() != null) {
						if (placeBet(betAmount, gameController.getCurrentPlayer())) {
							GameHelperMethods.showErrorMessage("Bet placed successfully", component);
							gameController.updateStatusBar();
						} else {
							GameHelperMethods.showErrorMessage("Invalid Bet",component);
						}

					} else {
						GameHelperMethods.showErrorMessage("Please select a player first",component);
					}
				} else {
					GameHelperMethods.showErrorMessage("Please enter a value greater than zero",component);
				}
			} else {
				GameHelperMethods.showErrorMessage("Please enter a positive numeric value",component);
			}
		} else {
			GameHelperMethods.showErrorMessage("Please enter a bet first",component);
		}
	}
	
}
