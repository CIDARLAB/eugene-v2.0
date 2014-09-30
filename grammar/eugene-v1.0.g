grammar eugene;

options {
	output = AST;
	backtrack = true;
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
	COMMA = ',';
	DOT = '.';
	NUM = 'num';
	INT = 'int';
	BOOLEAN = 'boolean';
	IMAGE = 'Image';
	PROPERTY = 'Property';
	PART = 'Part';
	DEVICE = 'Device';
	RULE = 'Rule';
	TXT = 'txt';
	ADDPROPS = 'addProperties';
	PRINT = 'print';
	IMAGE = 'Image';
	BEFORE = 'BEFORE';
	AFTER = 'AFTER';
	WITH = 'WITH';
	NOTWITH = 'NOTWITH';
	NEXTTO = 'NEXTTO';
	NOTCONTAINS = 'NOTCONTAINS';
	CONTAINS = 'CONTAINS';
	NOTMORETHAN = 'NOTMORETHAN';
	NEQUAL = '!=';
	LTHAN = '<';
	GTHAN = '>';
	LEQUAL = '<=';
	GEQUAL = '>=';
	AND = 'AND';
	OR = 'OR';
	NOT = 'NOT';
	ASSERT = 'Assert';
	NOTE = 'Note';
	IF = 'if';
	ELSE = 'else';
	ON = 'on';
	TRUE = 'true';
	FALSE = 'false';
	PERMUTE = 'permute';
	STRICT = 'strict';
	FLEXIBLE = 'flexible';
}

@header {
	//package eugene;
	import java.util.HashMap;
	import java.util.Stack;
	import java.util.Set;
	import java.util.Iterator;
	import java.util.Random;
}

