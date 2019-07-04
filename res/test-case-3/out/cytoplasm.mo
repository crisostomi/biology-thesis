block Cytoplasm

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];


Amount phosphate;


initial equation

phosphate = initial_state[1];

equation



end Cytoplasm;

