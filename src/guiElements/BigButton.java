package guiElements;

import java.awt.*;
import javax.swing.*;

import main.GamePanel;

public class BigButton extends Button{
	private ImageIcon button, buttonHovered;
	private String text;
	private int buttonWidth, buttonHeight;
	private int x, y;
	private Rectangle bounds;
	
	public BigButton(String text, int x, int y){
		button = new ImageIcon(getClass().getResource("/res/button.png"));
		//buttonHovered = new ImageIcon(getClass().getResource("/res/buttonHovered.png"));
		this.text = text;
		buttonWidth = button.getIconWidth();
		buttonHeight = button.getIconHeight();
		this.x = (x/2) - (buttonWidth / 2);
		this.y = (y - buttonHeight / 2);
		bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
	}
	
	public void draw(Graphics g, GamePanel panel, int width, int height){
        g.setFont(new Font("serif", Font.PLAIN, 25));
		button.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x+(buttonWidth/2)-((g.getFontMetrics()).stringWidth(text))/2, 
				y+(buttonHeight/2)+3);
	}
	
	public void drawHovered(Graphics g, GamePanel panel, int width, int height){
		g.setFont(new Font("serif", Font.PLAIN, 25));
		buttonHovered.paintIcon(panel, g, x, y);
		g.drawString(text, 
				x+(buttonWidth/2)-((g.getFontMetrics()).stringWidth(text))/2, 
				y+(buttonHeight/2)+3);
	}
	
	public boolean checkForClick(Point point){
		if((point.x >= x && point.x <= x + buttonWidth) && 
				(point.y >= y && point.y <= y + buttonHeight))
			return true;
		else
			return false;
	}
	
	public boolean contains(Point p){
		return bounds.contains(p);
	}
	
	public int getHeight(){
		return buttonHeight;
	}
}
