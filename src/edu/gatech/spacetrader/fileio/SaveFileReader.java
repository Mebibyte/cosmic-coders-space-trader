package edu.gatech.spacetrader.fileio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.gatech.spacetrader.good.Good;
import edu.gatech.spacetrader.main.SpaceTrader;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.planet.*;
import edu.gatech.spacetrader.screens.GameScreen;
import edu.gatech.spacetrader.spacecraft.*;

public class SaveFileReader {
	private GameScreen loadedGameScreen;
	private File gameSave;
	private Scanner scan;
	
	
	public void readFile(File gameSave){
		this.gameSave = gameSave;
		try{
			scan = new Scanner(this.gameSave);
		}
		catch (IOException e){
			
		}
		//method instance data
		String playerName = scan.nextLine();
		int[] skills = new int[4];
		for (int i = 0; i < skills.length; i++)
			skills[i] = Integer.parseInt(scan.nextLine());
		int credits = Integer.parseInt(scan.nextLine());
		//TODO finish player in FileWriter, pick up  from there
		
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
		for(int i = 0; i < storageCap; i++){
			int index = Integer.parseInt(scan.nextLine());
			int x = Integer.parseInt(scan.nextLine());
			int y = Integer.parseInt(scan.nextLine());
			Good scGood = new Good(index, sc, x, y);
			sc.canAddToStorage(scGood);
		}
		
		
		//galaxy
		Planet[] planets = new Planet[Galaxy.NUM_PLANETS];
		for(int i = 0; i < planets.length; i++){
			planets[i] = new Planet(SpaceTrader.WIDTH, 
					Galaxy.GALAXY_WIDTH, Galaxy.GALAXY_HEIGHT);
			planets[i].setName(scan.nextLine());
			planets[i].setX(Integer.parseInt(scan.nextLine()));
			planets[i].setY(Integer.parseInt(scan.nextLine()));
			planets[i].setCivLevel(scan.nextLine());
		}
		
		//current planet
		
	}
	
	public GameScreen getLoadedGameScreen(){
		return loadedGameScreen;
	}
	
	
}
