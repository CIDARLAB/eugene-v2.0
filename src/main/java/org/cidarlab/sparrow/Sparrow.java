package org.cidarlab.sparrow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.ComponentType;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.interaction.Interaction.InteractionType;
import org.cidarlab.sparrow.constants.Repository;
import org.cidarlab.sparrow.constants.Standard;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.rule.EugeneRule;
import org.cidarlab.sparrow.rule.SparrowQuery;
import org.cidarlab.sparrow.rule.SparrowRule;
import org.cidarlab.sparrow.rule.compiler.Eugene2Drools;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.AssertBehaviorOption;
import org.drools.io.ResourceFactory;
import org.drools.marshalling.Marshaller;
import org.drools.marshalling.MarshallerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.drools.template.ObjectDataCompiler;

public class Sparrow 
		implements ISparrow {

	private KnowledgeBuilder kbuilder = null;
	private KnowledgeBase kbase = null;
	private StatefulKnowledgeSession ksession = null;
	private String sessionId = null;
	
	public Sparrow() 
			throws SparrowException {
		
		this.startUp();
		this.sessionId = java.util.UUID.randomUUID().toString();
		
	}
	
	public Sparrow(String sessionId) 
			throws SparrowException {
		
		this.startUp();
		this.sessionId = sessionId;

		/*
		 * we check if the session exists already
		 */
		try {
			this.resume(sessionId);
		} catch(SparrowException spe) {
			// if the session does not exist already,
			// then we just proceed as normal
		}
		
	}
	
	private void startUp() 
			throws SparrowException {
		
		/*
		 * we don't want duplicates in the working memory
		 */
		KnowledgeBaseConfiguration kbaseConfig = 
				KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		kbaseConfig.setOption(AssertBehaviorOption.EQUALITY);
				
		/*
		 * KnowledgeBuilder
		 */
		this.kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		// parse and compile the predefined queries
		this.kbuilder.add( 
				ResourceFactory.newClassPathResource( "predefined-queries.drl", getClass() ), 
				ResourceType.DRL );

		/*
		 * KnowledgeBase
		 */
		this.kbase = KnowledgeBaseFactory.newKnowledgeBase(kbaseConfig);
		this.kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );	
		
		/*
		 * create a new Session
		 */
		this.ksession = this.kbase.newStatefulKnowledgeSession();

	}
	
	/**
	 * The getSessionID method returns the ID of the current session.
	 * 
	 * @return the ID of the current session
	 */
	public String getSessionID() {
		return this.sessionId;
	}
	
	/**
	 * The dumpWorking method queries all components 
	 * from the current working memory and prints them 
	 * onto the console (using System.out.println).
	 */
	public void printFacts() {
		if(null != this.ksession) {
			Collection<Object> facts = this.ksession.getObjects();
			for(Object obj : facts) {
				System.out.println(obj);
			}
		}
	}

	/**
	 * The getFactCount method returns the number of 
	 * facts in the current sessions's knowledge base 
	 */
	public long getFactCount() {
		return this.ksession.getFactCount();
	}


	/*---------------------------------------------------------------
	 * QUERIES
	 *---------------------------------------------------------------*/
	
	/**
	 * The queryPromoters method fetches all Parts whose 
	 * type=="Promoter".
	 * 
	 * @return all promoters in the current WM
	 * @throws SparrowException
	 */
	public List<Part> query(PartType pt) 
			throws SparrowException {
		
		QueryResults results = ksession.getQueryResults( "get all", new Object[] {  pt.getName() } );
		System.out.println( "there are " + results.size() + " "+pt.getName()+"s" );
		
		return null;
	}
	

	/**
	 * 
	 * @param rule  ... a Eugene rule
	 * @return a list of Components that complies with the given rule
	 * @throws SparrowException
	 */
	public List<Component> query(EugeneRule rule) 
			throws SparrowException {
		
		List<EugeneRule> rules = new ArrayList<EugeneRule>();
		rules.add(rule);
		return this.query(rules);
		
	}
	
	public Set<Component> query(SparrowQuery query) 
			throws SparrowException {
		
		if(null == query) {
			throw new SparrowException("Invalid query!");
		}
		
		Collection<SparrowQuery> queries = new HashSet<SparrowQuery>();
		queries.add(query);
		return this.query(queries);
	}
	
	/**
	 * 
	 * @param queries
	 * @return
	 */	
	public Set<Component> query(Collection<SparrowQuery> queries) 
			throws SparrowException {

		ObjectDataCompiler converter = new ObjectDataCompiler();
		InputStream templateStream = 
				Sparrow.class.getClassLoader().getResourceAsStream("query-template.drt");

		String drl = converter.compile( queries, templateStream );
//		System.out.println(drl);
		
		/*
		 * next, we open a new session to query all data 
		 * that complies to the given rules
		 */
		Reader rdr = new StringReader( drl );
		kbuilder.add( ResourceFactory.newReaderResource( rdr ), ResourceType.DRL );
		if( kbuilder.hasErrors() ){
//			System.out.println(kbuilder.getErrors());
		    throw new IllegalStateException( "DRL errors: " + kbuilder.getErrors());
		}
		this.ksession.getKnowledgeBase().addKnowledgePackages( kbuilder.getKnowledgePackages() );			
		
		
		// finally, we union all the query results
		// -> not very memory efficient though
		Set<Component> results = new HashSet<Component>();
		for(SparrowQuery query : queries) {
			QueryResults qrs = ksession.getQueryResults( query.getName() );
			
			if(null != qrs && qrs.size() > 0) {
				for(QueryResultsRow row : qrs) {
					results.add((Component)row.get("$ret"));
				}
			}
		}
		
		return results;
	}
	
	/**
	 * @param rules
	 * @return
	 * @throws SparrowException
	 */
	public List<Component> query(List<EugeneRule> rules) 
			throws SparrowException {
		
		/*
		 * first, we need to compile the Eugene rules into Drools rules...
		 */
		Collection<Eugene2Drools> col = new ArrayList<Eugene2Drools>();
		
//		String ruleName = null;
		List<String> ruleNames = new ArrayList<String>();
		for(EugeneRule rule : rules) {
//			ruleName = rule.getName();
			ruleNames.add(rule.getName());
			col.add(new Eugene2Drools(rule));
		}

		ObjectDataCompiler converter = new ObjectDataCompiler();
		InputStream templateStream = 
				Sparrow.class.getClassLoader().getResourceAsStream("rule-template.drt");

		String drl = converter.compile( col, templateStream );
//		System.out.println(drl);
		
		/*
		 * next, we open a new session to query all data 
		 * that complies to the given rules
		 */
		Reader rdr = new StringReader( drl );
		kbuilder.add( ResourceFactory.newReaderResource( rdr ), ResourceType.DRL );
		if( kbuilder.hasErrors() ){
		    throw new IllegalStateException( "DRL errors" );
		}
		this.ksession.getKnowledgeBase().addKnowledgePackages( kbuilder.getKnowledgePackages() );			
		
		
		// finally, we union all the query results
		// -> not very memory efficient though
		List<Component> results = new ArrayList<Component>();
		for(String ruleName : ruleNames) {
			QueryResults qrs = ksession.getQueryResults( ruleName );
			
			if(null != qrs && qrs.size() > 0) {
				for(QueryResultsRow row : qrs) {
					results.add((Component)row.get("$p"));
				}
			}
		}
		
		return results;
	}
	
	public void persist() 
			throws SparrowException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Marshaller marshaller = MarshallerFactory.newMarshaller(kbase);
		try {
			FileOutputStream foStream = new FileOutputStream(
					new File("./sessions/"+this.getSessionID()));
			marshaller.marshall(baos, this.ksession);
			baos.writeTo(foStream);
			baos.close();
		} catch(Exception e) {
			throw new SparrowException(e.getMessage());
		}
	}

	/**
	 * 
	 */
	public void resume(String sessionId) 
			throws SparrowException {
		
		try {
		
			FileInputStream fileInputStream =
					new FileInputStream("./sessions/" + this.getSessionID());
	
			Marshaller marshaller = MarshallerFactory.newMarshaller(kbase);	
			this.ksession = marshaller.unmarshall(fileInputStream);
		} catch(Exception e) {
			throw new SparrowException(e.getMessage());
		}
	}

	
	/***********
	 * IMPORT AND INSERT OF FACTS
	 ***********/

	public void importFrom(String name, final Repository repository)
			throws SparrowException {
		repository.importData(this.ksession, name);
	}

	public void importFrom(File file, Standard standard)
			throws SparrowException {
		// TODO Auto-generated method stub
		
	}

	public void importFrom(File file) throws SparrowException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * The insert method inserts a Named Element object into 
	 * the working memory.
	 * 
	 * @param element ... the element to be inserted
	 */
	public void insertFact(NamedElement element) 
			throws SparrowException {
		if(null == element) {
			throw new SparrowException(element + " is an invalid working memory element!");
		}

		this.ksession.insert(element);
	}

