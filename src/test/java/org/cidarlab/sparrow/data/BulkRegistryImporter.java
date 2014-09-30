package org.cidarlab.sparrow.data;

import java.net.URL;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;

/**
 * Here, we import facts directly from the iGEM partsregistry
 * using the SBOL converter ``service'' available at
 * http://convert.sbols.org/biobrick/
 *
 * 
 * and here's the mapping again:
 * SBOL DnaComponent (w/o SequenceAnnotations) --> Sparrow Part
 * SBOL DnaComponent (w/  SequenceAnnotations) --> Sparrow Device
 * SBOL Collection                             --> Sparrow java.util.HashSet 
 * 
 * @author Ernst Oberortner
 */
public class BulkRegistryImporter {

	private Sparrow sparrow;
	private static final String URL_PREFIX = "http://convert.sbols.org/biobrick/";
	
	public BulkRegistryImporter() 
			throws Exception {
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new Exception("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public static void main(String[] args) 
			throws Exception {
		
		BulkRegistryImporter it = new BulkRegistryImporter();

		// importing the JCA constitutive promoters
		for(int i=0; i<=99; i++) {
			String part = "BBa_J231";
			if(i<10) {
				part += "0"+i;
			} else {
				part += i;
			}
			
			it.importSBOL(part);
		}
				
		
		// import the USTC logic promoters
		// BBa_I732200 ... BBa_I732452
		for(int i=200; i<=452; i++) {
			String part = "BBa_I732"+i;
			
			it.importSBOL(part);
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
					this.insert((Component)o);
				} else if(o instanceof java.util.HashSet) {  // Collection
					/*
					 * here, we iterate over the collection
					 * inserting each element 
					 */
					Set<Component> components = (java.util.Set<Component>)o;
					if(!components.isEmpty()) {
						for(Component c : components) {
							this.insert(c);
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
	
	private void insert(Component c) 
			throws SparrowException {
		System.out.println("inserting "+c+" => "+c.hashCode());
		sparrow.insertFact(c);
	}
	
	public void printComponents() {
		System.out.println(this.sparrow.getFactCount());
		this.sparrow.printFacts();
	}

}
