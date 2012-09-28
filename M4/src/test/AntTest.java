package test;

import edu.cs2335.antlab.pkg3.AntLab31;
import edu.gatech.oad.antlab.person.*;
import edu.gatech.oad.antlab.pkg1.*;
import edu.gatech.oad.antlab.pkg2.*;
import junit.framework.TestCase;

public class AntTest extends TestCase{
	private AntLab11 ant11;
	private AntLab12 ant12;
	private AntLab21 ant21;
	private AntLab22 ant22;
	private AntLab31 ant31;
	
	public void testAnt11() {
        ant11 = new AntLab11();
		assertEquals("Congrats!", ant11.getMessage());
	}
	
	public void testAnt12() {
        ant12 = new AntLab12();
		assertEquals(" You", ant12.getMessage());
	}
	
	public void testAnt21() {
        ant21 = new AntLab21();
		assertEquals(" deserve", ant21.getMessage());
	}
	
	public void testAnt22() {
        ant22 = new AntLab22();
		assertEquals(" a", ant22.getMessage());
	}
	
	public void testAnt31() {
        ant31 = new AntLab31();
		assertEquals(" cookie.", ant31.getMessage());
	}
	
	public void testP1() {
		Person1 p1 = new Person1("Glenn Hollingsworth");
		assertEquals("Glenn Hollingsworth268596490", p1.toString("902685964"));
	}
	
	public void testP3() {
		Person3 p3 = new Person3("Kenisha Luby");
		assertEquals("Kenisha Luby913746209", p3.toString("902647319"));
	}
	
	public void testP4() {
		Person4 p4 = new Person4("Kirsten Roberts");
		assertEquals("Kirsten Roberts025290499", p4.toString("902529049"));
	}
	
	public void testP5() {
		Person5 p5 = new Person5("Patrick Conner");
		assertEquals("Patrick Conner812707902", p5.toString("902812707"));
	}
}
