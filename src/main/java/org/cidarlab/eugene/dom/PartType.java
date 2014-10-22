package org.cidarlab.eugene.dom;

import java.util.List;

public class PartType 
	extends ComponentType {

	private static final long serialVersionUID = 1242448556255896751L;

	public PartType(String name) {
		super(name);
	}
	
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
