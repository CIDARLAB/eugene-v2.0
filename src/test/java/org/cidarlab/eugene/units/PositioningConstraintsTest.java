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
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

/**
 * The PositioningConstraintsTest class is a set of 
 * unit-tests for testing (mini)Eugene Positioning 
 * constraints, i.e. BEFORE, AFTER, and NEXTTO
 *
 * Test Strategy:
 * --------------
 * We execute a Eugene script including Positioning
 * constraints and compare the number of returned 
 * solutions with the number of expected solutions.
 * 
 * @author Ernst Oberortner
 *
 */
public class PositioningConstraintsTest {


	// we define a part library of three parts
	// each having the same part type
	private static final String partLibrary = 
			"PartType PT; " +
			"PT p1; PT p2; PT p3;";
	
	// that's the name of the EugeneArray that
	// contains the results of the product() function
	private static final String result = "result";

	/* -----
	 * AFTER
	 * ----- */

	@Test
	public void testBefore_D1() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D1(PT);")
			.append("Rule R1(ON D1: p1 BEFORE p2);")
			.append(result).append("=product(D1);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 6);

		} catch(EugeneException ee) {

			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testBefore_D2() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D2(PT, PT);")
			.append("Rule R2(ON D2: p1 BEFORE p2);")
			.append(result).append("=product(D2);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			/*
			 * p1, p1  --> 4  
			 * p2, p2  --> 4
			 * p3, p3  --> 4
			 * 
			 * +
			 * 
			 * p1, p2  --> 4
			 * -- INVALID: p2, p1  XXX 0
			 * 
			 * + 
			 * 
			 * p1, p3  --> 4 
			 * p3, p1  --> 4
			 * 
			 * +
			 * 
			 * p2, p3  --> 4
			 * p3, p2  --> 4
			 * 
			 * --> 4 * 8 --> 32
			 * 
			 */
			assert(((EugeneArray)ec.get(result)).getElements() != null);			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 32);

		} catch(EugeneException ee) {

			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testBefore_D3() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: p1 BEFORE p2 and p2 BEFORE p3);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 112);

		} catch(EugeneException ee) {

			// no exception should be thrown.
			assertTrue(false);
		}
	}

	/* -----
	 * AFTER
	 * ----- */
	
	@Test
	public void testAfter_D3() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: p1 AFTER p2 and p2 AFTER p3);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 112);

		} catch(EugeneException ee) {

			// no exception should be thrown.
			assertTrue(false);
		}
	}

	/* ------
	 * NEXTTO
	 * ------ */
	
	@Test
	public void testNextTo_D3_and() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: contains p1 and contains p2 and contains p3 and "
					+ "p1 NEXTTO p2 and p2 NEXTTO p3);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 16);

		} catch(EugeneException ee) {

			ee.printStackTrace();
			// no exception should be thrown.
			assertTrue(false);
		}
	}

	@Test
	public void testNextTo_D3_or() {
		StringBuilder script = new StringBuilder();
		script.append(partLibrary)
			.append("Device D3(PT, PT, PT);")
			.append("Rule R3(ON D3: contains p1 and contains p2 and contains p3 and "
					+ "p1 NEXTTO p2 or p2 NEXTTO p3);")
			.append(result).append("=product(D3);");
		
		try {
			Eugene e = new Eugene();
			
			EugeneCollection ec = e.executeScript(script.toString());
			
			/*
			 * p1 p2 p3   Y
			 * p1 p3 p2   Y
			 * p2 p1 p3   Y
			 * p2 p3 p1   Y
			 * p3 p1 p2   Y
			 * p3 p2 p1   Y
			 * 
			 * --> 6 combinations a 8 orientations --> 48 solutions 
			 */
			assert(ec != null);
			assert(ec.get(result) != null);
			assert(ec.get(result) instanceof EugeneArray);

			assert(((EugeneArray)ec.get(result)).getElements() != null);			
			assert(((EugeneArray)ec.get(result)).getElements().size() == 48);

		} catch(EugeneException ee) {

			ee.printStackTrace();
			// no exception should be thrown.
			assertTrue(false);
		}
	}
}
