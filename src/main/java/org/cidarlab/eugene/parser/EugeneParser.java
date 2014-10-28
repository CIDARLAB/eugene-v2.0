// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g 2014-10-28 08:03:40

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
import org.cidarlab.eugene.dom.imp.functions.Function;
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


@SuppressWarnings({"all", "warnings", "unchecked"})
public class EugeneParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADDPROPS", "AMP", "ARRAY", "ARROW", "ASSERT", "BOOL", "BOOLEAN", "COLLECTION", "COLON", "COMMA", "DEVICE", "DIGIT", "DIV", "DOLLAR", "DOT", "DOTDOT", "EQUALS", "EXIT_LC", "EXIT_UC", "EXPORT_LC", "EXPORT_UC", "FALSE_LC", "FALSE_UC", "FLEXIBLE", "GENBANK", "GEQUAL", "GRAMMAR", "GTHAN", "HASHMARK", "ID", "IMAGE", "IMPORT_LC", "IMPORT_UC", "INCLUDE_LC", "INCLUDE_UC", "INTERACTION", "LC_AND", "LC_ELSE", "LC_ELSEIF", "LC_FOR", "LC_FORALL", "LC_IF", "LC_INDUCES", "LC_NOT", "LC_ON", "LC_OR", "LC_REPRESSES", "LC_WHILE", "LEFTCUR", "LEFTP", "LEFTSBR", "LEQUAL", "LINE_COMMENT", "LOG_AND", "LOG_NOT", "LOG_OR", "LTHAN", "MINUS", "ML_COMMENT", "MULT", "NEQUAL", "NEWLINE", "NOTE", "NUM", "NUMBER", "PART", "PART_TYPE", "PERMUTE", "PIGEON_LC", "PIGEON_UC", "PIPE", "PLUS", "PRINTLN_LC", "PRINTLN_UC", "PRINT_LC", "PRINT_UC", "PRODUCT", "PROPERTY", "RANDOM_LC", "RANDOM_UC", "REAL", "REF", "REGISTRY", "RETURN_LC", "RETURN_UC", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "RULE", "SAVE_LC", "SAVE_UC", "SBOL", "SEMIC", "SIZEOF_LC", "SIZEOF_UC", "SIZE_LC", "SIZE_UC", "STORE_LC", "STORE_UC", "STRICT", "STRING", "TRUE_LC", "TRUE_UC", "TXT", "TYPE", "UC_AND", "UC_ELSE", "UC_ELSEIF", "UC_FOR", "UC_FORALL", "UC_IF", "UC_INDUCES", "UC_NOT", "UC_ON", "UC_OR", "UC_REPRESSES", "UC_WHILE", "UNDERS", "WS", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", "'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'MATCHES'", "'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", "'SOUNDSLIKE'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'reverse'", "'same_count'", "'same_orientation'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", "'soundslike'", "'startswith'", "'then'", "'with'"
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
    public static final int LOG_NOT=58;
    public static final int LOG_OR=59;
    public static final int LTHAN=60;
    public static final int MINUS=61;
    public static final int ML_COMMENT=62;
    public static final int MULT=63;
    public static final int NEQUAL=64;
    public static final int NEWLINE=65;
    public static final int NOTE=66;
    public static final int NUM=67;
    public static final int NUMBER=68;
    public static final int PART=69;
    public static final int PART_TYPE=70;
    public static final int PERMUTE=71;
    public static final int PIGEON_LC=72;
    public static final int PIGEON_UC=73;
    public static final int PIPE=74;
    public static final int PLUS=75;
    public static final int PRINTLN_LC=76;
    public static final int PRINTLN_UC=77;
    public static final int PRINT_LC=78;
    public static final int PRINT_UC=79;
    public static final int PRODUCT=80;
    public static final int PROPERTY=81;
    public static final int RANDOM_LC=82;
    public static final int RANDOM_UC=83;
    public static final int REAL=84;
    public static final int REF=85;
    public static final int REGISTRY=86;
    public static final int RETURN_LC=87;
    public static final int RETURN_UC=88;
    public static final int RIGHTCUR=89;
    public static final int RIGHTP=90;
    public static final int RIGHTSBR=91;
    public static final int RULE=92;
    public static final int SAVE_LC=93;
    public static final int SAVE_UC=94;
    public static final int SBOL=95;
    public static final int SEMIC=96;
    public static final int SIZEOF_LC=97;
    public static final int SIZEOF_UC=98;
    public static final int SIZE_LC=99;
    public static final int SIZE_UC=100;
    public static final int STORE_LC=101;
    public static final int STORE_UC=102;
    public static final int STRICT=103;
    public static final int STRING=104;
    public static final int TRUE_LC=105;
    public static final int TRUE_UC=106;
    public static final int TXT=107;
    public static final int TYPE=108;
    public static final int UC_AND=109;
    public static final int UC_ELSE=110;
    public static final int UC_ELSEIF=111;
    public static final int UC_FOR=112;
    public static final int UC_FORALL=113;
    public static final int UC_IF=114;
    public static final int UC_INDUCES=115;
    public static final int UC_NOT=116;
    public static final int UC_ON=117;
    public static final int UC_OR=118;
    public static final int UC_REPRESSES=119;
    public static final int UC_WHILE=120;
    public static final int UNDERS=121;
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
    public String[] getTokenNames() { return EugeneParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g"; }


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
            } else if("imp_condition".equals(rule)) {
                rv = this.imp_condition(false);
            } else if("listOfStatements".equals(rule)) {
                rv = this.listOfStatements(false);
            } else if("function_statements".equals(rule)) {
                rv = this.function_statements(false);
            } else if("assignment".equals(rule)) {
                rv = this.assignment(false);
            } else if("listOfStatements".equals(rule)) {
                rv = this.listOfStatements(false);
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
            imp_condition_return ret = 
                   (imp_condition_return)this.exec(
                                               "imp_condition",         
                                               condStart.getTokenIndex());
            /*
             * while the condition is satisfied
             */
            while(ret.bTrue) {

                /*
                 * push the ForLoop object onto the stack
                 * (for scoping)
                 */
                this.interp.push(l);
                    
                /*
                 *   execute the statements
                 */
                this.exec(
                    "listOfStatements", 
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
                ret = (imp_condition_return)this.exec(
                                       "imp_condition",         
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
        
        public NamedElement call_function(String name, List<NamedElement> parameter_values)
            throws EugeneException {
        
            // first, we ask the interpreter if the function exists
            // and let it return the function object
            Function f = this.interp.getFunction(name);
            if(null == f) {
                printError("The function " + name + " is not defined!");
            }
            
            // then, we compare the types of the function parameters 
            // with the types of the parameter values
            try {
                this.interp.compareParameterTypes(
                        f.getParameters(), 
                        parameter_values);
            } catch(EugeneException ee) {
                printError(ee.getLocalizedMessage());
            }
            

            // if everything's fine at this point in time, then we 
            // execute the function.
                
            // first, we initialize its parameters with the specified 
            // parameter values (if there are any)
            if(null != parameter_values) {
    	    try {
    	        f.initialize(parameter_values);
    	    } catch(EugeneException ee) {
    	        throw new EugeneException(ee.getLocalizedMessage());
    	    }
            }

            // we store the current position in the Eugene script
            // for the jump back
            
            NamedElement ret_el = null;
            
            int oldPosition = this.input.index();

            // we need to do this, since function definitions 
            // can be ahead of the current position
            String s = this.input.toString();

            this.interp.push(f);
            
            try {
            	this.input.seek(f.getStatements().getTokenIndex());
                this.function_statements(false);
            } catch(RecognitionException re) {
                printError(re.getLocalizedMessage());	    
            } catch(EugeneReturnException ere) {

                // a return statement has been encountered
                ret_el = ere.getReturnValue();

            }
            
            // we need to pop the function from the stack!
            this.interp.pop();

            // and jump back to the original position in 
            // the Eugene script
            if(oldPosition != -1) {
                this.input.seek(oldPosition);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "prog"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:786:1: prog[boolean defer] : ( statement[defer] | function_definition[true] )+ EOF !;
    public final EugeneParser.prog_return prog(boolean defer) throws RecognitionException, EugeneReturnException {
        EugeneParser.prog_return retval = new EugeneParser.prog_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF3=null;
        EugeneParser.statement_return statement1 =null;

        EugeneParser.function_definition_return function_definition2 =null;


        Object EOF3_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:793:2: ( ( statement[defer] | function_definition[true] )+ EOF !)
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:793:4: ( statement[defer] | function_definition[true] )+ EOF !
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:793:4: ( statement[defer] | function_definition[true] )+
            int cnt1=0;
            loop1:
            do {
                int alt1=3;
                switch ( input.LA(1) ) {
                case ARRAY:
                case BOOL:
                case BOOLEAN:
                case COLLECTION:
                case DEVICE:
                case EXIT_LC:
                case EXIT_UC:
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
                case PIGEON_LC:
                case PIGEON_UC:
                case PRINTLN_LC:
                case PRINTLN_UC:
                case PRINT_LC:
                case PRINT_UC:
                case PRODUCT:
                case PROPERTY:
                case RANDOM_LC:
                case RANDOM_UC:
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
                        alt1=1;
                    }
                    else if ( (LA1_3==ID) ) {
                        int LA1_6 = input.LA(3);

                        if ( (LA1_6==COMMA||LA1_6==EQUALS||LA1_6==SEMIC) ) {
                            alt1=1;
                        }
                        else if ( (LA1_6==LEFTP) ) {
                            alt1=2;
                        }


                    }


                    }
                    break;
                case TXT:
                    {
                    int LA1_4 = input.LA(2);

                    if ( (LA1_4==LEFTSBR) ) {
                        alt1=1;
                    }
                    else if ( (LA1_4==ID) ) {
                        int LA1_7 = input.LA(3);

                        if ( (LA1_7==COMMA||LA1_7==EQUALS||LA1_7==SEMIC) ) {
                            alt1=1;
                        }
                        else if ( (LA1_7==LEFTP) ) {
                            alt1=2;
                        }


                    }


                    }
                    break;
                case ID:
                    {
                    int LA1_5 = input.LA(2);

                    if ( (LA1_5==LEFTP) ) {
                        switch ( input.LA(3) ) {
                        case RIGHTP:
                            {
                            int LA1_10 = input.LA(4);

                            if ( (LA1_10==LEFTCUR) ) {
                                alt1=2;
                            }
                            else if ( (LA1_10==SEMIC) ) {
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
                        case NUM:
                        case TXT:
                            {
                            alt1=2;
                            }
                            break;

                        }

                    }
                    else if ( ((LA1_5 >= DOLLAR && LA1_5 <= DOT)||LA1_5==EQUALS||LA1_5==ID||LA1_5==LC_INDUCES||LA1_5==LC_REPRESSES||LA1_5==LEFTSBR||LA1_5==UC_INDUCES||LA1_5==UC_REPRESSES) ) {
                        alt1=1;
                    }


                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:793:5: statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_prog997);
            	    statement1=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement1.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:793:24: function_definition[true]
            	    {
            	    pushFollow(FOLLOW_function_definition_in_prog1002);
            	    function_definition2=function_definition(true);

            	    state._fsp--;

            	    adaptor.addChild(root_0, function_definition2.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_prog1007); 

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:797:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | function_call[defer] SEMIC | built_in_function[defer] SEMIC );
    public final EugeneParser.statement_return statement(boolean defer) throws RecognitionException {
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
        EugeneParser.dataExchange_return de =null;

        EugeneParser.includeStatement_return includeStatement4 =null;

        EugeneParser.declarationStatement_return declarationStatement6 =null;

        EugeneParser.printStatement_return printStatement8 =null;

        EugeneParser.assignment_return assignment10 =null;

        EugeneParser.imperativeStatements_return imperativeStatements13 =null;

        EugeneParser.function_call_return function_call14 =null;

        EugeneParser.built_in_function_return built_in_function16 =null;


        Object SEMIC5_tree=null;
        Object SEMIC7_tree=null;
        Object SEMIC9_tree=null;
        Object SEMIC11_tree=null;
        Object SEMIC12_tree=null;
        Object SEMIC15_tree=null;
        Object SEMIC17_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:799:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | function_call[defer] SEMIC | built_in_function[defer] SEMIC )
            int alt3=8;
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
                    int LA3_8 = input.LA(3);

                    if ( (LA3_8==RIGHTP) ) {
                        int LA3_10 = input.LA(4);

                        if ( (LA3_10==SEMIC) ) {
                            alt3=7;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 10, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA3_8==DOLLAR||(LA3_8 >= EXIT_LC && LA3_8 <= EXIT_UC)||(LA3_8 >= FALSE_LC && LA3_8 <= FALSE_UC)||LA3_8==ID||(LA3_8 >= LEFTP && LA3_8 <= LEFTSBR)||LA3_8==MINUS||LA3_8==NUMBER||LA3_8==PERMUTE||LA3_8==PRODUCT||(LA3_8 >= RANDOM_LC && LA3_8 <= REAL)||(LA3_8 >= SAVE_LC && LA3_8 <= SAVE_UC)||(LA3_8 >= SIZEOF_LC && LA3_8 <= STORE_UC)||(LA3_8 >= STRING && LA3_8 <= TRUE_UC)) ) {
                        alt3=7;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 8, input);

                        throw nvae;

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
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;

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
            case IMPORT_LC:
            case IMPORT_UC:
            case PIGEON_LC:
            case PIGEON_UC:
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:800:3: includeStatement[defer] ( SEMIC )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_includeStatement_in_statement1033);
                    includeStatement4=includeStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, includeStatement4.getTree());

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:800:27: ( SEMIC )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==SEMIC) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:800:28: SEMIC
                            {
                            SEMIC5=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1037); 
                            SEMIC5_tree = 
                            (Object)adaptor.create(SEMIC5)
                            ;
                            adaptor.addChild(root_0, SEMIC5_tree);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:801:4: declarationStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declarationStatement_in_statement1044);
                    declarationStatement6=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, declarationStatement6.getTree());

                    SEMIC7=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1047); 
                    SEMIC7_tree = 
                    (Object)adaptor.create(SEMIC7)
                    ;
                    adaptor.addChild(root_0, SEMIC7_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:802:4: printStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_printStatement_in_statement1053);
                    printStatement8=printStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, printStatement8.getTree());

                    SEMIC9=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1056); 
                    SEMIC9_tree = 
                    (Object)adaptor.create(SEMIC9)
                    ;
                    adaptor.addChild(root_0, SEMIC9_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:803:4: assignment[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_statement1061);
                    assignment10=assignment(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, assignment10.getTree());

                    SEMIC11=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1064); 
                    SEMIC11_tree = 
                    (Object)adaptor.create(SEMIC11)
                    ;
                    adaptor.addChild(root_0, SEMIC11_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:804:4: de= dataExchange[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_statement1071);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());

                    SEMIC12=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1074); 
                    SEMIC12_tree = 
                    (Object)adaptor.create(SEMIC12)
                    ;
                    adaptor.addChild(root_0, SEMIC12_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            // iff there's no assignment to a LHS element,
                            // then we store the imported data into the 
                            // current scope's symbol tables
                            if(null != (de!=null?de.e:null)) {
                                this.interp.dataExchange((de!=null?de.e:null));
                            }
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }	
                    	

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:818:4: imperativeStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_imperativeStatements_in_statement1081);
                    imperativeStatements13=imperativeStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, imperativeStatements13.getTree());

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:819:4: function_call[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_function_call_in_statement1087);
                    function_call14=function_call(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, function_call14.getTree());

                    SEMIC15=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1090); 
                    SEMIC15_tree = 
                    (Object)adaptor.create(SEMIC15)
                    ;
                    adaptor.addChild(root_0, SEMIC15_tree);


                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:820:4: built_in_function[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_built_in_function_in_statement1095);
                    built_in_function16=built_in_function(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, built_in_function16.getTree());

                    SEMIC17=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1098); 
                    SEMIC17_tree = 
                    (Object)adaptor.create(SEMIC17)
                    ;
                    adaptor.addChild(root_0, SEMIC17_tree);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declarationStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:829:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] );
    public final EugeneParser.declarationStatement_return declarationStatement(boolean defer) throws RecognitionException {
        EugeneParser.declarationStatement_return retval = new EugeneParser.declarationStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.variableDeclaration_return v =null;

        EugeneParser.containerDeclaration_return containerDeclaration18 =null;

        EugeneParser.propertyDeclaration_return propertyDeclaration19 =null;

        EugeneParser.typeDeclaration_return typeDeclaration20 =null;

        EugeneParser.instantiation_return instantiation21 =null;

        EugeneParser.interactionDeclaration_return interactionDeclaration22 =null;

        EugeneParser.ruleDeclaration_return ruleDeclaration23 =null;

        EugeneParser.grammarDeclaration_return grammarDeclaration24 =null;

        EugeneParser.deviceDeclaration_return deviceDeclaration25 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:831:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 5, input);

                    throw nvae;

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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:831:4: v= variableDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_variableDeclaration_in_declarationStatement1119);
                    v=variableDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, v.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.name = (v!=null?v.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:836:4: containerDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_containerDeclaration_in_declarationStatement1127);
                    containerDeclaration18=containerDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, containerDeclaration18.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:837:4: propertyDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement1133);
                    propertyDeclaration19=propertyDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, propertyDeclaration19.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:838:4: typeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeDeclaration_in_declarationStatement1139);
                    typeDeclaration20=typeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, typeDeclaration20.getTree());

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:839:4: instantiation[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_instantiation_in_declarationStatement1145);
                    instantiation21=instantiation(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, instantiation21.getTree());

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:840:4: interactionDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement1151);
                    interactionDeclaration22=interactionDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, interactionDeclaration22.getTree());

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:841:4: ruleDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement1157);
                    ruleDeclaration23=ruleDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ruleDeclaration23.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:842:4: grammarDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_grammarDeclaration_in_declarationStatement1163);
                    grammarDeclaration24=grammarDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, grammarDeclaration24.getTree());

                    }
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:843:4: deviceDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement1169);
                    deviceDeclaration25=deviceDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, deviceDeclaration25.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:846:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] );
    public final EugeneParser.variableDeclaration_return variableDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.variableDeclaration_return retval = new EugeneParser.variableDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NUM26=null;
        Token TXT27=null;
        Token TXT28=null;
        Token LEFTSBR29=null;
        Token RIGHTSBR30=null;
        Token NUM31=null;
        Token LEFTSBR32=null;
        Token RIGHTSBR33=null;
        Token set34=null;
        EugeneParser.numdecl_return n =null;

        EugeneParser.txtdecl_return t =null;

        EugeneParser.txtlistdecl_return tl =null;

        EugeneParser.numlistdecl_return nl =null;

        EugeneParser.booldecl_return b =null;


        Object NUM26_tree=null;
        Object TXT27_tree=null;
        Object TXT28_tree=null;
        Object LEFTSBR29_tree=null;
        Object RIGHTSBR30_tree=null;
        Object NUM31_tree=null;
        Object LEFTSBR32_tree=null;
        Object RIGHTSBR33_tree=null;
        Object set34_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:848:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

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
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;

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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:848:4: NUM n= numdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM26=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1187); 
                    NUM26_tree = 
                    (Object)adaptor.create(NUM26)
                    ;
                    adaptor.addChild(root_0, NUM26_tree);


                    pushFollow(FOLLOW_numdecl_in_variableDeclaration1191);
                    n=numdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, n.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.varname = (n!=null?n.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:853:4: TXT t= txtdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT27=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1202); 
                    TXT27_tree = 
                    (Object)adaptor.create(TXT27)
                    ;
                    adaptor.addChild(root_0, TXT27_tree);


                    pushFollow(FOLLOW_txtdecl_in_variableDeclaration1206);
                    t=txtdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.varname = (t!=null?t.varname:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:858:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT28=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1217); 
                    TXT28_tree = 
                    (Object)adaptor.create(TXT28)
                    ;
                    adaptor.addChild(root_0, TXT28_tree);


                    LEFTSBR29=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1219); 
                    LEFTSBR29_tree = 
                    (Object)adaptor.create(LEFTSBR29)
                    ;
                    adaptor.addChild(root_0, LEFTSBR29_tree);


                    RIGHTSBR30=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1221); 
                    RIGHTSBR30_tree = 
                    (Object)adaptor.create(RIGHTSBR30)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR30_tree);


                    pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration1225);
                    tl=txtlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tl.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.varname = (tl!=null?tl.varname:null);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:863:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM31=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1236); 
                    NUM31_tree = 
                    (Object)adaptor.create(NUM31)
                    ;
                    adaptor.addChild(root_0, NUM31_tree);


                    LEFTSBR32=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1238); 
                    LEFTSBR32_tree = 
                    (Object)adaptor.create(LEFTSBR32)
                    ;
                    adaptor.addChild(root_0, LEFTSBR32_tree);


                    RIGHTSBR33=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1240); 
                    RIGHTSBR33_tree = 
                    (Object)adaptor.create(RIGHTSBR33)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR33_tree);


                    pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1244);
                    nl=numlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, nl.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.varname = (nl!=null?nl.varname:null);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:868:4: ( BOOLEAN | BOOL ) b= booldecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    set34=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set34)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_booldecl_in_variableDeclaration1263);
                    b=booldecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.varname = (b!=null?b.varname:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "numdecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:875:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
    public final EugeneParser.numdecl_return numdecl(boolean defer) throws RecognitionException {
        EugeneParser.numdecl_return retval = new EugeneParser.numdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID35=null;
        Token COMMA36=null;
        Token ID38=null;
        Token EQUALS39=null;
        Token COMMA40=null;
        EugeneParser.expr_return ex =null;

        EugeneParser.numdecl_return numdecl37 =null;

        EugeneParser.numdecl_return numdecl41 =null;


        Object ID35_tree=null;
        Object COMMA36_tree=null;
        Object ID38_tree=null;
        Object EQUALS39_tree=null;
        Object COMMA40_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:877:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:877:4: ID ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID35=(Token)match(input,ID,FOLLOW_ID_in_numdecl1286); 
                    ID35_tree = 
                    (Object)adaptor.create(ID35)
                    ;
                    adaptor.addChild(root_0, ID35_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        declareVariableNoValue((ID35!=null?ID35.getText():null), EugeneConstants.NUM);
                        retval.varname = (ID35!=null?ID35.getText():null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:882:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:882:6: COMMA numdecl[defer]
                            {
                            COMMA36=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1292); 
                            COMMA36_tree = 
                            (Object)adaptor.create(COMMA36)
                            ;
                            adaptor.addChild(root_0, COMMA36_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1294);
                            numdecl37=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl37.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:883:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID38=(Token)match(input,ID,FOLLOW_ID_in_numdecl1302); 
                    ID38_tree = 
                    (Object)adaptor.create(ID38)
                    ;
                    adaptor.addChild(root_0, ID38_tree);


                    EQUALS39=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1304); 
                    EQUALS39_tree = 
                    (Object)adaptor.create(EQUALS39)
                    ;
                    adaptor.addChild(root_0, EQUALS39_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:883:14: (ex= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:883:15: ex= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_numdecl1309);
                    ex=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ex.getTree());

                    }



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        declareVariableWithValueNum((ID38!=null?ID38.getText():null), (ex!=null?ex.p:null), (ex!=null?ex.index:0));
                        retval.varname = (ID38!=null?ID38.getText():null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:888:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:888:6: COMMA numdecl[defer]
                            {
                            COMMA40=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1317); 
                            COMMA40_tree = 
                            (Object)adaptor.create(COMMA40)
                            ;
                            adaptor.addChild(root_0, COMMA40_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1319);
                            numdecl41=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl41.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "txtdecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:891:1: txtdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? );
    public final EugeneParser.txtdecl_return txtdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtdecl_return retval = new EugeneParser.txtdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID42=null;
        Token COMMA43=null;
        Token EQUALS45=null;
        Token COMMA46=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtdecl_return txtdecl44 =null;

        EugeneParser.txtdecl_return txtdecl47 =null;


        Object var_tree=null;
        Object ID42_tree=null;
        Object COMMA43_tree=null;
        Object EQUALS45_tree=null;
        Object COMMA46_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:2: ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:4: ID ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID42=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1339); 
                    ID42_tree = 
                    (Object)adaptor.create(ID42)
                    ;
                    adaptor.addChild(root_0, ID42_tree);



                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableNoValue((ID42!=null?ID42.getText():null), EugeneConstants.TXT);
                    			retval.varname = (ID42!=null?ID42.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:899:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:899:6: COMMA txtdecl[defer]
                            {
                            COMMA43=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1346); 
                            COMMA43_tree = 
                            (Object)adaptor.create(COMMA43)
                            ;
                            adaptor.addChild(root_0, COMMA43_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1348);
                            txtdecl44=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl44.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:901:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1359); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS45=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1361); 
                    EQUALS45_tree = 
                    (Object)adaptor.create(EQUALS45)
                    ;
                    adaptor.addChild(root_0, EQUALS45_tree);


                    pushFollow(FOLLOW_expr_in_txtdecl1365);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableWithValueTxt((var!=null?var.getText():null), (let!=null?let.p:null), (let!=null?let.index:0));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:907:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:907:6: COMMA txtdecl[defer]
                            {
                            COMMA46=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1373); 
                            COMMA46_tree = 
                            (Object)adaptor.create(COMMA46)
                            ;
                            adaptor.addChild(root_0, COMMA46_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1375);
                            txtdecl47=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl47.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "txtlistdecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:910:1: txtlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? );
    public final EugeneParser.txtlistdecl_return txtlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtlistdecl_return retval = new EugeneParser.txtlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID48=null;
        Token COMMA49=null;
        Token EQUALS51=null;
        Token COMMA52=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtlistdecl_return txtlistdecl50 =null;

        EugeneParser.txtlistdecl_return txtlistdecl53 =null;


        Object var_tree=null;
        Object ID48_tree=null;
        Object COMMA49_tree=null;
        Object EQUALS51_tree=null;
        Object COMMA52_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:912:2: ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:912:4: ID ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID48=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1395); 
                    ID48_tree = 
                    (Object)adaptor.create(ID48)
                    ;
                    adaptor.addChild(root_0, ID48_tree);



                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableNoValue((ID48!=null?ID48.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID48!=null?ID48.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:918:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:918:6: COMMA txtlistdecl[defer]
                            {
                            COMMA49=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1402); 
                            COMMA49_tree = 
                            (Object)adaptor.create(COMMA49)
                            ;
                            adaptor.addChild(root_0, COMMA49_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1404);
                            txtlistdecl50=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl50.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:919:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1414); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS51=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1416); 
                    EQUALS51_tree = 
                    (Object)adaptor.create(EQUALS51)
                    ;
                    adaptor.addChild(root_0, EQUALS51_tree);


                    typeList = EugeneConstants.TXT;

                    pushFollow(FOLLOW_expr_in_txtlistdecl1422);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableWithValueTxtList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:925:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:925:6: COMMA txtlistdecl[defer]
                            {
                            COMMA52=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1430); 
                            COMMA52_tree = 
                            (Object)adaptor.create(COMMA52)
                            ;
                            adaptor.addChild(root_0, COMMA52_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1432);
                            txtlistdecl53=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl53.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "numlistdecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:928:1: numlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? );
    public final EugeneParser.numlistdecl_return numlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.numlistdecl_return retval = new EugeneParser.numlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID54=null;
        Token COMMA55=null;
        Token EQUALS57=null;
        Token COMMA58=null;
        EugeneParser.expr_return let =null;

        EugeneParser.numlistdecl_return numlistdecl56 =null;

        EugeneParser.numlistdecl_return numlistdecl59 =null;


        Object var_tree=null;
        Object ID54_tree=null;
        Object COMMA55_tree=null;
        Object EQUALS57_tree=null;
        Object COMMA58_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:930:2: ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:930:4: ID ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID54=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1452); 
                    ID54_tree = 
                    (Object)adaptor.create(ID54)
                    ;
                    adaptor.addChild(root_0, ID54_tree);



                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableNoValue((ID54!=null?ID54.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID54!=null?ID54.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:6: COMMA numlistdecl[defer]
                            {
                            COMMA55=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1459); 
                            COMMA55_tree = 
                            (Object)adaptor.create(COMMA55)
                            ;
                            adaptor.addChild(root_0, COMMA55_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1461);
                            numlistdecl56=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl56.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:937:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1471); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS57=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1473); 
                    EQUALS57_tree = 
                    (Object)adaptor.create(EQUALS57)
                    ;
                    adaptor.addChild(root_0, EQUALS57_tree);


                     typeList = EugeneConstants.NUM;

                    pushFollow(FOLLOW_expr_in_numlistdecl1478);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableWithValueNumList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:943:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:943:6: COMMA numlistdecl[defer]
                            {
                            COMMA58=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1486); 
                            COMMA58_tree = 
                            (Object)adaptor.create(COMMA58)
                            ;
                            adaptor.addChild(root_0, COMMA58_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1488);
                            numlistdecl59=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl59.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "booldecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:946:1: booldecl[boolean defer] returns [String varname] : ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] );
    public final EugeneParser.booldecl_return booldecl(boolean defer) throws RecognitionException {
        EugeneParser.booldecl_return retval = new EugeneParser.booldecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID60=null;
        Token COMMA61=null;
        Token EQUALS63=null;
        EugeneParser.expr_return let =null;

        EugeneParser.booldecl_return booldecl62 =null;


        Object var_tree=null;
        Object ID60_tree=null;
        Object COMMA61_tree=null;
        Object EQUALS63_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:948:2: ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }
            switch (alt19) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:948:4: ID ( COMMA booldecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID60=(Token)match(input,ID,FOLLOW_ID_in_booldecl1508); 
                    ID60_tree = 
                    (Object)adaptor.create(ID60)
                    ;
                    adaptor.addChild(root_0, ID60_tree);



                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableNoValue((ID60!=null?ID60.getText():null), EugeneConstants.BOOLEAN);
                    			retval.varname = (ID60!=null?ID60.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:5: ( COMMA booldecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:6: COMMA booldecl[defer]
                            {
                            COMMA61=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1515); 
                            COMMA61_tree = 
                            (Object)adaptor.create(COMMA61)
                            ;
                            adaptor.addChild(root_0, COMMA61_tree);


                            pushFollow(FOLLOW_booldecl_in_booldecl1517);
                            booldecl62=booldecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, booldecl62.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:955:4: var= ID EQUALS let= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1527); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS63=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1529); 
                    EQUALS63_tree = 
                    (Object)adaptor.create(EQUALS63)
                    ;
                    adaptor.addChild(root_0, EQUALS63_tree);


                    pushFollow(FOLLOW_expr_in_booldecl1533);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			declareVariableWithValueBool((var!=null?var.getText():null), (let!=null?let.p:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "propertyDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:964:1: propertyDeclaration[boolean defer] : PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP ;
    public final EugeneParser.propertyDeclaration_return propertyDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.propertyDeclaration_return retval = new EugeneParser.propertyDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token PROPERTY64=null;
        Token LEFTP65=null;
        Token RIGHTP66=null;
        EugeneParser.propertyType_return typeToken =null;


        Object nameToken_tree=null;
        Object PROPERTY64_tree=null;
        Object LEFTP65_tree=null;
        Object RIGHTP66_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:965:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:965:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            PROPERTY64=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1551); 
            PROPERTY64_tree = 
            (Object)adaptor.create(PROPERTY64)
            ;
            adaptor.addChild(root_0, PROPERTY64_tree);


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1555); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            LEFTP65=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1557); 
            LEFTP65_tree = 
            (Object)adaptor.create(LEFTP65)
            ;
            adaptor.addChild(root_0, LEFTP65_tree);


            pushFollow(FOLLOW_propertyType_in_propertyDeclaration1561);
            typeToken=propertyType();

            state._fsp--;

            adaptor.addChild(root_0, typeToken.getTree());

            RIGHTP66=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1563); 
            RIGHTP66_tree = 
            (Object)adaptor.create(RIGHTP66)
            ;
            adaptor.addChild(root_0, RIGHTP66_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    interp.createProperty(
                        (nameToken!=null?nameToken.getText():null), 
                        (typeToken!=null?typeToken.type:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "propertyType"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:978:1: propertyType returns [String type] : ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) );
    public final EugeneParser.propertyType_return propertyType() throws RecognitionException {
        EugeneParser.propertyType_return retval = new EugeneParser.propertyType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token TXT67=null;
        Token TXT68=null;
        Token LEFTSBR69=null;
        Token RIGHTSBR70=null;
        Token NUM71=null;
        Token NUM72=null;
        Token LEFTSBR73=null;
        Token RIGHTSBR74=null;
        Token set75=null;

        Object TXT67_tree=null;
        Object TXT68_tree=null;
        Object LEFTSBR69_tree=null;
        Object RIGHTSBR70_tree=null;
        Object NUM71_tree=null;
        Object NUM72_tree=null;
        Object LEFTSBR73_tree=null;
        Object RIGHTSBR74_tree=null;
        Object set75_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:980:2: ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;

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
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 2, input);

                    throw nvae;

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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:980:4: TXT
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT67=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1582); 
                    TXT67_tree = 
                    (Object)adaptor.create(TXT67)
                    ;
                    adaptor.addChild(root_0, TXT67_tree);



                    retval.type = EugeneConstants.TXT;	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:983:4: TXT LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT68=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1589); 
                    TXT68_tree = 
                    (Object)adaptor.create(TXT68)
                    ;
                    adaptor.addChild(root_0, TXT68_tree);


                    LEFTSBR69=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1591); 
                    LEFTSBR69_tree = 
                    (Object)adaptor.create(LEFTSBR69)
                    ;
                    adaptor.addChild(root_0, LEFTSBR69_tree);


                    RIGHTSBR70=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1593); 
                    RIGHTSBR70_tree = 
                    (Object)adaptor.create(RIGHTSBR70)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR70_tree);



                    retval.type = EugeneConstants.TXTLIST;	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:986:4: NUM
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM71=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1600); 
                    NUM71_tree = 
                    (Object)adaptor.create(NUM71)
                    ;
                    adaptor.addChild(root_0, NUM71_tree);



                    retval.type = EugeneConstants.NUM;	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:989:4: NUM LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM72=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1608); 
                    NUM72_tree = 
                    (Object)adaptor.create(NUM72)
                    ;
                    adaptor.addChild(root_0, NUM72_tree);


                    LEFTSBR73=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1610); 
                    LEFTSBR73_tree = 
                    (Object)adaptor.create(LEFTSBR73)
                    ;
                    adaptor.addChild(root_0, LEFTSBR73_tree);


                    RIGHTSBR74=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1612); 
                    RIGHTSBR74_tree = 
                    (Object)adaptor.create(RIGHTSBR74)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR74_tree);



                    retval.type = EugeneConstants.NUMLIST;	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:992:4: ( BOOLEAN | BOOL )
                    {
                    root_0 = (Object)adaptor.nil();


                    set75=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set75)
                        );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1001:1: typeDeclaration[boolean defer] : ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? );
    public final EugeneParser.typeDeclaration_return typeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.typeDeclaration_return retval = new EugeneParser.typeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token TYPE77=null;
        Token LEFTP78=null;
        Token RIGHTP79=null;
        EugeneParser.listOfIDs_return lstToken =null;

        EugeneParser.partTypeDeclaration_return partTypeDeclaration76 =null;


        Object nameToken_tree=null;
        Object TYPE77_tree=null;
        Object LEFTP78_tree=null;
        Object RIGHTP79_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1002:2: ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1002:4: partTypeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1641);
                    partTypeDeclaration76=partTypeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, partTypeDeclaration76.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:4: ( TYPE )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:5: TYPE
                    {
                    TYPE77=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1648); 
                    TYPE77_tree = 
                    (Object)adaptor.create(TYPE77)
                    ;
                    adaptor.addChild(root_0, TYPE77_tree);


                    }


                    nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1653); 
                    nameToken_tree = 
                    (Object)adaptor.create(nameToken)
                    ;
                    adaptor.addChild(root_0, nameToken_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:24: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==LEFTP) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:25: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                            {
                            LEFTP78=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1656); 
                            LEFTP78_tree = 
                            (Object)adaptor.create(LEFTP78)
                            ;
                            adaptor.addChild(root_0, LEFTP78_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:31: (lstToken= listOfIDs[defer] )?
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0==ID) ) {
                                alt21=1;
                            }
                            switch (alt21) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:32: lstToken= listOfIDs[defer]
                                    {
                                    pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1661);
                                    lstToken=listOfIDs(defer);

                                    state._fsp--;

                                    adaptor.addChild(root_0, lstToken.getTree());

                                    }
                                    break;

                            }


                            RIGHTP79=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1666); 
                            RIGHTP79_tree = 
                            (Object)adaptor.create(RIGHTP79)
                            ;
                            adaptor.addChild(root_0, RIGHTP79_tree);


                            }
                            break;

                    }



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            interp.createType(
                                (nameToken!=null?nameToken.getText():null), 
                                (lstToken!=null?lstToken.lstElements:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "partTypeDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1016:1: partTypeDeclaration[boolean defer] : ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? ;
    public final EugeneParser.partTypeDeclaration_return partTypeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.partTypeDeclaration_return retval = new EugeneParser.partTypeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token set80=null;
        Token LEFTP81=null;
        Token RIGHTP82=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object nameToken_tree=null;
        Object set80_tree=null;
        Object LEFTP81_tree=null;
        Object RIGHTP82_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            set80=(Token)input.LT(1);

            if ( (input.LA(1) >= PART && input.LA(1) <= PART_TYPE) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set80)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1694); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:35: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LEFTP) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:36: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                    {
                    LEFTP81=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1697); 
                    LEFTP81_tree = 
                    (Object)adaptor.create(LEFTP81)
                    ;
                    adaptor.addChild(root_0, LEFTP81_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:42: (lstToken= listOfIDs[defer] )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==ID) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1017:43: lstToken= listOfIDs[defer]
                            {
                            pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1702);
                            lstToken=listOfIDs(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lstToken.getTree());

                            }
                            break;

                    }


                    RIGHTP82=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1707); 
                    RIGHTP82_tree = 
                    (Object)adaptor.create(RIGHTP82)
                    ;
                    adaptor.addChild(root_0, RIGHTP82_tree);


                    }
                    break;

            }



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    interp.createPartType(
                        (nameToken!=null?nameToken.getText():null), 
                        (lstToken!=null?lstToken.lstElements:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "containerDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1033:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
    public final EugeneParser.containerDeclaration_return containerDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.containerDeclaration_return retval = new EugeneParser.containerDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token c=null;
        Token a=null;
        Token name=null;
        Token LEFTSBR83=null;
        Token RIGHTSBR84=null;
        Token LEFTP85=null;
        Token RIGHTP87=null;
        EugeneParser.list_of_declarations_return list_of_declarations86 =null;


        Object c_tree=null;
        Object a_tree=null;
        Object name_tree=null;
        Object LEFTSBR83_tree=null;
        Object RIGHTSBR84_tree=null;
        Object LEFTP85_tree=null;
        Object RIGHTP87_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:2: ( (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:5: c= COLLECTION
                    {
                    c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1734); 
                    c_tree = 
                    (Object)adaptor.create(c)
                    ;
                    adaptor.addChild(root_0, c_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:21: a= ARRAY ( LEFTSBR RIGHTSBR )?
                    {
                    a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1741); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:29: ( LEFTSBR RIGHTSBR )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==LEFTSBR) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1035:30: LEFTSBR RIGHTSBR
                            {
                            LEFTSBR83=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1744); 
                            LEFTSBR83_tree = 
                            (Object)adaptor.create(LEFTSBR83)
                            ;
                            adaptor.addChild(root_0, LEFTSBR83_tree);


                            RIGHTSBR84=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1746); 
                            RIGHTSBR84_tree = 
                            (Object)adaptor.create(RIGHTSBR84)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR84_tree);


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1754); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1049:4: ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LEFTP) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1049:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
                    {
                    LEFTP85=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1759); 
                    LEFTP85_tree = 
                    (Object)adaptor.create(LEFTP85)
                    ;
                    adaptor.addChild(root_0, LEFTP85_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1049:11: ( list_of_declarations[defer] )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==ARRAY||(LA28_0 >= BOOL && LA28_0 <= COLLECTION)||LA28_0==DEVICE||LA28_0==DOLLAR||(LA28_0 >= EXIT_LC && LA28_0 <= EXIT_UC)||(LA28_0 >= FALSE_LC && LA28_0 <= FALSE_UC)||LA28_0==GRAMMAR||LA28_0==ID||LA28_0==INTERACTION||(LA28_0 >= LEFTP && LA28_0 <= LEFTSBR)||LA28_0==MINUS||(LA28_0 >= NUM && LA28_0 <= PERMUTE)||(LA28_0 >= PRODUCT && LA28_0 <= REAL)||(LA28_0 >= RULE && LA28_0 <= SAVE_UC)||(LA28_0 >= SIZEOF_LC && LA28_0 <= STORE_UC)||(LA28_0 >= STRING && LA28_0 <= TYPE)) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1049:12: list_of_declarations[defer]
                            {
                            pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1762);
                            list_of_declarations86=list_of_declarations(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, list_of_declarations86.getTree());

                            }
                            break;

                    }


                    RIGHTP87=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1767); 
                    RIGHTP87_tree = 
                    (Object)adaptor.create(RIGHTP87)
                    ;
                    adaptor.addChild(root_0, RIGHTP87_tree);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list_of_declarations"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1064:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
    public final EugeneParser.list_of_declarations_return list_of_declarations(boolean defer) throws RecognitionException {
        EugeneParser.list_of_declarations_return retval = new EugeneParser.list_of_declarations_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA88=null;
        EugeneParser.declarationStatement_return ds =null;

        EugeneParser.expr_return exp =null;

        EugeneParser.list_of_declarations_return lod =null;


        Object COMMA88_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1066:2: ( (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1066:4: (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1066:4: (ds= declarationStatement[defer] |exp= expr[defer] )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 2, input);

                    throw nvae;

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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1066:6: ds= declarationStatement[defer]
                    {
                    pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1800);
                    ds=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ds.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1066:39: exp= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_list_of_declarations1807);
                    exp=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            if(null != (exp!=null?exp.element:null)) {
                                this.interp.addToContainer((exp!=null?exp.element:null));
                            } else if(null != (exp!=null?exp.p:null)) {
                                this.interp.addToContainer((exp!=null?exp.p:null));
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1080:5: ( COMMA lod= list_of_declarations[defer] )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==COMMA) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1080:7: COMMA lod= list_of_declarations[defer]
                    {
                    COMMA88=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1815); 
                    COMMA88_tree = 
                    (Object)adaptor.create(COMMA88)
                    ;
                    adaptor.addChild(root_0, COMMA88_tree);


                    pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations1819);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "instantiation"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1086:1: instantiation[boolean defer] : t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
    public final EugeneParser.instantiation_return instantiation(boolean defer) throws RecognitionException {
        EugeneParser.instantiation_return retval = new EugeneParser.instantiation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token t=null;
        Token LEFTP89=null;
        Token RIGHTP90=null;
        EugeneParser.dynamic_naming_return n =null;

        EugeneParser.listOfDotValues_return dotToken =null;

        EugeneParser.listOfValues_return valueToken =null;


        Object t_tree=null;
        Object LEFTP89_tree=null;
        Object RIGHTP90_tree=null;


        NamedElement type = null;
        String instance_name = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1091:2: (t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1091:4: t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            t=(Token)match(input,ID,FOLLOW_ID_in_instantiation1847); 
            t_tree = 
            (Object)adaptor.create(t)
            ;
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
            	

            pushFollow(FOLLOW_dynamic_naming_in_instantiation1853);
            n=dynamic_naming(defer);

            state._fsp--;

            adaptor.addChild(root_0, n.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                instance_name = (n!=null?n.name:null);	
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1111:4: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LEFTP) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1111:6: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
                    {
                    LEFTP89=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation1860); 
                    LEFTP89_tree = 
                    (Object)adaptor.create(LEFTP89)
                    ;
                    adaptor.addChild(root_0, LEFTP89_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1111:12: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1111:13: dotToken= listOfDotValues[defer]
                            {
                            pushFollow(FOLLOW_listOfDotValues_in_instantiation1865);
                            dotToken=listOfDotValues(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dotToken.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1111:45: valueToken= listOfValues[defer, (ComponentType)type]
                            {
                            pushFollow(FOLLOW_listOfValues_in_instantiation1870);
                            valueToken=listOfValues(defer, (ComponentType)type);

                            state._fsp--;

                            adaptor.addChild(root_0, valueToken.getTree());

                            }
                            break;

                    }


                    RIGHTP90=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation1875); 
                    RIGHTP90_tree = 
                    (Object)adaptor.create(RIGHTP90)
                    ;
                    adaptor.addChild(root_0, RIGHTP90_tree);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfDotValues"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1145:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
    public final EugeneParser.listOfDotValues_return listOfDotValues(boolean defer) throws RecognitionException {
        EugeneParser.listOfDotValues_return retval = new EugeneParser.listOfDotValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token prop=null;
        Token p=null;
        Token DOT91=null;
        Token LEFTP92=null;
        Token RIGHTP93=null;
        Token COMMA94=null;
        Token DOT95=null;
        Token LEFTP96=null;
        Token RIGHTP97=null;
        EugeneParser.expr_return v1 =null;

        EugeneParser.expr_return v2 =null;


        Object prop_tree=null;
        Object p_tree=null;
        Object DOT91_tree=null;
        Object LEFTP92_tree=null;
        Object RIGHTP93_tree=null;
        Object COMMA94_tree=null;
        Object DOT95_tree=null;
        Object LEFTP96_tree=null;
        Object RIGHTP97_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1146:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1146:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            {
            root_0 = (Object)adaptor.nil();


            DOT91=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1898); 
            DOT91_tree = 
            (Object)adaptor.create(DOT91)
            ;
            adaptor.addChild(root_0, DOT91_tree);


            prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1902); 
            prop_tree = 
            (Object)adaptor.create(prop)
            ;
            adaptor.addChild(root_0, prop_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {		
                try {
                    addToPropertyListHolder((prop!=null?prop.getText():null));
                } catch(EugeneException ee) {
                    printError(ee.getMessage());
                }				
            }			
            		

            LEFTP92=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1908); 
            LEFTP92_tree = 
            (Object)adaptor.create(LEFTP92)
            ;
            adaptor.addChild(root_0, LEFTP92_tree);


            pushFollow(FOLLOW_expr_in_listOfDotValues1912);
            v1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, v1.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
                try {
                    addToPropertyValuesHolder((prop!=null?prop.getText():null), (v1!=null?v1.p:null), (v1!=null?v1.index:0));
                } catch(EugeneException ee) {
                    printError(ee.getMessage());
                }				
            }				
            			

            RIGHTP93=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1920); 
            RIGHTP93_tree = 
            (Object)adaptor.create(RIGHTP93)
            ;
            adaptor.addChild(root_0, RIGHTP93_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:13: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMA) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:14: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
            	    {
            	    COMMA94=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues1923); 
            	    COMMA94_tree = 
            	    (Object)adaptor.create(COMMA94)
            	    ;
            	    adaptor.addChild(root_0, COMMA94_tree);


            	    DOT95=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1925); 
            	    DOT95_tree = 
            	    (Object)adaptor.create(DOT95)
            	    ;
            	    adaptor.addChild(root_0, DOT95_tree);


            	    p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1929); 
            	    p_tree = 
            	    (Object)adaptor.create(p)
            	    ;
            	    adaptor.addChild(root_0, p_tree);



            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
            	        try {
            	            addToPropertyListHolder((p!=null?p.getText():null));
            	        } catch(EugeneException ee) {
            	            printError(ee.getMessage());
            	        }				
            	    }				
            	    				

            	    LEFTP96=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1937); 
            	    LEFTP96_tree = 
            	    (Object)adaptor.create(LEFTP96)
            	    ;
            	    adaptor.addChild(root_0, LEFTP96_tree);


            	    pushFollow(FOLLOW_expr_in_listOfDotValues1941);
            	    v2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, v2.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
            	        try {
            	            addToPropertyValuesHolder((p!=null?p.getText():null), (v2!=null?v2.p:null), (v2!=null?v2.index:0));
            	        } catch(EugeneException ee) {
            	            printError(ee.getMessage());
            	        }				
            	    }				
            	    					

            	    RIGHTP97=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1951); 
            	    RIGHTP97_tree = 
            	    (Object)adaptor.create(RIGHTP97)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP97_tree);


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfValues"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1185:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
    public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
        EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA98=null;
        EugeneParser.expr_return val1 =null;

        EugeneParser.expr_return val2 =null;


        Object COMMA98_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1186:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1187:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
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
            		

            pushFollow(FOLLOW_expr_in_listOfValues1972);
            val1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, val1.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                propertyValuesHolder.add((val1!=null?val1.p:null));
            }				
            			

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1211:6: ( COMMA val2= expr[defer] )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1211:7: COMMA val2= expr[defer]
            	    {
            	    COMMA98=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues1978); 
            	    COMMA98_tree = 
            	    (Object)adaptor.create(COMMA98)
            	    ;
            	    adaptor.addChild(root_0, COMMA98_tree);



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
            	                   

            	    pushFollow(FOLLOW_expr_in_listOfValues1984);
            	    val2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, val2.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
            	        propertyValuesHolder.add((val2!=null?val2.p:null));
            	    }				
            	    		

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "deviceDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1241:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
    public final EugeneParser.deviceDeclaration_return deviceDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.deviceDeclaration_return retval = new EugeneParser.deviceDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token DEVICE99=null;
        Token LEFTP100=null;
        Token RIGHTP101=null;
        EugeneParser.deviceComponents_return dcs =null;


        Object n_tree=null;
        Object DEVICE99_tree=null;
        Object LEFTP100_tree=null;
        Object RIGHTP101_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1242:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1242:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            DEVICE99=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration2003); 
            DEVICE99_tree = 
            (Object)adaptor.create(DEVICE99)
            ;
            adaptor.addChild(root_0, DEVICE99_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration2007); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1242:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==LEFTP) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1242:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
                    {
                    LEFTP100=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration2010); 
                    LEFTP100_tree = 
                    (Object)adaptor.create(LEFTP100)
                    ;
                    adaptor.addChild(root_0, LEFTP100_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1242:23: (dcs= deviceComponents[defer] )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==ID||LA36_0==LEFTSBR||LA36_0==MINUS||LA36_0==PLUS) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1242:24: dcs= deviceComponents[defer]
                            {
                            pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration2015);
                            dcs=deviceComponents(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dcs.getTree());

                            }
                            break;

                    }


                    RIGHTP101=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration2020); 
                    RIGHTP101_tree = 
                    (Object)adaptor.create(RIGHTP101)
                    ;
                    adaptor.addChild(root_0, RIGHTP101_tree);


                    }
                    break;

            }



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    interp.createDevice(
                        (n!=null?n.getText():null), 
                        (dcs!=null?dcs.lstComponents:null),
                        (dcs!=null?dcs.lstOrientations:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "deviceComponents"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1256:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
    public final EugeneParser.deviceComponents_return deviceComponents(boolean defer) throws RecognitionException {
        EugeneParser.deviceComponents_return retval = new EugeneParser.deviceComponents_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal102=null;
        EugeneParser.selection_return s =null;

        EugeneParser.deviceComponents_return dcs =null;


        Object char_literal102_tree=null;


        retval.lstComponents = new ArrayList<List<NamedElement>>();
        retval.lstOrientations = new ArrayList<List<Orientation>>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1262:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1262:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selection_in_deviceComponents2051);
            s=selection(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                retval.lstComponents.add((s!=null?s.components:null));
                retval.lstOrientations.add((s!=null?s.orientations:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1267:4: ( ',' dcs= deviceComponents[defer] )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==COMMA) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1267:5: ',' dcs= deviceComponents[defer]
                    {
                    char_literal102=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents2057); 
                    char_literal102_tree = 
                    (Object)adaptor.create(char_literal102)
                    ;
                    adaptor.addChild(root_0, char_literal102_tree);


                    pushFollow(FOLLOW_deviceComponents_in_deviceComponents2061);
                    dcs=deviceComponents(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, dcs.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.lstComponents.addAll((dcs!=null?dcs.lstComponents:null));
                        retval.lstOrientations.addAll((dcs!=null?dcs.lstOrientations:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selection"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1275:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
    public final EugeneParser.selection_return selection(boolean defer) throws RecognitionException {
        EugeneParser.selection_return retval = new EugeneParser.selection_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTSBR103=null;
        Token RIGHTSBR104=null;
        EugeneParser.selection_list_return sl =null;

        EugeneParser.device_component_return dc =null;


        Object LEFTSBR103_tree=null;
        Object RIGHTSBR104_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1277:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1277:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR103=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection2085); 
                    LEFTSBR103_tree = 
                    (Object)adaptor.create(LEFTSBR103)
                    ;
                    adaptor.addChild(root_0, LEFTSBR103_tree);


                    pushFollow(FOLLOW_selection_list_in_selection2089);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());

                    RIGHTSBR104=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection2092); 
                    RIGHTSBR104_tree = 
                    (Object)adaptor.create(RIGHTSBR104)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR104_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.components = (sl!=null?sl.components:null);
                        retval.orientations = (sl!=null?sl.orientations:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1283:4: dc= device_component[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_device_component_in_selection2101);
                    dc=device_component(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, dc.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.components = new ArrayList<NamedElement>(1);
                        retval.components.add((dc!=null?dc.component:null));
                        
                        retval.orientations = new ArrayList<Orientation>();
                        retval.orientations.add((dc!=null?dc.orientation:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selection_list"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1294:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
    public final EugeneParser.selection_list_return selection_list(boolean defer) throws RecognitionException {
        EugeneParser.selection_list_return retval = new EugeneParser.selection_list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIPE105=null;
        EugeneParser.device_component_return dc =null;

        EugeneParser.selection_list_return sl =null;


        Object PIPE105_tree=null;


        retval.components = new ArrayList<NamedElement>();
        retval.orientations = new ArrayList<Orientation>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1300:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1300:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_device_component_in_selection_list2130);
            dc=device_component(defer);

            state._fsp--;

            adaptor.addChild(root_0, dc.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                retval.components.add((dc!=null?dc.component:null));
                retval.orientations.add((dc!=null?dc.orientation:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1305:4: ( PIPE sl= selection_list[defer] )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==PIPE) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1305:5: PIPE sl= selection_list[defer]
                    {
                    PIPE105=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list2136); 
                    PIPE105_tree = 
                    (Object)adaptor.create(PIPE105)
                    ;
                    adaptor.addChild(root_0, PIPE105_tree);


                    pushFollow(FOLLOW_selection_list_in_selection_list2140);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.components.addAll((sl!=null?sl.components:null));
                        retval.orientations.addAll((sl!=null?sl.orientations:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "device_component"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1313:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
    public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
        EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token directionToken=null;
        Token idToken=null;

        Object directionToken_tree=null;
        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1315:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1315:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1315:4: (directionToken= ( MINUS | PLUS ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==MINUS||LA41_0==PLUS) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1315:5: directionToken= ( MINUS | PLUS )
                    {
                    directionToken=(Token)input.LT(1);

                    if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(directionToken)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }


            idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component2176); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignment"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1363:1: assignment[boolean defer] : lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
    public final EugeneParser.assignment_return assignment(boolean defer) throws RecognitionException {
        EugeneParser.assignment_return retval = new EugeneParser.assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token a=null;
        Token EQUALS106=null;
        EugeneParser.lhs_assignment_return lhs =null;

        EugeneParser.rhs_assignment_return rhs =null;


        Object a_tree=null;
        Object EQUALS106_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1364:2: (lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1364:4: lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_lhs_assignment_in_assignment2196);
            lhs=lhs_assignment(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            EQUALS106=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment2199); 
            EQUALS106_tree = 
            (Object)adaptor.create(EQUALS106)
            ;
            adaptor.addChild(root_0, EQUALS106_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1364:37: (a= AMP )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==AMP) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1364:38: a= AMP
                    {
                    a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment2204); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    }
                    break;

            }


            pushFollow(FOLLOW_rhs_assignment_in_assignment2210);
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
                       this.interp.assignment((lhs!=null?((Object)lhs.tree):null), (rhs!=null?rhs.e:null));
                     */
                    this.interp.assignment(
                                    (lhs!=null?input.toString(lhs.start,lhs.stop):null), 
                                    (rhs!=null?rhs.e:null));
                    
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lhs_assignment"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1393:1: lhs_assignment[boolean defer] : ID lhs_access[defer] ;
    public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID107=null;
        EugeneParser.lhs_access_return lhs_access108 =null;


        Object ID107_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1394:2: ( ID lhs_access[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1394:4: ID lhs_access[defer]
            {
            root_0 = (Object)adaptor.nil();


            ID107=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment2225); 
            ID107_tree = 
            (Object)adaptor.create(ID107)
            ;
            adaptor.addChild(root_0, ID107_tree);


            pushFollow(FOLLOW_lhs_access_in_lhs_assignment2227);
            lhs_access108=lhs_access(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs_access108.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lhs_access"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1397:1: lhs_access[boolean defer] : (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] );
    public final EugeneParser.lhs_access_return lhs_access(boolean defer) throws RecognitionException {
        EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token DOT109=null;
        Token LEFTSBR110=null;
        Token set111=null;
        Token RIGHTSBR112=null;
        EugeneParser.lhs_access_return lhs_access113 =null;


        Object i_tree=null;
        Object DOT109_tree=null;
        Object LEFTSBR110_tree=null;
        Object set111_tree=null;
        Object RIGHTSBR112_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1398:2: (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1399:2: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1399:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1399:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1399:5: DOT i= ID
                            {
                            DOT109=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2247); 
                            DOT109_tree = 
                            (Object)adaptor.create(DOT109)
                            ;
                            adaptor.addChild(root_0, DOT109_tree);


                            i=(Token)match(input,ID,FOLLOW_ID_in_lhs_access2251); 
                            i_tree = 
                            (Object)adaptor.create(i)
                            ;
                            adaptor.addChild(root_0, i_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1399:16: LEFTSBR ( ID | NUMBER ) RIGHTSBR
                            {
                            LEFTSBR110=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2255); 
                            LEFTSBR110_tree = 
                            (Object)adaptor.create(LEFTSBR110)
                            ;
                            adaptor.addChild(root_0, LEFTSBR110_tree);


                            set111=(Token)input.LT(1);

                            if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                                input.consume();
                                adaptor.addChild(root_0, 
                                (Object)adaptor.create(set111)
                                );
                                state.errorRecovery=false;
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            RIGHTSBR112=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2263); 
                            RIGHTSBR112_tree = 
                            (Object)adaptor.create(RIGHTSBR112)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR112_tree);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_lhs_access_in_lhs_access2266);
                    lhs_access113=lhs_access(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs_access113.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rhs_assignment"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1410:1: rhs_assignment[boolean defer] returns [NamedElement e] : (de= dataExchange[defer] |exp= expr[defer] );
    public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.dataExchange_return de =null;

        EugeneParser.expr_return exp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1412:2: (de= dataExchange[defer] |exp= expr[defer] )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( ((LA45_0 >= IMPORT_LC && LA45_0 <= IMPORT_UC)||(LA45_0 >= PIGEON_LC && LA45_0 <= PIGEON_UC)||LA45_0==SBOL) ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1412:4: de= dataExchange[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_rhs_assignment2293);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.e = (de!=null?de.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1417:4: exp= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_rhs_assignment2303);
                    exp=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        if((exp!=null?exp.element:null) != null) {
                            retval.e = (exp!=null?exp.element:null);
                        } else {
                            retval.e = (exp!=null?exp.p:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfIDs"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1428:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
    public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
        EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token char_literal114=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object idToken_tree=null;
        Object char_literal114_tree=null;


        retval.lstElements =new ArrayList<NamedElement>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1433:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1433:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2331); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1445:4: ( ',' lstToken= listOfIDs[defer] )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==COMMA) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1445:5: ',' lstToken= listOfIDs[defer]
                    {
                    char_literal114=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2340); 
                    char_literal114_tree = 
                    (Object)adaptor.create(char_literal114)
                    ;
                    adaptor.addChild(root_0, char_literal114_tree);


                    pushFollow(FOLLOW_listOfIDs_in_listOfIDs2344);
                    lstToken=listOfIDs(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lstToken.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING){
                        retval.lstElements.addAll((lstToken!=null?lstToken.lstElements:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ruleDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1452:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
    public final EugeneParser.ruleDeclaration_return ruleDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.ruleDeclaration_return retval = new EugeneParser.ruleDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token device=null;
        Token RULE115=null;
        Token LEFTP116=null;
        Token set117=null;
        Token COLON118=null;
        Token RIGHTP119=null;
        EugeneParser.cnf_rule_return cnf =null;


        Object name_tree=null;
        Object device_tree=null;
        Object RULE115_tree=null;
        Object LEFTP116_tree=null;
        Object set117_tree=null;
        Object COLON118_tree=null;
        Object RIGHTP119_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            RULE115=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2368); 
            RULE115_tree = 
            (Object)adaptor.create(RULE115)
            ;
            adaptor.addChild(root_0, RULE115_tree);


            name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2372); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
            adaptor.addChild(root_0, name_tree);


            LEFTP116=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2374); 
            LEFTP116_tree = 
            (Object)adaptor.create(LEFTP116)
            ;
            adaptor.addChild(root_0, LEFTP116_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==LC_ON||LA47_0==UC_ON) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:26: ( LC_ON | UC_ON ) device= ID COLON
                    {
                    set117=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set117)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2387); 
                    device_tree = 
                    (Object)adaptor.create(device)
                    ;
                    adaptor.addChild(root_0, device_tree);


                    COLON118=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2389); 
                    COLON118_tree = 
                    (Object)adaptor.create(COLON118)
                    ;
                    adaptor.addChild(root_0, COLON118_tree);


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
            	

            pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2397);
            cnf=cnf_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, cnf.getTree());

            }


            RIGHTP119=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2402); 
            RIGHTP119_tree = 
            (Object)adaptor.create(RIGHTP119)
            ;
            adaptor.addChild(root_0, RIGHTP119_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                retval.rule.setLogicalAnd((cnf!=null?cnf.lAnd:null));

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ruleOperator"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1478:1: ruleOperator[boolean defer] : ruleOperators ;
    public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
        EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ruleOperators_return ruleOperators120 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1479:2: ( ruleOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1479:4: ruleOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_ruleOperators_in_ruleOperator2416);
            ruleOperators120=ruleOperators();

            state._fsp--;

            adaptor.addChild(root_0, ruleOperators120.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ruleOperators"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1483:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
    public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
        EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set121=null;

        Object set121_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1484:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set121=(Token)input.LT(1);

            if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 123 && input.LA(1) <= 157)||(input.LA(1) >= 159 && input.LA(1) <= 196)||(input.LA(1) >= 198 && input.LA(1) <= 200) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set121)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relationalOperators"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1527:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
    public final EugeneParser.relationalOperators_return relationalOperators() throws RecognitionException {
        EugeneParser.relationalOperators_return retval = new EugeneParser.relationalOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EQUALS122=null;
        Token EQUALS123=null;
        Token NEQUAL124=null;
        Token LTHAN125=null;
        Token GTHAN126=null;
        Token LEQUAL127=null;
        Token GEQUAL128=null;
        Token set129=null;
        Token set130=null;
        Token set131=null;
        Token set132=null;
        Token set133=null;
        Token set134=null;
        Token set135=null;
        Token set136=null;
        Token set137=null;

        Object EQUALS122_tree=null;
        Object EQUALS123_tree=null;
        Object NEQUAL124_tree=null;
        Object LTHAN125_tree=null;
        Object GTHAN126_tree=null;
        Object LEQUAL127_tree=null;
        Object GEQUAL128_tree=null;
        Object set129_tree=null;
        Object set130_tree=null;
        Object set131_tree=null;
        Object set132_tree=null;
        Object set133_tree=null;
        Object set134_tree=null;
        Object set135_tree=null;
        Object set136_tree=null;
        Object set137_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1528:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1528:4: EQUALS EQUALS
                    {
                    root_0 = (Object)adaptor.nil();


                    EQUALS122=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2795); 
                    EQUALS122_tree = 
                    (Object)adaptor.create(EQUALS122)
                    ;
                    adaptor.addChild(root_0, EQUALS122_tree);


                    EQUALS123=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2797); 
                    EQUALS123_tree = 
                    (Object)adaptor.create(EQUALS123)
                    ;
                    adaptor.addChild(root_0, EQUALS123_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1529:4: NEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    NEQUAL124=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators2802); 
                    NEQUAL124_tree = 
                    (Object)adaptor.create(NEQUAL124)
                    ;
                    adaptor.addChild(root_0, NEQUAL124_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1530:4: LTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    LTHAN125=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators2807); 
                    LTHAN125_tree = 
                    (Object)adaptor.create(LTHAN125)
                    ;
                    adaptor.addChild(root_0, LTHAN125_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1531:4: GTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    GTHAN126=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators2812); 
                    GTHAN126_tree = 
                    (Object)adaptor.create(GTHAN126)
                    ;
                    adaptor.addChild(root_0, GTHAN126_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1532:4: LEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    LEQUAL127=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators2817); 
                    LEQUAL127_tree = 
                    (Object)adaptor.create(LEQUAL127)
                    ;
                    adaptor.addChild(root_0, LEQUAL127_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1533:4: GEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    GEQUAL128=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators2822); 
                    GEQUAL128_tree = 
                    (Object)adaptor.create(GEQUAL128)
                    ;
                    adaptor.addChild(root_0, GEQUAL128_tree);


                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1534:4: ( 'CONTAINS' | 'contains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set129=(Token)input.LT(1);

                    if ( input.LA(1)==133||input.LA(1)==172 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set129)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1535:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set130=(Token)input.LT(1);

                    if ( input.LA(1)==142||input.LA(1)==181 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set130)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1536:4: ( 'MATCHES' | 'matches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set131=(Token)input.LT(1);

                    if ( input.LA(1)==139||input.LA(1)==178 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set131)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 10 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1537:4: ( 'NOTMATCHES' | 'notmatches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set132=(Token)input.LT(1);

                    if ( input.LA(1)==145||input.LA(1)==184 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set132)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 11 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1538:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set133=(Token)input.LT(1);

                    if ( input.LA(1)==159||input.LA(1)==198 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set133)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 12 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1539:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set134=(Token)input.LT(1);

                    if ( input.LA(1)==135||input.LA(1)==174 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set134)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 13 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1540:4: ( 'EQUALS' | 'equals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set135=(Token)input.LT(1);

                    if ( input.LA(1)==136||input.LA(1)==175 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set135)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 14 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1541:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set136=(Token)input.LT(1);

                    if ( input.LA(1)==143||input.LA(1)==182 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set136)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 15 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1542:4: ( 'SOUNDSLIKE' | 'soundslike' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set137=(Token)input.LT(1);

                    if ( input.LA(1)==158||input.LA(1)==197 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set137)
                        );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cnf_rule"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1545:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
    public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
        EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set138=null;
        EugeneParser.or_predicate_return c =null;

        EugeneParser.cnf_rule_return cnf =null;


        Object set138_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1547:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1547:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1547:4: (c= or_predicate[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1547:5: c= or_predicate[defer]
            {
            pushFollow(FOLLOW_or_predicate_in_cnf_rule2923);
            c=or_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, c.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                if(null == retval.lAnd) {
                    retval.lAnd = new LogicalAnd();
                }
                
                retval.lAnd.getPredicates().add((c!=null?c.p:null));
            }	
            	

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1555:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LC_AND||LA49_0==LOG_AND||LA49_0==UC_AND) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1555:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
                    {
                    set138=(Token)input.LT(1);

                    if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set138)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_cnf_rule_in_cnf_rule2941);
                    cnf=cnf_rule(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, cnf.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.lAnd.union((cnf!=null?cnf.lAnd:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "or_predicate"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1562:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
    public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
        EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set139=null;
        EugeneParser.negated_predicate_return n1 =null;

        EugeneParser.negated_predicate_return n2 =null;


        Object set139_tree=null;


        LogicalOr lor = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1567:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1567:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_negated_predicate_in_or_predicate2971);
            n1=negated_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, n1.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                retval.p = (n1!=null?n1.p:null);
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1571:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==LC_OR||LA50_0==LOG_OR||LA50_0==UC_OR) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1571:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
            	    {
            	    set139=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
            	        input.consume();
            	        adaptor.addChild(root_0, 
            	        (Object)adaptor.create(set139)
            	        );
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_negated_predicate_in_or_predicate2987);
            	    n2=negated_predicate(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, n2.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
            	        try {
            	            if(null == lor) {
            	                lor = this.interp.logicalOr((n1!=null?n1.p:null), (n2!=null?n2.p:null));
            	            } else {
            	                lor = this.interp.logicalOr(lor, (n2!=null?n2.p:null)); 
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
            } while (true);



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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "negated_predicate"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1592:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
    public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
        EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set140=null;
        EugeneParser.predicate_return c =null;


        Object set140_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1594:2: ( ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1594:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1594:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==LC_NOT||LA51_0==LOG_NOT||LA51_0==UC_NOT) ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1594:5: ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer]
                    {
                    set140=(Token)input.LT(1);

                    if ( input.LA(1)==LC_NOT||input.LA(1)==LOG_NOT||input.LA(1)==UC_NOT ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set140)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_predicate_in_negated_predicate3025);
                    c=predicate(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, c.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            retval.p = this.interp.negate((c!=null?c.p:null));
                        } catch(Exception e) {
                            printError(e.getMessage());
                        }
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1603:4: c= predicate[defer]
                    {
                    pushFollow(FOLLOW_predicate_in_negated_predicate3035);
                    c=predicate(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, c.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.p = (c!=null?c.p:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "predicate"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1610:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
    public final EugeneParser.predicate_return predicate(boolean defer) throws RecognitionException {
        EugeneParser.predicate_return retval = new EugeneParser.predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        EugeneParser.operand_return lhs =null;

        EugeneParser.ruleOperator_return op =null;

        EugeneParser.operand_return rhs =null;

        EugeneParser.expressionRule_return exp =null;


        Object i_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1612:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 5, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 7, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 8, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 9, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 10, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 11, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 12, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 13, input);

                        throw nvae;

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
                    NoViableAltException nvae =
                        new NoViableAltException("", 54, 1, input);

                    throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 5, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 7, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 8, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 9, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 10, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 11, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 12, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 13, input);

                        throw nvae;

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
                    NoViableAltException nvae =
                        new NoViableAltException("", 54, 2, input);

                    throw nvae;

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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1612:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1612:4: (lhs= operand[defer] )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==ID||LA52_0==LEFTSBR||LA52_0==NUMBER) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1612:5: lhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate3062);
                            lhs=operand(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lhs.getTree());


                            addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_ruleOperator_in_predicate3072);
                    op=ruleOperator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, op.getTree());


                    addToken((op!=null?input.toString(op.start,op.stop):null));	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1616:5: (rhs= operand[defer] )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==ID||LA53_0==LEFTSBR||LA53_0==NUMBER) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1616:6: rhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate3081);
                            rhs=operand(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, rhs.getTree());


                            addToken((rhs!=null?input.toString(rhs.start,rhs.stop):null));	
                            	

                            }
                            break;

                    }




                    try {
                        retval.p = this.interp.createPredicate((lhs!=null?lhs.o:null), (op!=null?input.toString(op.start,op.stop):null), (rhs!=null?rhs.o:null));
                    //    retval.p = this.interp.createPredicate(this.tokens);
                    } catch(EugeneException ee) {
                        printError(ee.getMessage());
                    }

                    // reset the global token array
                    this.tokens = null;
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1630:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_predicate3095); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1647:4: exp= expressionRule[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expressionRule_in_predicate3104);
                    exp=expressionRule(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.p = (exp!=null?exp.p:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operand"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1654:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' ) ;
    public final EugeneParser.operand_return operand(boolean defer) throws RecognitionException {
        EugeneParser.operand_return retval = new EugeneParser.operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token n=null;
        Token char_literal141=null;
        Token char_literal142=null;

        Object i_tree=null;
        Object n_tree=null;
        Object char_literal141_tree=null;
        Object char_literal142_tree=null;


        NamedElement element = null;
        int constant = -1;
        int index = -1;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1661:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1661:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1661:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1661:5: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_operand3135); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1673:4: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3144); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        constant = Integer.parseInt((n!=null?n.getText():null));
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1678:4: '[' n= NUMBER ']'
                    {
                    char_literal141=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand3151); 
                    char_literal141_tree = 
                    (Object)adaptor.create(char_literal141)
                    ;
                    adaptor.addChild(root_0, char_literal141_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3155); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal142=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand3157); 
                    char_literal142_tree = 
                    (Object)adaptor.create(char_literal142)
                    ;
                    adaptor.addChild(root_0, char_literal142_tree);



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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expressionRule"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1697:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
    public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
        EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expression_return lhs =null;

        EugeneParser.exp_op_return op =null;

        EugeneParser.expression_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1699:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1699:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expression_in_expressionRule3183);
            lhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_exp_op_in_expressionRule3188);
            op=exp_op(defer);

            state._fsp--;

            adaptor.addChild(root_0, op.getTree());

            pushFollow(FOLLOW_expression_in_expressionRule3193);
            rhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    retval.p = new ExpressionConstraint((lhs!=null?lhs.exp:null), (op!=null?input.toString(op.start,op.stop):null), (rhs!=null?rhs.exp:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1710:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
    public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
        EugeneParser.expression_return retval = new EugeneParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTP143=null;
        Token RIGHTP145=null;
        EugeneParser.exp_operand_return lhs =null;

        EugeneParser.exp_operator_return expop =null;

        EugeneParser.expression_return rhs =null;

        EugeneParser.expression_return expression144 =null;


        Object LEFTP143_tree=null;
        Object RIGHTP145_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1712:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1712:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_exp_operand_in_expression3217);
                    lhs=exp_operand(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.exp = new Expression((lhs!=null?lhs.eop:null), null, null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1716:4: (expop= exp_operator[defer] rhs= expression[defer] )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==DIV||LA56_0==MINUS||LA56_0==MULT||LA56_0==PLUS) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1716:6: expop= exp_operator[defer] rhs= expression[defer]
                            {
                            pushFollow(FOLLOW_exp_operator_in_expression3226);
                            expop=exp_operator(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, expop.getTree());

                            pushFollow(FOLLOW_expression_in_expression3231);
                            rhs=expression(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, rhs.getTree());


                            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                                retval.exp = new Expression((lhs!=null?lhs.eop:null), (expop!=null?expop.op:null), (rhs!=null?rhs.exp:null));
                            }	
                            	

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1721:4: LEFTP expression[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTP143=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3243); 
                    LEFTP143_tree = 
                    (Object)adaptor.create(LEFTP143)
                    ;
                    adaptor.addChild(root_0, LEFTP143_tree);


                    pushFollow(FOLLOW_expression_in_expression3245);
                    expression144=expression(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expression144.getTree());

                    RIGHTP145=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3248); 
                    RIGHTP145_tree = 
                    (Object)adaptor.create(RIGHTP145)
                    ;
                    adaptor.addChild(root_0, RIGHTP145_tree);



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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exp_operator"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1728:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
    public final EugeneParser.exp_operator_return exp_operator(boolean defer) throws RecognitionException {
        EugeneParser.exp_operator_return retval = new EugeneParser.exp_operator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLUS146=null;
        Token MINUS147=null;
        Token MULT148=null;
        Token DIV149=null;

        Object PLUS146_tree=null;
        Object MINUS147_tree=null;
        Object MULT148_tree=null;
        Object DIV149_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1730:2: ( PLUS | MINUS | MULT | DIV )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1730:4: PLUS
                    {
                    root_0 = (Object)adaptor.nil();


                    PLUS146=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3267); 
                    PLUS146_tree = 
                    (Object)adaptor.create(PLUS146)
                    ;
                    adaptor.addChild(root_0, PLUS146_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
                        retval.op = Expression.ExpOp.PLUS;	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1735:4: MINUS
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS147=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3275); 
                    MINUS147_tree = 
                    (Object)adaptor.create(MINUS147)
                    ;
                    adaptor.addChild(root_0, MINUS147_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
                        retval.op = Expression.ExpOp.MINUS;	
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1740:4: MULT
                    {
                    root_0 = (Object)adaptor.nil();


                    MULT148=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3282); 
                    MULT148_tree = 
                    (Object)adaptor.create(MULT148)
                    ;
                    adaptor.addChild(root_0, MULT148_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
                        retval.op = Expression.ExpOp.MULT;	
                    }
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1745:4: DIV
                    {
                    root_0 = (Object)adaptor.nil();


                    DIV149=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3289); 
                    DIV149_tree = 
                    (Object)adaptor.create(DIV149)
                    ;
                    adaptor.addChild(root_0, DIV149_tree);



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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exp_operand"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1752:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
    public final EugeneParser.exp_operand_return exp_operand(boolean defer) throws RecognitionException {
        EugeneParser.exp_operand_return retval = new EugeneParser.exp_operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i1=null;
        Token i2=null;
        Token n=null;
        Token r=null;
        Token s=null;
        Token DOT150=null;
        Token LEFTSBR151=null;
        Token RIGHTSBR152=null;
        Token MINUS153=null;
        Token MINUS154=null;

        Object i1_tree=null;
        Object i2_tree=null;
        Object n_tree=null;
        Object r_tree=null;
        Object s_tree=null;
        Object DOT150_tree=null;
        Object LEFTSBR151_tree=null;
        Object RIGHTSBR152_tree=null;
        Object MINUS153_tree=null;
        Object MINUS154_tree=null;


        List<NamedElement> elements = null;
        NamedElement ne = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1758:2: ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 3, input);

                    throw nvae;

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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1758:4: (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )*
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1758:4: (i1= ID DOT )*
                    loop59:
                    do {
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
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1758:5: i1= ID DOT
                    	    {
                    	    i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3319); 
                    	    i1_tree = 
                    	    (Object)adaptor.create(i1)
                    	    ;
                    	    adaptor.addChild(root_0, i1_tree);


                    	    DOT150=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3321); 
                    	    DOT150_tree = 
                    	    (Object)adaptor.create(DOT150)
                    	    ;
                    	    adaptor.addChild(root_0, DOT150_tree);



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
                    } while (true);


                    i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3330); 
                    i2_tree = 
                    (Object)adaptor.create(i2)
                    ;
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
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1826:5: ( LEFTSBR n= NUMBER RIGHTSBR )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==LEFTSBR) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1826:6: LEFTSBR n= NUMBER RIGHTSBR
                    	    {
                    	    LEFTSBR151=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3336); 
                    	    LEFTSBR151_tree = 
                    	    (Object)adaptor.create(LEFTSBR151)
                    	    ;
                    	    adaptor.addChild(root_0, LEFTSBR151_tree);


                    	    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3340); 
                    	    n_tree = 
                    	    (Object)adaptor.create(n)
                    	    ;
                    	    adaptor.addChild(root_0, n_tree);


                    	    RIGHTSBR152=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3342); 
                    	    RIGHTSBR152_tree = 
                    	    (Object)adaptor.create(RIGHTSBR152)
                    	    ;
                    	    adaptor.addChild(root_0, RIGHTSBR152_tree);



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
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1837:4: n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3354); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((n!=null?n.getText():null));
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1844:4: MINUS n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS153=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3361); 
                    MINUS153_tree = 
                    (Object)adaptor.create(MINUS153)
                    ;
                    adaptor.addChild(root_0, MINUS153_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3365); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((n!=null?n.getText():null)) * -1;
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1851:4: r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3374); 
                    r_tree = 
                    (Object)adaptor.create(r)
                    ;
                    adaptor.addChild(root_0, r_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((r!=null?r.getText():null));
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1858:4: MINUS r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS154=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3381); 
                    MINUS154_tree = 
                    (Object)adaptor.create(MINUS154)
                    ;
                    adaptor.addChild(root_0, MINUS154_tree);


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3385); 
                    r_tree = 
                    (Object)adaptor.create(r)
                    ;
                    adaptor.addChild(root_0, r_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((r!=null?r.getText():null)) * -1.0;
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1865:4: s= STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3394); 
                    s_tree = 
                    (Object)adaptor.create(s)
                    ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "regexp"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1875:1: regexp[boolean defer] :;
    public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
        EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1876:2: ()
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1877:2: 
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exp_op"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1879:1: exp_op[boolean defer] : relationalOperators ;
    public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
        EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.relationalOperators_return relationalOperators155 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1880:2: ( relationalOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1880:4: relationalOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_relationalOperators_in_exp_op3421);
            relationalOperators155=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, relationalOperators155.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "grammarDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1888:1: grammarDeclaration[boolean defer] : GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP ;
    public final EugeneParser.grammarDeclaration_return grammarDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.grammarDeclaration_return retval = new EugeneParser.grammarDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token GRAMMAR156=null;
        Token LEFTP157=null;
        Token RIGHTP159=null;
        EugeneParser.list_of_production_rules_return list_of_production_rules158 =null;


        Object n_tree=null;
        Object GRAMMAR156_tree=null;
        Object LEFTP157_tree=null;
        Object RIGHTP159_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1889:2: ( GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1890:3: GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            GRAMMAR156=(Token)match(input,GRAMMAR,FOLLOW_GRAMMAR_in_grammarDeclaration3440); 
            GRAMMAR156_tree = 
            (Object)adaptor.create(GRAMMAR156)
            ;
            adaptor.addChild(root_0, GRAMMAR156_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_grammarDeclaration3444); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            LEFTP157=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_grammarDeclaration3446); 
            LEFTP157_tree = 
            (Object)adaptor.create(LEFTP157)
            ;
            adaptor.addChild(root_0, LEFTP157_tree);


            pushFollow(FOLLOW_list_of_production_rules_in_grammarDeclaration3448);
            list_of_production_rules158=list_of_production_rules(defer);

            state._fsp--;

            adaptor.addChild(root_0, list_of_production_rules158.getTree());

            RIGHTP159=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_grammarDeclaration3451); 
            RIGHTP159_tree = 
            (Object)adaptor.create(RIGHTP159)
            ;
            adaptor.addChild(root_0, RIGHTP159_tree);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list_of_production_rules"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1893:1: list_of_production_rules[boolean defer] : production_rule[defer] SEMIC ( list_of_production_rules[defer] )? ;
    public final EugeneParser.list_of_production_rules_return list_of_production_rules(boolean defer) throws RecognitionException {
        EugeneParser.list_of_production_rules_return retval = new EugeneParser.list_of_production_rules_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMIC161=null;
        EugeneParser.production_rule_return production_rule160 =null;

        EugeneParser.list_of_production_rules_return list_of_production_rules162 =null;


        Object SEMIC161_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1894:2: ( production_rule[defer] SEMIC ( list_of_production_rules[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1894:4: production_rule[defer] SEMIC ( list_of_production_rules[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_production_rule_in_list_of_production_rules3463);
            production_rule160=production_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, production_rule160.getTree());

            SEMIC161=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_list_of_production_rules3466); 
            SEMIC161_tree = 
            (Object)adaptor.create(SEMIC161)
            ;
            adaptor.addChild(root_0, SEMIC161_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1894:33: ( list_of_production_rules[defer] )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==ID) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1894:34: list_of_production_rules[defer]
                    {
                    pushFollow(FOLLOW_list_of_production_rules_in_list_of_production_rules3469);
                    list_of_production_rules162=list_of_production_rules(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list_of_production_rules162.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "production_rule"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1897:1: production_rule[boolean defer] : lhs= ID ARROW right_hand_side[defer] ;
    public final EugeneParser.production_rule_return production_rule(boolean defer) throws RecognitionException {
        EugeneParser.production_rule_return retval = new EugeneParser.production_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs=null;
        Token ARROW163=null;
        EugeneParser.right_hand_side_return right_hand_side164 =null;


        Object lhs_tree=null;
        Object ARROW163_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1898:2: (lhs= ID ARROW right_hand_side[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1898:4: lhs= ID ARROW right_hand_side[defer]
            {
            root_0 = (Object)adaptor.nil();


            lhs=(Token)match(input,ID,FOLLOW_ID_in_production_rule3489); 
            lhs_tree = 
            (Object)adaptor.create(lhs)
            ;
            adaptor.addChild(root_0, lhs_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                // ID denotes a non-terminal of the grammar
            }	
            	

            ARROW163=(Token)match(input,ARROW,FOLLOW_ARROW_in_production_rule3493); 
            ARROW163_tree = 
            (Object)adaptor.create(ARROW163)
            ;
            adaptor.addChild(root_0, ARROW163_tree);


            pushFollow(FOLLOW_right_hand_side_in_production_rule3495);
            right_hand_side164=right_hand_side(defer);

            state._fsp--;

            adaptor.addChild(root_0, right_hand_side164.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "right_hand_side"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1905:1: right_hand_side[boolean defer] : (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] );
    public final EugeneParser.right_hand_side_return right_hand_side(boolean defer) throws RecognitionException {
        EugeneParser.right_hand_side_return retval = new EugeneParser.right_hand_side_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token COMMA165=null;
        EugeneParser.right_hand_side_return right_hand_side166 =null;

        EugeneParser.interaction_return interaction167 =null;


        Object i_tree=null;
        Object COMMA165_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1906:2: (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 64, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;

            }
            switch (alt64) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1906:4: i= ID ( COMMA right_hand_side[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_right_hand_side3511); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        // ID must be either a terminal (i.e. a part)
                        // or a non-terminal defined within the grammar
                    }	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1911:4: ( COMMA right_hand_side[defer] )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==COMMA) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1911:5: COMMA right_hand_side[defer]
                            {
                            COMMA165=(Token)match(input,COMMA,FOLLOW_COMMA_in_right_hand_side3516); 
                            COMMA165_tree = 
                            (Object)adaptor.create(COMMA165)
                            ;
                            adaptor.addChild(root_0, COMMA165_tree);


                            pushFollow(FOLLOW_right_hand_side_in_right_hand_side3518);
                            right_hand_side166=right_hand_side(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, right_hand_side166.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1912:4: interaction[defer, \"some_string\"]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_right_hand_side3526);
                    interaction167=interaction(defer, "some_string");

                    state._fsp--;

                    adaptor.addChild(root_0, interaction167.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interactionDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1919:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
    public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token INTERACTION168=null;
        Token LEFTP169=null;
        Token RIGHTP170=null;
        EugeneParser.interaction_return i1 =null;

        EugeneParser.interaction_return i2 =null;


        Object name_tree=null;
        Object INTERACTION168_tree=null;
        Object LEFTP169_tree=null;
        Object RIGHTP170_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1921:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1921:4: i1= interaction[defer, null]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3551);
                    i1=interaction(defer, null);

                    state._fsp--;

                    adaptor.addChild(root_0, i1.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.ia = (i1!=null?i1.ia:null);
                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1926:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    INTERACTION168=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3559); 
                    INTERACTION168_tree = 
                    (Object)adaptor.create(INTERACTION168)
                    ;
                    adaptor.addChild(root_0, INTERACTION168_tree);


                    name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3563); 
                    name_tree = 
                    (Object)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    LEFTP169=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3565); 
                    LEFTP169_tree = 
                    (Object)adaptor.create(LEFTP169)
                    ;
                    adaptor.addChild(root_0, LEFTP169_tree);


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3569);
                    i2=interaction(defer, (name!=null?name.getText():null));

                    state._fsp--;

                    adaptor.addChild(root_0, i2.getTree());

                    RIGHTP170=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3572); 
                    RIGHTP170_tree = 
                    (Object)adaptor.create(RIGHTP170)
                    ;
                    adaptor.addChild(root_0, RIGHTP170_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.ia = (i2!=null?i2.ia:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interaction"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1933:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
    public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
        EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs1=null;
        Token rhs1=null;
        Token lhs2=null;
        Token LEFTP171=null;
        Token RIGHTP172=null;
        EugeneParser.interactionType_return t1 =null;

        EugeneParser.interactionType_return t2 =null;

        EugeneParser.interaction_return rhs2 =null;


        Object lhs1_tree=null;
        Object rhs1_tree=null;
        Object lhs2_tree=null;
        Object LEFTP171_tree=null;
        Object RIGHTP172_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1935:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
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
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 2, input);

                        throw nvae;

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
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 3, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 66, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }
            switch (alt66) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1935:4: lhs1= ID t1= interactionType[defer] rhs1= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3595); 
                    lhs1_tree = 
                    (Object)adaptor.create(lhs1)
                    ;
                    adaptor.addChild(root_0, lhs1_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3599);
                    t1=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t1.getTree());

                    rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3604); 
                    rhs1_tree = 
                    (Object)adaptor.create(rhs1)
                    ;
                    adaptor.addChild(root_0, rhs1_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            retval.ia = this.interp.createInteraction(name, (lhs1!=null?lhs1.getText():null), (t1!=null?t1.type:null), (rhs1!=null?rhs1.getText():null));
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1944:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3613); 
                    lhs2_tree = 
                    (Object)adaptor.create(lhs2)
                    ;
                    adaptor.addChild(root_0, lhs2_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3617);
                    t2=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t2.getTree());

                    LEFTP171=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3620); 
                    LEFTP171_tree = 
                    (Object)adaptor.create(LEFTP171)
                    ;
                    adaptor.addChild(root_0, LEFTP171_tree);


                    pushFollow(FOLLOW_interaction_in_interaction3624);
                    rhs2=interaction(defer, name);

                    state._fsp--;

                    adaptor.addChild(root_0, rhs2.getTree());

                    RIGHTP172=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3627); 
                    RIGHTP172_tree = 
                    (Object)adaptor.create(RIGHTP172)
                    ;
                    adaptor.addChild(root_0, RIGHTP172_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            retval.ia = this.interp.createInteraction(name, (lhs2!=null?lhs2.getText():null), (t2!=null?t2.type:null), (rhs2!=null?rhs2.ia:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interactionType"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1955:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
    public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
        EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set173=null;
        Token set174=null;

        Object set173_tree=null;
        Object set174_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1957:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1957:4: ( UC_REPRESSES | LC_REPRESSES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set173=(Token)input.LT(1);

                    if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set173)
                        );
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1962:4: ( UC_INDUCES | LC_INDUCES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set174=(Token)input.LT(1);

                    if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set174)
                        );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "printStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1974:1: printStatement[boolean defer] : ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP );
    public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
        EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set175=null;
        Token LEFTP176=null;
        Token RIGHTP177=null;
        Token set178=null;
        Token LEFTP179=null;
        Token RIGHTP180=null;
        EugeneParser.toPrint_return tp =null;


        Object set175_tree=null;
        Object LEFTP176_tree=null;
        Object RIGHTP177_tree=null;
        Object set178_tree=null;
        Object LEFTP179_tree=null;
        Object RIGHTP180_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1975:2: ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1975:4: ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set175=(Token)input.LT(1);

                    if ( (input.LA(1) >= PRINTLN_LC && input.LA(1) <= PRINTLN_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set175)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP176=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3692); 
                    LEFTP176_tree = 
                    (Object)adaptor.create(LEFTP176)
                    ;
                    adaptor.addChild(root_0, LEFTP176_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3696);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP177=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3699); 
                    RIGHTP177_tree = 
                    (Object)adaptor.create(RIGHTP177)
                    ;
                    adaptor.addChild(root_0, RIGHTP177_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        if(null != (tp!=null?tp.sb:null)) {
                            try {
                                this.writer.write((tp!=null?tp.sb:null).toString());
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1988:4: ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set178=(Token)input.LT(1);

                    if ( (input.LA(1) >= PRINT_LC && input.LA(1) <= PRINT_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set178)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP179=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3712); 
                    LEFTP179_tree = 
                    (Object)adaptor.create(LEFTP179)
                    ;
                    adaptor.addChild(root_0, LEFTP179_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3716);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP180=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3719); 
                    RIGHTP180_tree = 
                    (Object)adaptor.create(RIGHTP180)
                    ;
                    adaptor.addChild(root_0, RIGHTP180_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        if(null != (tp!=null?tp.sb:null)) {
                            try {
                                this.writer.write((tp!=null?tp.sb:null).toString());
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "toPrint"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2002:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
    public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return exp =null;

        EugeneParser.toPrint_prime_return tpp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2004:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2004:4: exp= expr[defer] tpp= toPrint_prime[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_toPrint3740);
            exp=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, exp.getTree());

            pushFollow(FOLLOW_toPrint_prime_in_toPrint3745);
            tpp=toPrint_prime(defer);

            state._fsp--;

            adaptor.addChild(root_0, tpp.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                retval.sb = new StringBuilder();
                if(null != (exp!=null?exp.element:null)) {
                    retval.sb.append((exp!=null?exp.element:null));
                } else {
                    retval.sb.append((exp!=null?exp.p:null));
                }
                retval.sb.append((tpp!=null?tpp.sb:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "toPrint_prime"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2017:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
    public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA181=null;
        EugeneParser.toPrint_return tp =null;


        Object COMMA181_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2019:2: (| COMMA tp= toPrint[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2019:4: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.sb = new StringBuilder();
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2024:4: COMMA tp= toPrint[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    COMMA181=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime3771); 
                    COMMA181_tree = 
                    (Object)adaptor.create(COMMA181)
                    ;
                    adaptor.addChild(root_0, COMMA181_tree);


                    pushFollow(FOLLOW_toPrint_in_toPrint_prime3775);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.sb = new StringBuilder();
                        retval.sb.append((tp!=null?tp.sb:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "imperativeStatements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2036:1: imperativeStatements[boolean defer] : ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] );
    public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException {
        EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ifStatement_return ifStatement182 =null;

        EugeneParser.forall_iterator_return forall_iterator183 =null;

        EugeneParser.for_loop_return for_loop184 =null;

        EugeneParser.while_loop_return while_loop185 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2037:2: ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2037:4: ifStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifStatement_in_imperativeStatements3795);
                    ifStatement182=ifStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ifStatement182.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2038:4: forall_iterator[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_forall_iterator_in_imperativeStatements3801);
                    forall_iterator183=forall_iterator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, forall_iterator183.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2039:4: for_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_for_loop_in_imperativeStatements3807);
                    for_loop184=for_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, for_loop184.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2040:4: while_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_while_loop_in_imperativeStatements3813);
                    while_loop185=while_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, while_loop185.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ifStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2043:1: ifStatement[boolean defer] : ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? ;
    public final EugeneParser.ifStatement_return ifStatement(boolean defer) throws RecognitionException {
        EugeneParser.ifStatement_return retval = new EugeneParser.ifStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set186=null;
        Token LEFTP187=null;
        Token RIGHTP188=null;
        Token LEFTCUR189=null;
        Token RIGHTCUR190=null;
        Token set191=null;
        Token LEFTP192=null;
        Token RIGHTP193=null;
        Token LEFTCUR194=null;
        Token RIGHTCUR195=null;
        Token set196=null;
        Token LEFTCUR197=null;
        Token RIGHTCUR198=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set186_tree=null;
        Object LEFTP187_tree=null;
        Object RIGHTP188_tree=null;
        Object LEFTCUR189_tree=null;
        Object RIGHTCUR190_tree=null;
        Object set191_tree=null;
        Object LEFTP192_tree=null;
        Object RIGHTP193_tree=null;
        Object LEFTCUR194_tree=null;
        Object RIGHTCUR195_tree=null;
        Object set196_tree=null;
        Object LEFTCUR197_tree=null;
        Object RIGHTCUR198_tree=null;


        boolean bExecuted = false;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2047:2: ( ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2051:3: ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            {
            root_0 = (Object)adaptor.nil();


            set186=(Token)input.LT(1);

            if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set186)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP187=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3845); 
            LEFTP187_tree = 
            (Object)adaptor.create(LEFTP187)
            ;
            adaptor.addChild(root_0, LEFTP187_tree);


            pushFollow(FOLLOW_imp_condition_in_ifStatement3849);
            co=imp_condition(defer);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP188=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3852); 
            RIGHTP188_tree = 
            (Object)adaptor.create(RIGHTP188)
            ;
            adaptor.addChild(root_0, RIGHTP188_tree);


            LEFTCUR189=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3854); 
            LEFTCUR189_tree = 
            (Object)adaptor.create(LEFTCUR189)
            ;
            adaptor.addChild(root_0, LEFTCUR189_tree);


            pushFollow(FOLLOW_listOfStatements_in_ifStatement3862);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR190=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3865); 
            RIGHTCUR190_tree = 
            (Object)adaptor.create(RIGHTCUR190)
            ;
            adaptor.addChild(root_0, RIGHTCUR190_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                // evaluate the condition
                if((co!=null?co.bTrue:false)) {
                    // if true => execute the statements
                    // and ignore the rest of the ifStatement
                    
                    try {
                        this.exec("listOfStatements", (stmts!=null?((Token)stmts.start):null).getTokenIndex());
                    } catch(EugeneReturnException ere) {
                        // TODO!
                    } catch(EugeneException ee) {
                        printError(ee.getLocalizedMessage());
                    }
                    bExecuted = true;
                }
            }			
            		

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2074:3: ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==LC_ELSEIF||LA71_0==UC_ELSEIF) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2074:5: ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            	    {
            	    set191=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
            	        input.consume();
            	        adaptor.addChild(root_0, 
            	        (Object)adaptor.create(set191)
            	        );
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    LEFTP192=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3886); 
            	    LEFTP192_tree = 
            	    (Object)adaptor.create(LEFTP192)
            	    ;
            	    adaptor.addChild(root_0, LEFTP192_tree);


            	    pushFollow(FOLLOW_imp_condition_in_ifStatement3890);
            	    co=imp_condition(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, co.getTree());

            	    RIGHTP193=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3893); 
            	    RIGHTP193_tree = 
            	    (Object)adaptor.create(RIGHTP193)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP193_tree);


            	    LEFTCUR194=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3895); 
            	    LEFTCUR194_tree = 
            	    (Object)adaptor.create(LEFTCUR194)
            	    ;
            	    adaptor.addChild(root_0, LEFTCUR194_tree);


            	    pushFollow(FOLLOW_listOfStatements_in_ifStatement3903);
            	    stmts=listOfStatements(true);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmts.getTree());

            	    RIGHTCUR195=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3906); 
            	    RIGHTCUR195_tree = 
            	    (Object)adaptor.create(RIGHTCUR195)
            	    ;
            	    adaptor.addChild(root_0, RIGHTCUR195_tree);



            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
            	        // evaluate the condition
            	        if((co!=null?co.bTrue:false)) {
            	            // if true => execute the statements
            	            // and ignore the rest of the ifStatement
            	            try {        
            	                this.exec("listOfStatements", (stmts!=null?((Token)stmts.start):null).getTokenIndex());
            	            } catch(EugeneReturnException ere) {
            	                // TODO!
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
            } while (true);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2097:3: ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==LC_ELSE||LA72_0==UC_ELSE) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2097:4: ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR
                    {
                    set196=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set196)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTCUR197=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3928); 
                    LEFTCUR197_tree = 
                    (Object)adaptor.create(LEFTCUR197)
                    ;
                    adaptor.addChild(root_0, LEFTCUR197_tree);


                    pushFollow(FOLLOW_listOfStatements_in_ifStatement3936);
                    stmts=listOfStatements(true);

                    state._fsp--;

                    adaptor.addChild(root_0, stmts.getTree());

                    RIGHTCUR198=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3939); 
                    RIGHTCUR198_tree = 
                    (Object)adaptor.create(RIGHTCUR198)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR198_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
                        try {
                            this.exec("listOfStatements", (stmts!=null?((Token)stmts.start):null).getTokenIndex());        
                        } catch(EugeneReturnException ere) {
                            // TODO!
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


    public static class imp_condition_return extends ParserRuleReturnScope {
        public boolean bTrue;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "imp_condition"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2113:1: imp_condition[boolean defer] returns [boolean bTrue] : lhs= expr[defer] ro= relationalOperators rhs= expr[defer] ;
    public final EugeneParser.imp_condition_return imp_condition(boolean defer) throws RecognitionException {
        EugeneParser.imp_condition_return retval = new EugeneParser.imp_condition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return lhs =null;

        EugeneParser.relationalOperators_return ro =null;

        EugeneParser.expr_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2115:2: (lhs= expr[defer] ro= relationalOperators rhs= expr[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2115:4: lhs= expr[defer] ro= relationalOperators rhs= expr[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_imp_condition3963);
            lhs=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_relationalOperators_in_imp_condition3968);
            ro=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, ro.getTree());

            pushFollow(FOLLOW_expr_in_imp_condition3972);
            rhs=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {


                    if(null != (lhs!=null?lhs.element:null)) {
                        if(null != (rhs!=null?rhs.element:null)) {
                            // comparing two NamedElements against each other
                            // e.g. p.prop = q.prop
                            retval.bTrue = this.interp.evaluateCondition(
                                         (lhs!=null?lhs.element:null), 
                                         (ro!=null?input.toString(ro.start,ro.stop):null), 
                                         (rhs!=null?rhs.element:null));
                        } else if(null != (rhs!=null?rhs.p:null)) {
                            // comparing a LHS NamedElement against a Variable
                            // that could be either a variable or constant
                            // e.g. p.prop = i
                            retval.bTrue = this.interp.evaluateCondition(
                                         (lhs!=null?lhs.element:null), 
                                         (ro!=null?input.toString(ro.start,ro.stop):null), 
                                         (rhs!=null?rhs.p:null));
                        }
                    } else {
                    
                        if(null != (rhs!=null?rhs.element:null)) {
                            // comparing a LHS variable against a RHS 
                            // NamedElement
                            // e.g. i == q.prop
                            retval.bTrue = this.interp.evaluateCondition(
                                         (lhs!=null?lhs.p:null), 
                                         (ro!=null?input.toString(ro.start,ro.stop):null), 
                                         (rhs!=null?rhs.element:null));
                        } else if(null != (rhs!=null?rhs.p:null)) {
                            // comparing a LHS Variable against a Variable
                            // that could be either a variable or constant
                            // e.g. i == j
                            retval.bTrue = this.interp.evaluateCondition(
                                         (lhs!=null?lhs.p:null), 
                                         (ro!=null?input.toString(ro.start,ro.stop):null), 
                                         (rhs!=null?rhs.p:null));
                        } else {
                            throw new EugeneException("Invalid condition!");
                        }
                    }
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
    // $ANTLR end "imp_condition"


    public static class forall_iterator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "forall_iterator"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2166:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR ;
    public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException {
        EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token it=null;
        Token i=null;
        Token set199=null;
        Token COLON200=null;
        Token LEFTCUR201=null;
        Token RIGHTCUR203=null;
        EugeneParser.listOfStatements_return listOfStatements202 =null;


        Object it_tree=null;
        Object i_tree=null;
        Object set199_tree=null;
        Object COLON200_tree=null;
        Object LEFTCUR201_tree=null;
        Object RIGHTCUR203_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2167:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2167:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set199=(Token)input.LT(1);

            if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set199)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2167:26: (it= ID COLON )?
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2167:27: it= ID COLON
                    {
                    it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3997); 
                    it_tree = 
                    (Object)adaptor.create(it)
                    ;
                    adaptor.addChild(root_0, it_tree);


                    COLON200=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator3999); 
                    COLON200_tree = 
                    (Object)adaptor.create(COLON200)
                    ;
                    adaptor.addChild(root_0, COLON200_tree);


                    }
                    break;

            }


            i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator4005); 
            i_tree = 
            (Object)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            LEFTCUR201=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator4007); 
            LEFTCUR201_tree = 
            (Object)adaptor.create(LEFTCUR201)
            ;
            adaptor.addChild(root_0, LEFTCUR201_tree);


            pushFollow(FOLLOW_listOfStatements_in_forall_iterator4012);
            listOfStatements202=listOfStatements(defer);

            state._fsp--;

            adaptor.addChild(root_0, listOfStatements202.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

            }			
            	

            RIGHTCUR203=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator4019); 
            RIGHTCUR203_tree = 
            (Object)adaptor.create(RIGHTCUR203)
            ;
            adaptor.addChild(root_0, RIGHTCUR203_tree);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "for_loop"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2176:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException {
        EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set204=null;
        Token LEFTP205=null;
        Token SEMIC206=null;
        Token SEMIC207=null;
        Token RIGHTP208=null;
        Token LEFTCUR209=null;
        Token RIGHTCUR210=null;
        EugeneParser.variableDeclaration_return ds =null;

        EugeneParser.imp_condition_return co =null;

        EugeneParser.assignment_return as =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set204_tree=null;
        Object LEFTP205_tree=null;
        Object SEMIC206_tree=null;
        Object SEMIC207_tree=null;
        Object RIGHTP208_tree=null;
        Object LEFTCUR209_tree=null;
        Object RIGHTCUR210_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2177:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2177:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set204=(Token)input.LT(1);

            if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set204)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP205=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop4037); 
            LEFTP205_tree = 
            (Object)adaptor.create(LEFTP205)
            ;
            adaptor.addChild(root_0, LEFTP205_tree);


            pushFollow(FOLLOW_variableDeclaration_in_for_loop4041);
            ds=variableDeclaration(true);

            state._fsp--;

            adaptor.addChild(root_0, ds.getTree());

            SEMIC206=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4044); 
            SEMIC206_tree = 
            (Object)adaptor.create(SEMIC206)
            ;
            adaptor.addChild(root_0, SEMIC206_tree);


            pushFollow(FOLLOW_imp_condition_in_for_loop4048);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            SEMIC207=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4051); 
            SEMIC207_tree = 
            (Object)adaptor.create(SEMIC207)
            ;
            adaptor.addChild(root_0, SEMIC207_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2177:90: (as= assignment[true] )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==ID) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2177:91: as= assignment[true]
                    {
                    pushFollow(FOLLOW_assignment_in_for_loop4056);
                    as=assignment(true);

                    state._fsp--;

                    adaptor.addChild(root_0, as.getTree());

                    }
                    break;

            }


            RIGHTP208=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop4061); 
            RIGHTP208_tree = 
            (Object)adaptor.create(RIGHTP208)
            ;
            adaptor.addChild(root_0, RIGHTP208_tree);


            LEFTCUR209=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop4063); 
            LEFTCUR209_tree = 
            (Object)adaptor.create(LEFTCUR209)
            ;
            adaptor.addChild(root_0, LEFTCUR209_tree);


            pushFollow(FOLLOW_listOfStatements_in_for_loop4071);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    if(null != as) {
                        this.execute_loop(
                            (ds!=null?((Token)ds.start):null),      // init
                            (co!=null?((Token)co.start):null),      // condition
                            (as!=null?((Token)as.start):null),      // increment
                            (stmts!=null?((Token)stmts.start):null));  // loop-body
                    } else {
                        this.execute_loop(
                            (ds!=null?((Token)ds.start):null),      // init
                            (co!=null?((Token)co.start):null),      // condition
                            null,           // increment
                            (stmts!=null?((Token)stmts.start):null));  // loop-body
                    }
                } catch(EugeneReturnException ere) {
                    // TODO!
                } catch(Exception ee) {
                    printError(ee.getLocalizedMessage());
                }
            }			
            		

            RIGHTCUR210=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop4078); 
            RIGHTCUR210_tree = 
            (Object)adaptor.create(RIGHTCUR210)
            ;
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
    // $ANTLR end "for_loop"


    public static class while_loop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "while_loop"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2204:1: while_loop[boolean defer] : ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.while_loop_return while_loop(boolean defer) throws RecognitionException {
        EugeneParser.while_loop_return retval = new EugeneParser.while_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set211=null;
        Token LEFTP212=null;
        Token RIGHTP213=null;
        Token LEFTCUR214=null;
        Token RIGHTCUR215=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set211_tree=null;
        Object LEFTP212_tree=null;
        Object RIGHTP213_tree=null;
        Object LEFTCUR214_tree=null;
        Object RIGHTCUR215_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2205:2: ( ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2205:4: ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set211=(Token)input.LT(1);

            if ( input.LA(1)==LC_WHILE||input.LA(1)==UC_WHILE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set211)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP212=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_while_loop4098); 
            LEFTP212_tree = 
            (Object)adaptor.create(LEFTP212)
            ;
            adaptor.addChild(root_0, LEFTP212_tree);


            pushFollow(FOLLOW_imp_condition_in_while_loop4102);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP213=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_while_loop4105); 
            RIGHTP213_tree = 
            (Object)adaptor.create(RIGHTP213)
            ;
            adaptor.addChild(root_0, RIGHTP213_tree);


            LEFTCUR214=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_while_loop4107); 
            LEFTCUR214_tree = 
            (Object)adaptor.create(LEFTCUR214)
            ;
            adaptor.addChild(root_0, LEFTCUR214_tree);


            pushFollow(FOLLOW_listOfStatements_in_while_loop4115);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    this.execute_loop(
                        null,           // init
                        (co!=null?((Token)co.start):null),      // condition
                        null,           // increment
                        (stmts!=null?((Token)stmts.start):null));  // loop-body
                } catch(EugeneReturnException ere) {
                    // TODO!
                } catch(Exception ee) {
                    printError(ee.getLocalizedMessage());
                }
            }			
            	

            RIGHTCUR215=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_while_loop4122); 
            RIGHTCUR215_tree = 
            (Object)adaptor.create(RIGHTCUR215)
            ;
            adaptor.addChild(root_0, RIGHTCUR215_tree);


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


    public static class expr_return extends ParserRuleReturnScope {
        public Variable p;
        public String instance;
        public int index;
        public String listAddress;
        public Variable primVariable;
        public NamedElement element;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2228:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* ;
    public final EugeneParser.expr_return expr(boolean defer) throws RecognitionException {
        EugeneParser.expr_return retval = new EugeneParser.expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token op=null;
        EugeneParser.multExpr_return e =null;


        Object op_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2230:2: (e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2230:4: e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_expr4146);
            e=multExpr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                if(null != (e!=null?e.p:null)) {
                    retval.p = copyVariable((e!=null?e.p:null));
                
                    retval.instance = (e!=null?e.instance:null);
                    retval.index = (e!=null?e.index:0);
                    if ((e!=null?e.listAddress:null) != null) {
                        retval.listAddress = (e!=null?e.listAddress:null);
                    }
                    retval.primVariable = (e!=null?e.primVariable:null);

                } else if((e!=null?e.element:null) != null && !((e!=null?e.element:null) instanceof Variable) && !((e!=null?e.element:null) instanceof PropertyValue)) { 			
                    retval.element = (e!=null?e.element:null);
                } else {
                    retval.element = null;
                }
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2248:5: (op= ( PLUS | MINUS ) e= multExpr[defer] )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==MINUS||LA75_0==PLUS) ) {
                    alt75=1;
                }


                switch (alt75) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2248:6: op= ( PLUS | MINUS ) e= multExpr[defer]
            	    {
            	    op=(Token)input.LT(1);

            	    if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
            	        input.consume();
            	        adaptor.addChild(root_0, 
            	        (Object)adaptor.create(op)
            	        );
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_multExpr_in_expr4163);
            	    e=multExpr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
            	        try {
            	            if(null != retval.element) {
            	                if(null != (e!=null?e.element:null)) {
            	                    retval.element = this.interp.doMinPlusOp((e!=null?e.element:null), retval.element, (op!=null?op.getText():null));                
            	                } else if(null != (e!=null?e.p:null)) {
            	                    retval.element = this.interp.doMinPlusOp((e!=null?e.p:null), retval.element, (op!=null?op.getText():null));
            	                }
            	                retval.p = null;            
            	            } else {        
            	                if(null != (e!=null?e.element:null)) {
            	                    this.interp.doMinPlusOp((e!=null?e.element:null), retval.p, (op!=null?op.getText():null));
            	                } else {
            	                    this.interp.doMinPlusOp((e!=null?e.p:null), retval.p, (op!=null?op.getText():null)); 
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
            	    break loop75;
                }
            } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multExpr"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2287:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* ;
    public final EugeneParser.multExpr_return multExpr(boolean defer) throws RecognitionException {
        EugeneParser.multExpr_return retval = new EugeneParser.multExpr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token mul=null;
        Token div=null;
        EugeneParser.atom_return e =null;


        Object mul_tree=null;
        Object div_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2289:2: (e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2289:4: e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_multExpr4193);
            e=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                if( null != (e!=null?e.p:null)) {
                    retval.p = copyVariable((e!=null?e.p:null));
                    if ((e!=null?e.instance:null) != null) {
                        retval.instance = (e!=null?e.instance:null);
                    }
                
                    retval.index = (e!=null?e.index:0);
                    if ((e!=null?e.listAddress:null) != null) {
                        retval.listAddress = (e!=null?e.listAddress:null);
                    }
                    retval.primVariable = (e!=null?e.primVariable:null);

                } else if((e!=null?e.element:null) != null && !((e!=null?e.element:null) instanceof Variable) && !((e!=null?e.element:null) instanceof PropertyValue)) { 			
                    retval.element = (e!=null?e.element:null);
                } else {
                    retval.element = null;
                }
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:5: ( (mul= MULT |div= DIV ) e= atom[defer] )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==DIV||LA77_0==MULT) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:7: (mul= MULT |div= DIV ) e= atom[defer]
            	    {
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:7: (mul= MULT |div= DIV )
            	    int alt76=2;
            	    int LA76_0 = input.LA(1);

            	    if ( (LA76_0==MULT) ) {
            	        alt76=1;
            	    }
            	    else if ( (LA76_0==DIV) ) {
            	        alt76=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 76, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt76) {
            	        case 1 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:8: mul= MULT
            	            {
            	            mul=(Token)match(input,MULT,FOLLOW_MULT_in_multExpr4204); 
            	            mul_tree = 
            	            (Object)adaptor.create(mul)
            	            ;
            	            adaptor.addChild(root_0, mul_tree);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:17: div= DIV
            	            {
            	            div=(Token)match(input,DIV,FOLLOW_DIV_in_multExpr4208); 
            	            div_tree = 
            	            (Object)adaptor.create(div)
            	            ;
            	            adaptor.addChild(root_0, div_tree);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_atom_in_multExpr4213);
            	    e=atom(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
            	        try {
            	            if ((mul!=null?mul.getText():null) != null) {
            	                this.interp.doMultDivOp((e!=null?e.p:null), retval.p, (mul!=null?mul.getText():null));
            	            } else {
            	                this.interp.doMultDivOp((e!=null?e.p:null), retval.p, (div!=null?div.getText():null));
            	            }
            	        } catch(EugeneException ee) {
            	            printError(ee.getLocalizedMessage());
            	        }
            	        
            	        retval.element = null;
            	    }
            	    	

            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atom"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2326:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] |fc= function_call[defer] );
    public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
        EugeneParser.atom_return retval = new EugeneParser.atom_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token t=null;
        Token f=null;
        Token MINUS216=null;
        Token STRING217=null;
        Token char_literal218=null;
        Token char_literal220=null;
        Token LEFTSBR221=null;
        Token RIGHTSBR223=null;
        EugeneParser.dynamic_naming_return dn =null;

        EugeneParser.object_access_return oc =null;

        EugeneParser.built_in_function_return bif =null;

        EugeneParser.function_call_return fc =null;

        EugeneParser.expr_return expr219 =null;

        EugeneParser.list_return list222 =null;


        Object n_tree=null;
        Object t_tree=null;
        Object f_tree=null;
        Object MINUS216_tree=null;
        Object STRING217_tree=null;
        Object char_literal218_tree=null;
        Object char_literal220_tree=null;
        Object LEFTSBR221_tree=null;
        Object RIGHTSBR223_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2328:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] |fc= function_call[defer] )
            int alt81=9;
            switch ( input.LA(1) ) {
            case NUMBER:
            case REAL:
                {
                alt81=1;
                }
                break;
            case MINUS:
                {
                alt81=2;
                }
                break;
            case FALSE_LC:
            case FALSE_UC:
            case TRUE_LC:
            case TRUE_UC:
                {
                alt81=3;
                }
                break;
            case ID:
                {
                int LA81_4 = input.LA(2);

                if ( (LA81_4==LEFTP) ) {
                    int LA81_10 = input.LA(3);

                    if ( (LA81_10==RIGHTP) ) {
                        alt81=8;
                    }
                    else if ( (LA81_10==DOLLAR||(LA81_10 >= EXIT_LC && LA81_10 <= EXIT_UC)||(LA81_10 >= FALSE_LC && LA81_10 <= FALSE_UC)||LA81_10==ID||(LA81_10 >= LEFTP && LA81_10 <= LEFTSBR)||LA81_10==MINUS||LA81_10==NUMBER||LA81_10==PERMUTE||LA81_10==PRODUCT||(LA81_10 >= RANDOM_LC && LA81_10 <= REAL)||(LA81_10 >= SAVE_LC && LA81_10 <= SAVE_UC)||(LA81_10 >= SIZEOF_LC && LA81_10 <= STORE_UC)||(LA81_10 >= STRING && LA81_10 <= TRUE_UC)) ) {
                        alt81=9;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 81, 10, input);

                        throw nvae;

                    }
                }
                else if ( (LA81_4==COMMA||LA81_4==DIV||LA81_4==DOT||LA81_4==EQUALS||LA81_4==GEQUAL||LA81_4==GTHAN||(LA81_4 >= LEFTSBR && LA81_4 <= LEQUAL)||(LA81_4 >= LTHAN && LA81_4 <= MINUS)||(LA81_4 >= MULT && LA81_4 <= NEQUAL)||LA81_4==PLUS||(LA81_4 >= RIGHTCUR && LA81_4 <= RIGHTSBR)||LA81_4==SEMIC||LA81_4==133||(LA81_4 >= 135 && LA81_4 <= 136)||LA81_4==139||(LA81_4 >= 142 && LA81_4 <= 143)||LA81_4==145||(LA81_4 >= 158 && LA81_4 <= 159)||LA81_4==172||(LA81_4 >= 174 && LA81_4 <= 175)||LA81_4==178||(LA81_4 >= 181 && LA81_4 <= 182)||LA81_4==184||(LA81_4 >= 197 && LA81_4 <= 198)) ) {
                    alt81=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 4, input);

                    throw nvae;

                }
                }
                break;
            case DOLLAR:
                {
                alt81=4;
                }
                break;
            case STRING:
                {
                alt81=5;
                }
                break;
            case LEFTP:
                {
                alt81=6;
                }
                break;
            case LEFTSBR:
                {
                alt81=7;
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
                alt81=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;

            }

            switch (alt81) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2328:4: (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2328:4: (n= NUMBER |n= REAL )
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==NUMBER) ) {
                        alt78=1;
                    }
                    else if ( (LA78_0==REAL) ) {
                        alt78=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 78, 0, input);

                        throw nvae;

                    }
                    switch (alt78) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2328:5: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4240); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2328:16: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4246); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2337:4: MINUS (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS216=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom4256); 
                    MINUS216_tree = 
                    (Object)adaptor.create(MINUS216)
                    ;
                    adaptor.addChild(root_0, MINUS216_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2337:10: (n= NUMBER |n= REAL )
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==NUMBER) ) {
                        alt79=1;
                    }
                    else if ( (LA79_0==REAL) ) {
                        alt79=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 79, 0, input);

                        throw nvae;

                    }
                    switch (alt79) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2337:11: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4261); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2337:22: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4267); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2346:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2346:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( ((LA80_0 >= TRUE_LC && LA80_0 <= TRUE_UC)) ) {
                        alt80=1;
                    }
                    else if ( ((LA80_0 >= FALSE_LC && LA80_0 <= FALSE_UC)) ) {
                        alt80=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 80, 0, input);

                        throw nvae;

                    }
                    switch (alt80) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2346:5: t= ( TRUE_LC | TRUE_UC )
                            {
                            t=(Token)input.LT(1);

                            if ( (input.LA(1) >= TRUE_LC && input.LA(1) <= TRUE_UC) ) {
                                input.consume();
                                adaptor.addChild(root_0, 
                                (Object)adaptor.create(t)
                                );
                                state.errorRecovery=false;
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2346:27: f= ( FALSE_LC | FALSE_UC )
                            {
                            f=(Token)input.LT(1);

                            if ( (input.LA(1) >= FALSE_LC && input.LA(1) <= FALSE_UC) ) {
                                input.consume();
                                adaptor.addChild(root_0, 
                                (Object)adaptor.create(f)
                                );
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2359:4: dn= dynamic_naming[defer] oc= object_access[defer, $element]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dynamic_naming_in_atom4306);
                    dn=dynamic_naming(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, dn.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            retval.element = this.interp.get((dn!=null?dn.name:null));
                    					
                            if(null != retval.element) {
                                if(retval.element instanceof Variable) {
                                    retval.p = copyVariable((Variable)retval.element);
                                    retval.primVariable = (Variable)retval.element;
                                }
                            } else {
                                throw new EugeneException((dn!=null?dn.name:null) + " is not declared.");
                            }
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }
                    	

                    pushFollow(FOLLOW_object_access_in_atom4313);
                    oc=object_access(defer, retval.element);

                    state._fsp--;

                    adaptor.addChild(root_0, oc.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.element = (oc!=null?oc.child:null);
                        
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2391:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING217=(Token)match(input,STRING,FOLLOW_STRING_in_atom4322); 
                    STRING217_tree = 
                    (Object)adaptor.create(STRING217)
                    ;
                    adaptor.addChild(root_0, STRING217_tree);



                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			retval.p.type = EugeneConstants.TXT;
                    			retval.p.txt = (STRING217!=null?STRING217.getText():null).substring(1, (STRING217!=null?STRING217.getText():null).length()-1);

                    			retval.element = null;
                    		}
                    	

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:4: '(' expr[defer] ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal218=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4330); 
                    char_literal218_tree = 
                    (Object)adaptor.create(char_literal218)
                    ;
                    adaptor.addChild(root_0, char_literal218_tree);


                    pushFollow(FOLLOW_expr_in_atom4332);
                    expr219=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expr219.getTree());

                    char_literal220=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4335); 
                    char_literal220_tree = 
                    (Object)adaptor.create(char_literal220)
                    ;
                    adaptor.addChild(root_0, char_literal220_tree);



                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			retval.p = (expr219!=null?expr219.p:null);
                    			retval.primVariable = (expr219!=null?expr219.primVariable:null);
                    			retval.element = (expr219!=null?expr219.element:null);
                    		}
                    	

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2408:5: LEFTSBR list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR221=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4344); 
                    LEFTSBR221_tree = 
                    (Object)adaptor.create(LEFTSBR221)
                    ;
                    adaptor.addChild(root_0, LEFTSBR221_tree);


                    pushFollow(FOLLOW_list_in_atom4346);
                    list222=list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list222.getTree());

                    RIGHTSBR223=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4349); 
                    RIGHTSBR223_tree = 
                    (Object)adaptor.create(RIGHTSBR223)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR223_tree);



                    		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                    			retval.p = (list222!=null?list222.listPrim:null);
                    			retval.primVariable = (list222!=null?list222.listPrim:null);
                    			typeList="";
                    		}
                    	

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2416:4: bif= built_in_function[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_built_in_function_in_atom4359);
                    bif=built_in_function(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, bif.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && null != (bif!=null?bif.element:null)) {
                        if((bif!=null?bif.element:null) instanceof Variable) {
                            retval.p = (Variable)(bif!=null?bif.element:null);
                            retval.element = null;
                        } else {
                            retval.element = (bif!=null?bif.element:null);
                            retval.p = null;
                        }
                    }		
                    	

                    }
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2427:4: fc= function_call[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_function_call_in_atom4369);
                    fc=function_call(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, fc.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && null != (fc!=null?fc.e:null)) {
                        if((fc!=null?fc.e:null) instanceof Variable) {
                            retval.p = (Variable)(fc!=null?fc.e:null);
                            retval.element = null;
                        } else {
                            retval.element = (fc!=null?fc.e:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2440:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
    public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
        EugeneParser.list_return retval = new EugeneParser.list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA224=null;
        EugeneParser.expr_return str1 =null;

        EugeneParser.expr_return str2 =null;


        Object COMMA224_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2442:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2442:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_list4392);
            str1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, str1.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING){
                if (null != (str1!=null?str1.p:null)) {
                    retval.listPrim = new Variable();
                    if(EugeneConstants.NUM.equals(((str1!=null?str1.p:null)).getType())) {
                        retval.listPrim.type =  EugeneConstants.NUMLIST;
                        typeList = EugeneConstants.NUM;
                    } else if(EugeneConstants.TXT.equals(((str1!=null?str1.p:null)).getType())) {
                        retval.listPrim.type =  EugeneConstants.TXTLIST;
                        typeList = EugeneConstants.TXT;
                    } else {
                        printError("Only arrays of num and txt primitives are supported!");
                    }
                    
                    addToListPrim((str1!=null?str1.p:null), retval.listPrim);
                } else {
                    printError("Only arrays of num and txt primitives are supported!");
                }
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2461:5: ( COMMA str2= expr[defer] )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==COMMA) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2461:6: COMMA str2= expr[defer]
            	    {
            	    COMMA224=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4399); 
            	    COMMA224_tree = 
            	    (Object)adaptor.create(COMMA224)
            	    ;
            	    adaptor.addChild(root_0, COMMA224_tree);


            	    pushFollow(FOLLOW_expr_in_list4403);
            	    str2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, str2.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
            	        if(null != (str2!=null?str2.p:null)) {
            	            addToListPrim((str2!=null?str2.p:null), retval.listPrim);
            	        } else {
            	            printError("Only arrays of num and txt primitives are supported!");
            	        }
            	    }				
            	    	

            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "built_in_function"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2476:1: built_in_function[boolean defer] returns [NamedElement element] : ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP | (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP | ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )? | ID LEFTP RIGHTP );
    public final EugeneParser.built_in_function_return built_in_function(boolean defer) throws RecognitionException {
        EugeneParser.built_in_function_return retval = new EugeneParser.built_in_function_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token pr=null;
        Token pe=null;
        Token idToken=null;
        Token set225=null;
        Token LEFTP226=null;
        Token RIGHTP227=null;
        Token set228=null;
        Token LEFTP229=null;
        Token RIGHTP230=null;
        Token set231=null;
        Token LEFTP232=null;
        Token RIGHTP233=null;
        Token LEFTP234=null;
        Token RIGHTP235=null;
        Token set236=null;
        Token LEFTP237=null;
        Token RIGHTP238=null;
        Token ID239=null;
        Token LEFTP240=null;
        Token RIGHTP241=null;
        EugeneParser.expr_return e =null;

        EugeneParser.range_return rg =null;

        EugeneParser.toPrint_return p =null;


        Object pr_tree=null;
        Object pe_tree=null;
        Object idToken_tree=null;
        Object set225_tree=null;
        Object LEFTP226_tree=null;
        Object RIGHTP227_tree=null;
        Object set228_tree=null;
        Object LEFTP229_tree=null;
        Object RIGHTP230_tree=null;
        Object set231_tree=null;
        Object LEFTP232_tree=null;
        Object RIGHTP233_tree=null;
        Object LEFTP234_tree=null;
        Object RIGHTP235_tree=null;
        Object set236_tree=null;
        Object LEFTP237_tree=null;
        Object RIGHTP238_tree=null;
        Object ID239_tree=null;
        Object LEFTP240_tree=null;
        Object RIGHTP241_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2478:2: ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP | (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP | ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )? | ID LEFTP RIGHTP )
            int alt85=6;
            switch ( input.LA(1) ) {
            case SIZEOF_LC:
            case SIZEOF_UC:
            case SIZE_LC:
            case SIZE_UC:
                {
                alt85=1;
                }
                break;
            case RANDOM_LC:
            case RANDOM_UC:
                {
                alt85=2;
                }
                break;
            case SAVE_LC:
            case SAVE_UC:
            case STORE_LC:
            case STORE_UC:
                {
                alt85=3;
                }
                break;
            case PERMUTE:
            case PRODUCT:
                {
                alt85=4;
                }
                break;
            case EXIT_LC:
            case EXIT_UC:
                {
                alt85=5;
                }
                break;
            case ID:
                {
                alt85=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;

            }

            switch (alt85) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2478:4: ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set225=(Token)input.LT(1);

                    if ( (input.LA(1) >= SIZEOF_LC && input.LA(1) <= SIZE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set225)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP226=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4441); 
                    LEFTP226_tree = 
                    (Object)adaptor.create(LEFTP226)
                    ;
                    adaptor.addChild(root_0, LEFTP226_tree);


                    pushFollow(FOLLOW_expr_in_built_in_function4445);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTP227=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4448); 
                    RIGHTP227_tree = 
                    (Object)adaptor.create(RIGHTP227)
                    ;
                    adaptor.addChild(root_0, RIGHTP227_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            if(null != (e!=null?e.element:null)) {
                                retval.element = this.interp.getSizeOf((e!=null?e.element:null));
                            } else if(null != (e!=null?e.p:null)) {
                                retval.element = this.interp.getSizeOf((e!=null?e.p:null));
                            }
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2491:4: ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set228=(Token)input.LT(1);

                    if ( (input.LA(1) >= RANDOM_LC && input.LA(1) <= RANDOM_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set228)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP229=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4461); 
                    LEFTP229_tree = 
                    (Object)adaptor.create(LEFTP229)
                    ;
                    adaptor.addChild(root_0, LEFTP229_tree);


                    pushFollow(FOLLOW_range_in_built_in_function4465);
                    rg=range(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, rg.getTree());

                    RIGHTP230=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4468); 
                    RIGHTP230_tree = 
                    (Object)adaptor.create(RIGHTP230)
                    ;
                    adaptor.addChild(root_0, RIGHTP230_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try {
                            retval.element = this.interp.getRandom(rg.sor, rg.eor);
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2500:4: ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set231=(Token)input.LT(1);

                    if ( (input.LA(1) >= SAVE_LC && input.LA(1) <= SAVE_UC)||(input.LA(1) >= STORE_LC && input.LA(1) <= STORE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set231)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP232=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4485); 
                    LEFTP232_tree = 
                    (Object)adaptor.create(LEFTP232)
                    ;
                    adaptor.addChild(root_0, LEFTP232_tree);


                    pushFollow(FOLLOW_expr_in_built_in_function4489);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTP233=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4492); 
                    RIGHTP233_tree = 
                    (Object)adaptor.create(RIGHTP233)
                    ;
                    adaptor.addChild(root_0, RIGHTP233_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        try  {
                            if(null != (e!=null?e.element:null)) {
                                this.interp.storeIntoLibrary((e!=null?e.element:null));
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2514:4: (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2514:4: (pr= PRODUCT |pe= PERMUTE )
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==PRODUCT) ) {
                        alt83=1;
                    }
                    else if ( (LA83_0==PERMUTE) ) {
                        alt83=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 83, 0, input);

                        throw nvae;

                    }
                    switch (alt83) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2514:5: pr= PRODUCT
                            {
                            pr=(Token)match(input,PRODUCT,FOLLOW_PRODUCT_in_built_in_function4502); 
                            pr_tree = 
                            (Object)adaptor.create(pr)
                            ;
                            adaptor.addChild(root_0, pr_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2514:16: pe= PERMUTE
                            {
                            pe=(Token)match(input,PERMUTE,FOLLOW_PERMUTE_in_built_in_function4506); 
                            pe_tree = 
                            (Object)adaptor.create(pe)
                            ;
                            adaptor.addChild(root_0, pe_tree);


                            }
                            break;

                    }


                    LEFTP234=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4509); 
                    LEFTP234_tree = 
                    (Object)adaptor.create(LEFTP234)
                    ;
                    adaptor.addChild(root_0, LEFTP234_tree);


                    idToken=(Token)match(input,ID,FOLLOW_ID_in_built_in_function4513); 
                    idToken_tree = 
                    (Object)adaptor.create(idToken)
                    ;
                    adaptor.addChild(root_0, idToken_tree);


                    RIGHTP235=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4515); 
                    RIGHTP235_tree = 
                    (Object)adaptor.create(RIGHTP235)
                    ;
                    adaptor.addChild(root_0, RIGHTP235_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:4: ( EXIT_LC | EXIT_UC ) ( LEFTP p= toPrint[defer] RIGHTP )?
                    {
                    root_0 = (Object)adaptor.nil();


                    set236=(Token)input.LT(1);

                    if ( (input.LA(1) >= EXIT_LC && input.LA(1) <= EXIT_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set236)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:24: ( LEFTP p= toPrint[defer] RIGHTP )?
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==LEFTP) ) {
                        alt84=1;
                    }
                    switch (alt84) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:25: LEFTP p= toPrint[defer] RIGHTP
                            {
                            LEFTP237=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4531); 
                            LEFTP237_tree = 
                            (Object)adaptor.create(LEFTP237)
                            ;
                            adaptor.addChild(root_0, LEFTP237_tree);


                            pushFollow(FOLLOW_toPrint_in_built_in_function4535);
                            p=toPrint(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, p.getTree());

                            RIGHTP238=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4538); 
                            RIGHTP238_tree = 
                            (Object)adaptor.create(RIGHTP238)
                            ;
                            adaptor.addChild(root_0, RIGHTP238_tree);


                            }
                            break;

                    }



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        if(null == p) {
                            printError("exiting...");
                        } else {
                            printError((p!=null?p.sb:null));
                        }
                    }
                    	

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2545:4: ID LEFTP RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    ID239=(Token)match(input,ID,FOLLOW_ID_in_built_in_function4547); 
                    ID239_tree = 
                    (Object)adaptor.create(ID239)
                    ;
                    adaptor.addChild(root_0, ID239_tree);


                    LEFTP240=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4549); 
                    LEFTP240_tree = 
                    (Object)adaptor.create(LEFTP240)
                    ;
                    adaptor.addChild(root_0, LEFTP240_tree);


                    RIGHTP241=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4551); 
                    RIGHTP241_tree = 
                    (Object)adaptor.create(RIGHTP241)
                    ;
                    adaptor.addChild(root_0, RIGHTP241_tree);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "range"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2548:1: range[boolean defer] returns [Variable sor, Variable eor] : s= expr[defer] COMMA e= expr[defer] ;
    public final EugeneParser.range_return range(boolean defer) throws RecognitionException {
        EugeneParser.range_return retval = new EugeneParser.range_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA242=null;
        EugeneParser.expr_return s =null;

        EugeneParser.expr_return e =null;


        Object COMMA242_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2550:2: (s= expr[defer] COMMA e= expr[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2550:4: s= expr[defer] COMMA e= expr[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_range4571);
            s=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());

            COMMA242=(Token)match(input,COMMA,FOLLOW_COMMA_in_range4574); 
            COMMA242_tree = 
            (Object)adaptor.create(COMMA242)
            ;
            adaptor.addChild(root_0, COMMA242_tree);


            pushFollow(FOLLOW_expr_in_range4578);
            e=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

                if(null != (s!=null?s.p:null)) {
                    if(!EugeneConstants.NUM.equals((s!=null?s.p:null).getType())) {
                        printError("Invalid start of range!");
                    }
                    if((s!=null?s.p:null).getNum() % 1 != 0) {
                        printError("The start of the range is not an integer!");
                    }    
                    retval.sor = (s!=null?s.p:null);
                }  
               
                if(null != (e!=null?e.p:null)) {
                    if(!EugeneConstants.NUM.equals((e!=null?e.p:null).getType())) {
                        printError("Invalid end of range!");
                    }
                    if((e!=null?e.p:null).getNum() % 1 != 0) {
                        printError("The end of the range is not an integer!");
                    }    
                    
                    retval.eor = (e!=null?e.p:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "object_access"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2603:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
    public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
        EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token DOT243=null;
        Token set244=null;
        Token LEFTP245=null;
        Token RIGHTP246=null;
        Token LEFTSBR247=null;
        Token RIGHTSBR248=null;
        EugeneParser.expr_return exp =null;

        EugeneParser.object_access_return o =null;


        Object id_tree=null;
        Object DOT243_tree=null;
        Object set244_tree=null;
        Object LEFTP245_tree=null;
        Object RIGHTP246_tree=null;
        Object LEFTSBR247_tree=null;
        Object RIGHTSBR248_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2605:2: (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==COMMA||LA89_0==DIV||LA89_0==EQUALS||LA89_0==GEQUAL||LA89_0==GTHAN||LA89_0==LEQUAL||(LA89_0 >= LTHAN && LA89_0 <= MINUS)||(LA89_0 >= MULT && LA89_0 <= NEQUAL)||LA89_0==PLUS||(LA89_0 >= RIGHTCUR && LA89_0 <= RIGHTSBR)||LA89_0==SEMIC||LA89_0==133||(LA89_0 >= 135 && LA89_0 <= 136)||LA89_0==139||(LA89_0 >= 142 && LA89_0 <= 143)||LA89_0==145||(LA89_0 >= 158 && LA89_0 <= 159)||LA89_0==172||(LA89_0 >= 174 && LA89_0 <= 175)||LA89_0==178||(LA89_0 >= 181 && LA89_0 <= 182)||LA89_0==184||(LA89_0 >= 197 && LA89_0 <= 198)) ) {
                alt89=1;
            }
            else if ( (LA89_0==DOT||LA89_0==LEFTSBR) ) {
                alt89=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;

            }
            switch (alt89) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2606:2: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.child = parent;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2611:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2611:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==DOT) ) {
                        alt88=1;
                    }
                    else if ( (LA88_0==LEFTSBR) ) {
                        alt88=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 88, 0, input);

                        throw nvae;

                    }
                    switch (alt88) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2611:5: DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
                            {
                            DOT243=(Token)match(input,DOT,FOLLOW_DOT_in_object_access4614); 
                            DOT243_tree = 
                            (Object)adaptor.create(DOT243)
                            ;
                            adaptor.addChild(root_0, DOT243_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2611:9: (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
                            int alt87=2;
                            int LA87_0 = input.LA(1);

                            if ( (LA87_0==ID) ) {
                                alt87=1;
                            }
                            else if ( ((LA87_0 >= SIZE_LC && LA87_0 <= SIZE_UC)) ) {
                                alt87=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 87, 0, input);

                                throw nvae;

                            }
                            switch (alt87) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2611:10: id= ID
                                    {
                                    id=(Token)match(input,ID,FOLLOW_ID_in_object_access4619); 
                                    id_tree = 
                                    (Object)adaptor.create(id)
                                    ;
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
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2625:6: ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )?
                                    {
                                    set244=(Token)input.LT(1);

                                    if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
                                        input.consume();
                                        adaptor.addChild(root_0, 
                                        (Object)adaptor.create(set244)
                                        );
                                        state.errorRecovery=false;
                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        throw mse;
                                    }


                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2625:24: ( LEFTP RIGHTP )?
                                    int alt86=2;
                                    int LA86_0 = input.LA(1);

                                    if ( (LA86_0==LEFTP) ) {
                                        alt86=1;
                                    }
                                    switch (alt86) {
                                        case 1 :
                                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2625:25: LEFTP RIGHTP
                                            {
                                            LEFTP245=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_object_access4632); 
                                            LEFTP245_tree = 
                                            (Object)adaptor.create(LEFTP245)
                                            ;
                                            adaptor.addChild(root_0, LEFTP245_tree);


                                            RIGHTP246=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_object_access4634); 
                                            RIGHTP246_tree = 
                                            (Object)adaptor.create(RIGHTP246)
                                            ;
                                            adaptor.addChild(root_0, RIGHTP246_tree);


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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2634:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
                            {
                            LEFTSBR247=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access4644); 
                            LEFTSBR247_tree = 
                            (Object)adaptor.create(LEFTSBR247)
                            ;
                            adaptor.addChild(root_0, LEFTSBR247_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2634:12: (exp= expr[defer] )
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2634:13: exp= expr[defer]
                            {
                            pushFollow(FOLLOW_expr_in_object_access4649);
                            exp=expr(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, exp.getTree());

                            }


                            RIGHTSBR248=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access4653); 
                            RIGHTSBR248_tree = 
                            (Object)adaptor.create(RIGHTSBR248)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR248_tree);



                            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                                try {
                                    if(null != (exp!=null?exp.p:null) && EugeneConstants.NUM.equals((exp!=null?exp.p:null).getType())) {
                                    
                                        if((exp!=null?exp.p:null).getNum() % 1 != 0 && (exp!=null?exp.p:null).getNum() < 0) {
                                            throw new EugeneException("Invalid index " + (exp!=null?exp.p:null) + "!");
                                        }
                                        
                                        retval.child = parent.getElement((int)((exp!=null?exp.p:null).getNum()));
                                        
                                        if(null == retval.child) {
                                            throw new EugeneException(parent.getName() + " does not contain " + (id!=null?id.getText():null));
                                        }
                                        
                                    } else {
                                        throw new EugeneException("Invalid index " + (exp!=null?exp.p:null) + "!");
                                    }
                                } catch(EugeneException ee) {
                                    printError(ee.getLocalizedMessage());
                                }
                            }	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_object_access_in_object_access4660);
                    o=object_access(defer, retval.child);

                    state._fsp--;

                    adaptor.addChild(root_0, o.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.child = (o!=null?o.child:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "dynamic_naming"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2666:1: dynamic_naming[boolean defer] returns [String name] : (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR );
    public final EugeneParser.dynamic_naming_return dynamic_naming(boolean defer) throws RecognitionException {
        EugeneParser.dynamic_naming_return retval = new EugeneParser.dynamic_naming_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token DOLLAR249=null;
        Token LEFTCUR250=null;
        Token RIGHTCUR251=null;
        EugeneParser.expr_return e =null;


        Object i_tree=null;
        Object DOLLAR249_tree=null;
        Object LEFTCUR250_tree=null;
        Object RIGHTCUR251_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2668:2: (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==ID) ) {
                alt90=1;
            }
            else if ( (LA90_0==DOLLAR) ) {
                alt90=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;

            }
            switch (alt90) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2668:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_dynamic_naming4685); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.name = (i!=null?i.getText():null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2673:4: DOLLAR LEFTCUR e= expr[defer] RIGHTCUR
                    {
                    root_0 = (Object)adaptor.nil();


                    DOLLAR249=(Token)match(input,DOLLAR,FOLLOW_DOLLAR_in_dynamic_naming4692); 
                    DOLLAR249_tree = 
                    (Object)adaptor.create(DOLLAR249)
                    ;
                    adaptor.addChild(root_0, DOLLAR249_tree);


                    LEFTCUR250=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_dynamic_naming4694); 
                    LEFTCUR250_tree = 
                    (Object)adaptor.create(LEFTCUR250)
                    ;
                    adaptor.addChild(root_0, LEFTCUR250_tree);


                    pushFollow(FOLLOW_expr_in_dynamic_naming4698);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTCUR251=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_dynamic_naming4701); 
                    RIGHTCUR251_tree = 
                    (Object)adaptor.create(RIGHTCUR251)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR251_tree);



                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        if((e!=null?e.p:null) == null) {
                            printError("Invalid name!");
                        } else if(!EugeneConstants.TXT.equals(((e!=null?e.p:null)).getType())) {
                            printError("The name must be of type txt!");
                        }
                        
                        retval.name = ((e!=null?e.p:null)).getTxt();
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "dataExchange"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2690:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] );
    public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
        EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.sbolStatement_return s =null;

        EugeneParser.pigeonStatement_return p =null;

        EugeneParser.importStatement_return i =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2692:2: (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] )
            int alt91=3;
            switch ( input.LA(1) ) {
            case SBOL:
                {
                alt91=1;
                }
                break;
            case PIGEON_LC:
            case PIGEON_UC:
                {
                alt91=2;
                }
                break;
            case IMPORT_LC:
            case IMPORT_UC:
                {
                alt91=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;

            }

            switch (alt91) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2692:4: s= sbolStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_sbolStatement_in_dataExchange4726);
                    s=sbolStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.e = (s!=null?s.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2697:4: p= pigeonStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pigeonStatement_in_dataExchange4736);
                    p=pigeonStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2698:4: i= importStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_importStatement_in_dataExchange4744);
                    i=importStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, i.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.e = (i!=null?i.e:null);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "includeStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2709:1: includeStatement[boolean defer] : ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING ;
    public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
        EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token HASHMARK252=null;
        Token set253=null;

        Object file_tree=null;
        Object HASHMARK252_tree=null;
        Object set253_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2710:2: ( ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2710:4: ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2710:4: ( HASHMARK )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==HASHMARK) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2710:5: HASHMARK
                    {
                    HASHMARK252=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement4766); 
                    HASHMARK252_tree = 
                    (Object)adaptor.create(HASHMARK252)
                    ;
                    adaptor.addChild(root_0, HASHMARK252_tree);


                    }
                    break;

            }


            set253=(Token)input.LT(1);

            if ( (input.LA(1) >= INCLUDE_LC && input.LA(1) <= INCLUDE_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set253)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement4778); 
            file_tree = 
            (Object)adaptor.create(file)
            ;
            adaptor.addChild(root_0, file_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    this.interp.includeFile((file!=null?file.getText():null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "importStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2722:1: importStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP ;
    public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
        EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token set254=null;
        Token LEFTP255=null;
        Token RIGHTP256=null;

        Object file_tree=null;
        Object set254_tree=null;
        Object LEFTP255_tree=null;
        Object RIGHTP256_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2724:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2724:4: ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set254=(Token)input.LT(1);

            if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set254)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP255=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement4805); 
            LEFTP255_tree = 
            (Object)adaptor.create(LEFTP255)
            ;
            adaptor.addChild(root_0, LEFTP255_tree);


            file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement4809); 
            file_tree = 
            (Object)adaptor.create(file)
            ;
            adaptor.addChild(root_0, file_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    retval.e = this.interp.importFile((file!=null?file.getText():null));
                } catch(EugeneException ee) {
                    printError(ee.getLocalizedMessage());
                }
            }
            	

            RIGHTP256=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement4813); 
            RIGHTP256_tree = 
            (Object)adaptor.create(RIGHTP256)
            ;
            adaptor.addChild(root_0, RIGHTP256_tree);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sbolStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2736:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) ;
    public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SBOL257=null;
        Token DOT258=null;
        EugeneParser.sbolImportStatement_return i =null;

        EugeneParser.sbolExportStatement_return sbolExportStatement259 =null;


        Object SBOL257_tree=null;
        Object DOT258_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2738:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2738:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            {
            root_0 = (Object)adaptor.nil();


            SBOL257=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement4835); 
            SBOL257_tree = 
            (Object)adaptor.create(SBOL257)
            ;
            adaptor.addChild(root_0, SBOL257_tree);


            DOT258=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement4837); 
            DOT258_tree = 
            (Object)adaptor.create(DOT258)
            ;
            adaptor.addChild(root_0, DOT258_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2738:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( ((LA93_0 >= EXPORT_LC && LA93_0 <= EXPORT_UC)) ) {
                alt93=1;
            }
            else if ( ((LA93_0 >= IMPORT_LC && LA93_0 <= IMPORT_UC)) ) {
                alt93=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;

            }
            switch (alt93) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2738:14: sbolExportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement4840);
                    sbolExportStatement259=sbolExportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sbolExportStatement259.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2738:43: i= sbolImportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement4847);
                    i=sbolImportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, i.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.e = (i!=null?i.e:null);
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
    // $ANTLR end "sbolStatement"


    public static class sbolExportStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sbolExportStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2745:1: sbolExportStatement[boolean defer] : ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
    public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token filenameToken=null;
        Token set260=null;
        Token LEFTP261=null;
        Token COMMA262=null;
        Token RIGHTP263=null;

        Object idToken_tree=null;
        Object filenameToken_tree=null;
        Object set260_tree=null;
        Object LEFTP261_tree=null;
        Object COMMA262_tree=null;
        Object RIGHTP263_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2746:2: ( ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2746:4: ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set260=(Token)input.LT(1);

            if ( (input.LA(1) >= EXPORT_LC && input.LA(1) <= EXPORT_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set260)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP261=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement4870); 
            LEFTP261_tree = 
            (Object)adaptor.create(LEFTP261)
            ;
            adaptor.addChild(root_0, LEFTP261_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement4874); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            COMMA262=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement4876); 
            COMMA262_tree = 
            (Object)adaptor.create(COMMA262)
            ;
            adaptor.addChild(root_0, COMMA262_tree);


            filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement4880); 
            filenameToken_tree = 
            (Object)adaptor.create(filenameToken)
            ;
            adaptor.addChild(root_0, filenameToken_tree);


            RIGHTP263=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement4882); 
            RIGHTP263_tree = 
            (Object)adaptor.create(RIGHTP263)
            ;
            adaptor.addChild(root_0, RIGHTP263_tree);



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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sbolImportStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2759:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP ;
    public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token fileToken=null;
        Token set264=null;
        Token LEFTP265=null;
        Token RIGHTP266=null;

        Object fileToken_tree=null;
        Object set264_tree=null;
        Object LEFTP265_tree=null;
        Object RIGHTP266_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2761:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2761:4: ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set264=(Token)input.LT(1);

            if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set264)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP265=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement4911); 
            LEFTP265_tree = 
            (Object)adaptor.create(LEFTP265)
            ;
            adaptor.addChild(root_0, LEFTP265_tree);


            fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement4915); 
            fileToken_tree = 
            (Object)adaptor.create(fileToken)
            ;
            adaptor.addChild(root_0, fileToken_tree);


            RIGHTP266=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement4917); 
            RIGHTP266_tree = 
            (Object)adaptor.create(RIGHTP266)
            ;
            adaptor.addChild(root_0, RIGHTP266_tree);



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


    public static class pigeonStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pigeonStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2806:1: pigeonStatement[boolean defer] : ( PIGEON_LC | PIGEON_UC ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.pigeonStatement_return pigeonStatement(boolean defer) throws RecognitionException {
        EugeneParser.pigeonStatement_return retval = new EugeneParser.pigeonStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token set267=null;
        Token LEFTP268=null;
        Token RIGHTP269=null;

        Object idToken_tree=null;
        Object set267_tree=null;
        Object LEFTP268_tree=null;
        Object RIGHTP269_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2807:2: ( ( PIGEON_LC | PIGEON_UC ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2807:4: ( PIGEON_LC | PIGEON_UC ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set267=(Token)input.LT(1);

            if ( (input.LA(1) >= PIGEON_LC && input.LA(1) <= PIGEON_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set267)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP268=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_pigeonStatement4946); 
            LEFTP268_tree = 
            (Object)adaptor.create(LEFTP268)
            ;
            adaptor.addChild(root_0, LEFTP268_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_pigeonStatement4950); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP269=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_pigeonStatement4952); 
            RIGHTP269_tree = 
            (Object)adaptor.create(RIGHTP269)
            ;
            adaptor.addChild(root_0, RIGHTP269_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    this.interp.pigeon((idToken!=null?idToken.getText():null));
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
    // $ANTLR end "pigeonStatement"


    public static class testStatements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "testStatements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2855:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
    public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
        EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token n=null;
        Token ASSERT270=null;
        Token LEFTP271=null;
        Token DOT272=null;
        Token set273=null;
        Token LEFTP274=null;
        Token RIGHTP275=null;
        Token EQUALS276=null;
        Token EQUALS277=null;
        Token RIGHTP278=null;
        Token NOTE279=null;
        Token LEFTP280=null;
        Token DOT281=null;
        Token set282=null;
        Token LEFTP283=null;
        Token RIGHTP284=null;
        Token EQUALS285=null;
        Token EQUALS286=null;
        Token RIGHTP287=null;

        Object id_tree=null;
        Object n_tree=null;
        Object ASSERT270_tree=null;
        Object LEFTP271_tree=null;
        Object DOT272_tree=null;
        Object set273_tree=null;
        Object LEFTP274_tree=null;
        Object RIGHTP275_tree=null;
        Object EQUALS276_tree=null;
        Object EQUALS277_tree=null;
        Object RIGHTP278_tree=null;
        Object NOTE279_tree=null;
        Object LEFTP280_tree=null;
        Object DOT281_tree=null;
        Object set282_tree=null;
        Object LEFTP283_tree=null;
        Object RIGHTP284_tree=null;
        Object EQUALS285_tree=null;
        Object EQUALS286_tree=null;
        Object RIGHTP287_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2856:2: (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
            int alt94=3;
            switch ( input.LA(1) ) {
            case EOF:
                {
                alt94=1;
                }
                break;
            case ASSERT:
                {
                alt94=2;
                }
                break;
            case NOTE:
                {
                alt94=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;

            }

            switch (alt94) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2856:5: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2856:7: ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    ASSERT270=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements4976); 
                    ASSERT270_tree = 
                    (Object)adaptor.create(ASSERT270)
                    ;
                    adaptor.addChild(root_0, ASSERT270_tree);


                    LEFTP271=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4978); 
                    LEFTP271_tree = 
                    (Object)adaptor.create(LEFTP271)
                    ;
                    adaptor.addChild(root_0, LEFTP271_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements4982); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT272=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements4984); 
                    DOT272_tree = 
                    (Object)adaptor.create(DOT272)
                    ;
                    adaptor.addChild(root_0, DOT272_tree);


                    set273=(Token)input.LT(1);

                    if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set273)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP274=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4992); 
                    LEFTP274_tree = 
                    (Object)adaptor.create(LEFTP274)
                    ;
                    adaptor.addChild(root_0, LEFTP274_tree);


                    RIGHTP275=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4994); 
                    RIGHTP275_tree = 
                    (Object)adaptor.create(RIGHTP275)
                    ;
                    adaptor.addChild(root_0, RIGHTP275_tree);


                    EQUALS276=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4996); 
                    EQUALS276_tree = 
                    (Object)adaptor.create(EQUALS276)
                    ;
                    adaptor.addChild(root_0, EQUALS276_tree);


                    EQUALS277=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4998); 
                    EQUALS277_tree = 
                    (Object)adaptor.create(EQUALS277)
                    ;
                    adaptor.addChild(root_0, EQUALS277_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5002); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP278=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5004); 
                    RIGHTP278_tree = 
                    (Object)adaptor.create(RIGHTP278)
                    ;
                    adaptor.addChild(root_0, RIGHTP278_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2872:5: NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    NOTE279=(Token)match(input,NOTE,FOLLOW_NOTE_in_testStatements5012); 
                    NOTE279_tree = 
                    (Object)adaptor.create(NOTE279)
                    ;
                    adaptor.addChild(root_0, NOTE279_tree);


                    LEFTP280=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5014); 
                    LEFTP280_tree = 
                    (Object)adaptor.create(LEFTP280)
                    ;
                    adaptor.addChild(root_0, LEFTP280_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements5018); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT281=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements5020); 
                    DOT281_tree = 
                    (Object)adaptor.create(DOT281)
                    ;
                    adaptor.addChild(root_0, DOT281_tree);


                    set282=(Token)input.LT(1);

                    if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set282)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP283=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5028); 
                    LEFTP283_tree = 
                    (Object)adaptor.create(LEFTP283)
                    ;
                    adaptor.addChild(root_0, LEFTP283_tree);


                    RIGHTP284=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5030); 
                    RIGHTP284_tree = 
                    (Object)adaptor.create(RIGHTP284)
                    ;
                    adaptor.addChild(root_0, RIGHTP284_tree);


                    EQUALS285=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5032); 
                    EQUALS285_tree = 
                    (Object)adaptor.create(EQUALS285)
                    ;
                    adaptor.addChild(root_0, EQUALS285_tree);


                    EQUALS286=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5034); 
                    EQUALS286_tree = 
                    (Object)adaptor.create(EQUALS286)
                    ;
                    adaptor.addChild(root_0, EQUALS286_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5038); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP287=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5040); 
                    RIGHTP287_tree = 
                    (Object)adaptor.create(RIGHTP287)
                    ;
                    adaptor.addChild(root_0, RIGHTP287_tree);



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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_definition"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2881:1: function_definition[boolean defer] : (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= function_statements[true] RIGHTCUR ;
    public final EugeneParser.function_definition_return function_definition(boolean defer) throws RecognitionException, EugeneReturnException {
        EugeneParser.function_definition_return retval = new EugeneParser.function_definition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token LEFTP288=null;
        Token RIGHTP289=null;
        Token LEFTCUR290=null;
        Token RIGHTCUR291=null;
        EugeneParser.type_specification_return rt =null;

        EugeneParser.list_of_parameters_return lop =null;

        EugeneParser.function_statements_return stmts =null;


        Object n_tree=null;
        Object LEFTP288_tree=null;
        Object RIGHTP289_tree=null;
        Object LEFTCUR290_tree=null;
        Object RIGHTCUR291_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2883:2: ( (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= function_statements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2883:4: (rt= type_specification[true] )? n= ID LEFTP (lop= list_of_parameters[true] )? RIGHTP LEFTCUR stmts= function_statements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2883:4: (rt= type_specification[true] )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==NUM||LA95_0==TXT) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2883:5: rt= type_specification[true]
                    {
                    pushFollow(FOLLOW_type_specification_in_function_definition5065);
                    rt=type_specification(true);

                    state._fsp--;

                    adaptor.addChild(root_0, rt.getTree());

                    }
                    break;

            }


            n=(Token)match(input,ID,FOLLOW_ID_in_function_definition5072); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            LEFTP288=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_function_definition5074); 
            LEFTP288_tree = 
            (Object)adaptor.create(LEFTP288)
            ;
            adaptor.addChild(root_0, LEFTP288_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2883:46: (lop= list_of_parameters[true] )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==NUM||LA96_0==TXT) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2883:47: lop= list_of_parameters[true]
                    {
                    pushFollow(FOLLOW_list_of_parameters_in_function_definition5079);
                    lop=list_of_parameters(true);

                    state._fsp--;

                    adaptor.addChild(root_0, lop.getTree());

                    }
                    break;

            }


            RIGHTP289=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_function_definition5084); 
            RIGHTP289_tree = 
            (Object)adaptor.create(RIGHTP289)
            ;
            adaptor.addChild(root_0, RIGHTP289_tree);


            LEFTCUR290=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_function_definition5086); 
            LEFTCUR290_tree = 
            (Object)adaptor.create(LEFTCUR290)
            ;
            adaptor.addChild(root_0, LEFTCUR290_tree);


            pushFollow(FOLLOW_function_statements_in_function_definition5094);
            stmts=function_statements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR291=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_function_definition5100); 
            RIGHTCUR291_tree = 
            (Object)adaptor.create(RIGHTCUR291)
            ;
            adaptor.addChild(root_0, RIGHTCUR291_tree);



            if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {  // FUNCTION DEFINITION 
                try {
                    // let's check if a return type is specified
                    String return_type = null;
                    if(null != rt) {
                        return_type = (rt!=null?input.toString(rt.start,rt.stop):null);
                    }
                    
                    // let's check if parameters are specified
                    List<NamedElement> parameters = null;
                    if(null != lop) {
                        parameters = (lop!=null?lop.parameters:null);
                    }
                    
                    // Function w/ parameters
                    this.interp.defineFunction(
                            return_type,     // return type
                            (n!=null?n.getText():null),         // the name of the function
                            parameters,      // list of parameters
                            (stmts!=null?((Token)stmts.start):null));   // list of statements
                            
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
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type_specification"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2914:1: type_specification[boolean defer] : ( NUM | TXT );
    public final EugeneParser.type_specification_return type_specification(boolean defer) throws RecognitionException {
        EugeneParser.type_specification_return retval = new EugeneParser.type_specification_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set292=null;

        Object set292_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2915:2: ( NUM | TXT )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set292=(Token)input.LT(1);

            if ( input.LA(1)==NUM||input.LA(1)==TXT ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set292)
                );
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
    // $ANTLR end "type_specification"


    public static class list_of_parameters_return extends ParserRuleReturnScope {
        public List<NamedElement> parameters;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list_of_parameters"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2918:1: list_of_parameters[boolean defer] returns [List<NamedElement> parameters] : t= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? ;
    public final EugeneParser.list_of_parameters_return list_of_parameters(boolean defer) throws RecognitionException {
        EugeneParser.list_of_parameters_return retval = new EugeneParser.list_of_parameters_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token COMMA293=null;
        EugeneParser.type_specification_return t =null;

        EugeneParser.list_of_parameters_return lop =null;


        Object n_tree=null;
        Object COMMA293_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2920:2: (t= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2920:4: t= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_type_specification_in_list_of_parameters5139);
            t=type_specification(defer);

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            n=(Token)match(input,ID,FOLLOW_ID_in_list_of_parameters5144); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);



            if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
                if(null == retval.parameters) {
                    retval.parameters = new ArrayList<NamedElement>();
                }
                
                try {
                    retval.parameters.add(
                        this.interp.createFunctionParameter(
                            (t!=null?input.toString(t.start,t.stop):null),      // type of the parameter
                            (n!=null?n.getText():null)));    // name of the parameter
                } catch(EugeneException ee) {
                    printError(ee.getLocalizedMessage());
                }
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2935:4: ( COMMA lop= list_of_parameters[defer] )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2935:5: COMMA lop= list_of_parameters[defer]
                    {
                    COMMA293=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_parameters5149); 
                    COMMA293_tree = 
                    (Object)adaptor.create(COMMA293)
                    ;
                    adaptor.addChild(root_0, COMMA293_tree);


                    pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters5153);
                    lop=list_of_parameters(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lop.getTree());


                    if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
                        retval.parameters.addAll((lop!=null?lop.parameters:null));    
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


    public static class function_call_return extends ParserRuleReturnScope {
        public NamedElement e;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_call"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2950:1: function_call[boolean defer] returns [NamedElement e] : udf= call_user_defined_function[defer] ;
    public final EugeneParser.function_call_return function_call(boolean defer) throws RecognitionException {
        EugeneParser.function_call_return retval = new EugeneParser.function_call_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.call_user_defined_function_return udf =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2952:2: (udf= call_user_defined_function[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2952:4: udf= call_user_defined_function[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_call_user_defined_function_in_function_call5184);
            udf=call_user_defined_function(defer);

            state._fsp--;

            adaptor.addChild(root_0, udf.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                retval.e = udf.e;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "call_user_defined_function"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2960:1: call_user_defined_function[boolean defer] returns [NamedElement e] : f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP ;
    public final EugeneParser.call_user_defined_function_return call_user_defined_function(boolean defer) throws RecognitionException {
        EugeneParser.call_user_defined_function_return retval = new EugeneParser.call_user_defined_function_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token f=null;
        Token LEFTP294=null;
        Token RIGHTP295=null;
        EugeneParser.list_of_expressions_return loe =null;


        Object f_tree=null;
        Object LEFTP294_tree=null;
        Object RIGHTP295_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2962:2: (f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2962:4: f= ID LEFTP (loe= list_of_expressions[defer] )? RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            f=(Token)match(input,ID,FOLLOW_ID_in_call_user_defined_function5209); 
            f_tree = 
            (Object)adaptor.create(f)
            ;
            adaptor.addChild(root_0, f_tree);


            LEFTP294=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_call_user_defined_function5211); 
            LEFTP294_tree = 
            (Object)adaptor.create(LEFTP294)
            ;
            adaptor.addChild(root_0, LEFTP294_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2962:15: (loe= list_of_expressions[defer] )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==DOLLAR||(LA98_0 >= EXIT_LC && LA98_0 <= EXIT_UC)||(LA98_0 >= FALSE_LC && LA98_0 <= FALSE_UC)||LA98_0==ID||(LA98_0 >= LEFTP && LA98_0 <= LEFTSBR)||LA98_0==MINUS||LA98_0==NUMBER||LA98_0==PERMUTE||LA98_0==PRODUCT||(LA98_0 >= RANDOM_LC && LA98_0 <= REAL)||(LA98_0 >= SAVE_LC && LA98_0 <= SAVE_UC)||(LA98_0 >= SIZEOF_LC && LA98_0 <= STORE_UC)||(LA98_0 >= STRING && LA98_0 <= TRUE_UC)) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2962:16: loe= list_of_expressions[defer]
                    {
                    pushFollow(FOLLOW_list_of_expressions_in_call_user_defined_function5216);
                    loe=list_of_expressions(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, loe.getTree());

                    }
                    break;

            }


            RIGHTP295=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_call_user_defined_function5221); 
            RIGHTP295_tree = 
            (Object)adaptor.create(RIGHTP295)
            ;
            adaptor.addChild(root_0, RIGHTP295_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                try {
                    retval.e = this.call_function((f!=null?f.getText():null), (loe!=null?loe.parameter_values:null));
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list_of_expressions"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2973:1: list_of_expressions[boolean defer] returns [List<NamedElement> parameter_values] : e= expr[defer] ( COMMA loe= list_of_expressions[defer] )? ;
    public final EugeneParser.list_of_expressions_return list_of_expressions(boolean defer) throws RecognitionException {
        EugeneParser.list_of_expressions_return retval = new EugeneParser.list_of_expressions_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA296=null;
        EugeneParser.expr_return e =null;

        EugeneParser.list_of_expressions_return loe =null;


        Object COMMA296_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2975:2: (e= expr[defer] ( COMMA loe= list_of_expressions[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2975:4: e= expr[defer] ( COMMA loe= list_of_expressions[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_list_of_expressions5243);
            e=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                if(null == retval.parameter_values) {
                    retval.parameter_values = new ArrayList<NamedElement>();
                }
                
                if(null == (e!=null?e.p:null)) { 
                    retval.parameter_values.add((e!=null?e.element:null));
                } else {
                    retval.parameter_values.add((e!=null?e.p:null));
                }
                
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2988:5: ( COMMA loe= list_of_expressions[defer] )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==COMMA) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2988:6: COMMA loe= list_of_expressions[defer]
                    {
                    COMMA296=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_expressions5250); 
                    COMMA296_tree = 
                    (Object)adaptor.create(COMMA296)
                    ;
                    adaptor.addChild(root_0, COMMA296_tree);


                    pushFollow(FOLLOW_list_of_expressions_in_list_of_expressions5254);
                    loe=list_of_expressions(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, loe.getTree());


                    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                        retval.parameter_values.addAll((loe!=null?loe.parameter_values:null));
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


    public static class listOfStatements_return extends ParserRuleReturnScope {
        public NamedElement e;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfStatements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2996:1: listOfStatements[boolean defer] returns [NamedElement e] : (stmtToken= statement[defer] )+ ;
    public final EugeneParser.listOfStatements_return listOfStatements(boolean defer) throws RecognitionException {
        EugeneParser.listOfStatements_return retval = new EugeneParser.listOfStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.statement_return stmtToken =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2998:2: ( (stmtToken= statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2998:4: (stmtToken= statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2998:4: (stmtToken= statement[defer] )+
            int cnt100=0;
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( (LA100_0==ARRAY||(LA100_0 >= BOOL && LA100_0 <= COLLECTION)||LA100_0==DEVICE||(LA100_0 >= EXIT_LC && LA100_0 <= EXIT_UC)||LA100_0==GRAMMAR||(LA100_0 >= HASHMARK && LA100_0 <= ID)||(LA100_0 >= IMPORT_LC && LA100_0 <= INTERACTION)||(LA100_0 >= LC_FOR && LA100_0 <= LC_IF)||LA100_0==LC_WHILE||LA100_0==NUM||(LA100_0 >= PART && LA100_0 <= PIGEON_UC)||(LA100_0 >= PRINTLN_LC && LA100_0 <= RANDOM_UC)||(LA100_0 >= RULE && LA100_0 <= SBOL)||(LA100_0 >= SIZEOF_LC && LA100_0 <= STORE_UC)||(LA100_0 >= TXT && LA100_0 <= TYPE)||(LA100_0 >= UC_FOR && LA100_0 <= UC_IF)||LA100_0==UC_WHILE) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2998:5: stmtToken= statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_listOfStatements5283);
            	    stmtToken=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmtToken.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
            	        retval.e = (NamedElement)null;
            	    }	
            	    	

            	    }
            	    break;

            	default :
            	    if ( cnt100 >= 1 ) break loop100;
                        EarlyExitException eee =
                            new EarlyExitException(100, input);
                        throw eee;
                }
                cnt100++;
            } while (true);


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
    // $ANTLR end "listOfStatements"


    public static class function_statements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_statements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3006:1: function_statements[boolean defer] : ( statement[defer] |rs= return_statement[defer] )+ ;
    public final EugeneParser.function_statements_return function_statements(boolean defer) throws RecognitionException, EugeneReturnException {
        EugeneParser.function_statements_return retval = new EugeneParser.function_statements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.return_statement_return rs =null;

        EugeneParser.statement_return statement297 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3008:2: ( ( statement[defer] |rs= return_statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3008:4: ( statement[defer] |rs= return_statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3008:4: ( statement[defer] |rs= return_statement[defer] )+
            int cnt101=0;
            loop101:
            do {
                int alt101=3;
                int LA101_0 = input.LA(1);

                if ( (LA101_0==ARRAY||(LA101_0 >= BOOL && LA101_0 <= COLLECTION)||LA101_0==DEVICE||(LA101_0 >= EXIT_LC && LA101_0 <= EXIT_UC)||LA101_0==GRAMMAR||(LA101_0 >= HASHMARK && LA101_0 <= ID)||(LA101_0 >= IMPORT_LC && LA101_0 <= INTERACTION)||(LA101_0 >= LC_FOR && LA101_0 <= LC_IF)||LA101_0==LC_WHILE||LA101_0==NUM||(LA101_0 >= PART && LA101_0 <= PIGEON_UC)||(LA101_0 >= PRINTLN_LC && LA101_0 <= RANDOM_UC)||(LA101_0 >= RULE && LA101_0 <= SBOL)||(LA101_0 >= SIZEOF_LC && LA101_0 <= STORE_UC)||(LA101_0 >= TXT && LA101_0 <= TYPE)||(LA101_0 >= UC_FOR && LA101_0 <= UC_IF)||LA101_0==UC_WHILE) ) {
                    alt101=1;
                }
                else if ( ((LA101_0 >= RETURN_LC && LA101_0 <= RETURN_UC)) ) {
                    alt101=2;
                }


                switch (alt101) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3008:5: statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_function_statements5310);
            	    statement297=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement297.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3008:24: rs= return_statement[defer]
            	    {
            	    pushFollow(FOLLOW_return_statement_in_function_statements5317);
            	    rs=return_statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, rs.getTree());


            	    if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
            	        throw new EugeneReturnException((rs!=null?rs.el:null));
            	    }
            	    	

            	    }
            	    break;

            	default :
            	    if ( cnt101 >= 1 ) break loop101;
                        EarlyExitException eee =
                            new EarlyExitException(101, input);
                        throw eee;
                }
                cnt101++;
            } while (true);


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
    // $ANTLR end "function_statements"


    public static class return_statement_return extends ParserRuleReturnScope {
        public NamedElement el;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "return_statement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3015:1: return_statement[boolean defer] returns [NamedElement el] : ( RETURN_LC | RETURN_UC ) e= expr[defer] SEMIC ;
    public final EugeneParser.return_statement_return return_statement(boolean defer) throws RecognitionException {
        EugeneParser.return_statement_return retval = new EugeneParser.return_statement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set298=null;
        Token SEMIC299=null;
        EugeneParser.expr_return e =null;


        Object set298_tree=null;
        Object SEMIC299_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3017:2: ( ( RETURN_LC | RETURN_UC ) e= expr[defer] SEMIC )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:3017:4: ( RETURN_LC | RETURN_UC ) e= expr[defer] SEMIC
            {
            root_0 = (Object)adaptor.nil();


            set298=(Token)input.LT(1);

            if ( (input.LA(1) >= RETURN_LC && input.LA(1) <= RETURN_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set298)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_expr_in_return_statement5349);
            e=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            SEMIC299=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_return_statement5352); 
            SEMIC299_tree = 
            (Object)adaptor.create(SEMIC299)
            ;
            adaptor.addChild(root_0, SEMIC299_tree);



            if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
                if(null != (e!=null?e.element:null)) {
                    retval.el = (e!=null?e.element:null);
                } else {
                    retval.el = (e!=null?e.p:null);
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
    // $ANTLR end "return_statement"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_prog997 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_function_definition_in_prog1002 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_EOF_in_prog1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeStatement_in_statement1033 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_statement1044 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_printStatement_in_statement1053 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement1061 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_statement1071 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imperativeStatements_in_statement1081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_call_in_statement1087 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_built_in_function_in_statement1095 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement1119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement1133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instantiation_in_declarationStatement1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement1151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_grammarDeclaration_in_declarationStatement1163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1187 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numdecl_in_variableDeclaration1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1202 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtdecl_in_variableDeclaration1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1217 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1219 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1221 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration1225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1236 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1238 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1240 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_variableDeclaration1255 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_booldecl_in_variableDeclaration1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1286 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1292 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1302 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_numdecl1304 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_numdecl1309 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1317 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1339 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1346 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1359 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_txtdecl1361 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_txtdecl1365 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1373 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1395 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1402 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1414 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1416 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_txtlistdecl1422 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1430 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1452 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1459 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1471 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_numlistdecl1473 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_numlistdecl1478 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1486 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1508 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_booldecl1515 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_booldecl_in_booldecl1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1527 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_booldecl1529 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_booldecl1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1551 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration1555 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1557 = new BitSet(new long[]{0x0000000000000600L,0x0000080000000008L});
    public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1561 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1589 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1591 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1608 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1610 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_propertyType1619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_typeDeclaration1648 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_typeDeclaration1653 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1656 = new BitSet(new long[]{0x0000000200000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1661 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_partTypeDeclaration1685 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_partTypeDeclaration1694 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1697 = new BitSet(new long[]{0x0000000200000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1702 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1734 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1741 = new BitSet(new long[]{0x0040000200000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1744 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1746 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_containerDeclaration1754 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1759 = new BitSet(new long[]{0x2060008246624E40L,0x00001F7E741F00F8L});
    public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1762 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1800 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr_in_list_of_declarations1807 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_declarations1815 = new BitSet(new long[]{0x2060008246624E40L,0x00001F7E701F00F8L});
    public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instantiation1847 = new BitSet(new long[]{0x0000000200020000L});
    public static final BitSet FOLLOW_dynamic_naming_in_instantiation1853 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_instantiation1860 = new BitSet(new long[]{0x2060000206660000L,0x0000077E641D0090L});
    public static final BitSet FOLLOW_listOfDotValues_in_instantiation1865 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_listOfValues_in_instantiation1870 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_instantiation1875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1898 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1902 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1908 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1912 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1920 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfDotValues1923 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1925 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1929 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1937 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1941 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1951 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr_in_listOfValues1972 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfValues1978 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_listOfValues1984 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration2003 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_deviceDeclaration2007 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration2010 = new BitSet(new long[]{0x2040000200000000L,0x0000000004000800L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration2015 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration2020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_in_deviceComponents2051 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_deviceComponents2057 = new BitSet(new long[]{0x2040000200000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceComponents2061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_selection2085 = new BitSet(new long[]{0x2000000200000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_selection_list_in_selection2089 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_selection2092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection2101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection_list2130 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_PIPE_in_selection_list2136 = new BitSet(new long[]{0x2000000200000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_selection_list_in_selection_list2140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_device_component2166 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_device_component2176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_assignment_in_assignment2196 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2199 = new BitSet(new long[]{0x2060001A06620020L,0x0000077EE01D0390L});
    public static final BitSet FOLLOW_AMP_in_assignment2204 = new BitSet(new long[]{0x2060001A06620000L,0x0000077EE01D0390L});
    public static final BitSet FOLLOW_rhs_assignment_in_assignment2210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lhs_assignment2225 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lhs_access2247 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_lhs_access2251 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2255 = new BitSet(new long[]{0x0000000200000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_set_in_lhs_access2257 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2263 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_access2266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_rhs_assignment2303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_listOfIDs2331 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfIDs2340 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_ruleDeclaration2368 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2372 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2374 = new BitSet(new long[]{0x2465C00200000000L,0xF8B8010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_set_in_ruleDeclaration2379 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2387 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_ruleDeclaration2389 = new BitSet(new long[]{0x2464C00200000000L,0xF898010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2397 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2795 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQUAL_in_relationalOperators2802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTHAN_in_relationalOperators2807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GTHAN_in_relationalOperators2812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEQUAL_in_relationalOperators2817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GEQUAL_in_relationalOperators2822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_predicate_in_cnf_rule2923 = new BitSet(new long[]{0x0200010000000002L,0x0000200000000000L});
    public static final BitSet FOLLOW_set_in_cnf_rule2931 = new BitSet(new long[]{0x2464C00200000000L,0xF898010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_cnf_rule_in_cnf_rule2941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2971 = new BitSet(new long[]{0x0802000000000002L,0x0040000000000000L});
    public static final BitSet FOLLOW_set_in_or_predicate2977 = new BitSet(new long[]{0x2464C00200000000L,0xF898010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2987 = new BitSet(new long[]{0x0802000000000002L,0x0040000000000000L});
    public static final BitSet FOLLOW_set_in_negated_predicate3015 = new BitSet(new long[]{0x2064400200000000L,0xF888010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_predicate_in_negated_predicate3025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_negated_predicate3035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_predicate3062 = new BitSet(new long[]{0x0004400000000000L,0xF888000000000000L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_ruleOperator_in_predicate3072 = new BitSet(new long[]{0x0040000200000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_operand_in_predicate3081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_predicate3095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionRule_in_predicate3104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand3135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_operand3144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_operand3151 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_operand3155 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_operand3157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionRule3183 = new BitSet(new long[]{0x10800000A0100000L,0x0000000000000001L,0x0164D000C002C9A0L,0x0000000000000060L});
    public static final BitSet FOLLOW_exp_op_in_expressionRule3188 = new BitSet(new long[]{0x2020000200000000L,0x0000010000100010L});
    public static final BitSet FOLLOW_expression_in_expressionRule3193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_operand_in_expression3217 = new BitSet(new long[]{0xA000000000010002L,0x0000000000000800L});
    public static final BitSet FOLLOW_exp_operator_in_expression3226 = new BitSet(new long[]{0x2020000200000000L,0x0000010000100010L});
    public static final BitSet FOLLOW_expression_in_expression3231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_expression3243 = new BitSet(new long[]{0x2020000200000000L,0x0000010000100010L});
    public static final BitSet FOLLOW_expression_in_expression3245 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_expression3248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp_operator3267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operator3275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_exp_operator3282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_exp_operator3289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_exp_operand3319 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_exp_operand3321 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_exp_operand3330 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3340 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3342 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3381 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp_operand3394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOperators_in_exp_op3421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GRAMMAR_in_grammarDeclaration3440 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_grammarDeclaration3444 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_grammarDeclaration3446 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_list_of_production_rules_in_grammarDeclaration3448 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_grammarDeclaration3451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_production_rule_in_list_of_production_rules3463 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_list_of_production_rules3466 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_list_of_production_rules_in_list_of_production_rules3469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_production_rule3489 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ARROW_in_production_rule3493 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_right_hand_side_in_production_rule3495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_right_hand_side3511 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_right_hand_side3516 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_right_hand_side_in_right_hand_side3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_right_hand_side3526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3559 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_interactionDeclaration3563 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3565 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3569 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3595 = new BitSet(new long[]{0x0004400000000000L,0x0088000000000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3599 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_interaction3604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3613 = new BitSet(new long[]{0x0004400000000000L,0x0088000000000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3617 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interaction3620 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_interaction_in_interaction3624 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_interaction3627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_printStatement3686 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3692 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3696 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_printStatement3706 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3712 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3716 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_toPrint3740 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_toPrint_prime_in_toPrint3745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_toPrint_prime3771 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_toPrint_in_toPrint_prime3775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_imperativeStatements3795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements3801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_imperativeStatements3807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_imperativeStatements3813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ifStatement3839 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3845 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3849 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3852 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3854 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3862 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3865 = new BitSet(new long[]{0x0000060000000002L,0x0000C00000000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3880 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3886 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3890 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3893 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3895 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3903 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3906 = new BitSet(new long[]{0x0000060000000002L,0x0000C00000000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3922 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3928 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3936 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_imp_condition3963 = new BitSet(new long[]{0x10800000A0100000L,0x0000000000000001L,0x0164D000C002C9A0L,0x0000000000000060L});
    public static final BitSet FOLLOW_relationalOperators_in_imp_condition3968 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_imp_condition3972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_forall_iterator3988 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator3997 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_forall_iterator3999 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator4005 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator4007 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_listOfStatements_in_forall_iterator4012 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator4019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_for_loop4031 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_for_loop4037 = new BitSet(new long[]{0x0000000000000600L,0x0000080000000008L});
    public static final BitSet FOLLOW_variableDeclaration_in_for_loop4041 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop4044 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_imp_condition_in_for_loop4048 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop4051 = new BitSet(new long[]{0x0000000200000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_assignment_in_for_loop4056 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_for_loop4061 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_for_loop4063 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_listOfStatements_in_for_loop4071 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_for_loop4078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_while_loop4092 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_while_loop4098 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_imp_condition_in_while_loop4102 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_while_loop4105 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_while_loop4107 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_listOfStatements_in_while_loop4115 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_while_loop4122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_expr4146 = new BitSet(new long[]{0x2000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_set_in_expr4155 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_multExpr_in_expr4163 = new BitSet(new long[]{0x2000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_atom_in_multExpr4193 = new BitSet(new long[]{0x8000000000010002L});
    public static final BitSet FOLLOW_MULT_in_multExpr4204 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_DIV_in_multExpr4208 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_atom_in_multExpr4213 = new BitSet(new long[]{0x8000000000010002L});
    public static final BitSet FOLLOW_NUMBER_in_atom4240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom4256 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100010L});
    public static final BitSet FOLLOW_NUMBER_in_atom4261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dynamic_naming_in_atom4306 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_object_access_in_atom4313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_atom4330 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_atom4332 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_atom4335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_atom4344 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_list_in_atom4346 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_atom4349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_built_in_function_in_atom4359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_call_in_atom4369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_list4392 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list4399 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_list4403 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_set_in_built_in_function4431 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4441 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_built_in_function4445 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_built_in_function4455 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4461 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_range_in_built_in_function4465 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_built_in_function4475 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4485 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_built_in_function4489 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRODUCT_in_built_in_function4502 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_PERMUTE_in_built_in_function4506 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4509 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_built_in_function4513 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_built_in_function4522 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4531 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_toPrint_in_built_in_function4535 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_built_in_function4547 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4549 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_range4571 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_range4574 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_range4578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_object_access4614 = new BitSet(new long[]{0x0000000200000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_ID_in_object_access4619 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_set_in_object_access4625 = new BitSet(new long[]{0x0060000000040000L});
    public static final BitSet FOLLOW_LEFTP_in_object_access4632 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_object_access4634 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_LEFTSBR_in_object_access4644 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_object_access4649 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_object_access4653 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_object_access_in_object_access4660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_dynamic_naming4685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOLLAR_in_dynamic_naming4692 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_dynamic_naming4694 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_dynamic_naming4698 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_dynamic_naming4701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolStatement_in_dataExchange4726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pigeonStatement_in_dataExchange4736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importStatement_in_dataExchange4744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASHMARK_in_includeStatement4766 = new BitSet(new long[]{0x0000006000000000L});
    public static final BitSet FOLLOW_set_in_includeStatement4770 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_includeStatement4778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_importStatement4799 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_importStatement4805 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_importStatement4809 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_importStatement4813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBOL_in_sbolStatement4835 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_sbolStatement4837 = new BitSet(new long[]{0x0000001801800000L});
    public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement4840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement4847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolExportStatement4864 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement4870 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_sbolExportStatement4874 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_sbolExportStatement4876 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_sbolExportStatement4880 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement4882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolImportStatement4905 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement4911 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_sbolImportStatement4915 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement4917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_pigeonStatement4940 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_pigeonStatement4946 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_pigeonStatement4950 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_pigeonStatement4952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_testStatements4976 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4978 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_testStatements4982 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_testStatements4984 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_set_in_testStatements4986 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4992 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4994 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4996 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements5002 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements5004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTE_in_testStatements5012 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements5014 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_testStatements5018 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_testStatements5020 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_set_in_testStatements5022 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements5028 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements5030 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements5032 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements5034 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements5038 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements5040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specification_in_function_definition5065 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_function_definition5072 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_function_definition5074 = new BitSet(new long[]{0x0000000000000000L,0x0000080004000008L});
    public static final BitSet FOLLOW_list_of_parameters_in_function_definition5079 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_function_definition5084 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_function_definition5086 = new BitSet(new long[]{0x000838FB40604E40L,0x0107187EF18FF3E8L});
    public static final BitSet FOLLOW_function_statements_in_function_definition5094 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_function_definition5100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specification_in_list_of_parameters5139 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_list_of_parameters5144 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_parameters5149 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000008L});
    public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters5153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_call_user_defined_function_in_function_call5184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_call_user_defined_function5209 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_call_user_defined_function5211 = new BitSet(new long[]{0x2060000206620000L,0x0000077E641D0090L});
    public static final BitSet FOLLOW_list_of_expressions_in_call_user_defined_function5216 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_call_user_defined_function5221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_list_of_expressions5243 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_expressions5250 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_list_of_expressions_in_list_of_expressions5254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_listOfStatements5283 = new BitSet(new long[]{0x000838FB40604E42L,0x0107187EF00FF3E8L});
    public static final BitSet FOLLOW_statement_in_function_statements5310 = new BitSet(new long[]{0x000838FB40604E42L,0x0107187EF18FF3E8L});
    public static final BitSet FOLLOW_return_statement_in_function_statements5317 = new BitSet(new long[]{0x000838FB40604E42L,0x0107187EF18FF3E8L});
    public static final BitSet FOLLOW_set_in_return_statement5339 = new BitSet(new long[]{0x2060000206620000L,0x0000077E601D0090L});
    public static final BitSet FOLLOW_expr_in_return_statement5349 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_return_statement5352 = new BitSet(new long[]{0x0000000000000002L});

}