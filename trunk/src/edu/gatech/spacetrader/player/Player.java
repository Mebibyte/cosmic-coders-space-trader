/* Comment
 * 
 */

package edu.gatech.spacetrader.player;

import java.util.Arrays;

import edu.gatech.spacetrader.screens.ConfigScreen;

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
     * Constructor for Player.
     * @param name String
     * @param skills int[]
     * @param difficulty ConfigScreen.Difficulty
     */
    public Player(String name, int[] skills, ConfigScreen.Difficulty difficulty) {
        this.name = name;
        this.skills = skills.clone();
        this.difficulty = difficulty;
    }

    /**
     * Method getName.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method getSkills.
     * @return String
     */
    public String getSkills() {
        return Arrays.toString(skills);
    }

    /**
     * Method getDifficulty.
     * @return String
     */
    public String getDifficulty() {
        return difficulty.toString();
    }
    
    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString(){
        return "Player " + name + " with skills " + Arrays.toString(skills) 
                + " on difficulty " + difficulty.toString();
    }
}
