block PhotoreceptorOuterSegmentMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input PE;
input atRAL;

Amount NRPE;
Amount A2E;
Amount A2PE;
Amount NAPEPLD;

Real reaction_2467761_rate;
Real reaction_2466846_rate;

initial equation

NRPE = initial_state[1];
A2E = initial_state[2];
A2PE = initial_state[3];
NAPEPLD = initial_state[4];

equation

reaction_2467761_rate = rate_constants[1] * (A2E^1) ;
reaction_2466846_rate = rate_constants[2] * (PE^1) * (atRAL^1) ;

der(NRPE) = 1 * reaction_2466846_rate ;
der(A2E) = 1 * reaction_2467761_rate ;

end PhotoreceptorOuterSegmentMembrane;

