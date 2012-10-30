/* Comment
 * 
 */
package edu.gatech.spacetrader.good;

import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.planet.Planet;

/**
 * Generates prices of goods for a planet based on its conditions
 * 
 * @author Patrick Conner
 * @version 1.0
 */
public class PlanetMarket {
    /**
     * Field NUM_GOODS. (Value is 10)
     */
    private static final int NUM_GOODS = 10;

    /**
     * Field goods. Array of goods for PlanetMarket.
     */
    private Good[] goods = new Good[NUM_GOODS];

    /**
     * Constructor for PlanetMarket that sets all buy prices to base price.
     * 
     * @param planet
     *            Planet this market resides on.
     * @param x x location
     * @param y y location
     */
    public PlanetMarket(Planet planet, int x, int y) {
        int goodCount = 0;
        for (int i = y; i < (y + 150); i += 75) {
            for (int j = x; j < (x + 250); j += 50) {
                goods[goodCount] = new Good(goodCount, planet, j, i);
                goodCount++;
            }
        }
    }

    /**
     * Updates prices based on several factors of the planet and good.
     */
    public void updatePrices() {
        for (int i = 0; i < goods.length; i++) {
            goods[i].updatePrice();
        }
    }

    /**
     * Get the buy price of a specified good.
     * 
     * @param g
     *            Good.
     * @return Price of specified good.
     */
    public int getBuyPrice(Good g) {
        return g.getBuyPrice();
    }

    /**
     * Get the sell price of a specified good.
     * 
     * @param g
     *            Good.
     * @return Price of specified good.
     */
    public int getSellPrice(Good g) {
        return g.getSellPrice();
    }

    /**
     * draw method for PlanetMarket. Draws the goods in the planetMarket using
     * the given graphics g.
     * 
     * @param g
     *            Graphics to be drawn.
     * @param panel
     *            GamePanel.
     */
    public void draw(Graphics g, GamePanel panel) {
        for (Good good : goods) {
            good.draw(g, panel);
        }
    }

    /**
     * toString method for PlanetMarket required by CodePro.
     * 
     * @return String representation of PlanetMarket.
     */
    public String toString() {
        final StringBuffer ans = new StringBuffer("[");
        for (int i = 0; i < goods.length; i++) {
            ans.append(goods[i].toString());
        }
        ans.append(']');
        return ans.toString();
    }

    /**
     * Buys a good from the market.
     * @param bought Good bought.
     */
    public void boughtGood(Good bought) {
        goods[bought.getIndex()].removeGood();
    }

    /**
     * Gets the good with the specific index.
     * @param i Index
     * @return good
     */
    public Good getGood(int i) {
        return goods[i];
    }

    /**
     * Returns the good clicked.
     * @param point
     * @return The good clicked.
     */
    public Good goodClicked(Point point) {
        for (Good g : goods) {
            if (g.isClicked(point)) {
                return g;
            }
        }
        return null;
    }

    /**
     * Sold a good to market.
     * @param sold Good.
     */
    public void soldGood(Good sold) {
        goods[sold.getIndex()].addGood();
    }
}