/* Comment
 * 
 */
package edu.gatech.spacetrader.good;

import java.awt.Graphics;

import edu.gatech.spacetrader.main.GamePanel;
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
     * Field goods.
     * Array of goods for PlanetMarket.
     */
    private Good[] goods = new Good[NUM_GOODS];
	
	/**
     * Constructor for PlanetMarket that sets all buy prices to base price.
     * 
     * @param planet Planet this market resides on.
     */
	public PlanetMarket(Planet planet){
		for (int i = 0; i < goods.length; i++) {
		    goods[i] = new Good(i, planet);
		}
	}
	
	/**
	 * Updates prices based on several factors of the planet and good.
	 */
	public void updatePrices(){
	    for (int i = 0; i < goods.length; i++) {
	        goods[i].updatePrice();
	    }
	}
	
	/**
	 * Get the buy price of a specified good.
	 * 
	 * @param g Good.
	 * @return Price of specified good.
	 */
	public int getBuyPrice(Good g) {
        return g.getBuyPrice();
    }
	
	/**
     * Get the sell price of a specified good.
     * 
     * @param g Good.
     * @return Price of specified good.
     */
    public int getSellPrice(Good g) {
        return g.getSellPrice();
    }
	
	/**
	 * draw method for PlanetMarket.
	 * Draws the goods in the planetMarket using the given graphics g.
	 * 
	 * @param g Graphics to be drawn.
	 * @param panel GamePanel.
	 * @param x X-coordinate.
	 * @param y Y-coordinate.
	 */
    public void draw(Graphics g, GamePanel panel, int x, int y) {
        g.drawString("Goods", x, y);
    }
	
	/**
	 * toString method for PlanetMarket required by CodePro.
	 * 
	 * @return String representation of PlanetMarket.
	 */
	public String toString(){
	    final StringBuffer ans = new StringBuffer("[");
	    for (int i = 0; i < goods.length; i++) {
	        ans.append(goods[i].toString());
	    }
	    ans.append(']');
	    return ans.toString();
	}
}