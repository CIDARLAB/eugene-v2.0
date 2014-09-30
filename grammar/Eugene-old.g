grammar Eugene;

options {
language=Java;
output=AST;
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

import org.cidarlab.eugene.constants.EugeneConstants;
import org.cidarlab.eugene.dom.*;
import org.cidarlab.eugene.dom.functions.*;
import org.cidarlab.eugene.dom.loops.*;
import org.cidarlab.eugene.dom.branches.*;
import org.cidarlab.eugene.dom.rules.*;
import org.cidarlab.eugene.dom.arrays.*;
import org.cidarlab.eugene.dom.characterization.*;
import org.cidarlab.eugene.dom.collection.*;
import org.cidarlab.eugene.dom.components.*;
import org.cidarlab.eugene.dom.components.types.*;
import org.cidarlab.eugene.factory.DeviceFactory;
//import org.cidarlab.eugene.rules.*;
import org.cidarlab.eugene.rules.RuleEngine;
import org.cidarlab.eugene.exception.*; 
import org.cidarlab.eugene.util.*;
import org.cidarlab.eugene.output.ResultSet;
import org.cidarlab.eugene.data.registry.*;
import org.cidarlab.eugene.interpreter.*;
import org.cidarlab.eugene.cache.*;
import org.cidarlab.eugene.builder.EugeneBuilder;
import org.apache.commons.lang3.ArrayUtils;
import org.cidarlab.eugene.rules.RuleOperator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

// SBOL
import org.cidarlab.eugene.data.sbol.*;
import org.cidarlab.eugene.data.sbol.mapping.*;

// for Genbank import
import org.cidarlab.eugene.data.genbank.*;

// Pigeon
import org.cidarlab.eugene.data.pigeon.Pigeon;

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
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

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

//private RuleEngine ruleEngine = new RuleEngine();
private SBOLRegistryImporter registryImporter = null;
private Runtime rt = Runtime.getRuntime();
private Interp interp = new Interp();

public ResultSet getResultSet() {
    if(null != interp) {
        return interp.getResultSet();
    }
    return null;
}

public Object exec(String rule, int tokenIndex) 
        throws EugeneReturnException {
    Object rv = null;
    int oldPosition = this.input.index(); // save current location
    this.input.seek(tokenIndex); // seek to place to start execution
    try { // which rule are we executing?
        if(rule.equals("ifCondition")) { 
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
    } catch (Exception e) {
        e.printStackTrace();
    }
    finally { 
        // restore location
        this.input.seek(oldPosition); 
    }
    return rv;
}

public NamedElement callFunction(Function objFunction, ArrayList<NamedElement> lstParameterValues) 
    throws EugeneException {        

    // save current location
    int oldPosition = this.input.index(); 

    objFunction.setOldPosition(oldPosition);
    
    // compare the parameter values with the function's parameters
    if(!objFunction.checkParameters(lstParameterValues)) {
        throw new EugeneException(
            "Invalid parameter values to call the function "+objFunction.getName());
    }
    
    objFunction.initSymbolTables(lstParameterValues);
        
    // execute the function's statements
    NamedElement objReturnValue = null;
    Token objToken = objFunction.getStatements();
    if(objToken!=null && objReturnValue==null) {
        this.input.seek(objToken.getTokenIndex());
    
        try {
            this.listOfStatements(false);       
        } catch(EugeneReturnException ere) {
            objReturnValue = ere.getReturnValue();
        } catch(RecognitionException re) {
            throw new EugeneException(re.getMessage());
        }        
    }

    try {
        this.cleanUpFunction(objReturnValue); 
    } catch(Exception e ){
        throw new EugeneException(e.getMessage());
    }

    this.input.seek(oldPosition);
    
    return objReturnValue;
}

public void cleanUpFunction(NamedElement objReturnValue) 
        throws EugeneException {

    String sFunctionName = SymbolTables.getCurrentFunctionName();        
    NamedElement objElement = SymbolTables.get(sFunctionName);
            
    if(null != objElement && objElement instanceof Function) {
    	Function objFunction = (Function)objElement;
        NamedElement objReturn = objFunction.getReturn();
        if(objReturnValue!=null && objReturn!=null) { 
        
            /** COMPARE THE RETURN VALUES **/       
            if(objReturn instanceof Variable && objReturnValue instanceof Variable) {
                String sReturnType = ((Variable)objReturn).getType();
                String sReturnValueType = ((Variable)objReturnValue).getType();
                if(!sReturnType.equals(sReturnValueType)) {
                    throw new EugeneException("The return value of function "+objFunction.getName()+" is not of type "+sReturnType+"!");
                }
            } else if(objReturnValue instanceof PartType && objReturn instanceof PartType) {
                if(!objReturnValue.getName().equals(objReturn.getName())) {
                    throw new EugeneException("The return value of function "+objFunction.getName()+" is not of type "+objReturn.getName()+"!");
                }
            } else if(objReturn instanceof PartType && objReturnValue instanceof Part) {
                if(!((PartType)objReturn).getName().equals(((Part)objReturnValue).getPartType().getName())) {
                    throw new EugeneException("The return value of function "+objFunction.getName()+" is not of type "+objReturn.getName()+"!");
                }
            } else if(!objReturn.getClass().equals(objReturnValue.getClass())) {
                throw new EugeneException("The return value of function "+objFunction.getName()+" is not of type "+this.getObjectType(objReturn)+"!");
            }  
        }
        
        if(objReturn!=null && objReturnValue==null) {
            System.err.println(
                "WARNING! The Function "+objFunction.getName()+
                " should return a value of type "+this.getObjectType(objReturn)+"!");
            System.err.flush();
        }
 
        objFunction.clear();
    }
}

public void whileStat(Token condStart, Token whileStart) 
        throws EugeneReturnException {
    expression_return ret=(expression_return)exec("expression", condStart.getTokenIndex());
    Variable exprValue = (Variable)ret.objElement;
    boolean b = exprValue.getBoolean();
    while ( b ) { // if true, execute statements, and re-evalute condition
        exec("listOfStatements", whileStart.getTokenIndex());

        ret=(expression_return)exec("expression", condStart.getTokenIndex());
        exprValue = (Variable)ret.objElement;
        b = exprValue.getBoolean();
    }
}

public void forStat(Token initStart, Token condStart, Token incStart, Token forStart) 
        throws EugeneException, EugeneReturnException {

    int oldPosition = this.input.index(); // save current location

    ArrayList<NamedElement> lstInitVariables = execForInit(initStart.getTokenIndex());
    ForLoop objFor = new ForLoop();
    objFor.setInitVariables(lstInitVariables);
	        
    SymbolTables.push(objFor);

    expression_return ret=(expression_return)exec("expression", condStart.getTokenIndex());
    Variable exprValue = (Variable)ret.objElement;
    boolean b = exprValue.getBoolean();
    
    int i = 0;
    while (b) {
        // execution of the loop body
        try {
	    exec("listOfStatements", forStart.getTokenIndex());
        } catch(EugeneReturnException e) {
            throw new EugeneReturnException(e.getReturnValue());
        }
        
        // incrementation
        exec("computationalStatement", incStart.getTokenIndex());
        
        // evaluation of the loop condition
        ret=(expression_return)exec("expression", condStart.getTokenIndex());
        exprValue = (Variable)ret.objElement;
        b = exprValue.getBoolean();

        // after each loop iteration, clear the loop's symbol tables
        if(b) {        
            StackElement objStackElement = SymbolTables.peek();
            if(objStackElement != null && objStackElement instanceof ForLoop) {  // this should always be true
                ((ForLoop)objStackElement).clear(); // clear the loop's symbol tables
                objStackElement = null;
            }
        }
    }
    
    StackElement objStackElement = SymbolTables.peek();
    if(objStackElement != null && objStackElement instanceof ForLoop) {  // this should always be true
        ((ForLoop)objStackElement).clear(); // clear the loop's symbol tables
        SymbolTables.pop();
        
        // now, also clear the loops init variables
        ((ForLoop)objStackElement).finalCleanUp();
        objStackElement = null;
    }

    this.input.seek(oldPosition);
}

public ArrayList<NamedElement> execForInit(int tokenIndex) {
    ArrayList<NamedElement> lstElements = new ArrayList<NamedElement>();
    int oldPosition = this.input.index(); // save current location
    this.input.seek(tokenIndex); // seek to place to start execution
    try { // which rule are we executing?
        forInit_return rv = this.forInit(false); 
        lstElements = rv.lstElements;
    } catch (Exception e) {
        e.printStackTrace();
        //this.cleanUp(1);
    }
    finally { 
        // restore location
        this.input.seek(oldPosition); 
    }
    return lstElements;
}

    private void executeIf(IfStatement objIf) 
        throws EugeneException, EugeneReturnException {
        ConditionalBranch ifBranch = objIf.getIfBranch();
        boolean b;

        if(this.evaluateCondition(ifBranch)) {
            this.executeBranch(ifBranch);                
        } else {
            // evaluate the else-if conditions and execute the corresponding statements
            ArrayList<ConditionalBranch> lstElseIfBranches = objIf.getElseIfBranches();
            boolean bExecuted = false;
            if(lstElseIfBranches!=null && !lstElseIfBranches.isEmpty()) {
                for(int i=0; i<lstElseIfBranches.size() && !bExecuted; i++) {
                    ConditionalBranch elseIfBranch = lstElseIfBranches.get(i);
                    if(this.evaluateCondition(elseIfBranch)) {
                        this.executeBranch(elseIfBranch);                        
                        bExecuted = true;
                    }
                }
            } 
                
            // if no branch got executed, execute the statements of the else branch
            if(!bExecuted) {
                ConditionalBranch elseBranch = objIf.getElseBranch();
                if(elseBranch!=null) {
                    this.executeBranch(elseBranch);
                }
            }
        }
    }

private void executeBranch(ConditionalBranch objBranch) 
        throws EugeneReturnException {
    SymbolTables.push(objBranch);
    
    this.exec("listOfStatements", objBranch.getStatements().getTokenIndex());

    StackElement objElement = SymbolTables.peek();
    if(objElement!=null && objElement instanceof ConditionalBranch) {  
        ((ConditionalBranch)objElement).clear();        
        SymbolTables.pop();
    }
}
    
private boolean evaluateCondition(ConditionalBranch objBranch) 
        throws EugeneException {

    int oldPosition = this.input.index(); // save current location
    Token condToken = objBranch.getCondition();
    this.input.seek(condToken.getTokenIndex()); // seek to place to start execution

    ifCondition_return ret = null;
    try {
        ret = this.ifCondition(false);
    } catch(RecognitionException re) {
        throw new EugeneException(re.getMessage());
    }
	
    this.input.seek(oldPosition);

    return ret.b;
}

public boolean existsRule(String sRuleName) {
    NamedElement objElement=SymbolTables.get(sRuleName);
    if(null!=objElement && objElement instanceof Rule) {
        return true;
    }
    return false;
}

public String getObjectType(NamedElement objElement) {
    if(null == objElement) {
        return null;
    } else if(objElement instanceof Device) {
        return EugeneConstants.DEVICE;
    } else if(objElement instanceof PartType) {
        return ((PartType)objElement).getName();
    } else if(objElement instanceof Part) {
        return ((Part)objElement).getPartType().getName();
    } else if(objElement instanceof Variable) {
        return ((Variable)objElement).getType();
    }
    return objElement.getClass().toString();
}

public void initSymbolTables() {
    SymbolTables.init();
}

public void cleanUp(int nExitCode) {
    this.cleanUpNoExit();
    
    Runtime.getRuntime().halt(nExitCode);

    // and exit
    //System.exit(nExitCode);
}

public void cleanUpNoExit() {
    // clear the symbol tables
    SymbolTables.cleanUp();
	    
    // clear the interpreter's data structures
    if(null != this.interp) {
        this.interp.cleanUp();
        this.interp = null;
    }
    
    if(null != registryImporter) {
        this.registryImporter = null;
    }
}

public NamedElement createElement(String sType, String sName) {
    if(EugeneConstants.DEVICE.equalsIgnoreCase(sType)) {
        return new Device(sName);
    } else if(EugeneConstants.PART.equalsIgnoreCase(sType)) {
        return new PartType(sName);
    } else if(EugeneConstants.TXT.equalsIgnoreCase(sType) || 
              EugeneConstants.TXTLIST.equalsIgnoreCase(sType) ||
              EugeneConstants.NUM.equalsIgnoreCase(sType) ||
              EugeneConstants.NUMLIST.equalsIgnoreCase(sType) ||
              EugeneConstants.BOOLEAN.equalsIgnoreCase(sType)) {
        return new Variable(sName,sType);
    } else if(EugeneConstants.DEVICEARRAY.equalsIgnoreCase(sType)) {
        return new DeviceArray(sName);
    } else if(EugeneConstants.PARTARRAY.equalsIgnoreCase(sType)) {
        return new PartArray(sName);
    } else if(EugeneConstants.PROPERTYARRAY.equalsIgnoreCase(sType)) {
        return new PropertyArray(sName);
    } else if(EugeneConstants.RULEARRAY.equalsIgnoreCase(sType)) {
        return new RuleArray(sName);
    } else {
        return SymbolTables.get(sType);
    }
}
}

prog
	:	eugeneStatement+ EOF
	;

eugeneStatement
	:	statement[false]
	|	functionToken=functionDeclaration {
SymbolTables.put($functionToken.objFunction);
	}
	;	
	catch [Exception e] {
System.err.println(e.toString());
e.printStackTrace();
this.cleanUp(1);
	}

statement[boolean defer] 
    returns [NamedElement objReturnValue]
    throws EugeneReturnException
	:	INCLUDE ';'
	|	dataExtraction[defer] ';'
	|	declToken=declarationStatement[defer] ';'	/* Declaration of new PartTypes, Devices, Properties, PropertyValues, and Rules */
	;
/***
	|	computationalStatement[defer] ';'
	|	printStatement[defer] ';'
	|	ifStatement[defer]
	|	assertStatement[defer] ';'		
	|	noteStatement[defer] ';'		
	|	wrappedStatement[defer] ';'
	    |   loopStatement[defer]
	|	returnToken=returnStatement[defer] {
if(!defer) {
    $objReturnValue = $returnToken.objReturnValue;
    throw new EugeneReturnException($objReturnValue);
}	
	}
	|	saveStatement[defer] ';'
	;

computationalStatement[boolean defer] 
	:	functionCall[defer]
	|	instToken=instantiationStatement[defer] {
if(!defer) {
    SymbolTables.put($instToken.objComponent);
}	
	}
	|	leftToken=objectAssignmentStatement[defer] {
if(!defer) {
    if(null != $leftToken.assignElement) {
        interp.assign(
            (CommonTree)$leftToken.tree, 
            $leftToken.assignElement);
    }
}	
	}
	;
	catch[EugeneException e] {
System.err.println("[computationalStatement] "+e);
e.printStackTrace();
this.cleanUp(1);	
	}
**/

assignment[boolean defer] returns [NamedElement objElement]
	:	'=' exprToken=expression[defer] {
if(!defer) {
    $objElement = $exprToken.objElement;
}	
	}
	| 	'=' funcToken=functionCall[defer] {
if(!defer) {
    $objElement = $funcToken.objElement;
}	
	}
        |	'=' stmtToken=wrappedStatement[defer] {
if(!defer) {
    $objElement = $stmtToken.objElement;
}        
        }
        |	'+' '+' {
if(!defer) {
    $objElement = null;
}        
        }
        |	'-' '-' {
if(!defer) {
    $objElement = null;
}        
        }
	;	

importStatement[boolean defer] 
	:	importToken='import' lstToken=listOfIDs[defer] {
if(!defer) {
    Iterator<NamedElement> it=$lstToken.lstElements.iterator();
    while(it.hasNext()) {
        NamedElement objElement = it.next();
        if(null==SymbolTables.get(objElement.getName())) {
            try {
                RegistryPart objPart = registryImporter.importPart(objElement.getName());
                if(null!=objPart) {
                    SymbolTables.put(objPart);
                } else {
                    System.err.println("line "+$importToken.line+" => "+
                        "Cannot import "+objElement.getName()+"!");
                    this.cleanUp(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("line "+$importToken.line+" => "+
                  "An element named "+objElement.getName()+" exists already!");
            this.cleanUp(1);
        }
    }                    
}	
	}
	;
****/
	
listOfFilenames[boolean defer]
	:	filenameToken=filename[defer] (',' listOfFilenames[defer])?
	;
	
filename[boolean defer] returns [String sFilename]
	:	filenameToken+=( ~(','|';'|'(') )*  {
if(!defer) {	
    StringBuilder sb=new StringBuilder();
    Iterator<CommonToken> it=$filenameToken.iterator();
    while(it.hasNext()) {
        sb.append(it.next().getText());
    }
    $sFilename=sb.toString();    
}
	}   
	;	

//********************
//*** DECLARATIONS ***
//********************
declarationStatement[boolean defer]
	:	//collectionDeclaration[defer]
//	|	variableDeclaration[defer]
	|	propertyDeclaration[defer]
	|	partTypeDeclaration[defer]
	|	deviceDeclaration[defer]
//	|	arrayDeclaration[defer]
//	|	ruleDeclaration[defer]
//	|	imageDeclaration[defer]
//	|	deviceTypeDeclaration[defer]
|	|	relationDeclaration[defer]
	;
 	
/*** COLLECTION
collectionDeclaration[boolean defer]
@init {
EugeneCollection objCollection = null;
}
	:	'Collection' nameToken=ID {
if(!defer) {
    objCollection = interp.createCollection(
                $nameToken.text, 
                (java.util.Collection<NamedElement>)null);
}             
	}	
		(defToken=collectionDefinition[defer, objCollection] {
if(!defer) {
    objCollection.setElements( 
                $defToken.colElements);
}	
	} | 	 assignToken = assignment[defer] {
if(!defer) {
    interp.assign(
                $nameToken.text, 
                $assignToken.objElement);
}             
	})?
        ;
        catch[EugeneException e] {
System.err.println(
    "line "+$nameToken.line+":"+$nameToken.pos+
    " => "+e);
e.printStackTrace();    
this.cleanUp(1);
        }
 ***/	
 
collectionDefinition[boolean defer, EugeneCollection objCollection]
	returns [java.util.Set<CollectionElement> colElements]
@init {
$colElements = new java.util.HashSet<CollectionElement>();
}	
	:	'(' {
if(!defer) {
    // we need to push the collection onto the stack
    // because in the interpreter the collection elements 
    // will be put into the symbol tables...
    SymbolTables.push(objCollection);
}	
	} 	lstToken=listOfCollectionComponents[defer] {
if(!defer) {
    $colElements.addAll($lstToken.lstElements);
}	
	} 	')' {
if(!defer) {
    if(null != SymbolTables.peek()) {
        // pop the collection from the stack
        SymbolTables.pop();
    }
}	
	}
	|	'=' includeToken=INCLUDE {
if(!defer) {
    throw new EugeneException("such an assignment is not supported yet!");
}	
	}
	;
	catch[EugeneException e] {
System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+" => "+
    e.toString());
e.printStackTrace();
this.cleanUp(1);
	}

listOfCollectionComponents[boolean defer]
        returns [Set<CollectionElement> lstElements]
@init {
$lstElements=new HashSet<CollectionElement>();
}
        :	componentToken=collectionElement[defer] {
if(!defer) {
    $lstElements.add($componentToken.objElement);
}        
        } (',' lstToken=listOfCollectionComponents[defer] {
if(!defer) {
    $lstElements.addAll($lstToken.lstElements);
}        
        })?
        ;
        
collectionElement[boolean defer] returns [CollectionElement objElement]
	:	propertyToken=propertyDeclaration[defer]
	|	partTypeToken=partTypeDeclaration[defer] {
if(!defer) {
    $objElement = partTypeToken.objPartType;
}            
        }
        |	deviceToken=deviceDeclaration[defer] {
if(!defer) {
    $objElement = $deviceToken.objDevice;
}        
        }
	|	instToken=instantiationStatement[defer] {
if(!defer) {
    $objElement = $instToken.objComponent;
}
        }
        |	idToken=ID {
if(!defer) {
    NamedElement objEl = interp.get($idToken.text);
    if(null!=objEl && objEl instanceof CollectionElement) {
        $objElement = (CollectionElement)objEl;
    }
}        
	}
	;	

/*** PROPERTY ***/	
propertyDeclaration[boolean defer]
	returns [Property objProperty]
	:	'Property' nameToken=dynamicNaming[defer] '(' typeToken=propertyType ')' {
if(!defer) {
    $objProperty = interp.createProperty(
        $nameToken.sName, 
        $typeToken.text);
}
        }
	;
	catch[EugeneException e] {
System.err.println("line "+nameToken.start.getLine()+":"+nameToken.start.getCharPositionInLine()+" => "+        
    e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}

propertyType
	:	'txt'
	|	'txt' '[' ']'
	|	'num'
	|	'num' '[' ']'
	|	'boolean'
	;
	

/*** PropertyValues ***/	
variableDeclaration[boolean defer]
	returns [List<Variable> lstVariables]
	:	typeToken=propertyType varToken=listOfVariables[defer, $typeToken.text] {
if(!defer) {
    $lstVariables = $varToken.lstVariables;
}	
	}
	;

listOfVariables[boolean defer, String sVariableType]
	returns [List<Variable> lstVariables]
@init {
$lstVariables = new ArrayList<Variable>();
}	
	:	idToken=ID {
if(!defer) {
    $lstVariables.add(
        interp.createVariable($idToken.text, sVariableType));
}
	}      (
	
	assignToken=tmpStatement[defer] {
//	assignToken=assignment[defer] {
if(!defer) {
    interp.assign($idToken.text, 
        $assignToken.objElement);
}
        })? 	(',' lstToken=listOfVariables[defer, sVariableType] {
if($defer) {
    $lstVariables.addAll($lstToken.lstVariables);
}        
        })?
	;
	catch [Exception exc] {
if(!defer) {	
System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
     exc.getMessage());
exc.printStackTrace();
this.cleanUp(1);
}    
	}		

/*** Part Type ***/	
partTypeDeclaration[boolean defer] returns [PartType objPartType]
	:	('Part'|'PartType')  nameToken=ID ('(' (lstToken=listOfIDs[defer])? ')')? {
if(!defer) {
    interp.createPartType(
            $nameToken.text, 
            $lstToken.lstElements);
}
        }
        |	('Part'|'PartType') nameToken=ID assignToken=assignment[defer] {
if(!defer) {
    interp.assign(
            $nameToken.text, 
            $assignToken.objElement);
}        
        }
	;
	catch [Exception e] {
System.err.println("line "+$nameToken.line+":"+$nameToken.pos+" => "+
        e.getMessage());
e.printStackTrace();
this.cleanUp(1);	
        }
	
listOfIDs[boolean defer] returns [List<NamedElement> lstElements]
@init {
$lstElements=new ArrayList<NamedElement>();
}
	:	idToken=ID { 
if(!defer) {
    NamedElement objElement = interp.get($idToken.text);
    if(null==objElement) {
        System.err.println("line "+$idToken.line+":"+$idToken.pos+
            " => I don't know anything about "+$idToken.text+"!");
        this.cleanUp(1);
    }
    $lstElements.add(objElement);
}	
	} 	(',' lstToken=listOfIDs[defer])? {
if(!defer){
    if(null!=lstToken) {
        $lstElements.addAll($lstToken.lstElements);
    }
}
        }
	;

/*** DEVICE ***/	
deviceDeclaration[boolean defer] 
	returns [Device objDevice]
@init {
$objDevice = (Device)null;
}
	:	'Device' nameToken=ID 
		('(' (compToken=deviceComponents[defer]  {
if(!defer) {
    // here we need to delete the first element of the directions array
//    char[] dirs = ArrayUtils.remove($compToken.directions, 0);
    
    $objDevice = interp.createDevice(
        $nameToken.text, 
        $compToken.lstElements, 
        $compToken.directions);
}	
	})? ')'
    	  | 	assignToken=assignment[defer] {
if(!defer) {
    $objDevice = interp.createDevice($nameToken.text, (List<NamedElement>)null, (char[])null); 
    interp.assign($nameToken.text, $assignToken.objElement);
}    	
    	} )? {
if(!defer && null == $objDevice) {
    $objDevice = interp.createDevice($nameToken.text, (List<NamedElement>)null, (char[])null);
}     	
    	}
	;
	catch[EugeneException e] {
System.err.println("line "+$nameToken.line+":"+$nameToken.pos+
         " => "+e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}


deviceComponents[boolean defer] 
	returns [ArrayList<NamedElement> lstElements, char[\] directions]
@init {
$lstElements = new ArrayList<NamedElement>();
NamedElement objElement = null;
$directions = new char[1];
}	
@after {
$directions = ArrayUtils.remove($directions, 0);
}
	:	(directionToken=('-'|'+'))?	
		(objToken=expressionValue[defer] {
if(!defer) {	
    objElement = $objToken.objElement;
}
	}	
	|	partTypeToken=partTypeDeclaration[defer] {
if(!defer) {
    objElement = $partTypeToken.objPartType;
}    
	}
	|	instToken=instantiationStatement[defer] {
if(!defer) {
    objElement = $instToken.objComponent;
}    
    	}
	|	propertyToken=propertyDeclaration[defer] {
if(!defer) {
    objElement = $propertyToken.objProperty;
}    
    	}
	|	deviceToken=deviceDeclaration[defer]  {
if(!defer) {
    objElement = $deviceToken.objDevice;
}    
	}) {
if(!defer) {
    if(objElement instanceof Component) {    
        if(directionToken != null) {
            if("-".equals($directionToken.text)) {
                $directions = ArrayUtils.add($directions, '-');
            } else {
                $directions = ArrayUtils.add($directions, '+');
            }
        } else {
            $directions = ArrayUtils.add($directions, '+');
        }
    }
    $lstElements.add(objElement);
}	
	}	(',' compToken=deviceComponents[defer] {
if(!defer) {
    $lstElements.addAll($compToken.lstElements);
    $directions = ArrayUtils.addAll($directions, $compToken.directions);
}	
	})?
	;
	
deviceTypeDeclaration[boolean defer]
    returns [DeviceType objDeviceType]
	:	'DeviceType' idToken=ID '(' lstToken=listOfIDs[defer] ')' {
if(!defer) {
    interp.createDeviceType(
            $idToken.text, 
            $lstToken.lstElements,
            (char[])null);
}	
	}
	;
	catch[EugeneException e] {
System.err.println($idToken.line+":"+$idToken.pos+" => "+
    e.getMessage());
e.printStackTrace();
	}

/*** ARRAY DECLARATAION ***/
arrayDeclaration[boolean defer] 
	returns [NamedElement objArray]
@init {
$objArray = null;
}
	:	typeToken=arrayType nameToken=ID (assignToken=assignment[defer])? {
if(!defer) {
    if(null!=assignToken) {
        interp.assign($nameToken.text, $assignToken.objElement);
    } else {
        interp.createArray($typeToken.text, $nameToken.text);
    }
}
	}
	;
	catch[EugeneException e] {
System.err.println("line "+$nameToken.line+":"+$nameToken.pos+" => "+
    e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}
	

arrayType
	:	'Device' '[' ']'
	|	'PartType' '[' ']'
	|	'Part' '[' ']'
	|	'Property' '[' ']'
	|	'Rule' '[' ']'
	|	ID '[' ']'
	;
	

/*** RULE ***/
ruleDeclaration[boolean defer]
	:	'Rule' ruleNameToken=ID '(' 
				(deviceToken=onDevice[defer])?
				exprToken=expression[true] {
if(!defer) {    
    interp.createRule($ruleNameToken.text, 
            $deviceToken.device, 
            exprToken.start, 
            (CommonTree)$exprToken.tree);    
}
        } ')'
        ;
        catch[EugeneException e] {
System.err.println("line "+$ruleNameToken.line+":"+$ruleNameToken.pos+" => "+
        e.getMessage());
e.printStackTrace();
this.cleanUp(1);
        }

onDevice[boolean defer] 
	returns [Device device]
	:	'ON' deviceToken=ID ':' {
if(!defer) {
    NamedElement element = SymbolTables.get($deviceToken.text);
    if(null == element) {
        throw new EugeneException("I don't know "+$deviceToken.text);
    }
    
    if(!(element instanceof Device)) {
        throw new EugeneException($deviceToken.text+" is not a Device!");
    }
    
    $device = (Device)element;
}	
	}
	;        
        catch[EugeneException e] {
System.err.println("line "+$deviceToken.line+":"+$deviceToken.pos+" => "+
        e.getMessage());
e.printStackTrace();
this.cleanUp(1);
        }


/***        
onDeviceRule[boolean defer] 
	returns [Rule objRule]
	throws EugeneException
	:	(deviceToken=onDevice[defer])? exprToken=expression[true] {
if(!defer) {
    $objRule = interp.createRule(
            null, 
            $deviceToken.device, 
            exprToken.start, 
            (CommonTree)$exprToken.tree);    
}	
	}
	;
***/	

imageDeclaration[boolean defer]
	:	'Image' '(' imageNameToken=ID ',' imagePathToken=STRING ')' {
if(!defer) {
    System.out.println("TODO: IMAGE!");
    System.out.flush();
}	
	}
	;

/*** RELATIONS ***/
relationDeclaration[boolean defer]
	:	lhsToken=ID relationToken=relationalType rhsToken=ID {
if(!defer) {
    interp.createRelation($lhsToken.text, $relationToken.text, $rhsToken.text);
}	
	}
	;
	catch[EugeneException e] {
System.err.println("line "+$lhsToken.line+":"+$lhsToken.pos+" => "+e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	
	}
	
relationalType 
	:	'REPRESSES'
	|	'INDUCES'
	|	'DRIVES'
	|	'BINDS'
	|	'ORTHO'
	|	'MATCHES'
	;	

/*** ASSERT ***/
/*
Whenever a device is declared that violates an assertion, an exception is
thrown and compilation stops.
*/
assertStatement[boolean defer]
	:	assertToken='Assert' '(' ('ON' deviceToken=expression[defer] ':')? lstRules=listOfIDs[defer]? ')' {
if(!defer) {
    interp.evaluateRules(
        $deviceToken.objElement, 
        $lstRules.lstElements, 
        true);
}	
        }
	;
	catch[EugeneException e] {
System.err.println("line "+$assertToken.line+":"+$assertToken.pos+" => "+
    e.getMessage());
this.cleanUp(1);	
	}
	
				
/*** NOTE ***/	
/* 
Whenever a device is declared that violates a note, a warning is printed
and compilation continues.
*/
noteStatement[boolean defer]
	:	noteToken='Note' '('
			(deviceToken=onDevice[defer])?
			 exprToken=expression[true] {
if(!defer) {    
    Rule rule = interp.createRule("NOTE-RULE", 
            $deviceToken.device, 
            exprToken.start, 
            (CommonTree)$exprToken.tree);    
    if(!RuleEngine.evaluateIfRule(rule)) {
        System.err.println("RULE VIOLATION! "+rule);
    }
}
        } ')'
	;
	catch[EugeneException e] {
System.err.println("line "+$noteToken.line+":"+$noteToken.pos+" => "+
    e.getMessage());
this.cleanUp(1);	
	}

/**
relationalOperatorToken=relationalOperator {
$sOperator = $relationalOperatorToken.sOperator;
        }
**/

	
//*******************	
//*** DEFINITIONS ***
//*******************

instantiationStatement[boolean defer] returns [Component objComponent]
@init{
NamedElement objElement = null;
}
	:	typeToken=ID {
objElement = interp.get($typeToken.text);
if(!defer) {
    if(null==objElement) {
        System.err.println("line "+$typeToken.line+":"+$typeToken.pos+
            " => I don't know anything about "+$typeToken.text+"!");
        this.cleanUp(1);
    }                  
    if(!(objElement instanceof Device) && !(objElement instanceof PartType)) {
        System.err.println("line "+$typeToken.line+":"+$typeToken.pos+
            " => The "+$typeToken.text+" element is neither a Device nor a Part Type!");
        this.cleanUp(1);
    }                  
    if(objElement instanceof Device && !((Device)objElement).isAbstract()) {
        System.err.println("line "+$typeToken.line+":"+$typeToken.pos+
            " => The "+$typeToken.text+" Device is not an abstract Device!");
        this.cleanUp(1);
    }
}	
	} 
	instanceToken=instanceDefinitionStatement[defer, objElement] {
if(!defer) {
    $objComponent = $instanceToken.objComponent;
}	
	}
	;
	
instanceDefinitionStatement[boolean defer, NamedElement objElement] 
	returns [Component objComponent]
	:	partToken=partInstantiation[defer, objElement] {
if(!defer) {
    $objComponent = $partToken.objPart;
}	
	}
        |	deviceToken=deviceInstantiation[defer, objElement] {
if(!defer) {
    $objComponent = $deviceToken.objDeviceInstance;
}	
	}
	;	

dynamicNaming[boolean defer] 
	returns [String sName]
	:	nameToken=ID {
if(!defer) {
    $sName = $nameToken.text;
}	
	}
	|	DYNAMIC_NAME exprToken=expression[defer] DYNAMIC_NAME  {
if(!defer) {
    NamedElement objElement = $exprToken.objElement;

    if(null != objElement && 
        objElement instanceof Variable && 
        EugeneConstants.TXT.equals(((Variable)objElement).getType())) {
        
        $sName = ((Variable)objElement).getValue();
        
    } else {
        System.err.println("line "+exprToken.start.getLine()+":"+exprToken.start.getCharPositionInLine()+" => "+
            "Invalid Name!");
        this.cleanUp(1);
    }
}	
	}
	;	
	
partInstantiation[boolean defer, NamedElement objElement] 
	returns [Part objPart]
	:	{objElement instanceof PartType}? 
		nameToken=dynamicNaming[defer] (('(' (dotToken=listOfDotValues[defer] | valueToken=listOfValues[defer])? ')')? 
				|assignToken=assignment[defer]) {
if(!defer) {
    if(null!=dotToken) {
        interp.createPart(
            (PartType)objElement, 
            $nameToken.sName, 
            $dotToken.lstValues);
    } else if(null!=valueToken) {
        interp.createPart(
            (PartType)objElement, 
            $nameToken.sName, 
            $valueToken.lstValues);
    } else {
        interp.createPart(
            (PartType)objElement,
            $nameToken.sName,
            null);
    }
    
    if(null!=assignToken) {
        interp.assign(
            $nameToken.sName, 
            $assignToken.objElement);
    }
}	        
        }
	;
	catch[EugeneException exc] {
System.err.println("line "+nameToken.start.getLine()+":"+nameToken.start.getCharPositionInLine()+" => "+
    "Invalid Part declaration!");
exc.printStackTrace();    
this.cleanUp(1);
	}
	
/** {!defer && isDevice(objElement.getName())}?  **/	
deviceInstantiation[boolean defer, NamedElement objElement] returns [DeviceInstance objDeviceInstance] 
	:	instToken=ID ('(' (lstToken=listOfIDs[defer])? ')')? {
if(!defer) {	    
    
    if(null!=interp.get($instToken.text)) {
        System.err.println("line "+$instToken.line+":"+$instToken.pos+" => "+
            $instToken.text+" is declared already!");
        this.cleanUp(1);
    }
    if(!((Device)objElement).isAbstract()) {
        System.err.println("line "+$instToken.line+":"+$instToken.pos+" => "+
            "The "+objElement.getName()+" device is not abstract and hence cannot be instantiated!");
        this.cleanUp(1);
    }    
    Device objAbstractDevice = (Device)objElement;
    
    ArrayList<Component> lstComponents = null;
    if(lstToken != null) {
        // now, check if the list of IDs consists of just parts and/or devices that contain just parts
        List<NamedElement> lstElements = $lstToken.lstElements;
    
        if(objAbstractDevice.getComponents()==null || lstElements.size()>objAbstractDevice.getComponents().size()) {
            System.err.println("line "+$instToken.line+":"+$instToken.pos+" => "+
                "The number of the "+$instToken.text+" device's elements is greater than the number of the "+
                objAbstractDevice.getName()+" abstract device's components!");
            this.cleanUp(1);
        }
    
        lstComponents = new ArrayList<Component>();
        for(int i=0; i<lstElements.size(); i++) {
            NamedElement objAssignElement = lstElements.get(i);
            if(!(objAssignElement instanceof Part)) {
                System.err.println("line "+$instToken.line+":"+$instToken.pos+" => "+
                    "The "+objAssignElement.getName()+" component is not a part, and hence, the "+$instToken.text+
                    " device cannot instantiate the abstract "+objAbstractDevice.getName()+" device!");
                this.cleanUp(1);
            } 
        
            // now, let's check if the part is an instance of the abstract device's part type
            Part objPart = (Part)objAssignElement;
        
            NamedElement objAbstractDeviceElement = objAbstractDevice.get(i);
            if(objAbstractDeviceElement instanceof PartType && 
              !objPart.getPartType().getName().equals(objAbstractDeviceElement.getName())) {
                System.err.println("line "+$instToken.line+":"+$instToken.pos+" => "+
                    "The "+objPart.getName()+" part at index "+i+" is not an instance of the "+objAbstractDeviceElement.getName()+" part type!");
                this.cleanUp(1);
            }
        
            lstComponents.add(objPart);
        }
    }
    
    $objDeviceInstance = DeviceInstance.newInstance($instToken.text, objAbstractDevice);
    $objDeviceInstance.setComponents(lstComponents);
}	
	}
	;
	catch[EugeneException e] {
System.err.println("line "+$instToken.line+":"+$instToken.pos+" => "+
        e.getMessage());
e.printStackTrace();
this.cleanUp(1);    
	}
// e.g. 
// PartType Promoter(Sequence,Orientation);
// Promoter pLac(.Sequence(".."),.Orientation(".."));
// this rule only applies for PartType instances!
listOfDotValues [boolean defer] 
	returns [List<PropertyValue> lstValues]
@init {
$lstValues = new ArrayList<PropertyValue>();
}	
	:	'.' nameToken=ID '(' valueToken=expression[defer] ')' {
if(!defer) {
    $lstValues.add(
        interp.createPropertyValue(
                $nameToken.text, $valueToken.objElement));
}
        } 	(',' dotValuesToken=listOfDotValues[defer] {
if(!defer) {
    $lstValues.addAll($dotValuesToken.lstValues);
}        
        })?
	;
	catch[EugeneException e] {
System.err.println("line "+$nameToken.line+":"+$nameToken.pos+" => "+
     e.getMessage());
e.printStackTrace();
this.cleanUp(1);     
	}

// e.g. 
// PartType Promoter(ID,Sequence,Orientation);
// Promoter pLac("pLac","ATC...TCG","FORWARD");
listOfValues [boolean defer] 
	returns [List<NamedElement> lstValues]
@init {
$lstValues = new ArrayList<NamedElement>();
}	
	:	exprToken=expression[defer] {
if(!defer) {
    $lstValues.add($exprToken.objElement);
}
        } (',' lstToken=listOfValues[defer] {
if(!defer) {
    $lstValues.addAll($lstToken.lstValues);
}        
        })?
	;


//***
//*** EXPRESSIONS 	
//***
listOfExpressions[boolean defer] returns [ArrayList<NamedElement> lstElements]
@init {
$lstElements = new ArrayList<NamedElement>();
}
	:	exprToken=expression[defer] {
if(!defer) {
    $lstElements.add($exprToken.objElement);
}	
	} 	(',' lstToken=listOfExpressions[defer])? {
if(!defer) {
    if(lstToken!=null) {
        $lstElements.addAll($lstToken.lstElements);
    }
}		
}	
	;
	
expression[boolean defer] 
	returns [NamedElement objElement]
    	:   	notToken=notExpression[defer] {
if(!defer) {
    $objElement=$notToken.objElement;    
}    
        }
    	;

notExpression[boolean defer] 
	returns [NamedElement objElement]    	
@init {
boolean bNOT=false;
}
	:	notToken='NOT' exprToken=orExpression[defer] {
if(!defer) {
    $objElement = interp.not($exprToken.objElement);
}
	}	-> ^('NOT' $exprToken)
        |	exprToken=orExpression[defer] {
if(!defer) {
    $objElement = $exprToken.objElement;
}        
        }	-> $exprToken
	;

orExpression[boolean defer] returns [NamedElement objElement] 
	:	(leftToken=andExpression[defer] -> $leftToken) 
			((('OR'|'||') rightToken=notExpression[defer]) 
			-> ^('OR' $leftToken $rightToken))? {
if(!defer) {
    if(null==rightToken) {
        $objElement=$leftToken.objElement;
    } else {
        Variable objVariable = new Variable("OR",EugeneConstants.BOOLEAN);
        objVariable.setBoolean(
            interp.or($leftToken.objElement,$rightToken.objElement));
        $objElement = objVariable;
    }
}
	} 
	;
catch [Exception e] {
    e.printStackTrace();
    this.cleanUp(1);
}

andExpression[boolean defer] returns [NamedElement objElement] 
	:	(leftToken=xorExpression[defer] -> $leftToken) 
			((('AND'|'&&') rightToken=notExpression[defer]) 
			-> ^('AND' $leftToken $rightToken))? {
if(!defer) {
    if(null==rightToken) {
        $objElement=$leftToken.objElement;
    } else {
        Variable objVariable = new Variable("AND",EugeneConstants.BOOLEAN);
        objVariable.setBoolean(
            interp.and($leftToken.objElement,$rightToken.objElement));
        $objElement = objVariable;
    }
}
	} 
	;
catch [Exception e] {
    e.printStackTrace();
    this.cleanUp(1);
}
	
xorExpression[boolean defer] returns [NamedElement objElement] 
	:	(leftToken=comparativeExpression[defer] -> $leftToken) 
			((('XOR'|'^^') rightToken=notExpression[defer]) 
			-> ^('XOR' $leftToken $rightToken))? {
if(!defer) {
    if(null==rightToken) {
        $objElement=$leftToken.objElement;
    } else {
        Variable objVariable = new Variable("XOR",EugeneConstants.BOOLEAN);
        objVariable.setBoolean(
            interp.xor($leftToken.objElement,$rightToken.objElement));
        $objElement = objVariable;
    }
}
	} 
	;
catch [Exception e] {
    e.printStackTrace();
    this.cleanUp(1);
}

operator returns [String sOperator]
	:	relToken=relationalOperator  {
$sOperator = $relToken.sOperator;
	} 
	|	(ruleToken=ruleOperator {
$sOperator = $ruleToken.sOperator.toString();
	} -> $ruleToken)
	|	pairToken=relationalType {
$sOperator = $pairToken.text;	
	}
	;
	
			
ruleOperator returns [RuleOperator sOperator]
	:
/*
 *   BEFORE
 */
		('SOME_BEFORE' {
$sOperator = RuleOperator.SOME_BEFORE;	
	})	
	|	('BEFORE'|'ALL_BEFORE') {
$sOperator = RuleOperator.ALL_BEFORE;	
	}	
/*
 *   AFTER
 */
	|	('SOME_AFTER' {
$sOperator = RuleOperator.SOME_AFTER;
	})
	|	('AFTER' | 'ALL_AFTER') {
$sOperator = RuleOperator.ALL_AFTER;
	}
/*
 *   NEXTTO
 */
	|	('SOME_NEXTTO') {
$sOperator = RuleOperator.SOME_NEXTTO;
	}
	|	('NEXTTO' | 'ALL_NEXTTO') {
$sOperator = RuleOperator.ALL_NEXTTO;
	}
	|	('LEFTTO' | 'ALL_LEFTTO') {
$sOperator = RuleOperator.ALL_LEFTTO;
	}
	|	('SOME_LEFTTO') {
$sOperator = RuleOperator.SOME_LEFTTO;
	}
	|	('RIGHTTO' | 'ALL_RIGHTTO') {
$sOperator = RuleOperator.ALL_RIGHTTO;
	}
	|	('SOME_RIGHTTO') {
$sOperator = RuleOperator.SOME_RIGHTTO;
	}
	|	'STARTSWITH' {
$sOperator = RuleOperator.STARTSWITH;
	}
	|	('ENDSWITH' {
$sOperator = RuleOperator.ENDSWITH;
	})
	|	'WITH' {
$sOperator = RuleOperator.WITH;
	}
	|	'NOTWITH' {
$sOperator = RuleOperator.NOTWITH;
	}
	|	'THEN' {
$sOperator = RuleOperator.THEN;
	}	
	|	'NOTTHEN' {
$sOperator = RuleOperator.NOTTHEN;
	}	
	|	'CONTAINS' {
$sOperator = RuleOperator.CONTAINS;
	}
	|	'NOTCONTAINS' {
$sOperator = RuleOperator.NOTCONTAINS;
	}
	|	'MORETHAN' {
$sOperator = RuleOperator.MORETHAN;
	}
	|	'NOTMORETHAN' {
$sOperator = RuleOperator.NOTMORETHAN;
	}
	|	'EXACTLY' {
$sOperator = RuleOperator.EXACTLY;
	}	
	|	'NOTEXACTLY' {
$sOperator = RuleOperator.NOTEXACTLY;
	}	
	;

relationalOperator returns [String sOperator]
	:	('==' | 'EQUALS') {
$sOperator=EugeneConstants.EQUALS;	
	}
	|	('!=' | 'NOTEQUALS') {
$sOperator=EugeneConstants.NOTEQUALS;	
	}
	|	'<'  {
$sOperator=EugeneConstants.LT;	
	}
	|	'<=' {
$sOperator=EugeneConstants.LEQ;	
	}
	|	'>'  {
$sOperator=EugeneConstants.GT;	
	}
	|	'>=' {
$sOperator=EugeneConstants.GEQ;	
	}
	;

comparativeExpression[boolean defer] 
	returns [NamedElement objElement]
	:  
	( 
	(
		exprToken1=addExpression[defer]  {
if(!defer) {
    $objElement=$exprToken1.objElement;
}	
}	-> $exprToken1	
	)
	(	oToken=operator exprToken2=comparativeExpression[defer] {
if(!defer) {
    $objElement = interp.compare($exprToken1.objElement, $oToken.text, $exprToken2.objElement);
}
}	-> ^($oToken $exprToken1 $exprToken2)
		|	('INSTANCEOF'|'instanceof') typeToken=type {
if(!defer) {
    $objElement = EugeneBuilder.buildVariable(
        String.valueOf(interp.isInstanceOf($exprToken1.objElement, $typeToken.text)));
}	
}	-> ^('INSTANCEOF' $typeToken)
		
	)?	|	opToken=operator exprToken=addExpression[defer]		// for unary rules (e.g. CONTAINS p)
				-> ^($opToken $exprToken)	
	)		
	;
catch[EugeneException e] {
    System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+" => "+
        e.getMessage());
    e.printStackTrace();
    this.cleanUp(1);
}

addExpression[boolean defer] 
	returns [NamedElement objElement]
	:	(leftToken=subtractExpression[defer]  -> $leftToken)
			(opToken='+' rightToken=addExpression[defer]
				-> ^('+' $leftToken $rightToken))? {
if(!defer) {
    if(null==rightToken) {
        $objElement=$leftToken.objElement;
    } else {
        $objElement = interp.add($leftToken.objElement, $rightToken.objElement);
    }
}
	}
	;
	catch[EugeneException e] {
System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+
            " => "+e.toString());
e.printStackTrace();
this.cleanUp(1);            
	}

subtractExpression[boolean defer] 
	returns [NamedElement objElement]
	:	(leftToken=multiplicativeExpression[defer] -> $leftToken)
			(opToken='-' rightToken=subtractExpression[defer]
			-> ^('-' $leftToken $rightToken))? {
if(!defer) {
    if(null==rightToken) {
        $objElement=$leftToken.objElement;
    } else {
        $objElement=interp.subtract($leftToken.objElement,$rightToken.objElement);

        if($objElement==null) {
            System.err.println("line "+$opToken.line+":"+$opToken.pos+" => "+
                "I cannot apply the - operator!");
            this.cleanUp(1);
        }
    }
}
	} 
	;
	

multiplicativeExpression[boolean defer] 
	returns [NamedElement objElement]
    	:   	(leftToken=expressionValue[defer] {
if(!defer) {
    $objElement = $leftToken.objElement;
}    	
    	}	-> $leftToken)
    	(
    			(opToken='*' rightToken=multiplicativeExpression[defer] {
if(!defer) {
    if(null==rightToken) {
        $objElement=$leftToken.objElement;
    } else {
        $objElement=interp.multiply($leftToken.objElement,$rightToken.objElement);	
    }
}
	}	-> ^('*' $leftToken $rightToken))
	|		(opToken='/' rightToken=multiplicativeExpression[defer] {
if(!defer) {
    if(null==rightToken) {
        $objElement=$leftToken.objElement;
    } else {
        $objElement=interp.divide($leftToken.objElement,$rightToken.objElement);	
    }
}
	}	-> ^('/' $leftToken $rightToken))	
	)? 
	;

// if the given sPropertyType==null, then the PropertyValue/property on the left side 
// gets the type of the right side of the assignment
// e.g. someDNA = "ATACTAT"  <-- the PropertyValue/property someDNA gets the type txt
expressionValue[boolean defer] returns [NamedElement objElement]
	:	(minToken='-')? numToken=(INT|FLOAT) {
if(!defer) {
    Variable objValue=new Variable(EugeneConstants.NUM, EugeneConstants.NUM);
    objValue.setNum(new Double($numToken.text).doubleValue());
    if(minToken!=null) {
        objValue.setNum(objValue.getNum() * (-1));
    }
    $objElement=objValue;
}
        }	
	|	txtToken=STRING {
if(!defer) {
    Variable objValue=new Variable(EugeneConstants.TXT,EugeneConstants.TXT);
    objValue.setTxt($txtToken.text);
    $objElement=objValue;
}
        }	
	|	boolToken=('true'|'false') {
if(!defer) {
    Variable objValue=new Variable(EugeneConstants.BOOLEAN,EugeneConstants.BOOLEAN);
    objValue.setBoolean(new Boolean($boolToken.text).booleanValue());
    $objElement=objValue;
}
        }	
        |	(accessToken = objectAccessStatement[defer] {
if(!defer) {
    $objElement = $accessToken.objElement;
}        
	}	-> $accessToken)
	|	'(' exprToken=expression[defer] ')'  {
if(!defer){
    $objElement = $exprToken.objElement;
}
        }     -> ^('(' $exprToken)   
	|	'[' lstToken=listOfExpressions[defer] ']' {
if(!defer) {
    ArrayList<NamedElement> lstElements = $lstToken.lstElements;
    NamedElement objFirstElement = null;
    if(null != lstElements && !lstElements.isEmpty()) {
        // get the type of the first element
        objFirstElement = lstElements.get(0);
        
        if(objFirstElement instanceof Variable) {
            Variable objVariable = (Variable)objFirstElement;
            String sType = objVariable.getType();
            ArrayList<Double> lstNumList=null;
            ArrayList<String> lstTxtList=null;
            
            if(EugeneConstants.NUM.equals(sType)) {
                lstNumList = new ArrayList<Double>();
                lstNumList.add(objVariable.getNum());
            } else if(EugeneConstants.TXT.equals(sType)) {
                lstTxtList = new ArrayList<String>();
                lstTxtList.add(objVariable.getTxt());
            }
            
            for(int i=1;i<lstElements.size();i++) {
                NamedElement objElement = lstElements.get(i);
                if(objElement!=null && objElement instanceof Variable) {
                    objVariable = (Variable)objElement;
                    if(sType.equals(objVariable.getType())) {
                        if(EugeneConstants.NUM.equals(sType)) {
                            lstNumList.add(objVariable.getNum());
                        } else if(EugeneConstants.TXT.equals(sType)) {
                            lstTxtList.add(objVariable.getTxt());
                        }
                    } else {
                        System.err.println("The "+i+"-th element is not of type "+sType);
                        this.cleanUp(1);
                    }
                }     
            }   

            if(EugeneConstants.NUM.equals(sType)) {
                objVariable = new Variable(EugeneConstants.VARIABLE,EugeneConstants.NUMLIST);
                objVariable.setNumList(lstNumList);
            } else if(EugeneConstants.TXT.equals(sType)) {
                objVariable = new Variable(EugeneConstants.VARIABLE,EugeneConstants.TXTLIST);
                objVariable.setTxtList(lstTxtList);
            }
            $objElement = objVariable;
        } else if(objFirstElement instanceof Device) {
            DeviceArray objDeviceArray = new DeviceArray(EugeneConstants.DEVICEARRAY);
            Device objDevice = (Device)objFirstElement;
            objDeviceArray.add(objDevice);
            for(int i=1;i<lstElements.size();i++) {
                NamedElement objElement = lstElements.get(i);
                if(objElement!=null && objElement instanceof Device) {
                    objDeviceArray.add((Device)objElement);
                } else {
                    System.err.println("The "+i+"-th element is not a Device!");
                    this.cleanUp(1);
                }
            }   
            $objElement = objDeviceArray;
        } else if(objFirstElement instanceof PartType) {
            PartTypeArray objPartTypeArray = new PartTypeArray(EugeneConstants.PARTTYPEARRAY);
            PartType objPartType = (PartType)objFirstElement;
            objPartTypeArray.add(objPartType);
            for(int i=1;i<lstElements.size();i++) {
                NamedElement objElement = lstElements.get(i);
                if(objElement!=null && objElement instanceof PartType) {
                    objPartTypeArray.add((PartType)objElement);
                } else {
                    System.err.println("The "+i+"-th element is not a Part Type!");
                    this.cleanUp(1);
                }
            }   
            $objElement = objPartTypeArray;
        } else if(objFirstElement instanceof Part) {
            PartArray objPartArray = new PartArray(EugeneConstants.PARTARRAY);
            Part objPart = (Part)objFirstElement;
            objPartArray.add(objPart);
            for(int i=1;i<lstElements.size();i++) {
                NamedElement objElement = lstElements.get(i);
                if(objElement!=null && objElement instanceof Part) {
                    objPartArray.add((Part)objElement);
                } else {
                    System.err.println("The "+i+"-th element is not a Part!");
                    this.cleanUp(1);
                }
            }   
            $objElement = objPartArray;
        } else if(objFirstElement instanceof Property) {
            PropertyArray objPropertyArray = new PropertyArray(EugeneConstants.PROPERTYARRAY);
            Property objProperty = (Property)objFirstElement;
            objPropertyArray.add(objProperty);
            for(int i=1;i<lstElements.size();i++) {
                NamedElement objElement = lstElements.get(i);
                if(objElement!=null && objElement instanceof Property) {
                    objPropertyArray.add((Property)objElement);
                } else {
                    System.err.println("The "+i+"-th element is not a Property!");
                    this.cleanUp(1);
                }
            }   
            $objElement = objPropertyArray;
        } else if(objFirstElement instanceof Rule) {
            RuleArray objRuleArray = new RuleArray(EugeneConstants.RULEARRAY);
            Iterator<NamedElement> it = lstElements.iterator();
            while(it.hasNext()) {
                NamedElement objElement = it.next();
                if(objElement instanceof Rule) {
                    objRuleArray.add((Rule)objElement);
                } else {
                    System.err.println("line "+$lstToken.start.getLine()+":"+$lstToken.start.getCharPositionInLine()+" => "+
                        "The "+objElement.getName()+" element is not a Rule!");
                    this.cleanUp(1);
                }
            }
            $objElement = objRuleArray;
        }
    }
} 
        }	-> ^('[' $lstToken)
	;
	catch[EugeneException e] {
System.err.println(retval.start.getLine()+":"+retval.start.getCharPositionInLine()+
    " => "+e.toString());
e.printStackTrace();
this.cleanUp(1);	
	}

objectAccessStatement[boolean defer] 
	returns [NamedElement objElement]
	:	(idToken=ID  {
if(!defer) {
    $objElement = interp.get($idToken.text);
    
    if($objElement==null) {
        throw new EugeneException( "I don't know anything about "+$idToken.text+"!");
    } 
}
	}	-> $idToken)
		(accessToken=objectAccess[defer, $objElement] {
if(!defer) {
    $objElement = $accessToken.objReturnElement;
}	
	}	-> ^($idToken $accessToken))?
	;
	catch[EugeneException e] {
System.err.println("line"+$idToken.line+":"+$idToken.pos+" => "+e.toString());
e.printStackTrace();
this.cleanUp(1);	
	}

objectAccess[boolean defer, NamedElement objElement]
	returns [NamedElement objReturnElement]
	:	(arrayToken=arrayAccess[defer, $objElement] {
if(!defer) {
    $objElement = $arrayToken.objReturnElement;
}	
	}	-> $arrayToken) 
			(accessToken=access[defer, $objElement] {
if(!defer) {
    $objElement = $accessToken.objReturnElement;
}				
	} 	-> ^($objectAccess $arrayToken $accessToken))?  {
if(!defer) {
    $objReturnElement = $objElement;
}	
	}
	|	(dotToken=dotAccess[defer, $objElement] {
if(!defer) {
    $objElement = $dotToken.objReturnElement;
}	
	}		-> $dotToken) 	
			(accessToken=access[defer, $objElement] {
if(!defer) {
    $objElement = $accessToken.objReturnElement;
}				
	}	-> ^($objectAccess $dotToken $accessToken))? {
if(!defer) {
    $objReturnElement = $objElement;
}	
	}
	;	
	
access[boolean defer, NamedElement objElement]
	returns [NamedElement objReturnElement]
	:	objectToken=objectAccess[defer, $objElement] {
if(!defer) {
    $objReturnElement = $objectToken.objReturnElement;
}	
	}
	;	
	
arrayAccess[boolean defer, NamedElement objElement] 
	returns [NamedElement objReturnElement]
	:	('[' idxToken=expression[defer] ']' {
if(!defer) {
   
    int nLine = idxToken.start.getLine();
    int nPos = idxToken.start.getCharPositionInLine();
    
    int idx = -1;
    NamedElement objIdxElement = $idxToken.objElement;
    
    if(objIdxElement!=null && objIdxElement instanceof Variable) {
        Variable objIdxVariable = (Variable)objIdxElement;
        if(EugeneConstants.NUM.equals(objIdxVariable.getType())) {
            if(objIdxVariable.getNum() != (int)objIdxVariable.getNum()) {
                System.err.println("line "+nLine+":"+nPos+" => "+
                    "The value of "+$idxToken.text+
                    " ("+objIdxVariable.getNum()+") is an invalid index!");
                this.cleanUp(1);
            } else {
                idx = (int)objIdxVariable.getNum();
            }
        } else {
            System.err.println("line "+nLine+":"+nPos+" => "+
                $idxToken.text+" has an invalid type for an index!");
            this.cleanUp(1);
        }
    } else {
        System.err.println("Invalid array index!");
        this.cleanUp(1);
    }
    
    $objReturnElement = interp.get(objElement, idx);    
}	
	}	-> ^('[' $idxToken))	
	;
	catch[EugeneException e] {
System.err.println(
        "line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+
        " => "+e.toString());
e.printStackTrace();
this.cleanUp(1);
	}

dotAccess[boolean defer, NamedElement objElement]
	returns [NamedElement objReturnElement]
	:	( '.' propertyToken=ID {
if(!defer) {
    $objReturnElement = interp.get(objElement, $propertyToken.text);
}	
	}	-> ^('.' $propertyToken))	
	|	(('.' 'size' ('('')')?) {
if(!defer) {    
    Variable objVariable = 
        interp.createVariable((String)null, EugeneConstants.NUM);
    objVariable.setNum(interp.sizeOf(objElement));   
    $objReturnElement = objVariable; 
}	
	}	-> ^('.' 'size'))
	|	('.' getToken='get' '(' exprToken=expression[defer] ')' {
if(!defer) {
    $objReturnElement = interp.get(objElement, $exprToken.objElement);
}	
	}	-> ^('.' 'get' $exprToken))
	|	('.' seqToken='toSequence' ('(' ')')? {
if(!defer) {
    Variable objVariable = new Variable("SEQUENCE",EugeneConstants.TXT);
    objVariable.setTxt(interp.toSequence(objElement));
    $objReturnElement = objVariable;
}
	}	-> ^('.' 'toSequence'))
	|	('.' emptyToken='isEmpty' ('(' ')')? {
if(!defer) {
    Variable objVariable = new Variable(objElement.getName()+"_empty",EugeneConstants.BOOLEAN);
    if(objElement instanceof ComponentArray) {
        objVariable.setBoolean(((ComponentArray)objElement).size()==0);
    } else if (objElement instanceof Component) {
        objVariable.setBoolean(((Component)objElement).size()==0);
    }
    $objReturnElement = objVariable;
}	
	}	-> ^('.' 'isEmpty'))
	|	('.' 'instantiate' '(' (exprToken=expression[defer])? ')' {
if(!defer) {
    if(objElement == null) {
        System.err.println("Invalid usage of the instantiate() function!");
        this.cleanUp(1);
    }

    Rule objRule = null;
    if(null!=exprToken && $exprToken.objElement instanceof Rule) {
        objRule = (Rule)$exprToken.objElement;
        //System.out.println("[instantiate] -> "+objRule.toStringTree());
    }    
}	
	}	-> ^('.' 'instantiate' $exprToken?))
	;
	catch[EugeneException e] {
System.err.println("line "+$propertyToken.line+":"+$propertyToken.pos+
        " => "+e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}
	
/***
functionWrapper[boolean defer, NamedElement objElement] returns [NamedElement objValue]
	:	
	;
	catch[EugeneException e] {
System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+" => "+
    e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}
**/

objectAssignmentStatement[boolean defer] 
	returns [NamedElement assignElement] 
@init {
NamedElement objElement = null;
}
	:	((idToken=ID    	-> $idToken) {
if(!defer) {
   objElement = interp.get($idToken.text); 
}
	}
	(	('.' subIdToken=ID {
if(!defer) {
    NamedElement objTempElement = interp.get(objElement, $subIdToken.text);
    if(objElement == null) {
        System.err.println("line "+$subIdToken.line+":"+$subIdToken.pos+" => "+
            "The "+objElement.getName()+" element does not contain a "+$subIdToken.text+" element!");
        this.cleanUp(1);
    } 
    objElement = objTempElement;
}	
	}	-> ^('.' $objectAssignmentStatement $subIdToken))
	 |	('[' {
if(!defer) {
    if(null==objElement) {
        System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
                    "Invalid array access on "+$idToken.text);
        this.cleanUp(1);
    }
}	
	}	idxToken=expression[defer] ']' {
if(!defer) {
    NamedElement objIdxElement = $idxToken.objElement;
    if(null!=objIdxElement) {
        double idx = -1;
        if(!(objIdxElement instanceof Variable) ||
           !EugeneConstants.NUM.equals(((Variable)objIdxElement).getType())) {
            System.err.println("line "+idxToken.start.getLine()+":"+idxToken.start.getCharPositionInLine()+" => "+
                $idxToken.text+" is an invalid array index!");
            this.cleanUp(1);
        }
            	            
        objElement = objElement.get(
                (int)((Variable)objIdxElement).getNum());
    }    
}	
	}	-> ^('['  $objectAssignmentStatement $idxToken))
	)*	
	)
		rightToken=assignment[defer] {
if(!defer) {

    if("++".equals($rightToken.text) && objElement instanceof Variable) {
        ((Variable)objElement).increase();
    } else if ("--".equals($rightToken.text) && objElement instanceof Variable) {
        ((Variable)objElement).decrease();
    } else if (objElement == null) {
        objElement = interp.clone($rightToken.objElement);
        if(null == objElement) {
            throw new EugeneException($idToken.text+" has not been declared!");
        }
        objElement.setName($idToken.text);
        SymbolTables.put(objElement);
    } else {

        try {
            interp.assign(objElement.getName(), $rightToken.objElement);
            $assignElement = null;
        } catch(EugeneException e) {
            $assignElement = $rightToken.objElement;
        }
    }
}
        }
	;
	catch[EugeneException e] {
System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}


listOfStatements[boolean defer] returns [NamedElement objReturnValue]
	throws EugeneReturnException
	:	(stmtToken=statement[defer] {
if(!defer) {
    //$objReturnValue = $stmtToken.objReturnValue;
    $objReturnValue = (NamedElement)null;
}	
	} )+ 
	;

returnStatement[boolean defer] returns [NamedElement objReturnValue]
@init {
NamedElement objReturn = null;
}
	:	returnToken='return' {
if(!defer) {
/*** TODO:
    StackElement se = SymbolTables.peek();
    if(null == se || !(se instanceof Function)) {
        System.err.println("line "+$returnToken.line+":"+$returnToken.pos+
                " => A 'return' statement is not allowed here!");
        this.cleanUp(1);
    }
    
    NamedElement objElement = interp.get(sFunctionName);
    if(null!=objElement && objElement instanceof Function) {
        Function objFunction = (Function)objElement;
    
        // get the function's return type
        objReturn = objFunction.getReturn();
        if(null==objReturn) {
            System.err.println("line "+$returnToken.line+":"+$returnToken.pos+" => "+
                    "The Function "+objFunction.getName()+" cannot return anything because there's no return type specified!");
            this.cleanUp(1);        
        }
    }
    ***/
}	
	} 
	(	exprToken=expression[defer] {
if(!defer) {
    $objReturnValue = $exprToken.objElement;
}
	}
	|	functionToken=functionCall[defer] {
if(!defer) {
    $objReturnValue = $functionToken.objElement;
}
	}
	|	wrapperToken=wrappedStatement[defer] {
if(!defer) {
    $objReturnValue = $wrapperToken.objElement;
}	
	})? ';'
	;
		
//***************************
//*** SAVE-STATEMENT
//***************************
saveStatement[boolean defer]
	:	'save' '(' listOfSaveObjects[defer] ')'
	;
	
listOfSaveObjects[boolean defer]
	:	(nameToken=ID ':')? idToken=expression[defer] {
if(!defer) {
    String sName = (String)null;
    if(null != nameToken) {
        sName = $nameToken.text;
    }
    interp.save(sName, $idToken.objElement);
}
	} (',' listOfSaveObjects[defer])?
	;
	catch[EugeneException e] {
System.err.println("line "+idToken.start.getLine()+":"+idToken.start.getCharPositionInLine()+" => "+
    e.getMessage());
e.printStackTrace();
this.cleanUp(1);	        
	}
	
		
//***************************
//*** IF-STATEMENT
//***************************
ifStatement[boolean defer]
	throws EugeneReturnException 
@init {
ConditionalBranch objIfBranch = null;
ArrayList<ConditionalBranch> lstElseIfStatements = null;
ConditionalBranch objElseBranch = null;
}	
	:	'if' '(' ifConditionToken=ifCondition[true] ')' '{' thenStmtToken=listOfStatements[true] '}' {
if(!defer) {
   objIfBranch = new ConditionalBranch($ifConditionToken.start, $thenStmtToken.start); 
}
	}
                ('else' 'if' '(' elseIfToken=ifCondition[true] ')' '{' elseIfStmtToken=listOfStatements[true] '}' {
if(!defer) {
    if(lstElseIfStatements == null) {
        lstElseIfStatements = new ArrayList<ConditionalBranch>();
    }
    ConditionalBranch objElseIfBranch = new ConditionalBranch($elseIfToken.start, $elseIfStmtToken.start);
    lstElseIfStatements.add(objElseIfBranch);    
}
        })*
		('else' '{' elseStmtToken=listOfStatements[true] '}' {
if(!defer) {
    objElseBranch = new ConditionalBranch(null, $elseStmtToken.start);
}
        })? {
if(!defer) {
    // new version:
    IfStatement objIf = new IfStatement(
        objIfBranch, 
        lstElseIfStatements, 
        objElseBranch);

    this.executeIf(objIf);
}
        }
	;
	catch[EugeneException e] {
System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+" => "+e.getMessage());
e.printStackTrace();
this.cleanUp(1);	        
	}

 
ifCondition[boolean defer] 
    	returns [boolean b]
	:	(deviceToken=onDevice[defer])? exprToken=expression[defer] {
if(!defer) {
    if(null != deviceToken) {
        Rule rule = interp.createRule("IF-RULE", $deviceToken.device, exprToken.start, (CommonTree)$exprToken.tree);
        if(null != rule) {
            $b = RuleEngine.evaluateIfRule(rule);
        }
    } else if($exprToken.objElement instanceof Rule) {
        if(null != deviceToken) {
            Rule rule = interp.createRule("IF-RULE", $deviceToken.device, exprToken.start, ((CommonTree)exprToken.tree));
            $b = RuleEngine.evaluateIfRule(rule);
        }  else {
            $b = RuleEngine.evaluateIfRule((Rule)$exprToken.objElement);
        }
    } else if($exprToken.objElement instanceof Variable) {
        $b = ((Variable)$exprToken.objElement).getBoolean();
    } else {
        throw new EugeneException("INVALID CONDITION!");
    }
}
	}
	;
	catch[EugeneException e] {
System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+" => "+e.getMessage());
e.printStackTrace();
this.cleanUp(1);	        
	}

/**  
(ruleDefinition[true,null])=> ruleToken=ruleDefinition[defer,EugeneConstants.IFRULE] {
if(!defer) {
    if($ruleToken.objRule!=null) {
        $b = ruleEngine.evaluateRule((Rule)$ruleToken.objRule,false);
    }
}	
	}
**/	

//***************************
//*** LOOPS
//***************************
loopStatement [boolean defer] 
	throws EugeneReturnException 
    	:	'for' '(' initToken=forInit[true] ';' 
    		condToken=expression[true] ';' 
    		incToken=computationalStatement[true] ')' '{' 
    			forToken=listOfStatements[true] '}' {
if(!defer) {
    forStat($initToken.start, 
        $condToken.start, 
        $incToken.start, 
        $forToken.start);
}
        }
	|	'while' '(' condToken=expression[true] ')' '{' whileToken=listOfStatements[true] '}' {
if(!defer){
    whileStat($condToken.start, 
        $whileToken.start);
}
        }
	|	'do' '{' whileToken=listOfStatements[true] '}' 'while' '(' condToken=expression[true] ')'';' {
if(!defer){
   whileStat($condToken.start, 
       $whileToken.start);
}
        }
	;
	catch[EugeneException e] {
System.err.println("[loopStatement] "+e.toString());
e.printStackTrace();
this.cleanUp(1);
	}
	

forInit[boolean defer] returns [ArrayList<NamedElement> lstElements]
	:	declToken=variableDeclaration[defer] {
if(!defer) {
    $lstElements = new ArrayList<NamedElement>($declToken.lstVariables);
}	
	}
	|	exprToken=listOfExpressions[defer] {
if(!defer) {
    $lstElements = $exprToken.lstElements;
}	
        }
	;
    
//*****************
//*** FUNCTIONS ***
//*****************
functionDeclaration returns [Function objFunction]
	:	'function' (returnTypeToken=type)? nameToken=ID {
if(null != interp.get($nameToken.text)) {
    System.err.println("line "+$nameToken.line+":"+$nameToken.text+" => "+
        "The "+$nameToken.text+" function has been declared already!");
    this.cleanUp(1);
}
	} '(' (lstParametersToken=listOfFunctionParamenters)? ')' '{' 
			lstStatementsToken=listOfStatements[true]
		'}' {
$objFunction = new Function($nameToken.text);
if(null != returnTypeToken) {
    NamedElement objElement = this.createElement(
            $returnTypeToken.sType, $nameToken.text+"-RETURN-TYPE");
    $objFunction.setReturn(objElement);
}

$objFunction.setParameters($lstParametersToken.lst);	
$objFunction.setStatements($lstStatementsToken.start);

	}
	;
	catch[EugeneReturnException ere] {
	
	}

type returns [String sType]
	:	'Collection' {
$sType=EugeneConstants.COLLECTION;	
	}
	|	'Device' '[' ']' {
$sType=EugeneConstants.DEVICEARRAY;	
	}
	|	'Device' {
$sType=EugeneConstants.DEVICE;	
	}
	|	'Part' '[' ']' {
$sType=EugeneConstants.PARTTYPEARRAY;	
	}
	|	'Part' {
$sType=EugeneConstants.PARTTYPE;	
	}
	|	idToken=ID '[' ']' {
$sType=EugeneConstants.PARTARRAY;	
	}
	|	idToken=ID {
NamedElement objElement=interp.get($idToken.text);
if(objElement!=null && objElement instanceof Component) {
    $sType=((Component)objElement).getName();
} else {
    System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
        $idToken.text+" is an invalid return type!");
    this.cleanUp(1);
}
	}
	|	'Property' '[' ']' {
$sType=EugeneConstants.PROPERTYARRAY;	
	}
	|	'Property' {
$sType=EugeneConstants.PROPERTY;	
	}
	|	'num' '[' ']' {
$sType=EugeneConstants.NUMLIST;	
	}
	|	'num' {
$sType=EugeneConstants.NUM;	
	}
	|	'txt' '[' ']' {
$sType=EugeneConstants.TXTLIST;	
	}
	|	'txt' {
$sType=EugeneConstants.TXT;	
	}
	|	'boolean' {
$sType=EugeneConstants.BOOLEAN;	
	}
	;

listOfFunctionParamenters returns [ArrayList<NamedElement> lst]
@init {
$lst=new ArrayList<NamedElement>();
}
	:	paramTypeToken=type paramNameToken=ID { 
NamedElement objParameter = this.createElement($paramTypeToken.text,$paramNameToken.text);
if(objParameter!=null) {
    $lst.add(objParameter);
}
	} (',' lstToken=listOfFunctionParamenters {
if(null!=lstToken) {
    $lst.addAll($lstToken.lst);
}
	})? 
	;
	 
functionCall[boolean defer] returns [NamedElement objElement]
	:	functionToken=ID '(' (lstParametersToken=listOfParameterValues[defer])? ')' {
if(!defer) {
    // check if function is declared
    NamedElement objElement = interp.get($functionToken.text);
    if(null==objElement) {
        System.err.println("line "+$functionToken.line+":"+$functionToken.pos+
             " => I don't know anything about the function "+$functionToken.text+"!");
        this.cleanUp(1);
    } else if(!(objElement instanceof Function)) {
        System.err.println("line "+$functionToken.line+":"+$functionToken.pos+
             " => The "+$functionToken.text+" element is not a function!");
        this.cleanUp(1);
    }
    
    $objElement = this.callFunction(
            (Function)objElement,
            $lstParametersToken.lstParameterValues);
}	
	}
	;
	catch[EugeneException e] {
System.err.println("line "+$functionToken.line+":"+$functionToken.pos+" => "+e.getMessage());
this.cleanUp(1);
	}

listOfParameterValues[boolean defer] returns [ArrayList<NamedElement> lstParameterValues] 
@init {
$lstParameterValues=new ArrayList<NamedElement>();
}
	:	exprToken1=expression[defer] {
if(!defer) {
    if(null!=exprToken1) {
        $lstParameterValues.add($exprToken1.objElement);
    }
}
	} 	(',' exprToken2=listOfParameterValues[defer] {
if(!defer) {
    $lstParameterValues.addAll($exprToken2.lstParameterValues);
}
	})?
	;
	
//**************************
//*** WRAPPED STATEMENTS ***
//**************************
wrappedStatement[boolean defer] returns [NamedElement objElement]
	:	add[defer]	
	    |   permuteToken=combinatorialFunction[defer] {
if(!defer) {
    $objElement = $permuteToken.objDeviceArray;
}
}
	|	getToken=get[defer] {
if(!defer) {
    $objElement = $getToken.objElement;
}	
	}
	|	sizeToken=size[defer] {
if(!defer) {
    Variable objVariable = interp.createVariable(null, EugeneConstants.NUM);
    objVariable.setNum($sizeToken.nSize);
    $objElement = objVariable;
}	
	}
	|	removeToken=remove[defer]	
	|	seqToken=toSequence[defer] {
if(!defer) {
    Variable objVariable = new Variable("SEQUENCE",EugeneConstants.TXT);
    objVariable.setTxt($seqToken.sequence);
    $objElement = objVariable;
}
	}
	|	sbolToken=sbolStatement[defer] {
if(!defer) {
    $objElement = sbolToken.objElement;
}	
	}
	|	genbankToken=genbankStatement[defer] {
if(!defer) {
    $objElement = genbankToken.objElement;
}	
	}
	|       deviceToken=deviceDepthStatements[defer] {
if(!defer) {
    $objElement = deviceToken.objElement;
}	
	}
	;
	catch[EugeneException e] {
e.printStackTrace();
this.cleanUp(1);	
	}
	
deviceDepthStatements[boolean defer] 
		returns [NamedElement objElement]
	:	deviceToken=ID '.' 
			('depth' '(' depthToken=expression[defer] ')' {
if(!defer) {
    $objElement = interp.getDeviceDepth($deviceToken.text, $depthToken.objElement);    
}	
	}
	|		'maxDepth' '(' ')' {
if(!defer) {
    $objElement = interp.getDeviceMaxDepth($deviceToken.text);
}	
	})
	;	
	catch[EugeneException e] {
System.err.println("line "+$deviceToken.line+":"+$deviceToken.pos+" => "+
                e.getMessage());
this.cleanUp(1);
	}

add[boolean defer]
	:	componentToken=ID (
			'.add' '(' lstAdd=listOfExpressions[defer] ')' {
if(!defer) {
    NamedElement objElement = interp.get($componentToken.text); 
    if(null==objElement) {
        System.err.println("line "+$componentToken.line+":"+$componentToken.pos+" => "+
            "I don't know anything about "+$componentToken.text);
        this.cleanUp(1);
    } else {
        if(objElement instanceof Component) {
            ArrayList<NamedElement> lstElements = $lstAdd.lstElements;
            for(NamedElement element : lstElements) {
                ((Component)objElement).add(element);
            }
        } else if(objElement instanceof ComponentArray) {
            ArrayList<NamedElement> lstElements = $lstAdd.lstElements;
            for(NamedElement element : lstElements) {
                ((ComponentArray)objElement).add(element);
            }
        } else if (objElement instanceof EugeneCollection) {
            EugeneCollection objCollection = (EugeneCollection)objElement;

            ArrayList<NamedElement> lstElements = $lstAdd.lstElements;
            for(NamedElement element : lstElements) {
                if(element instanceof CollectionElement) {
                    objCollection.add((CollectionElement)element);
                } else {
                    throw new EugeneException(
                               element.getName()+" cannot be added to the "+
                               objCollection.getName()+" Collection!");
                }
            }
        } else {
            System.err.println("line "+$componentToken.line+":"+$componentToken.pos+" => "+
                $componentToken.text+" does not have an ADD function!");
            this.cleanUp(1);
        }
    }
}
	}	
	|	'.addProperty' '(' propertyToken=ID ')' {
if(!defer) {
    interp.addProperty($componentToken.text, $propertyToken.text);    
}	
	}
		|'.addProperties' '(' lstToken=listOfIDs[defer] ')' {
if(!defer) {
    interp.addProperties($componentToken.text, $lstToken.lstElements);    
}	
	})
	;		
	catch[EugeneException exc] {
System.err.println("line "+$componentToken.line+":"+$componentToken.pos+" => "+exc.toString());
exc.printStackTrace();
this.cleanUp(1);	
	}
	

toSequence[boolean defer] returns [String sequence]
	:	idToken=ID '.' 'toSequence' '(' ')' {
if(!defer) {
    NamedElement objElement = interp.get($idToken.text); 
    if(null == objElement) {
        throw new EugeneException("I don't know anything about "+$idToken.text);
    } else {
        $sequence = interp.toSequence(objElement);
    }
}	
	}
	;
	catch[EugeneException exc] {
System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+exc.toString());
exc.printStackTrace();
this.cleanUp(1);	
	}

