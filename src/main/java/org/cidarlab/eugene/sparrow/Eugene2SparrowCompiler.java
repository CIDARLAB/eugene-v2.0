/* Copyright (c) 2015, Boston University
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list 
 * of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without specific prior 
 * written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.sparrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.rules.LogicalNot;
import org.cidarlab.eugene.dom.rules.LogicalOr;
import org.cidarlab.eugene.dom.rules.Predicate;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.dom.rules.exp.Expression;
import org.cidarlab.eugene.dom.rules.exp.ExpressionConstraint;
import org.cidarlab.eugene.dom.rules.exp.ExpressionOperand;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.EugeneDeveloperUtils;
import org.cidarlab.sparrow.rule.SparrowQuery;
import org.cidarlab.sparrow.rule.SparrowRule;

/**
 * The Eugene2SparrowCompiler class provides methods to compile
 * Eugene expression rules to JBoss Drools rules.
 * 
 * @author Ernst Oberortner
 */
public class Eugene2SparrowCompiler {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	/*
	 * In the operandsMap we define the mapping between Eugene expressional operands 
	 * and Drools, such as STARTSWITH -> str[startsWith] 
	 * 
	 * key   ... Eugene Operand (in lower case)
	 * value ... the Drools operand
	 */
	private Map<String, String> operandsMap;

	private StringBuilder sb_components;
	private StringBuilder sb_eval;
	private Map<String, String> values_bindings;
	private Set<StringBuilder> evaluations;
	
	/*
	 * Example:
	 * key   ... $Promoter
	 * value ... Component ( type == "Promoter", propertyValues["strength"] !=null, $Promoter_strength : propertyValues["strength"].num )
	 */
	private Map<String, String> components_binding;

	
	/*
	 * ordered list of component definitions 
	 * this list serves to keep track about the dependencies among variable bindings
	 */
	private List<String> locd;
	
	private boolean DO_QUERY;
	
	public Eugene2SparrowCompiler() {
	}

	/*----------------------------------------------------------
	 * PUBLIC MEMBER FUNCTIONS
	 * compileQuery, compilePrune
	 *----------------------------------------------------------*/

	/**
	 * 
	 * @param rule
	 * @return
	 */
	public List<SparrowQuery> compileQuery(Rule rule) 
			throws EugeneException {

		this.setQuerying(true);
		this.initGlobals();
		
		/*
		 * only if the user has specified a query, then
		 * we compile the Eugene selection rule into a Drools query
		 */
		if(null != rule && null != rule.getPredicates() && 
				null != rule.getPredicates().getPredicates() &&
				!rule.getPredicates().getPredicates().isEmpty()) {
			this.compilePredicates(rule.getPredicates().getPredicates());
		}

		/*
		 * then, we build the Drools Query string
		 */
		StringBuilder sb_query = this.buildDroolsQuery();
		
		/*
		 * and instantiate a SparrowQuery object with the 
		 * created Drools Query string 
		 */
		List<SparrowQuery> queries = new ArrayList<SparrowQuery>();
		queries.add(new SparrowQuery(rule.getName(), sb_query.toString()));
				
		/*
		 * clean up
		 */
		this.clearGlobals();
		
		return queries;
	}
	
	
	/**
	 * 
	 * @param rule
	 * @return
	 */
	public SparrowRule compilePrune(Rule rule) 
			throws EugeneException {

		/*
		 * we do not query when we prune
		 */
		this.setQuerying(false);
		this.initGlobals();
		
		StringBuilder sb = new StringBuilder();
		SparrowRule sp_rule = null;
		if(null != rule && null != rule.getPredicates() && 
				null != rule.getPredicates().getPredicates() && !rule.getPredicates().getPredicates().isEmpty()) {
			
			sb.append("$d : Device ( ");
			if(null != rule.getDevice()) {
				sb.append("name str[startsWith] \"").append(rule.getDevice().getName()).append("\", "); 
			}
			sb.append("$loc : componentList )").append(NEWLINE);
			
			this.compilePredicates(rule.getPredicates().getPredicates());

			sb.append(this.buildDroolsQuery());
				
			sp_rule = new SparrowRule(rule.getName(), sb.toString(), "results.add($d);");
		}

		this.clearGlobals();

		return sp_rule;		
	}
	
	
	
