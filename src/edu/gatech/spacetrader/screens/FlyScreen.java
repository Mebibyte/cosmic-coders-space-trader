// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals
/*
 * Comment
 */
package edu.gatech.spacetrader.screens;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
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
     * Field width, height.
     */
    private final int width = SpaceTrader.WIDTH, height = SpaceTrader.HEIGHT;

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
    private BufferedImage ship = null;
    
    /**
     * Field resizedImage. Smaller version of ship.
     */
    private final BufferedImage resizedImage;
    
    /**
     * Field shipX, shipY, & degrees.
     */
    private int shipX, shipY;
    
    /**
     * Field degrees, rotation.
     */
    private double degrees, rotation = Math.toRadians(degrees);
    
    /**
     * Field hoveredPlanet, chosenPlanet
     */
    private Planet hoveredPlanet, chosenPlanet;
   
    /**
     * Field scale.
     */
    private static final int SCALE = 5, SHIFT = 10;
    
    /**
     * Field flying.
     */
    private boolean flying;
    
    /**
     * Field chosenX, choseY, deltaX, deltaY
     * X and Y coordinates of chosen planet.
     * Change in x and y to get to chosen planet.
     */
    private int chosenX, chosenY, deltaX, deltaY;
    
    /**
     * Field slope, shipXFloat, shipYFloat, timeTaken.
     * Slope of line between planet and ship.
     * Float location of ship.
     * Time taken to get to planet.
     */
    private float slope, shipXFloat, shipYFloat, timeTaken;
    
    /**
     * Field lastTime
     * Last time updated.
     */
    private long lastTime;

    /**
     * Constructor for FlyScreen.
     * 
     * @param gameScreen
     * @param panel 
     */
    public FlyScreen(GameScreen gameScreen, GamePanel panel) {
        this.gameScreen = gameScreen;
        this.panel = panel;
        
        final int ovalX = (gameScreen.getCurrentPlanet().getX() * SCALE) + SHIFT;
        final int ovalY = (gameScreen.getCurrentPlanet().getY() * SCALE) + SHIFT;
        final int distance = ((gameScreen.getPlayer().getSpaceCraft().getSpeed()
                + gameScreen.getPlayer().getSkillsArray()[0] / 2)
                * (SHIFT * SCALE)) / 2;

        final int fuelRequired = ((gameScreen.getPlayer().getSpaceCraft().getSpeed()
                + gameScreen.getPlayer().getSkillsArray()[0] / 2) * SHIFT)
                / gameScreen.getPlayer().getSpaceCraft().getSpeed();
        final int reduceDistance = fuelRequired
                - gameScreen.getPlayer().getSpaceCraft().getFuel() > 0 ? fuelRequired
                - gameScreen.getPlayer().getSpaceCraft().getFuel()
                : 0;
        final int ovalRadius = distance - (reduceDistance * SCALE);
        final int ovalDiameter = 2 * ovalRadius;

        range = new Ellipse2D.Double(ovalX - ovalRadius, ovalY - ovalRadius,
                ovalDiameter, ovalDiameter);

        fog = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2D = fog.createGraphics();

        /* Draw the grey rectangle */
        final int grayRGB = 180;
        final int alpha = 225;
        g2D.setColor(new Color(grayRGB, grayRGB, grayRGB, alpha));
        g2D.fillRect(0, 0, Galaxy.GALAXY_WIDTH * SCALE + 35, height);

        /* Enable Anti-Alias */
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        /* Clear the circle away */
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 1.0f));
        g2D.fillOval(ovalX - ovalRadius, ovalY - ovalRadius, ovalDiameter,
                ovalDiameter);

        g2D.dispose();
        try {
            ship = ImageIO.read(FlyScreen.class
                        .getResource("/edu/gatech/spacetrader/res/ship.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        resizedImage = new BufferedImage(20, 15, ship.getType());
        final Graphics2D g2 = resizedImage.createGraphics();
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
     */
    @Override
    public void draw(Graphics g) {
    	g.setFont(new Font("serif", Font.PLAIN, 12));
        g.setColor(Color.black);
        g.fillRect(0, 0, Galaxy.GALAXY_WIDTH * SCALE + 45, SpaceTrader.HEIGHT);
        gameScreen.getGalaxy().draw(g, panel, width, height);
        g.fillOval((Galaxy.GALAXY_WIDTH * SCALE) - 5,
                (Galaxy.GALAXY_HEIGHT * SCALE) - 5, SHIFT, SHIFT);
        g.drawImage(fog, 0, 0, null); // $codepro.audit.disable
                                      // com.instantiations.assist.eclipse.analysis.unusedReturnValue

        // Rotation information
        final double locationX = resizedImage.getWidth() / 2;
        final double locationY = resizedImage.getHeight() / 2;
        final AffineTransform tx = AffineTransform.getRotateInstance(rotation,
                locationX, locationY);
        final AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(resizedImage, null), shipX, shipY, null); // $codepro.audit.disable com.instantiations.assist.eclipse.analysis.unusedReturnValue
        
        final int sidebarLocation = (Galaxy.GALAXY_WIDTH * SCALE + 45);
        final int sidebarWidth = SpaceTrader.WIDTH - sidebarLocation;
        final int midSidebar = sidebarLocation + (sidebarWidth / 2);
        final FontMetrics fm = g.getFontMetrics();
        final int fh = fm.getHeight();
        int y = fh;
        
        //right pane background color
        g.setColor(Color.DARK_GRAY);
        g.fillRect(791, 0, sidebarWidth + 15, height);
        
        g.setColor(Color.WHITE);
        g.drawString("Current Planet Information: ", midSidebar
                - (fm.stringWidth("Current Planet Information: ") / 2), y);
        gameScreen.getCurrentPlanet().drawInfo(g, sidebarLocation, y += fh);
        
        g.drawString("Selected Planet: ", midSidebar
                - (fm.stringWidth("Selected Planet: ") / 2), y += fh + fh + fh + fh);
        if (hoveredPlanet == null && chosenPlanet == null) {
            g.drawString("No Planet Selected", midSidebar
                    - (fm.stringWidth("No Planet Selected") / 2), y += fh);
            g.drawString("Hover over a planet to", midSidebar
                    - (fm.stringWidth("Hover over a planet to") / 2), y += fh);
            g.drawString("view its information!", midSidebar
                    - (fm.stringWidth("view its information!") / 2), y += fh);
        } else if (chosenPlanet == null) {
            hoveredPlanet.drawInfo(g, sidebarLocation, y += fh);
        } else {
            chosenPlanet.drawInfo(g, sidebarLocation, y += fh);
        }
    }

    /**
     * Tick method to update position of ship.
     */
    @Override
    public void tick() {
        if (!flying) {
            degrees++;
            degrees %= 360;
            rotation = Math.toRadians(degrees);
            shipX = ((gameScreen.getCurrentPlanet().getX() * SCALE) + 1)
                    + ((int) (Math.cos(rotation) * 25)); // $codepro.audit.disable lossOfPrecisionInCast
            shipY = ((gameScreen.getCurrentPlanet().getY() * SCALE) + 3)
                    + ((int) (Math.sin(rotation) * 25)); // $codepro.audit.disable lossOfPrecisionInCast
        } else {
            if (timeTaken < 1) {
                final double deltaT = (System.currentTimeMillis() - lastTime) * 0.001;
                lastTime = System.currentTimeMillis();
                shipXFloat += deltaX * deltaT;
                shipX = Math.round(shipXFloat);
                shipYFloat += slope * (deltaX * deltaT);
                shipY = Math.round(shipYFloat);
                timeTaken += deltaT;
            } else {
                gameScreen.changePlanet(chosenPlanet);
                panel.setActiveScreen(gameScreen);
            }
        }
    }

    /**
     * Method checkForClick.
     * 
     * @param point
     */
    @Override
    public void checkForClick(Point point) {
        for (Planet p : gameScreen.getGalaxy().getPlanets()) {
            if (range.contains(point) && p.isClicked(point)) {
                if (!flying) {
                    flying = true;
                    chosenX = p.getX() * SCALE;
                    chosenY = p.getY() * SCALE;
                    deltaX = chosenX - shipX;
                    deltaY = chosenY - shipY;
                    slope = deltaY / (float) deltaX;
                    shipYFloat = shipY;
                    shipXFloat = shipX;
                    chosenPlanet = p;
                    degrees = Math.atan2(deltaY, deltaX);
                    rotation = degrees - Math.toRadians(90);
                    lastTime = System.currentTimeMillis();
                }
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
    
    /**
     * Sets the hovered location.
     * @param hoverPoint point
     */
    @Override
    public void setHoverPoint(Point hoverPoint) {
        super.setHoverPoint(hoverPoint);
        boolean isPlanetHovered = false;
        if (range.contains(hoverPoint)) {
            for (Planet p : gameScreen.getGalaxy().getPlanets()) {
                if (p.isClicked(hoverPoint)) {
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
