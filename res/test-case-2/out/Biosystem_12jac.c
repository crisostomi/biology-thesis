/* Jacobians 4 */
#include "Biosystem_model.h"
#include "Biosystem_12jac.h"
int Biosystem_initialAnalyticJacobianD(void* inData, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian)
{
  TRACE_PUSH
  TRACE_POP
  return 1;
}
int Biosystem_initialAnalyticJacobianC(void* inData, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian)
{
  TRACE_PUSH
  TRACE_POP
  return 1;
}
int Biosystem_initialAnalyticJacobianB(void* inData, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian)
{
  TRACE_PUSH
  TRACE_POP
  return 1;
}
OMC_DISABLE_OPT
int Biosystem_initialAnalyticJacobianA(void* inData, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian)
{
  TRACE_PUSH
  DATA* data = ((DATA*)inData);
  const int colPtrIndex[1+35] = {0,10,0,4,8,10,10,12,10,0,10,7,10,0,12,12,0,8,8,8,12,3,4,0,3,0,10,0,10,3,2,3,0,0,8,10};
  const int rowIndex[207] = {0,4,5,7,9,11,20,25,27,34,1,2,21,22,3,9,16,17,18,19,25,33,0,4,5,7,9,11,20,25,27,34,0,4,5,7,9,11,20,25,27,34,3,5,6,7,8,13,14,16,17,19,27,33,0,4,5,7,9,11,20,25,27,34,0,4,5,7,9,11,20,25,27,34,10,18,20,23,24,28,29,0,4,5,7,9,11,20,25,27,34,3,5,6,7,8,13,14,16,17,19,27,33,3,5,6,7,8,13,14,16,17,19,27,33,3,9,16,17,18,19,25,33,3,9,16,17,18,19,25,33,3,9,16,17,18,19,25,33,3,5,6,7,8,13,14,16,17,19,27,33,10,18,20,1,2,21,22,10,23,24,0,4,5,7,9,11,20,25,27,34,0,4,5,7,9,11,20,25,27,34,10,28,29,29,30,30,31,34,3,9,16,17,18,19,25,33,0,4,5,7,9,11,20,25,27,34};
  int i = 0;
  
  jacobian->sizeCols = 35;
  jacobian->sizeRows = 35;
  jacobian->sizeTmpVars = 0;
  jacobian->seedVars = (modelica_real*) calloc(35,sizeof(modelica_real));
  jacobian->resultVars = (modelica_real*) calloc(35,sizeof(modelica_real));
  jacobian->tmpVars = (modelica_real*) calloc(0,sizeof(modelica_real));
  jacobian->sparsePattern.leadindex = (unsigned int*) malloc((35+1)*sizeof(int));
  jacobian->sparsePattern.index = (unsigned int*) malloc(207*sizeof(int));
  jacobian->sparsePattern.numberOfNoneZeros = 207;
  jacobian->sparsePattern.colorCols = (unsigned int*) malloc(35*sizeof(int));
  jacobian->sparsePattern.maxColors = 18;
  
  /* write lead index of compressed sparse column */
  memcpy(jacobian->sparsePattern.leadindex, colPtrIndex, (35+1)*sizeof(int));
  
  for(i=2;i<35+1;++i)
    jacobian->sparsePattern.leadindex[i] += jacobian->sparsePattern.leadindex[i-1];
  
  /* call sparse index */
  memcpy(jacobian->sparsePattern.index, rowIndex, 207*sizeof(int));
  
  /* write color array */
  jacobian->sparsePattern.colorCols[0] = 1;
  jacobian->sparsePattern.colorCols[25] = 2;
  jacobian->sparsePattern.colorCols[9] = 3;
  jacobian->sparsePattern.colorCols[4] = 4;
  jacobian->sparsePattern.colorCols[34] = 5;
  jacobian->sparsePattern.colorCols[11] = 6;
  jacobian->sparsePattern.colorCols[18] = 7;
  jacobian->sparsePattern.colorCols[27] = 8;
  jacobian->sparsePattern.colorCols[17] = 9;
  jacobian->sparsePattern.colorCols[16] = 10;
  jacobian->sparsePattern.colorCols[33] = 11;
  jacobian->sparsePattern.colorCols[5] = 12;
  jacobian->sparsePattern.colorCols[3] = 13;
  jacobian->sparsePattern.colorCols[7] = 14;
  jacobian->sparsePattern.colorCols[6] = 15;
  jacobian->sparsePattern.colorCols[28] = 15;
  jacobian->sparsePattern.colorCols[14] = 16;
  jacobian->sparsePattern.colorCols[20] = 16;
  jacobian->sparsePattern.colorCols[13] = 17;
  jacobian->sparsePattern.colorCols[23] = 17;
  jacobian->sparsePattern.colorCols[29] = 17;
  jacobian->sparsePattern.colorCols[2] = 17;
  jacobian->sparsePattern.colorCols[19] = 18;
  jacobian->sparsePattern.colorCols[8] = 18;
  jacobian->sparsePattern.colorCols[10] = 18;
  jacobian->sparsePattern.colorCols[24] = 18;
  jacobian->sparsePattern.colorCols[30] = 18;
  jacobian->sparsePattern.colorCols[31] = 18;
  jacobian->sparsePattern.colorCols[21] = 18;
  jacobian->sparsePattern.colorCols[22] = 18;
  jacobian->sparsePattern.colorCols[1] = 18;
  jacobian->sparsePattern.colorCols[15] = 18;
  jacobian->sparsePattern.colorCols[26] = 18;
  jacobian->sparsePattern.colorCols[32] = 18;
  jacobian->sparsePattern.colorCols[12] = 18;
  TRACE_POP
  return 0;
}

int Biosystem_functionJacD_column(void* data, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian, ANALYTIC_JACOBIAN *parentJacobian)
{
  TRACE_PUSH
  TRACE_POP
  return 0;
}
int Biosystem_functionJacC_column(void* data, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian, ANALYTIC_JACOBIAN *parentJacobian)
{
  TRACE_PUSH
  TRACE_POP
  return 0;
}
int Biosystem_functionJacB_column(void* data, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian, ANALYTIC_JACOBIAN *parentJacobian)
{
  TRACE_PUSH
  TRACE_POP
  return 0;
}
int Biosystem_functionJacA_column(void* inData, threadData_t *threadData, ANALYTIC_JACOBIAN *jacobian, ANALYTIC_JACOBIAN *parentJacobian)
{
  TRACE_PUSH

  DATA* data = ((DATA*)inData);
  int index = Biosystem_INDEX_JAC_A;
  
  TRACE_POP
  return 0;
}


