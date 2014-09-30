/**** WITHOUT PIGEON PROPERTY ****/
//Properties
Property ID(txt);
Property Sequence(txt);
Property Name(txt);
Property Recognizes(txt);
Property InducedBy(txt);

//PartTypes
Part InduciblePromoter(Name, Sequence, InducedBy);
Part ConstitutivePromoter(Name, Sequence);
Part RBS(Name, Sequence);
Part Reporter(Name, Sequence);
Part Terminator(Name, Sequence);
Part Recombinase(Name, Sequence, Recognizes);
Part RecSite(Name, Sequence); //recognition site for a recombinase


//Promoters
InduciblePromoter Pbad(.Name("Pbad"), .InducedBy("araC"));
InduciblePromoter Plac(.Name("Plac"), .InducedBy("IPTG"));
InduciblePromoter PTet(.Name("Ptet"), .InducedBy("Tetracyclin")); //Actually a repressible promoter
ConstitutivePromoter Pcon(.Name("Pcon"));

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


/**** WITH PIGEON PROPERTY 
//Properties
Property ID(txt);
Property Sequence(txt);
Property Name(txt);
Property Recognizes(txt);
Property InducedBy(txt);
Property Pigeon(txt);

//PartTypes
Part InduciblePromoter(Name, Sequence, InducedBy, Pigeon);
Part ConstitutivePromoter(Name, Sequence, Pigeon);
Part RBS(Name, Sequence, Pigeon);
Part Reporter(Name, Sequence, Pigeon);
Part Terminator(Name, Sequence, Pigeon);
Part Recombinase(Name, Sequence, Recognizes, Pigeon);
Part RecSite(Name, Sequence, Pigeon); //recognition site for a recombinase


//Promoters
InduciblePromoter Pbad(.Name("Pbad"), .InducedBy("araC"), .Pigeon("p pBad 1"));
InduciblePromoter Plac(.Name("Plac"), .InducedBy("IPTG"), .Pigeon("p pLac 2"));
InduciblePromoter PTet(.Name("Ptet"), .InducedBy("Tetracyclin"), .Pigeon("p pTet 3")); //Actually a repressible promoter
ConstitutivePromoter Pcon(.Name("Pcon"), .Pigeon("p pCon 4"));

//Recombinase
Recombinase Cre(.Name("Cre"), .Recognizes("CreSite"), .Pigeon("c Cre 6"));
Recombinase Dre(.Name("Dre"), .Recognizes("DreSite"), .Pigeon("c Dre 7"));
Recombinase Flp(.Name("Flp"), .Recognizes("FrtSite"), .Pigeon("c Flp 8"));

//Recombinase Recognition Sites
RecSite CreSite_forward(.Name("CreSite"), .Pigeon("f CreSite 2"));
RecSite CreSite_reverse(.Name("CreSite"), .Pigeon("f CreSite 2"));
RecSite DreSite_forward(.Name("DreSite"), .Pigeon("f DreSite 3"));
RecSite DreSite_reverse(.Name("DreSite"), .Pigeon("f DreSite 3"));
RecSite FrtSite_forward(.Name("FrtSite"), .Pigeon("f FrtSite 4"));
RecSite FrtSite_reverse(.Name("FrtSite"), .Pigeon("f FrtSite 4"));

CreSite_forward MATCHES CreSite_reverse;
DreSite_forward MATCHES DreSite_reverse;
FrtSite_forward MATCHES FrtSite_reverse;
 
//Terminators
Terminator term(.Name("term"), .Pigeon("t xxx 13 nl"));

//RBS SItes
RBS goodRBS(.Name("goodRBS"), .Pigeon("r rbs 13 nl"));

//Reporters
Reporter FP1(.Name("FP1"), .Pigeon("c FP1 9"));
Reporter FP2(.Name("FP2"), .Pigeon("c FP2 10"));
Reporter FP3(.Name("FP3"), .Pigeon("c FP3 11"));
Reporter FP4(.Name("FP4"), .Pigeon("c FP4 12"));
Reporter FP5(.Name("FP5"), .Pigeon("c FP5 10"));
Reporter FP6(.Name("FP6"), .Pigeon("c FP6 11"));
Reporter FP7(.Name("FP7"), .Pigeon("c FP7 12"));
Reporter FP8(.Name("FP8"), .Pigeon("c FP8 13"));
****/