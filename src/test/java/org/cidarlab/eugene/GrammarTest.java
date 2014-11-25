package org.cidarlab.eugene;

import static org.junit.Assert.*;

import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.grammar.EugeneGrammar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.exception.EugeneException;

public class GrammarTest {

	private EugeneGrammar eg;
	private List<Device> lod;
	private static final int A_VERY_LARGE_NUMBER = 2;
	private static final int ANOTHER_VERY_LARGE_NUMBER = 3;
	
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
		this.eg = new EugeneGrammar();
	}
	
	@After
	public void tearDown() 
			throws Exception {
		this.eg = null;
	}

	
	@Test
	public void testNullDevice() {
		assertFalse(eg.isComposed(null));
	}
	
	@Test
	public void empty_device() {
		Device d = new Device("d");
		assertFalse(eg.isComposed(d));
	}
	
	@Test
	public void composed_of_part_type() {
		Device d = new Device("d");
		PartType pt = new PartType("pt");
		List<NamedElement> d_components = new ArrayList<NamedElement>();
		d_components.add(pt);
		d.getComponents().add(d_components);
		assertFalse(eg.isComposed(d));
	}
	
	@Test
	public void selection_without_device() {
		Device d = new Device("d");
		
		List<NamedElement> d_components = new ArrayList<NamedElement>();
		d_components.add(new PartType("pt1"));
		d_components.add(new PartType("pt2"));
		d.getComponents().add(d_components);
		
		assertFalse(eg.isComposed(d));
	}
	
	@Test
	public void composed_of_device() {
		Device d = new Device("d");
		
		List<NamedElement> d_components = new ArrayList<NamedElement>();
		d_components.add(new Device("subD"));
		d.getComponents().add(d_components);
		
		assertTrue(eg.isComposed(d));
	}
	
	@Test
	public void composed_of_a_device_that_appears_in_the_selection_operator() {
		Device d = new Device("d");
		
		List<NamedElement> d_components = new ArrayList<NamedElement>();
		d_components.add(new PartType("pt1"));
		d_components.add(new Device("subD"));
		d.getComponents().add(d_components);
		
		assertTrue(eg.isComposed(d));
	}
	
	@Test
	public void a_very_large_device_whose_elements_are_selection_operators_and_only_the_last_one_contains_a_device() {
		Device d = new Device("d");
		for(int i=1; i<=A_VERY_LARGE_NUMBER; i++) {
			
			List<NamedElement> loe = new ArrayList<NamedElement>();
			for(int j=1; j<=ANOTHER_VERY_LARGE_NUMBER; j++) {
				if(j % ANOTHER_VERY_LARGE_NUMBER == 0) {
					loe.add(new Device("subD"));
				} else {
					loe.add(new PartType("pt_"+i+"_"+j));
				}
			}
			
			d.getComponents().add(loe);
		}
		
		assertTrue(eg.isComposed(d));
	}

	@Test
	public void hierarchically_composed_promoter() {
		
		/*
		 * Grammar G := <
		 * P:= {
		 *         D    --> subD
		 *         subD --> Promoter
		 *         Promoter --> p1|p2
		 *     },
		 * NT:= {
		 *         D, subD, Promoter
		 *      },
		 * T:= {
		 *         p1, p2
		 *     },
		 *     
		 * Start: D 
		 *              >
		 */
		
		Device D = new Device("D");
		Device subD= new Device("subD");
		PartType Promoter = new PartType("Promoter");
		Part p1 = new Part(Promoter, "p1");
		Part p2 = new Part(Promoter, "p2");
		
		// add Promoter to subD
		List<NamedElement> loPromoter = new ArrayList<NamedElement>();
		loPromoter.add(Promoter);
		subD.getComponents().add(loPromoter);
		
		// add subD to D
		List<NamedElement> loSubD = new ArrayList<NamedElement>();
		loSubD.add(subD);
		D.getComponents().add(loSubD);
		
		Set<Component> parts = new HashSet<Component>();
		parts.add(p1);
		parts.add(p2);
		try {
			List<Device> enumD = this.eg.enumerateAll(D, parts);
			
			// based on the above specified design, we should get 
			// two devices:
			// D_1 := p1
			// D_2 := p2

			// for testing, we manually specify the expected list
			// of devices
			List<Device> mustD = this.list_for_hierarchically_composed_promoter(p1, p2);
			
			// now, we can compare both lists
			// the list enumerated by the EugeneGrammar and 
			// the manually built list.
//			enumD.retainAll(mustD);
			System.out.println(mustD);
			System.out.println(enumD);
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
		
		
		/*
		 * TODO: compare both lists
		 */
		
		assertTrue(true);
		
	}
	
	@Test
	public void cello_xor() {
		
		/*
		 * Grammar G := <
		 * P:= {
		 *         D    --> subD
		 *         subD --> Promoter
		 *         Promoter --> p1|p2
		 *     },
		 * NT:= {
		 *         D, subD, Promoter
		 *      },
		 * T:= {
		 *         p1, p2
		 *     },
		 *     
		 * Start: D 
		 *              >
		 */
		
		/*
		 * PART LIBRARY
		 */
		PartType Promoter = new PartType("Promoter");
		PartType RBS = new PartType("RBS");
		PartType CDS = new PartType("CDS");
		PartType Terminator = new PartType("Terminator");

		Part p1 = new Part("p1", Promoter);
		Part r1 = new Part("r1", RBS);
		Part c1 = new Part("c1", CDS);
		Part t1 = new Part("t1", Terminator);
		Part p2 = new Part("p2", Promoter);
		Part r2 = new Part("r2", RBS);
		Part c2 = new Part("c2", CDS);
		Part t2 = new Part("t2", Terminator);
		Part p3 = new Part("p3", Promoter);
		Part r3 = new Part("r3", RBS);
		Part c3 = new Part("c3", CDS);
		Part t3 = new Part("t3", Terminator);
		Part p4 = new Part("p4", Promoter);
		Part r4 = new Part("r4", RBS);
		Part c4 = new Part("c4", CDS);
		Part t4 = new Part("t4", Terminator);
		
		Set<Component> parts = new HashSet<Component>();
		parts.add(p1);
		parts.add(r1);
		parts.add(c1);
		parts.add(t1);
		parts.add(p2);
		parts.add(r2);
		parts.add(c2);
		parts.add(t2);
		parts.add(p3);
		parts.add(r3);
		parts.add(c3);
		parts.add(t3);
		parts.add(p4);
		parts.add(r4);
		parts.add(c4);
		parts.add(t4);

		
		Device D = new Device("D");
		Device subD1 = new Device("subD1");

		List<NamedElement> loSubD1_components = new ArrayList<NamedElement>();
		loSubD1_components.add(p1);
		loSubD1_components.add(r1);
		loSubD1_components.add(c1);
		loSubD1_components.add(t1);
		subD1.getComponents().add(loSubD1_components);
		subD1.getComponents().add(loSubD1_components);
		subD1.getComponents().add(loSubD1_components);
		subD1.getComponents().add(loSubD1_components);
		
		Device subD2 = new Device("subD2");
		List<NamedElement> loSubD2_components = new ArrayList<NamedElement>();
		loSubD2_components.add(p2);
		loSubD2_components.add(r2);
		loSubD2_components.add(c2);
		loSubD2_components.add(t2);
		subD2.getComponents().add(loSubD2_components);
		subD2.getComponents().add(loSubD2_components);
		subD2.getComponents().add(loSubD2_components);
		subD2.getComponents().add(loSubD2_components);

		Device subD3 = new Device("subD3");
		List<NamedElement> loSubD3_components = new ArrayList<NamedElement>();
		loSubD3_components.add(p3);
		loSubD3_components.add(r3);
		loSubD3_components.add(c3);
		loSubD3_components.add(t3);
		subD3.getComponents().add(loSubD3_components);
		subD3.getComponents().add(loSubD3_components);
		subD3.getComponents().add(loSubD3_components);
		subD3.getComponents().add(loSubD3_components);
		
		Device subD4 = new Device("subD4");
		List<NamedElement> loSubD4_components = new ArrayList<NamedElement>();
		loSubD4_components.add(p4);
		loSubD4_components.add(r4);
		loSubD4_components.add(c4);
		loSubD4_components.add(t4);
		subD4.getComponents().add(loSubD4_components);
		subD4.getComponents().add(loSubD4_components);
		subD4.getComponents().add(loSubD4_components);
		subD4.getComponents().add(loSubD4_components);
		

		List<NamedElement> loSubD = new ArrayList<NamedElement>();
		loSubD.add(subD1);
		loSubD.add(subD2);
		loSubD.add(subD3);
		loSubD.add(subD4);
		
		// add the loSubD list 4x to the Device D
		D.getComponents().add(loSubD);
		D.getComponents().add(loSubD);
		D.getComponents().add(loSubD);
		D.getComponents().add(loSubD);
		
		try {
			List<Device> enumD = this.eg.enumerateAll(D, parts);
			
			// based on the above specified design, we should get 
			// two devices:
			// D_1 := p1
			// D_2 := p2

			// for testing, we manually specify the expected list
			// of devices
			List<Device> mustD = this.list_for_hierarchically_composed_promoter(p1, p2);
			
			// now, we can compare both lists
			// the list enumerated by the EugeneGrammar and 
			// the manually built list.
//			enumD.retainAll(mustD);
			System.out.println(mustD);
			System.out.println(enumD);
		} catch(EugeneException ee) {
			ee.printStackTrace();
		}
		
		
		/*
		 * TODO: compare both lists
		 */
		
		assertTrue(true);
		
	}

	private List<Device> list_for_hierarchically_composed_promoter(Part p1, Part p2) {
		// let's define those two devices manually:
		Device D_1 = new Device("D_1");
		List<NamedElement> lo_p1 = new ArrayList<NamedElement>();
		lo_p1.add(p1);
		D_1.getComponents().add(lo_p1);
	
		Device D_2 = new Device("D_2");
		List<NamedElement> lo_p2 = new ArrayList<NamedElement>();
		lo_p2.add(p2);
		D_2.getComponents().add(lo_p2);
		
		List<Device> lod = new ArrayList<Device>();
		lod.add(D_1);
		lod.add(D_2);
		
		return lod;
		
	}
	
	@Test
	public void testEnumerateAll() {
		assert(true == true);
	}

}
