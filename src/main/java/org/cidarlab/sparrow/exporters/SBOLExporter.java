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

package org.cidarlab.sparrow.exporters;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.cidarlab.eugene.dom.*;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.exporters.mapping.Sparrow2SBOL;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;


/**
 * 
 * @author Ernst Oberortner
 */
public class SBOLExporter {
	public static void serialize(List<Component> objArray, String sFileName)
			throws SparrowException {
		if (sFileName.startsWith("\"") && sFileName.endsWith("\"")) {
			sFileName = sFileName.substring(1, sFileName.length() - 2);
		}

		try {
			org.sbolstandard.core.Collection sbolCollection = 
					Sparrow2SBOL.convert(objArray, SparrowConstants.SPARROW_URL);

			// create an empty document populated with some SBOL objects
			SBOLDocument document = SBOLFactory.createDocument();

			// add the DnaComponent to this document
			document.addContent(sbolCollection);

			FileOutputStream fos;
			File f = new File(sFileName);
//			System.out.println(sFileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream(f);

			SBOLFactory.write(document, fos);

			fos.flush();
			fos.close();
			fos = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SparrowException(e.getMessage());
		}
	}

//	public static boolean serialize(Device objDevice, String sFileName) {
//		try {
//			FileOutputStream fos;
//			DnaComponent dnaComponent = 
//					Sparrow2SBOL.convert(objDevice, null);
//			// create an empty document populated with some SBOL objects
//			SBOLDocument document = SBOLFactory.createDocument();
//			// add the DnaComponent to this document
//			document.addContent(dnaComponent);
//
//			fos = new FileOutputStream(new File(sFileName));
//			SBOLFactory.write(document, fos);
//		} catch (Exception e) {
//			System.err.println(e.toString());
//		}
//
//		return true;
//	}

//	public static boolean serialize(EugeneCollection objCollection, String sFileName) 
//			throws EugeneException {
//		try {
//			FileOutputStream fos;
//			
//			// create an empty document populated with the SBOL objects
//			// from the given collection
//			SBOLDocument document = SBOLFactory.createDocument();
//
//			// add the DnaComponent to this document
//			document.addContent(Sparrow2SBOL.convert(objCollection, EugeneConstants.EUGENE_URL));
//
//			fos = new FileOutputStream(new File(sFileName));
//			SBOLFactory.write(document, fos);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new EugeneException(e.getMessage());
//		}
//
//		return true;
//	}

}
