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
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.constants.EugeneConstants.ParsingPhase;
import org.cidarlab.eugene.data.genbank.GenbankImporter;
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
import org.cidarlab.eugene.dom.imp.functions.FunctionInstance;
import org.cidarlab.eugene.dom.imp.functions.FunctionPrototype;
import org.cidarlab.eugene.dom.imp.loops.Loop;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.ArrangementConstraint;
import org.cidarlab.eugene.dom.rules.ArrangementOperand;
import org.cidarlab.eugene.dom.rules.LogicalAnd;
import org.cidarlab.eugene.dom.rules.LogicalNot;
import org.cidarlab.eugene.dom.rules.LogicalOr;
import org.cidarlab.eugene.dom.rules.Predicate;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.dom.rules.SelectionPredicate;
import org.cidarlab.eugene.dom.rules.exp.ExpressionConstraint;
import org.cidarlab.eugene.exception.DOMException;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.minieugene.MiniEugeneAdapter;
import org.cidarlab.eugene.parser.EugeneLexer;
import org.cidarlab.eugene.parser.EugeneParser;
import org.cidarlab.eugene.sparrow.SparrowAdapter;
import org.cidarlab.eugene.util.EugeneDeveloperUtils;
import org.cidarlab.eugene.util.FileUtils;
import org.cidarlab.eugene.util.SequenceUtils;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.Repository;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.SparrowException;

import com.rits.cloning.Cloner;

/**
 * The Interp class is Eugene's Interpreter.
 * It provides methods to (mainly) the Eugene Parser 
 * for the execution of Eugene statements.
 * 
 * @author Ernst Oberortner
 */
public class Interp {

	private Sparrow sparrow;
	private SymbolTable symbols;
	
	private MiniEugeneAdapter meAdapter;
	private SparrowAdapter spAdapter;
	
	private Pigeonizer pigeon;
	private GenbankImporter genbanker;
	
	private Cloner cloner;
	
	/*
	 * an instance of the RuleBuilder
	 */
	private RuleBuilder ruleBuilder;
	
	/*
	 * STACK for scoping
	 */
	private Stack<StackElement> stack;

	/*
	 * the writer for writing outputs
	 */
    private BufferedWriter writer = null;

    /*
     * the Comparator object
     */
    private Comparator comparator;
	
    
    private String ROOT_DIRECTORY;
    
    // the ExpressionExecutor object provides method 
    // to interpret and perform mathematical expressions,
    // such as ((a + b) * c ) / d
    private ExpressionExecutor executor;
    
    // the includedFiles set keeps track of all files 
    // that have been included. if a file has been included 
    // and is being included again, then it will not 
    // be included and interpreted.    
	private Set<String> includedFiles;


