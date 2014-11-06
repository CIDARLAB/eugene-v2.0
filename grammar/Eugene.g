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
	DOLLAR = '$';
	EQUALS = '=';
	UNDERS = '_';
	SEMIC = ';';
	COLON = ':';
	COMMA = ',';
	DOT = '.';
	DOTDOT = '..';
	PIPE = '|';
	NUM = 'num';
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
	OP_NOT = '!';
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
	TRUE_LC = 'true';
	TRUE_UC = 'TRUE';
	FALSE_LC = 'false';
	FALSE_UC = 'FALSE';
	PERMUTE = 'permute';
	PRODUCT = 'product';
	STRICT = 'strict';
	FLEXIBLE = 'flexible';
	COLLECTION = 'Collection';
	ARRAY = 'Array';
	SBOL = 'SBOL';
	GENBANK = 'Genbank';
	REGISTRY = 'Registry';

	INTERACTION = 'Interaction';
	UC_REPRESSES = 'REPRESSES';
	LC_REPRESSES = 'represses';
	UC_INDUCES = 'INDUCES';
	LC_INDUCES = 'induces';
	HASHMARK = '#';	

	LC_FORALL = 'forall';
	UC_FORALL = 'FORALL';
	LC_FOR = 'for';
	UC_FOR = 'FOR';
	LC_WHILE = 'while';
	UC_WHILE = 'WHILE';

	ARROW = '-->';
	GRAMMAR = 'Grammar';
	
	RETURN_UC = 'RETURN';
	RETURN_LC = 'return';
	
	/*  
	 * RESERVED WORDS FOR BUILT-IN FUNCTIONS
	 */
	
	INCLUDE_LC = 'include';
	INCLUDE_UC = 'INCLUDE';
	
	IMPORT_LC = 'import';
	IMPORT_UC = 'IMPORT';

	EXPORT_LC = 'export';
	EXPORT_UC = 'EXPORT';

	PRINT_LC = 'print';
	PRINT_UC = 'PRINT';
	PRINTLN_LC = 'println';
	PRINTLN_UC = 'PRINTLN';

	SIZEOF_LC = 'sizeof';
	SIZEOF_UC = 'SIZEOF';
	SIZE_LC = 'size';
	SIZE_UC = 'SIZE';
	
	RANDOM_LC = 'random';
	RANDOM_UC = 'RANDOM';

	SAVE_LC = 'save';
	SAVE_UC = 'SAVE';
	STORE_LC = 'store';
	STORE_UC = 'STORE';
	
	VISUALIZE_LC = 'visualize';
	VISUALIZE_UC = 'VISUALIZE';

	EXIT_UC = 'EXIT';
	EXIT_LC = 'exit';

/*   -----
 *   Functions to interact with LMS
 *   ----- */
	CREATE_UC = 'CREATE';
	CREATE_LC = 'create';
	
	READ_UC = 'READ';
	READ_LC = 'read';
	
	UPDATE_UC = 'UPDATE';
	UPDATE_LC = 'update';
	
	DELETE_UC = 'DELETE';
	DELETE_LC = 'delete';
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

}

/*
 TODO-List:
 - exception handling
 - logging
 - dynamic naming
 - functions -> return exception
 */
 
/* start of program consisting of statements */
prog[boolean defer]
	throws EugeneReturnException
@before {
if(null == this.interp) {
    this.interp = new Interp(new Sparrow());
}
}
	:	(statement[defer] | function_definition[true])* EOF
	;


statement[boolean defer] 
	returns [NamedElement objReturnValue]
	throws EugeneReturnException
	:	
		includeStatement[defer] (SEMIC)?
	|	declarationStatement[defer] SEMIC	
	|	printStatement[defer] SEMIC
	|	assignment[defer] SEMIC
	|	de=dataExchange[defer] SEMIC {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        // iff there's no assignment to a LHS element,
        // then we store the imported data into the 
        // current scope's symbol tables
        if(null != $de.e) {
            this.interp.recursiveStoringOf($de.e);
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	|	imperativeStatements[defer]
	|	function_call[defer] SEMIC
	|	bif=built_in_function[defer] SEMIC // product/1, random/2, size/1, ...
	{  
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        // iff there's no assignment to a LHS element,
        // then we store the imported data into the 
        // current scope's symbol tables
        if(null != $bif.element) {
            this.interp.recursiveStoringOf($de.e);
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	|	stand_alone_function[defer] SEMIC  // save/1, exit/0
	|	return_statement[defer] SEMIC
	;
/***
predefined_statements[boolean defer] 
	:	testStatements[defer]
	|	built_in_function[defer]
	;
 ***/

declarationStatement[boolean defer]
	returns [String name]
	:	v=variableDeclaration[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $varname = $n.varname;
}	
	}
	|	TXT t=txtdecl[defer]  /* declaration of text instances*/ {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $varname = $t.varname;
}	
	}
	|	TXT LEFTSBR RIGHTSBR tl=txtlistdecl[defer]  /*  declaration of text lists */ {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $varname = $tl.varname;
}	
	}
	|	NUM LEFTSBR RIGHTSBR nl=numlistdecl[defer]  /* declaration of num lists */ {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $varname = $nl.varname;
}	
	}
	|	(BOOLEAN|BOOL) b=booldecl[defer]  /* declaration of boolean instances*/ {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $varname = $b.varname;
}	
	}
	;

