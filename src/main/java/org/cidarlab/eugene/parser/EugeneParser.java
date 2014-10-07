// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g 2014-09-10 15:39:01

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

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.constants.Orientation;
//import org.cidarlab.eugene.data.genbank.*;
//import org.cidarlab.eugene.data.registry.*;
import org.cidarlab.eugene.data.sbol.*;

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
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADDPROPS", "AMP", "ARRAY", "ASSERT", "BOOLEAN", "COLLECTION", "COLON", "COMMA", "DEVICE", "DIGIT", "DIV", "DOT", "EQUALS", "EXPORT", "FALSE", "FLEXIBLE", "GENBANK", "GEQUAL", "GTHAN", "HASHMARK", "ID", "IMAGE", "INT", "INTERACTION", "LC_AND", "LC_ELSE", "LC_ELSEIF", "LC_FOR", "LC_FORALL", "LC_IF", "LC_IMPORT", "LC_INCLUDE", "LC_INDUCES", "LC_NOT", "LC_ON", "LC_OR", "LC_PIGEON", "LC_REPRESSES", "LEFTCUR", "LEFTP", "LEFTSBR", "LEQUAL", "LINE_COMMENT", "LOG_AND", "LOG_NOT", "LOG_OR", "LTHAN", "MINUS", "ML_COMMENT", "MULT", "NEQUAL", "NEWLINE", "NOTE", "NUM", "NUMBER", "PART", "PART_TYPE", "PERMUTE", "PIGEON", "PIPE", "PLUS", "PRINT", "PRINTLN", "PRODUCT", "PROPERTY", "REAL", "REF", "REGISTRY", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "RULE", "SBOL", "SEMIC", "SIZE", "STRICT", "STRING", "TRUE", "TXT", "TYPE", "UC_AND", "UC_ELSE", "UC_ELSEIF", "UC_FOR", "UC_FORALL", "UC_IF", "UC_IMPORT", "UC_INCLUDE", "UC_INDUCES", "UC_NOT", "UC_ON", "UC_OR", "UC_REPRESSES", "UNDERS", "WS", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", "'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'MATCHES'", "'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", "'SOUNDSLIKE'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'reverse'", "'same_count'", "'same_orientation'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", "'soundslike'", "'startswith'", "'then'", "'with'"
    };

    public static final int EOF=-1;
    public static final int T__99=99;
    public static final int T__100=100;
    public static final int T__101=101;
    public static final int T__102=102;
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
    public static final int ADDPROPS=4;
    public static final int AMP=5;
    public static final int ARRAY=6;
    public static final int ASSERT=7;
    public static final int BOOLEAN=8;
    public static final int COLLECTION=9;
    public static final int COLON=10;
    public static final int COMMA=11;
    public static final int DEVICE=12;
    public static final int DIGIT=13;
    public static final int DIV=14;
    public static final int DOT=15;
    public static final int EQUALS=16;
    public static final int EXPORT=17;
    public static final int FALSE=18;
    public static final int FLEXIBLE=19;
    public static final int GENBANK=20;
    public static final int GEQUAL=21;
    public static final int GTHAN=22;
    public static final int HASHMARK=23;
    public static final int ID=24;
    public static final int IMAGE=25;
    public static final int INT=26;
    public static final int INTERACTION=27;
    public static final int LC_AND=28;
    public static final int LC_ELSE=29;
    public static final int LC_ELSEIF=30;
    public static final int LC_FOR=31;
    public static final int LC_FORALL=32;
    public static final int LC_IF=33;
    public static final int LC_IMPORT=34;
    public static final int LC_INCLUDE=35;
    public static final int LC_INDUCES=36;
    public static final int LC_NOT=37;
    public static final int LC_ON=38;
    public static final int LC_OR=39;
    public static final int LC_PIGEON=40;
    public static final int LC_REPRESSES=41;
    public static final int LEFTCUR=42;
    public static final int LEFTP=43;
    public static final int LEFTSBR=44;
    public static final int LEQUAL=45;
    public static final int LINE_COMMENT=46;
    public static final int LOG_AND=47;
    public static final int LOG_NOT=48;
    public static final int LOG_OR=49;
    public static final int LTHAN=50;
    public static final int MINUS=51;
    public static final int ML_COMMENT=52;
    public static final int MULT=53;
    public static final int NEQUAL=54;
    public static final int NEWLINE=55;
    public static final int NOTE=56;
    public static final int NUM=57;
    public static final int NUMBER=58;
    public static final int PART=59;
    public static final int PART_TYPE=60;
    public static final int PERMUTE=61;
    public static final int PIGEON=62;
    public static final int PIPE=63;
    public static final int PLUS=64;
    public static final int PRINT=65;
    public static final int PRINTLN=66;
    public static final int PRODUCT=67;
    public static final int PROPERTY=68;
    public static final int REAL=69;
    public static final int REF=70;
    public static final int REGISTRY=71;
    public static final int RIGHTCUR=72;
    public static final int RIGHTP=73;
    public static final int RIGHTSBR=74;
    public static final int RULE=75;
    public static final int SBOL=76;
    public static final int SEMIC=77;
    public static final int SIZE=78;
    public static final int STRICT=79;
    public static final int STRING=80;
    public static final int TRUE=81;
    public static final int TXT=82;
    public static final int TYPE=83;
    public static final int UC_AND=84;
    public static final int UC_ELSE=85;
    public static final int UC_ELSEIF=86;
    public static final int UC_FOR=87;
    public static final int UC_FORALL=88;
    public static final int UC_IF=89;
    public static final int UC_IMPORT=90;
    public static final int UC_INCLUDE=91;
    public static final int UC_INDUCES=92;
    public static final int UC_NOT=93;
    public static final int UC_ON=94;
    public static final int UC_OR=95;
    public static final int UC_REPRESSES=96;
    public static final int UNDERS=97;
    public static final int WS=98;

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
    public String getGrammarFileName() { return "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g"; }


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
        
    	// interpreter
    	this.interp = interp;
        
    	// writer
    	this.writer = writer;
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
    	
    	//does multiplication or division on a primitive, used by grammar rule multExpr
    	public void doMultDivOp(Variable source, Variable destination, String op) {
    		if (source.type.equals(EugeneConstants.NUM)) {
    			if (op.equals("*")) {
    				destination.num *= source.num;
    			} else {
    				if (source.num != 0) {
    					destination.num /= source.num;
    				} else {
    					printError("Division by zero.");
    				}
    			}
    		}
    	}

    	//does addition or subtraction on a primitive, used by grammar rule expr
    	public void doMinPlusOp(Variable source, Variable destination, String op) {
    	
    		if (op.equals("+")) {
    			if (source.type.equals(EugeneConstants.NUM)) {
    				destination.num += source.num;
    				destination.type = EugeneConstants.NUM;
    			} else if (source.type.equals(EugeneConstants.NUMLIST)) {
    				destination.numList.addAll(source.numList);
    				destination.type = EugeneConstants.NUMLIST;
    			} else if (source.type.equals(EugeneConstants.TXTLIST)) {
    				destination.txtList.addAll(source.txtList);
    				destination.type = EugeneConstants.TXTLIST;
    			} else if (source.type.equals(EugeneConstants.TXT)) {
    				destination.txt += source.txt;
    				destination.type = EugeneConstants.TXT;
    			}
    		} else if (op.equals("-")) {
    			if (source.type.equals(EugeneConstants.NUM)) {
    				destination.num -= source.num;
    				destination.type = EugeneConstants.NUM;
    			}
    		}
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:735:1: prog : ( statement[false] )+ EOF !;
    public final EugeneParser.prog_return prog() throws RecognitionException {
        EugeneParser.prog_return retval = new EugeneParser.prog_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EOF2=null;
        EugeneParser.statement_return statement1 =null;


        Object EOF2_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:741:2: ( ( statement[false] )+ EOF !)
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:741:4: ( statement[false] )+ EOF !
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:741:4: ( statement[false] )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= ARRAY && LA1_0 <= COLLECTION)||LA1_0==DEVICE||LA1_0==GENBANK||(LA1_0 >= HASHMARK && LA1_0 <= ID)||LA1_0==INTERACTION||(LA1_0 >= LC_FOR && LA1_0 <= LC_INCLUDE)||LA1_0==LC_PIGEON||LA1_0==NUM||(LA1_0 >= PART && LA1_0 <= PIGEON)||(LA1_0 >= PRINT && LA1_0 <= PROPERTY)||LA1_0==REGISTRY||(LA1_0 >= RULE && LA1_0 <= SEMIC)||(LA1_0 >= TXT && LA1_0 <= TYPE)||(LA1_0 >= UC_FOR && LA1_0 <= UC_INCLUDE)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:741:5: statement[false]
            	    {
            	    pushFollow(FOLLOW_statement_in_prog783);
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


            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_prog788); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:745:1: statement[boolean defer] returns [NamedElement objReturnValue] : ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | testStatements[defer] SEMIC | imperativeStatements[defer] );
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
        Token SEMIC15=null;
        EugeneParser.dataExchange_return de =null;

        EugeneParser.includeStatement_return includeStatement3 =null;

        EugeneParser.declarationStatement_return declarationStatement5 =null;

        EugeneParser.printStatement_return printStatement7 =null;

        EugeneParser.assignment_return assignment9 =null;

        EugeneParser.functionCall_return functionCall11 =null;

        EugeneParser.testStatements_return testStatements14 =null;

        EugeneParser.imperativeStatements_return imperativeStatements16 =null;


        Object SEMIC4_tree=null;
        Object SEMIC6_tree=null;
        Object SEMIC8_tree=null;
        Object SEMIC10_tree=null;
        Object SEMIC12_tree=null;
        Object SEMIC13_tree=null;
        Object SEMIC15_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:747:2: ( includeStatement[defer] ( SEMIC )? | declarationStatement[defer] SEMIC | printStatement[defer] SEMIC | assignment[defer] SEMIC | functionCall[defer] SEMIC |de= dataExchange[defer] SEMIC | testStatements[defer] SEMIC | imperativeStatements[defer] )
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
            case GENBANK:
            case LC_IMPORT:
            case LC_PIGEON:
            case PIGEON:
            case REGISTRY:
            case SBOL:
            case UC_IMPORT:
                {
                alt3=6;
                }
                break;
            case ASSERT:
            case SEMIC:
                {
                alt3=7;
                }
                break;
            case LC_FOR:
            case LC_FORALL:
            case LC_IF:
            case UC_FOR:
            case UC_FORALL:
            case UC_IF:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:748:3: includeStatement[defer] ( SEMIC )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_includeStatement_in_statement814);
                    includeStatement3=includeStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, includeStatement3.getTree());

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:748:27: ( SEMIC )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==SEMIC) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:748:28: SEMIC
                            {
                            SEMIC4=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement818); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:749:4: declarationStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_declarationStatement_in_statement825);
                    declarationStatement5=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, declarationStatement5.getTree());

                    SEMIC6=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement828); 
                    SEMIC6_tree = 
                    (Object)adaptor.create(SEMIC6)
                    ;
                    adaptor.addChild(root_0, SEMIC6_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:750:4: printStatement[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_printStatement_in_statement834);
                    printStatement7=printStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, printStatement7.getTree());

                    SEMIC8=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement837); 
                    SEMIC8_tree = 
                    (Object)adaptor.create(SEMIC8)
                    ;
                    adaptor.addChild(root_0, SEMIC8_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:751:4: assignment[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assignment_in_statement842);
                    assignment9=assignment(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, assignment9.getTree());

                    SEMIC10=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement845); 
                    SEMIC10_tree = 
                    (Object)adaptor.create(SEMIC10)
                    ;
                    adaptor.addChild(root_0, SEMIC10_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:752:4: functionCall[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_statement850);
                    functionCall11=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, functionCall11.getTree());

                    SEMIC12=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement853); 
                    SEMIC12_tree = 
                    (Object)adaptor.create(SEMIC12)
                    ;
                    adaptor.addChild(root_0, SEMIC12_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:753:4: de= dataExchange[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_statement860);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());

                    SEMIC13=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement863); 
                    SEMIC13_tree = 
                    (Object)adaptor.create(SEMIC13)
                    ;
                    adaptor.addChild(root_0, SEMIC13_tree);



                    if(!defer) {
                        try {
                            this.interp.putImportedData((de!=null?de.e:null));
                        } catch(EugeneException ee) {
                            printError(ee.toString());
                        }
                    }	
                    	

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:763:4: testStatements[defer] SEMIC
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_testStatements_in_statement870);
                    testStatements14=testStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, testStatements14.getTree());

                    SEMIC15=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_statement873); 
                    SEMIC15_tree = 
                    (Object)adaptor.create(SEMIC15)
                    ;
                    adaptor.addChild(root_0, SEMIC15_tree);


                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:764:4: imperativeStatements[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_imperativeStatements_in_statement878);
                    imperativeStatements16=imperativeStatements(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, imperativeStatements16.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:768:1: declarationStatement[boolean defer] returns [String name] : (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | deviceDeclaration[defer] );
    public final EugeneParser.declarationStatement_return declarationStatement(boolean defer) throws RecognitionException {
        EugeneParser.declarationStatement_return retval = new EugeneParser.declarationStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.variableDeclaration_return v =null;

        EugeneParser.containerDeclaration_return containerDeclaration17 =null;

        EugeneParser.propertyDeclaration_return propertyDeclaration18 =null;

        EugeneParser.typeDeclaration_return typeDeclaration19 =null;

        EugeneParser.instantiation_return instantiation20 =null;

        EugeneParser.interactionDeclaration_return interactionDeclaration21 =null;

        EugeneParser.ruleDeclaration_return ruleDeclaration22 =null;

        EugeneParser.deviceDeclaration_return deviceDeclaration23 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:770:2: (v= variableDeclaration[defer] | containerDeclaration[defer] | propertyDeclaration[defer] | typeDeclaration[defer] | instantiation[defer] | interactionDeclaration[defer] | ruleDeclaration[defer] | deviceDeclaration[defer] )
            int alt4=8;
            switch ( input.LA(1) ) {
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

                if ( (LA4_5==ID) ) {
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
            case DEVICE:
                {
                alt4=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:770:4: v= variableDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_variableDeclaration_in_declarationStatement899);
                    v=variableDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, v.getTree());


                    if(!defer) {
                        retval.name = (v!=null?v.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:775:4: containerDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_containerDeclaration_in_declarationStatement907);
                    containerDeclaration17=containerDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, containerDeclaration17.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:776:4: propertyDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_propertyDeclaration_in_declarationStatement913);
                    propertyDeclaration18=propertyDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, propertyDeclaration18.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:777:4: typeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_typeDeclaration_in_declarationStatement919);
                    typeDeclaration19=typeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, typeDeclaration19.getTree());

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:778:4: instantiation[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_instantiation_in_declarationStatement925);
                    instantiation20=instantiation(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, instantiation20.getTree());

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:779:4: interactionDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interactionDeclaration_in_declarationStatement931);
                    interactionDeclaration21=interactionDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, interactionDeclaration21.getTree());

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:780:4: ruleDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ruleDeclaration_in_declarationStatement937);
                    ruleDeclaration22=ruleDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ruleDeclaration22.getTree());

                    }
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:781:4: deviceDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_deviceDeclaration_in_declarationStatement943);
                    deviceDeclaration23=deviceDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, deviceDeclaration23.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:784:1: variableDeclaration[boolean defer] returns [String varname] : ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | BOOLEAN b= booldecl[defer] );
    public final EugeneParser.variableDeclaration_return variableDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.variableDeclaration_return retval = new EugeneParser.variableDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NUM24=null;
        Token TXT25=null;
        Token TXT26=null;
        Token LEFTSBR27=null;
        Token RIGHTSBR28=null;
        Token NUM29=null;
        Token LEFTSBR30=null;
        Token RIGHTSBR31=null;
        Token BOOLEAN32=null;
        EugeneParser.numdecl_return n =null;

        EugeneParser.txtdecl_return t =null;

        EugeneParser.txtlistdecl_return tl =null;

        EugeneParser.numlistdecl_return nl =null;

        EugeneParser.booldecl_return b =null;


        Object NUM24_tree=null;
        Object TXT25_tree=null;
        Object TXT26_tree=null;
        Object LEFTSBR27_tree=null;
        Object RIGHTSBR28_tree=null;
        Object NUM29_tree=null;
        Object LEFTSBR30_tree=null;
        Object RIGHTSBR31_tree=null;
        Object BOOLEAN32_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:786:2: ( NUM n= numdecl[defer] | TXT t= txtdecl[defer] | TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer] | NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer] | BOOLEAN b= booldecl[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:786:4: NUM n= numdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM24=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration961); 
                    NUM24_tree = 
                    (Object)adaptor.create(NUM24)
                    ;
                    adaptor.addChild(root_0, NUM24_tree);


                    pushFollow(FOLLOW_numdecl_in_variableDeclaration965);
                    n=numdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, n.getTree());


                    if(!defer) {
                        retval.varname = (n!=null?n.varname:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:791:4: TXT t= txtdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT25=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration976); 
                    TXT25_tree = 
                    (Object)adaptor.create(TXT25)
                    ;
                    adaptor.addChild(root_0, TXT25_tree);


                    pushFollow(FOLLOW_txtdecl_in_variableDeclaration980);
                    t=txtdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t.getTree());


                    if(!defer) {
                        retval.varname = (t!=null?t.varname:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:796:4: TXT LEFTSBR RIGHTSBR tl= txtlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT26=(Token)match(input,TXT,FOLLOW_TXT_in_variableDeclaration991); 
                    TXT26_tree = 
                    (Object)adaptor.create(TXT26)
                    ;
                    adaptor.addChild(root_0, TXT26_tree);


                    LEFTSBR27=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration993); 
                    LEFTSBR27_tree = 
                    (Object)adaptor.create(LEFTSBR27)
                    ;
                    adaptor.addChild(root_0, LEFTSBR27_tree);


                    RIGHTSBR28=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration995); 
                    RIGHTSBR28_tree = 
                    (Object)adaptor.create(RIGHTSBR28)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR28_tree);


                    pushFollow(FOLLOW_txtlistdecl_in_variableDeclaration999);
                    tl=txtlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tl.getTree());


                    if(!defer) {
                        retval.varname = (tl!=null?tl.varname:null);
                    }	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:801:4: NUM LEFTSBR RIGHTSBR nl= numlistdecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM29=(Token)match(input,NUM,FOLLOW_NUM_in_variableDeclaration1010); 
                    NUM29_tree = 
                    (Object)adaptor.create(NUM29)
                    ;
                    adaptor.addChild(root_0, NUM29_tree);


                    LEFTSBR30=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_variableDeclaration1012); 
                    LEFTSBR30_tree = 
                    (Object)adaptor.create(LEFTSBR30)
                    ;
                    adaptor.addChild(root_0, LEFTSBR30_tree);


                    RIGHTSBR31=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_variableDeclaration1014); 
                    RIGHTSBR31_tree = 
                    (Object)adaptor.create(RIGHTSBR31)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR31_tree);


                    pushFollow(FOLLOW_numlistdecl_in_variableDeclaration1018);
                    nl=numlistdecl(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, nl.getTree());


                    if(!defer) {
                        retval.varname = (nl!=null?nl.varname:null);
                    }	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:806:4: BOOLEAN b= booldecl[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    BOOLEAN32=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_variableDeclaration1029); 
                    BOOLEAN32_tree = 
                    (Object)adaptor.create(BOOLEAN32)
                    ;
                    adaptor.addChild(root_0, BOOLEAN32_tree);


                    pushFollow(FOLLOW_booldecl_in_variableDeclaration1033);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:813:1: numdecl[boolean defer] returns [String varname] : ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? );
    public final EugeneParser.numdecl_return numdecl(boolean defer) throws RecognitionException {
        EugeneParser.numdecl_return retval = new EugeneParser.numdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token ID33=null;
        Token COMMA34=null;
        Token ID36=null;
        Token EQUALS37=null;
        Token COMMA38=null;
        EugeneParser.expr_return ex =null;

        EugeneParser.numdecl_return numdecl35 =null;

        EugeneParser.numdecl_return numdecl39 =null;


        Object ID33_tree=null;
        Object COMMA34_tree=null;
        Object ID36_tree=null;
        Object EQUALS37_tree=null;
        Object COMMA38_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:815:2: ( ID ( COMMA numdecl[defer] )? | ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:815:4: ID ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID33=(Token)match(input,ID,FOLLOW_ID_in_numdecl1056); 
                    ID33_tree = 
                    (Object)adaptor.create(ID33)
                    ;
                    adaptor.addChild(root_0, ID33_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID33!=null?ID33.getText():null), EugeneConstants.NUM);
                    			retval.varname = (ID33!=null?ID33.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:821:5: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:821:6: COMMA numdecl[defer]
                            {
                            COMMA34=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1063); 
                            COMMA34_tree = 
                            (Object)adaptor.create(COMMA34)
                            ;
                            adaptor.addChild(root_0, COMMA34_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1065);
                            numdecl35=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl35.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:822:4: ID EQUALS (ex= expr[defer] ) ( COMMA numdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID36=(Token)match(input,ID,FOLLOW_ID_in_numdecl1073); 
                    ID36_tree = 
                    (Object)adaptor.create(ID36)
                    ;
                    adaptor.addChild(root_0, ID36_tree);


                    EQUALS37=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numdecl1075); 
                    EQUALS37_tree = 
                    (Object)adaptor.create(EQUALS37)
                    ;
                    adaptor.addChild(root_0, EQUALS37_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:822:14: (ex= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:822:15: ex= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_numdecl1080);
                    ex=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ex.getTree());

                    }



                    		if(!defer) {
                    			declareVariableWithValueNum((ID36!=null?ID36.getText():null), (ex!=null?ex.p:null), (ex!=null?ex.index:0));
                    			retval.varname = (ID36!=null?ID36.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:828:6: ( COMMA numdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:828:7: COMMA numdecl[defer]
                            {
                            COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_numdecl1090); 
                            COMMA38_tree = 
                            (Object)adaptor.create(COMMA38)
                            ;
                            adaptor.addChild(root_0, COMMA38_tree);


                            pushFollow(FOLLOW_numdecl_in_numdecl1092);
                            numdecl39=numdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numdecl39.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:831:1: txtdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? );
    public final EugeneParser.txtdecl_return txtdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtdecl_return retval = new EugeneParser.txtdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID40=null;
        Token COMMA41=null;
        Token EQUALS43=null;
        Token COMMA44=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtdecl_return txtdecl42 =null;

        EugeneParser.txtdecl_return txtdecl45 =null;


        Object var_tree=null;
        Object ID40_tree=null;
        Object COMMA41_tree=null;
        Object EQUALS43_tree=null;
        Object COMMA44_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:833:2: ( ID ( COMMA txtdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:833:4: ID ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID40=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1112); 
                    ID40_tree = 
                    (Object)adaptor.create(ID40)
                    ;
                    adaptor.addChild(root_0, ID40_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID40!=null?ID40.getText():null), EugeneConstants.TXT);
                    			retval.varname = (ID40!=null?ID40.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:839:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:839:6: COMMA txtdecl[defer]
                            {
                            COMMA41=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1119); 
                            COMMA41_tree = 
                            (Object)adaptor.create(COMMA41)
                            ;
                            adaptor.addChild(root_0, COMMA41_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1121);
                            txtdecl42=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl42.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:841:4: var= ID EQUALS let= expr[defer] ( COMMA txtdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtdecl1132); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS43=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtdecl1134); 
                    EQUALS43_tree = 
                    (Object)adaptor.create(EQUALS43)
                    ;
                    adaptor.addChild(root_0, EQUALS43_tree);


                    pushFollow(FOLLOW_expr_in_txtdecl1138);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxt((var!=null?var.getText():null), (let!=null?let.p:null), (let!=null?let.index:0));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:847:5: ( COMMA txtdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:847:6: COMMA txtdecl[defer]
                            {
                            COMMA44=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtdecl1146); 
                            COMMA44_tree = 
                            (Object)adaptor.create(COMMA44)
                            ;
                            adaptor.addChild(root_0, COMMA44_tree);


                            pushFollow(FOLLOW_txtdecl_in_txtdecl1148);
                            txtdecl45=txtdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtdecl45.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:850:1: txtlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? );
    public final EugeneParser.txtlistdecl_return txtlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.txtlistdecl_return retval = new EugeneParser.txtlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID46=null;
        Token COMMA47=null;
        Token EQUALS49=null;
        Token COMMA50=null;
        EugeneParser.expr_return let =null;

        EugeneParser.txtlistdecl_return txtlistdecl48 =null;

        EugeneParser.txtlistdecl_return txtlistdecl51 =null;


        Object var_tree=null;
        Object ID46_tree=null;
        Object COMMA47_tree=null;
        Object EQUALS49_tree=null;
        Object COMMA50_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:852:2: ( ID ( COMMA txtlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:852:4: ID ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID46=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1168); 
                    ID46_tree = 
                    (Object)adaptor.create(ID46)
                    ;
                    adaptor.addChild(root_0, ID46_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID46!=null?ID46.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID46!=null?ID46.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:858:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:858:6: COMMA txtlistdecl[defer]
                            {
                            COMMA47=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1175); 
                            COMMA47_tree = 
                            (Object)adaptor.create(COMMA47)
                            ;
                            adaptor.addChild(root_0, COMMA47_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1177);
                            txtlistdecl48=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl48.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:859:4: var= ID EQUALS let= expr[defer] ( COMMA txtlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_txtlistdecl1187); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS49=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_txtlistdecl1189); 
                    EQUALS49_tree = 
                    (Object)adaptor.create(EQUALS49)
                    ;
                    adaptor.addChild(root_0, EQUALS49_tree);


                    typeList = EugeneConstants.TXT;

                    pushFollow(FOLLOW_expr_in_txtlistdecl1195);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueTxtList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:865:5: ( COMMA txtlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:865:6: COMMA txtlistdecl[defer]
                            {
                            COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_txtlistdecl1203); 
                            COMMA50_tree = 
                            (Object)adaptor.create(COMMA50)
                            ;
                            adaptor.addChild(root_0, COMMA50_tree);


                            pushFollow(FOLLOW_txtlistdecl_in_txtlistdecl1205);
                            txtlistdecl51=txtlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, txtlistdecl51.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:868:1: numlistdecl[boolean defer] returns [String varname] : ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? );
    public final EugeneParser.numlistdecl_return numlistdecl(boolean defer) throws RecognitionException {
        EugeneParser.numlistdecl_return retval = new EugeneParser.numlistdecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID52=null;
        Token COMMA53=null;
        Token EQUALS55=null;
        Token COMMA56=null;
        EugeneParser.expr_return let =null;

        EugeneParser.numlistdecl_return numlistdecl54 =null;

        EugeneParser.numlistdecl_return numlistdecl57 =null;


        Object var_tree=null;
        Object ID52_tree=null;
        Object COMMA53_tree=null;
        Object EQUALS55_tree=null;
        Object COMMA56_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:870:2: ( ID ( COMMA numlistdecl[defer] )? |var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:870:4: ID ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID52=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1225); 
                    ID52_tree = 
                    (Object)adaptor.create(ID52)
                    ;
                    adaptor.addChild(root_0, ID52_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID52!=null?ID52.getText():null), EugeneConstants.TXTLIST);
                    			retval.varname = (ID52!=null?ID52.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:876:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:876:6: COMMA numlistdecl[defer]
                            {
                            COMMA53=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1232); 
                            COMMA53_tree = 
                            (Object)adaptor.create(COMMA53)
                            ;
                            adaptor.addChild(root_0, COMMA53_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1234);
                            numlistdecl54=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl54.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:877:4: var= ID EQUALS let= expr[defer] ( COMMA numlistdecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_numlistdecl1244); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS55=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_numlistdecl1246); 
                    EQUALS55_tree = 
                    (Object)adaptor.create(EQUALS55)
                    ;
                    adaptor.addChild(root_0, EQUALS55_tree);


                     typeList = EugeneConstants.NUM;

                    pushFollow(FOLLOW_expr_in_numlistdecl1251);
                    let=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, let.getTree());


                    		if(!defer) {
                    			declareVariableWithValueNumList((var!=null?var.getText():null), (let!=null?let.p:null));
                    			retval.varname = (var!=null?var.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:883:5: ( COMMA numlistdecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:883:6: COMMA numlistdecl[defer]
                            {
                            COMMA56=(Token)match(input,COMMA,FOLLOW_COMMA_in_numlistdecl1259); 
                            COMMA56_tree = 
                            (Object)adaptor.create(COMMA56)
                            ;
                            adaptor.addChild(root_0, COMMA56_tree);


                            pushFollow(FOLLOW_numlistdecl_in_numlistdecl1261);
                            numlistdecl57=numlistdecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, numlistdecl57.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:886:1: booldecl[boolean defer] returns [String varname] : ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] );
    public final EugeneParser.booldecl_return booldecl(boolean defer) throws RecognitionException {
        EugeneParser.booldecl_return retval = new EugeneParser.booldecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token var=null;
        Token ID58=null;
        Token COMMA59=null;
        Token EQUALS61=null;
        EugeneParser.expr_return let =null;

        EugeneParser.booldecl_return booldecl60 =null;


        Object var_tree=null;
        Object ID58_tree=null;
        Object COMMA59_tree=null;
        Object EQUALS61_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:888:2: ( ID ( COMMA booldecl[defer] )? |var= ID EQUALS let= expr[defer] )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:888:4: ID ( COMMA booldecl[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    ID58=(Token)match(input,ID,FOLLOW_ID_in_booldecl1281); 
                    ID58_tree = 
                    (Object)adaptor.create(ID58)
                    ;
                    adaptor.addChild(root_0, ID58_tree);



                    		if(!defer) {
                    			declareVariableNoValue((ID58!=null?ID58.getText():null), EugeneConstants.BOOLEAN);
                    			retval.varname = (ID58!=null?ID58.getText():null);
                    		}
                    		

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:894:5: ( COMMA booldecl[defer] )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:894:6: COMMA booldecl[defer]
                            {
                            COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_booldecl1288); 
                            COMMA59_tree = 
                            (Object)adaptor.create(COMMA59)
                            ;
                            adaptor.addChild(root_0, COMMA59_tree);


                            pushFollow(FOLLOW_booldecl_in_booldecl1290);
                            booldecl60=booldecl(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, booldecl60.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:895:4: var= ID EQUALS let= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    var=(Token)match(input,ID,FOLLOW_ID_in_booldecl1300); 
                    var_tree = 
                    (Object)adaptor.create(var)
                    ;
                    adaptor.addChild(root_0, var_tree);


                    EQUALS61=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_booldecl1302); 
                    EQUALS61_tree = 
                    (Object)adaptor.create(EQUALS61)
                    ;
                    adaptor.addChild(root_0, EQUALS61_tree);


                    pushFollow(FOLLOW_expr_in_booldecl1306);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:904:1: propertyDeclaration[boolean defer] : PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP ;
    public final EugeneParser.propertyDeclaration_return propertyDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.propertyDeclaration_return retval = new EugeneParser.propertyDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token PROPERTY62=null;
        Token LEFTP63=null;
        Token RIGHTP64=null;
        EugeneParser.propertyType_return typeToken =null;


        Object nameToken_tree=null;
        Object PROPERTY62_tree=null;
        Object LEFTP63_tree=null;
        Object RIGHTP64_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:905:2: ( PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:905:4: PROPERTY nameToken= ID LEFTP typeToken= propertyType RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            PROPERTY62=(Token)match(input,PROPERTY,FOLLOW_PROPERTY_in_propertyDeclaration1324); 
            PROPERTY62_tree = 
            (Object)adaptor.create(PROPERTY62)
            ;
            adaptor.addChild(root_0, PROPERTY62_tree);


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_propertyDeclaration1328); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            LEFTP63=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_propertyDeclaration1330); 
            LEFTP63_tree = 
            (Object)adaptor.create(LEFTP63)
            ;
            adaptor.addChild(root_0, LEFTP63_tree);


            pushFollow(FOLLOW_propertyType_in_propertyDeclaration1334);
            typeToken=propertyType();

            state._fsp--;

            adaptor.addChild(root_0, typeToken.getTree());

            RIGHTP64=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_propertyDeclaration1336); 
            RIGHTP64_tree = 
            (Object)adaptor.create(RIGHTP64)
            ;
            adaptor.addChild(root_0, RIGHTP64_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:918:1: propertyType returns [String type] : ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | BOOLEAN );
    public final EugeneParser.propertyType_return propertyType() throws RecognitionException {
        EugeneParser.propertyType_return retval = new EugeneParser.propertyType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token TXT65=null;
        Token TXT66=null;
        Token LEFTSBR67=null;
        Token RIGHTSBR68=null;
        Token NUM69=null;
        Token NUM70=null;
        Token LEFTSBR71=null;
        Token RIGHTSBR72=null;
        Token BOOLEAN73=null;

        Object TXT65_tree=null;
        Object TXT66_tree=null;
        Object LEFTSBR67_tree=null;
        Object RIGHTSBR68_tree=null;
        Object NUM69_tree=null;
        Object NUM70_tree=null;
        Object LEFTSBR71_tree=null;
        Object RIGHTSBR72_tree=null;
        Object BOOLEAN73_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:920:2: ( TXT | TXT LEFTSBR RIGHTSBR | NUM | NUM LEFTSBR RIGHTSBR | BOOLEAN )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:920:4: TXT
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT65=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1355); 
                    TXT65_tree = 
                    (Object)adaptor.create(TXT65)
                    ;
                    adaptor.addChild(root_0, TXT65_tree);



                    retval.type = EugeneConstants.TXT;	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:923:4: TXT LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    TXT66=(Token)match(input,TXT,FOLLOW_TXT_in_propertyType1362); 
                    TXT66_tree = 
                    (Object)adaptor.create(TXT66)
                    ;
                    adaptor.addChild(root_0, TXT66_tree);


                    LEFTSBR67=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1364); 
                    LEFTSBR67_tree = 
                    (Object)adaptor.create(LEFTSBR67)
                    ;
                    adaptor.addChild(root_0, LEFTSBR67_tree);


                    RIGHTSBR68=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1366); 
                    RIGHTSBR68_tree = 
                    (Object)adaptor.create(RIGHTSBR68)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR68_tree);



                    retval.type = EugeneConstants.TXTLIST;	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:926:4: NUM
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM69=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1373); 
                    NUM69_tree = 
                    (Object)adaptor.create(NUM69)
                    ;
                    adaptor.addChild(root_0, NUM69_tree);



                    retval.type = EugeneConstants.NUM;	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:929:4: NUM LEFTSBR RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    NUM70=(Token)match(input,NUM,FOLLOW_NUM_in_propertyType1381); 
                    NUM70_tree = 
                    (Object)adaptor.create(NUM70)
                    ;
                    adaptor.addChild(root_0, NUM70_tree);


                    LEFTSBR71=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_propertyType1383); 
                    LEFTSBR71_tree = 
                    (Object)adaptor.create(LEFTSBR71)
                    ;
                    adaptor.addChild(root_0, LEFTSBR71_tree);


                    RIGHTSBR72=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_propertyType1385); 
                    RIGHTSBR72_tree = 
                    (Object)adaptor.create(RIGHTSBR72)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR72_tree);



                    retval.type = EugeneConstants.NUMLIST;	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:932:4: BOOLEAN
                    {
                    root_0 = (Object)adaptor.nil();


                    BOOLEAN73=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_propertyType1392); 
                    BOOLEAN73_tree = 
                    (Object)adaptor.create(BOOLEAN73)
                    ;
                    adaptor.addChild(root_0, BOOLEAN73_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:941:1: typeDeclaration[boolean defer] : ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? );
    public final EugeneParser.typeDeclaration_return typeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.typeDeclaration_return retval = new EugeneParser.typeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token TYPE75=null;
        Token LEFTP76=null;
        Token RIGHTP77=null;
        EugeneParser.listOfIDs_return lstToken =null;

        EugeneParser.partTypeDeclaration_return partTypeDeclaration74 =null;


        Object nameToken_tree=null;
        Object TYPE75_tree=null;
        Object LEFTP76_tree=null;
        Object RIGHTP77_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:942:2: ( partTypeDeclaration[defer] | ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:942:4: partTypeDeclaration[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_partTypeDeclaration_in_typeDeclaration1410);
                    partTypeDeclaration74=partTypeDeclaration(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, partTypeDeclaration74.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:943:4: ( TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:943:4: ( TYPE )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:943:5: TYPE
                    {
                    TYPE75=(Token)match(input,TYPE,FOLLOW_TYPE_in_typeDeclaration1417); 
                    TYPE75_tree = 
                    (Object)adaptor.create(TYPE75)
                    ;
                    adaptor.addChild(root_0, TYPE75_tree);


                    }


                    nameToken=(Token)match(input,ID,FOLLOW_ID_in_typeDeclaration1422); 
                    nameToken_tree = 
                    (Object)adaptor.create(nameToken)
                    ;
                    adaptor.addChild(root_0, nameToken_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:943:24: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==LEFTP) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:943:25: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                            {
                            LEFTP76=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_typeDeclaration1425); 
                            LEFTP76_tree = 
                            (Object)adaptor.create(LEFTP76)
                            ;
                            adaptor.addChild(root_0, LEFTP76_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:943:31: (lstToken= listOfIDs[defer] )?
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0==ID) ) {
                                alt21=1;
                            }
                            switch (alt21) {
                                case 1 :
                                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:943:32: lstToken= listOfIDs[defer]
                                    {
                                    pushFollow(FOLLOW_listOfIDs_in_typeDeclaration1430);
                                    lstToken=listOfIDs(defer);

                                    state._fsp--;

                                    adaptor.addChild(root_0, lstToken.getTree());

                                    }
                                    break;

                            }


                            RIGHTP77=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_typeDeclaration1435); 
                            RIGHTP77_tree = 
                            (Object)adaptor.create(RIGHTP77)
                            ;
                            adaptor.addChild(root_0, RIGHTP77_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:956:1: partTypeDeclaration[boolean defer] : ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? ;
    public final EugeneParser.partTypeDeclaration_return partTypeDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.partTypeDeclaration_return retval = new EugeneParser.partTypeDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token set78=null;
        Token LEFTP79=null;
        Token RIGHTP80=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object nameToken_tree=null;
        Object set78_tree=null;
        Object LEFTP79_tree=null;
        Object RIGHTP80_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:957:2: ( ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:957:4: ( PART | PART_TYPE ) nameToken= ID ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            set78=(Token)input.LT(1);

            if ( (input.LA(1) >= PART && input.LA(1) <= PART_TYPE) ) {
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


            nameToken=(Token)match(input,ID,FOLLOW_ID_in_partTypeDeclaration1463); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:957:35: ( LEFTP (lstToken= listOfIDs[defer] )? RIGHTP )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LEFTP) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:957:36: LEFTP (lstToken= listOfIDs[defer] )? RIGHTP
                    {
                    LEFTP79=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_partTypeDeclaration1466); 
                    LEFTP79_tree = 
                    (Object)adaptor.create(LEFTP79)
                    ;
                    adaptor.addChild(root_0, LEFTP79_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:957:42: (lstToken= listOfIDs[defer] )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==ID) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:957:43: lstToken= listOfIDs[defer]
                            {
                            pushFollow(FOLLOW_listOfIDs_in_partTypeDeclaration1471);
                            lstToken=listOfIDs(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lstToken.getTree());

                            }
                            break;

                    }


                    RIGHTP80=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_partTypeDeclaration1476); 
                    RIGHTP80_tree = 
                    (Object)adaptor.create(RIGHTP80)
                    ;
                    adaptor.addChild(root_0, RIGHTP80_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:973:1: containerDeclaration[boolean defer] returns [NamedElement ne] : (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? ;
    public final EugeneParser.containerDeclaration_return containerDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.containerDeclaration_return retval = new EugeneParser.containerDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token c=null;
        Token a=null;
        Token name=null;
        Token LEFTSBR81=null;
        Token RIGHTSBR82=null;
        Token LEFTP83=null;
        Token RIGHTP85=null;
        EugeneParser.list_of_declarations_return list_of_declarations84 =null;


        Object c_tree=null;
        Object a_tree=null;
        Object name_tree=null;
        Object LEFTSBR81_tree=null;
        Object RIGHTSBR82_tree=null;
        Object LEFTP83_tree=null;
        Object RIGHTP85_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:975:2: ( (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:975:4: (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) ) name= ID ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:975:4: (c= COLLECTION | (a= ARRAY LEFTSBR RIGHTSBR ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==COLLECTION) ) {
                alt26=1;
            }
            else if ( (LA26_0==ARRAY) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:975:5: c= COLLECTION
                    {
                    c=(Token)match(input,COLLECTION,FOLLOW_COLLECTION_in_containerDeclaration1503); 
                    c_tree = 
                    (Object)adaptor.create(c)
                    ;
                    adaptor.addChild(root_0, c_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:975:20: (a= ARRAY LEFTSBR RIGHTSBR )
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:975:20: (a= ARRAY LEFTSBR RIGHTSBR )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:975:21: a= ARRAY LEFTSBR RIGHTSBR
                    {
                    a=(Token)match(input,ARRAY,FOLLOW_ARRAY_in_containerDeclaration1510); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    LEFTSBR81=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_containerDeclaration1512); 
                    LEFTSBR81_tree = 
                    (Object)adaptor.create(LEFTSBR81)
                    ;
                    adaptor.addChild(root_0, LEFTSBR81_tree);


                    RIGHTSBR82=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_containerDeclaration1514); 
                    RIGHTSBR82_tree = 
                    (Object)adaptor.create(RIGHTSBR82)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR82_tree);


                    }


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_containerDeclaration1520); 
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
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:989:4: ( LEFTP ( list_of_declarations[defer] )? RIGHTP )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==LEFTP) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:989:5: LEFTP ( list_of_declarations[defer] )? RIGHTP
                    {
                    LEFTP83=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_containerDeclaration1525); 
                    LEFTP83_tree = 
                    (Object)adaptor.create(LEFTP83)
                    ;
                    adaptor.addChild(root_0, LEFTP83_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:989:11: ( list_of_declarations[defer] )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==ARRAY||(LA27_0 >= BOOLEAN && LA27_0 <= COLLECTION)||LA27_0==DEVICE||LA27_0==FALSE||LA27_0==ID||LA27_0==INTERACTION||(LA27_0 >= LEFTP && LA27_0 <= LEFTSBR)||LA27_0==MINUS||(LA27_0 >= NUM && LA27_0 <= PART_TYPE)||(LA27_0 >= PROPERTY && LA27_0 <= REAL)||LA27_0==RULE||(LA27_0 >= STRING && LA27_0 <= TYPE)) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:989:12: list_of_declarations[defer]
                            {
                            pushFollow(FOLLOW_list_of_declarations_in_containerDeclaration1528);
                            list_of_declarations84=list_of_declarations(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, list_of_declarations84.getTree());

                            }
                            break;

                    }


                    RIGHTP85=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_containerDeclaration1533); 
                    RIGHTP85_tree = 
                    (Object)adaptor.create(RIGHTP85)
                    ;
                    adaptor.addChild(root_0, RIGHTP85_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1004:1: list_of_declarations[boolean defer] returns [List<NamedElement> elements] : (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )? ;
    public final EugeneParser.list_of_declarations_return list_of_declarations(boolean defer) throws RecognitionException {
        EugeneParser.list_of_declarations_return retval = new EugeneParser.list_of_declarations_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA86=null;
        EugeneParser.declarationStatement_return ds =null;

        EugeneParser.atom_return at =null;

        EugeneParser.list_of_declarations_return lod =null;


        Object COMMA86_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1006:2: ( (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1006:4: (ds= declarationStatement[defer] |at= atom[defer] ) ( COMMA lod= list_of_declarations[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1006:4: (ds= declarationStatement[defer] |at= atom[defer] )
            int alt29=2;
            switch ( input.LA(1) ) {
            case ARRAY:
            case BOOLEAN:
            case COLLECTION:
            case DEVICE:
            case INTERACTION:
            case NUM:
            case PART:
            case PART_TYPE:
            case PROPERTY:
            case RULE:
            case TXT:
            case TYPE:
                {
                alt29=1;
                }
                break;
            case ID:
                {
                int LA29_2 = input.LA(2);

                if ( (LA29_2==ID||LA29_2==LC_INDUCES||LA29_2==LC_REPRESSES||LA29_2==UC_INDUCES||LA29_2==UC_REPRESSES) ) {
                    alt29=1;
                }
                else if ( (LA29_2==COMMA||LA29_2==DOT||LA29_2==LEFTSBR||LA29_2==RIGHTP) ) {
                    alt29=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 2, input);

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
                alt29=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1006:6: ds= declarationStatement[defer]
                    {
                    pushFollow(FOLLOW_declarationStatement_in_list_of_declarations1566);
                    ds=declarationStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ds.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1006:39: at= atom[defer]
                    {
                    pushFollow(FOLLOW_atom_in_list_of_declarations1573);
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1018:5: ( COMMA lod= list_of_declarations[defer] )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==COMMA) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1018:7: COMMA lod= list_of_declarations[defer]
                    {
                    COMMA86=(Token)match(input,COMMA,FOLLOW_COMMA_in_list_of_declarations1581); 
                    COMMA86_tree = 
                    (Object)adaptor.create(COMMA86)
                    ;
                    adaptor.addChild(root_0, COMMA86_tree);


                    pushFollow(FOLLOW_list_of_declarations_in_list_of_declarations1585);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1024:1: instantiation[boolean defer] : t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? ;
    public final EugeneParser.instantiation_return instantiation(boolean defer) throws RecognitionException {
        EugeneParser.instantiation_return retval = new EugeneParser.instantiation_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token t=null;
        Token nameToken=null;
        Token LEFTP87=null;
        Token RIGHTP88=null;
        EugeneParser.listOfDotValues_return dotToken =null;

        EugeneParser.listOfValues_return valueToken =null;


        Object t_tree=null;
        Object nameToken_tree=null;
        Object LEFTP87_tree=null;
        Object RIGHTP88_tree=null;


        NamedElement type = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1028:2: (t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1028:4: t= ID nameToken= ID ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            t=(Token)match(input,ID,FOLLOW_ID_in_instantiation1613); 
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
            	

            nameToken=(Token)match(input,ID,FOLLOW_ID_in_instantiation1619); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1044:17: ( LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LEFTP) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1044:19: LEFTP (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )? RIGHTP
                    {
                    LEFTP87=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_instantiation1623); 
                    LEFTP87_tree = 
                    (Object)adaptor.create(LEFTP87)
                    ;
                    adaptor.addChild(root_0, LEFTP87_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1044:25: (dotToken= listOfDotValues[defer] |valueToken= listOfValues[defer, (ComponentType)type] )?
                    int alt31=3;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==DOT) ) {
                        alt31=1;
                    }
                    else if ( (LA31_0==FALSE||LA31_0==ID||(LA31_0 >= LEFTP && LA31_0 <= LEFTSBR)||LA31_0==MINUS||LA31_0==NUMBER||LA31_0==REAL||(LA31_0 >= STRING && LA31_0 <= TRUE)) ) {
                        alt31=2;
                    }
                    switch (alt31) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1044:26: dotToken= listOfDotValues[defer]
                            {
                            pushFollow(FOLLOW_listOfDotValues_in_instantiation1628);
                            dotToken=listOfDotValues(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dotToken.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1044:58: valueToken= listOfValues[defer, (ComponentType)type]
                            {
                            pushFollow(FOLLOW_listOfValues_in_instantiation1633);
                            valueToken=listOfValues(defer, (ComponentType)type);

                            state._fsp--;

                            adaptor.addChild(root_0, valueToken.getTree());

                            }
                            break;

                    }


                    RIGHTP88=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_instantiation1638); 
                    RIGHTP88_tree = 
                    (Object)adaptor.create(RIGHTP88)
                    ;
                    adaptor.addChild(root_0, RIGHTP88_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1079:1: listOfDotValues[boolean defer] : DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* ;
    public final EugeneParser.listOfDotValues_return listOfDotValues(boolean defer) throws RecognitionException {
        EugeneParser.listOfDotValues_return retval = new EugeneParser.listOfDotValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token prop=null;
        Token p=null;
        Token DOT89=null;
        Token LEFTP90=null;
        Token RIGHTP91=null;
        Token COMMA92=null;
        Token DOT93=null;
        Token LEFTP94=null;
        Token RIGHTP95=null;
        EugeneParser.expr_return v1 =null;

        EugeneParser.expr_return v2 =null;


        Object prop_tree=null;
        Object p_tree=null;
        Object DOT89_tree=null;
        Object LEFTP90_tree=null;
        Object RIGHTP91_tree=null;
        Object COMMA92_tree=null;
        Object DOT93_tree=null;
        Object LEFTP94_tree=null;
        Object RIGHTP95_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1080:2: ( DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1080:4: DOT prop= ID LEFTP v1= expr[defer] RIGHTP ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            {
            root_0 = (Object)adaptor.nil();


            DOT89=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1662); 
            DOT89_tree = 
            (Object)adaptor.create(DOT89)
            ;
            adaptor.addChild(root_0, DOT89_tree);


            prop=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1666); 
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
            		

            LEFTP90=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1672); 
            LEFTP90_tree = 
            (Object)adaptor.create(LEFTP90)
            ;
            adaptor.addChild(root_0, LEFTP90_tree);


            pushFollow(FOLLOW_expr_in_listOfDotValues1676);
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
            			

            RIGHTP91=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1684); 
            RIGHTP91_tree = 
            (Object)adaptor.create(RIGHTP91)
            ;
            adaptor.addChild(root_0, RIGHTP91_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1098:13: ( COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==COMMA) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1098:14: COMMA DOT p= ID LEFTP v2= expr[defer] RIGHTP
            	    {
            	    COMMA92=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfDotValues1687); 
            	    COMMA92_tree = 
            	    (Object)adaptor.create(COMMA92)
            	    ;
            	    adaptor.addChild(root_0, COMMA92_tree);


            	    DOT93=(Token)match(input,DOT,FOLLOW_DOT_in_listOfDotValues1689); 
            	    DOT93_tree = 
            	    (Object)adaptor.create(DOT93)
            	    ;
            	    adaptor.addChild(root_0, DOT93_tree);


            	    p=(Token)match(input,ID,FOLLOW_ID_in_listOfDotValues1693); 
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
            	    				

            	    LEFTP94=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_listOfDotValues1701); 
            	    LEFTP94_tree = 
            	    (Object)adaptor.create(LEFTP94)
            	    ;
            	    adaptor.addChild(root_0, LEFTP94_tree);


            	    pushFollow(FOLLOW_expr_in_listOfDotValues1705);
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
            	    					

            	    RIGHTP95=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_listOfDotValues1715); 
            	    RIGHTP95_tree = 
            	    (Object)adaptor.create(RIGHTP95)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP95_tree);


            	    }
            	    break;

            	default :
            	    break loop33;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1119:1: listOfValues[boolean defer, ComponentType pt] :val1= expr[defer] ( COMMA val2= expr[defer] )* ;
    public final EugeneParser.listOfValues_return listOfValues(boolean defer, ComponentType pt) throws RecognitionException {
        EugeneParser.listOfValues_return retval = new EugeneParser.listOfValues_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA96=null;
        EugeneParser.expr_return val1 =null;

        EugeneParser.expr_return val2 =null;


        Object COMMA96_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1120:2: (val1= expr[defer] ( COMMA val2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1121:3: val1= expr[defer] ( COMMA val2= expr[defer] )*
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
            		

            pushFollow(FOLLOW_expr_in_listOfValues1736);
            val1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, val1.getTree());


            if(!defer) {
                propertyValuesHolder.add((val1!=null?val1.p:null));
            }				
            			

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1145:6: ( COMMA val2= expr[defer] )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMA) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1145:7: COMMA val2= expr[defer]
            	    {
            	    COMMA96=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfValues1742); 
            	    COMMA96_tree = 
            	    (Object)adaptor.create(COMMA96)
            	    ;
            	    adaptor.addChild(root_0, COMMA96_tree);



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
            	                   

            	    pushFollow(FOLLOW_expr_in_listOfValues1748);
            	    val2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, val2.getTree());


            	    if(!defer) {
            	        propertyValuesHolder.add((val2!=null?val2.p:null));
            	    }				
            	    		

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
    // $ANTLR end "listOfValues"


    public static class deviceDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "deviceDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1175:1: deviceDeclaration[boolean defer] : DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? ;
    public final EugeneParser.deviceDeclaration_return deviceDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.deviceDeclaration_return retval = new EugeneParser.deviceDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token DEVICE97=null;
        Token LEFTP98=null;
        Token RIGHTP99=null;
        EugeneParser.deviceComponents_return dcs =null;


        Object n_tree=null;
        Object DEVICE97_tree=null;
        Object LEFTP98_tree=null;
        Object RIGHTP99_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1176:2: ( DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1176:4: DEVICE n= ID ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            {
            root_0 = (Object)adaptor.nil();


            DEVICE97=(Token)match(input,DEVICE,FOLLOW_DEVICE_in_deviceDeclaration1767); 
            DEVICE97_tree = 
            (Object)adaptor.create(DEVICE97)
            ;
            adaptor.addChild(root_0, DEVICE97_tree);


            n=(Token)match(input,ID,FOLLOW_ID_in_deviceDeclaration1771); 
            n_tree = 
            (Object)adaptor.create(n)
            ;
            adaptor.addChild(root_0, n_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1176:16: ( LEFTP (dcs= deviceComponents[defer] )? RIGHTP )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==LEFTP) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1176:17: LEFTP (dcs= deviceComponents[defer] )? RIGHTP
                    {
                    LEFTP98=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_deviceDeclaration1774); 
                    LEFTP98_tree = 
                    (Object)adaptor.create(LEFTP98)
                    ;
                    adaptor.addChild(root_0, LEFTP98_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1176:23: (dcs= deviceComponents[defer] )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==ID||LA35_0==LEFTSBR||LA35_0==MINUS||LA35_0==PLUS) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1176:24: dcs= deviceComponents[defer]
                            {
                            pushFollow(FOLLOW_deviceComponents_in_deviceDeclaration1779);
                            dcs=deviceComponents(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, dcs.getTree());

                            }
                            break;

                    }


                    RIGHTP99=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_deviceDeclaration1784); 
                    RIGHTP99_tree = 
                    (Object)adaptor.create(RIGHTP99)
                    ;
                    adaptor.addChild(root_0, RIGHTP99_tree);


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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1190:1: deviceComponents[boolean defer] returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations] : s= selection[defer] ( ',' dcs= deviceComponents[defer] )? ;
    public final EugeneParser.deviceComponents_return deviceComponents(boolean defer) throws RecognitionException {
        EugeneParser.deviceComponents_return retval = new EugeneParser.deviceComponents_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal100=null;
        EugeneParser.selection_return s =null;

        EugeneParser.deviceComponents_return dcs =null;


        Object char_literal100_tree=null;


        retval.lstComponents = new ArrayList<List<NamedElement>>();
        retval.lstOrientations = new ArrayList<List<Orientation>>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1196:2: (s= selection[defer] ( ',' dcs= deviceComponents[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1196:4: s= selection[defer] ( ',' dcs= deviceComponents[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selection_in_deviceComponents1815);
            s=selection(defer);

            state._fsp--;

            adaptor.addChild(root_0, s.getTree());


            if(!defer) {
                retval.lstComponents.add((s!=null?s.components:null));
                retval.lstOrientations.add((s!=null?s.orientations:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1201:4: ( ',' dcs= deviceComponents[defer] )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==COMMA) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1201:5: ',' dcs= deviceComponents[defer]
                    {
                    char_literal100=(Token)match(input,COMMA,FOLLOW_COMMA_in_deviceComponents1821); 
                    char_literal100_tree = 
                    (Object)adaptor.create(char_literal100)
                    ;
                    adaptor.addChild(root_0, char_literal100_tree);


                    pushFollow(FOLLOW_deviceComponents_in_deviceComponents1825);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1209:1: selection[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] );
    public final EugeneParser.selection_return selection(boolean defer) throws RecognitionException {
        EugeneParser.selection_return retval = new EugeneParser.selection_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTSBR101=null;
        Token RIGHTSBR102=null;
        EugeneParser.selection_list_return sl =null;

        EugeneParser.device_component_return dc =null;


        Object LEFTSBR101_tree=null;
        Object RIGHTSBR102_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1211:2: ( LEFTSBR sl= selection_list[defer] RIGHTSBR |dc= device_component[defer] )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LEFTSBR) ) {
                alt38=1;
            }
            else if ( (LA38_0==ID||LA38_0==MINUS||LA38_0==PLUS) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1211:4: LEFTSBR sl= selection_list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR101=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_selection1849); 
                    LEFTSBR101_tree = 
                    (Object)adaptor.create(LEFTSBR101)
                    ;
                    adaptor.addChild(root_0, LEFTSBR101_tree);


                    pushFollow(FOLLOW_selection_list_in_selection1853);
                    sl=selection_list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sl.getTree());

                    RIGHTSBR102=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_selection1856); 
                    RIGHTSBR102_tree = 
                    (Object)adaptor.create(RIGHTSBR102)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR102_tree);



                    if(!defer) {
                        retval.components = (sl!=null?sl.components:null);
                        retval.orientations = (sl!=null?sl.orientations:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1217:4: dc= device_component[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_device_component_in_selection1865);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1228:1: selection_list[boolean defer] returns [List<NamedElement> components, List<Orientation> orientations] : dc= device_component[defer] ( PIPE sl= selection_list[defer] )? ;
    public final EugeneParser.selection_list_return selection_list(boolean defer) throws RecognitionException {
        EugeneParser.selection_list_return retval = new EugeneParser.selection_list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PIPE103=null;
        EugeneParser.device_component_return dc =null;

        EugeneParser.selection_list_return sl =null;


        Object PIPE103_tree=null;


        retval.components = new ArrayList<NamedElement>();
        retval.orientations = new ArrayList<Orientation>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1234:2: (dc= device_component[defer] ( PIPE sl= selection_list[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1234:4: dc= device_component[defer] ( PIPE sl= selection_list[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_device_component_in_selection_list1894);
            dc=device_component(defer);

            state._fsp--;

            adaptor.addChild(root_0, dc.getTree());


            if(!defer) {
                retval.components.add((dc!=null?dc.component:null));
                retval.orientations.add((dc!=null?dc.orientation:null));
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1239:4: ( PIPE sl= selection_list[defer] )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==PIPE) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1239:5: PIPE sl= selection_list[defer]
                    {
                    PIPE103=(Token)match(input,PIPE,FOLLOW_PIPE_in_selection_list1900); 
                    PIPE103_tree = 
                    (Object)adaptor.create(PIPE103)
                    ;
                    adaptor.addChild(root_0, PIPE103_tree);


                    pushFollow(FOLLOW_selection_list_in_selection_list1904);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1247:1: device_component[boolean defer] returns [NamedElement component, Orientation orientation] : (directionToken= ( MINUS | PLUS ) )? idToken= ID ;
    public final EugeneParser.device_component_return device_component(boolean defer) throws RecognitionException {
        EugeneParser.device_component_return retval = new EugeneParser.device_component_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token directionToken=null;
        Token idToken=null;

        Object directionToken_tree=null;
        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1249:2: ( (directionToken= ( MINUS | PLUS ) )? idToken= ID )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1249:4: (directionToken= ( MINUS | PLUS ) )? idToken= ID
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1249:4: (directionToken= ( MINUS | PLUS ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==MINUS||LA40_0==PLUS) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1249:5: directionToken= ( MINUS | PLUS )
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


            idToken=(Token)match(input,ID,FOLLOW_ID_in_device_component1940); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1297:1: assignment[boolean defer] : lhs= lhs_assignment[defer, null, null, null] EQUALS (a= AMP )? rhs= rhs_assignment[defer] ;
    public final EugeneParser.assignment_return assignment(boolean defer) throws RecognitionException {
        EugeneParser.assignment_return retval = new EugeneParser.assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token a=null;
        Token EQUALS104=null;
        EugeneParser.lhs_assignment_return lhs =null;

        EugeneParser.rhs_assignment_return rhs =null;


        Object a_tree=null;
        Object EQUALS104_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1298:2: (lhs= lhs_assignment[defer, null, null, null] EQUALS (a= AMP )? rhs= rhs_assignment[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1298:4: lhs= lhs_assignment[defer, null, null, null] EQUALS (a= AMP )? rhs= rhs_assignment[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_lhs_assignment_in_assignment1960);
            lhs=lhs_assignment(defer, null, null, null);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            EQUALS104=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assignment1963); 
            EQUALS104_tree = 
            (Object)adaptor.create(EQUALS104)
            ;
            adaptor.addChild(root_0, EQUALS104_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1298:55: (a= AMP )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==AMP) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1298:56: a= AMP
                    {
                    a=(Token)match(input,AMP,FOLLOW_AMP_in_assignment1968); 
                    a_tree = 
                    (Object)adaptor.create(a)
                    ;
                    adaptor.addChild(root_0, a_tree);


                    }
                    break;

            }


            pushFollow(FOLLOW_rhs_assignment_in_assignment1974);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1309:1: lhs_assignment[boolean defer, NamedElement parent, String id_in, Variable idx_in] returns [String name, NamedElement child, String id_out = null, Variable idx_out = null] : idToken= ID ac= lhs_access[defer, parent, $id_out, $idx_out] ;
    public final EugeneParser.lhs_assignment_return lhs_assignment(boolean defer, NamedElement parent, String id_in, Variable idx_in) throws RecognitionException {
        EugeneParser.lhs_assignment_return retval = new EugeneParser.lhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        EugeneParser.lhs_access_return ac =null;


        Object idToken_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1311:2: (idToken= ID ac= lhs_access[defer, parent, $id_out, $idx_out] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1311:4: idToken= ID ac= lhs_access[defer, parent, $id_out, $idx_out]
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_lhs_assignment1996); 
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
            	

            pushFollow(FOLLOW_lhs_access_in_lhs_assignment2002);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1342:1: lhs_access[boolean defer, NamedElement parent, String id_in, Variable idx_in] returns [NamedElement child, String id_out, Variable idx_out] : (| DOT lhs= lhs_assignment[defer, parent, id_in, idx_in] | LEFTSBR (exp= expr[defer] ) RIGHTSBR ac= lhs_access[defer, $child, $id_out, $idx_out] );
    public final EugeneParser.lhs_access_return lhs_access(boolean defer, NamedElement parent, String id_in, Variable idx_in) throws RecognitionException {
        EugeneParser.lhs_access_return retval = new EugeneParser.lhs_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token DOT105=null;
        Token LEFTSBR106=null;
        Token RIGHTSBR107=null;
        EugeneParser.lhs_assignment_return lhs =null;

        EugeneParser.expr_return exp =null;

        EugeneParser.lhs_access_return ac =null;


        Object DOT105_tree=null;
        Object LEFTSBR106_tree=null;
        Object RIGHTSBR107_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1351:2: (| DOT lhs= lhs_assignment[defer, parent, id_in, idx_in] | LEFTSBR (exp= expr[defer] ) RIGHTSBR ac= lhs_access[defer, $child, $id_out, $idx_out] )
            int alt42=3;
            switch ( input.LA(1) ) {
            case EQUALS:
                {
                alt42=1;
                }
                break;
            case DOT:
                {
                alt42=2;
                }
                break;
            case LEFTSBR:
                {
                alt42=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }

            switch (alt42) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1351:4: 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1358:4: DOT lhs= lhs_assignment[defer, parent, id_in, idx_in]
                    {
                    root_0 = (Object)adaptor.nil();


                    DOT105=(Token)match(input,DOT,FOLLOW_DOT_in_lhs_access2034); 
                    DOT105_tree = 
                    (Object)adaptor.create(DOT105)
                    ;
                    adaptor.addChild(root_0, DOT105_tree);


                    pushFollow(FOLLOW_lhs_assignment_in_lhs_access2038);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1365:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR ac= lhs_access[defer, $child, $id_out, $idx_out]
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR106=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_lhs_access2046); 
                    LEFTSBR106_tree = 
                    (Object)adaptor.create(LEFTSBR106)
                    ;
                    adaptor.addChild(root_0, LEFTSBR106_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1365:12: (exp= expr[defer] )
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1365:13: exp= expr[defer]
                    {
                    pushFollow(FOLLOW_expr_in_lhs_access2051);
                    exp=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, exp.getTree());

                    }


                    RIGHTSBR107=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_lhs_access2055); 
                    RIGHTSBR107_tree = 
                    (Object)adaptor.create(RIGHTSBR107)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR107_tree);



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
                    	

                    pushFollow(FOLLOW_lhs_access_in_lhs_access2061);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1394:1: rhs_assignment[boolean defer] returns [NamedElement e] : (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] );
    public final EugeneParser.rhs_assignment_return rhs_assignment(boolean defer) throws RecognitionException {
        EugeneParser.rhs_assignment_return retval = new EugeneParser.rhs_assignment_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.functionCall_return fc =null;

        EugeneParser.dataExchange_return de =null;

        EugeneParser.expr_return exp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1396:2: (fc= functionCall[defer] |de= dataExchange[defer] |exp= expr[defer] )
            int alt43=3;
            switch ( input.LA(1) ) {
            case PERMUTE:
            case PRODUCT:
                {
                alt43=1;
                }
                break;
            case GENBANK:
            case LC_IMPORT:
            case LC_PIGEON:
            case PIGEON:
            case REGISTRY:
            case SBOL:
            case UC_IMPORT:
                {
                alt43=2;
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1396:4: fc= functionCall[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_rhs_assignment2086);
                    fc=functionCall(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, fc.getTree());


                    if(!defer) {
                        retval.e = (fc!=null?fc.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1401:4: de= dataExchange[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_dataExchange_in_rhs_assignment2096);
                    de=dataExchange(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, de.getTree());


                    if(!defer) {
                        retval.e = (de!=null?de.e:null);
                    }	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1406:4: exp= expr[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_rhs_assignment2106);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1417:1: listOfIDs[boolean defer] returns [List<NamedElement> lstElements] : idToken= ID ( ',' lstToken= listOfIDs[defer] )? ;
    public final EugeneParser.listOfIDs_return listOfIDs(boolean defer) throws RecognitionException {
        EugeneParser.listOfIDs_return retval = new EugeneParser.listOfIDs_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token char_literal108=null;
        EugeneParser.listOfIDs_return lstToken =null;


        Object idToken_tree=null;
        Object char_literal108_tree=null;


        retval.lstElements =new ArrayList<NamedElement>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1422:2: (idToken= ID ( ',' lstToken= listOfIDs[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1422:4: idToken= ID ( ',' lstToken= listOfIDs[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            idToken=(Token)match(input,ID,FOLLOW_ID_in_listOfIDs2134); 
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1434:4: ( ',' lstToken= listOfIDs[defer] )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==COMMA) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1434:5: ',' lstToken= listOfIDs[defer]
                    {
                    char_literal108=(Token)match(input,COMMA,FOLLOW_COMMA_in_listOfIDs2143); 
                    char_literal108_tree = 
                    (Object)adaptor.create(char_literal108)
                    ;
                    adaptor.addChild(root_0, char_literal108_tree);


                    pushFollow(FOLLOW_listOfIDs_in_listOfIDs2147);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1441:1: ruleDeclaration[boolean defer] returns [Rule rule] : RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP ;
    public final EugeneParser.ruleDeclaration_return ruleDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.ruleDeclaration_return retval = new EugeneParser.ruleDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token device=null;
        Token RULE109=null;
        Token LEFTP110=null;
        Token set111=null;
        Token COLON112=null;
        Token RIGHTP113=null;
        EugeneParser.cnf_rule_return cnf =null;


        Object name_tree=null;
        Object device_tree=null;
        Object RULE109_tree=null;
        Object LEFTP110_tree=null;
        Object set111_tree=null;
        Object COLON112_tree=null;
        Object RIGHTP113_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1443:2: ( RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1443:4: RULE name= ID LEFTP ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] ) RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            RULE109=(Token)match(input,RULE,FOLLOW_RULE_in_ruleDeclaration2171); 
            RULE109_tree = 
            (Object)adaptor.create(RULE109)
            ;
            adaptor.addChild(root_0, RULE109_tree);


            name=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2175); 
            name_tree = 
            (Object)adaptor.create(name)
            ;
            adaptor.addChild(root_0, name_tree);


            LEFTP110=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ruleDeclaration2177); 
            LEFTP110_tree = 
            (Object)adaptor.create(LEFTP110)
            ;
            adaptor.addChild(root_0, LEFTP110_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1443:23: ( ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1443:25: ( ( LC_ON | UC_ON ) device= ID COLON )? cnf= cnf_rule[defer]
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1443:25: ( ( LC_ON | UC_ON ) device= ID COLON )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LC_ON||LA45_0==UC_ON) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1443:26: ( LC_ON | UC_ON ) device= ID COLON
                    {
                    set111=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ON||input.LA(1)==UC_ON ) {
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


                    device=(Token)match(input,ID,FOLLOW_ID_in_ruleDeclaration2190); 
                    device_tree = 
                    (Object)adaptor.create(device)
                    ;
                    adaptor.addChild(root_0, device_tree);


                    COLON112=(Token)match(input,COLON,FOLLOW_COLON_in_ruleDeclaration2192); 
                    COLON112_tree = 
                    (Object)adaptor.create(COLON112)
                    ;
                    adaptor.addChild(root_0, COLON112_tree);


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
            	

            pushFollow(FOLLOW_cnf_rule_in_ruleDeclaration2200);
            cnf=cnf_rule(defer);

            state._fsp--;

            adaptor.addChild(root_0, cnf.getTree());

            }


            RIGHTP113=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ruleDeclaration2205); 
            RIGHTP113_tree = 
            (Object)adaptor.create(RIGHTP113)
            ;
            adaptor.addChild(root_0, RIGHTP113_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1467:1: ruleOperator[boolean defer] : ruleOperators ;
    public final EugeneParser.ruleOperator_return ruleOperator(boolean defer) throws RecognitionException {
        EugeneParser.ruleOperator_return retval = new EugeneParser.ruleOperator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ruleOperators_return ruleOperators114 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1468:2: ( ruleOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1468:4: ruleOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_ruleOperators_in_ruleOperator2219);
            ruleOperators114=ruleOperators();

            state._fsp--;

            adaptor.addChild(root_0, ruleOperators114.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1472:1: ruleOperators : ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) );
    public final EugeneParser.ruleOperators_return ruleOperators() throws RecognitionException {
        EugeneParser.ruleOperators_return retval = new EugeneParser.ruleOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set115=null;

        Object set115_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1473:2: ( ( 'CONTAINS' | 'contains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'THEN' | 'then' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'MATCHES' | 'matches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'NOTWITH' | 'notwith' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'NOTMATCHES' | 'notmatches' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:
            {
            root_0 = (Object)adaptor.nil();


            set115=(Token)input.LT(1);

            if ( input.LA(1)==LC_INDUCES||input.LA(1)==LC_REPRESSES||input.LA(1)==UC_INDUCES||input.LA(1)==UC_REPRESSES||(input.LA(1) >= 99 && input.LA(1) <= 133)||(input.LA(1) >= 135 && input.LA(1) <= 172)||(input.LA(1) >= 174 && input.LA(1) <= 176) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set115)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1516:1: relationalOperators : ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) );
    public final EugeneParser.relationalOperators_return relationalOperators() throws RecognitionException {
        EugeneParser.relationalOperators_return retval = new EugeneParser.relationalOperators_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EQUALS116=null;
        Token EQUALS117=null;
        Token NEQUAL118=null;
        Token LTHAN119=null;
        Token GTHAN120=null;
        Token LEQUAL121=null;
        Token GEQUAL122=null;
        Token set123=null;
        Token set124=null;
        Token set125=null;
        Token set126=null;
        Token set127=null;
        Token set128=null;
        Token set129=null;
        Token set130=null;
        Token set131=null;

        Object EQUALS116_tree=null;
        Object EQUALS117_tree=null;
        Object NEQUAL118_tree=null;
        Object LTHAN119_tree=null;
        Object GTHAN120_tree=null;
        Object LEQUAL121_tree=null;
        Object GEQUAL122_tree=null;
        Object set123_tree=null;
        Object set124_tree=null;
        Object set125_tree=null;
        Object set126_tree=null;
        Object set127_tree=null;
        Object set128_tree=null;
        Object set129_tree=null;
        Object set130_tree=null;
        Object set131_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1517:2: ( EQUALS EQUALS | NEQUAL | LTHAN | GTHAN | LEQUAL | GEQUAL | ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'SOUNDSLIKE' | 'soundslike' ) )
            int alt46=15;
            switch ( input.LA(1) ) {
            case EQUALS:
                {
                alt46=1;
                }
                break;
            case NEQUAL:
                {
                alt46=2;
                }
                break;
            case LTHAN:
                {
                alt46=3;
                }
                break;
            case GTHAN:
                {
                alt46=4;
                }
                break;
            case LEQUAL:
                {
                alt46=5;
                }
                break;
            case GEQUAL:
                {
                alt46=6;
                }
                break;
            case 109:
            case 148:
                {
                alt46=7;
                }
                break;
            case 118:
            case 157:
                {
                alt46=8;
                }
                break;
            case 115:
            case 154:
                {
                alt46=9;
                }
                break;
            case 121:
            case 160:
                {
                alt46=10;
                }
                break;
            case 135:
            case 174:
                {
                alt46=11;
                }
                break;
            case 111:
            case 150:
                {
                alt46=12;
                }
                break;
            case 112:
            case 151:
                {
                alt46=13;
                }
                break;
            case 119:
            case 158:
                {
                alt46=14;
                }
                break;
            case 134:
            case 173:
                {
                alt46=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }

            switch (alt46) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1517:4: EQUALS EQUALS
                    {
                    root_0 = (Object)adaptor.nil();


                    EQUALS116=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2598); 
                    EQUALS116_tree = 
                    (Object)adaptor.create(EQUALS116)
                    ;
                    adaptor.addChild(root_0, EQUALS116_tree);


                    EQUALS117=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_relationalOperators2600); 
                    EQUALS117_tree = 
                    (Object)adaptor.create(EQUALS117)
                    ;
                    adaptor.addChild(root_0, EQUALS117_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1518:4: NEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    NEQUAL118=(Token)match(input,NEQUAL,FOLLOW_NEQUAL_in_relationalOperators2605); 
                    NEQUAL118_tree = 
                    (Object)adaptor.create(NEQUAL118)
                    ;
                    adaptor.addChild(root_0, NEQUAL118_tree);


                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1519:4: LTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    LTHAN119=(Token)match(input,LTHAN,FOLLOW_LTHAN_in_relationalOperators2610); 
                    LTHAN119_tree = 
                    (Object)adaptor.create(LTHAN119)
                    ;
                    adaptor.addChild(root_0, LTHAN119_tree);


                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1520:4: GTHAN
                    {
                    root_0 = (Object)adaptor.nil();


                    GTHAN120=(Token)match(input,GTHAN,FOLLOW_GTHAN_in_relationalOperators2615); 
                    GTHAN120_tree = 
                    (Object)adaptor.create(GTHAN120)
                    ;
                    adaptor.addChild(root_0, GTHAN120_tree);


                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1521:4: LEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    LEQUAL121=(Token)match(input,LEQUAL,FOLLOW_LEQUAL_in_relationalOperators2620); 
                    LEQUAL121_tree = 
                    (Object)adaptor.create(LEQUAL121)
                    ;
                    adaptor.addChild(root_0, LEQUAL121_tree);


                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1522:4: GEQUAL
                    {
                    root_0 = (Object)adaptor.nil();


                    GEQUAL122=(Token)match(input,GEQUAL,FOLLOW_GEQUAL_in_relationalOperators2625); 
                    GEQUAL122_tree = 
                    (Object)adaptor.create(GEQUAL122)
                    ;
                    adaptor.addChild(root_0, GEQUAL122_tree);


                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1523:4: ( 'CONTAINS' | 'contains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set123=(Token)input.LT(1);

                    if ( input.LA(1)==109||input.LA(1)==148 ) {
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
                    break;
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1524:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set124=(Token)input.LT(1);

                    if ( input.LA(1)==118||input.LA(1)==157 ) {
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
                    break;
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1525:4: ( 'MATCHES' | 'matches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set125=(Token)input.LT(1);

                    if ( input.LA(1)==115||input.LA(1)==154 ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set125)
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1526:4: ( 'NOTMATCHES' | 'notmatches' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set126=(Token)input.LT(1);

                    if ( input.LA(1)==121||input.LA(1)==160 ) {
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
                case 11 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1527:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set127=(Token)input.LT(1);

                    if ( input.LA(1)==135||input.LA(1)==174 ) {
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
                case 12 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1528:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set128=(Token)input.LT(1);

                    if ( input.LA(1)==111||input.LA(1)==150 ) {
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
                case 13 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1529:4: ( 'EQUALS' | 'equals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set129=(Token)input.LT(1);

                    if ( input.LA(1)==112||input.LA(1)==151 ) {
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
                case 14 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1530:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set130=(Token)input.LT(1);

                    if ( input.LA(1)==119||input.LA(1)==158 ) {
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
                case 15 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1531:4: ( 'SOUNDSLIKE' | 'soundslike' )
                    {
                    root_0 = (Object)adaptor.nil();


                    set131=(Token)input.LT(1);

                    if ( input.LA(1)==134||input.LA(1)==173 ) {
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

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1534:1: cnf_rule[boolean defer] returns [LogicalAnd lAnd] : (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? ;
    public final EugeneParser.cnf_rule_return cnf_rule(boolean defer) throws RecognitionException {
        EugeneParser.cnf_rule_return retval = new EugeneParser.cnf_rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set132=null;
        EugeneParser.or_predicate_return c =null;

        EugeneParser.cnf_rule_return cnf =null;


        Object set132_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1536:2: ( (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1536:4: (c= or_predicate[defer] ) ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1536:4: (c= or_predicate[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1536:5: c= or_predicate[defer]
            {
            pushFollow(FOLLOW_or_predicate_in_cnf_rule2726);
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


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1544:5: ( ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer] )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==LC_AND||LA47_0==LOG_AND||LA47_0==UC_AND) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1544:7: ( LC_AND | UC_AND | LOG_AND ) cnf= cnf_rule[defer]
                    {
                    set132=(Token)input.LT(1);

                    if ( input.LA(1)==LC_AND||input.LA(1)==LOG_AND||input.LA(1)==UC_AND ) {
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


                    pushFollow(FOLLOW_cnf_rule_in_cnf_rule2744);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1551:1: or_predicate[boolean defer] returns [Predicate p] : n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* ;
    public final EugeneParser.or_predicate_return or_predicate(boolean defer) throws RecognitionException {
        EugeneParser.or_predicate_return retval = new EugeneParser.or_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set133=null;
        EugeneParser.negated_predicate_return n1 =null;

        EugeneParser.negated_predicate_return n2 =null;


        Object set133_tree=null;


        LogicalOr lor = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1556:2: (n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1556:4: n1= negated_predicate[defer] ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_negated_predicate_in_or_predicate2774);
            n1=negated_predicate(defer);

            state._fsp--;

            adaptor.addChild(root_0, n1.getTree());


            if(!defer) {
                retval.p = (n1!=null?n1.p:null);
            }	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1560:4: ( ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer] )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==LC_OR||LA48_0==LOG_OR||LA48_0==UC_OR) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1560:5: ( LC_OR | UC_OR | LOG_OR ) n2= negated_predicate[defer]
            	    {
            	    set133=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
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


            	    pushFollow(FOLLOW_negated_predicate_in_or_predicate2790);
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
            	    break loop48;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1581:1: negated_predicate[boolean defer] returns [Predicate p] : ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) ;
    public final EugeneParser.negated_predicate_return negated_predicate(boolean defer) throws RecognitionException {
        EugeneParser.negated_predicate_return retval = new EugeneParser.negated_predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set134=null;
        EugeneParser.predicate_return c =null;


        Object set134_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1583:2: ( ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1583:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1583:4: ( ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer] |c= predicate[defer] )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LC_NOT||LA49_0==LOG_NOT||LA49_0==UC_NOT) ) {
                alt49=1;
            }
            else if ( (LA49_0==ID||LA49_0==LC_INDUCES||LA49_0==LC_REPRESSES||(LA49_0 >= LEFTP && LA49_0 <= LEFTSBR)||LA49_0==MINUS||LA49_0==NUMBER||LA49_0==REAL||LA49_0==STRING||LA49_0==UC_INDUCES||LA49_0==UC_REPRESSES||(LA49_0 >= 99 && LA49_0 <= 133)||(LA49_0 >= 135 && LA49_0 <= 172)||(LA49_0 >= 174 && LA49_0 <= 176)) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }
            switch (alt49) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1583:5: ( UC_NOT | LC_NOT | LOG_NOT ) c= predicate[defer]
                    {
                    set134=(Token)input.LT(1);

                    if ( input.LA(1)==LC_NOT||input.LA(1)==LOG_NOT||input.LA(1)==UC_NOT ) {
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


                    pushFollow(FOLLOW_predicate_in_negated_predicate2828);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1592:4: c= predicate[defer]
                    {
                    pushFollow(FOLLOW_predicate_in_negated_predicate2838);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1599:1: predicate[boolean defer] returns [Predicate p] : ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] );
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1601:2: ( (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )? |i= ID |exp= expressionRule[defer] )
            int alt52=3;
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
                case 134:
                case 173:
                    {
                    alt52=3;
                    }
                    break;
                case 109:
                case 148:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 5, input);

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
                    alt52=2;
                    }
                    break;
                case 118:
                case 157:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 7, input);

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
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 8, input);

                        throw nvae;

                    }

                    }
                    break;
                case 121:
                case 160:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 9, input);

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
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 10, input);

                        throw nvae;

                    }

                    }
                    break;
                case 111:
                case 150:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 11, input);

                        throw nvae;

                    }

                    }
                    break;
                case 112:
                case 151:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 12, input);

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
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 13, input);

                        throw nvae;

                    }

                    }
                    break;
                case LC_INDUCES:
                case LC_REPRESSES:
                case UC_INDUCES:
                case UC_REPRESSES:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 110:
                case 113:
                case 114:
                case 116:
                case 117:
                case 120:
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
                case 149:
                case 152:
                case 153:
                case 155:
                case 156:
                case 159:
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
                case 175:
                case 176:
                    {
                    alt52=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 52, 1, input);

                    throw nvae;

                }

                }
                break;
            case NUMBER:
                {
                switch ( input.LA(2) ) {
                case 109:
                case 148:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 5, input);

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
                case 134:
                case 173:
                    {
                    alt52=3;
                    }
                    break;
                case 118:
                case 157:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 7, input);

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
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 8, input);

                        throw nvae;

                    }

                    }
                    break;
                case 121:
                case 160:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 9, input);

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
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 10, input);

                        throw nvae;

                    }

                    }
                    break;
                case 111:
                case 150:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 11, input);

                        throw nvae;

                    }

                    }
                    break;
                case 112:
                case 151:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 12, input);

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
                        alt52=1;
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
                        alt52=1;
                        }
                        break;
                    case LEFTP:
                    case MINUS:
                    case REAL:
                    case STRING:
                        {
                        alt52=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 13, input);

                        throw nvae;

                    }

                    }
                    break;
                case LC_INDUCES:
                case LC_REPRESSES:
                case UC_INDUCES:
                case UC_REPRESSES:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 110:
                case 113:
                case 114:
                case 116:
                case 117:
                case 120:
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
                case 149:
                case 152:
                case 153:
                case 155:
                case 156:
                case 159:
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
                case 175:
                case 176:
                    {
                    alt52=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 52, 2, input);

                    throw nvae;

                }

                }
                break;
            case LC_INDUCES:
            case LC_REPRESSES:
            case LEFTSBR:
            case UC_INDUCES:
            case UC_REPRESSES:
            case 99:
            case 100:
            case 101:
            case 102:
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
            case 174:
            case 175:
            case 176:
                {
                alt52=1;
                }
                break;
            case LEFTP:
            case MINUS:
            case REAL:
            case STRING:
                {
                alt52=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }

            switch (alt52) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1601:4: (lhs= operand[defer] )? op= ruleOperator[defer] (rhs= operand[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1601:4: (lhs= operand[defer] )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==ID||LA50_0==LEFTSBR||LA50_0==NUMBER) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1601:5: lhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate2865);
                            lhs=operand(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, lhs.getTree());


                            addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_ruleOperator_in_predicate2875);
                    op=ruleOperator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, op.getTree());


                    addToken((op!=null?input.toString(op.start,op.stop):null));	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1605:5: (rhs= operand[defer] )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==ID||LA51_0==LEFTSBR||LA51_0==NUMBER) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1605:6: rhs= operand[defer]
                            {
                            pushFollow(FOLLOW_operand_in_predicate2884);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1619:4: i= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    i=(Token)match(input,ID,FOLLOW_ID_in_predicate2898); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1636:4: exp= expressionRule[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expressionRule_in_predicate2907);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1643:1: operand[boolean defer] returns [ArrangementOperand o] : (i= ID |n= NUMBER | '[' n= NUMBER ']' ) ;
    public final EugeneParser.operand_return operand(boolean defer) throws RecognitionException {
        EugeneParser.operand_return retval = new EugeneParser.operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i=null;
        Token n=null;
        Token char_literal135=null;
        Token char_literal136=null;

        Object i_tree=null;
        Object n_tree=null;
        Object char_literal135_tree=null;
        Object char_literal136_tree=null;


        NamedElement element = null;
        int constant = -1;
        int index = -1;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1650:2: ( (i= ID |n= NUMBER | '[' n= NUMBER ']' ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1650:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1650:4: (i= ID |n= NUMBER | '[' n= NUMBER ']' )
            int alt53=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt53=1;
                }
                break;
            case NUMBER:
                {
                alt53=2;
                }
                break;
            case LEFTSBR:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1650:5: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_operand2938); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1662:4: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand2947); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1667:4: '[' n= NUMBER ']'
                    {
                    char_literal135=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand2954); 
                    char_literal135_tree = 
                    (Object)adaptor.create(char_literal135)
                    ;
                    adaptor.addChild(root_0, char_literal135_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_operand2958); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    char_literal136=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand2960); 
                    char_literal136_tree = 
                    (Object)adaptor.create(char_literal136)
                    ;
                    adaptor.addChild(root_0, char_literal136_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1686:1: expressionRule[boolean defer] returns [Predicate p] : lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] ;
    public final EugeneParser.expressionRule_return expressionRule(boolean defer) throws RecognitionException {
        EugeneParser.expressionRule_return retval = new EugeneParser.expressionRule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expression_return lhs =null;

        EugeneParser.exp_op_return op =null;

        EugeneParser.expression_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1688:2: (lhs= expression[defer] op= exp_op[defer] rhs= expression[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1688:4: lhs= expression[defer] op= exp_op[defer] rhs= expression[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expression_in_expressionRule2986);
            lhs=expression(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_exp_op_in_expressionRule2991);
            op=exp_op(defer);

            state._fsp--;

            adaptor.addChild(root_0, op.getTree());

            pushFollow(FOLLOW_expression_in_expressionRule2996);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1699:1: expression[boolean defer] returns [Expression exp] : (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP );
    public final EugeneParser.expression_return expression(boolean defer) throws RecognitionException {
        EugeneParser.expression_return retval = new EugeneParser.expression_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token LEFTP137=null;
        Token RIGHTP139=null;
        EugeneParser.exp_operand_return lhs =null;

        EugeneParser.exp_operator_return expop =null;

        EugeneParser.expression_return rhs =null;

        EugeneParser.expression_return expression138 =null;


        Object LEFTP137_tree=null;
        Object RIGHTP139_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1701:2: (lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )? | LEFTP expression[defer] RIGHTP )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==ID||LA55_0==MINUS||LA55_0==NUMBER||LA55_0==REAL||LA55_0==STRING) ) {
                alt55=1;
            }
            else if ( (LA55_0==LEFTP) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }
            switch (alt55) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1701:4: lhs= exp_operand[defer] (expop= exp_operator[defer] rhs= expression[defer] )?
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_exp_operand_in_expression3020);
                    lhs=exp_operand(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, lhs.getTree());


                    if(!defer) {
                        retval.exp = new Expression((lhs!=null?lhs.eop:null), null, null);
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1705:4: (expop= exp_operator[defer] rhs= expression[defer] )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==DIV||LA54_0==MINUS||LA54_0==MULT||LA54_0==PLUS) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1705:6: expop= exp_operator[defer] rhs= expression[defer]
                            {
                            pushFollow(FOLLOW_exp_operator_in_expression3029);
                            expop=exp_operator(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, expop.getTree());

                            pushFollow(FOLLOW_expression_in_expression3034);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1710:4: LEFTP expression[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTP137=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_expression3046); 
                    LEFTP137_tree = 
                    (Object)adaptor.create(LEFTP137)
                    ;
                    adaptor.addChild(root_0, LEFTP137_tree);


                    pushFollow(FOLLOW_expression_in_expression3048);
                    expression138=expression(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expression138.getTree());

                    RIGHTP139=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_expression3051); 
                    RIGHTP139_tree = 
                    (Object)adaptor.create(RIGHTP139)
                    ;
                    adaptor.addChild(root_0, RIGHTP139_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1717:1: exp_operator[boolean defer] returns [Expression.ExpOp op] : ( PLUS | MINUS | MULT | DIV );
    public final EugeneParser.exp_operator_return exp_operator(boolean defer) throws RecognitionException {
        EugeneParser.exp_operator_return retval = new EugeneParser.exp_operator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLUS140=null;
        Token MINUS141=null;
        Token MULT142=null;
        Token DIV143=null;

        Object PLUS140_tree=null;
        Object MINUS141_tree=null;
        Object MULT142_tree=null;
        Object DIV143_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1719:2: ( PLUS | MINUS | MULT | DIV )
            int alt56=4;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt56=1;
                }
                break;
            case MINUS:
                {
                alt56=2;
                }
                break;
            case MULT:
                {
                alt56=3;
                }
                break;
            case DIV:
                {
                alt56=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }

            switch (alt56) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1719:4: PLUS
                    {
                    root_0 = (Object)adaptor.nil();


                    PLUS140=(Token)match(input,PLUS,FOLLOW_PLUS_in_exp_operator3070); 
                    PLUS140_tree = 
                    (Object)adaptor.create(PLUS140)
                    ;
                    adaptor.addChild(root_0, PLUS140_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.PLUS;	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1724:4: MINUS
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS141=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operator3078); 
                    MINUS141_tree = 
                    (Object)adaptor.create(MINUS141)
                    ;
                    adaptor.addChild(root_0, MINUS141_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MINUS;	
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1729:4: MULT
                    {
                    root_0 = (Object)adaptor.nil();


                    MULT142=(Token)match(input,MULT,FOLLOW_MULT_in_exp_operator3085); 
                    MULT142_tree = 
                    (Object)adaptor.create(MULT142)
                    ;
                    adaptor.addChild(root_0, MULT142_tree);



                    if(!defer) {	
                        retval.op = Expression.ExpOp.MULT;	
                    }
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1734:4: DIV
                    {
                    root_0 = (Object)adaptor.nil();


                    DIV143=(Token)match(input,DIV,FOLLOW_DIV_in_exp_operator3092); 
                    DIV143_tree = 
                    (Object)adaptor.create(DIV143)
                    ;
                    adaptor.addChild(root_0, DIV143_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1741:1: exp_operand[boolean defer] returns [ExpressionOperand eop] : ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING );
    public final EugeneParser.exp_operand_return exp_operand(boolean defer) throws RecognitionException {
        EugeneParser.exp_operand_return retval = new EugeneParser.exp_operand_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token i1=null;
        Token i2=null;
        Token n=null;
        Token r=null;
        Token s=null;
        Token DOT144=null;
        Token LEFTSBR145=null;
        Token RIGHTSBR146=null;
        Token MINUS147=null;
        Token MINUS148=null;

        Object i1_tree=null;
        Object i2_tree=null;
        Object n_tree=null;
        Object r_tree=null;
        Object s_tree=null;
        Object DOT144_tree=null;
        Object LEFTSBR145_tree=null;
        Object RIGHTSBR146_tree=null;
        Object MINUS147_tree=null;
        Object MINUS148_tree=null;


        List<NamedElement> elements = null;
        NamedElement ne = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1747:2: ( (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )* |n= NUMBER | MINUS n= NUMBER |r= REAL | MINUS r= REAL |s= STRING )
            int alt59=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt59=1;
                }
                break;
            case NUMBER:
                {
                alt59=2;
                }
                break;
            case MINUS:
                {
                int LA59_3 = input.LA(2);

                if ( (LA59_3==NUMBER) ) {
                    alt59=3;
                }
                else if ( (LA59_3==REAL) ) {
                    alt59=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 59, 3, input);

                    throw nvae;

                }
                }
                break;
            case REAL:
                {
                alt59=4;
                }
                break;
            case STRING:
                {
                alt59=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;

            }

            switch (alt59) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1747:4: (i1= ID DOT )* i2= ID ( LEFTSBR n= NUMBER RIGHTSBR )*
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1747:4: (i1= ID DOT )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==ID) ) {
                            int LA57_1 = input.LA(2);

                            if ( (LA57_1==DOT) ) {
                                alt57=1;
                            }


                        }


                        switch (alt57) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1747:5: i1= ID DOT
                    	    {
                    	    i1=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3122); 
                    	    i1_tree = 
                    	    (Object)adaptor.create(i1)
                    	    ;
                    	    adaptor.addChild(root_0, i1_tree);


                    	    DOT144=(Token)match(input,DOT,FOLLOW_DOT_in_exp_operand3124); 
                    	    DOT144_tree = 
                    	    (Object)adaptor.create(DOT144)
                    	    ;
                    	    adaptor.addChild(root_0, DOT144_tree);



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
                    	    break loop57;
                        }
                    } while (true);


                    i2=(Token)match(input,ID,FOLLOW_ID_in_exp_operand3133); 
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
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1815:5: ( LEFTSBR n= NUMBER RIGHTSBR )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==LEFTSBR) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1815:6: LEFTSBR n= NUMBER RIGHTSBR
                    	    {
                    	    LEFTSBR145=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_exp_operand3139); 
                    	    LEFTSBR145_tree = 
                    	    (Object)adaptor.create(LEFTSBR145)
                    	    ;
                    	    adaptor.addChild(root_0, LEFTSBR145_tree);


                    	    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3143); 
                    	    n_tree = 
                    	    (Object)adaptor.create(n)
                    	    ;
                    	    adaptor.addChild(root_0, n_tree);


                    	    RIGHTSBR146=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_exp_operand3145); 
                    	    RIGHTSBR146_tree = 
                    	    (Object)adaptor.create(RIGHTSBR146)
                    	    ;
                    	    adaptor.addChild(root_0, RIGHTSBR146_tree);



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
                    	    break loop58;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1826:4: n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3157); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1833:4: MINUS n= NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS147=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3164); 
                    MINUS147_tree = 
                    (Object)adaptor.create(MINUS147)
                    ;
                    adaptor.addChild(root_0, MINUS147_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_exp_operand3168); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1840:4: r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3177); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1847:4: MINUS r= REAL
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS148=(Token)match(input,MINUS,FOLLOW_MINUS_in_exp_operand3184); 
                    MINUS148_tree = 
                    (Object)adaptor.create(MINUS148)
                    ;
                    adaptor.addChild(root_0, MINUS148_tree);


                    r=(Token)match(input,REAL,FOLLOW_REAL_in_exp_operand3188); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1854:4: s= STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    s=(Token)match(input,STRING,FOLLOW_STRING_in_exp_operand3197); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1864:1: regexp[boolean defer] :;
    public final EugeneParser.regexp_return regexp(boolean defer) throws RecognitionException {
        EugeneParser.regexp_return retval = new EugeneParser.regexp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1865:2: ()
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1866:2: 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1868:1: exp_op[boolean defer] : relationalOperators ;
    public final EugeneParser.exp_op_return exp_op(boolean defer) throws RecognitionException {
        EugeneParser.exp_op_return retval = new EugeneParser.exp_op_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.relationalOperators_return relationalOperators149 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1869:2: ( relationalOperators )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1869:4: relationalOperators
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_relationalOperators_in_exp_op3224);
            relationalOperators149=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, relationalOperators149.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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


    public static class interactionDeclaration_return extends ParserRuleReturnScope {
        public Interaction ia;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interactionDeclaration"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1877:1: interactionDeclaration[boolean defer] returns [Interaction ia] : (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP );
    public final EugeneParser.interactionDeclaration_return interactionDeclaration(boolean defer) throws RecognitionException {
        EugeneParser.interactionDeclaration_return retval = new EugeneParser.interactionDeclaration_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token name=null;
        Token INTERACTION150=null;
        Token LEFTP151=null;
        Token RIGHTP152=null;
        EugeneParser.interaction_return i1 =null;

        EugeneParser.interaction_return i2 =null;


        Object name_tree=null;
        Object INTERACTION150_tree=null;
        Object LEFTP151_tree=null;
        Object RIGHTP152_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1879:2: (i1= interaction[defer, null] | INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==ID) ) {
                alt60=1;
            }
            else if ( (LA60_0==INTERACTION) ) {
                alt60=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;

            }
            switch (alt60) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1879:4: i1= interaction[defer, null]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3250);
                    i1=interaction(defer, null);

                    state._fsp--;

                    adaptor.addChild(root_0, i1.getTree());


                    if(!defer) {
                        retval.ia = (i1!=null?i1.ia:null);
                    }


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1884:4: INTERACTION name= ID LEFTP i2= interaction[defer, $name.text] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    INTERACTION150=(Token)match(input,INTERACTION,FOLLOW_INTERACTION_in_interactionDeclaration3258); 
                    INTERACTION150_tree = 
                    (Object)adaptor.create(INTERACTION150)
                    ;
                    adaptor.addChild(root_0, INTERACTION150_tree);


                    name=(Token)match(input,ID,FOLLOW_ID_in_interactionDeclaration3262); 
                    name_tree = 
                    (Object)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    LEFTP151=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interactionDeclaration3264); 
                    LEFTP151_tree = 
                    (Object)adaptor.create(LEFTP151)
                    ;
                    adaptor.addChild(root_0, LEFTP151_tree);


                    pushFollow(FOLLOW_interaction_in_interactionDeclaration3268);
                    i2=interaction(defer, (name!=null?name.getText():null));

                    state._fsp--;

                    adaptor.addChild(root_0, i2.getTree());

                    RIGHTP152=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interactionDeclaration3271); 
                    RIGHTP152_tree = 
                    (Object)adaptor.create(RIGHTP152)
                    ;
                    adaptor.addChild(root_0, RIGHTP152_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1891:1: interaction[boolean defer, String name] returns [Interaction ia] : (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP );
    public final EugeneParser.interaction_return interaction(boolean defer, String name) throws RecognitionException {
        EugeneParser.interaction_return retval = new EugeneParser.interaction_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token lhs1=null;
        Token rhs1=null;
        Token lhs2=null;
        Token LEFTP153=null;
        Token RIGHTP154=null;
        EugeneParser.interactionType_return t1 =null;

        EugeneParser.interactionType_return t2 =null;

        EugeneParser.interaction_return rhs2 =null;


        Object lhs1_tree=null;
        Object rhs1_tree=null;
        Object lhs2_tree=null;
        Object LEFTP153_tree=null;
        Object RIGHTP154_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1893:2: (lhs1= ID t1= interactionType[defer] rhs1= ID |lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==ID) ) {
                int LA61_1 = input.LA(2);

                if ( (LA61_1==LC_REPRESSES||LA61_1==UC_REPRESSES) ) {
                    int LA61_2 = input.LA(3);

                    if ( (LA61_2==ID) ) {
                        alt61=1;
                    }
                    else if ( (LA61_2==LEFTP) ) {
                        alt61=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 61, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA61_1==LC_INDUCES||LA61_1==UC_INDUCES) ) {
                    int LA61_3 = input.LA(3);

                    if ( (LA61_3==ID) ) {
                        alt61=1;
                    }
                    else if ( (LA61_3==LEFTP) ) {
                        alt61=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 61, 3, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;

            }
            switch (alt61) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1893:4: lhs1= ID t1= interactionType[defer] rhs1= ID
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3294); 
                    lhs1_tree = 
                    (Object)adaptor.create(lhs1)
                    ;
                    adaptor.addChild(root_0, lhs1_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3298);
                    t1=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t1.getTree());

                    rhs1=(Token)match(input,ID,FOLLOW_ID_in_interaction3303); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1902:4: lhs2= ID t2= interactionType[defer] LEFTP rhs2= interaction[defer, name] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    lhs2=(Token)match(input,ID,FOLLOW_ID_in_interaction3312); 
                    lhs2_tree = 
                    (Object)adaptor.create(lhs2)
                    ;
                    adaptor.addChild(root_0, lhs2_tree);


                    pushFollow(FOLLOW_interactionType_in_interaction3316);
                    t2=interactionType(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, t2.getTree());

                    LEFTP153=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_interaction3319); 
                    LEFTP153_tree = 
                    (Object)adaptor.create(LEFTP153)
                    ;
                    adaptor.addChild(root_0, LEFTP153_tree);


                    pushFollow(FOLLOW_interaction_in_interaction3323);
                    rhs2=interaction(defer, name);

                    state._fsp--;

                    adaptor.addChild(root_0, rhs2.getTree());

                    RIGHTP154=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_interaction3326); 
                    RIGHTP154_tree = 
                    (Object)adaptor.create(RIGHTP154)
                    ;
                    adaptor.addChild(root_0, RIGHTP154_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1913:1: interactionType[boolean defer] returns [Interaction.InteractionType type] : ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) );
    public final EugeneParser.interactionType_return interactionType(boolean defer) throws RecognitionException {
        EugeneParser.interactionType_return retval = new EugeneParser.interactionType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set155=null;
        Token set156=null;

        Object set155_tree=null;
        Object set156_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1915:2: ( ( UC_REPRESSES | LC_REPRESSES ) | ( UC_INDUCES | LC_INDUCES ) )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==LC_REPRESSES||LA62_0==UC_REPRESSES) ) {
                alt62=1;
            }
            else if ( (LA62_0==LC_INDUCES||LA62_0==UC_INDUCES) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }
            switch (alt62) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1915:4: ( UC_REPRESSES | LC_REPRESSES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set155=(Token)input.LT(1);

                    if ( input.LA(1)==LC_REPRESSES||input.LA(1)==UC_REPRESSES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set155)
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1920:4: ( UC_INDUCES | LC_INDUCES )
                    {
                    root_0 = (Object)adaptor.nil();


                    set156=(Token)input.LT(1);

                    if ( input.LA(1)==LC_INDUCES||input.LA(1)==UC_INDUCES ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set156)
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1936:1: functionCall[boolean defer] returns [NamedElement e] : (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP ;
    public final EugeneParser.functionCall_return functionCall(boolean defer) throws RecognitionException {
        EugeneParser.functionCall_return retval = new EugeneParser.functionCall_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token pr=null;
        Token pe=null;
        Token idToken=null;
        Token LEFTP157=null;
        Token RIGHTP158=null;

        Object pr_tree=null;
        Object pe_tree=null;
        Object idToken_tree=null;
        Object LEFTP157_tree=null;
        Object RIGHTP158_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1938:2: ( (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1938:4: (pr= PRODUCT |pe= PERMUTE ) LEFTP idToken= ID RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1938:4: (pr= PRODUCT |pe= PERMUTE )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==PRODUCT) ) {
                alt63=1;
            }
            else if ( (LA63_0==PERMUTE) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1938:5: pr= PRODUCT
                    {
                    pr=(Token)match(input,PRODUCT,FOLLOW_PRODUCT_in_functionCall3393); 
                    pr_tree = 
                    (Object)adaptor.create(pr)
                    ;
                    adaptor.addChild(root_0, pr_tree);


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1938:16: pe= PERMUTE
                    {
                    pe=(Token)match(input,PERMUTE,FOLLOW_PERMUTE_in_functionCall3397); 
                    pe_tree = 
                    (Object)adaptor.create(pe)
                    ;
                    adaptor.addChild(root_0, pe_tree);


                    }
                    break;

            }


            LEFTP157=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_functionCall3400); 
            LEFTP157_tree = 
            (Object)adaptor.create(LEFTP157)
            ;
            adaptor.addChild(root_0, LEFTP157_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_functionCall3404); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP158=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_functionCall3406); 
            RIGHTP158_tree = 
            (Object)adaptor.create(RIGHTP158)
            ;
            adaptor.addChild(root_0, RIGHTP158_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1965:1: printStatement[boolean defer] : ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP );
    public final EugeneParser.printStatement_return printStatement(boolean defer) throws RecognitionException {
        EugeneParser.printStatement_return retval = new EugeneParser.printStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PRINTLN159=null;
        Token LEFTP160=null;
        Token RIGHTP161=null;
        Token PRINT162=null;
        Token LEFTP163=null;
        Token RIGHTP164=null;
        EugeneParser.toPrint_return tp =null;


        Object PRINTLN159_tree=null;
        Object LEFTP160_tree=null;
        Object RIGHTP161_tree=null;
        Object PRINT162_tree=null;
        Object LEFTP163_tree=null;
        Object RIGHTP164_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1966:2: ( PRINTLN LEFTP tp= toPrint[defer] RIGHTP | PRINT LEFTP tp= toPrint[defer] RIGHTP )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==PRINTLN) ) {
                alt64=1;
            }
            else if ( (LA64_0==PRINT) ) {
                alt64=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;

            }
            switch (alt64) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1966:4: PRINTLN LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINTLN159=(Token)match(input,PRINTLN,FOLLOW_PRINTLN_in_printStatement3424); 
                    PRINTLN159_tree = 
                    (Object)adaptor.create(PRINTLN159)
                    ;
                    adaptor.addChild(root_0, PRINTLN159_tree);


                    LEFTP160=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3426); 
                    LEFTP160_tree = 
                    (Object)adaptor.create(LEFTP160)
                    ;
                    adaptor.addChild(root_0, LEFTP160_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3430);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP161=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3433); 
                    RIGHTP161_tree = 
                    (Object)adaptor.create(RIGHTP161)
                    ;
                    adaptor.addChild(root_0, RIGHTP161_tree);



                    if(!defer) {
                        if(null != (tp!=null?tp.sb:null)) {
                            try {
                                this.writer.write((tp!=null?tp.sb:null).toString());
                                this.writer.write(LINE_FEED);
                                this.writer.flush();
                            } catch(IOException ioe) {
                                printError(ioe.getMessage());
                            }
                        }
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1979:4: PRINT LEFTP tp= toPrint[defer] RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    PRINT162=(Token)match(input,PRINT,FOLLOW_PRINT_in_printStatement3440); 
                    PRINT162_tree = 
                    (Object)adaptor.create(PRINT162)
                    ;
                    adaptor.addChild(root_0, PRINT162_tree);


                    LEFTP163=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_printStatement3442); 
                    LEFTP163_tree = 
                    (Object)adaptor.create(LEFTP163)
                    ;
                    adaptor.addChild(root_0, LEFTP163_tree);


                    pushFollow(FOLLOW_toPrint_in_printStatement3446);
                    tp=toPrint(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, tp.getTree());

                    RIGHTP164=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_printStatement3449); 
                    RIGHTP164_tree = 
                    (Object)adaptor.create(RIGHTP164)
                    ;
                    adaptor.addChild(root_0, RIGHTP164_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1993:1: toPrint[boolean defer] returns [StringBuilder sb] : exp= expr[defer] tpp= toPrint_prime[defer] ;
    public final EugeneParser.toPrint_return toPrint(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_return retval = new EugeneParser.toPrint_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.expr_return exp =null;

        EugeneParser.toPrint_prime_return tpp =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1995:2: (exp= expr[defer] tpp= toPrint_prime[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:1995:4: exp= expr[defer] tpp= toPrint_prime[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_toPrint3470);
            exp=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, exp.getTree());

            pushFollow(FOLLOW_toPrint_prime_in_toPrint3475);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2008:1: toPrint_prime[boolean defer] returns [StringBuilder sb] : (| COMMA tp= toPrint[defer] );
    public final EugeneParser.toPrint_prime_return toPrint_prime(boolean defer) throws RecognitionException {
        EugeneParser.toPrint_prime_return retval = new EugeneParser.toPrint_prime_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA165=null;
        EugeneParser.toPrint_return tp =null;


        Object COMMA165_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2010:2: (| COMMA tp= toPrint[defer] )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==RIGHTP) ) {
                alt65=1;
            }
            else if ( (LA65_0==COMMA) ) {
                alt65=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;

            }
            switch (alt65) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2010:4: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.sb = new StringBuilder();
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2015:4: COMMA tp= toPrint[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    COMMA165=(Token)match(input,COMMA,FOLLOW_COMMA_in_toPrint_prime3501); 
                    COMMA165_tree = 
                    (Object)adaptor.create(COMMA165)
                    ;
                    adaptor.addChild(root_0, COMMA165_tree);


                    pushFollow(FOLLOW_toPrint_in_toPrint_prime3505);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2027:1: imperativeStatements[boolean defer] : ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] );
    public final EugeneParser.imperativeStatements_return imperativeStatements(boolean defer) throws RecognitionException {
        EugeneParser.imperativeStatements_return retval = new EugeneParser.imperativeStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.ifStatement_return ifStatement166 =null;

        EugeneParser.forall_iterator_return forall_iterator167 =null;

        EugeneParser.for_loop_return for_loop168 =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2028:2: ( ifStatement[defer] | forall_iterator[defer] | for_loop[defer] )
            int alt66=3;
            switch ( input.LA(1) ) {
            case LC_IF:
            case UC_IF:
                {
                alt66=1;
                }
                break;
            case LC_FORALL:
            case UC_FORALL:
                {
                alt66=2;
                }
                break;
            case LC_FOR:
            case UC_FOR:
                {
                alt66=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }

            switch (alt66) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2028:4: ifStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_ifStatement_in_imperativeStatements3525);
                    ifStatement166=ifStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, ifStatement166.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2029:4: forall_iterator[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_forall_iterator_in_imperativeStatements3531);
                    forall_iterator167=forall_iterator(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, forall_iterator167.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2030:4: for_loop[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_for_loop_in_imperativeStatements3537);
                    for_loop168=for_loop(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, for_loop168.getTree());

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2033:1: ifStatement[boolean defer] : ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? ;
    public final EugeneParser.ifStatement_return ifStatement(boolean defer) throws RecognitionException {
        EugeneParser.ifStatement_return retval = new EugeneParser.ifStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set169=null;
        Token LEFTP170=null;
        Token RIGHTP171=null;
        Token LEFTCUR172=null;
        Token RIGHTCUR173=null;
        Token set174=null;
        Token LEFTP175=null;
        Token RIGHTP176=null;
        Token LEFTCUR177=null;
        Token RIGHTCUR178=null;
        Token set179=null;
        Token LEFTCUR180=null;
        Token RIGHTCUR181=null;
        EugeneParser.imp_condition_return co =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set169_tree=null;
        Object LEFTP170_tree=null;
        Object RIGHTP171_tree=null;
        Object LEFTCUR172_tree=null;
        Object RIGHTCUR173_tree=null;
        Object set174_tree=null;
        Object LEFTP175_tree=null;
        Object RIGHTP176_tree=null;
        Object LEFTCUR177_tree=null;
        Object RIGHTCUR178_tree=null;
        Object set179_tree=null;
        Object LEFTCUR180_tree=null;
        Object RIGHTCUR181_tree=null;


        boolean bExecuted = false;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2037:2: ( ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2041:3: ( UC_IF | LC_IF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )* ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            {
            root_0 = (Object)adaptor.nil();


            set169=(Token)input.LT(1);

            if ( input.LA(1)==LC_IF||input.LA(1)==UC_IF ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set169)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP170=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3569); 
            LEFTP170_tree = 
            (Object)adaptor.create(LEFTP170)
            ;
            adaptor.addChild(root_0, LEFTP170_tree);


            pushFollow(FOLLOW_imp_condition_in_ifStatement3573);
            co=imp_condition(defer);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            RIGHTP171=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3576); 
            RIGHTP171_tree = 
            (Object)adaptor.create(RIGHTP171)
            ;
            adaptor.addChild(root_0, RIGHTP171_tree);


            LEFTCUR172=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3578); 
            LEFTCUR172_tree = 
            (Object)adaptor.create(LEFTCUR172)
            ;
            adaptor.addChild(root_0, LEFTCUR172_tree);


            pushFollow(FOLLOW_listOfStatements_in_ifStatement3586);
            stmts=listOfStatements(true);

            state._fsp--;

            adaptor.addChild(root_0, stmts.getTree());

            RIGHTCUR173=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3589); 
            RIGHTCUR173_tree = 
            (Object)adaptor.create(RIGHTCUR173)
            ;
            adaptor.addChild(root_0, RIGHTCUR173_tree);



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
            		

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2064:3: ( ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==LC_ELSEIF||LA67_0==UC_ELSEIF) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2064:5: ( UC_ELSEIF | LC_ELSEIF ) LEFTP co= imp_condition[defer] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            	    {
            	    set174=(Token)input.LT(1);

            	    if ( input.LA(1)==LC_ELSEIF||input.LA(1)==UC_ELSEIF ) {
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


            	    LEFTP175=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_ifStatement3610); 
            	    LEFTP175_tree = 
            	    (Object)adaptor.create(LEFTP175)
            	    ;
            	    adaptor.addChild(root_0, LEFTP175_tree);


            	    pushFollow(FOLLOW_imp_condition_in_ifStatement3614);
            	    co=imp_condition(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, co.getTree());

            	    RIGHTP176=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_ifStatement3617); 
            	    RIGHTP176_tree = 
            	    (Object)adaptor.create(RIGHTP176)
            	    ;
            	    adaptor.addChild(root_0, RIGHTP176_tree);


            	    LEFTCUR177=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3619); 
            	    LEFTCUR177_tree = 
            	    (Object)adaptor.create(LEFTCUR177)
            	    ;
            	    adaptor.addChild(root_0, LEFTCUR177_tree);


            	    pushFollow(FOLLOW_listOfStatements_in_ifStatement3627);
            	    stmts=listOfStatements(true);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmts.getTree());

            	    RIGHTCUR178=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3630); 
            	    RIGHTCUR178_tree = 
            	    (Object)adaptor.create(RIGHTCUR178)
            	    ;
            	    adaptor.addChild(root_0, RIGHTCUR178_tree);



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
            	    break loop67;
                }
            } while (true);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2087:3: ( ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==LC_ELSE||LA68_0==UC_ELSE) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2087:4: ( UC_ELSE | LC_ELSE ) LEFTCUR stmts= listOfStatements[true] RIGHTCUR
                    {
                    set179=(Token)input.LT(1);

                    if ( input.LA(1)==LC_ELSE||input.LA(1)==UC_ELSE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(set179)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    LEFTCUR180=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_ifStatement3652); 
                    LEFTCUR180_tree = 
                    (Object)adaptor.create(LEFTCUR180)
                    ;
                    adaptor.addChild(root_0, LEFTCUR180_tree);


                    pushFollow(FOLLOW_listOfStatements_in_ifStatement3660);
                    stmts=listOfStatements(true);

                    state._fsp--;

                    adaptor.addChild(root_0, stmts.getTree());

                    RIGHTCUR181=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_ifStatement3663); 
                    RIGHTCUR181_tree = 
                    (Object)adaptor.create(RIGHTCUR181)
                    ;
                    adaptor.addChild(root_0, RIGHTCUR181_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2103:1: imp_condition[boolean defer] returns [boolean bTrue] : lhs= atom[defer] ro= relationalOperators rhs= atom[defer] ;
    public final EugeneParser.imp_condition_return imp_condition(boolean defer) throws RecognitionException {
        EugeneParser.imp_condition_return retval = new EugeneParser.imp_condition_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.atom_return lhs =null;

        EugeneParser.relationalOperators_return ro =null;

        EugeneParser.atom_return rhs =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2105:2: (lhs= atom[defer] ro= relationalOperators rhs= atom[defer] )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2105:4: lhs= atom[defer] ro= relationalOperators rhs= atom[defer]
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_imp_condition3687);
            lhs=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, lhs.getTree());

            pushFollow(FOLLOW_relationalOperators_in_imp_condition3692);
            ro=relationalOperators();

            state._fsp--;

            adaptor.addChild(root_0, ro.getTree());

            pushFollow(FOLLOW_atom_in_imp_condition3696);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2119:1: forall_iterator[boolean defer] : ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR ;
    public final EugeneParser.forall_iterator_return forall_iterator(boolean defer) throws RecognitionException {
        EugeneParser.forall_iterator_return retval = new EugeneParser.forall_iterator_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token it=null;
        Token i=null;
        Token set182=null;
        Token COLON183=null;
        Token LEFTCUR184=null;
        Token RIGHTCUR186=null;
        EugeneParser.listOfStatements_return listOfStatements185 =null;


        Object it_tree=null;
        Object i_tree=null;
        Object set182_tree=null;
        Object COLON183_tree=null;
        Object LEFTCUR184_tree=null;
        Object RIGHTCUR186_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2120:2: ( ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2120:4: ( UC_FORALL | LC_FORALL ) (it= ID COLON )? i= ID LEFTCUR listOfStatements[defer] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set182=(Token)input.LT(1);

            if ( input.LA(1)==LC_FORALL||input.LA(1)==UC_FORALL ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set182)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2120:26: (it= ID COLON )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==ID) ) {
                int LA69_1 = input.LA(2);

                if ( (LA69_1==COLON) ) {
                    alt69=1;
                }
            }
            switch (alt69) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2120:27: it= ID COLON
                    {
                    it=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3721); 
                    it_tree = 
                    (Object)adaptor.create(it)
                    ;
                    adaptor.addChild(root_0, it_tree);


                    COLON183=(Token)match(input,COLON,FOLLOW_COLON_in_forall_iterator3723); 
                    COLON183_tree = 
                    (Object)adaptor.create(COLON183)
                    ;
                    adaptor.addChild(root_0, COLON183_tree);


                    }
                    break;

            }


            i=(Token)match(input,ID,FOLLOW_ID_in_forall_iterator3729); 
            i_tree = 
            (Object)adaptor.create(i)
            ;
            adaptor.addChild(root_0, i_tree);


            LEFTCUR184=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_forall_iterator3731); 
            LEFTCUR184_tree = 
            (Object)adaptor.create(LEFTCUR184)
            ;
            adaptor.addChild(root_0, LEFTCUR184_tree);


            pushFollow(FOLLOW_listOfStatements_in_forall_iterator3736);
            listOfStatements185=listOfStatements(defer);

            state._fsp--;

            adaptor.addChild(root_0, listOfStatements185.getTree());


            if(!defer) {

            }			
            	

            RIGHTCUR186=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_forall_iterator3743); 
            RIGHTCUR186_tree = 
            (Object)adaptor.create(RIGHTCUR186)
            ;
            adaptor.addChild(root_0, RIGHTCUR186_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2129:1: for_loop[boolean defer] : ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR ;
    public final EugeneParser.for_loop_return for_loop(boolean defer) throws RecognitionException {
        EugeneParser.for_loop_return retval = new EugeneParser.for_loop_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set187=null;
        Token LEFTP188=null;
        Token SEMIC189=null;
        Token SEMIC190=null;
        Token RIGHTP191=null;
        Token LEFTCUR192=null;
        Token RIGHTCUR193=null;
        EugeneParser.variableDeclaration_return ds =null;

        EugeneParser.imp_condition_return co =null;

        EugeneParser.assignment_return as =null;

        EugeneParser.listOfStatements_return stmts =null;


        Object set187_tree=null;
        Object LEFTP188_tree=null;
        Object SEMIC189_tree=null;
        Object SEMIC190_tree=null;
        Object RIGHTP191_tree=null;
        Object LEFTCUR192_tree=null;
        Object RIGHTCUR193_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2130:2: ( ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2130:4: ( UC_FOR | LC_FOR ) LEFTP ds= variableDeclaration[true] SEMIC co= imp_condition[true] SEMIC as= assignment[true] RIGHTP LEFTCUR stmts= listOfStatements[true] RIGHTCUR
            {
            root_0 = (Object)adaptor.nil();


            set187=(Token)input.LT(1);

            if ( input.LA(1)==LC_FOR||input.LA(1)==UC_FOR ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set187)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP188=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_for_loop3761); 
            LEFTP188_tree = 
            (Object)adaptor.create(LEFTP188)
            ;
            adaptor.addChild(root_0, LEFTP188_tree);


            pushFollow(FOLLOW_variableDeclaration_in_for_loop3765);
            ds=variableDeclaration(true);

            state._fsp--;

            adaptor.addChild(root_0, ds.getTree());

            SEMIC189=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop3768); 
            SEMIC189_tree = 
            (Object)adaptor.create(SEMIC189)
            ;
            adaptor.addChild(root_0, SEMIC189_tree);


            pushFollow(FOLLOW_imp_condition_in_for_loop3772);
            co=imp_condition(true);

            state._fsp--;

            adaptor.addChild(root_0, co.getTree());

            SEMIC190=(Token)match(input,SEMIC,FOLLOW_SEMIC_in_for_loop3775); 
            SEMIC190_tree = 
            (Object)adaptor.create(SEMIC190)
            ;
            adaptor.addChild(root_0, SEMIC190_tree);


            pushFollow(FOLLOW_assignment_in_for_loop3779);
            as=assignment(true);

            state._fsp--;

            adaptor.addChild(root_0, as.getTree());

            RIGHTP191=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_for_loop3782); 
            RIGHTP191_tree = 
            (Object)adaptor.create(RIGHTP191)
            ;
            adaptor.addChild(root_0, RIGHTP191_tree);


            LEFTCUR192=(Token)match(input,LEFTCUR,FOLLOW_LEFTCUR_in_for_loop3784); 
            LEFTCUR192_tree = 
            (Object)adaptor.create(LEFTCUR192)
            ;
            adaptor.addChild(root_0, LEFTCUR192_tree);


            pushFollow(FOLLOW_listOfStatements_in_for_loop3792);
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
            		

            RIGHTCUR193=(Token)match(input,RIGHTCUR,FOLLOW_RIGHTCUR_in_for_loop3799); 
            RIGHTCUR193_tree = 
            (Object)adaptor.create(RIGHTCUR193)
            ;
            adaptor.addChild(root_0, RIGHTCUR193_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2150:1: listOfStatements[boolean defer] : (stmtToken= statement[defer] )+ ;
    public final EugeneParser.listOfStatements_return listOfStatements(boolean defer) throws RecognitionException {
        EugeneParser.listOfStatements_return retval = new EugeneParser.listOfStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.statement_return stmtToken =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2151:2: ( (stmtToken= statement[defer] )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2151:4: (stmtToken= statement[defer] )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2151:4: (stmtToken= statement[defer] )+
            int cnt70=0;
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( ((LA70_0 >= ARRAY && LA70_0 <= COLLECTION)||LA70_0==DEVICE||LA70_0==GENBANK||(LA70_0 >= HASHMARK && LA70_0 <= ID)||LA70_0==INTERACTION||(LA70_0 >= LC_FOR && LA70_0 <= LC_INCLUDE)||LA70_0==LC_PIGEON||LA70_0==NUM||(LA70_0 >= PART && LA70_0 <= PIGEON)||(LA70_0 >= PRINT && LA70_0 <= PROPERTY)||LA70_0==REGISTRY||(LA70_0 >= RULE && LA70_0 <= SEMIC)||(LA70_0 >= TXT && LA70_0 <= TYPE)||(LA70_0 >= UC_FOR && LA70_0 <= UC_INCLUDE)) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2151:5: stmtToken= statement[defer]
            	    {
            	    pushFollow(FOLLOW_statement_in_listOfStatements3818);
            	    stmtToken=statement(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, stmtToken.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt70 >= 1 ) break loop70;
                        EarlyExitException eee =
                            new EarlyExitException(70, input);
                        throw eee;
                }
                cnt70++;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2159:1: expr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= multExpr[defer] ( PLUS e= multExpr[defer] | MINUS e= multExpr[defer] )* ;
    public final EugeneParser.expr_return expr(boolean defer) throws RecognitionException {
        EugeneParser.expr_return retval = new EugeneParser.expr_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token PLUS194=null;
        Token MINUS195=null;
        EugeneParser.multExpr_return e =null;


        Object PLUS194_tree=null;
        Object MINUS195_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2161:2: (e= multExpr[defer] ( PLUS e= multExpr[defer] | MINUS e= multExpr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2161:4: e= multExpr[defer] ( PLUS e= multExpr[defer] | MINUS e= multExpr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_expr3846);
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
            			
            		

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2175:5: ( PLUS e= multExpr[defer] | MINUS e= multExpr[defer] )*
            loop71:
            do {
                int alt71=3;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==PLUS) ) {
                    alt71=1;
                }
                else if ( (LA71_0==MINUS) ) {
                    alt71=2;
                }


                switch (alt71) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2175:6: PLUS e= multExpr[defer]
            	    {
            	    PLUS194=(Token)match(input,PLUS,FOLLOW_PLUS_in_expr3854); 
            	    PLUS194_tree = 
            	    (Object)adaptor.create(PLUS194)
            	    ;
            	    adaptor.addChild(root_0, PLUS194_tree);


            	    pushFollow(FOLLOW_multExpr_in_expr3858);
            	    e=multExpr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    		if(!defer) {
            	    			doMinPlusOp((e!=null?e.p:null), retval.p, "+");
            	    			retval.element = null;
            	    		}
            	    		

            	    }
            	    break;
            	case 2 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2180:7: MINUS e= multExpr[defer]
            	    {
            	    MINUS195=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr3865); 
            	    MINUS195_tree = 
            	    (Object)adaptor.create(MINUS195)
            	    ;
            	    adaptor.addChild(root_0, MINUS195_tree);


            	    pushFollow(FOLLOW_multExpr_in_expr3869);
            	    e=multExpr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    		if(!defer) {
            	    			doMinPlusOp((e!=null?e.p:null), retval.p, "-");
            	    			retval.element = null;
            	    		}
            	    		

            	    }
            	    break;

            	default :
            	    break loop71;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2188:1: multExpr[boolean defer] returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element] : e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2190:2: (e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2190:4: e= atom[defer] ( (mul= MULT |div= DIV ) e= atom[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_atom_in_multExpr3895);
            e=atom(defer);

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());


            		if(!defer) {
            			retval.p = (e!=null?e.p:null);
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
            		

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2205:5: ( (mul= MULT |div= DIV ) e= atom[defer] )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==DIV||LA73_0==MULT) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2205:7: (mul= MULT |div= DIV ) e= atom[defer]
            	    {
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2205:7: (mul= MULT |div= DIV )
            	    int alt72=2;
            	    int LA72_0 = input.LA(1);

            	    if ( (LA72_0==MULT) ) {
            	        alt72=1;
            	    }
            	    else if ( (LA72_0==DIV) ) {
            	        alt72=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 72, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt72) {
            	        case 1 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2205:8: mul= MULT
            	            {
            	            mul=(Token)match(input,MULT,FOLLOW_MULT_in_multExpr3907); 
            	            mul_tree = 
            	            (Object)adaptor.create(mul)
            	            ;
            	            adaptor.addChild(root_0, mul_tree);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2205:17: div= DIV
            	            {
            	            div=(Token)match(input,DIV,FOLLOW_DIV_in_multExpr3911); 
            	            div_tree = 
            	            (Object)adaptor.create(div)
            	            ;
            	            adaptor.addChild(root_0, div_tree);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_atom_in_multExpr3916);
            	    e=atom(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, e.getTree());


            	    			if(!defer) {
            	    				if ((mul!=null?mul.getText():null) != null) {
            	    					doMultDivOp((e!=null?e.p:null), retval.p, (mul!=null?mul.getText():null));
            	    				} else {
            	    					doMultDivOp((e!=null?e.p:null), retval.p, (div!=null?div.getText():null));
            	    				}
            	    			}
            	    			

            	    }
            	    break;

            	default :
            	    break loop73;
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2217:1: atom[boolean defer] returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element] : ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= TRUE |f= FALSE ) | ID oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR );
    public final EugeneParser.atom_return atom(boolean defer) throws RecognitionException {
        EugeneParser.atom_return retval = new EugeneParser.atom_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token n=null;
        Token t=null;
        Token f=null;
        Token MINUS196=null;
        Token ID197=null;
        Token STRING198=null;
        Token char_literal199=null;
        Token char_literal201=null;
        Token LEFTSBR202=null;
        Token RIGHTSBR204=null;
        EugeneParser.object_access_return oc =null;

        EugeneParser.expr_return expr200 =null;

        EugeneParser.list_return list203 =null;


        Object n_tree=null;
        Object t_tree=null;
        Object f_tree=null;
        Object MINUS196_tree=null;
        Object ID197_tree=null;
        Object STRING198_tree=null;
        Object char_literal199_tree=null;
        Object char_literal201_tree=null;
        Object LEFTSBR202_tree=null;
        Object RIGHTSBR204_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2219:2: ( (n= NUMBER |n= REAL ) | MINUS (n= NUMBER |n= REAL ) | (t= TRUE |f= FALSE ) | ID oc= object_access[defer, $element] | STRING | '(' expr[defer] ')' | LEFTSBR list[defer] RIGHTSBR )
            int alt77=7;
            switch ( input.LA(1) ) {
            case NUMBER:
            case REAL:
                {
                alt77=1;
                }
                break;
            case MINUS:
                {
                alt77=2;
                }
                break;
            case FALSE:
            case TRUE:
                {
                alt77=3;
                }
                break;
            case ID:
                {
                alt77=4;
                }
                break;
            case STRING:
                {
                alt77=5;
                }
                break;
            case LEFTP:
                {
                alt77=6;
                }
                break;
            case LEFTSBR:
                {
                alt77=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;

            }

            switch (alt77) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2219:4: (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2219:4: (n= NUMBER |n= REAL )
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==NUMBER) ) {
                        alt74=1;
                    }
                    else if ( (LA74_0==REAL) ) {
                        alt74=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 74, 0, input);

                        throw nvae;

                    }
                    switch (alt74) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2219:5: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom3946); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2219:16: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom3952); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2226:4: MINUS (n= NUMBER |n= REAL )
                    {
                    root_0 = (Object)adaptor.nil();


                    MINUS196=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom3962); 
                    MINUS196_tree = 
                    (Object)adaptor.create(MINUS196)
                    ;
                    adaptor.addChild(root_0, MINUS196_tree);


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2226:10: (n= NUMBER |n= REAL )
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==NUMBER) ) {
                        alt75=1;
                    }
                    else if ( (LA75_0==REAL) ) {
                        alt75=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 75, 0, input);

                        throw nvae;

                    }
                    switch (alt75) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2226:11: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom3967); 
                            n_tree = 
                            (Object)adaptor.create(n)
                            ;
                            adaptor.addChild(root_0, n_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2226:22: n= REAL
                            {
                            n=(Token)match(input,REAL,FOLLOW_REAL_in_atom3973); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2233:4: (t= TRUE |f= FALSE )
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2233:4: (t= TRUE |f= FALSE )
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==TRUE) ) {
                        alt76=1;
                    }
                    else if ( (LA76_0==FALSE) ) {
                        alt76=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 76, 0, input);

                        throw nvae;

                    }
                    switch (alt76) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2233:5: t= TRUE
                            {
                            t=(Token)match(input,TRUE,FOLLOW_TRUE_in_atom3986); 
                            t_tree = 
                            (Object)adaptor.create(t)
                            ;
                            adaptor.addChild(root_0, t_tree);


                            }
                            break;
                        case 2 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2233:14: f= FALSE
                            {
                            f=(Token)match(input,FALSE,FOLLOW_FALSE_in_atom3992); 
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2244:4: ID oc= object_access[defer, $element]
                    {
                    root_0 = (Object)adaptor.nil();


                    ID197=(Token)match(input,ID,FOLLOW_ID_in_atom4002); 
                    ID197_tree = 
                    (Object)adaptor.create(ID197)
                    ;
                    adaptor.addChild(root_0, ID197_tree);



                    	if(!defer) {
                    		try {
                    			retval.element = this.interp.get((ID197!=null?ID197.getText():null));
                    					
                    			if(null != retval.element) {
                    				if(retval.element instanceof Variable) {
                    					retval.p = copyVariable((Variable)retval.element);
                    					retval.primVariable = (Variable)retval.element;
                    				}
                    			} else {
                    				throw new EugeneException((ID197!=null?ID197.getText():null) + " is not declared.");
                    			}
                    		} catch(EugeneException ee) {
                    			printError(ee.toString());
                    		}
                    	}
                    		

                    pushFollow(FOLLOW_object_access_in_atom4010);
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2270:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();


                    STRING198=(Token)match(input,STRING,FOLLOW_STRING_in_atom4019); 
                    STRING198_tree = 
                    (Object)adaptor.create(STRING198)
                    ;
                    adaptor.addChild(root_0, STRING198_tree);



                    		if(!defer) {
                    			retval.p.type = EugeneConstants.TXT;
                    			retval.p.txt = (STRING198!=null?STRING198.getText():null).substring(1, (STRING198!=null?STRING198.getText():null).length()-1);
                    		}
                    		

                    }
                    break;
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2277:4: '(' expr[defer] ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal199=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_atom4028); 
                    char_literal199_tree = 
                    (Object)adaptor.create(char_literal199)
                    ;
                    adaptor.addChild(root_0, char_literal199_tree);


                    pushFollow(FOLLOW_expr_in_atom4030);
                    expr200=expr(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, expr200.getTree());

                    char_literal201=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_atom4033); 
                    char_literal201_tree = 
                    (Object)adaptor.create(char_literal201)
                    ;
                    adaptor.addChild(root_0, char_literal201_tree);



                    		if(!defer) {
                    			retval.p = (expr200!=null?expr200.p:null);
                    			retval.primVariable = (expr200!=null?expr200.primVariable:null);
                    		}
                    		

                    }
                    break;
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2284:5: LEFTSBR list[defer] RIGHTSBR
                    {
                    root_0 = (Object)adaptor.nil();


                    LEFTSBR202=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_atom4043); 
                    LEFTSBR202_tree = 
                    (Object)adaptor.create(LEFTSBR202)
                    ;
                    adaptor.addChild(root_0, LEFTSBR202_tree);


                    pushFollow(FOLLOW_list_in_atom4045);
                    list203=list(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, list203.getTree());

                    RIGHTSBR204=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_atom4048); 
                    RIGHTSBR204_tree = 
                    (Object)adaptor.create(RIGHTSBR204)
                    ;
                    adaptor.addChild(root_0, RIGHTSBR204_tree);



                    		if(!defer) {
                    			retval.p = (list203!=null?list203.listPrim:null);
                    			retval.primVariable = (list203!=null?list203.listPrim:null);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2370:1: list[boolean defer] returns [Variable listPrim] : str1= expr[defer] ( COMMA str2= expr[defer] )* ;
    public final EugeneParser.list_return list(boolean defer) throws RecognitionException {
        EugeneParser.list_return retval = new EugeneParser.list_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token COMMA205=null;
        EugeneParser.expr_return str1 =null;

        EugeneParser.expr_return str2 =null;


        Object COMMA205_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2372:2: (str1= expr[defer] ( COMMA str2= expr[defer] )* )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2372:4: str1= expr[defer] ( COMMA str2= expr[defer] )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr_in_list4074);
            str1=expr(defer);

            state._fsp--;

            adaptor.addChild(root_0, str1.getTree());


            if(!defer){
                retval.listPrim = new Variable();
                retval.listPrim.type = typeList + "List";
                addToListPrim((str1!=null?str1.p:null), retval.listPrim);
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2378:5: ( COMMA str2= expr[defer] )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==COMMA) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2378:6: COMMA str2= expr[defer]
            	    {
            	    COMMA205=(Token)match(input,COMMA,FOLLOW_COMMA_in_list4081); 
            	    COMMA205_tree = 
            	    (Object)adaptor.create(COMMA205)
            	    ;
            	    adaptor.addChild(root_0, COMMA205_tree);


            	    pushFollow(FOLLOW_expr_in_list4085);
            	    str2=expr(defer);

            	    state._fsp--;

            	    adaptor.addChild(root_0, str2.getTree());


            	    if(!defer) {
            	        addToListPrim((str2!=null?str2.p:null), retval.listPrim);
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
    // $ANTLR end "list"


    public static class object_access_return extends ParserRuleReturnScope {
        public NamedElement child;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "object_access"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2407:1: object_access[boolean defer, NamedElement parent] returns [NamedElement child] : (| ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] );
    public final EugeneParser.object_access_return object_access(boolean defer, NamedElement parent) throws RecognitionException {
        EugeneParser.object_access_return retval = new EugeneParser.object_access_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token DOT206=null;
        Token LEFTSBR207=null;
        Token RIGHTSBR208=null;
        EugeneParser.expr_return exp =null;

        EugeneParser.object_access_return o =null;


        Object id_tree=null;
        Object DOT206_tree=null;
        Object LEFTSBR207_tree=null;
        Object RIGHTSBR208_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2409:2: (| ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child] )
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==COMMA||LA80_0==DIV||LA80_0==EQUALS||(LA80_0 >= GEQUAL && LA80_0 <= GTHAN)||LA80_0==LEQUAL||(LA80_0 >= LTHAN && LA80_0 <= MINUS)||(LA80_0 >= MULT && LA80_0 <= NEQUAL)||LA80_0==PLUS||(LA80_0 >= RIGHTP && LA80_0 <= RIGHTSBR)||LA80_0==SEMIC||LA80_0==109||(LA80_0 >= 111 && LA80_0 <= 112)||LA80_0==115||(LA80_0 >= 118 && LA80_0 <= 119)||LA80_0==121||(LA80_0 >= 134 && LA80_0 <= 135)||LA80_0==148||(LA80_0 >= 150 && LA80_0 <= 151)||LA80_0==154||(LA80_0 >= 157 && LA80_0 <= 158)||LA80_0==160||(LA80_0 >= 173 && LA80_0 <= 174)) ) {
                alt80=1;
            }
            else if ( (LA80_0==DOT||LA80_0==LEFTSBR) ) {
                alt80=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;

            }
            switch (alt80) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2410:2: 
                    {
                    root_0 = (Object)adaptor.nil();



                    if(!defer) {
                        retval.child = parent;
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2415:4: ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR ) o= object_access[defer, $child]
                    {
                    root_0 = (Object)adaptor.nil();


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2415:4: ( DOT id= ID | LEFTSBR (exp= expr[defer] ) RIGHTSBR )
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==DOT) ) {
                        alt79=1;
                    }
                    else if ( (LA79_0==LEFTSBR) ) {
                        alt79=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 79, 0, input);

                        throw nvae;

                    }
                    switch (alt79) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2415:5: DOT id= ID
                            {
                            DOT206=(Token)match(input,DOT,FOLLOW_DOT_in_object_access4125); 
                            DOT206_tree = 
                            (Object)adaptor.create(DOT206)
                            ;
                            adaptor.addChild(root_0, DOT206_tree);


                            id=(Token)match(input,ID,FOLLOW_ID_in_object_access4129); 
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2427:4: LEFTSBR (exp= expr[defer] ) RIGHTSBR
                            {
                            LEFTSBR207=(Token)match(input,LEFTSBR,FOLLOW_LEFTSBR_in_object_access4137); 
                            LEFTSBR207_tree = 
                            (Object)adaptor.create(LEFTSBR207)
                            ;
                            adaptor.addChild(root_0, LEFTSBR207_tree);


                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2427:12: (exp= expr[defer] )
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2427:13: exp= expr[defer]
                            {
                            pushFollow(FOLLOW_expr_in_object_access4142);
                            exp=expr(defer);

                            state._fsp--;

                            adaptor.addChild(root_0, exp.getTree());

                            }


                            RIGHTSBR208=(Token)match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_object_access4146); 
                            RIGHTSBR208_tree = 
                            (Object)adaptor.create(RIGHTSBR208)
                            ;
                            adaptor.addChild(root_0, RIGHTSBR208_tree);



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


                    pushFollow(FOLLOW_object_access_in_object_access4153);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2452:1: dataExchange[boolean defer] returns [NamedElement e] : (s= sbolStatement[defer] |g= genbankStatement[defer] |p= pigeonStatement[defer] |r= registryStatement[defer] |i= importStatement[defer] );
    public final EugeneParser.dataExchange_return dataExchange(boolean defer) throws RecognitionException {
        EugeneParser.dataExchange_return retval = new EugeneParser.dataExchange_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        EugeneParser.sbolStatement_return s =null;

        EugeneParser.genbankStatement_return g =null;

        EugeneParser.pigeonStatement_return p =null;

        EugeneParser.registryStatement_return r =null;

        EugeneParser.importStatement_return i =null;



        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2454:2: (s= sbolStatement[defer] |g= genbankStatement[defer] |p= pigeonStatement[defer] |r= registryStatement[defer] |i= importStatement[defer] )
            int alt81=5;
            switch ( input.LA(1) ) {
            case SBOL:
                {
                alt81=1;
                }
                break;
            case GENBANK:
                {
                alt81=2;
                }
                break;
            case LC_PIGEON:
            case PIGEON:
                {
                alt81=3;
                }
                break;
            case REGISTRY:
                {
                alt81=4;
                }
                break;
            case LC_IMPORT:
            case UC_IMPORT:
                {
                alt81=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;

            }

            switch (alt81) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2454:4: s= sbolStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_sbolStatement_in_dataExchange4179);
                    s=sbolStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());


                    if(!defer) {
                        retval.e = (s!=null?s.e:null);
                    }	
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2459:4: g= genbankStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_genbankStatement_in_dataExchange4189);
                    g=genbankStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, g.getTree());

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2460:4: p= pigeonStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pigeonStatement_in_dataExchange4197);
                    p=pigeonStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, p.getTree());

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2461:4: r= registryStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_registryStatement_in_dataExchange4205);
                    r=registryStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, r.getTree());

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2462:4: i= importStatement[defer]
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_importStatement_in_dataExchange4213);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2470:1: includeStatement[boolean defer] : ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING ;
    public final EugeneParser.includeStatement_return includeStatement(boolean defer) throws RecognitionException {
        EugeneParser.includeStatement_return retval = new EugeneParser.includeStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token HASHMARK209=null;
        Token set210=null;

        Object file_tree=null;
        Object HASHMARK209_tree=null;
        Object set210_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2471:2: ( ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2471:4: ( HASHMARK )? ( LC_INCLUDE | UC_INCLUDE ) file= STRING
            {
            root_0 = (Object)adaptor.nil();


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2471:4: ( HASHMARK )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==HASHMARK) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2471:5: HASHMARK
                    {
                    HASHMARK209=(Token)match(input,HASHMARK,FOLLOW_HASHMARK_in_includeStatement4232); 
                    HASHMARK209_tree = 
                    (Object)adaptor.create(HASHMARK209)
                    ;
                    adaptor.addChild(root_0, HASHMARK209_tree);


                    }
                    break;

            }


            set210=(Token)input.LT(1);

            if ( input.LA(1)==LC_INCLUDE||input.LA(1)==UC_INCLUDE ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set210)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            file=(Token)match(input,STRING,FOLLOW_STRING_in_includeStatement4244); 
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2483:1: importStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP ;
    public final EugeneParser.importStatement_return importStatement(boolean defer) throws RecognitionException {
        EugeneParser.importStatement_return retval = new EugeneParser.importStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token file=null;
        Token set211=null;
        Token LEFTP212=null;
        Token RIGHTP213=null;

        Object file_tree=null;
        Object set211_tree=null;
        Object LEFTP212_tree=null;
        Object RIGHTP213_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2485:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2485:4: ( LC_IMPORT | UC_IMPORT ) LEFTP file= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set211=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
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


            LEFTP212=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_importStatement4271); 
            LEFTP212_tree = 
            (Object)adaptor.create(LEFTP212)
            ;
            adaptor.addChild(root_0, LEFTP212_tree);


            file=(Token)match(input,STRING,FOLLOW_STRING_in_importStatement4275); 
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
            	

            RIGHTP213=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_importStatement4279); 
            RIGHTP213_tree = 
            (Object)adaptor.create(RIGHTP213)
            ;
            adaptor.addChild(root_0, RIGHTP213_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2497:1: sbolStatement[boolean defer] returns [NamedElement e] : SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) ;
    public final EugeneParser.sbolStatement_return sbolStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolStatement_return retval = new EugeneParser.sbolStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token SBOL214=null;
        Token DOT215=null;
        EugeneParser.sbolImportStatement_return i =null;

        EugeneParser.sbolExportStatement_return sbolExportStatement216 =null;


        Object SBOL214_tree=null;
        Object DOT215_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2499:2: ( SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2499:4: SBOL DOT ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            {
            root_0 = (Object)adaptor.nil();


            SBOL214=(Token)match(input,SBOL,FOLLOW_SBOL_in_sbolStatement4301); 
            SBOL214_tree = 
            (Object)adaptor.create(SBOL214)
            ;
            adaptor.addChild(root_0, SBOL214_tree);


            DOT215=(Token)match(input,DOT,FOLLOW_DOT_in_sbolStatement4303); 
            DOT215_tree = 
            (Object)adaptor.create(DOT215)
            ;
            adaptor.addChild(root_0, DOT215_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2499:13: ( sbolExportStatement[defer] |i= sbolImportStatement[defer] )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==EXPORT) ) {
                alt83=1;
            }
            else if ( (LA83_0==LC_IMPORT||LA83_0==UC_IMPORT) ) {
                alt83=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;

            }
            switch (alt83) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2499:14: sbolExportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolExportStatement_in_sbolStatement4306);
                    sbolExportStatement216=sbolExportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, sbolExportStatement216.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2499:43: i= sbolImportStatement[defer]
                    {
                    pushFollow(FOLLOW_sbolImportStatement_in_sbolStatement4313);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2506:1: sbolExportStatement[boolean defer] : EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP ;
    public final EugeneParser.sbolExportStatement_return sbolExportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolExportStatement_return retval = new EugeneParser.sbolExportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token idToken=null;
        Token filenameToken=null;
        Token EXPORT217=null;
        Token LEFTP218=null;
        Token COMMA219=null;
        Token RIGHTP220=null;

        Object idToken_tree=null;
        Object filenameToken_tree=null;
        Object EXPORT217_tree=null;
        Object LEFTP218_tree=null;
        Object COMMA219_tree=null;
        Object RIGHTP220_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2507:2: ( EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2507:4: EXPORT LEFTP idToken= ID COMMA filenameToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            EXPORT217=(Token)match(input,EXPORT,FOLLOW_EXPORT_in_sbolExportStatement4330); 
            EXPORT217_tree = 
            (Object)adaptor.create(EXPORT217)
            ;
            adaptor.addChild(root_0, EXPORT217_tree);


            LEFTP218=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolExportStatement4332); 
            LEFTP218_tree = 
            (Object)adaptor.create(LEFTP218)
            ;
            adaptor.addChild(root_0, LEFTP218_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_sbolExportStatement4336); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            COMMA219=(Token)match(input,COMMA,FOLLOW_COMMA_in_sbolExportStatement4338); 
            COMMA219_tree = 
            (Object)adaptor.create(COMMA219)
            ;
            adaptor.addChild(root_0, COMMA219_tree);


            filenameToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolExportStatement4342); 
            filenameToken_tree = 
            (Object)adaptor.create(filenameToken)
            ;
            adaptor.addChild(root_0, filenameToken_tree);


            RIGHTP220=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolExportStatement4344); 
            RIGHTP220_tree = 
            (Object)adaptor.create(RIGHTP220)
            ;
            adaptor.addChild(root_0, RIGHTP220_tree);



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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2520:1: sbolImportStatement[boolean defer] returns [NamedElement e] : ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP ;
    public final EugeneParser.sbolImportStatement_return sbolImportStatement(boolean defer) throws RecognitionException {
        EugeneParser.sbolImportStatement_return retval = new EugeneParser.sbolImportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token fileToken=null;
        Token set221=null;
        Token LEFTP222=null;
        Token RIGHTP223=null;

        Object fileToken_tree=null;
        Object set221_tree=null;
        Object LEFTP222_tree=null;
        Object RIGHTP223_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2522:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2522:4: ( LC_IMPORT | UC_IMPORT ) LEFTP fileToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            set221=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set221)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP222=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_sbolImportStatement4373); 
            LEFTP222_tree = 
            (Object)adaptor.create(LEFTP222)
            ;
            adaptor.addChild(root_0, LEFTP222_tree);


            fileToken=(Token)match(input,STRING,FOLLOW_STRING_in_sbolImportStatement4377); 
            fileToken_tree = 
            (Object)adaptor.create(fileToken)
            ;
            adaptor.addChild(root_0, fileToken_tree);


            RIGHTP223=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_sbolImportStatement4379); 
            RIGHTP223_tree = 
            (Object)adaptor.create(RIGHTP223)
            ;
            adaptor.addChild(root_0, RIGHTP223_tree);



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


    public static class genbankStatement_return extends ParserRuleReturnScope {
        public NamedElement objElement;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genbankStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2533:1: genbankStatement[boolean defer] returns [NamedElement objElement] : GENBANK DOT (importToken= genbankImportStatement[defer] | genbankExportStatement[defer] ) ;
    public final EugeneParser.genbankStatement_return genbankStatement(boolean defer) throws RecognitionException {
        EugeneParser.genbankStatement_return retval = new EugeneParser.genbankStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token GENBANK224=null;
        Token DOT225=null;
        EugeneParser.genbankImportStatement_return importToken =null;

        EugeneParser.genbankExportStatement_return genbankExportStatement226 =null;


        Object GENBANK224_tree=null;
        Object DOT225_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2534:2: ( GENBANK DOT (importToken= genbankImportStatement[defer] | genbankExportStatement[defer] ) )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2534:4: GENBANK DOT (importToken= genbankImportStatement[defer] | genbankExportStatement[defer] )
            {
            root_0 = (Object)adaptor.nil();


            GENBANK224=(Token)match(input,GENBANK,FOLLOW_GENBANK_in_genbankStatement4399); 
            GENBANK224_tree = 
            (Object)adaptor.create(GENBANK224)
            ;
            adaptor.addChild(root_0, GENBANK224_tree);


            DOT225=(Token)match(input,DOT,FOLLOW_DOT_in_genbankStatement4401); 
            DOT225_tree = 
            (Object)adaptor.create(DOT225)
            ;
            adaptor.addChild(root_0, DOT225_tree);


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2534:16: (importToken= genbankImportStatement[defer] | genbankExportStatement[defer] )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==LC_IMPORT||LA84_0==UC_IMPORT) ) {
                alt84=1;
            }
            else if ( (LA84_0==EXPORT) ) {
                alt84=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }
            switch (alt84) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2534:17: importToken= genbankImportStatement[defer]
                    {
                    pushFollow(FOLLOW_genbankImportStatement_in_genbankStatement4406);
                    importToken=genbankImportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, importToken.getTree());

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2534:61: genbankExportStatement[defer]
                    {
                    pushFollow(FOLLOW_genbankExportStatement_in_genbankStatement4411);
                    genbankExportStatement226=genbankExportStatement(defer);

                    state._fsp--;

                    adaptor.addChild(root_0, genbankExportStatement226.getTree());

                    }
                    break;

            }



            if(!defer && importToken!=null) {
                retval.objElement = (importToken!=null?importToken.objElement:null);
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
        public NamedElement objElement;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genbankExportStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2541:1: genbankExportStatement[boolean defer] returns [NamedElement objElement] : EXPORT LEFTP RIGHTP ;
    public final EugeneParser.genbankExportStatement_return genbankExportStatement(boolean defer) throws RecognitionException {
        EugeneParser.genbankExportStatement_return retval = new EugeneParser.genbankExportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token EXPORT227=null;
        Token LEFTP228=null;
        Token RIGHTP229=null;

        Object EXPORT227_tree=null;
        Object LEFTP228_tree=null;
        Object RIGHTP229_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2542:2: ( EXPORT LEFTP RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2542:4: EXPORT LEFTP RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            EXPORT227=(Token)match(input,EXPORT,FOLLOW_EXPORT_in_genbankExportStatement4434); 
            EXPORT227_tree = 
            (Object)adaptor.create(EXPORT227)
            ;
            adaptor.addChild(root_0, EXPORT227_tree);


            LEFTP228=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankExportStatement4436); 
            LEFTP228_tree = 
            (Object)adaptor.create(LEFTP228)
            ;
            adaptor.addChild(root_0, LEFTP228_tree);


            RIGHTP229=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankExportStatement4438); 
            RIGHTP229_tree = 
            (Object)adaptor.create(RIGHTP229)
            ;
            adaptor.addChild(root_0, RIGHTP229_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
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
        public NamedElement objElement;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genbankImportStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2545:1: genbankImportStatement[boolean defer] returns [NamedElement objElement] : ( ( LC_IMPORT | UC_IMPORT ) LEFTP RIGHTP | ( LC_IMPORT | UC_IMPORT ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP );
    public final EugeneParser.genbankImportStatement_return genbankImportStatement(boolean defer) throws RecognitionException {
        EugeneParser.genbankImportStatement_return retval = new EugeneParser.genbankImportStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token typeToken=null;
        Token partToken=null;
        Token set230=null;
        Token LEFTP231=null;
        Token RIGHTP232=null;
        Token set233=null;
        Token LEFTP234=null;
        Token COMMA235=null;
        Token RIGHTP236=null;

        Object typeToken_tree=null;
        Object partToken_tree=null;
        Object set230_tree=null;
        Object LEFTP231_tree=null;
        Object RIGHTP232_tree=null;
        Object set233_tree=null;
        Object LEFTP234_tree=null;
        Object COMMA235_tree=null;
        Object RIGHTP236_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2547:2: ( ( LC_IMPORT | UC_IMPORT ) LEFTP RIGHTP | ( LC_IMPORT | UC_IMPORT ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==LC_IMPORT||LA85_0==UC_IMPORT) ) {
                int LA85_1 = input.LA(2);

                if ( (LA85_1==LEFTP) ) {
                    int LA85_2 = input.LA(3);

                    if ( (LA85_2==RIGHTP) ) {
                        alt85=1;
                    }
                    else if ( (LA85_2==ID) ) {
                        alt85=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 85, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 85, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;

            }
            switch (alt85) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2547:4: ( LC_IMPORT | UC_IMPORT ) LEFTP RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set230=(Token)input.LT(1);

                    if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
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


                    LEFTP231=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankImportStatement4470); 
                    LEFTP231_tree = 
                    (Object)adaptor.create(LEFTP231)
                    ;
                    adaptor.addChild(root_0, LEFTP231_tree);


                    RIGHTP232=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankImportStatement4472); 
                    RIGHTP232_tree = 
                    (Object)adaptor.create(RIGHTP232)
                    ;
                    adaptor.addChild(root_0, RIGHTP232_tree);



                    if(!defer) {

                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2552:4: ( LC_IMPORT | UC_IMPORT ) LEFTP typeToken= ID COMMA partToken= STRING RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    set233=(Token)input.LT(1);

                    if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
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


                    LEFTP234=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_genbankImportStatement4485); 
                    LEFTP234_tree = 
                    (Object)adaptor.create(LEFTP234)
                    ;
                    adaptor.addChild(root_0, LEFTP234_tree);


                    typeToken=(Token)match(input,ID,FOLLOW_ID_in_genbankImportStatement4489); 
                    typeToken_tree = 
                    (Object)adaptor.create(typeToken)
                    ;
                    adaptor.addChild(root_0, typeToken_tree);


                    COMMA235=(Token)match(input,COMMA,FOLLOW_COMMA_in_genbankImportStatement4491); 
                    COMMA235_tree = 
                    (Object)adaptor.create(COMMA235)
                    ;
                    adaptor.addChild(root_0, COMMA235_tree);


                    partToken=(Token)match(input,STRING,FOLLOW_STRING_in_genbankImportStatement4495); 
                    partToken_tree = 
                    (Object)adaptor.create(partToken)
                    ;
                    adaptor.addChild(root_0, partToken_tree);


                    RIGHTP236=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_genbankImportStatement4497); 
                    RIGHTP236_tree = 
                    (Object)adaptor.create(RIGHTP236)
                    ;
                    adaptor.addChild(root_0, RIGHTP236_tree);



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
    // $ANTLR end "genbankImportStatement"


    public static class pigeonStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pigeonStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2559:1: pigeonStatement[boolean defer] : ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP ;
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
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2560:2: ( ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2560:4: ( PIGEON | LC_PIGEON ) LEFTP idToken= ID RIGHTP
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


            LEFTP238=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_pigeonStatement4519); 
            LEFTP238_tree = 
            (Object)adaptor.create(LEFTP238)
            ;
            adaptor.addChild(root_0, LEFTP238_tree);


            idToken=(Token)match(input,ID,FOLLOW_ID_in_pigeonStatement4523); 
            idToken_tree = 
            (Object)adaptor.create(idToken)
            ;
            adaptor.addChild(root_0, idToken_tree);


            RIGHTP239=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_pigeonStatement4525); 
            RIGHTP239_tree = 
            (Object)adaptor.create(RIGHTP239)
            ;
            adaptor.addChild(root_0, RIGHTP239_tree);



            if(!defer) {
                try {
                    Collection<URI> uris = this.interp.pigeon((idToken!=null?idToken.getText():null));
                    for(URI uri : uris) {
                    	System.out.println(uri.toASCIIString());
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
    // $ANTLR end "pigeonStatement"


    public static class registryStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "registryStatement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2574:1: registryStatement[boolean defer] : REGISTRY DOT ( LC_IMPORT | UC_IMPORT ) LEFTP nameToken= STRING RIGHTP ;
    public final EugeneParser.registryStatement_return registryStatement(boolean defer) throws RecognitionException {
        EugeneParser.registryStatement_return retval = new EugeneParser.registryStatement_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token nameToken=null;
        Token REGISTRY240=null;
        Token DOT241=null;
        Token set242=null;
        Token LEFTP243=null;
        Token RIGHTP244=null;

        Object nameToken_tree=null;
        Object REGISTRY240_tree=null;
        Object DOT241_tree=null;
        Object set242_tree=null;
        Object LEFTP243_tree=null;
        Object RIGHTP244_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2575:2: ( REGISTRY DOT ( LC_IMPORT | UC_IMPORT ) LEFTP nameToken= STRING RIGHTP )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2575:4: REGISTRY DOT ( LC_IMPORT | UC_IMPORT ) LEFTP nameToken= STRING RIGHTP
            {
            root_0 = (Object)adaptor.nil();


            REGISTRY240=(Token)match(input,REGISTRY,FOLLOW_REGISTRY_in_registryStatement4542); 
            REGISTRY240_tree = 
            (Object)adaptor.create(REGISTRY240)
            ;
            adaptor.addChild(root_0, REGISTRY240_tree);


            DOT241=(Token)match(input,DOT,FOLLOW_DOT_in_registryStatement4544); 
            DOT241_tree = 
            (Object)adaptor.create(DOT241)
            ;
            adaptor.addChild(root_0, DOT241_tree);


            set242=(Token)input.LT(1);

            if ( input.LA(1)==LC_IMPORT||input.LA(1)==UC_IMPORT ) {
                input.consume();
                adaptor.addChild(root_0, 
                (Object)adaptor.create(set242)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            LEFTP243=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_registryStatement4552); 
            LEFTP243_tree = 
            (Object)adaptor.create(LEFTP243)
            ;
            adaptor.addChild(root_0, LEFTP243_tree);


            nameToken=(Token)match(input,STRING,FOLLOW_STRING_in_registryStatement4556); 
            nameToken_tree = 
            (Object)adaptor.create(nameToken)
            ;
            adaptor.addChild(root_0, nameToken_tree);


            RIGHTP244=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_registryStatement4558); 
            RIGHTP244_tree = 
            (Object)adaptor.create(RIGHTP244)
            ;
            adaptor.addChild(root_0, RIGHTP244_tree);



            if(!defer) {

                String name = (nameToken!=null?nameToken.getText():null);
                if(null != name) {
                
                    // remove the double quotas
                    name = name.substring(1, name.length()-2);
                    
//                    if(null == this.registryImporter) {
//                        this.registryImporter = new SBOLRegistryImporter();
//                    }
//                    
//                    try {
//                        List<Component> lst = this.registryImporter.importComponent((nameToken!=null?nameToken.getText():null));
//                        if(null!=lst && !lst.isEmpty()) {
//                            for(Component component : lst) {
//                                this.interp.put(component);
//                            }
//                        } else {
//                            throw new EugeneException("Cannot import "+name+"!");
//                        }
//                    } catch(EugeneException ee) {
//                        printError(ee.getMessage());
//                    }
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "testStatements"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2607:1: testStatements[boolean defer] : (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP );
    public final EugeneParser.testStatements_return testStatements(boolean defer) throws RecognitionException {
        EugeneParser.testStatements_return retval = new EugeneParser.testStatements_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id=null;
        Token n=null;
        Token ASSERT245=null;
        Token LEFTP246=null;
        Token DOT247=null;
        Token SIZE248=null;
        Token LEFTP249=null;
        Token RIGHTP250=null;
        Token EQUALS251=null;
        Token EQUALS252=null;
        Token RIGHTP253=null;

        Object id_tree=null;
        Object n_tree=null;
        Object ASSERT245_tree=null;
        Object LEFTP246_tree=null;
        Object DOT247_tree=null;
        Object SIZE248_tree=null;
        Object LEFTP249_tree=null;
        Object RIGHTP250_tree=null;
        Object EQUALS251_tree=null;
        Object EQUALS252_tree=null;
        Object RIGHTP253_tree=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2608:2: (| ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==SEMIC) ) {
                alt86=1;
            }
            else if ( (LA86_0==ASSERT) ) {
                alt86=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;

            }
            switch (alt86) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2608:5: 
                    {
                    root_0 = (Object)adaptor.nil();


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/sparrow/grammar/Eugene.g:2608:7: ASSERT LEFTP id= ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n= NUMBER RIGHTP
                    {
                    root_0 = (Object)adaptor.nil();


                    ASSERT245=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_testStatements4578); 
                    ASSERT245_tree = 
                    (Object)adaptor.create(ASSERT245)
                    ;
                    adaptor.addChild(root_0, ASSERT245_tree);


                    LEFTP246=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4580); 
                    LEFTP246_tree = 
                    (Object)adaptor.create(LEFTP246)
                    ;
                    adaptor.addChild(root_0, LEFTP246_tree);


                    id=(Token)match(input,ID,FOLLOW_ID_in_testStatements4584); 
                    id_tree = 
                    (Object)adaptor.create(id)
                    ;
                    adaptor.addChild(root_0, id_tree);


                    DOT247=(Token)match(input,DOT,FOLLOW_DOT_in_testStatements4586); 
                    DOT247_tree = 
                    (Object)adaptor.create(DOT247)
                    ;
                    adaptor.addChild(root_0, DOT247_tree);


                    SIZE248=(Token)match(input,SIZE,FOLLOW_SIZE_in_testStatements4588); 
                    SIZE248_tree = 
                    (Object)adaptor.create(SIZE248)
                    ;
                    adaptor.addChild(root_0, SIZE248_tree);


                    LEFTP249=(Token)match(input,LEFTP,FOLLOW_LEFTP_in_testStatements4590); 
                    LEFTP249_tree = 
                    (Object)adaptor.create(LEFTP249)
                    ;
                    adaptor.addChild(root_0, LEFTP249_tree);


                    RIGHTP250=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4592); 
                    RIGHTP250_tree = 
                    (Object)adaptor.create(RIGHTP250)
                    ;
                    adaptor.addChild(root_0, RIGHTP250_tree);


                    EQUALS251=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4594); 
                    EQUALS251_tree = 
                    (Object)adaptor.create(EQUALS251)
                    ;
                    adaptor.addChild(root_0, EQUALS251_tree);


                    EQUALS252=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_testStatements4596); 
                    EQUALS252_tree = 
                    (Object)adaptor.create(EQUALS252)
                    ;
                    adaptor.addChild(root_0, EQUALS252_tree);


                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_testStatements4600); 
                    n_tree = 
                    (Object)adaptor.create(n)
                    ;
                    adaptor.addChild(root_0, n_tree);


                    RIGHTP253=(Token)match(input,RIGHTP,FOLLOW_RIGHTP_in_testStatements4602); 
                    RIGHTP253_tree = 
                    (Object)adaptor.create(RIGHTP253)
                    ;
                    adaptor.addChild(root_0, RIGHTP253_tree);



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


 

    public static final BitSet FOLLOW_statement_in_prog783 = new BitSet(new long[]{0x7A00010F899013C0L,0x000000000F8C389EL});
    public static final BitSet FOLLOW_EOF_in_prog788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_includeStatement_in_statement814 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_statement818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_statement825 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_statement828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_printStatement_in_statement834 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_statement837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_statement845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_statement853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_statement860 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_statement863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testStatements_in_statement870 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_statement873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imperativeStatements_in_statement878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_declarationStatement899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDeclaration_in_declarationStatement907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyDeclaration_in_declarationStatement913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeDeclaration_in_declarationStatement919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instantiation_in_declarationStatement925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interactionDeclaration_in_declarationStatement931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDeclaration_in_declarationStatement937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_deviceDeclaration_in_declarationStatement943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration961 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_numdecl_in_variableDeclaration965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration976 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_txtdecl_in_variableDeclaration980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_variableDeclaration991 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration995 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_variableDeclaration999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_variableDeclaration1010 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_variableDeclaration1012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_variableDeclaration1014 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_numlistdecl_in_variableDeclaration1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_variableDeclaration1029 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_booldecl_in_variableDeclaration1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1056 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1063 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numdecl1073 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_numdecl1075 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_numdecl1080 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_numdecl1090 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_numdecl_in_numdecl1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1112 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1119 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtdecl1132 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_txtdecl1134 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_txtdecl1138 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_txtdecl1146 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_txtdecl_in_txtdecl1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1168 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1175 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_txtlistdecl1187 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_txtlistdecl1189 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_txtlistdecl1195 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_txtlistdecl1203 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_txtlistdecl_in_txtlistdecl1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1225 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1232 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_numlistdecl1244 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_numlistdecl1246 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_numlistdecl1251 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_numlistdecl1259 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_numlistdecl_in_numlistdecl1261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1281 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_booldecl1288 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_booldecl_in_booldecl1290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_booldecl1300 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_booldecl1302 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_booldecl1306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROPERTY_in_propertyDeclaration1324 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_propertyDeclaration1328 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_propertyDeclaration1330 = new BitSet(new long[]{0x0200000000000100L,0x0000000000040000L});
    public static final BitSet FOLLOW_propertyType_in_propertyDeclaration1334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_propertyDeclaration1336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TXT_in_propertyType1362 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1364 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_propertyType1381 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_propertyType1383 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_propertyType1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_propertyType1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_partTypeDeclaration_in_typeDeclaration1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_typeDeclaration1417 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_typeDeclaration1422 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFTP_in_typeDeclaration1425 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listOfIDs_in_typeDeclaration1430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_typeDeclaration1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_partTypeDeclaration1454 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_partTypeDeclaration1463 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFTP_in_partTypeDeclaration1466 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listOfIDs_in_partTypeDeclaration1471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_partTypeDeclaration1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLECTION_in_containerDeclaration1503 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ARRAY_in_containerDeclaration1510 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFTSBR_in_containerDeclaration1512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_containerDeclaration1514 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_containerDeclaration1520 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFTP_in_containerDeclaration1525 = new BitSet(new long[]{0x1E08180009041340L,0x00000000000F0A30L});
    public static final BitSet FOLLOW_list_of_declarations_in_containerDeclaration1528 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_containerDeclaration1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStatement_in_list_of_declarations1566 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_atom_in_list_of_declarations1573 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_list_of_declarations1581 = new BitSet(new long[]{0x1E08180009041340L,0x00000000000F0830L});
    public static final BitSet FOLLOW_list_of_declarations_in_list_of_declarations1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instantiation1613 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_instantiation1619 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFTP_in_instantiation1623 = new BitSet(new long[]{0x0408180001048000L,0x0000000000030220L});
    public static final BitSet FOLLOW_listOfDotValues_in_instantiation1628 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listOfValues_in_instantiation1633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_instantiation1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1662 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1666 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1672 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1684 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_listOfDotValues1687 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_listOfDotValues1689 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_listOfDotValues1693 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_listOfDotValues1701 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_listOfDotValues1705 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_listOfDotValues1715 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_expr_in_listOfValues1736 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_listOfValues1742 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_listOfValues1748 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_DEVICE_in_deviceDeclaration1767 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_deviceDeclaration1771 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFTP_in_deviceDeclaration1774 = new BitSet(new long[]{0x0008100001000000L,0x0000000000000201L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceDeclaration1779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_deviceDeclaration1784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selection_in_deviceComponents1815 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_deviceComponents1821 = new BitSet(new long[]{0x0008100001000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_deviceComponents_in_deviceComponents1825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_selection1849 = new BitSet(new long[]{0x0008000001000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_selection_list_in_selection1853 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_selection1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection1865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_device_component_in_selection_list1894 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_PIPE_in_selection_list1900 = new BitSet(new long[]{0x0008000001000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_selection_list_in_selection_list1904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_device_component1930 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_device_component1940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_assignment_in_assignment1960 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment1963 = new BitSet(new long[]{0x6408190401140020L,0x00000000040310A8L});
    public static final BitSet FOLLOW_AMP_in_assignment1968 = new BitSet(new long[]{0x6408190401140000L,0x00000000040310A8L});
    public static final BitSet FOLLOW_rhs_assignment_in_assignment1974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lhs_assignment1996 = new BitSet(new long[]{0x0000100000008000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_assignment2002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lhs_access2034 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_lhs_assignment_in_lhs_access2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_lhs_access2046 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_lhs_access2051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_lhs_access2055 = new BitSet(new long[]{0x0000100000008000L});
    public static final BitSet FOLLOW_lhs_access_in_lhs_access2061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_rhs_assignment2086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataExchange_in_rhs_assignment2096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_rhs_assignment2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_listOfIDs2134 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_listOfIDs2143 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_listOfIDs_in_listOfIDs2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_ruleDeclaration2171 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2175 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ruleDeclaration2177 = new BitSet(new long[]{0x04091A7001000000L,0xFFFFFFF970010020L,0x0001DFFFFFFFFFBFL});
    public static final BitSet FOLLOW_set_in_ruleDeclaration2182 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_ruleDeclaration2190 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COLON_in_ruleDeclaration2192 = new BitSet(new long[]{0x04091A3001000000L,0xFFFFFFF930010020L,0x0001DFFFFFFFFFBFL});
    public static final BitSet FOLLOW_cnf_rule_in_ruleDeclaration2200 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_ruleDeclaration2205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperators_in_ruleOperator2219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2598 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_relationalOperators2600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQUAL_in_relationalOperators2605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTHAN_in_relationalOperators2610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GTHAN_in_relationalOperators2615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEQUAL_in_relationalOperators2620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GEQUAL_in_relationalOperators2625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relationalOperators2702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_predicate_in_cnf_rule2726 = new BitSet(new long[]{0x0000800010000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_set_in_cnf_rule2734 = new BitSet(new long[]{0x04091A3001000000L,0xFFFFFFF930010020L,0x0001DFFFFFFFFFBFL});
    public static final BitSet FOLLOW_cnf_rule_in_cnf_rule2744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2774 = new BitSet(new long[]{0x0002008000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_set_in_or_predicate2780 = new BitSet(new long[]{0x04091A3001000000L,0xFFFFFFF930010020L,0x0001DFFFFFFFFFBFL});
    public static final BitSet FOLLOW_negated_predicate_in_or_predicate2790 = new BitSet(new long[]{0x0002008000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_set_in_negated_predicate2818 = new BitSet(new long[]{0x04081A1001000000L,0xFFFFFFF910010020L,0x0001DFFFFFFFFFBFL});
    public static final BitSet FOLLOW_predicate_in_negated_predicate2828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_negated_predicate2838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_predicate2865 = new BitSet(new long[]{0x0000021000000000L,0xFFFFFFF910000000L,0x0001DFFFFFFFFFBFL});
    public static final BitSet FOLLOW_ruleOperator_in_predicate2875 = new BitSet(new long[]{0x0400100001000002L});
    public static final BitSet FOLLOW_operand_in_predicate2884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_predicate2898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionRule_in_predicate2907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand2938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_operand2947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_operand2954 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_operand2958 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_operand2960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionRule2986 = new BitSet(new long[]{0x0044200000610000L,0x02C9A00000000000L,0x0000600164D000C0L});
    public static final BitSet FOLLOW_exp_op_in_expressionRule2991 = new BitSet(new long[]{0x0408080001000000L,0x0000000000010020L});
    public static final BitSet FOLLOW_expression_in_expressionRule2996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_operand_in_expression3020 = new BitSet(new long[]{0x0028000000004002L,0x0000000000000001L});
    public static final BitSet FOLLOW_exp_operator_in_expression3029 = new BitSet(new long[]{0x0408080001000000L,0x0000000000010020L});
    public static final BitSet FOLLOW_expression_in_expression3034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_expression3046 = new BitSet(new long[]{0x0408080001000000L,0x0000000000010020L});
    public static final BitSet FOLLOW_expression_in_expression3048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_expression3051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_exp_operator3070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operator3078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_in_exp_operator3085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_exp_operator3092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_exp_operand3122 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_exp_operand3124 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_exp_operand3133 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_exp_operand3139 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_exp_operand3145 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3164 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_exp_operand3168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_exp_operand3184 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_REAL_in_exp_operand3188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp_operand3197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOperators_in_exp_op3224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERACTION_in_interactionDeclaration3258 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_interactionDeclaration3262 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interactionDeclaration3264 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_interaction_in_interactionDeclaration3268 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_interactionDeclaration3271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3294 = new BitSet(new long[]{0x0000021000000000L,0x0000000110000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3298 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_interaction3303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_interaction3312 = new BitSet(new long[]{0x0000021000000000L,0x0000000110000000L});
    public static final BitSet FOLLOW_interactionType_in_interaction3316 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_interaction3319 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_interaction_in_interaction3323 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_interaction3326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_interactionType3359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRODUCT_in_functionCall3393 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_PERMUTE_in_functionCall3397 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_functionCall3400 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_functionCall3404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_functionCall3406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINTLN_in_printStatement3424 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3426 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_printStatement3440 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_printStatement3442 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_toPrint_in_printStatement3446 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_printStatement3449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_toPrint3470 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_toPrint_prime_in_toPrint3475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_toPrint_prime3501 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_toPrint_in_toPrint_prime3505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_imperativeStatements3525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forall_iterator_in_imperativeStatements3531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_imperativeStatements3537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ifStatement3563 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3569 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3576 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3578 = new BitSet(new long[]{0x7A00010F899013C0L,0x000000000F8C389EL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3589 = new BitSet(new long[]{0x0000000060000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_set_in_ifStatement3604 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_ifStatement3610 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_imp_condition_in_ifStatement3614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_ifStatement3617 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3619 = new BitSet(new long[]{0x7A00010F899013C0L,0x000000000F8C389EL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3630 = new BitSet(new long[]{0x0000000060000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_set_in_ifStatement3646 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_ifStatement3652 = new BitSet(new long[]{0x7A00010F899013C0L,0x000000000F8C389EL});
    public static final BitSet FOLLOW_listOfStatements_in_ifStatement3660 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_RIGHTCUR_in_ifStatement3663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_imp_condition3687 = new BitSet(new long[]{0x0044200000610000L,0x02C9A00000000000L,0x0000600164D000C0L});
    public static final BitSet FOLLOW_relationalOperators_in_imp_condition3692 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_atom_in_imp_condition3696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_forall_iterator3712 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator3721 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_COLON_in_forall_iterator3723 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_forall_iterator3729 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_forall_iterator3731 = new BitSet(new long[]{0x7A00010F899013C0L,0x000000000F8C389EL});
    public static final BitSet FOLLOW_listOfStatements_in_forall_iterator3736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_RIGHTCUR_in_forall_iterator3743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_for_loop3755 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_for_loop3761 = new BitSet(new long[]{0x0200000000000100L,0x0000000000040000L});
    public static final BitSet FOLLOW_variableDeclaration_in_for_loop3765 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop3768 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_imp_condition_in_for_loop3772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMIC_in_for_loop3775 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_in_for_loop3779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_for_loop3782 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_LEFTCUR_in_for_loop3784 = new BitSet(new long[]{0x7A00010F899013C0L,0x000000000F8C389EL});
    public static final BitSet FOLLOW_listOfStatements_in_for_loop3792 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_RIGHTCUR_in_for_loop3799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_listOfStatements3818 = new BitSet(new long[]{0x7A00010F899013C2L,0x000000000F8C389EL});
    public static final BitSet FOLLOW_multExpr_in_expr3846 = new BitSet(new long[]{0x0008000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_PLUS_in_expr3854 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_multExpr_in_expr3858 = new BitSet(new long[]{0x0008000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_MINUS_in_expr3865 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_multExpr_in_expr3869 = new BitSet(new long[]{0x0008000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_atom_in_multExpr3895 = new BitSet(new long[]{0x0020000000004002L});
    public static final BitSet FOLLOW_MULT_in_multExpr3907 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_DIV_in_multExpr3911 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_atom_in_multExpr3916 = new BitSet(new long[]{0x0020000000004002L});
    public static final BitSet FOLLOW_NUMBER_in_atom3946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom3952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom3962 = new BitSet(new long[]{0x0400000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_atom3967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom3973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_atom3986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_atom3992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom4002 = new BitSet(new long[]{0x0000100000008000L});
    public static final BitSet FOLLOW_object_access_in_atom4010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTP_in_atom4028 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_atom4030 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_atom4033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_atom4043 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_list_in_atom4045 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_atom4048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_list4074 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COMMA_in_list4081 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_list4085 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_DOT_in_object_access4125 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_object_access4129 = new BitSet(new long[]{0x0000100000008000L});
    public static final BitSet FOLLOW_LEFTSBR_in_object_access4137 = new BitSet(new long[]{0x0408180001040000L,0x0000000000030020L});
    public static final BitSet FOLLOW_expr_in_object_access4142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_RIGHTSBR_in_object_access4146 = new BitSet(new long[]{0x0000100000008000L});
    public static final BitSet FOLLOW_object_access_in_object_access4153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolStatement_in_dataExchange4179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genbankStatement_in_dataExchange4189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pigeonStatement_in_dataExchange4197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_registryStatement_in_dataExchange4205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importStatement_in_dataExchange4213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HASHMARK_in_includeStatement4232 = new BitSet(new long[]{0x0000000800000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_set_in_includeStatement4236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_in_includeStatement4244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_importStatement4265 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_importStatement4271 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_in_importStatement4275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_importStatement4279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBOL_in_sbolStatement4301 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_sbolStatement4303 = new BitSet(new long[]{0x0000000400020000L,0x0000000004000000L});
    public static final BitSet FOLLOW_sbolExportStatement_in_sbolStatement4306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sbolImportStatement_in_sbolStatement4313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPORT_in_sbolExportStatement4330 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolExportStatement4332 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_sbolExportStatement4336 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COMMA_in_sbolExportStatement4338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_in_sbolExportStatement4342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolExportStatement4344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sbolImportStatement4367 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_sbolImportStatement4373 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_in_sbolImportStatement4377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_sbolImportStatement4379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GENBANK_in_genbankStatement4399 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_genbankStatement4401 = new BitSet(new long[]{0x0000000400020000L,0x0000000004000000L});
    public static final BitSet FOLLOW_genbankImportStatement_in_genbankStatement4406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genbankExportStatement_in_genbankStatement4411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPORT_in_genbankExportStatement4434 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_genbankExportStatement4436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_genbankExportStatement4438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_genbankImportStatement4464 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_genbankImportStatement4470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_genbankImportStatement4472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_genbankImportStatement4479 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_genbankImportStatement4485 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_genbankImportStatement4489 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COMMA_in_genbankImportStatement4491 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_in_genbankImportStatement4495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_genbankImportStatement4497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_pigeonStatement4513 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_pigeonStatement4519 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_pigeonStatement4523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_pigeonStatement4525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGISTRY_in_registryStatement4542 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_registryStatement4544 = new BitSet(new long[]{0x0000000400000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_set_in_registryStatement4546 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_registryStatement4552 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_in_registryStatement4556 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_registryStatement4558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_testStatements4578 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4580 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_testStatements4584 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_testStatements4586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_SIZE_in_testStatements4588 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFTP_in_testStatements4590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4592 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4594 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_EQUALS_in_testStatements4596 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_testStatements4600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_RIGHTP_in_testStatements4602 = new BitSet(new long[]{0x0000000000000002L});

}