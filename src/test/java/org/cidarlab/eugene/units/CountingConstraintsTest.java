package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

/**
 * The CountingConstraintsTest class is a set of 
 * unit-tests for testing (mini)Eugene Counting 
 * constraints, i.e. EXACTLY, MORETHAN, and CONTAINS
 *
 * Test Strategy:
 * --------------
 * We execute a Eugene script including Counting
 * constraints and compare the number of returned 
 * solutions with the number of expected solutions.
 * 
 * @author Ernst Oberortner
 *
 */
public class CountingConstraintsTest {

	// we define a part library of three parts
	// each having the same part type
	private static final String partLibrary = 
			"PartType PT; " +
			"PT p1; PT p2; PT p3;";
	
	// that's the name of the EugeneArray that
	// contains the results of the product() function
	private static final String result = "result";
	
	/*-----------------
	 * EXACTLY
	 *-----------------*/

	@Test
	public void testExactly1_1() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: p1 EXACTLY 1);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testExactly2_1() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
			.append("Rule R2(ON D2: p1 EXACTLY 1);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);
			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			// lastly, we compare the number of results
			// with the expected number
			assert(((EugeneArray)ec.get(result)).getElements().size() == 16);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testExactly2_p1_and_p2() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
			.append("Rule R2(ON D2: p1 EXACTLY 1 and p2 exactly 1);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);
			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			// lastly, we compare the number of results
			// with the expected number
			assert(((EugeneArray)ec.get(result)).getElements().size() == 8);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testExactly2_p1_and_p2_and_p3_no_solution() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
			.append("Rule R2(ON D2: p1 EXACTLY 1 and p2 exactly 1 and p3 exactly 1);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			e.executeScript(script.toString());
			
			assertTrue(false);
		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assert(ee.getMessage().contains("no solutions found!"));
		}
	}
	
	@Test
	public void testExactly2_2() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
				// p1 should appear EXACTLY twice
			.append("Rule R2(ON D2: p1 EXACTLY 2);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);
			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			// lastly, we compare the number of results
			// with the expected number
			assert(((EugeneArray)ec.get(result)).getElements().size() == 4);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testExactly3_1() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: p1 EXACTLY 1);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);
			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			// lastly, we compare the number of results
			// with the expected number
			assert(((EugeneArray)ec.get(result)).getElements().size() == 96);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testExactly3_2() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: p1 EXACTLY 2);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);
			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			// lastly, we compare the number of results
			// with the expected number
			assert(((EugeneArray)ec.get(result)).getElements().size() == 48);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testExactly3_3() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: p1 EXACTLY 3);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);
			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			// lastly, we compare the number of results
			// with the expected number
			assert(((EugeneArray)ec.get(result)).getElements().size() == 8);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testExactly3_p1_or_p2_or_p3() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: p1 EXACTLY 3 OR p2 EXACTLY 3 OR p3 EXACTLY 3);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);
			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			// lastly, we compare the number of results
			// with the expected number
			assert(((EugeneArray)ec.get(result)).getElements().size() == 24);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	/*-----------------
	 * MORETHAN
	 *-----------------*/

	@Test
	public void testMoreThan1_0() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: p1 MORETHAN 0);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testMoreThan2_0() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
				// p1 should appear MORETHAN 0 times
			.append("Rule R2(ON D2: p1 MORETHAN 0);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
			assert(((EugeneArray)ec.get(result)).getElements().size() == 20);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testMoreThan3_0() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
				// p1 should appear MORETHAN 0 times
			.append("Rule R3(ON D3: p1 MORETHAN 0);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 152);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	/*-----------------
	 * CONTAINS
	 *-----------------*/
	@Test
	public void testContains1_p1() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: CONTAINS p1);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testContains2_p1() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
				// p1 should appear MORETHAN 0 times
			.append("Rule R2(ON D2: CONTAINS p1);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
			assert(((EugeneArray)ec.get(result)).getElements().size() == 20);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testContains3_p1() {
		
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
				// p1 should appear MORETHAN 0 times
			.append("Rule R3(ON D3: CONTAINS p1);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 152);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
}
