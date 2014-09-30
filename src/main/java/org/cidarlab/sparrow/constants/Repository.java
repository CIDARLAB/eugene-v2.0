package org.cidarlab.sparrow.constants;

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
		public void importData(StatefulKnowledgeSession session, String name) 
				throws SparrowException {
			try {
				new RegistryImporter(session).importData(name);
			} catch(Exception e) {
				throw new SparrowException(e.getMessage());
			}
		}
	}, 
	JBEI_ICE {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	},
	SynBERC {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	},
	virtualparts {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	},
	Clotho3 {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	},
	SBPKb {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
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
	public abstract void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException;
}
