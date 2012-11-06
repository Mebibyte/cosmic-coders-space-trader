// $codepro.audit.disable numericLiterals, multiplicationOrDivisionByPowersOf2, expressionValue
/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

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
    public static final int NUM_PLANETS = 120;
    
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
    private Planet[] planets = new Planet[NUM_PLANETS];
    
    /**
     * Field bounds.
     * Rectangle used for checking if clicked.
     */
    private final Rectangle bounds;

    /**
     * Constructor for Galaxy.
     * @param height Height of JFrame.
     * @param width Width of JFrame.
     */
    public Galaxy(int height, int width) {
        for (int i = 0; i < NUM_PLANETS; i++) {
            planets[i] = new Planet(width, GALAXY_WIDTH, GALAXY_HEIGHT);
        }
        
        bounds = new Rectangle(-2, 
                height - (2 * Galaxy.HALF_GALAXY_HEIGHT) - 8, Galaxy.GALAXY_WIDTH + 16, 
                Galaxy.GALAXY_HEIGHT + 16);
    }

    /**
     * Method getStartingPlanet.
    
     * @return Planet */
    public Planet getStartingPlanet() {
        return planets[0];
    }
    
    /**
     * @param planets
     */
    public void setPlanets(Planet[] planets){
    	this.planets = planets;
    }
    
    /**
     * Method getStartingPlanet.
     * @param g Graphics
     * @param panel GamePanel
     * @param height 
     * @param width 
     */
    public void draw(Graphics g, GamePanel panel, int width, int height) {        
        for (Planet p : planets) {
            p.draw(g, panel, width, height);
        }
    }
    
    /**
     * Method toString.
    
     * @return String */
    public String toString() {
        final StringBuffer res = new StringBuffer("{");
        for (Planet p : planets) {
            res.append(p.toString());
            res.append(", ");
        }
        res.append('}');
        return res.toString();
    }
    
    /**
     * getPlanets method.
     * Returns an array of Planets.
     * 
    
     * @return array of planets. */
    public Planet[] getPlanets(){
    	return planets;
    }

    /**
     * isClicked method for galaxy.
     * 
     * @param point Point clicked
    
     * @return If the galaxy has been clicked. */
    public boolean isClicked(Point point) {
        return bounds.contains(point);
    }

    /**
     * drawMiniMap method.
     * Draws the minimap for the galaxy.
     * 
     * @param g Graphics
     * @param panel GamePanel
     * @param width Width of JFrame
     * @param height Height of JFrame
     */
    public void drawMiniMap(Graphics g, GamePanel panel, int width,
            int height) {
        g.drawRect(width - HALF_GALAXY_WIDTH - 8, height - HALF_GALAXY_HEIGHT - 8,
                GALAXY_WIDTH + 16, GALAXY_HEIGHT + 16);
        
        for (Planet p : planets) {
            p.drawMini(g, panel, width, height);
        }
    }

    /**
     * AdvanceTime method.
     * Advances the time of all planets.
     */
    public void advanceTime() {
        for (Planet p : planets) {
            p.advanceTime();
        }
    }
}
