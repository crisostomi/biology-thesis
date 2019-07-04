block PlasmaMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input HSP90;
input CDC37;
input GRB2_1_SOS1;
input ATP;
input ATP;
input GRB2_1_SOS1;
input ATP;

Amount p_5Y_EGFRvIII_p_Y349_Y350_SHC1_GRB2_SOS1;
Amount p21_RAS_GDP;
Amount p21_RAS_GTP;
Amount EGFRvIII;
Amount EGFRvIII_mutant_HSP90_CDC37;
Amount p_5Y_EGFRvIII_mutant_dimer;
Amount p_5Y_EGFRvIII_SHC1;
Amount p_5Y_EGFRvIII_p_Y349_Y350_SHC1;
Amount EGFRvIII_mutant_dimer;
Amount p_5Y_EGFRvIII_GRB2_GAB1;
Amount p_5Y_EGFRvIII_GRB2_GAB1_PI3K;
Amount p_5Y_EGFRvIII_PLCG1;
Amount p_5Y_EGFRvIII_p_Y771_Y783_Y1254_PLCG1;
Amount PLCG1;
Amount p_5Y_EGFRvIII_GRB2_SOS1;
Amount p_4Y_PLCG1;
Amount PI_4_5_P2;
Amount PI_3_4_5_P3;

Real reaction_1247999_rate;
Real reaction_5637798_rate;
Real reaction_1248002_rate;
Real reaction_5637796_rate;
Real reaction_1248655_rate;
Real reaction_5637770_rate;
Real reaction_5637800_rate;
Real reaction_5637801_rate;

initial equation

p_5Y_EGFRvIII_p_Y349_Y350_SHC1_GRB2_SOS1 = initial_state[1];
p21_RAS_GDP = initial_state[2];
p21_RAS_GTP = initial_state[3];
EGFRvIII = initial_state[4];
EGFRvIII_mutant_HSP90_CDC37 = initial_state[5];
p_5Y_EGFRvIII_mutant_dimer = initial_state[6];
p_5Y_EGFRvIII_SHC1 = initial_state[7];
p_5Y_EGFRvIII_p_Y349_Y350_SHC1 = initial_state[8];
EGFRvIII_mutant_dimer = initial_state[9];
p_5Y_EGFRvIII_GRB2_GAB1 = initial_state[10];
p_5Y_EGFRvIII_GRB2_GAB1_PI3K = initial_state[11];
p_5Y_EGFRvIII_PLCG1 = initial_state[12];
p_5Y_EGFRvIII_p_Y771_Y783_Y1254_PLCG1 = initial_state[13];
PLCG1 = initial_state[14];
p_5Y_EGFRvIII_GRB2_SOS1 = initial_state[15];
p_4Y_PLCG1 = initial_state[16];
PI_4_5_P2 = initial_state[17];
PI_3_4_5_P3 = initial_state[18];

equation

reaction_1247999_rate = rate_constants[1] * (EGFRvIII^1) * (HSP90^1) * (CDC37^1) ;
reaction_5637798_rate = rate_constants[2] * (p_5Y_EGFRvIII_p_Y349_Y350_SHC1^1) * (GRB2_1_SOS1^1) ;
reaction_1248002_rate = rate_constants[3] * (EGFRvIII_mutant_HSP90_CDC37^2) ;
reaction_5637796_rate = rate_constants[4] * (ATP^2) * (p_5Y_EGFRvIII_SHC1^1) ;
reaction_1248655_rate = rate_constants[5] * (ATP^10) * (EGFRvIII_mutant_dimer^1) ;
reaction_5637770_rate = rate_constants[6] * (p_5Y_EGFRvIII_mutant_dimer^1) * (GRB2_1_SOS1^1) ;
reaction_5637800_rate = rate_constants[7] * (p_5Y_EGFRvIII_p_Y771_Y783_Y1254_PLCG1^1) ;
reaction_5637801_rate = rate_constants[8] * (ATP^1) * (PI_4_5_P2^1) ;

der(p_5Y_EGFRvIII_p_Y349_Y350_SHC1_GRB2_SOS1) = 1 * reaction_5637798_rate ;
der(EGFRvIII) = 1 * reaction_1247999_rate ;
der(EGFRvIII_mutant_HSP90_CDC37) = 1 * reaction_1247999_rate - 2 * reaction_1248002_rate ;
der(p_5Y_EGFRvIII_mutant_dimer) = 1 * reaction_1248655_rate - 1 * reaction_5637770_rate + 1 * reaction_5637800_rate ;
der(p_5Y_EGFRvIII_SHC1) = 1 * reaction_5637796_rate ;
der(p_5Y_EGFRvIII_p_Y349_Y350_SHC1) = 1 * reaction_5637798_rate + 1 * reaction_5637796_rate ;
der(EGFRvIII_mutant_dimer) = 1 * reaction_1248002_rate - 1 * reaction_1248655_rate ;
der(p_5Y_EGFRvIII_p_Y771_Y783_Y1254_PLCG1) = 1 * reaction_5637800_rate ;
der(p_5Y_EGFRvIII_GRB2_SOS1) = 1 * reaction_5637770_rate ;
der(p_4Y_PLCG1) = 1 * reaction_5637800_rate ;
der(PI_4_5_P2) = 1 * reaction_5637801_rate ;
der(PI_3_4_5_P3) = 1 * reaction_5637801_rate ;

end PlasmaMembrane;

