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

import org.cidarlab.eugene.exception.EugeneException;

/**
 * A ComponentType represents an abstract genetic component, such as part type. 
 * In Eugene, every Component has a ComponentType. A ComponentType has a list of 
 * properties that the Component instantiates (so-called Property Values).
 *  
 * @author Ernst Oberortner
 *
 */
public class ComponentType 
	extends NamedElement {

	private static final long serialVersionUID = 1242448556255896751L;

	/*
	 * LIST OF PROPERTIES
	 */
	protected List<Property> properties; 
	
	/**
	 * Constructor w/ name
	 * 
	 * @param name   ... the name of the ComponentType
	 */
	public ComponentType(String name) {
		super(name);
		this.properties = new ArrayList<Property>();
	}
	
	/**
	 * Constructor w/ name and properties
	 * 
	 * @param name   ... the name of the ComponentType
	 * @param properties ... a list of properties of the type
	 */
	public ComponentType(String name, List<Property> properties) {
		super(name);
		this.properties = properties;
	}
	
	
	/**
	 * The getProperties() method returns the list of properties 
	 * of the ComponentType
	 * 
	 * @return  the of the ComponentType's properties
	 */
	public List<Property> getProperties() {
		return this.properties;
	}

	/**
	 * The getProperty(String) method returns a property of 
	 * a given name. If the property does not exist, then 
	 * it returns NULL.
	 *  
	 * @param name   ... the name of the desired Property
	 * @return   ... the Property if it exists, NULL otherwise
	 */
	public Property getProperty(String name) {
		
		for (Property p : this.getProperties()) {
			if (p.getName().equals(name)) {
				return p;
			}
		}

		return (Property) null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type ").append(this.getName()).append("(");
		for(int i=0; i<this.getProperties().size(); i++) {
			sb.append(".").append(this.getProperties().get(i).getName()).append("(")
				.append(this.getProperties().get(i).getType()).append(")");
			
			if(i < this.getProperties().size() -1 ) {
				sb.append(", ");
			}
		}
		sb.append(");");
		return sb.toString();
	}

	@Override
	public NamedElement getElement(String name) {
		/*
		 * here, we iterate over the properties
		 */
		return this.getProperty(name);
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		if(idx < 0 || idx >= this.getProperties().size()) {
			throw new EugeneException(idx + " is out of bounds in the " + this.getName() +" type!");
		}
		return this.getProperties().get(idx);
	}

}
