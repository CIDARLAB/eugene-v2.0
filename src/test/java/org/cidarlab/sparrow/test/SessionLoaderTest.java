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

package org.cidarlab.sparrow.test;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;
import org.cidarlab.sparrow.rule.EugeneRule;
import org.cidarlab.sparrow.rule.Predicate;

/**
 * 
 * @author Ernst Oberortner
 */
public class SessionLoaderTest 
		implements SparrowTest {

	private Sparrow sparrow;
	private String sessionId;
	private long factCount;
	
	public SessionLoaderTest(String sessionId, long factCount) 
			throws SparrowException {
		
		this.sessionId = sessionId;
		this.factCount = factCount;
		
		try {
			this.sparrow = new Sparrow(sessionId);
		} catch(SparrowException se) {
			throw new SparrowException("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	
	public void test() 
			throws SparrowException {
		
		// resume the session
		// i.e. let Sparrow load it
		this.resume();

		// now, compare the two sizes
		if(this.sparrow.getFactCount() != this.factCount) {
			throw new SparrowException("The number of facts does not match!");
		}
	}
	
	private void resume() 
			throws SparrowException {
		this.sparrow.resume("./sessions/"+this.sessionId);
	}
	
	public void printComponents() {
		System.out.println(this.sparrow.getFactCount());
		this.sparrow.printFacts();
	}
	
	public void queryAllPromoters() {
		/*
		 * let's get all promoters
		 */
		try {
			this.sparrow.query(new PartType("Promoter"));
		} catch(Exception e) {
			e.printStackTrace();
		}
//		EugeneRule eugeneRule = new EugeneRule("demo");
//		// type == "Promoter"
//		eugeneRule.getPredicates().add(new Predicate(new Property("type", SparrowConstants.TXT), "==", "Promoter"));
//		
//		List<EugeneRule> rules = new ArrayList<EugeneRule>();
//		rules.add(eugeneRule);
//		try {
//			this.sparrow.query(rules);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(this.sparrow.size());
	}

}
