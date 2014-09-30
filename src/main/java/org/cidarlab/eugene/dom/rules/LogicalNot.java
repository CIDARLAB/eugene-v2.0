package org.cidarlab.eugene.dom.rules;

/**
 * The LogicalNot class serves to negate constraints
 * 
 * @author Ernst Oberortner
 */
public class LogicalNot 
		implements Predicate {

	private Predicate p;
	
	/**
	 * 
	 * @param p ... the predicate to negate
	 */
	public LogicalNot(Predicate p) {
		this.p = p;
	}

	/**
	 * 
	 * @return the constraint
	 */
	public Predicate getPredicate() {
		return this.p;				
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NOT ").append(this.getPredicate());
		return sb.toString();
	}

}
