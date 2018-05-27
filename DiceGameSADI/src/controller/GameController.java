package controller;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.GameHelperMethods;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.GameMenu;
import view.MainMenuView;

public class GameController {
	private GameEngine gameEngine;
	private Player currentPlayer;
	private MainMenuView mainMenu = null;
	private GameMenu gameMenu = null;
	private MainMenuController mainMenuController;
	private GameController gameController;
	private MenuBarController menuBarController;
	private ToolbarController toolbarController;
	private MainPanelController mainPanelController;
	private StatusbarController statusBarController;
	private DicePair houseResult;

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public GameController() {
		/* Main entrance of Game */
		currentPlayer = null;
		gameController = this;
		gameEngine = new GameEngineImpl();
		mainMenu = new MainMenuView();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				/* Open MainMenu and make a new Main Menu controller */
				mainMenu.initialize();
				mainMenuController = new MainMenuController(gameController, mainMenu);
			}
		});

	}

	public void startGame() {
		if (gameMenu == null) {
			/*
			 * If the game is starting for the first time Create Game Menu and
			 * controllers - passing references Add callbacks to GameEngine
			 */
			this.currentPlayer = gameEngine.getAllPlayers().iterator().next();
			gameMenu = new GameMenu();
			menuBarController = new MenuBarController(gameController, gameMenu.getMainMenuBar());
			mainPanelController = new MainPanelController(gameController, gameMenu.getMainPanel());
			statusBarController = new StatusbarController(gameController, gameMenu.getStatusBar());
			toolbarController = new ToolbarController(gameController, gameMenu.getToolbar());
			gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
			gameEngine.addGameEngineCallback(
					new GameEngineCallbackGUI(mainPanelController, statusBarController, gameController));

		} else {
			/*
			 * After user adds new players from MainMenu and comes back to
			 * GameMenu Update players and details on menubar, toolbar and
			 * statusbar open GameMenu
			 */
			menuBarController.updatePlayers();
			toolbarController.updatePlayers();
			statusBarController.updateGameDetails();
			statusBarController.updateCurrentPlayerDetails();
			gameMenu.setVisible(true);

		}
	}

	public boolean placeBet(int betAmount, Player player) {
		return gameEngine.placeBet(player, betAmount);
	}

	public void roll() {
		boolean checkIfAnyPlayerHasBet = false;
		// For All player's that have a bet, roll
		for (final Player playerToBeRolled : gameEngine.getAllPlayers()) {
			if (playerToBeRolled.getBet() > 0) {
				/* If the player has a bet, roll */
				gameEngine.rollPlayer(playerToBeRolled, 1, 1000, 100);
				checkIfAnyPlayerHasBet = true;
			} else {
				/*
				 * If player doesn't have a bet, reset his previous Roll Result
				 * so that view will display blank dice
				 */
				playerToBeRolled.setRollResult(null);
			}
		}
		if (checkIfAnyPlayerHasBet) {
			setHouseResult(null);
			/* After all player's have finished rolling, roll house */
			gameEngine.rollHouse(1, 1000, 100);

		} else {
			GameHelperMethods.showErrorMessage("Please place a bet for a player first", null);
		}

	}

	public void changeCurrentPlayer(Player playerToChange) {
		this.currentPlayer = playerToChange;

		mainPanelController.changePlayer(gameController.currentPlayer);
		toolbarController.changePlayer(gameController.currentPlayer);
		statusBarController.updateCurrentPlayerDetails();

	}

	public void updateStatusBar() {
		statusBarController.updateCurrentPlayerDetails();
		statusBarController.updateGameDetails();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void switchToMainMenu() {
		gameMenu.setVisible(false);
		mainMenuController.switchToMainMenu();
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}

	public DicePair getHouseResult() {
		return houseResult;
	}

	public void setHouseResult(DicePair houseResult) {
		this.houseResult = houseResult;
	}
}
