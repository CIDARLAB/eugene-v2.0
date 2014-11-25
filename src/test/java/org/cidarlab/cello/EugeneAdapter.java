package org.cidarlab.cello;

import java.io.File;

import org.cidarlab.eugene.Eugene;

public class EugeneAdapter {

	public static void main(String[] args) 
			throws Exception {
//		new Eugene().executeFile(new File("./designs/cidar/cello/xor/cello_XOR-one.eug"));
		new Eugene().executeFile(new File("./designs/cidar/cello/xor/cello_XOR.eug"));
//		new Eugene().executeFile(new File("./designs/cidar/cello/xor/one-gate.eug"));
//		new Eugene().executeFile(new File("./designs/cidar/cello/xor/xor.eug"));
	}

}
