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

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.ComponentType;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The ExpressionExecutor class provides methods to perform 
 * and interpret mathematical expressions in the Eugene language
 * 
 * Example:
 * ((a + b) * c) / d
 * 
 * @author Ernst Oberortner
 *
 */
public class ExpressionExecutor {
	
	private Comparator comparator;
	
	public ExpressionExecutor() {
		this.comparator = new Comparator();
	}
	
	
    public NamedElement doMinPlusOp(NamedElement RHS, NamedElement LHS, String op) 
    		throws EugeneException {
    	
    	if(!("-".equals(op) || "+".equals(op))) {
    		throw new EugeneException(op + " is an invalid operator!");
    	}
    	
    	try {
    			// PROPERTY VALUE (+|-) PROPERTY VALUE
    		if(RHS instanceof PropertyValue && LHS instanceof PropertyValue) {
	            return this.doMinPlusOp((PropertyValue)RHS, (PropertyValue)LHS, op);
	            
	        	// VARIABLE (+|-) PROPERTY VALUE
	        } else if(RHS instanceof PropertyValue && LHS instanceof Variable) {
	        	return this.doMinPlusOp((PropertyValue)RHS, (Variable)LHS, op);
	        	// PROPERTY VALUE (+|-) VARIABLE
	        } else if(RHS instanceof Variable && LHS instanceof PropertyValue) {
	        	return this.doMinPlusOp((Variable)RHS, (PropertyValue)LHS, op);
	        	
	        	// VARIABLE (+|-) VARIABLE
	        } else if(RHS instanceof Variable && LHS instanceof Variable) {
	        	return this.doMinPlusOp((Variable)RHS, (Variable)LHS, op);

	        	// CONTAINER (+|-) CONTAINER
	        } else if(LHS instanceof EugeneContainer && RHS instanceof EugeneContainer) {
	        	return this.doMinPlusOp((EugeneContainer)RHS, (EugeneContainer)LHS, op);
	        	
	        	// adding/removing elements to/from containers
	        } else if(LHS instanceof EugeneContainer) {
	        	return this.doMinPlusOp((EugeneContainer)LHS, RHS, op);
	        } else if(RHS instanceof EugeneContainer) {
	        	return this.doMinPlusOp((EugeneContainer)RHS, LHS, op);
	        	
	        	// DEVICE (+|-) PARTTYPE
	        } else if(LHS instanceof Device) {
	        	return this.doMinPlusOp((Device)LHS, RHS, op);
	        	// PARTTYPE (+|-) DEVICE
	        } else if(RHS instanceof Device) {
	        	return this.doMinPlusOp(LHS, (Device)RHS, op);
	        }
	        
    	} catch(EugeneException ee) {
    		throw new EugeneException(ee.getLocalizedMessage());
    	}
    	
        throw new EugeneException("Unsupported " + op + " operation!");
    }
	
    // PropertyValue + PropertyValue -> PropertyValue
	private NamedElement doMinPlusOp(PropertyValue source, PropertyValue destination, String op) 
			throws EugeneException {
		
		return this.doMinPlusOp(
				this.comparator.convertPropertyValueToVariable(source), 
				this.comparator.convertPropertyValueToVariable(destination), 
				op);
		
	}
	
	// PropertyValue + Variable -> Variable
	private NamedElement doMinPlusOp(PropertyValue source, Variable destination, String op) 
			throws EugeneException {
		
		return this.doMinPlusOp(
				this.comparator.convertPropertyValueToVariable(source), 
				destination, 
				op);
	}
	
	// PropertyValue + Variable -> Variable
	private NamedElement doMinPlusOp(Variable source, PropertyValue destination, String op) 
			throws EugeneException {
		
		return this.doMinPlusOp(
				source, 
				this.comparator.convertPropertyValueToVariable(destination), 
				op);
	}

