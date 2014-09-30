package org.cidarlab.sparrow.rule;

import org.cidarlab.eugene.dom.Property;
import org.cidarlab.sparrow.constants.SparrowConstants;

/**
 * A Predicate is a constraint that neest to be satisfied.
 * Example: sequence == "ATCG" 
 * 
 * In Eugene, a predicate consists of a left-hand-side (LHS), an expression operator, 
 * and a right-hand-side (RHS). 
 * Example:
 *             ==
 *           /    \
 *  sequence       "ATCG"
 *  
 * @author Ernst Oberortner
 */
public class Predicate {

	private Property lhs;
	private String operator;
	private String rhs;
	
	public Predicate(Property lhs, String operator, String rhs) {
		this.lhs = lhs;
		this.operator = operator;
		this.rhs = rhs;
	}
	
	public Property getLhs() {
		return this.lhs;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public String getRhs() {
		if(SparrowConstants.TXT.equals(this.lhs.getType()) ||
				SparrowConstants.TXTLIST.equals(this.lhs.getType())) {
			return "\""+this.rhs+"\"";
		} else if (SparrowConstants.NUM.equals(this.lhs.getType()) ||
				SparrowConstants.NUMLIST.equals(this.lhs.getType())) {
			return this.rhs;
		} else if (SparrowConstants.BOOLEAN.equals(this.lhs.getType())) {
			return this.rhs;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.lhs.getName()).append(" ").append(this.operator).append(" ").append(this.rhs);
		return sb.toString();
	}
}