@members {
	@Override
	public void reportError(RecognitionException RecogEx) {
		int line = input.LT(-1).getLine();
		throw new IllegalArgumentException("@Error Line " + line + ": " + "Syntax error.");
	}

	//memory for definitions
	public HashMap<String, String> propertyDefinitions = new HashMap<String, String>();
	public HashMap<String, ArrayList<ArrayList<String>>> partDefinitions = new HashMap<String, ArrayList<ArrayList<String>>>();

	//memory for values and instances
	public HashMap<String, Part> partDeclarations = new HashMap<String, Part>();
	public HashMap<String, Device> deviceDeclarations = new HashMap<String, Device>();
	public HashMap<String, Rule> ruleDeclarations = new HashMap<String, Rule>();
	public HashMap<String, Primitive> primitiveDeclarations = new HashMap<String, Primitive>();

	boolean debug = false;
	boolean checktemp = true;
	boolean compListInside = false;
	Stack<String> evaluate = new Stack<String>();
	String typeList = "";
	String partType = "";

	//working variables for intermediate steps to hold contents
	ArrayList<String> propertyListHolder = new ArrayList<String>();
	ArrayList<Primitive> propertyValuesHolder = new ArrayList<Primitive>();
	ArrayList<String> componentList = new ArrayList<String>();

	//working lists to hold temporary elements for asserting and noting statements
	ArrayList<Primitive> assertionList = new ArrayList<Primitive>();

	ArrayList<Primitive> ifEvaluationList = new ArrayList<Primitive>();

	//memory for asserting and noting rules
	public HashMap<String, ArrayList<Primitive>> ruleAssertions = new HashMap<String, ArrayList<Primitive>>();
	public HashMap<String, ArrayList<Primitive>> ruleNotes = new HashMap<String, ArrayList<Primitive>>();
	public HashMap<String, ArrayList<String>> ruleAssertionViolations = new HashMap<String, ArrayList<String>>();
	public HashMap<String, ArrayList<String>> ruleNoteViolations = new HashMap<String, ArrayList<String>>();

	//holds the assert statement expression as key for ruleAssertions and ruleNotes
	String assertStatement = "";

	//stores the logic values evaluated by if statement and accroding to the values a statement will be
	//executed or not in rule statement
	Stack<String> ifValueStack = new Stack<String>();


	//defines a property and its type, if not already defined
	public void defineProperty(String type, String varname) {
		if (propertyDefinitions.containsKey(varname)) {
			printError("Property " + varname + " already exists.");
		} else {
			propertyDefinitions.put(varname, type);
		}
	}

	//defines a part class if not already declared
	public void definePart(String varname) {
		if (!checkIfAlreadyDeclared(varname, true)) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			ArrayList<String> propertyList = new ArrayList<String>();
			ArrayList<String> imageBinding = new ArrayList<String>();
			ArrayList<String> instanceList = new ArrayList<String>();
			list.add(propertyList);
			list.add(imageBinding);
			list.add(instanceList);
			partDefinitions.put(varname, list);
		}
	}

	//declares primitive without instantiating it to any value
	public void declarePrimitiveNoValue(String name, String type) {
		if (!checkIfAlreadyDeclared(name, true)) {
			Primitive p = new Primitive(name , type);
			primitiveDeclarations.put(name, p);
		}
	}

	//declares and instantiates a num primitive with a value
	public void declarePrimitiveWithValueNum(String name, Primitive prim, int index) {
		if (!checkIfAlreadyDeclared(name, true)) {
			if (prim.type.equals("num")) {
				Primitive p = new Primitive(name, "num");
				p.num = prim.num;
				primitiveDeclarations.put(name, p);
			} else if (prim.type.equals("numList") && prim.index != -1) {
				Primitive p = new Primitive(name, "num");
				p.num = prim.numList.get(prim.index);
			} else {
				printError("Type mismatch. Type is " + prim.type + " but must be num.");
			}
		}
	}

	//declares and instantiates a txt primitive with a value
	public void declarePrimitiveWithValueTxt(String name, Primitive prim, int index) {
		if (!checkIfAlreadyDeclared(name, true)) {
			if (prim.type.equals("txt")) {
				Primitive p = new Primitive(name, "txt");
				p.txt = prim.txt;
				primitiveDeclarations.put(name, p);
			} else if (prim.type.equals("txtList") && index != -1) {
				Primitive p = new Primitive(name, "txt");
				p.txt = prim.txtList.get(index);
			} else {
				printError("Type mismatch. Type is " + prim.type + " but must be txt.");
			}
		}
	}

	//declares and instantiates a txtList primitive with a txt value list
	public void declarePrimitiveWithValueTxtList(String name, Primitive prim) {
		if (!checkIfAlreadyDeclared(name, true)) {
			if (prim.type.equals("txtList")) {
				Primitive p = new Primitive(name, "txtList");
				p.txtList.addAll(prim.txtList);
				primitiveDeclarations.put(name, p);
			} else {
				printError("Type mismatch. Type is " + prim.type + " but must be txtList.");
			}
		}
	}

	//declares and instantiates a numList primitive with a num value list
	public void declarePrimitiveWithValueNumList(String name, Primitive prim) {
		if (!checkIfAlreadyDeclared(name, true)) {
			if (prim.type.equals("numList")) {
				Primitive p = new Primitive(name, "numList");
				p.numList.addAll(prim.numList);
				primitiveDeclarations.put(name, p);
			} else {
				printError("Type mismatch. Type is " + prim.type + " but must be numList.");
			}
		}
	}

	//declares and instantiates a boolean primitive with a boolean value
	public void declarePrimitiveWithValueBool(String name, Primitive prim) {
		if (!checkIfAlreadyDeclared(name, true)) {
			if (prim.type.equals("bool")) {
				Primitive p = new Primitive(name, "bool");
				p.bool = prim.bool;
				primitiveDeclarations.put(name, p);
			} else {
				printError("Type mismatch. Type is " + prim.type + " but must be numList.");
			}
		}
	}

	//adds property to the list which will be instantiated for each part through instPropertyList(typename)
	public void addToPropertyListHolder(String prop) {
		if (!propertyDefinitions.containsKey(prop)) {
			printError("Property " + prop + " does not exist.");
		} else {
			if(propertyDefinitions.get(prop).equals("txtList")) {
				typeList = "txt";
			} else if(propertyDefinitions.get(prop).equals("numList")) {
				typeList = "num";
			}
			propertyListHolder.add(prop);
		}
	}

	//adds values to the corresponding property
	public void addToPropertyValuesHolder(String prop, Primitive p, int index) {
		if (propertyDefinitions.get(prop).equals(p.type)) {
			propertyValuesHolder.add(p);
		} else if(p.type.equals("txtList") && index != -1 && p.type.equals("txt")) {
			p.index = index;
			propertyValuesHolder.add(p);
		} else if(p.type.equals("numList") && index != -1 && p.type.equals("num")) {
			p.index = index;
			propertyValuesHolder.add(p);
		} else {
			printError("Expected a value of type " + propertyDefinitions.get(prop) + " for property " + prop + ".");
		}
	}

	//instantiates a list of properties that a pomponent can have
	public void instPropertyList(String typename) {
		String property;
		for (int i = 0; i < propertyListHolder.size(); i++) {
			property = propertyListHolder.get(i);
			if (!propertyDefinitions.containsKey(property)) {
				printError("Property " + property + " does not exist.");
			} else {
				partDefinitions.get(typename).get(0).add(property);
			}
		}
		propertyListHolder.clear();
	}

	//binds a specific image path to a class or instance, not really useful by itself, needs to be used with Spectacles
	public void bindImage(String name, String path) {
		if(partDefinitions.containsKey(name)) {
			partDefinitions.get(name).get(1).add(path.substring(1, path.length()-1));
		} else if (partDeclarations.containsKey(name)) {
			partDeclarations.get(name).imagePath = path.substring(1, path.length()-1);
		} else if (deviceDeclarations.containsKey(name)) {
			deviceDeclarations.get(name).imagePath = path.substring(1, path.length()-1);
		} else {
			printError("Part " + name + " does not exist.");
		}
	}

	//declares an instance of a part
	public void declareInstances(String classname, String varname) {
		if (!(partDefinitions.containsKey(classname))) {
			printError("Part " + classname + " does not exist.");
		} else if (!checkIfAlreadyDeclared(varname, false) && !(propertyDefinitions.containsKey(varname))) {
			Part part = new Part();
			part.name = varname;
			part.type = classname;
			if (!partDefinitions.get(classname).get(1).isEmpty()) {
				part.imagePath = partDefinitions.get(classname).get(1).get(0);
			}
			ArrayList<String> props = partDefinitions.get(classname).get(0);
			for (int i = 0; i < props.size(); i++) {
				String name = props.get(i);
				Primitive prim = new Primitive(name, propertyDefinitions.get(name));
				part.propertyValues.put(name, prim);
			}
			partDeclarations.put(varname, part);
			partDefinitions.get(classname).get(2).add(varname);
		}
	}

	//checks if the name has been defined in program
	public boolean checkIfAlreadyDeclared(String name, boolean all) {
		if (ruleDeclarations.containsKey(name)) {
			printError(name + " has already been defined as a rule.");
			return true;
		} else if (partDeclarations.containsKey(name)) {
			printError(name + " has already been defined of type " + partDeclarations.get(name).type + ".");
			return true;
		} else if (deviceDeclarations.containsKey(name)) {
			printError(name + " has already been defined of type device.");
			return true;
		} else if (primitiveDeclarations.containsKey(name)) {
			printError(name + " has already been defined of type " + primitiveDeclarations.get(name).type + ".");
			return true;
		} else if (propertyDefinitions.containsKey(name) && all) {
			printError(name + " has aready been defined as a property.");
		} else if (partDefinitions.containsKey(name) && all) {
			printError(name + " has already been defined as a part class.");
		}
		return false;
	}

	//declares a rule by passing in the appropritate data types of the rule operands to declareRule method
	public void checkToDeclareRule(Primitive leftPrim, String leftOp, Primitive rightPrim, String rightOp, String name, String leftInst, String rightInst, String operator) {
		if (leftPrim != null && rightPrim != null) {
			declareRule(name, leftInst, leftPrim, rightInst, rightPrim, operator);
		} else {
			if (leftPrim != null) {
				declareRule(name, leftInst, leftPrim, rightOp, null, operator);
			} else if (rightPrim != null) {
				declareRule(name, leftOp, null, rightInst, rightPrim, operator);
			} else {
				declareRule(name, leftOp, null, rightOp, null, operator);
			}
		}
	}

	//declares a rule instance
	public void declareRule(String rulename, String leftOp, Primitive leftOpP, String rightOp, Primitive rightOpP, String operator) {
		if (!checkIfAlreadyDeclared(rulename, true)) {
			Rule r = new Rule();
			r.operator = operator;
            		r.name = rulename;
			if ((partDeclarations.containsKey(leftOp) /*|| deviceDeclarations.containsKey(leftOp)*/) && (partDeclarations.containsKey(rightOp) /*|| deviceDeclarations.containsKey(rightOp)*/)) {
				if (leftOpP != null && rightOpP != null) {
					Part leftComp = partDeclarations.get(leftOp);
					Part rightComp = partDeclarations.get(rightOp);
					if (leftComp.propertyValues.containsKey(leftOpP.name) && rightComp.propertyValues.containsKey(rightOpP.name)) {
						String typeleft = leftComp.propertyValues.get(leftOpP.name).type;
						String typeright = rightComp.propertyValues.get(rightOpP.name).type;
						if (typeleft.equals(typeright)) {
							r.operand1 = leftOpP.getValue().toString();
							r.operand2 = rightOpP.getValue().toString();
							ruleDeclarations.put(rulename, r);
						} else {
							printError("Left and right property are not of the same type.\nLeft property type: " + typeleft + " and right property type: " + typeright);
						}
					} else {
						printError("Either property " + leftOpP.name + " or property " + rightOpP.name + " are not " + "properties of part " + leftOp + " or part" + rightOp);
					}
				} else if (leftOpP == null && rightOpP == null) {
					r.operand1 = leftOp;
					r.operand2 = rightOp;
					ruleDeclarations.put(rulename, r);
				}
			} else if (partDeclarations.containsKey(leftOp) && leftOpP != null && primitiveDeclarations.containsKey(rightOp)) {
				r.operand1 = leftOpP.getValue().toString();
				r.operand2 = primitiveDeclarations.get(rightOp).getValue().toString();
				ruleDeclarations.put(rulename, r);
			} else if (partDeclarations.containsKey(rightOp) && rightOpP != null && primitiveDeclarations.containsKey(leftOp)) {
				r.operand1 = primitiveDeclarations.get(leftOp).getValue().toString();
				r.operand2 = rightOpP.getValue().toString();
				ruleDeclarations.put(rulename, r);
			//for NOTCONTAINS
			} else if (leftOp == null && partDeclarations.containsKey(rightOp)) {
				r.operand1 = "";
				r.operand2 = rightOp;
				ruleDeclarations.put(rulename, r);
			//for NOTMORETHAN
			} else if (partDeclarations.containsKey(leftOp) && primitiveDeclarations.containsKey(rightOp)) {
				r.operand1 = leftOp;
				r.operand2 = primitiveDeclarations.get(rightOp).getValue().toString();
				ruleDeclarations.put(rulename, r);
			} else {
				//printError("Either " + leftOp + " or " + rightOp + " or both are not either a part or device or primitive.");
				printError(leftOp + " or " + rightOp + " might not have been declared.");
			}
		}
	}

	//sets the values of all properties in a part
	public void setProperties(String classname, String name) {
		ArrayList<String> listproperties = partDefinitions.get(classname).get(0);
		Primitive p;
		if (propertyValuesHolder.size() != listproperties.size()) {
			printError("Expected " + listproperties.size() + " values for part " + classname + ".");
		}
		for (int i = 0; i < listproperties.size(); i++) {
			p = partDeclarations.get(name).propertyValues.get(listproperties.get(i));
			if (p.type == propertyValuesHolder.get(i).type) {
				if (p.type.equals("num")) {
					p.num = propertyValuesHolder.get(i).num;
				} else if (p.type.equals("txt")) {
					p.txt = propertyValuesHolder.get(i).txt;
				} else if (p.type.equals("txtList")) {
					p.copytxtList(propertyValuesHolder.get(i).txtList);
				} else if (p.type.equals("numList")) {
					p.copynumList(propertyValuesHolder.get(i).numList);
				} else if (p.type.equals("bool")) {
					p.bool = propertyValuesHolder.get(i).bool;
				}
			} else if (propertyValuesHolder.get(i).type.equals("numList") && p.type.equals("num") && propertyValuesHolder.get(i).index != -1) {
				p.num = propertyValuesHolder.get(i).numList.get(propertyValuesHolder.get(i).index);
			} else if (propertyValuesHolder.get(i).type.equals("txtList") && p.type.equals("txt") && propertyValuesHolder.get(i).index != -1) {
				p.txt = propertyValuesHolder.get(i).txtList.get(propertyValuesHolder.get(i).index);
			} else {
				printError("Expected a value of type " + p.type + " for property " + p.name + ".");
			}
		}
		propertyValuesHolder.clear();
	}

	//sets the values of specified properties in a part
	public void setPropertiesDot(String classname, String name) {
		ArrayList<String> listproperties = partDefinitions.get(classname).get(0);
		String propertyName;
		Primitive p;
		for (int i = 0; i < propertyListHolder.size(); i++) {
			propertyName = propertyListHolder.get(i);
			if (propertyDefinitions.containsKey(propertyName)) {
				p = partDeclarations.get(name).propertyValues.get(propertyName);
				if (p.type.equals("num")) {
					p.num = propertyValuesHolder.get(i).num;
				} else if (p.type.equals("txt")) {
					p.txt = propertyValuesHolder.get(i).txt;
				} else if (p.type.equals("txtList")) {
					p.copytxtList(propertyValuesHolder.get(i).txtList);
				} else if (p.type.equals("numList")) {
					p.copynumList(propertyValuesHolder.get(i).numList);
				} else if (p.type.equals("bool")) {
					p.bool = propertyValuesHolder.get(i).bool;
				}
			} else {
				printError("Property " + propertyName + " does not exist for part " + classname + ".");
			}
		}
		propertyListHolder.clear();
		propertyValuesHolder.clear();
	}

	//declares an instance of a device
	public void declareDevice(String deviceInst) {
		if (!checkIfAlreadyDeclared(deviceInst, true)) {
			Device d = new Device();
			d.type = "Device";
			d.name = deviceInst;
			deviceDeclarations.put(deviceInst, d);
		}
	}

	//instantiates all fields of the device
	public void instDevice(String deviceInst){
		if (!checkIfAlreadyDeclared(deviceInst, true)) {
			Device d = new Device();
			d.type = "Device";
			d.name = deviceInst;
			d.components.addAll(componentList);
			componentList.clear();
			deviceDeclarations.put(deviceInst, d);
		}
	}

	//checks if a part instance has been defined
	public void isPartDefined(String instance) {
		if (!partDefinitions.containsKey(instance)) {
			printError("Part " + instance + " does not exist.");
		}
	}

	//adds part or device to component list of a device
	public void addToComponentList(String compInst) {
		if (!partDeclarations.containsKey(compInst) && !deviceDeclarations.containsKey(compInst)) {
			printError(compInst + " does not exist.");
		} else {
			componentList.add(compInst);
		}
	}

	//gets the property values of a component list of a device
	public Primitive getDevicePropertyValue(String inst, String prop, int index) {
		if (!deviceDeclarations.containsKey(inst)) {
			printError("Device " + inst + " does not exist.");
		} else if (!propertyDefinitions.containsKey(prop)) {
			printError("Property " + prop + " does not exist.");
		} else {
			Device d = deviceDeclarations.get(inst);
			ArrayList<Primitive> listOfPrimitives = getPartList(d, prop);
			Primitive p = new Primitive();
			String property = propertyDefinitions.get(prop);
			if (property.equals("txtList") || property.equals("numList")) {
				p.type = propertyDefinitions.get(prop);
			} else {
				p.type = propertyDefinitions.get(prop) + "List";
			}
			for (int i = 0; i < listOfPrimitives.size(); i++) {
				if (p.type.equals("txtList")) {
					if (property.equals("txtList")) {
						p.txtList.addAll(listOfPrimitives.get(i).txtList);
					} else {
						p.txtList.add(listOfPrimitives.get(i).txt);
					}
				} else if (p.type.equals("numList")) {
					if (property.equals("numList")) {
						p.numList.addAll(listOfPrimitives.get(i).numList);
					} else {
						p.numList.add(listOfPrimitives.get(i).num);
					}
				}
			}
			return p;
		}
		return null;
	}

	//expands the component list of a device to the basic component parts and returns a list of the properties from all those expanded components
	public ArrayList<Primitive> getPartList(Device d, String prop) {
		ArrayList<Primitive> compLst = new ArrayList<Primitive>();
		ArrayList<String> comps = d.components;
		String comp;
		for (int i = 0; i < comps.size(); i++) {
			comp = comps.get(i);
			if (partDeclarations.containsKey(comp)) {
				compLst.add(partDeclarations.get(comp).propertyValues.get(prop));
			} else {
				compLst.addAll(getPartList(deviceDeclarations.get(comp), prop));
			}
		}
		return compLst;
	}

	//returns a list of names of the expanded components of a device
	public ArrayList<String> getPartList(Device d) {
		ArrayList<String> compLst = new ArrayList<String>();
		ArrayList<String> comps = d.components;
		String comp;
		for (int i = 0; i < comps.size(); i++) {
			comp = comps.get(i);
			if (partDeclarations.containsKey(comp)) {
				compLst.add(comp);
			} else {
				compLst.addAll(getPartList(deviceDeclarations.get(comp)));
			}
		}
		return compLst;
	}

	//right now only property is associated with parts, need to check later in devices
	public Primitive getPropertyValue(String inst, String prop, Primitive p, int index) {
		if (!partDeclarations.containsKey(inst)) {
			printError("Part " + inst + " does not exist.");
		} else if (!propertyDefinitions.containsKey(prop)){
			printError("Property " + prop + " does not exist.");
		} else {
			Part part = partDeclarations.get(inst);
			if (part == null) {
				printError("Part " + inst + " does not exist.");
			} else {
				Primitive prim;
				if (index != -1) {
					if (part.propertyValues.get(prop).type.equals("txtList")) {
						int size = part.propertyValues.get(prop).txtList.size();
						if (size <= index) {
							printError("Index out of bound exception: index " + index + " size: " + size);
						} else {
							prim = part.propertyValues.get(prop);
							if (prim == null) {
								printError("Property " + prop + " does not exist for part " + part.name + ".");
							} else {
								p.type = "txt";
								p.txt = prim.txtList.get(index);
								return prim;
							}
						}
					} else if (part.propertyValues.get(prop).type.equals("numList")) {
						int size = part.propertyValues.get(prop).numList.size();
						if (size <= index) {
							printError("Index out of bound exception: index " + index + " size: " + size);
						} else {
							prim = part.propertyValues.get(prop);
							if (prim == null) {
								printError("Property " + prop + " does not exist for part " + part.name + ".");
							} else {
								p.type = "num";
								p.num = prim.numList.get(index);
								return prim;
							}
						}
					}
				} else {
					prim = part.propertyValues.get(prop);
					if (prim == null) {
						printError("Property " + prop + " does not exist for part " + part.name + ".");
					} else {
						p.type = prim.type;
						if (prim.type.equals("txt")) {
							p.txt = prim.txt;
						} else if (prim.type.equals("num")) {
							p.num = prim.num;
						} else if (prim.type.equals("bool")) {
							p.bool = prim.bool;
						} else if (prim.type.equals("txtList")) {
							p.txtList.addAll(prim.txtList);
						} else if (prim.type.equals("numList")) {
							p.numList.addAll(prim.numList);
						}
						return prim;
					}
					
				}
			}
		}
		return null;
	}

	//concatenates to assertStatement either a bracket or operator
	public void addToAssertStatement(String s) {
		assertStatement += s;
	}

	//concatenates assertStatement with the ruleName which will be used as key for either ruleAsserts hashmap or ruleNotes hashmap
	public String getAssertStatementOperand(String ruleName) {
		if (ruleDeclarations.containsKey(ruleName)) {
			assertStatement += " " + ruleName;
			return ruleName;
		} else {
			printError(ruleName + " is not a rule instance.");
		}
		return ruleName;
	}

	//adds operand to Assertion List
	public void addToAssertionList(String opname, String type) {
		Primitive p;
		if (opname != null) {
			p = new Primitive(opname, type);
			assertionList.add(p);
		}
	}

	//clears the current assertionList after it has been stored in either the hashmap ruleNotes or ruleAssertions
	public void clearAssertionList() {
		if (assertionList.size() != 0) {
			assertionList.clear();
			assertStatement = "";
		}
	}

	//stores the current assertionList in either ruleNotes or ruleAssertions, depending what grammar method is called
	public void storeAssertList(boolean assertCheck) {
		if (!assertionList.isEmpty()) {
			ArrayList<Primitive> list = new ArrayList<Primitive>();
			list.addAll(assertionList);
			if (assertCheck) {
				ruleAssertions.put(assertStatement, list);
			} else {
				ruleNotes.put(assertStatement, list);
			}
		}
	}

	//asserts or notes a rule or combination of rules when a device is declared
	public boolean AssertRule(Device d, ArrayList<Primitive> List, String key, boolean note, boolean ifcheck) {
		if (!assertionList.isEmpty()) {
			Stack<String> evaluationStack = new Stack<String>();
			ArrayList<String> partList = getPartList(d);//list where all members are of class part
			ArrayList<String> unopenedList = d.components; //list of devices or parts, not expanded like above
			ArrayList<String> list;
			for (int i = 0; i < List.size(); i++) {
				Primitive subject = List.get(i);
				if (subject.type.equals("operand")) {
					evaluationStack.push(List.get(i).name);
				} else {
					String ruleName = evaluationStack.pop(), result = "", result2 = "", ruleName2 = "";
					Rule rule2;
					if (ruleName.equals("0") || ruleName.equals("1")) {
						result = ruleName;
					} else {
						Rule rule = ruleDeclarations.get(ruleName);
						//if both are devices, pass in just list consisting of devices possibly
						if (deviceDeclarations.containsKey(rule.operand1) && deviceDeclarations.containsKey(rule.operand2)) {
							result = rule.evaluate(null, unopenedList);
						} else {
							//if one of the operands is a device pass in both lists else if none is a device
							//it means they can only be parts and pass just the component list
							if (deviceDeclarations.containsKey(rule.operand1) || deviceDeclarations.containsKey(rule.operand2)) {
							} else {
								result = rule.evaluate(partList, null);
							}
						}
					}
					if (subject.name.equals("NOT")) {
						if (result.equals("0")) {
							result = "1";
						} else {
							result = "0";
						}
						evaluationStack.push(result);
					} else {
						ruleName2 = evaluationStack.pop();
						if (ruleName2.equals("0") || ruleName2.equals("1")) {
							result2 = ruleName2;
						} else {
							rule2 = ruleDeclarations.get(ruleName2);
							if (deviceDeclarations.containsKey(rule2.operand1) && deviceDeclarations.containsKey(rule2.operand2)) {
								result2 = rule2.evaluate(null, unopenedList);
							} else {
								//currently cannot compare a part to a device
								if (deviceDeclarations.containsKey(rule2.operand1) || deviceDeclarations.containsKey(rule2.operand2)) {
								//to be implemented maybe
								} else {
									result2 = rule2.evaluate(partList, null);
								}
							}
						}
						evaluationStack.push(evaluateValue(result, result2, subject.name));
					}
				}
				if (!evaluationStack.peek().equals("0") && !evaluationStack.peek().equals("1") && ruleDeclarations.containsKey(evaluationStack.peek())) {
					Rule rule = ruleDeclarations.get(evaluationStack.pop());
					String result = "";
					if (deviceDeclarations.containsKey(rule.operand1) && deviceDeclarations.containsKey(rule.operand2)) {
						result = rule.evaluate(null, unopenedList);
						evaluationStack.push(result);
					} else {
						//currently cannot compare a part to a device
						if (deviceDeclarations.containsKey(rule.operand1) || deviceDeclarations.containsKey(rule.operand2)) {
						//maybe to be implemented
						} else {
							result = rule.evaluate(partList, null);
							evaluationStack.push(result);
						}
					}
				}
			}
			if (evaluationStack.peek().equals("0") && note && !ifcheck) {
				String s = evaluationStack.pop();
				System.out.println("Warning, the Note statement: ("+ key + ") has been violated on device instance " + d.name);
				return false;
			} else if (evaluationStack.peek().equals("0") && !note && !ifcheck) {
				String s = evaluationStack.pop();
				return false;
			} else if (evaluationStack.peek().equals("1")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	//iterates through all existing rules and checks if the created device meets the criteria
	//if a rule in assert list is not met by the device, an excpetion is thrown and program terminates
	//if a rule in note list is not met by the device, a warning statement is printed out
	public void applyRulesToDevice(String deviceInst) {
		Set<String> setAssert = ruleAssertions.keySet();
		Set<String> setNote = ruleNotes.keySet();
		Iterator<String> iAssert = setAssert.iterator();
		Iterator<String> iNote = setNote.iterator();
		String key = "";
		while (iNote.hasNext()) {
			key = iNote.next();
			boolean b = AssertRule(deviceDeclarations.get(deviceInst), ruleNotes.get(key), key, true, false);
			if (!b) {
				if (!ruleNoteViolations.containsKey(deviceInst)) {
					ruleNoteViolations.put(deviceInst, new ArrayList<String>());					
				}
				ruleNoteViolations.get(deviceInst).add(key);
			}
		}
		while (iAssert.hasNext()) {
			key = iAssert.next();
			boolean b = AssertRule(deviceDeclarations.get(deviceInst), ruleAssertions.get(key), key, false, false);
			if (!b) {
				if (!ruleAssertionViolations.containsKey(deviceInst)) {
					ruleAssertionViolations.put(deviceInst, new ArrayList<String>());					
				}
				ruleAssertionViolations.get(deviceInst).add(key);
				printError("Error, the Assert statement: ("+ key + ") has been violated on device instance " + deviceInst);
			}
		}
	}

	//evaluates the logical value of two truth statements. Used by AssertRule method
	public String evaluateValue(String result, String result2, String operator) {
		if (operator.equals("AND")) {
			if (result.equals("0") || result2.equals("0")) {
				return "0";
			} else {
				return "1";
			}
		}
		if (operator.equals("OR")) {
			if (result.equals("0") && result2.equals("0")) {
				return "0";
			} else {
				return "1";
			}
		}
		return "null";
	}

	//assigns value to an address and used in rule statement
	public void valueAssignment(Primitive primVariable, int index, Primitive pRight) {
		if (primVariable.type.equals(pRight.type)) {
			if (primVariable.type.equals("num")) {
				primVariable.num = pRight.num;
			} else if (primVariable.type.equals("txt")) {
				primVariable.txt = pRight.txt;
			} else if (primVariable.type.equals("bool")) {
				primVariable.bool = pRight.bool;
			} else if (primVariable.type.equals("numList")) {
				primVariable.numList.clear();
				primVariable.numList.addAll(pRight.numList);
			} else if (primVariable.type.equals("txtList")) {
				primVariable.txtList.clear();
				primVariable.txtList.addAll(pRight.txtList);
			}
		} else {
			if (index != -1) {
				if (primVariable.type.equals("numList") && pRight.type.equals("num")) {
					primVariable.numList.set(index, pRight.num);
				} else if (primVariable.type.equals("txtList") && pRight.type.equals("txt")) {
					primVariable.txtList.set(index, pRight.txt);
				} else {
					printError("Incompatible types. Left variable type is " + primVariable.type + " and  instantiated type of value is " + pRight.type);
				}
			}
		}
	}

	//sets first expression in print statement
	public String setStatementPrint(Primitive p) {
		if (p != null) {
			if (p.index != -1 && p.type.equals("numList")) {
				return p.numList.get(p.index).toString();
			} else if (p.index != -1 && p.type.equals("txtList")) {
				return p.txtList.get(p.index);
			} else {
				return p.getValue().toString();
			}
		}
		return "";
	}

	//prints the concatenated string statment
	public void print(String str) {
		if (str != null) {
			System.out.println(str);
		} else {
			System.out.println("null");
		}
	}

	//copies the values of a primitive to a newly created Primitive
	public Primitive copyPrimitive(Primitive source) {
		Primitive destination = new Primitive();
		destination.index = source.index;
		if (source.type.equals("num")) {
			destination.type = "num";
			destination.num = source.num;
		} else if (source.type.equals("txt")) {
			destination.type = "txt";
			destination.txt = source.txt;
		} else if (source.type.equals("numList")) {
			destination.type = "numList";
			destination.numList.clear();
			destination.numList.addAll(source.numList);
		} else if (source.type.equals("txtList")) {
			destination.type = "txtList";
			destination.txtList.clear();
			destination.txtList.addAll(source.txtList);
		} else if (source.type.equals("bool")) {
			destination.type = "bool";
			destination.bool = source.bool;
		}
		return destination;
	}

	//does multiplication or division on a primitive, used by grammar rule multExpr
	public void doMultDivOp(Primitive source, Primitive destination, String op) {
		if (source.type.equals("num")) {
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
	public void doMinPlusOp(Primitive source, Primitive destination, String op) {
		if (op.equals("+")) {
			if (source.type.equals("num")) {
				destination.num += source.num;
			} else if (source.type.equals("numList")) {
				destination.numList.addAll(source.numList);
			} else if (source.type.equals("txtList")) {
				destination.txtList.addAll(source.txtList);
			} else if (source.type.equals("txt")) {
				destination.txt += source.txt;
			}
		} else if (op.equals("-")) {
			if (source.type.equals("num")) {
				destination.num -= source.num;
			}
		}
	}

	//method used to collect individual members in declaration, used by grammar rule list
	public void addToListPrim(Primitive p, Primitive listPrim) {
		if (p.type.equals(typeList)) {
			if (typeList.equals("txt")) {
				listPrim.txtList.add(p.txt);
			} else if(typeList.equals("num")) {
				listPrim.numList.add(p.num);
			}
		} else {
			printError("Type mismatch. List type is " + typeList + " and instantiated primitive type is " + p.type);
		}
	}

	//validates if expression associated with rules on devices, used by the grammar rule ifStatement
	public boolean validateIfStatement(ArrayList<Device> dList) {
		boolean c;
		for (int i = 0; i < dList.size(); i++) {
			c = AssertRule(dList.get(i), assertionList, assertStatement, false, true);
			if (!c) {
				ifValueStack.push("0");
				return false;
			}
		}
		ifValueStack.push("1");
		return true;
	}

	public boolean evaluateRelationalExpression(ArrayList<Primitive> list) {
		if (list.get(0).type.equals("num")) {
			return processNums(list);
		} else if (list.get(0).type.equals("txt")) {
			return processTxt(list);
		} else if (list.get(0).type.equals("bool")) {
			return processBool(list);
		} else if (list.get(0).type.equals("txtList") || list.get(0).type.equals("numList")) {
			return processList(list);
		}
		return false;
	}

	public boolean processNums(ArrayList<Primitive> list) {
		if (list.get(list.size()-1).name.equals("<")) {
			return compareLessThan(list.get(0).num, list.get(1).num);
		} else if (list.get(list.size()-1).name.equals(">")) {
			return compareGreaterThan(list.get(0).num, list.get(1).num);
		} else if (list.get(list.size()-1).name.equals("<=")) {
			return (!compareGreaterThan(list.get(0).num, list.get(1).num));
		} else if (list.get(list.size()-1).name.equals(">=")) {
			return (!compareLessThan(list.get(0).num, list.get(1).num));
		} else if (list.get(list.size()-1).name.equals("==")) {
			return compareEquals(list.get(0).num, list.get(1).num);
		} else if (list.get(list.size()-1).name.equals("!=")) {
			return (!compareEquals(list.get(0).num, list.get(1).num));
		}
		return false;
	}

	public boolean processTxt(ArrayList<Primitive> list) {
		if (list.get(list.size()-1).name.equals("==")) {
			return list.get(0).txt.equals(list.get(1).txt);
		} else if (list.get(list.size()-1).name.equals("!=")) {
			return !(list.get(0).txt.equals(list.get(1).txt));
		} else if (list.get(list.size()-1).name.equals("<")) {
			if (list.get(0).txt.compareTo(list.get(1).txt) < 0) {
				return true;
			} else {
				return false;
			}
		} else if (list.get(list.size()-1).name.equals(">")) {
			if (list.get(0).txt.compareTo(list.get(1).txt) > 0) {
				return true;
			} else {
				return false;
			}
		} else if (list.get(list.size()-1).name.equals(">=")) {
			if (list.get(0).txt.compareTo(list.get(1).txt) >= 0) {
				return true;
			} else {
				return false;
			}
		} else if (list.get(list.size()-1).name.equals("<=")) {
			if (list.get(0).txt.compareTo(list.get(1).txt) <= 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean processBool(ArrayList<Primitive> list) {
		if (list.get(list.size()-1).name.equals("==")) {
			if (list.get(0).bool == list.get(1).bool) {
				return true;
			} else {
				return false;
			}
		} else if (list.get(list.size()-1).name.equals("!=")) {
			if (list.get(0).bool != list.get(1).bool) {
				return true;
			} else {
				return false;
			}
		} else if (list.size() == 1) {
			if (list.get(0).bool) {
				return true;
			} else {
				return false;
			}
		} else {
			printError("Cannot evaluate for boolean types this operator " + list.get(list.size()-1).name);
		}
		return false;
	}

	public boolean processList(ArrayList<Primitive> list) {
		if (list.get(list.size()-1).name.equals("==")) {
			return compareEqualsLists(list);
		} else if (list.get(list.size()-1).name.equals("!=")) {
			return !compareEqualsLists(list);
		}
		return false;
	}

	public boolean compareLessThan(double num1, double num2) {
		if (num1 < num2) {
			return true;
		}
		return false;
	}

	public boolean compareGreaterThan(double num1, double num2) {
		if (num1 > num2) {
			return true;
		}
		return false;
	}

	public boolean compareEquals(double num1, double num2) {
		if (num1 == num2) {
			return true;
		}
		return false;
	}

	public boolean compareEqualsLists(ArrayList<Primitive> list) {
		if (list.get(0).type.equals("txtList")) {
			if (list.get(0).txtList.size() != list.get(1).txtList.size()) {
				return false;
			}
			for (int i = 0; i < list.get(0).txtList.size(); i++) {
				if (!list.get(0).txtList.get(i).equals(list.get(1).txtList.get(i))) {
					return false;
				}
			}
			return true;
		} else if (list.get(0).type.equals("numList")) {
			if (list.get(0).numList.size() != list.get(1).numList.size()) {
				return false;
			}
			for (int i = 0; i < list.get(0).numList.size(); i++) {
				if (!list.get(0).numList.get(i).equals(list.get(1).numList.get(i))) {

					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean evaluateExpression(boolean result1, boolean result2, String logicalOperator) {
		if (logicalOperator.equals("AND")) {
			return (result1 && result2);
		} else if (logicalOperator.equals("OR")) {
			return (result1 || result2);
		}
		return false;
	}

	public boolean validateIfStatementExp() {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < ifEvaluationList.size(); i++) {
			if (ifEvaluationList.get(i).name.equals("operand")) {
				stack.push(ifEvaluationList.get(i).txt);
			} else {
				String operator = ifEvaluationList.get(i).txt;
				String op1 = stack.pop();
				if (operator.equals("NOT")) {
					if (op1.equals("1")) {
						stack.push("0");
					} else {
						stack.push("1");
					}
				} else {
					String op2 = stack.pop();
					if (operator.equals("AND")) {
						if (op1.equals("1") && op2.equals("1")) {
							stack.push("1");
						} else {
							stack.push("0");
						}
					} else if (operator.equals("OR")) {
						if (op1.equals("0") && op2.equals("0")) {
							stack.push("0");
						} else {
							stack.push("1");
						}
					}
				}
			}
		}
		String check = stack.pop();
		if (check.equals("1")) {
			return true;
		}
		return false;
	}

	public boolean applyRulesToDevicesPermute(Device deviceInst, String degree) {
		HashMap<String, ArrayList<Primitive>> ruleMap = new HashMap<String, ArrayList<Primitive>>();
		boolean ruleMet = true, note = true;
		
		if(degree.equals("strict")) {
			ruleMap = ruleAssertions;
			note = false;
		} else if(degree.equals("flexible")) {
			ruleMap = ruleNotes;
		} else
			return false;
		
		Set<String> setKeys = ruleMap.keySet();
		
		Iterator<String> it = setKeys.iterator();
		String key = "";
		
		while(it.hasNext()) {
			key = it.next();
			ruleMet = AssertRule(deviceInst, ruleMap.get(key), key, note, true);
			if(!ruleMet)
				return false;
		}
		return true;
	}
	public void permute(String device, int cap, String degree) {
		ArrayList<String> compList = deviceDeclarations.get(device).components;
		ArrayList<ArrayList<String>> compPool = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> variations = new ArrayList<ArrayList<String>>();
		int compListSize = compList.size();
		int compPoolSize;
		int variationsSize = 1;
		ArrayList<Integer> restSizes = new ArrayList<Integer>();
		int prevSize;
		
		for (int i = 0; i < compListSize; i++) {
			compPool.add(new ArrayList<String>());
			if (partDeclarations.containsKey(compList.get(i))) {
				compPool.get(i).addAll(partDefinitions.get(partDeclarations.get(compList.get(i)).type).get(2));
				variationsSize *= compPool.get(i).size();
			} else {
				compPool.get(i).add(compList.get(i));
			}
		}
		
		prevSize = variationsSize;
		
		for (int i = 0; i < compListSize; i++) {
			restSizes.add(new Integer(prevSize/compPool.get(i).size()));
			prevSize = restSizes.get(i);
		}
		for (int i = 0; i < variationsSize; i++) {
			variations.add(new ArrayList<String>());
		}
		compPoolSize = compPool.size();
		for (int i = 0; i < variationsSize; i++) {
			for (int j = 0; j < compPoolSize; j++) {
				variations.get(i).add(j, compPool.get(j).get((i/restSizes.get(j))\%compPool.get(j).size()));
			}
		}
		if(cap == -1) {//want all devices that are either are either not "stricly" violating, "flexible violating" or neither violating rules
			if(degree.equals("") || degree.equals("strict") || degree.equals("flexible")) {
				findDegreePermutations(device, degree, variationsSize, variations);
			} else {
				printError(degree + " is not a valid input of measuring degree for function permute.");
			}
		} else if(cap >= 0) {//want cap number of devices that are either not "stricly" violating, "flexible violating" or neither violating rules
			if(degree.equals("") || degree.equals("strict") || degree.equals("flexible")) {
				findDegreeCapPermutations(device, degree, cap, variationsSize, variations);
			} else {
				printError(degree + " is not a valid input of measuring degree for function permute.");
			}
		}
	}

	public void findDegreePermutations(String device, String degree, int variationsSize, ArrayList<ArrayList<String>> variations) {
        Device d;
		int count = 1;

		for (int i = 1; i <= variationsSize; i++) {
			d = new Device();
			d.type = "Device";
			d.components.addAll(variations.get(i-1));
			if (deviceDeclarations.containsKey(d.name)) {
				printError(d.name + " already exists.");
			}
			if((!degree.equals("") && applyRulesToDevicesPermute(d, degree)) || degree.equals("")) {
					d.name = device + "_" + Integer.toString(count);
					printDebug(d.name + " " + d.components);
					deviceDeclarations.put(d.name, d);
					count++;
			} 
		}
	}
	
	public void findDegreeCapPermutations(String device, String degree, int cap, int variationsSize, ArrayList<ArrayList<String>> variations) {
		Device d;
		Random randomGenerator = new Random();
		int randomNum, i=1, loopcheck = 0;
		
		ArrayList<String> checkList = new ArrayList<String>();
		for(int j=0; j < variationsSize; j++) {
			checkList.add("0");
		}
		
		while(i <= cap) {
			d = new Device();
			d.name = device + "_" + Integer.toString(i);
			d.type = "Device";
			randomNum = randomGenerator.nextInt(variationsSize);
			d.components.addAll(variations.get(randomNum));
					
			if(checkList.get(randomNum).equals("1") || (!degree.equals("") && !applyRulesToDevicesPermute(d, degree))) {
				checkList.set(randomNum, "1");
				loopcheck++;
				if(loopcheck >= cap) {
					if(checkList.indexOf("0") == -1) {//means have looked at all variations
						break;
					}
				}
				continue;
			} else {
				checkList.set(randomNum, "1");
				if (deviceDeclarations.containsKey(d.name)) {
					printError(d.name + " already exists.");
				}
				deviceDeclarations.put(d.name, d);
				i++;
				printDebug(d.name + " " + d.components);
					
			}
						
		}
	}
	
	public String printDevices(Device d, int amount) {
		int count = 1;
		boolean all = false;
		String str = "";
		
		if(amount == -1)
			all = true;
			
		while(count <= amount || all) {
			String deviceName = d.name + "_" + Integer.toString(count);
			if(deviceDeclarations.containsKey(deviceName)){
				Device dPermuted = deviceDeclarations.get(deviceName);
				str += dPermuted.name + " " + dPermuted.components + "\n";
				count++;
			} else
				break;
		}
		
		return str;
	
	}
	
	
	public void printDebug(Object message) {
		if (debug) {
			int line = input.LT(-1).getLine();
			System.out.println("@Debug Line " + line + ": " + message);
		}
	}

	public void printError(Object message) {
		int line = input.LT(-1).getLine();
		throw new IllegalArgumentException("@Error Line " + line + ": " + message);
	}
}

@lexer::header {
	//package eugene;
}

@lexer::members {
	@Override
	public void reportError(RecognitionException RecogEx) {
		int line = getLine();
		throw new IllegalArgumentException("@Error Line " + line + ": " + "Syntax error.");
	}
}

@rulecatch {
	catch (RecognitionException RecogEx) {
		int line = input.LT(-1).getLine();
		throw new IllegalArgumentException("@Error Line " + RecogEx.line + ": " + "Syntax error.");
	}
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

/* start of program consisting of statements */
prog
	:	(statement)+ EOF!
	;

/* statements can be declaration of new classes, instances, assignments, print and if statements */
statement
	:	decl /* declaration of new classes */
	|	declObjects /* declaration of instances from the created classes */
	|	NUM numdecl SEMIC /* declaration of nums, which can be both real and integer*/
	|	TXT txtdecl SEMIC /* declaration of text instances*/
	|	TXT LEFTSBR RIGHTSBR txtlistdecl SEMIC /*  declaration of text lists */
	|	NUM LEFTSBR RIGHTSBR numlistdecl SEMIC /* declaration of num lists */
	|	BOOLEAN booldecl SEMIC /* declaration of boolean instances*/
	|	print /* prints a concatened string from rule print*/
		{
			if (!ifValueStack.empty()){
				if (ifValueStack.peek() == "1") {
					print($print.concat);
				}
			} else {
				print($print.concat);
			}
		}
	|	permute
	|	leftval=atom EQUALS rightval=expr SEMIC /* assigns the value expr to the address from atom*/
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					valueAssignment($leftval.primVariable, $leftval.index, $rightval.p);
				}
			} else {
				valueAssignment($leftval.primVariable, $leftval.index, $rightval.p);
			}
		}
	|	ifStatement /* if statement that can evaluate rule like expressions and regular if expressions */
	;

/* declaration of new classes */
decl
	:	PROPERTY LETTER LEFTP TXT RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					defineProperty("txt", $LETTER.text);
				}
			} else {
				defineProperty("txt", $LETTER.text);
			}
		}
	|	PROPERTY LETTER LEFTP TXT LEFTSBR RIGHTSBR RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					defineProperty("txtList", $LETTER.text);
				}
			} else {
				defineProperty("txtList", $LETTER.text);
			}
		}
	|	PROPERTY tn=LETTER LEFTP NUM RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					defineProperty("num", $LETTER.text);
				}
			} else {
				defineProperty("num", $LETTER.text);
			}
		}
	|	PROPERTY LETTER LEFTP NUM LEFTSBR RIGHTSBR RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					defineProperty("numList", $LETTER.text);
				}
			} else {
				defineProperty("numList", $LETTER.text);
			}
		}
	|	PROPERTY LETTER LEFTP BOOLEAN RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					defineProperty("bool", $LETTER.text);
				}
			} else {
				defineProperty("bool", $LETTER.text);
			}
		}
	|	PART LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					definePart($LETTER.text);
				}
			} else {
				definePart($LETTER.text);
			}
		} LEFTP propertyList RIGHTP SEMIC
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						instPropertyList($LETTER.text);
					}
				} else {
					instPropertyList($LETTER.text);
				}
			}
	|	DEVICE LETTER LEFTP componentList RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					instDevice($LETTER.text);
					applyRulesToDevice($LETTER.text);
				}
			} else {
				instDevice($LETTER.text);
				applyRulesToDevice($LETTER.text);
			}
		}
	|	IMAGE LEFTP name=LETTER COMMA path=STRING RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					bindImage($name.text, $path.text);
				}
			} else {
				bindImage($name.text, $path.text);
			}
		}
	|	RULE name=LETTER LEFTP (leftOp=LETTER | leftOpP=exprP |) operator (rightOp=LETTER | rightOpP=exprP) RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					checkToDeclareRule($leftOpP.primVariable, $leftOp.text, $rightOpP.primVariable, $rightOp.text, $name.text, $leftOpP.inst, $rightOpP.inst, $operator.text);
				}
			} else {
				checkToDeclareRule($leftOpP.primVariable, $leftOp.text, $rightOpP.primVariable, $rightOp.text, $name.text, $leftOpP.inst, $rightOpP.inst, $operator.text);
			}
		}
	|	NOTE LEFTP
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					clearAssertionList();
				}
			} else {
				clearAssertionList();
			}
		} statementsOfAssertion RIGHTP SEMIC
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						storeAssertList(false);
					}
				} else {
					storeAssertList(false);
				}
			}
	|	ASSERT LEFTP
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					clearAssertionList();
				}
			} else {
				clearAssertionList();
			}
		} statementsOfAssertion RIGHTP SEMIC
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						storeAssertList(true);
					}
				} else {
					storeAssertList(true);
				}
			}
	|	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					isPartDefined($LETTER.text);
				}
			} else {
				isPartDefined($LETTER.text);
			}
		} pfunctions
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						instPropertyList($LETTER.text);
					}
				} else {
					instPropertyList($LETTER.text);
				}
			}
	;

