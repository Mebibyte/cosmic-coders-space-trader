package tests;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
