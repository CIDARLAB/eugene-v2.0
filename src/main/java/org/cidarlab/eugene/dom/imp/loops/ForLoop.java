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

package org.cidarlab.eugene.dom.imp.loops;

import org.antlr.runtime.Token;
import org.cidarlab.eugene.dom.imp.ImperativeFeature;
import org.cidarlab.eugene.exception.EugeneException;


/**
 * 
 * @author Ernst Oberortner
 */
public class ForLoop 
	extends ImperativeFeature {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3582581391763053580L;
	private Token lstStatements;
	private int nOldPosition;

	/*
	 * the name of the iteration variable
	 */
	private String varname;
	
	public ForLoop() {
		super(null);
		this.lstStatements = null;
		nOldPosition = 0;
		this.varname = null;
	}
	
	public ForLoop(String name) {
		super(name);
		this.lstStatements = null;
		nOldPosition = 0;
		this.varname = null;
	}

	public void setOldPosition(int nOldPosition) {
		this.nOldPosition = nOldPosition;
	}

	public int getOldPosition() {
		return nOldPosition;
	}

	public void setStatements(Token lstStatements) {
		this.lstStatements = lstStatements;
	}

	public Token getStatements() {
		return this.lstStatements;
	}
	
	public void setVarname(String varname) {
		this.varname = varname;
	}
	
	public String getVarname() {
		return this.varname;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("for(");
		if(null != this.getVarname()) {
			sb.append(this.getVarname());
		}
		sb.append(")");
		return sb.toString();
	}


}