operator
	:	ruleOperators
	|	relationalOperators
	|	logicalOperators
	;

ruleOperators
	:	BEFORE
	|	AFTER
	|	WITH
	|	NOTWITH
	|	NEXTTO
	|	NOTCONTAINS
	|	CONTAINS
	|	NOTMORETHAN
	;

relationalOperators
	:	EQUALS EQUALS
	|	NEQUAL
	|	LTHAN
	|	GTHAN
	|	LEQUAL
	|	GEQUAL
	;

logicalOperators
	:	AND
	|	NOT
	|	OR
	;

statementsOfAssertion
	:	(s1=statmnts
		{
			if (!ifValueStack.empty()){
				if (ifValueStack.peek() == "1") {
					addToAssertionList($s1.op, "operand");
				}
			} else {
				addToAssertionList($s1.op, "operand");
			}
		} | ) (operator
			{
				if (!ifValueStack.empty()) {
					if(ifValueStack.peek() == "1") {
						addToAssertStatement(" " + $operator.text + " ");
					}
				} else {
					addToAssertStatement(" " + $operator.text + " ");
				}
			} s2=statmnts
				{
					if (!ifValueStack.empty()) {
						if (ifValueStack.peek() == "1") {
							addToAssertionList($s2.op, "operand");
							addToAssertionList($operator.text, "operator");
						}
					} else {
						addToAssertionList($s2.op, "operand");
						addToAssertionList($operator.text, "operator");
					}
				} )*
	;

