/* Initialization */
#include "Biosystem_model.h"
#include "Biosystem_11mix.h"
#include "Biosystem_12jac.h"
#if defined(__cplusplus)
extern "C" {
#endif

void Biosystem_functionInitialEquations_0(DATA *data, threadData_t *threadData);

/*
equation index: 1
type: SIMPLE_ASSIGN
c_1._SPO11_Dimer = c_1.initial_state[30]
*/
void Biosystem_eqFunction_1(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,1};
  data->localData[0]->realVars[28] /* c_1.SPO11_Dimer STATE(1,c_1.reaction_912368_rate) */ = data->simulationInfo->realParameter[29];
  TRACE_POP
}

/*
equation index: 2
type: SIMPLE_ASSIGN
c_1._SPO11_double_strand_break_with_3_single_strand_breaks = c_1.initial_state[29]
*/
void Biosystem_eqFunction_2(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,2};
  data->localData[0]->realVars[30] /* c_1.SPO11_double_strand_break_with_3_single_strand_breaks STATE(1) */ = data->simulationInfo->realParameter[28];
  TRACE_POP
}
extern void Biosystem_eqFunction_80(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_81(DATA *data, threadData_t *threadData);


/*
equation index: 5
type: SIMPLE_ASSIGN
c_1._SPO11_double_stand_break = c_1.initial_state[28]
*/
void Biosystem_eqFunction_5(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,5};
  data->localData[0]->realVars[29] /* c_1.SPO11_double_stand_break STATE(1) */ = data->simulationInfo->realParameter[27];
  TRACE_POP
}
extern void Biosystem_eqFunction_85(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_87(DATA *data, threadData_t *threadData);


/*
equation index: 8
type: SIMPLE_ASSIGN
c_1._ATM = c_1.initial_state[24]
*/
void Biosystem_eqFunction_8(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,8};
  data->localData[0]->realVars[0] /* c_1.ATM STATE(1,c_1.reaction_912503_rate) */ = data->simulationInfo->realParameter[23];
  TRACE_POP
}

/*
equation index: 9
type: SIMPLE_ASSIGN
c_1._RAD51 = c_1.initial_state[23]
*/
void Biosystem_eqFunction_9(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,9};
  data->localData[0]->realVars[25] /* c_1.RAD51 STATE(1) */ = data->simulationInfo->realParameter[22];
  TRACE_POP
}

/*
equation index: 10
type: SIMPLE_ASSIGN
c_1._DMC1 = c_1.initial_state[22]
*/
void Biosystem_eqFunction_10(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,10};
  data->localData[0]->realVars[9] /* c_1.DMC1 STATE(1) */ = data->simulationInfo->realParameter[21];
  TRACE_POP
}

/*
equation index: 11
type: SIMPLE_ASSIGN
c_1._BRCA1 = c_1.initial_state[21]
*/
void Biosystem_eqFunction_11(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,11};
  data->localData[0]->realVars[4] /* c_1.BRCA1 STATE(1,c_1.reaction_912503_rate) */ = data->simulationInfo->realParameter[20];
  TRACE_POP
}

/*
equation index: 12
type: SIMPLE_ASSIGN
c_1._s_3_overhanging_DNA_at_resected_DSB_ends = c_1.initial_state[20]
*/
void Biosystem_eqFunction_12(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,12};
  data->localData[0]->realVars[34] /* c_1.s_3_overhanging_DNA_at_resected_DSB_ends STATE(1) */ = data->simulationInfo->realParameter[19];
  TRACE_POP
}

/*
equation index: 13
type: SIMPLE_ASSIGN
c_1._H2AFX_Nucleosome = c_1.initial_state[19]
*/
void Biosystem_eqFunction_13(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,13};
  data->localData[0]->realVars[11] /* c_1.H2AFX_Nucleosome STATE(1,c_1.reaction_912503_rate) */ = data->simulationInfo->realParameter[18];
  TRACE_POP
}

