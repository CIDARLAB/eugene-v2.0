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

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

import java.util.List;
import java.util.ArrayList;

/**
 * An EugeneArray is an ordered list of NamedElements. 
 * NamedElements can either be Variables or genetic elements (e.g. parts, types, etc).
 * In Eugene it is also possible to nest arrays, i.e. arrays 
 * can contain other arrays.
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneArray 
		extends EugeneContainer {

	private static final long serialVersionUID = -6690697983518570457L;

	private List<NamedElement> elements;
	
	public EugeneArray(String name) {
		super(name);
		this.elements = new ArrayList<NamedElement>();
	}
	
	public List<NamedElement> getElements() {
		return this.elements;
	}
	
	@Override
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("Array[] ").append(this.getName()).append(" (").append(NEWLINE);
		for(NamedElement ne : this.getElements()) {
			sb.append(ne/*.getName()*/).append(NEWLINE);
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
		
		if(null != ne/* && !this.contains(ne.getName())*/) {
			if(ne instanceof NamedElement) {
				this.getElements().add((NamedElement)ne);
			} else {
				throw new EugeneException("I cannot add the "+ne+" element to an array!");
			}
//		} else if(null != ne && this.contains(ne.getName())) {
//			throw new EugeneException("The " + ne.getName()+" element exists already in the "+this.getName()+" array!"); 
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
		if(null != this.getElements() && !this.getElements().isEmpty()) {
			if(idx < 0 || idx > this.getElements().size() - 1) {
				throw new EugeneException("The index "+idx+" is out of bounds on the "+this.getName()+" array!");
			}
			return this.getElements().get(idx);
		}
		return null;
	}
	
	public void setElement(int idx, NamedElement element) 
			throws EugeneException {		
		if(idx < 0 || idx > this.getElements().size() - 1) {
			throw new EugeneException("The index "+idx+" is out of bounds on the "+this.getName()+" array!");
		}

		if(element.getName().contains("-")) {  // i.e. a generated name
			
		} else {
			
		}

		this.elements.set(idx, element);
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
		} else if(!(o instanceof EugeneArray)) {
			return false;
		}
		
		return this.hashCode() == ((EugeneArray)o).hashCode();
	}

	@Override
	public int size() {
		return this.getElements().size();
	}
	
}
