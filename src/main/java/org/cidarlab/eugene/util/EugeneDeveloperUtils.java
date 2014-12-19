package org.cidarlab.eugene.util;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Property;

/**
 * The EugeneUtils class provides a set of methods to 
 * provide some syntactic Java sugar for the Eugene code base.
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneDeveloperUtils {

	public static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * The getSequenceProperty() method returns a Property object 
	 * that represents the DNA sequence of a component.
	 * 
	 * @return a Property object of type txt with name SEQUENCE
	 */
	public static Property createSequenceProperty() {
		return new Property(EugeneConstants.SEQUENCE_PROPERTY, EugeneConstants.TXT);
	}
	
	/**
	 * The getSequenceProperty() method returns a Property object 
	 * that represents the DNA sequence of a component.
	 * 
	 * @return a Property object of type txt with name SEQUENCE
	 */
	public static Property createPigeonProperty() {
		return new Property(EugeneConstants.PIGEON_PROPERTY, EugeneConstants.TXT);
	}
	
	/**
	 * The addDoubleQuotesTo(String) method checks if a given 
	 * String starts and ends with a double-quota ('"') character.
	 * If yes, then it adds double-quotas around the original String.
	 * Otherwise, it returns the original string. 
	 * 
	 * @param s  ... the string
	 * @return  ... the string w/ double-quotes
	 */
	public static String addDoubleQuotesTo(String s) {
		if(!s.startsWith("\"") && !s.endsWith("\"")) {
			return "\"" + s + "\"";
		}
		
		return s;
	}

	
}
