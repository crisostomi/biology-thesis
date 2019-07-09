class Biosystem

type Amount = Real(unit="mol*10^(-6)");

parameter Amount init_c_1[3];
parameter Amount init_c_2[18];
parameter Amount init_c_3[1];
parameter Amount init_c_4[16];
parameter Amount init_c_5[6];
parameter Amount init_c_6[1];

parameter Rate rates_c_1[0];
parameter Rate rates_c_2[8];
parameter Rate rates_c_3[1];
parameter Rate rates_c_4[9];
parameter Rate rates_c_5[3];
parameter Rate rates_c_6[1];

GolgiMembrane c_1(initial_state=init_c_1, rate_constants=rates_c_1);
PlasmaMembrane c_2(initial_state=init_c_2, rate_constants=rates_c_2);
MitochondrialOuterMembrane c_3(initial_state=init_c_3, rate_constants=rates_c_3);
Cytosol c_4(initial_state=init_c_4, rate_constants=rates_c_4);
GolgiLumen c_5(initial_state=init_c_5, rate_constants=rates_c_5);
ExtracellularRegion c_6(initial_state=init_c_6, rate_constants=rates_c_6);
Environment env;

equation
	// Sinks
	env.compartment_876__p_5Y_EGFRvIII_p_Y349_Y350_SHC1_GRB2_SOS1 = c_2.p_5Y_EGFRvIII_p_Y349_Y350_SHC1_GRB2_SOS1;
	env.compartment_70101__HSP90_Benzoquinoid_ansamycins = c_4.HSP90_Benzoquinoid_ansamycins;
	env.compartment_876__p_4Y_PLCG1 = c_2.p_4Y_PLCG1;
	env.compartment_70101__GDP = c_4.GDP;
	env.compartment_20699__B4GALT1_LALBA = c_1.B4GALT1_LALBA;
	env.compartment_876__PI_3_4_5_P3 = c_2.PI_3_4_5_P3;
	env.compartment_17963__Lac = c_5.Lac;
	env.compartment_876__p_5Y_EGFRvIII_GRB2_GAB1_PI3K = c_2.p_5Y_EGFRvIII_GRB2_GAB1_PI3K;
	env.compartment_876__p_5Y_EGFRvIII_GRB2_SOS1 = c_2.p_5Y_EGFRvIII_GRB2_SOS1;
	env.compartment_70101__ADP = c_4.ADP;
	env.compartment_876__p21_RAS_GTP = c_2.p21_RAS_GTP;
	env.compartment_17963__UDP = c_5.UDP;

	// Sources
	env.compartment_984__EGF = c_6.EGF;
	env.compartment_876__EGFRvIII = c_2.EGFRvIII;
	env.compartment_876__PLCG1 = c_2.PLCG1;
	env.compartment_876__PI_4_5_P2 = c_2.PI_4_5_P2;
	env.compartment_70101__GRB2_GAB1 = c_4.GRB2_GAB1;
	env.compartment_17963__LALBA = c_5.LALBA;
	env.compartment_70101__HSP90 = c_4.HSP90;
	env.compartment_70101__Glc = c_4.Glc;
	env.compartment_70101__Benzoquinoid_ansamycins = c_4.Benzoquinoid_ansamycins;
	env.compartment_70101__GTP = c_4.GTP;
	env.compartment_70101__SHC1 = c_4.SHC1;
	env.compartment_17963__UDP_Gal = c_5.UDP_Gal;
	env.compartment_70101__ATP = c_4.ATP;
	env.compartment_70101__CBL = c_4.CBL;
	env.compartment_876__p21_RAS_GDP = c_2.p21_RAS_GDP;
	env.compartment_70101__PIK3CA_PIK3R1 = c_4.PIK3CA_PIK3R1;
	env.compartment_20699__B4GALT1 = c_1.B4GALT1;

	
end Biosystem;

