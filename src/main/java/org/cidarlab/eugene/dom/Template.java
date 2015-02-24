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

package org.cidarlab.eugene.dom;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * A Device is a Composite Component.
 * Since we offer the Selection operator for defining devices, 
 * a device contains a list of lists of components.
 * 
 * @author Ernst Oberortner
 * 
 * TODO: improve the public methods 
 * currently, we need to manually keep the size of the components list and the size of the
 * orientations list in sync. (see addComponents and addOrientations)
 * => only allow that the user can add components in conjunction with orientations
 * => don't offer too many getters/setters
 *
 */
public class Template 
		extends ComponentType {

	private static final long serialVersionUID = -2134313281573122775L;
	
	/*
	 * LIST OF (SUB)COMPONENTS
	 * 
	 * here, we need a list of list of components, due to the Selection operator
	 *
	 * in an ``enumerated'' device (produced by the product/permute methods), 
	 * every list element consists of a list of only one component.
	 */
	private List<List<Component>> components;
	
	/*
	 * LIST OF ORIENTATIONS
	 *
	 * the list of orientations keeps track of the orientation of the device's components.
	 * 
	 * The design rationale was the following:
	 * storing the orientation information in the device enables to have the components 
	 * only once in memory. 
	 * resultantly we can create more devices in memory instead of cloning the components 
	 * and store it to every device.
	 */
	private List<List<Orientation>> orientations;
	
	/*
	 * isValid flag
	 * 
	 * The isValid flag indicates if the device complies 
	 * with the specified rules. This flag is being utilized 
	 * by Sparrow in order to differentiate between functioning 
	 * and faulty devices.
	 * 
	 * When instantiating the Device class, the device is automatically 
	 * valid.
	 */
	private boolean isValid;
	
	/**
	 * 
	 * @param name
	 */
	public Template(String name) {
		super(name);
		this.setComponents(new ArrayList<List<Component>>());
		this.setOrientations(new ArrayList<List<Orientation>>());
		this.setValid(true);
	}
	
	/**
	 * Since this constructor does not take information on orientations,
	 * every component will get an undefined orientation
	 * @param name ... the name of the device
	 * @param components ... the components of the device
	 */
	public Template(String name, List<List<Component>> components) {
		super(name);
		this.setComponents(components);
		this.setOrientations(new ArrayList<List<Orientation>>());
		this.setValid(true);
	}
	

	/**
	 * 
	 * @param name ... the name of the device
	 * @param components ... the components of the device
	 * @param orientations ... the orientations of the device's components
	 */
	public Template(String name, List<List<Component>> components, List<List<Orientation>> orientations) {
		super(name);
		this.setComponents(components);
		this.setOrientations(orientations);		
		this.setValid(true);
	}

	/**
	 * 
	 * @return a list of the device's components 
	 * (the list can be recursive, i.e. elements in the list can 
	 * be devices)
	 */
	public List<List<Component>> getComponents() {
		return this.components;
	}
	
	public List<Component> getComponents(int idx) 
		throws EugeneException {

		if(idx < 0 || idx >= this.getComponents().size()) {
			throw new EugeneException("The index must be >=0 and <="+(this.getComponents().size()-1)+".");
		}
		
		return this.getComponents().get(idx);
	}
	
	/**
	 * The getComponentsList/0 returns the device's components 
	 * as list.
	 * This method is needed for the ``Pruning'' phase of our 
	 * multistep design space exploration process.
	 * Hence, this method is invoked by the Drools rules. 
	 * And we need this method since we store a device's components 
	 * as a list of lists, which is needed for the selection operator 
	 * when specifying a device in Eugene.
	 * 
	 * @return the device's components as list
	 */
	public List<Component> getComponentList() {
		List<Component> components_list = new ArrayList<Component>();
		for(List<Component> loc : this.getComponents()) {
			components_list.add(loc.get(0));
		}
		return components_list;
	}
	
	/**
	 * The getComponent/1 method returns the device's sub-component 
	 * named after the provided name
	 * @param name
	 * @return
	 */
	public Component getComponent(String name) 
			throws EugeneException {
		
		if(null == name || name.isEmpty()) {
			throw new EugeneException("No name of component provided.");
		}
		
		// search for the component
		for(List<Component> components : this.getComponents()) {
			for(Component component : components) {
				if(name.equals(component.getName())) {
					return component;
				}
			}
		}
		
		// component not found
		// => 
		return null;
	}
	
	/**
	 * set the list of component lists
	 * 
	 * @param lst ... the list of list of components
	 */
	public void setComponents(List<List<Component>> lstComponents) {
		if(null == lstComponents) {
			this.components = new ArrayList<List<Component>>();
		} else {
			this.components = lstComponents;
		}
	}
	
	/**
	 * 
	 * @param lst ... a list of components
	 * @param idx ... the index of the components list to be inserted or replaced
	 */
	public void setComponents(List<Component> components, int idx) 
			throws EugeneException {
		if(idx < 0 || idx >= this.getComponents().size()) {
			throw new EugeneException("The index must be >=0 and <="+(this.getComponents().size()-1)+".");
		}
		
		this.getComponents().set(idx, components);
	}
	
	/**
	 * The addComponents method appends a given list of components 
	 * to the device's list of list of components
	 * @param components ... a list of components
	 * @throws EugeneException
	 */
	public void addComponents(List<Component> components) 
			throws EugeneException {
		
		this.getComponents().add(components);
		
		/*
		 * here, we also need to specify the components' orientations
		 */

		if(this.getOrientations().size() == this.getComponents().size() - 1) {
			List<Orientation> orientations = new ArrayList<Orientation>(components.size());
			for(Component c : components) {
				orientations.add(c.getOrientation());
			}
			this.addOrientations(orientations);
		}
	}

	
	/*
	 * setters/getters for the isValid flag
	 */
	/**
	 * 
	 * @param isValid ... the valid status of this device
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	/**
	 * 
	 * @return the valid status of this device (either true or false)
	 */
	public boolean isValid() {
		return this.isValid;
	}
	
	
	/*
	 * methods for orientations
	 */
	/**
	 * 
	 * @return the list of lists of orientations
	 */
	public List<List<Orientation>> getOrientations() {
		return this.orientations;
	}
	
	/**
	 * 
	 * @param idx ... the required list of orientations at the idx-th position of the device
	 * @return    ... the list of orientations at the idx-th position of the device
	 * @throws EugeneException
	 */
	public List<Orientation> getOrientations(int idx) 
			throws EugeneException {
		
		if(idx < 0 || idx >= this.orientations.size()) {
			throw new EugeneException("The index must be >=0 and <="+(this.getOrientations().size()-1)+".");
		}
		
		return this.getOrientations().get(idx);
	}
	
	/**
	 * set the list of orientation lists
	 * 
	 * @param lst ... the list of list of orientations
	 */
	private void setOrientations(List<List<Orientation>> orientations) {
		if(null == orientations) {
			this.orientations = new ArrayList<List<Orientation>>();
		} else {
			this.orientations = orientations;
		}
	}
	
	/**
	 * The addOrientations method appends a given list of orientations 
	 * to the device's list of list of orientations
	 * @param orientations ... a list of orientations
	 * @throws EugeneException
	 */
	private void addOrientations(List<Orientation> orientations) 
			throws EugeneException {
		
		/*
		 * iff the list of orientations is one less than the list of components,
		 * then the addOrientations operation is allowed 
		 */
		if(this.getOrientations().size() == this.getComponents().size() - 1) {		
			this.getOrientations().add(orientations);
		} else {		
			throw new EugeneException("It is not allowed to add orientations w/o components!");
		}
	}

	@Override
	public int hashCode() {
		int hash = this.getName().hashCode();
		//for(Component c : this.components) {
		//	hash += c.hashCode();
		//}
		return hash;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Device ").append(this.getName()).append("(");

		for(int i = 0; i<this.getComponents().size(); i++) {
			if(this.getComponents().get(i).size() > 1) {
				// SELECTION
				sb.append("[");
				for(int j=0; j<this.getComponents().get(i).size(); j++) {
					
					if(this.getOrientations().get(i).get(j) == Orientation.FORWARD) {
						sb.append("+");
					} else if(this.getOrientations().get(i).get(j) == Orientation.REVERSE) {
						sb.append("-");
					}
						
					sb.append(this.getComponents().get(i).get(j).getName());
					
					if(j < this.getComponents().get(i).size() - 1) {
						sb.append("|");
					}
				}
				sb.append("]");
			} else {

				if(this.getOrientations().get(i).get(0) == Orientation.FORWARD) {
					sb.append("+");
				} else if(this.getOrientations().get(i).get(0) == Orientation.REVERSE) {
					sb.append("-");
				}
					
				sb.append(this.getComponents().get(i).get(0).getName());
			}
			
			if(i < this.getComponents().size() - 1) {
				sb.append(", ");
			}
			
		}
		sb.append(");");
		return sb.toString();
	}

}
