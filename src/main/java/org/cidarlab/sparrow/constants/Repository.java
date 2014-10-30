package org.cidarlab.sparrow.constants;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.RegistryImporter;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * we use ``polymorphic method dispatch''
 * 
 * @author Ernst Oberortner
 */
public enum Repository {
	iGEM {
		@Override
		public NamedElement importData(StatefulKnowledgeSession session, String name) 
				throws SparrowException {
			try {
				return new RegistryImporter(session).importData(name);
			} catch(Exception e) {
				throw new SparrowException(e.getMessage());
			}
		}
	},
	Clotho3 {   // WHEN???
		@Override
		public NamedElement importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	};

	/**
	 * 
	 * @param name ... 
	 * 
	 * @throws SparrowException
	 */
	public abstract NamedElement importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException;
}


/**** BACKUP ****

	JBEI_ICE {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	}
	
	
	SynBERC {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	}


	virtualparts {
		@Override
		public NamedElement importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	}
	
	SBPKb {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
 **********/