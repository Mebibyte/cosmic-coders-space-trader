// $codepro.audit.disable numericLiterals, multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

import java.awt.Color;
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
     * @param height 
     * @param width 
     */
    public void draw(Graphics g, GamePanel panel, int width, int height) {
        g.drawRect(width - HALF_GALAXY_WIDTH, height - HALF_GALAXY_HEIGHT,
                GALAXY_WIDTH, GALAXY_HEIGHT);
        for (Planet p : planets) {
        	switch(p.getEnvironment()){
        	case WATER:
        		g.setColor(Color.BLUE);
        		break;
        	case ICE:
        		g.setColor(new Color(0x36, 0xDB, 0xCA));
        		break;
        	case TROPICAL:
        		g.setColor(Color.GREEN);
        		break;
        	case DESERT:
        		g.setColor(new Color(0xCD, 0x95, 0x0C));
        		break;
        	case EARTHLIKE:
        		g.setColor(Color.CYAN);
        		break;
        	case JUNGLE:
        		g.setColor(new Color(00, 0x64, 00));
        		break;
        	default:
        		g.setColor(Color.BLACK);
        		break;
        	}
            g.fillOval(width - HALF_GALAXY_WIDTH + (p.getX() - 2),
                    height - HALF_GALAXY_HEIGHT + (p.getY() - 2), 4, 4); // $codepro.audit.disable numericLiterals
            g.setColor(Color.BLACK);
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