	/*------------------------------------------------
	 * COMPILATION METHODS
	 *------------------------------------------------*/
	private void compilePredicates(List<Predicate> predicates) 
			throws EugeneException {
		// we use this flag for the commas
		boolean bExpression = false;

		StringBuilder sbConstraints = new StringBuilder();
		sbConstraints.append("( ");
		for(Predicate p : predicates) {
			
			/*
			 * we only compile a Eugene predicate to a Drools query/rule 
			 * if the Eugene predicate is an expression
			 */
			if(p instanceof ExpressionConstraint) {

				String ec = this.compileExpressionConstraint((ExpressionConstraint)p);

				if(null != ec && !ec.isEmpty()) {
					if(bExpression) {
						if(((ExpressionConstraint)p).isQuery()) {
							sbConstraints.append("|| ");
						} else {
							sbConstraints.append("&& ( ");
						}
					}
					
					sbConstraints.append("( ").append(ec).append(" ) ");
					
					// close the opening parenthesis from the &&
					if(bExpression && !((ExpressionConstraint)p).isQuery()) {
						sbConstraints.append(" ) ");
					}
	
					bExpression = true;
				}

				
				this.evaluations.add(this.sb_eval);
				this.sb_eval = new StringBuilder();
				
			} else if(p instanceof LogicalOr && 
					((LogicalOr)p).getConstraints().get(0) instanceof ExpressionConstraint) {

				if(bExpression) {
					sbConstraints.append(" && ");
				}
				
				/*
				 * if the predicate is a logical disjunction, then
				 * we get the first predicate and check if its a expression
				 * 
				 * we can do this, since all predicates/constraints in a logical disjunction 
				 * must be of the same type --- either expression or arrangement
				 */
				Predicate subP = ((LogicalOr)p).getConstraints().get(0);
				if(subP instanceof ExpressionConstraint && ((ExpressionConstraint)subP).isQuery()) {
					sbConstraints.append("(");
					
					boolean bConstraint = false;
					
					for(int i=0; i<((LogicalOr)p).getConstraints().size(); i++) {
						
						String ec = this.compileExpressionConstraint(
								(ExpressionConstraint)((LogicalOr)p).getConstraints().get(i));

						if(null != ec) {
							if(bConstraint) {
								sbConstraints.append(" || ");
							}
							sbConstraints.append(ec);
							bConstraint = true;
						}
						
						if(i < ((LogicalOr)p).getConstraints().size() - 1) {
							if(null != this.sb_eval) {
								this.sb_eval.append(" || ");
							}
							if(null != this.tmp) {
								this.tmp.append(" || ");
							}
						}
					}
					sbConstraints.append(") ");
					
				}
				
				bExpression = true;
				
				this.evaluations.add(this.sb_eval);
				this.sb_eval = new StringBuilder();
			}
		}	
		
		sbConstraints.append(")");
		this.sb_ret = sbConstraints;

		this.tmp = null;
		
		/*
		 * finally, we append the compiled constraint to our 
		 * constraints stringbuffer
		 */
		this.sb_components.append(sbConstraints);
	}

	private StringBuilder tmp = null;
	private StringBuilder sb_ret = null;
	
