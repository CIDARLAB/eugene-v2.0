package org.cidarlab.sparrow.rule;

import java.io.Serializable;

import org.cidarlab.eugene.exception.EugeneException;

public abstract class NamedElement 
		implements Serializable {

	private static final long serialVersionUID = 7803721755204206476L;

	/*
	 * ID (automatically generated)
	 */
	private int id;
	
	/*
	 * NAME
	 */
	private String name;
	
	public NamedElement(String name) {

		if(null == name) {
			this.setName(String.valueOf(System.nanoTime()));
		} else {
			this.setName(name);
		}

		this.id = this.hashName();

	}
	
	/**
	 * the setName/1 method is used to set the name 
	 * of the NamedElement. 
	 * This method is used in the interpreter for 
	 * assignment statements, particularly if the 
	 * RHS of an assignment needs to be named as specified 
	 * on the LHS of an assignment.
	 * 
	 * @param name ... the name of the NamedElement object
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	private int hashName() {
		if(null != this.getName()) {
			int hash = this.getName().hashCode();
			if(hash < 0) {
				return hash * -1;
			}
			return hash;
		} 
		return 0;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
}
