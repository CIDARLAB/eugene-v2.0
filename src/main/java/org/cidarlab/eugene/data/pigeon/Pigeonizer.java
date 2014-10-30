/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.data.pigeon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.cidarlab.eugene.Eugene;
import org.cidarlab.eugene.constants.Orientation;
import org.cidarlab.eugene.dom.Device;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.Part;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.dom.imp.container.EugeneContainer;
import org.cidarlab.minieugene.constants.PredefinedTypes;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.interaction.Interaction;
import org.cidarlab.minieugene.predicates.interaction.Participation;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;

/**
 * The Pigeonizer class serves for compiling 
 * a EugeneContainer or a Device into a Pigeon script.
 *
 * Optionally, a coloring map can be specified that contains 
 * key-value pairs. The key represents the name of the component (e.g. p1) 
 * and the value is a non-negative integer representing a 
 * Pigeon color.
 * Example: the pair <p1, 14> will draw the p1 promoter black.
 *  
 * Besides, Eugene Parts can have a property name PIGEON (lower- or upper-case)
 * that encapsulates the Pigeon statement of the corresponding part.
 * Example: Promoter p1(.Pigeon("p p1 14 nl")) indicates that the part p1 should be 
 *          visualized non-labeled, black, and using the Promoter SBOLv symbol.
 * 
 * @author Ernst Oberortner
 *
 */
public class Pigeonizer {

	private Map<String, Integer> colors;
	private boolean label;
	
	private static final String PIGEON_PROPERTY = "PIGEON";
	
	/**
	 * default constructor
	 * 
	 * i.e. random coloring + labeling
	 */
	public Pigeonizer() {
		colors = new HashMap<String, Integer>();
		this.label = true;
	}
	
	/**
	 * 
	 * @param colors ... the desired color coding map in that the keys are the part names and the values are the colors [1-14]  
	 * @param label  ... if true, then the Pigeon symbols will be labeled
	 */
	public Pigeonizer(Map<String, Integer> colors, boolean label) {

		if(null == colors) {
			this.colors = new HashMap<String, Integer>();
		} else {
			this.colors = colors;
		}
		this.label = label;
	}


	/**
	 * The pigeonize/2 method visualizes all devices in the given 
	 * device container.
	 * 
	 * Therefore, we first compile the solutions and the interactions into a Pigeon script,
	 * send the script to the Pigeon server, which returns an URL of the resulting 
	 * Pigeon image. 
	 * 
	 * The pigeonize/2 method returns the URL of the Pigeon image that resides on 
	 * the Pigeon server.
	 * 
	 * @param solutions     ... a list of solutions
	 * @param interactions  ... a list of interactions (i.e. the # Arcs)
	 * @return              ... a URI of the Pigeon image
	 * @throws MiniEugeneException
	 */
	public URI pigeonize(EugeneContainer ec) 
			throws EugeneException {
		
		if(null == ec) {
			throw new EugeneException("I cannot pigeonize an empty set of devices!");
		} else if(ec.getElements().isEmpty()) {
			return null;
		}

		/*
		 * Compilation to Pigeon script
		 */
		List<URI> uris = new ArrayList<URI>();
		
		Iterator<NamedElement> it = ec.getElements().iterator();
		while(it.hasNext()) {

			NamedElement ne = it.next();
			if(ne instanceof Device) {
				uris.add(
					this.pigeonizeSingle(
							(Device)ne, 
							(Set<Interaction>)null));
			}
			
		}
		
    	// now, we merge all pigeon images 
    	// into one big image
    	RenderedImage img = toMergedImage(uris);
    	 
    	String filename = 
    			Eugene.ROOT_DIRECTORY + "/" + 
    			Eugene.IMAGES_DIRECTORY + "/" +
    					UUID.randomUUID() + ".png";
    	try {
    		// then, we serialize the RenderedImage object 
    		// into a PNG file
    		this.serializeImage(img, filename);
    	} catch(EugeneException ee) {
    		throw new EugeneException(ee.getMessage());
    	}
		
    	// if everything went well, then 
    	// return the URI of the serialized image
    	return URI.create(filename);
	}
	
