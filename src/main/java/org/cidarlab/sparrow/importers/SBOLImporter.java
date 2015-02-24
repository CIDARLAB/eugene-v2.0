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
