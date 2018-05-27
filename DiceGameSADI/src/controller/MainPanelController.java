package controller;

import model.interfaces.DicePair;
import model.interfaces.Player;
import view.MainPanel;

public class MainPanelController {
	
	private GameController gameController;
	private MainPanel mainPanel;
	
	public MainPanelController(GameController gameController, MainPanel mainPanel)
	{
		this.setGameController(gameController);
		this.mainPanel = mainPanel;
		
	}
	
	public void changePlayer(Player player){
		mainPanel.changePlayer(player);
	}
	
	
	public void displayDiceResults(DicePair dicePair){
		mainPanel.displayDiceResults(dicePair);
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
}