	//does addition or subtraction on a primitive, used by grammar rule expr
	private NamedElement doMinPlusOp(Variable source, Variable destination, String op) 
			throws EugeneException {

		Variable result = null;
		
		if ("+".equals(op)) {
			if (EugeneConstants.NUM.equals(source.getType())) {
				
				// NUM + NUM -> NUM
				if(EugeneConstants.NUM.equals(destination.getType())) {
			
					
					result = new Variable(null, EugeneConstants.NUM);
					result.num = destination.num + source.num;
					
				// NUM + TXT -> TXT
				} else if(EugeneConstants.TXT.equals(destination.getType())) {

					result = new Variable(null, EugeneConstants.TXT);
					result.txt = new String(destination.txt);
					if(source.num % 1 == 0) {
						result.txt += (int)source.num;
					} else {
						result.txt += source.num;
					}
					
				// NUM + NUM[] -> NUM[]
				} else if(EugeneConstants.NUMLIST.equals(destination.getType())) {

					result = new Variable(null, EugeneConstants.NUMLIST);
					
					if(null == destination.getNumList() || destination.getNumList().isEmpty()) {
						destination.numList =  new ArrayList<Double>();
					} else {
						result.numList.addAll(destination.numList);
					}
					
					result.numList.add(source.getNum());
					
				// NUM + TXT[] -> TXT[]	
				} else if(EugeneConstants.TXTLIST.equals(destination.getType())) {

					result = new Variable(null, EugeneConstants.TXTLIST);
					result.txtList = destination.txtList;
					
					if(source.num % 1 == 0) {
						result.txtList.add(String.valueOf((int)source.num));
					} else {
						result.txtList.add(String.valueOf(source.num));
					}
					
				} else {
					throw new EugeneException("Cannot perform the + operation! " + source.getType()+" + "+destination.getType());
				}
				
			} else if (EugeneConstants.NUMLIST.equals(source.getType())) {
				
				result = new Variable(null, EugeneConstants.NUMLIST);
				
				// NUM[] + NUM -> NUM[]
				if(EugeneConstants.NUM.equals(destination.getType())) {
					
					result.numList.add(destination.num);
					result.numList.addAll(source.numList);
					
				// NUM[] + NUM[] -> NUM[]
				} else if (EugeneConstants.NUMLIST.equals(destination.getType())) {
					
					result.numList = new ArrayList<Double>(destination.numList);
					result.numList.addAll(source.numList);
					
				} else {
					throw new EugeneException("Cannot perform the + operation! " + source.getType()+" + "+destination.getType());
				}
				
			} else if (EugeneConstants.TXTLIST.equals(source.getType())) {
				
				result = new Variable(null, EugeneConstants.TXTLIST);
				
				// TXT[] + TXT[] -> TXT[]
				if(EugeneConstants.TXTLIST.equals(destination.getType())) {
					result.txtList = new ArrayList<String>(destination.txtList);
					result.txtList.addAll(source.txtList);
					
				// TXT[] + TXT -> TXT[]	
				} else if(EugeneConstants.TXT.equals(destination.getType())) {
					
					result.txtList = new ArrayList<String>();
					result.txtList.add(destination.txt);
					result.txtList.addAll(source.txtList);
					
				// TXT[] + NUM -> TXT[]	
				} else if(EugeneConstants.NUM.equals(destination.getType())) {
					result.txtList = new ArrayList<String>();
					if(destination.num % 1 == 0) {
						result.txtList.add(String.valueOf((int)destination.num));
					} else {
						result.txtList.add(String.valueOf(destination.num));
					}
					result.txtList.addAll(source.txtList);

				} else {
					throw new EugeneException("Cannot perform the + operation! " + source.getType()+" + "+destination.getType());
				}
			} else if (EugeneConstants.TXT.equals(source.getType())) {
				
				// TXT + NUM -> TXT
				if(EugeneConstants.NUM.equals(destination.getType())) {
					
					result = new Variable(null, EugeneConstants.TXT);
					
					if(destination.num % 1 == 0) {
						result.txt = String.valueOf((int)destination.num);
					} else {
						result.txt = String.valueOf(destination.num);
					}
					result.txt += new String(source.txt);
					
				// TXT + TXT -> TXT
				} else if(EugeneConstants.TXT.equals(destination.getType())) {
					
					result = new Variable(null, EugeneConstants.TXT);
					result.txt = destination.txt + source.txt;
					
				// TXT + TXT[] -> TXT[]
				} else if(EugeneConstants.TXTLIST.equals(destination.getType())) {
					
					result = new Variable(null, EugeneConstants.TXTLIST);
					
					if(null == destination.txtList) {
						result.txtList = new ArrayList<String>();
					} else {
						result.txtList = new ArrayList<String>(destination.getTxtList());
					}
					
					result.txtList.add(source.txt);
					
				} else {
					throw new EugeneException("Cannot perform the + operation! " + source+" + "+destination.getType());
				}
			} else if(EugeneConstants.BOOLEAN.equals(source.getType())) {
				if(!EugeneConstants.BOOLEAN.equals(destination.getType())) {
					throw new EugeneException("Cannot perform the + operation!");
				}
				
				result = new Variable(null, EugeneConstants.BOOLEAN);
				result.bool = source.bool && destination.bool;
			}
			
		} else if ("-".equals(op)) {
			if (EugeneConstants.NUM.equals(source.getType())) {
				
				result = new Variable(null, EugeneConstants.NUM);
				result.num = destination.num - source.num;

			} else {
				throw new EugeneException("Cannot perform the - operation on non-numerical types!");
			}
		}
		
		return result;
	}
	
	
	/*----------------------------------------------
	 * Device (+|-) Component/ComponentType
	 *----------------------------------------------*/
	private NamedElement doMinPlusOp(Device LHS, NamedElement RHS, String op) 
			throws EugeneException {
		
		if(!(RHS instanceof Component) && !(RHS instanceof ComponentType)) {
			throw new EugeneException("Invalid use of the " + op + " operator!");
		}
		
		if(RHS.getName().equals(LHS.getName())) {
			throw new EugeneException("Cannot add " + RHS.getName() + " to " + LHS.getName()+"!");
		}

		if("+".equals(op)) {
			// ADD
			
			// add the element
			List<NamedElement> elements = new ArrayList<NamedElement>();
			elements.add(RHS);
			((Device)LHS).getComponents().add(elements);
			
			// add the orientations
			List<Orientation> orientations = new ArrayList<Orientation>();
			orientations.add(Orientation.UNDEFINED);
			((Device)LHS).getOrientations().add(orientations);
			
			return (Device)LHS;
		} 
		
		return null;
	}
	
