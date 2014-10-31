// $ANTLR 3.5.1 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g 2014-10-31 17:15:58

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

package org.cidarlab.eugene.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.constants.Orientation;
//import org.cidarlab.eugene.data.genbank.*;
//import org.cidarlab.eugene.data.registry.*;
import org.cidarlab.eugene.data.sbol.*;
import org.cidarlab.eugene.exception.EugeneException;
import org.cidarlab.eugene.exception.EugeneReturnException;
import org.cidarlab.eugene.interp.Interp;
import org.cidarlab.eugene.dom.rules.*;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.exp.*;
import org.cidarlab.eugene.dom.imp.*;
import org.cidarlab.eugene.dom.imp.container.*;
import org.cidarlab.eugene.dom.imp.loops.Loop;
import org.cidarlab.eugene.dom.imp.functions.*;
import org.cidarlab.eugene.constants.EugeneConstants.ParsingPhase;

import org.antlr.runtime.*;

 
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/*
 *  HELPER LIBRARIES
 */
import org.apache.commons.lang3.ArrayUtils;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class EugeneParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADDPROPS", "AMP", "ARRAY", "ARROW", 
		"ASSERT", "BOOL", "BOOLEAN", "COLLECTION", "COLON", "COMMA", "DEVICE", 
		"DIGIT", "DIV", "DOLLAR", "DOT", "DOTDOT", "EQUALS", "EXIT_LC", "EXIT_UC", 
		"EXPORT_LC", "EXPORT_UC", "FALSE_LC", "FALSE_UC", "FLEXIBLE", "GENBANK", 
		"GEQUAL", "GRAMMAR", "GTHAN", "HASHMARK", "ID", "IMAGE", "IMPORT_LC", 
		"IMPORT_UC", "INCLUDE_LC", "INCLUDE_UC", "INTERACTION", "LC_AND", "LC_ELSE", 
		"LC_ELSEIF", "LC_FOR", "LC_FORALL", "LC_IF", "LC_INDUCES", "LC_NOT", "LC_ON", 
		"LC_OR", "LC_REPRESSES", "LC_WHILE", "LEFTCUR", "LEFTP", "LEFTSBR", "LEQUAL", 
		"LINE_COMMENT", "LOG_AND", "LOG_OR", "LTHAN", "MINUS", "ML_COMMENT", "MULT", 
		"NEQUAL", "NEWLINE", "NOTE", "NUM", "NUMBER", "OP_NOT", "PART", "PART_TYPE", 
		"PERMUTE", "PIPE", "PLUS", "PRINTLN_LC", "PRINTLN_UC", "PRINT_LC", "PRINT_UC", 
		"PRODUCT", "PROPERTY", "RANDOM_LC", "RANDOM_UC", "REAL", "REF", "REGISTRY", 
		"RETURN_LC", "RETURN_UC", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "RULE", "SAVE_LC", 
		"SAVE_UC", "SBOL", "SEMIC", "SIZEOF_LC", "SIZEOF_UC", "SIZE_LC", "SIZE_UC", 
		"STORE_LC", "STORE_UC", "STRICT", "STRING", "TRUE_LC", "TRUE_UC", "TXT", 
		"TYPE", "UC_AND", "UC_ELSE", "UC_ELSEIF", "UC_FOR", "UC_FORALL", "UC_IF", 
		"UC_INDUCES", "UC_NOT", "UC_ON", "UC_OR", "UC_REPRESSES", "UC_WHILE", 
		"UNDERS", "VISUALIZE_LC", "VISUALIZE_UC", "WS", "'AFTER'", "'ALL_AFTER'", 
		"'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", 
		"'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", 
		"'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'MATCHES'", 
		"'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", 
		"'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'REVERSE'", 
		"'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", 
		"'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", 
		"'SOUNDSLIKE'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", "'all_after'", 
		"'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", 
		"'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", 
		"'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'matches'", 
		"'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", 
		"'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'reverse'", 
		"'same_count'", "'same_orientation'", "'some_after'", "'some_before'", 
		"'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", 
		"'soundslike'", "'startswith'", "'then'", "'with'"
	};
	public static final int EOF=-1;
	public static final int T__123=123;
	public static final int T__124=124;
	public static final int T__125=125;
	public static final int T__126=126;
	public static final int T__127=127;
	public static final int T__128=128;
	public static final int T__129=129;
	public static final int T__130=130;
	public static final int T__131=131;
	public static final int T__132=132;
	public static final int T__133=133;
	public static final int T__134=134;
	public static final int T__135=135;
	public static final int T__136=136;
	public static final int T__137=137;
	public static final int T__138=138;
	public static final int T__139=139;
	public static final int T__140=140;
	public static final int T__141=141;
	public static final int T__142=142;
	public static final int T__143=143;
	public static final int T__144=144;
	public static final int T__145=145;
	public static final int T__146=146;
	public static final int T__147=147;
	public static final int T__148=148;
	public static final int T__149=149;
	public static final int T__150=150;
	public static final int T__151=151;
	public static final int T__152=152;
	public static final int T__153=153;
	public static final int T__154=154;
	public static final int T__155=155;
	public static final int T__156=156;
	public static final int T__157=157;
	public static final int T__158=158;
	public static final int T__159=159;
	public static final int T__160=160;
	public static final int T__161=161;
	public static final int T__162=162;
	public static final int T__163=163;
	public static final int T__164=164;
	public static final int T__165=165;
	public static final int T__166=166;
	public static final int T__167=167;
	public static final int T__168=168;
	public static final int T__169=169;
	public static final int T__170=170;
	public static final int T__171=171;
	public static final int T__172=172;
	public static final int T__173=173;
	public static final int T__174=174;
	public static final int T__175=175;
	public static final int T__176=176;
	public static final int T__177=177;
	public static final int T__178=178;
	public static final int T__179=179;
	public static final int T__180=180;
	public static final int T__181=181;
	public static final int T__182=182;
	public static final int T__183=183;
	public static final int T__184=184;
	public static final int T__185=185;
	public static final int T__186=186;
	public static final int T__187=187;
	public static final int T__188=188;
	public static final int T__189=189;
	public static final int T__190=190;
	public static final int T__191=191;
	public static final int T__192=192;
	public static final int T__193=193;
	public static final int T__194=194;
	public static final int T__195=195;
	public static final int T__196=196;
	public static final int T__197=197;
	public static final int T__198=198;
	public static final int T__199=199;
	public static final int T__200=200;
	public static final int ADDPROPS=4;
	public static final int AMP=5;
	public static final int ARRAY=6;
	public static final int ARROW=7;
	public static final int ASSERT=8;
	public static final int BOOL=9;
	public static final int BOOLEAN=10;
	public static final int COLLECTION=11;
	public static final int COLON=12;
	public static final int COMMA=13;
	public static final int DEVICE=14;
	public static final int DIGIT=15;
	public static final int DIV=16;
	public static final int DOLLAR=17;
	public static final int DOT=18;
	public static final int DOTDOT=19;
	public static final int EQUALS=20;
	public static final int EXIT_LC=21;
	public static final int EXIT_UC=22;
	public static final int EXPORT_LC=23;
	public static final int EXPORT_UC=24;
	public static final int FALSE_LC=25;
	public static final int FALSE_UC=26;
	public static final int FLEXIBLE=27;
	public static final int GENBANK=28;
	public static final int GEQUAL=29;
	public static final int GRAMMAR=30;
	public static final int GTHAN=31;
	public static final int HASHMARK=32;
	public static final int ID=33;
	public static final int IMAGE=34;
	public static final int IMPORT_LC=35;
	public static final int IMPORT_UC=36;
	public static final int INCLUDE_LC=37;
	public static final int INCLUDE_UC=38;
	public static final int INTERACTION=39;
	public static final int LC_AND=40;
	public static final int LC_ELSE=41;
	public static final int LC_ELSEIF=42;
	public static final int LC_FOR=43;
	public static final int LC_FORALL=44;
	public static final int LC_IF=45;
	public static final int LC_INDUCES=46;
	public static final int LC_NOT=47;
	public static final int LC_ON=48;
	public static final int LC_OR=49;
	public static final int LC_REPRESSES=50;
	public static final int LC_WHILE=51;
	public static final int LEFTCUR=52;
	public static final int LEFTP=53;
	public static final int LEFTSBR=54;
	public static final int LEQUAL=55;
	public static final int LINE_COMMENT=56;
	public static final int LOG_AND=57;
	public static final int LOG_OR=58;
	public static final int LTHAN=59;
	public static final int MINUS=60;
	public static final int ML_COMMENT=61;
	public static final int MULT=62;
	public static final int NEQUAL=63;
	public static final int NEWLINE=64;
	public static final int NOTE=65;
	public static final int NUM=66;
	public static final int NUMBER=67;
	public static final int OP_NOT=68;
	public static final int PART=69;
	public static final int PART_TYPE=70;
	public static final int PERMUTE=71;
	public static final int PIPE=72;
	public static final int PLUS=73;
	public static final int PRINTLN_LC=74;
	public static final int PRINTLN_UC=75;
	public static final int PRINT_LC=76;
	public static final int PRINT_UC=77;
	public static final int PRODUCT=78;
	public static final int PROPERTY=79;
	public static final int RANDOM_LC=80;
	public static final int RANDOM_UC=81;
	public static final int REAL=82;
	public static final int REF=83;
	public static final int REGISTRY=84;
	public static final int RETURN_LC=85;
	public static final int RETURN_UC=86;
	public static final int RIGHTCUR=87;
	public static final int RIGHTP=88;
	public static final int RIGHTSBR=89;
	public static final int RULE=90;
	public static final int SAVE_LC=91;
	public static final int SAVE_UC=92;
	public static final int SBOL=93;
	public static final int SEMIC=94;
	public static final int SIZEOF_LC=95;
	public static final int SIZEOF_UC=96;
	public static final int SIZE_LC=97;
	public static final int SIZE_UC=98;
	public static final int STORE_LC=99;
	public static final int STORE_UC=100;
	public static final int STRICT=101;
	public static final int STRING=102;
	public static final int TRUE_LC=103;
	public static final int TRUE_UC=104;
	public static final int TXT=105;
	public static final int TYPE=106;
	public static final int UC_AND=107;
	public static final int UC_ELSE=108;
	public static final int UC_ELSEIF=109;
	public static final int UC_FOR=110;
	public static final int UC_FORALL=111;
	public static final int UC_IF=112;
	public static final int UC_INDUCES=113;
	public static final int UC_NOT=114;
	public static final int UC_ON=115;
	public static final int UC_OR=116;
	public static final int UC_REPRESSES=117;
	public static final int UC_WHILE=118;
	public static final int UNDERS=119;
	public static final int VISUALIZE_LC=120;
	public static final int VISUALIZE_UC=121;
	public static final int WS=122;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public EugeneParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public EugeneParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return EugeneParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g"; }


	/*
	 *  member variables
	 */
	// the interpreter
	private Interp interp;

	// the number of the parser's run
	private ParsingPhase PARSING_PHASE;

	// IMPORTERS
	//private SBOLRegistryImporter registryImporter;

	// the logger
	private final static Logger LOGGER = Logger.getLogger(EugeneParser.class.getName()); 

	// a writer for writing the output (print, println)
	private BufferedWriter writer = null;
	private final static String LINE_FEED = System.getProperty("line.separator");

	String typeList = "";
	boolean debug = false;
	ArrayList<Variable> propertyValuesHolder = new ArrayList<Variable>();
	ArrayList<String> propertyListHolder = new ArrayList<String>();

	// the name of the file to be parsed
	String filename = null;

	public void init(Interp interp, BufferedWriter writer, ParsingPhase PARSING_PHASE) {
	    this.interp = interp;
	    
	    if(null != writer) {
	    	this.writer = writer;
	    } else {
	        try {
	            // init the writer too
	            this.writer = new BufferedWriter(
	                              new OutputStreamWriter(
	                                  new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
	        } catch(Exception e) {
	            printError(e.getLocalizedMessage());
	        }
	    }
	    
	    this.PARSING_PHASE = PARSING_PHASE;
	    
	}

	/**
	 *  The cleanUp/0 method cleans up all the mess that
	 *  Eugene has caused.
	 */
	public void cleanUp() {
	    // clean up the mess of the interpreter
	    if(null != this.interp) {
	        this.interp.cleanUp();
	        this.interp = null;
	    }
	}

	public EugeneCollection getAllElements() 
	        throws EugeneException {
	    try {
	        return this.interp.getAllElements();
	    } catch(EugeneException ee) {
	        throw new EugeneException(ee.getMessage());
	    }
	}

	public void setFilename(String filename) {
	    this.filename = filename;
	}

	// for testing purpose
	public void printFunctions() {
	    this.interp.printFunctions();
	}

	@Override
	public void reportError(RecognitionException re) {
	    printError(re.getLocalizedMessage());
	}

	//method used to collect individual members in declaration, used by grammar rule list
	public void addToListPrim(Variable p, Variable listPrim) {
	    if (p.type.equals(typeList)) {
	        if (typeList.equals(EugeneConstants.TXT)) {
	            listPrim.txtList.add(p.txt);
	        } else if(typeList.equals(EugeneConstants.NUM)) {
	            listPrim.numList.add(p.num);
	        }
	    } else {
	        printError("Type mismatch. List type is " + typeList + " and instantiated Variable type is " + p.type);
	    }
	}

	// for tokenization of rules
	String[] tokens = null;
	private void addToken(String token) {
	    if(null != tokens) {
	        tokens = ArrayUtils.add(tokens, token);
	    } else {
	        tokens = new String[1];
	        tokens[0] = token;
	    }
	}

	/*
	 *   ASSIGNMENTS AND EXPRESSIONS
	 */
	NamedElement root = null;
	NamedElement parent = null;
	NamedElement child = null;  

	/*
	 *    VARIBABLE DECLARATIONS
	 */
	//checks if the name has been defined in program
	public boolean checkIfAlreadyDeclared(String name, boolean all) {
	    if (this.interp.contains(name)) {
	        printError(name + " has already been defined.");
	        return true;
	    } 
	    return false;
	}

	 
		//declares Variable without instantiating it to any value
		public void declareVariableNoValue(String name, String type) {
			if (!checkIfAlreadyDeclared(name, true)) {
				Variable p = new Variable(name , type);
				try {
	   			    this.interp.put(name, p);
				} catch(EugeneException ee) {
				    printError(ee.getLocalizedMessage());
				}
			}
		}

		//declares and instantiates a num Variable with a value
		public void declareVariableWithValueNum(String name, Variable prim, int index) {
			if (!checkIfAlreadyDeclared(name, true)) {
				if (prim.type.equals(EugeneConstants.NUM)) {
					Variable p = new Variable(name, EugeneConstants.NUM);
					p.num = prim.num;
					try {
					    this.interp.put(name, p);
				        } catch(EugeneException ee) {
				            printError(ee.getLocalizedMessage());
				        }
				} else if (prim.type.equals(EugeneConstants.NUMLIST) && prim.index != -1) {
					Variable p = new Variable(name, EugeneConstants.NUM);
					p.num = prim.numList.get(prim.index);
				} else {
					printError("Type mismatch. Type is " + prim.type + " but must be num.");
				}
			}
		}

		//declares and instantiates a txt Variable with a value
		public void declareVariableWithValueTxt(String name, Variable prim, int index) {
			if (!checkIfAlreadyDeclared(name, true)) {
				if (prim.type.equals(EugeneConstants.TXT)) {
					Variable p = new Variable(name, EugeneConstants.TXT);
					p.txt = prim.txt;
					try {
					    this.interp.put(name, p);
				        } catch(EugeneException ee) {
				            printError(ee.getLocalizedMessage());
				        }
				} else if (prim.type.equals(EugeneConstants.TXTLIST) && index != -1) {
					Variable p = new Variable(name, EugeneConstants.TXT);
					p.txt = prim.txtList.get(index);
				} else {
					printError("Type mismatch. Type is " + prim.type + " but must be txt.");
				}
			}
		}

		//declares and instantiates a txtList Variable with a txt value list
		public void declareVariableWithValueTxtList(String name, Variable prim) {
			if (!checkIfAlreadyDeclared(name, true)) {
				if (prim.type.equals(EugeneConstants.TXTLIST)) {
					Variable p = new Variable(name, EugeneConstants.TXTLIST);
					p.txtList.addAll(prim.txtList);
					try {
					    this.interp.put(name, p);
				        } catch(EugeneException ee) {
				            printError(ee.getLocalizedMessage());
				        }
				} else {
					printError("Type mismatch. Type is " + prim.type + " but must be txtList.");
				}
			}
		}

		//declares and instantiates a numList Variable with a num value list
		public void declareVariableWithValueNumList(String name, Variable prim) {
			if (!checkIfAlreadyDeclared(name, true)) {
				if (prim.type.equals(EugeneConstants.NUMLIST)) {
					Variable p = new Variable(name, EugeneConstants.NUMLIST);
					p.numList.addAll(prim.numList);
					try {
					    this.interp.put(name, p);
				        } catch(EugeneException ee) {
				            printError(ee.getLocalizedMessage());
				        }
				} else {
					printError("Type mismatch. Type is " + prim.type + " but must be numList.");
				}
			}
		}

		//declares and instantiates a boolean Variable with a boolean value
		public void declareVariableWithValueBool(String name, Variable prim) {
			if (!checkIfAlreadyDeclared(name, true)) {
				if (prim.type.equals(EugeneConstants.BOOLEAN)) {
					Variable p = new Variable(name, EugeneConstants.BOOLEAN);
					p.bool = prim.bool;
					try {
					    this.interp.put(name, p);
				        } catch(EugeneException ee) {
				            printError(ee.getLocalizedMessage());
				        }
				} else {
					printError("Type mismatch. Type is " + prim.type + " but must be numList.");
				}
			}
		}
		
		//copies the values of a primitive to a newly created Primitive
		public Variable copyVariable(Variable source) {
			Variable destination = new Variable(source.getName(), source.getType());
			destination.index = source.index;
			if (EugeneConstants.NUM.equals(source.getType())) {
				destination.type = EugeneConstants.NUM;
				destination.num = source.num;
			} else if (EugeneConstants.TXT.equals(source.getType())) {
				destination.type = EugeneConstants.TXT;
				destination.txt = source.txt;
			} else if (EugeneConstants.NUMLIST.equals(source.getType())) {
				destination.type = EugeneConstants.NUMLIST;
				destination.numList.clear();
				destination.numList.addAll(source.numList);
			} else if (EugeneConstants.TXTLIST.equals(source.getType())) {
				destination.type = EugeneConstants.TXTLIST;
				destination.txtList.clear();
				destination.txtList.addAll(source.txtList);
			} else if (EugeneConstants.BOOLEAN.equals(source.getType())) {
				destination.type = EugeneConstants.BOOLEAN;
				destination.bool = source.bool;
			}
			return destination;
		}
		
		
	    public void addToPropertyListHolder(String prop) 
	            throws EugeneException {
	        propertyListHolder.add(prop);
	    }
		
	    //adds values to the corresponding property
	    public void addToPropertyValuesHolder(String prop, Variable p, int index) 
	            throws EugeneException {
	        propertyValuesHolder.add(p);
	    }
	    	
	/*---------------------------------------------------------------------
	 * METHODS FOR ERROR REPORTING
	 *---------------------------------------------------------------------*/
	public void printDebug(Object message) {
	    if (debug) {
	        int line = input.LT(-1).getLine();
	        System.err.println("@Debug Line " + line + ": " + message);
	    }
	}

	private static final String NL = System.getProperty("line.separator");

	public void printError(Object message) {
	    int line = input.LT(-1).getLine();
	    int pos = input.LT(-1).getCharPositionInLine();
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append("@Error!").append(NL);
	    if(null != this.filename) {
	        sb.append("File: ").append(this.filename).append(NL);
	    }
	    sb.append("Line ").append(line).append(" Position ").append(pos).append(NL);
	    sb.append(message).append(NL);
	    throw new IllegalArgumentException(sb.toString());
	}


	/*---------------------------------------------------------------------
	 * IMPERATIVE LANGUAGE STATEMENTS
	 *---------------------------------------------------------------------*/
	public Object exec(String rule, int tokenIndex) 
	        throws EugeneReturnException, EugeneException {
	    Object rv = null;
	    int oldPosition = this.input.index(); // save current location
	    this.input.seek(tokenIndex); // seek to place to start execution
	    
	    try { // which rule are we executing?
	        if("variableDeclaration".equals(rule)) {
	            rv = this.variableDeclaration(false);
	        } else if("logical_condition".equals(rule)) {
	            rv = this.logical_condition(false);
	        } else if("list_of_statements".equals(rule)) {
	            rv = this.list_of_statements(false);
	        } else if("assignment".equals(rule)) {
	            rv = this.assignment(false);
	        } 
	        
	    } catch (EugeneReturnException ere) {
	        throw new EugeneReturnException(ere.getReturnValue());    
	    } catch (Exception e) {
	        throw new EugeneException(e.getLocalizedMessage());
	    }
	    finally { 
	        // restore location
	        this.input.seek(oldPosition); 
	    }
	    return rv;
	}
	 
	 
	    public void execute_loop(Token initStart, Token condStart, Token incStart, Token loopStart) 
	            throws EugeneException, EugeneReturnException {

	        /*
	         * save the current parsing position
	         * (i.e. after the for loop)
	         */
	        int oldPosition = this.input.index(); 

	        /*
	         * execute the declaration statement
	         */ 
	        Loop l = new Loop();
	        if(null != initStart) {
	            variableDeclaration_return vdr = 
	                 (variableDeclaration_return)this.exec(
	                     "variableDeclaration", 
	                     initStart.getTokenIndex());    

	            /*
	             * create a new ForLoop object
	             */
	            if(null != vdr) {
	                l.setVarname(vdr.varname);
	            }
	        } else {
	            l.setVarname(UUID.randomUUID().toString());
	        }
	        
	        /*
	         * evaluate the condition
	         */
	        // first, parse the condition
	        logical_condition_return ret = 
	               (logical_condition_return)this.exec(
	                                           "logical_condition",         
	                                           condStart.getTokenIndex());
	        /*
	         * while the condition is satisfied
	         */
	        while(ret.b) {

	            /*
	             * push the ForLoop object onto the stack
	             * (for scoping)
	             */
	            this.interp.push(l);
	                
	            /*
	             *   execute the statements
	             */
	            this.exec(
	                "list_of_statements", 
	                loopStart.getTokenIndex()); 
	             
	            /*
	             * pop the ForLoop statement
	             */
	            this.interp.pop();
	            
	            /*
	             *   do the assignment statement
	             */
	            if(null != incStart) { 
	                this.exec(
	                    "assignment", 
	                    incStart.getTokenIndex()); 
	            }
	            
	            /*
	             *    evaluate the condition again
	             */ 
	            ret = (logical_condition_return)this.exec(
	                                   "logical_condition",         
	                                   condStart.getTokenIndex());  
	        }
	        
	        
	        if(null != l.getVarname()) {
	            /*
	             * lastly, we need to get rid of the 
	             * iteration variable (e.g. num i)
	             */
	            this.interp.removeVariable(
	                    l.getVarname());
	        }
	        
	        /*
	         * and continue parsing/interpreting 
	         * (i.e. after the for loop)
	         */
	        this.input.seek(oldPosition);
	    }
	    
	    public NamedElement call_function(String name, List<NamedElement> lopv)
	        throws EugeneException {
	    
	        // first, we ask the interpreter if the function exists
	        // and let it return the function object
	        FunctionInstance fi = this.interp.instantiateFunction(name, lopv);
	        if(null == fi) {
	            printError("The function " + name + " is not defined!");
	        }
	        
	        // we remember the current position in the script
	        int oldPosition = this.input.index();
	        // and we hold a temporary reference to the current
	        // stream of tokens
	        TokenStream tmp = this.input;
	        
	        // we put the function onto the stack
	        // SCOPING !
	        this.interp.push(fi);
	        
	        
	        NamedElement ret_el = null;
	        String error_msg = null;
	        try {
	            // we point the current input token stream to the 
	            // token stream from that we've read the function
	            this.input = fi.getPrototype().getTokenStream();
	            
	            // since a function can be defined "ahead" 
	            // of the current position in the script,
	            // we need to stretch the input
	            // and that's how we do it
	            this.input.toString();

	            // then, we let the input point to the first statement
	            // of the function
	            this.input.seek(
	                 fi.getPrototype().getStatements().getTokenIndex());
	            
	            // we execute all statements of the function
	            this.list_of_statements(false);
	            
	        } catch(RecognitionException re) {
	            // if a syntax error was detected, then
	            // we store the error message in a string
	            error_msg = re.getLocalizedMessage();	

	        } catch(EugeneReturnException ere) {
	            // a return statement has been encountered
	            ret_el = ere.getReturnValue();
	        }
	        
	        // and we need to cleanup the function's stack
	        this.interp.cleanupFunction(fi);

	        // finally we restore everything to what it was 
	        // before the function call and 
	        // jump to the original position        
	        this.input = tmp;
	        this.input.seek(oldPosition);

	        // if an error occured (e.g. syntax error),
	        // then we print the error message and
	        // exit
	        if(error_msg != null) {
	            printError(error_msg);
	        }
	        
	        return ret_el;

	        /*
	         * FOR TESTING PURPOSE:
	         * - we simulate the function's return value
	         */
	//        return f.simulateReturnValue();
	    }



	public static class prog_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "prog"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:800:1: prog[boolean defer] : ( statement[defer] | function_definition[true] )* EOF ;
	public final EugeneParser.prog_return prog(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.prog_return retval = new EugeneParser.prog_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EOF3=null;
		ParserRuleReturnScope statement1 =null;
		ParserRuleReturnScope function_definition2 =null;

		Object EOF3_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:2: ( ( statement[defer] | function_definition[true] )* EOF )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:4: ( statement[defer] | function_definition[true] )* EOF
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:4: ( statement[defer] | function_definition[true] )*
			loop1:
			while (true) {
				int alt1=3;
				switch ( input.LA(1) ) {
				case ARRAY:
				case COLLECTION:
				case DEVICE:
				case EXIT_LC:
				case EXIT_UC:
				case GENBANK:
				case GRAMMAR:
				case HASHMARK:
				case IMPORT_LC:
				case IMPORT_UC:
				case INCLUDE_LC:
				case INCLUDE_UC:
				case INTERACTION:
				case LC_FOR:
				case LC_FORALL:
				case LC_IF:
				case LC_WHILE:
				case PART:
				case PART_TYPE:
				case PERMUTE:
				case PRINTLN_LC:
				case PRINTLN_UC:
				case PRINT_LC:
				case PRINT_UC:
				case PRODUCT:
				case PROPERTY:
				case RANDOM_LC:
				case RANDOM_UC:
				case REGISTRY:
				case RETURN_LC:
				case RETURN_UC:
				case RULE:
				case SAVE_LC:
				case SAVE_UC:
				case SBOL:
				case SIZEOF_LC:
				case SIZEOF_UC:
				case SIZE_LC:
				case SIZE_UC:
				case STORE_LC:
				case STORE_UC:
				case TYPE:
				case UC_FOR:
				case UC_FORALL:
				case UC_IF:
				case UC_WHILE:
					{
					alt1=1;
					}
					break;
				case NUM:
					{
					int LA1_3 = input.LA(2);
					if ( (LA1_3==LEFTSBR) ) {
						int LA1_7 = input.LA(3);
						if ( (LA1_7==RIGHTSBR) ) {
							int LA1_13 = input.LA(4);
							if ( (LA1_13==ID) ) {
								int LA1_17 = input.LA(5);
								if ( (LA1_17==COMMA||LA1_17==EQUALS||LA1_17==SEMIC) ) {
									alt1=1;
								}
								else if ( (LA1_17==LEFTP) ) {
									alt1=2;
								}

							}

						}

					}
					else if ( (LA1_3==ID) ) {
						int LA1_8 = input.LA(3);
						if ( (LA1_8==COMMA||LA1_8==EQUALS||LA1_8==SEMIC) ) {
							alt1=1;
						}
						else if ( (LA1_8==LEFTP) ) {
							alt1=2;
						}

					}

					}
					break;
				case TXT:
					{
					int LA1_4 = input.LA(2);
					if ( (LA1_4==LEFTSBR) ) {
						int LA1_9 = input.LA(3);
						if ( (LA1_9==RIGHTSBR) ) {
							int LA1_15 = input.LA(4);
							if ( (LA1_15==ID) ) {
								int LA1_18 = input.LA(5);
								if ( (LA1_18==COMMA||LA1_18==EQUALS||LA1_18==SEMIC) ) {
									alt1=1;
								}
								else if ( (LA1_18==LEFTP) ) {
									alt1=2;
								}

							}

						}

					}
					else if ( (LA1_4==ID) ) {
						int LA1_10 = input.LA(3);
						if ( (LA1_10==COMMA||LA1_10==EQUALS||LA1_10==SEMIC) ) {
							alt1=1;
						}
						else if ( (LA1_10==LEFTP) ) {
							alt1=2;
						}

					}

					}
					break;
				case BOOL:
				case BOOLEAN:
					{
					int LA1_5 = input.LA(2);
					if ( (LA1_5==ID) ) {
						int LA1_11 = input.LA(3);
						if ( (LA1_11==COMMA||LA1_11==EQUALS||LA1_11==SEMIC) ) {
							alt1=1;
						}
						else if ( (LA1_11==LEFTP) ) {
							alt1=2;
						}

					}

					}
					break;
				case ID:
					{
					int LA1_6 = input.LA(2);
					if ( (LA1_6==LEFTP) ) {
						switch ( input.LA(3) ) {
						case RIGHTP:
							{
							int LA1_16 = input.LA(4);
							if ( (LA1_16==LEFTCUR) ) {
								alt1=2;
							}
							else if ( (LA1_16==SEMIC) ) {
								alt1=1;
							}

							}
							break;
						case DOLLAR:
						case EXIT_LC:
						case EXIT_UC:
						case FALSE_LC:
						case FALSE_UC:
						case ID:
						case LEFTP:
						case LEFTSBR:
						case MINUS:
						case NUMBER:
						case PERMUTE:
						case PRODUCT:
						case RANDOM_LC:
						case RANDOM_UC:
						case REAL:
						case SAVE_LC:
						case SAVE_UC:
						case SIZEOF_LC:
						case SIZEOF_UC:
						case SIZE_LC:
						case SIZE_UC:
						case STORE_LC:
						case STORE_UC:
						case STRING:
						case TRUE_LC:
						case TRUE_UC:
							{
							alt1=1;
							}
							break;
						case BOOL:
						case BOOLEAN:
						case NUM:
						case TXT:
							{
							alt1=2;
							}
							break;
						}
					}
					else if ( ((LA1_6 >= DOLLAR && LA1_6 <= DOT)||LA1_6==EQUALS||LA1_6==ID||LA1_6==LC_INDUCES||LA1_6==LC_REPRESSES||LA1_6==LEFTSBR||LA1_6==UC_INDUCES||LA1_6==UC_REPRESSES) ) {
						alt1=1;
					}

					}
					break;
				}
				switch (alt1) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:5: statement[defer]
					{
					pushFollow(FOLLOW_statement_in_prog997);
					statement1=statement(defer);
					state._fsp--;

					adaptor.addChild(root_0, statement1.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:24: function_definition[true]
					{
					pushFollow(FOLLOW_function_definition_in_prog1002);
					function_definition2=function_definition(true);
					state._fsp--;

					adaptor.addChild(root_0, function_definition2.getTree());

					}
					break;

				default :
					break loop1;
				}
			}

			EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_prog1007); 
			EOF3_tree = (Object)adaptor.create(EOF3);
			adaptor.addChild(root_0, EOF3_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "prog"


	public static class statement_return extends ParserRuleReturnScope {
		public NamedElement objReturnValue;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:811:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | function_call[defer] SEMIC | built_in_function[defer] SEMIC | return_statement[defer] SEMIC );
	public final EugeneParser.statement_return statement(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.statement_return retval = new EugeneParser.statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMIC5=null;
		Token SEMIC7=null;
		Token SEMIC9=null;
		Token SEMIC11=null;
		Token SEMIC12=null;
		Token SEMIC15=null;
		Token SEMIC17=null;
		Token SEMIC19=null;
		ParserRuleReturnScope de =null;
		ParserRuleReturnScope includeStatement4 =null;
		ParserRuleReturnScope declarationStatement6 =null;
		ParserRuleReturnScope printStatement8 =null;
		ParserRuleReturnScope assignment10 =null;
		ParserRuleReturnScope imperativeStatements13 =null;
		ParserRuleReturnScope function_call14 =null;
		ParserRuleReturnScope built_in_function16 =null;
		ParserRuleReturnScope return_statement18 =null;

		Object SEMIC5_tree=null;
		Object SEMIC7_tree=null;
		Object SEMIC9_tree=null;
		Object SEMIC11_tree=null;
		Object SEMIC12_tree=null;
		Object SEMIC15_tree=null;
		Object SEMIC17_tree=null;
		Object SEMIC19_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:814:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | function_call[defer] SEMIC | built_in_function[defer] SEMIC | return_statement[defer] SEMIC )
			int alt3=9;
			switch ( input.LA(1) ) {
			case HASHMARK:
			case INCLUDE_LC:
			case INCLUDE_UC:
				{
				alt3=1;
				}
				break;
			case ARRAY:
			case BOOL:
			case BOOLEAN:
			case COLLECTION:
			case DEVICE:
			case GRAMMAR:
			case INTERACTION:
			case NUM:
			case PART:
			case PART_TYPE:
			case PROPERTY:
			case RULE:
			case TXT:
			case TYPE:
				{
				alt3=2;
				}
				break;
			case ID:
				{
				switch ( input.LA(2) ) {
				case LEFTP:
					{
					int LA3_9 = input.LA(3);
					if ( (LA3_9==RIGHTP) ) {
						int LA3_11 = input.LA(4);
						if ( (LA3_11==SEMIC) ) {
							alt3=7;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 3, 11, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA3_9==DOLLAR||(LA3_9 >= EXIT_LC && LA3_9 <= EXIT_UC)||(LA3_9 >= FALSE_LC && LA3_9 <= FALSE_UC)||LA3_9==ID||(LA3_9 >= LEFTP && LA3_9 <= LEFTSBR)||LA3_9==MINUS||LA3_9==NUMBER||LA3_9==PERMUTE||LA3_9==PRODUCT||(LA3_9 >= RANDOM_LC && LA3_9 <= REAL)||(LA3_9 >= SAVE_LC && LA3_9 <= SAVE_UC)||(LA3_9 >= SIZEOF_LC && LA3_9 <= STORE_UC)||(LA3_9 >= STRING && LA3_9 <= TRUE_UC)) ) {
						alt3=7;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 3, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case DOLLAR:
				case ID:
				case LC_INDUCES:
				case LC_REPRESSES:
				case UC_INDUCES:
				case UC_REPRESSES:
					{
					alt3=2;
					}
					break;
				case DOT:
				case EQUALS:
				case LEFTSBR:
					{
					alt3=4;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case PRINTLN_LC:
			case PRINTLN_UC:
			case PRINT_LC:
			case PRINT_UC:
				{
				alt3=3;
				}
				break;
			case GENBANK:
			case IMPORT_LC:
			case IMPORT_UC:
			case REGISTRY:
			case SBOL:
				{
				alt3=5;
				}
				break;
			case LC_FOR:
			case LC_FORALL:
			case LC_IF:
			case LC_WHILE:
			case UC_FOR:
			case UC_FORALL:
			case UC_IF:
			case UC_WHILE:
				{
				alt3=6;
				}
				break;
			case EXIT_LC:
			case EXIT_UC:
			case PERMUTE:
			case PRODUCT:
			case RANDOM_LC:
			case RANDOM_UC:
			case SAVE_LC:
			case SAVE_UC:
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_UC:
			case STORE_LC:
			case STORE_UC:
				{
				alt3=8;
				}
				break;
			case RETURN_LC:
			case RETURN_UC:
				{
				alt3=9;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:815:3: includeStatement[defer] ( SEMIC )?
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_includeStatement_in_statement1034);
					includeStatement4=includeStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, includeStatement4.getTree());

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:815:27: ( SEMIC )?
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( (LA2_0==SEMIC) ) {
						alt2=1;
					}
					switch (alt2) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:815:28: SEMIC
							{
							SEMIC5=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1038); 
							SEMIC5_tree = (Object)adaptor.create(SEMIC5);
							adaptor.addChild(root_0, SEMIC5_tree);

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:816:4: declarationStatement[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_declarationStatement_in_statement1045);
					declarationStatement6=declarationStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, declarationStatement6.getTree());

					SEMIC7=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1048); 
					SEMIC7_tree = (Object)adaptor.create(SEMIC7);
					adaptor.addChild(root_0, SEMIC7_tree);

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:817:4: printStatement[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_printStatement_in_statement1054);
					printStatement8=printStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, printStatement8.getTree());

					SEMIC9=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1057); 
					SEMIC9_tree = (Object)adaptor.create(SEMIC9);
					adaptor.addChild(root_0, SEMIC9_tree);

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:818:4: assignment[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_assignment_in_statement1062);
					assignment10=assignment(defer);
					state._fsp--;

					adaptor.addChild(root_0, assignment10.getTree());

					SEMIC11=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1065); 
					SEMIC11_tree = (Object)adaptor.create(SEMIC11);
					adaptor.addChild(root_0, SEMIC11_tree);

					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:819:4: de= dataExchange[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_dataExchange_in_statement1072);
					de=dataExchange(defer);
					state._fsp--;

					adaptor.addChild(root_0, de.getTree());

					SEMIC12=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1075); 
					SEMIC12_tree = (Object)adaptor.create(SEMIC12);
					adaptor.addChild(root_0, SEMIC12_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        // iff there's no assignment to a LHS element,
					        // then we store the imported data into the 
					        // current scope's symbol tables
					        if(null != (de!=null?((EugeneParser.dataExchange_return)de).e:null)) {
					            this.interp.dataExchange((de!=null?((EugeneParser.dataExchange_return)de).e:null));
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:833:4: imperativeStatements[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_imperativeStatements_in_statement1082);
					imperativeStatements13=imperativeStatements(defer);
					state._fsp--;

					adaptor.addChild(root_0, imperativeStatements13.getTree());

					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:834:4: function_call[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_function_call_in_statement1088);
					function_call14=function_call(defer);
					state._fsp--;

					adaptor.addChild(root_0, function_call14.getTree());

					SEMIC15=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1091); 
					SEMIC15_tree = (Object)adaptor.create(SEMIC15);
					adaptor.addChild(root_0, SEMIC15_tree);

					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:835:4: built_in_function[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_built_in_function_in_statement1096);
					built_in_function16=built_in_function(defer);
					state._fsp--;

					adaptor.addChild(root_0, built_in_function16.getTree());

					SEMIC17=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1099); 
					SEMIC17_tree = (Object)adaptor.create(SEMIC17);
					adaptor.addChild(root_0, SEMIC17_tree);

					}
					break;
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:836:4: return_statement[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_return_statement_in_statement1104);
					return_statement18=return_statement(defer);
					state._fsp--;

					adaptor.addChild(root_0, return_statement18.getTree());

					SEMIC19=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1107); 
					SEMIC19_tree = (Object)adaptor.create(SEMIC19);
					adaptor.addChild(root_0, SEMIC19_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class declarationStatement_return extends ParserRuleReturnScope {
		public String name;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "declarationStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:845:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] );
	public final EugeneParser.declarationStatement_return declarationStatement(boolean defer) throws RecognitionException {
		EugeneParser.declarationStatement_return retval = new EugeneParser.declarationStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope v =null;
		ParserRuleReturnScope containerDeclaration20 =null;
		ParserRuleReturnScope propertyDeclaration21 =null;
		ParserRuleReturnScope typeDeclaration22 =null;
		ParserRuleReturnScope instantiation23 =null;
		ParserRuleReturnScope interactionDeclaration24 =null;
		ParserRuleReturnScope ruleDeclaration25 =null;
		ParserRuleReturnScope grammarDeclaration26 =null;
		ParserRuleReturnScope deviceDeclaration27 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:847:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] )
			int alt4=9;
			switch ( input.LA(1) ) {
			case BOOL:
			case BOOLEAN:
			case NUM:
			case TXT:
				{
				alt4=1;
				}
				break;
			case ARRAY:
			case COLLECTION:
				{
				alt4=2;
				}
				break;
			case PROPERTY:
				{
				alt4=3;
				}
				break;
			case PART:
			case PART_TYPE:
			case TYPE:
				{
				alt4=4;
				}
				break;
			case ID:
				{
				int LA4_5 = input.LA(2);
				if ( (LA4_5==DOLLAR||LA4_5==ID) ) {
					alt4=5;
				}
				else if ( (LA4_5==LC_INDUCES||LA4_5==LC_REPRESSES||LA4_5==UC_INDUCES||LA4_5==UC_REPRESSES) ) {
					alt4=6;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case INTERACTION:
				{
				alt4=6;
				}
				break;
			case RULE:
				{
				alt4=7;
				}
				break;
			case GRAMMAR:
				{
				alt4=8;
				}
				break;
			case DEVICE:
				{
				alt4=9;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:847:4: v= variableDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_variableDeclaration_in_declarationStatement1128);
					v=variableDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, v.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.name = (v!=null?((EugeneParser.variableDeclaration_return)v).varname:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:852:4: containerDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_containerDeclaration_in_declarationStatement1136);
					containerDeclaration20=containerDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, containerDeclaration20.getTree());

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:853:4: propertyDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement1142);
					propertyDeclaration21=propertyDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, propertyDeclaration21.getTree());

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:854:4: typeDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_typeDeclaration_in_declarationStatement1148);
					typeDeclaration22=typeDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, typeDeclaration22.getTree());

					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:855:4: instantiation[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_instantiation_in_declarationStatement1154);
					instantiation23=instantiation(defer);
					state._fsp--;

					adaptor.addChild(root_0, instantiation23.getTree());

					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:856:4: interactionDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement1160);
					interactionDeclaration24=interactionDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, interactionDeclaration24.getTree());

					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:857:4: ruleDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement1166);
					ruleDeclaration25=ruleDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, ruleDeclaration25.getTree());

					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:858:4: grammarDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_grammarDeclaration_in_declarationStatement1172);
					grammarDeclaration26=grammarDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, grammarDeclaration26.getTree());

					}
					break;
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:859:4: deviceDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement1178);
					deviceDeclaration27=deviceDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, deviceDeclaration27.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "declarationStatement"


	public static class variableDeclaration_return extends ParserRuleReturnScope {
		public String varname;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "variableDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:862:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] );
	public final EugeneParser.variableDeclaration_return variableDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.variableDeclaration_return retval = new EugeneParser.variableDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NUM28=null;
		Token TXT29=null;
		Token TXT30=null;
		Token LEFTSBR31=null;
		Token RIGHTSBR32=null;
		Token NUM33=null;
		Token LEFTSBR34=null;
		Token RIGHTSBR35=null;
		Token set36=null;
		ParserRuleReturnScope n =null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope tl =null;
		ParserRuleReturnScope nl =null;
		ParserRuleReturnScope b =null;

		Object NUM28_tree=null;
		Object TXT29_tree=null;
		Object TXT30_tree=null;
		Object LEFTSBR31_tree=null;
		Object RIGHTSBR32_tree=null;
		Object NUM33_tree=null;
		Object LEFTSBR34_tree=null;
		Object RIGHTSBR35_tree=null;
		Object set36_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:864:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] )
			int alt5=5;
			switch ( input.LA(1) ) {
			case NUM:
				{
				int LA5_1 = input.LA(2);
				if ( (LA5_1==LEFTSBR) ) {
					alt5=4;
				}
				else if ( (LA5_1==ID) ) {
					alt5=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case TXT:
				{
				int LA5_2 = input.LA(2);
				if ( (LA5_2==LEFTSBR) ) {
					alt5=3;
				}
				else if ( (LA5_2==ID) ) {
					alt5=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case BOOL:
			case BOOLEAN:
				{
				alt5=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:864:4: NUM n= numdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					NUM28=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1196); 
					NUM28_tree = (Object)adaptor.create(NUM28);
					adaptor.addChild(root_0, NUM28_tree);

					pushFollow(FOLLOW_numdecl_in_variableDeclaration1200);
					n=numdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, n.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (n!=null?((EugeneParser.numdecl_return)n).varname:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:869:4: TXT t= txtdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					TXT29=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1211); 
					TXT29_tree = (Object)adaptor.create(TXT29);
					adaptor.addChild(root_0, TXT29_tree);

					pushFollow(FOLLOW_txtdecl_in_variableDeclaration1215);
					t=txtdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (t!=null?((EugeneParser.txtdecl_return)t).varname:null);
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:874:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					TXT30=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1226); 
					TXT30_tree = (Object)adaptor.create(TXT30);
					adaptor.addChild(root_0, TXT30_tree);

					LEFTSBR31=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1228); 
					LEFTSBR31_tree = (Object)adaptor.create(LEFTSBR31);
					adaptor.addChild(root_0, LEFTSBR31_tree);

					RIGHTSBR32=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1230); 
					RIGHTSBR32_tree = (Object)adaptor.create(RIGHTSBR32);
					adaptor.addChild(root_0, RIGHTSBR32_tree);

					pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration1234);
					tl=txtlistdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, tl.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (tl!=null?((EugeneParser.txtlistdecl_return)tl).varname:null);
					}	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:879:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					NUM33=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1245); 
					NUM33_tree = (Object)adaptor.create(NUM33);
					adaptor.addChild(root_0, NUM33_tree);

					LEFTSBR34=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1247); 
					LEFTSBR34_tree = (Object)adaptor.create(LEFTSBR34);
					adaptor.addChild(root_0, LEFTSBR34_tree);

					RIGHTSBR35=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1249); 
					RIGHTSBR35_tree = (Object)adaptor.create(RIGHTSBR35);
					adaptor.addChild(root_0, RIGHTSBR35_tree);

					pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1253);
					nl=numlistdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, nl.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (nl!=null?((EugeneParser.numlistdecl_return)nl).varname:null);
					}	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:884:4: ( BOOLEAN | BOOL ) b= booldecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					set36=input.LT(1);
					if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set36));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_booldecl_in_variableDeclaration1272);
					b=booldecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, b.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (b!=null?((EugeneParser.booldecl_return)b).varname:null);
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "variableDeclaration"


	public static class numdecl_return extends ParserRuleReturnScope {
		public String varname;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "numdecl"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:891:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
	public final EugeneParser.numdecl_return numdecl(boolean defer) throws RecognitionException {
		EugeneParser.numdecl_return retval = new EugeneParser.numdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID37=null;
		Token COMMA38=null;
		Token ID40=null;
		Token EQUALS41=null;
		Token COMMA42=null;
		ParserRuleReturnScope ex =null;
		ParserRuleReturnScope numdecl39 =null;
		ParserRuleReturnScope numdecl43 =null;

		Object ID37_tree=null;
		Object COMMA38_tree=null;
		Object ID40_tree=null;
		Object EQUALS41_tree=null;
		Object COMMA42_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==ID) ) {
				int LA8_1 = input.LA(2);
				if ( (LA8_1==EQUALS) ) {
					alt8=2;
				}
				else if ( (LA8_1==COMMA||LA8_1==RIGHTP||LA8_1==SEMIC) ) {
					alt8=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:4: ID ( COMMA numdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID37=(Token)match(input,ID,FOLLOW_ID_in_numdecl1295); 
					ID37_tree = (Object)adaptor.create(ID37);
					adaptor.addChild(root_0, ID37_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    declareVariableNoValue((ID37!=null?ID37.getText():null), EugeneConstants.NUM);
					    retval.varname = (ID37!=null?ID37.getText():null);
					}
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:898:5: ( COMMA numdecl[defer] )?
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==COMMA) ) {
						int LA6_1 = input.LA(2);
						if ( (LA6_1==ID) ) {
							alt6=1;
						}
					}
					switch (alt6) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:898:6: COMMA numdecl[defer]
							{
							COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1301); 
							COMMA38_tree = (Object)adaptor.create(COMMA38);
							adaptor.addChild(root_0, COMMA38_tree);

							pushFollow(FOLLOW_numdecl_in_numdecl1303);
							numdecl39=numdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numdecl39.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:899:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID40=(Token)match(input,ID,FOLLOW_ID_in_numdecl1311); 
					ID40_tree = (Object)adaptor.create(ID40);
					adaptor.addChild(root_0, ID40_tree);

					EQUALS41=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1313); 
					EQUALS41_tree = (Object)adaptor.create(EQUALS41);
					adaptor.addChild(root_0, EQUALS41_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:899:14: (ex= expr[defer] )
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:899:15: ex= expr[defer]
					{
					pushFollow(FOLLOW_expr_in_numdecl1318);
					ex=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, ex.getTree());

					}


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    declareVariableWithValueNum((ID40!=null?ID40.getText():null), (ex!=null?((EugeneParser.expr_return)ex).p:null), (ex!=null?((EugeneParser.expr_return)ex).index:0));
					    retval.varname = (ID40!=null?ID40.getText():null);
					}
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:904:5: ( COMMA numdecl[defer] )?
					int alt7=2;
					int LA7_0 = input.LA(1);
					if ( (LA7_0==COMMA) ) {
						int LA7_1 = input.LA(2);
						if ( (LA7_1==ID) ) {
							alt7=1;
						}
					}
					switch (alt7) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:904:6: COMMA numdecl[defer]
							{
							COMMA42=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1326); 
							COMMA42_tree = (Object)adaptor.create(COMMA42);
							adaptor.addChild(root_0, COMMA42_tree);

							pushFollow(FOLLOW_numdecl_in_numdecl1328);
							numdecl43=numdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numdecl43.getTree());

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "numdecl"


	public static class txtdecl_return extends ParserRuleReturnScope {
		public String varname;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "txtdecl"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:907:1: txtdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? );
	public final EugeneParser.txtdecl_return txtdecl(boolean defer) throws RecognitionException {
		EugeneParser.txtdecl_return retval = new EugeneParser.txtdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID44=null;
		Token COMMA45=null;
		Token EQUALS47=null;
		Token COMMA48=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope txtdecl46 =null;
		ParserRuleReturnScope txtdecl49 =null;

		Object var_tree=null;
		Object ID44_tree=null;
		Object COMMA45_tree=null;
		Object EQUALS47_tree=null;
		Object COMMA48_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:909:2: ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==ID) ) {
				int LA11_1 = input.LA(2);
				if ( (LA11_1==EQUALS) ) {
					alt11=2;
				}
				else if ( (LA11_1==COMMA||LA11_1==RIGHTP||LA11_1==SEMIC) ) {
					alt11=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 11, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:909:4: ID ( COMMA txtdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID44=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1348); 
					ID44_tree = (Object)adaptor.create(ID44);
					adaptor.addChild(root_0, ID44_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID44!=null?ID44.getText():null), EugeneConstants.TXT);
								retval.varname = (ID44!=null?ID44.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:915:5: ( COMMA txtdecl[defer] )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0==COMMA) ) {
						int LA9_1 = input.LA(2);
						if ( (LA9_1==ID) ) {
							alt9=1;
						}
					}
					switch (alt9) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:915:6: COMMA txtdecl[defer]
							{
							COMMA45=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1355); 
							COMMA45_tree = (Object)adaptor.create(COMMA45);
							adaptor.addChild(root_0, COMMA45_tree);

							pushFollow(FOLLOW_txtdecl_in_txtdecl1357);
							txtdecl46=txtdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtdecl46.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:917:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1368); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS47=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1370); 
					EQUALS47_tree = (Object)adaptor.create(EQUALS47);
					adaptor.addChild(root_0, EQUALS47_tree);

					pushFollow(FOLLOW_expr_in_txtdecl1374);
					let=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, let.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableWithValueTxt((var!=null?var.getText():null), (let!=null?((EugeneParser.expr_return)let).p:null), (let!=null?((EugeneParser.expr_return)let).index:0));
								retval.varname = (var!=null?var.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:923:5: ( COMMA txtdecl[defer] )?
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==COMMA) ) {
						int LA10_1 = input.LA(2);
						if ( (LA10_1==ID) ) {
							alt10=1;
						}
					}
					switch (alt10) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:923:6: COMMA txtdecl[defer]
							{
							COMMA48=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1382); 
							COMMA48_tree = (Object)adaptor.create(COMMA48);
							adaptor.addChild(root_0, COMMA48_tree);

							pushFollow(FOLLOW_txtdecl_in_txtdecl1384);
							txtdecl49=txtdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtdecl49.getTree());

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "txtdecl"


	public static class txtlistdecl_return extends ParserRuleReturnScope {
		public String varname;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "txtlistdecl"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:926:1: txtlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? );
	public final EugeneParser.txtlistdecl_return txtlistdecl(boolean defer) throws RecognitionException {
		EugeneParser.txtlistdecl_return retval = new EugeneParser.txtlistdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID50=null;
		Token COMMA51=null;
		Token EQUALS53=null;
		Token COMMA54=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope txtlistdecl52 =null;
		ParserRuleReturnScope txtlistdecl55 =null;

		Object var_tree=null;
		Object ID50_tree=null;
		Object COMMA51_tree=null;
		Object EQUALS53_tree=null;
		Object COMMA54_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:928:2: ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ID) ) {
				int LA14_1 = input.LA(2);
				if ( (LA14_1==EQUALS) ) {
					alt14=2;
				}
				else if ( (LA14_1==COMMA||LA14_1==RIGHTP||LA14_1==SEMIC) ) {
					alt14=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 14, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:928:4: ID ( COMMA txtlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID50=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1404); 
					ID50_tree = (Object)adaptor.create(ID50);
					adaptor.addChild(root_0, ID50_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID50!=null?ID50.getText():null), EugeneConstants.TXTLIST);
								retval.varname = (ID50!=null?ID50.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:934:5: ( COMMA txtlistdecl[defer] )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==COMMA) ) {
						int LA12_1 = input.LA(2);
						if ( (LA12_1==ID) ) {
							alt12=1;
						}
					}
					switch (alt12) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:934:6: COMMA txtlistdecl[defer]
							{
							COMMA51=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1411); 
							COMMA51_tree = (Object)adaptor.create(COMMA51);
							adaptor.addChild(root_0, COMMA51_tree);

							pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1413);
							txtlistdecl52=txtlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtlistdecl52.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:935:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1423); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS53=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1425); 
					EQUALS53_tree = (Object)adaptor.create(EQUALS53);
					adaptor.addChild(root_0, EQUALS53_tree);

					typeList = EugeneConstants.TXT;
					pushFollow(FOLLOW_expr_in_txtlistdecl1431);
					let=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, let.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableWithValueTxtList((var!=null?var.getText():null), (let!=null?((EugeneParser.expr_return)let).p:null));
								retval.varname = (var!=null?var.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:941:5: ( COMMA txtlistdecl[defer] )?
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( (LA13_0==COMMA) ) {
						int LA13_1 = input.LA(2);
						if ( (LA13_1==ID) ) {
							alt13=1;
						}
					}
					switch (alt13) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:941:6: COMMA txtlistdecl[defer]
							{
							COMMA54=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1439); 
							COMMA54_tree = (Object)adaptor.create(COMMA54);
							adaptor.addChild(root_0, COMMA54_tree);

							pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1441);
							txtlistdecl55=txtlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtlistdecl55.getTree());

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "txtlistdecl"


	public static class numlistdecl_return extends ParserRuleReturnScope {
		public String varname;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "numlistdecl"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:944:1: numlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? );
	public final EugeneParser.numlistdecl_return numlistdecl(boolean defer) throws RecognitionException {
		EugeneParser.numlistdecl_return retval = new EugeneParser.numlistdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID56=null;
		Token COMMA57=null;
		Token EQUALS59=null;
		Token COMMA60=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope numlistdecl58 =null;
		ParserRuleReturnScope numlistdecl61 =null;

		Object var_tree=null;
		Object ID56_tree=null;
		Object COMMA57_tree=null;
		Object EQUALS59_tree=null;
		Object COMMA60_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:946:2: ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? )
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==ID) ) {
				int LA17_1 = input.LA(2);
				if ( (LA17_1==EQUALS) ) {
					alt17=2;
				}
				else if ( (LA17_1==COMMA||LA17_1==RIGHTP||LA17_1==SEMIC) ) {
					alt17=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 17, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:946:4: ID ( COMMA numlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID56=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1461); 
					ID56_tree = (Object)adaptor.create(ID56);
					adaptor.addChild(root_0, ID56_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID56!=null?ID56.getText():null), EugeneConstants.NUMLIST);
								retval.varname = (ID56!=null?ID56.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:952:5: ( COMMA numlistdecl[defer] )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==COMMA) ) {
						int LA15_1 = input.LA(2);
						if ( (LA15_1==ID) ) {
							alt15=1;
						}
					}
					switch (alt15) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:952:6: COMMA numlistdecl[defer]
							{
							COMMA57=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1468); 
							COMMA57_tree = (Object)adaptor.create(COMMA57);
							adaptor.addChild(root_0, COMMA57_tree);

							pushFollow(FOLLOW_numlistdecl_in_numlistdecl1470);
							numlistdecl58=numlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numlistdecl58.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:953:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1480); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS59=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1482); 
					EQUALS59_tree = (Object)adaptor.create(EQUALS59);
					adaptor.addChild(root_0, EQUALS59_tree);

					 typeList = EugeneConstants.NUM;
					pushFollow(FOLLOW_expr_in_numlistdecl1487);
					let=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, let.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableWithValueNumList((var!=null?var.getText():null), (let!=null?((EugeneParser.expr_return)let).p:null));
								retval.varname = (var!=null?var.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:959:5: ( COMMA numlistdecl[defer] )?
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( (LA16_0==COMMA) ) {
						int LA16_1 = input.LA(2);
						if ( (LA16_1==ID) ) {
							alt16=1;
						}
					}
					switch (alt16) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:959:6: COMMA numlistdecl[defer]
							{
							COMMA60=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1495); 
							COMMA60_tree = (Object)adaptor.create(COMMA60);
							adaptor.addChild(root_0, COMMA60_tree);

							pushFollow(FOLLOW_numlistdecl_in_numlistdecl1497);
							numlistdecl61=numlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numlistdecl61.getTree());

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "numlistdecl"


	public static class booldecl_return extends ParserRuleReturnScope {
		public String varname;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "booldecl"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:962:1: booldecl[boolean defer] returns [String varname] : ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] );
	public final EugeneParser.booldecl_return booldecl(boolean defer) throws RecognitionException {
		EugeneParser.booldecl_return retval = new EugeneParser.booldecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID62=null;
		Token COMMA63=null;
		Token EQUALS65=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope booldecl64 =null;

		Object var_tree=null;
		Object ID62_tree=null;
		Object COMMA63_tree=null;
		Object EQUALS65_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:964:2: ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] )
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==ID) ) {
				int LA19_1 = input.LA(2);
				if ( (LA19_1==EQUALS) ) {
					alt19=2;
				}
				else if ( (LA19_1==COMMA||LA19_1==RIGHTP||LA19_1==SEMIC) ) {
					alt19=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}

			switch (alt19) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:964:4: ID ( COMMA booldecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID62=(Token)match(input,ID,FOLLOW_ID_in_booldecl1517); 
					ID62_tree = (Object)adaptor.create(ID62);
					adaptor.addChild(root_0, ID62_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID62!=null?ID62.getText():null), EugeneConstants.BOOLEAN);
								retval.varname = (ID62!=null?ID62.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:970:5: ( COMMA booldecl[defer] )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==COMMA) ) {
						int LA18_1 = input.LA(2);
						if ( (LA18_1==ID) ) {
							alt18=1;
						}
					}
					switch (alt18) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:970:6: COMMA booldecl[defer]
							{
							COMMA63=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1524); 
							COMMA63_tree = (Object)adaptor.create(COMMA63);
							adaptor.addChild(root_0, COMMA63_tree);

							pushFollow(FOLLOW_booldecl_in_booldecl1526);
							booldecl64=booldecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, booldecl64.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:971:4: var= ID EQUALS let= expr[defer]
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1536); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS65=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1538); 
					EQUALS65_tree = (Object)adaptor.create(EQUALS65);
					adaptor.addChild(root_0, EQUALS65_tree);

					pushFollow(FOLLOW_expr_in_booldecl1542);
					let=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, let.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableWithValueBool((var!=null?var.getText():null), (let!=null?((EugeneParser.expr_return)let).p:null));
								retval.varname = (var!=null?var.getText():null);
							}
							
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "booldecl"


	public static class propertyDeclaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "propertyDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:980:1: propertyDeclaration[boolean defer] : PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP ;
	public final EugeneParser.propertyDeclaration_return propertyDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.propertyDeclaration_return retval = new EugeneParser.propertyDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token nameToken=null;
		Token PROPERTY66=null;
		Token LEFTP67=null;
		Token RIGHTP68=null;
		ParserRuleReturnScope typeToken =null;

		Object nameToken_tree=null;
		Object PROPERTY66_tree=null;
		Object LEFTP67_tree=null;
		Object RIGHTP68_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:981:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:981:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			PROPERTY66=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1560); 
			PROPERTY66_tree = (Object)adaptor.create(PROPERTY66);
			adaptor.addChild(root_0, PROPERTY66_tree);

			nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1564); 
			nameToken_tree = (Object)adaptor.create(nameToken);
			adaptor.addChild(root_0, nameToken_tree);

			LEFTP67=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1566); 
			LEFTP67_tree = (Object)adaptor.create(LEFTP67);
			adaptor.addChild(root_0, LEFTP67_tree);

			pushFollow(FOLLOW_propertyType_in_propertyDeclaration1570);
			typeToken=propertyType();
			state._fsp--;

			adaptor.addChild(root_0, typeToken.getTree());

			RIGHTP68=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1572); 
			RIGHTP68_tree = (Object)adaptor.create(RIGHTP68);
			adaptor.addChild(root_0, RIGHTP68_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        interp.createProperty(
			            (nameToken!=null?nameToken.getText():null), 
			            (typeToken!=null?((EugeneParser.propertyType_return)typeToken).type:null));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			}
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "propertyDeclaration"


	public static class propertyType_return extends ParserRuleReturnScope {
		public String type;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "propertyType"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:1: propertyType returns [String type] : ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) );
	public final EugeneParser.propertyType_return propertyType() throws RecognitionException {
		EugeneParser.propertyType_return retval = new EugeneParser.propertyType_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token TXT69=null;
		Token TXT70=null;
		Token LEFTSBR71=null;
		Token RIGHTSBR72=null;
		Token NUM73=null;
		Token NUM74=null;
		Token LEFTSBR75=null;
		Token RIGHTSBR76=null;
		Token set77=null;

		Object TXT69_tree=null;
		Object TXT70_tree=null;
		Object LEFTSBR71_tree=null;
		Object RIGHTSBR72_tree=null;
		Object NUM73_tree=null;
		Object NUM74_tree=null;
		Object LEFTSBR75_tree=null;
		Object RIGHTSBR76_tree=null;
		Object set77_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:996:2: ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) )
			int alt20=5;
			switch ( input.LA(1) ) {
			case TXT:
				{
				int LA20_1 = input.LA(2);
				if ( (LA20_1==LEFTSBR) ) {
					alt20=2;
				}
				else if ( (LA20_1==RIGHTP) ) {
					alt20=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 20, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case NUM:
				{
				int LA20_2 = input.LA(2);
				if ( (LA20_2==LEFTSBR) ) {
					alt20=4;
				}
				else if ( (LA20_2==RIGHTP) ) {
					alt20=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 20, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case BOOL:
			case BOOLEAN:
				{
				alt20=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}
			switch (alt20) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:996:4: TXT
					{
					root_0 = (Object)adaptor.nil();


					TXT69=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1591); 
					TXT69_tree = (Object)adaptor.create(TXT69);
					adaptor.addChild(root_0, TXT69_tree);


					retval.type = EugeneConstants.TXT;	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:999:4: TXT LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					TXT70=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1598); 
					TXT70_tree = (Object)adaptor.create(TXT70);
					adaptor.addChild(root_0, TXT70_tree);

					LEFTSBR71=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1600); 
					LEFTSBR71_tree = (Object)adaptor.create(LEFTSBR71);
					adaptor.addChild(root_0, LEFTSBR71_tree);

					RIGHTSBR72=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1602); 
					RIGHTSBR72_tree = (Object)adaptor.create(RIGHTSBR72);
					adaptor.addChild(root_0, RIGHTSBR72_tree);


					retval.type = EugeneConstants.TXTLIST;	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1002:4: NUM
					{
					root_0 = (Object)adaptor.nil();


					NUM73=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1609); 
					NUM73_tree = (Object)adaptor.create(NUM73);
					adaptor.addChild(root_0, NUM73_tree);


					retval.type = EugeneConstants.NUM;	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1005:4: NUM LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					NUM74=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1617); 
					NUM74_tree = (Object)adaptor.create(NUM74);
					adaptor.addChild(root_0, NUM74_tree);

					LEFTSBR75=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1619); 
					LEFTSBR75_tree = (Object)adaptor.create(LEFTSBR75);
					adaptor.addChild(root_0, LEFTSBR75_tree);

					RIGHTSBR76=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1621); 
					RIGHTSBR76_tree = (Object)adaptor.create(RIGHTSBR76);
					adaptor.addChild(root_0, RIGHTSBR76_tree);


					retval.type = EugeneConstants.NUMLIST;	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1008:4: ( BOOLEAN | BOOL )
					{
					root_0 = (Object)adaptor.nil();


					set77=input.LT(1);
					if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set77));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}

					retval.type = EugeneConstants.BOOLEAN;	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "propertyType"


	public static class typeDeclaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "typeDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:1: typeDeclaration[boolean defer] : ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? );
	public final EugeneParser.typeDeclaration_return typeDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.typeDeclaration_return retval = new EugeneParser.typeDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token nameToken=null;
		Token TYPE79=null;
		Token LEFTP80=null;
		Token RIGHTP81=null;
		ParserRuleReturnScope lstToken =null;
		ParserRuleReturnScope partTypeDeclaration78 =null;

		Object nameToken_tree=null;
		Object TYPE79_tree=null;
		Object LEFTP80_tree=null;
		Object RIGHTP81_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1018:2: ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( ((LA23_0 >= PART && LA23_0 <= PART_TYPE)) ) {
				alt23=1;
			}
			else if ( (LA23_0==TYPE) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1018:4: partTypeDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1650);
					partTypeDeclaration78=partTypeDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, partTypeDeclaration78.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:4: ( TYPE )
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:5: TYPE
					{
					TYPE79=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1657); 
					TYPE79_tree = (Object)adaptor.create(TYPE79);
					adaptor.addChild(root_0, TYPE79_tree);

					}

					nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1662); 
					nameToken_tree = (Object)adaptor.create(nameToken);
					adaptor.addChild(root_0, nameToken_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:24: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==LEFTP) ) {
						alt22=1;
					}
					switch (alt22) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:25: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
							{
							LEFTP80=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1665); 
							LEFTP80_tree = (Object)adaptor.create(LEFTP80);
							adaptor.addChild(root_0, LEFTP80_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:31: (lstToken= listOfIDs[defer] )?
							int alt21=2;
							int LA21_0 = input.LA(1);
							if ( (LA21_0==ID) ) {
								alt21=1;
							}
							switch (alt21) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:32: lstToken= listOfIDs[defer]
									{
									pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1670);
									lstToken=listOfIDs(defer);
									state._fsp--;

									adaptor.addChild(root_0, lstToken.getTree());

									}
									break;

							}

							RIGHTP81=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1675); 
							RIGHTP81_tree = (Object)adaptor.create(RIGHTP81);
							adaptor.addChild(root_0, RIGHTP81_tree);

							}
							break;

					}


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        interp.createType(
					            (nameToken!=null?nameToken.getText():null), 
					            (lstToken!=null?((EugeneParser.listOfIDs_return)lstToken).lstElements:null));
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }
					}
					        
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "typeDeclaration"


	public static class partTypeDeclaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "partTypeDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1032:1: partTypeDeclaration[boolean defer] : ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? ;
	public final EugeneParser.partTypeDeclaration_return partTypeDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.partTypeDeclaration_return retval = new EugeneParser.partTypeDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token nameToken=null;
		Token set82=null;
		Token LEFTP83=null;
		Token RIGHTP84=null;
		ParserRuleReturnScope lstToken =null;

		Object nameToken_tree=null;
		Object set82_tree=null;
		Object LEFTP83_tree=null;
		Object RIGHTP84_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1033:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1033:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			set82=input.LT(1);
			if ( (input.LA(1) >= PART && input.LA(1) <= PART_TYPE) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set82));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1703); 
			nameToken_tree = (Object)adaptor.create(nameToken);
			adaptor.addChild(root_0, nameToken_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1033:35: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==LEFTP) ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1033:36: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
					{
					LEFTP83=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1706); 
					LEFTP83_tree = (Object)adaptor.create(LEFTP83);
					adaptor.addChild(root_0, LEFTP83_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1033:42: (lstToken= listOfIDs[defer] )?
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0==ID) ) {
						alt24=1;
					}
					switch (alt24) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1033:43: lstToken= listOfIDs[defer]
							{
							pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1711);
							lstToken=listOfIDs(defer);
							state._fsp--;

							adaptor.addChild(root_0, lstToken.getTree());

							}
							break;

					}

					RIGHTP84=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1716); 
					RIGHTP84_tree = (Object)adaptor.create(RIGHTP84);
					adaptor.addChild(root_0, RIGHTP84_tree);

					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        interp.createPartType(
			            (nameToken!=null?nameToken.getText():null), 
			            (lstToken!=null?((EugeneParser.listOfIDs_return)lstToken).lstElements:null));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			}
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "partTypeDeclaration"


	public static class containerDeclaration_return extends ParserRuleReturnScope {
		public NamedElement ne;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "containerDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1049:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
	public final EugeneParser.containerDeclaration_return containerDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.containerDeclaration_return retval = new EugeneParser.containerDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token c=null;
		Token a=null;
		Token name=null;
		Token LEFTSBR85=null;
		Token RIGHTSBR86=null;
		Token LEFTP87=null;
		Token RIGHTP89=null;
		ParserRuleReturnScope list_of_declarations88 =null;

		Object c_tree=null;
		Object a_tree=null;
		Object name_tree=null;
		Object LEFTSBR85_tree=null;
		Object RIGHTSBR86_tree=null;
		Object LEFTP87_tree=null;
		Object RIGHTP89_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:2: ( (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==COLLECTION) ) {
				alt27=1;
			}
			else if ( (LA27_0==ARRAY) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:5: c= COLLECTION
					{
					c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1743); 
					c_tree = (Object)adaptor.create(c);
					adaptor.addChild(root_0, c_tree);

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:21: a= ARRAY ( LEFTSBR RIGHTSBR )?
					{
					a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1750); 
					a_tree = (Object)adaptor.create(a);
					adaptor.addChild(root_0, a_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:29: ( LEFTSBR RIGHTSBR )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==LEFTSBR) ) {
						alt26=1;
					}
					switch (alt26) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1051:30: LEFTSBR RIGHTSBR
							{
							LEFTSBR85=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1753); 
							LEFTSBR85_tree = (Object)adaptor.create(LEFTSBR85);
							adaptor.addChild(root_0, LEFTSBR85_tree);

							RIGHTSBR86=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1755); 
							RIGHTSBR86_tree = (Object)adaptor.create(RIGHTSBR86);
							adaptor.addChild(root_0, RIGHTSBR86_tree);

							}
							break;

					}

					}

					}
					break;

			}

			name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1763); 
			name_tree = (Object)adaptor.create(name);
			adaptor.addChild(root_0, name_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    if(null != c) {
			        retval.ne = new EugeneCollection((name!=null?name.getText():null));
			    } else if(null != a) {
			        retval.ne = new EugeneArray((name!=null?name.getText():null));
			    }

			    /*
			     * push it on the stack
			     */
			    this.interp.push((StackElement)retval.ne);
			    
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1065:4: ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==LEFTP) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1065:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
					{
					LEFTP87=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1768); 
					LEFTP87_tree = (Object)adaptor.create(LEFTP87);
					adaptor.addChild(root_0, LEFTP87_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1065:11: ( list_of_declarations[defer] )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==ARRAY||(LA28_0 >= BOOL && LA28_0 <= COLLECTION)||LA28_0==DEVICE||LA28_0==DOLLAR||(LA28_0 >= EXIT_LC && LA28_0 <= EXIT_UC)||(LA28_0 >= FALSE_LC && LA28_0 <= FALSE_UC)||LA28_0==GRAMMAR||LA28_0==ID||LA28_0==INTERACTION||(LA28_0 >= LEFTP && LA28_0 <= LEFTSBR)||LA28_0==MINUS||(LA28_0 >= NUM && LA28_0 <= NUMBER)||(LA28_0 >= PART && LA28_0 <= PERMUTE)||(LA28_0 >= PRODUCT && LA28_0 <= REAL)||(LA28_0 >= RULE && LA28_0 <= SAVE_UC)||(LA28_0 >= SIZEOF_LC && LA28_0 <= STORE_UC)||(LA28_0 >= STRING && LA28_0 <= TYPE)) ) {
						alt28=1;
					}
					switch (alt28) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1065:12: list_of_declarations[defer]
							{
							pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1771);
							list_of_declarations88=list_of_declarations(defer);
							state._fsp--;

							adaptor.addChild(root_0, list_of_declarations88.getTree());

							}
							break;

					}

					RIGHTP89=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1776); 
					RIGHTP89_tree = (Object)adaptor.create(RIGHTP89);
					adaptor.addChild(root_0, RIGHTP89_tree);

					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        /*
			         *   after all declarations are done, we 
			         *   pop the collection from the stack.
			         */
			        this.interp.pop();
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "containerDeclaration"


	public static class list_of_declarations_return extends ParserRuleReturnScope {
		public List<NamedElement> elements;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "list_of_declarations"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1080:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
	public final EugeneParser.list_of_declarations_return list_of_declarations(boolean defer) throws RecognitionException {
		EugeneParser.list_of_declarations_return retval = new EugeneParser.list_of_declarations_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA90=null;
		ParserRuleReturnScope ds =null;
		ParserRuleReturnScope exp =null;
		ParserRuleReturnScope lod =null;

		Object COMMA90_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1082:2: ( (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1082:4: (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1082:4: (ds= declarationStatement[defer] |exp= expr[defer] )
			int alt30=2;
			switch ( input.LA(1) ) {
			case ARRAY:
			case BOOL:
			case BOOLEAN:
			case COLLECTION:
			case DEVICE:
			case GRAMMAR:
			case INTERACTION:
			case NUM:
			case PART:
			case PART_TYPE:
			case PROPERTY:
			case RULE:
			case TXT:
			case TYPE:
				{
				alt30=1;
				}
				break;
			case ID:
				{
				int LA30_2 = input.LA(2);
				if ( (LA30_2==COMMA||LA30_2==DIV||LA30_2==DOT||(LA30_2 >= LEFTP && LA30_2 <= LEFTSBR)||LA30_2==MINUS||LA30_2==MULT||LA30_2==PLUS||LA30_2==RIGHTP) ) {
					alt30=2;
				}
				else if ( (LA30_2==DOLLAR||LA30_2==ID||LA30_2==LC_INDUCES||LA30_2==LC_REPRESSES||LA30_2==UC_INDUCES||LA30_2==UC_REPRESSES) ) {
					alt30=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 30, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DOLLAR:
			case EXIT_LC:
			case EXIT_UC:
			case FALSE_LC:
			case FALSE_UC:
			case LEFTP:
			case LEFTSBR:
			case MINUS:
			case NUMBER:
			case PERMUTE:
			case PRODUCT:
			case RANDOM_LC:
			case RANDOM_UC:
			case REAL:
			case SAVE_LC:
			case SAVE_UC:
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_UC:
			case STORE_LC:
			case STORE_UC:
			case STRING:
			case TRUE_LC:
			case TRUE_UC:
				{
				alt30=2;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}
			switch (alt30) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1082:6: ds= declarationStatement[defer]
					{
					pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1809);
					ds=declarationStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, ds.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1082:39: exp= expr[defer]
					{
					pushFollow(FOLLOW_expr_in_list_of_declarations1816);
					exp=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, exp.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(null != (exp!=null?((EugeneParser.expr_return)exp).element:null)) {
					            this.interp.addToContainer((exp!=null?((EugeneParser.expr_return)exp).element:null));
					        } else if(null != (exp!=null?((EugeneParser.expr_return)exp).p:null)) {
					            this.interp.addToContainer((exp!=null?((EugeneParser.expr_return)exp).p:null));
					        } else {
					            throw new EugeneException("Cannot add " + (exp!=null?input.toString(exp.start,exp.stop):null) + " to a Eugene container!");
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;

			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1096:5: ( COMMA lod= list_of_declarations[defer] )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==COMMA) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1096:7: COMMA lod= list_of_declarations[defer]
					{
					COMMA90=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1824); 
					COMMA90_tree = (Object)adaptor.create(COMMA90);
					adaptor.addChild(root_0, COMMA90_tree);

					pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations1828);
					lod=list_of_declarations(defer);
					state._fsp--;

					adaptor.addChild(root_0, lod.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "list_of_declarations"


	public static class instantiation_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "instantiation"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1102:1: instantiation[boolean defer] : t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
	public final EugeneParser.instantiation_return instantiation(boolean defer) throws RecognitionException {
		EugeneParser.instantiation_return retval = new EugeneParser.instantiation_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token t=null;
		Token LEFTP91=null;
		Token RIGHTP92=null;
		ParserRuleReturnScope n =null;
		ParserRuleReturnScope dotToken =null;
		ParserRuleReturnScope valueToken =null;

		Object t_tree=null;
		Object LEFTP91_tree=null;
		Object RIGHTP92_tree=null;


		NamedElement type = null;
		String instance_name = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1107:2: (t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1107:4: t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			t=(Token)match(input,ID,FOLLOW_ID_in_instantiation1856); 
			t_tree = (Object)adaptor.create(t);
			adaptor.addChild(root_0, t_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        type = this.interp.get((t!=null?t.getText():null));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			    
			    if(null==type) {
			        printError("I don't know anything about "+ (t!=null?t.getText():null) + "!");
			    }         
			             
			    if(!(type instanceof ComponentType)) {
			        printError((t!=null?t.getText():null)+" is not a type!");
			    }                  
			}	
				
			pushFollow(FOLLOW_dynamic_naming_in_instantiation1862);
			n=dynamic_naming(defer);
			state._fsp--;

			adaptor.addChild(root_0, n.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    instance_name = (n!=null?((EugeneParser.dynamic_naming_return)n).name:null);	
			}
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1127:4: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==LEFTP) ) {
				alt33=1;
			}
			switch (alt33) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1127:6: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
					{
					LEFTP91=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation1869); 
					LEFTP91_tree = (Object)adaptor.create(LEFTP91);
					adaptor.addChild(root_0, LEFTP91_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1127:12: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
					int alt32=3;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==DOT) ) {
						alt32=1;
					}
					else if ( (LA32_0==DOLLAR||(LA32_0 >= EXIT_LC && LA32_0 <= EXIT_UC)||(LA32_0 >= FALSE_LC && LA32_0 <= FALSE_UC)||LA32_0==ID||(LA32_0 >= LEFTP && LA32_0 <= LEFTSBR)||LA32_0==MINUS||LA32_0==NUMBER||LA32_0==PERMUTE||LA32_0==PRODUCT||(LA32_0 >= RANDOM_LC && LA32_0 <= REAL)||(LA32_0 >= SAVE_LC && LA32_0 <= SAVE_UC)||(LA32_0 >= SIZEOF_LC && LA32_0 <= STORE_UC)||(LA32_0 >= STRING && LA32_0 <= TRUE_UC)) ) {
						alt32=2;
					}
					switch (alt32) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1127:13: dotToken= listOfDotValues[defer]
							{
							pushFollow(FOLLOW_listOfDotValues_in_instantiation1874);
							dotToken=listOfDotValues(defer);
							state._fsp--;

							adaptor.addChild(root_0, dotToken.getTree());

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1127:45: valueToken= listOfValues[defer, (ComponentType)type]
							{
							pushFollow(FOLLOW_listOfValues_in_instantiation1879);
							valueToken=listOfValues(defer, (ComponentType)type);
							state._fsp--;

							adaptor.addChild(root_0, valueToken.getTree());

							}
							break;

					}

					RIGHTP92=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation1884); 
					RIGHTP92_tree = (Object)adaptor.create(RIGHTP92);
					adaptor.addChild(root_0, RIGHTP92_tree);

					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        if(null != dotToken) {
			            this.interp.instantiate(
			                (ComponentType)type, instance_name,
			                this.propertyListHolder,
			                this.propertyValuesHolder);    	
			        } else if (null != valueToken) {
			            this.interp.instantiate(
			                (ComponentType)type, instance_name,
			                this.propertyListHolder,
			                this.propertyValuesHolder);    	
			        } else {
			            this.interp.instantiate(
			                (ComponentType)type, instance_name,
			                new ArrayList<String>(),
			                new ArrayList<Variable>());    	
			        }
			        
			        // clear the "holder"-lists
			        this.propertyValuesHolder.clear();
			        this.propertyListHolder.clear();
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			}                
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instantiation"


	public static class listOfDotValues_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "listOfDotValues"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1161:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
	public final EugeneParser.listOfDotValues_return listOfDotValues(boolean defer) throws RecognitionException {
		EugeneParser.listOfDotValues_return retval = new EugeneParser.listOfDotValues_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token prop=null;
		Token p=null;
		Token DOT93=null;
		Token LEFTP94=null;
		Token RIGHTP95=null;
		Token COMMA96=null;
		Token DOT97=null;
		Token LEFTP98=null;
		Token RIGHTP99=null;
		ParserRuleReturnScope v1 =null;
		ParserRuleReturnScope v2 =null;

		Object prop_tree=null;
		Object p_tree=null;
		Object DOT93_tree=null;
		Object LEFTP94_tree=null;
		Object RIGHTP95_tree=null;
		Object COMMA96_tree=null;
		Object DOT97_tree=null;
		Object LEFTP98_tree=null;
		Object RIGHTP99_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1162:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1162:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
			{
			root_0 = (Object)adaptor.nil();


			DOT93=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1907); 
			DOT93_tree = (Object)adaptor.create(DOT93);
			adaptor.addChild(root_0, DOT93_tree);

			prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1911); 
			prop_tree = (Object)adaptor.create(prop);
			adaptor.addChild(root_0, prop_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {		
			    try {
			        addToPropertyListHolder((prop!=null?prop.getText():null));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }				
			}			
					
			LEFTP94=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1917); 
			LEFTP94_tree = (Object)adaptor.create(LEFTP94);
			adaptor.addChild(root_0, LEFTP94_tree);

			pushFollow(FOLLOW_expr_in_listOfDotValues1921);
			v1=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, v1.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
			    try {
			        addToPropertyValuesHolder((prop!=null?prop.getText():null), (v1!=null?((EugeneParser.expr_return)v1).p:null), (v1!=null?((EugeneParser.expr_return)v1).index:0));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }				
			}				
						
			RIGHTP95=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1929); 
			RIGHTP95_tree = (Object)adaptor.create(RIGHTP95);
			adaptor.addChild(root_0, RIGHTP95_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1180:13: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
			loop34:
			while (true) {
				int alt34=2;
				int LA34_0 = input.LA(1);
				if ( (LA34_0==COMMA) ) {
					alt34=1;
				}

				switch (alt34) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1180:14: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
					{
					COMMA96=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues1932); 
					COMMA96_tree = (Object)adaptor.create(COMMA96);
					adaptor.addChild(root_0, COMMA96_tree);

					DOT97=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1934); 
					DOT97_tree = (Object)adaptor.create(DOT97);
					adaptor.addChild(root_0, DOT97_tree);

					p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1938); 
					p_tree = (Object)adaptor.create(p);
					adaptor.addChild(root_0, p_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
					    try {
					        addToPropertyListHolder((p!=null?p.getText():null));
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }				
					}				
									
					LEFTP98=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1946); 
					LEFTP98_tree = (Object)adaptor.create(LEFTP98);
					adaptor.addChild(root_0, LEFTP98_tree);

					pushFollow(FOLLOW_expr_in_listOfDotValues1950);
					v2=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, v2.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
					    try {
					        addToPropertyValuesHolder((p!=null?p.getText():null), (v2!=null?((EugeneParser.expr_return)v2).p:null), (v2!=null?((EugeneParser.expr_return)v2).index:0));
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }				
					}				
										
					RIGHTP99=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1960); 
					RIGHTP99_tree = (Object)adaptor.create(RIGHTP99);
					adaptor.addChild(root_0, RIGHTP99_tree);

					}
					break;

				default :
					break loop34;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "listOfDotValues"


	public static class listOfValues_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "listOfValues"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1201:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
	public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
		EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA100=null;
		ParserRuleReturnScope val1 =null;
		ParserRuleReturnScope val2 =null;

		Object COMMA100_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1202:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1203:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
			{
			root_0 = (Object)adaptor.nil();



			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	

			    List<Property> propertyList = pt.getProperties();            					
			    if(propertyList.size() < 1) {
			        printError("Invalid number of property values.");
			    }
			    try {	
			        NamedElement ne = this.interp.get(propertyList.get(propertyValuesHolder.size()).getName());
			        if(null != ne && ne instanceof Property) {
			            if (((Property)ne).getType().equals(EugeneConstants.TXTLIST)) {
			                typeList = EugeneConstants.TXT;
			            } else if (((Property)ne).getType().equals(EugeneConstants.NUMLIST)) {
			                typeList = EugeneConstants.NUM;
			            }
			        }
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }				
			}
					
			pushFollow(FOLLOW_expr_in_listOfValues1981);
			val1=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, val1.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    propertyValuesHolder.add((val1!=null?((EugeneParser.expr_return)val1).p:null));
			}				
						
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1227:6: ( COMMA val2= expr[defer] )*
			loop35:
			while (true) {
				int alt35=2;
				int LA35_0 = input.LA(1);
				if ( (LA35_0==COMMA) ) {
					alt35=1;
				}

				switch (alt35) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1227:7: COMMA val2= expr[defer]
					{
					COMMA100=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues1987); 
					COMMA100_tree = (Object)adaptor.create(COMMA100);
					adaptor.addChild(root_0, COMMA100_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	

					    List<Property> propertyList = pt.getProperties();            					
					    if(propertyList.size() <= propertyValuesHolder.size()) {
					        printError("Invalid number of property values.");
					    }
					    
					    try {
					        NamedElement ne = this.interp.get(propertyList.get(propertyValuesHolder.size()).getName());
					             					
					        if(null != ne && ne instanceof Property) {
					            if (((Property)ne).getType().equals(EugeneConstants.TXTLIST)) {
					                typeList = EugeneConstants.TXT;
					            } else if (((Property)ne).getType().equals(EugeneConstants.NUMLIST)) {
					                typeList = EugeneConstants.NUM;
					            }
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }				

					}
					               
					pushFollow(FOLLOW_expr_in_listOfValues1993);
					val2=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, val2.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    propertyValuesHolder.add((val2!=null?((EugeneParser.expr_return)val2).p:null));
					}				
							
					}
					break;

				default :
					break loop35;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "listOfValues"


	public static class deviceDeclaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "deviceDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1257:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
	public final EugeneParser.deviceDeclaration_return deviceDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.deviceDeclaration_return retval = new EugeneParser.deviceDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token DEVICE101=null;
		Token LEFTP102=null;
		Token RIGHTP103=null;
		ParserRuleReturnScope dcs =null;

		Object n_tree=null;
		Object DEVICE101_tree=null;
		Object LEFTP102_tree=null;
		Object RIGHTP103_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1258:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1258:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			DEVICE101=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration2012); 
			DEVICE101_tree = (Object)adaptor.create(DEVICE101);
			adaptor.addChild(root_0, DEVICE101_tree);

			n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration2016); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1258:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0==LEFTP) ) {
				alt37=1;
			}
			switch (alt37) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1258:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
					{
					LEFTP102=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration2019); 
					LEFTP102_tree = (Object)adaptor.create(LEFTP102);
					adaptor.addChild(root_0, LEFTP102_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1258:23: (dcs= deviceComponents[defer] )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==ID||LA36_0==LEFTSBR||LA36_0==MINUS||LA36_0==PLUS) ) {
						alt36=1;
					}
					switch (alt36) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1258:24: dcs= deviceComponents[defer]
							{
							pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration2024);
							dcs=deviceComponents(defer);
							state._fsp--;

							adaptor.addChild(root_0, dcs.getTree());

							}
							break;

					}

					RIGHTP103=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration2029); 
					RIGHTP103_tree = (Object)adaptor.create(RIGHTP103);
					adaptor.addChild(root_0, RIGHTP103_tree);

					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        interp.createDevice(
			            (n!=null?n.getText():null), 
			            (dcs!=null?((EugeneParser.deviceComponents_return)dcs).lstComponents:null),
			            (dcs!=null?((EugeneParser.deviceComponents_return)dcs).lstOrientations:null));
			    } catch(Exception e) {
			        printError(e.getMessage());
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "deviceDeclaration"


	public static class deviceComponents_return extends ParserRuleReturnScope {
		public List<List<NamedElement>> lstComponents;
		public List<List<Orientation>> lstOrientations;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "deviceComponents"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1272:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
	public final EugeneParser.deviceComponents_return deviceComponents(boolean defer) throws RecognitionException {
		EugeneParser.deviceComponents_return retval = new EugeneParser.deviceComponents_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal104=null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope dcs =null;

		Object char_literal104_tree=null;


		retval.lstComponents = new ArrayList<List<NamedElement>>();
		retval.lstOrientations = new ArrayList<List<Orientation>>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1278:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1278:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_selection_in_deviceComponents2060);
			s=selection(defer);
			state._fsp--;

			adaptor.addChild(root_0, s.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.lstComponents.add((s!=null?((EugeneParser.selection_return)s).components:null));
			    retval.lstOrientations.add((s!=null?((EugeneParser.selection_return)s).orientations:null));
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1283:4: ( ',' dcs= deviceComponents[defer] )?
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==COMMA) ) {
				alt38=1;
			}
			switch (alt38) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1283:5: ',' dcs= deviceComponents[defer]
					{
					char_literal104=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents2066); 
					char_literal104_tree = (Object)adaptor.create(char_literal104);
					adaptor.addChild(root_0, char_literal104_tree);

					pushFollow(FOLLOW_deviceComponents_in_deviceComponents2070);
					dcs=deviceComponents(defer);
					state._fsp--;

					adaptor.addChild(root_0, dcs.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.lstComponents.addAll((dcs!=null?((EugeneParser.deviceComponents_return)dcs).lstComponents:null));
					    retval.lstOrientations.addAll((dcs!=null?((EugeneParser.deviceComponents_return)dcs).lstOrientations:null));
					}	
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "deviceComponents"


	public static class selection_return extends ParserRuleReturnScope {
		public List<NamedElement> components;
		public List<Orientation> orientations;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "selection"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1291:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
	public final EugeneParser.selection_return selection(boolean defer) throws RecognitionException {
		EugeneParser.selection_return retval = new EugeneParser.selection_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LEFTSBR105=null;
		Token RIGHTSBR106=null;
		ParserRuleReturnScope sl =null;
		ParserRuleReturnScope dc =null;

		Object LEFTSBR105_tree=null;
		Object RIGHTSBR106_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1293:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
			int alt39=2;
			int LA39_0 = input.LA(1);
			if ( (LA39_0==LEFTSBR) ) {
				alt39=1;
			}
			else if ( (LA39_0==ID||LA39_0==MINUS||LA39_0==PLUS) ) {
				alt39=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 39, 0, input);
				throw nvae;
			}

			switch (alt39) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1293:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					LEFTSBR105=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection2094); 
					LEFTSBR105_tree = (Object)adaptor.create(LEFTSBR105);
					adaptor.addChild(root_0, LEFTSBR105_tree);

					pushFollow(FOLLOW_selection_list_in_selection2098);
					sl=selection_list(defer);
					state._fsp--;

					adaptor.addChild(root_0, sl.getTree());

					RIGHTSBR106=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection2101); 
					RIGHTSBR106_tree = (Object)adaptor.create(RIGHTSBR106);
					adaptor.addChild(root_0, RIGHTSBR106_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.components = (sl!=null?((EugeneParser.selection_list_return)sl).components:null);
					    retval.orientations = (sl!=null?((EugeneParser.selection_list_return)sl).orientations:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1299:4: dc= device_component[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_device_component_in_selection2110);
					dc=device_component(defer);
					state._fsp--;

					adaptor.addChild(root_0, dc.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.components = new ArrayList<NamedElement>(1);
					    retval.components.add((dc!=null?((EugeneParser.device_component_return)dc).component:null));
					    
					    retval.orientations = new ArrayList<Orientation>();
					    retval.orientations.add((dc!=null?((EugeneParser.device_component_return)dc).orientation:null));
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selection"


	public static class selection_list_return extends ParserRuleReturnScope {
		public List<NamedElement> components;
		public List<Orientation> orientations;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "selection_list"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1310:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
	public final EugeneParser.selection_list_return selection_list(boolean defer) throws RecognitionException {
		EugeneParser.selection_list_return retval = new EugeneParser.selection_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PIPE107=null;
		ParserRuleReturnScope dc =null;
		ParserRuleReturnScope sl =null;

		Object PIPE107_tree=null;


		retval.components = new ArrayList<NamedElement>();
		retval.orientations = new ArrayList<Orientation>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1316:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1316:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_device_component_in_selection_list2139);
			dc=device_component(defer);
			state._fsp--;

			adaptor.addChild(root_0, dc.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.components.add((dc!=null?((EugeneParser.device_component_return)dc).component:null));
			    retval.orientations.add((dc!=null?((EugeneParser.device_component_return)dc).orientation:null));
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:4: ( PIPE sl= selection_list[defer] )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==PIPE) ) {
				alt40=1;
			}
			switch (alt40) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:5: PIPE sl= selection_list[defer]
					{
					PIPE107=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list2145); 
					PIPE107_tree = (Object)adaptor.create(PIPE107);
					adaptor.addChild(root_0, PIPE107_tree);

					pushFollow(FOLLOW_selection_list_in_selection_list2149);
					sl=selection_list(defer);
					state._fsp--;

					adaptor.addChild(root_0, sl.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.components.addAll((sl!=null?((EugeneParser.selection_list_return)sl).components:null));
					    retval.orientations.addAll((sl!=null?((EugeneParser.selection_list_return)sl).orientations:null));
					}	
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selection_list"


	public static class device_component_return extends ParserRuleReturnScope {
		public NamedElement component;
		public Orientation orientation;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "device_component"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1329:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
	public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
		EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token directionToken=null;
		Token idToken=null;

		Object directionToken_tree=null;
		Object idToken_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1331:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1331:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1331:4: (directionToken= ( MINUS | PLUS ) )?
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( (LA41_0==MINUS||LA41_0==PLUS) ) {
				alt41=1;
			}
			switch (alt41) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1331:5: directionToken= ( MINUS | PLUS )
					{
					directionToken=input.LT(1);
					if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(directionToken));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

			}

			idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component2185); 
			idToken_tree = (Object)adaptor.create(idToken);
			adaptor.addChild(root_0, idToken_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        NamedElement ne = interp.get((idToken!=null?idToken.getText():null));
			        if(null == ne) {
			           printError((idToken!=null?idToken.getText():null)+" is not declared.");
			        }
			    
			        if(ne instanceof Component) { 
			            retval.component = (Component)ne;
			        } else if(ne instanceof ComponentType) {
			            retval.component = (ComponentType)ne;        
			        } else {
			            printError((idToken!=null?idToken.getText():null)+" is neither a Device, Part, nor Part Type.");
			        }

			        if(directionToken != null) {        	        
			            if("-".equals((directionToken!=null?directionToken.getText():null))) {            
			                retval.orientation = Orientation.REVERSE;
			            } else {
			                retval.orientation = Orientation.FORWARD;
			            }
			        } else {
			            retval.orientation = Orientation.UNDEFINED;
			        }
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }				
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "device_component"


	public static class assignment_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1379:1: assignment[boolean defer] : lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
	public final EugeneParser.assignment_return assignment(boolean defer) throws RecognitionException {
		EugeneParser.assignment_return retval = new EugeneParser.assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token a=null;
		Token EQUALS108=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope rhs =null;

		Object a_tree=null;
		Object EQUALS108_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1380:2: (lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1380:4: lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_lhs_assignment_in_assignment2205);
			lhs=lhs_assignment(defer);
			state._fsp--;

			adaptor.addChild(root_0, lhs.getTree());

			EQUALS108=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment2208); 
			EQUALS108_tree = (Object)adaptor.create(EQUALS108);
			adaptor.addChild(root_0, EQUALS108_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1380:37: (a= AMP )?
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0==AMP) ) {
				alt42=1;
			}
			switch (alt42) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1380:38: a= AMP
					{
					a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment2213); 
					a_tree = (Object)adaptor.create(a);
					adaptor.addChild(root_0, a_tree);

					}
					break;

			}

			pushFollow(FOLLOW_rhs_assignment_in_assignment2219);
			rhs=rhs_assignment(defer);
			state._fsp--;

			adaptor.addChild(root_0, rhs.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {

			        /* --- NEW VERSION: Updated Oct 15th, 2014 
			               by Ernst Oberortner
			               
			           in the new version, we do the parsing of the 
			           LHS in the interpreter.
			           this influences the efficiency though (2x parsing and non-recursive interpretation).
			           however, it works better, more stable, and is easier to understand and improve.
			           
			           To my offspring: Please improve!

			           In general, the Eugene interpreter needs an Abstract Syntax Tree (AST)
			           Example: 
			           this.interp.assignment((lhs!=null?((Object)lhs.getTree()):null), (rhs!=null?((EugeneParser.rhs_assignment_return)rhs).e:null));
			         */
			        this.interp.assignment(
			                        (lhs!=null?input.toString(lhs.start,lhs.stop):null), 
			                        (rhs!=null?((EugeneParser.rhs_assignment_return)rhs).e:null));
			        
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());    
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assignment"


	public static class lhs_assignment_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "lhs_assignment"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1409:1: lhs_assignment[boolean defer] : ID lhs_access[defer] ;
	public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer) throws RecognitionException {
		EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID109=null;
		ParserRuleReturnScope lhs_access110 =null;

		Object ID109_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1410:2: ( ID lhs_access[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1410:4: ID lhs_access[defer]
			{
			root_0 = (Object)adaptor.nil();


			ID109=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment2234); 
			ID109_tree = (Object)adaptor.create(ID109);
			adaptor.addChild(root_0, ID109_tree);

			pushFollow(FOLLOW_lhs_access_in_lhs_assignment2236);
			lhs_access110=lhs_access(defer);
			state._fsp--;

			adaptor.addChild(root_0, lhs_access110.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lhs_assignment"


	public static class lhs_access_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "lhs_access"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1413:1: lhs_access[boolean defer] : (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] );
	public final EugeneParser.lhs_access_return lhs_access(boolean defer) throws RecognitionException {
		EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token DOT111=null;
		Token LEFTSBR112=null;
		Token set113=null;
		Token RIGHTSBR114=null;
		ParserRuleReturnScope lhs_access115 =null;

		Object i_tree=null;
		Object DOT111_tree=null;
		Object LEFTSBR112_tree=null;
		Object set113_tree=null;
		Object RIGHTSBR114_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1414:2: (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] )
			int alt44=2;
			int LA44_0 = input.LA(1);
			if ( (LA44_0==EQUALS) ) {
				alt44=1;
			}
			else if ( (LA44_0==DOT||LA44_0==LEFTSBR) ) {
				alt44=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}

			switch (alt44) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1415:2: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1415:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer]
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1415:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR )
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==DOT) ) {
						alt43=1;
					}
					else if ( (LA43_0==LEFTSBR) ) {
						alt43=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 43, 0, input);
						throw nvae;
					}

					switch (alt43) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1415:5: DOT i= ID
							{
							DOT111=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2256); 
							DOT111_tree = (Object)adaptor.create(DOT111);
							adaptor.addChild(root_0, DOT111_tree);

							i=(Token)match(input,ID,FOLLOW_ID_in_lhs_access2260); 
							i_tree = (Object)adaptor.create(i);
							adaptor.addChild(root_0, i_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1415:16: LEFTSBR ( ID | NUMBER ) RIGHTSBR
							{
							LEFTSBR112=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2264); 
							LEFTSBR112_tree = (Object)adaptor.create(LEFTSBR112);
							adaptor.addChild(root_0, LEFTSBR112_tree);

							set113=input.LT(1);
							if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
								input.consume();
								adaptor.addChild(root_0, (Object)adaptor.create(set113));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							RIGHTSBR114=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2272); 
							RIGHTSBR114_tree = (Object)adaptor.create(RIGHTSBR114);
							adaptor.addChild(root_0, RIGHTSBR114_tree);

							}
							break;

					}

					pushFollow(FOLLOW_lhs_access_in_lhs_access2275);
					lhs_access115=lhs_access(defer);
					state._fsp--;

					adaptor.addChild(root_0, lhs_access115.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lhs_access"


	public static class rhs_assignment_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "rhs_assignment"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1426:1: rhs_assignment[boolean defer] returns [NamedElement e] : (de= dataExchange[defer] |exp= expr[defer] );
	public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
		EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope de =null;
		ParserRuleReturnScope exp =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1428:2: (de= dataExchange[defer] |exp= expr[defer] )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==GENBANK||(LA45_0 >= IMPORT_LC && LA45_0 <= IMPORT_UC)||LA45_0==REGISTRY||LA45_0==SBOL) ) {
				alt45=1;
			}
			else if ( (LA45_0==DOLLAR||(LA45_0 >= EXIT_LC && LA45_0 <= EXIT_UC)||(LA45_0 >= FALSE_LC && LA45_0 <= FALSE_UC)||LA45_0==ID||(LA45_0 >= LEFTP && LA45_0 <= LEFTSBR)||LA45_0==MINUS||LA45_0==NUMBER||LA45_0==PERMUTE||LA45_0==PRODUCT||(LA45_0 >= RANDOM_LC && LA45_0 <= REAL)||(LA45_0 >= SAVE_LC && LA45_0 <= SAVE_UC)||(LA45_0 >= SIZEOF_LC && LA45_0 <= STORE_UC)||(LA45_0 >= STRING && LA45_0 <= TRUE_UC)) ) {
				alt45=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1428:4: de= dataExchange[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_dataExchange_in_rhs_assignment2302);
					de=dataExchange(defer);
					state._fsp--;

					adaptor.addChild(root_0, de.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (de!=null?((EugeneParser.dataExchange_return)de).e:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1433:4: exp= expr[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expr_in_rhs_assignment2312);
					exp=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, exp.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    if((exp!=null?((EugeneParser.expr_return)exp).element:null) != null) {
					        retval.e = (exp!=null?((EugeneParser.expr_return)exp).element:null);
					    } else {
					        retval.e = (exp!=null?((EugeneParser.expr_return)exp).p:null);
					    }
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "rhs_assignment"


	public static class listOfIDs_return extends ParserRuleReturnScope {
		public List<NamedElement> lstElements;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "listOfIDs"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1444:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
	public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
		EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token idToken=null;
		Token char_literal116=null;
		ParserRuleReturnScope lstToken =null;

		Object idToken_tree=null;
		Object char_literal116_tree=null;


		retval.lstElements =new ArrayList<NamedElement>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1449:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1449:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2340); 
			idToken_tree = (Object)adaptor.create(idToken);
			adaptor.addChild(root_0, idToken_tree);

			 
			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING)
			    try {
			        NamedElement ne = interp.get((idToken!=null?idToken.getText():null));
			        if(null == ne) {
			           printError((idToken!=null?idToken.getText():null)+" is not declared.");
			        }
			        retval.lstElements.add(ne);
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1461:4: ( ',' lstToken= listOfIDs[defer] )?
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0==COMMA) ) {
				alt46=1;
			}
			switch (alt46) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1461:5: ',' lstToken= listOfIDs[defer]
					{
					char_literal116=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2349); 
					char_literal116_tree = (Object)adaptor.create(char_literal116);
					adaptor.addChild(root_0, char_literal116_tree);

					pushFollow(FOLLOW_listOfIDs_in_listOfIDs2353);
					lstToken=listOfIDs(defer);
					state._fsp--;

					adaptor.addChild(root_0, lstToken.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING){
					    retval.lstElements.addAll((lstToken!=null?((EugeneParser.listOfIDs_return)lstToken).lstElements:null));
					}
					        
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "listOfIDs"


	public static class ruleDeclaration_return extends ParserRuleReturnScope {
		public Rule rule;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ruleDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1468:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
	public final EugeneParser.ruleDeclaration_return ruleDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.ruleDeclaration_return retval = new EugeneParser.ruleDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token device=null;
		Token RULE117=null;
		Token LEFTP118=null;
		Token set119=null;
		Token COLON120=null;
		Token RIGHTP121=null;
		ParserRuleReturnScope cnf =null;

		Object name_tree=null;
		Object device_tree=null;
		Object RULE117_tree=null;
		Object LEFTP118_tree=null;
		Object set119_tree=null;
		Object COLON120_tree=null;
		Object RIGHTP121_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			RULE117=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2377); 
			RULE117_tree = (Object)adaptor.create(RULE117);
			adaptor.addChild(root_0, RULE117_tree);

			name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2381); 
			name_tree = (Object)adaptor.create(name);
			adaptor.addChild(root_0, name_tree);

			LEFTP118=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2383); 
			LEFTP118_tree = (Object)adaptor.create(LEFTP118);
			adaptor.addChild(root_0, LEFTP118_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
			{
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==LC_ON||LA47_0==UC_ON) ) {
				alt47=1;
			}
			switch (alt47) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:26: ( LC_ON | UC_ON ) device= ID COLON
					{
					set119=input.LT(1);
					if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set119));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2396); 
					device_tree = (Object)adaptor.create(device);
					adaptor.addChild(root_0, device_tree);

					COLON120=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2398); 
					COLON120_tree = (Object)adaptor.create(COLON120);
					adaptor.addChild(root_0, COLON120_tree);

					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        if(null == device) {
			            retval.rule = interp.createRule((name!=null?name.getText():null), null);
			        } else {
			            retval.rule = interp.createRule((name!=null?name.getText():null), (device!=null?device.getText():null));
			        }
			    } catch(Exception e) {
			        printError(e.getMessage());
			    }
			}		
				
			pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2406);
			cnf=cnf_rule(defer);
			state._fsp--;

			adaptor.addChild(root_0, cnf.getTree());

			}

			RIGHTP121=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2411); 
			RIGHTP121_tree = (Object)adaptor.create(RIGHTP121);
			adaptor.addChild(root_0, RIGHTP121_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.rule.setLogicalAnd((cnf!=null?((EugeneParser.cnf_rule_return)cnf).lAnd:null));

			    /*
			     *  ONLY FOR TESTING
			     */    
			//    this.interp.executeRule(retval.rule); 
			}
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ruleDeclaration"


	public static class ruleOperator_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ruleOperator"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1494:1: ruleOperator[boolean defer] : ruleOperators ;
	public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
		EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope ruleOperators122 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1495:2: ( ruleOperators )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1495:4: ruleOperators
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_ruleOperators_in_ruleOperator2425);
			ruleOperators122=ruleOperators();
			state._fsp--;

			adaptor.addChild(root_0, ruleOperators122.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ruleOperator"


	public static class ruleOperators_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ruleOperators"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1499:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
	public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
		EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set123=null;

		Object set123_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1500:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
			{
			root_0 = (Object)adaptor.nil();


			set123=input.LT(1);
			if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 123 && input.LA(1) <= 157)||(input.LA(1) >= 159 && input.LA(1) <= 196)||(input.LA(1) >= 198 && input.LA(1) <= 200) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set123));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ruleOperators"


	public static class relationalOperators_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "relationalOperators"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1543:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
	public final EugeneParser.relationalOperators_return relationalOperators() throws RecognitionException {
		EugeneParser.relationalOperators_return retval = new EugeneParser.relationalOperators_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EQUALS124=null;
		Token EQUALS125=null;
		Token NEQUAL126=null;
		Token LTHAN127=null;
		Token GTHAN128=null;
		Token LEQUAL129=null;
		Token GEQUAL130=null;
		Token set131=null;
		Token set132=null;
		Token set133=null;
		Token set134=null;
		Token set135=null;
		Token set136=null;
		Token set137=null;
		Token set138=null;
		Token set139=null;

		Object EQUALS124_tree=null;
		Object EQUALS125_tree=null;
		Object NEQUAL126_tree=null;
		Object LTHAN127_tree=null;
		Object GTHAN128_tree=null;
		Object LEQUAL129_tree=null;
		Object GEQUAL130_tree=null;
		Object set131_tree=null;
		Object set132_tree=null;
		Object set133_tree=null;
		Object set134_tree=null;
		Object set135_tree=null;
		Object set136_tree=null;
		Object set137_tree=null;
		Object set138_tree=null;
		Object set139_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1544:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
			int alt48=15;
			switch ( input.LA(1) ) {
			case EQUALS:
				{
				alt48=1;
				}
				break;
			case NEQUAL:
				{
				alt48=2;
				}
				break;
			case LTHAN:
				{
				alt48=3;
				}
				break;
			case GTHAN:
				{
				alt48=4;
				}
				break;
			case LEQUAL:
				{
				alt48=5;
				}
				break;
			case GEQUAL:
				{
				alt48=6;
				}
				break;
			case 133:
			case 172:
				{
				alt48=7;
				}
				break;
			case 142:
			case 181:
				{
				alt48=8;
				}
				break;
			case 139:
			case 178:
				{
				alt48=9;
				}
				break;
			case 145:
			case 184:
				{
				alt48=10;
				}
				break;
			case 159:
			case 198:
				{
				alt48=11;
				}
				break;
			case 135:
			case 174:
				{
				alt48=12;
				}
				break;
			case 136:
			case 175:
				{
				alt48=13;
				}
				break;
			case 143:
			case 182:
				{
				alt48=14;
				}
				break;
			case 158:
			case 197:
				{
				alt48=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}
			switch (alt48) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1544:4: EQUALS EQUALS
					{
					root_0 = (Object)adaptor.nil();


					EQUALS124=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2804); 
					EQUALS124_tree = (Object)adaptor.create(EQUALS124);
					adaptor.addChild(root_0, EQUALS124_tree);

					EQUALS125=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2806); 
					EQUALS125_tree = (Object)adaptor.create(EQUALS125);
					adaptor.addChild(root_0, EQUALS125_tree);

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1545:4: NEQUAL
					{
					root_0 = (Object)adaptor.nil();


					NEQUAL126=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators2811); 
					NEQUAL126_tree = (Object)adaptor.create(NEQUAL126);
					adaptor.addChild(root_0, NEQUAL126_tree);

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1546:4: LTHAN
					{
					root_0 = (Object)adaptor.nil();


					LTHAN127=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators2816); 
					LTHAN127_tree = (Object)adaptor.create(LTHAN127);
					adaptor.addChild(root_0, LTHAN127_tree);

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1547:4: GTHAN
					{
					root_0 = (Object)adaptor.nil();


					GTHAN128=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators2821); 
					GTHAN128_tree = (Object)adaptor.create(GTHAN128);
					adaptor.addChild(root_0, GTHAN128_tree);

					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1548:4: LEQUAL
					{
					root_0 = (Object)adaptor.nil();


					LEQUAL129=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators2826); 
					LEQUAL129_tree = (Object)adaptor.create(LEQUAL129);
					adaptor.addChild(root_0, LEQUAL129_tree);

					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1549:4: GEQUAL
					{
					root_0 = (Object)adaptor.nil();


					GEQUAL130=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators2831); 
					GEQUAL130_tree = (Object)adaptor.create(GEQUAL130);
					adaptor.addChild(root_0, GEQUAL130_tree);

					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1550:4: ( 'CONTAINS' | 'contains' )
					{
					root_0 = (Object)adaptor.nil();


					set131=input.LT(1);
					if ( input.LA(1)==133||input.LA(1)==172 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set131));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1551:4: ( 'NOTCONTAINS' | 'notcontains' )
					{
					root_0 = (Object)adaptor.nil();


					set132=input.LT(1);
					if ( input.LA(1)==142||input.LA(1)==181 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set132));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1552:4: ( 'MATCHES' | 'matches' )
					{
					root_0 = (Object)adaptor.nil();


					set133=input.LT(1);
					if ( input.LA(1)==139||input.LA(1)==178 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set133));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 10 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1553:4: ( 'NOTMATCHES' | 'notmatches' )
					{
					root_0 = (Object)adaptor.nil();


					set134=input.LT(1);
					if ( input.LA(1)==145||input.LA(1)==184 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set134));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 11 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1554:4: ( 'STARTSWITH' | 'startswith' )
					{
					root_0 = (Object)adaptor.nil();


					set135=input.LT(1);
					if ( input.LA(1)==159||input.LA(1)==198 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set135));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 12 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1555:4: ( 'ENDSWITH' | 'endswith' )
					{
					root_0 = (Object)adaptor.nil();


					set136=input.LT(1);
					if ( input.LA(1)==135||input.LA(1)==174 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set136));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 13 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1556:4: ( 'EQUALS' | 'equals' )
					{
					root_0 = (Object)adaptor.nil();


					set137=input.LT(1);
					if ( input.LA(1)==136||input.LA(1)==175 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set137));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 14 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1557:4: ( 'NOTEQUALS' | 'notequals' )
					{
					root_0 = (Object)adaptor.nil();


					set138=input.LT(1);
					if ( input.LA(1)==143||input.LA(1)==182 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set138));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 15 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1558:4: ( 'SOUNDSLIKE' | 'soundslike' )
					{
					root_0 = (Object)adaptor.nil();


					set139=input.LT(1);
					if ( input.LA(1)==158||input.LA(1)==197 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set139));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relationalOperators"


	public static class cnf_rule_return extends ParserRuleReturnScope {
		public LogicalAnd lAnd;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cnf_rule"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1561:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
	public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
		EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set140=null;
		ParserRuleReturnScope c =null;
		ParserRuleReturnScope cnf =null;

		Object set140_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1563:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1563:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1563:4: (c= or_predicate[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1563:5: c= or_predicate[defer]
			{
			pushFollow(FOLLOW_or_predicate_in_cnf_rule2932);
			c=or_predicate(defer);
			state._fsp--;

			adaptor.addChild(root_0, c.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    if(null == retval.lAnd) {
			        retval.lAnd = new LogicalAnd();
			    }
			    
			    retval.lAnd.getPredicates().add((c!=null?((EugeneParser.or_predicate_return)c).p:null));
			}	
				
			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1571:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
			int alt49=2;
			int LA49_0 = input.LA(1);
			if ( (LA49_0==LC_AND||LA49_0==LOG_AND||LA49_0==UC_AND) ) {
				alt49=1;
			}
			switch (alt49) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1571:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
					{
					set140=input.LT(1);
					if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set140));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_cnf_rule_in_cnf_rule2950);
					cnf=cnf_rule(defer);
					state._fsp--;

					adaptor.addChild(root_0, cnf.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.lAnd.union((cnf!=null?((EugeneParser.cnf_rule_return)cnf).lAnd:null));
					}	
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "cnf_rule"


	public static class or_predicate_return extends ParserRuleReturnScope {
		public Predicate p;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "or_predicate"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1578:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
	public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
		EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set141=null;
		ParserRuleReturnScope n1 =null;
		ParserRuleReturnScope n2 =null;

		Object set141_tree=null;


		LogicalOr lor = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1583:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1583:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_negated_predicate_in_or_predicate2980);
			n1=negated_predicate(defer);
			state._fsp--;

			adaptor.addChild(root_0, n1.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.p = (n1!=null?((EugeneParser.negated_predicate_return)n1).p:null);
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1587:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
			loop50:
			while (true) {
				int alt50=2;
				int LA50_0 = input.LA(1);
				if ( (LA50_0==LC_OR||LA50_0==LOG_OR||LA50_0==UC_OR) ) {
					alt50=1;
				}

				switch (alt50) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1587:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
					{
					set141=input.LT(1);
					if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set141));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_negated_predicate_in_or_predicate2996);
					n2=negated_predicate(defer);
					state._fsp--;

					adaptor.addChild(root_0, n2.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(null == lor) {
					            lor = this.interp.logicalOr((n1!=null?((EugeneParser.negated_predicate_return)n1).p:null), (n2!=null?((EugeneParser.negated_predicate_return)n2).p:null));
					        } else {
					            lor = this.interp.logicalOr(lor, (n2!=null?((EugeneParser.negated_predicate_return)n2).p:null)); 
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }
					}	
						
					}
					break;

				default :
					break loop50;
				}
			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    if(null != lor) {
			        retval.p = lor;
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "or_predicate"


	public static class negated_predicate_return extends ParserRuleReturnScope {
		public Predicate p;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "negated_predicate"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1608:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
	public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
		EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set142=null;
		ParserRuleReturnScope c =null;

		Object set142_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1610:2: ( ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1610:4: ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] )
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1610:4: ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] )
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==LC_NOT||LA51_0==OP_NOT||LA51_0==UC_NOT) ) {
				alt51=1;
			}
			else if ( (LA51_0==ID||LA51_0==LC_INDUCES||LA51_0==LC_REPRESSES||(LA51_0 >= LEFTP && LA51_0 <= LEFTSBR)||LA51_0==MINUS||LA51_0==NUMBER||LA51_0==REAL||LA51_0==STRING||LA51_0==UC_INDUCES||LA51_0==UC_REPRESSES||(LA51_0 >= 123 && LA51_0 <= 157)||(LA51_0 >= 159 && LA51_0 <= 196)||(LA51_0 >= 198 && LA51_0 <= 200)) ) {
				alt51=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 51, 0, input);
				throw nvae;
			}

			switch (alt51) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1610:5: ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer]
					{
					set142=input.LT(1);
					if ( input.LA(1)==LC_NOT||input.LA(1)==OP_NOT||input.LA(1)==UC_NOT ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set142));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_predicate_in_negated_predicate3034);
					c=predicate(defer);
					state._fsp--;

					adaptor.addChild(root_0, c.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.p = this.interp.negate((c!=null?((EugeneParser.predicate_return)c).p:null));
					    } catch(Exception e) {
					        printError(e.getMessage());
					    }
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1619:4: c= predicate[defer]
					{
					pushFollow(FOLLOW_predicate_in_negated_predicate3044);
					c=predicate(defer);
					state._fsp--;

					adaptor.addChild(root_0, c.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.p = (c!=null?((EugeneParser.predicate_return)c).p:null);
					}	
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "negated_predicate"


	public static class predicate_return extends ParserRuleReturnScope {
		public Predicate p;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "predicate"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1626:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
	public final EugeneParser.predicate_return predicate(boolean defer) throws RecognitionException {
		EugeneParser.predicate_return retval = new EugeneParser.predicate_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope op =null;
		ParserRuleReturnScope rhs =null;
		ParserRuleReturnScope exp =null;

		Object i_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1628:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
			int alt54=3;
			switch ( input.LA(1) ) {
			case ID:
				{
				switch ( input.LA(2) ) {
				case DIV:
				case DOT:
				case EQUALS:
				case GEQUAL:
				case GTHAN:
				case LEFTSBR:
				case LEQUAL:
				case LTHAN:
				case MINUS:
				case MULT:
				case NEQUAL:
				case PLUS:
				case 158:
				case 197:
					{
					alt54=3;
					}
					break;
				case 133:
				case 172:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case LC_AND:
				case LC_OR:
				case LOG_AND:
				case LOG_OR:
				case RIGHTP:
				case UC_AND:
				case UC_OR:
					{
					alt54=2;
					}
					break;
				case 142:
				case 181:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 7, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 139:
				case 178:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 145:
				case 184:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 159:
				case 198:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 10, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 135:
				case 174:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 11, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 136:
				case 175:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 12, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 143:
				case 182:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 13, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case LC_INDUCES:
				case LC_REPRESSES:
				case UC_INDUCES:
				case UC_REPRESSES:
				case 123:
				case 124:
				case 125:
				case 126:
				case 127:
				case 128:
				case 129:
				case 130:
				case 131:
				case 132:
				case 134:
				case 137:
				case 138:
				case 140:
				case 141:
				case 144:
				case 146:
				case 147:
				case 148:
				case 149:
				case 150:
				case 151:
				case 152:
				case 153:
				case 154:
				case 155:
				case 156:
				case 157:
				case 160:
				case 161:
				case 162:
				case 163:
				case 164:
				case 165:
				case 166:
				case 167:
				case 168:
				case 169:
				case 170:
				case 171:
				case 173:
				case 176:
				case 177:
				case 179:
				case 180:
				case 183:
				case 185:
				case 186:
				case 187:
				case 188:
				case 189:
				case 190:
				case 191:
				case 192:
				case 193:
				case 194:
				case 195:
				case 196:
				case 199:
				case 200:
					{
					alt54=1;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 54, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case NUMBER:
				{
				switch ( input.LA(2) ) {
				case 133:
				case 172:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case DIV:
				case EQUALS:
				case GEQUAL:
				case GTHAN:
				case LEQUAL:
				case LTHAN:
				case MINUS:
				case MULT:
				case NEQUAL:
				case PLUS:
				case 158:
				case 197:
					{
					alt54=3;
					}
					break;
				case 142:
				case 181:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 7, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 139:
				case 178:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 145:
				case 184:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 159:
				case 198:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 10, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 135:
				case 174:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 11, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 136:
				case 175:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 12, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 143:
				case 182:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt54=1;
						}
						break;
					case LC_AND:
					case LC_OR:
					case LEFTSBR:
					case LOG_AND:
					case LOG_OR:
					case NUMBER:
					case RIGHTP:
					case UC_AND:
					case UC_OR:
						{
						alt54=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt54=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 54, 13, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case LC_INDUCES:
				case LC_REPRESSES:
				case UC_INDUCES:
				case UC_REPRESSES:
				case 123:
				case 124:
				case 125:
				case 126:
				case 127:
				case 128:
				case 129:
				case 130:
				case 131:
				case 132:
				case 134:
				case 137:
				case 138:
				case 140:
				case 141:
				case 144:
				case 146:
				case 147:
				case 148:
				case 149:
				case 150:
				case 151:
				case 152:
				case 153:
				case 154:
				case 155:
				case 156:
				case 157:
				case 160:
				case 161:
				case 162:
				case 163:
				case 164:
				case 165:
				case 166:
				case 167:
				case 168:
				case 169:
				case 170:
				case 171:
				case 173:
				case 176:
				case 177:
				case 179:
				case 180:
				case 183:
				case 185:
				case 186:
				case 187:
				case 188:
				case 189:
				case 190:
				case 191:
				case 192:
				case 193:
				case 194:
				case 195:
				case 196:
				case 199:
				case 200:
					{
					alt54=1;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 54, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case LC_INDUCES:
			case LC_REPRESSES:
			case LEFTSBR:
			case UC_INDUCES:
			case UC_REPRESSES:
			case 123:
			case 124:
			case 125:
			case 126:
			case 127:
			case 128:
			case 129:
			case 130:
			case 131:
			case 132:
			case 133:
			case 134:
			case 135:
			case 136:
			case 137:
			case 138:
			case 139:
			case 140:
			case 141:
			case 142:
			case 143:
			case 144:
			case 145:
			case 146:
			case 147:
			case 148:
			case 149:
			case 150:
			case 151:
			case 152:
			case 153:
			case 154:
			case 155:
			case 156:
			case 157:
			case 159:
			case 160:
			case 161:
			case 162:
			case 163:
			case 164:
			case 165:
			case 166:
			case 167:
			case 168:
			case 169:
			case 170:
			case 171:
			case 172:
			case 173:
			case 174:
			case 175:
			case 176:
			case 177:
			case 178:
			case 179:
			case 180:
			case 181:
			case 182:
			case 183:
			case 184:
			case 185:
			case 186:
			case 187:
			case 188:
			case 189:
			case 190:
			case 191:
			case 192:
			case 193:
			case 194:
			case 195:
			case 196:
			case 198:
			case 199:
			case 200:
				{
				alt54=1;
				}
				break;
			case LEFTP:
			case MINUS:
			case REAL:
			case STRING:
				{
				alt54=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 54, 0, input);
				throw nvae;
			}
			switch (alt54) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1628:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1628:4: (lhs= operand[defer] )?
					int alt52=2;
					int LA52_0 = input.LA(1);
					if ( (LA52_0==ID||LA52_0==LEFTSBR||LA52_0==NUMBER) ) {
						alt52=1;
					}
					switch (alt52) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1628:5: lhs= operand[defer]
							{
							pushFollow(FOLLOW_operand_in_predicate3071);
							lhs=operand(defer);
							state._fsp--;

							adaptor.addChild(root_0, lhs.getTree());


							addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
								
							}
							break;

					}

					pushFollow(FOLLOW_ruleOperator_in_predicate3081);
					op=ruleOperator(defer);
					state._fsp--;

					adaptor.addChild(root_0, op.getTree());


					addToken((op!=null?input.toString(op.start,op.stop):null));	
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1632:5: (rhs= operand[defer] )?
					int alt53=2;
					int LA53_0 = input.LA(1);
					if ( (LA53_0==ID||LA53_0==LEFTSBR||LA53_0==NUMBER) ) {
						alt53=1;
					}
					switch (alt53) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1632:6: rhs= operand[defer]
							{
							pushFollow(FOLLOW_operand_in_predicate3090);
							rhs=operand(defer);
							state._fsp--;

							adaptor.addChild(root_0, rhs.getTree());


							addToken((rhs!=null?input.toString(rhs.start,rhs.stop):null));	
								
							}
							break;

					}



					try {
					    retval.p = this.interp.createPredicate((lhs!=null?((EugeneParser.operand_return)lhs).o:null), (op!=null?input.toString(op.start,op.stop):null), (rhs!=null?((EugeneParser.operand_return)rhs).o:null));
					//    retval.p = this.interp.createPredicate(this.tokens);
					} catch(EugeneException ee) {
					    printError(ee.getMessage());
					}

					// reset the global token array
					this.tokens = null;
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1646:4: i= ID
					{
					root_0 = (Object)adaptor.nil();


					i=(Token)match(input,ID,FOLLOW_ID_in_predicate3104); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        NamedElement ne = this.interp.get((i!=null?i.getText():null));
					        if(null == ne) {
					            printError((i!=null?i.getText():null)+" is not defined.");
					        }
					        if(!(ne instanceof Rule)) {
					            printError((i!=null?i.getText():null)+" is not a Rule.");
					        }
					    
					        retval.p = ((Rule)ne);
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }
					}		
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1663:4: exp= expressionRule[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expressionRule_in_predicate3113);
					exp=expressionRule(defer);
					state._fsp--;

					adaptor.addChild(root_0, exp.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.p = (exp!=null?((EugeneParser.expressionRule_return)exp).p:null);
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "predicate"


	public static class operand_return extends ParserRuleReturnScope {
		public ArrangementOperand o;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "operand"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1670:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' ) ;
	public final EugeneParser.operand_return operand(boolean defer) throws RecognitionException {
		EugeneParser.operand_return retval = new EugeneParser.operand_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token n=null;
		Token char_literal143=null;
		Token char_literal144=null;

		Object i_tree=null;
		Object n_tree=null;
		Object char_literal143_tree=null;
		Object char_literal144_tree=null;


		NamedElement element = null;
		int constant = -1;
		int index = -1;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
			int alt55=3;
			switch ( input.LA(1) ) {
			case ID:
				{
				alt55=1;
				}
				break;
			case NUMBER:
				{
				alt55=2;
				}
				break;
			case LEFTSBR:
				{
				alt55=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 55, 0, input);
				throw nvae;
			}
			switch (alt55) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:5: i= ID
					{
					i=(Token)match(input,ID,FOLLOW_ID_in_operand3144); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    if(!this.interp.contains((i!=null?i.getText():null))) {
					        printError((i!=null?i.getText():null)+" not defined.");
					    }
					    try {
					        element = this.interp.get((i!=null?i.getText():null));
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }
					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1689:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3153); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    constant = Integer.parseInt((n!=null?n.getText():null));
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1694:4: '[' n= NUMBER ']'
					{
					char_literal143=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand3160); 
					char_literal143_tree = (Object)adaptor.create(char_literal143);
					adaptor.addChild(root_0, char_literal143_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3164); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);

					char_literal144=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand3166); 
					char_literal144_tree = (Object)adaptor.create(char_literal144);
					adaptor.addChild(root_0, char_literal144_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    index = Integer.parseInt((n!=null?n.getText():null));
					}	
						
					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        retval.o = new ArrangementOperand(element, constant, index);
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			}
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "operand"


	public static class expressionRule_return extends ParserRuleReturnScope {
		public Predicate p;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expressionRule"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1713:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
	public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
		EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope op =null;
		ParserRuleReturnScope rhs =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1715:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1715:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expression_in_expressionRule3192);
			lhs=expression(defer);
			state._fsp--;

			adaptor.addChild(root_0, lhs.getTree());

			pushFollow(FOLLOW_exp_op_in_expressionRule3197);
			op=exp_op(defer);
			state._fsp--;

			adaptor.addChild(root_0, op.getTree());

			pushFollow(FOLLOW_expression_in_expressionRule3202);
			rhs=expression(defer);
			state._fsp--;

			adaptor.addChild(root_0, rhs.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        retval.p = new ExpressionConstraint((lhs!=null?((EugeneParser.expression_return)lhs).exp:null), (op!=null?input.toString(op.start,op.stop):null), (rhs!=null?((EugeneParser.expression_return)rhs).exp:null));
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expressionRule"


	public static class expression_return extends ParserRuleReturnScope {
		public Expression exp;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1726:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
	public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
		EugeneParser.expression_return retval = new EugeneParser.expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LEFTP145=null;
		Token RIGHTP147=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope expop =null;
		ParserRuleReturnScope rhs =null;
		ParserRuleReturnScope expression146 =null;

		Object LEFTP145_tree=null;
		Object RIGHTP147_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1728:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
			int alt57=2;
			int LA57_0 = input.LA(1);
			if ( (LA57_0==ID||LA57_0==MINUS||LA57_0==NUMBER||LA57_0==REAL||LA57_0==STRING) ) {
				alt57=1;
			}
			else if ( (LA57_0==LEFTP) ) {
				alt57=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 57, 0, input);
				throw nvae;
			}

			switch (alt57) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1728:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_exp_operand_in_expression3226);
					lhs=exp_operand(defer);
					state._fsp--;

					adaptor.addChild(root_0, lhs.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.exp = new Expression((lhs!=null?((EugeneParser.exp_operand_return)lhs).eop:null), null, null);
					}
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1732:4: (expop= exp_operator[defer] rhs= expression[defer] )?
					int alt56=2;
					int LA56_0 = input.LA(1);
					if ( (LA56_0==DIV||LA56_0==MINUS||LA56_0==MULT||LA56_0==PLUS) ) {
						alt56=1;
					}
					switch (alt56) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1732:6: expop= exp_operator[defer] rhs= expression[defer]
							{
							pushFollow(FOLLOW_exp_operator_in_expression3235);
							expop=exp_operator(defer);
							state._fsp--;

							adaptor.addChild(root_0, expop.getTree());

							pushFollow(FOLLOW_expression_in_expression3240);
							rhs=expression(defer);
							state._fsp--;

							adaptor.addChild(root_0, rhs.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
							    retval.exp = new Expression((lhs!=null?((EugeneParser.exp_operand_return)lhs).eop:null), (expop!=null?((EugeneParser.exp_operator_return)expop).op:null), (rhs!=null?((EugeneParser.expression_return)rhs).exp:null));
							}	
								
							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1737:4: LEFTP expression[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					LEFTP145=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3252); 
					LEFTP145_tree = (Object)adaptor.create(LEFTP145);
					adaptor.addChild(root_0, LEFTP145_tree);

					pushFollow(FOLLOW_expression_in_expression3254);
					expression146=expression(defer);
					state._fsp--;

					adaptor.addChild(root_0, expression146.getTree());

					RIGHTP147=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3257); 
					RIGHTP147_tree = (Object)adaptor.create(RIGHTP147);
					adaptor.addChild(root_0, RIGHTP147_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    	
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class exp_operator_return extends ParserRuleReturnScope {
		public Expression.ExpOp op;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "exp_operator"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1744:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
	public final EugeneParser.exp_operator_return exp_operator(boolean defer) throws RecognitionException {
		EugeneParser.exp_operator_return retval = new EugeneParser.exp_operator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PLUS148=null;
		Token MINUS149=null;
		Token MULT150=null;
		Token DIV151=null;

		Object PLUS148_tree=null;
		Object MINUS149_tree=null;
		Object MULT150_tree=null;
		Object DIV151_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1746:2: ( PLUS | MINUS | MULT | DIV )
			int alt58=4;
			switch ( input.LA(1) ) {
			case PLUS:
				{
				alt58=1;
				}
				break;
			case MINUS:
				{
				alt58=2;
				}
				break;
			case MULT:
				{
				alt58=3;
				}
				break;
			case DIV:
				{
				alt58=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 58, 0, input);
				throw nvae;
			}
			switch (alt58) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1746:4: PLUS
					{
					root_0 = (Object)adaptor.nil();


					PLUS148=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3276); 
					PLUS148_tree = (Object)adaptor.create(PLUS148);
					adaptor.addChild(root_0, PLUS148_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
					    retval.op = Expression.ExpOp.PLUS;	
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1751:4: MINUS
					{
					root_0 = (Object)adaptor.nil();


					MINUS149=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3284); 
					MINUS149_tree = (Object)adaptor.create(MINUS149);
					adaptor.addChild(root_0, MINUS149_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
					    retval.op = Expression.ExpOp.MINUS;	
					}
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1756:4: MULT
					{
					root_0 = (Object)adaptor.nil();


					MULT150=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3291); 
					MULT150_tree = (Object)adaptor.create(MULT150);
					adaptor.addChild(root_0, MULT150_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
					    retval.op = Expression.ExpOp.MULT;	
					}
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1761:4: DIV
					{
					root_0 = (Object)adaptor.nil();


					DIV151=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3298); 
					DIV151_tree = (Object)adaptor.create(DIV151);
					adaptor.addChild(root_0, DIV151_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
					    retval.op = Expression.ExpOp.DIV;	
					}
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exp_operator"


	public static class exp_operand_return extends ParserRuleReturnScope {
		public ExpressionOperand eop;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "exp_operand"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1768:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
	public final EugeneParser.exp_operand_return exp_operand(boolean defer) throws RecognitionException {
		EugeneParser.exp_operand_return retval = new EugeneParser.exp_operand_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i1=null;
		Token i2=null;
		Token n=null;
		Token r=null;
		Token s=null;
		Token DOT152=null;
		Token LEFTSBR153=null;
		Token RIGHTSBR154=null;
		Token MINUS155=null;
		Token MINUS156=null;

		Object i1_tree=null;
		Object i2_tree=null;
		Object n_tree=null;
		Object r_tree=null;
		Object s_tree=null;
		Object DOT152_tree=null;
		Object LEFTSBR153_tree=null;
		Object RIGHTSBR154_tree=null;
		Object MINUS155_tree=null;
		Object MINUS156_tree=null;


		List<NamedElement> elements = null;
		NamedElement ne = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1774:2: ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
			int alt61=6;
			switch ( input.LA(1) ) {
			case ID:
				{
				alt61=1;
				}
				break;
			case NUMBER:
				{
				alt61=2;
				}
				break;
			case MINUS:
				{
				int LA61_3 = input.LA(2);
				if ( (LA61_3==NUMBER) ) {
					alt61=3;
				}
				else if ( (LA61_3==REAL) ) {
					alt61=5;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 61, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case REAL:
				{
				alt61=4;
				}
				break;
			case STRING:
				{
				alt61=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 61, 0, input);
				throw nvae;
			}
			switch (alt61) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1774:4: (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )*
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1774:4: (i1= ID DOT )*
					loop59:
					while (true) {
						int alt59=2;
						int LA59_0 = input.LA(1);
						if ( (LA59_0==ID) ) {
							int LA59_1 = input.LA(2);
							if ( (LA59_1==DOT) ) {
								alt59=1;
							}

						}

						switch (alt59) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1774:5: i1= ID DOT
							{
							i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3328); 
							i1_tree = (Object)adaptor.create(i1);
							adaptor.addChild(root_0, i1_tree);

							DOT152=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3330); 
							DOT152_tree = (Object)adaptor.create(DOT152);
							adaptor.addChild(root_0, DOT152_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
							    try {
							        if(ne == null) {
							            ne = this.interp.get((i1!=null?i1.getText():null));
							            if(null == ne) {
							                printError((i1!=null?i1.getText():null)+" is not defined.");
							            }
							        } else {
							            if(!(ne instanceof Device)) {
							                printError((i1!=null?i1.getText():null)+" is not a Device.");
							            }
							        
							            String name = ne.getName();
							            try {
							                ne = ((Device)ne).getComponent((i1!=null?i1.getText():null));
							            } catch(EugeneException ee) {
							                printError(ee.getMessage());
							            }
							            if(null == ne) {
							                printError(name+" does not contain "+(i1!=null?i1.getText():null)+".");
							            }
							        }
							    } catch(EugeneException ee) {
							        printError(ee.getMessage());
							    }
							    
							    if(elements == null) {
							        elements = new ArrayList<NamedElement>();
							    }

							    elements.add(ne);
							}	
								
							}
							break;

						default :
							break loop59;
						}
					}

					i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3339); 
					i2_tree = (Object)adaptor.create(i2);
					adaptor.addChild(root_0, i2_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        NamedElement property = null;

					        if(ne == null) {
					            property = this.interp.get((i2!=null?i2.getText():null));
					            if(null == property) {
					                printError((i2!=null?i2.getText():null)+" is not defined.");
					            }
					        } else {
					            if(!(ne instanceof Component) && !(ne instanceof ComponentType)) {
					                printError((i2!=null?i2.getText():null)+" is not a Device, Part, nor Part Type.");
					            }
					        
					            if(ne instanceof ComponentType) {
					                property = ((ComponentType)ne).getProperty((i2!=null?i2.getText():null));
					            } else if(ne instanceof Component) {
					                property = ((Component)ne).getProperty((i2!=null?i2.getText():null));
					            }
					            if(null == property) {
					                printError(ne.getName()+" does not contain "+(i2!=null?i2.getText():null)+".");
					            }
					        }

					        if(!(property instanceof Property)) {
					            printError((i2!=null?i2.getText():null)+" is not a Property.");
					        }     
					    
					        retval.eop = new ExpressionOperand(elements, (Property)property);

					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }
					}	
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1842:5: ( LEFTSBR n= NUMBER RIGHTSBR )*
					loop60:
					while (true) {
						int alt60=2;
						int LA60_0 = input.LA(1);
						if ( (LA60_0==LEFTSBR) ) {
							alt60=1;
						}

						switch (alt60) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1842:6: LEFTSBR n= NUMBER RIGHTSBR
							{
							LEFTSBR153=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3345); 
							LEFTSBR153_tree = (Object)adaptor.create(LEFTSBR153);
							adaptor.addChild(root_0, LEFTSBR153_tree);

							n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3349); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							RIGHTSBR154=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3351); 
							RIGHTSBR154_tree = (Object)adaptor.create(RIGHTSBR154);
							adaptor.addChild(root_0, RIGHTSBR154_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
							    if(null != retval.eop) {
							        try {
							            retval.eop.addIndex((n!=null?n.getText():null));
							        } catch(EugeneException ee) {
							            printError(ee.getMessage());
							        }
							    }
							}	
								
							}
							break;

						default :
							break loop60;
						}
					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1853:4: n= NUMBER
					{
					root_0 = (Object)adaptor.nil();


					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3363); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    Variable v = new Variable(null, EugeneConstants.NUM);
					    v.num = Double.parseDouble((n!=null?n.getText():null));
					    retval.eop = new ExpressionOperand(v);
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1860:4: MINUS n= NUMBER
					{
					root_0 = (Object)adaptor.nil();


					MINUS155=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3370); 
					MINUS155_tree = (Object)adaptor.create(MINUS155);
					adaptor.addChild(root_0, MINUS155_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3374); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    Variable v = new Variable(null, EugeneConstants.NUM);
					    v.num = Double.parseDouble((n!=null?n.getText():null)) * -1;
					    retval.eop = new ExpressionOperand(v);
					}	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1867:4: r= REAL
					{
					root_0 = (Object)adaptor.nil();


					r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3383); 
					r_tree = (Object)adaptor.create(r);
					adaptor.addChild(root_0, r_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    Variable v = new Variable(null, EugeneConstants.NUM);
					    v.num = Double.parseDouble((r!=null?r.getText():null));
					    retval.eop = new ExpressionOperand(v);
					}	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1874:4: MINUS r= REAL
					{
					root_0 = (Object)adaptor.nil();


					MINUS156=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3390); 
					MINUS156_tree = (Object)adaptor.create(MINUS156);
					adaptor.addChild(root_0, MINUS156_tree);

					r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3394); 
					r_tree = (Object)adaptor.create(r);
					adaptor.addChild(root_0, r_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    Variable v = new Variable(null, EugeneConstants.NUM);
					    v.num = Double.parseDouble((r!=null?r.getText():null)) * -1.0;
					    retval.eop = new ExpressionOperand(v);
					}	
						
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1881:4: s= STRING
					{
					root_0 = (Object)adaptor.nil();


					s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3403); 
					s_tree = (Object)adaptor.create(s);
					adaptor.addChild(root_0, s_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    Variable v = new Variable(null, EugeneConstants.TXT);
					    v.txt = (s!=null?s.getText():null);
					    retval.eop = new ExpressionOperand(v);
					}		
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exp_operand"


	public static class regexp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "regexp"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1891:1: regexp[boolean defer] :;
	public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
		EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1892:2: ()
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1893:2: 
			{
			root_0 = (Object)adaptor.nil();


			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "regexp"


	public static class exp_op_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "exp_op"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1895:1: exp_op[boolean defer] : relationalOperators ;
	public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
		EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope relationalOperators157 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1896:2: ( relationalOperators )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1896:4: relationalOperators
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_relationalOperators_in_exp_op3430);
			relationalOperators157=relationalOperators();
			state._fsp--;

			adaptor.addChild(root_0, relationalOperators157.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exp_op"


	public static class grammarDeclaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "grammarDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1904:1: grammarDeclaration[boolean defer] : GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP ;
	public final EugeneParser.grammarDeclaration_return grammarDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.grammarDeclaration_return retval = new EugeneParser.grammarDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token GRAMMAR158=null;
		Token LEFTP159=null;
		Token RIGHTP161=null;
		ParserRuleReturnScope list_of_production_rules160 =null;

		Object n_tree=null;
		Object GRAMMAR158_tree=null;
		Object LEFTP159_tree=null;
		Object RIGHTP161_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1905:2: ( GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1906:3: GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			GRAMMAR158=(Token)match(input,GRAMMAR,FOLLOW_GRAMMAR_in_grammarDeclaration3449); 
			GRAMMAR158_tree = (Object)adaptor.create(GRAMMAR158);
			adaptor.addChild(root_0, GRAMMAR158_tree);

			n=(Token)match(input,ID,FOLLOW_ID_in_grammarDeclaration3453); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			LEFTP159=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_grammarDeclaration3455); 
			LEFTP159_tree = (Object)adaptor.create(LEFTP159);
			adaptor.addChild(root_0, LEFTP159_tree);

			pushFollow(FOLLOW_list_of_production_rules_in_grammarDeclaration3457);
			list_of_production_rules160=list_of_production_rules(defer);
			state._fsp--;

			adaptor.addChild(root_0, list_of_production_rules160.getTree());

			RIGHTP161=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_grammarDeclaration3460); 
			RIGHTP161_tree = (Object)adaptor.create(RIGHTP161);
			adaptor.addChild(root_0, RIGHTP161_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "grammarDeclaration"


	public static class list_of_production_rules_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "list_of_production_rules"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1909:1: list_of_production_rules[boolean defer] : production_rule[defer] SEMIC ( list_of_production_rules[defer] )? ;
	public final EugeneParser.list_of_production_rules_return list_of_production_rules(boolean defer) throws RecognitionException {
		EugeneParser.list_of_production_rules_return retval = new EugeneParser.list_of_production_rules_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMIC163=null;
		ParserRuleReturnScope production_rule162 =null;
		ParserRuleReturnScope list_of_production_rules164 =null;

		Object SEMIC163_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1910:2: ( production_rule[defer] SEMIC ( list_of_production_rules[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1910:4: production_rule[defer] SEMIC ( list_of_production_rules[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_production_rule_in_list_of_production_rules3472);
			production_rule162=production_rule(defer);
			state._fsp--;

			adaptor.addChild(root_0, production_rule162.getTree());

			SEMIC163=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_list_of_production_rules3475); 
			SEMIC163_tree = (Object)adaptor.create(SEMIC163);
			adaptor.addChild(root_0, SEMIC163_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1910:33: ( list_of_production_rules[defer] )?
			int alt62=2;
			int LA62_0 = input.LA(1);
			if ( (LA62_0==ID) ) {
				alt62=1;
			}
			switch (alt62) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1910:34: list_of_production_rules[defer]
					{
					pushFollow(FOLLOW_list_of_production_rules_in_list_of_production_rules3478);
					list_of_production_rules164=list_of_production_rules(defer);
					state._fsp--;

					adaptor.addChild(root_0, list_of_production_rules164.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "list_of_production_rules"


	public static class production_rule_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "production_rule"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1913:1: production_rule[boolean defer] : lhs= ID ARROW right_hand_side[defer] ;
	public final EugeneParser.production_rule_return production_rule(boolean defer) throws RecognitionException {
		EugeneParser.production_rule_return retval = new EugeneParser.production_rule_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token lhs=null;
		Token ARROW165=null;
		ParserRuleReturnScope right_hand_side166 =null;

		Object lhs_tree=null;
		Object ARROW165_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1914:2: (lhs= ID ARROW right_hand_side[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1914:4: lhs= ID ARROW right_hand_side[defer]
			{
			root_0 = (Object)adaptor.nil();


			lhs=(Token)match(input,ID,FOLLOW_ID_in_production_rule3498); 
			lhs_tree = (Object)adaptor.create(lhs);
			adaptor.addChild(root_0, lhs_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    // ID denotes a non-terminal of the grammar
			}	
				
			ARROW165=(Token)match(input,ARROW,FOLLOW_ARROW_in_production_rule3502); 
			ARROW165_tree = (Object)adaptor.create(ARROW165);
			adaptor.addChild(root_0, ARROW165_tree);

			pushFollow(FOLLOW_right_hand_side_in_production_rule3504);
			right_hand_side166=right_hand_side(defer);
			state._fsp--;

			adaptor.addChild(root_0, right_hand_side166.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "production_rule"


	public static class right_hand_side_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "right_hand_side"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1921:1: right_hand_side[boolean defer] : (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] );
	public final EugeneParser.right_hand_side_return right_hand_side(boolean defer) throws RecognitionException {
		EugeneParser.right_hand_side_return retval = new EugeneParser.right_hand_side_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token COMMA167=null;
		ParserRuleReturnScope right_hand_side168 =null;
		ParserRuleReturnScope interaction169 =null;

		Object i_tree=null;
		Object COMMA167_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1922:2: (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] )
			int alt64=2;
			int LA64_0 = input.LA(1);
			if ( (LA64_0==ID) ) {
				int LA64_1 = input.LA(2);
				if ( (LA64_1==COMMA||LA64_1==SEMIC) ) {
					alt64=1;
				}
				else if ( (LA64_1==LC_INDUCES||LA64_1==LC_REPRESSES||LA64_1==UC_INDUCES||LA64_1==UC_REPRESSES) ) {
					alt64=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 64, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 64, 0, input);
				throw nvae;
			}

			switch (alt64) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1922:4: i= ID ( COMMA right_hand_side[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					i=(Token)match(input,ID,FOLLOW_ID_in_right_hand_side3520); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    // ID must be either a terminal (i.e. a part)
					    // or a non-terminal defined within the grammar
					}	
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1927:4: ( COMMA right_hand_side[defer] )?
					int alt63=2;
					int LA63_0 = input.LA(1);
					if ( (LA63_0==COMMA) ) {
						alt63=1;
					}
					switch (alt63) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1927:5: COMMA right_hand_side[defer]
							{
							COMMA167=(Token)match(input,COMMA,FOLLOW_COMMA_in_right_hand_side3525); 
							COMMA167_tree = (Object)adaptor.create(COMMA167);
							adaptor.addChild(root_0, COMMA167_tree);

							pushFollow(FOLLOW_right_hand_side_in_right_hand_side3527);
							right_hand_side168=right_hand_side(defer);
							state._fsp--;

							adaptor.addChild(root_0, right_hand_side168.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1928:4: interaction[defer, \"some_string\"]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_interaction_in_right_hand_side3535);
					interaction169=interaction(defer, "some_string");
					state._fsp--;

					adaptor.addChild(root_0, interaction169.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "right_hand_side"


	public static class interactionDeclaration_return extends ParserRuleReturnScope {
		public Interaction ia;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "interactionDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1935:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
	public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token INTERACTION170=null;
		Token LEFTP171=null;
		Token RIGHTP172=null;
		ParserRuleReturnScope i1 =null;
		ParserRuleReturnScope i2 =null;

		Object name_tree=null;
		Object INTERACTION170_tree=null;
		Object LEFTP171_tree=null;
		Object RIGHTP172_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1937:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
			int alt65=2;
			int LA65_0 = input.LA(1);
			if ( (LA65_0==ID) ) {
				alt65=1;
			}
			else if ( (LA65_0==INTERACTION) ) {
				alt65=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 65, 0, input);
				throw nvae;
			}

			switch (alt65) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1937:4: i1= interaction[defer, null]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_interaction_in_interactionDeclaration3560);
					i1=interaction(defer, null);
					state._fsp--;

					adaptor.addChild(root_0, i1.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.ia = (i1!=null?((EugeneParser.interaction_return)i1).ia:null);
					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1942:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					INTERACTION170=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3568); 
					INTERACTION170_tree = (Object)adaptor.create(INTERACTION170);
					adaptor.addChild(root_0, INTERACTION170_tree);

					name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3572); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					LEFTP171=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3574); 
					LEFTP171_tree = (Object)adaptor.create(LEFTP171);
					adaptor.addChild(root_0, LEFTP171_tree);

					pushFollow(FOLLOW_interaction_in_interactionDeclaration3578);
					i2=interaction(defer, (name!=null?name.getText():null));
					state._fsp--;

					adaptor.addChild(root_0, i2.getTree());

					RIGHTP172=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3581); 
					RIGHTP172_tree = (Object)adaptor.create(RIGHTP172);
					adaptor.addChild(root_0, RIGHTP172_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.ia = (i2!=null?((EugeneParser.interaction_return)i2).ia:null);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "interactionDeclaration"


	public static class interaction_return extends ParserRuleReturnScope {
		public Interaction ia;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "interaction"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1949:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
	public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
		EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token lhs1=null;
		Token rhs1=null;
		Token lhs2=null;
		Token LEFTP173=null;
		Token RIGHTP174=null;
		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope t2 =null;
		ParserRuleReturnScope rhs2 =null;

		Object lhs1_tree=null;
		Object rhs1_tree=null;
		Object lhs2_tree=null;
		Object LEFTP173_tree=null;
		Object RIGHTP174_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1951:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
			int alt66=2;
			int LA66_0 = input.LA(1);
			if ( (LA66_0==ID) ) {
				int LA66_1 = input.LA(2);
				if ( (LA66_1==LC_REPRESSES||LA66_1==UC_REPRESSES) ) {
					int LA66_2 = input.LA(3);
					if ( (LA66_2==ID) ) {
						alt66=1;
					}
					else if ( (LA66_2==LEFTP) ) {
						alt66=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 66, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA66_1==LC_INDUCES||LA66_1==UC_INDUCES) ) {
					int LA66_3 = input.LA(3);
					if ( (LA66_3==ID) ) {
						alt66=1;
					}
					else if ( (LA66_3==LEFTP) ) {
						alt66=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 66, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 66, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 66, 0, input);
				throw nvae;
			}

			switch (alt66) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1951:4: lhs1= ID t1= interactionType[defer] rhs1= ID
					{
					root_0 = (Object)adaptor.nil();


					lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3604); 
					lhs1_tree = (Object)adaptor.create(lhs1);
					adaptor.addChild(root_0, lhs1_tree);

					pushFollow(FOLLOW_interactionType_in_interaction3608);
					t1=interactionType(defer);
					state._fsp--;

					adaptor.addChild(root_0, t1.getTree());

					rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3613); 
					rhs1_tree = (Object)adaptor.create(rhs1);
					adaptor.addChild(root_0, rhs1_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.ia = this.interp.createInteraction(name, (lhs1!=null?lhs1.getText():null), (t1!=null?((EugeneParser.interactionType_return)t1).type:null), (rhs1!=null?rhs1.getText():null));
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1960:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3622); 
					lhs2_tree = (Object)adaptor.create(lhs2);
					adaptor.addChild(root_0, lhs2_tree);

					pushFollow(FOLLOW_interactionType_in_interaction3626);
					t2=interactionType(defer);
					state._fsp--;

					adaptor.addChild(root_0, t2.getTree());

					LEFTP173=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3629); 
					LEFTP173_tree = (Object)adaptor.create(LEFTP173);
					adaptor.addChild(root_0, LEFTP173_tree);

					pushFollow(FOLLOW_interaction_in_interaction3633);
					rhs2=interaction(defer, name);
					state._fsp--;

					adaptor.addChild(root_0, rhs2.getTree());

					RIGHTP174=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3636); 
					RIGHTP174_tree = (Object)adaptor.create(RIGHTP174);
					adaptor.addChild(root_0, RIGHTP174_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.ia = this.interp.createInteraction(name, (lhs2!=null?lhs2.getText():null), (t2!=null?((EugeneParser.interactionType_return)t2).type:null), (rhs2!=null?((EugeneParser.interaction_return)rhs2).ia:null));
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "interaction"


	public static class interactionType_return extends ParserRuleReturnScope {
		public Interaction.InteractionType type;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "interactionType"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1971:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
	public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
		EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set175=null;
		Token set176=null;

		Object set175_tree=null;
		Object set176_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1973:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
			int alt67=2;
			int LA67_0 = input.LA(1);
			if ( (LA67_0==LC_REPRESSES||LA67_0==UC_REPRESSES) ) {
				alt67=1;
			}
			else if ( (LA67_0==LC_INDUCES||LA67_0==UC_INDUCES) ) {
				alt67=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 67, 0, input);
				throw nvae;
			}

			switch (alt67) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1973:4: ( UC_REPRESSES | LC_REPRESSES )
					{
					root_0 = (Object)adaptor.nil();


					set175=input.LT(1);
					if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set175));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}

					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.type = Interaction.InteractionType.REPRESSES;
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1978:4: ( UC_INDUCES | LC_INDUCES )
					{
					root_0 = (Object)adaptor.nil();


					set176=input.LT(1);
					if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set176));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}

					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.type = Interaction.InteractionType.INDUCES;
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "interactionType"


	public static class printStatement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "printStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1990:1: printStatement[boolean defer] : ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP );
	public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
		EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set177=null;
		Token LEFTP178=null;
		Token RIGHTP179=null;
		Token set180=null;
		Token LEFTP181=null;
		Token RIGHTP182=null;
		ParserRuleReturnScope tp =null;

		Object set177_tree=null;
		Object LEFTP178_tree=null;
		Object RIGHTP179_tree=null;
		Object set180_tree=null;
		Object LEFTP181_tree=null;
		Object RIGHTP182_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1991:2: ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP )
			int alt68=2;
			int LA68_0 = input.LA(1);
			if ( ((LA68_0 >= PRINTLN_LC && LA68_0 <= PRINTLN_UC)) ) {
				alt68=1;
			}
			else if ( ((LA68_0 >= PRINT_LC && LA68_0 <= PRINT_UC)) ) {
				alt68=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 68, 0, input);
				throw nvae;
			}

			switch (alt68) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1991:4: ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set177=input.LT(1);
					if ( (input.LA(1) >= PRINTLN_LC && input.LA(1) <= PRINTLN_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set177));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP178=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3701); 
					LEFTP178_tree = (Object)adaptor.create(LEFTP178);
					adaptor.addChild(root_0, LEFTP178_tree);

					pushFollow(FOLLOW_toPrint_in_printStatement3705);
					tp=toPrint(defer);
					state._fsp--;

					adaptor.addChild(root_0, tp.getTree());

					RIGHTP179=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3708); 
					RIGHTP179_tree = (Object)adaptor.create(RIGHTP179);
					adaptor.addChild(root_0, RIGHTP179_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    if(null != (tp!=null?((EugeneParser.toPrint_return)tp).sb:null)) {
					        try {
					            this.writer.write((tp!=null?((EugeneParser.toPrint_return)tp).sb:null).toString());
					            this.writer.newLine();
					            this.writer.flush();
					        } catch(IOException ioe) {
					            printError(ioe.getMessage());
					        }
					    }
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2004:4: ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set180=input.LT(1);
					if ( (input.LA(1) >= PRINT_LC && input.LA(1) <= PRINT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set180));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP181=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3721); 
					LEFTP181_tree = (Object)adaptor.create(LEFTP181);
					adaptor.addChild(root_0, LEFTP181_tree);

					pushFollow(FOLLOW_toPrint_in_printStatement3725);
					tp=toPrint(defer);
					state._fsp--;

					adaptor.addChild(root_0, tp.getTree());

					RIGHTP182=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3728); 
					RIGHTP182_tree = (Object)adaptor.create(RIGHTP182);
					adaptor.addChild(root_0, RIGHTP182_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    if(null != (tp!=null?((EugeneParser.toPrint_return)tp).sb:null)) {
					        try {
					            this.writer.write((tp!=null?((EugeneParser.toPrint_return)tp).sb:null).toString());
					            this.writer.flush();
					        } catch(IOException ioe) {
					            printError(ioe.getMessage());
					        }
					    }
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "printStatement"


	public static class toPrint_return extends ParserRuleReturnScope {
		public StringBuilder sb;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "toPrint"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2018:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
	public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
		EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope exp =null;
		ParserRuleReturnScope tpp =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2020:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2020:4: exp= expr[defer] tpp= toPrint_prime[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_toPrint3749);
			exp=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, exp.getTree());

			pushFollow(FOLLOW_toPrint_prime_in_toPrint3754);
			tpp=toPrint_prime(defer);
			state._fsp--;

			adaptor.addChild(root_0, tpp.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.sb = new StringBuilder();
			    if(null != (exp!=null?((EugeneParser.expr_return)exp).element:null)) {
			        retval.sb.append((exp!=null?((EugeneParser.expr_return)exp).element:null));
			    } else {
			        retval.sb.append((exp!=null?((EugeneParser.expr_return)exp).p:null));
			    }
			    retval.sb.append((tpp!=null?((EugeneParser.toPrint_prime_return)tpp).sb:null));
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "toPrint"


	public static class toPrint_prime_return extends ParserRuleReturnScope {
		public StringBuilder sb;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "toPrint_prime"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2033:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
	public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
		EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA183=null;
		ParserRuleReturnScope tp =null;

		Object COMMA183_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2035:2: (| COMMA tp= toPrint[defer] )
			int alt69=2;
			int LA69_0 = input.LA(1);
			if ( (LA69_0==RIGHTP) ) {
				alt69=1;
			}
			else if ( (LA69_0==COMMA) ) {
				alt69=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 69, 0, input);
				throw nvae;
			}

			switch (alt69) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2035:4: 
					{
					root_0 = (Object)adaptor.nil();



					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.sb = new StringBuilder();
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2040:4: COMMA tp= toPrint[defer]
					{
					root_0 = (Object)adaptor.nil();


					COMMA183=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime3780); 
					COMMA183_tree = (Object)adaptor.create(COMMA183);
					adaptor.addChild(root_0, COMMA183_tree);

					pushFollow(FOLLOW_toPrint_in_toPrint_prime3784);
					tp=toPrint(defer);
					state._fsp--;

					adaptor.addChild(root_0, tp.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.sb = new StringBuilder();
					    retval.sb.append((tp!=null?((EugeneParser.toPrint_return)tp).sb:null));
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "toPrint_prime"


	public static class imperativeStatements_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "imperativeStatements"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2052:1: imperativeStatements[boolean defer] : ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] );
	public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope ifStatement184 =null;
		ParserRuleReturnScope forall_iterator185 =null;
		ParserRuleReturnScope for_loop186 =null;
		ParserRuleReturnScope while_loop187 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2054:2: ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] )
			int alt70=4;
			switch ( input.LA(1) ) {
			case LC_IF:
			case UC_IF:
				{
				alt70=1;
				}
				break;
			case LC_FORALL:
			case UC_FORALL:
				{
				alt70=2;
				}
				break;
			case LC_FOR:
			case UC_FOR:
				{
				alt70=3;
				}
				break;
			case LC_WHILE:
			case UC_WHILE:
				{
				alt70=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 70, 0, input);
				throw nvae;
			}
			switch (alt70) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2054:4: ifStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_ifStatement_in_imperativeStatements3809);
					ifStatement184=ifStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, ifStatement184.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2055:4: forall_iterator[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_forall_iterator_in_imperativeStatements3815);
					forall_iterator185=forall_iterator(defer);
					state._fsp--;

					adaptor.addChild(root_0, forall_iterator185.getTree());

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2056:4: for_loop[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_for_loop_in_imperativeStatements3821);
					for_loop186=for_loop(defer);
					state._fsp--;

					adaptor.addChild(root_0, for_loop186.getTree());

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2057:4: while_loop[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_while_loop_in_imperativeStatements3827);
					while_loop187=while_loop(defer);
					state._fsp--;

					adaptor.addChild(root_0, while_loop187.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "imperativeStatements"


	public static class ifStatement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ifStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2060:1: ifStatement[boolean defer] : ( UC_IF | LC_IF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )? ;
	public final EugeneParser.ifStatement_return ifStatement(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.ifStatement_return retval = new EugeneParser.ifStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set188=null;
		Token LEFTP189=null;
		Token RIGHTP190=null;
		Token LEFTCUR191=null;
		Token RIGHTCUR192=null;
		Token set193=null;
		Token LEFTP194=null;
		Token RIGHTP195=null;
		Token LEFTCUR196=null;
		Token RIGHTCUR197=null;
		Token set198=null;
		Token LEFTCUR199=null;
		Token RIGHTCUR200=null;
		ParserRuleReturnScope co =null;
		ParserRuleReturnScope stmts =null;

		Object set188_tree=null;
		Object LEFTP189_tree=null;
		Object RIGHTP190_tree=null;
		Object LEFTCUR191_tree=null;
		Object RIGHTCUR192_tree=null;
		Object set193_tree=null;
		Object LEFTP194_tree=null;
		Object RIGHTP195_tree=null;
		Object LEFTCUR196_tree=null;
		Object RIGHTCUR197_tree=null;
		Object set198_tree=null;
		Object LEFTCUR199_tree=null;
		Object RIGHTCUR200_tree=null;


		boolean bExecuted = false;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2066:2: ( ( UC_IF | LC_IF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2070:3: ( UC_IF | LC_IF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )?
			{
			root_0 = (Object)adaptor.nil();


			set188=input.LT(1);
			if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set188));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP189=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3865); 
			LEFTP189_tree = (Object)adaptor.create(LEFTP189);
			adaptor.addChild(root_0, LEFTP189_tree);

			pushFollow(FOLLOW_logical_condition_in_ifStatement3869);
			co=logical_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, co.getTree());

			RIGHTP190=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3872); 
			RIGHTP190_tree = (Object)adaptor.create(RIGHTP190);
			adaptor.addChild(root_0, RIGHTP190_tree);

			LEFTCUR191=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3874); 
			LEFTCUR191_tree = (Object)adaptor.create(LEFTCUR191);
			adaptor.addChild(root_0, LEFTCUR191_tree);

			pushFollow(FOLLOW_list_of_statements_in_ifStatement3882);
			stmts=list_of_statements(true);
			state._fsp--;

			adaptor.addChild(root_0, stmts.getTree());

			RIGHTCUR192=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3885); 
			RIGHTCUR192_tree = (Object)adaptor.create(RIGHTCUR192);
			adaptor.addChild(root_0, RIGHTCUR192_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    // evaluate the condition
			    if((co!=null?((EugeneParser.logical_condition_return)co).b:false)) {
			        // if true => execute the statements
			        // and ignore the rest of the ifStatement
			        
			        try {
			            this.exec("list_of_statements", (stmts!=null?(stmts.start):null).getTokenIndex());
			        } catch(EugeneReturnException ere) {
			            throw new EugeneReturnException(ere.getReturnValue());
			        } catch(EugeneException ee) {
			            printError(ee.getLocalizedMessage());
			        }
			        bExecuted = true;
			    }
			}			
					
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2093:3: ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )*
			loop71:
			while (true) {
				int alt71=2;
				int LA71_0 = input.LA(1);
				if ( (LA71_0==LC_ELSEIF||LA71_0==UC_ELSEIF) ) {
					alt71=1;
				}

				switch (alt71) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2093:5: ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
					{
					set193=input.LT(1);
					if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set193));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP194=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3906); 
					LEFTP194_tree = (Object)adaptor.create(LEFTP194);
					adaptor.addChild(root_0, LEFTP194_tree);

					pushFollow(FOLLOW_logical_condition_in_ifStatement3910);
					co=logical_condition(defer);
					state._fsp--;

					adaptor.addChild(root_0, co.getTree());

					RIGHTP195=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3913); 
					RIGHTP195_tree = (Object)adaptor.create(RIGHTP195);
					adaptor.addChild(root_0, RIGHTP195_tree);

					LEFTCUR196=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3915); 
					LEFTCUR196_tree = (Object)adaptor.create(LEFTCUR196);
					adaptor.addChild(root_0, LEFTCUR196_tree);

					pushFollow(FOLLOW_list_of_statements_in_ifStatement3923);
					stmts=list_of_statements(true);
					state._fsp--;

					adaptor.addChild(root_0, stmts.getTree());

					RIGHTCUR197=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3926); 
					RIGHTCUR197_tree = (Object)adaptor.create(RIGHTCUR197);
					adaptor.addChild(root_0, RIGHTCUR197_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
					    // evaluate the condition
					    if((co!=null?((EugeneParser.logical_condition_return)co).b:false)) {
					        // if true => execute the statements
					        // and ignore the rest of the ifStatement
					        try {        
					            this.exec("list_of_statements", (stmts!=null?(stmts.start):null).getTokenIndex());
					        } catch(EugeneReturnException ere) {
					            throw new EugeneReturnException(ere.getReturnValue());
					        } catch(EugeneException ee) {
					            printError(ee.getLocalizedMessage());
					        }
					        
					        bExecuted = true;
					    }
					}			
							
					}
					break;

				default :
					break loop71;
				}
			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2116:3: ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )?
			int alt72=2;
			int LA72_0 = input.LA(1);
			if ( (LA72_0==LC_ELSE||LA72_0==UC_ELSE) ) {
				alt72=1;
			}
			switch (alt72) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2116:4: ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR
					{
					set198=input.LT(1);
					if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set198));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTCUR199=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3948); 
					LEFTCUR199_tree = (Object)adaptor.create(LEFTCUR199);
					adaptor.addChild(root_0, LEFTCUR199_tree);

					pushFollow(FOLLOW_list_of_statements_in_ifStatement3956);
					stmts=list_of_statements(true);
					state._fsp--;

					adaptor.addChild(root_0, stmts.getTree());

					RIGHTCUR200=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3959); 
					RIGHTCUR200_tree = (Object)adaptor.create(RIGHTCUR200);
					adaptor.addChild(root_0, RIGHTCUR200_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
					    try {
					        this.exec("list_of_statements", (stmts!=null?(stmts.start):null).getTokenIndex());        
					    } catch(EugeneReturnException ere) {
					        throw new EugeneReturnException(ere.getReturnValue());
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					    
					    bExecuted = true;
					}						
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ifStatement"


	public static class forall_iterator_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "forall_iterator"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2133:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR los= list_of_statements[defer] RIGHTCUR ;
	public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token it=null;
		Token i=null;
		Token set201=null;
		Token COLON202=null;
		Token LEFTCUR203=null;
		Token RIGHTCUR204=null;
		ParserRuleReturnScope los =null;

		Object it_tree=null;
		Object i_tree=null;
		Object set201_tree=null;
		Object COLON202_tree=null;
		Object LEFTCUR203_tree=null;
		Object RIGHTCUR204_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR los= list_of_statements[defer] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR los= list_of_statements[defer] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			set201=input.LT(1);
			if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set201));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:26: (it= ID COLON )?
			int alt73=2;
			int LA73_0 = input.LA(1);
			if ( (LA73_0==ID) ) {
				int LA73_1 = input.LA(2);
				if ( (LA73_1==COLON) ) {
					alt73=1;
				}
			}
			switch (alt73) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:27: it= ID COLON
					{
					it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3990); 
					it_tree = (Object)adaptor.create(it);
					adaptor.addChild(root_0, it_tree);

					COLON202=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator3992); 
					COLON202_tree = (Object)adaptor.create(COLON202);
					adaptor.addChild(root_0, COLON202_tree);

					}
					break;

			}

			i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3998); 
			i_tree = (Object)adaptor.create(i);
			adaptor.addChild(root_0, i_tree);

			LEFTCUR203=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator4000); 
			LEFTCUR203_tree = (Object)adaptor.create(LEFTCUR203);
			adaptor.addChild(root_0, LEFTCUR203_tree);

			pushFollow(FOLLOW_list_of_statements_in_forall_iterator4007);
			los=list_of_statements(defer);
			state._fsp--;

			adaptor.addChild(root_0, los.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        this.exec("list_of_statements", (los!=null?(los.start):null).getTokenIndex());
			    } catch(EugeneReturnException ere) {
			        throw new EugeneReturnException(ere.getReturnValue());
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}			
				
			RIGHTCUR204=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator4014); 
			RIGHTCUR204_tree = (Object)adaptor.create(RIGHTCUR204);
			adaptor.addChild(root_0, RIGHTCUR204_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "forall_iterator"


	public static class for_loop_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "for_loop"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2150:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= logical_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ;
	public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set205=null;
		Token LEFTP206=null;
		Token SEMIC207=null;
		Token SEMIC208=null;
		Token RIGHTP209=null;
		Token LEFTCUR210=null;
		Token RIGHTCUR211=null;
		ParserRuleReturnScope ds =null;
		ParserRuleReturnScope co =null;
		ParserRuleReturnScope as =null;
		ParserRuleReturnScope stmts =null;

		Object set205_tree=null;
		Object LEFTP206_tree=null;
		Object SEMIC207_tree=null;
		Object SEMIC208_tree=null;
		Object RIGHTP209_tree=null;
		Object LEFTCUR210_tree=null;
		Object RIGHTCUR211_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2152:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= logical_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2152:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= logical_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			set205=input.LT(1);
			if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set205));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP206=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop4037); 
			LEFTP206_tree = (Object)adaptor.create(LEFTP206);
			adaptor.addChild(root_0, LEFTP206_tree);

			pushFollow(FOLLOW_variableDeclaration_in_for_loop4041);
			ds=variableDeclaration(true);
			state._fsp--;

			adaptor.addChild(root_0, ds.getTree());

			SEMIC207=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4044); 
			SEMIC207_tree = (Object)adaptor.create(SEMIC207);
			adaptor.addChild(root_0, SEMIC207_tree);

			pushFollow(FOLLOW_logical_condition_in_for_loop4048);
			co=logical_condition(true);
			state._fsp--;

			adaptor.addChild(root_0, co.getTree());

			SEMIC208=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4051); 
			SEMIC208_tree = (Object)adaptor.create(SEMIC208);
			adaptor.addChild(root_0, SEMIC208_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2152:94: (as= assignment[true] )?
			int alt74=2;
			int LA74_0 = input.LA(1);
			if ( (LA74_0==ID) ) {
				alt74=1;
			}
			switch (alt74) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2152:95: as= assignment[true]
					{
					pushFollow(FOLLOW_assignment_in_for_loop4056);
					as=assignment(true);
					state._fsp--;

					adaptor.addChild(root_0, as.getTree());

					}
					break;

			}

			RIGHTP209=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop4061); 
			RIGHTP209_tree = (Object)adaptor.create(RIGHTP209);
			adaptor.addChild(root_0, RIGHTP209_tree);

			LEFTCUR210=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop4063); 
			LEFTCUR210_tree = (Object)adaptor.create(LEFTCUR210);
			adaptor.addChild(root_0, LEFTCUR210_tree);

			pushFollow(FOLLOW_list_of_statements_in_for_loop4071);
			stmts=list_of_statements(true);
			state._fsp--;

			adaptor.addChild(root_0, stmts.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        if(null != as) {
			            this.execute_loop(
			                (ds!=null?(ds.start):null),      // init
			                (co!=null?(co.start):null),      // condition
			                (as!=null?(as.start):null),      // increment
			                (stmts!=null?(stmts.start):null));  // loop-body
			        } else {
			            this.execute_loop(
			                (ds!=null?(ds.start):null),      // init
			                (co!=null?(co.start):null),      // condition
			                null,           // increment
			                (stmts!=null?(stmts.start):null));  // loop-body
			        }
			    } catch(EugeneReturnException ere) {
			        throw new EugeneReturnException(ere.getReturnValue());
			    } catch(Exception ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}			
					
			RIGHTCUR211=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop4078); 
			RIGHTCUR211_tree = (Object)adaptor.create(RIGHTCUR211);
			adaptor.addChild(root_0, RIGHTCUR211_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "for_loop"


	public static class while_loop_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "while_loop"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2179:1: while_loop[boolean defer] : ( UC_WHILE | LC_WHILE ) LEFTP co= logical_condition[true] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ;
	public final EugeneParser.while_loop_return while_loop(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.while_loop_return retval = new EugeneParser.while_loop_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set212=null;
		Token LEFTP213=null;
		Token RIGHTP214=null;
		Token LEFTCUR215=null;
		Token RIGHTCUR216=null;
		ParserRuleReturnScope co =null;
		ParserRuleReturnScope stmts =null;

		Object set212_tree=null;
		Object LEFTP213_tree=null;
		Object RIGHTP214_tree=null;
		Object LEFTCUR215_tree=null;
		Object RIGHTCUR216_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2181:2: ( ( UC_WHILE | LC_WHILE ) LEFTP co= logical_condition[true] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2181:4: ( UC_WHILE | LC_WHILE ) LEFTP co= logical_condition[true] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			set212=input.LT(1);
			if ( input.LA(1)==LC_WHILE||input.LA(1)==UC_WHILE ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set212));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP213=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_while_loop4103); 
			LEFTP213_tree = (Object)adaptor.create(LEFTP213);
			adaptor.addChild(root_0, LEFTP213_tree);

			pushFollow(FOLLOW_logical_condition_in_while_loop4107);
			co=logical_condition(true);
			state._fsp--;

			adaptor.addChild(root_0, co.getTree());

			RIGHTP214=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_while_loop4110); 
			RIGHTP214_tree = (Object)adaptor.create(RIGHTP214);
			adaptor.addChild(root_0, RIGHTP214_tree);

			LEFTCUR215=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_while_loop4112); 
			LEFTCUR215_tree = (Object)adaptor.create(LEFTCUR215);
			adaptor.addChild(root_0, LEFTCUR215_tree);

			pushFollow(FOLLOW_list_of_statements_in_while_loop4120);
			stmts=list_of_statements(true);
			state._fsp--;

			adaptor.addChild(root_0, stmts.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        this.execute_loop(
			            null,           // init
			            (co!=null?(co.start):null),      // condition
			            null,           // increment
			            (stmts!=null?(stmts.start):null));  // loop-body
			    } catch(EugeneReturnException ere) {
			        throw new EugeneReturnException(ere.getReturnValue());
			    } catch(Exception ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}			
				
			RIGHTCUR216=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_while_loop4127); 
			RIGHTCUR216_tree = (Object)adaptor.create(RIGHTCUR216);
			adaptor.addChild(root_0, RIGHTCUR216_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "while_loop"


	public static class logical_condition_return extends ParserRuleReturnScope {
		public boolean b;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_condition"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2205:1: logical_condition[boolean defer] returns [boolean b] : loc= logical_or_condition[defer] ;
	public final EugeneParser.logical_condition_return logical_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_condition_return retval = new EugeneParser.logical_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope loc =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2207:2: (loc= logical_or_condition[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2207:4: loc= logical_or_condition[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_logical_or_condition_in_logical_condition4153);
			loc=logical_or_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, loc.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.b = (loc!=null?((EugeneParser.logical_or_condition_return)loc).b:false);
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logical_condition"


	public static class logical_not_condition_return extends ParserRuleReturnScope {
		public boolean b;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_not_condition"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2214:1: logical_not_condition[boolean defer] returns [boolean b] : loc= logical_or_condition[defer] ;
	public final EugeneParser.logical_not_condition_return logical_not_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_not_condition_return retval = new EugeneParser.logical_not_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope loc =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2216:2: (loc= logical_or_condition[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2216:4: loc= logical_or_condition[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_logical_or_condition_in_logical_not_condition4178);
			loc=logical_or_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, loc.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.b = (loc!=null?((EugeneParser.logical_or_condition_return)loc).b:false);
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logical_not_condition"


	public static class logical_or_condition_return extends ParserRuleReturnScope {
		public boolean b;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_or_condition"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2223:1: logical_or_condition[boolean defer] returns [boolean b] : lac= logical_and_condition[defer] ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )* ;
	public final EugeneParser.logical_or_condition_return logical_or_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_or_condition_return retval = new EugeneParser.logical_or_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LC_OR217=null;
		Token UC_OR218=null;
		Token LOG_OR219=null;
		Token PIPE220=null;
		Token PIPE221=null;
		ParserRuleReturnScope lac =null;
		ParserRuleReturnScope loc =null;

		Object LC_OR217_tree=null;
		Object UC_OR218_tree=null;
		Object LOG_OR219_tree=null;
		Object PIPE220_tree=null;
		Object PIPE221_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2225:2: (lac= logical_and_condition[defer] ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2225:4: lac= logical_and_condition[defer] ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_logical_and_condition_in_logical_or_condition4203);
			lac=logical_and_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, lac.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.b = (lac!=null?((EugeneParser.logical_and_condition_return)lac).b:false);
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:4: ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )*
			loop77:
			while (true) {
				int alt77=2;
				switch ( input.LA(1) ) {
				case LC_OR:
					{
					alt77=1;
					}
					break;
				case UC_OR:
					{
					alt77=1;
					}
					break;
				case LOG_OR:
					{
					alt77=1;
					}
					break;
				case PIPE:
					{
					alt77=1;
					}
					break;
				}
				switch (alt77) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:5: ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer]
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:5: ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? )
					int alt76=4;
					switch ( input.LA(1) ) {
					case LC_OR:
						{
						alt76=1;
						}
						break;
					case UC_OR:
						{
						alt76=2;
						}
						break;
					case LOG_OR:
						{
						alt76=3;
						}
						break;
					case PIPE:
						{
						alt76=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 76, 0, input);
						throw nvae;
					}
					switch (alt76) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:6: LC_OR
							{
							LC_OR217=(Token)match(input,LC_OR,FOLLOW_LC_OR_in_logical_or_condition4210); 
							LC_OR217_tree = (Object)adaptor.create(LC_OR217);
							adaptor.addChild(root_0, LC_OR217_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:12: UC_OR
							{
							UC_OR218=(Token)match(input,UC_OR,FOLLOW_UC_OR_in_logical_or_condition4212); 
							UC_OR218_tree = (Object)adaptor.create(UC_OR218);
							adaptor.addChild(root_0, UC_OR218_tree);

							}
							break;
						case 3 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:18: LOG_OR
							{
							LOG_OR219=(Token)match(input,LOG_OR,FOLLOW_LOG_OR_in_logical_or_condition4214); 
							LOG_OR219_tree = (Object)adaptor.create(LOG_OR219);
							adaptor.addChild(root_0, LOG_OR219_tree);

							}
							break;
						case 4 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:25: PIPE ( PIPE )?
							{
							PIPE220=(Token)match(input,PIPE,FOLLOW_PIPE_in_logical_or_condition4216); 
							PIPE220_tree = (Object)adaptor.create(PIPE220);
							adaptor.addChild(root_0, PIPE220_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:30: ( PIPE )?
							int alt75=2;
							int LA75_0 = input.LA(1);
							if ( (LA75_0==PIPE) ) {
								alt75=1;
							}
							switch (alt75) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2229:31: PIPE
									{
									PIPE221=(Token)match(input,PIPE,FOLLOW_PIPE_in_logical_or_condition4219); 
									PIPE221_tree = (Object)adaptor.create(PIPE221);
									adaptor.addChild(root_0, PIPE221_tree);

									}
									break;

							}

							}
							break;

					}

					pushFollow(FOLLOW_logical_or_condition_in_logical_or_condition4226);
					loc=logical_or_condition(defer);
					state._fsp--;

					adaptor.addChild(root_0, loc.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    System.out.println("-> b: " + retval.b + " || " + (loc!=null?((EugeneParser.logical_or_condition_return)loc).b:false) + " -> " + (retval.b||(loc!=null?((EugeneParser.logical_or_condition_return)loc).b:false)));
					    retval.b = retval.b || (loc!=null?((EugeneParser.logical_or_condition_return)loc).b:false);
					}		
						
					}
					break;

				default :
					break loop77;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logical_or_condition"


	public static class logical_and_condition_return extends ParserRuleReturnScope {
		public boolean b;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_and_condition"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2237:1: logical_and_condition[boolean defer] returns [boolean b] : ac= atomic_condition[defer] ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )* ;
	public final EugeneParser.logical_and_condition_return logical_and_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_and_condition_return retval = new EugeneParser.logical_and_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LC_AND222=null;
		Token UC_AND223=null;
		Token LOG_AND224=null;
		Token AMP225=null;
		Token AMP226=null;
		ParserRuleReturnScope ac =null;
		ParserRuleReturnScope lac =null;

		Object LC_AND222_tree=null;
		Object UC_AND223_tree=null;
		Object LOG_AND224_tree=null;
		Object AMP225_tree=null;
		Object AMP226_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2239:2: (ac= atomic_condition[defer] ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2239:4: ac= atomic_condition[defer] ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_atomic_condition_in_logical_and_condition4251);
			ac=atomic_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, ac.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.b = (ac!=null?((EugeneParser.atomic_condition_return)ac).b:false);
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:4: ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )*
			loop80:
			while (true) {
				int alt80=2;
				switch ( input.LA(1) ) {
				case LC_AND:
					{
					alt80=1;
					}
					break;
				case UC_AND:
					{
					alt80=1;
					}
					break;
				case LOG_AND:
					{
					alt80=1;
					}
					break;
				case AMP:
					{
					alt80=1;
					}
					break;
				}
				switch (alt80) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:5: ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer]
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:5: ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? )
					int alt79=4;
					switch ( input.LA(1) ) {
					case LC_AND:
						{
						alt79=1;
						}
						break;
					case UC_AND:
						{
						alt79=2;
						}
						break;
					case LOG_AND:
						{
						alt79=3;
						}
						break;
					case AMP:
						{
						alt79=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 79, 0, input);
						throw nvae;
					}
					switch (alt79) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:6: LC_AND
							{
							LC_AND222=(Token)match(input,LC_AND,FOLLOW_LC_AND_in_logical_and_condition4258); 
							LC_AND222_tree = (Object)adaptor.create(LC_AND222);
							adaptor.addChild(root_0, LC_AND222_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:13: UC_AND
							{
							UC_AND223=(Token)match(input,UC_AND,FOLLOW_UC_AND_in_logical_and_condition4260); 
							UC_AND223_tree = (Object)adaptor.create(UC_AND223);
							adaptor.addChild(root_0, UC_AND223_tree);

							}
							break;
						case 3 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:20: LOG_AND
							{
							LOG_AND224=(Token)match(input,LOG_AND,FOLLOW_LOG_AND_in_logical_and_condition4262); 
							LOG_AND224_tree = (Object)adaptor.create(LOG_AND224);
							adaptor.addChild(root_0, LOG_AND224_tree);

							}
							break;
						case 4 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:28: AMP ( AMP )?
							{
							AMP225=(Token)match(input,AMP,FOLLOW_AMP_in_logical_and_condition4264); 
							AMP225_tree = (Object)adaptor.create(AMP225);
							adaptor.addChild(root_0, AMP225_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:32: ( AMP )?
							int alt78=2;
							int LA78_0 = input.LA(1);
							if ( (LA78_0==AMP) ) {
								alt78=1;
							}
							switch (alt78) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2243:33: AMP
									{
									AMP226=(Token)match(input,AMP,FOLLOW_AMP_in_logical_and_condition4267); 
									AMP226_tree = (Object)adaptor.create(AMP226);
									adaptor.addChild(root_0, AMP226_tree);

									}
									break;

							}

							}
							break;

					}

					pushFollow(FOLLOW_logical_and_condition_in_logical_and_condition4274);
					lac=logical_and_condition(defer);
					state._fsp--;

					adaptor.addChild(root_0, lac.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    System.out.println("-> b: " + retval.b + " && " + (lac!=null?((EugeneParser.logical_and_condition_return)lac).b:false) + " -> " + (retval.b||(lac!=null?((EugeneParser.logical_and_condition_return)lac).b:false)));
					    retval.b = retval.b && (lac!=null?((EugeneParser.logical_and_condition_return)lac).b:false);
					}	
						
					}
					break;

				default :
					break loop80;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logical_and_condition"


	public static class atomic_condition_return extends ParserRuleReturnScope {
		public boolean b;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atomic_condition"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2251:1: atomic_condition[boolean defer] returns [boolean b] : (lhs= expr[defer] ro= relationalOperators rhs= expr[defer] | ( LC_NOT | UC_NOT | OP_NOT ) LEFTP lac= atomic_condition[defer] RIGHTP );
	public final EugeneParser.atomic_condition_return atomic_condition(boolean defer) throws RecognitionException {
		EugeneParser.atomic_condition_return retval = new EugeneParser.atomic_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set227=null;
		Token LEFTP228=null;
		Token RIGHTP229=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope ro =null;
		ParserRuleReturnScope rhs =null;
		ParserRuleReturnScope lac =null;

		Object set227_tree=null;
		Object LEFTP228_tree=null;
		Object RIGHTP229_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2253:2: (lhs= expr[defer] ro= relationalOperators rhs= expr[defer] | ( LC_NOT | UC_NOT | OP_NOT ) LEFTP lac= atomic_condition[defer] RIGHTP )
			int alt81=2;
			int LA81_0 = input.LA(1);
			if ( (LA81_0==DOLLAR||(LA81_0 >= EXIT_LC && LA81_0 <= EXIT_UC)||(LA81_0 >= FALSE_LC && LA81_0 <= FALSE_UC)||LA81_0==ID||(LA81_0 >= LEFTP && LA81_0 <= LEFTSBR)||LA81_0==MINUS||LA81_0==NUMBER||LA81_0==PERMUTE||LA81_0==PRODUCT||(LA81_0 >= RANDOM_LC && LA81_0 <= REAL)||(LA81_0 >= SAVE_LC && LA81_0 <= SAVE_UC)||(LA81_0 >= SIZEOF_LC && LA81_0 <= STORE_UC)||(LA81_0 >= STRING && LA81_0 <= TRUE_UC)) ) {
				alt81=1;
			}
			else if ( (LA81_0==LC_NOT||LA81_0==OP_NOT||LA81_0==UC_NOT) ) {
				alt81=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 81, 0, input);
				throw nvae;
			}

			switch (alt81) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2253:4: lhs= expr[defer] ro= relationalOperators rhs= expr[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expr_in_atomic_condition4302);
					lhs=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, lhs.getTree());

					pushFollow(FOLLOW_relationalOperators_in_atomic_condition4307);
					ro=relationalOperators();
					state._fsp--;

					adaptor.addChild(root_0, ro.getTree());

					pushFollow(FOLLOW_expr_in_atomic_condition4311);
					rhs=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, rhs.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(null != (lhs!=null?((EugeneParser.expr_return)lhs).element:null)) {
					            if(null != (rhs!=null?((EugeneParser.expr_return)rhs).element:null)) {
					                // comparing two NamedElements against each other
					                // e.g. p.prop = q.prop
					                retval.b = this.interp.evaluateCondition(
					                             (lhs!=null?((EugeneParser.expr_return)lhs).element:null), 
					                             (ro!=null?input.toString(ro.start,ro.stop):null), 
					                             (rhs!=null?((EugeneParser.expr_return)rhs).element:null));
					            } else if(null != (rhs!=null?((EugeneParser.expr_return)rhs).p:null)) {
					                // comparing a LHS NamedElement against a Variable
					                // that could be either a variable or constant
					                // e.g. p.prop = i
					                retval.b = this.interp.evaluateCondition(
					                             (lhs!=null?((EugeneParser.expr_return)lhs).element:null), 
					                             (ro!=null?input.toString(ro.start,ro.stop):null), 
					                             (rhs!=null?((EugeneParser.expr_return)rhs).p:null));
					            }
					        } else {
					        
					            if(null != (rhs!=null?((EugeneParser.expr_return)rhs).element:null)) {
					                // comparing a LHS variable against a RHS 
					                // NamedElement
					                // e.g. i == q.prop
					                retval.b = this.interp.evaluateCondition(
					                             (lhs!=null?((EugeneParser.expr_return)lhs).p:null), 
					                             (ro!=null?input.toString(ro.start,ro.stop):null), 
					                             (rhs!=null?((EugeneParser.expr_return)rhs).element:null));
					            } else if(null != (rhs!=null?((EugeneParser.expr_return)rhs).p:null)) {
					                // comparing a LHS Variable against a Variable
					                // that could be either a variable or constant
					                // e.g. i == j
					                retval.b = this.interp.evaluateCondition(
					                             (lhs!=null?((EugeneParser.expr_return)lhs).p:null), 
					                             (ro!=null?input.toString(ro.start,ro.stop):null), 
					                             (rhs!=null?((EugeneParser.expr_return)rhs).p:null));
					            } else {
					                throw new EugeneException("Invalid condition!");
					            }
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2300:4: ( LC_NOT | UC_NOT | OP_NOT ) LEFTP lac= atomic_condition[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set227=input.LT(1);
					if ( input.LA(1)==LC_NOT||input.LA(1)==OP_NOT||input.LA(1)==UC_NOT ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set227));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP228=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atomic_condition4327); 
					LEFTP228_tree = (Object)adaptor.create(LEFTP228);
					adaptor.addChild(root_0, LEFTP228_tree);

					pushFollow(FOLLOW_atomic_condition_in_atomic_condition4331);
					lac=atomic_condition(defer);
					state._fsp--;

					adaptor.addChild(root_0, lac.getTree());

					RIGHTP229=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atomic_condition4334); 
					RIGHTP229_tree = (Object)adaptor.create(RIGHTP229);
					adaptor.addChild(root_0, RIGHTP229_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.b = !((lac!=null?((EugeneParser.atomic_condition_return)lac).b:false));
					}
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atomic_condition"


	public static class expr_return extends ParserRuleReturnScope {
		public Variable p;
		public String instance;
		public int index;
		public String listAddress;
		public Variable primVariable;
		public NamedElement element;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expr"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2311:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* ;
	public final EugeneParser.expr_return expr(boolean defer) throws RecognitionException {
		EugeneParser.expr_return retval = new EugeneParser.expr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token op=null;
		ParserRuleReturnScope e =null;

		Object op_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2313:2: (e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2313:4: e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_multExpr_in_expr4361);
			e=multExpr(defer);
			state._fsp--;

			adaptor.addChild(root_0, e.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    if(null != (e!=null?((EugeneParser.multExpr_return)e).p:null)) {
			        retval.p = copyVariable((e!=null?((EugeneParser.multExpr_return)e).p:null));
			    
			        retval.instance = (e!=null?((EugeneParser.multExpr_return)e).instance:null);
			        retval.index = (e!=null?((EugeneParser.multExpr_return)e).index:0);
			        if ((e!=null?((EugeneParser.multExpr_return)e).listAddress:null) != null) {
			            retval.listAddress = (e!=null?((EugeneParser.multExpr_return)e).listAddress:null);
			        }
			        retval.primVariable = (e!=null?((EugeneParser.multExpr_return)e).primVariable:null);

			    } else if((e!=null?((EugeneParser.multExpr_return)e).element:null) != null && !((e!=null?((EugeneParser.multExpr_return)e).element:null) instanceof Variable) && !((e!=null?((EugeneParser.multExpr_return)e).element:null) instanceof PropertyValue)) { 			
			        retval.element = (e!=null?((EugeneParser.multExpr_return)e).element:null);
			    } else {
			        retval.element = null;
			    }
			}
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2331:5: (op= ( PLUS | MINUS ) e= multExpr[defer] )*
			loop82:
			while (true) {
				int alt82=2;
				int LA82_0 = input.LA(1);
				if ( (LA82_0==MINUS||LA82_0==PLUS) ) {
					alt82=1;
				}

				switch (alt82) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2331:6: op= ( PLUS | MINUS ) e= multExpr[defer]
					{
					op=input.LT(1);
					if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(op));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_multExpr_in_expr4378);
					e=multExpr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(null != retval.element) {
					            if(null != (e!=null?((EugeneParser.multExpr_return)e).element:null)) {
					                retval.element = this.interp.doMinPlusOp((e!=null?((EugeneParser.multExpr_return)e).element:null), retval.element, (op!=null?op.getText():null));                
					            } else if(null != (e!=null?((EugeneParser.multExpr_return)e).p:null)) {
					                retval.element = this.interp.doMinPlusOp((e!=null?((EugeneParser.multExpr_return)e).p:null), retval.element, (op!=null?op.getText():null));
					            }
					            retval.p = null;            
					        } else {        
					            if(null != (e!=null?((EugeneParser.multExpr_return)e).element:null)) {
					                this.interp.doMinPlusOp((e!=null?((EugeneParser.multExpr_return)e).element:null), retval.p, (op!=null?op.getText():null));
					            } else {
					                this.interp.doMinPlusOp((e!=null?((EugeneParser.multExpr_return)e).p:null), retval.p, (op!=null?op.getText():null)); 
					            }
					            retval.element = null;
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}
						
					}
					break;

				default :
					break loop82;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class multExpr_return extends ParserRuleReturnScope {
		public Variable p;
		public String instance;
		public int index;
		public String listAddress;
		public Variable primVariable;
		public NamedElement element;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multExpr"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2370:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* ;
	public final EugeneParser.multExpr_return multExpr(boolean defer) throws RecognitionException {
		EugeneParser.multExpr_return retval = new EugeneParser.multExpr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token mul=null;
		Token div=null;
		ParserRuleReturnScope e =null;

		Object mul_tree=null;
		Object div_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2372:2: (e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2372:4: e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_atom_in_multExpr4408);
			e=atom(defer);
			state._fsp--;

			adaptor.addChild(root_0, e.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    if( null != (e!=null?((EugeneParser.atom_return)e).p:null)) {
			        retval.p = copyVariable((e!=null?((EugeneParser.atom_return)e).p:null));
			        if ((e!=null?((EugeneParser.atom_return)e).instance:null) != null) {
			            retval.instance = (e!=null?((EugeneParser.atom_return)e).instance:null);
			        }
			    
			        retval.index = (e!=null?((EugeneParser.atom_return)e).index:0);
			        if ((e!=null?((EugeneParser.atom_return)e).listAddress:null) != null) {
			            retval.listAddress = (e!=null?((EugeneParser.atom_return)e).listAddress:null);
			        }
			        retval.primVariable = (e!=null?((EugeneParser.atom_return)e).primVariable:null);

			    } else if((e!=null?((EugeneParser.atom_return)e).element:null) != null && !((e!=null?((EugeneParser.atom_return)e).element:null) instanceof Variable) && !((e!=null?((EugeneParser.atom_return)e).element:null) instanceof PropertyValue)) { 			
			        retval.element = (e!=null?((EugeneParser.atom_return)e).element:null);
			    } else {
			        retval.element = null;
			    }
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2392:5: ( (mul= MULT |div= DIV ) e= atom[defer] )*
			loop84:
			while (true) {
				int alt84=2;
				int LA84_0 = input.LA(1);
				if ( (LA84_0==DIV||LA84_0==MULT) ) {
					alt84=1;
				}

				switch (alt84) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2392:7: (mul= MULT |div= DIV ) e= atom[defer]
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2392:7: (mul= MULT |div= DIV )
					int alt83=2;
					int LA83_0 = input.LA(1);
					if ( (LA83_0==MULT) ) {
						alt83=1;
					}
					else if ( (LA83_0==DIV) ) {
						alt83=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 83, 0, input);
						throw nvae;
					}

					switch (alt83) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2392:8: mul= MULT
							{
							mul=(Token)match(input,MULT,FOLLOW_MULT_in_multExpr4419); 
							mul_tree = (Object)adaptor.create(mul);
							adaptor.addChild(root_0, mul_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2392:17: div= DIV
							{
							div=(Token)match(input,DIV,FOLLOW_DIV_in_multExpr4423); 
							div_tree = (Object)adaptor.create(div);
							adaptor.addChild(root_0, div_tree);

							}
							break;

					}

					pushFollow(FOLLOW_atom_in_multExpr4428);
					e=atom(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if ((mul!=null?mul.getText():null) != null) {
					            this.interp.doMultDivOp((e!=null?((EugeneParser.atom_return)e).p:null), retval.p, (mul!=null?mul.getText():null));
					        } else {
					            this.interp.doMultDivOp((e!=null?((EugeneParser.atom_return)e).p:null), retval.p, (div!=null?div.getText():null));
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					    
					    retval.element = null;
					}
						
					}
					break;

				default :
					break loop84;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multExpr"


	public static class atom_return extends ParserRuleReturnScope {
		public Variable p = new Variable();
		public String instance;
		public int index = -1;
		public String listAddress;
		public Variable primVariable;
		public NamedElement element;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2409:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] |fc= function_call[defer] );
	public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
		EugeneParser.atom_return retval = new EugeneParser.atom_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token t=null;
		Token f=null;
		Token MINUS230=null;
		Token STRING231=null;
		Token char_literal232=null;
		Token char_literal234=null;
		Token LEFTSBR235=null;
		Token RIGHTSBR237=null;
		ParserRuleReturnScope dn =null;
		ParserRuleReturnScope oc =null;
		ParserRuleReturnScope bif =null;
		ParserRuleReturnScope fc =null;
		ParserRuleReturnScope expr233 =null;
		ParserRuleReturnScope list236 =null;

		Object n_tree=null;
		Object t_tree=null;
		Object f_tree=null;
		Object MINUS230_tree=null;
		Object STRING231_tree=null;
		Object char_literal232_tree=null;
		Object char_literal234_tree=null;
		Object LEFTSBR235_tree=null;
		Object RIGHTSBR237_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2411:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] |fc= function_call[defer] )
			int alt88=9;
			switch ( input.LA(1) ) {
			case NUMBER:
			case REAL:
				{
				alt88=1;
				}
				break;
			case MINUS:
				{
				alt88=2;
				}
				break;
			case FALSE_LC:
			case FALSE_UC:
			case TRUE_LC:
			case TRUE_UC:
				{
				alt88=3;
				}
				break;
			case ID:
				{
				int LA88_4 = input.LA(2);
				if ( (LA88_4==LEFTP) ) {
					int LA88_10 = input.LA(3);
					if ( (LA88_10==RIGHTP) ) {
						alt88=8;
					}
					else if ( (LA88_10==DOLLAR||(LA88_10 >= EXIT_LC && LA88_10 <= EXIT_UC)||(LA88_10 >= FALSE_LC && LA88_10 <= FALSE_UC)||LA88_10==ID||(LA88_10 >= LEFTP && LA88_10 <= LEFTSBR)||LA88_10==MINUS||LA88_10==NUMBER||LA88_10==PERMUTE||LA88_10==PRODUCT||(LA88_10 >= RANDOM_LC && LA88_10 <= REAL)||(LA88_10 >= SAVE_LC && LA88_10 <= SAVE_UC)||(LA88_10 >= SIZEOF_LC && LA88_10 <= STORE_UC)||(LA88_10 >= STRING && LA88_10 <= TRUE_UC)) ) {
						alt88=9;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 88, 10, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA88_4==EOF||LA88_4==AMP||LA88_4==COMMA||LA88_4==DIV||LA88_4==DOT||LA88_4==EQUALS||LA88_4==GEQUAL||LA88_4==GTHAN||LA88_4==LC_AND||LA88_4==LC_OR||(LA88_4 >= LEFTSBR && LA88_4 <= LEQUAL)||(LA88_4 >= LOG_AND && LA88_4 <= MINUS)||(LA88_4 >= MULT && LA88_4 <= NEQUAL)||(LA88_4 >= PIPE && LA88_4 <= PLUS)||(LA88_4 >= RIGHTCUR && LA88_4 <= RIGHTSBR)||LA88_4==SEMIC||LA88_4==UC_AND||LA88_4==UC_OR||LA88_4==133||(LA88_4 >= 135 && LA88_4 <= 136)||LA88_4==139||(LA88_4 >= 142 && LA88_4 <= 143)||LA88_4==145||(LA88_4 >= 158 && LA88_4 <= 159)||LA88_4==172||(LA88_4 >= 174 && LA88_4 <= 175)||LA88_4==178||(LA88_4 >= 181 && LA88_4 <= 182)||LA88_4==184||(LA88_4 >= 197 && LA88_4 <= 198)) ) {
					alt88=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 88, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DOLLAR:
				{
				alt88=4;
				}
				break;
			case STRING:
				{
				alt88=5;
				}
				break;
			case LEFTP:
				{
				alt88=6;
				}
				break;
			case LEFTSBR:
				{
				alt88=7;
				}
				break;
			case EXIT_LC:
			case EXIT_UC:
			case PERMUTE:
			case PRODUCT:
			case RANDOM_LC:
			case RANDOM_UC:
			case SAVE_LC:
			case SAVE_UC:
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_UC:
			case STORE_LC:
			case STORE_UC:
				{
				alt88=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 88, 0, input);
				throw nvae;
			}
			switch (alt88) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2411:4: (n= NUMBER |n= REAL )
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2411:4: (n= NUMBER |n= REAL )
					int alt85=2;
					int LA85_0 = input.LA(1);
					if ( (LA85_0==NUMBER) ) {
						alt85=1;
					}
					else if ( (LA85_0==REAL) ) {
						alt85=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 85, 0, input);
						throw nvae;
					}

					switch (alt85) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2411:5: n= NUMBER
							{
							n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4455); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2411:16: n= REAL
							{
							n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4461); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							}
							break;

					}


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p.num = Double.parseDouble((n!=null?n.getText():null));
								retval.p.type = EugeneConstants.NUM;
								
								retval.element = null;
							}
							
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2420:4: MINUS (n= NUMBER |n= REAL )
					{
					root_0 = (Object)adaptor.nil();


					MINUS230=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom4471); 
					MINUS230_tree = (Object)adaptor.create(MINUS230);
					adaptor.addChild(root_0, MINUS230_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2420:10: (n= NUMBER |n= REAL )
					int alt86=2;
					int LA86_0 = input.LA(1);
					if ( (LA86_0==NUMBER) ) {
						alt86=1;
					}
					else if ( (LA86_0==REAL) ) {
						alt86=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 86, 0, input);
						throw nvae;
					}

					switch (alt86) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2420:11: n= NUMBER
							{
							n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4476); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2420:22: n= REAL
							{
							n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4482); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							}
							break;

					}


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p.num = Double.parseDouble((n!=null?n.getText():null)) * -1.0;
								retval.p.type = EugeneConstants.NUM;

								retval.element = null;
							}
							
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2429:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2429:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
					int alt87=2;
					int LA87_0 = input.LA(1);
					if ( ((LA87_0 >= TRUE_LC && LA87_0 <= TRUE_UC)) ) {
						alt87=1;
					}
					else if ( ((LA87_0 >= FALSE_LC && LA87_0 <= FALSE_UC)) ) {
						alt87=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 87, 0, input);
						throw nvae;
					}

					switch (alt87) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2429:5: t= ( TRUE_LC | TRUE_UC )
							{
							t=input.LT(1);
							if ( (input.LA(1) >= TRUE_LC && input.LA(1) <= TRUE_UC) ) {
								input.consume();
								adaptor.addChild(root_0, (Object)adaptor.create(t));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2429:27: f= ( FALSE_LC | FALSE_UC )
							{
							f=input.LT(1);
							if ( (input.LA(1) >= FALSE_LC && input.LA(1) <= FALSE_UC) ) {
								input.consume();
								adaptor.addChild(root_0, (Object)adaptor.create(f));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							}
							break;

					}


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p.type = EugeneConstants.BOOLEAN;
								if (t != null) {
									retval.p.bool = true;
								} else {
									retval.p.bool = false;
								}

								retval.element = null;
							}
							
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2442:4: dn= dynamic_naming[defer] oc= object_access[defer, $element]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_dynamic_naming_in_atom4521);
					dn=dynamic_naming(defer);
					state._fsp--;

					adaptor.addChild(root_0, dn.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.element = this.interp.get((dn!=null?((EugeneParser.dynamic_naming_return)dn).name:null));
										
					        if(null != retval.element) {
					            if(retval.element instanceof Variable) {
					                retval.p = copyVariable((Variable)retval.element);
					                retval.primVariable = (Variable)retval.element;
					            }
					        } else {
					            throw new EugeneException((dn!=null?((EugeneParser.dynamic_naming_return)dn).name:null) + " is not declared.");
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}
						
					pushFollow(FOLLOW_object_access_in_atom4528);
					oc=object_access(defer, retval.element);
					state._fsp--;

					adaptor.addChild(root_0, oc.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.element = (oc!=null?((EugeneParser.object_access_return)oc).child:null);
					    
					    if(retval.element instanceof Variable) {
					        retval.p = (Variable)retval.element;
					        retval.element = null;
					    } else if(retval.element instanceof PropertyValue) {
					        retval.p = this.interp.convertPropertyValueToVariable((PropertyValue)retval.element);
					        retval.element = null;
					    } else {
					        retval.p = null;
					    }
					}		
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2474:4: STRING
					{
					root_0 = (Object)adaptor.nil();


					STRING231=(Token)match(input,STRING,FOLLOW_STRING_in_atom4537); 
					STRING231_tree = (Object)adaptor.create(STRING231);
					adaptor.addChild(root_0, STRING231_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p.type = EugeneConstants.TXT;
								retval.p.txt = (STRING231!=null?STRING231.getText():null).substring(1, (STRING231!=null?STRING231.getText():null).length()-1);

								retval.element = null;
							}
						
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2483:4: '(' expr[defer] ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal232=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4545); 
					char_literal232_tree = (Object)adaptor.create(char_literal232);
					adaptor.addChild(root_0, char_literal232_tree);

					pushFollow(FOLLOW_expr_in_atom4547);
					expr233=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, expr233.getTree());

					char_literal234=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4550); 
					char_literal234_tree = (Object)adaptor.create(char_literal234);
					adaptor.addChild(root_0, char_literal234_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p = (expr233!=null?((EugeneParser.expr_return)expr233).p:null);
								retval.primVariable = (expr233!=null?((EugeneParser.expr_return)expr233).primVariable:null);
								retval.element = (expr233!=null?((EugeneParser.expr_return)expr233).element:null);
							}
						
					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2491:5: LEFTSBR list[defer] RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					LEFTSBR235=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4559); 
					LEFTSBR235_tree = (Object)adaptor.create(LEFTSBR235);
					adaptor.addChild(root_0, LEFTSBR235_tree);

					pushFollow(FOLLOW_list_in_atom4561);
					list236=list(defer);
					state._fsp--;

					adaptor.addChild(root_0, list236.getTree());

					RIGHTSBR237=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4564); 
					RIGHTSBR237_tree = (Object)adaptor.create(RIGHTSBR237);
					adaptor.addChild(root_0, RIGHTSBR237_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p = (list236!=null?((EugeneParser.list_return)list236).listPrim:null);
								retval.primVariable = (list236!=null?((EugeneParser.list_return)list236).listPrim:null);
								typeList="";
							}
						
					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2499:4: bif= built_in_function[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_built_in_function_in_atom4574);
					bif=built_in_function(defer);
					state._fsp--;

					adaptor.addChild(root_0, bif.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && null != (bif!=null?((EugeneParser.built_in_function_return)bif).element:null)) {
					    if((bif!=null?((EugeneParser.built_in_function_return)bif).element:null) instanceof Variable) {
					        retval.p = (Variable)(bif!=null?((EugeneParser.built_in_function_return)bif).element:null);
					        retval.element = null;
					    } else {
					        retval.element = (bif!=null?((EugeneParser.built_in_function_return)bif).element:null);
					        retval.p = null;
					    }
					}		
						
					}
					break;
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2510:4: fc= function_call[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_function_call_in_atom4584);
					fc=function_call(defer);
					state._fsp--;

					adaptor.addChild(root_0, fc.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && null != (fc!=null?((EugeneParser.function_call_return)fc).e:null)) {
					    if((fc!=null?((EugeneParser.function_call_return)fc).e:null) instanceof Variable) {
					        retval.p = (Variable)(fc!=null?((EugeneParser.function_call_return)fc).e:null);
					        retval.element = null;
					    } else {
					        retval.element = (fc!=null?((EugeneParser.function_call_return)fc).e:null);
					        retval.p = null;
					    }
					}		
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"


	public static class list_return extends ParserRuleReturnScope {
		public Variable listPrim;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "list"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2523:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
	public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
		EugeneParser.list_return retval = new EugeneParser.list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA238=null;
		ParserRuleReturnScope str1 =null;
		ParserRuleReturnScope str2 =null;

		Object COMMA238_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2525:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2525:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_list4607);
			str1=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, str1.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING){
			    if (null != (str1!=null?((EugeneParser.expr_return)str1).p:null)) {
			        retval.listPrim = new Variable();
			        if(EugeneConstants.NUM.equals(((str1!=null?((EugeneParser.expr_return)str1).p:null)).getType())) {
			            retval.listPrim.type =  EugeneConstants.NUMLIST;
			            typeList = EugeneConstants.NUM;
			        } else if(EugeneConstants.TXT.equals(((str1!=null?((EugeneParser.expr_return)str1).p:null)).getType())) {
			            retval.listPrim.type =  EugeneConstants.TXTLIST;
			            typeList = EugeneConstants.TXT;
			        } else {
			            printError("Only arrays of num and txt primitives are supported!");
			        }
			        
			        addToListPrim((str1!=null?((EugeneParser.expr_return)str1).p:null), retval.listPrim);
			    } else {
			        printError("Only arrays of num and txt primitives are supported!");
			    }
			}
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2544:5: ( COMMA str2= expr[defer] )*
			loop89:
			while (true) {
				int alt89=2;
				int LA89_0 = input.LA(1);
				if ( (LA89_0==COMMA) ) {
					alt89=1;
				}

				switch (alt89) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2544:6: COMMA str2= expr[defer]
					{
					COMMA238=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4614); 
					COMMA238_tree = (Object)adaptor.create(COMMA238);
					adaptor.addChild(root_0, COMMA238_tree);

					pushFollow(FOLLOW_expr_in_list4618);
					str2=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, str2.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    if(null != (str2!=null?((EugeneParser.expr_return)str2).p:null)) {
					        addToListPrim((str2!=null?((EugeneParser.expr_return)str2).p:null), retval.listPrim);
					    } else {
					        printError("Only arrays of num and txt primitives are supported!");
					    }
					}				
						
					}
					break;

				default :
					break loop89;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "list"


	public static class built_in_function_return extends ParserRuleReturnScope {
		public NamedElement element;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "built_in_function"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2559:1: built_in_function[boolean defer] returns [NamedElement element] : ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP | (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP | ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )? | ID LEFTP RIGHTP );
	public final EugeneParser.built_in_function_return built_in_function(boolean defer) throws RecognitionException {
		EugeneParser.built_in_function_return retval = new EugeneParser.built_in_function_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token pr=null;
		Token pe=null;
		Token idToken=null;
		Token set239=null;
		Token LEFTP240=null;
		Token RIGHTP241=null;
		Token set242=null;
		Token LEFTP243=null;
		Token RIGHTP244=null;
		Token set245=null;
		Token LEFTP246=null;
		Token RIGHTP247=null;
		Token LEFTP248=null;
		Token RIGHTP249=null;
		Token set250=null;
		Token LEFTP251=null;
		Token RIGHTP252=null;
		Token ID253=null;
		Token LEFTP254=null;
		Token RIGHTP255=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope rg =null;
		ParserRuleReturnScope p =null;

		Object pr_tree=null;
		Object pe_tree=null;
		Object idToken_tree=null;
		Object set239_tree=null;
		Object LEFTP240_tree=null;
		Object RIGHTP241_tree=null;
		Object set242_tree=null;
		Object LEFTP243_tree=null;
		Object RIGHTP244_tree=null;
		Object set245_tree=null;
		Object LEFTP246_tree=null;
		Object RIGHTP247_tree=null;
		Object LEFTP248_tree=null;
		Object RIGHTP249_tree=null;
		Object set250_tree=null;
		Object LEFTP251_tree=null;
		Object RIGHTP252_tree=null;
		Object ID253_tree=null;
		Object LEFTP254_tree=null;
		Object RIGHTP255_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2561:2: ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP | (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP | ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )? | ID LEFTP RIGHTP )
			int alt92=6;
			switch ( input.LA(1) ) {
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_UC:
				{
				alt92=1;
				}
				break;
			case RANDOM_LC:
			case RANDOM_UC:
				{
				alt92=2;
				}
				break;
			case SAVE_LC:
			case SAVE_UC:
			case STORE_LC:
			case STORE_UC:
				{
				alt92=3;
				}
				break;
			case PERMUTE:
			case PRODUCT:
				{
				alt92=4;
				}
				break;
			case EXIT_LC:
			case EXIT_UC:
				{
				alt92=5;
				}
				break;
			case ID:
				{
				alt92=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 92, 0, input);
				throw nvae;
			}
			switch (alt92) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2561:4: ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set239=input.LT(1);
					if ( (input.LA(1) >= SIZEOF_LC && input.LA(1) <= SIZE_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set239));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP240=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4656); 
					LEFTP240_tree = (Object)adaptor.create(LEFTP240);
					adaptor.addChild(root_0, LEFTP240_tree);

					pushFollow(FOLLOW_expr_in_built_in_function4660);
					e=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());

					RIGHTP241=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4663); 
					RIGHTP241_tree = (Object)adaptor.create(RIGHTP241);
					adaptor.addChild(root_0, RIGHTP241_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(null != (e!=null?((EugeneParser.expr_return)e).element:null)) {
					            retval.element = this.interp.getSizeOf((e!=null?((EugeneParser.expr_return)e).element:null));
					        } else if(null != (e!=null?((EugeneParser.expr_return)e).p:null)) {
					            retval.element = this.interp.getSizeOf((e!=null?((EugeneParser.expr_return)e).p:null));
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2574:4: ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set242=input.LT(1);
					if ( (input.LA(1) >= RANDOM_LC && input.LA(1) <= RANDOM_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set242));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP243=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4676); 
					LEFTP243_tree = (Object)adaptor.create(LEFTP243);
					adaptor.addChild(root_0, LEFTP243_tree);

					pushFollow(FOLLOW_range_in_built_in_function4680);
					rg=range(defer);
					state._fsp--;

					adaptor.addChild(root_0, rg.getTree());

					RIGHTP244=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4683); 
					RIGHTP244_tree = (Object)adaptor.create(RIGHTP244);
					adaptor.addChild(root_0, RIGHTP244_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.element = this.interp.getRandom(
					                          (rg!=null?((EugeneParser.range_return)rg).sor:null), 
					                          (rg!=null?((EugeneParser.range_return)rg).eor:null));
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2585:4: ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set245=input.LT(1);
					if ( (input.LA(1) >= SAVE_LC && input.LA(1) <= SAVE_UC)||(input.LA(1) >= STORE_LC && input.LA(1) <= STORE_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set245));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP246=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4700); 
					LEFTP246_tree = (Object)adaptor.create(LEFTP246);
					adaptor.addChild(root_0, LEFTP246_tree);

					pushFollow(FOLLOW_expr_in_built_in_function4704);
					e=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());

					RIGHTP247=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4707); 
					RIGHTP247_tree = (Object)adaptor.create(RIGHTP247);
					adaptor.addChild(root_0, RIGHTP247_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try  {
					        if(null != (e!=null?((EugeneParser.expr_return)e).element:null)) {
					            this.interp.storeIntoLibrary((e!=null?((EugeneParser.expr_return)e).element:null));
					        } else {
					            throw new EugeneException("Cannot store " + (e!=null?input.toString(e.start,e.stop):null) + " into the library!");
					        }
					        retval.element = null;
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:4: (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:4: (pr= PRODUCT |pe= PERMUTE )
					int alt90=2;
					int LA90_0 = input.LA(1);
					if ( (LA90_0==PRODUCT) ) {
						alt90=1;
					}
					else if ( (LA90_0==PERMUTE) ) {
						alt90=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 90, 0, input);
						throw nvae;
					}

					switch (alt90) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:5: pr= PRODUCT
							{
							pr=(Token)match(input,PRODUCT,FOLLOW_PRODUCT_in_built_in_function4717); 
							pr_tree = (Object)adaptor.create(pr);
							adaptor.addChild(root_0, pr_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:16: pe= PERMUTE
							{
							pe=(Token)match(input,PERMUTE,FOLLOW_PERMUTE_in_built_in_function4721); 
							pe_tree = (Object)adaptor.create(pe);
							adaptor.addChild(root_0, pe_tree);

							}
							break;

					}

					LEFTP248=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4724); 
					LEFTP248_tree = (Object)adaptor.create(LEFTP248);
					adaptor.addChild(root_0, LEFTP248_tree);

					idToken=(Token)match(input,ID,FOLLOW_ID_in_built_in_function4728); 
					idToken_tree = (Object)adaptor.create(idToken);
					adaptor.addChild(root_0, idToken_tree);

					RIGHTP249=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4730); 
					RIGHTP249_tree = (Object)adaptor.create(RIGHTP249);
					adaptor.addChild(root_0, RIGHTP249_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					    
					        if(!this.interp.contains((idToken!=null?idToken.getText():null))) {
					            printError((idToken!=null?idToken.getText():null)+" does not exists.");
					        } 
					        NamedElement ne = this.interp.get((idToken!=null?idToken.getText():null));
					        if(!(ne instanceof Device)) {
					            printError((idToken!=null?idToken.getText():null)+" is not a Device.");
					        }
					        
					        if(pr != null) {
					            retval.element = this.interp.product((Device)ne);
					        } else {
					            retval.element = this.interp.permute((Device)ne);
					        }
					    } catch(Exception ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2621:4: ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )?
					{
					root_0 = (Object)adaptor.nil();


					set250=input.LT(1);
					if ( (input.LA(1) >= EXIT_LC && input.LA(1) <= EXIT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set250));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2621:24: ( LEFTP p= toPrint[defer] RIGHTP )?
					int alt91=2;
					int LA91_0 = input.LA(1);
					if ( (LA91_0==LEFTP) ) {
						alt91=1;
					}
					switch (alt91) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2621:25: LEFTP p= toPrint[defer] RIGHTP
							{
							LEFTP251=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4746); 
							LEFTP251_tree = (Object)adaptor.create(LEFTP251);
							adaptor.addChild(root_0, LEFTP251_tree);

							pushFollow(FOLLOW_toPrint_in_built_in_function4750);
							p=toPrint(defer);
							state._fsp--;

							adaptor.addChild(root_0, p.getTree());

							RIGHTP252=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4753); 
							RIGHTP252_tree = (Object)adaptor.create(RIGHTP252);
							adaptor.addChild(root_0, RIGHTP252_tree);

							}
							break;

					}


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    if(null == p) {
					        printError("exiting...");
					    } else {
					        printError((p!=null?((EugeneParser.toPrint_return)p).sb:null));
					    }
					}
						
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2630:4: ID LEFTP RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					ID253=(Token)match(input,ID,FOLLOW_ID_in_built_in_function4762); 
					ID253_tree = (Object)adaptor.create(ID253);
					adaptor.addChild(root_0, ID253_tree);

					LEFTP254=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4764); 
					LEFTP254_tree = (Object)adaptor.create(LEFTP254);
					adaptor.addChild(root_0, LEFTP254_tree);

					RIGHTP255=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4766); 
					RIGHTP255_tree = (Object)adaptor.create(RIGHTP255);
					adaptor.addChild(root_0, RIGHTP255_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "built_in_function"


	public static class range_return extends ParserRuleReturnScope {
		public Variable sor;
		public Variable eor;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "range"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2633:1: range[boolean defer] returns [Variable sor, Variable eor] : s= expr[defer] COMMA e= expr[defer] ;
	public final EugeneParser.range_return range(boolean defer) throws RecognitionException {
		EugeneParser.range_return retval = new EugeneParser.range_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA256=null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope e =null;

		Object COMMA256_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2635:2: (s= expr[defer] COMMA e= expr[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2635:4: s= expr[defer] COMMA e= expr[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_range4786);
			s=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, s.getTree());

			COMMA256=(Token)match(input,COMMA,FOLLOW_COMMA_in_range4789); 
			COMMA256_tree = (Object)adaptor.create(COMMA256);
			adaptor.addChild(root_0, COMMA256_tree);

			pushFollow(FOLLOW_expr_in_range4793);
			e=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, e.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

			    if(null != (s!=null?((EugeneParser.expr_return)s).p:null)) {
			        if(!EugeneConstants.NUM.equals((s!=null?((EugeneParser.expr_return)s).p:null).getType())) {
			            printError("Invalid start of range!");
			        }
			        if((s!=null?((EugeneParser.expr_return)s).p:null).getNum() % 1 != 0) {
			            printError("The start of the range is not an integer!");
			        }    
			        retval.sor = (s!=null?((EugeneParser.expr_return)s).p:null);
			    }  
			   
			    if(null != (e!=null?((EugeneParser.expr_return)e).p:null)) {
			        if(!EugeneConstants.NUM.equals((e!=null?((EugeneParser.expr_return)e).p:null).getType())) {
			            printError("Invalid end of range!");
			        }
			        if((e!=null?((EugeneParser.expr_return)e).p:null).getNum() % 1 != 0) {
			            printError("The end of the range is not an integer!");
			        }    
			        
			        retval.eor = (e!=null?((EugeneParser.expr_return)e).p:null);
			    }
			    
			    if(retval.sor.num > retval.eor.num) {
			        printError("Invalid range!");
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "range"


	public static class object_access_return extends ParserRuleReturnScope {
		public NamedElement child;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "object_access"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2688:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
	public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
		EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token id=null;
		Token DOT257=null;
		Token set258=null;
		Token LEFTP259=null;
		Token RIGHTP260=null;
		Token LEFTSBR261=null;
		Token RIGHTSBR262=null;
		ParserRuleReturnScope exp =null;
		ParserRuleReturnScope o =null;

		Object id_tree=null;
		Object DOT257_tree=null;
		Object set258_tree=null;
		Object LEFTP259_tree=null;
		Object RIGHTP260_tree=null;
		Object LEFTSBR261_tree=null;
		Object RIGHTSBR262_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2690:2: (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
			int alt96=2;
			int LA96_0 = input.LA(1);
			if ( (LA96_0==EOF||LA96_0==AMP||LA96_0==COMMA||LA96_0==DIV||LA96_0==EQUALS||LA96_0==GEQUAL||LA96_0==GTHAN||LA96_0==LC_AND||LA96_0==LC_OR||LA96_0==LEQUAL||(LA96_0 >= LOG_AND && LA96_0 <= MINUS)||(LA96_0 >= MULT && LA96_0 <= NEQUAL)||(LA96_0 >= PIPE && LA96_0 <= PLUS)||(LA96_0 >= RIGHTCUR && LA96_0 <= RIGHTSBR)||LA96_0==SEMIC||LA96_0==UC_AND||LA96_0==UC_OR||LA96_0==133||(LA96_0 >= 135 && LA96_0 <= 136)||LA96_0==139||(LA96_0 >= 142 && LA96_0 <= 143)||LA96_0==145||(LA96_0 >= 158 && LA96_0 <= 159)||LA96_0==172||(LA96_0 >= 174 && LA96_0 <= 175)||LA96_0==178||(LA96_0 >= 181 && LA96_0 <= 182)||LA96_0==184||(LA96_0 >= 197 && LA96_0 <= 198)) ) {
				alt96=1;
			}
			else if ( (LA96_0==DOT||LA96_0==LEFTSBR) ) {
				alt96=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 96, 0, input);
				throw nvae;
			}

			switch (alt96) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2691:2: 
					{
					root_0 = (Object)adaptor.nil();



					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.child = parent;
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2696:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2696:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
					int alt95=2;
					int LA95_0 = input.LA(1);
					if ( (LA95_0==DOT) ) {
						alt95=1;
					}
					else if ( (LA95_0==LEFTSBR) ) {
						alt95=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 95, 0, input);
						throw nvae;
					}

					switch (alt95) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2696:5: DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
							{
							DOT257=(Token)match(input,DOT,FOLLOW_DOT_in_object_access4829); 
							DOT257_tree = (Object)adaptor.create(DOT257);
							adaptor.addChild(root_0, DOT257_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2696:9: (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
							int alt94=2;
							int LA94_0 = input.LA(1);
							if ( (LA94_0==ID) ) {
								alt94=1;
							}
							else if ( ((LA94_0 >= SIZE_LC && LA94_0 <= SIZE_UC)) ) {
								alt94=2;
							}

							else {
								NoViableAltException nvae =
									new NoViableAltException("", 94, 0, input);
								throw nvae;
							}

							switch (alt94) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2696:10: id= ID
									{
									id=(Token)match(input,ID,FOLLOW_ID_in_object_access4834); 
									id_tree = (Object)adaptor.create(id);
									adaptor.addChild(root_0, id_tree);


									if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
									    try {
									    
									        retval.child = parent.getElement((id!=null?id.getText():null));

									        if(null == retval.child) {
									            throw new EugeneException(parent.getName() + " does not contain " + (id!=null?id.getText():null));
									        }

									    } catch(EugeneException ee) {
									        printError(ee.getLocalizedMessage());
									    }
									}	
										
									}
									break;
								case 2 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2710:6: ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )?
									{
									set258=input.LT(1);
									if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
										input.consume();
										adaptor.addChild(root_0, (Object)adaptor.create(set258));
										state.errorRecovery=false;
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										throw mse;
									}
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2710:24: ( LEFTP RIGHTP )?
									int alt93=2;
									int LA93_0 = input.LA(1);
									if ( (LA93_0==LEFTP) ) {
										alt93=1;
									}
									switch (alt93) {
										case 1 :
											// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2710:25: LEFTP RIGHTP
											{
											LEFTP259=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_object_access4847); 
											LEFTP259_tree = (Object)adaptor.create(LEFTP259);
											adaptor.addChild(root_0, LEFTP259_tree);

											RIGHTP260=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_object_access4849); 
											RIGHTP260_tree = (Object)adaptor.create(RIGHTP260);
											adaptor.addChild(root_0, RIGHTP260_tree);

											}
											break;

									}


									if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
									    try {
									        retval.child = this.interp.getSizeOf(parent);
									    } catch(EugeneException ee) {
									        printError(ee.getLocalizedMessage());
									    }
									}	
										
									}
									break;

							}

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2719:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
							{
							LEFTSBR261=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access4859); 
							LEFTSBR261_tree = (Object)adaptor.create(LEFTSBR261);
							adaptor.addChild(root_0, LEFTSBR261_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2719:12: (exp= expr[defer] )
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2719:13: exp= expr[defer]
							{
							pushFollow(FOLLOW_expr_in_object_access4864);
							exp=expr(defer);
							state._fsp--;

							adaptor.addChild(root_0, exp.getTree());

							}

							RIGHTSBR262=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access4868); 
							RIGHTSBR262_tree = (Object)adaptor.create(RIGHTSBR262);
							adaptor.addChild(root_0, RIGHTSBR262_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
							    try {
							        if(null != (exp!=null?((EugeneParser.expr_return)exp).p:null) && EugeneConstants.NUM.equals((exp!=null?((EugeneParser.expr_return)exp).p:null).getType())) {
							        
							            if((exp!=null?((EugeneParser.expr_return)exp).p:null).getNum() % 1 != 0 && (exp!=null?((EugeneParser.expr_return)exp).p:null).getNum() < 0) {
							                throw new EugeneException("Invalid index " + (exp!=null?((EugeneParser.expr_return)exp).p:null) + "!");
							            }
							            
							            retval.child = parent.getElement((int)((exp!=null?((EugeneParser.expr_return)exp).p:null).getNum()));
							            
							            if(null == retval.child) {
							                throw new EugeneException(parent.getName() + " does not contain " + (id!=null?id.getText():null));
							            }
							            
							        } else {
							            throw new EugeneException("Invalid index " + (exp!=null?((EugeneParser.expr_return)exp).p:null) + "!");
							        }
							    } catch(EugeneException ee) {
							        printError(ee.getLocalizedMessage());
							    }
							}	
								
							}
							break;

					}

					pushFollow(FOLLOW_object_access_in_object_access4875);
					o=object_access(defer, retval.child);
					state._fsp--;

					adaptor.addChild(root_0, o.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.child = (o!=null?((EugeneParser.object_access_return)o).child:null);
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "object_access"


	public static class dynamic_naming_return extends ParserRuleReturnScope {
		public String name;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "dynamic_naming"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2751:1: dynamic_naming[boolean defer] returns [String name] : (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR );
	public final EugeneParser.dynamic_naming_return dynamic_naming(boolean defer) throws RecognitionException {
		EugeneParser.dynamic_naming_return retval = new EugeneParser.dynamic_naming_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token DOLLAR263=null;
		Token LEFTCUR264=null;
		Token RIGHTCUR265=null;
		ParserRuleReturnScope e =null;

		Object i_tree=null;
		Object DOLLAR263_tree=null;
		Object LEFTCUR264_tree=null;
		Object RIGHTCUR265_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2753:2: (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR )
			int alt97=2;
			int LA97_0 = input.LA(1);
			if ( (LA97_0==ID) ) {
				alt97=1;
			}
			else if ( (LA97_0==DOLLAR) ) {
				alt97=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 97, 0, input);
				throw nvae;
			}

			switch (alt97) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2753:4: i= ID
					{
					root_0 = (Object)adaptor.nil();


					i=(Token)match(input,ID,FOLLOW_ID_in_dynamic_naming4900); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.name = (i!=null?i.getText():null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2758:4: DOLLAR LEFTCUR e= expr[defer] RIGHTCUR
					{
					root_0 = (Object)adaptor.nil();


					DOLLAR263=(Token)match(input,DOLLAR,FOLLOW_DOLLAR_in_dynamic_naming4907); 
					DOLLAR263_tree = (Object)adaptor.create(DOLLAR263);
					adaptor.addChild(root_0, DOLLAR263_tree);

					LEFTCUR264=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_dynamic_naming4909); 
					LEFTCUR264_tree = (Object)adaptor.create(LEFTCUR264);
					adaptor.addChild(root_0, LEFTCUR264_tree);

					pushFollow(FOLLOW_expr_in_dynamic_naming4913);
					e=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());

					RIGHTCUR265=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_dynamic_naming4916); 
					RIGHTCUR265_tree = (Object)adaptor.create(RIGHTCUR265);
					adaptor.addChild(root_0, RIGHTCUR265_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    if((e!=null?((EugeneParser.expr_return)e).p:null) == null) {
					        printError("Invalid name!");
					    } else if(!EugeneConstants.TXT.equals(((e!=null?((EugeneParser.expr_return)e).p:null)).getType())) {
					        printError("The name must be of type txt!");
					    }
					    
					    retval.name = ((e!=null?((EugeneParser.expr_return)e).p:null)).getTxt();
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dynamic_naming"


	public static class dataExchange_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "dataExchange"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2775:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |i= importStatement[defer] |g= genbankStatement[defer] |r= registryStatement[defer] );
	public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
		EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope s =null;
		ParserRuleReturnScope i =null;
		ParserRuleReturnScope g =null;
		ParserRuleReturnScope r =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2777:2: (s= sbolStatement[defer] |i= importStatement[defer] |g= genbankStatement[defer] |r= registryStatement[defer] )
			int alt98=4;
			switch ( input.LA(1) ) {
			case SBOL:
				{
				alt98=1;
				}
				break;
			case IMPORT_LC:
			case IMPORT_UC:
				{
				alt98=2;
				}
				break;
			case GENBANK:
				{
				alt98=3;
				}
				break;
			case REGISTRY:
				{
				alt98=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 98, 0, input);
				throw nvae;
			}
			switch (alt98) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2777:4: s= sbolStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_sbolStatement_in_dataExchange4941);
					s=sbolStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, s.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (s!=null?((EugeneParser.sbolStatement_return)s).e:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2782:4: i= importStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_importStatement_in_dataExchange4951);
					i=importStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, i.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (i!=null?((EugeneParser.importStatement_return)i).e:null);
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2787:4: g= genbankStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_genbankStatement_in_dataExchange4961);
					g=genbankStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, g.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (g!=null?((EugeneParser.genbankStatement_return)g).e:null);
					}	
						
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2793:4: r= registryStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_registryStatement_in_dataExchange4971);
					r=registryStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, r.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (r!=null?((EugeneParser.registryStatement_return)r).e:null);
					}		
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dataExchange"


	public static class includeStatement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "includeStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2801:1: includeStatement[boolean defer] : ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING ;
	public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
		EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token file=null;
		Token HASHMARK266=null;
		Token set267=null;

		Object file_tree=null;
		Object HASHMARK266_tree=null;
		Object set267_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2802:2: ( ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2802:4: ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2802:4: ( HASHMARK )?
			int alt99=2;
			int LA99_0 = input.LA(1);
			if ( (LA99_0==HASHMARK) ) {
				alt99=1;
			}
			switch (alt99) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2802:5: HASHMARK
					{
					HASHMARK266=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement4990); 
					HASHMARK266_tree = (Object)adaptor.create(HASHMARK266);
					adaptor.addChild(root_0, HASHMARK266_tree);

					}
					break;

			}

			set267=input.LT(1);
			if ( (input.LA(1) >= INCLUDE_LC && input.LA(1) <= INCLUDE_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set267));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement5002); 
			file_tree = (Object)adaptor.create(file);
			adaptor.addChild(root_0, file_tree);


			if(this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
			    try {
			        this.interp.includeFile((file!=null?file.getText():null), ParsingPhase.PRE_PROCESSING);
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			} else if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        this.interp.includeFile((file!=null?file.getText():null), ParsingPhase.INTERPRETING);
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "includeStatement"


	public static class importStatement_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "importStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2820:1: importStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP ;
	public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
		EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token file=null;
		Token set268=null;
		Token LEFTP269=null;
		Token RIGHTP270=null;

		Object file_tree=null;
		Object set268_tree=null;
		Object LEFTP269_tree=null;
		Object RIGHTP270_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2822:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2822:4: ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set268=input.LT(1);
			if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set268));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP269=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement5029); 
			LEFTP269_tree = (Object)adaptor.create(LEFTP269);
			adaptor.addChild(root_0, LEFTP269_tree);

			file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement5033); 
			file_tree = (Object)adaptor.create(file);
			adaptor.addChild(root_0, file_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        retval.e = this.interp.importFile((file!=null?file.getText():null));
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}
				
			RIGHTP270=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement5037); 
			RIGHTP270_tree = (Object)adaptor.create(RIGHTP270);
			adaptor.addChild(root_0, RIGHTP270_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "importStatement"


	public static class sbolStatement_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "sbolStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2834:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] ) ;
	public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SBOL271=null;
		Token DOT272=null;
		ParserRuleReturnScope i =null;
		ParserRuleReturnScope sbolExportStatement273 =null;
		ParserRuleReturnScope sbolVisualStatement274 =null;

		Object SBOL271_tree=null;
		Object DOT272_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2836:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2836:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] )
			{
			root_0 = (Object)adaptor.nil();


			SBOL271=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement5059); 
			SBOL271_tree = (Object)adaptor.create(SBOL271);
			adaptor.addChild(root_0, SBOL271_tree);

			DOT272=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement5061); 
			DOT272_tree = (Object)adaptor.create(DOT272);
			adaptor.addChild(root_0, DOT272_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2836:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] )
			int alt100=3;
			switch ( input.LA(1) ) {
			case EXPORT_LC:
			case EXPORT_UC:
				{
				alt100=1;
				}
				break;
			case IMPORT_LC:
			case IMPORT_UC:
				{
				alt100=2;
				}
				break;
			case VISUALIZE_LC:
			case VISUALIZE_UC:
				{
				alt100=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 100, 0, input);
				throw nvae;
			}
			switch (alt100) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2836:14: sbolExportStatement[defer]
					{
					pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement5064);
					sbolExportStatement273=sbolExportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, sbolExportStatement273.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2836:43: i= sbolImportStatement[defer]
					{
					pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement5071);
					i=sbolImportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, i.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (i!=null?((EugeneParser.sbolImportStatement_return)i).e:null);
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2840:7: sbolVisualStatement[defer]
					{
					pushFollow(FOLLOW_sbolVisualStatement_in_sbolStatement5079);
					sbolVisualStatement274=sbolVisualStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, sbolVisualStatement274.getTree());


						
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sbolStatement"


	public static class sbolExportStatement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "sbolExportStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2845:1: sbolExportStatement[boolean defer] : ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
	public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token idToken=null;
		Token filenameToken=null;
		Token set275=null;
		Token LEFTP276=null;
		Token COMMA277=null;
		Token RIGHTP278=null;

		Object idToken_tree=null;
		Object filenameToken_tree=null;
		Object set275_tree=null;
		Object LEFTP276_tree=null;
		Object COMMA277_tree=null;
		Object RIGHTP278_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2846:2: ( ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2846:4: ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set275=input.LT(1);
			if ( (input.LA(1) >= EXPORT_LC && input.LA(1) <= EXPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set275));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP276=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement5102); 
			LEFTP276_tree = (Object)adaptor.create(LEFTP276);
			adaptor.addChild(root_0, LEFTP276_tree);

			idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement5106); 
			idToken_tree = (Object)adaptor.create(idToken);
			adaptor.addChild(root_0, idToken_tree);

			COMMA277=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement5108); 
			COMMA277_tree = (Object)adaptor.create(COMMA277);
			adaptor.addChild(root_0, COMMA277_tree);

			filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement5112); 
			filenameToken_tree = (Object)adaptor.create(filenameToken);
			adaptor.addChild(root_0, filenameToken_tree);

			RIGHTP278=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement5114); 
			RIGHTP278_tree = (Object)adaptor.create(RIGHTP278);
			adaptor.addChild(root_0, RIGHTP278_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        this.interp.exportToSBOL(
			            (idToken!=null?idToken.getText():null), 
			            (filenameToken!=null?filenameToken.getText():null).substring(1, (filenameToken!=null?filenameToken.getText():null).length()-1));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sbolExportStatement"


	public static class sbolImportStatement_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "sbolImportStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2859:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP ;
	public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token fileToken=null;
		Token set279=null;
		Token LEFTP280=null;
		Token RIGHTP281=null;

		Object fileToken_tree=null;
		Object set279_tree=null;
		Object LEFTP280_tree=null;
		Object RIGHTP281_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2861:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2861:4: ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set279=input.LT(1);
			if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set279));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP280=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement5143); 
			LEFTP280_tree = (Object)adaptor.create(LEFTP280);
			adaptor.addChild(root_0, LEFTP280_tree);

			fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement5147); 
			fileToken_tree = (Object)adaptor.create(fileToken);
			adaptor.addChild(root_0, fileToken_tree);

			RIGHTP281=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement5149); 
			RIGHTP281_tree = (Object)adaptor.create(RIGHTP281);
			adaptor.addChild(root_0, RIGHTP281_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        retval.e = this.interp.importSBOL((fileToken!=null?fileToken.getText():null));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sbolImportStatement"


	public static class sbolVisualStatement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "sbolVisualStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2872:1: sbolVisualStatement[boolean defer] : ( VISUALIZE_LC | VISUALIZE_UC ) LEFTP idToken= ID RIGHTP ;
	public final EugeneParser.sbolVisualStatement_return sbolVisualStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolVisualStatement_return retval = new EugeneParser.sbolVisualStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token idToken=null;
		Token set282=null;
		Token LEFTP283=null;
		Token RIGHTP284=null;

		Object idToken_tree=null;
		Object set282_tree=null;
		Object LEFTP283_tree=null;
		Object RIGHTP284_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2873:2: ( ( VISUALIZE_LC | VISUALIZE_UC ) LEFTP idToken= ID RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2873:4: ( VISUALIZE_LC | VISUALIZE_UC ) LEFTP idToken= ID RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set282=input.LT(1);
			if ( (input.LA(1) >= VISUALIZE_LC && input.LA(1) <= VISUALIZE_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set282));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP283=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolVisualStatement5171); 
			LEFTP283_tree = (Object)adaptor.create(LEFTP283);
			adaptor.addChild(root_0, LEFTP283_tree);

			idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolVisualStatement5175); 
			idToken_tree = (Object)adaptor.create(idToken);
			adaptor.addChild(root_0, idToken_tree);

			RIGHTP284=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolVisualStatement5177); 
			RIGHTP284_tree = (Object)adaptor.create(RIGHTP284);
			adaptor.addChild(root_0, RIGHTP284_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        this.interp.visualSBOL((idToken!=null?idToken.getText():null));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }
			}		
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sbolVisualStatement"


	public static class genbankStatement_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "genbankStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2887:1: genbankStatement[boolean defer] returns [NamedElement e] : GENBANK DOT (i= genbankImportStatement[defer] | genbankExportStatement[defer] ) ;
	public final EugeneParser.genbankStatement_return genbankStatement(boolean defer) throws RecognitionException {
		EugeneParser.genbankStatement_return retval = new EugeneParser.genbankStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token GENBANK285=null;
		Token DOT286=null;
		ParserRuleReturnScope i =null;
		ParserRuleReturnScope genbankExportStatement287 =null;

		Object GENBANK285_tree=null;
		Object DOT286_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2889:2: ( GENBANK DOT (i= genbankImportStatement[defer] | genbankExportStatement[defer] ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2889:4: GENBANK DOT (i= genbankImportStatement[defer] | genbankExportStatement[defer] )
			{
			root_0 = (Object)adaptor.nil();


			GENBANK285=(Token)match(input,GENBANK,FOLLOW_GENBANK_in_genbankStatement5202); 
			GENBANK285_tree = (Object)adaptor.create(GENBANK285);
			adaptor.addChild(root_0, GENBANK285_tree);

			DOT286=(Token)match(input,DOT,FOLLOW_DOT_in_genbankStatement5204); 
			DOT286_tree = (Object)adaptor.create(DOT286);
			adaptor.addChild(root_0, DOT286_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2889:16: (i= genbankImportStatement[defer] | genbankExportStatement[defer] )
			int alt101=2;
			int LA101_0 = input.LA(1);
			if ( ((LA101_0 >= IMPORT_LC && LA101_0 <= IMPORT_UC)) ) {
				alt101=1;
			}
			else if ( ((LA101_0 >= EXPORT_LC && LA101_0 <= EXPORT_UC)) ) {
				alt101=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 101, 0, input);
				throw nvae;
			}

			switch (alt101) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2889:17: i= genbankImportStatement[defer]
					{
					pushFollow(FOLLOW_genbankImportStatement_in_genbankStatement5209);
					i=genbankImportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, i.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (i!=null?((EugeneParser.genbankImportStatement_return)i).e:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2894:4: genbankExportStatement[defer]
					{
					pushFollow(FOLLOW_genbankExportStatement_in_genbankStatement5217);
					genbankExportStatement287=genbankExportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, genbankExportStatement287.getTree());

					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.e = null;
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "genbankStatement"


	public static class genbankExportStatement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "genbankExportStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2901:1: genbankExportStatement[boolean defer] : ( EXPORT_UC | EXPORT_LC ) LEFTP RIGHTP ;
	public final EugeneParser.genbankExportStatement_return genbankExportStatement(boolean defer) throws RecognitionException {
		EugeneParser.genbankExportStatement_return retval = new EugeneParser.genbankExportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set288=null;
		Token LEFTP289=null;
		Token RIGHTP290=null;

		Object set288_tree=null;
		Object LEFTP289_tree=null;
		Object RIGHTP290_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2902:2: ( ( EXPORT_UC | EXPORT_LC ) LEFTP RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2902:4: ( EXPORT_UC | EXPORT_LC ) LEFTP RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set288=input.LT(1);
			if ( (input.LA(1) >= EXPORT_LC && input.LA(1) <= EXPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set288));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP289=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankExportStatement5242); 
			LEFTP289_tree = (Object)adaptor.create(LEFTP289);
			adaptor.addChild(root_0, LEFTP289_tree);

			RIGHTP290=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankExportStatement5244); 
			RIGHTP290_tree = (Object)adaptor.create(RIGHTP290);
			adaptor.addChild(root_0, RIGHTP290_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "genbankExportStatement"


	public static class genbankImportStatement_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "genbankImportStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2905:1: genbankImportStatement[boolean defer] returns [NamedElement e] : ( ( IMPORT_LC | IMPORT_UC ) LEFTP f= STRING RIGHTP | ( IMPORT_LC | IMPORT_UC ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP );
	public final EugeneParser.genbankImportStatement_return genbankImportStatement(boolean defer) throws RecognitionException {
		EugeneParser.genbankImportStatement_return retval = new EugeneParser.genbankImportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token f=null;
		Token typeToken=null;
		Token partToken=null;
		Token set291=null;
		Token LEFTP292=null;
		Token RIGHTP293=null;
		Token set294=null;
		Token LEFTP295=null;
		Token COMMA296=null;
		Token RIGHTP297=null;

		Object f_tree=null;
		Object typeToken_tree=null;
		Object partToken_tree=null;
		Object set291_tree=null;
		Object LEFTP292_tree=null;
		Object RIGHTP293_tree=null;
		Object set294_tree=null;
		Object LEFTP295_tree=null;
		Object COMMA296_tree=null;
		Object RIGHTP297_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2907:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP f= STRING RIGHTP | ( IMPORT_LC | IMPORT_UC ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP )
			int alt102=2;
			int LA102_0 = input.LA(1);
			if ( ((LA102_0 >= IMPORT_LC && LA102_0 <= IMPORT_UC)) ) {
				int LA102_1 = input.LA(2);
				if ( (LA102_1==LEFTP) ) {
					int LA102_2 = input.LA(3);
					if ( (LA102_2==STRING) ) {
						alt102=1;
					}
					else if ( (LA102_2==ID) ) {
						alt102=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 102, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 102, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 102, 0, input);
				throw nvae;
			}

			switch (alt102) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2907:4: ( IMPORT_LC | IMPORT_UC ) LEFTP f= STRING RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set291=input.LT(1);
					if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set291));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP292=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankImportStatement5276); 
					LEFTP292_tree = (Object)adaptor.create(LEFTP292);
					adaptor.addChild(root_0, LEFTP292_tree);

					f=(Token)match(input,STRING,FOLLOW_STRING_in_genbankImportStatement5280); 
					f_tree = (Object)adaptor.create(f);
					adaptor.addChild(root_0, f_tree);

					RIGHTP293=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankImportStatement5282); 
					RIGHTP293_tree = (Object)adaptor.create(RIGHTP293);
					adaptor.addChild(root_0, RIGHTP293_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.e = this.interp.importGenbank((f!=null?f.getText():null));
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    } 
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2916:4: ( IMPORT_LC | IMPORT_UC ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set294=input.LT(1);
					if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set294));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP295=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankImportStatement5295); 
					LEFTP295_tree = (Object)adaptor.create(LEFTP295);
					adaptor.addChild(root_0, LEFTP295_tree);

					typeToken=(Token)match(input,ID,FOLLOW_ID_in_genbankImportStatement5299); 
					typeToken_tree = (Object)adaptor.create(typeToken);
					adaptor.addChild(root_0, typeToken_tree);

					COMMA296=(Token)match(input,COMMA,FOLLOW_COMMA_in_genbankImportStatement5301); 
					COMMA296_tree = (Object)adaptor.create(COMMA296);
					adaptor.addChild(root_0, COMMA296_tree);

					partToken=(Token)match(input,STRING,FOLLOW_STRING_in_genbankImportStatement5305); 
					partToken_tree = (Object)adaptor.create(partToken);
					adaptor.addChild(root_0, partToken_tree);

					RIGHTP297=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankImportStatement5307); 
					RIGHTP297_tree = (Object)adaptor.create(RIGHTP297);
					adaptor.addChild(root_0, RIGHTP297_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    // TODO!!!
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "genbankImportStatement"


	public static class registryStatement_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "registryStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2927:1: registryStatement[boolean defer] returns [NamedElement e] : REGISTRY DOT ( IMPORT_LC | IMPORT_UC ) LEFTP n= STRING RIGHTP ;
	public final EugeneParser.registryStatement_return registryStatement(boolean defer) throws RecognitionException {
		EugeneParser.registryStatement_return retval = new EugeneParser.registryStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token REGISTRY298=null;
		Token DOT299=null;
		Token set300=null;
		Token LEFTP301=null;
		Token RIGHTP302=null;

		Object n_tree=null;
		Object REGISTRY298_tree=null;
		Object DOT299_tree=null;
		Object set300_tree=null;
		Object LEFTP301_tree=null;
		Object RIGHTP302_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2929:2: ( REGISTRY DOT ( IMPORT_LC | IMPORT_UC ) LEFTP n= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2929:4: REGISTRY DOT ( IMPORT_LC | IMPORT_UC ) LEFTP n= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			REGISTRY298=(Token)match(input,REGISTRY,FOLLOW_REGISTRY_in_registryStatement5331); 
			REGISTRY298_tree = (Object)adaptor.create(REGISTRY298);
			adaptor.addChild(root_0, REGISTRY298_tree);

			DOT299=(Token)match(input,DOT,FOLLOW_DOT_in_registryStatement5333); 
			DOT299_tree = (Object)adaptor.create(DOT299);
			adaptor.addChild(root_0, DOT299_tree);

			set300=input.LT(1);
			if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set300));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP301=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_registryStatement5341); 
			LEFTP301_tree = (Object)adaptor.create(LEFTP301);
			adaptor.addChild(root_0, LEFTP301_tree);

			n=(Token)match(input,STRING,FOLLOW_STRING_in_registryStatement5345); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			RIGHTP302=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_registryStatement5347); 
			RIGHTP302_tree = (Object)adaptor.create(RIGHTP302);
			adaptor.addChild(root_0, RIGHTP302_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

			    try {
			        String name = (n!=null?n.getText():null);
			        name = name.substring(1, name.length()-2);
			        retval.e = this.interp.importRegistry(name);
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());    
			    }
			}		
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "registryStatement"


	public static class testStatements_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "testStatements"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2944:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
	public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
		EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token id=null;
		Token n=null;
		Token ASSERT303=null;
		Token LEFTP304=null;
		Token DOT305=null;
		Token set306=null;
		Token LEFTP307=null;
		Token RIGHTP308=null;
		Token EQUALS309=null;
		Token EQUALS310=null;
		Token RIGHTP311=null;
		Token NOTE312=null;
		Token LEFTP313=null;
		Token DOT314=null;
		Token set315=null;
		Token LEFTP316=null;
		Token RIGHTP317=null;
		Token EQUALS318=null;
		Token EQUALS319=null;
		Token RIGHTP320=null;

		Object id_tree=null;
		Object n_tree=null;
		Object ASSERT303_tree=null;
		Object LEFTP304_tree=null;
		Object DOT305_tree=null;
		Object set306_tree=null;
		Object LEFTP307_tree=null;
		Object RIGHTP308_tree=null;
		Object EQUALS309_tree=null;
		Object EQUALS310_tree=null;
		Object RIGHTP311_tree=null;
		Object NOTE312_tree=null;
		Object LEFTP313_tree=null;
		Object DOT314_tree=null;
		Object set315_tree=null;
		Object LEFTP316_tree=null;
		Object RIGHTP317_tree=null;
		Object EQUALS318_tree=null;
		Object EQUALS319_tree=null;
		Object RIGHTP320_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2945:2: (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
			int alt103=3;
			switch ( input.LA(1) ) {
			case EOF:
				{
				alt103=1;
				}
				break;
			case ASSERT:
				{
				alt103=2;
				}
				break;
			case NOTE:
				{
				alt103=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 103, 0, input);
				throw nvae;
			}
			switch (alt103) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2945:5: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2945:7: ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					ASSERT303=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements5366); 
					ASSERT303_tree = (Object)adaptor.create(ASSERT303);
					adaptor.addChild(root_0, ASSERT303_tree);

					LEFTP304=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5368); 
					LEFTP304_tree = (Object)adaptor.create(LEFTP304);
					adaptor.addChild(root_0, LEFTP304_tree);

					id=(Token)match(input,ID,FOLLOW_ID_in_testStatements5372); 
					id_tree = (Object)adaptor.create(id);
					adaptor.addChild(root_0, id_tree);

					DOT305=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements5374); 
					DOT305_tree = (Object)adaptor.create(DOT305);
					adaptor.addChild(root_0, DOT305_tree);

					set306=input.LT(1);
					if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set306));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP307=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5382); 
					LEFTP307_tree = (Object)adaptor.create(LEFTP307);
					adaptor.addChild(root_0, LEFTP307_tree);

					RIGHTP308=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5384); 
					RIGHTP308_tree = (Object)adaptor.create(RIGHTP308);
					adaptor.addChild(root_0, RIGHTP308_tree);

					EQUALS309=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5386); 
					EQUALS309_tree = (Object)adaptor.create(EQUALS309);
					adaptor.addChild(root_0, EQUALS309_tree);

					EQUALS310=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5388); 
					EQUALS310_tree = (Object)adaptor.create(EQUALS310);
					adaptor.addChild(root_0, EQUALS310_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5392); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);

					RIGHTP311=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5394); 
					RIGHTP311_tree = (Object)adaptor.create(RIGHTP311);
					adaptor.addChild(root_0, RIGHTP311_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        NamedElement el = this.interp.get((id!=null?id.getText():null));
					        if(null != el) {
					//            if(el instanceof EugeneCollection) {
					//                if(((EugeneCollection)el).getElements().size() != Integer.parseInt((n!=null?n.getText():null))) {
					//                    printError("TEST NOT PASSED!");
					//                }
					//            }
					    }
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2961:5: NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					NOTE312=(Token)match(input,NOTE,FOLLOW_NOTE_in_testStatements5402); 
					NOTE312_tree = (Object)adaptor.create(NOTE312);
					adaptor.addChild(root_0, NOTE312_tree);

					LEFTP313=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5404); 
					LEFTP313_tree = (Object)adaptor.create(LEFTP313);
					adaptor.addChild(root_0, LEFTP313_tree);

					id=(Token)match(input,ID,FOLLOW_ID_in_testStatements5408); 
					id_tree = (Object)adaptor.create(id);
					adaptor.addChild(root_0, id_tree);

					DOT314=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements5410); 
					DOT314_tree = (Object)adaptor.create(DOT314);
					adaptor.addChild(root_0, DOT314_tree);

					set315=input.LT(1);
					if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set315));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP316=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5418); 
					LEFTP316_tree = (Object)adaptor.create(LEFTP316);
					adaptor.addChild(root_0, LEFTP316_tree);

					RIGHTP317=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5420); 
					RIGHTP317_tree = (Object)adaptor.create(RIGHTP317);
					adaptor.addChild(root_0, RIGHTP317_tree);

					EQUALS318=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5422); 
					EQUALS318_tree = (Object)adaptor.create(EQUALS318);
					adaptor.addChild(root_0, EQUALS318_tree);

					EQUALS319=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5424); 
					EQUALS319_tree = (Object)adaptor.create(EQUALS319);
					adaptor.addChild(root_0, EQUALS319_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5428); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);

					RIGHTP320=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5430); 
					RIGHTP320_tree = (Object)adaptor.create(RIGHTP320);
					adaptor.addChild(root_0, RIGHTP320_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "testStatements"


	public static class function_definition_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_definition"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2970:1: function_definition[boolean defer] : (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ;
	public final EugeneParser.function_definition_return function_definition(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.function_definition_return retval = new EugeneParser.function_definition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token LEFTP321=null;
		Token RIGHTP322=null;
		Token LEFTCUR323=null;
		Token RIGHTCUR324=null;
		ParserRuleReturnScope rt =null;
		ParserRuleReturnScope lop =null;
		ParserRuleReturnScope stmts =null;

		Object n_tree=null;
		Object LEFTP321_tree=null;
		Object RIGHTP322_tree=null;
		Object LEFTCUR323_tree=null;
		Object RIGHTCUR324_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2972:2: ( (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2972:4: (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2972:4: (rt= type_specification[true] )?
			int alt104=2;
			int LA104_0 = input.LA(1);
			if ( ((LA104_0 >= BOOL && LA104_0 <= BOOLEAN)||LA104_0==NUM||LA104_0==TXT) ) {
				alt104=1;
			}
			switch (alt104) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2972:5: rt= type_specification[true]
					{
					pushFollow(FOLLOW_type_specification_in_function_definition5455);
					rt=type_specification(true);
					state._fsp--;

					adaptor.addChild(root_0, rt.getTree());

					}
					break;

			}

			n=(Token)match(input,ID,FOLLOW_ID_in_function_definition5462); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			LEFTP321=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_function_definition5464); 
			LEFTP321_tree = (Object)adaptor.create(LEFTP321);
			adaptor.addChild(root_0, LEFTP321_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2972:46: (lop= list_of_parameters[true] )?
			int alt105=2;
			int LA105_0 = input.LA(1);
			if ( ((LA105_0 >= BOOL && LA105_0 <= BOOLEAN)||LA105_0==NUM||LA105_0==TXT) ) {
				alt105=1;
			}
			switch (alt105) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2972:47: lop= list_of_parameters[true]
					{
					pushFollow(FOLLOW_list_of_parameters_in_function_definition5469);
					lop=list_of_parameters(true);
					state._fsp--;

					adaptor.addChild(root_0, lop.getTree());

					}
					break;

			}

			RIGHTP322=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_function_definition5474); 
			RIGHTP322_tree = (Object)adaptor.create(RIGHTP322);
			adaptor.addChild(root_0, RIGHTP322_tree);

			LEFTCUR323=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_function_definition5476); 
			LEFTCUR323_tree = (Object)adaptor.create(LEFTCUR323);
			adaptor.addChild(root_0, LEFTCUR323_tree);

			pushFollow(FOLLOW_list_of_statements_in_function_definition5484);
			stmts=list_of_statements(true);
			state._fsp--;

			adaptor.addChild(root_0, stmts.getTree());

			RIGHTCUR324=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_function_definition5490); 
			RIGHTCUR324_tree = (Object)adaptor.create(RIGHTCUR324);
			adaptor.addChild(root_0, RIGHTCUR324_tree);


			if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {  // FUNCTION DEFINITION 
			    try {
			        // let's check if a return type is specified
			        String return_type = null;
			        if(null != rt) {
			            return_type = (rt!=null?((EugeneParser.type_specification_return)rt).t:null);
			        }
			        
			        // let's check if parameters are specified
			        List<NamedElement> parameters = null;
			        if(null != lop) {
			            parameters = (lop!=null?((EugeneParser.list_of_parameters_return)lop).parameters:null);
			        }
			        
			        // Function w/ parameters
			        this.interp.defineFunction(
			                return_type,     // return type
			                (n!=null?n.getText():null),         // the name of the function
			                parameters,      // list of parameters
			                (stmts!=null?(stmts.start):null),    // list of statements
			                this.input);     // the token stream
			                
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function_definition"


	public static class type_specification_return extends ParserRuleReturnScope {
		public String t;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "type_specification"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3004:1: type_specification[boolean defer] returns [String t] : ( NUM | TXT | NUM LEFTSBR RIGHTSBR | TXT LEFTSBR RIGHTSBR | ( BOOL | BOOLEAN ) );
	public final EugeneParser.type_specification_return type_specification(boolean defer) throws RecognitionException {
		EugeneParser.type_specification_return retval = new EugeneParser.type_specification_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NUM325=null;
		Token TXT326=null;
		Token NUM327=null;
		Token LEFTSBR328=null;
		Token RIGHTSBR329=null;
		Token TXT330=null;
		Token LEFTSBR331=null;
		Token RIGHTSBR332=null;
		Token set333=null;

		Object NUM325_tree=null;
		Object TXT326_tree=null;
		Object NUM327_tree=null;
		Object LEFTSBR328_tree=null;
		Object RIGHTSBR329_tree=null;
		Object TXT330_tree=null;
		Object LEFTSBR331_tree=null;
		Object RIGHTSBR332_tree=null;
		Object set333_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3006:2: ( NUM | TXT | NUM LEFTSBR RIGHTSBR | TXT LEFTSBR RIGHTSBR | ( BOOL | BOOLEAN ) )
			int alt106=5;
			switch ( input.LA(1) ) {
			case NUM:
				{
				int LA106_1 = input.LA(2);
				if ( (LA106_1==LEFTSBR) ) {
					alt106=3;
				}
				else if ( (LA106_1==ID) ) {
					alt106=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 106, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case TXT:
				{
				int LA106_2 = input.LA(2);
				if ( (LA106_2==LEFTSBR) ) {
					alt106=4;
				}
				else if ( (LA106_2==ID) ) {
					alt106=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 106, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case BOOL:
			case BOOLEAN:
				{
				alt106=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 106, 0, input);
				throw nvae;
			}
			switch (alt106) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3006:4: NUM
					{
					root_0 = (Object)adaptor.nil();


					NUM325=(Token)match(input,NUM,FOLLOW_NUM_in_type_specification5510); 
					NUM325_tree = (Object)adaptor.create(NUM325);
					adaptor.addChild(root_0, NUM325_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.NUM;
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3011:4: TXT
					{
					root_0 = (Object)adaptor.nil();


					TXT326=(Token)match(input,TXT,FOLLOW_TXT_in_type_specification5517); 
					TXT326_tree = (Object)adaptor.create(TXT326);
					adaptor.addChild(root_0, TXT326_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.TXT;
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3016:4: NUM LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					NUM327=(Token)match(input,NUM,FOLLOW_NUM_in_type_specification5524); 
					NUM327_tree = (Object)adaptor.create(NUM327);
					adaptor.addChild(root_0, NUM327_tree);

					LEFTSBR328=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_type_specification5526); 
					LEFTSBR328_tree = (Object)adaptor.create(LEFTSBR328);
					adaptor.addChild(root_0, LEFTSBR328_tree);

					RIGHTSBR329=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_type_specification5528); 
					RIGHTSBR329_tree = (Object)adaptor.create(RIGHTSBR329);
					adaptor.addChild(root_0, RIGHTSBR329_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.NUMLIST;
					}	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3021:4: TXT LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					TXT330=(Token)match(input,TXT,FOLLOW_TXT_in_type_specification5535); 
					TXT330_tree = (Object)adaptor.create(TXT330);
					adaptor.addChild(root_0, TXT330_tree);

					LEFTSBR331=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_type_specification5537); 
					LEFTSBR331_tree = (Object)adaptor.create(LEFTSBR331);
					adaptor.addChild(root_0, LEFTSBR331_tree);

					RIGHTSBR332=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_type_specification5539); 
					RIGHTSBR332_tree = (Object)adaptor.create(RIGHTSBR332);
					adaptor.addChild(root_0, RIGHTSBR332_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.TXTLIST;
					}	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3026:4: ( BOOL | BOOLEAN )
					{
					root_0 = (Object)adaptor.nil();


					set333=input.LT(1);
					if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set333));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}

					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.BOOLEAN;
					}	
						
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type_specification"


	public static class list_of_parameters_return extends ParserRuleReturnScope {
		public List<NamedElement> parameters;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "list_of_parameters"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3033:1: list_of_parameters[boolean defer] returns [List<NamedElement> parameters] : pt= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? ;
	public final EugeneParser.list_of_parameters_return list_of_parameters(boolean defer) throws RecognitionException {
		EugeneParser.list_of_parameters_return retval = new EugeneParser.list_of_parameters_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token COMMA334=null;
		ParserRuleReturnScope pt =null;
		ParserRuleReturnScope lop =null;

		Object n_tree=null;
		Object COMMA334_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3035:2: (pt= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3035:4: pt= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_type_specification_in_list_of_parameters5572);
			pt=type_specification(defer);
			state._fsp--;

			adaptor.addChild(root_0, pt.getTree());

			n=(Token)match(input,ID,FOLLOW_ID_in_list_of_parameters5577); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);


			if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
			    if(null == retval.parameters) {
			        retval.parameters = new ArrayList<NamedElement>();
			    }
			    
			    try {
			        retval.parameters.add(
			            this.interp.createFunctionParameter(
			                (pt!=null?((EugeneParser.type_specification_return)pt).t:null),        // type of the parameter
			                (n!=null?n.getText():null)));    // name of the parameter
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3050:4: ( COMMA lop= list_of_parameters[defer] )?
			int alt107=2;
			int LA107_0 = input.LA(1);
			if ( (LA107_0==COMMA) ) {
				alt107=1;
			}
			switch (alt107) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3050:5: COMMA lop= list_of_parameters[defer]
					{
					COMMA334=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_parameters5582); 
					COMMA334_tree = (Object)adaptor.create(COMMA334);
					adaptor.addChild(root_0, COMMA334_tree);

					pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters5586);
					lop=list_of_parameters(defer);
					state._fsp--;

					adaptor.addChild(root_0, lop.getTree());


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.parameters.addAll((lop!=null?((EugeneParser.list_of_parameters_return)lop).parameters:null));    
					}	
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "list_of_parameters"


	public static class list_of_statements_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "list_of_statements"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3057:1: list_of_statements[boolean defer] : statement[defer] ( statement[defer] )* ;
	public final EugeneParser.list_of_statements_return list_of_statements(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.list_of_statements_return retval = new EugeneParser.list_of_statements_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope statement335 =null;
		ParserRuleReturnScope statement336 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3059:2: ( statement[defer] ( statement[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3059:4: statement[defer] ( statement[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_statement_in_list_of_statements5610);
			statement335=statement(defer);
			state._fsp--;

			adaptor.addChild(root_0, statement335.getTree());

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3059:21: ( statement[defer] )*
			loop108:
			while (true) {
				int alt108=2;
				int LA108_0 = input.LA(1);
				if ( (LA108_0==ARRAY||(LA108_0 >= BOOL && LA108_0 <= COLLECTION)||LA108_0==DEVICE||(LA108_0 >= EXIT_LC && LA108_0 <= EXIT_UC)||LA108_0==GENBANK||LA108_0==GRAMMAR||(LA108_0 >= HASHMARK && LA108_0 <= ID)||(LA108_0 >= IMPORT_LC && LA108_0 <= INTERACTION)||(LA108_0 >= LC_FOR && LA108_0 <= LC_IF)||LA108_0==LC_WHILE||LA108_0==NUM||(LA108_0 >= PART && LA108_0 <= PERMUTE)||(LA108_0 >= PRINTLN_LC && LA108_0 <= RANDOM_UC)||(LA108_0 >= REGISTRY && LA108_0 <= RETURN_UC)||(LA108_0 >= RULE && LA108_0 <= SBOL)||(LA108_0 >= SIZEOF_LC && LA108_0 <= STORE_UC)||(LA108_0 >= TXT && LA108_0 <= TYPE)||(LA108_0 >= UC_FOR && LA108_0 <= UC_IF)||LA108_0==UC_WHILE) ) {
					alt108=1;
				}

				switch (alt108) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3059:22: statement[defer]
					{
					pushFollow(FOLLOW_statement_in_list_of_statements5614);
					statement336=statement(defer);
					state._fsp--;

					adaptor.addChild(root_0, statement336.getTree());

					}
					break;

				default :
					break loop108;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "list_of_statements"


	public static class return_statement_return extends ParserRuleReturnScope {
		public NamedElement el;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "return_statement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3062:1: return_statement[boolean defer] returns [NamedElement el] : ( RETURN_LC | RETURN_UC ) e= expr[defer] ;
	public final EugeneParser.return_statement_return return_statement(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.return_statement_return retval = new EugeneParser.return_statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set337=null;
		ParserRuleReturnScope e =null;

		Object set337_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3065:2: ( ( RETURN_LC | RETURN_UC ) e= expr[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3065:4: ( RETURN_LC | RETURN_UC ) e= expr[defer]
			{
			root_0 = (Object)adaptor.nil();


			set337=input.LT(1);
			if ( (input.LA(1) >= RETURN_LC && input.LA(1) <= RETURN_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set337));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_expr_in_return_statement5649);
			e=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, e.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

			    if(!this.interp.isReturnAllowed()) {
			        printError("a return statement is not allowed at this position!");
			    }

			    if(null != (e!=null?((EugeneParser.expr_return)e).element:null)) {
			        retval.el = (e!=null?((EugeneParser.expr_return)e).element:null);
			    } else {
			        retval.el = (e!=null?((EugeneParser.expr_return)e).p:null);
			    }
			    
			    throw new EugeneReturnException(retval.el);
			}
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "return_statement"


	public static class function_call_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_call"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3092:1: function_call[boolean defer] returns [NamedElement e] : udf= call_user_defined_function[defer] ;
	public final EugeneParser.function_call_return function_call(boolean defer) throws RecognitionException {
		EugeneParser.function_call_return retval = new EugeneParser.function_call_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope udf =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3094:2: (udf= call_user_defined_function[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3094:4: udf= call_user_defined_function[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_call_user_defined_function_in_function_call5680);
			udf=call_user_defined_function(defer);
			state._fsp--;

			adaptor.addChild(root_0, udf.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.e = (udf!=null?((EugeneParser.call_user_defined_function_return)udf).e:null);
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function_call"


	public static class call_user_defined_function_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "call_user_defined_function"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3102:1: call_user_defined_function[boolean defer] returns [NamedElement e] : f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP ;
	public final EugeneParser.call_user_defined_function_return call_user_defined_function(boolean defer) throws RecognitionException {
		EugeneParser.call_user_defined_function_return retval = new EugeneParser.call_user_defined_function_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token f=null;
		Token LEFTP338=null;
		Token RIGHTP339=null;
		ParserRuleReturnScope loe =null;

		Object f_tree=null;
		Object LEFTP338_tree=null;
		Object RIGHTP339_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3104:2: (f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3104:4: f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			f=(Token)match(input,ID,FOLLOW_ID_in_call_user_defined_function5705); 
			f_tree = (Object)adaptor.create(f);
			adaptor.addChild(root_0, f_tree);

			LEFTP338=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_call_user_defined_function5707); 
			LEFTP338_tree = (Object)adaptor.create(LEFTP338);
			adaptor.addChild(root_0, LEFTP338_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3104:15: (loe= list_of_expressions[defer] )?
			int alt109=2;
			int LA109_0 = input.LA(1);
			if ( (LA109_0==DOLLAR||(LA109_0 >= EXIT_LC && LA109_0 <= EXIT_UC)||(LA109_0 >= FALSE_LC && LA109_0 <= FALSE_UC)||LA109_0==ID||(LA109_0 >= LEFTP && LA109_0 <= LEFTSBR)||LA109_0==MINUS||LA109_0==NUMBER||LA109_0==PERMUTE||LA109_0==PRODUCT||(LA109_0 >= RANDOM_LC && LA109_0 <= REAL)||(LA109_0 >= SAVE_LC && LA109_0 <= SAVE_UC)||(LA109_0 >= SIZEOF_LC && LA109_0 <= STORE_UC)||(LA109_0 >= STRING && LA109_0 <= TRUE_UC)) ) {
				alt109=1;
			}
			switch (alt109) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3104:16: loe= list_of_expressions[defer]
					{
					pushFollow(FOLLOW_list_of_expressions_in_call_user_defined_function5712);
					loe=list_of_expressions(defer);
					state._fsp--;

					adaptor.addChild(root_0, loe.getTree());

					}
					break;

			}

			RIGHTP339=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_call_user_defined_function5717); 
			RIGHTP339_tree = (Object)adaptor.create(RIGHTP339);
			adaptor.addChild(root_0, RIGHTP339_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        retval.e = this.call_function((f!=null?f.getText():null), (loe!=null?((EugeneParser.list_of_expressions_return)loe).parameter_values:null));
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}	
				
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "call_user_defined_function"


	public static class list_of_expressions_return extends ParserRuleReturnScope {
		public List<NamedElement> parameter_values;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "list_of_expressions"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3115:1: list_of_expressions[boolean defer] returns [List<NamedElement> parameter_values] : e= expr[defer] ( COMMA loe= list_of_expressions[defer] )? ;
	public final EugeneParser.list_of_expressions_return list_of_expressions(boolean defer) throws RecognitionException {
		EugeneParser.list_of_expressions_return retval = new EugeneParser.list_of_expressions_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA340=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope loe =null;

		Object COMMA340_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3117:2: (e= expr[defer] ( COMMA loe= list_of_expressions[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3117:4: e= expr[defer] ( COMMA loe= list_of_expressions[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_list_of_expressions5739);
			e=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, e.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    if(null == retval.parameter_values) {
			        retval.parameter_values = new ArrayList<NamedElement>();
			    }
			    
			    if(null == (e!=null?((EugeneParser.expr_return)e).p:null)) { 
			        retval.parameter_values.add((e!=null?((EugeneParser.expr_return)e).element:null));
			    } else {
			        retval.parameter_values.add((e!=null?((EugeneParser.expr_return)e).p:null));
			    }
			    
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3130:5: ( COMMA loe= list_of_expressions[defer] )?
			int alt110=2;
			int LA110_0 = input.LA(1);
			if ( (LA110_0==COMMA) ) {
				alt110=1;
			}
			switch (alt110) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3130:6: COMMA loe= list_of_expressions[defer]
					{
					COMMA340=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_expressions5746); 
					COMMA340_tree = (Object)adaptor.create(COMMA340);
					adaptor.addChild(root_0, COMMA340_tree);

					pushFollow(FOLLOW_list_of_expressions_in_list_of_expressions5750);
					loe=list_of_expressions(defer);
					state._fsp--;

					adaptor.addChild(root_0, loe.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.parameter_values.addAll((loe!=null?((EugeneParser.list_of_expressions_return)loe).parameter_values:null));
					}	
						
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "list_of_expressions"

	// Delegated rules



	public static final BitSet FOLLOW_statement_in_prog997 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_function_definition_in_prog1002 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_EOF_in_prog1007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_includeStatement_in_statement1034 = new BitSet(new long[]{0x0000000000000002L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1038 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declarationStatement_in_statement1045 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_printStatement_in_statement1054 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1057 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement1062 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1065 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dataExchange_in_statement1072 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1075 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_imperativeStatements_in_statement1082 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_call_in_statement1088 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1091 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_built_in_function_in_statement1096 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1099 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_return_statement_in_statement1104 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement1128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement1136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement1142 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement1148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instantiation_in_declarationStatement1154 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement1160 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement1166 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_grammarDeclaration_in_declarationStatement1172 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement1178 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_variableDeclaration1196 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_numdecl_in_variableDeclaration1200 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_variableDeclaration1211 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_txtdecl_in_variableDeclaration1215 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_variableDeclaration1226 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1228 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1230 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration1234 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_variableDeclaration1245 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1247 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1249 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1253 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_variableDeclaration1264 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_booldecl_in_variableDeclaration1272 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numdecl1295 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numdecl1301 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_numdecl_in_numdecl1303 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numdecl1311 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_numdecl1313 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_numdecl1318 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numdecl1326 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_numdecl_in_numdecl1328 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtdecl1348 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtdecl1355 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_txtdecl_in_txtdecl1357 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtdecl1368 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_txtdecl1370 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_txtdecl1374 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtdecl1382 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_txtdecl_in_txtdecl1384 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtlistdecl1404 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtlistdecl1411 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1413 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtlistdecl1423 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1425 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_txtlistdecl1431 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtlistdecl1439 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1441 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numlistdecl1461 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numlistdecl1468 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numlistdecl1480 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_numlistdecl1482 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_numlistdecl1487 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numlistdecl1495 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1497 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_booldecl1517 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_booldecl1524 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_booldecl_in_booldecl1526 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_booldecl1536 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_booldecl1538 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_booldecl1542 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1560 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_propertyDeclaration1564 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1566 = new BitSet(new long[]{0x0000000000000600L,0x0000020000000004L});
	public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1570 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1572 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_propertyType1591 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_propertyType1598 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_propertyType1600 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1602 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_propertyType1609 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_propertyType1617 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_propertyType1619 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1621 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_propertyType1628 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_in_typeDeclaration1657 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_typeDeclaration1662 = new BitSet(new long[]{0x0020000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1665 = new BitSet(new long[]{0x0000000200000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1670 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1675 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_partTypeDeclaration1694 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_partTypeDeclaration1703 = new BitSet(new long[]{0x0020000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1706 = new BitSet(new long[]{0x0000000200000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1711 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1716 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1743 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1750 = new BitSet(new long[]{0x0040000200000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1753 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1755 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_containerDeclaration1763 = new BitSet(new long[]{0x0020000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1768 = new BitSet(new long[]{0x1060008246624E40L,0x000007DF9D07C0ECL});
	public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1771 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1776 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1809 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_expr_in_list_of_declarations1816 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list_of_declarations1824 = new BitSet(new long[]{0x1060008246624E40L,0x000007DF9C07C0ECL});
	public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations1828 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_instantiation1856 = new BitSet(new long[]{0x0000000200020000L});
	public static final BitSet FOLLOW_dynamic_naming_in_instantiation1862 = new BitSet(new long[]{0x0020000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_instantiation1869 = new BitSet(new long[]{0x1060000206660000L,0x000001DF99074088L});
	public static final BitSet FOLLOW_listOfDotValues_in_instantiation1874 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_listOfValues_in_instantiation1879 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_instantiation1884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_listOfDotValues1907 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_listOfDotValues1911 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1917 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_listOfDotValues1921 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1929 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_listOfDotValues1932 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_DOT_in_listOfDotValues1934 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_listOfDotValues1938 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1946 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_listOfDotValues1950 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1960 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_expr_in_listOfValues1981 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_listOfValues1987 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_listOfValues1993 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration2012 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_deviceDeclaration2016 = new BitSet(new long[]{0x0020000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration2019 = new BitSet(new long[]{0x1040000200000000L,0x0000000001000200L});
	public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration2024 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration2029 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selection_in_deviceComponents2060 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_deviceComponents2066 = new BitSet(new long[]{0x1040000200000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_deviceComponents_in_deviceComponents2070 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_selection2094 = new BitSet(new long[]{0x1000000200000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_selection_list_in_selection2098 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_selection2101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_device_component_in_selection2110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_device_component_in_selection_list2139 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
	public static final BitSet FOLLOW_PIPE_in_selection_list2145 = new BitSet(new long[]{0x1000000200000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_selection_list_in_selection_list2149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_device_component2175 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_device_component2185 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_lhs_assignment_in_assignment2205 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_assignment2208 = new BitSet(new long[]{0x1060001A16620020L,0x000001DFB8174088L});
	public static final BitSet FOLLOW_AMP_in_assignment2213 = new BitSet(new long[]{0x1060001A16620000L,0x000001DFB8174088L});
	public static final BitSet FOLLOW_rhs_assignment_in_assignment2219 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_lhs_assignment2234 = new BitSet(new long[]{0x0040000000040000L});
	public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2236 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_lhs_access2256 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_lhs_access2260 = new BitSet(new long[]{0x0040000000040000L});
	public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2264 = new BitSet(new long[]{0x0000000200000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_set_in_lhs_access2266 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2272 = new BitSet(new long[]{0x0040000000040000L});
	public static final BitSet FOLLOW_lhs_access_in_lhs_access2275 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_rhs_assignment2312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_listOfIDs2340 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_listOfIDs2349 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2353 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RULE_in_ruleDeclaration2377 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_ruleDeclaration2381 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2383 = new BitSet(new long[]{0x1065C00200000000L,0xF82E004000040018L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
	public static final BitSet FOLLOW_set_in_ruleDeclaration2388 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_ruleDeclaration2396 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_COLON_in_ruleDeclaration2398 = new BitSet(new long[]{0x1064C00200000000L,0xF826004000040018L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
	public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2406 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2411 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2425 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EQUALS_in_relationalOperators2804 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_relationalOperators2806 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEQUAL_in_relationalOperators2811 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LTHAN_in_relationalOperators2816 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GTHAN_in_relationalOperators2821 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEQUAL_in_relationalOperators2826 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GEQUAL_in_relationalOperators2831 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2836 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2845 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2863 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2872 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2881 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2890 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators2908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_predicate_in_cnf_rule2932 = new BitSet(new long[]{0x0200010000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_set_in_cnf_rule2940 = new BitSet(new long[]{0x1064C00200000000L,0xF826004000040018L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
	public static final BitSet FOLLOW_cnf_rule_in_cnf_rule2950 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_negated_predicate_in_or_predicate2980 = new BitSet(new long[]{0x0402000000000002L,0x0010000000000000L});
	public static final BitSet FOLLOW_set_in_or_predicate2986 = new BitSet(new long[]{0x1064C00200000000L,0xF826004000040018L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
	public static final BitSet FOLLOW_negated_predicate_in_or_predicate2996 = new BitSet(new long[]{0x0402000000000002L,0x0010000000000000L});
	public static final BitSet FOLLOW_set_in_negated_predicate3024 = new BitSet(new long[]{0x1064400200000000L,0xF822004000040008L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
	public static final BitSet FOLLOW_predicate_in_negated_predicate3034 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_predicate_in_negated_predicate3044 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_predicate3071 = new BitSet(new long[]{0x0004400000000000L,0xF822000000000000L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
	public static final BitSet FOLLOW_ruleOperator_in_predicate3081 = new BitSet(new long[]{0x0040000200000002L,0x0000000000000008L});
	public static final BitSet FOLLOW_operand_in_predicate3090 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_predicate3104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expressionRule_in_predicate3113 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_operand3144 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_operand3153 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_operand3160 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_NUMBER_in_operand3164 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_operand3166 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_expressionRule3192 = new BitSet(new long[]{0x88800000A0100000L,0x0000000000000000L,0x0164D000C002C9A0L,0x0000000000000060L});
	public static final BitSet FOLLOW_exp_op_in_expressionRule3197 = new BitSet(new long[]{0x1020000200000000L,0x0000004000040008L});
	public static final BitSet FOLLOW_expression_in_expressionRule3202 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp_operand_in_expression3226 = new BitSet(new long[]{0x5000000000010002L,0x0000000000000200L});
	public static final BitSet FOLLOW_exp_operator_in_expression3235 = new BitSet(new long[]{0x1020000200000000L,0x0000004000040008L});
	public static final BitSet FOLLOW_expression_in_expression3240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_expression3252 = new BitSet(new long[]{0x1020000200000000L,0x0000004000040008L});
	public static final BitSet FOLLOW_expression_in_expression3254 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_expression3257 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUS_in_exp_operator3276 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_exp_operator3284 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MULT_in_exp_operator3291 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DIV_in_exp_operator3298 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_exp_operand3328 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_DOT_in_exp_operand3330 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_exp_operand3339 = new BitSet(new long[]{0x0040000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_NUMBER_in_exp_operand3349 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3351 = new BitSet(new long[]{0x0040000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_exp_operand3363 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_exp_operand3370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_NUMBER_in_exp_operand3374 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REAL_in_exp_operand3383 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_exp_operand3390 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_REAL_in_exp_operand3394 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_exp_operand3403 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_relationalOperators_in_exp_op3430 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GRAMMAR_in_grammarDeclaration3449 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_grammarDeclaration3453 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_grammarDeclaration3455 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_list_of_production_rules_in_grammarDeclaration3457 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_grammarDeclaration3460 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_production_rule_in_list_of_production_rules3472 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_list_of_production_rules3475 = new BitSet(new long[]{0x0000000200000002L});
	public static final BitSet FOLLOW_list_of_production_rules_in_list_of_production_rules3478 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_production_rule3498 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_ARROW_in_production_rule3502 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_right_hand_side_in_production_rule3504 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_right_hand_side3520 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_right_hand_side3525 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_right_hand_side_in_right_hand_side3527 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_interaction_in_right_hand_side3535 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_interaction_in_interactionDeclaration3560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3568 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_interactionDeclaration3572 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3574 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_interaction_in_interactionDeclaration3578 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_interaction3604 = new BitSet(new long[]{0x0004400000000000L,0x0022000000000000L});
	public static final BitSet FOLLOW_interactionType_in_interaction3608 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_interaction3613 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_interaction3622 = new BitSet(new long[]{0x0004400000000000L,0x0022000000000000L});
	public static final BitSet FOLLOW_interactionType_in_interaction3626 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_interaction3629 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_interaction_in_interaction3633 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_interaction3636 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_interactionType3656 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_interactionType3669 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_printStatement3695 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_printStatement3701 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_toPrint_in_printStatement3705 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_printStatement3708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_printStatement3715 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_printStatement3721 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_toPrint_in_printStatement3725 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_printStatement3728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_toPrint3749 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_toPrint_prime_in_toPrint3754 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_toPrint_prime3780 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_toPrint_in_toPrint_prime3784 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ifStatement_in_imperativeStatements3809 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements3815 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_for_loop_in_imperativeStatements3821 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_while_loop_in_imperativeStatements3827 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_ifStatement3859 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_ifStatement3865 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_logical_condition_in_ifStatement3869 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_ifStatement3872 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3874 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_list_of_statements_in_ifStatement3882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3885 = new BitSet(new long[]{0x0000060000000002L,0x0000300000000000L});
	public static final BitSet FOLLOW_set_in_ifStatement3900 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_ifStatement3906 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_logical_condition_in_ifStatement3910 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_ifStatement3913 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3915 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_list_of_statements_in_ifStatement3923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3926 = new BitSet(new long[]{0x0000060000000002L,0x0000300000000000L});
	public static final BitSet FOLLOW_set_in_ifStatement3942 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3948 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_list_of_statements_in_ifStatement3956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3959 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_forall_iterator3981 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_forall_iterator3990 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_COLON_in_forall_iterator3992 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_forall_iterator3998 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator4000 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_list_of_statements_in_forall_iterator4007 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator4014 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_for_loop4031 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_for_loop4037 = new BitSet(new long[]{0x0000000000000600L,0x0000020000000004L});
	public static final BitSet FOLLOW_variableDeclaration_in_for_loop4041 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_for_loop4044 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_logical_condition_in_for_loop4048 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_SEMIC_in_for_loop4051 = new BitSet(new long[]{0x0000000200000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_assignment_in_for_loop4056 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_for_loop4061 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_for_loop4063 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_list_of_statements_in_for_loop4071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_for_loop4078 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_while_loop4097 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_while_loop4103 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_logical_condition_in_while_loop4107 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_while_loop4110 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_while_loop4112 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_list_of_statements_in_while_loop4120 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_while_loop4127 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_or_condition_in_logical_condition4153 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_or_condition_in_logical_not_condition4178 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_and_condition_in_logical_or_condition4203 = new BitSet(new long[]{0x0402000000000002L,0x0010000000000100L});
	public static final BitSet FOLLOW_LC_OR_in_logical_or_condition4210 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_UC_OR_in_logical_or_condition4212 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_LOG_OR_in_logical_or_condition4214 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_PIPE_in_logical_or_condition4216 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074198L});
	public static final BitSet FOLLOW_PIPE_in_logical_or_condition4219 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_logical_or_condition_in_logical_or_condition4226 = new BitSet(new long[]{0x0402000000000002L,0x0010000000000100L});
	public static final BitSet FOLLOW_atomic_condition_in_logical_and_condition4251 = new BitSet(new long[]{0x0200010000000022L,0x0000080000000000L});
	public static final BitSet FOLLOW_LC_AND_in_logical_and_condition4258 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_UC_AND_in_logical_and_condition4260 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_LOG_AND_in_logical_and_condition4262 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_AMP_in_logical_and_condition4264 = new BitSet(new long[]{0x1060800206620020L,0x000401DF98074098L});
	public static final BitSet FOLLOW_AMP_in_logical_and_condition4267 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_logical_and_condition_in_logical_and_condition4274 = new BitSet(new long[]{0x0200010000000022L,0x0000080000000000L});
	public static final BitSet FOLLOW_expr_in_atomic_condition4302 = new BitSet(new long[]{0x88800000A0100000L,0x0000000000000000L,0x0164D000C002C9A0L,0x0000000000000060L});
	public static final BitSet FOLLOW_relationalOperators_in_atomic_condition4307 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_atomic_condition4311 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_atomic_condition4319 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_atomic_condition4327 = new BitSet(new long[]{0x1060800206620000L,0x000401DF98074098L});
	public static final BitSet FOLLOW_atomic_condition_in_atomic_condition4331 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_atomic_condition4334 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expr4361 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000200L});
	public static final BitSet FOLLOW_set_in_expr4370 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_multExpr_in_expr4378 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000200L});
	public static final BitSet FOLLOW_atom_in_multExpr4408 = new BitSet(new long[]{0x4000000000010002L});
	public static final BitSet FOLLOW_MULT_in_multExpr4419 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_DIV_in_multExpr4423 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_atom_in_multExpr4428 = new BitSet(new long[]{0x4000000000010002L});
	public static final BitSet FOLLOW_NUMBER_in_atom4455 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REAL_in_atom4461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_atom4471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040008L});
	public static final BitSet FOLLOW_NUMBER_in_atom4476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REAL_in_atom4482 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_atom4495 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_atom4505 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dynamic_naming_in_atom4521 = new BitSet(new long[]{0x0040000000040000L});
	public static final BitSet FOLLOW_object_access_in_atom4528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_atom4537 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_atom4545 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_atom4547 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_atom4550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_atom4559 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_list_in_atom4561 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_atom4564 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_built_in_function_in_atom4574 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_call_in_atom4584 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_list4607 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list4614 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_list4618 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_set_in_built_in_function4646 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4656 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_built_in_function4660 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4663 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4670 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4676 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_range_in_built_in_function4680 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4690 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4700 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_built_in_function4704 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4707 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRODUCT_in_built_in_function4717 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_PERMUTE_in_built_in_function4721 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4724 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_built_in_function4728 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4730 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4737 = new BitSet(new long[]{0x0020000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4746 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_toPrint_in_built_in_function4750 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4753 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_built_in_function4762 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4764 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4766 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_range4786 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_range4789 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_range4793 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_object_access4829 = new BitSet(new long[]{0x0000000200000000L,0x0000000600000000L});
	public static final BitSet FOLLOW_ID_in_object_access4834 = new BitSet(new long[]{0x0040000000040000L});
	public static final BitSet FOLLOW_set_in_object_access4840 = new BitSet(new long[]{0x0060000000040000L});
	public static final BitSet FOLLOW_LEFTP_in_object_access4847 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_object_access4849 = new BitSet(new long[]{0x0040000000040000L});
	public static final BitSet FOLLOW_LEFTSBR_in_object_access4859 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_object_access4864 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_object_access4868 = new BitSet(new long[]{0x0040000000040000L});
	public static final BitSet FOLLOW_object_access_in_object_access4875 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_dynamic_naming4900 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOLLAR_in_dynamic_naming4907 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_dynamic_naming4909 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_dynamic_naming4913 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_dynamic_naming4916 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sbolStatement_in_dataExchange4941 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_importStatement_in_dataExchange4951 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_genbankStatement_in_dataExchange4961 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_registryStatement_in_dataExchange4971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HASHMARK_in_includeStatement4990 = new BitSet(new long[]{0x0000006000000000L});
	public static final BitSet FOLLOW_set_in_includeStatement4994 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_STRING_in_includeStatement5002 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_importStatement5023 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_importStatement5029 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_STRING_in_importStatement5033 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_importStatement5037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SBOL_in_sbolStatement5059 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_DOT_in_sbolStatement5061 = new BitSet(new long[]{0x0000001801800000L,0x0300000000000000L});
	public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement5064 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement5071 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sbolVisualStatement_in_sbolStatement5079 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_sbolExportStatement5096 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement5102 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_sbolExportStatement5106 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_sbolExportStatement5108 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_STRING_in_sbolExportStatement5112 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement5114 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_sbolImportStatement5137 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement5143 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_STRING_in_sbolImportStatement5147 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement5149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_sbolVisualStatement5165 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_sbolVisualStatement5171 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_sbolVisualStatement5175 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_sbolVisualStatement5177 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GENBANK_in_genbankStatement5202 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_DOT_in_genbankStatement5204 = new BitSet(new long[]{0x0000001801800000L});
	public static final BitSet FOLLOW_genbankImportStatement_in_genbankStatement5209 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_genbankExportStatement_in_genbankStatement5217 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_genbankExportStatement5236 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_genbankExportStatement5242 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_genbankExportStatement5244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_genbankImportStatement5270 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_genbankImportStatement5276 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_STRING_in_genbankImportStatement5280 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_genbankImportStatement5282 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_genbankImportStatement5289 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_genbankImportStatement5295 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_genbankImportStatement5299 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_genbankImportStatement5301 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_STRING_in_genbankImportStatement5305 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_genbankImportStatement5307 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTRY_in_registryStatement5331 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_DOT_in_registryStatement5333 = new BitSet(new long[]{0x0000001800000000L});
	public static final BitSet FOLLOW_set_in_registryStatement5335 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_registryStatement5341 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_STRING_in_registryStatement5345 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_registryStatement5347 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASSERT_in_testStatements5366 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5368 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_testStatements5372 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_DOT_in_testStatements5374 = new BitSet(new long[]{0x0000000000000000L,0x0000000600000000L});
	public static final BitSet FOLLOW_set_in_testStatements5376 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5382 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5384 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5386 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5388 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_NUMBER_in_testStatements5392 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5394 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOTE_in_testStatements5402 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5404 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_testStatements5408 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_DOT_in_testStatements5410 = new BitSet(new long[]{0x0000000000000000L,0x0000000600000000L});
	public static final BitSet FOLLOW_set_in_testStatements5412 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5418 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5420 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5422 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_NUMBER_in_testStatements5428 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5430 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_specification_in_function_definition5455 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_function_definition5462 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_function_definition5464 = new BitSet(new long[]{0x0000000000000600L,0x0000020001000004L});
	public static final BitSet FOLLOW_list_of_parameters_in_function_definition5469 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_function_definition5474 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_function_definition5476 = new BitSet(new long[]{0x000838FB50604E40L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_list_of_statements_in_function_definition5484 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_function_definition5490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_type_specification5510 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_type_specification5517 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_type_specification5524 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_type_specification5526 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_type_specification5528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_type_specification5535 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_type_specification5537 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_type_specification5539 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_type_specification5546 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_specification_in_list_of_parameters5572 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_ID_in_list_of_parameters5577 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list_of_parameters5582 = new BitSet(new long[]{0x0000000000000600L,0x0000020000000004L});
	public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters5586 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_list_of_statements5610 = new BitSet(new long[]{0x000838FB50604E42L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_statement_in_list_of_statements5614 = new BitSet(new long[]{0x000838FB50604E42L,0x0041C61FBC73FCE4L});
	public static final BitSet FOLLOW_set_in_return_statement5639 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_expr_in_return_statement5649 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_call_user_defined_function_in_function_call5680 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_call_user_defined_function5705 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_call_user_defined_function5707 = new BitSet(new long[]{0x1060000206620000L,0x000001DF99074088L});
	public static final BitSet FOLLOW_list_of_expressions_in_call_user_defined_function5712 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_RIGHTP_in_call_user_defined_function5717 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_list_of_expressions5739 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list_of_expressions5746 = new BitSet(new long[]{0x1060000206620000L,0x000001DF98074088L});
	public static final BitSet FOLLOW_list_of_expressions_in_list_of_expressions5750 = new BitSet(new long[]{0x0000000000000002L});
}
