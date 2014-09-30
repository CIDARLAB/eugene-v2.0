package org.cidarlab.eugene;

import java.io.File;

import org.cidarlab.eugene.exception.EugeneException;

public class DemoDesigns {

	public static void main(String[] args) 
			throws EugeneException {
		
		new DemoDesigns().test("./designs/cidar/3-to-8-decoder/3-to-8-decoder.eug");
	}

	public void test(String file) 
			throws EugeneException {
		try {
			long t1 = System.nanoTime();
			
			new Eugene().executeFile(new File(file));

			long tProcessing = System.nanoTime() - t1;
			
			System.out.println("[DemoDesigns.test] full processing time: "+tProcessing*Math.pow(10, -9)+"sec");
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}
	}
}
