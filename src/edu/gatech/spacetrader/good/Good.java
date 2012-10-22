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
	WATER(30, 0), FURS(250, 1), FOOD(100, 2), ORE(350, 3), GAMES(250, 4), 
	FIREARMS(1250, 5), MEDICINE(650, 6), MACHINES(900, 7), NARCOTICS(3500, 8), ROBOTS(5000, 9);
	
	/**
     * Field basePrice.
     */
	private final int basePrice, index;
	
	/**
     * Constructor for Good.
     * @param basePrice int.
     */
	private Good(int basePrice, int index) {
	    this.basePrice = basePrice;
	    this.index = index;
	}
	
	/**
	 * Method getBasePrice.
	 * @return int basePrice.
	 */
    public int getBasePrice() {
        return basePrice;
    }
    
    /**
     * Method getIndex.
     * @return int index.
     */
    public int getIndex() {
        return index;
    }

    public static int getBasePrice(int i) {
        for(Good g : Good.values()) {
            if (g.index == i) return g.getBasePrice();
        }
        return 0;
    }
}