numdecl[boolean defer]
	returns [String varname]
	:	ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    declareVariableNoValue($ID.text, EugeneConstants.NUM);
    $varname = $ID.text;
}
	} 	(COMMA numdecl[defer])?
	|	ID EQUALS (ex=expr[defer]) {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    declareVariableWithValueNum($ID.text, $ex.p, $ex.index);
    $varname = $ID.text;
}
	}  (COMMA numdecl[defer])?
	;

txtdecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableNoValue($ID.text, EugeneConstants.TXT);
			$varname = $ID.text;
		}
		} (COMMA txtdecl[defer])?

	|	var=ID EQUALS let=expr[defer]
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableWithValueTxt($var.text, $let.p, $let.index);
			$varname = $ID.text;
		}
		} (COMMA txtdecl[defer])?
	;

txtlistdecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableNoValue($ID.text, EugeneConstants.TXTLIST);
			$varname = $ID.text;
		}
		} (COMMA txtlistdecl[defer])?
	|	var=ID EQUALS {typeList = EugeneConstants.TXT;} let=expr[defer]
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableWithValueTxtList($var.text, $let.p);
			$varname = $ID.text;
		}
		} (COMMA txtlistdecl[defer])?
	;

numlistdecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableNoValue($ID.text, EugeneConstants.NUMLIST);
			$varname = $ID.text;
		}
		} (COMMA numlistdecl[defer])?
	|	var=ID EQUALS { typeList = EugeneConstants.NUM;}let=expr[defer]
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableWithValueNumList($var.text, $let.p);
			$varname = $ID.text;
		}
		} (COMMA numlistdecl[defer])?
	;

booldecl[boolean defer]
	returns [String varname]
	:	ID
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableNoValue($ID.text, EugeneConstants.BOOLEAN);
			$varname = $ID.text;
		}
		} (COMMA booldecl[defer])?
	|	var=ID EQUALS let=expr[defer]
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			declareVariableWithValueBool($var.text, $let.p);
			$varname = $ID.text;
		}
		}
	;
	
propertyDeclaration[boolean defer]
	:	PROPERTY nameToken=ID LEFTP typeToken=propertyType RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
	|	(BOOLEAN|BOOL) {
$type = EugeneConstants.BOOLEAN;	
	}
	;

/*------------------------------------------------------------------
 * TYPES (i.e. Part Types and Device Templates)
 *------------------------------------------------------------------*/ 

typeDeclaration[boolean defer]
	:	partTypeDeclaration[defer]
	|	(TYPE) nameToken=ID (LEFTP (lstToken=listOfIDs[defer])? RIGHTP)? {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
 *  EUGENE CONTAINERS (i.e. COLLECTIONS and ARRAYS)
 *------------------------------------------------------------------*/ 
containerDeclaration[boolean defer]
	returns [NamedElement ne]
	:	(c=COLLECTION | (a=ARRAY (LEFTSBR RIGHTSBR)?)) name=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
	;

list_of_declarations[boolean defer]	
        returns [List<NamedElement> elements]
	:	( ds=declarationStatement[defer] | exp=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(null != $exp.element) {
            this.interp.addToContainer($exp.element);
        } else if(null != $exp.p) {
            this.interp.addToContainer($exp.p);
        } else {
            throw new EugeneException("Cannot add " + $exp.text + " to a Eugene container!");
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
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
String instance_name = null;
}
	:	t=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
	}	n=dynamic_naming[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    instance_name = $n.name;	
}
	}	( LEFTP (dotToken=listOfDotValues[defer]|valueToken=listOfValues[defer, (ComponentType)type])? RIGHTP )? {
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
	;

// e.g. 
// PartType Promoter(Sequence, Orientation);
// Promoter pLac(.Sequence(".."),.Orientation(".."));
// this rule only applies for PartType instances!
listOfDotValues [boolean defer] 
	:	DOT prop=ID
		{
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {		
    try {
        addToPropertyListHolder($prop.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}			
		} LEFTP v1=expr[defer]
			{
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
    try {
        addToPropertyValuesHolder($prop.text, $v1.p, $v1.index);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}				
			} RIGHTP (COMMA DOT p=ID
				{
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
    try {
        addToPropertyListHolder($p.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }				
}				
				} LEFTP v2=expr[defer]
					{
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {			
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
		} val1=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    propertyValuesHolder.add($val1.p);
}				
			} (COMMA {
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
               } val2=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    propertyValuesHolder.add($val2.p);
}				
		} )*
	;
	
deviceDeclaration[boolean defer]
	:	DEVICE n=ID (LEFTP (dcs=deviceComponents[defer])? RIGHTP)?  {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $lstComponents.add($s.components);
    $lstOrientations.add($s.orientations);
}	
	}	(',' dcs=deviceComponents[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $lstComponents.addAll($dcs.lstComponents);
    $lstOrientations.addAll($dcs.lstOrientations);
}	
	}	)?
	;

