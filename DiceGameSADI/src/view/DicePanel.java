package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.DicePairImpl;
import model.GameConstants;
import model.interfaces.DicePair;

@SuppressWarnings("serial")
public class DicePanel extends JPanel {

	/* Player Panel */
	private JPanel playerDicePairPanel;
	private JPanel playerDiceOnePanel;
	private JPanel playerDiceTwoPanel;
	private JLabel playerDiceOneLabel;
	private JLabel playerDiceTwoLabel;
	private JLabel playerDiceOneImage;
	private JLabel playerDiceTwoImage;
	
	/*Result Bar*/
	private JPanel resultBar;
	private JLabel resultBarLabel;
	
	
	/* House Panel */
	private JPanel houseDicePairPanel;
	private JPanel houseDiceOnePanel;
	private JPanel houseDiceTwoPanel;
	private JLabel houseDiceOneLabel;
	private JLabel houseDiceTwoLabel;
	private JLabel houseDiceOneImage;
	private JLabel houseDiceTwoImage;

	public DicePanel() {

		/* Default look - No Player Selected */
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		/* Create Player Panel */
		playerDiceOneImage = new JLabel();
		playerDiceTwoImage = new JLabel();
		playerDicePairPanel = createDicePanel(playerDicePairPanel, playerDiceOnePanel, playerDiceTwoPanel,
				playerDiceOneImage, playerDiceTwoImage, playerDiceOneLabel, playerDiceTwoLabel,GameConstants.PLAYER_TEXT);

		/* Create ResultBar */
		resultBar = new JPanel();
		resultBar.setLayout(new BoxLayout(resultBar,BoxLayout.X_AXIS));
		resultBarLabel = new JLabel(GameConstants.DEFAULT_RESULT_STRING);
		resultBarLabel.setFont(new Font("Arial",Font.BOLD,30));
		
		resultBar.add(Box.createHorizontalGlue());
		resultBar.add(resultBarLabel);
		resultBar.add(Box.createHorizontalGlue());
		
		
		/* Create House Panel */
		houseDiceOneImage = new JLabel();
		houseDiceTwoImage = new JLabel();
		houseDicePairPanel = createDicePanel(houseDicePairPanel, houseDiceOnePanel, houseDiceTwoPanel,
				houseDiceOneImage, houseDiceTwoImage, houseDiceOneLabel, houseDiceTwoLabel,GameConstants.HOUSE_TEXT);
		
		
		this.add(Box.createGlue());
		this.add(playerDicePairPanel);
		this.add(Box.createGlue());
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(resultBar);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(Box.createGlue());
		this.add(houseDicePairPanel);
		this.add(Box.createGlue());
	}

	private JPanel createDicePanel(JPanel dicePairPanel, JPanel diceOnePanel, JPanel diceTwoPanel, JLabel diceOneImage,
			JLabel diceTwoImage, JLabel diceOneLabel, JLabel diceTwoLabel, String ownerText) {
		dicePairPanel = new JPanel();
		dicePairPanel.setLayout(new BoxLayout(dicePairPanel, BoxLayout.LINE_AXIS));
		dicePairPanel.setBackground(null);
		
		/* Owner Text Panel*/
		JPanel ownerPanel = new JPanel();
		ownerPanel.setBackground(null);
		JLabel ownerTextLabel = new JLabel(ownerText+" Dice");
		ownerTextLabel.setForeground(Color.ORANGE);
		ownerPanel.add(ownerTextLabel);
		ownerPanel.setLayout(new BoxLayout(ownerPanel, BoxLayout.PAGE_AXIS));
		
		/* Dice One Panel */
		diceOnePanel = new JPanel();
		diceOnePanel.setBackground(null);
		diceOnePanel.setLayout(new BoxLayout(diceOnePanel, BoxLayout.PAGE_AXIS));
		diceOneLabel = new JLabel("Dice One");
		diceOneLabel.setForeground(Color.ORANGE);
		diceOnePanel.add(diceOneLabel);
		diceOnePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		diceOnePanel.add(diceOneImage);

		/* Dice Two Panel */
		diceTwoPanel = new JPanel();
		diceTwoPanel.setBackground(null);
		diceTwoPanel.setLayout(new BoxLayout(diceTwoPanel, BoxLayout.PAGE_AXIS));
		diceTwoLabel = new JLabel("Dice Two");
		diceTwoLabel.setForeground(Color.ORANGE);
		diceTwoPanel.add(diceTwoLabel);
		diceTwoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		diceTwoPanel.add(diceTwoImage);

		updateDiceUI(null, diceOneImage, diceTwoImage);

		/*Dice Pair Panel */
		dicePairPanel.add(ownerPanel);
		dicePairPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		dicePairPanel.add(diceOnePanel);
		dicePairPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		dicePairPanel.add(diceTwoPanel);

		return dicePairPanel;
	}

	public void updateDiceUI(DicePair dicePair, JLabel diceOneImage, JLabel diceTwoImage) {
		if (dicePair == null) {
			dicePair = new DicePairImpl(0, 0, 6);
		}
		String pathToDiceImageOne = "resources/dice" + dicePair.getDice1() + ".png";
		String pathToDiceImageTwo = "resources/dice" + dicePair.getDice2() + ".png";
		try {
			BufferedImage diceImageOne = ImageIO.read(new File(pathToDiceImageOne));
			BufferedImage diceImageTwo = ImageIO.read(new File(pathToDiceImageTwo));

			diceOneImage.setIcon(new ImageIcon(diceImageOne.getScaledInstance(130, 130, Image.SCALE_FAST)));
			diceTwoImage.setIcon(new ImageIcon(diceImageTwo.getScaledInstance(130, 130, Image.SCALE_FAST)));
			this.revalidate();
			this.repaint();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateResultBar(String resultText,String currentPlayerName){
		resultBarLabel.setText(currentPlayerName+" "+resultText);
	}
	
	public void updateResultBar(){
		resultBarLabel.setText(GameConstants.DEFAULT_RESULT_STRING);
	}

	public JLabel getPlayerDiceOneImage() {
		return this.playerDiceOneImage;
	}

	public JLabel getPlayerDiceTwoImage() {
		return this.playerDiceTwoImage;
	}
	
	public JLabel getHouseDiceOneImage(){
		return this.houseDiceOneImage;
	}
	
	public JLabel getHouseDiceTwoImage(){
		return this.houseDiceTwoImage;
	}
}
