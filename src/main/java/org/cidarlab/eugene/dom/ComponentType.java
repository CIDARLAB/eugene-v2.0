package org.cidarlab.eugene.dom;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.exception.EugeneException;

public class ComponentType 
	extends NamedElement {

	private static final long serialVersionUID = 1242448556255896751L;

	/*
	 * LIST OF PROPERTIES
	 */
	protected List<Property> properties; 
	
	public ComponentType(String name) {
		super(name);
		this.properties = new ArrayList<Property>();
	}
	
	public ComponentType(String name, List<Property> properties) {
		super(name);
		this.properties = properties;
	}
	
	public List<Property> getProperties() {
		return this.properties;
	}

	public Property getProperty(String sPropertyName) {

		for (Property p : this.getProperties()) {
			if (p.getName().equals(sPropertyName)) {
				return p;
			}
		}
		
		return (Property) null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type ").append(this.getName()).append("(");
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
	public NamedElement getElement(String name) {
		/*
		 * here, we iterate over the properties
		 */
		return this.getProperty(name);
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		if(idx < 0 || idx >= this.getProperties().size()) {
			throw new EugeneException(idx + " is out of bounds in the " + this.getName() +" type!");
		}
		return this.getProperties().get(idx);
	}

}
