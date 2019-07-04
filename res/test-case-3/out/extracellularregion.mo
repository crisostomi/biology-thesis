block ExtracellularRegion

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input capped_methylated_pre_FGFR2_mRNA_CBC_complex;
input NOTCH1_t_7_9_NOTCH1_M1580_K2555_;
input Ub_DLL_JAG_NOTCH1_PEST_Domain_Mutants;
input NOTCH1_HD_PEST_Domain_Mutants_Ub_DLL_JAG_NOTCH1_HD_PEST_Domain_Mutants;
input NOTCH1_HD_domain_mutants_Ub_DLL_JAG_NOTCH1_HD_domain_mutants;
input EGFRvIII_mutant_HSP90_CDC37;
input EGFR;

Amount HS;
Amount FGFR1c_binding_FGFs;
Amount FGFR3c_binding_FGFs;
Amount FGFR2b_mutant_binding_FGFs;
Amount FP_1039;
Amount FGFR2b_mutant_binding_FGFs_FP_1039;
Amount FGFR2c_mutant_binding_FGFs;
Amount GP369;
Amount FGFR2_IIIa_TM;
Amount FGF1_2;
Amount DKK;
Amount NOTCH1_t_7_9_NOTCH1_M1580_K2555_fragment;
Amount NOTCH1_HD_domain_mutant_fragments_Ub_DLL_JAG_NOTCH1_HD_domain_mutant_fragments;
Amount EGF;
Amount Cetuximab;
Amount Dimeric_TGFB1;
Amount atROL;
Amount RBP4_mutants;

Real reaction_2077421_rate;
Real reaction_8851710_rate;
Real reaction_2666278_rate;
Real reaction_2220944_rate;
Real reaction_2220976_rate;
Real reaction_2730752_rate;
Real reaction_5638137_rate;
Real reaction_1248677_rate;
Real reaction_2466828_rate;

initial equation

HS = initial_state[1];
FGFR1c_binding_FGFs = initial_state[2];
FGFR3c_binding_FGFs = initial_state[3];
FGFR2b_mutant_binding_FGFs = initial_state[4];
FP_1039 = initial_state[5];
FGFR2b_mutant_binding_FGFs_FP_1039 = initial_state[6];
FGFR2c_mutant_binding_FGFs = initial_state[7];
GP369 = initial_state[8];
FGFR2_IIIa_TM = initial_state[9];
FGF1_2 = initial_state[10];
DKK = initial_state[11];
NOTCH1_t_7_9_NOTCH1_M1580_K2555_fragment = initial_state[12];
NOTCH1_HD_domain_mutant_fragments_Ub_DLL_JAG_NOTCH1_HD_domain_mutant_fragments = initial_state[13];
EGF = initial_state[14];
Cetuximab = initial_state[15];
Dimeric_TGFB1 = initial_state[16];
atROL = initial_state[17];
RBP4_mutants = initial_state[18];

equation

reaction_2077421_rate = rate_constants[1] * (FGFR2b_mutant_binding_FGFs^1) * (FP_1039^1) ;
reaction_8851710_rate = rate_constants[2] * (capped_methylated_pre_FGFR2_mRNA_CBC_complex^1) ;
reaction_2666278_rate = rate_constants[3] * (NOTCH1_t_7_9_NOTCH1_M1580_K2555_^1) ;
reaction_2220944_rate = rate_constants[4] * (Ub_DLL_JAG_NOTCH1_PEST_Domain_Mutants^1) ;
reaction_2220976_rate = rate_constants[5] * (NOTCH1_HD_PEST_Domain_Mutants_Ub_DLL_JAG_NOTCH1_HD_PEST_Domain_Mutants^1) ;
reaction_2730752_rate = rate_constants[6] * (NOTCH1_HD_domain_mutants_Ub_DLL_JAG_NOTCH1_HD_domain_mutants^1) ;
reaction_5638137_rate = rate_constants[7] * (EGF^1) * (EGFRvIII_mutant_HSP90_CDC37^1) ;
reaction_1248677_rate = rate_constants[8] * (EGFR^1) * (Cetuximab^1) ;
reaction_2466828_rate = rate_constants[9] * (RBP4_mutants^1) * (atROL^1) ;

der(FGFR2b_mutant_binding_FGFs) = 1 * reaction_2077421_rate ;
der(FP_1039) = 1 * reaction_2077421_rate ;
der(FGFR2b_mutant_binding_FGFs_FP_1039) = 1 * reaction_2077421_rate ;
der(FGFR2_IIIa_TM) = 1 * reaction_8851710_rate ;
der(NOTCH1_t_7_9_NOTCH1_M1580_K2555_fragment) = 1 * reaction_2666278_rate ;
der(NOTCH1_HD_domain_mutant_fragments_Ub_DLL_JAG_NOTCH1_HD_domain_mutant_fragments) = 1 * reaction_2730752_rate ;
der(EGF) = 1 * reaction_5638137_rate ;
der(Cetuximab) = 1 * reaction_1248677_rate ;
der(atROL) = 1 * reaction_2466828_rate ;
der(RBP4_mutants) = 1 * reaction_2466828_rate ;

end ExtracellularRegion;

