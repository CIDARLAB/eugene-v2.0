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
