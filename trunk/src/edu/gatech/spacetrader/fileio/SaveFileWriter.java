package edu.gatech.spacetrader.fileio;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import edu.gatech.spacetrader.screens.GameScreen;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.planet.*;

public class SaveFileWriter {
	
	private Player p;
	
	
	public SaveFileWriter(GameScreen screen){
		this.p = screen.getPlayer();
	}
	
}
