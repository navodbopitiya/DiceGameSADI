package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;



import model.ComboBoxPlayer;
import model.SimplePlayer;
import model.interfaces.Player;
import view.Toolbar;

public class ToolbarController {

	private GameController gameController;
	private Toolbar toolbar;

	public ToolbarController(GameController gameController, Toolbar toolbar) {
		this.gameController = gameController;
		this.toolbar = toolbar;
		toolbar.addActionListener(new ButtonListener(toolbar, gameController));
		toolbar.addItemListener(new PlayerItemListener());
		updatePlayers();
	}
	
	public void updatePlayers(){
		Collection<ComboBoxPlayer> comboPlayerList = new ArrayList<ComboBoxPlayer>();
		for(Player player : gameController.getGameEngine().getAllPlayers()){
			comboPlayerList.add(new ComboBoxPlayer(player));
		}
		toolbar.updatePlayers(comboPlayerList);
	}
	
	public void changePlayer(Player player){
		toolbar.changePlayer(player);
	}
	
	private void changeCurrentPlayer(ComboBoxPlayer comboBoxPlayer) {
		Player playerToChange = null;
		for(Player player : gameController.getGameEngine().getAllPlayers()){

			if(player.getPlayerId().equals(comboBoxPlayer.getPlayerId())){
				playerToChange = player;
			}
		}
		gameController.changeCurrentPlayer(playerToChange);
	}
	
	class PlayerItemListener implements ItemListener{
		/*Listener for switch player*/
		@Override
		public void itemStateChanged(ItemEvent event) {
			
			if (event.getStateChange() == ItemEvent.SELECTED) {
				/*If new player selected, change the current player to selected player */
				ComboBoxPlayer testComboPlayer = (ComboBoxPlayer) toolbar.getPlayerBox().getSelectedItem();
				/*Check if player already assigned as current player*/
				if (!gameController.getCurrentPlayer().getPlayerId().equals(testComboPlayer.getPlayerId())) {
					/*If not assign current player*/
					changeCurrentPlayer((ComboBoxPlayer) toolbar.getPlayerBox().getSelectedItem());
				}
			}
		}
		
	}
	
}