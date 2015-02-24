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

package org.cidarlab.sparrow.rule.compiler;

import java.util.HashSet;
import java.util.Set;

import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.rule.EugeneRule;
import org.cidarlab.sparrow.rule.Predicate;
import org.cidarlab.sparrow.rule.SparrowRule;

/**
 * The DroolsRule class is a Java representation of a Drools Rule.
 * In Eugene, we utilize DroolsRule as a transformer from a Eugene rule 
 * onto a Drools rule.
 * 
 * 
 * @author Ernst Oberortner
 */
public class Eugene2Drools {

	private EugeneRule eu_rule;
	private SparrowRule sp_rule;
	
	public Eugene2Drools(EugeneRule rule) {
		this.eu_rule = rule;
		this.sp_rule = null;
	}
	
	public Eugene2Drools(SparrowRule rule) {
		this.eu_rule = null;
		this.sp_rule = rule;
	}

	public String getName() {
		
		if(null != this.eu_rule) {
			return this.eu_rule.getName();
		}
		
		return this.sp_rule.getName();
	}
	
	public String getLhs() {
		if(null != this.sp_rule) {
			return this.sp_rule.getLHS();
		}
		return null;
	}
	
	public String getRhs() {
		if(null != this.sp_rule) {
			return this.sp_rule.getRHS();
		}
		return null;
	}
	
	public String getRule() {
		
		if(null != this.eu_rule) {
			return this.euRule();
		}
		return null;
	}
	
	/*
	 * OLD VERSION
	 */
	private String euRule() {
		/*
		 * here, we need to transform the Eugene rule 
		 * into Drools premises
		 */
		// in defined_variables we keep track of the 
		// $ variables of drools
		Set<String> defined_variables = new HashSet<String>();

		StringBuilder sb = new StringBuilder();
		
		sb.append("$p : Component (");
		for(int i=0; i<eu_rule.getPredicates().size(); i++) {
			Predicate predicate = eu_rule.getPredicates().get(i);
			if("type".equalsIgnoreCase(predicate.getLhs().getName())) {
				/*
				 * part type
				 */
				sb.append(predicate.getLhs().getName());
			} else if("name".equalsIgnoreCase(predicate.getLhs().getName())) {
				/*
				 * part name
				 */
				sb.append(predicate.getLhs().getName());	
			} else {
				
				/*
				 * property value
				 */
				if(!defined_variables.contains(predicate.getLhs().getName())) {
					sb.append("$").append(predicate.getLhs().getName()).append(":");
					sb.append("propertyValues[\"").append(predicate.getLhs().getName()).append("\"], $")
						// we need to check for NULL values explicitly
						.append(predicate.getLhs().getName()).append("!=null, $")
						.append(predicate.getLhs().getName()).append(".");
					
					defined_variables.add(predicate.getLhs().getName());
				} else {
					sb.append("$").append(predicate.getLhs().getName()).append(".");
				}
				
				// type of the property value
				if(SparrowConstants.NUM.equals(predicate.getLhs().getType()) ||
						SparrowConstants.TXT.equals(predicate.getLhs().getType())) {
					sb.append(predicate.getLhs().getType());
				} else if(SparrowConstants.NUMLIST.equals(predicate.getLhs().getType())) {
					sb.append("numList");
				} else if(SparrowConstants.TXTLIST.equals(predicate.getLhs().getType())) {
					sb.append("txtList");
				} else if(SparrowConstants.BOOLEAN.equals(predicate.getLhs().getType())) {
					sb.append("bool");
				}
				
			}
			
			sb.append(predicate.getOperator()).append(predicate.getRhs());
			
			if(i < eu_rule.getPredicates().size() - 1) {
				sb.append(", ");
			}
		}		
		sb.append(")");
		return sb.toString();
	}

}