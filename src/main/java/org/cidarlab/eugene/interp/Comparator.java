/* Copyright (c) 2015, Boston University
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list 
 * of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without specific prior 
 * written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.interp;

import java.util.List;
import java.util.ArrayList;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The Comparator compares variables and expressions 
 * regarding their types and values.
 * 
 * @author Ernst Oberortner
 */
public class Comparator {

	/**
	 * The convertPropertyValueToVariable/1 converts a PropertyValue object 
	 * into a Variable object.
	 * 
	 * @param pv  ... the PropertyValue that should be converted
	 * @return    ... the resulting Variable object
	 */
	public Variable convertPropertyValueToVariable(PropertyValue pv) {
		
		// instantiate a Variable object using the name and type 
		// of the PropertyValue object
		Variable v = new Variable(pv.getName(), pv.getType());
		
		// assign the value of the PropertyValue object to the
		// Variable object depending on the type
		if(EugeneConstants.NUM.equals(pv.getType())) {
			v.num = pv.getNum();
		} else if(EugeneConstants.NUMLIST.equals(pv.getType())) {
			v.numList = (ArrayList<Double>)pv.getNumList();
		}else if(EugeneConstants.TXT.equals(pv.getType())) {
			v.txt = pv.getTxt();
		} else if(EugeneConstants.TXTLIST.equals(pv.getType())) {
			v.txtList = (ArrayList<String>)pv.getTxtList();
		} else if(EugeneConstants.BOOLEAN.equals(pv.getType())) {
			v.bool = pv.getBool();
		}
		
		// return the Variable object
		return v;
	}
	
	/**
	 * The toVariable/1 method converts a NamedElement object into 
	 * a Variable object (if possible). otherwise an exception will be thrown.
	 * 
	 * @param el  ... the NamedElement to be converted
	 * @return    ... the resulting Variable object
	 * 
	 * @throws EugeneException  ... if a conversion is not possible
	 */
	private Variable toVariable(NamedElement el) 
			throws EugeneException {
		
		if(!(el instanceof Variable)) {
			if(el instanceof PropertyValue) {
				// convert property value to variable
				return this.convertPropertyValueToVariable((PropertyValue)el);
			}
			throw new EugeneException("Not supported yet!");
		}
		
		return (Variable)el;
	}
	
	/**
	 * The evaluateCondition/2 method evaluates a condition. Therefore, we 
	 * compare the LHS element against the RHS element regarding a given 
	 * comparison operator.
	 * 
	 * @param lhs  ... the LHS of the condition
	 * @param op   ... the comparison operator (i.e. <, <=, ==, !=, >=, >) 
	 * @param rhs  ... the RHS of the condition
	 * 
	 * @return true  ... if the condition evaluates to true
	 *         false ... otherwise
	 *         
	 * @throws EugeneException
	 */
	public boolean evaluateCondition(NamedElement lhs, String op, NamedElement rhs)
			throws EugeneException {
		
		Variable v_lhs = this.toVariable(lhs);
		Variable v_rhs = this.toVariable(rhs);
		
		return this.evaluateVariableCondition(v_lhs, op, v_rhs);
	}
	
