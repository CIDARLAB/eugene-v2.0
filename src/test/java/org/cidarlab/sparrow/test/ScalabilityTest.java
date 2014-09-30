package org.cidarlab.sparrow.test;

import org.cidarlab.sparrow.exception.SparrowException;

public class ScalabilityTest 
		implements SparrowTest {
	
	private int NR_OF_CLIENTS;
	private int NR_OF_REQUESTS;
	private int NR_OF_FACTS;
	private int NR_OF_RULES;
	
	public ScalabilityTest(
			int NR_OF_CLIENTS, int NR_OF_REQUESTS, int NR_OF_FACTS, int NR_OF_RULES) 
					throws SparrowException {
		
		this.NR_OF_CLIENTS = NR_OF_CLIENTS;
		this.NR_OF_REQUESTS = NR_OF_REQUESTS;
		this.NR_OF_FACTS = NR_OF_FACTS;
		this.NR_OF_RULES = NR_OF_RULES;
	}

	public void test() 
			throws SparrowException {
	}
	
}
