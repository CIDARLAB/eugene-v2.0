package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

/**
 * The SyntaxTest class unit-tests  
 * the syntax of the Eugene language.
 * 
 * These tests include:
 * - missing semi-colons
 * - case sensitivity
 * - duplicate elements
 * - undefined elements
 * - ...
 * 
 * @author Ernst Oberortner
 */
public class SyntaxTest {

	@Test
	public void testMissingSemicolon() {
		String script = "num i = 0";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("expecting SEMIC"));
		}
		
	}

}
