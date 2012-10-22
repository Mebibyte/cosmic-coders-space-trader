/* Comment
 * 
 */
// $codepro.audit.disable numericLiterals
package edu.gatech.spacetrader.good;

/**
 * Will probably only be used statically. 
 * Contains information about good types and what affects them
 * @author Patrick Conner
 * @version 1.0
 * 
 */
public enum Good {
	WATER(30), FURS(250), FOOD(100), ORE(350), GAMES(250), 
	FIREARMS(1250), MEDICINE(650), MACHINES(900), NARCOTICS(3500), ROBOTS(5000);
	
	/**
     * Field basePrice.
     */
	private final int basePrice;
	
	/**
     * Constructor for Good.
     * @param basePrice int.
     */
	private Good(int basePrice) {
	    this.basePrice = basePrice;
	}
	
	/**
	 * Method getBasePrice.
	 * @return int basePrice.
	 */
    public int getBasePrice() {
        return basePrice;
    }
}
