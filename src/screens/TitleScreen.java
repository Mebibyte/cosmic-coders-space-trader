package screens;

import guiElements.BigButton;

import java.awt.Graphics;
import java.awt.Point;

import main.GamePanel;

public class TitleScreen extends Screen{
	private BigButton newGame;
	private int width, height;
	private GamePanel panel;
	
	public TitleScreen(GamePanel panel, int width, int height){
		this.panel = panel;
		this.width = width;
		this.height = height;
		newGame = new BigButton("New Game", width, height/2);
	}
	
	@Override
	public void hoverPosition(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawString("Space Trader", 200, 150);
		newGame.draw(g, panel, width, height);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkForClick(Point point) {
		if (newGame.checkForClick(point)) panel.setActiveScreen(new SkillScreen(panel, width, height));
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
