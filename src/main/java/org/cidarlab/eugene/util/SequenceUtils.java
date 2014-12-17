package org.cidarlab.eugene.util;

import org.biojava.bio.seq.DNATools;
import org.cidarlab.eugene.dom.Component;
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
	
	

	public static String toSequence(Component c) 
			throws EugeneException {
		/*
		 * first, we verify if the component has a sequence
		 */
		return c.getSequence();
//		if(c instanceof Device) {
//			return ((Device)c).getSequence();
//		} else if(c instanceof Part) {
//			return ((Part)c).getSequence();
//		}
		
//		return null;
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
		
		if(null==seq || seq.isEmpty()) {
			throw new EugeneException("Invalid DNA sequence!");
		}
		
		String tmpSeq = new String(seq);
		if(seq.startsWith("\"") && seq.endsWith("\"")) {
			tmpSeq = seq.substring(1, seq.length()-1);
		}
		
		// The BioJava library somehow converts a DNA sequence 
		// into a lower case sequence. Hence, we 
		// check if the original sequence is in upper case		
		boolean bUpper = SequenceUtils.isUpperCase(seq);
		
		try {
			String revComp = DNATools.reverseComplement(
									DNATools.createDNA(tmpSeq)).seqString();
			
			// rebuild old DNA sequence w/ upper case characters
			if(bUpper) {
				return revComp.toUpperCase();
			}
			
			return revComp;
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	/**
	 * The isUpperCase(String) method evaluates if a given 
	 * String contains only upper case characters.
	 * 
	 * @param str   ... the String to be evaluated 
	 * @return true ... if the String contains only upper case characters
	 *        false ... otherwise
	 */
	public static boolean isUpperCase(String str) {
		for (char c : str.toCharArray()) {
		    if (!Character.isUpperCase(c)) {
		    	return false;
		    }
		}
		return true;
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
