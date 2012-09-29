/* Comment
 * 
 */

package screens;

import gui.SkillButton;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import javax.swing.ImageIcon;

import main.GamePanel;

public class SkillScreen extends Screen{
    private SkillButton[] buttons;
    
    private int[] skills;
    
    private final ImageIcon unusedSkillPoint, usedSkillPoint;
	
    private int width, height;
	
    private GamePanel panel;
	
	public SkillScreen(GamePanel panel, int width, int height){
	    skills = new int[3];
	    buttons = new SkillButton[6];
	    int xNeg = (width / 2) - 90;
	    int xPos = (width / 2) + 55;
	    int y = (width / 2);
	    buttons[0] = new SkillButton("-", xNeg, y);
	    buttons[1] = new SkillButton("+", xPos, y);
	    buttons[2] = new SkillButton("-", xNeg, y + 50);
        buttons[3] = new SkillButton("+", xPos, y + 50);
        buttons[4] = new SkillButton("-", xNeg, y + 100);
        buttons[5] = new SkillButton("+", xPos, y + 100);
		this.panel = panel;
		this.width = width;
		this.height = height;
		unusedSkillPoint = new ImageIcon(getClass().getResource("/res/unusedSkillPoint.png"));
		usedSkillPoint = new ImageIcon(getClass().getResource("/res/usedSkillPoint.png"));
	}

	@Override
	public void draw(Graphics g) {
		g.drawString("Skill Screen", 200, 150);
		for (int i = 0; i < buttons.length; i++) {
		    buttons[i].draw(g, panel, width, height);
		}
		int x = width / 2 - 50;
		int y = 255;
		for (int skill = 0; skill < skills.length; skill++) {
		    for (int i = 0; i < skills[skill]; i++) {
		        usedSkillPoint.paintIcon(panel, g, x, y);
		        x += 10;
		    }
		    for (int j = skills[skill]; j < 10; j++) {
                unusedSkillPoint.paintIcon(panel, g, x, y);
                x+=10;
            }
		    y += 50;
		    x = width / 2 - 50;
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
