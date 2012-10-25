package edu.gatech.spacetrader.fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import edu.gatech.spacetrader.screens.GameScreen;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.planet.*;

public class SaveFileWriter {
	
	private Player player;
	private Galaxy galaxy;
	private Planet currentPlanet;
	private FileWriter fw;
	private BufferedWriter buffer;
	private PrintWriter output;

	
	public SaveFileWriter(GameScreen screen){
		this.player = screen.getPlayer();
		this.galaxy = screen.getGalaxy();
		this.currentPlanet = screen.getCurrentPlanet();
	}
	
	public void generateSaveFile(String fileName) throws IOException{
		fw = new FileWriter(new File(fileName));
		buffer = new BufferedWriter(fw);
		output = new PrintWriter(buffer);
		
		//begin writing
		output.write(player.getName()+"\n");
		int[] skills = player.getSkillsArray();
		for (int i = 0; i < skills.length; i++)
			output.write(skills[i]+"\n");
		output.write(player.getCredits()+"\n");
		output.write(player.getSpaceCraft().toString()+"\n");
		
		
	
		
		
	}
	
}
