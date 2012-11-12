/*
 * Comment
 */
package edu.gatech.spacetrader.fileio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

//<<<<<<< .mine
		
			//method instance data
			String playerName = scan.nextLine();
			int[] skills = new int[ConfigScreen.NUMSKILLS];
			for (int i = 0; i < skills.length; i++) {
				skills[i] = Integer.parseInt(scan.nextLine());
			}
			int credits = Integer.parseInt(scan.nextLine());
			
			//TODO finish player in FileWriter, pick up  from there
			ConfigScreen.Difficulty difficulty = ConfigScreen.Difficulty.valueOf(
			        scan.nextLine());
			
			player = new Player(playerName, skills, difficulty);
			player.setCredits(credits);
	
			SpaceCraft sc;
			String scName = scan.nextLine();
			switch (scName){ //TODO Fill in as we create more types
				case Gnat.GNAT_NAME:
					sc = new Gnat();
					break;
				default:
					sc = new Gnat();//had to be something
					break;
			}
			sc.setHealth(Integer.parseInt(scan.nextLine()));
			int storageCap = Integer.parseInt(scan.nextLine());
			int fuel = Integer.parseInt(scan.nextLine());
			for(int i = 0; i < storageCap; i++){
				int index = Integer.parseInt(scan.nextLine());
				int x = Integer.parseInt(scan.nextLine());
				int y = Integer.parseInt(scan.nextLine());
				int quant = Integer.parseInt(scan.nextLine());
				Good scGood = new Good(index, sc, x, y);
				//scGood.setQuantity(quant);
				for(int j = 0; j < quant; j++)
					sc.addToStorage(scGood);
			}
			sc.setFuel(fuel);
			player.setSpaceCraft(sc);

			gamePanel = SpaceTrader.GAME_PANEL;
			//System.out.println("Made panel");
			
			gameScreen = new GameScreen(player, gamePanel,
			        SpaceTrader.WIDTH, SpaceTrader.HEIGHT);
			
			//System.out.println("made gameScreen");
			Galaxy galaxy = gameScreen.getGalaxy();
			//System.out.println("Got galaxy");
			
			//Planet[] planets = new Planet[Galaxy.NUM_PLANETS];
			//System.out.println("about to start planets");
			//System.out.println(planets.length);
			for(int i = 0; i < galaxy.getPlanets().length; i++){
				//galaxy.getPlanets()[i] = new Planet(SpaceTrader.WIDTH, 
						//Galaxy.GALAXY_WIDTH, Galaxy.GALAXY_HEIGHT);
				galaxy.getPlanets()[i].setName(scan.nextLine());
				galaxy.getPlanets()[i].setX(Integer.parseInt(scan.nextLine()));
				galaxy.getPlanets()[i].setY(Integer.parseInt(scan.nextLine()));
				galaxy.getPlanets()[i].setEnvironment(scan.nextLine());
				galaxy.getPlanets()[i].setTechLevel(scan.nextLine());
				//System.out.println(i);
			}
			//System.out.println("End For");

			//current planet

			/*
			currentPlanet = new Planet(SpaceTrader.WIDTH,
			        Galaxy.GALAXY_WIDTH, Galaxy.GALAXY_HEIGHT);
			System.out.println("Made Current P");
			currentPlanet.setName(scan.nextLine());
			currentPlanet.setX(Integer.parseInt(scan.nextLine()));
			currentPlanet.setY(Integer.parseInt(scan.nextLine()));
			currentPlanet.setEnvironment(scan.nextLine());
			currentPlanet.setTechLevel(scan.nextLine());
			*/
			
			String cpName = scan.nextLine();
			//System.out.println(cpName);
			for (Planet p : galaxy.getPlanets()){
				//System.out.println("Iterating "+ p.toString()+" with " + p.getX());
				if(cpName.equals(p.toString())){
					//System.out.println("Found it");
					gameScreen.setCurrentPlanet(p);
				}
					
			}
		}
	
		catch(IOException e){
			//JOptionPane.showMessageDialog(new JPanel(), "Error reading file");
			e.printStackTrace();
		}
		catch(NumberFormatException e){
			//JOptionPane.showMessageDialog(new JPanel(), "Error reading file");
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
