block EndoplasmicReticulumMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input LGK974;
input OS9_ERLEC1;
input SHH_variants;
input Ub;
input SHH_processing_variants_OS9_ERLEC1;
input N_terminal_CHOL_Hh_fragments;
input FACYLs;
input RBP1_atROL;

Amount PORCN;
Amount PORCN_LGK974;
Amount SHH_processing_variants_ERLEC_OS9_SEL1_SYVN1_dimer_DERL2_VCP_hexamer;
Amount ub_SHH_processing_variants_ERLEC_OS9_SEL1_SYVN1_dimer_DERL2_VCP_hexamer;
Amount SEL1_SYVN1_dimer_DERL2_VCP_hexamer;
Amount HHAT_G287V;
Amount LRAT_S175R;
Amount RDH5_mutants;

Real reaction_5340560_rate;
Real reaction_5362450_rate;
Real reaction_5483238_rate;
Real reaction_5387386_rate;
Real reaction_5483229_rate;
Real reaction_2466710_rate;

initial equation

PORCN = initial_state[1];
PORCN_LGK974 = initial_state[2];
SHH_processing_variants_ERLEC_OS9_SEL1_SYVN1_dimer_DERL2_VCP_hexamer = initial_state[3];
ub_SHH_processing_variants_ERLEC_OS9_SEL1_SYVN1_dimer_DERL2_VCP_hexamer = initial_state[4];
SEL1_SYVN1_dimer_DERL2_VCP_hexamer = initial_state[5];
HHAT_G287V = initial_state[6];
LRAT_S175R = initial_state[7];
RDH5_mutants = initial_state[8];

equation

reaction_5340560_rate = rate_constants[1] * (PORCN^1) * (LGK974^1) ;
reaction_5362450_rate = rate_constants[2] * (OS9_ERLEC1^1) * (SHH_variants^1) ;
reaction_5483238_rate = rate_constants[3] * (Ub^1) * (SHH_processing_variants_ERLEC_OS9_SEL1_SYVN1_dimer_DERL2_VCP_hexamer^1) ;
reaction_5387386_rate = rate_constants[4] * (SEL1_SYVN1_dimer_DERL2_VCP_hexamer^1) * (SHH_processing_variants_OS9_ERLEC1^1) ;
reaction_5483229_rate = rate_constants[5] * (N_terminal_CHOL_Hh_fragments^1) ;
reaction_2466710_rate = rate_constants[6] * (FACYLs^1) * (RBP1_atROL^1) ;

der(PORCN) = 1 * reaction_5340560_rate ;
der(PORCN_LGK974) = 1 * reaction_5340560_rate ;
der(SHH_processing_variants_ERLEC_OS9_SEL1_SYVN1_dimer_DERL2_VCP_hexamer) = 1 * reaction_5483238_rate + 1 * reaction_5387386_rate ;
der(ub_SHH_processing_variants_ERLEC_OS9_SEL1_SYVN1_dimer_DERL2_VCP_hexamer) = 1 * reaction_5483238_rate ;
der(SEL1_SYVN1_dimer_DERL2_VCP_hexamer) = 1 * reaction_5387386_rate ;

end EndoplasmicReticulumMembrane;

