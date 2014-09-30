/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.eugene.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.DOMException;
import org.cidarlab.sparrow.exception.SparrowException;

public class SparrowBuilder {

	public static Property buildProperty(
			String sName, String sType) {
		return new Property(sName, sType);
	}
	
	public static PropertyValue buildPropertyValue(
			String name, String type, String value) 
				throws SparrowException {
		return buildPropertyValue(
				new PropertyValue(name, type), value);		
	}
	
	public static PropertyValue buildPropertyValue(
			Property property, String value) 
				throws SparrowException {
		PropertyValue pv = new PropertyValue(property);
		
		if(SparrowConstants.TXT.equalsIgnoreCase(property.getType())) {
			pv.setTxt(value);
		} else if(SparrowConstants.NUM.equalsIgnoreCase(property.getType())) {
			try {
				pv.setNum(Double.parseDouble(value));
			} catch(Exception e) {
				throw new SparrowException("Invalid NUM value for property value "+property.getName());
			}
		} else {
			throw new SparrowException(property.getType()+" is an invalid type!");
		}
		
		return pv;
	}
	

//	public static Device buildDevice(
//			String sName, List<Component> lstComponents, char[] directions)
//			throws SparrowException {
//		
////		System.out.println("[EugeneBuilder.buildDevice] -> "+sName+", "+Arrays.toString(directions));
//		
////		if(null != lstComponents) {
////			/* here, we iterate over the devices components and start building the device graph */
////			long[] components = new long[lstComponents.size()];
////			int i=0;
////			for(Component component : lstComponents) {
////				//System.out.println("[EugeneBuilder.buildDevice] -> "+
////				//		component.getName()+", "+directions[i]);
////				components[i++] = SymbolTables.getId(component.getName());
////			}
////		}
////		Device d = new Device(sName, lstComponents);
//		
//		Device device = new Device(sName, lstComponents, null, directions);
////		System.out.println("[EugeneBuilder.buildDevice] -> "+device);
//		return device;
//	}
//
//	public static Device buildDevice(
//			String sName, 
//			long[] lstComponents, 
//			char[] directions) 
//					throws SparrowException {
//		
////		System.out.println("[EugeneBuilder.buildDevice] -> "+sName+", "+Arrays.toString(lstComponents));
//		
//		Device device = new Device(sName);
//		if(null != lstComponents && lstComponents.length > 0) {
//			for(long component : lstComponents) {
//				NamedElement element = SymbolTables.getComponent(component);
//				device.add(element);
//			}
//		}
//		device.setDirections(directions);
//		
//		return device;
//	}
//
//	public static Device buildDevice(
//			String sName, 
//			List<Component> lstComponents, 
//			List<Property> lstProperties, 
//			char[] directions) 
//					throws SparrowException {
//		
//		if(null != directions) {
//			for(int i=0; i<directions.length; i++) {
//				if('-' == directions[i]) {
//					Component component = lstComponents.get(i);
//					
//					/*
//					 * if the current component is a device, 
//					 * then we reverse the device's elements
//					 */
//					if(component instanceof Device) {
//						((Device)component).reverseComponents();
//					}
//				}
//			}
//		}
//		return new Device(sName, lstComponents, lstProperties, directions);		
//	}

//	public static Rule buildRule(
//			String sName, Device objDevice, Token token, CommonTree tree) 
//					throws SparrowException {
//		
//		/** iterate over the tree and build the appropriate predicates **/
//		Predicate predicate = null;
//		if (null != tree) {
//			try {
//				predicate = RuleTreeParser.buildTree(tree);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new SparrowException(e.getMessage());
//			}
//		} else {
//			throw new SparrowException("No rule tree provided!");
//		}
//
//		/** and finally assign the rules to the device **/
//		/** => making it later easier to retrieve one device's rules **/
//		
//		
//		if(null != predicate) { 
//			return new Rule(sName, objDevice, predicate);
//		}
//		return null;
//	}

	private static Map<String, PartType> hmPartTypes = null;
	
	private static void initPartTypesMap() {
		hmPartTypes = new HashMap<String, PartType>();
	}
	
	public static PartType buildPartType(
			String name, List<Property> properties) {
		
		// instantiate the part types hashmap
		// if it's NULL
		if(null == hmPartTypes) {
			initPartTypesMap();
		}
		
		// check if the part type has been declared already
		// if yes, then return it
		if(hmPartTypes.containsKey(name)) {
			return hmPartTypes.get(name);
		} 
		
		// the part type has not been declared
		// hence, create it and store it in the part types hashmap
		PartType objPartType = new PartType(name);
		if (null != properties && !properties.isEmpty()) {
			for (Property property : properties) {
				objPartType.getProperties().add(property);
			}
		}
		hmPartTypes.put(name, objPartType);
		
		return objPartType;
	}

	public static Part buildPart(
			String name, PartType parttype, List<PropertyValue> values) 
					throws SparrowException {

		Part part = new Part(name, parttype);

		if (null != values && !values.isEmpty()) {
				/*
				 *  assign the property values to the part
				 */
				for (PropertyValue value : values) {
					
					try {
						
						// first, check if the part's part type has the property						
						Property property = part.getType().getProperty(value.getName());
						
						if(null != property) {
							// here, we can also compare the types
							part.setPropertyValue(property, value);
						} else {
							// we need to create the property first
							part.setPropertyValue(
									SparrowBuilder.buildProperty(value.getName(), value.getType()), 
									value);
						}
						
					} catch(DOMException de) {
						throw new SparrowException(de.toString());
					}
				}
		}

		return part;
	}
	
	/**
	 * sbolDC.getDisplayId(), lstDeviceElements, directions
	 * @return
	 * @throws SparrowException
	 */
	public static Device buildDevice(String displayId, List<Component> components, char[] directions) 
			throws SparrowException {
		
		throw new UnsupportedOperationException("DOES NOT WORK (YET)!");
	}

}
