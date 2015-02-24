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

import org.cidarlab.eugene.dom.rules.SelectionPredicate;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The ExpressionConstraint class serves for expression rules.
 * e.g. 
 * Promoter.Strength > 5
 * CDS.Sequence MATCHES *AT*
 * 
 * @author Ernst Oberortner
 *
 */
public class ExpressionConstraint 
		implements SelectionPredicate {

	/*
	 * An expression constraint consists of:
	 * - a left-hand-side
	 * - a relational operator (!=, ==, >=, ...)
	 * - a right-hand-side
	 */

	private Expression lhs;
	private String rel_op;
	private Expression rhs;
	
	public ExpressionConstraint(Expression lhs, String rel_op, Expression rhs) 
			throws EugeneException {
		
		this.lhs = lhs;
		this.rel_op = rel_op;
		this.rhs = rhs;
	}
	
	public Expression getLhs() {
		return this.lhs;
	}
	
	public String getOp() {
		return this.rel_op;
	}
	
	public Expression getRhs() {
		return this.rhs;
	}
	

	/*
	 * the setOp method is only being used be the negate/0 method
	 */
	private void setOp(String op) {
		this.rel_op = op;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getLhs()).append(" ").append(this.getOp()).append(" ").append(this.getRhs());
		return sb.toString();
	}

	/**
	 * The negate/0 method negates the expression constraint.
	 * i.e. it changes the relational operator.
	 * "==" => "!="
	 * "<"  => ">="
	 * ...
	 */
	public void negate() {
		if("==".equals(this.getOp())) {
			this.setOp("!=");
		} else if("!=".equals(this.getOp())) {
			this.setOp("==");
		} else if("<".equals(this.getOp())) {
			this.setOp(">=");
		} else if("<=".equals(this.getOp())) {
			this.setOp(">");
		} else if(">=".equals(this.getOp())) {
			this.setOp("<");
		} else if(">".equals(this.getOp())) {
			this.setOp("<=");
		} else {
			this.setOp("not "+this.getOp());
		}
	}
	
	/**
	 * The isQuery/0 method returns true iff the expression constraint 
	 * is a Query.
	 * A Query is an expression constraint where one of the operands 
	 * is a constant and the other operand is not an expression.
	 * 
	 * @return
	 */
	public boolean isQuery() {
//		System.out.println("[isQuery] -> "+this +" -> "+(this.getLhs().isConstant() || this.getRhs().isConstant()));
		return this.getLhs().isConstant() || this.getRhs().isConstant();
	}
	
	public boolean isPrunable() {
		if(this.getLhs().isConstant()) {
			return this.getRhs().getRhs() == null;
		} else if(this.getRhs().isConstant()) {
			return this.getLhs().getRhs() == null;
		}
		return false;
	}
}