	private String compileExpressionConstraint(ExpressionConstraint exp) 
			throws EugeneException {

		String exp_op = null;
		
		/*
		 * <constant> <rel-op> <expression> 
		 */
		if(exp.getLhs().isConstant()) {

			if(EugeneConstants.NUMLIST.equals(exp.getRhs().getType()) || 
					EugeneConstants.TXTLIST.equals(exp.getRhs().getType())) {
				if(null == this.tmp) {
					this.tmp = new StringBuilder();
				}
				this.tmp.append(this.operandsMap.get(exp.getOp().toLowerCase())).append(" ")
						.append(this.Constant2String(exp.getRhs().getLhs().getConstant()));

				if(this.isRelationalOperator(this.operandsMap.get(exp.getOp().toLowerCase()))) {
					this.sb_eval.append(" ").append(this.tmp);				
				} else {
					this.sb_eval = null;
				}
			} else {
				this.compileExpressionConstant(exp.getLhs());
				this.sb_eval.append(" ").append(this.operandsMap.get(exp.getOp().toLowerCase())).append(" ");			
			}
			
			exp_op = this.compileExpression(exp.getRhs(), exp.isQuery());

		/*
		 * <expression> <rel-op> <constant>  
		 */
		} else if(exp.getRhs().isConstant()) {

//			this.compileExpressionConstant(exp.getRhs());
			if(EugeneConstants.NUMLIST.equals(exp.getLhs().getType()) || 
					EugeneConstants.TXTLIST.equals(exp.getLhs().getType())) {

				if(null == this.tmp) {
					this.tmp = new StringBuilder();
				}
				
				this.tmp.append(this.operandsMap.get(exp.getOp().toLowerCase())).append(" ")
						.append(this.Constant2String(exp.getRhs().getLhs().getConstant()));

				exp_op = this.compileExpression(exp.getLhs(), exp.isQuery());

				if(this.isRelationalOperator(this.operandsMap.get(exp.getOp().toLowerCase()))) {
					this.sb_eval.append(" ").append(this.tmp);				
				} else {
					this.sb_eval = null;
				}

			} else {
				
				exp_op = this.compileExpression(exp.getLhs(), exp.isQuery());

				this.sb_eval.append(" ").append(this.operandsMap.get(exp.getOp().toLowerCase())).append(" ");		
				
				this.compileExpressionConstant(exp.getRhs());
			}
			
		/*
		 * <expression> <rel-op> <expression>  
		 */
		} else if(!exp.getLhs().isConstant() && !exp.getRhs().isConstant()) {
			
			exp_op = this.compileExpression(exp.getLhs(), exp.isQuery());
			this.sb_eval.append(" ").append(exp.getOp()).append(" ");

			exp_op += " || " + this.compileExpression(exp.getRhs(), exp.isQuery());
			

			// we need to switch the last to variables of component definitions
			// since the lhs component depends on the rhs component
			if(this.locd.size() >= 2) {
				String tmp = locd.get(this.locd.size() - 1);
				locd.set(locd.size() - 1, locd.get(locd.size() - 2));
				locd.set(locd.size() - 2, tmp);
			}
			
		/*
		 * <constant> <rel-op> <constant>	
		 */
		} else {
			// we just don't allow such constillations
			throw new EugeneException(exp.getLhs() + " " + exp.getOp() + " " + exp.getRhs() + " is an invalid expression.");
		}
		
		if(null != this.sb_eval) {
			this.evaluations.add(this.sb_eval);
		}
		
		return exp_op;
	}
	
	private String compileExpression(Expression exp, boolean isQuery) {

		String exp_op = null;
		if(exp.getRhs() == null) {
			/*
			 * <lhs>
			 */
			if(!exp.getLhs().isConstant()) {
				
				exp_op = this.compileExpressionOperand(exp.getLhs(), isQuery);
				if(null != exp_op) {
					this.locd.add(exp_op);
				
					exp_op = "name == " + this.locd.get(this.locd.size() - 1) + ".name";
				}
			} else {
				this.sb_eval.append(exp.getLhs());
			}
		} else {
			/*
			 * <lhs> <op> <rhs>
			 */
			if(!exp.getLhs().isConstant()) {
				exp_op = this.compileExpressionOperand(exp.getLhs(), isQuery);
				if(null != exp_op) {
					this.locd.add(exp_op);
				
					exp_op = "name == " + this.locd.get(this.locd.size() - 1) + ".name";
				}
			} else {
				this.sb_eval.append(exp.getLhs());
			}

			this.sb_eval.append(" ").append(this.toOperator(exp.getOp())).append(" ");
			
			String s = this.compileExpression(exp.getRhs(), isQuery);
			if(null != s) {
				if(null != exp_op) {
					exp_op += " || " + s ;
				} else {
					exp_op = s;
				}
			}
			
		}
		
		return exp_op;

	}
	
