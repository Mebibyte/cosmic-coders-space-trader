/* Comment
 * 
 */

package screens;

import gui.SkillButton;

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
	    int xNeg = 300;
	    int xPos = 650;
	    int yNeg = 250;
	    int yPos = 250;
	    buttons[0] = new SkillButton("-", xNeg, yNeg);
	    buttons[1] = new SkillButton("+", xPos, yPos);
	    buttons[2] = new SkillButton("-", xNeg, yNeg + 50);
        buttons[3] = new SkillButton("+", xPos, yPos + 50);
        buttons[4] = new SkillButton("-", xNeg, yNeg + 100);
        buttons[5] = new SkillButton("+", xPos, yPos + 100);
		this.panel = panel;
		this.width = width;
		this.height = height;
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
            if (buttons[i].isClicked(point)) {
                skills[i / 2] += 2 * (i % 2) - 1;
            }
        }
	    System.out.println(Arrays.toString(skills));
	}
}
