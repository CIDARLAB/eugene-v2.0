grammar Eugene;

options {
language=Java;
output=AST;
}

tokens {
	PLUS = '+' ;
	MINUS = '-' ;
	MULT = '*' ;
	DIV = '/' ;
	LEFTP = '(';
	RIGHTP = ')';
	LEFTSBR = '[';
	RIGHTSBR = ']';
	LEFTCUR = '{';
	RIGHTCUR = '}';
	EQUALS = '=';
	UNDERS = '_';
	SEMIC = ';';
	COLON = ':';
	COMMA = ',';
	DOT = '.';
	PIPE = '|';
	NUM = 'num';
	INT = 'int';
	BOOL = 'bool';
	BOOLEAN = 'boolean';
	IMAGE = 'Image';
	PROPERTY = 'Property';
	TYPE = 'Type';
	PART_TYPE = 'PartType';
	PART = 'Part';
	DEVICE = 'Device';
	RULE = 'Rule';
	TXT = 'txt';
	ADDPROPS = 'addProperties';
	PRINT = 'print';
	SIZE = 'size';
	PRINTLN = 'println';
	AMP = '&';
	REF = 'ref';
	IMAGE = 'Image';
	NEQUAL = '!=';
	LTHAN = '<';
	GTHAN = '>';
	LEQUAL = '<=';
	GEQUAL = '>=';
	UC_AND = 'AND';
	LC_AND = 'and';
	LOG_AND = '/\\';
	UC_OR = 'OR';
	LC_OR = 'or';
	LOG_OR = '\\/';
	UC_NOT = 'NOT';
	LC_NOT = 'not';
	LOG_NOT = '!';
	ASSERT = 'Assert';
	NOTE = 'Note';
	LC_IF = 'if';
	UC_IF = 'IF';
	LC_ELSEIF = 'elseif';
	UC_ELSEIF = 'ELSEIF';	
	LC_ELSE = 'else';
	UC_ELSE = 'ELSE';
	UC_ON = 'ON';
	LC_ON = 'on';
	TRUE = 'true';
	FALSE = 'false';
	PERMUTE = 'permute';
	PRODUCT = 'product';
	STRICT = 'strict';
	FLEXIBLE = 'flexible';
	COLLECTION = 'Collection';
	ARRAY = 'Array';
	SBOL = 'SBOL';
	EXPORT = 'export';
	GENBANK = 'Genbank';
	PIGEON = 'Pigeon';
	LC_PIGEON = 'pigeon';
	REGISTRY = 'Registry';
	EXIT_UC = 'EXIT';
	EXIT_LC = 'exit';

	INTERACTION = 'Interaction';
	UC_REPRESSES = 'REPRESSES';
	LC_REPRESSES = 'represses';
	UC_INDUCES = 'INDUCES';
	LC_INDUCES = 'induces';
	
	LC_INCLUDE = 'include';
	UC_INCLUDE = 'INCLUDE';
	
	LC_IMPORT = 'import';
	UC_IMPORT = 'IMPORT';

	HASHMARK = '#';	

	LC_FORALL = 'forall';
	UC_FORALL = 'FORALL';
	LC_FOR = 'for';
	UC_FOR = 'FOR';

	ARROW = '-->';
	GRAMMAR = 'Grammar';
}


@header {
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

}

@lexer::header {
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
BOSTON UNIVERSITY HAS BEEN ADVISED OF THcE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
*/

package org.cidarlab.eugene.parser;
}

@lexer::members {
class SaveStruct {
    public CharStream input;
    public int marker;
    SaveStruct(CharStream input) {
        this.input = input;
        this.marker = input.mark();
    }
}
 
Stack<SaveStruct> includes = new Stack<SaveStruct>();
 
// We should override this method for handling EOF of included file
public Token nextToken(){
    Token token = super.nextToken();
 
    if(token.getType() == Token.EOF && !includes.empty()){
        // We've got EOF and have non empty stack
        SaveStruct ss = includes.pop();
        setCharStream(ss.input);
        input.rewind(ss.marker);

        //this should be used instead of super [like below] to handle exits from nested includes
        //it matters, when the 'include' token is the last in previous stream (using super, lexer 'crashes' returning EOF token)
        token = this.nextToken();
    }
 
    // Skip first token after switching on another input.
    // You need to use this rather than super as there may be nested include files
    if(((CommonToken)token).getStartIndex() < 0) {
        token = this.nextToken();
    }
    
    return token;
}

public void includeFile(String name) {
    try {
        // save current lexer's state
        SaveStruct ss = new SaveStruct(input);
        includes.push(ss);
 
        // switch on new input stream
        setCharStream(new ANTLRFileStream(name));
        reset();
    } catch (Exception fnf) {
        fnf.printStackTrace();
    }
}
}

@members {
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

}

/*
 TODO-List:
 - exception handling
 - logging
 - dynamic naming
 - functions -> return exception
 */
 
/* start of program consisting of statements */
prog
@before {
if(null == this.interp) {
    this.interp = new Interp(new Sparrow());
}
}
	:	(statement[false])+ EOF!
	;


statement[boolean defer] 
    returns [NamedElement objReturnValue]
	:	
		includeStatement[defer] (SEMIC)?
	|	declarationStatement[defer] SEMIC	
	|	printStatement[defer] SEMIC
	|	assignment[defer] SEMIC
	|	functionCall[defer] SEMIC
	|	de=dataExchange[defer] SEMIC {
if(!defer) {
    try {
        System.out.println("[Parser.dataExchange] -> " + $de.e);
        this.interp.put($de.e);
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}	
	}
	|	imperativeStatements[defer]
	|	predefined_statements[defer] SEMIC
	;

predefined_statements[boolean defer] 
	:	testStatements[defer]
	|	(EXIT_LC | EXIT_UC) {
if(!defer) {
    printError("exiting...");
}
	}
	;


declarationStatement[boolean defer]
	returns [String name]
	:	v=variableDeclaration[defer] {
if(!defer) {
    $name = $v.varname;
}	
	}
	|	containerDeclaration[defer]
	|	propertyDeclaration[defer]
	|	typeDeclaration[defer]
	|	instantiation[defer]
	|	interactionDeclaration[defer]
	|	ruleDeclaration[defer]
	|	grammarDeclaration[defer]
	|	deviceDeclaration[defer]
	;

variableDeclaration[boolean defer]
	returns [String varname]
	:	NUM n=numdecl[defer]  /* declaration of nums, which can be both real and integer*/ {
if(!defer) {
    $varname = $n.varname;
}	
	}
	|	TXT t=txtdecl[defer]  /* declaration of text instances*/ {
if(!defer) {
    $varname = $t.varname;
}	
	}
	|	TXT LEFTSBR RIGHTSBR tl=txtlistdecl[defer]  /*  declaration of text lists */ {
if(!defer) {
    $varname = $tl.varname;
}	
	}
	|	NUM LEFTSBR RIGHTSBR nl=numlistdecl[defer]  /* declaration of num lists */ {
if(!defer) {
    $varname = $nl.varname;
}	
	}
	|	(BOOLEAN|BOOL) b=booldecl[defer]  /* declaration of boolean instances*/ {
if(!defer) {
    $varname = $b.varname;
}	
	}
	;

numdecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer) {
			declareVariableNoValue($ID.text, EugeneConstants.NUM);
			$varname = $ID.text;
		}
		} (COMMA numdecl[defer])?
	|	ID EQUALS (ex=expr[defer])
		{
		if(!defer) {
			declareVariableWithValueNum($ID.text, $ex.p, $ex.index);
			$varname = $ID.text;
		}
		}  (COMMA numdecl[defer])?
	;

txtdecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer) {
			declareVariableNoValue($ID.text, EugeneConstants.TXT);
			$varname = $ID.text;
		}
		} (COMMA txtdecl[defer])?

	|	var=ID EQUALS let=expr[defer]
		{
		if(!defer) {
			declareVariableWithValueTxt($var.text, $let.p, $let.index);
			$varname = $ID.text;
		}
		} (COMMA txtdecl[defer])?
	;

txtlistdecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer) {
			declareVariableNoValue($ID.text, EugeneConstants.TXTLIST);
			$varname = $ID.text;
		}
		} (COMMA txtlistdecl[defer])?
	|	var=ID EQUALS {typeList = EugeneConstants.TXT;} let=expr[defer]
		{
		if(!defer) {
			declareVariableWithValueTxtList($var.text, $let.p);
			$varname = $ID.text;
		}
		} (COMMA txtlistdecl[defer])?
	;

numlistdecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer) {
			declareVariableNoValue($ID.text, EugeneConstants.TXTLIST);
			$varname = $ID.text;
		}
		} (COMMA numlistdecl[defer])?
	|	var=ID EQUALS { typeList = EugeneConstants.NUM;}let=expr[defer]
		{
		if(!defer) {
			declareVariableWithValueNumList($var.text, $let.p);
			$varname = $ID.text;
		}
		} (COMMA numlistdecl[defer])?
	;

booldecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer) {
			declareVariableNoValue($ID.text, EugeneConstants.BOOLEAN);
			$varname = $ID.text;
		}
		} (COMMA booldecl[defer])?
	|	var=ID EQUALS let=expr[defer]
		{
		if(!defer) {
			declareVariableWithValueBool($var.text, $let.p);
			$varname = $ID.text;
		}
		}
	;
	
