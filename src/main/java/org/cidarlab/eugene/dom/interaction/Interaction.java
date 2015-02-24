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

package org.cidarlab.eugene.dom.interaction;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.interaction.Participation.Role;
import org.cidarlab.eugene.exception.EugeneException;

import java.util.List;
import java.util.ArrayList;

public class Interaction 
		extends NamedElement {

	private static final long serialVersionUID = -5603391336825177408L;
	
	private InteractionType type;
	private List<Participation> participations;
	
	public enum InteractionType {
		INDUCES  {
			@Override
			public List<Participation> createParticipations(Component lhs, NamedElement rhs) {
				List<Participation> lop = new ArrayList<Participation>();
				lop.add(new Participation(Role.INDUCER, lhs));
				lop.add(new Participation(Role.INDUCEE, rhs));
				return lop;
			}
		}, 
		DRIVES {
			@Override
			public List<Participation> createParticipations(Component lhs, NamedElement rhs) {
				List<Participation> lop = new ArrayList<Participation>();
//				lop.add(new Participation(Role.INDUCER, lhs));
//				lop.add(new Participation(Role.INDUCER, lhs));
				return lop;
			}
		}, 		
		REPRESSES {
			@Override
			public List<Participation> createParticipations(Component lhs, NamedElement rhs) {
				List<Participation> lop = new ArrayList<Participation>();
				lop.add(new Participation(Role.REPRESSOR, lhs));
				lop.add(new Participation(Role.REPRESSEE, rhs));
				return lop;
			}
		};
		
		public abstract List<Participation> createParticipations(Component lhs, NamedElement rhs);
	}
	
	public Interaction(String name, Component lhs, InteractionType type, NamedElement rhs) 
			throws EugeneException {
		super(name);
		this.type = type;
		this.participations = this.type.createParticipations(lhs, rhs);
	}
	
	public InteractionType getType() {
		return this.type;
	}

	public List<Participation> getParticipations() {
		return this.participations;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("INTERACTION ").append(this.getName()).append(" TYPE ").append(this.getType()).append("[");
		for(Participation participation : this.getParticipations()) {
			sb.append(participation).append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		for(Participation p : this.getParticipations()) {
			if(p.getParticipant() instanceof Interaction) {
				NamedElement el = ((Interaction)p.getParticipant()).getElement(name);
				if(null != el) {
					return el;
				}
			} else if (p.getParticipant().getName().equals(name)) {
				return p.getParticipant();
			}
		}
		return (NamedElement)null;
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		// TODO Auto-generated method stub
		return null;
	}
}
