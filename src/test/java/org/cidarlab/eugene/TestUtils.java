package org.cidarlab.eugene;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.exception.EugeneException;

import java.util.List;

public class TestUtils {

	public static StringBuilder toTestString(Device d) 
			throws EugeneException {
		StringBuilder sb = new StringBuilder();
		sb.append(d.getName()).append("(");
		
		int i = 0;
		for(List<NamedElement> loe : d.getComponents()) {

			List<Orientation> loo = null;
			try {
				loo = d.getOrientations(i);
			} catch(EugeneException e) {
				throw new EugeneException(e.getMessage());
			}
			
			if(loo.size() != loe.size()) {
				throw new EugeneException("Invalid number of orientations!");
			}
			
			sb.append("[");
			int k = 0;
			for(NamedElement e:loe) {
				
				if(loo.get(k) == Orientation.FORWARD) {
					sb.append("+");
				} else if(loo.get(k) == Orientation.REVERSE) {
					sb.append("-");
				}
				
				sb.append(e.getName());
				if(++k < loe.size()) {
					sb.append("|");
				}
			}
			
			sb.append("]");
			
			if((++i) < d.getComponents().size()) {
				sb.append(",");
			}
			
		}
		
		sb.append(")");
		
		return sb;
	}
}
