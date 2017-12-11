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

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.features.FeatureInterface;
import org.biojava.nbio.core.sequence.io.GenbankReaderHelper;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;

/**
 * The GenbankImporter class provides methods to 
 * import biological data from the GenBank format.
 * 
 * !!!! This class is not fully implemented yet !!!!
 * 
 * The GenbankImporter class supports 
 * -- the import of a part from a web service that 
 *    has been developed by the Cambridge iGEM team.
 * -- the import of biological data (either device or part) 
 *    from a GenBank file
 * 
 * @author Ernst Oberortner
 */
public class GenbankImporter {

	private SymbolTable symbols;
	
	/**
	 * Constructor
	 * requires a reference to the symbol tables of the 
	 * Eugene Interpreter
	 * 
	 * @param symbols
	 */
	public GenbankImporter(SymbolTable symbols) {
		this.symbols = symbols;
	}
	
	/**
	 * The getGenBank(String) method reads a part from a web service 
	 * developed by the Cambridge iGEM team.
	 * URL: http://cambridgeigem.org/gbdownload/BBa_<PartName>.gb"
	 * 
	 * @param sPartName   ... the name of the part to be imported
	 * 
	 * @return The GenBank representation of the desired part from the web service
	 * 
	 * @throws EugeneException
	 */
	public String getGenBank(String sPartName) 
			throws EugeneException {
		try {
			URL url = new URL(
					"http://cambridgeigem.org/gbdownload/BBa_"+ sPartName + ".gb");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			
			// we read the URL line-by-line
			String inputLine;
			StringBuilder sb = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				
				// adding each line to a string buffer
				sb.append(inputLine).append(System.getProperty("line.separator"));
			}
			in.close();
	
			// lastly, we return the string buffer as string
			return sb.toString();
			
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}
	}

	/**
	 * The importGenbank(String) method reads the biological data 
	 * from a GenBank file specified by the given filename.
	 * To parse the GenBank file, Eugene utilizes the BioJava library.
	 * 
	 * @param filename   ... the filename of the GenBank file
	 * @return ... a NamedElement object that represents the content of 
	 * the GenBank file in the Eugene DOM
	 * 
	 * @throws EugeneException
	 */
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
	
	/**
	 * The private toDevice(List) method converts a list of features 
	 * (i.e. a composite DNA) to a Eugene Device object.
	 * 
	 * @param lof  ... list of features (BioJava specific)
	 * 
	 * @return ... a Eugene Device object
	 * @throws EugeneException
	 */
	private Device toDevice( List<FeatureInterface<AbstractSequence<NucleotideCompound>, NucleotideCompound>> lof) 
			throws EugeneException {

		// iterate over the features
	    Iterator<FeatureInterface<AbstractSequence<NucleotideCompound>, NucleotideCompound>> it = lof.iterator();
	    while(it.hasNext()) {
	    	FeatureInterface<AbstractSequence<NucleotideCompound>, NucleotideCompound> f = it.next();
	    	
	    	try {
	    		
	    		// check if there is a part type in the symbol tables
	    		NamedElement e = this.symbols.get(f.getType());
	    		
	    		
	    		PartType pt = null;
	    		if(null != e) {
	    			if(e instanceof PartType) {
	    				pt = (PartType)e;
	    			} else {
	    				throw new EugeneException("Feature type of " + f.getType() +" is identical to "+e+"!");
	    			}
	    		} else {
	    			// no part type yet,
	    			// i.e. we create one
	    			pt = new PartType(f.getType());
	    			// and store it in the symbol tables
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