get[boolean defer] returns [NamedElement objElement]
	:	idToken=ID '.' 'get' '(' (idxToken=INT {
if(!defer) {
    $objElement = interp.get(
            $idToken.text, 
            Double.parseDouble($idxToken.text));
}
	}	|	varToken=ID {
if(!defer) {
    $objElement = interp.get(
            $idToken.text, 
            $varToken.text);
}
	}	|	strToken=STRING {
if(!defer) {
    $objElement = interp.get(
            $idToken.text, 
            $strToken.text.substring(1,$strToken.text.length()-1));
}
	}) ')' 
	;
	catch[EugeneException e] {
System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
        e.getMessage());
e.printStackTrace();
this.cleanUp(1);        	
	}

/***
    NamedElement objElement = SymbolTables.get($idToken.text);
    if(null==objElement) {
        System.err.println("I don't know anything about "+$idToken.text);
        this.cleanUp(1);
    } else if(!(objElement instanceof Component)) {
        System.err.println($idToken.text+" is not a biological component!");
        this.cleanUp(1);
    }
    
    if(null!=indexToken) {
        Component obj=(Component)objElement;
        $objComponent = (Component)obj.get(
            Integer.parseInt($indexToken.text));
    } else if(null!=variableToken) {
        NamedElement objVar=SymbolTables.get($variableToken.text);
        if(null==objVar) {
            System.err.println("I don't know anything about "+$variableToken.text);
            this.cleanUp(1);
        } else if(!(objVar instanceof Variable)) {
            System.err.println($idToken.text+" is not a variable!");
            this.cleanUp(1);
        }
        
        Variable objVariable = (Variable)objVar;
        if(EugeneConstants.NUM.equals(objVariable.getType())) {
            Component obj=(Component)objElement;
            $objComponent = (Component)obj.get(
                (int)objVariable.getNum());
        } else {
            System.err.println("The variable "+$idToken.text+" is not of type num!");
            this.cleanUp(1);
        }
    }
}	
	}
	;
***/    
	
