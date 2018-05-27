package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

import model.GameConstants;
import model.interfaces.Player;
import view.Toolbar;

public class ToolbarController {

	private GameController gameController;
	private Toolbar toolbar;

	public ToolbarController(GameController gameController, Toolbar toolbar) {
		this.gameController = gameController;
		this.toolbar = toolbar;
		toolbar.addActionListener(new ToolbarListener());
		toolbar.addItemListener(new PlayerItemListener());
		updatePlayers();
	}
	
	private boolean placeBet(int betAmount, Player player) {
		return gameController.placeBet(betAmount, player);
	}
	
	public void updatePlayers(){
		toolbar.updatePlayers(gameController.getGameEngine().getAllPlayers());
	}
	
	public void changePlayer(Player player){
		toolbar.changePlayer(player);
	}
	
	private void changeCurrentPlayer(Player player) {
		System.out.println("YOU ARE CURRENTLY IN TOOLBAR CONTROLLER - CHANGE CURRENT PLAYER");
		gameController.changeCurrentPlayer(player);
	}
	
	class PlayerItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			
			if (event.getStateChange() == ItemEvent.SELECTED) {
				/*If new player selected, change the current player to selected player */
				if (gameController.getCurrentPlayer() != toolbar.getPlayerBox().getSelectedItem()) {
					changeCurrentPlayer((Player) toolbar.getPlayerBox().getSelectedItem());
				}
			}
		}
		
	}
	
	class ToolbarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			String actionCommand = event.getActionCommand();
			
			if(actionCommand.equals(GameConstants.BET_BUTTON_ACTION)){
				
				/*Bet Action*/
				int betAmount;
				if (!toolbar.getBetText().isEmpty()) {
					if (GameConstants.PATTERN.matcher(toolbar.getBetText()).matches()) {
						betAmount = Integer.parseInt(toolbar.getBetText());
						if (betAmount != GameConstants.BET_ZERO) {
							if (gameController.getCurrentPlayer() != null) {
								if (placeBet(betAmount, gameController.getCurrentPlayer())) {
									toolbar.showErrorMessage("Bet placed successfully");
									toolbar.resetBetTextField(); 
									gameController.updateStatusBar();
								} else {
									toolbar.showErrorMessage("Invalid Bet");
								}

							} else {
								toolbar.showErrorMessage("Please select a player first");
							}
						} else {
							toolbar.showErrorMessage("Please enter a value greater than zero");
						}
					} else {
						toolbar.showErrorMessage("Please enter a positive numeric value");
					}
				} else {
					toolbar.showErrorMessage("Please enter a bet first");
				}
				
			}else if(actionCommand.equals(GameConstants.ROLL_BUTTON_ACTION)){
				/*Roll Action*/
				if (gameController.getCurrentPlayer() != null) {
					new Thread() {
						public void run() {
							gameController.roll();
						}
					}.start();
				} else {
					JOptionPane.showMessageDialog(null, "Please select a player first");
				}
			}
		}
		
	}
	

}