statmnts returns [String op]
	:	LEFTP { addToAssertStatement("("); } statementsOfAssertion RIGHTP { addToAssertStatement(")"); }
	|	LETTER { $op = getAssertStatementOperand($LETTER.text); }
	;

ifStatement returns [boolean check = true]
	:	IF LEFTP (stmts=statementsOfAssertionExpr | exp=expression) RIGHTP LEFTCUR
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if ($stmts.text != null) {
						$check = validateIfStatement($stmts.dList);
					} else {
						$check = validateIfStatementExp();
						if ($check) {
							ifValueStack.push("1");
						} else {
							ifValueStack.push("0");
						}
					}
					evaluate.push("1");
				} else {
					evaluate.push("0");
				}
			} else if (ifValueStack.empty()) {
				if ($stmts.text != null) {
					$check = validateIfStatement($stmts.dList);
				} else {
					$check = validateIfStatementExp();
					if ($check) {
						ifValueStack.push("1");
					} else {
						ifValueStack.push("0");
					}
				}
				if ($check) {
					evaluate.push("1");
				} else {
					evaluate.push("0");
				}
			}
		} (statement)* (RIGHTCUR
			{
				String str = evaluate.pop();
				if (str.equals("1") || evaluate.size() == 0) {
					ifValueStack.pop();
				}
			} (ELSE LEFTCUR) => ELSE LEFTCUR
				{
					if (!ifValueStack.empty()) {
						if (ifValueStack.peek() == "1") {
							if ($check) {
								ifValueStack.add("0");
							} else {
								ifValueStack.add("1");
							}
							evaluate.push("1");
						} else {
							evaluate.push("0");
						}
					} else {
						if ($check){
							ifValueStack.add("0");
							evaluate.push("1");
						} else {
							ifValueStack.add("1");
							evaluate.push("0");
						}
					}
                } (statement)*
				| ) RIGHTCUR
					{
						String str = evaluate.pop();
						if (str.equals("1") || evaluate.size() == 0) {
							ifValueStack.pop();
						}
					}
	;