	private boolean evaluateVariableCondition(Variable lhs, String op, Variable rhs) 
			throws EugeneException {
		
		if(EugeneConstants.LT.equals(op)) {
			if(!EugeneConstants.NUM.equals(lhs.getType())) {
				throw new EugeneException("The types of the LHS and RHS are not numeric!");
			}
			return this.lt(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.LEQ.equals(op)) {
			if(!EugeneConstants.NUM.equals(lhs.getType())) {
				throw new EugeneException("The types of the LHS and RHS are not numeric!");
			}
			return this.leq(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.EQUALS.equals(op) ||
				"==".equals(op)) {
			if(EugeneConstants.NUM.equals(lhs.getType())) {
				return this.num_eq(lhs.getNum(), rhs.getNum());
			} else if(EugeneConstants.NUMLIST.equals(lhs.getType())) {
				return this.numList_eq(lhs.getNumList(), rhs.getNumList());
			} else if(EugeneConstants.TXT.equals(lhs.getType())) {
				return this.txt_eq(lhs.getTxt(), rhs.getTxt());
			} else if(EugeneConstants.TXTLIST.equals(lhs.getType())) {
				return this.txtList_eq(lhs.getTxtList(), rhs.getTxtList());
			} else if(EugeneConstants.BOOLEAN.equals(lhs.getType())) {
				return this.bool_eq(lhs.getBool(), rhs.getBool());
			}
		} else if(EugeneConstants.NOTEQUALS.equals(op) ||
				"!=".equals(op)) {
			if(EugeneConstants.NUM.equals(lhs.getType())) {
				return !this.num_eq(lhs.getNum(), rhs.getNum());
			} else if(EugeneConstants.NUMLIST.equals(lhs.getType())) {
				return !this.numList_eq(lhs.getNumList(), rhs.getNumList());
			} else if(EugeneConstants.TXT.equals(lhs.getType())) {
				return !this.txt_eq(lhs.getTxt(), rhs.getTxt());
			} else if(EugeneConstants.TXTLIST.equals(lhs.getType())) {
				return !this.txtList_eq(lhs.getTxtList(), rhs.getTxtList());
			} else if(EugeneConstants.BOOLEAN.equals(lhs.getType())) {
				return !this.bool_eq(lhs.getBool(), rhs.getBool());
			}
		} else if(EugeneConstants.GEQ.equals(op)) {
			if(!EugeneConstants.NUM.equals(lhs.getType())) {
				throw new EugeneException("The types of the LHS and RHS are not numeric!");
			}
			return this.geq(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.GT.equals(op)) {
			if(!EugeneConstants.NUM.equals(lhs.getType())) {
				throw new EugeneException("The types of the LHS and RHS are not numeric!");
			}
			return this.gt(lhs.getNum(), rhs.getNum());
		}
		
		return true;
	} 
	
	// num vs num
	private boolean num_eq(double a, double b) {
		return a == b;
	}

	// num[] vs num[]
	private boolean numList_eq(List<Double> a, List<Double> b) {
		
		if(a.size() != b.size()) {
			return false;
		}
		
		for(int i=0; i<a.size(); i++) {
			if(a.get(i).doubleValue() != b.get(i).doubleValue()) {
				return false;
			}
		}
		return true;
	}

	// txt vs txt
	private boolean txt_eq(String a, String b) {
		return a.equalsIgnoreCase(b);
	}

	// txt[] vs ntxt[]
	private boolean txtList_eq(List<String> a, List<String> b) {
		if(a.size() != b.size()) {
			return false;
		}
		for(int i=0; i<a.size(); i++) {
			if(!a.get(i).equalsIgnoreCase(b.get(i))) {
				return false;
			}
		}
		return true;
	}

	// bool vs. bool
	private boolean bool_eq(boolean a, boolean b) {
		return a == b;
	}

	/*---------------------------------------
	 * NUMERIC COMPARISONS <, <=, >, >=
	 *---------------------------------------*/

	private boolean lt(double a, double b) {
		return a < b;
	}

	private boolean leq(double a, double b) {
		return a <= b;
	}

	private boolean geq(double a, double b) {
		return a >= b;
	}

	private boolean gt(double a, double b) {
		return a > b;
	}
	

	/**
	 * The compareTypes/2 method compares the types of the 
	 * LHS NamedElement and the RHS NamedElement
	 * 
	 * @param lhs  ... the NamedElement object of the LHS
	 * @param rhs  ... the NamedElement object of the RHS
	 * 
	 * @return true  ... if the types match
	 *         false ... otherwise 
	 */
	public boolean compareTypes(NamedElement lhs, NamedElement rhs) {

		// TODO:
		// currently, there are a lot of individual type checks necessary
		// optimize this solution!
		// e.g. by introducing a TypedElement class between NamedElement 
		// and all typed elements in Eugene (e.g. variables, parts, properties, etc)
		
		// if both objects are either variables or property values, 
		// then we need to compare their primitive types
		if(lhs instanceof Variable) {
			if(rhs instanceof Variable) {

				return ((Variable)lhs).getType().equals(
						((Variable)rhs).getType());
				
			} else if(rhs instanceof PropertyValue) {
				
				return ((Variable)lhs).getType().equals(
						((PropertyValue)rhs).getType());
			}
			
		// LHS is a property value
		// e.g. p1.PIGEON
		} else if(lhs instanceof PropertyValue) {
			
			// RHS is property value
			// e.g. p1.PIGEON = p2.PIGEON
			if(rhs instanceof PropertyValue) {
				return ((PropertyValue)lhs).getType().equals(
						((PropertyValue)rhs).getType());

			// RHS is a variable (or constant)
			// e.g. p1.PIGEON = "p p1 14 nl"
			} else if(rhs instanceof Variable) {
				return ((PropertyValue)lhs).getType().equals(
						((Variable)rhs).getType());
			}
			
		
		// ``Part-Assignments''
		// LHS and RHS is a part
		// e.g. Promoter p1; Promoter p2; p1 = p2;
		} else if(lhs instanceof Part && rhs instanceof Part) {

			// the part types must match
			if(((Part)lhs).getType() != null && 
					((Part)rhs).getType() != null) {
				
				return ((Part)lhs).getType().getName().equals(
						((Part)rhs).getType().getName());
			}
			
			return false;
			
		// Assignment to a Property of a component type (e.g. part type)
		// the value should be propagated to all instances of the component type 
		} else if(lhs instanceof Property) {
			
			// RHS is a property value
			// e.g. Promoter.PIGEON = p1.PIGEON
			if(rhs instanceof PropertyValue) {
				return ((Property)lhs).getType().equals(
						((PropertyValue)rhs).getType());

			// RHS is a variable
			// e.g. Promoter.PIGEON = "Promoter"
			} else if(rhs instanceof Variable) {
				return ((Property)lhs).getType().equals(
						((Variable)rhs).getType());
			}
		}
		
		// otherwise, we just compare it both objects
		// are of the same class
		return lhs.getClass().getName().equals(
				rhs.getClass().getName());
	}
	
	/**
	 * The compareTypes/3 method gets as input two NamedElement objects and
	 * an index that denotes the requested element on the LHS. 
	 * e.g. p1.numList[0] = my_num
	 * then lhs is numList, idx is 0, and rhs is my_num 
	 * 
	 * @param lhs  ... the element on the LHS
	 * @param idx  ... the requested index of the LHS' sub-element
	 * @param rhs  ... the element on the RHS
	 * @return
	 */
	public boolean compareTypes(NamedElement lhs, int idx, NamedElement rhs) {
		
		if(lhs instanceof PropertyValue) {
			
			// first, we get the type of the LHS and 
			// derive the type of the indexed element
			String type = null;
			if(EugeneConstants.NUMLIST.equals(((PropertyValue)lhs).getType())) {
				type = EugeneConstants.NUM;
			} else if (EugeneConstants.TXTLIST.equals(((PropertyValue)lhs).getType()) ||
					EugeneConstants.TXT.equals(((PropertyValue)lhs).getType())) {
				type = EugeneConstants.TXT;
			} else {
				return false;
			}
			
			Variable v_lhs = new Variable("anonymous", type);
			return this.compareTypes(v_lhs, rhs);
			
		} else if(lhs instanceof Variable) {
			
			// first, we get the type of the LHS and 
			// derive the type of the indexed element
			String type = null;
			if(EugeneConstants.NUMLIST.equals(((Variable)lhs).getType())) {
				type = EugeneConstants.NUM;
			} else if (EugeneConstants.TXTLIST.equals(((Variable)lhs).getType()) ||
					EugeneConstants.TXT.equals(((Variable)lhs).getType())) {
				type = EugeneConstants.TXT;
			} else {
				return false;
			}
			
			Variable v_lhs = new Variable("anonymous", type);
			return this.compareTypes(v_lhs, rhs);
			
		// added 05/18/2015
		// see org.cidarlab.eugene.units.ContainerTest
		} else if(lhs instanceof EugeneArray) {
			try {
				return this.compareTypes(((EugeneArray)lhs).getElement(idx), rhs);
			} catch(EugeneException ee) {
				return false;
			}
		}
		
		
		
		return false;
	}
		
}
