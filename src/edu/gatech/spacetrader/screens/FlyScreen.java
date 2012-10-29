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
import edu.gatech.spacetrader.planet.Planet;

/**
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

    private Ellipse2D.Double range;

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

        range = new Ellipse2D.Double(
                ovalX - ((gameScreen.getPlayer().getSpaceCraft().getSpeed() 
                        + gameScreen.getPlayer().getSkillsArray()[0] / 2) * (5 * 5)),
                ovalY - ((gameScreen.getPlayer().getSpaceCraft().getSpeed()
                        + gameScreen.getPlayer().getSkillsArray()[0] / 2) * (5 * 5)),
                2 * ovalRadius, 2 * ovalRadius);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        /* Draw the grey rectangle */
        g.setColor(new Color(180, 180, 180, 200));
        g.fillRect(0, 0, width, height);

        /* Enable Anti-Alias */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        /* Clear the circle away */
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 1.0f));
        g.fillOval(ovalX - ovalRadius, ovalY - ovalRadius, 2 * ovalRadius,
                2 * ovalRadius);

        g.dispose();
    }

    /**
     * Method draw.
     * 
     * @param g
     *            Graphics
     */
    @Override
    public void draw(Graphics g) {
        gameScreen.getGalaxy().draw(g, panel, width, height);
        g.fillOval((150 * 5) - 5, (100 * 5) - 5, 10, 10);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(range);
        g.drawImage(img, 0, 0, null);
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
            if (range.contains(point) && p.checkForClick(point)) {
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
