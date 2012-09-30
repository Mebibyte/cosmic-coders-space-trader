/* Comment
 * 
 */
package edu.gatech.spacetrader.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import edu.gatech.spacetrader.main.GamePanel;


public class BigButton extends Button{
	private static final ImageIcon BUTTON = new ImageIcon(
	        BigButton.class.getResource("/edu/gatech/spacetrader/res/button.png"));
	
	private static final ImageIcon BUTTONHOVERED = new ImageIcon(
	        BigButton.class.getResource("/edu/gatech/spacetrader/res/buttonHovered.png"));
	
	private String text;
	
	private int buttonWidth, buttonHeight;
	
	private int x, y;
	
	private Rectangle bounds;
	
	public BigButton(String text, int x, int y){
		this.text = text;
		buttonWidth = BUTTON.getIconWidth();
		buttonHeight = BUTTON.getIconHeight();
		this.x = (x >> 1) - (buttonWidth >> 1);
		this.y = (y - (buttonHeight >> 1));
		bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
	}
	
	public void draw(Graphics g, GamePanel panel, int width, int height){
        g.setFont(new Font("serif", Font.PLAIN, 25));
		BUTTON.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x + (buttonWidth >> 1) - (((g.getFontMetrics()).stringWidth(text)) >> 1), 
				y + (buttonHeight >> 1) + 3);
	}
	
	public void drawHovered(Graphics g, GamePanel panel, int width, int height){
		g.setFont(new Font("serif", Font.PLAIN, 25));
		BUTTONHOVERED.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x + (buttonWidth >> 1) - (((g.getFontMetrics()).stringWidth(text)) >> 1), 
				y + (buttonHeight >> 1) + 3);
	}
	
	public boolean isClicked(Point point){
		return (point.x >= x && point.x <= x + buttonWidth) && 
				(point.y >= y && point.y <= y + buttonHeight);
	}
	
	public boolean isIn(Point p){
	    if (p == null) return false;
		return bounds.contains(p);
	}
	
	public int getHeight(){
		return buttonHeight;
	}
	
	public Rectangle getBounds(){
	    return bounds;
	}
}
