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

package org.cidarlab.eugene.dom.rules;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The ArrangementOperand class serves to hold information 
 * about rule operands. 

 * Example: CONTAINS x 
 * the operand x of the CONTAINS rule is an instance of 
 * the ArrangementOperand class. 
 *  
 * A rule operand is either characterized by
 * - a NamedElement object (e.g. x),
 * - a constant (e.g. 1), or
 * - an index (e.g. [i])
 * 
 * @author Ernst Oberortner
 *
 */
public class ArrangementOperand {

	private NamedElement element;
	private int constant;
	private int index;
	
	
	/**
	 * The ArrangementOperand/1 constructor takes as input three parameters
	 * where only one of them must be set.
	 * 
	 * @param element   ... the rule's operand is a NamedElement object
	 * @param constant  ... the rule's operand is a constant
	 * @param index     ... the rule's operand is an index
	 * @throws EugeneException
	 */
	public ArrangementOperand(NamedElement element, int constant, int index) 
			throws EugeneException {
		if(null != element) {
			this.element = element;
			this.constant = -1;
			this.index = -1;
		} else if(-1 != constant) {
			this.element = null;
			this.constant = constant;
			this.index = -1;
		} else if(-1 != index) {
			this.element = null;
			this.constant = -1;
			this.index = index;
		} else {
			throw new EugeneException("Invalid operand for arrangement constraint!");
		}
	}
	
	/*---------
	 * GETTERS
	 *---------*/
	public NamedElement getElement() {
		return this.element;
	}
	
	public boolean isElement() {
		return null != this.getElement();
	}
	
	public int getConstant() {
		return this.constant;
	}
	
	public boolean isConstant() {
		return -1 != this.getConstant();
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public boolean isIndex() {
		return -1 != this.getIndex();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.getElement()) {
			sb.append(this.getElement().getName());
		} else if (-1 != this.getConstant()) {
			sb.append(this.getConstant());
		} else if(-1 != this.getIndex()) {
			sb.append("[").append(this.getIndex()).append("]");
		}
		return sb.toString();
	}
}
