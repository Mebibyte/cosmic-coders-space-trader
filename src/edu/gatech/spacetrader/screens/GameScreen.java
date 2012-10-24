// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.spacetrader.gui.BigButton;
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
    private final int width, height;
    
    /**
     * Field galaxy.
     */
    private final Galaxy galaxy = new Galaxy();
    
    /**
     * Field currentPlanet.
     */
    private Planet currentPlanet;
    
    /**
     * Field buy.
     */
    /**
     * Field sell.
     */
    /**
     * Field fly.
     */
    private final BigButton buy, sell, fly;

    /**
     * Constructor for GameScreen.
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
        currentPlanet = galaxy.getStartingPlanet();
        buy = new BigButton("Buy", (width / 2) - (BigButton.getWidth() / 2), 
                (height / 2) + 50, true);
        sell = new BigButton("Sell", (width / 2) + (BigButton.getWidth() / 2), 
                (height / 2) + 50, true);
        fly = new BigButton("Fly", Galaxy.HALF_GALAXY_WIDTH, 
                height - Galaxy.GALAXY_HEIGHT - sell.getHeight(), true);
    }

    /**
     * Method draw.
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        final int x = 0;
        final int y = g.getFontMetrics().getHeight();
        
        g.drawString(player.getName(), x, y);
        g.drawString("Pilot: " + player.getSkillsArray()[0], x, y * 3);
        g.drawString("Fighter: " + player.getSkillsArray()[1], x, y * 4);
        g.drawString("Trader: " + player.getSkillsArray()[2], x, y * 5);
        g.drawString("Engineer: " + player.getSkillsArray()[3], x, y * 6);
        g.drawString(player.getCredits() + " credits", x,  y * 8);
        g.drawString(player.getSpaceCraft().toString(), x, y * 9);
        g.drawString(player.getDifficulty(), x, y * 10);
        g.drawString(currentPlanet.toString(), x, y * 11);
        
        g.drawLine(Galaxy.GALAXY_WIDTH + 9, 0, Galaxy.GALAXY_WIDTH + 9, height);
        galaxy.draw(g, panel, Galaxy.HALF_GALAXY_WIDTH,
                height - Galaxy.HALF_GALAXY_HEIGHT);
        g.setColor(Color.GRAY);
        //Draw radius of travel. Depends on "speed" ability of the player's current ship
        g.drawOval(currentPlanet.getX() - 10,
                currentPlanet.getY() + height - (2 * Galaxy.HALF_GALAXY_HEIGHT) - 10,
                20, 20); //TODO: Change boundaries based on player's ship "speed" in later versions. Maybe speed*10?
        g.setColor(Color.BLACK);
        
        currentPlanet.getMarket().draw(g, panel, width / 2, height / 2);
        buy.draw(g, panel, width, height);
        sell.draw(g, panel, width, height);
        fly.draw(g, panel, width, height);
    }

    /**
     * Method checkForClick.
     * @param point Point
     */
    @Override
    public void checkForClick(Point point){
        if (buy.isClicked(point)) {
            System.out.println("Bought!"); //TODO
        } else if (sell.isClicked(point)) {
            System.out.println("Sold!"); //TODO
        } else if (fly.isClicked(point)) {
            System.out.println("Go to fly screen!"); //TODO
        }
    }
    
    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString(){
        return "Game screen";
    }
    
    /**
     * Method changePlanet.
     * @param planet Planet
     */
    public void changePlanet(Planet planet) {
        currentPlanet = planet;
    }
}
