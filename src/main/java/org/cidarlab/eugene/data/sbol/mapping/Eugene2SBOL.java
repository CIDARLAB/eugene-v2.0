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

// SBOL
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.biojava.bio.seq.DNATools;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.DeviceUtils;
import org.cidarlab.eugene.util.SequenceUtils;
import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.StrandType;
import org.sbolstandard.core.impl.DnaSequenceImpl;
import org.sbolstandard.core.util.SequenceOntology;


/**
 * The Eugene2SBOL class provides static methods for compiling 
 * a Eugene component or container into an SBOL equivalent.
 *  
 * The Eugene2SBOL compiler is based on the 
 * following mapping table:
 *       Eugene        |                         SBOL
 * --------------------+--------------------------------------------------
 *      Container      |   Collection
 *      Device         |   DNAComponent w/ SequenceAnnotations
 *                     |   and sub-DNAComponents
 *      Part           |   DNAComponent w/o SequenceAnnotations     
 *                     |   and sub-DNAComponents, but w/ a DNASequence
 *      PartType       |   DNAComponent w/o SequenceAnnotations     
 *                     |   and sub-DNAComponents, and w/o a DNASequence
 *                     
 * @author Ernst Oberortner
 */
public class Eugene2SBOL {

	public static ArrayList<String> lstURIs;
	private static final String DEFAULT_URI = "http://www.eugenecad.org";
	public static Map<String, URI> reusedComponents;
	
	/*
	 *  EugeneContainer --> SBOL Collection
	 */
	public static Collection convert(EugeneContainer objContainer, String sURI)
			throws Exception {
		
		if (null == objContainer) {
			throw new EugeneException("I cannot export a NULL value to SBOL!");
		}

		if(null == lstURIs) {
			lstURIs = new ArrayList<String>();
		}
		if(null == reusedComponents) {
			reusedComponents = new HashMap<String, URI>();
		}

//		lstURIs = new ArrayList<String>();
//		reusedComponents = new HashMap<String, URI>();
		
		Collection sbolCollection = SBOLFactory.createCollection();
		sbolCollection.setDescription(objContainer.getName());
		sbolCollection.setDisplayId(objContainer.getName());

		try {
			sbolCollection.setURI(URI.create(DEFAULT_URI + "/" + objContainer.getName()));
			addURI(URI.create(DEFAULT_URI + "/" + objContainer.getName()).toASCIIString());
		} catch (Exception e) {
			throw new EugeneException(e.toString());
		}

		for(NamedElement element : objContainer.getElements()) {
			if(element instanceof Component) {
				sbolCollection.addComponent(
						Eugene2SBOL.convert(
								(Component)element,
								null,
								0));
			}
		}

		lstURIs = null;
		reusedComponents = null;
		
		return sbolCollection;
	}

	/*
	 * Eugene Component --> SBOL DnaComponent
	 */
	public static DnaComponent convert(Component objComponent, DnaComponent parent, int pos)
			throws EugeneException {
		
		if (null == objComponent) {
			throw new EugeneException("I cannot export a NULL value to SBOL!");
		}
		
//		if(null == lstURIs) {
//			lstURIs = new ArrayList<String>();
//		}

		if (objComponent instanceof Device) {

			/*
			 * Eugene Device --> SBOL DnaComponent w/ SequenceAnnotations and subComponents
			 */		
			return toDnaComponent((Device)objComponent, parent);
			
		} else if (objComponent instanceof Part) {
			
			/*
			 * Eugene Part   --> SBOL DnaComponent w/o SequenceAnnotations and subComponents
			 */			
			return toDnaComponent((Part)objComponent, parent, pos);
			
		} else {
			throw new EugeneException(
					"I cannot export the " + objComponent.getName() + " element to SBOL!");
		}
	}
	
	/*
	 * Eugene ComponentType --> SBOL DnaComponent w/o DnaSequence
	 */
	public static DnaComponent convert(ComponentType objType, DnaComponent parent, int pos)
			throws EugeneException {
		
		if (null == objType) {
			throw new EugeneException("I cannot export a NULL value to SBOL!");
		}

		if (objType instanceof PartType) {
			
			/*
			 * Eugene Part Type     -> SBOL DnaComponent w/o Sequence
			 */
			return toDnaComponent((PartType)objType, parent, pos);
			
		} else {
			throw new EugeneException("I cannot export the " + objType.getName() + " element to SBOL!");
		}
		
	}
	