size[boolean defer] returns [double nSize]
	:	idToken=ID '.' 'size' '(' ')' {
if(!defer) {
    $nSize = interp.sizeOf($idToken.text);
}	
	}	-> ^('.' 'size')
	;
	catch[EugeneException e] {
System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
        e.getMessage());
e.printStackTrace();
this.cleanUp(1);        	
	}

remove[boolean defer]
	:	idToken=ID '.' 'remove' '(' idxToken=expression[defer] ')' {
if(!defer) {
    interp.remove($idToken.text, $idxToken.objElement);
}	
	}	-> ^('.' 'remove')
	;
	catch[EugeneException e] {
System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+ e.getMessage());
e.printStackTrace();
this.cleanUp(1);        	
	}

/** EVALUATE
evaluateStatement[boolean defer]
	returns [NamedElement objElement]
	:	'evaluate' '(' deviceToken=ID (',' ruleToken=ID)? ')' {
if(!defer) {
    NamedElement objDevice = SymbolTables.get($deviceToken.text);
    if(null==objDevice) {
        System.err.println("line "+$deviceToken.line+":"+$deviceToken.pos+
            " => I don't know anything about "+$deviceToken.text+"!");
        this.cleanUp(1);
    }
    if(!(objDevice instanceof SavableElement)) {
        System.err.println("line "+$deviceToken.line+":"+$deviceToken.pos+" => "+
            $deviceToken.text+" is not a biological component nor an array of biological components!");
        this.cleanUp(1);
    }

    // evaluate the 2nd argument
    if(null==ruleToken) {
        RuleArray objRuleArray = new RuleArray(EugeneConstants.RULEARRAY);

        ArrayList<Rule> lstRules = (ArrayList<Rule>)SymbolTables.getRules();
        if(null!=lstRules && !lstRules.isEmpty()) {
            Iterator<Rule> it = lstRules.iterator();
            while(it.hasNext()) {
                try {
                    objRuleArray.add(it.next());
                } catch(Exception e) {
                    // this should actually never happen!
                }
            }
        }
        
        $objElement = (NamedElement)ruleEngine.evaluate((SavableElement)objDevice, objRuleArray);        
        
    } else {
        NamedElement objRule = SymbolTables.get($ruleToken.text);
        if(null==objRule) {
            System.err.println("line "+$ruleToken.line+":"+$ruleToken.pos+
                " => I don't know anything about "+$ruleToken.text+"!");
            this.cleanUp(1);
        }
        if(!(objRule instanceof Rule) && !(objRule instanceof RuleArray)) {
            System.err.println("line "+$ruleToken.line+":"+$ruleToken.pos+" => "+
                $ruleToken.text+" is not a rule nor an array of rules!");
            this.cleanUp(1);
        }

        $objElement = (NamedElement)ruleEngine.evaluate((SavableElement)objDevice, objRule);
    }
}	
	}
	;
**/
 
