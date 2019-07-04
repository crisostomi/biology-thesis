class Biosystem

type Amount = Real(unit="mol*10^(-6)");

parameter Amount init_c_1[2];
parameter Amount init_c_2[2];
parameter Amount init_c_3[11];
parameter Amount init_c_4[1];
parameter Amount init_c_5[4];
parameter Amount init_c_6[1];
parameter Amount init_c_7[8];
parameter Amount init_c_8[18];
parameter Amount init_c_9[137];
parameter Amount init_c_10[44];
parameter Amount init_c_11[5];
parameter Amount init_c_12[217];
parameter Amount init_c_13[1];

parameter Real rates_c_1[1];
parameter Real rates_c_2[2];
parameter Real rates_c_3[1];
parameter Real rates_c_4[0];
parameter Real rates_c_5[2];
parameter Real rates_c_6[0];
parameter Real rates_c_7[6];
parameter Real rates_c_8[9];
parameter Real rates_c_9[99];
parameter Real rates_c_10[21];
parameter Real rates_c_11[1];
parameter Real rates_c_12[147];
parameter Real rates_c_13[0];

EarlyEndosomeMembrane c_1(initial_state=init_c_1, rate_constants=rates_c_1);
PhotoreceptorInnerSegmentMembrane c_2(initial_state=init_c_2, rate_constants=rates_c_2);
PhotoreceptorDiscMembrane c_3(initial_state=init_c_3, rate_constants=rates_c_3);
Cytoplasm c_4(initial_state=init_c_4, rate_constants=rates_c_4);
PhotoreceptorOuterSegmentMembrane c_5(initial_state=init_c_5, rate_constants=rates_c_5);
EndoplasmicReticulumQualityControlCompartment c_6(initial_state=init_c_6, rate_constants=rates_c_6);
EndoplasmicReticulumMembrane c_7(initial_state=init_c_7, rate_constants=rates_c_7);
ExtracellularRegion c_8(initial_state=init_c_8, rate_constants=rates_c_8);
Cytosol c_9(initial_state=init_c_9, rate_constants=rates_c_9);
Nucleoplasm c_10(initial_state=init_c_10, rate_constants=rates_c_10);
EndoplasmicReticulumLumen c_11(initial_state=init_c_11, rate_constants=rates_c_11);
PlasmaMembrane c_12(initial_state=init_c_12, rate_constants=rates_c_12);
Phagolysosome c_13(initial_state=init_c_13, rate_constants=rates_c_13);

end Biosystem;

