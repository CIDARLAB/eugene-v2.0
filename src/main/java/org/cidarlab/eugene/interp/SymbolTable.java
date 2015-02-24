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

package org.cidarlab.eugene.interp;

import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.imp.functions.FunctionPrototype;
import org.cidarlab.eugene.dom.rules.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * In Eugene, we utilize the symbol tables to only store the followings:
 * - Property
 * - Part Type
 * - Part
 * - Device Specifications
 * - Rule Specifications
 * 
 * @author Ernst Oberortner
 *
 */
public class SymbolTable {

	/*
	 * the symbols map stores all symbols defined in a Eugene script
	 * 
	 * key   ... the name of the symbol
	 * value ... the symbol's object
	 * 
	 * Example:
	 * Part p;
	 * 
	 * key   ... p
	 * value ... the Part object
	 */
	private Map<String, NamedElement> symbols;
	
	/*
	 * also, we add an additional map to store for 
	 * each part type a list of defined parts
	 * 
	 * key   ... the name of the part type
	 * value ... the part object
	 */
	private Map<ComponentType, Set<Component>> type_components;
	
	/*
	 * also, we hold references to all defined functions
	 */
	private Map<String, FunctionPrototype> functions;
	
	public Map<String, Variable> variables;

	public SymbolTable() {
		this.symbols = new HashMap<String, NamedElement>();
		this.type_components = new HashMap<ComponentType, Set<Component>>();
		this.variables = new HashMap<String, Variable>();
		this.functions = new HashMap<String, FunctionPrototype>();
	}
	
	/**
	 * The put/1 method stores the provided symbol (i.e. a NamedElement object) 
	 * into the symbol tables.
	 * 
	 * @param symbol ... the symbol to be stored in the symbol tables
	 */
	public void put(NamedElement symbol) {
		if(null != symbol) {
			this.put(symbol.getName(), symbol);
		}
	}
	
	/**
	 * The put/2 method stores the provided symbol (i.e. a NamedElement object) 
	 * into the symbol tables using the provided name.
	 * 
	 * @param symbol ... the symbol to be stored in the symbol tables
	 */
	public void put(String name, NamedElement symbol) {
		if(null != symbol) {
			/*
			 * if the symbol is a variable, then
			 * we put it into the variables hashmap
			 */
			if(symbol instanceof Variable) {
				this.variables.put(name, (Variable)symbol);
				return;
			}
			
			/*
			 * we put the symbol into the symbols table
			 */
			this.symbols.put(name, symbol);
			
			if(symbol instanceof Component) {

				/*
				 * since Eugene offers data exchange facilities and 
				 * we do not insert the data directly from those exchange classes, 
				 * we also need to check if the component type has been declared
				 */
				if(null != ((Component) symbol).getType()) {
					if(!this.symbols.containsKey(((Component) symbol).getType().getName())) {
						this.symbols.put(((Component) symbol).getType().getName(), ((Component) symbol).getType());
					}
				}
				
				/*
				 * if the symbol is a typed symbol,
				 */
				if(!this.type_components.containsKey(((Component)symbol).getType())) {
					this.type_components.put(((Component)symbol).getType(), new HashSet<Component>());
				}
				
				/*
				 * then we also store the information in the typed_components hashmap
				 */
				this.type_components.get(((Component)symbol).getType()).add((Component)symbol);				
			}
		}
	}

	
	/**
	 * the get/1 method returns the NamedElement object 
	 * corresponding to the provided name
	 * 
	 * @param name  ... the name of the symbol
	 * @return      ... the NamedElement object
	 */
	public NamedElement get(String name) {
		if(this.variables.containsKey(name)) {
			return this.variables.get(name);
		}
		return this.symbols.get(name);
	}
	
	/**
	 * the contains/1 method evaluates if the symbols table 
	 * contains a NamedElement object of the given name
	 * 
	 * @param name  ... the name of the symbol
	 * @return      ... true, if the symbols table contains the name
	 *                  false, otherwise
	 */
	public boolean contains(String name) {
		return this.symbols.containsKey(name) || this.variables.containsKey(name);
	}
	
	/**
	 * The contains/1 method evaluates if the given NamedElement 
	 * object is contained in the symbols table
	 * 
	 * @param symbol  ... the NamedElement object
	 * @return        ... true, if the NamedElement object is in the symbols table
	 *                    false, otherwise
	 */
	public boolean contains(NamedElement symbol) {
		if(null != symbol) {
			return this.symbols.containsValue(symbol) || this.symbols.containsKey(symbol.getName());
		}
		return false;
	}
	
