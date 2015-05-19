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

import java.util.Collection;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VariableTest {

	@Test
	public void testInvalidVariables() {
		try {
			new Variable("",EugeneConstants.NUM);
		} catch(IllegalArgumentException iae) {
			assertTrue("Invalid name!".equals(iae.getLocalizedMessage()));
		}
		
		try {
			new Variable("var_name","invalid_type");
		} catch(IllegalArgumentException iae) {
			assertTrue("Invalid type of variable!".equals(iae.getLocalizedMessage()));
		}
	}
	
	@Test
	public void testNumVariables() {
		Variable numVar1 = new Variable("num_var1", EugeneConstants.NUM);
		assertTrue(EugeneConstants.NUM.equals(numVar1.getType()));
		assertTrue(numVar1.getNum() == 0);
	}
	
	@Test
	public void testAnonymousVariable() {
		Variable v = new Variable(null, EugeneConstants.NUM);
		assertTrue(v.isAnonymous());
	}
	
	@Test
	public void testAssignConstantToVariable() {
		String script = "num var1=0;";
		
		try {
			Eugene eug = new Eugene();
			EugeneCollection results = 
					eug.executeScript(script);
			
			assertTrue(results != null);
			assertTrue(results.get("var1") != null);
			assertTrue(results.get("var1") instanceof Variable);
			assertTrue(EugeneConstants.NUM.equals(((Variable)results.get("var1")).getType()));
			assertTrue(0 == ((Variable)results.get("var1")).getNum());
			
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);	// no exception allowed
		}
	}
	
}