listOfRules[boolean defer] 
	returns [ArrayList<Rule> lstRules] 
@init {
$lstRules = new ArrayList<Rule>();
}
	:	idToken=ID {
if(!defer) {    
    NamedElement objElement = interp.get($idToken.text);
    if(objElement==null) {
        System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
            "I don't know anything about " + $idToken.text + "!");
        this.cleanUp(1);
    }
    if(!(objElement instanceof Rule)) {
        System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
            "The "+$idToken.text+" component is not a Eugene rule!");
        this.cleanUp(1);
    }
    $lstRules.add((Rule)objElement);
}	
	} (',' idToken=ID {
if(!defer) {
    NamedElement objElement = interp.get($idToken.text);
    if(objElement==null) {
        System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
            "I don't know anything about " + $idToken.text + "!");
        this.cleanUp(1);
    }
    if(!(objElement instanceof Rule)) {
        System.err.println("line "+$idToken.line+":"+$idToken.pos+" => "+
            "The "+$idToken.text+" component is not a Eugene rule!");
        this.cleanUp(1);
    }
    $lstRules.add((Rule)objElement);
}	
	})* 
	;

//***************************
//** PERMUTE() and PRODUCT()
//***************************
combinatorialFunction[boolean defer] returns [DeviceArray objDeviceArray]
    	:   	functionToken=('permute'|'product') '(' 
    			deviceToken=ID              		// device
    			(',' rulesToken=('strict'|'flexible'))?         // rules y/n
    			(',' (capToken=expression[defer])?)?		// cap
    			(',' (depthToken=expression[defer])?)? ')' {    // depth            
if(!defer) {
    $objDeviceArray = interp.generateDevices(
            $functionToken.text,
            $deviceToken.text, 
            $rulesToken.text,
            $capToken.objElement,
            $depthToken.objElement);
}
        }
    	;
    	catch[EugeneException e] {
System.err.println("line "+$functionToken.line+":"+$functionToken.pos+" => "+ e.toString());
e.printStackTrace();
this.cleanUp(1);
    	}

	
