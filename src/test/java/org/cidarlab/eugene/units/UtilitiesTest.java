package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.TestUtils;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.DeviceUtils;
import org.cidarlab.eugene.util.SequenceUtils;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * The UtilitiesTest unit tests all Utility classes 
 * of Eugene contained in package. That is,
 * org.cidarlab.eugene.utils.*
 * 
 * @author Ernst Oberortner
 */
public class UtilitiesTest {

	@Test
	public void testDeviceUtilsFlip() {

		// the device under test:
		// D(A, B)

		// build the device
		Device d = new Device("D");
		List<NamedElement> loe1 = new ArrayList<NamedElement>();
		loe1.add(new PartType("A"));
		d.getComponents().add(loe1);

		List<Orientation> loo1 = new ArrayList<Orientation>();
		loo1.add(Orientation.FORWARD);
		d.getOrientations().add(loo1);

		List<NamedElement> loe2 = new ArrayList<NamedElement>();
		loe2.add(new PartType("B"));
		d.getComponents().add(loe2);

		List<Orientation> loo2 = new ArrayList<Orientation>();
		loo2.add(Orientation.FORWARD);
		d.getOrientations().add(loo2);

		
		try {
			// flip the device
			Device fd = DeviceUtils.flip(d);
			
			// that's how the flipped device should look like
			String original = "D([+A],[+B])";
			String excpected = "D([+B],[+A])";
			
			assert(original.equals(TestUtils.toTestString(d)));
			assert(excpected.equals(TestUtils.toTestString(fd)));
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	@Test
	public void testDeviceUtilsInvert() {

		// the device under test:
		// D(A, B)

		// build the device
		Device d = new Device("D");
		List<NamedElement> loe1 = new ArrayList<NamedElement>();
		loe1.add(new PartType("A"));
		d.getComponents().add(loe1);

		List<Orientation> loo1 = new ArrayList<Orientation>();
		loo1.add(Orientation.FORWARD);
		d.getOrientations().add(loo1);

		List<NamedElement> loe2 = new ArrayList<NamedElement>();
		loe2.add(new PartType("B"));
		d.getComponents().add(loe2);

		List<Orientation> loo2 = new ArrayList<Orientation>();
		loo2.add(Orientation.FORWARD);
		d.getOrientations().add(loo2);

		
		try {
			// flip the device
			Device fd = DeviceUtils.invert(d);
			
			// that's how the flipped device should look like
			String original = "D([+A],[+B])";
			String excpected = "D([-A],[-B])";
			
			assert(original.equals(TestUtils.toTestString(d)));
			assert(excpected.equals(TestUtils.toTestString(fd)));
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}


	@Test
	public void testDeviceUtilsFlipAndInvert() {

		// the device under test:
		// D(A, B)

		// build the device
		Device d = new Device("D");
		List<NamedElement> loe1 = new ArrayList<NamedElement>();
		loe1.add(new PartType("A"));
		d.getComponents().add(loe1);

		List<Orientation> loo1 = new ArrayList<Orientation>();
		loo1.add(Orientation.FORWARD);
		d.getOrientations().add(loo1);

		List<NamedElement> loe2 = new ArrayList<NamedElement>();
		loe2.add(new PartType("B"));
		d.getComponents().add(loe2);

		List<Orientation> loo2 = new ArrayList<Orientation>();
		loo2.add(Orientation.FORWARD);
		d.getOrientations().add(loo2);

		
		try {
			// flip the device
			Device fd = DeviceUtils.invert(d);
			
			// that's how the flipped device should look like
			String original = "D([+A],[+B])";
			String excpected = "D([-B],[-A])";
			
			assert(original.equals(TestUtils.toTestString(d)));
			assert(excpected.equals(TestUtils.toTestString(fd)));
			
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	@Test
	public void testSequenceUtilsReverseComplement01() {
		String seq = "ATCGATCGATCGATCG";
		
		try {
			String revComp = SequenceUtils.reverseComplement(seq);
			
			assert(revComp.equals("CGATCGATCGATCGAT"));
		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSequenceUtilsReverseComplement02() {
		String seq = "TTTT";
		
		try {
			String revComp = SequenceUtils.reverseComplement(seq);
			
			assert(revComp.equals("AAAA"));
		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}

}
