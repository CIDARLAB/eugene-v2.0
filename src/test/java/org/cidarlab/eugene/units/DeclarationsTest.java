/* Copyright (c) 2015, Boston University
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list 
 * of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without specific prior 
 * written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.cidarlab.eugene.units;

import static org.junit.Assert.*;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.DOMException;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.util.EugeneDeveloperUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeclarationsTest {

    private Eugene eugene;

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

            assertTrue(col != null);
            assertTrue(col.getElements().size() == 1);

            assertTrue(col.get("num_prop") != null);
            assertTrue(col.get("num_prop") instanceof Property);
            assertTrue(col.get("num_prop").getName().equals(num_prop.getName()));
            assertTrue(((Property) col.get("num_prop")).getType().equals(num_prop.getType()));
        } catch (Exception e) {
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
            assertTrue(col != null);
            assertTrue(col.getElements().size() == 1);
            assertTrue(col.get("txt_prop") != null);
            assertTrue(col.get("txt_prop") instanceof Property);
            assertTrue(col.get("txt_prop").getName().equals(txt_prop.getName()));

            assertTrue(((Property) col.get("txt_prop")).getType().equals(txt_prop.getType()));
        } catch (Exception e) {
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
            assertTrue(col != null);
            assertTrue(col.getElements().size() == 1);
            assertTrue(col.get("bool_prop") != null);
            assertTrue(col.get("bool_prop") instanceof Property);
            assertTrue(col.get("bool_prop").getName().equals(bool_prop.getName()));

            assertTrue(((Property) col.get("bool_prop")).getType().equals(bool_prop.getType()));
        } catch (Exception e) {
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
            assertTrue(col != null);
            assertTrue(col.getElements().size() == 1);
            assertTrue(col.get("txt_list_prop") != null);
            assertTrue(col.get("txt_list_prop") instanceof Property);
            assertTrue(col.get("txt_list_prop").getName().equals(txt_list_prop.getName()));

            assertTrue(((Property) col.get("txt_list_prop")).getType().equals(txt_list_prop.getType()));
        } catch (Exception e) {
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
            assertTrue(col != null);
            assertTrue(col.getElements().size() == 1);
            assertTrue(col.get("num_list_prop") != null);
            assertTrue(col.get("num_list_prop") instanceof Property);
            assertTrue(col.get("num_list_prop").getName().equals(num_list_prop.getName()));

            assertTrue(((Property) col.get("num_list_prop")).getType().equals(num_list_prop.getType()));
        } catch (Exception e) {
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
            assertTrue(col != null);
            assertTrue(col.getElements().size() == 1);
            assertTrue(col.get("empty") != null);
            assertTrue(col.get("empty") instanceof PartType);
            assertTrue(col.get("empty").getName().equals(empty.getName()));

            assertTrue(((PartType) col.get("empty")).getProperties().equals(empty.getProperties()));
        } catch (Exception e) {
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
            assertTrue(null != ec);
            // 2 elements: prop, pt_one
            assertTrue(ec.getElements().size() == 2);

            assertTrue(null != ec.get("prop"));
            assertTrue(ec.get("prop") instanceof Property);

            assertTrue(null != ec.get("pt_one"));
            assertTrue(ec.get("pt_one") instanceof PartType);

            assertTrue(((PartType) ec.get("pt_one")).getProperty("prop").equals(ec.get("prop")));

        } catch (EugeneException ee) {
            ee.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testPartTypeWithManyPropertiesDeclaration() {
        int NR_PROPERTIES = 100;
        int NR_REQUIRED_PROPERTIES = 3; //SEQUENCE, PIGEON, SEQUENCE_ONTOLOGY_PROPERTY

        PartType pt_many = new PartType("pt_many");

        StringBuilder sbProps = new StringBuilder();
        StringBuilder sbPropAssign = new StringBuilder();
        for (int i = 1; i <= NR_PROPERTIES; i++) {

            // assigning property to part type
            pt_many.getProperties().add(
              new Property("prop", EugeneConstants.TXT));

            // building script
            sbProps.append("Property prop" + i + "(txt);");

            sbPropAssign.append("prop" + i);
            if (i <= NR_PROPERTIES - 1) {
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

            assertTrue(null != ec);
            // 2 elements: prop, pt_one
            assertTrue(ec.getElements().size() == NR_PROPERTIES + 1);

            for (int i = 1; i <= NR_PROPERTIES; i++) {
                assertTrue(null != ec.get("prop" + i));
                assertTrue(ec.get("prop" + i) instanceof Property);
            }

            assertTrue(null != ec.get("pt_many"));
            assertTrue(ec.get("pt_many") instanceof PartType);

            assertTrue(((PartType) ec.get("pt_many")).getProperties().size() == NR_PROPERTIES + NR_REQUIRED_PROPERTIES);
            // +3 ... SEQUENCE, PIGEON, and SEQUENCE_ONTOLOGY_PROPERTY

//			assertTrue(((PartType)ec.get("pt_one")).getProperty("prop").equals(ec.get("prop")));
        } catch (EugeneException ee) {
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
        } catch (DOMException de) {
            assertTrue(false);
        }
        PropertyValue pt_p1_txt = new PropertyValue(txt_prop);
        pt_p1_txt.setTxt("one");
        try {
            pt_p1.setPropertyValue(txt_prop, pt_p1_txt);
        } catch (DOMException de) {
            assertTrue(false);
        }

        Part pt_p2 = new Part("pt_p2", PT);
        PropertyValue pt_p2_num = new PropertyValue(num_prop);
        pt_p2_num.setNum(2);
        try {
            pt_p2.setPropertyValue(num_prop, pt_p2_num);
        } catch (DOMException de) {
            assertTrue(false);
        }
        PropertyValue pt_p2_txt = new PropertyValue(txt_prop);
        pt_p2_txt.setTxt("two");
        try {
            pt_p2.setPropertyValue(txt_prop, pt_p2_txt);
        } catch (DOMException de) {
            assertTrue(false);
        }

        String script
          = "Property num_prop(num); Property txt_prop(txt); "
          + "PartType PT(num_prop, txt_prop); "
          + "PT pt_p1(.num_prop(1), .txt_prop(\"one\")); "
          + "PT pt_p2(.num_prop(2), .txt_prop(\"two\")); ";

        try {
            EugeneCollection ec = this.eugene.executeScript(script.toString());

            // pt_p1.pt_p1_num
            assertTrue(ec.get("pt_p1") instanceof Part);

            assertTrue(((Part) ec.get("pt_p1")).getPropertyValue("num_prop") != null);
            assertTrue(EugeneConstants.NUM.equals(
              ((Part) ec.get("pt_p1")).getPropertyValue("num_prop").getType()));
            assertTrue(1 == ((Part) ec.get("pt_p1")).getPropertyValue("num_prop").getNum());

            // pt_p1.pt_p1_txt
            assertTrue(ec.get("pt_p1") instanceof Part);
            assertTrue(((Part) ec.get("pt_p1")).getPropertyValue("txt_prop") != null);
            assertTrue(EugeneConstants.TXT.equals(
              ((Part) ec.get("pt_p1")).getPropertyValue("txt_prop").getType()));
            assertTrue("one".equals(((Part) ec.get("pt_p1")).getPropertyValue("txt_prop").getTxt()));

            // pt_p2.pt_p2_num
            assertTrue(ec.get("pt_p2") instanceof Part);
            assertTrue(((Part) ec.get("pt_p2")).getPropertyValue("num_prop") != null);
            assertTrue(EugeneConstants.NUM.equals(
              ((Part) ec.get("pt_p2")).getPropertyValue("num_prop").getType()));
            assertTrue(2 == ((Part) ec.get("pt_p2")).getPropertyValue("num_prop").getNum());

            // pt_p2.pt_p2_txt
            assertTrue(ec.get("pt_p2") instanceof Part);
            assertTrue(((Part) ec.get("pt_p2")).getPropertyValue("txt_prop") != null);
            assertTrue(EugeneConstants.TXT.equals(
              ((Part) ec.get("pt_p2")).getPropertyValue("txt_prop").getType()));
            assertTrue("two".equals(((Part) ec.get("pt_p2")).getPropertyValue("txt_prop").getTxt()));

        } catch (EugeneException ee) {
            assertTrue(false);
        }
    }

    @Test
    public void testPartSequenceProperty() {

        String seq = "ATCG";

        PartType PT = new PartType("PT");
        Part p1 = new Part(PT, "p1");
        p1.setSequence(seq);

        try {

            assertTrue(seq.equals(p1.getPropertyValue(EugeneConstants.SEQUENCE_PROPERTY).getTxt()));

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    // TO DO: VALIDATE BLANK SO NUMBERS
    @Test
    public void testPartPigeonProperty() {

        String pig = "p p1 1 nl";

        PartType PT = new PartType("PT");
        Part p1 = new Part(PT, "p1");

        Property pigProp = EugeneDeveloperUtils.createPigeonProperty();
        PropertyValue pigVal = new PropertyValue(pigProp);
        pigVal.setTxt(pig);

        try {

            p1.setPropertyValue(pigProp, pigVal);

            assertTrue(pig.equals(p1.getPropertyValue(EugeneConstants.PIGEON_PROPERTY).getTxt()));

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void testNotDeclared_device_product() {
        String script = "Device D; product(X);";

        try {
            new Eugene().executeScript(script);
        } catch (EugeneException ee) {
            assertTrue(ee.getMessage().contains("X is not declared."));
        }
    }

    @Test
    public void testNotDevice_product() {
        String script = "num i; Device D; product(i);";

        try {
            new Eugene().executeScript(script);
        } catch (EugeneException ee) {
            assertTrue(ee.getMessage().contains("i is not a Device."));
        }
    }

    /*----------------------------
	 * COMPOSITE BIOLOGICAL FACTS
	 * 
	 * Section 3.1.4
	 *----------------------------*/
    @Test
    public void testCompositeDevice_orientations() {
        String script
          = "PartType Promoter(); "
          + "Promoter I14018; "
          + "Device proms(I14018, I14018); "
          + "Device fproms(+I14018, +I14018); "
          + "Device rproms(-I14018, -I14018); "
          + "Device hierarchical(proms, +I14018); ";

        try {
            EugeneCollection ec = new Eugene().executeScript(script);

            assertTrue(null != ec);

            /*-------
			 * proms Device
			 *-------*/
            assertTrue(null != ec.get("proms"));
            assertTrue(ec.get("proms") instanceof Device);
            Device proms = (Device) ec.get("proms");
            assertTrue(null != proms.getOrientations());
            assertTrue(proms.getOrientations().size() == 2);
            assertTrue(null != proms.getOrientations(0));
            assertTrue(proms.getOrientations(0).size() == 1);
            assertTrue(proms.getOrientations(0).get(0) == Orientation.UNDEFINED);
            assertTrue(null != proms.getOrientations(1));
            assertTrue(proms.getOrientations(1).size() == 1);
            assertTrue(proms.getOrientations(1).get(0) == Orientation.UNDEFINED);

            /*-------
			 * fproms Device
			 *-------*/
            assertTrue(null != ec.get("fproms"));
            assertTrue(ec.get("fproms") instanceof Device);
            Device fproms = (Device) ec.get("fproms");
            assertTrue(null != fproms.getOrientations());
            assertTrue(fproms.getOrientations().size() == 2);
            assertTrue(null != fproms.getOrientations(0));
            assertTrue(fproms.getOrientations(0).size() == 1);
            assertTrue(fproms.getOrientations(0).get(0) == Orientation.FORWARD);
            assertTrue(null != fproms.getOrientations(1));
            assertTrue(fproms.getOrientations(1).size() == 1);
            assertTrue(fproms.getOrientations(1).get(0) == Orientation.FORWARD);

            /*-------
			 * rproms Device
			 *-------*/
            assertTrue(null != ec.get("rproms"));
            assertTrue(ec.get("rproms") instanceof Device);
            Device rproms = (Device) ec.get("rproms");
            assertTrue(null != rproms.getOrientations());
            assertTrue(rproms.getOrientations().size() == 2);
            assertTrue(null != rproms.getOrientations(0));
            assertTrue(rproms.getOrientations(0).size() == 1);
            assertTrue(rproms.getOrientations(0).get(0) == Orientation.REVERSE);
            assertTrue(null != rproms.getOrientations(1));
            assertTrue(rproms.getOrientations(1).size() == 1);
            assertTrue(rproms.getOrientations(1).get(0) == Orientation.REVERSE);

            /*-------
			 * hierarchical Device
			 *-------*/
            assertTrue(null != ec.get("hierarchical"));
            assertTrue(ec.get("hierarchical") instanceof Device);
            Device hierarchical = (Device) ec.get("hierarchical");
            assertTrue(null != hierarchical.getOrientations());
            assertTrue(hierarchical.getOrientations().size() == 2);
            assertTrue(null != hierarchical.getOrientations(0));
            assertTrue(hierarchical.getOrientations(0).size() == 1);
            assertTrue(hierarchical.getOrientations(0).get(0) == Orientation.UNDEFINED);
            assertTrue(null != hierarchical.getOrientations(1));
            assertTrue(hierarchical.getOrientations(1).size() == 1);
            assertTrue(hierarchical.getOrientations(1).get(0) == Orientation.FORWARD);
        } catch (EugeneException ee) {
            // no exception allowed here
            assertTrue(false);
        }

    }

    /*----------------------------
	 * COMPOSITE BIOLOGICAL FACTS
	 * 
	 * Section 3.1.4
	 *----------------------------*/
    @Test
    public void testInteractions_represses() {
        String script
          = "PartType Promoter(); "
          + "PartType Repressor(); "
          + "Repressor r1; Repressor r2; Repressor r3; "
          + "Promoter p1; Promoter p2; Promoter p3; "
          + "r1 REPRESSES p1; r2 represses p2; r3 REPRESSES p3; ";
        try {
            new Eugene().executeScript(script);
        } catch (EugeneException ee) {
            ee.printStackTrace();
            assertTrue(false);
        }
    }
}
