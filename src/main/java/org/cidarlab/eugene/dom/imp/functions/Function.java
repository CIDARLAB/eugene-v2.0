package org.cidarlab.eugene.dom.imp.functions;

import org.antlr.runtime.Token;
import java.util.List;

import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.exception.EugeneException;

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
	
	public Function(String return_type, String name, List<NamedElement> parameters, Token statements) {
		super(name);
		
		this.return_type = return_type;
		this.parameters = parameters;
		this.statements = statements;
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

}
