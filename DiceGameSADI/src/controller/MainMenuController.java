package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import model.GameConstants;
import model.SimplePlayer;
import model.interfaces.Player;
import view.MainMenuView;

public class MainMenuController {

	private GameController gameController;
	private MainMenuView mainMenuView;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private int playerID = 0;

	public MainMenuController(GameController gameController, MainMenuView mainMenuView) {
		this.gameController = gameController;
		this.mainMenuView = mainMenuView;
		
		this.mainMenuView.addActionListener(new MainMenuListener());

	}

	class MainMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals(GameConstants.PLAY_BUTTON_ACTION)) {
				/* Play Button Action */
				
				/*Check if a player has been entered - TODO */
				
				/* Open next Window */
				mainMenuView.setVisible(false);
				gameController.startGame();			

			} else if (actionCommand.equals(GameConstants.ADD_PLAYER_ACTION)) {
				/* Add Player Button Action */

				String playerName = null;
				/* Check for empty textboxes */
				if (!mainMenuView.getPlayerNameText().isEmpty()) {
					playerName = mainMenuView.getPlayerNameText();
				} else {
					mainMenuView.showErrorMessage("Please enter a Name");
					return;
				}

				int initialPoints;
				if (!mainMenuView.getInitialPointsString().isEmpty()) {
					if (GameConstants.PATTERN.matcher(mainMenuView.getInitialPointsString()).matches()) {
						// Check if value is numeric
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
				for (Player checkPlayer : playerList) {
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

					SimplePlayer tempPlayer = new SimplePlayer(Integer.toString(playerID), playerName, initialPoints);
					gameController.getGameEngine().addPlayer(tempPlayer);
					playerList.add(tempPlayer);
					playerID++;

					/* Display names in playerPanel */
					JLabel tempLabel = new JLabel(tempPlayer.getPlayerName() + " (" + tempPlayer.getPoints() + ")");
					tempLabel.setHorizontalAlignment(JLabel.CENTER);
					tempLabel.setForeground(Color.ORANGE);
					tempLabel.setFont(new Font("Arial", Font.BOLD, 13));

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
