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
		/*When player has no roll result or intermediate roll results*/
		resultsPanel.setPlayerName(player.getPlayerName());
		resultsPanel.setBalance(Integer.toString(player.getPoints()));
		dicePanel.updateDiceUI(player.getRollResult(),dicePanel.getPlayerDiceOneImage(), dicePanel.getPlayerDiceTwoImage());
		dicePanel.updateResultBar();
	}
	
	public void changePlayer(Player player,String resultText){
		/*When player has a current roll result*/
		changePlayer(player);
		dicePanel.updateResultBar(resultText,player.getPlayerName());
	}
	
	
	public void displayPlayerDiceResults(DicePair dicePair){
		dicePanel.updateDiceUI(dicePair, dicePanel.getPlayerDiceOneImage(), dicePanel.getPlayerDiceTwoImage());
	}
	
	public void displayHouseDiceResults(DicePair dicePair){
		dicePanel.updateDiceUI(dicePair, dicePanel.getHouseDiceOneImage(), dicePanel.getHouseDiceTwoImage());
	}
	
	public void updatePlayerResults(Player currentPlayer){
		resultsPanel.setBalance(Integer.toString(currentPlayer.getPoints()));
	}
	
	public void displayResultBar(String resultText,String currentPlayerName){
		/*Display result of roll - When current player has a bet for the round*/
		dicePanel.updateResultBar(resultText,currentPlayerName);
	}
	
	public void displayResultBar(){
		/*Default result display - When current player doesn't have a bet for that round*/
		dicePanel.updateResultBar();
	}

}
