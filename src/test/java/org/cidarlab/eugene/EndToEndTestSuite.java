package org.cidarlab.eugene;

import java.io.File;
import java.util.Collection;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.exception.EugeneException;

public class EndToEndTestSuite {

	private static void testBasics() 
			throws EugeneException {

		/*---------------------------------------------
		 * BACKWARD COMPATIBILITY
		 *---------------------------------------------*/
//		new EndToEndTestSuite().test("./tests/basics/backward-comp/AllSyntaxExample.eug");
//		new EndToEndTestSuite().test("./tests/basics/backward-comp/variable-declarations");
		new EndToEndTestSuite().test("./tests/basics/backward-comp/hard-expression-test");
//		new EndToEndTestSuite().test("./tests/basics/backward-comp/complex");    
		
		/*---------------------------------------------
		 * PRIMITIVE DATA TYPES AND MODIFICATIONS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./tests/basics/primitives/primitives-assign");    
		new EndToEndTestSuite().test("./tests/basics/primitives/part-property-assign");    
		new EndToEndTestSuite().test("./tests/basics/primitives/array-manip");    
		new EndToEndTestSuite().test("./tests/basics/primitives/plus-operator");    

		// EXPRESSIONS
		new EndToEndTestSuite().test("./tests/basics/expressions/plus-sub-mult-div");
		new EndToEndTestSuite().test("./tests/basics/expressions/loops-and-calculations");    
		new EndToEndTestSuite().test("./tests/basics/expressions/expression01");    
		
		// ASSIGNMENTS
		new EndToEndTestSuite().test("./tests/basics/assignments");

		// ACCESS statements
		new EndToEndTestSuite().test("./tests/basics/data-access");

		// PRINT
		new EndToEndTestSuite().test("./tests/basics/print-statements");
		

		/*---------------------------------------------
		 * BUILT-IN FUNCTIONS AND FEATURES
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./tests/basics/built-in/sizeof");    
		new EndToEndTestSuite().test("./tests/basics/built-in/random");    
		new EndToEndTestSuite().test("./tests/basics/built-in/store");    
		
		/*---------------------------------------------
		 * SEQUENCE SHENANIGANS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./tests/basics/sequence/reverse-complement");    
		new EndToEndTestSuite().test("./tests/basics/sequence/random-sequence");    
		new EndToEndTestSuite().test("./tests/basics/sequence/sequence-homology");    
		
		/*---------------------------------------------
		 * EUGENE + BIOLOGOY BASICS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./tests/basics/biology/property-definitions");
		new EndToEndTestSuite().test("./tests/basics/biology/part-type-definitions");
		new EndToEndTestSuite().test("./tests/basics/biology/part-definitions");
		new EndToEndTestSuite().test("./tests/basics/biology/device-definitions");
		new EndToEndTestSuite().test("./tests/basics/biology/hierarchical-composition");

		/*---------------------------------------------
		 * EUGENE CONTAINERS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./tests/basics/containers/collections");
		new EndToEndTestSuite().test("./tests/basics/containers/collection-access");
		new EndToEndTestSuite().test("./tests/basics/containers/collection-operations");
		new EndToEndTestSuite().test("./tests/basics/containers/union");
			
		new EndToEndTestSuite().test("./tests/basics/containers/arrays");

	}
	
	/*---------------------------------------------
	 * DATA EXCHANGE
	 *---------------------------------------------*/
	private static void testDataExchange() 
			throws EugeneException {
		
		ROOT_DIRECTORY = "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/";

//		new EndToEndTestSuite().testAll("./tests/data-exchange");

//		//---- INDIVIDUAL TESTS ----
//		// INCLUDE
//		new EndToEndTestSuite().test("./tests/data-exchange/include/include01");
//
//		// IMPORT
//		new EndToEndTestSuite().test("./tests/data-exchange/import/import01");
//		
//		
//		// SBOL -- IMPORT
//		new EndToEndTestSuite().test("./tests/data-exchange/sbol/sbol-import01");
//		new EndToEndTestSuite().test("./tests/data-exchange/sbol/import-from-cello-01");

//		// SBOL -- EXPORT
//		new EndToEndTestSuite().test("./tests/data-exchange/sbol/sbol-export01");
//		new EndToEndTestSuite().test("./tests/data-exchange/sbol/sbol-export02");
//
		// HIEARCHIES
		new EndToEndTestSuite().test("./tests/data-exchange/sbol/sbol-hierarchy-export");

		// SEQUENCES
//		new EndToEndTestSuite().test("./tests/data-exchange/sbol/sbol-sequence-hierarchy-export");

//		new EndToEndTestSuite().test("./tests/data-exchange/sbol/round-trip");
//
		// PIGEON
//		new EndToEndTestSuite().test("./tests/data-exchange/pigeon");
		
		
		// GENBANK
		// - IMPORT
		//new EndToEndTestSuite().test("./tests/data-exchange/genbank/genbank-import");
		
		// - EXPORT
		
	}
	
	/*---------------------------------------------
	 * RULES
	 *---------------------------------------------*/
	private static void testRules() 
			throws EugeneException {
		// HIERARCHICAL COMPOSITION
//		new EndToEndTestSuite().test("./tests/hierarchical-composition");

		// RULES
		new EndToEndTestSuite().test("./tests/rules/faulty-rules");
//		new EndToEndTestSuite().test("./tests/rules/valid-rules");
//		new EndToEndTestSuite().test("./tests/rules/product-and-rules");
		new EndToEndTestSuite().test("./tests/rules/conjunction");
		new EndToEndTestSuite().test("./tests/rules/logical-or");
		new EndToEndTestSuite().test("./tests/rules/logical-or-02");
		new EndToEndTestSuite().test("./tests/rules/indexed-rules");

//		new EndToEndTestSuite().test("./tests/rules/represses-txt");

		
		/**************************************************************
		 * ARCHITECTURAL CONSTRAINTS
		 * 
		 * those need to be compiled into miniEugene constraints
		 * main difficulties: when constraint operand is a part type
		 * => how to substitute part types with available parts?
		 * => how to logically combine the substituted constraints?
		 **************************************************************/
		
		/* TBD: Wed, 07/23 and/or Th, 07/24
		 *  
		 * USE SUBSTITUTION 
		 * e.g. not forward Promoter \/ Promoter before RBS
		 * 
		 * Promoter := {p1, p2}
		 * RBS := {rbs1, rbs2}
		 * 
		 * =>
		 * not forward p1 \/ p1 before rbs1
		 * not forward p1 \/ p1 before rbs2
		 * not forward p2 \/ p2 before rbs1
		 * not forward p2 \/ p2 before rbs2
		 * 
		 * i.e. keep the select part during the substitution process
		 */
		
		/*----------------------------------------------------------
		 * Eugene 2 Drools Compiler
		 *----------------------------------------------------------*/
//		new EndToEndTestSuite().test("./tests/rules/arrangement/counting");
//		new EndToEndTestSuite().test("./tests/rules/arrangement/positioning");
//		new EndToEndTestSuite().test("./tests/rules/arrangement/orientation");

		// logical NOT
//		new EndToEndTestSuite().test("./tests/rules/arrangement/counting-not");
//		new EndToEndTestSuite().test("./tests/rules/arrangement/positioning-not");
//		new EndToEndTestSuite().test("./tests/rules/arrangement/orientation-not");

		// logical OR		
//		new EndToEndTestSuite().test("./tests/rules/arrangement/distributive");
//		new EndToEndTestSuite().test("./tests/rules/arrangement/counting-or");
		
//		new EndToEndTestSuite().test("./tests/rules/multistep-rule-evaluation");
//		new EndToEndTestSuite().test("./tests/rules/multistep-rule-evaluation02");
//		new EndToEndTestSuite().test("./tests/rules/oriented-tus");
//		new EndToEndTestSuite().test("./tests/rules/dnf/dnf-oriented-tus");
//		new EndToEndTestSuite().test("./tests/rules/dnf/dnf-contains");
		
//		new EndToEndTestSuite().test("./tests/rules/type-as-txt");
		
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-01"); 
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-02");
		new EndToEndTestSuite().test("./tests/rules/expression/string-rules-01");
//		new EndToEndTestSuite().test("./tests/rules/expression/string-regexp-01");
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-declaration");
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-or");		

//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-arrays-01");
//		new EndToEndTestSuite().test("./tests/rules/expression/string-rules-01");
		
		/*
		 * DISJUNCTIVE NORMAL FORM
		 */

		/*---------------------------------------------
		 * PRODUCT
		 *---------------------------------------------*/
//		new EndToEndTestSuite().test("./tests/product02");
		
		/*---------------------------------------------
		 * MEETING 08/01
		 *---------------------------------------------*/
		
		// 1. EXPRESSION RULES
		// a1 + a2 + ... + aN >= 10
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-01"); 
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-02");
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-03");  
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-04");  
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-05");  
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-or-01");		
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-or-02");		
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-NOT-01");		
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-NOT-02");		
//		new EndToEndTestSuite().test("./tests/rules/expression/expression-rules-arrays-01");		

		
		/*---------------------------------------------
		 * INTERACTIONS
		 *---------------------------------------------*/
//		new EndToEndTestSuite().test("./tests/interactions/interaction-specifications");
//		new EndToEndTestSuite().test("./tests/interactions/cidar");
//		new EndToEndTestSuite().test("./tests/interactions/interaction-visualization");
		
		
		// 2. Disjunctive Normal Form (DNF)   --- DONE
//		new EndToEndTestSuite().test("./tests/rules/oriented-tus");
//		new EndToEndTestSuite().test("./tests/rules/dnf/dnf-oriented-tus");
//		new EndToEndTestSuite().test("./tests/rules/dnf/dnf-contains");
	}
	
	
	/*---------------------------------------------
	 * IMPERATIVE LANGUAGE FEATURES
	 *---------------------------------------------*/	
	private static void testImperativeFeatures() 
			throws EugeneException {
		// CONDITIONAL BRANCHES
		new EndToEndTestSuite().testAll("./tests/imperative/branches");

		// FOR LOOPS
		new EndToEndTestSuite().testAll("./tests/imperative/loops");
		
		// FUNCTIONS
		new EndToEndTestSuite().testAll("./tests/imperative/functions");

		// SCOPING
		new EndToEndTestSuite().testAll("./tests/imperative/scoping");

	}
	
	/*---------------------------------------------
	 * REAL WORLD EXAMPLES
	 *---------------------------------------------*/	
	private static void testRealWorldExamples() 
			throws EugeneException {
		new EndToEndTestSuite().test("./designs/cidar/inverter/inverters");
		new EndToEndTestSuite().test("./designs/cidar/ernst/sb2/demo");
		new EndToEndTestSuite().test("./designs/cidar/genomatica/genomatica");
	}
	
	/*---------------------------------------------
	 * EUGENELAB TUTORIALS
	 *---------------------------------------------*/	
	private static void testEugeneLabTutorials()
			throws EugeneException {
		ROOT_DIRECTORY = "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/tests/EugeneLab";
//		ROOT_DIRECTORY = "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/EugeneLab_Home/home/no_name_user/";
		// TESTING ALL FILES
//		new EndToEndTestSuite().testAll(ROOT_DIRECTORY);

		// TESTING INDIVIDUAL FILES
//		new EndToEndTestSuite().test("./tests/EugeneLab/data-exchange/01-Rule-based-Design.eug");
//		new EndToEndTestSuite().test("./tests/EugeneLab/imperative-features/11-functions.eug");
//		new EndToEndTestSuite().test("./tests/EugeneLab/imperative-features/12-functions-and-arrays.eug");
//		new EndToEndTestSuite().test("./tests/EugeneLab/sequence-shenanigans/04-constraint-number-of-occurrences.eug");
	}
	
	private static void testAll() {
		
		ROOT_DIRECTORY = "./tests/";
		
		try {
			new EndToEndTestSuite().testAll("./tests/");
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
		
	}
	
	public static void testEC327FunWithEugene()
			throws EugeneException {
		
		/*---------------------------------------------
		 * EUGENE FUN - INSPIRED BY EC327 :)
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./tests/eugene-fun/diagonals");
		new EndToEndTestSuite().test("./tests/eugene-fun/no-duplicates");
		new EndToEndTestSuite().test("./tests/eugene-fun/bubble-sort");		
		new EndToEndTestSuite().test("./tests/eugene-fun/rhombus");
	}
	
	public static void testInteractWithLMS()
			throws EugeneException {
		
		ROOT_DIRECTORY = "./tests/lms";
		new EndToEndTestSuite().testAll(ROOT_DIRECTORY);		
	}
	
	/**
	 * main()
	 * 
	 * @param args
	 * @throws EugeneException
	 */
	public static void main(String[] args) 
			throws EugeneException {

//		testBasics();
//		testImperativeFeatures();
//		testEugeneLabTutorials();		
		testDataExchange();

//		testInteractWithLMS();

		// TODOs:
//		testRules();
//		testRealWorldExamples();

//		testEC327FunWithEugene();
//		testAll();	
	}
	

	public static String ROOT_DIRECTORY = ".";
	
	public void test(String file)
			throws EugeneException {
		System.out.println("**** TESTING: " + file +" ****");
		try {
			long t1 = System.nanoTime();
			
			Eugene e= new Eugene();
			e.setRootDirectory(ROOT_DIRECTORY);
			Collection<Component> coc = e.executeFile(
											new File(file));

			long tProcessing = System.nanoTime() - t1;
			
			System.out.println("[TestSuite.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	/**
	 * The testAll/1 method takes as input a directory,  
	 * recursively traverses it, and executes every Eugene script.
	 * 
	 * @param path ... the PATH of the test directory
	 */
	public void testAll( String path ) 
			throws EugeneException {
		File root = new File( path );
		
		if(!root.exists()) {
			throw new EugeneException("Path does not exist! " + path);
		}
	    File[] list = root.listFiles();

		for ( File f : list ) {
			if ( f.isDirectory() ) {
				testAll( f.getAbsolutePath() );
			} else {
				try {
					this.test(f.getAbsoluteFile().toString());
				} catch (EugeneException ee) {
					throw new EugeneException(ee.getLocalizedMessage());
				}
			}
		}
	}
}
