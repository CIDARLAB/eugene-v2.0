package org.cidarlab.eugene;

import java.io.File;

import org.cidarlab.eugene.exception.EugeneException;

public class TestSuite {

	public static void main(String[] args) 
			throws EugeneException {
		
		/*** TESTS ***/
		/*---------------------------------------------
		 * BACKWARD COMPATIBILITY
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/backward-comp/AllSyntaxExample.eug");
//		new TestSuite().test("./tests/backward-comp/variable-declarations");
//		new TestSuite().test("./tests/backward-comp/hard-expression-test");
//		new TestSuite().test("./tests/backward-comp/complex");    
		
		/*---------------------------------------------
		 * PRIMITIVE DATA TYPES AND MODIFICATIONS
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/primitives/primitives-assign");    
//		new TestSuite().test("./tests/primitives/part-property-assign");    
//		new TestSuite().test("./tests/primitives/array-manip");    
		
		// EXPRESSIONS
//		new TestSuite().test("./tests/expressions/plus-sub-mult-div");    
//		new TestSuite().test("./tests/expressions/loops-and-calculations");    
		
		
		/*---------------------------------------------
		 * BUILT-IN FUNCTIONS AND FEATURES
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/built-in/sizeof");    
		
		/*---------------------------------------------
		 * SEQUENCE SHENANIGANS
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/sequence/reverse-complement");    
		
		/*---------------------------------------------
		 * EUGENE BASICS
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/property-definitions");
//		new TestSuite().test("./tests/part-type-definitions");
//		new TestSuite().test("./tests/part-definitions");
//		new TestSuite().test("./tests/device-definitions");

//		new TestSuite().test("./tests/data-access");

		

		/*---------------------------------------------
		 * EUGENE CONTAINERS
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/containers/collections");
//		new TestSuite().test("./tests/containers/arrays");


		// HIERARCHICAL COMPOSITION
//		new TestSuite().test("./tests/hierarchical-composition");

		// ASSIGNMENTS
//		new TestSuite().test("./tests/assignments");

		// ACCESS statements
//		new TestSuite().test("./tests/data-access");

		// PRINT
//		new TestSuite().test("./tests/print-statements");
		
		// RULES
//		new TestSuite().test("./tests/rules/faulty-rules");
//		new TestSuite().test("./tests/rules/valid-rules");
//		new TestSuite().test("./tests/rules/product-and-rules");
//		new TestSuite().test("./tests/rules/conjunction");
//		new TestSuite().test("./tests/rules/logical-or");
//		new TestSuite().test("./tests/rules/logical-or-02");
//		new TestSuite().test("./tests/rules/indexed-rules");
//		new TestSuite().test("./tests/rules/expression-rules");
//		new TestSuite().test("./tests/rules/expression-rules-02");
//		new TestSuite().test("./tests/rules/expression-rules-arrays-01");
		
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
//		new TestSuite().test("./tests/rules/arrangement/positioning-or");
//		new TestSuite().test("./tests/rules/arrangement/orientation-or");
		
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
//		new TestSuite().test("./tests/rules/expression/expression-rules-01"); 
//		new TestSuite().test("./tests/rules/expression/expression-rules-02");
//		new TestSuite().test("./tests/rules/expression/expression-rules-03");  
//		new TestSuite().test("./tests/rules/expression/expression-rules-04");  
//		new TestSuite().test("./tests/rules/expression/expression-rules-05");  
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
		
		// Operator example of Ellis' paper
//		new TestSuite().test("./designs/literature/ellis-et-al");  		
		
		// 2. Disjunctive Normal Form (DNF)   --- DONE
//		new TestSuite().test("./tests/rules/oriented-tus");
//		new TestSuite().test("./tests/rules/dnf/dnf-oriented-tus");
//		new TestSuite().test("./tests/rules/dnf/dnf-contains");
		
		// 3. Loops
//		new TestSuite().test("./tests/imperative/loops/loops-syntax-demos");   // TH, 07/31
		
		// 4. Demonstrate EugeneLab (localhost)
		
		
		/*---------------------------------------------
		 * DATA CONTAINERS
		 *---------------------------------------------*/

		// COLLECTIONS
//		new TestSuite().test("./tests/containers/collections");
//		new TestSuite().test("./tests/containers/collection-access");
		
		// ARRAYS
//		new TestSuite().test("./tests/containers/arrays");
		
		/*---------------------------------------------
		 * DATA EXCHANGE
		 *---------------------------------------------*/
		// INCLUDE
//		new TestSuite().test("./tests/include/include01");

		// IMPORT
//		new TestSuite().test("./tests/import/import01");
		
		
		// SBOL -- IMPORT
//		new TestSuite().test("./tests/data-exchange/sbol/sbol-import01");
		
		// SBOL -- EXPORT
//		new TestSuite().test("./tests/data-exchange/sbol/sbol-export01");
//		new TestSuite().test("./tests/data-exchange/sbol/sbol-export02");
		
//		new TestSuite().test("./tests/data-exchange/sbol/round-trip");

		// PIGEON
//		new TestSuite().test("./tests/pigeon");
//		new TestSuite().test("./tests/EugeneLab/multistep-rule-evaluation");
//		new TestSuite().test("./tests/EugeneLab/multistep-rule-evaluation02");
//		new TestSuite().test("./tests/EugeneLab/sb2");
		
		
		/*---------------------------------------------
		 * IMPERATIVE LANGUAGE FEATURES
		 *---------------------------------------------*/
		
		// CONDITIONAL BRANCHES
//		new TestSuite().test("./tests/imperative/branches/if");
//		new TestSuite().test("./tests/imperative/branches/if-else");
//		new TestSuite().test("./tests/imperative/branches/if-elseif");
//		new TestSuite().test("./tests/imperative/branches/if-elseif-else");
		
		// LOOPS
//		new TestSuite().test("./tests/imperative/loops/for-loop-01");   // correctness tests
//		new TestSuite().test("./tests/imperative/loops/for-loop-02");   // efficiency tests
//		new TestSuite().test("./tests/imperative/loops/nested-for-loop-01");

		// ITERATORS (FORALL)
		// TODO
		
		/*
		 * REAL ``PART LIBRARY''
		 */
//		new TestSuite().test("./designs/cidar/inverter/inverters");
//		new TestSuite().test("./designs/cidar/ernst/sb2/demo");
//		new TestSuite().test("./designs/cidar/genomatica/genomatica");
		
		
		/*---------------------------------------------------------
		 * EugeneLab examples
		 *---------------------------------------------------------*/
//		new TestSuite().test("./tests/EugeneLab/logical-or-02");
//		new TestSuite().test("./tests/EugeneLab/multistep-rule-evaluation");
//		new TestSuite().test("./tests/EugeneLab/multistep-rule-evaluation02");
//		new TestSuite().test("./tests/EugeneLab/product-and-rules");
//		new TestSuite().test("./tests/EugeneLab/sb2");
		
		
		/*---------------------------------------------
		 * EUGENE GRAMMARS
		 *---------------------------------------------*/
//		new TestSuite().test("./tests/grammars/ind_rep");
		
		
		/*---------------------------------------------------------
		 * DEMOS
		 *---------------------------------------------------------*/
//		new TestSuite().test("./demos/expression-rule");
		
	
		
	}

	public void test(String file) 
			throws EugeneException {
		try {
			long t1 = System.nanoTime();
			
			new Eugene().executeFile(new File(file));

			long tProcessing = System.nanoTime() - t1;
			
			System.out.println("[TestSuite.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	/**	
	public void testAll( String path ) {
		File root = new File( path );
		FilenameFilter filter = new FilenameFilter() {
                @Override 
	        public boolean accept(File directory, String fileName) {
	            return fileName.endsWith(".eug");
	        }
	    };
	    File[] list = root.listFiles(filter);

		for ( File f : list ) {
			if ( f.isDirectory() ) {
				testAll( f.getAbsolutePath() );
			} else {
				System.out.println("**** "+f.getAbsoluteFile()+" ****");
				try {
					EugeneExecutor.execute(
							f.getAbsoluteFile(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	**/
}