selection[boolean defer] 
	returns [List<NamedElement> components, List<Orientation> orientations]
	:	LEFTSBR sl=selection_list[defer] RIGHTSBR {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $components = $sl.components;
    $orientations = $sl.orientations;
}	
	}
	|	dc=device_component[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $components.add($dc.component);
    $orientations.add($dc.orientation);
}	
	}	(PIPE sl=selection_list[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $components.addAll($sl.components);
    $orientations.addAll($sl.orientations);
}	
	}	)?
	;

device_component[boolean defer]
	returns [NamedElement component, Orientation orientation]
	:	(directionToken=(MINUS|PLUS))? idToken=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
	:	lhs=lhs_assignment[defer] EQUALS (a=AMP)? rhs=rhs_assignment[defer] {
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
           this.interp.assignment($lhs.tree, $rhs.e);
         */
        this.interp.assignment(
                        $lhs.text, 
                        $rhs.e);
        
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());    
    }
}	
	}
	;

lhs_assignment[boolean defer]
	:	ID lhs_access[defer]
	;	
	
lhs_access[boolean defer]
	:	
	|	(DOT i=ID | LEFTSBR (ID|NUMBER) RIGHTSBR) lhs_access[defer]
	;	
	
/*** BACKUP:
fc=function_call[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $fc.e;
}	
	}	|	
**/	

rhs_assignment[boolean defer]	
	returns [NamedElement e]
	:	de=dataExchange[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $de.e;
}	
	}
	|	exp=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING)
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING){
    $lstElements.addAll($lstToken.lstElements);
}
        }	)?
	;
	
ruleDeclaration[boolean defer]
	returns [Rule rule]
	:	RULE name=ID LEFTP ( ((LC_ON|UC_ON) device=ID COLON)? {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if(null == $lAnd) {
        $lAnd = new LogicalAnd();
    }
    
    $lAnd.getPredicates().add($c.p);
}	
	}) ( (LC_AND|UC_AND|LOG_AND) cnf=cnf_rule[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $p = $n1.p;
}	
	}	((LC_OR|UC_OR|LOG_OR) n2=negated_predicate[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if(null != lor) {
        $p = lor;
    }
}	
	}
	;
	
negated_predicate[boolean defer]
	returns [Predicate p]
	:	((UC_NOT|LC_NOT|OP_NOT) c=predicate[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $p = this.interp.negate($c.p);
    } catch(Exception e) {
        printError(e.getMessage());
    }
}
	}
	|	c=predicate[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(!this.interp.contains($i.text)) {
            printError($i.text+" not defined.");
        }
        element = this.interp.get($i.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}
	}
	|	n=NUMBER {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    constant = Integer.parseInt($n.text);
}	
	}
	|	'[' n=NUMBER ']' {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    index = Integer.parseInt($n.text);
}	
	}) {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $p = new ExpressionConstraint($lhs.exp, $op.text, $rhs.exp);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}
	}
	;
		
expression[boolean defer]
	returns [Expression exp]
	:	lhs=exp_operand[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $exp = new Expression($lhs.eop, null, null);
}
	}	( expop=exp_operator[defer] rhs=expression[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $exp = new Expression($lhs.eop, $expop.op, $rhs.exp);
}	
	}	)? 
	|	LEFTP expression[defer] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    	
}	
	}
	;

exp_operator[boolean defer]
	returns [Expression.ExpOp op]
	:	PLUS {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
    $op = Expression.ExpOp.PLUS;	
}
	} 
	|	MINUS {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
    $op = Expression.ExpOp.MINUS;	
}
	}
	|	MULT {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
    $op = Expression.ExpOp.MULT;	
}
	}
	|	DIV {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {	
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($n.text);
    $eop = new ExpressionOperand(v);
}	
	}
	|	MINUS n=NUMBER {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($n.text) * -1;
    $eop = new ExpressionOperand(v);
}	
	}
	|	r=REAL {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($r.text);
    $eop = new ExpressionOperand(v);
}	
	}
	|	MINUS r=REAL {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    Variable v = new Variable(null, EugeneConstants.NUM);
    v.num = Double.parseDouble($r.text) * -1.0;
    $eop = new ExpressionOperand(v);
}	
	}
	|	s=STRING {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    // ID denotes a non-terminal of the grammar
}	
	}	ARROW right_hand_side[defer] 
	;

right_hand_side[boolean defer]
	:	i=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $ia = $i1.ia;
}
}
	|	INTERACTION name=ID LEFTP i2=interaction[defer, $name.text] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $ia = $i2.ia;
}
}
	;
	
interaction[boolean defer, String name] 
	returns [Interaction ia]
	:	lhs1=ID t1=interactionType[defer] rhs1=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $ia = this.interp.createInteraction(name, $lhs1.text, $t1.type, $rhs1.text);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	|	lhs2=ID t2=interactionType[defer] LEFTP rhs2=interaction[defer, name] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $ia = this.interp.createInteraction(name, $lhs2.text, $t2.type, $rhs2.ia);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	;

interactionType[boolean defer] 
	returns [Interaction.InteractionType type]
	:	(UC_REPRESSES | LC_REPRESSES) {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $type = Interaction.InteractionType.REPRESSES;
}	
	}
	|	(UC_INDUCES | LC_INDUCES) {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $type = Interaction.InteractionType.INDUCES;
}	
	}
	;	