	/**
	 * the getParts/1 method returns the list of parts 
	 * of the given part type
	 * 
	 * @param pt ... the PartType object
	 * 
	 * @return a list of Part objects representing the parts of the given part type
	 */
	public Set<Part> getParts(PartType pt) {
		/*
		 * here, we iterate over the typed_components map
		 */
		for(ComponentType ct : this.type_components.keySet()) {
			if(ct instanceof PartType && ct.getName().equals(pt.getName())) {
				Set<Part> parts = new HashSet<Part>();
				// we also need to convert the Set<Component> to a Set<Part>
				for(Component c : this.type_components.get(ct)) {
					if(c instanceof Part) {
						parts.add((Part)c);
					}
				}
				return parts;
			}
		}
		return null;
	}

	/**
	 * The getComponents/1 method returns the set of instances 
	 * of the given component type
	 * 
	 * @param ct ... the type of the wanted components
	 * 
	 * @return
	 */
	public Set<Component> getComponents(ComponentType ct) {
		return this.type_components.get(ct);
	}

	/**
	 * the getRules/1 method returns all rules that are 
	 * specified on the given device.
	 * i.e. a list of rules that have an ON clause for the given device
	 * as well as all rules that do NOT have an ON clause
	 * 
	 * @param d  ... the device
	 * @return   ... the list of device-specific rules
	 */
	public List<Rule> getRules(Device d) {
		List<Rule> lst = new ArrayList<Rule>();
		for(NamedElement ne : this.symbols.values()) {
			if(ne instanceof Rule) {
				// if the rule has NO ON clause
				if(null == ((Rule)ne).getDevice() ||
						// or the rule is defined on the given device
						(((Rule)ne).getDevice().getName().equals(d.getName()))) {
					// then we add the rule to the list of device-specific rules
					lst.add((Rule)ne);
				}
			}
		}
		return lst;
	}
	
	public void print() {
		for(String name : this.symbols.keySet()) {
			System.out.println(name+" -> "+this.symbols.get(name));
		}
	}

	/**
	 * The clear method clears all data structures
	 */
	public void clear() {
		if(null != this.type_components) {
			this.type_components.clear();
		}
		
		if(null != this.symbols) {
			this.symbols.clear();
		}
		
		if(null != this.variables) {
			this.variables.clear();
		}
	}
	
	/**
	 * the removeVariable/1 method removes the variable with the 
	 * given varname from the symbol tables.
	 * this method is of particular importance for the FOR loops 
	 * of Eugene since after the execution of a FOR loop, the 
	 * FOR loop's iteration variable needs to be deleted. 
	 *  
	 * @param varname  ... the name of the FOR loops iteration variable
	 */
	public void removeVariable(String varname) {
		if(null != this.variables && this.variables.containsKey(varname)) {
			this.variables.remove(varname);
		}
	}
	
	/**
	 * The getAll/0 method returns all symbols contained 
	 * in the symbol tables (including Variables).
	 * 
	 * @return a collection of all elements
	 */
	public Collection<NamedElement> getAll() {
		Collection<NamedElement> col = new HashSet<NamedElement>();
		col.addAll(this.symbols.values());
		col.addAll(this.variables.values());
		return col;
	}
	
	/**
	 * The getFunction/1 method returns the Function object 
	 * of the given name.
	 * 
	 * @param name  ... the name of the desired function
	 * 
	 * @return
	 */
	public FunctionPrototype getFunction(String name) {
		return this.functions.get(name);
	}
	
	/**
	 * The putFunction/1 method stores a given Function object 
	 * in the functions hashmap
	 * 
	 * @param f ... the Function object
	 * 
	 */
	public void putFunction(FunctionPrototype f) {
		this.functions.put(f.getName(), f);
	}
	
	/**
	 * The contains/1 method checks if a Function with a 
	 * given name has been defined. It's being used for 
	 * two resons in the Interp:
	 * 1. for function calls and 2. for function definitions
	 * 
	 * Currently, a function name must be unique in the Eugene script.
	 * Future improvements can, for example, also evaluate the 
	 * number and types of parameters.
	 * 
	 * @param name ... the name of the defined function
	 * 
	 * @return  ... true ... if the function has been defined
	 *              false .. otherwise
	 */
	public boolean containsFunction(String name) {
		return this.functions.containsKey(name);
	}
	
	/**
	 * The getFunctions/0 method returns all defined function 
	 * prototypes in a Eugene script (including the ones 
	 * defined in an included file (include)).
	 * 
	 * @return ... a collection of FunctionPrototypes objects
	 *             representing all defined functions
	 */
	public Collection<FunctionPrototype> getFunctions() {
		return this.functions.values();
	}
}
