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


/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class BigButton extends Button{
	/**
	 * Field BUTTON.
	 */
	private static final ImageIcon BUTTON = new ImageIcon(
	        BigButton.class.getResource("/edu/gatech/spacetrader/res/button.png"));
	
	/**
	 * Field BUTTONHOVERED.
	 */
	private static final ImageIcon BUTTONHOVERED = new ImageIcon(
	        BigButton.class.getResource("/edu/gatech/spacetrader/res/buttonHovered.png"));
	
	/**
     * Field BUTTONDISABLED.
     */
    private static final ImageIcon BUTTONDISABLED = new ImageIcon(
           BigButton.class.getResource("/edu/gatech/spacetrader/res/buttonDisabled.png"));
	
	/**
	 * Field text.
	 */
	private final String text;
	
	/**
	 * Field buttonHeight.
	 */
	/**
	 * Field buttonWidth.
	 */
	private final int buttonWidth, buttonHeight;
	
	/**
	 * Field y.
	 */
	/**
	 * Field x.
	 */
	private final int x, y;
	
	/**
	 * Field bounds.
	 */
	private final Rectangle bounds;
	
	/**
     * Field disabled.
     */
	private boolean disabled;
	
	/**
	 * Constructor for BigButton.
	 * @param text String
	 * @param x int
	 * @param y int
	 */
	public BigButton(String text, int x, int y){
		this(text, x, y, false);
	}
	
	public BigButton(String text, int x, int y, boolean disabled) {
	    this.text = text;
        buttonWidth = BUTTON.getIconWidth();
        buttonHeight = BUTTON.getIconHeight();
        this.x = x - (buttonWidth >> 1);
        this.y = y - (buttonHeight >> 1);
        bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
        this.disabled = disabled;
	}
	
	/**
	 * Method draw.
	 * @param g Graphics
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public void draw(Graphics g, GamePanel panel, int width, int height){
        g.setFont(new Font("serif", Font.PLAIN, 25));
		if (disabled) {
		    BUTTONDISABLED.paintIcon(panel, g, x, y);
		} else BUTTON.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x + (buttonWidth >> 1) - (((g.getFontMetrics()).stringWidth(text)) >> 1), 
				y + (buttonHeight >> 1) + 3);
	}
	
	/**
	 * Method drawHovered.
	 * @param g Graphics
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public void drawHovered(Graphics g, GamePanel panel, int width, int height){
		g.setFont(new Font("serif", Font.PLAIN, 25));
		BUTTONHOVERED.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x + (buttonWidth >> 1) - (((g.getFontMetrics()).stringWidth(text)) >> 1), 
				y + (buttonHeight >> 1) + 3);
	}
	
	/**
	 * Method isClicked.
	 * @param point Point
	
	 * @return boolean */
	public boolean isClicked(Point point){
		return (point.x >= x && point.x <= x + buttonWidth) && 
				(point.y >= y && point.y <= y + buttonHeight);
	}
	
	/**
	 * Method isIn.
	 * @param p Point
	
	 * @return boolean */
	public boolean isIn(Point p){
	    if (p == null) return false;
		return bounds.contains(p);
	}
	
	/**
	 * Method getHeight.
	
	 * @return int */
	public int getHeight(){
		return buttonHeight;
	}
	
	/**
	 * Method getBounds.
	
	 * @return Rectangle */
	public Rectangle getBounds(){
	    return bounds;
	}
	
	public void setDisabled(boolean disabled){
	    this.disabled = disabled;
	}
}
