package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import model.GameConstants;
import model.SimplePlayer;
import model.interfaces.Player;
import view.MainMenuView;

public class MainMenuController {

	private GameController gameController;
	private MainMenuView mainMenuView;
	private int playerID = 0;

	public MainMenuController(GameController gameController, MainMenuView mainMenuView) {
		/*Overloaded Constructor for class - has references of GameController and MainMenuView*/
		this.gameController = gameController;
		this.mainMenuView = mainMenuView;
		
		/*add's action listener to view*/
		this.mainMenuView.addActionListener(new MainMenuListener());

	}
	
	public void switchToMainMenu(){
		/*Used to switch to MainMenu from GameMenu*/
		
		updatePlayerTextPanel();
		mainMenuView.switchToMainMenu();
	}
	
	public void updatePlayerTextPanel(){
		/*Updating player balances since betting and rolling may have occurred*/
		mainMenuView.getPlayerTextPanel().removeAll();
		for(Player player : gameController.getGameEngine().getAllPlayers()){
			JLabel tempLabel = createTempLabel(player);
			mainMenuView.getPlayerTextPanel().add(tempLabel);
		}
		mainMenuView.getPlayerTextPanel().revalidate();
		mainMenuView.getPlayerTextPanel().repaint();
	}
	
	private JLabel createTempLabel(Player tempPlayer) {
		/*Helper method to remove code duplication
		 *Creates tempLabel from player object*/
		
		JLabel tempLabel = new JLabel(tempPlayer.getPlayerName() + " (" + tempPlayer.getPoints() + ")");
		tempLabel.setHorizontalAlignment(JLabel.CENTER);
		tempLabel.setForeground(Color.ORANGE);
		tempLabel.setFont(new Font("Arial", Font.BOLD, 13));
		return tempLabel;
	}
	
	class MainMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals(GameConstants.PLAY_BUTTON_ACTION)) {
				/* Play Button Action */

				/* Check if a player has been entered */
				if (gameController.getGameEngine().getAllPlayers().isEmpty()) {
					mainMenuView.showErrorMessage("Please enter a player first");
				} else {
					/* Open Game Window */
					mainMenuView.setVisible(false);
					gameController.startGame();
				}

			} else if (actionCommand.equals(GameConstants.ADD_PLAYER_ACTION)) {
				/* Add Player Button Action */

				String playerName = null;
				/* Check if player text field is empty */
				if (!mainMenuView.getPlayerNameText().isEmpty()) {
					playerName = mainMenuView.getPlayerNameText();
				} else {
					mainMenuView.showErrorMessage("Please enter a Name");
					return;
				}

				int initialPoints;
				/*Check if points text field is empty */
				if (!mainMenuView.getInitialPointsString().isEmpty()) {
					/*Check if numeric*/
					if (GameConstants.PATTERN.matcher(mainMenuView.getInitialPointsString()).matches()) {
						initialPoints = Integer.parseInt(mainMenuView.getInitialPointsString());
					} else {
						initialPoints = 0;
					}

				} else {
					initialPoints = 0;
				}

				boolean nameExists = false;
				boolean pointsValid = true;

				/* Check if PlayerName exists in the Player Array List */
				for (Player checkPlayer : gameController.getGameEngine().getAllPlayers()) {
					if (playerName.compareToIgnoreCase(checkPlayer.getPlayerName()) == 0) {
						/* Player name already exists */
						nameExists = true;
					}
				}

				/* Check if betting points are valid */
				if (initialPoints <= 0) {
					pointsValid = false;
				}

				/* If name doesn't exists and points are valid */
				if (nameExists == false && pointsValid == true) {

					Player tempPlayer = new SimplePlayer(Integer.toString(playerID), playerName, initialPoints);
					gameController.getGameEngine().addPlayer(tempPlayer);
					playerID++;

					/* Display names in playerPanel */
					JLabel tempLabel = createTempLabel(tempPlayer);

					mainMenuView.getPlayerTextPanel().add(tempLabel);
					mainMenuView.getPlayerTextPanel().validate();
					mainMenuView.getPlayerTextPanel().repaint();

					/* Reset TextFields */
					mainMenuView.resetTextFields();
				} else {
					if (nameExists == true) {
						mainMenuView.showErrorMessage("Name already Exists");
					} else if (pointsValid == false) {
						mainMenuView.showErrorMessage("Invalid Points");
					}

				}

			}

		}

	}

}
