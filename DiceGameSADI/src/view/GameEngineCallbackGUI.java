package view;

import javax.swing.SwingUtilities;

import controller.GameController;
import controller.MainPanelController;
import controller.StatusbarController;
import model.GameConstants;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private GameController gameController;
	private MainPanelController mainPanelController;
	private StatusbarController statusBarController;

	public GameEngineCallbackGUI(MainPanelController mainPanelController, StatusbarController statusBarController,
			GameController gameController) {
		this.gameController = gameController;
		this.mainPanelController = mainPanelController;
		this.statusBarController = statusBarController;
	}

	@Override
	public void intermediateResult(Player player, final DicePair dicePair, GameEngine gameEngine) {
		/*If current player is equal to the player being rolled right now, display on screen using a new thread*/
		if (gameController.getCurrentPlayer().equals(player)) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					mainPanelController.displayPlayerDiceResults(dicePair);
					statusBarController.setAction("Rolling player");
				}
			});
		}

	}

	@Override
	public void result(Player player, final DicePair result, GameEngine gameEngine) {
		/*Display current user result*/
		if (gameController.getCurrentPlayer().equals(player)) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					mainPanelController.displayPlayerDiceResults(result);

				}
			});

		}
	}

	@Override
	public void intermediateHouseResult(final DicePair dicePair, GameEngine gameEngine) {
		/*Display house intermediate results for all players*/

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainPanelController.displayPlayerDiceResults(gameController.getCurrentPlayer().getRollResult());
				mainPanelController.displayHouseDiceResults(dicePair);
				statusBarController.setAction("House rolling");

			}
		});

	}

	@Override
	public void houseResult(final DicePair result, GameEngine gameEngine) {
		/*Display final house result for all players*/
		gameController.setHouseResult(result);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				mainPanelController.displayPlayerDiceResults(gameController.getCurrentPlayer().getRollResult());
				mainPanelController.displayHouseDiceResults(result);
				statusBarController.updateCurrentPlayerDetails();
				statusBarController.updateGameDetails();
				mainPanelController.updatePlayerResults();
				statusBarController.setAction("House roll finished");

			}
		});

	}

}
