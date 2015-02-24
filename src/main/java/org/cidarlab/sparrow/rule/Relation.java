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
 * x has a relation to y
 * 
 * @author E. Oberortner
 *
 */
public class Relation 
		extends NamedElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3015139709378484720L;
	private NamedElement lhs;
	private RelationType type;
	private NamedElement rhs;
	
	
	public enum RelationType {
		ORTHOGONAL
	} 
	
	public Relation(NamedElement lhs, RelationType type, NamedElement rhs) {
		super(UUID.randomUUID().toString());
		this.lhs = lhs;
		this.type = type;
		this.rhs = rhs;
	}
	
	public NamedElement getLHS() {
		return this.lhs;
	}
	
	public String getType() {
		return this.type.toString();
	}
	
	public NamedElement getRHS() {
		return this.rhs;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getLHS().getName()).append(" ")
			.append(this.getType()).append(" ")
			.append(this.getRHS().getName());
		return sb.toString();
	}

}