/*------------------------------------------------------------------
 * PRINT STATEMENTS
 *------------------------------------------------------------------*/
printStatement[boolean defer] 
	:	(PRINTLN_LC|PRINTLN_UC) LEFTP tp=toPrint[defer] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
	|	(PRINT_LC|PRINT_UC) LEFTP tp=toPrint[defer] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $sb = new StringBuilder();
    if(null != $exp.element) {
        $sb.append($exp.element);
    } else {
        $sb.append($exp.p);
    }
    $sb.append($tpp.sb);
}	
	}
	;
	
toPrint_prime[boolean defer]
	returns [StringBuilder sb]
	:	{
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $sb = new StringBuilder();
}
	}
	|	COMMA tp=toPrint[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $sb = new StringBuilder();
    $sb.append($tp.sb);
}	
	}
	;	


/*------------------------------------------------------------------
 *  IMPERATIVE LANGUAGE STATEMENTS
 *------------------------------------------------------------------*/ 
imperativeStatements[boolean defer]
	throws EugeneReturnException
	:	if_elseif_else[defer]
	|	forall_iterator[defer]
	|	for_loop[defer]
	|	while_loop[defer]
	;	

if_elseif_else[boolean defer]
	throws EugeneReturnException

@init {
boolean bExecuted = false;
}
	:	
		/*
		 *    ONE IF-BRANCH
		 */
		(UC_IF|LC_IF) LEFTP co=logical_condition[defer] RIGHTP LEFTCUR 
			stmts=list_of_statements[true] RIGHTCUR {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    // evaluate the condition
    if($co.b) {
        // if true => execute the statements
        // and ignore the rest of the ifStatement
        
        try {
            this.exec_branch($stmts.start.getTokenIndex());
        } catch(EugeneReturnException ere) {
            throw new EugeneReturnException(ere.getReturnValue());
        } catch(EugeneException ee) {
            printError(ee.getLocalizedMessage());
        }
        bExecuted = true;
    }
}			
		}
		
		/*
		 *    ZERO-OR-MORE ELSEIF BRANCHES
		 */
		( (UC_ELSEIF|LC_ELSEIF) LEFTP co=logical_condition[defer] RIGHTP LEFTCUR 
			stmts=list_of_statements[true] RIGHTCUR {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
    // evaluate the condition
    if($co.b) {
        // if true => execute the statements
        // and ignore the rest of the ifStatement
        try {        
            this.exec_branch($stmts.start.getTokenIndex());
        } catch(EugeneReturnException ere) {
            throw new EugeneReturnException(ere.getReturnValue());
        } catch(EugeneException ee) {
            printError(ee.getLocalizedMessage());
        }
        
        bExecuted = true;
    }
}			
		})*
		
		/*
		 *    ONE OPTIONAL ELSE BRANCH
		 */
		((UC_ELSE|LC_ELSE) LEFTCUR 
			stmts=list_of_statements[true] RIGHTCUR {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && !bExecuted) {
    try {
        this.exec_branch($stmts.start.getTokenIndex());        
    } catch(EugeneReturnException ere) {
        throw new EugeneReturnException(ere.getReturnValue());
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
    
    bExecuted = true;
}						
	})?
	;


forall_iterator[boolean defer]
	throws EugeneReturnException
	:	(UC_FORALL|LC_FORALL) (it=ID COLON)? i=ID LEFTCUR
			los=list_of_statements[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        this.exec("list_of_statements", $los.start.getTokenIndex());
    } catch(EugeneReturnException ere) {
        throw new EugeneReturnException(ere.getReturnValue());
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}			
	}
		RIGHTCUR
	;

for_loop[boolean defer]
	throws EugeneReturnException
	:	(UC_FOR|LC_FOR) LEFTP ds=variableDeclaration[true] SEMIC co=logical_condition[true] SEMIC (as=assignment[true])? RIGHTP LEFTCUR 
			stmts=list_of_statements[true] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(null != as) {
            this.execute_loop(
                $ds.start,      // init
                $co.start,      // condition
                $as.start,      // increment
                $stmts.start);  // loop-body
        } else {
            this.execute_loop(
                $ds.start,      // init
                $co.start,      // condition
                null,           // increment
                $stmts.start);  // loop-body
        }
    } catch(EugeneReturnException ere) {
        throw new EugeneReturnException(ere.getReturnValue());
    } catch(Exception ee) {
        printError(ee.getLocalizedMessage());
    }
}			
		}
		RIGHTCUR
	;		

while_loop[boolean defer]
	throws EugeneReturnException
	:	(UC_WHILE|LC_WHILE) LEFTP co=logical_condition[true] RIGHTP LEFTCUR 
			stmts=list_of_statements[true] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        this.execute_loop(
            null,           // init
            $co.start,      // condition
            null,           // increment
            $stmts.start);  // loop-body
    } catch(EugeneReturnException ere) {
        throw new EugeneReturnException(ere.getReturnValue());
    } catch(Exception ee) {
        printError(ee.getLocalizedMessage());
    }
}			
	}
		RIGHTCUR
	;
	
	
/*------------------------------------------------------------------
 * LOGICAL CONDITIONS
 *------------------------------------------------------------------*/

