/* Main Simulation File */

#if defined(__cplusplus)
extern "C" {
#endif

#include "Biosystem_model.h"
#include "simulation/solver/events.h"

#define prefixedName_performSimulation Biosystem_performSimulation
#define prefixedName_updateContinuousSystem Biosystem_updateContinuousSystem
#include <simulation/solver/perform_simulation.c>

#define prefixedName_performQSSSimulation Biosystem_performQSSSimulation
#include <simulation/solver/perform_qss_simulation.c>

/* dummy VARINFO and FILEINFO */
const FILE_INFO dummyFILE_INFO = omc_dummyFileInfo;
const VAR_INFO dummyVAR_INFO = omc_dummyVarInfo;

int Biosystem_input_function(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  
  TRACE_POP
  return 0;
}

int Biosystem_input_function_init(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  
  TRACE_POP
  return 0;
}

int Biosystem_input_function_updateStartValues(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  
  TRACE_POP
  return 0;
}

int Biosystem_inputNames(DATA *data, char ** names){
  TRACE_PUSH

  
  TRACE_POP
  return 0;
}

int Biosystem_output_function(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  
  TRACE_POP
  return 0;
}


/*
equation index: 80
type: SIMPLE_ASSIGN
c_1._reaction_9023943_rate = c_1.rate_constants[9] * c_1.SPO11_double_strand_break_with_3_single_strand_breaks
*/
void Biosystem_eqFunction_80(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,80};
  data->localData[0]->realVars[72] /* c_1.reaction_9023943_rate variable */ = (data->simulationInfo->realParameter[43]) * (data->localData[0]->realVars[30] /* c_1.SPO11_double_strand_break_with_3_single_strand_breaks STATE(1) */);
  TRACE_POP
}
/*
equation index: 81
type: SIMPLE_ASSIGN
der(c_1._SPO11_oligonucleotide) = c_1.reaction_9023943_rate
*/
void Biosystem_eqFunction_81(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,81};
  data->localData[0]->realVars[66] /* der(c_1.SPO11_oligonucleotide) STATE_DER */ = data->localData[0]->realVars[72] /* c_1.reaction_9023943_rate variable */;
  TRACE_POP
}
/*
equation index: 82
type: SIMPLE_ASSIGN
c_1._reaction_912496_rate = c_1.rate_constants[7] * c_1.MSH4 * c_1.MSH5 * c_1.Meiotic_D_loop_complex * c_1.BLM * c_1.TOP3A
*/
void Biosystem_eqFunction_82(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,82};
  data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */ = (data->simulationInfo->realParameter[41]) * ((data->localData[0]->realVars[16] /* c_1.MSH4 STATE(1) */) * ((data->localData[0]->realVars[17] /* c_1.MSH5 STATE(1) */) * ((data->localData[0]->realVars[18] /* c_1.Meiotic_D_loop_complex STATE(1) */) * ((data->localData[0]->realVars[3] /* c_1.BLM STATE(1) */) * (data->localData[0]->realVars[33] /* c_1.TOP3A STATE(1) */)))));
  TRACE_POP
}
/*
equation index: 83
type: SIMPLE_ASSIGN
c_1._reaction_912368_rate = c_1.rate_constants[6] * c_1.DNA * c_1.SPO11_Dimer
*/
void Biosystem_eqFunction_83(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,83};
  data->localData[0]->realVars[74] /* c_1.reaction_912368_rate variable */ = (data->simulationInfo->realParameter[40]) * ((data->localData[0]->realVars[10] /* c_1.DNA STATE(1) */) * (data->localData[0]->realVars[28] /* c_1.SPO11_Dimer STATE(1,c_1.reaction_912368_rate) */));
  TRACE_POP
}
/*
equation index: 84
type: SIMPLE_ASSIGN
der(c_1._SPO11_Dimer) = c_1.reaction_912368_rate
*/
void Biosystem_eqFunction_84(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,84};
  data->localData[0]->realVars[63] /* der(c_1.SPO11_Dimer) STATE_DER */ = data->localData[0]->realVars[74] /* c_1.reaction_912368_rate variable */;
  TRACE_POP
}
/*
equation index: 85
type: SIMPLE_ASSIGN
c_1._reaction_9023941_rate = c_1.rate_constants[5] * c_1.SPO11_double_stand_break
*/
void Biosystem_eqFunction_85(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,85};
  data->localData[0]->realVars[71] /* c_1.reaction_9023941_rate variable */ = (data->simulationInfo->realParameter[39]) * (data->localData[0]->realVars[29] /* c_1.SPO11_double_stand_break STATE(1) */);
  TRACE_POP
}
/*
equation index: 86
type: SIMPLE_ASSIGN
der(c_1._SPO11_double_stand_break) = c_1.reaction_9023941_rate + c_1.reaction_912368_rate
*/
void Biosystem_eqFunction_86(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,86};
  data->localData[0]->realVars[64] /* der(c_1.SPO11_double_stand_break) STATE_DER */ = data->localData[0]->realVars[71] /* c_1.reaction_9023941_rate variable */ + data->localData[0]->realVars[74] /* c_1.reaction_912368_rate variable */;
  TRACE_POP
}
/*
equation index: 87
type: SIMPLE_ASSIGN
der(c_1._SPO11_double_strand_break_with_3_single_strand_breaks) = c_1.reaction_9023941_rate - c_1.reaction_9023943_rate
*/
void Biosystem_eqFunction_87(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,87};
  data->localData[0]->realVars[65] /* der(c_1.SPO11_double_strand_break_with_3_single_strand_breaks) STATE_DER */ = data->localData[0]->realVars[71] /* c_1.reaction_9023941_rate variable */ - data->localData[0]->realVars[72] /* c_1.reaction_9023943_rate variable */;
  TRACE_POP
}
/*
equation index: 88
type: SIMPLE_ASSIGN
c_1._reaction_912503_rate = c_1.rate_constants[4] * c_1.BRCA2 * c_1.CDK4 * c_1.H2AFX_Nucleosome * c_1.DMC1 * c_1.RPA_heterotrimer * c_1.RAD51 * c_1.BRCA1 * c_1.ATM * c_1.s_3_overhanging_DNA_at_resected_DSB_ends
*/
void Biosystem_eqFunction_88(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,88};
  data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */ = (data->simulationInfo->realParameter[38]) * ((data->localData[0]->realVars[5] /* c_1.BRCA2 STATE(1) */) * ((data->localData[0]->realVars[7] /* c_1.CDK4 STATE(1) */) * ((data->localData[0]->realVars[11] /* c_1.H2AFX_Nucleosome STATE(1,c_1.reaction_912503_rate) */) * ((data->localData[0]->realVars[9] /* c_1.DMC1 STATE(1) */) * ((data->localData[0]->realVars[27] /* c_1.RPA_heterotrimer STATE(1) */) * ((data->localData[0]->realVars[25] /* c_1.RAD51 STATE(1) */) * ((data->localData[0]->realVars[4] /* c_1.BRCA1 STATE(1,c_1.reaction_912503_rate) */) * ((data->localData[0]->realVars[0] /* c_1.ATM STATE(1,c_1.reaction_912503_rate) */) * (data->localData[0]->realVars[34] /* c_1.s_3_overhanging_DNA_at_resected_DSB_ends STATE(1) */)))))))));
  TRACE_POP
}
/*
equation index: 89
type: SIMPLE_ASSIGN
der(c_1._H2AFX_Nucleosome) = c_1.reaction_912503_rate
*/
void Biosystem_eqFunction_89(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,89};
  data->localData[0]->realVars[46] /* der(c_1.H2AFX_Nucleosome) STATE_DER */ = data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */;
  TRACE_POP
}
/*
equation index: 90
type: SIMPLE_ASSIGN
der(c_1._s_3_overhanging_DNA_at_resected_DSB_ends) = c_1.reaction_912503_rate + c_1.reaction_9023943_rate
*/
void Biosystem_eqFunction_90(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,90};
  data->localData[0]->realVars[69] /* der(c_1.s_3_overhanging_DNA_at_resected_DSB_ends) STATE_DER */ = data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */ + data->localData[0]->realVars[72] /* c_1.reaction_9023943_rate variable */;
  TRACE_POP
}
/*
equation index: 91
type: SIMPLE_ASSIGN
der(c_1._BRCA1) = c_1.reaction_912503_rate
*/
void Biosystem_eqFunction_91(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,91};
  data->localData[0]->realVars[39] /* der(c_1.BRCA1) STATE_DER */ = data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */;
  TRACE_POP
}
/*
equation index: 92
type: SIMPLE_ASSIGN
der(c_1._DMC1) = c_1.reaction_912503_rate + c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_92(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,92};
  data->localData[0]->realVars[44] /* der(c_1.DMC1) STATE_DER */ = data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */ + data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 93
