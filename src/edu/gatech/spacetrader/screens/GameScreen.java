// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

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
    }

    /**
     * Method draw.
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        final int x = width / 2;
        final int y = height / 2;
        g.drawString(player.getName(), 
                x - (((g.getFontMetrics()).stringWidth(player.getName())) / 2), y);
        g.drawString(player.getSkills(), 
                x - (((g.getFontMetrics()).stringWidth(player.getSkills())) / 2), 
                y + g.getFontMetrics().getHeight());
        g.drawString(player.getCredits() + " credits", x 
                - (g.getFontMetrics().stringWidth(player.getCredits() + " credits") / 2), 
                y + (g.getFontMetrics().getHeight() * 2));
        g.drawString(player.getSpaceCraft().toString(), x 
                - (g.getFontMetrics().stringWidth(player.getSpaceCraft().toString()) / 2),
                y + (g.getFontMetrics().getHeight() * 3));
        g.drawString(player.getDifficulty(), 
                x - (((g.getFontMetrics()).stringWidth(player.getDifficulty())) / 2), 
                y + (g.getFontMetrics().getHeight() * 4));
        g.drawString(currentPlanet.toString(), 
                x - (((g.getFontMetrics()).stringWidth(currentPlanet.toString())) / 2), 
                y + (g.getFontMetrics().getHeight() * 5));
        galaxy.draw(g, panel, width / 2, (height / 2) - 70);
        g.drawOval(currentPlanet.getX() + (width / 2) - Galaxy.HALF_GALAXY_WIDTH - 5,
                currentPlanet.getY() + (height / 2) - 70 - Galaxy.HALF_GALAXY_HEIGHT - 5,
                10, 10); 
        g.setColor(Color.GRAY);
        //Draw radius of travel. Depends on "speed" ability of the player's current ship
        g.drawOval(currentPlanet.getX() + (width / 2) - Galaxy.HALF_GALAXY_WIDTH - 10,
                currentPlanet.getY() + (height / 2) - 70 - Galaxy.HALF_GALAXY_HEIGHT - 10,
                20, 20); //TODO: Change boundaries based on player's ship "speed" in later versions. Maybe speed*10?
        g.setColor(Color.BLACK);
    }

    /**
     * Method checkForClick.
     * @param point Point
     */
    @Override
    public void checkForClick(Point point) {
        System.out.println("Checking for clicks"); //FIXME
    }
    
    /**
     * Method toString.
    
     * @return String */
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