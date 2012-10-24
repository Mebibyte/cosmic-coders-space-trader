// $codepro.audit.disable numericLiterals, multiplicationOrDivisionByPowersOf2, expressionValue
/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

import java.awt.Graphics;

import edu.gatech.spacetrader.main.GamePanel;

/**
 * @author Glenn
 * @version 2.0
 */
public class Galaxy {
    /**
     * Field NUM_PLANETS.
     * (value is 120)
     */
    private static final int NUM_PLANETS = 120;
    
    /**
     * Field GALAXY_WIDTH.
     * (value is 150)
     */
    /**
     * Field GALAXY_HEIGHT.
     * (value is 100)
     */
    public static final int GALAXY_WIDTH = 150,  GALAXY_HEIGHT = 100;
    
    /**
     * Field HALF_GALAXY_WIDTH.
     * (value is 75)
     */
    /**
     * Field HALF_GALAXY_HEIGHT.
     * (value is 50)
     */
    public static final int HALF_GALAXY_WIDTH = 75,  HALF_GALAXY_HEIGHT = 50;

    /**
     * Field planets.
     */
    private final Planet[] planets = new Planet[NUM_PLANETS];

    /**
     * Constructor for Galaxy.
     */
    public Galaxy(int width) {
        for (int i = 0; i < NUM_PLANETS; i++) {
            planets[i] = new Planet(width, GALAXY_WIDTH, GALAXY_HEIGHT);
        }
    }

    /**
     * Method getStartingPlanet.
     * @return Planet
     */
    public Planet getStartingPlanet() {
        return planets[0];
    }
    
    /**
     * Method getStartingPlanet.
     * @param g Graphics
     * @param panel GamePanel
     * @param height 
     * @param width 
     */
    public void draw(Graphics g, GamePanel panel, int width, int height) {
        g.drawRect(width - HALF_GALAXY_WIDTH - 8, height - HALF_GALAXY_HEIGHT - 8,
                GALAXY_WIDTH + 16, GALAXY_HEIGHT + 16);
        /*I expanded the border a bit. We may need to find a real workaround for when the 
        *player's travel radius increases beyond the border */
        for (Planet p : planets) {
            p.draw(g, panel, width, height);
        }
    }
    
    /**
     * Method toString.
     * @return String
     */
    public String toString() {
        final StringBuffer res = new StringBuffer("{");
        for (Planet p : planets) {
            res.append(p.toString());
            res.append(", ");
        }
        res.append('}');
        return res.toString();
    }
}
