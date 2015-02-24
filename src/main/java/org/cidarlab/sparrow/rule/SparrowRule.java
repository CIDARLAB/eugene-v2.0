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
public class SparrowRule 
		extends NamedElement {

	private static final long serialVersionUID = -5221976854047668810L;
	private String lhs;
	private String rhs;
	
	public SparrowRule(String name, String lhs, String rhs) {
		super("name_"+UUID.randomUUID().toString().replace('-', '_'));
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public String getLHS() {
		return this.lhs;
	}
	
	public String getRHS() {
		return this.rhs;
	}
	
	@Override
	public String toString() {
		return "IF " + this.getLHS() +" THEN " + this.getRHS();
	}
	
	
}
