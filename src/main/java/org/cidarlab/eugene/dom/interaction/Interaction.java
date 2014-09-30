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
