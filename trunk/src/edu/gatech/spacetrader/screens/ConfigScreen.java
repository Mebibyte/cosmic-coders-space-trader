// $codepro.audit.disable numericLiterals, multiplicationOrDivisionByPowersOf2, expressionValue

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import edu.gatech.spacetrader.gui.BigButton;
import edu.gatech.spacetrader.gui.TextFieldFrame;
import edu.gatech.spacetrader.gui.SelectableButton;
import edu.gatech.spacetrader.gui.SkillButton;
import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.player.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

/**
 * @author Glenn
 * @version $Revision: 1.0 $Z
 */
public class ConfigScreen extends Screen{ 
    /**
     * Field buttons.
     */
    private final BigButton startGame, editName;
    
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
     * Field unusedSkillPoint.
     */
    private static final ImageIcon UN_SKILL_POINT = new ImageIcon(
            ConfigScreen.class.getResource(
                    "/edu/gatech/spacetrader/res/unusedSkillPoint.png"));

    /**
     * Field usedSkillPoint.
     */
    private static final ImageIcon SKILL_POINT = new ImageIcon(
            ConfigScreen.class.getResource(
                    "/edu/gatech/spacetrader/res/usedSkillPoint.png"));
    
    /**
     * Field BG.
     * Background image for ConfigScreen.
     */
    private static final ImageIcon BG = new ImageIcon(
            ConfigScreen.class.getResource("/edu/gatech/spacetrader/res/configBack.jpg"));
	
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
    private String playerName = "Player";
    
    /**
     * Field SKILL_NAMES.
     */
    private static final String[] SKILL_NAMES = 
            new String[]{"Pilot", "Fighter", "Trader", "Engineer"};
    
    /**
     * Field numSkill.
     */
    /**
     * Field numSkill.
     */
    private static final int NUMSKILLS = 4, MAXSKILL = 10,
            POSNEG = 2, MAXSKILLPOINTS = 16;
    
    /**
     * Field skills.
     */
    private final int[] skills = new int[NUMSKILLS];
    
    /**
     * Field buttons.
     */
    private final SkillButton[] skillButtons = new SkillButton[NUMSKILLS * 2];
   
    /**
     * Field skillPointsUsed.
     */
    private int skillPointsUsed;
    
    /**
     * Field points.
     */
    private String points;
    
    /**
     * @author Glenn
     */
    public enum Difficulty {
        /**
         * Field EASY.
         */
        EASY("Easy"), /**
  * Field NORMAL.
  */
 NORMAL("Normal"), /**
  * Field HARD.
  */
 HARD("Hard");
        
        /**
         * Field text.
         */
        private final String text;
        
        /**
         * Difficulty constructor.
         * @param text String
         */
        private Difficulty(String text){
            this.text = text;
        }
        
        /**
         * Method toString.
        
         * @return String */
        public String toString(){
            return text;
        }
    }
    
    /**
     * Field currentDifficulty.
     */
    private Difficulty currentDifficulty = Difficulty.NORMAL;
    
    /**
     * Field textFieldFrame.
     */
    private TextFieldFrame editNameFrame;
	
	/**
	 * Constructor for SkillScreen.
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public ConfigScreen(GamePanel panel, int width, int height) {
	    final int xNeg = (width / 2) - 90;
	    final int xPos = (width / 2) + 55;
	    final int y = 165;

	    for (int i = 0; i < skillButtons.length; i++) {
	        skillButtons[i] = new SkillButton(i % POSNEG == 0 ? "-" : "+",
	                i % POSNEG == 0 ? xNeg : xPos, y + (i / 2) * 50);
	        if (i % POSNEG == 0) skillButtons[i].setDisabled(true);
	    }
	    
	    final int buttonSep = 200;
	    
	    easy = new SelectableButton("Easy", (width / 2) - buttonSep, height - 125, false);
	    normal = new SelectableButton("Normal", width / 2, height - 125, true);
	    hard = new SelectableButton("Hard", (width / 2) + buttonSep, height - 125, false);
        
		this.panel = panel;
		this.width = width;
		this.height = height;
		
		editName = new BigButton("Edit", width / 2, 75);
		startGame = new BigButton("Start", width / 2, height - 50, true);
	}

	/**
	 * Method draw.
	 * @param g Graphics
	 */
	@Override
	public void draw(Graphics g) {
		g.setFont(new Font("arial heavy", Font.BOLD, 25));
		BG.paintIcon(panel, g, 0, 0);
		g.setColor(Color.white);
	    g.drawString("Player name: " + playerName, 
	            (width / 2) - 
	            (g.getFontMetrics().stringWidth("Player name: " + playerName) / 2),
	            35);
		
		editName.draw(g, panel, width, height);
		g.setColor(Color.white);
		for (int i = 0; i < skillButtons.length; i++) {
		    skillButtons[i].draw(g, panel, width, height);
		}
		
		points = "Remaining Skill Points: " + (MAXSKILLPOINTS - skillPointsUsed);
		
	    g.drawString(points, (width / 2) - 
	            (g.getFontMetrics().stringWidth(points) / 2), 130);
		
		final int sep = 50;
		int x = (width / 2) - sep;
		int y = 170;
		
		for (int skill = 0; skill < NUMSKILLS; skill++) {
		    g.drawString(SKILL_NAMES[skill], (width / 2) - 
		        (g.getFontMetrics().stringWidth(SKILL_NAMES[skill]) / 2), y - 5);
		    
		    for (int i = 0; i < skills[skill]; i++) {
		        SKILL_POINT.paintIcon(panel, g, x, y);
		        x += SKILL_POINT.getIconWidth();
		    }
		    for (int j = skills[skill]; j < MAXSKILL; j++) {
                UN_SKILL_POINT.paintIcon(panel, g, x, y);
                x += UN_SKILL_POINT.getIconWidth();
            }
		    y += sep;
		    x = (width / 2) - sep;
		}
		
		easy.draw(g, panel, width, height);
		normal.draw(g, panel, width, height);
		hard.draw(g, panel, width, height);
		
		startGame.draw(g, panel, width, height);
	}

