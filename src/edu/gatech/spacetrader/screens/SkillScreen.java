/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import edu.gatech.spacetrader.gui.SelectableButton;
import edu.gatech.spacetrader.gui.SkillButton;
import edu.gatech.spacetrader.main.GamePanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


/**
 * @author Glenn
 * @version $Revision: 1.0 $Z
 */
public class SkillScreen extends Screen{
    /**
     * Field buttons.
     */
    private final SkillButton[] buttons;
    
    /**
     * Field skills.
     */
    private final int[] skills;
    
    /**
     * Field unusedSkillPoint.
     */
    private final ImageIcon unusedSkillPoint = new ImageIcon(
            getClass().getResource(
                    "/edu/gatech/spacetrader/res/unusedSkillPoint.png"));

    /**
     * Field usedSkillPoint.
     */
    private final ImageIcon usedSkillPoint = new ImageIcon(
            getClass().getResource(
                    "/edu/gatech/spacetrader/res/usedSkillPoint.png"));
    
    /**
     * Field easy.
     */
    /**
     * Field normal.
     */
    /**
     * Field hard.
     */
    private final SelectableButton easy, normal, hard;
	
    /**
     * Field height.
     */
    /**
     * Field width.
     */
    private final int width, height;
	
    /**
     * Field panel.
     */
    private final GamePanel panel;
    
    /**
     * Field playerName.
     */
    private String playerName = "";
    
    /**
     * Field numSkill.
     */
    /**
     * Field numSkill.
     */
    private static final int NUMSKILLS = 3, DIFFHEIGHT = 350, MAXSKILL = 10,
            POSNEG = 2;
	
	/**
	 * Constructor for SkillScreen.
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public SkillScreen(GamePanel panel, int width, int height) {
	    skills = new int[NUMSKILLS];
	    buttons = new SkillButton[6];

	    final int xNeg = (width >> 1) - 90;
	    final int xPos = (width >> 1) + 55;
	    final int y = (height >> 1) - 100;

	    for (int i = 0; i < buttons.length; i++) {
	        buttons[i] = new SkillButton(i % POSNEG == 0 ? "-" : "+",
	                i % POSNEG == 0 ? xNeg : xPos, y + (i >> 1) * 50);
	    }
	    
	    final int buttonSep = 200;
	    
	    easy = new SelectableButton("Easy", (width >> 1) - buttonSep, DIFFHEIGHT, false);
	    normal = new SelectableButton("Normal", width >> 1, DIFFHEIGHT, true);
	    hard = new SelectableButton("Hard", (width >> 1) + buttonSep, DIFFHEIGHT, false);
        
		this.panel = panel;
		this.width = width;
		this.height = height;
	}

	/**
	 * Method draw.
	 * @param g Graphics
	 */
	@Override
	public void draw(Graphics g) {
	    final int minBoxWidth = 50;
	    g.drawString("Player name: ", (width >> 1) - 110, (height >> 1) - 150);
		g.drawString(playerName, (width >> 1) - 25, (height >> 1) - 150);
		g.drawRect((width >> 1) - 30, (height >> 1) - 165, 
		        g.getFontMetrics().stringWidth(playerName) + 10 < minBoxWidth ? 
		                minBoxWidth : g.getFontMetrics().stringWidth(playerName) + 10,
		                20);
		
		for (int i = 0; i < buttons.length; i++) {
		    buttons[i].draw(g, panel, width, height);
		}
		
		final int sep = 50;
		int x = (width >> 1) - sep;
		int y = 155;
		
		for (int skill = 0; skill < NUMSKILLS; skill++) {
		    g.drawString("Skill " + (skill + 1), (width >> 1) - 
		        (g.getFontMetrics().stringWidth("Skill " + (skill + 1)) >> 1), y - 5);
		    
		    for (int i = 0; i < skills[skill]; i++) {
		        usedSkillPoint.paintIcon(panel, g, x, y);
		        x += usedSkillPoint.getIconWidth();
		    }
		    for (int j = skills[skill]; j < MAXSKILL; j++) {
                unusedSkillPoint.paintIcon(panel, g, x, y);
                x += unusedSkillPoint.getIconWidth();
            }
		    y += sep;
		    x = (width >> 1) - sep;
		}
		
		if (easy.isSelected()) {
		    easy.drawSelected(g, panel, width, height);
		} else easy.draw(g, panel, width, height);
		
		if (normal.isSelected()) {
		    normal.drawSelected(g, panel, width, height);
		} else normal.draw(g, panel, width, height);
		
		if (hard.isSelected()) {
		    hard.drawSelected(g, panel, width, height);
		} else hard.draw(g, panel, width, height);
	}

	/**
	 * Method checkForClick.
	 * @param point Point
	 */
	@Override
	public void checkForClick(Point point) {
	    for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isClicked(point)) {
                if ((i % POSNEG == 0 && skills[i >> 1] > 0) 
                        || (i % POSNEG == 1 && skills[i >> 1] < MAXSKILL)) {
                    skills[i >> 1] += POSNEG * (i % POSNEG) - 1;
                }
            }
        }
	    changeDifficulty(point);
	}
	
	/**
     * Method changeDifficulty.
     * @param p Point
     */
	private void changeDifficulty(Point p) {
	    if (!easy.isSelected() && easy.isIn(p)) {
            easy.setSelected(true);
            normal.setSelected(false);
            hard.setSelected(false);
        } else if (!normal.isSelected() && normal.isIn(p)) {
            easy.setSelected(false);
            normal.setSelected(true);
            hard.setSelected(false);
        } else if (!hard.isSelected() && hard.isIn(p)) {
            easy.setSelected(false);
            normal.setSelected(false);
            hard.setSelected(true);
        }
	}

    /**
     * Method keyTyped.
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            if (playerName.length() > 0) {
                playerName = playerName.substring(0, playerName.length() - 1);
            }
        } else if (!e.isActionKey() && playerName.length() < 20) {
            playerName += e.getKeyChar();
        }
    }
}
