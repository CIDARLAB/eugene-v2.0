// COMMON VARIABLES
num N = 20;
txt[] nucleotides = ["A", "T", "C", "G"];

// COMMON PROPERTIES
Property seq(txt);

// COMMON PART TYPES
PartType Promoter(seq);
PartType CDS(seq);
 