	/**
	 * Method checkForClick.
	 * @param point Point
	 */
	@Override
	public void checkForClick(Point point) {
	    for (int i = 0; i < skillButtons.length; i++) {
            if (skillButtons[i].isClicked(point)) {
                skills[i / 2] += POSNEG * (i % POSNEG) - 1;
                skillPointsUsed += POSNEG * (i % POSNEG) - 1;
                
                if (skills[i / 2] > 0) {
                    skillButtons[(i / 2) * 2].setDisabled(false);
                } else skillButtons[(i / 2) * 2].setDisabled(true);
                
                if (skills[i / 2] < MAXSKILL) {
                    skillButtons[((i / 2) * 2) + 1].setDisabled(false);
                } else skillButtons[((i / 2) * 2) + 1].setDisabled(true);
            }
        }
	    
	    resetButtons();
	    startGameDisable();
	    changeDifficulty(point);
	    
	    if (startGame.isClicked(point)) {
	        panel.setActiveScreen(new GameScreen(
	                new Player(playerName, skills, currentDifficulty),
	                panel, width, height));
	    } else if (editName.isClicked(point)) {
	        if (editNameFrame == null) {
	            editNameFrame = new TextFieldFrame(this, panel, 14, playerName);
	        } else if (!editNameFrame.isVisible()) {
	            editNameFrame.setVisible(true);
	            editNameFrame.resetFocus();
	        } else {
	            editNameFrame.requestFocus();
	            editNameFrame.resetFocus();
	        }
	    }
	}
	
	/**
     * Method resetButtons.
     */
	private void resetButtons() {
	    for (int i = 1; i < NUMSKILLS * 2; i += 2) {
            if (skillPointsUsed >= MAXSKILLPOINTS) {
                skillButtons[i].setDisabled(true);
            } else if (skills[i / 2] < MAXSKILL) {
                skillButtons[i].setDisabled(false);
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
	    if (currentDifficulty != Difficulty.EASY && easy.isIn(p)) {
	        currentDifficulty = Difficulty.EASY;
            easy.setSelected(true);
            normal.setSelected(false);
            hard.setSelected(false);
        } else if (currentDifficulty != Difficulty.NORMAL && normal.isIn(p)) {
            currentDifficulty = Difficulty.NORMAL;
            easy.setSelected(false);
            normal.setSelected(true);
            hard.setSelected(false);
        } else if (currentDifficulty != Difficulty.HARD && hard.isIn(p)) {
            currentDifficulty = Difficulty.HARD;
            easy.setSelected(false);
            normal.setSelected(false);
            hard.setSelected(true);
        }
	}
    
    /**
     * Method setHoverPoint.
     * @param p Point
     */
    @Override
    public void setHoverPoint(Point p) {
        super.setHoverPoint(p);
        editName.setHovered(p);
        startGame.setHovered(p);
    }
    
    /**
     * Method toString.
    
     * @return String */
    @Override
    public String toString(){
        return "Skill Screen";
    }

    /**
     * Method changeName.
     * @param playerName String
     */
    public void changeName(String playerName) {
        this.playerName = playerName;
        editNameFrame.setVisible(false);
    }
}
