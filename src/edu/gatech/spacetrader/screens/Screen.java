// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals
/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
    private JFileChooser fc = new JFileChooser();
    
    
    /**
     * 
     */
    private SaveFileWriter saveFileWriter = new SaveFileWriter();
    
    
    /**
     * Field paused.
     */
    private boolean paused;
    
    /**
     * Field saveGame and quitGame buttons.
     */
    private final BigButton saveGame = new BigButton("Save Game", (SpaceTrader.WIDTH / 2),
            SpaceTrader.HEIGHT / 2), 
            quitGame = new BigButton("Quit Game", (SpaceTrader.WIDTH / 2),
                    (SpaceTrader.HEIGHT / 2) + saveGame.getHeight());
    
	/**
	 * Method draw.
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
	    if (paused) {
	        g.setColor(new Color(0, 0, 0, 150));
	        g.fillRect(0, 0, SpaceTrader.WIDTH, SpaceTrader.HEIGHT);
	        g.setColor(Color.WHITE);
	        g.drawString("PAUSED", (SpaceTrader.WIDTH / 2)
	                - (g.getFontMetrics().stringWidth("PAUSED") / 2),
	                (SpaceTrader.HEIGHT / 2) - saveGame.getHeight());
	        saveGame.draw(g, SpaceTrader.GAME_PANEL, SpaceTrader.WIDTH,
	                SpaceTrader.HEIGHT);
	        quitGame.draw(g, SpaceTrader.GAME_PANEL, SpaceTrader.WIDTH,
	                SpaceTrader.HEIGHT);
	    }
	}
	
	/**
	 * Method checkForClick.
	 * @param point Point
	 * @throws IOException 
	 */
	public void checkForClick(Point point){
	    if (paused) {
	        if (saveGame.isClicked(point)) {
	            //System.out.println("Save Game");
	        	
	        	try {
					saveFile(SpaceTrader.GAME_PANEL.getLocalGameScreen());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	            
	        } else if (quitGame.isClicked(point)) {
	            System.exit(0);
	        }
	    }
	}
	
	private void saveFile(GameScreen gs) throws IOException{
		int choice = fc.showSaveDialog(new JPanel());
		
		if(choice == JFileChooser.APPROVE_OPTION){
			System.out.println(fc.getSelectedFile().getAbsolutePath());
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
}