type: SIMPLE_ASSIGN
der(c_1._RAD51) = c_1.reaction_912503_rate + c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_93(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,93};
  data->localData[0]->realVars[60] /* der(c_1.RAD51) STATE_DER */ = data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */ + data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 94
type: SIMPLE_ASSIGN
der(c_1._ATM) = c_1.reaction_912503_rate
*/
void Biosystem_eqFunction_94(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,94};
  data->localData[0]->realVars[35] /* der(c_1.ATM) STATE_DER */ = data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */;
  TRACE_POP
}
/*
equation index: 95
type: SIMPLE_ASSIGN
c_1._reaction_912458_rate = c_1.rate_constants[3] * c_1.DNA * c_1.Meiotic_single_stranded_DNA_complex
*/
void Biosystem_eqFunction_95(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,95};
  data->localData[0]->realVars[76] /* c_1.reaction_912458_rate variable */ = (data->simulationInfo->realParameter[37]) * ((data->localData[0]->realVars[10] /* c_1.DNA STATE(1) */) * (data->localData[0]->realVars[20] /* c_1.Meiotic_single_stranded_DNA_complex STATE(1) */));
  TRACE_POP
}
/*
equation index: 96
type: SIMPLE_ASSIGN
der(c_1._Meiotic_single_stranded_DNA_complex) = c_1.reaction_912458_rate + c_1.reaction_912503_rate
*/
void Biosystem_eqFunction_96(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,96};
  data->localData[0]->realVars[55] /* der(c_1.Meiotic_single_stranded_DNA_complex) STATE_DER */ = data->localData[0]->realVars[76] /* c_1.reaction_912458_rate variable */ + data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */;
  TRACE_POP
}
/*
equation index: 97
type: SIMPLE_ASSIGN
der(c_1._Meiotic_D_loop_complex) = c_1.reaction_912458_rate - c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_97(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,97};
  data->localData[0]->realVars[53] /* der(c_1.Meiotic_D_loop_complex) STATE_DER */ = data->localData[0]->realVars[76] /* c_1.reaction_912458_rate variable */ - data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 98
