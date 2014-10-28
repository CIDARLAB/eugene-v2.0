package org.cidarlab.eugene;

import java.io.File;

import org.cidarlab.eugene.exception.EugeneException;

public class TestSuite {

	private static void testBasics() 
			throws EugeneException {

		/*---------------------------------------------
		 * BACKWARD COMPATIBILITY
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/basics/backward-comp/AllSyntaxExample.eug");
//		new TestSuite().test("./tests/basics/backward-comp/variable-declarations");
		new TestSuite().test("./tests/basics/backward-comp/hard-expression-test");
//		new TestSuite().test("./tests/basics/backward-comp/complex");    
		
		/*---------------------------------------------
		 * PRIMITIVE DATA TYPES AND MODIFICATIONS
		 *---------------------------------------------*/
		new TestSuite().test("./tests/basics/primitives/primitives-assign");    
		new TestSuite().test("./tests/basics/primitives/part-property-assign");    
		new TestSuite().test("./tests/basics/primitives/array-manip");    
		new TestSuite().test("./tests/basics/primitives/plus-operator");    

		// EXPRESSIONS
		new TestSuite().test("./tests/basics/expressions/plus-sub-mult-div");
		new TestSuite().test("./tests/basics/expressions/loops-and-calculations");    
		new TestSuite().test("./tests/basics/expressions/expression01");    
		
		// ASSIGNMENTS
		new TestSuite().test("./tests/basics/assignments");

		// ACCESS statements
		new TestSuite().test("./tests/basics/data-access");

		// PRINT
		new TestSuite().test("./tests/basics/print-statements");
		

		/*---------------------------------------------
		 * BUILT-IN FUNCTIONS AND FEATURES
		 *---------------------------------------------*/
		new TestSuite().test("./tests/basics/built-in/sizeof");    
		new TestSuite().test("./tests/basics/built-in/random");    
		new TestSuite().test("./tests/basics/built-in/store");    
		
		/*---------------------------------------------
		 * SEQUENCE SHENANIGANS
		 *---------------------------------------------*/
		new TestSuite().test("./tests/basics/sequence/reverse-complement");    
		new TestSuite().test("./tests/basics/sequence/random-sequence");    
		new TestSuite().test("./tests/basics/sequence/sequence-homology");    
		
		/*---------------------------------------------
		 * EUGENE + BIOLOGOY BASICS
		 *---------------------------------------------*/
		new TestSuite().test("./tests/basics/biology/property-definitions");
		new TestSuite().test("./tests/basics/biology/part-type-definitions");
		new TestSuite().test("./tests/basics/biology/part-definitions");
		new TestSuite().test("./tests/basics/biology/device-definitions");
		new TestSuite().test("./tests/basics/biology/hierarchical-composition");

		/*---------------------------------------------
		 * EUGENE CONTAINERS
		 *---------------------------------------------*/
		new TestSuite().test("./tests/basics/containers/collections");
		new TestSuite().test("./tests/basics/containers/collection-access");
		new TestSuite().test("./tests/basics/containers/collection-operations");
		new TestSuite().test("./tests/basics/containers/union");
			
		new TestSuite().test("./tests/basics/containers/arrays");

	}
	
	/*---------------------------------------------
	 * DATA EXCHANGE
	 *---------------------------------------------*/
	private static void testDataExchange() 
			throws EugeneException {
		
		// INCLUDE
		new TestSuite().test("./tests/include/include01");

		// IMPORT
		new TestSuite().test("./tests/import/import01");
		
		
		// SBOL -- IMPORT
		new TestSuite().test("./tests/data-exchange/sbol/sbol-import01");
		
		// SBOL -- EXPORT
		new TestSuite().test("./tests/data-exchange/sbol/sbol-export01");
		new TestSuite().test("./tests/data-exchange/sbol/sbol-export02");
		
		new TestSuite().test("./tests/data-exchange/sbol/round-trip");

		// PIGEON
		new TestSuite().test("./tests/pigeon");
	}
	