statementsOfAssertionExpr returns [ArrayList<Device> dList]
	:	ON LEFTP deviceList RIGHTP {$dList = $deviceList.dList;} statementsOfAssertion
	;

expression returns[boolean result1, boolean result2, boolean finalResult]
	:	(re1=relationalExpression | )
		{
			if ($re1.text != null && $re1.list.size() != 0) {
				$result1 = evaluateRelationalExpression($re1.list);
				Primitive prim = new Primitive("operand", "txt");
				if ($result1) {
					prim.txt = "1";
				} else {
					prim.txt = "0";
				}
				ifEvaluationList.add(prim);
			}
		} ((logicalOperators) re2=relationalExpression
			{
				if ($re2.list.size() != 0) {
					$result2 = evaluateRelationalExpression($re2.list);
					Primitive prim = new Primitive("operand", "txt");
					if ($result2) {
						prim.txt = "1";
					} else {
						prim.txt = "0";
					}
					ifEvaluationList.add(prim);
					Primitive prim2 = new Primitive("operator", "txt");
					prim2.txt = $logicalOperators.text;
					ifEvaluationList.add(prim2);
				}
			} )*
	;

relationalExpression returns[ArrayList<Primitive> list = new ArrayList<Primitive>()]
 	:	(e=expr | )
 		{
			if ($e.p != null) {
				$list.add($e.p);
			}
 		} ((relationalOperators) f=expr
			{
				if ($f.p != null) {
					if ($f.p.type.equals($list.get(0).type)) {
						$list.add($f.p);
					} else {
						printError("Type mismatch in relational expression in if statement.\n");
					}
				}
				Primitive pOp = new Primitive($relationalOperators.text, "operator");
				$list.add(pOp);
			} )*
	|	LEFTP expression RIGHTP
	;