logical_condition[boolean defer] 
	returns [boolean b]
	:	loc=logical_or_condition[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $b = $loc.b;
}	
	}
	;
		
logical_not_condition[boolean defer]
	returns [boolean b] 
	:	loc=logical_or_condition[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $b = $loc.b;
}	
	}
	;
		
logical_or_condition[boolean defer] 
	returns [boolean b]
	:	lac=logical_and_condition[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $b = $lac.b;
}	
	}	((LC_OR|UC_OR|LOG_OR|PIPE (PIPE)?) loc=logical_or_condition[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    System.out.println("-> b: " + $b + " || " + $loc.b + " -> " + ($b||$loc.b));
    $b = $b || $loc.b;
}		
	}	)*
	;

logical_and_condition[boolean defer]
	returns [boolean b]
	:	ac=atomic_condition[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $b = $ac.b;
}	
	}	((LC_AND|UC_AND|LOG_AND|AMP (AMP)?) lac=logical_and_condition[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    System.out.println("-> b: " + $b + " && " + $lac.b + " -> " + ($b||$lac.b));
    $b = $b && $lac.b;
}	
	}	)*
	;
		
atomic_condition[boolean defer] 
	returns [boolean b]
	:	lhs=expr[defer] ro=relationalOperators rhs=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(null != $lhs.element) {
            if(null != $rhs.element) {
                // comparing two NamedElements against each other
                // e.g. p.prop = q.prop
                $b = this.interp.evaluateCondition(
                             $lhs.element, 
                             $ro.text, 
                             $rhs.element);
            } else if(null != $rhs.p) {
                // comparing a LHS NamedElement against a Variable
                // that could be either a variable or constant
                // e.g. p.prop = i
                $b = this.interp.evaluateCondition(
                             $lhs.element, 
                             $ro.text, 
                             $rhs.p);
            }
        } else {
        
            if(null != $rhs.element) {
                // comparing a LHS variable against a RHS 
                // NamedElement
                // e.g. i == q.prop
                $b = this.interp.evaluateCondition(
                             $lhs.p, 
                             $ro.text, 
                             $rhs.element);
            } else if(null != $rhs.p) {
                // comparing a LHS Variable against a Variable
                // that could be either a variable or constant
                // e.g. i == j
                $b = this.interp.evaluateCondition(
                             $lhs.p, 
                             $ro.text, 
                             $rhs.p);
            } else {
                throw new EugeneException("Invalid condition!");
            }
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	|	(LC_NOT|UC_NOT|OP_NOT) LEFTP lac=atomic_condition[defer] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $b = !($lac.b);
}
	}
	;	
	
/*------------------------------------------------------------------
 * EXPRESSIONS
 *------------------------------------------------------------------*/

expr[boolean defer] 
	returns [Variable p,  String instance, int index, String listAddress, Variable primVariable, NamedElement element]
	:	e=multExpr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if(null != $e.p) {
        $p = copyVariable($e.p);
    
        $instance = $e.instance;
        $index = $e.index;
        if ($e.listAddress != null) {
            $listAddress = $e.listAddress;
        }
        $primVariable = $e.primVariable;

    } else if($e.element != null && !($e.element instanceof Variable) && !($e.element instanceof PropertyValue)) { 			
        $element = $e.element;
    } else {
        $element = null;
    }
}
	} 	(op=(PLUS|MINUS) e=multExpr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(null != $element) {
            if(null != $e.element) {
                $element = this.interp.doMinPlusOp($e.element, $element, $op.text);                
            } else if(null != $e.p) {
                $element = this.interp.doMinPlusOp($e.p, $element, $op.text);
            }
            $p = null;            
        } else {        
            if(null != $e.element) {
                this.interp.doMinPlusOp($e.element, $p, $op.text);
            } else {
                this.interp.doMinPlusOp($e.p, $p, $op.text); 
            }
            $element = null;
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}
	})*
	;

/****
	 	| mi=MINUS e=multExpr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        this.interp.doMinPlusOp($e.p, $p, $mi.text);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
    
    $element = null;
}
	} 
 ****/
 	
