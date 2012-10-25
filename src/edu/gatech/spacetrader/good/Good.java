// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */
// $codepro.audit.disable numericLiterals
package edu.gatech.spacetrader.good;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import edu.gatech.spacetrader.gui.BigButton;
import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.planet.Planet;
import edu.gatech.spacetrader.spacecraft.SpaceCraft;

/**
 * Will probably only be used statically. Contains information about good types
 * and what affects them
 * 
 * @author Patrick Conner
 * @version 1.0
 * 
 */
public class Good {

    public static enum GoodType {
        WATER(30, 0, 0, 0, 3, 4), FURS(250, 1, 0, 0, 10, 10), FOOD(100, 2, 1,
                0, 20, 10), ORE(350, 3, 2, 2, 20, 10), GAMES(250, 4, 3, 1, -10,
                5), FIREARMS(1250, 5, 3, 1, -75, 100), MEDICINE(650, 6, 4, 1,
                -20, 10), MACHINES(900, 7, 4, 3, -30, 5), NARCOTICS(3500, 8, 5,
                0, -125, 150), ROBOTS(5000, 9, 6, 4, -150, 100);

        /**
         * Field basePrice. Field index. Field MTLP. Field MTLU. Field IPL.
         * Field VAR.
         */
        private final int basePrice, index, MTLP, MTLU, IPL, VAR;

        /**
         * Good constructor.
         * 
         * @param basePrice
         * @param index
         * @param MTLP
         * @param MTLU
         * @param IPL
         * @param VAR
         */
        private GoodType(int basePrice, int index, int MTLP, int MTLU, int IPL,
                int VAR) {
            this.basePrice = basePrice;
            this.index = index;
            this.MTLP = MTLP;
            this.MTLU = MTLU;
            this.IPL = IPL;
            this.VAR = VAR;
        }

        /**
         * Get the type of a good.
         * 
         * @param index
         * @return
         */
        public static GoodType getGoodType(int index) {
            for (GoodType g : GoodType.values()) {
                if (g.index == index) {
                    return g;
                }
            }
            return null;
        }
    }

    /**
     * Field type.
     */
    private final GoodType type;

    /**
     * Field planet.
     */
    private Planet planet;

    /**
     * Field quantity.
     */
    /**
     * Field buyPrice.
     */
    /**
     * Field sellPrice.
     */
    /**
     * Field x.
     */
    /**
     * Field y.
     */
    private int quantity, buyPrice, sellPrice, x, y;

    /**
     * Field bounds.
     */
    private final Rectangle bounds;

    /**
     * Field spaceCraft.
     */
    private SpaceCraft spaceCraft;

    /**
     * Field GOOD_BG.
     */
    private static final ImageIcon GOOD_BG = new ImageIcon(
            BigButton.class.getResource("/edu/gatech/spacetrader/res/good.png"));

    /**
     * Field RAND.
     */
    private static final Random RAND = new Random();

    /**
     * Constructor for Good on a specific planet.
     * 
     * @param index
     * @param planet
     * @param x
     * @param y
     */
    public Good(int index, Planet planet, int x, int y) {
        this.planet = planet;
        this.type = GoodType.getGoodType(index);
        this.buyPrice = type.basePrice
                + (type.IPL * (planet.getCivLevel().getTechLevel() - type.MTLP))
                + ((RAND.nextBoolean() ? 1 : -1) * RAND.nextInt(type.VAR));
        this.sellPrice = planet.getCivLevel().getTechLevel() > type.MTLU ? (buyPrice * 9) / 10
                : 0;

        if (planet.getCivLevel().getTechLevel() >= type.MTLP) {
            this.quantity = RAND.nextInt(10) + 10;
        }

        this.x = x;
        this.y = y;
        bounds = new Rectangle(x, y, GOOD_BG.getIconWidth(),
                GOOD_BG.getIconHeight());
    }

    /**
     * Constructor for a good on a spacecraft.
     * 
     * @param index
     * @param spaceCraft
     * @param x
     * @param y
     */
    public Good(int index, SpaceCraft spaceCraft, int x, int y) {
        this.spaceCraft = spaceCraft;
        this.type = GoodType.getGoodType(index);
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x, y, GOOD_BG.getIconWidth(),
                GOOD_BG.getIconHeight());
    }

    /**
     * Increases the quantity of a good.
     */
    public void addGood() {
        quantity++;
    }

    /**
     * updates the price of a good.
     */
    public void updatePrice() {
        buyPrice = type.basePrice
                + (type.IPL * (planet.getCivLevel().getTechLevel() - type.MTLP))
                + ((RAND.nextBoolean() ? 1 : -1) * RAND.nextInt(type.VAR));
        sellPrice = planet.getCivLevel().getTechLevel() > type.MTLU ? (buyPrice * 9) / 10
                : 0;
    }

    /**
     * Gets the buy price.
     * 
     * @return buyPrice.
     */
    public int getBuyPrice() {
        return buyPrice;
    }

    /**
     * Gets the sell price.
     * 
     * @return sellPrice.
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Gets the index.
     * 
     * @return index.
     */
    public int getIndex() {
        return type.index;
    }

    /**
     * Draws the good on the screen.
     * 
     * @param g
     * @param panel
     */
    public void draw(Graphics g, GamePanel panel) {
        GOOD_BG.paintIcon(panel, g, x, y);
        g.drawString(quantity + "", x + (GOOD_BG.getIconWidth() / 2)
                - (g.getFontMetrics().stringWidth(quantity + "") / 2), y + 13);

        if (spaceCraft == null) {
            g.drawString("Buy:", x + (GOOD_BG.getIconWidth() / 2)
                    - (g.getFontMetrics().stringWidth("Buy:") / 2), y + 33);
            g.drawString(buyPrice + "", x + (GOOD_BG.getIconWidth() / 2)
                    - (g.getFontMetrics().stringWidth(buyPrice + "") / 2),
                    y + 53);
        } else {
            g.drawString("Sell:", x + (GOOD_BG.getIconWidth() / 2)
                    - (g.getFontMetrics().stringWidth("Sell:") / 2), y + 33);
            g.drawString(sellPrice + "", x + (GOOD_BG.getIconWidth() / 2)
                    - (g.getFontMetrics().stringWidth(sellPrice + "") / 2),
                    y + 53);
        }

        g.drawString(type.toString(), x + (GOOD_BG.getIconWidth() / 2)
                - (g.getFontMetrics().stringWidth(type.toString()) / 2), y + 73);
    }

    /**
     * Gets the quantity.
     * 
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    /**
     * Checks if a good is clicked and has a quantity greater than 0.
     * 
     * @param point
     * @return True if contained and there is a good to be used, otherwise
     *         false.
     */
    public boolean isClicked(Point point) {
        return bounds.contains(point) && quantity > 0;
    }

    /**
     * Gets the type of a good.
     * 
     * @return GoodType
     */
    public GoodType getType() {
        return type;
    }

    /**
     * Gets the x value.
     * 
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y value.
     * 
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Set sellPrice
     * 
     * @param sellPrice
     */
    public void setSalePrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * Creates a string representation of the good.
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Good of type " + type + " with buy price: " + buyPrice
                + " and sell price: " + sellPrice + ".");
        return res.toString();
    }

    /**
     * Decreases the quantity of a good.
     */
    public void removeGood() {
        quantity--;
    }
}