	/*---------------------------------------------
	 * RULES
	 *---------------------------------------------*/
	private static void testRules() 
			throws EugeneException {
		// HIERARCHICAL COMPOSITION
//		new TestSuite().test("./tests/hierarchical-composition");

		// RULES
		new TestSuite().test("./tests/rules/faulty-rules");
//		new TestSuite().test("./tests/rules/valid-rules");
//		new TestSuite().test("./tests/rules/product-and-rules");
		new TestSuite().test("./tests/rules/conjunction");
		new TestSuite().test("./tests/rules/logical-or");
		new TestSuite().test("./tests/rules/logical-or-02");
		new TestSuite().test("./tests/rules/indexed-rules");

//		new TestSuite().test("./tests/rules/represses-txt");

		
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
//		new TestSuite().test("./tests/rules/arrangement/counting");
//		new TestSuite().test("./tests/rules/arrangement/positioning");
//		new TestSuite().test("./tests/rules/arrangement/orientation");

		// logical NOT
//		new TestSuite().test("./tests/rules/arrangement/counting-not");
//		new TestSuite().test("./tests/rules/arrangement/positioning-not");
//		new TestSuite().test("./tests/rules/arrangement/orientation-not");

		// logical OR		
//		new TestSuite().test("./tests/rules/arrangement/distributive");
//		new TestSuite().test("./tests/rules/arrangement/counting-or");
		
//		new TestSuite().test("./tests/rules/multistep-rule-evaluation");
//		new TestSuite().test("./tests/rules/multistep-rule-evaluation02");
//		new TestSuite().test("./tests/rules/oriented-tus");
//		new TestSuite().test("./tests/rules/dnf/dnf-oriented-tus");
//		new TestSuite().test("./tests/rules/dnf/dnf-contains");
		
//		new TestSuite().test("./tests/rules/type-as-txt");
		
//		new TestSuite().test("./tests/rules/expression/expression-rules-01"); 
//		new TestSuite().test("./tests/rules/expression/expression-rules-02");
//		new TestSuite().test("./tests/rules/expression/string-rules-01");
//		new TestSuite().test("./tests/rules/expression/string-regexp-01");
//		new TestSuite().test("./tests/rules/expression/expression-rules-declaration");
//		new TestSuite().test("./tests/rules/expression/expression-rules-or");		

//		new TestSuite().test("./tests/rules/expression/expression-rules-arrays-01");
//		new TestSuite().test("./tests/rules/expression/string-rules-01");
		
		/*
		 * DISJUNCTIVE NORMAL FORM
		 */

		/*---------------------------------------------
		 * PRODUCT
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/product02");
		
		/*---------------------------------------------
		 * MEETING 08/01
		 *---------------------------------------------*/
		
		// 1. EXPRESSION RULES
		// a1 + a2 + ... + aN >= 10
		new TestSuite().test("./tests/rules/expression/expression-rules-01"); 
		new TestSuite().test("./tests/rules/expression/expression-rules-02");
		new TestSuite().test("./tests/rules/expression/expression-rules-03");  
		new TestSuite().test("./tests/rules/expression/expression-rules-04");  
		new TestSuite().test("./tests/rules/expression/expression-rules-05");  
//		new TestSuite().test("./tests/rules/expression/expression-rules-or-01");		
//		new TestSuite().test("./tests/rules/expression/expression-rules-or-02");		
//		new TestSuite().test("./tests/rules/expression/expression-rules-NOT-01");		
//		new TestSuite().test("./tests/rules/expression/expression-rules-NOT-02");		
//		new TestSuite().test("./tests/rules/expression/expression-rules-arrays-01");		

		
		/*---------------------------------------------
		 * INTERACTIONS
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/interactions/interaction-specifications");
//		new TestSuite().test("./tests/interactions/cidar");
//		new TestSuite().test("./tests/interactions/interaction-visualization");
		
		
		// 2. Disjunctive Normal Form (DNF)   --- DONE
//		new TestSuite().test("./tests/rules/oriented-tus");
//		new TestSuite().test("./tests/rules/dnf/dnf-oriented-tus");
//		new TestSuite().test("./tests/rules/dnf/dnf-contains");
	}
	
	
	/*---------------------------------------------
	 * IMPERATIVE LANGUAGE FEATURES
	 *---------------------------------------------*/	
	private static void testImperativeFeatures() 
			throws EugeneException {
		// CONDITIONAL BRANCHES
//		new TestSuite().testAll("./tests/imperative/branches");

		// FOR LOOPS
//		new TestSuite().testAll("./tests/imperative/loops");
		
		// FUNCTIONS
//		new TestSuite().testAll("./tests/imperative/functions");

		// individual tests		
//		new TestSuite().test("./tests/imperative/functions/function-syntax");
//		new TestSuite().test("./tests/imperative/functions/function-execution");
//		new TestSuite().test("./tests/imperative/functions/function-call-as-parameters");		
//		new TestSuite().test("./tests/imperative/functions/2-phase-interpreter");
//		new TestSuite().test("./tests/imperative/functions/functions-calling-functions");
//		new TestSuite().test("./tests/imperative/functions/scope-in-functions");
		new TestSuite().test("./tests/imperative/functions/include-function-library");

		// TODO!!
	}
	
	/*---------------------------------------------
	 * REAL WORLD EXAMPLES
	 *---------------------------------------------*/	
	private static void testRealWorldExamples() 
			throws EugeneException {
		new TestSuite().test("./designs/cidar/inverter/inverters");
		new TestSuite().test("./designs/cidar/ernst/sb2/demo");
		new TestSuite().test("./designs/cidar/genomatica/genomatica");
	}
	
	/*---------------------------------------------
	 * EUGENELAB TUTORIALS
	 *---------------------------------------------*/	
	private static void testEugeneLabTutorials()
			throws EugeneException {
		ROOT_DIRECTORY = "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/tests/EugeneLab";
//		ROOT_DIRECTORY = "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/EugeneLab_Home/home/no_name_user/";

		// TESTING INDIVIDUAL FILES
//		new TestSuite().test("./tests/EugeneLab/data-exchange/01-Rule-based-Design.eug");
		

		// TESTING ALL FILES
		new TestSuite().testAll(ROOT_DIRECTORY);

	}
	
	private static void testAll() {
		
		ROOT_DIRECTORY = "./tests/";
		
		try {
			new TestSuite().testAll("./tests/");
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
		
	}
	
	public static void testHavingSomeFunWithEugene()
			throws EugeneException {
		
		/*---------------------------------------------
		 * EUGENE FUN - INSPIRED BY EC327 :)
		 *---------------------------------------------*/
		new TestSuite().test("./tests/eugene-fun/diagonals");
		new TestSuite().test("./tests/eugene-fun/no-duplicates");
		new TestSuite().test("./tests/eugene-fun/bubble-sort");		
		new TestSuite().test("./tests/eugene-fun/rhombus");
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
		testImperativeFeatures();
//		testEugeneLabTutorials();
		
//		testHavingSomeFunWithEugene();
		
//		testDataExchange();
//		testRules();
//		testRealWorldExamples();
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
			e.executeFile(
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
