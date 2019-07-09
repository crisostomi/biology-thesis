class Biosystem

type Amount = Real(unit="mol*10^(-6)");

parameter Amount init_c_1[35];

parameter Real rates_c_1[9];

Nucleoplasm c_1(initial_state=init_c_1, rate_constants=rates_c_1);
Environment env;

equation
	// Sinks
	env.compartment_7660__AdoHcy = c_1.AdoHcy;
	env.compartment_7660__PRDM9_DNA = c_1.PRDM9_DNA;
	env.compartment_7660__Nucleosome_with_H3K4me3 = c_1.Nucleosome_with_H3K4me3;
	env.compartment_7660__SPO11_oligonucleotide = c_1.SPO11_oligonucleotide;
	env.compartment_7660__Cleaved_Meiotic_Holliday_Junction = c_1.Cleaved_Meiotic_Holliday_Junction;

	// Sources
	env.compartment_7660__PRDM9 = c_1.PRDM9;
	env.compartment_7660__DNA = c_1.DNA;
	env.compartment_7660__H2AFX_Nucleosome = c_1.H2AFX_Nucleosome;
	env.compartment_7660__CDK2 = c_1.CDK2;
	env.compartment_7660__MLH3 = c_1.MLH3;
	env.compartment_7660__Nucleosome_with_H3K4me2 = c_1.Nucleosome_with_H3K4me2;
	env.compartment_7660__BRCA1 = c_1.BRCA1;
	env.compartment_7660__ATM = c_1.ATM;
	env.compartment_7660__AdoMet = c_1.AdoMet;
	env.compartment_7660__MLH1 = c_1.MLH1;
	env.compartment_7660__SPO11_Dimer = c_1.SPO11_Dimer;

end Biosystem;

