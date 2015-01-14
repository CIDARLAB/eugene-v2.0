package org.cidarlab.eugene;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;

public class EndToEndTestSuite {

	private static void testBasics() 
			throws EugeneException {

		/*---------------------------------------------
		 * BACKWARD COMPATIBILITY
		 *---------------------------------------------*/
//		new EndToEndTestSuite().test("./src/test/eugene/basics/backward-comp/AllSyntaxExample.eug");
//		new EndToEndTestSuite().test("./src/test/eugene/basics/backward-comp/variable-declarations");
		new EndToEndTestSuite().test("./src/test/eugene/basics/backward-comp/hard-expression-test");
//		new EndToEndTestSuite().test("./src/test/eugene/basics/backward-comp/complex");    
		
		/*---------------------------------------------
		 * PRIMITIVE DATA TYPES AND MODIFICATIONS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./src/test/eugene/basics/primitives/primitives-assign");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/primitives/part-property-assign");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/primitives/array-manip");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/primitives/plus-operator");    

		// EXPRESSIONS
		new EndToEndTestSuite().test("./src/test/eugene/basics/expressions/plus-sub-mult-div");
		new EndToEndTestSuite().test("./src/test/eugene/basics/expressions/loops-and-calculations");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/expressions/expression01");    
		
		// ASSIGNMENTS
		new EndToEndTestSuite().test("./src/test/eugene/basics/assignments");

		// ACCESS statements
		new EndToEndTestSuite().test("./src/test/eugene/basics/data-access");

		// PRINT
		new EndToEndTestSuite().test("./src/test/eugene/basics/print-statements");
		

		/*---------------------------------------------
		 * BUILT-IN FUNCTIONS AND FEATURES
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./src/test/eugene/basics/built-in/sizeof");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/built-in/random");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/built-in/store");    
		
		/*---------------------------------------------
		 * SEQUENCE SHENANIGANS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./src/test/eugene/basics/sequence/reverse-complement");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/sequence/random-sequence");    
		new EndToEndTestSuite().test("./src/test/eugene/basics/sequence/sequence-homology");    
		
		/*---------------------------------------------
		 * EUGENE + BIOLOGOY BASICS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./src/test/eugene/basics/biology/property-definitions");
		new EndToEndTestSuite().test("./src/test/eugene/basics/biology/part-type-definitions");
		new EndToEndTestSuite().test("./src/test/eugene/basics/biology/part-definitions");
		new EndToEndTestSuite().test("./src/test/eugene/basics/biology/device-definitions");
		new EndToEndTestSuite().test("./src/test/eugene/basics/biology/hierarchical-composition");

		/*---------------------------------------------
		 * EUGENE CONTAINERS
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./src/test/eugene/basics/containers/collections");
		new EndToEndTestSuite().test("./src/test/eugene/basics/containers/collection-access");
		new EndToEndTestSuite().test("./src/test/eugene/basics/containers/collection-operations");
		new EndToEndTestSuite().test("./src/test/eugene/basics/containers/union");
			
		new EndToEndTestSuite().test("./src/test/eugene/basics/containers/arrays");

	}
	
	/*---------------------------------------------
	 * DATA EXCHANGE
	 *---------------------------------------------*/
	private static void testDataExchange() 
			throws EugeneException {
		
		ROOT_DIRECTORY = "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/";

		new EndToEndTestSuite().testAll("./src/test/eugene/data-exchange");

//		//---- INDIVIDUAL TESTS ----
//		// INCLUDE
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/include/include01");
//
//		// IMPORT
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/import/import01");
//		
//		
//		// SBOL -- IMPORT
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/sbol/sbol-import01");
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/sbol/import-from-cello-01");

//		// SBOL -- EXPORT
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/sbol/sbol-export01");
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/sbol/sbol-export02");
//
		// HIEARCHIES
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/sbol/sbol-hierarchy-export");

		// SEQUENCES
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/sbol/sbol-sequence-hierarchy-export");

//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/sbol/round-trip");
//
		// PIGEON
//		new EndToEndTestSuite().test("./src/test/eugene/data-exchange/pigeon");
		
		
		// GENBANK
		// - IMPORT
		//new EndToEndTestSuite().test("./src/test/eugene/data-exchange/genbank/genbank-import");
		
		// - EXPORT
		
	}
	
	/*---------------------------------------------
	 * RULES
	 *---------------------------------------------*/
	private static void testRules() 
			throws EugeneException {
		// HIERARCHICAL COMPOSITION
//		new EndToEndTestSuite().test("./src/test/eugene/hierarchical-composition");

		// RULES
//		new EndToEndTestSuite().test("./src/test/eugene/rules/faulty-rules");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/valid-rules");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/product-and-rules");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/conjunction");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/logical-or");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/logical-or-02");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/indexed-rules");

//		new EndToEndTestSuite().test("./src/test/eugene/rules/represses-txt");

		
		
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
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/counting");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/positioning");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/orientation");

		// logical NOT
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/counting-not");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/positioning-not");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/orientation-not");

