// $ANTLR 3.5.1 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g 2014-12-19 11:06:12

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
import org.cidarlab.eugene.interp.RuleBuilder;
import org.cidarlab.eugene.dom.rules.*;
import org.cidarlab.eugene.dom.interaction.Interaction;
import org.cidarlab.eugene.dom.rules.exp.*;
import org.cidarlab.eugene.dom.imp.*;
import org.cidarlab.eugene.dom.imp.container.*;
import org.cidarlab.eugene.dom.imp.loops.Loop;
import org.cidarlab.eugene.dom.imp.functions.*;
import org.cidarlab.eugene.dom.imp.branches.ConditionalBranch;
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
		"ASSERT", "BOOL", "BOOLEAN", "COLLECTION", "COLON", "COMMA", "CREATE_LC", 
		"CREATE_UC", "DELETE_LC", "DELETE_UC", "DEVICE", "DIGIT", "DIV", "DOLLAR", 
		"DOT", "DOTDOT", "EQUALS", "EXIT_LC", "EXIT_UC", "EXPORT_LC", "EXPORT_UC", 
		"FALSE_LC", "FALSE_UC", "FLEXIBLE", "GENBANK", "GEQUAL", "GRAMMAR", "GTHAN", 
		"HASHMARK", "ID", "IMAGE", "IMPORT_LC", "IMPORT_UC", "INCLUDE_LC", "INCLUDE_UC", 
		"INTERACTION", "LC_AND", "LC_ELSE", "LC_ELSEIF", "LC_FOR", "LC_FORALL", 
		"LC_IF", "LC_INDUCES", "LC_NOT", "LC_ON", "LC_OR", "LC_PERMUTE", "LC_PRODUCT", 
		"LC_REPRESSES", "LC_SEQUENCE_OF", "LC_WHILE", "LEFTCUR", "LEFTP", "LEFTSBR", 
		"LEQUAL", "LINE_COMMENT", "LOG_AND", "LOG_OR", "LTHAN", "MINUS", "ML_COMMENT", 
		"MULT", "NEQUAL", "NEWLINE", "NOTE", "NUM", "NUMBER", "OP_NOT", "PART", 
		"PART_TYPE", "PIPE", "PLUS", "PRINTLN_LC", "PRINTLN_UC", "PRINT_LC", "PRINT_UC", 
		"PROPERTY", "QUERY_LC", "QUERY_UC", "RANDOM_LC", "RANDOM_UC", "READ_LC", 
		"READ_UC", "REAL", "REF", "REGISTRY", "RETURN_LC", "RETURN_UC", "RIGHTCUR", 
		"RIGHTP", "RIGHTSBR", "RULE", "RULE_BUILDER", "SAVE_LC", "SAVE_UC", "SBOL", 
		"SEMIC", "SIZEOF_LC", "SIZEOF_UC", "SIZE_LC", "SIZE_OF_LC", "SIZE_OF_UC", 
		"SIZE_UC", "STORE_LC", "STORE_UC", "STRICT", "STRING", "TRUE_LC", "TRUE_UC", 
		"TXT", "TYPE", "UC_AND", "UC_ELSE", "UC_ELSEIF", "UC_FOR", "UC_FORALL", 
		"UC_IF", "UC_INDUCES", "UC_NOT", "UC_ON", "UC_OR", "UC_PERMUTE", "UC_PRODUCT", 
		"UC_REPRESSES", "UC_SEQUENCE_OF", "UC_WHILE", "UNDERS", "UPDATE_LC", "UPDATE_UC", 
		"VISUALIZE_LC", "VISUALIZE_UC", "WS", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", 
		"'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", 
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
	public static final int T__201=201;
	public static final int T__202=202;
	public static final int T__203=203;
	public static final int T__204=204;
	public static final int T__205=205;
	public static final int T__206=206;
	public static final int T__207=207;
	public static final int T__208=208;
	public static final int T__209=209;
	public static final int T__210=210;
	public static final int T__211=211;
	public static final int T__212=212;
	public static final int T__213=213;
	public static final int T__214=214;
	public static final int T__215=215;
	public static final int T__216=216;
	public static final int T__217=217;
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
	public static final int CREATE_LC=14;
	public static final int CREATE_UC=15;
	public static final int DELETE_LC=16;
	public static final int DELETE_UC=17;
	public static final int DEVICE=18;
	public static final int DIGIT=19;
	public static final int DIV=20;
	public static final int DOLLAR=21;
	public static final int DOT=22;
	public static final int DOTDOT=23;
	public static final int EQUALS=24;
	public static final int EXIT_LC=25;
	public static final int EXIT_UC=26;
	public static final int EXPORT_LC=27;
	public static final int EXPORT_UC=28;
	public static final int FALSE_LC=29;
	public static final int FALSE_UC=30;
	public static final int FLEXIBLE=31;
	public static final int GENBANK=32;
	public static final int GEQUAL=33;
	public static final int GRAMMAR=34;
	public static final int GTHAN=35;
	public static final int HASHMARK=36;
	public static final int ID=37;
	public static final int IMAGE=38;
	public static final int IMPORT_LC=39;
	public static final int IMPORT_UC=40;
	public static final int INCLUDE_LC=41;
	public static final int INCLUDE_UC=42;
	public static final int INTERACTION=43;
	public static final int LC_AND=44;
	public static final int LC_ELSE=45;
	public static final int LC_ELSEIF=46;
	public static final int LC_FOR=47;
	public static final int LC_FORALL=48;
	public static final int LC_IF=49;
	public static final int LC_INDUCES=50;
	public static final int LC_NOT=51;
	public static final int LC_ON=52;
	public static final int LC_OR=53;
	public static final int LC_PERMUTE=54;
	public static final int LC_PRODUCT=55;
	public static final int LC_REPRESSES=56;
	public static final int LC_SEQUENCE_OF=57;
	public static final int LC_WHILE=58;
	public static final int LEFTCUR=59;
	public static final int LEFTP=60;
	public static final int LEFTSBR=61;
	public static final int LEQUAL=62;
	public static final int LINE_COMMENT=63;
	public static final int LOG_AND=64;
	public static final int LOG_OR=65;
	public static final int LTHAN=66;
	public static final int MINUS=67;
	public static final int ML_COMMENT=68;
	public static final int MULT=69;
	public static final int NEQUAL=70;
	public static final int NEWLINE=71;
	public static final int NOTE=72;
	public static final int NUM=73;
	public static final int NUMBER=74;
	public static final int OP_NOT=75;
	public static final int PART=76;
	public static final int PART_TYPE=77;
	public static final int PIPE=78;
	public static final int PLUS=79;
	public static final int PRINTLN_LC=80;
	public static final int PRINTLN_UC=81;
	public static final int PRINT_LC=82;
	public static final int PRINT_UC=83;
	public static final int PROPERTY=84;
	public static final int QUERY_LC=85;
	public static final int QUERY_UC=86;
	public static final int RANDOM_LC=87;
	public static final int RANDOM_UC=88;
	public static final int READ_LC=89;
	public static final int READ_UC=90;
	public static final int REAL=91;
	public static final int REF=92;
	public static final int REGISTRY=93;
	public static final int RETURN_LC=94;
	public static final int RETURN_UC=95;
	public static final int RIGHTCUR=96;
	public static final int RIGHTP=97;
	public static final int RIGHTSBR=98;
	public static final int RULE=99;
	public static final int RULE_BUILDER=100;
	public static final int SAVE_LC=101;
	public static final int SAVE_UC=102;
	public static final int SBOL=103;
	public static final int SEMIC=104;
	public static final int SIZEOF_LC=105;
	public static final int SIZEOF_UC=106;
	public static final int SIZE_LC=107;
	public static final int SIZE_OF_LC=108;
	public static final int SIZE_OF_UC=109;
	public static final int SIZE_UC=110;
	public static final int STORE_LC=111;
	public static final int STORE_UC=112;
	public static final int STRICT=113;
	public static final int STRING=114;
	public static final int TRUE_LC=115;
	public static final int TRUE_UC=116;
	public static final int TXT=117;
	public static final int TYPE=118;
	public static final int UC_AND=119;
	public static final int UC_ELSE=120;
	public static final int UC_ELSEIF=121;
	public static final int UC_FOR=122;
	public static final int UC_FORALL=123;
	public static final int UC_IF=124;
	public static final int UC_INDUCES=125;
	public static final int UC_NOT=126;
	public static final int UC_ON=127;
	public static final int UC_OR=128;
	public static final int UC_PERMUTE=129;
	public static final int UC_PRODUCT=130;
	public static final int UC_REPRESSES=131;
	public static final int UC_SEQUENCE_OF=132;
	public static final int UC_WHILE=133;
	public static final int UNDERS=134;
	public static final int UPDATE_LC=135;
	public static final int UPDATE_UC=136;
	public static final int VISUALIZE_LC=137;
	public static final int VISUALIZE_UC=138;
	public static final int WS=139;

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
	 *  Eugene (i.e. Ernst) has caused.
	 */
	public void cleanUp() {
	    // clean up the mess of the interpreter
	    if(null != this.interp) {
	        this.interp.cleanUp();
	        this.interp = null;
	    }
	}


	/**
	 * The getAllElements() method returns a EugeneCollection
	 * that consists all NamedElement objects utilized in the 
	 * Eugene script.
	 */
	public EugeneCollection getAllElements() 
	        throws EugeneException {
	    try {
	        if(null != this.interp) {
	            return this.interp.getAllElements();
	        }
	    } catch(EugeneException ee) {
	        throw new EugeneException(ee.getMessage());
	    }

	    return null;
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
	    printError(
	        this.getErrorMessage(re, this.getTokenNames()));
	}

	public String getErrorMessage(RecognitionException e,
	                              String[] tokenNames) {
	    List stack = getRuleInvocationStack(e, this.getClass().getName());
	    String msg = null;
	    if ( e instanceof NoViableAltException ) {
	        NoViableAltException nvae = (NoViableAltException)e;
	        msg = " no viable alt; token="+e.token+
	        " (decision="+nvae.decisionNumber+
	        " state "+nvae.stateNumber+")"+
	        " decision=<<"+nvae.grammarDecisionDescription+">>";
	    } else {
	        msg = super.getErrorMessage(e, tokenNames);
	    }
	    return stack+" "+msg;
	}

	public String getTokenErrorDisplay(Token t) {
	    return t.toString();
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
	    try {
	        if (this.interp.checkIfDeclaredInScope(name)) {
	            printError(name + " has already been defined.");
	            return true;
	        } 
	    } catch(EugeneException ee) {
	        printError(ee.getLocalizedMessage());
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
	        // restore location
	        this.input.seek(oldPosition); 
	        // and throw exception
	        throw new EugeneReturnException(ere.getReturnValue());    
	    } catch (Exception e) {
	        // restore location
	        this.input.seek(oldPosition); 
	        // and throw exception
	        throw new EugeneException(e.getLocalizedMessage());
	    }

	    // restore location
	    this.input.seek(oldPosition); 
	    // and return the returned object	    
	    return rv;
	}
	 
	    /**
	     *   The exec_branch/1 method executes the branch of an IF-ELSE statement
	     *   depending on the condition.
	     *   The condition is evaluated during the Parsing process by invoking the Interp.evaluateCondition()
	     *   method. Depending on this, the parser invokes the exec_branch method.
	     *   (see if_elseif_else rule)
	     */
	    public void exec_branch(int tokenIndex) 
	        throws EugeneException, EugeneReturnException {

	        // push it!
	        this.interp.push(new ConditionalBranch());
	        try {
	            this.exec("list_of_statements", tokenIndex);
	        } catch (EugeneReturnException ere) {
	            // pop the if statement from the stack
	            this.interp.pop();
	            // and throw the exception
	            throw new EugeneReturnException(ere.getReturnValue());    
	        } catch (Exception e) {
	            // pop the if statement from the stack
	            this.interp.pop();
	            // and throw the exception
	            throw new EugeneException(e.getLocalizedMessage());
	        }
	        
	        // pop the if statement from the stack
	        this.interp.pop();
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:901:1: prog[boolean defer] : ( statement[defer] | function_definition[true] )* EOF ;
	public final EugeneParser.prog_return prog(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.prog_return retval = new EugeneParser.prog_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EOF3=null;
		ParserRuleReturnScope statement1 =null;
		ParserRuleReturnScope function_definition2 =null;

		Object EOF3_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:2: ( ( statement[defer] | function_definition[true] )* EOF )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:4: ( statement[defer] | function_definition[true] )* EOF
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:4: ( statement[defer] | function_definition[true] )*
			loop1:
			while (true) {
				int alt1=3;
				switch ( input.LA(1) ) {
				case ARRAY:
				case COLLECTION:
				case CREATE_LC:
				case CREATE_UC:
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
				case LC_AND:
				case LC_FOR:
				case LC_FORALL:
				case LC_IF:
				case LC_PERMUTE:
				case LC_PRODUCT:
				case LC_SEQUENCE_OF:
				case LC_WHILE:
				case PART:
				case PART_TYPE:
				case PRINTLN_LC:
				case PRINTLN_UC:
				case PRINT_LC:
				case PRINT_UC:
				case PROPERTY:
				case QUERY_LC:
				case QUERY_UC:
				case RANDOM_LC:
				case RANDOM_UC:
				case REGISTRY:
				case RETURN_LC:
				case RETURN_UC:
				case RULE:
				case RULE_BUILDER:
				case SAVE_LC:
				case SAVE_UC:
				case SBOL:
				case SIZEOF_LC:
				case SIZEOF_UC:
				case SIZE_LC:
				case SIZE_OF_LC:
				case SIZE_OF_UC:
				case SIZE_UC:
				case STORE_LC:
				case STORE_UC:
				case TYPE:
				case UC_AND:
				case UC_FOR:
				case UC_FORALL:
				case UC_IF:
				case UC_PERMUTE:
				case UC_PRODUCT:
				case UC_SEQUENCE_OF:
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
						case DOLLAR:
						case FALSE_LC:
						case FALSE_UC:
						case ID:
						case LC_PERMUTE:
						case LC_PRODUCT:
						case LC_SEQUENCE_OF:
						case LEFTP:
						case LEFTSBR:
						case MINUS:
						case NUMBER:
						case QUERY_LC:
						case QUERY_UC:
						case RANDOM_LC:
						case RANDOM_UC:
						case REAL:
						case SIZEOF_LC:
						case SIZEOF_UC:
						case SIZE_LC:
						case SIZE_OF_LC:
						case SIZE_OF_UC:
						case SIZE_UC:
						case STRING:
						case TRUE_LC:
						case TRUE_UC:
						case UC_PERMUTE:
						case UC_PRODUCT:
						case UC_SEQUENCE_OF:
							{
							alt1=1;
							}
							break;
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:5: statement[defer]
					{
					pushFollow(FOLLOW_statement_in_prog1148);
					statement1=statement(defer);
					state._fsp--;

					adaptor.addChild(root_0, statement1.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:24: function_definition[true]
					{
					pushFollow(FOLLOW_function_definition_in_prog1153);
					function_definition2=function_definition(true);
					state._fsp--;

					adaptor.addChild(root_0, function_definition2.getTree());

					}
					break;

				default :
					break loop1;
				}
			}

			EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_prog1158); 
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:912:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | function_call[defer] SEMIC |bif= built_in_function[defer] SEMIC | stand_alone_function[defer] SEMIC | return_statement[defer] SEMIC );
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
		Token SEMIC16=null;
		Token SEMIC18=null;
		Token SEMIC20=null;
		ParserRuleReturnScope de =null;
		ParserRuleReturnScope bif =null;
		ParserRuleReturnScope includeStatement4 =null;
		ParserRuleReturnScope declarationStatement6 =null;
		ParserRuleReturnScope printStatement8 =null;
		ParserRuleReturnScope assignment10 =null;
		ParserRuleReturnScope imperativeStatements13 =null;
		ParserRuleReturnScope function_call14 =null;
		ParserRuleReturnScope stand_alone_function17 =null;
		ParserRuleReturnScope return_statement19 =null;

		Object SEMIC5_tree=null;
		Object SEMIC7_tree=null;
		Object SEMIC9_tree=null;
		Object SEMIC11_tree=null;
		Object SEMIC12_tree=null;
		Object SEMIC15_tree=null;
		Object SEMIC16_tree=null;
		Object SEMIC18_tree=null;
		Object SEMIC20_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:915:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | function_call[defer] SEMIC |bif= built_in_function[defer] SEMIC | stand_alone_function[defer] SEMIC | return_statement[defer] SEMIC )
			int alt3=10;
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
			case RULE_BUILDER:
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
					alt3=7;
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
			case LC_PERMUTE:
			case LC_PRODUCT:
			case LC_SEQUENCE_OF:
			case QUERY_LC:
			case QUERY_UC:
			case RANDOM_LC:
			case RANDOM_UC:
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_OF_LC:
			case SIZE_OF_UC:
			case SIZE_UC:
			case UC_PERMUTE:
			case UC_PRODUCT:
			case UC_SEQUENCE_OF:
				{
				alt3=8;
				}
				break;
			case CREATE_LC:
			case CREATE_UC:
			case EXIT_LC:
			case EXIT_UC:
			case LC_AND:
			case SAVE_LC:
			case SAVE_UC:
			case STORE_LC:
			case STORE_UC:
			case UC_AND:
				{
				alt3=9;
				}
				break;
			case RETURN_LC:
			case RETURN_UC:
				{
				alt3=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:916:3: includeStatement[defer] ( SEMIC )?
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_includeStatement_in_statement1185);
					includeStatement4=includeStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, includeStatement4.getTree());

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:916:27: ( SEMIC )?
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( (LA2_0==SEMIC) ) {
						alt2=1;
					}
					switch (alt2) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:916:28: SEMIC
							{
							SEMIC5=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1189); 
							SEMIC5_tree = (Object)adaptor.create(SEMIC5);
							adaptor.addChild(root_0, SEMIC5_tree);

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:917:4: declarationStatement[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_declarationStatement_in_statement1196);
					declarationStatement6=declarationStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, declarationStatement6.getTree());

					SEMIC7=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1199); 
					SEMIC7_tree = (Object)adaptor.create(SEMIC7);
					adaptor.addChild(root_0, SEMIC7_tree);

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:918:4: printStatement[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_printStatement_in_statement1205);
					printStatement8=printStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, printStatement8.getTree());

					SEMIC9=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1208); 
					SEMIC9_tree = (Object)adaptor.create(SEMIC9);
					adaptor.addChild(root_0, SEMIC9_tree);

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:919:4: assignment[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_assignment_in_statement1213);
					assignment10=assignment(defer);
					state._fsp--;

					adaptor.addChild(root_0, assignment10.getTree());

					SEMIC11=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1216); 
					SEMIC11_tree = (Object)adaptor.create(SEMIC11);
					adaptor.addChild(root_0, SEMIC11_tree);

					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:920:4: de= dataExchange[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_dataExchange_in_statement1223);
					de=dataExchange(defer);
					state._fsp--;

					adaptor.addChild(root_0, de.getTree());

					SEMIC12=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1226); 
					SEMIC12_tree = (Object)adaptor.create(SEMIC12);
					adaptor.addChild(root_0, SEMIC12_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        // iff there's no assignment to a LHS element,
					        // then we store the imported data into the 
					        // current scope's symbol tables
					        if(null != (de!=null?((EugeneParser.dataExchange_return)de).e:null)) {
					            this.interp.recursiveStoringOf((de!=null?((EugeneParser.dataExchange_return)de).e:null));
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:934:4: imperativeStatements[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_imperativeStatements_in_statement1233);
					imperativeStatements13=imperativeStatements(defer);
					state._fsp--;

					adaptor.addChild(root_0, imperativeStatements13.getTree());

					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:935:4: function_call[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_function_call_in_statement1239);
					function_call14=function_call(defer);
					state._fsp--;

					adaptor.addChild(root_0, function_call14.getTree());

					SEMIC15=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1242); 
					SEMIC15_tree = (Object)adaptor.create(SEMIC15);
					adaptor.addChild(root_0, SEMIC15_tree);

					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:4: bif= built_in_function[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_built_in_function_in_statement1249);
					bif=built_in_function(defer);
					state._fsp--;

					adaptor.addChild(root_0, bif.getTree());

					SEMIC16=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1252); 
					SEMIC16_tree = (Object)adaptor.create(SEMIC16);
					adaptor.addChild(root_0, SEMIC16_tree);

					  
					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					//    try {
					        // iff there's no assignment to a LHS element,
					        // then we store the imported data into the 
					        // current scope's symbol tables

					// Ernst's brain is storming!
					//
					//--------------
					// what should happen with the returned result of a built-in-function?
					//--------------
					//
					// the user should specify what should happen with the result 
					// of built-in functions. this can be achieved by using 
					// assignments.
					// Example: lod = product(D);
					//     The list of devices (lod) contains then all enumerated
					//     devices of device D returned by the product function
					//
					// moreover, should we allow the specification of built-in functions 
					// as stand-alone? i.e. giving the user the possibility to neglect 
					// the returned result?
					//
					// here's an option: storing the result into the LMS.
					// no so ``time'' and ``memory'' efficient though.
					//        if(null != (bif!=null?((EugeneParser.built_in_function_return)bif).element:null)) {
					//            this.interp.recursiveStoringOf((bif!=null?((EugeneParser.built_in_function_return)bif).element:null));
					//        }
					//
					//    } catch(EugeneException ee) {
					//        printError(ee.getLocalizedMessage());
					//    }
					}	
						
					}
					break;
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:972:4: stand_alone_function[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_stand_alone_function_in_statement1261);
					stand_alone_function17=stand_alone_function(defer);
					state._fsp--;

					adaptor.addChild(root_0, stand_alone_function17.getTree());

					SEMIC18=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1264); 
					SEMIC18_tree = (Object)adaptor.create(SEMIC18);
					adaptor.addChild(root_0, SEMIC18_tree);

					}
					break;
				case 10 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:973:4: return_statement[defer] SEMIC
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_return_statement_in_statement1271);
					return_statement19=return_statement(defer);
					state._fsp--;

					adaptor.addChild(root_0, return_statement19.getTree());

					SEMIC20=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1274); 
					SEMIC20_tree = (Object)adaptor.create(SEMIC20);
					adaptor.addChild(root_0, SEMIC20_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:982:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | deviceDeclaration[defer] | ruleDeclaration[defer] | rulebuilderDeclaration[defer] | grammarDeclaration[defer] );
	public final EugeneParser.declarationStatement_return declarationStatement(boolean defer) throws RecognitionException {
		EugeneParser.declarationStatement_return retval = new EugeneParser.declarationStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope v =null;
		ParserRuleReturnScope containerDeclaration21 =null;
		ParserRuleReturnScope propertyDeclaration22 =null;
		ParserRuleReturnScope typeDeclaration23 =null;
		ParserRuleReturnScope instantiation24 =null;
		ParserRuleReturnScope interactionDeclaration25 =null;
		ParserRuleReturnScope deviceDeclaration26 =null;
		ParserRuleReturnScope ruleDeclaration27 =null;
		ParserRuleReturnScope rulebuilderDeclaration28 =null;
		ParserRuleReturnScope grammarDeclaration29 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:984:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | deviceDeclaration[defer] | ruleDeclaration[defer] | rulebuilderDeclaration[defer] | grammarDeclaration[defer] )
			int alt4=10;
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
			case DEVICE:
				{
				alt4=7;
				}
				break;
			case RULE:
				{
				alt4=8;
				}
				break;
			case RULE_BUILDER:
				{
				alt4=9;
				}
				break;
			case GRAMMAR:
				{
				alt4=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:984:4: v= variableDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_variableDeclaration_in_declarationStatement1295);
					v=variableDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, v.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.name = (v!=null?((EugeneParser.variableDeclaration_return)v).varname:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:989:4: containerDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_containerDeclaration_in_declarationStatement1303);
					containerDeclaration21=containerDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, containerDeclaration21.getTree());

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:990:4: propertyDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement1309);
					propertyDeclaration22=propertyDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, propertyDeclaration22.getTree());

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:991:4: typeDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_typeDeclaration_in_declarationStatement1315);
					typeDeclaration23=typeDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, typeDeclaration23.getTree());

					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:992:4: instantiation[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_instantiation_in_declarationStatement1321);
					instantiation24=instantiation(defer);
					state._fsp--;

					adaptor.addChild(root_0, instantiation24.getTree());

					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:993:4: interactionDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement1327);
					interactionDeclaration25=interactionDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, interactionDeclaration25.getTree());

					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:4: deviceDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement1333);
					deviceDeclaration26=deviceDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, deviceDeclaration26.getTree());

					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:995:4: ruleDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement1339);
					ruleDeclaration27=ruleDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, ruleDeclaration27.getTree());

					}
					break;
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:996:4: rulebuilderDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_rulebuilderDeclaration_in_declarationStatement1345);
					rulebuilderDeclaration28=rulebuilderDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, rulebuilderDeclaration28.getTree());

					}
					break;
				case 10 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:997:4: grammarDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_grammarDeclaration_in_declarationStatement1351);
					grammarDeclaration29=grammarDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, grammarDeclaration29.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1000:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] );
	public final EugeneParser.variableDeclaration_return variableDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.variableDeclaration_return retval = new EugeneParser.variableDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NUM30=null;
		Token TXT31=null;
		Token TXT32=null;
		Token LEFTSBR33=null;
		Token RIGHTSBR34=null;
		Token NUM35=null;
		Token LEFTSBR36=null;
		Token RIGHTSBR37=null;
		Token set38=null;
		ParserRuleReturnScope n =null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope tl =null;
		ParserRuleReturnScope nl =null;
		ParserRuleReturnScope b =null;

		Object NUM30_tree=null;
		Object TXT31_tree=null;
		Object TXT32_tree=null;
		Object LEFTSBR33_tree=null;
		Object RIGHTSBR34_tree=null;
		Object NUM35_tree=null;
		Object LEFTSBR36_tree=null;
		Object RIGHTSBR37_tree=null;
		Object set38_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1002:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1002:4: NUM n= numdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					NUM30=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1369); 
					NUM30_tree = (Object)adaptor.create(NUM30);
					adaptor.addChild(root_0, NUM30_tree);

					pushFollow(FOLLOW_numdecl_in_variableDeclaration1373);
					n=numdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, n.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (n!=null?((EugeneParser.numdecl_return)n).varname:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1007:4: TXT t= txtdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					TXT31=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1384); 
					TXT31_tree = (Object)adaptor.create(TXT31);
					adaptor.addChild(root_0, TXT31_tree);

					pushFollow(FOLLOW_txtdecl_in_variableDeclaration1388);
					t=txtdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (t!=null?((EugeneParser.txtdecl_return)t).varname:null);
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1012:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					TXT32=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1399); 
					TXT32_tree = (Object)adaptor.create(TXT32);
					adaptor.addChild(root_0, TXT32_tree);

					LEFTSBR33=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1401); 
					LEFTSBR33_tree = (Object)adaptor.create(LEFTSBR33);
					adaptor.addChild(root_0, LEFTSBR33_tree);

					RIGHTSBR34=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1403); 
					RIGHTSBR34_tree = (Object)adaptor.create(RIGHTSBR34);
					adaptor.addChild(root_0, RIGHTSBR34_tree);

					pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration1407);
					tl=txtlistdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, tl.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (tl!=null?((EugeneParser.txtlistdecl_return)tl).varname:null);
					}	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					NUM35=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1418); 
					NUM35_tree = (Object)adaptor.create(NUM35);
					adaptor.addChild(root_0, NUM35_tree);

					LEFTSBR36=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1420); 
					LEFTSBR36_tree = (Object)adaptor.create(LEFTSBR36);
					adaptor.addChild(root_0, LEFTSBR36_tree);

					RIGHTSBR37=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1422); 
					RIGHTSBR37_tree = (Object)adaptor.create(RIGHTSBR37);
					adaptor.addChild(root_0, RIGHTSBR37_tree);

					pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1426);
					nl=numlistdecl(defer);
					state._fsp--;

					adaptor.addChild(root_0, nl.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.varname = (nl!=null?((EugeneParser.numlistdecl_return)nl).varname:null);
					}	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1022:4: ( BOOLEAN | BOOL ) b= booldecl[defer]
					{
					root_0 = (Object)adaptor.nil();


					set38=input.LT(1);
					if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set38));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_booldecl_in_variableDeclaration1445);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1029:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
	public final EugeneParser.numdecl_return numdecl(boolean defer) throws RecognitionException {
		EugeneParser.numdecl_return retval = new EugeneParser.numdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID39=null;
		Token COMMA40=null;
		Token ID42=null;
		Token EQUALS43=null;
		Token COMMA44=null;
		ParserRuleReturnScope ex =null;
		ParserRuleReturnScope numdecl41 =null;
		ParserRuleReturnScope numdecl45 =null;

		Object ID39_tree=null;
		Object COMMA40_tree=null;
		Object ID42_tree=null;
		Object EQUALS43_tree=null;
		Object COMMA44_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1031:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1031:4: ID ( COMMA numdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID39=(Token)match(input,ID,FOLLOW_ID_in_numdecl1468); 
					ID39_tree = (Object)adaptor.create(ID39);
					adaptor.addChild(root_0, ID39_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    declareVariableNoValue((ID39!=null?ID39.getText():null), EugeneConstants.NUM);
					    retval.varname = (ID39!=null?ID39.getText():null);
					}
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1036:5: ( COMMA numdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1036:6: COMMA numdecl[defer]
							{
							COMMA40=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1474); 
							COMMA40_tree = (Object)adaptor.create(COMMA40);
							adaptor.addChild(root_0, COMMA40_tree);

							pushFollow(FOLLOW_numdecl_in_numdecl1476);
							numdecl41=numdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numdecl41.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1037:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID42=(Token)match(input,ID,FOLLOW_ID_in_numdecl1484); 
					ID42_tree = (Object)adaptor.create(ID42);
					adaptor.addChild(root_0, ID42_tree);

					EQUALS43=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1486); 
					EQUALS43_tree = (Object)adaptor.create(EQUALS43);
					adaptor.addChild(root_0, EQUALS43_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1037:14: (ex= expr[defer] )
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1037:15: ex= expr[defer]
					{
					pushFollow(FOLLOW_expr_in_numdecl1491);
					ex=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, ex.getTree());

					}


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    declareVariableWithValueNum((ID42!=null?ID42.getText():null), (ex!=null?((EugeneParser.expr_return)ex).p:null), (ex!=null?((EugeneParser.expr_return)ex).index:0));
					    retval.varname = (ID42!=null?ID42.getText():null);
					}
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1042:5: ( COMMA numdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1042:6: COMMA numdecl[defer]
							{
							COMMA44=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1499); 
							COMMA44_tree = (Object)adaptor.create(COMMA44);
							adaptor.addChild(root_0, COMMA44_tree);

							pushFollow(FOLLOW_numdecl_in_numdecl1501);
							numdecl45=numdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numdecl45.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1045:1: txtdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? );
	public final EugeneParser.txtdecl_return txtdecl(boolean defer) throws RecognitionException {
		EugeneParser.txtdecl_return retval = new EugeneParser.txtdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID46=null;
		Token COMMA47=null;
		Token EQUALS49=null;
		Token COMMA50=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope txtdecl48 =null;
		ParserRuleReturnScope txtdecl51 =null;

		Object var_tree=null;
		Object ID46_tree=null;
		Object COMMA47_tree=null;
		Object EQUALS49_tree=null;
		Object COMMA50_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1047:2: ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1047:4: ID ( COMMA txtdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID46=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1521); 
					ID46_tree = (Object)adaptor.create(ID46);
					adaptor.addChild(root_0, ID46_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID46!=null?ID46.getText():null), EugeneConstants.TXT);
								retval.varname = (ID46!=null?ID46.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1053:5: ( COMMA txtdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1053:6: COMMA txtdecl[defer]
							{
							COMMA47=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1528); 
							COMMA47_tree = (Object)adaptor.create(COMMA47);
							adaptor.addChild(root_0, COMMA47_tree);

							pushFollow(FOLLOW_txtdecl_in_txtdecl1530);
							txtdecl48=txtdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtdecl48.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1055:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1541); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS49=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1543); 
					EQUALS49_tree = (Object)adaptor.create(EQUALS49);
					adaptor.addChild(root_0, EQUALS49_tree);

					pushFollow(FOLLOW_expr_in_txtdecl1547);
					let=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, let.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableWithValueTxt((var!=null?var.getText():null), (let!=null?((EugeneParser.expr_return)let).p:null), (let!=null?((EugeneParser.expr_return)let).index:0));
								retval.varname = (var!=null?var.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1061:5: ( COMMA txtdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1061:6: COMMA txtdecl[defer]
							{
							COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1555); 
							COMMA50_tree = (Object)adaptor.create(COMMA50);
							adaptor.addChild(root_0, COMMA50_tree);

							pushFollow(FOLLOW_txtdecl_in_txtdecl1557);
							txtdecl51=txtdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtdecl51.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1064:1: txtlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? );
	public final EugeneParser.txtlistdecl_return txtlistdecl(boolean defer) throws RecognitionException {
		EugeneParser.txtlistdecl_return retval = new EugeneParser.txtlistdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID52=null;
		Token COMMA53=null;
		Token EQUALS55=null;
		Token COMMA56=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope txtlistdecl54 =null;
		ParserRuleReturnScope txtlistdecl57 =null;

		Object var_tree=null;
		Object ID52_tree=null;
		Object COMMA53_tree=null;
		Object EQUALS55_tree=null;
		Object COMMA56_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1066:2: ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1066:4: ID ( COMMA txtlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID52=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1577); 
					ID52_tree = (Object)adaptor.create(ID52);
					adaptor.addChild(root_0, ID52_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID52!=null?ID52.getText():null), EugeneConstants.TXTLIST);
								retval.varname = (ID52!=null?ID52.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1072:5: ( COMMA txtlistdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1072:6: COMMA txtlistdecl[defer]
							{
							COMMA53=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1584); 
							COMMA53_tree = (Object)adaptor.create(COMMA53);
							adaptor.addChild(root_0, COMMA53_tree);

							pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1586);
							txtlistdecl54=txtlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtlistdecl54.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1073:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1596); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS55=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1598); 
					EQUALS55_tree = (Object)adaptor.create(EQUALS55);
					adaptor.addChild(root_0, EQUALS55_tree);

					typeList = EugeneConstants.TXT;
					pushFollow(FOLLOW_expr_in_txtlistdecl1604);
					let=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, let.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableWithValueTxtList((var!=null?var.getText():null), (let!=null?((EugeneParser.expr_return)let).p:null));
								retval.varname = (var!=null?var.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1079:5: ( COMMA txtlistdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1079:6: COMMA txtlistdecl[defer]
							{
							COMMA56=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1612); 
							COMMA56_tree = (Object)adaptor.create(COMMA56);
							adaptor.addChild(root_0, COMMA56_tree);

							pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1614);
							txtlistdecl57=txtlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, txtlistdecl57.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1082:1: numlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? );
	public final EugeneParser.numlistdecl_return numlistdecl(boolean defer) throws RecognitionException {
		EugeneParser.numlistdecl_return retval = new EugeneParser.numlistdecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID58=null;
		Token COMMA59=null;
		Token EQUALS61=null;
		Token COMMA62=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope numlistdecl60 =null;
		ParserRuleReturnScope numlistdecl63 =null;

		Object var_tree=null;
		Object ID58_tree=null;
		Object COMMA59_tree=null;
		Object EQUALS61_tree=null;
		Object COMMA62_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1084:2: ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1084:4: ID ( COMMA numlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID58=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1634); 
					ID58_tree = (Object)adaptor.create(ID58);
					adaptor.addChild(root_0, ID58_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID58!=null?ID58.getText():null), EugeneConstants.NUMLIST);
								retval.varname = (ID58!=null?ID58.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1090:5: ( COMMA numlistdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1090:6: COMMA numlistdecl[defer]
							{
							COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1641); 
							COMMA59_tree = (Object)adaptor.create(COMMA59);
							adaptor.addChild(root_0, COMMA59_tree);

							pushFollow(FOLLOW_numlistdecl_in_numlistdecl1643);
							numlistdecl60=numlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numlistdecl60.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1091:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1653); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS61=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1655); 
					EQUALS61_tree = (Object)adaptor.create(EQUALS61);
					adaptor.addChild(root_0, EQUALS61_tree);

					 typeList = EugeneConstants.NUM;
					pushFollow(FOLLOW_expr_in_numlistdecl1660);
					let=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, let.getTree());


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableWithValueNumList((var!=null?var.getText():null), (let!=null?((EugeneParser.expr_return)let).p:null));
								retval.varname = (var!=null?var.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1097:5: ( COMMA numlistdecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1097:6: COMMA numlistdecl[defer]
							{
							COMMA62=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1668); 
							COMMA62_tree = (Object)adaptor.create(COMMA62);
							adaptor.addChild(root_0, COMMA62_tree);

							pushFollow(FOLLOW_numlistdecl_in_numlistdecl1670);
							numlistdecl63=numlistdecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, numlistdecl63.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1100:1: booldecl[boolean defer] returns [String varname] : ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] );
	public final EugeneParser.booldecl_return booldecl(boolean defer) throws RecognitionException {
		EugeneParser.booldecl_return retval = new EugeneParser.booldecl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token var=null;
		Token ID64=null;
		Token COMMA65=null;
		Token EQUALS67=null;
		ParserRuleReturnScope let =null;
		ParserRuleReturnScope booldecl66 =null;

		Object var_tree=null;
		Object ID64_tree=null;
		Object COMMA65_tree=null;
		Object EQUALS67_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1102:2: ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1102:4: ID ( COMMA booldecl[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					ID64=(Token)match(input,ID,FOLLOW_ID_in_booldecl1690); 
					ID64_tree = (Object)adaptor.create(ID64);
					adaptor.addChild(root_0, ID64_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								declareVariableNoValue((ID64!=null?ID64.getText():null), EugeneConstants.BOOLEAN);
								retval.varname = (ID64!=null?ID64.getText():null);
							}
							
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1108:5: ( COMMA booldecl[defer] )?
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1108:6: COMMA booldecl[defer]
							{
							COMMA65=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1697); 
							COMMA65_tree = (Object)adaptor.create(COMMA65);
							adaptor.addChild(root_0, COMMA65_tree);

							pushFollow(FOLLOW_booldecl_in_booldecl1699);
							booldecl66=booldecl(defer);
							state._fsp--;

							adaptor.addChild(root_0, booldecl66.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1109:4: var= ID EQUALS let= expr[defer]
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1709); 
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);

					EQUALS67=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1711); 
					EQUALS67_tree = (Object)adaptor.create(EQUALS67);
					adaptor.addChild(root_0, EQUALS67_tree);

					pushFollow(FOLLOW_expr_in_booldecl1715);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1118:1: propertyDeclaration[boolean defer] : PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP ;
	public final EugeneParser.propertyDeclaration_return propertyDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.propertyDeclaration_return retval = new EugeneParser.propertyDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token nameToken=null;
		Token PROPERTY68=null;
		Token LEFTP69=null;
		Token RIGHTP70=null;
		ParserRuleReturnScope typeToken =null;

		Object nameToken_tree=null;
		Object PROPERTY68_tree=null;
		Object LEFTP69_tree=null;
		Object RIGHTP70_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1119:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1119:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			PROPERTY68=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1733); 
			PROPERTY68_tree = (Object)adaptor.create(PROPERTY68);
			adaptor.addChild(root_0, PROPERTY68_tree);

			nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1737); 
			nameToken_tree = (Object)adaptor.create(nameToken);
			adaptor.addChild(root_0, nameToken_tree);

			LEFTP69=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1739); 
			LEFTP69_tree = (Object)adaptor.create(LEFTP69);
			adaptor.addChild(root_0, LEFTP69_tree);

			pushFollow(FOLLOW_propertyType_in_propertyDeclaration1743);
			typeToken=propertyType();
			state._fsp--;

			adaptor.addChild(root_0, typeToken.getTree());

			RIGHTP70=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1745); 
			RIGHTP70_tree = (Object)adaptor.create(RIGHTP70);
			adaptor.addChild(root_0, RIGHTP70_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1132:1: propertyType returns [String type] : ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) );
	public final EugeneParser.propertyType_return propertyType() throws RecognitionException {
		EugeneParser.propertyType_return retval = new EugeneParser.propertyType_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token TXT71=null;
		Token TXT72=null;
		Token LEFTSBR73=null;
		Token RIGHTSBR74=null;
		Token NUM75=null;
		Token NUM76=null;
		Token LEFTSBR77=null;
		Token RIGHTSBR78=null;
		Token set79=null;

		Object TXT71_tree=null;
		Object TXT72_tree=null;
		Object LEFTSBR73_tree=null;
		Object RIGHTSBR74_tree=null;
		Object NUM75_tree=null;
		Object NUM76_tree=null;
		Object LEFTSBR77_tree=null;
		Object RIGHTSBR78_tree=null;
		Object set79_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1134:2: ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1134:4: TXT
					{
					root_0 = (Object)adaptor.nil();


					TXT71=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1764); 
					TXT71_tree = (Object)adaptor.create(TXT71);
					adaptor.addChild(root_0, TXT71_tree);


					retval.type = EugeneConstants.TXT;	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1137:4: TXT LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					TXT72=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1771); 
					TXT72_tree = (Object)adaptor.create(TXT72);
					adaptor.addChild(root_0, TXT72_tree);

					LEFTSBR73=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1773); 
					LEFTSBR73_tree = (Object)adaptor.create(LEFTSBR73);
					adaptor.addChild(root_0, LEFTSBR73_tree);

					RIGHTSBR74=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1775); 
					RIGHTSBR74_tree = (Object)adaptor.create(RIGHTSBR74);
					adaptor.addChild(root_0, RIGHTSBR74_tree);


					retval.type = EugeneConstants.TXTLIST;	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1140:4: NUM
					{
					root_0 = (Object)adaptor.nil();


					NUM75=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1782); 
					NUM75_tree = (Object)adaptor.create(NUM75);
					adaptor.addChild(root_0, NUM75_tree);


					retval.type = EugeneConstants.NUM;	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1143:4: NUM LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					NUM76=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1790); 
					NUM76_tree = (Object)adaptor.create(NUM76);
					adaptor.addChild(root_0, NUM76_tree);

					LEFTSBR77=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1792); 
					LEFTSBR77_tree = (Object)adaptor.create(LEFTSBR77);
					adaptor.addChild(root_0, LEFTSBR77_tree);

					RIGHTSBR78=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1794); 
					RIGHTSBR78_tree = (Object)adaptor.create(RIGHTSBR78);
					adaptor.addChild(root_0, RIGHTSBR78_tree);


					retval.type = EugeneConstants.NUMLIST;	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1146:4: ( BOOLEAN | BOOL )
					{
					root_0 = (Object)adaptor.nil();


					set79=input.LT(1);
					if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set79));
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1155:1: typeDeclaration[boolean defer] : ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? );
	public final EugeneParser.typeDeclaration_return typeDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.typeDeclaration_return retval = new EugeneParser.typeDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token nameToken=null;
		Token TYPE81=null;
		Token LEFTP82=null;
		Token RIGHTP83=null;
		ParserRuleReturnScope lstToken =null;
		ParserRuleReturnScope partTypeDeclaration80 =null;

		Object nameToken_tree=null;
		Object TYPE81_tree=null;
		Object LEFTP82_tree=null;
		Object RIGHTP83_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1156:2: ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1156:4: partTypeDeclaration[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1823);
					partTypeDeclaration80=partTypeDeclaration(defer);
					state._fsp--;

					adaptor.addChild(root_0, partTypeDeclaration80.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1157:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1157:4: ( TYPE )
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1157:5: TYPE
					{
					TYPE81=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1830); 
					TYPE81_tree = (Object)adaptor.create(TYPE81);
					adaptor.addChild(root_0, TYPE81_tree);

					}

					nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1835); 
					nameToken_tree = (Object)adaptor.create(nameToken);
					adaptor.addChild(root_0, nameToken_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1157:24: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==LEFTP) ) {
						alt22=1;
					}
					switch (alt22) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1157:25: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
							{
							LEFTP82=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1838); 
							LEFTP82_tree = (Object)adaptor.create(LEFTP82);
							adaptor.addChild(root_0, LEFTP82_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1157:31: (lstToken= listOfIDs[defer] )?
							int alt21=2;
							int LA21_0 = input.LA(1);
							if ( (LA21_0==ID) ) {
								alt21=1;
							}
							switch (alt21) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1157:32: lstToken= listOfIDs[defer]
									{
									pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1843);
									lstToken=listOfIDs(defer);
									state._fsp--;

									adaptor.addChild(root_0, lstToken.getTree());

									}
									break;

							}

							RIGHTP83=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1848); 
							RIGHTP83_tree = (Object)adaptor.create(RIGHTP83);
							adaptor.addChild(root_0, RIGHTP83_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1170:1: partTypeDeclaration[boolean defer] : ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? ;
	public final EugeneParser.partTypeDeclaration_return partTypeDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.partTypeDeclaration_return retval = new EugeneParser.partTypeDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token nameToken=null;
		Token set84=null;
		Token LEFTP85=null;
		Token RIGHTP86=null;
		ParserRuleReturnScope lstToken =null;

		Object nameToken_tree=null;
		Object set84_tree=null;
		Object LEFTP85_tree=null;
		Object RIGHTP86_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1171:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1171:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			set84=input.LT(1);
			if ( (input.LA(1) >= PART && input.LA(1) <= PART_TYPE) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set84));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1876); 
			nameToken_tree = (Object)adaptor.create(nameToken);
			adaptor.addChild(root_0, nameToken_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1171:35: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==LEFTP) ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1171:36: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
					{
					LEFTP85=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1879); 
					LEFTP85_tree = (Object)adaptor.create(LEFTP85);
					adaptor.addChild(root_0, LEFTP85_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1171:42: (lstToken= listOfIDs[defer] )?
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0==ID) ) {
						alt24=1;
					}
					switch (alt24) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1171:43: lstToken= listOfIDs[defer]
							{
							pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1884);
							lstToken=listOfIDs(defer);
							state._fsp--;

							adaptor.addChild(root_0, lstToken.getTree());

							}
							break;

					}

					RIGHTP86=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1889); 
					RIGHTP86_tree = (Object)adaptor.create(RIGHTP86);
					adaptor.addChild(root_0, RIGHTP86_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1187:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
	public final EugeneParser.containerDeclaration_return containerDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.containerDeclaration_return retval = new EugeneParser.containerDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token c=null;
		Token a=null;
		Token name=null;
		Token LEFTSBR87=null;
		Token RIGHTSBR88=null;
		Token LEFTP89=null;
		Token RIGHTP91=null;
		ParserRuleReturnScope list_of_declarations90 =null;

		Object c_tree=null;
		Object a_tree=null;
		Object name_tree=null;
		Object LEFTSBR87_tree=null;
		Object RIGHTSBR88_tree=null;
		Object LEFTP89_tree=null;
		Object RIGHTP91_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:2: ( (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:5: c= COLLECTION
					{
					c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1916); 
					c_tree = (Object)adaptor.create(c);
					adaptor.addChild(root_0, c_tree);

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:21: a= ARRAY ( LEFTSBR RIGHTSBR )?
					{
					a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1923); 
					a_tree = (Object)adaptor.create(a);
					adaptor.addChild(root_0, a_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:29: ( LEFTSBR RIGHTSBR )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==LEFTSBR) ) {
						alt26=1;
					}
					switch (alt26) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:30: LEFTSBR RIGHTSBR
							{
							LEFTSBR87=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1926); 
							LEFTSBR87_tree = (Object)adaptor.create(LEFTSBR87);
							adaptor.addChild(root_0, LEFTSBR87_tree);

							RIGHTSBR88=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1928); 
							RIGHTSBR88_tree = (Object)adaptor.create(RIGHTSBR88);
							adaptor.addChild(root_0, RIGHTSBR88_tree);

							}
							break;

					}

					}

					}
					break;

			}

			name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1936); 
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
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1203:4: ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==LEFTP) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1203:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
					{
					LEFTP89=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1941); 
					LEFTP89_tree = (Object)adaptor.create(LEFTP89);
					adaptor.addChild(root_0, LEFTP89_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1203:11: ( list_of_declarations[defer] )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==ARRAY||(LA28_0 >= BOOL && LA28_0 <= COLLECTION)||LA28_0==DEVICE||LA28_0==DOLLAR||(LA28_0 >= FALSE_LC && LA28_0 <= FALSE_UC)||LA28_0==GRAMMAR||LA28_0==ID||LA28_0==INTERACTION||(LA28_0 >= LC_PERMUTE && LA28_0 <= LC_PRODUCT)||LA28_0==LC_SEQUENCE_OF||(LA28_0 >= LEFTP && LA28_0 <= LEFTSBR)||LA28_0==MINUS||(LA28_0 >= NUM && LA28_0 <= NUMBER)||(LA28_0 >= PART && LA28_0 <= PART_TYPE)||(LA28_0 >= PROPERTY && LA28_0 <= RANDOM_UC)||LA28_0==REAL||(LA28_0 >= RULE && LA28_0 <= RULE_BUILDER)||(LA28_0 >= SIZEOF_LC && LA28_0 <= SIZE_UC)||(LA28_0 >= STRING && LA28_0 <= TYPE)||(LA28_0 >= UC_PERMUTE && LA28_0 <= UC_PRODUCT)||LA28_0==UC_SEQUENCE_OF) ) {
						alt28=1;
					}
					switch (alt28) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1203:12: list_of_declarations[defer]
							{
							pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1944);
							list_of_declarations90=list_of_declarations(defer);
							state._fsp--;

							adaptor.addChild(root_0, list_of_declarations90.getTree());

							}
							break;

					}

					RIGHTP91=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1949); 
					RIGHTP91_tree = (Object)adaptor.create(RIGHTP91);
					adaptor.addChild(root_0, RIGHTP91_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1218:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
	public final EugeneParser.list_of_declarations_return list_of_declarations(boolean defer) throws RecognitionException {
		EugeneParser.list_of_declarations_return retval = new EugeneParser.list_of_declarations_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA92=null;
		ParserRuleReturnScope ds =null;
		ParserRuleReturnScope exp =null;
		ParserRuleReturnScope lod =null;

		Object COMMA92_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1220:2: ( (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1220:4: (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1220:4: (ds= declarationStatement[defer] |exp= expr[defer] )
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
			case RULE_BUILDER:
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
			case FALSE_LC:
			case FALSE_UC:
			case LC_PERMUTE:
			case LC_PRODUCT:
			case LC_SEQUENCE_OF:
			case LEFTP:
			case LEFTSBR:
			case MINUS:
			case NUMBER:
			case QUERY_LC:
			case QUERY_UC:
			case RANDOM_LC:
			case RANDOM_UC:
			case REAL:
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_OF_LC:
			case SIZE_OF_UC:
			case SIZE_UC:
			case STRING:
			case TRUE_LC:
			case TRUE_UC:
			case UC_PERMUTE:
			case UC_PRODUCT:
			case UC_SEQUENCE_OF:
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1220:6: ds= declarationStatement[defer]
					{
					pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1982);
					ds=declarationStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, ds.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1220:39: exp= expr[defer]
					{
					pushFollow(FOLLOW_expr_in_list_of_declarations1989);
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

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1234:5: ( COMMA lod= list_of_declarations[defer] )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==COMMA) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1234:7: COMMA lod= list_of_declarations[defer]
					{
					COMMA92=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1997); 
					COMMA92_tree = (Object)adaptor.create(COMMA92);
					adaptor.addChild(root_0, COMMA92_tree);

					pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations2001);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1240:1: instantiation[boolean defer] : t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
	public final EugeneParser.instantiation_return instantiation(boolean defer) throws RecognitionException {
		EugeneParser.instantiation_return retval = new EugeneParser.instantiation_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token t=null;
		Token LEFTP93=null;
		Token RIGHTP94=null;
		ParserRuleReturnScope n =null;
		ParserRuleReturnScope dotToken =null;
		ParserRuleReturnScope valueToken =null;

		Object t_tree=null;
		Object LEFTP93_tree=null;
		Object RIGHTP94_tree=null;


		NamedElement type = null;
		String instance_name = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1245:2: (t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1245:4: t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			t=(Token)match(input,ID,FOLLOW_ID_in_instantiation2029); 
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
				
			pushFollow(FOLLOW_dynamic_naming_in_instantiation2035);
			n=dynamic_naming(defer);
			state._fsp--;

			adaptor.addChild(root_0, n.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    instance_name = (n!=null?((EugeneParser.dynamic_naming_return)n).name:null);	
			}
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1265:4: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==LEFTP) ) {
				alt33=1;
			}
			switch (alt33) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1265:6: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
					{
					LEFTP93=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation2042); 
					LEFTP93_tree = (Object)adaptor.create(LEFTP93);
					adaptor.addChild(root_0, LEFTP93_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1265:12: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
					int alt32=3;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==DOT) ) {
						alt32=1;
					}
					else if ( (LA32_0==DOLLAR||(LA32_0 >= FALSE_LC && LA32_0 <= FALSE_UC)||LA32_0==ID||(LA32_0 >= LC_PERMUTE && LA32_0 <= LC_PRODUCT)||LA32_0==LC_SEQUENCE_OF||(LA32_0 >= LEFTP && LA32_0 <= LEFTSBR)||LA32_0==MINUS||LA32_0==NUMBER||(LA32_0 >= QUERY_LC && LA32_0 <= RANDOM_UC)||LA32_0==REAL||(LA32_0 >= SIZEOF_LC && LA32_0 <= SIZE_UC)||(LA32_0 >= STRING && LA32_0 <= TRUE_UC)||(LA32_0 >= UC_PERMUTE && LA32_0 <= UC_PRODUCT)||LA32_0==UC_SEQUENCE_OF) ) {
						alt32=2;
					}
					switch (alt32) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1265:13: dotToken= listOfDotValues[defer]
							{
							pushFollow(FOLLOW_listOfDotValues_in_instantiation2047);
							dotToken=listOfDotValues(defer);
							state._fsp--;

							adaptor.addChild(root_0, dotToken.getTree());

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1265:45: valueToken= listOfValues[defer, (ComponentType)type]
							{
							pushFollow(FOLLOW_listOfValues_in_instantiation2052);
							valueToken=listOfValues(defer, (ComponentType)type);
							state._fsp--;

							adaptor.addChild(root_0, valueToken.getTree());

							}
							break;

					}

					RIGHTP94=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation2057); 
					RIGHTP94_tree = (Object)adaptor.create(RIGHTP94);
					adaptor.addChild(root_0, RIGHTP94_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1299:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
	public final EugeneParser.listOfDotValues_return listOfDotValues(boolean defer) throws RecognitionException {
		EugeneParser.listOfDotValues_return retval = new EugeneParser.listOfDotValues_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token prop=null;
		Token p=null;
		Token DOT95=null;
		Token LEFTP96=null;
		Token RIGHTP97=null;
		Token COMMA98=null;
		Token DOT99=null;
		Token LEFTP100=null;
		Token RIGHTP101=null;
		ParserRuleReturnScope v1 =null;
		ParserRuleReturnScope v2 =null;

		Object prop_tree=null;
		Object p_tree=null;
		Object DOT95_tree=null;
		Object LEFTP96_tree=null;
		Object RIGHTP97_tree=null;
		Object COMMA98_tree=null;
		Object DOT99_tree=null;
		Object LEFTP100_tree=null;
		Object RIGHTP101_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1300:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1300:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
			{
			root_0 = (Object)adaptor.nil();


			DOT95=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues2080); 
			DOT95_tree = (Object)adaptor.create(DOT95);
			adaptor.addChild(root_0, DOT95_tree);

			prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues2084); 
			prop_tree = (Object)adaptor.create(prop);
			adaptor.addChild(root_0, prop_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {		
			    try {
			        addToPropertyListHolder((prop!=null?prop.getText():null));
			    } catch(EugeneException ee) {
			        printError(ee.getMessage());
			    }				
			}			
				
			LEFTP96=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues2088); 
			LEFTP96_tree = (Object)adaptor.create(LEFTP96);
			adaptor.addChild(root_0, LEFTP96_tree);

			pushFollow(FOLLOW_expr_in_listOfDotValues2092);
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
				
			RIGHTP97=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues2097); 
			RIGHTP97_tree = (Object)adaptor.create(RIGHTP97);
			adaptor.addChild(root_0, RIGHTP97_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1317:2: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
			loop34:
			while (true) {
				int alt34=2;
				int LA34_0 = input.LA(1);
				if ( (LA34_0==COMMA) ) {
					alt34=1;
				}

				switch (alt34) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1317:3: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
					{
					COMMA98=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues2102); 
					COMMA98_tree = (Object)adaptor.create(COMMA98);
					adaptor.addChild(root_0, COMMA98_tree);

					DOT99=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues2104); 
					DOT99_tree = (Object)adaptor.create(DOT99);
					adaptor.addChild(root_0, DOT99_tree);

					p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues2108); 
					p_tree = (Object)adaptor.create(p);
					adaptor.addChild(root_0, p_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
					    try {
					        addToPropertyListHolder((p!=null?p.getText():null));
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }				
					}				
						
					LEFTP100=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues2112); 
					LEFTP100_tree = (Object)adaptor.create(LEFTP100);
					adaptor.addChild(root_0, LEFTP100_tree);

					pushFollow(FOLLOW_expr_in_listOfDotValues2116);
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
						
					RIGHTP101=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues2121); 
					RIGHTP101_tree = (Object)adaptor.create(RIGHTP101);
					adaptor.addChild(root_0, RIGHTP101_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1336:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
	public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
		EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA102=null;
		ParserRuleReturnScope val1 =null;
		ParserRuleReturnScope val2 =null;

		Object COMMA102_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1337:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1338:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
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
					
			pushFollow(FOLLOW_expr_in_listOfValues2142);
			val1=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, val1.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    propertyValuesHolder.add((val1!=null?((EugeneParser.expr_return)val1).p:null));
			}				
						
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1362:6: ( COMMA val2= expr[defer] )*
			loop35:
			while (true) {
				int alt35=2;
				int LA35_0 = input.LA(1);
				if ( (LA35_0==COMMA) ) {
					alt35=1;
				}

				switch (alt35) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1362:7: COMMA val2= expr[defer]
					{
					COMMA102=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues2148); 
					COMMA102_tree = (Object)adaptor.create(COMMA102);
					adaptor.addChild(root_0, COMMA102_tree);


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
					               
					pushFollow(FOLLOW_expr_in_listOfValues2154);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1395:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
	public final EugeneParser.deviceDeclaration_return deviceDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.deviceDeclaration_return retval = new EugeneParser.deviceDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token DEVICE103=null;
		Token LEFTP104=null;
		Token RIGHTP105=null;
		ParserRuleReturnScope dcs =null;

		Object n_tree=null;
		Object DEVICE103_tree=null;
		Object LEFTP104_tree=null;
		Object RIGHTP105_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1396:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1396:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			DEVICE103=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration2177); 
			DEVICE103_tree = (Object)adaptor.create(DEVICE103);
			adaptor.addChild(root_0, DEVICE103_tree);

			n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration2181); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1396:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0==LEFTP) ) {
				alt37=1;
			}
			switch (alt37) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1396:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
					{
					LEFTP104=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration2184); 
					LEFTP104_tree = (Object)adaptor.create(LEFTP104);
					adaptor.addChild(root_0, LEFTP104_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1396:23: (dcs= deviceComponents[defer] )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==ID||LA36_0==LEFTSBR||LA36_0==MINUS||LA36_0==PLUS) ) {
						alt36=1;
					}
					switch (alt36) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1396:24: dcs= deviceComponents[defer]
							{
							pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration2189);
							dcs=deviceComponents(defer);
							state._fsp--;

							adaptor.addChild(root_0, dcs.getTree());

							}
							break;

					}

					RIGHTP105=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration2194); 
					RIGHTP105_tree = (Object)adaptor.create(RIGHTP105);
					adaptor.addChild(root_0, RIGHTP105_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1410:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
	public final EugeneParser.deviceComponents_return deviceComponents(boolean defer) throws RecognitionException {
		EugeneParser.deviceComponents_return retval = new EugeneParser.deviceComponents_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal106=null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope dcs =null;

		Object char_literal106_tree=null;


		retval.lstComponents = new ArrayList<List<NamedElement>>();
		retval.lstOrientations = new ArrayList<List<Orientation>>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1416:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1416:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_selection_in_deviceComponents2225);
			s=selection(defer);
			state._fsp--;

			adaptor.addChild(root_0, s.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.lstComponents.add((s!=null?((EugeneParser.selection_return)s).components:null));
			    retval.lstOrientations.add((s!=null?((EugeneParser.selection_return)s).orientations:null));
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1421:4: ( ',' dcs= deviceComponents[defer] )?
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==COMMA) ) {
				alt38=1;
			}
			switch (alt38) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1421:5: ',' dcs= deviceComponents[defer]
					{
					char_literal106=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents2231); 
					char_literal106_tree = (Object)adaptor.create(char_literal106);
					adaptor.addChild(root_0, char_literal106_tree);

					pushFollow(FOLLOW_deviceComponents_in_deviceComponents2235);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1432:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
	public final EugeneParser.selection_return selection(boolean defer) throws RecognitionException {
		EugeneParser.selection_return retval = new EugeneParser.selection_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LEFTSBR107=null;
		Token RIGHTSBR108=null;
		ParserRuleReturnScope sl =null;
		ParserRuleReturnScope dc =null;

		Object LEFTSBR107_tree=null;
		Object RIGHTSBR108_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1434:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1434:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					LEFTSBR107=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection2264); 
					LEFTSBR107_tree = (Object)adaptor.create(LEFTSBR107);
					adaptor.addChild(root_0, LEFTSBR107_tree);

					pushFollow(FOLLOW_selection_list_in_selection2268);
					sl=selection_list(defer);
					state._fsp--;

					adaptor.addChild(root_0, sl.getTree());

					RIGHTSBR108=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection2271); 
					RIGHTSBR108_tree = (Object)adaptor.create(RIGHTSBR108);
					adaptor.addChild(root_0, RIGHTSBR108_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.components = (sl!=null?((EugeneParser.selection_list_return)sl).components:null);
					    retval.orientations = (sl!=null?((EugeneParser.selection_list_return)sl).orientations:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1440:4: dc= device_component[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_device_component_in_selection2280);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1451:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
	public final EugeneParser.selection_list_return selection_list(boolean defer) throws RecognitionException {
		EugeneParser.selection_list_return retval = new EugeneParser.selection_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PIPE109=null;
		ParserRuleReturnScope dc =null;
		ParserRuleReturnScope sl =null;

		Object PIPE109_tree=null;


		retval.components = new ArrayList<NamedElement>();
		retval.orientations = new ArrayList<Orientation>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1457:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1457:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_device_component_in_selection_list2309);
			dc=device_component(defer);
			state._fsp--;

			adaptor.addChild(root_0, dc.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.components.add((dc!=null?((EugeneParser.device_component_return)dc).component:null));
			    retval.orientations.add((dc!=null?((EugeneParser.device_component_return)dc).orientation:null));
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1462:4: ( PIPE sl= selection_list[defer] )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==PIPE) ) {
				alt40=1;
			}
			switch (alt40) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1462:5: PIPE sl= selection_list[defer]
					{
					PIPE109=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list2315); 
					PIPE109_tree = (Object)adaptor.create(PIPE109);
					adaptor.addChild(root_0, PIPE109_tree);

					pushFollow(FOLLOW_selection_list_in_selection_list2319);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
	public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
		EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token directionToken=null;
		Token idToken=null;

		Object directionToken_tree=null;
		Object idToken_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:4: (directionToken= ( MINUS | PLUS ) )?
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( (LA41_0==MINUS||LA41_0==PLUS) ) {
				alt41=1;
			}
			switch (alt41) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:5: directionToken= ( MINUS | PLUS )
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

			idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component2355); 
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
			            printError((idToken!=null?idToken.getText():null)+" is neither a Device, Part nor a Part Type.");
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1520:1: assignment[boolean defer] : lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
	public final EugeneParser.assignment_return assignment(boolean defer) throws RecognitionException {
		EugeneParser.assignment_return retval = new EugeneParser.assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token a=null;
		Token EQUALS110=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope rhs =null;

		Object a_tree=null;
		Object EQUALS110_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1521:2: (lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1521:4: lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_lhs_assignment_in_assignment2375);
			lhs=lhs_assignment(defer);
			state._fsp--;

			adaptor.addChild(root_0, lhs.getTree());

			EQUALS110=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment2378); 
			EQUALS110_tree = (Object)adaptor.create(EQUALS110);
			adaptor.addChild(root_0, EQUALS110_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1521:37: (a= AMP )?
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0==AMP) ) {
				alt42=1;
			}
			switch (alt42) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1521:38: a= AMP
					{
					a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment2383); 
					a_tree = (Object)adaptor.create(a);
					adaptor.addChild(root_0, a_tree);

					}
					break;

			}

			pushFollow(FOLLOW_rhs_assignment_in_assignment2389);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1550:1: lhs_assignment[boolean defer] : ID lhs_access[defer] ;
	public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer) throws RecognitionException {
		EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID111=null;
		ParserRuleReturnScope lhs_access112 =null;

		Object ID111_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1551:2: ( ID lhs_access[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1551:4: ID lhs_access[defer]
			{
			root_0 = (Object)adaptor.nil();


			ID111=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment2404); 
			ID111_tree = (Object)adaptor.create(ID111);
			adaptor.addChild(root_0, ID111_tree);

			pushFollow(FOLLOW_lhs_access_in_lhs_assignment2406);
			lhs_access112=lhs_access(defer);
			state._fsp--;

			adaptor.addChild(root_0, lhs_access112.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1554:1: lhs_access[boolean defer] : (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] );
	public final EugeneParser.lhs_access_return lhs_access(boolean defer) throws RecognitionException {
		EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token DOT113=null;
		Token LEFTSBR114=null;
		Token set115=null;
		Token RIGHTSBR116=null;
		ParserRuleReturnScope lhs_access117 =null;

		Object i_tree=null;
		Object DOT113_tree=null;
		Object LEFTSBR114_tree=null;
		Object set115_tree=null;
		Object RIGHTSBR116_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1555:2: (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1556:2: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1556:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer]
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1556:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR )
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1556:5: DOT i= ID
							{
							DOT113=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2426); 
							DOT113_tree = (Object)adaptor.create(DOT113);
							adaptor.addChild(root_0, DOT113_tree);

							i=(Token)match(input,ID,FOLLOW_ID_in_lhs_access2430); 
							i_tree = (Object)adaptor.create(i);
							adaptor.addChild(root_0, i_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1556:16: LEFTSBR ( ID | NUMBER ) RIGHTSBR
							{
							LEFTSBR114=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2434); 
							LEFTSBR114_tree = (Object)adaptor.create(LEFTSBR114);
							adaptor.addChild(root_0, LEFTSBR114_tree);

							set115=input.LT(1);
							if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
								input.consume();
								adaptor.addChild(root_0, (Object)adaptor.create(set115));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							RIGHTSBR116=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2442); 
							RIGHTSBR116_tree = (Object)adaptor.create(RIGHTSBR116);
							adaptor.addChild(root_0, RIGHTSBR116_tree);

							}
							break;

					}

					pushFollow(FOLLOW_lhs_access_in_lhs_access2445);
					lhs_access117=lhs_access(defer);
					state._fsp--;

					adaptor.addChild(root_0, lhs_access117.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1567:1: rhs_assignment[boolean defer] returns [NamedElement e] : (de= dataExchange[defer] |exp= expr[defer] );
	public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
		EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope de =null;
		ParserRuleReturnScope exp =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1569:2: (de= dataExchange[defer] |exp= expr[defer] )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==GENBANK||(LA45_0 >= IMPORT_LC && LA45_0 <= IMPORT_UC)||LA45_0==REGISTRY||LA45_0==SBOL) ) {
				alt45=1;
			}
			else if ( (LA45_0==DOLLAR||(LA45_0 >= FALSE_LC && LA45_0 <= FALSE_UC)||LA45_0==ID||(LA45_0 >= LC_PERMUTE && LA45_0 <= LC_PRODUCT)||LA45_0==LC_SEQUENCE_OF||(LA45_0 >= LEFTP && LA45_0 <= LEFTSBR)||LA45_0==MINUS||LA45_0==NUMBER||(LA45_0 >= QUERY_LC && LA45_0 <= RANDOM_UC)||LA45_0==REAL||(LA45_0 >= SIZEOF_LC && LA45_0 <= SIZE_UC)||(LA45_0 >= STRING && LA45_0 <= TRUE_UC)||(LA45_0 >= UC_PERMUTE && LA45_0 <= UC_PRODUCT)||LA45_0==UC_SEQUENCE_OF) ) {
				alt45=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1569:4: de= dataExchange[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_dataExchange_in_rhs_assignment2472);
					de=dataExchange(defer);
					state._fsp--;

					adaptor.addChild(root_0, de.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (de!=null?((EugeneParser.dataExchange_return)de).e:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1574:4: exp= expr[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expr_in_rhs_assignment2482);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1585:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
	public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
		EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token idToken=null;
		Token char_literal118=null;
		ParserRuleReturnScope lstToken =null;

		Object idToken_tree=null;
		Object char_literal118_tree=null;


		retval.lstElements =new ArrayList<NamedElement>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1590:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1590:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2510); 
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

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1602:4: ( ',' lstToken= listOfIDs[defer] )?
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0==COMMA) ) {
				alt46=1;
			}
			switch (alt46) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1602:5: ',' lstToken= listOfIDs[defer]
					{
					char_literal118=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2519); 
					char_literal118_tree = (Object)adaptor.create(char_literal118);
					adaptor.addChild(root_0, char_literal118_tree);

					pushFollow(FOLLOW_listOfIDs_in_listOfIDs2523);
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


	public static class rulebuilderDeclaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "rulebuilderDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1612:1: rulebuilderDeclaration[boolean defer] : RULE_BUILDER i= ID ( LEFTP ( ( LC_ON | UC_ON ) d= ID )? RIGHTP )? ;
	public final EugeneParser.rulebuilderDeclaration_return rulebuilderDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.rulebuilderDeclaration_return retval = new EugeneParser.rulebuilderDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token d=null;
		Token RULE_BUILDER119=null;
		Token LEFTP120=null;
		Token set121=null;
		Token RIGHTP122=null;

		Object i_tree=null;
		Object d_tree=null;
		Object RULE_BUILDER119_tree=null;
		Object LEFTP120_tree=null;
		Object set121_tree=null;
		Object RIGHTP122_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1613:2: ( RULE_BUILDER i= ID ( LEFTP ( ( LC_ON | UC_ON ) d= ID )? RIGHTP )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1613:4: RULE_BUILDER i= ID ( LEFTP ( ( LC_ON | UC_ON ) d= ID )? RIGHTP )?
			{
			root_0 = (Object)adaptor.nil();


			RULE_BUILDER119=(Token)match(input,RULE_BUILDER,FOLLOW_RULE_BUILDER_in_rulebuilderDeclaration2548); 
			RULE_BUILDER119_tree = (Object)adaptor.create(RULE_BUILDER119);
			adaptor.addChild(root_0, RULE_BUILDER119_tree);

			i=(Token)match(input,ID,FOLLOW_ID_in_rulebuilderDeclaration2552); 
			i_tree = (Object)adaptor.create(i);
			adaptor.addChild(root_0, i_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1613:22: ( LEFTP ( ( LC_ON | UC_ON ) d= ID )? RIGHTP )?
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0==LEFTP) ) {
				alt48=1;
			}
			switch (alt48) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1613:23: LEFTP ( ( LC_ON | UC_ON ) d= ID )? RIGHTP
					{
					LEFTP120=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_rulebuilderDeclaration2555); 
					LEFTP120_tree = (Object)adaptor.create(LEFTP120);
					adaptor.addChild(root_0, LEFTP120_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1613:29: ( ( LC_ON | UC_ON ) d= ID )?
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==LC_ON||LA47_0==UC_ON) ) {
						alt47=1;
					}
					switch (alt47) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1613:30: ( LC_ON | UC_ON ) d= ID
							{
							set121=input.LT(1);
							if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
								input.consume();
								adaptor.addChild(root_0, (Object)adaptor.create(set121));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							d=(Token)match(input,ID,FOLLOW_ID_in_rulebuilderDeclaration2566); 
							d_tree = (Object)adaptor.create(d);
							adaptor.addChild(root_0, d_tree);

							}
							break;

					}

					RIGHTP122=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_rulebuilderDeclaration2570); 
					RIGHTP122_tree = (Object)adaptor.create(RIGHTP122);
					adaptor.addChild(root_0, RIGHTP122_tree);

					}
					break;

			}


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING){
			    try {
			        // first, we ask the Interpreter to instantate a RuleBuilder
			        this.interp.instantiateRuleBuilder((i!=null?i.getText():null), (d!=null?d.getText():null));
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
	// $ANTLR end "rulebuilderDeclaration"


	public static class ruleDeclaration_return extends ParserRuleReturnScope {
		public Rule rule;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ruleDeclaration"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1628:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
	public final EugeneParser.ruleDeclaration_return ruleDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.ruleDeclaration_return retval = new EugeneParser.ruleDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token device=null;
		Token RULE123=null;
		Token LEFTP124=null;
		Token set125=null;
		Token COLON126=null;
		Token RIGHTP127=null;
		ParserRuleReturnScope cnf =null;

		Object name_tree=null;
		Object device_tree=null;
		Object RULE123_tree=null;
		Object LEFTP124_tree=null;
		Object set125_tree=null;
		Object COLON126_tree=null;
		Object RIGHTP127_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1630:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1630:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			RULE123=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2597); 
			RULE123_tree = (Object)adaptor.create(RULE123);
			adaptor.addChild(root_0, RULE123_tree);

			name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2601); 
			name_tree = (Object)adaptor.create(name);
			adaptor.addChild(root_0, name_tree);

			LEFTP124=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2603); 
			LEFTP124_tree = (Object)adaptor.create(LEFTP124);
			adaptor.addChild(root_0, LEFTP124_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1630:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1630:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
			{
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1630:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
			int alt49=2;
			int LA49_0 = input.LA(1);
			if ( (LA49_0==LC_ON||LA49_0==UC_ON) ) {
				alt49=1;
			}
			switch (alt49) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1630:26: ( LC_ON | UC_ON ) device= ID COLON
					{
					set125=input.LT(1);
					if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set125));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2616); 
					device_tree = (Object)adaptor.create(device);
					adaptor.addChild(root_0, device_tree);

					COLON126=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2618); 
					COLON126_tree = (Object)adaptor.create(COLON126);
					adaptor.addChild(root_0, COLON126_tree);

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
				
			pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2626);
			cnf=cnf_rule(defer);
			state._fsp--;

			adaptor.addChild(root_0, cnf.getTree());

			}

			RIGHTP127=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2631); 
			RIGHTP127_tree = (Object)adaptor.create(RIGHTP127);
			adaptor.addChild(root_0, RIGHTP127_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1654:1: ruleOperator[boolean defer] : ruleOperators ;
	public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
		EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope ruleOperators128 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1655:2: ( ruleOperators )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1655:4: ruleOperators
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_ruleOperators_in_ruleOperator2645);
			ruleOperators128=ruleOperators();
			state._fsp--;

			adaptor.addChild(root_0, ruleOperators128.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1659:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
	public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
		EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set129=null;

		Object set129_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1660:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
			{
			root_0 = (Object)adaptor.nil();


			set129=input.LT(1);
			if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 140 && input.LA(1) <= 174)||(input.LA(1) >= 176 && input.LA(1) <= 213)||(input.LA(1) >= 215 && input.LA(1) <= 217) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set129));
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1703:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
	public final EugeneParser.relationalOperators_return relationalOperators() throws RecognitionException {
		EugeneParser.relationalOperators_return retval = new EugeneParser.relationalOperators_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EQUALS130=null;
		Token EQUALS131=null;
		Token NEQUAL132=null;
		Token LTHAN133=null;
		Token GTHAN134=null;
		Token LEQUAL135=null;
		Token GEQUAL136=null;
		Token set137=null;
		Token set138=null;
		Token set139=null;
		Token set140=null;
		Token set141=null;
		Token set142=null;
		Token set143=null;
		Token set144=null;
		Token set145=null;

		Object EQUALS130_tree=null;
		Object EQUALS131_tree=null;
		Object NEQUAL132_tree=null;
		Object LTHAN133_tree=null;
		Object GTHAN134_tree=null;
		Object LEQUAL135_tree=null;
		Object GEQUAL136_tree=null;
		Object set137_tree=null;
		Object set138_tree=null;
		Object set139_tree=null;
		Object set140_tree=null;
		Object set141_tree=null;
		Object set142_tree=null;
		Object set143_tree=null;
		Object set144_tree=null;
		Object set145_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1704:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
			int alt50=15;
			switch ( input.LA(1) ) {
			case EQUALS:
				{
				alt50=1;
				}
				break;
			case NEQUAL:
				{
				alt50=2;
				}
				break;
			case LTHAN:
				{
				alt50=3;
				}
				break;
			case GTHAN:
				{
				alt50=4;
				}
				break;
			case LEQUAL:
				{
				alt50=5;
				}
				break;
			case GEQUAL:
				{
				alt50=6;
				}
				break;
			case 150:
			case 189:
				{
				alt50=7;
				}
				break;
			case 159:
			case 198:
				{
				alt50=8;
				}
				break;
			case 156:
			case 195:
				{
				alt50=9;
				}
				break;
			case 162:
			case 201:
				{
				alt50=10;
				}
				break;
			case 176:
			case 215:
				{
				alt50=11;
				}
				break;
			case 152:
			case 191:
				{
				alt50=12;
				}
				break;
			case 153:
			case 192:
				{
				alt50=13;
				}
				break;
			case 160:
			case 199:
				{
				alt50=14;
				}
				break;
			case 175:
			case 214:
				{
				alt50=15;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 50, 0, input);
				throw nvae;
			}
			switch (alt50) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1704:4: EQUALS EQUALS
					{
					root_0 = (Object)adaptor.nil();


					EQUALS130=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators3024); 
					EQUALS130_tree = (Object)adaptor.create(EQUALS130);
					adaptor.addChild(root_0, EQUALS130_tree);

					EQUALS131=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators3026); 
					EQUALS131_tree = (Object)adaptor.create(EQUALS131);
					adaptor.addChild(root_0, EQUALS131_tree);

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1705:4: NEQUAL
					{
					root_0 = (Object)adaptor.nil();


					NEQUAL132=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators3031); 
					NEQUAL132_tree = (Object)adaptor.create(NEQUAL132);
					adaptor.addChild(root_0, NEQUAL132_tree);

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1706:4: LTHAN
					{
					root_0 = (Object)adaptor.nil();


					LTHAN133=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators3036); 
					LTHAN133_tree = (Object)adaptor.create(LTHAN133);
					adaptor.addChild(root_0, LTHAN133_tree);

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1707:4: GTHAN
					{
					root_0 = (Object)adaptor.nil();


					GTHAN134=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators3041); 
					GTHAN134_tree = (Object)adaptor.create(GTHAN134);
					adaptor.addChild(root_0, GTHAN134_tree);

					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1708:4: LEQUAL
					{
					root_0 = (Object)adaptor.nil();


					LEQUAL135=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators3046); 
					LEQUAL135_tree = (Object)adaptor.create(LEQUAL135);
					adaptor.addChild(root_0, LEQUAL135_tree);

					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1709:4: GEQUAL
					{
					root_0 = (Object)adaptor.nil();


					GEQUAL136=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators3051); 
					GEQUAL136_tree = (Object)adaptor.create(GEQUAL136);
					adaptor.addChild(root_0, GEQUAL136_tree);

					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1710:4: ( 'CONTAINS' | 'contains' )
					{
					root_0 = (Object)adaptor.nil();


					set137=input.LT(1);
					if ( input.LA(1)==150||input.LA(1)==189 ) {
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
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1711:4: ( 'NOTCONTAINS' | 'notcontains' )
					{
					root_0 = (Object)adaptor.nil();


					set138=input.LT(1);
					if ( input.LA(1)==159||input.LA(1)==198 ) {
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
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1712:4: ( 'MATCHES' | 'matches' )
					{
					root_0 = (Object)adaptor.nil();


					set139=input.LT(1);
					if ( input.LA(1)==156||input.LA(1)==195 ) {
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
				case 10 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1713:4: ( 'NOTMATCHES' | 'notmatches' )
					{
					root_0 = (Object)adaptor.nil();


					set140=input.LT(1);
					if ( input.LA(1)==162||input.LA(1)==201 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set140));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 11 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1714:4: ( 'STARTSWITH' | 'startswith' )
					{
					root_0 = (Object)adaptor.nil();


					set141=input.LT(1);
					if ( input.LA(1)==176||input.LA(1)==215 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set141));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 12 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1715:4: ( 'ENDSWITH' | 'endswith' )
					{
					root_0 = (Object)adaptor.nil();


					set142=input.LT(1);
					if ( input.LA(1)==152||input.LA(1)==191 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set142));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 13 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1716:4: ( 'EQUALS' | 'equals' )
					{
					root_0 = (Object)adaptor.nil();


					set143=input.LT(1);
					if ( input.LA(1)==153||input.LA(1)==192 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set143));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 14 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1717:4: ( 'NOTEQUALS' | 'notequals' )
					{
					root_0 = (Object)adaptor.nil();


					set144=input.LT(1);
					if ( input.LA(1)==160||input.LA(1)==199 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set144));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 15 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1718:4: ( 'SOUNDSLIKE' | 'soundslike' )
					{
					root_0 = (Object)adaptor.nil();


					set145=input.LT(1);
					if ( input.LA(1)==175||input.LA(1)==214 ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set145));
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1721:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
	public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
		EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set146=null;
		ParserRuleReturnScope c =null;
		ParserRuleReturnScope cnf =null;

		Object set146_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1723:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1723:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1723:4: (c= or_predicate[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1723:5: c= or_predicate[defer]
			{
			pushFollow(FOLLOW_or_predicate_in_cnf_rule3152);
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

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1731:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==LC_AND||LA51_0==LOG_AND||LA51_0==UC_AND) ) {
				alt51=1;
			}
			switch (alt51) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1731:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
					{
					set146=input.LT(1);
					if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set146));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_cnf_rule_in_cnf_rule3170);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1738:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
	public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
		EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set147=null;
		ParserRuleReturnScope n1 =null;
		ParserRuleReturnScope n2 =null;

		Object set147_tree=null;


		LogicalOr lor = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1743:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1743:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_negated_predicate_in_or_predicate3200);
			n1=negated_predicate(defer);
			state._fsp--;

			adaptor.addChild(root_0, n1.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.p = (n1!=null?((EugeneParser.negated_predicate_return)n1).p:null);
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1747:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
			loop52:
			while (true) {
				int alt52=2;
				int LA52_0 = input.LA(1);
				if ( (LA52_0==LC_OR||LA52_0==LOG_OR||LA52_0==UC_OR) ) {
					alt52=1;
				}

				switch (alt52) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1747:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
					{
					set147=input.LT(1);
					if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set147));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_negated_predicate_in_or_predicate3216);
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
					break loop52;
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1768:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
	public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
		EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set148=null;
		ParserRuleReturnScope c =null;

		Object set148_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1770:2: ( ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1770:4: ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] )
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1770:4: ( ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer] |c= predicate[defer] )
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==LC_NOT||LA53_0==OP_NOT||LA53_0==UC_NOT) ) {
				alt53=1;
			}
			else if ( (LA53_0==DOLLAR||LA53_0==ID||LA53_0==LC_INDUCES||LA53_0==LC_REPRESSES||(LA53_0 >= LEFTP && LA53_0 <= LEFTSBR)||LA53_0==MINUS||LA53_0==NUMBER||LA53_0==REAL||LA53_0==STRING||LA53_0==UC_INDUCES||LA53_0==UC_REPRESSES||(LA53_0 >= 140 && LA53_0 <= 174)||(LA53_0 >= 176 && LA53_0 <= 213)||(LA53_0 >= 215 && LA53_0 <= 217)) ) {
				alt53=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 53, 0, input);
				throw nvae;
			}

			switch (alt53) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1770:5: ( UC_NOT | LC_NOT | OP_NOT ) c= predicate[defer]
					{
					set148=input.LT(1);
					if ( input.LA(1)==LC_NOT||input.LA(1)==OP_NOT||input.LA(1)==UC_NOT ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set148));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_predicate_in_negated_predicate3254);
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1779:4: c= predicate[defer]
					{
					pushFollow(FOLLOW_predicate_in_negated_predicate3264);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1786:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
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
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1788:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
			int alt56=3;
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
				case 175:
				case 214:
					{
					alt56=3;
					}
					break;
				case 150:
				case 189:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 5, input);
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
					alt56=2;
					}
					break;
				case 159:
				case 198:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 7, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 156:
				case 195:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 162:
				case 201:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 176:
				case 215:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 10, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 152:
				case 191:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 11, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 153:
				case 192:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 12, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 160:
				case 199:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 13, input);
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
				case 151:
				case 154:
				case 155:
				case 157:
				case 158:
				case 161:
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
				case 190:
				case 193:
				case 194:
				case 196:
				case 197:
				case 200:
				case 202:
				case 203:
				case 204:
				case 205:
				case 206:
				case 207:
				case 208:
				case 209:
				case 210:
				case 211:
				case 212:
				case 213:
				case 216:
				case 217:
					{
					alt56=1;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 56, 1, input);
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
				case 150:
				case 189:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 5, input);
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
				case 175:
				case 214:
					{
					alt56=3;
					}
					break;
				case 159:
				case 198:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 7, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 156:
				case 195:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 8, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 162:
				case 201:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 176:
				case 215:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 10, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 152:
				case 191:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 11, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 153:
				case 192:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 12, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case 160:
				case 199:
					{
					switch ( input.LA(3) ) {
					case ID:
						{
						alt56=1;
						}
						break;
					case DOLLAR:
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
						alt56=1;
						}
						break;
					case LEFTP:
					case MINUS:
					case REAL:
					case STRING:
						{
						alt56=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 56, 13, input);
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
				case 151:
				case 154:
				case 155:
				case 157:
				case 158:
				case 161:
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
				case 190:
				case 193:
				case 194:
				case 196:
				case 197:
				case 200:
				case 202:
				case 203:
				case 204:
				case 205:
				case 206:
				case 207:
				case 208:
				case 209:
				case 210:
				case 211:
				case 212:
				case 213:
				case 216:
				case 217:
					{
					alt56=1;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 56, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case DOLLAR:
			case LC_INDUCES:
			case LC_REPRESSES:
			case LEFTSBR:
			case UC_INDUCES:
			case UC_REPRESSES:
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
			case 158:
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
			case 197:
			case 198:
			case 199:
			case 200:
			case 201:
			case 202:
			case 203:
			case 204:
			case 205:
			case 206:
			case 207:
			case 208:
			case 209:
			case 210:
			case 211:
			case 212:
			case 213:
			case 215:
			case 216:
			case 217:
				{
				alt56=1;
				}
				break;
			case LEFTP:
			case MINUS:
			case REAL:
			case STRING:
				{
				alt56=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 56, 0, input);
				throw nvae;
			}
			switch (alt56) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1788:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1788:4: (lhs= operand[defer] )?
					int alt54=2;
					int LA54_0 = input.LA(1);
					if ( (LA54_0==DOLLAR||LA54_0==ID||LA54_0==LEFTSBR||LA54_0==NUMBER) ) {
						alt54=1;
					}
					switch (alt54) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1788:5: lhs= operand[defer]
							{
							pushFollow(FOLLOW_operand_in_predicate3291);
							lhs=operand(defer);
							state._fsp--;

							adaptor.addChild(root_0, lhs.getTree());


							addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
								
							}
							break;

					}

					pushFollow(FOLLOW_ruleOperator_in_predicate3301);
					op=ruleOperator(defer);
					state._fsp--;

					adaptor.addChild(root_0, op.getTree());


					addToken((op!=null?input.toString(op.start,op.stop):null));	
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1792:5: (rhs= operand[defer] )?
					int alt55=2;
					int LA55_0 = input.LA(1);
					if ( (LA55_0==DOLLAR||LA55_0==ID||LA55_0==LEFTSBR||LA55_0==NUMBER) ) {
						alt55=1;
					}
					switch (alt55) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1792:6: rhs= operand[defer]
							{
							pushFollow(FOLLOW_operand_in_predicate3310);
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1806:4: i= ID
					{
					root_0 = (Object)adaptor.nil();


					i=(Token)match(input,ID,FOLLOW_ID_in_predicate3324); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1823:4: exp= expressionRule[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expressionRule_in_predicate3333);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' |dn= dynamic_naming[defer] ) ;
	public final EugeneParser.operand_return operand(boolean defer) throws RecognitionException {
		EugeneParser.operand_return retval = new EugeneParser.operand_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token n=null;
		Token char_literal149=null;
		Token char_literal150=null;
		ParserRuleReturnScope dn =null;

		Object i_tree=null;
		Object n_tree=null;
		Object char_literal149_tree=null;
		Object char_literal150_tree=null;


		NamedElement element = null;
		int constant = -1;
		int index = -1;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1837:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' |dn= dynamic_naming[defer] ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1837:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' |dn= dynamic_naming[defer] )
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1837:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' |dn= dynamic_naming[defer] )
			int alt57=4;
			switch ( input.LA(1) ) {
			case ID:
				{
				alt57=1;
				}
				break;
			case NUMBER:
				{
				alt57=2;
				}
				break;
			case LEFTSBR:
				{
				alt57=3;
				}
				break;
			case DOLLAR:
				{
				alt57=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 57, 0, input);
				throw nvae;
			}
			switch (alt57) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1837:5: i= ID
					{
					i=(Token)match(input,ID,FOLLOW_ID_in_operand3364); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(!this.interp.contains((i!=null?i.getText():null))) {
					            printError((i!=null?i.getText():null)+" not defined.");
					        }
					        element = this.interp.get((i!=null?i.getText():null));
					    } catch(EugeneException ee) {
					        printError(ee.getMessage());
					    }
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1849:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3373); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    constant = Integer.parseInt((n!=null?n.getText():null));
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1854:4: '[' n= NUMBER ']'
					{
					char_literal149=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand3380); 
					char_literal149_tree = (Object)adaptor.create(char_literal149);
					adaptor.addChild(root_0, char_literal149_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3384); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);

					char_literal150=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand3386); 
					char_literal150_tree = (Object)adaptor.create(char_literal150);
					adaptor.addChild(root_0, char_literal150_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    index = Integer.parseInt((n!=null?n.getText():null));
					}	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1858:5: dn= dynamic_naming[defer]
					{
					pushFollow(FOLLOW_dynamic_naming_in_operand3393);
					dn=dynamic_naming(defer);
					state._fsp--;

					adaptor.addChild(root_0, dn.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(!this.interp.contains((dn!=null?((EugeneParser.dynamic_naming_return)dn).name:null))) {
					            printError((dn!=null?((EugeneParser.dynamic_naming_return)dn).name:null)+" not defined.");
					        }
					        element = this.interp.get((dn!=null?((EugeneParser.dynamic_naming_return)dn).name:null));
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1884:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
	public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
		EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope op =null;
		ParserRuleReturnScope rhs =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1886:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1886:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expression_in_expressionRule3420);
			lhs=expression(defer);
			state._fsp--;

			adaptor.addChild(root_0, lhs.getTree());

			pushFollow(FOLLOW_exp_op_in_expressionRule3425);
			op=exp_op(defer);
			state._fsp--;

			adaptor.addChild(root_0, op.getTree());

			pushFollow(FOLLOW_expression_in_expressionRule3430);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1897:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
	public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
		EugeneParser.expression_return retval = new EugeneParser.expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LEFTP151=null;
		Token RIGHTP153=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope expop =null;
		ParserRuleReturnScope rhs =null;
		ParserRuleReturnScope expression152 =null;

		Object LEFTP151_tree=null;
		Object RIGHTP153_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1899:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
			int alt59=2;
			int LA59_0 = input.LA(1);
			if ( (LA59_0==ID||LA59_0==MINUS||LA59_0==NUMBER||LA59_0==REAL||LA59_0==STRING) ) {
				alt59=1;
			}
			else if ( (LA59_0==LEFTP) ) {
				alt59=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 59, 0, input);
				throw nvae;
			}

			switch (alt59) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1899:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_exp_operand_in_expression3454);
					lhs=exp_operand(defer);
					state._fsp--;

					adaptor.addChild(root_0, lhs.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.exp = new Expression((lhs!=null?((EugeneParser.exp_operand_return)lhs).eop:null), null, null);
					}
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1903:4: (expop= exp_operator[defer] rhs= expression[defer] )?
					int alt58=2;
					int LA58_0 = input.LA(1);
					if ( (LA58_0==DIV||LA58_0==MINUS||LA58_0==MULT||LA58_0==PLUS) ) {
						alt58=1;
					}
					switch (alt58) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1903:6: expop= exp_operator[defer] rhs= expression[defer]
							{
							pushFollow(FOLLOW_exp_operator_in_expression3463);
							expop=exp_operator(defer);
							state._fsp--;

							adaptor.addChild(root_0, expop.getTree());

							pushFollow(FOLLOW_expression_in_expression3468);
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1908:4: LEFTP expression[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					LEFTP151=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3480); 
					LEFTP151_tree = (Object)adaptor.create(LEFTP151);
					adaptor.addChild(root_0, LEFTP151_tree);

					pushFollow(FOLLOW_expression_in_expression3482);
					expression152=expression(defer);
					state._fsp--;

					adaptor.addChild(root_0, expression152.getTree());

					RIGHTP153=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3485); 
					RIGHTP153_tree = (Object)adaptor.create(RIGHTP153);
					adaptor.addChild(root_0, RIGHTP153_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1915:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
	public final EugeneParser.exp_operator_return exp_operator(boolean defer) throws RecognitionException {
		EugeneParser.exp_operator_return retval = new EugeneParser.exp_operator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PLUS154=null;
		Token MINUS155=null;
		Token MULT156=null;
		Token DIV157=null;

		Object PLUS154_tree=null;
		Object MINUS155_tree=null;
		Object MULT156_tree=null;
		Object DIV157_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1917:2: ( PLUS | MINUS | MULT | DIV )
			int alt60=4;
			switch ( input.LA(1) ) {
			case PLUS:
				{
				alt60=1;
				}
				break;
			case MINUS:
				{
				alt60=2;
				}
				break;
			case MULT:
				{
				alt60=3;
				}
				break;
			case DIV:
				{
				alt60=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 60, 0, input);
				throw nvae;
			}
			switch (alt60) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1917:4: PLUS
					{
					root_0 = (Object)adaptor.nil();


					PLUS154=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3504); 
					PLUS154_tree = (Object)adaptor.create(PLUS154);
					adaptor.addChild(root_0, PLUS154_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
					    retval.op = Expression.ExpOp.PLUS;	
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1922:4: MINUS
					{
					root_0 = (Object)adaptor.nil();


					MINUS155=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3512); 
					MINUS155_tree = (Object)adaptor.create(MINUS155);
					adaptor.addChild(root_0, MINUS155_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
					    retval.op = Expression.ExpOp.MINUS;	
					}
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1927:4: MULT
					{
					root_0 = (Object)adaptor.nil();


					MULT156=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3519); 
					MULT156_tree = (Object)adaptor.create(MULT156);
					adaptor.addChild(root_0, MULT156_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
					    retval.op = Expression.ExpOp.MULT;	
					}
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1932:4: DIV
					{
					root_0 = (Object)adaptor.nil();


					DIV157=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3526); 
					DIV157_tree = (Object)adaptor.create(DIV157);
					adaptor.addChild(root_0, DIV157_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1939:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* (i2= ID ) ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
	public final EugeneParser.exp_operand_return exp_operand(boolean defer) throws RecognitionException {
		EugeneParser.exp_operand_return retval = new EugeneParser.exp_operand_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i1=null;
		Token i2=null;
		Token n=null;
		Token r=null;
		Token s=null;
		Token DOT158=null;
		Token LEFTSBR159=null;
		Token RIGHTSBR160=null;
		Token MINUS161=null;
		Token MINUS162=null;

		Object i1_tree=null;
		Object i2_tree=null;
		Object n_tree=null;
		Object r_tree=null;
		Object s_tree=null;
		Object DOT158_tree=null;
		Object LEFTSBR159_tree=null;
		Object RIGHTSBR160_tree=null;
		Object MINUS161_tree=null;
		Object MINUS162_tree=null;


		List<NamedElement> elements = null;
		NamedElement ne = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1945:2: ( (i1= ID DOT )* (i2= ID ) ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
			int alt63=6;
			switch ( input.LA(1) ) {
			case ID:
				{
				alt63=1;
				}
				break;
			case NUMBER:
				{
				alt63=2;
				}
				break;
			case MINUS:
				{
				int LA63_3 = input.LA(2);
				if ( (LA63_3==NUMBER) ) {
					alt63=3;
				}
				else if ( (LA63_3==REAL) ) {
					alt63=5;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 63, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case REAL:
				{
				alt63=4;
				}
				break;
			case STRING:
				{
				alt63=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 63, 0, input);
				throw nvae;
			}
			switch (alt63) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1945:4: (i1= ID DOT )* (i2= ID ) ( LEFTSBR n= NUMBER RIGHTSBR )*
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1945:4: (i1= ID DOT )*
					loop61:
					while (true) {
						int alt61=2;
						int LA61_0 = input.LA(1);
						if ( (LA61_0==ID) ) {
							int LA61_1 = input.LA(2);
							if ( (LA61_1==DOT) ) {
								alt61=1;
							}

						}

						switch (alt61) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1945:5: i1= ID DOT
							{
							i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3556); 
							i1_tree = (Object)adaptor.create(i1);
							adaptor.addChild(root_0, i1_tree);

							DOT158=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3558); 
							DOT158_tree = (Object)adaptor.create(DOT158);
							adaptor.addChild(root_0, DOT158_tree);


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
							break loop61;
						}
					}

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1978:7: (i2= ID )
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1978:8: i2= ID
					{
					i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3568); 
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
						
					}

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2013:6: ( LEFTSBR n= NUMBER RIGHTSBR )*
					loop62:
					while (true) {
						int alt62=2;
						int LA62_0 = input.LA(1);
						if ( (LA62_0==LEFTSBR) ) {
							alt62=1;
						}

						switch (alt62) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2013:7: LEFTSBR n= NUMBER RIGHTSBR
							{
							LEFTSBR159=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3575); 
							LEFTSBR159_tree = (Object)adaptor.create(LEFTSBR159);
							adaptor.addChild(root_0, LEFTSBR159_tree);

							n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3579); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							RIGHTSBR160=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3581); 
							RIGHTSBR160_tree = (Object)adaptor.create(RIGHTSBR160);
							adaptor.addChild(root_0, RIGHTSBR160_tree);


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
							break loop62;
						}
					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2024:4: n= NUMBER
					{
					root_0 = (Object)adaptor.nil();


					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3593); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2031:4: MINUS n= NUMBER
					{
					root_0 = (Object)adaptor.nil();


					MINUS161=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3600); 
					MINUS161_tree = (Object)adaptor.create(MINUS161);
					adaptor.addChild(root_0, MINUS161_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3604); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2038:4: r= REAL
					{
					root_0 = (Object)adaptor.nil();


					r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3613); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2045:4: MINUS r= REAL
					{
					root_0 = (Object)adaptor.nil();


					MINUS162=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3620); 
					MINUS162_tree = (Object)adaptor.create(MINUS162);
					adaptor.addChild(root_0, MINUS162_tree);

					r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3624); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2052:4: s= STRING
					{
					root_0 = (Object)adaptor.nil();


					s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3633); 
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2062:1: regexp[boolean defer] :;
	public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
		EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2063:2: ()
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2064:2: 
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2066:1: exp_op[boolean defer] : relationalOperators ;
	public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
		EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope relationalOperators163 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2067:2: ( relationalOperators )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2067:4: relationalOperators
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_relationalOperators_in_exp_op3660);
			relationalOperators163=relationalOperators();
			state._fsp--;

			adaptor.addChild(root_0, relationalOperators163.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2075:1: grammarDeclaration[boolean defer] : GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP ;
	public final EugeneParser.grammarDeclaration_return grammarDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.grammarDeclaration_return retval = new EugeneParser.grammarDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token GRAMMAR164=null;
		Token LEFTP165=null;
		Token RIGHTP167=null;
		ParserRuleReturnScope list_of_production_rules166 =null;

		Object n_tree=null;
		Object GRAMMAR164_tree=null;
		Object LEFTP165_tree=null;
		Object RIGHTP167_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2076:2: ( GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2077:3: GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			GRAMMAR164=(Token)match(input,GRAMMAR,FOLLOW_GRAMMAR_in_grammarDeclaration3679); 
			GRAMMAR164_tree = (Object)adaptor.create(GRAMMAR164);
			adaptor.addChild(root_0, GRAMMAR164_tree);

			n=(Token)match(input,ID,FOLLOW_ID_in_grammarDeclaration3683); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			LEFTP165=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_grammarDeclaration3685); 
			LEFTP165_tree = (Object)adaptor.create(LEFTP165);
			adaptor.addChild(root_0, LEFTP165_tree);

			pushFollow(FOLLOW_list_of_production_rules_in_grammarDeclaration3687);
			list_of_production_rules166=list_of_production_rules(defer);
			state._fsp--;

			adaptor.addChild(root_0, list_of_production_rules166.getTree());

			RIGHTP167=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_grammarDeclaration3690); 
			RIGHTP167_tree = (Object)adaptor.create(RIGHTP167);
			adaptor.addChild(root_0, RIGHTP167_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2080:1: list_of_production_rules[boolean defer] : production_rule[defer] SEMIC ( list_of_production_rules[defer] )? ;
	public final EugeneParser.list_of_production_rules_return list_of_production_rules(boolean defer) throws RecognitionException {
		EugeneParser.list_of_production_rules_return retval = new EugeneParser.list_of_production_rules_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMIC169=null;
		ParserRuleReturnScope production_rule168 =null;
		ParserRuleReturnScope list_of_production_rules170 =null;

		Object SEMIC169_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2081:2: ( production_rule[defer] SEMIC ( list_of_production_rules[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2081:4: production_rule[defer] SEMIC ( list_of_production_rules[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_production_rule_in_list_of_production_rules3702);
			production_rule168=production_rule(defer);
			state._fsp--;

			adaptor.addChild(root_0, production_rule168.getTree());

			SEMIC169=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_list_of_production_rules3705); 
			SEMIC169_tree = (Object)adaptor.create(SEMIC169);
			adaptor.addChild(root_0, SEMIC169_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2081:33: ( list_of_production_rules[defer] )?
			int alt64=2;
			int LA64_0 = input.LA(1);
			if ( (LA64_0==ID) ) {
				alt64=1;
			}
			switch (alt64) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2081:34: list_of_production_rules[defer]
					{
					pushFollow(FOLLOW_list_of_production_rules_in_list_of_production_rules3708);
					list_of_production_rules170=list_of_production_rules(defer);
					state._fsp--;

					adaptor.addChild(root_0, list_of_production_rules170.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2084:1: production_rule[boolean defer] : lhs= ID ARROW right_hand_side[defer] ;
	public final EugeneParser.production_rule_return production_rule(boolean defer) throws RecognitionException {
		EugeneParser.production_rule_return retval = new EugeneParser.production_rule_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token lhs=null;
		Token ARROW171=null;
		ParserRuleReturnScope right_hand_side172 =null;

		Object lhs_tree=null;
		Object ARROW171_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2085:2: (lhs= ID ARROW right_hand_side[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2085:4: lhs= ID ARROW right_hand_side[defer]
			{
			root_0 = (Object)adaptor.nil();


			lhs=(Token)match(input,ID,FOLLOW_ID_in_production_rule3728); 
			lhs_tree = (Object)adaptor.create(lhs);
			adaptor.addChild(root_0, lhs_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    // ID denotes a non-terminal of the grammar
			}	
				
			ARROW171=(Token)match(input,ARROW,FOLLOW_ARROW_in_production_rule3732); 
			ARROW171_tree = (Object)adaptor.create(ARROW171);
			adaptor.addChild(root_0, ARROW171_tree);

			pushFollow(FOLLOW_right_hand_side_in_production_rule3734);
			right_hand_side172=right_hand_side(defer);
			state._fsp--;

			adaptor.addChild(root_0, right_hand_side172.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2092:1: right_hand_side[boolean defer] : (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] );
	public final EugeneParser.right_hand_side_return right_hand_side(boolean defer) throws RecognitionException {
		EugeneParser.right_hand_side_return retval = new EugeneParser.right_hand_side_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token COMMA173=null;
		ParserRuleReturnScope right_hand_side174 =null;
		ParserRuleReturnScope interaction175 =null;

		Object i_tree=null;
		Object COMMA173_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2093:2: (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] )
			int alt66=2;
			int LA66_0 = input.LA(1);
			if ( (LA66_0==ID) ) {
				int LA66_1 = input.LA(2);
				if ( (LA66_1==COMMA||LA66_1==SEMIC) ) {
					alt66=1;
				}
				else if ( (LA66_1==LC_INDUCES||LA66_1==LC_REPRESSES||LA66_1==UC_INDUCES||LA66_1==UC_REPRESSES) ) {
					alt66=2;
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2093:4: i= ID ( COMMA right_hand_side[defer] )?
					{
					root_0 = (Object)adaptor.nil();


					i=(Token)match(input,ID,FOLLOW_ID_in_right_hand_side3750); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    // ID must be either a terminal (i.e. a part)
					    // or a non-terminal defined within the grammar
					}	
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2098:4: ( COMMA right_hand_side[defer] )?
					int alt65=2;
					int LA65_0 = input.LA(1);
					if ( (LA65_0==COMMA) ) {
						alt65=1;
					}
					switch (alt65) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2098:5: COMMA right_hand_side[defer]
							{
							COMMA173=(Token)match(input,COMMA,FOLLOW_COMMA_in_right_hand_side3755); 
							COMMA173_tree = (Object)adaptor.create(COMMA173);
							adaptor.addChild(root_0, COMMA173_tree);

							pushFollow(FOLLOW_right_hand_side_in_right_hand_side3757);
							right_hand_side174=right_hand_side(defer);
							state._fsp--;

							adaptor.addChild(root_0, right_hand_side174.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2099:4: interaction[defer, \"some_string\"]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_interaction_in_right_hand_side3765);
					interaction175=interaction(defer, "some_string");
					state._fsp--;

					adaptor.addChild(root_0, interaction175.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2106:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
	public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
		EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token INTERACTION176=null;
		Token LEFTP177=null;
		Token RIGHTP178=null;
		ParserRuleReturnScope i1 =null;
		ParserRuleReturnScope i2 =null;

		Object name_tree=null;
		Object INTERACTION176_tree=null;
		Object LEFTP177_tree=null;
		Object RIGHTP178_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2108:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
			int alt67=2;
			int LA67_0 = input.LA(1);
			if ( (LA67_0==ID) ) {
				alt67=1;
			}
			else if ( (LA67_0==INTERACTION) ) {
				alt67=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 67, 0, input);
				throw nvae;
			}

			switch (alt67) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2108:4: i1= interaction[defer, null]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_interaction_in_interactionDeclaration3790);
					i1=interaction(defer, null);
					state._fsp--;

					adaptor.addChild(root_0, i1.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.ia = (i1!=null?((EugeneParser.interaction_return)i1).ia:null);
					}

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2113:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					INTERACTION176=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3798); 
					INTERACTION176_tree = (Object)adaptor.create(INTERACTION176);
					adaptor.addChild(root_0, INTERACTION176_tree);

					name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3802); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					LEFTP177=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3804); 
					LEFTP177_tree = (Object)adaptor.create(LEFTP177);
					adaptor.addChild(root_0, LEFTP177_tree);

					pushFollow(FOLLOW_interaction_in_interactionDeclaration3808);
					i2=interaction(defer, (name!=null?name.getText():null));
					state._fsp--;

					adaptor.addChild(root_0, i2.getTree());

					RIGHTP178=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3811); 
					RIGHTP178_tree = (Object)adaptor.create(RIGHTP178);
					adaptor.addChild(root_0, RIGHTP178_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2120:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
	public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
		EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token lhs1=null;
		Token rhs1=null;
		Token lhs2=null;
		Token LEFTP179=null;
		Token RIGHTP180=null;
		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope t2 =null;
		ParserRuleReturnScope rhs2 =null;

		Object lhs1_tree=null;
		Object rhs1_tree=null;
		Object lhs2_tree=null;
		Object LEFTP179_tree=null;
		Object RIGHTP180_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2122:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
			int alt68=2;
			int LA68_0 = input.LA(1);
			if ( (LA68_0==ID) ) {
				int LA68_1 = input.LA(2);
				if ( (LA68_1==LC_REPRESSES||LA68_1==UC_REPRESSES) ) {
					int LA68_2 = input.LA(3);
					if ( (LA68_2==ID) ) {
						alt68=1;
					}
					else if ( (LA68_2==LEFTP) ) {
						alt68=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 68, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA68_1==LC_INDUCES||LA68_1==UC_INDUCES) ) {
					int LA68_3 = input.LA(3);
					if ( (LA68_3==ID) ) {
						alt68=1;
					}
					else if ( (LA68_3==LEFTP) ) {
						alt68=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 68, 3, input);
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
							new NoViableAltException("", 68, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 68, 0, input);
				throw nvae;
			}

			switch (alt68) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2122:4: lhs1= ID t1= interactionType[defer] rhs1= ID
					{
					root_0 = (Object)adaptor.nil();


					lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3834); 
					lhs1_tree = (Object)adaptor.create(lhs1);
					adaptor.addChild(root_0, lhs1_tree);

					pushFollow(FOLLOW_interactionType_in_interaction3838);
					t1=interactionType(defer);
					state._fsp--;

					adaptor.addChild(root_0, t1.getTree());

					rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3843); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2131:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3852); 
					lhs2_tree = (Object)adaptor.create(lhs2);
					adaptor.addChild(root_0, lhs2_tree);

					pushFollow(FOLLOW_interactionType_in_interaction3856);
					t2=interactionType(defer);
					state._fsp--;

					adaptor.addChild(root_0, t2.getTree());

					LEFTP179=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3859); 
					LEFTP179_tree = (Object)adaptor.create(LEFTP179);
					adaptor.addChild(root_0, LEFTP179_tree);

					pushFollow(FOLLOW_interaction_in_interaction3863);
					rhs2=interaction(defer, name);
					state._fsp--;

					adaptor.addChild(root_0, rhs2.getTree());

					RIGHTP180=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3866); 
					RIGHTP180_tree = (Object)adaptor.create(RIGHTP180);
					adaptor.addChild(root_0, RIGHTP180_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2142:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
	public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
		EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set181=null;
		Token set182=null;

		Object set181_tree=null;
		Object set182_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2144:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
			int alt69=2;
			int LA69_0 = input.LA(1);
			if ( (LA69_0==LC_REPRESSES||LA69_0==UC_REPRESSES) ) {
				alt69=1;
			}
			else if ( (LA69_0==LC_INDUCES||LA69_0==UC_INDUCES) ) {
				alt69=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 69, 0, input);
				throw nvae;
			}

			switch (alt69) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2144:4: ( UC_REPRESSES | LC_REPRESSES )
					{
					root_0 = (Object)adaptor.nil();


					set181=input.LT(1);
					if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set181));
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2149:4: ( UC_INDUCES | LC_INDUCES )
					{
					root_0 = (Object)adaptor.nil();


					set182=input.LT(1);
					if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set182));
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2161:1: printStatement[boolean defer] : ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP );
	public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
		EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set183=null;
		Token LEFTP184=null;
		Token RIGHTP185=null;
		Token set186=null;
		Token LEFTP187=null;
		Token RIGHTP188=null;
		ParserRuleReturnScope tp =null;

		Object set183_tree=null;
		Object LEFTP184_tree=null;
		Object RIGHTP185_tree=null;
		Object set186_tree=null;
		Object LEFTP187_tree=null;
		Object RIGHTP188_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2162:2: ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP )
			int alt70=2;
			int LA70_0 = input.LA(1);
			if ( ((LA70_0 >= PRINTLN_LC && LA70_0 <= PRINTLN_UC)) ) {
				alt70=1;
			}
			else if ( ((LA70_0 >= PRINT_LC && LA70_0 <= PRINT_UC)) ) {
				alt70=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 70, 0, input);
				throw nvae;
			}

			switch (alt70) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2162:4: ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set183=input.LT(1);
					if ( (input.LA(1) >= PRINTLN_LC && input.LA(1) <= PRINTLN_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set183));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP184=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3931); 
					LEFTP184_tree = (Object)adaptor.create(LEFTP184);
					adaptor.addChild(root_0, LEFTP184_tree);

					pushFollow(FOLLOW_toPrint_in_printStatement3935);
					tp=toPrint(defer);
					state._fsp--;

					adaptor.addChild(root_0, tp.getTree());

					RIGHTP185=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3938); 
					RIGHTP185_tree = (Object)adaptor.create(RIGHTP185);
					adaptor.addChild(root_0, RIGHTP185_tree);


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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2175:4: ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set186=input.LT(1);
					if ( (input.LA(1) >= PRINT_LC && input.LA(1) <= PRINT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set186));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP187=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3951); 
					LEFTP187_tree = (Object)adaptor.create(LEFTP187);
					adaptor.addChild(root_0, LEFTP187_tree);

					pushFollow(FOLLOW_toPrint_in_printStatement3955);
					tp=toPrint(defer);
					state._fsp--;

					adaptor.addChild(root_0, tp.getTree());

					RIGHTP188=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3958); 
					RIGHTP188_tree = (Object)adaptor.create(RIGHTP188);
					adaptor.addChild(root_0, RIGHTP188_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2189:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
	public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
		EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope exp =null;
		ParserRuleReturnScope tpp =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2191:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2191:4: exp= expr[defer] tpp= toPrint_prime[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_toPrint3979);
			exp=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, exp.getTree());

			pushFollow(FOLLOW_toPrint_prime_in_toPrint3984);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2204:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
	public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
		EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA189=null;
		ParserRuleReturnScope tp =null;

		Object COMMA189_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2206:2: (| COMMA tp= toPrint[defer] )
			int alt71=2;
			int LA71_0 = input.LA(1);
			if ( (LA71_0==RIGHTP) ) {
				alt71=1;
			}
			else if ( (LA71_0==COMMA) ) {
				alt71=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 71, 0, input);
				throw nvae;
			}

			switch (alt71) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2206:4: 
					{
					root_0 = (Object)adaptor.nil();



					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.sb = new StringBuilder();
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2211:4: COMMA tp= toPrint[defer]
					{
					root_0 = (Object)adaptor.nil();


					COMMA189=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime4010); 
					COMMA189_tree = (Object)adaptor.create(COMMA189);
					adaptor.addChild(root_0, COMMA189_tree);

					pushFollow(FOLLOW_toPrint_in_toPrint_prime4014);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2223:1: imperativeStatements[boolean defer] : ( if_elseif_else[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] );
	public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope if_elseif_else190 =null;
		ParserRuleReturnScope forall_iterator191 =null;
		ParserRuleReturnScope for_loop192 =null;
		ParserRuleReturnScope while_loop193 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2225:2: ( if_elseif_else[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] )
			int alt72=4;
			switch ( input.LA(1) ) {
			case LC_IF:
			case UC_IF:
				{
				alt72=1;
				}
				break;
			case LC_FORALL:
			case UC_FORALL:
				{
				alt72=2;
				}
				break;
			case LC_FOR:
			case UC_FOR:
				{
				alt72=3;
				}
				break;
			case LC_WHILE:
			case UC_WHILE:
				{
				alt72=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 72, 0, input);
				throw nvae;
			}
			switch (alt72) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2225:4: if_elseif_else[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_if_elseif_else_in_imperativeStatements4039);
					if_elseif_else190=if_elseif_else(defer);
					state._fsp--;

					adaptor.addChild(root_0, if_elseif_else190.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2226:4: forall_iterator[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_forall_iterator_in_imperativeStatements4045);
					forall_iterator191=forall_iterator(defer);
					state._fsp--;

					adaptor.addChild(root_0, forall_iterator191.getTree());

					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2227:4: for_loop[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_for_loop_in_imperativeStatements4051);
					for_loop192=for_loop(defer);
					state._fsp--;

					adaptor.addChild(root_0, for_loop192.getTree());

					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2228:4: while_loop[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_while_loop_in_imperativeStatements4057);
					while_loop193=while_loop(defer);
					state._fsp--;

					adaptor.addChild(root_0, while_loop193.getTree());

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


	public static class if_elseif_else_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "if_elseif_else"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2231:1: if_elseif_else[boolean defer] : ( UC_IF | LC_IF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )? ;
	public final EugeneParser.if_elseif_else_return if_elseif_else(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.if_elseif_else_return retval = new EugeneParser.if_elseif_else_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set194=null;
		Token LEFTP195=null;
		Token RIGHTP196=null;
		Token LEFTCUR197=null;
		Token RIGHTCUR198=null;
		Token set199=null;
		Token LEFTP200=null;
		Token RIGHTP201=null;
		Token LEFTCUR202=null;
		Token RIGHTCUR203=null;
		Token set204=null;
		Token LEFTCUR205=null;
		Token RIGHTCUR206=null;
		ParserRuleReturnScope co =null;
		ParserRuleReturnScope stmts =null;

		Object set194_tree=null;
		Object LEFTP195_tree=null;
		Object RIGHTP196_tree=null;
		Object LEFTCUR197_tree=null;
		Object RIGHTCUR198_tree=null;
		Object set199_tree=null;
		Object LEFTP200_tree=null;
		Object RIGHTP201_tree=null;
		Object LEFTCUR202_tree=null;
		Object RIGHTCUR203_tree=null;
		Object set204_tree=null;
		Object LEFTCUR205_tree=null;
		Object RIGHTCUR206_tree=null;


		boolean bExecuted = false;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2237:2: ( ( UC_IF | LC_IF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2241:3: ( UC_IF | LC_IF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )?
			{
			root_0 = (Object)adaptor.nil();


			set194=input.LT(1);
			if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set194));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP195=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_if_elseif_else4095); 
			LEFTP195_tree = (Object)adaptor.create(LEFTP195);
			adaptor.addChild(root_0, LEFTP195_tree);

			pushFollow(FOLLOW_logical_condition_in_if_elseif_else4099);
			co=logical_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, co.getTree());

			RIGHTP196=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_if_elseif_else4102); 
			RIGHTP196_tree = (Object)adaptor.create(RIGHTP196);
			adaptor.addChild(root_0, RIGHTP196_tree);

			LEFTCUR197=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_if_elseif_else4104); 
			LEFTCUR197_tree = (Object)adaptor.create(LEFTCUR197);
			adaptor.addChild(root_0, LEFTCUR197_tree);

			pushFollow(FOLLOW_list_of_statements_in_if_elseif_else4112);
			stmts=list_of_statements(true);
			state._fsp--;

			adaptor.addChild(root_0, stmts.getTree());

			RIGHTCUR198=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_if_elseif_else4115); 
			RIGHTCUR198_tree = (Object)adaptor.create(RIGHTCUR198);
			adaptor.addChild(root_0, RIGHTCUR198_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    // evaluate the condition
			    if((co!=null?((EugeneParser.logical_condition_return)co).b:false)) {
			        // if true => execute the statements
			        // and ignore the rest of the ifStatement
			        
			        try {
			            this.exec_branch((stmts!=null?(stmts.start):null).getTokenIndex());
			        } catch(EugeneReturnException ere) {
			            throw new EugeneReturnException(ere.getReturnValue());
			        } catch(EugeneException ee) {
			            printError(ee.getLocalizedMessage());
			        }
			        bExecuted = true;
			    }
			}			
					
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2264:3: ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )*
			loop73:
			while (true) {
				int alt73=2;
				int LA73_0 = input.LA(1);
				if ( (LA73_0==LC_ELSEIF||LA73_0==UC_ELSEIF) ) {
					alt73=1;
				}

				switch (alt73) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2264:5: ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= logical_condition[defer] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
					{
					set199=input.LT(1);
					if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set199));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP200=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_if_elseif_else4136); 
					LEFTP200_tree = (Object)adaptor.create(LEFTP200);
					adaptor.addChild(root_0, LEFTP200_tree);

					pushFollow(FOLLOW_logical_condition_in_if_elseif_else4140);
					co=logical_condition(defer);
					state._fsp--;

					adaptor.addChild(root_0, co.getTree());

					RIGHTP201=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_if_elseif_else4143); 
					RIGHTP201_tree = (Object)adaptor.create(RIGHTP201);
					adaptor.addChild(root_0, RIGHTP201_tree);

					LEFTCUR202=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_if_elseif_else4145); 
					LEFTCUR202_tree = (Object)adaptor.create(LEFTCUR202);
					adaptor.addChild(root_0, LEFTCUR202_tree);

					pushFollow(FOLLOW_list_of_statements_in_if_elseif_else4153);
					stmts=list_of_statements(true);
					state._fsp--;

					adaptor.addChild(root_0, stmts.getTree());

					RIGHTCUR203=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_if_elseif_else4156); 
					RIGHTCUR203_tree = (Object)adaptor.create(RIGHTCUR203);
					adaptor.addChild(root_0, RIGHTCUR203_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
					    // evaluate the condition
					    if((co!=null?((EugeneParser.logical_condition_return)co).b:false)) {
					        // if true => execute the statements
					        // and ignore the rest of the ifStatement
					        try {        
					            this.exec_branch((stmts!=null?(stmts.start):null).getTokenIndex());
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
					break loop73;
				}
			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2287:3: ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR )?
			int alt74=2;
			int LA74_0 = input.LA(1);
			if ( (LA74_0==LC_ELSE||LA74_0==UC_ELSE) ) {
				alt74=1;
			}
			switch (alt74) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2287:4: ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= list_of_statements[true] RIGHTCUR
					{
					set204=input.LT(1);
					if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set204));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTCUR205=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_if_elseif_else4178); 
					LEFTCUR205_tree = (Object)adaptor.create(LEFTCUR205);
					adaptor.addChild(root_0, LEFTCUR205_tree);

					pushFollow(FOLLOW_list_of_statements_in_if_elseif_else4186);
					stmts=list_of_statements(true);
					state._fsp--;

					adaptor.addChild(root_0, stmts.getTree());

					RIGHTCUR206=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_if_elseif_else4189); 
					RIGHTCUR206_tree = (Object)adaptor.create(RIGHTCUR206);
					adaptor.addChild(root_0, RIGHTCUR206_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
					    try {
					        this.exec_branch((stmts!=null?(stmts.start):null).getTokenIndex());        
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
	// $ANTLR end "if_elseif_else"


	public static class forall_iterator_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "forall_iterator"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2304:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR los= list_of_statements[defer] RIGHTCUR ;
	public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token it=null;
		Token i=null;
		Token set207=null;
		Token COLON208=null;
		Token LEFTCUR209=null;
		Token RIGHTCUR210=null;
		ParserRuleReturnScope los =null;

		Object it_tree=null;
		Object i_tree=null;
		Object set207_tree=null;
		Object COLON208_tree=null;
		Object LEFTCUR209_tree=null;
		Object RIGHTCUR210_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2306:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR los= list_of_statements[defer] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2306:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR los= list_of_statements[defer] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			set207=input.LT(1);
			if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set207));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2306:26: (it= ID COLON )?
			int alt75=2;
			int LA75_0 = input.LA(1);
			if ( (LA75_0==ID) ) {
				int LA75_1 = input.LA(2);
				if ( (LA75_1==COLON) ) {
					alt75=1;
				}
			}
			switch (alt75) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2306:27: it= ID COLON
					{
					it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator4220); 
					it_tree = (Object)adaptor.create(it);
					adaptor.addChild(root_0, it_tree);

					COLON208=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator4222); 
					COLON208_tree = (Object)adaptor.create(COLON208);
					adaptor.addChild(root_0, COLON208_tree);

					}
					break;

			}

			i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator4228); 
			i_tree = (Object)adaptor.create(i);
			adaptor.addChild(root_0, i_tree);

			LEFTCUR209=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator4230); 
			LEFTCUR209_tree = (Object)adaptor.create(LEFTCUR209);
			adaptor.addChild(root_0, LEFTCUR209_tree);

			pushFollow(FOLLOW_list_of_statements_in_forall_iterator4237);
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
				
			RIGHTCUR210=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator4244); 
			RIGHTCUR210_tree = (Object)adaptor.create(RIGHTCUR210);
			adaptor.addChild(root_0, RIGHTCUR210_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2321:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= logical_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ;
	public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set211=null;
		Token LEFTP212=null;
		Token SEMIC213=null;
		Token SEMIC214=null;
		Token RIGHTP215=null;
		Token LEFTCUR216=null;
		Token RIGHTCUR217=null;
		ParserRuleReturnScope ds =null;
		ParserRuleReturnScope co =null;
		ParserRuleReturnScope as =null;
		ParserRuleReturnScope stmts =null;

		Object set211_tree=null;
		Object LEFTP212_tree=null;
		Object SEMIC213_tree=null;
		Object SEMIC214_tree=null;
		Object RIGHTP215_tree=null;
		Object LEFTCUR216_tree=null;
		Object RIGHTCUR217_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2323:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= logical_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2323:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= logical_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			set211=input.LT(1);
			if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set211));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP212=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop4267); 
			LEFTP212_tree = (Object)adaptor.create(LEFTP212);
			adaptor.addChild(root_0, LEFTP212_tree);

			pushFollow(FOLLOW_variableDeclaration_in_for_loop4271);
			ds=variableDeclaration(true);
			state._fsp--;

			adaptor.addChild(root_0, ds.getTree());

			SEMIC213=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4274); 
			SEMIC213_tree = (Object)adaptor.create(SEMIC213);
			adaptor.addChild(root_0, SEMIC213_tree);

			pushFollow(FOLLOW_logical_condition_in_for_loop4278);
			co=logical_condition(true);
			state._fsp--;

			adaptor.addChild(root_0, co.getTree());

			SEMIC214=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4281); 
			SEMIC214_tree = (Object)adaptor.create(SEMIC214);
			adaptor.addChild(root_0, SEMIC214_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2323:94: (as= assignment[true] )?
			int alt76=2;
			int LA76_0 = input.LA(1);
			if ( (LA76_0==ID) ) {
				alt76=1;
			}
			switch (alt76) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2323:95: as= assignment[true]
					{
					pushFollow(FOLLOW_assignment_in_for_loop4286);
					as=assignment(true);
					state._fsp--;

					adaptor.addChild(root_0, as.getTree());

					}
					break;

			}

			RIGHTP215=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop4291); 
			RIGHTP215_tree = (Object)adaptor.create(RIGHTP215);
			adaptor.addChild(root_0, RIGHTP215_tree);

			LEFTCUR216=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop4293); 
			LEFTCUR216_tree = (Object)adaptor.create(LEFTCUR216);
			adaptor.addChild(root_0, LEFTCUR216_tree);

			pushFollow(FOLLOW_list_of_statements_in_for_loop4301);
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
					
			RIGHTCUR217=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop4308); 
			RIGHTCUR217_tree = (Object)adaptor.create(RIGHTCUR217);
			adaptor.addChild(root_0, RIGHTCUR217_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2350:1: while_loop[boolean defer] : ( UC_WHILE | LC_WHILE ) LEFTP co= logical_condition[true] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ;
	public final EugeneParser.while_loop_return while_loop(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.while_loop_return retval = new EugeneParser.while_loop_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set218=null;
		Token LEFTP219=null;
		Token RIGHTP220=null;
		Token LEFTCUR221=null;
		Token RIGHTCUR222=null;
		ParserRuleReturnScope co =null;
		ParserRuleReturnScope stmts =null;

		Object set218_tree=null;
		Object LEFTP219_tree=null;
		Object RIGHTP220_tree=null;
		Object LEFTCUR221_tree=null;
		Object RIGHTCUR222_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2352:2: ( ( UC_WHILE | LC_WHILE ) LEFTP co= logical_condition[true] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2352:4: ( UC_WHILE | LC_WHILE ) LEFTP co= logical_condition[true] RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			set218=input.LT(1);
			if ( input.LA(1)==LC_WHILE||input.LA(1)==UC_WHILE ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set218));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP219=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_while_loop4333); 
			LEFTP219_tree = (Object)adaptor.create(LEFTP219);
			adaptor.addChild(root_0, LEFTP219_tree);

			pushFollow(FOLLOW_logical_condition_in_while_loop4337);
			co=logical_condition(true);
			state._fsp--;

			adaptor.addChild(root_0, co.getTree());

			RIGHTP220=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_while_loop4340); 
			RIGHTP220_tree = (Object)adaptor.create(RIGHTP220);
			adaptor.addChild(root_0, RIGHTP220_tree);

			LEFTCUR221=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_while_loop4342); 
			LEFTCUR221_tree = (Object)adaptor.create(LEFTCUR221);
			adaptor.addChild(root_0, LEFTCUR221_tree);

			pushFollow(FOLLOW_list_of_statements_in_while_loop4350);
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
				
			RIGHTCUR222=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_while_loop4357); 
			RIGHTCUR222_tree = (Object)adaptor.create(RIGHTCUR222);
			adaptor.addChild(root_0, RIGHTCUR222_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2376:1: logical_condition[boolean defer] returns [boolean b] : loc= logical_or_condition[defer] ;
	public final EugeneParser.logical_condition_return logical_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_condition_return retval = new EugeneParser.logical_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope loc =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2378:2: (loc= logical_or_condition[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2378:4: loc= logical_or_condition[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_logical_or_condition_in_logical_condition4383);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2385:1: logical_not_condition[boolean defer] returns [boolean b] : loc= logical_or_condition[defer] ;
	public final EugeneParser.logical_not_condition_return logical_not_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_not_condition_return retval = new EugeneParser.logical_not_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope loc =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2387:2: (loc= logical_or_condition[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2387:4: loc= logical_or_condition[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_logical_or_condition_in_logical_not_condition4408);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2394:1: logical_or_condition[boolean defer] returns [boolean b] : lac= logical_and_condition[defer] ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )* ;
	public final EugeneParser.logical_or_condition_return logical_or_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_or_condition_return retval = new EugeneParser.logical_or_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LC_OR223=null;
		Token UC_OR224=null;
		Token LOG_OR225=null;
		Token PIPE226=null;
		Token PIPE227=null;
		ParserRuleReturnScope lac =null;
		ParserRuleReturnScope loc =null;

		Object LC_OR223_tree=null;
		Object UC_OR224_tree=null;
		Object LOG_OR225_tree=null;
		Object PIPE226_tree=null;
		Object PIPE227_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2396:2: (lac= logical_and_condition[defer] ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2396:4: lac= logical_and_condition[defer] ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_logical_and_condition_in_logical_or_condition4433);
			lac=logical_and_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, lac.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.b = (lac!=null?((EugeneParser.logical_and_condition_return)lac).b:false);
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:4: ( ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer] )*
			loop79:
			while (true) {
				int alt79=2;
				switch ( input.LA(1) ) {
				case LC_OR:
					{
					alt79=1;
					}
					break;
				case UC_OR:
					{
					alt79=1;
					}
					break;
				case LOG_OR:
					{
					alt79=1;
					}
					break;
				case PIPE:
					{
					alt79=1;
					}
					break;
				}
				switch (alt79) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:5: ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? ) loc= logical_or_condition[defer]
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:5: ( LC_OR | UC_OR | LOG_OR | PIPE ( PIPE )? )
					int alt78=4;
					switch ( input.LA(1) ) {
					case LC_OR:
						{
						alt78=1;
						}
						break;
					case UC_OR:
						{
						alt78=2;
						}
						break;
					case LOG_OR:
						{
						alt78=3;
						}
						break;
					case PIPE:
						{
						alt78=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 78, 0, input);
						throw nvae;
					}
					switch (alt78) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:6: LC_OR
							{
							LC_OR223=(Token)match(input,LC_OR,FOLLOW_LC_OR_in_logical_or_condition4440); 
							LC_OR223_tree = (Object)adaptor.create(LC_OR223);
							adaptor.addChild(root_0, LC_OR223_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:12: UC_OR
							{
							UC_OR224=(Token)match(input,UC_OR,FOLLOW_UC_OR_in_logical_or_condition4442); 
							UC_OR224_tree = (Object)adaptor.create(UC_OR224);
							adaptor.addChild(root_0, UC_OR224_tree);

							}
							break;
						case 3 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:18: LOG_OR
							{
							LOG_OR225=(Token)match(input,LOG_OR,FOLLOW_LOG_OR_in_logical_or_condition4444); 
							LOG_OR225_tree = (Object)adaptor.create(LOG_OR225);
							adaptor.addChild(root_0, LOG_OR225_tree);

							}
							break;
						case 4 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:25: PIPE ( PIPE )?
							{
							PIPE226=(Token)match(input,PIPE,FOLLOW_PIPE_in_logical_or_condition4446); 
							PIPE226_tree = (Object)adaptor.create(PIPE226);
							adaptor.addChild(root_0, PIPE226_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:30: ( PIPE )?
							int alt77=2;
							int LA77_0 = input.LA(1);
							if ( (LA77_0==PIPE) ) {
								alt77=1;
							}
							switch (alt77) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:31: PIPE
									{
									PIPE227=(Token)match(input,PIPE,FOLLOW_PIPE_in_logical_or_condition4449); 
									PIPE227_tree = (Object)adaptor.create(PIPE227);
									adaptor.addChild(root_0, PIPE227_tree);

									}
									break;

							}

							}
							break;

					}

					pushFollow(FOLLOW_logical_or_condition_in_logical_or_condition4456);
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
					break loop79;
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2408:1: logical_and_condition[boolean defer] returns [boolean b] : ac= atomic_condition[defer] ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )* ;
	public final EugeneParser.logical_and_condition_return logical_and_condition(boolean defer) throws RecognitionException {
		EugeneParser.logical_and_condition_return retval = new EugeneParser.logical_and_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LC_AND228=null;
		Token UC_AND229=null;
		Token LOG_AND230=null;
		Token AMP231=null;
		Token AMP232=null;
		ParserRuleReturnScope ac =null;
		ParserRuleReturnScope lac =null;

		Object LC_AND228_tree=null;
		Object UC_AND229_tree=null;
		Object LOG_AND230_tree=null;
		Object AMP231_tree=null;
		Object AMP232_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2410:2: (ac= atomic_condition[defer] ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2410:4: ac= atomic_condition[defer] ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_atomic_condition_in_logical_and_condition4481);
			ac=atomic_condition(defer);
			state._fsp--;

			adaptor.addChild(root_0, ac.getTree());


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    retval.b = (ac!=null?((EugeneParser.atomic_condition_return)ac).b:false);
			}	
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:4: ( ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer] )*
			loop82:
			while (true) {
				int alt82=2;
				switch ( input.LA(1) ) {
				case LC_AND:
					{
					alt82=1;
					}
					break;
				case UC_AND:
					{
					alt82=1;
					}
					break;
				case LOG_AND:
					{
					alt82=1;
					}
					break;
				case AMP:
					{
					alt82=1;
					}
					break;
				}
				switch (alt82) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:5: ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? ) lac= logical_and_condition[defer]
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:5: ( LC_AND | UC_AND | LOG_AND | AMP ( AMP )? )
					int alt81=4;
					switch ( input.LA(1) ) {
					case LC_AND:
						{
						alt81=1;
						}
						break;
					case UC_AND:
						{
						alt81=2;
						}
						break;
					case LOG_AND:
						{
						alt81=3;
						}
						break;
					case AMP:
						{
						alt81=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 81, 0, input);
						throw nvae;
					}
					switch (alt81) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:6: LC_AND
							{
							LC_AND228=(Token)match(input,LC_AND,FOLLOW_LC_AND_in_logical_and_condition4488); 
							LC_AND228_tree = (Object)adaptor.create(LC_AND228);
							adaptor.addChild(root_0, LC_AND228_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:13: UC_AND
							{
							UC_AND229=(Token)match(input,UC_AND,FOLLOW_UC_AND_in_logical_and_condition4490); 
							UC_AND229_tree = (Object)adaptor.create(UC_AND229);
							adaptor.addChild(root_0, UC_AND229_tree);

							}
							break;
						case 3 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:20: LOG_AND
							{
							LOG_AND230=(Token)match(input,LOG_AND,FOLLOW_LOG_AND_in_logical_and_condition4492); 
							LOG_AND230_tree = (Object)adaptor.create(LOG_AND230);
							adaptor.addChild(root_0, LOG_AND230_tree);

							}
							break;
						case 4 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:28: AMP ( AMP )?
							{
							AMP231=(Token)match(input,AMP,FOLLOW_AMP_in_logical_and_condition4494); 
							AMP231_tree = (Object)adaptor.create(AMP231);
							adaptor.addChild(root_0, AMP231_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:32: ( AMP )?
							int alt80=2;
							int LA80_0 = input.LA(1);
							if ( (LA80_0==AMP) ) {
								alt80=1;
							}
							switch (alt80) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2414:33: AMP
									{
									AMP232=(Token)match(input,AMP,FOLLOW_AMP_in_logical_and_condition4497); 
									AMP232_tree = (Object)adaptor.create(AMP232);
									adaptor.addChild(root_0, AMP232_tree);

									}
									break;

							}

							}
							break;

					}

					pushFollow(FOLLOW_logical_and_condition_in_logical_and_condition4504);
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
	// $ANTLR end "logical_and_condition"


	public static class atomic_condition_return extends ParserRuleReturnScope {
		public boolean b;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atomic_condition"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2422:1: atomic_condition[boolean defer] returns [boolean b] : (lhs= expr[defer] ro= relationalOperators rhs= expr[defer] | ( LC_NOT | UC_NOT | OP_NOT ) LEFTP lac= atomic_condition[defer] RIGHTP );
	public final EugeneParser.atomic_condition_return atomic_condition(boolean defer) throws RecognitionException {
		EugeneParser.atomic_condition_return retval = new EugeneParser.atomic_condition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set233=null;
		Token LEFTP234=null;
		Token RIGHTP235=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope ro =null;
		ParserRuleReturnScope rhs =null;
		ParserRuleReturnScope lac =null;

		Object set233_tree=null;
		Object LEFTP234_tree=null;
		Object RIGHTP235_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2424:2: (lhs= expr[defer] ro= relationalOperators rhs= expr[defer] | ( LC_NOT | UC_NOT | OP_NOT ) LEFTP lac= atomic_condition[defer] RIGHTP )
			int alt83=2;
			int LA83_0 = input.LA(1);
			if ( (LA83_0==DOLLAR||(LA83_0 >= FALSE_LC && LA83_0 <= FALSE_UC)||LA83_0==ID||(LA83_0 >= LC_PERMUTE && LA83_0 <= LC_PRODUCT)||LA83_0==LC_SEQUENCE_OF||(LA83_0 >= LEFTP && LA83_0 <= LEFTSBR)||LA83_0==MINUS||LA83_0==NUMBER||(LA83_0 >= QUERY_LC && LA83_0 <= RANDOM_UC)||LA83_0==REAL||(LA83_0 >= SIZEOF_LC && LA83_0 <= SIZE_UC)||(LA83_0 >= STRING && LA83_0 <= TRUE_UC)||(LA83_0 >= UC_PERMUTE && LA83_0 <= UC_PRODUCT)||LA83_0==UC_SEQUENCE_OF) ) {
				alt83=1;
			}
			else if ( (LA83_0==LC_NOT||LA83_0==OP_NOT||LA83_0==UC_NOT) ) {
				alt83=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 83, 0, input);
				throw nvae;
			}

			switch (alt83) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2424:4: lhs= expr[defer] ro= relationalOperators rhs= expr[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expr_in_atomic_condition4532);
					lhs=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, lhs.getTree());

					pushFollow(FOLLOW_relationalOperators_in_atomic_condition4537);
					ro=relationalOperators();
					state._fsp--;

					adaptor.addChild(root_0, ro.getTree());

					pushFollow(FOLLOW_expr_in_atomic_condition4541);
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2471:4: ( LC_NOT | UC_NOT | OP_NOT ) LEFTP lac= atomic_condition[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set233=input.LT(1);
					if ( input.LA(1)==LC_NOT||input.LA(1)==OP_NOT||input.LA(1)==UC_NOT ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set233));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP234=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atomic_condition4557); 
					LEFTP234_tree = (Object)adaptor.create(LEFTP234);
					adaptor.addChild(root_0, LEFTP234_tree);

					pushFollow(FOLLOW_atomic_condition_in_atomic_condition4561);
					lac=atomic_condition(defer);
					state._fsp--;

					adaptor.addChild(root_0, lac.getTree());

					RIGHTP235=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atomic_condition4564); 
					RIGHTP235_tree = (Object)adaptor.create(RIGHTP235);
					adaptor.addChild(root_0, RIGHTP235_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2482:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* ;
	public final EugeneParser.expr_return expr(boolean defer) throws RecognitionException {
		EugeneParser.expr_return retval = new EugeneParser.expr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token op=null;
		ParserRuleReturnScope e =null;

		Object op_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2484:2: (e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2484:4: e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_multExpr_in_expr4591);
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
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2502:5: (op= ( PLUS | MINUS ) e= multExpr[defer] )*
			loop84:
			while (true) {
				int alt84=2;
				int LA84_0 = input.LA(1);
				if ( (LA84_0==MINUS||LA84_0==PLUS) ) {
					alt84=1;
				}

				switch (alt84) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2502:6: op= ( PLUS | MINUS ) e= multExpr[defer]
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
					pushFollow(FOLLOW_multExpr_in_expr4608);
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
					            NamedElement ne = null;        
					            if(null != (e!=null?((EugeneParser.multExpr_return)e).element:null)) {
					                ne = this.interp.doMinPlusOp((e!=null?((EugeneParser.multExpr_return)e).element:null), retval.p, (op!=null?op.getText():null));
					            } else {
					                ne = this.interp.doMinPlusOp((e!=null?((EugeneParser.multExpr_return)e).p:null), retval.p, (op!=null?op.getText():null)); 
					            }
					            
					            if(null != ne && ne instanceof Variable) {
					                retval.p = (Variable)ne;
					            } else {
					                printError("Invalid " + (op!=null?op.getText():null) + " operation!");
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2548:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] (op= ( MULT | DIV ) e= atom[defer] )* ;
	public final EugeneParser.multExpr_return multExpr(boolean defer) throws RecognitionException {
		EugeneParser.multExpr_return retval = new EugeneParser.multExpr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token op=null;
		ParserRuleReturnScope e =null;

		Object op_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2550:2: (e= atom[defer] (op= ( MULT | DIV ) e= atom[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2550:4: e= atom[defer] (op= ( MULT | DIV ) e= atom[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_atom_in_multExpr4638);
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
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2570:5: (op= ( MULT | DIV ) e= atom[defer] )*
			loop85:
			while (true) {
				int alt85=2;
				int LA85_0 = input.LA(1);
				if ( (LA85_0==DIV||LA85_0==MULT) ) {
					alt85=1;
				}

				switch (alt85) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2570:7: op= ( MULT | DIV ) e= atom[defer]
					{
					op=input.LT(1);
					if ( input.LA(1)==DIV||input.LA(1)==MULT ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(op));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_atom_in_multExpr4656);
					e=atom(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(null != retval.element) {
					            if(null != (e!=null?((EugeneParser.atom_return)e).element:null)) {
					                retval.element = this.interp.doMultDivOp((e!=null?((EugeneParser.atom_return)e).element:null), retval.element, (op!=null?op.getText():null));                
					            } else if(null != (e!=null?((EugeneParser.atom_return)e).p:null)) {
					                retval.element = this.interp.doMultDivOp((e!=null?((EugeneParser.atom_return)e).p:null), retval.element, (op!=null?op.getText():null));
					            }
					            retval.p = null;            
					        } else {        
					            if(null != (e!=null?((EugeneParser.atom_return)e).element:null)) {
					                this.interp.doMultDivOp((e!=null?((EugeneParser.atom_return)e).element:null), retval.p, (op!=null?op.getText():null));
					            } else {
					                this.interp.doMultDivOp((e!=null?((EugeneParser.atom_return)e).p:null), retval.p, (op!=null?op.getText():null)); 
					            }
					            retval.element = null;
					        }    
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					    
					    retval.element = null;
					}
						
					}
					break;

				default :
					break loop85;
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2597:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] |fc= function_call[defer] );
	public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
		EugeneParser.atom_return retval = new EugeneParser.atom_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token t=null;
		Token f=null;
		Token MINUS236=null;
		Token STRING237=null;
		Token char_literal238=null;
		Token char_literal240=null;
		Token LEFTSBR241=null;
		Token RIGHTSBR243=null;
		ParserRuleReturnScope dn =null;
		ParserRuleReturnScope oc =null;
		ParserRuleReturnScope bif =null;
		ParserRuleReturnScope fc =null;
		ParserRuleReturnScope expr239 =null;
		ParserRuleReturnScope list242 =null;

		Object n_tree=null;
		Object t_tree=null;
		Object f_tree=null;
		Object MINUS236_tree=null;
		Object STRING237_tree=null;
		Object char_literal238_tree=null;
		Object char_literal240_tree=null;
		Object LEFTSBR241_tree=null;
		Object RIGHTSBR243_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] |fc= function_call[defer] )
			int alt89=9;
			switch ( input.LA(1) ) {
			case NUMBER:
			case REAL:
				{
				alt89=1;
				}
				break;
			case MINUS:
				{
				alt89=2;
				}
				break;
			case FALSE_LC:
			case FALSE_UC:
			case TRUE_LC:
			case TRUE_UC:
				{
				alt89=3;
				}
				break;
			case ID:
				{
				int LA89_4 = input.LA(2);
				if ( (LA89_4==LEFTP) ) {
					alt89=9;
				}
				else if ( (LA89_4==EOF||LA89_4==AMP||LA89_4==COMMA||LA89_4==DIV||LA89_4==DOT||LA89_4==EQUALS||LA89_4==GEQUAL||LA89_4==GTHAN||LA89_4==LC_AND||LA89_4==LC_OR||(LA89_4 >= LEFTSBR && LA89_4 <= LEQUAL)||(LA89_4 >= LOG_AND && LA89_4 <= MINUS)||(LA89_4 >= MULT && LA89_4 <= NEQUAL)||(LA89_4 >= PIPE && LA89_4 <= PLUS)||(LA89_4 >= RIGHTCUR && LA89_4 <= RIGHTSBR)||LA89_4==SEMIC||LA89_4==UC_AND||LA89_4==UC_OR||LA89_4==150||(LA89_4 >= 152 && LA89_4 <= 153)||LA89_4==156||(LA89_4 >= 159 && LA89_4 <= 160)||LA89_4==162||(LA89_4 >= 175 && LA89_4 <= 176)||LA89_4==189||(LA89_4 >= 191 && LA89_4 <= 192)||LA89_4==195||(LA89_4 >= 198 && LA89_4 <= 199)||LA89_4==201||(LA89_4 >= 214 && LA89_4 <= 215)) ) {
					alt89=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 89, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DOLLAR:
				{
				alt89=4;
				}
				break;
			case STRING:
				{
				alt89=5;
				}
				break;
			case LEFTP:
				{
				alt89=6;
				}
				break;
			case LEFTSBR:
				{
				alt89=7;
				}
				break;
			case LC_PERMUTE:
			case LC_PRODUCT:
			case LC_SEQUENCE_OF:
			case QUERY_LC:
			case QUERY_UC:
			case RANDOM_LC:
			case RANDOM_UC:
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_OF_LC:
			case SIZE_OF_UC:
			case SIZE_UC:
			case UC_PERMUTE:
			case UC_PRODUCT:
			case UC_SEQUENCE_OF:
				{
				alt89=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 89, 0, input);
				throw nvae;
			}
			switch (alt89) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:4: (n= NUMBER |n= REAL )
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:4: (n= NUMBER |n= REAL )
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:5: n= NUMBER
							{
							n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4683); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2599:16: n= REAL
							{
							n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4689); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2608:4: MINUS (n= NUMBER |n= REAL )
					{
					root_0 = (Object)adaptor.nil();


					MINUS236=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom4699); 
					MINUS236_tree = (Object)adaptor.create(MINUS236);
					adaptor.addChild(root_0, MINUS236_tree);

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2608:10: (n= NUMBER |n= REAL )
					int alt87=2;
					int LA87_0 = input.LA(1);
					if ( (LA87_0==NUMBER) ) {
						alt87=1;
					}
					else if ( (LA87_0==REAL) ) {
						alt87=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 87, 0, input);
						throw nvae;
					}

					switch (alt87) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2608:11: n= NUMBER
							{
							n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4704); 
							n_tree = (Object)adaptor.create(n);
							adaptor.addChild(root_0, n_tree);

							}
							break;
						case 2 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2608:22: n= REAL
							{
							n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4710); 
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2617:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2617:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
					int alt88=2;
					int LA88_0 = input.LA(1);
					if ( ((LA88_0 >= TRUE_LC && LA88_0 <= TRUE_UC)) ) {
						alt88=1;
					}
					else if ( ((LA88_0 >= FALSE_LC && LA88_0 <= FALSE_UC)) ) {
						alt88=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 88, 0, input);
						throw nvae;
					}

					switch (alt88) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2617:5: t= ( TRUE_LC | TRUE_UC )
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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2617:27: f= ( FALSE_LC | FALSE_UC )
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2630:4: dn= dynamic_naming[defer] oc= object_access[defer, $element]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_dynamic_naming_in_atom4749);
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
						
					pushFollow(FOLLOW_object_access_in_atom4756);
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2662:4: STRING
					{
					root_0 = (Object)adaptor.nil();


					STRING237=(Token)match(input,STRING,FOLLOW_STRING_in_atom4765); 
					STRING237_tree = (Object)adaptor.create(STRING237);
					adaptor.addChild(root_0, STRING237_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p.type = EugeneConstants.TXT;
								retval.p.txt = (STRING237!=null?STRING237.getText():null).substring(1, (STRING237!=null?STRING237.getText():null).length()-1);

								retval.element = null;
							}
						
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2671:4: '(' expr[defer] ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal238=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4773); 
					char_literal238_tree = (Object)adaptor.create(char_literal238);
					adaptor.addChild(root_0, char_literal238_tree);

					pushFollow(FOLLOW_expr_in_atom4775);
					expr239=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, expr239.getTree());

					char_literal240=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4778); 
					char_literal240_tree = (Object)adaptor.create(char_literal240);
					adaptor.addChild(root_0, char_literal240_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p = (expr239!=null?((EugeneParser.expr_return)expr239).p:null);
								retval.primVariable = (expr239!=null?((EugeneParser.expr_return)expr239).primVariable:null);
								retval.element = (expr239!=null?((EugeneParser.expr_return)expr239).element:null);
							}
						
					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2679:5: LEFTSBR list[defer] RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					LEFTSBR241=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4787); 
					LEFTSBR241_tree = (Object)adaptor.create(LEFTSBR241);
					adaptor.addChild(root_0, LEFTSBR241_tree);

					pushFollow(FOLLOW_list_in_atom4789);
					list242=list(defer);
					state._fsp--;

					adaptor.addChild(root_0, list242.getTree());

					RIGHTSBR243=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4792); 
					RIGHTSBR243_tree = (Object)adaptor.create(RIGHTSBR243);
					adaptor.addChild(root_0, RIGHTSBR243_tree);


							if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
								retval.p = (list242!=null?((EugeneParser.list_return)list242).listPrim:null);
								retval.primVariable = (list242!=null?((EugeneParser.list_return)list242).listPrim:null);
								typeList="";
							}
						
					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2687:4: bif= built_in_function[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_built_in_function_in_atom4802);
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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2698:4: fc= function_call[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_function_call_in_atom4812);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2711:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
	public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
		EugeneParser.list_return retval = new EugeneParser.list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA244=null;
		ParserRuleReturnScope str1 =null;
		ParserRuleReturnScope str2 =null;

		Object COMMA244_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2713:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2713:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_list4835);
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
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2732:5: ( COMMA str2= expr[defer] )*
			loop90:
			while (true) {
				int alt90=2;
				int LA90_0 = input.LA(1);
				if ( (LA90_0==COMMA) ) {
					alt90=1;
				}

				switch (alt90) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2732:6: COMMA str2= expr[defer]
					{
					COMMA244=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4842); 
					COMMA244_tree = (Object)adaptor.create(COMMA244);
					adaptor.addChild(root_0, COMMA244_tree);

					pushFollow(FOLLOW_expr_in_list4846);
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
					break loop90;
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2750:1: built_in_function[boolean defer] returns [NamedElement element] : ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC | SIZE_OF_LC | SIZE_OF_UC ) LEFTP e= expr[defer] RIGHTP | ( LC_SEQUENCE_OF | UC_SEQUENCE_OF ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( LC_PERMUTE | UC_PERMUTE ) LEFTP idToken= ID RIGHTP | ( LC_PRODUCT | UC_PRODUCT ) LEFTP idToken= ID RIGHTP | ( QUERY_LC | QUERY_UC ) LEFTP er= expressionRule[defer] RIGHTP );
	public final EugeneParser.built_in_function_return built_in_function(boolean defer) throws RecognitionException {
		EugeneParser.built_in_function_return retval = new EugeneParser.built_in_function_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token idToken=null;
		Token set245=null;
		Token LEFTP246=null;
		Token RIGHTP247=null;
		Token set248=null;
		Token LEFTP249=null;
		Token RIGHTP250=null;
		Token set251=null;
		Token LEFTP252=null;
		Token RIGHTP253=null;
		Token set254=null;
		Token LEFTP255=null;
		Token RIGHTP256=null;
		Token set257=null;
		Token LEFTP258=null;
		Token RIGHTP259=null;
		Token set260=null;
		Token LEFTP261=null;
		Token RIGHTP262=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope rg =null;
		ParserRuleReturnScope er =null;

		Object idToken_tree=null;
		Object set245_tree=null;
		Object LEFTP246_tree=null;
		Object RIGHTP247_tree=null;
		Object set248_tree=null;
		Object LEFTP249_tree=null;
		Object RIGHTP250_tree=null;
		Object set251_tree=null;
		Object LEFTP252_tree=null;
		Object RIGHTP253_tree=null;
		Object set254_tree=null;
		Object LEFTP255_tree=null;
		Object RIGHTP256_tree=null;
		Object set257_tree=null;
		Object LEFTP258_tree=null;
		Object RIGHTP259_tree=null;
		Object set260_tree=null;
		Object LEFTP261_tree=null;
		Object RIGHTP262_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2752:2: ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC | SIZE_OF_LC | SIZE_OF_UC ) LEFTP e= expr[defer] RIGHTP | ( LC_SEQUENCE_OF | UC_SEQUENCE_OF ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( LC_PERMUTE | UC_PERMUTE ) LEFTP idToken= ID RIGHTP | ( LC_PRODUCT | UC_PRODUCT ) LEFTP idToken= ID RIGHTP | ( QUERY_LC | QUERY_UC ) LEFTP er= expressionRule[defer] RIGHTP )
			int alt91=6;
			switch ( input.LA(1) ) {
			case SIZEOF_LC:
			case SIZEOF_UC:
			case SIZE_LC:
			case SIZE_OF_LC:
			case SIZE_OF_UC:
			case SIZE_UC:
				{
				alt91=1;
				}
				break;
			case LC_SEQUENCE_OF:
			case UC_SEQUENCE_OF:
				{
				alt91=2;
				}
				break;
			case RANDOM_LC:
			case RANDOM_UC:
				{
				alt91=3;
				}
				break;
			case LC_PERMUTE:
			case UC_PERMUTE:
				{
				alt91=4;
				}
				break;
			case LC_PRODUCT:
			case UC_PRODUCT:
				{
				alt91=5;
				}
				break;
			case QUERY_LC:
			case QUERY_UC:
				{
				alt91=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 91, 0, input);
				throw nvae;
			}
			switch (alt91) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2752:4: ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC | SIZE_OF_LC | SIZE_OF_UC ) LEFTP e= expr[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set245=input.LT(1);
					if ( (input.LA(1) >= SIZEOF_LC && input.LA(1) <= SIZE_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set245));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP246=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4888); 
					LEFTP246_tree = (Object)adaptor.create(LEFTP246);
					adaptor.addChild(root_0, LEFTP246_tree);

					pushFollow(FOLLOW_expr_in_built_in_function4892);
					e=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());

					RIGHTP247=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4895); 
					RIGHTP247_tree = (Object)adaptor.create(RIGHTP247);
					adaptor.addChild(root_0, RIGHTP247_tree);


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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2765:4: ( LC_SEQUENCE_OF | UC_SEQUENCE_OF ) LEFTP e= expr[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set248=input.LT(1);
					if ( input.LA(1)==LC_SEQUENCE_OF||input.LA(1)==UC_SEQUENCE_OF ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set248));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP249=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4908); 
					LEFTP249_tree = (Object)adaptor.create(LEFTP249);
					adaptor.addChild(root_0, LEFTP249_tree);

					pushFollow(FOLLOW_expr_in_built_in_function4912);
					e=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());

					RIGHTP250=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4915); 
					RIGHTP250_tree = (Object)adaptor.create(RIGHTP250);
					adaptor.addChild(root_0, RIGHTP250_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        if(null != (e!=null?((EugeneParser.expr_return)e).element:null)) {
					            retval.element = this.interp.getSequenceOf((e!=null?((EugeneParser.expr_return)e).element:null));
					        } else if(null != (e!=null?((EugeneParser.expr_return)e).p:null)) {
					            printError("Cannot determine the sequence of " + ((e!=null?((EugeneParser.expr_return)e).p:null)).getName());
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2778:4: ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set251=input.LT(1);
					if ( (input.LA(1) >= RANDOM_LC && input.LA(1) <= RANDOM_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set251));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP252=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4929); 
					LEFTP252_tree = (Object)adaptor.create(LEFTP252);
					adaptor.addChild(root_0, LEFTP252_tree);

					pushFollow(FOLLOW_range_in_built_in_function4933);
					rg=range(defer);
					state._fsp--;

					adaptor.addChild(root_0, rg.getTree());

					RIGHTP253=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4936); 
					RIGHTP253_tree = (Object)adaptor.create(RIGHTP253);
					adaptor.addChild(root_0, RIGHTP253_tree);


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
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2793:4: ( LC_PERMUTE | UC_PERMUTE ) LEFTP idToken= ID RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set254=input.LT(1);
					if ( input.LA(1)==LC_PERMUTE||input.LA(1)==UC_PERMUTE ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set254));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP255=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4953); 
					LEFTP255_tree = (Object)adaptor.create(LEFTP255);
					adaptor.addChild(root_0, LEFTP255_tree);

					idToken=(Token)match(input,ID,FOLLOW_ID_in_built_in_function4957); 
					idToken_tree = (Object)adaptor.create(idToken);
					adaptor.addChild(root_0, idToken_tree);

					RIGHTP256=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4959); 
					RIGHTP256_tree = (Object)adaptor.create(RIGHTP256);
					adaptor.addChild(root_0, RIGHTP256_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.element = this.interp.permute((idToken!=null?idToken.getText():null));
					    } catch(Exception ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2806:4: ( LC_PRODUCT | UC_PRODUCT ) LEFTP idToken= ID RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set257=input.LT(1);
					if ( input.LA(1)==LC_PRODUCT||input.LA(1)==UC_PRODUCT ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set257));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP258=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4977); 
					LEFTP258_tree = (Object)adaptor.create(LEFTP258);
					adaptor.addChild(root_0, LEFTP258_tree);

					idToken=(Token)match(input,ID,FOLLOW_ID_in_built_in_function4981); 
					idToken_tree = (Object)adaptor.create(idToken);
					adaptor.addChild(root_0, idToken_tree);

					RIGHTP259=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4983); 
					RIGHTP259_tree = (Object)adaptor.create(RIGHTP259);
					adaptor.addChild(root_0, RIGHTP259_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.element = this.interp.product((idToken!=null?idToken.getText():null));
					    } catch(Exception ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2819:4: ( QUERY_LC | QUERY_UC ) LEFTP er= expressionRule[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set260=input.LT(1);
					if ( (input.LA(1) >= QUERY_LC && input.LA(1) <= QUERY_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set260));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP261=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function5000); 
					LEFTP261_tree = (Object)adaptor.create(LEFTP261);
					adaptor.addChild(root_0, LEFTP261_tree);

					pushFollow(FOLLOW_expressionRule_in_built_in_function5004);
					er=expressionRule(defer);
					state._fsp--;

					adaptor.addChild(root_0, er.getTree());

					RIGHTP262=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function5007); 
					RIGHTP262_tree = (Object)adaptor.create(RIGHTP262);
					adaptor.addChild(root_0, RIGHTP262_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					        retval.element = this.interp.query((er!=null?((EugeneParser.expressionRule_return)er).p:null));
					    } catch(Exception ee) {
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
	// $ANTLR end "built_in_function"


	public static class stand_alone_function_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "stand_alone_function"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2836:1: stand_alone_function[boolean defer] : ( ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC | CREATE_LC | CREATE_UC ) LEFTP e= expr[defer] RIGHTP | ( UC_AND | LC_AND ) LEFTP i= ID COMMA pred= or_predicate[defer] RIGHTP | ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )? );
	public final EugeneParser.stand_alone_function_return stand_alone_function(boolean defer) throws RecognitionException {
		EugeneParser.stand_alone_function_return retval = new EugeneParser.stand_alone_function_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token set263=null;
		Token LEFTP264=null;
		Token RIGHTP265=null;
		Token set266=null;
		Token LEFTP267=null;
		Token COMMA268=null;
		Token RIGHTP269=null;
		Token set270=null;
		Token LEFTP271=null;
		Token RIGHTP272=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope pred =null;
		ParserRuleReturnScope p =null;

		Object i_tree=null;
		Object set263_tree=null;
		Object LEFTP264_tree=null;
		Object RIGHTP265_tree=null;
		Object set266_tree=null;
		Object LEFTP267_tree=null;
		Object COMMA268_tree=null;
		Object RIGHTP269_tree=null;
		Object set270_tree=null;
		Object LEFTP271_tree=null;
		Object RIGHTP272_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2837:2: ( ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC | CREATE_LC | CREATE_UC ) LEFTP e= expr[defer] RIGHTP | ( UC_AND | LC_AND ) LEFTP i= ID COMMA pred= or_predicate[defer] RIGHTP | ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )? )
			int alt93=3;
			switch ( input.LA(1) ) {
			case CREATE_LC:
			case CREATE_UC:
			case SAVE_LC:
			case SAVE_UC:
			case STORE_LC:
			case STORE_UC:
				{
				alt93=1;
				}
				break;
			case LC_AND:
			case UC_AND:
				{
				alt93=2;
				}
				break;
			case EXIT_LC:
			case EXIT_UC:
				{
				alt93=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 93, 0, input);
				throw nvae;
			}
			switch (alt93) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2837:4: ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC | CREATE_LC | CREATE_UC ) LEFTP e= expr[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set263=input.LT(1);
					if ( (input.LA(1) >= CREATE_LC && input.LA(1) <= CREATE_UC)||(input.LA(1) >= SAVE_LC && input.LA(1) <= SAVE_UC)||(input.LA(1) >= STORE_LC && input.LA(1) <= STORE_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set263));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP264=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_stand_alone_function5038); 
					LEFTP264_tree = (Object)adaptor.create(LEFTP264);
					adaptor.addChild(root_0, LEFTP264_tree);

					pushFollow(FOLLOW_expr_in_stand_alone_function5042);
					e=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());

					RIGHTP265=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_stand_alone_function5045); 
					RIGHTP265_tree = (Object)adaptor.create(RIGHTP265);
					adaptor.addChild(root_0, RIGHTP265_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try  {
					        if(null != (e!=null?((EugeneParser.expr_return)e).element:null)) {
					            this.interp.storeIntoLibrary((e!=null?((EugeneParser.expr_return)e).element:null));
					        } else {
					            throw new EugeneException("Cannot store " + (e!=null?input.toString(e.start,e.stop):null) + " into the library!");
					        }
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2850:4: ( UC_AND | LC_AND ) LEFTP i= ID COMMA pred= or_predicate[defer] RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set266=input.LT(1);
					if ( input.LA(1)==LC_AND||input.LA(1)==UC_AND ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set266));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP267=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_stand_alone_function5058); 
					LEFTP267_tree = (Object)adaptor.create(LEFTP267);
					adaptor.addChild(root_0, LEFTP267_tree);

					i=(Token)match(input,ID,FOLLOW_ID_in_stand_alone_function5062); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);

					COMMA268=(Token)match(input,COMMA,FOLLOW_COMMA_in_stand_alone_function5064); 
					COMMA268_tree = (Object)adaptor.create(COMMA268);
					adaptor.addChild(root_0, COMMA268_tree);

					pushFollow(FOLLOW_or_predicate_in_stand_alone_function5068);
					pred=or_predicate(defer);
					state._fsp--;

					adaptor.addChild(root_0, pred.getTree());

					RIGHTP269=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_stand_alone_function5071); 
					RIGHTP269_tree = (Object)adaptor.create(RIGHTP269);
					adaptor.addChild(root_0, RIGHTP269_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    try {
					         this.interp.andRule((i!=null?i.getText():null), (pred!=null?((EugeneParser.or_predicate_return)pred).p:null));
					    } catch(EugeneException ee) {
					        printError(ee.getLocalizedMessage());
					    }
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2859:4: ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )?
					{
					root_0 = (Object)adaptor.nil();


					set270=input.LT(1);
					if ( (input.LA(1) >= EXIT_LC && input.LA(1) <= EXIT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set270));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2859:24: ( LEFTP p= toPrint[defer] RIGHTP )?
					int alt92=2;
					int LA92_0 = input.LA(1);
					if ( (LA92_0==LEFTP) ) {
						alt92=1;
					}
					switch (alt92) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2859:25: LEFTP p= toPrint[defer] RIGHTP
							{
							LEFTP271=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_stand_alone_function5087); 
							LEFTP271_tree = (Object)adaptor.create(LEFTP271);
							adaptor.addChild(root_0, LEFTP271_tree);

							pushFollow(FOLLOW_toPrint_in_stand_alone_function5091);
							p=toPrint(defer);
							state._fsp--;

							adaptor.addChild(root_0, p.getTree());

							RIGHTP272=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_stand_alone_function5094); 
							RIGHTP272_tree = (Object)adaptor.create(RIGHTP272);
							adaptor.addChild(root_0, RIGHTP272_tree);

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
	// $ANTLR end "stand_alone_function"


	public static class range_return extends ParserRuleReturnScope {
		public Variable sor;
		public Variable eor;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "range"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2870:1: range[boolean defer] returns [Variable sor, Variable eor] : s= expr[defer] COMMA e= expr[defer] ;
	public final EugeneParser.range_return range(boolean defer) throws RecognitionException {
		EugeneParser.range_return retval = new EugeneParser.range_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA273=null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope e =null;

		Object COMMA273_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2872:2: (s= expr[defer] COMMA e= expr[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2872:4: s= expr[defer] COMMA e= expr[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_range5118);
			s=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, s.getTree());

			COMMA273=(Token)match(input,COMMA,FOLLOW_COMMA_in_range5121); 
			COMMA273_tree = (Object)adaptor.create(COMMA273);
			adaptor.addChild(root_0, COMMA273_tree);

			pushFollow(FOLLOW_expr_in_range5125);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2925:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
	public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
		EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token id=null;
		Token DOT274=null;
		Token set275=null;
		Token LEFTP276=null;
		Token RIGHTP277=null;
		Token LEFTSBR278=null;
		Token RIGHTSBR279=null;
		ParserRuleReturnScope exp =null;
		ParserRuleReturnScope o =null;

		Object id_tree=null;
		Object DOT274_tree=null;
		Object set275_tree=null;
		Object LEFTP276_tree=null;
		Object RIGHTP277_tree=null;
		Object LEFTSBR278_tree=null;
		Object RIGHTSBR279_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2927:2: (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
			int alt97=2;
			int LA97_0 = input.LA(1);
			if ( (LA97_0==EOF||LA97_0==AMP||LA97_0==COMMA||LA97_0==DIV||LA97_0==EQUALS||LA97_0==GEQUAL||LA97_0==GTHAN||LA97_0==LC_AND||LA97_0==LC_OR||LA97_0==LEQUAL||(LA97_0 >= LOG_AND && LA97_0 <= MINUS)||(LA97_0 >= MULT && LA97_0 <= NEQUAL)||(LA97_0 >= PIPE && LA97_0 <= PLUS)||(LA97_0 >= RIGHTCUR && LA97_0 <= RIGHTSBR)||LA97_0==SEMIC||LA97_0==UC_AND||LA97_0==UC_OR||LA97_0==150||(LA97_0 >= 152 && LA97_0 <= 153)||LA97_0==156||(LA97_0 >= 159 && LA97_0 <= 160)||LA97_0==162||(LA97_0 >= 175 && LA97_0 <= 176)||LA97_0==189||(LA97_0 >= 191 && LA97_0 <= 192)||LA97_0==195||(LA97_0 >= 198 && LA97_0 <= 199)||LA97_0==201||(LA97_0 >= 214 && LA97_0 <= 215)) ) {
				alt97=1;
			}
			else if ( (LA97_0==DOT||LA97_0==LEFTSBR) ) {
				alt97=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 97, 0, input);
				throw nvae;
			}

			switch (alt97) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2928:2: 
					{
					root_0 = (Object)adaptor.nil();



					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.child = parent;
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2933:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
					{
					root_0 = (Object)adaptor.nil();


					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2933:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
					int alt96=2;
					int LA96_0 = input.LA(1);
					if ( (LA96_0==DOT) ) {
						alt96=1;
					}
					else if ( (LA96_0==LEFTSBR) ) {
						alt96=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 96, 0, input);
						throw nvae;
					}

					switch (alt96) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2933:5: DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
							{
							DOT274=(Token)match(input,DOT,FOLLOW_DOT_in_object_access5161); 
							DOT274_tree = (Object)adaptor.create(DOT274);
							adaptor.addChild(root_0, DOT274_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2933:9: (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
							int alt95=2;
							int LA95_0 = input.LA(1);
							if ( (LA95_0==ID) ) {
								alt95=1;
							}
							else if ( (LA95_0==SIZE_LC||LA95_0==SIZE_UC) ) {
								alt95=2;
							}

							else {
								NoViableAltException nvae =
									new NoViableAltException("", 95, 0, input);
								throw nvae;
							}

							switch (alt95) {
								case 1 :
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2933:10: id= ID
									{
									id=(Token)match(input,ID,FOLLOW_ID_in_object_access5166); 
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
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2947:6: ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )?
									{
									set275=input.LT(1);
									if ( input.LA(1)==SIZE_LC||input.LA(1)==SIZE_UC ) {
										input.consume();
										adaptor.addChild(root_0, (Object)adaptor.create(set275));
										state.errorRecovery=false;
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										throw mse;
									}
									// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2947:24: ( LEFTP RIGHTP )?
									int alt94=2;
									int LA94_0 = input.LA(1);
									if ( (LA94_0==LEFTP) ) {
										alt94=1;
									}
									switch (alt94) {
										case 1 :
											// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2947:25: LEFTP RIGHTP
											{
											LEFTP276=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_object_access5179); 
											LEFTP276_tree = (Object)adaptor.create(LEFTP276);
											adaptor.addChild(root_0, LEFTP276_tree);

											RIGHTP277=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_object_access5181); 
											RIGHTP277_tree = (Object)adaptor.create(RIGHTP277);
											adaptor.addChild(root_0, RIGHTP277_tree);

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
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2956:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
							{
							LEFTSBR278=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access5191); 
							LEFTSBR278_tree = (Object)adaptor.create(LEFTSBR278);
							adaptor.addChild(root_0, LEFTSBR278_tree);

							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2956:12: (exp= expr[defer] )
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2956:13: exp= expr[defer]
							{
							pushFollow(FOLLOW_expr_in_object_access5196);
							exp=expr(defer);
							state._fsp--;

							adaptor.addChild(root_0, exp.getTree());

							}

							RIGHTSBR279=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access5200); 
							RIGHTSBR279_tree = (Object)adaptor.create(RIGHTSBR279);
							adaptor.addChild(root_0, RIGHTSBR279_tree);


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

					pushFollow(FOLLOW_object_access_in_object_access5207);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2988:1: dynamic_naming[boolean defer] returns [String name] : (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR );
	public final EugeneParser.dynamic_naming_return dynamic_naming(boolean defer) throws RecognitionException {
		EugeneParser.dynamic_naming_return retval = new EugeneParser.dynamic_naming_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		Token DOLLAR280=null;
		Token LEFTCUR281=null;
		Token RIGHTCUR282=null;
		ParserRuleReturnScope e =null;

		Object i_tree=null;
		Object DOLLAR280_tree=null;
		Object LEFTCUR281_tree=null;
		Object RIGHTCUR282_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2990:2: (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR )
			int alt98=2;
			int LA98_0 = input.LA(1);
			if ( (LA98_0==ID) ) {
				alt98=1;
			}
			else if ( (LA98_0==DOLLAR) ) {
				alt98=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 98, 0, input);
				throw nvae;
			}

			switch (alt98) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2990:4: i= ID
					{
					root_0 = (Object)adaptor.nil();


					i=(Token)match(input,ID,FOLLOW_ID_in_dynamic_naming5232); 
					i_tree = (Object)adaptor.create(i);
					adaptor.addChild(root_0, i_tree);


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.name = (i!=null?i.getText():null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2995:4: DOLLAR LEFTCUR e= expr[defer] RIGHTCUR
					{
					root_0 = (Object)adaptor.nil();


					DOLLAR280=(Token)match(input,DOLLAR,FOLLOW_DOLLAR_in_dynamic_naming5239); 
					DOLLAR280_tree = (Object)adaptor.create(DOLLAR280);
					adaptor.addChild(root_0, DOLLAR280_tree);

					LEFTCUR281=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_dynamic_naming5241); 
					LEFTCUR281_tree = (Object)adaptor.create(LEFTCUR281);
					adaptor.addChild(root_0, LEFTCUR281_tree);

					pushFollow(FOLLOW_expr_in_dynamic_naming5245);
					e=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, e.getTree());

					RIGHTCUR282=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_dynamic_naming5248); 
					RIGHTCUR282_tree = (Object)adaptor.create(RIGHTCUR282);
					adaptor.addChild(root_0, RIGHTCUR282_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3012:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |i= importStatement[defer] |g= genbankStatement[defer] |r= registryStatement[defer] );
	public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
		EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope s =null;
		ParserRuleReturnScope i =null;
		ParserRuleReturnScope g =null;
		ParserRuleReturnScope r =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3014:2: (s= sbolStatement[defer] |i= importStatement[defer] |g= genbankStatement[defer] |r= registryStatement[defer] )
			int alt99=4;
			switch ( input.LA(1) ) {
			case SBOL:
				{
				alt99=1;
				}
				break;
			case IMPORT_LC:
			case IMPORT_UC:
				{
				alt99=2;
				}
				break;
			case GENBANK:
				{
				alt99=3;
				}
				break;
			case REGISTRY:
				{
				alt99=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 99, 0, input);
				throw nvae;
			}
			switch (alt99) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3014:4: s= sbolStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_sbolStatement_in_dataExchange5273);
					s=sbolStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, s.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (s!=null?((EugeneParser.sbolStatement_return)s).e:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3019:4: i= importStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_importStatement_in_dataExchange5283);
					i=importStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, i.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (i!=null?((EugeneParser.importStatement_return)i).e:null);
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3024:4: g= genbankStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_genbankStatement_in_dataExchange5293);
					g=genbankStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, g.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (g!=null?((EugeneParser.genbankStatement_return)g).e:null);
					}	
						
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3030:4: r= registryStatement[defer]
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_registryStatement_in_dataExchange5303);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3038:1: includeStatement[boolean defer] : ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING ;
	public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
		EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token file=null;
		Token HASHMARK283=null;
		Token set284=null;

		Object file_tree=null;
		Object HASHMARK283_tree=null;
		Object set284_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3039:2: ( ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3039:4: ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3039:4: ( HASHMARK )?
			int alt100=2;
			int LA100_0 = input.LA(1);
			if ( (LA100_0==HASHMARK) ) {
				alt100=1;
			}
			switch (alt100) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3039:5: HASHMARK
					{
					HASHMARK283=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement5322); 
					HASHMARK283_tree = (Object)adaptor.create(HASHMARK283);
					adaptor.addChild(root_0, HASHMARK283_tree);

					}
					break;

			}

			set284=input.LT(1);
			if ( (input.LA(1) >= INCLUDE_LC && input.LA(1) <= INCLUDE_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set284));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement5334); 
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3057:1: importStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP ;
	public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
		EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token file=null;
		Token set285=null;
		Token LEFTP286=null;
		Token RIGHTP287=null;

		Object file_tree=null;
		Object set285_tree=null;
		Object LEFTP286_tree=null;
		Object RIGHTP287_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3059:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3059:4: ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set285=input.LT(1);
			if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set285));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP286=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement5361); 
			LEFTP286_tree = (Object)adaptor.create(LEFTP286);
			adaptor.addChild(root_0, LEFTP286_tree);

			file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement5365); 
			file_tree = (Object)adaptor.create(file);
			adaptor.addChild(root_0, file_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        retval.e = this.interp.importFile((file!=null?file.getText():null));
			    } catch(EugeneException ee) {
			        printError(ee.getLocalizedMessage());
			    }
			}
				
			RIGHTP287=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement5369); 
			RIGHTP287_tree = (Object)adaptor.create(RIGHTP287);
			adaptor.addChild(root_0, RIGHTP287_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3071:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] ) ;
	public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SBOL288=null;
		Token DOT289=null;
		ParserRuleReturnScope i =null;
		ParserRuleReturnScope sbolExportStatement290 =null;
		ParserRuleReturnScope sbolVisualStatement291 =null;

		Object SBOL288_tree=null;
		Object DOT289_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3073:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3073:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] )
			{
			root_0 = (Object)adaptor.nil();


			SBOL288=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement5391); 
			SBOL288_tree = (Object)adaptor.create(SBOL288);
			adaptor.addChild(root_0, SBOL288_tree);

			DOT289=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement5393); 
			DOT289_tree = (Object)adaptor.create(DOT289);
			adaptor.addChild(root_0, DOT289_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3073:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] | sbolVisualStatement[defer] )
			int alt101=3;
			switch ( input.LA(1) ) {
			case EXPORT_LC:
			case EXPORT_UC:
				{
				alt101=1;
				}
				break;
			case IMPORT_LC:
			case IMPORT_UC:
				{
				alt101=2;
				}
				break;
			case VISUALIZE_LC:
			case VISUALIZE_UC:
				{
				alt101=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 101, 0, input);
				throw nvae;
			}
			switch (alt101) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3073:14: sbolExportStatement[defer]
					{
					pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement5396);
					sbolExportStatement290=sbolExportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, sbolExportStatement290.getTree());

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3073:43: i= sbolImportStatement[defer]
					{
					pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement5403);
					i=sbolImportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, i.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (i!=null?((EugeneParser.sbolImportStatement_return)i).e:null);
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3077:7: sbolVisualStatement[defer]
					{
					pushFollow(FOLLOW_sbolVisualStatement_in_sbolStatement5411);
					sbolVisualStatement291=sbolVisualStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, sbolVisualStatement291.getTree());


						
						
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3082:1: sbolExportStatement[boolean defer] : ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
	public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token idToken=null;
		Token filenameToken=null;
		Token set292=null;
		Token LEFTP293=null;
		Token COMMA294=null;
		Token RIGHTP295=null;

		Object idToken_tree=null;
		Object filenameToken_tree=null;
		Object set292_tree=null;
		Object LEFTP293_tree=null;
		Object COMMA294_tree=null;
		Object RIGHTP295_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3083:2: ( ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3083:4: ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set292=input.LT(1);
			if ( (input.LA(1) >= EXPORT_LC && input.LA(1) <= EXPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set292));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP293=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement5434); 
			LEFTP293_tree = (Object)adaptor.create(LEFTP293);
			adaptor.addChild(root_0, LEFTP293_tree);

			idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement5438); 
			idToken_tree = (Object)adaptor.create(idToken);
			adaptor.addChild(root_0, idToken_tree);

			COMMA294=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement5440); 
			COMMA294_tree = (Object)adaptor.create(COMMA294);
			adaptor.addChild(root_0, COMMA294_tree);

			filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement5444); 
			filenameToken_tree = (Object)adaptor.create(filenameToken);
			adaptor.addChild(root_0, filenameToken_tree);

			RIGHTP295=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement5446); 
			RIGHTP295_tree = (Object)adaptor.create(RIGHTP295);
			adaptor.addChild(root_0, RIGHTP295_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3096:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP ;
	public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token fileToken=null;
		Token set296=null;
		Token LEFTP297=null;
		Token RIGHTP298=null;

		Object fileToken_tree=null;
		Object set296_tree=null;
		Object LEFTP297_tree=null;
		Object RIGHTP298_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3098:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3098:4: ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set296=input.LT(1);
			if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set296));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP297=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement5475); 
			LEFTP297_tree = (Object)adaptor.create(LEFTP297);
			adaptor.addChild(root_0, LEFTP297_tree);

			fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement5479); 
			fileToken_tree = (Object)adaptor.create(fileToken);
			adaptor.addChild(root_0, fileToken_tree);

			RIGHTP298=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement5481); 
			RIGHTP298_tree = (Object)adaptor.create(RIGHTP298);
			adaptor.addChild(root_0, RIGHTP298_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3109:1: sbolVisualStatement[boolean defer] : ( VISUALIZE_LC | VISUALIZE_UC ) LEFTP e= expr[defer] ( COMMA f= expr[defer] )? RIGHTP ;
	public final EugeneParser.sbolVisualStatement_return sbolVisualStatement(boolean defer) throws RecognitionException {
		EugeneParser.sbolVisualStatement_return retval = new EugeneParser.sbolVisualStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set299=null;
		Token LEFTP300=null;
		Token COMMA301=null;
		Token RIGHTP302=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope f =null;

		Object set299_tree=null;
		Object LEFTP300_tree=null;
		Object COMMA301_tree=null;
		Object RIGHTP302_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3110:2: ( ( VISUALIZE_LC | VISUALIZE_UC ) LEFTP e= expr[defer] ( COMMA f= expr[defer] )? RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3110:4: ( VISUALIZE_LC | VISUALIZE_UC ) LEFTP e= expr[defer] ( COMMA f= expr[defer] )? RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set299=input.LT(1);
			if ( (input.LA(1) >= VISUALIZE_LC && input.LA(1) <= VISUALIZE_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set299));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP300=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolVisualStatement5503); 
			LEFTP300_tree = (Object)adaptor.create(LEFTP300);
			adaptor.addChild(root_0, LEFTP300_tree);

			pushFollow(FOLLOW_expr_in_sbolVisualStatement5507);
			e=expr(defer);
			state._fsp--;

			adaptor.addChild(root_0, e.getTree());

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3110:52: ( COMMA f= expr[defer] )?
			int alt102=2;
			int LA102_0 = input.LA(1);
			if ( (LA102_0==COMMA) ) {
				alt102=1;
			}
			switch (alt102) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3110:53: COMMA f= expr[defer]
					{
					COMMA301=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolVisualStatement5511); 
					COMMA301_tree = (Object)adaptor.create(COMMA301);
					adaptor.addChild(root_0, COMMA301_tree);

					pushFollow(FOLLOW_expr_in_sbolVisualStatement5515);
					f=expr(defer);
					state._fsp--;

					adaptor.addChild(root_0, f.getTree());

					}
					break;

			}

			RIGHTP302=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolVisualStatement5520); 
			RIGHTP302_tree = (Object)adaptor.create(RIGHTP302);
			adaptor.addChild(root_0, RIGHTP302_tree);


			if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			    try {
			        // we check if the user specified a filename
			        if(null == f) {        
			            // no filename provided
			            this.interp.visualizeSBOL((e!=null?((EugeneParser.expr_return)e).element:null), null);
			        } else {
			            // filename provided
			            if(null != (f!=null?((EugeneParser.expr_return)f).p:null)) {
			                this.interp.visualizeSBOL((e!=null?((EugeneParser.expr_return)e).element:null), (f!=null?((EugeneParser.expr_return)f).p:null));
			            } else {
			                printError("Invalid filename.");
			            }
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
	// $ANTLR end "sbolVisualStatement"


	public static class genbankStatement_return extends ParserRuleReturnScope {
		public NamedElement e;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "genbankStatement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3135:1: genbankStatement[boolean defer] returns [NamedElement e] : GENBANK DOT (i= genbankImportStatement[defer] | genbankExportStatement[defer] ) ;
	public final EugeneParser.genbankStatement_return genbankStatement(boolean defer) throws RecognitionException {
		EugeneParser.genbankStatement_return retval = new EugeneParser.genbankStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token GENBANK303=null;
		Token DOT304=null;
		ParserRuleReturnScope i =null;
		ParserRuleReturnScope genbankExportStatement305 =null;

		Object GENBANK303_tree=null;
		Object DOT304_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3137:2: ( GENBANK DOT (i= genbankImportStatement[defer] | genbankExportStatement[defer] ) )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3137:4: GENBANK DOT (i= genbankImportStatement[defer] | genbankExportStatement[defer] )
			{
			root_0 = (Object)adaptor.nil();


			GENBANK303=(Token)match(input,GENBANK,FOLLOW_GENBANK_in_genbankStatement5545); 
			GENBANK303_tree = (Object)adaptor.create(GENBANK303);
			adaptor.addChild(root_0, GENBANK303_tree);

			DOT304=(Token)match(input,DOT,FOLLOW_DOT_in_genbankStatement5547); 
			DOT304_tree = (Object)adaptor.create(DOT304);
			adaptor.addChild(root_0, DOT304_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3137:16: (i= genbankImportStatement[defer] | genbankExportStatement[defer] )
			int alt103=2;
			int LA103_0 = input.LA(1);
			if ( ((LA103_0 >= IMPORT_LC && LA103_0 <= IMPORT_UC)) ) {
				alt103=1;
			}
			else if ( ((LA103_0 >= EXPORT_LC && LA103_0 <= EXPORT_UC)) ) {
				alt103=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 103, 0, input);
				throw nvae;
			}

			switch (alt103) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3137:17: i= genbankImportStatement[defer]
					{
					pushFollow(FOLLOW_genbankImportStatement_in_genbankStatement5552);
					i=genbankImportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, i.getTree());


					if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
					    retval.e = (i!=null?((EugeneParser.genbankImportStatement_return)i).e:null);
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3142:4: genbankExportStatement[defer]
					{
					pushFollow(FOLLOW_genbankExportStatement_in_genbankStatement5560);
					genbankExportStatement305=genbankExportStatement(defer);
					state._fsp--;

					adaptor.addChild(root_0, genbankExportStatement305.getTree());

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3149:1: genbankExportStatement[boolean defer] : ( EXPORT_UC | EXPORT_LC ) LEFTP RIGHTP ;
	public final EugeneParser.genbankExportStatement_return genbankExportStatement(boolean defer) throws RecognitionException {
		EugeneParser.genbankExportStatement_return retval = new EugeneParser.genbankExportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set306=null;
		Token LEFTP307=null;
		Token RIGHTP308=null;

		Object set306_tree=null;
		Object LEFTP307_tree=null;
		Object RIGHTP308_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3150:2: ( ( EXPORT_UC | EXPORT_LC ) LEFTP RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3150:4: ( EXPORT_UC | EXPORT_LC ) LEFTP RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			set306=input.LT(1);
			if ( (input.LA(1) >= EXPORT_LC && input.LA(1) <= EXPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set306));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP307=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankExportStatement5585); 
			LEFTP307_tree = (Object)adaptor.create(LEFTP307);
			adaptor.addChild(root_0, LEFTP307_tree);

			RIGHTP308=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankExportStatement5587); 
			RIGHTP308_tree = (Object)adaptor.create(RIGHTP308);
			adaptor.addChild(root_0, RIGHTP308_tree);

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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3153:1: genbankImportStatement[boolean defer] returns [NamedElement e] : ( ( IMPORT_LC | IMPORT_UC ) LEFTP f= STRING RIGHTP | ( IMPORT_LC | IMPORT_UC ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP );
	public final EugeneParser.genbankImportStatement_return genbankImportStatement(boolean defer) throws RecognitionException {
		EugeneParser.genbankImportStatement_return retval = new EugeneParser.genbankImportStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token f=null;
		Token typeToken=null;
		Token partToken=null;
		Token set309=null;
		Token LEFTP310=null;
		Token RIGHTP311=null;
		Token set312=null;
		Token LEFTP313=null;
		Token COMMA314=null;
		Token RIGHTP315=null;

		Object f_tree=null;
		Object typeToken_tree=null;
		Object partToken_tree=null;
		Object set309_tree=null;
		Object LEFTP310_tree=null;
		Object RIGHTP311_tree=null;
		Object set312_tree=null;
		Object LEFTP313_tree=null;
		Object COMMA314_tree=null;
		Object RIGHTP315_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3155:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP f= STRING RIGHTP | ( IMPORT_LC | IMPORT_UC ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP )
			int alt104=2;
			int LA104_0 = input.LA(1);
			if ( ((LA104_0 >= IMPORT_LC && LA104_0 <= IMPORT_UC)) ) {
				int LA104_1 = input.LA(2);
				if ( (LA104_1==LEFTP) ) {
					int LA104_2 = input.LA(3);
					if ( (LA104_2==STRING) ) {
						alt104=1;
					}
					else if ( (LA104_2==ID) ) {
						alt104=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 104, 2, input);
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
							new NoViableAltException("", 104, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 104, 0, input);
				throw nvae;
			}

			switch (alt104) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3155:4: ( IMPORT_LC | IMPORT_UC ) LEFTP f= STRING RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set309=input.LT(1);
					if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set309));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP310=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankImportStatement5619); 
					LEFTP310_tree = (Object)adaptor.create(LEFTP310);
					adaptor.addChild(root_0, LEFTP310_tree);

					f=(Token)match(input,STRING,FOLLOW_STRING_in_genbankImportStatement5623); 
					f_tree = (Object)adaptor.create(f);
					adaptor.addChild(root_0, f_tree);

					RIGHTP311=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankImportStatement5625); 
					RIGHTP311_tree = (Object)adaptor.create(RIGHTP311);
					adaptor.addChild(root_0, RIGHTP311_tree);


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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3164:4: ( IMPORT_LC | IMPORT_UC ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					set312=input.LT(1);
					if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set312));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP313=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankImportStatement5638); 
					LEFTP313_tree = (Object)adaptor.create(LEFTP313);
					adaptor.addChild(root_0, LEFTP313_tree);

					typeToken=(Token)match(input,ID,FOLLOW_ID_in_genbankImportStatement5642); 
					typeToken_tree = (Object)adaptor.create(typeToken);
					adaptor.addChild(root_0, typeToken_tree);

					COMMA314=(Token)match(input,COMMA,FOLLOW_COMMA_in_genbankImportStatement5644); 
					COMMA314_tree = (Object)adaptor.create(COMMA314);
					adaptor.addChild(root_0, COMMA314_tree);

					partToken=(Token)match(input,STRING,FOLLOW_STRING_in_genbankImportStatement5648); 
					partToken_tree = (Object)adaptor.create(partToken);
					adaptor.addChild(root_0, partToken_tree);

					RIGHTP315=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankImportStatement5650); 
					RIGHTP315_tree = (Object)adaptor.create(RIGHTP315);
					adaptor.addChild(root_0, RIGHTP315_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3175:1: registryStatement[boolean defer] returns [NamedElement e] : REGISTRY DOT ( IMPORT_LC | IMPORT_UC ) LEFTP n= STRING RIGHTP ;
	public final EugeneParser.registryStatement_return registryStatement(boolean defer) throws RecognitionException {
		EugeneParser.registryStatement_return retval = new EugeneParser.registryStatement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token REGISTRY316=null;
		Token DOT317=null;
		Token set318=null;
		Token LEFTP319=null;
		Token RIGHTP320=null;

		Object n_tree=null;
		Object REGISTRY316_tree=null;
		Object DOT317_tree=null;
		Object set318_tree=null;
		Object LEFTP319_tree=null;
		Object RIGHTP320_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3177:2: ( REGISTRY DOT ( IMPORT_LC | IMPORT_UC ) LEFTP n= STRING RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3177:4: REGISTRY DOT ( IMPORT_LC | IMPORT_UC ) LEFTP n= STRING RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			REGISTRY316=(Token)match(input,REGISTRY,FOLLOW_REGISTRY_in_registryStatement5674); 
			REGISTRY316_tree = (Object)adaptor.create(REGISTRY316);
			adaptor.addChild(root_0, REGISTRY316_tree);

			DOT317=(Token)match(input,DOT,FOLLOW_DOT_in_registryStatement5676); 
			DOT317_tree = (Object)adaptor.create(DOT317);
			adaptor.addChild(root_0, DOT317_tree);

			set318=input.LT(1);
			if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set318));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			LEFTP319=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_registryStatement5684); 
			LEFTP319_tree = (Object)adaptor.create(LEFTP319);
			adaptor.addChild(root_0, LEFTP319_tree);

			n=(Token)match(input,STRING,FOLLOW_STRING_in_registryStatement5688); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			RIGHTP320=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_registryStatement5690); 
			RIGHTP320_tree = (Object)adaptor.create(RIGHTP320);
			adaptor.addChild(root_0, RIGHTP320_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3192:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
	public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
		EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token id=null;
		Token n=null;
		Token ASSERT321=null;
		Token LEFTP322=null;
		Token DOT323=null;
		Token set324=null;
		Token LEFTP325=null;
		Token RIGHTP326=null;
		Token EQUALS327=null;
		Token EQUALS328=null;
		Token RIGHTP329=null;
		Token NOTE330=null;
		Token LEFTP331=null;
		Token DOT332=null;
		Token set333=null;
		Token LEFTP334=null;
		Token RIGHTP335=null;
		Token EQUALS336=null;
		Token EQUALS337=null;
		Token RIGHTP338=null;

		Object id_tree=null;
		Object n_tree=null;
		Object ASSERT321_tree=null;
		Object LEFTP322_tree=null;
		Object DOT323_tree=null;
		Object set324_tree=null;
		Object LEFTP325_tree=null;
		Object RIGHTP326_tree=null;
		Object EQUALS327_tree=null;
		Object EQUALS328_tree=null;
		Object RIGHTP329_tree=null;
		Object NOTE330_tree=null;
		Object LEFTP331_tree=null;
		Object DOT332_tree=null;
		Object set333_tree=null;
		Object LEFTP334_tree=null;
		Object RIGHTP335_tree=null;
		Object EQUALS336_tree=null;
		Object EQUALS337_tree=null;
		Object RIGHTP338_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3193:2: (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
			int alt105=3;
			switch ( input.LA(1) ) {
			case EOF:
				{
				alt105=1;
				}
				break;
			case ASSERT:
				{
				alt105=2;
				}
				break;
			case NOTE:
				{
				alt105=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 105, 0, input);
				throw nvae;
			}
			switch (alt105) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3193:5: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3193:7: ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					ASSERT321=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements5709); 
					ASSERT321_tree = (Object)adaptor.create(ASSERT321);
					adaptor.addChild(root_0, ASSERT321_tree);

					LEFTP322=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5711); 
					LEFTP322_tree = (Object)adaptor.create(LEFTP322);
					adaptor.addChild(root_0, LEFTP322_tree);

					id=(Token)match(input,ID,FOLLOW_ID_in_testStatements5715); 
					id_tree = (Object)adaptor.create(id);
					adaptor.addChild(root_0, id_tree);

					DOT323=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements5717); 
					DOT323_tree = (Object)adaptor.create(DOT323);
					adaptor.addChild(root_0, DOT323_tree);

					set324=input.LT(1);
					if ( input.LA(1)==SIZE_LC||input.LA(1)==SIZE_UC ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set324));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP325=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5725); 
					LEFTP325_tree = (Object)adaptor.create(LEFTP325);
					adaptor.addChild(root_0, LEFTP325_tree);

					RIGHTP326=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5727); 
					RIGHTP326_tree = (Object)adaptor.create(RIGHTP326);
					adaptor.addChild(root_0, RIGHTP326_tree);

					EQUALS327=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5729); 
					EQUALS327_tree = (Object)adaptor.create(EQUALS327);
					adaptor.addChild(root_0, EQUALS327_tree);

					EQUALS328=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5731); 
					EQUALS328_tree = (Object)adaptor.create(EQUALS328);
					adaptor.addChild(root_0, EQUALS328_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5735); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);

					RIGHTP329=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5737); 
					RIGHTP329_tree = (Object)adaptor.create(RIGHTP329);
					adaptor.addChild(root_0, RIGHTP329_tree);


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
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3209:5: NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
					{
					root_0 = (Object)adaptor.nil();


					NOTE330=(Token)match(input,NOTE,FOLLOW_NOTE_in_testStatements5745); 
					NOTE330_tree = (Object)adaptor.create(NOTE330);
					adaptor.addChild(root_0, NOTE330_tree);

					LEFTP331=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5747); 
					LEFTP331_tree = (Object)adaptor.create(LEFTP331);
					adaptor.addChild(root_0, LEFTP331_tree);

					id=(Token)match(input,ID,FOLLOW_ID_in_testStatements5751); 
					id_tree = (Object)adaptor.create(id);
					adaptor.addChild(root_0, id_tree);

					DOT332=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements5753); 
					DOT332_tree = (Object)adaptor.create(DOT332);
					adaptor.addChild(root_0, DOT332_tree);

					set333=input.LT(1);
					if ( input.LA(1)==SIZE_LC||input.LA(1)==SIZE_UC ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set333));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					LEFTP334=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5761); 
					LEFTP334_tree = (Object)adaptor.create(LEFTP334);
					adaptor.addChild(root_0, LEFTP334_tree);

					RIGHTP335=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5763); 
					RIGHTP335_tree = (Object)adaptor.create(RIGHTP335);
					adaptor.addChild(root_0, RIGHTP335_tree);

					EQUALS336=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5765); 
					EQUALS336_tree = (Object)adaptor.create(EQUALS336);
					adaptor.addChild(root_0, EQUALS336_tree);

					EQUALS337=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5767); 
					EQUALS337_tree = (Object)adaptor.create(EQUALS337);
					adaptor.addChild(root_0, EQUALS337_tree);

					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5771); 
					n_tree = (Object)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);

					RIGHTP338=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5773); 
					RIGHTP338_tree = (Object)adaptor.create(RIGHTP338);
					adaptor.addChild(root_0, RIGHTP338_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3218:1: function_definition[boolean defer] : (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR ;
	public final EugeneParser.function_definition_return function_definition(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.function_definition_return retval = new EugeneParser.function_definition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token LEFTP339=null;
		Token RIGHTP340=null;
		Token LEFTCUR341=null;
		Token RIGHTCUR342=null;
		ParserRuleReturnScope rt =null;
		ParserRuleReturnScope lop =null;
		ParserRuleReturnScope stmts =null;

		Object n_tree=null;
		Object LEFTP339_tree=null;
		Object RIGHTP340_tree=null;
		Object LEFTCUR341_tree=null;
		Object RIGHTCUR342_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3220:2: ( (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3220:4: (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= list_of_statements[true] RIGHTCUR
			{
			root_0 = (Object)adaptor.nil();


			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3220:4: (rt= type_specification[true] )?
			int alt106=2;
			int LA106_0 = input.LA(1);
			if ( ((LA106_0 >= BOOL && LA106_0 <= BOOLEAN)||LA106_0==NUM||LA106_0==TXT) ) {
				alt106=1;
			}
			switch (alt106) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3220:5: rt= type_specification[true]
					{
					pushFollow(FOLLOW_type_specification_in_function_definition5798);
					rt=type_specification(true);
					state._fsp--;

					adaptor.addChild(root_0, rt.getTree());

					}
					break;

			}

			n=(Token)match(input,ID,FOLLOW_ID_in_function_definition5805); 
			n_tree = (Object)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);

			LEFTP339=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_function_definition5807); 
			LEFTP339_tree = (Object)adaptor.create(LEFTP339);
			adaptor.addChild(root_0, LEFTP339_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3220:46: (lop= list_of_parameters[true] )?
			int alt107=2;
			int LA107_0 = input.LA(1);
			if ( ((LA107_0 >= BOOL && LA107_0 <= BOOLEAN)||LA107_0==NUM||LA107_0==TXT) ) {
				alt107=1;
			}
			switch (alt107) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3220:47: lop= list_of_parameters[true]
					{
					pushFollow(FOLLOW_list_of_parameters_in_function_definition5812);
					lop=list_of_parameters(true);
					state._fsp--;

					adaptor.addChild(root_0, lop.getTree());

					}
					break;

			}

			RIGHTP340=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_function_definition5817); 
			RIGHTP340_tree = (Object)adaptor.create(RIGHTP340);
			adaptor.addChild(root_0, RIGHTP340_tree);

			LEFTCUR341=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_function_definition5819); 
			LEFTCUR341_tree = (Object)adaptor.create(LEFTCUR341);
			adaptor.addChild(root_0, LEFTCUR341_tree);

			pushFollow(FOLLOW_list_of_statements_in_function_definition5827);
			stmts=list_of_statements(true);
			state._fsp--;

			adaptor.addChild(root_0, stmts.getTree());

			RIGHTCUR342=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_function_definition5833); 
			RIGHTCUR342_tree = (Object)adaptor.create(RIGHTCUR342);
			adaptor.addChild(root_0, RIGHTCUR342_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3252:1: type_specification[boolean defer] returns [String t] : ( NUM | TXT | NUM LEFTSBR RIGHTSBR | TXT LEFTSBR RIGHTSBR | ( BOOL | BOOLEAN ) );
	public final EugeneParser.type_specification_return type_specification(boolean defer) throws RecognitionException {
		EugeneParser.type_specification_return retval = new EugeneParser.type_specification_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NUM343=null;
		Token TXT344=null;
		Token NUM345=null;
		Token LEFTSBR346=null;
		Token RIGHTSBR347=null;
		Token TXT348=null;
		Token LEFTSBR349=null;
		Token RIGHTSBR350=null;
		Token set351=null;

		Object NUM343_tree=null;
		Object TXT344_tree=null;
		Object NUM345_tree=null;
		Object LEFTSBR346_tree=null;
		Object RIGHTSBR347_tree=null;
		Object TXT348_tree=null;
		Object LEFTSBR349_tree=null;
		Object RIGHTSBR350_tree=null;
		Object set351_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3254:2: ( NUM | TXT | NUM LEFTSBR RIGHTSBR | TXT LEFTSBR RIGHTSBR | ( BOOL | BOOLEAN ) )
			int alt108=5;
			switch ( input.LA(1) ) {
			case NUM:
				{
				int LA108_1 = input.LA(2);
				if ( (LA108_1==LEFTSBR) ) {
					alt108=3;
				}
				else if ( (LA108_1==ID) ) {
					alt108=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 108, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case TXT:
				{
				int LA108_2 = input.LA(2);
				if ( (LA108_2==LEFTSBR) ) {
					alt108=4;
				}
				else if ( (LA108_2==ID) ) {
					alt108=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 108, 2, input);
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
				alt108=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 108, 0, input);
				throw nvae;
			}
			switch (alt108) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3254:4: NUM
					{
					root_0 = (Object)adaptor.nil();


					NUM343=(Token)match(input,NUM,FOLLOW_NUM_in_type_specification5853); 
					NUM343_tree = (Object)adaptor.create(NUM343);
					adaptor.addChild(root_0, NUM343_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.NUM;
					}	
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3259:4: TXT
					{
					root_0 = (Object)adaptor.nil();


					TXT344=(Token)match(input,TXT,FOLLOW_TXT_in_type_specification5860); 
					TXT344_tree = (Object)adaptor.create(TXT344);
					adaptor.addChild(root_0, TXT344_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.TXT;
					}	
						
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3264:4: NUM LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					NUM345=(Token)match(input,NUM,FOLLOW_NUM_in_type_specification5867); 
					NUM345_tree = (Object)adaptor.create(NUM345);
					adaptor.addChild(root_0, NUM345_tree);

					LEFTSBR346=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_type_specification5869); 
					LEFTSBR346_tree = (Object)adaptor.create(LEFTSBR346);
					adaptor.addChild(root_0, LEFTSBR346_tree);

					RIGHTSBR347=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_type_specification5871); 
					RIGHTSBR347_tree = (Object)adaptor.create(RIGHTSBR347);
					adaptor.addChild(root_0, RIGHTSBR347_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.NUMLIST;
					}	
						
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3269:4: TXT LEFTSBR RIGHTSBR
					{
					root_0 = (Object)adaptor.nil();


					TXT348=(Token)match(input,TXT,FOLLOW_TXT_in_type_specification5878); 
					TXT348_tree = (Object)adaptor.create(TXT348);
					adaptor.addChild(root_0, TXT348_tree);

					LEFTSBR349=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_type_specification5880); 
					LEFTSBR349_tree = (Object)adaptor.create(LEFTSBR349);
					adaptor.addChild(root_0, LEFTSBR349_tree);

					RIGHTSBR350=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_type_specification5882); 
					RIGHTSBR350_tree = (Object)adaptor.create(RIGHTSBR350);
					adaptor.addChild(root_0, RIGHTSBR350_tree);


					if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
					    retval.t = EugeneConstants.TXTLIST;
					}	
						
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3274:4: ( BOOL | BOOLEAN )
					{
					root_0 = (Object)adaptor.nil();


					set351=input.LT(1);
					if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set351));
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3281:1: list_of_parameters[boolean defer] returns [List<NamedElement> parameters] : pt= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? ;
	public final EugeneParser.list_of_parameters_return list_of_parameters(boolean defer) throws RecognitionException {
		EugeneParser.list_of_parameters_return retval = new EugeneParser.list_of_parameters_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token n=null;
		Token COMMA352=null;
		ParserRuleReturnScope pt =null;
		ParserRuleReturnScope lop =null;

		Object n_tree=null;
		Object COMMA352_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3283:2: (pt= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3283:4: pt= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_type_specification_in_list_of_parameters5915);
			pt=type_specification(defer);
			state._fsp--;

			adaptor.addChild(root_0, pt.getTree());

			n=(Token)match(input,ID,FOLLOW_ID_in_list_of_parameters5920); 
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
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3298:4: ( COMMA lop= list_of_parameters[defer] )?
			int alt109=2;
			int LA109_0 = input.LA(1);
			if ( (LA109_0==COMMA) ) {
				alt109=1;
			}
			switch (alt109) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3298:5: COMMA lop= list_of_parameters[defer]
					{
					COMMA352=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_parameters5925); 
					COMMA352_tree = (Object)adaptor.create(COMMA352);
					adaptor.addChild(root_0, COMMA352_tree);

					pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters5929);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3305:1: list_of_statements[boolean defer] : statement[defer] ( statement[defer] )* ;
	public final EugeneParser.list_of_statements_return list_of_statements(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.list_of_statements_return retval = new EugeneParser.list_of_statements_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope statement353 =null;
		ParserRuleReturnScope statement354 =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3307:2: ( statement[defer] ( statement[defer] )* )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3307:4: statement[defer] ( statement[defer] )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_statement_in_list_of_statements5953);
			statement353=statement(defer);
			state._fsp--;

			adaptor.addChild(root_0, statement353.getTree());

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3307:21: ( statement[defer] )*
			loop110:
			while (true) {
				int alt110=2;
				int LA110_0 = input.LA(1);
				if ( (LA110_0==ARRAY||(LA110_0 >= BOOL && LA110_0 <= COLLECTION)||(LA110_0 >= CREATE_LC && LA110_0 <= CREATE_UC)||LA110_0==DEVICE||(LA110_0 >= EXIT_LC && LA110_0 <= EXIT_UC)||LA110_0==GENBANK||LA110_0==GRAMMAR||(LA110_0 >= HASHMARK && LA110_0 <= ID)||(LA110_0 >= IMPORT_LC && LA110_0 <= LC_AND)||(LA110_0 >= LC_FOR && LA110_0 <= LC_IF)||(LA110_0 >= LC_PERMUTE && LA110_0 <= LC_PRODUCT)||(LA110_0 >= LC_SEQUENCE_OF && LA110_0 <= LC_WHILE)||LA110_0==NUM||(LA110_0 >= PART && LA110_0 <= PART_TYPE)||(LA110_0 >= PRINTLN_LC && LA110_0 <= RANDOM_UC)||(LA110_0 >= REGISTRY && LA110_0 <= RETURN_UC)||(LA110_0 >= RULE && LA110_0 <= SBOL)||(LA110_0 >= SIZEOF_LC && LA110_0 <= STORE_UC)||(LA110_0 >= TXT && LA110_0 <= UC_AND)||(LA110_0 >= UC_FOR && LA110_0 <= UC_IF)||(LA110_0 >= UC_PERMUTE && LA110_0 <= UC_PRODUCT)||(LA110_0 >= UC_SEQUENCE_OF && LA110_0 <= UC_WHILE)) ) {
					alt110=1;
				}

				switch (alt110) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3307:22: statement[defer]
					{
					pushFollow(FOLLOW_statement_in_list_of_statements5957);
					statement354=statement(defer);
					state._fsp--;

					adaptor.addChild(root_0, statement354.getTree());

					}
					break;

				default :
					break loop110;
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3310:1: return_statement[boolean defer] returns [NamedElement el] : ( RETURN_LC | RETURN_UC ) e= expr[defer] ;
	public final EugeneParser.return_statement_return return_statement(boolean defer) throws RecognitionException, EugeneReturnException {
		EugeneParser.return_statement_return retval = new EugeneParser.return_statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set355=null;
		ParserRuleReturnScope e =null;

		Object set355_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3313:2: ( ( RETURN_LC | RETURN_UC ) e= expr[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3313:4: ( RETURN_LC | RETURN_UC ) e= expr[defer]
			{
			root_0 = (Object)adaptor.nil();


			set355=input.LT(1);
			if ( (input.LA(1) >= RETURN_LC && input.LA(1) <= RETURN_UC) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set355));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_expr_in_return_statement5992);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3340:1: function_call[boolean defer] returns [NamedElement e] : udf= call_user_defined_function[defer] ;
	public final EugeneParser.function_call_return function_call(boolean defer) throws RecognitionException {
		EugeneParser.function_call_return retval = new EugeneParser.function_call_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope udf =null;


		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3342:2: (udf= call_user_defined_function[defer] )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3342:4: udf= call_user_defined_function[defer]
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_call_user_defined_function_in_function_call6023);
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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3350:1: call_user_defined_function[boolean defer] returns [NamedElement e] : f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP ;
	public final EugeneParser.call_user_defined_function_return call_user_defined_function(boolean defer) throws RecognitionException {
		EugeneParser.call_user_defined_function_return retval = new EugeneParser.call_user_defined_function_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token f=null;
		Token LEFTP356=null;
		Token RIGHTP357=null;
		ParserRuleReturnScope loe =null;

		Object f_tree=null;
		Object LEFTP356_tree=null;
		Object RIGHTP357_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3352:2: (f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3352:4: f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP
			{
			root_0 = (Object)adaptor.nil();


			f=(Token)match(input,ID,FOLLOW_ID_in_call_user_defined_function6048); 
			f_tree = (Object)adaptor.create(f);
			adaptor.addChild(root_0, f_tree);

			LEFTP356=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_call_user_defined_function6050); 
			LEFTP356_tree = (Object)adaptor.create(LEFTP356);
			adaptor.addChild(root_0, LEFTP356_tree);

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3352:15: (loe= list_of_expressions[defer] )?
			int alt111=2;
			int LA111_0 = input.LA(1);
			if ( (LA111_0==DOLLAR||(LA111_0 >= FALSE_LC && LA111_0 <= FALSE_UC)||LA111_0==ID||(LA111_0 >= LC_PERMUTE && LA111_0 <= LC_PRODUCT)||LA111_0==LC_SEQUENCE_OF||(LA111_0 >= LEFTP && LA111_0 <= LEFTSBR)||LA111_0==MINUS||LA111_0==NUMBER||(LA111_0 >= QUERY_LC && LA111_0 <= RANDOM_UC)||LA111_0==REAL||(LA111_0 >= SIZEOF_LC && LA111_0 <= SIZE_UC)||(LA111_0 >= STRING && LA111_0 <= TRUE_UC)||(LA111_0 >= UC_PERMUTE && LA111_0 <= UC_PRODUCT)||LA111_0==UC_SEQUENCE_OF) ) {
				alt111=1;
			}
			switch (alt111) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3352:16: loe= list_of_expressions[defer]
					{
					pushFollow(FOLLOW_list_of_expressions_in_call_user_defined_function6055);
					loe=list_of_expressions(defer);
					state._fsp--;

					adaptor.addChild(root_0, loe.getTree());

					}
					break;

			}

			RIGHTP357=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_call_user_defined_function6060); 
			RIGHTP357_tree = (Object)adaptor.create(RIGHTP357);
			adaptor.addChild(root_0, RIGHTP357_tree);


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
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3363:1: list_of_expressions[boolean defer] returns [List<NamedElement> parameter_values] : e= expr[defer] ( COMMA loe= list_of_expressions[defer] )? ;
	public final EugeneParser.list_of_expressions_return list_of_expressions(boolean defer) throws RecognitionException {
		EugeneParser.list_of_expressions_return retval = new EugeneParser.list_of_expressions_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA358=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope loe =null;

		Object COMMA358_tree=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3365:2: (e= expr[defer] ( COMMA loe= list_of_expressions[defer] )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3365:4: e= expr[defer] ( COMMA loe= list_of_expressions[defer] )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_expr_in_list_of_expressions6082);
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
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3378:5: ( COMMA loe= list_of_expressions[defer] )?
			int alt112=2;
			int LA112_0 = input.LA(1);
			if ( (LA112_0==COMMA) ) {
				alt112=1;
			}
			switch (alt112) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3378:6: COMMA loe= list_of_expressions[defer]
					{
					COMMA358=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_expressions6089); 
					COMMA358_tree = (Object)adaptor.create(COMMA358);
					adaptor.addChild(root_0, COMMA358_tree);

					pushFollow(FOLLOW_list_of_expressions_in_list_of_expressions6093);
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



	public static final BitSet FOLLOW_statement_in_prog1148 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_function_definition_in_prog1153 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_EOF_in_prog1158 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_includeStatement_in_statement1185 = new BitSet(new long[]{0x0000000000000002L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declarationStatement_in_statement1196 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1199 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_printStatement_in_statement1205 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1208 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement1213 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1216 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dataExchange_in_statement1223 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1226 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_imperativeStatements_in_statement1233 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_call_in_statement1239 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1242 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_built_in_function_in_statement1249 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1252 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stand_alone_function_in_statement1261 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1264 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_return_statement_in_statement1271 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_statement1274 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement1295 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement1303 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement1309 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement1315 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instantiation_in_declarationStatement1321 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement1327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement1333 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement1339 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rulebuilderDeclaration_in_declarationStatement1345 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_grammarDeclaration_in_declarationStatement1351 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_variableDeclaration1369 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_numdecl_in_variableDeclaration1373 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_variableDeclaration1384 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_txtdecl_in_variableDeclaration1388 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_variableDeclaration1399 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1401 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1403 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration1407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_variableDeclaration1418 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1420 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1422 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_variableDeclaration1437 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_booldecl_in_variableDeclaration1445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numdecl1468 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numdecl1474 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_numdecl_in_numdecl1476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numdecl1484 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_numdecl1486 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_numdecl1491 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numdecl1499 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_numdecl_in_numdecl1501 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtdecl1521 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtdecl1528 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_txtdecl_in_txtdecl1530 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtdecl1541 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_txtdecl1543 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_txtdecl1547 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtdecl1555 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_txtdecl_in_txtdecl1557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtlistdecl1577 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtlistdecl1584 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1586 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_txtlistdecl1596 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1598 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_txtlistdecl1604 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_txtlistdecl1612 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numlistdecl1634 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numlistdecl1641 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1643 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_numlistdecl1653 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_numlistdecl1655 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_numlistdecl1660 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_numlistdecl1668 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_booldecl1690 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_booldecl1697 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_booldecl_in_booldecl1699 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_booldecl1709 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_booldecl1711 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_booldecl1715 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1733 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_propertyDeclaration1737 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1739 = new BitSet(new long[]{0x0000000000000600L,0x0020000000000200L});
	public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1743 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1745 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_propertyType1764 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_propertyType1771 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_propertyType1773 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1775 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_propertyType1782 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_propertyType1790 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_propertyType1792 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1794 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_propertyType1801 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1823 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_in_typeDeclaration1830 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_typeDeclaration1835 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1838 = new BitSet(new long[]{0x0000002000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1843 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1848 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_partTypeDeclaration1867 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_partTypeDeclaration1876 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1879 = new BitSet(new long[]{0x0000002000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1884 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1889 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1916 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1923 = new BitSet(new long[]{0x2000002000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1926 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1928 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_containerDeclaration1936 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1941 = new BitSet(new long[]{0x32C0082460240E40L,0x007C7E1A09F03608L,0x0000000000000016L});
	public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1944 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1949 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1982 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_expr_in_list_of_declarations1989 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list_of_declarations1997 = new BitSet(new long[]{0x32C0082460240E40L,0x007C7E1809F03608L,0x0000000000000016L});
	public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations2001 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_instantiation2029 = new BitSet(new long[]{0x0000002000200000L});
	public static final BitSet FOLLOW_dynamic_naming_in_instantiation2035 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_instantiation2042 = new BitSet(new long[]{0x32C0002060600000L,0x001C7E0209E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_listOfDotValues_in_instantiation2047 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_listOfValues_in_instantiation2052 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_instantiation2057 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_listOfDotValues2080 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_listOfDotValues2084 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_listOfDotValues2088 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_listOfDotValues2092 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues2097 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_listOfDotValues2102 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOT_in_listOfDotValues2104 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_listOfDotValues2108 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_listOfDotValues2112 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_listOfDotValues2116 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues2121 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_expr_in_listOfValues2142 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_listOfValues2148 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_listOfValues2154 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration2177 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_deviceDeclaration2181 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration2184 = new BitSet(new long[]{0x2000002000000000L,0x0000000200008008L});
	public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration2189 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration2194 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selection_in_deviceComponents2225 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_deviceComponents2231 = new BitSet(new long[]{0x2000002000000000L,0x0000000000008008L});
	public static final BitSet FOLLOW_deviceComponents_in_deviceComponents2235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_selection2264 = new BitSet(new long[]{0x0000002000000000L,0x0000000000008008L});
	public static final BitSet FOLLOW_selection_list_in_selection2268 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_selection2271 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_device_component_in_selection2280 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_device_component_in_selection_list2309 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
	public static final BitSet FOLLOW_PIPE_in_selection_list2315 = new BitSet(new long[]{0x0000002000000000L,0x0000000000008008L});
	public static final BitSet FOLLOW_selection_list_in_selection_list2319 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_device_component2345 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_device_component2355 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_lhs_assignment_in_assignment2375 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_assignment2378 = new BitSet(new long[]{0x32C001A160200020L,0x001C7E8029E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_AMP_in_assignment2383 = new BitSet(new long[]{0x32C001A160200000L,0x001C7E8029E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_rhs_assignment_in_assignment2389 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_lhs_assignment2404 = new BitSet(new long[]{0x2000000000400000L});
	public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2406 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_lhs_access2426 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_lhs_access2430 = new BitSet(new long[]{0x2000000000400000L});
	public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2434 = new BitSet(new long[]{0x0000002000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_set_in_lhs_access2436 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2442 = new BitSet(new long[]{0x2000000000400000L});
	public static final BitSet FOLLOW_lhs_access_in_lhs_access2445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2472 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_rhs_assignment2482 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_listOfIDs2510 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_listOfIDs2519 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2523 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RULE_BUILDER_in_rulebuilderDeclaration2548 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_rulebuilderDeclaration2552 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_rulebuilderDeclaration2555 = new BitSet(new long[]{0x0010000000000000L,0x8000000200000000L});
	public static final BitSet FOLLOW_set_in_rulebuilderDeclaration2558 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_rulebuilderDeclaration2566 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_rulebuilderDeclaration2570 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RULE_in_ruleDeclaration2597 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_ruleDeclaration2601 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2603 = new BitSet(new long[]{0x311C002000200000L,0xE004000008000C08L,0xFFFF7FFFFFFFF008L,0x0000000003BFFFFFL});
	public static final BitSet FOLLOW_set_in_ruleDeclaration2608 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_ruleDeclaration2616 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_COLON_in_ruleDeclaration2618 = new BitSet(new long[]{0x310C002000200000L,0x6004000008000C08L,0xFFFF7FFFFFFFF008L,0x0000000003BFFFFFL});
	public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2626 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2631 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2645 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EQUALS_in_relationalOperators3024 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_relationalOperators3026 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEQUAL_in_relationalOperators3031 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LTHAN_in_relationalOperators3036 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GTHAN_in_relationalOperators3041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEQUAL_in_relationalOperators3046 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GEQUAL_in_relationalOperators3051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3056 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3065 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3074 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3083 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3092 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_relationalOperators3128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_predicate_in_cnf_rule3152 = new BitSet(new long[]{0x0000100000000002L,0x0080000000000001L});
	public static final BitSet FOLLOW_set_in_cnf_rule3160 = new BitSet(new long[]{0x310C002000200000L,0x6004000008000C08L,0xFFFF7FFFFFFFF008L,0x0000000003BFFFFFL});
	public static final BitSet FOLLOW_cnf_rule_in_cnf_rule3170 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_negated_predicate_in_or_predicate3200 = new BitSet(new long[]{0x0020000000000002L,0x0000000000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_set_in_or_predicate3206 = new BitSet(new long[]{0x310C002000200000L,0x6004000008000C08L,0xFFFF7FFFFFFFF008L,0x0000000003BFFFFFL});
	public static final BitSet FOLLOW_negated_predicate_in_or_predicate3216 = new BitSet(new long[]{0x0020000000000002L,0x0000000000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_set_in_negated_predicate3244 = new BitSet(new long[]{0x3104002000200000L,0x2004000008000408L,0xFFFF7FFFFFFFF008L,0x0000000003BFFFFFL});
	public static final BitSet FOLLOW_predicate_in_negated_predicate3254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_predicate_in_negated_predicate3264 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_predicate3291 = new BitSet(new long[]{0x0104000000000000L,0x2000000000000000L,0xFFFF7FFFFFFFF008L,0x0000000003BFFFFFL});
	public static final BitSet FOLLOW_ruleOperator_in_predicate3301 = new BitSet(new long[]{0x2000002000200002L,0x0000000000000400L});
	public static final BitSet FOLLOW_operand_in_predicate3310 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_predicate3324 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expressionRule_in_predicate3333 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_operand3364 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_operand3373 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_operand3380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_NUMBER_in_operand3384 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_operand3386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dynamic_naming_in_operand3393 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_expressionRule3420 = new BitSet(new long[]{0x4000000A01000000L,0x0000000000000044L,0xA001800593400000L,0x0000000000C002C9L});
	public static final BitSet FOLLOW_exp_op_in_expressionRule3425 = new BitSet(new long[]{0x1000002000000000L,0x0004000008000408L});
	public static final BitSet FOLLOW_expression_in_expressionRule3430 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp_operand_in_expression3454 = new BitSet(new long[]{0x0000000000100002L,0x0000000000008028L});
	public static final BitSet FOLLOW_exp_operator_in_expression3463 = new BitSet(new long[]{0x1000002000000000L,0x0004000008000408L});
	public static final BitSet FOLLOW_expression_in_expression3468 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_expression3480 = new BitSet(new long[]{0x1000002000000000L,0x0004000008000408L});
	public static final BitSet FOLLOW_expression_in_expression3482 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_expression3485 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUS_in_exp_operator3504 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_exp_operator3512 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MULT_in_exp_operator3519 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DIV_in_exp_operator3526 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_exp_operand3556 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOT_in_exp_operand3558 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_exp_operand3568 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_NUMBER_in_exp_operand3579 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3581 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_exp_operand3593 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_exp_operand3600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_NUMBER_in_exp_operand3604 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REAL_in_exp_operand3613 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_exp_operand3620 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_REAL_in_exp_operand3624 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_exp_operand3633 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_relationalOperators_in_exp_op3660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GRAMMAR_in_grammarDeclaration3679 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_grammarDeclaration3683 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_grammarDeclaration3685 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_list_of_production_rules_in_grammarDeclaration3687 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_grammarDeclaration3690 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_production_rule_in_list_of_production_rules3702 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_list_of_production_rules3705 = new BitSet(new long[]{0x0000002000000002L});
	public static final BitSet FOLLOW_list_of_production_rules_in_list_of_production_rules3708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_production_rule3728 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_ARROW_in_production_rule3732 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_right_hand_side_in_production_rule3734 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_right_hand_side3750 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_right_hand_side3755 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_right_hand_side_in_right_hand_side3757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_interaction_in_right_hand_side3765 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_interaction_in_interactionDeclaration3790 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3798 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_interactionDeclaration3802 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3804 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_interaction_in_interactionDeclaration3808 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3811 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_interaction3834 = new BitSet(new long[]{0x0104000000000000L,0x2000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_interactionType_in_interaction3838 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_interaction3843 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_interaction3852 = new BitSet(new long[]{0x0104000000000000L,0x2000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_interactionType_in_interaction3856 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_interaction3859 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_interaction_in_interaction3863 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_interaction3866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_interactionType3886 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_interactionType3899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_printStatement3925 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_printStatement3931 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_toPrint_in_printStatement3935 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_printStatement3938 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_printStatement3945 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_printStatement3951 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_toPrint_in_printStatement3955 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_printStatement3958 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_toPrint3979 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_toPrint_prime_in_toPrint3984 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_toPrint_prime4010 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_toPrint_in_toPrint_prime4014 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_elseif_else_in_imperativeStatements4039 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements4045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_for_loop_in_imperativeStatements4051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_while_loop_in_imperativeStatements4057 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_if_elseif_else4089 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_if_elseif_else4095 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_logical_condition_in_if_elseif_else4099 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_if_elseif_else4102 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_if_elseif_else4104 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_list_of_statements_in_if_elseif_else4112 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_if_elseif_else4115 = new BitSet(new long[]{0x0000600000000002L,0x0300000000000000L});
	public static final BitSet FOLLOW_set_in_if_elseif_else4130 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_if_elseif_else4136 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_logical_condition_in_if_elseif_else4140 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_if_elseif_else4143 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_if_elseif_else4145 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_list_of_statements_in_if_elseif_else4153 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_if_elseif_else4156 = new BitSet(new long[]{0x0000600000000002L,0x0300000000000000L});
	public static final BitSet FOLLOW_set_in_if_elseif_else4172 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_if_elseif_else4178 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_list_of_statements_in_if_elseif_else4186 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_if_elseif_else4189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_forall_iterator4211 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_forall_iterator4220 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_COLON_in_forall_iterator4222 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_forall_iterator4228 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator4230 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_list_of_statements_in_forall_iterator4237 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator4244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_for_loop4261 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_for_loop4267 = new BitSet(new long[]{0x0000000000000600L,0x0020000000000200L});
	public static final BitSet FOLLOW_variableDeclaration_in_for_loop4271 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_for_loop4274 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_logical_condition_in_for_loop4278 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_SEMIC_in_for_loop4281 = new BitSet(new long[]{0x0000002000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_assignment_in_for_loop4286 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_for_loop4291 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_for_loop4293 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_list_of_statements_in_for_loop4301 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_for_loop4308 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_while_loop4327 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_while_loop4333 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_logical_condition_in_while_loop4337 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_while_loop4340 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_while_loop4342 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_list_of_statements_in_while_loop4350 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_while_loop4357 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_or_condition_in_logical_condition4383 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_or_condition_in_logical_not_condition4408 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logical_and_condition_in_logical_or_condition4433 = new BitSet(new long[]{0x0020000000000002L,0x0000000000004002L,0x0000000000000001L});
	public static final BitSet FOLLOW_LC_OR_in_logical_or_condition4440 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_UC_OR_in_logical_or_condition4442 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_LOG_OR_in_logical_or_condition4444 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_PIPE_in_logical_or_condition4446 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E04C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_PIPE_in_logical_or_condition4449 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_logical_or_condition_in_logical_or_condition4456 = new BitSet(new long[]{0x0020000000000002L,0x0000000000004002L,0x0000000000000001L});
	public static final BitSet FOLLOW_atomic_condition_in_logical_and_condition4481 = new BitSet(new long[]{0x0000100000000022L,0x0080000000000001L});
	public static final BitSet FOLLOW_LC_AND_in_logical_and_condition4488 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_UC_AND_in_logical_and_condition4490 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_LOG_AND_in_logical_and_condition4492 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_AMP_in_logical_and_condition4494 = new BitSet(new long[]{0x32C8002060200020L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_AMP_in_logical_and_condition4497 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_logical_and_condition_in_logical_and_condition4504 = new BitSet(new long[]{0x0000100000000022L,0x0080000000000001L});
	public static final BitSet FOLLOW_expr_in_atomic_condition4532 = new BitSet(new long[]{0x4000000A01000000L,0x0000000000000044L,0xA001800593400000L,0x0000000000C002C9L});
	public static final BitSet FOLLOW_relationalOperators_in_atomic_condition4537 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_atomic_condition4541 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_atomic_condition4549 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_atomic_condition4557 = new BitSet(new long[]{0x32C8002060200000L,0x401C7E0009E00C08L,0x0000000000000016L});
	public static final BitSet FOLLOW_atomic_condition_in_atomic_condition4561 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_atomic_condition4564 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expr4591 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008008L});
	public static final BitSet FOLLOW_set_in_expr4600 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_multExpr_in_expr4608 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008008L});
	public static final BitSet FOLLOW_atom_in_multExpr4638 = new BitSet(new long[]{0x0000000000100002L,0x0000000000000020L});
	public static final BitSet FOLLOW_set_in_multExpr4648 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_atom_in_multExpr4656 = new BitSet(new long[]{0x0000000000100002L,0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_atom4683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REAL_in_atom4689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_atom4699 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000400L});
	public static final BitSet FOLLOW_NUMBER_in_atom4704 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REAL_in_atom4710 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_atom4723 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_atom4733 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dynamic_naming_in_atom4749 = new BitSet(new long[]{0x2000000000400000L});
	public static final BitSet FOLLOW_object_access_in_atom4756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_atom4765 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_atom4773 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_atom4775 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_atom4778 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_atom4787 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_list_in_atom4789 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_atom4792 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_built_in_function_in_atom4802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_call_in_atom4812 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_list4835 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list4842 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_list4846 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_set_in_built_in_function4874 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4888 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_built_in_function4892 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4895 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4902 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4908 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_built_in_function4912 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4915 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4923 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4929 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_range_in_built_in_function4933 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4936 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4947 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4953 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_built_in_function4957 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4959 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4971 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function4977 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_built_in_function4981 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function4983 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_built_in_function4994 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_built_in_function5000 = new BitSet(new long[]{0x1000002000000000L,0x0004000008000408L});
	public static final BitSet FOLLOW_expressionRule_in_built_in_function5004 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_built_in_function5007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_stand_alone_function5024 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_stand_alone_function5038 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_stand_alone_function5042 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_stand_alone_function5045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_stand_alone_function5052 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_stand_alone_function5058 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_stand_alone_function5062 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_stand_alone_function5064 = new BitSet(new long[]{0x310C002000200000L,0x6004000008000C08L,0xFFFF7FFFFFFFF008L,0x0000000003BFFFFFL});
	public static final BitSet FOLLOW_or_predicate_in_stand_alone_function5068 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_stand_alone_function5071 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_stand_alone_function5078 = new BitSet(new long[]{0x1000000000000002L});
	public static final BitSet FOLLOW_LEFTP_in_stand_alone_function5087 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_toPrint_in_stand_alone_function5091 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_stand_alone_function5094 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_range5118 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_range5121 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_range5125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_object_access5161 = new BitSet(new long[]{0x0000002000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_ID_in_object_access5166 = new BitSet(new long[]{0x2000000000400000L});
	public static final BitSet FOLLOW_set_in_object_access5172 = new BitSet(new long[]{0x3000000000400000L});
	public static final BitSet FOLLOW_LEFTP_in_object_access5179 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_object_access5181 = new BitSet(new long[]{0x2000000000400000L});
	public static final BitSet FOLLOW_LEFTSBR_in_object_access5191 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_object_access5196 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_object_access5200 = new BitSet(new long[]{0x2000000000400000L});
	public static final BitSet FOLLOW_object_access_in_object_access5207 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_dynamic_naming5232 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOLLAR_in_dynamic_naming5239 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_dynamic_naming5241 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_dynamic_naming5245 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_dynamic_naming5248 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sbolStatement_in_dataExchange5273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_importStatement_in_dataExchange5283 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_genbankStatement_in_dataExchange5293 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_registryStatement_in_dataExchange5303 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HASHMARK_in_includeStatement5322 = new BitSet(new long[]{0x0000060000000000L});
	public static final BitSet FOLLOW_set_in_includeStatement5326 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_STRING_in_includeStatement5334 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_importStatement5355 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_importStatement5361 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_STRING_in_importStatement5365 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_importStatement5369 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SBOL_in_sbolStatement5391 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOT_in_sbolStatement5393 = new BitSet(new long[]{0x0000018018000000L,0x0000000000000000L,0x0000000000000600L});
	public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement5396 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement5403 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sbolVisualStatement_in_sbolStatement5411 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_sbolExportStatement5428 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement5434 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_sbolExportStatement5438 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_sbolExportStatement5440 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_STRING_in_sbolExportStatement5444 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement5446 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_sbolImportStatement5469 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement5475 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_STRING_in_sbolImportStatement5479 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement5481 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_sbolVisualStatement5497 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_sbolVisualStatement5503 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_sbolVisualStatement5507 = new BitSet(new long[]{0x0000000000002000L,0x0000000200000000L});
	public static final BitSet FOLLOW_COMMA_in_sbolVisualStatement5511 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_sbolVisualStatement5515 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_sbolVisualStatement5520 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GENBANK_in_genbankStatement5545 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOT_in_genbankStatement5547 = new BitSet(new long[]{0x0000018018000000L});
	public static final BitSet FOLLOW_genbankImportStatement_in_genbankStatement5552 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_genbankExportStatement_in_genbankStatement5560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_genbankExportStatement5579 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_genbankExportStatement5585 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_genbankExportStatement5587 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_genbankImportStatement5613 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_genbankImportStatement5619 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_STRING_in_genbankImportStatement5623 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_genbankImportStatement5625 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_genbankImportStatement5632 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_genbankImportStatement5638 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_genbankImportStatement5642 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_genbankImportStatement5644 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_STRING_in_genbankImportStatement5648 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_genbankImportStatement5650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTRY_in_registryStatement5674 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOT_in_registryStatement5676 = new BitSet(new long[]{0x0000018000000000L});
	public static final BitSet FOLLOW_set_in_registryStatement5678 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_registryStatement5684 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_STRING_in_registryStatement5688 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_registryStatement5690 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASSERT_in_testStatements5709 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5711 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_testStatements5715 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOT_in_testStatements5717 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_set_in_testStatements5719 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5725 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5727 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5729 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_NUMBER_in_testStatements5735 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOTE_in_testStatements5745 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5747 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_testStatements5751 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOT_in_testStatements5753 = new BitSet(new long[]{0x0000000000000000L,0x0000480000000000L});
	public static final BitSet FOLLOW_set_in_testStatements5755 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_testStatements5761 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5763 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5765 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_EQUALS_in_testStatements5767 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_NUMBER_in_testStatements5771 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_testStatements5773 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_specification_in_function_definition5798 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_function_definition5805 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_function_definition5807 = new BitSet(new long[]{0x0000000000000600L,0x0020000200000200L});
	public static final BitSet FOLLOW_list_of_parameters_in_function_definition5812 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_function_definition5817 = new BitSet(new long[]{0x0800000000000000L});
	public static final BitSet FOLLOW_LEFTCUR_in_function_definition5819 = new BitSet(new long[]{0x06C39FB50604CE40L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_list_of_statements_in_function_definition5827 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RIGHTCUR_in_function_definition5833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_type_specification5853 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_type_specification5860 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUM_in_type_specification5867 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_type_specification5869 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_type_specification5871 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TXT_in_type_specification5878 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_LEFTSBR_in_type_specification5880 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_type_specification5882 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_type_specification5889 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_specification_in_list_of_parameters5915 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_ID_in_list_of_parameters5920 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list_of_parameters5925 = new BitSet(new long[]{0x0000000000000600L,0x0020000000000200L});
	public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters5929 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_list_of_statements5953 = new BitSet(new long[]{0x06C39FB50604CE42L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_statement_in_list_of_statements5957 = new BitSet(new long[]{0x06C39FB50604CE42L,0x1CE1FEF8E1FF3200L,0x0000000000000036L});
	public static final BitSet FOLLOW_set_in_return_statement5982 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_expr_in_return_statement5992 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_call_user_defined_function_in_function_call6023 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_call_user_defined_function6048 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_LEFTP_in_call_user_defined_function6050 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0209E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_list_of_expressions_in_call_user_defined_function6055 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RIGHTP_in_call_user_defined_function6060 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_list_of_expressions6082 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_list_of_expressions6089 = new BitSet(new long[]{0x32C0002060200000L,0x001C7E0009E00408L,0x0000000000000016L});
	public static final BitSet FOLLOW_list_of_expressions_in_list_of_expressions6093 = new BitSet(new long[]{0x0000000000000002L});
}
