package org.cidarlab.sparrow.rule;

import java.util.UUID;

/**
 * x has a relation to y
 * 
 * @author E. Oberortner
 *
 */
public class Relation 
		extends NamedElement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3015139709378484720L;
	private NamedElement lhs;
	private RelationType type;
	private NamedElement rhs;
	
	
	public enum RelationType {
		ORTHOGONAL
	} 
	
	public Relation(NamedElement lhs, RelationType type, NamedElement rhs) {
		super(UUID.randomUUID().toString());
		this.lhs = lhs;
		this.type = type;
		this.rhs = rhs;
	}
	
	public NamedElement getLHS() {
		return this.lhs;
	}
	
	public String getType() {
		return this.type.toString();
	}
	
	public NamedElement getRHS() {
		return this.rhs;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getLHS().getName()).append(" ")
			.append(this.getType()).append(" ")
			.append(this.getRHS().getName());
		return sb.toString();
	}

}
