package edu.gatech.spacetrader.screens;

import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.spacetrader.main.GamePanel;

public class FlyScreen extends Screen {
    
    private GameScreen gameScreen;
    
    private GamePanel panel;
    
    private int width, height;

    public FlyScreen(GameScreen gameScreen, GamePanel panel, int width,
            int height) {
        this.gameScreen = gameScreen;
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        gameScreen.getGalaxy().draw(g, panel, width, height);
    }

    @Override
    public void checkForClick(Point point) {
        // TODO Auto-generated method stub
        
    }

}
