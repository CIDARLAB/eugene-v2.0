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

package org.cidarlab.sparrow.rule;

import org.cidarlab.eugene.dom.Property;
import org.cidarlab.sparrow.constants.SparrowConstants;

/**
 * A Predicate is a constraint that neest to be satisfied.
 * Example: sequence == "ATCG" 
 * 
 * In Eugene, a predicate consists of a left-hand-side (LHS), an expression operator, 
 * and a right-hand-side (RHS). 
 * Example:
 *             ==
 *           /    \
 *  sequence       "ATCG"
 *  
 * @author Ernst Oberortner
 */
public class Predicate {

	private Property lhs;
	private String operator;
	private String rhs;
	
	public Predicate(Property lhs, String operator, String rhs) {
		this.lhs = lhs;
		this.operator = operator;
		this.rhs = rhs;
	}
	
	public Property getLhs() {
		return this.lhs;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public String getRhs() {
		if(SparrowConstants.TXT.equals(this.lhs.getType()) ||
				SparrowConstants.TXTLIST.equals(this.lhs.getType())) {
			return "\""+this.rhs+"\"";
		} else if (SparrowConstants.NUM.equals(this.lhs.getType()) ||
				SparrowConstants.NUMLIST.equals(this.lhs.getType())) {
			return this.rhs;
		} else if (SparrowConstants.BOOLEAN.equals(this.lhs.getType())) {
			return this.rhs;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.lhs.getName()).append(" ").append(this.operator).append(" ").append(this.rhs);
		return sb.toString();
	}
}