/*
equation index: 14
type: SIMPLE_ASSIGN
c_1._Meiotic_D_loop_complex = c_1.initial_state[17]
*/
void Biosystem_eqFunction_14(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,14};
  data->localData[0]->realVars[18] /* c_1.Meiotic_D_loop_complex STATE(1) */ = data->simulationInfo->realParameter[16];
  TRACE_POP
}

/*
equation index: 15
type: SIMPLE_ASSIGN
c_1._Meiotic_single_stranded_DNA_complex = c_1.initial_state[16]
*/
void Biosystem_eqFunction_15(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,15};
  data->localData[0]->realVars[20] /* c_1.Meiotic_single_stranded_DNA_complex STATE(1) */ = data->simulationInfo->realParameter[15];
  TRACE_POP
}

/*
equation index: 16
type: SIMPLE_ASSIGN
c_1._PRDM9 = c_1.initial_state[14]
*/
void Biosystem_eqFunction_16(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,16};
  data->localData[0]->realVars[23] /* c_1.PRDM9 STATE(1,c_1.reaction_912363_rate) */ = data->simulationInfo->realParameter[13];
  TRACE_POP
}

/*
equation index: 17
type: SIMPLE_ASSIGN
c_1._DNA = c_1.initial_state[13]
*/
void Biosystem_eqFunction_17(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,17};
  data->localData[0]->realVars[10] /* c_1.DNA STATE(1) */ = data->simulationInfo->realParameter[12];
  TRACE_POP
}
extern void Biosystem_eqFunction_83(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_84(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_86(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_95(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_98(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_101(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_100(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_99(DATA *data, threadData_t *threadData);


/*
equation index: 26
type: SIMPLE_ASSIGN
c_1._RPA_heterotrimer = c_1.initial_state[12]
*/
void Biosystem_eqFunction_26(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,26};
  data->localData[0]->realVars[27] /* c_1.RPA_heterotrimer STATE(1) */ = data->simulationInfo->realParameter[11];
  TRACE_POP
}

/*
equation index: 27
type: SIMPLE_ASSIGN
c_1._MSH5 = c_1.initial_state[11]
*/
void Biosystem_eqFunction_27(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,27};
  data->localData[0]->realVars[17] /* c_1.MSH5 STATE(1) */ = data->simulationInfo->realParameter[10];
  TRACE_POP
}

/*
equation index: 28
type: SIMPLE_ASSIGN
c_1._MSH4 = c_1.initial_state[9]
*/
void Biosystem_eqFunction_28(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,28};
  data->localData[0]->realVars[16] /* c_1.MSH4 STATE(1) */ = data->simulationInfo->realParameter[8];
  TRACE_POP
}

/*
equation index: 29
type: SIMPLE_ASSIGN
c_1._TOP3A = c_1.initial_state[8]
*/
void Biosystem_eqFunction_29(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,29};
  data->localData[0]->realVars[33] /* c_1.TOP3A STATE(1) */ = data->simulationInfo->realParameter[7];
  TRACE_POP
}

/*
equation index: 30
type: SIMPLE_ASSIGN
c_1._BRCA2 = c_1.initial_state[7]
*/
void Biosystem_eqFunction_30(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,30};
  data->localData[0]->realVars[5] /* c_1.BRCA2 STATE(1) */ = data->simulationInfo->realParameter[6];
  TRACE_POP
}

/*
equation index: 31
type: SIMPLE_ASSIGN
c_1._BLM = c_1.initial_state[6]
*/
void Biosystem_eqFunction_31(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,31};
  data->localData[0]->realVars[3] /* c_1.BLM STATE(1) */ = data->simulationInfo->realParameter[5];
  TRACE_POP
}
extern void Biosystem_eqFunction_82(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_97(DATA *data, threadData_t *threadData);


/*
equation index: 34
type: SIMPLE_ASSIGN
c_1._CDK4 = c_1.initial_state[5]
*/
void Biosystem_eqFunction_34(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,34};
  data->localData[0]->realVars[7] /* c_1.CDK4 STATE(1) */ = data->simulationInfo->realParameter[4];
  TRACE_POP
}
extern void Biosystem_eqFunction_88(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_94(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_93(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_92(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_91(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_90(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_89(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_96(DATA *data, threadData_t *threadData);


/*
equation index: 43
type: SIMPLE_ASSIGN
c_1._CDK2 = c_1.initial_state[4]
*/
void Biosystem_eqFunction_43(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,43};
  data->localData[0]->realVars[6] /* c_1.CDK2 STATE(1,c_1.reaction_912429_rate) */ = data->simulationInfo->realParameter[3];
  TRACE_POP
}

/*
equation index: 44
type: SIMPLE_ASSIGN
c_1._MLH3 = c_1.initial_state[3]
*/
void Biosystem_eqFunction_44(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,44};
  data->localData[0]->realVars[14] /* c_1.MLH3 STATE(1,c_1.reaction_912429_rate) */ = data->simulationInfo->realParameter[2];
  TRACE_POP
}

/*
equation index: 45
type: SIMPLE_ASSIGN
c_1._MLH1 = c_1.initial_state[2]
*/
void Biosystem_eqFunction_45(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,45};
  data->localData[0]->realVars[13] /* c_1.MLH1 STATE(1,c_1.reaction_912429_rate) */ = data->simulationInfo->realParameter[1];
  TRACE_POP
}

/*
equation index: 46
type: SIMPLE_ASSIGN
c_1._Meiotic_Holliday_Junction = c_1.initial_state[1]
*/
void Biosystem_eqFunction_46(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,46};
  data->localData[0]->realVars[19] /* c_1.Meiotic_Holliday_Junction STATE(1) */ = data->simulationInfo->realParameter[0];
  TRACE_POP
}
extern void Biosystem_eqFunction_102(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_114(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_113(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_112(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_111(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_110(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_109(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_108(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_107(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_106(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_105(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_104(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_103(DATA *data, threadData_t *threadData);


/*
equation index: 60
type: SIMPLE_ASSIGN
c_1._AdoMet = c_1.initial_state[32]
*/
void Biosystem_eqFunction_60(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,60};
  data->localData[0]->realVars[2] /* c_1.AdoMet STATE(1) */ = data->simulationInfo->realParameter[31];
  TRACE_POP
}

/*
equation index: 61
type: SIMPLE_ASSIGN
c_1._Nucleosome_with_H3K4me2 = c_1.initial_state[31]
*/
void Biosystem_eqFunction_61(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,61};
  data->localData[0]->realVars[21] /* c_1.Nucleosome_with_H3K4me2 STATE(1,c_1.reaction_1214188_rate) */ = data->simulationInfo->realParameter[30];
  TRACE_POP
}
extern void Biosystem_eqFunction_115(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_119(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_118(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_117(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_116(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_120(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_121(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_122(DATA *data, threadData_t *threadData);

extern void Biosystem_eqFunction_123(DATA *data, threadData_t *threadData);


/*
equation index: 71
type: SIMPLE_ASSIGN
c_1._SPO11_oligonucleotide = c_1.initial_state[35]
*/
void Biosystem_eqFunction_71(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,71};
  data->localData[0]->realVars[31] /* c_1.SPO11_oligonucleotide STATE(1,c_1.reaction_9023943_rate) */ = data->simulationInfo->realParameter[34];
  TRACE_POP
}

/*
equation index: 72
type: SIMPLE_ASSIGN
c_1._AdoHcy = c_1.initial_state[34]
*/
void Biosystem_eqFunction_72(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,72};
  data->localData[0]->realVars[1] /* c_1.AdoHcy STATE(1) */ = data->simulationInfo->realParameter[33];
  TRACE_POP
}

/*
equation index: 73
type: SIMPLE_ASSIGN
c_1._Nucleosome_with_H3K4me3 = c_1.initial_state[33]
*/
void Biosystem_eqFunction_73(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,73};
  data->localData[0]->realVars[22] /* c_1.Nucleosome_with_H3K4me3 STATE(1,c_1.reaction_1214188_rate) */ = data->simulationInfo->realParameter[32];
  TRACE_POP
}

/*
equation index: 74
type: SIMPLE_ASSIGN
c_1._MRN_CtIP = c_1.initial_state[27]
*/
void Biosystem_eqFunction_74(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,74};
  data->localData[0]->realVars[15] /* c_1.MRN_CtIP STATE(1) */ = data->simulationInfo->realParameter[26];
  TRACE_POP
}

/*
equation index: 75
type: SIMPLE_ASSIGN
c_1._RAD51C = c_1.initial_state[26]
*/
void Biosystem_eqFunction_75(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,75};
  data->localData[0]->realVars[26] /* c_1.RAD51C STATE(1) */ = data->simulationInfo->realParameter[25];
  TRACE_POP
}

/*
equation index: 76
type: SIMPLE_ASSIGN
c_1._TEX15 = c_1.initial_state[25]
*/
void Biosystem_eqFunction_76(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,76};
  data->localData[0]->realVars[32] /* c_1.TEX15 STATE(1) */ = data->simulationInfo->realParameter[24];
  TRACE_POP
}

/*
equation index: 77
type: SIMPLE_ASSIGN
c_1._HOP2_TBPIP_MND1 = c_1.initial_state[18]
*/
void Biosystem_eqFunction_77(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,77};
  data->localData[0]->realVars[12] /* c_1.HOP2_TBPIP_MND1 STATE(1) */ = data->simulationInfo->realParameter[17];
  TRACE_POP
}

/*
equation index: 78
type: SIMPLE_ASSIGN
c_1._PRDM9_DNA = c_1.initial_state[15]
*/
void Biosystem_eqFunction_78(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,78};
  data->localData[0]->realVars[24] /* c_1.PRDM9_DNA STATE(1,c_1.reaction_912363_rate) */ = data->simulationInfo->realParameter[14];
  TRACE_POP
}

/*
equation index: 79
type: SIMPLE_ASSIGN
c_1._Cleaved_Meiotic_Holliday_Junction = c_1.initial_state[10]
*/
void Biosystem_eqFunction_79(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,79};
  data->localData[0]->realVars[8] /* c_1.Cleaved_Meiotic_Holliday_Junction STATE(1,c_1.reaction_912429_rate) */ = data->simulationInfo->realParameter[9];
  TRACE_POP
}
OMC_DISABLE_OPT
void Biosystem_functionInitialEquations_0(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  Biosystem_eqFunction_1(data, threadData);
  Biosystem_eqFunction_2(data, threadData);
  Biosystem_eqFunction_80(data, threadData);
  Biosystem_eqFunction_81(data, threadData);
  Biosystem_eqFunction_5(data, threadData);
  Biosystem_eqFunction_85(data, threadData);
  Biosystem_eqFunction_87(data, threadData);
  Biosystem_eqFunction_8(data, threadData);
  Biosystem_eqFunction_9(data, threadData);
  Biosystem_eqFunction_10(data, threadData);
  Biosystem_eqFunction_11(data, threadData);
  Biosystem_eqFunction_12(data, threadData);
  Biosystem_eqFunction_13(data, threadData);
  Biosystem_eqFunction_14(data, threadData);
  Biosystem_eqFunction_15(data, threadData);
  Biosystem_eqFunction_16(data, threadData);
  Biosystem_eqFunction_17(data, threadData);
  Biosystem_eqFunction_83(data, threadData);
  Biosystem_eqFunction_84(data, threadData);
  Biosystem_eqFunction_86(data, threadData);
  Biosystem_eqFunction_95(data, threadData);
  Biosystem_eqFunction_98(data, threadData);
  Biosystem_eqFunction_101(data, threadData);
  Biosystem_eqFunction_100(data, threadData);
  Biosystem_eqFunction_99(data, threadData);
  Biosystem_eqFunction_26(data, threadData);
  Biosystem_eqFunction_27(data, threadData);
  Biosystem_eqFunction_28(data, threadData);
  Biosystem_eqFunction_29(data, threadData);
  Biosystem_eqFunction_30(data, threadData);
  Biosystem_eqFunction_31(data, threadData);
  Biosystem_eqFunction_82(data, threadData);
  Biosystem_eqFunction_97(data, threadData);
  Biosystem_eqFunction_34(data, threadData);
  Biosystem_eqFunction_88(data, threadData);
  Biosystem_eqFunction_94(data, threadData);
  Biosystem_eqFunction_93(data, threadData);
  Biosystem_eqFunction_92(data, threadData);
  Biosystem_eqFunction_91(data, threadData);
  Biosystem_eqFunction_90(data, threadData);
  Biosystem_eqFunction_89(data, threadData);
  Biosystem_eqFunction_96(data, threadData);
  Biosystem_eqFunction_43(data, threadData);
  Biosystem_eqFunction_44(data, threadData);
  Biosystem_eqFunction_45(data, threadData);
  Biosystem_eqFunction_46(data, threadData);
  Biosystem_eqFunction_102(data, threadData);
  Biosystem_eqFunction_114(data, threadData);
  Biosystem_eqFunction_113(data, threadData);
  Biosystem_eqFunction_112(data, threadData);
  Biosystem_eqFunction_111(data, threadData);
  Biosystem_eqFunction_110(data, threadData);
  Biosystem_eqFunction_109(data, threadData);
  Biosystem_eqFunction_108(data, threadData);
  Biosystem_eqFunction_107(data, threadData);
  Biosystem_eqFunction_106(data, threadData);
  Biosystem_eqFunction_105(data, threadData);
  Biosystem_eqFunction_104(data, threadData);
  Biosystem_eqFunction_103(data, threadData);
  Biosystem_eqFunction_60(data, threadData);
  Biosystem_eqFunction_61(data, threadData);
  Biosystem_eqFunction_115(data, threadData);
  Biosystem_eqFunction_119(data, threadData);
  Biosystem_eqFunction_118(data, threadData);
  Biosystem_eqFunction_117(data, threadData);
  Biosystem_eqFunction_116(data, threadData);
  Biosystem_eqFunction_120(data, threadData);
  Biosystem_eqFunction_121(data, threadData);
  Biosystem_eqFunction_122(data, threadData);
  Biosystem_eqFunction_123(data, threadData);
  Biosystem_eqFunction_71(data, threadData);
  Biosystem_eqFunction_72(data, threadData);
  Biosystem_eqFunction_73(data, threadData);
  Biosystem_eqFunction_74(data, threadData);
  Biosystem_eqFunction_75(data, threadData);
  Biosystem_eqFunction_76(data, threadData);
  Biosystem_eqFunction_77(data, threadData);
  Biosystem_eqFunction_78(data, threadData);
  Biosystem_eqFunction_79(data, threadData);
  TRACE_POP
}


int Biosystem_functionInitialEquations(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  data->simulationInfo->discreteCall = 1;
  Biosystem_functionInitialEquations_0(data, threadData);
  data->simulationInfo->discreteCall = 0;
  
  TRACE_POP
  return 0;
}


int Biosystem_functionInitialEquations_lambda0(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  data->simulationInfo->discreteCall = 1;
  data->simulationInfo->discreteCall = 0;
  
  TRACE_POP
  return 0;
}
int Biosystem_functionRemovedInitialEquations(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int *equationIndexes = NULL;
  double res = 0.0;

  
  TRACE_POP
  return 0;
}


#if defined(__cplusplus)
}
#endif