deviceList returns [ArrayList<Device> dList = new ArrayList<Device>()]
	:	name=LETTER
		{
			if (deviceDeclarations.containsKey($name.text)) {
				$dList.add(deviceDeclarations.get($name.text));
			}

		} (COMMA name=LETTER
			{
				if (deviceDeclarations.containsKey($name.text)) {
					$dList.add(deviceDeclarations.get($name.text));
				}
			} )*
	;

numdecl
	:	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveNoValue($LETTER.text, "num");
				}
			} else {
				declarePrimitiveNoValue($LETTER.text, "num");
			}
		} (COMMA numdecl)*
	|	LETTER EQUALS (ex=expr)
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveWithValueNum($LETTER.text, $ex.p, $ex.index);
				}
			} else {
				declarePrimitiveWithValueNum($LETTER.text, $ex.p, $ex.index);
			}
		}  (COMMA numdecl)*
	;

txtdecl
	:	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveNoValue($LETTER.text, "txt");
				}
			} else {
				declarePrimitiveNoValue($LETTER.text, "txt");
			}
		} (COMMA txtdecl)*

	|	var=LETTER EQUALS let=expr
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveWithValueTxt($var.text, $let.p, $let.index);
				}
			} else {
				declarePrimitiveWithValueTxt($var.text, $let.p, $let.index);
			}
		} (COMMA txtdecl)*
	;

txtlistdecl
	:	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveNoValue($LETTER.text, "txtList");
				}
			} else {
				declarePrimitiveNoValue($LETTER.text, "txtList");
			}
		} (COMMA txtlistdecl)*
	|	var=LETTER EQUALS {typeList = "txt";} let=expr
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveWithValueTxtList($var.text, $let.p);
				}
			} else {
				declarePrimitiveWithValueTxtList($var.text, $let.p);
			}
		} (COMMA txtlistdecl)*
	;

numlistdecl
	:	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveNoValue($LETTER.text, "txtList");
				}
			} else {
				declarePrimitiveNoValue($LETTER.text, "txtList");
			}
		} (COMMA numlistdecl)*
	|	var=LETTER EQUALS { typeList = "num";}let=expr
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveWithValueNumList($var.text, $let.p);
				}
			} else {
				declarePrimitiveWithValueNumList($var.text, $let.p);
			}
		} (COMMA numlistdecl)*
	;

booldecl
	:	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveNoValue($LETTER.text, "bool");
				}
			} else {
				declarePrimitiveNoValue($LETTER.text, "bool");
			}
		} (COMMA booldecl)*
	|	var=LETTER EQUALS let=expr
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declarePrimitiveWithValueBool($var.text, $let.p);
				}
			} else {
				declarePrimitiveWithValueBool($var.text, $let.p);
			}
		}
	;

propertyList
	:	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					propertyListHolder.add($LETTER.text);
				}
			} else {
				propertyListHolder.add($LETTER.text);
			}
		} COMMA propertyList
	|	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					propertyListHolder.add($LETTER.text);
				}
			} else {
				propertyListHolder.add($LETTER.text);
			}
		}
	|
	;

componentList
	:	(instJust1=LETTER | class1=LETTER inst1=LETTER | device=DEVICE instDev=LETTER)
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if ($instJust1 != null) {
						addToComponentList($instJust1.text);
					} else if ($inst1 != null) {
						if (partDeclarations.get($inst1.text).type.equals($class1.text)) {
							addToComponentList($inst1.text);
						} else {
							printError("Instance " + $inst1.text + " is not a " + $class1.text + " but a " + partDeclarations.get($inst1.text).type);
						}
					} else if ($instDev != null) {
						if(deviceDeclarations.containsKey($instDev.text)) {
							addToComponentList($instDev.text);
						}
					}
				}
			} else {
				if ($instJust1 != null) {
					addToComponentList($instJust1.text);
				} else if ($inst1 != null) {
					if (partDeclarations.get($inst1.text).type.equals($class1.text)) {
						addToComponentList($inst1.text);
					} else {
						printError("Instance " + $inst1.text + " is not a " + $class1.text + " but a " + partDeclarations.get($inst1.text).type);
					}
				} else if ($instDev != null) {
						if(deviceDeclarations.containsKey($instDev.text)) {
							addToComponentList($instDev.text);
						}
				}
			}
		} COMMA componentList
	|	(instJust2=LETTER | class2=LETTER inst2=LETTER | device2=DEVICE instDev2=LETTER)
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if ($instJust2 != null) {
						addToComponentList($instJust2.text);
					} else if ($inst2 != null) {
						if (partDeclarations.get($inst2.text).type.equals($class2.text)) {
							addToComponentList($inst2.text);
						} else {
							printError("Instance " + $inst2.text + " is not a " + $class2.text + " but a " + partDeclarations.get($inst2.text).type);
						}
					} else if ($instDev2 != null) {
						if(deviceDeclarations.containsKey($instDev2.text)) {
							addToComponentList($instDev2.text);
						}
					}
				}
			} else {
				if ($instJust2 != null) {
					addToComponentList($instJust2.text);
				} else if ($inst2 != null) {
					if (partDeclarations.get($inst2.text).type.equals($class2.text)) {
						addToComponentList($inst2.text);
					} else {
						printError("Instance " + $inst2.text + " is not a " + $class2.text + " but a " + partDeclarations.get($inst2.text).type);
					}
				} else if ($instDev2 != null) {
						if(deviceDeclarations.containsKey($instDev2.text)) {
							addToComponentList($instDev2.text);
						}
				}
			}
		}
	|
		{
			if (!ifValueStack.empty()){
				if (ifValueStack.peek() == "1") {
					compListInside = true;
				}
			} else {
				compListInside = true;
			}
		} (declObjects)*
	;

pfunctions
	:	DOT ADDPROPS LEFTP propertyList RIGHTP SEMIC
	;

declObjects
	:	classname=LETTER varname=LETTER LEFTP
		{
			partType = $classname.text;
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						declareInstances($classname.text, $varname.text);
						if (compListInside) {
							addToComponentList($varname.text);
						}
					}
				} else {
					declareInstances($classname.text, $varname.text);
					if (compListInside) {
						addToComponentList($varname.text);
					}
				}
		} propertyListInst
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						if (propertyValuesHolder.size() != 0) {
							setProperties($classname.text, $varname.text);
						}
					}
				} else {
					if (propertyValuesHolder.size() != 0) {
						setProperties($classname.text, $varname.text);
					}
				}
			} RIGHTP SEMIC
	|	classname=LETTER varname=LETTER LEFTP
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					declareInstances($classname.text, $varname.text);
					if (compListInside) {
						addToComponentList($varname.text);
					}
				}
			} else {
				declareInstances($classname.text, $varname.text);
				if (compListInside) {
					addToComponentList($varname.text);
				}
			}
		} propertyListInstDot
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						setPropertiesDot($classname.text, $varname.text);
					}
				} else {
					setPropertiesDot($classname.text, $varname.text);
				}
			} RIGHTP SEMIC
	;

