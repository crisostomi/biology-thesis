class Biosystem

type Amount = Real(unit="mol*10^(-6)");

parameter Amount init_c_1[1];
parameter Amount init_c_2[3];
parameter Amount init_c_3[16];
parameter Amount init_c_4[18];
parameter Amount init_c_5[1];
parameter Amount init_c_6[6];

parameter Real rates_c_1[1];
parameter Real rates_c_2[0];
parameter Real rates_c_3[9];
parameter Real rates_c_4[8];
parameter Real rates_c_5[1];
parameter Real rates_c_6[3];

MitochondrialOuterMembrane c_1(initial_state=init_c_1, rate_constants=rates_c_1);
GolgiMembrane c_2(initial_state=init_c_2, rate_constants=rates_c_2);
Cytosol c_3(initial_state=init_c_3, rate_constants=rates_c_3);
PlasmaMembrane c_4(initial_state=init_c_4, rate_constants=rates_c_4);
ExtracellularRegion c_5(initial_state=init_c_5, rate_constants=rates_c_5);
GolgiLumen c_6(initial_state=init_c_6, rate_constants=rates_c_6);

end Biosystem;