getObject[NamedElement objElement]
	:	(('.' elementToken=ID) | ('[' exprToken=expression[true] ']'))*
	;

/**
assemblingStatements[boolean defer] returns [NamedElement objElement]
	:	assembleToken='assemble' '(' nameToken=STRING ',' lstToken=listOfIDs[defer] ')' {
if(!defer) {
    ArrayList<DeviceArray> lstDeviceArray = new ArrayList<DeviceArray>();
    ArrayList<NamedElement> lstElements = $lstToken.lstElements;
    for(int i=0; i<lstElements.size(); i++) {
        NamedElement objElement = lstElements.get(i);
        if(!(objElement instanceof DeviceArray)) {
            System.err.println("line "+$assembleToken.line+":"+$assembleToken.pos+" => "+
                    "The "+objElement.getName()+" element is not of type Device[]!");
            this.cleanUp(1);
        }
        lstDeviceArray.add((DeviceArray)objElement);
    }

    String sName = $nameToken.text;
    if(sName.startsWith("\"") && sName.endsWith("\"")) {
        sName=sName.substring(1, sName.length()-1);
    }

    $objElement = DeviceFactory.productDeviceArrays(sName, lstDeviceArray);
}	
	}
	;
	catch[EugeneException ee] {
System.err.println("line "+$assembleToken.line+":"+$assembleToken.pos+" => "+
    ee.toString());
this.cleanUp(1);
	}
**/
	
