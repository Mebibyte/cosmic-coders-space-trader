// $codepro.audit.disable numericLiterals, multiplicationOrDivisionByPowersOf2, expressionValue

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import edu.gatech.spacetrader.gui.BigButton;
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
     * Field buttons.
     */
    private final BigButton startGame;
    
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
    private static final int NUMSKILLS = 5, DIFFHEIGHT = 350, MAXSKILL = 10,
            POSNEG = 2, MAXSKILLPOINTS = 16;
   
    /**
     * Field skillPointsUsed.
     */
    private int skillPointsUsed;
    
    /**
     * Field points.
     */
    private String points;
	
	/**
	 * Constructor for SkillScreen.
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public SkillScreen(GamePanel panel, int width, int height) {
	    skills = new int[NUMSKILLS];
	    buttons = new SkillButton[NUMSKILLS * 2];

	    final int xNeg = (width / 2) - 90;
	    final int xPos = (width / 2) + 55;
	    final int y = (height / 2) - 100;

	    for (int i = 0; i < buttons.length; i++) {
	        buttons[i] = new SkillButton(i % POSNEG == 0 ? "-" : "+",
	                i % POSNEG == 0 ? xNeg : xPos, y + (i / 2) * 50);
	        if (i % POSNEG == 0) buttons[i].setDisabled(true);
	    }
	    
	    final int buttonSep = 200;
	    
	    easy = new SelectableButton("Easy", (width / 2) - buttonSep, DIFFHEIGHT, false);
	    normal = new SelectableButton("Normal", width / 2, DIFFHEIGHT, true);
	    hard = new SelectableButton("Hard", (width / 2) + buttonSep, DIFFHEIGHT, false);
        
		this.panel = panel;
		this.width = width;
		this.height = height;
		
		startGame = new BigButton("Start", width / 2, height - 50, true);
	}

	/**
	 * Method draw.
	 * @param g Graphics
	 */
	@Override
	public void draw(Graphics g) {
	    final int minBoxWidth = 50;
	    g.drawString("Player name: ", (width / 2) - 110, (height / 2) - 150);
		g.drawString(playerName, (width / 2) - 25, (height / 2) - 150);
		g.drawRect((width / 2) - 30, (height / 2) - 165, 
		        g.getFontMetrics().stringWidth(playerName) + 10 < minBoxWidth ? 
		                minBoxWidth : g.getFontMetrics().stringWidth(playerName) + 10,
		                20);
		
		for (int i = 0; i < buttons.length; i++) {
		    buttons[i].draw(g, panel, width, height);
		}
		
		points = "Remaining Skill Points: " + (MAXSKILLPOINTS - skillPointsUsed);
		
	    g.drawString(points, (width / 2) - 
	            (g.getFontMetrics().stringWidth(points) / 2), 130);
		
		final int sep = 50;
		int x = (width / 2) - sep;
		int y = 155;
		
		for (int skill = 0; skill < NUMSKILLS; skill++) {
		    g.drawString("Skill " + (skill + 1), (width / 2) - 
		        (g.getFontMetrics().stringWidth("Skill " + (skill + 1)) / 2), y - 5);
		    
		    for (int i = 0; i < skills[skill]; i++) {
		        usedSkillPoint.paintIcon(panel, g, x, y);
		        x += usedSkillPoint.getIconWidth();
		    }
		    for (int j = skills[skill]; j < MAXSKILL; j++) {
                unusedSkillPoint.paintIcon(panel, g, x, y);
                x += unusedSkillPoint.getIconWidth();
            }
		    y += sep;
		    x = (width / 2) - sep;
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
		
		startGame.draw(g, panel, width, height);
	}

	/**
	 * Method checkForClick.
	 * @param point Point
	 */
	@Override
	public void checkForClick(Point point) {
	    for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isClicked(point)) {
                if ((i % POSNEG == 0 && skills[i / 2] > 0) 
                        || (i % POSNEG == 1 && skills[i / 2] < MAXSKILL)) {
                    skills[i / 2] += POSNEG * (i % POSNEG) - 1;
                    skillPointsUsed += POSNEG * (i % POSNEG) - 1;
                }
                
                if (skills[i / 2] > 0) {
                    buttons[(i / 2) * 2].setDisabled(false);
                } else buttons[(i / 2) * 2].setDisabled(true);
                
                if (skills[i / 2] < MAXSKILL) {
                    buttons[((i / 2) * 2) + 1].setDisabled(false);
                } else buttons[((i / 2) * 2) + 1].setDisabled(true);
            }
        }
	    resetButtons();
	    startGameDisable();
	    changeDifficulty(point);
	}
	
	/**
     * Method resetButtons.
     */
	private void resetButtons() {
	    for (int i = 1; i < NUMSKILLS * 2; i += 2) {
            if (skillPointsUsed >= MAXSKILLPOINTS) {
                buttons[i].setDisabled(true);
            } else if (skills[i / 2] < MAXSKILL) {
                buttons[i].setDisabled(false);
            }
        }
	}
	
	/**
     * Method startGameDisable.
     */
	private void startGameDisable(){
	    if (skillPointsUsed >= MAXSKILLPOINTS && playerName.length() > 0) {
            startGame.setDisabled(false);
        } else {
            startGame.setDisabled(true);
        }
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
        startGameDisable();
    }
    
    /**
     * Method toString.
    
     * @return String
     */
    @Override
    public String toString(){
        return "Skill Screen";
    }
}
