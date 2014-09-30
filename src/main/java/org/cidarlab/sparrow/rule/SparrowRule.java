package org.cidarlab.sparrow.rule;

import java.util.UUID;

/**
 * A Eugene rule is of the form:
 * IF <premise> THEN <conclusion>
 * 
 * The <premise> is a list of predicates (conjunction).
 * The <conclusion> specifies what needs to happen (or what 
 * we've learned) if the conjunction of the predicates is TRUE.
 * 
 * @author Ernst Oberortner
 *
 */
public class SparrowRule 
		extends NamedElement {

	private static final long serialVersionUID = -5221976854047668810L;
	private String lhs;
	private String rhs;
	
	public SparrowRule(String name, String lhs, String rhs) {
		super("name_"+UUID.randomUUID().toString().replace('-', '_'));
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public String getLHS() {
		return this.lhs;
	}
	
	public String getRHS() {
		return this.rhs;
	}
	
	@Override
	public String toString() {
		return "IF " + this.getLHS() +" THEN " + this.getRHS();
	}
	
	
}
