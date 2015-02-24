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
