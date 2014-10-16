package org.cidarlab.eugene.dom;

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
	
	/*
	 * ABSTRACT METHODS
	 * 
	 * for retrieving sub-elements of
	 * NamedElements, such as from Eugene containers 
	 * or devices.   
	 * 
	 * all primitive NamedElement sub-classes (Part, Type, Property etc) 
	 * will throw an EugeneException since they do not have sub-elements
	 */
	
	/**
	 * The getElement/1 method returns the element named with name. 
	 * If the element does not exist, then getElement/1 returns NULL. 
	 * If the object does not support the getElement/1 method, then 
	 * an EugeneException is thrown.
	 * 
	 * @param name ... the name of the desired element
	 * 
	 * @return  the requested NamedElement
	 * 
	 * @throws EugeneException
	 */
	public abstract NamedElement getElement(String name)
		throws EugeneException;
	
	/**
	 * The getElement/1 method returns the element at the given 
	 * index idx. In Eugene, indices start at position 0. 
	 * If the index is out of bounds, then an EugeneException is 
	 * thrown. 
	 * 
	 * @param idx  ... the idx of the desired element
	 * @return the element at index idx
	 * @throws EugeneException
	 */
	public abstract NamedElement getElement(int idx)
		throws EugeneException;
	
}
