// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g 2014-10-09 10:04:32

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADDPROPS", "AMP", "ARRAY", "ARROW", "ASSERT", "BOOLEAN", "COLLECTION", "COLON", "COMMA", "DEVICE", "DIGIT", "DIV", "DOT", "EQUALS", "EXIT_LC", "EXIT_UC", "EXPORT", "FALSE", "FLEXIBLE", "GENBANK", "GEQUAL", "GRAMMAR", "GTHAN", "HASHMARK", "ID", "IMAGE", "INT", "INTERACTION", "LC_AND", "LC_ELSE", "LC_ELSEIF", "LC_FOR", "LC_FORALL", "LC_IF", "LC_IMPORT", "LC_INCLUDE", "LC_INDUCES", "LC_NOT", "LC_ON", "LC_OR", "LC_PIGEON", "LC_REPRESSES", "LEFTCUR", "LEFTP", "LEFTSBR", "LEQUAL", "LINE_COMMENT", "LOG_AND", "LOG_NOT", "LOG_OR", "LTHAN", "MINUS", "ML_COMMENT", "MULT", "NEQUAL", "NEWLINE", "NOTE", "NUM", "NUMBER", "PART", "PART_TYPE", "PERMUTE", "PIGEON", "PIPE", "PLUS", "PRINT", "PRINTLN", "PRODUCT", "PROPERTY", "REAL", "REF", "REGISTRY", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "RULE", "SBOL", "SEMIC", "SIZE", "STRICT", "STRING", "TRUE", "TXT", "TYPE", "UC_AND", "UC_ELSE", "UC_ELSEIF", "UC_FOR", "UC_FORALL", "UC_IF", "UC_IMPORT", "UC_INCLUDE", "UC_INDUCES", "UC_NOT", "UC_ON", "UC_OR", "UC_REPRESSES", "UNDERS", "WS", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", "'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'MATCHES'", "'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", "'SOUNDSLIKE'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'reverse'", "'same_count'", "'same_orientation'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", "'soundslike'", "'startswith'", "'then'", "'with'"
    };

    public static final int EOF=-1;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int T__105=105;
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
    public static final int ADDPROPS=4;
    public static final int AMP=5;
    public static final int ARRAY=6;
    public static final int ARROW=7;
    public static final int ASSERT=8;
    public static final int BOOLEAN=9;
    public static final int COLLECTION=10;
    public static final int COLON=11;
    public static final int COMMA=12;
    public static final int DEVICE=13;
    public static final int DIGIT=14;
    public static final int DIV=15;
    public static final int DOT=16;
    public static final int EQUALS=17;
    public static final int EXIT_LC=18;
    public static final int EXIT_UC=19;
    public static final int EXPORT=20;
    public static final int FALSE=21;
    public static final int FLEXIBLE=22;
    public static final int GENBANK=23;
    public static final int GEQUAL=24;
    public static final int GRAMMAR=25;
    public static final int GTHAN=26;
    public static final int HASHMARK=27;
    public static final int ID=28;
    public static final int IMAGE=29;
    public static final int INT=30;
    public static final int INTERACTION=31;
    public static final int LC_AND=32;
    public static final int LC_ELSE=33;
    public static final int LC_ELSEIF=34;
    public static final int LC_FOR=35;
    public static final int LC_FORALL=36;
    public static final int LC_IF=37;
    public static final int LC_IMPORT=38;
    public static final int LC_INCLUDE=39;
    public static final int LC_INDUCES=40;
    public static final int LC_NOT=41;
    public static final int LC_ON=42;
    public static final int LC_OR=43;
    public static final int LC_PIGEON=44;
    public static final int LC_REPRESSES=45;
    public static final int LEFTCUR=46;
    public static final int LEFTP=47;
    public static final int LEFTSBR=48;
    public static final int LEQUAL=49;
    public static final int LINE_COMMENT=50;
    public static final int LOG_AND=51;
    public static final int LOG_NOT=52;
    public static final int LOG_OR=53;
    public static final int LTHAN=54;
    public static final int MINUS=55;
    public static final int ML_COMMENT=56;
    public static final int MULT=57;
    public static final int NEQUAL=58;
    public static final int NEWLINE=59;
    public static final int NOTE=60;
    public static final int NUM=61;
    public static final int NUMBER=62;
    public static final int PART=63;
    public static final int PART_TYPE=64;
    public static final int PERMUTE=65;
    public static final int PIGEON=66;
    public static final int PIPE=67;
    public static final int PLUS=68;
    public static final int PRINT=69;
    public static final int PRINTLN=70;
    public static final int PRODUCT=71;
    public static final int PROPERTY=72;
    public static final int REAL=73;
    public static final int REF=74;
    public static final int REGISTRY=75;
    public static final int RIGHTCUR=76;
    public static final int RIGHTP=77;
    public static final int RIGHTSBR=78;
    public static final int RULE=79;
    public static final int SBOL=80;
    public static final int SEMIC=81;
    public static final int SIZE=82;
    public static final int STRICT=83;
    public static final int STRING=84;
    public static final int TRUE=85;
    public static final int TXT=86;
    public static final int TYPE=87;
    public static final int UC_AND=88;
    public static final int UC_ELSE=89;
    public static final int UC_ELSEIF=90;
    public static final int UC_FOR=91;
    public static final int UC_FORALL=92;
    public static final int UC_IF=93;
    public static final int UC_IMPORT=94;
    public static final int UC_INCLUDE=95;
    public static final int UC_INDUCES=96;
    public static final int UC_NOT=97;
    public static final int UC_ON=98;
    public static final int UC_OR=99;
    public static final int UC_REPRESSES=100;
    public static final int UNDERS=101;
    public static final int WS=102;

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:704:1: prog : ( statement[false] )+ EOF !;
    public final EugeneParser.prog_return prog() throws RecognitionException {
        EugeneParser.prog_return retval = new EugeneParser.prog_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF2=null;
        EugeneParser.statement_return statement1 =null;


        Object EOF2_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:710:2: ( ( statement[false] )+ EOF !)
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:710:4: ( statement[false] )+ EOF !
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:710:4: ( statement[false] )+
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
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:710:5: statement[false]
            	    {
            	    pushFollow(FOLLOW_statement_in_prog816);
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


            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_prog821); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:714:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:716:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | imperativeStatements[defer] | predefined_statements[defer] SEMIC )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:717:3: includeStatement[defer] ( SEMIC )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_includeStatement_in_statement847);
                    includeStatement3=includeStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, includeStatement3.getTree());

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:717:27: ( SEMIC )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==SEMIC) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:717:28: SEMIC
                            {
                            SEMIC4=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement851); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:718:4: declarationStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declarationStatement_in_statement858);
                    declarationStatement5=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, declarationStatement5.getTree());

                    SEMIC6=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement861); 
                    SEMIC6_tree = 
                    (Object)adaptor.create(SEMIC6)
                    ;
                    adaptor.addChild(root_0, SEMIC6_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:719:4: printStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_printStatement_in_statement867);
                    printStatement7=printStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, printStatement7.getTree());

                    SEMIC8=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement870); 
                    SEMIC8_tree = 
                    (Object)adaptor.create(SEMIC8)
                    ;
                    adaptor.addChild(root_0, SEMIC8_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:720:4: assignment[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_statement875);
                    assignment9=assignment(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, assignment9.getTree());

                    SEMIC10=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement878); 
                    SEMIC10_tree = 
                    (Object)adaptor.create(SEMIC10)
                    ;
                    adaptor.addChild(root_0, SEMIC10_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:721:4: functionCall[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_statement883);
                    functionCall11=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, functionCall11.getTree());

                    SEMIC12=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement886); 
                    SEMIC12_tree = 
                    (Object)adaptor.create(SEMIC12)
                    ;
                    adaptor.addChild(root_0, SEMIC12_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:722:4: de= dataExchange[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_statement893);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());

                    SEMIC13=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement896); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:732:4: imperativeStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_imperativeStatements_in_statement903);
                    imperativeStatements14=imperativeStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, imperativeStatements14.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:733:4: predefined_statements[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_predefined_statements_in_statement909);
                    predefined_statements15=predefined_statements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, predefined_statements15.getTree());

                    SEMIC16=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement912); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:736:1: predefined_statements[boolean defer] : ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) );
    public final EugeneParser.predefined_statements_return predefined_statements(boolean defer) throws RecognitionException {
        EugeneParser.predefined_statements_return retval = new EugeneParser.predefined_statements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set18=null;
        EugeneParser.testStatements_return testStatements17 =null;


        Object set18_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:737:2: ( testStatements[defer] | ( EXIT_LC | EXIT_UC ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:737:4: testStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_testStatements_in_predefined_statements925);
                    testStatements17=testStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, testStatements17.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:738:4: ( EXIT_LC | EXIT_UC )
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:746:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:748:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | grammarDeclaration[defer] | deviceDeclaration[defer] )
            int alt5=9;
            switch ( input.LA(1) ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:748:4: v= variableDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_variableDeclaration_in_declarationStatement959);
                    v=variableDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, v.getTree());


                    if(!defer) {
                        retval.name = (v!=null?v.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:753:4: containerDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_containerDeclaration_in_declarationStatement967);
                    containerDeclaration19=containerDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, containerDeclaration19.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:754:4: propertyDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement973);
                    propertyDeclaration20=propertyDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, propertyDeclaration20.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:755:4: typeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeDeclaration_in_declarationStatement979);
                    typeDeclaration21=typeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, typeDeclaration21.getTree());

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:756:4: instantiation[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_instantiation_in_declarationStatement985);
                    instantiation22=instantiation(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, instantiation22.getTree());

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:757:4: interactionDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement991);
                    interactionDeclaration23=interactionDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, interactionDeclaration23.getTree());

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:758:4: ruleDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement997);
                    ruleDeclaration24=ruleDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ruleDeclaration24.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:759:4: grammarDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_grammarDeclaration_in_declarationStatement1003);
                    grammarDeclaration25=grammarDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, grammarDeclaration25.getTree());

                    }
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:760:4: deviceDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement1009);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:763:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | BOOLEAN b= booldecl[defer] );
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
        Token BOOLEAN35=null;
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
        Object BOOLEAN35_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:765:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | BOOLEAN b= booldecl[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:765:4: NUM n= numdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM27=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1027); 
                    NUM27_tree = 
                    (Object)adaptor.create(NUM27)
                    ;
                    adaptor.addChild(root_0, NUM27_tree);


                    pushFollow(FOLLOW_numdecl_in_variableDeclaration1031);
                    n=numdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, n.getTree());


                    if(!defer) {
                        retval.varname = (n!=null?n.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:770:4: TXT t= txtdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT28=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1042); 
                    TXT28_tree = 
                    (Object)adaptor.create(TXT28)
                    ;
                    adaptor.addChild(root_0, TXT28_tree);


                    pushFollow(FOLLOW_txtdecl_in_variableDeclaration1046);
                    t=txtdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t.getTree());


                    if(!defer) {
                        retval.varname = (t!=null?t.varname:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:775:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT29=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration1057); 
                    TXT29_tree = 
                    (Object)adaptor.create(TXT29)
                    ;
                    adaptor.addChild(root_0, TXT29_tree);


                    LEFTSBR30=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1059); 
                    LEFTSBR30_tree = 
                    (Object)adaptor.create(LEFTSBR30)
                    ;
                    adaptor.addChild(root_0, LEFTSBR30_tree);


                    RIGHTSBR31=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1061); 
                    RIGHTSBR31_tree = 
                    (Object)adaptor.create(RIGHTSBR31)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR31_tree);


                    pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration1065);
                    tl=txtlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tl.getTree());


                    if(!defer) {
                        retval.varname = (tl!=null?tl.varname:null);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:780:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM32=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1076); 
                    NUM32_tree = 
                    (Object)adaptor.create(NUM32)
                    ;
                    adaptor.addChild(root_0, NUM32_tree);


                    LEFTSBR33=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1078); 
                    LEFTSBR33_tree = 
                    (Object)adaptor.create(LEFTSBR33)
                    ;
                    adaptor.addChild(root_0, LEFTSBR33_tree);


                    RIGHTSBR34=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1080); 
                    RIGHTSBR34_tree = 
                    (Object)adaptor.create(RIGHTSBR34)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR34_tree);


                    pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1084);
                    nl=numlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, nl.getTree());


                    if(!defer) {
                        retval.varname = (nl!=null?nl.varname:null);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:785:4: BOOLEAN b= booldecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    BOOLEAN35=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_variableDeclaration1095); 
                    BOOLEAN35_tree = 
                    (Object)adaptor.create(BOOLEAN35)
                    ;
                    adaptor.addChild(root_0, BOOLEAN35_tree);


                    pushFollow(FOLLOW_booldecl_in_variableDeclaration1099);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:792:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:794:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:794:4: ID ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID36=(Token)match(input,ID,FOLLOW_ID_in_numdecl1122); 
                    ID36_tree = 
                    (Object)adaptor.create(ID36)
                    ;
                    adaptor.addChild(root_0, ID36_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID36!=null?ID36.getText():null), EugeneConstants.NUM);
                    			retval.varname = (ID36!=null?ID36.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:800:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:800:6: COMMA numdecl[defer]
                            {
                            COMMA37=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1129); 
                            COMMA37_tree = 
                            (Object)adaptor.create(COMMA37)
                            ;
                            adaptor.addChild(root_0, COMMA37_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1131);
                            numdecl38=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl38.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:801:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID39=(Token)match(input,ID,FOLLOW_ID_in_numdecl1139); 
                    ID39_tree = 
                    (Object)adaptor.create(ID39)
                    ;
                    adaptor.addChild(root_0, ID39_tree);


                    EQUALS40=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1141); 
                    EQUALS40_tree = 
                    (Object)adaptor.create(EQUALS40)
                    ;
                    adaptor.addChild(root_0, EQUALS40_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:801:14: (ex= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:801:15: ex= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_numdecl1146);
                    ex=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ex.getTree());

                    }



                    		if(!defer) {
                    			declareVariableWithValueNum((ID39!=null?ID39.getText():null), (ex!=null?ex.p:null), (ex!=null?ex.index:0));
                    			retval.varname = (ID39!=null?ID39.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:6: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:807:7: COMMA numdecl[defer]
                            {
                            COMMA41=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1156); 
                            COMMA41_tree = 
                            (Object)adaptor.create(COMMA41)
                            ;
                            adaptor.addChild(root_0, COMMA41_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1158);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:810:1: txtdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:812:2: ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:812:4: ID ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID43=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1178); 
                    ID43_tree = 
                    (Object)adaptor.create(ID43)
                    ;
                    adaptor.addChild(root_0, ID43_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID43!=null?ID43.getText():null), EugeneConstants.TXT);
                    			retval.varname = (ID43!=null?ID43.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:818:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:818:6: COMMA txtdecl[defer]
                            {
                            COMMA44=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1185); 
                            COMMA44_tree = 
                            (Object)adaptor.create(COMMA44)
                            ;
                            adaptor.addChild(root_0, COMMA44_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1187);
                            txtdecl45=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl45.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:820:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1198); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS46=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1200); 
                    EQUALS46_tree = 
                    (Object)adaptor.create(EQUALS46)
                    ;
                    adaptor.addChild(root_0, EQUALS46_tree);


                    pushFollow(FOLLOW_expr_in_txtdecl1204);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxt((var!=null?var.getText():null), (let!=null?let.p:null), (let!=null?let.index:0));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:826:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:826:6: COMMA txtdecl[defer]
                            {
                            COMMA47=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1212); 
                            COMMA47_tree = 
                            (Object)adaptor.create(COMMA47)
                            ;
                            adaptor.addChild(root_0, COMMA47_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1214);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:829:1: txtlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:831:2: ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:831:4: ID ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID49=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1234); 
                    ID49_tree = 
                    (Object)adaptor.create(ID49)
                    ;
                    adaptor.addChild(root_0, ID49_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID49!=null?ID49.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID49!=null?ID49.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:837:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:837:6: COMMA txtlistdecl[defer]
                            {
                            COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1241); 
                            COMMA50_tree = 
                            (Object)adaptor.create(COMMA50)
                            ;
                            adaptor.addChild(root_0, COMMA50_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1243);
                            txtlistdecl51=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl51.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:838:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1253); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS52=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1255); 
                    EQUALS52_tree = 
                    (Object)adaptor.create(EQUALS52)
                    ;
                    adaptor.addChild(root_0, EQUALS52_tree);


                    typeList = EugeneConstants.TXT;

                    pushFollow(FOLLOW_expr_in_txtlistdecl1261);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxtList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:844:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:844:6: COMMA txtlistdecl[defer]
                            {
                            COMMA53=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1269); 
                            COMMA53_tree = 
                            (Object)adaptor.create(COMMA53)
                            ;
                            adaptor.addChild(root_0, COMMA53_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1271);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:847:1: numlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:849:2: ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:849:4: ID ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID55=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1291); 
                    ID55_tree = 
                    (Object)adaptor.create(ID55)
                    ;
                    adaptor.addChild(root_0, ID55_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID55!=null?ID55.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID55!=null?ID55.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:855:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:855:6: COMMA numlistdecl[defer]
                            {
                            COMMA56=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1298); 
                            COMMA56_tree = 
                            (Object)adaptor.create(COMMA56)
                            ;
                            adaptor.addChild(root_0, COMMA56_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1300);
                            numlistdecl57=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl57.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:856:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1310); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS58=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1312); 
                    EQUALS58_tree = 
                    (Object)adaptor.create(EQUALS58)
                    ;
                    adaptor.addChild(root_0, EQUALS58_tree);


                     typeList = EugeneConstants.NUM;

                    pushFollow(FOLLOW_expr_in_numlistdecl1317);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueNumList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:862:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:862:6: COMMA numlistdecl[defer]
                            {
                            COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1325); 
                            COMMA59_tree = 
                            (Object)adaptor.create(COMMA59)
                            ;
                            adaptor.addChild(root_0, COMMA59_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1327);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:865:1: booldecl[boolean defer] returns [String varname] : ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:867:2: ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:867:4: ID ( COMMA booldecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID61=(Token)match(input,ID,FOLLOW_ID_in_booldecl1347); 
                    ID61_tree = 
                    (Object)adaptor.create(ID61)
                    ;
                    adaptor.addChild(root_0, ID61_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID61!=null?ID61.getText():null), EugeneConstants.BOOLEAN);
                    			retval.varname = (ID61!=null?ID61.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:873:5: ( COMMA booldecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:873:6: COMMA booldecl[defer]
                            {
                            COMMA62=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1354); 
                            COMMA62_tree = 
                            (Object)adaptor.create(COMMA62)
                            ;
                            adaptor.addChild(root_0, COMMA62_tree);


                            pushFollow(FOLLOW_booldecl_in_booldecl1356);
                            booldecl63=booldecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, booldecl63.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:874:4: var= ID EQUALS let= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1366); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS64=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1368); 
                    EQUALS64_tree = 
                    (Object)adaptor.create(EQUALS64)
                    ;
                    adaptor.addChild(root_0, EQUALS64_tree);


                    pushFollow(FOLLOW_expr_in_booldecl1372);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:883:1: propertyDeclaration[boolean defer] : PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:884:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:884:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            PROPERTY65=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1390); 
            PROPERTY65_tree = 
            (Object)adaptor.create(PROPERTY65)
            ;
            adaptor.addChild(root_0, PROPERTY65_tree);


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1394); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            LEFTP66=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1396); 
            LEFTP66_tree = 
            (Object)adaptor.create(LEFTP66)
            ;
            adaptor.addChild(root_0, LEFTP66_tree);


            pushFollow(FOLLOW_propertyType_in_propertyDeclaration1400);
            typeToken=propertyType();

            state._fsp--;

            adaptor.addChild(root_0, typeToken.getTree());

            RIGHTP67=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1402); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:897:1: propertyType returns [String type] : ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | BOOLEAN );
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
        Token BOOLEAN76=null;

        Object TXT68_tree=null;
        Object TXT69_tree=null;
        Object LEFTSBR70_tree=null;
        Object RIGHTSBR71_tree=null;
        Object NUM72_tree=null;
        Object NUM73_tree=null;
        Object LEFTSBR74_tree=null;
        Object RIGHTSBR75_tree=null;
        Object BOOLEAN76_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:899:2: ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | BOOLEAN )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:899:4: TXT
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT68=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1421); 
                    TXT68_tree = 
                    (Object)adaptor.create(TXT68)
                    ;
                    adaptor.addChild(root_0, TXT68_tree);



                    retval.type = EugeneConstants.TXT;	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:902:4: TXT LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT69=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1428); 
                    TXT69_tree = 
                    (Object)adaptor.create(TXT69)
                    ;
                    adaptor.addChild(root_0, TXT69_tree);


                    LEFTSBR70=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1430); 
                    LEFTSBR70_tree = 
                    (Object)adaptor.create(LEFTSBR70)
                    ;
                    adaptor.addChild(root_0, LEFTSBR70_tree);


                    RIGHTSBR71=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1432); 
                    RIGHTSBR71_tree = 
                    (Object)adaptor.create(RIGHTSBR71)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR71_tree);



                    retval.type = EugeneConstants.TXTLIST;	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:905:4: NUM
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM72=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1439); 
                    NUM72_tree = 
                    (Object)adaptor.create(NUM72)
                    ;
                    adaptor.addChild(root_0, NUM72_tree);



                    retval.type = EugeneConstants.NUM;	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:908:4: NUM LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM73=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1447); 
                    NUM73_tree = 
                    (Object)adaptor.create(NUM73)
                    ;
                    adaptor.addChild(root_0, NUM73_tree);


                    LEFTSBR74=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1449); 
                    LEFTSBR74_tree = 
                    (Object)adaptor.create(LEFTSBR74)
                    ;
                    adaptor.addChild(root_0, LEFTSBR74_tree);


                    RIGHTSBR75=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1451); 
                    RIGHTSBR75_tree = 
                    (Object)adaptor.create(RIGHTSBR75)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR75_tree);



                    retval.type = EugeneConstants.NUMLIST;	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:911:4: BOOLEAN
                    {
                    root_0 = (Object)adaptor.nil();


                    BOOLEAN76=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_propertyType1458); 
                    BOOLEAN76_tree = 
                    (Object)adaptor.create(BOOLEAN76)
                    ;
                    adaptor.addChild(root_0, BOOLEAN76_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:920:1: typeDeclaration[boolean defer] : ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:921:2: ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:921:4: partTypeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1476);
                    partTypeDeclaration77=partTypeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, partTypeDeclaration77.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:4: ( TYPE )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:5: TYPE
                    {
                    TYPE78=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1483); 
                    TYPE78_tree = 
                    (Object)adaptor.create(TYPE78)
                    ;
                    adaptor.addChild(root_0, TYPE78_tree);


                    }


                    nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1488); 
                    nameToken_tree = 
                    (Object)adaptor.create(nameToken)
                    ;
                    adaptor.addChild(root_0, nameToken_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:24: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==LEFTP) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:25: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                            {
                            LEFTP79=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1491); 
                            LEFTP79_tree = 
                            (Object)adaptor.create(LEFTP79)
                            ;
                            adaptor.addChild(root_0, LEFTP79_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:31: (lstToken= listOfIDs[defer] )?
                            int alt22=2;
                            int LA22_0 = input.LA(1);

                            if ( (LA22_0==ID) ) {
                                alt22=1;
                            }
                            switch (alt22) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:922:32: lstToken= listOfIDs[defer]
                                    {
                                    pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1496);
                                    lstToken=listOfIDs(defer);

                                    state._fsp--;

                                    adaptor.addChild(root_0, lstToken.getTree());

                                    }
                                    break;

                            }


                            RIGHTP80=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1501); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:935:1: partTypeDeclaration[boolean defer] : ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
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


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1529); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:35: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==LEFTP) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:36: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                    {
                    LEFTP82=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1532); 
                    LEFTP82_tree = 
                    (Object)adaptor.create(LEFTP82)
                    ;
                    adaptor.addChild(root_0, LEFTP82_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:42: (lstToken= listOfIDs[defer] )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==ID) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:936:43: lstToken= listOfIDs[defer]
                            {
                            pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1537);
                            lstToken=listOfIDs(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lstToken.getTree());

                            }
                            break;

                    }


                    RIGHTP83=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1542); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:952:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:2: ( (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:4: (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:4: (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:5: c= COLLECTION
                    {
                    c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1569); 
                    c_tree = 
                    (Object)adaptor.create(c)
                    ;
                    adaptor.addChild(root_0, c_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:20: (a= ARRAY LEFTSBR RIGHTSBR )
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:20: (a= ARRAY LEFTSBR RIGHTSBR )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:954:21: a= ARRAY LEFTSBR RIGHTSBR
                    {
                    a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1576); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    LEFTSBR84=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1578); 
                    LEFTSBR84_tree = 
                    (Object)adaptor.create(LEFTSBR84)
                    ;
                    adaptor.addChild(root_0, LEFTSBR84_tree);


                    RIGHTSBR85=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1580); 
                    RIGHTSBR85_tree = 
                    (Object)adaptor.create(RIGHTSBR85)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR85_tree);


                    }


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1586); 
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:968:4: ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LEFTP) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:968:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
                    {
                    LEFTP86=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1591); 
                    LEFTP86_tree = 
                    (Object)adaptor.create(LEFTP86)
                    ;
                    adaptor.addChild(root_0, LEFTP86_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:968:11: ( list_of_declarations[defer] )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==ARRAY||(LA28_0 >= BOOLEAN && LA28_0 <= COLLECTION)||LA28_0==DEVICE||LA28_0==FALSE||LA28_0==GRAMMAR||LA28_0==ID||LA28_0==INTERACTION||(LA28_0 >= LEFTP && LA28_0 <= LEFTSBR)||LA28_0==MINUS||(LA28_0 >= NUM && LA28_0 <= PART_TYPE)||(LA28_0 >= PROPERTY && LA28_0 <= REAL)||LA28_0==RULE||(LA28_0 >= STRING && LA28_0 <= TYPE)) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:968:12: list_of_declarations[defer]
                            {
                            pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1594);
                            list_of_declarations87=list_of_declarations(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, list_of_declarations87.getTree());

                            }
                            break;

                    }


                    RIGHTP88=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1599); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:983:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:985:2: ( (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:985:4: (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:985:4: (ds= declarationStatement[defer] |at= atom[defer] )
            int alt30=2;
            switch ( input.LA(1) ) {
            case ARRAY:
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
            case FALSE:
            case LEFTP:
            case LEFTSBR:
            case MINUS:
            case NUMBER:
            case REAL:
            case STRING:
            case TRUE:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:985:6: ds= declarationStatement[defer]
                    {
                    pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1632);
                    ds=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ds.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:985:39: at= atom[defer]
                    {
                    pushFollow(FOLLOW_atom_in_list_of_declarations1639);
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:997:5: ( COMMA lod= list_of_declarations[defer] )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==COMMA) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:997:7: COMMA lod= list_of_declarations[defer]
                    {
                    COMMA89=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1647); 
                    COMMA89_tree = 
                    (Object)adaptor.create(COMMA89)
                    ;
                    adaptor.addChild(root_0, COMMA89_tree);


                    pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations1651);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1003:1: instantiation[boolean defer] : t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1007:2: (t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1007:4: t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            t=(Token)match(input,ID,FOLLOW_ID_in_instantiation1679); 
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
            	

            nameToken=(Token)match(input,ID,FOLLOW_ID_in_instantiation1685); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1023:17: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LEFTP) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1023:19: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
                    {
                    LEFTP90=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation1689); 
                    LEFTP90_tree = 
                    (Object)adaptor.create(LEFTP90)
                    ;
                    adaptor.addChild(root_0, LEFTP90_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1023:25: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
                    int alt32=3;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==DOT) ) {
                        alt32=1;
                    }
                    else if ( (LA32_0==FALSE||LA32_0==ID||(LA32_0 >= LEFTP && LA32_0 <= LEFTSBR)||LA32_0==MINUS||LA32_0==NUMBER||LA32_0==REAL||(LA32_0 >= STRING && LA32_0 <= TRUE)) ) {
                        alt32=2;
                    }
                    switch (alt32) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1023:26: dotToken= listOfDotValues[defer]
                            {
                            pushFollow(FOLLOW_listOfDotValues_in_instantiation1694);
                            dotToken=listOfDotValues(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dotToken.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1023:58: valueToken= listOfValues[defer, (ComponentType)type]
                            {
                            pushFollow(FOLLOW_listOfValues_in_instantiation1699);
                            valueToken=listOfValues(defer, (ComponentType)type);

                            state._fsp--;

                            adaptor.addChild(root_0, valueToken.getTree());

                            }
                            break;

                    }


                    RIGHTP91=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation1704); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1058:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1059:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1059:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            {
            root_0 = (Object)adaptor.nil();


            DOT92=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1728); 
            DOT92_tree = 
            (Object)adaptor.create(DOT92)
            ;
            adaptor.addChild(root_0, DOT92_tree);


            prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1732); 
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
            		

            LEFTP93=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1738); 
            LEFTP93_tree = 
            (Object)adaptor.create(LEFTP93)
            ;
            adaptor.addChild(root_0, LEFTP93_tree);


            pushFollow(FOLLOW_expr_in_listOfDotValues1742);
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
            			

            RIGHTP94=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1750); 
            RIGHTP94_tree = 
            (Object)adaptor.create(RIGHTP94)
            ;
            adaptor.addChild(root_0, RIGHTP94_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1077:13: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMA) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1077:14: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
            	    {
            	    COMMA95=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues1753); 
            	    COMMA95_tree = 
            	    (Object)adaptor.create(COMMA95)
            	    ;
            	    adaptor.addChild(root_0, COMMA95_tree);


            	    DOT96=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1755); 
            	    DOT96_tree = 
            	    (Object)adaptor.create(DOT96)
            	    ;
            	    adaptor.addChild(root_0, DOT96_tree);


            	    p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1759); 
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
            	    				

            	    LEFTP97=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1767); 
            	    LEFTP97_tree = 
            	    (Object)adaptor.create(LEFTP97)
            	    ;
            	    adaptor.addChild(root_0, LEFTP97_tree);


            	    pushFollow(FOLLOW_expr_in_listOfDotValues1771);
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
            	    					

            	    RIGHTP98=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1781); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1098:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
    public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
        EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA99=null;
        EugeneParser.expr_return val1 =null;

        EugeneParser.expr_return val2 =null;


        Object COMMA99_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1099:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1100:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
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
            		

            pushFollow(FOLLOW_expr_in_listOfValues1802);
            val1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, val1.getTree());


            if(!defer) {
                propertyValuesHolder.add((val1!=null?val1.p:null));
            }				
            			

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1124:6: ( COMMA val2= expr[defer] )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1124:7: COMMA val2= expr[defer]
            	    {
            	    COMMA99=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues1808); 
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
            	                   

            	    pushFollow(FOLLOW_expr_in_listOfValues1814);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1154:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1155:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1155:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            DEVICE100=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration1833); 
            DEVICE100_tree = 
            (Object)adaptor.create(DEVICE100)
            ;
            adaptor.addChild(root_0, DEVICE100_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration1837); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1155:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==LEFTP) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1155:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
                    {
                    LEFTP101=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration1840); 
                    LEFTP101_tree = 
                    (Object)adaptor.create(LEFTP101)
                    ;
                    adaptor.addChild(root_0, LEFTP101_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1155:23: (dcs= deviceComponents[defer] )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==ID||LA36_0==LEFTSBR||LA36_0==MINUS||LA36_0==PLUS) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1155:24: dcs= deviceComponents[defer]
                            {
                            pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration1845);
                            dcs=deviceComponents(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dcs.getTree());

                            }
                            break;

                    }


                    RIGHTP102=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration1850); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1169:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1175:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1175:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selection_in_deviceComponents1881);
            s=selection(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());


            if(!defer) {
                retval.lstComponents.add((s!=null?s.components:null));
                retval.lstOrientations.add((s!=null?s.orientations:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1180:4: ( ',' dcs= deviceComponents[defer] )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==COMMA) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1180:5: ',' dcs= deviceComponents[defer]
                    {
                    char_literal103=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents1887); 
                    char_literal103_tree = 
                    (Object)adaptor.create(char_literal103)
                    ;
                    adaptor.addChild(root_0, char_literal103_tree);


                    pushFollow(FOLLOW_deviceComponents_in_deviceComponents1891);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1188:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1190:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1190:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR104=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection1915); 
                    LEFTSBR104_tree = 
                    (Object)adaptor.create(LEFTSBR104)
                    ;
                    adaptor.addChild(root_0, LEFTSBR104_tree);


                    pushFollow(FOLLOW_selection_list_in_selection1919);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());

                    RIGHTSBR105=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection1922); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1196:4: dc= device_component[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_device_component_in_selection1931);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1207:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1213:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1213:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_device_component_in_selection_list1960);
            dc=device_component(defer);

            state._fsp--;

            adaptor.addChild(root_0, dc.getTree());


            if(!defer) {
                retval.components.add((dc!=null?dc.component:null));
                retval.orientations.add((dc!=null?dc.orientation:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1218:4: ( PIPE sl= selection_list[defer] )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==PIPE) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1218:5: PIPE sl= selection_list[defer]
                    {
                    PIPE106=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list1966); 
                    PIPE106_tree = 
                    (Object)adaptor.create(PIPE106)
                    ;
                    adaptor.addChild(root_0, PIPE106_tree);


                    pushFollow(FOLLOW_selection_list_in_selection_list1970);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1226:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
    public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
        EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token directionToken=null;
        Token idToken=null;

        Object directionToken_tree=null;
        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1228:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1228:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1228:4: (directionToken= ( MINUS | PLUS ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==MINUS||LA41_0==PLUS) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1228:5: directionToken= ( MINUS | PLUS )
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


            idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component2006); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1276:1: assignment[boolean defer] : lhs= lhs_assignment[defer, null, null, null] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1277:2: (lhs= lhs_assignment[defer, null, null, null] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1277:4: lhs= lhs_assignment[defer, null, null, null] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_lhs_assignment_in_assignment2026);
            lhs=lhs_assignment(defer, null, null, null);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            EQUALS107=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment2029); 
            EQUALS107_tree = 
            (Object)adaptor.create(EQUALS107)
            ;
            adaptor.addChild(root_0, EQUALS107_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1277:55: (a= AMP )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==AMP) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1277:56: a= AMP
                    {
                    a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment2034); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    }
                    break;

            }


            pushFollow(FOLLOW_rhs_assignment_in_assignment2040);
            rhs=rhs_assignment(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer) {
                try {
                    this.interp.assignTo((lhs!=null?lhs.name:null), (lhs!=null?lhs.child:null), (lhs!=null?lhs.id_out:null), (lhs!=null?lhs.idx_out:null), (rhs!=null?rhs.e:null), a!=null);
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
        public String name;
        public NamedElement child;
        public String id_out = null;
        public Variable idx_out = null;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lhs_assignment"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1288:1: lhs_assignment[boolean defer, NamedElement parent, String id_in, Variable idx_in] returns [String name, NamedElement child, String id_out = null, Variable idx_out = null] : idToken= ID ac= lhs_access[defer, parent, $id_out, $idx_out] ;
    public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer, NamedElement parent, String id_in, Variable idx_in) throws RecognitionException {
        EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        EugeneParser.lhs_access_return ac =null;


        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1290:2: (idToken= ID ac= lhs_access[defer, parent, $id_out, $idx_out] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1290:4: idToken= ID ac= lhs_access[defer, parent, $id_out, $idx_out]
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment2062); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);



            if(!defer) {
                try {
                    retval.name = (idToken!=null?idToken.getText():null);
                    retval.id_out = (idToken!=null?idToken.getText():null);
                    retval.idx_out = idx_in;
                    
                    if(null == parent) {
                        parent = this.interp.get((idToken!=null?idToken.getText():null));
                        retval.child = null;
                    } else {
                        retval.child = parent.getElement((idToken!=null?idToken.getText():null));
                        if(null == retval.child) {
                            throw new EugeneException("The " + parent.getName() + " element does not contain "+
                                "an element named " + (idToken!=null?idToken.getText():null) + "!");
                        }
                    }
                    
                } catch(EugeneException ee) {
                    printError(ee.getMessage());
                }
            }	
            	

            pushFollow(FOLLOW_lhs_access_in_lhs_assignment2068);
            ac=lhs_access(defer, parent, retval.id_out, retval.idx_out);

            state._fsp--;

            adaptor.addChild(root_0, ac.getTree());


            if(!defer) {
                retval.child = (ac!=null?ac.child:null);
                retval.id_out = (ac!=null?ac.id_out:null);
                retval.idx_out = (ac!=null?ac.idx_out:null);
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
    // $ANTLR end "lhs_assignment"


    public static class lhs_access_return extends ParserRuleReturnScope {
        public NamedElement child;
        public String id_out;
        public Variable idx_out;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lhs_access"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1321:1: lhs_access[boolean defer, NamedElement parent, String id_in, Variable idx_in] returns [NamedElement child, String id_out, Variable idx_out] : (| DOT lhs= lhs_assignment[defer, parent, id_in, idx_in] | LEFTSBR (exp= expr[defer] ) RIGHTSBR ac= lhs_access[defer, $child, $id_out, $idx_out] );
    public final EugeneParser.lhs_access_return lhs_access(boolean defer, NamedElement parent, String id_in, Variable idx_in) throws RecognitionException {
        EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token DOT108=null;
        Token LEFTSBR109=null;
        Token RIGHTSBR110=null;
        EugeneParser.lhs_assignment_return lhs =null;

        EugeneParser.expr_return exp =null;

        EugeneParser.lhs_access_return ac =null;


        Object DOT108_tree=null;
        Object LEFTSBR109_tree=null;
        Object RIGHTSBR110_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1330:2: (| DOT lhs= lhs_assignment[defer, parent, id_in, idx_in] | LEFTSBR (exp= expr[defer] ) RIGHTSBR ac= lhs_access[defer, $child, $id_out, $idx_out] )
            int alt43=3;
            switch ( input.LA(1) ) {
            case EQUALS:
                {
                alt43=1;
                }
                break;
            case DOT:
                {
                alt43=2;
                }
                break;
            case LEFTSBR:
                {
                alt43=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;

            }

            switch (alt43) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1330:4: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.child = parent;
                        retval.id_out = id_in;
                        retval.idx_out = idx_in;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1337:4: DOT lhs= lhs_assignment[defer, parent, id_in, idx_in]
                    {
                    root_0 = (Object)adaptor.nil();


                    DOT108=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2100); 
                    DOT108_tree = 
                    (Object)adaptor.create(DOT108)
                    ;
                    adaptor.addChild(root_0, DOT108_tree);


                    pushFollow(FOLLOW_lhs_assignment_in_lhs_access2104);
                    lhs=lhs_assignment(defer, parent, id_in, idx_in);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs.getTree());


                    if(!defer) {
                        retval.child = (lhs!=null?lhs.child:null);
                        retval.id_out = (lhs!=null?lhs.id_out:null);
                        retval.idx_out = (lhs!=null?lhs.idx_out:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1344:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR ac= lhs_access[defer, $child, $id_out, $idx_out]
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR109=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2112); 
                    LEFTSBR109_tree = 
                    (Object)adaptor.create(LEFTSBR109)
                    ;
                    adaptor.addChild(root_0, LEFTSBR109_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1344:12: (exp= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1344:13: exp= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_lhs_access2117);
                    exp=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());

                    }


                    RIGHTSBR110=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2121); 
                    RIGHTSBR110_tree = 
                    (Object)adaptor.create(RIGHTSBR110)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR110_tree);



                    if(!defer) {
                        try {
                            if(idx_in != null) {
                                retval.child = parent.getElement((int)idx_in.getNum());
                                if(null == retval.child) {
                                    throw new EugeneException("The " + parent.getName() + " element does not contain "+
                                        "an element at " + idx_in.getNum() + "!");
                                }
                            } else {
                                retval.child = parent;
                            }
                            
                            retval.id_out = null;
                        } catch(EugeneException ee) {
                            printError(ee.toString());
                        }

                        retval.idx_out = (exp!=null?exp.p:null);
                    }	
                    	

                    pushFollow(FOLLOW_lhs_access_in_lhs_access2127);
                    ac=lhs_access(defer, retval.child, retval.id_out, retval.idx_out);

                    state._fsp--;

                    adaptor.addChild(root_0, ac.getTree());


                    if(!defer) {
                        retval.child = (ac!=null?ac.child:null);
                        retval.id_out = (ac!=null?ac.id_out:null);
                        retval.idx_out = (ac!=null?ac.idx_out:null);
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
    // $ANTLR end "lhs_access"


    public static class rhs_assignment_return extends ParserRuleReturnScope {
        public NamedElement e;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rhs_assignment"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1373:1: rhs_assignment[boolean defer] returns [NamedElement e] : (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] );
    public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.functionCall_return fc =null;

        EugeneParser.dataExchange_return de =null;

        EugeneParser.expr_return exp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1375:2: (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] )
            int alt44=3;
            switch ( input.LA(1) ) {
            case PERMUTE:
            case PRODUCT:
                {
                alt44=1;
                }
                break;
            case LC_IMPORT:
            case LC_PIGEON:
            case PIGEON:
            case SBOL:
            case UC_IMPORT:
                {
                alt44=2;
                }
                break;
            case FALSE:
            case ID:
            case LEFTP:
            case LEFTSBR:
            case MINUS:
            case NUMBER:
            case REAL:
            case STRING:
            case TRUE:
                {
                alt44=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }

            switch (alt44) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1375:4: fc= functionCall[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_rhs_assignment2152);
                    fc=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, fc.getTree());


                    if(!defer) {
                        retval.e = (fc!=null?fc.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1380:4: de= dataExchange[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_rhs_assignment2162);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());


                    if(!defer) {
                        retval.e = (de!=null?de.e:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1385:4: exp= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_rhs_assignment2172);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1396:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
    public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
        EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token char_literal111=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object idToken_tree=null;
        Object char_literal111_tree=null;


        retval.lstElements =new ArrayList<NamedElement>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1401:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1401:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2200); 
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1413:4: ( ',' lstToken= listOfIDs[defer] )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==COMMA) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1413:5: ',' lstToken= listOfIDs[defer]
                    {
                    char_literal111=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2209); 
                    char_literal111_tree = 
                    (Object)adaptor.create(char_literal111)
                    ;
                    adaptor.addChild(root_0, char_literal111_tree);


                    pushFollow(FOLLOW_listOfIDs_in_listOfIDs2213);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1420:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
    public final EugeneParser.ruleDeclaration_return ruleDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.ruleDeclaration_return retval = new EugeneParser.ruleDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token device=null;
        Token RULE112=null;
        Token LEFTP113=null;
        Token set114=null;
        Token COLON115=null;
        Token RIGHTP116=null;
        EugeneParser.cnf_rule_return cnf =null;


        Object name_tree=null;
        Object device_tree=null;
        Object RULE112_tree=null;
        Object LEFTP113_tree=null;
        Object set114_tree=null;
        Object COLON115_tree=null;
        Object RIGHTP116_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1422:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1422:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            RULE112=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2237); 
            RULE112_tree = 
            (Object)adaptor.create(RULE112)
            ;
            adaptor.addChild(root_0, RULE112_tree);


            name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2241); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
            adaptor.addChild(root_0, name_tree);


            LEFTP113=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2243); 
            LEFTP113_tree = 
            (Object)adaptor.create(LEFTP113)
            ;
            adaptor.addChild(root_0, LEFTP113_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1422:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1422:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1422:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LC_ON||LA46_0==UC_ON) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1422:26: ( LC_ON | UC_ON ) device= ID COLON
                    {
                    set114=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
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


                    device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2256); 
                    device_tree = 
                    (Object)adaptor.create(device)
                    ;
                    adaptor.addChild(root_0, device_tree);


                    COLON115=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2258); 
                    COLON115_tree = 
                    (Object)adaptor.create(COLON115)
                    ;
                    adaptor.addChild(root_0, COLON115_tree);


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
            	

            pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2266);
            cnf=cnf_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, cnf.getTree());

            }


            RIGHTP116=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2271); 
            RIGHTP116_tree = 
            (Object)adaptor.create(RIGHTP116)
            ;
            adaptor.addChild(root_0, RIGHTP116_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1446:1: ruleOperator[boolean defer] : ruleOperators ;
    public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
        EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ruleOperators_return ruleOperators117 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1447:2: ( ruleOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1447:4: ruleOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_ruleOperators_in_ruleOperator2285);
            ruleOperators117=ruleOperators();

            state._fsp--;

            adaptor.addChild(root_0, ruleOperators117.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1451:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
    public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
        EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set118=null;

        Object set118_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1452:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set118=(Token)input.LT(1);

            if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 103 && input.LA(1) <= 137)||(input.LA(1) >= 139 && input.LA(1) <= 176)||(input.LA(1) >= 178 && input.LA(1) <= 180) ) {
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


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1495:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
    public final EugeneParser.relationalOperators_return relationalOperators() throws RecognitionException {
        EugeneParser.relationalOperators_return retval = new EugeneParser.relationalOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EQUALS119=null;
        Token EQUALS120=null;
        Token NEQUAL121=null;
        Token LTHAN122=null;
        Token GTHAN123=null;
        Token LEQUAL124=null;
        Token GEQUAL125=null;
        Token set126=null;
        Token set127=null;
        Token set128=null;
        Token set129=null;
        Token set130=null;
        Token set131=null;
        Token set132=null;
        Token set133=null;
        Token set134=null;

        Object EQUALS119_tree=null;
        Object EQUALS120_tree=null;
        Object NEQUAL121_tree=null;
        Object LTHAN122_tree=null;
        Object GTHAN123_tree=null;
        Object LEQUAL124_tree=null;
        Object GEQUAL125_tree=null;
        Object set126_tree=null;
        Object set127_tree=null;
        Object set128_tree=null;
        Object set129_tree=null;
        Object set130_tree=null;
        Object set131_tree=null;
        Object set132_tree=null;
        Object set133_tree=null;
        Object set134_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1496:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
            int alt47=15;
            switch ( input.LA(1) ) {
            case EQUALS:
                {
                alt47=1;
                }
                break;
            case NEQUAL:
                {
                alt47=2;
                }
                break;
            case LTHAN:
                {
                alt47=3;
                }
                break;
            case GTHAN:
                {
                alt47=4;
                }
                break;
            case LEQUAL:
                {
                alt47=5;
                }
                break;
            case GEQUAL:
                {
                alt47=6;
                }
                break;
            case 113:
            case 152:
                {
                alt47=7;
                }
                break;
            case 122:
            case 161:
                {
                alt47=8;
                }
                break;
            case 119:
            case 158:
                {
                alt47=9;
                }
                break;
            case 125:
            case 164:
                {
                alt47=10;
                }
                break;
            case 139:
            case 178:
                {
                alt47=11;
                }
                break;
            case 115:
            case 154:
                {
                alt47=12;
                }
                break;
            case 116:
            case 155:
                {
                alt47=13;
                }
                break;
            case 123:
            case 162:
                {
                alt47=14;
                }
                break;
            case 138:
            case 177:
                {
                alt47=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;

            }

            switch (alt47) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1496:4: EQUALS EQUALS
                    {
                    root_0 = (Object)adaptor.nil();


                    EQUALS119=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2664); 
                    EQUALS119_tree = 
                    (Object)adaptor.create(EQUALS119)
                    ;
                    adaptor.addChild(root_0, EQUALS119_tree);


                    EQUALS120=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2666); 
                    EQUALS120_tree = 
                    (Object)adaptor.create(EQUALS120)
                    ;
                    adaptor.addChild(root_0, EQUALS120_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1497:4: NEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    NEQUAL121=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators2671); 
                    NEQUAL121_tree = 
                    (Object)adaptor.create(NEQUAL121)
                    ;
                    adaptor.addChild(root_0, NEQUAL121_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1498:4: LTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    LTHAN122=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators2676); 
                    LTHAN122_tree = 
                    (Object)adaptor.create(LTHAN122)
                    ;
                    adaptor.addChild(root_0, LTHAN122_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1499:4: GTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    GTHAN123=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators2681); 
                    GTHAN123_tree = 
                    (Object)adaptor.create(GTHAN123)
                    ;
                    adaptor.addChild(root_0, GTHAN123_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1500:4: LEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    LEQUAL124=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators2686); 
                    LEQUAL124_tree = 
                    (Object)adaptor.create(LEQUAL124)
                    ;
                    adaptor.addChild(root_0, LEQUAL124_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1501:4: GEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    GEQUAL125=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators2691); 
                    GEQUAL125_tree = 
                    (Object)adaptor.create(GEQUAL125)
                    ;
                    adaptor.addChild(root_0, GEQUAL125_tree);


                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1502:4: ( 'CONTAINS' | 'contains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set126=(Token)input.LT(1);

                    if ( input.LA(1)==113||input.LA(1)==152 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set126)
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1503:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set127=(Token)input.LT(1);

                    if ( input.LA(1)==122||input.LA(1)==161 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set127)
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1504:4: ( 'MATCHES' | 'matches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set128=(Token)input.LT(1);

                    if ( input.LA(1)==119||input.LA(1)==158 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set128)
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1505:4: ( 'NOTMATCHES' | 'notmatches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set129=(Token)input.LT(1);

                    if ( input.LA(1)==125||input.LA(1)==164 ) {
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
                case 11 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1506:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set130=(Token)input.LT(1);

                    if ( input.LA(1)==139||input.LA(1)==178 ) {
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
                case 12 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1507:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set131=(Token)input.LT(1);

                    if ( input.LA(1)==115||input.LA(1)==154 ) {
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
                case 13 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1508:4: ( 'EQUALS' | 'equals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set132=(Token)input.LT(1);

                    if ( input.LA(1)==116||input.LA(1)==155 ) {
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
                case 14 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1509:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set133=(Token)input.LT(1);

                    if ( input.LA(1)==123||input.LA(1)==162 ) {
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
                case 15 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1510:4: ( 'SOUNDSLIKE' | 'soundslike' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set134=(Token)input.LT(1);

                    if ( input.LA(1)==138||input.LA(1)==177 ) {
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

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1513:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
    public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
        EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set135=null;
        EugeneParser.or_predicate_return c =null;

        EugeneParser.cnf_rule_return cnf =null;


        Object set135_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1515:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1515:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1515:4: (c= or_predicate[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1515:5: c= or_predicate[defer]
            {
            pushFollow(FOLLOW_or_predicate_in_cnf_rule2792);
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1523:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LC_AND||LA48_0==LOG_AND||LA48_0==UC_AND) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1523:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
                    {
                    set135=(Token)input.LT(1);

                    if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
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


                    pushFollow(FOLLOW_cnf_rule_in_cnf_rule2810);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1530:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
    public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
        EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set136=null;
        EugeneParser.negated_predicate_return n1 =null;

        EugeneParser.negated_predicate_return n2 =null;


        Object set136_tree=null;


        LogicalOr lor = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1535:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1535:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_negated_predicate_in_or_predicate2840);
            n1=negated_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, n1.getTree());


            if(!defer) {
                retval.p = (n1!=null?n1.p:null);
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1539:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==LC_OR||LA49_0==LOG_OR||LA49_0==UC_OR) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1539:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
            	    {
            	    set136=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
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


            	    pushFollow(FOLLOW_negated_predicate_in_or_predicate2856);
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
            	    break loop49;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1560:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
    public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
        EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set137=null;
        EugeneParser.predicate_return c =null;


        Object set137_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1562:2: ( ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1562:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1562:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LC_NOT||LA50_0==LOG_NOT||LA50_0==UC_NOT) ) {
                alt50=1;
            }
            else if ( (LA50_0==ID||LA50_0==LC_INDUCES||LA50_0==LC_REPRESSES||(LA50_0 >= LEFTP && LA50_0 <= LEFTSBR)||LA50_0==MINUS||LA50_0==NUMBER||LA50_0==REAL||LA50_0==STRING||LA50_0==UC_INDUCES||LA50_0==UC_REPRESSES||(LA50_0 >= 103 && LA50_0 <= 137)||(LA50_0 >= 139 && LA50_0 <= 176)||(LA50_0 >= 178 && LA50_0 <= 180)) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;

            }
            switch (alt50) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1562:5: ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer]
                    {
                    set137=(Token)input.LT(1);

                    if ( input.LA(1)==LC_NOT||input.LA(1)==LOG_NOT||input.LA(1)==UC_NOT ) {
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


                    pushFollow(FOLLOW_predicate_in_negated_predicate2894);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1571:4: c= predicate[defer]
                    {
                    pushFollow(FOLLOW_predicate_in_negated_predicate2904);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1578:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
            int alt53=3;
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
                case 138:
                case 177:
                    {
                    alt53=3;
                    }
                    break;
                case 113:
                case 152:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 5, input);

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
                    alt53=2;
                    }
                    break;
                case 122:
                case 161:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 7, input);

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
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 8, input);

                        throw nvae;

                    }

                    }
                    break;
                case 125:
                case 164:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 9, input);

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
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 10, input);

                        throw nvae;

                    }

                    }
                    break;
                case 115:
                case 154:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 11, input);

                        throw nvae;

                    }

                    }
                    break;
                case 116:
                case 155:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 12, input);

                        throw nvae;

                    }

                    }
                    break;
                case 123:
                case 162:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 13, input);

                        throw nvae;

                    }

                    }
                    break;
                case LC_INDUCES:
                case LC_REPRESSES:
                case UC_INDUCES:
                case UC_REPRESSES:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 114:
                case 117:
                case 118:
                case 120:
                case 121:
                case 124:
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
                case 156:
                case 157:
                case 159:
                case 160:
                case 163:
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
                case 179:
                case 180:
                    {
                    alt53=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;

                }

                }
                break;
            case NUMBER:
                {
                switch ( input.LA(2) ) {
                case 113:
                case 152:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 5, input);

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
                case 138:
                case 177:
                    {
                    alt53=3;
                    }
                    break;
                case 122:
                case 161:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 7, input);

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
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 8, input);

                        throw nvae;

                    }

                    }
                    break;
                case 125:
                case 164:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 9, input);

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
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 10, input);

                        throw nvae;

                    }

                    }
                    break;
                case 115:
                case 154:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 11, input);

                        throw nvae;

                    }

                    }
                    break;
                case 116:
                case 155:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 12, input);

                        throw nvae;

                    }

                    }
                    break;
                case 123:
                case 162:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt53=1;
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
                        alt53=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt53=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 53, 13, input);

                        throw nvae;

                    }

                    }
                    break;
                case LC_INDUCES:
                case LC_REPRESSES:
                case UC_INDUCES:
                case UC_REPRESSES:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 114:
                case 117:
                case 118:
                case 120:
                case 121:
                case 124:
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
                case 156:
                case 157:
                case 159:
                case 160:
                case 163:
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
                case 179:
                case 180:
                    {
                    alt53=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 2, input);

                    throw nvae;

                }

                }
                break;
            case LC_INDUCES:
            case LC_REPRESSES:
            case LEFTSBR:
            case UC_INDUCES:
            case UC_REPRESSES:
            case 103:
            case 104:
            case 105:
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
            case 178:
            case 179:
            case 180:
                {
                alt53=1;
                }
                break;
            case LEFTP:
            case MINUS:
            case REAL:
            case STRING:
                {
                alt53=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }

            switch (alt53) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:4: (lhs= operand[defer] )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==ID||LA51_0==LEFTSBR||LA51_0==NUMBER) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1580:5: lhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate2931);
                            lhs=operand(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lhs.getTree());


                            addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_ruleOperator_in_predicate2941);
                    op=ruleOperator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, op.getTree());


                    addToken((op!=null?input.toString(op.start,op.stop):null));	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1584:5: (rhs= operand[defer] )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==ID||LA52_0==LEFTSBR||LA52_0==NUMBER) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1584:6: rhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate2950);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1598:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_predicate2964); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1615:4: exp= expressionRule[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expressionRule_in_predicate2973);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1622:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' ) ;
    public final EugeneParser.operand_return operand(boolean defer) throws RecognitionException {
        EugeneParser.operand_return retval = new EugeneParser.operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token n=null;
        Token char_literal138=null;
        Token char_literal139=null;

        Object i_tree=null;
        Object n_tree=null;
        Object char_literal138_tree=null;
        Object char_literal139_tree=null;


        NamedElement element = null;
        int constant = -1;
        int index = -1;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1629:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1629:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1629:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            int alt54=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt54=1;
                }
                break;
            case NUMBER:
                {
                alt54=2;
                }
                break;
            case LEFTSBR:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1629:5: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_operand3004); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1641:4: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3013); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1646:4: '[' n= NUMBER ']'
                    {
                    char_literal138=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand3020); 
                    char_literal138_tree = 
                    (Object)adaptor.create(char_literal138)
                    ;
                    adaptor.addChild(root_0, char_literal138_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand3024); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal139=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand3026); 
                    char_literal139_tree = 
                    (Object)adaptor.create(char_literal139)
                    ;
                    adaptor.addChild(root_0, char_literal139_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1665:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
    public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
        EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expression_return lhs =null;

        EugeneParser.exp_op_return op =null;

        EugeneParser.expression_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1667:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1667:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expression_in_expressionRule3052);
            lhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_exp_op_in_expressionRule3057);
            op=exp_op(defer);

            state._fsp--;

            adaptor.addChild(root_0, op.getTree());

            pushFollow(FOLLOW_expression_in_expressionRule3062);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1678:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
    public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
        EugeneParser.expression_return retval = new EugeneParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTP140=null;
        Token RIGHTP142=null;
        EugeneParser.exp_operand_return lhs =null;

        EugeneParser.exp_operator_return expop =null;

        EugeneParser.expression_return rhs =null;

        EugeneParser.expression_return expression141 =null;


        Object LEFTP140_tree=null;
        Object RIGHTP142_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1680:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==ID||LA56_0==MINUS||LA56_0==NUMBER||LA56_0==REAL||LA56_0==STRING) ) {
                alt56=1;
            }
            else if ( (LA56_0==LEFTP) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }
            switch (alt56) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1680:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_exp_operand_in_expression3086);
                    lhs=exp_operand(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs.getTree());


                    if(!defer) {
                        retval.exp = new Expression((lhs!=null?lhs.eop:null), null, null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1684:4: (expop= exp_operator[defer] rhs= expression[defer] )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==DIV||LA55_0==MINUS||LA55_0==MULT||LA55_0==PLUS) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1684:6: expop= exp_operator[defer] rhs= expression[defer]
                            {
                            pushFollow(FOLLOW_exp_operator_in_expression3095);
                            expop=exp_operator(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, expop.getTree());

                            pushFollow(FOLLOW_expression_in_expression3100);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1689:4: LEFTP expression[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTP140=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3112); 
                    LEFTP140_tree = 
                    (Object)adaptor.create(LEFTP140)
                    ;
                    adaptor.addChild(root_0, LEFTP140_tree);


                    pushFollow(FOLLOW_expression_in_expression3114);
                    expression141=expression(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expression141.getTree());

                    RIGHTP142=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3117); 
                    RIGHTP142_tree = 
                    (Object)adaptor.create(RIGHTP142)
                    ;
                    adaptor.addChild(root_0, RIGHTP142_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1696:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
    public final EugeneParser.exp_operator_return exp_operator(boolean defer) throws RecognitionException {
        EugeneParser.exp_operator_return retval = new EugeneParser.exp_operator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLUS143=null;
        Token MINUS144=null;
        Token MULT145=null;
        Token DIV146=null;

        Object PLUS143_tree=null;
        Object MINUS144_tree=null;
        Object MULT145_tree=null;
        Object DIV146_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1698:2: ( PLUS | MINUS | MULT | DIV )
            int alt57=4;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt57=1;
                }
                break;
            case MINUS:
                {
                alt57=2;
                }
                break;
            case MULT:
                {
                alt57=3;
                }
                break;
            case DIV:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1698:4: PLUS
                    {
                    root_0 = (Object)adaptor.nil();


                    PLUS143=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3136); 
                    PLUS143_tree = 
                    (Object)adaptor.create(PLUS143)
                    ;
                    adaptor.addChild(root_0, PLUS143_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.PLUS;	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1703:4: MINUS
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS144=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3144); 
                    MINUS144_tree = 
                    (Object)adaptor.create(MINUS144)
                    ;
                    adaptor.addChild(root_0, MINUS144_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MINUS;	
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1708:4: MULT
                    {
                    root_0 = (Object)adaptor.nil();


                    MULT145=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3151); 
                    MULT145_tree = 
                    (Object)adaptor.create(MULT145)
                    ;
                    adaptor.addChild(root_0, MULT145_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MULT;	
                    }
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1713:4: DIV
                    {
                    root_0 = (Object)adaptor.nil();


                    DIV146=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3158); 
                    DIV146_tree = 
                    (Object)adaptor.create(DIV146)
                    ;
                    adaptor.addChild(root_0, DIV146_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1720:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
    public final EugeneParser.exp_operand_return exp_operand(boolean defer) throws RecognitionException {
        EugeneParser.exp_operand_return retval = new EugeneParser.exp_operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i1=null;
        Token i2=null;
        Token n=null;
        Token r=null;
        Token s=null;
        Token DOT147=null;
        Token LEFTSBR148=null;
        Token RIGHTSBR149=null;
        Token MINUS150=null;
        Token MINUS151=null;

        Object i1_tree=null;
        Object i2_tree=null;
        Object n_tree=null;
        Object r_tree=null;
        Object s_tree=null;
        Object DOT147_tree=null;
        Object LEFTSBR148_tree=null;
        Object RIGHTSBR149_tree=null;
        Object MINUS150_tree=null;
        Object MINUS151_tree=null;


        List<NamedElement> elements = null;
        NamedElement ne = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1726:2: ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
            int alt60=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt60=1;
                }
                break;
            case NUMBER:
                {
                alt60=2;
                }
                break;
            case MINUS:
                {
                int LA60_3 = input.LA(2);

                if ( (LA60_3==NUMBER) ) {
                    alt60=3;
                }
                else if ( (LA60_3==REAL) ) {
                    alt60=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 3, input);

                    throw nvae;

                }
                }
                break;
            case REAL:
                {
                alt60=4;
                }
                break;
            case STRING:
                {
                alt60=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;

            }

            switch (alt60) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1726:4: (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )*
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1726:4: (i1= ID DOT )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==ID) ) {
                            int LA58_1 = input.LA(2);

                            if ( (LA58_1==DOT) ) {
                                alt58=1;
                            }


                        }


                        switch (alt58) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1726:5: i1= ID DOT
                    	    {
                    	    i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3188); 
                    	    i1_tree = 
                    	    (Object)adaptor.create(i1)
                    	    ;
                    	    adaptor.addChild(root_0, i1_tree);


                    	    DOT147=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3190); 
                    	    DOT147_tree = 
                    	    (Object)adaptor.create(DOT147)
                    	    ;
                    	    adaptor.addChild(root_0, DOT147_tree);



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
                    	    break loop58;
                        }
                    } while (true);


                    i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3199); 
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
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1794:5: ( LEFTSBR n= NUMBER RIGHTSBR )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==LEFTSBR) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1794:6: LEFTSBR n= NUMBER RIGHTSBR
                    	    {
                    	    LEFTSBR148=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3205); 
                    	    LEFTSBR148_tree = 
                    	    (Object)adaptor.create(LEFTSBR148)
                    	    ;
                    	    adaptor.addChild(root_0, LEFTSBR148_tree);


                    	    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3209); 
                    	    n_tree = 
                    	    (Object)adaptor.create(n)
                    	    ;
                    	    adaptor.addChild(root_0, n_tree);


                    	    RIGHTSBR149=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3211); 
                    	    RIGHTSBR149_tree = 
                    	    (Object)adaptor.create(RIGHTSBR149)
                    	    ;
                    	    adaptor.addChild(root_0, RIGHTSBR149_tree);



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
                    	    break loop59;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1805:4: n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3223); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1812:4: MINUS n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS150=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3230); 
                    MINUS150_tree = 
                    (Object)adaptor.create(MINUS150)
                    ;
                    adaptor.addChild(root_0, MINUS150_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3234); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1819:4: r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3243); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1826:4: MINUS r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS151=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3250); 
                    MINUS151_tree = 
                    (Object)adaptor.create(MINUS151)
                    ;
                    adaptor.addChild(root_0, MINUS151_tree);


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3254); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1833:4: s= STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3263); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1843:1: regexp[boolean defer] :;
    public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
        EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1844:2: ()
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1845:2: 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1847:1: exp_op[boolean defer] : relationalOperators ;
    public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
        EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.relationalOperators_return relationalOperators152 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1848:2: ( relationalOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1848:4: relationalOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_relationalOperators_in_exp_op3290);
            relationalOperators152=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, relationalOperators152.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1856:1: grammarDeclaration[boolean defer] : GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP ;
    public final EugeneParser.grammarDeclaration_return grammarDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.grammarDeclaration_return retval = new EugeneParser.grammarDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token GRAMMAR153=null;
        Token LEFTP154=null;
        Token RIGHTP156=null;
        EugeneParser.list_of_production_rules_return list_of_production_rules155 =null;


        Object n_tree=null;
        Object GRAMMAR153_tree=null;
        Object LEFTP154_tree=null;
        Object RIGHTP156_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1857:2: ( GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1858:3: GRAMMAR n= ID LEFTP list_of_production_rules[defer] RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            GRAMMAR153=(Token)match(input,GRAMMAR,FOLLOW_GRAMMAR_in_grammarDeclaration3309); 
            GRAMMAR153_tree = 
            (Object)adaptor.create(GRAMMAR153)
            ;
            adaptor.addChild(root_0, GRAMMAR153_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_grammarDeclaration3313); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            LEFTP154=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_grammarDeclaration3315); 
            LEFTP154_tree = 
            (Object)adaptor.create(LEFTP154)
            ;
            adaptor.addChild(root_0, LEFTP154_tree);


            pushFollow(FOLLOW_list_of_production_rules_in_grammarDeclaration3317);
            list_of_production_rules155=list_of_production_rules(defer);

            state._fsp--;

            adaptor.addChild(root_0, list_of_production_rules155.getTree());

            RIGHTP156=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_grammarDeclaration3320); 
            RIGHTP156_tree = 
            (Object)adaptor.create(RIGHTP156)
            ;
            adaptor.addChild(root_0, RIGHTP156_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1861:1: list_of_production_rules[boolean defer] : production_rule[defer] SEMIC ( list_of_production_rules[defer] )? ;
    public final EugeneParser.list_of_production_rules_return list_of_production_rules(boolean defer) throws RecognitionException {
        EugeneParser.list_of_production_rules_return retval = new EugeneParser.list_of_production_rules_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SEMIC158=null;
        EugeneParser.production_rule_return production_rule157 =null;

        EugeneParser.list_of_production_rules_return list_of_production_rules159 =null;


        Object SEMIC158_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1862:2: ( production_rule[defer] SEMIC ( list_of_production_rules[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1862:4: production_rule[defer] SEMIC ( list_of_production_rules[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_production_rule_in_list_of_production_rules3332);
            production_rule157=production_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, production_rule157.getTree());

            SEMIC158=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_list_of_production_rules3335); 
            SEMIC158_tree = 
            (Object)adaptor.create(SEMIC158)
            ;
            adaptor.addChild(root_0, SEMIC158_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1862:33: ( list_of_production_rules[defer] )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==ID) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1862:34: list_of_production_rules[defer]
                    {
                    pushFollow(FOLLOW_list_of_production_rules_in_list_of_production_rules3338);
                    list_of_production_rules159=list_of_production_rules(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list_of_production_rules159.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1865:1: production_rule[boolean defer] : lhs= ID ARROW right_hand_side[defer] ;
    public final EugeneParser.production_rule_return production_rule(boolean defer) throws RecognitionException {
        EugeneParser.production_rule_return retval = new EugeneParser.production_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs=null;
        Token ARROW160=null;
        EugeneParser.right_hand_side_return right_hand_side161 =null;


        Object lhs_tree=null;
        Object ARROW160_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1866:2: (lhs= ID ARROW right_hand_side[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1866:4: lhs= ID ARROW right_hand_side[defer]
            {
            root_0 = (Object)adaptor.nil();


            lhs=(Token)match(input,ID,FOLLOW_ID_in_production_rule3358); 
            lhs_tree = 
            (Object)adaptor.create(lhs)
            ;
            adaptor.addChild(root_0, lhs_tree);



            if(!defer) {
                // ID denotes a non-terminal of the grammar
            }	
            	

            ARROW160=(Token)match(input,ARROW,FOLLOW_ARROW_in_production_rule3362); 
            ARROW160_tree = 
            (Object)adaptor.create(ARROW160)
            ;
            adaptor.addChild(root_0, ARROW160_tree);


            pushFollow(FOLLOW_right_hand_side_in_production_rule3364);
            right_hand_side161=right_hand_side(defer);

            state._fsp--;

            adaptor.addChild(root_0, right_hand_side161.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1873:1: right_hand_side[boolean defer] : (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] );
    public final EugeneParser.right_hand_side_return right_hand_side(boolean defer) throws RecognitionException {
        EugeneParser.right_hand_side_return retval = new EugeneParser.right_hand_side_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token COMMA162=null;
        EugeneParser.right_hand_side_return right_hand_side163 =null;

        EugeneParser.interaction_return interaction164 =null;


        Object i_tree=null;
        Object COMMA162_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1874:2: (i= ID ( COMMA right_hand_side[defer] )? | interaction[defer, \"some_string\"] )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==ID) ) {
                int LA63_1 = input.LA(2);

                if ( (LA63_1==COMMA||LA63_1==SEMIC) ) {
                    alt63=1;
                }
                else if ( (LA63_1==LC_INDUCES||LA63_1==LC_REPRESSES||LA63_1==UC_INDUCES||LA63_1==UC_REPRESSES) ) {
                    alt63=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1874:4: i= ID ( COMMA right_hand_side[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_right_hand_side3380); 
                    i_tree = 
                    (Object)adaptor.create(i)
                    ;
                    adaptor.addChild(root_0, i_tree);



                    if(!defer) {
                        // ID must be either a terminal (i.e. a part)
                        // or a non-terminal defined within the grammar
                    }	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1879:4: ( COMMA right_hand_side[defer] )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==COMMA) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1879:5: COMMA right_hand_side[defer]
                            {
                            COMMA162=(Token)match(input,COMMA,FOLLOW_COMMA_in_right_hand_side3385); 
                            COMMA162_tree = 
                            (Object)adaptor.create(COMMA162)
                            ;
                            adaptor.addChild(root_0, COMMA162_tree);


                            pushFollow(FOLLOW_right_hand_side_in_right_hand_side3387);
                            right_hand_side163=right_hand_side(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, right_hand_side163.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1880:4: interaction[defer, \"some_string\"]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_right_hand_side3395);
                    interaction164=interaction(defer, "some_string");

                    state._fsp--;

                    adaptor.addChild(root_0, interaction164.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1887:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
    public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token INTERACTION165=null;
        Token LEFTP166=null;
        Token RIGHTP167=null;
        EugeneParser.interaction_return i1 =null;

        EugeneParser.interaction_return i2 =null;


        Object name_tree=null;
        Object INTERACTION165_tree=null;
        Object LEFTP166_tree=null;
        Object RIGHTP167_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1889:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==ID) ) {
                alt64=1;
            }
            else if ( (LA64_0==INTERACTION) ) {
                alt64=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;

            }
            switch (alt64) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1889:4: i1= interaction[defer, null]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3420);
                    i1=interaction(defer, null);

                    state._fsp--;

                    adaptor.addChild(root_0, i1.getTree());


                    if(!defer) {
                        retval.ia = (i1!=null?i1.ia:null);
                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1894:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    INTERACTION165=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3428); 
                    INTERACTION165_tree = 
                    (Object)adaptor.create(INTERACTION165)
                    ;
                    adaptor.addChild(root_0, INTERACTION165_tree);


                    name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3432); 
                    name_tree = 
                    (Object)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    LEFTP166=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3434); 
                    LEFTP166_tree = 
                    (Object)adaptor.create(LEFTP166)
                    ;
                    adaptor.addChild(root_0, LEFTP166_tree);


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3438);
                    i2=interaction(defer, (name!=null?name.getText():null));

                    state._fsp--;

                    adaptor.addChild(root_0, i2.getTree());

                    RIGHTP167=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3441); 
                    RIGHTP167_tree = 
                    (Object)adaptor.create(RIGHTP167)
                    ;
                    adaptor.addChild(root_0, RIGHTP167_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1901:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
    public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
        EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs1=null;
        Token rhs1=null;
        Token lhs2=null;
        Token LEFTP168=null;
        Token RIGHTP169=null;
        EugeneParser.interactionType_return t1 =null;

        EugeneParser.interactionType_return t2 =null;

        EugeneParser.interaction_return rhs2 =null;


        Object lhs1_tree=null;
        Object rhs1_tree=null;
        Object lhs2_tree=null;
        Object LEFTP168_tree=null;
        Object RIGHTP169_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1903:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==ID) ) {
                int LA65_1 = input.LA(2);

                if ( (LA65_1==LC_REPRESSES||LA65_1==UC_REPRESSES) ) {
                    int LA65_2 = input.LA(3);

                    if ( (LA65_2==ID) ) {
                        alt65=1;
                    }
                    else if ( (LA65_2==LEFTP) ) {
                        alt65=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 65, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA65_1==LC_INDUCES||LA65_1==UC_INDUCES) ) {
                    int LA65_3 = input.LA(3);

                    if ( (LA65_3==ID) ) {
                        alt65=1;
                    }
                    else if ( (LA65_3==LEFTP) ) {
                        alt65=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 65, 3, input);

                        throw nvae;

                    }
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1903:4: lhs1= ID t1= interactionType[defer] rhs1= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3464); 
                    lhs1_tree = 
                    (Object)adaptor.create(lhs1)
                    ;
                    adaptor.addChild(root_0, lhs1_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3468);
                    t1=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t1.getTree());

                    rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3473); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1912:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3482); 
                    lhs2_tree = 
                    (Object)adaptor.create(lhs2)
                    ;
                    adaptor.addChild(root_0, lhs2_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3486);
                    t2=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t2.getTree());

                    LEFTP168=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3489); 
                    LEFTP168_tree = 
                    (Object)adaptor.create(LEFTP168)
                    ;
                    adaptor.addChild(root_0, LEFTP168_tree);


                    pushFollow(FOLLOW_interaction_in_interaction3493);
                    rhs2=interaction(defer, name);

                    state._fsp--;

                    adaptor.addChild(root_0, rhs2.getTree());

                    RIGHTP169=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3496); 
                    RIGHTP169_tree = 
                    (Object)adaptor.create(RIGHTP169)
                    ;
                    adaptor.addChild(root_0, RIGHTP169_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1923:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
    public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
        EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set170=null;
        Token set171=null;

        Object set170_tree=null;
        Object set171_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1925:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==LC_REPRESSES||LA66_0==UC_REPRESSES) ) {
                alt66=1;
            }
            else if ( (LA66_0==LC_INDUCES||LA66_0==UC_INDUCES) ) {
                alt66=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }
            switch (alt66) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1925:4: ( UC_REPRESSES | LC_REPRESSES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set170=(Token)input.LT(1);

                    if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set170)
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1930:4: ( UC_INDUCES | LC_INDUCES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set171=(Token)input.LT(1);

                    if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set171)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1946:1: functionCall[boolean defer] returns [NamedElement e] : (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.functionCall_return functionCall(boolean defer) throws RecognitionException {
        EugeneParser.functionCall_return retval = new EugeneParser.functionCall_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token pr=null;
        Token pe=null;
        Token idToken=null;
        Token LEFTP172=null;
        Token RIGHTP173=null;

        Object pr_tree=null;
        Object pe_tree=null;
        Object idToken_tree=null;
        Object LEFTP172_tree=null;
        Object RIGHTP173_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1948:2: ( (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1948:4: (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1948:4: (pr= PRODUCT |pe= PERMUTE )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==PRODUCT) ) {
                alt67=1;
            }
            else if ( (LA67_0==PERMUTE) ) {
                alt67=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;

            }
            switch (alt67) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1948:5: pr= PRODUCT
                    {
                    pr=(Token)match(input,PRODUCT,FOLLOW_PRODUCT_in_functionCall3563); 
                    pr_tree = 
                    (Object)adaptor.create(pr)
                    ;
                    adaptor.addChild(root_0, pr_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1948:16: pe= PERMUTE
                    {
                    pe=(Token)match(input,PERMUTE,FOLLOW_PERMUTE_in_functionCall3567); 
                    pe_tree = 
                    (Object)adaptor.create(pe)
                    ;
                    adaptor.addChild(root_0, pe_tree);


                    }
                    break;

            }


            LEFTP172=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_functionCall3570); 
            LEFTP172_tree = 
            (Object)adaptor.create(LEFTP172)
            ;
            adaptor.addChild(root_0, LEFTP172_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_functionCall3574); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP173=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_functionCall3576); 
            RIGHTP173_tree = 
            (Object)adaptor.create(RIGHTP173)
            ;
            adaptor.addChild(root_0, RIGHTP173_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1975:1: printStatement[boolean defer] : ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP );
    public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
        EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PRINTLN174=null;
        Token LEFTP175=null;
        Token RIGHTP176=null;
        Token PRINT177=null;
        Token LEFTP178=null;
        Token RIGHTP179=null;
        EugeneParser.toPrint_return tp =null;


        Object PRINTLN174_tree=null;
        Object LEFTP175_tree=null;
        Object RIGHTP176_tree=null;
        Object PRINT177_tree=null;
        Object LEFTP178_tree=null;
        Object RIGHTP179_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1976:2: ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP )
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==PRINTLN) ) {
                alt68=1;
            }
            else if ( (LA68_0==PRINT) ) {
                alt68=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;

            }
            switch (alt68) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1976:4: PRINTLN LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINTLN174=(Token)match(input,PRINTLN,FOLLOW_PRINTLN_in_printStatement3594); 
                    PRINTLN174_tree = 
                    (Object)adaptor.create(PRINTLN174)
                    ;
                    adaptor.addChild(root_0, PRINTLN174_tree);


                    LEFTP175=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3596); 
                    LEFTP175_tree = 
                    (Object)adaptor.create(LEFTP175)
                    ;
                    adaptor.addChild(root_0, LEFTP175_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3600);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP176=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3603); 
                    RIGHTP176_tree = 
                    (Object)adaptor.create(RIGHTP176)
                    ;
                    adaptor.addChild(root_0, RIGHTP176_tree);



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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:1989:4: PRINT LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINT177=(Token)match(input,PRINT,FOLLOW_PRINT_in_printStatement3610); 
                    PRINT177_tree = 
                    (Object)adaptor.create(PRINT177)
                    ;
                    adaptor.addChild(root_0, PRINT177_tree);


                    LEFTP178=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3612); 
                    LEFTP178_tree = 
                    (Object)adaptor.create(LEFTP178)
                    ;
                    adaptor.addChild(root_0, LEFTP178_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3616);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP179=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3619); 
                    RIGHTP179_tree = 
                    (Object)adaptor.create(RIGHTP179)
                    ;
                    adaptor.addChild(root_0, RIGHTP179_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2003:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
    public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return exp =null;

        EugeneParser.toPrint_prime_return tpp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2005:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2005:4: exp= expr[defer] tpp= toPrint_prime[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_toPrint3640);
            exp=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, exp.getTree());

            pushFollow(FOLLOW_toPrint_prime_in_toPrint3645);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2018:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
    public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA180=null;
        EugeneParser.toPrint_return tp =null;


        Object COMMA180_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2020:2: (| COMMA tp= toPrint[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2020:4: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.sb = new StringBuilder();
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2025:4: COMMA tp= toPrint[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    COMMA180=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime3671); 
                    COMMA180_tree = 
                    (Object)adaptor.create(COMMA180)
                    ;
                    adaptor.addChild(root_0, COMMA180_tree);


                    pushFollow(FOLLOW_toPrint_in_toPrint_prime3675);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2037:1: imperativeStatements[boolean defer] : ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] );
    public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException {
        EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ifStatement_return ifStatement181 =null;

        EugeneParser.forall_iterator_return forall_iterator182 =null;

        EugeneParser.for_loop_return for_loop183 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2038:2: ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] )
            int alt70=3;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;

            }

            switch (alt70) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2038:4: ifStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifStatement_in_imperativeStatements3695);
                    ifStatement181=ifStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ifStatement181.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2039:4: forall_iterator[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_forall_iterator_in_imperativeStatements3701);
                    forall_iterator182=forall_iterator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, forall_iterator182.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2040:4: for_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_for_loop_in_imperativeStatements3707);
                    for_loop183=for_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, for_loop183.getTree());

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

        Token set184=null;
        Token LEFTP185=null;
        Token RIGHTP186=null;
        Token LEFTCUR187=null;
        Token RIGHTCUR188=null;
        Token set189=null;
        Token LEFTP190=null;
        Token RIGHTP191=null;
        Token LEFTCUR192=null;
        Token RIGHTCUR193=null;
        Token set194=null;
        Token LEFTCUR195=null;
        Token RIGHTCUR196=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set184_tree=null;
        Object LEFTP185_tree=null;
        Object RIGHTP186_tree=null;
        Object LEFTCUR187_tree=null;
        Object RIGHTCUR188_tree=null;
        Object set189_tree=null;
        Object LEFTP190_tree=null;
        Object RIGHTP191_tree=null;
        Object LEFTCUR192_tree=null;
        Object RIGHTCUR193_tree=null;
        Object set194_tree=null;
        Object LEFTCUR195_tree=null;
        Object RIGHTCUR196_tree=null;


        boolean bExecuted = false;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2047:2: ( ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2051:3: ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            {
            root_0 = (Object)adaptor.nil();


            set184=(Token)input.LT(1);

            if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set184)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP185=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3739); 
            LEFTP185_tree = 
            (Object)adaptor.create(LEFTP185)
            ;
            adaptor.addChild(root_0, LEFTP185_tree);


            pushFollow(FOLLOW_imp_condition_in_ifStatement3743);
            co=imp_condition(defer);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP186=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3746); 
            RIGHTP186_tree = 
            (Object)adaptor.create(RIGHTP186)
            ;
            adaptor.addChild(root_0, RIGHTP186_tree);


            LEFTCUR187=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3748); 
            LEFTCUR187_tree = 
            (Object)adaptor.create(LEFTCUR187)
            ;
            adaptor.addChild(root_0, LEFTCUR187_tree);


            pushFollow(FOLLOW_listOfStatements_in_ifStatement3756);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR188=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3759); 
            RIGHTCUR188_tree = 
            (Object)adaptor.create(RIGHTCUR188)
            ;
            adaptor.addChild(root_0, RIGHTCUR188_tree);



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
            	    set189=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
            	        input.consume();
            	        adaptor.addChild(root_0, 
            	        (Object)adaptor.create(set189)
            	        );
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    LEFTP190=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3780); 
            	    LEFTP190_tree = 
            	    (Object)adaptor.create(LEFTP190)
            	    ;
            	    adaptor.addChild(root_0, LEFTP190_tree);


            	    pushFollow(FOLLOW_imp_condition_in_ifStatement3784);
            	    co=imp_condition(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, co.getTree());

            	    RIGHTP191=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3787); 
            	    RIGHTP191_tree = 
            	    (Object)adaptor.create(RIGHTP191)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP191_tree);


            	    LEFTCUR192=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3789); 
            	    LEFTCUR192_tree = 
            	    (Object)adaptor.create(LEFTCUR192)
            	    ;
            	    adaptor.addChild(root_0, LEFTCUR192_tree);


            	    pushFollow(FOLLOW_listOfStatements_in_ifStatement3797);
            	    stmts=listOfStatements(true);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmts.getTree());

            	    RIGHTCUR193=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3800); 
            	    RIGHTCUR193_tree = 
            	    (Object)adaptor.create(RIGHTCUR193)
            	    ;
            	    adaptor.addChild(root_0, RIGHTCUR193_tree);



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
                    set194=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set194)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTCUR195=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3822); 
                    LEFTCUR195_tree = 
                    (Object)adaptor.create(LEFTCUR195)
                    ;
                    adaptor.addChild(root_0, LEFTCUR195_tree);


                    pushFollow(FOLLOW_listOfStatements_in_ifStatement3830);
                    stmts=listOfStatements(true);

                    state._fsp--;

                    adaptor.addChild(root_0, stmts.getTree());

                    RIGHTCUR196=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3833); 
                    RIGHTCUR196_tree = 
                    (Object)adaptor.create(RIGHTCUR196)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR196_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2113:1: imp_condition[boolean defer] returns [boolean bTrue] : lhs= atom[defer] ro= relationalOperators rhs= atom[defer] ;
    public final EugeneParser.imp_condition_return imp_condition(boolean defer) throws RecognitionException {
        EugeneParser.imp_condition_return retval = new EugeneParser.imp_condition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.atom_return lhs =null;

        EugeneParser.relationalOperators_return ro =null;

        EugeneParser.atom_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2115:2: (lhs= atom[defer] ro= relationalOperators rhs= atom[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2115:4: lhs= atom[defer] ro= relationalOperators rhs= atom[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_imp_condition3857);
            lhs=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_relationalOperators_in_imp_condition3862);
            ro=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, ro.getTree());

            pushFollow(FOLLOW_atom_in_imp_condition3866);
            rhs=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, rhs.getTree());


            if(!defer) {
                try {
                    retval.bTrue = this.interp.evaluateCondition(
                                     (lhs!=null?lhs.p:null), 
                                     (ro!=null?input.toString(ro.start,ro.stop):null), 
                                     (rhs!=null?rhs.p:null));
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
    // $ANTLR end "imp_condition"


    public static class forall_iterator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "forall_iterator"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2129:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR ;
    public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException {
        EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token it=null;
        Token i=null;
        Token set197=null;
        Token COLON198=null;
        Token LEFTCUR199=null;
        Token RIGHTCUR201=null;
        EugeneParser.listOfStatements_return listOfStatements200 =null;


        Object it_tree=null;
        Object i_tree=null;
        Object set197_tree=null;
        Object COLON198_tree=null;
        Object LEFTCUR199_tree=null;
        Object RIGHTCUR201_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2130:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2130:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set197=(Token)input.LT(1);

            if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set197)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2130:26: (it= ID COLON )?
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2130:27: it= ID COLON
                    {
                    it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3891); 
                    it_tree = 
                    (Object)adaptor.create(it)
                    ;
                    adaptor.addChild(root_0, it_tree);


                    COLON198=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator3893); 
                    COLON198_tree = 
                    (Object)adaptor.create(COLON198)
                    ;
                    adaptor.addChild(root_0, COLON198_tree);


                    }
                    break;

            }


            i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3899); 
            i_tree = 
            (Object)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            LEFTCUR199=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator3901); 
            LEFTCUR199_tree = 
            (Object)adaptor.create(LEFTCUR199)
            ;
            adaptor.addChild(root_0, LEFTCUR199_tree);


            pushFollow(FOLLOW_listOfStatements_in_forall_iterator3906);
            listOfStatements200=listOfStatements(defer);

            state._fsp--;

            adaptor.addChild(root_0, listOfStatements200.getTree());


            if(!defer) {

            }			
            	

            RIGHTCUR201=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator3913); 
            RIGHTCUR201_tree = 
            (Object)adaptor.create(RIGHTCUR201)
            ;
            adaptor.addChild(root_0, RIGHTCUR201_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2139:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException {
        EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set202=null;
        Token LEFTP203=null;
        Token SEMIC204=null;
        Token SEMIC205=null;
        Token RIGHTP206=null;
        Token LEFTCUR207=null;
        Token RIGHTCUR208=null;
        EugeneParser.variableDeclaration_return ds =null;

        EugeneParser.imp_condition_return co =null;

        EugeneParser.assignment_return as =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set202_tree=null;
        Object LEFTP203_tree=null;
        Object SEMIC204_tree=null;
        Object SEMIC205_tree=null;
        Object RIGHTP206_tree=null;
        Object LEFTCUR207_tree=null;
        Object RIGHTCUR208_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2140:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2140:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set202=(Token)input.LT(1);

            if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set202)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP203=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop3931); 
            LEFTP203_tree = 
            (Object)adaptor.create(LEFTP203)
            ;
            adaptor.addChild(root_0, LEFTP203_tree);


            pushFollow(FOLLOW_variableDeclaration_in_for_loop3935);
            ds=variableDeclaration(true);

            state._fsp--;

            adaptor.addChild(root_0, ds.getTree());

            SEMIC204=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop3938); 
            SEMIC204_tree = 
            (Object)adaptor.create(SEMIC204)
            ;
            adaptor.addChild(root_0, SEMIC204_tree);


            pushFollow(FOLLOW_imp_condition_in_for_loop3942);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            SEMIC205=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop3945); 
            SEMIC205_tree = 
            (Object)adaptor.create(SEMIC205)
            ;
            adaptor.addChild(root_0, SEMIC205_tree);


            pushFollow(FOLLOW_assignment_in_for_loop3949);
            as=assignment(true);

            state._fsp--;

            adaptor.addChild(root_0, as.getTree());

            RIGHTP206=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop3952); 
            RIGHTP206_tree = 
            (Object)adaptor.create(RIGHTP206)
            ;
            adaptor.addChild(root_0, RIGHTP206_tree);


            LEFTCUR207=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop3954); 
            LEFTCUR207_tree = 
            (Object)adaptor.create(LEFTCUR207)
            ;
            adaptor.addChild(root_0, LEFTCUR207_tree);


            pushFollow(FOLLOW_listOfStatements_in_for_loop3962);
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
            		

            RIGHTCUR208=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop3969); 
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
    // $ANTLR end "for_loop"


    public static class listOfStatements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "listOfStatements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2160:1: listOfStatements[boolean defer] : (stmtToken= statement[defer] )+ ;
    public final EugeneParser.listOfStatements_return listOfStatements(boolean defer) throws RecognitionException {
        EugeneParser.listOfStatements_return retval = new EugeneParser.listOfStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.statement_return stmtToken =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2161:2: ( (stmtToken= statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2161:4: (stmtToken= statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2161:4: (stmtToken= statement[defer] )+
            int cnt74=0;
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==ARRAY||(LA74_0 >= ASSERT && LA74_0 <= COLLECTION)||LA74_0==DEVICE||(LA74_0 >= EXIT_LC && LA74_0 <= EXIT_UC)||LA74_0==GRAMMAR||(LA74_0 >= HASHMARK && LA74_0 <= ID)||LA74_0==INTERACTION||(LA74_0 >= LC_FOR && LA74_0 <= LC_INCLUDE)||LA74_0==LC_PIGEON||LA74_0==NUM||(LA74_0 >= PART && LA74_0 <= PIGEON)||(LA74_0 >= PRINT && LA74_0 <= PROPERTY)||(LA74_0 >= RULE && LA74_0 <= SEMIC)||(LA74_0 >= TXT && LA74_0 <= TYPE)||(LA74_0 >= UC_FOR && LA74_0 <= UC_INCLUDE)) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2161:5: stmtToken= statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_listOfStatements3988);
            	    stmtToken=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmtToken.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt74 >= 1 ) break loop74;
                        EarlyExitException eee =
                            new EarlyExitException(74, input);
                        throw eee;
                }
                cnt74++;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2169:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )* ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2171:2: (e= multExpr[defer] (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2171:4: e= multExpr[defer] (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_expr4016);
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2183:5: (pl= PLUS e= multExpr[defer] |mi= MINUS e= multExpr[defer] )*
            loop75:
            do {
                int alt75=3;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==PLUS) ) {
                    alt75=1;
                }
                else if ( (LA75_0==MINUS) ) {
                    alt75=2;
                }


                switch (alt75) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2183:6: pl= PLUS e= multExpr[defer]
            	    {
            	    pl=(Token)match(input,PLUS,FOLLOW_PLUS_in_expr4025); 
            	    pl_tree = 
            	    (Object)adaptor.create(pl)
            	    ;
            	    adaptor.addChild(root_0, pl_tree);


            	    pushFollow(FOLLOW_multExpr_in_expr4029);
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
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2192:7: mi= MINUS e= multExpr[defer]
            	    {
            	    mi=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr4039); 
            	    mi_tree = 
            	    (Object)adaptor.create(mi)
            	    ;
            	    adaptor.addChild(root_0, mi_tree);


            	    pushFollow(FOLLOW_multExpr_in_expr4043);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2204:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2206:2: (e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2206:4: e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_multExpr4069);
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2220:5: ( (mul= MULT |div= DIV ) e= atom[defer] )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==DIV||LA77_0==MULT) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2220:7: (mul= MULT |div= DIV ) e= atom[defer]
            	    {
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2220:7: (mul= MULT |div= DIV )
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
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2220:8: mul= MULT
            	            {
            	            mul=(Token)match(input,MULT,FOLLOW_MULT_in_multExpr4080); 
            	            mul_tree = 
            	            (Object)adaptor.create(mul)
            	            ;
            	            adaptor.addChild(root_0, mul_tree);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2220:17: div= DIV
            	            {
            	            div=(Token)match(input,DIV,FOLLOW_DIV_in_multExpr4084); 
            	            div_tree = 
            	            (Object)adaptor.create(div)
            	            ;
            	            adaptor.addChild(root_0, div_tree);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_atom_in_multExpr4089);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2236:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= TRUE |f= FALSE ) | ID oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR );
    public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
        EugeneParser.atom_return retval = new EugeneParser.atom_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token t=null;
        Token f=null;
        Token MINUS209=null;
        Token ID210=null;
        Token STRING211=null;
        Token char_literal212=null;
        Token char_literal214=null;
        Token LEFTSBR215=null;
        Token RIGHTSBR217=null;
        EugeneParser.object_access_return oc =null;

        EugeneParser.expr_return expr213 =null;

        EugeneParser.list_return list216 =null;


        Object n_tree=null;
        Object t_tree=null;
        Object f_tree=null;
        Object MINUS209_tree=null;
        Object ID210_tree=null;
        Object STRING211_tree=null;
        Object char_literal212_tree=null;
        Object char_literal214_tree=null;
        Object LEFTSBR215_tree=null;
        Object RIGHTSBR217_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2238:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= TRUE |f= FALSE ) | ID oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR )
            int alt81=7;
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
            case FALSE:
            case TRUE:
                {
                alt81=3;
                }
                break;
            case ID:
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;

            }

            switch (alt81) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2238:4: (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2238:4: (n= NUMBER |n= REAL )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2238:5: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4116); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2238:16: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4122); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2245:4: MINUS (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS209=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom4132); 
                    MINUS209_tree = 
                    (Object)adaptor.create(MINUS209)
                    ;
                    adaptor.addChild(root_0, MINUS209_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2245:10: (n= NUMBER |n= REAL )
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2245:11: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom4137); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2245:22: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom4143); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2252:4: (t= TRUE |f= FALSE )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2252:4: (t= TRUE |f= FALSE )
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==TRUE) ) {
                        alt80=1;
                    }
                    else if ( (LA80_0==FALSE) ) {
                        alt80=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 80, 0, input);

                        throw nvae;

                    }
                    switch (alt80) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2252:5: t= TRUE
                            {
                            t=(Token)match(input,TRUE,FOLLOW_TRUE_in_atom4156); 
                            t_tree = 
                            (Object)adaptor.create(t)
                            ;
                            adaptor.addChild(root_0, t_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2252:14: f= FALSE
                            {
                            f=(Token)match(input,FALSE,FOLLOW_FALSE_in_atom4162); 
                            f_tree = 
                            (Object)adaptor.create(f)
                            ;
                            adaptor.addChild(root_0, f_tree);


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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2263:4: ID oc= object_access[defer, $element]
                    {
                    root_0 = (Object)adaptor.nil();


                    ID210=(Token)match(input,ID,FOLLOW_ID_in_atom4172); 
                    ID210_tree = 
                    (Object)adaptor.create(ID210)
                    ;
                    adaptor.addChild(root_0, ID210_tree);



                    	if(!defer) {
                    		try {
                    			retval.element = this.interp.get((ID210!=null?ID210.getText():null));
                    					
                    			if(null != retval.element) {
                    				if(retval.element instanceof Variable) {
                    					retval.p = copyVariable((Variable)retval.element);
                    					retval.primVariable = (Variable)retval.element;
                    				}
                    			} else {
                    				throw new EugeneException((ID210!=null?ID210.getText():null) + " is not declared.");
                    			}
                    		} catch(EugeneException ee) {
                    			printError(ee.toString());
                    		}
                    	}
                    		

                    pushFollow(FOLLOW_object_access_in_atom4180);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2289:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING211=(Token)match(input,STRING,FOLLOW_STRING_in_atom4189); 
                    STRING211_tree = 
                    (Object)adaptor.create(STRING211)
                    ;
                    adaptor.addChild(root_0, STRING211_tree);



                    		if(!defer) {
                    			retval.p.type = EugeneConstants.TXT;
                    			retval.p.txt = (STRING211!=null?STRING211.getText():null).substring(1, (STRING211!=null?STRING211.getText():null).length()-1);
                    		}
                    		

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2296:4: '(' expr[defer] ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal212=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4198); 
                    char_literal212_tree = 
                    (Object)adaptor.create(char_literal212)
                    ;
                    adaptor.addChild(root_0, char_literal212_tree);


                    pushFollow(FOLLOW_expr_in_atom4200);
                    expr213=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expr213.getTree());

                    char_literal214=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4203); 
                    char_literal214_tree = 
                    (Object)adaptor.create(char_literal214)
                    ;
                    adaptor.addChild(root_0, char_literal214_tree);



                    		if(!defer) {
                    			retval.p = (expr213!=null?expr213.p:null);
                    			retval.primVariable = (expr213!=null?expr213.primVariable:null);
                    		}
                    		

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2303:5: LEFTSBR list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR215=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4213); 
                    LEFTSBR215_tree = 
                    (Object)adaptor.create(LEFTSBR215)
                    ;
                    adaptor.addChild(root_0, LEFTSBR215_tree);


                    pushFollow(FOLLOW_list_in_atom4215);
                    list216=list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list216.getTree());

                    RIGHTSBR217=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4218); 
                    RIGHTSBR217_tree = 
                    (Object)adaptor.create(RIGHTSBR217)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR217_tree);



                    		if(!defer) {
                    			retval.p = (list216!=null?list216.listPrim:null);
                    			retval.primVariable = (list216!=null?list216.listPrim:null);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2389:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
    public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
        EugeneParser.list_return retval = new EugeneParser.list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA218=null;
        EugeneParser.expr_return str1 =null;

        EugeneParser.expr_return str2 =null;


        Object COMMA218_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2391:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2391:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_list4244);
            str1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, str1.getTree());


            if(!defer){
                retval.listPrim = new Variable();
                retval.listPrim.type = typeList + "List";
                addToListPrim((str1!=null?str1.p:null), retval.listPrim);
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2397:5: ( COMMA str2= expr[defer] )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==COMMA) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2397:6: COMMA str2= expr[defer]
            	    {
            	    COMMA218=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4251); 
            	    COMMA218_tree = 
            	    (Object)adaptor.create(COMMA218)
            	    ;
            	    adaptor.addChild(root_0, COMMA218_tree);


            	    pushFollow(FOLLOW_expr_in_list4255);
            	    str2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, str2.getTree());


            	    if(!defer) {
            	        addToListPrim((str2!=null?str2.p:null), retval.listPrim);
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


    public static class object_access_return extends ParserRuleReturnScope {
        public NamedElement child;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "object_access"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2426:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
    public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
        EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token DOT219=null;
        Token LEFTSBR220=null;
        Token RIGHTSBR221=null;
        EugeneParser.expr_return exp =null;

        EugeneParser.object_access_return o =null;


        Object id_tree=null;
        Object DOT219_tree=null;
        Object LEFTSBR220_tree=null;
        Object RIGHTSBR221_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2428:2: (| ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==COMMA||LA84_0==DIV||LA84_0==EQUALS||LA84_0==GEQUAL||LA84_0==GTHAN||LA84_0==LEQUAL||(LA84_0 >= LTHAN && LA84_0 <= MINUS)||(LA84_0 >= MULT && LA84_0 <= NEQUAL)||LA84_0==PLUS||(LA84_0 >= RIGHTP && LA84_0 <= RIGHTSBR)||LA84_0==SEMIC||LA84_0==113||(LA84_0 >= 115 && LA84_0 <= 116)||LA84_0==119||(LA84_0 >= 122 && LA84_0 <= 123)||LA84_0==125||(LA84_0 >= 138 && LA84_0 <= 139)||LA84_0==152||(LA84_0 >= 154 && LA84_0 <= 155)||LA84_0==158||(LA84_0 >= 161 && LA84_0 <= 162)||LA84_0==164||(LA84_0 >= 177 && LA84_0 <= 178)) ) {
                alt84=1;
            }
            else if ( (LA84_0==DOT||LA84_0==LEFTSBR) ) {
                alt84=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }
            switch (alt84) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2429:2: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.child = parent;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2434:4: ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2434:4: ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==DOT) ) {
                        alt83=1;
                    }
                    else if ( (LA83_0==LEFTSBR) ) {
                        alt83=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 83, 0, input);

                        throw nvae;

                    }
                    switch (alt83) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2434:5: DOT id= ID
                            {
                            DOT219=(Token)match(input,DOT,FOLLOW_DOT_in_object_access4295); 
                            DOT219_tree = 
                            (Object)adaptor.create(DOT219)
                            ;
                            adaptor.addChild(root_0, DOT219_tree);


                            id=(Token)match(input,ID,FOLLOW_ID_in_object_access4299); 
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2446:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
                            {
                            LEFTSBR220=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access4307); 
                            LEFTSBR220_tree = 
                            (Object)adaptor.create(LEFTSBR220)
                            ;
                            adaptor.addChild(root_0, LEFTSBR220_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2446:12: (exp= expr[defer] )
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2446:13: exp= expr[defer]
                            {
                            pushFollow(FOLLOW_expr_in_object_access4312);
                            exp=expr(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, exp.getTree());

                            }


                            RIGHTSBR221=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access4316); 
                            RIGHTSBR221_tree = 
                            (Object)adaptor.create(RIGHTSBR221)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR221_tree);



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


                    pushFollow(FOLLOW_object_access_in_object_access4323);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2471:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] );
    public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
        EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.sbolStatement_return s =null;

        EugeneParser.pigeonStatement_return p =null;

        EugeneParser.importStatement_return i =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2473:2: (s= sbolStatement[defer] |p= pigeonStatement[defer] |i= importStatement[defer] )
            int alt85=3;
            switch ( input.LA(1) ) {
            case SBOL:
                {
                alt85=1;
                }
                break;
            case LC_PIGEON:
            case PIGEON:
                {
                alt85=2;
                }
                break;
            case LC_IMPORT:
            case UC_IMPORT:
                {
                alt85=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;

            }

            switch (alt85) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2473:4: s= sbolStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_sbolStatement_in_dataExchange4349);
                    s=sbolStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());


                    if(!defer) {
                        retval.e = (s!=null?s.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2478:4: p= pigeonStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pigeonStatement_in_dataExchange4359);
                    p=pigeonStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2479:4: i= importStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_importStatement_in_dataExchange4367);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2490:1: includeStatement[boolean defer] : ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING ;
    public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
        EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token HASHMARK222=null;
        Token set223=null;

        Object file_tree=null;
        Object HASHMARK222_tree=null;
        Object set223_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2491:2: ( ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2491:4: ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2491:4: ( HASHMARK )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==HASHMARK) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2491:5: HASHMARK
                    {
                    HASHMARK222=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement4389); 
                    HASHMARK222_tree = 
                    (Object)adaptor.create(HASHMARK222)
                    ;
                    adaptor.addChild(root_0, HASHMARK222_tree);


                    }
                    break;

            }


            set223=(Token)input.LT(1);

            if ( input.LA(1)==LC_INCLUDE||input.LA(1)==UC_INCLUDE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set223)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement4401); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2503:1: importStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP ;
    public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
        EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token set224=null;
        Token LEFTP225=null;
        Token RIGHTP226=null;

        Object file_tree=null;
        Object set224_tree=null;
        Object LEFTP225_tree=null;
        Object RIGHTP226_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2505:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2505:4: ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set224=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set224)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP225=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement4428); 
            LEFTP225_tree = 
            (Object)adaptor.create(LEFTP225)
            ;
            adaptor.addChild(root_0, LEFTP225_tree);


            file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement4432); 
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
            	

            RIGHTP226=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement4436); 
            RIGHTP226_tree = 
            (Object)adaptor.create(RIGHTP226)
            ;
            adaptor.addChild(root_0, RIGHTP226_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2517:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) ;
    public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SBOL227=null;
        Token DOT228=null;
        EugeneParser.sbolImportStatement_return i =null;

        EugeneParser.sbolExportStatement_return sbolExportStatement229 =null;


        Object SBOL227_tree=null;
        Object DOT228_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2519:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2519:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            {
            root_0 = (Object)adaptor.nil();


            SBOL227=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement4458); 
            SBOL227_tree = 
            (Object)adaptor.create(SBOL227)
            ;
            adaptor.addChild(root_0, SBOL227_tree);


            DOT228=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement4460); 
            DOT228_tree = 
            (Object)adaptor.create(DOT228)
            ;
            adaptor.addChild(root_0, DOT228_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2519:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==EXPORT) ) {
                alt87=1;
            }
            else if ( (LA87_0==LC_IMPORT||LA87_0==UC_IMPORT) ) {
                alt87=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;

            }
            switch (alt87) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2519:14: sbolExportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement4463);
                    sbolExportStatement229=sbolExportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sbolExportStatement229.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2519:43: i= sbolImportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement4470);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2526:1: sbolExportStatement[boolean defer] : EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
    public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token filenameToken=null;
        Token EXPORT230=null;
        Token LEFTP231=null;
        Token COMMA232=null;
        Token RIGHTP233=null;

        Object idToken_tree=null;
        Object filenameToken_tree=null;
        Object EXPORT230_tree=null;
        Object LEFTP231_tree=null;
        Object COMMA232_tree=null;
        Object RIGHTP233_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2527:2: ( EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2527:4: EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            EXPORT230=(Token)match(input,EXPORT,FOLLOW_EXPORT_in_sbolExportStatement4487); 
            EXPORT230_tree = 
            (Object)adaptor.create(EXPORT230)
            ;
            adaptor.addChild(root_0, EXPORT230_tree);


            LEFTP231=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement4489); 
            LEFTP231_tree = 
            (Object)adaptor.create(LEFTP231)
            ;
            adaptor.addChild(root_0, LEFTP231_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement4493); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            COMMA232=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement4495); 
            COMMA232_tree = 
            (Object)adaptor.create(COMMA232)
            ;
            adaptor.addChild(root_0, COMMA232_tree);


            filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement4499); 
            filenameToken_tree = 
            (Object)adaptor.create(filenameToken)
            ;
            adaptor.addChild(root_0, filenameToken_tree);


            RIGHTP233=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement4501); 
            RIGHTP233_tree = 
            (Object)adaptor.create(RIGHTP233)
            ;
            adaptor.addChild(root_0, RIGHTP233_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2540:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP ;
    public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token fileToken=null;
        Token set234=null;
        Token LEFTP235=null;
        Token RIGHTP236=null;

        Object fileToken_tree=null;
        Object set234_tree=null;
        Object LEFTP235_tree=null;
        Object RIGHTP236_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2542:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2542:4: ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set234=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set234)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP235=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement4530); 
            LEFTP235_tree = 
            (Object)adaptor.create(LEFTP235)
            ;
            adaptor.addChild(root_0, LEFTP235_tree);


            fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement4534); 
            fileToken_tree = 
            (Object)adaptor.create(fileToken)
            ;
            adaptor.addChild(root_0, fileToken_tree);


            RIGHTP236=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement4536); 
            RIGHTP236_tree = 
            (Object)adaptor.create(RIGHTP236)
            ;
            adaptor.addChild(root_0, RIGHTP236_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2587:1: pigeonStatement[boolean defer] : ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.pigeonStatement_return pigeonStatement(boolean defer) throws RecognitionException {
        EugeneParser.pigeonStatement_return retval = new EugeneParser.pigeonStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token set237=null;
        Token LEFTP238=null;
        Token RIGHTP239=null;

        Object idToken_tree=null;
        Object set237_tree=null;
        Object LEFTP238_tree=null;
        Object RIGHTP239_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2588:2: ( ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2588:4: ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set237=(Token)input.LT(1);

            if ( input.LA(1)==LC_PIGEON||input.LA(1)==PIGEON ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set237)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP238=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_pigeonStatement4565); 
            LEFTP238_tree = 
            (Object)adaptor.create(LEFTP238)
            ;
            adaptor.addChild(root_0, LEFTP238_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_pigeonStatement4569); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP239=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_pigeonStatement4571); 
            RIGHTP239_tree = 
            (Object)adaptor.create(RIGHTP239)
            ;
            adaptor.addChild(root_0, RIGHTP239_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2636:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
    public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
        EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token n=null;
        Token ASSERT240=null;
        Token LEFTP241=null;
        Token DOT242=null;
        Token SIZE243=null;
        Token LEFTP244=null;
        Token RIGHTP245=null;
        Token EQUALS246=null;
        Token EQUALS247=null;
        Token RIGHTP248=null;

        Object id_tree=null;
        Object n_tree=null;
        Object ASSERT240_tree=null;
        Object LEFTP241_tree=null;
        Object DOT242_tree=null;
        Object SIZE243_tree=null;
        Object LEFTP244_tree=null;
        Object RIGHTP245_tree=null;
        Object EQUALS246_tree=null;
        Object EQUALS247_tree=null;
        Object RIGHTP248_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2637:2: (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==SEMIC) ) {
                alt88=1;
            }
            else if ( (LA88_0==ASSERT) ) {
                alt88=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;

            }
            switch (alt88) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2637:5: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/eugene-v2.0/grammar/Eugene.g:2637:7: ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    ASSERT240=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements4595); 
                    ASSERT240_tree = 
                    (Object)adaptor.create(ASSERT240)
                    ;
                    adaptor.addChild(root_0, ASSERT240_tree);


                    LEFTP241=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4597); 
                    LEFTP241_tree = 
                    (Object)adaptor.create(LEFTP241)
                    ;
                    adaptor.addChild(root_0, LEFTP241_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements4601); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT242=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements4603); 
                    DOT242_tree = 
                    (Object)adaptor.create(DOT242)
                    ;
                    adaptor.addChild(root_0, DOT242_tree);


                    SIZE243=(Token)match(input,SIZE,FOLLOW_SIZE_in_testStatements4605); 
                    SIZE243_tree = 
                    (Object)adaptor.create(SIZE243)
                    ;
                    adaptor.addChild(root_0, SIZE243_tree);


                    LEFTP244=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4607); 
                    LEFTP244_tree = 
                    (Object)adaptor.create(LEFTP244)
                    ;
                    adaptor.addChild(root_0, LEFTP244_tree);


                    RIGHTP245=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4609); 
                    RIGHTP245_tree = 
                    (Object)adaptor.create(RIGHTP245)
                    ;
                    adaptor.addChild(root_0, RIGHTP245_tree);


                    EQUALS246=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4611); 
                    EQUALS246_tree = 
                    (Object)adaptor.create(EQUALS246)
                    ;
                    adaptor.addChild(root_0, EQUALS246_tree);


                    EQUALS247=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4613); 
                    EQUALS247_tree = 
                    (Object)adaptor.create(EQUALS247)
                    ;
                    adaptor.addChild(root_0, EQUALS247_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements4617); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP248=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4619); 
                    RIGHTP248_tree = 
                    (Object)adaptor.create(RIGHTP248)
                    ;
                    adaptor.addChild(root_0, RIGHTP248_tree);



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


 

    public static final BitSet FOLLOW_statement_in_prog816 = new BitSet(new long[]{0xA00010F89A0C2740L,0x00000000F8C181E7L});
    public static final BitSet FOLLOW_EOF_in_prog821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeStatement_in_statement847 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_statement851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_statement858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_statement861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_printStatement_in_statement867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_statement870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement875 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_statement878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_statement886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_statement893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_statement896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imperativeStatements_in_statement903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predefined_statements_in_statement909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_statement912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testStatements_in_predefined_statements925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_predefined_statements931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instantiation_in_declarationStatement985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_grammarDeclaration_in_declarationStatement1003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1027 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_numdecl_in_variableDeclaration1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1042 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_txtdecl_in_variableDeclaration1046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration1057 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1061 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1076 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1080 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_variableDeclaration1095 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_booldecl_in_variableDeclaration1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1122 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1129 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1139 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_numdecl1141 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_numdecl1146 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1156 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1178 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1185 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1198 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_txtdecl1200 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_txtdecl1204 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1212 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1234 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1241 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1253 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1255 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_txtlistdecl1261 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1269 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1291 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1298 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1310 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_numlistdecl1312 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_numlistdecl1317 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1325 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1347 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_booldecl1354 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_booldecl_in_booldecl1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1366 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_booldecl1368 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_booldecl1372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1390 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration1394 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1396 = new BitSet(new long[]{0x2000000000000200L,0x0000000000400000L});
    public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1428 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1447 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_propertyType1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_typeDeclaration1483 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_typeDeclaration1488 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1491 = new BitSet(new long[]{0x0000000010000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_partTypeDeclaration1520 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_partTypeDeclaration1529 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1532 = new BitSet(new long[]{0x0000000010000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1537 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1569 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1576 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1578 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1580 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_containerDeclaration1586 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1591 = new BitSet(new long[]{0xE081800092202640L,0x0000000000F0A301L});
    public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1632 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_atom_in_list_of_declarations1639 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_list_of_declarations1647 = new BitSet(new long[]{0xE081800092202640L,0x0000000000F08301L});
    public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations1651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instantiation1679 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_instantiation1685 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_LEFTP_in_instantiation1689 = new BitSet(new long[]{0x4081800010210000L,0x0000000000302200L});
    public static final BitSet FOLLOW_listOfDotValues_in_instantiation1694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_listOfValues_in_instantiation1699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_instantiation1704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1728 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1732 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1738 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1742 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1750 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_listOfDotValues1753 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1755 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1759 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1767 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1771 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1781 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_expr_in_listOfValues1802 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_listOfValues1808 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_listOfValues1814 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration1833 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_deviceDeclaration1837 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration1840 = new BitSet(new long[]{0x0081000010000000L,0x0000000000002010L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration1845 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_in_deviceComponents1881 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_deviceComponents1887 = new BitSet(new long[]{0x0081000010000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceComponents1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_selection1915 = new BitSet(new long[]{0x0080000010000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_selection_list_in_selection1919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_selection1922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection1931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection_list1960 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_PIPE_in_selection_list1966 = new BitSet(new long[]{0x0080000010000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_selection_list_in_selection_list1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_device_component1996 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_device_component2006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_assignment_in_assignment2026 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2029 = new BitSet(new long[]{0x4081904010200020L,0x0000000040310286L});
    public static final BitSet FOLLOW_AMP_in_assignment2034 = new BitSet(new long[]{0x4081904010200000L,0x0000000040310286L});
    public static final BitSet FOLLOW_rhs_assignment_in_assignment2040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lhs_assignment2062 = new BitSet(new long[]{0x0001000000010000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lhs_access2100 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_lhs_assignment_in_lhs_access2104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2112 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_lhs_access2117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2121 = new BitSet(new long[]{0x0001000000010000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_access2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_rhs_assignment2152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_rhs_assignment2172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_listOfIDs2200 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_listOfIDs2209 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_ruleDeclaration2237 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2241 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2243 = new BitSet(new long[]{0x4091A70010000000L,0xFFFFFF9700100200L,0x001DFFFFFFFFFBFFL});
    public static final BitSet FOLLOW_set_in_ruleDeclaration2248 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2256 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_ruleDeclaration2258 = new BitSet(new long[]{0x4091A30010000000L,0xFFFFFF9300100200L,0x001DFFFFFFFFFBFFL});
    public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2266 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2664 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQUAL_in_relationalOperators2671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTHAN_in_relationalOperators2676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GTHAN_in_relationalOperators2681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEQUAL_in_relationalOperators2686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GEQUAL_in_relationalOperators2691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_predicate_in_cnf_rule2792 = new BitSet(new long[]{0x0008000100000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_set_in_cnf_rule2800 = new BitSet(new long[]{0x4091A30010000000L,0xFFFFFF9300100200L,0x001DFFFFFFFFFBFFL});
    public static final BitSet FOLLOW_cnf_rule_in_cnf_rule2810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2840 = new BitSet(new long[]{0x0020080000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_set_in_or_predicate2846 = new BitSet(new long[]{0x4091A30010000000L,0xFFFFFF9300100200L,0x001DFFFFFFFFFBFFL});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2856 = new BitSet(new long[]{0x0020080000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_set_in_negated_predicate2884 = new BitSet(new long[]{0x4081A10010000000L,0xFFFFFF9100100200L,0x001DFFFFFFFFFBFFL});
    public static final BitSet FOLLOW_predicate_in_negated_predicate2894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_negated_predicate2904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_predicate2931 = new BitSet(new long[]{0x0000210000000000L,0xFFFFFF9100000000L,0x001DFFFFFFFFFBFFL});
    public static final BitSet FOLLOW_ruleOperator_in_predicate2941 = new BitSet(new long[]{0x4001000010000002L});
    public static final BitSet FOLLOW_operand_in_predicate2950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_predicate2964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionRule_in_predicate2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand3004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_operand3013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_operand3020 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_operand3024 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_operand3026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionRule3052 = new BitSet(new long[]{0x0442000005020000L,0x2C9A000000000000L,0x000600164D000C00L});
    public static final BitSet FOLLOW_exp_op_in_expressionRule3057 = new BitSet(new long[]{0x4080800010000000L,0x0000000000100200L});
    public static final BitSet FOLLOW_expression_in_expressionRule3062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_operand_in_expression3086 = new BitSet(new long[]{0x0280000000008002L,0x0000000000000010L});
    public static final BitSet FOLLOW_exp_operator_in_expression3095 = new BitSet(new long[]{0x4080800010000000L,0x0000000000100200L});
    public static final BitSet FOLLOW_expression_in_expression3100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_expression3112 = new BitSet(new long[]{0x4080800010000000L,0x0000000000100200L});
    public static final BitSet FOLLOW_expression_in_expression3114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_expression3117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp_operator3136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operator3144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_exp_operator3151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_exp_operator3158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_exp_operand3188 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_DOT_in_exp_operand3190 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_exp_operand3199 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3205 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3211 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3230 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3250 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp_operand3263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOperators_in_exp_op3290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GRAMMAR_in_grammarDeclaration3309 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_grammarDeclaration3313 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_grammarDeclaration3315 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_list_of_production_rules_in_grammarDeclaration3317 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_grammarDeclaration3320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_production_rule_in_list_of_production_rules3332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_list_of_production_rules3335 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_list_of_production_rules_in_list_of_production_rules3338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_production_rule3358 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ARROW_in_production_rule3362 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_right_hand_side_in_production_rule3364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_right_hand_side3380 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_right_hand_side3385 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_right_hand_side_in_right_hand_side3387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_right_hand_side3395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3428 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_interactionDeclaration3432 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3434 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3464 = new BitSet(new long[]{0x0000210000000000L,0x0000001100000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3468 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_interaction3473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3482 = new BitSet(new long[]{0x0000210000000000L,0x0000001100000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3486 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interaction3489 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_interaction_in_interaction3493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_interaction3496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRODUCT_in_functionCall3563 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_PERMUTE_in_functionCall3567 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_functionCall3570 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_functionCall3574 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_functionCall3576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINTLN_in_printStatement3594 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3596 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_printStatement3610 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3612 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_toPrint3640 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_toPrint_prime_in_toPrint3645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_toPrint_prime3671 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_toPrint_in_toPrint_prime3675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_imperativeStatements3695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements3701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_imperativeStatements3707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ifStatement3733 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3739 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3743 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3746 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3748 = new BitSet(new long[]{0xA00010F89A0C2740L,0x00000000F8C181E7L});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3756 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3759 = new BitSet(new long[]{0x0000000600000002L,0x0000000006000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3774 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3780 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3784 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3787 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3789 = new BitSet(new long[]{0xA00010F89A0C2740L,0x00000000F8C181E7L});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3797 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3800 = new BitSet(new long[]{0x0000000600000002L,0x0000000006000000L});
    public static final BitSet FOLLOW_set_in_ifStatement3816 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3822 = new BitSet(new long[]{0xA00010F89A0C2740L,0x00000000F8C181E7L});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_imp_condition3857 = new BitSet(new long[]{0x0442000005020000L,0x2C9A000000000000L,0x000600164D000C00L});
    public static final BitSet FOLLOW_relationalOperators_in_imp_condition3862 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_atom_in_imp_condition3866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_forall_iterator3882 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator3891 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_forall_iterator3893 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator3899 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator3901 = new BitSet(new long[]{0xA00010F89A0C2740L,0x00000000F8C181E7L});
    public static final BitSet FOLLOW_listOfStatements_in_forall_iterator3906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator3913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_for_loop3925 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_for_loop3931 = new BitSet(new long[]{0x2000000000000200L,0x0000000000400000L});
    public static final BitSet FOLLOW_variableDeclaration_in_for_loop3935 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop3938 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_imp_condition_in_for_loop3942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop3945 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_assignment_in_for_loop3949 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_for_loop3952 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_for_loop3954 = new BitSet(new long[]{0xA00010F89A0C2740L,0x00000000F8C181E7L});
    public static final BitSet FOLLOW_listOfStatements_in_for_loop3962 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_RIGHTCUR_in_for_loop3969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_listOfStatements3988 = new BitSet(new long[]{0xA00010F89A0C2742L,0x00000000F8C181E7L});
    public static final BitSet FOLLOW_multExpr_in_expr4016 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_PLUS_in_expr4025 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_multExpr_in_expr4029 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_MINUS_in_expr4039 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_multExpr_in_expr4043 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_atom_in_multExpr4069 = new BitSet(new long[]{0x0200000000008002L});
    public static final BitSet FOLLOW_MULT_in_multExpr4080 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_DIV_in_multExpr4084 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_atom_in_multExpr4089 = new BitSet(new long[]{0x0200000000008002L});
    public static final BitSet FOLLOW_NUMBER_in_atom4116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom4132 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_NUMBER_in_atom4137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom4143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_atom4156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_atom4162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom4172 = new BitSet(new long[]{0x0001000000010000L});
    public static final BitSet FOLLOW_object_access_in_atom4180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_atom4198 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_atom4200 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_atom4203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_atom4213 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_list_in_atom4215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_atom4218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_list4244 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_list4251 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_list4255 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_DOT_in_object_access4295 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_object_access4299 = new BitSet(new long[]{0x0001000000010000L});
    public static final BitSet FOLLOW_LEFTSBR_in_object_access4307 = new BitSet(new long[]{0x4081800010200000L,0x0000000000300200L});
    public static final BitSet FOLLOW_expr_in_object_access4312 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_object_access4316 = new BitSet(new long[]{0x0001000000010000L});
    public static final BitSet FOLLOW_object_access_in_object_access4323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolStatement_in_dataExchange4349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pigeonStatement_in_dataExchange4359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importStatement_in_dataExchange4367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASHMARK_in_includeStatement4389 = new BitSet(new long[]{0x0000008000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_set_in_includeStatement4393 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_includeStatement4401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_importStatement4422 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_importStatement4428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_importStatement4432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_importStatement4436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBOL_in_sbolStatement4458 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_DOT_in_sbolStatement4460 = new BitSet(new long[]{0x0000004000100000L,0x0000000040000000L});
    public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement4463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement4470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPORT_in_sbolExportStatement4487 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement4489 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_sbolExportStatement4493 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_sbolExportStatement4495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_sbolExportStatement4499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement4501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolImportStatement4524 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement4530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_STRING_in_sbolImportStatement4534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement4536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_pigeonStatement4559 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_pigeonStatement4565 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_pigeonStatement4569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_pigeonStatement4571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_testStatements4595 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4597 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_testStatements4601 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_DOT_in_testStatements4603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SIZE_in_testStatements4605 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4607 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4609 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4611 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4613 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements4617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4619 = new BitSet(new long[]{0x0000000000000002L});

}