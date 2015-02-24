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

package org.cidarlab.eugene.interp;

import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.rules.LogicalAnd;
import org.cidarlab.eugene.dom.rules.Predicate;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The RuleBuilder class serves to build Eugene rules.
 * A RuleBuilder is defined on a specific Device and it provides
 *  
 * @author Ernst Oberortner
 */
public class RuleBuilder {

	/**
	 * The instantiate/2 method takes as input the name of the 
	 * rule and a Device object on that the rule is being specified.
	 * 
	 * @param name  ... name of the rule
	 * @param d     ... the device on that rule is being specified
	 * 
	 * @return
	 */
	public Rule instantiate(String name, Device d) {
		return new Rule(name, d);
	}
			
	
	/**
	 * The and/1 method takes as input a rule and 
	 * appends it to the existing rules using the 
	 * logical AND operation (/\).
	 * 
	 * @param r  ... the rule to be appended
	 * 
	 * @throws EugeneException
	 */
	public void and(Rule r, Predicate p) 
			throws EugeneException {
		
		if(p instanceof Rule) {
			// if a rule is being ANDed to a rule, 
			// then we need to union them
			LogicalAnd rhs = ((Rule)p).getPredicates();
			for(Predicate rhsPredicate : rhs.getPredicates()) {
				
				// i.e. we add each predicate of the RHS rule to the 
				// LHS rule
				r.getPredicates().getPredicates().add(rhsPredicate);
			}
			
		} else {
			// otherwise, we do this.
			r.getPredicates().getPredicates().add(p);
		}
	}
}
