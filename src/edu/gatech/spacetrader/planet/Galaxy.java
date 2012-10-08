/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

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
     * Field planets.
     */
    private final Planet[] planets = new Planet[NUM_PLANETS];

    /**
     * Constructor for Galaxy.
     */
    public Galaxy() {
        for (int i = 0; i < NUM_PLANETS; i++) {
            planets[i] = new Planet();
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