type: SIMPLE_ASSIGN
c_1._reaction_912363_rate = c_1.rate_constants[2] * c_1.PRDM9 * c_1.DNA
*/
void Biosystem_eqFunction_98(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,98};
  data->localData[0]->realVars[73] /* c_1.reaction_912363_rate variable */ = (data->simulationInfo->realParameter[36]) * ((data->localData[0]->realVars[23] /* c_1.PRDM9 STATE(1,c_1.reaction_912363_rate) */) * (data->localData[0]->realVars[10] /* c_1.DNA STATE(1) */));
  TRACE_POP
}
/*
equation index: 99
type: SIMPLE_ASSIGN
der(c_1._DNA) = c_1.reaction_912363_rate + (-c_1.reaction_912458_rate) - c_1.reaction_912368_rate
*/
void Biosystem_eqFunction_99(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,99};
  data->localData[0]->realVars[45] /* der(c_1.DNA) STATE_DER */ = data->localData[0]->realVars[73] /* c_1.reaction_912363_rate variable */ + (-data->localData[0]->realVars[76] /* c_1.reaction_912458_rate variable */) - data->localData[0]->realVars[74] /* c_1.reaction_912368_rate variable */;
  TRACE_POP
}
/*
equation index: 100
type: SIMPLE_ASSIGN
der(c_1._PRDM9) = c_1.reaction_912363_rate
*/
void Biosystem_eqFunction_100(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,100};
  data->localData[0]->realVars[58] /* der(c_1.PRDM9) STATE_DER */ = data->localData[0]->realVars[73] /* c_1.reaction_912363_rate variable */;
  TRACE_POP
}
/*
equation index: 101
type: SIMPLE_ASSIGN
der(c_1._PRDM9_DNA) = c_1.reaction_912363_rate
*/
void Biosystem_eqFunction_101(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,101};
  data->localData[0]->realVars[59] /* der(c_1.PRDM9_DNA) STATE_DER */ = data->localData[0]->realVars[73] /* c_1.reaction_912363_rate variable */;
  TRACE_POP
}
/*
equation index: 102
type: SIMPLE_ASSIGN
c_1._reaction_912429_rate = c_1.rate_constants[1] * c_1.CDK2 * c_1.Meiotic_Holliday_Junction * c_1.MLH3 * c_1.MLH1
*/
void Biosystem_eqFunction_102(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,102};
  data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ = (data->simulationInfo->realParameter[35]) * ((data->localData[0]->realVars[6] /* c_1.CDK2 STATE(1,c_1.reaction_912429_rate) */) * ((data->localData[0]->realVars[19] /* c_1.Meiotic_Holliday_Junction STATE(1) */) * ((data->localData[0]->realVars[14] /* c_1.MLH3 STATE(1,c_1.reaction_912429_rate) */) * (data->localData[0]->realVars[13] /* c_1.MLH1 STATE(1,c_1.reaction_912429_rate) */))));
  TRACE_POP
}
/*
equation index: 103
type: SIMPLE_ASSIGN
der(c_1._Meiotic_Holliday_Junction) = c_1.reaction_912429_rate + c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_103(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,103};
  data->localData[0]->realVars[54] /* der(c_1.Meiotic_Holliday_Junction) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ + data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 104
type: SIMPLE_ASSIGN
der(c_1._MLH1) = c_1.reaction_912429_rate
*/
void Biosystem_eqFunction_104(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,104};
  data->localData[0]->realVars[48] /* der(c_1.MLH1) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */;
  TRACE_POP
}
/*
equation index: 105
type: SIMPLE_ASSIGN
der(c_1._MLH3) = c_1.reaction_912429_rate
*/
void Biosystem_eqFunction_105(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,105};
  data->localData[0]->realVars[49] /* der(c_1.MLH3) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */;
  TRACE_POP
}
/*
equation index: 106
type: SIMPLE_ASSIGN
der(c_1._CDK2) = c_1.reaction_912429_rate
*/
void Biosystem_eqFunction_106(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,106};
  data->localData[0]->realVars[41] /* der(c_1.CDK2) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */;
  TRACE_POP
}
/*
equation index: 107
type: SIMPLE_ASSIGN
der(c_1._CDK4) = c_1.reaction_912429_rate - c_1.reaction_912503_rate
*/
void Biosystem_eqFunction_107(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,107};
  data->localData[0]->realVars[42] /* der(c_1.CDK4) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ - data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */;
  TRACE_POP
}
/*
equation index: 108
type: SIMPLE_ASSIGN
der(c_1._BLM) = c_1.reaction_912429_rate - c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_108(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,108};
  data->localData[0]->realVars[38] /* der(c_1.BLM) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ - data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 109
type: SIMPLE_ASSIGN
der(c_1._BRCA2) = c_1.reaction_912429_rate - c_1.reaction_912503_rate
*/
void Biosystem_eqFunction_109(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,109};
  data->localData[0]->realVars[40] /* der(c_1.BRCA2) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ - data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */;
  TRACE_POP
}
/*
equation index: 110
type: SIMPLE_ASSIGN
der(c_1._TOP3A) = c_1.reaction_912429_rate - c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_110(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,110};
  data->localData[0]->realVars[68] /* der(c_1.TOP3A) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ - data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 111
type: SIMPLE_ASSIGN
der(c_1._MSH4) = c_1.reaction_912429_rate - c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_111(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,111};
  data->localData[0]->realVars[51] /* der(c_1.MSH4) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ - data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 112
type: SIMPLE_ASSIGN
der(c_1._Cleaved_Meiotic_Holliday_Junction) = c_1.reaction_912429_rate
*/
void Biosystem_eqFunction_112(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,112};
  data->localData[0]->realVars[43] /* der(c_1.Cleaved_Meiotic_Holliday_Junction) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */;
  TRACE_POP
}
/*
equation index: 113
type: SIMPLE_ASSIGN
der(c_1._MSH5) = c_1.reaction_912429_rate - c_1.reaction_912496_rate
*/
void Biosystem_eqFunction_113(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,113};
  data->localData[0]->realVars[52] /* der(c_1.MSH5) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ - data->localData[0]->realVars[77] /* c_1.reaction_912496_rate variable */;
  TRACE_POP
}
/*
equation index: 114
type: SIMPLE_ASSIGN
der(c_1._RPA_heterotrimer) = c_1.reaction_912429_rate - c_1.reaction_912503_rate
*/
void Biosystem_eqFunction_114(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,114};
  data->localData[0]->realVars[62] /* der(c_1.RPA_heterotrimer) STATE_DER */ = data->localData[0]->realVars[75] /* c_1.reaction_912429_rate variable */ - data->localData[0]->realVars[78] /* c_1.reaction_912503_rate variable */;
  TRACE_POP
}
/*
equation index: 115
type: SIMPLE_ASSIGN
c_1._reaction_1214188_rate = c_1.rate_constants[8] * c_1.Nucleosome_with_H3K4me2 * c_1.AdoMet ^ 2.0
*/
void Biosystem_eqFunction_115(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,115};
  modelica_real tmp0;
  tmp0 = data->localData[0]->realVars[2] /* c_1.AdoMet STATE(1) */;
  data->localData[0]->realVars[70] /* c_1.reaction_1214188_rate variable */ = (data->simulationInfo->realParameter[42]) * ((data->localData[0]->realVars[21] /* c_1.Nucleosome_with_H3K4me2 STATE(1,c_1.reaction_1214188_rate) */) * ((tmp0 * tmp0)));
  TRACE_POP
}
/*
equation index: 116
type: SIMPLE_ASSIGN
der(c_1._Nucleosome_with_H3K4me2) = c_1.reaction_1214188_rate
*/
void Biosystem_eqFunction_116(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,116};
  data->localData[0]->realVars[56] /* der(c_1.Nucleosome_with_H3K4me2) STATE_DER */ = data->localData[0]->realVars[70] /* c_1.reaction_1214188_rate variable */;
  TRACE_POP
}
/*
equation index: 117
type: SIMPLE_ASSIGN
der(c_1._AdoMet) = 2.0 * c_1.reaction_1214188_rate
*/
void Biosystem_eqFunction_117(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,117};
  data->localData[0]->realVars[37] /* der(c_1.AdoMet) STATE_DER */ = (2.0) * (data->localData[0]->realVars[70] /* c_1.reaction_1214188_rate variable */);
  TRACE_POP
}
/*
equation index: 118
type: SIMPLE_ASSIGN
der(c_1._Nucleosome_with_H3K4me3) = c_1.reaction_1214188_rate
*/
void Biosystem_eqFunction_118(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,118};
  data->localData[0]->realVars[57] /* der(c_1.Nucleosome_with_H3K4me3) STATE_DER */ = data->localData[0]->realVars[70] /* c_1.reaction_1214188_rate variable */;
  TRACE_POP
}
/*
equation index: 119
type: SIMPLE_ASSIGN
der(c_1._AdoHcy) = 2.0 * c_1.reaction_1214188_rate
*/
void Biosystem_eqFunction_119(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,119};
  data->localData[0]->realVars[36] /* der(c_1.AdoHcy) STATE_DER */ = (2.0) * (data->localData[0]->realVars[70] /* c_1.reaction_1214188_rate variable */);
  TRACE_POP
}
/*
equation index: 120
type: SIMPLE_ASSIGN
der(c_1._MRN_CtIP) = 0.0
*/
void Biosystem_eqFunction_120(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,120};
  data->localData[0]->realVars[50] /* der(c_1.MRN_CtIP) STATE_DER */ = 0.0;
  TRACE_POP
}
/*
equation index: 121
type: SIMPLE_ASSIGN
der(c_1._RAD51C) = 0.0
*/
void Biosystem_eqFunction_121(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,121};
  data->localData[0]->realVars[61] /* der(c_1.RAD51C) STATE_DER */ = 0.0;
  TRACE_POP
}
/*
equation index: 122
type: SIMPLE_ASSIGN
der(c_1._TEX15) = 0.0
*/
void Biosystem_eqFunction_122(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,122};
  data->localData[0]->realVars[67] /* der(c_1.TEX15) STATE_DER */ = 0.0;
  TRACE_POP
}
/*
equation index: 123
type: SIMPLE_ASSIGN
der(c_1._HOP2_TBPIP_MND1) = 0.0
*/
void Biosystem_eqFunction_123(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,123};
  data->localData[0]->realVars[47] /* der(c_1.HOP2_TBPIP_MND1) STATE_DER */ = 0.0;
  TRACE_POP
}