propertyListInstDot
	:	DOT prop=LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					addToPropertyListHolder($prop.text);
				}
			} else {
				addToPropertyListHolder($prop.text);
			}
		} LEFTP v1=expr
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						addToPropertyValuesHolder($prop.text, $v1.p, $v1.index);
					}
				} else {
					addToPropertyValuesHolder($prop.text, $v1.p, $v1.index);
				}
			} RIGHTP (COMMA DOT p=LETTER
				{
					if (!ifValueStack.empty()) {
						if (ifValueStack.peek() == "1") {
							addToPropertyListHolder($p.text);
						}
					} else {
						addToPropertyListHolder($p.text);
					}
				} LEFTP v2=expr
					{
						if (!ifValueStack.empty()) {
							if (ifValueStack.peek() == "1") {
								addToPropertyValuesHolder($p.text, $v2.p, $v2.index);
							}
						} else {
							addToPropertyValuesHolder($p.text, $v2.p, $v2.index);
						}
					} RIGHTP)*
	;

propertyListInst
	:
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					ArrayList<String> propertyList = partDefinitions.get(partType).get(0);
					if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("txtList")) {
						typeList = "txt";
					} else if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("numList")) {
						typeList = "num";
					}
				}
			} else {
				ArrayList<String> propertyList = partDefinitions.get(partType).get(0);
				if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("txtList")) {
					typeList = "txt";
				} else if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("numList")) {
					typeList = "num";
				}
			}
		} val1=expr
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						propertyValuesHolder.add($val1.p);
					}
				} else {
					propertyValuesHolder.add($val1.p);
				}
			} (COMMA
				{
					if (!ifValueStack.empty()) {
						if (ifValueStack.peek() == "1") {
							ArrayList<String> propertyList = partDefinitions.get(partType).get(0);
							if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("txtList")) {
								typeList = "txt";
							} else if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("numList")) {
								typeList = "num";
							}
						}
					} else {
						ArrayList<String> propertyList = partDefinitions.get(partType).get(0);
						if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("txtList")) {
							typeList = "txt";
						} else if (propertyDefinitions.get(propertyList.get(propertyValuesHolder.size())).equals("numList")) {
							typeList = "num";
						}
					}
               } val2=expr
					{
						if (!ifValueStack.empty()) {
							if (ifValueStack.peek() == "1") {
								propertyValuesHolder.add($val2.p);
							}
						} else {
							propertyValuesHolder.add($val2.p);
						}
					} )*
	|
	;

print returns [String concat]
	:	PRINT LEFTP (val=expr | )
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$concat = setStatementPrint($val.p);
				}
			} else {
				$concat = setStatementPrint($val.p);
			}
		} (COMMA val2=expr
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						if ($val2.p != null) {
							if ($val2.p.index != -1 && $val2.p.type.equals("txtList") && $val2.p.txtList.size() != 0) {
								$concat += $val2.p.txtList.get($val2.p.index);
							} else if ($val2.p.index != -1 && $val2.p.type.equals("numList") && $val2.p.numList.size() != 0) {
								$concat += $val2.p.numList.get($val2.p.index).toString();
							} else {
								$concat += $val2.p.getValue().toString();
							}
						}
					}
				} else {
					if ($val2.p != null) {
						if ($val2.p.index != -1 && $val2.p.type.equals("txtList") && $val2.p.txtList.size() != 0) {
							$concat += $val2.p.txtList.get($val2.p.index);
						} else if($val2.p.index != -1 && $val2.p.type.equals("numList") && $val2.p.numList.size() != 0) {
							$concat += $val2.p.numList.get($val2.p.index).toString();
						} else {
							$concat += $val2.p.getValue().toString();
						}
					}
				}

			} )* RIGHTP SEMIC
			| device=LETTER '.' PRINT LEFTP (numVar=LETTER | num=NUMBER | ) RIGHTP SEMIC
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						if(deviceDeclarations.containsKey($device.text)) {
							if($numVar.text != null) {
								if(primitiveDeclarations.containsKey($numVar.text)) {
									int amount = (int)primitiveDeclarations.get($numVar.text).num;
									$concat = printDevices(deviceDeclarations.get($device.text), amount);
								} else
									printError($numVar.text + " has not been declared as a primitive.");
							} else if($num.text != null) {
								$concat = printDevices(deviceDeclarations.get($device.text), Integer.parseInt($num.text));
							} else
								$concat = printDevices(deviceDeclarations.get($device.text), -1);
						} else 
							printError($device.text + " has not been declared as a Device.");
					}
				} else {
					if(deviceDeclarations.containsKey($device.text)) {
						if($numVar.text != null) {
							if(primitiveDeclarations.containsKey($numVar.text)) {
								int amount = (int)primitiveDeclarations.get($numVar.text).num;
								$concat = printDevices(deviceDeclarations.get($device.text), amount);
							} else
								printError($numVar.text + " has not been declared as a primitive.");
						} else if($num.text != null) {
							$concat = printDevices(deviceDeclarations.get($device.text), Integer.parseInt($num.text));
						} else
							$concat = printDevices(deviceDeclarations.get($device.text), -1);
					} else 
						printError($device.text + " has not been declared as a Device.");
					
				}
			}
	;

permute
	:	PERMUTE LEFTP device=LETTER RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if (!deviceDeclarations.containsKey($device.text)) {
						printError("Device " + $device.text + " does not exist.");
					} else {
						permute($device.text, -1, "");
					}
				}
			} else {
					if (!deviceDeclarations.containsKey($device.text)) {
						printError("Device " + $device.text + " does not exist.");
					} else {
						permute($device.text, -1, "");
					}
			}
		}
		| PERMUTE LEFTP device=LETTER COMMA (num=NUMBER | numVar=LETTER)  RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if (!deviceDeclarations.containsKey($device.text)) {
						printError("Device " + $device.text + " does not exist.");
					} else {
						if($num.text != null)
							permute($device.text, Integer.parseInt($num.text), "");
						else if($numVar.text != null) { 
							if(primitiveDeclarations.containsKey($numVar.text)) {
								int cap = (int)primitiveDeclarations.get($numVar.text).num;
								permute($device.text, cap, "");
							} else
								printError("Variable " + $numVar.text + " has not been declared as a primitive type.");
						}
					}
				}
			} else {
					if (!deviceDeclarations.containsKey($device.text)) {
						printError("Device " + $device.text + " does not exist.");
					} else {
						if($num.text != null)
							permute($device.text, Integer.parseInt($num.text), "");
						else if($numVar.text != null) {
							if(primitiveDeclarations.containsKey($numVar.text)) {
								int cap = (int)primitiveDeclarations.get($numVar.text).num;
								permute($device.text, cap, "");
							} else
								printError("Variable " + $numVar.text + " has not been declared as a primitive type.");
							
						}
					}
			}
		}
		| PERMUTE LEFTP device=LETTER COMMA (num=NUMBER | numVar=LETTER) COMMA degree=(STRICT | FLEXIBLE) RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if (!deviceDeclarations.containsKey($device.text)) {
						printError("Device " + $device.text + " does not exist.");
					} else {
						if($num.text != null)
							permute($device.text, Integer.parseInt($num.text), $degree.text);
						else if($numVar.text != null) {
							if(primitiveDeclarations.containsKey($numVar.text)) {
								int cap = (int)primitiveDeclarations.get($numVar.text).num;
								permute($device.text, cap, $degree.text);
							} else
								printError("Variable " + $numVar.text + " has not been declared as a primitive type.");
						}
					}
				}
			} else {
					if (!deviceDeclarations.containsKey($device.text)) {
						printError("Device " + $device.text + " does not exist.");
					} else {
						if($num.text != null)
							permute($device.text, Integer.parseInt($num.text), $degree.text);
						else if($numVar.text != null) {
							if(primitiveDeclarations.containsKey($numVar.text)) {
								int cap = (int)primitiveDeclarations.get($numVar.text).num;
								permute($device.text, cap, $degree.text);
							} else
								printError("Variable " + $numVar.text + " has not been declared as a primitive type.");
						}
					}
			}
		}
		| PERMUTE LEFTP device=LETTER COMMA degree=(STRICT | FLEXIBLE) RIGHTP SEMIC
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if (!deviceDeclarations.containsKey($device.text)) {
						printError("Device " + $device.text + " does not exist.");
					} else {
						
						permute($device.text, -1 , $degree.text);
					}
				}
			} else {
				if (!deviceDeclarations.containsKey($device.text)) {
					printError("Device " + $device.text + " does not exist.");
				} else {
					permute($device.text, -1 , $degree.text);
				}
			}
		}
	;

exprP returns [Primitive p, String inst, int index, String listAddress, Primitive primVariable]
	:	(instance=LETTER|multinst=exprD) DOT property
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$index = $property.index;
					if ($property.listAddress != null) {
						$listAddress = $property.listAddress;
					}
					if ($multinst.inst != null) {
						if (partDeclarations.containsKey($multinst.inst)) {
							$p = new Primitive();
							$primVariable = getPropertyValue($multinst.inst, $property.prop, $p, $property.index);
						} else if (deviceDeclarations.containsKey($multinst.inst)) {
							$p = getDevicePropertyValue($multinst.inst, $property.prop, $property.index);
						}
						$inst = $multinst.inst;
					} else {
						if (partDeclarations.containsKey($instance.text)) {
							$p = new Primitive();
							$primVariable = getPropertyValue($instance.text, $property.prop, $p, $property.index);
						} else if (deviceDeclarations.containsKey($instance.text)) {
							$p = getDevicePropertyValue($instance.text, $property.prop, $property.index);
						}
						$inst = $instance.text;
					}
				}
			} else {
				$index = $property.index;
				if ($property.listAddress != null) {
					$listAddress = $property.listAddress;
				}
				if ($multinst.inst != null) {
					if (partDeclarations.containsKey($multinst.inst)) {
						$p = new Primitive();
						$primVariable = getPropertyValue($multinst.inst, $property.prop, $p, $property.index);
					} else if(deviceDeclarations.containsKey($multinst.inst)) {
						$p = getDevicePropertyValue($multinst.inst, $property.prop, $property.index);
					}
					$inst = $multinst.inst;
				} else {
					if (partDeclarations.containsKey($instance.text)) {
						$p = new Primitive();
						$primVariable = getPropertyValue($instance.text, $property.prop, $p, $property.index);
					} else if (deviceDeclarations.containsKey($instance.text)) {
						$p = getDevicePropertyValue($instance.text, $property.prop, $property.index);
					}
					$inst = $instance.text;
				}
			}
		}
	;

