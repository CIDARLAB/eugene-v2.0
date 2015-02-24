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

package org.cidarlab.eugene.util;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

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
