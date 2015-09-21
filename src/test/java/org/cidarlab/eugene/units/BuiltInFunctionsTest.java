/* Copyright (c) 2015, Boston University
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list 
 * of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without specific prior 
 * written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.units;

import static org.junit.Assert.assertTrue;

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
			assertTrue(ee.getMessage().contains("PT is neither a Part nor a Device."));
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
			
			assertTrue(null != seq);
			assertTrue(seq instanceof Variable);
			
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
			assertTrue(null != ec);
			
			// seqD1
			assertTrue(null != ec.get("seqD1"));
			assertTrue(ec.get("seqD1") instanceof Variable);
			assertTrue(EugeneConstants.TXT.equals(((Variable)ec.get("seqD1")).getType()));
			assertTrue("ATCGCGAT".equals(((Variable)ec.get("seqD1")).getTxt()));
			
			// seqD2
			assertTrue(null != ec.get("seqD2"));
			assertTrue(ec.get("seqD2") instanceof Variable);
			assertTrue(EugeneConstants.TXT.equals(((Variable)ec.get("seqD2")).getType()));
			assertTrue("CGATATCG".equals(((Variable)ec.get("seqD2")).getTxt()));

			// seqD3
			assertTrue(null != ec.get("seqD3"));
			assertTrue(ec.get("seqD3") instanceof Variable);
			assertTrue(EugeneConstants.TXT.equals(((Variable)ec.get("seqD3")).getType()));
			assertTrue("ATCGATCG".equals(((Variable)ec.get("seqD3")).getTxt()));

			// seqD4
			assertTrue(null != ec.get("seqD4"));
			assertTrue(ec.get("seqD4") instanceof Variable);
			assertTrue(EugeneConstants.TXT.equals(((Variable)ec.get("seqD4")).getType()));
			assertTrue("CGATCGAT".equals(((Variable)ec.get("seqD4")).getTxt()));
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
			assertTrue(null != ec);

			// seqD5
			assertTrue(null != ec.get("seqD5"));
			assertTrue(ec.get("seqD5") instanceof Variable);
			assertTrue(EugeneConstants.TXT.equals(((Variable)ec.get("seqD5")).getType()));
			assertTrue("ATCGCGATCGATATCGATCGATCGCGATCGAT".equals(
						((Variable)ec.get("seqD5")).getTxt()));

			// seqD6
			// D6 --> +p2, -p1, -p2, +p1, -p2, -p1, +p2, +p1
			assertTrue(null != ec.get("seqD6"));
			assertTrue(ec.get("seqD6") instanceof Variable);
			assertTrue(EugeneConstants.TXT.equals(((Variable)ec.get("seqD6")).getType()));
			assertTrue("ATCGCGATCGATATCGCGATCGATATCGATCG".equals(
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
			
			assertTrue(ec != null);
			assertTrue(ec.get(result) != null);
			assertTrue(ec.get(result) instanceof EugeneCollection);

			assertTrue(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assertTrue(((EugeneCollection)ec.get(result)).getElements().size() == 1);

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
			
			assertTrue(ec != null);
			assertTrue(ec.get(result) != null);
			assertTrue(ec.get(result) instanceof EugeneCollection);

			assertTrue(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assertTrue(((EugeneCollection)ec.get(result)).getElements().size() == 1);

		} catch(EugeneException ee) {
			
			ee.printStackTrace();
			
			// no exception should be thrown.
			assertTrue(false);
		}
	}
	
	@Test
	public void testQuery_Sequence_EQUALS_Variable() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("num n = 1;")
			.append(result).append(" = query(PT.SEQUENCE == \"ATG\");");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assertTrue(ec != null);
			assertTrue(ec.get(result) != null);
			assertTrue(ec.get(result) instanceof EugeneCollection);

			assertTrue(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return an empty collection
			assertTrue(((EugeneCollection)ec.get(result)).getElements().isEmpty());

		} catch(EugeneException ee) {
			
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
			
			assertTrue(ec != null);
			assertTrue(ec.get(result) != null);
			assertTrue(ec.get(result) instanceof EugeneCollection);

			assertTrue(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assertTrue(((EugeneCollection)ec.get(result)).getElements().size() == 1);

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
			
			assertTrue(ec != null);
			assertTrue(ec.get(result) != null);
			assertTrue(ec.get(result) instanceof EugeneCollection);

			assertTrue(((EugeneCollection)ec.get(result)).getElements() != null);
				// the query should return 1 part, namely p1
			assertTrue(((EugeneCollection)ec.get(result)).getElements().size() == 1);

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
			
			assertTrue(ec != null);
			
			assertTrue(ec.get("strong_promoters") != null);
			assertTrue(ec.get("medium_promoters") != null);
			assertTrue(ec.get("weak_promoters") != null);
			
			assertTrue(ec.get("strong_promoters") instanceof EugeneCollection);
			assertTrue(ec.get("medium_promoters") instanceof EugeneCollection);
			assertTrue(ec.get("weak_promoters") instanceof EugeneCollection);

			assertTrue(((EugeneCollection)ec.get("strong_promoters")).getElements() != null);
			assertTrue(((EugeneCollection)ec.get("medium_promoters")).getElements() != null);
			assertTrue(((EugeneCollection)ec.get("weak_promoters")).getElements() != null);

				// the query should return 1 part, namely p1
			assertTrue(((EugeneCollection)ec.get("strong_promoters")).getElements().size() == NR_OF_PROMOTERS);
			assertTrue(((EugeneCollection)ec.get("medium_promoters")).getElements().size() == NR_OF_PROMOTERS);
			assertTrue(((EugeneCollection)ec.get("weak_promoters")).getElements().size() == NR_OF_PROMOTERS);

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
			
			assertTrue(ec != null);
			
			assertTrue(ec.get("strong_promoters") != null);
			assertTrue(ec.get("medium_promoters") != null);
			assertTrue(ec.get("weak_promoters") != null);
			
			assertTrue(ec.get("strong_promoters") instanceof EugeneCollection);
			assertTrue(ec.get("medium_promoters") instanceof EugeneCollection);
			assertTrue(ec.get("weak_promoters") instanceof EugeneCollection);

			assertTrue(((EugeneCollection)ec.get("strong_promoters")).getElements() != null);
			assertTrue(((EugeneCollection)ec.get("medium_promoters")).getElements() != null);
			assertTrue(((EugeneCollection)ec.get("weak_promoters")).getElements() != null);

				// the query should return 1 part, namely p1
			assertTrue(((EugeneCollection)ec.get("strong_promoters")).getElements().size() == NR_OF_PROMOTERS / 3);
			assertTrue(((EugeneCollection)ec.get("medium_promoters")).getElements().size() == NR_OF_PROMOTERS / 3);
			assertTrue(((EugeneCollection)ec.get("weak_promoters")).getElements().size() == NR_OF_PROMOTERS / 3);

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
			
			assertTrue(null != ec);
			
			/*-------
			 * proms Device
			 *-------*/
			assertTrue(null != ec.get("proms"));
			assertTrue(ec.get("proms") instanceof Device);			
			Device proms = (Device)ec.get("proms");
			assertTrue(null != proms.getOrientations());
			assertTrue(proms.getOrientations().size() == 2);
			assertTrue(null != proms.getOrientations(0));
			assertTrue(proms.getOrientations(0).size() == 1);
			assertTrue(proms.getOrientations(0).get(0) == Orientation.UNDEFINED);
			assertTrue(null != proms.getOrientations(1));
			assertTrue(proms.getOrientations(1).size() == 1);
			assertTrue(proms.getOrientations(1).get(0) == Orientation.UNDEFINED);

			/*-------
			 * fproms Device
			 *-------*/
			assertTrue(null != ec.get("fproms"));
			assertTrue(ec.get("fproms") instanceof Device);			
			Device fproms = (Device)ec.get("fproms");
			assertTrue(null != fproms.getOrientations());
			assertTrue(fproms.getOrientations().size() == 2);
			assertTrue(null != fproms.getOrientations(0));
			assertTrue(fproms.getOrientations(0).size() == 1);
			assertTrue(fproms.getOrientations(0).get(0) == Orientation.FORWARD);
			assertTrue(null != fproms.getOrientations(1));
			assertTrue(fproms.getOrientations(1).size() == 1);
			assertTrue(fproms.getOrientations(1).get(0) == Orientation.FORWARD);

			/*-------
			 * rproms Device
			 *-------*/
			assertTrue(null != ec.get("rproms"));
			assertTrue(ec.get("rproms") instanceof Device);			
			Device rproms = (Device)ec.get("rproms");
			assertTrue(null != rproms.getOrientations());
			assertTrue(rproms.getOrientations().size() == 2);
			assertTrue(null != rproms.getOrientations(0));
			assertTrue(rproms.getOrientations(0).size() == 1);
			assertTrue(rproms.getOrientations(0).get(0) == Orientation.REVERSE);
			assertTrue(null != rproms.getOrientations(1));
			assertTrue(rproms.getOrientations(1).size() == 1);
			assertTrue(rproms.getOrientations(1).get(0) == Orientation.REVERSE);

			/*-------
			 * hierarchical Device
			 *-------*/
			assertTrue(null != ec.get("hierarchical"));
			assertTrue(ec.get("hierarchical") instanceof Device);			
			Device hierarchical = (Device)ec.get("hierarchical");
			assertTrue(null != hierarchical.getOrientations());
			assertTrue(hierarchical.getOrientations().size() == 2);
			assertTrue(null != hierarchical.getOrientations(0));
			assertTrue(hierarchical.getOrientations(0).size() == 1);
			assertTrue(hierarchical.getOrientations(0).get(0) == Orientation.UNDEFINED);
			assertTrue(null != hierarchical.getOrientations(1));
			assertTrue(hierarchical.getOrientations(1).size() == 1);
			assertTrue(hierarchical.getOrientations(1).get(0) == Orientation.FORWARD);
		} catch(EugeneException ee) {
			// no exception allowed here
			assertTrue(false);
		}
	}
	
	@Test
	public void testNumberOfPermute() {
		String script = "PartType PT; PT p1; PT p2; Device D(PT, PT); ds = permute(D, 1);";
		try {
			Eugene eug = new Eugene();
			EugeneCollection results = eug.executeScript(script);
			
//			System.out.println(results.get("ds"));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}
}
