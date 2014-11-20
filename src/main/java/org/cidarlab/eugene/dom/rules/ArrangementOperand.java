package org.cidarlab.eugene.dom.rules;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The ArrangementOperand class serves to hold information 
 * about rule operands. 

 * Example: CONTAINS x 
 * the operand x of the CONTAINS rule is an instance of 
 * the ArrangementOperand class. 
 *  
 * A rule operand is either characterized by
 * - a NamedElement object (e.g. x),
 * - a constant (e.g. 1), or
 * - an index (e.g. [i])
 * 
 * @author Ernst Oberortner
 *
 */
public class ArrangementOperand {

	private NamedElement element;
	private int constant;
	private int index;
	
	
	/**
	 * The ArrangementOperand/1 constructor takes as input three parameters
	 * where only one of them must be set.
	 * 
	 * @param element   ... the rule's operand is a NamedElement object
	 * @param constant  ... the rule's operand is a constant
	 * @param index     ... the rule's operand is an index
	 * @throws EugeneException
	 */
	public ArrangementOperand(NamedElement element, int constant, int index) 
			throws EugeneException {
		if(null != element) {
			this.element = element;
			this.constant = -1;
			this.index = -1;
		} else if(-1 != constant) {
			this.element = null;
			this.constant = constant;
			this.index = -1;
		} else if(-1 != index) {
			this.element = null;
			this.constant = -1;
			this.index = index;
		} else {
			throw new EugeneException("Invalid operand for arrangement constraint!");
		}
	}
	
	/*---------
	 * GETTERS
	 *---------*/
	public NamedElement getElement() {
		return this.element;
	}
	
	public boolean isElement() {
		return null != this.getElement();
	}
	
	public int getConstant() {
		return this.constant;
	}
	
	public boolean isConstant() {
		return -1 != this.getConstant();
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public boolean isIndex() {
		return -1 != this.getIndex();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.getElement()) {
			sb.append(this.getElement().getName());
		} else if (-1 != this.getConstant()) {
			sb.append(this.getConstant());
		} else if(-1 != this.getIndex()) {
			sb.append("[").append(this.getIndex()).append("]");
		}
		return sb.toString();
	}
}
