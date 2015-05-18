package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.junit.Test;

public class ContainerTest {

	@Test
	public void test_ArrayDefinition_MultipleSameElements() {
		
			// here, we add the var1 variable twice to 
			// the testArray array
		String script = "num var1=1; num var2=2;" +
		"Array testArray(var1, var2, var1);";
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			NamedElement ne = ec.get("testArray");
			assertTrue(null != ne);
			assertTrue(ne instanceof EugeneArray);
			
			EugeneArray testArray = (EugeneArray)ne;
			assertTrue(testArray.size() == 3);
			
			assertTrue(testArray.getElement(0) instanceof Variable);
			assertTrue(((Variable)testArray.getElement(0)).getType() == EugeneConstants.NUM);
			assertTrue("var1".equals(((Variable)testArray.getElement(0)).getName()));
			assertTrue(((Variable)testArray.getElement(0)).getNum() == 1);
			
			assertTrue(testArray.getElement(1) instanceof Variable);
			assertTrue(((Variable)testArray.getElement(1)).getType() == EugeneConstants.NUM);
			assertTrue("var2".equals(((Variable)testArray.getElement(1)).getName()));
			assertTrue(((Variable)testArray.getElement(1)).getNum() == 2);
			
			assertTrue(testArray.getElement(2) instanceof Variable);
			assertTrue(((Variable)testArray.getElement(2)).getType() == EugeneConstants.NUM);
			assertTrue("var1".equals(((Variable)testArray.getElement(2)).getName()));
			assertTrue(((Variable)testArray.getElement(2)).getNum() == 1);
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);	// no exception allowed
		}
	}

	@Test
	public void test_IndexBased_AssignmentToArrayElements() {
		
			// here, we add the var1 variable twice to 
			// the testArray array
		String script = 
				"num var1=1; num var2=2;" +
				"Array testArray(var1, var2, var1);" +
				"testArray[0] = 0;" +
				"testArray[1] = 1;" +
				"testArray[2] = 2;";
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			NamedElement ne = ec.get("testArray");
			assertTrue(null != ne);
			assertTrue(ne instanceof EugeneArray);
			
			EugeneArray testArray = (EugeneArray)ne;
			assertTrue(testArray.size() == 3);
			
			assertTrue(testArray.getElement(0) instanceof Variable);
			assertTrue(((Variable)testArray.getElement(0)).getType() == EugeneConstants.NUM);
			assertTrue("var1".equals(((Variable)testArray.getElement(0)).getName()));
			assertTrue(((Variable)testArray.getElement(0)).getNum() == 0);
			
			assertTrue(testArray.getElement(1) instanceof Variable);
			assertTrue(((Variable)testArray.getElement(1)).getType() == EugeneConstants.NUM);
			assertTrue("var2".equals(((Variable)testArray.getElement(1)).getName()));
			assertTrue(((Variable)testArray.getElement(1)).getNum() == 1);
			
			assertTrue(testArray.getElement(2) instanceof Variable);
			assertTrue(((Variable)testArray.getElement(2)).getType() == EugeneConstants.NUM);
			assertTrue("var1".equals(((Variable)testArray.getElement(2)).getName()));
			assertTrue(((Variable)testArray.getElement(2)).getNum() == 2);
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);	// no exception allowed
		}
	}
}
