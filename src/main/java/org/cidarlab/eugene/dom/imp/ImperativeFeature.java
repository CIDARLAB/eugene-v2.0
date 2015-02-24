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

/**
 * All Eugene imperative language features do not support 
 * the getElement(String) and getElement(int)
 * methods. Hence, we've introduced the ImperativeFeature abstract class 
 * to avoid implementing those functions in loops, branches, 
 * and function prototypes. 
 * 
 * @author Ernst Oberortner
 *
 */
public abstract class ImperativeFeature 
		extends StackElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386541171187688528L;

	public ImperativeFeature(String name) {
		super(name);
	}
	
	/*-----------------------------------
	 * ACCESSORS FOR SYMBOLS 
	 * (SCOPING)
	 *-----------------------------------*/
	@Override
	public boolean contains(String name) {
		return this.getSymbols().contains(name);
	}

	@Override
	public NamedElement get(String name) {
		return this.getSymbols().get(name);		
	}

	@Override
	public void put(NamedElement ne) 
			throws EugeneException {
		this.getSymbols().put(ne);
	}


	@Override
	public void clear() {
		this.getSymbols().clear();
	}
	
	/**
	 * The remove/1 method removes the NamedElement object with 
	 * the given name from the symbol tables.
	 * 
	 * @param name  ... the name of the NamedElement object that should be removed
	 * @throws EugeneException
	 */
	public void remove(String name) {
		this.getSymbols().removeVariable(name);
	}
	
	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		return this.get(name);
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new EugeneException("This is not supported!");
	}


}
