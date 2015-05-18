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

package org.cidarlab.eugene.dom.imp.container;

import java.util.Collection;
import java.util.HashSet;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * A EugeneCollection is a container of unorder elements. 
 * A EugeneCollection can contain all kinds of NamedElements, 
 * such as parts, variables, types, as well as other 
 * Eugene containers (i.e. arrays and collections).
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneCollection 
		extends EugeneContainer {

	private static final long serialVersionUID = 3438297215191122244L;
	
	private Collection<NamedElement> elements;

	public EugeneCollection(String name) {
		super(name);
		this.elements = new HashSet<NamedElement>();
	}
	
	/**
	 * The getElements/0 method returns the entire 
	 * collection of named elements.
	 * 
	 * @return the collection of named elements
	 */
	public Collection<NamedElement> getElements() {
		return this.elements;
	}
		
	@Override
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("Collection ").append(this.getName()).append(" (").append(NEWLINE);
		for(NamedElement ne : this.getElements()) {
			if(ne instanceof EugeneContainer) {
				sb.append(((EugeneContainer)ne).toString()).append(NEWLINE);
			} else {
				sb.append(ne.getName()).append(NEWLINE);
			}
		}
		sb.append(");");
		return sb.toString();
	}
	
	@Override
	public NamedElement get(String name) {
		if(null != this.getElements() && !this.getElements().isEmpty()) {
			for(NamedElement element : this.getElements()) {
				if(element.getName().equals(name)) {
					return element;
				}
			}
		}
		return null;
	}

	@Override
	public boolean contains(String name) {
		if(null != this.getElements() && !this.getElements().isEmpty()) {
			for(NamedElement element : this.getElements()) {
				if(element.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void put(NamedElement ne) 
			throws EugeneException {
		if(null != ne && !this.contains(ne.getName())) {
			if(ne instanceof NamedElement) {
				this.getElements().add((NamedElement)ne);
			} else {
				throw new EugeneException("I cannot add the "+ne+" element to a collection!");
			}
		} else if(null != ne && this.contains(ne.getName())) {
			throw new EugeneException("The " + ne.getName()+" element exists already in the "+this.getName()+" collection!"); 
		}
	}

	@Override
	public void clear() {
		this.getElements().clear();
	}
	
	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		if(null != this.getElements() && !this.getElements().isEmpty()) {
			for(NamedElement e : this.getElements()) {
				if(e.getName().equals(name)) {
					return e;
				}
			}
		}
		return null;
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new EugeneException("A EugeneCollection does not support access through array indices!");
	}
	
	@Override
	public int hashCode() {
		int hash = this.getID();
		
		if(null != this.getElements() && !(this.getElements().isEmpty())) {
			for(NamedElement el : this.getElements()) {
				hash += el.hashCode();
			}
		}
		
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(!(o instanceof EugeneCollection)) {
			return false;
		}
		
		return this.hashCode() == ((EugeneCollection)o).hashCode();
	}

	@Override
	public int size() {
		return this.getElements().size();
	}
}
