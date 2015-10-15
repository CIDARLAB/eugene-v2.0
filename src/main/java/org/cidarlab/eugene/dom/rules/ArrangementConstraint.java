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

import java.io.Serializable;

/**
 * The PrimtiveConstraint class represents one Eugene constraint.
 * 
 * Examples:
 * CONTAINS x
 * 
 * A constraint is represented as a string array of tokens.
 * Example:
 * ["CONTAINS", "x"]
 * 
 * @author Ernst Oberortner
 *
 */
public class ArrangementConstraint 
		implements ArrangementPredicate, Serializable {

	private static final long serialVersionUID = 2066412931466489691L;

	private ArrangementOperand lhs;
	private String op;
	private ArrangementOperand rhs;
	
	/**
	 * 
	 * @param tokens an array of tokens
	 */
	public ArrangementConstraint(ArrangementOperand lhs, String op, ArrangementOperand rhs) {
		this.lhs = lhs;
		this.op = op;
		this.rhs = rhs;
	}
	
	public ArrangementOperand getLhs() {
		return this.lhs;
	}
	
	public String getOp() {
		return this.op;
	}
	
	public ArrangementOperand getRhs() {
		return this.rhs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.getLhs()) {
			sb.append(this.getLhs()).append(" ");
		} 
		sb.append(this.getOp());
		if(null != this.getRhs()) {
			sb.append(" ").append(this.getRhs());
		}
		return sb.toString();
	}

	
	/*
	 * HELPER METHODS
	 */
	public boolean isBinary() {
		return this.getLhs() != null && this.getRhs() != null;
	}
	
	public boolean isUnary() {
		return this.getLhs() == null && this.getRhs() != null;
	}
	
	public boolean isNullary() {
		return this.getLhs() == null && this.getRhs() == null;
	}
}
