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

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.dom.rules.exp.ExpressionConstraint;

import com.rits.cloning.Cloner;

/**
 * The LogicalOr class serves to combine multiple predicates using the
 * logical or operator.
 * 
 * A LogicalOr can only be composed of LogicalNot and Predicates.
 * 
 * @author Ernst Oberortner
 *
 */
public class LogicalOr 
		implements Predicate {

	private List<Predicate> constraints;
	
	/**
	 * default constructor
	 */
	public LogicalOr() {
		this.constraints = new ArrayList<Predicate>();
	}
	
	/**
	 * 
	 * @return the list of disjunctive predicates
	 */
	public List<Predicate> getConstraints() {
		return this.constraints;
	}

	/**
	 * The validate/0 method validates the logical disjunction of constraints.
	 * A logical disjunction is valid, if all constraints are of the same type (either arrangement or expression). 
	 * 
	 * Examples:
	 * p before c \/ p.strength >= 0  ... is an invalid disjunction since it 
	 *                                    combines an arrangement and an expression constraint
	 * p before c \/ c before p       ... is a valid disjunction
	 * p.strength >=0 \/ c.expression >= 100 ... is a valid disjunction
	 * 
	 * @return true ... if all disjunctive predicates are of the same type
	 */
	public boolean validate() {
		/*
		 * iterate over the predicates and compare each one 
		 * with its predecessor
		 */

		Predicate p0 = this.getConstraints().get(0);
		if(p0 instanceof LogicalNot) {
			p0 = ((LogicalNot) p0).getPredicate();
		}
		
		for(int i=1; i<this.getConstraints().size(); i++) {
			Predicate pi = this.getConstraints().get(i);
			
			/*
			 * dealing with negated predicates (e.g. not contains p)
			 */
			if(pi instanceof LogicalNot) {
				pi = ((LogicalNot) pi).getPredicate();
			}
			
			/*
			 * 
			 */
			if(! (p0 instanceof ExpressionConstraint && pi instanceof ExpressionConstraint ||
			      p0 instanceof ArrangementConstraint && pi instanceof ArrangementConstraint)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param constraints a list of conjunctive constraints
	 */
	public void setConstraints(List<Predicate> constraints) {
		this.constraints = new Cloner().deepClone(constraints);		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.getConstraints().size(); i++) {
			sb.append(this.getConstraints().get(i));
			if(i < this.getConstraints().size() - 1) {
				sb.append(" OR ");
			}
		}
		return sb.toString();
	}
	
}
