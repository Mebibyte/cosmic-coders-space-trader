// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

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
    
    private Rectangle fuelButton;

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
        fuelButton = new Rectangle(0, 146, 100, 18);
    }

    /**
     * Method draw.
     * 
     * @param g
     *            Graphics
     */
    @Override
    public void draw(Graphics g) {
        final int x = 0;
        final int y = g.getFontMetrics().getHeight();

        g.drawString(player.getName(), x, y);
        g.drawString(player.getDifficulty(), x, y * 2);
        g.drawString("Pilot: " + player.getSkillsArray()[0], x, y * 3);
        g.drawString("Fighter: " + player.getSkillsArray()[1], x, y * 4);
        g.drawString("Trader: " + player.getSkillsArray()[2], x, y * 5);
        g.drawString("Engineer: " + player.getSkillsArray()[3], x, y * 6);
        g.drawString(player.getCredits() + " credits", x, y * 8);
        g.drawString(player.getSpaceCraft().toString(), x, y * 9);
        
        g.drawRect(fuelButton.x, fuelButton.y, fuelButton.width, fuelButton.height);
        g.drawString("Buy Fuel", (fuelButton.width / 2) - (g.getFontMetrics().stringWidth("Buy Fuel") / 2), y * 10);
        player.getSpaceCraft().drawFuel(g, panel, x, y * 11);

        g.drawString(currentPlanet.toString(), x, (y * 11) + 120);
        g.drawString(currentPlanet.getTechLevel().getTechLevel() + "", x, (y * 12) + 120);

        g.drawLine(Galaxy.GALAXY_WIDTH + 9, 0, Galaxy.GALAXY_WIDTH + 9, height);

        galaxy.drawMiniMap(g, panel, Galaxy.HALF_GALAXY_WIDTH, height
                - Galaxy.HALF_GALAXY_HEIGHT);

        g.drawString("Click Minimap to fly!", ((Galaxy.GALAXY_WIDTH + 9) / 2)
                - g.getFontMetrics().stringWidth("Click Minimap to fly!") / 2,
                height - (Galaxy.GALAXY_HEIGHT + 10));
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
                (player.getSpaceCraft().getSpeed() + player.getSkillsArray()[0] / 2) * 10,
                (player.getSpaceCraft().getSpeed() 
                        + player.getSkillsArray()[0] / 2) * 10);
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
            if (player.getSpaceCraft().addFuel()) {
                player.useCredits(-5);
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
     * @return String
     */
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
        int xSquared = (planet.getX() - currentPlanet.getX()) * (planet.getX() - currentPlanet.getX());
        int ySquared = (planet.getY() - currentPlanet.getY()) * (planet.getY() - currentPlanet.getY());
        int distance = (int) Math.sqrt(xSquared + ySquared);
        currentPlanet = planet;
        galaxy.advanceTime();
        player.getSpaceCraft().updatePrices(planet.getMarket());
        player.getSpaceCraft().fly(distance);
    }

    public Player getPlayer() {
        return player;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
}
