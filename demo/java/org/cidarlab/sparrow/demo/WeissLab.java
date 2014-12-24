package org.cidarlab.sparrow.demo;

import java.io.File;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.exception.EugeneException;

public class WeissLab {
	
	public static void main(String[] args) 
			throws Exception {
		
		new WeissLab().test("./demo/weiss-alt1");
	}
	
	public void test(String file) 
			throws EugeneException {
		try {
			long t1 = System.nanoTime();
			
			new Eugene().executeFile(new File(file));

			long tProcessing = System.nanoTime() - t1;
			
			System.out.println("[WeissLab.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		} catch(Exception e) {
//			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}

}
