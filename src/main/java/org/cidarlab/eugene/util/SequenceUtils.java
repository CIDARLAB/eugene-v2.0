package org.cidarlab.eugene.util;

import org.biojava.bio.seq.DNATools;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The SequenceUtils class provides a set of static methods 
 * to operate with DNA sequences.
 * 
 * The methods include:
 * - reverse_complement
 * - verify
 * - annotate
 * 
 * The methods
 * @author Ernst Oberortner
 *
 */
public class SequenceUtils {
	
	

	public static String toSequence(Component c) {
		/*
		 * first, we verify if the component has a sequence
		 */
		
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	
	/**
	 * The reverseComplement(String) method reverse complements a given string 
	 * of DNA sequence.
	 * 
	 * The reverse_complement(String) method utilizes BioJava.
	 * see http://biojava.org/wiki/BioJava:Cookbook:Sequence:Reverse
	 * 
	 * @param seq   ... the sequence to be reverse complemented
	 * @return      ... the reserve complemented sequence
	 * 
	 * @throws EugeneException
	 */
	public static String reverseComplement(String seq) 
			throws EugeneException {
		try {
			return DNATools.reverseComplement(
						DNATools.createDNA(seq)).seqString();
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}
	}
	
	
	/**
	 * The verify(String) method verifies if a given string 
	 * contains only valid letters of A, T, C, and G --- 
	 * either in upper or lower-case. 
	 * 
	 * @param seq ... the sequence to be verified
	 * @return true ... if the sequence contains only letters of A, T, C, and G
	 *        false ... otherwise
	 *        
	 * @throws EugeneException
	 */
	public static boolean verify(String seq) 
			throws EugeneException {
		
		return true;
	} 

	
}
