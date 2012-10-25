/* Comment
 * 
 */

package edu.gatech.spacetrader.spacecraft;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import edu.gatech.spacetrader.good.Good;
import edu.gatech.spacetrader.good.PlanetMarket;
import edu.gatech.spacetrader.main.GamePanel;

import javax.swing.ImageIcon;

/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public abstract class SpaceCraft {

    /**
	 * 
	 */
    private static final Random RAND = new Random();

    /**
	 * 
	 */
    protected int health;

    /** 
	 * 
	 */
    protected Good[] storage;

    /**
	 * 
	 */
    protected int speed;

    /**
	 * 
	 */
    protected int defense;

    /**
	 * 
	 */
    protected int attack;

    /**
	 * 
	 */
    protected int maxCapacity;

    /**
	 * 
	 */
    protected int quantity;

    /**
     * We can eventually create pictures of each of the ships for when the
     * player upgrades to a new ship
     */
    protected ImageIcon shipIcon;

    /**
     * @param repairSkill
     */
    public abstract void repair(int repairSkill);

    /**
     * Method fly.
     * 
     */
    public void fly() {
        System.out.println("2 Infinity N Beyond! nice touch glenn"); // TODO
    }

    /**
     * @param enemyAttack
     *            Attack strength of enemy
     * @return damage done to the gnat
     */
    public int takeDamge(int enemyAttack) {
        // TODO Auto-generated method stub
        final int damage;

        damage = Math.max(RAND.nextInt(enemyAttack) - RAND.nextInt(defense), 0);
        health -= damage;
        return damage;
    }

    /**
     * @return int Attack value.
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * @return int Defense Value.
     */
    public int getDefense() {
        return this.defense;
    }
    
    public int getHealth(){
    	return health;
    }
    
    public void setHealth(int health){
    	this.health = health;
    }
    
    /**
     * Adds a certain Good to the SpaceCraft's Storage
     * 
     * @param g
     * @return Boolean of whether or not you could add the good to the storage
     */
    public boolean addToStorage(Good g) {
        boolean ans;

        if (quantity >= maxCapacity) {
            ans = false;
        } else {
            storage[g.getIndex()].setQuantity(storage[g.getIndex()]
                    .getQuantity() + 1);
            ans = true;
            quantity++;
        }
        return ans;

    }

    public void removeGood(Good g) {
        storage[g.getIndex()]
                .setQuantity(storage[g.getIndex()].getQuantity() - 1);
    }
    
    public Good[] getStorage(){
        return storage;
    }

    public Good checkForClick(Point point) {
        for (Good g : storage) {
            if (g.checkForClick(point)) {
                return g;
            }
        }
        return null;
    }

    public void drawStorage(Graphics g, GamePanel panel) {
        for (Good good : storage) {
            good.draw(g, panel);
        }
    }

    public void setSellPrices(PlanetMarket market) {
       for (int i = 0; i < 10; i++) {
           storage[i].setSalePrice(market.getGood(i).getSellPrice());
       }
    }
}
