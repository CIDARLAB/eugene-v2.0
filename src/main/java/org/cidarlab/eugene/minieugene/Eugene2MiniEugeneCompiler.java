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

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.ComponentType;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.ArrangementConstraint;
import org.cidarlab.eugene.dom.rules.LogicalNot;
import org.cidarlab.eugene.dom.rules.LogicalOr;
import org.cidarlab.eugene.dom.rules.Predicate;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.Interp;

/**
 * The Eugene2MiniEugeneCompiler class provides methods to 
 * compile structural rules specified in the Eugene language 
 * to miniEugene constraints.
 * 
 * @author Ernst Oberortner
 */
public class Eugene2MiniEugeneCompiler {

	/*
	 * A map of components for a more runtime efficient compilation process.
	 * 
	 * key   ... a Eugene PartType (e.g. Promoter)
	 * value ... a set of instances of the corresponding part type. 
	 */
	private Map<ComponentType, Set<Component>> components;

	
	/*
	 * The sequences set holds all sequences of the 
	 * compiled miniEugene script.
	 * 
	 * key   ... device name
	 * value ... the sequence(s)
	 */
	private Map<String, StringBuilder> sequences;
	
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

		// WORK-IN-PROGRESS
		this.toMiniEugeneSequence(d);
		if(null != this.sequences) {
			for(String s:this.sequences.keySet()) {
				System.out.println(s+" -> "+this.sequences.get(s));
			}
		}
		////////////////////////////
		
		
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
		 * we also clean up the parts map
		 */
		this.cleanUpPartsHashMap();

