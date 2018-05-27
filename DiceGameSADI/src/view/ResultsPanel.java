package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class ResultsPanel extends JPanel{
	
	private JPanel playerNamePanel;
	private JPanel wonPanel;
	private JPanel lostPanel;
	private JPanel balancePanel;
	
	private JLabel playerNamelbl;
	private JLabel playerName;
	private JLabel wonLabel;
	private JLabel lostLabel;
	private JLabel balanceLabel;
	
	private JLabel wonAmount;
	private JLabel lostAmount;
	private JLabel balanceAmount;
	
	public ResultsPanel(){
		/*Default look - No Player Selected*/
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		/*Player Name Panel*/
		playerNamePanel = new JPanel();
		playerNamePanel.setBackground(null);
		playerNamelbl = new JLabel("Player : ");
		playerNamelbl.setForeground(Color.ORANGE);
		playerName = new JLabel("playerName");
		playerName.setForeground(Color.ORANGE);
		playerNamePanel.setLayout(new BoxLayout(playerNamePanel,BoxLayout.LINE_AXIS));
		playerNamePanel.add(playerNamelbl);
		playerNamePanel.add(Box.createRigidArea(new Dimension(0,10)));
		playerNamePanel.add(playerName);
		
		/*Balance Panel*/
		balancePanel = new JPanel();
		balancePanel.setBackground(null);
		balanceLabel =  new JLabel("Balance : ");
		balanceLabel.setForeground(Color.ORANGE);
		balanceAmount = new JLabel("0");
		balanceAmount.setForeground(Color.ORANGE);
		balancePanel.setLayout(new BoxLayout(balancePanel,BoxLayout.LINE_AXIS));
		balancePanel.add(balanceLabel);
		balancePanel.add(Box.createRigidArea(new Dimension(0,10)));
		balancePanel.add(balanceAmount);
		
		/*Won Panel*/
		wonPanel = new JPanel();
		wonPanel.setBackground(null);
		wonLabel =  new JLabel("Won : ");
		wonLabel.setForeground(Color.ORANGE);
		wonAmount = new JLabel("0");
		wonAmount.setForeground(Color.ORANGE);
		wonPanel.setLayout(new BoxLayout(wonPanel,BoxLayout.LINE_AXIS));
		wonPanel.add(wonLabel);
		wonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		wonPanel.add(wonAmount);
		
		
		/*Lost Panel*/
		lostPanel = new JPanel();
		lostPanel.setBackground(null);
		lostLabel =  new JLabel("Lost : ");
		lostLabel.setForeground(Color.ORANGE);
		lostAmount = new JLabel("0");
		lostAmount.setForeground(Color.ORANGE);
		lostPanel.setLayout(new BoxLayout(lostPanel,BoxLayout.LINE_AXIS));
		lostPanel.add(lostLabel);
		lostPanel.add(Box.createRigidArea(new Dimension(0,10)));
		lostPanel.add(lostAmount);
		
		this.add(Box.createRigidArea(new Dimension(0,80)));
		this.add(playerNamePanel);
		this.add(Box.createRigidArea(new Dimension(0,60)));
		this.add(balancePanel);
		this.add(Box.createRigidArea(new Dimension(0,30)));
		this.add(wonPanel);
		this.add(Box.createRigidArea(new Dimension(0,30)));
		this.add(lostPanel);
		
	}
	
	public void setPlayerName(String playerNameTxt){
		playerName.setText(playerNameTxt);
	}
	
	public void setBalance(String playerPoints){
		balanceAmount.setText(playerPoints);
		balanceAmount.revalidate();
		balanceAmount.repaint();
	}
	
	public void setWonAmount(String pointsWon){
		wonAmount.setText(pointsWon);
	}
	
	public void setLostAmount(String pointsLost){
		lostAmount.setText(pointsLost);
	}
}
