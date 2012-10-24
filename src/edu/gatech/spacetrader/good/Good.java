/* Comment
 * 
 */
// $codepro.audit.disable numericLiterals
package edu.gatech.spacetrader.good;
import java.util.Random;

import edu.gatech.spacetrader.planet.Planet;

/**
 * Will probably only be used statically. 
 * Contains information about good types and what affects them
 * @author Patrick Conner
 * @version 1.0
 * 
 */
public class Good {
    
    public static enum GoodType {
    	WATER(30, 0, 0, 0, 3, 4), FURS(250, 1, 0, 0, 10, 10), FOOD(100, 2, 1, 0, 20, 10), 
    	ORE(350, 3, 2, 2, 20, 10), GAMES(250, 4, 3, 1, -10, 5), 
    	FIREARMS(1250, 5, 3, 1, -75, 100), MEDICINE(650, 6, 4, 1, -20, 10),
    	MACHINES(900, 7, 4, 3, -30, 5), NARCOTICS(3500, 8, 5, 0, -125, 150), 
    	ROBOTS(5000, 9, 6, 4, -150, 100);
    	
    	/**
         * Field basePrice. 
         * Field index. 
         * Field MTLP. 
         * Field MTLU. 
         * Field IPL. 
         * Field VAR. 
         */
    	private final int basePrice, index, MTLP, MTLU, IPL, VAR;
    	
    	/**
    	 * Good constructor.
    	 * @param basePrice
    	 * @param index
    	 * @param MTLP
    	 * @param MTLU
    	 * @param IPL
    	 * @param VAR
    	 */
    	private GoodType(int basePrice, int index, int MTLP, int MTLU, int IPL, int VAR) {
    	    this.basePrice = basePrice;
    	    this.index = index; 
    	    this.MTLP = MTLP; 
    	    this.MTLU = MTLU; 
    	    this.IPL = IPL; 
    	    this.VAR = VAR; 
    	}

        public static GoodType getGoodType(int index) {
            for (GoodType g : GoodType.values()) {
                if (g.index == index) return g;
            }
            return null;
        }
    }
    
    private GoodType type;
    
    private Planet planet;
    
    private int quantity, buyPrice, sellPrice;
    
    private static final Random rand = new Random();
    
    public Good(int index, Planet planet) {
        this.type = GoodType.getGoodType(index);
        this.buyPrice = type.basePrice;
        this.sellPrice = (int) (buyPrice * .9);
    }
    
    public boolean buyGood() {
        if (quantity > 0) {
            quantity--;
            return true;
        } else return false;
    }
    
    public void addGood() {
        quantity++;
    }
    
    public void updatePrice() {
        buyPrice = type.basePrice + (type.IPL * (planet.getCivLevel().getTechLevel() - type.MTLP)) + 
                ((rand.nextBoolean() ? 1 : -1) * rand.nextInt(type.VAR));
        sellPrice = (int) (this.buyPrice * .9);
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