	/**
	 * The serializeImage/2 method serializes a given RenderedImage object 
	 * to a given file using the PNG format.
	 * 
	 * @param img  ... the RenderedImage object to be serialized to a PNG picture
	 * @param filename ... the filename of the resulting PNG image
	 * 
	 * @throws EugeneException
	 */
	public void serializeImage(RenderedImage img, String filename) 
		throws EugeneException {
		try {
            ImageIO.write(img, "PNG", new File(filename));
         } catch (IOException e) {
            throw new EugeneException(e.getMessage());
         }
	}
	
	/**
	 * The toMergedImage/2 method takes as input a list of URIs which 
	 * point to an image (such as an SBOL Visual compliant representation 
	 * of a synthetic biology design), merges them into one image, 
	 * and returns the resulting image as a Java RenderedImage object.
	 * 
	 * @param uris ... a list of URIs pointing to images
	 * @return ... a Java Image object
	 * @throws EugeneException
	 */
	public RenderedImage toMergedImage(List<URI> uris) 
			throws EugeneException {

		try {
			int w = 0;
			int h = 0;
			
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			
			int idx = 0;
			for(URI uri : uris) {

				images.add(ImageIO.read(uri.toURL()));
				
				// create the new image, canvas size is the max. of both image sizes
				if(w < images.get(idx).getWidth()) {
					w = images.get(idx).getWidth();
				}
				
				h += images.get(idx).getHeight();
				
				idx++;
			}
	
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			Graphics g = combined.getGraphics();
			int ch = 0;
			for(BufferedImage img : images) {
				g.drawImage(img, 0, ch, null);
				ch += img.getHeight();
			}

			// finally, we return the merged image,
			// encapsulated in a Java BufferedImage object
			return combined;
			
		} catch(Exception e) {
			throw new EugeneException(e.getLocalizedMessage());
		}
	}

	
	/**
	 * The pigeonizeSingle/2 method visualizes one single solution using 
	 * Pigeon.
	 * 
	 * It takes as input the solution as Component array (Component[]) and 
	 * a set of interactions among the solution's components.
	 * 
	 * @param solution     ... a Component[] representing the solution
	 * @param interactions ... a set of interactions representing the Arcs
	 * @return
	 * @throws MiniEugeneException
	 */
	public URI pigeonizeSingle(Device device, Set<Interaction> interactions) 
			throws EugeneException {

		/*
		 * COMPILATION into a Pigeon script
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("fontsize 1.0\r\n");
		int i = 0;
		for(List<NamedElement> loe : device.getComponents()) {
			List<Orientation> loo = device.getOrientations(i++);
			int k = 0;
			for(NamedElement ne : loe) {
				if(ne instanceof Part) {
					sb.append(toPigeon((Part)ne, loo.get(k++))).append("\r\n");
				} else if(ne instanceof Device) {
					sb.append(toPigeon((Device)ne)).append("\r\n");
				}
			}
		}

		// INTERACTIONS
		sb.append(this.toPigeon(interactions));

		
		/*
		 * sending the Pigeon script to the Pigeon server
		 */
		WeyekinPoster.setPigeonText(sb.toString());	
		
		/*
		 * returning the URL of the Pigeon image 
		 */
		return WeyekinPoster.getMyBirdsURL();		
	}