multExpr[boolean defer] 
	returns [Variable p, String instance, int index, String listAddress, Variable primVariable, NamedElement element]
	:	e=atom[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if( null != $e.p) {
        $p = copyVariable($e.p);
        if ($e.instance != null) {
            $instance = $e.instance;
        }
    
        $index = $e.index;
        if ($e.listAddress != null) {
            $listAddress = $e.listAddress;
        }
        $primVariable = $e.primVariable;

    } else if($e.element != null && !($e.element instanceof Variable) && !($e.element instanceof PropertyValue)) { 			
        $element = $e.element;
    } else {
        $element = null;
    }
}	
	} 	( (mul=MULT|div=DIV) e=atom[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			$p.num = Double.parseDouble($n.text);
			$p.type = EugeneConstants.NUM;
			
			$element = null;
		}
		}
	|	MINUS (n=NUMBER | n=REAL)
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			$p.num = Double.parseDouble($n.text) * -1.0;
			$p.type = EugeneConstants.NUM;

			$element = null;
		}
		}
	|	(t=(TRUE_LC|TRUE_UC) | f=(FALSE_LC|FALSE_UC))
		{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			$p.type = EugeneConstants.BOOLEAN;
			if ($t != null) {
				$p.bool = true;
			} else {
				$p.bool = false;
			}

			$element = null;
		}
		}
	|	dn=dynamic_naming[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $element = this.interp.get($dn.name);
					
        if(null != $element) {
            if($element instanceof Variable) {
                $p = copyVariable((Variable)$element);
                $primVariable = (Variable)$element;
            }
        } else {
            throw new EugeneException($dn.name + " is not declared.");
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}
	}	oc=object_access[defer, $element] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $element = $oc.child;
    
    if($element instanceof Variable) {
        $p = (Variable)$element;
        $element = null;
    } else if($element instanceof PropertyValue) {
        $p = this.interp.convertPropertyValueToVariable((PropertyValue)$element);
        $element = null;
    } else {
        $p = null;
    }
}		
	} 
	|	STRING
	{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			$p.type = EugeneConstants.TXT;
			$p.txt = $STRING.text.substring(1, $STRING.text.length()-1);

			$element = null;
		}
	}
	|	'(' expr[defer] ')'
	{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			$p = $expr.p;
			$primVariable = $expr.primVariable;
			$element = $expr.element;
		}
	}
		|	LEFTSBR list[defer] RIGHTSBR
	{
		if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
			$p = $list.listPrim;
			$primVariable = $list.listPrim;
			typeList="";
		}
	}
		|bif=built_in_function[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && null != $bif.element) {
    if($bif.element instanceof Variable) {
        $p = (Variable)$bif.element;
        $element = null;
    } else {
        $element = $bif.element;
        $p = null;
    }
}		
	}
	|	fc=function_call[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING && null != $fc.e) {
    if($fc.e instanceof Variable) {
        $p = (Variable)$fc.e;
        $element = null;
    } else {
        $element = $fc.e;
        $p = null;
    }
}		
	}
	;

list [boolean defer]
	returns [Variable listPrim]
	:	str1=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING){
    if (null != $str1.p) {
        $listPrim = new Variable();
        if(EugeneConstants.NUM.equals(($str1.p).getType())) {
            $listPrim.type =  EugeneConstants.NUMLIST;
            typeList = EugeneConstants.NUM;
        } else if(EugeneConstants.TXT.equals(($str1.p).getType())) {
            $listPrim.type =  EugeneConstants.TXTLIST;
            typeList = EugeneConstants.TXT;
        } else {
            printError("Only arrays of num and txt primitives are supported!");
        }
        
        addToListPrim($str1.p, $listPrim);
    } else {
        printError("Only arrays of num and txt primitives are supported!");
    }
}
	} 	(COMMA str2=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if(null != $str2.p) {
        addToListPrim($str2.p, $listPrim);
    } else {
        printError("Only arrays of num and txt primitives are supported!");
    }
}				
	} 	)*
	;
  
/*------------------------------------------------------------------
 * BUILT-IN FUNCTIONS
 * E.g. sizeof()
 * Built-in functions can be used on the RHS of assignments, 
 * i.e. they return same result which is interpreted later
 * i.e. in the "assignment" or "statement" rule 
 *------------------------------------------------------------------*/
built_in_function[boolean defer]
	returns [NamedElement element]
	:	(SIZEOF_LC|SIZEOF_UC|SIZE_LC|SIZE_UC) LEFTP e=expr[defer] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(null != $e.element) {
            $element = this.interp.getSizeOf($e.element);
        } else if(null != $e.p) {
            $element = this.interp.getSizeOf($e.p);
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	|	(RANDOM_LC|RANDOM_UC) LEFTP rg=range[defer] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $element = this.interp.getRandom(
                          $rg.sor, 
                          $rg.eor);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	|	(pr=PRODUCT|pe=PERMUTE) LEFTP idToken=ID RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(pr != null) {
            $element = this.interp.product($idToken.text);
        } else {
            $element = this.interp.permute($idToken.text);
        }
    } catch(Exception ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	;

/*------------------------------------------------------------------
 * STAND-ALONE FUNCTIONS
 * E.g. SAVE(my_part)
 * Stand-alone functions can NOT be used on the RHS of assignments, 
 * i.e. they perform actions immediately
 *------------------------------------------------------------------*/
stand_alone_function[boolean defer] 
	:	(SAVE_LC|SAVE_UC|STORE_LC|STORE_UC|CREATE_LC|CREATE_UC) LEFTP e=expr[defer] RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try  {
        if(null != $e.element) {
            this.interp.storeIntoLibrary($e.element);
        } else {
            throw new EugeneException("Cannot store " + $e.text + " into the library!");
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	|	(EXIT_LC | EXIT_UC) (LEFTP p=toPrint[defer] RIGHTP)? {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if(null == p) {
        printError("exiting...");
    } else {
        printError($p.sb);
    }
}
	}
	;

range[boolean defer] 
	returns [Variable sor, Variable eor]
	:	s=expr[defer] COMMA e=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

    if(null != $s.p) {
        if(!EugeneConstants.NUM.equals($s.p.getType())) {
            printError("Invalid start of range!");
        }
        if($s.p.getNum() \% 1 != 0) {
            printError("The start of the range is not an integer!");
        }    
        $sor = $s.p;
    }  
   
    if(null != $e.p) {
        if(!EugeneConstants.NUM.equals($e.p.getType())) {
            printError("Invalid end of range!");
        }
        if($e.p.getNum() \% 1 != 0) {
            printError("The end of the range is not an integer!");
        }    
        
        $eor = $e.p;
    }
    
    if($sor.num > $eor.num) {
        printError("Invalid range!");
    }
}	
	}
	;	
	
