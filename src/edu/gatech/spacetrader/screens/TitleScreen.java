/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import edu.gatech.spacetrader.gui.BigButton;
import edu.gatech.spacetrader.main.GamePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;


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
	 * Field bg.
	 */
	private Image bg; // $codepro.audit.disable variableShouldBeFinal
	
	/**s
	 * Constructor for TitleScreen.
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public TitleScreen(GamePanel panel, int width, int height) {
		this.panel = panel;
		this.width = width;
		this.height = height;
		newGame = new BigButton("New Game", width >> 1, height >> 1);
		loadGame = new BigButton("Load Game", width >> 1,
				(height >> 1) + newGame.getHeight(), true);
		quit = new BigButton("Quit", width >> 1,
				(height >> 1) + newGame.getHeight() + loadGame.getHeight());
		
		try {
            bg = ImageIO.read(TitleScreen.class.getResource(
                        "/edu/gatech/spacetrader/res/space.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Method draw.
	 * @param g Graphics
	 */
	@Override
	public void draw(Graphics g) {
	    g.drawImage(bg, 0, 0, null);
	    g.setColor(Color.WHITE);
	    g.drawString("Space Trader",
		        (width >> 1) - (g.getFontMetrics().stringWidth("Space Trader") >> 1),
		        (height >> 1) - (newGame.getHeight()));
	    g.setColor(Color.BLACK);
	    
		newGame.draw(g, panel, width, height);
        loadGame.draw(g, panel, width, height);
		quit.draw(g, panel, width, height);
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
		    System.out.println("Fix this later"); //FIXME 
		} else if (quit.isClicked(point)) {
			panel.quitGame();
		}
	}
	
	/**
     * Method setHoverPoint.
     * @param p Point
     */
	@Override
	public void setHoverPoint(Point p) {
	    super.setHoverPoint(p);
        newGame.setHovered(p);
        loadGame.setHovered(p);
        quit.setHovered(p);
    }
	
	/**
     * Method toString.
    
     * @return String */
	@Override
    public String toString(){
        return "Title Screen";
    }
}
