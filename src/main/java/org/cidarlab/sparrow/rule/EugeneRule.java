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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A Eugene rule is of the form:
 * IF <premise> THEN <conclusion>
 * 
 * The <premise> is a list of predicates (conjunction).
 * The <conclusion> specifies what needs to happen (or what 
 * we've learned) if the conjunction of the predicates is TRUE.
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneRule 
		extends NamedElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7718948136863290639L;
	private List<Predicate> predicates;
	
	/* TODO: 
	 * - think and do something with conclusions
	 *  
	private String conclusion;
	 */
	
	public EugeneRule() {
		super("query_"+UUID.randomUUID().toString().replace('-', '_'));
		
		this.predicates = new ArrayList<Predicate>();
	}
	
	public EugeneRule(String name) {
		super(name);
		
		this.predicates = new ArrayList<Predicate>();
	}

	public EugeneRule(String name, Predicate predicate) {
		super(name);
		this.predicates = new ArrayList<Predicate>();
		this.getPredicates().add(predicate);
	}
	
	public EugeneRule(String name, List<Predicate> predicates) {
		super(name);
		
		this.predicates = predicates;
	}
	
	public List<Predicate> getPredicates() {
		return this.predicates;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append(" #= ");
		if(null != this.predicates && !this.predicates.isEmpty()) {
			for(int i=0; i<predicates.size(); i++) {
				sb.append(this.predicates.get(i));
				if(i < predicates.size() - 1) {
					sb.append(" /\\ ");
				}
			}
		}
		return sb.toString();
	}
}
