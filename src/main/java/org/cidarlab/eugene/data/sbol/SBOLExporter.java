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

package org.cidarlab.eugene.data.sbol;

import java.io.File;
import java.io.FileOutputStream;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.data.sbol.mapping.Eugene2SBOL;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.eugene.exception.EugeneException;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;


/**
 * 
 * @author Ernst Oberortner
 */
public class SBOLExporter {

	/**
	 * The toSBOLDocument/1 method serializes a Eugene NamedElement object
	 * into an SBOLDocument object in-memory.
	 * accepted NamedElement objects are: 
	 * - EugeneContainers: EugeneCollection, EugeneArray
	 * - Components: Device, Part
	 * 
	 * @param ne ... the Eugene NamedElement object
	 * @return ... an SBOLDocument object
	 * @throws EugeneException
	 */
	public static SBOLDocument toSBOLDocument(NamedElement ne)
			throws EugeneException {
		
		if(null == ne) {
			throw new EugeneException("Invalid element for SBOL Serialization!");
		}

		// create an empty document populated with some SBOL objects
		SBOLDocument document = SBOLFactory.createDocument();

		try {

			if(ne instanceof EugeneContainer) {
				org.sbolstandard.core.Collection sbolCollection = 
						Eugene2SBOL.convert(
								(EugeneContainer)ne, 
								EugeneConstants.EUGENE_URL);
				
				// add the DnaComponent to this document
				document.addContent(sbolCollection);

			} else if(ne instanceof Component) {
				DnaComponent dnaComponent = 
						Eugene2SBOL.convert((Component)ne, null, 0);

				// add the DnaComponent to this document
				document.addContent(dnaComponent);

			} else {
				throw new EugeneException("Cannot serialize " + ne.getName() + " to SBOL!");
			}
			
			
			return document;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getLocalizedMessage());
		}
	}

	/**
	 * The serialize/2 method converts a Eugene NamedElement to an 
	 * SBOL compliant format and serializes it to a given file.
	 * The NamedElement must be either a EugeneContainer (e.g. Array, Collection)
	 * or a Component (e.g. Device, Part), otherwise an exception 
	 * is thrown.
	 * 
	 * @param ne ... the Eugene NamedElement to be serialized
	 * @param sFileName ... the file name
	 * 
	 * @throws EugeneException
	 */
	public static void serialize(NamedElement ne, String sFileName)
			throws EugeneException {

		/*
		 * ERROR CHECKING
		 */
		if(sFileName == null || sFileName.isEmpty() ) {
			throw new EugeneException("Invalid Filename!"); 
		}		

		if (sFileName.startsWith("\"") && sFileName.endsWith("\"")) {
			sFileName = sFileName.substring(1, sFileName.length() - 2);
		} 
		
		try {

			/*
			 * convert the Eugene NamedElement into an SBOL document
			 */
			SBOLDocument document = toSBOLDocument(ne);

			/*
			 * serialize the document to a file
			 */
			SBOLExporter.serializeSBOL(document, sFileName);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.toString());
		}
	}
	
	/**
	 * The serializeSBOL/2 method persists a given SBOLDocument object 
	 * into a given file.
	 * 
	 * @param document  ... the SBOLDocument object
	 * @param file  ... the desired name of the file (evtl. including path information)
	 * 
	 * @throws EugeneException
	 */
	private static void serializeSBOL(SBOLDocument document, String file) 
			throws EugeneException {

		try {
			// open a file stream
			FileOutputStream fos;
			File f = new File(file);
			if (!f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream(f);
	
			// write the document to the file stream
			// using the SBOLFactory
			SBOLFactory.write(document, fos);
	
			// flush and close the stream
			fos.flush();
			fos.close();
		} catch(Exception e) {
			// if something went wrong, throw an exception
			throw new EugeneException(e.toString());
		}
	}

}