	/**
	 * CONSTRUCTOR
	 * 
	 * @param sparrow  ... a Sparrow object which represents the library management system (LMS)
	 * @param writer   ... a BufferedWriter to that all output (via print statements) is being directed 
	 * @param ROOT_DIRECTORY  ... the ROOT directory of the Eugene script which is being interpreted
	 */
	public Interp(Sparrow sparrow, BufferedWriter writer, String ROOT_DIRECTORY) {

		/*
		 * FACTS/RULES
		 */
		try {
			if(null != sparrow) {
				this.sparrow = sparrow;
			} else {
				this.sparrow = new Sparrow();
			}
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
		
		/*
		 * the comparator
		 */
		this.comparator = null;
		
		/*
		 * ROOT DIRECTORY
		 */
		this.ROOT_DIRECTORY = ROOT_DIRECTORY;
	}

	private BufferedWriter getWriter() {
		return this.writer;
	}
	
	
	/**
	 * 
	 * @param sName ... the name of the property
	 * @param sType ... the type of the property
	 * @throws EugeneException
	 */
	public void createProperty(String name, String type)
			throws EugeneException {
		
		if(this.checkIfDeclaredInScope(name)) {
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
		
		if(this.checkIfDeclaredInScope(name)) {
			throw new EugeneException(name+" exists already.");
		}
		
		ComponentType ct = new ComponentType(name);
		
		if(null != elements) {
			for(NamedElement ne : elements) {
				
				if(!(ne instanceof Property)) {
					throw new EugeneException(ne.getName()+" is not a Property.");
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
		
		if(this.checkIfDeclaredInScope(name)) {
			throw new EugeneException("An element named "+name+" exists already!");
		}
		
		PartType pt = new PartType(name);
		
		if(null != elements) {
			for(NamedElement ne : elements) {
				
				if(!(ne instanceof Property)) {
					throw new EugeneException(ne.getName()+" is not a Property.");
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
		if(this.checkIfDeclaredInScope(name)) {
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

				Property prop = null;
				
				// pre-defined properties of parts
				if(EugeneConstants.PIGEON_PROPERTY.equals(property.toUpperCase())) {
					prop = EugeneDeveloperUtils.createPigeonProperty();
					//prop = pt.getProperty(EugeneConstants.PIGEON_PROPERTY);
				} else if (EugeneConstants.SEQUENCE_PROPERTY.equals(property.toUpperCase())) {
					prop = EugeneDeveloperUtils.createSequenceProperty();
//					prop = pt.getProperty(EugeneConstants.SEQUENCE_PROPERTY);
				} else if(null == (prop = pt.getProperty(property))) {
					throw new EugeneException("Parts of part type "+pt.getName()+" do not have "+property+" property.");
				}
				
				try {
					p.setPropertyValue(prop, values.get(i));
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
		Component c = Component.instantiate(ct, name);
		
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

		if(this.checkIfDeclaredInScope(name)) {
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
			throw new EugeneException(lhsElement +" is not a genetic component.");
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
		
		if(!this.checkIfDeclaredInScope(rhs.getName())) {
			this.put(rhs);
		}
		
		return this.createInteraction(name, lhs, type, rhs.getName());
	}

	/**
	 * The product/1 method enumerates all rule-compliant 
	 * devices based on a given device (``Device Template'')
	 * 
	 * @param name ... the name of the ``device template''
	 * @throws EugeneException
	 */
	public EugeneArray product(String name) 
			throws EugeneException {
		
        NamedElement ne = this.get(name);
        if(ne == null) {
            throw new EugeneException(name+" is not declared.");
        }
        
        if(!(ne instanceof Device)) {
            throw new EugeneException(name+" is not a Device.");
        }
        
        return this.product((Device)ne);
	}
	
	/**
	 * The private product/1 method takes as input a Eugene Device 
	 * and enumerates all rule-compliant instances of it.
	 * 
	 * It follows a three-step design exploration process:
	 * 1. Querying/Selecting Parts from the LMS  (Sparrow)
	 * 2. Architectural Composition Constraints  (miniEugene)
	 * 3. Pruning of Part compositions regarding Querying constraints
	 *                                           (Sparrow)
	 * 
	 * Both, the product(String) and permute(String) methods invoke 
	 * this product(Device) method. 
	 * 
	 * @param d  ... the Device to be permuted
	 * @return   ... an Eugene Array containing all rule-compliant 
	 *               instances of the Device
	 *               
	 * @throws EugeneException
	 */
	private EugeneArray product(Device d)
			throws EugeneException {
		
		// Lazy Evaluation of the SparrowAdapter object
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
			meAdapter = new MiniEugeneAdapter(this);
		}

		/*
		 * STEP 2: ``Architectural'' Constraints
		 * 
		 * here, we use miniEugene
		 * e.g. promoter BEFORE repressor
		 */
		List<Device> sod = null;
		try {
//			System.out.println("*** STEP2: ARCHITECTURE ***");
			sod = this.meAdapter.product(d, rules, sop, soi);
//			System.out.println("[Interp.product] devices -> "+sod);
//			System.out.println("***************************");
		} catch(Exception ee) {
			throw new EugeneException(ee.getMessage());
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
		 * then, we store all devices into a EugeneArray and 
		 * name them properly
		 */
		EugeneArray ea = new EugeneArray(null);
		int i=1;
		if(!sod.isEmpty()) {
			for(Device sd : sod) {
				// naming of the device
				sd.setName(d.getName()+"_"+i);
				i++;

				// putting the device into the 
				// EugeneArray
				ea.getElements().add(sd);
			}
		}
		
		// in the product/1 method, we do not store any
		// enumerated device since it should be the task 
		// of the user what to do with those devices.
		return ea; 
	}
	
	/**
	 * The query(Predicate) method gets as input a Predicate object
	 * that represents an expression-constraint in Eugene. It 
	 * returns a EugeneCollection which contains all parts compliant 
	 * with the expression constraint.
	 * 
	 * Example:
	 * PT.name != "p1" AND PT.id > 1 
	 * 
	 * Query all parts of type PT whose 
	 * name does not equal to "p1" AND whose 
	 * id is greater then 1 
	 * 
	 * @param p  ... a Eugene query
	 * @return ... a
	 * @throws EugeneException
	 */
	public EugeneCollection query(LogicalAnd query)		
			throws EugeneException {
		
		if(null == query) {
			throw new EugeneException("Invalid query.");
		}
		
		// Lazy Evaluation of the SparrowAdapter object
		if(null == spAdapter) {
			spAdapter = new SparrowAdapter(this.sparrow);
		}
		
		// first, we need to convert the 
		// predicate into a rule.
		Rule r = new Rule("r");
		r.setLogicalAnd(query);
		
		EugeneCollection ec = new EugeneCollection(null);
		try {
			// then, we do the query on ``Sparrow''
			Set<Part> sop = this.spAdapter.queryParts(r);
		
			// then we store the parts in a EugeneCollection
			ec.getElements().addAll(sop);
			
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
		
		// lastly we return the EugeneCollection object
		return ec;
	}
	
	/**
	 * The method getSizeOf/1 returns the size of a given NamedElement object.
	 * Example: sizeof("ATCG") -> 4
	 * 
	 * @param el  ... the NamedElement object
	 * @return    ... the size of the NamedElement object
	 * 
	 * @throws EugeneException
	 */
	public Variable getSizeOf(NamedElement el) 
			throws EugeneException {
		
		if(null == el) {
			throw new EugeneException("Undeterministic size!");
		}
		
    	Variable v = new Variable(null, EugeneConstants.NUM);
		
    	if(el instanceof Variable) {
    		if(EugeneConstants.NUM.equals(((Variable)el).getType()) ||
    				EugeneConstants.BOOLEAN.equals(((Variable)el).getType())) {
    			v.num = 0;
    		} else if(EugeneConstants.NUMLIST.equals(((Variable)el).getType())) {
    			if(null != ((Variable)el).getNumList()) {
    				v.num = ((Variable)el).getNumList().size();
    			} else {
    				v.num = 0;
    			}
    		} else if(EugeneConstants.TXTLIST.equals(((Variable)el).getType())) {
    			if(null != ((Variable)el).getTxtList()) {
    				v.num = ((Variable)el).getTxtList().size();
    			} else {
    				v.num = 0;
    			}
    		} else if(EugeneConstants.TXT.equals(((Variable)el).getType())) {
    			if(null != ((Variable)el).getTxt()) {
    				v.num = ((Variable)el).getTxt().length();
    			} else {
    				v.num = 0;
    			}
    		}
    	} else if(el instanceof PropertyValue) {
    		if(EugeneConstants.NUM.equals(((PropertyValue)el).getType()) ||
    				EugeneConstants.BOOLEAN.equals(((PropertyValue)el).getType())) {
    			v.num = 0;
    		} else if(EugeneConstants.NUMLIST.equals(((PropertyValue)el).getType())) {
    			if(null != ((PropertyValue)el).getNumList()) {
    				v.num = ((PropertyValue)el).getNumList().size();
    			} else {
    				v.num = 0;
    			}
    		} else if(EugeneConstants.TXTLIST.equals(((PropertyValue)el).getType())) {
    			if(null != ((PropertyValue)el).getTxtList()) {
    				v.num = ((PropertyValue)el).getTxtList().size();
    			} else {
    				v.num = 0;
    			}
    		} else if(EugeneConstants.TXT.equals(((PropertyValue)el).getType())) {
    			if(null != ((PropertyValue)el).getTxt()) {
    				v.num = ((PropertyValue)el).getTxt().length();
    			} else {
    				v.num = 0;
    			}
    		}
    		
    	// EugeneContainers (i.e. EugeneArray and EugeneCollection)	
    	} else if(el instanceof EugeneContainer) {
    		if(null != ((EugeneContainer)el).getElements()) {
        		v.num = ((EugeneContainer)el).getElements().size();
    		} else {
    			v.num = 0;
    		}
    		
    	// Device	
    	} else if(el instanceof Device) {
    		if(null != ((Device)el).getComponentList()) {
    			v.num = ((Device)el).getComponentList().size();
    		} else {
    			v.num = 0;
    		}
    		
    	}
    	
    	return v;
	}
	
	/**
	 * The getSequenceOf(NamedElement) function calculates 
	 * the DNA sequence of the given NamedElement object
	 * iff the object is either a Device or a Part. Otherwise
	 * an exception is thrown.
	 * 
	 * @param el  ... the object of that the DNA sequence should
	 * be calculated
	 * 
	 * @return ... a Variable of type TXT that contains the DNA sequence
	 * 
	 * @throws EugeneException ... if the DNA sequence cannot be determined
	 */
	public Variable getSequenceOf(NamedElement el) 
			throws EugeneException {
		
		// NULL
		if(null == el) {
			throw new EugeneException("Undeterministic sequence.");
		}

		// we can only calculate the DNA sequence of a 
		// Component, eiter basic (Part) or composite (Device).
		if(!(el instanceof Component)) {
			throw new EugeneException(el.getName() + " is neither a Part nor a Device.");
		}
		
		// ``anonymous'' variable of type txt
		Variable v = new Variable(null, EugeneConstants.TXT);
		
		// we utilize Eugene's Sequence Utilities 
		// to calculate the sequence of the component
		v.txt = SequenceUtils.toSequence((Component)el);
		
		return v;
	}
	
	/**
	 * The getRandom/2 method returns a random number (encapsulated in a 
	 * num Eugene variable) between the given range [sor, eor].
	 * sor ... start of range
	 * eor ... end of range
	 * 
	 * @param sor  ... the start of the range
	 * @param eor  ... the end of the range
	 * @return   ... a num variable containing a random number
	 * @throws EugeneException
	 */
	public Variable getRandom(Variable sor, Variable eor) 
			throws EugeneException {

		Variable v = new Variable(null, EugeneConstants.NUM);
		v.num = new Random().nextInt(((int)eor.getNum() - (int)sor.getNum()) + 1) + (int)sor.getNum();
		return v;
	}
	
	/**
	 * The storeIntoLibrary/1 method gets as input a NamedElement 
	 * and stores it into the Library (ie Sparrow).
	 * 
	 * @param e  ... the NamedElement object that should be store in the library
	 * 
	 * @throws EugeneException
	 */
	public void storeIntoLibrary(NamedElement e) 
			throws EugeneException {
		
		try {
			// we insert the NamedElement into the 
			// WM of our Sparrow LMS
			this.sparrow.insertFact(e);
		} catch(SparrowException spe) {
			throw new EugeneException(spe.getLocalizedMessage());
		}
	}
	

	/**
	 * The permute/1 method permutes the elements of the given device.
	 * @param d ... the device
	 * @throws EugeneException
	 */
	public EugeneArray permute(String name) 
			throws EugeneException {
		
        NamedElement ne = this.get(name);
        if(ne == null) {
            throw new EugeneException(name+" is not declared.");
        }
        
        //Device d = null;
        if(!(ne instanceof Device)) {
            throw new EugeneException(name+" is not a Device.");
        }
        
        /*
         * now, we permute the device
         */
        
        
        // now, we prepare the Device for permute:
        // Device D ([e11|e12], e21, [e31|e32]) 
        // -->
        // Device D ([e11|e12 | e21 | e31|e32], 
        //			 [e11|e12 | e21 | e31|e32],
        //			 [e11|e12 | e21 | e31|e32]);
        Device tmp = new Device(ne.getName());
        for(int i=0; i<((Device)ne).getComponents().size(); i++) {
        	List<NamedElement> els = new ArrayList<NamedElement>();
        	for(List<NamedElement> loe : ((Device)ne).getComponents()) {
        		els.addAll(loe);
        	}
        	tmp.getComponents().add(els);
        }
        
//        System.out.println("[Interp.permute] -> permuting: " + tmp);
        return this.product(tmp);
        
//        System.out.println(ea.getElements().size() + " solutions!");
//        for(NamedElement e : ea.getElements()) {
//        	if(e instanceof Device) {
//        		System.out.print("Device " + e.getName()+": ");
//        		int i=0;
//        		for(NamedElement ee : ((Device)e).getComponentList()) {
//        			System.out.print(((Device)e).getOrientations(i) +""+ee.getName()+"  ");
//        			i++;
//        		}
//        		System.out.println();
//        	}
//        }
        
//        return new EugeneArray(null);
	}

	/**
	 * The assignment/2 method performs a Eugene assignment statement, such as
	 * p1.prop1[i] = p2.prop2[i]. 
	 * It gets as input the LHS (e.g. p1.prop1[i]) as a string, and the NamedElement 
	 * object of the RHS (p2.prop2[i]) which has been interpreted already in the 
	 * parser.The parsing of the LHS happens by calling
	 * the parseAndGetElement/1 method. 
	 *  
	 * @param lhs ... the left-hand-side (LHS) of the assignment
	 * @param rhs ... the right-hand-side (RHS) of the assignment
	 * 
	 * @throws EugeneException
	 */
	public void assignment(String lhs, NamedElement el_rhs)
			throws EugeneException {
		
		this.parent = null;
		this.idx = -1;
		
		NamedElement el_lhs = this.parseAndGetElement(lhs);
		
//		System.out.println("[Interp.assignment] " + el_lhs +" <- "+el_rhs);
//		System.out.println("[Interp.assignment] " + el_lhs.getClass() +" <- "+el_rhs.getClass());
//		
		if(null != el_lhs) {
			/*
			 * we need to compare the types of 
			 * the LHS and the RHS elements
			 */
			// lazy evaluation of the Comparator
			if(null == this.comparator) {
				this.comparator = new Comparator();
			}
			
			/*-------------------
			 * TYPE COMPARISIONS
			 *-------------------*/
			boolean bValid = false;
			if((-1) != this.idx) {
				bValid = this.comparator.compareTypes(el_lhs, idx, el_rhs);
			} else {
				bValid = this.comparator.compareTypes(el_lhs, el_rhs);
			}
			
			if(!bValid) {
				throw new EugeneException("Invalid types!");
			}

			/*------------
			 * ASSIGNMENT
			 *------------*/
			if(el_rhs instanceof PropertyValue) {
				
				if(el_lhs instanceof Variable) {
					if(idx != -1) {
						((Variable)el_lhs).setElement(idx, 
								this.comparator.convertPropertyValueToVariable((PropertyValue)el_rhs));						
					} else {					
						this.assignment((Variable)el_lhs, (PropertyValue)el_rhs);
					}
					
					if(null != parent && parent instanceof Component) {
						try {
							((Component)parent).setPropertyValue(((Variable)el_lhs));
						} catch(DOMException de) {
							throw new EugeneException(de.getLocalizedMessage());
						}
					}

				} else if(el_lhs instanceof PropertyValue) {
					if(idx != -1) {
						((PropertyValue)el_lhs).set(idx, el_rhs);						
					} else {					
						// assigning a variable to a property value 
						this.assignment((PropertyValue)el_lhs, (PropertyValue)el_rhs);
					}
					
					if(null != parent && parent instanceof Component) {
						try {
							((Component)parent).setPropertyValue(((PropertyValue)el_lhs));
						} catch(DOMException de) {
							throw new EugeneException(de.getLocalizedMessage());
						}
					}

				}
				
			} else if(el_rhs instanceof Variable) {
				
				if(el_lhs instanceof Variable) {
					if(idx != -1) {
						((Variable)el_lhs).setElement(idx, (Variable)el_rhs);
					} else {
						this.assignment((Variable)el_lhs, (Variable)el_rhs);
					}

					if(null != parent && parent instanceof Component) {
						try {
							((Component)parent).setPropertyValue(((Variable)el_lhs));
						} catch(DOMException de) {
							throw new EugeneException(de.getLocalizedMessage());
						}
					}

				} else if(el_lhs instanceof PropertyValue) {
					
					// assigning a variable to a property value
					if(idx != -1) {
						((PropertyValue)el_lhs).set(idx, el_rhs);
					} else {
						this.assignment((PropertyValue)el_lhs, (Variable)el_rhs);
					}
					
					if(null != parent && parent instanceof Component) {
						try {
							((Component)parent).setPropertyValue(((PropertyValue)el_lhs));
						} catch(DOMException de) {
							throw new EugeneException(de.getLocalizedMessage());
						}
					}
				}
			} else {
				
				this.removeVariable(el_lhs.getName());

				el_lhs = this.cloner.deepClone(el_rhs);
				el_lhs.setName(lhs);
				
//				if(el_lhs instanceof EugeneCollection) {
//					for(NamedElement e : ((EugeneCollection)el_lhs).getElements()) {
//						if(e instanceof Device) {
//							System.out.println(e.getName()+" -> "+((Device)e).getComponentList());
//						}
//					}
//				}
				
				this.put(el_lhs);

			}
		
		// in this case, the LHS of the assignment does not exist.
		// hence, we duplicate the RHS, assign it the LHS name and 
		// store it in the symbol tables
		} else {
			el_lhs = this.cloner.deepClone(el_rhs);
			el_lhs.setName(lhs);
			this.put(el_lhs);
		}
	}
	
	private NamedElement parent = null;
	private int idx = -1;
	
	/**
	 * The parseAndGetElement/1 method parses the input string (e.g. p1.prop1[0]) 
	 * and returns the corresponding NamedElement object (e.g. the Variable 
	 * representing the first element of the prop1 array of part p1.
	 *  
	 * @param s ... the input string
	 * @return  ... the corresponding NamedElement object
	 * @throws EugeneException
	 */
	private NamedElement parseAndGetElement(String s) 
			throws EugeneException {
		
		String[] aos = s.split("\\.");
		
		NamedElement root = null;

		if(aos.length >= 1) {
			
			String root_name = aos[0];
			// check if there's an index provided
			if(aos[0].contains("[")) {
				try {
					// parse the index
					this.idx = this.getIndex(
							aos[0].substring(
									aos[0].indexOf('[')+1, 
									aos[0].indexOf(']')));
				} catch(EugeneException ee) {
					throw new EugeneException(ee.getLocalizedMessage());
				}
				
				root_name = s.substring(0, aos[0].indexOf('['));
				root = this.get(root_name);
			} else {
				// just an ID
				root = this.get(root_name);
			}

			if(aos.length == 1) {
				// regardless if root is NULL or not, 
				// we return it.
				return root;
			} else if(root == null) {
				throw new EugeneException("Cannot find " + root_name + "!");
			}
		}
		
		this.parent = root;
		NamedElement child = null;

		for(int i=1; i<aos.length; i++) {
			
			String prop_name = aos[i];
			boolean hasIndex = false;
			if(aos[i].contains("[")) {
				 prop_name = aos[i].substring(0, aos[i].indexOf('['));
				 hasIndex=true;
			}

			if(child == null) {
				child = this.parent.getElement(prop_name);
			} else {
				this.parent = child;
				child = this.parent.getElement(prop_name);
			}
			
			if(hasIndex) {
				try {
					// parse the index
					this.idx = this.getIndex(
							aos[i].substring(
									aos[i].indexOf('[')+1, 
									aos[i].indexOf(']')));
				} catch(EugeneException ee) {
					throw new EugeneException(ee.getLocalizedMessage());
				}
			}
			
		}
		
		return child;
	}
	
	/**
	 * The getIndex/1 method converts a given string into 
	 * an integer that denotes an array index.
	 * The string can either represent a number (i.e. we use Integer.parseInt) or
	 * an ID (i.e. we retrieve the ID from the symbol tables).
	 * 
	 * @param s  ... a string that represents the index
	 * @return   ... the index as integer
	 */
	private int getIndex(String s) 
			throws EugeneException {
		
		int idx = -1;
		try {
			idx = Integer.parseInt(s);
		} catch(Exception e) {
			
			// in this case, it's highly possible 
			// that the index is a variable
			NamedElement ne = this.get(s);
			
			// first, we check if s exists
			if(ne == null) {
				throw new EugeneException(s + " is an invalid index!");
			}
			// second, we check if s refers to a variable or property value
			if(!(ne instanceof Variable) && !(ne instanceof PropertyValue)) {
				throw new EugeneException(s + " is not a Variable nor a Property Value.");
			}
			
			// the type of the variable/property value must be numeric
			// hence, we retrieve the type of the variable/property value
			String type = null;
			if(ne instanceof Variable) {
				type = ((Variable)ne).getType();
			} else {
				type = ((PropertyValue)ne).getType();
			}
			if(!EugeneConstants.NUM.equals(type)) {
				throw new EugeneException(s +" is not a numeric value.");
			}
			
			// finally, we retrieve the numeric index
			double d_idx = -1;
			if(ne instanceof Variable) {
				d_idx = ((Variable)ne).getNum();
			} else {
				d_idx = ((PropertyValue)ne).getNum();
			}
			
			// we use the modulo operation in order to check 
			// if the index is an integer (i.e. a whole number) 
			if((d_idx % 1) != 0) {
				throw new EugeneException("Floating-point number as index!");
			}
			
			idx = (int)d_idx;
		} 
		
		if(idx < 0) {
			throw new EugeneException(s +" is a negative index!");
		}


		// finally, we return the index
		return idx;
	}
	
	public Variable convertPropertyValueToVariable(PropertyValue pv) {
		if(null == this.comparator) {
			this.comparator = new Comparator();
		}
		
		return this.comparator.convertPropertyValueToVariable(pv);
	}
	
	/**
	 * The variableAssignment/2 method assigns the value of the 
	 * RHS variable to the value of the LHS variable. 
	 * In this method, we do not perform any type checks. Those 
	 * happen in the assignment/3 method.
	 *  
	 * @param lhs  ... the LHS variable of the assignment 
	 * @param rhs  ... the RHS variable of the assignment
	 * 
	 * @throws EugeneException
	 */
	private void assignment(Variable lhs, Variable rhs) {
		if(EugeneConstants.NUM.equals(rhs.getType())) {
			lhs.num = rhs.num;
		} else if(EugeneConstants.NUMLIST.equals(rhs.getType())) {
			lhs.numList = this.cloner.deepClone(rhs.numList);
		} else if(EugeneConstants.TXT.equals(rhs.getType())) {
			lhs.txt = new String(rhs.txt);
		} else if(EugeneConstants.TXTLIST.equals(rhs.getType())) {
			lhs.txtList = this.cloner.deepClone(rhs.txtList);
		} else if(EugeneConstants.BOOLEAN.equals(rhs.getType())) {
			lhs.bool = rhs.bool;
		}
	}
	
	private void assignment(Variable lhs, PropertyValue rhs) {
		if(EugeneConstants.NUM.equals(rhs.getType())) {
			lhs.num = rhs.getNum();
		} else if(EugeneConstants.NUMLIST.equals(rhs.getType())) {
			lhs.numList = (ArrayList<Double>)this.cloner.deepClone(rhs.getNumList());
		} else if(EugeneConstants.TXT.equals(rhs.getType())) {
			lhs.txt = new String(rhs.getTxt());
		} else if(EugeneConstants.TXTLIST.equals(rhs.getType())) {
			lhs.txtList = (ArrayList<String>)this.cloner.deepClone(rhs.getTxtList());
		} else if(EugeneConstants.BOOLEAN.equals(rhs.getType())) {
			lhs.bool = rhs.getBool();
		}
	}

	private void assignment(PropertyValue lhs, Variable rhs) {
		if(EugeneConstants.NUM.equals(rhs.getType())) {
			lhs.setNum(rhs.num);
		} else if(EugeneConstants.NUMLIST.equals(rhs.getType())) {
			lhs.setNumList(this.cloner.deepClone(rhs.numList));
		} else if(EugeneConstants.TXT.equals(rhs.getType())) {
			lhs.setTxt(new String(rhs.txt));
		} else if(EugeneConstants.TXTLIST.equals(rhs.getType())) {
			lhs.setTxtList(this.cloner.deepClone(rhs.txtList));
		} else if(EugeneConstants.BOOLEAN.equals(rhs.getType())) {
			lhs.setBool(rhs.bool);
		}
	}

	private void assignment(PropertyValue lhs, PropertyValue rhs) {
		if(EugeneConstants.NUM.equals(rhs.getType())) {
			lhs.setNum(rhs.getNum());
		} else if(EugeneConstants.NUMLIST.equals(rhs.getType())) {
			lhs.setNumList(this.cloner.deepClone(rhs.getNumList()));
		} else if(EugeneConstants.TXT.equals(rhs.getType())) {
			lhs.setTxt(new String(rhs.getTxt()));
		} else if(EugeneConstants.TXTLIST.equals(rhs.getType())) {
			lhs.setTxtList(this.cloner.deepClone(rhs.getTxtList()));
		} else if(EugeneConstants.BOOLEAN.equals(rhs.getType())) {
			lhs.setBool(rhs.getBool());
		}
	}
	
	public void updateElement(NamedElement ne) 
			throws EugeneException {
		
		System.out.println("[Interp.updateElement] -> "  + ne);
		
	}

	/**
	 * The getComponents/1 method returns a set of all 
	 * components of a given component type ct.
	 * 
	 * @param ct  ... the type of the components
	 * 
	 * @return ... a set of components of the given type
	 * 
	 * @throws EugeneException
	 */
	public Set<Component> getComponents(ComponentType ct) 
			throws EugeneException {
		return this.symbols.getComponents(ct);
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

		// if we're not in the GLOBAL scope,
		if(!this.stack.isEmpty()) {
			
			// then we look into all higher scopes 
			// if the requested element exists
			Stack<StackElement> tmp = new Stack<StackElement>();
			while(this.stack.size() > 0 && ne == null) {
				StackElement se = this.stack.pop();
				tmp.push(se);

				// we ask the stackelement if they contain 
				// the desired namedlement object
				if(se.contains(name)) {
					ne = se.get(name);
				}

				// if we've popped a function from the stack, 
				// then we do not pop further elements.
				if(se instanceof FunctionInstance) {
					break;
				}
			}

			/*
			 * we need to restore the old stack
			 */
			while(tmp.size() > 0) {
				this.stack.push(
						tmp.pop());
			}

		}
		
		// if the requested element does not exist in any 
		// scope above, then we check the global symbol 
		// tables and our Library Management System (LMS) Sparrow
		if(null == ne) {
			// first, we query the request element from 
			// the symbol tables
			ne = this.symbols.get(name);
	
			if(null == ne) {
				// if the request element does not exist in 
				// the LMS, then we retrieve it from the symbol tables
				try {
					ne = this.sparrow.getFact(name);
				} catch(SparrowException spe) {
					throw new EugeneException(spe.getMessage());
				}
	
			}	
		}
		
		// finally, we return the requested element
		// (could be NULL though)
		return ne;
	}
	
	public Variable getVariable(String name) 
			throws EugeneException {
		
		NamedElement ne = this.get(name);
		if(null == ne) { 
			throw new EugeneException(name + " is not declared.");
		} else if(!(ne instanceof Variable)) {
			throw new EugeneException(name + " is not a Variable.");
		}
		return (Variable)ne;
	}
		
	
	/**
	 * The checkIfDeclaredInScope/1 method checks if a 
	 * given identifier is declared in the current scope.
	 * This method is particularly important to avoid 
	 * duplicate declarations of the same identifier.
	 * 
	 * @param name   ... the name, i.e. the identifier
	 * @return       ... true ... if declard
	 *                  false ... otherwise
	 *                  
	 * @throws EugeneException
	 */
	public boolean checkIfDeclaredInScope(String name) 
			throws EugeneException {		
		if(!this.stack.isEmpty()) {
			return this.stack.peek().contains(name);
		}
		try {
			return this.symbols.contains(name) || this.sparrow.contains(name);
		} catch(SparrowException spe) {
			throw new EugeneException(spe.getLocalizedMessage());
		}
	}
	
	/**
	 * The contains/1 method checks if a given name has been declared.
	 * 
	 * @param name ... the name of the desired variable/component
	 * 
	 * @return true  ... if a NamedElement object of name name has been declard
	 *         false ... otherwise
	 */
	public boolean contains(String name) 
			throws EugeneException {

		boolean bContains = false;
		
		try {
			
			
			// if we're not in the global scope, 
			// then we scan the current stack elements for 
			// the requested name
			if(!this.stack.isEmpty()) {
				
				Stack<StackElement> tmp = new Stack<StackElement>();
				
				// we pop the elements from the stack until we've found the element (done via break)
				// or there's no more element left
				while(!this.stack.isEmpty()) {
					
					StackElement se = this.stack.pop();
					
					// we need to keep track of the popped elements
					// that is done via a temporary stack
					// i.e. the temporary stack will be the reverse of 
					// the current stack
					tmp.push(se);

					// then, we search the requested name 
					// in the stack's element symbol tables
					bContains = se.contains(name);
					
					// if we've popped a function from the stack, 
					// then we do not pop further elements.
					if(se instanceof FunctionInstance || bContains) {
						break;
					}
				}
				
				// since we were messing around with the stack, we 
				// need to rebuild it
				while(tmp.size() > 0) {
					this.stack.push(tmp.pop());
				}
			}
			
			// everything got popped from the stack and we still 
			// haven't found what we're looking for
			if(!bContains) {
				
				// then we search the global symbol tables for the element!
				bContains = this.symbols.contains(name) || this.sparrow.contains(name);
			}
			
			return bContains;
			
		} catch(SparrowException se) {
			// something went wrong.
			throw new EugeneException(se.getMessage());
		}
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
		
		if(ne instanceof EugeneContainer) {
			/*
			 * iterate over the elements and put them 
			 * individually into the symbol tables
			 */
			for(NamedElement e : ((EugeneContainer)ne).getElements()) {
				this.put(e);
			}
		} else {
			this.put(ne);
		}
		
	}

	/**
	 * The put/2 method stores a NamedElement object in the symbol tables.
	 * The NamedElement object will be stored under a specified name 
	 * that is usually equal to the name of the NamedElement object.
	 * 
	 * In the put/2 method, we also incorporate Scoping. That is, variables 
	 * and components defined within loops, branches, or functions are not 
	 * visible and accessible outside.
	 * 
	 * @param name  ... the name under that the NamedElement object should be stored
	 * @param ne    ... the NamedElement object
	 * 
	 * @throws EugeneException
	 */
	public void put(String name, NamedElement ne) 
			throws EugeneException {
		
		if(!this.stack.isEmpty()) {
			
			/*
			 * scoping
			 */
			
			// in this case, we store the loop's iteration variable 
			// into the symbol tables of the below stack element
			if(this.stack.peek() instanceof Loop &&
					((Loop)this.stack.peek()).getVarname().equals(name)) {
					
				StackElement se = this.stack.pop();
				se.put(ne);
				this.stack.push(se);

			} else {
				this.stack.peek().put(ne);
			}
		} else {
			/*
			 * MAIN scope
			 */
			this.symbols.put(name, ne);

			// in the MAIN scope, we also put
			// biological components and interactions 
			// into the the library (i.e. Sparrow)
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
		if(this.checkIfDeclaredInScope(name)) {
			throw new EugeneException(name +" exists already.");
		}

		NamedElement ne = null;
		if(null != device) {
			if(!this.contains(device)) {
				throw new EugeneException(device+" is not declared.");
			}
			
			ne = this.get(device);
			if(!(ne instanceof Device)) {
				throw new EugeneException(device+" is not a Device.");
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
	 * The createRuleBuilder/2 method instantiates the RuleBuilder class 
	 * with the given parameters (name and device) and returns the 
	 * created RuleBuilder object.
	 * 
	 * @param name    ... a String representing the name of the RuleBuilder object 
	 * @param device  ... a String representing the name of the device the RuleBuilder is defined on
	 *                    
	 * @return ... the RuleBuilder object
	 * 
	 * @throws EugeneException
	 */
	public void instantiateRuleBuilder(String name, String device) 
			throws EugeneException {
		
		/*
		 * first, we do some error checking 
		 */
		
		// does an element with the same name exist?
		if(this.checkIfDeclaredInScope(name)) {
			throw new EugeneException(name +" exists already.");
		}

		NamedElement ne = null;
		if(null != device) {
			// does an element with the device name exist?
			if(!this.contains(device)) {
				throw new EugeneException(device+" is not declared.");
			}
			
			// is the device a device?
			ne = this.get(device);
			if(!(ne instanceof Device)) {
				throw new EugeneException(device+" is not a Device.");
			}
		}
		
		if(null == this.ruleBuilder) {
			this.ruleBuilder = new RuleBuilder();
		}
		
		Rule r = this.ruleBuilder.instantiate(name, (Device)ne);
		
        // if everything went fine, then we put the RuleBuilder
        // object into the symbol-tables for later retrieval.
        this.put(r);
	}
	
	/**
	 * The andRule/2 method builds the logical conjunction of 
	 * a rule and the given predicate. Therefore, it takes as 
	 * input the name of the rule and the predicate that should 
	 * be logically conjugated with the rule. 
	 * If a rule with the specified name does not a exist, 
	 * then an exception will be thrown.
	 * 
	 * @param ruleName   ... the name of the rule
	 * @param p          ... the predicate
	 * 
	 * @throws EugeneException
	 */
	public void andRule(String ruleName, Predicate p) 
			throws EugeneException {
		Rule r = this.doErrorChecking(ruleName);
		this.ruleBuilder.and(r, p);
	}
	

	/**
	 * 
	 * @param lhs
	 * @param rhs
	 * @throws EugeneException
	 */
	public void andRule(String lhs, String rhs) 
			throws EugeneException {
		Rule lhsRule = this.doErrorChecking(lhs);
		Rule rhsRule = this.doErrorChecking(rhs);
		
		this.ruleBuilder.and(lhsRule, rhsRule);
	}

	private Rule doErrorChecking(String ruleName) 
			throws EugeneException {
		if(null == this.ruleBuilder) {
			this.ruleBuilder = new RuleBuilder();
		}
		
		// first, we retrieve the rule from the sybmol tables
		NamedElement e = this.get(ruleName);
		if(null == e) {
			throw new EugeneException(ruleName + " is not declared.");
		}
		if(!(e instanceof Rule)) {
			throw new EugeneException(ruleName +" is not a Rule.");
		}
		
		return (Rule)e;
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
		if(this.checkIfDeclaredInScope(name)) {
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
		// first, we evaluate if something with the same 
		// name is declared in the current scope.
		if(this.checkIfDeclaredInScope(name)) {
			throw new EugeneException(name+" exists already.");
		}
		this.push(new EugeneArray(name));
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
		
//		System.out.println("[Interp.addToContainer] -> " + ne.getClass() +" -> "+ne.getName());
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
    public NamedElement doMultDivOp(NamedElement LHS, NamedElement RHS, String op) 
    		throws EugeneException {
    	
    	if(null == this.executor) {
    		this.executor = new ExpressionExecutor();
    	}
    	
    	try {
    		return this.executor.doMultDivOp(RHS, LHS, op);
    	} catch(EugeneException ee) {
    		throw new EugeneException(ee.getLocalizedMessage());
    	}

    }

    
    // LHS + RHS  
    // the result is stored in the lhs
    public NamedElement doMinPlusOp(NamedElement RHS, NamedElement LHS, String op) 
    		throws EugeneException {
        
    	if(null == this.executor) {
    		this.executor = new ExpressionExecutor();
    	}
    	
    	try {
    		return this.executor.doMinPlusOp(RHS, LHS, op);
    	} catch(EugeneException ee) {
    		throw new EugeneException(ee.getLocalizedMessage());
    	}
    }
    
	
	/**********************************************************************
	 * IMPERATIVE LANGUAGE FEATURES
	 **********************************************************************/
	public void push(StackElement se) {
		this.stack.push(se);
	}
	
//	/**
//	 * The getScope/1 method returns the name of the scope 
//	 * in that the given NamedElement object has been declared.
//	 * i.e. we iterate through the Stack and return the 
//	 * NamedElement's first occurrence
//	 * 
//	 * @param ne
//	 * @return
//	 */
//	private String getScope(NamedElement ne) {
//		
//		String scope = null;
//		if(this.stack.isEmpty()) {
//			// stash the current stack
//			Stack<StackElement> tmp = new Stack<StackElement>();
//			while(!this.stack.isEmpty() && scope==null) {
//				
//				// if we've found the corresponding scope, 
//				// then we store its name
//				if(this.stack.peek().contains(ne.getName())) {
//					scope = this.stack.peek().getName();
//				}
//				tmp.push(this.stack.pop());
//			}
//			
//			// we also need to restore the original stack
//			while(!tmp.isEmpty()) {
//				this.stack.push(tmp.pop());
//			}
//		}
//		
//		if(scope == null) {
//			return "MAIN";
//		}
//		return scope;
// 	}
	
	/**
	 * The clear/0 method clears the symbol tables of the top stack element.
	 * 
	 * @throws EugeneException
	 */
	public void clear() 
			throws EugeneException {
		if(!this.stack.isEmpty()) {
			
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
	 * The cleanupFunctionStack/1 method pops elements from 
	 * the stack until we've reached the desired function
	 * 
	 * @param f   ... the function
	 * 
	 * @throws EugeneException
	 */
	public void cleanupFunction(FunctionInstance f) 
			throws EugeneException {
 
		if(!this.stack.isEmpty()) {
			
			StackElement se = null;
			// first, we pop everything from the stack
			// until we've reached the first occurrence of the function f
			while(!this.stack.peek().getName().equals(f.getName())) {
				se = this.stack.pop();
				
				// we clear the symbol tables of the popped element
				se.clear();
			}

			// then, we also pop the function
			se = this.stack.pop();
			// and clear its symbol tables
			se.clear();
		}
		
		f = null;
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
		
		if(this.stack.isEmpty()) {
			this.symbols.removeVariable(varname);
		} else {
			if(this.stack.peek() instanceof ImperativeFeature) {
				((ImperativeFeature)this.stack.peek()).remove(varname);
			}
		}
	}
	
	/**
	 * The updateElement/3 method updates a NamedElement object with a given 
	 * name in a given scope with a new NamedElement object.
	 * 
	 * @param old_el ... the old NamedElement
	 * @param scope  ... the scope of the old NamedElement
	 * @param new_el ... the new NamedElement
	 */
	public void updateElement(String old_el_name, String scope, NamedElement new_el) 
			throws EugeneException {
		
		if("MAIN".equals(scope)) {
//			System.out.println("replacing " + old_el_name +" with " + new_el);
			this.symbols.removeVariable(old_el_name);
			new_el.setName(old_el_name);
			this.symbols.put(new_el);
			
//			System.out.println("replaced! " + this.symbols.get(old_el_name));
		} else {
		
			// first, iterate over the stack
			Enumeration<StackElement> e = this.stack.elements();
			while(e.hasMoreElements()) {
				StackElement se = e.nextElement(); 
				if(se.equals(scope) && se.contains(old_el_name)) {
					if(se instanceof ImperativeFeature) {
						((ImperativeFeature)se).remove(old_el_name);
					}
					se.put(new_el);
				}
			}
		}
	}
	
	
	/**
	 * The evaluateCondition/3 compares the LHS with the RHS 
	 * according to the given operator op.
	 * The evaluateCondition/3 returns true if the condition is 
	 * satisfied, false otherwise.
	 * 
	 * @param lhs  ... the LHS NamedElement
	 * @param op   ... the comparison operator
	 * @param rhs  ... the RHS NamedElement
	 * 
	 * @return true ... iff the condition is satisfied
	 *         false .. otherwise
	 *         
	 * @throws EugeneException
	 */
	public boolean evaluateCondition(NamedElement lhs, String op, NamedElement rhs) 
			throws EugeneException {
	
		if(null==lhs) {
			throw new EugeneException("The LHS of a condition cannot be NULL!");
		}
		if(null==rhs) {
			throw new EugeneException("The RHS of a condition cannot be NULL!");
		}
		
		// lazy evaluation
		// since not all scripts will use Eugene's imperative features. 		
		if(null == this.comparator) {
			this.comparator = new Comparator();
		}
		
		/*
		 * compare the types
		 */
		if(this.comparator.compareTypes(lhs, rhs)) {
			/*
			 * comparing variables
			 */
			return this.comparator.evaluateCondition(lhs, op, rhs);

		}
		
		return false;
	}

	/*-------------------------------------------------
	 * METHODS REGARDING DATE EXCHANGE FEATURES
	 *-------------------------------------------------*/
	private String getRootDirectory() {
		return this.ROOT_DIRECTORY;
	}
	
	
	/**
	 * The dataExchange/1 method is being invoked iff the Eugene 
	 * user imports data from a data standard file and does not 
	 * assign the imported data to a variable.
	 * 
	 * @param e ... the imported data
	 * 
	 * @throws EugeneException
	 */
	public void recursiveStoringOf(NamedElement e) 
			throws EugeneException {
		
		if(e instanceof EugeneContainer) {
			for(NamedElement el : ((EugeneContainer)e).getElements()) {
				this.recursiveStoringOf(el);
			}

		// in case it's a EugeneArray, then we store every single
		// element of the collection in the symbol tables
		} else if(e instanceof Device) {
			for(NamedElement el : ((Device)e).getComponentList()) {
				this.recursiveStoringOf(el);
			}
		
		// otherwise, we just put the NamedElement object 
		// into the symbol tables
		} else { 
			this.put(e);
		}
		
		try {
			if(!this.sparrow.contains(e.getName())) {
				this.sparrow.insertFact(e);
			}
		} catch(SparrowException se) {
			throw new EugeneException(se.getMessage());
		}
	}
 
	/**
	 * The include/1 method includes an external Eugene script 
	 * and interprets it on-the-fly.
	 * 
	 * @param file  ... The file to be included
	 * 
	 * @throws EugeneException
	 */
	public void includeFile(String file, ParsingPhase phase) 
			throws EugeneException {

		if(phase == ParsingPhase.INTERPRETING) {
			if(null == this.includedFiles) {
				this.includedFiles = new HashSet<String>();
			}
	
			// if the file has been included already, 
			// then there's no need to include it again.
			if(this.includedFiles.contains(file)) {
				return;
			}
			
			
			// if the file has not been included,
			// then we store the filename in the includedFiles set
			this.includedFiles.add(file);
		}
		
		// first, we read the file's content
		String script = this.readFileContent(file);
		
		/*
		 * lexical analysis and parsing
		 */
		EugeneLexer lexer = new EugeneLexer(new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		EugeneParser parser = new EugeneParser(tokens);

		/*
		 * initialize the with this interpreter
		 */
		parser.init(this, this.getWriter(), phase);	
		parser.setFilename(file);
		
		try {
			switch(phase) {
			case PRE_PROCESSING:
				parser.prog(true);
				break;
				
			case INTERPRETING:
				parser.prog(false);
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	/**
	 * The importFile/1 method imports all biological data 
	 * specified in an external Eugene script. 
	 * 
	 * @param file  ... The file to import
	 * @return      ... a NamedElement object (usually a 
	 *                  EugeneContainer that contains all data
	 *                  specified in the external Eugene script)
	 * @throws EugeneException
	 */
	public NamedElement importFile(String file) 
			throws EugeneException {

		String script = this.readFileContent(file);
		
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
					new Interp(new Sparrow(), this.getWriter(), this.getRootDirectory()), 
					this.getWriter(),
					ParsingPhase.INTERPRETING);
		} catch(SparrowException spe) {
			throw new EugeneException(spe.toString());
		}
		parser.setFilename(file);
		
		try {
			parser.prog(false);
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
	
	/*
	 * SBOL Import
	 */ 
	public NamedElement importSBOL(String file) 
			throws EugeneException {
		
		file = this.getFileWithRootPathInformation(file);
		
		Set<NamedElement> elements = SBOLImporter.importSBOL(file);
		if (null != elements && !elements.isEmpty()) {
			
			if(elements.size() > 0) {
			
				// then, we return a Eugene collection
				EugeneCollection ec = new EugeneCollection(null);
				for(NamedElement element : elements) {
					ec.getElements().add(element);
				}
				
				return ec;
			} else {
				// otherwise, we return the only element
				for(NamedElement element : elements) {
					return element;
				}
			}
		}
		
		return null;
	}

	/*
	 * SBOL EXPORT
	 */ 
	public void exportToSBOL(String sName, String file)
			throws EugeneException {

		// augment the file/path name with the current
		// ROOT folder information
		file = this.getFileWithRootPathInformation(file);

		NamedElement objElement = this.get(sName);
		if (objElement == null) {
			throw new EugeneException(
					"NULL is not SBOL compliant!");
		} else if (objElement instanceof EugeneContainer) {
			SBOLExporter.serialize((EugeneContainer) objElement, file);
		} else if (objElement instanceof Component) {
			SBOLExporter.serialize((Component) objElement, file);
		} else {
			throw new EugeneException(
					"I cannot export the "
							+ sName
							+ " element to SBOL! "
							+ "Only collections, arrays, devices, and parts are allowed!");
		}
	}
	
	/*
	 * SBOL VISUAL -- PIGEON
	 */ 
	public Collection<URI> visualizeSBOL(NamedElement element, Variable filename) 
			throws EugeneException {
		
		if(null == element) {
			throw new EugeneException("Invalid element to visualize!");
		}
		
		
		/*
		 * interpret the filename
		 */
		String file = this.interpretFilename(filename);
		
		Collection<URI> ret_uris = new HashSet<URI>();
		
		/*
		 * retrieve the object from the this.symbols
		 */
		if(!(element instanceof Component) && !(element instanceof EugeneContainer)) {
			throw new EugeneException("I cannot visualize "+element+"!");
		}

		if(null == this.pigeon) {
			this.pigeon = new Pigeonizer();
		}
		
		URI uri = null;
		if(element instanceof Device) {
			List<URI> uris = new ArrayList<URI>(1);
			uri = this.pigeon.pigeonizeSingle((Device)element, null);
			
			if(null != uri) {
				/*---------------------------
				 * for testing, open the URI
				 *---------------------------*/
//				WeyekinPoster.launchPage(uri);
				
				uris.add(uri);
			}
			
			ret_uris.add(
					this.toSerializedImage(
							uris, file));

		} else if(element instanceof EugeneContainer) {
			
			List<URI> uris = new ArrayList<URI>(50);
			int i = 0; 
			for(NamedElement e : ((EugeneContainer)element).getElements()) {
				
				if(e instanceof Device) {
					i++;
					
					if(i % 50 == 0) {
						
						ret_uris.add(
								this.toSerializedImage(
										uris, file));
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
								uris, file));
			}
			
		}
		
		return ret_uris;
	}
	
	/**
	 * The interpretFilename(Variable) method gets as input 
	 * a Variable object that ideally contains the filename
	 * of the SBOL-Visual picture.
	 * If the Variable object is NULL, then we generate 
	 * a random filename --- ending with .PNG -- and 
	 * store it in the ROOT_DIRECTORY + IMAGES_DIRECTORY.
	 * If the Variable object is NOT NULL, then it must 
	 * be a TXT variable with non-empty value. Otherwise 
	 * an exception is thrown.
	 * 
	 * @param v  ... a Variable object representing the filename 
	 * of the SBOL-Visual compliant picture
	 * 
	 * @return ... the filename
	 * 
	 * @throws EugeneException
	 */
	private String interpretFilename(Variable v)
			throws EugeneException {
		
		// check if the Variable object is null
		if(null == v) {
			
			// default filename:
			// ROOT_DIRECTORY + IMAGES_DIRECTORY + randomly 
			// generate filename with suffix .PNG
			return Eugene.ROOT_DIRECTORY+"/"+
					Eugene.IMAGES_DIRECTORY+"/"+
					UUID.randomUUID()+".png";
			
		// if the Variable is not null, then it must 
		// be of type TXT, and does not have an empty value
		} else if(EugeneConstants.TXT.equals(v.getType()) &&
				null != v.getTxt() &&
				!v.getTxt().isEmpty()) {
		
			// return the TXT value
			return v.getTxt();
		}
		
		// throw an Exception
		throw new EugeneException("Invalid filename specified.");
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
	
	public NamedElement importGenbank(String filename) 
			throws EugeneException {
		
		if(filename.startsWith("\"")) {
			filename = filename.substring(1, filename.length());
		} 
		if(filename.endsWith("\"")) {
			filename = filename.substring(0, filename.length() - 1);
		}
		
		if(null == this.genbanker) {
			this.genbanker = new GenbankImporter(this.symbols);
		}
		
		try {
			this.genbanker.importGenbank(filename);
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getLocalizedMessage());
		}
		
		return null;
	}
	
	public NamedElement importRegistry(String partId) 
			throws EugeneException {
        // remove the double quotas
		
		try {
			this.sparrow.importFrom(partId, Repository.iGEM);
		} catch(SparrowException spe) {
			throw new EugeneException(spe.getLocalizedMessage());
		}
		
		return null;
		
//        if(null == this.registryImporter) {
//            this.registryImporter = new SBOLRegistryImporter();
//        }
//        
//        try {
//            List<Component> lst = this.registryImporter.importComponent(partId);
//            if(null!=lst && !lst.isEmpty()) {
//                for(Component component : lst) {
//                    this.put(component);
//                }
//            } else {
//                throw new EugeneException("Cannot import " + partId + "!");
//            }
//        } catch(EugeneException ee) {
//            throw new EugeneException(ee.getLocalizedMessage());
//        }
	}

	
	/**
	 * The readFileContent/1 method gets as input a filename
	 * and returns the file's content represented in a String object
	 * 
	 * @param file  ... the file to be read
	 * @return ... the file content
	 * 
	 * @throws EugeneException
	 */
	private String readFileContent(String file) 
			throws EugeneException {
		
		file = getFileWithRootPathInformation(file);
		
		String script = null; 
		try {
			script = FileUtils.readFile(new File(file));
		} catch(IOException ioe) {
			throw new EugeneException("I cannot read the file " + file);
		}
		
		return script;
	}
	
	/**
	 * The getFileWithPathInformation/1 method gets as input 
	 * a filename and augments it with the path information store
	 * in the ROOT_DIRECTORY member variable.
	 * 
	 * @param file ... the file name (as String)
	 * @return  ... a string containing file information 
	 *              including PATH and filename
	 *              
	 * @throws EugeneException
	 */
	private String getFileWithRootPathInformation(String file) 
			throws EugeneException {
		
		// NULL filename
		if(null == file) {
			throw new EugeneException("Invalid file name!");
		}
		
		// checking and removing double-quotas
		if(file.startsWith("\"") && file.endsWith("\"")) {
			file = file.substring(1, file.length() - 1);
		}
		
		// empty file name, example:
		// include ""
		if(file.isEmpty()) {
			throw new EugeneException("File is empty!");
		}
		
		// put Eugene's ROOT directory in front of 
		// the specified INCLUDE file
		if(!(".".equals(this.getRootDirectory()))) {
			file = this.getRootDirectory()+"/"+file;
		}

		return file;
	}

	/*-------------------------------------------------
	 * METHODS REGARDING USER-DEFINED FUNCTIONS
	 *-------------------------------------------------*/
	
	/**
	 * The defineFunction/4 method takes as input all relevant information for 
	 * a Eugene function, i.e. return type, name, list of parameters, and the function's 
	 * statements.
	 * It creates a org.cidarlab.eugene.dom.functions.Function object and stores 
	 * it in the symbol tables. 
	 * 
	 * @param return_type   ... the return type of the function (must be a reserved keyword for Eugene types)
	 * @param name          ... the name of the function
	 * @param parameters    ... a list of NamedElement objects which represent the function's parameters 
	 * @param statements    ... the statements of the function body
	 * 
	 * @throws EugeneException
	 */
	public void defineFunction(String return_type, String name, List<NamedElement> parameters, Token statements, TokenStream tokenstream) 
			throws EugeneException {
		
		// first, we evaluate if the function has been 
		// defined already
		if(this.symbols.containsFunction(name)) {
			throw new EugeneException("The function " + name + " has been defined already!");
		}
		
		// if the function has not been defined, then we instantiate the 
		// Function class and set its variables properly
		FunctionPrototype fp = 
				new FunctionPrototype(
						return_type,    // the function's return type 
						name,           // the function's name
						parameters,     // the function's list of parameters
						statements,     // the function's statements ("Function Body")
						tokenstream,    // the stream of tokens in that the function exists
						this.symbols);  // a reference to the global symbol tables
		
		
		// finally, we store the Function object in the symbol tables
		this.symbols.putFunction(fp);
	}
	
	/**
	 * The createFunctionParameter/2 method takes as input a type-name pair representing
	 * the name and the type of a Function parameter. It checks if the type is valid, 
	 * creates a NamedElement object (depending on the type), and returns the 
	 * NamedElement object.
	 * 
	 * @param type  ... the type of the parameter
	 * @param name  ... the name of the parameter
	 * 
	 * @return
	 * @throws EugeneException
	 */
	public NamedElement createFunctionParameter(String type, String name)
			throws EugeneException {
		
		// currently, we support the following types
		// num
		if(EugeneConstants.NUM.equals(type)) {
			return new Variable(name, EugeneConstants.NUM);
		// txt
		} else if(EugeneConstants.TXT.equals(type)) {
			return new Variable(name, EugeneConstants.TXT);
		} else if(EugeneConstants.NUMLIST.equals(type)) {
			return new Variable(name, EugeneConstants.NUMLIST);
		} else if(EugeneConstants.TXTLIST.equals(type)) {
			return new Variable(name, EugeneConstants.TXTLIST);
		} else if(EugeneConstants.BOOLEAN.equals(type)) {
			return new Variable(name, EugeneConstants.BOOLEAN);
		}
		
		// invalid type! -> throw an exception
		throw new EugeneException("Unsupported type for function parameter!" + type);
	}
	
	/**
	 * The executeFunction/2 method gets as input the name of the function that 
	 * is being called and a list of parameter values (lopv). The executeFunction/2
	 * instantiates the FunctionPrototype (if it exists) with the given 
	 * parameter values.
	 * 
	 * @param name ... the name of the function prototype
	 * @param lopv ... a list of parameter values passed to the function
	 * 
	 * @return an instance of the Function prototype ready for execution.
	 */
	public FunctionInstance instantiateFunction(String name, List<NamedElement> lopv) 
			throws EugeneException {
		FunctionPrototype fp = this.symbols.getFunction(name);
		if(fp != null) {
			
            // then, we compare the types of the function parameters 
            // with the types of the parameter values
			try {
				this.compareParameterTypes(fp.getParameters(), lopv);
			} catch(EugeneException ee) {
				throw new EugeneException(ee.getLocalizedMessage());
			}

            // if everything's fine at this point in time, then we 
            // instantiate the function by initializing its parameters with 
			// the specified parameter values (if there are any)
			try {
				return fp.instantiate(lopv);
			} catch(EugeneException ee) {
				throw new EugeneException(ee.getLocalizedMessage());
			}
		}
		
		throw new EugeneException("The function  " + name + " has not been defined!");
	}

	public void printFunctions() {
		Collection<FunctionPrototype> fs = this.symbols.getFunctions();
		for(FunctionPrototype f : fs) {
			System.out.println(f);
		}
	}
	
	/**
	 * The isReturnAllowed/0 method evaluates if the occurrence 
	 * of a RETURN statement is valid or not. 
	 * 
	 * A RETURN statement can only occur, iff a Function object is on the 
	 * stack.
	 * 
	 * @return  true ... the RETURN statement is valid
	 *         false ... otherwise
	 */
	public boolean isReturnAllowed() {
		if(!this.stack.isEmpty()) {
			Enumeration<StackElement> els = this.stack.elements();
			StackElement se = null;
			while((se = els.nextElement()) != null) {
				if(se instanceof FunctionInstance) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * The compareParameterTypes/2 compares the types of a function's parameters
	 * with the types of parameter values specified in a function call statement.
	 * It throws a EugeneException if any types do not match.
	 *  
	 * @param params        ... the parameters of the function
	 * @param param_values  ... the parameter values of the function call
	 * 
	 * @throws EugeneException
	 */
	public void compareParameterTypes(List<NamedElement> params, List<NamedElement> param_values)
			throws EugeneException {

		if(params != null && null != param_values) {
			
			// for the time being, both lists must be of equal size
			// i.e. there are no default-values for parameters
			if(params.size() != param_values.size()) {
				throw new EugeneException("The number of parameter values does not " +
						"match the number of paramters!");
			}
			
			// lazy evaluation of Comparator object
			if(null == comparator) {
				this.comparator = new Comparator();
			}
			
			// we check each parameter individually 
			// using the Comparator class
			for(int i=0; i<params.size(); i++) {
				
				if(!this.comparator.compareTypes(
						params.get(i), 
						param_values.get(i))) {
					
					// if the types do not match, we throw an 
					// exception with a pretty expressive 
					// error message.
					throw new EugeneException(
							"Incompatible types for parameter " + 
									params.get(i).getName());
				}
				
			}
			
		} else if(!(params == null && param_values == null)) {			
			throw new EugeneException("Invalid number of parameter values of " +
					"function call!");
		}
	} 
	
	/**
	 * The cleanUp/0 method cleans up the entire mess 
	 * caused by the Interpreter 
	 */
	public void cleanUp() {
		
		// clear the symbol tables
		if(null != this.symbols) {
			this.symbols.clear();
			this.symbols = null;
		}
		
		
		// clean up the stack
		// in theory, the stack should be empty ?!
		if(null != this.stack && !this.stack.isEmpty()) {
			StackElement se = null;
			while((se = this.stack.pop()) != null) {
				se.clear();
			}
			
			this.stack.clear();
			this.stack = null;
		}
		
		// clean up the adapters for 
		// miniEugene and sparrow
		this.meAdapter = null;
		this.spAdapter = null;
		
		// cleanup sparrow
		if(null != this.sparrow) {
			try {
				this.sparrow.clear();
			} catch(SparrowException spe) {
				// ignore this one
			}
			this.sparrow = null;
		}
		
		
		
	}
}
