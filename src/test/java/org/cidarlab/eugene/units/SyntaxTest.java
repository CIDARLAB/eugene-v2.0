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

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

/**
 * The SyntaxTest class unit-tests  
 * the syntax of the Eugene language.
 * 
 * These tests include:
 * - missing semi-colons
 * - case sensitivity
 * - duplicate elements
 * - undefined elements
 * - ...
 * 
 * @author Ernst Oberortner
 */
public class SyntaxTest {

	@Test
	public void testMissingSemicolon() {
		String script = "num i = 0";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("expecting SEMIC"));
		}
	}

	@Test
	public void testCaseSensitivity_num_variable() {
		String script = "num i = 0; println(I);";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("I is not declared"));
		}
	}

	@Test
	public void testCaseSensitivity_UC_NUM_variable() {
		String script = "NUM i = 0;";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("expecting SEMIC"));
		}
	}

	@Test
	public void testCaseSensitivity_UC_DEVICE() {
		String script = "DEVICE D; ";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("I don't know anything about DEVICE!"));
		}
	}

	@Test
	public void testCaseSensitivity_device_product() {
		String script = "Device D; product(d);";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("d is not declared."));
		}
	}

	@Test
	public void testCaseSensitivity_device_UC_PRODUCT() {
		String script = "Device D; PRODUCT(D);";
		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assert(ee.getMessage().contains("There are no components (e.g. parts) specified!"));
		}
	}


}
