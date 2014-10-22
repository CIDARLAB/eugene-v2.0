// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g 2014-10-22 14:07:45

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADDPROPS", "AMP", "ARRAY", "ARROW", "ASSERT", "BOOL", "BOOLEAN", "COLLECTION", "COLON", "COMMA", "DEVICE", "DIGIT", "DIV", "DOLLAR", "DOT", "DOTDOT", "EQUALS", "EXIT_LC", "EXIT_UC", "EXPORT", "FALSE_LC", "FALSE_UC", "FLEXIBLE", "GENBANK", "GEQUAL", "GRAMMAR", "GTHAN", "HASHMARK", "ID", "IMAGE", "INTERACTION", "LC_AND", "LC_ELSE", "LC_ELSEIF", "LC_FOR", "LC_FORALL", "LC_IF", "LC_IMPORT", "LC_INCLUDE", "LC_INDUCES", "LC_NOT", "LC_ON", "LC_OR", "LC_PIGEON", "LC_REPRESSES", "LC_WHILE", "LEFTCUR", "LEFTP", "LEFTSBR", "LEQUAL", "LINE_COMMENT", "LOG_AND", "LOG_NOT", "LOG_OR", "LTHAN", "MINUS", "ML_COMMENT", "MULT", "NEQUAL", "NEWLINE", "NOTE", "NUM", "NUMBER", "PART", "PART_TYPE", "PERMUTE", "PIGEON", "PIPE", "PLUS", "PRINT", "PRINTLN", "PRODUCT", "PROPERTY", "RANDOM_LC", "RANDOM_UC", "REAL", "REF", "REGISTRY", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "RULE", "SAVE_LC", "SAVE_UC", "SBOL", "SEMIC", "SIZE", "SIZEOF_LC", "SIZEOF_UC", "STORE_LC", "STORE_UC", "STRICT", "STRING", "TRUE_LC", "TRUE_UC", "TXT", "TYPE", "UC_AND", "UC_ELSE", "UC_ELSEIF", "UC_FOR", "UC_FORALL", "UC_IF", "UC_IMPORT", "UC_INCLUDE", "UC_INDUCES", "UC_NOT", "UC_ON", "UC_OR", "UC_REPRESSES", "UC_WHILE", "UNDERS", "WS", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", "'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'MATCHES'", "'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", "'SOUNDSLIKE'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'reverse'", "'same_count'", "'same_orientation'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", "'soundslike'", "'startswith'", "'then'", "'with'"
    };

    public static final int EOF=-1;
    public static final int T__117=117;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__120=120;
    public static final int T__121=121;
    public static final int T__122=122;
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
    public static final int EXPORT=23;
    public static final int FALSE_LC=24;
    public static final int FALSE_UC=25;
    public static final int FLEXIBLE=26;
    public static final int GENBANK=27;
    public static final int GEQUAL=28;
    public static final int GRAMMAR=29;
    public static final int GTHAN=30;
    public static final int HASHMARK=31;
    public static final int ID=32;
    public static final int IMAGE=33;
    public static final int INTERACTION=34;
    public static final int LC_AND=35;
    public static final int LC_ELSE=36;
    public static final int LC_ELSEIF=37;
    public static final int LC_FOR=38;
    public static final int LC_FORALL=39;
    public static final int LC_IF=40;
    public static final int LC_IMPORT=41;
    public static final int LC_INCLUDE=42;
    public static final int LC_INDUCES=43;
    public static final int LC_NOT=44;
    public static final int LC_ON=45;
    public static final int LC_OR=46;
    public static final int LC_PIGEON=47;
    public static final int LC_REPRESSES=48;
    public static final int LC_WHILE=49;
    public static final int LEFTCUR=50;
    public static final int LEFTP=51;
    public static final int LEFTSBR=52;
    public static final int LEQUAL=53;
    public static final int LINE_COMMENT=54;
    public static final int LOG_AND=55;
    public static final int LOG_NOT=56;
    public static final int LOG_OR=57;
    public static final int LTHAN=58;
    public static final int MINUS=59;
    public static final int ML_COMMENT=60;
    public static final int MULT=61;
    public static final int NEQUAL=62;
    public static final int NEWLINE=63;
    public static final int NOTE=64;
    public static final int NUM=65;
    public static final int NUMBER=66;
    public static final int PART=67;
    public static final int PART_TYPE=68;
    public static final int PERMUTE=69;
    public static final int PIGEON=70;
    public static final int PIPE=71;
    public static final int PLUS=72;
    public static final int PRINT=73;
    public static final int PRINTLN=74;
    public static final int PRODUCT=75;
    public static final int PROPERTY=76;
    public static final int RANDOM_LC=77;
    public static final int RANDOM_UC=78;
    public static final int REAL=79;
    public static final int REF=80;
    public static final int REGISTRY=81;
    public static final int RIGHTCUR=82;
    public static final int RIGHTP=83;
    public static final int RIGHTSBR=84;
    public static final int RULE=85;
    public static final int SAVE_LC=86;
    public static final int SAVE_UC=87;
    public static final int SBOL=88;
    public static final int SEMIC=89;
    public static final int SIZE=90;
    public static final int SIZEOF_LC=91;
    public static final int SIZEOF_UC=92;
    public static final int STORE_LC=93;
    public static final int STORE_UC=94;
    public static final int STRICT=95;
    public static final int STRING=96;
    public static final int TRUE_LC=97;
    public static final int TRUE_UC=98;
    public static final int TXT=99;
    public static final int TYPE=100;
    public static final int UC_AND=101;
    public static final int UC_ELSE=102;
    public static final int UC_ELSEIF=103;
    public static final int UC_FOR=104;
    public static final int UC_FORALL=105;
    public static final int UC_IF=106;
    public static final int UC_IMPORT=107;
    public static final int UC_INCLUDE=108;
    public static final int UC_INDUCES=109;
    public static final int UC_NOT=110;
    public static final int UC_ON=111;
    public static final int UC_OR=112;
    public static final int UC_REPRESSES=113;
    public static final int UC_WHILE=114;
    public static final int UNDERS=115;
    public static final int WS=116;

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:710:1: prog : ( statement[false] )+ EOF !;
    public final EugeneParser.prog_return prog() throws RecognitionException {
        EugeneParser.prog_return retval = new EugeneParser.prog_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF2=null;
        EugeneParser.statement_return statement1 =null;


        Object EOF2_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:716:2: ( ( statement[false] )+ EOF !)
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:716:4: ( statement[false] )+ EOF !
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:716:4: ( statement[false] )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ARRAY||(LA1_0 >= ASSERT && LA1_0 <= COLLECTION)||LA1_0==DEVICE||(LA1_0 >= EXIT_LC && LA1_0 <= EXIT_UC)||LA1_0==GRAMMAR||(LA1_0 >= HASHMARK && LA1_0 <= ID)||LA1_0==INTERACTION||(LA1_0 >= LC_FOR && LA1_0 <= LC_INCLUDE)||LA1_0==LC_PIGEON||LA1_0==LC_WHILE||(LA1_0 >= NOTE && LA1_0 <= NUM)||(LA1_0 >= PART && LA1_0 <= PIGEON)||(LA1_0 >= PRINT && LA1_0 <= RANDOM_UC)||(LA1_0 >= RULE && LA1_0 <= SEMIC)||(LA1_0 >= SIZEOF_LC && LA1_0 <= STORE_UC)||(LA1_0 >= TXT && LA1_0 <= TYPE)||(LA1_0 >= UC_FOR && LA1_0 <= UC_INCLUDE)||LA1_0==UC_WHILE) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:716:5: statement[false]
            	    {
            	    pushFollow(FOLLOW_statement_in_prog936);
            	    statement1=statement(false);

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement1.getTree());

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


            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_prog941); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:720:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC );
    public final EugeneParser.statement_return statement(boolean defer) throws RecognitionException {
        EugeneParser.statement_return retval = new EugeneParser.statement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMIC4=null;
        Token SEMIC6=null;
        Token SEMIC8=null;
        Token SEMIC10=null;
        Token SEMIC12=null;
        Token SEMIC13=null;
        Token SEMIC16=null;
        EugeneParser.dataExchange_return de =null;

        EugeneParser.includeStatement_return includeStatement3 =null;

        EugeneParser.declarationStatement_return declarationStatement5 =null;

        EugeneParser.printStatement_return printStatement7 =null;

        EugeneParser.assignment_return assignment9 =null;

        EugeneParser.functionCall_return functionCall11 =null;

        EugeneParser.imperativeStatements_return imperativeStatements14 =null;

        EugeneParser.predefined_statements_return predefined_statements15 =null;


        Object SEMIC4_tree=null;
        Object SEMIC6_tree=null;
        Object SEMIC8_tree=null;
        Object SEMIC10_tree=null;
        Object SEMIC12_tree=null;
        Object SEMIC13_tree=null;
        Object SEMIC16_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:722:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC )
            int alt3=8;
            switch ( input.LA(1) ) {
            case HASHMARK:
            case LC_INCLUDE:
            case UC_INCLUDE:
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
            case PRINT:
            case PRINTLN:
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
            case LC_IMPORT:
            case LC_PIGEON:
            case PIGEON:
            case SBOL:
            case UC_IMPORT:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:723:3: includeStatement[defer] ( SEMIC )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_includeStatement_in_statement967);
                    includeStatement3=includeStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, includeStatement3.getTree());

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:723:27: ( SEMIC )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==SEMIC) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:723:28: SEMIC
                            {
                            SEMIC4=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement971); 
                            SEMIC4_tree = 
                            (Object)adaptor.create(SEMIC4)
                            ;
                            adaptor.addChild(root_0, SEMIC4_tree);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:724:4: declarationStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declarationStatement_in_statement978);
                    declarationStatement5=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, declarationStatement5.getTree());

                    SEMIC6=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement981); 
                    SEMIC6_tree = 
                    (Object)adaptor.create(SEMIC6)
                    ;
                    adaptor.addChild(root_0, SEMIC6_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:725:4: printStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_printStatement_in_statement987);
                    printStatement7=printStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, printStatement7.getTree());

                    SEMIC8=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement990); 
                    SEMIC8_tree = 
                    (Object)adaptor.create(SEMIC8)
                    ;
                    adaptor.addChild(root_0, SEMIC8_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:726:4: assignment[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_statement995);
                    assignment9=assignment(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, assignment9.getTree());

                    SEMIC10=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement998); 
                    SEMIC10_tree = 
                    (Object)adaptor.create(SEMIC10)
                    ;
                    adaptor.addChild(root_0, SEMIC10_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:727:4: functionCall[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_statement1003);
                    functionCall11=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, functionCall11.getTree());

                    SEMIC12=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1006); 
                    SEMIC12_tree = 
                    (Object)adaptor.create(SEMIC12)
                    ;
                    adaptor.addChild(root_0, SEMIC12_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:728:4: de= dataExchange[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_statement1013);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());

                    SEMIC13=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1016); 
                    SEMIC13_tree = 
                    (Object)adaptor.create(SEMIC13)
                    ;
                    adaptor.addChild(root_0, SEMIC13_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:742:4: imperativeStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_imperativeStatements_in_statement1023);
                    imperativeStatements14=imperativeStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, imperativeStatements14.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:743:4: predefined_statements[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_predefined_statements_in_statement1029);
                    predefined_statements15=predefined_statements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, predefined_statements15.getTree());

                    SEMIC16=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement1032); 
                    SEMIC16_tree = 
                    (Object)adaptor.create(SEMIC16)
                    ;
                    adaptor.addChild(root_0, SEMIC16_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:746:1: predefined_statements[boolean defer] : ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) | built_in_function[defer] );
    public final EugeneParser.predefined_statements_return predefined_statements(boolean defer) throws RecognitionException {
        EugeneParser.predefined_statements_return retval = new EugeneParser.predefined_statements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set18=null;
        EugeneParser.testStatements_return testStatements17 =null;

        EugeneParser.built_in_function_return built_in_function19 =null;


        Object set18_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:747:2: ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) | built_in_function[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:747:4: testStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_testStatements_in_predefined_statements1045);
                    testStatements17=testStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, testStatements17.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:748:4: ( EXIT_LC | EXIT_UC )
                    {
                    root_0 = (Object)adaptor.nil();


                    set18=(Token)input.LT(1);

                    if ( (input.LA(1) >= EXIT_LC && input.LA(1) <= EXIT_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set18)
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:753:4: built_in_function[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_built_in_function_in_predefined_statements1064);
                    built_in_function19=built_in_function(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, built_in_function19.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:757:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] );
    public final EugeneParser.declarationStatement_return declarationStatement(boolean defer) throws RecognitionException {
        EugeneParser.declarationStatement_return retval = new EugeneParser.declarationStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.variableDeclaration_return v =null;

        EugeneParser.containerDeclaration_return containerDeclaration20 =null;

        EugeneParser.propertyDeclaration_return propertyDeclaration21 =null;

        EugeneParser.typeDeclaration_return typeDeclaration22 =null;

        EugeneParser.instantiation_return instantiation23 =null;

        EugeneParser.interactionDeclaration_return interactionDeclaration24 =null;

        EugeneParser.ruleDeclaration_return ruleDeclaration25 =null;

        EugeneParser.grammarDeclaration_return grammarDeclaration26 =null;

        EugeneParser.deviceDeclaration_return deviceDeclaration27 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:759:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:759:4: v= variableDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_variableDeclaration_in_declarationStatement1085);
                    v=variableDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, v.getTree());


                    if(!defer) {
                        retval.name = (v!=null?v.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:764:4: containerDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_containerDeclaration_in_declarationStatement1093);
                    containerDeclaration20=containerDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, containerDeclaration20.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:765:4: propertyDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement1099);
                    propertyDeclaration21=propertyDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, propertyDeclaration21.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:766:4: typeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeDeclaration_in_declarationStatement1105);
                    typeDeclaration22=typeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, typeDeclaration22.getTree());

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:767:4: instantiation[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_instantiation_in_declarationStatement1111);
                    instantiation23=instantiation(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, instantiation23.getTree());

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:768:4: interactionDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement1117);
                    interactionDeclaration24=interactionDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, interactionDeclaration24.getTree());

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:769:4: ruleDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement1123);
                    ruleDeclaration25=ruleDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ruleDeclaration25.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:770:4: grammarDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_grammarDeclaration_in_declarationStatement1129);
                    grammarDeclaration26=grammarDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, grammarDeclaration26.getTree());

                    }
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:771:4: deviceDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement1135);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:774:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] );
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
        EugeneParser.numdecl_return n =null;

        EugeneParser.txtdecl_return t =null;

        EugeneParser.txtlistdecl_return tl =null;

        EugeneParser.numlistdecl_return nl =null;

        EugeneParser.booldecl_return b =null;


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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:776:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:776:4: NUM n= numdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM28=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1153); 
                    NUM28_tree = 
                    (Object)adaptor.create(NUM28)
                    ;
                    adaptor.addChild(root_0, NUM28_tree);


                    pushFollow(FOLLOW_numdecl_in_variableDeclaration1157);
                    n=numdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, n.getTree());


                    if(!defer) {
                        retval.varname = (n!=null?n.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:781:4: TXT t= txtdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT29=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1168); 
                    TXT29_tree = 
                    (Object)adaptor.create(TXT29)
                    ;
                    adaptor.addChild(root_0, TXT29_tree);


                    pushFollow(FOLLOW_txtdecl_in_variableDeclaration1172);
                    t=txtdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t.getTree());


                    if(!defer) {
                        retval.varname = (t!=null?t.varname:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:786:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT30=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1183); 
                    TXT30_tree = 
                    (Object)adaptor.create(TXT30)
                    ;
                    adaptor.addChild(root_0, TXT30_tree);


                    LEFTSBR31=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1185); 
                    LEFTSBR31_tree = 
                    (Object)adaptor.create(LEFTSBR31)
                    ;
                    adaptor.addChild(root_0, LEFTSBR31_tree);


                    RIGHTSBR32=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1187); 
                    RIGHTSBR32_tree = 
                    (Object)adaptor.create(RIGHTSBR32)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR32_tree);


                    pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration1191);
                    tl=txtlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tl.getTree());


                    if(!defer) {
                        retval.varname = (tl!=null?tl.varname:null);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:791:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM33=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1202); 
                    NUM33_tree = 
                    (Object)adaptor.create(NUM33)
                    ;
                    adaptor.addChild(root_0, NUM33_tree);


                    LEFTSBR34=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1204); 
                    LEFTSBR34_tree = 
                    (Object)adaptor.create(LEFTSBR34)
                    ;
                    adaptor.addChild(root_0, LEFTSBR34_tree);


                    RIGHTSBR35=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1206); 
                    RIGHTSBR35_tree = 
                    (Object)adaptor.create(RIGHTSBR35)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR35_tree);


                    pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1210);
                    nl=numlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, nl.getTree());


                    if(!defer) {
                        retval.varname = (nl!=null?nl.varname:null);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:796:4: ( BOOLEAN | BOOL ) b= booldecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    set36=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set36)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_booldecl_in_variableDeclaration1229);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:803:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
    public final EugeneParser.numdecl_return numdecl(boolean defer) throws RecognitionException {
        EugeneParser.numdecl_return retval = new EugeneParser.numdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID37=null;
        Token COMMA38=null;
        Token ID40=null;
        Token EQUALS41=null;
        Token COMMA42=null;
        EugeneParser.expr_return ex =null;

        EugeneParser.numdecl_return numdecl39 =null;

        EugeneParser.numdecl_return numdecl43 =null;


        Object ID37_tree=null;
        Object COMMA38_tree=null;
        Object ID40_tree=null;
        Object EQUALS41_tree=null;
        Object COMMA42_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:805:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:805:4: ID ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID37=(Token)match(input,ID,FOLLOW_ID_in_numdecl1252); 
                    ID37_tree = 
                    (Object)adaptor.create(ID37)
                    ;
                    adaptor.addChild(root_0, ID37_tree);



                    if(!defer) {
                        declareVariableNoValue((ID37!=null?ID37.getText():null), EugeneConstants.NUM);
                        retval.varname = (ID37!=null?ID37.getText():null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:810:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:810:6: COMMA numdecl[defer]
                            {
                            COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1258); 
                            COMMA38_tree = 
                            (Object)adaptor.create(COMMA38)
                            ;
                            adaptor.addChild(root_0, COMMA38_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1260);
                            numdecl39=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl39.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:811:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID40=(Token)match(input,ID,FOLLOW_ID_in_numdecl1268); 
                    ID40_tree = 
                    (Object)adaptor.create(ID40)
                    ;
                    adaptor.addChild(root_0, ID40_tree);


                    EQUALS41=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1270); 
                    EQUALS41_tree = 
                    (Object)adaptor.create(EQUALS41)
                    ;
                    adaptor.addChild(root_0, EQUALS41_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:811:14: (ex= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:811:15: ex= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_numdecl1275);
                    ex=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ex.getTree());

                    }



                    if(!defer) {
                        declareVariableWithValueNum((ID40!=null?ID40.getText():null), (ex!=null?ex.p:null), (ex!=null?ex.index:0));
                        retval.varname = (ID40!=null?ID40.getText():null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:816:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:816:6: COMMA numdecl[defer]
                            {
                            COMMA42=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1283); 
                            COMMA42_tree = 
                            (Object)adaptor.create(COMMA42)
                            ;
                            adaptor.addChild(root_0, COMMA42_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1285);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "txtdecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:819:1: txtdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? );
    public final EugeneParser.txtdecl_return txtdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtdecl_return retval = new EugeneParser.txtdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID44=null;
        Token COMMA45=null;
        Token EQUALS47=null;
        Token COMMA48=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtdecl_return txtdecl46 =null;

        EugeneParser.txtdecl_return txtdecl49 =null;


        Object var_tree=null;
        Object ID44_tree=null;
        Object COMMA45_tree=null;
        Object EQUALS47_tree=null;
        Object COMMA48_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:821:2: ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:821:4: ID ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID44=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1305); 
                    ID44_tree = 
                    (Object)adaptor.create(ID44)
                    ;
                    adaptor.addChild(root_0, ID44_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID44!=null?ID44.getText():null), EugeneConstants.TXT);
                    			retval.varname = (ID44!=null?ID44.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:827:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:827:6: COMMA txtdecl[defer]
                            {
                            COMMA45=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1312); 
                            COMMA45_tree = 
                            (Object)adaptor.create(COMMA45)
                            ;
                            adaptor.addChild(root_0, COMMA45_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1314);
                            txtdecl46=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl46.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:829:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1325); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS47=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1327); 
                    EQUALS47_tree = 
                    (Object)adaptor.create(EQUALS47)
                    ;
                    adaptor.addChild(root_0, EQUALS47_tree);


                    pushFollow(FOLLOW_expr_in_txtdecl1331);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxt((var!=null?var.getText():null), (let!=null?let.p:null), (let!=null?let.index:0));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:835:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:835:6: COMMA txtdecl[defer]
                            {
                            COMMA48=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1339); 
                            COMMA48_tree = 
                            (Object)adaptor.create(COMMA48)
                            ;
                            adaptor.addChild(root_0, COMMA48_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1341);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "txtlistdecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:838:1: txtlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? );
    public final EugeneParser.txtlistdecl_return txtlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtlistdecl_return retval = new EugeneParser.txtlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID50=null;
        Token COMMA51=null;
        Token EQUALS53=null;
        Token COMMA54=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtlistdecl_return txtlistdecl52 =null;

        EugeneParser.txtlistdecl_return txtlistdecl55 =null;


        Object var_tree=null;
        Object ID50_tree=null;
        Object COMMA51_tree=null;
        Object EQUALS53_tree=null;
        Object COMMA54_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:840:2: ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:840:4: ID ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID50=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1361); 
                    ID50_tree = 
                    (Object)adaptor.create(ID50)
                    ;
                    adaptor.addChild(root_0, ID50_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID50!=null?ID50.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID50!=null?ID50.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:846:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:846:6: COMMA txtlistdecl[defer]
                            {
                            COMMA51=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1368); 
                            COMMA51_tree = 
                            (Object)adaptor.create(COMMA51)
                            ;
                            adaptor.addChild(root_0, COMMA51_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1370);
                            txtlistdecl52=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl52.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:847:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1380); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS53=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1382); 
                    EQUALS53_tree = 
                    (Object)adaptor.create(EQUALS53)
                    ;
                    adaptor.addChild(root_0, EQUALS53_tree);


                    typeList = EugeneConstants.TXT;

                    pushFollow(FOLLOW_expr_in_txtlistdecl1388);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxtList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:853:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:853:6: COMMA txtlistdecl[defer]
                            {
                            COMMA54=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1396); 
                            COMMA54_tree = 
                            (Object)adaptor.create(COMMA54)
                            ;
                            adaptor.addChild(root_0, COMMA54_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1398);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "numlistdecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:856:1: numlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? );
    public final EugeneParser.numlistdecl_return numlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.numlistdecl_return retval = new EugeneParser.numlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID56=null;
        Token COMMA57=null;
        Token EQUALS59=null;
        Token COMMA60=null;
        EugeneParser.expr_return let =null;

        EugeneParser.numlistdecl_return numlistdecl58 =null;

        EugeneParser.numlistdecl_return numlistdecl61 =null;


        Object var_tree=null;
        Object ID56_tree=null;
        Object COMMA57_tree=null;
        Object EQUALS59_tree=null;
        Object COMMA60_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:858:2: ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:858:4: ID ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID56=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1418); 
                    ID56_tree = 
                    (Object)adaptor.create(ID56)
                    ;
                    adaptor.addChild(root_0, ID56_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID56!=null?ID56.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID56!=null?ID56.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:864:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:864:6: COMMA numlistdecl[defer]
                            {
                            COMMA57=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1425); 
                            COMMA57_tree = 
                            (Object)adaptor.create(COMMA57)
                            ;
                            adaptor.addChild(root_0, COMMA57_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1427);
                            numlistdecl58=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl58.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:865:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1437); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS59=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1439); 
                    EQUALS59_tree = 
                    (Object)adaptor.create(EQUALS59)
                    ;
                    adaptor.addChild(root_0, EQUALS59_tree);


                     typeList = EugeneConstants.NUM;

                    pushFollow(FOLLOW_expr_in_numlistdecl1444);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueNumList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:871:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:871:6: COMMA numlistdecl[defer]
                            {
                            COMMA60=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1452); 
                            COMMA60_tree = 
                            (Object)adaptor.create(COMMA60)
                            ;
                            adaptor.addChild(root_0, COMMA60_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1454);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "booldecl"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:874:1: booldecl[boolean defer] returns [String varname] : ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] );
    public final EugeneParser.booldecl_return booldecl(boolean defer) throws RecognitionException {
        EugeneParser.booldecl_return retval = new EugeneParser.booldecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID62=null;
        Token COMMA63=null;
        Token EQUALS65=null;
        EugeneParser.expr_return let =null;

        EugeneParser.booldecl_return booldecl64 =null;


        Object var_tree=null;
        Object ID62_tree=null;
        Object COMMA63_tree=null;
        Object EQUALS65_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:876:2: ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:876:4: ID ( COMMA booldecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID62=(Token)match(input,ID,FOLLOW_ID_in_booldecl1474); 
                    ID62_tree = 
                    (Object)adaptor.create(ID62)
                    ;
                    adaptor.addChild(root_0, ID62_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID62!=null?ID62.getText():null), EugeneConstants.BOOLEAN);
                    			retval.varname = (ID62!=null?ID62.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:882:5: ( COMMA booldecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:882:6: COMMA booldecl[defer]
                            {
                            COMMA63=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1481); 
                            COMMA63_tree = 
                            (Object)adaptor.create(COMMA63)
                            ;
                            adaptor.addChild(root_0, COMMA63_tree);


                            pushFollow(FOLLOW_booldecl_in_booldecl1483);
                            booldecl64=booldecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, booldecl64.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:883:4: var= ID EQUALS let= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1493); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS65=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1495); 
                    EQUALS65_tree = 
                    (Object)adaptor.create(EQUALS65)
                    ;
                    adaptor.addChild(root_0, EQUALS65_tree);


                    pushFollow(FOLLOW_expr_in_booldecl1499);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:892:1: propertyDeclaration[boolean defer] : PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP ;
    public final EugeneParser.propertyDeclaration_return propertyDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.propertyDeclaration_return retval = new EugeneParser.propertyDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token PROPERTY66=null;
        Token LEFTP67=null;
        Token RIGHTP68=null;
        EugeneParser.propertyType_return typeToken =null;


        Object nameToken_tree=null;
        Object PROPERTY66_tree=null;
        Object LEFTP67_tree=null;
        Object RIGHTP68_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            PROPERTY66=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1517); 
            PROPERTY66_tree = 
            (Object)adaptor.create(PROPERTY66)
            ;
            adaptor.addChild(root_0, PROPERTY66_tree);


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1521); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            LEFTP67=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1523); 
            LEFTP67_tree = 
            (Object)adaptor.create(LEFTP67)
            ;
            adaptor.addChild(root_0, LEFTP67_tree);


            pushFollow(FOLLOW_propertyType_in_propertyDeclaration1527);
            typeToken=propertyType();

            state._fsp--;

            adaptor.addChild(root_0, typeToken.getTree());

            RIGHTP68=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1529); 
            RIGHTP68_tree = 
            (Object)adaptor.create(RIGHTP68)
            ;
            adaptor.addChild(root_0, RIGHTP68_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:906:1: propertyType returns [String type] : ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:2: ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | ( BOOLEAN | BOOL ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:4: TXT
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT69=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1548); 
                    TXT69_tree = 
                    (Object)adaptor.create(TXT69)
                    ;
                    adaptor.addChild(root_0, TXT69_tree);



                    retval.type = EugeneConstants.TXT;	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:911:4: TXT LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT70=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1555); 
                    TXT70_tree = 
                    (Object)adaptor.create(TXT70)
                    ;
                    adaptor.addChild(root_0, TXT70_tree);


                    LEFTSBR71=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1557); 
                    LEFTSBR71_tree = 
                    (Object)adaptor.create(LEFTSBR71)
                    ;
                    adaptor.addChild(root_0, LEFTSBR71_tree);


                    RIGHTSBR72=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1559); 
                    RIGHTSBR72_tree = 
                    (Object)adaptor.create(RIGHTSBR72)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR72_tree);



                    retval.type = EugeneConstants.TXTLIST;	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:914:4: NUM
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM73=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1566); 
                    NUM73_tree = 
                    (Object)adaptor.create(NUM73)
                    ;
                    adaptor.addChild(root_0, NUM73_tree);



                    retval.type = EugeneConstants.NUM;	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:917:4: NUM LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM74=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1574); 
                    NUM74_tree = 
                    (Object)adaptor.create(NUM74)
                    ;
                    adaptor.addChild(root_0, NUM74_tree);


                    LEFTSBR75=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1576); 
                    LEFTSBR75_tree = 
                    (Object)adaptor.create(LEFTSBR75)
                    ;
                    adaptor.addChild(root_0, LEFTSBR75_tree);


                    RIGHTSBR76=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1578); 
                    RIGHTSBR76_tree = 
                    (Object)adaptor.create(RIGHTSBR76)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR76_tree);



                    retval.type = EugeneConstants.NUMLIST;	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:920:4: ( BOOLEAN | BOOL )
                    {
                    root_0 = (Object)adaptor.nil();


                    set77=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set77)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:929:1: typeDeclaration[boolean defer] : ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? );
    public final EugeneParser.typeDeclaration_return typeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.typeDeclaration_return retval = new EugeneParser.typeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token TYPE79=null;
        Token LEFTP80=null;
        Token RIGHTP81=null;
        EugeneParser.listOfIDs_return lstToken =null;

        EugeneParser.partTypeDeclaration_return partTypeDeclaration78 =null;


        Object nameToken_tree=null;
        Object TYPE79_tree=null;
        Object LEFTP80_tree=null;
        Object RIGHTP81_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:930:2: ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:930:4: partTypeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1607);
                    partTypeDeclaration78=partTypeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, partTypeDeclaration78.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:4: ( TYPE )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:5: TYPE
                    {
                    TYPE79=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1614); 
                    TYPE79_tree = 
                    (Object)adaptor.create(TYPE79)
                    ;
                    adaptor.addChild(root_0, TYPE79_tree);


                    }


                    nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1619); 
                    nameToken_tree = 
                    (Object)adaptor.create(nameToken)
                    ;
                    adaptor.addChild(root_0, nameToken_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:24: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==LEFTP) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:25: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                            {
                            LEFTP80=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1622); 
                            LEFTP80_tree = 
                            (Object)adaptor.create(LEFTP80)
                            ;
                            adaptor.addChild(root_0, LEFTP80_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:31: (lstToken= listOfIDs[defer] )?
                            int alt22=2;
                            int LA22_0 = input.LA(1);

                            if ( (LA22_0==ID) ) {
                                alt22=1;
                            }
                            switch (alt22) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:32: lstToken= listOfIDs[defer]
                                    {
                                    pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1627);
                                    lstToken=listOfIDs(defer);

                                    state._fsp--;

                                    adaptor.addChild(root_0, lstToken.getTree());

                                    }
                                    break;

                            }


                            RIGHTP81=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1632); 
                            RIGHTP81_tree = 
                            (Object)adaptor.create(RIGHTP81)
                            ;
                            adaptor.addChild(root_0, RIGHTP81_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:944:1: partTypeDeclaration[boolean defer] : ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? ;
    public final EugeneParser.partTypeDeclaration_return partTypeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.partTypeDeclaration_return retval = new EugeneParser.partTypeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token set82=null;
        Token LEFTP83=null;
        Token RIGHTP84=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object nameToken_tree=null;
        Object set82_tree=null;
        Object LEFTP83_tree=null;
        Object RIGHTP84_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            set82=(Token)input.LT(1);

            if ( (input.LA(1) >= PART && input.LA(1) <= PART_TYPE) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set82)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1660); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:35: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==LEFTP) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:36: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                    {
                    LEFTP83=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1663); 
                    LEFTP83_tree = 
                    (Object)adaptor.create(LEFTP83)
                    ;
                    adaptor.addChild(root_0, LEFTP83_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:42: (lstToken= listOfIDs[defer] )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==ID) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:43: lstToken= listOfIDs[defer]
                            {
                            pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1668);
                            lstToken=listOfIDs(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lstToken.getTree());

                            }
                            break;

                    }


                    RIGHTP84=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1673); 
                    RIGHTP84_tree = 
                    (Object)adaptor.create(RIGHTP84)
                    ;
                    adaptor.addChild(root_0, RIGHTP84_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:961:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
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
        EugeneParser.list_of_declarations_return list_of_declarations88 =null;


        Object c_tree=null;
        Object a_tree=null;
        Object name_tree=null;
        Object LEFTSBR85_tree=null;
        Object RIGHTSBR86_tree=null;
        Object LEFTP87_tree=null;
        Object RIGHTP89_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:2: ( (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:4: (c= COLLECTION | (a= ARRAY ( LEFTSBR RIGHTSBR )? ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:5: c= COLLECTION
                    {
                    c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1700); 
                    c_tree = 
                    (Object)adaptor.create(c)
                    ;
                    adaptor.addChild(root_0, c_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:20: (a= ARRAY ( LEFTSBR RIGHTSBR )? )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:21: a= ARRAY ( LEFTSBR RIGHTSBR )?
                    {
                    a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1707); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:29: ( LEFTSBR RIGHTSBR )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==LEFTSBR) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:30: LEFTSBR RIGHTSBR
                            {
                            LEFTSBR85=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1710); 
                            LEFTSBR85_tree = 
                            (Object)adaptor.create(LEFTSBR85)
                            ;
                            adaptor.addChild(root_0, LEFTSBR85_tree);


                            RIGHTSBR86=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1712); 
                            RIGHTSBR86_tree = 
                            (Object)adaptor.create(RIGHTSBR86)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR86_tree);


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1720); 
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:977:4: ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==LEFTP) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:977:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
                    {
                    LEFTP87=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1725); 
                    LEFTP87_tree = 
                    (Object)adaptor.create(LEFTP87)
                    ;
                    adaptor.addChild(root_0, LEFTP87_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:977:11: ( list_of_declarations[defer] )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==ARRAY||(LA29_0 >= BOOL && LA29_0 <= COLLECTION)||LA29_0==DEVICE||LA29_0==DOLLAR||(LA29_0 >= FALSE_LC && LA29_0 <= FALSE_UC)||LA29_0==GRAMMAR||LA29_0==ID||LA29_0==INTERACTION||(LA29_0 >= LEFTP && LA29_0 <= LEFTSBR)||LA29_0==MINUS||(LA29_0 >= NUM && LA29_0 <= PART_TYPE)||(LA29_0 >= PROPERTY && LA29_0 <= REAL)||(LA29_0 >= RULE && LA29_0 <= SAVE_UC)||(LA29_0 >= SIZEOF_LC && LA29_0 <= STORE_UC)||(LA29_0 >= STRING && LA29_0 <= TYPE)) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:977:12: list_of_declarations[defer]
                            {
                            pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1728);
                            list_of_declarations88=list_of_declarations(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, list_of_declarations88.getTree());

                            }
                            break;

                    }


                    RIGHTP89=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1733); 
                    RIGHTP89_tree = 
                    (Object)adaptor.create(RIGHTP89)
                    ;
                    adaptor.addChild(root_0, RIGHTP89_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:992:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
    public final EugeneParser.list_of_declarations_return list_of_declarations(boolean defer) throws RecognitionException {
        EugeneParser.list_of_declarations_return retval = new EugeneParser.list_of_declarations_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA90=null;
        EugeneParser.declarationStatement_return ds =null;

        EugeneParser.expr_return exp =null;

        EugeneParser.list_of_declarations_return lod =null;


        Object COMMA90_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:2: ( (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:4: (ds= declarationStatement[defer] |exp= expr[defer] ) ( COMMA lod= list_of_declarations[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:4: (ds= declarationStatement[defer] |exp= expr[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:6: ds= declarationStatement[defer]
                    {
                    pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1766);
                    ds=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ds.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:39: exp= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_list_of_declarations1773);
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1008:5: ( COMMA lod= list_of_declarations[defer] )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==COMMA) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1008:7: COMMA lod= list_of_declarations[defer]
                    {
                    COMMA90=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1781); 
                    COMMA90_tree = 
                    (Object)adaptor.create(COMMA90)
                    ;
                    adaptor.addChild(root_0, COMMA90_tree);


                    pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations1785);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1014:1: instantiation[boolean defer] : t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
    public final EugeneParser.instantiation_return instantiation(boolean defer) throws RecognitionException {
        EugeneParser.instantiation_return retval = new EugeneParser.instantiation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token t=null;
        Token LEFTP91=null;
        Token RIGHTP92=null;
        EugeneParser.dynamic_naming_return n =null;

        EugeneParser.listOfDotValues_return dotToken =null;

        EugeneParser.listOfValues_return valueToken =null;


        Object t_tree=null;
        Object LEFTP91_tree=null;
        Object RIGHTP92_tree=null;


        NamedElement type = null;
        String instance_name = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:2: (t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1019:4: t= ID n= dynamic_naming[defer] ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            t=(Token)match(input,ID,FOLLOW_ID_in_instantiation1813); 
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
            	

            pushFollow(FOLLOW_dynamic_naming_in_instantiation1819);
            n=dynamic_naming(defer);

            state._fsp--;

            adaptor.addChild(root_0, n.getTree());


            if(!defer) {
                instance_name = (n!=null?n.name:null);	
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1039:4: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==LEFTP) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1039:6: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
                    {
                    LEFTP91=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation1826); 
                    LEFTP91_tree = 
                    (Object)adaptor.create(LEFTP91)
                    ;
                    adaptor.addChild(root_0, LEFTP91_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1039:12: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1039:13: dotToken= listOfDotValues[defer]
                            {
                            pushFollow(FOLLOW_listOfDotValues_in_instantiation1831);
                            dotToken=listOfDotValues(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dotToken.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1039:45: valueToken= listOfValues[defer, (ComponentType)type]
                            {
                            pushFollow(FOLLOW_listOfValues_in_instantiation1836);
                            valueToken=listOfValues(defer, (ComponentType)type);

                            state._fsp--;

                            adaptor.addChild(root_0, valueToken.getTree());

                            }
                            break;

                    }


                    RIGHTP92=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation1841); 
                    RIGHTP92_tree = 
                    (Object)adaptor.create(RIGHTP92)
                    ;
                    adaptor.addChild(root_0, RIGHTP92_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1073:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
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
        EugeneParser.expr_return v1 =null;

        EugeneParser.expr_return v2 =null;


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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1074:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1074:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            {
            root_0 = (Object)adaptor.nil();


            DOT93=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1864); 
            DOT93_tree = 
            (Object)adaptor.create(DOT93)
            ;
            adaptor.addChild(root_0, DOT93_tree);


            prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1868); 
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
            		

            LEFTP94=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1874); 
            LEFTP94_tree = 
            (Object)adaptor.create(LEFTP94)
            ;
            adaptor.addChild(root_0, LEFTP94_tree);


            pushFollow(FOLLOW_expr_in_listOfDotValues1878);
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
            			

            RIGHTP95=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1886); 
            RIGHTP95_tree = 
            (Object)adaptor.create(RIGHTP95)
            ;
            adaptor.addChild(root_0, RIGHTP95_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1092:13: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1092:14: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
            	    {
            	    COMMA96=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues1889); 
            	    COMMA96_tree = 
            	    (Object)adaptor.create(COMMA96)
            	    ;
            	    adaptor.addChild(root_0, COMMA96_tree);


            	    DOT97=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1891); 
            	    DOT97_tree = 
            	    (Object)adaptor.create(DOT97)
            	    ;
            	    adaptor.addChild(root_0, DOT97_tree);


            	    p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1895); 
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
            	    				

            	    LEFTP98=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1903); 
            	    LEFTP98_tree = 
            	    (Object)adaptor.create(LEFTP98)
            	    ;
            	    adaptor.addChild(root_0, LEFTP98_tree);


            	    pushFollow(FOLLOW_expr_in_listOfDotValues1907);
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
            	    					

            	    RIGHTP99=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1917); 
            	    RIGHTP99_tree = 
            	    (Object)adaptor.create(RIGHTP99)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP99_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1113:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
    public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
        EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA100=null;
        EugeneParser.expr_return val1 =null;

        EugeneParser.expr_return val2 =null;


        Object COMMA100_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1114:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1115:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
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
            		

            pushFollow(FOLLOW_expr_in_listOfValues1938);
            val1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, val1.getTree());


            if(!defer) {
                propertyValuesHolder.add((val1!=null?val1.p:null));
            }				
            			

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1139:6: ( COMMA val2= expr[defer] )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==COMMA) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1139:7: COMMA val2= expr[defer]
            	    {
            	    COMMA100=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues1944); 
            	    COMMA100_tree = 
            	    (Object)adaptor.create(COMMA100)
            	    ;
            	    adaptor.addChild(root_0, COMMA100_tree);



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
            	                   

            	    pushFollow(FOLLOW_expr_in_listOfValues1950);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1169:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
    public final EugeneParser.deviceDeclaration_return deviceDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.deviceDeclaration_return retval = new EugeneParser.deviceDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token DEVICE101=null;
        Token LEFTP102=null;
        Token RIGHTP103=null;
        EugeneParser.deviceComponents_return dcs =null;


        Object n_tree=null;
        Object DEVICE101_tree=null;
        Object LEFTP102_tree=null;
        Object RIGHTP103_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1170:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1170:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            DEVICE101=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration1969); 
            DEVICE101_tree = 
            (Object)adaptor.create(DEVICE101)
            ;
            adaptor.addChild(root_0, DEVICE101_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration1973); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1170:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LEFTP) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1170:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
                    {
                    LEFTP102=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration1976); 
                    LEFTP102_tree = 
                    (Object)adaptor.create(LEFTP102)
                    ;
                    adaptor.addChild(root_0, LEFTP102_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1170:23: (dcs= deviceComponents[defer] )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==ID||LA37_0==LEFTSBR||LA37_0==MINUS||LA37_0==PLUS) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1170:24: dcs= deviceComponents[defer]
                            {
                            pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration1981);
                            dcs=deviceComponents(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dcs.getTree());

                            }
                            break;

                    }


                    RIGHTP103=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration1986); 
                    RIGHTP103_tree = 
                    (Object)adaptor.create(RIGHTP103)
                    ;
                    adaptor.addChild(root_0, RIGHTP103_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1184:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
    public final EugeneParser.deviceComponents_return deviceComponents(boolean defer) throws RecognitionException {
        EugeneParser.deviceComponents_return retval = new EugeneParser.deviceComponents_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal104=null;
        EugeneParser.selection_return s =null;

        EugeneParser.deviceComponents_return dcs =null;


        Object char_literal104_tree=null;


        retval.lstComponents = new ArrayList<List<NamedElement>>();
        retval.lstOrientations = new ArrayList<List<Orientation>>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1190:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1190:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selection_in_deviceComponents2017);
            s=selection(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());


            if(!defer) {
                retval.lstComponents.add((s!=null?s.components:null));
                retval.lstOrientations.add((s!=null?s.orientations:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1195:4: ( ',' dcs= deviceComponents[defer] )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==COMMA) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1195:5: ',' dcs= deviceComponents[defer]
                    {
                    char_literal104=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents2023); 
                    char_literal104_tree = 
                    (Object)adaptor.create(char_literal104)
                    ;
                    adaptor.addChild(root_0, char_literal104_tree);


                    pushFollow(FOLLOW_deviceComponents_in_deviceComponents2027);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1203:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
    public final EugeneParser.selection_return selection(boolean defer) throws RecognitionException {
        EugeneParser.selection_return retval = new EugeneParser.selection_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTSBR105=null;
        Token RIGHTSBR106=null;
        EugeneParser.selection_list_return sl =null;

        EugeneParser.device_component_return dc =null;


        Object LEFTSBR105_tree=null;
        Object RIGHTSBR106_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1205:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1205:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR105=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection2051); 
                    LEFTSBR105_tree = 
                    (Object)adaptor.create(LEFTSBR105)
                    ;
                    adaptor.addChild(root_0, LEFTSBR105_tree);


                    pushFollow(FOLLOW_selection_list_in_selection2055);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());

                    RIGHTSBR106=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection2058); 
                    RIGHTSBR106_tree = 
                    (Object)adaptor.create(RIGHTSBR106)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR106_tree);



                    if(!defer) {
                        retval.components = (sl!=null?sl.components:null);
                        retval.orientations = (sl!=null?sl.orientations:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1211:4: dc= device_component[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_device_component_in_selection2067);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1222:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
    public final EugeneParser.selection_list_return selection_list(boolean defer) throws RecognitionException {
        EugeneParser.selection_list_return retval = new EugeneParser.selection_list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIPE107=null;
        EugeneParser.device_component_return dc =null;

        EugeneParser.selection_list_return sl =null;


        Object PIPE107_tree=null;


        retval.components = new ArrayList<NamedElement>();
        retval.orientations = new ArrayList<Orientation>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1228:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1228:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_device_component_in_selection_list2096);
            dc=device_component(defer);

            state._fsp--;

            adaptor.addChild(root_0, dc.getTree());


            if(!defer) {
                retval.components.add((dc!=null?dc.component:null));
                retval.orientations.add((dc!=null?dc.orientation:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1233:4: ( PIPE sl= selection_list[defer] )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==PIPE) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1233:5: PIPE sl= selection_list[defer]
                    {
                    PIPE107=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list2102); 
                    PIPE107_tree = 
                    (Object)adaptor.create(PIPE107)
                    ;
                    adaptor.addChild(root_0, PIPE107_tree);


                    pushFollow(FOLLOW_selection_list_in_selection_list2106);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1241:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
    public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
        EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token directionToken=null;
        Token idToken=null;

        Object directionToken_tree=null;
        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1243:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1243:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1243:4: (directionToken= ( MINUS | PLUS ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==MINUS||LA42_0==PLUS) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1243:5: directionToken= ( MINUS | PLUS )
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


            idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component2142); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1291:1: assignment[boolean defer] : lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
    public final EugeneParser.assignment_return assignment(boolean defer) throws RecognitionException {
        EugeneParser.assignment_return retval = new EugeneParser.assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token a=null;
        Token EQUALS108=null;
        EugeneParser.lhs_assignment_return lhs =null;

        EugeneParser.rhs_assignment_return rhs =null;


        Object a_tree=null;
        Object EQUALS108_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1292:2: (lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1292:4: lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_lhs_assignment_in_assignment2162);
            lhs=lhs_assignment(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            EQUALS108=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment2165); 
            EQUALS108_tree = 
            (Object)adaptor.create(EQUALS108)
            ;
            adaptor.addChild(root_0, EQUALS108_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1292:37: (a= AMP )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==AMP) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1292:38: a= AMP
                    {
                    a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment2170); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    }
                    break;

            }


            pushFollow(FOLLOW_rhs_assignment_in_assignment2176);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:1: lhs_assignment[boolean defer] : ID lhs_access[defer] ;
    public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID109=null;
        EugeneParser.lhs_access_return lhs_access110 =null;


        Object ID109_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1322:2: ( ID lhs_access[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1322:4: ID lhs_access[defer]
            {
            root_0 = (Object)adaptor.nil();


            ID109=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment2191); 
            ID109_tree = 
            (Object)adaptor.create(ID109)
            ;
            adaptor.addChild(root_0, ID109_tree);


            pushFollow(FOLLOW_lhs_access_in_lhs_assignment2193);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lhs_access"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1325:1: lhs_access[boolean defer] : (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] );
    public final EugeneParser.lhs_access_return lhs_access(boolean defer) throws RecognitionException {
        EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token DOT111=null;
        Token LEFTSBR112=null;
        Token set113=null;
        Token RIGHTSBR114=null;
        EugeneParser.lhs_access_return lhs_access115 =null;


        Object i_tree=null;
        Object DOT111_tree=null;
        Object LEFTSBR112_tree=null;
        Object set113_tree=null;
        Object RIGHTSBR114_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1326:2: (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1327:2: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1327:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1327:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1327:5: DOT i= ID
                            {
                            DOT111=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2213); 
                            DOT111_tree = 
                            (Object)adaptor.create(DOT111)
                            ;
                            adaptor.addChild(root_0, DOT111_tree);


                            i=(Token)match(input,ID,FOLLOW_ID_in_lhs_access2217); 
                            i_tree = 
                            (Object)adaptor.create(i)
                            ;
                            adaptor.addChild(root_0, i_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1327:16: LEFTSBR ( ID | NUMBER ) RIGHTSBR
                            {
                            LEFTSBR112=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2221); 
                            LEFTSBR112_tree = 
                            (Object)adaptor.create(LEFTSBR112)
                            ;
                            adaptor.addChild(root_0, LEFTSBR112_tree);


                            set113=(Token)input.LT(1);

                            if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                                input.consume();
                                adaptor.addChild(root_0, 
                                (Object)adaptor.create(set113)
                                );
                                state.errorRecovery=false;
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            RIGHTSBR114=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2229); 
                            RIGHTSBR114_tree = 
                            (Object)adaptor.create(RIGHTSBR114)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR114_tree);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_lhs_access_in_lhs_access2232);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rhs_assignment"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1330:1: rhs_assignment[boolean defer] returns [NamedElement e] : (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] );
    public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.functionCall_return fc =null;

        EugeneParser.dataExchange_return de =null;

        EugeneParser.expr_return exp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1332:2: (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] )
            int alt46=3;
            switch ( input.LA(1) ) {
            case PERMUTE:
            case PRODUCT:
                {
                alt46=1;
                }
                break;
            case LC_IMPORT:
            case LC_PIGEON:
            case PIGEON:
            case SBOL:
            case UC_IMPORT:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1332:4: fc= functionCall[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_rhs_assignment2255);
                    fc=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, fc.getTree());


                    if(!defer) {
                        retval.e = (fc!=null?fc.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1337:4: de= dataExchange[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_rhs_assignment2265);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());


                    if(!defer) {
                        retval.e = (de!=null?de.e:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1342:4: exp= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_rhs_assignment2275);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1353:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
    public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
        EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token char_literal116=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object idToken_tree=null;
        Object char_literal116_tree=null;


        retval.lstElements =new ArrayList<NamedElement>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1358:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1358:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2303); 
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1370:4: ( ',' lstToken= listOfIDs[defer] )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==COMMA) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1370:5: ',' lstToken= listOfIDs[defer]
                    {
                    char_literal116=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2312); 
                    char_literal116_tree = 
                    (Object)adaptor.create(char_literal116)
                    ;
                    adaptor.addChild(root_0, char_literal116_tree);


                    pushFollow(FOLLOW_listOfIDs_in_listOfIDs2316);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1377:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
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
        EugeneParser.cnf_rule_return cnf =null;


        Object name_tree=null;
        Object device_tree=null;
        Object RULE117_tree=null;
        Object LEFTP118_tree=null;
        Object set119_tree=null;
        Object COLON120_tree=null;
        Object RIGHTP121_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1379:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1379:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            RULE117=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2340); 
            RULE117_tree = 
            (Object)adaptor.create(RULE117)
            ;
            adaptor.addChild(root_0, RULE117_tree);


            name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2344); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
            adaptor.addChild(root_0, name_tree);


            LEFTP118=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2346); 
            LEFTP118_tree = 
            (Object)adaptor.create(LEFTP118)
            ;
            adaptor.addChild(root_0, LEFTP118_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1379:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1379:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1379:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LC_ON||LA48_0==UC_ON) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1379:26: ( LC_ON | UC_ON ) device= ID COLON
                    {
                    set119=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set119)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2359); 
                    device_tree = 
                    (Object)adaptor.create(device)
                    ;
                    adaptor.addChild(root_0, device_tree);


                    COLON120=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2361); 
                    COLON120_tree = 
                    (Object)adaptor.create(COLON120)
                    ;
                    adaptor.addChild(root_0, COLON120_tree);


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
            	

            pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2369);
            cnf=cnf_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, cnf.getTree());

            }


            RIGHTP121=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2374); 
            RIGHTP121_tree = 
            (Object)adaptor.create(RIGHTP121)
            ;
            adaptor.addChild(root_0, RIGHTP121_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1403:1: ruleOperator[boolean defer] : ruleOperators ;
    public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
        EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ruleOperators_return ruleOperators122 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1404:2: ( ruleOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1404:4: ruleOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_ruleOperators_in_ruleOperator2388);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ruleOperators"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1408:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
    public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
        EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set123=null;

        Object set123_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1409:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set123=(Token)input.LT(1);

            if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 117 && input.LA(1) <= 151)||(input.LA(1) >= 153 && input.LA(1) <= 190)||(input.LA(1) >= 192 && input.LA(1) <= 194) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set123)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1452:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1453:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
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
            case 127:
            case 166:
                {
                alt49=7;
                }
                break;
            case 136:
            case 175:
                {
                alt49=8;
                }
                break;
            case 133:
            case 172:
                {
                alt49=9;
                }
                break;
            case 139:
            case 178:
                {
                alt49=10;
                }
                break;
            case 153:
            case 192:
                {
                alt49=11;
                }
                break;
            case 129:
            case 168:
                {
                alt49=12;
                }
                break;
            case 130:
            case 169:
                {
                alt49=13;
                }
                break;
            case 137:
            case 176:
                {
                alt49=14;
                }
                break;
            case 152:
            case 191:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1453:4: EQUALS EQUALS
                    {
                    root_0 = (Object)adaptor.nil();


                    EQUALS124=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2767); 
                    EQUALS124_tree = 
                    (Object)adaptor.create(EQUALS124)
                    ;
                    adaptor.addChild(root_0, EQUALS124_tree);


                    EQUALS125=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2769); 
                    EQUALS125_tree = 
                    (Object)adaptor.create(EQUALS125)
                    ;
                    adaptor.addChild(root_0, EQUALS125_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:4: NEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    NEQUAL126=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators2774); 
                    NEQUAL126_tree = 
                    (Object)adaptor.create(NEQUAL126)
                    ;
                    adaptor.addChild(root_0, NEQUAL126_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1455:4: LTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    LTHAN127=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators2779); 
                    LTHAN127_tree = 
                    (Object)adaptor.create(LTHAN127)
                    ;
                    adaptor.addChild(root_0, LTHAN127_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1456:4: GTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    GTHAN128=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators2784); 
                    GTHAN128_tree = 
                    (Object)adaptor.create(GTHAN128)
                    ;
                    adaptor.addChild(root_0, GTHAN128_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1457:4: LEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    LEQUAL129=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators2789); 
                    LEQUAL129_tree = 
                    (Object)adaptor.create(LEQUAL129)
                    ;
                    adaptor.addChild(root_0, LEQUAL129_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1458:4: GEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    GEQUAL130=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators2794); 
                    GEQUAL130_tree = 
                    (Object)adaptor.create(GEQUAL130)
                    ;
                    adaptor.addChild(root_0, GEQUAL130_tree);


                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1459:4: ( 'CONTAINS' | 'contains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set131=(Token)input.LT(1);

                    if ( input.LA(1)==127||input.LA(1)==166 ) {
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
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1460:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set132=(Token)input.LT(1);

                    if ( input.LA(1)==136||input.LA(1)==175 ) {
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
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1461:4: ( 'MATCHES' | 'matches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set133=(Token)input.LT(1);

                    if ( input.LA(1)==133||input.LA(1)==172 ) {
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
                case 10 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1462:4: ( 'NOTMATCHES' | 'notmatches' )
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
                case 11 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1463:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set135=(Token)input.LT(1);

                    if ( input.LA(1)==153||input.LA(1)==192 ) {
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
                case 12 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1464:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set136=(Token)input.LT(1);

                    if ( input.LA(1)==129||input.LA(1)==168 ) {
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
                case 13 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1465:4: ( 'EQUALS' | 'equals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set137=(Token)input.LT(1);

                    if ( input.LA(1)==130||input.LA(1)==169 ) {
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
                case 14 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1466:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set138=(Token)input.LT(1);

                    if ( input.LA(1)==137||input.LA(1)==176 ) {
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
                case 15 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1467:4: ( 'SOUNDSLIKE' | 'soundslike' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set139=(Token)input.LT(1);

                    if ( input.LA(1)==152||input.LA(1)==191 ) {
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

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1470:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
    public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
        EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set140=null;
        EugeneParser.or_predicate_return c =null;

        EugeneParser.cnf_rule_return cnf =null;


        Object set140_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:4: (c= or_predicate[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1472:5: c= or_predicate[defer]
            {
            pushFollow(FOLLOW_or_predicate_in_cnf_rule2895);
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1480:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LC_AND||LA50_0==LOG_AND||LA50_0==UC_AND) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1480:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
                    {
                    set140=(Token)input.LT(1);

                    if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
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


                    pushFollow(FOLLOW_cnf_rule_in_cnf_rule2913);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1487:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
    public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
        EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set141=null;
        EugeneParser.negated_predicate_return n1 =null;

        EugeneParser.negated_predicate_return n2 =null;


        Object set141_tree=null;


        LogicalOr lor = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1492:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1492:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_negated_predicate_in_or_predicate2943);
            n1=negated_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, n1.getTree());


            if(!defer) {
                retval.p = (n1!=null?n1.p:null);
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1496:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==LC_OR||LA51_0==LOG_OR||LA51_0==UC_OR) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1496:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
            	    {
            	    set141=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
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


            	    pushFollow(FOLLOW_negated_predicate_in_or_predicate2959);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1517:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
    public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
        EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set142=null;
        EugeneParser.predicate_return c =null;


        Object set142_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1519:2: ( ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1519:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1519:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==LC_NOT||LA52_0==LOG_NOT||LA52_0==UC_NOT) ) {
                alt52=1;
            }
            else if ( (LA52_0==ID||LA52_0==LC_INDUCES||LA52_0==LC_REPRESSES||(LA52_0 >= LEFTP && LA52_0 <= LEFTSBR)||LA52_0==MINUS||LA52_0==NUMBER||LA52_0==REAL||LA52_0==STRING||LA52_0==UC_INDUCES||LA52_0==UC_REPRESSES||(LA52_0 >= 117 && LA52_0 <= 151)||(LA52_0 >= 153 && LA52_0 <= 190)||(LA52_0 >= 192 && LA52_0 <= 194)) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }
            switch (alt52) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1519:5: ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer]
                    {
                    set142=(Token)input.LT(1);

                    if ( input.LA(1)==LC_NOT||input.LA(1)==LOG_NOT||input.LA(1)==UC_NOT ) {
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


                    pushFollow(FOLLOW_predicate_in_negated_predicate2997);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1528:4: c= predicate[defer]
                    {
                    pushFollow(FOLLOW_predicate_in_negated_predicate3007);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1535:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1537:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
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
                case 152:
                case 191:
                    {
                    alt55=3;
                    }
                    break;
                case 127:
                case 166:
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
                            new NoViableAltException("", 55, 7, input);

                        throw nvae;

                    }

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
                            new NoViableAltException("", 55, 8, input);

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
                            new NoViableAltException("", 55, 9, input);

                        throw nvae;

                    }

                    }
                    break;
                case 153:
                case 192:
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
                case 129:
                case 168:
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
                case 130:
                case 169:
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
                case 137:
                case 176:
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
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 128:
                case 131:
                case 132:
                case 134:
                case 135:
                case 138:
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
                case 167:
                case 170:
                case 171:
                case 173:
                case 174:
                case 177:
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
                case 193:
                case 194:
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
                case 127:
                case 166:
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
                case 152:
                case 191:
                    {
                    alt55=3;
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
                            new NoViableAltException("", 55, 7, input);

                        throw nvae;

                    }

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
                            new NoViableAltException("", 55, 8, input);

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
                            new NoViableAltException("", 55, 9, input);

                        throw nvae;

                    }

                    }
                    break;
                case 153:
                case 192:
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
                case 129:
                case 168:
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
                case 130:
                case 169:
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
                case 137:
                case 176:
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
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 128:
                case 131:
                case 132:
                case 134:
                case 135:
                case 138:
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
                case 167:
                case 170:
                case 171:
                case 173:
                case 174:
                case 177:
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
                case 193:
                case 194:
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
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
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
            case 192:
            case 193:
            case 194:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1537:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1537:4: (lhs= operand[defer] )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==ID||LA53_0==LEFTSBR||LA53_0==NUMBER) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1537:5: lhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate3034);
                            lhs=operand(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lhs.getTree());


                            addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_ruleOperator_in_predicate3044);
                    op=ruleOperator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, op.getTree());


                    addToken((op!=null?input.toString(op.start,op.stop):null));	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1541:5: (rhs= operand[defer] )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==ID||LA54_0==LEFTSBR||LA54_0==NUMBER) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1541:6: rhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate3053);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1555:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_predicate3067); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1572:4: exp= expressionRule[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expressionRule_in_predicate3076);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1579:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' ) ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1586:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1586:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1586:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1586:5: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_operand3107); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1598:4: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3116); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1603:4: '[' n= NUMBER ']'
                    {
                    char_literal143=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand3123); 
                    char_literal143_tree = 
                    (Object)adaptor.create(char_literal143)
                    ;
                    adaptor.addChild(root_0, char_literal143_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3127); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal144=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand3129); 
                    char_literal144_tree = 
                    (Object)adaptor.create(char_literal144)
                    ;
                    adaptor.addChild(root_0, char_literal144_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1622:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
    public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
        EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expression_return lhs =null;

        EugeneParser.exp_op_return op =null;

        EugeneParser.expression_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1624:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1624:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expression_in_expressionRule3155);
            lhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_exp_op_in_expressionRule3160);
            op=exp_op(defer);

            state._fsp--;

            adaptor.addChild(root_0, op.getTree());

            pushFollow(FOLLOW_expression_in_expressionRule3165);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1635:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
    public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
        EugeneParser.expression_return retval = new EugeneParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTP145=null;
        Token RIGHTP147=null;
        EugeneParser.exp_operand_return lhs =null;

        EugeneParser.exp_operator_return expop =null;

        EugeneParser.expression_return rhs =null;

        EugeneParser.expression_return expression146 =null;


        Object LEFTP145_tree=null;
        Object RIGHTP147_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1637:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1637:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_exp_operand_in_expression3189);
                    lhs=exp_operand(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs.getTree());


                    if(!defer) {
                        retval.exp = new Expression((lhs!=null?lhs.eop:null), null, null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1641:4: (expop= exp_operator[defer] rhs= expression[defer] )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==DIV||LA57_0==MINUS||LA57_0==MULT||LA57_0==PLUS) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1641:6: expop= exp_operator[defer] rhs= expression[defer]
                            {
                            pushFollow(FOLLOW_exp_operator_in_expression3198);
                            expop=exp_operator(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, expop.getTree());

                            pushFollow(FOLLOW_expression_in_expression3203);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1646:4: LEFTP expression[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTP145=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3215); 
                    LEFTP145_tree = 
                    (Object)adaptor.create(LEFTP145)
                    ;
                    adaptor.addChild(root_0, LEFTP145_tree);


                    pushFollow(FOLLOW_expression_in_expression3217);
                    expression146=expression(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expression146.getTree());

                    RIGHTP147=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3220); 
                    RIGHTP147_tree = 
                    (Object)adaptor.create(RIGHTP147)
                    ;
                    adaptor.addChild(root_0, RIGHTP147_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1653:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1655:2: ( PLUS | MINUS | MULT | DIV )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1655:4: PLUS
                    {
                    root_0 = (Object)adaptor.nil();


                    PLUS148=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3239); 
                    PLUS148_tree = 
                    (Object)adaptor.create(PLUS148)
                    ;
                    adaptor.addChild(root_0, PLUS148_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.PLUS;	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1660:4: MINUS
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS149=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3247); 
                    MINUS149_tree = 
                    (Object)adaptor.create(MINUS149)
                    ;
                    adaptor.addChild(root_0, MINUS149_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MINUS;	
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1665:4: MULT
                    {
                    root_0 = (Object)adaptor.nil();


                    MULT150=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3254); 
                    MULT150_tree = 
                    (Object)adaptor.create(MULT150)
                    ;
                    adaptor.addChild(root_0, MULT150_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MULT;	
                    }
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1670:4: DIV
                    {
                    root_0 = (Object)adaptor.nil();


                    DIV151=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3261); 
                    DIV151_tree = 
                    (Object)adaptor.create(DIV151)
                    ;
                    adaptor.addChild(root_0, DIV151_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1683:2: ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1683:4: (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )*
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1683:4: (i1= ID DOT )*
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
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1683:5: i1= ID DOT
                    	    {
                    	    i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3291); 
                    	    i1_tree = 
                    	    (Object)adaptor.create(i1)
                    	    ;
                    	    adaptor.addChild(root_0, i1_tree);


                    	    DOT152=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3293); 
                    	    DOT152_tree = 
                    	    (Object)adaptor.create(DOT152)
                    	    ;
                    	    adaptor.addChild(root_0, DOT152_tree);



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


                    i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3302); 
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
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1751:5: ( LEFTSBR n= NUMBER RIGHTSBR )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==LEFTSBR) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1751:6: LEFTSBR n= NUMBER RIGHTSBR
                    	    {
                    	    LEFTSBR153=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3308); 
                    	    LEFTSBR153_tree = 
                    	    (Object)adaptor.create(LEFTSBR153)
                    	    ;
                    	    adaptor.addChild(root_0, LEFTSBR153_tree);


                    	    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3312); 
                    	    n_tree = 
                    	    (Object)adaptor.create(n)
                    	    ;
                    	    adaptor.addChild(root_0, n_tree);


                    	    RIGHTSBR154=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3314); 
                    	    RIGHTSBR154_tree = 
                    	    (Object)adaptor.create(RIGHTSBR154)
                    	    ;
                    	    adaptor.addChild(root_0, RIGHTSBR154_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1762:4: n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3326); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1769:4: MINUS n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS155=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3333); 
                    MINUS155_tree = 
                    (Object)adaptor.create(MINUS155)
                    ;
                    adaptor.addChild(root_0, MINUS155_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3337); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1776:4: r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3346); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1783:4: MINUS r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS156=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3353); 
                    MINUS156_tree = 
                    (Object)adaptor.create(MINUS156)
                    ;
                    adaptor.addChild(root_0, MINUS156_tree);


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3357); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1790:4: s= STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3366); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1800:1: regexp[boolean defer] :;
    public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
        EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1801:2: ()
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1802:2: 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1804:1: exp_op[boolean defer] : relationalOperators ;
    public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
        EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.relationalOperators_return relationalOperators157 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1805:2: ( relationalOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1805:4: relationalOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_relationalOperators_in_exp_op3393);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "grammarDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1813:1: grammarDeclaration[boolean defer] : GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP ;
    public final EugeneParser.grammarDeclaration_return grammarDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.grammarDeclaration_return retval = new EugeneParser.grammarDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token GRAMMAR158=null;
        Token LEFTP159=null;
        Token RIGHTP161=null;
        EugeneParser.list_of_production_rules_return list_of_production_rules160 =null;


        Object n_tree=null;
        Object GRAMMAR158_tree=null;
        Object LEFTP159_tree=null;
        Object RIGHTP161_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1814:2: ( GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1815:3: GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            GRAMMAR158=(Token)match(input,GRAMMAR,FOLLOW_GRAMMAR_in_grammarDeclaration3412); 
            GRAMMAR158_tree = 
            (Object)adaptor.create(GRAMMAR158)
            ;
            adaptor.addChild(root_0, GRAMMAR158_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_grammarDeclaration3416); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            LEFTP159=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_grammarDeclaration3418); 
            LEFTP159_tree = 
            (Object)adaptor.create(LEFTP159)
            ;
            adaptor.addChild(root_0, LEFTP159_tree);


            pushFollow(FOLLOW_list_of_production_rules_in_grammarDeclaration3420);
            list_of_production_rules160=list_of_production_rules(defer);

            state._fsp--;

            adaptor.addChild(root_0, list_of_production_rules160.getTree());

            RIGHTP161=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_grammarDeclaration3423); 
            RIGHTP161_tree = 
            (Object)adaptor.create(RIGHTP161)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list_of_production_rules"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1818:1: list_of_production_rules[boolean defer] : production_rule[defer] SEMIC ( list_of_production_rules[defer] )? ;
    public final EugeneParser.list_of_production_rules_return list_of_production_rules(boolean defer) throws RecognitionException {
        EugeneParser.list_of_production_rules_return retval = new EugeneParser.list_of_production_rules_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMIC163=null;
        EugeneParser.production_rule_return production_rule162 =null;

        EugeneParser.list_of_production_rules_return list_of_production_rules164 =null;


        Object SEMIC163_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1819:2: ( production_rule[defer] SEMIC ( list_of_production_rules[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1819:4: production_rule[defer] SEMIC ( list_of_production_rules[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_production_rule_in_list_of_production_rules3435);
            production_rule162=production_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, production_rule162.getTree());

            SEMIC163=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_list_of_production_rules3438); 
            SEMIC163_tree = 
            (Object)adaptor.create(SEMIC163)
            ;
            adaptor.addChild(root_0, SEMIC163_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1819:33: ( list_of_production_rules[defer] )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==ID) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1819:34: list_of_production_rules[defer]
                    {
                    pushFollow(FOLLOW_list_of_production_rules_in_list_of_production_rules3441);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "production_rule"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1822:1: production_rule[boolean defer] : lhs= ID ARROW right_hand_side[defer] ;
    public final EugeneParser.production_rule_return production_rule(boolean defer) throws RecognitionException {
        EugeneParser.production_rule_return retval = new EugeneParser.production_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs=null;
        Token ARROW165=null;
        EugeneParser.right_hand_side_return right_hand_side166 =null;


        Object lhs_tree=null;
        Object ARROW165_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1823:2: (lhs= ID ARROW right_hand_side[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1823:4: lhs= ID ARROW right_hand_side[defer]
            {
            root_0 = (Object)adaptor.nil();


            lhs=(Token)match(input,ID,FOLLOW_ID_in_production_rule3461); 
            lhs_tree = 
            (Object)adaptor.create(lhs)
            ;
            adaptor.addChild(root_0, lhs_tree);



            if(!defer) {
                // ID denotes a non-terminal of the grammar
            }	
            	

            ARROW165=(Token)match(input,ARROW,FOLLOW_ARROW_in_production_rule3465); 
            ARROW165_tree = 
            (Object)adaptor.create(ARROW165)
            ;
            adaptor.addChild(root_0, ARROW165_tree);


            pushFollow(FOLLOW_right_hand_side_in_production_rule3467);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "right_hand_side"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:1: right_hand_side[boolean defer] : (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] );
    public final EugeneParser.right_hand_side_return right_hand_side(boolean defer) throws RecognitionException {
        EugeneParser.right_hand_side_return retval = new EugeneParser.right_hand_side_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token COMMA167=null;
        EugeneParser.right_hand_side_return right_hand_side168 =null;

        EugeneParser.interaction_return interaction169 =null;


        Object i_tree=null;
        Object COMMA167_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1831:2: (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1831:4: i= ID ( COMMA right_hand_side[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_right_hand_side3483); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer) {
                        // ID must be either a terminal (i.e. a part)
                        // or a non-terminal defined within the grammar
                    }	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1836:4: ( COMMA right_hand_side[defer] )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==COMMA) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1836:5: COMMA right_hand_side[defer]
                            {
                            COMMA167=(Token)match(input,COMMA,FOLLOW_COMMA_in_right_hand_side3488); 
                            COMMA167_tree = 
                            (Object)adaptor.create(COMMA167)
                            ;
                            adaptor.addChild(root_0, COMMA167_tree);


                            pushFollow(FOLLOW_right_hand_side_in_right_hand_side3490);
                            right_hand_side168=right_hand_side(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, right_hand_side168.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1837:4: interaction[defer, \"some_string\"]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_right_hand_side3498);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interactionDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1844:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
    public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token INTERACTION170=null;
        Token LEFTP171=null;
        Token RIGHTP172=null;
        EugeneParser.interaction_return i1 =null;

        EugeneParser.interaction_return i2 =null;


        Object name_tree=null;
        Object INTERACTION170_tree=null;
        Object LEFTP171_tree=null;
        Object RIGHTP172_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1846:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1846:4: i1= interaction[defer, null]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3523);
                    i1=interaction(defer, null);

                    state._fsp--;

                    adaptor.addChild(root_0, i1.getTree());


                    if(!defer) {
                        retval.ia = (i1!=null?i1.ia:null);
                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1851:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    INTERACTION170=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3531); 
                    INTERACTION170_tree = 
                    (Object)adaptor.create(INTERACTION170)
                    ;
                    adaptor.addChild(root_0, INTERACTION170_tree);


                    name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3535); 
                    name_tree = 
                    (Object)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    LEFTP171=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3537); 
                    LEFTP171_tree = 
                    (Object)adaptor.create(LEFTP171)
                    ;
                    adaptor.addChild(root_0, LEFTP171_tree);


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3541);
                    i2=interaction(defer, (name!=null?name.getText():null));

                    state._fsp--;

                    adaptor.addChild(root_0, i2.getTree());

                    RIGHTP172=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3544); 
                    RIGHTP172_tree = 
                    (Object)adaptor.create(RIGHTP172)
                    ;
                    adaptor.addChild(root_0, RIGHTP172_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1858:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
    public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
        EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs1=null;
        Token rhs1=null;
        Token lhs2=null;
        Token LEFTP173=null;
        Token RIGHTP174=null;
        EugeneParser.interactionType_return t1 =null;

        EugeneParser.interactionType_return t2 =null;

        EugeneParser.interaction_return rhs2 =null;


        Object lhs1_tree=null;
        Object rhs1_tree=null;
        Object lhs2_tree=null;
        Object LEFTP173_tree=null;
        Object RIGHTP174_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1860:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1860:4: lhs1= ID t1= interactionType[defer] rhs1= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3567); 
                    lhs1_tree = 
                    (Object)adaptor.create(lhs1)
                    ;
                    adaptor.addChild(root_0, lhs1_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3571);
                    t1=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t1.getTree());

                    rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3576); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1869:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3585); 
                    lhs2_tree = 
                    (Object)adaptor.create(lhs2)
                    ;
                    adaptor.addChild(root_0, lhs2_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3589);
                    t2=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t2.getTree());

                    LEFTP173=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3592); 
                    LEFTP173_tree = 
                    (Object)adaptor.create(LEFTP173)
                    ;
                    adaptor.addChild(root_0, LEFTP173_tree);


                    pushFollow(FOLLOW_interaction_in_interaction3596);
                    rhs2=interaction(defer, name);

                    state._fsp--;

                    adaptor.addChild(root_0, rhs2.getTree());

                    RIGHTP174=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3599); 
                    RIGHTP174_tree = 
                    (Object)adaptor.create(RIGHTP174)
                    ;
                    adaptor.addChild(root_0, RIGHTP174_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1880:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
    public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
        EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set175=null;
        Token set176=null;

        Object set175_tree=null;
        Object set176_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1882:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1882:4: ( UC_REPRESSES | LC_REPRESSES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set175=(Token)input.LT(1);

                    if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
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



                    if(!defer) {
                        retval.type = Interaction.InteractionType.REPRESSES;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1887:4: ( UC_INDUCES | LC_INDUCES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set176=(Token)input.LT(1);

                    if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1903:1: functionCall[boolean defer] returns [NamedElement e] : (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.functionCall_return functionCall(boolean defer) throws RecognitionException {
        EugeneParser.functionCall_return retval = new EugeneParser.functionCall_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token pr=null;
        Token pe=null;
        Token idToken=null;
        Token LEFTP177=null;
        Token RIGHTP178=null;

        Object pr_tree=null;
        Object pe_tree=null;
        Object idToken_tree=null;
        Object LEFTP177_tree=null;
        Object RIGHTP178_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1905:2: ( (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1905:4: (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1905:4: (pr= PRODUCT |pe= PERMUTE )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1905:5: pr= PRODUCT
                    {
                    pr=(Token)match(input,PRODUCT,FOLLOW_PRODUCT_in_functionCall3666); 
                    pr_tree = 
                    (Object)adaptor.create(pr)
                    ;
                    adaptor.addChild(root_0, pr_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1905:16: pe= PERMUTE
                    {
                    pe=(Token)match(input,PERMUTE,FOLLOW_PERMUTE_in_functionCall3670); 
                    pe_tree = 
                    (Object)adaptor.create(pe)
                    ;
                    adaptor.addChild(root_0, pe_tree);


                    }
                    break;

            }


            LEFTP177=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_functionCall3673); 
            LEFTP177_tree = 
            (Object)adaptor.create(LEFTP177)
            ;
            adaptor.addChild(root_0, LEFTP177_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_functionCall3677); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP178=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_functionCall3679); 
            RIGHTP178_tree = 
            (Object)adaptor.create(RIGHTP178)
            ;
            adaptor.addChild(root_0, RIGHTP178_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1932:1: printStatement[boolean defer] : ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP );
    public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
        EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PRINTLN179=null;
        Token LEFTP180=null;
        Token RIGHTP181=null;
        Token PRINT182=null;
        Token LEFTP183=null;
        Token RIGHTP184=null;
        EugeneParser.toPrint_return tp =null;


        Object PRINTLN179_tree=null;
        Object LEFTP180_tree=null;
        Object RIGHTP181_tree=null;
        Object PRINT182_tree=null;
        Object LEFTP183_tree=null;
        Object RIGHTP184_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1933:2: ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==PRINTLN) ) {
                alt70=1;
            }
            else if ( (LA70_0==PRINT) ) {
                alt70=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;

            }
            switch (alt70) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1933:4: PRINTLN LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINTLN179=(Token)match(input,PRINTLN,FOLLOW_PRINTLN_in_printStatement3697); 
                    PRINTLN179_tree = 
                    (Object)adaptor.create(PRINTLN179)
                    ;
                    adaptor.addChild(root_0, PRINTLN179_tree);


                    LEFTP180=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3699); 
                    LEFTP180_tree = 
                    (Object)adaptor.create(LEFTP180)
                    ;
                    adaptor.addChild(root_0, LEFTP180_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3703);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP181=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3706); 
                    RIGHTP181_tree = 
                    (Object)adaptor.create(RIGHTP181)
                    ;
                    adaptor.addChild(root_0, RIGHTP181_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1946:4: PRINT LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINT182=(Token)match(input,PRINT,FOLLOW_PRINT_in_printStatement3713); 
                    PRINT182_tree = 
                    (Object)adaptor.create(PRINT182)
                    ;
                    adaptor.addChild(root_0, PRINT182_tree);


                    LEFTP183=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3715); 
                    LEFTP183_tree = 
                    (Object)adaptor.create(LEFTP183)
                    ;
                    adaptor.addChild(root_0, LEFTP183_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3719);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP184=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3722); 
                    RIGHTP184_tree = 
                    (Object)adaptor.create(RIGHTP184)
                    ;
                    adaptor.addChild(root_0, RIGHTP184_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1960:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
    public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return exp =null;

        EugeneParser.toPrint_prime_return tpp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1962:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1962:4: exp= expr[defer] tpp= toPrint_prime[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_toPrint3743);
            exp=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, exp.getTree());

            pushFollow(FOLLOW_toPrint_prime_in_toPrint3748);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1975:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
    public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA185=null;
        EugeneParser.toPrint_return tp =null;


        Object COMMA185_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1977:2: (| COMMA tp= toPrint[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1977:4: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.sb = new StringBuilder();
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1982:4: COMMA tp= toPrint[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    COMMA185=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime3774); 
                    COMMA185_tree = 
                    (Object)adaptor.create(COMMA185)
                    ;
                    adaptor.addChild(root_0, COMMA185_tree);


                    pushFollow(FOLLOW_toPrint_in_toPrint_prime3778);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1994:1: imperativeStatements[boolean defer] : ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] );
    public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException {
        EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ifStatement_return ifStatement186 =null;

        EugeneParser.forall_iterator_return forall_iterator187 =null;

        EugeneParser.for_loop_return for_loop188 =null;

        EugeneParser.while_loop_return while_loop189 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1995:2: ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] | while_loop[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1995:4: ifStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifStatement_in_imperativeStatements3798);
                    ifStatement186=ifStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ifStatement186.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1996:4: forall_iterator[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_forall_iterator_in_imperativeStatements3804);
                    forall_iterator187=forall_iterator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, forall_iterator187.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1997:4: for_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_for_loop_in_imperativeStatements3810);
                    for_loop188=for_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, for_loop188.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1998:4: while_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_while_loop_in_imperativeStatements3816);
                    while_loop189=while_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, while_loop189.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2001:1: ifStatement[boolean defer] : ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? ;
    public final EugeneParser.ifStatement_return ifStatement(boolean defer) throws RecognitionException {
        EugeneParser.ifStatement_return retval = new EugeneParser.ifStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set190=null;
        Token LEFTP191=null;
        Token RIGHTP192=null;
        Token LEFTCUR193=null;
        Token RIGHTCUR194=null;
        Token set195=null;
        Token LEFTP196=null;
        Token RIGHTP197=null;
        Token LEFTCUR198=null;
        Token RIGHTCUR199=null;
        Token set200=null;
        Token LEFTCUR201=null;
        Token RIGHTCUR202=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set190_tree=null;
        Object LEFTP191_tree=null;
        Object RIGHTP192_tree=null;
        Object LEFTCUR193_tree=null;
        Object RIGHTCUR194_tree=null;
        Object set195_tree=null;
        Object LEFTP196_tree=null;
        Object RIGHTP197_tree=null;
        Object LEFTCUR198_tree=null;
        Object RIGHTCUR199_tree=null;
        Object set200_tree=null;
        Object LEFTCUR201_tree=null;
        Object RIGHTCUR202_tree=null;


        boolean bExecuted = false;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2005:2: ( ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2009:3: ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            {
            root_0 = (Object)adaptor.nil();


            set190=(Token)input.LT(1);

            if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set190)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP191=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3848); 
            LEFTP191_tree = 
            (Object)adaptor.create(LEFTP191)
            ;
            adaptor.addChild(root_0, LEFTP191_tree);


            pushFollow(FOLLOW_imp_condition_in_ifStatement3852);
            co=imp_condition(defer);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP192=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3855); 
            RIGHTP192_tree = 
            (Object)adaptor.create(RIGHTP192)
            ;
            adaptor.addChild(root_0, RIGHTP192_tree);


            LEFTCUR193=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3857); 
            LEFTCUR193_tree = 
            (Object)adaptor.create(LEFTCUR193)
            ;
            adaptor.addChild(root_0, LEFTCUR193_tree);


            pushFollow(FOLLOW_listOfStatements_in_ifStatement3865);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR194=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3868); 
            RIGHTCUR194_tree = 
            (Object)adaptor.create(RIGHTCUR194)
            ;
            adaptor.addChild(root_0, RIGHTCUR194_tree);



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
            		

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2032:3: ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==LC_ELSEIF||LA73_0==UC_ELSEIF) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2032:5: ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            	    {
            	    set195=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
            	        input.consume();
            	        adaptor.addChild(root_0, 
            	        (Object)adaptor.create(set195)
            	        );
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    LEFTP196=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3889); 
            	    LEFTP196_tree = 
            	    (Object)adaptor.create(LEFTP196)
            	    ;
            	    adaptor.addChild(root_0, LEFTP196_tree);


            	    pushFollow(FOLLOW_imp_condition_in_ifStatement3893);
            	    co=imp_condition(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, co.getTree());

            	    RIGHTP197=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3896); 
            	    RIGHTP197_tree = 
            	    (Object)adaptor.create(RIGHTP197)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP197_tree);


            	    LEFTCUR198=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3898); 
            	    LEFTCUR198_tree = 
            	    (Object)adaptor.create(LEFTCUR198)
            	    ;
            	    adaptor.addChild(root_0, LEFTCUR198_tree);


            	    pushFollow(FOLLOW_listOfStatements_in_ifStatement3906);
            	    stmts=listOfStatements(true);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmts.getTree());

            	    RIGHTCUR199=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3909); 
            	    RIGHTCUR199_tree = 
            	    (Object)adaptor.create(RIGHTCUR199)
            	    ;
            	    adaptor.addChild(root_0, RIGHTCUR199_tree);



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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2055:3: ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==LC_ELSE||LA74_0==UC_ELSE) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2055:4: ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR
                    {
                    set200=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set200)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTCUR201=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3931); 
                    LEFTCUR201_tree = 
                    (Object)adaptor.create(LEFTCUR201)
                    ;
                    adaptor.addChild(root_0, LEFTCUR201_tree);


                    pushFollow(FOLLOW_listOfStatements_in_ifStatement3939);
                    stmts=listOfStatements(true);

                    state._fsp--;

                    adaptor.addChild(root_0, stmts.getTree());

                    RIGHTCUR202=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3942); 
                    RIGHTCUR202_tree = 
                    (Object)adaptor.create(RIGHTCUR202)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR202_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2071:1: imp_condition[boolean defer] returns [boolean bTrue] : lhs= expr[defer] ro= relationalOperators rhs= expr[defer] ;
    public final EugeneParser.imp_condition_return imp_condition(boolean defer) throws RecognitionException {
        EugeneParser.imp_condition_return retval = new EugeneParser.imp_condition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return lhs =null;

        EugeneParser.relationalOperators_return ro =null;

        EugeneParser.expr_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2073:2: (lhs= expr[defer] ro= relationalOperators rhs= expr[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2073:4: lhs= expr[defer] ro= relationalOperators rhs= expr[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_imp_condition3966);
            lhs=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_relationalOperators_in_imp_condition3971);
            ro=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, ro.getTree());

            pushFollow(FOLLOW_expr_in_imp_condition3975);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2124:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR ;
    public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException {
        EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token it=null;
        Token i=null;
        Token set203=null;
        Token COLON204=null;
        Token LEFTCUR205=null;
        Token RIGHTCUR207=null;
        EugeneParser.listOfStatements_return listOfStatements206 =null;


        Object it_tree=null;
        Object i_tree=null;
        Object set203_tree=null;
        Object COLON204_tree=null;
        Object LEFTCUR205_tree=null;
        Object RIGHTCUR207_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2125:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2125:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set203=(Token)input.LT(1);

            if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set203)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2125:26: (it= ID COLON )?
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2125:27: it= ID COLON
                    {
                    it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator4000); 
                    it_tree = 
                    (Object)adaptor.create(it)
                    ;
                    adaptor.addChild(root_0, it_tree);


                    COLON204=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator4002); 
                    COLON204_tree = 
                    (Object)adaptor.create(COLON204)
                    ;
                    adaptor.addChild(root_0, COLON204_tree);


                    }
                    break;

            }


            i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator4008); 
            i_tree = 
            (Object)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            LEFTCUR205=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator4010); 
            LEFTCUR205_tree = 
            (Object)adaptor.create(LEFTCUR205)
            ;
            adaptor.addChild(root_0, LEFTCUR205_tree);


            pushFollow(FOLLOW_listOfStatements_in_forall_iterator4015);
            listOfStatements206=listOfStatements(defer);

            state._fsp--;

            adaptor.addChild(root_0, listOfStatements206.getTree());


            if(!defer) {

            }			
            	

            RIGHTCUR207=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator4022); 
            RIGHTCUR207_tree = 
            (Object)adaptor.create(RIGHTCUR207)
            ;
            adaptor.addChild(root_0, RIGHTCUR207_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2134:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException {
        EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set208=null;
        Token LEFTP209=null;
        Token SEMIC210=null;
        Token SEMIC211=null;
        Token RIGHTP212=null;
        Token LEFTCUR213=null;
        Token RIGHTCUR214=null;
        EugeneParser.variableDeclaration_return ds =null;

        EugeneParser.imp_condition_return co =null;

        EugeneParser.assignment_return as =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set208_tree=null;
        Object LEFTP209_tree=null;
        Object SEMIC210_tree=null;
        Object SEMIC211_tree=null;
        Object RIGHTP212_tree=null;
        Object LEFTCUR213_tree=null;
        Object RIGHTCUR214_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC (as= assignment[true] )? RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set208=(Token)input.LT(1);

            if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set208)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP209=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop4040); 
            LEFTP209_tree = 
            (Object)adaptor.create(LEFTP209)
            ;
            adaptor.addChild(root_0, LEFTP209_tree);


            pushFollow(FOLLOW_variableDeclaration_in_for_loop4044);
            ds=variableDeclaration(true);

            state._fsp--;

            adaptor.addChild(root_0, ds.getTree());

            SEMIC210=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4047); 
            SEMIC210_tree = 
            (Object)adaptor.create(SEMIC210)
            ;
            adaptor.addChild(root_0, SEMIC210_tree);


            pushFollow(FOLLOW_imp_condition_in_for_loop4051);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            SEMIC211=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop4054); 
            SEMIC211_tree = 
            (Object)adaptor.create(SEMIC211)
            ;
            adaptor.addChild(root_0, SEMIC211_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:90: (as= assignment[true] )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==ID) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2135:91: as= assignment[true]
                    {
                    pushFollow(FOLLOW_assignment_in_for_loop4059);
                    as=assignment(true);

                    state._fsp--;

                    adaptor.addChild(root_0, as.getTree());

                    }
                    break;

            }


            RIGHTP212=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop4064); 
            RIGHTP212_tree = 
            (Object)adaptor.create(RIGHTP212)
            ;
            adaptor.addChild(root_0, RIGHTP212_tree);


            LEFTCUR213=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop4066); 
            LEFTCUR213_tree = 
            (Object)adaptor.create(LEFTCUR213)
            ;
            adaptor.addChild(root_0, LEFTCUR213_tree);


            pushFollow(FOLLOW_listOfStatements_in_for_loop4074);
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
            		

            RIGHTCUR214=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop4081); 
            RIGHTCUR214_tree = 
            (Object)adaptor.create(RIGHTCUR214)
            ;
            adaptor.addChild(root_0, RIGHTCUR214_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2162:1: while_loop[boolean defer] : ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.while_loop_return while_loop(boolean defer) throws RecognitionException {
        EugeneParser.while_loop_return retval = new EugeneParser.while_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set215=null;
        Token LEFTP216=null;
        Token RIGHTP217=null;
        Token LEFTCUR218=null;
        Token RIGHTCUR219=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set215_tree=null;
        Object LEFTP216_tree=null;
        Object RIGHTP217_tree=null;
        Object LEFTCUR218_tree=null;
        Object RIGHTCUR219_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2163:2: ( ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2163:4: ( UC_WHILE | LC_WHILE ) LEFTP co= imp_condition[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set215=(Token)input.LT(1);

            if ( input.LA(1)==LC_WHILE||input.LA(1)==UC_WHILE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set215)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP216=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_while_loop4101); 
            LEFTP216_tree = 
            (Object)adaptor.create(LEFTP216)
            ;
            adaptor.addChild(root_0, LEFTP216_tree);


            pushFollow(FOLLOW_imp_condition_in_while_loop4105);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP217=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_while_loop4108); 
            RIGHTP217_tree = 
            (Object)adaptor.create(RIGHTP217)
            ;
            adaptor.addChild(root_0, RIGHTP217_tree);


            LEFTCUR218=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_while_loop4110); 
            LEFTCUR218_tree = 
            (Object)adaptor.create(LEFTCUR218)
            ;
            adaptor.addChild(root_0, LEFTCUR218_tree);


            pushFollow(FOLLOW_listOfStatements_in_while_loop4118);
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
            	

            RIGHTCUR219=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_while_loop4125); 
            RIGHTCUR219_tree = 
            (Object)adaptor.create(RIGHTCUR219)
            ;
            adaptor.addChild(root_0, RIGHTCUR219_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2182:1: listOfStatements[boolean defer] : (stmtToken= statement[defer] )+ ;
    public final EugeneParser.listOfStatements_return listOfStatements(boolean defer) throws RecognitionException {
        EugeneParser.listOfStatements_return retval = new EugeneParser.listOfStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.statement_return stmtToken =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2183:2: ( (stmtToken= statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2183:4: (stmtToken= statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2183:4: (stmtToken= statement[defer] )+
            int cnt77=0;
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==ARRAY||(LA77_0 >= ASSERT && LA77_0 <= COLLECTION)||LA77_0==DEVICE||(LA77_0 >= EXIT_LC && LA77_0 <= EXIT_UC)||LA77_0==GRAMMAR||(LA77_0 >= HASHMARK && LA77_0 <= ID)||LA77_0==INTERACTION||(LA77_0 >= LC_FOR && LA77_0 <= LC_INCLUDE)||LA77_0==LC_PIGEON||LA77_0==LC_WHILE||(LA77_0 >= NOTE && LA77_0 <= NUM)||(LA77_0 >= PART && LA77_0 <= PIGEON)||(LA77_0 >= PRINT && LA77_0 <= RANDOM_UC)||(LA77_0 >= RULE && LA77_0 <= SEMIC)||(LA77_0 >= SIZEOF_LC && LA77_0 <= STORE_UC)||(LA77_0 >= TXT && LA77_0 <= TYPE)||(LA77_0 >= UC_FOR && LA77_0 <= UC_INCLUDE)||LA77_0==UC_WHILE) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2183:5: stmtToken= statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_listOfStatements4142);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2191:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* ;
    public final EugeneParser.expr_return expr(boolean defer) throws RecognitionException {
        EugeneParser.expr_return retval = new EugeneParser.expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token op=null;
        EugeneParser.multExpr_return e =null;


        Object op_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2193:2: (e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2193:4: e= multExpr[defer] (op= ( PLUS | MINUS ) e= multExpr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_expr4170);
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2211:5: (op= ( PLUS | MINUS ) e= multExpr[defer] )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==MINUS||LA78_0==PLUS) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2211:6: op= ( PLUS | MINUS ) e= multExpr[defer]
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


            	    pushFollow(FOLLOW_multExpr_in_expr4187);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2250:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2252:2: (e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2252:4: e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_multExpr4217);
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2272:5: ( (mul= MULT |div= DIV ) e= atom[defer] )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==DIV||LA80_0==MULT) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2272:7: (mul= MULT |div= DIV ) e= atom[defer]
            	    {
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2272:7: (mul= MULT |div= DIV )
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
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2272:8: mul= MULT
            	            {
            	            mul=(Token)match(input,MULT,FOLLOW_MULT_in_multExpr4228); 
            	            mul_tree = 
            	            (Object)adaptor.create(mul)
            	            ;
            	            adaptor.addChild(root_0, mul_tree);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2272:17: div= DIV
            	            {
            	            div=(Token)match(input,DIV,FOLLOW_DIV_in_multExpr4232); 
            	            div_tree = 
            	            (Object)adaptor.create(div)
            	            ;
            	            adaptor.addChild(root_0, div_tree);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_atom_in_multExpr4237);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2289:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] );
    public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
        EugeneParser.atom_return retval = new EugeneParser.atom_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token t=null;
        Token f=null;
        Token MINUS220=null;
        Token STRING221=null;
        Token char_literal222=null;
        Token char_literal224=null;
        Token LEFTSBR225=null;
        Token RIGHTSBR227=null;
        EugeneParser.dynamic_naming_return dn =null;

        EugeneParser.object_access_return oc =null;

        EugeneParser.built_in_function_return bif =null;

        EugeneParser.expr_return expr223 =null;

        EugeneParser.list_return list226 =null;


        Object n_tree=null;
        Object t_tree=null;
        Object f_tree=null;
        Object MINUS220_tree=null;
        Object STRING221_tree=null;
        Object char_literal222_tree=null;
        Object char_literal224_tree=null;
        Object LEFTSBR225_tree=null;
        Object RIGHTSBR227_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2291:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) |dn= dynamic_naming[defer] oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR |bif= built_in_function[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2291:4: (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2291:4: (n= NUMBER |n= REAL )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2291:5: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4264); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2291:16: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4270); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2300:4: MINUS (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS220=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom4280); 
                    MINUS220_tree = 
                    (Object)adaptor.create(MINUS220)
                    ;
                    adaptor.addChild(root_0, MINUS220_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2300:10: (n= NUMBER |n= REAL )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2300:11: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4285); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2300:22: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4291); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:5: t= ( TRUE_LC | TRUE_UC )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2309:27: f= ( FALSE_LC | FALSE_UC )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2322:4: dn= dynamic_naming[defer] oc= object_access[defer, $element]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dynamic_naming_in_atom4330);
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
                    	

                    pushFollow(FOLLOW_object_access_in_atom4337);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2354:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING221=(Token)match(input,STRING,FOLLOW_STRING_in_atom4346); 
                    STRING221_tree = 
                    (Object)adaptor.create(STRING221)
                    ;
                    adaptor.addChild(root_0, STRING221_tree);



                    		if(!defer) {
                    			retval.p.type = EugeneConstants.TXT;
                    			retval.p.txt = (STRING221!=null?STRING221.getText():null).substring(1, (STRING221!=null?STRING221.getText():null).length()-1);

                    			retval.element = null;
                    		}
                    	

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2363:4: '(' expr[defer] ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal222=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4354); 
                    char_literal222_tree = 
                    (Object)adaptor.create(char_literal222)
                    ;
                    adaptor.addChild(root_0, char_literal222_tree);


                    pushFollow(FOLLOW_expr_in_atom4356);
                    expr223=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expr223.getTree());

                    char_literal224=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4359); 
                    char_literal224_tree = 
                    (Object)adaptor.create(char_literal224)
                    ;
                    adaptor.addChild(root_0, char_literal224_tree);



                    		if(!defer) {
                    			retval.p = (expr223!=null?expr223.p:null);
                    			retval.primVariable = (expr223!=null?expr223.primVariable:null);
                    			retval.element = (expr223!=null?expr223.element:null);
                    		}
                    	

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2371:5: LEFTSBR list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR225=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4368); 
                    LEFTSBR225_tree = 
                    (Object)adaptor.create(LEFTSBR225)
                    ;
                    adaptor.addChild(root_0, LEFTSBR225_tree);


                    pushFollow(FOLLOW_list_in_atom4370);
                    list226=list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list226.getTree());

                    RIGHTSBR227=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4373); 
                    RIGHTSBR227_tree = 
                    (Object)adaptor.create(RIGHTSBR227)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR227_tree);



                    		if(!defer) {
                    			retval.p = (list226!=null?list226.listPrim:null);
                    			retval.primVariable = (list226!=null?list226.listPrim:null);
                    			typeList="";
                    		}
                    	

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2379:5: bif= built_in_function[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_built_in_function_in_atom4384);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2387:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
    public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
        EugeneParser.list_return retval = new EugeneParser.list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA228=null;
        EugeneParser.expr_return str1 =null;

        EugeneParser.expr_return str2 =null;


        Object COMMA228_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2389:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2389:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_list4407);
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2408:5: ( COMMA str2= expr[defer] )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==COMMA) ) {
                    alt85=1;
                }


                switch (alt85) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2408:6: COMMA str2= expr[defer]
            	    {
            	    COMMA228=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4414); 
            	    COMMA228_tree = 
            	    (Object)adaptor.create(COMMA228)
            	    ;
            	    adaptor.addChild(root_0, COMMA228_tree);


            	    pushFollow(FOLLOW_expr_in_list4418);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2423:1: built_in_function[boolean defer] returns [Variable p] : ( ( SIZEOF_LC | SIZEOF_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP );
    public final EugeneParser.built_in_function_return built_in_function(boolean defer) throws RecognitionException {
        EugeneParser.built_in_function_return retval = new EugeneParser.built_in_function_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set229=null;
        Token LEFTP230=null;
        Token RIGHTP231=null;
        Token set232=null;
        Token LEFTP233=null;
        Token RIGHTP234=null;
        Token set235=null;
        Token LEFTP236=null;
        Token RIGHTP237=null;
        EugeneParser.expr_return e =null;

        EugeneParser.range_return rg =null;


        Object set229_tree=null;
        Object LEFTP230_tree=null;
        Object RIGHTP231_tree=null;
        Object set232_tree=null;
        Object LEFTP233_tree=null;
        Object RIGHTP234_tree=null;
        Object set235_tree=null;
        Object LEFTP236_tree=null;
        Object RIGHTP237_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2425:2: ( ( SIZEOF_LC | SIZEOF_UC ) LEFTP e= expr[defer] RIGHTP | ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP | ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP )
            int alt86=3;
            switch ( input.LA(1) ) {
            case SIZEOF_LC:
            case SIZEOF_UC:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2425:4: ( SIZEOF_LC | SIZEOF_UC ) LEFTP e= expr[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set229=(Token)input.LT(1);

                    if ( (input.LA(1) >= SIZEOF_LC && input.LA(1) <= SIZEOF_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set229)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP230=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4452); 
                    LEFTP230_tree = 
                    (Object)adaptor.create(LEFTP230)
                    ;
                    adaptor.addChild(root_0, LEFTP230_tree);


                    pushFollow(FOLLOW_expr_in_built_in_function4456);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTP231=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4459); 
                    RIGHTP231_tree = 
                    (Object)adaptor.create(RIGHTP231)
                    ;
                    adaptor.addChild(root_0, RIGHTP231_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2438:4: ( RANDOM_LC | RANDOM_UC ) LEFTP rg= range[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set232=(Token)input.LT(1);

                    if ( (input.LA(1) >= RANDOM_LC && input.LA(1) <= RANDOM_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set232)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP233=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4472); 
                    LEFTP233_tree = 
                    (Object)adaptor.create(LEFTP233)
                    ;
                    adaptor.addChild(root_0, LEFTP233_tree);


                    pushFollow(FOLLOW_range_in_built_in_function4476);
                    rg=range(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, rg.getTree());

                    RIGHTP234=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4479); 
                    RIGHTP234_tree = 
                    (Object)adaptor.create(RIGHTP234)
                    ;
                    adaptor.addChild(root_0, RIGHTP234_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2447:4: ( SAVE_LC | SAVE_UC | STORE_LC | STORE_UC ) LEFTP e= expr[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set235=(Token)input.LT(1);

                    if ( (input.LA(1) >= SAVE_LC && input.LA(1) <= SAVE_UC)||(input.LA(1) >= STORE_LC && input.LA(1) <= STORE_UC) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set235)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTP236=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_built_in_function4496); 
                    LEFTP236_tree = 
                    (Object)adaptor.create(LEFTP236)
                    ;
                    adaptor.addChild(root_0, LEFTP236_tree);


                    pushFollow(FOLLOW_expr_in_built_in_function4500);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTP237=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_built_in_function4503); 
                    RIGHTP237_tree = 
                    (Object)adaptor.create(RIGHTP237)
                    ;
                    adaptor.addChild(root_0, RIGHTP237_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2462:1: range[boolean defer] returns [Variable sor, Variable eor] : s= expr[defer] COMMA e= expr[defer] ;
    public final EugeneParser.range_return range(boolean defer) throws RecognitionException {
        EugeneParser.range_return retval = new EugeneParser.range_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA238=null;
        EugeneParser.expr_return s =null;

        EugeneParser.expr_return e =null;


        Object COMMA238_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2464:2: (s= expr[defer] COMMA e= expr[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2464:4: s= expr[defer] COMMA e= expr[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_range4525);
            s=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());

            COMMA238=(Token)match(input,COMMA,FOLLOW_COMMA_in_range4528); 
            COMMA238_tree = 
            (Object)adaptor.create(COMMA238)
            ;
            adaptor.addChild(root_0, COMMA238_tree);


            pushFollow(FOLLOW_expr_in_range4532);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2517:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT (id= ID | SIZE ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
    public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
        EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token DOT239=null;
        Token SIZE240=null;
        Token LEFTP241=null;
        Token RIGHTP242=null;
        Token LEFTSBR243=null;
        Token RIGHTSBR244=null;
        EugeneParser.expr_return exp =null;

        EugeneParser.object_access_return o =null;


        Object id_tree=null;
        Object DOT239_tree=null;
        Object SIZE240_tree=null;
        Object LEFTP241_tree=null;
        Object RIGHTP242_tree=null;
        Object LEFTSBR243_tree=null;
        Object RIGHTSBR244_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2519:2: (| ( DOT (id= ID | SIZE ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==COMMA||LA90_0==DIV||LA90_0==EQUALS||LA90_0==GEQUAL||LA90_0==GTHAN||LA90_0==LEQUAL||(LA90_0 >= LTHAN && LA90_0 <= MINUS)||(LA90_0 >= MULT && LA90_0 <= NEQUAL)||LA90_0==PLUS||(LA90_0 >= RIGHTCUR && LA90_0 <= RIGHTSBR)||LA90_0==SEMIC||LA90_0==127||(LA90_0 >= 129 && LA90_0 <= 130)||LA90_0==133||(LA90_0 >= 136 && LA90_0 <= 137)||LA90_0==139||(LA90_0 >= 152 && LA90_0 <= 153)||LA90_0==166||(LA90_0 >= 168 && LA90_0 <= 169)||LA90_0==172||(LA90_0 >= 175 && LA90_0 <= 176)||LA90_0==178||(LA90_0 >= 191 && LA90_0 <= 192)) ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2520:2: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.child = parent;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2525:4: ( DOT (id= ID | SIZE ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2525:4: ( DOT (id= ID | SIZE ( LEFTP RIGHTP )? ) | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2525:5: DOT (id= ID | SIZE ( LEFTP RIGHTP )? )
                            {
                            DOT239=(Token)match(input,DOT,FOLLOW_DOT_in_object_access4568); 
                            DOT239_tree = 
                            (Object)adaptor.create(DOT239)
                            ;
                            adaptor.addChild(root_0, DOT239_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2525:9: (id= ID | SIZE ( LEFTP RIGHTP )? )
                            int alt88=2;
                            int LA88_0 = input.LA(1);

                            if ( (LA88_0==ID) ) {
                                alt88=1;
                            }
                            else if ( (LA88_0==SIZE) ) {
                                alt88=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 88, 0, input);

                                throw nvae;

                            }
                            switch (alt88) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2525:10: id= ID
                                    {
                                    id=(Token)match(input,ID,FOLLOW_ID_in_object_access4573); 
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
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2539:6: SIZE ( LEFTP RIGHTP )?
                                    {
                                    SIZE240=(Token)match(input,SIZE,FOLLOW_SIZE_in_object_access4579); 
                                    SIZE240_tree = 
                                    (Object)adaptor.create(SIZE240)
                                    ;
                                    adaptor.addChild(root_0, SIZE240_tree);


                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2539:11: ( LEFTP RIGHTP )?
                                    int alt87=2;
                                    int LA87_0 = input.LA(1);

                                    if ( (LA87_0==LEFTP) ) {
                                        alt87=1;
                                    }
                                    switch (alt87) {
                                        case 1 :
                                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2539:12: LEFTP RIGHTP
                                            {
                                            LEFTP241=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_object_access4582); 
                                            LEFTP241_tree = 
                                            (Object)adaptor.create(LEFTP241)
                                            ;
                                            adaptor.addChild(root_0, LEFTP241_tree);


                                            RIGHTP242=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_object_access4584); 
                                            RIGHTP242_tree = 
                                            (Object)adaptor.create(RIGHTP242)
                                            ;
                                            adaptor.addChild(root_0, RIGHTP242_tree);


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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2548:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
                            {
                            LEFTSBR243=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access4594); 
                            LEFTSBR243_tree = 
                            (Object)adaptor.create(LEFTSBR243)
                            ;
                            adaptor.addChild(root_0, LEFTSBR243_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2548:12: (exp= expr[defer] )
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2548:13: exp= expr[defer]
                            {
                            pushFollow(FOLLOW_expr_in_object_access4599);
                            exp=expr(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, exp.getTree());

                            }


                            RIGHTSBR244=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access4603); 
                            RIGHTSBR244_tree = 
                            (Object)adaptor.create(RIGHTSBR244)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR244_tree);



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


                    pushFollow(FOLLOW_object_access_in_object_access4610);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2580:1: dynamic_naming[boolean defer] returns [String name] : (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR );
    public final EugeneParser.dynamic_naming_return dynamic_naming(boolean defer) throws RecognitionException {
        EugeneParser.dynamic_naming_return retval = new EugeneParser.dynamic_naming_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token DOLLAR245=null;
        Token LEFTCUR246=null;
        Token RIGHTCUR247=null;
        EugeneParser.expr_return e =null;


        Object i_tree=null;
        Object DOLLAR245_tree=null;
        Object LEFTCUR246_tree=null;
        Object RIGHTCUR247_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2582:2: (i= ID | DOLLAR LEFTCUR e= expr[defer] RIGHTCUR )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2582:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_dynamic_naming4635); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2587:4: DOLLAR LEFTCUR e= expr[defer] RIGHTCUR
                    {
                    root_0 = (Object)adaptor.nil();


                    DOLLAR245=(Token)match(input,DOLLAR,FOLLOW_DOLLAR_in_dynamic_naming4642); 
                    DOLLAR245_tree = 
                    (Object)adaptor.create(DOLLAR245)
                    ;
                    adaptor.addChild(root_0, DOLLAR245_tree);


                    LEFTCUR246=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_dynamic_naming4644); 
                    LEFTCUR246_tree = 
                    (Object)adaptor.create(LEFTCUR246)
                    ;
                    adaptor.addChild(root_0, LEFTCUR246_tree);


                    pushFollow(FOLLOW_expr_in_dynamic_naming4648);
                    e=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, e.getTree());

                    RIGHTCUR247=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_dynamic_naming4651); 
                    RIGHTCUR247_tree = 
                    (Object)adaptor.create(RIGHTCUR247)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR247_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2604:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] );
    public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
        EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.sbolStatement_return s =null;

        EugeneParser.pigeonStatement_return p =null;

        EugeneParser.importStatement_return i =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2606:2: (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] )
            int alt92=3;
            switch ( input.LA(1) ) {
            case SBOL:
                {
                alt92=1;
                }
                break;
            case LC_PIGEON:
            case PIGEON:
                {
                alt92=2;
                }
                break;
            case LC_IMPORT:
            case UC_IMPORT:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2606:4: s= sbolStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_sbolStatement_in_dataExchange4676);
                    s=sbolStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());


                    if(!defer) {
                        retval.e = (s!=null?s.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2611:4: p= pigeonStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pigeonStatement_in_dataExchange4686);
                    p=pigeonStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2612:4: i= importStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_importStatement_in_dataExchange4694);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2623:1: includeStatement[boolean defer] : ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING ;
    public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
        EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token HASHMARK248=null;
        Token set249=null;

        Object file_tree=null;
        Object HASHMARK248_tree=null;
        Object set249_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2624:2: ( ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2624:4: ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2624:4: ( HASHMARK )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==HASHMARK) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2624:5: HASHMARK
                    {
                    HASHMARK248=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement4716); 
                    HASHMARK248_tree = 
                    (Object)adaptor.create(HASHMARK248)
                    ;
                    adaptor.addChild(root_0, HASHMARK248_tree);


                    }
                    break;

            }


            set249=(Token)input.LT(1);

            if ( input.LA(1)==LC_INCLUDE||input.LA(1)==UC_INCLUDE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set249)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement4728); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2636:1: importStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP ;
    public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
        EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token set250=null;
        Token LEFTP251=null;
        Token RIGHTP252=null;

        Object file_tree=null;
        Object set250_tree=null;
        Object LEFTP251_tree=null;
        Object RIGHTP252_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2638:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2638:4: ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set250=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
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


            LEFTP251=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement4755); 
            LEFTP251_tree = 
            (Object)adaptor.create(LEFTP251)
            ;
            adaptor.addChild(root_0, LEFTP251_tree);


            file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement4759); 
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
            	

            RIGHTP252=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement4763); 
            RIGHTP252_tree = 
            (Object)adaptor.create(RIGHTP252)
            ;
            adaptor.addChild(root_0, RIGHTP252_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2650:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) ;
    public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SBOL253=null;
        Token DOT254=null;
        EugeneParser.sbolImportStatement_return i =null;

        EugeneParser.sbolExportStatement_return sbolExportStatement255 =null;


        Object SBOL253_tree=null;
        Object DOT254_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2652:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2652:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            {
            root_0 = (Object)adaptor.nil();


            SBOL253=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement4785); 
            SBOL253_tree = 
            (Object)adaptor.create(SBOL253)
            ;
            adaptor.addChild(root_0, SBOL253_tree);


            DOT254=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement4787); 
            DOT254_tree = 
            (Object)adaptor.create(DOT254)
            ;
            adaptor.addChild(root_0, DOT254_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2652:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==EXPORT) ) {
                alt94=1;
            }
            else if ( (LA94_0==LC_IMPORT||LA94_0==UC_IMPORT) ) {
                alt94=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;

            }
            switch (alt94) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2652:14: sbolExportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement4790);
                    sbolExportStatement255=sbolExportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sbolExportStatement255.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2652:43: i= sbolImportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement4797);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2659:1: sbolExportStatement[boolean defer] : EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
    public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token filenameToken=null;
        Token EXPORT256=null;
        Token LEFTP257=null;
        Token COMMA258=null;
        Token RIGHTP259=null;

        Object idToken_tree=null;
        Object filenameToken_tree=null;
        Object EXPORT256_tree=null;
        Object LEFTP257_tree=null;
        Object COMMA258_tree=null;
        Object RIGHTP259_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2660:2: ( EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2660:4: EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            EXPORT256=(Token)match(input,EXPORT,FOLLOW_EXPORT_in_sbolExportStatement4814); 
            EXPORT256_tree = 
            (Object)adaptor.create(EXPORT256)
            ;
            adaptor.addChild(root_0, EXPORT256_tree);


            LEFTP257=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement4816); 
            LEFTP257_tree = 
            (Object)adaptor.create(LEFTP257)
            ;
            adaptor.addChild(root_0, LEFTP257_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement4820); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            COMMA258=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement4822); 
            COMMA258_tree = 
            (Object)adaptor.create(COMMA258)
            ;
            adaptor.addChild(root_0, COMMA258_tree);


            filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement4826); 
            filenameToken_tree = 
            (Object)adaptor.create(filenameToken)
            ;
            adaptor.addChild(root_0, filenameToken_tree);


            RIGHTP259=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement4828); 
            RIGHTP259_tree = 
            (Object)adaptor.create(RIGHTP259)
            ;
            adaptor.addChild(root_0, RIGHTP259_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2673:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP ;
    public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token fileToken=null;
        Token set260=null;
        Token LEFTP261=null;
        Token RIGHTP262=null;

        Object fileToken_tree=null;
        Object set260_tree=null;
        Object LEFTP261_tree=null;
        Object RIGHTP262_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2675:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2675:4: ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set260=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
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


            LEFTP261=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement4857); 
            LEFTP261_tree = 
            (Object)adaptor.create(LEFTP261)
            ;
            adaptor.addChild(root_0, LEFTP261_tree);


            fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement4861); 
            fileToken_tree = 
            (Object)adaptor.create(fileToken)
            ;
            adaptor.addChild(root_0, fileToken_tree);


            RIGHTP262=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement4863); 
            RIGHTP262_tree = 
            (Object)adaptor.create(RIGHTP262)
            ;
            adaptor.addChild(root_0, RIGHTP262_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2720:1: pigeonStatement[boolean defer] : ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.pigeonStatement_return pigeonStatement(boolean defer) throws RecognitionException {
        EugeneParser.pigeonStatement_return retval = new EugeneParser.pigeonStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token set263=null;
        Token LEFTP264=null;
        Token RIGHTP265=null;

        Object idToken_tree=null;
        Object set263_tree=null;
        Object LEFTP264_tree=null;
        Object RIGHTP265_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2721:2: ( ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2721:4: ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set263=(Token)input.LT(1);

            if ( input.LA(1)==LC_PIGEON||input.LA(1)==PIGEON ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set263)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP264=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_pigeonStatement4892); 
            LEFTP264_tree = 
            (Object)adaptor.create(LEFTP264)
            ;
            adaptor.addChild(root_0, LEFTP264_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_pigeonStatement4896); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP265=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_pigeonStatement4898); 
            RIGHTP265_tree = 
            (Object)adaptor.create(RIGHTP265)
            ;
            adaptor.addChild(root_0, RIGHTP265_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2769:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
    public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
        EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token n=null;
        Token ASSERT266=null;
        Token LEFTP267=null;
        Token DOT268=null;
        Token SIZE269=null;
        Token LEFTP270=null;
        Token RIGHTP271=null;
        Token EQUALS272=null;
        Token EQUALS273=null;
        Token RIGHTP274=null;
        Token NOTE275=null;
        Token LEFTP276=null;
        Token DOT277=null;
        Token SIZE278=null;
        Token LEFTP279=null;
        Token RIGHTP280=null;
        Token EQUALS281=null;
        Token EQUALS282=null;
        Token RIGHTP283=null;

        Object id_tree=null;
        Object n_tree=null;
        Object ASSERT266_tree=null;
        Object LEFTP267_tree=null;
        Object DOT268_tree=null;
        Object SIZE269_tree=null;
        Object LEFTP270_tree=null;
        Object RIGHTP271_tree=null;
        Object EQUALS272_tree=null;
        Object EQUALS273_tree=null;
        Object RIGHTP274_tree=null;
        Object NOTE275_tree=null;
        Object LEFTP276_tree=null;
        Object DOT277_tree=null;
        Object SIZE278_tree=null;
        Object LEFTP279_tree=null;
        Object RIGHTP280_tree=null;
        Object EQUALS281_tree=null;
        Object EQUALS282_tree=null;
        Object RIGHTP283_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2770:2: (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP | NOTE LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2770:5: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2770:7: ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    ASSERT266=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements4922); 
                    ASSERT266_tree = 
                    (Object)adaptor.create(ASSERT266)
                    ;
                    adaptor.addChild(root_0, ASSERT266_tree);


                    LEFTP267=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4924); 
                    LEFTP267_tree = 
                    (Object)adaptor.create(LEFTP267)
                    ;
                    adaptor.addChild(root_0, LEFTP267_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements4928); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT268=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements4930); 
                    DOT268_tree = 
                    (Object)adaptor.create(DOT268)
                    ;
                    adaptor.addChild(root_0, DOT268_tree);


                    SIZE269=(Token)match(input,SIZE,FOLLOW_SIZE_in_testStatements4932); 
                    SIZE269_tree = 
                    (Object)adaptor.create(SIZE269)
                    ;
                    adaptor.addChild(root_0, SIZE269_tree);


                    LEFTP270=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4934); 
                    LEFTP270_tree = 
                    (Object)adaptor.create(LEFTP270)
                    ;
                    adaptor.addChild(root_0, LEFTP270_tree);


                    RIGHTP271=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4936); 
                    RIGHTP271_tree = 
                    (Object)adaptor.create(RIGHTP271)
                    ;
                    adaptor.addChild(root_0, RIGHTP271_tree);


                    EQUALS272=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4938); 
                    EQUALS272_tree = 
                    (Object)adaptor.create(EQUALS272)
                    ;
                    adaptor.addChild(root_0, EQUALS272_tree);


                    EQUALS273=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4940); 
                    EQUALS273_tree = 
                    (Object)adaptor.create(EQUALS273)
                    ;
                    adaptor.addChild(root_0, EQUALS273_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements4944); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP274=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4946); 
                    RIGHTP274_tree = 
                    (Object)adaptor.create(RIGHTP274)
                    ;
                    adaptor.addChild(root_0, RIGHTP274_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2786:5: NOTE LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    NOTE275=(Token)match(input,NOTE,FOLLOW_NOTE_in_testStatements4954); 
                    NOTE275_tree = 
                    (Object)adaptor.create(NOTE275)
                    ;
                    adaptor.addChild(root_0, NOTE275_tree);


                    LEFTP276=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4956); 
                    LEFTP276_tree = 
                    (Object)adaptor.create(LEFTP276)
                    ;
                    adaptor.addChild(root_0, LEFTP276_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements4960); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT277=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements4962); 
                    DOT277_tree = 
                    (Object)adaptor.create(DOT277)
                    ;
                    adaptor.addChild(root_0, DOT277_tree);


                    SIZE278=(Token)match(input,SIZE,FOLLOW_SIZE_in_testStatements4964); 
                    SIZE278_tree = 
                    (Object)adaptor.create(SIZE278)
                    ;
                    adaptor.addChild(root_0, SIZE278_tree);


                    LEFTP279=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4966); 
                    LEFTP279_tree = 
                    (Object)adaptor.create(LEFTP279)
                    ;
                    adaptor.addChild(root_0, LEFTP279_tree);


                    RIGHTP280=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4968); 
                    RIGHTP280_tree = 
                    (Object)adaptor.create(RIGHTP280)
                    ;
                    adaptor.addChild(root_0, RIGHTP280_tree);


                    EQUALS281=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4970); 
                    EQUALS281_tree = 
                    (Object)adaptor.create(EQUALS281)
                    ;
                    adaptor.addChild(root_0, EQUALS281_tree);


                    EQUALS282=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4972); 
                    EQUALS282_tree = 
                    (Object)adaptor.create(EQUALS282)
                    ;
                    adaptor.addChild(root_0, EQUALS282_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements4976); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP283=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4978); 
                    RIGHTP283_tree = 
                    (Object)adaptor.create(RIGHTP283)
                    ;
                    adaptor.addChild(root_0, RIGHTP283_tree);



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

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_prog936 = new BitSet(new long[]{0x000287C5A0604F40L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_EOF_in_prog941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeStatement_in_statement967 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_statement978 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_printStatement_in_statement987 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement995 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement1003 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_statement1013 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imperativeStatements_in_statement1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predefined_statements_in_statement1029 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_statement1032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testStatements_in_predefined_statements1045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_predefined_statements1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_built_in_function_in_predefined_statements1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instantiation_in_declarationStatement1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_grammarDeclaration_in_declarationStatement1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement1135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1153 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_numdecl_in_variableDeclaration1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1168 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_txtdecl_in_variableDeclaration1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1183 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1187 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1202 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1206 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_variableDeclaration1221 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_booldecl_in_variableDeclaration1229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1252 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1258 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1268 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_numdecl1270 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_numdecl1275 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1283 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1305 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1312 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1325 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_txtdecl1327 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_txtdecl1331 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1339 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1361 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1368 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1380 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1382 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_txtlistdecl1388 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1396 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1418 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1425 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1437 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_numlistdecl1439 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_numlistdecl1444 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1452 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1474 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_booldecl1481 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_booldecl_in_booldecl1483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1493 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_booldecl1495 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_booldecl1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1517 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration1521 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1523 = new BitSet(new long[]{0x0000000000000600L,0x0000000800000002L});
    public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1555 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1574 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_propertyType1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_typeDeclaration1614 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_typeDeclaration1619 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1622 = new BitSet(new long[]{0x0000000100000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_partTypeDeclaration1651 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_partTypeDeclaration1660 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1663 = new BitSet(new long[]{0x0000000100000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1700 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1707 = new BitSet(new long[]{0x0010000100000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1710 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1712 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_containerDeclaration1720 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1725 = new BitSet(new long[]{0x0818000523024E40L,0x0000001F78E8F01EL});
    public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1728 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1766 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr_in_list_of_declarations1773 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_declarations1781 = new BitSet(new long[]{0x0818000523024E40L,0x0000001F78E0F01EL});
    public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations1785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instantiation1813 = new BitSet(new long[]{0x0000000100020000L});
    public static final BitSet FOLLOW_dynamic_naming_in_instantiation1819 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_instantiation1826 = new BitSet(new long[]{0x0818000103060000L,0x0000000778C8E004L});
    public static final BitSet FOLLOW_listOfDotValues_in_instantiation1831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_listOfValues_in_instantiation1836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_instantiation1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1864 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1868 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1874 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1886 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfDotValues1889 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1891 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1895 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1903 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1917 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr_in_listOfValues1938 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfValues1944 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_listOfValues1950 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration1969 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_deviceDeclaration1973 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration1976 = new BitSet(new long[]{0x0810000100000000L,0x0000000000080100L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration1981 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration1986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_in_deviceComponents2017 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_deviceComponents2023 = new BitSet(new long[]{0x0810000100000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceComponents2027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_selection2051 = new BitSet(new long[]{0x0800000100000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_selection_list_in_selection2055 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_selection2058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection2067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection_list2096 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_PIPE_in_selection_list2102 = new BitSet(new long[]{0x0800000100000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_selection_list_in_selection_list2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_device_component2132 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_device_component2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_assignment_in_assignment2162 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2165 = new BitSet(new long[]{0x0818820103020020L,0x0000080779C0E864L});
    public static final BitSet FOLLOW_AMP_in_assignment2170 = new BitSet(new long[]{0x0818820103020000L,0x0000080779C0E864L});
    public static final BitSet FOLLOW_rhs_assignment_in_assignment2176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lhs_assignment2191 = new BitSet(new long[]{0x0010000000040000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lhs_access2213 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_lhs_access2217 = new BitSet(new long[]{0x0010000000040000L});
    public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2221 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_set_in_lhs_access2223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2229 = new BitSet(new long[]{0x0010000000040000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_access2232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_rhs_assignment2255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_rhs_assignment2275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_listOfIDs2303 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfIDs2312 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_ruleDeclaration2340 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2344 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2346 = new BitSet(new long[]{0x0919380100000000L,0xFFE2E00100008004L,0x7FFFFFFFFEFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_set_in_ruleDeclaration2351 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2359 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_ruleDeclaration2361 = new BitSet(new long[]{0x0919180100000000L,0xFFE2600100008004L,0x7FFFFFFFFEFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2369 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2767 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQUAL_in_relationalOperators2774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTHAN_in_relationalOperators2779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GTHAN_in_relationalOperators2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEQUAL_in_relationalOperators2789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GEQUAL_in_relationalOperators2794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_predicate_in_cnf_rule2895 = new BitSet(new long[]{0x0080000800000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_set_in_cnf_rule2903 = new BitSet(new long[]{0x0919180100000000L,0xFFE2600100008004L,0x7FFFFFFFFEFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_cnf_rule_in_cnf_rule2913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2943 = new BitSet(new long[]{0x0200400000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_set_in_or_predicate2949 = new BitSet(new long[]{0x0919180100000000L,0xFFE2600100008004L,0x7FFFFFFFFEFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2959 = new BitSet(new long[]{0x0200400000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_set_in_negated_predicate2987 = new BitSet(new long[]{0x0819080100000000L,0xFFE2200100008004L,0x7FFFFFFFFEFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_predicate_in_negated_predicate2997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_negated_predicate3007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_predicate3034 = new BitSet(new long[]{0x0001080000000000L,0xFFE2200000000000L,0x7FFFFFFFFEFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_ruleOperator_in_predicate3044 = new BitSet(new long[]{0x0010000100000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_operand_in_predicate3053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_predicate3067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionRule_in_predicate3076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand3107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_operand3116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_operand3123 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_NUMBER_in_operand3127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_operand3129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionRule3155 = new BitSet(new long[]{0x4420000050100000L,0x8000000000000000L,0x8005934003000B26L,0x0000000000000001L});
    public static final BitSet FOLLOW_exp_op_in_expressionRule3160 = new BitSet(new long[]{0x0808000100000000L,0x0000000100008004L});
    public static final BitSet FOLLOW_expression_in_expressionRule3165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_operand_in_expression3189 = new BitSet(new long[]{0x2800000000010002L,0x0000000000000100L});
    public static final BitSet FOLLOW_exp_operator_in_expression3198 = new BitSet(new long[]{0x0808000100000000L,0x0000000100008004L});
    public static final BitSet FOLLOW_expression_in_expression3203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_expression3215 = new BitSet(new long[]{0x0808000100000000L,0x0000000100008004L});
    public static final BitSet FOLLOW_expression_in_expression3217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_expression3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp_operator3239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operator3247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_exp_operator3254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_exp_operator3261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_exp_operand3291 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_exp_operand3293 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_exp_operand3302 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3308 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3312 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3314 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp_operand3366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOperators_in_exp_op3393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GRAMMAR_in_grammarDeclaration3412 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_grammarDeclaration3416 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_grammarDeclaration3418 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_list_of_production_rules_in_grammarDeclaration3420 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_grammarDeclaration3423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_production_rule_in_list_of_production_rules3435 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_list_of_production_rules3438 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_list_of_production_rules_in_list_of_production_rules3441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_production_rule3461 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ARROW_in_production_rule3465 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_right_hand_side_in_production_rule3467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_right_hand_side3483 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_right_hand_side3488 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_right_hand_side_in_right_hand_side3490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_right_hand_side3498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3531 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_interactionDeclaration3535 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3537 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3541 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3567 = new BitSet(new long[]{0x0001080000000000L,0x0002200000000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3571 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_interaction3576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3585 = new BitSet(new long[]{0x0001080000000000L,0x0002200000000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3589 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interaction3592 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_interaction_in_interaction3596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_interaction3599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRODUCT_in_functionCall3666 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_PERMUTE_in_functionCall3670 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_functionCall3673 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_functionCall3677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_functionCall3679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINTLN_in_printStatement3697 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3699 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_printStatement3713 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3715 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_toPrint3743 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_toPrint_prime_in_toPrint3748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_toPrint_prime3774 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_toPrint_in_toPrint_prime3778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_imperativeStatements3798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements3804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_imperativeStatements3810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_imperativeStatements3816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ifStatement3842 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3848 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3855 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3857 = new BitSet(new long[]{0x000287C5A0604F40L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3868 = new BitSet(new long[]{0x0000003000000002L,0x000000C000000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3883 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3889 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3896 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3898 = new BitSet(new long[]{0x000287C5A0604F40L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3909 = new BitSet(new long[]{0x0000003000000002L,0x000000C000000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3925 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3931 = new BitSet(new long[]{0x000287C5A0604F40L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3939 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_imp_condition3966 = new BitSet(new long[]{0x4420000050100000L,0x8000000000000000L,0x8005934003000B26L,0x0000000000000001L});
    public static final BitSet FOLLOW_relationalOperators_in_imp_condition3971 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_imp_condition3975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_forall_iterator3991 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator4000 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_forall_iterator4002 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator4008 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator4010 = new BitSet(new long[]{0x000287C5A0604F40L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_listOfStatements_in_forall_iterator4015 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator4022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_for_loop4034 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_for_loop4040 = new BitSet(new long[]{0x0000000000000600L,0x0000000800000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_for_loop4044 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop4047 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_imp_condition_in_for_loop4051 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop4054 = new BitSet(new long[]{0x0000000100000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_assignment_in_for_loop4059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_for_loop4064 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_for_loop4066 = new BitSet(new long[]{0x000287C5A0604F40L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_listOfStatements_in_for_loop4074 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_for_loop4081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_while_loop4095 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_while_loop4101 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_imp_condition_in_while_loop4105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_while_loop4108 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_while_loop4110 = new BitSet(new long[]{0x000287C5A0604F40L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_listOfStatements_in_while_loop4118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_while_loop4125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_listOfStatements4142 = new BitSet(new long[]{0x000287C5A0604F42L,0x00041F1879E07E7BL});
    public static final BitSet FOLLOW_multExpr_in_expr4170 = new BitSet(new long[]{0x0800000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_set_in_expr4179 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_multExpr_in_expr4187 = new BitSet(new long[]{0x0800000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_atom_in_multExpr4217 = new BitSet(new long[]{0x2000000000010002L});
    public static final BitSet FOLLOW_MULT_in_multExpr4228 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_DIV_in_multExpr4232 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_atom_in_multExpr4237 = new BitSet(new long[]{0x2000000000010002L});
    public static final BitSet FOLLOW_NUMBER_in_atom4264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom4280 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_NUMBER_in_atom4285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dynamic_naming_in_atom4330 = new BitSet(new long[]{0x0010000000040000L});
    public static final BitSet FOLLOW_object_access_in_atom4337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_atom4354 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_atom4356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_atom4359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_atom4368 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_list_in_atom4370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_atom4373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_built_in_function_in_atom4384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_list4407 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list4414 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_list4418 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_set_in_built_in_function4446 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4452 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_built_in_function4456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_built_in_function4466 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4472 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_range_in_built_in_function4476 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_built_in_function4486 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_built_in_function4496 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_built_in_function4500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_built_in_function4503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_range4525 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_range4528 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_range4532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_object_access4568 = new BitSet(new long[]{0x0000000100000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_object_access4573 = new BitSet(new long[]{0x0010000000040000L});
    public static final BitSet FOLLOW_SIZE_in_object_access4579 = new BitSet(new long[]{0x0018000000040000L});
    public static final BitSet FOLLOW_LEFTP_in_object_access4582 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_object_access4584 = new BitSet(new long[]{0x0010000000040000L});
    public static final BitSet FOLLOW_LEFTSBR_in_object_access4594 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_object_access4599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_object_access4603 = new BitSet(new long[]{0x0010000000040000L});
    public static final BitSet FOLLOW_object_access_in_object_access4610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_dynamic_naming4635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOLLAR_in_dynamic_naming4642 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_dynamic_naming4644 = new BitSet(new long[]{0x0818000103020000L,0x0000000778C0E004L});
    public static final BitSet FOLLOW_expr_in_dynamic_naming4648 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_dynamic_naming4651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolStatement_in_dataExchange4676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pigeonStatement_in_dataExchange4686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importStatement_in_dataExchange4694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASHMARK_in_includeStatement4716 = new BitSet(new long[]{0x0000040000000000L,0x0000100000000000L});
    public static final BitSet FOLLOW_set_in_includeStatement4720 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_STRING_in_includeStatement4728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_importStatement4749 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_importStatement4755 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_STRING_in_importStatement4759 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_importStatement4763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBOL_in_sbolStatement4785 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_sbolStatement4787 = new BitSet(new long[]{0x0000020000800000L,0x0000080000000000L});
    public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement4790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement4797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPORT_in_sbolExportStatement4814 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement4816 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_sbolExportStatement4820 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_sbolExportStatement4822 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_STRING_in_sbolExportStatement4826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement4828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolImportStatement4851 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement4857 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_STRING_in_sbolImportStatement4861 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement4863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_pigeonStatement4886 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_pigeonStatement4892 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_pigeonStatement4896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_pigeonStatement4898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_testStatements4922 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4924 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_testStatements4928 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_testStatements4930 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SIZE_in_testStatements4932 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4936 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4938 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4940 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements4944 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTE_in_testStatements4954 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4956 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ID_in_testStatements4960 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DOT_in_testStatements4962 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SIZE_in_testStatements4964 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4966 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4968 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4970 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4972 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements4976 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4978 = new BitSet(new long[]{0x0000000000000002L});

}