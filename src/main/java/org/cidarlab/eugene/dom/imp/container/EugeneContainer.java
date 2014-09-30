package org.cidarlab.eugene.dom.imp.container;

import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.imp.StackElement;

import java.util.Collection;

/**
 * All elements defined in a container are only visible 
 * within the container. Hence, Eugene containers do need scoping, 
 * and therefore we inherit containers from the StackElement class. 
 * 
 * @author Ernst Oberortner
 */
public abstract class EugeneContainer  
	extends StackElement {

	private static final long serialVersionUID = 8107705739127791367L;

	public EugeneContainer(String name) {
		super(name);
	}

	/*
	 * abstract methods
	 */
	public abstract Collection<NamedElement> getElements();
}
