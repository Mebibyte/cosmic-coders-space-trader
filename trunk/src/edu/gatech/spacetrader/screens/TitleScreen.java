/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import edu.gatech.spacetrader.gui.BigButton;
import edu.gatech.spacetrader.main.GamePanel;

import java.awt.Graphics;
import java.awt.Point;


/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class TitleScreen extends Screen{
	/**
	 * Field newGame.
	 */
	private final BigButton newGame, quit, loadGame;
	
	/**
	 * Field height.
	 */
	/**
	 * Field width.
	 */
	private final int width, height;
	
	/**
	 * Field panel.
	 */
	private final GamePanel panel;
	
	/**
	 * Constructor for TitleScreen.
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public TitleScreen(GamePanel panel, int width, int height){
		this.panel = panel;
		this.width = width;
		this.height = height;
		newGame = new BigButton("New Game", width >> 1, height >> 1);
		loadGame = new BigButton("Load Game", width >> 1,
				(height >> 1) + newGame.getHeight());
		quit = new BigButton("Quit", width >> 1,
				(height >> 1) + newGame.getHeight() + loadGame.getHeight());
	}

	/**
	 * Method draw.
	 * @param g Graphics
	 */
	@Override
	public void draw(Graphics g) {
		g.drawString("Space Trader",
		        (width >> 1) - (g.getFontMetrics().stringWidth("Space Trader") >> 1),
		        (height >> 1) - (newGame.getHeight()));
		if (panel.isMouseOnScreen()) {
		    if (newGame.isIn(getHoverPoint())) {
		        newGame.drawHovered(g, panel, width, height);
		    } else newGame.draw(g, panel, width, height);
		    
		    if (loadGame.isIn(getHoverPoint())) {
		        loadGame.drawHovered(g, panel, width, height);
		    } else loadGame.draw(g, panel, width, height);
		    
		    if (quit.isIn(getHoverPoint())) {
		        quit.drawHovered(g, panel, width, height);
		    } else quit.draw(g, panel, width, height);
		} else {
		    newGame.draw(g, panel, width, height);
		    quit.draw(g, panel, width, height);
		    loadGame.draw(g, panel, width, height);
		}
	}
	
	/**
	 * Method checkForClick.
	 * @param point Point
	 */
	@Override
	public void checkForClick(Point point) {
		if (newGame.isClicked(point)) {
		    panel.setActiveScreen(new ConfigScreen(panel, width, height));
		} else if (loadGame.isClicked(point)) {
		    System.out.println("Fix this later");//FIXME 
		} else{
			panel.quitGame();
		}
	}
	
	/**
     * Method toString.
     * @return String
     */
	@Override
    public String toString(){
        return "Title Screen";
    }
}
