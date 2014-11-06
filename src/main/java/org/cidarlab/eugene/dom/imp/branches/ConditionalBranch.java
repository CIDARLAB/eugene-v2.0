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

package org.cidarlab.eugene.dom.imp.branches;

import java.util.UUID;

import org.cidarlab.eugene.dom.imp.ImperativeFeature;


public class ConditionalBranch 
	extends ImperativeFeature {

	private static final long serialVersionUID = 1L;
	
//	private Token condition;
//	private Token statements;

	public ConditionalBranch() {
		super(UUID.randomUUID().toString());
	}
	
//	@Override
//	public boolean contains(String name) {
//		return this.getSymbols().contains(symbol)
//	}

//	public ConditionalBranch(Token condition, Token statements) {
//		super(null);
//		this.condition = condition;
//		this.statements = statements;
//	}
//
//	public Token getCondition() {
//		return this.condition;
//	}
//
//	public Token getStatements() {
//		return this.statements;
//	}

	@Override
	public void clear() {
		this.getSymbols().clear();
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
}