/*------------------------------------------------------------------
 * DATA ACCESS
 * E.g. D1.D2.D3[0].p1.prop1
 *------------------------------------------------------------------*/
/***
data_access[boolean defer]
	returns [NamedElement e]
	:	id=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = this.interp.get($id.text);
    if(null == $e) {
        printError($e + " does not exist.");
    }
}	
	}	rhs=object_access[defer, $e] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $rhs.child;
}		
	}
	;
 ***/
 
object_access[boolean defer, NamedElement parent]
	returns [NamedElement child]
	:	// empty-word 
	{
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $child = parent;
}	
	}
		|(DOT (id=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
    
        $child = parent.getElement($id.text);

        if(null == $child) {
            throw new EugeneException(parent.getName() + " does not contain " + $id.text);
        }

    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	} |	(SIZE_UC|SIZE_LC) (LEFTP RIGHTP)? {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $child = this.interp.getSizeOf(parent);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	})
	|	LEFTSBR (exp=expr[defer]) RIGHTSBR {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        if(null != $exp.p && EugeneConstants.NUM.equals($exp.p.getType())) {
        
            if($exp.p.getNum() \% 1 != 0 && $exp.p.getNum() < 0) {
                throw new EugeneException("Invalid index " + $exp.p + "!");
            }
            
            $child = parent.getElement((int)($exp.p.getNum()));
            
            if(null == $child) {
                throw new EugeneException(parent.getName() + " does not contain " + $id.text);
            }
            
        } else {
            throw new EugeneException("Invalid index " + $exp.p + "!");
        }
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	})	o=object_access[defer, $child] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $child = $o.child;
}	
	}
	;	

/*------------------------------------------------------------------
 * DYNAMIC NAMING FEATURES
 *------------------------------------------------------------------*/
dynamic_naming[boolean defer]
	returns [String name]
	:	i=ID {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $name = $i.text;
}	
	}
	|	DOLLAR LEFTCUR e=expr[defer] RIGHTCUR {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if($e.p == null) {
        printError("Invalid name!");
    } else if(!EugeneConstants.TXT.equals(($e.p).getType())) {
        printError("The name must be of type txt!");
    }
    
    $name = ($e.p).getTxt();
}	
	}
	;


/*------------------------------------------------------------------
 * DATA EXCHANGE STATEMENTS
 *------------------------------------------------------------------*/
dataExchange[boolean defer] 
	returns [NamedElement e]
	:	s=sbolStatement[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $s.e;
}	
	}
	|	i=importStatement[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $i.e;
}	
	}
	|	g=genbankStatement[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $g.e;
}	
	
	}
	|	r=registryStatement[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $r.e;
}		
	}
	;
	
/*** include STATEMENT ***/
includeStatement[boolean defer]
	:	(HASHMARK)? (INCLUDE_LC|INCLUDE_UC) file=STRING {
if(this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    try {
        this.interp.includeFile($file.text, ParsingPhase.PRE_PROCESSING);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
} else if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        this.interp.includeFile($file.text, ParsingPhase.INTERPRETING);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}
	}
	;

/*** include STATEMENT ***/
importStatement[boolean defer]
	returns [NamedElement e]
	:	(IMPORT_LC|IMPORT_UC) LEFTP file=STRING {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $e = this.interp.importFile($file.text);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}
	}	RIGHTP
	;
		
/*** SBOL STATEMENTS ***/
sbolStatement[boolean defer] 
	returns [NamedElement e]
	:	SBOL DOT (sbolExportStatement[defer] | i=sbolImportStatement[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $i.e;
}	
	} | 	sbolVisualStatement[defer] {
	
	})
	;

sbolExportStatement[boolean defer] 
	:	(EXPORT_LC|EXPORT_UC) LEFTP idToken=ID COMMA filenameToken=STRING RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        this.interp.exportToSBOL(
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
	:	(IMPORT_LC|IMPORT_UC) LEFTP fileToken=STRING RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $e = this.interp.importSBOL($fileToken.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}	
	}
	;	
	
sbolVisualStatement[boolean defer]
	:	(VISUALIZE_LC|VISUALIZE_UC) LEFTP idToken=ID RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        this.interp.visualSBOL($idToken.text);
    } catch(EugeneException ee) {
        printError(ee.getMessage());
    }
}		
	}	
	;	

/*---------------------
 * GenBank
 *--------------------*/	
genbankStatement[boolean defer] 
	returns [NamedElement e]
	:	GENBANK DOT (i=genbankImportStatement[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $i.e;
}	
	}
	|	genbankExportStatement[defer]) {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = null;
}	
	}
	;
		
genbankExportStatement[boolean defer] 
	:	(EXPORT_UC|EXPORT_LC) LEFTP RIGHTP
	;
	
