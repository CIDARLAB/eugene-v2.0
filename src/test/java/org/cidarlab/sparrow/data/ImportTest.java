package org.cidarlab.sparrow.data;

import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;

/**
 * Here, we import the SBOL files (see ./data/sbol)
 * and insert them into Sparrow's WM.
 * 
 * and here's the mapping again:
 * SBOL DnaComponent (w/o SequenceAnnotations) --> Sparrow Part
 * SBOL DnaComponent (w/  SequenceAnnotations) --> Sparrow Device
 * SBOL Collection                             --> Sparrow java.util.HashSet 
 * 
 * @author Ernst Oberortner
 */
public class ImportTest {

	private Sparrow sparrow;
	
	public ImportTest() 
			throws Exception {
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new Exception("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public static void main(String[] args) 
			throws Exception {
		
		ImportTest it = new ImportTest();
		
		if(!it.importSBOL("./data/sbol/partregistry/rbs/K780001.rdf")) {
			throw new Exception("FAILED!");
		}
		if(!it.importSBOL("./data/sbol/partregistry/rbs/K780002.rdf")) {
			throw new Exception("FAILED!");
		}
		if(!it.importSBOL("./data/sbol/partregistry/terminator/K780000.rdf")) {
			throw new Exception("FAILED!");
		}
		if(!it.importSBOL("./data/sbol/partregistry/partsLibrary.sbol")) {
			throw new Exception("FAILED!");
		}
		if(!it.importSBOL("./data/sbol/bacillobricks/RBSs.sbol.xml")) {
			throw new Exception("FAILED!");
		}
		if(!it.importSBOL("./data/sbol/bacillobricks/Terminators.sbol.xml")) {
			throw new Exception("FAILED!");
		}
		
		// finally, we dump the WM
		it.printComponents();
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
	
	public void printComponents() {
		System.out.println(this.sparrow.getFactCount());
		this.sparrow.printFacts();
	}

}
