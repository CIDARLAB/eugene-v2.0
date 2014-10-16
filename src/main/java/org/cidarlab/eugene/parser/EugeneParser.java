// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g 2014-10-16 09:44:27

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
import org.cidarlab.eugene.dom.imp.loops.ForLoop;

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADDPROPS", "AMP", "ARRAY", "ARROW", "ASSERT", "BOOL", "BOOLEAN", "COLLECTION", "COLON", "COMMA", "DEVICE", "DIGIT", "DIV", "DOT", "EQUALS", "EXIT_LC", "EXIT_UC", "EXPORT", "FALSE_LC", "FALSE_UC", "FLEXIBLE", "GENBANK", "GEQUAL", "GRAMMAR", "GTHAN", "HASHMARK", "ID", "IMAGE", "INT", "INTERACTION", "LC_AND", "LC_ELSE", "LC_ELSEIF", "LC_FOR", "LC_FORALL", "LC_IF", "LC_IMPORT", "LC_INCLUDE", "LC_INDUCES", "LC_NOT", "LC_ON", "LC_OR", "LC_PIGEON", "LC_REPRESSES", "LEFTCUR", "LEFTP", "LEFTSBR", "LEQUAL", "LINE_COMMENT", "LOG_AND", "LOG_NOT", "LOG_OR", "LTHAN", "MINUS", "ML_COMMENT", "MULT", "NEQUAL", "NEWLINE", "NOTE", "NUM", "NUMBER", "PART", "PART_TYPE", "PERMUTE", "PIGEON", "PIPE", "PLUS", "PRINT", "PRINTLN", "PRODUCT", "PROPERTY", "REAL", "REF", "REGISTRY", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "RULE", "SBOL", "SEMIC", "SIZE", "STRICT", "STRING", "TRUE_LC", "TRUE_UC", "TXT", "TYPE", "UC_AND", "UC_ELSE", "UC_ELSEIF", "UC_FOR", "UC_FORALL", "UC_IF", "UC_IMPORT", "UC_INCLUDE", "UC_INDUCES", "UC_NOT", "UC_ON", "UC_OR", "UC_REPRESSES", "UNDERS", "WS", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", "'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'MATCHES'", "'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", "'SOUNDSLIKE'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'reverse'", "'same_count'", "'same_orientation'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", "'soundslike'", "'startswith'", "'then'", "'with'"
    };

    public static final int EOF=-1;
    public static final int T__106=106;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__110=110;
    public static final int T__111=111;
    public static final int T__112=112;
    public static final int T__113=113;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__116=116;
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
    public static final int DOT=17;
    public static final int EQUALS=18;
    public static final int EXIT_LC=19;
    public static final int EXIT_UC=20;
    public static final int EXPORT=21;
    public static final int FALSE_LC=22;
    public static final int FALSE_UC=23;
    public static final int FLEXIBLE=24;
    public static final int GENBANK=25;
    public static final int GEQUAL=26;
    public static final int GRAMMAR=27;
    public static final int GTHAN=28;
    public static final int HASHMARK=29;
    public static final int ID=30;
    public static final int IMAGE=31;
    public static final int INT=32;
    public static final int INTERACTION=33;
    public static final int LC_AND=34;
    public static final int LC_ELSE=35;
    public static final int LC_ELSEIF=36;
    public static final int LC_FOR=37;
    public static final int LC_FORALL=38;
    public static final int LC_IF=39;
    public static final int LC_IMPORT=40;
    public static final int LC_INCLUDE=41;
    public static final int LC_INDUCES=42;
    public static final int LC_NOT=43;
    public static final int LC_ON=44;
    public static final int LC_OR=45;
    public static final int LC_PIGEON=46;
    public static final int LC_REPRESSES=47;
    public static final int LEFTCUR=48;
    public static final int LEFTP=49;
    public static final int LEFTSBR=50;
    public static final int LEQUAL=51;
    public static final int LINE_COMMENT=52;
    public static final int LOG_AND=53;
    public static final int LOG_NOT=54;
    public static final int LOG_OR=55;
    public static final int LTHAN=56;
    public static final int MINUS=57;
    public static final int ML_COMMENT=58;
    public static final int MULT=59;
    public static final int NEQUAL=60;
    public static final int NEWLINE=61;
    public static final int NOTE=62;
    public static final int NUM=63;
    public static final int NUMBER=64;
    public static final int PART=65;
    public static final int PART_TYPE=66;
    public static final int PERMUTE=67;
    public static final int PIGEON=68;
    public static final int PIPE=69;
    public static final int PLUS=70;
    public static final int PRINT=71;
    public static final int PRINTLN=72;
    public static final int PRODUCT=73;
    public static final int PROPERTY=74;
    public static final int REAL=75;
    public static final int REF=76;
    public static final int REGISTRY=77;
    public static final int RIGHTCUR=78;
    public static final int RIGHTP=79;
    public static final int RIGHTSBR=80;
    public static final int RULE=81;
    public static final int SBOL=82;
    public static final int SEMIC=83;
    public static final int SIZE=84;
    public static final int STRICT=85;
    public static final int STRING=86;
    public static final int TRUE_LC=87;
    public static final int TRUE_UC=88;
    public static final int TXT=89;
    public static final int TYPE=90;
    public static final int UC_AND=91;
    public static final int UC_ELSE=92;
    public static final int UC_ELSEIF=93;
    public static final int UC_FOR=94;
    public static final int UC_FORALL=95;
    public static final int UC_IF=96;
    public static final int UC_IMPORT=97;
    public static final int UC_INCLUDE=98;
    public static final int UC_INDUCES=99;
    public static final int UC_NOT=100;
    public static final int UC_ON=101;
    public static final int UC_OR=102;
    public static final int UC_REPRESSES=103;
    public static final int UNDERS=104;
    public static final int WS=105;

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
                printError(e.toString());
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
        printError(re.toString());
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
    			    printError(ee.toString());
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
    			            printError(ee.toString());
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
    			            printError(ee.toString());
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
    			            printError(ee.toString());
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
    			            printError(ee.toString());
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
    			            printError(ee.toString());
    			        }
    			} else {
    				printError("Type mismatch. Type is " + prim.type + " but must be numList.");
    			}
    		}
    	}
    	
    	//copies the values of a primitive to a newly created Primitive
    	public Variable copyVariable(Variable source) {
    		Variable destination = new Variable();
    		destination.index = source.index;
    		if (source.type.equals(EugeneConstants.NUM)) {
    			destination.type = EugeneConstants.NUM;
    			destination.num = source.num;
    		} else if (source.type.equals(EugeneConstants.TXT)) {
    			destination.type = EugeneConstants.TXT;
    			destination.txt = source.txt;
    		} else if (source.type.equals(EugeneConstants.NUMLIST)) {
    			destination.type = EugeneConstants.NUMLIST;
    			destination.numList.clear();
    			destination.numList.addAll(source.numList);
    		} else if (source.type.equals(EugeneConstants.TXTLIST)) {
    			destination.type = EugeneConstants.TXTLIST;
    			destination.txtList.clear();
    			destination.txtList.addAll(source.txtList);
    		} else if (source.type.equals(EugeneConstants.BOOLEAN)) {
    			destination.type = EugeneConstants.BOOLEAN;
    			destination.bool = source.bool;
    		}
    		return destination;
    	}
    	
    	
        public void addToPropertyListHolder(String prop) 
                throws EugeneException {
            if(!this.interp.contains(prop)) {
                printError("Property " + prop + " does not exist.");
            } else {
                NamedElement ne = this.interp.get(prop);
                if(!(ne instanceof Property)) {
                    printError(prop+" is not a property.");
                }
        			
                if(((Property)ne).getType().equals(EugeneConstants.TXTLIST)) {
                    typeList = EugeneConstants.TXT;
                } else if(((Property)ne).getType().equals(EugeneConstants.NUMLIST)) {
                    typeList = EugeneConstants.NUM;
                }
         			
                propertyListHolder.add(prop);
            }
        }
    	
        //adds values to the corresponding property
        public void addToPropertyValuesHolder(String prop, Variable p, int index) 
                throws EugeneException {
            NamedElement ne = this.interp.get(prop);
            if(ne instanceof Property) {
                if (((Property)ne).getType().equals(p.type)) {
                    propertyValuesHolder.add(p);
                } else if(p.type.equals(EugeneConstants.TXTLIST) && index != -1 && p.type.equals(EugeneConstants.TXT)) {
                    p.index = index;
                    propertyValuesHolder.add(p);
                } else if(p.type.equals(EugeneConstants.NUMLIST) && index != -1 && p.type.equals(EugeneConstants.NUM)) {
                    p.index = index;
                    propertyValuesHolder.add(p);
                } else {
                    printError("Expected a value of type " + ((Property)ne).getType() + " for property " + prop + ".");
                }
            } else {
                printError("The named element " + prop + " is not a property.");
            }
        }
        	
    /*---------------------------------------------------------------------
     * METHODS FOR ERROR REPORTING
     *---------------------------------------------------------------------*/
    public void printDebug(Object message) {
        if (debug) {
            int line = input.LT(-1).getLine();
            System.out.println("@Debug Line " + line + ": " + message);
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
    //       throw new EugeneReturnException(ere.toString());
        } catch (Exception e) {
            throw new EugeneException(e.toString());
        }
        finally { 
            // restore location
            this.input.seek(oldPosition); 
        }
        return rv;
    }
     
     
        public void for_loop(Token initStart, Token condStart, Token incStart, Token forStart) 
                throws EugeneException, EugeneReturnException {

            /*
             * save the current parsing position
             * (i.e. after the for loop)
             */
            int oldPosition = this.input.index(); 

            /*
             * execute the declaration statement
             */    
            variableDeclaration_return vdr = 
                 (variableDeclaration_return)this.exec(
                     "variableDeclaration", 
                     initStart.getTokenIndex());    

            /*
             * create a new ForLoop object
             */
            ForLoop fl = new ForLoop();
            if(null != vdr) {
                fl.setVarname(vdr.varname);
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
                this.interp.push(fl);
                    
                /*
                 *   execute the statements
                 */
                this.exec(
                    "listOfStatements", 
                    forStart.getTokenIndex()); 
                 
                /*
                 * pop the ForLoop statement
                 */
                this.interp.pop();
                
                /*
                 *   do the assignment statement
                 */
                this.exec(
                    "assignment", 
                    incStart.getTokenIndex()); 
                 
                /*
                 *    evaluate the condition again
                 */ 
                ret = (imp_condition_return)this.exec(
                                       "imp_condition",         
                                       condStart.getTokenIndex());  
            }
            
            /*
             * lastly, we need to get rid of the 
             * iteration variable (e.g. num i)
             */
            this.interp.removeVariable(
                fl.getVarname());
            
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:713:1: prog : ( statement[false] )+ EOF !;
    public final EugeneParser.prog_return prog() throws RecognitionException {
        EugeneParser.prog_return retval = new EugeneParser.prog_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF2=null;
        EugeneParser.statement_return statement1 =null;


        Object EOF2_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:719:2: ( ( statement[false] )+ EOF !)
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:719:4: ( statement[false] )+ EOF !
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:719:4: ( statement[false] )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ARRAY||(LA1_0 >= ASSERT && LA1_0 <= COLLECTION)||LA1_0==DEVICE||(LA1_0 >= EXIT_LC && LA1_0 <= EXIT_UC)||LA1_0==GRAMMAR||(LA1_0 >= HASHMARK && LA1_0 <= ID)||LA1_0==INTERACTION||(LA1_0 >= LC_FOR && LA1_0 <= LC_INCLUDE)||LA1_0==LC_PIGEON||LA1_0==NUM||(LA1_0 >= PART && LA1_0 <= PIGEON)||(LA1_0 >= PRINT && LA1_0 <= PROPERTY)||(LA1_0 >= RULE && LA1_0 <= SEMIC)||(LA1_0 >= TXT && LA1_0 <= TYPE)||(LA1_0 >= UC_FOR && LA1_0 <= UC_INCLUDE)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:719:5: statement[false]
            	    {
            	    pushFollow(FOLLOW_statement_in_prog840);
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


            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_prog845); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:723:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:725:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC )
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

                if ( (LA3_3==ID||LA3_3==LC_INDUCES||LA3_3==LC_REPRESSES||LA3_3==UC_INDUCES||LA3_3==UC_REPRESSES) ) {
                    alt3=2;
                }
                else if ( ((LA3_3 >= DOT && LA3_3 <= EQUALS)||LA3_3==LEFTSBR) ) {
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
            case UC_FOR:
            case UC_FORALL:
            case UC_IF:
                {
                alt3=7;
                }
                break;
            case ASSERT:
            case EXIT_LC:
            case EXIT_UC:
            case SEMIC:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:726:3: includeStatement[defer] ( SEMIC )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_includeStatement_in_statement871);
                    includeStatement3=includeStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, includeStatement3.getTree());

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:726:27: ( SEMIC )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==SEMIC) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:726:28: SEMIC
                            {
                            SEMIC4=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement875); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:727:4: declarationStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declarationStatement_in_statement882);
                    declarationStatement5=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, declarationStatement5.getTree());

                    SEMIC6=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement885); 
                    SEMIC6_tree = 
                    (Object)adaptor.create(SEMIC6)
                    ;
                    adaptor.addChild(root_0, SEMIC6_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:728:4: printStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_printStatement_in_statement891);
                    printStatement7=printStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, printStatement7.getTree());

                    SEMIC8=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement894); 
                    SEMIC8_tree = 
                    (Object)adaptor.create(SEMIC8)
                    ;
                    adaptor.addChild(root_0, SEMIC8_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:729:4: assignment[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_statement899);
                    assignment9=assignment(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, assignment9.getTree());

                    SEMIC10=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement902); 
                    SEMIC10_tree = 
                    (Object)adaptor.create(SEMIC10)
                    ;
                    adaptor.addChild(root_0, SEMIC10_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:730:4: functionCall[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_statement907);
                    functionCall11=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, functionCall11.getTree());

                    SEMIC12=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement910); 
                    SEMIC12_tree = 
                    (Object)adaptor.create(SEMIC12)
                    ;
                    adaptor.addChild(root_0, SEMIC12_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:731:4: de= dataExchange[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_statement917);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());

                    SEMIC13=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement920); 
                    SEMIC13_tree = 
                    (Object)adaptor.create(SEMIC13)
                    ;
                    adaptor.addChild(root_0, SEMIC13_tree);



                    if(!defer) {
                        try {
                            System.out.println("[Parser.dataExchange] -> " + (de!=null?de.e:null));
                            this.interp.put((de!=null?de.e:null));
                        } catch(EugeneException ee) {
                            printError(ee.toString());
                        }
                    }	
                    	

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:741:4: imperativeStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_imperativeStatements_in_statement927);
                    imperativeStatements14=imperativeStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, imperativeStatements14.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:742:4: predefined_statements[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_predefined_statements_in_statement933);
                    predefined_statements15=predefined_statements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, predefined_statements15.getTree());

                    SEMIC16=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement936); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:745:1: predefined_statements[boolean defer] : ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) );
    public final EugeneParser.predefined_statements_return predefined_statements(boolean defer) throws RecognitionException {
        EugeneParser.predefined_statements_return retval = new EugeneParser.predefined_statements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set18=null;
        EugeneParser.testStatements_return testStatements17 =null;


        Object set18_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:746:2: ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ASSERT||LA4_0==SEMIC) ) {
                alt4=1;
            }
            else if ( ((LA4_0 >= EXIT_LC && LA4_0 <= EXIT_UC)) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:746:4: testStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_testStatements_in_predefined_statements949);
                    testStatements17=testStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, testStatements17.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:747:4: ( EXIT_LC | EXIT_UC )
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

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:755:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] );
    public final EugeneParser.declarationStatement_return declarationStatement(boolean defer) throws RecognitionException {
        EugeneParser.declarationStatement_return retval = new EugeneParser.declarationStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.variableDeclaration_return v =null;

        EugeneParser.containerDeclaration_return containerDeclaration19 =null;

        EugeneParser.propertyDeclaration_return propertyDeclaration20 =null;

        EugeneParser.typeDeclaration_return typeDeclaration21 =null;

        EugeneParser.instantiation_return instantiation22 =null;

        EugeneParser.interactionDeclaration_return interactionDeclaration23 =null;

        EugeneParser.ruleDeclaration_return ruleDeclaration24 =null;

        EugeneParser.grammarDeclaration_return grammarDeclaration25 =null;

        EugeneParser.deviceDeclaration_return deviceDeclaration26 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:757:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] )
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

                if ( (LA5_5==ID) ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:757:4: v= variableDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_variableDeclaration_in_declarationStatement983);
                    v=variableDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, v.getTree());


                    if(!defer) {
                        retval.name = (v!=null?v.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:762:4: containerDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_containerDeclaration_in_declarationStatement991);
                    containerDeclaration19=containerDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, containerDeclaration19.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:763:4: propertyDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement997);
                    propertyDeclaration20=propertyDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, propertyDeclaration20.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:764:4: typeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeDeclaration_in_declarationStatement1003);
                    typeDeclaration21=typeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, typeDeclaration21.getTree());

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:765:4: instantiation[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_instantiation_in_declarationStatement1009);
                    instantiation22=instantiation(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, instantiation22.getTree());

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:766:4: interactionDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement1015);
                    interactionDeclaration23=interactionDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, interactionDeclaration23.getTree());

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:767:4: ruleDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement1021);
                    ruleDeclaration24=ruleDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ruleDeclaration24.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:768:4: grammarDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_grammarDeclaration_in_declarationStatement1027);
                    grammarDeclaration25=grammarDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, grammarDeclaration25.getTree());

                    }
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:769:4: deviceDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement1033);
                    deviceDeclaration26=deviceDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, deviceDeclaration26.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:772:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] );
    public final EugeneParser.variableDeclaration_return variableDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.variableDeclaration_return retval = new EugeneParser.variableDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NUM27=null;
        Token TXT28=null;
        Token TXT29=null;
        Token LEFTSBR30=null;
        Token RIGHTSBR31=null;
        Token NUM32=null;
        Token LEFTSBR33=null;
        Token RIGHTSBR34=null;
        Token set35=null;
        EugeneParser.numdecl_return n =null;

        EugeneParser.txtdecl_return t =null;

        EugeneParser.txtlistdecl_return tl =null;

        EugeneParser.numlistdecl_return nl =null;

        EugeneParser.booldecl_return b =null;


        Object NUM27_tree=null;
        Object TXT28_tree=null;
        Object TXT29_tree=null;
        Object LEFTSBR30_tree=null;
        Object RIGHTSBR31_tree=null;
        Object NUM32_tree=null;
        Object LEFTSBR33_tree=null;
        Object RIGHTSBR34_tree=null;
        Object set35_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:774:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | ( BOOLEAN | BOOL ) b= booldecl[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:774:4: NUM n= numdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM27=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1051); 
                    NUM27_tree = 
                    (Object)adaptor.create(NUM27)
                    ;
                    adaptor.addChild(root_0, NUM27_tree);


                    pushFollow(FOLLOW_numdecl_in_variableDeclaration1055);
                    n=numdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, n.getTree());


                    if(!defer) {
                        retval.varname = (n!=null?n.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:779:4: TXT t= txtdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT28=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1066); 
                    TXT28_tree = 
                    (Object)adaptor.create(TXT28)
                    ;
                    adaptor.addChild(root_0, TXT28_tree);


                    pushFollow(FOLLOW_txtdecl_in_variableDeclaration1070);
                    t=txtdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t.getTree());


                    if(!defer) {
                        retval.varname = (t!=null?t.varname:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:784:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT29=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1081); 
                    TXT29_tree = 
                    (Object)adaptor.create(TXT29)
                    ;
                    adaptor.addChild(root_0, TXT29_tree);


                    LEFTSBR30=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1083); 
                    LEFTSBR30_tree = 
                    (Object)adaptor.create(LEFTSBR30)
                    ;
                    adaptor.addChild(root_0, LEFTSBR30_tree);


                    RIGHTSBR31=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1085); 
                    RIGHTSBR31_tree = 
                    (Object)adaptor.create(RIGHTSBR31)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR31_tree);


                    pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration1089);
                    tl=txtlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tl.getTree());


                    if(!defer) {
                        retval.varname = (tl!=null?tl.varname:null);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:789:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM32=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1100); 
                    NUM32_tree = 
                    (Object)adaptor.create(NUM32)
                    ;
                    adaptor.addChild(root_0, NUM32_tree);


                    LEFTSBR33=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1102); 
                    LEFTSBR33_tree = 
                    (Object)adaptor.create(LEFTSBR33)
                    ;
                    adaptor.addChild(root_0, LEFTSBR33_tree);


                    RIGHTSBR34=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1104); 
                    RIGHTSBR34_tree = 
                    (Object)adaptor.create(RIGHTSBR34)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR34_tree);


                    pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1108);
                    nl=numlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, nl.getTree());


                    if(!defer) {
                        retval.varname = (nl!=null?nl.varname:null);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:794:4: ( BOOLEAN | BOOL ) b= booldecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    set35=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set35)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_booldecl_in_variableDeclaration1127);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:801:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
    public final EugeneParser.numdecl_return numdecl(boolean defer) throws RecognitionException {
        EugeneParser.numdecl_return retval = new EugeneParser.numdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID36=null;
        Token COMMA37=null;
        Token ID39=null;
        Token EQUALS40=null;
        Token COMMA41=null;
        EugeneParser.expr_return ex =null;

        EugeneParser.numdecl_return numdecl38 =null;

        EugeneParser.numdecl_return numdecl42 =null;


        Object ID36_tree=null;
        Object COMMA37_tree=null;
        Object ID39_tree=null;
        Object EQUALS40_tree=null;
        Object COMMA41_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:803:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:803:4: ID ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID36=(Token)match(input,ID,FOLLOW_ID_in_numdecl1150); 
                    ID36_tree = 
                    (Object)adaptor.create(ID36)
                    ;
                    adaptor.addChild(root_0, ID36_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID36!=null?ID36.getText():null), EugeneConstants.NUM);
                    			retval.varname = (ID36!=null?ID36.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:809:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:809:6: COMMA numdecl[defer]
                            {
                            COMMA37=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1157); 
                            COMMA37_tree = 
                            (Object)adaptor.create(COMMA37)
                            ;
                            adaptor.addChild(root_0, COMMA37_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1159);
                            numdecl38=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl38.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:810:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID39=(Token)match(input,ID,FOLLOW_ID_in_numdecl1167); 
                    ID39_tree = 
                    (Object)adaptor.create(ID39)
                    ;
                    adaptor.addChild(root_0, ID39_tree);


                    EQUALS40=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1169); 
                    EQUALS40_tree = 
                    (Object)adaptor.create(EQUALS40)
                    ;
                    adaptor.addChild(root_0, EQUALS40_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:810:14: (ex= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:810:15: ex= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_numdecl1174);
                    ex=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ex.getTree());

                    }



                    		if(!defer) {
                    			declareVariableWithValueNum((ID39!=null?ID39.getText():null), (ex!=null?ex.p:null), (ex!=null?ex.index:0));
                    			retval.varname = (ID39!=null?ID39.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:816:6: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:816:7: COMMA numdecl[defer]
                            {
                            COMMA41=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1184); 
                            COMMA41_tree = 
                            (Object)adaptor.create(COMMA41)
                            ;
                            adaptor.addChild(root_0, COMMA41_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1186);
                            numdecl42=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl42.getTree());

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
        Token ID43=null;
        Token COMMA44=null;
        Token EQUALS46=null;
        Token COMMA47=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtdecl_return txtdecl45 =null;

        EugeneParser.txtdecl_return txtdecl48 =null;


        Object var_tree=null;
        Object ID43_tree=null;
        Object COMMA44_tree=null;
        Object EQUALS46_tree=null;
        Object COMMA47_tree=null;

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


                    ID43=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1206); 
                    ID43_tree = 
                    (Object)adaptor.create(ID43)
                    ;
                    adaptor.addChild(root_0, ID43_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID43!=null?ID43.getText():null), EugeneConstants.TXT);
                    			retval.varname = (ID43!=null?ID43.getText():null);
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
                            COMMA44=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1213); 
                            COMMA44_tree = 
                            (Object)adaptor.create(COMMA44)
                            ;
                            adaptor.addChild(root_0, COMMA44_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1215);
                            txtdecl45=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl45.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:829:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1226); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS46=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1228); 
                    EQUALS46_tree = 
                    (Object)adaptor.create(EQUALS46)
                    ;
                    adaptor.addChild(root_0, EQUALS46_tree);


                    pushFollow(FOLLOW_expr_in_txtdecl1232);
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
                            COMMA47=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1240); 
                            COMMA47_tree = 
                            (Object)adaptor.create(COMMA47)
                            ;
                            adaptor.addChild(root_0, COMMA47_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1242);
                            txtdecl48=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl48.getTree());

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
        Token ID49=null;
        Token COMMA50=null;
        Token EQUALS52=null;
        Token COMMA53=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtlistdecl_return txtlistdecl51 =null;

        EugeneParser.txtlistdecl_return txtlistdecl54 =null;


        Object var_tree=null;
        Object ID49_tree=null;
        Object COMMA50_tree=null;
        Object EQUALS52_tree=null;
        Object COMMA53_tree=null;

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


                    ID49=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1262); 
                    ID49_tree = 
                    (Object)adaptor.create(ID49)
                    ;
                    adaptor.addChild(root_0, ID49_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID49!=null?ID49.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID49!=null?ID49.getText():null);
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
                            COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1269); 
                            COMMA50_tree = 
                            (Object)adaptor.create(COMMA50)
                            ;
                            adaptor.addChild(root_0, COMMA50_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1271);
                            txtlistdecl51=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl51.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:847:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1281); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS52=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1283); 
                    EQUALS52_tree = 
                    (Object)adaptor.create(EQUALS52)
                    ;
                    adaptor.addChild(root_0, EQUALS52_tree);


                    typeList = EugeneConstants.TXT;

                    pushFollow(FOLLOW_expr_in_txtlistdecl1289);
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
                            COMMA53=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1297); 
                            COMMA53_tree = 
                            (Object)adaptor.create(COMMA53)
                            ;
                            adaptor.addChild(root_0, COMMA53_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1299);
                            txtlistdecl54=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl54.getTree());

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
        Token ID55=null;
        Token COMMA56=null;
        Token EQUALS58=null;
        Token COMMA59=null;
        EugeneParser.expr_return let =null;

        EugeneParser.numlistdecl_return numlistdecl57 =null;

        EugeneParser.numlistdecl_return numlistdecl60 =null;


        Object var_tree=null;
        Object ID55_tree=null;
        Object COMMA56_tree=null;
        Object EQUALS58_tree=null;
        Object COMMA59_tree=null;

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


                    ID55=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1319); 
                    ID55_tree = 
                    (Object)adaptor.create(ID55)
                    ;
                    adaptor.addChild(root_0, ID55_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID55!=null?ID55.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID55!=null?ID55.getText():null);
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
                            COMMA56=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1326); 
                            COMMA56_tree = 
                            (Object)adaptor.create(COMMA56)
                            ;
                            adaptor.addChild(root_0, COMMA56_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1328);
                            numlistdecl57=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl57.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:865:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1338); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS58=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1340); 
                    EQUALS58_tree = 
                    (Object)adaptor.create(EQUALS58)
                    ;
                    adaptor.addChild(root_0, EQUALS58_tree);


                     typeList = EugeneConstants.NUM;

                    pushFollow(FOLLOW_expr_in_numlistdecl1345);
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
                            COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1353); 
                            COMMA59_tree = 
                            (Object)adaptor.create(COMMA59)
                            ;
                            adaptor.addChild(root_0, COMMA59_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1355);
                            numlistdecl60=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl60.getTree());

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
        Token ID61=null;
        Token COMMA62=null;
        Token EQUALS64=null;
        EugeneParser.expr_return let =null;

        EugeneParser.booldecl_return booldecl63 =null;


        Object var_tree=null;
        Object ID61_tree=null;
        Object COMMA62_tree=null;
        Object EQUALS64_tree=null;

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


                    ID61=(Token)match(input,ID,FOLLOW_ID_in_booldecl1375); 
                    ID61_tree = 
                    (Object)adaptor.create(ID61)
                    ;
                    adaptor.addChild(root_0, ID61_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID61!=null?ID61.getText():null), EugeneConstants.BOOLEAN);
                    			retval.varname = (ID61!=null?ID61.getText():null);
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
                            COMMA62=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1382); 
                            COMMA62_tree = 
                            (Object)adaptor.create(COMMA62)
                            ;
                            adaptor.addChild(root_0, COMMA62_tree);


                            pushFollow(FOLLOW_booldecl_in_booldecl1384);
                            booldecl63=booldecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, booldecl63.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:883:4: var= ID EQUALS let= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1394); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS64=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1396); 
                    EQUALS64_tree = 
                    (Object)adaptor.create(EQUALS64)
                    ;
                    adaptor.addChild(root_0, EQUALS64_tree);


                    pushFollow(FOLLOW_expr_in_booldecl1400);
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
        Token PROPERTY65=null;
        Token LEFTP66=null;
        Token RIGHTP67=null;
        EugeneParser.propertyType_return typeToken =null;


        Object nameToken_tree=null;
        Object PROPERTY65_tree=null;
        Object LEFTP66_tree=null;
        Object RIGHTP67_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:893:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            PROPERTY65=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1418); 
            PROPERTY65_tree = 
            (Object)adaptor.create(PROPERTY65)
            ;
            adaptor.addChild(root_0, PROPERTY65_tree);


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1422); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            LEFTP66=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1424); 
            LEFTP66_tree = 
            (Object)adaptor.create(LEFTP66)
            ;
            adaptor.addChild(root_0, LEFTP66_tree);


            pushFollow(FOLLOW_propertyType_in_propertyDeclaration1428);
            typeToken=propertyType();

            state._fsp--;

            adaptor.addChild(root_0, typeToken.getTree());

            RIGHTP67=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1430); 
            RIGHTP67_tree = 
            (Object)adaptor.create(RIGHTP67)
            ;
            adaptor.addChild(root_0, RIGHTP67_tree);



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

        Token TXT68=null;
        Token TXT69=null;
        Token LEFTSBR70=null;
        Token RIGHTSBR71=null;
        Token NUM72=null;
        Token NUM73=null;
        Token LEFTSBR74=null;
        Token RIGHTSBR75=null;
        Token set76=null;

        Object TXT68_tree=null;
        Object TXT69_tree=null;
        Object LEFTSBR70_tree=null;
        Object RIGHTSBR71_tree=null;
        Object NUM72_tree=null;
        Object NUM73_tree=null;
        Object LEFTSBR74_tree=null;
        Object RIGHTSBR75_tree=null;
        Object set76_tree=null;

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


                    TXT68=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1449); 
                    TXT68_tree = 
                    (Object)adaptor.create(TXT68)
                    ;
                    adaptor.addChild(root_0, TXT68_tree);



                    retval.type = EugeneConstants.TXT;	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:911:4: TXT LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT69=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1456); 
                    TXT69_tree = 
                    (Object)adaptor.create(TXT69)
                    ;
                    adaptor.addChild(root_0, TXT69_tree);


                    LEFTSBR70=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1458); 
                    LEFTSBR70_tree = 
                    (Object)adaptor.create(LEFTSBR70)
                    ;
                    adaptor.addChild(root_0, LEFTSBR70_tree);


                    RIGHTSBR71=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1460); 
                    RIGHTSBR71_tree = 
                    (Object)adaptor.create(RIGHTSBR71)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR71_tree);



                    retval.type = EugeneConstants.TXTLIST;	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:914:4: NUM
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM72=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1467); 
                    NUM72_tree = 
                    (Object)adaptor.create(NUM72)
                    ;
                    adaptor.addChild(root_0, NUM72_tree);



                    retval.type = EugeneConstants.NUM;	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:917:4: NUM LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM73=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1475); 
                    NUM73_tree = 
                    (Object)adaptor.create(NUM73)
                    ;
                    adaptor.addChild(root_0, NUM73_tree);


                    LEFTSBR74=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1477); 
                    LEFTSBR74_tree = 
                    (Object)adaptor.create(LEFTSBR74)
                    ;
                    adaptor.addChild(root_0, LEFTSBR74_tree);


                    RIGHTSBR75=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1479); 
                    RIGHTSBR75_tree = 
                    (Object)adaptor.create(RIGHTSBR75)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR75_tree);



                    retval.type = EugeneConstants.NUMLIST;	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:920:4: ( BOOLEAN | BOOL )
                    {
                    root_0 = (Object)adaptor.nil();


                    set76=(Token)input.LT(1);

                    if ( (input.LA(1) >= BOOL && input.LA(1) <= BOOLEAN) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set76)
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
        Token TYPE78=null;
        Token LEFTP79=null;
        Token RIGHTP80=null;
        EugeneParser.listOfIDs_return lstToken =null;

        EugeneParser.partTypeDeclaration_return partTypeDeclaration77 =null;


        Object nameToken_tree=null;
        Object TYPE78_tree=null;
        Object LEFTP79_tree=null;
        Object RIGHTP80_tree=null;

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


                    pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1508);
                    partTypeDeclaration77=partTypeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, partTypeDeclaration77.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:4: ( TYPE )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:931:5: TYPE
                    {
                    TYPE78=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1515); 
                    TYPE78_tree = 
                    (Object)adaptor.create(TYPE78)
                    ;
                    adaptor.addChild(root_0, TYPE78_tree);


                    }


                    nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1520); 
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
                            LEFTP79=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1523); 
                            LEFTP79_tree = 
                            (Object)adaptor.create(LEFTP79)
                            ;
                            adaptor.addChild(root_0, LEFTP79_tree);


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
                                    pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1528);
                                    lstToken=listOfIDs(defer);

                                    state._fsp--;

                                    adaptor.addChild(root_0, lstToken.getTree());

                                    }
                                    break;

                            }


                            RIGHTP80=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1533); 
                            RIGHTP80_tree = 
                            (Object)adaptor.create(RIGHTP80)
                            ;
                            adaptor.addChild(root_0, RIGHTP80_tree);


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
        Token set81=null;
        Token LEFTP82=null;
        Token RIGHTP83=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object nameToken_tree=null;
        Object set81_tree=null;
        Object LEFTP82_tree=null;
        Object RIGHTP83_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:945:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            set81=(Token)input.LT(1);

            if ( (input.LA(1) >= PART && input.LA(1) <= PART_TYPE) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set81)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1561); 
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
                    LEFTP82=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1564); 
                    LEFTP82_tree = 
                    (Object)adaptor.create(LEFTP82)
                    ;
                    adaptor.addChild(root_0, LEFTP82_tree);


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
                            pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1569);
                            lstToken=listOfIDs(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lstToken.getTree());

                            }
                            break;

                    }


                    RIGHTP83=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1574); 
                    RIGHTP83_tree = 
                    (Object)adaptor.create(RIGHTP83)
                    ;
                    adaptor.addChild(root_0, RIGHTP83_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:961:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
    public final EugeneParser.containerDeclaration_return containerDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.containerDeclaration_return retval = new EugeneParser.containerDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token c=null;
        Token a=null;
        Token name=null;
        Token LEFTSBR84=null;
        Token RIGHTSBR85=null;
        Token LEFTP86=null;
        Token RIGHTP88=null;
        EugeneParser.list_of_declarations_return list_of_declarations87 =null;


        Object c_tree=null;
        Object a_tree=null;
        Object name_tree=null;
        Object LEFTSBR84_tree=null;
        Object RIGHTSBR85_tree=null;
        Object LEFTP86_tree=null;
        Object RIGHTP88_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:2: ( (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:4: (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:4: (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:5: c= COLLECTION
                    {
                    c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1601); 
                    c_tree = 
                    (Object)adaptor.create(c)
                    ;
                    adaptor.addChild(root_0, c_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:20: (a= ARRAY LEFTSBR RIGHTSBR )
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:20: (a= ARRAY LEFTSBR RIGHTSBR )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:963:21: a= ARRAY LEFTSBR RIGHTSBR
                    {
                    a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1608); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    LEFTSBR84=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1610); 
                    LEFTSBR84_tree = 
                    (Object)adaptor.create(LEFTSBR84)
                    ;
                    adaptor.addChild(root_0, LEFTSBR84_tree);


                    RIGHTSBR85=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1612); 
                    RIGHTSBR85_tree = 
                    (Object)adaptor.create(RIGHTSBR85)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR85_tree);


                    }


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1618); 
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
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LEFTP) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:977:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
                    {
                    LEFTP86=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1623); 
                    LEFTP86_tree = 
                    (Object)adaptor.create(LEFTP86)
                    ;
                    adaptor.addChild(root_0, LEFTP86_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:977:11: ( list_of_declarations[defer] )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==ARRAY||(LA28_0 >= BOOL && LA28_0 <= COLLECTION)||LA28_0==DEVICE||(LA28_0 >= FALSE_LC && LA28_0 <= FALSE_UC)||LA28_0==GRAMMAR||LA28_0==ID||LA28_0==INTERACTION||(LA28_0 >= LEFTP && LA28_0 <= LEFTSBR)||LA28_0==MINUS||(LA28_0 >= NUM && LA28_0 <= PART_TYPE)||(LA28_0 >= PROPERTY && LA28_0 <= REAL)||LA28_0==RULE||(LA28_0 >= STRING && LA28_0 <= TYPE)) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:977:12: list_of_declarations[defer]
                            {
                            pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1626);
                            list_of_declarations87=list_of_declarations(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, list_of_declarations87.getTree());

                            }
                            break;

                    }


                    RIGHTP88=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1631); 
                    RIGHTP88_tree = 
                    (Object)adaptor.create(RIGHTP88)
                    ;
                    adaptor.addChild(root_0, RIGHTP88_tree);


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
                    printError(ee.toString());
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:992:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
    public final EugeneParser.list_of_declarations_return list_of_declarations(boolean defer) throws RecognitionException {
        EugeneParser.list_of_declarations_return retval = new EugeneParser.list_of_declarations_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA89=null;
        EugeneParser.declarationStatement_return ds =null;

        EugeneParser.atom_return at =null;

        EugeneParser.list_of_declarations_return lod =null;


        Object COMMA89_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:2: ( (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:4: (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:4: (ds= declarationStatement[defer] |at= atom[defer] )
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

                if ( (LA30_2==ID||LA30_2==LC_INDUCES||LA30_2==LC_REPRESSES||LA30_2==UC_INDUCES||LA30_2==UC_REPRESSES) ) {
                    alt30=1;
                }
                else if ( (LA30_2==COMMA||LA30_2==DOT||LA30_2==LEFTSBR||LA30_2==RIGHTP) ) {
                    alt30=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 2, input);

                    throw nvae;

                }
                }
                break;
            case FALSE_LC:
            case FALSE_UC:
            case LEFTP:
            case LEFTSBR:
            case MINUS:
            case NUMBER:
            case REAL:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:6: ds= declarationStatement[defer]
                    {
                    pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1664);
                    ds=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ds.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:994:39: at= atom[defer]
                    {
                    pushFollow(FOLLOW_atom_in_list_of_declarations1671);
                    at=atom(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, at.getTree());


                    if(!defer) {
                        try {
                            if(null != (at!=null?at.element:null)) {
                                this.interp.addToContainer((at!=null?at.element:null));
                            } else {
                                throw new EugeneException("Cannot add " + (at!=null?input.toString(at.start,at.stop):null) + " to a Eugene container!");
                            }
                        } catch(EugeneException ee) {
                            printError(ee.toString());
                        }
                    }	
                    	

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1006:5: ( COMMA lod= list_of_declarations[defer] )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==COMMA) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1006:7: COMMA lod= list_of_declarations[defer]
                    {
                    COMMA89=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1679); 
                    COMMA89_tree = 
                    (Object)adaptor.create(COMMA89)
                    ;
                    adaptor.addChild(root_0, COMMA89_tree);


                    pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations1683);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1012:1: instantiation[boolean defer] : t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
    public final EugeneParser.instantiation_return instantiation(boolean defer) throws RecognitionException {
        EugeneParser.instantiation_return retval = new EugeneParser.instantiation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token t=null;
        Token nameToken=null;
        Token LEFTP90=null;
        Token RIGHTP91=null;
        EugeneParser.listOfDotValues_return dotToken =null;

        EugeneParser.listOfValues_return valueToken =null;


        Object t_tree=null;
        Object nameToken_tree=null;
        Object LEFTP90_tree=null;
        Object RIGHTP91_tree=null;


        NamedElement type = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1016:2: (t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1016:4: t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            t=(Token)match(input,ID,FOLLOW_ID_in_instantiation1711); 
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
            	

            nameToken=(Token)match(input,ID,FOLLOW_ID_in_instantiation1717); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1032:17: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LEFTP) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1032:19: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
                    {
                    LEFTP90=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation1721); 
                    LEFTP90_tree = 
                    (Object)adaptor.create(LEFTP90)
                    ;
                    adaptor.addChild(root_0, LEFTP90_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1032:25: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
                    int alt32=3;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==DOT) ) {
                        alt32=1;
                    }
                    else if ( ((LA32_0 >= FALSE_LC && LA32_0 <= FALSE_UC)||LA32_0==ID||(LA32_0 >= LEFTP && LA32_0 <= LEFTSBR)||LA32_0==MINUS||LA32_0==NUMBER||LA32_0==REAL||(LA32_0 >= STRING && LA32_0 <= TRUE_UC)) ) {
                        alt32=2;
                    }
                    switch (alt32) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1032:26: dotToken= listOfDotValues[defer]
                            {
                            pushFollow(FOLLOW_listOfDotValues_in_instantiation1726);
                            dotToken=listOfDotValues(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dotToken.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1032:58: valueToken= listOfValues[defer, (ComponentType)type]
                            {
                            pushFollow(FOLLOW_listOfValues_in_instantiation1731);
                            valueToken=listOfValues(defer, (ComponentType)type);

                            state._fsp--;

                            adaptor.addChild(root_0, valueToken.getTree());

                            }
                            break;

                    }


                    RIGHTP91=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation1736); 
                    RIGHTP91_tree = 
                    (Object)adaptor.create(RIGHTP91)
                    ;
                    adaptor.addChild(root_0, RIGHTP91_tree);


                    }
                    break;

            }



            if(!defer) {
                try {

                    if(null != dotToken) {
                        this.interp.instantiate(
                            (ComponentType)type, (nameToken!=null?nameToken.getText():null),
                            this.propertyListHolder,
                            this.propertyValuesHolder);    	
                    } else if (null != valueToken) {
                        this.interp.instantiate(
                            (ComponentType)type, (nameToken!=null?nameToken.getText():null),
                            this.propertyListHolder,
                            this.propertyValuesHolder);    	
                    } else {
                        this.interp.instantiate(
                            (ComponentType)type, (nameToken!=null?nameToken.getText():null),
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1067:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
    public final EugeneParser.listOfDotValues_return listOfDotValues(boolean defer) throws RecognitionException {
        EugeneParser.listOfDotValues_return retval = new EugeneParser.listOfDotValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token prop=null;
        Token p=null;
        Token DOT92=null;
        Token LEFTP93=null;
        Token RIGHTP94=null;
        Token COMMA95=null;
        Token DOT96=null;
        Token LEFTP97=null;
        Token RIGHTP98=null;
        EugeneParser.expr_return v1 =null;

        EugeneParser.expr_return v2 =null;


        Object prop_tree=null;
        Object p_tree=null;
        Object DOT92_tree=null;
        Object LEFTP93_tree=null;
        Object RIGHTP94_tree=null;
        Object COMMA95_tree=null;
        Object DOT96_tree=null;
        Object LEFTP97_tree=null;
        Object RIGHTP98_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1068:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1068:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            {
            root_0 = (Object)adaptor.nil();


            DOT92=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1760); 
            DOT92_tree = 
            (Object)adaptor.create(DOT92)
            ;
            adaptor.addChild(root_0, DOT92_tree);


            prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1764); 
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
            		

            LEFTP93=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1770); 
            LEFTP93_tree = 
            (Object)adaptor.create(LEFTP93)
            ;
            adaptor.addChild(root_0, LEFTP93_tree);


            pushFollow(FOLLOW_expr_in_listOfDotValues1774);
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
            			

            RIGHTP94=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1782); 
            RIGHTP94_tree = 
            (Object)adaptor.create(RIGHTP94)
            ;
            adaptor.addChild(root_0, RIGHTP94_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1086:13: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMA) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1086:14: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
            	    {
            	    COMMA95=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues1785); 
            	    COMMA95_tree = 
            	    (Object)adaptor.create(COMMA95)
            	    ;
            	    adaptor.addChild(root_0, COMMA95_tree);


            	    DOT96=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1787); 
            	    DOT96_tree = 
            	    (Object)adaptor.create(DOT96)
            	    ;
            	    adaptor.addChild(root_0, DOT96_tree);


            	    p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1791); 
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
            	    				

            	    LEFTP97=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1799); 
            	    LEFTP97_tree = 
            	    (Object)adaptor.create(LEFTP97)
            	    ;
            	    adaptor.addChild(root_0, LEFTP97_tree);


            	    pushFollow(FOLLOW_expr_in_listOfDotValues1803);
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
            	    					

            	    RIGHTP98=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1813); 
            	    RIGHTP98_tree = 
            	    (Object)adaptor.create(RIGHTP98)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP98_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1107:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
    public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
        EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA99=null;
        EugeneParser.expr_return val1 =null;

        EugeneParser.expr_return val2 =null;


        Object COMMA99_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1108:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1109:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
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
            		

            pushFollow(FOLLOW_expr_in_listOfValues1834);
            val1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, val1.getTree());


            if(!defer) {
                propertyValuesHolder.add((val1!=null?val1.p:null));
            }				
            			

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1133:6: ( COMMA val2= expr[defer] )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1133:7: COMMA val2= expr[defer]
            	    {
            	    COMMA99=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues1840); 
            	    COMMA99_tree = 
            	    (Object)adaptor.create(COMMA99)
            	    ;
            	    adaptor.addChild(root_0, COMMA99_tree);



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
            	                   

            	    pushFollow(FOLLOW_expr_in_listOfValues1846);
            	    val2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, val2.getTree());


            	    if(!defer) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1163:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
    public final EugeneParser.deviceDeclaration_return deviceDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.deviceDeclaration_return retval = new EugeneParser.deviceDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token DEVICE100=null;
        Token LEFTP101=null;
        Token RIGHTP102=null;
        EugeneParser.deviceComponents_return dcs =null;


        Object n_tree=null;
        Object DEVICE100_tree=null;
        Object LEFTP101_tree=null;
        Object RIGHTP102_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            DEVICE100=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration1865); 
            DEVICE100_tree = 
            (Object)adaptor.create(DEVICE100)
            ;
            adaptor.addChild(root_0, DEVICE100_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration1869); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==LEFTP) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
                    {
                    LEFTP101=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration1872); 
                    LEFTP101_tree = 
                    (Object)adaptor.create(LEFTP101)
                    ;
                    adaptor.addChild(root_0, LEFTP101_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:23: (dcs= deviceComponents[defer] )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==ID||LA36_0==LEFTSBR||LA36_0==MINUS||LA36_0==PLUS) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1164:24: dcs= deviceComponents[defer]
                            {
                            pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration1877);
                            dcs=deviceComponents(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dcs.getTree());

                            }
                            break;

                    }


                    RIGHTP102=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration1882); 
                    RIGHTP102_tree = 
                    (Object)adaptor.create(RIGHTP102)
                    ;
                    adaptor.addChild(root_0, RIGHTP102_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1178:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
    public final EugeneParser.deviceComponents_return deviceComponents(boolean defer) throws RecognitionException {
        EugeneParser.deviceComponents_return retval = new EugeneParser.deviceComponents_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal103=null;
        EugeneParser.selection_return s =null;

        EugeneParser.deviceComponents_return dcs =null;


        Object char_literal103_tree=null;


        retval.lstComponents = new ArrayList<List<NamedElement>>();
        retval.lstOrientations = new ArrayList<List<Orientation>>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1184:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1184:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selection_in_deviceComponents1913);
            s=selection(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());


            if(!defer) {
                retval.lstComponents.add((s!=null?s.components:null));
                retval.lstOrientations.add((s!=null?s.orientations:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:4: ( ',' dcs= deviceComponents[defer] )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==COMMA) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1189:5: ',' dcs= deviceComponents[defer]
                    {
                    char_literal103=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents1919); 
                    char_literal103_tree = 
                    (Object)adaptor.create(char_literal103)
                    ;
                    adaptor.addChild(root_0, char_literal103_tree);


                    pushFollow(FOLLOW_deviceComponents_in_deviceComponents1923);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1197:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
    public final EugeneParser.selection_return selection(boolean defer) throws RecognitionException {
        EugeneParser.selection_return retval = new EugeneParser.selection_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTSBR104=null;
        Token RIGHTSBR105=null;
        EugeneParser.selection_list_return sl =null;

        EugeneParser.device_component_return dc =null;


        Object LEFTSBR104_tree=null;
        Object RIGHTSBR105_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1199:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1199:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR104=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection1947); 
                    LEFTSBR104_tree = 
                    (Object)adaptor.create(LEFTSBR104)
                    ;
                    adaptor.addChild(root_0, LEFTSBR104_tree);


                    pushFollow(FOLLOW_selection_list_in_selection1951);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());

                    RIGHTSBR105=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection1954); 
                    RIGHTSBR105_tree = 
                    (Object)adaptor.create(RIGHTSBR105)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR105_tree);



                    if(!defer) {
                        retval.components = (sl!=null?sl.components:null);
                        retval.orientations = (sl!=null?sl.orientations:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1205:4: dc= device_component[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_device_component_in_selection1963);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1216:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
    public final EugeneParser.selection_list_return selection_list(boolean defer) throws RecognitionException {
        EugeneParser.selection_list_return retval = new EugeneParser.selection_list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIPE106=null;
        EugeneParser.device_component_return dc =null;

        EugeneParser.selection_list_return sl =null;


        Object PIPE106_tree=null;


        retval.components = new ArrayList<NamedElement>();
        retval.orientations = new ArrayList<Orientation>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1222:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1222:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_device_component_in_selection_list1992);
            dc=device_component(defer);

            state._fsp--;

            adaptor.addChild(root_0, dc.getTree());


            if(!defer) {
                retval.components.add((dc!=null?dc.component:null));
                retval.orientations.add((dc!=null?dc.orientation:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1227:4: ( PIPE sl= selection_list[defer] )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==PIPE) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1227:5: PIPE sl= selection_list[defer]
                    {
                    PIPE106=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list1998); 
                    PIPE106_tree = 
                    (Object)adaptor.create(PIPE106)
                    ;
                    adaptor.addChild(root_0, PIPE106_tree);


                    pushFollow(FOLLOW_selection_list_in_selection_list2002);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1235:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
    public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
        EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token directionToken=null;
        Token idToken=null;

        Object directionToken_tree=null;
        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1237:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1237:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1237:4: (directionToken= ( MINUS | PLUS ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==MINUS||LA41_0==PLUS) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1237:5: directionToken= ( MINUS | PLUS )
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


            idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component2038); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1285:1: assignment[boolean defer] : lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
    public final EugeneParser.assignment_return assignment(boolean defer) throws RecognitionException {
        EugeneParser.assignment_return retval = new EugeneParser.assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token a=null;
        Token EQUALS107=null;
        EugeneParser.lhs_assignment_return lhs =null;

        EugeneParser.rhs_assignment_return rhs =null;


        Object a_tree=null;
        Object EQUALS107_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1286:2: (lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1286:4: lhs= lhs_assignment[defer] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_lhs_assignment_in_assignment2058);
            lhs=lhs_assignment(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            EQUALS107=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment2061); 
            EQUALS107_tree = 
            (Object)adaptor.create(EQUALS107)
            ;
            adaptor.addChild(root_0, EQUALS107_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1286:37: (a= AMP )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==AMP) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1286:38: a= AMP
                    {
                    a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment2066); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    }
                    break;

            }


            pushFollow(FOLLOW_rhs_assignment_in_assignment2072);
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
                    printError(ee.toString());    
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1315:1: lhs_assignment[boolean defer] : ID lhs_access[defer] ;
    public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID108=null;
        EugeneParser.lhs_access_return lhs_access109 =null;


        Object ID108_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1316:2: ( ID lhs_access[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1316:4: ID lhs_access[defer]
            {
            root_0 = (Object)adaptor.nil();


            ID108=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment2087); 
            ID108_tree = 
            (Object)adaptor.create(ID108)
            ;
            adaptor.addChild(root_0, ID108_tree);


            pushFollow(FOLLOW_lhs_access_in_lhs_assignment2089);
            lhs_access109=lhs_access(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs_access109.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1319:1: lhs_access[boolean defer] : (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] );
    public final EugeneParser.lhs_access_return lhs_access(boolean defer) throws RecognitionException {
        EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token DOT110=null;
        Token LEFTSBR111=null;
        Token set112=null;
        Token RIGHTSBR113=null;
        EugeneParser.lhs_access_return lhs_access114 =null;


        Object i_tree=null;
        Object DOT110_tree=null;
        Object LEFTSBR111_tree=null;
        Object set112_tree=null;
        Object RIGHTSBR113_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1320:2: (| ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:2: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR ) lhs_access[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:4: ( DOT i= ID | LEFTSBR ( ID | NUMBER ) RIGHTSBR )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:5: DOT i= ID
                            {
                            DOT110=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2109); 
                            DOT110_tree = 
                            (Object)adaptor.create(DOT110)
                            ;
                            adaptor.addChild(root_0, DOT110_tree);


                            i=(Token)match(input,ID,FOLLOW_ID_in_lhs_access2113); 
                            i_tree = 
                            (Object)adaptor.create(i)
                            ;
                            adaptor.addChild(root_0, i_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:16: LEFTSBR ( ID | NUMBER ) RIGHTSBR
                            {
                            LEFTSBR111=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2117); 
                            LEFTSBR111_tree = 
                            (Object)adaptor.create(LEFTSBR111)
                            ;
                            adaptor.addChild(root_0, LEFTSBR111_tree);


                            set112=(Token)input.LT(1);

                            if ( input.LA(1)==ID||input.LA(1)==NUMBER ) {
                                input.consume();
                                adaptor.addChild(root_0, 
                                (Object)adaptor.create(set112)
                                );
                                state.errorRecovery=false;
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            RIGHTSBR113=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2125); 
                            RIGHTSBR113_tree = 
                            (Object)adaptor.create(RIGHTSBR113)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR113_tree);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_lhs_access_in_lhs_access2128);
                    lhs_access114=lhs_access(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs_access114.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1324:1: rhs_assignment[boolean defer] returns [NamedElement e] : (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] );
    public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.functionCall_return fc =null;

        EugeneParser.dataExchange_return de =null;

        EugeneParser.expr_return exp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1326:2: (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] )
            int alt45=3;
            switch ( input.LA(1) ) {
            case PERMUTE:
            case PRODUCT:
                {
                alt45=1;
                }
                break;
            case LC_IMPORT:
            case LC_PIGEON:
            case PIGEON:
            case SBOL:
            case UC_IMPORT:
                {
                alt45=2;
                }
                break;
            case FALSE_LC:
            case FALSE_UC:
            case ID:
            case LEFTP:
            case LEFTSBR:
            case MINUS:
            case NUMBER:
            case REAL:
            case STRING:
            case TRUE_LC:
            case TRUE_UC:
                {
                alt45=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }

            switch (alt45) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1326:4: fc= functionCall[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_rhs_assignment2151);
                    fc=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, fc.getTree());


                    if(!defer) {
                        retval.e = (fc!=null?fc.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1331:4: de= dataExchange[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_rhs_assignment2161);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());


                    if(!defer) {
                        retval.e = (de!=null?de.e:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1336:4: exp= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_rhs_assignment2171);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1347:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
    public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
        EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token char_literal115=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object idToken_tree=null;
        Object char_literal115_tree=null;


        retval.lstElements =new ArrayList<NamedElement>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1352:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1352:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2199); 
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1364:4: ( ',' lstToken= listOfIDs[defer] )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==COMMA) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1364:5: ',' lstToken= listOfIDs[defer]
                    {
                    char_literal115=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2208); 
                    char_literal115_tree = 
                    (Object)adaptor.create(char_literal115)
                    ;
                    adaptor.addChild(root_0, char_literal115_tree);


                    pushFollow(FOLLOW_listOfIDs_in_listOfIDs2212);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1371:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
    public final EugeneParser.ruleDeclaration_return ruleDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.ruleDeclaration_return retval = new EugeneParser.ruleDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token device=null;
        Token RULE116=null;
        Token LEFTP117=null;
        Token set118=null;
        Token COLON119=null;
        Token RIGHTP120=null;
        EugeneParser.cnf_rule_return cnf =null;


        Object name_tree=null;
        Object device_tree=null;
        Object RULE116_tree=null;
        Object LEFTP117_tree=null;
        Object set118_tree=null;
        Object COLON119_tree=null;
        Object RIGHTP120_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1373:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1373:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            RULE116=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2236); 
            RULE116_tree = 
            (Object)adaptor.create(RULE116)
            ;
            adaptor.addChild(root_0, RULE116_tree);


            name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2240); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
            adaptor.addChild(root_0, name_tree);


            LEFTP117=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2242); 
            LEFTP117_tree = 
            (Object)adaptor.create(LEFTP117)
            ;
            adaptor.addChild(root_0, LEFTP117_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1373:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1373:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1373:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==LC_ON||LA47_0==UC_ON) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1373:26: ( LC_ON | UC_ON ) device= ID COLON
                    {
                    set118=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set118)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2255); 
                    device_tree = 
                    (Object)adaptor.create(device)
                    ;
                    adaptor.addChild(root_0, device_tree);


                    COLON119=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2257); 
                    COLON119_tree = 
                    (Object)adaptor.create(COLON119)
                    ;
                    adaptor.addChild(root_0, COLON119_tree);


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
            	

            pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2265);
            cnf=cnf_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, cnf.getTree());

            }


            RIGHTP120=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2270); 
            RIGHTP120_tree = 
            (Object)adaptor.create(RIGHTP120)
            ;
            adaptor.addChild(root_0, RIGHTP120_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1397:1: ruleOperator[boolean defer] : ruleOperators ;
    public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
        EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ruleOperators_return ruleOperators121 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1398:2: ( ruleOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1398:4: ruleOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_ruleOperators_in_ruleOperator2284);
            ruleOperators121=ruleOperators();

            state._fsp--;

            adaptor.addChild(root_0, ruleOperators121.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1402:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
    public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
        EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set122=null;

        Object set122_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1403:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set122=(Token)input.LT(1);

            if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 106 && input.LA(1) <= 140)||(input.LA(1) >= 142 && input.LA(1) <= 179)||(input.LA(1) >= 181 && input.LA(1) <= 183) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set122)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1446:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
    public final EugeneParser.relationalOperators_return relationalOperators() throws RecognitionException {
        EugeneParser.relationalOperators_return retval = new EugeneParser.relationalOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EQUALS123=null;
        Token EQUALS124=null;
        Token NEQUAL125=null;
        Token LTHAN126=null;
        Token GTHAN127=null;
        Token LEQUAL128=null;
        Token GEQUAL129=null;
        Token set130=null;
        Token set131=null;
        Token set132=null;
        Token set133=null;
        Token set134=null;
        Token set135=null;
        Token set136=null;
        Token set137=null;
        Token set138=null;

        Object EQUALS123_tree=null;
        Object EQUALS124_tree=null;
        Object NEQUAL125_tree=null;
        Object LTHAN126_tree=null;
        Object GTHAN127_tree=null;
        Object LEQUAL128_tree=null;
        Object GEQUAL129_tree=null;
        Object set130_tree=null;
        Object set131_tree=null;
        Object set132_tree=null;
        Object set133_tree=null;
        Object set134_tree=null;
        Object set135_tree=null;
        Object set136_tree=null;
        Object set137_tree=null;
        Object set138_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1447:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
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
            case 116:
            case 155:
                {
                alt48=7;
                }
                break;
            case 125:
            case 164:
                {
                alt48=8;
                }
                break;
            case 122:
            case 161:
                {
                alt48=9;
                }
                break;
            case 128:
            case 167:
                {
                alt48=10;
                }
                break;
            case 142:
            case 181:
                {
                alt48=11;
                }
                break;
            case 118:
            case 157:
                {
                alt48=12;
                }
                break;
            case 119:
            case 158:
                {
                alt48=13;
                }
                break;
            case 126:
            case 165:
                {
                alt48=14;
                }
                break;
            case 141:
            case 180:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1447:4: EQUALS EQUALS
                    {
                    root_0 = (Object)adaptor.nil();


                    EQUALS123=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2663); 
                    EQUALS123_tree = 
                    (Object)adaptor.create(EQUALS123)
                    ;
                    adaptor.addChild(root_0, EQUALS123_tree);


                    EQUALS124=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2665); 
                    EQUALS124_tree = 
                    (Object)adaptor.create(EQUALS124)
                    ;
                    adaptor.addChild(root_0, EQUALS124_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1448:4: NEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    NEQUAL125=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators2670); 
                    NEQUAL125_tree = 
                    (Object)adaptor.create(NEQUAL125)
                    ;
                    adaptor.addChild(root_0, NEQUAL125_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1449:4: LTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    LTHAN126=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators2675); 
                    LTHAN126_tree = 
                    (Object)adaptor.create(LTHAN126)
                    ;
                    adaptor.addChild(root_0, LTHAN126_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1450:4: GTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    GTHAN127=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators2680); 
                    GTHAN127_tree = 
                    (Object)adaptor.create(GTHAN127)
                    ;
                    adaptor.addChild(root_0, GTHAN127_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1451:4: LEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    LEQUAL128=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators2685); 
                    LEQUAL128_tree = 
                    (Object)adaptor.create(LEQUAL128)
                    ;
                    adaptor.addChild(root_0, LEQUAL128_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1452:4: GEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    GEQUAL129=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators2690); 
                    GEQUAL129_tree = 
                    (Object)adaptor.create(GEQUAL129)
                    ;
                    adaptor.addChild(root_0, GEQUAL129_tree);


                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1453:4: ( 'CONTAINS' | 'contains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set130=(Token)input.LT(1);

                    if ( input.LA(1)==116||input.LA(1)==155 ) {
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
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1454:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set131=(Token)input.LT(1);

                    if ( input.LA(1)==125||input.LA(1)==164 ) {
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
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1455:4: ( 'MATCHES' | 'matches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set132=(Token)input.LT(1);

                    if ( input.LA(1)==122||input.LA(1)==161 ) {
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
                case 10 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1456:4: ( 'NOTMATCHES' | 'notmatches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set133=(Token)input.LT(1);

                    if ( input.LA(1)==128||input.LA(1)==167 ) {
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
                case 11 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1457:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set134=(Token)input.LT(1);

                    if ( input.LA(1)==142||input.LA(1)==181 ) {
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
                case 12 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1458:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set135=(Token)input.LT(1);

                    if ( input.LA(1)==118||input.LA(1)==157 ) {
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
                case 13 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1459:4: ( 'EQUALS' | 'equals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set136=(Token)input.LT(1);

                    if ( input.LA(1)==119||input.LA(1)==158 ) {
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
                case 14 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1460:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set137=(Token)input.LT(1);

                    if ( input.LA(1)==126||input.LA(1)==165 ) {
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
                case 15 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1461:4: ( 'SOUNDSLIKE' | 'soundslike' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set138=(Token)input.LT(1);

                    if ( input.LA(1)==141||input.LA(1)==180 ) {
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

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1464:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
    public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
        EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set139=null;
        EugeneParser.or_predicate_return c =null;

        EugeneParser.cnf_rule_return cnf =null;


        Object set139_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1466:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1466:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1466:4: (c= or_predicate[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1466:5: c= or_predicate[defer]
            {
            pushFollow(FOLLOW_or_predicate_in_cnf_rule2791);
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1474:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LC_AND||LA49_0==LOG_AND||LA49_0==UC_AND) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1474:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
                    {
                    set139=(Token)input.LT(1);

                    if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
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


                    pushFollow(FOLLOW_cnf_rule_in_cnf_rule2809);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1481:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
    public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
        EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set140=null;
        EugeneParser.negated_predicate_return n1 =null;

        EugeneParser.negated_predicate_return n2 =null;


        Object set140_tree=null;


        LogicalOr lor = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1486:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1486:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_negated_predicate_in_or_predicate2839);
            n1=negated_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, n1.getTree());


            if(!defer) {
                retval.p = (n1!=null?n1.p:null);
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1490:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==LC_OR||LA50_0==LOG_OR||LA50_0==UC_OR) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1490:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
            	    {
            	    set140=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
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


            	    pushFollow(FOLLOW_negated_predicate_in_or_predicate2855);
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
            	    break loop50;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1511:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
    public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
        EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set141=null;
        EugeneParser.predicate_return c =null;


        Object set141_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1513:2: ( ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1513:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1513:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==LC_NOT||LA51_0==LOG_NOT||LA51_0==UC_NOT) ) {
                alt51=1;
            }
            else if ( (LA51_0==ID||LA51_0==LC_INDUCES||LA51_0==LC_REPRESSES||(LA51_0 >= LEFTP && LA51_0 <= LEFTSBR)||LA51_0==MINUS||LA51_0==NUMBER||LA51_0==REAL||LA51_0==STRING||LA51_0==UC_INDUCES||LA51_0==UC_REPRESSES||(LA51_0 >= 106 && LA51_0 <= 140)||(LA51_0 >= 142 && LA51_0 <= 179)||(LA51_0 >= 181 && LA51_0 <= 183)) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;

            }
            switch (alt51) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1513:5: ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer]
                    {
                    set141=(Token)input.LT(1);

                    if ( input.LA(1)==LC_NOT||input.LA(1)==LOG_NOT||input.LA(1)==UC_NOT ) {
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


                    pushFollow(FOLLOW_predicate_in_negated_predicate2893);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1522:4: c= predicate[defer]
                    {
                    pushFollow(FOLLOW_predicate_in_negated_predicate2903);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1529:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1531:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
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
                case 141:
                case 180:
                    {
                    alt54=3;
                    }
                    break;
                case 116:
                case 155:
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
                case 125:
                case 164:
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
                case 122:
                case 161:
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
                case 128:
                case 167:
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
                            new NoViableAltException("", 54, 10, input);

                        throw nvae;

                    }

                    }
                    break;
                case 118:
                case 157:
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
                case 119:
                case 158:
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
                case 126:
                case 165:
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
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 117:
                case 120:
                case 121:
                case 123:
                case 124:
                case 127:
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
                case 156:
                case 159:
                case 160:
                case 162:
                case 163:
                case 166:
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
                case 182:
                case 183:
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
                case 116:
                case 155:
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
                case 141:
                case 180:
                    {
                    alt54=3;
                    }
                    break;
                case 125:
                case 164:
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
                case 122:
                case 161:
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
                case 128:
                case 167:
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
                            new NoViableAltException("", 54, 10, input);

                        throw nvae;

                    }

                    }
                    break;
                case 118:
                case 157:
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
                case 119:
                case 158:
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
                case 126:
                case 165:
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
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 117:
                case 120:
                case 121:
                case 123:
                case 124:
                case 127:
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
                case 156:
                case 159:
                case 160:
                case 162:
                case 163:
                case 166:
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
                case 182:
                case 183:
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
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
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
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 181:
            case 182:
            case 183:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1531:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1531:4: (lhs= operand[defer] )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==ID||LA52_0==LEFTSBR||LA52_0==NUMBER) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1531:5: lhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate2930);
                            lhs=operand(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lhs.getTree());


                            addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_ruleOperator_in_predicate2940);
                    op=ruleOperator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, op.getTree());


                    addToken((op!=null?input.toString(op.start,op.stop):null));	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1535:5: (rhs= operand[defer] )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==ID||LA53_0==LEFTSBR||LA53_0==NUMBER) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1535:6: rhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate2949);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1549:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_predicate2963); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1566:4: exp= expressionRule[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expressionRule_in_predicate2972);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1573:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' ) ;
    public final EugeneParser.operand_return operand(boolean defer) throws RecognitionException {
        EugeneParser.operand_return retval = new EugeneParser.operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token n=null;
        Token char_literal142=null;
        Token char_literal143=null;

        Object i_tree=null;
        Object n_tree=null;
        Object char_literal142_tree=null;
        Object char_literal143_tree=null;


        NamedElement element = null;
        int constant = -1;
        int index = -1;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:5: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_operand3003); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1592:4: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3012); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1597:4: '[' n= NUMBER ']'
                    {
                    char_literal142=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand3019); 
                    char_literal142_tree = 
                    (Object)adaptor.create(char_literal142)
                    ;
                    adaptor.addChild(root_0, char_literal142_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3023); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal143=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand3025); 
                    char_literal143_tree = 
                    (Object)adaptor.create(char_literal143)
                    ;
                    adaptor.addChild(root_0, char_literal143_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1616:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
    public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
        EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expression_return lhs =null;

        EugeneParser.exp_op_return op =null;

        EugeneParser.expression_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1618:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1618:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expression_in_expressionRule3051);
            lhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_exp_op_in_expressionRule3056);
            op=exp_op(defer);

            state._fsp--;

            adaptor.addChild(root_0, op.getTree());

            pushFollow(FOLLOW_expression_in_expressionRule3061);
            rhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer) {
                try {
                    retval.p = new ExpressionConstraint((lhs!=null?lhs.exp:null), (op!=null?input.toString(op.start,op.stop):null), (rhs!=null?rhs.exp:null));
                } catch(EugeneException ee) {
                    printError(ee.toString());
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1629:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
    public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
        EugeneParser.expression_return retval = new EugeneParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTP144=null;
        Token RIGHTP146=null;
        EugeneParser.exp_operand_return lhs =null;

        EugeneParser.exp_operator_return expop =null;

        EugeneParser.expression_return rhs =null;

        EugeneParser.expression_return expression145 =null;


        Object LEFTP144_tree=null;
        Object RIGHTP146_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1631:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1631:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_exp_operand_in_expression3085);
                    lhs=exp_operand(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs.getTree());


                    if(!defer) {
                        retval.exp = new Expression((lhs!=null?lhs.eop:null), null, null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1635:4: (expop= exp_operator[defer] rhs= expression[defer] )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==DIV||LA56_0==MINUS||LA56_0==MULT||LA56_0==PLUS) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1635:6: expop= exp_operator[defer] rhs= expression[defer]
                            {
                            pushFollow(FOLLOW_exp_operator_in_expression3094);
                            expop=exp_operator(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, expop.getTree());

                            pushFollow(FOLLOW_expression_in_expression3099);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1640:4: LEFTP expression[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTP144=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3111); 
                    LEFTP144_tree = 
                    (Object)adaptor.create(LEFTP144)
                    ;
                    adaptor.addChild(root_0, LEFTP144_tree);


                    pushFollow(FOLLOW_expression_in_expression3113);
                    expression145=expression(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expression145.getTree());

                    RIGHTP146=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3116); 
                    RIGHTP146_tree = 
                    (Object)adaptor.create(RIGHTP146)
                    ;
                    adaptor.addChild(root_0, RIGHTP146_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1647:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
    public final EugeneParser.exp_operator_return exp_operator(boolean defer) throws RecognitionException {
        EugeneParser.exp_operator_return retval = new EugeneParser.exp_operator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLUS147=null;
        Token MINUS148=null;
        Token MULT149=null;
        Token DIV150=null;

        Object PLUS147_tree=null;
        Object MINUS148_tree=null;
        Object MULT149_tree=null;
        Object DIV150_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1649:2: ( PLUS | MINUS | MULT | DIV )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1649:4: PLUS
                    {
                    root_0 = (Object)adaptor.nil();


                    PLUS147=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3135); 
                    PLUS147_tree = 
                    (Object)adaptor.create(PLUS147)
                    ;
                    adaptor.addChild(root_0, PLUS147_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.PLUS;	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1654:4: MINUS
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS148=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3143); 
                    MINUS148_tree = 
                    (Object)adaptor.create(MINUS148)
                    ;
                    adaptor.addChild(root_0, MINUS148_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MINUS;	
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1659:4: MULT
                    {
                    root_0 = (Object)adaptor.nil();


                    MULT149=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3150); 
                    MULT149_tree = 
                    (Object)adaptor.create(MULT149)
                    ;
                    adaptor.addChild(root_0, MULT149_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MULT;	
                    }
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1664:4: DIV
                    {
                    root_0 = (Object)adaptor.nil();


                    DIV150=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3157); 
                    DIV150_tree = 
                    (Object)adaptor.create(DIV150)
                    ;
                    adaptor.addChild(root_0, DIV150_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1671:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
    public final EugeneParser.exp_operand_return exp_operand(boolean defer) throws RecognitionException {
        EugeneParser.exp_operand_return retval = new EugeneParser.exp_operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i1=null;
        Token i2=null;
        Token n=null;
        Token r=null;
        Token s=null;
        Token DOT151=null;
        Token LEFTSBR152=null;
        Token RIGHTSBR153=null;
        Token MINUS154=null;
        Token MINUS155=null;

        Object i1_tree=null;
        Object i2_tree=null;
        Object n_tree=null;
        Object r_tree=null;
        Object s_tree=null;
        Object DOT151_tree=null;
        Object LEFTSBR152_tree=null;
        Object RIGHTSBR153_tree=null;
        Object MINUS154_tree=null;
        Object MINUS155_tree=null;


        List<NamedElement> elements = null;
        NamedElement ne = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:2: ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:4: (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )*
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:4: (i1= ID DOT )*
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
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1677:5: i1= ID DOT
                    	    {
                    	    i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3187); 
                    	    i1_tree = 
                    	    (Object)adaptor.create(i1)
                    	    ;
                    	    adaptor.addChild(root_0, i1_tree);


                    	    DOT151=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3189); 
                    	    DOT151_tree = 
                    	    (Object)adaptor.create(DOT151)
                    	    ;
                    	    adaptor.addChild(root_0, DOT151_tree);



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
                    	    break loop59;
                        }
                    } while (true);


                    i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3198); 
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
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1745:5: ( LEFTSBR n= NUMBER RIGHTSBR )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==LEFTSBR) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1745:6: LEFTSBR n= NUMBER RIGHTSBR
                    	    {
                    	    LEFTSBR152=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3204); 
                    	    LEFTSBR152_tree = 
                    	    (Object)adaptor.create(LEFTSBR152)
                    	    ;
                    	    adaptor.addChild(root_0, LEFTSBR152_tree);


                    	    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3208); 
                    	    n_tree = 
                    	    (Object)adaptor.create(n)
                    	    ;
                    	    adaptor.addChild(root_0, n_tree);


                    	    RIGHTSBR153=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3210); 
                    	    RIGHTSBR153_tree = 
                    	    (Object)adaptor.create(RIGHTSBR153)
                    	    ;
                    	    adaptor.addChild(root_0, RIGHTSBR153_tree);



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
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1756:4: n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3222); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1763:4: MINUS n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS154=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3229); 
                    MINUS154_tree = 
                    (Object)adaptor.create(MINUS154)
                    ;
                    adaptor.addChild(root_0, MINUS154_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3233); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1770:4: r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3242); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1777:4: MINUS r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS155=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3249); 
                    MINUS155_tree = 
                    (Object)adaptor.create(MINUS155)
                    ;
                    adaptor.addChild(root_0, MINUS155_tree);


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3253); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1784:4: s= STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3262); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1794:1: regexp[boolean defer] :;
    public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
        EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1795:2: ()
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1796:2: 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1798:1: exp_op[boolean defer] : relationalOperators ;
    public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
        EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.relationalOperators_return relationalOperators156 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1799:2: ( relationalOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1799:4: relationalOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_relationalOperators_in_exp_op3289);
            relationalOperators156=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, relationalOperators156.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1807:1: grammarDeclaration[boolean defer] : GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP ;
    public final EugeneParser.grammarDeclaration_return grammarDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.grammarDeclaration_return retval = new EugeneParser.grammarDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token GRAMMAR157=null;
        Token LEFTP158=null;
        Token RIGHTP160=null;
        EugeneParser.list_of_production_rules_return list_of_production_rules159 =null;


        Object n_tree=null;
        Object GRAMMAR157_tree=null;
        Object LEFTP158_tree=null;
        Object RIGHTP160_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1808:2: ( GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1809:3: GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            GRAMMAR157=(Token)match(input,GRAMMAR,FOLLOW_GRAMMAR_in_grammarDeclaration3308); 
            GRAMMAR157_tree = 
            (Object)adaptor.create(GRAMMAR157)
            ;
            adaptor.addChild(root_0, GRAMMAR157_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_grammarDeclaration3312); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            LEFTP158=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_grammarDeclaration3314); 
            LEFTP158_tree = 
            (Object)adaptor.create(LEFTP158)
            ;
            adaptor.addChild(root_0, LEFTP158_tree);


            pushFollow(FOLLOW_list_of_production_rules_in_grammarDeclaration3316);
            list_of_production_rules159=list_of_production_rules(defer);

            state._fsp--;

            adaptor.addChild(root_0, list_of_production_rules159.getTree());

            RIGHTP160=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_grammarDeclaration3319); 
            RIGHTP160_tree = 
            (Object)adaptor.create(RIGHTP160)
            ;
            adaptor.addChild(root_0, RIGHTP160_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1812:1: list_of_production_rules[boolean defer] : production_rule[defer] SEMIC ( list_of_production_rules[defer] )? ;
    public final EugeneParser.list_of_production_rules_return list_of_production_rules(boolean defer) throws RecognitionException {
        EugeneParser.list_of_production_rules_return retval = new EugeneParser.list_of_production_rules_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMIC162=null;
        EugeneParser.production_rule_return production_rule161 =null;

        EugeneParser.list_of_production_rules_return list_of_production_rules163 =null;


        Object SEMIC162_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1813:2: ( production_rule[defer] SEMIC ( list_of_production_rules[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1813:4: production_rule[defer] SEMIC ( list_of_production_rules[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_production_rule_in_list_of_production_rules3331);
            production_rule161=production_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, production_rule161.getTree());

            SEMIC162=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_list_of_production_rules3334); 
            SEMIC162_tree = 
            (Object)adaptor.create(SEMIC162)
            ;
            adaptor.addChild(root_0, SEMIC162_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1813:33: ( list_of_production_rules[defer] )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==ID) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1813:34: list_of_production_rules[defer]
                    {
                    pushFollow(FOLLOW_list_of_production_rules_in_list_of_production_rules3337);
                    list_of_production_rules163=list_of_production_rules(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list_of_production_rules163.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1816:1: production_rule[boolean defer] : lhs= ID ARROW right_hand_side[defer] ;
    public final EugeneParser.production_rule_return production_rule(boolean defer) throws RecognitionException {
        EugeneParser.production_rule_return retval = new EugeneParser.production_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs=null;
        Token ARROW164=null;
        EugeneParser.right_hand_side_return right_hand_side165 =null;


        Object lhs_tree=null;
        Object ARROW164_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1817:2: (lhs= ID ARROW right_hand_side[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1817:4: lhs= ID ARROW right_hand_side[defer]
            {
            root_0 = (Object)adaptor.nil();


            lhs=(Token)match(input,ID,FOLLOW_ID_in_production_rule3357); 
            lhs_tree = 
            (Object)adaptor.create(lhs)
            ;
            adaptor.addChild(root_0, lhs_tree);



            if(!defer) {
                // ID denotes a non-terminal of the grammar
            }	
            	

            ARROW164=(Token)match(input,ARROW,FOLLOW_ARROW_in_production_rule3361); 
            ARROW164_tree = 
            (Object)adaptor.create(ARROW164)
            ;
            adaptor.addChild(root_0, ARROW164_tree);


            pushFollow(FOLLOW_right_hand_side_in_production_rule3363);
            right_hand_side165=right_hand_side(defer);

            state._fsp--;

            adaptor.addChild(root_0, right_hand_side165.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1824:1: right_hand_side[boolean defer] : (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] );
    public final EugeneParser.right_hand_side_return right_hand_side(boolean defer) throws RecognitionException {
        EugeneParser.right_hand_side_return retval = new EugeneParser.right_hand_side_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token COMMA166=null;
        EugeneParser.right_hand_side_return right_hand_side167 =null;

        EugeneParser.interaction_return interaction168 =null;


        Object i_tree=null;
        Object COMMA166_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1825:2: (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1825:4: i= ID ( COMMA right_hand_side[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_right_hand_side3379); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer) {
                        // ID must be either a terminal (i.e. a part)
                        // or a non-terminal defined within the grammar
                    }	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:4: ( COMMA right_hand_side[defer] )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==COMMA) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1830:5: COMMA right_hand_side[defer]
                            {
                            COMMA166=(Token)match(input,COMMA,FOLLOW_COMMA_in_right_hand_side3384); 
                            COMMA166_tree = 
                            (Object)adaptor.create(COMMA166)
                            ;
                            adaptor.addChild(root_0, COMMA166_tree);


                            pushFollow(FOLLOW_right_hand_side_in_right_hand_side3386);
                            right_hand_side167=right_hand_side(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, right_hand_side167.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1831:4: interaction[defer, \"some_string\"]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_right_hand_side3394);
                    interaction168=interaction(defer, "some_string");

                    state._fsp--;

                    adaptor.addChild(root_0, interaction168.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1838:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
    public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token INTERACTION169=null;
        Token LEFTP170=null;
        Token RIGHTP171=null;
        EugeneParser.interaction_return i1 =null;

        EugeneParser.interaction_return i2 =null;


        Object name_tree=null;
        Object INTERACTION169_tree=null;
        Object LEFTP170_tree=null;
        Object RIGHTP171_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1840:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1840:4: i1= interaction[defer, null]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3419);
                    i1=interaction(defer, null);

                    state._fsp--;

                    adaptor.addChild(root_0, i1.getTree());


                    if(!defer) {
                        retval.ia = (i1!=null?i1.ia:null);
                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1845:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    INTERACTION169=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3427); 
                    INTERACTION169_tree = 
                    (Object)adaptor.create(INTERACTION169)
                    ;
                    adaptor.addChild(root_0, INTERACTION169_tree);


                    name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3431); 
                    name_tree = 
                    (Object)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    LEFTP170=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3433); 
                    LEFTP170_tree = 
                    (Object)adaptor.create(LEFTP170)
                    ;
                    adaptor.addChild(root_0, LEFTP170_tree);


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3437);
                    i2=interaction(defer, (name!=null?name.getText():null));

                    state._fsp--;

                    adaptor.addChild(root_0, i2.getTree());

                    RIGHTP171=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3440); 
                    RIGHTP171_tree = 
                    (Object)adaptor.create(RIGHTP171)
                    ;
                    adaptor.addChild(root_0, RIGHTP171_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1852:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
    public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
        EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs1=null;
        Token rhs1=null;
        Token lhs2=null;
        Token LEFTP172=null;
        Token RIGHTP173=null;
        EugeneParser.interactionType_return t1 =null;

        EugeneParser.interactionType_return t2 =null;

        EugeneParser.interaction_return rhs2 =null;


        Object lhs1_tree=null;
        Object rhs1_tree=null;
        Object lhs2_tree=null;
        Object LEFTP172_tree=null;
        Object RIGHTP173_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1854:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1854:4: lhs1= ID t1= interactionType[defer] rhs1= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3463); 
                    lhs1_tree = 
                    (Object)adaptor.create(lhs1)
                    ;
                    adaptor.addChild(root_0, lhs1_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3467);
                    t1=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t1.getTree());

                    rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3472); 
                    rhs1_tree = 
                    (Object)adaptor.create(rhs1)
                    ;
                    adaptor.addChild(root_0, rhs1_tree);



                    if(!defer) {
                        try {
                            retval.ia = this.interp.createInteraction(name, (lhs1!=null?lhs1.getText():null), (t1!=null?t1.type:null), (rhs1!=null?rhs1.getText():null));
                        } catch(EugeneException ee) {
                            printError(ee.toString());
                        }
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1863:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3481); 
                    lhs2_tree = 
                    (Object)adaptor.create(lhs2)
                    ;
                    adaptor.addChild(root_0, lhs2_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3485);
                    t2=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t2.getTree());

                    LEFTP172=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3488); 
                    LEFTP172_tree = 
                    (Object)adaptor.create(LEFTP172)
                    ;
                    adaptor.addChild(root_0, LEFTP172_tree);


                    pushFollow(FOLLOW_interaction_in_interaction3492);
                    rhs2=interaction(defer, name);

                    state._fsp--;

                    adaptor.addChild(root_0, rhs2.getTree());

                    RIGHTP173=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3495); 
                    RIGHTP173_tree = 
                    (Object)adaptor.create(RIGHTP173)
                    ;
                    adaptor.addChild(root_0, RIGHTP173_tree);



                    if(!defer) {
                        try {
                            retval.ia = this.interp.createInteraction(name, (lhs2!=null?lhs2.getText():null), (t2!=null?t2.type:null), (rhs2!=null?rhs2.ia:null));
                        } catch(EugeneException ee) {
                            printError(ee.toString());
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1874:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
    public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
        EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set174=null;
        Token set175=null;

        Object set174_tree=null;
        Object set175_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1876:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1876:4: ( UC_REPRESSES | LC_REPRESSES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set174=(Token)input.LT(1);

                    if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
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



                    if(!defer) {
                        retval.type = Interaction.InteractionType.REPRESSES;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1881:4: ( UC_INDUCES | LC_INDUCES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set175=(Token)input.LT(1);

                    if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1897:1: functionCall[boolean defer] returns [NamedElement e] : (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.functionCall_return functionCall(boolean defer) throws RecognitionException {
        EugeneParser.functionCall_return retval = new EugeneParser.functionCall_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token pr=null;
        Token pe=null;
        Token idToken=null;
        Token LEFTP176=null;
        Token RIGHTP177=null;

        Object pr_tree=null;
        Object pe_tree=null;
        Object idToken_tree=null;
        Object LEFTP176_tree=null;
        Object RIGHTP177_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1899:2: ( (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1899:4: (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1899:4: (pr= PRODUCT |pe= PERMUTE )
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==PRODUCT) ) {
                alt68=1;
            }
            else if ( (LA68_0==PERMUTE) ) {
                alt68=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;

            }
            switch (alt68) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1899:5: pr= PRODUCT
                    {
                    pr=(Token)match(input,PRODUCT,FOLLOW_PRODUCT_in_functionCall3562); 
                    pr_tree = 
                    (Object)adaptor.create(pr)
                    ;
                    adaptor.addChild(root_0, pr_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1899:16: pe= PERMUTE
                    {
                    pe=(Token)match(input,PERMUTE,FOLLOW_PERMUTE_in_functionCall3566); 
                    pe_tree = 
                    (Object)adaptor.create(pe)
                    ;
                    adaptor.addChild(root_0, pe_tree);


                    }
                    break;

            }


            LEFTP176=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_functionCall3569); 
            LEFTP176_tree = 
            (Object)adaptor.create(LEFTP176)
            ;
            adaptor.addChild(root_0, LEFTP176_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_functionCall3573); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP177=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_functionCall3575); 
            RIGHTP177_tree = 
            (Object)adaptor.create(RIGHTP177)
            ;
            adaptor.addChild(root_0, RIGHTP177_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1926:1: printStatement[boolean defer] : ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP );
    public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
        EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PRINTLN178=null;
        Token LEFTP179=null;
        Token RIGHTP180=null;
        Token PRINT181=null;
        Token LEFTP182=null;
        Token RIGHTP183=null;
        EugeneParser.toPrint_return tp =null;


        Object PRINTLN178_tree=null;
        Object LEFTP179_tree=null;
        Object RIGHTP180_tree=null;
        Object PRINT181_tree=null;
        Object LEFTP182_tree=null;
        Object RIGHTP183_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1927:2: ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==PRINTLN) ) {
                alt69=1;
            }
            else if ( (LA69_0==PRINT) ) {
                alt69=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;

            }
            switch (alt69) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1927:4: PRINTLN LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINTLN178=(Token)match(input,PRINTLN,FOLLOW_PRINTLN_in_printStatement3593); 
                    PRINTLN178_tree = 
                    (Object)adaptor.create(PRINTLN178)
                    ;
                    adaptor.addChild(root_0, PRINTLN178_tree);


                    LEFTP179=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3595); 
                    LEFTP179_tree = 
                    (Object)adaptor.create(LEFTP179)
                    ;
                    adaptor.addChild(root_0, LEFTP179_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3599);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP180=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3602); 
                    RIGHTP180_tree = 
                    (Object)adaptor.create(RIGHTP180)
                    ;
                    adaptor.addChild(root_0, RIGHTP180_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1940:4: PRINT LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINT181=(Token)match(input,PRINT,FOLLOW_PRINT_in_printStatement3609); 
                    PRINT181_tree = 
                    (Object)adaptor.create(PRINT181)
                    ;
                    adaptor.addChild(root_0, PRINT181_tree);


                    LEFTP182=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3611); 
                    LEFTP182_tree = 
                    (Object)adaptor.create(LEFTP182)
                    ;
                    adaptor.addChild(root_0, LEFTP182_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3615);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP183=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3618); 
                    RIGHTP183_tree = 
                    (Object)adaptor.create(RIGHTP183)
                    ;
                    adaptor.addChild(root_0, RIGHTP183_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1954:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
    public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return exp =null;

        EugeneParser.toPrint_prime_return tpp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1956:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1956:4: exp= expr[defer] tpp= toPrint_prime[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_toPrint3639);
            exp=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, exp.getTree());

            pushFollow(FOLLOW_toPrint_prime_in_toPrint3644);
            tpp=toPrint_prime(defer);

            state._fsp--;

            adaptor.addChild(root_0, tpp.getTree());


            if(!defer) {
                retval.sb = new StringBuilder();
                if(null != (exp!=null?exp.element:null)) {
                    retval.sb.append((exp!=null?exp.element:null));
                } else {
                    retval.sb.append((exp!=null?exp.p:null).getValue());
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1969:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
    public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA184=null;
        EugeneParser.toPrint_return tp =null;


        Object COMMA184_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1971:2: (| COMMA tp= toPrint[defer] )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==RIGHTP) ) {
                alt70=1;
            }
            else if ( (LA70_0==COMMA) ) {
                alt70=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;

            }
            switch (alt70) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1971:4: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.sb = new StringBuilder();
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1976:4: COMMA tp= toPrint[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    COMMA184=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime3670); 
                    COMMA184_tree = 
                    (Object)adaptor.create(COMMA184)
                    ;
                    adaptor.addChild(root_0, COMMA184_tree);


                    pushFollow(FOLLOW_toPrint_in_toPrint_prime3674);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1988:1: imperativeStatements[boolean defer] : ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] );
    public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException {
        EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ifStatement_return ifStatement185 =null;

        EugeneParser.forall_iterator_return forall_iterator186 =null;

        EugeneParser.for_loop_return for_loop187 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1989:2: ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] )
            int alt71=3;
            switch ( input.LA(1) ) {
            case LC_IF:
            case UC_IF:
                {
                alt71=1;
                }
                break;
            case LC_FORALL:
            case UC_FORALL:
                {
                alt71=2;
                }
                break;
            case LC_FOR:
            case UC_FOR:
                {
                alt71=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;

            }

            switch (alt71) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1989:4: ifStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifStatement_in_imperativeStatements3694);
                    ifStatement185=ifStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ifStatement185.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1990:4: forall_iterator[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_forall_iterator_in_imperativeStatements3700);
                    forall_iterator186=forall_iterator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, forall_iterator186.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1991:4: for_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_for_loop_in_imperativeStatements3706);
                    for_loop187=for_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, for_loop187.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1994:1: ifStatement[boolean defer] : ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? ;
    public final EugeneParser.ifStatement_return ifStatement(boolean defer) throws RecognitionException {
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
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1998:2: ( ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2002:3: ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            {
            root_0 = (Object)adaptor.nil();


            set188=(Token)input.LT(1);

            if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set188)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP189=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3738); 
            LEFTP189_tree = 
            (Object)adaptor.create(LEFTP189)
            ;
            adaptor.addChild(root_0, LEFTP189_tree);


            pushFollow(FOLLOW_imp_condition_in_ifStatement3742);
            co=imp_condition(defer);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP190=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3745); 
            RIGHTP190_tree = 
            (Object)adaptor.create(RIGHTP190)
            ;
            adaptor.addChild(root_0, RIGHTP190_tree);


            LEFTCUR191=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3747); 
            LEFTCUR191_tree = 
            (Object)adaptor.create(LEFTCUR191)
            ;
            adaptor.addChild(root_0, LEFTCUR191_tree);


            pushFollow(FOLLOW_listOfStatements_in_ifStatement3755);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR192=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3758); 
            RIGHTCUR192_tree = 
            (Object)adaptor.create(RIGHTCUR192)
            ;
            adaptor.addChild(root_0, RIGHTCUR192_tree);



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
                        printError(ee.toString());
                    }
                    bExecuted = true;
                }
            }			
            		

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2025:3: ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==LC_ELSEIF||LA72_0==UC_ELSEIF) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2025:5: ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            	    {
            	    set193=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
            	        input.consume();
            	        adaptor.addChild(root_0, 
            	        (Object)adaptor.create(set193)
            	        );
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    LEFTP194=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3779); 
            	    LEFTP194_tree = 
            	    (Object)adaptor.create(LEFTP194)
            	    ;
            	    adaptor.addChild(root_0, LEFTP194_tree);


            	    pushFollow(FOLLOW_imp_condition_in_ifStatement3783);
            	    co=imp_condition(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, co.getTree());

            	    RIGHTP195=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3786); 
            	    RIGHTP195_tree = 
            	    (Object)adaptor.create(RIGHTP195)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP195_tree);


            	    LEFTCUR196=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3788); 
            	    LEFTCUR196_tree = 
            	    (Object)adaptor.create(LEFTCUR196)
            	    ;
            	    adaptor.addChild(root_0, LEFTCUR196_tree);


            	    pushFollow(FOLLOW_listOfStatements_in_ifStatement3796);
            	    stmts=listOfStatements(true);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmts.getTree());

            	    RIGHTCUR197=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3799); 
            	    RIGHTCUR197_tree = 
            	    (Object)adaptor.create(RIGHTCUR197)
            	    ;
            	    adaptor.addChild(root_0, RIGHTCUR197_tree);



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
            	                printError(ee.toString());
            	            }
            	            
            	            bExecuted = true;
            	        }
            	    }			
            	    		

            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2048:3: ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==LC_ELSE||LA73_0==UC_ELSE) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2048:4: ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR
                    {
                    set198=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set198)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTCUR199=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3821); 
                    LEFTCUR199_tree = 
                    (Object)adaptor.create(LEFTCUR199)
                    ;
                    adaptor.addChild(root_0, LEFTCUR199_tree);


                    pushFollow(FOLLOW_listOfStatements_in_ifStatement3829);
                    stmts=listOfStatements(true);

                    state._fsp--;

                    adaptor.addChild(root_0, stmts.getTree());

                    RIGHTCUR200=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3832); 
                    RIGHTCUR200_tree = 
                    (Object)adaptor.create(RIGHTCUR200)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR200_tree);



                    if(!defer && !bExecuted) {
                        try {
                            this.exec("listOfStatements", (stmts!=null?((Token)stmts.start):null).getTokenIndex());        
                        } catch(EugeneReturnException ere) {
                            // TODO!
                        } catch(EugeneException ee) {
                            printError(ee.toString());
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2064:1: imp_condition[boolean defer] returns [boolean bTrue] : lhs= atom[defer] ro= relationalOperators rhs= atom[defer] ;
    public final EugeneParser.imp_condition_return imp_condition(boolean defer) throws RecognitionException {
        EugeneParser.imp_condition_return retval = new EugeneParser.imp_condition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.atom_return lhs =null;

        EugeneParser.relationalOperators_return ro =null;

        EugeneParser.atom_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2066:2: (lhs= atom[defer] ro= relationalOperators rhs= atom[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2066:4: lhs= atom[defer] ro= relationalOperators rhs= atom[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_imp_condition3856);
            lhs=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_relationalOperators_in_imp_condition3861);
            ro=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, ro.getTree());

            pushFollow(FOLLOW_atom_in_imp_condition3865);
            rhs=atom(defer);

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
                                         (lhs!=null?lhs.element:null), 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2117:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR ;
    public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException {
        EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token it=null;
        Token i=null;
        Token set201=null;
        Token COLON202=null;
        Token LEFTCUR203=null;
        Token RIGHTCUR205=null;
        EugeneParser.listOfStatements_return listOfStatements204 =null;


        Object it_tree=null;
        Object i_tree=null;
        Object set201_tree=null;
        Object COLON202_tree=null;
        Object LEFTCUR203_tree=null;
        Object RIGHTCUR205_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2118:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2118:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set201=(Token)input.LT(1);

            if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2118:26: (it= ID COLON )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==ID) ) {
                int LA74_1 = input.LA(2);

                if ( (LA74_1==COLON) ) {
                    alt74=1;
                }
            }
            switch (alt74) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2118:27: it= ID COLON
                    {
                    it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3890); 
                    it_tree = 
                    (Object)adaptor.create(it)
                    ;
                    adaptor.addChild(root_0, it_tree);


                    COLON202=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator3892); 
                    COLON202_tree = 
                    (Object)adaptor.create(COLON202)
                    ;
                    adaptor.addChild(root_0, COLON202_tree);


                    }
                    break;

            }


            i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3898); 
            i_tree = 
            (Object)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            LEFTCUR203=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator3900); 
            LEFTCUR203_tree = 
            (Object)adaptor.create(LEFTCUR203)
            ;
            adaptor.addChild(root_0, LEFTCUR203_tree);


            pushFollow(FOLLOW_listOfStatements_in_forall_iterator3905);
            listOfStatements204=listOfStatements(defer);

            state._fsp--;

            adaptor.addChild(root_0, listOfStatements204.getTree());


            if(!defer) {

            }			
            	

            RIGHTCUR205=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator3912); 
            RIGHTCUR205_tree = 
            (Object)adaptor.create(RIGHTCUR205)
            ;
            adaptor.addChild(root_0, RIGHTCUR205_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2127:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException {
        EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set206=null;
        Token LEFTP207=null;
        Token SEMIC208=null;
        Token SEMIC209=null;
        Token RIGHTP210=null;
        Token LEFTCUR211=null;
        Token RIGHTCUR212=null;
        EugeneParser.variableDeclaration_return ds =null;

        EugeneParser.imp_condition_return co =null;

        EugeneParser.assignment_return as =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set206_tree=null;
        Object LEFTP207_tree=null;
        Object SEMIC208_tree=null;
        Object SEMIC209_tree=null;
        Object RIGHTP210_tree=null;
        Object LEFTCUR211_tree=null;
        Object RIGHTCUR212_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2128:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2128:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set206=(Token)input.LT(1);

            if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set206)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP207=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop3930); 
            LEFTP207_tree = 
            (Object)adaptor.create(LEFTP207)
            ;
            adaptor.addChild(root_0, LEFTP207_tree);


            pushFollow(FOLLOW_variableDeclaration_in_for_loop3934);
            ds=variableDeclaration(true);

            state._fsp--;

            adaptor.addChild(root_0, ds.getTree());

            SEMIC208=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop3937); 
            SEMIC208_tree = 
            (Object)adaptor.create(SEMIC208)
            ;
            adaptor.addChild(root_0, SEMIC208_tree);


            pushFollow(FOLLOW_imp_condition_in_for_loop3941);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            SEMIC209=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop3944); 
            SEMIC209_tree = 
            (Object)adaptor.create(SEMIC209)
            ;
            adaptor.addChild(root_0, SEMIC209_tree);


            pushFollow(FOLLOW_assignment_in_for_loop3948);
            as=assignment(true);

            state._fsp--;

            adaptor.addChild(root_0, as.getTree());

            RIGHTP210=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop3951); 
            RIGHTP210_tree = 
            (Object)adaptor.create(RIGHTP210)
            ;
            adaptor.addChild(root_0, RIGHTP210_tree);


            LEFTCUR211=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop3953); 
            LEFTCUR211_tree = 
            (Object)adaptor.create(LEFTCUR211)
            ;
            adaptor.addChild(root_0, LEFTCUR211_tree);


            pushFollow(FOLLOW_listOfStatements_in_for_loop3961);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());


            if(!defer) {
                try {
                    this.for_loop(
                        (ds!=null?((Token)ds.start):null),      // init
                        (co!=null?((Token)co.start):null),      // condition
                        (as!=null?((Token)as.start):null),      // increment
                        (stmts!=null?((Token)stmts.start):null));  // loop-body
                } catch(EugeneReturnException ere) {
                    // TODO!
                } catch(Exception ee) {
                    printError(ee.toString());
                }
            }			
            		

            RIGHTCUR212=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop3968); 
            RIGHTCUR212_tree = 
            (Object)adaptor.create(RIGHTCUR212)
            ;
            adaptor.addChild(root_0, RIGHTCUR212_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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


    public static class listOfStatements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfStatements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2148:1: listOfStatements[boolean defer] : (stmtToken= statement[defer] )+ ;
    public final EugeneParser.listOfStatements_return listOfStatements(boolean defer) throws RecognitionException {
        EugeneParser.listOfStatements_return retval = new EugeneParser.listOfStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.statement_return stmtToken =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2149:2: ( (stmtToken= statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2149:4: (stmtToken= statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2149:4: (stmtToken= statement[defer] )+
            int cnt75=0;
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==ARRAY||(LA75_0 >= ASSERT && LA75_0 <= COLLECTION)||LA75_0==DEVICE||(LA75_0 >= EXIT_LC && LA75_0 <= EXIT_UC)||LA75_0==GRAMMAR||(LA75_0 >= HASHMARK && LA75_0 <= ID)||LA75_0==INTERACTION||(LA75_0 >= LC_FOR && LA75_0 <= LC_INCLUDE)||LA75_0==LC_PIGEON||LA75_0==NUM||(LA75_0 >= PART && LA75_0 <= PIGEON)||(LA75_0 >= PRINT && LA75_0 <= PROPERTY)||(LA75_0 >= RULE && LA75_0 <= SEMIC)||(LA75_0 >= TXT && LA75_0 <= TYPE)||(LA75_0 >= UC_FOR && LA75_0 <= UC_INCLUDE)) ) {
                    alt75=1;
                }


                switch (alt75) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2149:5: stmtToken= statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_listOfStatements3987);
            	    stmtToken=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmtToken.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt75 >= 1 ) break loop75;
                        EarlyExitException eee =
                            new EarlyExitException(75, input);
                        throw eee;
                }
                cnt75++;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2157:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )* ;
    public final EugeneParser.expr_return expr(boolean defer) throws RecognitionException {
        EugeneParser.expr_return retval = new EugeneParser.expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token pl=null;
        Token mi=null;
        EugeneParser.multExpr_return e =null;


        Object pl_tree=null;
        Object mi_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2159:2: (e= multExpr[defer] (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2159:4: e= multExpr[defer] (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_expr4015);
            e=multExpr(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer) {
                retval.p = copyVariable((e!=null?e.p:null));
                retval.instance = (e!=null?e.instance:null);
                retval.index = (e!=null?e.index:0);
                if ((e!=null?e.listAddress:null) != null) {
                    retval.listAddress = (e!=null?e.listAddress:null);
                }
                retval.primVariable = (e!=null?e.primVariable:null);

                retval.element = (e!=null?e.element:null);
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2171:5: (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )*
            loop76:
            do {
                int alt76=3;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==PLUS) ) {
                    alt76=1;
                }
                else if ( (LA76_0==MINUS) ) {
                    alt76=2;
                }


                switch (alt76) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2171:6: pl= PLUS e= multExpr[defer]
            	    {
            	    pl=(Token)match(input,PLUS,FOLLOW_PLUS_in_expr4024); 
            	    pl_tree = 
            	    (Object)adaptor.create(pl)
            	    ;
            	    adaptor.addChild(root_0, pl_tree);


            	    pushFollow(FOLLOW_multExpr_in_expr4028);
            	    e=multExpr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    if(!defer) {
            	        try {
            	            this.interp.doMinPlusOp((e!=null?e.p:null), retval.p, (pl!=null?pl.getText():null));
            	        } catch(EugeneException ee) {
            	            printError(ee.getLocalizedMessage());
            	        }
            	        retval.element = null;
            	    }
            	    	

            	    }
            	    break;
            	case 2 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2180:7: mi= MINUS e= multExpr[defer]
            	    {
            	    mi=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr4038); 
            	    mi_tree = 
            	    (Object)adaptor.create(mi)
            	    ;
            	    adaptor.addChild(root_0, mi_tree);


            	    pushFollow(FOLLOW_multExpr_in_expr4042);
            	    e=multExpr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    if(!defer) {
            	        try {
            	            this.interp.doMinPlusOp((e!=null?e.p:null), retval.p, (mi!=null?mi.getText():null));
            	        } catch(EugeneException ee) {
            	            printError(ee.getLocalizedMessage());
            	        }
            	        retval.element = null;
            	    }
            	    	

            	    }
            	    break;

            	default :
            	    break loop76;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2192:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2194:2: (e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2194:4: e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_multExpr4068);
            e=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            if(!defer) {
                retval.p = copyVariable((e!=null?e.p:null));
                if ((e!=null?e.instance:null) != null) {
                    retval.instance = (e!=null?e.instance:null);
                }
                retval.index = (e!=null?e.index:0);
                if ((e!=null?e.listAddress:null) != null) {
                    retval.listAddress = (e!=null?e.listAddress:null);
                }
                retval.primVariable = (e!=null?e.primVariable:null);
            			
                retval.element = (e!=null?e.element:null);
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2208:5: ( (mul= MULT |div= DIV ) e= atom[defer] )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==DIV||LA78_0==MULT) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2208:7: (mul= MULT |div= DIV ) e= atom[defer]
            	    {
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2208:7: (mul= MULT |div= DIV )
            	    int alt77=2;
            	    int LA77_0 = input.LA(1);

            	    if ( (LA77_0==MULT) ) {
            	        alt77=1;
            	    }
            	    else if ( (LA77_0==DIV) ) {
            	        alt77=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 77, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt77) {
            	        case 1 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2208:8: mul= MULT
            	            {
            	            mul=(Token)match(input,MULT,FOLLOW_MULT_in_multExpr4079); 
            	            mul_tree = 
            	            (Object)adaptor.create(mul)
            	            ;
            	            adaptor.addChild(root_0, mul_tree);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2208:17: div= DIV
            	            {
            	            div=(Token)match(input,DIV,FOLLOW_DIV_in_multExpr4083); 
            	            div_tree = 
            	            (Object)adaptor.create(div)
            	            ;
            	            adaptor.addChild(root_0, div_tree);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_atom_in_multExpr4088);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2224:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) | ID oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR );
    public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
        EugeneParser.atom_return retval = new EugeneParser.atom_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token t=null;
        Token f=null;
        Token MINUS213=null;
        Token ID214=null;
        Token STRING215=null;
        Token char_literal216=null;
        Token char_literal218=null;
        Token LEFTSBR219=null;
        Token RIGHTSBR221=null;
        EugeneParser.object_access_return oc =null;

        EugeneParser.expr_return expr217 =null;

        EugeneParser.list_return list220 =null;


        Object n_tree=null;
        Object t_tree=null;
        Object f_tree=null;
        Object MINUS213_tree=null;
        Object ID214_tree=null;
        Object STRING215_tree=null;
        Object char_literal216_tree=null;
        Object char_literal218_tree=null;
        Object LEFTSBR219_tree=null;
        Object RIGHTSBR221_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2226:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) ) | ID oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR )
            int alt82=7;
            switch ( input.LA(1) ) {
            case NUMBER:
            case REAL:
                {
                alt82=1;
                }
                break;
            case MINUS:
                {
                alt82=2;
                }
                break;
            case FALSE_LC:
            case FALSE_UC:
            case TRUE_LC:
            case TRUE_UC:
                {
                alt82=3;
                }
                break;
            case ID:
                {
                alt82=4;
                }
                break;
            case STRING:
                {
                alt82=5;
                }
                break;
            case LEFTP:
                {
                alt82=6;
                }
                break;
            case LEFTSBR:
                {
                alt82=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;

            }

            switch (alt82) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2226:4: (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2226:4: (n= NUMBER |n= REAL )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2226:5: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4115); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2226:16: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4121); 
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
                    		}
                    		

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2233:4: MINUS (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS213=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom4131); 
                    MINUS213_tree = 
                    (Object)adaptor.create(MINUS213)
                    ;
                    adaptor.addChild(root_0, MINUS213_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2233:10: (n= NUMBER |n= REAL )
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==NUMBER) ) {
                        alt80=1;
                    }
                    else if ( (LA80_0==REAL) ) {
                        alt80=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 80, 0, input);

                        throw nvae;

                    }
                    switch (alt80) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2233:11: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4136); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2233:22: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4142); 
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
                    		}
                    		

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2240:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2240:4: (t= ( TRUE_LC | TRUE_UC ) |f= ( FALSE_LC | FALSE_UC ) )
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( ((LA81_0 >= TRUE_LC && LA81_0 <= TRUE_UC)) ) {
                        alt81=1;
                    }
                    else if ( ((LA81_0 >= FALSE_LC && LA81_0 <= FALSE_UC)) ) {
                        alt81=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 81, 0, input);

                        throw nvae;

                    }
                    switch (alt81) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2240:5: t= ( TRUE_LC | TRUE_UC )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2240:27: f= ( FALSE_LC | FALSE_UC )
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
                    		}
                    		

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2251:4: ID oc= object_access[defer, $element]
                    {
                    root_0 = (Object)adaptor.nil();


                    ID214=(Token)match(input,ID,FOLLOW_ID_in_atom4179); 
                    ID214_tree = 
                    (Object)adaptor.create(ID214)
                    ;
                    adaptor.addChild(root_0, ID214_tree);



                    	if(!defer) {
                    		try {
                    			retval.element = this.interp.get((ID214!=null?ID214.getText():null));
                    					
                    			if(null != retval.element) {
                    				if(retval.element instanceof Variable) {
                    					retval.p = copyVariable((Variable)retval.element);
                    					retval.primVariable = (Variable)retval.element;
                    				}
                    			} else {
                    				throw new EugeneException((ID214!=null?ID214.getText():null) + " is not declared.");
                    			}
                    		} catch(EugeneException ee) {
                    			printError(ee.toString());
                    		}
                    	}
                    		

                    pushFollow(FOLLOW_object_access_in_atom4187);
                    oc=object_access(defer, retval.element);

                    state._fsp--;

                    adaptor.addChild(root_0, oc.getTree());


                    if(!defer) {
                        retval.element = (oc!=null?oc.child:null);
                        if(retval.element instanceof Variable) {
                            retval.p = (Variable)retval.element;
                        }
                    }		
                    		

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2277:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING215=(Token)match(input,STRING,FOLLOW_STRING_in_atom4196); 
                    STRING215_tree = 
                    (Object)adaptor.create(STRING215)
                    ;
                    adaptor.addChild(root_0, STRING215_tree);



                    		if(!defer) {
                    			retval.p.type = EugeneConstants.TXT;
                    			retval.p.txt = (STRING215!=null?STRING215.getText():null).substring(1, (STRING215!=null?STRING215.getText():null).length()-1);
                    		}
                    		

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2284:4: '(' expr[defer] ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal216=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4205); 
                    char_literal216_tree = 
                    (Object)adaptor.create(char_literal216)
                    ;
                    adaptor.addChild(root_0, char_literal216_tree);


                    pushFollow(FOLLOW_expr_in_atom4207);
                    expr217=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expr217.getTree());

                    char_literal218=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4210); 
                    char_literal218_tree = 
                    (Object)adaptor.create(char_literal218)
                    ;
                    adaptor.addChild(root_0, char_literal218_tree);



                    		if(!defer) {
                    			retval.p = (expr217!=null?expr217.p:null);
                    			retval.primVariable = (expr217!=null?expr217.primVariable:null);
                    		}
                    		

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2291:5: LEFTSBR list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR219=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4220); 
                    LEFTSBR219_tree = 
                    (Object)adaptor.create(LEFTSBR219)
                    ;
                    adaptor.addChild(root_0, LEFTSBR219_tree);


                    pushFollow(FOLLOW_list_in_atom4222);
                    list220=list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list220.getTree());

                    RIGHTSBR221=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4225); 
                    RIGHTSBR221_tree = 
                    (Object)adaptor.create(RIGHTSBR221)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR221_tree);



                    		if(!defer) {
                    			retval.p = (list220!=null?list220.listPrim:null);
                    			retval.primVariable = (list220!=null?list220.listPrim:null);
                    			typeList="";
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2378:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
    public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
        EugeneParser.list_return retval = new EugeneParser.list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA222=null;
        EugeneParser.expr_return str1 =null;

        EugeneParser.expr_return str2 =null;


        Object COMMA222_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2380:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2380:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_list4251);
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2399:5: ( COMMA str2= expr[defer] )*
            loop83:
            do {
                int alt83=2;
                int LA83_0 = input.LA(1);

                if ( (LA83_0==COMMA) ) {
                    alt83=1;
                }


                switch (alt83) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2399:6: COMMA str2= expr[defer]
            	    {
            	    COMMA222=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4258); 
            	    COMMA222_tree = 
            	    (Object)adaptor.create(COMMA222)
            	    ;
            	    adaptor.addChild(root_0, COMMA222_tree);


            	    pushFollow(FOLLOW_expr_in_list4262);
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
            	    break loop83;
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


    public static class object_access_return extends ParserRuleReturnScope {
        public NamedElement child;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "object_access"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2432:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
    public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
        EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token DOT223=null;
        Token LEFTSBR224=null;
        Token RIGHTSBR225=null;
        EugeneParser.expr_return exp =null;

        EugeneParser.object_access_return o =null;


        Object id_tree=null;
        Object DOT223_tree=null;
        Object LEFTSBR224_tree=null;
        Object RIGHTSBR225_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2434:2: (| ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==COMMA||LA85_0==DIV||LA85_0==EQUALS||LA85_0==GEQUAL||LA85_0==GTHAN||LA85_0==LEQUAL||(LA85_0 >= LTHAN && LA85_0 <= MINUS)||(LA85_0 >= MULT && LA85_0 <= NEQUAL)||LA85_0==PLUS||(LA85_0 >= RIGHTP && LA85_0 <= RIGHTSBR)||LA85_0==SEMIC||LA85_0==116||(LA85_0 >= 118 && LA85_0 <= 119)||LA85_0==122||(LA85_0 >= 125 && LA85_0 <= 126)||LA85_0==128||(LA85_0 >= 141 && LA85_0 <= 142)||LA85_0==155||(LA85_0 >= 157 && LA85_0 <= 158)||LA85_0==161||(LA85_0 >= 164 && LA85_0 <= 165)||LA85_0==167||(LA85_0 >= 180 && LA85_0 <= 181)) ) {
                alt85=1;
            }
            else if ( (LA85_0==DOT||LA85_0==LEFTSBR) ) {
                alt85=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;

            }
            switch (alt85) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2435:2: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.child = parent;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2440:4: ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2440:4: ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==DOT) ) {
                        alt84=1;
                    }
                    else if ( (LA84_0==LEFTSBR) ) {
                        alt84=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 84, 0, input);

                        throw nvae;

                    }
                    switch (alt84) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2440:5: DOT id= ID
                            {
                            DOT223=(Token)match(input,DOT,FOLLOW_DOT_in_object_access4302); 
                            DOT223_tree = 
                            (Object)adaptor.create(DOT223)
                            ;
                            adaptor.addChild(root_0, DOT223_tree);


                            id=(Token)match(input,ID,FOLLOW_ID_in_object_access4306); 
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
                                    printError(ee.toString());
                                }
                            }	
                            	

                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2453:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
                            {
                            LEFTSBR224=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access4314); 
                            LEFTSBR224_tree = 
                            (Object)adaptor.create(LEFTSBR224)
                            ;
                            adaptor.addChild(root_0, LEFTSBR224_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2453:12: (exp= expr[defer] )
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2453:13: exp= expr[defer]
                            {
                            pushFollow(FOLLOW_expr_in_object_access4319);
                            exp=expr(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, exp.getTree());

                            }


                            RIGHTSBR225=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access4323); 
                            RIGHTSBR225_tree = 
                            (Object)adaptor.create(RIGHTSBR225)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR225_tree);



                            if(!defer) {
                                try {
                                    if(null != (exp!=null?exp.p:null) && EugeneConstants.NUM.equals((exp!=null?exp.p:null).getType())) {
                                        retval.child = parent.getElement((int)((exp!=null?exp.p:null).getNum()));
                                        if(null == retval.child) {
                                            throw new EugeneException(parent.getName() + " does not contain " + (id!=null?id.getText():null));
                                        }
                                    } else {
                                        throw new EugeneException("Invalid index " + (exp!=null?exp.p:null) + "!");
                                    }
                                } catch(EugeneException ee) {
                                    printError(ee.toString());
                                }
                            }	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_object_access_in_object_access4330);
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


    public static class dataExchange_return extends ParserRuleReturnScope {
        public NamedElement e;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "dataExchange"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2478:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] );
    public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
        EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.sbolStatement_return s =null;

        EugeneParser.pigeonStatement_return p =null;

        EugeneParser.importStatement_return i =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2480:2: (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] )
            int alt86=3;
            switch ( input.LA(1) ) {
            case SBOL:
                {
                alt86=1;
                }
                break;
            case LC_PIGEON:
            case PIGEON:
                {
                alt86=2;
                }
                break;
            case LC_IMPORT:
            case UC_IMPORT:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2480:4: s= sbolStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_sbolStatement_in_dataExchange4356);
                    s=sbolStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());


                    if(!defer) {
                        retval.e = (s!=null?s.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2485:4: p= pigeonStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pigeonStatement_in_dataExchange4366);
                    p=pigeonStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2486:4: i= importStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_importStatement_in_dataExchange4374);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2497:1: includeStatement[boolean defer] : ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING ;
    public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
        EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token HASHMARK226=null;
        Token set227=null;

        Object file_tree=null;
        Object HASHMARK226_tree=null;
        Object set227_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2498:2: ( ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2498:4: ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2498:4: ( HASHMARK )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==HASHMARK) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2498:5: HASHMARK
                    {
                    HASHMARK226=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement4396); 
                    HASHMARK226_tree = 
                    (Object)adaptor.create(HASHMARK226)
                    ;
                    adaptor.addChild(root_0, HASHMARK226_tree);


                    }
                    break;

            }


            set227=(Token)input.LT(1);

            if ( input.LA(1)==LC_INCLUDE||input.LA(1)==UC_INCLUDE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set227)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement4408); 
            file_tree = 
            (Object)adaptor.create(file)
            ;
            adaptor.addChild(root_0, file_tree);



            if(!defer) {
                try {
                    this.interp.includeFile((file!=null?file.getText():null));
                } catch(EugeneException ee) {
                    printError(ee.toString());
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2510:1: importStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP ;
    public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
        EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token set228=null;
        Token LEFTP229=null;
        Token RIGHTP230=null;

        Object file_tree=null;
        Object set228_tree=null;
        Object LEFTP229_tree=null;
        Object RIGHTP230_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2512:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2512:4: ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set228=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
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


            LEFTP229=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement4435); 
            LEFTP229_tree = 
            (Object)adaptor.create(LEFTP229)
            ;
            adaptor.addChild(root_0, LEFTP229_tree);


            file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement4439); 
            file_tree = 
            (Object)adaptor.create(file)
            ;
            adaptor.addChild(root_0, file_tree);



            if(!defer) {
                try {
                    retval.e = this.interp.importFile((file!=null?file.getText():null));
                } catch(EugeneException ee) {
                    printError(ee.toString());
                }
            }
            	

            RIGHTP230=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement4443); 
            RIGHTP230_tree = 
            (Object)adaptor.create(RIGHTP230)
            ;
            adaptor.addChild(root_0, RIGHTP230_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2524:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) ;
    public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SBOL231=null;
        Token DOT232=null;
        EugeneParser.sbolImportStatement_return i =null;

        EugeneParser.sbolExportStatement_return sbolExportStatement233 =null;


        Object SBOL231_tree=null;
        Object DOT232_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2526:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2526:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            {
            root_0 = (Object)adaptor.nil();


            SBOL231=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement4465); 
            SBOL231_tree = 
            (Object)adaptor.create(SBOL231)
            ;
            adaptor.addChild(root_0, SBOL231_tree);


            DOT232=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement4467); 
            DOT232_tree = 
            (Object)adaptor.create(DOT232)
            ;
            adaptor.addChild(root_0, DOT232_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2526:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==EXPORT) ) {
                alt88=1;
            }
            else if ( (LA88_0==LC_IMPORT||LA88_0==UC_IMPORT) ) {
                alt88=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;

            }
            switch (alt88) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2526:14: sbolExportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement4470);
                    sbolExportStatement233=sbolExportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sbolExportStatement233.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2526:43: i= sbolImportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement4477);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2533:1: sbolExportStatement[boolean defer] : EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
    public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token filenameToken=null;
        Token EXPORT234=null;
        Token LEFTP235=null;
        Token COMMA236=null;
        Token RIGHTP237=null;

        Object idToken_tree=null;
        Object filenameToken_tree=null;
        Object EXPORT234_tree=null;
        Object LEFTP235_tree=null;
        Object COMMA236_tree=null;
        Object RIGHTP237_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2534:2: ( EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2534:4: EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            EXPORT234=(Token)match(input,EXPORT,FOLLOW_EXPORT_in_sbolExportStatement4494); 
            EXPORT234_tree = 
            (Object)adaptor.create(EXPORT234)
            ;
            adaptor.addChild(root_0, EXPORT234_tree);


            LEFTP235=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement4496); 
            LEFTP235_tree = 
            (Object)adaptor.create(LEFTP235)
            ;
            adaptor.addChild(root_0, LEFTP235_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement4500); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            COMMA236=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement4502); 
            COMMA236_tree = 
            (Object)adaptor.create(COMMA236)
            ;
            adaptor.addChild(root_0, COMMA236_tree);


            filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement4506); 
            filenameToken_tree = 
            (Object)adaptor.create(filenameToken)
            ;
            adaptor.addChild(root_0, filenameToken_tree);


            RIGHTP237=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement4508); 
            RIGHTP237_tree = 
            (Object)adaptor.create(RIGHTP237)
            ;
            adaptor.addChild(root_0, RIGHTP237_tree);



            if(!defer) {
                try {
                    interp.exportToSBOL(
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2547:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP ;
    public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token fileToken=null;
        Token set238=null;
        Token LEFTP239=null;
        Token RIGHTP240=null;

        Object fileToken_tree=null;
        Object set238_tree=null;
        Object LEFTP239_tree=null;
        Object RIGHTP240_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2549:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2549:4: ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set238=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set238)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP239=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement4537); 
            LEFTP239_tree = 
            (Object)adaptor.create(LEFTP239)
            ;
            adaptor.addChild(root_0, LEFTP239_tree);


            fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement4541); 
            fileToken_tree = 
            (Object)adaptor.create(fileToken)
            ;
            adaptor.addChild(root_0, fileToken_tree);


            RIGHTP240=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement4543); 
            RIGHTP240_tree = 
            (Object)adaptor.create(RIGHTP240)
            ;
            adaptor.addChild(root_0, RIGHTP240_tree);



            if(!defer) {
                try {
                    retval.e = interp.importSBOL((fileToken!=null?fileToken.getText():null));
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2594:1: pigeonStatement[boolean defer] : ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.pigeonStatement_return pigeonStatement(boolean defer) throws RecognitionException {
        EugeneParser.pigeonStatement_return retval = new EugeneParser.pigeonStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token set241=null;
        Token LEFTP242=null;
        Token RIGHTP243=null;

        Object idToken_tree=null;
        Object set241_tree=null;
        Object LEFTP242_tree=null;
        Object RIGHTP243_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2595:2: ( ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2595:4: ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set241=(Token)input.LT(1);

            if ( input.LA(1)==LC_PIGEON||input.LA(1)==PIGEON ) {
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


            LEFTP242=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_pigeonStatement4572); 
            LEFTP242_tree = 
            (Object)adaptor.create(LEFTP242)
            ;
            adaptor.addChild(root_0, LEFTP242_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_pigeonStatement4576); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP243=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_pigeonStatement4578); 
            RIGHTP243_tree = 
            (Object)adaptor.create(RIGHTP243)
            ;
            adaptor.addChild(root_0, RIGHTP243_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2643:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
    public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
        EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token n=null;
        Token ASSERT244=null;
        Token LEFTP245=null;
        Token DOT246=null;
        Token SIZE247=null;
        Token LEFTP248=null;
        Token RIGHTP249=null;
        Token EQUALS250=null;
        Token EQUALS251=null;
        Token RIGHTP252=null;

        Object id_tree=null;
        Object n_tree=null;
        Object ASSERT244_tree=null;
        Object LEFTP245_tree=null;
        Object DOT246_tree=null;
        Object SIZE247_tree=null;
        Object LEFTP248_tree=null;
        Object RIGHTP249_tree=null;
        Object EQUALS250_tree=null;
        Object EQUALS251_tree=null;
        Object RIGHTP252_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2644:2: (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==SEMIC) ) {
                alt89=1;
            }
            else if ( (LA89_0==ASSERT) ) {
                alt89=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;

            }
            switch (alt89) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2644:5: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2644:7: ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    ASSERT244=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements4602); 
                    ASSERT244_tree = 
                    (Object)adaptor.create(ASSERT244)
                    ;
                    adaptor.addChild(root_0, ASSERT244_tree);


                    LEFTP245=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4604); 
                    LEFTP245_tree = 
                    (Object)adaptor.create(LEFTP245)
                    ;
                    adaptor.addChild(root_0, LEFTP245_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements4608); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT246=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements4610); 
                    DOT246_tree = 
                    (Object)adaptor.create(DOT246)
                    ;
                    adaptor.addChild(root_0, DOT246_tree);


                    SIZE247=(Token)match(input,SIZE,FOLLOW_SIZE_in_testStatements4612); 
                    SIZE247_tree = 
                    (Object)adaptor.create(SIZE247)
                    ;
                    adaptor.addChild(root_0, SIZE247_tree);


                    LEFTP248=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4614); 
                    LEFTP248_tree = 
                    (Object)adaptor.create(LEFTP248)
                    ;
                    adaptor.addChild(root_0, LEFTP248_tree);


                    RIGHTP249=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4616); 
                    RIGHTP249_tree = 
                    (Object)adaptor.create(RIGHTP249)
                    ;
                    adaptor.addChild(root_0, RIGHTP249_tree);


                    EQUALS250=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4618); 
                    EQUALS250_tree = 
                    (Object)adaptor.create(EQUALS250)
                    ;
                    adaptor.addChild(root_0, EQUALS250_tree);


                    EQUALS251=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4620); 
                    EQUALS251_tree = 
                    (Object)adaptor.create(EQUALS251)
                    ;
                    adaptor.addChild(root_0, EQUALS251_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements4624); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP252=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4626); 
                    RIGHTP252_tree = 
                    (Object)adaptor.create(RIGHTP252)
                    ;
                    adaptor.addChild(root_0, RIGHTP252_tree);



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

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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


 

    public static final BitSet FOLLOW_statement_in_prog840 = new BitSet(new long[]{0x800043E268184F40L,0x00000007C606079EL});
    public static final BitSet FOLLOW_EOF_in_prog845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeStatement_in_statement871 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_statement875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_statement882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_statement885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_printStatement_in_statement891 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_statement894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement899 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_statement902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_statement910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_statement917 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_statement920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imperativeStatements_in_statement927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predefined_statements_in_statement933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_statement936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testStatements_in_predefined_statements949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_predefined_statements955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement1003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instantiation_in_declarationStatement1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_grammarDeclaration_in_declarationStatement1027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1051 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_numdecl_in_variableDeclaration1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1066 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_txtdecl_in_variableDeclaration1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1081 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1083 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1085 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1100 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1104 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_variableDeclaration1119 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_booldecl_in_variableDeclaration1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1150 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1157 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1167 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_numdecl1169 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_numdecl1174 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1184 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1206 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1213 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1226 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_txtdecl1228 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_txtdecl1232 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1240 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1262 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1269 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1281 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1283 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_txtlistdecl1289 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1297 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1319 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1326 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1338 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_numlistdecl1340 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_numlistdecl1345 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1353 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1375 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_booldecl1382 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_booldecl_in_booldecl1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1394 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_booldecl1396 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_booldecl1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1418 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration1422 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1424 = new BitSet(new long[]{0x8000000000000600L,0x0000000002000000L});
    public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1456 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1475 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_propertyType1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_typeDeclaration1515 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_typeDeclaration1520 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1523 = new BitSet(new long[]{0x0000000040000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1528 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_partTypeDeclaration1552 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_partTypeDeclaration1561 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1564 = new BitSet(new long[]{0x0000000040000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1601 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1608 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1612 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_containerDeclaration1618 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1623 = new BitSet(new long[]{0x8206000248C04E40L,0x0000000007C28C07L});
    public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1626 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1664 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_atom_in_list_of_declarations1671 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_declarations1679 = new BitSet(new long[]{0x8206000248C04E40L,0x0000000007C20C07L});
    public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations1683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instantiation1711 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_instantiation1717 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_instantiation1721 = new BitSet(new long[]{0x0206000040C20000L,0x0000000001C08801L});
    public static final BitSet FOLLOW_listOfDotValues_in_instantiation1726 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_listOfValues_in_instantiation1731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_instantiation1736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1760 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1764 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1770 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1782 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfDotValues1785 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1787 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1791 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1799 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1813 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr_in_listOfValues1834 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfValues1840 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_listOfValues1846 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration1865 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_deviceDeclaration1869 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration1872 = new BitSet(new long[]{0x0204000040000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration1877 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration1882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_in_deviceComponents1913 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_deviceComponents1919 = new BitSet(new long[]{0x0204000040000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceComponents1923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_selection1947 = new BitSet(new long[]{0x0200000040000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_selection_list_in_selection1951 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_selection1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection1963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection_list1992 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_PIPE_in_selection_list1998 = new BitSet(new long[]{0x0200000040000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_selection_list_in_selection_list2002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_device_component2028 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_device_component2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_assignment_in_assignment2058 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2061 = new BitSet(new long[]{0x0206410040C00020L,0x0000000201C40A19L});
    public static final BitSet FOLLOW_AMP_in_assignment2066 = new BitSet(new long[]{0x0206410040C00000L,0x0000000201C40A19L});
    public static final BitSet FOLLOW_rhs_assignment_in_assignment2072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lhs_assignment2087 = new BitSet(new long[]{0x0004000000020000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lhs_access2109 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_lhs_access2113 = new BitSet(new long[]{0x0004000000020000L});
    public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2117 = new BitSet(new long[]{0x0000000040000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_set_in_lhs_access2119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2125 = new BitSet(new long[]{0x0004000000020000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_access2128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_rhs_assignment2151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_rhs_assignment2171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_listOfIDs2199 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_listOfIDs2208 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_ruleDeclaration2236 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2240 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2242 = new BitSet(new long[]{0x02469C0040000000L,0xFFFFFCB800400801L,0x00EFFFFFFFFFDFFFL});
    public static final BitSet FOLLOW_set_in_ruleDeclaration2247 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2255 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_ruleDeclaration2257 = new BitSet(new long[]{0x02468C0040000000L,0xFFFFFC9800400801L,0x00EFFFFFFFFFDFFFL});
    public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2663 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQUAL_in_relationalOperators2670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTHAN_in_relationalOperators2675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GTHAN_in_relationalOperators2680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEQUAL_in_relationalOperators2685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GEQUAL_in_relationalOperators2690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_predicate_in_cnf_rule2791 = new BitSet(new long[]{0x0020000400000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_set_in_cnf_rule2799 = new BitSet(new long[]{0x02468C0040000000L,0xFFFFFC9800400801L,0x00EFFFFFFFFFDFFFL});
    public static final BitSet FOLLOW_cnf_rule_in_cnf_rule2809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2839 = new BitSet(new long[]{0x0080200000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_set_in_or_predicate2845 = new BitSet(new long[]{0x02468C0040000000L,0xFFFFFC9800400801L,0x00EFFFFFFFFFDFFFL});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2855 = new BitSet(new long[]{0x0080200000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_set_in_negated_predicate2883 = new BitSet(new long[]{0x0206840040000000L,0xFFFFFC8800400801L,0x00EFFFFFFFFFDFFFL});
    public static final BitSet FOLLOW_predicate_in_negated_predicate2893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_negated_predicate2903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_predicate2930 = new BitSet(new long[]{0x0000840000000000L,0xFFFFFC8800000000L,0x00EFFFFFFFFFDFFFL});
    public static final BitSet FOLLOW_ruleOperator_in_predicate2940 = new BitSet(new long[]{0x0004000040000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_operand_in_predicate2949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_predicate2963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionRule_in_predicate2972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand3003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_operand3012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_operand3019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_NUMBER_in_operand3023 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_operand3025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionRule3051 = new BitSet(new long[]{0x1108000014040000L,0x64D0000000000000L,0x003000B268006001L});
    public static final BitSet FOLLOW_exp_op_in_expressionRule3056 = new BitSet(new long[]{0x0202000040000000L,0x0000000000400801L});
    public static final BitSet FOLLOW_expression_in_expressionRule3061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_operand_in_expression3085 = new BitSet(new long[]{0x0A00000000010002L,0x0000000000000040L});
    public static final BitSet FOLLOW_exp_operator_in_expression3094 = new BitSet(new long[]{0x0202000040000000L,0x0000000000400801L});
    public static final BitSet FOLLOW_expression_in_expression3099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_expression3111 = new BitSet(new long[]{0x0202000040000000L,0x0000000000400801L});
    public static final BitSet FOLLOW_expression_in_expression3113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_expression3116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp_operator3135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operator3143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_exp_operator3150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_exp_operator3157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_exp_operand3187 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_exp_operand3189 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_exp_operand3198 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3208 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3210 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3229 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3249 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp_operand3262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOperators_in_exp_op3289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GRAMMAR_in_grammarDeclaration3308 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_grammarDeclaration3312 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_grammarDeclaration3314 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_list_of_production_rules_in_grammarDeclaration3316 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_grammarDeclaration3319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_production_rule_in_list_of_production_rules3331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_list_of_production_rules3334 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_list_of_production_rules_in_list_of_production_rules3337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_production_rule3357 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ARROW_in_production_rule3361 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_right_hand_side_in_production_rule3363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_right_hand_side3379 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_right_hand_side3384 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_right_hand_side_in_right_hand_side3386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_right_hand_side3394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3427 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_interactionDeclaration3431 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3433 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3437 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3463 = new BitSet(new long[]{0x0000840000000000L,0x0000008800000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3467 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_interaction3472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3481 = new BitSet(new long[]{0x0000840000000000L,0x0000008800000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3485 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interaction3488 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_interaction_in_interaction3492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_interaction3495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRODUCT_in_functionCall3562 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_PERMUTE_in_functionCall3566 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_functionCall3569 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_functionCall3573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_functionCall3575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINTLN_in_printStatement3593 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3595 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_printStatement3609 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3611 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3615 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_toPrint3639 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_toPrint_prime_in_toPrint3644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_toPrint_prime3670 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_toPrint_in_toPrint_prime3674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_imperativeStatements3694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements3700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_imperativeStatements3706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ifStatement3732 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3738 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3742 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3745 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3747 = new BitSet(new long[]{0x800043E268184F40L,0x00000007C606079EL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3758 = new BitSet(new long[]{0x0000001800000002L,0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3773 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3779 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3786 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3788 = new BitSet(new long[]{0x800043E268184F40L,0x00000007C606079EL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3799 = new BitSet(new long[]{0x0000001800000002L,0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3815 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3821 = new BitSet(new long[]{0x800043E268184F40L,0x00000007C606079EL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_imp_condition3856 = new BitSet(new long[]{0x1108000014040000L,0x64D0000000000000L,0x003000B268006001L});
    public static final BitSet FOLLOW_relationalOperators_in_imp_condition3861 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_atom_in_imp_condition3865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_forall_iterator3881 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator3890 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_forall_iterator3892 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator3898 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator3900 = new BitSet(new long[]{0x800043E268184F40L,0x00000007C606079EL});
    public static final BitSet FOLLOW_listOfStatements_in_forall_iterator3905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator3912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_for_loop3924 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_for_loop3930 = new BitSet(new long[]{0x8000000000000600L,0x0000000002000000L});
    public static final BitSet FOLLOW_variableDeclaration_in_for_loop3934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop3937 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_imp_condition_in_for_loop3941 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop3944 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_assignment_in_for_loop3948 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_for_loop3951 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_for_loop3953 = new BitSet(new long[]{0x800043E268184F40L,0x00000007C606079EL});
    public static final BitSet FOLLOW_listOfStatements_in_for_loop3961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_for_loop3968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_listOfStatements3987 = new BitSet(new long[]{0x800043E268184F42L,0x00000007C606079EL});
    public static final BitSet FOLLOW_multExpr_in_expr4015 = new BitSet(new long[]{0x0200000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_PLUS_in_expr4024 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_multExpr_in_expr4028 = new BitSet(new long[]{0x0200000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_MINUS_in_expr4038 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_multExpr_in_expr4042 = new BitSet(new long[]{0x0200000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_atom_in_multExpr4068 = new BitSet(new long[]{0x0800000000010002L});
    public static final BitSet FOLLOW_MULT_in_multExpr4079 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_DIV_in_multExpr4083 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_atom_in_multExpr4088 = new BitSet(new long[]{0x0800000000010002L});
    public static final BitSet FOLLOW_NUMBER_in_atom4115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom4131 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000801L});
    public static final BitSet FOLLOW_NUMBER_in_atom4136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom4165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom4179 = new BitSet(new long[]{0x0004000000020000L});
    public static final BitSet FOLLOW_object_access_in_atom4187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_atom4205 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_atom4207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_atom4210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_atom4220 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_list_in_atom4222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_atom4225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_list4251 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_list4258 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_list4262 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_DOT_in_object_access4302 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_object_access4306 = new BitSet(new long[]{0x0004000000020000L});
    public static final BitSet FOLLOW_LEFTSBR_in_object_access4314 = new BitSet(new long[]{0x0206000040C00000L,0x0000000001C00801L});
    public static final BitSet FOLLOW_expr_in_object_access4319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_object_access4323 = new BitSet(new long[]{0x0004000000020000L});
    public static final BitSet FOLLOW_object_access_in_object_access4330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolStatement_in_dataExchange4356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pigeonStatement_in_dataExchange4366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importStatement_in_dataExchange4374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASHMARK_in_includeStatement4396 = new BitSet(new long[]{0x0000020000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_set_in_includeStatement4400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_STRING_in_includeStatement4408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_importStatement4429 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_importStatement4435 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_STRING_in_importStatement4439 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_importStatement4443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBOL_in_sbolStatement4465 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_sbolStatement4467 = new BitSet(new long[]{0x0000010000200000L,0x0000000200000000L});
    public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement4470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement4477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPORT_in_sbolExportStatement4494 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement4496 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_sbolExportStatement4500 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_sbolExportStatement4502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_STRING_in_sbolExportStatement4506 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement4508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolImportStatement4531 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement4537 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_STRING_in_sbolImportStatement4541 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement4543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_pigeonStatement4566 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_pigeonStatement4572 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_pigeonStatement4576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_pigeonStatement4578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_testStatements4602 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4604 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ID_in_testStatements4608 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_testStatements4610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SIZE_in_testStatements4612 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4616 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4618 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4620 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements4624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4626 = new BitSet(new long[]{0x0000000000000002L});

}