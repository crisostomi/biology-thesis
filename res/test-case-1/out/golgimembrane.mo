block GolgiMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];


Amount B4GALT1_LALBA;
Amount SLC2A1_tetramer;
Amount B4GALT1;


initial equation

B4GALT1_LALBA = initial_state[1];
SLC2A1_tetramer = initial_state[2];
B4GALT1 = initial_state[3];

equation



end GolgiMembrane;

