package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Variable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VariableTest {

	@BeforeClass
	public static void setUpBeforeClass() 
			throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() 
			throws Exception {
	}

	@Before
	public void setUp() 
			throws Exception {
	}

	@After
	public void tearDown() 
			throws Exception {
	}

	@Test
	public void testInvalidVariables() {
		try {
			new Variable("",EugeneConstants.NUM);
		} catch(IllegalArgumentException iae) {
			assertTrue("Invalid name!".equals(iae.getLocalizedMessage()));
		}
		
		try {
			new Variable("var-name","invalid_type");
		} catch(IllegalArgumentException iae) {
			assertTrue("Invalid type of variable!".equals(iae.getLocalizedMessage()));
		}
	}
	
	@Test
	public void testNumVariables() {
		Variable numVar1 = new Variable("num_var1", EugeneConstants.NUM);
		assertTrue(EugeneConstants.NUM.equals(numVar1.getType()));
		assertTrue(numVar1.getNum() == 0);
	}
	
}
