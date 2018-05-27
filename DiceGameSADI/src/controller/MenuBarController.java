package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

import model.GameConstants;
import model.interfaces.Player;
import view.Menubar;

public class MenuBarController {
	private GameController gameController;
	private Menubar menuBar;

	public MenuBarController(GameController gameController, Menubar menuBar) {
		this.gameController = gameController;
		this.menuBar = menuBar;
		menuBar.addActionListener(new MenuBarListener());
		updatePlayers();
	}

	public void updatePlayers() {
		JMenuItem[] playerItems = new JMenuItem[gameController.getGameEngine().getAllPlayers().size()];
		int i = 0;
		for (Player player : gameController.getGameEngine().getAllPlayers()) {
			/* Go through all players and add to Menu bar Item */
			playerItems[i] = new JMenuItem(new PlayerMenuAction(player.getPlayerName()));
			i++;
		}
		menuBar.updatePlayers(playerItems);
	}

	@SuppressWarnings("serial")
	class PlayerMenuAction extends AbstractAction {
		public PlayerMenuAction(String playerName) {
			super(playerName);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			/*User has switched players*/
			Player playerToChange = null;
			for (Player player : gameController.getGameEngine().getAllPlayers()) {
				if (player.getPlayerName().equals(getValue(NAME))) {
					playerToChange = player;
				}
			}

			if (playerToChange != null) {
				/*GameEngine had the player*/
				System.out.println("CHANGING PLAYERS");
				gameController.changeCurrentPlayer(playerToChange);
				
			}

		}
	}

	class MenuBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// Check what Item was clicked
			System.out.println("SOME ACTION WAS CLICKED IN MENUBAR");
			String actionCommand = event.getActionCommand();
			if (actionCommand.equals(GameConstants.ADD_PLAYER_ACTION)) {
				/* Add Player Item */
				System.out.println("YOU ARE IN ADD PLAYER ITEM IN MENUBAR");
				gameController.switchToMainMenu();
			} else if (actionCommand.equals(GameConstants.BET_BUTTON_ACTION)) {
				/*Bet Item -TODO*/
			} else if (actionCommand.equals(GameConstants.ROLL_BUTTON_ACTION)) {
				/*Roll Item - TODO*/
			}
		}

	}
}
