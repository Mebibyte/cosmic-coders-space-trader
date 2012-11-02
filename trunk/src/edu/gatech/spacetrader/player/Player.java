// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */

package edu.gatech.spacetrader.player;

import java.awt.Graphics;
import java.util.Arrays;

import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.screens.ConfigScreen;
import edu.gatech.spacetrader.spacecraft.Gnat;
import edu.gatech.spacetrader.spacecraft.SpaceCraft;

/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class Player {
    /**
     * Field name.
     */
    private final String name;
    
    /**
     * Field skills.
     */
    private final int[] skills;
    
    /**
     * Field difficulty.
     */
    private final ConfigScreen.Difficulty difficulty;
    
    /**
     * Field credits.
     */
    private int credits;

    /**
     * Field spaceCraft.
     */
    private SpaceCraft spaceCraft = new Gnat();
    
    /**
     * Field INITIALCREDITS.
     */
    private static final int INITIALCREDITS = 1000;
    
    /**
     * Constructor for Player.
     * @param name String
     * @param skills int[]
     * @param difficulty ConfigScreen.Difficulty
     */
    public Player(String name, int[] skills, ConfigScreen.Difficulty difficulty) {
        this.name = name;
        this.skills = skills.clone();
        this.difficulty = difficulty;
        credits = INITIALCREDITS;
    }

    /**
     * Method getName.
    
     * @return String */
    public String getName() {
        return name;
    }

    /**
     * Method getSkills.
    
     * @return String */
    public String getSkillsString() {
        return Arrays.toString(skills);
    }
    
    /**
     * Method getSkillsArray.
     * @return int[]
     */
    public int[] getSkillsArray(){
    	return skills;
    }
    
    /**
     * Method getDifficulty.
    
     * @return String */
    public String getDifficulty() {
        return difficulty.toString();
    }
    
    /**
     * Method getDifficulty.
    
     * @return String */
    public int getCredits() {
        return credits;
    }
    
    /**
     * Method getSpaceCraft.
    
     * @return SpaceCraft */
    public SpaceCraft getSpaceCraft() {
        return spaceCraft;
    }
    
    /**
     * Method useCredits.
     * @param amount int
     */
    public void useCredits(int amount) {
        credits += amount;
    }
    
    /**
     * Method changeSpaceCraft.
     * @param spaceCraft SpaceCraft 
     */
    public void changeSpaceCraft(SpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
    }
    
    /**
     * Method toString.
    
     * @return String */
    @Override
    public String toString(){
        return "Player " + name + " with " + credits + " credits, skills " 
                + Arrays.toString(skills) + " on difficulty " + difficulty.toString();
    }

    /**
     * Method canSpend.
     * @param buyPrice int
     * @return boolean
     */
    public boolean canSpend(int buyPrice) {
        return credits > buyPrice;
    }

    /**
     * Method drawInfo.
     * @param g Graphics
     * @param panel GamePanel
     * @param x int
     * @param y int
     */
    public void drawInfo(Graphics g, GamePanel panel, int x, int y) {
        g.drawString("Name: " + name, x, y * 2);
        g.drawString("Difficulty: " + difficulty.toString(), x, y * 3);
        g.drawString("Pilot: " + skills[0], x, y * 4);
        g.drawString("Fighter: " + skills[1], x, y * 5);
        g.drawString("Trader: " + skills[2], x, y * 6);
        g.drawString("Engineer: " + skills[3], x, y * 7);
        g.drawString(credits + " credits", x, y * 8);
        g.drawString(spaceCraft.toString(), x, y * 9);
    }
}
