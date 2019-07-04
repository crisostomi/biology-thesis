block Environment

type Amount = Real(unit="mol*10^(-6)");

// Sinks 
input Amount compartment_7660__AdoHcy;
input Amount compartment_7660__PRDM9_DNA;
input Amount compartment_7660__Nucleosome_with_H3K4me3;
input Amount compartment_7660__SPO11_oligonucleotide;
input Amount compartment_7660__Cleaved_Meiotic_Holliday_Junction;

// Sources 
output Amount compartment_7660__AdoMet;
output Amount compartment_7660__H2AFX_Nucleosome;
output Amount compartment_7660__MLH1;
output Amount compartment_7660__ATM;
output Amount compartment_7660__Nucleosome_with_H3K4me2;
output Amount compartment_7660__SPO11_Dimer;
output Amount compartment_7660__BRCA1;
output Amount compartment_7660__CDK2;
output Amount compartment_7660__MLH3;
output Amount compartment_7660__DNA;
output Amount compartment_7660__PRDM9;

end Environment;

