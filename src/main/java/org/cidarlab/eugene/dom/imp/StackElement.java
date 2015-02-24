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

package org.cidarlab.eugene.dom.imp;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;

/**
 * In Eugene, a StackElement represents an imperative language feature, such as 
 * Loops, Branches, and Functions. 
 * 
 * A StackElement can have a name (such as functions). If no name is provided (such as for loops and branches), 
 * then we generate a random name using Java's UUID features.
 * Every StackElement has its own local symbol tables, represented by the symbols 
 * variable.
 * However, the entire control of putting, retrieving, and searching for variables/symbols 
 * during the execution/interpretation of a Eugene script is done in the interpreter (Interp).
 * 
 * Also, we characterize Eugene containers as StackElements since a container's elements 
 * are only visible within the container, and, hence, scoping is needed.
 * 
 * @author Ernst Oberortner
 *
 */
public abstract class StackElement 
		extends NamedElement {

	private static final long serialVersionUID = -5593349465159567676L;
	private SymbolTable symbols;
	
	public StackElement() {
		super(null);
		
		this.symbols = new SymbolTable();
	}
	
	public StackElement(String name) {
		super(name);
		
		this.symbols = new SymbolTable();
	}
	
	/*
	 * common methods
	 */	
	protected SymbolTable getSymbols() {
		return this.symbols;
	}
	
	
	/**
	 * The get/1 method returns the requested NamedElement 
	 * 
	 * @param name ... the name of the required NamedElement
	 * 
	 * @return  ... the NamedElement object with the provided name
	 *              null if the NamedElement has not been declared
	 *              
	 * @throws EugeneException
	 */
	public abstract NamedElement get(String name);
	
	/**
	 * The contains/1 method checks if its symbol tables contain 
	 * a NamedElement with the provided name. If it does not 
	 * exist, then we return false. otherwise true.
	 * @param name ... the name of the wanted NamedElement object
	 * @return true ... if the NamedElement object exists
	 *         false .. otherwise
	 * @throws EugeneException
	 */
	public abstract boolean contains(String name);
	
	/**
	 * The put/1 method inserts the given NamedElement object into 
	 * the local symbol tables.
	 * 
	 * @param ne ... the NamedElement object that must be stored in the
	 *               local symbol tables
	 *               
	 * @throws EugeneException
	 */
	public abstract void put(NamedElement ne)
			throws EugeneException;
	
	
	/**
	 * The clear/0 method cleans the local symbol tables
	 */
	public abstract void clear();
	
}
