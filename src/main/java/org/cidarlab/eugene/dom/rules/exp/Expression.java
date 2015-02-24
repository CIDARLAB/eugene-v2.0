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


/**
 * An expression is an operand of an expression rule and has
 * the following format:
 * exp-operand ( exp-op exp-operand )*
 * exp-op := {'+', '-', '*', '/'}
 * 
 * Example:
 * Promoter.strength + RBS.strength
 * 
 * @author Ernst Oberortner
 */
public class Expression {
	
	public enum ExpOp {
		PLUS, MINUS, MULT, DIV
	}
	
	/*
	 * the left-hand-side is an expression operand
	 * since we resolve an expression from left to right
	 */
	private ExpressionOperand lhs;

	/*
	 * if the expression has an operator and a rhs
	 * 
	 * <lhs> <op> <rhs>
	 * 
	 * e.g. Promoter.strength + RBS.strength
	 * => 
	 * lhs := Promoter.strength
	 * op := +
	 * rhs := RBS.strength
	 */
	private ExpOp op;
	private Expression rhs;
	
	public Expression(ExpressionOperand lhs, ExpOp op, Expression rhs) {		
		this.lhs = lhs;
		this.op = op;
		this.rhs = rhs;
	}
	
	public ExpressionOperand getLhs() {
		return this.lhs;
	}
	
	public ExpOp getOp() {
		return this.op;
	}
	
	public Expression getRhs() {
		return this.rhs;
	}
	
	public String getType() {
		if(null != rhs) {
			return this.rhs.getType();
		}
		if(null != this.lhs.getProperty()) {
			return this.lhs.getProperty().getType();
		} 
		return this.lhs.getConstant().getType();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getLhs());
		if(null != this.getOp() && null != this.getRhs()) {
			sb.append(" ").append(this.getOp())
				.append(" ").append(this.getLhs());
		}
		return sb.toString();
	}
	
	/**
	 * The isConstant/0 method returns true if the operand 
	 * is a constant. 
	 * An expression operand is a constant if the Variable constant 
	 * is set. 
	 * 
	 * @return true  ... iff the expression operand is a constant
	 *         false ... otherwise
	 */
	public boolean isConstant() {
		return this.getOp()==null && this.getRhs()==null && this.getLhs().getConstant()!=null;
	}
	
	public boolean isIndexed() {
		
		return true;
	}

}
