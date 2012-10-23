/* Comment
 * 
 */
package edu.gatech.spacetrader.good;

import java.util.Arrays;

import edu.gatech.spacetrader.planet.Planet;

/**
 * 
 * Generates prices of goods for a planet based on its conditions
 * @author Patrick Conner
 * @version 1.0
 */
public class PlanetMarket {
    
    /**
     * Field NUM_GOODS.
     * (Value is 10)
     */
    private static final int NUM_GOODS = 10;
    
    /**
     * Field prices.
     * 2D int arrray for prices of goods. Each good has an inner array.
     * The first spot of inner array is buy price, second is sell price.
     */
    private int[][] prices = new int[NUM_GOODS][2];

    /**
     * Field BUY.
     * (Value is 0)
     */
    /**
     * Field prices.
     * (value is 1)
     */
    private static final int BUY = 0, SELL = 1;
    
    /**
     * Field planet.
     * Planet this market belongs to.
     */
	private final Planet planet;
	
	/**
     * Constructor for PlanetMarket that sets all buy prices to base price.
     * 
     * @param planet Planet this market resides on.
     */
	public PlanetMarket(Planet planet){
	    this.planet = planet;
		for (int i = 0; i < prices.length; i++) {
		    prices[i][BUY] = Good.getBasePrice(i);
		}
	}
	
	/**
	 * Updates prices based on several factors of the planet and good.
	 */
	public void updatePrices(){
	    for(int i = 0; i < prices.length; i++) {
	        prices[i][BUY] = Good.getBasePrice(i) + 
	                planet.getCivLevel().getTechLevel(); // + (IPL * (Planet Tech Level - MTLP)) + (variance)
	    } //TODO Add missing variables from planet/good
	}
	
	/**
	 * Get the buy/sell price of a specified good.
	 * 
	 * @param g Good.
	 * @param s String representing buy(b) or sell(s).
	 * @return Price of specified good.
	 */
	public int getPrice(Good g, String s) {
        return prices[g.getIndex()][s.equals("s") ? SELL : BUY];
    }
	
	/**
	 * toString method for PlanetMarket required by CodePro.
	 * 
	 * @return String representation of PlanetMarket.
	 */
	public String toString(){
	    final StringBuffer ans = new StringBuffer("[");
	    for (int i = 0; i < prices.length; i++) {
	        ans.append(Arrays.toString(prices[i]));
	    }
	    ans.append(']');
	    return ans.toString();
	}
}
