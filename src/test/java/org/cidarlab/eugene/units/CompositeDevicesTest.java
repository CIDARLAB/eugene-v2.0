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
				"Device sD1(PT);" +
				"Rule sD1_R(ON sD1: all_forward);" +
				"Device sD2(PT);" +
				"Rule sD2_R(ON sD2: all_reverse);" +
				"Device compositeD(sD1, sD2);";
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			
			// compositeD is composite
			assert(ec.get("compositeD") != null);
			assert(ec.get("compositeD") instanceof Device);
			assert(((Device)ec.get("compositeD")).isComposite() == true);
			
			// sD1 is NOT composite
			assert(ec.get("sD1") != null);
			assert(ec.get("sD1") instanceof Device);
			assert(((Device)ec.get("sD1")).isComposite() == false);
			
			// sD2 is NOT composite
			assert(ec.get("sD2") != null);
			assert(ec.get("sD2") instanceof Device);
			assert(((Device)ec.get("sD2")).isComposite() == false);
			
		} catch(EugeneException ee) {
			
			// no exception allowed!
			assertTrue(false);
		}
				
	}

	@Test
	public void testProductCompositeDevice01() {
		
		// every Device object has a method called 
		// isComposite.
		// check the function's comment to see how 
		// a "composite" device is defined.
		// here, we test the isComposite() method
		String script = 
				"PartType PT; PT p1; PT p2; " +
				"Device sD1(PT); " +
				"Rule sD1_R(ON sD1: CONTAINS p1); " +
				"Device sD2(PT); " +
				"Rule sD2_R(ON sD2: CONTAINS p2); " +
				"Device compositeD(sD1, sD2); " +
				"Rule compositeD_R(ON compositeD: REVERSE sD1 AND FORWARD sD2); " +
				"results = product(compositeD);";
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			
			// compositeD is composite
			assert(ec.get("results") != null);
			assert(ec.get("results") instanceof EugeneArray);
			
			/*
			 * Grammar:
			 * compositeD --> sD1 sD2
			 * sD1      --> PT
			 * sD2      --> PT
			 * PT         --> p1 | p2
			 * 
			 * 
			 * Solutions:
			 * 
			 * +p1 , +p2
			 * +p1 , -p2
			 * -p1 , +p2
			 * -p1 , -p2
			 */
			assert(((EugeneArray)ec.get("results")).getElements() != null);
			
//			System.out.println(((EugeneArray)ec.get("results")).getElements());
			
			assert(((EugeneArray)ec.get("results")).getElements().size() == 4);
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
			// no exception allowed!
			assertTrue(false);
		}
				
	}

	@Test
	public void testProductCompositeDevice02() {
		
		// every Device object has a method called 
		// isComposite.
		// check the function's comment to see how 
		// a "composite" device is defined.
		// here, we test the isComposite() method
		String script = 
				"PartType PT;PT p1;PT p2; " +
				"Device sd1(PT); " +
				"Rule sD1_R(ON sd1: CONTAINS p1 AND REVERSE p1); " +
				"Device sd2(PT); " +
				"Rule sD2_R(ON sd2: CONTAINS p2 AND FORWARD p2); " +
				"Device compositeD(sd1, sd2); " +
				"Rule compositeD_R(ON compositeD: reverse sd1 AND forward sd2); " +
				"results = product(compositeD);";
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			
			// compositeD is composite
			assert(ec.get("results") != null);
			assert(ec.get("results") instanceof EugeneArray);
			
			/*
			 * Grammar:
			 * compositeD --> sD1 sD2
			 * sD1      --> PT
			 * sD2      --> PT
			 * PT         --> p1 | p2
			 * 
			 * 
			 * Solutions:
			 * 
			 * -p1 , +p2
			 */
			assert(((EugeneArray)ec.get("results")).getElements() != null);
			assert(((EugeneArray)ec.get("results")).getElements().size() == 1);
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
			// no exception allowed!
			assertTrue(false);
		}
				
	}

}