	/*----------------------------------------------
	 * Component/ComponentType (+|-) Device 
	 *----------------------------------------------*/
	private NamedElement doMinPlusOp(NamedElement LHS, Device RHS, String op) 
			throws EugeneException {
		
		if(!(LHS instanceof Component) && !(LHS instanceof ComponentType)) {
			throw new EugeneException("Invalid use of the " + op + " operator!");
		}
		
		if("+".equals(op)) {
			// ADD
			
			// Elements
			List<List<NamedElement>> elements = new ArrayList<List<NamedElement>>();
			
			List<NamedElement> parttype = new ArrayList<NamedElement>();
			parttype.add(LHS);
			elements.add(parttype);
			
			elements.addAll(RHS.getComponents());
			RHS.setComponents(elements);
			
			
			// Orientations
			List<List<Orientation>> orientations = new ArrayList<List<Orientation>>();
			
			List<Orientation> elementOrientation = new ArrayList<Orientation>();
			elementOrientation.add(Orientation.UNDEFINED);
			orientations.add(elementOrientation);
			
			orientations.addAll(RHS.getOrientations());
			RHS.setOrientations(orientations);

			return (Device)RHS;
		} 
		
		return null;
	}

	/*----------------------------------------------
	 * EugeneContainer (+|-) EugeneContainer
	 *----------------------------------------------*/
	private NamedElement doMinPlusOp(EugeneContainer RHS, EugeneContainer LHS, String op) 
			throws EugeneException {
		
		EugeneContainer ec = null;
		
		if(RHS instanceof EugeneCollection) {
			if(LHS instanceof EugeneCollection) {
				ec = new EugeneCollection(null);
				
				// EugeneCollection <- EugeneCollection + EugeneCollection
				if("+".equals(op)) {
					ec.getElements().addAll(((EugeneCollection)LHS).getElements());
					ec.getElements().addAll(((EugeneCollection)RHS).getElements());

				// EugeneCollection <- EugeneCollection \ EugeneCollection
				} else if("-".equals(op)) {
					ec.getElements().addAll(((EugeneCollection)LHS).getElements());
					ec.getElements().retainAll(((EugeneCollection)RHS).getElements());
				}
				
				return ec;
				
			} else if(LHS instanceof EugeneArray) {
				ec = new EugeneArray(null);

				// EugeneArray <- EugeneArray + EugeneCollection				
				if("+".equals(op)) {
					ec.getElements().addAll(((EugeneArray)LHS).getElements());
					ec.getElements().addAll(((EugeneCollection)RHS).getElements());
					
				// EugeneArray <- EugeneArray - EugeneCollection									
				} else if("-".equals(op)) {
					ec.getElements().addAll(((EugeneArray)LHS).getElements());
					ec.getElements().retainAll(((EugeneCollection)RHS).getElements());
				}				
				return ec;
			}
		} else if(RHS instanceof EugeneArray) {
			if(LHS instanceof EugeneCollection) {
				ec = new EugeneCollection(null);

				// EugeneCollection <- EugeneCollection + EugeneArray
				if("+".equals(op)) {
					ec.getElements().addAll(((EugeneCollection)LHS).getElements());
					ec.getElements().addAll(((EugeneArray)RHS).getElements());
				// EugeneCollection <- EugeneCollection - EugeneArray
				} else if("-".equals(op)) {
					ec.getElements().addAll(((EugeneCollection)LHS).getElements());
					ec.getElements().retainAll(((EugeneArray)RHS).getElements());
				}
				return ec;

			} else if(LHS instanceof EugeneArray) {
				ec = new EugeneArray(null);
				
				// EugeneArray <- EugeneArray + EugeneArray
				if("+".equals(op)) {
					ec.getElements().addAll(((EugeneArray)LHS).getElements());
					ec.getElements().addAll(((EugeneArray)RHS).getElements());

				// EugeneArray <- EugeneArray - EugeneArray
				} else if("-".equals(op)) {
					ec.getElements().addAll(((EugeneArray)LHS).getElements());
					ec.getElements().retainAll(((EugeneArray)RHS).getElements());
				}				
				return ec;
			}
		}
		
		throw new EugeneException("Unsupported " + op + " operation for Eugene containers!");
		
	}
	
