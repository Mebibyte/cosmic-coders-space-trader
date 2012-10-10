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
        	switch(p.getEnvironment()){
        	case WATER:
        		g.setColor(Color.BLUE);
        		break;
        	case ICE:
        		g.setColor(new Color(0x36,0xDB,0xCA));
        		break;
        	case TROPICAL:
        		g.setColor(Color.GREEN);
        		break;
        	case DESERT:
        		g.setColor(new Color(0xCD,0x95,0x0C));
        		break;
        	case EARTHLIKE:
        		g.setColor(Color.CYAN);
        		break;
        	case JUNGLE:
        		g.setColor(new Color(00,0x64,00));
        		break;
        	default:
        		g.setColor(Color.BLACK);
        		break;
        	}
            g.fillOval(p.getX() - 2, p.getY() - 2, 4, 4); // $codepro.audit.disable numericLiterals
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
