package org.cidarlab.cello;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;





/*
 * Eugene imports
 */
import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.data.pigeon.Pigeonizer;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.EugeneUtils;

/**
 * The EugeneAdapter class demonstrates the programmatic 
 * utilization of Eugene. 
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneAdapter {

	public Eugene e;
	public Pigeonizer p;
	
	public EugeneAdapter() 
			throws Exception {
		/*
		 * STEP I:
		 * - Instantiating Eugene
		 */
		this.e = new Eugene();
		
		/*
		 * - Instantiating the Pigeonizer 
		 *   for SBOL Visual compliant visualization 
		 *   of designs using Pigeon
		 */
		this.p = new Pigeonizer();
	}
	
	public void generateNOTGates() 
			throws Exception {
		
		/*
		 * STEP II:
		 * Executing a Eugene script
		 */
		EugeneCollection ec = e.executeFile(new File("./designs/cidar/cello/xor/permute"));
		
		/*
		 * STEP III:
		 * Processing the results 
		 */
		EugeneArray not_gates = (EugeneArray)ec.get("loNOT");
		
		// SBOL Visual compliant visualization of 
		// the designs using Pigeon
		if(null != not_gates && !not_gates.getElements().isEmpty()) {
			this.visualizeDesigns(not_gates);
		} 
		
		// textual representation of the designs
		// and ouput them onto the console
		if(null != not_gates && !not_gates.getElements().isEmpty()) {
			this.textualizeDesigns(not_gates);
		}		
	}
	
	private void visualizeDesigns(EugeneArray array) 
			throws EugeneException {
		
		List<URI> pics = new ArrayList<URI>();
		for(NamedElement ne : array.getElements()) {
			// here, we only print each NamedElement object
			// to the console
			if(ne instanceof Device) {
				try {
					pics.add(this.visualizeDevice((Device)ne));
				} catch(EugeneException ee) {
					throw new EugeneException(ee.getMessage());
				}
			}
			
		}
		
		try {
			
			// merge all URIs into one pic
			this.p.serializeImage(
				this.p.toMergedImage(pics),
				"./exports/pigeon/demo01.png");
			
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
	}
	
	private void textualizeDesigns(EugeneArray array) 
			throws EugeneException {

		for(NamedElement ne : array.getElements()) {
			if(ne instanceof Device) {
				System.out.println(
						EugeneUtils.prettyPrint((Device)ne));
			}
		}
	}
	
	public static void main(String[] args) 
			throws Exception {
		
		new EugeneAdapter().generateNOTGates();
	}
	
	
	
	public URI visualizeDevice(Device d) 
			throws EugeneException {
		return this.p.pigeonizeSingle(d, null);		
	}

}
