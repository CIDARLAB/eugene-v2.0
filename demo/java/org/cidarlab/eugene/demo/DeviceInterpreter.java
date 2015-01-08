package org.cidarlab.eugene.demo;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.EugeneUtils;

/**
 * 
 * @author Ernst Oberortner
 *
 */
public class DeviceInterpreter {

	// Eugene instance
	private Eugene eugene;
	
	public DeviceInterpreter() 
			throws EugeneException {
		// instantiation of Eugene
		this.eugene = new Eugene();
	}
	
	public void demo() {
		
		try {
			/*
			 * STEP 1:
			 * building the Eugene script
			 */
			String script = this.buildScript();
			
			/*
			 * STEP 2:
			 * execution of Eugene script
			 */
			EugeneCollection ec = this.eugene.executeScript(script);

			/*
			 * STEP 3:
			 * interpret the enumerated designs
			 */
			EugeneArray designs = (EugeneArray)ec.get("designs");
			for(NamedElement e : designs.getElements()) {
				
				if(e instanceof Device) {
					Device interpDevice = 
							this.interpretDevice((Device)e);
					
					System.out.println(
							EugeneUtils.prettyPrint(interpDevice));
				}
				
			}
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
	}
	
	private String buildScript() {
		return "";
	}
	
	/**
	 * depending on the orientation of each 
	 * design's components
	 * here, we use the methods provided 
	 * by the EugeneUtils class
	 *  
	 * @param d
	 * @return
	 * @throws EugeneException
	 */
	public Device interpretDevice(Device d) 
			throws EugeneException {
		
		return null;
	}
	
	/**
	 * MAIN
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
			throws Exception {
		new DeviceInterpreter().demo();
	}
}
