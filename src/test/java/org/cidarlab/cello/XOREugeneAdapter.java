package org.cidarlab.cello;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/*
 * Eugene relevant imports
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
 * utilization of Eugene especially tailored for the 
 * design of XOR gates using Cello. 
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
	
	public EugeneCollection enumerateXORGates(File file) 
			throws Exception {
		/*
		 * STEP II:
		 * Executing a Eugene script
		 */
		return e.executeFile(file);
	}
	
	/*
	 * STEP III:
	 * Processing the results 
	 */
	public void processXORGates(EugeneArray result) 
			throws EugeneException {
		// SBOL Visual compliant visualization of 
		// the designs using Pigeon
		this.visualizeDesigns(result);
		
		// textual representation of the designs
		// and output them onto the console
		this.toStringDesigns(result);

		// textual representation of the designs
		// and output them onto the console
		this.prettyPrintDesigns(result);
		
		// generate the DNA sequences
		this.sequenceDesigns(result);
	}
	
	/**
	 * SBOL VISUAL compliant representation of the designs
	 * 
	 * @param array  ... an array of designs
	 * 
	 * @throws EugeneException
	 */
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
			
			// merge all URIs into one image
			this.p.serializeImage(
				this.p.toMergedImage(pics),
				"./xor_gates.png");
			
		} catch(EugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
	}
	
	/**
	 * SBOL VISUAL compliant representation of one design, 
	 * i.e. Eugene device
	 * 
	 * @param d ... the device to be SBOL visualized
	 * 
	 * @return ... the URI of the SBOL Visual image (generated using Pigeon)
	 * 
	 * @throws EugeneException
	 */
	public URI visualizeDevice(Device d) 
			throws EugeneException {
		return this.p.pigeonizeSingle(d, null);		
	}

	
	/**
	 * TEXTUAL representation of the designs
	 * 
	 * @param array  ... an array of designs
	 * 
	 * @throws EugeneException
	 */
	private void toStringDesigns(EugeneArray array) 
			throws EugeneException {

		for(NamedElement ne : array.getElements()) {
			if(ne instanceof Device) {
				System.out.println((Device)ne);
			}
		}
	}

	/**
	 * PRETTY TEXTUAL representation of the designs
	 * (including indentations and line-breaks).
	 * 
	 * @param array  ... and array of designs
	 * 
	 * @throws EugeneException
	 */
	private void prettyPrintDesigns(EugeneArray array) 
			throws EugeneException {

		for(NamedElement ne : array.getElements()) {
			if(ne instanceof Device) {
				System.out.println(
						// here, we use Eugene's PrettyPrinter
						EugeneUtils.prettyPrint((Device)ne));
			}
		}
	}
	
	/**
	 * DNA SEQUENCE representation of designs
	 * 
	 * @param array  ... an array of designs
	 * 
	 * @throws EugeneException
	 */
	private void sequenceDesigns(EugeneArray array) 
			throws EugeneException {

		for(NamedElement ne : array.getElements()) {
			if(ne instanceof Device) {

				// To get the sequence of a Design, Eugene v2.0 offers 
				// two options:
				
				// Option I: invoke the getSequence() method of the Device
				System.out.println(ne.getName() + " --> " +
						((Device)ne).getSequence());
				
				// Option II: utilize the SequenceUtils of Eugene v2.0
				System.out.println(ne.getName() + " --> " +
						SequenceUtils.toSequence((Device)ne));
				
								
				// only for testing: 
				// comparing both sequences
				if(((Device)ne).getSequence().equals(
						SequenceUtils.toSequence((Device)ne))) {
					System.out.println("BINGO!");
				}
			}
		}
	}

	/**
	 * MAIN()
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) 
			throws Exception {
		
		if(args.length != 1) {
			throw new IllegalArgumentException("Usage: XOREugeneAdapter <filename>");
		}
		
		// instantiate the XOREugeneAdapter 
		XOREugeneAdapter xor = new XOREugeneAdapter();

		// enumerate the XOR designs as specified 
		// in a Eugene script
		EugeneCollection ec = xor.enumerateXORGates(new File(args[0]));

		// if Eugene returned some results
		if(null != ec && !ec.getElements().isEmpty()) {
			
			// then, we retrieve the array of XOR designs
			// from the results
			// the name of the get() parameter must match with 
			// the specified name in the Eugene script
			// Example: result = permute(XORGate);
			EugeneArray result = (EugeneArray)ec.get("result");
			
			// if there are any XOR designs, then we process them
			if(null != result && !result.getElements().isEmpty()) {
				xor.processXORGates(result);
			}
		}
		
	}
	
	
	

}
