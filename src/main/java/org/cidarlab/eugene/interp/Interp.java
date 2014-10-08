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

package org.cidarlab.eugene.interp;

import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.data.pigeon.Pigeonizer;
import org.cidarlab.eugene.data.sbol.SBOLExporter;
import org.cidarlab.eugene.data.sbol.SBOLImporter;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.ComponentType;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.ImperativeFeature;
import org.cidarlab.eugene.dom.imp.StackElement;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.eugene.dom.imp.loops.ForLoop;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.ArrangementConstraint;
import org.cidarlab.eugene.dom.rules.ArrangementOperand;
import org.cidarlab.eugene.dom.rules.LogicalNot;
import org.cidarlab.eugene.dom.rules.LogicalOr;
import org.cidarlab.eugene.dom.rules.Predicate;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.dom.rules.exp.ExpressionConstraint;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.minieugene.MiniEugeneAdapter;
import org.cidarlab.eugene.parser.EugeneLexer;
import org.cidarlab.eugene.parser.EugeneParser;
import org.cidarlab.eugene.sparrow.SparrowAdapter;
import org.cidarlab.eugene.util.EugeneUtil;
import org.cidarlab.minieugene.data.pigeon.WeyekinPoster;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.DOMException;
import org.cidarlab.sparrow.exception.SparrowException;

import com.rits.cloning.Cloner;

public class Interp {

	private Sparrow sparrow;
	private SymbolTable symbols;
	
	private MiniEugeneAdapter meAdapter;
	private SparrowAdapter spAdapter;
	
	private Pigeonizer pigeon;
	
	private Cloner cloner;
	
	/*
	 * STACK for scoping
	 */
	private Stack<StackElement> stack;

	/*
	 * the writer for writing outputs
	 */
    private BufferedWriter writer = null;

	
	public Interp(Sparrow sparrow, BufferedWriter writer) {

		/*
		 * FACTS/RULES
		 */
		try {
			this.sparrow = sparrow;
		} catch(Exception e) {}
		
		
		/*
		 * SYMBOL TABLES
		 */
		this.symbols = new SymbolTable();
		
		/*
		 * STACK (for scoping)
		 */
		this.stack = new Stack<StackElement>();
		
		/*
		 * for cloning
		 * especially for assignmnents
		 */
		this.cloner = new Cloner();
		
		/*
		 * the writer
		 */
		this.writer = writer;
	}

