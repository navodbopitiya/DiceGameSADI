package controller;

import model.GameHelperMethods;
import model.interfaces.DicePair;
import model.interfaces.Player;
import view.MainPanel;

public class MainPanelController {

	private GameController gameController;
	private MainPanel mainPanel;

	public MainPanelController(GameController gameController, MainPanel mainPanel) {
		this.setGameController(gameController);
		this.mainPanel = mainPanel;
		changePlayer(gameController.getCurrentPlayer());

	}

	public void changePlayer(Player player) {
		if (player.getRollResult() == null) {
			/* When player doesn't have a roll result */
			mainPanel.changePlayer(player);
		} else if (gameController.getHouseResult() != null) {
			/*
			 * When player has a roll result, calculate and send result using
			 * stored house dice result
			 */
			mainPanel.changePlayer(player, GameHelperMethods.calculateResults(gameController.getHouseResult(),
					gameController.getCurrentPlayer().getRollResult()));
		}

	}

	public void displayPlayerDiceResults(DicePair dicePair) {
		mainPanel.displayPlayerDiceResults(dicePair);
	}

	public void displayHouseDiceResults(DicePair dicePair) {
		mainPanel.displayHouseDiceResults(dicePair);
		mainPanel.displayResultBar(
				GameHelperMethods.calculateResults(dicePair, gameController.getCurrentPlayer().getRollResult()),
				gameController.getCurrentPlayer().getPlayerName());
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public void updatePlayerResults() {
		mainPanel.updatePlayerResults(gameController.getCurrentPlayer());
	}
}
