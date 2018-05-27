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
		menuBar.addActionListener(new ButtonListener(gameController,menuBar));
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
			
			/*Check if player was present in gameEngine*/
			if (playerToChange != null) {
				gameController.changeCurrentPlayer(playerToChange);
				
			}

		}
	}
}
