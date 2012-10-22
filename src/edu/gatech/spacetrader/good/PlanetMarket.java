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
    private int[][] prices = new int[10][2];

    private static final int SELL = 0, BUY = 1;
    
	private final Planet planet;
	
	public int getPrice(Good g, String s) {
	    return prices[g.getIndex()][s.equals("s") ? SELL : BUY];
	}
	
	public PlanetMarket(Planet planet){
	    this.planet = planet;
		for (int i = 0; i < prices.length; i++) {
		    prices[i][BUY] = Good.getBasePrice(i);
		}
	}
	
	public void updatePrices(){
	    for(int i = 0; i < prices.length; i++) {
	        prices[i][BUY] = Good.getBasePrice(i) + 
	                planet.getCivLevel().getTechLevel(); // + (IPL * (Planet Tech Level - MTLP)) + (variance)
	    }
	}
	
	public String toString(){
	    final StringBuffer ans = new StringBuffer("[");
	    for (int i = 0; i < prices.length; i++) {
	        ans.append(Arrays.toString(prices[i]));
	    }
	    ans.append(']');
	    return ans.toString();
	}
}
