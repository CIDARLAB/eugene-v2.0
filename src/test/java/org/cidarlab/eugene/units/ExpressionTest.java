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

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.ExpressionExecutor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionTest {

	private ExpressionExecutor exp;
	
	@BeforeClass
	public static void setUpBeforeClass() 
			throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() 
			throws Exception {
	}

	@Before
	public void setUp() 
			throws Exception {
		
		this.exp = new ExpressionExecutor();
	}

	@After
	public void tearDown() 
			throws Exception {
	}

	@Test
	public void testIntNumPlusIntNum() {
		Variable v1 = new Variable("v1", EugeneConstants.NUM);
		v1.num = 1;
		
		Variable v2 = new Variable("v2", EugeneConstants.NUM);
		v2.num = 1;
		
		try {
			NamedElement ne = this.exp.doMinPlusOp(v2, v1, "+");
			if(ne instanceof Variable) {
				double sum = v1.getNum()+v2.getNum();
				
				assertTrue(EugeneConstants.NUM.equalsIgnoreCase(((Variable)ne).getType()) &&
						((Variable)ne).getNum() == sum);
			} else {
				assertFalse(true);
			}
		} catch(EugeneException ee) {
			// TEST FAILED
			assertFalse(true);
		}
		
	}

	@Test
	public void testDoubleNumPlusDoubleNum() {
		Variable v1 = new Variable("v1", EugeneConstants.NUM);
		v1.num = 1.1;
		
		Variable v2 = new Variable("v2", EugeneConstants.NUM);
		v2.num = 1.2;
		
		try {
			NamedElement ne = this.exp.doMinPlusOp(v2, v1, "+");
			if(ne instanceof Variable) {
				double sum = v1.getNum()+v2.getNum();
				
				assertTrue(EugeneConstants.NUM.equalsIgnoreCase(((Variable)ne).getType()) && 
						((Variable)ne).getNum() == sum);
			} else {
				assertFalse(true);
			}
		} catch(EugeneException ee) {
			// TEST FAILED
			assertFalse(true);
		}
		
	}

	@Test
	public void testTxtPlusTxt() {
		Variable v1 = new Variable("v1", EugeneConstants.TXT);
		v1.txt = "Hello ";
		
		Variable v2 = new Variable("v2", EugeneConstants.TXT);
		v2.txt = "World!";
		
		try {
			NamedElement ne = this.exp.doMinPlusOp(v2, v1, "+");
			if(ne instanceof Variable) {

				assertTrue(EugeneConstants.TXT.equalsIgnoreCase(((Variable)ne).getType()) && 
						((Variable)ne).getTxt().equals("Hello World!"));
			} else {
				assertFalse(true);
			}
		} catch(EugeneException ee) {
			// TEST FAILED
			assertFalse(true);
		}
		
	}

	@Test
	public void testTxtPlusNum() {
		Variable v1 = new Variable("v1", EugeneConstants.TXT);
		v1.txt = "PI=";
		
		Variable v2 = new Variable("v2", EugeneConstants.NUM);
		v2.num = 3.1415;
		
		try {
			
			// v1 + v2 ==> "PI=3.1415"
			NamedElement ne = this.exp.doMinPlusOp(v2, v1, "+");
			if(ne instanceof Variable) {
				assertTrue(EugeneConstants.TXT.equalsIgnoreCase(((Variable)ne).getType()) && 
						((Variable)ne).getTxt().equals("PI=3.1415") &&
						"PI=".equals(v1.getTxt()) && 3.1415==v2.getNum());
			} else {
				assertFalse(true);
			}

		
			// v2 + v1 ==> "3.1415PI="
			NamedElement ne2 = this.exp.doMinPlusOp(v1, v2, "+");
			if(ne2 instanceof Variable) {
				assertTrue(EugeneConstants.TXT.equalsIgnoreCase(((Variable)ne2).getType()) && 
						((Variable)ne2).getTxt().equals("3.1415PI="));
			} else {
				assertFalse(true);
			}

		} catch(EugeneException ee) {
			// TEST FAILED
			assertFalse(true);
		}
		
	}

}
