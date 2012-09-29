/* Comment
 * 
 */

package edu.gatech.spacetrader.gui;

import edu.gatech.spacetrader.main.GamePanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class SkillScreen extends Screen{
    private SkillButton[] buttons;
    
    private int[] skills;
    
    private final ImageIcon unusedSkillPoint = new ImageIcon(
            getClass().getResource(
                    "/edu/gatech/spacetrader/res/unusedSkillPoint.png"));

    private final ImageIcon usedSkillPoint = new ImageIcon(
            getClass().getResource(
                    "/edu/gatech/spacetrader/res/usedSkillPoint.png"));;
	
    private int width, height;
	
    private GamePanel panel;
    
    private String playerName = "";
	
	public SkillScreen(GamePanel panel, int width, int height){
	    skills = new int[3];
	    buttons = new SkillButton[6];
	    
	    int buttonWidth = 40;
	    int skillPointBarWidth = 50;
	    int xNeg = (width >> 1) - (buttonWidth + skillPointBarWidth);
	    int xPos = (width >> 1) + skillPointBarWidth + 5;
	    int y = (height >> 1);
	    
	    for (int i = 0; i < buttons.length; i++) {
	        buttons[i] = new SkillButton(i % 2 == 0 ? "-" : "+",
	                i % 2 == 0 ? xNeg : xPos, y + (i >> 1) * 50);
	    }
        
		this.panel = panel;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		g.drawString("Skill Screen", width >> 1, height >> 1);
		g.drawString("Player name: ", (width >> 1) - 100, (height >> 1) - 50);
		g.drawString(playerName, (width >> 1) - 25, (height >> 1) - 50);
		
		for (int i = 0; i < buttons.length; i++) {
		    buttons[i].draw(g, panel, width, height);
		}
		
		int x = (width >> 1) - 50;
		int y = 255;
		
		for (int skill = 0; skill < skills.length; skill++) {
		    for (int i = 0; i < skills[skill]; i++) {
		        usedSkillPoint.paintIcon(panel, g, x, y);
		        x += usedSkillPoint.getIconWidth();
		    }
		    for (int j = skills[skill]; j < 10; j++) {
                unusedSkillPoint.paintIcon(panel, g, x, y);
                x += unusedSkillPoint.getIconWidth();
            }
		    y += 50;
		    x = (width >> 1) - 50;
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
                skills[i >> 1] += 2 * (i % 2) - 1;
            }
        }
	}

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE && playerName.length() > 0) {
            playerName = playerName.substring(0, playerName.length() - 1);
        } else if (!e.isActionKey()) playerName += e.getKeyChar();
    }
}
