package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

public class BuiltInFunctionsTest {

	@Test
	public void testSequenceOfPartType() {
		try {
			Eugene e = new Eugene();
			
			String script = 
					"PartType PT; " +
					"PT p1(.SEQUENCE(\"ATCG\")); " +
					"SEQUENCE_OF(PT);";
					// an exception should be thrown
					// part types do not have a sequence
			e.executeScript(script);
			
		} catch(EugeneException ee) {

				// see exception message in Interp.getSequenceOf 
			assert(ee.getMessage().contains("PT is neither a Part nor a Device."));
		}
	}

	@Test
	public void testSequenceOfPart() {
		try {
			Eugene e = new Eugene();
			
			String script = 
					"PartType PT; " +
					"PT p1(.SEQUENCE(\"ATCG\")); " +
					"txt seq = SEQUENCE_OF(p1);";
			
			EugeneCollection ec = e.executeScript(script);
			NamedElement seq = ec.get("seq");
			
			assert(null != seq);
			assert(seq instanceof Variable);
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testSequenceOfDevice() {
		try {
			Eugene e = new Eugene();
			
			String script = 
					"PartType PT; " +
					"PT p1(.SEQUENCE(\"ATCG\")); " +
					"PT p2(.SEQUENCE(\"ATCG\")); " +
					"Device D1(+p1, -p2); " +
					"txt seqD1 = SEQUENCE_OF(D1);"+
					"Device D2(-p1, +p2); " +
					"txt seqD2 = SEQUENCE_OF(D2);" +
					"Device D3(+p1, +p2); " +
					"txt seqD3 = SEQUENCE_OF(D3);" +
					"Device D4(-p1, -p2); " +
					"txt seqD4 = SEQUENCE_OF(D4);";


			EugeneCollection ec = e.executeScript(script);
			assert(null != ec);
			
			// seqD1
			assert(null != ec.get("seqD1"));
			assert(ec.get("seqD1") instanceof Variable);
			assert(EugeneConstants.TXT.equals(((Variable)ec.get("seqD1")).getType()));
			assert("ATCGCGAT".equals(((Variable)ec.get("seqD1")).getTxt()));
			
			// seqD2
			assert(null != ec.get("seqD2"));
			assert(ec.get("seqD2") instanceof Variable);
			assert(EugeneConstants.TXT.equals(((Variable)ec.get("seqD2")).getType()));
			assert("CGATATCG".equals(((Variable)ec.get("seqD2")).getTxt()));

			// seqD3
			assert(null != ec.get("seqD3"));
			assert(ec.get("seqD3") instanceof Variable);
			assert(EugeneConstants.TXT.equals(((Variable)ec.get("seqD3")).getType()));
			assert("ATCGATCG".equals(((Variable)ec.get("seqD3")).getTxt()));

			// seqD4
			assert(null != ec.get("seqD4"));
			assert(ec.get("seqD4") instanceof Variable);
			assert(EugeneConstants.TXT.equals(((Variable)ec.get("seqD4")).getType()));
			assert("CGATCGAT".equals(((Variable)ec.get("seqD4")).getTxt()));
		} catch(EugeneException ee) {
			
			// there shouldn't be an exception
			assertTrue(false);
		}
	}

	@Test
	public void testSequenceOfHierarchicalDevice() {
		try {
			Eugene e = new Eugene();
			
			String script = 
					"PartType PT; " +
					"PT p1(.SEQUENCE(\"ATCG\")); " +
					"PT p2(.SEQUENCE(\"ATCG\")); " +
					"Device D1(+p1, -p2); " +
					"txt seqD1 = SEQUENCE_OF(D1);"+
					"Device D2(-p1, +p2); " +
					"txt seqD2 = SEQUENCE_OF(D2);" +
					"Device D3(+p1, +p2); " +
					"txt seqD3 = SEQUENCE_OF(D3);" +
					"Device D4(-p1, -p2); " +
					"txt seqD4 = SEQUENCE_OF(D4);" +
					"Device D5(+D1, +D2, +D3, +D4); " +
					"txt seqD5 = sequence_of(D5);" +
					"Device D6(-D1, -D2, -D3, -D4); " +
					"txt seqD6 = sequence_of(D6);";

			EugeneCollection ec = e.executeScript(script);
			assert(null != ec);

			// seqD5
			assert(null != ec.get("seqD5"));
			assert(ec.get("seqD5") instanceof Variable);
			assert(EugeneConstants.TXT.equals(((Variable)ec.get("seqD5")).getType()));
			assert("ATCGCGATCGATATCGATCGATCGCGATCGAT".equals(
						((Variable)ec.get("seqD5")).getTxt()));

			// seqD6
			// D6 --> +p2, -p1, -p2, +p1, -p2, -p1, +p2, +p1
			assert(null != ec.get("seqD6"));
			assert(ec.get("seqD6") instanceof Variable);
			assert(EugeneConstants.TXT.equals(((Variable)ec.get("seqD6")).getType()));
			assert("ATCGCGATCGATATCGCGATCGATATCGATCG".equals(
						((Variable)ec.get("seqD6")).getTxt()));
		} catch(EugeneException ee) {
		}
	}
	
	
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
	public void testQuery_num_EQUALS_constant() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append(result).append(" = query(PT.np == 1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneCollection);

			assert(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assert(((EugeneCollection)ec.get(result)).getElements().size() == 1);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testQuery_num_EQUALS_Variable() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("num n = 1;")
			.append(result).append(" = query(PT.np == n);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneCollection);

			assert(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assert(((EugeneCollection)ec.get(result)).getElements().size() == 1);

		} catch(EugeneException ee) {
			
			ee.printStackTrace();
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testQuery_txt_EQUALS_constant() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append(result).append(" = query(PT.tp == \"one\");");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneCollection);

			assert(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assert(((EugeneCollection)ec.get(result)).getElements().size() == 1);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testQuery_txt_EQUALS_Variable() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("txt one = \"one\";")
			.append(result).append(" = query(PT.tp == one);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneCollection);

			assert(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assert(((EugeneCollection)ec.get(result)).getElements().size() == 1);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
}
