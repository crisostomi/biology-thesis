/* update bound parameters and variable attributes (start, nominal, min, max) */
#include "Biosystem_model.h"
#if defined(__cplusplus)
extern "C" {
#endif

OMC_DISABLE_OPT
int Biosystem_updateBoundVariableAttributes(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  /* min ******************************************************** */
  
  infoStreamPrint(LOG_INIT, 1, "updating min-values");
  if (ACTIVE_STREAM(LOG_INIT)) messageClose(LOG_INIT);
  
  /* max ******************************************************** */
  
  infoStreamPrint(LOG_INIT, 1, "updating max-values");
  if (ACTIVE_STREAM(LOG_INIT)) messageClose(LOG_INIT);
  
  /* nominal **************************************************** */
  
  infoStreamPrint(LOG_INIT, 1, "updating nominal-values");
  if (ACTIVE_STREAM(LOG_INIT)) messageClose(LOG_INIT);
  
  /* start ****************************************************** */
  infoStreamPrint(LOG_INIT, 1, "updating primary start-values");
  if (ACTIVE_STREAM(LOG_INIT)) messageClose(LOG_INIT);
  
  TRACE_POP
  return 0;
}

void Biosystem_updateBoundParameters_0(DATA *data, threadData_t *threadData);

/*
equation index: 124
type: SIMPLE_ASSIGN
c_1._rate_constants[9] = rates_c_1[9]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_124(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,124};
  data->simulationInfo->realParameter[43] = data->simulationInfo->realParameter[87];
  TRACE_POP
}

/*
equation index: 125
type: SIMPLE_ASSIGN
c_1._rate_constants[8] = rates_c_1[8]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_125(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,125};
  data->simulationInfo->realParameter[42] = data->simulationInfo->realParameter[86];
  TRACE_POP
}

/*
equation index: 126
type: SIMPLE_ASSIGN
c_1._rate_constants[7] = rates_c_1[7]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_126(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,126};
  data->simulationInfo->realParameter[41] = data->simulationInfo->realParameter[85];
  TRACE_POP
}

/*
equation index: 127
type: SIMPLE_ASSIGN
c_1._rate_constants[6] = rates_c_1[6]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_127(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,127};
  data->simulationInfo->realParameter[40] = data->simulationInfo->realParameter[84];
  TRACE_POP
}

/*
equation index: 128
type: SIMPLE_ASSIGN
c_1._rate_constants[5] = rates_c_1[5]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_128(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,128};
  data->simulationInfo->realParameter[39] = data->simulationInfo->realParameter[83];
  TRACE_POP
}

/*
equation index: 129
type: SIMPLE_ASSIGN
c_1._rate_constants[4] = rates_c_1[4]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_129(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,129};
  data->simulationInfo->realParameter[38] = data->simulationInfo->realParameter[82];
  TRACE_POP
}

/*
equation index: 130
type: SIMPLE_ASSIGN
c_1._rate_constants[3] = rates_c_1[3]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_130(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,130};
  data->simulationInfo->realParameter[37] = data->simulationInfo->realParameter[81];
  TRACE_POP
}

/*
equation index: 131
type: SIMPLE_ASSIGN
c_1._rate_constants[2] = rates_c_1[2]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_131(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,131};
  data->simulationInfo->realParameter[36] = data->simulationInfo->realParameter[80];
  TRACE_POP
}

/*
equation index: 132
type: SIMPLE_ASSIGN
c_1._rate_constants[1] = rates_c_1[1]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_132(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,132};
  data->simulationInfo->realParameter[35] = data->simulationInfo->realParameter[79];
  TRACE_POP
}

/*
equation index: 133
type: SIMPLE_ASSIGN
c_1._initial_state[35] = init_c_1[35]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_133(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,133};
  data->simulationInfo->realParameter[34] = data->simulationInfo->realParameter[78];
  TRACE_POP
}

/*
equation index: 134
type: SIMPLE_ASSIGN
c_1._initial_state[34] = init_c_1[34]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_134(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,134};
  data->simulationInfo->realParameter[33] = data->simulationInfo->realParameter[77];
  TRACE_POP
}

/*
equation index: 135
type: SIMPLE_ASSIGN
c_1._initial_state[33] = init_c_1[33]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_135(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,135};
  data->simulationInfo->realParameter[32] = data->simulationInfo->realParameter[76];
  TRACE_POP
}

/*
equation index: 136
type: SIMPLE_ASSIGN
c_1._initial_state[32] = init_c_1[32]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_136(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,136};
  data->simulationInfo->realParameter[31] = data->simulationInfo->realParameter[75];
  TRACE_POP
}

/*
equation index: 137
type: SIMPLE_ASSIGN
c_1._initial_state[31] = init_c_1[31]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_137(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,137};
  data->simulationInfo->realParameter[30] = data->simulationInfo->realParameter[74];
  TRACE_POP
}

/*
equation index: 138
type: SIMPLE_ASSIGN
c_1._initial_state[30] = init_c_1[30]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_138(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,138};
  data->simulationInfo->realParameter[29] = data->simulationInfo->realParameter[73];
  TRACE_POP
}

/*
equation index: 139
type: SIMPLE_ASSIGN
c_1._initial_state[29] = init_c_1[29]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_139(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,139};
  data->simulationInfo->realParameter[28] = data->simulationInfo->realParameter[72];
  TRACE_POP
}

/*
equation index: 140
type: SIMPLE_ASSIGN
c_1._initial_state[28] = init_c_1[28]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_140(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,140};
  data->simulationInfo->realParameter[27] = data->simulationInfo->realParameter[71];
  TRACE_POP
}

/*
equation index: 141
type: SIMPLE_ASSIGN
c_1._initial_state[27] = init_c_1[27]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_141(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,141};
  data->simulationInfo->realParameter[26] = data->simulationInfo->realParameter[70];
  TRACE_POP
}

/*
equation index: 142
type: SIMPLE_ASSIGN
c_1._initial_state[26] = init_c_1[26]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_142(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,142};
  data->simulationInfo->realParameter[25] = data->simulationInfo->realParameter[69];
  TRACE_POP
}

/*
equation index: 143
type: SIMPLE_ASSIGN
c_1._initial_state[25] = init_c_1[25]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_143(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,143};
  data->simulationInfo->realParameter[24] = data->simulationInfo->realParameter[68];
  TRACE_POP
}

/*
equation index: 144
type: SIMPLE_ASSIGN
c_1._initial_state[24] = init_c_1[24]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_144(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,144};
  data->simulationInfo->realParameter[23] = data->simulationInfo->realParameter[67];
  TRACE_POP
}

/*
equation index: 145
type: SIMPLE_ASSIGN
c_1._initial_state[23] = init_c_1[23]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_145(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,145};
  data->simulationInfo->realParameter[22] = data->simulationInfo->realParameter[66];
  TRACE_POP
}

/*
equation index: 146
type: SIMPLE_ASSIGN
c_1._initial_state[22] = init_c_1[22]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_146(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,146};
  data->simulationInfo->realParameter[21] = data->simulationInfo->realParameter[65];
  TRACE_POP
}

/*
equation index: 147
type: SIMPLE_ASSIGN
c_1._initial_state[21] = init_c_1[21]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_147(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,147};
  data->simulationInfo->realParameter[20] = data->simulationInfo->realParameter[64];
  TRACE_POP
}

/*
equation index: 148
type: SIMPLE_ASSIGN
c_1._initial_state[20] = init_c_1[20]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_148(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,148};
  data->simulationInfo->realParameter[19] = data->simulationInfo->realParameter[63];
  TRACE_POP
}

/*
equation index: 149
type: SIMPLE_ASSIGN
c_1._initial_state[19] = init_c_1[19]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_149(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,149};
  data->simulationInfo->realParameter[18] = data->simulationInfo->realParameter[62];
  TRACE_POP
}

/*
equation index: 150
type: SIMPLE_ASSIGN
c_1._initial_state[18] = init_c_1[18]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_150(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,150};
  data->simulationInfo->realParameter[17] = data->simulationInfo->realParameter[61];
  TRACE_POP
}

/*
equation index: 151
type: SIMPLE_ASSIGN
c_1._initial_state[17] = init_c_1[17]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_151(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,151};
  data->simulationInfo->realParameter[16] = data->simulationInfo->realParameter[60];
  TRACE_POP
}

/*
equation index: 152
type: SIMPLE_ASSIGN
c_1._initial_state[16] = init_c_1[16]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_152(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,152};
  data->simulationInfo->realParameter[15] = data->simulationInfo->realParameter[59];
  TRACE_POP
}

/*
equation index: 153
type: SIMPLE_ASSIGN
c_1._initial_state[15] = init_c_1[15]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_153(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,153};
  data->simulationInfo->realParameter[14] = data->simulationInfo->realParameter[58];
  TRACE_POP
}

/*
equation index: 154
type: SIMPLE_ASSIGN
c_1._initial_state[14] = init_c_1[14]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_154(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,154};
  data->simulationInfo->realParameter[13] = data->simulationInfo->realParameter[57];
  TRACE_POP
}

/*
equation index: 155
type: SIMPLE_ASSIGN
c_1._initial_state[13] = init_c_1[13]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_155(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,155};
  data->simulationInfo->realParameter[12] = data->simulationInfo->realParameter[56];
  TRACE_POP
}

/*
equation index: 156
type: SIMPLE_ASSIGN
c_1._initial_state[12] = init_c_1[12]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_156(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,156};
  data->simulationInfo->realParameter[11] = data->simulationInfo->realParameter[55];
  TRACE_POP
}

/*
equation index: 157
type: SIMPLE_ASSIGN
c_1._initial_state[11] = init_c_1[11]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_157(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,157};
  data->simulationInfo->realParameter[10] = data->simulationInfo->realParameter[54];
  TRACE_POP
}

/*
equation index: 158
type: SIMPLE_ASSIGN
c_1._initial_state[10] = init_c_1[10]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_158(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,158};
  data->simulationInfo->realParameter[9] = data->simulationInfo->realParameter[53];
  TRACE_POP
}

/*
equation index: 159
type: SIMPLE_ASSIGN
c_1._initial_state[9] = init_c_1[9]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_159(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,159};
  data->simulationInfo->realParameter[8] = data->simulationInfo->realParameter[52];
  TRACE_POP
}

/*
equation index: 160
type: SIMPLE_ASSIGN
c_1._initial_state[8] = init_c_1[8]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_160(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,160};
  data->simulationInfo->realParameter[7] = data->simulationInfo->realParameter[51];
  TRACE_POP
}

/*
equation index: 161
type: SIMPLE_ASSIGN
c_1._initial_state[7] = init_c_1[7]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_161(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,161};
  data->simulationInfo->realParameter[6] = data->simulationInfo->realParameter[50];
  TRACE_POP
}

/*
equation index: 162
type: SIMPLE_ASSIGN
c_1._initial_state[6] = init_c_1[6]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_162(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,162};
  data->simulationInfo->realParameter[5] = data->simulationInfo->realParameter[49];
  TRACE_POP
}

/*
equation index: 163
type: SIMPLE_ASSIGN
c_1._initial_state[5] = init_c_1[5]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_163(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,163};
  data->simulationInfo->realParameter[4] = data->simulationInfo->realParameter[48];
  TRACE_POP
}

/*
equation index: 164
type: SIMPLE_ASSIGN
c_1._initial_state[4] = init_c_1[4]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_164(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,164};
  data->simulationInfo->realParameter[3] = data->simulationInfo->realParameter[47];
  TRACE_POP
}

/*
equation index: 165
type: SIMPLE_ASSIGN
c_1._initial_state[3] = init_c_1[3]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_165(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,165};
  data->simulationInfo->realParameter[2] = data->simulationInfo->realParameter[46];
  TRACE_POP
}

/*
equation index: 166
type: SIMPLE_ASSIGN
c_1._initial_state[2] = init_c_1[2]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_166(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,166};
  data->simulationInfo->realParameter[1] = data->simulationInfo->realParameter[45];
  TRACE_POP
}

/*
equation index: 167
type: SIMPLE_ASSIGN
c_1._initial_state[1] = init_c_1[1]
*/
OMC_DISABLE_OPT
static void Biosystem_eqFunction_167(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  const int equationIndexes[2] = {1,167};
  data->simulationInfo->realParameter[0] = data->simulationInfo->realParameter[44];
  TRACE_POP
}
OMC_DISABLE_OPT
void Biosystem_updateBoundParameters_0(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  Biosystem_eqFunction_124(data, threadData);
  Biosystem_eqFunction_125(data, threadData);
  Biosystem_eqFunction_126(data, threadData);
  Biosystem_eqFunction_127(data, threadData);
  Biosystem_eqFunction_128(data, threadData);
  Biosystem_eqFunction_129(data, threadData);
  Biosystem_eqFunction_130(data, threadData);
  Biosystem_eqFunction_131(data, threadData);
  Biosystem_eqFunction_132(data, threadData);
  Biosystem_eqFunction_133(data, threadData);
  Biosystem_eqFunction_134(data, threadData);
  Biosystem_eqFunction_135(data, threadData);
  Biosystem_eqFunction_136(data, threadData);
  Biosystem_eqFunction_137(data, threadData);
  Biosystem_eqFunction_138(data, threadData);
  Biosystem_eqFunction_139(data, threadData);
  Biosystem_eqFunction_140(data, threadData);
  Biosystem_eqFunction_141(data, threadData);
  Biosystem_eqFunction_142(data, threadData);
  Biosystem_eqFunction_143(data, threadData);
  Biosystem_eqFunction_144(data, threadData);
  Biosystem_eqFunction_145(data, threadData);
  Biosystem_eqFunction_146(data, threadData);
  Biosystem_eqFunction_147(data, threadData);
  Biosystem_eqFunction_148(data, threadData);
  Biosystem_eqFunction_149(data, threadData);
  Biosystem_eqFunction_150(data, threadData);
  Biosystem_eqFunction_151(data, threadData);
  Biosystem_eqFunction_152(data, threadData);
  Biosystem_eqFunction_153(data, threadData);
  Biosystem_eqFunction_154(data, threadData);
  Biosystem_eqFunction_155(data, threadData);
  Biosystem_eqFunction_156(data, threadData);
  Biosystem_eqFunction_157(data, threadData);
  Biosystem_eqFunction_158(data, threadData);
  Biosystem_eqFunction_159(data, threadData);
  Biosystem_eqFunction_160(data, threadData);
  Biosystem_eqFunction_161(data, threadData);
  Biosystem_eqFunction_162(data, threadData);
  Biosystem_eqFunction_163(data, threadData);
  Biosystem_eqFunction_164(data, threadData);
  Biosystem_eqFunction_165(data, threadData);
  Biosystem_eqFunction_166(data, threadData);
  Biosystem_eqFunction_167(data, threadData);
  TRACE_POP
}
OMC_DISABLE_OPT
int Biosystem_updateBoundParameters(DATA *data, threadData_t *threadData)
{
  TRACE_PUSH
  Biosystem_updateBoundParameters_0(data, threadData);
  TRACE_POP
  return 0;
}

#if defined(__cplusplus)
}
#endif