//*****************
//***** PRINT *****
//*****************
printStatement[boolean defer]	
@init {
System.err.flush();
System.out.flush();
}
@after {
System.out.flush();
}
	:
        |	'println' '(' ')' {
if(!defer) {
        System.out.println();
}
        }
        |	'print' '(' printToken1=whatToPrint[defer] {
if(!defer) {        
    System.out.print($printToken1.s);
}
        } (',' printToken2=whatToPrint[defer] {
if(!defer) {                
    System.out.print($printToken2.s);
}	
	})* ')'
        |	'println' '(' printToken1=whatToPrint[defer] {
if(!defer) {        
    System.out.print($printToken1.s);
}
        } (',' printToken2=whatToPrint[defer] {
if(!defer) {                
    System.out.print($printToken2.s);
}	
	})* ')' {
if(!defer) {        
    System.out.println();
}
	}
	;

whatToPrint[boolean defer] returns [String s] 
	:	valueToken=expression[defer] {
if(!defer) {
    if($valueToken.objElement!=null) {
        $s = $valueToken.objElement.toString();
    } else {
        $s = new String();
    }
}
	}
	|	wrapperToken=wrappedStatement[defer] {
if(!defer) {
    NamedElement objElement = $wrapperToken.objElement;
    if(objElement!=null) {
        $s = objElement.toString();
    }
}	
	}
	;

