/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public abstract class Screen {
    /**
     * Field hoverPoint.
     */
    private Point hoverPoint;
    
	/**
	 * Method draw.
	 * @param g Graphics
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Method checkForClick.
	 * @param point Point
	 */
	public abstract void checkForClick(Point point);
	/**
	 *
	 * Method update.
	 */
	/*public void update() {}*/
	
	/**
	 * Method keyTyped.
	 * @param e KeyEvent
	 */
	public void keyTyped(KeyEvent e) {
	    if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
	        System.out.println("Pause");
	    }
	}
	
    /**
     * Method getHoverPoint.
    
     * @return Point */
    public Point getHoverPoint() {
        return hoverPoint;
    }

    /**
     * Method setHoverPoint.
     * @param hoverPoint Point
     */
    public void setHoverPoint(Point hoverPoint) {
        this.hoverPoint = hoverPoint;
    }
}