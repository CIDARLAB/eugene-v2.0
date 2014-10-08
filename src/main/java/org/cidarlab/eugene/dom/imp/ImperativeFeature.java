package org.cidarlab.eugene.dom.imp;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * All Eugene imperative language features do not support 
 * the getElement(String) and getElement(int)
 * methods. Hence, we've introduced the ImperativeFeature abstract class 
 * to avoid implementing those functions in loops, branches, 
 * and function prototypes. 
 * 
 * @author Ernst Oberortner
 *
 */
public abstract class ImperativeFeature 
		extends StackElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386541171187688528L;

	public ImperativeFeature(String name) {
		super(name);
	}
	
	/*-----------------------------------
	 * ACCESSORS FOR SYMBOLS 
	 * (SCOPING)
	 *-----------------------------------*/
	@Override
	public boolean contains(String name) {
		return this.getSymbols().contains(name);
	}

	@Override
	public NamedElement get(String name) {
		return this.getSymbols().get(name);		
	}

	@Override
	public void put(NamedElement ne) 
			throws EugeneException {
		this.getSymbols().put(ne);
	}


	@Override
	public void clear() {
		this.getSymbols().clear();
	}
	
	/**
	 * The remove/1 method removes the NamedElement object with 
	 * the given name from the symbol tables.
	 * 
	 * @param name  ... the name of the NamedElement object that should be removed
	 * @throws EugeneException
	 */
	public void remove(String name) {
		this.getSymbols().removeVariable(name);
	}
	
	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		return this.get(name);
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new EugeneException("This is not supported!");
	}


}
