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
import java.util.List;
import java.util.ArrayList;

import com.rits.cloning.Cloner;

/**
 * The LogicalAnd combines a list of conjunctive constraints.
 * 
 * The LogicalAnd does NOT implement the Constraint interface since 
 * Eugene supports the Disjunctive Normal Form.
 * 
 * @author Ernst Oberortner
 */
public class LogicalAnd 
		implements Serializable {

	private static final long serialVersionUID = 9068664189498644832L;
	
	/*
	 * A List of constraints, which can either be
	 * LogicalOr, LogicalNot, or Predicate (i.e. primitive constraints)
	 * 
	 */
	private List<Predicate> predicates;
	
	/**
	 * default constructor
	 */
	public LogicalAnd() {
		this.predicates = new ArrayList<Predicate>();
	}
	
	/**
	 * 
	 * @return the list of conjunctive constraints
	 */
	public List<Predicate> getPredicates() {
		return this.predicates;
	}

	/**
	 * 
	 * @param constraints a list of conjunctive constraints
	 */
	public void setPredicates(List<Predicate> constraints) {
		this.predicates = new Cloner().deepClone(constraints);		
	}
	
	/**
	 * The union/1 method adds all constraints of a given LogicalAnd predicate 
	 * to this LogicalAnd predicate
	 * @param land
	 */
	public void union(LogicalAnd land) {
		if(null != land && null!=land.getPredicates() && !land.getPredicates().isEmpty()) {
			for(Predicate c : land.getPredicates()) {
				this.getPredicates().add(c);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.getPredicates().size(); i++) {
			sb.append(this.getPredicates().get(i));
			if(i < this.getPredicates().size() - 1) {
				sb.append(" AND ");
			}
		}
		return sb.toString();
	}
}
