package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.data.sbol.SBOLExporter;
import org.cidarlab.eugene.data.sbol.SBOLImporter;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

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
	public void testSBOLExport_Device() {
		// first we create a device
		PartType pt = new PartType("PT");
		
		// TODO!
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

}
