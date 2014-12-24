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