/*** DATA EXTRACTION ***/
dataExtraction[boolean defer] 
	:	sbolStatement[defer]
	|	genbankStatement[defer]
	|	pigeonStatement[defer]
	|	registryStatement[defer]
	;
	
/*** SBOL STATEMENTS ***/
sbolStatement[boolean defer] returns [NamedElement objElement]
	:	'SBOL' '.' (sbolExportStatement[defer] | importToken=sbolImportStatement[defer]) {
if(!defer && importToken!=null) {
    $objElement = $importToken.objElement;
}	
	}
	;

sbolExportStatement[boolean defer] 
	:	'export' '(' idToken=ID ',' filenameToken=STRING ')' {
if(!defer) {
    interp.exportToSBOL(
            $idToken.text, 
            $filenameToken.text.substring(1, $filenameToken.text.length()-1));
}	
	}	
	;
	catch[EugeneException e] {
System.err.println("line "+$idToken.line+":"+$idToken.pos+
    " => "+e.getMessage());	
this.cleanUp(1);
	}
	
sbolImportStatement[boolean defer] 
	returns [NamedElement objElement] 
	:	'import' '(' fileToken=STRING ')' {
if(!defer) {
    $objElement = interp.importSBOL($fileToken.text);
}	
	}
	;	
	catch[EugeneException e] {
System.err.println("line "+$fileToken.line+":"+$fileToken.pos+
    " => "+e.getMessage());	
this.cleanUp(1);
	}
	
