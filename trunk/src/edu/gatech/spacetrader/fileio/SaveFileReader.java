package edu.gatech.spacetrader.fileio;

import java.util.Scanner;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.planet.*;

public class SaveFileReader {
	private Player loadPlayer;
	private Galaxy loadGalaxy;
	
	public SaveFileReader(){
		Scanner scan = new Scanner("filepath.ext");//TODO this
	}
	
	
	public Player getPlayer(){
		return loadPlayer;
	}
	
	public Galaxy getGalaxy(){
		return loadGalaxy;
	}
	
	
}
