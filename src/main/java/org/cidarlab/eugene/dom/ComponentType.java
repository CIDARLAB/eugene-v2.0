package org.cidarlab.eugene.dom;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.exception.EugeneException;

/**
 * A ComponentType represents an abstract genetic component, such as part type. 
 * In Eugene, every Component has a ComponentType. A ComponentType has a list of 
 * properties that the Component instantiates (so-called Property Values).
 *  
 * @author Ernst Oberortner
 *
 */
public class ComponentType 
	extends NamedElement {

	private static final long serialVersionUID = 1242448556255896751L;

	/*
	 * LIST OF PROPERTIES
	 */
	protected List<Property> properties; 
	
	/**
	 * Constructor w/ name
	 * 
	 * @param name   ... the name of the ComponentType
	 */
	public ComponentType(String name) {
		super(name);
		this.properties = new ArrayList<Property>();
	}
	
	/**
	 * Constructor w/ name and properties
	 * 
	 * @param name   ... the name of the ComponentType
	 * @param properties ... a list of properties of the type
	 */
	public ComponentType(String name, List<Property> properties) {
		super(name);
		this.properties = properties;
	}
	
	
	/**
	 * The getProperties() method returns the list of properties 
	 * of the ComponentType
	 * 
	 * @return  the of the ComponentType's properties
	 */
	public List<Property> getProperties() {
		return this.properties;
	}

	/**
	 * The getProperty(String) method returns a property of 
	 * a given name. If the property does not exist, then 
	 * it returns NULL.
	 *  
	 * @param name   ... the name of the desired Property
	 * @return   ... the Property if it exists, NULL otherwise
	 */
	public Property getProperty(String name) {
		for (Property p : this.getProperties()) {
			if (p.getName().equals(name)) {
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
