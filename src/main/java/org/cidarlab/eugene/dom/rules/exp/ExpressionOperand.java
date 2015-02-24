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

package org.cidarlab.eugene.dom.rules.exp;

import java.util.List;
import java.util.ArrayList;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The ExpressionOperand class is the type of 
 * an operand of an expression rule.
 * e.g
 * Promoter.Strength
 * 5
 * 
 * @author Ernst Oberortner
 *
 */
public class ExpressionOperand {

	// e.g. Inverter.TU1.Promoter
	// -> elements := [Inverter, TU1, Promoter]
	private List<NamedElement> elements;
	
	// e.g. Strength, Sequence
	private Property property;
	
	// Variable is being used for constants
	// e.g. "ATCG", 5
	private Variable constant;
	
	// Indices
	// e.g. txtList[0] -> 
	private List<Integer> indices;
	
	public ExpressionOperand(List<NamedElement> elements, Property property) {
		this.elements = elements;
		this.property = property;
		this.constant = null;
		this.indices = null;
	}
	
	public ExpressionOperand(Variable constant) {
		this.constant = constant;
		this.elements = null;
		this.property = null;
		this.indices = null;
	}
	
	public Variable getConstant() {
		return this.constant;
	}
	
	public List<NamedElement> getElements() {
		return this.elements;
	}
	
	public Property getProperty() {
		return this.property;
	}
	
	public List<Integer> getIndices() {
		return this.indices;
	}
	
	public void addIndex(String idx) 
			throws EugeneException {
		
		if(null == this.getIndices()) {
			this.indices = new ArrayList<Integer>();
		}
		
		try {
			this.getIndices().add(Integer.parseInt(idx));
		} catch(Exception e) {
			throw new EugeneException(idx + " is an invalid index!");
		}
		
	}
	
	public String getType() {

		if(null != this.constant) {
			return this.constant.getType();
		} 

		return this.property.getType();		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.getConstant()) {
			sb.append(this.getConstant().getValue());
		} else if (null != this.getElements()) {
			for(int i=0; i<this.getElements().size(); i++) {
				sb.append(this.getElements().get(i).getName()).append(".");
			}
			if(null != this.getProperty()) {
				sb.append(this.getProperty().getName());
			}
			
		}
		return sb.toString();
	}
	
	public boolean isConstant() {
		return this.getConstant()!=null;
	}
}
