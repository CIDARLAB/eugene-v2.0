/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.eugene.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.sparrow.exception.DOMException;

/**
 * In Eugene, Components are a super-type of genetic elements (such as parts).
 * A Component has three predefined characteristics:
 * - a sequence (represented as String),
 * - an orientation (Orientation enum), 
 * - a type (reference to a ComponentType instance)
 * - an additional list of properties, and
 * - a set of property values
 * 
 * Besides, components can have user-defined characteristics, which are 
 * in Eugene terminology, Properties.
 * 
 * In general, we differentiate between composite components (devices) and 
 * primitive components (parts).
 * 
 * @author Ernst Oberortner
 */
public class Component 
	extends NamedElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7841606660339005799L;

	/*
	 * SEQUENCE
	 */
	private String sequence;
	
	/*
	 * ORIENTATION
	 */
	private Orientation orientation;
	
	/*
	 * LIST OF PROPERTIES
	 */
	protected List<Property> properties; 
	
	/*
	 * TYPE OF THE COMPONENT
	 */
	protected ComponentType type;
	
	/*
	 * PROPERTY VALUES
	 */
	private HashMap<String, PropertyValue> hmPropertiesValues;

	public Component(String name) {
		super(name);
		this.type = null;
		this.sequence = new String();
		this.properties = new ArrayList<Property>();
		this.orientation = Orientation.UNDEFINED;
		this.hmPropertiesValues = new HashMap<String, PropertyValue>();
	}
	
	public Component(ComponentType type, String name) {
		super(name);
		this.type = type;
		this.sequence = new String();
		this.properties = new ArrayList<Property>();
		this.orientation = Orientation.UNDEFINED;
		this.hmPropertiesValues = new HashMap<String, PropertyValue>();
	}
	
	public Component(String sName, List<Property> properties) {
		super(sName);
		this.properties = properties;
		this.sequence = new String();
		this.orientation = Orientation.UNDEFINED;
		this.hmPropertiesValues = new HashMap<String, PropertyValue>();
	}

	public String getSequence() {
		return this.sequence;
	}
	
	public String getTypeAsString() {
		if(null != this.getType()) {
			return this.getType().getName();
		}
		return "";
	}
	
	public ComponentType getType() {
		return this.type;
	}
	
	
	/**
	 * 
	 * @param isForward
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * 
	 * @return the orientation of the component
	 */
	public Orientation getOrientation() {
		return this.orientation;
	}
	
	/*
	 * PROPERTY METHODS 
	 */
	public void addProperty(Property objProperty) {
		if (null != objProperty) {
			this.properties.add(objProperty);
		}
	}

	public void addProperties(List<Property> lst) {
		this.properties.addAll(lst);
	}
	
	public void setProperties(List<Property> lst) {
		this.properties = new ArrayList<Property>(lst);
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
		
		if(null != this.getType() && null != this.getType().getProperties()) {
			return this.getType().getProperty(sPropertyName);
		}
		
		return (Property) null;
	}
	
	/*
	 * SEQUENCE
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public String toSequence() {
		return this.sequence;
	}
	
	/*
	 * PROPERTY VALUES
	 */
	public Map<String, PropertyValue> getPropertyValues() {
//		System.out.println("[Component.getPropertyValues] -> "+this.hmPropertiesValues);
		return this.hmPropertiesValues;
	}
	public PropertyValue getPropertyValue(String sPropertyName) {
		return this.hmPropertiesValues.get(sPropertyName);
	}
	
	public void setPropertyValue(Variable v) 
			throws DOMException {
		/*
		 * convert variable to property value
		 */
		PropertyValue pv = new PropertyValue(new Property("anonymous", v.getType()));
		if(EugeneConstants.TXT.equals(v.getType())) {
			pv.setTxt(v.getTxt());
		} else if(EugeneConstants.TXTLIST.equals(v.getType())) {
			pv.setTxtList(v.getTxtList());
		} else if(EugeneConstants.NUM.equals(v.getType())) {
			pv.setNum(v.getNum());
		} else if(EugeneConstants.NUMLIST.equals(v.getType())) {
			pv.setNumList(v.getNumList());
		} else if(EugeneConstants.BOOLEAN.equals(v.getType())) {
			pv.setBool(v.getBool());
		} else {
			throw new DOMException("Incompatible Type!");
		}
		
		this.hmPropertiesValues.put(v.getName(), pv);
	}
	
	public void setPropertyValue(PropertyValue pv) 
			throws DOMException {
		// if the component does not have a property ``property'', 
		// then we add it to the properties list
		if(!this.getProperties().contains(pv.getProperty())) {
			this.getProperties().add(pv.getProperty());
		}
		
		// compare the types
		if(!pv.getProperty().getType().equalsIgnoreCase(pv.getType())) {
			throw new DOMException("Incompatible Types!");
		}

		this.hmPropertiesValues.put(
				pv.getProperty().getName(), 
				pv);
	}

	public void setPropertyValue(Property property, Variable v) 
			throws DOMException {
		
		if(!property.getType().equalsIgnoreCase(v.getType())) {
			throw new DOMException("Incompatible Types!");			
		}
		
		v.setName(property.getName());
		this.setPropertyValue(v);
	}
	
	public void setPropertyValue(Property property, PropertyValue pv) 
			throws DOMException {

		if(!property.getType().equalsIgnoreCase(pv.getType())) {
			throw new DOMException("Incompatible Types!");			
		}
		
		this.setPropertyValue(pv);
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (this.getType() != null) {
			sb.append(this.getType().getName()).append(" ");
		} else {
			sb.append("Component ");
		}

		sb.append(this.getName()).append(" (");

		if (null != this.getPropertyValues() && !this.getPropertyValues().isEmpty()) {
			Iterator<String> it = this.getPropertyValues().keySet().iterator();
			while (it.hasNext()) {
				String sPropertyName = it.next();
				PropertyValue objValue = this.getPropertyValues().get(sPropertyName);
				sb.append(".").append(sPropertyName).append("(");

				if (objValue != null) {
					sb.append(objValue.toString());
				}
				sb.append(")");

				if (it.hasNext()) {
					sb.append(",");
				}
			}
		}

		sb.append(");");
		return sb.toString();
	}

	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		throw new EugeneException("This is not supported!");
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new EugeneException("This is not supported!");
	}
}
