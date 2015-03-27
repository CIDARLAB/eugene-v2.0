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

package org.cidarlab.eugene.sparrow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.rules.*;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.rule.SparrowQuery;
import org.cidarlab.sparrow.rule.SparrowRule;

/**
 * The SparrowAdapter class follows the Adapter design pattern, 
 * providing tailored interfaces for Eugene to interact with 
 * the Sparrow Library Management System (LMS).
 * 
 * Mainly, it compiles Eugene objects into Sparrow queries and rules.
 * The source language is Eugene. 
 * Sparrow is build atop the JBoss Drools engine and, hence, 
 * the target language is the Drools Rule Language (DRL).
 * 
 * Further information on Drools is provided here:
 * http://docs.jboss.org/drools/release/5.6.0.Final/drools-expert-docs/html/
 * 
 * @author Ernst Oberortner
 *
 */
public class SparrowAdapter {

	// the Sparrow
	private Sparrow sparrow;
	
	// the compiler
	private Eugene2SparrowCompiler compiler;
	
	/**
	 * The SparrowAdapter constructor requires 
	 * an instance of Sparrow.
	 * 
	 * @param sparrow
	 */
	public SparrowAdapter(Sparrow sparrow) {
		this.sparrow = sparrow;
		
		this.compiler = new Eugene2SparrowCompiler();
	}
	
	/**
	 * The queryParts(Rule) methods queries all parts 
	 * compliant with a specified rule. 
	 * Therefore it compiles the Rule object into 
	 * a JBoss Drools query.
	 * 
	 * @param rule
	 * @return
	 * @throws EugeneException
	 */
	public Set<Part> queryParts(Rule rule) 
			throws EugeneException {
		
		/*
		 * first, we compile the rule into sparrow queries
		 */
		List<SparrowQuery> queries = null;
		
		try {
			queries = this.compiler.compileQuery(rule);
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}

		/*
		 * then, we execute the query
		 */
		Set<Part> sop = new HashSet<Part>();
		try {
			
			for(SparrowQuery query : queries) {
				Set<Component> components = this.sparrow.query(query);
//				System.out.println("[SparrowAdapter] components: " + components);
				for(Component component : components) {
					if(component instanceof Part) {
						sop.add((Part)component);
					}
				}
			}
			
			return sop;
			
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}
	}
	
	/**
	 * The queryComponents(List) method queries the Sparrow LMS 
	 * regarding a set of rules and returns all rule-compliant 
	 * elements stored in the LMS.
	 * 
	 * The functionality of the queryComponents(List) method 
	 * utilizes the {@link #queryParts(Rule)}  method. 
	 * 
	 * @param rules   ... the selection rules
	 * @return a set of components compliant with the specified rules.
	 * @throws EugeneException
	 */
	public Set<Component> queryComponents(List<Rule> rules) 
			throws EugeneException {

		Set<Component> components = new HashSet<Component>();		

		for(Rule rule : rules)  {
			components.addAll(
					this.queryParts(rule));
		}
		
		return components;
	}
	
	/**
	 * The prune(List, List) method takes as input a list of Device objects 
	 * and a list of Rule objects. It filters out all Devices that do not 
	 * comply with the given set of rules. The pruning process, however, 
	 * considers only expression rules. The securing of architectural constraints
	 * is warranted by miniEugene, in the previous design space exploration step.
	 * 
	 * @param devices   ... the Devices that need to be filtered
	 * @param rules     ... the filtering criteria
	 * @return a list of Devices compliant with the specified rules
	 * @throws EugeneException
	 */
	public List<Device> prune(List<Device> devices, List<Rule> rules) 
			throws EugeneException {
		
		// if there are no rules, then we 
		// just return the given set of devices
		if(null == rules || rules.isEmpty()) {
			return devices;
		}
		
		/*
		 * first, let's check if we have to do a prune
		 */
		List<Rule> lor = null;
		for(int i=0; i<rules.size(); i++) {
				
			/*
			 * we only prune if the rule has at least 
			 * one expression constraint in it  
			 */				
			if(rules.get(i).hasExpression()) {
				
				if(null == lor) {
					lor = new ArrayList<Rule>();
				}
				lor.add(rules.get(i));
			}
		}

		/*
		 * we only prune if the list of rules has at least 
		 * one expression constraint in it  
		 */
		if(null != lor) {
			return this.doPrune(devices, lor);
		}
		
		return devices;
	}
	
	/**
	 * The doPrune method does the ``real'' pruning process, since 
	 * the given list of rules to the {@link #prune(List, List)} method 
	 * can only contain topology-related constraints.
	 * Hence, the {@link #prune(List, List)} method inspects the 
	 * given rules if there are any expressional rules. 
	 * @param devices
	 * @param rules
	 * @return
	 * @throws EugeneException
	 */
	private List<Device> doPrune(List<Device> devices, List<Rule> rules) 
			throws EugeneException { 
		List<Device> devs = new ArrayList<Device>();

		try {
			// to do the real pruning, we create a new 
			// instance of sparrow
			// --> not so efficient though
			Sparrow sp = new Sparrow();
			
			// and insert all devices into the working memory (WM) 
			// of Sparrow
			for(Device d : devices) {
				sp.insertFact(d);
			}
			
			// we compile every expression rule into Sparrow rules
			for(Rule rule : rules) {
				
				SparrowRule sp_rule = this.compiler.compilePrune(rule);
				if(null != sp_rule) {
					// and prune it.
					devs.addAll(sp.prune(sp_rule));
				}
			}

		} catch(SparrowException se) {
			throw new EugeneException(se.toString());
		}
		
		// lastly, we return the remaining devices
		return devs;
	}	
}
