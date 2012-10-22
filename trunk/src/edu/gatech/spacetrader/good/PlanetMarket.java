/* Comment
 * 
 */
package edu.gatech.spacetrader.good;

import edu.gatech.spacetrader.planet.Planet;

/**
 * 
 * Generates prices of goods for a planet based on its conditions
 * @author Patrick Conner
 * @version 1.0
 */
public class PlanetMarket {
    private int[][] prices = new int[10][2];
	private static final int waterIndex = 0;
	private static final int fursIndex = 1;
	private static final int foodIndex = 2;
	private static final int oreIndex = 3;
	private static final int gamesIndex = 4;
	private static final int firearmsIndex = 5;
	private static final int medicineIndex = 6;
	private static final int machinesIndex = 7;
	private static final int narcoticsIndex = 8;
	private static final int robotsIndex = 9;
	
	private static final int sellIndex = 0;
	private static final int buyIndex = 1;
	
	// getters and setters. Lots of them
	
	
	public int getWaterSellPrice() {
		return prices[waterIndex][sellIndex];
	}

	public int getFursSellPrice() {
		return prices[fursIndex][sellIndex];
	}
	
	public int getFoodSellPrice() {
        return prices[foodIndex][sellIndex];
    }

	public int getOreSellPrice() {
		return prices[oreIndex][sellIndex];
	}
	
	public int getGamesSellPrice() {
        return prices[gamesIndex][sellIndex];
    }
	
	public int getFirearmsSellPrice() {
        return prices[firearmsIndex][sellIndex];
    }

	public int getMedicineSellPrice() {
		return prices[medicineIndex][sellIndex];
	}

	public int getMachinesSellPrice() {
		return prices[machinesIndex][sellIndex];
	}

	public int getNarcoticsSellPrice() {
		return prices[narcoticsIndex][sellIndex];
	}

	public int getRobotsSellPrice() {
	    return prices[robotsIndex][sellIndex];
	}

	public int getWaterBuyPrice() {
        return prices[waterIndex][buyIndex];
    }

    public int getFursBuyPrice() {
        return prices[fursIndex][buyIndex];
    }
    
    public int getFoodBuyPrice() {
        return prices[foodIndex][buyIndex];
    }

    public int getOreBuyPrice() {
        return prices[oreIndex][buyIndex];
    }
    
    public int getGamesBuyPrice() {
        return prices[gamesIndex][buyIndex];
    }
    
    public int getFirearmsBuyPrice() {
        return prices[firearmsIndex][buyIndex];
    }

    public int getMedicineBuyPrice() {
        return prices[medicineIndex][buyIndex];
    }

    public int getMachinesBuyPrice() {
        return prices[machinesIndex][buyIndex];
    }

    public int getNarcoticsBuyPrice() {
        return prices[narcoticsIndex][buyIndex];
    }

    public int getRobotsBuyPrice() {
        return prices[robotsIndex][buyIndex];
    }

	
	
	public void createPlanetMarket(Planet p){
		//TODO Logic here that generates market prices
		//based on environment, current event, and civilization level
		
	}

}
