/* Comment
 * 
 */

package edu.gatech.spacetrader.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class SpaceTrader extends JFrame {
    /**
     * Field HEIGHT.
     */
    /**
     * Field WIDTH.
     */
    private static final int WIDTH = 1000, HEIGHT = 500;
	
    /**
     * Field GamePanel.
     */
    private static final GamePanel GamePanel = new GamePanel(WIDTH, HEIGHT);;
	
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
	private static final boolean GameRunning = true;
	
	/**
	 * Constructor for SpaceTrader.
	 */
	public SpaceTrader(){
		super("Space Trader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		getContentPane().add(GamePanel);

		runGameLoop();    
		final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((int) (dimension.getWidth() - WIDTH) >> 1,
	            (int) (dimension.getHeight() - HEIGHT) >> 1);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	/**
	 * Method runGameLoop.
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
	 */
	private void gameLoop() {
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();
      
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
      
		while (GameRunning) {
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
         
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime) lastSecondTime = thisSecond;
         
			while(now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS 
					&& now - lastUpdateTime < TIME_BETWEEN_UPDATES){
				Thread.yield();
				try {
				    Thread.sleep(1);
			    } catch(Exception e) {
			        System.out.println(e);
			    } 
				now = System.nanoTime();
			}
		}
	}
	
	/**
	 * Method drawGame.
	 */
	private void drawGame(){
		GamePanel.repaint();
	}
	
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new edu.gatech.spacetrader.main.SpaceTrader();
			}
		});
	}
}
