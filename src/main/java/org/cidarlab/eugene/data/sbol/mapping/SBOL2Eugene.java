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

package org.cidarlab.eugene.data.sbol.mapping;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.eugene.exception.EugeneException;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLRootObject;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.StrandType;
import org.sbolstandard.core.util.SequenceOntology;

/**
 * The SBOL2Eugene class provides static methods to compile in-memory 
 * objects of the Eugene DOM into objects of the SBOL DOM 
 * based on the following mapping:
 * -- basic DNACompomonent (ie a DNAComponent without SequenceAnnotations)   <-->  Part
 * -- composite DNAComponent (ie a DNAComponent with SequenceAnnotations)    <-->  Device
 * -- Collection                                                             <-->  Device[], Collection
 *   
 * @author Ernst Oberortner
 */
public class SBOL2Eugene {

	private static List<Property> lstProperties = null;

	/*
	 * the convert() method gets as input the SBOLRootObject (from the SBOLDocument)
	 * and returns a corresponding Eugene object ... 
	 * 
	 * all classes in Eugene inherit (directly or indirectly) from the NamedElement class
	 * 
	 */
	public static NamedElement convert(SBOLRootObject sbolComponent)
			throws EugeneException {

		if (null == lstProperties) {
			createSBOLProperties();
		}

		if (null != sbolComponent) {
			/*
			 * SBOL Collection --> Eugene Collection
			 */
			if (sbolComponent instanceof org.sbolstandard.core.impl.CollectionImpl) {
				org.sbolstandard.core.impl.CollectionImpl sbolCollection = 
						(org.sbolstandard.core.impl.CollectionImpl) sbolComponent;

				EugeneCollection collection = new EugeneCollection(sbolCollection.getName());
				
				/*
				 * here, we iterate over the SBOL Collection's elements
				 */
				for (DnaComponent dc : sbolCollection.getComponents()) {

					/*
					 * every SBOL Collection element gets added to 
					 * the Eugene collection 
					 */
					
					NamedElement ne = SBOL2Eugene.convert(dc);
					if(ne instanceof EugeneContainer) {
						collection.getElements().addAll(((EugeneContainer)ne).getElements());
					} else if(ne instanceof Component) {
						collection.getElements().add((Component)ne);
					}
					
				}

				return collection;

			/*
			 * SBOL DnaComponent
			 */
			} else if (sbolComponent instanceof org.sbolstandard.core.impl.DnaComponentImpl) {
				org.sbolstandard.core.impl.DnaComponentImpl sbolDC = 
						(org.sbolstandard.core.impl.DnaComponentImpl) sbolComponent;

				
				if (null != sbolDC.getAnnotations()
						&& !sbolDC.getAnnotations().isEmpty()) {
					
					/*
					 * SBOL composite DNAComponent --> Eugene Device
					 */
					return buildDevice(sbolDC);
					
				} else {
					
					/*
					 * SBOL DNAComponent -> Eugene Part
					 */
					return buildPart(sbolDC);
				}
			}
		}

		return (NamedElement) null;
	}

	
	private static Device buildDevice(
			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
		throws EugeneException {
		
		/*
		 *  if the SBOL component does have annotations, then 
		 *  we transform it into an Eugene Device 
		 */

		// for a device, we have to iterate over all sequence
		// annotations and convert each annotated DNA component
		Device device = new Device(sbolDC.getName());
		for (SequenceAnnotation sa : sbolDC.getAnnotations()) {
			
			NamedElement objElement = SBOL2Eugene.convert(sa.getSubComponent());

			if (null != objElement && 
					objElement instanceof org.cidarlab.eugene.dom.Component) {

				/*
				 * COMPONENT
				 */
				List<org.cidarlab.eugene.dom.NamedElement> loc = 
						new ArrayList<org.cidarlab.eugene.dom.NamedElement>();
				loc.add((org.cidarlab.eugene.dom.Component) objElement);
				device.getComponents().add(loc);
			
				/*
				 * ORIENTATION of the component
				 */
				List<Orientation> loo = new ArrayList<Orientation>();
				if(sa.getStrand() == StrandType.NEGATIVE) {
					loo.add(Orientation.REVERSE);
				} else {
					loo.add(Orientation.FORWARD);
				}
				device.getOrientations().add(loo);
			}
			
		}
		
		/*
		 * get the directions
		 */
		return device;
	}
	
	private static Part buildPart(
			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
		throws EugeneException {

		// first, let's check if the SBOL Dna component is valid.
		// i.e. it has a type.
		if(null == sbolDC) {
			throw new EugeneException(sbolDC + " is an invalid SBOL Dna Component.");
		}
		
		/*
		 * let's get the part type
		 */
		
		PartType pt = null;
		if(sbolDC.getTypes() == null || sbolDC.getTypes().isEmpty()) {
			pt = getPartType(null);
		} else {
			pt = getPartType(sbolDC.getTypes().get(0).toString());
		}

		/*
		 * finally, we set the part's property values
		 */
		List<PropertyValue> lstValues = getPartPropertyValues(sbolDC);
		
		/*
		 * next, we create the Part object and
		 * set its property values
		 */		
		Part objPart = null;
		if(null != sbolDC.getName() && !sbolDC.getName().isEmpty()) {
			objPart = new Part(pt, sbolDC.getName());
		} else { 
			objPart = new Part(pt, sbolDC.getDisplayId());
		}
		
		for(PropertyValue pv : lstValues) {
			objPart.getPropertyValues().put(pv.getName(), pv);
		}

		return objPart;
	}
	
	private static List<PropertyValue> getPartPropertyValues(
			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
					throws EugeneException {
		
		List<PropertyValue> lstValues = new ArrayList<PropertyValue>();
		
		/*
		 * displayId
		 */
		PropertyValue objDisplayId =
				new PropertyValue(
						EugeneConstants.DISPLAY_ID_PROPERTY, EugeneConstants.TXT);
		objDisplayId.setTxt((null!=sbolDC.getDisplayId())?sbolDC.getDisplayId():"");
		lstValues.add(objDisplayId);
		
		/*
		 * name
		 */
		PropertyValue objName =
				new PropertyValue(EugeneConstants.NAME_PROPERTY, EugeneConstants.TXT);
		objName.setTxt((null!=sbolDC.getName())?sbolDC.getName():"");
		lstValues.add(objName);
		
		/*
		 * URI
		 */
		PropertyValue objURI = 
				new PropertyValue(
						EugeneConstants.URI_PROPERTY, EugeneConstants.TXT);
		objURI.setTxt((null!=sbolDC.getURI())?sbolDC.getURI().toString():"");
		lstValues.add(objURI);
		
		/*
		 * description
		 */
		PropertyValue objDescription =
				new PropertyValue(
						EugeneConstants.DESCRIPTION_PROPERTY, EugeneConstants.TXT);
		objDescription.setTxt((null!=sbolDC.getDescription())?sbolDC.getDescription():"");
		lstValues.add(objDescription);
		
		/*
		 * SEQUENCE
		 */
		PropertyValue objSequenceValue = 
				new PropertyValue(
						EugeneConstants.SEQUENCE_PROPERTY, EugeneConstants.TXT);
		objSequenceValue.setTxt((sbolDC.getDnaSequence()!=null)?sbolDC.getDnaSequence().getNucleotides():"");
		lstValues.add(objSequenceValue);
		
		return lstValues;
	}
	
	
	/**
	 * 
	 * @param type
	 * @return
	 * @throws EugeneException
	 */
	private static PartType getPartType(String type) 
			throws EugeneException {
		String sPartTypeName = soMapping(type);

		PartType objPartType = new PartType(
					sPartTypeName,
					lstProperties);
		return objPartType;
	}

	
	private static String soMapping(String s) {

		if (SequenceOntology.FIVE_PRIME_UTR.toString().equals(s)) {
			return "Five_Prime_UTR";
		} else if (SequenceOntology.CDS.toString().equals(s)) {
			return "CDS";
		} else if("http://purl.obolibrary.org/obo/SO_0000139".equals(s) ||
				"http://purl.obolibrary.org/obo/SO_0000552".equals(s)) {
			return "RBS";
		} else if (SequenceOntology.INSULATOR.toString().equals(s)) {
			return "Insulator";
		} else if (SequenceOntology.OPERATOR.toString().equals(s)) {
			return "Operator";
		} else if (SequenceOntology.ORIGIN_OF_REPLICATION.toString().equals(s)) {
			return "Origin_of_Replication";
		} else if (SequenceOntology.PRIMER_BINDING_SITE.toString().equals(s)) {
			return "Primiter_Binding_Site";
		} else if (SequenceOntology.PROMOTER.toString().equals(s)) {
			return "Promoter";
		} else if (SequenceOntology.RESTRICTION_ENZYME_RECOGNITION_SITE
				.toString().equals(s)) {
			return "Restriction_Enzyme_Recognition_Site";
		} else if (SequenceOntology.TERMINATOR.toString().equals(s) ||
				"http://purl.obolibrary.org/obo/SO_0000313".equals(s)) {
			return "Terminator";
		} else if("Promoter".equals(s)) {
			return "Promoter";
		} else if("CDS".equals(s)) {
			return "CDS";
		}

		return EugeneConstants.SBOL_PART_TYPE;
	}
	
	private static void createSBOLProperties() 
			throws EugeneException {
		if (null == lstProperties) {
			
			lstProperties = new ArrayList<Property>(6);
			
			/*
			 * URI
			 */
			lstProperties.add(
					new Property(
							EugeneConstants.URI_PROPERTY, 
							EugeneConstants.TXT));
			
			/*
			 * displayId
			 */
			lstProperties.add(
					new Property(
							EugeneConstants.DISPLAY_ID_PROPERTY, 
							EugeneConstants.TXT));
			
			/*
			 * name
			 */
			lstProperties.add(
					new Property(
							EugeneConstants.NAME_PROPERTY, 
							EugeneConstants.TXT));
			
			/*
			 * description
			 */
			lstProperties.add(
					new Property(
							EugeneConstants.DESCRIPTION_PROPERTY, 
							EugeneConstants.TXT));
			
			/*
			 * type(s) 
			 */
			lstProperties.add(
					new Property(
							EugeneConstants.TYPE_PROPERTY, 
							EugeneConstants.TXTLIST));
			
			/*
			 * Sequence
			 */
			lstProperties.add(
					new Property(
							EugeneConstants.SEQUENCE_PROPERTY, 
							EugeneConstants.TXT));
			
		}
	}
}
