package org.cidarlab.eugene.demo;

import java.io.File;


// Eugene imports
import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;

/**
 * The EugeneScriptExecutor demonstrates the programmatic 
 * utilization of Eugene. Especially how to execute a Eugene script 
 * and how to process the results. 
 * 
 * @author Ernst Oberortner
 *
 */
public class EugeneScriptExecutor {

	public static void main(String[] args) 
			throws Exception {

		/*
		 * STEP I:
		 * Instantiating Eugene
		 */
		Eugene e = new Eugene();
		
		// optionally, Eugene provides the configuration of 
		// its ROOT_DIRECTORY.
		Eugene.ROOT_DIRECTORY = "./demos";
		
		/*
		 * STEP II:
		 * Executing a Eugene script
		 */
		EugeneCollection ec = e.executeFile(new File("imperative-features/01-hello-world.eug"));
		
		/*
		 * STEP III:
		 * Processing the results 
		 */
		for(NamedElement ne : ec.getElements()) {
			// here, we only print each NamedElement object
			// to the console
			System.out.println(ne);
		}

	}

}
