package controller;

import model.interfaces.Player;
import view.Statusbar;

public class StatusbarController {
	
	private GameController gameController;
	private Statusbar statusBar;
	
	public StatusbarController(GameController gameController, Statusbar statusBar){
		this.gameController = gameController;
		this.statusBar = statusBar;
		updateCurrentPlayerDetails(gameController.getCurrentPlayer());
		updateGameDetails();
	}
	
	public void updateCurrentPlayerDetails(Player currentPlayer){
		statusBar.setCurrentBet(currentPlayer.getBet());
		statusBar.setCurrentBalance(currentPlayer.getPoints());
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
