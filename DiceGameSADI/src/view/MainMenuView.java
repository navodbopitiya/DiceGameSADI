package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GameController;
import model.GameConstants;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author Navod Bopitiya
 * @studentId s3617221 
 * Apr 14, 2018
 * MainMenuView.java 
 * Describe:
 */

@SuppressWarnings("serial")
public class MainMenuView extends JFrame {
	
	private JButton btnAddPlayer;
	private JButton btnPlay;
	private JTextField playerTextField;
	private JTextField initialPointsTextField;
	private JPanel playerPanel;



	public void initialize() {
		/* Create new frame */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setResizable(true);
		this.getContentPane().setBackground(new Color(27, 91, 127));
		this.setMinimumSize(new Dimension(screenSize.width/2,screenSize.height/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setLocationRelativeTo(null);

		

		/* Panel for player text input */
		JPanel playerTextPanel = new JPanel();
		playerTextPanel.setLayout(new BoxLayout(playerTextPanel, BoxLayout.LINE_AXIS));
		playerTextPanel.setBackground(GameConstants.BG_BLUE);

		/* Panel for player betting points input */
		JPanel pointsPanel = new JPanel();
		pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.LINE_AXIS));
		pointsPanel.setBackground(GameConstants.BG_BLUE);

		/* Panel for buttons */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBackground(GameConstants.BG_BLUE);

		/* Label for Player Text Field */
		JLabel lblPlayer = new JLabel("Player Name");
		lblPlayer.setForeground(Color.ORANGE);
		lblPlayer.setFont(new Font("Arial", Font.BOLD, 13));
		playerTextPanel.add(lblPlayer);
		playerTextPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		/* TextField for Player */
		playerTextField = new JTextField();
		playerTextField.setPreferredSize(new Dimension(120, 23));
		playerTextField.setMaximumSize(playerTextField.getPreferredSize());
		playerTextPanel.add(playerTextField);

		/* Label for Initial Points */
		JLabel lblInitialPoints = new JLabel("Initial Points");
		lblInitialPoints.setForeground(Color.ORANGE);
		lblInitialPoints.setFont(new Font("Arial", Font.BOLD, 13));
		pointsPanel.add(lblInitialPoints);
		pointsPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		/* TextField for Initial Points */
		initialPointsTextField = new JTextField();
		initialPointsTextField.setPreferredSize(new Dimension(120, 23));
		initialPointsTextField.setMaximumSize(initialPointsTextField.getPreferredSize());
		pointsPanel.add(initialPointsTextField);

		/* Panel for all player details */
		GridLayout gridLayout = new GridLayout(0, 4);
		playerPanel = new JPanel();
		playerPanel.setLayout(gridLayout);
		playerPanel.setBackground(GameConstants.BG_BLUE);
		
		/* Add button */
		btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setBackground(Color.ORANGE);
		btnAddPlayer.setActionCommand(GameConstants.ADD_PLAYER_ACTION);
		buttonPanel.add(btnAddPlayer);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		/* Play button */
		btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.ORANGE);
		btnPlay.setActionCommand(GameConstants.PLAY_BUTTON_ACTION);
		buttonPanel.add(btnPlay);

		/* Add all panels to frame */
		this.getContentPane().add(Box.createRigidArea(new Dimension(0, 40)));
		this.getContentPane().add(playerTextPanel);
		this.getContentPane().add(Box.createRigidArea(new Dimension(0, 10)));
		this.getContentPane().add(pointsPanel);
		this.getContentPane().add(Box.createRigidArea(new Dimension(0, 10)));
		this.getContentPane().add(buttonPanel);
		this.getContentPane().add(Box.createRigidArea(new Dimension(0, 30)));
		this.getContentPane().add(playerPanel);
		this.setVisible(true);

	}
	
	public void addActionListener(ActionListener listenForButtonPress){
		btnAddPlayer.addActionListener(listenForButtonPress);
		btnPlay.addActionListener(listenForButtonPress);
	}
	
	public void showErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public String getPlayerNameText(){
		return playerTextField.getText();
	}
	
	public String getInitialPointsString(){
		return initialPointsTextField.getText();
	}
	
	public JPanel getPlayerTextPanel(){
		return playerPanel;
	}
	
	public void resetTextFields(){
		playerTextField.setText("");
		initialPointsTextField.setText("");
	}
	
	public void switchToMainMenu(){
		/*Used to switch from Game Menu to this, when adding new players after playing Game*/
		this.setVisible(true);
	}

}
