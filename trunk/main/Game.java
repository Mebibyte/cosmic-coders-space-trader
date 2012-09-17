package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Game extends JFrame {
	private static GamePanel gamePanel;
	private Screen activeScreen;
	private int width = 500, height = 500;
	
	public Game(){
		super("Space Trader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(new Dimension(width, height));
		getContentPane().add(gamePanel);
		
		setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - width/2), 
	    		(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - height/2));
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	private class GamePanel extends JPanel{
		public GamePanel(){
			setFocusable(true);
			requestFocus();
		}
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
	}
}
