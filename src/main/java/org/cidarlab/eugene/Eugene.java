/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
 */

package org.cidarlab.eugene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.logging.LogManager;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.cidarlab.eugene.constants.EugeneConstants.ParsingPhase;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.Interp;
import org.cidarlab.eugene.parser.EugeneLexer;
import org.cidarlab.eugene.parser.EugeneParser;
import org.cidarlab.eugene.util.EugeneUtil;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;

/**
 * Welcome to the origin of live!
 * 
 * @author Ernst Oberortner
 */
public class Eugene {

	/*
	 * Sparrow is the reference to the 
	 * library management software
	 */
	private Sparrow sparrow;
	
	/*
	 * the interpreter
	 */
	private Interp interp;
	
	/*
	 * a writer for writing any output
	 */
	private BufferedWriter writer = null;

	public static String ROOT_DIRECTORY = ".";
	public static String IMAGES_DIRECTORY = "./exports/pigeon/";
	
	/*--------------------------------------
	 * EUGENE CONSTRUCTORS
	 *--------------------------------------*/
	
	// default no-args constructor
	public Eugene() {
		LogManager.getLogManager().reset();
	}
	
	// constructor with SessionID
	public Eugene(String sessionId)
			throws EugeneException {

		LogManager.getLogManager().reset();

		// initialize the rule-engine with 
		// a session id
		try {
			this.sparrow = new Sparrow(sessionId);
		} catch(SparrowException spe) {
			throw new EugeneException(spe.toString());
		}
		
		/*
		 * here, we also a create a writer for 
		 * writing any outputs
		 */
        try {        	
            // init the writer too
            writer = new BufferedWriter(
                              new OutputStreamWriter(
                                  new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        } catch(Exception e) {
        	throw new EugeneException(e.getLocalizedMessage());
        }
	}
	
	// constructor with sessionID and a writer to 
	// that the Eugene output is being written
	public Eugene(String sessionId, BufferedWriter writer) 
			throws EugeneException {

		LogManager.getLogManager().reset();

		// initialize the rule-engine with 
		// a session id
		try {
			this.sparrow = new Sparrow(sessionId);
		} catch(SparrowException spe) {
			throw new EugeneException(spe.toString());
		}
		
		this.writer = writer;
	}
	
	/**
	 * The setRootDirectory/1 method sets Eugene's root directory 
	 * to a given directory.
	 *  
	 * @param dir ... the desired root directory of Eugene
	 */
	public void setRootDirectory(String dir) {
		ROOT_DIRECTORY = dir;
	}
	
	
	/**
	 * The getRootDirectory method returns Eugene's root directory.
	 * 
	 * @return ... Eugene's current root directory
	 */
	public String getRootDirectory() {
		return ROOT_DIRECTORY;
	}
	
	
	/**
	 * The executeFile/1 method gets as input a File object, 
	 * reads the file, and executes it.
	 * 
	 * @param f
	 * @throws EugeneException
	 */
	public Collection<Component> executeFile(File file) 
			throws EugeneException {
		
		/*
		 * first, we read the file
		 * utilizing our EugeneUtil helpers 
		 */
		String script = null; 
		try {
			script = EugeneUtil.readFile(file);
		} catch(IOException ioe) {
			throw new EugeneException(ioe.toString());
		}
		
		if(null == script || script.isEmpty()) {
			throw new EugeneException("Empty file! Nothing to interprete!");
		}
		
		/*
		 * then, we execute the script
		 */
		return this.executeScript(script);
	}
	
	/**
	 * The executeScript/1 method executes the as String 
	 * provided Eugene script.
	 * 
	 * @param script  ... The Eugene script (as String)
	 * @throws EugeneException
	 */
	public Collection<Component> executeScript(String script) 
		throws EugeneException {

		/*
		 * PHASE 1:
		 * COLLECTING FUNCTIONS
		 */
//		System.out.println("*** PHASE I ***");
		EugeneParser parser = this.initParser(script, ParsingPhase.PRE_PROCESSING);
		try {
			parser.prog(true);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.toString());
		}
		
//		parser.printFunctions();
		
		/*
		 * PHASE II:
		 * INTERPRETATION
		 */
//		System.out.println("*** PHASE II ***");		
		parser = this.initParser(script, ParsingPhase.INTERPRETING);

		try {
			parser.prog(false);
		} catch(Exception e) {
			e.printStackTrace();
			throw new EugeneException(e.toString());
		}
		
		/*
		 * after executing a Eugene script, 
		 * we also persist the session
		 */
//		try {
//			this.sparrow.persist();
//		} catch(SparrowException e) {
//			throw new EugeneException(e.getMessage());
//		}
		
		// we will return a collection
		// of all Component objects in the 
		// working memory
		Collection<Component> lib = this.getLibrary();
		
		// but we first have to clean up the 
		// parser and all its mess
		parser.cleanUp();
		
		// and then we return the collection 
		// of components
		return lib;
	}

	public Collection<Component> getLibrary() 
			throws EugeneException {
		try {
			return this.sparrow.getFacts();
		} catch(SparrowException spe) {
			throw new EugeneException(spe.getMessage());
		}
	}
	
	private EugeneParser initParser(String script, ParsingPhase phase) 
			throws EugeneException {
		
		EugeneLexer lexer = new EugeneLexer(new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		EugeneParser parser = new EugeneParser(tokens);

		/*
		 * instantiate the library
		 * or clear its working memory
		 */
		if(phase == ParsingPhase.PRE_PROCESSING) {
			try {
				if(null == this.sparrow) {
					this.sparrow = new Sparrow();
				} else {
					this.sparrow.clear();
				}		
			} catch(SparrowException spe) {
				throw new EugeneException(spe.toString());
			}

			this.interp = new Interp(this.sparrow, this.writer, this.getRootDirectory());
		}
		
		/*
		 * initialize the parser, with the connection 
		 * to the library (i.e. Java Drools in our case)
		 * and the writer for writing the outputs
		 */		
		parser.init(
				this.interp, 
				this.writer, 
				phase);
		
		return parser;
	}

	public static void main(String[] args) 
			throws Exception {
		if(args.length!=1) {
			System.err.println("Usage: java -jar eugene.jar <eugene-file>");
			System.exit(1); 
		} 
		
		new Eugene().executeFile(new File(args[0]));
	}
}