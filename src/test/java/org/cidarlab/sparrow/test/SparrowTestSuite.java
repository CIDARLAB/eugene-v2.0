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

package org.cidarlab.sparrow.test;

import org.cidarlab.sparrow.exception.ImportException;
import org.cidarlab.sparrow.exception.SparrowException;


/**
 * The SparrowTestSuite class provides 
 * various tests for the Sparrow Library Management System (LMS).
 * 
 * @author Ernst Oberortner
 */
public class SparrowTestSuite {

	public void testAll() 
			throws SparrowException {
		
		// test: data import from registry
		try {
			new URLImporter().test();
		} catch(ImportException ie) {
			throw new SparrowException("[SparrowTestSuite.URLImporter]" + ie.getMessage());
		}
		 
		// test: import data from local SBOL files
		try {
			new LocalDataImportTest().test();
		} catch(SparrowException ie) {
			throw new SparrowException("[SparrowTestSuite.LocalDataImportTest]" + ie.getMessage());
		}
		
		
		// Test: SessionID
		new SessionIDTest(500);
		
		// Test: inserting facts and then pausing the session
		String sessionId = null;
		long factCount = -1;
		try {
			SessionPersistorTest spt = new SessionPersistorTest();
			spt.test();
			
			sessionId = spt.getSessionId();
			factCount = spt.getFactCount(); 

		} catch(SparrowException spe) {
			spe.printStackTrace();
			throw new SparrowException(spe.toString());
		}

		// Test: Resuming the paused session of Test2		
		try {
			SessionLoaderTest slt = new SessionLoaderTest(sessionId, factCount);
			slt.test();
		} catch(SparrowException spe) {
			spe.printStackTrace();
			throw new SparrowException(spe.toString());
		}

		// Test: insert facts and avoid duplicates
		// a duplicate is given, if there is already a fact in the knowledge base
		// that has exactly the same property values
		// duplicates do not focus on Object IDs...
		new DuplicateTest().test();
		
		
		// Test: DATA IMPORT TESTS 
		// this test takes a long time
		// since it downloads parts from the iGEM registry
//		new RegistryImporterTest().test();
		
//		// Scalability Tester
		new ScalabilityTest(1000, 1000, 1000, 1000).test();
		
//		// TEST: QUERYING
		new QueryTester().test();
	}

}
