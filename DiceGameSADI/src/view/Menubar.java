package view;


import java.awt.event.ActionListener;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.GameConstants;

@SuppressWarnings("serial")
public class Menubar extends JMenuBar{

	private JMenu playerMenu;
	private JMenu fileMenu;
	private JMenuItem betItem;
	private JMenuItem rollItem;
	private JMenuItem addPlayerItem;

	public Menubar() {
		/* Menu bar */
		playerMenu = new JMenu("Switch Player");
		fileMenu = new JMenu("File");
		addPlayerItem = new JMenuItem("Add Player");
		addPlayerItem.setActionCommand(GameConstants.ADD_PLAYER_ACTION);
		betItem = new JMenuItem("Bet");
		betItem.setActionCommand(GameConstants.BET_BUTTON_ACTION);
		rollItem = new JMenuItem("Roll");
		rollItem.setActionCommand(GameConstants.ROLL_BUTTON_ACTION);
		
		fileMenu.add(addPlayerItem);
		fileMenu.add(betItem);
		fileMenu.add(rollItem);

		this.add(fileMenu);
		this.add(playerMenu);
		
	}

	public JMenu getPlayerMenu() {
		return playerMenu;
	}
	
	public void updatePlayers(JMenuItem[] playerItems) {
		playerMenu.removeAll();
		for(JMenuItem playerItem : playerItems){
			playerMenu.add(playerItem);
		}
		
	}
	
	public void addActionListener(ActionListener listenToMenubar){
		betItem.addActionListener(listenToMenubar);
		rollItem.addActionListener(listenToMenubar);
		addPlayerItem.addActionListener(listenToMenubar);
	}
	
}
