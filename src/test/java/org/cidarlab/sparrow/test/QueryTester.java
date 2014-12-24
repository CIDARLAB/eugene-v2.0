package org.cidarlab.sparrow.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.rule.EugeneRule;
import org.cidarlab.sparrow.rule.Predicate;

public class QueryTester 
	implements SparrowTest {

	private Sparrow sparrow;
	private final int NR_OF_PARTS = 1;
	
	public QueryTester() 
			throws SparrowException {
		
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new SparrowException("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
		
	}
	
	public void test() 
			throws SparrowException {
		try {
			// first, let's define and insert some data
			this.insertData();
			
			// second, let's query the data
			this.queryData();
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new SparrowException(e.getMessage());
		}
	}
	
	private void insertData() 
			throws Exception {
		
		// properties
		Property txt = new Property("txt", SparrowConstants.TXT);
		Property num = new Property("num", SparrowConstants.NUM);		
		Property numList = new Property("numList", SparrowConstants.NUMLIST);		
		Property txtList = new Property("txtList", SparrowConstants.TXTLIST);		
		Property bool = new Property("bool", SparrowConstants.BOOLEAN);		

		// the part type
		PartType pt = new PartType("PartType");
		
		// add properties to the part type
		pt.getProperties().add(txt);
		pt.getProperties().add(num);
		pt.getProperties().add(numList);
		pt.getProperties().add(txtList);
		pt.getProperties().add(bool);
		
		// instantiate the part type N times
		// and set its property values
		for(int i=1; i<=NR_OF_PARTS; i++) {
			Part p = new Part("part"+i, pt);
			
			// TXT VALUE
			PropertyValue pvTxt = new PropertyValue(txt);
			if(i % 2 == 0) {
				pvTxt.setTxt("ATCG");
			} else {
				pvTxt.setTxt("GCTA");
			}
			p.setPropertyValue(txt, pvTxt);
						
			// NUM VALUE
			PropertyValue pvNum = new PropertyValue(num);
			pvNum.setNum(i);
			p.setPropertyValue(num, pvNum);

			
			// NUM[]
			PropertyValue pvNumList = new PropertyValue(numList);
			for(int k=0; k<NR_OF_PARTS; k++) {
				pvNumList.getNumList().add(new Double(randomNumber()));
			}
			p.setPropertyValue(numList, pvNumList);
			
			
			// TXT[]
			PropertyValue pvTxtList = new PropertyValue(txtList);
			for(int k=0; k<i; k++) {
				pvTxtList.getTxtList().add("ATCG");
			}
			p.setPropertyValue(txtList, pvTxtList);
			
			
			// BOOL
			PropertyValue boolVal = new PropertyValue(bool);
			if(i%2 == 0) {
				boolVal.setBool(true);
			} else {
				boolVal.setBool(false);
			}
			p.setPropertyValue(bool, boolVal);
			
			this.sparrow.insertFact(p);
		}
	}
	
	private void queryData() 
			throws SparrowException {
		
//		this.queryNumList();
		
		this.conjunctions();
//		this.disjunctions();
		
	}
	
	private void queryNumList() 
			throws SparrowException {
		
		List<EugeneRule> rules = new ArrayList<EugeneRule>();
		
		EugeneRule numListRule = new EugeneRule("numList");
		numListRule.getPredicates().add(new Predicate(new Property("numList", SparrowConstants.NUMLIST), "[0] == ", "0"));
		rules.add(numListRule);
		
		List<Component> lst = this.sparrow.query(rules);
		if(lst.size() != NR_OF_PARTS) {
			throw new SparrowException("queryNumList FAILED");
		}
	}
	

	/*
	 * CONJUNCTIONS
	 */
	private void conjunctions() 
			throws SparrowException {

		this.conjunction_txt();
		this.conjunction_txt_bool();
		
		// TODO:
		// - more tests
	}
	
	private void conjunction_txt() 
			throws SparrowException {
		
		// txt == "ATCG" /\ txt == "GCTA"  
		EugeneRule rule1 = new EugeneRule();
		rule1.getPredicates().add(new Predicate(new Property("txt", SparrowConstants.TXT), " == ", "ATCG"));
		rule1.getPredicates().add(new Predicate(new Property("txt", SparrowConstants.TXT), " == ", "GCTA"));

		List<Component> lst = this.sparrow.query(rule1);

		if(lst.size() != 0) {
			throw new SparrowException("conjunction_txt FAILED");
		}
		
	}


	private void conjunction_txt_bool() 
			throws SparrowException {
		
		// txt == "ATCG" /\ bool == true  
		EugeneRule rule = new EugeneRule();
		rule.getPredicates().add(new Predicate(new Property("txt", SparrowConstants.TXT), " == ", "ATCG"));
		rule.getPredicates().add(new Predicate(new Property("bool", SparrowConstants.BOOLEAN), " == ", "true"));

		List<Component> lst = this.sparrow.query(rule);

		if(lst.size() != NR_OF_PARTS / 2) {
			throw new SparrowException("conjunction_txt_bool FAILED");
		}
		
	}

//	/*
//	 * DISJUNCTIONS
//	 */
//	private void disjunctions() 
//			throws SparrowException {
//
//		this.query_txt();
//		
//		// TODO:
//		// - more tests
//	}
//	
//	private void query_txt() 
//			throws SparrowException {
//		
//		List<EugeneRule> rules = new ArrayList<EugeneRule>();
//
//		// txt == "ATCG" \/ txt == "GCTA"  
//		EugeneRule rule1 = new EugeneRule();
//		rule1.getPredicates().add(new Predicate(new Property("txt", SparrowConstants.TXT), " == ", "GCTA"));
//		rules.add(rule1);
//		
////		EugeneRule rule2 = new EugeneRule();
////		rule2.getPredicates().add(new Predicate(new Property("txt", SparrowConstants.TXT), " == ", "GCTA"));
////		rules.add(rule2);
//
//		System.out.println(this.sparrow.getFacts());
//		List<Component> lst = this.sparrow.query(rules);
//
//		System.out.println(lst);
//		if(lst.size() != NR_OF_PARTS) {
//			throw new SparrowException("disjunction_txt FAILED");
//		}
//		
//	}
	
	/*
	 * HELPER METHODS
	 */
	private double randomNumber() {
		return new Random(1).nextDouble();
	}

}
