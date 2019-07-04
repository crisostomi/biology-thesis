class Biosystem

type Amount = Real(unit="mol*10^(-6)");

parameter Amount init_c_1[35];

parameter Real rates_c_1[9];

Nucleoplasm c_1(initial_state=init_c_1, rate_constants=rates_c_1);

end Biosystem;

