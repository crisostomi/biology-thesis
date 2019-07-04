block Phagolysosome

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];


Amount A2E;


initial equation

A2E = initial_state[1];

equation



end Phagolysosome;