	private String compileExpressionOperand(ExpressionOperand eop, boolean isQuery) {
		// $Promoter
		StringBuilder sb_variable = new StringBuilder();
		
		// Component ( ... )
		StringBuilder sb_component = new StringBuilder();
		
		// $Promoter_strength
		StringBuilder sb_property = new StringBuilder();
		
		// propertyValues["strength"]
		StringBuilder sb_value = new StringBuilder();
		
		/*
		 * <lhs> . <property>
		 */
		sb_component.append("Component( ");
		sb_property.append("$");
		if(null != eop.getElements() && !eop.getElements().isEmpty()) {			
			sb_component.append("typeAsString == \"").append((eop.getElements().get(eop.getElements().size()-1).getName())).append("\", ");
			sb_property.append((eop.getElements().get(eop.getElements().size()-1).getName())).append("_");
		}
		
		/*
		 * compile eventually specified indices
		 */
		StringBuilder sb_indices = null;
		StringBuilder sb_suffix = null;
		int last_idx = -1;
		if(null != eop.getIndices() && !eop.getIndices().isEmpty()) {
			sb_indices = new StringBuilder();
			sb_suffix = new StringBuilder();
			for(int idx : eop.getIndices()) {
				sb_indices.append("[").append(idx).append("]");
				sb_suffix.append(idx);
				
				last_idx = idx;
			}
		}

		/*
		 * naming of the variable for binding the property value,
		 * which is used either for constraints that 
		 * need to be added to the Component(...) statement 
		 * or to the eval() statement
		 */
		sb_property.append(eop.getProperty().getName());
		
		/*
		 * if indices have been specified, then 
		 * we name the property binding variable accordingly
		 */
		if(null != sb_suffix) {
			sb_property.append("_").append(sb_suffix);
		}
		
		/*
		 * get the property value and check against NULL values
		 */
		sb_value.append("propertyValues[\"").append(eop.getProperty().getName()).append("\"] != null, ")
				.append("propertyValues[\"").append(eop.getProperty().getName()).append("\"].").append(eop.getProperty().getType()).append(" != null, ");
		if(last_idx != -1) {
			/*
			 * avoiding ArrayIndexOutOfBounds exceptions
			 */
			sb_value.append("propertyValues[\"").append(eop.getProperty().getName()).append("\"].size > ").append(last_idx).append(", ");
		}		
		sb_value.append(sb_property).append(" : ")
			.append("propertyValues[\"").append(eop.getProperty().getName()).append("\"].")
			.append(eop.getProperty().getType());
		if(null != sb_indices) {
			sb_value.append(sb_indices);
		}
		
		sb_component.append(sb_value).append(" )");

		this.sb_eval.append(sb_property);

		if(!this.DO_QUERY) {
			sb_component.append(" from $loc ");
		}
			
		/*
		 * variable naming
		 */
		sb_variable.append("$");
		if(null != eop.getElements()) {
			sb_variable.append((eop.getElements().get(eop.getElements().size()-1).getName())).append("_");
		}
		sb_variable.append(System.nanoTime());
		
		boolean bExists = false;
		for(String value : this.components_binding.values()) {
			if(value.equals(sb_component.toString())) {
				bExists = true;
				break;
			}
		}

		if(!bExists) {
			this.components_binding.put(sb_variable.toString(), sb_component.toString());
			return sb_variable.toString();
		}
		
		return null;
	}
	
	/**
	 * The compileExpressionConstant(Expression) compiles an expression's 
	 * operand to a String representation if the operand is a constant or variable.
	 * 
	 * @param exp  ... the expression
	 * 
	 * @throws EugeneException
	 */
	private void compileExpressionConstant(Expression exp) 
			throws EugeneException {
		if(exp.getLhs().isConstant()) {
			this.sb_eval.append(this.Constant2String(exp.getLhs().getConstant()));
		} else if(exp.getRhs().isConstant()) {
			this.sb_eval.append(this.Constant2String(exp.getRhs().getLhs().getConstant()));
		}
	}
	
