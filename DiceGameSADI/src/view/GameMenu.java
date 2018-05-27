/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @author Navod Bopitiya
 *
 */
@SuppressWarnings("serial")
public class GameMenu extends JFrame{
	
	private Toolbar toolbar;
	private Menubar menuBar;
	private MainPanel mainPanel;
	private Statusbar statusBar;
	
	
	public GameMenu() {
		initialize();
	}
	
	private void initialize() {

		/* Create new frame */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setResizable(true);
		this.getContentPane().setBackground(new Color(27, 91, 127));
		this.setMinimumSize(new Dimension(screenSize.width/2,screenSize.height/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		/*Create Menubar and add to frame*/
		menuBar = new Menubar();
		this.setJMenuBar(menuBar);
		
		/*Create and add toolbar to Frame*/
		toolbar = new Toolbar();
		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		
		/*Create and add MainPanel to Frame*/
		mainPanel = new MainPanel();
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		/*Create and add StatusBar to Frame*/
		statusBar = new Statusbar();
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public MainPanel getMainPanel(){
		return this.mainPanel;
	}
	
	public Menubar getMainMenuBar(){
		return this.menuBar;
	}
	
	public Toolbar getToolbar(){
		return this.toolbar;
		
	}
	
	public Statusbar getStatusBar(){
		return this.statusBar;
	}

	
}
