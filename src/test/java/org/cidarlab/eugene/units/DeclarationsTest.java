package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.DOMException;
import org.cidarlab.eugene.exception.EugeneException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeclarationsTest {

	private Eugene eugene;
	
	@BeforeClass
	public static void setUpBeforeClass() 
			throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() 
			throws Exception {
	}

	@Before
	public void setUp() 
			throws Exception {
		this.eugene = new Eugene();
	}

	@After
	public void tearDown()
			throws Exception {
		
		this.eugene = null;
	}

	/*---------------------------------------
	 * PROPERTY DECLARATIONS
	 * we test all 5 types of properties (num, num[], txt, txt[], bool)
	 * 
	 * Test Strategy:
	 *----------------
	 * 1. representing a property as an instance of Eugene's DOM
	 * 2. specifying the same property in Eugene syntax and interpret it w/ Eugene
	 * 3. comparing the Eugene interpretation w/ the instance of the Eugene DOM regarding
	 *    - NULL value
	 *    - Property class
	 *    - name of property
	 *    - type of property
	 *---------------------------------------*/
	@Test
	public void testNumPropertyDeclaration() {
		
		Property num_prop = new Property("num_prop", EugeneConstants.NUM);

		String script = "Property num_prop(num);";
		
		try {
			EugeneCollection col = this.eugene.executeScript(script);
			
			assert(col != null);
			assert(col.getElements().size() == 1);

			assert(col.get("num_prop") != null);
			assert(col.get("num_prop") instanceof Property);
			assert(col.get("num_prop").getName().equals(num_prop.getName()));			
			assert(((Property)col.get("num_prop")).getType().equals(num_prop.getType()));
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testTxtPropertyDeclaration() {
		Property txt_prop = new Property("txt_prop", EugeneConstants.TXT);
		
		String script = "Property txt_prop(txt);";
		
		try {
			EugeneCollection col = this.eugene.executeScript(script);
			assert(col != null);
			assert(col.getElements().size() == 1);
			assert(col.get("txt_prop") != null);
			assert(col.get("txt_prop") instanceof Property);
			assert(col.get("txt_prop").getName().equals(txt_prop.getName()));
			
			assert(((Property)col.get("txt_prop")).getType().equals(txt_prop.getType()));
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testBoolPropertyDeclaration() {
		Property bool_prop = new Property("bool_prop", EugeneConstants.BOOLEAN);
		
		String script = "Property bool_prop(bool);";
		
		try {
			EugeneCollection col = this.eugene.executeScript(script);
			assert(col != null);
			assert(col.getElements().size() == 1);
			assert(col.get("bool_prop") != null);
			assert(col.get("bool_prop") instanceof Property);
			assert(col.get("bool_prop").getName().equals(bool_prop.getName()));
			
			assert(((Property)col.get("bool_prop")).getType().equals(bool_prop.getType()));
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testTxtListPropertyDeclaration() {
		Property txt_list_prop = new Property("txt_list_prop", EugeneConstants.TXTLIST);
		
		String script = "Property txt_list_prop(txt[]);";
		
		try {
			EugeneCollection col = this.eugene.executeScript(script);
			assert(col != null);
			assert(col.getElements().size() == 1);
			assert(col.get("txt_list_prop") != null);
			assert(col.get("txt_list_prop") instanceof Property);
			assert(col.get("txt_list_prop").getName().equals(txt_list_prop.getName()));
			
			assert(((Property)col.get("txt_list_prop")).getType().equals(txt_list_prop.getType()));
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testNumListPropertyDeclaration() {
		Property num_list_prop = new Property("num_list_prop", EugeneConstants.NUMLIST);
		
		String script = "Property num_list_prop(num[]);";
		
		try {
			EugeneCollection col = this.eugene.executeScript(script);
			assert(col != null);
			assert(col.getElements().size() == 1);
			assert(col.get("num_list_prop") != null);
			assert(col.get("num_list_prop") instanceof Property);
			assert(col.get("num_list_prop").getName().equals(num_list_prop.getName()));
			
			assert(((Property)col.get("num_list_prop")).getType().equals(num_list_prop.getType()));
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*---------------------------------------
	 * PART TYPE DECLARATIONS
	 * 
	 * Test Strategy:
	 *----------------
	 * 1. representing a property as an instance of Eugene's DOM
	 * 2. specifying the same property in Eugene syntax and interpret it w/ Eugene
	 * 3. comparing the Eugene interpretation w/ the instance of the Eugene DOM regarding
	 *    - NULL value
	 *    - PartType class
	 *    - name of part type
	 *    - list of properties
	 *---------------------------------------*/ 
	@Test
	public void testEmptyPartTypeDeclaration() {
		PartType empty = new PartType("empty");
		
		String script = "PartType empty();";
		
		try {
			EugeneCollection col = this.eugene.executeScript(script);
			assert(col != null);
			assert(col.getElements().size() == 1);
			assert(col.get("empty") != null);
			assert(col.get("empty") instanceof PartType);
			assert(col.get("empty").getName().equals(empty.getName()));
			
			assert(((PartType)col.get("empty")).getProperties().equals(empty.getProperties()));
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPartTypeWithOnePropertyDeclaration() {
		Property prop = new Property("prop", EugeneConstants.TXT);
		PartType pt_one = new PartType("pt_one");
		pt_one.getProperties().add(prop);
		
		String script = "Property prop(txt); PartType pt_one(prop);";
		
		try {
			EugeneCollection ec = this.eugene.executeScript(script);
			assert(null != ec);
			// 2 elements: prop, pt_one
			assert(ec.getElements().size() == 2);
			
			assert(null != ec.get("prop"));
			assert(ec.get("prop") instanceof Property);
			
			assert(null != ec.get("pt_one"));
			assert(ec.get("pt_one") instanceof PartType);
			
			assert(((PartType)ec.get("pt_one")).getProperty("prop").equals(ec.get("prop")));
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testPartTypeWithManyPropertiesDeclaration() {
		int NR_PROPERTIES = 100;
		PartType pt_many = new PartType("pt_many");
		
		StringBuilder sbProps = new StringBuilder();
		StringBuilder sbPropAssign = new StringBuilder();
		for(int i=1; i<=NR_PROPERTIES; i++) {

			// assigning property to part type
			pt_many.getProperties().add(
					new Property("prop", EugeneConstants.TXT));
			
			// building script
			sbProps.append("Property prop"+i+"(txt);");
			
			sbPropAssign.append("prop"+i);
			if(i<=NR_PROPERTIES-1) {
				sbPropAssign.append(",");
			}
			
		}
		
		StringBuilder script = new StringBuilder();
		script.append(sbProps.toString())
			.append("PartType pt_many(")
			.append(sbPropAssign)
			.append(");");
		

		try {
			EugeneCollection ec = this.eugene.executeScript(script.toString());

			assert(null != ec);
			// 2 elements: prop, pt_one
			assert(ec.getElements().size() == NR_PROPERTIES + 1);

			for(int i=1; i<=NR_PROPERTIES; i++) {
				assert(null != ec.get("prop"+i));
				assert(ec.get("prop"+i) instanceof Property);
			}
			
			assert(null != ec.get("pt_many"));
			assert(ec.get("pt_many") instanceof PartType);
			assert(((PartType)ec.get("pt_many")).getProperties().size() == NR_PROPERTIES);
			
//			assert(((PartType)ec.get("pt_one")).getProperty("prop").equals(ec.get("prop")));
			
		} catch(EugeneException ee) {
			ee.printStackTrace();
			assertTrue(false);
		}
	}
	
	/*---------------------------------------
	 * INSTANTIATIONS (OF PART TYPES)
	 * 
	 * Test Strategy:
	 *----------------
	 * (1) representing properties, part types, and parts as instances of the Eugene DOM
	 * (2) specifying the same properties, part types, and parts in Eugene syntax and 
	 *     let it execute w/ Eugene's interpreter
	 * (3) comparing the (1) and (2) regarding
	 *    - NULL value
	 *    - PartType class
	 *    - name of part type
	 *    - list of properties
	 *---------------------------------------*/ 
	@Test
	public void testInstantiation() {
		
		// properties
		Property num_prop = new Property("num_prop", EugeneConstants.NUM);
		Property txt_prop = new Property("txt_prop", EugeneConstants.TXT);
		
		// part types
		PartType PT = new PartType("PT");
		PT.getProperties().add(num_prop);
		PT.getProperties().add(txt_prop);
		
		// parts
		Part pt_p1 = new Part("pt_p1", PT);
		PropertyValue pt_p1_num = new PropertyValue(num_prop);
		pt_p1_num.setNum(1);
		try {
			pt_p1.setPropertyValue(num_prop, pt_p1_num);
		} catch(DOMException de) {
			assertTrue(false);
		}
		PropertyValue pt_p1_txt = new PropertyValue(txt_prop);
		pt_p1_txt.setTxt("one");
		try {
			pt_p1.setPropertyValue(txt_prop, pt_p1_txt);
		} catch(DOMException de) {
			assertTrue(false);
		}


		Part pt_p2 = new Part("pt_p2", PT);
		PropertyValue pt_p2_num = new PropertyValue(num_prop);
		pt_p2_num.setNum(2);
		try {
			pt_p2.setPropertyValue(num_prop, pt_p2_num);
		} catch(DOMException de) {
			assertTrue(false);
		}
		PropertyValue pt_p2_txt = new PropertyValue(txt_prop);
		pt_p2_txt.setTxt("two");
		try {
			pt_p2.setPropertyValue(txt_prop, pt_p2_txt);
		} catch(DOMException de) {
			assertTrue(false);
		}
		
		
		String script = 
				"Property num_prop(num); Property txt_prop(txt); " +
				"PartType PT(num_prop, txt_prop); " +
				"PT pt_p1(.num_prop(1), .txt_prop(\"one\")); " +
				"PT pt_p2(.num_prop(2), .txt_prop(\"two\")); ";

		try {
			EugeneCollection ec = this.eugene.executeScript(script.toString());

			// pt_p1.pt_p1_num
			assert(ec.get("pt_p1") instanceof Part);
			assert(((Part)ec.get("pt_p1")).getPropertyValue("pt_p1_num") != null);
			assert(EugeneConstants.NUM.equals(
					((Part)ec.get("pt_p1")).getPropertyValue("pt_p1_num").getType()));
			assert(1 == ((Part)ec.get("pt_p1")).getPropertyValue("pt_p1_num").getNum());
			
			// pt_p1.pt_p1_txt
			assert(ec.get("pt_p1") instanceof Part);
			assert(((Part)ec.get("pt_p1")).getPropertyValue("pt_p1_txt") != null);
			assert(EugeneConstants.TXT.equals(
					((Part)ec.get("pt_p1")).getPropertyValue("pt_p1_txt").getType()));
			assert(1 == ((Part)ec.get("pt_p1")).getPropertyValue("pt_p1_txt").getNum());

			// pt_p2.pt_p2_num
			assert(ec.get("pt_p2") instanceof Part);
			assert(((Part)ec.get("pt_p2")).getPropertyValue("pt_p2_num") != null);
			assert(EugeneConstants.NUM.equals(
					((Part)ec.get("pt_p2")).getPropertyValue("pt_p2_num").getType()));
			assert(2 == ((Part)ec.get("pt_p2")).getPropertyValue("pt_p2_num").getNum());

			// pt_p2.pt_p2_txt
			assert(ec.get("pt_p2") instanceof Part);
			assert(((Part)ec.get("pt_p2")).getPropertyValue("pt_p2_txt") != null);
			assert(EugeneConstants.TXT.equals(
					((Part)ec.get("pt_p2")).getPropertyValue("pt_p2_txt").getType()));
			assert(2 == ((Part)ec.get("pt_p2")).getPropertyValue("pt_p2_txt").getNum());

		} catch(EugeneException ee) {
			assertTrue(false);
		}
	}

}
