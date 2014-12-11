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
import org.cidarlab.eugene.util.SequenceUtils;

/**
 * The EugeneAdapter class demonstrates the programmatic 
 * utilization of Eugene. 
 * 
 * @author Ernst Oberortner
 *
 */
public class XOREugeneAdapter {

	public Eugene e;
	public Pigeonizer p;
	
	public XOREugeneAdapter() 
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
		// part library w/o DNA sequences
//		EugeneCollection ec = e.executeFile(new File("./designs/cidar/cello/cello_eugene_xor_121014.eug"));
		
		// part library w/ DNA sequences
		EugeneCollection ec = e.executeFile(new File("./designs/cidar/cello/cello_eugene_xor_dnaseq.eug"));
		
		/*
		 * STEP III:
		 * Processing the results 
		 */
		EugeneArray result = (EugeneArray)ec.get("result");
		
		// SBOL Visual compliant visualization of 
		// the designs using Pigeon
		if(null != result && !result.getElements().isEmpty()) {
			this.visualizeDesigns(result);
		} 
		
		// textual representation of the designs
		// and output them onto the console
		if(null != result && !result.getElements().isEmpty()) {
			this.toStringDesigns(result);
		}	

		// textual representation of the designs
		// and output them onto the console
		if(null != result && !result.getElements().isEmpty()) {
			this.prettyPrintDesigns(result);
		}	
		
		// generate the DNA sequences
		if(null != result && !result.getElements().isEmpty()) {
			this.sequenceDesigns(result);
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
				"./exports/pigeon/xor_gates.png");
			
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
	}
	
	public URI visualizeDevice(Device d) 
			throws EugeneException {
		return this.p.pigeonizeSingle(d, null);		
	}

	
	private void toStringDesigns(EugeneArray array) 
			throws EugeneException {

		for(NamedElement ne : array.getElements()) {
			if(ne instanceof Device) {
				System.out.println((Device)ne);
			}
		}
	}

	private void prettyPrintDesigns(EugeneArray array) 
			throws EugeneException {

		for(NamedElement ne : array.getElements()) {
			if(ne instanceof Device) {
				System.out.println(
						EugeneUtils.prettyPrint((Device)ne));
			}
		}
	}
	
	private void sequenceDesigns(EugeneArray array) 
			throws EugeneException {

		for(NamedElement ne : array.getElements()) {
			if(ne instanceof Device) {
				
				System.out.println(ne.getName() + " --> " +
						((Device)ne).getSequence());
				
			}
		}
	}

	public static void main(String[] args) 
			throws Exception {
		
		new XOREugeneAdapter().generateNOTGates();
	}
	
	
	

}
