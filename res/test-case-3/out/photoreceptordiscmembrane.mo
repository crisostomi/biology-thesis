block PhotoreceptorDiscMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input NRPE;
input H2O;
input ATP;

Amount OPN1SW;
Amount OPN1LW_mutants;
Amount OPN1MW;
Amount ABCA4_mutants;
Amount OPN1SW_mutants;
Amount OPN1LW;
Amount OPN1MW_C203R;
Amount OPN1LW_G338E;
Amount OPN1MW_W177R;
Amount atRAL;
Amount PE;

Real reaction_2466802_rate;

initial equation

OPN1SW = initial_state[1];
OPN1LW_mutants = initial_state[2];
OPN1MW = initial_state[3];
ABCA4_mutants = initial_state[4];
OPN1SW_mutants = initial_state[5];
OPN1LW = initial_state[6];
OPN1MW_C203R = initial_state[7];
OPN1LW_G338E = initial_state[8];
OPN1MW_W177R = initial_state[9];
atRAL = initial_state[10];
PE = initial_state[11];

equation

reaction_2466802_rate = rate_constants[1] * (NRPE^1) * (H2O^1) * (ATP^1) ;


end PhotoreceptorDiscMembrane;

