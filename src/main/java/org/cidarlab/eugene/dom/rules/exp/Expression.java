package org.cidarlab.eugene.dom.rules.exp;


/**
 * An expression is an operand of an expression rule and has
 * the following format:
 * exp-operand ( exp-op exp-operand )*
 * exp-op := {'+', '-', '*', '/'}
 * 
 * Example:
 * Promoter.strength + RBS.strength
 * 
 * @author Ernst Oberortner
 */
public class Expression {
	
	public enum ExpOp {
		PLUS, MINUS, MULT, DIV
	}
	
	/*
	 * the left-hand-side is an expression operand
	 * since we resolve an expression from left to right
	 */
	private ExpressionOperand lhs;

	/*
	 * if the expression has an operator and a rhs
	 * 
	 * <lhs> <op> <rhs>
	 * 
	 * e.g. Promoter.strength + RBS.strength
	 * => 
	 * lhs := Promoter.strength
	 * op := +
	 * rhs := RBS.strength
	 */
	private ExpOp op;
	private Expression rhs;
	
	public Expression(ExpressionOperand lhs, ExpOp op, Expression rhs) {		
		this.lhs = lhs;
		this.op = op;
		this.rhs = rhs;
	}
	
	public ExpressionOperand getLhs() {
		return this.lhs;
	}
	
	public ExpOp getOp() {
		return this.op;
	}
	
	public Expression getRhs() {
		return this.rhs;
	}
	
	public String getType() {
		if(null != rhs) {
			return this.rhs.getType();
		}
		if(null != this.lhs.getProperty()) {
			return this.lhs.getProperty().getType();
		} 
		return this.lhs.getConstant().getType();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getLhs());
		if(null != this.getOp() && null != this.getRhs()) {
			sb.append(" ").append(this.getOp())
				.append(" ").append(this.getLhs());
		}
		return sb.toString();
	}
	
	/**
	 * The isConstant/0 method returns true if the operand 
	 * is a constant. 
	 * An expression operand is a constant if the Variable constant 
	 * is set. 
	 * 
	 * @return true  ... iff the expression operand is a constant
	 *         false ... otherwise
	 */
	public boolean isConstant() {
		return this.getOp()==null && this.getRhs()==null && this.getLhs().getConstant()!=null;
	}
	
	public boolean isIndexed() {
		
		return true;
	}

}
