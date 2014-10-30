package org.cidarlab.sparrow;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.interaction.Interaction.InteractionType;
import org.cidarlab.sparrow.constants.Repository;
import org.cidarlab.sparrow.constants.Standard;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.rule.SparrowQuery;
import org.cidarlab.sparrow.rule.SparrowRule;

public interface ISparrow {

	/**
	 * 
	 * @return the id of the current session (as String)
	 */
	public String getSessionID();

	/**
	 * 
	 * @return the number of facts in the knowledge base
	 */
	public long getFactCount();
	
	/**
	 * printFacts prints all facts of the current session's knowledge base
	 * to the console
	 */
	public void printFacts();
	
	/*
	 * START AND RESUME SPARROW
	 */

	/**
	 * the persist method persists the current session.
	 * 
	 * @throws SparrowException
	 */
	public void persist() 
		throws SparrowException;
	
	/**
	 * 
	 * @throws SparrowException
	 */
	public void resume(String sessionId) 
		throws SparrowException; 
	
	
	/*
	 * CRUD INTERFACES FOR IMPORTING AND INSERTING ``FACTS''
	 */
	/**
	 * Sparrow supports the following repositories:
	 * iGEM, JBEI_ICE, SynBERC, virtualparts, Clotho3, SBPKb
	 *  
	 * @param repository   ... the name of the repository
	 *                         use the org.cidarlab.sparrow.constants.Repository enum
	 * @param name         ... the name of the ``thing'' 
	 * @throws SparrowException
	 */
	public NamedElement importFrom(String name, Repository repository) 
		throws SparrowException;

	/**
	 * Sparrow supports the following data exchange standards:
	 * SBOL, Genbank
	 * 
	 * If the given file does not contain data compliant with the specified standard,
	 * then a SparrowException will be thrown.
	 * 
	 * @param file   ... the file from that the data should be imported
	 *                   use the org.cidarlab.sparrow.constants.Standard enum
	 *                         
	 * @throws SparrowException
	 */
	public NamedElement importFrom(File file, Standard standard)
		throws SparrowException;
	
	/**
	 * here, Sparrow will try to figure out the standard by itself. 
	 * If the given file does not contain data according to the provided standards,
	 * then a SparrowException will be thrown.
	 * 
	 * Sparrow supports the following data exchange standards:
	 * SBOL, Genbank
	 * 
	 * @param file   ... the file from that the data should be imported
	 *                   use the org.cidarlab.sparrow.constants.Standard enum
	 *                         
	 * @throws SparrowException
	 */
	public NamedElement importFrom(File file)
		throws SparrowException;

//	/**
//	 * 
//	 * @param e ... the fact that should be inserted
//	 *              into the knowledge base
//	 * @throws SparrowException
//	 */
//	public void insertFact(NamedElement e)
//		throws SparrowException;

	/**
	 * 
	 * @param element ... the element (i.e. ``fact'') that should be inserted
	 *                    into the knowledge base working memory
	 * @throws SparrowException
	 */
	public void insertFact(NamedElement element)
		throws SparrowException;

	/**
	 * the exportTo/3 method exports the provided list of components to 
	 * the specified standard. the data gets serialized into the file 
	 * specified by the filename parameter.
	 * 
	 * @param lst      ... the list of components
	 * @param standard ... the data exchange standard
	 * @param filename ... the name of the file 
	 * @throws SparrowException
	 */
	public void exportTo(List<Component> lst, Standard standard, String filename)
		throws SparrowException;
	
	
	/**
	 * The exists/1 method inspects the current working memory for the 
	 * required fact name and returns true if the requested fact exists.
	 * 
	 * @param name ... the name of the fact
	 * @return
	 * @throws SparrowException
	 */
	public boolean exists(String name)
		throws SparrowException;
	
	/**
	 * The contains/1 method inspects the current working memory for the 
	 * required fact name and returns true if the requested fact exists.
	 * 
	 * @param name ... the name of the fact
	 * @return
	 * @throws SparrowException
	 */
	public boolean contains(String name)
		throws SparrowException;

