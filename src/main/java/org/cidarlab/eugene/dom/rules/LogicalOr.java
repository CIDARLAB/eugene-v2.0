package org.cidarlab.eugene.dom.rules;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.dom.rules.exp.ExpressionConstraint;

import com.rits.cloning.Cloner;

/**
 * The LogicalOr class serves to combine multiple predicates using the
 * logical or operator.
 * 
 * A LogicalOr can only be composed of LogicalNot and Predicates.
 * 
 * @author Ernst Oberortner
 *
 */
public class LogicalOr 
		implements Predicate {

	private List<Predicate> constraints;
	
	/**
	 * default constructor
	 */
	public LogicalOr() {
		this.constraints = new ArrayList<Predicate>();
	}
	
	/**
	 * 
	 * @return the list of disjunctive predicates
	 */
	public List<Predicate> getConstraints() {
		return this.constraints;
	}

	/**
	 * The validate/0 method validates the logical disjunction of constraints.
	 * A logical disjunction is valid, if all constraints are of the same type (either arrangement or expression). 
	 * 
	 * Examples:
	 * p before c \/ p.strength >= 0  ... is an invalid disjunction since it 
	 *                                    combines an arrangement and an expression constraint
	 * p before c \/ c before p       ... is a valid disjunction
	 * p.strength >=0 \/ c.expression >= 100 ... is a valid disjunction
	 * 
	 * @return true ... if all disjunctive predicates are of the same type
	 */
	public boolean validate() {
		/*
		 * iterate over the predicates and compare each one 
		 * with its predecessor
		 */

		Predicate p0 = this.getConstraints().get(0);
		if(p0 instanceof LogicalNot) {
			p0 = ((LogicalNot) p0).getPredicate();
		}
		
		for(int i=1; i<this.getConstraints().size(); i++) {
			Predicate pi = this.getConstraints().get(i);
			
			/*
			 * dealing with negated predicates (e.g. not contains p)
			 */
			if(pi instanceof LogicalNot) {
				pi = ((LogicalNot) pi).getPredicate();
			}
			
			/*
			 * 
			 */
			if(! (p0 instanceof ExpressionConstraint && pi instanceof ExpressionConstraint ||
			      p0 instanceof ArrangementConstraint && pi instanceof ArrangementConstraint)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param constraints a list of conjunctive constraints
	 */
	public void setConstraints(List<Predicate> constraints) {
		this.constraints = new Cloner().deepClone(constraints);		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.getConstraints().size(); i++) {
			sb.append(this.getConstraints().get(i));
			if(i < this.getConstraints().size() - 1) {
				sb.append(" OR ");
			}
		}
		return sb.toString();
	}
	
}
