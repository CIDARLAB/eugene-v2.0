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

package org.cidarlab.eugene.minieugene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.exception.EugeneException;
//import org.cidarlab.eugene.interp.SymbolTable;
import org.cidarlab.eugene.interp.Interp;
import org.cidarlab.minieugene.MiniEugene;

public class MiniEugeneAdapter {

	private static final int MAX_NR_OF_SOLUTIONS = 50000;
	
	/*
	 * the Eugene 2 MiniEugene compiler
	 */
	private Eugene2MiniEugeneCompiler compiler;
	
	/*
	 * an instance of MiniEugene
	 */
	private MiniEugene me;
	
	/*
	 * a reference to the Interpreter
	 */
	private Interp interp;
	
	public MiniEugeneAdapter(Interp interp) {		
		this.interp = interp;

		this.me = new MiniEugene();
		this.compiler = new Eugene2MiniEugeneCompiler(this.interp);		
	}

	/**
	 * 
	 * @param d            ... the device template
	 * @param rules        ... the rules on the device
	 * @param components   ... the set of selected components
	 * @param interactions ... a set of interactions
	 * 
	 * @return
	 * 
	 * @throws EugeneException
	 */
	public List<Device> product(
			Device d, 
			List<Rule> rules, 
			Set<org.cidarlab.eugene.dom.Component> components,
			Set<Interaction> interactions,
			String PERMUTE_CONSTRAINTS, String GRAMMAR_CONSTRAINTS) 
		throws EugeneException {

		/*
		 * let's do some error checking
		 */
		if(null == d || d.getComponents() == null || d.getComponents().isEmpty()) {
			throw new EugeneException("Cannot use product() on an empty device.");
		}

		List<Device> solutions = new ArrayList<Device>();

		if(!rules.isEmpty()) {
			/*
			 * since we use DNF, we need to solve the problem for every rule
			 * and then union the results
			 */
			for(Rule rule : rules) {
				Set<Device> sod = this.execute(d, rule, components, interactions, PERMUTE_CONSTRAINTS, GRAMMAR_CONSTRAINTS);
				if(null != sod && !sod.isEmpty()) {
					solutions.addAll(sod);
				}
			}
		} else {
			Set<Device> sod = this.execute(d, null, components, interactions, PERMUTE_CONSTRAINTS, GRAMMAR_CONSTRAINTS);
			if(null != sod && !sod.isEmpty()) {
				solutions.addAll(sod);
			}
		}
		
		/*
		 * STEP 3: TRANSFORMATION of the miniEugene solutions 
		 *         to Eugene Device objects and 
		 *         return the resulting list
		 */
		return solutions;
	}
	
	private Set<Device> execute(
			Device d, 
			Rule rule, 
			Set<org.cidarlab.eugene.dom.Component> components,
			Set<Interaction> interactions,
			String PERMUTE_CONSTRAINTS, String GRAMMAR_CONSTRAINTS) 
					throws EugeneException {
		
		/*
		 * STEP I: COMPILATION into miniEugene script
		 */
		String meScript = this.compiler.compile(d, rule, components, interactions);
		
		// if it's a permutation, then 
		// we need to add the CONTAINS and ORIENTATION constraints
		// into the miniEugene script.
		if(null != PERMUTE_CONSTRAINTS) {
			meScript += PERMUTE_CONSTRAINTS;
		}
		
		// if a template contains devices, then the device's instances
		// are specified w/ the IS_A statement.
		// those specifications are in the GRAMMAR_CONSTRAINTS string 
		// which is mainly concatenated in the Interp.productCompositeDevice(Device) method		
		if(null != GRAMMAR_CONSTRAINTS) {
			meScript += GRAMMAR_CONSTRAINTS;
		}
		
//		System.out.println(meScript);
		
		/*
		 * STEP II: EXECUTION of miniEugene script
		 *         i.e. constraint solving
		 * 
		 * for the time being, 
		 * we always ask for MAX_NR_OF_SOLUTIONS solutions
		 */
		try {		
			me.solve(meScript, MAX_NR_OF_SOLUTIONS);		
		} catch(Exception ee) {
			ee.printStackTrace();
			throw new EugeneException(ee.getMessage());
		}
		
		/*
		 * STEP III:
		 * CONVERSION of miniEugene solutions into Eugene solutions
		 */
		return this.convertToEugene(d.getName(), me.getSolutions());
	}

	 /**
	  * convert the miniEugene solutions into a list of Device objects
	  * 
	  * @param name
	  * @param solutions
	  * @return
	  */
	private Set<Device> convertToEugene(String name, List<org.cidarlab.minieugene.dom.Component[]> solutions) {

		Set<Device> sod = new HashSet<Device>(solutions.size());
		int i=1;
		for(org.cidarlab.minieugene.dom.Component[] solution : solutions) {
			Device d = new Device(name+"_"+i);
			

			for(int k=0; k<solution.length; k++) {
				
				org.cidarlab.eugene.dom.Component c = null;
				
				try {
					c = (org.cidarlab.eugene.dom.Component)this.interp.get(solution[k].getName());
				} catch(EugeneException ee) {
					ee.printStackTrace();
				}
				
				/*
				 * component
				 */
				List<org.cidarlab.eugene.dom.NamedElement> lst = new ArrayList<org.cidarlab.eugene.dom.NamedElement>();
				lst.add(c);
				d.getComponents().add(lst);
				
				/*
				 * the component's orientation
				 */
				List<Orientation> orientations = new ArrayList<Orientation>();
				if(solution[k].isForward()) {
					orientations.add(Orientation.FORWARD);
				} else {
					orientations.add(Orientation.REVERSE);
				}
				d.getOrientations().add(orientations);
			}
			
			/*
			 * add the enumerated device to the list of devices
			 */
			sod.add(d);
			
			i++;
		}

		return sod;
	}
	

}
