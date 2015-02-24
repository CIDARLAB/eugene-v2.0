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

package org.cidarlab.sparrow.exporters.mapping;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.sparrow.constants.SBOLConstants;
import org.cidarlab.sparrow.exception.SparrowException;
import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.util.SequenceOntology;
//

public class Sparrow2SBOL {

	public static ArrayList<String> lstURIs;
	private static final String DEFAULT_URI = "http://www.eugenecad.org";
	public static Map<String, URI> reusedComponents;
	
	/*
	 * Eugene ComponentArray -> SBOL Collection
	 */
	public static Collection convert(List<Component> list, String sURI)
			throws SparrowException {
		
		String name = UUID.randomUUID().toString();
		
		lstURIs = new ArrayList<String>();
		reusedComponents = new HashMap<String, URI>();
		
		Collection col = SBOLFactory.createCollection();
		col.setDescription(name);
		col.setDisplayId(name);

		try {
			col.setURI(URI.create(DEFAULT_URI + "/" + name));
		} catch (Exception e) {
			throw new SparrowException(e.toString());
		}

		for(Component c:list) {
			col.addComponent(
					convert(c, null));
		}

		lstURIs = null;
		reusedComponents = null;
		
		return col;
	}

	public static DnaComponent convert(Component objComponent, DnaComponent parent)
			throws SparrowException {
		
		if (null == objComponent) {
			throw new SparrowException("I cannot export a NULL value to SBOL!");
		}

		// NOT SUPPORTED YET!
//		if (objComponent instanceof Device) {
//
//			/*
//			 * Eugene Device -> composite SBOL DnaComponent
//			 */
//			
//			return toDnaComponent((Device)objComponent, parent);
//			
//		} else if (objComponent instanceof PartType) {
//			
//			/*
//			 * Eugene Part Type -> SBOL DnaComponent
//			 */
//			
//			return toDnaComponent((PartType)objComponent, parent);
//			
//		} else 
			
		if (objComponent instanceof Part) {
			
			/*
			 * Eugene Part      -> SBOL DnaComponent
			 */
			
			return toDnaComponent((Part)objComponent, parent);
			
		} else {
			throw new SparrowException("I cannot export the " + objComponent.getName() + " element to SBOL!");
		}
		
	}

//	/*
//	 * Device --> composite DNAComponent
//	 */
//	public static DnaComponent toDnaComponent(Device objDevice, DnaComponent parent) 
//			throws SparrowException {
//		
//		DnaComponent dc = SBOLFactory.createDnaComponent();
//
//		/*
//		 * displayId
//		 */
//		String deviceDisplayId = objDevice.getName();
//		dc.setDisplayId(deviceDisplayId);
//
//		/*
//		 * the composite DNAComponent's description
//		 */		
//		dc.setDescription(objDevice.getName());
//		
//		/*
//		 * the DNAComponent's URI
//		 */
//		String sURI = null;
//		if(null != parent) {
//			sURI = parent.getURI() + "/" + objDevice.getName();
//		} else {
//			sURI = DEFAULT_URI + "/"  + deviceDisplayId;
//		}
//		dc.setURI(URI.create(sURI));
//
//		/*
//		 * n is a counter over the device's sub components
//		 */
//		int n = 1;
//		
//		/*
//		 * nCurrentStrandIdx is a counter for the current index
//		 * in the composite DNA strand
//		 * it's used to calculate for SBOL bioStart and bioEnd
//		 * (for SequenceAnnotations)
//		 */
//		int nCurrentStrandIdx = 1; 
//		
//		String subComponentDisplayIds = null;
//		for (Component c : objDevice.getComponents()) {
//
//			if(null == subComponentDisplayIds) {
//				subComponentDisplayIds=c.getName();
//			} else {
//				subComponentDisplayIds+="_"+c.getName();
//			}
//			
//			/*
//			 * create a SequenceAnnotation for each sub component
//			 */
//			SequenceAnnotation sa = SBOLFactory.createSequenceAnnotation();
//			if(null != parent) {
//				sa.setURI(URI.create(parent.getURI() + "/" + objDevice.getName()+"/annotation_"+n));
//			} else {
//				sa.setURI(URI.create(DEFAULT_URI + "/" + objDevice.getName()+"/annotation_"+n));
//			}
//			
//			/*
//			 * convert the device's sub component into a SBOL DNAComponent
//			 * and assign it to the current SequenceAnnoation 
//			 */
//			DnaComponent subComponent = Sparrow2SBOL.convert(c, dc);
//			sa.setSubComponent(subComponent);
//
//			/*
//			 * START and END (bioStart, bioEnd)
//			 */
//			int start = nCurrentStrandIdx;
//			int end = -1;
//			if(null != subComponent.getDnaSequence() && 
//					null != subComponent.getDnaSequence().getNucleotides()) {
//				end = start + (subComponent.getDnaSequence().getNucleotides().length() - 1);
//			}			
//			sa.setBioStart(start);		
//			sa.setBioEnd(end);
//
//			/*
//			 * STRAND / DIRECTIONALITY
//			 */
//			if(objDevice.getDirections()[n-1] == '+') {
//				sa.setStrand(StrandType.POSITIVE);
//			} else {
//				sa.setStrand(StrandType.NEGATIVE);
//			}
//			dc.addAnnotation(sa);
//			
//			n++;	
//			
//			/*
//			 * adjust the current strand index appropriately
//			 */
//			nCurrentStrandIdx = end + 1;				
//		}
//
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
//		}
//
//		/*
//		 * map Eugene device onto the
//		 * SO ``engineered component'' term
//		 */
//		dc.getTypes().add(soMapping("Device"));
//
//
//		String seq = objDevice.toSequence();
//		if (null != seq && !seq.isEmpty()) {
//			DnaSequence dnaSeq = new DnaSequenceImpl();
//			dnaSeq.setURI(URI.create(dc.getURI() + "_sequence"));
//			dnaSeq.setNucleotides(seq.toLowerCase());
//			dc.setDnaSequence(dnaSeq);
//		}
//
//		return dc;
//	}
	
//	/*
//	 * Part Type --> basic DNAComponent
//	 */
//	public static DnaComponent toDnaComponent(PartType objPartType, DnaComponent parent) {
//		DnaComponent c = SBOLFactory.createDnaComponent();
//		
//		c.setDescription(objPartType.getName());
//		
//		/*
//		 * displayId
//		 */
//		if(null != parent) {
//			c.setDisplayId(parent.getDisplayId()+"_"+objPartType.getName());
//		} else {
//			c.setDisplayId(objPartType.getName());
//		}
//
//		/*
//		 * URI
//		 */
//		if(null != parent) {
//			c.setURI(URI.create(parent.getURI()+"/"+objPartType.getName()));
//		} else {
//			c.setDisplayId(objPartType.getName());
//		}
//
//		/*
//		 * the DNAComponent's type
//		 * i.e. the part type's name mapped to an SO term
//		 */
//		c.addType(soMapping(objPartType.getName()));
//		
//		return c;
//	}
	
