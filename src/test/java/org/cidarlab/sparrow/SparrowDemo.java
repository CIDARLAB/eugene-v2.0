package org.cidarlab.sparrow;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.DOMException;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.rule.EugeneRule;
import org.cidarlab.sparrow.rule.Predicate;

public class SparrowDemo {

	private static final int NR_OF_PARTS = 10;
	
	private Sparrow sparrow;
	
	public SparrowDemo() 
			throws SparrowException {
		this.sparrow = new Sparrow();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
			throws Exception {
		SparrowDemo m = new SparrowDemo();
		
		/*
		 * first, let's populate the working memory (WM) 
		 * with components
		 * (currently only basic parts)
		 */
		m.insertComponents();
		
		System.gc();
		
		/*
		 * let's query all parts
		 * 
		 */
		m.queryAll();
		
		System.gc();

		/*
		 * then, let's create some Eugene Rules
		 * 
		 * We want to build a conjunction of several predicates.
		 * Therefore, we put all predicates into the EugeneRule object
		 * 
		 */
		m.conjunctionExample();
		
		System.gc();
		
		/*
		 * If we want to have a DISJUNCTION, then 
		 * we have to instantiate the EugeneRule class for every predicate.
		 * (see other example) 
		 */
		// DISJUNCTION
		m.disjunctionExample();
		
	}
	
	/**
	 * 
	 */
	private void queryAll() {
		List<EugeneRule> rules = new ArrayList<EugeneRule>();
		EugeneRule eugeneRule = new EugeneRule("demo");
		rules.add(eugeneRule);
		
		try {
			List<Component> lst = this.sparrow.query(rules);
			System.out.println(lst);
			//this.sparrow.exportTo(lst, Standard.SBOL, "./exports/sbol/all.sbol.xml");
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * type=="Promoter" /\ sequence=="ATCG" /\ strength>5
	 * 
	 * @return
	 */
	private void conjunctionExample() {
		List<EugeneRule> rules = new ArrayList<EugeneRule>();

		EugeneRule eugeneRule = new EugeneRule("demo");
		// type == "Promoter"
		eugeneRule.getPredicates().add(new Predicate(new Property("type", SparrowConstants.TXT), "==", "Promoter"));
		// sequence == "ATCG"
		eugeneRule.getPredicates().add(new Predicate(new Property("sequence", SparrowConstants.TXT), "==", "GCTA"));
		// strength > 5
		eugeneRule.getPredicates().add(new Predicate(new Property("strength", SparrowConstants.NUM), ">=", "1"));
		
		rules.add(eugeneRule);
		
		try {
			List<Component> lst = this.sparrow.query(rules);
			System.out.println(lst);

//			this.sparrow.exportTo(lst, Standard.SBOL, "./exports/sbol/conjunction.sbol.xml");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * type=="Promoter" \/ type=="RBS"
	 * 
	 * @return
	 */
	private void disjunctionExample() {
		List<EugeneRule> rules = new ArrayList<EugeneRule>();
				
		// type == "Promoter"
		EugeneRule promoterRule = new EugeneRule();
		promoterRule.getPredicates().add(new Predicate(new Property("type", SparrowConstants.TXT), "==", "Promoter"));
		rules.add(promoterRule);
		
		// some more types
		EugeneRule rbsRule = new EugeneRule("rbs_type");
		rbsRule.getPredicates().add(new Predicate(new Property("type", SparrowConstants.TXT), "==", "RBS"));
		rules.add(rbsRule);
		
		try {
			List<Component> lst = this.sparrow.query(rules);
			System.out.println(lst);

//			this.sparrow.exportTo(lst, Standard.SBOL, "./exports/sbol/disjunction.sbol.xml");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/*
	private String templating(List<Rule> eugeneRules) {
		
		Collection<Eugene2Drools> rules = new ArrayList<Eugene2Drools>();
		for(Rule rule : eugeneRules) {
			rules.add(new Eugene2Drools(rule));
		}

		ObjectDataCompiler converter = new ObjectDataCompiler();
		InputStream templateStream = 
				RuleTemplateExample.class.getClassLoader().getResourceAsStream("template.drt");

		return converter.compile( rules, templateStream );
	}
	 */

	/*
	private void evaluate(String drl) {
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();

		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		Reader rdr = new StringReader( drl );

		kBuilder.add( ResourceFactory.newReaderResource( rdr ), ResourceType.DRL );

		if( kBuilder.hasErrors() ){
		    throw new IllegalStateException( "DRL errors" );
		}

		kBase.addKnowledgePackages( kBuilder.getKnowledgePackages() );	
		
		StatefulKnowledgeSession session = kBase.newStatefulKnowledgeSession();
		
		insertFacts(session);
		
		session.fireAllRules();
	}
	*/
	
	
	private void insertComponents() 
			throws SparrowException {
		try {
			insertPromoter();
			insertRBS();
			insertCDS();
			insertTerminator();
		} catch(DOMException de) {
			throw new SparrowException(de.toString());
		}
	}

	private void insertPromoter() 
			throws DOMException, SparrowException {
		
		// here, we create a lot of parts
		Property sequence = new Property("sequence", SparrowConstants.TXT);
		Property strength = new Property("strength", SparrowConstants.NUM);		
		PartType promoter = new PartType("Promoter");
		promoter.getProperties().add(sequence);
		promoter.getProperties().add(strength);
		
		for(int i=1; i<=NR_OF_PARTS; i++) {
			Part p = new Part("promoter"+i, promoter);
			
			// the promoter's sequence 
			PropertyValue pvSequence = new PropertyValue(sequence);
			if(i % 2 == 0) {
				pvSequence.setTxt("ATCG");
			} else {
				pvSequence.setTxt("GCTA");
			}
			p.setPropertyValue(sequence, pvSequence);
			
			
			// the promoter's strength
			PropertyValue pvStrength = new PropertyValue(strength);
			pvStrength.setNum(i);
			p.setPropertyValue(strength, pvStrength);

			this.sparrow.insertFact(p);
		}
	}
	
	private void insertRBS() 
			throws DOMException, SparrowException {
		
		// here, we create a lot of parts
		Property sequence = new Property("sequence", SparrowConstants.TXT);
		PartType rbs = new PartType("RBS");
		rbs.getProperties().add(sequence);
		
		for(int i=1; i<=NR_OF_PARTS; i++) {
			Part p = new Part("rbs"+i, rbs);
			
			// the promoter's sequence 
			PropertyValue pvSequence = new PropertyValue(sequence);
			if(i % 2 == 0) {
				pvSequence.setTxt("ATCG");
			} else {
				pvSequence.setTxt("GCTA");
			}
			p.setPropertyValue(sequence, pvSequence);
			
			
			this.sparrow.insertFact(p);
		}
	}
	
	private void insertCDS() 
			throws DOMException, SparrowException {
		
		// here, we create a lot of parts
		Property sequence = new Property("sequence", SparrowConstants.TXT);
		PartType cds = new PartType("CDS");
		cds.getProperties().add(sequence);
		
		for(int i=1; i<=NR_OF_PARTS; i++) {
			Part p = new Part("cds"+i, cds);
			
			// the promoter's sequence 
			PropertyValue pvSequence = new PropertyValue(sequence);
			if(i % 2 == 0) {
				pvSequence.setTxt("ATCG");
			} else {
				pvSequence.setTxt("GCTA");
			}
			p.setPropertyValue(sequence, pvSequence);
			
			
			this.sparrow.insertFact(p);
		}
	}
	
	private void insertTerminator() 
			throws DOMException, SparrowException {
		
		// here, we create a lot of parts
		Property sequence = new Property("sequence", SparrowConstants.TXT);
		PartType terminator = new PartType("Terminator");
		terminator.getProperties().add(sequence);
		
		for(int i=1; i<=NR_OF_PARTS; i++) {
			Part p = new Part("terminator"+i, terminator);
			
			// the promoter's sequence 
			PropertyValue pvSequence = new PropertyValue(sequence);
			if(i % 2 == 0) {
				pvSequence.setTxt("ATCG");
			} else {
				pvSequence.setTxt("GCTA");
			}
			p.setPropertyValue(sequence, pvSequence);
			
			this.sparrow.insertFact(p);
		}
	}
}