	/*
	 * Device --> composite DNAComponent
	 */
	public static DnaComponent toDnaComponent(Device objDevice, DnaComponent parent) 
			throws EugeneException {
		
		DnaComponent dc = SBOLFactory.createDnaComponent();

		/*
		 * displayId
		 */
		String deviceDisplayId = objDevice.getName();
		if(null == parent) {
			dc.setDisplayId(deviceDisplayId);
		} else {
			dc.setDisplayId(parent.getDisplayId()+"_"+deviceDisplayId);
		}

		/*
		 * name
		 */
		dc.setName(objDevice.getName());
		
		/*
		 * description
		 */		
		dc.setDescription(objDevice.getName());
		
		/*
		 * URI
		 */
		String sURI = null;
		if(null != parent) {
			sURI = parent.getURI() + "/" + objDevice.getName();
		} else {
			sURI = DEFAULT_URI + "/"  + deviceDisplayId;
		}
		dc.setURI(URI.create(sURI));
		addURI(sURI);
		
//		System.out.println("DNAComponent: " + dc.getURI());
		
		/*
		 * n is a counter over the device's sub components
		 */
		int n = 1;
		
		/*
		 * nCurrentStrandIdx is a counter for the current index
		 * in the composite DNA strand
		 * it's used to calculate for SBOL bioStart and bioEnd
		 * (for SequenceAnnotations)
		 */
		int nCurrentStrandIdx = 1; 
		
		int pos = 0;
		String subComponentDisplayIds = null;
		
		StringBuilder device_seq = new StringBuilder();
		
		/*
		 * a Eugene Device is a composite component. 
		 * Hence, we iterate over the list of a device's components 
		 * and compile each Device's sub-component into an SBOL
		 * SequenceAnnotation w/ a sub-DNAComponent
		 */
		for (NamedElement c : objDevice.getComponentList()) {

			/*--------------------
			 * SequenceAnnotation 
			 *--------------------*/
			SequenceAnnotation sa = SBOLFactory.createSequenceAnnotation();
			if(null != parent) {
				sa.setURI(URI.create(parent.getURI() + "/" + objDevice.getName() + "/annotation_" + n + "/" + c.getName()));
			} else {
				sa.setURI(URI.create(DEFAULT_URI + "/" + objDevice.getName() + "/annotation_" + n + "/" + c.getName()));
			}
			addURI(sa.getURI().toASCIIString());
			
			/*
			 * if the device's sub-component is a device, then
			 * we flip it. see the flip/1 method for further documentation. 
			 */
			if(c instanceof Device && objDevice.getOrientations().get(n-1).get(0) == Orientation.REVERSE) {
				c = DeviceUtils.flipAndInvert((Device)c);
			}

			/*
			 * Strand/Orientation of the SequenceAnnotation
			 */
			if(objDevice.getOrientations().get(n-1).get(0) == Orientation.FORWARD ||
					objDevice.getOrientations().get(n-1).get(0) == Orientation.UNDEFINED) { 
				sa.setStrand(StrandType.POSITIVE);
			} else {
				sa.setStrand(StrandType.NEGATIVE);
			}
	
			/*----------------------------
			 * Sub DNAComponent
			 *----------------------------*/
			
			/*
			 * convert the device's sub component into a SBOL DNAComponent
			 * and assign it to the current SequenceAnnoation 
			 */
			DnaComponent subComponent = null;
			
			/*
			 * displayId of the sub-component
			 */
			if(null == subComponentDisplayIds) {
				subComponentDisplayIds=c.getName();
			} else {
				subComponentDisplayIds+="_"+c.getName();
			}
			
			if(c instanceof Component) {
				subComponent = Eugene2SBOL.convert((Component)c, dc, pos);
			} else if(c instanceof ComponentType) {
				subComponent = Eugene2SBOL.convert((ComponentType)c, dc, pos);
			} else {
				throw new EugeneException("Invalid!");
			}
			
			// assign the sub-DNAComponent to the
			// SequenceAnnotation
			sa.setSubComponent(subComponent);


			/*
			 *  Sequence of the sub-component
			 */ 
			if(null != subComponent.getDnaSequence() &&
					subComponent.getDnaSequence().getNucleotides().length() > 0) {
			
				/*
				 * START and END (bioStart, bioEnd)
				 */
				int start = nCurrentStrandIdx;
				int end = -1;
				if(null != subComponent.getDnaSequence() && 
						null != subComponent.getDnaSequence().getNucleotides()) {
					end = start + (subComponent.getDnaSequence().getNucleotides().length() - 1);
				}			
				sa.setBioStart(start);		
				sa.setBioEnd(end);
				
				/*
				 * adjust the current strand index appropriately
				 */
				nCurrentStrandIdx = end + 1; 
				
				 /* 
				  * if the orientation is reversed, then we have to 
				  * build the reverse complemented sequence
				  */
				if(c instanceof Part) {
					
					// in case of a part, we must do the reverse complement
					// of the sequence
					if(sa.getStrand() == StrandType.NEGATIVE) {
						
						try {
							
							// we reverse complement the sequence 
							// from the Eugene SequenceUtils class
							String rev_comp = SequenceUtils.reverseComplement(
									sa.getSubComponent().getDnaSequence().getNucleotides().toString());
							
							// and assign the reverse complemented DNA sequence 
							// to the sub-component
							sa.getSubComponent().getDnaSequence().setNucleotides(rev_comp);
							
							// we also append the reverse complemented 
							// DNA sequence to the string buffer in that we 
							// keep track of the device's sequence
							device_seq.append(rev_comp);
							
						} catch(Exception e) {
							
							throw new EugeneException("Invalid DNA sequence!");
						}
					} else {
						
						// we also append the part's DNA sequence to the string buffer 
						// in that we keep track of the device's sequence
						device_seq.append(sa.getSubComponent().getDnaSequence().getNucleotides().toString());
					}
				} else if(c instanceof Device) {
					// if the DNAComponent's sub-component is a Device, then we append the
					// sub-Device's sequence
					device_seq.append(sa.getSubComponent().getDnaSequence().getNucleotides().toString());
				}
				
			}
			
			// assign the SequenceAnnotation 
			// to the DNAComponent 
			dc.addAnnotation(sa);
			
			n++;	
			pos++;
		}

//		/*
//		 * complete the DNAComponent's displayId
//		 */
//		if(null != subComponentDisplayIds && parent != null) {
//			
//			String displayId = subComponentDisplayIds;
//
//			URI uri = null;
//			if(reusedComponents.containsKey(displayId)) {
//				uri = reusedComponents.get(displayId);
//			} else {
//				uri = URI.create("http://www.eugenecad.org/device/"+displayId);
//				reusedComponents.put(displayId, uri);
//			}
//
//			/*
//			 * complete the DNAComponent's URI
//			 */
//			dc.setDisplayId(displayId);
//			dc.setURI(uri);
//			addURI(uri.toASCIIString());
//		}

		/*
		 * map Eugene device onto the
		 * SO ``engineered component'' term
		 */
		dc.getTypes().add(soMapping("Device"));

//		String seq = device_seq.toString(); //Eugene2SBOL.deviceSequence(objDevice);
//		System.out.println(objDevice.getName()+" -> " +device_seq);
		if (null != device_seq.toString() && !device_seq.toString().isEmpty()) {
			DnaSequence dnaSeq = new DnaSequenceImpl();
			dnaSeq.setURI(URI.create(dc.getURI() + "_sequence"));
			addURI(URI.create(dc.getURI() + "_sequence").toASCIIString());
			
			dnaSeq.setNucleotides(device_seq.toString());
			
			dc.setDnaSequence(dnaSeq);
		}

		return dc;
	}
	
	
	
//	/**
//	 * The deviceSequence/1 method recursively generates the DNA sequence 
//	 * of a device based on its sub-components.
//	 * 
//	 * @param d ... the Device object
//	 * 
//	 * @return a String that represents the DNA sequence of the Device object
//	 */
//	private static String deviceSequence(Device d) {
//		
////		System.out.println("[deviceSequence] -> " + d);
//		StringBuffer sb = new StringBuffer();
//		if(null != d.getComponentList() && !d.getComponentList().isEmpty()) {
//			for(NamedElement e : d.getComponentList()) {
////				System.out.println(e);
//				if(e instanceof Device) {
//					sb.append(Eugene2SBOL.deviceSequence((Device)e));
//				} else if(e instanceof Part) {
//					PropertyValue seq = ((Part)e).getPropertyValue(EugeneConstants.SEQUENCE_PROPERTY);
//					if(null != seq) {
//						sb.append(seq.getTxt());
//					}
//				}
//				
//			}
//		}
//		return sb.toString();
//	}
	
