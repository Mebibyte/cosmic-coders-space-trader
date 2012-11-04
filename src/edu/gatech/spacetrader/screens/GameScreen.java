// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import edu.gatech.spacetrader.good.Good;
import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.planet.Galaxy;
import edu.gatech.spacetrader.planet.Planet;
import edu.gatech.spacetrader.player.Player;

/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class GameScreen extends Screen {

    /**
     * Field player.
     */
    private final Player player;

    /**
     * Field panel.
     */
    private final GamePanel panel;

    /**
     * Field height.
     */
    /**
     * Field width.
     */
    public final int width, height;

    /**
     * Field galaxy.
     */
    private final Galaxy galaxy;

    /**
     * Field currentPlanet.
     */
    private Planet currentPlanet;

    /**
     * Field fuel button
     */
    private Rectangle fuelButton, fillFuelButton;

    /**
     * Field BG
     */
    private static final ImageIcon BG = new ImageIcon(
            GameScreen.class
                    .getResource("/edu/gatech/spacetrader/res/market.jpg"));

    /**
     * Constructor for GameScreen.
     * 
     * @param player
     *            Player
     * @param panel
     *            GamePanel
     * @param width
     *            int
     * @param height
     *            int
     */
    public GameScreen(Player player, GamePanel panel, int width, int height) {
        this.player = player;
        this.panel = panel;
        this.width = width;
        this.height = height;
        this.galaxy = new Galaxy(height, width);
        currentPlanet = galaxy.getStartingPlanet();
        player.getSpaceCraft().setSellPrices(currentPlanet.getMarket());
        fuelButton = new Rectangle(40, 182, 85, 18);
        fillFuelButton = new Rectangle(40, 218, 85, 18);
    }

    /**
     * Method draw.
     * 
     * @param g
     *            Graphics
     */
    @Override
    public void draw(Graphics g) {
        BG.paintIcon(panel, g, 0, 0);

        final FontMetrics fm = g.getFontMetrics();
        final int x = 0;
        final int y = fm.getHeight();
        final int sidebarWidth = 164;

        g.drawString("Player Information",
                (sidebarWidth / 2) - (fm.stringWidth("Player Information") / 2),
                y);
        player.drawInfo(g, panel, x, y);
        
        g.drawRect(fuelButton.x, fuelButton.y, fuelButton.width,
                fuelButton.height);
        g.drawString("10 Fuel - 5 cr",
                fuelButton.x + ((fuelButton.width / 2) - (fm.stringWidth("10 Fuel - 5 cr") / 2)),
                fuelButton.y + y);
        
        g.drawRect(fillFuelButton.x, fillFuelButton.y, fillFuelButton.width,
                fillFuelButton.height);
        g.drawString("Fill Fuel - " + (-1 * (player.getSpaceCraft().getFuel() - 100) * 5 / 10) + " cr",
                fillFuelButton.x + ((fillFuelButton.width / 2) - (fm.stringWidth("Fill Fuel - " + (-1 * (player.getSpaceCraft().getFuel() - 100) * 5 / 10) + " cr") / 2)),
                fillFuelButton.y + y);
        
        player.getSpaceCraft().drawFuel(g, panel, x, y * 11);
        
        g.drawString("Planet Information",
                (sidebarWidth / 2) - (fm.stringWidth("Player Information") / 2),
                y * 19);
        currentPlanet.drawInfo(g, x, y * 20);

        galaxy.drawMiniMap(g, panel, Galaxy.HALF_GALAXY_WIDTH + 6, height
                - Galaxy.HALF_GALAXY_HEIGHT);

        g.drawString("Click Minimap to fly!", ((Galaxy.GALAXY_WIDTH + 9) / 2)
                - fm.stringWidth("Click Minimap to fly!") / 2, height
                - (Galaxy.GALAXY_HEIGHT + 10));

        g.setColor(Color.GRAY);
        g.drawOval(
                currentPlanet.getX()
                        - (player.getSpaceCraft().getSpeed() + player
                                .getSkillsArray()[0] / 2) * 5,
                currentPlanet.getY()
                        + height
                        - (2 * Galaxy.HALF_GALAXY_HEIGHT)
                        - (player.getSpaceCraft().getSpeed() + player
                                .getSkillsArray()[0] / 2) * 5,
                (player.getSpaceCraft().getSpeed() + 
                        player.getSkillsArray()[0] / 2) * 10,
                (player.getSpaceCraft().getSpeed() + 
                        player.getSkillsArray()[0] / 2) * 10);
        g.setColor(Color.BLACK);

        currentPlanet.getMarket().draw(g, panel);
        player.getSpaceCraft().drawStorage(g, panel);
    }

    /**
     * Method checkForClick.
     * 
     * @param point
     *            Point
     */
    @Override
    public void checkForClick(Point point) {
        if (galaxy.isClicked(point) && player.getSpaceCraft().canFly()) {
            panel.setActiveScreen(new FlyScreen(this, panel, width, height));
        } else if (fuelButton.contains(point)) {
            if (player.getSpaceCraft().canAddFuel()) {
                player.useCredits(-5);
            }
        } else if (fillFuelButton.contains(point)) {
            int spent = (player.getSpaceCraft().getFuel() - 100) * 5 / 10;
            if (player.getSpaceCraft().canFillFuel()) {
                player.useCredits(spent);
            }
        } else {
            Good bought = currentPlanet.getMarket().goodClicked(point);
            if (bought != null) {
                if (player.canSpend(bought.getBuyPrice())
                        && player.getSpaceCraft().canAddToStorage()) {
                    player.useCredits(bought.getBuyPrice() * -1);
                    player.getSpaceCraft().addToStorage(bought);
                    currentPlanet.getMarket().boughtGood(bought);
                }
            }

            Good sold = player.getSpaceCraft().goodClicked(point);
            if (sold != null) {
                if (sold.getSellPrice() > 0
                        && player.getSpaceCraft().canSellGood(sold)) {
                    player.useCredits(sold.getSellPrice());
                    player.getSpaceCraft().removeGood(sold);
                    currentPlanet.getMarket().soldGood(sold);
                }
            }
        }
    }

    /**
     * Method toString.
     * 
    
     * @return String */
    @Override
    public String toString() {
        return "Game screen";
    }

    /**
     * Method changePlanet.
     * 
     * @param planet
     *            Planet
     */
    public void changePlanet(Planet planet) {
        int xSquared = (planet.getX() - currentPlanet.getX())
                * (planet.getX() - currentPlanet.getX());
        int ySquared = (planet.getY() - currentPlanet.getY())
                * (planet.getY() - currentPlanet.getY());
        int distance = (int) Math.sqrt(xSquared + ySquared);
        currentPlanet = planet;
        galaxy.advanceTime();
        player.getSpaceCraft().updatePrices(planet.getMarket());
        player.getSpaceCraft().fly(distance);
    }

    /**
     * Method getPlayer.
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Method getGalaxy.
     * @return Galaxy
     */
    public Galaxy getGalaxy() {
        return galaxy;
    }

    /**
     * Method getCurrentPlanet.
     * @return Planet
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
}
