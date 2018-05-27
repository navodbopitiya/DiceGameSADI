package view;

import javax.swing.SwingUtilities;

import controller.MainPanelController;
import controller.StatusbarController;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private MainPanelController mainPanelController;
	private StatusbarController statusBarController;

	public GameEngineCallbackGUI(MainPanelController mainPanelController, StatusbarController statusBarController) {
		this.mainPanelController = mainPanelController;
		this.statusBarController = statusBarController;
	}

	@Override
	public void intermediateResult(Player player, final DicePair dicePair, GameEngine gameEngine) {

		if (mainPanelController.getGameController().getCurrentPlayer().equals(player)) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					mainPanelController.displayDiceResults(dicePair);
					statusBarController.setAction("Rolling player");
				}
			});
		}

	}

	@Override
	public void result(Player player, final DicePair result, GameEngine gameEngine) {

		if (mainPanelController.getGameController().getCurrentPlayer().equals(player)) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					mainPanelController.displayDiceResults(result);

				}
			});

		}
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					statusBarController.setAction("House rolling");

				}
			});


	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				statusBarController.setAction("House roll finished");

			}
		});


	}

}
