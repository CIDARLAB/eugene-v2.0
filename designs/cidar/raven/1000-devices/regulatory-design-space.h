 
/*
 *   DESIGN SPACE
 */  

Property Name(txt);
Property Sequence(txt);
Property Left_Overhang(txt);
Property Right_Overhang(txt);
Property Type(txt);
Property Resistance(txt);
Property Level(txt);


/*
 *   PART TYPES
 */ 
Part Promoter(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
Part RBS(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
Part Gene(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
Part Terminator(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);
Part Reporter(Name, Sequence, Left_Overhang, Right_Overhang, Type, Resistance, Level);

/*
 *   PARTS
 */

/** PROMOTERS **/
Promoter pLtetO1(.Name("pLtetO1"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("Promoter"), .Resistance(""), .Level(""));
Promoter lambdaPr(.Name("lambdaPr"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("Promoter"), .Resistance(""), .Level(""));
Promoter pLlacO1(.Name("pLlacO1"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("Promoter"), .Resistance(""), .Level(""));
Promoter pLux(.Name("pLux"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("Promoter"), .Resistance(""), .Level(""));

/** RBSs ***/
RBS rbs1;
RBS rbs2;
RBS rbs3;

/** GENES **/
Gene tetR_lite(.Name("tetR-lite"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("Gene"), .Resistance(""), .Level(""));
Gene lambdacI_lite(.Name("lambdacI-lite"), .Sequence(""), .Left_Overhang("B"), .Right_Overhang("C"), .Type("Gene"), .Resistance(""), .Level(""));
Gene lacI_lite(.Name("lacI-lite"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("Gene"), .Resistance(""), .Level(""));
Gene luxR_lite(.Name("luxR_lite"), .Sequence(""), .Left_Overhang(""), .Right_Overhang(""), .Type("Gene"), .Resistance(""), .Level(""));

/** REPRESSOR-PROMOTER PAIRS **/
tetR_lite REPRESSES pLtetO1;
lambdacI_lite REPRESSES lambdaPr;
lacI_lite REPRESSES pLlacO1;
luxR_lite REPRESSES pLux;

/** REPORTERS **/
Reporter GFP("GFP",
"tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcactactagagaaagaggagaaatactagatgcgtaaaggagaagaact
tttcactggagttgtcccaattcttgttgaattagatggtgatgttaatgggcacaaattttctgtcagtggagagggtgaaggtgatgcaacatacgga
aaacttacccttaaatttatttgcactactggaaaactacctgttccatggccaacacttgtcactactttcggttatggtgttcaatgctttgcgagat
acccagatcatatgaaacagcatgactttttcaagagtgccatgcccgaaggttatgtacaggaaagaactatatttttcaaagatgacgggaactacaa
gacacgtgctgaagtcaagtttgaaggtgatacccttgttaatagaatcgagttaaaaggtattgattttaaagaagatggaaacattcttggacacaaa
ttggaatacaactataactcacacaatgtatacatcatggcagacaaacaaaagaatggaatcaaagttaacttcaaaattagacacaacattgaagatg
gaagcgttcaactagcagaccattatcaacaaaatactccaattggcgatggccctgtccttttaccagacaaccattacctgtccacacaatctgccct
ttcgaaagatcccaacgaaaagagagaccacatggtccttcttgagtttgtaacagctgctgggattacacatggcatggatgaactatacaaataataa
tactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcac
actggctcaccttcgggtgggcctttctgcgtttata",
	"c GFP 4");

/** TERMINATORS **/
Terminator T1(.Name("T1"), .Sequence(""), .Left_Overhang("F"), .Right_Overhang("G"), .Type("Terminator"), .Resistance(""), .Level(""));
Terminator T7(.Name("T7"), .Sequence(""), .Left_Overhang("F"), .Right_Overhang("G"), .Type("Terminator"), .Resistance(""), .Level(""));