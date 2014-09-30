package org.cidarlab.eugene.dom.interaction;

import org.cidarlab.eugene.dom.NamedElement;

public class Participation {

	private Role role;
	private NamedElement participant;
	
	public enum Role {
		REPRESSEE, REPRESSOR, INDUCER, INDUCEE
	}
	
	protected Participation(Role role, NamedElement participant) {
		this.role = role;
		this.participant = participant;		
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public NamedElement getParticipant() {
		return this.participant;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getRole()).append(": ").append(this.getParticipant());
		return sb.toString();
	}
}
