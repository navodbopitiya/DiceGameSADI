package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Statusbar extends JPanel{
	
	private JLabel numOfPlayers;
	private JLabel totalBets;
	private JLabel currentAction;
	private JLabel currentBet;
	private JLabel currentBalance;
	
	public Statusbar(){
		numOfPlayers = new JLabel("Player Count: ");
		totalBets = new JLabel("Total Bets: ");
		currentAction = new JLabel("Current Action: ");
		currentBet = new JLabel("Player Bet: ");
		currentBalance = new JLabel("Player Balance: ");
		
		/*Add labels to panel*/
		this.add(numOfPlayers);
		this.add(totalBets);
		this.add(currentAction);
		this.add(currentBet);
		this.add(currentBalance);
	}
	
	public void setPlayerNumber(int playerCount){
		numOfPlayers.setText("Player Count: "+playerCount);
	}
	
	public void setTotalBets(int sumBets){
		totalBets.setText("Total Bets: "+sumBets);
	}
	
	public void setCurrentAction(String actionName){
		currentAction.setText("Current Action: "+actionName);
	}
	
	public void setCurrentBet(int playerBetValue){
		currentBet.setText("Player Bet: "+playerBetValue);
	}
	
	public void setCurrentBalance(int playerBalance){
		currentBalance.setText("Player Balance: "+playerBalance);
	}

}
