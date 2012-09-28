package screens;

import guiElements.SkillButton;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import main.GamePanel;

public class SkillScreen extends Screen{
    private SkillButton[] buttons;
    private int[] skills;
	private int width, height;
	private GamePanel panel;
	
	public SkillScreen(GamePanel panel, int width, int height){
	    skills = new int[3];
	    buttons = new SkillButton[6];
	    buttons[0] = new SkillButton("-", 300, 250);
	    buttons[1] = new SkillButton("+", 650, 250);
	    buttons[2] = new SkillButton("-", 300, 300);
        buttons[3] = new SkillButton("+", 650, 300);
        buttons[4] = new SkillButton("-", 300, 350);
        buttons[5] = new SkillButton("+", 650, 350);
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
		for (int i = 0; i < buttons.length; i++) {
		    buttons[i].draw(g, panel, width, height);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkForClick(Point point) {
	    for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].checkForClick(point)) {
                skills[i/2] += 2 * (i%2) - 1;
            }
        }
	    System.out.println(Arrays.toString(skills));
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
