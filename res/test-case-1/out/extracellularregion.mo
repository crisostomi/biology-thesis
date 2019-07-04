block ExtracellularRegion

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input EGFRvIII_mutant_HSP90_CDC37;

Amount EGF;

Real reaction_5638137_rate;

initial equation

EGF = initial_state[1];

equation

reaction_5638137_rate = rate_constants[1] * (EGF^1) * (EGFRvIII_mutant_HSP90_CDC37^1) ;

der(EGF) = 1 * reaction_5638137_rate ;

end ExtracellularRegion;

