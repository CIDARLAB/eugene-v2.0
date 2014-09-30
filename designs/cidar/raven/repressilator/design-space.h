//PART PROPERTIES
Property Name(txt);
Property Sequence(txt);
Property Left_Overhang(txt);
Property Right_Overhang(txt);
Property Type(txt);
Property Resistance(txt);
Property Level(txt);

//PART TYPES
Part promoter(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
Part gene(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
Part terminator(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
//Part Type(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
Part origin(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);

//PARTS
//promoter pLtetO1(.Name("pLtetO1"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("promoter"), .Resistance(""), .Level(""));
terminator T1(.Name("T1"), .Sequence(""), .Left_Overhang("F"), .Right_Overhang("G"), .Type("terminator"), .Resistance(""), .Level(""));
promoter lambdaPr(.Name("lambdaPr"), .Sequence(""), .Left_Overhang("D"), .Right_Overhang("E"), .Type("promoter"), .Resistance(""), .Level(""));
//promoter lambdaPr(.Name("lambdaPr"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("promoter"), .Resistance(""), .Level(""));
promoter pLtetO1(.Name("pLtetO1"), .Sequence(""), .Left_Overhang("G"), .Right_Overhang("B"), .Type("promoter"), .Resistance(""), .Level(""));
//Type Name(.Name("Name"), .Sequence("Sequence"), .Left_Overhang("Left_Overhang"), .Right_Overhang("Right_Overhang"), .Type("Type"), .Resistance(""), .Level(""));
//terminator T1(.Name("T1"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("terminator"), .Resistance(""), .Level(""));
//gene lacI_lite(.Name("lacI-lite"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("gene"), .Resistance(""), .Level(""));
//terminator T1(.Name("T1"), .Sequence(""), .Left_Overhang("C"), .Right_Overhang("D"), .Type("terminator"), .Resistance(""), .Level(""));
//origin pSC101(.Name("pSC101"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("origin"), .Resistance(""), .Level(""));
//gene lambdacI_lite(.Name("lambdacI-lite"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("gene"), .Resistance(""), .Level(""));
gene ampR(.Name("ampR"), .Sequence(""), .Left_Overhang("I"), .Right_Overhang("C"), .Type("gene"), .Resistance(""), .Level(""));
origin pSC101(.Name("pSC101"), .Sequence(""), .Left_Overhang("D"), .Right_Overhang("H"), .Type("origin"), .Resistance(""), .Level(""));
//gene ampR(.Name("ampR"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("gene"), .Resistance(""), .Level(""));
//terminator T1(.Name("T1"), .Sequence(""), .Left_Overhang("C"), .Right_Overhang("J"), .Type("terminator"), .Resistance(""), .Level(""));
gene tetR_lite(.Name("tetR-lite"), .Sequence(""), .Left_Overhang("B"), .Right_Overhang("C"), .Type("gene"), .Resistance(""), .Level(""));
//promoter pLlacO1(.Name("pLlacO1"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("promoter"), .Resistance(""), .Level(""));
promoter pLlacO1(.Name("pLlacO1"), .Sequence(""), .Left_Overhang("A"), .Right_Overhang("B"), .Type("promoter"), .Resistance(""), .Level(""));
gene lambdacI_lite(.Name("lambdacI-lite"), .Sequence(""), .Left_Overhang("B"), .Right_Overhang("C"), .Type("gene"), .Resistance(""), .Level(""));
//gene tetR_lite(.Name("tetR-lite"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("gene"), .Resistance(""), .Level(""));
gene lacI_lite(.Name("lacI-lite"), .Sequence(""), .Left_Overhang("E"), .Right_Overhang("F"), .Type("gene"), .Resistance(""), .Level(""));
