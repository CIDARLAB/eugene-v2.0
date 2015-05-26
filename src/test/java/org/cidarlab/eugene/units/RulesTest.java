package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.imp.container.EugeneArray;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.dom.rules.Rule;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.Test;

public class RulesTest {

	@Test
	public void testSyntaxEmptyRules() {
		
		String script = 
				"PartType PT; Device D(PT);" +
				"Rule R1;" +			
				"Rule R2();" +
				"Rule R3(ON D:);";
		
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection col = eug.executeScript(script);
			
			assertTrue(null != col);
			assertTrue(null != col.getElements());
			assertTrue(!col.getElements().isEmpty());
			
			
		} catch(EugeneException ee) {
			assertTrue(false);
		}
						
	}

	@Test
	public void testAddContainsRules() {
		
		String script = 
				"PartType PT; Device D;" +
				"Rule containsParts(ON D:);" +
				"for(num i=1; i<=2; i=i+1) {" + 
				"    D = D + PT; " +
				"    PT ${\"p\"+i};" + 
				"    SAVE (${\"p\"+i});" +
				"    AND (containsParts, CONTAINS ${\"p\"+i});" +
				"}" +
				"AND (containsParts, ALL_FORWARD);" +
				"results = product(D);";
		
		try {
			Eugene eug = new Eugene();
			
			EugeneCollection col = eug.executeScript(script);
			
			assertTrue(null != col);
			assertTrue(null != col.getElements());
			assertTrue(!col.getElements().isEmpty());
			
			assertTrue(null != col.getElement("D"));
			assertTrue(col.getElement("D") instanceof Device);
			
			// TEST the rule
			assertTrue(null != col.getElement("containsParts"));
			assertTrue(col.getElement("containsParts") instanceof Rule);
			
			Rule containsRule = (Rule)col.getElement("containsParts");
			assertTrue(null != containsRule.getDevice());
			assertTrue(containsRule.getDevice().getName().equals(col.getElement("D").getName()));
			assertTrue(null != containsRule.getPredicates());
			assertTrue(null != containsRule.getPredicates().getPredicates());
			assertTrue(containsRule.getPredicates().getPredicates().size() == 3);
			
			
			// test the results
			assertTrue(null != col.getElement("results"));
			assertTrue(col.getElement("results") instanceof EugeneArray);
			
			EugeneArray results = (EugeneArray)col.getElement("results");
			assertTrue(null != results.getElements());
			assertTrue(results.getElements().size() == 2);   // 2 permutations
			
		} catch(EugeneException ee) {
			assertTrue(false);
		}
						
	}

	@Test
	public void testSpecificationDocumentExample() {
		
		String script = 
					"PartType Promoter;" +
					"Promoter p1;" +
					"Promoter p2;" +
					"Device Promoters(Promoter, Promoter);" +
					"Rule containsPromoters(ON Promoters:);" +
					"AND(containsPromoters, CONTAINS p1);" +
					"AND(containsPromoters, CONTAINS p2);" +
					"results = product(Promoters);";
		try {

			Eugene eug = new Eugene();
			EugeneCollection col = eug.executeScript(script);
			
			assertTrue(null != col);
			assertTrue(null != col.getElement("results"));
			
			assertTrue(col.getElement("results") instanceof EugeneArray);
			
			EugeneArray results = (EugeneArray)col.getElement("results");
			assertTrue(null != results.getElements());
			assertTrue(results.getElements().size() == (2 * 2) * 2);
			
			
		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}
}
