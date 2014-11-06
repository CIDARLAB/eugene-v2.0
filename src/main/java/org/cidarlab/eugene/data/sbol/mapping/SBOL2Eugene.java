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

package org.cidarlab.eugene.data.sbol.mapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
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

/*
 *   Mapping:
 *   
 *   basic DNACompomonent (ie a DNAComponent without SequenceAnnotations)   <-->  Part
 *   composite DNAComponent (ie a DNAComponent with SequenceAnnotations)    <-->  Device
 *   Collection                                                             <-->  Device[], Collection
 *
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
	
//	private static PartType buildPartType(
//			org.sbolstandard.core.impl.DnaComponentImpl sbolDC)
//					throws EugeneException {
//		PartType pt = getPartType(sbolDC.getTypes());
////		if(null != pt) {
////			SymbolTables.put(pt);
////		}
//		return pt;
//	}
//	

//	private static PartType getPartType(List<URI> types) 
//		throws EugeneException {
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
			throws EugeneException {
		String sPartTypeName = soMapping(type);

		PartType objPartType = (PartType) null;
//		NamedElement objTmp = SymbolTables.get(sPartTypeName);
//		if (null != objTmp && objTmp instanceof PartType) {
//			return (PartType) objTmp;
//		} else {
			objPartType = new PartType(
					sPartTypeName,
					lstProperties);
//			SymbolTables.put(objPartType);
//		}
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
			
			/*
			 * put the properties into the symbol tables
			 */
//			for (Property objProperty : lstProperties) {
//				if (!SymbolTables.contains(objProperty.getName())) {
//					SymbolTables.put(objProperty);
//				}
//			}
			
		}
	}

//	private static Property getSequenceProperty() {
//		// every part type should have a sequence property
//		Property objSequenceProperty = (Property) SymbolTables
//				.get(EugeneConstants.SEQUENCE_PROPERTY);
//		if (null == objSequenceProperty) {
//			objSequenceProperty = new Property(
//					EugeneConstants.SEQUENCE_PROPERTY, EugeneConstants.TXT);
//		}
//		return objSequenceProperty;
//	}

}
