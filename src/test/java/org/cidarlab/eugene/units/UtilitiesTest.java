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

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.TestUtils;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.DeviceUtils;
import org.cidarlab.eugene.util.EugeneDeveloperUtils;
import org.cidarlab.eugene.util.EugeneUtils;
import org.cidarlab.eugene.util.SequenceUtils;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * The UtilitiesTest unit tests all Utility classes 
 * of Eugene contained in package. That is,
 * org.cidarlab.eugene.utils.*
 * 
 * @author Ernst Oberortner
 */
public class UtilitiesTest {

	@Test
	public void testDeviceUtilsFlip() {

		// the device under test:
		// D(A, B)

		// build the device
		Device d = new Device("D");
		List<NamedElement> loe1 = new ArrayList<NamedElement>();
		loe1.add(new PartType("A"));
		d.getComponents().add(loe1);

		List<Orientation> loo1 = new ArrayList<Orientation>();
		loo1.add(Orientation.FORWARD);
		d.getOrientations().add(loo1);

		List<NamedElement> loe2 = new ArrayList<NamedElement>();
		loe2.add(new PartType("B"));
		d.getComponents().add(loe2);

		List<Orientation> loo2 = new ArrayList<Orientation>();
		loo2.add(Orientation.FORWARD);
		d.getOrientations().add(loo2);

		
		try {
			// flip the device
			Device fd = DeviceUtils.flip(d);
			
			// that's how the flipped device should look like
			String original = "D([+A],[+B])";
			String excpected = "D([+B],[+A])";
			
			assert(original.equals(TestUtils.toTestString(d).toString()));
			assert(excpected.equals(TestUtils.toTestString(fd).toString()));
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	@Test
	public void testDeviceUtilsInvert() {

		// the device under test:
		// D(A, B)

		// build the device
		Device d = new Device("D");
		List<NamedElement> loe1 = new ArrayList<NamedElement>();
		loe1.add(new PartType("A"));
		d.getComponents().add(loe1);

		List<Orientation> loo1 = new ArrayList<Orientation>();
		loo1.add(Orientation.FORWARD);
		d.getOrientations().add(loo1);

		List<NamedElement> loe2 = new ArrayList<NamedElement>();
		loe2.add(new PartType("B"));
		d.getComponents().add(loe2);

		List<Orientation> loo2 = new ArrayList<Orientation>();
		loo2.add(Orientation.FORWARD);
		d.getOrientations().add(loo2);

		
		try {
			// flip the device
			Device fd = DeviceUtils.invert(d);
			
			// that's how the flipped device should look like
			String original = "D([+A],[+B])";
			String excpected = "D([-A],[-B])";
			
			assert(original.equals(TestUtils.toTestString(d).toString()));
			assert(excpected.equals(TestUtils.toTestString(fd).toString()));
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}


	@Test
	public void testDeviceUtilsFlipAndInvert() {

		// the device under test:
		// D(A, B)

		// build the device
		Device d = new Device("D");
		List<NamedElement> loe1 = new ArrayList<NamedElement>();
		loe1.add(new PartType("A"));
		d.getComponents().add(loe1);

		List<Orientation> loo1 = new ArrayList<Orientation>();
		loo1.add(Orientation.FORWARD);
		d.getOrientations().add(loo1);

		List<NamedElement> loe2 = new ArrayList<NamedElement>();
		loe2.add(new PartType("B"));
		d.getComponents().add(loe2);

		List<Orientation> loo2 = new ArrayList<Orientation>();
		loo2.add(Orientation.FORWARD);
		d.getOrientations().add(loo2);

		
		try {
			// flip the device
			Device fd = DeviceUtils.invert(d);
			
			// that's how the flipped device should look like
			String original = "D([+A],[+B])";
			String excpected = "D([-A],[-B])";
			
			assert(original.equals(TestUtils.toTestString(d).toString()));
			assert(excpected.equals(TestUtils.toTestString(fd).toString()));
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	@Test
	public void testSequenceUtilsReverseComplement01() {
		String seq = "ATCG";
		
		try {
			String revComp = SequenceUtils.reverseComplement(seq);
			assert(revComp.equalsIgnoreCase("CGAT"));
		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSequenceUtilsReverseComplement02() {
		String seq = "TTTT";
		
		try {
			String revComp = SequenceUtils.reverseComplement(seq);
			
			assert(revComp.equalsIgnoreCase("AAAA"));
		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSequenceUtilsReverseComplementHierarchicalDevice() {
		String script = "PartType Promoter;" +
				"Promoter pA(.SEQUENCE(\"A\"));" +
				"Promoter pT(.SEQUENCE(\"T\"));" +
				"Promoter pC(.SEQUENCE(\"C\"));" +
				"Promoter pG(.SEQUENCE(\"G\"));" +
				"Device D(pA, pT, pC, pG); ";
		
		try {
			EugeneCollection ec = new Eugene().executeScript(script);
			
			if(null != ec && !ec.getElements().isEmpty()) {
				Device D = (Device)ec.get("D");
				
				assert("ATCG".equalsIgnoreCase(D.getSequence()));
				assert("CGAT".equalsIgnoreCase(SequenceUtils.reverseComplement(D.getSequence())));
			} else {
				// something went wrong!
				assertTrue(false);
			}
		} catch(EugeneException ee) {
			// something went wrong!
			assertTrue(false);
		}
	}

	@Test
	public void testSequenceUtilsReverseComplementPartsDevice02() {
		String script = "PartType Promoter;" +
				"Promoter p1(.SEQUENCE(\"ATCG\"));" +
				"Promoter p2(.SEQUENCE(\"ATCG\"));" +
				"Device D01(-p1, +p2); " +
				"Device D02(-p1, -p2); " +
				"Device D03(+p1, +p2); " +
				"Device D04(+p1, -p2); ";
		
		try {
			EugeneCollection ec = new Eugene().executeScript(script);
			
			if(null != ec && !ec.getElements().isEmpty()) {
				Device D01 = (Device)ec.get("D01");
				assert("CGATATCG".equalsIgnoreCase(D01.getSequence()));

				Device D02 = (Device)ec.get("D02");
				assert("CGATCGAT".equalsIgnoreCase(D02.getSequence()));

				Device D03 = (Device)ec.get("D03");
				assert("ATCGATCG".equalsIgnoreCase(D03.getSequence()));

				Device D04 = (Device)ec.get("D04");
				assert("ATCGCGAT".equalsIgnoreCase(D04.getSequence()));

			} else {
				// something went wrong!
				assertTrue(false);
			}
		} catch(EugeneException ee) {
			// something went wrong!
			assertTrue(false);
		}
	}

	@Test
	public void testSequenceUtilsReverseComplementHierarchicalDevice02() {
		String script = "PartType Promoter;" +
				"Promoter p1(.SEQUENCE(\"AAAA\"));" +
				"Promoter p2(.SEQUENCE(\"TTTT\"));" +
				"Device D01(-p1, +p2); " +
				"Device D02(-p1, -p2); " +
				"Device D03(+p1, +p2); " +
				"Device D04(+p1, -p2); " +
				"Device D05(+D01);" +
				"Device D06(-D01);" +
				"Device D07(-D01, -D02);" +
				"Device D08(-D01, +D02);" +
				"Device D09(+D01, -D02);" +
				"Device D10(+D01, +D02);" +
				"Device D11(+D01, +D02, +D03, +D04);" +
				"Device D12(+D01, +D02, +D03, -D04);";
		
		try {
			EugeneCollection ec = new Eugene().executeScript(script);
			
			if(null != ec && !ec.getElements().isEmpty()) {
				// Device D01(-p1, +p2);
				Device D01 = (Device)ec.get("D01");
				String expected_D01 =
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						((Part)ec.get("p2")).getSequence();
				assert(expected_D01.equalsIgnoreCase(D01.getSequence()));

				// Device D02(-p1, -p2);
				Device D02 = (Device)ec.get("D02");
				String expected_D02 =
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence());
				assert(expected_D02.equalsIgnoreCase(D02.getSequence()));

				// Device D03(+p1, +p2);
				Device D03 = (Device)ec.get("D03");
				String expected_D03 =
						((Part)ec.get("p1")).getSequence() +
						((Part)ec.get("p2")).getSequence();
				assert(expected_D03.equalsIgnoreCase(D03.getSequence()));

				// Device D04(+p1, -p2);
				Device D04 = (Device)ec.get("D04");
				String expected_D04 =
						((Part)ec.get("p1")).getSequence() +
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence());
				assert(expected_D04.equalsIgnoreCase(D04.getSequence()));

				// Device D05(+D01);
				//            -p1, +p2
				Device D05 = (Device)ec.get("D05");
				String expected_D05 = D01.getSequence();
				assert(expected_D05.equalsIgnoreCase(D05.getSequence()));


				// Device D06(-D01);
				//            -D01   --> -p2, +p1
				Device D06 = (Device)ec.get("D06");
				String expected_D06 =
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence()) +
						((Part)ec.get("p1")).getSequence();
				assert(expected_D06.equalsIgnoreCase(D06.getSequence()));

				// Device D07(-D01, -D02);
				Device D07 = (Device)ec.get("D07");
//				String expected_D07 =
//						(EugeneUtils.flipAndInvert(D01)).getSequence() +
//						(EugeneUtils.flipAndInvert(D02)).getSequence();
				String expected_D07 =
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence()) +
						((Part)ec.get("p1")).getSequence() +
						((Part)ec.get("p2")).getSequence() +
						((Part)ec.get("p1")).getSequence();
				assert(expected_D07.equalsIgnoreCase(D07.getSequence()));
				

				// Device D08(-D01, +D02);
				Device D08 = (Device)ec.get("D08");
//				String expected_D08 =
//						(EugeneUtils.flipAndInvert(D01)).getSequence() +
//						D02.getSequence();
				String expected_D08 =
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence()) +
						((Part)ec.get("p1")).getSequence() +
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence());
				assert(expected_D08.equalsIgnoreCase(D08.getSequence()));

				// Device D09(+D01, -D02);
				Device D09 = (Device)ec.get("D09");
//				String expected_D09 =
//						D01.getSequence() +
//						(EugeneUtils.flipAndInvert(D02)).getSequence();
				String expected_D09 =
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						((Part)ec.get("p2")).getSequence() +
						((Part)ec.get("p2")).getSequence() +
						((Part)ec.get("p1")).getSequence();
				assert(expected_D09.equalsIgnoreCase(D09.getSequence()));

				// Device D10(+D01, +D02);
				Device D10 = (Device)ec.get("D10");
//				String expected_D10 =
//						D01.getSequence() +
//						D02.getSequence();
				String expected_D10 =
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						((Part)ec.get("p2")).getSequence() +
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence());
						
				assert(expected_D10.equalsIgnoreCase(D10.getSequence()));

				Device D11 = (Device)ec.get("D11");
				String expected_D11 = 
						D01.getSequence() +
						D02.getSequence() +
						D03.getSequence() +
						D04.getSequence(); 
				assert(expected_D11.equalsIgnoreCase(D11.getSequence()));

				Device D12 = (Device)ec.get("D12");
				
				// -p1, +p2, -p1, -p2, +p1, +p2, +p2, -p1
				String expected_D12 = 
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						((Part)ec.get("p2")).getSequence() +
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence()) +
						SequenceUtils.reverseComplement(((Part)ec.get("p2")).getSequence()) +
						((Part)ec.get("p1")).getSequence() +
						((Part)ec.get("p2")).getSequence() +
						((Part)ec.get("p2")).getSequence() +
						SequenceUtils.reverseComplement(((Part)ec.get("p1")).getSequence());
				assert(expected_D12.equalsIgnoreCase(D12.getSequence()));

			} else {
				// something went wrong!
				assertTrue(false);
			}
		} catch(EugeneException ee) {
			// something went wrong!
			assertTrue(false);
		}
	}

	@Test
	public void testEugeneUtilsPrettyPrintEmptyDevice() {
		String script = "Device D;";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Device D = (Device)col.get("D");
			
			String result = EugeneUtils.prettyPrint(D);
			
			String expected = "Device D ()";

			assert(expected.equals(result));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testEugeneUtilsPrettyPrintPartTypeDevice() {
		String script = "PartType PT1(); Device D(PT1);";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Device D = (Device)col.get("D");
			
			String result = EugeneUtils.prettyPrint(D);
			
			String expected = "Device D ("+EugeneDeveloperUtils.NEWLINE+"    PT1"+EugeneDeveloperUtils.NEWLINE+")";

			assert(expected.equals(result));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testEugeneUtilsPrettyPrintPartDevice() {
		String script = "PartType PT1(); PT1 p1; Device D(p1);";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Device D = (Device)col.get("D");
			
			String result = EugeneUtils.prettyPrint(D);
			
			String expected = 
					"Device D ("				+ EugeneDeveloperUtils.NEWLINE +
					"    PT1 p1 (" 				+ EugeneDeveloperUtils.NEWLINE +
					"        .SEQUENCE(\"\")," 	+ EugeneDeveloperUtils.NEWLINE +
					"        .PIGEON(\"\")" 	+ EugeneDeveloperUtils.NEWLINE +
					"    )" 					+ EugeneDeveloperUtils.NEWLINE +
					")";

			assert(expected.equals(result));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testEugeneUtilsPrettyPrintPartsDevice() {
		String script = "PartType PT1(); PT1 p11; PT1 p12; PT1 p13; Device D(p11, p12, p13);";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Device D = (Device)col.get("D");
			
			String result = EugeneUtils.prettyPrint(D);
			
			String expected = 
					"Device D ("				+ EugeneDeveloperUtils.NEWLINE +
					"    PT1 p11 ("				+ EugeneDeveloperUtils.NEWLINE +
					"        .SEQUENCE(\"\"),"	+ EugeneDeveloperUtils.NEWLINE +
					"        .PIGEON(\"\")"		+ EugeneDeveloperUtils.NEWLINE +
					"    )," 					+ EugeneDeveloperUtils.NEWLINE +
					"    PT1 p12 ("				+ EugeneDeveloperUtils.NEWLINE +
					"        .SEQUENCE(\"\"),"	+ EugeneDeveloperUtils.NEWLINE +
					"        .PIGEON(\"\")"		+ EugeneDeveloperUtils.NEWLINE +
					"    )," 					+ EugeneDeveloperUtils.NEWLINE +
					"    PT1 p13 ("				+ EugeneDeveloperUtils.NEWLINE +
					"        .SEQUENCE(\"\"),"	+ EugeneDeveloperUtils.NEWLINE +
					"        .PIGEON(\"\")"		+ EugeneDeveloperUtils.NEWLINE +
					"    )"						+ EugeneDeveloperUtils.NEWLINE +
					")";

			assert(expected.equals(result));
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testEugeneUtilsPrettyPrintTwoLevelHierachicalDevice() {
		String script = "PartType PT1(); PartType PT2(); Device subD1(PT1); Device subD2(PT2); Device supD(subD1, subD2);";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Device supD = (Device)col.get("supD");
			
			String result = EugeneUtils.prettyPrint(supD);
			
			String expected = 
					"Device supD ("			+ EugeneDeveloperUtils.NEWLINE +
					"    Device subD1 ("	+ EugeneDeveloperUtils.NEWLINE +
					"        PT1"			+ EugeneDeveloperUtils.NEWLINE +
					"    ),"				+ EugeneDeveloperUtils.NEWLINE + 
					"    Device subD2 ("	+ EugeneDeveloperUtils.NEWLINE + 
					"        PT2"			+ EugeneDeveloperUtils.NEWLINE +
					"    )"					+ EugeneDeveloperUtils.NEWLINE +
					")";

			assert(expected.equals(result));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testEugeneUtilsPrettyPrintThreeLevelHierachicalDevice() {
		String script = 
				"PartType PT1(); PartType PT2(); "+
				"Device subsubD1(PT1, PT2); Device subsubD2(PT2, PT1); "+
				"Device subD1(subsubD1, subsubD2); Device subD2(subsubD2, subsubD1); "+
				"Device supD(subD1, subD2);";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Device supD = (Device)col.get("supD");
			
			EugeneUtils.prettyPrint(supD);
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testEugeneUtilsPrettyPrintThreeLevelHierachicalDeviceWithParts() {
		String script = 
				"Property num_prop(num); Property txt_prop(txt); " +
				"Property num_list_prop(num[]); Property txt_list_prop(txt[]);" +
				"PartType PT1(num_prop, txt_prop);" +
				"PartType PT2(num_list_prop, txt_list_prop); "+
				"PT1 p1(.num_prop(1), .txt_prop(\"one\"));"+
				"PT1 p2(.num_prop(2), .txt_prop(\"two\"));"+
				"Device subsubPT(PT1, PT2); Device subsubP(p1, p2); "+
				"Device subD1(subsubPT, subsubP); Device subD2(subsubPT, subsubP); "+
				"Device supD(subD1, subD2);";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Device supD = (Device)col.get("supD");
			
			EugeneUtils.prettyPrint(supD);
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testEugeneUtilsPrettyPrintPart() {
		String script = "Property txt_prop(txt); Property num_prop(num); " +
				"PartType PT(txt_prop, num_prop); " +
				"PT p1(.txt_prop(\"one\"), .num_prop(1));";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
			Part p1 = (Part)col.get("p1");
			
			String result = EugeneUtils.prettyPrint(p1);

			String expected = 
					"PT p1 (" 					+ EugeneDeveloperUtils.NEWLINE +
					"    .num_prop(1),"			+ EugeneDeveloperUtils.NEWLINE +
					"    .SEQUENCE(\"\")," 		+ EugeneDeveloperUtils.NEWLINE +
					"    .PIGEON(\"\")," 		+ EugeneDeveloperUtils.NEWLINE +
					"    .txt_prop(\"one\")" 	+ EugeneDeveloperUtils.NEWLINE +
					")";

			assert(expected.equals(result));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

}
