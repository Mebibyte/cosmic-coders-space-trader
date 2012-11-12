// $codepro.audit.disable numericLiterals, multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import edu.gatech.spacetrader.good.PlanetMarket;
import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.screens.GameScreen;

/**
 * @author Glenn
 * @version 2.0
 */
public class Planet {
    /**
     * Field PLANET_NAMES.
     */
    private static final String[] PLANET_NAMES = { "Acamar", "Adahn", "Aldea",
            "Andevian", "Antedi", "Balosnee", "Baratas", "Brax", "Bretel",
            "Calondia", "Campor", "Capelle", "Carzon", "Castor", "Cestus",
            "Cheron", "Courteney", "Daled", "Damast", "Davlos", "Deneb",
            "Deneva", "Devidia", "Draylon", "Drema", "Endor", "Esmee", "Exo",
            "Ferris", "Festen", "Fourmi", "Frolix", "Gemulon", "Guinifer",
            "Hades", "Hamlet", "Helena", "Hulst", "Iodine", "Iralius", "Janus",
            "Japori", "Jarada", "Jason", "Kaylon", "Khefka", "Kira", "Klaatu",
            "Klaestron", "Korma", "Kravat", "Krios", "Laertes", "Largo",
            "Lave", "Ligon", "Lowry", "Magrat", "Malcoria", "Melina", "Mentar",
            "Merik", "Mintaka", "Montor", "Mordan", "Myrthe", "Nelvana", "Nix",
            "Nyle", "Odet", "Og", "Omega", "Omphalos", "Orias", "Othello",
            "Parade", "Penthara", "Picard", "Pollux", "Quator", "Rakhar",
            "Ran", "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia",
            "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Sol", "Somari",
            "Stakoron", "Styris", "Talani", "Tamus", "Tantalos", "Tanuga",
            "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus",
            "Turkana", "Tyrus", "Umberlee", "Utopia", "Vadera", "Vagra",
            "Vandor", "Ventax", "Xenon", "Xerxes", "Yew", "Yojimbo", "Zalkon",
            "Zuul" };

    /**
     */
    public static enum Event {
        DROUGHT("This planet has a low supply of fresh water"),
        COLD("The planet has just experienced a sudden climate drop"),
        CROPFAIL("Unfavorable weather has caused this year's harvest to be poor"),
        WAR("Tensions between rival nations have broken into war"),
        BOREDOM("There is a quiet unrest among citizens here"),
        PLAGUE("Food is currently scarce"),
        LACKOFWORKERS("Economic difficulties have resulted in large worker strikes"),
        NONE("No event");
        //TODO make array of messages per event to add some variety;
        /**
         * Field eventString.
         */
        private final String eventString;
        
        /**
         * Constructor for event.
         * @param eventString
         */
        private Event(String eventString){
        	this.eventString = eventString;
        }
        
        /**
         * Gets the event string.
         * @return Event String
         */
        public String getEventString(){
        	return eventString;
        }
    }

    /**
     * @author Glenn
     */
    public static enum Environment {
        DESERT(new Color(0xCD, 0x95, 0x0C)), WATER(Color.BLUE), 
        JUNGLE(new Color(00, 0x64, 00)), ICE(new Color(0x36, 0xDB, 0xCA)), 
        EARTHLIKE(Color.CYAN), TROPICAL(Color.GREEN), UNDERGROUND(Color.BLACK);

        /**
         * Field color.
         */
        private final Color color;

        /**
         * Field images
         */
        private final ImageIcon[] images = new ImageIcon[3];
        
        /**
        
         * @return ImageIcon representation of environment. */
        private ImageIcon getRandomImage(){
        	return images[RAND.nextInt(images.length)];
        }
        
        /**
         * Constructor for Environment.
         * 
         * @param color Color.
         */
        private Environment(Color color) { // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods
            this.color = color;
            for (int i = 0; i < images.length; i++){
                String imgPath = "/edu/gatech/spacetrader/res/"
                        + this.toString() + i + ".png";
            	images[i] = new ImageIcon(Planet.class.getResource(imgPath));
            }
        }

        /**
         * Method getColor.
         * 
         * @return Color.
         */
        public Color getColor() {
            return color;
        }
        
        /**
         * String representation of good.
         * 
         * @return String.
         */
        @Override
        public String toString(){
            return Character.toUpperCase(name().charAt(0))
                    + name().substring(1).toLowerCase();
        }
    }

