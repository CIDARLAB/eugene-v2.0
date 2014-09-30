package org.cidarlab.sparrow.test;

import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;

/**
 * @author Ernst Oberortner
 */
public class DuplicateTest 
		implements SparrowTest {

	private Sparrow sparrow;
	
	public DuplicateTest() 
			throws Exception {
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new Exception("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public void test()
			throws SparrowException {
		
		if(!this.importSBOL("./data/sbol/partregistry/rbs/K780001.rdf")) {
			throw new SparrowException("FAILED!");
		}
		if(!this.importSBOL("./data/sbol/partregistry/rbs/K780001.rdf")) {
			throw new SparrowException("FAILED!");
		}
				
		// finally, we dump the WM
		if(this.sparrow.getFactCount() != 1) {
			throw new SparrowException("DuplicateTest FAILED!");
		}
	}
	
	private boolean importSBOL(String file) {
		
		try {
			Object o = SBOLImporter.importSBOL(file);
			if(null != o) {
				if(o instanceof Component) {                 // Part or Device
					this.sparrow.insertFact((Component)o);
				} else if(o instanceof java.util.HashSet) {  // Collection
					/*
					 * here, we iterate over the collection
					 * inserting each element 
					 */
					Set<Component> components = (java.util.Set<Component>)o;
					if(!components.isEmpty()) {
						for(Component c : components) {
							this.sparrow.insertFact(c);
						}
					}
					
				}
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
