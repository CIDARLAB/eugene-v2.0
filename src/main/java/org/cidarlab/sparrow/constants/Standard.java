/**
 * 
 */
package org.cidarlab.sparrow.constants;

import java.util.List;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.exporters.SBOLExporter;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * @author Ernst Oberortner
 *
 */
public enum Standard {
	SBOL {
		@Override
		public void exportData(StatefulKnowledgeSession session, String filename)
				throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}

		@Override
		public void exportData(List<Component> lst, String filename)
				throws SparrowException {
			try {
				SBOLExporter.serialize(lst, filename);
			} catch(Exception e) {
				throw new SparrowException(e.getMessage());
			}
		}
	},	
	
	Genbank {
		@Override
		public void exportData(StatefulKnowledgeSession session, String filename)
				throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}

		@Override
		public void exportData(List<Component> lst, String filename)
				throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	};
	
	/**
	 * 
	 */
	public abstract void exportData(StatefulKnowledgeSession session, String filename) 
			throws SparrowException;
	
	/**
	 * 
	 */
	public abstract void exportData(List<Component> lst, String filename) 
			throws SparrowException;
}