	private StringBuilder buildDroolsQuery() {
		StringBuilder sb_query = new StringBuilder();

		/*
		 * EVALUATIONS
		 */
		if(null != this.evaluations && !this.evaluations.isEmpty()) {
			for(StringBuilder sb : this.evaluations) {
				if(null != sb) {   

					if(!(sb.toString().contains("+") || sb.toString().contains("-") || 
					     sb.toString().contains("/") || sb.toString().contains("*"))) {
					
						String[] evalTokens = sb.toString().split(" ");
						
						/*
						 * iterate over all component bindings and add the 
						 * eval statement to the component binding
						 */
						for(String s : this.components_binding.keySet()) {
							if(this.components_binding.get(s).contains(evalTokens[0]+" :")) {
								this.components_binding.put(s, this.components_binding.get(s).replace(" )", ", "+sb.toString()+" )"));
							}
						}
					}
				}
			}
		}
		
		
		/*
		 * COMPONENTS
		 */
		for(String s : this.locd) {
			
			/*
			 * analyze the component binding
			 */
			if(!s.isEmpty()) {
				sb_query.append(s).append(": ");
			}
			sb_query.append(this.components_binding.get(s)).append(NEWLINE);
			
			// remove it from the components_bindings
			this.components_binding.remove(s);
		}
		
		if(!this.components_binding.isEmpty()) {
			for(String s : this.components_binding.keySet()) {
				if(!s.isEmpty()) {
					sb_query.append(s).append(": ");
				}
				sb_query.append(this.components_binding.get(s)).append(NEWLINE);
			}
		}
		
		sb_query.append(NEWLINE);
		
		/*
		 * EVALUATIONS
		 */
		if(null != this.evaluations && !this.evaluations.isEmpty()) {
			for(StringBuilder sb : this.evaluations) {
				if(null != sb) {   // WHY DO I HAVE TO CHECK THIS HERE??

					if(sb.toString().contains("+") || sb.toString().contains("-") || 
					   sb.toString().contains("/") || sb.toString().contains("*")) {
						sb_query.append("eval ( ").append(sb).append(" ) ").append(NEWLINE);
					}
				}
			}
		}
		sb_query.append(NEWLINE);
		
		
		if(this.DO_QUERY) {
			/*
			 * RETURN
			 */
			sb_query.append("$ret : Component (");
			if(!"( )".equals(sb_ret.toString())) {
				sb_query.append(this.sb_ret);
			}
			sb_query.append(")");
//			if(null != this.components_binding && !this.components_binding.keySet().isEmpty()) {
//				Iterator<String> it = this.components_binding.keySet().iterator();
//				while(it.hasNext()) {
//					String component_name = it.next();
//					if(!component_name.isEmpty()) {
//						sb_query.append("name == ").append(component_name).append(".name");
//						if(it.hasNext()) {
//							sb_query.append(" || ");
//						}
//					}
//				}
//			}
//			sb_query.append(")");
		}
		
		return sb_query;
	}

	
	/*------------------------------------------------
	 * COMPILATION HELPER METHODS
	 *------------------------------------------------*/
	private boolean hasQuery(List<Predicate> predicates) {
		boolean bHasQuery = false;
		if(null != predicates && !predicates.isEmpty()) {
			for(int i=0; i<predicates.size() && !bHasQuery; i++) {
				Predicate p = predicates.get(i);
				bHasQuery = this.isQuery(p);
			}
		}
		return bHasQuery;
	}
	
	private boolean isQuery(Predicate p) {
		if(p instanceof ExpressionConstraint) {
			return ((ExpressionConstraint)p).isQuery();
		} else if(p instanceof LogicalOr) {
			return this.hasQuery(((LogicalOr)p).getConstraints());
		} else if(p instanceof LogicalNot) {
			return this.isQuery(((LogicalNot)p).getPredicate());
		}
		return false;
	}

	private void setQuerying(boolean querying) {
		this.DO_QUERY = querying;
	}
	
//	private StringBuilder toListBinding(ExpressionConstraint ec) {
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append(this.compileToLists(ec.getLhs())).append(NEWLINE);
//		sb.append(this.compileToLists(ec.getRhs())).append(NEWLINE);
//		
//		return sb;
//	}
//	
//	private StringBuilder compileToLists(Expression exp) {
//		StringBuilder sb = new StringBuilder();
//		if(!exp.getLhs().isConstant()) {
//			// list  binding for lhs
//			String name = exp.getLhs().getElements().get(exp.getLhs().getElements().size() - 1).getName();
//			sb.append("$").append(name).append("List: List() from $loc");
//
//			if(null != exp.getRhs()) {
//				sb.append(NEWLINE).append(this.compileToLists(exp.getRhs()));
//			}
//		}
//		return sb;
//	}

