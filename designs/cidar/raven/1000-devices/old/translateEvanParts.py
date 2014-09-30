#! /usr/bin/python
import sys
import os
# first argument is input file





################################################################
#run eugene script
os.system("java -jar eugene.jar devices_raven_ex.eug > eugOutput.txt")
compositionArray = []; #a 2D array that stores the composition
basicParts = set();
with open("eugOutput.txt") as f:
   configFile = f.readlines()
for line in configFile:
   tokens=line.split(",");
   tokens[0] = tokens[0][tokens[0].index("(")+1:];
   tokens[-1] = tokens[-1][:tokens[-1].index(")")];
   compositionArray.append(tokens);
   toAdd = set(tokens);
   basicParts = basicParts | toAdd;

# outFile.write out the file
print("Name,Sequence,Left Overhang,Right Overhang,type,Resistance,Level,Composition,,,,,,,,,,,,,,,,,,,");
for basicPart in basicParts:
   type = "gene"
   partName = basicPart;
   if("terminator" in basicPart.lower()):
      type = "terminator";
   elif("rbs" in basicPart.lower()):
      type = "RBS";
   elif("promoter" in basicPart.lower()):
      type = "promoter";
   elif("fp" in basicPart.lower()):
      type = "reporter";
   if(not "empty" in basicPart.lower()):
      print(basicPart+",,,,"+type+",,,,,,,,,,,,,,,,,,,,,,");
for i in range(len(compositionArray)):
   compositionString = "Device"+str(i+1)+",,,,composite,,,";
   for j in range(len(compositionArray[i])):
      if(not "empty" in compositionArray[i][j].lower() and not "urs" in compositionArray[i][j].lower()):
         compositionString = compositionString+compositionArray[i][j]+",";
   print(compositionString[:-1]);
#os.system("java -jar Raven.jar")

