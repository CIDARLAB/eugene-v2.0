package org.cidarlab.sparrow.test;

import java.net.URI;
import java.net.URL;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;

/**
 * 
 * @author Ernst Oberortner
 */
public class SessionPersistorTest
		implements SparrowTest {

	private Sparrow sparrow;
	private static final String URL_PREFIX = "http://convert.sbols.org/biobrick/";
	
	public SessionPersistorTest() 
			throws SparrowException {
		
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new SparrowException(
					"Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public String getSessionId() {
		return this.sparrow.getSessionID();
	}
	
	public long getFactCount() {
		return this.sparrow.getFactCount();
	}
	
	public void test() 
			throws SparrowException {
		
		// first, we populate the session with ``real'' data
		// imported from the iGEM partsregistry
		this.doBulkImport();
		this.persist();
	}
	
	private void doBulkImport() {
		// importing the JCA constitutive promoters
		for(int i=0; i<=10; i++) {
			String part = "BBa_J231";
			if(i<10) {
				part += "0"+i;
			} else {
				part += i;
			}
			this.importSBOL(part);
		}
		
//		// import the USTC logic promoters
//		// BBa_I732200 ... BBa_I732452
//		for(int i=200; i<=452; i++) {
//			String part = "BBa_I732"+i;
//			this.importSBOL(part);
//		}
		
		// finally, we dump the WM
		//this.printComponents();
	}
	
	private void persist() {
		try {
			this.sparrow.persist();
		} catch(SparrowException se) {
			se.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean importSBOL(String part) {
		
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
			//e.printStackTrace();
		}
		return false;
	}
	
	private void insert(Component c) 
			throws SparrowException {
		this.sparrow.insertFact(c);
	}
	
}
