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
	WATER(30, 0, 0, 0, 3, 4), FURS(250, 1, 0, 0, 10, 10), FOOD(100, 2, 1, 0, 20, 10), 
	ORE(350, 3, 2, 2, 20, 10), GAMES(250, 4, 3, 1, -10, 5), 
	FIREARMS(1250, 5, 3, 1, -75, 100), MEDICINE(650, 6, 4, 1, -20, 10),
	MACHINES(900, 7, 4, 3, -30, 5), NARCOTICS(3500, 8, 5, 0, -125, 150), 
	ROBOTS(5000, 9, 6, 4, -150, 100);
	
	/**
     * Field basePrice.
     */
	private final int basePrice, index, MTLP, MTLU, IPL, VAR;
	
	/**
     * Constructor for Good.
     * @param basePrice int.
     * @param index int.
     */
	private Good(int basePrice, int index, int MTLP, int MTLU, int IPL, int VAR) {
	    this.basePrice = basePrice;
	    this.index = index; 
	    this.MTLP = MTLP; 
	    this.MTLU = MTLU; 
	    this.IPL = IPL; 
	    this.VAR = VAR; 
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

    /**
     * Gets the base price of a specific index. (NOT GOOD)
     * 
     * @param i Index to get
     * @return int basePrice.
     */
    public static int getBasePrice(int i) {
        for(Good g : Good.values()) {
            if (g.index == i) return g.getBasePrice();
        }
        return 0;
    }
}