	/**
	 * The getFact/1 method returns the NamedElement object with the given 
	 * name "name" from the working memory
	 *  
	 * @param name ... the name of the fact
	 * @return
	 * @throws SparrowException
	 */
	public NamedElement getFact(String name) 
			throws SparrowException;

	/**
	 * The getFacts/0 method returns all components in the
	 * current working memory.
	 *  
	 * @return ... a collection of components
	 * 
	 * @throws SparrowException
	 */
	public Set<Component> getFacts() 
			throws SparrowException;
	
	/**
	 * The getParts/0 method queries all parts and 
	 * returns them as a collection.
	 * 
	 * @return  a collection of all parts
	 *  
	 * @throws SparrowException
	 */
	public Set<Component> getParts()
			throws SparrowException;

	/**
	 * The getComponents/1 method queries all components of a given 
	 * type and returns them as a collection.
	 * 
	 * @param ct  ... the type of the desired components
	 * @return  a collection of components of the given type
	 * @throws SparrowException
	 */
	public Set<Component> getComponentsOf(ComponentType ct)
			throws SparrowException;

	/**
	 * The getParts/1 method queries all parts of a given 
	 * PartType and returns the parts as a collection.
	 * 
	 * @param pt  ... the part type of the desired parts
	 * @return  a collection of parts of the given part type
	 * @throws SparrowException
	 */
	public Set<Component> getParts(PartType pt)
			throws SparrowException;
	
	/**
	 * The getInteractions/0 method retrieves all specified 
	 * interactions from the working memory and returns them 
	 * as a set.
	 * 
	 * @return a set of interactions
	 * 
	 * @throws SparrowException
	 */
	public Set<Interaction> getInteractions() 
			throws SparrowException;
	

	/**
	 * The getInteractions/1 method retrieves all interactions
	 * from the working memory of the given type. It returns 
	 * the interactions as a set.
	 * 
	 * @param it  ... the type of the interaction
	 * @return  a set of interactions of the given type
	 * @throws SparrowException
	 */
	public Set<Interaction> getInteractions(InteractionType it) 
			throws SparrowException;

	/**
	 * 
	 * the fireRules/1 method fires the rules specified in the 
	 * provide Drools Rule file on the facts in the current WM
	 * 
	 * @param ruleFileName
	 */
	public void fireRules(String ruleFileName)
			throws SparrowException;

	/**
	 * The query/2 method gets as input the name of a query and
	 * executes it
	 * The query must be loaded earlier in order to be executed.
	 * The second parameter is a list of parameters (Object[]) that
	 * the query requires
	 * 
	 * @param queryName   ... the name of the query
	 * @param queryParams ... the required parameters of the query
	 * @return the query results
	 * @throws SparrowException
	 */
	public Set<Object> query(String queryName, Object[] queryParams) 
			throws SparrowException;

	/**
	 * The query/1 method executes a provided Sparrow query and 
	 * returns a list of components that comply with the query.
	 *  
	 * @param query
	 * @return a list of components
	 * @throws SparrowException
	 */
	public Set<Component> query(SparrowQuery query) 
			throws SparrowException;
	
	/**
	 * 
	 * @param queries
	 * @return
	 */	
	public Set<Component> query(Collection<SparrowQuery> queries) 
			throws SparrowException;
	
	/**
	 * The prune/1 method takes as input a sparrow rule (for the rule template),
	 * prunes the current working memory, and returns a list of resulting components
	 * 
	 * @param rule
	 * @return
	 * @throws SparrowException
	 */
	public Set<Device> prune(SparrowRule rule)
			throws SparrowException;

	/**
	 * The clear/0 method deletes all facts contained in 
	 * the working memory (WM).
	 * 
	 * @throws SparrowException
	 */
	public void clear() 
			throws SparrowException;
}
