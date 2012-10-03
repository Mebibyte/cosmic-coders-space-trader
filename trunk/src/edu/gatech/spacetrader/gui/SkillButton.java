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
    private ImageIcon button, buttonDisabled; //buttonHovered;
    
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
     * Constructor for SkillButton.
     * @param type String
     * @param x int
     * @param y int
     */
    public SkillButton(String type, int x, int y){
        if (type.equals("+")) {
            button = new ImageIcon(getClass().getResource(
                    "/edu/gatech/spacetrader/res/plusButton.png"));
            buttonDisabled = new ImageIcon(getClass().getResource(
                    "/edu/gatech/spacetrader/res/plusButtonDisabled.png"));
        } else if (type.equals("-")) {
            button = new ImageIcon(getClass().getResource(
                    "/edu/gatech/spacetrader/res/minusButton.png"));
            buttonDisabled = new ImageIcon(getClass().getResource(
                    "/edu/gatech/spacetrader/res/minusButtonDisabled.png"));
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
	    if (disabled) {
	        buttonDisabled.paintIcon(panel, g, x, y);
	    } else button.paintIcon(panel, g, x, y);
	}
	
	/**
	 * Method isClicked.
	 * @param point Point
	
	 * @return boolean */
	@Override
	public boolean isClicked(Point point) {
	    return (point.x >= x && point.x <= x + buttonWidth) && 
                (point.y >= y && point.y <= y + buttonHeight) && !disabled;
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
    
    /**
     * Method setDisabled.
    
     * @param disabled boolean. */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    /**
     * Method toString.
    
     * @return String
     */
    @Override
    public String toString(){
        return "Skill Button with x = " + x + " and y = " + y;
    }
}