		// logical OR		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/distributive");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/arrangement/counting-or");
		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/multistep-rule-evaluation");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/multistep-rule-evaluation02");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/oriented-tus");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/dnf/dnf-oriented-tus");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/dnf/dnf-contains");
		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/type-as-txt");
		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-01"); 
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-02");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/string-rules-01");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/string-regexp-01");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-declaration");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-or");		

//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-arrays-01");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/string-rules-01");
		
		/*
		 * DISJUNCTIVE NORMAL FORM
		 */

		/*---------------------------------------------
		 * PRODUCT
		 *---------------------------------------------*/
//		new EndToEndTestSuite().test("./src/test/eugene/product02");
		
		/*---------------------------------------------
		 * MEETING 08/01
		 *---------------------------------------------*/
		
		// 1. EXPRESSION RULES
		// a1 + a2 + ... + aN >= 10
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-01"); 
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-02");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-03");  
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-04");  
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-05");  
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-or-01");		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-or-02");		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-NOT-01");		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-NOT-02");		
//		new EndToEndTestSuite().test("./src/test/eugene/rules/expression/expression-rules-arrays-01");		

		
		/*---------------------------------------------
		 * INTERACTIONS
		 *---------------------------------------------*/
//		new EndToEndTestSuite().test("./src/test/eugene/interactions/interaction-specifications");
//		new EndToEndTestSuite().test("./src/test/eugene/interactions/cidar");
//		new EndToEndTestSuite().test("./src/test/eugene/interactions/interaction-visualization");
		
		
		// 2. Disjunctive Normal Form (DNF)   --- DONE
