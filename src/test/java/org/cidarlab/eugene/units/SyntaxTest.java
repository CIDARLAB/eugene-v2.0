package org.cidarlab.eugene.units;

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

	@Test
	public void testCaseSensitivity_num_variable() {
		String script = "num i = 0; println(I);";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("I is not declared"));
		}
	}

	@Test
	public void testCaseSensitivity_UC_NUM_variable() {
		String script = "NUM i = 0;";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("expecting SEMIC"));
		}
	}

	@Test
	public void testCaseSensitivity_UC_DEVICE() {
		String script = "DEVICE D; ";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("I don't know anything about DEVICE!"));
		}
	}

	@Test
	public void testCaseSensitivity_device_product() {
		String script = "Device D; product(d);";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("d is not declared."));
		}
	}

	@Test
	public void testCaseSensitivity_device_UC_PRODUCT() {
		String script = "Device D; PRODUCT(D);";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("There are no components (e.g. parts) specified!"));
		}
	}


}
