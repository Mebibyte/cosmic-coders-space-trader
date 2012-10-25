/* Comment
 * 
 */

package edu.gatech.spacetrader.spacecraft;

import edu.gatech.spacetrader.good.Good;
import edu.gatech.spacetrader.main.SpaceTrader;
import edu.gatech.spacetrader.planet.Galaxy;

/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class Gnat extends SpaceCraft {

    /**
     * Field GNAT_HEALTH (value is 100)
     */
    private static final int GNAT_HEALTH = 100;

    /**
     * Field GNAT_SPEED. (value is 2)
     */
    private static final int GNAT_SPEED = 2;

    /**
     * Field GNAT_ATTACK. (value is 5)
     */
    private static final int GNAT_ATTACK = 5;

    /**
     * Field GNAT_DEFENSE. (value is 5)
     */
    private static final int GNAT_DEFENSE = 5;

    /**
     * Field GNAT_CARGO_SIZE. (value is 10)
     */
    private static final int GNAT_CAPACITY = 10;

    /**
     * Field GNAT_STORAGE (Good array of size 10)
     */
    private static Good[] GNAT_STORAGE = new Good[10];

    /**
     * Field GNAT_NAME.
     * (value is Gnat)
     */
    public static final String GNAT_NAME = "Gnat";
    
    /**
	 * 
	 */
    public Gnat() {
        this.health = GNAT_HEALTH;
        this.speed = GNAT_SPEED;
        this.attack = GNAT_ATTACK;
        this.defense = GNAT_DEFENSE;
        this.storage = GNAT_STORAGE;
        int goodCount = 0;
        int x = ((SpaceTrader.WIDTH - Galaxy.GALAXY_WIDTH + 10) / 2) + 300;
        int y = 0;
        for (int i = y; i < (y + 150); i += 75) {
            for (int j = x; j < (x + 250); j += 50) {
                storage[goodCount] = new Good(goodCount, this, j, i);
                goodCount++;
            }
        }
        this.maxCapacity = GNAT_CAPACITY;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Gnat";
    }

    /**
     * @param repairSkill
     * 
     * @see edu.gatech.spacetrader.spacecraft.SpaceCraft#repair(int)
     */
    @Override
    public void repair(int repairSkill) {
        System.out.println("Repair"); // FIXME
    }

}
