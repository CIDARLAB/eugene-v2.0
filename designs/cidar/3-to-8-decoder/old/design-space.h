//Properties
Property ID(txt);
Property Sequence(txt);
Property Name(txt);
Property Recognizes(txt);
Property InducedBy(txt);
Property Orientation(txt);
Property 
//PartTypes
Part InduciblePromoter(Name, Sequence, InducedBy);
Part ConstitutivePromoter(Name, Sequence);
Part RBS(Name, Sequence);
Part Reporter(Name, Sequence);
Part Terminator(Name, Sequence);
Part Recombinase(Name, Sequence, Recognizes);
Part RecSite(Name, Sequence, Orientation); //recognition site for a recombinase


//Promoters
InduciblePromoter Pbad(.Name("Pbad"), .InducedBy("araC"), .Pigeon("p pBad 1"));
InduciblePromoter Plac(.Name("Plac"), .InducedBy("IPTG"), .Pigeon("p pLac 2"));
InduciblePromoter PTet(.Name("Ptet"), .InducedBy("Tetracyclin"), .Pigeon("p pTet 3")); //Actually a repressible promoter
ConstitutivePromoter Pcon(.Name("Pcon"), .Pigeon("p pCon 4"));

//Recombinase
Recombinase Cre(.Name("Cre"), .Recognizes("CreSite"));
Recombinase Dre(.Name("Dre"), .Recognizes("DreSite"));
Recombinase Flp(.Name("Flp"), .Recognizes("FrtSite"));

//Recombinase Recognition Sites
RecSite CreSite(.Name("CreSite"));
RecSite DreSite(.Name("DreSite"));
RecSite FrtSite(.Name("FrtSite"));

//Terminators
Terminator term(.Name("term"));

//RBS SItes
RBS goodRBS(.Name("goodRBS"));

//Reporters
Reporter FP1(.Name("FP1"));
Reporter FP2(.Name("FP2"));
Reporter FP3(.Name("FP3"));
Reporter FP4(.Name("FP4"));
Reporter FP5(.Name("FP5"));
Reporter FP6(.Name("FP6"));
Reporter FP7(.Name("FP7"));
Reporter FP8(.Name("FP8"));

