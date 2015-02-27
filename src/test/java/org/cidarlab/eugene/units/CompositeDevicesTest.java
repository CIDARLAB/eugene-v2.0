package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

/**
 * In these UNIT test, we test Eugene's features 
 * on enumerating hierarchically composed devices 
 * and the inclusion of rules into the enumeration 
 * process.
 *  
 * @author Ernst Oberortner
 */
public class CompositeDevicesTest {

	@Test
	public void testIsCompositeDevice() {
		
		// every Device object has a method called 
		// isComposite.
		// check the function's comment to see how 
		// a "composite" device is defined.
		// here, we test the isComposite() method
		String script = 
				"PartType PT;PT p1;PT p2;" +
				"Device subD1(PT);" +
				"Rule subD1_R(ON subD1: all_forward);" +
				"Device subD2(PT);" +
				"Rule subD2_R(ON subD2: all_reverse);" +
				"Device compositeD(subD1, subD2);";
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			
			// compositeD is composite
			assert(ec.get("compositeD") != null);
			assert(ec.get("compositeD") instanceof Device);
			assert(((Device)ec.get("compositeD")).isComposite() == true);
			
			// subD1 is NOT composite
			assert(ec.get("subD1") != null);
			assert(ec.get("subD1") instanceof Device);
			assert(((Device)ec.get("subD1")).isComposite() == false);
			
			// subD2 is NOT composite
			assert(ec.get("subD2") != null);
			assert(ec.get("subD2") instanceof Device);
			assert(((Device)ec.get("subD2")).isComposite() == false);
			
		} catch(EugeneException ee) {
			
			// no exception allowed!
			assertTrue(false);
		}
				
	}

	@Test
	public void testProductCompositeDevice() {
		
		// every Device object has a method called 
		// isComposite.
		// check the function's comment to see how 
		// a "composite" device is defined.
		// here, we test the isComposite() method
		String script = 
				"PartType PT;PT p1;PT p2; " +
				"Device subD1(PT); " +
				"Rule subD1_R(ON subD1: CONTAINS p1); " +
				"Device subD2(PT); " +
				"Rule subD2_R(ON subD2: CONTAINS p2); " +
				"Device compositeD(subD1, subD2); " +
				"Rule compositeD_R(ON compositeD: reverse subD1 AND forward subD2); " +
				"product(compositeD);";
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
//			assert(ec != null);
//			
//			// compositeD is composite
//			assert(ec.get("results") != null);
//			assert(ec.get("results") instanceof EugeneArray);
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
			// no exception allowed!
			assertTrue(false);
		}
				
	}
}
