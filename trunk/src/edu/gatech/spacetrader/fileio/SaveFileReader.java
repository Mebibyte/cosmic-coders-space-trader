package edu.gatech.spacetrader.fileio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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
		SpaceCraft sc;
		String scName = scan.nextLine();
		switch (scName){ //TODO Fill in as we create more types
			case Gnat.GNAT_NAME:
				sc = new Gnat();
				break;
			default:
				break;
		}
		
		
		
	}
	
	public GameScreen getLoadedGameScreen(){
		return loadedGameScreen;
	}
	
	
}
