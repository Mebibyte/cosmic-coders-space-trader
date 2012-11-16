// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals
/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import edu.gatech.spacetrader.fileio.SaveFileWriter;
import edu.gatech.spacetrader.gui.BigButton;
import edu.gatech.spacetrader.main.SpaceTrader;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class Screen {
    /**
     * Field hoverPoint.
     */
    private Point hoverPoint;
    
    /**
     * 
     */
    private final JFileChooser fc = new JFileChooser();

    /**
     * 
     */
    private final SaveFileWriter saveFileWriter = new SaveFileWriter();

    /**
     * Field paused.
     */
    private boolean paused;
    
    /**
     * Field pauseButton.
     */
    private final Rectangle pauseButton = new Rectangle(SpaceTrader.WIDTH
            - PAUSE.getIconWidth(), 0, PAUSE.getIconWidth(), PAUSE.getIconHeight());
    
    /**
     * Field saveGame and quitGame buttons.
     */
    private final BigButton saveGame = new BigButton("Save Game", (SpaceTrader.WIDTH / 2),
            SpaceTrader.HEIGHT / 2), 
            quitGame = new BigButton("Quit Game", (SpaceTrader.WIDTH / 2),
                    (SpaceTrader.HEIGHT / 2) + saveGame.getHeight());
    
    /**
     * Field PAUSE.
     */
    private static final ImageIcon PAUSE = new ImageIcon(GameScreen.class
            .getResource("/edu/gatech/spacetrader/res/pause.png"));
    
	/**
	 * Method draw.
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
	    if (paused) {
	        g.setColor(new Color(0, 0, 0, 150));
	        g.fillRect(0, 0, SpaceTrader.WIDTH, SpaceTrader.HEIGHT);
	        g.setColor(Color.WHITE);
	        final Font defaultFont = g.getFont();
	        g.setFont(new Font("serif", Font.PLAIN, 25));
	        g.drawString("Paused", (SpaceTrader.WIDTH / 2)
	                - (g.getFontMetrics().stringWidth("Paused") / 2),
	                (SpaceTrader.HEIGHT / 2) - saveGame.getHeight());
	        saveGame.draw(g, SpaceTrader.GAME_PANEL, SpaceTrader.WIDTH,
	                SpaceTrader.HEIGHT);
	        quitGame.draw(g, SpaceTrader.GAME_PANEL, SpaceTrader.WIDTH,
	                SpaceTrader.HEIGHT);
	        g.setFont(defaultFont);
	    }
        PAUSE.paintIcon(SpaceTrader.GAME_PANEL, g,
                SpaceTrader.WIDTH - PAUSE.getIconWidth(), 0);
	}
	
	/**
	 * Method checkForClick.
	 * @param point Point
	 * @throws IOException 
	 */
	public void checkForClick(Point point){
	    if (paused) {
	        if (saveGame.isClicked(point)) {
	            saveFile(SpaceTrader.GAME_PANEL.getLocalGameScreen());
	        } else if (quitGame.isClicked(point)) {
	            SpaceTrader.GAME_PANEL.setActiveScreen(
	                    new TitleScreen(SpaceTrader.GAME_PANEL,
	                    SpaceTrader.WIDTH, SpaceTrader.HEIGHT));
	        }
	    }
	    if (pauseButton.contains(point)) {
            paused = !paused;
	    }
	}
	
	/**
	 * Saves the game.
	 * @param gs Game screen
	 * @throws IOException
	 */
	private void saveFile(GameScreen gs){
		final int choice = fc.showSaveDialog(new JPanel());
		
		if(choice == JFileChooser.APPROVE_OPTION){
			saveFileWriter.generateSaveFile(fc.getSelectedFile().getAbsolutePath(), gs);
		}
	}
	
	/**
	 * Method keyTyped.
	 * @param e KeyEvent
	 */
	public void keyTyped(KeyEvent e) {
	    if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
	        paused = !paused;
	    }
	}
	
    /**
     * Method getHoverPoint.
     * @return Point
     */
    public Point getHoverPoint() {
        return hoverPoint;
    }

    /**
     * Method setHoverPoint.
     * @param hoverPoint Point
     */
    public void setHoverPoint(Point hoverPoint) {
        this.hoverPoint = hoverPoint;
        saveGame.setHovered(hoverPoint);
        quitGame.setHovered(hoverPoint);
    }

    /**
     * Method tick.
     * Updates objects on the screen.
     */
    public void tick() { // $codepro.audit.disable emptyMethod
        
    }
    
    /**
     * String representation required by CodePro.
     * @return String
     */
    public String toString() {
        return "Screen";
    }
    
    /**
     * Checks if paused.
     * 
     * @return paused
     */
    public boolean isPaused() {
        return paused;
    }
}