	/*
	 */
	/**
	 * The toDnaComponent(PartType, DnaComponent) method compiles a Eugene Part Type into 
	 * an SBOL DNAComponent w/o SequenceAnnotations and w/o DNASequence.
	 * 
	 * @param objPartType   ... the Eugene PartType object that should be compiled
	 * @param parent        ... the PartType's parent
	 * 
	 * @return  an SBOL DNAComponent w/o SequenceAnnotations and w/o DNASequence
	 */
	public static DnaComponent toDnaComponent(PartType objPartType, DnaComponent parent) {
		DnaComponent c = SBOLFactory.createDnaComponent();
		
		/*
		 * displayId
		 */
		if(null != parent) {
			c.setDisplayId(parent.getDisplayId()+"_"+objPartType.getName());
		} else {
			c.setDisplayId(objPartType.getName());
		}

		/*
		 * description
		 */
		c.setDescription(objPartType.getName());
		
		/*
		 * URI
		 */
		if(null != parent) {
			c.setURI(URI.create(parent.getURI()+"/"+objPartType.getName()));
			addURI(URI.create(parent.getURI()+"/"+objPartType.getName()).toASCIIString());
		} else {
			c.setURI(URI.create(DEFAULT_URI + "/" + objPartType.getName()));
			addURI(URI.create(DEFAULT_URI + "/" + objPartType.getName()).toASCIIString());
		}

		/*
		 * the DNAComponent's type
		 * i.e. the part type's name mapped to an SO term
		 */
		c.addType(soMapping(objPartType.getName()));
		
		return c;
	}
	