    /**
     * @author Glenn
     */
    public static enum TechLevel {
        PRE_AGRICULTURAL(0), AGRICULTURAL(1), MEDIEVAL(2), 
        RENAISSANCE(3), EARLY_INDUSTRIAL(4), INDUSTRIAL(5), 
        POST_INDUSTRIAL(6), HI_TECH(7);

        /**
         * Field LevelInt of TechLevel.
         */
        private final int levelInt;

        /**
         * TechLevel constructor to set level int used in calculations.
         * 
         * @param levelInt
         */
        private TechLevel(int levelInt) {
            this.levelInt = levelInt;
        }

        /**
         * Get the levelInt of a TechLevel.
         * 
         * @return levelInt.
         */
        public int getTechLevel() {
            return levelInt;
        }
        
        /**
         * String representation of good.
         * 
         * @return String.
         */
        @Override
        public String toString(){
            return Character.toUpperCase(name().charAt(0))
                    + name().substring(1).toLowerCase();
        }
    }

    /**
     * code pro is wrong here. this will change
     */
    private TechLevel techLevel;

    /**
     * 
     */
    private Environment environment;

    /**
     * 
     */
    private Event currentEvent;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field NAMES_USED.
     */
    private static final ArrayList<Integer> NAMES_USED = new ArrayList<Integer>();
    
    /**
     * Field LOCATIONS_USED.
     */
    private static final List<Rectangle> LOCATIONS_USED = new ArrayList<Rectangle>();

    /**
     * Field RAND.
     */
    private static final Random RAND = new Random();

    /**
     * Field NUM_PLANETS. (value is 120)
     */
    private static final int NUM_PLANETS = 120;

    /**
     * Field location.
     */
    private Point location;
    
    /**
     * Field icon
     */
    private final ImageIcon icon;

    /**
     * Field market.
     */
    private final PlanetMarket market;

    /**
     * Constructor for Planet.
     * 
     * @param width Width of JFrame.
     * @param galaxyWidth
     *            Width of galaxy.
     * @param galaxyHeight
     *            Height of the galaxy.
     */
    public Planet(int width, int galaxyWidth, int galaxyHeight) {
        if (NAMES_USED.size() == PLANET_NAMES.length) { // Reset used names & locations.
            NAMES_USED.clear();
            LOCATIONS_USED.clear();
        }
        
        int nextPlanet = RAND.nextInt(NUM_PLANETS);

        while (NAMES_USED.contains(nextPlanet)) {
            nextPlanet = RAND.nextInt(NUM_PLANETS);
        }

        name = PLANET_NAMES[nextPlanet];
        NAMES_USED.add(nextPlanet);
        
        location = new Point(RAND.nextInt(galaxyWidth), RAND.nextInt(galaxyHeight));
        
        boolean added = false;
        while (!added) {
            added = true;
            
            for (Rectangle r : LOCATIONS_USED) {
                if (r.contains(location)) {
                    added = false;
                }
            }
            
            if (!added) {
                location = new Point(RAND.nextInt(galaxyWidth), 
                        RAND.nextInt(galaxyHeight));
            }
        }
        
        LOCATIONS_USED.add(new Rectangle(location.x - 3, location.y - 3, 7, 7));

        techLevel = TechLevel.values()[RAND.nextInt(TechLevel.values().length)];
        environment = Environment.values()[RAND
                .nextInt(Environment.values().length)];

        currentEvent = Event.NONE;
        market = new PlanetMarket(this, 280,
                350);
        icon = environment.getRandomImage();
    }

    /**
     * This is the huge method that will generate random events, advance or
     * revert the civilization level, and determine market prices. It should be
     * called whenever the player flies to a new location.
     * @param gs GameScreen
     */
    public void advanceTime(GameScreen gs) {
        
        market.updatePrices();
        if(this.equals(gs.getCurrentPlanet())){
	        if (RAND.nextInt(100) == 0) { //TODO make advancing harder as civ. level increases. Put that number in TechLevel enum
	        	advanceCivilization();
	        	System.out.println(this.toString() + " advanced to " + 
	        	this.getTechLevel().toString());//TODO message box
	        }
	        if (isEventChanged()) {
	        	System.out.println(this.getCurrentEvent().getEventString()); //TODO message box
	        }
        }
    }

    /**
     * Method getX.
     * 
    
     * @return int X value of planet. */
    public int getX() {
        return location.x;
    }

    /**
     * Method getY.
     * 
    
     * @return int Y value of planet. */
    public int getY() {
        return location.y;
    }

    /**
    
     * @return The planet's environment type */
    public Environment getEnvironment() {
        return this.environment;
    }

