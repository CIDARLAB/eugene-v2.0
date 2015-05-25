/* Copyright (c) 2015, Boston University
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list 
 * of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without specific prior 
 * written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.data.sbol;

import java.io.File;
import java.io.FileOutputStream;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.data.sbol.mapping.Eugene2SBOL;
import org.cidarlab.eugene.dom.ComponentType;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.FileUtils;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;


/**
 * The SBOLExporter class provides methods to serialize Eugene objects 
 * into an SBOL-compliant file format.
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

			// EugeneContainer
			// either EugeneArray or EugeneCollection
			if(ne instanceof EugeneContainer) {

				org.sbolstandard.core.Collection sbolCollection = 
						Eugene2SBOL.convert(
								(EugeneContainer)ne, 
								EugeneConstants.EUGENE_URL);

				// add the DnaComponent to this document
				document.addContent(sbolCollection);

			// Eugene Component
			// either primitive (Part) or composite (Device)
			} else if(ne instanceof Component) {
				DnaComponent dnaComponent = 
						Eugene2SBOL.convert((Component)ne, null, 0);

				// add the DnaComponent to this document
				document.addContent(dnaComponent);

			// Eugene ComponentType
			// in Eugene v2.0 --> only PartType
			} else if(ne instanceof ComponentType) {

				DnaComponent dnaComponent = 
						Eugene2SBOL.convert((ComponentType)ne, null, 0);

				// add the DnaComponent to this document
				document.addContent(dnaComponent);
				
			// Invalid
			// e.g. Eugene Variable or Eugene PropertyValue
			} else {
				throw new EugeneException("Cannot serialize " + ne.getName() + " to SBOL!");
			}
			
			return document;
			
		} catch(Exception e) {
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
//			e.printStackTrace();
			throw new EugeneException(e.getLocalizedMessage());
		}
	}
	
	/**
	 * The serializeSBOL/2 method persists a given SBOLDocument object 
	 * into a given file. 
	 * The path of the file starts at the Eugene ROOT_DIRECTORY, which is 
	 * set to '.' per default.
	 * 
	 * @param document  ... the SBOLDocument object
	 * @param file  ... the desired name of the file (evtl. including path information)
	 * 
	 * @throws EugeneException
	 */
	private static void serializeSBOL(SBOLDocument document, String file) 
			throws EugeneException {

			if(file.contains("/")) {
				// creating the directories
				FileUtils.createDirectories(file);
			}
			
			// creating the file
			File f = new File(file);
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch(Exception e) {
					throw new EugeneException(e.getMessage());
				}
			}
			
		try (
				// open a file stream
				FileOutputStream fos = new FileOutputStream(f);
				
			) {
				
				
			// write the SBOLDocument in-memory object onto 
			// the file stream using the SBOLFactory
			SBOLFactory.write(document, fos);
	
			// flush and close the stream
			fos.flush();
			
		} catch(Exception e) {
			// if something went wrong, throw an exception
			throw new EugeneException(e.getMessage());
		}
	}
	
 
}