genbankStatement[boolean defer] returns [NamedElement objElement]
	:	'Genbank' '.' (importToken=genbankImportStatement[defer] | genbankExportStatement[defer]) {
if(!defer && importToken!=null) {
    $objElement = $importToken.objElement;
}	
	}
	;
		
genbankExportStatement[boolean defer] returns [NamedElement objElement] 
	:	'export' '(' ')'
	;
	
genbankImportStatement[boolean defer] returns [NamedElement objElement]
	:	'import' '(' typeToken=ID ',' partToken=STRING ')' {
if(!defer) {    
    // check if the part type exists
    NamedElement objElement = interp.get($typeToken.text);
    if(objElement==null) {
        System.err.println("line "+$typeToken.line+":"+$typeToken.pos+" => "+
            "I don't know the part type "+$typeToken.text);
        this.cleanUp(1);
    } else if(!(objElement instanceof PartType)) {
        System.err.println("line "+$typeToken.line+":"+$typeToken.pos+" => "+
            "The element "+$typeToken.text+" is not a part type!");
        this.cleanUp(1);
    } 
    
    try {
        $objElement = GenbankImporter.importPart((PartType)objElement, $partToken.text);
    } catch(Exception e ){
        System.err.println("line "+$typeToken.line+":"+$typeToken.pos+" => "+
            "Somehting went wrong while importing part "+$partToken.text+"!");
        $objElement = null;
    }
}	
	} 
	;	

pigeonStatement[boolean defer]
	:	'pigeon' '(' idToken=ID ')' {
if(!defer) {
    interp.pigeon($idToken.text);
}		
}
	;	
	catch[EugeneException e] {
System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+" => "+
    e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}

/** PARTSREGISTRY **/
registryStatement[boolean defer]
	:	'Registry' '.' 'import' '(' nameToken=STRING ')' {
if(!defer) {

    String name = $nameToken.text;
    if(null != name) {
    
        // remove the double quotas
        name = name.substring(1, name.length()-2);
        
        if(null == this.registryImporter) {
            this.registryImporter = new SBOLRegistryImporter();
        }
        
        List<Component> lst = this.registryImporter.importComponent($nameToken.text);
        if(null!=lst && !lst.isEmpty()) {
            for(Component component : lst) {
                SymbolTables.put(component);
            }
        } else {
            System.err.println("line "+$nameToken.line+":"+$nameToken.pos+" => "+
                "Cannot import "+name+"!");
            this.cleanUp(1);
        }
    }
}		
	}	
	;
	catch[Exception e] {
System.err.println("line "+retval.start.getLine()+":"+retval.start.getCharPositionInLine()+" => "+
    e.getMessage());
e.printStackTrace();
this.cleanUp(1);
	}

//*****************************
//*** LEXER
//*****************************
// Lexer rule for include statements
INCLUDE	:	'include' (WS)+ f=STRING {
String name = f.getText();
name = name.substring(1,name.length()-1);
includeFile(name);
	}
	;

ID  :		('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ('a'..'z'|'A'..'Z'|'0'..'9')
	|	INT ('a'..'z'|'A'..'Z'|'_')+ ('a'..'z'|'A'..'Z'|'0'..'9')
	|	'a'..'z'
|		'A'..'Z'
    ;

INT	:	('0'..'9')+
	;
	
	
FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;
    
COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

DYNAMIC_NAME
	: '*' '*'
	;

	
STRING
    :  '"' ( ESC_SEQ | ~('"') )* '"'
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;


fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
