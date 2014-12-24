package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.test.SparrowTestSuite;
import org.junit.Test;

/**
 * The IntegrationTest contains unit-tests 
 * for testing the integration of miniEugene and 
 * Sparrow into Eugene v2.0.
 * 
 * @author Ernst Oberortner
 */
public class IntegrationTest {

	@Test
	public void testSparrow() {
		
		// in this test we execute the
		// org.cidarlab.sparrow.test.SparrowTestSuite
		
		try {
			new SparrowTestSuite().testAll();
			
			// if there was no exception, then we passed the test.
			
		} catch(SparrowException spe) {
			// there should be no exception thrown
			assertTrue(false);
		}
	}
	
	@Test
	public void testMiniEugene_ToggleSwitchExport() {
		
		// in this test we include miniEugene's exported Eugene scripts
		// into Eugene scripts and augment them w/ part information
		
		String script =
				// PART LIBRARY SPECIFICATIONS
				"PartType Promoter; Promoter p1(.SEQUENCE(\"ATCG\")); Promoter p2(.SEQUENCE(\"ATCG\")); " +
				"PartType Repressor(); Repressor c1(.SEQUENCE(\"ATCG\")); Repressor c2(.SEQUENCE(\"ATCG\"));" +
				"PartType Reporter(); Reporter cReporter(.SEQUENCE(\"ATCG\"));" +
						
				// INCLUDING miniEugene EXPORT				
				"include \"./src/test/data/miniEugene/ToggleSwitch.eug\" " + 
				"txt seq = SEQUENCE_OF(d1);";
		try {
			
			Eugene e = new Eugene();
			EugeneCollection ec = e.executeScript(script);
			
			assert(null != ec);
			assert(!ec.getElements().isEmpty());
			
			// d1 device
			assert(null != ec.get("d1"));
			assert(ec.get("d1") instanceof Device);
			
			assert(null != ec.get("seq"));
			assert(ec.get("seq") instanceof Variable);
			
			// Device d1(-c1, -p2, p1, c2, cReporter);
			// --> SEQUENCE
			assert("CGATCGATATCGATCGATCG".equalsIgnoreCase(((Variable)ec.get("seq")).getTxt()));
		} catch(EugeneException ee) {
			
			ee.printStackTrace();
			// no exception allowed
			assertTrue(false);
		}
		
	}

}
