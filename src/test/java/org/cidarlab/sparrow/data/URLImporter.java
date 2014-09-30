package org.cidarlab.sparrow.data;

import java.net.URI;
import java.net.URL;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
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
			throws Exception {
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new Exception("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public static void main(String[] args) 
			throws Exception {
		
		URLImporter it = new URLImporter();
		
		String[] parts = {"BBa_K780001", "BBa_K780002", "BBa_K780003", "BBa_I0500", "BBa_T9002"};
		
		for(String part : parts) {
			if(!it.importSBOL(part)) {
				throw new Exception("import of "+part+" failed.");
			}
		}
				
		// finally, we dump the WM
		it.printComponents();
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
	
	public void printComponents() {
		System.out.println(this.sparrow.getFactCount());
		this.sparrow.printFacts();
	}

}
