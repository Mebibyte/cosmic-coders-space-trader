/*
 * Comment
 */
package edu.gatech.spacetrader.fileio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.gatech.spacetrader.good.Good;
import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.main.SpaceTrader;
import edu.gatech.spacetrader.planet.Galaxy;
import edu.gatech.spacetrader.planet.Planet;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.screens.ConfigScreen;
import edu.gatech.spacetrader.screens.GameScreen;
import edu.gatech.spacetrader.spacecraft.Gnat;
import edu.gatech.spacetrader.spacecraft.SpaceCraft;

/**
 * @author Patrick
 * @version 1.0
 */
public class SaveFileReader {
	/**
	 * Field loadedGameScreen.
	 */
	private GameScreen gameScreen;

	/**
	 * 
	 */
	private Player player;
	
	/**
     * 
     */
	private GamePanel gamePanel;
	
	/**
     * 
     */
	private Galaxy galaxy;
	
	/**
	 * Field gameSave.
	 */
	private File gameSave;

	/**
	 * Field scan.
	 */
	private Scanner scan;

	/**
	 * Method readFile.
	 * @param gameSave File
	 */
	public void readFile(File gameSave){
		this.gameSave = gameSave;
		try {
			scan = new Scanner(this.gameSave);

    		//method instance data
    		final String playerName = scan.nextLine();
    		final int[] skills = new int[ConfigScreen.NUMSKILLS];
    		for (int i = 0; i < skills.length; i++) {
    			skills[i] = Integer.parseInt(scan.nextLine());
    		}
    		final int credits = Integer.parseInt(scan.nextLine());
    		
    		//TODO finish player in FileWriter, pick up  from there
    		final ConfigScreen.Difficulty difficulty = ConfigScreen.Difficulty.valueOf(
    		        scan.nextLine());
    		
    		player = new Player(playerName, skills, difficulty);
    		player.setCredits(credits);
    
    		SpaceCraft sc;
    		final String scName = scan.nextLine();
    		switch (scName) { //TODO Fill in as we create more types
    			case Gnat.GNAT_NAME:
    				sc = new Gnat();
    				break;
    			default:
    				sc = new Gnat();//had to be something
    				break;
    		}
    		sc.setHealth(Integer.parseInt(scan.nextLine()));
    		final int storageCap = Integer.parseInt(scan.nextLine());
    		for(int i = 0; i < storageCap; i++){
    			int index = Integer.parseInt(scan.nextLine());
    			int x = Integer.parseInt(scan.nextLine());
    			int y = Integer.parseInt(scan.nextLine());
    			Good scGood = new Good(index, sc, x, y);
    			sc.addToStorage(scGood);
    		}
    		
    		player.setSpaceCraft(sc);
    
    		//galaxy
    		final Planet[] planets = new Planet[Galaxy.NUM_PLANETS];
    		for(int i = 0; i < planets.length; i++){
    			planets[i] = new Planet(SpaceTrader.WIDTH, 
    					Galaxy.GALAXY_WIDTH, Galaxy.GALAXY_HEIGHT);
    			planets[i].setName(scan.nextLine());
    			planets[i].setX(Integer.parseInt(scan.nextLine()));
    			planets[i].setY(Integer.parseInt(scan.nextLine()));
    			planets[i].setEnvironment(scan.nextLine());
    			planets[i].setTechLevel(scan.nextLine());
    		}
    		
    		galaxy = new Galaxy(SpaceTrader.HEIGHT, SpaceTrader.WIDTH);
    		galaxy.setPlanets(planets);
    		
    		//current planet
    		final Planet currentPlanet = new Planet(SpaceTrader.WIDTH,
    		        Galaxy.GALAXY_WIDTH, Galaxy.GALAXY_HEIGHT);
    		currentPlanet.setName(scan.nextLine());
    		currentPlanet.setX(Integer.parseInt(scan.nextLine()));
    		currentPlanet.setY(Integer.parseInt(scan.nextLine()));
    		currentPlanet.setEnvironment(scan.nextLine());
    		currentPlanet.setTechLevel(scan.nextLine());
    		
    		gameScreen.setCurrentPlanet(currentPlanet);
    		
    		//As of now, I'm not having the prices of the market stay as persistant quantities.
    		//Time will advance as if the player has moved when a game is loaded.
    
    		gamePanel = new GamePanel(SpaceTrader.WIDTH, SpaceTrader.HEIGHT);
    		
    		gameScreen = new GameScreen(player, gamePanel,
    		        SpaceTrader.WIDTH, SpaceTrader.HEIGHT);
    		
    		gameScreen.setGalaxy(galaxy);
		} catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Method getLoadedGameScreen.
	 * @return GameScreen
	 */
	public GameScreen getLoadedGameScreen(){
		return gameScreen;
	}
	
	/**
     * Method toString required by CodePro.
     * @return String.
     */
    public String toString(){
        return "Save file reader";
    }
}
