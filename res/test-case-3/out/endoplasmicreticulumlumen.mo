block EndoplasmicReticulumLumen

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];


Amount LGK974;
Amount SHH_variants;
Amount SHH_processing_variants_OS9_ERLEC1;
Amount CHOL;
Amount N_terminal_CHOL_Hh_fragments;

Real reaction_5358460_rate;

initial equation

LGK974 = initial_state[1];
SHH_variants = initial_state[2];
SHH_processing_variants_OS9_ERLEC1 = initial_state[3];
CHOL = initial_state[4];
N_terminal_CHOL_Hh_fragments = initial_state[5];

equation

reaction_5358460_rate = rate_constants[1] * (SHH_variants^1) * (CHOL^1) ;

der(SHH_variants) = 1 * reaction_5358460_rate ;
der(CHOL) = 1 * reaction_5358460_rate ;

end EndoplasmicReticulumLumen;

