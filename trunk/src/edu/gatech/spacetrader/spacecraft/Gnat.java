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
     * Method fly.
     * 
     */
	@Override
    public void fly(){
        System.out.println("Implement later");
    }
    
    /**
     * Method toString.
    
     * @return String */
	@Override
    public String toString(){
        return "Gnat";
    }

	/**
	 * @param attackStrength Attack strength of enemy
	
	 * @return damage done to the gnat */
	@Override
	public int takeDamge(int attackStrength) {
		// TODO Auto-generated method stub
		return 0;
	}

	/** 
	 * @param repairSkill
	
	 * @see edu.gatech.spacetrader.spacecraft.SpaceCraft#repair(int) */
	@Override
	public void repair(int repairSkill) {
		System.out.println("Repair"); //FIXME
	}
}
