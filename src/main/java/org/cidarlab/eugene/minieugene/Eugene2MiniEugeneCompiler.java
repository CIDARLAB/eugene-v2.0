/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.minieugene;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.ComponentType;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.ArrangementConstraint;
import org.cidarlab.eugene.dom.rules.LogicalNot;
import org.cidarlab.eugene.dom.rules.LogicalOr;
import org.cidarlab.eugene.dom.rules.Predicate;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.Interp;

public class Eugene2MiniEugeneCompiler {

	/*
	 * A list of parts relevant for arrangement
	 */
	private Map<ComponentType, Set<Component>> components;

	/*
	 * A reference to the Interpreter
	 */
	private Interp interp;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param interp  ... a reference to the Eugene interpreter
	 */
	public Eugene2MiniEugeneCompiler(Interp interp) {
		this.interp = interp;
	}

	/**
	 * 
	 * @param d
	 * @param rules
	 * @param parts
	 * @return
	 * @throws EugeneException
	 */
	public String compile(Device d, Rule rule, Set<Component> components, Set<Interaction> interactions) 
			throws EugeneException {
		
		/*
		 * for a more efficient search of parts and part types 
		 * throughout the compilation process,
		 * we set up a temporary hashmap to link 
		 * parts and their part type
		 */
		this.buildComponentsMap(components);
		
		StringBuilder sb = new StringBuilder();
		
		/*
		 * N = n .
		 */
		sb.append(this.compileSize(d));
		
		/*
		 * compile the given device into a miniEugene template
		 */
		try {
			sb.append(this.buildMiniEugeneTemplate(d));
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
		
		/*
		 * [part] is_a [part-type] .
		 */
		sb.append(this.compileFacts());

		/*
		 * compile the Eugene rules into corresponding miniEugene rules
		 */
		if(null != rule) {
			sb.append(
					this.compileRule(rule));
		}
		
		/*
		 * finally, we also clean up the parts hashmap
		 */
		this.cleanUpPartsHashMap();

		return sb.toString();
	}
	
	private StringBuilder compileSize(Device d) {
		StringBuilder sb = new StringBuilder();
		sb.append("N=").append(d.getComponents().size()).append(".");
		return sb;
	}
	
	private StringBuilder compileFacts() {
		StringBuilder sb = new StringBuilder();
		if(null != this.components) {			
			for(ComponentType ct : this.components.keySet()) {
				if(null != this.components.get(ct)) {
					for(Component c : this.components.get(ct)) {
						if(c instanceof Part) {
							sb.append(c.getName())
								.append(" IS_A ")
								.append(ct.getName())
								.append(".");
						}
					}
				}
			}
		}
		return sb;
	}
	
	private void buildComponentsMap(Set<Component> soc) {
		if(null != soc && !soc.isEmpty()) {
			this.components = new HashMap<ComponentType, Set<Component>>();
			for(Component c : soc) {
				if(this.components.containsKey(c.getType())) {
					this.components.get(c.getType()).add(c);					
				} else {
					Set<Component> sop = new HashSet<Component>();
					sop.add(c);
					this.components.put(c.getType(), sop);
				}
			}
		}
	}
	
	private void cleanUpPartsHashMap() {
		if(null != components) {
			this.components.clear();
			this.components = null;	
		}
	}
		
	
	
	/**
	 * Here, we compile a list of Eugene components into 
	 * a miniEugene template
	 * 
	 * @param component
	 * @return
	 */
	private StringBuilder buildMiniEugeneTemplate(Device d) 
			throws EugeneException {
		StringBuilder sb = new StringBuilder();
		sb.append("TEMPLATE ");
		for(int c=0; c<d.getComponents().size(); c++) {
			sb.append(
				this.selection(d.getComponents().get(c)));
			
			if(c < d.getComponents().size() - 1) {
				sb.append(", ");
			}			
		}

		sb.append(".");
		return sb;
	}
	
	
		
	
	/**
	 * 
	 * @param components
	 * @return
	 */
	private StringBuilder selection(List<? extends org.cidarlab.eugene.dom.NamedElement> components) 
			throws EugeneException {

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0; i<components.size(); i++) {			
			org.cidarlab.eugene.dom.NamedElement ne = components.get(i);
			
			sb.append(ne.getName());
			
			if(ne instanceof ComponentType) {
				this.updatedComponentsMap((ComponentType)ne);
			}
			
			if(i < components.size() - 1) {
				sb.append("|");
			}
		}
		sb.append("]");
		return sb;
	}
		
	private void updatedComponentsMap(ComponentType ct) 
			throws EugeneException {
		
		/*
		 * if parts with appropriate characteristics were already selected, 
		 * then we use those parts
		 */
		if(this.components == null) {
			this.components = new HashMap<ComponentType, Set<Component>>();
		}
		
		if(!this.components.containsKey(ct)) {
			try {
				this.components.put(ct, this.interp.getComponents(ct));
			} catch(EugeneException ee) {
				throw new EugeneException(ee.getLocalizedMessage());
			}
		}
	}
	
	/**
	 * 
	 * @return the rule represented as miniEugene constraints
	 */
	private StringBuilder compileRule(Rule rule) {
		StringBuilder sb = new StringBuilder();
		if(null != rule.getPredicates() && null != rule.getPredicates().getPredicates() &&
			!rule.getPredicates().getPredicates().isEmpty()) {
			
			for(Predicate p : rule.getPredicates().getPredicates()) {
				
				if(p instanceof ArrangementConstraint) {
					
					sb.append(p).append(".");

				} else if(p instanceof LogicalOr) {

					if(((LogicalOr)p).getConstraints().get(0) instanceof ArrangementConstraint) {
						sb.append(p).append(".");
					}
					
				} else if(p instanceof LogicalNot) {

					if(((LogicalNot)p).getPredicate() instanceof ArrangementConstraint) {
						sb.append(p).append(".");
					}

				}
			}
		}

		return sb;
	}
}
