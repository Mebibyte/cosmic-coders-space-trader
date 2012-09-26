package edu.gatech.oad.antlab.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AntTest {
	@Test(timeout = 200)
	public void testOne() {
		boolean test = true;
		assertTrue("Testing", test);
	}
}
