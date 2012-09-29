/* Comment
 * 
 */

package edu.gatech.spacetrader.gui;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Screen {
    private Point hoverPoint;
    
	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public abstract void checkForClick(Point point);
	
	public void keyPress(int keyCode) {
	    System.out.println(keyCode);
	}
	
	public void keyRelease(int keyCode) {
	       System.out.println(keyCode);

	}

    public Point getHoverPoint() {
        return hoverPoint;
    }

    public void setHoverPoint(Point hoverPoint) {
        this.hoverPoint = hoverPoint;
    }
}