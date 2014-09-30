package org.cidarlab.eugene.dom.imp;

import java.util.UUID;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;

/**
 * In Eugene, a StackElement represents an imperative language feature, such as 
 * Loops, Branches, and Functions. 
 * 
 * A StackElement can have a name (such as functions). If no name is provided (such as for loops and branches), 
 * then we generate a random name using Java's UUID features.
 * Every StackElement has its own local symbol tables, represented by the symbols 
 * variable.
 * However, the entire control of putting, retrieving, and searching for variables/symbols 
 * during the execution/interpretation of a Eugene script is done in the interpreter (Interp).
 * 
 * Also, we characterize Eugene containers as StackElements since a container's elements 
 * are only visible within the container, and, hence, scoping is needed.
 * 
 * @author Ernst Oberortner
 *
 */
public abstract class StackElement 
		extends NamedElement {

	private static final long serialVersionUID = -5593349465159567676L;
	private SymbolTable symbols;
	

	public StackElement() {
		super(UUID.randomUUID().toString());
		
		this.symbols = new SymbolTable();
	}
	
	public StackElement(String name) {
		super(name);
		
		this.symbols = new SymbolTable();
	}
	
	/*
	 * common methods
	 */
	
	protected SymbolTable getSymbols() {
		return this.symbols;
	}
	
	
	/**
	 * The get/1 method returns the requested NamedElement 
	 * 
	 * @param name ... the name of the required NamedElement
	 * 
	 * @return  ... the NamedElement object with the provided name
	 *              null if the NamedElement has not been declared
	 *              
	 * @throws EugeneException
	 */
	public abstract NamedElement get(String name);
	
	/**
	 * The contains/1 method checks if its symbol tables contain 
	 * a NamedElement with the provided name. If it does not 
	 * exist, then we return false. otherwise true.
	 * @param name ... the name of the wanted NamedElement object
	 * @return true ... if the NamedElement object exists
	 *         false .. otherwise
	 * @throws EugeneException
	 */
	public abstract boolean contains(String name);
	
	/**
	 * The put/1 method inserts the given NamedElement object into 
	 * the local symbol tables.
	 * 
	 * @param ne ... the NamedElement object that must be stored in the
	 *               local symbol tables
	 *               
	 * @throws EugeneException
	 */
	public abstract void put(NamedElement ne)
			throws EugeneException;
	
	/**
	 * The clear/0 method cleans the local symbol tables
	 */
	public abstract void clear();
	
}