		/*
		 * lastly, we return the compiled miniEugene script
		 */
		return sb.toString();
	}

	/**
	 * The compileSize/1 method takes as input a Eugene Device and 
	 * compiles the miniEugene "size" sentence, i.e. "N=x.". 
	 * It calculates the Device's size through calling the 
	 * getDeviceSize/1 method that recursively traverses the Device.
	 * 
	 * @param d  ... the device of that the miniEugene "size" sentence
	 *               should be generated
	 * 
	 * @return   ... the compile miniEugene "size" sentence of the 
	 *               given Device
	 */
	private StringBuilder compileSize(Device d) {
		StringBuilder sb = new StringBuilder();
		
		// call the getDeviceSize/1 method to determine 
		// the Device's size
		sb.append("N=").append(getDeviceSize(d)).append(".");
		
		return sb;
	}
	
	/**
	 * The getDeviceSize/1 method recursively traverses the 
	 * given Device object and sums up its size which 
	 * will be returned.
	 * 
	 * @param d  ... The Device of that the size needs to be determined
	 * @return   ... The size of the Device.
	 */
	private int getDeviceSize(Device d) {
		
//		return d.getComponents().size();

		/** NEW VERSION: [under development] **/
		int size = 0;
		for(NamedElement e : d.getComponentList()) {
			if(e instanceof Device) {
				size += this.getDeviceSize((Device)e);
			} else {
				size ++;
			}
		}
		return size; 
	}
	
	/**
	 * The compileFacts/0 method iterates over all elements in the
	 * components map and generates miniEugene "is_a" statements 
	 * for them.
	 * For example, if p is a Promoter, then the miniEugene "p is_a Promoter"
	 * sentence will be generated.
	 * 
	 * @return the compileFacts/0 method returns a string concatenated of 
	 *         miniEugene "is_a" sentences 
	 */
	private StringBuilder compileFacts() {
		StringBuilder sb = new StringBuilder();
		
		// iterate over the elements of the components map
		if(null != this.components) {
			
			// each key of the components map refers to a 
			// list of components of that type
			for(ComponentType ct : this.components.keySet()) {
				
				// if the list is not null and empty
				if(null != this.components.get(ct) &&
						!this.components.get(ct).isEmpty()) {
					
					// we iterate over every instance of the component
					for(Component c : this.components.get(ct)) {
						if(c instanceof Part) {
							// and append a miniEugene "is_a" sentence 
							// to our stringbuilder
							sb.append(c.getName())
								.append(" IS_A ")
								.append(ct.getName())
								.append(".");
						}
					}
				}
			}
		}
		
		// lastly we return the concatenated string 
		// of miniEugene "is_a" sentences.
		return sb;
	}
	
	/**
	 * The buildComponentsMap/1 method initializes the the Eugene2MiniEugene compiler's 
	 * map of components.
	 * 
	 * The map is organized as follows:
	 * key   ... the part type
	 * value ... a list of instances (i.e. parts) of the corresponding key
	 * 
	 * @param soc  ... a set of components that are being stored in the components map.
	 */
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
	
	/**
	 * cleanUp the components map.
	 */
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
			
			
			if(d.getComponents().get(c).size() > 1) {
				sb.append(
						this.selection(d.getComponents().get(c)));
			} else {
				sb.append(
						this.traverse(d.getComponents().get(c).get(0)));
			}
			
			if(c < d.getComponents().size() - 1) {
				sb.append(", ");
			}			
		}

		sb.append(".");
		return sb;
	}
	
	/**
	 * The toMiniEugeneSequence/1 method takes as input a 
	 * (hierarchically composed) Eugene Device and recursively 
	 * compiles it onto a set of miniEugene Sequence constraints.
	 * 
	 * @param d
	 * @return
	 * @throws EugeneException
	 */
	private void toMiniEugeneSequence(Device d) 
			throws EugeneException {
		
		if(null == this.sequences) {
			this.sequences = new HashMap<String, StringBuilder>();
		}

		for(List<NamedElement> loe : d.getComponents()) {
			if(loe.size() == 1) {
				if(loe.get(0) instanceof Device) {
					StringBuilder sb = new StringBuilder();
					
					sb.append("SEQUENCE ").append(this.traverse(loe.get(0)))
						.append(" OR ")
						.append("SEQUENCE ").append(this.traverse(this.flip((Device)loe.get(0))))
						.append(".");
					
 					this.sequences.put(loe.get(0).getName(), sb);
				}
			}
		}
	}
	
	/**
	 * The flip/1 method reverses the elements in a given 
	 * device. The flip/1 method returns a duplicate object 
	 * containing the original device's elements in reverse
	 * order.
	 * 
	 * Example:
	 * Device D(+a, +b, +c); --> Device D(-c, -b, -a);
	 * Device D(-c, -b, -a); --> Device D(+a, +b, +c); 
	 * 
	 * @param d  ... the device whose elements must be reverse
	 * 
	 * @return
	 */
	private Device flip(Device d) {
		
		if(null == d.getComponents() || d.getComponents().isEmpty()) {
			return d;
		}
		
		Device fd = new Device(d.getName());
		List<Orientation> loo = null;
		int size = d.getComponents().size();
		for(int i=size-1; i>=0; i--) {
			
			try {
				// components
				fd.getComponents().add(d.getComponents(i));
				
				// orientations
				loo = d.getOrientations(i);
				for(int j=0; j<loo.size(); j++) {
					if(loo.get(j) == Orientation.FORWARD) {
						loo.set(j, Orientation.REVERSE);
					} else {
						loo.set(j, Orientation.FORWARD);
					}
				}
				
				fd.getOrientations().add(loo);
				// reverse the orientations
				
			} catch(EugeneException ee) {
				ee.printStackTrace();
			}
			
		}
		
		return fd;
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
	
	/**
	 * The traverse/1 method traverses the given component.
	 * 
	 * @param c
	 * @return
	 * @throws EugeneException
	 */
	private StringBuilder traverse(NamedElement e) 
			throws EugeneException {
		
		StringBuilder sb = new StringBuilder();
		
		// if the component is a device, then 
		// we need to traverse it
		if(e instanceof Device) {
			
			int i = 0;
			int size = ((Device)e).getComponents().size();
			for(List<NamedElement> loe : ((Device)e).getComponents()) {
				
				if(loe.size() > 1) {
					// the i-th element of the device is a "selection", 
					// hence we need to compile it to a selection []
					sb.append(this.selection(loe));
				} else {
					sb.append(this.traverse(loe.get(0)));
				}
				
				i++;
				
				if(i < size) {
					sb.append(", ");
				}
			}

		} else {
			
			if(e instanceof ComponentType) {
				// in case it's a component type, then we also need to 
				// update the components map
				this.updatedComponentsMap((ComponentType)e);
			}
			
			// we only append the name of the component/component-type
			sb.append(e.getName());
		}		
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

					int or_size = ((LogicalOr)p).getConstraints().size();
					for(Predicate pred : ((LogicalOr)p).getConstraints()) {
						
						if(pred instanceof ArrangementConstraint) {
							sb.append(pred); 
						} else if(pred instanceof LogicalNot) {
							if(((LogicalNot)pred).getPredicate() instanceof ArrangementConstraint) {
								sb.append(pred);
							}
						}

						or_size --;
						if(or_size >= 1) {
							sb.append(" OR ");
						}
					}
					sb.append(".");
					
					
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
