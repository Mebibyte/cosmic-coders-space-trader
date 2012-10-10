/* Comment
 * 
 */
package edu.gatech.spacetrader.planet;

import java.util.ArrayList;
import java.util.Random;

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
    
    private static enum Environment{
    	DESERT, WATER, JUNGLE, ICE, EARTHLIKE, TROPICAL, UNDERGROUND
    }
    
    private static enum CivilizationLevel{
    	STONE_AGE, AGRICULTURAL, IMPERIAL, EARLY_INDUSTRIAL, ADVANCED_INDUSTRIAL
    }
    
    /**
     * code pro is wrong here. this will change
     */
    private CivilizationLevel civLevel;
    
    /**
     * 
     */
    private final Environment environment;
    
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
        
        civLevel = CivilizationLevel.values()
        		[RAND.nextInt(CivilizationLevel.values().length)];
        environment = Environment.values()[RAND.nextInt(Environment.values().length)];
    }
    
    /**
     * Method getX.
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
    public CivilizationLevel getCivLevel(){
    	return this.civLevel;
    }
    
    /**
     * 
     */
    private void advanceCivilization(){
    	//Advance civilization to next level
    	//called internally when a random conditional is met during player movement.
        switch (civLevel) {
            case STONE_AGE:
                civLevel = CivilizationLevel.AGRICULTURAL;
                break;
            default:
                break;
        }
    }
    
    /**
     * Method toString.
     * @return String
     */
    public String toString(){
        return name;
    }
}