property returns [String prop, int index = -1, String listAddress]
	:	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$prop = $LETTER.text;
					$index = -1;
				}
			} else {
				$prop = $LETTER.text;
				$index = -1;
			}
		}
	|	LETTER LEFTSBR (num=NUMBER|r=REAL) RIGHTSBR
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$prop = $LETTER.text;
					$listAddress = $LETTER.text;
					if ($r != null) {
						printError("Invalid index, must be an integer.");
					} else {
						$index = Integer.parseInt($num.text);
					}
				}
			} else {
				$prop = $LETTER.text;
				$listAddress = $LETTER.text;
				if ($r != null) {
					printError("Invalid index, must be an integer.");
				} else {
					$index = Integer.parseInt($num.text);
				}
			}
		}
	;

//returns the inner most instance, eg d2 = {d1}, d3 = {d2}, d3[0][0].Sequence
exprD returns [String inst]
	:	instance=LETTER {$inst = $instance.text;} ( LEFTSBR (num=NUMBER|r=REAL) RIGHTSBR
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if (deviceDeclarations.containsKey($inst)) {
						ArrayList<String> comp = deviceDeclarations.get($inst).components;
						if (r !=  null) {
							printError("Invalid index.");
						} else {
							int n = Integer.parseInt($num.text);
							if (n > comp.size()) {
								printError("Index out of bounds, index + " + n + " and list size " + comp.size() + ".");
							} else {
								$inst = comp.get(n);
							}
						}
					} else if (partDeclarations.containsKey($inst)) {
						if (r !=  null)
							printError("Invalid index.");
						else {
							$inst = partDeclarations.get($inst).name;
						}
					} else {
						printError("Device " + $instance + " does not exist.");
					}
				}
			} else {
				if (deviceDeclarations.containsKey($inst)) {
					ArrayList<String> comp = deviceDeclarations.get($inst).components;
					if (r !=  null) {
						printError("Invalid index.");
					} else {
						int n = Integer.parseInt($num.text);
						if (n > comp.size()) {
							printError("Index out of bounds, index + " + n + " and list size " + comp.size() + ".");
						} else {
							$inst = comp.get(n);
						}
					}
				} else if (partDeclarations.containsKey($inst)) {
					if (r !=  null) {
						printError("Invalid index.");
					} else {
						$inst = partDeclarations.get($inst).name;
					}
				} else {
					printError("Device " + $instance + " does not exist.");
				}
			}
		} )+
	;

expr returns [Primitive p,  String instance, int index, String listAddress, Primitive primVariable]
	:	e=multExpr
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p = copyPrimitive($e.p);
					$instance = $e.instance;
					$index = $e.index; //should be changed later
					if ($e.listAddress != null) {
						$listAddress = $e.listAddress;
					}
					$primVariable = $e.primVariable;
				}
			} else {
				$p = copyPrimitive($e.p);
				$instance = $e.instance;
				$index = $e.index;//should be changed later
				if ($e.listAddress != null) {
					$listAddress = $e.listAddress;
				}
				$primVariable = $e.primVariable;
			}
		} ('+' e=multExpr
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						doMinPlusOp($e.p, $p, "+");
					}
				} else {
					doMinPlusOp($e.p, $p, "+");
				}
			} | '-' e=multExpr
				{
					if (!ifValueStack.empty()) {
						if (ifValueStack.peek() == "1") {
							doMinPlusOp($e.p, $p, "-");
						}
					} else {
						doMinPlusOp($e.p, $p, "-");
					}
				} )*
	;

multExpr returns [Primitive p, String instance, int index, String listAddress, Primitive primVariable]
	:	e=atom
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p = $e.p;
					if ($e.instance != null) {
						$instance = $e.instance;
					}
					$index = $e.index;
					if ($e.listAddress != null) {
						$listAddress = $e.listAddress;
					}
					$primVariable = $e.primVariable;
				}
			} else {
				$p = $e.p;
				if ($e.instance != null) {
					$instance = $e.instance;
				}
				$index = $e.index;
				if ($e.listAddress != null) {
					$listAddress = $e.listAddress;
				}
				$primVariable = $e.primVariable;
			}
		} ( (mul='*'|div='/') e=atom
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						if ($mul.text != null) {
							doMultDivOp($e.p, $p, $mul.text);
						} else {
							doMultDivOp($e.p, $p, $div.text);
						}
					}
				} else {
					if ($mul.text != null) {
						doMultDivOp($e.p, $p, $mul.text);
					} else {
						doMultDivOp($e.p, $p, $div.text);
					}
				}
			} )*
	;

atom returns [Primitive p = new Primitive(), String instance, int index = -1, String listAddress, Primitive primVariable]
	:	exprP
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p = $exprP.p;
					$instance = $exprP.inst;
					$index = $exprP.index;
					$p.index = $index;
					if ($exprP.listAddress != null) {
						$listAddress = $exprP.listAddress;
					}
					$primVariable = $exprP.primVariable;
				}
			} else {
				$p = $exprP.p;
				$instance = $exprP.inst;
				$index = $exprP.index;
				$p.index = $index;
				if ($exprP.listAddress != null) {
					$listAddress = $exprP.listAddress;
				}
				$primVariable = $exprP.primVariable;
			}
		}
	|	(n=NUMBER | n=REAL)
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p.num = Double.parseDouble($n.text);
					$p.type = "num";
				}
			} else {
				$p.num = Double.parseDouble($n.text);
				$p.type = "num";
			}
		}
	|	(t=TRUE | f=FALSE)
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p.type = "bool";
					if ($t != null) {
						$p.bool = true;
					} else {
						$p.bool = false;
					}
				}
			} else {
				$p.type = "bool";
				if ($t != null) {
					$p.bool = true;
				} else {
					$p.bool = false;
				}
			}
		}
	|	LETTER
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if (primitiveDeclarations.containsKey($LETTER.text)) {
						$p = copyPrimitive(primitiveDeclarations.get($LETTER.text));
						$primVariable = primitiveDeclarations.get($LETTER.text);
					} else {
						printError($LETTER.text + " is not a valid primitive.");
					}
				}
			} else {
				if (primitiveDeclarations.containsKey($LETTER.text)) {
					$p = copyPrimitive(primitiveDeclarations.get($LETTER.text));
					$primVariable = primitiveDeclarations.get($LETTER.text);
				} else {
					printError($LETTER.text + " is not a valid primitive.");
				}
			}
		}
	|	STRING
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p.type = "txt";
					$p.txt = $STRING.text.substring(1, $STRING.text.length()-1);
				}
			} else {
				$p.type = "txt";
				$p.txt = $STRING.text.substring(1, $STRING.text.length()-1);
			}
		}
	|	LETTER LEFTSBR (n=NUMBER|r=REAL) RIGHTSBR
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					if ($r != null) {
						printError("Invalid index.");
					} else {
						if (primitiveDeclarations.containsKey($LETTER.text)) {
							$listAddress = $LETTER.text;
							Primitive prim = primitiveDeclarations.get($LETTER.text);
							if (prim.type.equals("numList")) {
								int s = Integer.parseInt($n.text);
								if (s > prim.numList.size()) {
									printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
								} else {
									$p.num = prim.numList.get(s);
									$p.type = "num";
									$index = s;
									$primVariable = prim;
								}
							} else if (prim.type.equals("txtList")) {
								int s = Integer.parseInt($n.text);
								if (s > prim.txtList.size()) {
									printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
								} else {
									$p.txt = prim.txtList.get(s);
									$p.type = "txt";
									$index = s;
									$primVariable = prim;
								}
							}
						} else {
							printError("List " + $LETTER.text + " does not exist or is not a primitive list.");
						}
					}
				}
			} else {
				if ($r != null) {
					printError("Invalid index.");
				} else {
					if (primitiveDeclarations.containsKey($LETTER.text)) {
						$listAddress = $LETTER.text;
						Primitive prim = primitiveDeclarations.get($LETTER.text);
						if (prim.type.equals("numList")) {
							int s = Integer.parseInt($n.text);
							if (s > prim.numList.size()) {
								printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
							} else {
								$p.num = prim.numList.get(s);
								$p.type = "num";
								$index = s;
								$primVariable = prim;
							}
						} else if (prim.type.equals("txtList")) {
							int s = Integer.parseInt($n.text);
							if (s > prim.txtList.size()) {
								printError("Index out of bounds, index: " + $n.text + " and list size: " + s + ".");
							} else {
								$p.txt = prim.txtList.get(s);
								$p.type = "txt";
								$index = s;
								$primVariable = prim;
							}
						}
					} else {
						printError("List " + $LETTER.text + " does not exist or is not a primitive list.");
					}
				}
			} //end else
		}
	|	LEFTSBR list RIGHTSBR
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p = $list.listPrim;
					$primVariable = $list.listPrim;
				}
			} else {
				$p = $list.listPrim;
				$primVariable = $list.listPrim;
			}
		}
	|	'(' expr ')'
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$p = $expr.p;
					$primVariable = $expr.primVariable;
				}
			} else {
				$p = $expr.p;
				$primVariable = $expr.primVariable;
			}
		}
	;

list returns [Primitive listPrim]
	:	str1=expr
		{
			if (!ifValueStack.empty()) {
				if (ifValueStack.peek() == "1") {
					$listPrim = new Primitive();
					$listPrim.type = typeList + "List";
					addToListPrim($str1.p, $listPrim);
				}
			} else {
				$listPrim = new Primitive();
				$listPrim.type = typeList + "List";
				addToListPrim($str1.p, $listPrim);
			}
		} (COMMA str2=expr
			{
				if (!ifValueStack.empty()) {
					if (ifValueStack.peek() == "1") {
						addToListPrim($str2.p, $listPrim);
					}
				} else {
					addToListPrim($str2.p, $listPrim);
				}
			} )*
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

LETTER
	:	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

STRING
	:	'"' (options {greedy=false;} : .)* '"'
	;
