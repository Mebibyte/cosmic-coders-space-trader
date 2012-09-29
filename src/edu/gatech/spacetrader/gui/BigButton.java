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
	private ImageIcon button; //buttonHovered;
	
	private String text;
	
	private int buttonWidth, buttonHeight;
	
	private int x, y;
	
	private Rectangle bounds;
	
	public BigButton(String text, int x, int y){
		button = new ImageIcon(getClass().getResource(
		        "/edu/gatech/spacetrader/res/button.png"));
		//buttonHovered = new ImageIcon(getClass().getResource("/res/buttonHovered.png"));
		this.text = text;
		buttonWidth = button.getIconWidth();
		buttonHeight = button.getIconHeight();
		this.x = (x >> 1) - (buttonWidth >> 1);
		this.y = (y - (buttonHeight >> 1));
		bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
	}
	
	public void draw(Graphics g, GamePanel panel, int width, int height){
        g.setFont(new Font("serif", Font.PLAIN, 25));
		button.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x + (buttonWidth >> 1) - (((g.getFontMetrics()).stringWidth(text)) >> 1), 
				y + (buttonHeight >> 1) + 3);
	}
	
	public void drawHovered(Graphics g, GamePanel panel, int width, int height){
		/*g.setFont(new Font("serif", Font.PLAIN, 25));
		buttonHovered.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x+(buttonWidth/2)-((g.getFontMetrics()).stringWidth(text))/2, 
				y+(buttonHeight/2)+3);*/
	}
	
	public boolean isClicked(Point point){
		return (point.x >= x && point.x <= x + buttonWidth) && 
				(point.y >= y && point.y <= y + buttonHeight);
	}
	
	public boolean isIn(Point p){
		return bounds.contains(p);
	}
	
	public int getHeight(){
		return buttonHeight;
	}
}
