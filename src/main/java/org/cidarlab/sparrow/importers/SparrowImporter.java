package org.cidarlab.sparrow.importers;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.sparrow.exception.ImportException;
import org.drools.runtime.StatefulKnowledgeSession;

public abstract class SparrowImporter 
	implements Importer {
	
	protected StatefulKnowledgeSession session;
	
	public SparrowImporter(StatefulKnowledgeSession session) {
		this.session = session;
	}
	
	/**
	 * 
	 */
	public abstract NamedElement importData(String pattern) 
			throws ImportException;

}
