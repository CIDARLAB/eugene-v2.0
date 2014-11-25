package org.cidarlab.eugene.grammar;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;

/**
 * Eugene supports hierarchical composition of devices. In order to 
 * enumerate all rule-compliant devices, we utilize context-free grammars.
 * 
 * A context-free grammar is formally defined as the quatruple
 * G := <NT, T, P, S>
 * 
 * NT ... non-terminals (i.e. Devices and Part Types)
 * T  ... terminals     (i.e. Parts)
 * P  ... production rules  (i.e. LHS -> RHS)
 * S  ... the start symbol (i.e. the name of the hierarchically compose device).
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneGrammar {

	private Set<String> visited;
	private Map<String, List<Symbol>> parts;
	
	/*
	 * A list which holds all enumerated devices
	 */
	private List<Device> lod;
	
	
	/**
	 * The isComposed/1 method checks if a given Device d 
	 * is hierarchically composed
	 * 
	 * @param d  ... the Device to be checked
	 * 
	 * @return  true ... the Device is hierarchically composed
	 *         false ... otherwise
	 */
	public boolean isComposed(Device d) {
		if(null != d && null != d.getComponents() && !d.getComponents().isEmpty()) {
			for(List<NamedElement> loe : d.getComponents()) {
				if(null != loe && !loe.isEmpty()) {
					for(NamedElement e : loe) {
						if(e instanceof Device) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 *  The enumerateAll/1 method returns a list of all enumerated Devices 
	 *  of a given Device d. If the given Device d is not hierarchically composed, 
	 *  then the returned list only contains the given Device d.
	 *
	 * @param d  ... the device
	 * @param components  ... a set of components which represents the terminal symbols
	 * @return
	 * @throws EugeneException
	 */
	public List<Device> enumerateAll(Device d, Set<org.cidarlab.eugene.dom.Component> Ts) 
			throws EugeneException {

		/*
		 * initializations
		 */
		// the list of returned devices
		this.lod = new ArrayList<Device>();
		
		// if the given device d is not hierarchically composed, 
		// then the list of enumerated devices only contains 
		// the given Device d.
		if(!this.isComposed(d)) {
			lod.add(d);
			return lod;
		}
		
		// if the given device is hierarchically composed, then 
		// we first initialize the productions hashmap.
		this.buildsPartsMap(Ts);

		
		this.visited = new HashSet<String>();
		
		// the start-symbol
		Collection<Production> p = this.buildProductions(d);
		System.out.println(p);
		
		Grammar g = new Grammar(p, new Nonterminal(d.getName()));
		Iterator<Production> it = g.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		try {
			Parser parser = LL1ParserGenerator.createParser(g);
		} catch(Exception ee) {
			ee.printStackTrace();
		}
		
//		System.out.println("[Grammar.enumerateAll] -> " + this.productions);
//		this.traverseProductions(d.getName());
		
		/**
		int i=1;
		if(null != d && null != d.getComponents() && !d.getComponents().isEmpty()) {
			
			Device enumD = new Device(d.getName()+"_"+i);
			for(List<NamedElement> loe : d.getComponents()) {
				if(null != loe && !loe.isEmpty()) {
					List<NamedElement> subD_components = new ArrayList<NamedElement>(); 
					for(NamedElement e : loe) {
						if(e instanceof Device) {
						} else {
							subD_components.add(e);
							enumD.getComponents().add(subD_components);
						}
					}
					
					enumD.getComponents().add(subD_components);
				}
			}
			lod.add(enumD);
			i++;
			
		}	
		 **/	
		return lod;
	}
	

	private Collection<Production> buildProductions(Device d) {
		
		Set<Production> prods = new HashSet<Production>();
		
		/*
		 * non-terminal
		 */
		Nonterminal nt = new Nonterminal(d.getName());
		
		List<Symbol> ts = new ArrayList<Symbol>();
		for(List<NamedElement> loe : d.getComponents()) {
		
			for(NamedElement e : loe) {
				if(e instanceof Device) {
					
					ts.add(new Nonterminal(e.getName()));

					if(!this.visited.contains(e.getName())) {
						
						prods.addAll(
								this.buildProductions((Device)e));
						
						this.visited.add(e.getName());
					}
					
				} else if(e instanceof PartType) {
					Terminal pt = new Terminal(e.getName());
					ts.add(pt);
					
//					if(!this.visited.contains(e.getName())) {
//
//						Terminal pt = new Terminal(e.getName());
//						ts.add(pt);
//						
//						// mark the part type as visited
//						this.visited.add(e.getName());
//					}
					
				} else if(e instanceof Part) {
					ts.add(new Terminal(e.getName()));
				}
			}
		}
		
//		System.out.println("Terminals! -> " + ts);
		prods.add(new Production(nt, ts));
		return prods;
	}
	
	private void buildsPartsMap(Set<org.cidarlab.eugene.dom.Component> parts) {
		
		this.parts = new HashMap<String, List<Symbol>>();
		
		for(Component c: parts) {
			if(c instanceof Part) {
				if(!this.parts.containsKey(((Part)c).getType().getName())) {
					this.parts.put(((Part)c).getType().getName(), new ArrayList<Symbol>());
				}
				
				this.parts.get(((Part)c).getType().getName()).add(new Terminal(c.getName()));
			}
		}
	
	}
	
}
