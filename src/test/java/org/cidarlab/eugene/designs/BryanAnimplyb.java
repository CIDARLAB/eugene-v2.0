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
public class BryanAnimplyb {

	public static void main(String[] args) {
//		testScript("./designs/cidar/cello/animplyb/animplyb_works.eug");
//		testScript("./designs/cidar/cello/animplyb/animplyb_rev01.eug");
//		testScript("./designs/cidar/cello/animplyb/animplyb_rev02.eug");
//		testScript("./designs/cidar/cello/animplyb/animplyb_rev03.eug");
		testScript("./designs/cidar/cello/animplyb/animplyb_rev04.eug");
	}
	
	
	private static void testScript(String filename) {
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection ec = eug.executeFile(
					new File(filename));
			
			if(ec instanceof EugeneReturnCollection) {
				System.out.println("visualized images: " + 
						((EugeneReturnCollection)ec).getImages());								
			}
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}		
	}

}
