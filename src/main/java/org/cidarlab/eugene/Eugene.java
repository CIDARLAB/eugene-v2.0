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
	 * a writer for writing any output
	 */
	private BufferedWriter writer = null;

	private String ROOT_DIRECTORY = ".";
	
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
		this.ROOT_DIRECTORY = dir;
	}
	
	
	/**
	 * The getRootDirectory method returns Eugene's root directory.
	 * 
	 * @return ... Eugene's current root directory
	 */
	public String getRootDirectory() {
		return this.ROOT_DIRECTORY;
	}
	
	
	/**
	 * The executeFile/1 method gets as input a File object, 
	 * reads the file, and executes it.
	 * 
	 * @param f
	 * @throws EugeneException
	 */
	public void executeFile(File file) 
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
		this.executeScript(script, file.toString());
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
		
		return this.executeScript(script, null);
		
	}
	
	private Collection<Component> executeScript(String script, String filename)
			throws EugeneException {

		/*
		 * lexical analysis and parsing
		 */
		EugeneLexer lexer = new EugeneLexer(new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		EugeneParser parser = new EugeneParser(tokens);

		/*
		 * instantiate the library
		 * or clear its working memory
		 */
		try {
			if(null == this.sparrow) {
				this.sparrow = new Sparrow();
			} else {
				this.sparrow.clear();
			}		
		} catch(SparrowException spe) {
			throw new EugeneException(spe.toString());
		}

		
		/*
		 * initialize the parser, with the connection 
		 * to the library (i.e. Java Drools in our case)
		 * and the writer for writing the outputs
		 */		
		parser.init(
				new Interp(this.sparrow, this.writer, this.getRootDirectory()), 
				this.writer);
		
		/*
		 * do the lexing, parsing, interpreting
		 */
		try {
			parser.prog();
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
		
		// finally, we return a collection
		// of all Component objects in the 
		// working memory
		return this.getLibrary();
//		return parser.getAllComponents();

	}

	public Collection<Component> getLibrary() 
			throws EugeneException {
		try {
			return this.sparrow.getFacts();
		} catch(SparrowException spe) {
			throw new EugeneException(spe.getMessage());
		}
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