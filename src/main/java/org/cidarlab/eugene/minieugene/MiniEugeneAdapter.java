package org.cidarlab.eugene.minieugene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.exception.EugeneException;
//import org.cidarlab.eugene.interp.SymbolTable;
import org.cidarlab.eugene.interp.Interp;
import org.cidarlab.minieugene.MiniEugene;

public class MiniEugeneAdapter {

	private static final int MAX_NR_OF_SOLUTIONS = 50000;
	
	/*
	 * the Eugene 2 MiniEugene compiler
	 */
	private Eugene2MiniEugeneCompiler compiler;
	
	/*
	 * an instance of MiniEugene
	 */
	private MiniEugene me;
	
	/*
	 * a reference to the Interpreter
	 */
	private Interp interp;
	
	public MiniEugeneAdapter(Interp interp) {		
		this.interp = interp;

		this.me = new MiniEugene();
		this.compiler = new Eugene2MiniEugeneCompiler(this.interp);		
	}

	/**
	 * 
	 * @param d            ... the device template
	 * @param rules        ... the rules on the device
	 * @param components   ... the set of selected components
	 * @param interactions ... a set of interactions
	 * 
	 * @return
	 * 
	 * @throws EugeneException
	 */
	public List<Device> product(
			Device d, 
			List<Rule> rules, 
			Set<org.cidarlab.eugene.dom.Component> components,
			Set<Interaction> interactions) 
		throws EugeneException {

		/*
		 * let's do some error checking
		 */
		if(null == d || d.getComponents() == null || d.getComponents().isEmpty()) {
			throw new EugeneException("Cannot use product() on an empty device.");
		}

		List<Device> solutions = new ArrayList<Device>();

		if(!rules.isEmpty()) {
			/*
			 * since we use DNF, we need to solve the problem for every rule
			 * and then union the results
			 */
			for(Rule rule : rules) {
				Set<Device> sod = this.execute(d, rule, components, interactions);
				if(null != sod && !sod.isEmpty()) {
					solutions.addAll(sod);
				}
			}
		} else {
			Set<Device> sod = this.execute(d, null, components, interactions);
			if(null != sod && !sod.isEmpty()) {
				solutions.addAll(sod);
			}
		}
		
		/*
		 * STEP 3: TRANSFORMATION of the miniEugene solutions 
		 *         to Eugene Device objects and 
		 *         return the resulting list
		 */
		return solutions;
	}
	
	private Set<Device> execute(
			Device d, 
			Rule rule, 
			Set<org.cidarlab.eugene.dom.Component> components,
			Set<Interaction> interactions) 
			throws EugeneException {
		
		/*
		 * STEP 1: enumerate all devices if the Device is
		 *         composed hierarchically
		 */
//		if(null == eg) {
//			this.eg = new EugeneGrammar();
//		}
//		
//		List<Device> lod = eg.enumerateAll(d, components);
//		
		/*
		 * STEP 2: COMPILATION to miniEugene script
		 */
		String meScript = this.compiler.compile(d, rule, components, interactions);
		
	
		// if it's a permutation, then 
		// we need to add the CONTAINS and ORIENTATION constraints
		// into the miniEugene script.
		if(null != Interp.PERMUTE_STRING) {
			meScript += Interp.PERMUTE_STRING.toString();
		}
		
//		System.out.println(meScript);
		
		/*
		 * STEP 3: EXECUTE miniEugene script
		 *         i.e. constraint solving
		 * 
		 * for the time being, 
		 * we always ask for MAX_NR_OF_SOLUTIONS solutions
		 */
		try {		
			me.solve(meScript, MAX_NR_OF_SOLUTIONS);		
		} catch(Exception ee) {
			throw new EugeneException(ee.getMessage());
		}
		
		return this.convertToEugene(d.getName(), me.getSolutions());
	}

	 /**
	  * convert the miniEugene solutions into a list of Device objects
	  * 
	  * @param name
	  * @param solutions
	  * @return
	  */
	private Set<Device> convertToEugene(String name, List<org.cidarlab.minieugene.dom.Component[]> solutions) {

		Set<Device> sod = new HashSet<Device>(solutions.size());
		int i=1;
		for(org.cidarlab.minieugene.dom.Component[] solution : solutions) {
			Device d = new Device(name+"_"+i);
			

			for(int k=0; k<solution.length; k++) {
				
				org.cidarlab.eugene.dom.Component c = null;
				
				try {
					c = (org.cidarlab.eugene.dom.Component)this.interp.get(solution[k].getName());
				} catch(EugeneException ee) {
					ee.printStackTrace();
				}
				
				/*
				 * component
				 */
				List<org.cidarlab.eugene.dom.NamedElement> lst = new ArrayList<org.cidarlab.eugene.dom.NamedElement>();
				lst.add(c);
				d.getComponents().add(lst);
				
				/*
				 * the component's orientation
				 */
				List<Orientation> orientations = new ArrayList<Orientation>();
				if(solution[k].isForward()) {
					orientations.add(Orientation.FORWARD);
				} else {
					orientations.add(Orientation.REVERSE);
				}
				d.getOrientations().add(orientations);
			}
			
			/*
			 * add the enumerated device to the list of devices
			 */
			sod.add(d);
			
			i++;
		}

		return sod;
	}
	

}
