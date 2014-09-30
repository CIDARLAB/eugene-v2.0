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

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;


public class Property 
	extends NamedElement {

	private static final long serialVersionUID = -608427454031681028L;
	protected String type;

	public Property(String name, String type) {
		super(name);
		this.type = type;
	}

	public Property(String name) {
		super(name);
		this.type = null;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Property ").append(this.getName()).append("(")
				.append(this.type).append(")");
		return sb.toString();
	}

	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		throw new EugeneException("Properties do not support the DOT ('.') notation! (."+name+")");
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new EugeneException("Properties do not support the array access! ('["+idx+"]')");
	}
}