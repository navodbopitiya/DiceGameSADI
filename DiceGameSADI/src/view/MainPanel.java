package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSplitPane;

import model.interfaces.DicePair;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class MainPanel extends JSplitPane{
	private DicePanel dicePanel;
	private ResultsPanel resultsPanel;
	private Color bgBlue = new Color(27, 91, 127);
	
	public MainPanel(){
		/*No Player selected - */
		dicePanel = new DicePanel();
		resultsPanel = new ResultsPanel();
		this.setLeftComponent(resultsPanel);
		this.setRightComponent(dicePanel);
		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);		
		this.setBackground(bgBlue);
		this.setEnabled(false);
		this.leftComponent.setMinimumSize(new Dimension(150,200));
		this.rightComponent.setMinimumSize(new Dimension(150,200));
		this.leftComponent.setBackground(bgBlue);
		this.rightComponent.setBackground(bgBlue);
	}
	
	public void changePlayer(Player player){
		resultsPanel.setPlayerName(player.getPlayerName());
		resultsPanel.setBalance(Integer.toString(player.getPoints()));
		dicePanel.updateDiceUI(player.getRollResult());
	}
	
	
	public void displayDiceResults(DicePair dicePair){
		dicePanel.updateDiceUI(dicePair);
	}

}
