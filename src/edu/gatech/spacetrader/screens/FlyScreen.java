/*
 * Comment
 */
package edu.gatech.spacetrader.screens;

import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.spacetrader.main.GamePanel;

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

    /**
     * Constructor for FlyScreen.
     * @param gameScreen GameScreen
     * @param panel GamePanel
     * @param width int
     * @param height int
     */
    public FlyScreen(GameScreen gameScreen, GamePanel panel, int width,
            int height) {
        this.gameScreen = gameScreen;
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
        gameScreen.getGalaxy().draw(g, panel, width, height);
    }

    /**
     * Method checkForClick.
     * @param point Point
     */
    @Override
    public void checkForClick(Point point) {
        // TODO Auto-generated method stub
        System.out.println("Clicked");
    }

    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString() {
        return "Fly screen";
    }
}
