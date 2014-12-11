package org.cidarlab.eugene.util;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * 
 * The EugeneUtils class provides a set of methods to process 
 * Eugene results more easily. 
 * 
 * It is an Adapter to other Utility classes.
 * For example, it provides the flipAndInvert(Device) method 
 * which is actually implemented in to DeviceUtils class.
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneUtils {
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	/**
	 * The flipAndInvert(Device) method takes as input a device, reverses the 
	 * order of its components (``flip'') and inverts the components' orientation.
	 * 
	 * Example:
	 * Device D(+a, +b, +c); --> Device D(-c, -b, -a);
	 * Device D(-c, -b, -a); --> Device D(+a, +b, +c);
	 *  
	 * @param d  ... the device to be flipped and inverted
	 * 
	 * @return a new device with reverse ordered components and inverted orientations
	 * 
	 * @throws EugeneException
	 */
	public static Device flipAndInvert(Device d) 
			throws EugeneException {
		return DeviceUtils.flipAndInvert(d);
	}

	/**
	 * The invert(Device) method inverts the orientations in 
	 * a given device.
	 *  
	 * Example:
	 * Device D(+a, +b, +c); --> Device D(-a, -b, -c);
	 * Device D(-a, -b, -c); --> Device D(+a, +b, +c);
	 * 
	 * @param d  ... the device in which the orientations of its components should be inverted
	 * 
	 * @return ... a device with inverted orientations of its components
	 * 
	 * @throws EugeneException
	 */
	public static Device invert(Device d) 
			throws EugeneException {
		
		return DeviceUtils.invert(d);
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
		
		return DeviceUtils.flip(d);
		
	}
	
	/**
	 * The prettyPrint(Device) method takes as input a Device object
	 * and returns a String representation of the Device object 
	 * including line breaks and tab-spaces.
	 * 
	 * @param d  ... the Device object to be ``pretty printed''
	 * 
	 * @return the String representation of the Device
	 */
	public static String prettyPrint(Device d) 
			throws EugeneException {

		if(null != d) {
			return (EugeneUtils.traverse(d, 1)).toString();
		}
		
		return null;
	}
	
	public static String prettyPrint(Part p) 
			throws EugeneException {

		if(null == p) {
			return null;
		}
		
		return prettyPrint(p, 0).toString();
	}
	
	private static StringBuilder prettyPrint(Part p, int level) 
			throws EugeneException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(p.getTypeAsString()).append(" ")
			.append(p.getName()).append(" (");
		
		if(null != p.getPropertyValues() && !p.getPropertyValues().isEmpty()) {

			sb.append(EugeneUtils.NEWLINE);
			
			int i=0;
			for(String prop : p.getPropertyValues().keySet()) {
				
				if(p.getPropertyValues().get(prop)!=null) {
					
					for(int k=1; k<=(level+1); k++) {
						sb.append("    ");
					}
					
					sb.append(".").append(prop).append("(");
					sb.append(p.getPropertyValues().get(prop).getValue());
					sb.append(")");

					if(i < p.getPropertyValues().size() - 1) {
						sb.append(",");
					}
					
					sb.append(EugeneUtils.NEWLINE);
				}
				
				i++;
			}
		}

		for(int i=1; i<=level; i++) {
			sb.append("    ");
		}
		sb.append(")")/*.append(EugeneUtils.NEWLINE)*/;
		
		return sb;
	}
	
	private static StringBuilder traverse(Device d, int level) 
			throws EugeneException {
		StringBuilder sb = new StringBuilder();
		sb.append(EugeneConstants.DEVICE).append(" ").append(d.getName()).append(" (");

		if(d.getComponents().size() >= 1) {
			sb.append(EugeneUtils.NEWLINE);
		}
		
		for(int i = 0; i<d.getComponents().size(); i++) {
			
			// indentations
			for(int l=1; l<=level; l++) {
				sb.append("    ");
			}
			
			
			if(d.getComponents().get(i).size() > 1) {
				
				// SELECTION
				sb.append("[");
				for(int j=0; j<d.getComponents().get(i).size(); j++) {
					
					if(null != d.getOrientations() && !d.getOrientations().isEmpty()) {
						if(d.getOrientations().get(i).get(j) == Orientation.FORWARD) {
							sb.append("+");
						} else if(d.getOrientations().get(i).get(j) == Orientation.REVERSE) {
							sb.append("-");
						}
					}						
					sb.append(d.getComponents().get(i).get(j)/*.getName()*/);
					
					if(j < d.getComponents().get(i).size() - 1) {
						sb.append("|");
					}
				}
				sb.append("]");
				
			} else {
				
				if(null != d.getOrientations() && !d.getOrientations().isEmpty()) {
					if(d.getOrientations().get(i).get(0) == Orientation.FORWARD) {
						sb.append("+");
					} else if(d.getOrientations().get(i).get(0) == Orientation.REVERSE) {
						sb.append("-");
					}
				}
				
				if(d.getComponents().get(i).get(0) instanceof Device) {
					
					Device printDev = (Device)d.getComponents().get(i).get(0);
					if(d.getOrientations().get(i).get(0) == Orientation.REVERSE) {
						try {
							printDev = EugeneUtils.flipAndInvert((Device)printDev);
						} catch(EugeneException ee) {
							throw new EugeneException(ee.getMessage());
						}
					}
					
					sb.append(
							EugeneUtils.traverse(printDev, level + 1));
				} else if(d.getComponents().get(i).get(0) instanceof Part) {
					sb.append(prettyPrint((Part)d.getComponents().get(i).get(0), level));
				} else {
					sb.append(d.getComponents().get(i).get(0).getName());
				}
			}
			
			if(i < d.getComponents().size() - 1) {
				sb.append(",");
			}
			// every component gets printed in a separate line
			sb.append(EugeneUtils.NEWLINE);


		}
		if(level > 1) {
			// indentations
			for(int l=1; l<=level-1; l++) {
				sb.append("    ");
			}
		}
		sb.append(")");
		return sb;
	} 

}
