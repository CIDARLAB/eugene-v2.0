package org.cidarlab.sparrow.importers;

import java.net.URL;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.sparrow.exception.ImportException;
import org.drools.runtime.StatefulKnowledgeSession;

public class RegistryImporter 
		extends SparrowImporter 
		implements Importer {

	private static final String URL_PREFIX = "http://convert.sbols.org/biobrick/";
	
	public RegistryImporter(StatefulKnowledgeSession session) {
		super(session);
	}

	@Override
	public void importData(String pattern) 
			throws ImportException {
		try {
			Component c = (Component)SBOLImporter.importSBOL(new URL(URL_PREFIX+pattern));
			if(null != c) {
				System.out.println(c.getName()+" -> "+c.getClass());
				
				if(c instanceof Device) {
					importDevice((Device)c);
				} else if(c instanceof Part) {
					this.session.insert(c);
				}
			} else {
				throw new ImportException("Cannot import "+pattern+" from partsregistry! "+URL_PREFIX+pattern);
			}
		} catch(Exception e) {
			throw new ImportException(e.getMessage());
		}
	}

	
	private void importDevice(Device d) {
		
	}
}
