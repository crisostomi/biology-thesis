block EndoplasmicReticulumQualityControlCompartment

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];


Amount OS9_ERLEC1;


initial equation

OS9_ERLEC1 = initial_state[1];

equation



end EndoplasmicReticulumQualityControlCompartment;

