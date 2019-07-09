/* Linearization */
#include "Biosystem_model.h"
#if defined(__cplusplus)
extern "C" {
#endif

const char *Biosystem_linear_model_frame()
{
  return "model linear_Biosystem\n  parameter Integer n = 35 \"number of states\";\n  parameter Integer p = 0 \"number of inputs\";\n  parameter Integer q = 0 \"number of outputs\";\n"
  "\n"
  "  parameter Real x0[n] = %s;\n"
  "  parameter Real u0[p] = %s;\n"
  "\n"
  "  parameter Real A[n, n] = [%s];\n"
  "  parameter Real B[n, p] = zeros(n, p);%s\n"
  "  parameter Real C[q, n] = zeros(q, n);%s\n"
  "  parameter Real D[q, p] = zeros(q, p);%s\n"
  "\n"
  "  Real x[n](start=x0);\n"
  "  input Real u[p];\n"
  "  output Real y[q];\n"
  "\n"
  "  Real 'x_c_1.ATM' = x[1];\n""  Real 'x_c_1.AdoHcy' = x[2];\n""  Real 'x_c_1.AdoMet' = x[3];\n""  Real 'x_c_1.BLM' = x[4];\n""  Real 'x_c_1.BRCA1' = x[5];\n""  Real 'x_c_1.BRCA2' = x[6];\n""  Real 'x_c_1.CDK2' = x[7];\n""  Real 'x_c_1.CDK4' = x[8];\n""  Real 'x_c_1.Cleaved_Meiotic_Holliday_Junction' = x[9];\n""  Real 'x_c_1.DMC1' = x[10];\n""  Real 'x_c_1.DNA' = x[11];\n""  Real 'x_c_1.H2AFX_Nucleosome' = x[12];\n""  Real 'x_c_1.HOP2_TBPIP_MND1' = x[13];\n""  Real 'x_c_1.MLH1' = x[14];\n""  Real 'x_c_1.MLH3' = x[15];\n""  Real 'x_c_1.MRN_CtIP' = x[16];\n""  Real 'x_c_1.MSH4' = x[17];\n""  Real 'x_c_1.MSH5' = x[18];\n""  Real 'x_c_1.Meiotic_D_loop_complex' = x[19];\n""  Real 'x_c_1.Meiotic_Holliday_Junction' = x[20];\n""  Real 'x_c_1.Meiotic_single_stranded_DNA_complex' = x[21];\n""  Real 'x_c_1.Nucleosome_with_H3K4me2' = x[22];\n""  Real 'x_c_1.Nucleosome_with_H3K4me3' = x[23];\n""  Real 'x_c_1.PRDM9' = x[24];\n""  Real 'x_c_1.PRDM9_DNA' = x[25];\n""  Real 'x_c_1.RAD51' = x[26];\n""  Real 'x_c_1.RAD51C' = x[27];\n""  Real 'x_c_1.RPA_heterotrimer' = x[28];\n""  Real 'x_c_1.SPO11_Dimer' = x[29];\n""  Real 'x_c_1.SPO11_double_stand_break' = x[30];\n""  Real 'x_c_1.SPO11_double_strand_break_with_3_single_strand_breaks' = x[31];\n""  Real 'x_c_1.SPO11_oligonucleotide' = x[32];\n""  Real 'x_c_1.TEX15' = x[33];\n""  Real 'x_c_1.TOP3A' = x[34];\n""  Real 'x_c_1.s_3_overhanging_DNA_at_resected_DSB_ends' = x[35];\n"
  "equation\n  der(x) = A * x + B * u;\n  y = C * x + D * u;\nend linear_Biosystem;\n";
}
const char *Biosystem_linear_model_datarecovery_frame()
{
  return "model linear_Biosystem\n  parameter Integer n = 35 \"number of states\";\n  parameter Integer p = 0 \"number of inputs\";\n  parameter Integer q = 0 \"number of outputs\";\n  parameter Integer nz = 9 \"data recovery variables\";\n"
  "\n"
  "  parameter Real x0[35] = %s;\n"
  "  parameter Real u0[0] = %s;\n"
  "  parameter Real z0[9] = %s;\n"
  "\n"
  "  parameter Real A[n, n] = [%s];\n"
  "  parameter Real B[n, p] = zeros(n, p);%s\n"
  "  parameter Real C[q, n] = zeros(q, n);%s\n"
  "  parameter Real D[q, p] = zeros(q, p);%s\n"
  "  parameter Real Cz[nz, n] = [%s];\n"
  "  parameter Real Dz[nz, p] = zeros(nz, p);%s\n"
  "\n"
  "  Real x[n](start=x0);\n"
  "  input Real u[p];\n"
  "  output Real y[q];\n"
  "  output Real z[nz];\n"
  "\n"
  "  Real 'x_c_1.ATM' = x[1];\n""  Real 'x_c_1.AdoHcy' = x[2];\n""  Real 'x_c_1.AdoMet' = x[3];\n""  Real 'x_c_1.BLM' = x[4];\n""  Real 'x_c_1.BRCA1' = x[5];\n""  Real 'x_c_1.BRCA2' = x[6];\n""  Real 'x_c_1.CDK2' = x[7];\n""  Real 'x_c_1.CDK4' = x[8];\n""  Real 'x_c_1.Cleaved_Meiotic_Holliday_Junction' = x[9];\n""  Real 'x_c_1.DMC1' = x[10];\n""  Real 'x_c_1.DNA' = x[11];\n""  Real 'x_c_1.H2AFX_Nucleosome' = x[12];\n""  Real 'x_c_1.HOP2_TBPIP_MND1' = x[13];\n""  Real 'x_c_1.MLH1' = x[14];\n""  Real 'x_c_1.MLH3' = x[15];\n""  Real 'x_c_1.MRN_CtIP' = x[16];\n""  Real 'x_c_1.MSH4' = x[17];\n""  Real 'x_c_1.MSH5' = x[18];\n""  Real 'x_c_1.Meiotic_D_loop_complex' = x[19];\n""  Real 'x_c_1.Meiotic_Holliday_Junction' = x[20];\n""  Real 'x_c_1.Meiotic_single_stranded_DNA_complex' = x[21];\n""  Real 'x_c_1.Nucleosome_with_H3K4me2' = x[22];\n""  Real 'x_c_1.Nucleosome_with_H3K4me3' = x[23];\n""  Real 'x_c_1.PRDM9' = x[24];\n""  Real 'x_c_1.PRDM9_DNA' = x[25];\n""  Real 'x_c_1.RAD51' = x[26];\n""  Real 'x_c_1.RAD51C' = x[27];\n""  Real 'x_c_1.RPA_heterotrimer' = x[28];\n""  Real 'x_c_1.SPO11_Dimer' = x[29];\n""  Real 'x_c_1.SPO11_double_stand_break' = x[30];\n""  Real 'x_c_1.SPO11_double_strand_break_with_3_single_strand_breaks' = x[31];\n""  Real 'x_c_1.SPO11_oligonucleotide' = x[32];\n""  Real 'x_c_1.TEX15' = x[33];\n""  Real 'x_c_1.TOP3A' = x[34];\n""  Real 'x_c_1.s_3_overhanging_DNA_at_resected_DSB_ends' = x[35];\n"
  "  Real 'z_c_1.reaction_1214188_rate' = z[1];\n""  Real 'z_c_1.reaction_9023941_rate' = z[2];\n""  Real 'z_c_1.reaction_9023943_rate' = z[3];\n""  Real 'z_c_1.reaction_912363_rate' = z[4];\n""  Real 'z_c_1.reaction_912368_rate' = z[5];\n""  Real 'z_c_1.reaction_912429_rate' = z[6];\n""  Real 'z_c_1.reaction_912458_rate' = z[7];\n""  Real 'z_c_1.reaction_912496_rate' = z[8];\n""  Real 'z_c_1.reaction_912503_rate' = z[9];\n"
  "equation\n  der(x) = A * x + B * u;\n  y = C * x + D * u;\n  z = Cz * x + Dz * u;\nend linear_Biosystem;\n";
}
#if defined(__cplusplus)
}
#endif

