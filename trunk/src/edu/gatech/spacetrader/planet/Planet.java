// $codepro.audit.disable numericLiterals
/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

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
    private static final String[] PLANET_NAMES = {
            "Acamar",
            "Adahn",        // The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",         // One of the heroes in Master of Magic
            "Bretel",       // This is a Dutch device for keeping your pants up.
            "Calondia",
            "Campor",
            "Capelle",      // The city I lived in while programming this game
            "Carzon",
            "Castor",       // A Greek demi-god
            "Cestus",
            "Cheron",       
            "Courteney",    // After Courteney Cox
            "Daled",
            "Damast",
            "Davlos",
            "Deneb",
            "Deneva",
            "Devidia",
            "Draylon",
            "Drema",
            "Endor",
            "Esmee",        // One of the witches in Pratchett's Discworld
            "Exo",
            "Ferris",       // Iron
            "Festen",       // A great Scandinavian movie
            "Fourmi",       // An ant, in French
            "Frolix",       // A solar system in one of Philip K. Dick's novels
            "Gemulon",
            "Guinifer",     // One way of writing the name of king Arthur's wife
            "Hades",        // The underworld
            "Hamlet",       // From Shakespeare
            "Helena",       // Of Troy
            "Hulst",        // A Dutch plant
            "Iodine",       // An element
            "Iralius",
            "Janus",        // A seldom encountered Dutch boy's name
            "Japori",
            "Jarada",
            "Jason",        // A Greek hero
            "Kaylon",
            "Khefka",
            "Kira",         // My dog's name
            "Klaatu",       // From a classic SF movie
            "Klaestron",
            "Korma",        // An Indian sauce
            "Kravat",       // Interesting spelling of the French word for "tie"
            "Krios",
            "Laertes",      // A king in a Greek tragedy
            "Largo",
            "Lave",         // The starting system in Elite
            "Ligon",
            "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
            "Magrat",       // The second of the witches in Pratchett's Discworld
            "Malcoria",
            "Melina",
            "Mentar",       // The Psilon home system in Master of Orion
            "Merik",
            "Mintaka",
            "Montor",       // A city in Ultima III and Ultima VII part 2
            "Mordan",
            "Myrthe",       // The name of my daughter
            "Nelvana",
            "Nix",          // An interesting spelling of a word meaning "nothing" in Dutch
            "Nyle",         // An interesting spelling of the great river
            "Odet",
            "Og",           // The last of the witches in Pratchett's Discworld
            "Omega",        // The end of it all
            "Omphalos",     // Greek for navel
            "Orias",
            "Othello",      // From Shakespeare
            "Parade",       // This word means the same in Dutch and in English
            "Penthara",
            "Picard",       // The enigmatic captain from ST:TNG
            "Pollux",       // Brother of Castor
            "Quator",
            "Rakhar",
            "Ran",          // A film by Akira Kurosawa
            "Regulas",
            "Relva",
            "Rhymus",
            "Rochani",
            "Rubicum",      // The river Ceasar crossed to get into Rome
            "Rutia",
            "Sarpeidon",
            "Sefalla",
            "Seltrice",
            "Sigma",
            "Sol",          // That's our own solar system
            "Somari",
            "Stakoron",
            "Styris",
            "Talani",
            "Tamus",
            "Tantalos",     // A king from a Greek tragedy
            "Tanuga",
            "Tarchannen",
            "Terosa",
            "Thera",        // A seldom encountered Dutch girl's name
            "Titan",        // The largest moon of Jupiter
            "Torin",        // A hero from Master of Magic
            "Triacus",
            "Turkana",
            "Tyrus",
            "Umberlee",     // A god from AD&D, which has a prominent role in Baldur's Gate
            "Utopia",       // The ultimate goal
            "Vadera",
            "Vagra",
            "Vandor",
            "Ventax",
            "Xenon",
            "Xerxes",       // A Greek hero
            "Yew",          // A city which is in almost all of the Ultima games
            "Yojimbo",      // A film by Akira Kurosawa
            "Zalkon",
            "Zuul"          // From the first Ghostbusters movie
        };
    
    public static enum Event{
    	DROUGHT, COLD, CROPFAIL, WAR, BOREDOM, PLAGUE, LACKOFWORKERS, NONE
    }
    
    /**
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
    	 * Constructor for Environment.
    	 * 
    	 * @param color Color.
         */
    	private Environment(Color color) {
    	    this.color = color;
    	}
    	
    	/**
         * Method getColor.
         * 
         * @return Color.
         */
    	public Color getColor(){
    	    return color;
    	}
    }
    
    /**
     */
    public static enum TechLevel{
    	PRE_AGRICULTURAL(0), AGRICULTURAL(1), MEDIEVAL(2), RENAISSANCE(3), EARLY_INDUSTRIAL(4),
    	INDUSTRIAL(5), POST_INDUSTRIAL(6), HI_TECH(7);
    	
    	private int levelInt;
    	
    	private TechLevel(int levelInt) {
    	    this.levelInt = levelInt;
    	}

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
    private final Environment environment;
    
    /**
     * 
     */
    @SuppressWarnings("unused")
	private Event currentEvent;
    
    /**
     * Field name.
     */
    private final String name;
    
    /**
     * Field NAMES_USED.
     */
    private static final ArrayList<Integer> NAMES_USED = new ArrayList<Integer>();
    
    /**
     * Field RAND.
     */
    private static final Random RAND = new Random();
    
    /**
     * Field NUM_PLANETS.
     * (value is 120)
     */
    private static final int NUM_PLANETS = 120;
    
    /**
     * Field x.
     */
    /**
     * Field y.
     */
    private final int x, y;
    
    /**
     * Field market.
     */
    private final PlanetMarket market;
    
    /**
     * Constructor for Planet.
     * @param galaxyWidth Width of galaxy.
     * @param galaxyHeight Height of the galaxy.
     */
    public Planet(int galaxyWidth, int galaxyHeight){
        int nextPlanet = RAND.nextInt(NUM_PLANETS);
        
        while (NAMES_USED.contains(nextPlanet)) {
            nextPlanet = RAND.nextInt(NUM_PLANETS);
        }
        
        name = PLANET_NAMES[nextPlanet];
        NAMES_USED.add(nextPlanet);
        
        x = RAND.nextInt(galaxyWidth);
        y = RAND.nextInt(galaxyHeight);
        
        techLevel = TechLevel.values()
        		[RAND.nextInt(TechLevel.values().length)];
        environment = Environment.values()[RAND.nextInt(Environment.values().length)];
        
        currentEvent = Event.NONE;
        market = new PlanetMarket(this);
    }
    
    /**
     * This is the huge method that will generate random events, 
     * advance or revert the civilization level,
     * and determine market prices.
     * It should be called whenever the player flies to a new location.
     */
    public void advanceTime(){
    	//TODO - Everything
        System.out.println("Time++");
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
     * @return int Y value of planet.
     */
    public int getY() {
        return y;
    }
    
    /**
     * @return The planet's environment type
     */
    public Environment getEnvironment(){
    	return this.environment;
    }
    
    /**
     * @return The planet's civilization level
     */
    public TechLevel getCivLevel(){
    	return this.techLevel;
    }
    
    /**
     * 
     */
    @SuppressWarnings("unused")
    private void advanceCivilization(){ // $codepro.audit.disable unusedMethod
    	//Advance civilization to next level
    	//called internally when a random conditional is met during player movement.
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
     * Method changeEvent.
     * Changes the current event to a new random event.
     */
    public void changeEvent(){
        currentEvent = Event.values()[new Random().nextInt(Event.values().length)];
    }
    
    /**
     * @return A short descriptor of any recent planetary events that affect the market
     */
    public String eventMessage(){
    	/*TODO - Create a string-generator. More importantly,
    	 * create a list (enum) of possible events,
    	 * e.g. Government uprising, famine.
    	 * Events can be triggered in the "advanceTime" method.
    	 * Message is based on "currentEvent" field.
    	 * The message will be displayed when a player arrives at a planet
    	*/
    	return null;
    }
    
    /**
     * Method toString.
     * @return String
     */
    public String toString(){
        return name;
    }
    
    /**
     * Method draw.
     * 
     * @param g Graphics
     * @param panel GamePanel
     * @param width Int
     * @param height Int
     */
    public void draw(Graphics g, GamePanel panel, int width, int height) {
        g.setColor(environment.getColor());
        g.fillOval(width - Galaxy.HALF_GALAXY_WIDTH + (x - 2),
                    height - Galaxy.HALF_GALAXY_HEIGHT + (y - 2), 4, 4); // $codepro.audit.disable numericLiterals
        g.setColor(Color.BLACK);
    }
}
