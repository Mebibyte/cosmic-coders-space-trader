/*
 * Comment
 */
package edu.gatech.spacetrader.fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.gatech.spacetrader.planet.Galaxy;
import edu.gatech.spacetrader.planet.Planet;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.screens.GameScreen;


/**
 * @author Patrick
 * @version 1.0
 */
public class SaveFileWriter {

    /**
     * Field player.
     */
    private Player player;

    /**
     * Field galaxy.
     */
    private Galaxy galaxy;

    /**
     * Field currentPlanet.
     */
    private Planet currentPlanet;

    /**
     * Field output.
     */
    private PrintWriter output;

    /**
     * Method generateSaveFile.
     * @param fileName String
     * @param screen GameScreen
     * @throws IOException
     */
    public void generateSaveFile(String fileName, GameScreen screen) {
    	this.player = screen.getPlayer();
		this.galaxy = screen.getGalaxy();
		this.currentPlanet = screen.getCurrentPlanet();
        try {
            output = new PrintWriter(new BufferedWriter(new FileWriter(
                    new File(fileName))));
            
            output.write(player.getName() + "\n");
            final int[] skills = player.getSkillsArray();
            for (int i = 0; i < skills.length; i++) {
                output.write(skills[i] + "\n");
            }
            output.write(player.getCredits() + "\n");
            output.write(player.getDifficulty().toUpperCase() + "\n");
            // TODO Finish writing player attributes

            output.write(player.getSpaceCraft().toString() + "\n");
            output.write(player.getSpaceCraft().getHealth() + "\n");
            output.write(player.getSpaceCraft().getStorage().length + "\n");
            output.write(player.getSpaceCraft().getFuel() + "\n");
            for (int i = 0; i < player.getSpaceCraft().getStorage().length; i++) {
                output.write(player.getSpaceCraft().getStorage()[i].getIndex()
                        + "\n");
                output.write(player.getSpaceCraft().getStorage()[i].getX() + "\n");
                output.write(player.getSpaceCraft().getStorage()[i].getY() + "\n");
                output.write(player.getSpaceCraft().getStorage()[i].getQuantity() + "\n");
            }
            //output.write(player.getDifficulty().toString() + "\n");

            // Write galaxy here
            final Planet[] planets = galaxy.getPlanets();
            for (Planet p : planets) {
                output.write(p.toString() + "\n");
                output.write(p.getX() + "\n");
                output.write(p.getY() + "\n");
                output.write(p.getEnvironment().toString().toUpperCase() + "\n");
                output.write(p.getTechLevel().toString().toUpperCase() + "\n");
            }

            // do current planet specifics here
            
            output.write(currentPlanet.toString() + "\n");
            output.write(currentPlanet.getX() + "\n");
            output.write(currentPlanet.getY() + "\n");
            output.write(currentPlanet.getEnvironment().toString().toUpperCase() + "\n");
            output.write(currentPlanet.getTechLevel().toString().toUpperCase() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            output.close();
        }
    }

    /**
     * Method toString required by CodePro.
     * @return String.
     */
    public String toString(){
        return "Save file writer";
    }
}
