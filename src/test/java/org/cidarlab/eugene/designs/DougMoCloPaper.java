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
		//rebuildQueryScenario();

		testRev1();
	}
	
//	private static void rebuildQueryScenario() {
//		try {
//			Eugene eug = new Eugene();
//		
//			
//			EugeneCollection ec = eug.executeFile(
//					new File("./designs/cidar/doug/query-issue.eug"));
//			
//			if(ec instanceof EugeneReturnCollection) {
//				System.out.println("visualized images: " + 
//						((EugeneReturnCollection)ec).getImages());								
//			}
//			
//		} catch(EugeneException ee) {
//			ee.printStackTrace();
//		}		
//	}
	
	private static void testRev1() {
		try {
			Eugene eug = new Eugene();
		
			
//			EugeneCollection ec = eug.executeFile(
//					new File("./designs/cidar/doug/CIDAR_MoClo_Eugene_rev3.eug"));
			EugeneCollection ec = eug.executeFile(
					new File("./designs/cidar/doug/CIDAR_MoClo_Eugene_final.eug"));
			
			if(ec instanceof EugeneReturnCollection) {
				System.out.println("visualized images: " + 
						((EugeneReturnCollection)ec).getImages());								
			}
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}	
	}
}
