package org.cidarlab.eugene.sparrow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.rules.*;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.rule.SparrowQuery;
import org.cidarlab.sparrow.rule.SparrowRule;

public class SparrowAdapter {

	private Sparrow sparrow;
	private Eugene2SparrowCompiler compiler;
	
	public SparrowAdapter(Sparrow sparrow) {
		this.sparrow = sparrow;
		
		this.compiler = new Eugene2SparrowCompiler();
	}
	
	public Set<Part> queryParts(Rule rule) 
			throws EugeneException {
		
		/*
		 * first, we compile the rule into sparrow queries
		 */
		List<SparrowQuery> queries = null;
		
		try {
			queries = this.compiler.compileQuery(rule);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.toString());
		}

//		System.out.println(queries);
		
		/*
		 * then, we execute the query
		 */
		Set<Part> sop = new HashSet<Part>();
		try {
			
			for(SparrowQuery query : queries) {
				Set<Component> components = this.sparrow.query(query);
				for(Component component : components) {
					if(component instanceof Part) {
						sop.add((Part)component);
					}
				}
			}
			
			return sop;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	public Set<Component> queryComponents(List<Rule> rules) 
			throws EugeneException {

		Set<Component> components = new HashSet<Component>();		

		for(Rule rule : rules)  {
			
			System.out.println("[SparrowAdapter] -> " + rule);
			components.addAll(
					this.queryParts(rule));
			
			System.out.println(components);
		}
		
		return components;
	}
	
	public List<Device> prune(List<Device> devices, List<Rule> rules) 
			throws EugeneException {
		
		if(null == rules || rules.isEmpty()) {
			return devices;
		}
		
		/*
		 * first, let's check if we have to do a prune
		 */
		List<Rule> lor = null;
		for(int i=0; i<rules.size(); i++) {
				
			/*
			 * we only prune if the rule has at least 
			 * one expression constraint in it  
			 */				
			if(rules.get(i).hasExpression()) {
				
				if(null == lor) {
					lor = new ArrayList<Rule>();
				}
				lor.add(rules.get(i));
			}
		}

		if(null != lor) {
			return this.doPrune(devices, lor);
		}
		
		return devices;
	}
	
	private List<Device> doPrune(List<Device> devices, List<Rule> rules) 
			throws EugeneException { 
		List<Device> devs = new ArrayList<Device>();

		try {

			Sparrow sp = new Sparrow();
			
			/*
			 * first, we insert all devices into the WM
			 */
			for(Device d : devices) {
				sp.insertFact(d);
//				this.sparrow.insertFact(d);
			}
			
			/*
			 * then, we compile the Eugene rules into Sparrow rules
			 */
//			sp.fireRules("expression-rules-03.drl");
//			this.sparrow.fireRules("expression-rules-03.drl");
			
			for(Rule rule : rules) {
				
				/*
				 * we only prune if the rule has at least 
				 * one expression constraint in it  
				 */
				
				SparrowRule sp_rule = this.compiler.compilePrune(rule);
//				System.out.println(sp_rule);
				if(null != sp_rule) {
					devs.addAll(sp.prune(sp_rule));
				}
			}

		} catch(SparrowException se) {
			se.printStackTrace();
			throw new EugeneException(se.toString());
		}
		
		return devs;
	}	
}
