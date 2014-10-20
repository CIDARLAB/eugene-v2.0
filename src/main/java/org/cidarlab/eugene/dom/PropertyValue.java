/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.eugene.dom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.exception.*;


/**
 * 
 * @author Ernst Oberortner
 */
public class PropertyValue 
	extends Property {

	private static final long serialVersionUID = 3074125010808580574L;

	private boolean bool;
	private String txt;
	private double num;
	private List<Double> numList;
	private List<String> txtList;

	/*
	 * the property value is an instance of a given property
	 */
	private Property property;
	
	public PropertyValue(String name, String type) {
		super(name, type);

		txt = new String();
		num = 0.0;
		bool = false;
		numList = new ArrayList<Double>();
		txtList = new ArrayList<String>();
		
		this.property = new Property(name, type);
	}

	public PropertyValue(Property property) {
		super(property.getName(), property.getType());

		txt = new String();
		num = 0.0;
		bool = false;
		numList = new ArrayList<Double>();
		txtList = new ArrayList<String>();
		
		this.property = property;
	}
	
	public void setType(String sType) {
		this.type = sType;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @return the property of that the property value is an instance of
	 */
	public Property getProperty() {
		return this.property;
	}
	
	public void setTxt(String txt) {
		if (null != txt) {
			if (txt.startsWith("\"") && txt.endsWith("\"")) {
				this.txt = txt.substring(1, txt.length() - 1);
				return;
			}
		}
		this.txt = txt;
	}

	public String getTxt() {
		return this.txt;
	}

	public void setTxtList(ArrayList<String> txtList) {
		this.txtList = txtList;
	}

	public void addTxt(String s) {
		if (EugeneConstants.TXTLIST.equals(this.type)) {
			this.txtList.add(s);
		} else {
			System.err.println("cannot add a string to property "
					+ this.getName());
		}
	}

	public void setTxtList(List<String> txtList) {
		if (null == txtList)
			return;
		this.txtList = new ArrayList<String>();
		Iterator<String> it = txtList.iterator();
		while (it.hasNext()) {
			this.txtList.add(it.next());
		}
	}

	public List<String> getTxtList() {
		return this.txtList;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public double getNum() {
		return this.num;
	}

	public void setNumList(List<Double> numList) {
		this.numList = numList;
	}

	public void addNum(double d) {
		if (EugeneConstants.NUMLIST.equals(this.type)) {
			this.numList.add(new Double(d));
		} else {
			System.err.println("cannot add a number to property "
					+ this.getName());
		}
	}

	public List<Double> getNumList() {
		return this.numList;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public boolean getBool() {
		return this.bool;
	}

	public void setValue(PropertyValue value) {
		if (EugeneConstants.NUM.equals(this.type)) {
			this.num = Double.parseDouble(String.valueOf(value.getNum()));
		} else if (EugeneConstants.NUMLIST.equals(this.type)) {
			this.numList = new ArrayList<Double>();
			for (int i = 0; i < value.getNumList().size(); i++) {
				this.numList.add(new Double(value.getNumList().get(i)));
			}
		} else if (EugeneConstants.TXT.equals(this.type)) {
			this.txt = new String(value.getTxt());
		} else if (EugeneConstants.TXTLIST.equals(this.type)) {
			this.txtList = new ArrayList<String>();
			for (int i = 0; i < value.getTxtList().size(); i++) {
				this.txtList.add(new String(value.getTxtList().get(i)));
			}
		} else if (EugeneConstants.BOOLEAN.equals(this.type)) {
			this.bool = new Boolean(String.valueOf(value.getBool()));
		}
	}

	public String getValue() {
		if (EugeneConstants.NUM.equals(this.type)) {
			String numberD = String.valueOf(this.num);
	        numberD = numberD.substring( numberD.indexOf ( "." )+1);
	        
	        try {
	        	// if the number is an integer, then 
	        	// we don't print the floating points
	        	if(Integer.parseInt(numberD) == 0) {
	        		return String.valueOf((int)this.num);
	        	}
	        } catch(Exception e) {
	        	return String.valueOf(this.num);
	        } 
	    } else if (EugeneConstants.NUMLIST.equals(this.type)) {
			return this.numList.toString();
		} else if (EugeneConstants.TXT.equals(this.type)) {
			return this.txt;
		} else if (EugeneConstants.TXTLIST.equals(this.type)) {
			return this.txtList.toString();
		} else if (EugeneConstants.BOOLEAN.equals(this.type)) {
			return String.valueOf(this.bool);
		}
		return new String();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(EugeneConstants.NUM.equals(this.type)) {
			
			String numberD = String.valueOf(this.num);
	        numberD = numberD.substring( numberD.indexOf ( "." )+1);

	        try {
	        	// if the number is an integer, then
	        	// we don't print the floating points
	        	if(Integer.parseInt(numberD) == 0) {
	        		sb.append((int)this.num);
	        	}
	        } catch(Exception e) {
	        	sb.append(this.num);
	        } 
		} else if(EugeneConstants.NUMLIST.equals(this.type)) {
			sb.append("[");
			for(int i=0; i<this.getNumList().size(); i++) {
				sb.append(this.getNumList().get(i));
				if(i < this.getNumList().size() - 1) {
					sb.append(",");
				}
			}
			sb.append("]");
		} else if(EugeneConstants.TXT.equals(this.getType())) {
			sb.append("\"").append(this.getValue()).append("\"");
		} else if(EugeneConstants.TXTLIST.equalsIgnoreCase(this.getType())) {
			sb.append("[");
			for(int i=0; i<this.getTxtList().size(); i++) {
				sb.append("\"").append(this.getTxtList().get(i)).append("\"");
				if(i < this.getTxtList().size() - 1) {
					sb.append(",");
				}
			}
			sb.append("]");
		} else if(EugeneConstants.BOOLEAN.equalsIgnoreCase(this.getType())) {
			sb.append(this.getBool());
		}
		return sb.toString();
	}

	public PropertyValue toPropertyValue(Part pi) {
		PropertyValue var = new PropertyValue(this.getName(), this.type);
		var.setBool(this.bool);
		var.setNum(this.num);
		var.setNumList(this.numList);
		var.setTxt(this.txt);
		var.setTxtList(this.txtList);
		return var;
	}

	public boolean compareTo(PropertyValue objVariable) {
		if (this.equals(objVariable)) {
			return true;
		}
		return false;
	}

	public boolean equals(NamedElement objElement) {
		if (objElement instanceof PropertyValue) {
			PropertyValue objVar = (PropertyValue) objElement;
			if (this.getValue() == null && objVar == null
					|| this.getValue() != null
					&& this.getValue().equals(objVar.getValue())) {
				return true;
			}
		}
		return false;
	}

	public int getSize() {
		return this.size();
	}
	
	public int size() {
		if (EugeneConstants.NUM.equals(this.getType())
				|| EugeneConstants.BOOLEAN.equals(this.getType())) {
			return 1;
		} else if (EugeneConstants.NUMLIST.equals(this.getType())) {
			return this.getNumList().size();
		} else if (EugeneConstants.TXT.equals(this.getType())) {
			return this.getTxt().length();
		} else if (EugeneConstants.TXTLIST.equals(this.getType())) {
			return this.getTxtList().size();
		}
		return -1;
	}


	public NamedElement get(int idx) {
		if (EugeneConstants.NUMLIST.equals(this.getType())) {
			if (idx >= 0 && idx < this.getNumList().size()) {
				PropertyValue objVariable = new PropertyValue(EugeneConstants.NUM,
						EugeneConstants.NUM);
				objVariable.setNum(this.numList.get(idx));
				return objVariable;
			}
		} else if (EugeneConstants.TXTLIST.equals(this.getType())) {
			if (idx >= 0 && idx < this.getTxtList().size()) {
				PropertyValue objVariable = new PropertyValue(EugeneConstants.TXT,
						EugeneConstants.TXT);
				objVariable.setTxt(this.getTxtList().get(idx));
				return objVariable;
			}
		} else if (EugeneConstants.TXT.equals(this.getType())) {
			if (idx >= 0 && idx < this.getTxt().length()) {
				PropertyValue objVariable = new PropertyValue(EugeneConstants.TXT,
						EugeneConstants.TXT);
				objVariable.setTxt(String.valueOf(this.getTxt().charAt(idx)));
				return objVariable;
			}
		}
		
		return null;
	}

	public void assign(NamedElement objElement)
			throws EugeneException {

		if (objElement instanceof PropertyValue) {
			PropertyValue objVariable = (PropertyValue) objElement;
			if (this.getType().equals(objVariable.getType())) {
				this.setValue((PropertyValue) objElement);
			} else {
				throw new EugeneException("Cannot assign a "
						+ objVariable.getType() + " value to a "
						+ this.getType() + " value!");
			}
		}
	}

	/**
	 * The set/2 method assigns a given NamedElement to the PropertyValue 
	 * at a given index.
	 * It throws a EugeneException if the array index is out of bounds 
	 * and the types do not match.
	 * 
	 * @param idx
	 * @param objElement
	 * @throws EugeneException
	 */
	public void set(int idx, NamedElement objElement)
			throws EugeneException {
		
		if (objElement instanceof PropertyValue) {
			PropertyValue objVariable = (PropertyValue) objElement;

			// num[] <- num
			if (EugeneConstants.NUMLIST.equals(this.getType())
					&& EugeneConstants.NUM.equals(objVariable.getType())) {
				if (idx >= 0 && idx < this.numList.size()) {
					this.numList.set(idx, new Double(objVariable.getNum()));
				} else if(idx == this.numList.size()) {
					this.numList.add(new Double(objVariable.getNum()));
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds!");
				}
			// txt[] <- txt
			} else if (EugeneConstants.TXTLIST.equals(this.getType())
					&& EugeneConstants.TXT.equals(objVariable.getType())) {
				if (idx >= 0 && idx < this.txtList.size()) {
					this.txtList.set(idx, new String(objVariable.getTxt()));
				} else if(this.txtList.size() == idx) {
					this.txtList.add(objVariable.getTxt());
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds!");
				}
			// txt <- txt
			} else if (EugeneConstants.TXT.equals(this.getType())
					&& EugeneConstants.TXT.equals(objVariable.getType())) {
				
				String tmp = this.txt;
				StringBuilder sb = new StringBuilder();
				if (idx >= 0 && idx <= this.txt.length()) {
					sb.append(tmp.substring(0, idx)).append(objVariable.getTxt()).append(tmp.substring(idx+1, tmp.length()));
					this.txt = sb.toString();
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds!");
				}
			} else {
				throw new EugeneException("Cannot assign a "
						+ objVariable.getType() + " value to an element of a "
						+ this.getType() + " list!");
			}
		} else if (objElement instanceof Variable) {
			Variable objVariable = (Variable) objElement;

			if (EugeneConstants.NUMLIST.equals(this.getType())
					&& EugeneConstants.NUM.equals(objVariable.getType())) {
				if (idx >= 0 && idx < this.numList.size()) {
					this.numList.set(idx, new Double(objVariable.getNum()));
				} else if(idx == this.numList.size()) {
					this.numList.add(new Double(objVariable.getNum()));
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds!");
				}
			} else if (EugeneConstants.TXTLIST.equals(this.getType())
					&& EugeneConstants.TXT.equals(objVariable.getType())) {
				if (idx >= 0 && idx < this.txtList.size()) {
					this.txtList.set(idx, new String(objVariable.getTxt()));
				} else if(idx == this.txtList.size()) {
					this.txtList.add(objVariable.getTxt());
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds!");
				}
			// txt <- txt
			} else if (EugeneConstants.TXT.equals(this.getType())
						&& EugeneConstants.TXT.equals(objVariable.getType())) {
					
				String tmp = this.txt;
				StringBuilder sb = new StringBuilder();
				if (idx >= 0 && idx <= this.txt.length()) {
					sb.append(tmp.substring(0, idx)).append(objVariable.getTxt()).append(tmp.substring(idx+1, tmp.length()));
					this.txt = sb.toString();
				} else {
					throw new EugeneException(
							"The array index (" + idx
									+ ") is out of bounds!");
				}
			} else {
				throw new EugeneException("Cannot assign a "
						+ objVariable.getType() + " value to an element of a "
						+ this.getType() + " list!");
			}
		} else {
			throw new EugeneException("Cannot assign the "
					+ objElement + " element to the " + (idx + 1)
					+ " element!");
		}
	}

	public void set(String sElementName, NamedElement objElement)
			throws EugeneException {
		throw new EugeneException("This is not possible!");
	}

	public void increase() throws EugeneException {
		if (!EugeneConstants.NUM.equals(this.getType())) {
			throw new EugeneException(
					"Cannot increase the value of a " + this.getType() + "!");
		}
		this.setNum(this.getNum() + 1);
	}

	public void decrease() throws EugeneException {
		if (!EugeneConstants.NUM.equals(this.getType())) {
			throw new EugeneException(
					"Cannot increase the value of a " + this.getType() + "!");
		}
		this.setNum(this.getNum() - 1);
	}

	public PropertyValue add(PropertyValue objVariable) 
			throws EugeneException {
		PropertyValue retVal = (PropertyValue) null;
		if (null != objVariable) {
			if (EugeneConstants.TXT.equals(this.getType())) {
				// <TXT> + <TXT>
				if (EugeneConstants.TXT.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.TXT);
					StringBuilder sb = new StringBuilder();
					sb.append(this.getTxt());
					sb.append(objVariable.getTxt());
					retVal.setTxt(sb.toString());

					// <TXT> + <TXTLIST>
				} else if (EugeneConstants.TXTLIST
						.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.TXTLIST);

					ArrayList<String> lst = new ArrayList<String>();

					lst.add(this.getTxt());

					int size = objVariable.getTxtList().size();
					for (int i = 0; i < size; i++) {
						lst.add(objVariable.getTxtList().get(i));
					}

					retVal.setTxtList(lst);

					// <TXT> + <NUM>
				} else if (EugeneConstants.NUM.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.TXT);
					retVal.setTxt(this.getTxt() + (int) objVariable.getNum());
				}
			} else if (EugeneConstants.TXTLIST.equals(this.getType())) {
				if (EugeneConstants.TXTLIST.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.TXTLIST);

					ArrayList<String> lst = new ArrayList<String>();
					int size = this.getTxtList().size();
					for (int i = 0; i < size; i++) {
						lst.add(this.getTxtList().get(i));
					}

					size = objVariable.getTxtList().size();
					for (int i = 0; i < size; i++) {
						lst.add(objVariable.getTxtList().get(i));
					}

					retVal.setTxtList(lst);
				} else if (EugeneConstants.TXT.equals(objVariable.getType())) {

					retVal = new PropertyValue(null, EugeneConstants.TXTLIST);

					ArrayList<String> lst = new ArrayList<String>();
					int size = this.getTxtList().size();
					for (int i = 0; i < size; i++) {
						lst.add(this.getTxtList().get(i));
					}

					lst.add(objVariable.getTxt());

					retVal.setTxtList(lst);
				}
			} else if (EugeneConstants.NUM.equals(this.getType())) {
				// <NUM> + <NUM>
				if (EugeneConstants.NUM.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.NUM);
					retVal.setNum(this.getNum() + objVariable.getNum());

					// <NUM> + <NUMLIST>
				} else if (EugeneConstants.NUMLIST
						.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.NUMLIST);

					ArrayList<Double> lst = new ArrayList<Double>();

					lst.add(this.getNum());

					int size = objVariable.getNumList().size();
					for (int i = 0; i < size; i++) {
						lst.add(objVariable.getNumList().get(i));
					}

					retVal.setNumList(lst);

					// <NUM> + <TXT> -> <TXT>
				} else if (EugeneConstants.TXT.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.TXT);
					try {
						retVal.setTxt((long)this.getNum() + 
								objVariable.getTxt());
					} catch(Exception e) {
						throw new EugeneException(
								"I cannot concatenate "+this.getNum()+" and "+objVariable.getValue()+"!");
					}
				}
			} else if (EugeneConstants.NUMLIST.equals(this.getType())) {
				if (EugeneConstants.NUMLIST.equals(objVariable.getType())) {
					retVal = new PropertyValue(null, EugeneConstants.NUMLIST);

					ArrayList<Double> lst = new ArrayList<Double>();
					int size = this.getNumList().size();
					for (int i = 0; i < size; i++) {
						lst.add(this.getNumList().get(i));
					}

					size = objVariable.getNumList().size();
					for (int i = 0; i < size; i++) {
						lst.add(objVariable.getNumList().get(i));
					}

					retVal.setNumList(lst);
				} else if (EugeneConstants.NUM.equals(objVariable.getType())) {

					retVal = new PropertyValue(null, EugeneConstants.NUMLIST);

					ArrayList<Double> lst = new ArrayList<Double>();
					int size = this.getNumList().size();
					for (int i = 0; i < size; i++) {
						lst.add(this.getNumList().get(i));
					}
					lst.add(objVariable.getNum());

					retVal.setNumList(lst);
				}
			}
		}
		return retVal;
	}
	
	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		throw new EugeneException("Property values do not support the DOT ('.') notation! (."+name+")");
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		return this.get(idx);
	}

	@Override
	public int hashCode() {
		int hash = this.getName().hashCode();
		hash += this.getType().hashCode();
		hash += this.getValue().hashCode();
		return hash;
	}
}
