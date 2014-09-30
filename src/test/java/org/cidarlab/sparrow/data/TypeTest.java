package org.cidarlab.sparrow.data;

import org.cidarlab.sparrow.importers.SBOLImporter;

/**
 * Here, we import the SBOL files (see ./data/sbol)
 * and compare the type of the imported object with 
 * the expected type.
 * 
 * and here's the mapping again:
 * SBOL DnaComponent (w/o SequenceAnnotations) --> Sparrow Part
 * SBOL DnaComponent (w/  SequenceAnnotations) --> Sparrow Device
 * SBOL Collection                             --> Sparrow java.util.HashSet 
 * 
 * @author Ernst Oberortner
 */
public class TypeTest {

	public static void main(String[] args) 
			throws Exception {
		
		TypeTest it = new TypeTest();
		
		if(!it.test("./data/sbol/partregistry/rbs/K780001.rdf", org.cidarlab.eugene.dom.Part.class)) {
			throw new Exception("FAILED!");
		}
		if(!it.test("./data/sbol/partregistry/rbs/K780002.rdf", org.cidarlab.eugene.dom.Part.class)) {
			throw new Exception("FAILED!");
		}
		if(!it.test("./data/sbol/partregistry/terminator/K780000.rdf", org.cidarlab.eugene.dom.Part.class)) {
			throw new Exception("FAILED!");
		}
		if(!it.test("./data/sbol/partregistry/partsLibrary.sbol", java.util.HashSet.class)) {
			throw new Exception("FAILED!");
		}
		if(!it.test("./data/sbol/bacillobricks/RBSs.sbol.xml", java.util.HashSet.class)) {
			throw new Exception("FAILED!");
		}
		if(!it.test("./data/sbol/bacillobricks/Terminators.sbol.xml", java.util.HashSet.class)) {
			throw new Exception("FAILED!");
		}
	}
	
	public boolean test(String file, Class c) {
		
		try {
			Object o = SBOLImporter.importSBOL(file);
			System.out.println(o.getClass());
			return o.getClass().equals(c);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