	/*
	 * Part --> basic DnaComponent
	 */
	public static DnaComponent toDnaComponent(Part objPart, DnaComponent parent, int pos) {
		
		DnaComponent c = SBOLFactory.createDnaComponent();
//		System.out.println("[toDnaComponent(Part)] -> " + objPart);

		/*
		 * Part Type
		 */
		c.addType(soMapping(objPart.getType().getName()));

		/*
		 * name
		 */
		c.setName(objPart.getName());		
		
		/*
		 * description
		 */
		PropertyValue description =  objPart.getPropertyValue(EugeneConstants.DESCRIPTION_PROPERTY);
		if(null != description && !description.getValue().isEmpty()) {
			c.setDescription(objPart.getPropertyValue(EugeneConstants.DESCRIPTION_PROPERTY).getValue());
		} else {
			c.setDescription(objPart.getType().getName());
		}
		
		/*
		 * displayId
		 */
		String uuid = UUID.randomUUID().toString();
		PropertyValue displayId = objPart.getPropertyValue(EugeneConstants.DISPLAY_ID_PROPERTY);
		if(null != displayId && !displayId.getValue().isEmpty()) {
			c.setDisplayId(objPart.getPropertyValue(EugeneConstants.DISPLAY_ID_PROPERTY).getValue() + "/" + uuid);
		} else {
			if(null != parent) {
				c.setDisplayId(parent.getDisplayId() + "_" + "pos_" + pos + "_" + objPart.getName());
			} else {
				c.setDisplayId("pos_" + pos + "_" + objPart.getName());
			}
		}

		/*
		 * URI
		 */
		URI partURI = URI.create(DEFAULT_URI + "/parts/" + objPart.getName());
		if(null != parent && pos != -1) {
			partURI = URI.create(parent.getURI() + "/pos_" + pos + "/" + objPart.getName());
		}

		PropertyValue uri = objPart.getPropertyValue(EugeneConstants.URI_PROPERTY);
		if(null != uri && !uri.getValue().isEmpty()) {
			if(null != parent && pos != -1) {
				partURI = URI.create(objPart.getPropertyValue(EugeneConstants.URI_PROPERTY).getValue() + "/" + uuid);
			} else {
				partURI = URI.create(objPart.getPropertyValue(EugeneConstants.URI_PROPERTY).getValue());
			}
		}
		c.setURI(partURI);
		addURI(partURI.toASCIIString());
		
		/*
		 *  SEQUENCE information			
		 */
//		System.out.println("[Eugene2SBOL] -> " + objPart.getName()+" -> "+objPart.getPropertyValue(EugeneConstants.SEQUENCE_PROPERTY));
		if(null != objPart.getPropertyValue(EugeneConstants.SEQUENCE_PROPERTY)) {
			DnaSequence seq = SBOLFactory.createDnaSequence();
			seq.setURI(URI.create(partURI+"_sequence"));
			addURI(URI.create(partURI+"_sequence").toASCIIString());
			
			seq.setNucleotides(objPart.getPropertyValue(EugeneConstants.SEQUENCE_PROPERTY).getValue().toLowerCase());
			c.setDnaSequence(seq);
		}
		return c;
	}
	
	
	/*
	 * Part --> basic DnaComponent
	 */
	public static DnaComponent toDnaComponent(PartType objType, DnaComponent parent, int pos) {
		
		DnaComponent c = SBOLFactory.createDnaComponent();

		/*
		 * Part Type
		 */
		c.addType(soMapping(objType.getName()));

		/*
		 * name
		 */
		c.setName(objType.getName());		
		
		/*
		 * description
		 */
		c.setDescription(objType.getName());
		
		/*
		 * displayId
		 */
		if(null != parent) {
			c.setDisplayId(parent.getDisplayId() + "_" + "pos_" + pos + "_" + objType.getName());
		} else {
			c.setDisplayId("pos_" + pos + "_" + objType.getName());
		}

		/*
		 * URI
		 */
		URI partURI = URI.create(DEFAULT_URI + "/types/" + objType.getName());
		if(null != parent && pos != -1) {
			partURI = URI.create(parent.getURI() + "/pos_" + pos + "/" + objType.getName());
		}
		c.setURI(partURI);
		addURI(partURI.toASCIIString());
		
		return c;
	}

	
	private static URI soMapping(String s) {

		if ("Five_Prime_UTR".equals(s)) {
			return SequenceOntology.FIVE_PRIME_UTR;
		} else if ("CDS".equals(s)) {
			return SequenceOntology.CDS;
		} else if("RBS".equals(s)) {
			try {
				return new URI("http://purl.obolibrary.org/obo/SO_0000139");
			} catch(Exception e) {}
		} else if ("Insulator".equals(s)) {
			return SequenceOntology.INSULATOR;
		} else if ("Operator".equals(s)) {
			return SequenceOntology.OPERATOR;
		} else if ("Origin_of_Replication".equals(s)) {
			return SequenceOntology.ORIGIN_OF_REPLICATION;
		} else if ("Primiter_Binding_Site".equals(s)) {
			return SequenceOntology.PRIMER_BINDING_SITE;
		} else if ("Promoter".equals(s)) {
			return SequenceOntology.PROMOTER;
		} else if ("Restriction_Enzyme_Recognition_Site".equals(s)) {
			return SequenceOntology.RESTRICTION_ENZYME_RECOGNITION_SITE;
		} else if ("Terminator".equals(s)) {
			return SequenceOntology.TERMINATOR;
		} else if("Device".equals(s)) {
			/*
			 * SO Term: Engineered Forein Region
			 */
			try {
				return new URI("http://purl.obolibrary.org/obo/SO_0000805");
			} catch(Exception e) {}
		}

		return SequenceOntology.CDS;
	}

	public static void addURI(String s) {
		if (null == lstURIs) {
			lstURIs = new ArrayList<String>();
		}

		if (!lstURIs.contains(s)) {
			lstURIs.add(s);
		}
	}

	public static void addDisplayId(String s) {
		if (null == lstURIs) {
			lstURIs = new ArrayList<String>();
		}

		if (!lstURIs.contains(s)) {
			lstURIs.add(s);
		}
	}

}
