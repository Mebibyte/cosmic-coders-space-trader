/* Comment
 * 
 */
package edu.gatech.spacetrader.good;

import edu.gatech.spacetrader.planet.Planet;

/**
 * 
 * Generates prices of goods for a planet based on its conditions
 * @author Patrick Conner
 * @version 1.0
 */
public class PlanetMarket {
    private int[][] prices = new int[10][2];
	private static final int sellIndex = 0;
	private static final int buyIndex = 1;
	private final Planet planet;
	
	public int getPrice(Good g, String s) {
	    return prices[g.getIndex()][s.equals("s") ? sellIndex : buyIndex];
	}
	
	public PlanetMarket(Planet planet){
	    this.planet = planet;
		for (int i = 0; i < prices.length; i++) {
		    prices[i][1] = Good.getBasePrice(i);
		}
	}
	
	public void updatePrices(){
	    for(int i = 0; i < prices.length; i++) {
	        prices[i][buyIndex] = Good.getBasePrice(i) + planet.getCivLevel().getTechLevel(); // + (IPL * (Planet Tech Level - MTLP)) + (variance)
	    }
	}
}
