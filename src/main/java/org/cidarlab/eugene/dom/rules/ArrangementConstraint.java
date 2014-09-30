package org.cidarlab.eugene.dom.rules;

/**
 * The PrimtiveConstraint class represents one Eugene constraint.
 * 
 * Examples:
 * CONTAINS x
 * 
 * A constraint is represented as a string array of tokens.
 * Example:
 * ["CONTAINS", "x"]
 * 
 * @author Ernst Oberortner
 *
 */
public class ArrangementConstraint 
		implements ArrangementPredicate {

	private ArrangementOperand lhs;
	private String op;
	private ArrangementOperand rhs;
	
	/**
	 * 
	 * @param tokens an array of tokens
	 */
	public ArrangementConstraint(ArrangementOperand lhs, String op, ArrangementOperand rhs) {
		this.lhs = lhs;
		this.op = op;
		this.rhs = rhs;
	}
	
	public ArrangementOperand getLhs() {
		return this.lhs;
	}
	
	public String getOp() {
		return this.op;
	}
	
	public ArrangementOperand getRhs() {
		return this.rhs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.getLhs()) {
			sb.append(this.getLhs()).append(" ");
		} 
		sb.append(this.getOp());
		if(null != this.getRhs()) {
			sb.append(" ").append(this.getRhs());
		}
		return sb.toString();
	}

	
	/*
	 * HELPER METHODS
	 */
	public boolean isBinary() {
		return this.getLhs() != null && this.getRhs() != null;
	}
	
	public boolean isUnary() {
		return this.getLhs() == null && this.getRhs() != null;
	}
	
	public boolean isNullary() {
		return this.getLhs() == null && this.getRhs() == null;
	}
}
