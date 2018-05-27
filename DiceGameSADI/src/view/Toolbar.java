/**
 * 
 */
package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Collection;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import model.ComboBoxPlayer;
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
		switchPlayerBox.setEditable(true);

		/* Add buttons and fields to toolbar */
		this.setLayout(new GridLayout());
		this.add(btnRoll);
		this.add(switchPlayerBox);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		this.add(betTextField);
		this.add(btnBet);
	}

	public void updatePlayers(Collection<ComboBoxPlayer> playerList) {
		/*Update player list*/
		switchPlayerBox.removeAllItems();
		for (ComboBoxPlayer player : playerList) {
			switchPlayerBox.addItem(player);
		}
	}

	public void changePlayer(ComboBoxPlayer player) {
		/*Set selected player to current player*/
		switchPlayerBox.setSelectedItem(player);
	}

	
	public void addActionListener(ActionListener listenToToolBar){
		btnRoll.addActionListener(listenToToolBar);
		btnBet.addActionListener(listenToToolBar);
	}
	
	public void addItemListener(ItemListener itemListener){
		switchPlayerBox.addItemListener(itemListener);
	}
	
	public String getBetText(){
		String betString = betTextField.getText();
		resetBetTextField();
		return betString;
	}
	
	public void resetBetTextField(){
		this.betTextField.setText("");
	}
	
	public JComboBox<Player> getPlayerBox(){
		return switchPlayerBox;
	}

}