genbankImportStatement[boolean defer] 
        returns [NamedElement e]
	:	(IMPORT_LC|IMPORT_UC) LEFTP f=STRING RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $e = this.interp.importGenbank($f.text);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    } 
}
	}
	|	(IMPORT_LC|IMPORT_UC) LEFTP typeToken=ID COMMA partToken=STRING RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    // TODO!!!
}	
	} 
	;	


/*---------------------
 * iGEM partsregistry
 *---------------------*/
registryStatement[boolean defer]
	returns [NamedElement e]
	:	REGISTRY DOT (IMPORT_LC|IMPORT_UC) LEFTP n=STRING RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

    try {
        String name = $n.text;
        name = name.substring(1, name.length()-2);
        $e = this.interp.importRegistry(name);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());    
    }
}		
	}	
	;


testStatements[boolean defer]
	: 	|	ASSERT LEFTP id=ID DOT (SIZE_UC|SIZE_LC) LEFTP RIGHTP EQUALS EQUALS n=NUMBER RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
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
	|		NOTE LEFTP id=ID DOT (SIZE_UC|SIZE_LC) LEFTP RIGHTP EQUALS EQUALS n=NUMBER RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
}
}
	;

/*------------------------
 * USER-DEFINED FUNCTIONS
 *------------------------*/
function_definition[boolean defer] 
	throws EugeneReturnException
	:	(rt=type_specification[true])? n=ID LEFTP (lop=list_of_parameters[true])? RIGHTP LEFTCUR 
			stmts=list_of_statements[true] 
		RIGHTCUR {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {  // FUNCTION DEFINITION 
    try {
        // let's check if a return type is specified
        String return_type = null;
        if(null != rt) {
            return_type = $rt.t;
        }
        
        // let's check if parameters are specified
        List<NamedElement> parameters = null;
        if(null != lop) {
            parameters = $lop.parameters;
        }
        
        // Function w/ parameters
        this.interp.defineFunction(
                return_type,     // return type
                $n.text,         // the name of the function
                parameters,      // list of parameters
                $stmts.start,    // list of statements
                this.input);     // the token stream
                
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	;

type_specification[boolean defer] 
	returns [String t]
	:	NUM {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    $t = EugeneConstants.NUM;
}	
	}
	|	TXT {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    $t = EugeneConstants.TXT;
}	
	}
	|	NUM LEFTSBR RIGHTSBR {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    $t = EugeneConstants.NUMLIST;
}	
	}
	|	TXT LEFTSBR RIGHTSBR {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    $t = EugeneConstants.TXTLIST;
}	
	}
	|	(BOOL|BOOLEAN) {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    $t = EugeneConstants.BOOLEAN;
}	
	}
	;	

list_of_parameters[boolean defer]
	returns [List<NamedElement> parameters]
	:	pt=type_specification[defer] n=ID {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    if(null == $parameters) {
        $parameters = new ArrayList<NamedElement>();
    }
    
    try {
        $parameters.add(
            this.interp.createFunctionParameter(
                $pt.t,        // type of the parameter
                $n.text));    // name of the parameter
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}	(COMMA lop=list_of_parameters[defer] {
if(defer && this.PARSING_PHASE == ParsingPhase.PRE_PROCESSING) {
    $parameters.addAll($lop.parameters);    
}	
	}	)?
	;	

list_of_statements[boolean defer]
	throws EugeneReturnException
	:	statement[defer] (statement[defer])*
	;

return_statement[boolean defer]
	returns [NamedElement el]
	throws EugeneReturnException
	:	(RETURN_LC | RETURN_UC) e=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {

    if(!this.interp.isReturnAllowed()) {
        printError("a return statement is not allowed at this position!");
    }

    if(null != $e.element) {
        $el = $e.element;
    } else {
        $el = $e.p;
    }
    
    throw new EugeneReturnException($el);
}
	}	
	;	
	
	

/*------------------------------------------------------------------
 * FUNCTION CALLS
 * are either:
 * - calls of predefined functions (like product, permute)
 * - calls of user-defined functions
 *------------------------------------------------------------------*/

function_call[boolean defer]
	returns [NamedElement e]
	:	udf=call_user_defined_function[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $e = $udf.e;
}	
	}
	;
	
	
call_user_defined_function[boolean defer]
	returns [NamedElement e]
	:	f=ID LEFTP (loe=list_of_expressions[defer])? RIGHTP {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    try {
        $e = this.call_function($f.text, $loe.parameter_values);
    } catch(EugeneException ee) {
        printError(ee.getLocalizedMessage());
    }
}	
	}
	;	

list_of_expressions[boolean defer]
	returns [List<NamedElement> parameter_values]
	:	e=expr[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    if(null == $parameter_values) {
        $parameter_values = new ArrayList<NamedElement>();
    }
    
    if(null == $e.p) { 
        $parameter_values.add($e.element);
    } else {
        $parameter_values.add($e.p);
    }
    
}	
	} 	(COMMA loe=list_of_expressions[defer] {
if(!defer && this.PARSING_PHASE == ParsingPhase.INTERPRETING) {
    $parameter_values.addAll($loe.parameter_values);
}	
	}	)? 
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
