// $codepro.audit.disable multiplicationOrDivisionByPowersOf2
/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import edu.gatech.spacetrader.gui.BigButton;
import edu.gatech.spacetrader.main.GamePanel;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class TitleScreen extends Screen {
    /**
     * Field newGame.
     */
    private final BigButton newGame, quit, loadGame;

    /**
     * Field height.
     */
    /**
     * Field width.
     */
    private final int width, height;

    /**
     * Field panel.
     */
    private final GamePanel panel;

    /**
     * Field bg.
     */
    private static final ImageIcon BG = new ImageIcon(
            TitleScreen.class
                    .getResource("/edu/gatech/spacetrader/res/space.jpg"));

    /**
     * Field player.
     */
    private static final ImageIcon PLAYER = new ImageIcon(
            TitleScreen.class
                    .getResource("/edu/gatech/spacetrader/res/player.png"));

    /**
     * Field shipX. Ships's x location.
     */
    private int shipX;

    /**
     * Constructor for TitleScreen.
     * 
     * @param panel
     *            GamePanel
     * @param width
     *            int
     * @param height
     *            int
     */
    public TitleScreen(GamePanel panel, int width, int height) {
        this.panel = panel;
        this.width = width;
        this.height = height;
        newGame = new BigButton("New Game", width / 2, height / 2);
        loadGame = new BigButton("Load Game", width / 2, (height / 2)
                + newGame.getHeight(), true);
        quit = new BigButton("Quit", width / 2, (height / 2)
                + newGame.getHeight() + loadGame.getHeight());
    }

    /**
     * Method draw.
     * 
     * @param g
     *            Graphics
     */
    @Override
    public void draw(Graphics g) {
        BG.paintIcon(panel, g, 0, 0);

        if (shipX + PLAYER.getIconWidth() > width) {
            PLAYER.paintIcon(panel, g, shipX - width, 440);
        }

        PLAYER.paintIcon(panel, g, shipX, 440);
        newGame.draw(g, panel, width, height);
        loadGame.draw(g, panel, width, height);
        quit.draw(g, panel, width, height);
    }

    /**
     * Method tick.
     */
    @Override
    public void tick() {
        shipX = (shipX + 4) % width;
    }

    /**
     * Method checkForClick.
     * 
     * @param point
     *            Point
     */
    @Override
    public void checkForClick(Point point) {
        if (newGame.isClicked(point)) {
            panel.setActiveScreen(new ConfigScreen(panel, width, height));
        } else if (loadGame.isClicked(point)) {
            System.out.println("Fix this later"); // FIXME
        } else if (quit.isClicked(point)) {
            System.exit(0);
        }
    }

    /**
     * Method setHoverPoint.
     * 
     * @param p
     *            Point
     */
    @Override
    public void setHoverPoint(Point p) {
        super.setHoverPoint(p);
        newGame.setHovered(p);
        loadGame.setHovered(p);
        quit.setHovered(p);
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Title Screen";
    }
}