	/*
	 * COMPONENT + CONTAINER
	 */
	private EugeneContainer doMinPlusOp( EugeneContainer c, NamedElement e, String op) 
				throws EugeneException { 
		if(null != e && null != c) {
			if("+".equals(op)) {
				// add the LHS to the container
				c.getElements().add(e);
			} else if("-".equals(op)) {
				// remove the LHS from the container
				if(c.getElements().contains(e)) {
					c.getElements().remove(e);
				} else {
					throw new EugeneException("The " + c.getName() + " container does not contain " + e.getName());
				}
			}
		}
		return c;
	}

	
	/**
	 * The doMultDivOp/3 takes as input the left-hand-side (LHS) and the right-hand-side (RHS)  
	 * of the operator op which can either be "*" (multiplication) or "/" (division).
	 * 
	 * @param lhs  ... the LHS of the operation
	 * @param rhs  ... the RHS of the operation 
	 * @param op   ... the operator, either "*" or "/"
	 * @return  ... a NamedElement that represents the multiplication/division result
	 * 
	 * @throws EugeneException  ... if the operator is not support on the types of operands
	 */
	public NamedElement doMultDivOp(NamedElement lhs, NamedElement rhs, String op) 
			throws EugeneException {
		
		if(!"*".equals(op) && !"/".equals(op)) {
			throw new EugeneException(op + " is an invalid operator!");
		}
		
		Variable v_lhs = null;
		Variable v_rhs = null;
		
		// Left-Hand-Side
		if(lhs instanceof PropertyValue) {
			v_lhs = this.comparator.convertPropertyValueToVariable(
					(PropertyValue)lhs);
		} else if(lhs instanceof Variable) {
			v_lhs = (Variable)lhs;
		}
		
		// Right-Hand-Side
		if(rhs instanceof PropertyValue) {
			v_rhs = this.comparator.convertPropertyValueToVariable(
					(PropertyValue)rhs);
		} else if(rhs instanceof Variable) {
			v_rhs = (Variable)rhs;
		}

		if(null != v_lhs && null != v_rhs) {
			return this.doMultDivOp(v_lhs, v_rhs, op);
		}
		
		throw new EugeneException("Unsupported " + op +" operation!");
	}
	
	
	private Variable doMultDivOp(Variable lhs, Variable rhs, String op) 
			throws EugeneException {
        
		// NUM * NUM --> NUM
		if (EugeneConstants.NUM.equals(lhs.getType()) && 
        		EugeneConstants.NUM.equals(rhs.getType())) {
            if ("*".equals(op)) {
                lhs.num *= rhs.num;
            } else {
                if (rhs.num != 0) {
                    lhs.num /= rhs.num;
                } else {
                    throw new EugeneException("Division by zero.");
                }
            }

            return lhs;
        }
		
    	throw new EugeneException("Cannot perform * operation on non-numerical values!");
	}

}
