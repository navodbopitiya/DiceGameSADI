package controller;

import model.interfaces.Player;
import view.Statusbar;

public class StatusbarController {
	
	private GameController gameController;
	private Statusbar statusBar;
	
	public StatusbarController(GameController gameController, Statusbar statusBar){
		this.gameController = gameController;
		this.statusBar = statusBar;
		updateCurrentPlayerDetails();
		updateGameDetails();
	}
	
	public void updateCurrentPlayerDetails(){
		statusBar.setCurrentBet(gameController.getCurrentPlayer().getBet());
		statusBar.setCurrentBalance(gameController.getCurrentPlayer().getPoints());
	}
	
	public void updateGameDetails(){
		statusBar.setPlayerNumber(gameController.getGameEngine().getAllPlayers().size());
		int totalBets = 0;
		for(Player player : gameController.getGameEngine().getAllPlayers()){
			totalBets += player.getBet();
		}
		statusBar.setTotalBets(totalBets);
	}
	
	public void setAction(String actionText){
		statusBar.setCurrentAction(actionText);
	}
}
