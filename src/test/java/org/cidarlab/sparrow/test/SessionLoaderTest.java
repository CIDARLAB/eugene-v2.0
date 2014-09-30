package org.cidarlab.sparrow.test;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;
import org.cidarlab.sparrow.rule.EugeneRule;
import org.cidarlab.sparrow.rule.Predicate;

/**
 * 
 * @author Ernst Oberortner
 */
public class SessionLoaderTest 
		implements SparrowTest {

	private Sparrow sparrow;
	private String sessionId;
	private long factCount;
	
	public SessionLoaderTest(String sessionId, long factCount) 
			throws SparrowException {
		
		this.sessionId = sessionId;
		this.factCount = factCount;
		
		try {
			this.sparrow = new Sparrow(sessionId);
		} catch(SparrowException se) {
			throw new SparrowException("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	
	public void test() 
			throws SparrowException {
		
		// resume the session
		// i.e. let Sparrow load it
		this.resume();

		// now, compare the two sizes
		if(this.sparrow.getFactCount() != this.factCount) {
			throw new SparrowException("The number of facts does not match!");
		}
	}
	
	private void resume() 
			throws SparrowException {
		this.sparrow.resume("./sessions/"+this.sessionId);
	}
	
	public void printComponents() {
		System.out.println(this.sparrow.getFactCount());
		this.sparrow.printFacts();
	}
	
	public void queryAllPromoters() {
		/*
		 * let's get all promoters
		 */
		try {
			this.sparrow.query(new PartType("Promoter"));
		} catch(Exception e) {
			e.printStackTrace();
		}
//		EugeneRule eugeneRule = new EugeneRule("demo");
//		// type == "Promoter"
//		eugeneRule.getPredicates().add(new Predicate(new Property("type", SparrowConstants.TXT), "==", "Promoter"));
//		
//		List<EugeneRule> rules = new ArrayList<EugeneRule>();
//		rules.add(eugeneRule);
//		try {
//			this.sparrow.query(rules);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(this.sparrow.size());
	}

}
