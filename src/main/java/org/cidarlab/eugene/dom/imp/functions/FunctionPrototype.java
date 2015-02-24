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

package org.cidarlab.eugene.dom.imp.functions;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

import java.util.List;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.imp.ImperativeFeature;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;

import com.rits.cloning.Cloner;

/**
 * The FunctionPrototype class represents a Eugene function definition.
 * It holds the name of the function, its return type, list of parameters, 
 * a reference to the global symbol tables, as well as the tokens and 
 * the tokenstream the function was defined (due to include statements).
 * 
 * Hence, in the Eugene symbol table, we only store the the Function prototype 
 * and if a function gets invoked, then we instantiate the function 
 * prototype (see the FunctionExecution class for more details).
 * 
 * @author Ernst Oberortner
 */
public class FunctionPrototype 
		extends NamedElement {

	private static final long serialVersionUID = 2662476634283408818L;

	// the return type of the function
	private String return_type;
	
	// the parameter list of the function
	private List<NamedElement> parameters;
	
	// the statements of the functions
	private Token statements;
	
	// the stream the function was read from
	// (because of include statements)
	private TokenStream stream;
	
	// also, a function has a reference to the global symbol tables
	private SymbolTable global_symbols;
	
	// also we have a cloner object which eases the passing 
	// of the parameter values to the function instance
	private Cloner cloner;
	
	public FunctionPrototype(
			String return_type, String name, List<NamedElement> parameters, Token statements,
			TokenStream stream,
			SymbolTable global_symbols) {
		super(name);
		
		this.return_type = return_type;
		this.parameters = parameters;
		this.statements = statements;
		this.stream = stream;
		
		this.global_symbols = global_symbols;
		
		this.cloner = new Cloner();
	}
	
	/*---------
	 * GETTERS
	 *---------*/
	public String getReturnType() {
		return this.return_type;
	}
	
	public List<NamedElement> getParameters() {
		return this.parameters;
	}
	
	public Token getStatements() {
		return this.statements;
	}
	
	public TokenStream getTokenStream() {
		return this.stream;
	}
	
	/**
	 * the initialize/1 method initializes the function parameters with 
	 * the parameter values specified in the function call. In this function,
	 * we do NOT check the parameter values against the parameters' types.
	 * This should have been performed earlier.
	 * 
	 * @param parameter_values  ... a list of parameter values
	 * 
	 * @throws EugeneException
	 */
	public FunctionInstance instantiate(List<NamedElement> parameter_values)
			throws EugeneException {
		
		int i = 0;
		
		// here, we instance the function prototype. 
		// we give it a name (following the convention <prototype-name>_<current-time-millis>)
		// and provide a reference to the instance's prototype, i.e. this.
		FunctionInstance fi = new FunctionInstance(
				this.getName()+"_"+System.nanoTime(), 
				this);
		
		// iff there are parameter values specified, then
		// we deep clone them (pass by value) and 
		// put them into the instance's local symbol tables
		if(null != parameter_values) {
			
			// we iterate over the parameter values
			for(NamedElement pv : parameter_values) {
				
				NamedElement p = this.getParameters().get(i++);
				
				// function calls in Eugene are done by-value.
				// therefore we create a local variable lv 
				// that is a deep clone of the parameter value
				NamedElement lv = cloner.deepClone(pv);
				
				// and assign it the name of the corresponding 
				// parameter
				lv.setName(p.getName());
				
				// finally, we store the parameter value with the 
				// name of the parameter into the symbol tables
				fi.put(lv);
			}
		}
		
		// lastly, we return the function instance
		return fi;
	}

	/**
	 * The get/1 method returns the NamedElement object
	 * 
	 * @param name
	 * @return
	 */
	public NamedElement get(String name) {
		return this.global_symbols.get(name);
	}

	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		throw new UnsupportedOperationException("Function.getElement(String) is a unsupported operation!");
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new UnsupportedOperationException("Function.getElement(int) is a unsupported operation!");
	}
	
	/*------------------
	 * FOR TESTING ONLY
	 *------------------*/
	public NamedElement simulateReturnValue() {
		if(EugeneConstants.NUM.equals(this.getReturnType()) || 
				EugeneConstants.TXT.equals(this.getReturnType())) {
			return new Variable(this.getName(), this.getReturnType());
		}
		return null;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getReturnType()).append(" ")
			.append(this.getName()).append("(")
			.append(this.getParameters()).append(")");
		return sb.toString();		
	}
}
