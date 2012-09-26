package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SpaceTrader extends JFrame {
	private static GamePanel gamePanel;
	private int width = 500, height = 500;
	
	private final double TIME_BETWEEN_UPDATES = 1000000000 / 30.0;
	private final int MAX_UPDATES_BEFORE_RENDER = 5;
	private final double TARGET_FPS = 60;
	private final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	
	public SpaceTrader(){
		super("Space Trader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel = new GamePanel(width, height);
		gamePanel.setPreferredSize(new Dimension(width, height));
		getContentPane().add(gamePanel);
		
		runGameLoop();
		setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - width/2), 
	    		(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - height/2));
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	public void runGameLoop(){
		Thread loop = new Thread(){
			public void run(){
				gameLoop();
			}
		};
		loop.start();
	}
	
	private void gameLoop(){	
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
      
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
      
		while (true){
			double now = System.nanoTime();
			int updateCount = 0;
         
			while(now - lastUpdateTime > TIME_BETWEEN_UPDATES 
					&& updateCount < MAX_UPDATES_BEFORE_RENDER){
				//updateGame();
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}
			
			if(now - lastUpdateTime > TIME_BETWEEN_UPDATES){
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}

			drawGame();
			lastRenderTime = now;
         
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime) lastSecondTime = thisSecond;
         
			while(now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS 
					&& now - lastUpdateTime < TIME_BETWEEN_UPDATES){
				Thread.yield();
				try {Thread.sleep(1);} catch(Exception e) {} 
				now = System.nanoTime();
			}
		}
	}
	
	private void drawGame(){
		gamePanel.repaint();
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SpaceTrader();
			}
		});
	}
}
