package org.cidarlab.eugene.dom.imp.container;

import java.util.Collection;
import java.util.HashSet;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * A EugeneCollection is a container of unorder elements. 
 * A EugeneCollection can contain all kinds of NamedElements, 
 * such as parts, variables, types, as well as other 
 * Eugene containers (i.e. arrays and collections).
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneCollection 
		extends EugeneContainer {

	private static final long serialVersionUID = 3438297215191122244L;
	
	private Collection<NamedElement> elements;

	public EugeneCollection(String name) {
		super(name);
		this.elements = new HashSet<NamedElement>();
	}
	
	/**
	 * The getElements/0 method returns the entire 
	 * collection of named elements.
	 * 
	 * @return the collection of named elements
	 */
	public Collection<NamedElement> getElements() {
		return this.elements;
	}
		
	@Override
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("Collection ").append(this.getName()).append(" (").append(NEWLINE);
		for(NamedElement ne : this.getElements()) {
			sb.append(ne).append(NEWLINE);
		}
		sb.append(");");
		return sb.toString();
	}
	
	@Override
	public NamedElement get(String name) {
		if(null != this.getElements() && !this.getElements().isEmpty()) {
			for(NamedElement element : this.getElements()) {
				if(element.getName().equals(name)) {
					return element;
				}
			}
		}
		return null;
	}

	@Override
	public boolean contains(String name) {
		if(null != this.getElements() && !this.getElements().isEmpty()) {
			for(NamedElement element : this.getElements()) {
				if(element.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void put(NamedElement ne) 
			throws EugeneException {
		if(null != ne && !this.contains(ne.getName())) {
			if(ne instanceof NamedElement) {
				this.getElements().add((NamedElement)ne);
			} else {
				throw new EugeneException("I cannot add the "+ne+" element to a collection!");
			}
		} else if(null != ne && this.contains(ne.getName())) {
			throw new EugeneException("The " + ne.getName()+" element exists already in the "+this.getName()+" collection!"); 
		}
	}

	@Override
	public void clear() {
		this.getElements().clear();
	}
	
	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		if(null != this.getElements() && !this.getElements().isEmpty()) {
			for(NamedElement e : this.getElements()) {
				if(e.getName().equals(name)) {
					return e;
				}
			}
		}
		return null;
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		throw new EugeneException("A EugeneCollection does not support access through array indices!");
	}
}