OMC_DISABLE_OPT
int Biosystem_functionDAE(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  int equationIndexes[1] = {0};
  
  data->simulationInfo->needToIterate = 0;
  data->simulationInfo->discreteCall = 1;
  Biosystem_functionLocalKnownVars(data, threadData);
  Biosystem_eqFunction_80(data, threadData);

  Biosystem_eqFunction_81(data, threadData);

  Biosystem_eqFunction_82(data, threadData);

  Biosystem_eqFunction_83(data, threadData);

  Biosystem_eqFunction_84(data, threadData);

  Biosystem_eqFunction_85(data, threadData);

  Biosystem_eqFunction_86(data, threadData);

  Biosystem_eqFunction_87(data, threadData);

  Biosystem_eqFunction_88(data, threadData);

  Biosystem_eqFunction_89(data, threadData);

  Biosystem_eqFunction_90(data, threadData);

  Biosystem_eqFunction_91(data, threadData);

  Biosystem_eqFunction_92(data, threadData);

  Biosystem_eqFunction_93(data, threadData);

  Biosystem_eqFunction_94(data, threadData);

  Biosystem_eqFunction_95(data, threadData);

  Biosystem_eqFunction_96(data, threadData);

  Biosystem_eqFunction_97(data, threadData);

  Biosystem_eqFunction_98(data, threadData);

  Biosystem_eqFunction_99(data, threadData);

  Biosystem_eqFunction_100(data, threadData);

  Biosystem_eqFunction_101(data, threadData);

  Biosystem_eqFunction_102(data, threadData);

  Biosystem_eqFunction_103(data, threadData);

  Biosystem_eqFunction_104(data, threadData);

  Biosystem_eqFunction_105(data, threadData);

  Biosystem_eqFunction_106(data, threadData);

  Biosystem_eqFunction_107(data, threadData);

  Biosystem_eqFunction_108(data, threadData);

  Biosystem_eqFunction_109(data, threadData);

  Biosystem_eqFunction_110(data, threadData);

  Biosystem_eqFunction_111(data, threadData);

  Biosystem_eqFunction_112(data, threadData);

  Biosystem_eqFunction_113(data, threadData);

  Biosystem_eqFunction_114(data, threadData);

  Biosystem_eqFunction_115(data, threadData);

  Biosystem_eqFunction_116(data, threadData);

  Biosystem_eqFunction_117(data, threadData);

  Biosystem_eqFunction_118(data, threadData);

  Biosystem_eqFunction_119(data, threadData);

  Biosystem_eqFunction_120(data, threadData);

  Biosystem_eqFunction_121(data, threadData);

  Biosystem_eqFunction_122(data, threadData);

  Biosystem_eqFunction_123(data, threadData);
  data->simulationInfo->discreteCall = 0;
  
  TRACE_POP
  return 0;
}


