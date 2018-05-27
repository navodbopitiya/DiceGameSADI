package controller;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
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

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public GameController() {
		currentPlayer = null;
		gameController = this;
		gameEngine = new GameEngineImpl();
		mainMenu = new MainMenuView();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// Run UI code on a new thread
				mainMenu.initialize();
				mainMenuController = new MainMenuController(gameController, mainMenu);
			}
		});

	}

	public void startGame() {
		if (gameMenu == null) {
			// If the application is starting
			this.currentPlayer = gameEngine.getAllPlayers().iterator().next();
			gameMenu = new GameMenu(this);
			menuBarController = new MenuBarController(gameController, gameMenu.getMainMenuBar());
			toolbarController = new ToolbarController(gameController, gameMenu.getToolbar());
			mainPanelController = new MainPanelController(gameController, gameMenu.getMainPanel());
			statusBarController = new StatusbarController(gameController, gameMenu.getStatusBar());
			gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
			gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainPanelController,statusBarController));

		} else {
			// When player goes back to add more players
			menuBarController.updatePlayers();
			toolbarController.updatePlayers();
			statusBarController.updateGameDetails();
			statusBarController.updateCurrentPlayerDetails(this.currentPlayer);
			gameMenu.setVisible(true);

		}
	}

	public boolean placeBet(int betAmount, Player player) {
		System.out.println("Betting - Controller");
		System.out.println(player);
		return gameEngine.placeBet(player, betAmount);
	}

	public void roll() {
		System.out.println("Rolling - Controller");
		boolean checkIfAnyPlayerHasBet = false;
		// For All player's that have a bet, roll
		for (final Player playerToBeRolled : gameEngine.getAllPlayers()) {
			if (playerToBeRolled.getBet() > 0) {
				// If the player has a bet, roll
				gameEngine.rollPlayer(playerToBeRolled, 1, 1000, 100);
				checkIfAnyPlayerHasBet = true;
			}
		}
		if (checkIfAnyPlayerHasBet) {
			// After all player's have finished rolling, roll house
			gameEngine.rollHouse(1, 100, 20);
			//Update Status Bar and ResultsPanel
			updateStatusBar();
			updatePlayerResults();
			

		} else {
			gameMenu.getToolbar().showErrorMessage("Please place a bet for a player first");
		}

	}

	public void changeCurrentPlayer(Player playerToChange) {
		currentPlayer = playerToChange;
		mainPanelController.changePlayer(this.currentPlayer);
		toolbarController.changePlayer(this.currentPlayer);
		statusBarController.updateCurrentPlayerDetails(this.currentPlayer);
		System.out.println("Switched current player to: " + currentPlayer.getPlayerName());

	}
	
	public void updateStatusBar(){
		statusBarController.updateCurrentPlayerDetails(this.currentPlayer);
	}
	
	public void updatePlayerResults(){
		mainPanelController.updatePlayerResults(this.currentPlayer);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void switchToMainMenu() {
		gameMenu.getFrame().setVisible(false);
		System.out.println("YOU ARE IN SWITCHING");
		mainMenu.getFrame().setVisible(true);
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}
}