    /**
    
     * @return The planet's tech level */
    public TechLevel getTechLevel() {
        return this.techLevel;
    }

    /**
     * Advance civilization to next level
     * called internally when a random conditional is met during player
     * movement.
     */
    private void advanceCivilization() { // $codepro.audit.disable unusedMethod
        switch (techLevel) {
        case PRE_AGRICULTURAL:
            techLevel = TechLevel.AGRICULTURAL;
            break;
        case AGRICULTURAL:
            techLevel = TechLevel.MEDIEVAL;
            break;
        case MEDIEVAL:
            techLevel = TechLevel.EARLY_INDUSTRIAL;
            break;
        case EARLY_INDUSTRIAL:
            techLevel = TechLevel.INDUSTRIAL;
            break;
        case INDUSTRIAL:
            techLevel = TechLevel.POST_INDUSTRIAL;
            break;
        case POST_INDUSTRIAL:
            techLevel = TechLevel.HI_TECH;
            break;
        default:
            break;
        }
    }

    /**
     * Method changeEvent. Changes the current event to a new random event.
     * @return if event changed.
     */
    public boolean isEventChanged() {
    	if (RAND.nextInt(4) == 0) { //create a one-in-four chance of an event occuring at all.
    		currentEvent = Event.values()[RAND.nextInt(Event.values().length)];
    		return true;
    	} else {
    		currentEvent = Event.NONE;
    		return false;
    	}
    }

    /**
    
     * @return A short descriptor of any recent planetary events that affect the
     *         market */
    public String eventMessage() {
        /*
         * TODO - Create a string-generator. More importantly, create a list
         * (enum) of possible events, e.g. Government uprising, famine. Events
         * can be triggered in the "advanceTime" method. Message is based on
         * "currentEvent" field. The message will be displayed when a player
         * arrives at a planet
         */
        return currentEvent.getEventString();
    }

    /**
     * Method toString.
     * 
    
     * @return String */
    public String toString() {
        return name;
    }

    /**
     * Draw the small version of the planet.
     * 
     * @param g Graphics
     * @param panel GamePanel
     * @param width
     * @param height
     */
    public void drawMini(Graphics g, GamePanel panel, int width, int height) {
        g.setColor(environment.getColor());
        g.fillOval(width - Galaxy.HALF_GALAXY_WIDTH + (location.x - 2), height
                - Galaxy.HALF_GALAXY_HEIGHT + (location.y - 2), 4, 4); // $codepro.audit.disable
                                                              // numericLiterals
        g.setColor(Color.BLACK);
    }
    
    /**
     * Gets the current event.
     * @return event
     */
    public Event getCurrentEvent(){
    	return this.currentEvent;
    }

    /**
     * Method draw.
     * 
     * @param g
     *            Graphics
     * @param panel
     *            GamePanel
     * @param width
     *            Int
     * @param height
     *            Int
     */
    public void draw(Graphics g, GamePanel panel, int width, int height) {
        //g.setColor(environment.getColor());
        //g.fillOval((x * 5), (y * 5), 20, 20); // $codepro.audit.disable numericLiterals
        icon.paintIcon(panel, g, (location.x * 5), (location.y * 5));
        g.setColor(Color.BLACK);
    }

    /**
     * Get the planetMarket of this planet.
     * 
    
     * @return planetMarket for this planet. */
    public PlanetMarket getMarket() {
        return market;
    }

    /**
     * Set X.
     * @param x
     */
    public void setX(int x) {
        location.x = x;
    }

    /**
     * Set Y.
     * @param y
     */
    public void setY(int y) {
        location.y = y;
    }

    /**
     * Set environment.
     * @param env
     */
    public void setEnvironment(String env) {
        environment = Environment.valueOf(env);
    }

    /**
     * Set tech level
     * @param tl
     */
    public void setTechLevel(String tl) {
        techLevel = TechLevel.valueOf(tl);
    }

    /**
     * Set name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Check if the planet is clicked.
     * @param p point
     * @return Clicked or not.
     */
    public boolean isClicked(Point p) {
        final int dx = p.x - ((location.x * 5) + 10);
        final int dy = p.y - ((location.y * 5) + 10);
        return dx * dx + dy * dy <= 100;
    }

    /**
     * Draw info about planet.
     * 
     * @param g Graphics.
     * @param x X position.
     * @param y Y position.
     */
    public void drawInfo(Graphics g, int x, int y) {
        g.drawString("Name: " + name, x, y);
        g.drawString("Environment: " + environment.toString(), x, y + 18);
        g.drawString("Tech Level: " + techLevel.toString(), x, y + 36);
    }
}
