package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Variable;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.junit.Test;

public class MulDivOperatorsTest {

	@Test
	public void testMult() 
			throws Exception {
		
		Eugene eug = new Eugene();
		// div on variables
		String[] scripts = new String[] {
				"num N=1*2;",
				"num one=1;num N=one*2;",
				"num one=1;num two=2;num N=one*two;",
				"num one=1;num two=2;num N=one*two*one;"
		};
		
		for(String script : scripts) {
			EugeneCollection results = eug.executeScript(script);
			assertTrue(null != results);
			assertTrue(results.contains("N"));
			assertTrue(results.get("N") instanceof Variable);
			Variable N = (Variable)results.get("N");
			assertTrue(EugeneConstants.NUM.equals(N.getType()));
			assertTrue((double)2 == N.getNum());
		}
	}

	@Test
	public void testDiv() 
			throws Exception {
		
		Eugene eug = new Eugene();
		// div on variables
		String[] scripts = new String[] {
				"num N=1/2;",
				"num one=1;num N=one/2;",
				"num one=1;num two=2;num N=one/two;",
				"num one=1;num two=2;num N=one/two/one;"
		};
		
		for(String script : scripts) {
			EugeneCollection results = eug.executeScript(script);
			assertTrue(null != results);
			assertTrue(results.contains("N"));
			assertTrue(results.get("N") instanceof Variable);
			Variable N = (Variable)results.get("N");
			assertTrue(EugeneConstants.NUM.equals(N.getType()));
			assertTrue((double)1/2 == N.getNum());
		}
	}
	
	@Test
	public void testDivByZero() {
		
		// div on variables
		String[] scripts = new String[] {
				"num N=1/0;",
				"num zero=0;num one=1;num N=one/zero;",
				"num zero=0;num one=1;num two=2;num N=one/(two*zero);"
		};
		
		for(String script : scripts) {
			try {
				new Eugene().executeScript(script);
				assertTrue(false);
			} catch(Exception e) {
				assertTrue(e.getLocalizedMessage().toLowerCase().contains("division by zero"));
			}
		}
	}

	@Test
	public void testPrecedence()
			throws Exception {
		
		Eugene eug = new Eugene();
		// div on variables
		String[] scripts = new String[] {
				"num N=1*2/1;",							// 2
				"num one=1;num N= one/1 * 2;",			// 2
		};
		
		for(String script : scripts) {
			EugeneCollection results = eug.executeScript(script);
			assertTrue(null != results);
			assertTrue(results.contains("N"));
			assertTrue(results.get("N") instanceof Variable);
			Variable N = (Variable)results.get("N");
			assertTrue(EugeneConstants.NUM.equals(N.getType()));
			assertTrue((double)2 == N.getNum());
		}
	}

	@Test
	public void testParenthesis()
			throws Exception {
		
		Eugene eug = new Eugene();
		// div on variables
		String[] scripts = new String[] {
				"num N = 1 * (1/1);",						// 1
				"num two=2;num N = two / (1 * 2);",			// 1
		};
		
		for(String script : scripts) {
			EugeneCollection results = eug.executeScript(script);
			assertTrue(null != results);
			assertTrue(results.contains("N"));
			assertTrue(results.get("N") instanceof Variable);
			Variable N = (Variable)results.get("N");
			assertTrue(EugeneConstants.NUM.equals(N.getType()));
			assertTrue((double)1 == N.getNum());
		}
	}
}
