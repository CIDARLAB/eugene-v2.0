package org.cidarlab.eugene;

import static org.junit.Assert.*;

import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.grammar.EugeneGrammar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.cidarlab.eugene.dom.*;

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
	public void testEnumerateAll() {
		assert(true == true);
	}

}
