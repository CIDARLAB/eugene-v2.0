package org.cidarlab.eugene.units;

import static org.junit.Assert.assertTrue;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

public class PlusMinusOperatorsTest {

	@Test
	public void testDesignTemplatePlusPartType() {
		
		String script = 
				"PartType PT;" +
				"Device D;" +
				"D = D + PT;";
		
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection results = eug.executeScript(script);
			
			assertTrue(null != results);
			assertTrue(results.contains("D"));
			assertTrue(results.get("D") instanceof Device);
			
			Device D = (Device)results.get("D");
			assertTrue(D.getComponents().size() == 1);
			
			assertTrue(D.getComponents().get(0).size() == 1);
			assertTrue(D.getComponents().get(0).get(0) instanceof PartType);
			
			PartType PT = (PartType)(D.getComponents().get(0).get(0));
			assertTrue("PT".equals(PT.getName()));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPartTypePlusDesignTemplate() {
		
		String script = 
				"PartType PT;" +
				"Device D;" +
				"D = PT + D;";
		
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection results = eug.executeScript(script);
			
			assertTrue(null != results);
			assertTrue(results.contains("D"));
			assertTrue(results.get("D") instanceof Device);
			
			Device D = (Device)results.get("D");
			assertTrue(D.getComponents().size() == 1);
			
			assertTrue(D.getComponents().get(0).size() == 1);
			assertTrue(D.getComponents().get(0).get(0) instanceof PartType);
			
			PartType PT = (PartType)(D.getComponents().get(0).get(0));
			assertTrue("PT".equals(PT.getName()));
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDesignTemplatePlusThreeTimesPartType() {
		
		String script = 
				"PartType PT;" +
				"Device D;" +
				"D = D + PT + PT + PT;";
		
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection results = eug.executeScript(script);
			
			assertTrue(null != results);
			assertTrue(results.contains("D"));
			assertTrue(results.get("D") instanceof Device);
			
			Device D = (Device)results.get("D");
			assertTrue(D.getComponents().size() == 3);
			
			for(int i=0; i<3; i++) {
				assertTrue(D.getComponents().get(i).size() == 1);
				assertTrue(D.getComponents().get(i).get(0) instanceof PartType);
				
				PartType PT = (PartType)(D.getComponents().get(i).get(0));
				assertTrue("PT".equals(PT.getName()));
			}
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testDesignTemplatePlusDesignTemplate() {
		
		String script = 
				"PartType PT;" +
				"Device PTs (PT);" +
				"Device D;" +
				"D = D + PTs;";
		
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection results = eug.executeScript(script);
			
			assertTrue(null != results);
			assertTrue(results.contains("D"));
			assertTrue(results.get("D") instanceof Device);
			
			Device D = (Device)results.get("D");
			assertTrue(D.getComponents().size() == 1);
			
			assertTrue(D.getComponents().get(0).size() == 1);
			assertTrue(D.getComponents().get(0).get(0) instanceof Device);
			
			Device PTs = (Device)(D.getComponents().get(0).get(0));
			assertTrue("PTs".equals(PTs.getName()));
			assertTrue(PTs.getComponents().size() == 1);
			assertTrue(PTs.getComponents().get(0).size() == 1);
			assertTrue(PTs.getComponents().get(0).get(0) instanceof PartType);
			
			PartType PT = (PartType)PTs.getComponents().get(0).get(0);
			assertTrue("PT".equals(PT.getName()));
				
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testDeploymentIssue() {
		String script = "PartType PT; Device D; D = D + PT;";
		try {
			EugeneCollection col = new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testDesignTemplatePlusPartTypeInLoop() {
		
		String script = 
				"PartType PT;" +
				"Device D;" +
				"num N = 5;" +
				"for(num i=1; i<=N; i=i+1) { D = D + PT; }";
		
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection results = eug.executeScript(script);
			
			assertTrue(null != results);
			assertTrue(results.contains("D"));
			assertTrue(results.get("D") instanceof Device);
			
			Device D = (Device)results.get("D");
			assertTrue(D.getComponents().size() == 5);
			
			assertTrue(null != results.get("N"));
			assertTrue(results.get("N") instanceof Variable);
			
			Variable N = (Variable)results.get("N");
			assertTrue(EugeneConstants.NUM.equals(N.getType()));
			
			for(int i=0; i<N.getNum(); i++) {			
				assertTrue(D.getComponents().get(i).size() == 1);
				assertTrue(D.getComponents().get(i).get(0) instanceof PartType);
			}
			
		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}
	
	/*---------------
	 * INVALID USES OF THE + OPERATOR
	 *---------------*/
	@Test
	public void testDesignTemplatePlusVariable() {
		
		String script = 
				"num v1 = 1;" +
				"Device D;" +
				"D = D + v1;";
		
		try {
			new Eugene().executeScript(script);
			assertTrue(false);
		} catch(EugeneException ee) {
			assertTrue(ee.getLocalizedMessage().contains("Invalid use of the + operator!"));
		}
	}
	
	@Test
	public void testDesignTemplatePlusCollection() {
		
		String script = 
				"Collection C (num v1 = 1);" +
				"Device D;" +
				"D = D + C;";
		
		try {
			new Eugene().executeScript(script);
			assertTrue(false);
		} catch(EugeneException ee) {
			assertTrue(ee.getLocalizedMessage().contains("Invalid types!"));
		}
	}
	
	@Test
	public void testDesignTemplateDPlusDesignTemplateD() {
		
		String script = 
				"Device D;" +
				"D = D + D;";		
		try {
			new Eugene().executeScript(script);
			assertTrue(false);
		} catch(EugeneException ee) {
			assertTrue(ee.getLocalizedMessage().contains("Cannot add D to D!"));
		}
	}
	
	@Test
	public void testRecursiveAdd() {
		String script = 
				"Device D1;" +
				"Device D2;" +
				"D1 = D1 + D2;" +		
				"D2 = D2 + D1;"; 		
		try {
			new Eugene().executeScript(script);
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(ee.getLocalizedMessage().contains("Cannot add D to D!"));
		}
	}
	
}
