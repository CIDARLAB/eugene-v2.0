package org.cidarlab.eugene.dom.imp.functions;

import org.antlr.runtime.Token;

import java.util.List;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;

public class Function 
		extends NamedElement {

	private static final long serialVersionUID = 2662476634283408818L;

	// los ... List of Statements
	private Token los;
	
	// variable to keep track of the 
	// position in the Eugene script where the
	// function call takes place
	private int callPosition;
	
	// the return type of the function
	private String return_type;
	
	// the parameter list of the function
	private List<NamedElement> parameters;
	
	// the statements of the functions
	private Token statements;
	
	// also, a function has a reference to the global symbol tables
	private SymbolTable global_symbols;
	
	public Function(
			String return_type, String name, List<NamedElement> parameters, Token statements, 
			SymbolTable global_symbols) {
		super(name);
		
		this.return_type = return_type;
		this.parameters = parameters;
		this.statements = statements;
		
		this.global_symbols = global_symbols;
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
}
