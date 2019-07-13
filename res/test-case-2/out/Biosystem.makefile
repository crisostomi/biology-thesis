# Makefile generated by OpenModelica
# Platform: linux64

# Simulations use -O3 by default
CC=clang
CXX=clang++
LINK=clang -shared
EXEEXT=
DLLEXT=.so
CFLAGS_BASED_ON_INIT_FILE=
DEBUG_FLAGS=-Os
CFLAGS=$(CFLAGS_BASED_ON_INIT_FILE) $(DEBUG_FLAGS) -fPIC -mfpmath=sse ${MODELICAUSERCFLAGS}   
CPPFLAGS= -I"/usr/include/omc/c" -I. -DOPENMODELICA_XML_FROM_FILE_AT_RUNTIME -DOMC_MODEL_PREFIX=BioSystem -DOMC_NUM_MIXED_SYSTEMS=0 -DOMC_NUM_LINEAR_SYSTEMS=0 -DOMC_NUM_NONLINEAR_SYSTEMS=0 -DOMC_NDELAY_EXPRESSIONS=0 -DOMC_NVAR_STRING=0
LDFLAGS=-L"/usr/lib/x86_64-linux-gnu/omc" -L"/usr/lib" -Wl,-rpath,"/usr/lib/x86_64-linux-gnu/omc" -Wl,-rpath,"/usr/lib"     -lSimulationRuntimeC -llapack -lblas -lm -lm -lomcgc -lpthread -rdynamic -Wl,--no-undefined 
DIREXTRA=-L"/home/scacio/Dropbox/Personale/Workspace/IdeaProjects/BiologyThesis/res/test-case-2/out"
MAINFILE=BioSystem.c
MAINOBJ=BioSystem.o
CFILES=Biosystem_functions.c Biosystem_records.c \
Biosystem_01exo.c Biosystem_02nls.c Biosystem_03lsy.c Biosystem_04set.c Biosystem_05evt.c Biosystem_06inz.c Biosystem_07dly.c \
Biosystem_08bnd.c Biosystem_09alg.c Biosystem_10asr.c Biosystem_11mix.c Biosystem_12jac.c Biosystem_13opt.c Biosystem_14lnz.c \
Biosystem_15syn.c Biosystem_16dae.c Biosystem_17inl.c 

OFILES=$(CFILES:.c=.o)
GENERATEDFILES=$(MAINFILE) BioSystem.makefile Biosystem_literals.h Biosystem_functions.h $(CFILES)

.PHONY: omc_main_target clean bundle

# This is to make sure that Biosystem_*.c are always compiled.
.PHONY: $(CFILES)

omc_main_target: $(MAINOBJ) Biosystem_functions.h Biosystem_literals.h $(OFILES)
	$(CC) -I. -o BioSystem$(EXEEXT) $(MAINOBJ) $(OFILES) $(CPPFLAGS) $(DIREXTRA)   $(CFLAGS) $(CPPFLAGS) $(LDFLAGS)
clean:
	@rm -f Biosystem_records.o $(MAINOBJ)

bundle:
	@tar -cvf Biosystem_Files.tar $(GENERATEDFILES)