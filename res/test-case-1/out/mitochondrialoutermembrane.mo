block MitochondrialOuterMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input porphyrin;
input ATP;
input H2O;

Amount ABCB6_mutants;

Real reaction_5683355_rate;

initial equation

ABCB6_mutants = initial_state[1];

equation

reaction_5683355_rate = rate_constants[1] * (porphyrin^1) * (ATP^1) * (H2O^1) ;


end MitochondrialOuterMembrane;

