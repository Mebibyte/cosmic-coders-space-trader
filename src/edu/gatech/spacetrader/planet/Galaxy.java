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
    private static final int GALAXY_WIDTH = 150, GALAXY_HEIGHT = 100;

    /**
     * Field planets.
     */
    private final Planet[] planets = new Planet[NUM_PLANETS];

    /**
     * Constructor for Galaxy.
     */
    public Galaxy() {
        for (int i = 0; i < NUM_PLANETS; i++) {
            planets[i] = new Planet(GALAXY_WIDTH, GALAXY_HEIGHT);
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
     */
    public void draw(Graphics g, GamePanel panel) {
        g.drawRect(0, 0, GALAXY_WIDTH, GALAXY_HEIGHT);
        for (Planet p : planets) {
            g.fillOval(p.getX() - 1, p.getY() - 1, 2, 2); // $codepro.audit.disable numericLiterals
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
