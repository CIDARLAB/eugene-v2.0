// COMMON VARIABLES
num N = 20;

// part specification

// COMMON PROPERTIES
Property seq(txt);


// generate random sequences
txt[] nucleotides = ["A", "T", "C", "G"];


// Part Types
PartType Promoter(seq);
PartType CDS(seq);
 
