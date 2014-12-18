package org.cidarlab.eugene.interp;

import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.rules.LogicalAnd;
import org.cidarlab.eugene.dom.rules.Predicate;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The RuleBuilder class serves to build Eugene rules.
 * A RuleBuilder is defined on a specific Device and it provides
 *  
 * @author Ernst Oberortner
 */
public class RuleBuilder {

	/**
	 * The instantiate/2 method takes as input the name of the 
	 * rule and a Device object on that the rule is being specified.
	 * 
	 * @param name  ... name of the rule
	 * @param d     ... the device on that rule is being specified
	 * 
	 * @return
	 */
	public Rule instantiate(String name, Device d) {
		return new Rule(name, d);
	}
			
	
	/**
	 * The and/1 method takes as input a rule and 
	 * appends it to the existing rules using the 
	 * logical AND operation (/\).
	 * 
	 * @param r  ... the rule to be appended
	 * 
	 * @throws EugeneException
	 */
	public void and(Rule r, Predicate p) 
			throws EugeneException {
		
		if(p instanceof Rule) {
			// if a rule is being ANDed to a rule, 
			// then we need to union them
			LogicalAnd rhs = ((Rule)p).getPredicates();
			for(Predicate rhsPredicate : rhs.getPredicates()) {
				
				// i.e. we add each predicate of the RHS rule to the 
				// LHS rule
				r.getPredicates().getPredicates().add(rhsPredicate);
			}
			
		} else {
			// otherwise, we do this.
			r.getPredicates().getPredicates().add(p);
		}
	}
}
