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

package org.cidarlab.eugene.constants;

/**
 * 
 * @author Ernst Oberortner
 */
public class EugeneConstants {

	public static final String CONTAINS = "CONTAINS";
	public static final String NOTCONTAINS = "NOTCONTAINS";
	public static final String AFTER = "AFTER";
	public static final String BEFORE = "BEFORE";
	public static final String STARTSWITH = "STARTSWITH";
	public static final String ENDSWITH = "ENDSWITH";
	public static final String WITH = "WITH";
	public static final String NOTWITH = "NOTWITH";
	public static final String NEXTTO = "NEXTTO";
	public static final String LEFTTO = "LEFTTO";
	public static final String MORETHAN = "MORETHAN";
	public static final String NOTMORETHAN = "NOTMORETHAN";
	public static final String THEN = "THEN";
	public static final String MATCHES = "MATCHES";
	public static final String EXACTLY = "EXACTLY";
	public static final String INSTANCEOF = "INSTANCEOF";

	public static final String EQUALS = "EQUALS";
	public static final String NOTEQUALS = "NOTEQUALS";
	public static final String GT = ">";
	public static final String GEQ = ">=";
	public static final String LT = "<";
	public static final String LEQ = "<=";

	public static final String ADD = "+";
	public static final String SUBTRACT = "-";
	public static final String MULTIPLY = "*";
	public static final String DIVIDE = "/";

	public static final String COLLECTION = "COLLECTION";
	public static final String COLLECTIONELEMENT = "COLLECTIONELEMENT";
	public static final String COLLECTIONARRAY = "COLLECTION[]";

	public static final String DEVICE = "Device";
	public static final String ABSTRACT_DEVICE = "ABSTRACT DEVICE";
	public static final String TANGIBLE_DEVICE = "TANGIBLE DEVICE";
	public static final String HYBRID_DEVICE = "HYBRID DEVICE";
	public static final String PARTTYPE = "Part Type";
	public static final String PART = "Part";
	public static final String PROPERTY = "Property";
	public static final String DESIGN_SPACE = "DESIGN_SPACE";

	public static final String COMPONENTARRAY = "Device[]";
	public static final String DEVICEARRAY = "Device[]";
	public static final String PARTARRAY = "Part[]";
	public static final String PARTTYPEARRAY = "Part Type[]";
	public static final String PROPERTYARRAY = "Property[]";
	public static final String RULEARRAY = "Rule[]";

	public static final String STRICT = "strict";
	public static final String FLEXIBLE = "flexible";

	public static final String PIGEON_PROPERTY = "PIGEON";
	public static final String SEQUENCE_PROPERTY = "SEQUENCE";
	
	public static final String SBOL_PART_TYPE = "SBOLPartType";
	public static final String DISPLAY_ID_PROPERTY = "displayId";
	public static final String URI_PROPERTY = "URI";
	public static final String NAME_PROPERTY = "Name";
	public static final String DESCRIPTION_PROPERTY = "Description";
	public static final String TYPE_PROPERTY = "Type";

	public static final String VARIABLE = "Variable";
	public static final String RULE = "Rule";
	public static final String RULEOPERAND = "RULEOPERAND";
	public static final String FUNCTION = "Function";
	public static final String PARAMETER = "PARAMETER";
	public static final String PARAMETERVALUE = "PARAMETERVALUE";

	public static final String NUM = "num";
	public static final String NUMLIST = "numList";
	public static final String TXT = "txt";
	public static final String TXTLIST = "txtList";
	public static final String BOOLEAN = "boolean";

	public static final String IFRULE = "IF-RULE";
	public static final String NOTERULE = "NOTE-RULE";

	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final String XOR = "XOR";
	public static final String NOT = "NOT";

	public static final String EUGENE_URL = "http://www.eugenecad.org";

	public static final String PERMUTE = "permute";
	public static final String PRODUCT = "product";
	
	public static final String FORWARD = "forward";
	public static final String REVERSE = "reverse";
	
	public static final String SIZE_FUNCTION = "size";
	
	public static final String DIRECTION = "direction";
	
	/** PRE-DEFINED NODE PROPERTIES **/
	public static final String NODE_NAME = "COMPONENT_NAME";
	public static final String NODE_TYPE = "COMPONENT_TYPE";
	
//	public static final String ANONYMOUS_VARIABLE = "ANONYMOUS_VARIABLE";
	
	public static enum ParsingPhase {
		PRE_PROCESSING, INTERPRETING
	}
	
}
