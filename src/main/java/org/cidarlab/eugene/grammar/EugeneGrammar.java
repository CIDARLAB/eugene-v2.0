package org.cidarlab.eugene.grammar;

import java.util.List;
import java.util.ArrayList;

import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneGrammar {

	/**
	 * The isComposed/1 method checks if a given Device d 
	 * is hierarchically composed
	 * 
	 * @param d  ... the Device to be checked
	 * 
	 * @return  true ... the Device is hierarchically composed
	 *         false ... otherwise
	 */
	public boolean isComposed(Device d) {
		if(null != d && null != d.getComponents() && !d.getComponents().isEmpty()) {
			for(List<NamedElement> loe : d.getComponents()) {
				if(null != loe && !loe.isEmpty()) {
					for(NamedElement e : loe) {
						if(e instanceof Device) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public List<Device> enumerateAll(Device d) 
			throws EugeneException {
		
		List<Device> devices = new ArrayList<Device>();
		
		return devices;
	}
	

	private void enumerate(Device d) {
		
	}
}
