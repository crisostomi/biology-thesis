block EarlyEndosomeMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input ATP;

Amount ZFYVE9_1;
Amount TGFB1_p_TGFBR_ZFYVE9_SMAD2_3_Phosphorylation_Motif_Mutants;

Real reaction_3304394_rate;

initial equation

ZFYVE9_1 = initial_state[1];
TGFB1_p_TGFBR_ZFYVE9_SMAD2_3_Phosphorylation_Motif_Mutants = initial_state[2];

equation

reaction_3304394_rate = rate_constants[1] * (TGFB1_p_TGFBR_ZFYVE9_SMAD2_3_Phosphorylation_Motif_Mutants^1) * (ATP^2) ;

der(TGFB1_p_TGFBR_ZFYVE9_SMAD2_3_Phosphorylation_Motif_Mutants) = 1 * reaction_3304394_rate ;

end EarlyEndosomeMembrane;

