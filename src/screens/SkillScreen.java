package screens;

import guiElements.MyButton;

import java.awt.Graphics;
import java.awt.Point;

import main.GamePanel;

public class SkillScreen extends Screen{
	private int width, height;
	private GamePanel panel;
	
	public SkillScreen(GamePanel panel, int width, int height){
		this.panel = panel;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void hoverPosition(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawString("Skill Screen", 200, 150);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkForClick(Point point) {
	}

	@Override
	public void keyPress(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyRelease(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
