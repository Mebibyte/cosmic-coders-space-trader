/* Comment
 * 
 */

package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import main.GamePanel;

public class SkillButton extends Button{
    private ImageIcon button; //buttonHovered;
    
    private int buttonWidth, buttonHeight;
    
    private int x, y;
    
    private Rectangle bounds;
	
    public SkillButton(String type, int x, int y){
        if (type.equals("+")) {
            button = new ImageIcon(getClass().getResource("/res/plusButton.png"));
        } else if (type.equals("-")) {
            button = new ImageIcon(getClass().getResource("/res/minusButton.png"));
        }
        
        buttonWidth = button.getIconWidth();
        buttonHeight = button.getIconHeight();
        
        this.x = x;
        this.y = y;
        
        bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
    }
    
	@Override
    public void draw(Graphics g, GamePanel panel, int width, int height) {
	    button.paintIcon(panel, g, x, y);
	}

	@Override
	public void drawHovered(Graphics g, GamePanel panel, int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isClicked(Point point) {
	    return (point.x >= x && point.x <= x + buttonWidth) && 
                (point.y >= y && point.y <= y + buttonHeight);
	}

	@Override
	public boolean isIn(Point p) {
		return bounds.contains(p);
	}

	@Override
	public int getHeight() {
		return buttonHeight;
	}

}
