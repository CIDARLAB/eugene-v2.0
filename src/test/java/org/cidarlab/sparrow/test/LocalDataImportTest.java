package org.cidarlab.sparrow.test;

import java.util.Set;

import org.cidarlab.eugene.data.sbol.SBOLImporter;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;

/**
 * Here, we import some SBOL files (see ./test_data/), 
 * insert the data into the Sparrow LMS,
 * and compare the number of inserted facts with the number 
 * of expected facts.
 * 
 * @author Ernst Oberortner
 */
public class LocalDataImportTest {

	private Sparrow sparrow;
	
	public LocalDataImportTest() 
			throws SparrowException {
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new SparrowException("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public void test() 
			throws SparrowException {
		
		if(!this.importSBOL("./test_data/K780001.rdf")) {
			throw new SparrowException("K780001.rdf FAILED!");
		}
		if(!this.importSBOL("./test_data/K780002.rdf")) {
			throw new SparrowException("K780002.rdf FAILED!");
		}
		if(!this.importSBOL("./test_data/K780000.rdf")) {
			throw new SparrowException("K780000.rdf FAILED!");
		}
		if(!this.importSBOL("./test_data/partsLibrary.sbol.xml")) {
			throw new SparrowException("partsLibrary.sbol.xml FAILED!");
		}
		if(!this.importSBOL("./test_data/RBSs.sbol.xml")) {
			throw new SparrowException("RBSs.sbol.xml FAILED!");
		}
		if(!this.importSBOL("./test_data/Terminators.sbol.xml")) {
			throw new SparrowException("Terminators.sbol.xml FAILED!");
		}
		
		// finally, we dump the WM
		if(this.sparrow.getFactCount() != 114) {
			throw new SparrowException("Wrong number of facts! LocalDataImportTest FAILED!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean importSBOL(String file) {
		
		try {
			Object o = SBOLImporter.importSBOL(file);
			if(null != o) {
				if(o instanceof Component) {                 // Part or Device
					sparrow.insertFact((Component)o);
				} else if(o instanceof java.util.HashSet) {  // Collection
					/*
					 * here, we iterate over the collection
					 * inserting each element 
					 */
					Set<Component> components = (java.util.Set<Component>)o;
					if(!components.isEmpty()) {
						for(Component c : components) {
							sparrow.insertFact(c);
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
	
	public static void main(String[] args) 
			throws Exception {
		
		new LocalDataImportTest().test();
	}
}
