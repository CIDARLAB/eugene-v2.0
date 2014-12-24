package org.cidarlab.sparrow.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.dom.PartType;
import org.cidarlab.eugene.dom.Property;
import org.cidarlab.eugene.dom.PropertyValue;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.constants.SparrowConstants;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.rule.EugeneRule;
import org.cidarlab.sparrow.rule.Predicate;

public class Genomatica {

	private Sparrow sparrow;
	
	public Genomatica() 
			throws EugeneException {
		
		try {
			this.sparrow = new Sparrow();
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(
					"Something's wrong with the Part Library Management System: "+
							e.getMessage());
		}
	}
	
	public void populatePartLibrary() 
			throws EugeneException {
		/*
		 * first, we define the part types and its properties
		 */
		
		// every part type has the following two properties of type TXT:
		Property nameProp = new Property("name", SparrowConstants.TXT);
		Property sequenceProp = new Property("sequence", SparrowConstants.TXT);
		Property pigeonProp = new Property("pigeon", SparrowConstants.TXT);

		// and we have the following part types:
		// Spacer
		PartType spacer = new PartType("Spacer");
		spacer.getProperties().add(nameProp);
		spacer.getProperties().add(sequenceProp);
		spacer.getProperties().add(pigeonProp);
		this.spacers(spacer);
		
		// Ribozyme
		PartType ribozyme = new PartType("Ribozyme");
		ribozyme.getProperties().add(nameProp);
		ribozyme.getProperties().add(sequenceProp);
		ribozyme.getProperties().add(pigeonProp);
		this.ribozymes(ribozyme);
		
		// RBS
		PartType rbs = new PartType("RBS");
		rbs.getProperties().add(nameProp);
		rbs.getProperties().add(sequenceProp);
		rbs.getProperties().add(pigeonProp);
		this.rbss(rbs);

		// Promoter
		PartType promoter = new PartType("Promoter");
		promoter.getProperties().add(nameProp);
		promoter.getProperties().add(sequenceProp);
		promoter.getProperties().add(pigeonProp);
		this.promoters(promoter);

		// Leader
		PartType leader = new PartType("Leader");
		leader.getProperties().add(nameProp);
		leader.getProperties().add(sequenceProp);
		leader.getProperties().add(pigeonProp);
		this.leaders(leader);

		// CDS
		PartType cds = new PartType("CodingSequence");
		cds.getProperties().add(nameProp);
		cds.getProperties().add(sequenceProp);
		cds.getProperties().add(pigeonProp);
		this.cdss(cds);
		
		// Terminator
		PartType terminator = new PartType("Terminator");
		terminator.getProperties().add(nameProp);
		terminator.getProperties().add(sequenceProp);
		terminator.getProperties().add(pigeonProp);
		this.terminators(terminator);
		
		
		/*
		 * FOR TESTING
		 */
//		this.sparrow.printFacts();
		
	}
	