//		new EndToEndTestSuite().test("./src/test/eugene/rules/oriented-tus");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/dnf/dnf-oriented-tus");
//		new EndToEndTestSuite().test("./src/test/eugene/rules/dnf/dnf-contains");
		
		/*----
		 * RuleBuilder
		 *----*/
		new EndToEndTestSuite().test("./src/test/eugene/rules/rule-builder/rule-builder-instantiation");
		new EndToEndTestSuite().test("./src/test/eugene/rules/rule-builder/rule-builder-AND");
		new EndToEndTestSuite().test("./src/test/eugene/rules/rule-builder/rule-builder-AND-02");
		
	}
	
	
	/*---------------------------------------------
	 * IMPERATIVE LANGUAGE FEATURES
	 *---------------------------------------------*/	
	private static void testImperativeFeatures() 
			throws EugeneException {
		// CONDITIONAL BRANCHES
		new EndToEndTestSuite().testAll("./src/test/eugene/imperative/branches");

		// FOR LOOPS
		new EndToEndTestSuite().testAll("./src/test/eugene/imperative/loops");
		
		// FUNCTIONS
		new EndToEndTestSuite().testAll("./src/test/eugene/imperative/functions");

		// SCOPING
		new EndToEndTestSuite().testAll("./src/test/eugene/imperative/scoping");

	}
	
	private static void testGrammars()
			throws EugeneException {
		
		new EndToEndTestSuite().test("./src/test/eugene/grammars/hierarchical-composition-01");
		
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
		ROOT_DIRECTORY = "./src/demo/eugene";
		// TESTING ALL FILES
		new EndToEndTestSuite().testAll(ROOT_DIRECTORY);

		// TESTING INDIVIDUAL FILES
//		new EndToEndTestSuite().test("./src/test/eugene/EugeneLab/data-exchange/01-Rule-based-Design.eug");
//		new EndToEndTestSuite().test("./src/test/eugene/EugeneLab/imperative-features/11-functions.eug");
//		new EndToEndTestSuite().test("./src/test/eugene/EugeneLab/imperative-features/12-functions-and-arrays.eug");
//		new EndToEndTestSuite().test("./src/test/eugene/EugeneLab/sequence-shenanigans/04-constraint-number-of-occurrences.eug");
	}
	
	private static void testAll() {
		
		ROOT_DIRECTORY = "./src/test/eugene/";
		
		try {
			new EndToEndTestSuite().testAll("./src/test/eugene/");
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
		
	}
	
	public static void testEC327FunWithEugene()
			throws EugeneException {
		
		/*---------------------------------------------
		 * EUGENE FUN - INSPIRED BY EC327 :)
		 *---------------------------------------------*/
		new EndToEndTestSuite().test("./src/test/eugene/eugene-fun/diagonals");
		new EndToEndTestSuite().test("./src/test/eugene/eugene-fun/no-duplicates");
		new EndToEndTestSuite().test("./src/test/eugene/eugene-fun/bubble-sort");		
		new EndToEndTestSuite().test("./src/test/eugene/eugene-fun/rhombus");
	}
	
	/**
	 * here we test Eugene's built-in functions:
	 * - SIZEOF
	 * - PRODUCT
	 * - PERMUTE
	 * 
	 * @throws EugeneException
	 */
	public static void testBuiltInFunctions() 
			throws EugeneException {
		
//		new EndToEndTestSuite().test("./src/test/eugene/built-in-functions/sizeof");
//		new EndToEndTestSuite().test("./src/test/eugene/built-in-functions/product");
		
		new EndToEndTestSuite().test("./src/test/eugene/built-in-functions/permute");
	}
	
	public static void testInteractWithLMS()
			throws EugeneException {
		
		ROOT_DIRECTORY = "./src/test/eugene/lms";
		new EndToEndTestSuite().testAll(ROOT_DIRECTORY);		
	}
	
	public static void testCelloDesigns() 
			throws EugeneException {
		
		new EndToEndTestSuite().test("./designs/cidar/cello/or/or.eug");
		
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
//		testBuiltInFunctions();
//		testEugeneLabTutorials();		
//		testDataExchange();

//		testInteractWithLMS();

		// TODOs:
//		testRules();
//		testGrammars();
//		testRealWorldExamples();

//		testEC327FunWithEugene();
		
		testCelloDesigns();
		
//		testAll();	
	}
	

	public static String ROOT_DIRECTORY = ".";
	
	public void test(String file)
			throws EugeneException {
		System.out.println("**** TESTING: " + file +" ****");
		try {
			long t1 = System.nanoTime();
			
			// set Eugene's ROOT directory
			Eugene.ROOT_DIRECTORY = ROOT_DIRECTORY;
			
			// instantiate Eugene
			Eugene e = new Eugene();
			
			// execute the file
			EugeneCollection ec = e.executeFile(new File(file));

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
	    File[] list = root.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".eug") || !(name.contains("."));
	        }
	    });

		for ( File f : list ) {
			if ( f.isDirectory() ) {
				testAll( f.getAbsolutePath() );
			} else {
				try {
					this.test(path+"/"+f.getName());
				} catch (Exception ee) {
					throw new EugeneException(ee.getLocalizedMessage());
				}
			}
		}
	}
}
