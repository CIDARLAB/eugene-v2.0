package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.junit.Test;

public class LibrarySpecificationTest {

	@Test
	public void testPartWithoutDotNotation() {
		
		String script = 
				"Property prop1(txt);" +
				"Property prop2(num);" +
				"PartType PT2(prop1, prop2);" +
				"PT2 pt2_2(\"T\", 3.1);";
		
		try {
			EugeneCollection results = 
					new Eugene().executeScript(script);
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
						
	}
	
	@Test
	public void testEugeneLabExample() {
		String script = 
			"Property prop1(txt);" +
			"Property prop2(num);" +
			"Property prop3(txt[]);" +
			"Property prop4(num[]);" +
			"Property prop5(boolean);" +
			"PartType PT0();" +
			"PartType PT1(prop1);" +
			"PartType PT2(prop1, prop2);" +
			"PartType PT3(prop1, prop2, prop3);" +
			"PartType PT4(prop1, prop2, prop3, prop4);" +
			"PartType PT5(prop1, prop2, prop3, prop4, prop5);" +
			"// parts with DOT notation" +
			"PT0 pt0_1(.PIGEON(\"c\"), .SEQUENCE(\"ATGTAA\"));" +
			"PT1 pt1_1(.prop1(\"A\"));" +
			"PT2 pt2_1(.prop1(\"T\"), .prop2(3.1));" +
			"PT3 pt3_1(.prop1(\"C\"), .prop2(3.14), .prop3([\"C\"]));" +
			"PT4 pt4_1(.prop1(\"G\"), .prop2(3.141), .prop3([\"A\", \"T\", \"C\", \"G\"]), .prop4([0, 1.1, 2.2, 3.3, 4.4, 5.5]));" +
			"PT5 pt5_1(.prop1(\"ATCG\"), .prop2(3.1415962), .prop3([\"A\", \"T\", \"C\", \"G\"]), .prop4([1.0, 2.0, 3.0, 4, 5]), .prop5(true));" +
			"// part w/o DOT notation" +
			"PT0 pt0_2(\"c\", \"ATGTAA\");" +
			"PT1 pt1_2(\"A\");" +
			"PT2 pt2_2(\"T\", 3.1);" +
			"PT3 pt3_2(\"C\", 3.14, [\"C\"]);" +
			"PT4 pt4_2(\"G\", 3.141, [\"A\", \"T\", \"C\", \"G\"], [0, 1.1, 2.2, 3.3, 4.4, 5.5]);" +
			"PT5 pt5_2(\"ATCG\", 3.1415962, [\"A\", \"T\", \"C\", \"G\"], [0, 1.1, 2.2, 3.3, 4.4, 5.5], true);";
		
		try {
			new Eugene().executeScript(script);
		} catch(Exception e) {
			assertTrue(false);
		}
	}

}
