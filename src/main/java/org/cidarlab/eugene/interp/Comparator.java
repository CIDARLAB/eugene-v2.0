package org.cidarlab.eugene.interp;

import java.util.List;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * The Comparator compares variables and expressions 
 * regarding their types and values.
 * 
 * @author Ernst Oberortner
 */
public class Comparator {

		public boolean evaluateCondition(Variable lhs, String op, Variable rhs) 
				throws EugeneException {
			
			if(EugeneConstants.LT.equals(op)) {
				if(!EugeneConstants.NUM.equals(lhs.getType())) {
					throw new EugeneException("The types of the LHS and RHS are not numeric!");
				}
				return this.lt(lhs.getNum(), rhs.getNum());
			} else if(EugeneConstants.LEQ.equals(op)) {
				if(!EugeneConstants.NUM.equals(lhs.getType())) {
					throw new EugeneException("The types of the LHS and RHS are not numeric!");
				}
				return this.leq(lhs.getNum(), rhs.getNum());
			} else if(EugeneConstants.EQUALS.equals(op) ||
					"==".equals(op)) {
				if(EugeneConstants.NUM.equals(lhs.getType())) {
					return this.num_eq(lhs.getNum(), rhs.getNum());
				} else if(EugeneConstants.NUMLIST.equals(lhs.getType())) {
					return this.numList_eq(lhs.getNumList(), rhs.getNumList());
				} else if(EugeneConstants.TXT.equals(lhs.getType())) {
					return this.txt_eq(lhs.getTxt(), rhs.getTxt());
				} else if(EugeneConstants.TXTLIST.equals(lhs.getType())) {
					return this.txtList_eq(lhs.getTxtList(), rhs.getTxtList());
				} else if(EugeneConstants.BOOLEAN.equals(lhs.getType())) {
					return this.bool_eq(lhs.getBool(), rhs.getBool());
				}
			} else if(EugeneConstants.NOTEQUALS.equals(op) ||
					"!=".equals(op)) {
				if(EugeneConstants.NUM.equals(lhs.getType())) {
					return !this.num_eq(lhs.getNum(), rhs.getNum());
				} else if(EugeneConstants.NUMLIST.equals(lhs.getType())) {
					return !this.numList_eq(lhs.getNumList(), rhs.getNumList());
				} else if(EugeneConstants.TXT.equals(lhs.getType())) {
					return !this.txt_eq(lhs.getTxt(), rhs.getTxt());
				} else if(EugeneConstants.TXTLIST.equals(lhs.getType())) {
					return !this.txtList_eq(lhs.getTxtList(), rhs.getTxtList());
				} else if(EugeneConstants.BOOLEAN.equals(lhs.getType())) {
					return !this.bool_eq(lhs.getBool(), rhs.getBool());
				}
			} else if(EugeneConstants.GEQ.equals(op)) {
				if(!EugeneConstants.NUM.equals(lhs.getType())) {
					throw new EugeneException("The types of the LHS and RHS are not numeric!");
				}
				return this.geq(lhs.getNum(), rhs.getNum());
			} else if(EugeneConstants.GT.equals(op)) {
				if(!EugeneConstants.NUM.equals(lhs.getType())) {
					throw new EugeneException("The types of the LHS and RHS are not numeric!");
				}
				return this.gt(lhs.getNum(), rhs.getNum());
			}
			
			return true;
		} 
		
		// num vs num
		private boolean num_eq(double a, double b) {
			return a == b;
		}

		// num[] vs num[]
		private boolean numList_eq(List<Double> a, List<Double> b) {
			
			if(a.size() != b.size()) {
				return false;
			}
			
			for(int i=0; i<a.size(); i++) {
				if(a.get(i).doubleValue() != b.get(i).doubleValue()) {
					return false;
				}
			}
			return true;
		}

		// txt vs txt
		private boolean txt_eq(String a, String b) {
			return a.equalsIgnoreCase(b);
		}

		// txt[] vs ntxt[]
		private boolean txtList_eq(List<String> a, List<String> b) {
			if(a.size() != b.size()) {
				return false;
			}
			for(int i=0; i<a.size(); i++) {
				if(!a.get(i).equalsIgnoreCase(b.get(i))) {
					return false;
				}
			}
			return true;
		}

		// bool vs. bool
		private boolean bool_eq(boolean a, boolean b) {
			return a == b;
		}

		/*---------------------------------------
		 * NUMERIC COMPARISONS <, <=, >, >=
		 *---------------------------------------*/

		private boolean lt(double a, double b) {
			return a < b;
		}

		private boolean leq(double a, double b) {
			return a <= b;
		}

		private boolean geq(double a, double b) {
			return a >= b;
		}

		private boolean gt(double a, double b) {
			return a > b;
		}
}
