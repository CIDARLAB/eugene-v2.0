
package org.cidarlab.eugene.dom;

import java.util.*;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class Variable 
		extends NamedElement {

	private static final long serialVersionUID = 7419972196934330805L;
	
	public String type;
	public boolean bool;
    public String txt;
	public double num;
	public ArrayList<Double> numList;
	public ArrayList<String> txtList;
    public int index;

	public Variable() {
		super("");
//		this.internal_name = "";
        type = "";
		txt = "";
		num = 0.0;
		txtList = new ArrayList<String>();
		numList = new ArrayList<Double>();
        index = -1;
	}

	public Variable(String n, String t) {
		super(n);
		
//		this.internal_name = n;
		type = t;
		txt = "";
		num = 0.0;
		txtList = new ArrayList<String>();
		numList = new ArrayList<Double>();
        index = -1;
	}

	public void printNumList() {
		for(int i=0 ; i < numList.size(); i++) {
			System.out.print(numList.get(i) + " ");
		}
		System.out.println();
	}

	public void printTextList() {
		for(int i=0 ; i < txtList.size(); i++) {
			System.out.print(txtList.get(i) + " ");
		}
		System.out.println();
	}
	
	public void copytxtList(ArrayList<String> txtL) {
		txtList.clear();
		for(int i = 0; i < txtL.size(); i++) {
			txtList.add(txtL.get(i));
		}
	}

	public void copynumList(ArrayList<Double> numL) {
		numList.clear();
		for(int i = 0; i < numL.size(); i++) {
			numList.add(numL.get(i));
		}
	}

	public Object getValue() {
		if (type.equals("txt")) {
			return txt;
		} else if (type.equals("num")) {
			return num;
		} else if (type.equals("txtList")) {
			return txtList;
		} else if (type.equals("numList")) {
			return numList;
		} else if (type.equals("bool")) {
			return bool;
		}
		return null;
	}

    public String getType() {
        if (type.equals("numLst") || type.equals("txtLst")) {
            return type.substring(0, 3) + "[]";
        } else {
            return type;
        }
    }
    
    public String getTxt() {
    	return this.txt;
    }

    public List<String> getTxtList() {
    	return this.txtList;
    }
    
    public List<Double> getNumList() {
    	return this.numList;
    }

    public double getNum() {
    	return this.num;
    }
    
    public boolean getBool() {
    	return this.bool;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
//    	sb.append(this.getType()).append(" ").append(this.getName()).append(" := ");
        if (EugeneConstants.TXT.equals(this.getType())) {
//        	if(!txt.startsWith("\"") && !txt.endsWith("\"")) {
//        		sb.append("\"").append(this.getTxt()).append("\"");
//        	} else {
        		sb.append(this.getTxt());
//        	}
		} else if (EugeneConstants.NUM.equals(this.getType())) {
			
    		if(this.getNum() % 1 == 0) {
    			sb.append((int)this.getNum());
    		} else {
    			sb.append(this.getNum());
    		}

		} else if (EugeneConstants.TXTLIST.equals(this.getType())) {
			sb.append("[");
            for (int i = 0; i < this.getTxtList().size(); i++) {
            	if(null != this.getTxtList().get(i)) {
	            	if(!this.getTxtList().get(i).startsWith("\"") && !this.getTxtList().get(i).endsWith("\"")) {
	            		sb.append("\"").append(this.getTxtList().get(i)).append("\"");
	            	} else {
	            		sb.append(this.getTxtList().get(i));
	            	}
            	}
            	if(i < this.getTxtList().size() - 1) {
            		sb.append(", "); 
            	}
            }
            sb.append("]");
		} else if (EugeneConstants.NUMLIST.equals(this.getType())) {
			sb.append("[");
            for (int i = 0; i < this.getNumList().size(); i++) {
            	if(null != this.getNumList().get(i)) {
            		if((this.getNumList().get(i)).doubleValue() % 1 == 0) {
            			sb.append((this.getNumList().get(i)).intValue());
            		} else {
            			sb.append(this.getNumList().get(i));
            		}
            	}
            	if(i < this.getNumList().size() - 1) {
            		sb.append(", "); 
            	}
            }
            sb.append("]");
		} else if (EugeneConstants.BOOLEAN.equals(this.getType())) {
			if (this.getBool()) {
                sb.append("true");
            } else {
                sb.append("false");
            }
		}
        //sb.append(";");
        return sb.toString();
    }

	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		throw new EugeneException("Variables do not support accessing sub-elements using the DOT ('.') notation!");
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		if(EugeneConstants.TXTLIST.equals(this.getType())) {
			if(idx < 0 || idx > this.getTxtList().size() - 1) {
				throw new EugeneException("The index " + idx +" is out of bounds!");
			}
			Variable v = new Variable(null, EugeneConstants.TXT);
			v.txt = this.getTxtList().get(idx);
			return v;
		} else if(EugeneConstants.NUMLIST.equals(this.getType())) {
			if(idx < 0 || idx > this.getNumList().size() - 1) {
				throw new EugeneException("The index " + idx +" is out of bounds!");
			}
			Variable v = new Variable(null, EugeneConstants.NUM);
			v.num = this.getNumList().get(idx);
			return v;
		} else if(EugeneConstants.TXT.equals(this.getType())) {
			if(idx < 0 || idx > this.getTxt().length() - 1) {
				throw new EugeneException("The index " + idx +" is out of bounds!");
			}
			Variable v = new Variable(null, EugeneConstants.TXT);
			v.txt = String.valueOf(this.getTxt().charAt(idx));
			return v;
		}
			
		throw new EugeneException("Variables of type "+this.getType()+" do not support array access!");
	}
	
	public void setElement(int idx, Variable v) 
			throws EugeneException {
		
		if(EugeneConstants.TXTLIST.equals(this.getType()) && EugeneConstants.TXT.equals(v.getType())) {
			if(idx < 0 || idx > this.getTxtList().size()) {
				throw new EugeneException("The index " + idx +" is out of bounds!");
			}
			// iff the index is equal to the current size, then
			// we add the new variable to the current txtlist
			if(idx == this.getTxtList().size()) {
				this.getTxtList().add(v.getTxt());
			} else {
				this.getTxtList().set(idx, v.getTxt());
			}
		} else if(EugeneConstants.NUMLIST.equals(this.getType()) && EugeneConstants.NUM.equals(v.getType())) {
			
			if(idx < 0 || idx > this.getNumList().size()) {
				throw new EugeneException("The index " + idx +" is out of bounds!");
			}
			
			// iff the index is equal to the current size, then
			// we add the new variable to the current numlist
			if(idx == this.getNumList().size()) {
				this.getNumList().add(v.getNum());
			} else {
				this.getNumList().set(idx, v.getNum());
			}
			
		} else if(EugeneConstants.TXT.equals(this.getType()) && EugeneConstants.TXT.equals(v.getType())) {
			
			if(idx < 0 || idx > this.getTxt().length()) {
				throw new EugeneException("The index " + idx +" is out of bounds!");
			}
			
			
			StringBuilder sb = new StringBuilder();
			
			// iff the index is equals the current size, 
			// then we place the new string to the end of the current string
			if(idx == this.getTxt().length()) {
				sb.append(this.getTxt());
				sb.append(v.getTxt());
			} else {
				sb.append(this.getTxt().substring(0, idx));
				sb.append(v.getTxt());
				sb.append(this.getTxt().substring(idx + 1));
			}
			this.txt = sb.toString();
			
		} else {
			throw new EugeneException("Variables of type "+this.getType()+" do not support array access!");
		}
	}

}