package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import model.interfaces.DicePair;

@SuppressWarnings("serial")
public class DicePanel extends JPanel {

	private JPanel diceOnePanel;
	private JPanel diceTwoPanel;
	private JPanel dicePairPanel;
	private JLabel diceOneLabel;
	private JLabel diceTwoLabel;
	private JLabel diceOneImageLabel;
	private JLabel diceTwoImageLabel;

	public DicePanel() {
		/* Default look - No Player Selected */
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		dicePairPanel = new JPanel();
		dicePairPanel.setLayout(new BoxLayout(dicePairPanel, BoxLayout.LINE_AXIS));
		dicePairPanel.setBackground(null);
		/* Dice One Panel */
		diceOnePanel = new JPanel();
		diceOnePanel.setBackground(null);
		diceOnePanel.setLayout(new BoxLayout(diceOnePanel, BoxLayout.PAGE_AXIS));
		diceOneLabel = new JLabel("Dice One");
		diceOneLabel.setForeground(Color.ORANGE);
		diceOneImageLabel = new JLabel();
		diceOnePanel.add(diceOneLabel);
		diceOnePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		diceOnePanel.add(diceOneImageLabel);

		/* Dice Two Panel */
		diceTwoPanel = new JPanel();
		diceTwoPanel.setBackground(null);
		diceTwoPanel.setLayout(new BoxLayout(diceTwoPanel, BoxLayout.PAGE_AXIS));
		diceTwoLabel = new JLabel("Dice Two");
		diceTwoLabel.setForeground(Color.ORANGE);
		diceTwoImageLabel = new JLabel();
		diceTwoPanel.add(diceTwoLabel);
		diceTwoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		diceTwoPanel.add(diceTwoImageLabel);

		updateDiceUI(null);

		dicePairPanel.add(diceOnePanel);
		dicePairPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		dicePairPanel.add(diceTwoPanel);
		this.add(dicePairPanel);
	}

	public void updateDiceUI(DicePair dicePair){
		if(dicePair == null){
			dicePair = new DicePairImpl(0, 0, 6);
		}
		String pathToDiceImageOne = "resources/dice"+dicePair.getDice1()+".png";
		String pathToDiceImageTwo = "resources/dice"+dicePair.getDice2()+".png";
		try {
			BufferedImage diceImageOne = ImageIO.read(new File(pathToDiceImageOne));
			BufferedImage diceImageTwo = ImageIO.read(new File(pathToDiceImageTwo));
			
			diceOneImageLabel.setIcon(new ImageIcon(diceImageOne.getScaledInstance(80, 80, Image.SCALE_FAST)));
			diceTwoImageLabel.setIcon(new ImageIcon(diceImageTwo.getScaledInstance(80, 80, Image.SCALE_FAST)));
			this.revalidate();
			this.repaint();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
