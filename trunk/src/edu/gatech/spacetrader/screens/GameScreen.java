// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals

/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.player.Player;


/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class GameScreen extends Screen {
    
    /**
     * Field player.
     */
    private final Player player;
    
    /**
     * Field panel.
     */
    private final GamePanel panel;
    
    /**
     * Field height.
     */
    /**
     * Field width.
     */
    private final int width, height;

    /**
     * Constructor for GameScreen.
     * @param player Player
     * @param panel GamePanel
     * @param width int
     * @param height int
     */
    public GameScreen(Player player, GamePanel panel, int width, int height) {
        this.player = player;
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    /**
     * Method draw.
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        final int x = width / 2;
        final int y = height / 2;
        g.drawString(player.getName(), 
                x - (((g.getFontMetrics()).stringWidth(player.getName())) / 2), y);
        g.drawString(player.getSkills(), 
                x - (((g.getFontMetrics()).stringWidth(player.getSkills())) / 2), 
                y + g.getFontMetrics().getHeight());
        g.drawString(player.getDifficulty(), 
                x - (((g.getFontMetrics()).stringWidth(player.getDifficulty())) / 2), 
                y + (g.getFontMetrics().getHeight() * 2));
    }

    /**
     * Method checkForClick.
     * @param point Point
     */
    @Override
    public void checkForClick(Point point) {
        System.out.println("Add a button with a panel: " + panel);
    }
    
    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString(){
        return "Game screen";
    }
}