	public void includeFile(String file) 
			throws EugeneException {
		/*
		 * first, we remove the double-quotas
		 */
		if(null == file || file.length() <= 2) {
			throw new EugeneException("Invalid file name!");
		}
		file = file.substring(1, file.length() - 1);
		
		String script = null; 
		try {
			script = EugeneUtil.readFile(new File(file));
		} catch(IOException ioe) {
			throw new EugeneException("I cannot read the file " + file);
		}
		
		/*
		 * lexical analysis and parsing
		 */
		EugeneLexer lexer = new EugeneLexer(new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		EugeneParser parser = new EugeneParser(tokens);

		/*
		 * initialize the with this interpreter
		 */
		parser.init(this, this.getWriter());	
		parser.setFilename(file);
		
		try {
			parser.prog();
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	private BufferedWriter getWriter() {
		return this.writer;
	}
	
	public NamedElement importFile(String file) 
			throws EugeneException {
		/*
		 * first, we remove the double-quotas
		 */
		if(null == file || file.length() <= 2) {
			throw new EugeneException("Invalid file name!");
		}
		file = file.substring(1, file.length() - 1);
		
		String script = null; 
		try {
			script = EugeneUtil.readFile(new File(file));
		} catch(IOException ioe) {
			throw new EugeneException("I cannot read the file " + file);
		}
		
		/*
		 * lexical analysis and parsing
		 */
		EugeneLexer lexer = new EugeneLexer(new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		EugeneParser parser = new EugeneParser(tokens);

		/*
		 * initialize the with this interpreter
		 */
		try {
			parser.init(
					new Interp(new Sparrow(), this.getWriter()), 
					this.getWriter());
		} catch(SparrowException spe) {
			throw new EugeneException(spe.toString());
		}
		parser.setFilename(file);
		
		try {
			parser.prog();
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
		
		/*
		 * lastly, we create a new EugeneCollection
		 */
		
		EugeneCollection ec = parser.getAllElements();
		this.put(ec);
		return ec;
	}
	
	/*----------------------------------------------------
	 * IMPERATIVE LANGUAGE FEATURES
	 *----------------------------------------------------*/
	public void executeForLoop() {
		
	}

	/**
	 * 
	 * @param sName ... the name of the property
	 * @param sType ... the type of the property
	 * @throws EugeneException
	 */
	public void createProperty(String name, String type)
			throws EugeneException {
		
		if(this.contains(name)) {
			throw new EugeneException("An element named "+name+" exists already!");
		}
		
		this.put(
				new Property(name, type));		
	}
	
	/**
	 * 
	 * @param p           ... the part
	 * @param prop_name   ... the property name
	 * @param value       ... the property value
	 * @return
	 * @throws EugeneException
	 */
	public PropertyValue createPropertyValue(
			Part p, String prop_name, String value) 
				throws EugeneException {
		
		/*
		 * first, let's get the property from the part's part type
		 */
		PartType pt = (PartType)p.getType();

		Property prop = pt.getProperty(prop_name);
		
		if(null == prop) {
			throw new EugeneException("The part type "+pt+" does not contain a property named "+prop_name);
		}
		
		return this.createPropertyValue(
				new PropertyValue(prop_name, prop.getType()), value);		
	}
	
	
	public PropertyValue createPropertyValue(Property property, String value) 
				throws EugeneException {
		
		PropertyValue pv = new PropertyValue(property);
		
		if(SparrowConstants.TXT.equalsIgnoreCase(property.getType())) {
			pv.setTxt(value);
		} else if(SparrowConstants.NUM.equalsIgnoreCase(property.getType())) {
			try {
				pv.setNum(Double.parseDouble(value));
			} catch(Exception e) {
				throw new EugeneException("Invalid NUM value for property value "+property.getName());
			}
		} else {
			throw new EugeneException(property.getType()+" is an invalid type!");
		}
		
		return pv;
	}

	/**
	 * The createType/2 method gets invoked if the Eugene user 
	 * defines a new Type. 
	 *  
	 * @param name ... the name of the type
	 * @param elements ... a list of elements (usually the type's properties)
	 *
	 * @throws EugeneException
	 */
	public void createType(String name, List<NamedElement> elements) 
			throws EugeneException {
		
		if(this.contains(name)) {
			throw new EugeneException("An element named "+name+" exists already!");
		}
		
		ComponentType ct = new ComponentType(name);
		
		if(null != elements) {
			for(NamedElement ne : elements) {
				
				if(!(ne instanceof Property)) {
					throw new EugeneException(ne.getName()+" is not a Property!");
				}
				
				ct.getProperties().add((Property)ne);
			}
		}
		
		
		// finally, store the part type object in the symbol tables
		this.put(ct);
	}

	/**
	 * 
	 * @param name     ... the name of the part type
	 * @param elements ... a list of properties 
	 * @throws EugeneException
	 */
	public void createPartType(String name, List<NamedElement> elements) 
			throws EugeneException {
		
		if(this.contains(name)) {
			throw new EugeneException("An element named "+name+" exists already!");
		}
		
		PartType pt = new PartType(name);
		
		if(null != elements) {
			for(NamedElement ne : elements) {
				
				if(!(ne instanceof Property)) {
					throw new EugeneException(ne.getName()+" is not a Property!");
				}
				
				pt.getProperties().add((Property)ne);
			}
		}
		
		
		// finally, store the part type object in the symbol tables
		this.put(pt);
	}
	
	/**
	 * The instantiate/4 method creates an object of the given component type, assigns it 
	 * the provided name, properties, and property values.
	 * 
	 * The instantiate/4 method is the only interface to the outside of Eugene for 
	 * the instantiation of component types. Hence, the instantiate/4 method is a 
	 * dispatcher for instantiating the corresponding object of the provide component type.
	 * For example, if the component type is a part type, then the instantiate/4 method 
	 * invokes the private createPart/4 method and returns the instantiated part object.
	 * 
	 * @param type  ... the type of the instantiated component
	 * @param name  ... the name of the instance
	 * @param properties ... a list of properties (which is set if the user follows the DOT notation)
	 * @param values ... a list of property values
	 * 
	 * @return the instantiated component
	 * 
	 * @throws EugeneException
	 */
	public Component instantiate(ComponentType type, String name, List<String> properties, List<Variable> values) 
			throws EugeneException {
		
		/*
		 * error checking
		 */
		if(this.contains(name)) {
			throw new EugeneException("An element named "+name+" exists already!");
		}
	
		if(type instanceof PartType) {			
			return this.createPart((PartType)type, name, properties, values);
		}
		
		return this.createComponent(type, name, properties, values);
	}
	
	/**
	 * The createPart/4 method creates a Part object of the given PartType, and 
	 * with the provided name, properties, and values.
	 * 
	 * This method is private since it is invoked by the instantiate/4 method, 
	 * which is the only interface for the instantiation facilities of Eugene.
	 * 
	 * @param pt     ... the part type
	 * @param name   ... the name of the part
	 * @param values ... a list of property values
	 * @return
	 * @throws EugeneException
	 */
	private Part createPart(PartType pt, String name, List<String> properties, List<Variable> values) 
			throws EugeneException {
		
		// create the part object
		Part p = new Part(pt, name);
		
		// Property values		
		if(properties != null && !properties.isEmpty()) {
			// DOT ('.') NOTATION
			int i = 0;
			for(String property : properties) {

				if(null == pt.getProperty(property)) {
					throw new EugeneException("Parts of part type "+pt.getName()+" do not have "+property+" property.");
				}
				
				try {
					p.setPropertyValue(pt.getProperty(property), values.get(i));
				} catch(DOMException de) {
					throw new EugeneException(de.getMessage());
				}
				i++;
			}
		} else {
			if(null!=values && !values.isEmpty()) {
				
				int i=0;
				for(Variable value : values) {

					// type checking
					// could, however, be done earlier too.
					if(!pt.getProperties().get(i).getType().equals(value.getType())) {
						throw new EugeneException(
								"Invalid type "+value.getType()+"! "+
								"Excpecting value of type "+pt.getProperties().get(i).getType()+
								" for property "+pt.getProperties().get(i).getName()+".");
					}
					
					try {
						p.setPropertyValue(pt.getProperties().get(i++), value);
					} catch(Exception e) {
						throw new EugeneException(e.getMessage());
					}
				}
			}
		}
		
		// we store the resulting Part object
		// into the symbol tables
		this.put(p);

		// return the part object
		return p;
	}
	
	/**
	 * 
	 * @param pt     ... the part type
	 * @param name   ... the name of the part
	 * @param values ... a list of property values
	 * @return
	 * @throws EugeneException
	 */
	private Component createComponent(ComponentType ct, String name, List<String> properties, List<Variable> values) 
			throws EugeneException {
		
		// create the part object
		Component c = new Component(ct, name);
		
		// Property values		
		if(properties != null && !properties.isEmpty()) {
			// DOT ('.') NOTATION
			int i = 0;
			for(String property : properties) {

				if(null == ct.getProperty(property)) {
					throw new EugeneException("The type "+ct.getName()+" does not have a "+property+" property.");
				}
				
				try {
					c.setPropertyValue(ct.getProperty(property), values.get(i));
				} catch(DOMException de) {
					throw new EugeneException(de.getMessage());
				}
				i++;
			}
		} else {
			if(null!=values && !values.isEmpty()) {
				
				int i=0;
				for(Variable value : values) {
					// type checking
					// could, however, be done earlier too.
					if(!ct.getProperties().get(i).getType().equals(value.getType())) {
						throw new EugeneException(
								"Excpecting value of type "+ct.getProperties().get(i).getType()+" for property "+(i+1)+".");
					}
					
					try {
						c.setPropertyValue(ct.getProperties().get(i++), value);
					} catch(Exception e) {
						throw new EugeneException(e.getMessage());
					}
				}
			}
		}
		
		// we store the resulting Part object
		// into the symbol tables
		this.put(c);

		// return the part object
		return c;
	}

	/**
	 * The createDevice method instantiates the Device class and stores the Device object
	 * into the symbol tables.
	 * 
	 * @param name ... the name of the device
	 * @param components ... the device's components
	 * @param orientations ... the orientations of the device's components
	 * @throws EugeneException
	 */
	public void createDevice(String name, List<List<NamedElement>> components, List<List<Orientation>> orientations)
			throws EugeneException {

		if(this.contains(name)) {
			throw new EugeneException("An element named "+name+" exists already.");
		}
		
		/*
		 * devices are only stored in the symbol tables 
		 * since the specify a design.
		 */
		this.put(
				new Device(name, components, orientations));
		
	}
	
	/**
	 * The createInteraction/3 method constructs an Interaction object which two facts of 
	 * a given type. 
	 * Example: TetR REPRESSES pTet
	 * 
	 * @param lhs  ... the left-hand-side (lhs) of the interaction
	 * @param type ... the type of the interaction
	 * @param rhs  ... the right-hand-side (rhs) of the interaction
	 * 
	 * @throws EugeneException
	 */
	public Interaction createInteraction(String name, String lhs, Interaction.InteractionType type, String rhs) 
			throws EugeneException {
		/*
		 * LHS error checking
		 */
		NamedElement lhsElement = this.get(lhs);
		if(null == lhsElement) {
			throw new EugeneException(lhs +" is not defined!");
		} else if(!(lhsElement instanceof Component)) {
			throw new EugeneException(lhsElement +" is not a genetic component!");
		}
		
		/*
		 * RHS error checking
		 */
		NamedElement rhsElement = this.get(rhs);
		if(null == rhsElement) {
			throw new EugeneException(rhs +" is not defined!");
		} else if(!(rhsElement instanceof Component) && !(rhsElement instanceof Interaction)) {
			throw new EugeneException(rhsElement +" is neither a genetic component nor an interaction!");
		}
		
		try {
			Interaction ia = new Interaction(name, (Component)lhsElement, type, rhsElement);
			
			/*
			 * we also store the interaction in the symbol tables
			 */
			this.put(ia);
			
			return ia;
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.toString());
		}
		
	}
	
	/**
	 * The createInteraction/3 method constructs an Interaction object consisting of
	 * one left-hand-side genetic element and one right-hand-side interaction.  
	 * Example: IPTG induces (lacI REPRESSES pLac)
	 * 
	 * @param lhs  ... the left-hand-side (lhs) of the interaction
	 * @param type ... the type of the interaction
	 * @param rhs  ... the right-hand-side (rhs) of the interaction (which is an interaction itself)
	 * 
	 * @throws EugeneException
	 */
	public Interaction createInteraction(String name, String lhs, Interaction.InteractionType type, Interaction rhs) 
			throws EugeneException {
		
		if(!this.contains(rhs.getName())) {
			this.put(rhs);
		}
		
		return this.createInteraction(name, lhs, type, rhs.getName());
	}

	/**
	 * The product/1 method calculates the cartesian product of the given device.
	 * 
	 * @param d ... the device
	 * @throws EugeneException
	 */
	public EugeneCollection product(Device d) 
			throws EugeneException {

		if(null == spAdapter) {
			spAdapter = new SparrowAdapter(this.sparrow);
		}

		// ONLY FOR TESTING PURPOSE
//		this.doQueryTests();
		
		/*
		 * first, we query all rules from the symbol tables
		 * that are defined on the given device.
		 * i.e. rules that have the ON clause for the given device
		 * as well as rules which do NOT have an ON clause 
		 */
		List<Rule> rules = this.symbols.getRules(d);
		
		/*
		 * STEP 1: ``Selection'' of Parts
		 * 
		 * using expression rules which have one constant operand
		 * e.g. promoter.strength == "strong"
		 */
//		System.out.println("*** STEP1: QUERY ***");		
		Set<Component> sop = null;
		if(null != rules && !rules.isEmpty()) {
			/*
			 * if there are rules specified, then 
			 * we use those rules to query all components
			 */
			sop = spAdapter.queryComponents(rules);
		} else {
			/*
			 * if there are no rules specified, then 
			 * we retrieve all facts (i.e. components)
			 * from the working memory
			 */
			try {
				sop = this.sparrow.getFacts();
			} catch(SparrowException spe) {
				spe.printStackTrace();
				throw new EugeneException(spe.toString());
			}
		}
		
		if(sop == null || sop.isEmpty()) {
			throw new EugeneException("There are no components (e.g. parts) specified!");
		}

//		System.out.println("[Interp.product] parts -> "+sop);
//		System.out.println("[Interp.product] parts -> "+sop.size());
//		System.out.println("********************");

		/*
		 * retrieve all interactions
		 */
		Set<Interaction> soi = null;
		try {
			soi = this.sparrow.getInteractions();
		} catch(SparrowException spe) {
			throw new EugeneException(spe.toString());
		}
		
//		System.exit(1);

		
		if(null == meAdapter) {
			meAdapter = new MiniEugeneAdapter(this.symbols);
		}

		/*
		 * STEP 2: ``Architectural'' Constraints
		 * 
		 * here, we use miniEugene
		 * e.g. promoter BEFORE repressor
		 */
		Set<Device> sod = null;
		try {
//			System.out.println("*** STEP2: ARCHITECTURE ***");
			sod = this.meAdapter.product(d, rules, sop, soi);
//			System.out.println("[Interp.product] devices -> "+sod);
//			System.out.println("***************************");
		} catch(Exception ee) {
			ee.printStackTrace();
		}

		

		/*
		 * STEP 3: ``Pruning''
		 *  
		 * then, we execute all expression rules where both operands are not constants
		 * e.g. repressor.represses == promoter.name 
		 */
//		System.out.println("*** STEP3: PRUNE ***");
		sod = this.spAdapter.prune(sod, rules);
//		System.out.println("[Interp.product] devices -> "+sod);
//		System.out.println("********************");
		
		/*
		 * STEP 4: ``Storing''
		 * 
		 * finally, we store all rule-compliant devices into the WM
		 */

//		System.out.println("*** STEP4: STORING ***");
		EugeneCollection ec = new EugeneCollection(null);
		for(Device dev : sod) {
			ec.getElements().add(dev);
		}
		
		this.put(ec);

		this.storeRuleCompliantDevices(sod);
//		System.out.println("**********************");
		
//		this.pigeon(ea.getName());
		
		return ec;
		
//		return devices;
	}
	
//	private void doQueryTests() 
//			throws EugeneException {
//		int inters = -1;
//		int reps = -1;
//		int inds = -1;
//		try {
//			/*
//			 * retrieve all interactions
//			 */
//			Set<Interaction> soi = this.sparrow.getInteractions();
//			inters = soi.size();
//		} catch(SparrowException spe) {
//			throw new EugeneException(spe.toString());
//		}
//		
//
//		try {
//			/*
//			 * retrieve all REPRESSES interactions
//			 */
//			Set<Interaction> soi = this.sparrow.getInteractions(InteractionType.REPRESSES);
//			reps = soi.size();
//		} catch(SparrowException spe) {
//			throw new EugeneException(spe.toString());
//		}
//		
//		try {
//			/*
//			 * retrieve all INDUCES interactions
//			 */
//			Set<Interaction> soi = this.sparrow.getInteractions(InteractionType.INDUCES);
//			inds = soi.size();
//		} catch(SparrowException spe) {
//			throw new EugeneException(spe.toString());
//		}
//		
//		if(inters != reps + inds) {
//			throw new EugeneException("TEST NOT PASSED!");
//		}
//		
//		System.exit(1);
//
//		try {
//			List<Object> loo = this.sparrow.query("union", null);
//			System.out.println(loo.size());
//			
//		} catch(SparrowException spe) {
//			throw new EugeneException(spe.toString());
//		}
//
//		try {
//			List<Object> loo = this.sparrow.query("union2", null);
//			System.out.println(loo);
//			
//		} catch(SparrowException spe) {
//			throw new EugeneException(spe.toString());
//		}
//
//		try {
//			List<Object> loo = this.sparrow.query("test", null);
//			System.out.println(loo);
//			
//		} catch(SparrowException spe) {
//			throw new EugeneException(spe.toString());
//		}
//
//		try {
//			List<Object> loo = this.sparrow.query("test01", null);
//			System.out.println(loo);
//		
//		} catch(SparrowException spe) {
//			throw new EugeneException(spe.toString());
//		}
//
//		System.out.println("executing ...");
//		try {
//			this.sparrow.fireRules("tester01.drl");
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		System.exit(1);
//	}
	
	private void storeRuleCompliantDevices(Set<Device> sod) 
			throws EugeneException {
		if(null!=sod && !sod.isEmpty()) {
			for(Device device : sod) {
				try {
//					System.out.println(device);
					this.sparrow.insertFact(device);
				} catch(SparrowException spe) {
					throw new EugeneException(spe.getMessage());
				}
			}
		}
	}
	

	/**
	 * The permute/1 method permutes the elements of the given device.
	 * @param d ... the device
	 * @throws EugeneException
	 */
	public EugeneCollection permute(Device d) 
			throws EugeneException {
		return new EugeneCollection(null);
	}

	public void assignTo(String name, NamedElement ne, String id, Variable idx, NamedElement rhs, boolean bRef) 
			throws EugeneException {
		
		if(null == rhs) {
			throw new EugeneException("Invalid assignment to " + name+"!");
		}
		
//		System.out.println("[Interp.assignTo] -> "+ name +", " + ne+", " + id+", "+idx+" =  " +rhs+", "+ bRef);
		
		if(null != name && null == ne) {
			// create a new NamedElement named after name
			if(bRef) {
//				System.out.println(name + " by-reference ");
				
				// by-reference
				rhs.setName(name);
				this.put(rhs);
			} else {
//				System.out.println(name + " by-value ");
				// deep-clone
				NamedElement lhs = this.cloner.deepClone(rhs);
				lhs.setName(name);
				this.put(lhs);
			}
		} else if(ne != null) {
			
			if(!(ne instanceof Variable) && !(ne instanceof Part) && 
					!(ne instanceof PropertyValue) && !(ne instanceof EugeneContainer)) {
				throw new EugeneException("Invalid assignment!");
			}
			
			// the 2nd condition is a bit of a hack.
			if(null != id && !ne.getName().equals(id)) {
				
				// ne must be a component
				if(ne instanceof Component) {
					
					Property prop = ((Component)ne).getProperty(id);
					if(null != prop) {

						try {
							// the type checking is done in the setPropertyValue method
							if(rhs instanceof Variable) {
								((Component)ne).setPropertyValue(prop, (Variable)rhs);
							} else if(rhs instanceof PropertyValue) {
								((Component)ne).setPropertyValue(prop, (PropertyValue)rhs);
							} else {
								throw new EugeneException("I cannot assign " + rhs + " to " + ne.getName()+"."+id);
							}
						} catch(Exception e) {
							throw new EugeneException(e.getMessage());
						}
						
					} else {
						throw new EugeneException(ne.getName() + " does not contain a property named " + id);
						
					}
					
				} else {
					
					throw new EugeneException("Invalid assignment!" + ne.getName()+"."+id);
					
				}
			} else if(null != idx) {
				
				/*
				 * this is only possible if the named element is a part, property value, or variable
				 */
				if(ne instanceof Variable && rhs instanceof Variable) {
					
					((Variable)ne).setElement((int)idx.getNum(), (Variable)rhs);
				}
//				ne.setElement(idx.getNum(), rhs);

			} else {
				if(bRef) {
					// by-reference
					rhs.setName(name);
					this.put(rhs);
				} else {
					// deep-clone
					ne = this.cloner.deepClone(rhs);
					ne.setName(name);
					this.put(ne);
				}
			}
			
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws EugeneException
	 */
	public NamedElement get(String name) 
			throws EugeneException {

		NamedElement ne = null;

		if(this.stack.size() > 0) {
			Stack<StackElement> tmp = new Stack<StackElement>();
			while(this.stack.size() > 0 && ne == null) {
				StackElement se = this.stack.pop();
				tmp.push(se);

				if(se.contains(name)) {
					ne = se.get(name);
				}
			}

			/*
			 * we need to restore the old stack
			 */
			while(tmp.size() > 0) {
				this.stack.push(tmp.pop());
			}

		}
		
		if(null != ne) {
			return ne;
		} else {
			ne = this.symbols.get(name);
			if(null != ne) {
				return ne;
			}
	
			try {
				ne = this.sparrow.getFact(name);
			} catch(SparrowException spe) {
				throw new EugeneException(spe.getMessage());
			}
		}
		
		return ne;
	}
	
	public Variable getVariable(String name) 
			throws EugeneException {
		
		NamedElement ne = this.get(name);
		if(null == ne) { 
			throw new EugeneException(name + " has not been declared!");
		} else if(!(ne instanceof Variable)) {
			throw new EugeneException(name + " is not a Variable!");
		}
		return (Variable)ne;
	}
		
	public boolean contains(String name) {

		boolean bContains = false;
		try {
			
			bContains = this.symbols.contains(name) || this.sparrow.contains(name);
			if(bContains) {
				return true;
			}
			
			if(this.stack.size() > 0) {
				Stack<StackElement> tmp = new Stack<StackElement>();
				while(this.stack.size() > 0 && !bContains) {
					StackElement se = this.stack.pop();
					tmp.push(se);
					if(bContains == false) {
						bContains = se.contains(name);
					}
				}
				
				/*
				 * we need to rebuild the old stack
				 */
				while(tmp.size() > 0) {
					this.stack.push(tmp.pop());
				}
				
				if(bContains) {
					return bContains;
				}
			}
			
			// the requested object can either be in the symbol tables 
			// or in the WM of the KBS
			
		} catch(SparrowException se) {
			// ignore
		}
		
		return false;
	}
	

	/**
	 * The putImportedData/1 method inserts data 
	 * --- imported using Eugene's data exchange facilities --- 
	 * into the symbol tables.
	 * if the data is a Eugene collection, then we store 
	 * each collection element individually into the symbol tables.
	 * otherwise, we just store the given element.
	 * 
	 * This method gets invoked by the Eugene parser if the user 
	 * imported biological data and does not assign it to 
	 * a variable. 
	 * Example:
	 * SBOL.import("/path/to/data.sbol");
	 *  
	 * @param ne ... a named element
	 * @throws EugeneException
	 */
	public void putImportedData(NamedElement ne) 
			throws EugeneException {
		
		if(ne instanceof EugeneCollection) {
			/*
			 * iterate over the elements and put them 
			 * individually into the symbol tables
			 */
			for(NamedElement e : ((EugeneCollection)ne).getElements()) {
				this.put(e);
			}
		} else {
			this.put(ne);
		}
		
	}
	
	public void put(String name, NamedElement ne) 
			throws EugeneException {

		
		if(0 == this.stack.size()) {
			/*
			 * MAIN function
			 */
			this.symbols.put(name, ne);

			if(ne instanceof Part) {
				try {
					this.sparrow.insertFact((Part)ne);
				} catch(SparrowException spe) {
					// let's ignore it for the time being.
				}
			} else if(ne instanceof Interaction) {
				try {
					this.sparrow.insertFact((Interaction)ne);
				} catch(SparrowException spe) {
					// let's ignore it for the time being.
				}
			}
		} else {
			/*
			 * scoping
			 */
			if(this.stack.peek() instanceof ForLoop &&
					((ForLoop)this.stack.peek()).getVarname().equals(name)) {
					
				StackElement se = this.stack.pop();
				this.put(name, ne);
				this.stack.push(se);

			} else {

				this.stack.peek().put(ne);
				
			}
		}
		
	}
	
	public void put(NamedElement ne) 
			throws EugeneException {
		if(null != ne) {
			this.put(ne.getName(), ne);
		}
	}
	
	public Rule createRule(String name, String device) 
			throws EugeneException {

		/*
		 * first, we need to some error checking 
		 */
		if(this.contains(name)) {
			throw new EugeneException(name +" exists already.");
		}

		NamedElement ne = null;
		if(null != device) {
			if(!this.contains(device)) {
				throw new EugeneException("A device named "+device+" does not exist.");
			}
			
			ne = this.get(device);
			if(!(ne instanceof Device)) {
				throw new EugeneException(device+" is not a device.");
			}
		}

		/*
		 * now, we create a new rule object
		 */
		Rule r = new Rule(name, (Device)ne);

		/*
		 * then, we store it into the symbol tables
		 */
		this.put(r);
		
		/*
		 * and finally, we return the Rule object
		 */
		return r;
	}
	
	/**
	 * 
	 * @param lhs
	 * @param op
	 * @param rhs
	 * @return
	 * @throws EugeneException
	 */
	public ArrangementConstraint createPredicate(
			ArrangementOperand lhs, String op, ArrangementOperand rhs) 
			throws EugeneException {
		
		return new ArrangementConstraint(lhs, op, rhs);
		
	}
	
	/**
	 * The negatePredicate/1 method negates a given predicate
	 * 
	 * @param p ... the predicate to be negated
	 * @return  ... an instance of the LogicalNot predicate, representing the negation
	 *              of the given predicate p
	 * @throws EugeneException
	 */
	public Predicate negate(Predicate p) 
			throws EugeneException {
		
		if(p instanceof ExpressionConstraint) {
			((ExpressionConstraint)p).negate();
			return p;
		}
		
		return new LogicalNot(p);
	}
	
	/**
	 * The logicalOr/2 method combines two constraints using the logical or (/\) operator
	 * 
	 * @param c1   ... the lhs constraint
	 * @param c2   ... the rhs constraint
	 * @return   a LogicalOr object representing the disjunction of c1 and c2
	 * @throws EugeneException
	 */
	public LogicalOr logicalOr(Predicate p1, Predicate p2) 
			throws EugeneException {

		LogicalOr lor = null; 
		if(p1 instanceof LogicalOr) {
			lor = (LogicalOr)p1;
		} else {		
			lor = new LogicalOr();
			lor.getConstraints().add(p1);
		}
		
		lor.getConstraints().add(p2);

		/*
		 * only if it's a valid disjunction, then 
		 * we return the LogicalOr object
		 */
		if(lor.validate()) {
			return lor;
		}
		
		throw new EugeneException(p1+" "+EugeneConstants.OR+" "+p2+" is invalid!");
	}
	
	/**
	 * the executeRule method executes the given rule.
	 * 
	 * @param r ... the rule to be executed
	 */
	public void executeRule(Rule rule) {
		
		if(null == this.spAdapter) {
			this.spAdapter = new SparrowAdapter(this.sparrow);
		}
		
		try {
			this.spAdapter.queryParts(rule);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param name
	 * @throws EugeneException
	 */
	public void createCollection(String name) 
			throws EugeneException {
		if(this.contains(name)) {
			throw new EugeneException(name+" exists already.");
		}

		this.push(new EugeneCollection(name));
	}
	
	/**
	 * 
	 * @param name
	 * @throws EugeneException
	 */
	public void createArray(String name) 
			throws EugeneException {
		if(this.contains(name)) {
			throw new EugeneException(name+" exists already.");
		}
		this.push(new EugeneArray(name));
	}
	
	public void assign(String to, NamedElement element) 
			throws EugeneException {
		NamedElement ne = null;
		
		if(this.contains(to)) {
			try {
				ne = this.get(to);
			} catch(EugeneException e) {
				throw new EugeneException(e.getMessage());
			}
		}

		/*
		 * TODO!!!
		 */
		
		if(!this.contains(to)) {
			this.put(ne);
		}

	}
	
	
	/**********************************************************************
	 * DATA EXCHANGE METHODS
	 **********************************************************************/
	/*
	 * SBOL Import
	 */ 
	public NamedElement importSBOL(String sFile) 
			throws EugeneException {
		if (null == sFile || sFile.isEmpty()) {
			throw new EugeneException("SBOL.import: Invalid filename!");
		}

		if (sFile.startsWith("\"") && sFile.endsWith("\"")) {
			sFile = sFile.substring(1, sFile.length() - 1);
		}

		Set<NamedElement> elements = SBOLImporter.importSBOL(sFile);
		if (null != elements && !elements.isEmpty()) {
			
			if(elements.size() > 0) {
				// then, we return a Eugene collection
				EugeneCollection ec = new EugeneCollection(null);
				for(NamedElement element : elements) {
					ec.getElements().add(element);
				}
				
//				this.put(ec);
				return ec;
			} else {
				// otherwise, we return the only element
				for(NamedElement element : elements) {
//					this.put(element);
					return element;
				}
			}
		}
		return null;
	}

	/*
	 * SBOL EXPORT
	 */ 
	public void exportToSBOL(String sName, String sFileName)
			throws EugeneException {
		
		NamedElement objElement = this.get(sName);
		if (objElement == null) {
			throw new EugeneException("I don't know anything about " + sName
					+ "!");
		} else if (objElement instanceof EugeneContainer) {
			SBOLExporter.serialize((EugeneContainer) objElement, sFileName);
		} else if (objElement instanceof Component) {
			SBOLExporter.serialize((Component) objElement, sFileName);
		} else {
			throw new EugeneException(
					"I cannot export the "
							+ sName
							+ " element to SBOL! "
							+ "Only collections, arrays, devices, and parts are allowed!");
		}
	}
	
	/*
	 * PIGEON
	 */ 
	public Collection<URI> pigeon(String name) 
			throws EugeneException {
		
		if(null == name || name.isEmpty()) {
			throw new EugeneException("Invalid name!");
		}
		
		Collection<URI> ret_uris = new HashSet<URI>();
		
		/*
		 * retrieve the object from the this.symbols
		 */
		NamedElement element = this.get(name);
		if(null == element) {
			throw new EugeneException("I cannot find an element named "+name+"!");
		}
		
		if(!(element instanceof Component) && !(element instanceof EugeneContainer)) {
			throw new EugeneException("I cannot visualize "+name+"!");
		}

		if(null == this.pigeon) {
			this.pigeon = new Pigeonizer();
		}
		
		URI uri = null;
		if(element instanceof Device) {
			uri = this.pigeon.pigeonizeSingle((Device)element, null);
			
			/*
			 * for testing, open the URI
			 */
			if(null != uri) {
				WeyekinPoster.launchPage(uri);
				
				ret_uris.add(uri);
			}

		} else if(element instanceof EugeneContainer) {
			
			List<URI> uris = new ArrayList<URI>(50);
			int i = 0; 
			for(NamedElement e : ((EugeneContainer)element).getElements()) {
				
				if(e instanceof Device) {
					i++;
					
					if(i % 50 == 0) {
						
						ret_uris.add(
								this.toSerializedImage(
										uris, 
										"./exports/pigeon/"+UUID.randomUUID()+".png"));
						uris.clear();

					} else {
						uris.add(
							this.pigeon.pigeonizeSingle(
									(Device)e, 
									null));
					}
				}
			}
			
			/*
			 * lastly, visualize the remaining devices
			 * (if there are any)
			 */
			if(!uris.isEmpty()) {
				ret_uris.add(
						this.toSerializedImage(
								uris, 
								"./exports/pigeon/"+UUID.randomUUID()+".png"));
			}
			
		}
		
		return ret_uris;
	}
	
	/**
	 * The toSerializeImage/2 merges all images given by the URIs into 
	 * one big image (using the org.cidarlab.eugene.data.Pigeonizer) and
	 * stores the resulting image into the given filename.
	 * 
	 * @param uris  ... a list of URIs pointing to images (such as 
	 *                  SBOL Visual compliant representations) 
	 * @param filename ... the filename of the resulting image
	 * 
	 * @return the URI of the resulting image
	 * 
	 * @throws EugeneException
	 */
	private URI toSerializedImage(List<URI> uris, String filename)
		throws EugeneException {
		try {
			// merge all images into one image
			RenderedImage img = this.pigeon.toMergedImage(uris);
			// serialize the image to a file
			this.pigeon.serializeImage(img, filename);
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}
		
		// if everything went fine, then 
		// we return the generated image as an URI
		return URI.create(filename);
	}
	/*
	 * EUGENE DOM OBJECTS
	 */
	public EugeneCollection getAllElements() 
			throws EugeneException {
		try {
			EugeneCollection ec = new EugeneCollection(null);
			ec.getElements().addAll(this.sparrow.getFacts());
			ec.getElements().addAll(this.symbols.getAll());
			return ec;
		} catch(SparrowException spe) {
			throw new EugeneException(spe.getMessage());
		}
	}
	
	/**********************************************************************
	 * METHODS TO DEAL WITH CONTAINERS 
	 **********************************************************************/
	public void addToContainer(NamedElement ne) 
			throws EugeneException {
		
		if(null != this.stack && null != this.stack.peek() && null != ne) {
			if(this.stack.peek() instanceof EugeneContainer) {
				// if everything's OK, then add a deep-clone of 
				// the element to the container
				((EugeneContainer)(this.stack.peek())).put(
						this.cloner.deepClone(ne));
			} else {
				throw new EugeneException("Adding " + ne.getName() +" to " + this.stack.peek().getName()+" is not supported!");
			}
		}
		
	}
	
	
	/**********************************************************************
	 * EXPRESSIONS 
	 **********************************************************************/
    //does multiplication or division on a primitive, used by grammar rule multExpr
    public void doMultDivOp(Variable source, Variable destination, String op) 
    		throws EugeneException {
    	
        if (source.type.equals(EugeneConstants.NUM)) {
            if ("*".equals(op)) {
                destination.num *= source.num;
            } else {
                if (source.num != 0) {
                    destination.num /= source.num;
                } else {
                    throw new EugeneException("Division by zero.");
                }
            }
        } else {
        	throw new EugeneException("Cannot perform * operation on non-numerical values!");
        }
    }

	//does addition or subtraction on a primitive, used by grammar rule expr
	public void doMinPlusOp(Variable source, Variable destination, String op) 
			throws EugeneException {

		if ("+".equals(op)) {
			if (source.type.equals(EugeneConstants.NUM)) {
				destination.num += source.num;
				destination.type = EugeneConstants.NUM;
			} else if (source.type.equals(EugeneConstants.NUMLIST)) {
				destination.numList.addAll(source.numList);
				destination.type = EugeneConstants.NUMLIST;
			} else if (source.type.equals(EugeneConstants.TXTLIST)) {
				destination.txtList.addAll(source.txtList);
				destination.type = EugeneConstants.TXTLIST;
			} else if (source.type.equals(EugeneConstants.TXT)) {
				destination.txt += source.txt;
				destination.type = EugeneConstants.TXT;
			}
		} else if ("-".equals(op)) {
			if (source.type.equals(EugeneConstants.NUM)) {
				destination.num -= source.num;
				destination.type = EugeneConstants.NUM;
			} else {
				throw new EugeneException("Cannot perform the - operation on non-numerical types!");
			}
		}
	}

	
	/**********************************************************************
	 * IMPERATIVE LANGUAGE FEATURES
	 **********************************************************************/
	public void push(StackElement se) {
		this.stack.push(se);
//		System.out.println("[PUSHED] -> " + se);
	}
	
	/*
	 * the clear/0 method clears the symbol tables of the top stack element
	 */
	public void clear() 
			throws EugeneException {
		if(0 == this.stack.size()) {
			throw new EugeneException("The stack is empty!");
		}
		this.stack.peek().clear();
	}
	
	public void pop() 
			throws EugeneException {
		
		if(0 == this.stack.size()) {
			throw new EugeneException("The stack is empty!");
		}
		
		StackElement se = this.stack.pop();
		if(!(se instanceof EugeneContainer)) {
			se.clear();
		}
		
		
		if(se instanceof EugeneContainer) { 
			if(this.stack.size() == 0) {
				this.put(se);
			} else {
				if(this.stack.peek() instanceof EugeneContainer) {
					this.stack.peek().put(se);
				}
			}
		}  
	}
	
	/**
	 * The removeVariable/1 method removes the variable of the given
	 * name from the symbol tables.
	 * 
	 * This method is particularly important for:
	 * - FOR loops in order to remove the iteration variable
	 * - FUNCTION PROTOTYPES in order to remove the parameters
	 * 
	 * @param varname
	 */
	public void removeVariable(String varname) {
		if(0 == this.stack.size()) {
			this.symbols.removeVariable(varname);
		} else {
			if(this.stack.peek() instanceof ImperativeFeature) {
				((ImperativeFeature)this.stack.peek()).removeVariable(varname);
			}
		}
	}
	
	
	/**
	 * The evaluateCondition/3 compares the lhs variable with the 
	 * rhs variable according to the given operator op.
	 * The evaluateCondition/3 returns true if the condition is 
	 * satisfied, false otherwise.
	 * 
	 * @param lhs  ... the lhs variable
	 * @param op   ... the comparison operator
	 * @param rhs  ... the rhs variable
	 * 
	 * @return true ... iff the condition is satisfied
	 *         false .. otherwise
	 *         
	 * @throws EugeneException
	 */
	public boolean evaluateCondition(Variable lhs, String op, Variable rhs) 
			throws EugeneException {
		if(null==lhs) {
			throw new EugeneException("The LHS of a condition cannot be NULL!");
		}
		if(null==rhs) {
			throw new EugeneException("The RHS of a condition cannot be NULL!");
		}
		
		// type comparison
		if(!lhs.getType().equals(rhs.getType())) {
			throw new EugeneException("The types of the LHS and RHS do not match! " + 
					lhs.getType() +" != "+ rhs.getType());
		}
		
		if(!EugeneConstants.NUM.equals(lhs.getType())) {
			throw new EugeneException("The types of the LHS and RHS are not numeric!");
		}
		
		if(EugeneConstants.LT.equals(op)) {
			return this.lt(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.LEQ.equals(op)) {
			return this.leq(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.EQUALS.equals(op) ||
				"==".equals(op)) {
			return this.eq(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.NOTEQUALS.equals(op) ||
				"!=".equals(op)) {
			return this.neq(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.GEQ.equals(op)) {
			return this.geq(lhs.getNum(), rhs.getNum());
		} else if(EugeneConstants.GT.equals(op)) {
			return this.gt(lhs.getNum(), rhs.getNum());
		}
		
		return true;
	}
	
	private boolean lt(double a, double b) {
		return a < b;
	}

	private boolean leq(double a, double b) {
		return a <= b;
	}

	private boolean eq(double a, double b) {
		return a == b;
	}

	private boolean neq(double a, double b) {
		return a != b;
	}

	private boolean geq(double a, double b) {
		return a >= b;
	}

	private boolean gt(double a, double b) {
		return a > b;
	}
}
