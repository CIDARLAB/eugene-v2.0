
package org.cidarlab.eugene.data.sbol;

import java.io.FileInputStream;
import java.util.Set;
import java.util.HashSet;

import org.cidarlab.eugene.data.sbol.mapping.SBOL2Eugene;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SBOLRootObject;


/**
 * 
 * @author Ernst Oberortner
 */
public class SBOLImporter {

	/**
	 * The importSBOL/1 method reads the given file and returns a 
	 * set of NamedElements. 
	 * We return a set since an SBOL file can contain of multiple ``root'' objects 
	 * which can be Collections or (primitive or composite) DnaComponents.
	 * 
	 * @param sFileName  ... the name of the SBOL file
	 * @return  a set of Eugene NamedElements
	 * 
	 * @throws EugeneException
	 */
	public static Set<NamedElement> importSBOL(String sFileName)
			throws EugeneException {
		/*
		 * an SBOLDocument object keeps the SBOL File's information in memory
		 */
		SBOLDocument newDocument;
		
		Set<NamedElement> elements = new HashSet<NamedElement>();
		
		try {
			/*
			 * read the SBOL File into memory
			 */
			newDocument = SBOLFactory.read(
					new FileInputStream(sFileName));

			if (null != newDocument && 
					null != newDocument.getContents() && 
					!newDocument.getContents().isEmpty()) {

				/*
				 * we iterate over the elements in the SBOL document.
				 * an element can be either a Collection or DnaComponent
				 */
				for(SBOLRootObject ro : newDocument.getContents()) {
					
					NamedElement element = SBOL2Eugene.convert(ro);
					if(null != element) {
						elements.add(element);
					}
				}					
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
		
		return elements;
	}
}
