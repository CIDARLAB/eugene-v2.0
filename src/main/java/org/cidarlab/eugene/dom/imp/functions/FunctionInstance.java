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

package org.cidarlab.eugene.dom.imp.functions;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.imp.ImperativeFeature;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The FunctionInstance class represents an executing function. That is,
 * an executing function is derived from a FunctionPrototype and contains 
 * local symbol tables that are initialized with the passed parameters.
 * 
 * @author Ernst Oberortner
 *
 */
public class FunctionInstance 
		extends ImperativeFeature {

	private static final long serialVersionUID = 2937340332998400619L;

	// an instance of a Function holds a reference to the 
	// function prototype
	private FunctionPrototype fp;
	
	// it also has local symbol tables, but they are 
	// inherited from the ImperativeFeature super class.
	
	/**
	 * To instantiate an object of the FunctionInstance class, you 
	 * must give it a name and a reference to its prototype.
	 * @param name ... the name of the function instance
	 *                 convention: <name-of-function>_<current-time-millis>
	 * @param fp   ... a reference to the function prototype 
	 */
	public FunctionInstance(String name, FunctionPrototype fp) {
		super(name);
		
		this.fp = fp;
	}	
	
	/**
	 * The getPrototype/0 method returns the reference 
	 * to the prototype of the function instance
	 * @return
	 */
	public FunctionPrototype getPrototype() {
		return this.fp;
	}
	
	@Override
	public NamedElement get(String name) {
//		System.out.println("[FunctionInstance.get] -> " + this.getName()+", "+name);

		// first, we check the local symbols
		NamedElement ne = this.getSymbols().get(name);
		
		if(null == ne) {
			
			// then, we also check the global ones
			ne = this.fp.get(name);
			
		}
		
		// finally we return it (if found or not)
		return ne;
	}

	@Override
	public boolean contains(String name) {
		return this.getSymbols().contains(name);
	}

	@Override
	public void put(NamedElement ne) 
			throws EugeneException {

//		System.out.println("[FunctionInstance.put] -> " + this.getName()+", "+ne.getName()+", " +ne);
		
		if(null != ne && !this.contains(ne.getName())) {
			this.getSymbols().put(ne);
		}
	}

	@Override
	public void clear() {
		this.getSymbols().clear();
	}

}