propertyDeclaration[boolean defer]
	:	PROPERTY nameToken=ID LEFTP typeToken=propertyType RIGHTP {
if(!defer) {
    try {
        interp.createProperty(
            $nameToken.text, 
            $typeToken.type);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}
        }
	;

propertyType 
	returns [String type]
	:	TXT {
$type = EugeneConstants.TXT;	
	}
	|	TXT LEFTSBR RIGHTSBR {
$type = EugeneConstants.TXTLIST;	
	}
	|	NUM  {
$type = EugeneConstants.NUM;	
	}
	|	NUM LEFTSBR RIGHTSBR {
$type = EugeneConstants.NUMLIST;	
	}
	|	BOOLEAN {
$type = EugeneConstants.BOOLEAN;	
	}
	;

/*------------------------------------------------------------------
 * TYPES (e.g. PartType, DeviceType, ...)
 *------------------------------------------------------------------*/ 

typeDeclaration[boolean defer]
	:	partTypeDeclaration[defer]
	|	(TYPE) nameToken=ID (LEFTP (lstToken=listOfIDs[defer])? RIGHTP)? {
if(!defer) {
    try {
        interp.createType(
            $nameToken.text, 
            $lstToken.lstElements);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}
        }	
	;
	
partTypeDeclaration[boolean defer] 
	:	(PART|PART_TYPE)  nameToken=ID (LEFTP (lstToken=listOfIDs[defer])? RIGHTP)? {
if(!defer) {
    try {
        interp.createPartType(
            $nameToken.text, 
            $lstToken.lstElements);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}
        }
	;

/*------------------------------------------------------------------
 *  CONTAINERS / SETS / ARRAYS
 *------------------------------------------------------------------*/ 
containerDeclaration[boolean defer]
	returns [NamedElement ne]
	:	(c=COLLECTION | (a=ARRAY LEFTSBR RIGHTSBR)) name=ID {
if(!defer) {
    if(null != c) {
        $ne = new EugeneCollection($name.text);
    } else if(null != a) {
        $ne = new EugeneArray($name.text);
    }

    /*
     * push it on the stack
     */
    this.interp.push((StackElement)$ne);
    
}	
	}	(LEFTP (list_of_declarations[defer])? RIGHTP)? {
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
	;

list_of_declarations[boolean defer]	
        returns [List<NamedElement> elements]
	:	( ds=declarationStatement[defer] | at=atom[defer] {
if(!defer) {
    try {
        if(null != $at.element) {
            this.interp.addToContainer($at.element);
        } else {
            throw new EugeneException("Cannot add " + $at.text + " to a Eugene container!");
        }
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}	
	})	( COMMA lod=list_of_declarations[defer] )?
	;

/*------------------------------------------------------------------
 *  INSTANTIATIONS
 *------------------------------------------------------------------*/ 	
instantiation[boolean defer] 
@init {
NamedElement type = null;
}
	:	t=ID {
if(!defer) {
    try {
        type = this.interp.get($t.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
    
    if(null==type) {
        printError("I don't know anything about "+ $t.text + "!");
    }         
             
    if(!(type instanceof ComponentType)) {
        printError($t.text+" is not a type!");
    }                  
}	
	}	nameToken=ID ( LEFTP (dotToken=listOfDotValues[defer]|valueToken=listOfValues[defer, (ComponentType)type])? RIGHTP )? {
if(!defer) {
    try {

        if(null != dotToken) {
            this.interp.instantiate(
                (ComponentType)type, $nameToken.text,
                this.propertyListHolder,
                this.propertyValuesHolder);    	
        } else if (null != valueToken) {
            this.interp.instantiate(
                (ComponentType)type, $nameToken.text,
                this.propertyListHolder,
                this.propertyValuesHolder);    	
        } else {
            this.interp.instantiate(
                (ComponentType)type, $nameToken.text,
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
	;

// e.g. 
// PartType Promoter(Sequence, Orientation);
// Promoter pLac(.Sequence(".."),.Orientation(".."));
// this rule only applies for PartType instances!
listOfDotValues [boolean defer] 
	:	DOT prop=ID
		{
if(!defer) {		
    try {
        addToPropertyListHolder($prop.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}			
		} LEFTP v1=expr[defer]
			{
if(!defer) {			
    try {
        addToPropertyValuesHolder($prop.text, $v1.p, $v1.index);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}				
			} RIGHTP (COMMA DOT p=ID
				{
if(!defer) {			
    try {
        addToPropertyListHolder($p.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}				
				} LEFTP v2=expr[defer]
					{
if(!defer) {			
    try {
        addToPropertyValuesHolder($p.text, $v2.p, $v2.index);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}				
					} RIGHTP )*
	;

listOfValues[boolean defer, ComponentType pt]
	:
		{
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
		} val1=expr[defer] {
if(!defer) {
    propertyValuesHolder.add($val1.p);
}				
			} (COMMA {
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
               } val2=expr[defer] {
if(!defer) {
    propertyValuesHolder.add($val2.p);
}				
		} )*
	;
	
deviceDeclaration[boolean defer]
	:	DEVICE n=ID (LEFTP (dcs=deviceComponents[defer])? RIGHTP)?  {
if(!defer) {
    try {
        interp.createDevice(
            $n.text, 
            $dcs.lstComponents,
            $dcs.lstOrientations);
    } catch(Exception e) {
        printError(e.getMessage());
    }
}	
	}
	;

deviceComponents[boolean defer] 
	returns [List<List<NamedElement>> lstComponents, List<List<Orientation>> lstOrientations]
@init {
$lstComponents = new ArrayList<List<NamedElement>>();
$lstOrientations = new ArrayList<List<Orientation>>();
}	
	:	s=selection[defer] {
if(!defer) {
    $lstComponents.add($s.components);
    $lstOrientations.add($s.orientations);
}	
	}	(',' dcs=deviceComponents[defer] {
if(!defer) {
    $lstComponents.addAll($dcs.lstComponents);
    $lstOrientations.addAll($dcs.lstOrientations);
}	
	}	)?
	;

selection[boolean defer] 
	returns [List<NamedElement> components, List<Orientation> orientations]
	:	LEFTSBR sl=selection_list[defer] RIGHTSBR {
if(!defer) {
    $components = $sl.components;
    $orientations = $sl.orientations;
}	
	}
	|	dc=device_component[defer] {
if(!defer) {
    $components = new ArrayList<NamedElement>(1);
    $components.add($dc.component);
    
    $orientations = new ArrayList<Orientation>();
    $orientations.add($dc.orientation);
}	
	}
	;
	
selection_list[boolean defer] 
	returns [List<NamedElement> components, List<Orientation> orientations]	
@init{
$components = new ArrayList<NamedElement>();
$orientations = new ArrayList<Orientation>();
}
	:	dc=device_component[defer] {
if(!defer) {
    $components.add($dc.component);
    $orientations.add($dc.orientation);
}	
	}	(PIPE sl=selection_list[defer] {
if(!defer) {
    $components.addAll($sl.components);
    $orientations.addAll($sl.orientations);
}	
	}	)?
	;

device_component[boolean defer]
	returns [NamedElement component, Orientation orientation]
	:	(directionToken=(MINUS|PLUS))? idToken=ID {
if(!defer) {
    try {
        NamedElement ne = interp.get($idToken.text);
        if(null == ne) {
           printError($idToken.text+" is not declared.");
        }
    
        if(ne instanceof Component) { 
            $component = (Component)ne;
        } else if(ne instanceof ComponentType) {
            $component = (ComponentType)ne;        
        } else {
            printError($idToken.text+" is neither a Device, Part, nor Part Type.");
        }

        if(directionToken != null) {        	        
            if("-".equals($directionToken.text)) {            
                $orientation = Orientation.REVERSE;
            } else {
                $orientation = Orientation.FORWARD;
            }
        } else {
            $orientation = Orientation.UNDEFINED;
        }
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}	
	}
	;
 
 /*-------------------------------------------------------------
  * ASSIGNMENTS
  *-------------------------------------------------------------
  *
  * for assignments, we first process the right-hand-side (RHS) of the assignment.
  * then, we assign the RHS to the left-hand-side (LHS) element. 
  * 
  * if the LHS element has not been declared, then we declare 
  * it with the same type of the RHS.
  *
  * if the types do not match, we print an error. 
  *
  * In Eugene, the user can specify if the RHS should be assigned 
  * by-reference (&) or by-value. Per default, we use by-value.
  *
  */
assignment[boolean defer]
	:	{
if(!defer) {
    // we first initialize the root, parent, and child
    // NamedElements for the lhs assignments
    this.root = null;
    this.parent = null;
    this.child = null;
}	
	}	lhs=lhs_assignment[defer, null, null] EQUALS (a=AMP)? rhs=rhs_assignment[defer] {
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
           this.interp.assignment($lhs.tree, $rhs.e);
         */
        this.interp.assignment(
                        $lhs.text, 
                        $rhs.e);
        
        /* --- OLD VERSION         
        this.interp.assignTo(
                    $lhs.name, 
                    this.child,
                    $lhs.id_out, 
                    $lhs.idx_out, 
                    $rhs.e, 
                    a!=null);
        this.interp.updateElement(this.root);
           --- */
    } catch(EugeneException ee) {
        printError(ee.toString());    
    }
}	
	}
	;

lhs_assignment[boolean defer, String id_in, Variable idx_in]
	returns [String name, String id_out = null, Variable idx_out = null]
	:	idToken=ID {
if(!defer) {
    try {
    
        $name = $idToken.text;
        $id_out = $idToken.text;
        $idx_out = idx_in;
        
    	if(this.root == null) {
    	    this.root = this.interp.get($idToken.text);
    	}

        if(null == this.parent) {
            this.parent = this.interp.get($idToken.text);
            this.child = null;
        } else {
            this.child = this.parent.getElement($idToken.text);
            if(null == this.child) {
                throw new EugeneException("The " + parent.getName() + " element does not contain "+
                    "an element named " + $idToken.text + "!");
            }
        }
        
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	}	ac=lhs_access[defer, id_in, idx_in] {
if(!defer) {
    $id_out = $ac.id_out;
    $idx_out = $ac.idx_out;
}	
	}
	;	
	
lhs_access[boolean defer, String id_in, Variable idx_in]
	returns [String id_out, Variable idx_out]
	:	{
if(!defer) {
    $id_out = id_in;
    $idx_out = idx_in;
}	
	}
	|	(DOT i=ID {
//	}	lhs=lhs_assignment[defer, id_in, idx_in] {
if(!defer) {

    try {
    
        if(null != this.child) {
            this.parent = this.child;
        }
        if(this.parent instanceof Component) {
            this.child = ((Component)this.parent).getElement($i.text);
        } else {
            throw new EugeneException("Cannot access " + $i.text + " of " + this.parent);
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
    
    $id_out = $i.text;
    $idx_out = null;
}	
	}
	|	LEFTSBR (exp=expr[defer]) RIGHTSBR {
if(!defer) {
    try {
    
    	if(null != $exp.p) {
    	    if(EugeneConstants.NUM.equals(($exp.p).getType())) {
    	        // constant index
    	        $idx_out = $exp.p;
    	    } else if (EugeneConstants.TXT.equals(($exp.p).getType())) {
               // variable index
               NamedElement idx_var = this.interp.get($exp.p.getName());
               if(null == $idx_out) {
                   throw new EugeneException("Cannot find " + $exp.p.getName());
               }
               if(!($idx_out instanceof Variable)) {
                   throw new EugeneException($exp.p.getName() + " is not a variable!");
               }
               if(!EugeneConstants.NUM.equals(((Variable)$idx_out).getType())) {
                   throw new EugeneException($exp.p.getName() + " is not a variable of type num!");
               }
               
               $idx_out = (Variable)idx_var;
    	    } else {
                throw new EugeneException("Invalid array index!");
    	    }
    	}
    	
        if(idx_in != null) {
            this.child = this.parent.getElement((int)$idx_in.getNum());

            if(null == this.child) {
                throw new EugeneException("The " + parent.getName() + " element does not contain "+
                    "an element at " + idx_in.getNum() + "!");
            }
        } else {
            this.child = this.parent;
        }
         
        $id_out = null;
    } catch(EugeneException ee) {
        printError(ee.toString());
    }

    $idx_out = $exp.p;
}	
	})	ac=lhs_access[defer, $id_out, $idx_out] {
if(!defer) {
    $id_out = $ac.id_out;
    $idx_out = $ac.idx_out;
}	
	}
	;	
	
rhs_assignment[boolean defer]	
	returns [NamedElement e]
	:	fc=functionCall[defer] {
if(!defer) {
    $e = $fc.e;
}	
	}
	|	de=dataExchange[defer] {
if(!defer) {
    $e = $de.e;
}	
	}
	|	exp=expr[defer] {
if(!defer) {
    if($exp.element != null) {
        $e = $exp.element;
    } else {
        $e = $exp.p;
    }
}	
	}
	;

listOfIDs[boolean defer] 
	returns [List<NamedElement> lstElements]
@init {
$lstElements=new ArrayList<NamedElement>();
}
	:	idToken=ID { 
if(!defer)
    try {
        NamedElement ne = interp.get($idToken.text);
        if(null == ne) {
           printError($idToken.text+" is not declared.");
        }
        $lstElements.add(ne);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	 	(',' lstToken=listOfIDs[defer] {
if(!defer){
    $lstElements.addAll($lstToken.lstElements);
}
        }	)?
	;
	
ruleDeclaration[boolean defer]
	returns [Rule rule]
	:	RULE name=ID LEFTP ( ((LC_ON|UC_ON) device=ID COLON)? {
if(!defer) {
    try {
        if(null == device) {
            $rule = interp.createRule($name.text, null);
        } else {
            $rule = interp.createRule($name.text, $device.text);
        }
    } catch(Exception e) {
        printError(e.getMessage());
    }
}		
	}	cnf=cnf_rule[defer] ) RIGHTP {
if(!defer) {
    $rule.setLogicalAnd($cnf.lAnd);

    /*
     *  ONLY FOR TESTING
     */    
//    this.interp.executeRule($rule); 
}
	}
	;

ruleOperator[boolean defer]
	:	ruleOperators
//	|	relationalOperators
	;

ruleOperators
	:	('CONTAINS'|'contains')
	|	('EXACTLY'|'exactly')
	|	('MORETHAN'|'morethan')
	|	('SAME_COUNT'|'same_count')
	|	('WITH'|'with')
	|	('THEN'|'then')
	|	('STARTSWITH'|'startswith')
	|	('ENDSWITH'|'endswith')
	|	('BEFORE'|'before')
	|	('ALL_BEFORE'|'all_before')
	|	('SOME_BEFORE'|'some_before')
	|	('AFTER'|'after')
	|	('ALL_AFTER'|'all_after')
	|	('SOME_AFTER'|'some_after')
	|	('NEXTTO'|'nextto')
	|	('ALL_NEXTTO'|'all_nextto')
	|	('SOME_NEXTTO'|'some_nextto')
	|	('ALWAYS_NEXTTO'|'always_nextto')
	|	('EQUALS'|'equals')
	|	('MATCHES'|'matches')
	|	('FORWARD'|'forward')
	|	('ALL_FORWARD'|'all_forward')
	|	('SOME_FORWARD'|'some_forward')
	|	('REVERSE'|'reverse')
	|	('ALL_REVERSE'|'all_reverse')
	|	('SOME_REVERSE'|'some_reverse')
	|	('SAME_ORIENTATION'|'same_orientation')
	|	('ALL_SAME_ORIENTATION'|'all_same_orientation')
	|	('SOME_SAME_ORIENTATION'|'some_same_orientation')
	|	('REPRESSES'|'represses')
	|	('INDUCES'|'induces')
	|	('DRIVES'|'drives')
	|	('ALTERNATE_ORIENTATION'|'alternate_orientation')
	|	('NOTCONTAINS'|'notcontains')
	|	('NOTEXACTLY'|'notexactly')
	|	('NOTMORETHAN'|'notmorethan')
	|	('NOTWITH'|'notwith')
	|	('NOTTHEN'|'notthen')
	|	('NOTEQUALS'|'notequals')
	|	('NOTMATCHES'|'notmatches')
	;


relationalOperators
	:	EQUALS EQUALS
	|	NEQUAL
	|	LTHAN
	|	GTHAN
	|	LEQUAL
	|	GEQUAL
	|	('CONTAINS'|'contains')
	|	('NOTCONTAINS'|'notcontains')
	|	('MATCHES'|'matches')
	|	('NOTMATCHES'|'notmatches')
	|	('STARTSWITH'|'startswith')
	|	('ENDSWITH'|'endswith')
	|	('EQUALS'|'equals')
	|	('NOTEQUALS'|'notequals')
	|	('SOUNDSLIKE'|'soundslike')
	;

cnf_rule[boolean defer]
	returns [LogicalAnd lAnd]
	:	(c=or_predicate[defer] {
if(!defer) {
    if(null == $lAnd) {
        $lAnd = new LogicalAnd();
    }
    
    $lAnd.getPredicates().add($c.p);
}	
	}) ( (LC_AND|UC_AND|LOG_AND) cnf=cnf_rule[defer] {
if(!defer) {
    $lAnd.union($cnf.lAnd);
}	
	})?
	;

or_predicate[boolean defer] 
	returns [Predicate p]
@init{
LogicalOr lor = null;
}	
	:	n1=negated_predicate[defer] {
if(!defer) {
    $p = $n1.p;
}	
	}	((LC_OR|UC_OR|LOG_OR) n2=negated_predicate[defer] {
if(!defer) {
    try {
        if(null == lor) {
            lor = this.interp.logicalOr($n1.p, $n2.p);
        } else {
            lor = this.interp.logicalOr(lor, $n2.p); 
        }
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	}	)*  {
if(!defer) {
    if(null != lor) {
        $p = lor;
    }
}	
	}
	;
	
negated_predicate[boolean defer]
	returns [Predicate p]
	:	((UC_NOT|LC_NOT|LOG_NOT) c=predicate[defer] {
if(!defer) {
    try {
        $p = this.interp.negate($c.p);
    } catch(Exception e) {
        printError(e.getMessage());
    }
}
	}
	|	c=predicate[defer] {
if(!defer) {
    $p = $c.p;
}	
	}) 
	;
 	
predicate[boolean defer]
	returns [Predicate p]
	:	(lhs=operand[defer] {
addToken($lhs.text);	
	})? 	op=ruleOperator[defer] {
addToken($op.text);	
	} 	(rhs=operand[defer] {
addToken($rhs.text);	
	})? {

try {
    $p = this.interp.createPredicate($lhs.o, $op.text, $rhs.o);
//    $p = this.interp.createPredicate(this.tokens);
} catch(EugeneException ee) {
    printError(ee.getMessage());
}

// reset the global token array
this.tokens = null;
	}
	|	i=ID {
if(!defer) {
    try {
        NamedElement ne = this.interp.get($i.text);
        if(null == ne) {
            printError($i.text+" is not defined.");
        }
        if(!(ne instanceof Rule)) {
            printError($i.text+" is not a Rule.");
        }
    
        $p = ((Rule)ne);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}		
	}
	|	exp=expressionRule[defer] {
if(!defer) {
    $p = $exp.p;
}	
	}
	;

operand[boolean defer]
	returns [ArrangementOperand o] 
@init {
NamedElement element = null;
int constant = -1;
int index = -1;
}		
	:	(i=ID {
if(!defer) {
    if(!this.interp.contains($i.text)) {
        printError($i.text+" not defined.");
    }
    try {
        element = this.interp.get($i.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}
}
	|	n=NUMBER {
if(!defer) {
    constant = Integer.parseInt($n.text);
}	
	}
	|	'[' n=NUMBER ']' {
if(!defer) {
    index = Integer.parseInt($n.text);
}	
	}) {
if(!defer) {
    try {
        $o = new ArrangementOperand(element, constant, index);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}
	}
	;

/*------------------------------------------------------------------
 * EXPRESSION RULES
 * - to select appropriate parts from the Part Library (e.g. Promoter.Strength > n)
 *------------------------------------------------------------------*/
expressionRule[boolean defer]
	returns [Predicate p]
	:	lhs=expression[defer] op=exp_op[defer] rhs=expression[defer] {
if(!defer) {
    try {
        $p = new ExpressionConstraint($lhs.exp, $op.text, $rhs.exp);
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}
	}
	;
		
expression[boolean defer]
	returns [Expression exp]
	:	lhs=exp_operand[defer] {
if(!defer) {
    $exp = new Expression($lhs.eop, null, null);
}
	}	( expop=exp_operator[defer] rhs=expression[defer] {
if(!defer) {
    $exp = new Expression($lhs.eop, $expop.op, $rhs.exp);
}	
	}	)? 
	|	LEFTP expression[defer] RIGHTP {
if(!defer) {
    	
}	
	}
	;

exp_operator[boolean defer]
	returns [Expression.ExpOp op]
	:	PLUS {
if(!defer) {	
    $op = Expression.ExpOp.PLUS;	
}
	} 
	|	MINUS {
if(!defer) {	
    $op = Expression.ExpOp.MINUS;	
}
	}
	|	MULT {
if(!defer) {	
    $op = Expression.ExpOp.MULT;	
}
	}
	|	DIV {
if(!defer) {	
    $op = Expression.ExpOp.DIV;	
}
	}
	;	
	
exp_operand[boolean defer]
	returns [ExpressionOperand eop]	
@init{
List<NamedElement> elements = null;
NamedElement ne = null;
}	
	:	(i1=ID DOT {
if(!defer) {
    try {
        if(ne == null) {
            ne = this.interp.get($i1.text);
            if(null == ne) {
                printError($i1.text+" is not defined.");
            }
        } else {
            if(!(ne instanceof Device)) {
                printError($i1.text+" is not a Device.");
            }
        
            String name = ne.getName();
            try {
                ne = ((Device)ne).getComponent($i1.text);
            } catch(EugeneException ee) {
                printError(ee.getMessage());
            }
            if(null == ne) {
                printError(name+" does not contain "+$i1.text+".");
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
	}	)* i2=ID {
if(!defer) {
    try {
        NamedElement property = null;

        if(ne == null) {
            property = this.interp.get($i2.text);
            if(null == property) {
                printError($i2.text+" is not defined.");
            }
        } else {
            if(!(ne instanceof Component) && !(ne instanceof ComponentType)) {
                printError($i2.text+" is not a Device, Part, nor Part Type.");
            }
        
            if(ne instanceof ComponentType) {
                property = ((ComponentType)ne).getProperty($i2.text);
            } else if(ne instanceof Component) {
                property = ((Component)ne).getProperty($i2.text);
            }
            if(null == property) {
                printError(ne.getName()+" does not contain "+$i2.text+".");
            }
        }

        if(!(property instanceof Property)) {
            printError($i2.text+" is not a Property.");
        }     
    
        $eop = new ExpressionOperand(elements, (Property)property);

    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	} 	(LEFTSBR n=NUMBER RIGHTSBR {
if(!defer) {
    if(null != $eop) {
        try {
            $eop.addIndex($n.text);
        } catch(EugeneException ee) {
            printError(ee.getMessage());
        }
    }
}	
	}	)*
	|	n=NUMBER {
if(!defer) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($n.text);
    $eop = new ExpressionOperand(v);
}	
	}
	|	MINUS n=NUMBER {
if(!defer) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($n.text) * -1;
    $eop = new ExpressionOperand(v);
}	
	}
	|	r=REAL {
if(!defer) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($r.text);
    $eop = new ExpressionOperand(v);
}	
	}
	|	MINUS r=REAL {
if(!defer) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($r.text) * -1.0;
    $eop = new ExpressionOperand(v);
}	
	}
	|	s=STRING {
if(!defer) {
    Variable v = new Variable(null, EugeneConstants.TXT);
    v.txt = $s.text;
    $eop = new ExpressionOperand(v);
}		
	}
//	|	expr[defer]
	;

regexp[boolean defer] 
	:
	;
	
exp_op[boolean defer]
	:	relationalOperators
	;		


/*------------------------------------------------------------------
 * SPECIFICATION OF GRAMMARS
 * i.e. PRODUCTION RULES
 *------------------------------------------------------------------*/
grammarDeclaration[boolean defer]
	:
		GRAMMAR n=ID LEFTP list_of_production_rules[defer] RIGHTP
	;

list_of_production_rules[boolean defer]
	:	production_rule[defer] SEMIC (list_of_production_rules[defer])?	
	;	

production_rule[boolean defer]	
	:	lhs=ID {
if(!defer) {
    // ID denotes a non-terminal of the grammar
}	
	}	ARROW right_hand_side[defer] 
	;

right_hand_side[boolean defer]
	:	i=ID {
if(!defer) {
    // ID must be either a terminal (i.e. a part)
    // or a non-terminal defined within the grammar
}	
	}	(COMMA right_hand_side[defer])?
	|	interaction[defer, "some_string"]
	;	
		
/*------------------------------------------------------------------
 * FACTS ON RELATIONS
 * Example: IPTC induces (lacI represses pLac)
 *------------------------------------------------------------------*/
interactionDeclaration[boolean defer]
	returns [Interaction ia]
	:	i1=interaction[defer, null] {
if(!defer) {
    $ia = $i1.ia;
}
}
	|	INTERACTION name=ID LEFTP i2=interaction[defer, $name.text] RIGHTP {
if(!defer) {
    $ia = $i2.ia;
}
}
	;
	
interaction[boolean defer, String name] 
	returns [Interaction ia]
	:	lhs1=ID t1=interactionType[defer] rhs1=ID {
if(!defer) {
    try {
        $ia = this.interp.createInteraction(name, $lhs1.text, $t1.type, $rhs1.text);
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}	
	}
	|	lhs2=ID t2=interactionType[defer] LEFTP rhs2=interaction[defer, name] RIGHTP {
if(!defer) {
    try {
        $ia = this.interp.createInteraction(name, $lhs2.text, $t2.type, $rhs2.ia);
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}	
	}
	;

interactionType[boolean defer] 
	returns [Interaction.InteractionType type]
	:	(UC_REPRESSES | LC_REPRESSES) {
if(!defer) {
    $type = Interaction.InteractionType.REPRESSES;
}	
	}
	|	(UC_INDUCES | LC_INDUCES) {
if(!defer) {
    $type = Interaction.InteractionType.INDUCES;
}	
	}
	;	



/*------------------------------------------------------------------
 * FUNCTION CALLS
 * are either:
 * - calls of predefined functions (like product, permute)
 * - calls of user-defined functions
 *------------------------------------------------------------------*/

functionCall[boolean defer]
	returns [NamedElement e]
	:	(pr=PRODUCT|pe=PERMUTE) LEFTP idToken=ID RIGHTP {
if(!defer) {
    try {
    
        if(!this.interp.contains($idToken.text)) {
            printError($idToken.text+" does not exists.");
        } 
        NamedElement ne = this.interp.get($idToken.text);
        if(!(ne instanceof Device)) {
            printError($idToken.text+" is not a Device.");
        }
        
        if(pr != null) {
            $e = this.interp.product((Device)ne);
        } else {
            $e = this.interp.permute((Device)ne);
        }
    } catch(Exception e) {
        printError(e.getMessage());
    }
}	
	}
	;
	
/*------------------------------------------------------------------
 * PRINT STATEMENTS
 *------------------------------------------------------------------*/
printStatement[boolean defer] 
	:	PRINTLN LEFTP tp=toPrint[defer] RIGHTP {
if(!defer) {
    if(null != $tp.sb) {
        try {
            this.writer.write($tp.sb.toString());
            this.writer.newLine();
            this.writer.flush();
        } catch(IOException ioe) {
            printError(ioe.getMessage());
        }
    }
}	
	}
	|	PRINT LEFTP tp=toPrint[defer] RIGHTP {
if(!defer) {
    if(null != $tp.sb) {
        try {
            this.writer.write($tp.sb.toString());
            this.writer.flush();
        } catch(IOException ioe) {
            printError(ioe.getMessage());
        }
    }
}	
	}
	;

toPrint[boolean defer]
	returns [StringBuilder sb]
	:	exp=expr[defer] tpp=toPrint_prime[defer] {
if(!defer) {
    $sb = new StringBuilder();
    if(null != $exp.element) {
        $sb.append($exp.element);
    } else {
        $sb.append($exp.p.getValue());
    }
    $sb.append($tpp.sb);
}	
	}
	;
	
toPrint_prime[boolean defer]
	returns [StringBuilder sb]
	:	{
if(!defer) {
    $sb = new StringBuilder();
}
	}
	|	COMMA tp=toPrint[defer] {
if(!defer) {
    $sb = new StringBuilder();
    $sb.append($tp.sb);
}	
	}
	;	


/*------------------------------------------------------------------
 *  IMPERATIVE LANGUAGE STATEMENTS
 *------------------------------------------------------------------*/ 
imperativeStatements[boolean defer]
	:	ifStatement[defer]
	|	forall_iterator[defer]
	|	for_loop[defer]
	;	

ifStatement[boolean defer]
@init {
boolean bExecuted = false;
}
	:	
		/*
		 *    ONE IF-BRANCH
		 */
		(UC_IF|LC_IF) LEFTP co=imp_condition[defer] RIGHTP LEFTCUR 
			stmts=listOfStatements[true] RIGHTCUR {
if(!defer) {
    // evaluate the condition
    if($co.bTrue) {
        // if true => execute the statements
        // and ignore the rest of the ifStatement
        
        try {
            this.exec("listOfStatements", $stmts.start.getTokenIndex());
        } catch(EugeneReturnException ere) {
            // TODO!
        } catch(EugeneException ee) {
            printError(ee.toString());
        }
        bExecuted = true;
    }
}			
		}
		
		/*
		 *    ZERO-OR-MORE ELSEIF BRANCHES
		 */
		( (UC_ELSEIF|LC_ELSEIF) LEFTP co=imp_condition[defer] RIGHTP LEFTCUR 
			stmts=listOfStatements[true] RIGHTCUR {
if(!defer && !bExecuted) {
    // evaluate the condition
    if($co.bTrue) {
        // if true => execute the statements
        // and ignore the rest of the ifStatement
        try {        
            this.exec("listOfStatements", $stmts.start.getTokenIndex());
        } catch(EugeneReturnException ere) {
            // TODO!
        } catch(EugeneException ee) {
            printError(ee.toString());
        }
        
        bExecuted = true;
    }
}			
		})*
		
		/*
		 *    ONE OPTIONAL ELSE BRANCH
		 */
		((UC_ELSE|LC_ELSE) LEFTCUR 
			stmts=listOfStatements[true] RIGHTCUR {
if(!defer && !bExecuted) {
    try {
        this.exec("listOfStatements", $stmts.start.getTokenIndex());        
    } catch(EugeneReturnException ere) {
        // TODO!
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
    
    bExecuted = true;
}						
	})?
	;

imp_condition[boolean defer] 
	returns [boolean bTrue]
	:	lhs=atom[defer] ro=relationalOperators rhs=atom[defer] {
if(!defer) {
    try {
        $bTrue = this.interp.evaluateCondition(
                         $lhs.p, 
                         $ro.text, 
                         $rhs.p);
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}	
	}
	;	

forall_iterator[boolean defer]
	:	(UC_FORALL|LC_FORALL) (it=ID COLON)? i=ID LEFTCUR
			listOfStatements[defer] {
if(!defer) {

}			
	}
		RIGHTCUR
	;

for_loop[boolean defer]
	:	(UC_FOR|LC_FOR) LEFTP ds=variableDeclaration[true] SEMIC co=imp_condition[true] SEMIC as=assignment[true] RIGHTP LEFTCUR 
			stmts=listOfStatements[true] {
if(!defer) {
    try {
        this.for_loop(
            $ds.start,      // init
            $co.start,      // condition
            $as.start,      // increment
            $stmts.start);  // loop-body
    } catch(EugeneReturnException ere) {
        // TODO!
    } catch(Exception ee) {
        printError(ee.toString());
    }
}			
		}
		RIGHTCUR
	;		


listOfStatements[boolean defer] 
	:	(stmtToken=statement[defer])+ 
	;


/*------------------------------------------------------------------
 * EXPRESSIONS
 *------------------------------------------------------------------*/

expr[boolean defer] 
	returns [Variable p,  String instance, int index, String listAddress, Variable primVariable, NamedElement element]
	:	e=multExpr[defer] {
if(!defer) {
    $p = copyVariable($e.p);
    $instance = $e.instance;
    $index = $e.index;
    if ($e.listAddress != null) {
        $listAddress = $e.listAddress;
    }
    $primVariable = $e.primVariable;

    $element = $e.element;
}
	} 	(pl=PLUS e=multExpr[defer] {
if(!defer) {
    try {
        this.interp.doMinPlusOp($e.p, $p, $pl.text);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
    $element = null;
}
	} 	| mi=MINUS e=multExpr[defer] {
if(!defer) {
    try {
        this.interp.doMinPlusOp($e.p, $p, $mi.text);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
    $element = null;
}
	} )*
	;

multExpr[boolean defer] 
	returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element]
	:	e=atom[defer] {
if(!defer) {
    $p = copyVariable($e.p);
    if ($e.instance != null) {
        $instance = $e.instance;
    }
    $index = $e.index;
    if ($e.listAddress != null) {
        $listAddress = $e.listAddress;
    }
    $primVariable = $e.primVariable;
			
    $element = $e.element;
}	
	} 	( (mul=MULT|div=DIV) e=atom[defer] {
if(!defer) {
    try {
        if ($mul.text != null) {
            this.interp.doMultDivOp($e.p, $p, $mul.text);
        } else {
            this.interp.doMultDivOp($e.p, $p, $div.text);
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
    $element = null;
}
	} )*
	;

atom [boolean defer]
	returns [Variable p = new Variable(), String instance, int index = -1, String listAddress, Variable primVariable, NamedElement element]
	:	(n=NUMBER | n=REAL)
		{
		if(!defer) {
			$p.num = Double.parseDouble($n.text);
			$p.type = EugeneConstants.NUM;
		}
		}
	|	MINUS (n=NUMBER | n=REAL)
		{
		if(!defer) {
			$p.num = Double.parseDouble($n.text) * -1.0;
			$p.type = EugeneConstants.NUM;
		}
		}
	|	(t=TRUE | f=FALSE)
		{
		if(!defer) {
			$p.type = EugeneConstants.BOOLEAN;
			if ($t != null) {
				$p.bool = true;
			} else {
				$p.bool = false;
			}
		}
		}
	|	ID
		{
	if(!defer) {
		try {
			$element = this.interp.get($ID.text);
					
			if(null != $element) {
				if($element instanceof Variable) {
					$p = copyVariable((Variable)$element);
					$primVariable = (Variable)$element;
				}
			} else {
				throw new EugeneException($ID.text + " is not declared.");
			}
		} catch(EugeneException ee) {
			printError(ee.toString());
		}
	}
		}	oc=object_access[defer, $element] {
if(!defer) {
    $element = $oc.child;
    if($element instanceof Variable) {
        $p = (Variable)$element;
    }
}		
		} 
	|	STRING
		{
		if(!defer) {
			$p.type = EugeneConstants.TXT;
			$p.txt = $STRING.text.substring(1, $STRING.text.length()-1);
		}
		}
	|	'(' expr[defer] ')'
		{
		if(!defer) {
			$p = $expr.p;
			$primVariable = $expr.primVariable;
		}
		}
		|	LEFTSBR list[defer] RIGHTSBR
		{
		if(!defer) {
			$p = $list.listPrim;
			$primVariable = $list.listPrim;
		}
		}

	;
/**** 
 BACKUP	:	
	|	ID LEFTSBR (n=NUMBER|r=REAL) RIGHTSBR
		{
		try {
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if ($r != null) {
						printError("Invalid index.");
					} else {
						if (null != this.interp.getVariable($ID.text)) {
							$listAddress = $ID.text;
							Variable prim = this.interp.getVariable($ID.text);
							if (prim.type.equals(EugeneConstants.NUMLIST)) {
								int s = Integer.parseInt($n.text);
								if (s > prim.numList.size()) {
									printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
								} else {
									$p.num = prim.numList.get(s);
									$p.type = EugeneConstants.NUM;
									$index = s;
									$primVariable = prim;
								}
							} else if (prim.type.equals(EugeneConstants.TXTLIST)) {
								int s = Integer.parseInt($n.text);
								if (s > prim.txtList.size()) {
									printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
								} else {
									$p.txt = prim.txtList.get(s);
									$p.type = EugeneConstants.TXT;
									$index = s;
									$primVariable = prim;
								}
							}
						} else {
							printError("List " + $ID.text + " does not exist or is not a Variable list.");
						}
					}
				}
			} else {
				if ($r != null) {
					printError("Invalid index.");
				} else {
					if (null != this.interp.getVariable($ID.text)) {
						$listAddress = $ID.text;
						Variable prim = this.interp.getVariable($ID.text);
						if (prim.type.equals(EugeneConstants.NUMLIST)) {
							int s = Integer.parseInt($n.text);
							if (s > prim.numList.size()) {
								printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
							} else {
								$p.num = prim.numList.get(s);
								$p.type = EugeneConstants.NUM;
								$index = s;
								$primVariable = prim;
							}
						} else if (prim.type.equals(EugeneConstants.TXTLIST)) {
							int s = Integer.parseInt($n.text);
							if (s > prim.txtList.size()) {
								printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
							} else {
								$p.txt = prim.txtList.get(s);
								$p.type = EugeneConstants.TXT;
								$index = s;
								$primVariable = prim;
							}
						}
					} else {
						printError("List " + $ID.text + " does not exist or is not a Variable list.");
					}
				}
			} //end else
		} catch(EugeneException ee) {
			printError(ee.toString());
		} 
		}
 ****/
list [boolean defer]
	returns [Variable listPrim]
	:	str1=expr[defer] {
if(!defer){
    $listPrim = new Variable();
    $listPrim.type = typeList + "List";
    addToListPrim($str1.p, $listPrim);
}
	} 	(COMMA str2=expr[defer] {
if(!defer) {
    addToListPrim($str2.p, $listPrim);
}				
	} 	)*
	;
  
/*------------------------------------------------------------------
 * DATA ACCESS
 * E.g. D1.D2.D3[0].p1.prop1
 *------------------------------------------------------------------*/
/***
data_access[boolean defer]
	returns [NamedElement e]
	:	id=ID {
if(!defer) {
    $e = this.interp.get($id.text);
    if(null == $e) {
        printError($e + " does not exist.");
    }
}	
	}	rhs=object_access[defer, $e] {
if(!defer) {
    $e = $rhs.child;
}		
	}
	;
 ***/
 
object_access[boolean defer, NamedElement parent]
	returns [NamedElement child]
	:	// empty-word 
	{
if(!defer) {
    $child = parent;
}	
	}
		|(DOT id=ID {
if(!defer) {
    try {
        $child = parent.getElement($id.text);
        if(null == $child) {
            throw new EugeneException(parent.getName() + " does not contain " + $id.text);
        }
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}	
	} 
	|	LEFTSBR (exp=expr[defer]) RIGHTSBR {
if(!defer) {
    try {
        if(null != $exp.p && EugeneConstants.NUM.equals($exp.p.getType())) {
            $child = parent.getElement((int)($exp.p.getNum()));
            if(null == $child) {
                throw new EugeneException(parent.getName() + " does not contain " + $id.text);
            }
        } else {
            throw new EugeneException("Invalid index " + $exp.p + "!");
        }
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}	
	})	o=object_access[defer, $child] {
if(!defer) {
    $child = $o.child;
}	
	}
	;	

/*------------------------------------------------------------------
 * DATA EXCHANGE STATEMENTS
 *------------------------------------------------------------------*/
dataExchange[boolean defer] 
	returns [NamedElement e]
	:	s=sbolStatement[defer] {
if(!defer) {
    $e = $s.e;
}	
	}
	|	p=pigeonStatement[defer]
	|	i=importStatement[defer] {
if(!defer) {
    $e = $i.e;
}	
	}
// TODO:
//	|	g=genbankStatement[defer]
//	|	r=registryStatement[defer]
	;
	
/*** include STATEMENT ***/
includeStatement[boolean defer]
	:	(HASHMARK)? (LC_INCLUDE|UC_INCLUDE) file=STRING {
if(!defer) {
    try {
        this.interp.includeFile($file.text);
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}
	}
	;

/*** include STATEMENT ***/
importStatement[boolean defer]
	returns [NamedElement e]
	:	(LC_IMPORT|UC_IMPORT) LEFTP file=STRING {
if(!defer) {
    try {
        $e = this.interp.importFile($file.text);
    } catch(EugeneException ee) {
        printError(ee.toString());
    }
}
	}	RIGHTP
	;
		
/*** SBOL STATEMENTS ***/
sbolStatement[boolean defer] 
	returns [NamedElement e]
	:	SBOL DOT (sbolExportStatement[defer] | i=sbolImportStatement[defer] {
if(!defer) {
    $e = $i.e;
}	
	})
	;

sbolExportStatement[boolean defer] 
	:	EXPORT LEFTP idToken=ID COMMA filenameToken=STRING RIGHTP {
if(!defer) {
    try {
        interp.exportToSBOL(
            $idToken.text, 
            $filenameToken.text.substring(1, $filenameToken.text.length()-1));
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	}	
	;
	
sbolImportStatement[boolean defer]
	returns [NamedElement e]  
	:	(LC_IMPORT|UC_IMPORT) LEFTP fileToken=STRING RIGHTP {
if(!defer) {
    try {
        $e = interp.importSBOL($fileToken.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	}
	;	

/*---------------------
 * GenBank
 *--------------------*/	
/************** 
genbankStatement[boolean defer] returns [NamedElement objElement]
	:	GENBANK DOT (importToken=genbankImportStatement[defer] | genbankExportStatement[defer]) {
if(!defer && importToken!=null) {
    $objElement = $importToken.objElement;
}	
	}
	;
		
genbankExportStatement[boolean defer] returns [NamedElement objElement] 
	:	EXPORT LEFTP RIGHTP
	;
	
genbankImportStatement[boolean defer] 
        returns [NamedElement objElement]
	:	(LC_IMPORT|UC_IMPORT) LEFTP RIGHTP {
if(!defer) {

}
	}
	|	(LC_IMPORT|UC_IMPORT) LEFTP typeToken=ID COMMA partToken=STRING RIGHTP {
if(!defer) {

}	
	} 
	;	
****************/

/*----------------------
 * Pigeon / SBOL Visual
 *----------------------*/
pigeonStatement[boolean defer]
	:	(PIGEON|LC_PIGEON) LEFTP idToken=ID RIGHTP {
if(!defer) {
    try {
        this.interp.pigeon($idToken.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}		
	}
	;	

/*---------------------
 * iGEM partsregistry
 *---------------------*/
/***** 
registryStatement[boolean defer]
	:	REGISTRY DOT (LC_IMPORT|UC_IMPORT) LEFTP nameToken=STRING RIGHTP {
if(!defer) {

    String name = $nameToken.text;
    if(null != name) {
    
        // remove the double quotas
        name = name.substring(1, name.length()-2);
        
        if(null == this.registryImporter) {
            this.registryImporter = new SBOLRegistryImporter();
        }
        
        try {
            List<Component> lst = this.registryImporter.importComponent($nameToken.text);
            if(null!=lst && !lst.isEmpty()) {
                for(Component component : lst) {
                    this.interp.put(component);
                }
            } else {
                throw new EugeneException("Cannot import "+name+"!");
            }
        } catch(EugeneException ee) {
            printError(ee.getMessage());
        }
    }
}		
	}	
	;
****/


testStatements[boolean defer]
	: 	|	ASSERT LEFTP id=ID DOT SIZE LEFTP RIGHTP EQUALS EQUALS n=NUMBER RIGHTP {
if(!defer) {
    try {
        NamedElement el = this.interp.get($id.text);
        if(null != el) {
//            if(el instanceof EugeneCollection) {
//                if(((EugeneCollection)el).getElements().size() != Integer.parseInt($n.text)) {
//                    printError("TEST NOT PASSED!");
//                }
//            }
    }
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	}
	;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
NUMBER
	:	(DIGIT)+
	;

REAL
	:	NUMBER '.' NUMBER
	;

WS
	:	( '\t' | ' ' | '\u000C' )+ {$channel = HIDDEN;}
	;

NEWLINE
	:	'\r' ? '\n' {$channel = HIDDEN;}
	;

LINE_COMMENT
	:	'//' ~('\n'|'\r')*  ('\r\n' | '\r' | '\n')
		{
			skip();
		}
	|	'//' ~('\n'|'\r')* // a line comment could appear at the end of the file without CR/LF
		{
			skip();
		}
	;

ML_COMMENT
	:	'/*' (options {greedy=false;} : .)* '*/' {$channel=HIDDEN;}
	;

fragment DIGIT
	:	'0'..'9'
	;

ID
	:	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

STRING
	:	'"' (options {greedy=false;} : .)* '"'
	;
