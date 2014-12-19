package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

/**
 * The ExpressionConstraintsTest class is a set of 
 * unit-tests for testing Eugene (``Sparrow'') constraints 
 * specified on part properties, e.g.
 * Promoter.Strength > N 
 * Promoter.Strength + RBS.Strength < N 
 *
 * Test Strategy:
 * --------------
 * We execute a Eugene script and compare 
 * the number of returned solutions with 
 * the number of expected solutions.
 * 
 * @author Ernst Oberortner
 *
 */
public class ExpressionConstraintsTest {

	// we define a part library of three parts
	// each having the same part type
	private static final String partLibrary =
			"Property np(num); " +
			"Property tp(txt); " +
			"PartType PT(np, tp); " +
			"PT p1(.np(1), .tp(\"one\")); " +
			"PT p2(.np(2), .tp(\"two\")); " +
			"PT p3(.np(3), .tp(\"three\")); " +
			"PartType PT2(np, tp); " +
			"PT2 p21(.np(1), .tp(\"one\")); " +
			"PT2 p22(.np(2), .tp(\"two\")); " +
			"PT2 p23(.np(3), .tp(\"three\")); ";
	
	// that's the name of the EugeneArray that
	// contains the results of the product() function
	private static final String result = "result";

	@Test
	public void testNumProp_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: PT.np == 1);")
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
	public void testNumProp_NOT_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: PT.np != 1);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// 2 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2 * 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp_GREATER_THAN_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: PT.np >= 1);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// 3 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 3 * 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp_GREATER_THAN() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: PT.np > 1);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// 2 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2 * 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp_LESS_THAN_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: PT.np <= 1);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// 1 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 1 * 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp_LESS_THAN_2() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: PT.np < 2);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// 1 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 1 * 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp_LESS_THAN_1() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: PT.np < 1);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			e.executeScript(script.toString());
			
			assertTrue(false);

		} catch(EugeneException ee) {
			
				// exception should be thrown: NO PARTS SELECTED
			assert(ee.getMessage().contains("There are no components (e.g. parts) specified!"));
		}
	}

	@Test
	public void testNumProp_PLUS_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT2);")
			.append("Rule R2(ON D2: PT.np + PT2.np == 2);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// 1 selected PT part and 1 selected PT2 part
				// --> 2 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2 * 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp2_PLUS_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
			.append("Rule R2(ON D2: PT.np + PT.np == 2);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// the only selected part is p1 and
				// p1 appears twice			
				// --> 2 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2 * 2);

		} catch(EugeneException ee) {
			
			ee.printStackTrace();
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp3_PLUS_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT2, PT);")
			.append("Rule R2(ON D2: PT.np + PT2.np + PT.np == 3);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// the selected parts are p1 and p2
				// p1 appears twice, p2 appears once			
				// and 8 combinations of orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 1 * 8);

		} catch(EugeneException ee) {
			
			ee.printStackTrace();
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp2_MULT_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT2);")
			.append("Rule R2(ON D2: PT.np * PT2.np == 3);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// the selected parts are: p1, p3, p21, p23
				// and 8 combinations of orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 1 * 8);

		} catch(EugeneException ee) {
			
			ee.printStackTrace();
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNumProp_MINUS_EQUALS() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT2);")
			.append("Rule R2(ON D2: PT.np - PT2.np == 2);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);
				// 1 selected PT part (p3) and 1 selected PT2 part (p21)
				// --> 2 selected parts each can have 2 orientations
			assert(((EugeneArray)ec.get(result)).getElements().size() == 2 * 2);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
}
