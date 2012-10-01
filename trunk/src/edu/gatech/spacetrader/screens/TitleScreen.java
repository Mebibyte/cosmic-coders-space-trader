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
	private final BigButton newGame;
	
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
	}

	/**
	 * Method draw.
	 * @param g Graphics
	 */
	@Override
	public void draw(Graphics g) {
		g.drawString("Space Trader", (width >> 1) - (g.getFontMetrics().stringWidth("Space Trader") >> 1), (height >> 1) - (newGame.getHeight()));
		if (panel.isMouseOnScreen()) {
		    if (newGame.isIn(getHoverPoint())) {
		        newGame.drawHovered(g, panel, width, height);
		    } else newGame.draw(g, panel, width, height);
		} else {
		    newGame.draw(g, panel, width, height);
		}
	}
	
	/**
	 * Method checkForClick.
	 * @param point Point
	 */
	@Override
	public void checkForClick(Point point) {
		if (newGame.isClicked(point)) {
		    panel.setActiveScreen(new SkillScreen(panel, width, height));
		}
	}
}
