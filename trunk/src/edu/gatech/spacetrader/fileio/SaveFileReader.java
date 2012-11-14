/*
 * Comment
 */
package edu.gatech.spacetrader.fileio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.gatech.spacetrader.good.Good;
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
	 * Method readFile.
	 * @param gameSave File
	 * @return created GameScreen
	 */
	@SuppressWarnings("resource")
    public static GameScreen readFile(File gameSave){
		try {
            final Scanner scan = new Scanner(gameSave);
			
			//method instance data
			final String playerName = scan.nextLine();
			final int[] skills = new int[ConfigScreen.NUMSKILLS];
			for (int i = 0; i < skills.length; i++) {
				skills[i] = Integer.parseInt(scan.nextLine());
			}
			final int credits = Integer.parseInt(scan.nextLine());

			final ConfigScreen.Difficulty difficulty = ConfigScreen.Difficulty.valueOf(
			        scan.nextLine());
			
			final Player player = new Player(playerName, skills, difficulty);
			player.setCredits(credits);
			
			SpaceCraft sc = null;
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
			final int fuel = Integer.parseInt(scan.nextLine());
			for (int i = 0; i < storageCap; i++) {
				int index = Integer.parseInt(scan.nextLine());
				int x = Integer.parseInt(scan.nextLine());
				int y = Integer.parseInt(scan.nextLine());
				int quant = Integer.parseInt(scan.nextLine());
				Good scGood = new Good(index, sc, x, y);
				for(int j = 0; j < quant; j++) {
					sc.addToStorage(scGood);
				}
			}
			
			sc.setFuel(fuel);
			player.setSpaceCraft(sc);

			final GameScreen gameScreen = new GameScreen(player, SpaceTrader.GAME_PANEL,
			        SpaceTrader.WIDTH, SpaceTrader.HEIGHT);
			
			final Galaxy galaxy = gameScreen.getGalaxy();
			for(int i = 0; i < galaxy.getPlanets().length; i++){
				galaxy.getPlanets()[i].setName(scan.nextLine());
				galaxy.getPlanets()[i].setX(Integer.parseInt(scan.nextLine()));
				galaxy.getPlanets()[i].setY(Integer.parseInt(scan.nextLine()));
				galaxy.getPlanets()[i].setEnvironment(scan.nextLine());
				galaxy.getPlanets()[i].setTechLevel(scan.nextLine());
			}
			final String cpName = scan.nextLine();
			for (Planet p : galaxy.getPlanets()){
				if(cpName.equals(p.toString())){
					gameScreen.setCurrentPlanet(p);
				}
			}
			sc.updatePrices(gameScreen.getCurrentPlanet().getMarket());
			return gameScreen;
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * Method toString required by CodePro.
     * @return String.
     */
    public String toString(){
        return "Save file reader";
    }
}