	/**
	 * The toPigeon/1 method compiles a Eugene Device 
	 * into a corresponding Pigeon line
	 * 
	 * The toPigeon/1 method is not publicly available. It 
	 * is only being used by and can only be invoked through 
	 * the pigeonize methods.
	 * 
	 * @param device  ... the to converting Device
	 * 
	 * @return the corresponding Pigeon line
	 */
	private String toPigeon(Device device) 
			throws EugeneException {
		StringBuilder sb = new StringBuilder();

		int i=0;
		for(List<NamedElement> loe : device.getComponents()) {

			List<Orientation> loo = device.getOrientations(i);
			int k = 0;
			
			for(NamedElement ne : loe) {
				if(ne instanceof Device) {
					sb.append(this.toPigeon((Device)ne)).append("\r\n");
				} else if(ne instanceof Part) {
					sb.append(this.toPigeon((Part)ne, loo.get(k))).append("\r\n");
				}
				k++;				
			}
			
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * The toPigeon/1 method compiles a Eugene Part 
	 * into a corresponding Pigeon line
	 * 
	 * The toPigeon/1 method is not publicly available. It 
	 * is only being used by and can only be invoked through 
	 * the pigeonize methods.
	 * 
	 * @param part  ... the to converting Part
	 * 
	 * @return the corresponding Pigeon line
	 */
	private String toPigeon(Part part, Orientation o) {

		StringBuilder sb = new StringBuilder();
		if(null != part.getPropertyValue(PIGEON_PROPERTY) && 
			!part.getPropertyValue(PIGEON_PROPERTY).getTxt().isEmpty()) {

			String pigeon = part.getPropertyValue(PIGEON_PROPERTY).getTxt();
			
			// reverse invertase site
			if(Orientation.REVERSE == o && pigeon.startsWith(">")) {
				pigeon.replace('>', '<');
				sb.append(pigeon);		
			// reverse oriented known part type
			} else if(Orientation.REVERSE == o) {
				sb.append("<").append(pigeon);
			} else {
				sb.append(pigeon);
			}
			
			return sb.toString();
		}
		
		// first, let's get the Pigeon letter
		// corresponding to the component's type
		char letter = PredefinedTypes.toPigeonLetter(
							PredefinedTypes.toId(
									PredefinedTypes.toPartType(
											part.getType().getName())));

		// reverse invertase site
		if(Orientation.REVERSE == o && letter == '>') {
			sb.append("<");		
		// reverse oriented known part type
		} else if(Orientation.REVERSE == o) {
				sb.append("<").append(letter);
		// unknown part type => orientation irrelevant
		} else if(letter == '?') {
			sb.append("?");			
		// forward oriented known part type	
		} else {
			sb.append(letter);
		}

		// name + color
		String s = part.getName();
		sb.append(" ").append(s).append(" ").append(getColor(s));

		// is labeling turned on?
		if(!this.label) {
			sb.append(" nl");
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param interactions
	 * @return
	 */
	private String toPigeon(Set<Interaction> interactions) {
		StringBuilder sb = new StringBuilder();
		sb.append("# Arcs").append("\r\n");
		if(null != interactions && !(interactions.isEmpty())) {
			Iterator<Interaction> it = interactions.iterator();
			while(it.hasNext()) {
				Interaction ia = it.next();
				if(InteractionType.REPRESSES == ia.getType()) {
					sb.append(toPigeonRep(ia));
				} else if(InteractionType.INDUCES == ia.getType()) {
					sb.append(toPigeonInd(ia));
				}
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}
	
	/*
	 * REPRESSES -> REP
	 */
	private String toPigeonRep(Interaction represses) {
		StringBuilder sb = new StringBuilder();
		
		// get the individual roles
		String repressor = null;
		String repressee = null;		
		for(Participation part : represses.getParticipations()) {
			if(Participation.Role.REPRESSOR == part.getRole()) {
				repressor = part.getParticipant().getName();
			} else if (Participation.Role.REPRESSEE == part.getRole()) {
				repressee = part.getParticipant().getName();
			}
		}

		// finally, we build the Pigeon statement
		sb.append(repressor);
		sb.append(" rep ");
		sb.append(repressee);
		
		return sb.toString();
	}
	
	/*
	 * REPRESSES -> REP
	 */
	private String toPigeonInd(Interaction induces) {
		StringBuilder sb = new StringBuilder();
		
		// get the individual roles
		String inducer = null;
		String inducee = null;		
		for(Participation part : induces.getParticipations()) {
			if(Participation.Role.INDUCER == part.getRole()) {
				inducer = part.getParticipant().getName();
			} else if (Participation.Role.INDUCEE == part.getRole()) {
				inducee = part.getParticipant().getName();
			}
		}

		// finally, we build the Pigeon statement
		sb.append(inducer);
		sb.append(" ing ");
		sb.append(inducee);
		
		return sb.toString();
	}

	/**
	 * 
	 * @param s  ... the name of a component
	 * @return   the color code of the component
	 */
	private int getColor(String s) {
		if(this.colors.containsKey(s)) {
			int color = colors.get(s);
			if(color <= 1) {
				return 1;
			} else if(color >= 14) {
				return 14;
			}
			return color;
		}

		/*
		 * otherwise, we put the name into the coloring map
		 */
		int color = getRandomColor();
		this.colors.put(s, color);
		return color;
	}
	
	private static final int COLOR_MIN = 1;
	private static final int COLOR_MAX = 14;
	
	private static int getRandomColor() {
		return COLOR_MIN + (int)(Math.random() * ((COLOR_MAX - COLOR_MIN) + 1));
	}
}
