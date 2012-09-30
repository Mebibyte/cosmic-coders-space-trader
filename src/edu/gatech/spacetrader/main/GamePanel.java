/* Comment
 * 
 */

package edu.gatech.spacetrader.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import edu.gatech.spacetrader.screens.Screen;
import edu.gatech.spacetrader.screens.TitleScreen;


/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	/**
	 * Field activeScreen.
	 */
	private Screen activeScreen;
	
	/**
	 * Field mouseOnScreen.
	 */
	private boolean mouseOnScreen;
	
	/**
	 * Constructor for GamePanel.
	 * @param width int
	 * @param height int
	 */
	public GamePanel(int width, int height){
		setFocusable(true);
		requestFocus();
		activeScreen = new TitleScreen(this, width, height);
		setBackground(Color.white);
		addMouseListener(new MouseListener());
		addKeyListener(new KeyListener());
	}
	
	/**
	 * Method paintComponent.
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (mouseOnScreen) {
		    PointerInfo pInfo = MouseInfo.getPointerInfo();
		    activeScreen.setHoverPoint(new Point(
		            pInfo.getLocation().x - this.getLocationOnScreen().x,
	                pInfo.getLocation().y - this.getLocationOnScreen().y));
		}
		activeScreen.draw(g);
	}
	
	/**
	 * Method update.
	 */
	public void update(){
		activeScreen.update();
	}
	
	/**
	 * Method setActiveScreen.
	 * @param activeScreen Screen
	 */
	public void setActiveScreen(Screen activeScreen){
		this.activeScreen = activeScreen;
	}
	
	/**
	 * Method isMouseOnScreen.
	
	 * @return boolean */
	public boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	/**
	 * Method setMouseOnScreen.
	 * @param mouseOnScreen boolean
	 */
	public void setMouseOnScreen(boolean mouseOnScreen) {
		this.mouseOnScreen = mouseOnScreen;
	}
	
	/**
	 * @author Glenn
	 */
	private class MouseListener extends MouseAdapter {
	    /**
	     * Method mousePressed.
	     * @param event MouseEvent
	    
	     * @see java.awt.event.MouseListener#mousePressed(MouseEvent) */
	    public void mousePressed(MouseEvent event){
            activeScreen.checkForClick(event.getPoint());
        }
        
        /**
         * Method mouseEntered.
         * @param event MouseEvent
        
         * @see java.awt.event.MouseListener#mouseEntered(MouseEvent) */
        public void mouseEntered(MouseEvent event){
            setMouseOnScreen(true);
        }
        
        /**
         * Method mouseExited.
         * @param e MouseEvent
        
         * @see java.awt.event.MouseListener#mouseExited(MouseEvent) */
        public void mouseExited(MouseEvent e){
            setMouseOnScreen(false);
        }
	}
	
	/**
	 * @author Glenn
	 */
	private class KeyListener extends KeyAdapter {
	    /**
	     * Method keyTyped.
	     * @param e KeyEvent
	    
	     * @see java.awt.event.KeyListener#keyTyped(KeyEvent) */
	    public void keyTyped(KeyEvent e) {
            activeScreen.keyTyped(e);
        }
	}
}
