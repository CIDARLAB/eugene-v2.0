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
		 * Instantiating Eugene
		 */
		this.e = new Eugene();
		
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
		
		
		List<URI> pics = new ArrayList<URI>();
		for(NamedElement ne : not_gates.getElements()) {
			// here, we only print each NamedElement object
			// to the console
			if(ne instanceof Device) {
				pics.add(this.visualizeDevice((Device)ne));
			}
			
		}
		
		// merge all URIs into one pic
		this.p.serializeImage(
			this.p.toMergedImage(pics),
			"./exports/pigeon/demo01.png");

		
	}
	public static void main(String[] args) 
			throws Exception {
		
		new EugeneAdapter().generateNOTGates();
	}
	
	
	
	public URI visualizeDevice(Device d) 
			throws Exception {
		return this.p.pigeonizeSingle(d, null);		
	}

}
