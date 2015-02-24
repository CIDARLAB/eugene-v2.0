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

import java.io.Serializable;
import java.util.UUID;

import org.cidarlab.eugene.exception.EugeneException;

/**
 * The NamedElement class represents the top-most base class 
 * of the inheritance tree of Eugene's data model. Hence, NamedElement 
 * is an abstract class.
 * 
 * Every object in Eugene MUST have a unique name throughout the 
 * scope of an Eugene script. Automatically we also generate a 
 * non-negative integer ID for the NamedElement object which is being used
 * in particular for the Constraint Solving process. 
 * 
 * @author Ernst Oberortner
 *
 */
public abstract class NamedElement 
		implements Serializable {

	private static final long serialVersionUID = 7803721755204206476L;

	/*
	 * ID (automatically generated)
	 */
	private int id;
	
	/*
	 * NAME
	 */
	private String name;
	
	/**
	 * Constructor
	 * 
	 * If a NamedElement has a NULL name, then it is treated as Anonymous variable.
	 * If a NamedElement has an empty name, then an IllegalArgumentException is thrown.
	 * Otherwise, the NamedElement gets the specified name
	 * 
	 * @param name  the name of the NamedElement object
	 */
	public NamedElement(String name) {

		// error checking
		if(null == name) {   // ANONYMOUS 
			this.setName(UUID.randomUUID().toString());
			
		} else if(name.isEmpty()) {   // an empty name is invalid!
			throw new IllegalArgumentException("Invalid name!");
			
		} else {
			// set the name
			this.setName(name);
		}
		
		// ID
		this.id = this.hashName();
	}
	
	/**
	 * the setName/1 method is used to set the name 
	 * of the NamedElement. 
	 * This method is used in the interpreter for 
	 * assignment statements, particularly if the 
	 * RHS of an assignment needs to be named as specified 
	 * on the LHS of an assignment.
	 * 
	 * @param name ... the name of the NamedElement object
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * The private hashName() method hashes the name into 
	 * a non-negative integer that represents the ID of 
	 * the NamedElement object
	 * 
	 * @return a non-negative integer representing the ID of the NamedElement object
	 */
	private int hashName() {
		if(null != this.getName()) {
			int hash = this.getName().hashCode();
			if(hash < 0) {
				return hash * -1;
			}
			return hash;
		} 
		return 0;
	}
	
	
	/*-----------------------------
	 * GETTERS
	 *-----------------------------*/
	
	/**
	 * The getID() method returns the automatically generated 
	 * ID of the NamedElement object.
	 * 
	 * @return  the ID of the NamedElement object
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * The getName() method returns the name of the 
	 * NamedElement object.
	 * 
	 * @return  the name of the NamedElement object
	 */
	public String getName() {
		return this.name;
	}
	
	
	/*-----------------------------
	 * ABSTRACT METHODS
	 * 
	 * for retrieving sub-elements of
	 * NamedElements, such as from Eugene containers 
	 * or devices.   
	 * 
	 * all primitive NamedElement sub-classes (Part, Type, Property etc) 
	 * will throw an EugeneException since they do not have sub-elements
	 *-----------------------------*/
	
	/**
	 * The getElement/1 method returns the element named with name. 
	 * If the element does not exist, then getElement/1 returns NULL. 
	 * If the object does not support the getElement/1 method, then 
	 * an EugeneException is thrown.
	 * 
	 * @param name ... the name of the desired element
	 * 
	 * @return  the requested NamedElement
	 * 
	 * @throws EugeneException
	 */
	public abstract NamedElement getElement(String name)
		throws EugeneException;
	
	/**
	 * The getElement/1 method returns the element at the given 
	 * index idx. In Eugene, indices start at position 0. 
	 * If the index is out of bounds, then an EugeneException is 
	 * thrown. 
	 * 
	 * @param idx  ... the idx of the desired element
	 * @return the element at index idx
	 * @throws EugeneException
	 */
	public abstract NamedElement getElement(int idx)
		throws EugeneException;
	
}
