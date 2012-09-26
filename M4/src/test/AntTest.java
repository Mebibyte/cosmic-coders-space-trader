package test;

import junit.framework.TestCase;

public class AntTest extends TestCase{
	public void testOne() {
		boolean test = true;
		assertTrue("Testing", test);
	}
	
	public void testTwo() {
		boolean test = false;
		assertFalse("Testing", test);
	}
}
