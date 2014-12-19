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
import java.util.Set;
import java.util.logging.LogManager;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.cidarlab.eugene.constants.EugeneConstants.ParsingPhase;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.eugene.dom.imp.container.EugeneCollection;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.interp.Interp;
import org.cidarlab.eugene.parser.EugeneLexer;
import org.cidarlab.eugene.parser.EugeneParser;
import org.cidarlab.eugene.util.FileUtils;
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
	 * the parser
	 */
	private EugeneParser parser;
	
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

	/**
	 * The default no-arguments constructor instantiates Eugene 
	 * by creating a new instance of Sparrow and a default output writer.
	 * 
	 * @throws EugeneException
	 */
	public Eugene() 
			throws EugeneException {
		
		this.init(null, null);
	}
	
	/**
	 * The Eugene/1 constructor instantiates Eugene with a default output 
	 * writer. Also, this constructor instantiates Eugene's Sparrow with 
	 * the given session-id. If such a session exists already, then 
	 * the session will be resumed. Otherwise, a new session will be created.
	 *  
	 * @param sessionId  ... An ID of a Sparrow session.
	 * 
	 * @throws EugeneException 
	 */
	public Eugene(String sessionId)
			throws EugeneException {

		this.init(sessionId, null);
	}
	
	/**
	 * The Eugene/2 constructor instantiates Eugene w/ the given 
	 * output-writer and the given session-id. If such a session exists already, then 
	 * the session will be resumed. Otherwise, a new session will be created.
	 * 
	 * @param sessionId   ... the session-id
	 * @param writer      ... the output writer
	 * 
	 * @throws EugeneException
	 */
	public Eugene(String sessionId, BufferedWriter writer) 
			throws EugeneException {
		
		this.init(sessionId, writer);
	}
	
	/**
	 * The init/2 method initializes the Eugene instance with 
	 * - a the session-based Sparrow LMS and RE, 
	 * - a writer to that all the output is written, and 
	 * - a logger for DEBUG messages.
	 * 
	 * If the session-id is null, then we generate a random one.
	 * Otherwise, Sparrow tries to resume the session of the given id.
	 * If the session does not exist, then a new one will be created.
	 * 
	 * The init/2 method is private and is invoked with the parameters 
	 * of the used Constructor. 
	 * 
	 * @param sessionId   ... the Session ID
	 * @param writer      ... the output writer
	 * 
	 * @throws EugeneException
	 */
	private void init(String sessionId, BufferedWriter writer) 
			throws EugeneException {

		// LOGGING
		LogManager.getLogManager().reset();
		
		// SPARROW
		// --- Eugene's session-based Library Management System (LMS) 
		//     and Rule-Engine (RE)
		try {
			if(null == sessionId) {
				// init Sparrow w/ a randomly generated session ID
				this.sparrow = new Sparrow();
			} else {
				// init Sparrow w/ a given session ID
				this.sparrow = new Sparrow(sessionId);
			}
		} catch(SparrowException spe) {
			throw new EugeneException(spe.toString());
		}
		
		// OUTPUT WRITER
		if(null == writer) {
	        try {        	
	            // init the writer too
	            this.writer = new BufferedWriter(
	                              new OutputStreamWriter(
	                                  new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
	        } catch(Exception e) {
	        	throw new EugeneException(e.getLocalizedMessage());
	        }
		} else {
			this.writer = writer;
		}
		
		// create directors if they don't exists
    	// we need to create the directories if they 
    	// don't exist
    	File fDirs = new File(Eugene.ROOT_DIRECTORY + "/" + Eugene.IMAGES_DIRECTORY);
    	if(!fDirs.exists()) {
    		fDirs.mkdirs();
    	}
    	
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
	public EugeneCollection executeFile(File file) 
			throws EugeneException {
		
		/*
		 * first, we read the file
		 * utilizing our EugeneUtil helpers 
		 */
		String script = null; 
		try {
			script = FileUtils.readFile(file);
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
	public EugeneCollection executeScript(String script) 
		throws EugeneException {

		// clean up everything from an eventual 
		// earlier run
		if(null != this.parser) {
			this.parser.cleanUp();
		}
		
		/*
		 * PHASE 1:
		 * COLLECTING FUNCTIONS
		 */
		this.initParser(script, ParsingPhase.PRE_PROCESSING);
		try {
			parser.prog(true);
		} catch(Exception e) {
			throw new EugeneException(e.toString());
		}
		
		/*
		 * PHASE II:
		 * INTERPRETATION
		 */
		this.initParser(script, ParsingPhase.INTERPRETING);

		try {
			parser.prog(false);
		} catch(Exception e) {
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
		return this.parser.getAllElements();
	}

	/**
	 * The private initParser/2 initializes the Eugene parser depending 
	 * on the ParsingPhase. 
	 * 
	 * In the first parsing phase (``Preprocessing'') we collect all functions 
	 * whereas in the second parsing phase (``Interpretation'') we execute 
	 * the Eugene script.
	 * 
	 * @param script   ... the script the be parsed
	 * @param phase    ... the parsing phase
	 * 
	 * @return the initialized EugeneParser object
	 * 
	 * @throws EugeneException
	 */
	private void initParser(String script, ParsingPhase phase) 
			throws EugeneException {
		
		EugeneLexer lexer = new EugeneLexer(new ANTLRStringStream(script));
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		this.parser = new EugeneParser(tokens);

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
		this.parser.init(
				this.interp, 
				this.writer, 
				phase);
		
	}

	/**
	 * The get(String) method retrieves the NamedElement object of 
	 * the provided name. This method enables to retrieve  
	 * individual results of executing a Eugene script.
	 * 
	 * Example:
	 *********** 
	 * - my.eug:  
	 * ...
	 * list_of_devices = product(my_device);
	 * ...
	 * 
	 * - Java: 
	 * Eugene e = new Eugene();
	 * e.executeFile(new File("my.eug"));
	 * NamedElement list = e.get("list_of_devices");  
	 * 
	 * @param name ... the name of the required NamedElement object.
	 * 
	 * @return  the NamedElement of the specified name. NULL if the element
	 * does not exist.
	 */
	public NamedElement get(String name) {
		try {
			return this.sparrow.getFact(name);
		} catch(SparrowException spe) {
			return null;
		}
	}
	
	/**
	 * The getLibrary() method returns a set of components 
	 * that have been stored into the library. This set of 
	 * components consists of basic and composite parts.
	 * 
	 * @return  a set of components generated through the execution of Eugene scripts.
	 * @throws EugeneException
	 */
	public Set<Component> getLibrary() 
			throws EugeneException {
		if(null != this.sparrow) {
			try {
				return this.sparrow.getFacts();
			} catch(Exception e) {
				throw new EugeneException(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * The MAIN function.
	 * 
	 * It serves to execute Eugene from the command line via 
	 * java -jar 
	 * It requires one input parameter, i.e. the Eugene script 
	 * to be executed.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) 
			throws Exception {
		if(args.length!=1) {
			System.err.println("Usage: java -jar eugene.jar <eugene-file>");
			System.exit(1); 
		} 
		
		new Eugene().executeFile(new File(args[0]));
	}
}