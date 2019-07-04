block Nucleoplasm

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];


Amount Meiotic_Holliday_Junction;
Amount MLH1;
Amount MLH3;
Amount CDK2;
Amount CDK4;
Amount BLM;
Amount BRCA2;
Amount TOP3A;
Amount MSH4;
Amount Cleaved_Meiotic_Holliday_Junction;
Amount MSH5;
Amount RPA_heterotrimer;
Amount DNA;
Amount PRDM9;
Amount PRDM9_DNA;
Amount Meiotic_single_stranded_DNA_complex;
Amount Meiotic_D_loop_complex;
Amount HOP2_TBPIP_MND1;
Amount H2AFX_Nucleosome;
Amount 3_overhanging_DNA_at_resected_DSB_ends;
Amount BRCA1;
Amount DMC1;
Amount RAD51;
Amount ATM;
Amount TEX15;
Amount RAD51C;
Amount MRN_CtIP;
Amount SPO11_double_stand_break;
Amount SPO11_double_strand_break_with_3_single_strand_breaks;
Amount SPO11_Dimer;
Amount Nucleosome_with_H3K4me2;
Amount AdoMet;
Amount Nucleosome_with_H3K4me3;
Amount AdoHcy;
Amount SPO11_oligonucleotide;

Real reaction_912429_rate;
Real reaction_912363_rate;
Real reaction_912458_rate;
Real reaction_912503_rate;
Real reaction_9023941_rate;
Real reaction_912368_rate;
Real reaction_912496_rate;
Real reaction_1214188_rate;
Real reaction_9023943_rate;

initial equation

Meiotic_Holliday_Junction = initial_state[1];
MLH1 = initial_state[2];
MLH3 = initial_state[3];
CDK2 = initial_state[4];
CDK4 = initial_state[5];
BLM = initial_state[6];
BRCA2 = initial_state[7];
TOP3A = initial_state[8];
MSH4 = initial_state[9];
Cleaved_Meiotic_Holliday_Junction = initial_state[10];
MSH5 = initial_state[11];
RPA_heterotrimer = initial_state[12];
DNA = initial_state[13];
PRDM9 = initial_state[14];
PRDM9_DNA = initial_state[15];
Meiotic_single_stranded_DNA_complex = initial_state[16];
Meiotic_D_loop_complex = initial_state[17];
HOP2_TBPIP_MND1 = initial_state[18];
H2AFX_Nucleosome = initial_state[19];
3_overhanging_DNA_at_resected_DSB_ends = initial_state[20];
BRCA1 = initial_state[21];
DMC1 = initial_state[22];
RAD51 = initial_state[23];
ATM = initial_state[24];
TEX15 = initial_state[25];
RAD51C = initial_state[26];
MRN_CtIP = initial_state[27];
SPO11_double_stand_break = initial_state[28];
SPO11_double_strand_break_with_3_single_strand_breaks = initial_state[29];
SPO11_Dimer = initial_state[30];
Nucleosome_with_H3K4me2 = initial_state[31];
AdoMet = initial_state[32];
Nucleosome_with_H3K4me3 = initial_state[33];
AdoHcy = initial_state[34];
SPO11_oligonucleotide = initial_state[35];

equation

reaction_912429_rate = rate_constants[1] * (MLH1^1) * (Meiotic_Holliday_Junction^1) * (CDK2^1) * (MLH3^1) ;
reaction_912363_rate = rate_constants[2] * (PRDM9^1) * (DNA^1) ;
reaction_912458_rate = rate_constants[3] * (Meiotic_single_stranded_DNA_complex^1) * (DNA^1) ;
reaction_912503_rate = rate_constants[4] * (BRCA1^1) * (RAD51^1) * (3_overhanging_DNA_at_resected_DSB_ends^1) * (RPA_heterotrimer^1) * (H2AFX_Nucleosome^1) * (CDK4^1) * (BRCA2^1) * (ATM^1) * (DMC1^1) ;
reaction_9023941_rate = rate_constants[5] * (SPO11_double_stand_break^1) ;
reaction_912368_rate = rate_constants[6] * (SPO11_Dimer^1) * (DNA^1) ;
reaction_912496_rate = rate_constants[7] * (BLM^1) * (MSH4^1) * (TOP3A^1) * (MSH5^1) * (Meiotic_D_loop_complex^1) ;
reaction_1214188_rate = rate_constants[8] * (AdoMet^2) * (Nucleosome_with_H3K4me2^1) ;
reaction_9023943_rate = rate_constants[9] * (SPO11_double_strand_break_with_3_single_strand_breaks^1) ;

der(Meiotic_Holliday_Junction) = 1 * reaction_912429_rate + 1 * reaction_912496_rate ;
der(MLH1) = 1 * reaction_912429_rate ;
der(MLH3) = 1 * reaction_912429_rate ;
der(CDK2) = 1 * reaction_912429_rate ;
der(CDK4) = 1 * reaction_912429_rate - 1 * reaction_912503_rate ;
der(BLM) = 1 * reaction_912429_rate - 1 * reaction_912496_rate ;
der(BRCA2) = 1 * reaction_912429_rate - 1 * reaction_912503_rate ;
der(TOP3A) = 1 * reaction_912429_rate - 1 * reaction_912496_rate ;
der(MSH4) = 1 * reaction_912429_rate - 1 * reaction_912496_rate ;
der(Cleaved_Meiotic_Holliday_Junction) = 1 * reaction_912429_rate ;
der(MSH5) = 1 * reaction_912429_rate - 1 * reaction_912496_rate ;
der(RPA_heterotrimer) = 1 * reaction_912429_rate - 1 * reaction_912503_rate ;
der(DNA) = 1 * reaction_912363_rate - 1 * reaction_912458_rate - 1 * reaction_912368_rate ;
der(PRDM9) = 1 * reaction_912363_rate ;
der(PRDM9_DNA) = 1 * reaction_912363_rate ;
der(Meiotic_single_stranded_DNA_complex) = 1 * reaction_912458_rate + 1 * reaction_912503_rate ;
der(Meiotic_D_loop_complex) = 1 * reaction_912458_rate - 1 * reaction_912496_rate ;
der(H2AFX_Nucleosome) = 1 * reaction_912503_rate ;
der(3_overhanging_DNA_at_resected_DSB_ends) = 1 * reaction_912503_rate + 1 * reaction_9023943_rate ;
der(BRCA1) = 1 * reaction_912503_rate ;
der(DMC1) = 1 * reaction_912503_rate + 1 * reaction_912496_rate ;
der(RAD51) = 1 * reaction_912503_rate + 1 * reaction_912496_rate ;
der(ATM) = 1 * reaction_912503_rate ;
der(SPO11_double_stand_break) = 1 * reaction_9023941_rate + 1 * reaction_912368_rate ;
der(SPO11_double_strand_break_with_3_single_strand_breaks) = 1 * reaction_9023941_rate - 1 * reaction_9023943_rate ;
der(SPO11_Dimer) = 1 * reaction_912368_rate ;
der(Nucleosome_with_H3K4me2) = 1 * reaction_1214188_rate ;
der(AdoMet) = 2 * reaction_1214188_rate ;
der(Nucleosome_with_H3K4me3) = 1 * reaction_1214188_rate ;
der(AdoHcy) = 2 * reaction_1214188_rate ;
der(SPO11_oligonucleotide) = 1 * reaction_9023943_rate ;

end Nucleoplasm;

