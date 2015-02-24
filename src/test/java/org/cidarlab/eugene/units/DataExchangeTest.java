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

package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.data.sbol.SBOLExporter;
import org.cidarlab.eugene.data.sbol.SBOLImporter;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.Set;

/**
 * The DataExchangeTest class provides unit tests for Eugene v2.0's 
 * data exchange facilities.
 * 
 * Test-Strategy for Exports:
 * --------------------------
 * create in-memory objects, serialize them, read them in againg, 
 * and compare their values. If the values match, then test passed.
 * Example: 
 * A Eugene Part object is being serialized into SBOL. Then, we import
 * the SBOL file which should only contain the Part object. 
 * If so, then we compare the values of the two Part objects. 
 * 
 * @author Ernst Oberortner
 *
 */
public class DataExchangeTest {

	@Test
	public void testSBOLExport_PartWithSequence() {
		PartType pt = new PartType("PT");
		Part p1 = new Part(pt, "p1");
		p1.setSequence("ATCG");
		
		try {
			SBOLExporter.serialize(p1, "./exports/tests/testSBOLExport_PartWithSequence.sbol.xml");
			
			Set<NamedElement> set = SBOLImporter.importSBOL("./exports/tests/testSBOLExport_PartWithSequence.sbol.xml");
			assert(set != null);
			assert(set.size() == 1);
			Iterator<NamedElement> it = set.iterator();
			
			NamedElement e = it.next();
			assert(e instanceof Part);
			assert("p1".equals(((Part)e).getName()));
			assert("ATCG".equalsIgnoreCase(((Part)e).getSequence()));
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
	}

	@Test
	public void testSBOLExport_PartWithoutSequence() {
		PartType pt = new PartType("PT");
		Part p1 = new Part(pt, "p1");
//		p1.setSequence("ATCG");
		
		try {
			SBOLExporter.serialize(p1, "./exports/tests/testSBOLExport_PartWithoutSequence.sbol.xml");
			
			Set<NamedElement> set = SBOLImporter.importSBOL("./exports/tests/testSBOLExport_PartWithoutSequence.sbol.xml");
			assert(set != null);
			assert(set.size() == 1);
			Iterator<NamedElement> it = set.iterator();
			
			NamedElement e = it.next();
			assert(e instanceof Part);
			assert("p1".equals(((Part)e).getName()));
			assert(null == ((Part)e).getSequence());
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
	}

	@Test
	public void testSBOLExport_PartType() {
		PartType pt = new PartType("PT");
		
		try {
			SBOLExporter.serialize(pt, "./exports/tests/testSBOLExport_PartType.sbol.xml");
			
			Set<NamedElement> set = SBOLImporter.importSBOL("./exports/tests/testSBOLExport_PartType.sbol.xml");
			assert(set != null);
			assert(set.size() == 1);
			Iterator<NamedElement> it = set.iterator();
			
			
			NamedElement e = it.next();
			
			// hm... a PartType is being converted into a Part...			
			assert(e instanceof Part);
			assert("PT".equals(((Part)e).getName()));
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
	}
	
	@Test
	public void testSBOLExport_enumeratedDevice() {
		String script = "PartType PT();" +
				"PT p1; PT p2; PT p3; PT p4;" +
				"Device D(PT);" +
				"result = product(D);";
		try {
			EugeneCollection ec = new Eugene().executeScript(script);
			
			NamedElement result = ec.get("result");
			
			assert(null != result);
			assert(result instanceof EugeneArray);
			assert(((EugeneArray)result).getElements().size() == 8);
			
			// export to SBOL
			SBOLExporter.serialize(result, 
					"./exports/tests/testSBOLExport_enumeratedDevice.sbol.xml");

			// import from SBOL
			Set<NamedElement> impResult = SBOLImporter.importSBOL(
					"./exports/tests/testSBOLExport_enumeratedDevice.sbol.xml");
			
			// compare both the impResult and result
			assert(null != impResult);
			Iterator<NamedElement> it = impResult.iterator();
			assert(it.hasNext());
			NamedElement impArray = it.next();
			assert(impArray.getName().equals(result.getName()));
			
		} catch(EugeneException ee) {
			// something's wrong --> Test not passed.
			assertTrue(false);
		}
	}
	
	@Test
	public void testSBOLVisual_Device_with_filename() {
		String script = "PartType PT();" +
				"PT p1; PT p2; PT p3; PT p4;" +
				"Device D(PT);" +
				"result = product(D);" +
				"SBOL.visualize(result, \"./tests/results/data-exchange/testSBOLVisual_Device.png\");";
		try {
			new Eugene().executeScript(script);
			
			// after executing the Eugene script, 
			// the file must exists
			File f = new File("./tests/results/data-exchange/testSBOLVisual_Device.png");
			assert(f.exists());
			
		} catch(Exception ee) {
			ee.printStackTrace();
			assertTrue(false);
		}		
	}

	@Test
	public void testSBOLVisual_Device_without_filename() {
		String script = "PartType PT();" +
				"PT p1; PT p2; PT p3; PT p4;" +
				"Device D(PT);" +
				"result = product(D);" +
				"SBOL.visualize(result);";
		try {
			Eugene e = new Eugene();
			
			// the name of the image is randomly generated.
			// hence, we first get the number of .png files 
			// in the default IMAGE_DIRECTORY
			int NR_OF_BEFORE_IMAGES = this.getNrOfImages(); 
			
			e.executeScript(script);
			
			
			// after executing the Eugene script, 
			// there must be one more image
			// in the IMAGES_DIRECTORY
			assert(this.getNrOfImages() == NR_OF_BEFORE_IMAGES + 1); 
			
		} catch(Exception ee) {
			ee.printStackTrace();
			assertTrue(false);
		}		
	}
	
	private int getNrOfImages() {
		File dir = new File(Eugene.ROOT_DIRECTORY+"/"+Eugene.IMAGES_DIRECTORY+"/");
		File[] files = dir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".png");
		    }
		});
		return files.length;
	}
}
