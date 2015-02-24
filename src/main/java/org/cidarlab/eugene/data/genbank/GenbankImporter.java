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


package org.cidarlab.eugene.data.genbank;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.compound.NucleotideCompound;
import org.biojava3.core.sequence.features.FeatureInterface;
import org.biojava3.core.sequence.io.GenbankReaderHelper;
import org.biojava3.core.sequence.template.AbstractSequence;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;

/**
 * 
 * @author Ernst Oberortner
 */
public class GenbankImporter {

	private SymbolTable symbols;
	
	public GenbankImporter(SymbolTable symbols) {
		this.symbols = symbols;
	}
	
	public String getGenBank(String sPartName) throws Exception {
		URL url = new URL(
				"http://cambridgeigem.org/gbdownload/BBa_"+ sPartName + ".gb");
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
				
		try {
			Map<String, DNASequence> dnaSequences = GenbankReaderHelper.readGenbankDNASequence( dnaFile );
			for (DNASequence sequence : dnaSequences.values()) {
				
				if(sequence.getFeatures().size() > 1) {
					return this.toDevice(sequence.getFeatures());
				} else {
			    	System.out.println( "IT'S A PART! " + sequence.getSequenceAsString() );
			    	// TODO
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
