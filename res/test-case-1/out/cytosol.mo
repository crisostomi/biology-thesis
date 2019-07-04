block Cytosol

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input p21_RAS_GDP;
input p_5Y_EGFRvIII_mutant_dimer;
input p_5Y_EGFRvIII_mutant_dimer;
input p_5Y_EGFRvIII_GRB2_GAB1;
input p_5Y_EGFRvIII_mutant_dimer;
input p_5Y_EGFRvIII_PLCG1;
input PLCG1;
input p_5Y_EGFRvIII_mutant_dimer;
input p21_RAS_GDP;

Amount ATP;
Amount H2O;
Amount porphyrin;
Amount GTP;
Amount GDP;
Amount CDC37;
Amount HSP90;
Amount SHC1;
Amount GRB2_1_SOS1;
Amount ADP;
Amount GRB2_GAB1;
Amount PIK3CA_PIK3R1;
Amount CBL;
Amount Benzoquinoid_ansamycins;
Amount HSP90_Benzoquinoid_ansamycins;
Amount Glc;

Real reaction_5637808_rate;
Real reaction_5637766_rate;
Real reaction_5637764_rate;
Real reaction_5637765_rate;
Real reaction_5637794_rate;
Real reaction_5637795_rate;
Real reaction_5637792_rate;
Real reaction_5637806_rate;
Real reaction_1218824_rate;

initial equation

ATP = initial_state[1];
H2O = initial_state[2];
porphyrin = initial_state[3];
GTP = initial_state[4];
GDP = initial_state[5];
CDC37 = initial_state[6];
HSP90 = initial_state[7];
SHC1 = initial_state[8];
GRB2_1_SOS1 = initial_state[9];
ADP = initial_state[10];
GRB2_GAB1 = initial_state[11];
PIK3CA_PIK3R1 = initial_state[12];
CBL = initial_state[13];
Benzoquinoid_ansamycins = initial_state[14];
HSP90_Benzoquinoid_ansamycins = initial_state[15];
Glc = initial_state[16];

equation

reaction_5637808_rate = rate_constants[1] * (GTP^1) * (p21_RAS_GDP^1) ;
reaction_5637766_rate = rate_constants[2] * (SHC1^1) * (p_5Y_EGFRvIII_mutant_dimer^1) ;
reaction_5637764_rate = rate_constants[3] * (GRB2_GAB1^1) * (p_5Y_EGFRvIII_mutant_dimer^1) ;
reaction_5637765_rate = rate_constants[4] * (p_5Y_EGFRvIII_GRB2_GAB1^1) * (PIK3CA_PIK3R1^1) ;
reaction_5637794_rate = rate_constants[5] * (CBL^2) * (p_5Y_EGFRvIII_mutant_dimer^1) ;
reaction_5637795_rate = rate_constants[6] * (ATP^4) * (p_5Y_EGFRvIII_PLCG1^1) ;
reaction_5637792_rate = rate_constants[7] * (PLCG1^1) * (p_5Y_EGFRvIII_mutant_dimer^1) ;
reaction_5637806_rate = rate_constants[8] * (GTP^1) * (p21_RAS_GDP^1) ;
reaction_1218824_rate = rate_constants[9] * (Benzoquinoid_ansamycins^2) * (HSP90^1) ;

der(ATP) = 4 * reaction_5637795_rate ;
der(GTP) = 1 * reaction_5637808_rate - 1 * reaction_5637806_rate ;
der(GDP) = 1 * reaction_5637808_rate + 1 * reaction_5637806_rate ;
der(HSP90) = 1 * reaction_1218824_rate ;
der(SHC1) = 1 * reaction_5637766_rate ;
der(ADP) = 4 * reaction_5637795_rate ;
der(GRB2_GAB1) = 1 * reaction_5637764_rate ;
der(PIK3CA_PIK3R1) = 1 * reaction_5637765_rate ;
der(CBL) = 2 * reaction_5637794_rate ;
der(Benzoquinoid_ansamycins) = 2 * reaction_1218824_rate ;
der(HSP90_Benzoquinoid_ansamycins) = 1 * reaction_1218824_rate ;

end Cytosol;

