package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
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

	@Test
	public void testQuery_Promoter_txt_Strength() {
		int NR_OF_PROMOTERS = 10;
		StringBuilder script = new StringBuilder();
		script.append("Property strength(txt);")
			.append("PartType Promoter(strength);");
		
		// 10 strong/medium/weak promoters
		for(int i=1; i<=NR_OF_PROMOTERS; i++) {
			script.append("Promoter pstrong").append(i).append("(.strength(\"strong\"));");
			script.append("Promoter pmedium").append(i).append("(.strength(\"medium\"));");
			script.append("Promoter pweak").append(i).append("(.strength(\"weak\"));");
		}
		
		script.append("strong_promoters = QUERY(Promoter.strength == \"strong\");");
		script.append("medium_promoters = QUERY(Promoter.strength == \"medium\");");
		script.append("weak_promoters = QUERY(Promoter.strength == \"weak\");");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			
			assert(ec.get("strong_promoters") != null);
			assert(ec.get("medium_promoters") != null);
			assert(ec.get("weak_promoters") != null);
			
			assert(ec.get("strong_promoters") instanceof EugeneCollection);
			assert(ec.get("medium_promoters") instanceof EugeneCollection);
			assert(ec.get("weak_promoters") instanceof EugeneCollection);

			assert(((EugeneCollection)ec.get("strong_promoters")).getElements() != null);
			assert(((EugeneCollection)ec.get("medium_promoters")).getElements() != null);
			assert(((EugeneCollection)ec.get("weak_promoters")).getElements() != null);

				// the query should return 1 part, namely p1
			assert(((EugeneCollection)ec.get("strong_promoters")).getElements().size() == NR_OF_PROMOTERS);
			assert(((EugeneCollection)ec.get("medium_promoters")).getElements().size() == NR_OF_PROMOTERS);
			assert(((EugeneCollection)ec.get("weak_promoters")).getElements().size() == NR_OF_PROMOTERS);

		} catch(EugeneException ee) {
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testQuery_Promoter_num_Strength() {
		int NR_OF_PROMOTERS = 300;
		StringBuilder script = new StringBuilder();
		script.append("Property strength(num);")
			.append("PartType Promoter(strength);");
		
		// 10 strong/medium/weak promoters
		for(int i=1; i<=NR_OF_PROMOTERS; i++) {
			script.append("Promoter p").append(i).append("(.strength(").append(i).append("));");
		}
		
		script.append("strong_promoters = QUERY(Promoter.strength > 200);");
		script.append("medium_promoters = QUERY(Promoter.strength > 100 AND Promoter.strength <= 200);");
		script.append("weak_promoters = QUERY(Promoter.strength <= 100);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			
			assert(ec.get("strong_promoters") != null);
			assert(ec.get("medium_promoters") != null);
			assert(ec.get("weak_promoters") != null);
			
			assert(ec.get("strong_promoters") instanceof EugeneCollection);
			assert(ec.get("medium_promoters") instanceof EugeneCollection);
			assert(ec.get("weak_promoters") instanceof EugeneCollection);

			assert(((EugeneCollection)ec.get("strong_promoters")).getElements() != null);
			assert(((EugeneCollection)ec.get("medium_promoters")).getElements() != null);
			assert(((EugeneCollection)ec.get("weak_promoters")).getElements() != null);

				// the query should return 1 part, namely p1
			assert(((EugeneCollection)ec.get("strong_promoters")).getElements().size() == NR_OF_PROMOTERS / 3);
			assert(((EugeneCollection)ec.get("medium_promoters")).getElements().size() == NR_OF_PROMOTERS / 3);
			assert(((EugeneCollection)ec.get("weak_promoters")).getElements().size() == NR_OF_PROMOTERS / 3);

		} catch(EugeneException ee) {
			
			ee.printStackTrace();
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testPermute() {

		String script = 
				"PartType Promoter(); " +
				"Promoter I14018; " +
				"Device proms(I14018, I14018); " +
				"Device fproms(+I14018, +I14018); " +
				"Device rproms(-I14018, -I14018); " +
				"Device hierarchical(proms, +I14018); ";
		
		// FIX ME:
		// permute(proms)
		// permute(fproms)
		// permute(rproms)
		// permute(hproms)

		try {
			EugeneCollection ec = new Eugene().executeScript(script);
			
			assert(null != ec);
			
			/*-------
			 * proms Device
			 *-------*/
			assert(null != ec.get("proms"));
			assert(ec.get("proms") instanceof Device);			
			Device proms = (Device)ec.get("proms");
			assert(null != proms.getOrientations());
			assert(proms.getOrientations().size() == 2);
			assert(null != proms.getOrientations(0));
			assert(proms.getOrientations(0).size() == 1);
			assert(proms.getOrientations(0).get(0) == Orientation.UNDEFINED);
			assert(null != proms.getOrientations(1));
			assert(proms.getOrientations(1).size() == 1);
			assert(proms.getOrientations(1).get(0) == Orientation.UNDEFINED);

			/*-------
			 * fproms Device
			 *-------*/
			assert(null != ec.get("fproms"));
			assert(ec.get("fproms") instanceof Device);			
			Device fproms = (Device)ec.get("fproms");
			assert(null != fproms.getOrientations());
			assert(fproms.getOrientations().size() == 2);
			assert(null != fproms.getOrientations(0));
			assert(fproms.getOrientations(0).size() == 1);
			assert(fproms.getOrientations(0).get(0) == Orientation.FORWARD);
			assert(null != fproms.getOrientations(1));
			assert(fproms.getOrientations(1).size() == 1);
			assert(fproms.getOrientations(1).get(0) == Orientation.FORWARD);

			/*-------
			 * rproms Device
			 *-------*/
			assert(null != ec.get("rproms"));
			assert(ec.get("rproms") instanceof Device);			
			Device rproms = (Device)ec.get("rproms");
			assert(null != rproms.getOrientations());
			assert(rproms.getOrientations().size() == 2);
			assert(null != rproms.getOrientations(0));
			assert(rproms.getOrientations(0).size() == 1);
			assert(rproms.getOrientations(0).get(0) == Orientation.REVERSE);
			assert(null != rproms.getOrientations(1));
			assert(rproms.getOrientations(1).size() == 1);
			assert(rproms.getOrientations(1).get(0) == Orientation.REVERSE);

			/*-------
			 * hierarchical Device
			 *-------*/
			assert(null != ec.get("hierarchical"));
			assert(ec.get("hierarchical") instanceof Device);			
			Device hierarchical = (Device)ec.get("hierarchical");
			assert(null != hierarchical.getOrientations());
			assert(hierarchical.getOrientations().size() == 2);
			assert(null != hierarchical.getOrientations(0));
			assert(hierarchical.getOrientations(0).size() == 1);
			assert(hierarchical.getOrientations(0).get(0) == Orientation.UNDEFINED);
			assert(null != hierarchical.getOrientations(1));
			assert(hierarchical.getOrientations(1).size() == 1);
			assert(hierarchical.getOrientations(1).get(0) == Orientation.FORWARD);
		} catch(EugeneException ee) {
			// no exception allowed here
			assertTrue(false);
		}
	}
}
