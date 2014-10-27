package org.cidarlab.eugene.dom.imp.functions;

import org.antlr.runtime.Token;

import java.util.List;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.imp.StackElement;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.SymbolTable;

import com.rits.cloning.Cloner;

public class Function 
		extends StackElement {

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
	
	// in addition, a function has local symbol tables
	private SymbolTable local_symbols;
	
	// since parameters are passed by-value in Eugene, we also 
	// create an instance of the Cloner
	private Cloner cloner;
	
	public Function(
			String return_type, String name, List<NamedElement> parameters, Token statements, 
			SymbolTable global_symbols) {
		super(name);
		
		this.return_type = return_type;
		this.parameters = parameters;
		this.statements = statements;
		
		this.global_symbols = global_symbols;
		
		// the cloner
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
	public void initialize(List<NamedElement> parameter_values)
			throws EugeneException {
		
		int i = 0;
		
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
			this.put(lv);
		}
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
	public NamedElement get(String name) {
		
		initLocalSymbolTable();
		
		NamedElement ne = this.local_symbols.get(name);
		if(null == ne) {
			ne = this.global_symbols.get(name);
		}
		return ne;
	}

	private void initLocalSymbolTable() {
		if(null == this.local_symbols) {
			this.local_symbols = new SymbolTable();
		}
	}
	
	@Override
	public boolean contains(String name) {
		
		initLocalSymbolTable();
		
		return this.local_symbols.contains(name);
	}

	@Override
	public void put(NamedElement ne) 
			throws EugeneException {

		if(null != ne && !this.contains(ne.getName())) {
			this.local_symbols.put(ne);
		}
	}

	@Override
	public void clear() {
		if(null != this.local_symbols) {
			this.local_symbols.clear();
			this.local_symbols = null;
		}
	}
}