	/*
	 * SPACERS
	 */
	private void spacers(PartType spacer) 
			throws EugeneException {
		
		// spacer1
		Part sp1 = new Part(spacer, "spacer1");
		PropertyValue sp1_name = new PropertyValue(spacer.getProperty("name"));
		sp1_name.setTxt("spacer1");		
		PropertyValue sp1_seq = new PropertyValue(spacer.getProperty("sequence"));
		sp1_seq.setTxt("a");		

		try {
			sp1.setPropertyValue(spacer.getProperty("name"), sp1_name);
			sp1.setPropertyValue(spacer.getProperty("sequence"), sp1_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(sp1);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		
		// spacer2
		Part sp2 = new Part(spacer, "spacer2");
		PropertyValue sp2_name = new PropertyValue(spacer.getProperty("name"));
		sp2_name.setTxt("spacer2");		
		PropertyValue sp2_seq = new PropertyValue(spacer.getProperty("sequence"));
		sp2_seq.setTxt("aa");		

		try {
			sp2.setPropertyValue(spacer.getProperty("name"), sp2_name);
			sp2.setPropertyValue(spacer.getProperty("sequence"), sp2_seq);
			
			// insert the spacer2 part into the WM
			this.sparrow.insertFact(sp2);
		} catch(Exception e) {
			throw new EugeneException(e.getMessage());
		}
	}
	

	/*
	 * RIBOZYMES
	 */
	private void ribozymes(PartType ribozyme) 
			throws EugeneException {
		
		// ribozyme1
		Part ribozyme1 = new Part(ribozyme, "ribozyme1");
		PropertyValue rb1_name = new PropertyValue(ribozyme.getProperty("name"));
		rb1_name.setTxt("ribozyme1");		
		PropertyValue rb1_seq = new PropertyValue(ribozyme.getProperty("sequence"));
		rb1_seq.setTxt("a");		

		try {
			ribozyme1.setPropertyValue(ribozyme.getProperty("name"), rb1_name);
			ribozyme1.setPropertyValue(ribozyme.getProperty("sequence"), rb1_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(ribozyme1);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		
		// emptyRibozyme
		Part emptyRibozyme = new Part(ribozyme, "emptyRibozyme");
		PropertyValue er_name = new PropertyValue(ribozyme.getProperty("name"));
		er_name.setTxt("");		
		PropertyValue er_seq = new PropertyValue(ribozyme.getProperty("sequence"));
		er_seq.setTxt("");		

		try {
			emptyRibozyme.setPropertyValue(ribozyme.getProperty("name"), er_name);
			emptyRibozyme.setPropertyValue(ribozyme.getProperty("sequence"), er_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(emptyRibozyme);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}

	/*
	 * LEADERS
	 */
	private void leaders(PartType leader) 
			throws EugeneException {
		
		// leader1
		Part leader1 = new Part(leader, "leader1");
		PropertyValue rb1_name = new PropertyValue(leader.getProperty("name"));
		rb1_name.setTxt("leader1");		
		PropertyValue rb1_seq = new PropertyValue(leader.getProperty("sequence"));
		rb1_seq.setTxt("a");		

		try {
			leader1.setPropertyValue(leader.getProperty("name"), rb1_name);
			leader1.setPropertyValue(leader.getProperty("sequence"), rb1_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(leader1);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		
		// emptyleader
		Part emptyleader = new Part(leader, "emptyLeader");
		PropertyValue er_name = new PropertyValue(leader.getProperty("name"));
		er_name.setTxt("");		
		PropertyValue er_seq = new PropertyValue(leader.getProperty("sequence"));
		er_seq.setTxt("");		

		try {
			emptyleader.setPropertyValue(leader.getProperty("name"), er_name);
			emptyleader.setPropertyValue(leader.getProperty("sequence"), er_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(emptyleader);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}

	/*
	 * RBSs
	 */
	private void rbss(PartType rbs) 
			throws EugeneException {
		
		// RBS1
		Part rbs1 = new Part(rbs, "rbs1");
		PropertyValue rb1_name = new PropertyValue(rbs.getProperty("name"));
		rb1_name.setTxt("rbs1");		
		PropertyValue rb1_seq = new PropertyValue(rbs.getProperty("sequence"));
		rb1_seq.setTxt("a");		

		try {
			rbs1.setPropertyValue(rbs.getProperty("name"), rb1_name);
			rbs1.setPropertyValue(rbs.getProperty("sequence"), rb1_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(rbs1);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		// RBS2
		Part rbs2 = new Part(rbs, "rbs2");
		PropertyValue rb2_name = new PropertyValue(rbs.getProperty("name"));
		rb2_name.setTxt("rbs2");		
		PropertyValue rb2_seq = new PropertyValue(rbs.getProperty("sequence"));
		rb2_seq.setTxt("aa");		

		try {
			rbs2.setPropertyValue(rbs.getProperty("name"), rb2_name);
			rbs2.setPropertyValue(rbs.getProperty("sequence"), rb2_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(rbs2);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		
		// emptyRBS
		Part emptyRBS = new Part(rbs, "emptyRBS");
		PropertyValue er_name = new PropertyValue(rbs.getProperty("name"));
		er_name.setTxt("");		
		PropertyValue er_seq = new PropertyValue(rbs.getProperty("sequence"));
		er_seq.setTxt("");		

		try {
			emptyRBS.setPropertyValue(rbs.getProperty("name"), er_name);
			emptyRBS.setPropertyValue(rbs.getProperty("sequence"), er_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(emptyRBS);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	/*
	 * PROMOTERS
	 */
	public void promoters(PartType promoter) 
			throws EugeneException {

		// promoter1
		Part promoter1 = new Part(promoter, "promoter1");
		PropertyValue rb1_name = new PropertyValue(promoter.getProperty("name"));
		rb1_name.setTxt("promoter1");		
		PropertyValue rb1_seq = new PropertyValue(promoter.getProperty("sequence"));
		rb1_seq.setTxt("a");		

		try {
			promoter1.setPropertyValue(promoter.getProperty("name"), rb1_name);
			promoter1.setPropertyValue(promoter.getProperty("sequence"), rb1_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(promoter1);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		// promoter2
		Part promoter2 = new Part(promoter, "promoter2");
		PropertyValue rb2_name = new PropertyValue(promoter.getProperty("name"));
		rb2_name.setTxt("promoter2");		
		PropertyValue rb2_seq = new PropertyValue(promoter.getProperty("sequence"));
		rb2_seq.setTxt("aa");		

		try {
			promoter2.setPropertyValue(promoter.getProperty("name"), rb2_name);
			promoter2.setPropertyValue(promoter.getProperty("sequence"), rb2_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(promoter2);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}
	
	/*
	 * CODING SEQUENCES
	 */
	private void cdss(PartType cds) 
			throws EugeneException {
		// GFP
		Part gfp = new Part(cds, "GFP");
		PropertyValue gfp_name = new PropertyValue(cds.getProperty("name"));
		gfp_name.setTxt("GFP");		
		PropertyValue gfp_seq = new PropertyValue(cds.getProperty("sequence"));
		gfp_seq.setTxt("aaaa");		

		try {
			gfp.setPropertyValue(cds.getProperty("name"), gfp_name);
			gfp.setPropertyValue(cds.getProperty("sequence"), gfp_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(gfp);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		// RFP
		Part rfp = new Part(cds, "RFP");
		PropertyValue rfp_name = new PropertyValue(cds.getProperty("name"));
		rfp_name.setTxt("RFP");		
		PropertyValue rfp_seq = new PropertyValue(cds.getProperty("sequence"));
		rfp_seq.setTxt("aaaa");		

		try {
			rfp.setPropertyValue(cds.getProperty("name"), rfp_name);
			rfp.setPropertyValue(cds.getProperty("sequence"), rfp_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(rfp);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}

		// YFP
		Part yfp = new Part(cds, "YFP");
		PropertyValue yfp_name = new PropertyValue(cds.getProperty("name"));
		yfp_name.setTxt("YFP");		
		PropertyValue yfp_seq = new PropertyValue(cds.getProperty("sequence"));
		yfp_seq.setTxt("aaaa");		

		try {
			yfp.setPropertyValue(cds.getProperty("name"), yfp_name);
			yfp.setPropertyValue(cds.getProperty("sequence"), yfp_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(yfp);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
		
	}

	/*
	 * TERMINATORS
	 */
	private void terminators(PartType terminator) 
			throws EugeneException {
		// YFP
		Part term1 = new Part(terminator, "term1");
		PropertyValue term1_name = new PropertyValue(terminator.getProperty("name"));
		term1_name.setTxt("term1");		
		PropertyValue term1_seq = new PropertyValue(terminator.getProperty("sequence"));
		term1_seq.setTxt("aaaa");		

		try {
			term1.setPropertyValue(terminator.getProperty("name"), term1_name);
			term1.setPropertyValue(terminator.getProperty("sequence"), term1_seq);

			// insert the spacer1 part into the WM
			this.sparrow.insertFact(term1);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.getMessage());
		}
	}

	
	/*
	 * create devices based on the parts in the part library
	 * 
	 * here, we utilize miniEugene
	 * 
	 */
	public void createDevices() 
			throws EugeneException {
		
		MiniEugene me = new MiniEugene();
		
		String script = this.buildMiniEugeneScript();
		try {
			me.solve(script);
			
			me.getStatistics().print();
			
			/*
			 * process the solutions
			 * i.e. we convert every component array that miniEugene returns
			 * into a Eugene Device object and store it into the WM
			 */
			if(null != me.getSolutions() && !me.getSolutions().isEmpty()) {
				int nr = 1;
				for(org.cidarlab.minieugene.dom.Component[] solution : me.getSolutions()) {
					try {
						this.sparrow.insertFact(
								this.toEugeneDevice("device_"+nr, solution));
					} catch(Exception e) {
						throw new EugeneException(e.getMessage());
					}
					
					nr++;
				}
				
			}
		} catch(MiniEugeneException ee) {
			throw new EugeneException(ee.getMessage());
		}
	}

	/*
	 * that's the devices' topology:
	 * Spacer, Promoter, RBS, Ribozyme, RBS, Leader, CodingSequence, Terminator, Spacer
	 */
	private String buildMiniEugeneScript() {
		StringBuilder sb = new StringBuilder();
		
		/*
		 * for the time being, we ``hard-code'' the miniEugene script
		 */
		sb.append("N=9. all_forward. ")
			.append("TEMPLATE [spacer1|spacer2], [promoter1|promoter2], [rbs1|rbs2|emptyRBS], ")
			.append("[ribozyme1|emptyRibozyme], [rbs1|rbs2|emptyRBS], [leader1|emptyLeader], ")
			.append("[GFP|YFP|RFP], term1, [spacer1|spacer2].");
		return sb.toString();
	}

	/*
	 * caching => for better efficiency of querying Drools
	 * 
	 * we need to move the caching into Sparrow
	 */
	private HashMap<String, Part> cache;
	private Device toEugeneDevice(String name, org.cidarlab.minieugene.dom.Component[] solution) {
		Device device = new Device(name);
		
		for(int i=0; i<solution.length; i++) {
			try {
				List<Component> components = new ArrayList<Component>();
				components.add(toEugenePart(solution[i]));
				device.addComponents(components);
			} catch(Exception e) {
			}
		}
		
		return device;
	}	
	
	private org.cidarlab.eugene.dom.Part toEugenePart(org.cidarlab.minieugene.dom.Component component) {
		if(null == cache) {
			this.cache = new HashMap<String, Part>();
		}
		
		if(cache.containsKey(component.getName())) {
			return cache.get(component.getName());
		} else {
			try {
				List<Component> lst = this.sparrow.query(
					new EugeneRule(
							"MUSS_BESSER_WERDEN",
							new Predicate(new Property("name", SparrowConstants.TXT), "==", component.getName())));
				Part p = (Part)lst.get(0);

				/*
				 *  ORIENTATION 
				 */
				if(component.isForward()) {
					p.setOrientation(Orientation.FORWARD);
				} else {
					p.setOrientation(Orientation.REVERSE);
				}

				this.cache.put(component.getName(), p);

				return p;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	

	public void pruneDevices() 
			throws SparrowException {
		
		this.sparrow.fireRules("genomatica.drl");
		
	}
	
	public static void main(String[] args) 
			throws Exception {
		Genomatica gm = new Genomatica();
		
		/*
		 * STEP 1: population of the part library
		 */
		try {
			gm.populatePartLibrary();
		} catch(EugeneException ee) {
			ee.printStackTrace();
			System.exit(1);
		}
		
		/*
		 * STEP 2: create devices (using miniEugene)
		 */
		try {
			gm.createDevices();
		} catch(EugeneException ee) {
			ee.printStackTrace();
			System.exit(2);
		}
		
		/*
		 * STEP 3: prune devices (using Sparrow)
		 */
		try {
			gm.pruneDevices();			
		} catch(Exception ee) {
			ee.printStackTrace();
			System.exit(2);
		}
	
	}

}
