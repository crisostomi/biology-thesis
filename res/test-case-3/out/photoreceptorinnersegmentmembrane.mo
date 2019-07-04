block PhotoreceptorInnerSegmentMembrane

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input H_;
input NADPH;
input atRAL;
input H_;
input NADPH;
input atRAL;

Amount RDH12_A126V;
Amount RDH12_mutants;

Real reaction_2471670_rate;
Real reaction_2466861_rate;

initial equation

RDH12_A126V = initial_state[1];
RDH12_mutants = initial_state[2];

equation

reaction_2471670_rate = rate_constants[1] * (H_^1) * (NADPH^1) * (atRAL^1) ;
reaction_2466861_rate = rate_constants[2] * (H_^1) * (NADPH^1) * (atRAL^1) ;


end PhotoreceptorInnerSegmentMembrane;

