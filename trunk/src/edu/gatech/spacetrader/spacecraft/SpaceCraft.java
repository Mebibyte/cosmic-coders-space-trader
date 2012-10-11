/* Comment
 * 
 */

package edu.gatech.spacetrader.spacecraft;

/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public abstract class SpaceCraft {
    
    /**
     * Method fly.
     */
    public abstract void fly();
    
    /**
     * @param attackStrength
    
     * @return damage done by opponent  */
    public abstract int takeDamge(int attackStrength);
    
    /**
     * @param repairSkill
     */
    public abstract void repair(int repairSkill);
}
