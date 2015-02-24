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
