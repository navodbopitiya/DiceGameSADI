package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.ComboBoxPlayer;
import view.Toolbar;

class PlayerItemListener implements ItemListener{
	
	private GameController gameController;
	private Toolbar toolbar;
	private ToolbarController toolbarController;
	
	/*Listener for switch player*/
	public PlayerItemListener(GameController gameController, Toolbar toolbar, ToolbarController toolbarController) {
		this.gameController = gameController;
		this.toolbar = toolbar;
		this.toolbarController = toolbarController;
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		
		if (event.getStateChange() == ItemEvent.SELECTED) {
			/*If new player selected, change the current player to selected player */
			ComboBoxPlayer testComboPlayer = (ComboBoxPlayer) toolbar.getPlayerBox().getSelectedItem();
			/*Check if player already assigned as current player*/
			if (!gameController.getCurrentPlayer().getPlayerId().equals(testComboPlayer.getPlayerId())) {
				/*If not assign current player*/
				toolbarController.changeCurrentPlayer((ComboBoxPlayer) toolbar.getPlayerBox().getSelectedItem());
			}
		}
	}
	
}
