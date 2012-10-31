// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/*
 * Comment
 */
package edu.gatech.spacetrader.screens;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.main.SpaceTrader;
import edu.gatech.spacetrader.planet.Galaxy;
import edu.gatech.spacetrader.planet.Planet;

/**
 * @author Cosmic Coders
 * @version 1.0
 */
public class FlyScreen extends Screen {

    /**
     * Field gameScreen.
     */
    private GameScreen gameScreen;

    /**
     * Field panel.
     */
    private GamePanel panel;

    /**
     * Field width.
     */
    /**
     * Field height.
     */
    private int width, height;

    /**
     * Field range.
     * Range the player can fly.
     */
    private Ellipse2D.Double range;

    /**
     * Field img.
     * Fog of war cloud.
     */
    private BufferedImage img;

    /**
     * Constructor for FlyScreen.
     * 
     * @param gameScreen
     *            GameScreen
     * @param panel
     *            GamePanel
     * @param width
     *            int
     * @param height
     *            int
     */
    public FlyScreen(GameScreen gameScreen, GamePanel panel, int width,
            int height) {
        this.gameScreen = gameScreen;
        this.panel = panel;
        this.width = width;
        this.height = height;

        int ovalX = (gameScreen.getCurrentPlanet().getX() * 5) + 10;
        int ovalY = (gameScreen.getCurrentPlanet().getY() * 5) + 10;
        int ovalRadius = ((gameScreen.getPlayer().getSpaceCraft().getSpeed() + gameScreen
                .getPlayer().getSkillsArray()[0] / 2) * (10 * 5)) / 2;
        
        // TODO: Limit range based on fuel when fuel is low.

        range = new Ellipse2D.Double(
                ovalX - ((gameScreen.getPlayer().getSpaceCraft().getSpeed() 
                        + gameScreen.getPlayer().getSkillsArray()[0] / 2) * (25)),
                ovalY - ((gameScreen.getPlayer().getSpaceCraft().getSpeed()
                        + gameScreen.getPlayer().getSkillsArray()[0] / 2) * (25)),
                2 * ovalRadius, 2 * ovalRadius);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = img.createGraphics();

        /* Draw the grey rectangle */
        g2D.setColor(new Color(180, 180, 180, 200));
        g2D.fillRect(0, 0, width, height);

        /* Enable Anti-Alias */
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        /* Clear the circle away */
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 1.0f));
        g2D.fillOval(ovalX - ovalRadius, ovalY - ovalRadius, 2 * ovalRadius,
                2 * ovalRadius);

        g2D.dispose();
    }

    /**
     * Method draw.
     * 
     * @param g
     *            Graphics
     */
    @Override
    public void draw(Graphics g) {
    	g.setColor(Color.black);
    	g.fillRect(0, 0, SpaceTrader.WIDTH, SpaceTrader.HEIGHT);
        gameScreen.getGalaxy().draw(g, panel, width, height);
        g.fillOval((Galaxy.GALAXY_WIDTH * 5) - 5, (Galaxy.GALAXY_HEIGHT * 5) - 5, 10, 10);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(range);
        g.drawImage(img, 0, 0, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
    }

    /**
     * Method checkForClick.
     * 
     * @param point
     *            Point
     */
    @Override
    public void checkForClick(Point point) {
        for (Planet p : gameScreen.getGalaxy().getPlanets()) {
            if (range.contains(point) && p.isClicked(point)) {
                gameScreen.changePlanet(p);
                panel.setActiveScreen(gameScreen);
            }
        }
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Fly screen";
    }
}
