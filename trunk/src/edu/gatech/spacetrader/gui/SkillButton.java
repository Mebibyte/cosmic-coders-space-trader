/* Comment
 * 
 */

package edu.gatech.spacetrader.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import edu.gatech.spacetrader.main.GamePanel;


/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class SkillButton extends Button{
    /**
     * Field button.
     */
    private ImageIcon button; //buttonHovered;
    
    /**
     * Field buttonHeight.
     */
    /**
     * Field buttonWidth.
     */
    private int buttonWidth, buttonHeight;
    
    /**
     * Field y.
     */
    /**
     * Field x.
     */
    private int x, y;
    
    /**
     * Field bounds.
     */
    private Rectangle bounds;
	
    /**
     * Constructor for SkillButton.
     * @param type String
     * @param x int
     * @param y int
     */
    public SkillButton(String type, int x, int y){
        if (type.equals("+")) {
            button = new ImageIcon(getClass().getResource(
                    "/edu/gatech/spacetrader/res/plusButton.png"));
        } else if (type.equals("-")) {
            button = new ImageIcon(getClass().getResource(
                    "/edu/gatech/spacetrader/res/minusButton.png"));
        }
        
        buttonWidth = button.getIconWidth();
        buttonHeight = button.getIconHeight();
        
        this.x = x;
        this.y = y;
        
        bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
    }
    
	/**
	 * Method draw.
	 * @param g Graphics
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	@Override
    public void draw(Graphics g, GamePanel panel, int width, int height) {
	    button.paintIcon(panel, g, x, y);
	}

	/**
	 * Method drawHovered.
	 * @param g Graphics
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	@Override
	public void drawHovered(Graphics g, GamePanel panel, int width, int height) {
		// TODO Auto-generated method stub
	}

	/**
	 * Method isClicked.
	 * @param point Point
	
	 * @return boolean */
	@Override
	public boolean isClicked(Point point) {
	    return (point.x >= x && point.x <= x + buttonWidth) && 
                (point.y >= y && point.y <= y + buttonHeight);
	}

	/**
	 * Method isIn.
	 * @param p Point
	
	 * @return boolean */
	@Override
	public boolean isIn(Point p) {
		return bounds.contains(p);
	}

	/**
	 * Method getHeight.
	
	 * @return int */
	@Override
	public int getHeight() {
		return buttonHeight;
	}

}
