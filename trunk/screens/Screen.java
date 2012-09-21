package screens;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Screen {
	public abstract void hoverPosition(Point point);
	public abstract void draw(Graphics g);
	public abstract void update();
	public abstract void checkForClick(Point point);
	public abstract void keyPress(int keyCode);
	public abstract void keyRelease(int keyCode);
}