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

import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.rules.exp.ExpressionConstraint;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * In Eugene, we utilize the Disjunctive Normal Form, 
 * a disjunction of conjunctions.
 * When specifying multiple rules on one device, then 
 * Eugene builds the disjunction of those rules. 
 * 
 * Since rules can be logically composed together, 
 * the Rule class implements the Predicate interface. 
 * 
 * @author Ernst Oberortner
 */
public class Rule 
	extends NamedElement 
	implements ArrangementPredicate {

	private static final long serialVersionUID = -6536162873444956906L;

	/*
	 * conjunctive predicates
	 */
	private LogicalAnd predicates;
	
	/*
	 * the device on that the rule is specified 
	 */
	private Device device;
	
	/**
	 * Constructor for rules that are not specified on a device
	 * 
	 * @param name ... the name of the rule
	 */
	public Rule(String name) {
		super(name);
		this.device = null;
		this.predicates = new LogicalAnd();
	}
	
	/**
	 * Constructor for rules that are specified on a specific device
	 *
	 * @param name ... the name of the rule
	 * @param device ... a reference to the device
	 */
	public Rule(String name, Device device) {
		super(name);
		this.device = device;
		this.predicates = new LogicalAnd();
	}
	
	/**
	 * 
	 * @return the list of conjunctive predicates
	 */
	public LogicalAnd getPredicates() {
		return this.predicates;
	}
	
	
	/**
	 * 
	 * @return the device on that the rule is being specified
	 */
	public Device getDevice() {
		return this.device;
	}
	
	/**
	 * 
	 * @param land  the logical and predicate
	 */
	public void setLogicalAnd(LogicalAnd land) {
		this.predicates = land;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Rule ").append(this.getName()).append("(");
		if(null != this.getDevice()) {
			sb.append("ON ").append(this.getDevice().getName()).append(": ");
		}
		sb.append(this.getPredicates());
		sb.append(");");
		return sb.toString();
	}

	/**
	 * the hasExpression/0 method evaluates if the rule has 
	 * at least one expression constraint in it. 
	 * 
	 * @return true  ... if the rule has at least one expression constraint
	 *         false ... otherwise
	 */
	public boolean hasExpression() {
		if(null != this.getPredicates() && 
				null != this.getPredicates().getPredicates() && 
				!this.getPredicates().getPredicates().isEmpty()) {
			
			for(Predicate p : this.getPredicates().getPredicates()) {
				if(p instanceof ExpressionConstraint) {
					return true;
				} else if (p instanceof LogicalOr && 
						((LogicalOr)p).getConstraints().get(0) instanceof ExpressionConstraint) {
					return true;
				} else if (p instanceof LogicalNot &&
						((LogicalNot)p).getPredicate() instanceof ExpressionConstraint) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * on a rule, the getElement method can only 
	 * return the device if specified.
	 */
	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		if(null != this.getDevice()) {
			if(this.getDevice().getName().equals(name)) {
				return this.getDevice();
			}
		}
		return null;
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new EugeneException("Eugene rules do not support access through array indices!");
	}
}
