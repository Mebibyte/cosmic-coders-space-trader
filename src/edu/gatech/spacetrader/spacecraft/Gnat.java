/* Comment
 * 
 */

package edu.gatech.spacetrader.spacecraft;



/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class Gnat extends SpaceCraft{
    
	/**
	 * 
	 */
	private final int GNAT_HEALTH = 100;
	
	private final int GNAT_SPEED = 2;
	
	private final int GNAT_ATTACK = 5;
	
	private final int GNAT_DEFENSE = 5;

	/**
	 * 
	 */
	public Gnat(){
		this.health = GNAT_HEALTH;
		this.speed = GNAT_SPEED;
		this.attack = GNAT_ATTACK;
		this.defense = GNAT_DEFENSE;
	}

    /**
     * Method toString.
    
     * @return String */
	@Override
    public String toString(){
        return "Gnat";
    }

	/** 
	 * @param repairSkill
	
	 * @see edu.gatech.spacetrader.spacecraft.SpaceCraft#repair(int) */
	@Override
	public void repair(int repairSkill) {
		System.out.println("Repair"); //FIXME
	}
	
}
