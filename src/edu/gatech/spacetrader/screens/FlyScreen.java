// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/*
 * Comment
 */
package edu.gatech.spacetrader.screens;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
    private final GameScreen gameScreen;

    /**
     * Field panel.
     */
    private final GamePanel panel;

    /**
     * Field width.
     */
    /**
     * Field height.
     */
    private final int width, height;

    /**
     * Field range. Range the player can fly.
     */
    private final Ellipse2D.Double range;

    /**
     * Field fog. Fog of war cloud.
     */
    private final BufferedImage fog;

    /**
     * Field ship. Spaceship.
     */
    private BufferedImage ship, resizedImage;
    
    /**
     * Field shipX, shipY, & degrees.
     */
    private int shipX, shipY, degrees;

    /**
     * Field rotation.
     */
    private double rotation = Math.toRadians(degrees);
    
    private Planet hoveredPlanet;

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

        final int ovalX = (gameScreen.getCurrentPlanet().getX() * 5) + 10;
        final int ovalY = (gameScreen.getCurrentPlanet().getY() * 5) + 10;
        int distance = ((gameScreen.getPlayer().getSpaceCraft().getSpeed() + gameScreen
                .getPlayer().getSkillsArray()[0] / 2) * (10 * 5)) / 2;

        int fuelRequired = ((gameScreen.getPlayer().getSpaceCraft().getSpeed()
                + gameScreen.getPlayer().getSkillsArray()[0] / 2) * 10)
                / gameScreen.getPlayer().getSpaceCraft().getSpeed();
        int reduceDistance = fuelRequired
                - gameScreen.getPlayer().getSpaceCraft().getFuel() > 0 ? fuelRequired
                - gameScreen.getPlayer().getSpaceCraft().getFuel()
                : 0;
        final int ovalRadius = distance - (reduceDistance * 5);

        range = new Ellipse2D.Double(ovalX - ovalRadius, ovalY - ovalRadius,
                2 * ovalRadius, 2 * ovalRadius);

        fog = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = fog.createGraphics();

        /* Draw the grey rectangle */
        g2D.setColor(new Color(180, 180, 180, 200));
        g2D.fillRect(0, 0, Galaxy.GALAXY_WIDTH * 5 + 35, height);

        /* Enable Anti-Alias */
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        /* Clear the circle away */
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 1.0f));
        g2D.fillOval(ovalX - ovalRadius, ovalY - ovalRadius, 2 * ovalRadius,
                2 * ovalRadius);

        g2D.dispose();
        try {
            ship = ImageIO.read(FlyScreen.class
                    .getResource("/edu/gatech/spacetrader/res/ship.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        resizedImage = new BufferedImage(20, 15, ship.getType());
        Graphics2D g2 = resizedImage.createGraphics();
        g2.drawImage(ship, 0, 0, 20, 15, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
        g2.dispose();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
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
        g.fillRect(0, 0, Galaxy.GALAXY_WIDTH * 5 + 45, SpaceTrader.HEIGHT);
        gameScreen.getGalaxy().draw(g, panel, width, height);
        g.fillOval((Galaxy.GALAXY_WIDTH * 5) - 5,
                (Galaxy.GALAXY_HEIGHT * 5) - 5, 10, 10);
        g.drawImage(fog, 0, 0, null); // $codepro.audit.disable
                                      // com.instantiations.assist.eclipse.analysis.unusedReturnValue

        // Rotation information
        double locationX = resizedImage.getWidth() / 2;
        double locationY = resizedImage.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotation,
                locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(resizedImage, null), shipX, shipY, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
        
        final int sidebarLocation = (Galaxy.GALAXY_WIDTH * 5 + 45);
        final int sidebarWidth = SpaceTrader.WIDTH - sidebarLocation;
        final int midSidebar = sidebarLocation + (sidebarWidth / 2);
        final FontMetrics fm = g.getFontMetrics();
        final int fh = fm.getHeight();
        g.drawString("Current Planet Information: ", midSidebar - (fm.stringWidth("Current Planet Information: ") / 2), fh);
        g.drawString("Name: " + gameScreen.getCurrentPlanet().toString(), sidebarLocation, fh * 2);
        g.drawString("Tech Level: " + gameScreen.getCurrentPlanet().getTechLevel().toString(), sidebarLocation, fh * 3);
        
        g.drawString("Selected Planet: ", midSidebar - (fm.stringWidth("Selected Planet: ") / 2), fh * 5);
        if (hoveredPlanet == null) {
            g.drawString("No Planet Selected", midSidebar - (fm.stringWidth("No Planet Selected") / 2), fh * 6);
            g.drawString("Hover over a planet to", midSidebar - (fm.stringWidth("Hover over a planet to") / 2), fh * 7);
            g.drawString("view its information!", midSidebar - (fm.stringWidth("view its information!") / 2), fh * 8);
        }
    }

    /**
     * Tick method to update position of ship.
     */
    @Override
    public void tick() {
        degrees++;
        degrees %= 360;
        rotation = Math.toRadians(degrees);
        shipX = ((gameScreen.getCurrentPlanet().getX() * 5) + 1)
                + ((int) (Math.cos(rotation) * 25)); // $codepro.audit.disable lossOfPrecisionInCast
        shipY = ((gameScreen.getCurrentPlanet().getY() * 5) + 3)
                + ((int) (Math.sin(rotation) * 25)); // $codepro.audit.disable lossOfPrecisionInCast
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
    
    @Override
    public void setHoverPoint(Point hoverPoint) {
        super.setHoverPoint(hoverPoint);
        boolean isPlanetHovered = false;
        if (range.contains(hoverPoint)) {
            for (Planet p : gameScreen.getGalaxy().getPlanets()) {
                if (p.isIn(hoverPoint)) {
                    hoveredPlanet = p;
                    isPlanetHovered = true;
                }
            }
            if (!isPlanetHovered) {
                hoveredPlanet = null;
            }
        } else {
            hoveredPlanet = null;
        }
    }
}
