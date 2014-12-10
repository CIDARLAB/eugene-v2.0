package org.cidarlab.eugene.util;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

import com.rits.cloning.Cloner;

/**
 * The DeviceUtils class provides a set of static methods 
 * for the interpretation of Eugene Devices. 
 * The methods are:
 * - flip 
 * - inverse  
 * 
 * @author Ernst Oberortner
 *
 */
public class DeviceUtils {
	
	/**
	 * The flipAndInvert(Device) method takes as input a device, reverses the 
	 * order of its components (``flip'') and inverts the components' orientation.
	 * 
	 * @param d  ... the device to be flipped and inverted
	 * 
	 * @return a new device with reverse ordered components and inverted orientations
	 * 
	 * @throws EugeneException
	 */
	public static Device flipAndInvert(Device d) 
			throws EugeneException {
		
		try {
			return flip(invert(d));
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
	}
	
	public static Device invert(Device d) 
			throws EugeneException {
		
		if(null == d.getComponents() || d.getComponents().isEmpty()) {
			return d;
		}
		
		// create a new device that has the 
		// same name as the given device
		Device fd = new Device(d.getName());
		
		List<Orientation> loo = null;	
		List<NamedElement> loe = null;
		
		int size = d.getComponents().size();
		for(int i=0; i<size; i++) {

			// copy the elements
			loe = d.getComponents().get(i);
			fd.getComponents().add(loe);
			
			try {

				// orientations
				loo = new ArrayList<Orientation>(d.getOrientations(i));
			
				for(int j=0; j<loo.size(); j++) {
					if(loo.get(j) == Orientation.FORWARD) {
						loo.set(j, Orientation.REVERSE);
					} else {
						loo.set(j, Orientation.FORWARD);
					}
				}
				
				fd.getOrientations().add(loo);
			} catch(EugeneException ee) {
				throw new EugeneException(ee.getMessage());
			}
			
		}
		
		
		return fd;
	}
	
	/**
	 * The flip(Device) method reverses the elements in a given 
	 * device w/o changing the elements' orientation. 
	 * The flip(Device) method returns a duplicate object 
	 * containing the original device's elements in reverse
	 * order.
	 * 
	 * Example:
	 * Device D(+a, +b, +c); --> Device D(+c, +b, +a);
	 * Device D(-c, -b, -a); --> Device D(-a, -b, -c); 
	 * 
	 * @param d  ... the device whose elements must be reverse
	 * 
	 * @return
	 */
	public static Device flip(Device d) 
			throws EugeneException {
		
		if(null == d.getComponents() || d.getComponents().isEmpty()) {
			return d;
		}
		
		Device fd = new Device(d.getName());		
		int size = d.getComponents().size();
		for(int i=size-1; i>=0; i--) {
			
				// components
				fd.getComponents().add(new ArrayList<NamedElement>(d.getComponents().get(i)));
				
				// orientations
				fd.getOrientations().add(new ArrayList<Orientation>(d.getOrientations().get(i)));
		}
		
		return fd;
	}

}
