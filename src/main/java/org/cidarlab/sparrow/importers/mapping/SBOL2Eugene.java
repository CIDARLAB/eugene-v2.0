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

package org.cidarlab.sparrow.importers.mapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.SparrowBuilder;
import org.cidarlab.sparrow.constants.SBOLConstants;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.SparrowException;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLRootObject;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.StrandType;
import org.sbolstandard.core.util.SequenceOntology;

/*
 *   Mapping:
 *   
 *   basic DNACompomonent (ie a DNAComponent without SequenceAnnotations)   <-->  Part
 *   composite DNAComponent (ie a DNAComponent with SequenceAnnotations)    <-->  Device
 *   Collection                                                             <-->  Device[], Collection
 *
 */

public class SBOL2Eugene {

	private static List<Property> properties = null;

	/*
	 * the convert() method gets as input the SBOLRootObject (from the SBOLDocument)
	 * and returns a corresponding Eugene object ... 
	 * 
	 * all classes in Eugene inherit (directly or indirectly) from the NamedElement class
	 * 
	 */
	public static Object convert(SBOLRootObject sbolComponent)
			throws SparrowException {

		if (null == properties) {
			createSBOLProperties();
		}

		if (null != sbolComponent) {
			/*
			 * SBOL Collection --> Eugene Collection
			 */
			if (sbolComponent instanceof org.sbolstandard.core.impl.CollectionImpl) {
				org.sbolstandard.core.impl.CollectionImpl sbolCollection = 
						(org.sbolstandard.core.impl.CollectionImpl) sbolComponent;

				Set<Component> components = new HashSet<Component>();
				
				/*
				 * here, we iterate over the SBOL Collection's elements
				 */
				for (DnaComponent dc : sbolCollection.getComponents()) {

					/*
					 * every SBOL Collection element gets converted into a
					 * Eugene NamedElement 
					 */
					NamedElement objElement = (NamedElement)SBOL2Eugene.convert(dc);
					
					if (null != objElement && objElement instanceof Component) {
						
						/*
						 * every SBOL 
						 */
						components.add((Component) objElement);
					}
				}

				return components;

			/*
			 * SBOL DnaComponent
			 */
			} else if (sbolComponent instanceof org.sbolstandard.core.impl.DnaComponentImpl) {
				org.sbolstandard.core.impl.DnaComponentImpl sbolDC = (org.sbolstandard.core.impl.DnaComponentImpl) sbolComponent;

				
				if (null != sbolDC.getAnnotations()
						&& !sbolDC.getAnnotations().isEmpty()) {
					
					/*
					 * SBOL composite DNAComponent --> Eugene Device
					 */
					return buildDevice(sbolDC);
					
				} else {

					/*
					 *  if the SBOL component does not have any annotations, then we map it onto a 
					 *  Eugene Part
					 */

					return buildPart(sbolDC);
				}
			}
		}

		return (NamedElement) null;
	}

	
	private static Device buildDevice(
			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
		throws SparrowException {
		
		// if the SBOL component does have annotations, then we transform it into a 
		// Eugene Device

		List<Component> lstDeviceElements = 
				new ArrayList<Component>(sbolDC.getAnnotations().size());

		// for a device, we have to iterate over all sequence
		// annotations and
		// convert each annotated DNA component
		char[] directions = new char[sbolDC.getAnnotations().size()];
		int i=0;
		for (SequenceAnnotation sa : sbolDC.getAnnotations()) {
			Object element = SBOL2Eugene.convert(sa.getSubComponent());
			if (null != element && 
					element instanceof Component) {
				
				lstDeviceElements.add((Component) element);
			}
			if(sa.getStrand() == StrandType.NEGATIVE) {
				directions[i++] = '-';
			} else {
				directions[i++] = '+';
			}
			
		}
		
		/*
		 * get the directions
		 */
		return SparrowBuilder.buildDevice(
				sbolDC.getDisplayId(), lstDeviceElements, directions);
	}
	
	private static Part buildPart(
			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
		throws SparrowException {

		
		/*
		 * let's get the part type
		 */
		PartType pt = getPartType(sbolDC.getTypes().get(0).toString());

		/*
		 * finally, we set the part's property values
		 */
		List<PropertyValue> values = getPartPropertyValues(sbolDC);
		
		/*
		 * next, we create the Part object
		 */		
		Part objPart = SparrowBuilder.buildPart(
				sbolDC.getDisplayId(), pt, values);

		/*
		 * and store it in the symbol tables
		 */
//		SymbolTables.put(objPart);

		return objPart;
	}
	
	private static List<PropertyValue> getPartPropertyValues(
			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
					throws SparrowException {
		
		List<PropertyValue> lstValues = new ArrayList<PropertyValue>();
		
		/*
		 * displayId
		 */
		PropertyValue objDisplayId =
				SparrowBuilder.buildPropertyValue(
						SBOLConstants.DISPLAY_ID_PROPERTY,                            // name 
						SparrowConstants.TXT,                                         // type
						(null!=sbolDC.getDisplayId())?sbolDC.getDisplayId():"");      // value
		lstValues.add(objDisplayId);
		
		/*
		 * name
		 */
		PropertyValue objName =
				SparrowBuilder.buildPropertyValue(
						SBOLConstants.NAME_PROPERTY,
						SparrowConstants.TXT,                                        
						(null!=sbolDC.getName())?sbolDC.getName():"");
		lstValues.add(objName);
		
		/*
		 * URI
		 */
		PropertyValue objURI = SparrowBuilder.buildPropertyValue(
				SBOLConstants.URI_PROPERTY, 
				SparrowConstants.TXT, 
				(null!=sbolDC.getURI())?sbolDC.getURI().toString():"");
		lstValues.add(objURI);
		
		/*
		 * description
		 */
		PropertyValue objDescription =
				SparrowBuilder.buildPropertyValue(
						SBOLConstants.DESCRIPTION_PROPERTY,
						SparrowConstants.TXT,                                        
						(null!=sbolDC.getDescription())?sbolDC.getDescription():"");
		lstValues.add(objDescription);
		
		/*
		 * SEQUENCE
		 */
		PropertyValue objSequenceValue = 
				SparrowBuilder.buildPropertyValue(
						SBOLConstants.SEQUENCE_PROPERTY,
						SparrowConstants.TXT,                                        
						(sbolDC.getDnaSequence()!=null)?sbolDC.getDnaSequence().getNucleotides():"");
		lstValues.add(objSequenceValue);
		
		return lstValues;
	}
	
//	private static PartType buildPartType(
//			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
//					throws SparrowException {
//
//		return getPartType(sbolDC.getTypes());
//	}
	
//	private static PartType getPartType(List<URI> types) 
//		throws SparrowException {
//		
//		PartType pt = getPartType(types.get(0).toString());
//		if(null != pt) {
//			return pt;
//		}
//		
//		/*
//		 * we need to create the part type
//		 */
//		return getPartType(types.get(0).toString());
//	}
	
	private static PartType getPartType(String type) 
			throws SparrowException {
		
		String sPartTypeName = soMapping(type);

		return SparrowBuilder.buildPartType(
				sPartTypeName,
				properties);

//		PartType objPartType = (PartType) null;
//		NamedElement objTmp = SymbolTables.get(sPartTypeName);
//		if (null != objTmp && objTmp instanceof PartType) {
//			return (PartType) objTmp;
//		} else {
//			SymbolTables.put(objPartType);
//		}
//		return objPartType;
	}

	private static String soMapping(String s) {
		if(null == s || s.isEmpty()) {
			return SBOLConstants.SBOL_PART_TYPE;
		}
		
		if (SequenceOntology.FIVE_PRIME_UTR.toString().equals(s)) {
			return "Five_Prime_UTR";
		} else if (SequenceOntology.CDS.toString().equals(s) || "CDS".equals(s)) {
			return "CDS";
		} else if(s.contains("SO_0000139") || s.contains("SO_0000552")) {
			return "RBS";
		} else if (SequenceOntology.INSULATOR.toString().equals(s)) {
			return "Insulator";
		} else if (SequenceOntology.OPERATOR.toString().equals(s)) {
			return "Operator";
		} else if (SequenceOntology.ORIGIN_OF_REPLICATION.toString().equals(s)) {
			return "Origin_of_Replication";
		} else if (SequenceOntology.PRIMER_BINDING_SITE.toString().equals(s)) {
			return "Primiter_Binding_Site";
		} else if (SequenceOntology.PROMOTER.toString().equals(s) || "Promoter".equals(s) ||
				s.contains("SO_0005836")) {
			return "Promoter";
		} else if (SequenceOntology.RESTRICTION_ENZYME_RECOGNITION_SITE
				.toString().equals(s)) {
			return "Restriction_Enzyme_Recognition_Site";
		} else if (SequenceOntology.TERMINATOR.toString().equals(s) ||
				s.contains("SO_0000313") ||
				s.contains("SO_0000614")) {
			return "Terminator";
		}

		return SBOLConstants.SBOL_PART_TYPE;
	}

	public static void readURI(URI uri) throws Exception {
		URL url = uri.toURL();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}

	
	private static void createSBOLProperties() 
			throws SparrowException {
		if (null == properties) {
			
			properties = new ArrayList<Property>(6);
			
			/*
			 * URI
			 */
			properties.add(
					SparrowBuilder.buildProperty(
							SBOLConstants.URI_PROPERTY, 
							SparrowConstants.TXT));
			
			/*
			 * displayId
			 */
			properties.add(
					SparrowBuilder.buildProperty(
							SBOLConstants.DISPLAY_ID_PROPERTY, 
							SparrowConstants.TXT));
			
			/*
			 * name
			 */
			properties.add(
					SparrowBuilder.buildProperty(
							SBOLConstants.NAME_PROPERTY, 
							SparrowConstants.TXT));
			
			/*
			 * description
			 */
			properties.add(
					SparrowBuilder.buildProperty(
							SBOLConstants.DESCRIPTION_PROPERTY, 
							SparrowConstants.TXT));
			
			/*
			 * type(s) 
			 */
			properties.add(
					SparrowBuilder.buildProperty(
							SBOLConstants.TYPE_PROPERTY, 
							SparrowConstants.TXTLIST));
			
			/*
			 * Sequence
			 */
			properties.add(
					SparrowBuilder.buildProperty(
							SBOLConstants.SEQUENCE_PROPERTY, 
							SparrowConstants.TXT));
			
//			/*
//			 * put the properties into the symbol tables
//			 */
//			for (Property objProperty : properties) {
//				if (!SymbolTables.contains(objProperty.getName())) {
//					SymbolTables.put(objProperty);
//				}
//			}
			
		}
	}

//	private static Property getSequenceProperty() {
//		// every part type should have a sequence property
//		Property objSequenceProperty = (Property) SymbolTables
//				.get(SparrowConstants.SEQUENCE_PROPERTY);
//		if (null == objSequenceProperty) {
//			objSequenceProperty = SparrowBuilder.buildProperty(
//					SparrowConstants.SEQUENCE_PROPERTY, SparrowConstants.TXT);
//		}
//		return objSequenceProperty;
//	}

}