	private String toOperator(Expression.ExpOp eop) {
		switch(eop) {
		case PLUS:
			return "+";
		case MINUS:
			return "-";
		case MULT:
			return "*";
		case DIV:
			return "/";
		}
		return null;
	}

	
	/*------------------------------------------------
	 * COMPILATION HELPER METHODS -- INITIALIZATIONS
	 *------------------------------------------------*/
	private void initGlobals() {
		/*
		 * init the ``global'' variables
		 */
		this.components_binding = new HashMap<String, String>();
		this.values_bindings = new HashMap<String, String>();
		this.evaluations = new HashSet<StringBuilder>();
		this.sb_eval = new StringBuilder();
		this.sb_components = new StringBuilder();	
		this.locd = new ArrayList<String>();
		
		this.initOperandsMap();
	}
	
	private void initOperandsMap() {
		this.operandsMap = new HashMap<String, String>();
		this.operandsMap.put("==", "==");
		this.operandsMap.put("!=", "!=");
		this.operandsMap.put("<", "<");
		this.operandsMap.put("<=", "<=");
		this.operandsMap.put("<=", "<=");
		this.operandsMap.put(">=", ">=");
		this.operandsMap.put(">", ">");
		this.operandsMap.put("equals", "==");
		this.operandsMap.put("notequals", "!=");
		this.operandsMap.put("contains", "contains");
		this.operandsMap.put("not contains", "not contains");
		this.operandsMap.put("notcontains", "not contains");
		this.operandsMap.put("matches", "matches");
		this.operandsMap.put("not matches", "not matches");
		this.operandsMap.put("startswith", "str[startsWith]");
		this.operandsMap.put("not startswith", "not str[startsWith]");
		this.operandsMap.put("endswith", "str[endsWith]");
		this.operandsMap.put("not endswith", "not str[endsWith]");
		this.operandsMap.put("soundslike", "soundslike");
		this.operandsMap.put("not soundslike", "not soundslike");		
	}
	
	private boolean isRelationalOperator(String op) {
		return "==".equals(op) ||  "!=".equals(op) ||  
				"<".equals(op) ||  "<=".equals(op) ||
				">=".equals(op) ||  ">".equals(op);
	}
	
	private void clearGlobals() {
		if(null != values_bindings) {
			this.values_bindings.clear();
			this.values_bindings = null;
		}
		if(null != this.locd) {
			this.locd.clear();
			this.locd = null;
		}
		this.sb_components = null;
		this.sb_eval = null;
	}
	
	/**
	 * The Constant2String(Variable) method compiles 
	 * a Eugene Variable object into a String. 
	 * If the Variable is of type txt or txt[], then 
	 * we must add double-quotes to them (if not specified).
	 * Otherwise, we utilize the Variable.getValue() method.
	 * 
	 * @param v  ... the Variable to be compiled
	 * @return ... a String representation of the Variable's value
	 */
	private String Constant2String(Variable v) 
				throws EugeneException {
		
		// if the variable is of type num, num[], or bool,
		// then we just return its value
		if(EugeneConstants.NUM.equals(v.getType())) {

			return String.valueOf(v.getNum());
			
		} else if(EugeneConstants.NUMLIST.equals(v.getType())) {
			
			return String.valueOf(v.getNumList());
			
		} else if(EugeneConstants.BOOLEAN.equals(v.getType())) {
		
			return String.valueOf(v.getBool());
			
		// if the variable is of type txt, then
		// we add double-quotes if not existing
		} else if(EugeneConstants.TXT.equals(v.getType())) {

			return EugeneDeveloperUtils.addDoubleQuotesTo(v.getTxt());
			
		} else if(EugeneConstants.TXTLIST.equals(v.getType())) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			int i = 0;
			// we iterate over every element
			for(String d : v.getTxtList()) {
				
				// and add double-quotes around every list element
				sb.append(EugeneDeveloperUtils.addDoubleQuotesTo(d));
				
				
				if(i < v.getTxtList().size() - 1) {
					// comma separated list
					sb.append(",");
				}
			}
			sb.append("]");
			
			return sb.toString();
		}
		
		throw new EugeneException("Invalid type of " + v);
	}
	

}
