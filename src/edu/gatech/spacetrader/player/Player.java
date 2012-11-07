// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */

package edu.gatech.spacetrader.player;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

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
    private ConfigScreen.Difficulty difficulty;
    
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
     * @param spaceCraft
     */
    public void setSpaceCraft(SpaceCraft spaceCraft){
    	this.spaceCraft = spaceCraft;
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
     * @return String
     */
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

    public void checkForEvent(){
    	Random rand = new Random();  
    	int num = rand.nextInt(20);  
    	 
    	if ( num == 19 ) { 
    		// thieves still 10% of your money  
    		credits = (credits * 9) / 10; 
    	} else if ( num >= 17 ) { 
    		// hit by a stray asteroid in space 
    		spaceCraft.takeDamge(8); 
    	} else if ( num == 16) {
    		//find random treasure in space 
    	   credits += rand.nextInt(1000) + 50; 
    	}  else if ( num == 15 ) {
    		// spaceCraft's fuel hull sprung a leak lose 40%  
    		spaceCraft.setFuel((spaceCraft.getFuel()* (rand.nextInt(4) + 6)) /10);
    	} else if ( num == 14 ) {
    		// thieves steal one of your goods 
    		spaceCraft.loseRandomGood(); 
    	} else if (num == 13) {
    		// find random good in space
    		spaceCraft.addRandomGood(); 
    	} 
    	// other than that nothing happens
    }
    
    /**
     * @param difficulty
     */
    public void setDifficulty(ConfigScreen.Difficulty difficulty){
    	this.difficulty = difficulty;
    }
    
    /**
     * Method drawInfo.
     * @param g Graphics
     * @param panel GamePanel
     * @param x int
     * @param dy int
     */
    public void drawInfo(Graphics g, GamePanel panel, int x, int dy) {
        int y = dy + dy;
        g.drawString("Name: " + name, x, y);
        g.drawString("Difficulty: " + difficulty.toString(), x, y += dy);
        
        int skill = 0;
        g.drawString("Pilot: " + skills[skill++], x, y += dy);
        g.drawString("Fighter: " + skills[skill++], x, y += dy);
        g.drawString("Trader: " + skills[skill++], x, y += dy);
        g.drawString("Engineer: " + skills[skill], x, y += dy);
        
        g.drawString(credits + " credits", x, y += dy);
        g.drawString("Spacecraft: " + spaceCraft.toString(), x, y += dy);
    }
}
