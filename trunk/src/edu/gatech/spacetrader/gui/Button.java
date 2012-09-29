/* Comment
 * 
 */

package edu.gatech.spacetrader.gui;

import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.spacetrader.main.GamePanel;


public abstract class Button{
	public abstract void draw(Graphics g, GamePanel panel, int width, int height);
	
	public abstract void drawHovered(Graphics g, GamePanel panel, int width, int height);
	
	public abstract boolean isClicked(Point point);
	
	public abstract boolean isIn(Point p);
	
	public abstract int getHeight();
}
