// $codepro.audit.disable multiplicationOrDivisionByPowersOf2, numericLiterals
/* Comment
 * 
 */

package edu.gatech.spacetrader.screens;

import edu.gatech.spacetrader.fileio.SaveFileReader;
import edu.gatech.spacetrader.gui.BigButton;
import edu.gatech.spacetrader.main.GamePanel;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

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
     * 
     */
    private final JFileChooser fc = new JFileChooser();
    
    /**
     * Field saveFileReader
     */
    private final SaveFileReader saveFileReader = new SaveFileReader();
    
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
     * Field SHIP_Y. Ships's y location.
     */
    private static final int SHIP_Y = 440;

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
                + newGame.getHeight(), false);
        quit = new BigButton("Exit Game", width / 2, (height / 2)
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
            PLAYER.paintIcon(panel, g, shipX - width, SHIP_Y);
        }

        PLAYER.paintIcon(panel, g, shipX, SHIP_Y);
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
            //System.out.println("Fix this later"); // FIXME
        	loadFile();
        	
        } else if (quit.isClicked(point)) {
            System.exit(0);
        }
    }

    /**
     * 
     * Loads and reads save file, initiates gamescreen
     * 
     */
    private void loadFile(){
    	/*
    }
<<<<<<< .mine
    	int choice = fc.showOpenDialog(new JPanel());
    	if (choice == JFileChooser.APPROVE_OPTION){
    		//try{
=======
*/
    	final int choice = fc.showOpenDialog(new JPanel());
    	if (choice == JFileChooser.APPROVE_OPTION) {
    		try {
//>>>>>>> .r178
    			saveFileReader.readFile(fc.getSelectedFile());
    			final GameScreen gc = saveFileReader.getLoadedGameScreen();
    			panel.setLocalGameScreen(gc);
    			panel.setActiveScreen(gc);
//<<<<<<< .mine
    		//}
    		//catch(Exception e){
    			//System.out.println("WTF???");
    		//}
//=======
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
//>>>>>>> .r178
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
    
     * @return String */
    @Override
    public String toString() {
        return "Title Screen";
    }
}
