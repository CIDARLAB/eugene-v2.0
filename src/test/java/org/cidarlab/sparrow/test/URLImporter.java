package org.cidarlab.sparrow.test;

import java.net.URL;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.ImportException;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;

/**
 * 
 * and here's the mapping again:
 * SBOL DnaComponent (w/o SequenceAnnotations) --> Sparrow Part
 * SBOL DnaComponent (w/  SequenceAnnotations) --> Sparrow Device
 * SBOL Collection                             --> Sparrow java.util.HashSet 
 * 
 * @author Ernst Oberortner
 */
public class URLImporter {

	private Sparrow sparrow;
	private static final String URL_PREFIX = "http://convert.sbols.org/biobrick/";
	
	public URLImporter() 
			throws SparrowException {
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new SparrowException(se.getMessage());
		}
	}
	
	public void test() 
			throws SparrowException, ImportException {
		
		String[] parts = {"BBa_K780001", "BBa_K780002", "BBa_K780003", "BBa_I0500"};
		
		for(String part : parts) {
			if(!this.importSBOL(part)) {
				throw new ImportException("import of "+part+" failed.");
			}
		}
		
		
		// compare the test results w/ the expected results
		if(4 != this.sparrow.getFactCount()) {
			throw new SparrowException("[URLImporter.test] FAILED!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean importSBOL(String part) {
		
		try {
			Object o = SBOLImporter.importSBOL(new URL(URL_PREFIX+part));
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
