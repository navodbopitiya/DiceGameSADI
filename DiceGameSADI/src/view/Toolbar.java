/**
 * 
 */
package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.GameController;
import model.GameConstants;
import model.interfaces.Player;

/**
 * @author Navod Bopitiya
 *
 */
@SuppressWarnings("serial")
public class Toolbar extends JToolBar {

	private JComboBox<Player> switchPlayerBox;
	private JButton btnRoll;
	private JTextField betTextField;
	private JButton btnBet;
	

	public Toolbar() {
		/* Roll Button */
		btnRoll = new JButton("Roll");
		btnRoll.setActionCommand(GameConstants.ROLL_BUTTON_ACTION);

		/* Bet Text Field */
		betTextField = new JTextField();

		/* Bet Button */
		btnBet = new JButton("Bet");
		btnBet.setActionCommand(GameConstants.BET_BUTTON_ACTION);

		/* Switch Player ComboBox */
		switchPlayerBox = new JComboBox<Player>();

		/* Add buttons and fields to toolbar */
		this.setLayout(new GridLayout());
		this.add(btnRoll);
		this.add(switchPlayerBox);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		this.add(betTextField);
		this.add(btnBet);
	}

	public void updatePlayers(Collection<Player> playerList) {
		switchPlayerBox.removeAllItems();
		for (Player player : playerList) {
			switchPlayerBox.addItem(player);
		}
	}

	public void changePlayer(Player player) {
		switchPlayerBox.setSelectedItem(player);
		System.out.println("PLAYER CHANGED IN TOOLBAR");
	}

	
	public void addActionListener(ActionListener listenToToolBar){
		btnRoll.addActionListener(listenToToolBar);
		btnBet.addActionListener(listenToToolBar);
	}
	
	public void addItemListener(ItemListener itemListener){
		switchPlayerBox.addItemListener(itemListener);
	}
	
	public void showErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(null, errorMessage);
	}
	
	public String getBetText(){
		return betTextField.getText();
	}
	
	public void resetBetTextField(){
		this.betTextField.setText("");
	}
	
	public JComboBox<Player> getPlayerBox(){
		return switchPlayerBox;
	}

}
