class Biosystem

type Amount = Real(unit="mol*10^(-6)");

parameter Amount init_c_1[2];
parameter Amount init_c_2[1];
parameter Amount init_c_3[44];
parameter Amount init_c_4[5];
parameter Amount init_c_5[1];
parameter Amount init_c_6[217];
parameter Amount init_c_7[4];
parameter Amount init_c_8[11];
parameter Amount init_c_9[137];
parameter Amount init_c_10[18];
parameter Amount init_c_11[8];
parameter Amount init_c_12[2];
parameter Amount init_c_13[1];

parameter Real rates_c_1[1];
parameter Real rates_c_2[0];
parameter Real rates_c_3[21];
parameter Real rates_c_4[1];
parameter Real rates_c_5[0];
parameter Real rates_c_6[147];
parameter Real rates_c_7[2];
parameter Real rates_c_8[1];
parameter Real rates_c_9[99];
parameter Real rates_c_10[9];
parameter Real rates_c_11[6];
parameter Real rates_c_12[2];
parameter Real rates_c_13[0];

EarlyEndosomeMembrane c_1(initial_state=init_c_1, rate_constants=rates_c_1);
EndoplasmicReticulumQualityControlCompartment c_2(initial_state=init_c_2, rate_constants=rates_c_2);
Nucleoplasm c_3(initial_state=init_c_3, rate_constants=rates_c_3);
EndoplasmicReticulumLumen c_4(initial_state=init_c_4, rate_constants=rates_c_4);
Cytoplasm c_5(initial_state=init_c_5, rate_constants=rates_c_5);
PlasmaMembrane c_6(initial_state=init_c_6, rate_constants=rates_c_6);
PhotoreceptorOuterSegmentMembrane c_7(initial_state=init_c_7, rate_constants=rates_c_7);
PhotoreceptorDiscMembrane c_8(initial_state=init_c_8, rate_constants=rates_c_8);
Cytosol c_9(initial_state=init_c_9, rate_constants=rates_c_9);
ExtracellularRegion c_10(initial_state=init_c_10, rate_constants=rates_c_10);
EndoplasmicReticulumMembrane c_11(initial_state=init_c_11, rate_constants=rates_c_11);
PhotoreceptorInnerSegmentMembrane c_12(initial_state=init_c_12, rate_constants=rates_c_12);
Phagolysosome c_13(initial_state=init_c_13, rate_constants=rates_c_13);

end Biosystem;

