/* Comment
 * 
 */

package main;

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

import screens.Screen;
import screens.TitleScreen;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private Screen activeScreen;
	
	private boolean mouseOnScreen;
	
	public GamePanel(int width, int height){
		setFocusable(true);
		requestFocus();
		activeScreen = new TitleScreen(this, width, height);
		setBackground(Color.white);
		addMouseListener(new MouseListener());
		addKeyListener(new KeyListener());
	}
	
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
	
	public void update(){
		activeScreen.update();
	}
	
	public void setActiveScreen(Screen activeScreen){
		this.activeScreen = activeScreen;
	}
	
	public boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	public void setMouseOnScreen(boolean mouseOnScreen) {
		this.mouseOnScreen = mouseOnScreen;
	}
	
	private class MouseListener extends MouseAdapter {
	    public void mousePressed(MouseEvent event){
            activeScreen.checkForClick(event.getPoint());
        }
        
        public void mouseEntered(MouseEvent event){
            setMouseOnScreen(true);
        }
        
        public void mouseExited(MouseEvent e){
            setMouseOnScreen(false);
        }
	}
	
	private class KeyListener extends KeyAdapter {
	    public void keyPressed(KeyEvent e) {
            activeScreen.keyPress(e.getKeyCode());
        }
        
        public void keyReleased(KeyEvent e) {
            activeScreen.keyRelease(e.getKeyCode());
        }
	}
}
