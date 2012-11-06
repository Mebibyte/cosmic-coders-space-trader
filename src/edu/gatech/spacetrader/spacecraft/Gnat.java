// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */

package edu.gatech.spacetrader.spacecraft;

import edu.gatech.spacetrader.good.Good;

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
    private static final Good[] GNAT_STORAGE = new Good[10];

    /**
     * Field GNAT_NAME.
     * (value is Gnat)
     */
    public static final String GNAT_NAME = "Gnat";
    
    /**
	 * 
	 */
    public Gnat() {
        super(GNAT_HEALTH, GNAT_SPEED, GNAT_ATTACK, GNAT_DEFENSE,
                GNAT_CAPACITY, GNAT_STORAGE);
    }

    /**
     * Method toString.
     * 
    
     * @return String */
    @Override
    public String toString() {
        return "Gnat";
    }

    /**
     * @param repairSkill
     * 
    
     * @see edu.gatech.spacetrader.spacecraft.SpaceCraft#repair(int) */
    @Override
    public void repair(int repairSkill) {
        System.out.println("Repair"); // FIXME
    }

}
