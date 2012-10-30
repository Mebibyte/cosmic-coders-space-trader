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
import edu.gatech.spacetrader.main.SpaceTrader;
import edu.gatech.spacetrader.planet.Galaxy;

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
    private Good[] storage;

    /**
	 * 
	 */
    private int health, speed, defense, attack, maxCapacity, quantity, fuel;

    /**
     * We can eventually create pictures of each of the ships for when the
     * player upgrades to a new ship
     */
    protected ImageIcon shipIcon;

    public SpaceCraft(int health, int speed, int attack,
            int defense, int maxCapacity, Good[] storage) {
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.storage = storage;
        this.maxCapacity = maxCapacity;
        fuel = 10;
        
        int goodCount = 0;
        int x = ((SpaceTrader.WIDTH - Galaxy.GALAXY_WIDTH + 10) / 2) + 300;
        int y = 0;
        
        for (int i = y; i < (y + 150); i += 75) {
            for (int j = x; j < (x + 250); j += 50) {
                this.storage[goodCount] = new Good(goodCount, this, j, i);
                goodCount++;
            }
        }
    }

    /**
     * @param repairSkill
     */
    public abstract void repair(int repairSkill);

    /**
     * Method fly.
     * 
     */
    public void fly() {
        fuel--;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Checks if there is room to add a good to storage.
     * 
     * @return Boolean of whether or not you could add the good to the storage
     */
    public boolean canAddToStorage() {
        return quantity < maxCapacity;
    }
    
    public void addToStorage(Good g) {
        storage[g.getIndex()].addGood();
        quantity++;
    }

    public void removeGood(Good g) {
        storage[g.getIndex()].removeGood();
        quantity--;
    }

    public Good[] getStorage() {
        return storage;
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

    public int getSpeed() {
        return speed;
    }
    
    public int getFuel() {
        return fuel;
    }
    
    public void addFuel() {
        fuel++;
    }

    public Good goodClicked(Point point) {
        for (Good g : storage) {
            if (g.isClicked(point)) {
                return g;
            }
        }
        return null;
    }

    public boolean canSellGood(Good sold) {
        return storage[sold.getIndex()].getQuantity() > 0;
    }

    public void updatePrices(PlanetMarket market) {
        for (int i = 0; i < 10; i++) {
            storage[i].setSalePrice(market.getGood(i).getSellPrice());
        }
    }
}