int Biosystem_functionLocalKnownVars(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  
  TRACE_POP
  return 0;
}


/* forwarded equations */
extern void Biosystem_eqFunction_123(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_122(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_121(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_120(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_115(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_116(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_117(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_118(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_119(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_80(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_81(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_82(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_83(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_84(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_85(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_86(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_87(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_88(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_89(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_90(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_91(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_92(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_93(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_94(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_95(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_96(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_97(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_98(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_99(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_100(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_101(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_102(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_103(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_104(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_105(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_106(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_107(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_108(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_109(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_110(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_111(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_112(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_113(DATA* data, threadData_t *threadData);
extern void Biosystem_eqFunction_114(DATA* data, threadData_t *threadData);

static void functionODE_system0(DATA *data, threadData_t *threadData)
{
    Biosystem_eqFunction_123(data, threadData);

    Biosystem_eqFunction_122(data, threadData);

    Biosystem_eqFunction_121(data, threadData);

    Biosystem_eqFunction_120(data, threadData);

    Biosystem_eqFunction_115(data, threadData);

    Biosystem_eqFunction_116(data, threadData);

    Biosystem_eqFunction_117(data, threadData);

    Biosystem_eqFunction_118(data, threadData);

    Biosystem_eqFunction_119(data, threadData);

    Biosystem_eqFunction_80(data, threadData);

    Biosystem_eqFunction_81(data, threadData);

    Biosystem_eqFunction_82(data, threadData);

    Biosystem_eqFunction_83(data, threadData);

    Biosystem_eqFunction_84(data, threadData);

    Biosystem_eqFunction_85(data, threadData);

    Biosystem_eqFunction_86(data, threadData);

    Biosystem_eqFunction_87(data, threadData);

    Biosystem_eqFunction_88(data, threadData);

    Biosystem_eqFunction_89(data, threadData);

    Biosystem_eqFunction_90(data, threadData);

    Biosystem_eqFunction_91(data, threadData);

    Biosystem_eqFunction_92(data, threadData);

    Biosystem_eqFunction_93(data, threadData);

    Biosystem_eqFunction_94(data, threadData);

    Biosystem_eqFunction_95(data, threadData);

    Biosystem_eqFunction_96(data, threadData);

    Biosystem_eqFunction_97(data, threadData);

    Biosystem_eqFunction_98(data, threadData);

    Biosystem_eqFunction_99(data, threadData);

    Biosystem_eqFunction_100(data, threadData);

    Biosystem_eqFunction_101(data, threadData);

    Biosystem_eqFunction_102(data, threadData);

    Biosystem_eqFunction_103(data, threadData);

    Biosystem_eqFunction_104(data, threadData);

    Biosystem_eqFunction_105(data, threadData);

    Biosystem_eqFunction_106(data, threadData);

    Biosystem_eqFunction_107(data, threadData);

    Biosystem_eqFunction_108(data, threadData);

    Biosystem_eqFunction_109(data, threadData);

    Biosystem_eqFunction_110(data, threadData);

    Biosystem_eqFunction_111(data, threadData);

    Biosystem_eqFunction_112(data, threadData);

    Biosystem_eqFunction_113(data, threadData);

    Biosystem_eqFunction_114(data, threadData);
}

int Biosystem_functionODE(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH

  
  data->simulationInfo->callStatistics.functionODE++;
  
  Biosystem_functionLocalKnownVars(data, threadData);
  functionODE_system0(data, threadData);

  
  TRACE_POP
  return 0;
}

/* forward the main in the simulation runtime */
extern int _main_SimulationRuntime(int argc, char**argv, DATA *data, threadData_t *threadData);

#include "Biosystem_12jac.h"
#include "Biosystem_13opt.h"

struct OpenModelicaGeneratedFunctionCallbacks Biosystem_callback = {
   (int (*)(DATA *, threadData_t *, void *)) Biosystem_performSimulation,
   (int (*)(DATA *, threadData_t *, void *)) Biosystem_performQSSSimulation,
   Biosystem_updateContinuousSystem,
   Biosystem_callExternalObjectDestructors,
   NULL,
   NULL,
   NULL,
   #if !defined(OMC_NO_STATESELECTION)
   Biosystem_initializeStateSets,
   #else
   NULL,
   #endif
   Biosystem_initializeDAEmodeData,
   Biosystem_functionODE,
   Biosystem_functionAlgebraics,
   Biosystem_functionDAE,
   Biosystem_functionLocalKnownVars,
   Biosystem_input_function,
   Biosystem_input_function_init,
   Biosystem_input_function_updateStartValues,
   Biosystem_output_function,
   Biosystem_function_storeDelayed,
   Biosystem_updateBoundVariableAttributes,
   Biosystem_functionInitialEquations,
   1, /* useHomotopy - 0: local homotopy (equidistant lambda), 1: global homotopy (equidistant lambda), 2: new global homotopy approach (adaptive lambda), 3: new local homotopy approach (adaptive lambda)*/
   Biosystem_functionInitialEquations_lambda0,
   Biosystem_functionRemovedInitialEquations,
   Biosystem_updateBoundParameters,
   Biosystem_checkForAsserts,
   Biosystem_function_ZeroCrossingsEquations,
   Biosystem_function_ZeroCrossings,
   Biosystem_function_updateRelations,
   Biosystem_zeroCrossingDescription,
   Biosystem_relationDescription,
   Biosystem_function_initSample,
   Biosystem_INDEX_JAC_A,
   Biosystem_INDEX_JAC_B,
   Biosystem_INDEX_JAC_C,
   Biosystem_INDEX_JAC_D,
   Biosystem_initialAnalyticJacobianA,
   Biosystem_initialAnalyticJacobianB,
   Biosystem_initialAnalyticJacobianC,
   Biosystem_initialAnalyticJacobianD,
   Biosystem_functionJacA_column,
   Biosystem_functionJacB_column,
   Biosystem_functionJacC_column,
   Biosystem_functionJacD_column,
   Biosystem_linear_model_frame,
   Biosystem_linear_model_datarecovery_frame,
   Biosystem_mayer,
   Biosystem_lagrange,
   Biosystem_pickUpBoundsForInputsInOptimization,
   Biosystem_setInputData,
   Biosystem_getTimeGrid,
   Biosystem_symbolicInlineSystem,
   Biosystem_function_initSynchronous,
   Biosystem_function_updateSynchronous,
   Biosystem_function_equationsSynchronous,
   Biosystem_inputNames,
   NULL,
   NULL,
   NULL,
   -1

};

#define _OMC_LIT_RESOURCE_0_name_data "Biosystem"
#define _OMC_LIT_RESOURCE_0_dir_data "/home/scacio/Dropbox/Personale/Workspace/IdeaProjects/BiologyThesis/res/test-case-2/out"
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_0_name,9,_OMC_LIT_RESOURCE_0_name_data);
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_0_dir,87,_OMC_LIT_RESOURCE_0_dir_data);

#define _OMC_LIT_RESOURCE_1_name_data "Complex"
#define _OMC_LIT_RESOURCE_1_dir_data "/usr/lib/omlibrary"
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_1_name,7,_OMC_LIT_RESOURCE_1_name_data);
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_1_dir,18,_OMC_LIT_RESOURCE_1_dir_data);

#define _OMC_LIT_RESOURCE_2_name_data "Environment"
#define _OMC_LIT_RESOURCE_2_dir_data "/home/scacio/Dropbox/Personale/Workspace/IdeaProjects/BiologyThesis/res/test-case-2/out"
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_2_name,11,_OMC_LIT_RESOURCE_2_name_data);
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_2_dir,87,_OMC_LIT_RESOURCE_2_dir_data);

#define _OMC_LIT_RESOURCE_3_name_data "Modelica"
#define _OMC_LIT_RESOURCE_3_dir_data "/usr/lib/omlibrary/Modelica 3.2.2"
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_3_name,8,_OMC_LIT_RESOURCE_3_name_data);
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_3_dir,33,_OMC_LIT_RESOURCE_3_dir_data);

#define _OMC_LIT_RESOURCE_4_name_data "ModelicaServices"
#define _OMC_LIT_RESOURCE_4_dir_data "/usr/lib/omlibrary/ModelicaServices 3.2.2"
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_4_name,16,_OMC_LIT_RESOURCE_4_name_data);
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_4_dir,41,_OMC_LIT_RESOURCE_4_dir_data);

#define _OMC_LIT_RESOURCE_5_name_data "Nucleoplasm"
#define _OMC_LIT_RESOURCE_5_dir_data "/home/scacio/Dropbox/Personale/Workspace/IdeaProjects/BiologyThesis/res/test-case-2/out"
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_5_name,11,_OMC_LIT_RESOURCE_5_name_data);
static const MMC_DEFSTRINGLIT(_OMC_LIT_RESOURCE_5_dir,87,_OMC_LIT_RESOURCE_5_dir_data);

static const MMC_DEFSTRUCTLIT(_OMC_LIT_RESOURCES,12,MMC_ARRAY_TAG) {MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_0_name), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_0_dir), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_1_name), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_1_dir), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_2_name), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_2_dir), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_3_name), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_3_dir), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_4_name), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_4_dir), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_5_name), MMC_REFSTRINGLIT(_OMC_LIT_RESOURCE_5_dir)}};
void Biosystem_setupDataStruc(DATA *data, threadData_t *threadData)
{
  assertStreamPrint(threadData,0!=data, "Error while initialize Data");
  threadData->localRoots[LOCAL_ROOT_SIMULATION_DATA] = data;
  data->callback = &Biosystem_callback;
  OpenModelica_updateUriMapping(threadData, MMC_REFSTRUCTLIT(_OMC_LIT_RESOURCES));
  data->modelData->modelName = "Biosystem";
  data->modelData->modelFilePrefix = "Biosystem";
  data->modelData->resultFileName = NULL;
  data->modelData->modelDir = "/home/scacio/Dropbox/Personale/Workspace/IdeaProjects/BiologyThesis/res/test-case-2/out";
  data->modelData->modelGUID = "{0f82b343-06e3-40a3-9690-20fc11667fcc}";
  #if defined(OPENMODELICA_XML_FROM_FILE_AT_RUNTIME)
  data->modelData->initXMLData = NULL;
  data->modelData->modelDataXml.infoXMLData = NULL;
  #else
  #if defined(_MSC_VER) /* handle joke compilers */
  {
  /* for MSVC we encode a string like char x[] = {'a', 'b', 'c', '\0'} */
  /* because the string constant limit is 65535 bytes */
  static const char contents_init[] =
    #include "Biosystem_init.c"
    ;
  static const char contents_info[] =
    #include "Biosystem_info.c"
    ;
    data->modelData->initXMLData = contents_init;
    data->modelData->modelDataXml.infoXMLData = contents_info;
  }
  #else /* handle real compilers */
  data->modelData->initXMLData =
  #include "Biosystem_init.c"
    ;
  data->modelData->modelDataXml.infoXMLData =
  #include "Biosystem_info.c"
    ;
  #endif /* defined(_MSC_VER) */
  #endif /* defined(OPENMODELICA_XML_FROM_FILE_AT_RUNTIME) */
  
  data->modelData->nStates = 35;
  data->modelData->nVariablesReal = 79;
  data->modelData->nDiscreteReal = 0;
  data->modelData->nVariablesInteger = 0;
  data->modelData->nVariablesBoolean = 0;
  data->modelData->nVariablesString = 0;
  data->modelData->nParametersReal = 88;
  data->modelData->nParametersInteger = 0;
  data->modelData->nParametersBoolean = 0;
  data->modelData->nParametersString = 0;
  data->modelData->nInputVars = 0;
  data->modelData->nOutputVars = 0;
  
  data->modelData->nAliasReal = 16;
  data->modelData->nAliasInteger = 0;
  data->modelData->nAliasBoolean = 0;
  data->modelData->nAliasString = 0;
  
  data->modelData->nZeroCrossings = 0;
  data->modelData->nSamples = 0;
  data->modelData->nRelations = 0;
  data->modelData->nMathEvents = 0;
  data->modelData->nExtObjs = 0;
  data->modelData->modelDataXml.fileName = "Biosystem_info.json";
  data->modelData->modelDataXml.modelInfoXmlLength = 0;
  data->modelData->modelDataXml.nFunctions = 0;
  data->modelData->modelDataXml.nProfileBlocks = 0;
  data->modelData->modelDataXml.nEquations = 168;
  data->modelData->nMixedSystems = 0;
  data->modelData->nLinearSystems = 0;
  data->modelData->nNonLinearSystems = 0;
  data->modelData->nStateSets = 0;
  data->modelData->nJacobians = 4;
  data->modelData->nOptimizeConstraints = 0;
  data->modelData->nOptimizeFinalConstraints = 0;
  
  data->modelData->nDelayExpressions = 0;
  
  data->modelData->nClocks = 0;
  data->modelData->nSubClocks = 0;
  
  data->modelData->nSensitivityVars = 0;
  data->modelData->nSensitivityParamVars = 0;
}

static int rml_execution_failed()
{
  fflush(NULL);
  fprintf(stderr, "Execution failed!\n");
  fflush(NULL);
  return 1;
}

#if defined(threadData)
#undef threadData
#endif
/* call the simulation runtime main from our main! */
int main(int argc, char**argv)
{
  int res;
  DATA data;
  MODEL_DATA modelData;
  SIMULATION_INFO simInfo;
  data.modelData = &modelData;
  data.simulationInfo = &simInfo;
  measure_time_flag = 0;
  compiledInDAEMode = 0;
  compiledWithSymSolver = 0;
  MMC_INIT(0);
  omc_alloc_interface.init();
  {
    MMC_TRY_TOP()
  
    MMC_TRY_STACK()
  
    Biosystem_setupDataStruc(&data, threadData);
    res = _main_SimulationRuntime(argc, argv, &data, threadData);
    
    MMC_ELSE()
    rml_execution_failed();
    fprintf(stderr, "Stack overflow detected and was not caught.\nSend us a bug report at https://trac.openmodelica.org/OpenModelica/newticket\n    Include the following trace:\n");
    printStacktraceMessages();
    fflush(NULL);
    return 1;
    MMC_CATCH_STACK()
    
    MMC_CATCH_TOP(return rml_execution_failed());
  }

  fflush(NULL);
  EXIT(res);
  return res;
}

#ifdef __cplusplus
}
#endif


