/*
 * Comment
 */
package edu.gatech.spacetrader.fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import edu.gatech.spacetrader.screens.GameScreen;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.planet.*;

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
     * Field fw.
     */
    private FileWriter fw;

    /**
     * Field buffer.
     */
    private BufferedWriter buffer;

    /**
     * Field output.
     */
    private PrintWriter output;

    /**
     * Constructor for SaveFileWriter.
     * @param screen GameScreen
     */
    public SaveFileWriter(GameScreen screen) {
        this.player = screen.getPlayer();
        this.galaxy = screen.getGalaxy();
        this.currentPlanet = screen.getCurrentPlanet();
    }

    /**
     * Method generateSaveFile.
     * @param fileName String
     * @throws IOException
     */
    public void generateSaveFile(String fileName) throws IOException {
        fw = new FileWriter(new File(fileName));
        buffer = new BufferedWriter(fw);
        output = new PrintWriter(buffer);

        // begin writing
        output.write(player.getName() + "\n");
        int[] skills = player.getSkillsArray();
        for (int i = 0; i < skills.length; i++)
            output.write(skills[i] + "\n");
        output.write(player.getCredits() + "\n");
        // TODO Finish writing player attributes

        output.write(player.getSpaceCraft().toString() + "\n");
        output.write(player.getSpaceCraft().getHealth() + "\n");
        output.write(player.getSpaceCraft().getStorage().length);
        for (int i = 0; i < player.getSpaceCraft().getStorage().length; i++) {
            output.write(player.getSpaceCraft().getStorage()[i].getIndex()
                    + "\n");
            output.write(player.getSpaceCraft().getStorage()[i].getX() + "\n");
            output.write(player.getSpaceCraft().getStorage()[i].getY() + "\n");

        }

        // Write galaxy here
        Planet[] planets = galaxy.getPlanets();
        for (Planet p : planets) {
            output.write(p.toString() + "\n");
            output.write(p.getX() + "\n");
            output.write(p.getY() + "\n");
            output.write(p.getEnvironment().toString() + "\n");
            output.write(p.getTechLevel().toString() + "\n");
        }

        // do current planet specifics here

    }

}
