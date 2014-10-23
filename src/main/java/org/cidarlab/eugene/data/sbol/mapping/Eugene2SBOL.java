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
import java.util.Map;
import java.util.UUID;

import org.biojava.bio.seq.DNATools;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.eugene.exception.EugeneException;
import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.StrandType;
import org.sbolstandard.core.impl.DnaSequenceImpl;
import org.sbolstandard.core.util.SequenceOntology;


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

		lstURIs = new ArrayList<String>();
		reusedComponents = new HashMap<String, URI>();
		
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
			throw new EugeneException("I cannot export the " + objComponent.getName() + " element to SBOL!");
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
		dc.setDisplayId(deviceDisplayId);

		/*
		 * the composite DNAComponent's description
		 */		
		dc.setDescription(objDevice.getName());
		
		/*
		 * the DNAComponent's URI
		 */
		String sURI = null;
		if(null != parent) {
			sURI = parent.getURI() + "/" + objDevice.getName();
		} else {
			sURI = DEFAULT_URI + "/"  + deviceDisplayId;
		}
		dc.setURI(URI.create(sURI));
		addURI(sURI);

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
		for (NamedElement c : objDevice.getComponents().get(0)) {

			System.out.println(objDevice+" -> " +objDevice.getComponentList());
			if(null == subComponentDisplayIds) {
				
				subComponentDisplayIds=c.getName();
			} else {
				subComponentDisplayIds+="_"+c.getName();
			}
			
			/*
			 * create a SequenceAnnotation for each sub component
			 */
			SequenceAnnotation sa = SBOLFactory.createSequenceAnnotation();
			if(null != parent) {
				sa.setURI(URI.create(parent.getURI() + "/" + objDevice.getName()+"/"+c.getName()+"_annotation_"+n));
				addURI(URI.create(parent.getURI() + "/" + objDevice.getName()+"/"+c.getName()+"_annotation_"+n).toASCIIString());

			} else {
				sa.setURI(URI.create(DEFAULT_URI + "/" + objDevice.getName()+"/"+c.getName()+"_annotation_"+n));
				addURI(URI.create(DEFAULT_URI + "/" + objDevice.getName()+"/"+c.getName()+"_annotation_"+n).toASCIIString());
			}
			
			/*
			 * convert the device's sub component into a SBOL DNAComponent
			 * and assign it to the current SequenceAnnoation 
			 */
			DnaComponent subComponent = null;
			
			if(c instanceof Component) {
				subComponent = Eugene2SBOL.convert((Component)c, dc, pos++);
			} else if(c instanceof ComponentType) {
				subComponent = Eugene2SBOL.convert((ComponentType)c, dc, pos++);
			} else {
				throw new EugeneException("Invalid!");
			}
			sa.setSubComponent(subComponent);

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
				 * STRAND / DIRECTIONALITY
				 */
				if(objDevice.getOrientations().get(n-1).get(0) == Orientation.FORWARD ||
						objDevice.getOrientations().get(n-1).get(0) == Orientation.UNDEFINED) { 
					sa.setStrand(StrandType.POSITIVE);
				} else {
					// here, we need to reverse complement the sequence
					// and we use BioJava for this
					// see http://biojava.org/wiki/BioJava:Cookbook:Sequence:Reverse
					
					try {
						sa.getSubComponent().getDnaSequence().setNucleotides(
								DNATools.reverseComplement(
										DNATools.createDNA(
												sa.getSubComponent().getDnaSequence().getNucleotides())).seqString());
					} catch(Exception e) {
						e.printStackTrace();
						throw new EugeneException("Invalid DNA sequence!");
					}
					
					sa.setStrand(StrandType.NEGATIVE);
				}
				
			}

			dc.addAnnotation(sa);
			
			n++;				
		}

		/*
		 * complete the DNAComponent's displayId
		 */
		if(null != subComponentDisplayIds && parent != null) {
			
			String displayId = subComponentDisplayIds;

			URI uri = null;
			if(reusedComponents.containsKey(displayId)) {
				uri = reusedComponents.get(displayId);
			} else {
				uri = URI.create("http://www.eugenecad.org/device/"+displayId);
				reusedComponents.put(displayId, uri);
			}

			/*
			 * complete the DNAComponent's URI
			 */
			dc.setDisplayId(displayId);
			dc.setURI(uri);
			addURI(uri.toASCIIString());
		}

		/*
		 * map Eugene device onto the
		 * SO ``engineered component'' term
		 */
		dc.getTypes().add(soMapping("Device"));


		String seq = objDevice.toSequence();
		if (null != seq && !seq.isEmpty()) {
			DnaSequence dnaSeq = new DnaSequenceImpl();
			dnaSeq.setURI(URI.create(dc.getURI() + "_sequence"));
			addURI(URI.create(dc.getURI() + "_sequence").toASCIIString());
			dnaSeq.setNucleotides(seq.toLowerCase());
			dc.setDnaSequence(dnaSeq);
		}

		return dc;
	}
	
	/*
	 * Part Type --> basic DNAComponent
	 */
	public static DnaComponent toDnaComponent(PartType objPartType, DnaComponent parent) {
		DnaComponent c = SBOLFactory.createDnaComponent();
		
		c.setDescription(objPartType.getName());
		
		/*
		 * displayId
		 */
		if(null != parent) {
			c.setDisplayId(parent.getDisplayId()+"_"+objPartType.getName());
		} else {
			c.setDisplayId(objPartType.getName());
		}

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
		//System.out.println(s);
		return SequenceOntology.CDS;
	}

//	public static Collection convert(
//			org.cidarlab.eugene.dom.collection.EugeneCollection objCollection) {
//		Collection c = new CollectionImpl();
//
//		return c;
//	}

	public static void addURI(String s) {
		if (null == lstURIs) {
			lstURIs = new ArrayList<String>();
		}

		if (!lstURIs.contains(s)) {
			lstURIs.add(s);
		} else {
			System.out.println(s + " appears several times");
		}
	}
}
