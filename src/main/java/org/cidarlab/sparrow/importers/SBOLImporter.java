/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.sparrow.importers;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

import org.cidarlab.eugene.data.sbol.mapping.SBOL2Eugene;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.sparrow.exception.ImportException;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SBOLRootObject;


/**
 * 
 * @author Ernst Oberortner
 */
public class SBOLImporter {

	public static Object importSBOL(URL url) 
			throws ImportException {
	
		if(null == url || url.toString().isEmpty()) {
			throw new ImportException("Invalid URL!");
		}		

		try {
			SBOLDocument document = SBOLFactory.read(url.openStream());
			return parseDocument(document);
		} catch(Exception e) {
			throw new ImportException(e.getMessage());
		}
		
	}
	
	public static Object importSBOL(String sFileName)
			throws ImportException {
		/*
		 * an SBOLDocument object keeps the SBOL File's information in memory
		 */
		SBOLDocument document;
		
		try {
			/*
			 * read the SBOL File into memory
			 */
			document = SBOLFactory.read(
					new FileInputStream(sFileName));

			return parseDocument(document);

		} catch (Exception e) {
			throw new ImportException(e.toString());
		}

	}
	
	private static Object parseDocument(SBOLDocument document) 
			throws EugeneException {
		
		if (null != document && null != document.getContents() && 
			!document.getContents().isEmpty()) {

			if(document.getContents().size() > 1) {
				/*
				 * Collection
				 */
				Collection<Component> components = new HashSet<Component>();

				// first, create the SBOL properties
				for (SBOLRootObject sbolObj : document.getContents()) {
					components.add((Component)SBOL2Eugene.convert(sbolObj));
				}
				
				return components;
				
			} else {
				
				/*
				 * Component (Part or Device)
				 */
				Object element = SBOL2Eugene.convert(document.getContents().get(0));

				if (null != element && element instanceof Component) {
					return element;
				}
			}
		}
		
		return null;
	}
}
