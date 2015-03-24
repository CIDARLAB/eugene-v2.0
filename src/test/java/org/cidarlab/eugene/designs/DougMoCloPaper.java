package org.cidarlab.eugene.designs;

import java.io.File;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.dom.imp.container.EugeneReturnCollection;
import org.cidarlab.eugene.exception.EugeneException;

/**
 * 
 * @author Ernst Oberortner
 */
public class DougMoCloPaper {

	public static void main(String[] args) {
		try {
			Eugene eug = new Eugene();
		
			EugeneCollection ec = eug.executeFile(
					new File("./designs/cidar/doug/moclo.eug"));
			
			if(ec instanceof EugeneReturnCollection) {
				System.out.println(((EugeneReturnCollection)ec).getImages());
			}
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}		
	}
}
