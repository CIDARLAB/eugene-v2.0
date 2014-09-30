package org.cidarlab.eugene.dom.rules;

import java.util.List;
import java.util.ArrayList;

import com.rits.cloning.Cloner;

/**
 * The LogicalAnd combines a list of conjunctive constraints.
 * 
 * The LogicalAnd does NOT implement the Constraint interface since 
 * Eugene supports the Disjunctive Normal Form.
 * 
 * @author Ernst Oberortner
 */
public class LogicalAnd {

	/*
	 * A List of constraints, which can either be
	 * LogicalOr, LogicalNot, or Predicate (i.e. primitive constraints)
	 * 
	 */
	private List<Predicate> predicates;
	
	/**
	 * default constructor
	 */
	public LogicalAnd() {
		this.predicates = new ArrayList<Predicate>();
	}
	
	/**
	 * 
	 * @return the list of conjunctive constraints
	 */
	public List<Predicate> getPredicates() {
		return this.predicates;
	}

	/**
	 * 
	 * @param constraints a list of conjunctive constraints
	 */
	public void setPredicates(List<Predicate> constraints) {
		this.predicates = new Cloner().deepClone(constraints);		
	}
	
	/**
	 * The union/1 method adds all constraints of a given LogicalAnd predicate 
	 * to this LogicalAnd predicate
	 * @param land
	 */
	public void union(LogicalAnd land) {
		if(null != land && null!=land.getPredicates() && !land.getPredicates().isEmpty()) {
			for(Predicate c : land.getPredicates()) {
				this.getPredicates().add(c);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.getPredicates().size(); i++) {
			sb.append(this.getPredicates().get(i));
			if(i < this.getPredicates().size() - 1) {
				sb.append(" AND ");
			}
		}
		return sb.toString();
	}
}
