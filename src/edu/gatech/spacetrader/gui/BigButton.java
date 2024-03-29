/* Comment
 * 
 */
package edu.gatech.spacetrader.gui;

import java.awt.Color;
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
public class BigButton extends Button {
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
     * Field BUTTONSELECTED.
     */
    private static final ImageIcon BUTTONSELECTED = new ImageIcon(
            BigButton.class.getResource(
                    "/edu/gatech/spacetrader/res/buttonSelected.png"));
	
	/**
	 * Field text.
	 */
	private final String text;
	
	/**
	 * Field buttonHeight, buttonWidth.
	 */
	private final int buttonWidth, buttonHeight;
	
	/**
	 * Field y, x.
	 */
	private final int x, y;
	
	/**
     * Field fontSize.
     */
	private static final int FONTSIZE = 25, FONTY = 3;
	
	/**
	 * Field bounds.
	 */
	private final Rectangle bounds;
	
	/**
     * Field disabled, hovered, selected
     */
	private boolean disabled, hovered, selected;
	
	/**
	 * Constructor for BigButton.
	 * @param text String
	 * @param x int
	 * @param y int
	 */
	public BigButton(String text, int x, int y) {
		this(text, x, y, false, false);
	}
	
	/**
     * Constructor for BigButton.
     * @param text String
     * @param x int
     * @param y int
     * @param disabled boolean
     */
    public BigButton(String text, int x, int y, boolean disabled) {
        this(text, x, y, disabled, false);
    }
	
	/**
     * Constructor for BigButton.
     * @param text String
     * @param x int
     * @param y int
     * @param disabled boolean
     * @param selected boolean
     */
	public BigButton(String text, int x, int y, boolean disabled, boolean selected) {
	    this.text = text;
        buttonWidth = BUTTON.getIconWidth();
        buttonHeight = BUTTON.getIconHeight();
        this.x = x - (buttonWidth >> 1);
        this.y = y - (buttonHeight >> 1);
        bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
        this.disabled = disabled;
        this.selected = selected;
	}
	
	/**
	 * Method draw.
	 * @param g Graphics
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public void draw(Graphics g, GamePanel panel, int width, int height){
		if (disabled) {
		    BUTTONDISABLED.paintIcon(panel, g, x, y);
		    g.setColor(Color.white);
		} else if (selected) {
		    BUTTONSELECTED.paintIcon(panel, g, x, y);
            g.setColor(Color.white);
        } else if (hovered) {
		    BUTTONHOVERED.paintIcon(panel, g, x, y);
		    g.setColor(Color.white);
		} else {
			BUTTON.paintIcon(panel, g, x, y);
			g.setColor(Color.black);
		}
		
		final Font default_font = g.getFont();
        g.setFont(new Font("serif", Font.PLAIN, FONTSIZE));
		g.drawString(text, 
				x + (buttonWidth >> 1) - (((g.getFontMetrics()).stringWidth(text)) >> 1), 
				y + (buttonHeight >> 1) + FONTY);
		g.setFont(default_font);
	}
	
	/**
	 * Method isClicked.
	 * @param point Point
	 * @return boolean
	 */
	public boolean isClicked(Point point){
		return (point.x >= x && point.x <= x + buttonWidth) && 
				(point.y >= y && point.y <= y + buttonHeight) && !disabled;
	}
	
	/**
	 * Method isIn.
	 * @param p Point
	 * @return boolean
	 */
	public boolean isIn(Point p){
	    if (p == null) return false;
		return bounds.contains(p);
	}
	
	/**
	 * Method getHeight.
	 * @return int
	 */
	public int getHeight(){
		return buttonHeight;
	}
	
	/**
	 * Method getBounds.
	 * @return Rectangle 
	 */
	public Rectangle getBounds(){
	    return bounds;
	}
	
	/**
     * Method setDisabled.
     * @param disabled boolean
     */
	public void setDisabled(boolean disabled){
	    this.disabled = disabled;
	}
	
	/**
     * Method toString.
     * @return String
     */
	public String toString(){
	    return "Button with x = " + x + " and y = " + y;
	}

	/**
     * Method setHovered.
     * @param p Point
     */
    public void setHovered(Point p) {
        if (bounds.contains(p)) {
            hovered = true;
        } else hovered = false;
    }
    
    /**
     * Method isSelected.
     * @return boolean
     */
    public boolean isSelected(){
        return selected;
    }
    
    /**
     * Method setSelected.
     * @param selected boolean.
     */
    public void setSelected(boolean selected){
        this.selected = selected;
    }

    /**
     * Method getWidth.
     * Get's the width of the button image.
     * @return int width.
     */
    public static int getWidth() {
        return BUTTON.getIconWidth();
    }
}
