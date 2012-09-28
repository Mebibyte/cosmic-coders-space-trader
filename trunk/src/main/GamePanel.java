package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
		addMouseListener(new Listener());
		addKeyListener(new Listener());
		addMouseMotionListener(new Listener());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
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

	private class Listener implements MouseMotionListener, MouseListener, KeyListener{
		public void mousePressed(MouseEvent event){
			activeScreen.checkForClick(event.getPoint());
		}
		
		public void mouseEntered(MouseEvent event){
			setMouseOnScreen(true);
		}
		
		public void mouseExited(MouseEvent event){
			setMouseOnScreen(false);
		}
		
		public void keyPressed(KeyEvent e) {
			activeScreen.keyPress(e.getKeyCode());
		}
		
		public void keyReleased(KeyEvent e) {
			activeScreen.keyRelease(e.getKeyCode());
		}
		
		public void mouseMoved(MouseEvent event){}
		public void keyTyped(KeyEvent e) {}
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		public void mouseDragged(MouseEvent arg0) {}
	}
}