	/*
	 * Part --> basic DnaComponent
	 */
	public static DnaComponent toDnaComponent(Part objPart, DnaComponent parent) {
		
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
		PropertyValue description =  objPart.getPropertyValue(SBOLConstants.DESCRIPTION_PROPERTY);
		if(null != description && !description.getValue().isEmpty()) {
			c.setDescription(objPart.getPropertyValue(SBOLConstants.DESCRIPTION_PROPERTY).getValue());
		} else {
			c.setDescription(objPart.getType().getName());
		}
		
		/*
		 * displayId
		 */
		PropertyValue displayId = objPart.getPropertyValue(SBOLConstants.DISPLAY_ID_PROPERTY);
		if(null != displayId && !displayId.getValue().isEmpty()) {
			c.setDisplayId(objPart.getPropertyValue(SBOLConstants.DISPLAY_ID_PROPERTY).getValue());
		} else {
			c.setDisplayId(objPart.getName());
		}

		/*
		 * URI
		 */
		URI partURI = URI.create(DEFAULT_URI + "/parts/" + objPart.getName());

		PropertyValue uri = objPart.getPropertyValue(SBOLConstants.URI_PROPERTY);
		if(null != uri && !uri.getValue().isEmpty()) {
			partURI = URI.create(objPart.getPropertyValue(SBOLConstants.URI_PROPERTY).getValue());
		}
		c.setURI(partURI);
		
		/*
		 *  SEQUENCE information			
		 */
		if(null != objPart.getPropertyValue(SBOLConstants.SEQUENCE_PROPERTY)) {
			DnaSequence seq = SBOLFactory.createDnaSequence();
			seq.setURI(URI.create(partURI+"_sequence"));
			
			seq.setNucleotides(objPart.getPropertyValue(SBOLConstants.SEQUENCE_PROPERTY).getValue().toLowerCase());
			c.setDnaSequence(seq);
		}
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

}
