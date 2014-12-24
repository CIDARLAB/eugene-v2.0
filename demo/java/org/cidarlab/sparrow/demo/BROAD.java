package org.cidarlab.sparrow.demo;

import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.Repository;
import org.cidarlab.sparrow.exception.SparrowException;

public class BROAD {

	private Sparrow sparrow;
	
	public BROAD() 
			throws SparrowException {
		this.sparrow = new Sparrow();
	}

	public static void main(String[] args) 
			throws SparrowException {
		BROAD broad = new BROAD();
		
		broad.populateFacts();
		
		broad.printFacts();
	}
	
	public void populateFacts() 
			throws SparrowException {
		
		/*
		 * import data from 
		 * http://parts.igem.org/Yeast
		 * 
		 * - Promoters
		 * - Protein Coding Sequences (Genes)
		 * - Terminators
		 */
		this.importPromoters();
	}
	
	private void importPromoters() 
			throws SparrowException {
		try {
			this.importParts(new String[]{
				"BBa_J63005", "BBa_K319005", "BBa_K105027", "BBa_K105028", 
				"BBa_K105029", "BBa_K105030", "BBa_K105031", "BBa_K122000"});
		} catch(SparrowException spe) {
			throw new SparrowException(spe.getMessage());
		}
	}
	
	private void importParts(String[] parts) 
		throws SparrowException {
		for(int i=0; i<parts.length; i++) {
			System.out.println("importing "+parts[i]);
			this.sparrow.importFrom(parts[i], Repository.iGEM);
		}
	}
	
	public void printFacts() {
		System.out.println(this.sparrow.getFactCount());
		//this.sparrow.printFacts();
	}

}
