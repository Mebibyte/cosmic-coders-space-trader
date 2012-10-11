// $codepro.audit.disable disallowSleepInsideWhile, com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods, lossOfPrecisionInCast, multiplicationOrDivisionByPowersOf2
/* SpaceTrader class
 * Main class for project.
 */

package edu.gatech.spacetrader.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Glenn
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SpaceTrader extends JFrame {
    /**
     * Field HEIGHT.
     * (value is 1000)
     */
    /**
     * Field WIDTH.
     */
    private static final int WIDTH = 1000, HEIGHT = (WIDTH * 9) / 16;
	
    /**
     * Field GamePanel.
     */
    private final GamePanel gamePanel;
	
	/**
	 * Field TIME_BETWEEN_UPDATES.
	 */
	private static final double TIME_BETWEEN_UPDATES = 1000000000 / 30.0;
	
	/**
	 * Field MAX_UPDATES_BEFORE_RENDER.
	 * (value is 5)
	 */
	private static final int MAX_UPDATES_BEFORE_RENDER = 5;
	
	/**
     * Field ONEBILLION.
     * (value is 5)
     */
    private static final int ONEBILLION = 1000000000;
	
	/**
	 * Field TARGET_FPS.
	 * (value is 60.0)
	 */
	private static final double TARGET_FPS = 60;
	
	/**
	 * Field TARGET_TIME_BETWEEN_RENDERS.
	 */
	private static final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	
	/**
	 * Field GameRunning.
	 */
	private boolean gameRunning = true;
	
	/**
	 * Constructor for SpaceTrader.
	 * Creates a new GamePanel and adds it to the JFrame.
	 * Also starts the game loop.
	 */
	public SpaceTrader(){
		super("Space Trader");
		setResizable(false);
		
		gamePanel = new GamePanel(WIDTH, HEIGHT);
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(gamePanel);
	    
        pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		
		runGameLoop();
	}
	
	/**
	 * Method runGameLoop.
	 * Creates a new thread to run the game loop.
	 */
	public void runGameLoop(){
		final Thread loop = new Thread(){
			public void run(){
				gameLoop();
			}
		};
		loop.start();
	}
	
	/**
	 * Method gameLoop.
	 * Draws to the panel and updates the game.
	 * Attempts to maintain 60 FPS.
	 */
	private void gameLoop() {
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();
              
		int lastSecondTime = (int) (lastUpdateTime / ONEBILLION);
      
		while (gameRunning) {
			double now = System.nanoTime();
			int updateCount = 0;
         
			while (now - lastUpdateTime > TIME_BETWEEN_UPDATES 
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
         
			int thisSecond = (int) (lastUpdateTime / ONEBILLION);
			if (thisSecond > lastSecondTime) lastSecondTime = thisSecond;
         
			while(now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS 
					&& now - lastUpdateTime < TIME_BETWEEN_UPDATES){
				Thread.yield();
				try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
				now = System.nanoTime();
			}
		}
	}
	
	/**
	 * Method drawGame.
	 * Calls the repaint method of the GamePanel.
	 */
	private void drawGame(){
		gamePanel.repaint();
	}
	
	/**
	 * Method main.
	 * When run, creates a new instance of SpaceTrader.
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SpaceTrader();
			}
		});
	}

	/**
     * Method quitGame.
     * Ends the game loop.
     */
	public void quitGame() {
        gameRunning = false;
    }
}
