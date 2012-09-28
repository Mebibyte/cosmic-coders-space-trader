package guiElements;

import java.awt.*;

import main.GamePanel;

public abstract class Button{
	public abstract void draw(Graphics g, GamePanel panel, int width, int height);
	public abstract void drawHovered(Graphics g, GamePanel panel, int width, int height);
	public abstract boolean checkForClick(Point point);
	public abstract boolean contains(Point p);
	public abstract int getHeight();
}
