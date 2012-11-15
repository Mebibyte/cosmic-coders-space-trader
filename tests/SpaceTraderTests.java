package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.gatech.spacetrader.main.SpaceTrader;
import edu.gatech.spacetrader.planet.Galaxy;
import edu.gatech.spacetrader.planet.Planet;
import edu.gatech.spacetrader.player.Player;
import edu.gatech.spacetrader.screens.ConfigScreen;
import edu.gatech.spacetrader.spacecraft.SpaceCraft;
import edu.gatech.spacetrader.spacecraft.Gnat;
import edu.gatech.spacetrader.good.Good;

public class SpaceTraderTests {

    @Test
    public void AddingToCraftTest() { // By Glenn
        SpaceCraft craft = new Gnat();
        assertTrue("Can add to empty storage?", craft.canAddToStorage());
        for (int i = 0; i < 10; i++) {
            craft.addToStorage(new Good(0, craft, 0, 0));
        }
        assertFalse("Can't add to full storage?", craft.canAddToStorage());
    }
    
    @Test  
    public void FillingFuelTest(){//Kirsten Roberts
    	SpaceCraft craft = new Gnat();
    	assertFalse("Cannot add fuel when full?", craft.canFillFuel());
    	craft.setFuel(0);
    	assertTrue("Fuel is empty, can add fuel?", craft.canFillFuel());
    }

    @Test
    public void CanFlyTest(){// by Kenisha Luby
    	SpaceCraft craft = new Gnat();
    	assertTrue("Can fly away?", craft.canFly());
    	craft.setFuel(0);
    	assertFalse("Can't fly away?", craft.canFly());
    } 
    
    @Test 
    public void CanSpendMoneyTest() { //Piero  
    	Player player= new Player("poop", new int[]{10,6,0,0}, ConfigScreen.Difficulty.EASY); 
    	assertTrue("Can spend money if you have it?", player.canSpend(1));
    	player.setCredits(0); 
    	assertFalse("Cannot spend money?", player.canSpend(1)); 
    }
    
    @Test
    public void TestMarketTechLevel(){ //Patrick Conner
    	Planet planet = new Planet(SpaceTrader.WIDTH, Galaxy.GALAXY_WIDTH, Galaxy.GALAXY_HEIGHT);
    	planet.setTechLevel("MEDIEVAL");
    	planet.getMarket().updatePrices();
    	assertEquals("Sell price of good is 0 if tech level isn't high enough.",
                0, planet.getMarket().getGood(9).getSellPrice());
    }
}