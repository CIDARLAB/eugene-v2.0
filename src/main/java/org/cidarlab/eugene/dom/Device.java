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

package org.cidarlab.eugene.dom;

import java.util.ArrayList;
import java.util.List;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.DeviceUtils;
import org.cidarlab.eugene.util.EugeneDeveloperUtils;
import org.cidarlab.eugene.util.EugeneUtils;
import org.cidarlab.eugene.util.SequenceUtils;

/**
 * 
 * A Device is a Composite Component. It can be composed of 
 * Devices, Types, and Parts.
 * 
 * @author Ernst Oberortner
 * 
 */
public class Device 
		extends Component {

	private static final long serialVersionUID = -2134313281573122775L;
	
	/*
	 * LIST OF (SUB)COMPONENTS
	 * 
	 * Since we offer the Selection operator for defining devices, 
	 * a device contains a list of lists of components.
	 *
	 * in an ``enumerated'' device (produced by the product/permute methods), 
	 * every list element consists of a list of only one component.
	 */
	private List<List<NamedElement>> components;
	
	/*
	 * LIST OF ORIENTATIONS
	 *
	 * the list of orientations keeps track of the orientation of the device's components.
	 * 
	 * The design rationale was the following:
	 * storing the orientation information in the device enables to have the components 
	 * only once in memory. 
	 * resultantly we can create more devices in memory instead of cloning the components 
	 * and store it to every device.
	 */
	private List<List<Orientation>> orientations;
	
	/*
	 * isValid flag
	 * 
	 * The isValid flag indicates if the device complies 
	 * with the specified rules. This flag is being utilized 
	 * by Sparrow in order to differentiate between functioning 
	 * and faulty devices.
	 * 
	 * When instantiating the Device class, the device is automatically 
	 * valid.
	 */
	private boolean isValid;
	
	/**
	 * 
	 * @param name
	 */
	public Device(String name) {
		super(name);
		this.setComponents(new ArrayList<List<NamedElement>>());
		this.setOrientations(new ArrayList<List<Orientation>>());
		this.setValid(true);
	}
	
	/**
	 * Since this constructor does not take information on orientations,
	 * every component will get an undefined orientation
	 * @param name ... the name of the device
	 * @param components ... the components of the device
	 */
	public Device(String name, List<List<NamedElement>> components) {
		super(name);
		this.setComponents(components);
		this.setOrientations(new ArrayList<List<Orientation>>());
		this.setValid(true);
	}
	

	/**
	 * 
	 * @param name ... the name of the device
	 * @param components ... the components of the device
	 * @param orientations ... the orientations of the device's components
	 */
	public Device(String name, List<List<NamedElement>> components, List<List<Orientation>> orientations) {
		super(name);
		this.setComponents(components);
		this.setOrientations(orientations);		
		this.setValid(true);
	}

	/**
	 * 
	 * @return a list of the device's components 
	 * (the list can be recursive, i.e. elements in the list can 
	 * be devices)
	 */
	public List<List<NamedElement>> getComponents() {
		return this.components;
	}
	
	public List<NamedElement> getComponents(int idx) 
		throws EugeneException {

		if(idx < 0 || idx >= this.getComponents().size()) {
			throw new EugeneException("The index must be >=0 and <="+(this.getComponents().size()-1)+".");
		}
		
		return this.getComponents().get(idx);
	}
	
	/**
	 * The getComponentsList/0 returns the device's components 
	 * as list.
	 * This method is needed for the ``Pruning'' phase of our 
	 * multistep design space exploration process.
	 * Hence, this method is invoked by the Drools rules. 
	 * And we need this method since we store a device's components 
	 * as a list of lists, which is needed for the selection operator 
	 * when specifying a device in Eugene.
	 * 
	 * @return the device's components as list
	 */
	public List<NamedElement> getComponentList() {
		List<NamedElement> components_list = new ArrayList<NamedElement>();
		for(List<NamedElement> loc : this.getComponents()) {
			components_list.add(loc.get(0));
		}
		return components_list;
	}
	
	/**
	 * The getComponent/1 method returns the device's sub-component 
	 * named after the provided name
	 * @param name
	 * @return
	 */
	public NamedElement getComponent(String name) 
			throws EugeneException {
		
		if(null == name || name.isEmpty()) {
			throw new EugeneException("No name of component provided.");
		}
		
		// search for the component
		for(List<NamedElement> components : this.getComponents()) {
			for(NamedElement component : components) {
				if(name.equals(component.getName())) {
					return component;
				}
			}
		}
		
		// component not found
		// => 
		return null;
	}
	
	/**
	 * set the list of component lists
	 * 
	 * @param lst ... the list of list of components
	 */
	public void setComponents(List<List<NamedElement>> lstComponents) {
		if(null == lstComponents) {
			this.components = new ArrayList<List<NamedElement>>();
		} else {
			this.components = lstComponents;
		}
	}
	
	/**
	 * 
	 * @param lst ... a list of components
	 * @param idx ... the index of the components list to be inserted or replaced
	 */
	public void setComponents(List<NamedElement> components, int idx) 
			throws EugeneException {
		if(idx < 0 || idx >= this.getComponents().size()) {
			throw new EugeneException("The index must be >=0 and <="+(this.getComponents().size()-1)+".");
		}

		this.getComponents().set(idx, components);
	}
	
	/**
	 * The addComponents method appends a given list of components 
	 * to the device's list of list of components
	 * @param components ... a list of components
	 * @throws EugeneException
	 */
	public void addComponents(List<Component> components) 
			throws EugeneException {
		List<NamedElement> loe = new ArrayList<NamedElement>();
		if(null != components && !components.isEmpty()) {
			for(Component c : components) {
				loe.add(c);
			}
			this.getComponents().add(loe);
		
			/*
			 * here, we also need to specify the components' orientations
			 */
			if(this.getOrientations().size() == this.getComponents().size() - 1) {
				List<Orientation> orientations = new ArrayList<Orientation>(components.size());
				for(Component c : components) {
					orientations.add(c.getOrientation());
				}
				this.addOrientations(orientations);
			}
		}
	}

	
	/*
	 * setters/getters for the isValid flag
	 */
	/**
	 * 
	 * @param isValid ... the valid status of this device
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	/**
	 * 
	 * @return the valid status of this device (either true or false)
	 */
	public boolean isValid() {
		return this.isValid;
	}
	
	
	/*
	 * methods for orientations
	 */
	/**
	 * 
	 * @return the list of lists of orientations
	 */
	public List<List<Orientation>> getOrientations() {
		return this.orientations;
	}
	
	/**
	 * 
	 * @param idx ... the required list of orientations at the idx-th position of the device
	 * @return    ... the list of orientations at the idx-th position of the device
	 * @throws EugeneException
	 */
	public List<Orientation> getOrientations(int idx) 
			throws EugeneException {
		
		if(idx < 0 || idx >= this.orientations.size()) {
			throw new EugeneException("The index must be >=0 and <="+(this.getOrientations().size()-1)+".");
		}
		
		return this.getOrientations().get(idx);
	}
	
	/**
	 * set the list of orientation lists
	 * 
	 * @param lst ... the list of list of orientations
	 */
	public void setOrientations(List<List<Orientation>> orientations) {
		if(null == orientations) {
			this.orientations = new ArrayList<List<Orientation>>();
		} else {
			this.orientations = orientations;
		}
	}
	
	/**
	 * The addOrientations method appends a given list of orientations 
	 * to the device's list of list of orientations
	 * @param orientations ... a list of orientations
	 * @throws EugeneException
	 */
	private void addOrientations(List<Orientation> orientations) 
			throws EugeneException {
		
		/*
		 * iff the list of orientations is one less than the list of components,
		 * then the addOrientations operation is allowed 
		 */
		if(this.getOrientations().size() == this.getComponents().size() - 1) {		
			this.getOrientations().add(orientations);
		} else {		
			throw new EugeneException("It is not allowed to add orientations w/o components!");
		}
	}

	@Override
	public int hashCode() {
		int hash = this.getName().hashCode();
		//for(Component c : this.components) {
		//	hash += c.hashCode();
		//}
		return hash;
	}
	
	@Override
	public String toString() {
		
//		try {
//			return EugeneUtils.prettyPrint(this);
//		} catch(EugeneException ee) {
//			ee.printStackTrace();
//		}
//		return null;

		StringBuilder sb = new StringBuilder();
		sb.append("Device ").append(this.getName()).append("(");
		
		if(this.getComponents().size() == 0) {
			sb.append(")");
			return sb.toString();
		}
		
		sb.append(EugeneDeveloperUtils.NEWLINE);

		for(int i = 0; i<this.getComponents().size(); i++) {
			
			boolean bDevice = false;
			
			sb.append("    ");
			if(this.getComponents().get(i).size() > 1) {
				
				// SELECTION
				sb.append("[");
				for(int j=0; j<this.getComponents().get(i).size(); j++) {
					
					if(null != this.getOrientations() && !this.getOrientations().isEmpty()) {
						if(this.getOrientations().get(i).get(j) == Orientation.FORWARD) {
							sb.append("+");
						} else if(this.getOrientations().get(i).get(j) == Orientation.REVERSE) {
							sb.append("-");
						}
					}						
					sb.append(this.getComponents().get(i).get(j)/*.getName()*/);
					
					if(j < this.getComponents().get(i).size() - 1) {
						sb.append("|");
					}
				}
				sb.append("]");
				
				// and this is readable for the real people
				// i.e. Computer Scientists
				if(i < this.getComponents().size() - 1) {
					sb.append(", ");
				} else {
					sb.append(")");
				}
				
			} else {

				if(null != this.getOrientations() && !this.getOrientations().isEmpty()) {
					if(this.getOrientations().get(i).get(0) == Orientation.FORWARD) {
						sb.append("+");
					} else if(this.getOrientations().get(i).get(0) == Orientation.REVERSE) {
						sb.append("-");
					}
				}
				
				if(this.getComponentList().get(i) instanceof Device &&
						this.getOrientations().get(i).get(0) == Orientation.REVERSE) {
					try {
						sb.append(EugeneUtils.flipAndInvert((Device)this.getComponentList().get(i)).toString());
					} catch(Exception e) {}
				} else {
					sb.append(this.getComponentList().get(i)/*.getName()*/);
					
				}

				if(this.getComponentList().get(i) instanceof Device) {
					// remove the two NEWLINE characters from the embedded device
					sb.setLength(sb.length() - 2);
					bDevice = true;
				}

				// this behavior is very particular to devices
				// i.e. non templates
				// should enhance readability for biologists ?!?
				if(i < this.getComponents().size() - 1) {
	
					sb.append(", ");
					
					// this adding newlines for printing devices is really driving nuts!
					if(bDevice) {
						// only add two NEWLINE characters if the sub-component was a device
						sb.append(EugeneDeveloperUtils.NEWLINE).append(EugeneDeveloperUtils.NEWLINE);
					} else {
						sb.append(EugeneDeveloperUtils.NEWLINE);
					}
				} else {
					sb.append(")").append(EugeneDeveloperUtils.NEWLINE);
				}
			}
			
		}
		
		return sb.toString();
	}

	@Override
	public NamedElement getElement(String name) 
			throws EugeneException {
		for(NamedElement e : this.getComponentList()) {
			if(e.getName().equals(name)) {
				return e;
			}
		} 
		return (NamedElement)null;
	}

	@Override
	public NamedElement getElement(int idx) 
			throws EugeneException {
		
		if( idx < 0 || idx > this.getComponents().size() - 1) {
			throw new EugeneException("The array index "+idx+" is out of bounds on the device "+this.getName());
		}
		
		List<NamedElement> loc = this.getComponents().get(idx);
		if(loc.size() > 1) {
			// return a EugeneCollection
			EugeneCollection ec = new EugeneCollection(null);
			ec.getElements().addAll(loc);
			return ec;
		} else {
			return loc.get(0);
		}
	}
	
	
	/**
	 * The setSequence(String) method of the Device class
	 * throws an exception since the sequence of a Device 
	 * is automatically generated based on the Device's 
	 * components. 
	 * 
	 * @param sequence  ... a sequence
	 */
	@Override
	public void setSequence(String sequence) 
			throws EugeneException {
		throw new EugeneException("A device's sequence will be generated based on its components.");
	}

	/**
	 * The getSequence() method returns the 
	 * sequence of the component if the sequence 
	 * is not empty and not null.
	 * 
	 * @return this component's sequence
	 * 
	 * @throws EugeneException
	 */
	@Override
	public String getSequence() 
			throws EugeneException {
		
		if(this.hasSequence()) {
			StringBuilder sb = new StringBuilder();
			
			int i=0;
			for(List<NamedElement> loe : this.getComponents()) {
				
				NamedElement e = loe.get(0);
				if(e instanceof Part) {
					if(this.getOrientations().get(i).get(0) == Orientation.REVERSE) {
						sb.append(SequenceUtils.reverseComplement(((Part)e).getSequence()));
					} else {
						sb.append(((Part)e).getSequence());
					}
				} else if(e instanceof Device) {
					if(this.getOrientations().get(i).get(0) == Orientation.REVERSE) {
						// flip the device
						Device d = DeviceUtils.flipAndInvert((Device)e);
						sb.append(d.getSequence());
					} else {
						sb.append(((Device)e).getSequence());
					}
				}
				
				i++;
			}
			return sb.toString();
		}
		
		throw new EugeneException("The component " + this.getName() +" has no DNA sequence!");
	}
	
	/**
	 * The hasSequence() method checks if the Device has 
	 * a sequence. That is, if
	 * - the Device contains only Parts and
	 * - each Part has a sequence
	 * 
	 * @return true ... if the sequence is non-empty and non-null
	 *        false ... otherwise
	 *        
	 * @throws EugeneException
	 */
	@Override
	public boolean hasSequence() 
			throws EugeneException {

		// we iterate over all elements of the device
		for(List<NamedElement> loe : this.getComponents()) {
			
			// no ``selection'' operator
			if(loe.size() != 1) {
				return false;
			}
			
			// the element must be a Part and 
			// the Part must have a sequence
			if(loe.get(0) instanceof Part && !((Part)loe.get(0)).hasSequence()) {
				return false;
			}
		}
		
		return true;
	}
}
