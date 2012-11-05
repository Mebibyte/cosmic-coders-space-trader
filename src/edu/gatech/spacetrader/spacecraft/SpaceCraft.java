// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */

package edu.gatech.spacetrader.spacecraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;

import edu.gatech.spacetrader.good.Good;
import edu.gatech.spacetrader.good.PlanetMarket;
import edu.gatech.spacetrader.main.GamePanel;

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

    /**
     * Constructor for SpaceCraft.
     * @param health int
     * @param speed int
     * @param attack int
     * @param defense int
     * @param maxCapacity int
     * @param storage Good[]
     */
    public SpaceCraft(int health, int speed, int attack, int defense, // $codepro.audit.disable largeNumberOfParameters
            int maxCapacity, Good[] storage) {
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.storage = storage.clone();
        this.maxCapacity = maxCapacity;
        fuel = 100;

        int goodCount = 0;
        int x = 700;
        int y = 350;

        for (int i = y; i < (y + 150); i += 75) {
            for (int j = x; j < (x + 300); j += 60) {
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
     * @param distance
     */
    public void fly(int distance) {
        fuel -= distance / speed;
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
    
     * @return int Attack value. */
    public int getAttack() {
        return this.attack;
    }

    /**
    
     * @return int Defense Value. */
    public int getDefense() {
        return this.defense;
    }

    /**
     * Method getHealth.
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method setHealth.
     * @param health int
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Checks if there is room to add a good to storage.
     * 
    
     * @return Boolean of whether or not you could add the good to the storage */
    public boolean canAddToStorage() {
        return quantity < maxCapacity;
    }

    /**
     * Method addToStorage.
     * @param g Good
     */
    public void addToStorage(Good g) {
        storage[g.getIndex()].addGood();
        quantity++;
    }

    /**
     * Method removeGood.
     * @param g Good
     */
    public void removeGood(Good g) {
        storage[g.getIndex()].removeGood();
        quantity--;
    }

    /**
     * Method getStorage.
     * @return Good[]
     */
    public Good[] getStorage() {
        return storage;
    }

    /**
     * Method drawStorage.
     * @param g Graphics
     * @param panel GamePanel
     */
    public void drawStorage(Graphics g, GamePanel panel) {
        for (Good good : storage) {
            good.draw(g, panel);
        }
    }

    /**
     * Method setSellPrices.
     * @param market PlanetMarket
     */
    public void setSellPrices(PlanetMarket market) {
        for (int i = 0; i < 10; i++) {
            storage[i].setSalePrice(market.getGood(i).getSellPrice());
        }
    }

    /**
     * Method getSpeed.
     * @return int
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Method getFuel.
     * @return int
     */
    public int getFuel() {
        return fuel;
    }
    
    /**
     * Method canAddFuel.
     * @return boolean
     */
    public boolean canFillFuel() {
        if (fuel < 100) {
            fuel = 100;
            return true;
        }
        return false;
    }

    /**
     * Method goodClicked.
     * @param point Point
     * @return Good
     */
    public Good goodClicked(Point point) {
        for (Good g : storage) {
            if (g.isClicked(point)) {
                return g;
            }
        }
        return null;
    }

    /**
     * Method canSellGood.
     * @param sold Good
     * @return boolean
     */
    public boolean canSellGood(Good sold) {
        return storage[sold.getIndex()].getQuantity() > 0;
    }

    /**
     * Method updatePrices.
     * @param market PlanetMarket
     */
    public void updatePrices(PlanetMarket market) {
        for (int i = 0; i < 10; i++) {
            storage[i].setSalePrice(market.getGood(i).getSellPrice());
        }
    }

    /**
     * Method drawFuel.
     * @param g Graphics
     * @param panel GamePanel
     * @param x int
     * @param y int
     */
    public void drawFuel(Graphics g, GamePanel panel, int x, int y) {
        final int fuelWidth = 25, halfFuelWidth = 25 / 2;
        g.setColor(Color.BLACK);
        g.fillRect(x, y, fuelWidth, -1 * (fuel - 100));
        g.setColor(Color.ORANGE);
        g.fillRect(x, y + -1 * (fuel - 100), fuelWidth, 100 - -1 * (fuel - 100));
        g.setColor(Color.BLACK);
        g.drawRect(x, y, fuelWidth, 100);

        for (int i = y; i < (y + 100); i += 5) {
            if (i % 10 == 0) {
                g.drawLine(x, i, x + 5, i);
            } else {
                g.drawLine(x, i, x + 3, i);
            }
        }

        g.drawString("F", halfFuelWidth - (g.getFontMetrics().stringWidth("F") / 2),
                y + g.getFontMetrics().getHeight());
        g.drawString("E", halfFuelWidth - (g.getFontMetrics().stringWidth("E") / 2),
                y + 95);
    }

    /**
     * Method canFly.
     * @return boolean
     */
    public boolean canFly() {
        return fuel > 0;
    }
}
