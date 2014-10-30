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

package org.cidarlab.eugene.data.genbank;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.biojava.bio.seq.Feature;
import org.biojava.bio.seq.Sequence;
import org.biojava.bio.seq.SequenceIterator;
import org.biojava.bio.seq.io.SeqIOTools;
import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.ProteinSequence;
import org.biojava3.core.sequence.compound.AminoAcidCompound;
import org.biojava3.core.sequence.compound.AminoAcidCompoundSet;
import org.biojava3.core.sequence.compound.DNACompoundSet;
import org.biojava3.core.sequence.compound.NucleotideCompound;
import org.biojava3.core.sequence.features.FeatureInterface;
import org.biojava3.core.sequence.io.GenbankReaderHelper;
import org.biojava3.core.sequence.template.AbstractSequence;
import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;


public class GenbankImporter {

	private SymbolTable symbols;
	
	public GenbankImporter(SymbolTable symbols) {
		this.symbols = symbols;
	}
	
//	public Part importPart(PartType objPartType, String sPartName)
//			throws Exception {
//
//		// load the genbank file
//		String sGenBank = getGenBank(sPartName);
//
//		// convert the string to
//		InputStream is = new ByteArrayInputStream(sGenBank.getBytes());
//
//		// now, use BioJava's feature to read the GenBank data
//		String sPartSeq = readGenBank(new BufferedReader(new InputStreamReader(
//				is)));
//
//		Part objPart = new Part(objPartType, sPartName);
//
//		PropertyValue pv = new PropertyValue(
//				objPart.getProperty(EugeneConstants.SEQUENCE_PROPERTY));
//		pv.setTxt(sPartSeq);
//		objPart.setPropertyValue(
//				objPart.getProperty(EugeneConstants.SEQUENCE_PROPERTY), 
//				pv);
//
//		return objPart;
//	}

	private String getGenBank(String sPartName) throws Exception {
		URL url = new URL("http://cambridgeigem.org/gbdownload/BBa_"
				+ sPartName + ".gb");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String inputLine;
		StringBuilder sb = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine).append(System.getProperty("line.separator"));
		}
		in.close();

		return sb.toString();
	}

	public NamedElement importGenbank(String filename) 
			throws EugeneException {
		File dnaFile = new File(filename);
		
//		 BufferedReader br = null;
//		 
//		 try {
//		 
//			 br = new BufferedReader(
//					 new FileReader(new File("/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/data/genbank/pJGIL023_colE1-amp-lacUV5_lox.gb")));
//		 
//		 } catch (FileNotFoundException ex) {
//		    	throw new EugeneException("file not found! " + Eugene.ROOT_DIRECTORY+"/"+filename);
//		 }
//		 
//			//read the GenBank File
//			SequenceIterator sequences = 
//			    		SeqIOTools.readGenbank(br);
//			 
//			//iterate through the sequences
//			while(sequences.hasNext()){
//			try {
//			 
//			Sequence seq = sequences.nextSequence();
//			
//			if(seq.countFeatures() > 1) {
//			} else {
//				// here, we return a Part
//			        }
//			        
//			        
//			        System.out.println(seq.toString());
//			 
//			      } catch (Exception ex) {
//				    	throw new EugeneException("file not found! " + filename);
//			  }
//		}
		
		try {
			Map<String, DNASequence> dnaSequences = GenbankReaderHelper.readGenbankDNASequence( dnaFile );
			for (DNASequence sequence : dnaSequences.values()) {
				
				if(sequence.getFeatures().size() > 1) {
					Device d = this.toDevice(sequence.getFeatures());
				} else {
			    	System.out.println( "IT'S A PART! " + sequence.getSequenceAsString() );
				}
			}
		} catch(Exception e) {
			throw new EugeneException(e.getLocalizedMessage());
		}
		
		return null;
	}
	
	private Device toDevice( List<FeatureInterface<AbstractSequence<NucleotideCompound>, NucleotideCompound>> lof) 
			throws EugeneException {
		// here, we return a Device
	    Iterator<FeatureInterface<AbstractSequence<NucleotideCompound>, NucleotideCompound>> it = lof.iterator();
	    while(it.hasNext()) {
	    	FeatureInterface<AbstractSequence<NucleotideCompound>, NucleotideCompound> f = it.next();
	    	
	    	try {
	    		NamedElement e = this.symbols.get(f.getType());
	    		PartType pt = null;
	    		if(null != e) {
	    			if(e instanceof PartType) {
	    				pt = (PartType)e;
	    			} else {
	    				throw new EugeneException("Feature type of " + f.getType() +" is identical to "+e+"!");
	    			}
	    		} else {
	    			pt = new PartType(f.getType());
	    			this.symbols.put(pt);
	    		} 
	    		
	    		System.out.println(f.toString());
//	    		Part p = new Part(pt, f.getTypeTerm().toString());
//	    		p.setSequence(f.getSequence().toString());
//	    		
//	    		System.out.println("IMPORTED PART -> " + p);
	    	} catch(EugeneException ee) {
	    		throw new EugeneException(ee.getLocalizedMessage());
	    	}
	    	System.out.println(f.getType()+" -> "+f.toString());
	    }
	    return null;
	}
}
