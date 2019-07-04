block GolgiLumen

type Amount = Real(unit="mol*10^(-6)");

parameter Amount initial_state[num_species];
parameter Real rate_constants[num_reactions];

input Glc;
input B4GALT1;

Amount Glc;
Amount UDP_Gal;
Amount UDP;
Amount Lac;
Amount LALBA;
Amount Mn2_;

Real reaction_5653878_rate;
Real reaction_5653873_rate;
Real reaction_5653886_rate;

initial equation

Glc = initial_state[1];
UDP_Gal = initial_state[2];
UDP = initial_state[3];
Lac = initial_state[4];
LALBA = initial_state[5];
Mn2_ = initial_state[6];

equation

reaction_5653878_rate = rate_constants[1] * (UDP_Gal^1) * (Glc^1) ;
reaction_5653873_rate = rate_constants[2] * (Glc^1) ;
reaction_5653886_rate = rate_constants[3] * (B4GALT1^1) * (LALBA^1) ;

der(Glc) = 1 * reaction_5653878_rate + 1 * reaction_5653873_rate ;
der(UDP_Gal) = 1 * reaction_5653878_rate ;
der(UDP) = 1 * reaction_5653878_rate ;
der(Lac) = 1 * reaction_5653878_rate ;
der(LALBA) = 1 * reaction_5653886_rate ;

end GolgiLumen;

