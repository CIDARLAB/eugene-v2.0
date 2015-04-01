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
//		testWorks();
//		testFails();
//		testRev01();
		testRev03();
	}
	
	private static void testWorks() {
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection ec = eug.executeFile(
					new File("./designs/cidar/cello/animplyb/animplyb_works.eug"));
			
			if(ec instanceof EugeneReturnCollection) {
				System.out.println("visualized images: " + 
						((EugeneReturnCollection)ec).getImages());								
			}
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}		
	}
	
	private static void testFails() {
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection ec = eug.executeFile(
					new File("./designs/cidar/cello/animplyb/animplyb_fails.eug"));
			
			if(ec instanceof EugeneReturnCollection) {
				System.out.println("visualized images: " + 
						((EugeneReturnCollection)ec).getImages());								
			}
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}		
	}

	private static void testRev01() {
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection ec = eug.executeFile(
					new File("./designs/cidar/cello/animplyb/animplyb_rev01.eug"));
			
			if(ec instanceof EugeneReturnCollection) {
				System.out.println("visualized images: " + 
						((EugeneReturnCollection)ec).getImages());								
			}
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}		
	}

	private static void testRev03() {
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection ec = eug.executeFile(
					new File("./designs/cidar/cello/animplyb/animplyb_rev03.eug"));
			
			
			System.out.println(ec.get("allDesigns"));			
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}		
	}
}
