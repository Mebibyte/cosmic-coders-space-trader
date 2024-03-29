// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Color;
import java.awt.Font;
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
     * Field width & height
     */
    public final int width, height;

    /**
     * Field galaxy.
     */
    private Galaxy galaxy;

    /**
     * Field currentPlanet.
     */
    private Planet currentPlanet;

    /**
     * Field fuel button
     */
    private final Rectangle fillFuelButton;

    /**
     * Field BG
     */
    private static final ImageIcon BG = new ImageIcon(
            GameScreen.class.getResource("/edu/gatech/spacetrader/res/market.png"));

    /**
     * Constructor for GameScreen.
     * 
     * @param player Player
     * @param panel GamePanel
     * @param width int
     * @param height int
     */
    public GameScreen(Player player, GamePanel panel, int width, int height) {
        this.player = player;
        this.panel = panel;
        this.width = width;
        this.height = height;
        this.galaxy = new Galaxy(height, width);

        currentPlanet = galaxy.getStartingPlanet();
        player.getSpaceCraft().setSellPrices(currentPlanet.getMarket());
        fillFuelButton = new Rectangle(40, 200, 85, 36);
    }

    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        final FontMetrics fm = g.getFontMetrics();
        
        g.setFont(new Font("serif", Font.PLAIN, 12));
        
        //---------------------
        // Mini Map
        //---------------------
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 164, height);
        
        g.setColor(Color.BLACK);
        galaxy.drawMiniMap(g, panel, Galaxy.HALF_GALAXY_WIDTH + 6, height
                - Galaxy.HALF_GALAXY_HEIGHT);
        
        g.setColor(Color.WHITE);
        g.drawOval(currentPlanet.getX() - (player.getSpaceCraft().getSpeed()
                + player.getSkillsArray()[0] / 2) * 5,
                currentPlanet.getY() + height - (2 * Galaxy.HALF_GALAXY_HEIGHT)
                        - (player.getSpaceCraft().getSpeed()
                                + player.getSkillsArray()[0] / 2) * 5,
                (player.getSpaceCraft().getSpeed()
                        + player.getSkillsArray()[0] / 2) * 10,
                (player.getSpaceCraft().getSpeed()
                        + player.getSkillsArray()[0] / 2) * 10);
        
        g.setColor(Color.DARK_GRAY);        
        g.fillRect(0, height - Galaxy.GALAXY_HEIGHT - 40, 164, 32);
        
        g.setColor(Color.WHITE);
        g.drawString("Click Minimap to fly!", ((Galaxy.GALAXY_WIDTH + 9) / 2)
                - fm.stringWidth("Click Minimap to fly!") / 2, height
                - (Galaxy.GALAXY_HEIGHT + 10) - 1);
        
        BG.paintIcon(panel, g, 0, 0);
        
        final int x = 0;
        final int y = fm.getHeight();
        final int halfSidebarWidth = 82;
        final int sidebarWidth = 176;
        
        //---------------------
        //  Event
        //---------------------
        
        g.setColor(Color.BLACK);
        g.fillRect(sidebarWidth, 0, width, y + 5);
        g.setColor(Color.WHITE);
        g.drawString(currentPlanet.getCurrentEvent().getEventString(), sidebarWidth, y);
        
        //---------------------
        // Player Information
        //---------------------
        
        g.setColor(Color.WHITE);
        g.drawString("Player Information",
                halfSidebarWidth - (fm.stringWidth("Player Information") / 2), y);
        player.drawInfo(g, panel, x, y);

        // ------------
        // FUEL
        // ------------
        
        if (fillFuelButton.contains(getHoverPoint())) {
            g.setColor(Color.GREEN);
            g.fillRoundRect(fillFuelButton.x, fillFuelButton.y, fillFuelButton.width,
                    fillFuelButton.height, 50, 50);
            g.setColor(Color.BLACK);
        }
        g.drawRoundRect(fillFuelButton.x, fillFuelButton.y, fillFuelButton.width,
                fillFuelButton.height, 50, 50);

        g.drawString("Fill Fuel", fillFuelButton.x + ((fillFuelButton.width / 2) 
                        - (fm.stringWidth("Fill Fuel") / 2)), fillFuelButton.y + y);
        
        final String fuelCost = (-1 * (player.getSpaceCraft().getFuel() - 100) * 5 / 10)
                + " credits";
        
        g.drawString(fuelCost, fillFuelButton.x + ((fillFuelButton.width / 2)
                    - (fm.stringWidth(fuelCost) / 2)), fillFuelButton.y + y * 2);

        player.getSpaceCraft().drawFuel(g, panel, x, y * 11);
        
        //---------------------
        // Planet Information
        //---------------------
        
        g.setColor(Color.WHITE);
        g.drawString("Planet Information",
                halfSidebarWidth - (fm.stringWidth("Player Information") / 2),
                y * 19);
        currentPlanet.drawInfo(g, x, y * 20);

        currentPlanet.getMarket().draw(g, panel);
        player.getSpaceCraft().drawStorage(g, panel);
        super.draw(g);
    }

    /**
     * Method checkForClick.
     * 
     * @param point Point
     */
    @Override
    public void checkForClick(Point point) {
        if (!isPaused()) {
            if (galaxy.isClicked(point)) {
                panel.setActiveScreen(new FlyScreen(this, panel));
            } else if (fillFuelButton.contains(point)) {
                player.fillFuel();
            } else {
                final Good bought = currentPlanet.getMarket().goodClicked(point);
                if (bought != null) {
                    if (player.canSpend(bought.getBuyPrice())
                            && player.getSpaceCraft().canAddToStorage()) {
                        player.useCredits(bought.getBuyPrice() * -1);
                        player.getSpaceCraft().addToStorage(bought);
                        currentPlanet.getMarket().boughtGood(bought);
                    }
                }
    
                final Good sold = player.getSpaceCraft().goodClicked(point);
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
        super.checkForClick(point);
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
     * @param planet Planet
     */
    public void changePlanet(Planet planet) {
        final int xSquared = (planet.getX() - currentPlanet.getX())
                * (planet.getX() - currentPlanet.getX());
        final int ySquared = (planet.getY() - currentPlanet.getY())
                * (planet.getY() - currentPlanet.getY());
        final int distance = (int) Math.sqrt(xSquared + ySquared); // $codepro.audit.disable lossOfPrecisionInCast
        currentPlanet = planet;
        galaxy.advanceTime(this);
        player.getSpaceCraft().updatePrices(planet.getMarket());
        player.getSpaceCraft().fly(distance); 
        player.checkForEvent(planet.getCurrentEvent());  
    }

    /**
     * Method getPlayer.
     * 
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Method getGalaxy.
     * 
     * @return Galaxy
     */
    public Galaxy getGalaxy() {
        return galaxy;
    }

    /**
     * @param currentPlanet
     */
    public void setCurrentPlanet(Planet currentPlanet){
    	this.currentPlanet = currentPlanet;
    }
    
    /**
     * @param galaxy
     */
    public void setGalaxy(Galaxy galaxy){
    	this.galaxy = galaxy;
    }
    
    /**
     * Method getCurrentPlanet.
     * 
     * @return Planet
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
}
