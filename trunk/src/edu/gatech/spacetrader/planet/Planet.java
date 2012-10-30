// $codepro.audit.disable numericLiterals, multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import edu.gatech.spacetrader.good.PlanetMarket;
import edu.gatech.spacetrader.main.GamePanel;

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

    public static enum Event {
        DROUGHT, COLD, CROPFAIL, WAR, BOREDOM, PLAGUE, LACKOFWORKERS, NONE
    }

    /**
     */
    public static enum Environment {
        DESERT(new Color(0xCD, 0x95, 0x0C)), WATER(Color.BLUE), JUNGLE(
                new Color(00, 0x64, 00)), ICE(new Color(0x36, 0xDB, 0xCA)), EARTHLIKE(
                Color.CYAN), TROPICAL(Color.GREEN), UNDERGROUND(Color.BLACK);

        /**
         * Field color.
         */
        private final Color color;

        /**
         * Field images
         */
        private final ImageIcon[] images;
        
        /**
         * @return
         */
        public ImageIcon getRandomImage(){
        	return images[RAND.nextInt(images.length)];
        }
        
        /**
         * Constructor for Environment.
         * 
         * @param color
         *            Color.
         */
        private Environment(Color color) {
            this.color = color;
            images = new ImageIcon[3];
            for (int i = 0; i < images.length; i++){
                String imgPath = "/edu/gatech/spacetrader/res/" + this.toString() + i + ".png";
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

    }

    /**
     */
    public static enum TechLevel {
        PRE_AGRICULTURAL(0), AGRICULTURAL(1), MEDIEVAL(2), RENAISSANCE(3), EARLY_INDUSTRIAL(
                4), INDUSTRIAL(5), POST_INDUSTRIAL(6), HI_TECH(7);

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
    @SuppressWarnings("unused")
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
     * Field RAND.
     */
    private static final Random RAND = new Random();

    /**
     * Field NUM_PLANETS. (value is 120)
     */
    private static final int NUM_PLANETS = 120;

    /**
     * Field x.
     */
    /**
     * Field y.
     */
    private int x, y;

    /**
     * Field market.
     */
    private final PlanetMarket market;

    /**
     * Constructor for Planet.
     * 
     * @param galaxyWidth
     *            Width of galaxy.
     * @param galaxyHeight
     *            Height of the galaxy.
     */
    public Planet(int width, int galaxyWidth, int galaxyHeight) {
        int nextPlanet = RAND.nextInt(NUM_PLANETS);

        while (NAMES_USED.contains(nextPlanet)) {
            nextPlanet = RAND.nextInt(NUM_PLANETS);
        }

        name = PLANET_NAMES[nextPlanet];
        NAMES_USED.add(nextPlanet);

        x = RAND.nextInt(galaxyWidth);
        y = RAND.nextInt(galaxyHeight);

        techLevel = TechLevel.values()[RAND.nextInt(TechLevel.values().length)];
        environment = Environment.values()[RAND
                .nextInt(Environment.values().length)];

        currentEvent = Event.NONE;
        market = new PlanetMarket(this, (width - Galaxy.GALAXY_WIDTH + 10) / 2,
                0);
    }

    /**
     * This is the huge method that will generate random events, advance or
     * revert the civilization level, and determine market prices. It should be
     * called whenever the player flies to a new location.
     */
    public void advanceTime() {
        // TODO - Everything
        market.updatePrices();
    }

    /**
     * Method getX.
     * 
     * @return int X value of planet.
     */
    public int getX() {
        return x;
    }

    /**
     * Method getY.
     * 
     * @return int Y value of planet.
     */
    public int getY() {
        return y;
    }

    /**
     * @return The planet's environment type
     */
    public Environment getEnvironment() {
        return this.environment;
    }

    /**
     * @return The planet's civilization level
     */
    public TechLevel getCivLevel() {
        return this.techLevel;
    }

    /**
     * 
     */
    @SuppressWarnings("unused")
    private void advanceCivilization() { // $codepro.audit.disable unusedMethod
        // Advance civilization to next level
        // called internally when a random conditional is met during player
        // movement.
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
     */
    public void changeEvent() {
        currentEvent = Event.values()[new Random()
                .nextInt(Event.values().length)];
    }

    /**
     * @return A short descriptor of any recent planetary events that affect the
     *         market
     */
    public String eventMessage() {
        /*
         * TODO - Create a string-generator. More importantly, create a list
         * (enum) of possible events, e.g. Government uprising, famine. Events
         * can be triggered in the "advanceTime" method. Message is based on
         * "currentEvent" field. The message will be displayed when a player
         * arrives at a planet
         */
        return null;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    public String toString() {
        return name;
    }

    public void drawMini(Graphics g, GamePanel panel, int width, int height) {
        g.setColor(environment.getColor());
        g.fillOval(width - Galaxy.HALF_GALAXY_WIDTH + (x - 2), height
                - Galaxy.HALF_GALAXY_HEIGHT + (y - 2), 4, 4); // $codepro.audit.disable
                                                              // numericLiterals
        g.setColor(Color.BLACK);
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
        environment.getRandomImage().paintIcon(panel, g, (x * 5), (y * 5));
        g.setColor(Color.BLACK);
    }

    /**
     * Get the planetMarket of this planet.
     * 
     * @return planetMarket for this planet.
     */
    public PlanetMarket getMarket() {
        return market;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setEnvironment(String env) {
        environment = Environment.valueOf(env);
    }

    public void setCivLevel(String civ) {
        techLevel = TechLevel.valueOf(civ);
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkForClick(Point p) {
        int dx = p.x - ((x * 5) + 10);
        int dy = p.y - ((y * 5) + 10);
        return dx * dx + dy * dy <= 10 * 10;
    }
}
