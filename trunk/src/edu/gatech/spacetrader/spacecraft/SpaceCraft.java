/* Comment
 * 
 */

package edu.gatech.spacetrader.spacecraft;

import java.util.Random;
import edu.gatech.spacetrader.good.Good; 

import javax.swing.ImageIcon;

/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public abstract class SpaceCraft {
    
	/**
	 * 
	 */
	private static final Random RAND = new Random();

	/**
	 * 
	 */
	protected int health;
	
	/** 
	 * 
	 */
	protected Good[] storage; 
	
	/**
	 * 
	 */
	protected int speed;
	
	/**
	 * 
	 */
	protected int defense;
	
	/**
	 * 
	 */
	protected int attack; 
	
	/**
	 * 
	 */
	protected int maxCapacity;  
	
	/**
	 * 
	 */
	protected int quantity; 
	
	/**
	 * We can eventually create pictures of each of the ships for when
	 * the player upgrades to a new ship
	 */
	protected ImageIcon shipIcon;

    /**
     * @param repairSkill
     */
    public abstract void repair(int repairSkill);
    
    /**
     * Method fly.
     * 
     */
    public void fly(){
        System.out.println("2 Infinity N Beyond! nice touch glenn"); //TODO
    }
    
    /**
	 * @param enemyAttack Attack strength of enemy	
	 * @return damage done to the gnat */
	public int takeDamge(int enemyAttack) {
		// TODO Auto-generated method stub
		final int damage; 
		
		damage = Math.max(RAND.nextInt(enemyAttack) - RAND.nextInt(defense), 0);  
		health -= damage;
		return damage;
	}

    /**
     * @return int Attack value.
     */
    public int getAttack(){
		return this.attack;
	}
    
    /**
     * @return int Defense Value.
     */
    public int getDefense(){
    	return this.defense;
    } 
    
    /**
     * Adds a certain Good to the SpaceCraft's Storage
     * @param g 
     * @return Boolean of whether or not you could add the good to the storage 
     */
    public boolean addToStorage(Good g){ 
    	boolean ans; 
    	int i= g.getIndex();  
    	
    	if ( quantity >= maxCapacity ){
    		ans= false; 
    	} else if (storage[i] == null) {
    		storage[i]= g;  
    		ans= true; 
    		quantity++; 
    	}else {
    		storage[i].addGood();  
    		quantity++;  
    		ans= true; 
    	}
    	return ans; 
    	
    }  

    
    public Good removeGood(Good g){  
    	int i= 0; 
    	boolean a; 
    	
    	return null; 
    }
}
