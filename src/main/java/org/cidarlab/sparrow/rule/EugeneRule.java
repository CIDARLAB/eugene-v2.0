package org.cidarlab.sparrow.rule;

import java.util.ArrayList;
import java.util.List;
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
public class EugeneRule 
		extends NamedElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7718948136863290639L;
	private List<Predicate> predicates;
	
	/* TODO: 
	 * - think and do something with conclusions
	 *  
	private String conclusion;
	 */
	
	public EugeneRule() {
		super("query_"+UUID.randomUUID().toString().replace('-', '_'));
		
		this.predicates = new ArrayList<Predicate>();
	}
	
	public EugeneRule(String name) {
		super(name);
		
		this.predicates = new ArrayList<Predicate>();
	}

	public EugeneRule(String name, Predicate predicate) {
		super(name);
		this.predicates = new ArrayList<Predicate>();
		this.getPredicates().add(predicate);
	}
	
	public EugeneRule(String name, List<Predicate> predicates) {
		super(name);
		
		this.predicates = predicates;
	}
	
	public List<Predicate> getPredicates() {
		return this.predicates;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append(" #= ");
		if(null != this.predicates && !this.predicates.isEmpty()) {
			for(int i=0; i<predicates.size(); i++) {
				sb.append(this.predicates.get(i));
				if(i < predicates.size() - 1) {
					sb.append(" /\\ ");
				}
			}
		}
		return sb.toString();
	}
}