//	/**
//	 * 
//	 * The insert method inserts a Component object into 
//	 * the working memory.
//	 * 
//	 * @param component ... the Component object that should be inserted
//	 */
//	public void insertFact(Component component) 
//			throws SparrowException {
//		if(null != component) {
//			ksession.insert(component);
//		}
//	}

	
//	public void exportTo(List<Component> lst, final Standard standard, String filename)
//			throws SparrowException {
//		if(null != standard) {
//			standard.exportData(lst, filename);
//		}
//	}

	public NamedElement getFact(String name) 
				throws SparrowException {
		
		// first, we query the WM for the fact
		QueryResults qr = 
				this.ksession.getQueryResults("get", name);
		
		if(null != qr && qr.size() == 1) {		
			// if the fact exists, then we return it
			return (NamedElement)
					qr.iterator().next().get("element");
		}
		
		// if the fact does not exist, then we return NULL
		return null;
	}

	public boolean exists(String name) 
				throws SparrowException {
		return null!=this.getFact(name) ? true : false; 
	}

	public boolean contains(String name) 
			throws SparrowException {
		return null!=this.getFact(name) ? true : false; 
	}
	
	public void fireRules(String ruleFileName) 
			throws SparrowException {
		
		// for the time, we only add the Drools Rule file...
		this.kbuilder.add( 
				ResourceFactory.newClassPathResource( ruleFileName, getClass() ), 
				ResourceType.DRL );
		
		// add the knowledge to the current session
		this.ksession.getKnowledgeBase().addKnowledgePackages( kbuilder.getKnowledgePackages() );
		
		// NEEDS TESTING
		this.ksession.fireAllRules();
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.cidarlab.sparrow.ISparrow#query(java.lang.String, java.lang.Object[])
	 */
	public Set<Object> query(String queryName, Object[] queryParams)
			throws SparrowException {
		Set<Object> soo = null;

		QueryResults results = ksession.getQueryResults( queryName, queryParams );
		if(results != null) {
			soo = new HashSet<Object>(results.size());
			for ( QueryResultsRow row : results ) {
				soo.add(( Object ) row.get( "$ret" )); 
			}
		}
		
		return soo;
	}

	public Set<Device> prune(SparrowRule rule) 
			throws SparrowException {

		Collection<Eugene2Drools> col = new ArrayList<Eugene2Drools>();
		col.add(new Eugene2Drools(rule));

		ObjectDataCompiler converter = new ObjectDataCompiler();
		InputStream templateStream = 
				Sparrow.class.getClassLoader().getResourceAsStream("rule-template-new.drt");

		String drl = converter.compile( col, templateStream );
//		System.out.println(drl);
		
		/*
		 * next, we open a new session to query all data 
		 * that complies to the given rules
		 */
		Reader rdr = new StringReader( drl );
		kbuilder.add( ResourceFactory.newReaderResource( rdr ), ResourceType.DRL );
		if( kbuilder.hasErrors() ){
		    throw new IllegalStateException( "DRL errors: " + kbuilder.getErrors());
		}
		try {
			this.ksession.getKnowledgeBase().addKnowledgePackages( kbuilder.getKnowledgePackages() );
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * initialization of global list that keeps results
		 */
		this.ksession.setGlobal("results", new HashSet<Device>());
		
		this.ksession.fireAllRules();

		return (Set<Device>)this.ksession.getGlobal("results");
	}

	public void exportTo(List<Component> lst, Standard standard, String filename)
			throws SparrowException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.cidarlab.sparrow.ISparrow#getFacts()
	 */
	public Set<Component> getFacts() 
			throws SparrowException {
		return this.executePredefinedQuery("getAllComponents", new Object[] {});
	}

	public Set<Component> getParts() 
			throws SparrowException {
		return this.executePredefinedQuery("getAllParts", new Object[] {});
	}

	public Set<Component> getComponents(ComponentType ct)
			throws SparrowException {
		return this.executePredefinedQuery("getComponents", new Object[] {ct});
	}

	public Set<Component> getParts(PartType pt) 
			throws SparrowException {
		return this.executePredefinedQuery("getParts", new Object[] {pt});
	}
	

	private Set<Component> executePredefinedQuery(String q, Object[] aoo)
		throws SparrowException {
		/*
		 * here, we execute a predefined query, which we 
		 * defined in predefined-queries.drl and loaded at 
		 * the instantiation of Sparrow (see Sparrow constructor)
		 */
		Set<Component> soc = null;
		QueryResults results = ksession.getQueryResults( q, aoo );
		
		/*
		 * then, we process the query results 
		 * i.e. putting them into a set
		 */
		if(results != null) {
			soc = new HashSet<Component>(results.size());
			for ( QueryResultsRow row : results ) {
				if(row.get("$c") instanceof Part) {
					soc.add((Part)row.get( "$c" )); 
				} else if(row.get("$c") instanceof Component) {
					soc.add((Component)row.get( "$c" )); 
				}
			}
		}
		
		/*
		 * and finally, we return the set
		 */
		return soc;
	}
	
	public Set<Interaction> getInteractions() 
			throws SparrowException {
		/*
		 * here, we execute a predefined query, which we 
		 * defined in predefined-queries.drl and loaded at 
		 * the instantiation of Sparrow (see Sparrow constructor)
		 */
		Set<Interaction> soc = null;
		QueryResults results = ksession.getQueryResults( "getAllInteractions", new Object[] {} );
		
		/*
		 * then, we process the query results 
		 * i.e. putting them into a set
		 */
		if(results != null) {
			soc = new HashSet<Interaction>(results.size());
			for ( QueryResultsRow row : results ) {
				if(row.get("$i") instanceof Interaction) {
					soc.add((Interaction)row.get( "$i" )); 
				}
			}
		}
		
		/*
		 * and finally, we return the set
		 */
		return soc;
	}


	public Set<Interaction> getInteractions(InteractionType it) 
			throws SparrowException {
		/*
		 * here, we execute a predefined query, which we 
		 * defined in predefined-queries.drl and loaded at 
		 * the instantiation of Sparrow (see Sparrow constructor)
		 */
		Set<Interaction> soc = null;
		QueryResults results = ksession.getQueryResults( "getInteractions", new Object[] {it} );
		
		/*
		 * then, we process the query results 
		 * i.e. putting them into a set
		 */
		if(results != null) {
			soc = new HashSet<Interaction>(results.size());
			for ( QueryResultsRow row : results ) {
				if(row.get("$i") instanceof Interaction) {
					soc.add((Interaction)row.get( "$i" )); 
				}
			}
		}
		
		/*
		 * and finally, we return the set
		 */
		return soc;
	}

	public void clear() 
			throws SparrowException {
		
		if(null != this.ksession) {
			this.ksession.dispose();
			this.ksession = null;
		}
		
		this.ksession = this.kbase.newStatefulKnowledgeSession();
	}
	
}
