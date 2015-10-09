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

package org.cidarlab.eugene.dom;

import java.util.List;

public class PartType 
	extends ComponentType {

	private static final long serialVersionUID = 1242448556255896751L;

	/**
	 * Constructor to instantiate a PartType w/o properties
	 * @param name
	 */
	public PartType(String name) {
		super(name);
	}
	
	/**
	 * Constructor to instantiate a PartType w/ properties
	 * 
	 * @param name
	 * @param proplist
	 */
	public PartType(String name, List<Property> proplist) {
		super(name, proplist);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PartType ").append(this.getName()).append("(");
		for(int i=0; i<this.getProperties().size(); i++) {
			sb.append(".").append(this.getProperties().get(i).getName()).append("(")
				.append(this.getProperties().get(i).getType()).append(")");
			
			if(i < this.getProperties().size() -1 ) {
				sb.append(", ");
			}
		}
		sb.append(");");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		int hash = this.getName().hashCode();
		if(null!=this.getProperties() && !this.getProperties().isEmpty()) {
			for(Property p : this.getProperties()) {
				hash += p.getName().hashCode();
			}
		}
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(!(o instanceof PartType)) {
			return false;
		}
		
		return this.hashCode() == ((PartType)o).hashCode();
	}

}
