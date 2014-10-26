// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g 2014-10-26 13:13:19

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

    public void init(Interp interp, BufferedWriter writer) {
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
            } else if("assignment".equals(rule)) {
                rv = this.assignment(false);
            } else if("listOfStatements".equals(rule)) {
                rv = this.listOfStatements(false);
            } 
            
    /****        
            else if(rule.equals("ifCondition")) { 
                rv = this.expression(false).objElement; 
            } else if("onDeviceRule".equals(rule)) {
                rv = null;
                //rv = this.onDeviceRule(false).objRule;            
            } else if (rule.equals("listOfStatements")) {
                this.listOfStatements(false);
            } else if (rule.equals("PropertyValueDeclaration")){
                variableDeclaration_return ret=this.variableDeclaration(false);
            } else if(rule.equals("expression")) {
                return this.expression(false);
            } else if(rule.equals("computationalStatement")) {
                return this.computationalStatement(false);
            } else {
                System.err.println("Error: cannot execute rule " + rule + ".");
                //this.cleanUp(1);
            }
        } catch (EugeneReturnException e) {
            //return e.getReturnValue();
            throw new EugeneReturnException(e.getReturnValue());
     ****/        
    //    } catch (EugeneReturnException ere) {
    //       throw new EugeneReturnException(ere.getLocalizedMessage());
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



    public static class prog_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "prog"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:721:1: prog : ( statement[false] | function_definition[false] )+ EOF !;
    public final EugeneParser.prog_return prog() throws RecognitionException {
        EugeneParser.prog_return retval = new EugeneParser.prog_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF3=null;
        EugeneParser.statement_return statement1 =null;

        EugeneParser.function_definition_return function_definition2 =null;


        Object EOF3_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:727:2: ( ( statement[false] | function_definition[false] )+ EOF !)
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:727:4: ( statement[false] | function_definition[false] )+ EOF !
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:727:4: ( statement[false] | function_definition[false] )+
            int cnt1=0;
            loop1:
            do {
                int alt1=3;
                switch ( input.LA(1) ) {
                case ARRAY:
                case ASSERT:
                case BOOL:
                case BOOLEAN:
                case COLLECTION:
                case DEVICE:
                case EXIT_LC:
                case EXIT_UC:
                case GRAMMAR:
                case HASHMARK:
                case ID:
                case IMPORT_LC:
                case IMPORT_UC:
                case INCLUDE_LC:
                case INCLUDE_UC:
                case INTERACTION:
                case LC_FOR:
                case LC_FORALL:
                case LC_IF:
                case LC_WHILE:
                case NOTE:
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
                case SEMIC:
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
                        int LA1_5 = input.LA(3);

                        if ( (LA1_5==COMMA||LA1_5==EQUALS||LA1_5==SEMIC) ) {
                            alt1=1;
                        }
                        else if ( (LA1_5==LEFTP) ) {
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

                }

                switch (alt1) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:727:5: statement[false]
            	    {
            	    pushFollow(FOLLOW_statement_in_prog991);
            	    statement1=statement(false);

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement1.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:727:24: function_definition[false]
            	    {
            	    pushFollow(FOLLOW_function_definition_in_prog996);
            	    function_definition2=function_definition(false);

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


            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_prog1001); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:731:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC );
    public final EugeneParser.statement_return statement(boolean defer) throws RecognitionException {
        EugeneParser.statement_return retval = new EugeneParser.statement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMIC5=null;
        Token SEMIC7=null;
        Token SEMIC9=null;
        Token SEMIC11=null;
        Token SEMIC13=null;
        Token SEMIC14=null;
        Token SEMIC17=null;
        EugeneParser.dataExchange_return de =null;

        EugeneParser.includeStatement_return includeStatement4 =null;

        EugeneParser.declarationStatement_return declarationStatement6 =null;

        EugeneParser.printStatement_return printStatement8 =null;

        EugeneParser.assignment_return assignment10 =null;

        EugeneParser.functionCall_return functionCall12 =null;

        EugeneParser.imperativeStatements_return imperativeStatements15 =null;

        EugeneParser.predefined_statements_return predefined_statements16 =null;


        Object SEMIC5_tree=null;
        Object SEMIC7_tree=null;
        Object SEMIC9_tree=null;
        Object SEMIC11_tree=null;
        Object SEMIC13_tree=null;
        Object SEMIC14_tree=null;
        Object SEMIC17_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:733:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC )
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
                int LA3_3 = input.LA(2);

                if ( (LA3_3==DOLLAR||LA3_3==ID||LA3_3==LC_INDUCES||LA3_3==LC_REPRESSES||LA3_3==UC_INDUCES||LA3_3==UC_REPRESSES) ) {
                    alt3=2;
                }
                else if ( (LA3_3==DOT||LA3_3==EQUALS||LA3_3==LEFTSBR) ) {
                    alt3=4;
                }
                else {
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
            case PERMUTE:
            case PRODUCT:
                {
                alt3=5;
                }
                break;
            case IMPORT_LC:
            case IMPORT_UC:
            case PIGEON_LC:
            case PIGEON_UC:
            case SBOL:
                {
                alt3=6;
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
                alt3=7;
                }
                break;
            case ASSERT:
            case EXIT_LC:
            case EXIT_UC:
            case NOTE:
            case RANDOM_LC:
            case RANDOM_UC:
            case SAVE_LC:
            case SAVE_UC:
            case SEMIC:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:734:3: includeStatement[defer] ( SEMIC )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_includeStatement_in_statement1027);
                    includeStatement4=includeStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, includeStatement4.getTree());

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:734:27: ( SEMIC )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==SEMIC) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:734:28: SEMIC
                            {
                            SEMIC5=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1031); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:735:4: declarationStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declarationStatement_in_statement1038);
                    declarationStatement6=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, declarationStatement6.getTree());

                    SEMIC7=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1041); 
                    SEMIC7_tree = 
                    (Object)adaptor.create(SEMIC7)
                    ;
                    adaptor.addChild(root_0, SEMIC7_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:736:4: printStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_printStatement_in_statement1047);
                    printStatement8=printStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, printStatement8.getTree());

                    SEMIC9=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1050); 
                    SEMIC9_tree = 
                    (Object)adaptor.create(SEMIC9)
                    ;
                    adaptor.addChild(root_0, SEMIC9_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:737:4: assignment[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_statement1055);
                    assignment10=assignment(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, assignment10.getTree());

                    SEMIC11=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1058); 
                    SEMIC11_tree = 
                    (Object)adaptor.create(SEMIC11)
                    ;
                    adaptor.addChild(root_0, SEMIC11_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:738:4: functionCall[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_statement1063);
                    functionCall12=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, functionCall12.getTree());

                    SEMIC13=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1066); 
                    SEMIC13_tree = 
                    (Object)adaptor.create(SEMIC13)
                    ;
                    adaptor.addChild(root_0, SEMIC13_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:739:4: de= dataExchange[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_statement1073);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());

                    SEMIC14=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1076); 
                    SEMIC14_tree = 
                    (Object)adaptor.create(SEMIC14)
                    ;
                    adaptor.addChild(root_0, SEMIC14_tree);



                    if(!defer) {
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
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:753:4: imperativeStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_imperativeStatements_in_statement1083);
                    imperativeStatements15=imperativeStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, imperativeStatements15.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:754:4: predefined_statements[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_predefined_statements_in_statement1089);
                    predefined_statements16=predefined_statements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, predefined_statements16.getTree());

                    SEMIC17=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1092); 
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


    public static class predefined_statements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "predefined_statements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:757:1: predefined_statements[boolean defer] : ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) | built_in_function[defer] );
    public final EugeneParser.predefined_statements_return predefined_statements(boolean defer) throws RecognitionException {
        EugeneParser.predefined_statements_return retval = new EugeneParser.predefined_statements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set19=null;
        EugeneParser.testStatements_return testStatements18 =null;

        EugeneParser.built_in_function_return built_in_function20 =null;


        Object set19_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:758:2: ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) | built_in_function[defer] )
            int alt4=3;
            switch ( input.LA(1) ) {
            case ASSERT:
            case NOTE:
            case SEMIC:
                {
                alt4=1;
                }
                break;
            case EXIT_LC:
            case EXIT_UC:
                {
                alt4=2;
                }
                break;
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
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:758:4: testStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_testStatements_in_predefined_statements1105);
                    testStatements18=testStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, testStatements18.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:759:4: ( EXIT_LC | EXIT_UC )
                    {
                    root_0 = (Object)adaptor.nil();


                    set19=(Token)input.LT(1);

                    if ( (input.LA(1) >= EXIT_LC && input.LA(1) <= EXIT_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set19)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }



                    if(!defer) {
                        printError("exiting...");
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:764:4: built_in_function[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_built_in_function_in_predefined_statements1124);
                    built_in_function20=built_in_function(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, built_in_function20.getTree());

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
    // $ANTLR end "predefined_statements"


    public static class declarationStatement_return extends ParserRuleReturnScope {
        public String name;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "declarationStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:768:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] );
    public final EugeneParser.declarationStatement_return declarationStatement(boolean defer) throws RecognitionException {
        EugeneParser.declarationStatement_return retval = new EugeneParser.declarationStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.variableDeclaration_return v =null;

        EugeneParser.containerDeclaration_return containerDeclaration21 =null;

        EugeneParser.propertyDeclaration_return propertyDeclaration22 =null;

        EugeneParser.typeDeclaration_return typeDeclaration23 =null;

        EugeneParser.instantiation_return instantiation24 =null;

        EugeneParser.interactionDeclaration_return interactionDeclaration25 =null;

        EugeneParser.ruleDeclaration_return ruleDeclaration26 =null;

        EugeneParser.grammarDeclaration_return grammarDeclaration27 =null;

        EugeneParser.deviceDeclaration_return deviceDeclaration28 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:770:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] )
            int alt5=9;
            switch ( input.LA(1) ) {
            case BOOL:
            case BOOLEAN:
            case NUM:
            case TXT:
                {
                alt5=1;
                }
                break;
            case ARRAY:
            case COLLECTION:
                {
                alt5=2;
                }
                break;
            case PROPERTY:
                {
                alt5=3;
                }
                break;
            case PART:
            case PART_TYPE:
            case TYPE:
                {
                alt5=4;
                }
                break;
            case ID:
                {
                int LA5_5 = input.LA(2);

                if ( (LA5_5==DOLLAR||LA5_5==ID) ) {
                    alt5=5;
                }
                else if ( (LA5_5==LC_INDUCES||LA5_5==LC_REPRESSES||LA5_5==UC_INDUCES||LA5_5==UC_REPRESSES) ) {
                    alt5=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 5, input);

                    throw nvae;

                }
                }
                break;
            case INTERACTION:
                {
                alt5=6;
                }
                break;
            case RULE:
                {
                alt5=7;
                }
                break;
            case GRAMMAR:
                {
                alt5=8;
                }
                break;
            case DEVICE:
                {
                alt5=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:770:4: v= variableDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_variableDeclaration_in_declarationStatement1145);
                    v=variableDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, v.getTree());


                    if(!defer) {
                        retval.name = (v!=null?v.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:775:4: containerDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_containerDeclaration_in_declarationStatement1153);
                    containerDeclaration21=containerDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, containerDeclaration21.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:776:4: propertyDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement1159);
                    propertyDeclaration22=propertyDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, propertyDeclaration22.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:777:4: typeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeDeclaration_in_declarationStatement1165);
                    typeDeclaration23=typeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, typeDeclaration23.getTree());

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:778:4: instantiation[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_instantiation_in_declarationStatement1171);
                    instantiation24=instantiation(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, instantiation24.getTree());

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:779:4: interactionDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement1177);
                    interactionDeclaration25=interactionDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, interactionDeclaration25.getTree());

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:780:4: ruleDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement1183);
                    ruleDeclaration26=ruleDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ruleDeclaration26.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:781:4: grammarDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_grammarDeclaration_in_declarationStatement1189);
                    grammarDeclaration27=grammarDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, grammarDeclaration27.getTree());

                    }
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:782:4: deviceDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement1195);
                    deviceDeclaration28=deviceDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, deviceDeclaration28.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:785:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] );
    public final EugeneParser.variableDeclaration_return variableDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.variableDeclaration_return retval = new EugeneParser.variableDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NUM29=null;
        Token TXT30=null;
        Token TXT31=null;
        Token LEFTSBR32=null;
        Token RIGHTSBR33=null;
        Token NUM34=null;
        Token LEFTSBR35=null;
        Token RIGHTSBR36=null;
        Token set37=null;
        EugeneParser.numdecl_return n =null;

        EugeneParser.txtdecl_return t =null;

        EugeneParser.txtlistdecl_return tl =null;

        EugeneParser.numlistdecl_return nl =null;

        EugeneParser.booldecl_return b =null;


        Object NUM29_tree=null;
        Object TXT30_tree=null;
        Object TXT31_tree=null;
        Object LEFTSBR32_tree=null;
        Object RIGHTSBR33_tree=null;
        Object NUM34_tree=null;
        Object LEFTSBR35_tree=null;
        Object RIGHTSBR36_tree=null;
        Object set37_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:787:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] )
            int alt6=5;
            switch ( input.LA(1) ) {
            case NUM:
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==LEFTSBR) ) {
                    alt6=4;
                }
                else if ( (LA6_1==ID) ) {
                    alt6=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;

                }
                }
                break;
            case TXT:
                {
                int LA6_2 = input.LA(2);

                if ( (LA6_2==LEFTSBR) ) {
                    alt6=3;
                }
                else if ( (LA6_2==ID) ) {
                    alt6=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 2, input);

                    throw nvae;

                }
                }
                break;
            case BOOL:
            case BOOLEAN:
                {
                alt6=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:787:4: NUM n= numdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM29=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1213); 
                    NUM29_tree = 
                    (Object)adaptor.create(NUM29)
                    ;
                    adaptor.addChild(root_0, NUM29_tree);


                    pushFollow(FOLLOW_numdecl_in_variableDeclaration1217);
                    n=numdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, n.getTree());


                    if(!defer) {
                        retval.varname = (n!=null?n.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:792:4: TXT t= txtdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT30=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1228); 
                    TXT30_tree = 
                    (Object)adaptor.create(TXT30)
                    ;
                    adaptor.addChild(root_0, TXT30_tree);


                    pushFollow(FOLLOW_txtdecl_in_variableDeclaration1232);
                    t=txtdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t.getTree());


                    if(!defer) {
                        retval.varname = (t!=null?t.varname:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:797:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT31=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1243); 
                    TXT31_tree = 
                    (Object)adaptor.create(TXT31)
                    ;
                    adaptor.addChild(root_0, TXT31_tree);


                    LEFTSBR32=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1245); 
                    LEFTSBR32_tree = 
                    (Object)adaptor.create(LEFTSBR32)
                    ;
                    adaptor.addChild(root_0, LEFTSBR32_tree);


                    RIGHTSBR33=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1247); 
                    RIGHTSBR33_tree = 
                    (Object)adaptor.create(RIGHTSBR33)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR33_tree);


                    pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration1251);
                    tl=txtlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tl.getTree());


                    if(!defer) {
                        retval.varname = (tl!=null?tl.varname:null);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:802:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM34=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1262); 
                    NUM34_tree = 
                    (Object)adaptor.create(NUM34)
                    ;
                    adaptor.addChild(root_0, NUM34_tree);


                    LEFTSBR35=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1264); 
                    LEFTSBR35_tree = 
                    (Object)adaptor.create(LEFTSBR35)
                    ;
                    adaptor.addChild(root_0, LEFTSBR35_tree);


                    RIGHTSBR36=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1266); 
                    RIGHTSBR36_tree = 
                    (Object)adaptor.create(RIGHTSBR36)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR36_tree);


                    pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1270);
                    nl=numlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, nl.getTree());


                    if(!defer) {
                        retval.varname = (nl!=null?nl.varname:null);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:4: ( BOOLEAN | BOOL ) b= booldecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    set37=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set37)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_booldecl_in_variableDeclaration1289);
                    b=booldecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, b.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:814:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
    public final EugeneParser.numdecl_return numdecl(boolean defer) throws RecognitionException {
        EugeneParser.numdecl_return retval = new EugeneParser.numdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID38=null;
        Token COMMA39=null;
        Token ID41=null;
        Token EQUALS42=null;
        Token COMMA43=null;
        EugeneParser.expr_return ex =null;

        EugeneParser.numdecl_return numdecl40 =null;

        EugeneParser.numdecl_return numdecl44 =null;


        Object ID38_tree=null;
        Object COMMA39_tree=null;
        Object ID41_tree=null;
        Object EQUALS42_tree=null;
        Object COMMA43_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:816:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ID) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==EQUALS) ) {
                    alt9=2;
                }
                else if ( (LA9_1==COMMA||LA9_1==RIGHTP||LA9_1==SEMIC) ) {
                    alt9=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:816:4: ID ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID38=(Token)match(input,ID,FOLLOW_ID_in_numdecl1312); 
                    ID38_tree = 
                    (Object)adaptor.create(ID38)
                    ;
                    adaptor.addChild(root_0, ID38_tree);



                    if(!defer) {
                        declareVariableNoValue((ID38!=null?ID38.getText():null), EugeneConstants.NUM);
                        retval.varname = (ID38!=null?ID38.getText():null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:821:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:821:6: COMMA numdecl[defer]
                            {
                            COMMA39=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1318); 
                            COMMA39_tree = 
                            (Object)adaptor.create(COMMA39)
                            ;
                            adaptor.addChild(root_0, COMMA39_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1320);
                            numdecl40=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl40.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:822:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID41=(Token)match(input,ID,FOLLOW_ID_in_numdecl1328); 
                    ID41_tree = 
                    (Object)adaptor.create(ID41)
                    ;
                    adaptor.addChild(root_0, ID41_tree);


                    EQUALS42=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1330); 
                    EQUALS42_tree = 
                    (Object)adaptor.create(EQUALS42)
                    ;
                    adaptor.addChild(root_0, EQUALS42_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:822:14: (ex= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:822:15: ex= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_numdecl1335);
                    ex=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ex.getTree());

                    }



                    if(!defer) {
                        declareVariableWithValueNum((ID41!=null?ID41.getText():null), (ex!=null?ex.p:null), (ex!=null?ex.index:0));
                        retval.varname = (ID41!=null?ID41.getText():null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:827:5: ( COMMA numdecl[defer] )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==COMMA) ) {
                        int LA8_1 = input.LA(2);

                        if ( (LA8_1==ID) ) {
                            alt8=1;
                        }
                    }
                    switch (alt8) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:827:6: COMMA numdecl[defer]
                            {
                            COMMA43=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1343); 
                            COMMA43_tree = 
                            (Object)adaptor.create(COMMA43)
                            ;
                            adaptor.addChild(root_0, COMMA43_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1345);
                            numdecl44=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl44.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:830:1: txtdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? );
    public final EugeneParser.txtdecl_return txtdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtdecl_return retval = new EugeneParser.txtdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID45=null;
        Token COMMA46=null;
        Token EQUALS48=null;
        Token COMMA49=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtdecl_return txtdecl47 =null;

        EugeneParser.txtdecl_return txtdecl50 =null;


        Object var_tree=null;
        Object ID45_tree=null;
        Object COMMA46_tree=null;
        Object EQUALS48_tree=null;
        Object COMMA49_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:832:2: ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ID) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==EQUALS) ) {
                    alt12=2;
                }
                else if ( (LA12_1==COMMA||LA12_1==RIGHTP||LA12_1==SEMIC) ) {
                    alt12=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:832:4: ID ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID45=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1365); 
                    ID45_tree = 
                    (Object)adaptor.create(ID45)
                    ;
                    adaptor.addChild(root_0, ID45_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID45!=null?ID45.getText():null), EugeneConstants.TXT);
                    			retval.varname = (ID45!=null?ID45.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:838:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:838:6: COMMA txtdecl[defer]
                            {
                            COMMA46=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1372); 
                            COMMA46_tree = 
                            (Object)adaptor.create(COMMA46)
                            ;
                            adaptor.addChild(root_0, COMMA46_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1374);
                            txtdecl47=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl47.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:840:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1385); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS48=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1387); 
                    EQUALS48_tree = 
                    (Object)adaptor.create(EQUALS48)
                    ;
                    adaptor.addChild(root_0, EQUALS48_tree);


                    pushFollow(FOLLOW_expr_in_txtdecl1391);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxt((var!=null?var.getText():null), (let!=null?let.p:null), (let!=null?let.index:0));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:846:5: ( COMMA txtdecl[defer] )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==COMMA) ) {
                        int LA11_1 = input.LA(2);

                        if ( (LA11_1==ID) ) {
                            alt11=1;
                        }
                    }
                    switch (alt11) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:846:6: COMMA txtdecl[defer]
                            {
                            COMMA49=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1399); 
                            COMMA49_tree = 
                            (Object)adaptor.create(COMMA49)
                            ;
                            adaptor.addChild(root_0, COMMA49_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1401);
                            txtdecl50=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl50.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:849:1: txtlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? );
    public final EugeneParser.txtlistdecl_return txtlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtlistdecl_return retval = new EugeneParser.txtlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID51=null;
        Token COMMA52=null;
        Token EQUALS54=null;
        Token COMMA55=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtlistdecl_return txtlistdecl53 =null;

        EugeneParser.txtlistdecl_return txtlistdecl56 =null;


        Object var_tree=null;
        Object ID51_tree=null;
        Object COMMA52_tree=null;
        Object EQUALS54_tree=null;
        Object COMMA55_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:851:2: ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==EQUALS) ) {
                    alt15=2;
                }
                else if ( (LA15_1==COMMA||LA15_1==RIGHTP||LA15_1==SEMIC) ) {
                    alt15=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:851:4: ID ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID51=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1421); 
                    ID51_tree = 
                    (Object)adaptor.create(ID51)
                    ;
                    adaptor.addChild(root_0, ID51_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID51!=null?ID51.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID51!=null?ID51.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:857:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:857:6: COMMA txtlistdecl[defer]
                            {
                            COMMA52=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1428); 
                            COMMA52_tree = 
                            (Object)adaptor.create(COMMA52)
                            ;
                            adaptor.addChild(root_0, COMMA52_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1430);
                            txtlistdecl53=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl53.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:858:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1440); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS54=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1442); 
                    EQUALS54_tree = 
                    (Object)adaptor.create(EQUALS54)
                    ;
                    adaptor.addChild(root_0, EQUALS54_tree);


                    typeList = EugeneConstants.TXT;

                    pushFollow(FOLLOW_expr_in_txtlistdecl1448);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxtList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:864:5: ( COMMA txtlistdecl[defer] )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==COMMA) ) {
                        int LA14_1 = input.LA(2);

                        if ( (LA14_1==ID) ) {
                            alt14=1;
                        }
                    }
                    switch (alt14) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:864:6: COMMA txtlistdecl[defer]
                            {
                            COMMA55=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1456); 
                            COMMA55_tree = 
                            (Object)adaptor.create(COMMA55)
                            ;
                            adaptor.addChild(root_0, COMMA55_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1458);
                            txtlistdecl56=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl56.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:867:1: numlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? );
    public final EugeneParser.numlistdecl_return numlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.numlistdecl_return retval = new EugeneParser.numlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID57=null;
        Token COMMA58=null;
        Token EQUALS60=null;
        Token COMMA61=null;
        EugeneParser.expr_return let =null;

        EugeneParser.numlistdecl_return numlistdecl59 =null;

        EugeneParser.numlistdecl_return numlistdecl62 =null;


        Object var_tree=null;
        Object ID57_tree=null;
        Object COMMA58_tree=null;
        Object EQUALS60_tree=null;
        Object COMMA61_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:869:2: ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==ID) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==EQUALS) ) {
                    alt18=2;
                }
                else if ( (LA18_1==COMMA||LA18_1==RIGHTP||LA18_1==SEMIC) ) {
                    alt18=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:869:4: ID ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID57=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1478); 
                    ID57_tree = 
                    (Object)adaptor.create(ID57)
                    ;
                    adaptor.addChild(root_0, ID57_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID57!=null?ID57.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID57!=null?ID57.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:875:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:875:6: COMMA numlistdecl[defer]
                            {
                            COMMA58=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1485); 
                            COMMA58_tree = 
                            (Object)adaptor.create(COMMA58)
                            ;
                            adaptor.addChild(root_0, COMMA58_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1487);
                            numlistdecl59=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl59.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:876:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1497); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS60=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1499); 
                    EQUALS60_tree = 
                    (Object)adaptor.create(EQUALS60)
                    ;
                    adaptor.addChild(root_0, EQUALS60_tree);


                     typeList = EugeneConstants.NUM;

                    pushFollow(FOLLOW_expr_in_numlistdecl1504);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueNumList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:882:5: ( COMMA numlistdecl[defer] )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==COMMA) ) {
                        int LA17_1 = input.LA(2);

                        if ( (LA17_1==ID) ) {
                            alt17=1;
                        }
                    }
                    switch (alt17) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:882:6: COMMA numlistdecl[defer]
                            {
                            COMMA61=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1512); 
                            COMMA61_tree = 
                            (Object)adaptor.create(COMMA61)
                            ;
                            adaptor.addChild(root_0, COMMA61_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1514);
                            numlistdecl62=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl62.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:885:1: booldecl[boolean defer] returns [String varname] : ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] );
    public final EugeneParser.booldecl_return booldecl(boolean defer) throws RecognitionException {
        EugeneParser.booldecl_return retval = new EugeneParser.booldecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID63=null;
        Token COMMA64=null;
        Token EQUALS66=null;
        EugeneParser.expr_return let =null;

        EugeneParser.booldecl_return booldecl65 =null;


        Object var_tree=null;
        Object ID63_tree=null;
        Object COMMA64_tree=null;
        Object EQUALS66_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:887:2: ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ID) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==EQUALS) ) {
                    alt20=2;
                }
                else if ( (LA20_1==COMMA||LA20_1==RIGHTP||LA20_1==SEMIC) ) {
                    alt20=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:887:4: ID ( COMMA booldecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID63=(Token)match(input,ID,FOLLOW_ID_in_booldecl1534); 
                    ID63_tree = 
                    (Object)adaptor.create(ID63)
                    ;
                    adaptor.addChild(root_0, ID63_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID63!=null?ID63.getText():null), EugeneConstants.BOOLEAN);
                    			retval.varname = (ID63!=null?ID63.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:5: ( COMMA booldecl[defer] )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==COMMA) ) {
                        int LA19_1 = input.LA(2);

                        if ( (LA19_1==ID) ) {
                            alt19=1;
                        }
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:6: COMMA booldecl[defer]
                            {
                            COMMA64=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1541); 
                            COMMA64_tree = 
                            (Object)adaptor.create(COMMA64)
                            ;
                            adaptor.addChild(root_0, COMMA64_tree);


                            pushFollow(FOLLOW_booldecl_in_booldecl1543);
                            booldecl65=booldecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, booldecl65.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:894:4: var= ID EQUALS let= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1553); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS66=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1555); 
                    EQUALS66_tree = 
                    (Object)adaptor.create(EQUALS66)
                    ;
                    adaptor.addChild(root_0, EQUALS66_tree);


                    pushFollow(FOLLOW_expr_in_booldecl1559);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:903:1: propertyDeclaration[boolean defer] : PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP ;
    public final EugeneParser.propertyDeclaration_return propertyDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.propertyDeclaration_return retval = new EugeneParser.propertyDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token PROPERTY67=null;
        Token LEFTP68=null;
        Token RIGHTP69=null;
        EugeneParser.propertyType_return typeToken =null;


        Object nameToken_tree=null;
        Object PROPERTY67_tree=null;
        Object LEFTP68_tree=null;
        Object RIGHTP69_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:904:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:904:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            PROPERTY67=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1577); 
            PROPERTY67_tree = 
            (Object)adaptor.create(PROPERTY67)
            ;
            adaptor.addChild(root_0, PROPERTY67_tree);


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1581); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            LEFTP68=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1583); 
            LEFTP68_tree = 
            (Object)adaptor.create(LEFTP68)
            ;
            adaptor.addChild(root_0, LEFTP68_tree);


            pushFollow(FOLLOW_propertyType_in_propertyDeclaration1587);
            typeToken=propertyType();

            state._fsp--;

            adaptor.addChild(root_0, typeToken.getTree());

            RIGHTP69=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1589); 
            RIGHTP69_tree = 
            (Object)adaptor.create(RIGHTP69)
            ;
            adaptor.addChild(root_0, RIGHTP69_tree);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:917:1: propertyType returns [String type] : ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) );
    public final EugeneParser.propertyType_return propertyType() throws RecognitionException {
        EugeneParser.propertyType_return retval = new EugeneParser.propertyType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token TXT70=null;
        Token TXT71=null;
        Token LEFTSBR72=null;
        Token RIGHTSBR73=null;
        Token NUM74=null;
        Token NUM75=null;
        Token LEFTSBR76=null;
        Token RIGHTSBR77=null;
        Token set78=null;

        Object TXT70_tree=null;
        Object TXT71_tree=null;
        Object LEFTSBR72_tree=null;
        Object RIGHTSBR73_tree=null;
        Object NUM74_tree=null;
        Object NUM75_tree=null;
        Object LEFTSBR76_tree=null;
        Object RIGHTSBR77_tree=null;
        Object set78_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:919:2: ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) )
            int alt21=5;
            switch ( input.LA(1) ) {
            case TXT:
                {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==LEFTSBR) ) {
                    alt21=2;
                }
                else if ( (LA21_1==RIGHTP) ) {
                    alt21=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;

                }
                }
                break;
            case NUM:
                {
                int LA21_2 = input.LA(2);

                if ( (LA21_2==LEFTSBR) ) {
                    alt21=4;
                }
                else if ( (LA21_2==RIGHTP) ) {
                    alt21=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 2, input);

                    throw nvae;

                }
                }
                break;
            case BOOL:
            case BOOLEAN:
                {
                alt21=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:919:4: TXT
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT70=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1608); 
                    TXT70_tree = 
                    (Object)adaptor.create(TXT70)
                    ;
                    adaptor.addChild(root_0, TXT70_tree);



                    retval.type = EugeneConstants.TXT;	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:4: TXT LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT71=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1615); 
                    TXT71_tree = 
                    (Object)adaptor.create(TXT71)
                    ;
                    adaptor.addChild(root_0, TXT71_tree);


                    LEFTSBR72=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1617); 
                    LEFTSBR72_tree = 
                    (Object)adaptor.create(LEFTSBR72)
                    ;
                    adaptor.addChild(root_0, LEFTSBR72_tree);


                    RIGHTSBR73=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1619); 
                    RIGHTSBR73_tree = 
                    (Object)adaptor.create(RIGHTSBR73)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR73_tree);



                    retval.type = EugeneConstants.TXTLIST;	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:925:4: NUM
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM74=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1626); 
                    NUM74_tree = 
                    (Object)adaptor.create(NUM74)
                    ;
                    adaptor.addChild(root_0, NUM74_tree);



                    retval.type = EugeneConstants.NUM;	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:928:4: NUM LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM75=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1634); 
                    NUM75_tree = 
                    (Object)adaptor.create(NUM75)
                    ;
                    adaptor.addChild(root_0, NUM75_tree);


                    LEFTSBR76=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1636); 
                    LEFTSBR76_tree = 
                    (Object)adaptor.create(LEFTSBR76)
                    ;
                    adaptor.addChild(root_0, LEFTSBR76_tree);


                    RIGHTSBR77=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1638); 
                    RIGHTSBR77_tree = 
                    (Object)adaptor.create(RIGHTSBR77)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR77_tree);



                    retval.type = EugeneConstants.NUMLIST;	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:4: ( BOOLEAN | BOOL )
                    {
                    root_0 = (Object)adaptor.nil();


                    set78=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set78)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:940:1: typeDeclaration[boolean defer] : ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? );
    public final EugeneParser.typeDeclaration_return typeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.typeDeclaration_return retval = new EugeneParser.typeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token TYPE80=null;
        Token LEFTP81=null;
        Token RIGHTP82=null;
        EugeneParser.listOfIDs_return lstToken =null;

        EugeneParser.partTypeDeclaration_return partTypeDeclaration79 =null;


        Object nameToken_tree=null;
        Object TYPE80_tree=null;
        Object LEFTP81_tree=null;
        Object RIGHTP82_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:941:2: ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0 >= PART && LA24_0 <= PART_TYPE)) ) {
                alt24=1;
            }
            else if ( (LA24_0==TYPE) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:941:4: partTypeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1667);
                    partTypeDeclaration79=partTypeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, partTypeDeclaration79.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:942:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:942:4: ( TYPE )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:942:5: TYPE
                    {
                    TYPE80=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1674); 
                    TYPE80_tree = 
                    (Object)adaptor.create(TYPE80)
                    ;
                    adaptor.addChild(root_0, TYPE80_tree);


                    }


                    nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1679); 
                    nameToken_tree = 
                    (Object)adaptor.create(nameToken)
                    ;
                    adaptor.addChild(root_0, nameToken_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:942:24: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==LEFTP) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:942:25: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                            {
                            LEFTP81=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1682); 
                            LEFTP81_tree = 
                            (Object)adaptor.create(LEFTP81)
                            ;
                            adaptor.addChild(root_0, LEFTP81_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:942:31: (lstToken= listOfIDs[defer] )?
                            int alt22=2;
                            int LA22_0 = input.LA(1);

                            if ( (LA22_0==ID) ) {
                                alt22=1;
                            }
                            switch (alt22) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:942:32: lstToken= listOfIDs[defer]
                                    {
                                    pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1687);
                                    lstToken=listOfIDs(defer);

                                    state._fsp--;

                                    adaptor.addChild(root_0, lstToken.getTree());

                                    }
                                    break;

                            }


                            RIGHTP82=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1692); 
                            RIGHTP82_tree = 
                            (Object)adaptor.create(RIGHTP82)
                            ;
                            adaptor.addChild(root_0, RIGHTP82_tree);


                            }
                            break;

                    }



                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:955:1: partTypeDeclaration[boolean defer] : ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? ;
    public final EugeneParser.partTypeDeclaration_return partTypeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.partTypeDeclaration_return retval = new EugeneParser.partTypeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token set83=null;
        Token LEFTP84=null;
        Token RIGHTP85=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object nameToken_tree=null;
        Object set83_tree=null;
        Object LEFTP84_tree=null;
        Object RIGHTP85_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:956:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:956:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            set83=(Token)input.LT(1);

            if ( (input.LA(1) >= PART && input.LA(1) <= PART_TYPE) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set83)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1720); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:956:35: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==LEFTP) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:956:36: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                    {
                    LEFTP84=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1723); 
                    LEFTP84_tree = 
                    (Object)adaptor.create(LEFTP84)
                    ;
                    adaptor.addChild(root_0, LEFTP84_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:956:42: (lstToken= listOfIDs[defer] )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==ID) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:956:43: lstToken= listOfIDs[defer]
                            {
                            pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1728);
                            lstToken=listOfIDs(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lstToken.getTree());

                            }
                            break;

                    }


                    RIGHTP85=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1733); 
                    RIGHTP85_tree = 
                    (Object)adaptor.create(RIGHTP85)
                    ;
                    adaptor.addChild(root_0, RIGHTP85_tree);


                    }
                    break;

            }



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:972:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
    public final EugeneParser.containerDeclaration_return containerDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.containerDeclaration_return retval = new EugeneParser.containerDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token c=null;
        Token a=null;
        Token name=null;
        Token LEFTSBR86=null;
        Token RIGHTSBR87=null;
        Token LEFTP88=null;
        Token RIGHTP90=null;
        EugeneParser.list_of_declarations_return list_of_declarations89 =null;


        Object c_tree=null;
        Object a_tree=null;
        Object name_tree=null;
        Object LEFTSBR86_tree=null;
        Object RIGHTSBR87_tree=null;
        Object LEFTP88_tree=null;
        Object RIGHTP90_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:2: ( (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==COLLECTION) ) {
                alt28=1;
            }
            else if ( (LA28_0==ARRAY) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }
            switch (alt28) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:5: c= COLLECTION
                    {
                    c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1760); 
                    c_tree = 
                    (Object)adaptor.create(c)
                    ;
                    adaptor.addChild(root_0, c_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:21: a= ARRAY ( LEFTSBR RIGHTSBR )?
                    {
                    a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1767); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:29: ( LEFTSBR RIGHTSBR )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==LEFTSBR) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:974:30: LEFTSBR RIGHTSBR
                            {
                            LEFTSBR86=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1770); 
                            LEFTSBR86_tree = 
                            (Object)adaptor.create(LEFTSBR86)
                            ;
                            adaptor.addChild(root_0, LEFTSBR86_tree);


                            RIGHTSBR87=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1772); 
                            RIGHTSBR87_tree = 
                            (Object)adaptor.create(RIGHTSBR87)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR87_tree);


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1780); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
            adaptor.addChild(root_0, name_tree);



            if(!defer) {
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:988:4: ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==LEFTP) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:988:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
                    {
                    LEFTP88=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1785); 
                    LEFTP88_tree = 
                    (Object)adaptor.create(LEFTP88)
                    ;
                    adaptor.addChild(root_0, LEFTP88_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:988:11: ( list_of_declarations[defer] )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==ARRAY||(LA29_0 >= BOOL && LA29_0 <= COLLECTION)||LA29_0==DEVICE||LA29_0==DOLLAR||(LA29_0 >= FALSE_LC && LA29_0 <= FALSE_UC)||LA29_0==GRAMMAR||LA29_0==ID||LA29_0==INTERACTION||(LA29_0 >= LEFTP && LA29_0 <= LEFTSBR)||LA29_0==MINUS||(LA29_0 >= NUM && LA29_0 <= PART_TYPE)||(LA29_0 >= PROPERTY && LA29_0 <= REAL)||(LA29_0 >= RULE && LA29_0 <= SAVE_UC)||(LA29_0 >= SIZEOF_LC && LA29_0 <= STORE_UC)||(LA29_0 >= STRING && LA29_0 <= TYPE)) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:988:12: list_of_declarations[defer]
                            {
                            pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1788);
                            list_of_declarations89=list_of_declarations(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, list_of_declarations89.getTree());

                            }
                            break;

                    }


                    RIGHTP90=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1793); 
                    RIGHTP90_tree = 
                    (Object)adaptor.create(RIGHTP90)
                    ;
                    adaptor.addChild(root_0, RIGHTP90_tree);


                    }
                    break;

            }



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
    public final EugeneParser.list_of_declarations_return list_of_declarations(boolean defer) throws RecognitionException {
        EugeneParser.list_of_declarations_return retval = new EugeneParser.list_of_declarations_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA91=null;
        EugeneParser.declarationStatement_return ds =null;

        EugeneParser.expr_return exp =null;

        EugeneParser.list_of_declarations_return lod =null;


        Object COMMA91_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1005:2: ( (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1005:4: (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1005:4: (ds= declarationStatement[defer] |exp= expr[defer] )
            int alt31=2;
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
                alt31=1;
                }
                break;
            case ID:
                {
                int LA31_2 = input.LA(2);

                if ( (LA31_2==DOLLAR||LA31_2==ID||LA31_2==LC_INDUCES||LA31_2==LC_REPRESSES||LA31_2==UC_INDUCES||LA31_2==UC_REPRESSES) ) {
                    alt31=1;
                }
                else if ( (LA31_2==COMMA||LA31_2==DIV||LA31_2==DOT||LA31_2==LEFTSBR||LA31_2==MINUS||LA31_2==MULT||LA31_2==PLUS||LA31_2==RIGHTP) ) {
                    alt31=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 2, input);

                    throw nvae;

                }
                }
                break;
            case DOLLAR:
            case FALSE_LC:
            case FALSE_UC:
            case LEFTP:
            case LEFTSBR:
            case MINUS:
            case NUMBER:
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
                alt31=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }

            switch (alt31) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1005:6: ds= declarationStatement[defer]
                    {
                    pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1826);
                    ds=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ds.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1005:39: exp= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_list_of_declarations1833);
                    exp=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());


                    if(!defer) {
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:5: ( COMMA lod= list_of_declarations[defer] )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==COMMA) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:7: COMMA lod= list_of_declarations[defer]
                    {
                    COMMA91=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1841); 
                    COMMA91_tree = 
                    (Object)adaptor.create(COMMA91)
                    ;
                    adaptor.addChild(root_0, COMMA91_tree);


                    pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations1845);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1025:1: instantiation[boolean defer] : t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
    public final EugeneParser.instantiation_return instantiation(boolean defer) throws RecognitionException {
        EugeneParser.instantiation_return retval = new EugeneParser.instantiation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token t=null;
        Token LEFTP92=null;
        Token RIGHTP93=null;
        EugeneParser.dynamic_naming_return n =null;

        EugeneParser.listOfDotValues_return dotToken =null;

        EugeneParser.listOfValues_return valueToken =null;


        Object t_tree=null;
        Object LEFTP92_tree=null;
        Object RIGHTP93_tree=null;


        NamedElement type = null;
        String instance_name = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1030:2: (t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1030:4: t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            t=(Token)match(input,ID,FOLLOW_ID_in_instantiation1873); 
            t_tree = 
            (Object)adaptor.create(t)
            ;
            adaptor.addChild(root_0, t_tree);



            if(!defer) {
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
            	

            pushFollow(FOLLOW_dynamic_naming_in_instantiation1879);
            n=dynamic_naming(defer);

            state._fsp--;

            adaptor.addChild(root_0, n.getTree());


            if(!defer) {
                instance_name = (n!=null?n.name:null);	
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1050:4: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==LEFTP) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1050:6: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
                    {
                    LEFTP92=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation1886); 
                    LEFTP92_tree = 
                    (Object)adaptor.create(LEFTP92)
                    ;
                    adaptor.addChild(root_0, LEFTP92_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1050:12: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
                    int alt33=3;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==DOT) ) {
                        alt33=1;
                    }
                    else if ( (LA33_0==DOLLAR||(LA33_0 >= FALSE_LC && LA33_0 <= FALSE_UC)||LA33_0==ID||(LA33_0 >= LEFTP && LA33_0 <= LEFTSBR)||LA33_0==MINUS||LA33_0==NUMBER||(LA33_0 >= RANDOM_LC && LA33_0 <= REAL)||(LA33_0 >= SAVE_LC && LA33_0 <= SAVE_UC)||(LA33_0 >= SIZEOF_LC && LA33_0 <= STORE_UC)||(LA33_0 >= STRING && LA33_0 <= TRUE_UC)) ) {
                        alt33=2;
                    }
                    switch (alt33) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1050:13: dotToken= listOfDotValues[defer]
                            {
                            pushFollow(FOLLOW_listOfDotValues_in_instantiation1891);
                            dotToken=listOfDotValues(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dotToken.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1050:45: valueToken= listOfValues[defer, (ComponentType)type]
                            {
                            pushFollow(FOLLOW_listOfValues_in_instantiation1896);
                            valueToken=listOfValues(defer, (ComponentType)type);

                            state._fsp--;

                            adaptor.addChild(root_0, valueToken.getTree());

                            }
                            break;

                    }


                    RIGHTP93=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation1901); 
                    RIGHTP93_tree = 
                    (Object)adaptor.create(RIGHTP93)
                    ;
                    adaptor.addChild(root_0, RIGHTP93_tree);


                    }
                    break;

            }



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1084:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
    public final EugeneParser.listOfDotValues_return listOfDotValues(boolean defer) throws RecognitionException {
        EugeneParser.listOfDotValues_return retval = new EugeneParser.listOfDotValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token prop=null;
        Token p=null;
        Token DOT94=null;
        Token LEFTP95=null;
        Token RIGHTP96=null;
        Token COMMA97=null;
        Token DOT98=null;
        Token LEFTP99=null;
        Token RIGHTP100=null;
        EugeneParser.expr_return v1 =null;

        EugeneParser.expr_return v2 =null;


        Object prop_tree=null;
        Object p_tree=null;
        Object DOT94_tree=null;
        Object LEFTP95_tree=null;
        Object RIGHTP96_tree=null;
        Object COMMA97_tree=null;
        Object DOT98_tree=null;
        Object LEFTP99_tree=null;
        Object RIGHTP100_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1085:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1085:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            {
            root_0 = (Object)adaptor.nil();


            DOT94=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1924); 
            DOT94_tree = 
            (Object)adaptor.create(DOT94)
            ;
            adaptor.addChild(root_0, DOT94_tree);


            prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1928); 
            prop_tree = 
            (Object)adaptor.create(prop)
            ;
            adaptor.addChild(root_0, prop_tree);



            if(!defer) {		
                try {
                    addToPropertyListHolder((prop!=null?prop.getText():null));
                } catch(EugeneException ee) {
                    printError(ee.getMessage());
                }				
            }			
            		

            LEFTP95=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1934); 
            LEFTP95_tree = 
            (Object)adaptor.create(LEFTP95)
            ;
            adaptor.addChild(root_0, LEFTP95_tree);


            pushFollow(FOLLOW_expr_in_listOfDotValues1938);
            v1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, v1.getTree());


            if(!defer) {			
                try {
                    addToPropertyValuesHolder((prop!=null?prop.getText():null), (v1!=null?v1.p:null), (v1!=null?v1.index:0));
                } catch(EugeneException ee) {
                    printError(ee.getMessage());
                }				
            }				
            			

            RIGHTP96=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1946); 
            RIGHTP96_tree = 
            (Object)adaptor.create(RIGHTP96)
            ;
            adaptor.addChild(root_0, RIGHTP96_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1103:13: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1103:14: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
            	    {
            	    COMMA97=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues1949); 
            	    COMMA97_tree = 
            	    (Object)adaptor.create(COMMA97)
            	    ;
            	    adaptor.addChild(root_0, COMMA97_tree);


            	    DOT98=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1951); 
            	    DOT98_tree = 
            	    (Object)adaptor.create(DOT98)
            	    ;
            	    adaptor.addChild(root_0, DOT98_tree);


            	    p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1955); 
            	    p_tree = 
            	    (Object)adaptor.create(p)
            	    ;
            	    adaptor.addChild(root_0, p_tree);



            	    if(!defer) {			
            	        try {
            	            addToPropertyListHolder((p!=null?p.getText():null));
            	        } catch(EugeneException ee) {
            	            printError(ee.getMessage());
            	        }				
            	    }				
            	    				

            	    LEFTP99=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1963); 
            	    LEFTP99_tree = 
            	    (Object)adaptor.create(LEFTP99)
            	    ;
            	    adaptor.addChild(root_0, LEFTP99_tree);


            	    pushFollow(FOLLOW_expr_in_listOfDotValues1967);
            	    v2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, v2.getTree());


            	    if(!defer) {			
            	        try {
            	            addToPropertyValuesHolder((p!=null?p.getText():null), (v2!=null?v2.p:null), (v2!=null?v2.index:0));
            	        } catch(EugeneException ee) {
            	            printError(ee.getMessage());
            	        }				
            	    }				
            	    					

            	    RIGHTP100=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1977); 
            	    RIGHTP100_tree = 
            	    (Object)adaptor.create(RIGHTP100)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP100_tree);


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
    // $ANTLR end "listOfDotValues"


    public static class listOfValues_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfValues"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1124:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
    public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
        EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA101=null;
        EugeneParser.expr_return val1 =null;

        EugeneParser.expr_return val2 =null;


        Object COMMA101_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1125:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1126:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
            {
            root_0 = (Object)adaptor.nil();



            if(!defer) {	

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
            		

            pushFollow(FOLLOW_expr_in_listOfValues1998);
            val1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, val1.getTree());


            if(!defer) {
                propertyValuesHolder.add((val1!=null?val1.p:null));
            }				
            			

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1150:6: ( COMMA val2= expr[defer] )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==COMMA) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1150:7: COMMA val2= expr[defer]
            	    {
            	    COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues2004); 
            	    COMMA101_tree = 
            	    (Object)adaptor.create(COMMA101)
            	    ;
            	    adaptor.addChild(root_0, COMMA101_tree);



            	    if(!defer) {	

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
            	                   

            	    pushFollow(FOLLOW_expr_in_listOfValues2010);
            	    val2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, val2.getTree());


            	    if(!defer) {
            	        propertyValuesHolder.add((val2!=null?val2.p:null));
            	    }				
            	    		

            	    }
            	    break;

            	default :
            	    break loop36;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1180:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
    public final EugeneParser.deviceDeclaration_return deviceDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.deviceDeclaration_return retval = new EugeneParser.deviceDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token DEVICE102=null;
        Token LEFTP103=null;
        Token RIGHTP104=null;
        EugeneParser.deviceComponents_return dcs =null;


        Object n_tree=null;
        Object DEVICE102_tree=null;
        Object LEFTP103_tree=null;
        Object RIGHTP104_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1181:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1181:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            DEVICE102=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration2029); 
            DEVICE102_tree = 
            (Object)adaptor.create(DEVICE102)
            ;
            adaptor.addChild(root_0, DEVICE102_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration2033); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1181:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LEFTP) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1181:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
                    {
                    LEFTP103=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration2036); 
                    LEFTP103_tree = 
                    (Object)adaptor.create(LEFTP103)
                    ;
                    adaptor.addChild(root_0, LEFTP103_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1181:23: (dcs= deviceComponents[defer] )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==ID||LA37_0==LEFTSBR||LA37_0==MINUS||LA37_0==PLUS) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1181:24: dcs= deviceComponents[defer]
                            {
                            pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration2041);
                            dcs=deviceComponents(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dcs.getTree());

                            }
                            break;

                    }


                    RIGHTP104=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration2046); 
                    RIGHTP104_tree = 
                    (Object)adaptor.create(RIGHTP104)
                    ;
                    adaptor.addChild(root_0, RIGHTP104_tree);


                    }
                    break;

            }



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1195:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
    public final EugeneParser.deviceComponents_return deviceComponents(boolean defer) throws RecognitionException {
        EugeneParser.deviceComponents_return retval = new EugeneParser.deviceComponents_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal105=null;
        EugeneParser.selection_return s =null;

        EugeneParser.deviceComponents_return dcs =null;


        Object char_literal105_tree=null;


        retval.lstComponents = new ArrayList<List<NamedElement>>();
        retval.lstOrientations = new ArrayList<List<Orientation>>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1201:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1201:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selection_in_deviceComponents2077);
            s=selection(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());


            if(!defer) {
                retval.lstComponents.add((s!=null?s.components:null));
                retval.lstOrientations.add((s!=null?s.orientations:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1206:4: ( ',' dcs= deviceComponents[defer] )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==COMMA) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1206:5: ',' dcs= deviceComponents[defer]
                    {
                    char_literal105=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents2083); 
                    char_literal105_tree = 
                    (Object)adaptor.create(char_literal105)
                    ;
                    adaptor.addChild(root_0, char_literal105_tree);


                    pushFollow(FOLLOW_deviceComponents_in_deviceComponents2087);
                    dcs=deviceComponents(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, dcs.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1214:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
    public final EugeneParser.selection_return selection(boolean defer) throws RecognitionException {
        EugeneParser.selection_return retval = new EugeneParser.selection_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTSBR106=null;
        Token RIGHTSBR107=null;
        EugeneParser.selection_list_return sl =null;

        EugeneParser.device_component_return dc =null;


        Object LEFTSBR106_tree=null;
        Object RIGHTSBR107_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1216:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==LEFTSBR) ) {
                alt40=1;
            }
            else if ( (LA40_0==ID||LA40_0==MINUS||LA40_0==PLUS) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }
            switch (alt40) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1216:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR106=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection2111); 
                    LEFTSBR106_tree = 
                    (Object)adaptor.create(LEFTSBR106)
                    ;
                    adaptor.addChild(root_0, LEFTSBR106_tree);


                    pushFollow(FOLLOW_selection_list_in_selection2115);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());

                    RIGHTSBR107=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection2118); 
                    RIGHTSBR107_tree = 
                    (Object)adaptor.create(RIGHTSBR107)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR107_tree);



                    if(!defer) {
                        retval.components = (sl!=null?sl.components:null);
                        retval.orientations = (sl!=null?sl.orientations:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1222:4: dc= device_component[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_device_component_in_selection2127);
                    dc=device_component(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, dc.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1233:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
    public final EugeneParser.selection_list_return selection_list(boolean defer) throws RecognitionException {
        EugeneParser.selection_list_return retval = new EugeneParser.selection_list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIPE108=null;
        EugeneParser.device_component_return dc =null;

        EugeneParser.selection_list_return sl =null;


        Object PIPE108_tree=null;


        retval.components = new ArrayList<NamedElement>();
        retval.orientations = new ArrayList<Orientation>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1239:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1239:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_device_component_in_selection_list2156);
            dc=device_component(defer);

            state._fsp--;

            adaptor.addChild(root_0, dc.getTree());


            if(!defer) {
                retval.components.add((dc!=null?dc.component:null));
                retval.orientations.add((dc!=null?dc.orientation:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1244:4: ( PIPE sl= selection_list[defer] )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==PIPE) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1244:5: PIPE sl= selection_list[defer]
                    {
                    PIPE108=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list2162); 
                    PIPE108_tree = 
                    (Object)adaptor.create(PIPE108)
                    ;
                    adaptor.addChild(root_0, PIPE108_tree);


                    pushFollow(FOLLOW_selection_list_in_selection_list2166);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1252:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
    public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
        EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token directionToken=null;
        Token idToken=null;

        Object directionToken_tree=null;
        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1254:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1254:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1254:4: (directionToken= ( MINUS | PLUS ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==MINUS||LA42_0==PLUS) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1254:5: directionToken= ( MINUS | PLUS )
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


            idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component2202); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1302:1: assignment[boolean defer] : lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
    public final EugeneParser.assignment_return assignment(boolean defer) throws RecognitionException {
        EugeneParser.assignment_return retval = new EugeneParser.assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token a=null;
        Token EQUALS109=null;
        EugeneParser.lhs_assignment_return lhs =null;

        EugeneParser.rhs_assignment_return rhs =null;


        Object a_tree=null;
        Object EQUALS109_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1303:2: (lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1303:4: lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_lhs_assignment_in_assignment2222);
            lhs=lhs_assignment(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            EQUALS109=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment2225); 
            EQUALS109_tree = 
            (Object)adaptor.create(EQUALS109)
            ;
            adaptor.addChild(root_0, EQUALS109_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1303:37: (a= AMP )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==AMP) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1303:38: a= AMP
                    {
                    a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment2230); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    }
                    break;

            }


            pushFollow(FOLLOW_rhs_assignment_in_assignment2236);
            rhs=rhs_assignment(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1332:1: lhs_assignment[boolean defer] : ID lhs_access[defer] ;
    public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID110=null;
        EugeneParser.lhs_access_return lhs_access111 =null;


        Object ID110_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1333:2: ( ID lhs_access[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1333:4: ID lhs_access[defer]
            {
            root_0 = (Object)adaptor.nil();


            ID110=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment2251); 
            ID110_tree = 
            (Object)adaptor.create(ID110)
            ;
            adaptor.addChild(root_0, ID110_tree);


            pushFollow(FOLLOW_lhs_access_in_lhs_assignment2253);
            lhs_access111=lhs_access(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs_access111.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1336:1: lhs_access[boolean defer] : (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] );
    public final EugeneParser.lhs_access_return lhs_access(boolean defer) throws RecognitionException {
        EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token DOT112=null;
        Token LEFTSBR113=null;
        Token set114=null;
        Token RIGHTSBR115=null;
        EugeneParser.lhs_access_return lhs_access116 =null;


        Object i_tree=null;
        Object DOT112_tree=null;
        Object LEFTSBR113_tree=null;
        Object set114_tree=null;
        Object RIGHTSBR115_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1337:2: (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==EQUALS) ) {
                alt45=1;
            }
            else if ( (LA45_0==DOT||LA45_0==LEFTSBR) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }
            switch (alt45) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1338:2: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1338:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1338:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR )
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==DOT) ) {
                        alt44=1;
                    }
                    else if ( (LA44_0==LEFTSBR) ) {
                        alt44=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 0, input);

                        throw nvae;

                    }
                    switch (alt44) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1338:5: DOT i= ID
                            {
                            DOT112=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2273); 
                            DOT112_tree = 
                            (Object)adaptor.create(DOT112)
                            ;
                            adaptor.addChild(root_0, DOT112_tree);


                            i=(Token)match(input,ID,FOLLOW_ID_in_lhs_access2277); 
                            i_tree = 
                            (Object)adaptor.create(i)
                            ;
                            adaptor.addChild(root_0, i_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1338:16: LEFTSBR ( ID | NUMBER ) RIGHTSBR
                            {
                            LEFTSBR113=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2281); 
                            LEFTSBR113_tree = 
                            (Object)adaptor.create(LEFTSBR113)
                            ;
                            adaptor.addChild(root_0, LEFTSBR113_tree);


                            set114=(Token)input.LT(1);

                            if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                                input.consume();
                                adaptor.addChild(root_0, 
                                (Object)adaptor.create(set114)
                                );
                                state.errorRecovery=false;
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            RIGHTSBR115=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2289); 
                            RIGHTSBR115_tree = 
                            (Object)adaptor.create(RIGHTSBR115)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR115_tree);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_lhs_access_in_lhs_access2292);
                    lhs_access116=lhs_access(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs_access116.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1341:1: rhs_assignment[boolean defer] returns [NamedElement e] : (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] );
    public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.functionCall_return fc =null;

        EugeneParser.dataExchange_return de =null;

        EugeneParser.expr_return exp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1343:2: (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] )
            int alt46=3;
            switch ( input.LA(1) ) {
            case PERMUTE:
            case PRODUCT:
                {
                alt46=1;
                }
                break;
            case IMPORT_LC:
            case IMPORT_UC:
            case PIGEON_LC:
            case PIGEON_UC:
            case SBOL:
                {
                alt46=2;
                }
                break;
            case DOLLAR:
            case FALSE_LC:
            case FALSE_UC:
            case ID:
            case LEFTP:
            case LEFTSBR:
            case MINUS:
            case NUMBER:
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
                alt46=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }

            switch (alt46) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1343:4: fc= functionCall[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_rhs_assignment2315);
                    fc=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, fc.getTree());


                    if(!defer) {
                        retval.e = (fc!=null?fc.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1348:4: de= dataExchange[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_rhs_assignment2325);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());


                    if(!defer) {
                        retval.e = (de!=null?de.e:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1353:4: exp= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_rhs_assignment2335);
                    exp=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1364:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
    public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
        EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token char_literal117=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object idToken_tree=null;
        Object char_literal117_tree=null;


        retval.lstElements =new ArrayList<NamedElement>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1369:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1369:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2363); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


             
            if(!defer)
                try {
                    NamedElement ne = interp.get((idToken!=null?idToken.getText():null));
                    if(null == ne) {
                       printError((idToken!=null?idToken.getText():null)+" is not declared.");
                    }
                    retval.lstElements.add(ne);
                } catch(EugeneException ee) {
                    printError(ee.getMessage());
                }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1381:4: ( ',' lstToken= listOfIDs[defer] )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==COMMA) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1381:5: ',' lstToken= listOfIDs[defer]
                    {
                    char_literal117=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2372); 
                    char_literal117_tree = 
                    (Object)adaptor.create(char_literal117)
                    ;
                    adaptor.addChild(root_0, char_literal117_tree);


                    pushFollow(FOLLOW_listOfIDs_in_listOfIDs2376);
                    lstToken=listOfIDs(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lstToken.getTree());


                    if(!defer){
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1388:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
    public final EugeneParser.ruleDeclaration_return ruleDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.ruleDeclaration_return retval = new EugeneParser.ruleDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token device=null;
        Token RULE118=null;
        Token LEFTP119=null;
        Token set120=null;
        Token COLON121=null;
        Token RIGHTP122=null;
        EugeneParser.cnf_rule_return cnf =null;


        Object name_tree=null;
        Object device_tree=null;
        Object RULE118_tree=null;
        Object LEFTP119_tree=null;
        Object set120_tree=null;
        Object COLON121_tree=null;
        Object RIGHTP122_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1390:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1390:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            RULE118=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2400); 
            RULE118_tree = 
            (Object)adaptor.create(RULE118)
            ;
            adaptor.addChild(root_0, RULE118_tree);


            name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2404); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
            adaptor.addChild(root_0, name_tree);


            LEFTP119=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2406); 
            LEFTP119_tree = 
            (Object)adaptor.create(LEFTP119)
            ;
            adaptor.addChild(root_0, LEFTP119_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1390:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1390:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1390:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LC_ON||LA48_0==UC_ON) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1390:26: ( LC_ON | UC_ON ) device= ID COLON
                    {
                    set120=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set120)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2419); 
                    device_tree = 
                    (Object)adaptor.create(device)
                    ;
                    adaptor.addChild(root_0, device_tree);


                    COLON121=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2421); 
                    COLON121_tree = 
                    (Object)adaptor.create(COLON121)
                    ;
                    adaptor.addChild(root_0, COLON121_tree);


                    }
                    break;

            }



            if(!defer) {
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
            	

            pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2429);
            cnf=cnf_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, cnf.getTree());

            }


            RIGHTP122=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2434); 
            RIGHTP122_tree = 
            (Object)adaptor.create(RIGHTP122)
            ;
            adaptor.addChild(root_0, RIGHTP122_tree);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1414:1: ruleOperator[boolean defer] : ruleOperators ;
    public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
        EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ruleOperators_return ruleOperators123 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1415:2: ( ruleOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1415:4: ruleOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_ruleOperators_in_ruleOperator2448);
            ruleOperators123=ruleOperators();

            state._fsp--;

            adaptor.addChild(root_0, ruleOperators123.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1419:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
    public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
        EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set124=null;

        Object set124_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1420:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set124=(Token)input.LT(1);

            if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 123 && input.LA(1) <= 157)||(input.LA(1) >= 159 && input.LA(1) <= 196)||(input.LA(1) >= 198 && input.LA(1) <= 200) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set124)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1463:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
    public final EugeneParser.relationalOperators_return relationalOperators() throws RecognitionException {
        EugeneParser.relationalOperators_return retval = new EugeneParser.relationalOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EQUALS125=null;
        Token EQUALS126=null;
        Token NEQUAL127=null;
        Token LTHAN128=null;
        Token GTHAN129=null;
        Token LEQUAL130=null;
        Token GEQUAL131=null;
        Token set132=null;
        Token set133=null;
        Token set134=null;
        Token set135=null;
        Token set136=null;
        Token set137=null;
        Token set138=null;
        Token set139=null;
        Token set140=null;

        Object EQUALS125_tree=null;
        Object EQUALS126_tree=null;
        Object NEQUAL127_tree=null;
        Object LTHAN128_tree=null;
        Object GTHAN129_tree=null;
        Object LEQUAL130_tree=null;
        Object GEQUAL131_tree=null;
        Object set132_tree=null;
        Object set133_tree=null;
        Object set134_tree=null;
        Object set135_tree=null;
        Object set136_tree=null;
        Object set137_tree=null;
        Object set138_tree=null;
        Object set139_tree=null;
        Object set140_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1464:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
            int alt49=15;
            switch ( input.LA(1) ) {
            case EQUALS:
                {
                alt49=1;
                }
                break;
            case NEQUAL:
                {
                alt49=2;
                }
                break;
            case LTHAN:
                {
                alt49=3;
                }
                break;
            case GTHAN:
                {
                alt49=4;
                }
                break;
            case LEQUAL:
                {
                alt49=5;
                }
                break;
            case GEQUAL:
                {
                alt49=6;
                }
                break;
            case 133:
            case 172:
                {
                alt49=7;
                }
                break;
            case 142:
            case 181:
                {
                alt49=8;
                }
                break;
            case 139:
            case 178:
                {
                alt49=9;
                }
                break;
            case 145:
            case 184:
                {
                alt49=10;
                }
                break;
            case 159:
            case 198:
                {
                alt49=11;
                }
                break;
            case 135:
            case 174:
                {
                alt49=12;
                }
                break;
            case 136:
            case 175:
                {
                alt49=13;
                }
                break;
            case 143:
            case 182:
                {
                alt49=14;
                }
                break;
            case 158:
            case 197:
                {
                alt49=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }

            switch (alt49) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1464:4: EQUALS EQUALS
                    {
                    root_0 = (Object)adaptor.nil();


                    EQUALS125=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2827); 
                    EQUALS125_tree = 
                    (Object)adaptor.create(EQUALS125)
                    ;
                    adaptor.addChild(root_0, EQUALS125_tree);


                    EQUALS126=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2829); 
                    EQUALS126_tree = 
                    (Object)adaptor.create(EQUALS126)
                    ;
                    adaptor.addChild(root_0, EQUALS126_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1465:4: NEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    NEQUAL127=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators2834); 
                    NEQUAL127_tree = 
                    (Object)adaptor.create(NEQUAL127)
                    ;
                    adaptor.addChild(root_0, NEQUAL127_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1466:4: LTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    LTHAN128=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators2839); 
                    LTHAN128_tree = 
                    (Object)adaptor.create(LTHAN128)
                    ;
                    adaptor.addChild(root_0, LTHAN128_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1467:4: GTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    GTHAN129=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators2844); 
                    GTHAN129_tree = 
                    (Object)adaptor.create(GTHAN129)
                    ;
                    adaptor.addChild(root_0, GTHAN129_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1468:4: LEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    LEQUAL130=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators2849); 
                    LEQUAL130_tree = 
                    (Object)adaptor.create(LEQUAL130)
                    ;
                    adaptor.addChild(root_0, LEQUAL130_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1469:4: GEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    GEQUAL131=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators2854); 
                    GEQUAL131_tree = 
                    (Object)adaptor.create(GEQUAL131)
                    ;
                    adaptor.addChild(root_0, GEQUAL131_tree);


                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:4: ( 'CONTAINS' | 'contains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set132=(Token)input.LT(1);

                    if ( input.LA(1)==133||input.LA(1)==172 ) {
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
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1471:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set133=(Token)input.LT(1);

                    if ( input.LA(1)==142||input.LA(1)==181 ) {
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
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:4: ( 'MATCHES' | 'matches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set134=(Token)input.LT(1);

                    if ( input.LA(1)==139||input.LA(1)==178 ) {
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
                case 10 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1473:4: ( 'NOTMATCHES' | 'notmatches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set135=(Token)input.LT(1);

                    if ( input.LA(1)==145||input.LA(1)==184 ) {
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
                case 11 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1474:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set136=(Token)input.LT(1);

                    if ( input.LA(1)==159||input.LA(1)==198 ) {
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
                case 12 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1475:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set137=(Token)input.LT(1);

                    if ( input.LA(1)==135||input.LA(1)==174 ) {
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
                case 13 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1476:4: ( 'EQUALS' | 'equals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set138=(Token)input.LT(1);

                    if ( input.LA(1)==136||input.LA(1)==175 ) {
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


                    }
                    break;
                case 14 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1477:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set139=(Token)input.LT(1);

                    if ( input.LA(1)==143||input.LA(1)==182 ) {
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


                    }
                    break;
                case 15 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1478:4: ( 'SOUNDSLIKE' | 'soundslike' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set140=(Token)input.LT(1);

                    if ( input.LA(1)==158||input.LA(1)==197 ) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1481:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
    public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
        EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set141=null;
        EugeneParser.or_predicate_return c =null;

        EugeneParser.cnf_rule_return cnf =null;


        Object set141_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1483:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1483:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1483:4: (c= or_predicate[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1483:5: c= or_predicate[defer]
            {
            pushFollow(FOLLOW_or_predicate_in_cnf_rule2955);
            c=or_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, c.getTree());


            if(!defer) {
                if(null == retval.lAnd) {
                    retval.lAnd = new LogicalAnd();
                }
                
                retval.lAnd.getPredicates().add((c!=null?c.p:null));
            }	
            	

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1491:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LC_AND||LA50_0==LOG_AND||LA50_0==UC_AND) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1491:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
                    {
                    set141=(Token)input.LT(1);

                    if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set141)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_cnf_rule_in_cnf_rule2973);
                    cnf=cnf_rule(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, cnf.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1498:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
    public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
        EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set142=null;
        EugeneParser.negated_predicate_return n1 =null;

        EugeneParser.negated_predicate_return n2 =null;


        Object set142_tree=null;


        LogicalOr lor = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1503:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1503:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_negated_predicate_in_or_predicate3003);
            n1=negated_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, n1.getTree());


            if(!defer) {
                retval.p = (n1!=null?n1.p:null);
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1507:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==LC_OR||LA51_0==LOG_OR||LA51_0==UC_OR) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1507:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
            	    {
            	    set142=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
            	        input.consume();
            	        adaptor.addChild(root_0, 
            	        (Object)adaptor.create(set142)
            	        );
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_negated_predicate_in_or_predicate3019);
            	    n2=negated_predicate(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, n2.getTree());


            	    if(!defer) {
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
            	    break loop51;
                }
            } while (true);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1528:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
    public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
        EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set143=null;
        EugeneParser.predicate_return c =null;


        Object set143_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1530:2: ( ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1530:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1530:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==LC_NOT||LA52_0==LOG_NOT||LA52_0==UC_NOT) ) {
                alt52=1;
            }
            else if ( (LA52_0==ID||LA52_0==LC_INDUCES||LA52_0==LC_REPRESSES||(LA52_0 >= LEFTP && LA52_0 <= LEFTSBR)||LA52_0==MINUS||LA52_0==NUMBER||LA52_0==REAL||LA52_0==STRING||LA52_0==UC_INDUCES||LA52_0==UC_REPRESSES||(LA52_0 >= 123 && LA52_0 <= 157)||(LA52_0 >= 159 && LA52_0 <= 196)||(LA52_0 >= 198 && LA52_0 <= 200)) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }
            switch (alt52) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1530:5: ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer]
                    {
                    set143=(Token)input.LT(1);

                    if ( input.LA(1)==LC_NOT||input.LA(1)==LOG_NOT||input.LA(1)==UC_NOT ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set143)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_predicate_in_negated_predicate3057);
                    c=predicate(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, c.getTree());


                    if(!defer) {
                        try {
                            retval.p = this.interp.negate((c!=null?c.p:null));
                        } catch(Exception e) {
                            printError(e.getMessage());
                        }
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1539:4: c= predicate[defer]
                    {
                    pushFollow(FOLLOW_predicate_in_negated_predicate3067);
                    c=predicate(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, c.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1546:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1548:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
            int alt55=3;
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
                    alt55=3;
                    }
                    break;
                case 133:
                case 172:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 5, input);

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
                    alt55=2;
                    }
                    break;
                case 142:
                case 181:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 7, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 8, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 9, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 10, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 11, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 12, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 13, input);

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
                    alt55=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 1, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 5, input);

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
                    alt55=3;
                    }
                    break;
                case 142:
                case 181:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 7, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 8, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 9, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 10, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 11, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 12, input);

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
                        alt55=1;
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
                        alt55=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt55=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 13, input);

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
                    alt55=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 2, input);

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
                alt55=1;
                }
                break;
            case LEFTP:
            case MINUS:
            case REAL:
            case STRING:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1548:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1548:4: (lhs= operand[defer] )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==ID||LA53_0==LEFTSBR||LA53_0==NUMBER) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1548:5: lhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate3094);
                            lhs=operand(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lhs.getTree());


                            addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_ruleOperator_in_predicate3104);
                    op=ruleOperator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, op.getTree());


                    addToken((op!=null?input.toString(op.start,op.stop):null));	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1552:5: (rhs= operand[defer] )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==ID||LA54_0==LEFTSBR||LA54_0==NUMBER) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1552:6: rhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate3113);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1566:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_predicate3127); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1583:4: exp= expressionRule[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expressionRule_in_predicate3136);
                    exp=expressionRule(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1590:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' ) ;
    public final EugeneParser.operand_return operand(boolean defer) throws RecognitionException {
        EugeneParser.operand_return retval = new EugeneParser.operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token n=null;
        Token char_literal144=null;
        Token char_literal145=null;

        Object i_tree=null;
        Object n_tree=null;
        Object char_literal144_tree=null;
        Object char_literal145_tree=null;


        NamedElement element = null;
        int constant = -1;
        int index = -1;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1597:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1597:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1597:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            int alt56=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt56=1;
                }
                break;
            case NUMBER:
                {
                alt56=2;
                }
                break;
            case LEFTSBR:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1597:5: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_operand3167); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1609:4: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3176); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);



                    if(!defer) {
                        constant = Integer.parseInt((n!=null?n.getText():null));
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1614:4: '[' n= NUMBER ']'
                    {
                    char_literal144=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand3183); 
                    char_literal144_tree = 
                    (Object)adaptor.create(char_literal144)
                    ;
                    adaptor.addChild(root_0, char_literal144_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3187); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal145=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand3189); 
                    char_literal145_tree = 
                    (Object)adaptor.create(char_literal145)
                    ;
                    adaptor.addChild(root_0, char_literal145_tree);



                    if(!defer) {
                        index = Integer.parseInt((n!=null?n.getText():null));
                    }	
                    	

                    }
                    break;

            }



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1633:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
    public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
        EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expression_return lhs =null;

        EugeneParser.exp_op_return op =null;

        EugeneParser.expression_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1635:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1635:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expression_in_expressionRule3215);
            lhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_exp_op_in_expressionRule3220);
            op=exp_op(defer);

            state._fsp--;

            adaptor.addChild(root_0, op.getTree());

            pushFollow(FOLLOW_expression_in_expressionRule3225);
            rhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1646:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
    public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
        EugeneParser.expression_return retval = new EugeneParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTP146=null;
        Token RIGHTP148=null;
        EugeneParser.exp_operand_return lhs =null;

        EugeneParser.exp_operator_return expop =null;

        EugeneParser.expression_return rhs =null;

        EugeneParser.expression_return expression147 =null;


        Object LEFTP146_tree=null;
        Object RIGHTP148_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1648:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==ID||LA58_0==MINUS||LA58_0==NUMBER||LA58_0==REAL||LA58_0==STRING) ) {
                alt58=1;
            }
            else if ( (LA58_0==LEFTP) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;

            }
            switch (alt58) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1648:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_exp_operand_in_expression3249);
                    lhs=exp_operand(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs.getTree());


                    if(!defer) {
                        retval.exp = new Expression((lhs!=null?lhs.eop:null), null, null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1652:4: (expop= exp_operator[defer] rhs= expression[defer] )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==DIV||LA57_0==MINUS||LA57_0==MULT||LA57_0==PLUS) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1652:6: expop= exp_operator[defer] rhs= expression[defer]
                            {
                            pushFollow(FOLLOW_exp_operator_in_expression3258);
                            expop=exp_operator(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, expop.getTree());

                            pushFollow(FOLLOW_expression_in_expression3263);
                            rhs=expression(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, rhs.getTree());


                            if(!defer) {
                                retval.exp = new Expression((lhs!=null?lhs.eop:null), (expop!=null?expop.op:null), (rhs!=null?rhs.exp:null));
                            }	
                            	

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1657:4: LEFTP expression[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTP146=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3275); 
                    LEFTP146_tree = 
                    (Object)adaptor.create(LEFTP146)
                    ;
                    adaptor.addChild(root_0, LEFTP146_tree);


                    pushFollow(FOLLOW_expression_in_expression3277);
                    expression147=expression(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expression147.getTree());

                    RIGHTP148=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3280); 
                    RIGHTP148_tree = 
                    (Object)adaptor.create(RIGHTP148)
                    ;
                    adaptor.addChild(root_0, RIGHTP148_tree);



                    if(!defer) {
                        	
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1664:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
    public final EugeneParser.exp_operator_return exp_operator(boolean defer) throws RecognitionException {
        EugeneParser.exp_operator_return retval = new EugeneParser.exp_operator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLUS149=null;
        Token MINUS150=null;
        Token MULT151=null;
        Token DIV152=null;

        Object PLUS149_tree=null;
        Object MINUS150_tree=null;
        Object MULT151_tree=null;
        Object DIV152_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1666:2: ( PLUS | MINUS | MULT | DIV )
            int alt59=4;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt59=1;
                }
                break;
            case MINUS:
                {
                alt59=2;
                }
                break;
            case MULT:
                {
                alt59=3;
                }
                break;
            case DIV:
                {
                alt59=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;

            }

            switch (alt59) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1666:4: PLUS
                    {
                    root_0 = (Object)adaptor.nil();


                    PLUS149=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3299); 
                    PLUS149_tree = 
                    (Object)adaptor.create(PLUS149)
                    ;
                    adaptor.addChild(root_0, PLUS149_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.PLUS;	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1671:4: MINUS
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS150=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3307); 
                    MINUS150_tree = 
                    (Object)adaptor.create(MINUS150)
                    ;
                    adaptor.addChild(root_0, MINUS150_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MINUS;	
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1676:4: MULT
                    {
                    root_0 = (Object)adaptor.nil();


                    MULT151=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3314); 
                    MULT151_tree = 
                    (Object)adaptor.create(MULT151)
                    ;
                    adaptor.addChild(root_0, MULT151_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MULT;	
                    }
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1681:4: DIV
                    {
                    root_0 = (Object)adaptor.nil();


                    DIV152=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3321); 
                    DIV152_tree = 
                    (Object)adaptor.create(DIV152)
                    ;
                    adaptor.addChild(root_0, DIV152_tree);



                    if(!defer) {	
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1688:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
    public final EugeneParser.exp_operand_return exp_operand(boolean defer) throws RecognitionException {
        EugeneParser.exp_operand_return retval = new EugeneParser.exp_operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i1=null;
        Token i2=null;
        Token n=null;
        Token r=null;
        Token s=null;
        Token DOT153=null;
        Token LEFTSBR154=null;
        Token RIGHTSBR155=null;
        Token MINUS156=null;
        Token MINUS157=null;

        Object i1_tree=null;
        Object i2_tree=null;
        Object n_tree=null;
        Object r_tree=null;
        Object s_tree=null;
        Object DOT153_tree=null;
        Object LEFTSBR154_tree=null;
        Object RIGHTSBR155_tree=null;
        Object MINUS156_tree=null;
        Object MINUS157_tree=null;


        List<NamedElement> elements = null;
        NamedElement ne = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1694:2: ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
            int alt62=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt62=1;
                }
                break;
            case NUMBER:
                {
                alt62=2;
                }
                break;
            case MINUS:
                {
                int LA62_3 = input.LA(2);

                if ( (LA62_3==NUMBER) ) {
                    alt62=3;
                }
                else if ( (LA62_3==REAL) ) {
                    alt62=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 3, input);

                    throw nvae;

                }
                }
                break;
            case REAL:
                {
                alt62=4;
                }
                break;
            case STRING:
                {
                alt62=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }

            switch (alt62) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1694:4: (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )*
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1694:4: (i1= ID DOT )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==ID) ) {
                            int LA60_1 = input.LA(2);

                            if ( (LA60_1==DOT) ) {
                                alt60=1;
                            }


                        }


                        switch (alt60) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1694:5: i1= ID DOT
                    	    {
                    	    i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3351); 
                    	    i1_tree = 
                    	    (Object)adaptor.create(i1)
                    	    ;
                    	    adaptor.addChild(root_0, i1_tree);


                    	    DOT153=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3353); 
                    	    DOT153_tree = 
                    	    (Object)adaptor.create(DOT153)
                    	    ;
                    	    adaptor.addChild(root_0, DOT153_tree);



                    	    if(!defer) {
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
                    	    break loop60;
                        }
                    } while (true);


                    i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3362); 
                    i2_tree = 
                    (Object)adaptor.create(i2)
                    ;
                    adaptor.addChild(root_0, i2_tree);



                    if(!defer) {
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
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1762:5: ( LEFTSBR n= NUMBER RIGHTSBR )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==LEFTSBR) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1762:6: LEFTSBR n= NUMBER RIGHTSBR
                    	    {
                    	    LEFTSBR154=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3368); 
                    	    LEFTSBR154_tree = 
                    	    (Object)adaptor.create(LEFTSBR154)
                    	    ;
                    	    adaptor.addChild(root_0, LEFTSBR154_tree);


                    	    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3372); 
                    	    n_tree = 
                    	    (Object)adaptor.create(n)
                    	    ;
                    	    adaptor.addChild(root_0, n_tree);


                    	    RIGHTSBR155=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3374); 
                    	    RIGHTSBR155_tree = 
                    	    (Object)adaptor.create(RIGHTSBR155)
                    	    ;
                    	    adaptor.addChild(root_0, RIGHTSBR155_tree);



                    	    if(!defer) {
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
                    	    break loop61;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1773:4: n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3386); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);



                    if(!defer) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((n!=null?n.getText():null));
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1780:4: MINUS n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS156=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3393); 
                    MINUS156_tree = 
                    (Object)adaptor.create(MINUS156)
                    ;
                    adaptor.addChild(root_0, MINUS156_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3397); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);



                    if(!defer) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((n!=null?n.getText():null)) * -1;
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1787:4: r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3406); 
                    r_tree = 
                    (Object)adaptor.create(r)
                    ;
                    adaptor.addChild(root_0, r_tree);



                    if(!defer) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((r!=null?r.getText():null));
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1794:4: MINUS r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS157=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3413); 
                    MINUS157_tree = 
                    (Object)adaptor.create(MINUS157)
                    ;
                    adaptor.addChild(root_0, MINUS157_tree);


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3417); 
                    r_tree = 
                    (Object)adaptor.create(r)
                    ;
                    adaptor.addChild(root_0, r_tree);



                    if(!defer) {
                        Variable v = new Variable(null, EugeneConstants.NUM);
                        v.num = Double.parseDouble((r!=null?r.getText():null)) * -1.0;
                        retval.eop = new ExpressionOperand(v);
                    }	
                    	

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1801:4: s= STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3426); 
                    s_tree = 
                    (Object)adaptor.create(s)
                    ;
                    adaptor.addChild(root_0, s_tree);



                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1811:1: regexp[boolean defer] :;
    public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
        EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1812:2: ()
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1813:2: 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1815:1: exp_op[boolean defer] : relationalOperators ;
    public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
        EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.relationalOperators_return relationalOperators158 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1816:2: ( relationalOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1816:4: relationalOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_relationalOperators_in_exp_op3453);
            relationalOperators158=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, relationalOperators158.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1824:1: grammarDeclaration[boolean defer] : GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP ;
    public final EugeneParser.grammarDeclaration_return grammarDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.grammarDeclaration_return retval = new EugeneParser.grammarDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token GRAMMAR159=null;
        Token LEFTP160=null;
        Token RIGHTP162=null;
        EugeneParser.list_of_production_rules_return list_of_production_rules161 =null;


        Object n_tree=null;
        Object GRAMMAR159_tree=null;
        Object LEFTP160_tree=null;
        Object RIGHTP162_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1825:2: ( GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1826:3: GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            GRAMMAR159=(Token)match(input,GRAMMAR,FOLLOW_GRAMMAR_in_grammarDeclaration3472); 
            GRAMMAR159_tree = 
            (Object)adaptor.create(GRAMMAR159)
            ;
            adaptor.addChild(root_0, GRAMMAR159_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_grammarDeclaration3476); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            LEFTP160=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_grammarDeclaration3478); 
            LEFTP160_tree = 
            (Object)adaptor.create(LEFTP160)
            ;
            adaptor.addChild(root_0, LEFTP160_tree);


            pushFollow(FOLLOW_list_of_production_rules_in_grammarDeclaration3480);
            list_of_production_rules161=list_of_production_rules(defer);

            state._fsp--;

            adaptor.addChild(root_0, list_of_production_rules161.getTree());

            RIGHTP162=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_grammarDeclaration3483); 
            RIGHTP162_tree = 
            (Object)adaptor.create(RIGHTP162)
            ;
            adaptor.addChild(root_0, RIGHTP162_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1829:1: list_of_production_rules[boolean defer] : production_rule[defer] SEMIC ( list_of_production_rules[defer] )? ;
    public final EugeneParser.list_of_production_rules_return list_of_production_rules(boolean defer) throws RecognitionException {
        EugeneParser.list_of_production_rules_return retval = new EugeneParser.list_of_production_rules_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMIC164=null;
        EugeneParser.production_rule_return production_rule163 =null;

        EugeneParser.list_of_production_rules_return list_of_production_rules165 =null;


        Object SEMIC164_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:2: ( production_rule[defer] SEMIC ( list_of_production_rules[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:4: production_rule[defer] SEMIC ( list_of_production_rules[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_production_rule_in_list_of_production_rules3495);
            production_rule163=production_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, production_rule163.getTree());

            SEMIC164=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_list_of_production_rules3498); 
            SEMIC164_tree = 
            (Object)adaptor.create(SEMIC164)
            ;
            adaptor.addChild(root_0, SEMIC164_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:33: ( list_of_production_rules[defer] )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==ID) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:34: list_of_production_rules[defer]
                    {
                    pushFollow(FOLLOW_list_of_production_rules_in_list_of_production_rules3501);
                    list_of_production_rules165=list_of_production_rules(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list_of_production_rules165.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1833:1: production_rule[boolean defer] : lhs= ID ARROW right_hand_side[defer] ;
    public final EugeneParser.production_rule_return production_rule(boolean defer) throws RecognitionException {
        EugeneParser.production_rule_return retval = new EugeneParser.production_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs=null;
        Token ARROW166=null;
        EugeneParser.right_hand_side_return right_hand_side167 =null;


        Object lhs_tree=null;
        Object ARROW166_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1834:2: (lhs= ID ARROW right_hand_side[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1834:4: lhs= ID ARROW right_hand_side[defer]
            {
            root_0 = (Object)adaptor.nil();


            lhs=(Token)match(input,ID,FOLLOW_ID_in_production_rule3521); 
            lhs_tree = 
            (Object)adaptor.create(lhs)
            ;
            adaptor.addChild(root_0, lhs_tree);



            if(!defer) {
                // ID denotes a non-terminal of the grammar
            }	
            	

            ARROW166=(Token)match(input,ARROW,FOLLOW_ARROW_in_production_rule3525); 
            ARROW166_tree = 
            (Object)adaptor.create(ARROW166)
            ;
            adaptor.addChild(root_0, ARROW166_tree);


            pushFollow(FOLLOW_right_hand_side_in_production_rule3527);
            right_hand_side167=right_hand_side(defer);

            state._fsp--;

            adaptor.addChild(root_0, right_hand_side167.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1841:1: right_hand_side[boolean defer] : (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] );
    public final EugeneParser.right_hand_side_return right_hand_side(boolean defer) throws RecognitionException {
        EugeneParser.right_hand_side_return retval = new EugeneParser.right_hand_side_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token COMMA168=null;
        EugeneParser.right_hand_side_return right_hand_side169 =null;

        EugeneParser.interaction_return interaction170 =null;


        Object i_tree=null;
        Object COMMA168_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1842:2: (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==ID) ) {
                int LA65_1 = input.LA(2);

                if ( (LA65_1==COMMA||LA65_1==SEMIC) ) {
                    alt65=1;
                }
                else if ( (LA65_1==LC_INDUCES||LA65_1==LC_REPRESSES||LA65_1==UC_INDUCES||LA65_1==UC_REPRESSES) ) {
                    alt65=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 65, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;

            }
            switch (alt65) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1842:4: i= ID ( COMMA right_hand_side[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_right_hand_side3543); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer) {
                        // ID must be either a terminal (i.e. a part)
                        // or a non-terminal defined within the grammar
                    }	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1847:4: ( COMMA right_hand_side[defer] )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==COMMA) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1847:5: COMMA right_hand_side[defer]
                            {
                            COMMA168=(Token)match(input,COMMA,FOLLOW_COMMA_in_right_hand_side3548); 
                            COMMA168_tree = 
                            (Object)adaptor.create(COMMA168)
                            ;
                            adaptor.addChild(root_0, COMMA168_tree);


                            pushFollow(FOLLOW_right_hand_side_in_right_hand_side3550);
                            right_hand_side169=right_hand_side(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, right_hand_side169.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1848:4: interaction[defer, \"some_string\"]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_right_hand_side3558);
                    interaction170=interaction(defer, "some_string");

                    state._fsp--;

                    adaptor.addChild(root_0, interaction170.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1855:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
    public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token INTERACTION171=null;
        Token LEFTP172=null;
        Token RIGHTP173=null;
        EugeneParser.interaction_return i1 =null;

        EugeneParser.interaction_return i2 =null;


        Object name_tree=null;
        Object INTERACTION171_tree=null;
        Object LEFTP172_tree=null;
        Object RIGHTP173_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1857:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==ID) ) {
                alt66=1;
            }
            else if ( (LA66_0==INTERACTION) ) {
                alt66=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }
            switch (alt66) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1857:4: i1= interaction[defer, null]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3583);
                    i1=interaction(defer, null);

                    state._fsp--;

                    adaptor.addChild(root_0, i1.getTree());


                    if(!defer) {
                        retval.ia = (i1!=null?i1.ia:null);
                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1862:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    INTERACTION171=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3591); 
                    INTERACTION171_tree = 
                    (Object)adaptor.create(INTERACTION171)
                    ;
                    adaptor.addChild(root_0, INTERACTION171_tree);


                    name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3595); 
                    name_tree = 
                    (Object)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    LEFTP172=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3597); 
                    LEFTP172_tree = 
                    (Object)adaptor.create(LEFTP172)
                    ;
                    adaptor.addChild(root_0, LEFTP172_tree);


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3601);
                    i2=interaction(defer, (name!=null?name.getText():null));

                    state._fsp--;

                    adaptor.addChild(root_0, i2.getTree());

                    RIGHTP173=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3604); 
                    RIGHTP173_tree = 
                    (Object)adaptor.create(RIGHTP173)
                    ;
                    adaptor.addChild(root_0, RIGHTP173_tree);



                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1869:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
    public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
        EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs1=null;
        Token rhs1=null;
        Token lhs2=null;
        Token LEFTP174=null;
        Token RIGHTP175=null;
        EugeneParser.interactionType_return t1 =null;

        EugeneParser.interactionType_return t2 =null;

        EugeneParser.interaction_return rhs2 =null;


        Object lhs1_tree=null;
        Object rhs1_tree=null;
        Object lhs2_tree=null;
        Object LEFTP174_tree=null;
        Object RIGHTP175_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1871:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==ID) ) {
                int LA67_1 = input.LA(2);

                if ( (LA67_1==LC_REPRESSES||LA67_1==UC_REPRESSES) ) {
                    int LA67_2 = input.LA(3);

                    if ( (LA67_2==ID) ) {
                        alt67=1;
                    }
                    else if ( (LA67_2==LEFTP) ) {
                        alt67=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 67, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA67_1==LC_INDUCES||LA67_1==UC_INDUCES) ) {
                    int LA67_3 = input.LA(3);

                    if ( (LA67_3==ID) ) {
                        alt67=1;
                    }
                    else if ( (LA67_3==LEFTP) ) {
                        alt67=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 67, 3, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;

            }
            switch (alt67) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1871:4: lhs1= ID t1= interactionType[defer] rhs1= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3627); 
                    lhs1_tree = 
                    (Object)adaptor.create(lhs1)
                    ;
                    adaptor.addChild(root_0, lhs1_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3631);
                    t1=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t1.getTree());

                    rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3636); 
                    rhs1_tree = 
                    (Object)adaptor.create(rhs1)
                    ;
                    adaptor.addChild(root_0, rhs1_tree);



                    if(!defer) {
                        try {
                            retval.ia = this.interp.createInteraction(name, (lhs1!=null?lhs1.getText():null), (t1!=null?t1.type:null), (rhs1!=null?rhs1.getText():null));
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1880:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3645); 
                    lhs2_tree = 
                    (Object)adaptor.create(lhs2)
                    ;
                    adaptor.addChild(root_0, lhs2_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3649);
                    t2=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t2.getTree());

                    LEFTP174=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3652); 
                    LEFTP174_tree = 
                    (Object)adaptor.create(LEFTP174)
                    ;
                    adaptor.addChild(root_0, LEFTP174_tree);


                    pushFollow(FOLLOW_interaction_in_interaction3656);
                    rhs2=interaction(defer, name);

                    state._fsp--;

                    adaptor.addChild(root_0, rhs2.getTree());

                    RIGHTP175=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3659); 
                    RIGHTP175_tree = 
                    (Object)adaptor.create(RIGHTP175)
                    ;
                    adaptor.addChild(root_0, RIGHTP175_tree);



                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1891:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
    public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
        EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set176=null;
        Token set177=null;

        Object set176_tree=null;
        Object set177_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1893:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==LC_REPRESSES||LA68_0==UC_REPRESSES) ) {
                alt68=1;
            }
            else if ( (LA68_0==LC_INDUCES||LA68_0==UC_INDUCES) ) {
                alt68=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;

            }
            switch (alt68) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1893:4: ( UC_REPRESSES | LC_REPRESSES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set176=(Token)input.LT(1);

                    if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set176)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }



                    if(!defer) {
                        retval.type = Interaction.InteractionType.REPRESSES;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1898:4: ( UC_INDUCES | LC_INDUCES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set177=(Token)input.LT(1);

                    if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set177)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }



                    if(!defer) {
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


    public static class functionCall_return extends ParserRuleReturnScope {
        public NamedElement e;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionCall"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1914:1: functionCall[boolean defer] returns [NamedElement e] : (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.functionCall_return functionCall(boolean defer) throws RecognitionException {
        EugeneParser.functionCall_return retval = new EugeneParser.functionCall_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token pr=null;
        Token pe=null;
        Token idToken=null;
        Token LEFTP178=null;
        Token RIGHTP179=null;

        Object pr_tree=null;
        Object pe_tree=null;
        Object idToken_tree=null;
        Object LEFTP178_tree=null;
        Object RIGHTP179_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1916:2: ( (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1916:4: (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1916:4: (pr= PRODUCT |pe= PERMUTE )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==PRODUCT) ) {
                alt69=1;
            }
            else if ( (LA69_0==PERMUTE) ) {
                alt69=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;

            }
            switch (alt69) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1916:5: pr= PRODUCT
                    {
                    pr=(Token)match(input,PRODUCT,FOLLOW_PRODUCT_in_functionCall3726); 
                    pr_tree = 
                    (Object)adaptor.create(pr)
                    ;
                    adaptor.addChild(root_0, pr_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1916:16: pe= PERMUTE
                    {
                    pe=(Token)match(input,PERMUTE,FOLLOW_PERMUTE_in_functionCall3730); 
                    pe_tree = 
                    (Object)adaptor.create(pe)
                    ;
                    adaptor.addChild(root_0, pe_tree);


                    }
                    break;

            }


            LEFTP178=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_functionCall3733); 
            LEFTP178_tree = 
            (Object)adaptor.create(LEFTP178)
            ;
            adaptor.addChild(root_0, LEFTP178_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_functionCall3737); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP179=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_functionCall3739); 
            RIGHTP179_tree = 
            (Object)adaptor.create(RIGHTP179)
            ;
            adaptor.addChild(root_0, RIGHTP179_tree);



            if(!defer) {
                try {
                
                    if(!this.interp.contains((idToken!=null?idToken.getText():null))) {
                        printError((idToken!=null?idToken.getText():null)+" does not exists.");
                    } 
                    NamedElement ne = this.interp.get((idToken!=null?idToken.getText():null));
                    if(!(ne instanceof Device)) {
                        printError((idToken!=null?idToken.getText():null)+" is not a Device.");
                    }
                    
                    if(pr != null) {
                        retval.e = this.interp.product((Device)ne);
                    } else {
                        retval.e = this.interp.permute((Device)ne);
                    }
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
    // $ANTLR end "functionCall"


    public static class printStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "printStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1943:1: printStatement[boolean defer] : ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP );
    public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
        EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set180=null;
        Token LEFTP181=null;
        Token RIGHTP182=null;
        Token set183=null;
        Token LEFTP184=null;
        Token RIGHTP185=null;
        EugeneParser.toPrint_return tp =null;


        Object set180_tree=null;
        Object LEFTP181_tree=null;
        Object RIGHTP182_tree=null;
        Object set183_tree=null;
        Object LEFTP184_tree=null;
        Object RIGHTP185_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1944:2: ( ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP | ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1944:4: ( PRINTLN_LC | PRINTLN_UC ) LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set180=(Token)input.LT(1);

                    if ( (input.LA(1) >= PRINTLN_LC && input.LA(1) <= PRINTLN_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set180)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP181=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3763); 
                    LEFTP181_tree = 
                    (Object)adaptor.create(LEFTP181)
                    ;
                    adaptor.addChild(root_0, LEFTP181_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3767);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP182=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3770); 
                    RIGHTP182_tree = 
                    (Object)adaptor.create(RIGHTP182)
                    ;
                    adaptor.addChild(root_0, RIGHTP182_tree);



                    if(!defer) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1957:4: ( PRINT_LC | PRINT_UC ) LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set183=(Token)input.LT(1);

                    if ( (input.LA(1) >= PRINT_LC && input.LA(1) <= PRINT_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set183)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP184=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3783); 
                    LEFTP184_tree = 
                    (Object)adaptor.create(LEFTP184)
                    ;
                    adaptor.addChild(root_0, LEFTP184_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3787);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP185=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3790); 
                    RIGHTP185_tree = 
                    (Object)adaptor.create(RIGHTP185)
                    ;
                    adaptor.addChild(root_0, RIGHTP185_tree);



                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1971:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
    public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return exp =null;

        EugeneParser.toPrint_prime_return tpp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1973:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1973:4: exp= expr[defer] tpp= toPrint_prime[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_toPrint3811);
            exp=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, exp.getTree());

            pushFollow(FOLLOW_toPrint_prime_in_toPrint3816);
            tpp=toPrint_prime(defer);

            state._fsp--;

            adaptor.addChild(root_0, tpp.getTree());


            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1986:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
    public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA186=null;
        EugeneParser.toPrint_return tp =null;


        Object COMMA186_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1988:2: (| COMMA tp= toPrint[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1988:4: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.sb = new StringBuilder();
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1993:4: COMMA tp= toPrint[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    COMMA186=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime3842); 
                    COMMA186_tree = 
                    (Object)adaptor.create(COMMA186)
                    ;
                    adaptor.addChild(root_0, COMMA186_tree);


                    pushFollow(FOLLOW_toPrint_in_toPrint_prime3846);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2005:1: imperativeStatements[boolean defer] : ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] );
    public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException {
        EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ifStatement_return ifStatement187 =null;

        EugeneParser.forall_iterator_return forall_iterator188 =null;

        EugeneParser.for_loop_return for_loop189 =null;

        EugeneParser.while_loop_return while_loop190 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2006:2: ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2006:4: ifStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifStatement_in_imperativeStatements3866);
                    ifStatement187=ifStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ifStatement187.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2007:4: forall_iterator[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_forall_iterator_in_imperativeStatements3872);
                    forall_iterator188=forall_iterator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, forall_iterator188.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2008:4: for_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_for_loop_in_imperativeStatements3878);
                    for_loop189=for_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, for_loop189.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2009:4: while_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_while_loop_in_imperativeStatements3884);
                    while_loop190=while_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, while_loop190.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2012:1: ifStatement[boolean defer] : ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? ;
    public final EugeneParser.ifStatement_return ifStatement(boolean defer) throws RecognitionException {
        EugeneParser.ifStatement_return retval = new EugeneParser.ifStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set191=null;
        Token LEFTP192=null;
        Token RIGHTP193=null;
        Token LEFTCUR194=null;
        Token RIGHTCUR195=null;
        Token set196=null;
        Token LEFTP197=null;
        Token RIGHTP198=null;
        Token LEFTCUR199=null;
        Token RIGHTCUR200=null;
        Token set201=null;
        Token LEFTCUR202=null;
        Token RIGHTCUR203=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set191_tree=null;
        Object LEFTP192_tree=null;
        Object RIGHTP193_tree=null;
        Object LEFTCUR194_tree=null;
        Object RIGHTCUR195_tree=null;
        Object set196_tree=null;
        Object LEFTP197_tree=null;
        Object RIGHTP198_tree=null;
        Object LEFTCUR199_tree=null;
        Object RIGHTCUR200_tree=null;
        Object set201_tree=null;
        Object LEFTCUR202_tree=null;
        Object RIGHTCUR203_tree=null;


        boolean bExecuted = false;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2016:2: ( ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2020:3: ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            {
            root_0 = (Object)adaptor.nil();


            set191=(Token)input.LT(1);

            if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
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


            LEFTP192=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3916); 
            LEFTP192_tree = 
            (Object)adaptor.create(LEFTP192)
            ;
            adaptor.addChild(root_0, LEFTP192_tree);


            pushFollow(FOLLOW_imp_condition_in_ifStatement3920);
            co=imp_condition(defer);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP193=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3923); 
            RIGHTP193_tree = 
            (Object)adaptor.create(RIGHTP193)
            ;
            adaptor.addChild(root_0, RIGHTP193_tree);


            LEFTCUR194=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3925); 
            LEFTCUR194_tree = 
            (Object)adaptor.create(LEFTCUR194)
            ;
            adaptor.addChild(root_0, LEFTCUR194_tree);


            pushFollow(FOLLOW_listOfStatements_in_ifStatement3933);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR195=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3936); 
            RIGHTCUR195_tree = 
            (Object)adaptor.create(RIGHTCUR195)
            ;
            adaptor.addChild(root_0, RIGHTCUR195_tree);



            if(!defer) {
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
            		

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2043:3: ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==LC_ELSEIF||LA73_0==UC_ELSEIF) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2043:5: ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            	    {
            	    set196=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
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


            	    LEFTP197=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3957); 
            	    LEFTP197_tree = 
            	    (Object)adaptor.create(LEFTP197)
            	    ;
            	    adaptor.addChild(root_0, LEFTP197_tree);


            	    pushFollow(FOLLOW_imp_condition_in_ifStatement3961);
            	    co=imp_condition(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, co.getTree());

            	    RIGHTP198=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3964); 
            	    RIGHTP198_tree = 
            	    (Object)adaptor.create(RIGHTP198)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP198_tree);


            	    LEFTCUR199=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3966); 
            	    LEFTCUR199_tree = 
            	    (Object)adaptor.create(LEFTCUR199)
            	    ;
            	    adaptor.addChild(root_0, LEFTCUR199_tree);


            	    pushFollow(FOLLOW_listOfStatements_in_ifStatement3974);
            	    stmts=listOfStatements(true);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmts.getTree());

            	    RIGHTCUR200=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3977); 
            	    RIGHTCUR200_tree = 
            	    (Object)adaptor.create(RIGHTCUR200)
            	    ;
            	    adaptor.addChild(root_0, RIGHTCUR200_tree);



            	    if(!defer && !bExecuted) {
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
            	    break loop73;
                }
            } while (true);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2066:3: ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==LC_ELSE||LA74_0==UC_ELSE) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2066:4: ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR
                    {
                    set201=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set201)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTCUR202=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3999); 
                    LEFTCUR202_tree = 
                    (Object)adaptor.create(LEFTCUR202)
                    ;
                    adaptor.addChild(root_0, LEFTCUR202_tree);


                    pushFollow(FOLLOW_listOfStatements_in_ifStatement4007);
                    stmts=listOfStatements(true);

                    state._fsp--;

                    adaptor.addChild(root_0, stmts.getTree());

                    RIGHTCUR203=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement4010); 
                    RIGHTCUR203_tree = 
                    (Object)adaptor.create(RIGHTCUR203)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR203_tree);



                    if(!defer && !bExecuted) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2082:1: imp_condition[boolean defer] returns [boolean bTrue] : lhs= expr[defer] ro= relationalOperators rhs= expr[defer] ;
    public final EugeneParser.imp_condition_return imp_condition(boolean defer) throws RecognitionException {
        EugeneParser.imp_condition_return retval = new EugeneParser.imp_condition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return lhs =null;

        EugeneParser.relationalOperators_return ro =null;

        EugeneParser.expr_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2084:2: (lhs= expr[defer] ro= relationalOperators rhs= expr[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2084:4: lhs= expr[defer] ro= relationalOperators rhs= expr[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_imp_condition4034);
            lhs=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_relationalOperators_in_imp_condition4039);
            ro=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, ro.getTree());

            pushFollow(FOLLOW_expr_in_imp_condition4043);
            rhs=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR ;
    public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException {
        EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token it=null;
        Token i=null;
        Token set204=null;
        Token COLON205=null;
        Token LEFTCUR206=null;
        Token RIGHTCUR208=null;
        EugeneParser.listOfStatements_return listOfStatements207 =null;


        Object it_tree=null;
        Object i_tree=null;
        Object set204_tree=null;
        Object COLON205_tree=null;
        Object LEFTCUR206_tree=null;
        Object RIGHTCUR208_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2136:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2136:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set204=(Token)input.LT(1);

            if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2136:26: (it= ID COLON )?
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2136:27: it= ID COLON
                    {
                    it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator4068); 
                    it_tree = 
                    (Object)adaptor.create(it)
                    ;
                    adaptor.addChild(root_0, it_tree);


                    COLON205=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator4070); 
                    COLON205_tree = 
                    (Object)adaptor.create(COLON205)
                    ;
                    adaptor.addChild(root_0, COLON205_tree);


                    }
                    break;

            }


            i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator4076); 
            i_tree = 
            (Object)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            LEFTCUR206=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator4078); 
            LEFTCUR206_tree = 
            (Object)adaptor.create(LEFTCUR206)
            ;
            adaptor.addChild(root_0, LEFTCUR206_tree);


            pushFollow(FOLLOW_listOfStatements_in_forall_iterator4083);
            listOfStatements207=listOfStatements(defer);

            state._fsp--;

            adaptor.addChild(root_0, listOfStatements207.getTree());


            if(!defer) {

            }			
            	

            RIGHTCUR208=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator4090); 
            RIGHTCUR208_tree = 
            (Object)adaptor.create(RIGHTCUR208)
            ;
            adaptor.addChild(root_0, RIGHTCUR208_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2145:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException {
        EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set209=null;
        Token LEFTP210=null;
        Token SEMIC211=null;
        Token SEMIC212=null;
        Token RIGHTP213=null;
        Token LEFTCUR214=null;
        Token RIGHTCUR215=null;
        EugeneParser.variableDeclaration_return ds =null;

        EugeneParser.imp_condition_return co =null;

        EugeneParser.assignment_return as =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set209_tree=null;
        Object LEFTP210_tree=null;
        Object SEMIC211_tree=null;
        Object SEMIC212_tree=null;
        Object RIGHTP213_tree=null;
        Object LEFTCUR214_tree=null;
        Object RIGHTCUR215_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2146:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2146:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set209=(Token)input.LT(1);

            if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set209)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP210=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop4108); 
            LEFTP210_tree = 
            (Object)adaptor.create(LEFTP210)
            ;
            adaptor.addChild(root_0, LEFTP210_tree);


            pushFollow(FOLLOW_variableDeclaration_in_for_loop4112);
            ds=variableDeclaration(true);

            state._fsp--;

            adaptor.addChild(root_0, ds.getTree());

            SEMIC211=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4115); 
            SEMIC211_tree = 
            (Object)adaptor.create(SEMIC211)
            ;
            adaptor.addChild(root_0, SEMIC211_tree);


            pushFollow(FOLLOW_imp_condition_in_for_loop4119);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            SEMIC212=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4122); 
            SEMIC212_tree = 
            (Object)adaptor.create(SEMIC212)
            ;
            adaptor.addChild(root_0, SEMIC212_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2146:90: (as= assignment[true] )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==ID) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2146:91: as= assignment[true]
                    {
                    pushFollow(FOLLOW_assignment_in_for_loop4127);
                    as=assignment(true);

                    state._fsp--;

                    adaptor.addChild(root_0, as.getTree());

                    }
                    break;

            }


            RIGHTP213=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop4132); 
            RIGHTP213_tree = 
            (Object)adaptor.create(RIGHTP213)
            ;
            adaptor.addChild(root_0, RIGHTP213_tree);


            LEFTCUR214=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop4134); 
            LEFTCUR214_tree = 
            (Object)adaptor.create(LEFTCUR214)
            ;
            adaptor.addChild(root_0, LEFTCUR214_tree);


            pushFollow(FOLLOW_listOfStatements_in_for_loop4142);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());


            if(!defer) {
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
            		

            RIGHTCUR215=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop4149); 
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
    // $ANTLR end "for_loop"


    public static class while_loop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "while_loop"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2173:1: while_loop[boolean defer] : ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.while_loop_return while_loop(boolean defer) throws RecognitionException {
        EugeneParser.while_loop_return retval = new EugeneParser.while_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set216=null;
        Token LEFTP217=null;
        Token RIGHTP218=null;
        Token LEFTCUR219=null;
        Token RIGHTCUR220=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set216_tree=null;
        Object LEFTP217_tree=null;
        Object RIGHTP218_tree=null;
        Object LEFTCUR219_tree=null;
        Object RIGHTCUR220_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2174:2: ( ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2174:4: ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set216=(Token)input.LT(1);

            if ( input.LA(1)==LC_WHILE||input.LA(1)==UC_WHILE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set216)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP217=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_while_loop4169); 
            LEFTP217_tree = 
            (Object)adaptor.create(LEFTP217)
            ;
            adaptor.addChild(root_0, LEFTP217_tree);


            pushFollow(FOLLOW_imp_condition_in_while_loop4173);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP218=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_while_loop4176); 
            RIGHTP218_tree = 
            (Object)adaptor.create(RIGHTP218)
            ;
            adaptor.addChild(root_0, RIGHTP218_tree);


            LEFTCUR219=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_while_loop4178); 
            LEFTCUR219_tree = 
            (Object)adaptor.create(LEFTCUR219)
            ;
            adaptor.addChild(root_0, LEFTCUR219_tree);


            pushFollow(FOLLOW_listOfStatements_in_while_loop4186);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());


            if(!defer) {
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
            	

            RIGHTCUR220=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_while_loop4193); 
            RIGHTCUR220_tree = 
            (Object)adaptor.create(RIGHTCUR220)
            ;
            adaptor.addChild(root_0, RIGHTCUR220_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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


    public static class listOfStatements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfStatements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2193:1: listOfStatements[boolean defer] : (stmtToken= statement[defer] )+ ;
    public final EugeneParser.listOfStatements_return listOfStatements(boolean defer) throws RecognitionException {
        EugeneParser.listOfStatements_return retval = new EugeneParser.listOfStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.statement_return stmtToken =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2194:2: ( (stmtToken= statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2194:4: (stmtToken= statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2194:4: (stmtToken= statement[defer] )+
            int cnt77=0;
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==ARRAY||(LA77_0 >= ASSERT && LA77_0 <= COLLECTION)||LA77_0==DEVICE||(LA77_0 >= EXIT_LC && LA77_0 <= EXIT_UC)||LA77_0==GRAMMAR||(LA77_0 >= HASHMARK && LA77_0 <= ID)||(LA77_0 >= IMPORT_LC && LA77_0 <= INTERACTION)||(LA77_0 >= LC_FOR && LA77_0 <= LC_IF)||LA77_0==LC_WHILE||(LA77_0 >= NOTE && LA77_0 <= NUM)||(LA77_0 >= PART && LA77_0 <= PIGEON_UC)||(LA77_0 >= PRINTLN_LC && LA77_0 <= RANDOM_UC)||(LA77_0 >= RULE && LA77_0 <= STORE_UC)||(LA77_0 >= TXT && LA77_0 <= TYPE)||(LA77_0 >= UC_FOR && LA77_0 <= UC_IF)||LA77_0==UC_WHILE) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2194:5: stmtToken= statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_listOfStatements4210);
            	    stmtToken=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmtToken.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt77 >= 1 ) break loop77;
                        EarlyExitException eee =
                            new EarlyExitException(77, input);
                        throw eee;
                }
                cnt77++;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2202:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* ;
    public final EugeneParser.expr_return expr(boolean defer) throws RecognitionException {
        EugeneParser.expr_return retval = new EugeneParser.expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token op=null;
        EugeneParser.multExpr_return e =null;


        Object op_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2204:2: (e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2204:4: e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_expr4238);
            e=multExpr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer) {
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2222:5: (op= ( PLUS | MINUS ) e= multExpr[defer] )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==MINUS||LA78_0==PLUS) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2222:6: op= ( PLUS | MINUS ) e= multExpr[defer]
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


            	    pushFollow(FOLLOW_multExpr_in_expr4255);
            	    e=multExpr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    if(!defer) {
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
            	    break loop78;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2261:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2263:2: (e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2263:4: e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_multExpr4285);
            e=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer) {
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2283:5: ( (mul= MULT |div= DIV ) e= atom[defer] )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==DIV||LA80_0==MULT) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2283:7: (mul= MULT |div= DIV ) e= atom[defer]
            	    {
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2283:7: (mul= MULT |div= DIV )
            	    int alt79=2;
            	    int LA79_0 = input.LA(1);

            	    if ( (LA79_0==MULT) ) {
            	        alt79=1;
            	    }
            	    else if ( (LA79_0==DIV) ) {
            	        alt79=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 79, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt79) {
            	        case 1 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2283:8: mul= MULT
            	            {
            	            mul=(Token)match(input,MULT,FOLLOW_MULT_in_multExpr4296); 
            	            mul_tree = 
            	            (Object)adaptor.create(mul)
            	            ;
            	            adaptor.addChild(root_0, mul_tree);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2283:17: div= DIV
            	            {
            	            div=(Token)match(input,DIV,FOLLOW_DIV_in_multExpr4300); 
            	            div_tree = 
            	            (Object)adaptor.create(div)
            	            ;
            	            adaptor.addChild(root_0, div_tree);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_atom_in_multExpr4305);
            	    e=atom(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    if(!defer) {
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
            	    break loop80;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2300:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] );
    public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
        EugeneParser.atom_return retval = new EugeneParser.atom_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token t=null;
        Token f=null;
        Token MINUS221=null;
        Token STRING222=null;
        Token char_literal223=null;
        Token char_literal225=null;
        Token LEFTSBR226=null;
        Token RIGHTSBR228=null;
        EugeneParser.dynamic_naming_return dn =null;

        EugeneParser.object_access_return oc =null;

        EugeneParser.built_in_function_return bif =null;

        EugeneParser.expr_return expr224 =null;

        EugeneParser.list_return list227 =null;


        Object n_tree=null;
        Object t_tree=null;
        Object f_tree=null;
        Object MINUS221_tree=null;
        Object STRING222_tree=null;
        Object char_literal223_tree=null;
        Object char_literal225_tree=null;
        Object LEFTSBR226_tree=null;
        Object RIGHTSBR228_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2302:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] )
            int alt84=8;
            switch ( input.LA(1) ) {
            case NUMBER:
            case REAL:
                {
                alt84=1;
                }
                break;
            case MINUS:
                {
                alt84=2;
                }
                break;
            case FALSE_LC:
            case FALSE_UC:
            case TRUE_LC:
            case TRUE_UC:
                {
                alt84=3;
                }
                break;
            case DOLLAR:
            case ID:
                {
                alt84=4;
                }
                break;
            case STRING:
                {
                alt84=5;
                }
                break;
            case LEFTP:
                {
                alt84=6;
                }
                break;
            case LEFTSBR:
                {
                alt84=7;
                }
                break;
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
                alt84=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }

            switch (alt84) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2302:4: (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2302:4: (n= NUMBER |n= REAL )
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==NUMBER) ) {
                        alt81=1;
                    }
                    else if ( (LA81_0==REAL) ) {
                        alt81=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 81, 0, input);

                        throw nvae;

                    }
                    switch (alt81) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2302:5: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4332); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2302:16: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4338); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;

                    }



                    		if(!defer) {
                    			retval.p.num = Double.parseDouble((n!=null?n.getText():null));
                    			retval.p.type = EugeneConstants.NUM;
                    			
                    			retval.element = null;
                    		}
                    		

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2311:4: MINUS (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS221=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom4348); 
                    MINUS221_tree = 
                    (Object)adaptor.create(MINUS221)
                    ;
                    adaptor.addChild(root_0, MINUS221_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2311:10: (n= NUMBER |n= REAL )
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==NUMBER) ) {
                        alt82=1;
                    }
                    else if ( (LA82_0==REAL) ) {
                        alt82=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 82, 0, input);

                        throw nvae;

                    }
                    switch (alt82) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2311:11: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4353); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2311:22: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4359); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;

                    }



                    		if(!defer) {
                    			retval.p.num = Double.parseDouble((n!=null?n.getText():null)) * -1.0;
                    			retval.p.type = EugeneConstants.NUM;

                    			retval.element = null;
                    		}
                    		

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2320:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2320:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( ((LA83_0 >= TRUE_LC && LA83_0 <= TRUE_UC)) ) {
                        alt83=1;
                    }
                    else if ( ((LA83_0 >= FALSE_LC && LA83_0 <= FALSE_UC)) ) {
                        alt83=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 83, 0, input);

                        throw nvae;

                    }
                    switch (alt83) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2320:5: t= ( TRUE_LC | TRUE_UC )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2320:27: f= ( FALSE_LC | FALSE_UC )
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



                    		if(!defer) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2333:4: dn= dynamic_naming[defer] oc= object_access[defer, $element]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dynamic_naming_in_atom4398);
                    dn=dynamic_naming(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, dn.getTree());


                    if(!defer) {
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
                    	

                    pushFollow(FOLLOW_object_access_in_atom4405);
                    oc=object_access(defer, retval.element);

                    state._fsp--;

                    adaptor.addChild(root_0, oc.getTree());


                    if(!defer) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2365:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING222=(Token)match(input,STRING,FOLLOW_STRING_in_atom4414); 
                    STRING222_tree = 
                    (Object)adaptor.create(STRING222)
                    ;
                    adaptor.addChild(root_0, STRING222_tree);



                    		if(!defer) {
                    			retval.p.type = EugeneConstants.TXT;
                    			retval.p.txt = (STRING222!=null?STRING222.getText():null).substring(1, (STRING222!=null?STRING222.getText():null).length()-1);

                    			retval.element = null;
                    		}
                    	

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2374:4: '(' expr[defer] ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal223=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4422); 
                    char_literal223_tree = 
                    (Object)adaptor.create(char_literal223)
                    ;
                    adaptor.addChild(root_0, char_literal223_tree);


                    pushFollow(FOLLOW_expr_in_atom4424);
                    expr224=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expr224.getTree());

                    char_literal225=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4427); 
                    char_literal225_tree = 
                    (Object)adaptor.create(char_literal225)
                    ;
                    adaptor.addChild(root_0, char_literal225_tree);



                    		if(!defer) {
                    			retval.p = (expr224!=null?expr224.p:null);
                    			retval.primVariable = (expr224!=null?expr224.primVariable:null);
                    			retval.element = (expr224!=null?expr224.element:null);
                    		}
                    	

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2382:5: LEFTSBR list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR226=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4436); 
                    LEFTSBR226_tree = 
                    (Object)adaptor.create(LEFTSBR226)
                    ;
                    adaptor.addChild(root_0, LEFTSBR226_tree);


                    pushFollow(FOLLOW_list_in_atom4438);
                    list227=list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list227.getTree());

                    RIGHTSBR228=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4441); 
                    RIGHTSBR228_tree = 
                    (Object)adaptor.create(RIGHTSBR228)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR228_tree);



                    		if(!defer) {
                    			retval.p = (list227!=null?list227.listPrim:null);
                    			retval.primVariable = (list227!=null?list227.listPrim:null);
                    			typeList="";
                    		}
                    	

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2390:5: bif= built_in_function[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_built_in_function_in_atom4452);
                    bif=built_in_function(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, bif.getTree());


                    if(!defer) {
                        retval.p = (bif!=null?bif.p:null);
                        retval.element = null;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2398:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
    public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
        EugeneParser.list_return retval = new EugeneParser.list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA229=null;
        EugeneParser.expr_return str1 =null;

        EugeneParser.expr_return str2 =null;


        Object COMMA229_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2400:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_list4475);
            str1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, str1.getTree());


            if(!defer){
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2419:5: ( COMMA str2= expr[defer] )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==COMMA) ) {
                    alt85=1;
                }


                switch (alt85) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2419:6: COMMA str2= expr[defer]
            	    {
            	    COMMA229=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4482); 
            	    COMMA229_tree = 
            	    (Object)adaptor.create(COMMA229)
            	    ;
            	    adaptor.addChild(root_0, COMMA229_tree);


            	    pushFollow(FOLLOW_expr_in_list4486);
            	    str2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, str2.getTree());


            	    if(!defer) {
            	        if(null != (str2!=null?str2.p:null)) {
            	            addToListPrim((str2!=null?str2.p:null), retval.listPrim);
            	        } else {
            	            printError("Only arrays of num and txt primitives are supported!");
            	        }
            	    }				
            	    	

            	    }
            	    break;

            	default :
            	    break loop85;
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
        public Variable p;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "built_in_function"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2434:1: built_in_function[boolean defer] returns [Variable p] : ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP );
    public final EugeneParser.built_in_function_return built_in_function(boolean defer) throws RecognitionException {
        EugeneParser.built_in_function_return retval = new EugeneParser.built_in_function_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set230=null;
        Token LEFTP231=null;
        Token RIGHTP232=null;
        Token set233=null;
        Token LEFTP234=null;
        Token RIGHTP235=null;
        Token set236=null;
        Token LEFTP237=null;
        Token RIGHTP238=null;
        EugeneParser.expr_return e =null;

        EugeneParser.range_return rg =null;


        Object set230_tree=null;
        Object LEFTP231_tree=null;
        Object RIGHTP232_tree=null;
        Object set233_tree=null;
        Object LEFTP234_tree=null;
        Object RIGHTP235_tree=null;
        Object set236_tree=null;
        Object LEFTP237_tree=null;
        Object RIGHTP238_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2436:2: ( ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP )
            int alt86=3;
            switch ( input.LA(1) ) {
            case SIZEOF_LC:
            case SIZEOF_UC:
            case SIZE_LC:
            case SIZE_UC:
                {
                alt86=1;
                }
                break;
            case RANDOM_LC:
            case RANDOM_UC:
                {
                alt86=2;
                }
                break;
            case SAVE_LC:
            case SAVE_UC:
            case STORE_LC:
            case STORE_UC:
                {
                alt86=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;

            }

            switch (alt86) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2436:4: ( SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_UC ) LEFTP e= expr[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set230=(Token)input.LT(1);

                    if ( (input.LA(1) >= SIZEOF_LC && input.LA(1) <= SIZE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set230)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP231=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4524); 
                    LEFTP231_tree = 
                    (Object)adaptor.create(LEFTP231)
                    ;
                    adaptor.addChild(root_0, LEFTP231_tree);


                    pushFollow(FOLLOW_expr_in_built_in_function4528);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTP232=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4531); 
                    RIGHTP232_tree = 
                    (Object)adaptor.create(RIGHTP232)
                    ;
                    adaptor.addChild(root_0, RIGHTP232_tree);



                    if(!defer) {
                        try {
                            if(null != (e!=null?e.element:null)) {
                                retval.p = this.interp.getSizeOf((e!=null?e.element:null));
                            } else if(null != (e!=null?e.p:null)) {
                                retval.p = this.interp.getSizeOf((e!=null?e.p:null));
                            }
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2449:4: ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set233=(Token)input.LT(1);

                    if ( (input.LA(1) >= RANDOM_LC && input.LA(1) <= RANDOM_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set233)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP234=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4544); 
                    LEFTP234_tree = 
                    (Object)adaptor.create(LEFTP234)
                    ;
                    adaptor.addChild(root_0, LEFTP234_tree);


                    pushFollow(FOLLOW_range_in_built_in_function4548);
                    rg=range(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, rg.getTree());

                    RIGHTP235=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4551); 
                    RIGHTP235_tree = 
                    (Object)adaptor.create(RIGHTP235)
                    ;
                    adaptor.addChild(root_0, RIGHTP235_tree);



                    if(!defer) {
                        try {
                            retval.p = this.interp.getRandom(rg.sor, rg.eor);
                        } catch(EugeneException ee) {
                            printError(ee.getLocalizedMessage());
                        }
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2458:4: ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set236=(Token)input.LT(1);

                    if ( (input.LA(1) >= SAVE_LC && input.LA(1) <= SAVE_UC)||(input.LA(1) >= STORE_LC && input.LA(1) <= STORE_UC) ) {
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


                    LEFTP237=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4568); 
                    LEFTP237_tree = 
                    (Object)adaptor.create(LEFTP237)
                    ;
                    adaptor.addChild(root_0, LEFTP237_tree);


                    pushFollow(FOLLOW_expr_in_built_in_function4572);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTP238=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4575); 
                    RIGHTP238_tree = 
                    (Object)adaptor.create(RIGHTP238)
                    ;
                    adaptor.addChild(root_0, RIGHTP238_tree);



                    if(!defer) {
                        try  {
                            if(null != (e!=null?e.element:null)) {
                                this.interp.storeIntoLibrary((e!=null?e.element:null));
                            } else {
                                throw new EugeneException("Cannot store " + (e!=null?input.toString(e.start,e.stop):null) + " into the library!");
                            }
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
    // $ANTLR end "built_in_function"


    public static class range_return extends ParserRuleReturnScope {
        public Variable sor;
        public Variable eor;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "range"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2473:1: range[boolean defer] returns [Variable sor, Variable eor] : s= expr[defer] COMMA e= expr[defer] ;
    public final EugeneParser.range_return range(boolean defer) throws RecognitionException {
        EugeneParser.range_return retval = new EugeneParser.range_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA239=null;
        EugeneParser.expr_return s =null;

        EugeneParser.expr_return e =null;


        Object COMMA239_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2475:2: (s= expr[defer] COMMA e= expr[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2475:4: s= expr[defer] COMMA e= expr[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_range4597);
            s=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());

            COMMA239=(Token)match(input,COMMA,FOLLOW_COMMA_in_range4600); 
            COMMA239_tree = 
            (Object)adaptor.create(COMMA239)
            ;
            adaptor.addChild(root_0, COMMA239_tree);


            pushFollow(FOLLOW_expr_in_range4604);
            e=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer) {

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2528:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
    public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
        EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token DOT240=null;
        Token set241=null;
        Token LEFTP242=null;
        Token RIGHTP243=null;
        Token LEFTSBR244=null;
        Token RIGHTSBR245=null;
        EugeneParser.expr_return exp =null;

        EugeneParser.object_access_return o =null;


        Object id_tree=null;
        Object DOT240_tree=null;
        Object set241_tree=null;
        Object LEFTP242_tree=null;
        Object RIGHTP243_tree=null;
        Object LEFTSBR244_tree=null;
        Object RIGHTSBR245_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2530:2: (| ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==COMMA||LA90_0==DIV||LA90_0==EQUALS||LA90_0==GEQUAL||LA90_0==GTHAN||LA90_0==LEQUAL||(LA90_0 >= LTHAN && LA90_0 <= MINUS)||(LA90_0 >= MULT && LA90_0 <= NEQUAL)||LA90_0==PLUS||(LA90_0 >= RIGHTCUR && LA90_0 <= RIGHTSBR)||LA90_0==SEMIC||LA90_0==133||(LA90_0 >= 135 && LA90_0 <= 136)||LA90_0==139||(LA90_0 >= 142 && LA90_0 <= 143)||LA90_0==145||(LA90_0 >= 158 && LA90_0 <= 159)||LA90_0==172||(LA90_0 >= 174 && LA90_0 <= 175)||LA90_0==178||(LA90_0 >= 181 && LA90_0 <= 182)||LA90_0==184||(LA90_0 >= 197 && LA90_0 <= 198)) ) {
                alt90=1;
            }
            else if ( (LA90_0==DOT||LA90_0==LEFTSBR) ) {
                alt90=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;

            }
            switch (alt90) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2531:2: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.child = parent;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:4: ( DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
                    int alt89=2;
                    int LA89_0 = input.LA(1);

                    if ( (LA89_0==DOT) ) {
                        alt89=1;
                    }
                    else if ( (LA89_0==LEFTSBR) ) {
                        alt89=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 89, 0, input);

                        throw nvae;

                    }
                    switch (alt89) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:5: DOT (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
                            {
                            DOT240=(Token)match(input,DOT,FOLLOW_DOT_in_object_access4640); 
                            DOT240_tree = 
                            (Object)adaptor.create(DOT240)
                            ;
                            adaptor.addChild(root_0, DOT240_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:9: (id= ID | ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )? )
                            int alt88=2;
                            int LA88_0 = input.LA(1);

                            if ( (LA88_0==ID) ) {
                                alt88=1;
                            }
                            else if ( ((LA88_0 >= SIZE_LC && LA88_0 <= SIZE_UC)) ) {
                                alt88=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 88, 0, input);

                                throw nvae;

                            }
                            switch (alt88) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2536:10: id= ID
                                    {
                                    id=(Token)match(input,ID,FOLLOW_ID_in_object_access4645); 
                                    id_tree = 
                                    (Object)adaptor.create(id)
                                    ;
                                    adaptor.addChild(root_0, id_tree);



                                    if(!defer) {
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
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2550:6: ( SIZE_UC | SIZE_LC ) ( LEFTP RIGHTP )?
                                    {
                                    set241=(Token)input.LT(1);

                                    if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
                                        input.consume();
                                        adaptor.addChild(root_0, 
                                        (Object)adaptor.create(set241)
                                        );
                                        state.errorRecovery=false;
                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        throw mse;
                                    }


                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2550:24: ( LEFTP RIGHTP )?
                                    int alt87=2;
                                    int LA87_0 = input.LA(1);

                                    if ( (LA87_0==LEFTP) ) {
                                        alt87=1;
                                    }
                                    switch (alt87) {
                                        case 1 :
                                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2550:25: LEFTP RIGHTP
                                            {
                                            LEFTP242=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_object_access4658); 
                                            LEFTP242_tree = 
                                            (Object)adaptor.create(LEFTP242)
                                            ;
                                            adaptor.addChild(root_0, LEFTP242_tree);


                                            RIGHTP243=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_object_access4660); 
                                            RIGHTP243_tree = 
                                            (Object)adaptor.create(RIGHTP243)
                                            ;
                                            adaptor.addChild(root_0, RIGHTP243_tree);


                                            }
                                            break;

                                    }



                                    if(!defer) {
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2559:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
                            {
                            LEFTSBR244=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access4670); 
                            LEFTSBR244_tree = 
                            (Object)adaptor.create(LEFTSBR244)
                            ;
                            adaptor.addChild(root_0, LEFTSBR244_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2559:12: (exp= expr[defer] )
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2559:13: exp= expr[defer]
                            {
                            pushFollow(FOLLOW_expr_in_object_access4675);
                            exp=expr(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, exp.getTree());

                            }


                            RIGHTSBR245=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access4679); 
                            RIGHTSBR245_tree = 
                            (Object)adaptor.create(RIGHTSBR245)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR245_tree);



                            if(!defer) {
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


                    pushFollow(FOLLOW_object_access_in_object_access4686);
                    o=object_access(defer, retval.child);

                    state._fsp--;

                    adaptor.addChild(root_0, o.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2591:1: dynamic_naming[boolean defer] returns [String name] : (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR );
    public final EugeneParser.dynamic_naming_return dynamic_naming(boolean defer) throws RecognitionException {
        EugeneParser.dynamic_naming_return retval = new EugeneParser.dynamic_naming_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token DOLLAR246=null;
        Token LEFTCUR247=null;
        Token RIGHTCUR248=null;
        EugeneParser.expr_return e =null;


        Object i_tree=null;
        Object DOLLAR246_tree=null;
        Object LEFTCUR247_tree=null;
        Object RIGHTCUR248_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2593:2: (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==ID) ) {
                alt91=1;
            }
            else if ( (LA91_0==DOLLAR) ) {
                alt91=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;

            }
            switch (alt91) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2593:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_dynamic_naming4711); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer) {
                        retval.name = (i!=null?i.getText():null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2598:4: DOLLAR LEFTCUR e= expr[defer] RIGHTCUR
                    {
                    root_0 = (Object)adaptor.nil();


                    DOLLAR246=(Token)match(input,DOLLAR,FOLLOW_DOLLAR_in_dynamic_naming4718); 
                    DOLLAR246_tree = 
                    (Object)adaptor.create(DOLLAR246)
                    ;
                    adaptor.addChild(root_0, DOLLAR246_tree);


                    LEFTCUR247=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_dynamic_naming4720); 
                    LEFTCUR247_tree = 
                    (Object)adaptor.create(LEFTCUR247)
                    ;
                    adaptor.addChild(root_0, LEFTCUR247_tree);


                    pushFollow(FOLLOW_expr_in_dynamic_naming4724);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTCUR248=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_dynamic_naming4727); 
                    RIGHTCUR248_tree = 
                    (Object)adaptor.create(RIGHTCUR248)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR248_tree);



                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2615:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] );
    public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
        EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.sbolStatement_return s =null;

        EugeneParser.pigeonStatement_return p =null;

        EugeneParser.importStatement_return i =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2617:2: (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] )
            int alt92=3;
            switch ( input.LA(1) ) {
            case SBOL:
                {
                alt92=1;
                }
                break;
            case PIGEON_LC:
            case PIGEON_UC:
                {
                alt92=2;
                }
                break;
            case IMPORT_LC:
            case IMPORT_UC:
                {
                alt92=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;

            }

            switch (alt92) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2617:4: s= sbolStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_sbolStatement_in_dataExchange4752);
                    s=sbolStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());


                    if(!defer) {
                        retval.e = (s!=null?s.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2622:4: p= pigeonStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pigeonStatement_in_dataExchange4762);
                    p=pigeonStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2623:4: i= importStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_importStatement_in_dataExchange4770);
                    i=importStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, i.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2634:1: includeStatement[boolean defer] : ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING ;
    public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
        EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token HASHMARK249=null;
        Token set250=null;

        Object file_tree=null;
        Object HASHMARK249_tree=null;
        Object set250_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2635:2: ( ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2635:4: ( HASHMARK )? ( INCLUDE_LC | INCLUDE_UC ) file= STRING
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2635:4: ( HASHMARK )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==HASHMARK) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2635:5: HASHMARK
                    {
                    HASHMARK249=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement4792); 
                    HASHMARK249_tree = 
                    (Object)adaptor.create(HASHMARK249)
                    ;
                    adaptor.addChild(root_0, HASHMARK249_tree);


                    }
                    break;

            }


            set250=(Token)input.LT(1);

            if ( (input.LA(1) >= INCLUDE_LC && input.LA(1) <= INCLUDE_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set250)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement4804); 
            file_tree = 
            (Object)adaptor.create(file)
            ;
            adaptor.addChild(root_0, file_tree);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2647:1: importStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP ;
    public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
        EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token set251=null;
        Token LEFTP252=null;
        Token RIGHTP253=null;

        Object file_tree=null;
        Object set251_tree=null;
        Object LEFTP252_tree=null;
        Object RIGHTP253_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2649:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2649:4: ( IMPORT_LC | IMPORT_UC ) LEFTP file= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set251=(Token)input.LT(1);

            if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set251)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP252=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement4831); 
            LEFTP252_tree = 
            (Object)adaptor.create(LEFTP252)
            ;
            adaptor.addChild(root_0, LEFTP252_tree);


            file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement4835); 
            file_tree = 
            (Object)adaptor.create(file)
            ;
            adaptor.addChild(root_0, file_tree);



            if(!defer) {
                try {
                    retval.e = this.interp.importFile((file!=null?file.getText():null));
                } catch(EugeneException ee) {
                    printError(ee.getLocalizedMessage());
                }
            }
            	

            RIGHTP253=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement4839); 
            RIGHTP253_tree = 
            (Object)adaptor.create(RIGHTP253)
            ;
            adaptor.addChild(root_0, RIGHTP253_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2661:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) ;
    public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SBOL254=null;
        Token DOT255=null;
        EugeneParser.sbolImportStatement_return i =null;

        EugeneParser.sbolExportStatement_return sbolExportStatement256 =null;


        Object SBOL254_tree=null;
        Object DOT255_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2663:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2663:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            {
            root_0 = (Object)adaptor.nil();


            SBOL254=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement4861); 
            SBOL254_tree = 
            (Object)adaptor.create(SBOL254)
            ;
            adaptor.addChild(root_0, SBOL254_tree);


            DOT255=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement4863); 
            DOT255_tree = 
            (Object)adaptor.create(DOT255)
            ;
            adaptor.addChild(root_0, DOT255_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2663:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( ((LA94_0 >= EXPORT_LC && LA94_0 <= EXPORT_UC)) ) {
                alt94=1;
            }
            else if ( ((LA94_0 >= IMPORT_LC && LA94_0 <= IMPORT_UC)) ) {
                alt94=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;

            }
            switch (alt94) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2663:14: sbolExportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement4866);
                    sbolExportStatement256=sbolExportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sbolExportStatement256.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2663:43: i= sbolImportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement4873);
                    i=sbolImportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, i.getTree());


                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2670:1: sbolExportStatement[boolean defer] : ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
    public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token filenameToken=null;
        Token set257=null;
        Token LEFTP258=null;
        Token COMMA259=null;
        Token RIGHTP260=null;

        Object idToken_tree=null;
        Object filenameToken_tree=null;
        Object set257_tree=null;
        Object LEFTP258_tree=null;
        Object COMMA259_tree=null;
        Object RIGHTP260_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2671:2: ( ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2671:4: ( EXPORT_LC | EXPORT_UC ) LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set257=(Token)input.LT(1);

            if ( (input.LA(1) >= EXPORT_LC && input.LA(1) <= EXPORT_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set257)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP258=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement4896); 
            LEFTP258_tree = 
            (Object)adaptor.create(LEFTP258)
            ;
            adaptor.addChild(root_0, LEFTP258_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement4900); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            COMMA259=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement4902); 
            COMMA259_tree = 
            (Object)adaptor.create(COMMA259)
            ;
            adaptor.addChild(root_0, COMMA259_tree);


            filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement4906); 
            filenameToken_tree = 
            (Object)adaptor.create(filenameToken)
            ;
            adaptor.addChild(root_0, filenameToken_tree);


            RIGHTP260=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement4908); 
            RIGHTP260_tree = 
            (Object)adaptor.create(RIGHTP260)
            ;
            adaptor.addChild(root_0, RIGHTP260_tree);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2684:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP ;
    public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token fileToken=null;
        Token set261=null;
        Token LEFTP262=null;
        Token RIGHTP263=null;

        Object fileToken_tree=null;
        Object set261_tree=null;
        Object LEFTP262_tree=null;
        Object RIGHTP263_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2686:2: ( ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2686:4: ( IMPORT_LC | IMPORT_UC ) LEFTP fileToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set261=(Token)input.LT(1);

            if ( (input.LA(1) >= IMPORT_LC && input.LA(1) <= IMPORT_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set261)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP262=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement4937); 
            LEFTP262_tree = 
            (Object)adaptor.create(LEFTP262)
            ;
            adaptor.addChild(root_0, LEFTP262_tree);


            fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement4941); 
            fileToken_tree = 
            (Object)adaptor.create(fileToken)
            ;
            adaptor.addChild(root_0, fileToken_tree);


            RIGHTP263=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement4943); 
            RIGHTP263_tree = 
            (Object)adaptor.create(RIGHTP263)
            ;
            adaptor.addChild(root_0, RIGHTP263_tree);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2731:1: pigeonStatement[boolean defer] : ( PIGEON_LC | PIGEON_UC ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.pigeonStatement_return pigeonStatement(boolean defer) throws RecognitionException {
        EugeneParser.pigeonStatement_return retval = new EugeneParser.pigeonStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token set264=null;
        Token LEFTP265=null;
        Token RIGHTP266=null;

        Object idToken_tree=null;
        Object set264_tree=null;
        Object LEFTP265_tree=null;
        Object RIGHTP266_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2732:2: ( ( PIGEON_LC | PIGEON_UC ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2732:4: ( PIGEON_LC | PIGEON_UC ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set264=(Token)input.LT(1);

            if ( (input.LA(1) >= PIGEON_LC && input.LA(1) <= PIGEON_UC) ) {
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


            LEFTP265=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_pigeonStatement4972); 
            LEFTP265_tree = 
            (Object)adaptor.create(LEFTP265)
            ;
            adaptor.addChild(root_0, LEFTP265_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_pigeonStatement4976); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP266=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_pigeonStatement4978); 
            RIGHTP266_tree = 
            (Object)adaptor.create(RIGHTP266)
            ;
            adaptor.addChild(root_0, RIGHTP266_tree);



            if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2780:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
    public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
        EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token n=null;
        Token ASSERT267=null;
        Token LEFTP268=null;
        Token DOT269=null;
        Token set270=null;
        Token LEFTP271=null;
        Token RIGHTP272=null;
        Token EQUALS273=null;
        Token EQUALS274=null;
        Token RIGHTP275=null;
        Token NOTE276=null;
        Token LEFTP277=null;
        Token DOT278=null;
        Token set279=null;
        Token LEFTP280=null;
        Token RIGHTP281=null;
        Token EQUALS282=null;
        Token EQUALS283=null;
        Token RIGHTP284=null;

        Object id_tree=null;
        Object n_tree=null;
        Object ASSERT267_tree=null;
        Object LEFTP268_tree=null;
        Object DOT269_tree=null;
        Object set270_tree=null;
        Object LEFTP271_tree=null;
        Object RIGHTP272_tree=null;
        Object EQUALS273_tree=null;
        Object EQUALS274_tree=null;
        Object RIGHTP275_tree=null;
        Object NOTE276_tree=null;
        Object LEFTP277_tree=null;
        Object DOT278_tree=null;
        Object set279_tree=null;
        Object LEFTP280_tree=null;
        Object RIGHTP281_tree=null;
        Object EQUALS282_tree=null;
        Object EQUALS283_tree=null;
        Object RIGHTP284_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2781:2: (| ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
            int alt95=3;
            switch ( input.LA(1) ) {
            case SEMIC:
                {
                alt95=1;
                }
                break;
            case ASSERT:
                {
                alt95=2;
                }
                break;
            case NOTE:
                {
                alt95=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;

            }

            switch (alt95) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2781:5: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2781:7: ASSERT LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    ASSERT267=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements5002); 
                    ASSERT267_tree = 
                    (Object)adaptor.create(ASSERT267)
                    ;
                    adaptor.addChild(root_0, ASSERT267_tree);


                    LEFTP268=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5004); 
                    LEFTP268_tree = 
                    (Object)adaptor.create(LEFTP268)
                    ;
                    adaptor.addChild(root_0, LEFTP268_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements5008); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT269=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements5010); 
                    DOT269_tree = 
                    (Object)adaptor.create(DOT269)
                    ;
                    adaptor.addChild(root_0, DOT269_tree);


                    set270=(Token)input.LT(1);

                    if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set270)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP271=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5018); 
                    LEFTP271_tree = 
                    (Object)adaptor.create(LEFTP271)
                    ;
                    adaptor.addChild(root_0, LEFTP271_tree);


                    RIGHTP272=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5020); 
                    RIGHTP272_tree = 
                    (Object)adaptor.create(RIGHTP272)
                    ;
                    adaptor.addChild(root_0, RIGHTP272_tree);


                    EQUALS273=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5022); 
                    EQUALS273_tree = 
                    (Object)adaptor.create(EQUALS273)
                    ;
                    adaptor.addChild(root_0, EQUALS273_tree);


                    EQUALS274=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5024); 
                    EQUALS274_tree = 
                    (Object)adaptor.create(EQUALS274)
                    ;
                    adaptor.addChild(root_0, EQUALS274_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5028); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP275=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5030); 
                    RIGHTP275_tree = 
                    (Object)adaptor.create(RIGHTP275)
                    ;
                    adaptor.addChild(root_0, RIGHTP275_tree);



                    if(!defer) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2797:5: NOTE LEFTP id= ID DOT ( SIZE_UC | SIZE_LC ) LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    NOTE276=(Token)match(input,NOTE,FOLLOW_NOTE_in_testStatements5038); 
                    NOTE276_tree = 
                    (Object)adaptor.create(NOTE276)
                    ;
                    adaptor.addChild(root_0, NOTE276_tree);


                    LEFTP277=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5040); 
                    LEFTP277_tree = 
                    (Object)adaptor.create(LEFTP277)
                    ;
                    adaptor.addChild(root_0, LEFTP277_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements5044); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT278=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements5046); 
                    DOT278_tree = 
                    (Object)adaptor.create(DOT278)
                    ;
                    adaptor.addChild(root_0, DOT278_tree);


                    set279=(Token)input.LT(1);

                    if ( (input.LA(1) >= SIZE_LC && input.LA(1) <= SIZE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set279)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP280=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements5054); 
                    LEFTP280_tree = 
                    (Object)adaptor.create(LEFTP280)
                    ;
                    adaptor.addChild(root_0, LEFTP280_tree);


                    RIGHTP281=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5056); 
                    RIGHTP281_tree = 
                    (Object)adaptor.create(RIGHTP281)
                    ;
                    adaptor.addChild(root_0, RIGHTP281_tree);


                    EQUALS282=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5058); 
                    EQUALS282_tree = 
                    (Object)adaptor.create(EQUALS282)
                    ;
                    adaptor.addChild(root_0, EQUALS282_tree);


                    EQUALS283=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements5060); 
                    EQUALS283_tree = 
                    (Object)adaptor.create(EQUALS283)
                    ;
                    adaptor.addChild(root_0, EQUALS283_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements5064); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP284=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements5066); 
                    RIGHTP284_tree = 
                    (Object)adaptor.create(RIGHTP284)
                    ;
                    adaptor.addChild(root_0, RIGHTP284_tree);



                    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2806:1: function_definition[boolean defer] : rt= type_specification[defer] n= ID LEFTP lop= list_of_parameters[defer] RIGHTP LEFTCUR stmts= function_statements[defer] RIGHTCUR ;
    public final EugeneParser.function_definition_return function_definition(boolean defer) throws RecognitionException {
        EugeneParser.function_definition_return retval = new EugeneParser.function_definition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token LEFTP285=null;
        Token RIGHTP286=null;
        Token LEFTCUR287=null;
        Token RIGHTCUR288=null;
        EugeneParser.type_specification_return rt =null;

        EugeneParser.list_of_parameters_return lop =null;

        EugeneParser.function_statements_return stmts =null;


        Object n_tree=null;
        Object LEFTP285_tree=null;
        Object RIGHTP286_tree=null;
        Object LEFTCUR287_tree=null;
        Object RIGHTCUR288_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2807:2: (rt= type_specification[defer] n= ID LEFTP lop= list_of_parameters[defer] RIGHTP LEFTCUR stmts= function_statements[defer] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2807:4: rt= type_specification[defer] n= ID LEFTP lop= list_of_parameters[defer] RIGHTP LEFTCUR stmts= function_statements[defer] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_type_specification_in_function_definition5085);
            rt=type_specification(defer);

            state._fsp--;

            adaptor.addChild(root_0, rt.getTree());

            n=(Token)match(input,ID,FOLLOW_ID_in_function_definition5090); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            LEFTP285=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_function_definition5092); 
            LEFTP285_tree = 
            (Object)adaptor.create(LEFTP285)
            ;
            adaptor.addChild(root_0, LEFTP285_tree);


            pushFollow(FOLLOW_list_of_parameters_in_function_definition5096);
            lop=list_of_parameters(defer);

            state._fsp--;

            adaptor.addChild(root_0, lop.getTree());

            RIGHTP286=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_function_definition5099); 
            RIGHTP286_tree = 
            (Object)adaptor.create(RIGHTP286)
            ;
            adaptor.addChild(root_0, RIGHTP286_tree);


            LEFTCUR287=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_function_definition5101); 
            LEFTCUR287_tree = 
            (Object)adaptor.create(LEFTCUR287)
            ;
            adaptor.addChild(root_0, LEFTCUR287_tree);


            pushFollow(FOLLOW_function_statements_in_function_definition5105);
            stmts=function_statements(defer);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR288=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_function_definition5108); 
            RIGHTCUR288_tree = 
            (Object)adaptor.create(RIGHTCUR288)
            ;
            adaptor.addChild(root_0, RIGHTCUR288_tree);



            if(!defer) {
                try {
                    this.interp.defineFunction(
                        (rt!=null?input.toString(rt.start,rt.stop):null),          // return type
                        (n!=null?n.getText():null),           // the name of the function
                        (lop!=null?lop.parameters:null),   // list of parameters
                        (stmts!=null?((Token)stmts.start):null));     // list of statements
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2822:1: type_specification[boolean defer] : ( NUM | TXT );
    public final EugeneParser.type_specification_return type_specification(boolean defer) throws RecognitionException {
        EugeneParser.type_specification_return retval = new EugeneParser.type_specification_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set289=null;

        Object set289_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2823:2: ( NUM | TXT )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set289=(Token)input.LT(1);

            if ( input.LA(1)==NUM||input.LA(1)==TXT ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set289)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2826:1: list_of_parameters[boolean defer] returns [List<NamedElement> parameters] : t= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? ;
    public final EugeneParser.list_of_parameters_return list_of_parameters(boolean defer) throws RecognitionException {
        EugeneParser.list_of_parameters_return retval = new EugeneParser.list_of_parameters_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token COMMA290=null;
        EugeneParser.type_specification_return t =null;

        EugeneParser.list_of_parameters_return lop =null;


        Object n_tree=null;
        Object COMMA290_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2828:2: (t= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2828:4: t= type_specification[defer] n= ID ( COMMA lop= list_of_parameters[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_type_specification_in_list_of_parameters5147);
            t=type_specification(defer);

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());

            n=(Token)match(input,ID,FOLLOW_ID_in_list_of_parameters5152); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);



            if(!defer) {
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2843:4: ( COMMA lop= list_of_parameters[defer] )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==COMMA) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2843:5: COMMA lop= list_of_parameters[defer]
                    {
                    COMMA290=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_parameters5157); 
                    COMMA290_tree = 
                    (Object)adaptor.create(COMMA290)
                    ;
                    adaptor.addChild(root_0, COMMA290_tree);


                    pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters5161);
                    lop=list_of_parameters(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lop.getTree());


                    if(!defer) {
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


    public static class function_statements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_statements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2850:1: function_statements[boolean defer] : ( statement[defer] | return_statement[defer] )+ ;
    public final EugeneParser.function_statements_return function_statements(boolean defer) throws RecognitionException {
        EugeneParser.function_statements_return retval = new EugeneParser.function_statements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.statement_return statement291 =null;

        EugeneParser.return_statement_return return_statement292 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2851:2: ( ( statement[defer] | return_statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2851:4: ( statement[defer] | return_statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2851:4: ( statement[defer] | return_statement[defer] )+
            int cnt97=0;
            loop97:
            do {
                int alt97=3;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==ARRAY||(LA97_0 >= ASSERT && LA97_0 <= COLLECTION)||LA97_0==DEVICE||(LA97_0 >= EXIT_LC && LA97_0 <= EXIT_UC)||LA97_0==GRAMMAR||(LA97_0 >= HASHMARK && LA97_0 <= ID)||(LA97_0 >= IMPORT_LC && LA97_0 <= INTERACTION)||(LA97_0 >= LC_FOR && LA97_0 <= LC_IF)||LA97_0==LC_WHILE||(LA97_0 >= NOTE && LA97_0 <= NUM)||(LA97_0 >= PART && LA97_0 <= PIGEON_UC)||(LA97_0 >= PRINTLN_LC && LA97_0 <= RANDOM_UC)||(LA97_0 >= RULE && LA97_0 <= STORE_UC)||(LA97_0 >= TXT && LA97_0 <= TYPE)||(LA97_0 >= UC_FOR && LA97_0 <= UC_IF)||LA97_0==UC_WHILE) ) {
                    alt97=1;
                }
                else if ( ((LA97_0 >= RETURN_LC && LA97_0 <= RETURN_UC)) ) {
                    alt97=2;
                }


                switch (alt97) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2851:5: statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_function_statements5182);
            	    statement291=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement291.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2851:24: return_statement[defer]
            	    {
            	    pushFollow(FOLLOW_return_statement_in_function_statements5187);
            	    return_statement292=return_statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, return_statement292.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt97 >= 1 ) break loop97;
                        EarlyExitException eee =
                            new EarlyExitException(97, input);
                        throw eee;
                }
                cnt97++;
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
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "return_statement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2854:1: return_statement[boolean defer] : ( RETURN_LC | RETURN_UC ) e= expr[defer] SEMIC ;
    public final EugeneParser.return_statement_return return_statement(boolean defer) throws RecognitionException {
        EugeneParser.return_statement_return retval = new EugeneParser.return_statement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set293=null;
        Token SEMIC294=null;
        EugeneParser.expr_return e =null;


        Object set293_tree=null;
        Object SEMIC294_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2855:2: ( ( RETURN_LC | RETURN_UC ) e= expr[defer] SEMIC )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2855:4: ( RETURN_LC | RETURN_UC ) e= expr[defer] SEMIC
            {
            root_0 = (Object)adaptor.nil();


            set293=(Token)input.LT(1);

            if ( (input.LA(1) >= RETURN_LC && input.LA(1) <= RETURN_UC) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set293)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_expr_in_return_statement5213);
            e=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            SEMIC294=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_return_statement5216); 
            SEMIC294_tree = 
            (Object)adaptor.create(SEMIC294)
            ;
            adaptor.addChild(root_0, SEMIC294_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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


 

    public static final BitSet FOLLOW_statement_in_prog991 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_function_definition_in_prog996 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_EOF_in_prog1001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeStatement_in_statement1027 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_statement1038 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_printStatement_in_statement1047 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement1055 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement1063 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_statement1073 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imperativeStatements_in_statement1083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predefined_statements_in_statement1089 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testStatements_in_predefined_statements1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_predefined_statements1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_built_in_function_in_predefined_statements1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement1153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instantiation_in_declarationStatement1171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement1183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_grammarDeclaration_in_declarationStatement1189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1213 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numdecl_in_variableDeclaration1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1228 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtdecl_in_variableDeclaration1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1243 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1245 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1247 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration1251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1262 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1264 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1266 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_variableDeclaration1281 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_booldecl_in_variableDeclaration1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1312 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1318 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1328 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_numdecl1330 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_numdecl1335 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1343 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1365 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1372 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1385 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_txtdecl1387 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_txtdecl1391 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1399 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1421 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1428 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1440 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1442 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_txtlistdecl1448 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1456 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1478 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1485 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1497 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_numlistdecl1499 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_numlistdecl1504 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1512 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1534 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_booldecl1541 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_booldecl_in_booldecl1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1553 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_booldecl1555 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_booldecl1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1577 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration1581 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1583 = new BitSet(new long[]{0x0000000000000600L,0x0000080000000008L});
    public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1587 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1615 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1617 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1634 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1636 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_propertyType1645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_typeDeclaration1674 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_typeDeclaration1679 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1682 = new BitSet(new long[]{0x0000000200000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1687 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_partTypeDeclaration1711 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_partTypeDeclaration1720 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1723 = new BitSet(new long[]{0x0000000200000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1728 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1760 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1767 = new BitSet(new long[]{0x0040000200000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1770 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1772 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_containerDeclaration1780 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1785 = new BitSet(new long[]{0x2060008246024E40L,0x00001F7E741E0078L});
    public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1788 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1826 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr_in_list_of_declarations1833 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_declarations1841 = new BitSet(new long[]{0x2060008246024E40L,0x00001F7E701E0078L});
    public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations1845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instantiation1873 = new BitSet(new long[]{0x0000000200020000L});
    public static final BitSet FOLLOW_dynamic_naming_in_instantiation1879 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_instantiation1886 = new BitSet(new long[]{0x2060000206060000L,0x0000077E641C0010L});
    public static final BitSet FOLLOW_listOfDotValues_in_instantiation1891 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_listOfValues_in_instantiation1896 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_instantiation1901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1924 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1928 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1934 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1938 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1946 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfDotValues1949 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1951 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1955 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1963 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1967 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1977 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr_in_listOfValues1998 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfValues2004 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_listOfValues2010 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration2029 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_deviceDeclaration2033 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration2036 = new BitSet(new long[]{0x2040000200000000L,0x0000000004000800L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration2041 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration2046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_in_deviceComponents2077 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_deviceComponents2083 = new BitSet(new long[]{0x2040000200000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceComponents2087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_selection2111 = new BitSet(new long[]{0x2000000200000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_selection_list_in_selection2115 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_selection2118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection_list2156 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_PIPE_in_selection_list2162 = new BitSet(new long[]{0x2000000200000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_selection_list_in_selection_list2166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_device_component2192 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_device_component2202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_assignment_in_assignment2222 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2225 = new BitSet(new long[]{0x2060001A06020020L,0x0000077EE01D0390L});
    public static final BitSet FOLLOW_AMP_in_assignment2230 = new BitSet(new long[]{0x2060001A06020000L,0x0000077EE01D0390L});
    public static final BitSet FOLLOW_rhs_assignment_in_assignment2236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lhs_assignment2251 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lhs_access2273 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_lhs_access2277 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2281 = new BitSet(new long[]{0x0000000200000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_set_in_lhs_access2283 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2289 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_access2292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_rhs_assignment2315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_rhs_assignment2335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_listOfIDs2363 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfIDs2372 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_ruleDeclaration2400 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2404 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2406 = new BitSet(new long[]{0x2465C00200000000L,0xF8B8010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_set_in_ruleDeclaration2411 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2419 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_ruleDeclaration2421 = new BitSet(new long[]{0x2464C00200000000L,0xF898010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2429 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2827 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQUAL_in_relationalOperators2834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTHAN_in_relationalOperators2839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GTHAN_in_relationalOperators2844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEQUAL_in_relationalOperators2849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GEQUAL_in_relationalOperators2854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_predicate_in_cnf_rule2955 = new BitSet(new long[]{0x0200010000000002L,0x0000200000000000L});
    public static final BitSet FOLLOW_set_in_cnf_rule2963 = new BitSet(new long[]{0x2464C00200000000L,0xF898010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_cnf_rule_in_cnf_rule2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate3003 = new BitSet(new long[]{0x0802000000000002L,0x0040000000000000L});
    public static final BitSet FOLLOW_set_in_or_predicate3009 = new BitSet(new long[]{0x2464C00200000000L,0xF898010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate3019 = new BitSet(new long[]{0x0802000000000002L,0x0040000000000000L});
    public static final BitSet FOLLOW_set_in_negated_predicate3047 = new BitSet(new long[]{0x2064400200000000L,0xF888010000100010L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_predicate_in_negated_predicate3057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_negated_predicate3067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_predicate3094 = new BitSet(new long[]{0x0004400000000000L,0xF888000000000000L,0xFFFFFFFFBFFFFFFFL,0x00000000000001DFL});
    public static final BitSet FOLLOW_ruleOperator_in_predicate3104 = new BitSet(new long[]{0x0040000200000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_operand_in_predicate3113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_predicate3127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionRule_in_predicate3136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand3167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_operand3176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_operand3183 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_operand3187 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_operand3189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionRule3215 = new BitSet(new long[]{0x10800000A0100000L,0x0000000000000001L,0x0164D000C002C9A0L,0x0000000000000060L});
    public static final BitSet FOLLOW_exp_op_in_expressionRule3220 = new BitSet(new long[]{0x2020000200000000L,0x0000010000100010L});
    public static final BitSet FOLLOW_expression_in_expressionRule3225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_operand_in_expression3249 = new BitSet(new long[]{0xA000000000010002L,0x0000000000000800L});
    public static final BitSet FOLLOW_exp_operator_in_expression3258 = new BitSet(new long[]{0x2020000200000000L,0x0000010000100010L});
    public static final BitSet FOLLOW_expression_in_expression3263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_expression3275 = new BitSet(new long[]{0x2020000200000000L,0x0000010000100010L});
    public static final BitSet FOLLOW_expression_in_expression3277 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_expression3280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp_operator3299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operator3307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_exp_operator3314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_exp_operator3321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_exp_operand3351 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_exp_operand3353 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_exp_operand3362 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3372 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3374 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3393 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp_operand3426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOperators_in_exp_op3453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GRAMMAR_in_grammarDeclaration3472 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_grammarDeclaration3476 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_grammarDeclaration3478 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_list_of_production_rules_in_grammarDeclaration3480 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_grammarDeclaration3483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_production_rule_in_list_of_production_rules3495 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_list_of_production_rules3498 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_list_of_production_rules_in_list_of_production_rules3501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_production_rule3521 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ARROW_in_production_rule3525 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_right_hand_side_in_production_rule3527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_right_hand_side3543 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_right_hand_side3548 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_right_hand_side_in_right_hand_side3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_right_hand_side3558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3591 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_interactionDeclaration3595 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3597 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3601 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3627 = new BitSet(new long[]{0x0004400000000000L,0x0088000000000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3631 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_interaction3636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3645 = new BitSet(new long[]{0x0004400000000000L,0x0088000000000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3649 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interaction3652 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_interaction_in_interaction3656 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_interaction3659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRODUCT_in_functionCall3726 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_PERMUTE_in_functionCall3730 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_functionCall3733 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_functionCall3737 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_functionCall3739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_printStatement3757 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3763 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3767 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_printStatement3777 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3783 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3787 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_toPrint3811 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_toPrint_prime_in_toPrint3816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_toPrint_prime3842 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_toPrint_in_toPrint_prime3846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_imperativeStatements3866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_imperativeStatements3878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_imperativeStatements3884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ifStatement3910 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3916 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3920 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3923 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3925 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3933 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3936 = new BitSet(new long[]{0x0000060000000002L,0x0000C00000000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3951 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3957 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3961 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3964 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3966 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3974 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3977 = new BitSet(new long[]{0x0000060000000002L,0x0000C00000000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3993 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3999 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement4007 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement4010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_imp_condition4034 = new BitSet(new long[]{0x10800000A0100000L,0x0000000000000001L,0x0164D000C002C9A0L,0x0000000000000060L});
    public static final BitSet FOLLOW_relationalOperators_in_imp_condition4039 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_imp_condition4043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_forall_iterator4059 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator4068 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_forall_iterator4070 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator4076 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator4078 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_listOfStatements_in_forall_iterator4083 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator4090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_for_loop4102 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_for_loop4108 = new BitSet(new long[]{0x0000000000000600L,0x0000080000000008L});
    public static final BitSet FOLLOW_variableDeclaration_in_for_loop4112 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop4115 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_imp_condition_in_for_loop4119 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop4122 = new BitSet(new long[]{0x0000000200000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_assignment_in_for_loop4127 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_for_loop4132 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_for_loop4134 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_listOfStatements_in_for_loop4142 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_for_loop4149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_while_loop4163 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_while_loop4169 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_imp_condition_in_while_loop4173 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_while_loop4176 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_while_loop4178 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_listOfStatements_in_while_loop4186 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_while_loop4193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_listOfStatements4210 = new BitSet(new long[]{0x000838FB40604F42L,0x0107187EF00FF3ECL});
    public static final BitSet FOLLOW_multExpr_in_expr4238 = new BitSet(new long[]{0x2000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_set_in_expr4247 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_multExpr_in_expr4255 = new BitSet(new long[]{0x2000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_atom_in_multExpr4285 = new BitSet(new long[]{0x8000000000010002L});
    public static final BitSet FOLLOW_MULT_in_multExpr4296 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_DIV_in_multExpr4300 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_atom_in_multExpr4305 = new BitSet(new long[]{0x8000000000010002L});
    public static final BitSet FOLLOW_NUMBER_in_atom4332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom4348 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100010L});
    public static final BitSet FOLLOW_NUMBER_in_atom4353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dynamic_naming_in_atom4398 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_object_access_in_atom4405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_atom4422 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_atom4424 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_atom4427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_atom4436 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_list_in_atom4438 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_atom4441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_built_in_function_in_atom4452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_list4475 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list4482 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_list4486 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_set_in_built_in_function4514 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4524 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_built_in_function4528 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_built_in_function4538 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4544 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_range_in_built_in_function4548 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_built_in_function4558 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4568 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_built_in_function4572 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_range4597 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_range4600 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_range4604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_object_access4640 = new BitSet(new long[]{0x0000000200000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_ID_in_object_access4645 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_set_in_object_access4651 = new BitSet(new long[]{0x0060000000040000L});
    public static final BitSet FOLLOW_LEFTP_in_object_access4658 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_object_access4660 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_LEFTSBR_in_object_access4670 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_object_access4675 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_object_access4679 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_object_access_in_object_access4686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_dynamic_naming4711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOLLAR_in_dynamic_naming4718 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_dynamic_naming4720 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_dynamic_naming4724 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_dynamic_naming4727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolStatement_in_dataExchange4752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pigeonStatement_in_dataExchange4762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importStatement_in_dataExchange4770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASHMARK_in_includeStatement4792 = new BitSet(new long[]{0x0000006000000000L});
    public static final BitSet FOLLOW_set_in_includeStatement4796 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_includeStatement4804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_importStatement4825 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_importStatement4831 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_importStatement4835 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_importStatement4839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBOL_in_sbolStatement4861 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_sbolStatement4863 = new BitSet(new long[]{0x0000001801800000L});
    public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement4866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement4873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolExportStatement4890 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement4896 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_sbolExportStatement4900 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_sbolExportStatement4902 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_sbolExportStatement4906 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement4908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolImportStatement4931 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement4937 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_STRING_in_sbolImportStatement4941 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement4943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_pigeonStatement4966 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_pigeonStatement4972 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_pigeonStatement4976 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_pigeonStatement4978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_testStatements5002 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements5004 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_testStatements5008 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_testStatements5010 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_set_in_testStatements5012 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements5018 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements5020 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements5022 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements5024 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements5028 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements5030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTE_in_testStatements5038 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements5040 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_testStatements5044 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_testStatements5046 = new BitSet(new long[]{0x0000000000000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_set_in_testStatements5048 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements5054 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements5056 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements5058 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements5060 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements5064 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements5066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specification_in_function_definition5085 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_function_definition5090 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_function_definition5092 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000008L});
    public static final BitSet FOLLOW_list_of_parameters_in_function_definition5096 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_RIGHTP_in_function_definition5099 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_function_definition5101 = new BitSet(new long[]{0x000838FB40604F40L,0x0107187EF18FF3ECL});
    public static final BitSet FOLLOW_function_statements_in_function_definition5105 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_function_definition5108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_specification_in_list_of_parameters5147 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ID_in_list_of_parameters5152 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_parameters5157 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000008L});
    public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters5161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_function_statements5182 = new BitSet(new long[]{0x000838FB40604F42L,0x0107187EF18FF3ECL});
    public static final BitSet FOLLOW_return_statement_in_function_statements5187 = new BitSet(new long[]{0x000838FB40604F42L,0x0107187EF18FF3ECL});
    public static final BitSet FOLLOW_set_in_return_statement5203 = new BitSet(new long[]{0x2060000206020000L,0x0000077E601C0010L});
    public static final BitSet FOLLOW_expr_in_return_statement5213 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_SEMIC_in_return_statement5216 = new BitSet(new long[]{0x0000000000000002L});

}