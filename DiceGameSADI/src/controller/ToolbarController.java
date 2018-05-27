package controller;

import java.util.ArrayList;
import java.util.Collection;



import model.ComboBoxPlayer;
import model.interfaces.Player;
import view.Toolbar;

public class ToolbarController {

	private GameController gameController;
	private Toolbar toolbar;

	public ToolbarController(GameController gameController, Toolbar toolbar) {
		this.gameController = gameController;
		this.toolbar = toolbar;
		toolbar.addActionListener(new ButtonListener(toolbar, gameController));
		toolbar.addItemListener(new PlayerItemListener(gameController, toolbar, this));
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
		ComboBoxPlayer playerToChange = new ComboBoxPlayer(player);
		toolbar.changePlayer(playerToChange);
	}
	
	void changeCurrentPlayer(ComboBoxPlayer comboBoxPlayer) {
		Player playerToChange = null;
		for(Player player : gameController.getGameEngine().getAllPlayers()){

			if(player.getPlayerId().equals(comboBoxPlayer.getPlayerId())){
				playerToChange = player;
			}
		}
		gameController.changeCurrentPlayer(playerToChange);
	}
	
}