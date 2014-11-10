package org.cidarlab.eugene.interp;

import java.util.ArrayList;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Component;
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
	        if(RHS instanceof PropertyValue && LHS instanceof PropertyValue) {
	            return this.doMinPlusOp((PropertyValue)RHS, (PropertyValue)LHS, op);
	        } else if(RHS instanceof PropertyValue && LHS instanceof Variable) {
	        	return this.doMinPlusOp((PropertyValue)RHS, (Variable)LHS, op);
	        } else if(RHS instanceof Variable && LHS instanceof PropertyValue) {
	        	return this.doMinPlusOp((Variable)RHS, (PropertyValue)LHS, op);
	        } else if(RHS instanceof Variable && LHS instanceof Variable) {
	        	return this.doMinPlusOp((Variable)RHS, (Variable)LHS, op);

	        	// adding/removing elements to/from containers
	        } else if(LHS instanceof EugeneContainer) {
	        	return this.doMinPlusOp((EugeneContainer)LHS, RHS, op);
	        } else if(RHS instanceof EugeneContainer) {
	        	return this.doMinPlusOp((EugeneContainer)RHS, LHS, op);
	        }
	        
    	} catch(EugeneException ee) {
    		throw new EugeneException(ee.getLocalizedMessage());
    	}
    	
        throw new EugeneException("Unsupported " + op + " operation!");
    }
	
    // PropertyValue + PropertyValue -> PropertyValue
	public NamedElement doMinPlusOp(PropertyValue source, PropertyValue destination, String op) 
			throws EugeneException {
		
		return this.doMinPlusOp(
				this.comparator.convertPropertyValueToVariable(source), 
				this.comparator.convertPropertyValueToVariable(destination), 
				op);
		
	}
	
	// PropertyValue + Variable -> Variable
	public NamedElement doMinPlusOp(PropertyValue source, Variable destination, String op) 
			throws EugeneException {
		
		return this.doMinPlusOp(
				this.comparator.convertPropertyValueToVariable(source), 
				destination, 
				op);
	}
	
	// PropertyValue + Variable -> Variable
	public NamedElement doMinPlusOp(Variable source, PropertyValue destination, String op) 
			throws EugeneException {
		
		return this.doMinPlusOp(
				source, 
				this.comparator.convertPropertyValueToVariable(destination), 
				op);
	}

	//does addition or subtraction on a primitive, used by grammar rule expr
	public NamedElement doMinPlusOp(Variable source, Variable destination, String op) 
			throws EugeneException {

		if ("+".equals(op)) {
			if (EugeneConstants.NUM.equals(source.getType())) {
				
				// NUM + NUM -> NUM
				if(EugeneConstants.NUM.equals(destination.getType())) {
					destination.num += source.num;
					destination.type = EugeneConstants.NUM;
					
				// NUM + TXT -> TXT
				} else if(EugeneConstants.TXT.equals(destination.getType())) {

					if(source.num % 1 == 0) {
						destination.txt += (int)source.num;
					} else {
						destination.txt += source.num;
					}
					destination.type = EugeneConstants.TXT;
					
				// NUM + NUM[] -> NUM[]
				} else if(EugeneConstants.NUMLIST.equals(destination.getType())) {

					if(null == destination.getNumList()) {
						destination.numList =  new ArrayList<Double>();
					}
					destination.numList.add(source.getNum());
					destination.type = EugeneConstants.NUMLIST;
					
				// NUM + TXT[] -> TXT[]	
				} else if(EugeneConstants.TXTLIST.equals(destination.getType())) {
					
					if(source.num % 1 == 0) {
						destination.txtList.add(String.valueOf((int)source.num));
					} else {
						destination.txtList.add(String.valueOf(source.num));
					}
					
					destination.type = EugeneConstants.TXTLIST;
					
				} else {
					throw new EugeneException("Cannot perform the + operation! " + source.getType()+" + "+destination.getType());
				}
				
			} else if (EugeneConstants.NUMLIST.equals(source.getType())) {
				
				if(EugeneConstants.NUM.equals(destination.getType())) {
					destination.numList.add(destination.num);
					destination.numList.addAll(source.numList);
					destination.type = EugeneConstants.NUMLIST;
				} else if (EugeneConstants.NUMLIST.equals(destination.getType())) {
					destination.numList.addAll(source.numList);
					destination.type = EugeneConstants.NUMLIST;
				} else {
					throw new EugeneException("Cannot perform the + operation! " + source.getType()+" + "+destination.getType());
				}
			} else if (EugeneConstants.TXTLIST.equals(source.getType())) {
				
				// TXT[] + TXT[] -> TXT[]
				if(EugeneConstants.TXTLIST.equals(destination.getType())) {
					destination.txtList.addAll(source.txtList);
					destination.type = EugeneConstants.TXTLIST;
					
				// TXT[] + TXT -> TXT[]	
				} else if(EugeneConstants.TXT.equals(destination.getType())) {
					destination.txtList = new ArrayList<String>();
					destination.txtList.add(destination.txt);
					destination.txtList.addAll(source.txtList);
					destination.type = EugeneConstants.TXTLIST;
					
				// TXT[] + NUM -> TXT[]	
				} else if(EugeneConstants.NUM.equals(destination.getType())) {
					destination.txtList = new ArrayList<String>();
					if(destination.num % 1 == 0) {
						destination.txtList.add(String.valueOf((int)destination.num));
					} else {
						destination.txtList.add(String.valueOf(destination.num));
					}
					destination.txtList.addAll(source.txtList);
					destination.type = EugeneConstants.TXTLIST;
				} else {
					throw new EugeneException("Cannot perform the + operation! " + source.getType()+" + "+destination.getType());
				}
			} else if (EugeneConstants.TXT.equals(source.getType())) {
				
				// TXT + NUM -> TXT
				if(EugeneConstants.NUM.equals(destination.getType())) {
					if(destination.num % 1 == 0) {
						destination.txt = String.valueOf((int)destination.num);
					} else {
						destination.txt = String.valueOf(destination.num);
					}
					destination.txt += source.txt;
					destination.type = EugeneConstants.TXT;
					
				// TXT + TXT -> TXT
				} else if(EugeneConstants.TXT.equals(destination.getType())) {
					destination.txt += source.txt;
					destination.type = EugeneConstants.TXT;
					
				// TXT + TXT[] -> TXT[]
				} else if(EugeneConstants.TXTLIST.equals(destination.getType())) {
					if(null == destination.txtList) {
						destination.txtList = new ArrayList<String>();
					} 
					destination.txtList.add(source.txt);
					destination.type = EugeneConstants.TXTLIST;
				} else {
					throw new EugeneException("Cannot perform the + operation! " + source+" + "+destination.getType());
				}
			} else if(EugeneConstants.BOOLEAN.equals(source.getType())) {
				if(!EugeneConstants.BOOLEAN.equals(destination.getType())) {
					throw new EugeneException("Cannot perform the + operation!");
				}
				
				destination.bool = source.bool && destination.bool;
			}
			
		} else if ("-".equals(op)) {
			if (EugeneConstants.NUM.equals(source.getType())) {
				destination.num -= source.num;
				destination.type = EugeneConstants.NUM;
			} else {
				throw new EugeneException("Cannot perform the - operation on non-numerical types!");
			}
		}
		
		return destination;
	}
	
	
	/*----------------------------------------------
	 * EugeneContainer (+|-) EugeneContainer
	 *----------------------------------------------*/
	public NamedElement doMinPlusOp(EugeneContainer RHS, EugeneContainer LHS, String op) 
			throws EugeneException {
		
		EugeneContainer ec = null;
		if(RHS instanceof EugeneCollection) {
			// EugeneCollection <- EugeneCollection + EugeneCollection
			if(LHS instanceof EugeneCollection) {
				ec = new EugeneCollection(EugeneConstants.ANONYMOUS_VARIABLE);
				
				if("+".equals(op)) {
					
					ec.getElements().addAll(((EugeneCollection)LHS).getElements());
					ec.getElements().addAll(((EugeneCollection)RHS).getElements());
					/**
					for(NamedElement el : ((EugeneCollection)RHS).getElements()) {
						System.out.println("el -> " + el);
						if(ec.getElements().contains(el)) {
							System.out.println("CONTAINS -> " + el);
						} else {
							ec.getElements().add(el);
						}
					}
					**/
				} else if("-".equals(op)) {
					ec.getElements().addAll(((EugeneCollection)LHS).getElements());
					ec.getElements().retainAll(((EugeneCollection)LHS).getElements());
				}
				
				return ec;
				
			// EugeneArray <- EugeneArray + EugeneCollection
			} else if(LHS instanceof EugeneArray) {
				ec = new EugeneArray(EugeneConstants.ANONYMOUS_VARIABLE);

				ec.getElements().addAll(((EugeneArray)LHS).getElements());
				ec.getElements().addAll(((EugeneCollection)RHS).getElements());
				
				return ec;
			}
		} else if(RHS instanceof EugeneArray) {
			// EugeneCollection <- EugeneCollection + EugeneArray
			if(LHS instanceof EugeneCollection) {
				ec = new EugeneCollection(EugeneConstants.ANONYMOUS_VARIABLE);
				
				ec.getElements().addAll(((EugeneCollection)LHS).getElements());
				ec.getElements().addAll(((EugeneArray)RHS).getElements());
				
				return ec;
			// EugeneArray <- EugeneArray + EugeneArray
			} else if(LHS instanceof EugeneArray) {
				ec = new EugeneArray(EugeneConstants.ANONYMOUS_VARIABLE);

				ec.getElements().addAll(((EugeneArray)LHS).getElements());
				ec.getElements().addAll(((EugeneArray)RHS).getElements());
				
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
	
	
	public Variable doMultDivOp(Variable lhs, Variable rhs, String op) 
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
