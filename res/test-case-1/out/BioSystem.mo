package BioSystem

    model Cell
        extends BioChem.Compartments.MainCompartment(V(start=cell_V));

        inner parameter BioChem.Units.Volume cell_V = 1.0E-11;

        model Cytosol
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_29438(n(start=1.0053173754094836E-12)) "GTP [cytosol]";
            BioChem.Substances.Substance species_29420(n(start=1.0109823079506435E-12)) "GDP [cytosol]";
            BioChem.Substances.Substance species_1225828(n(start=1.0003330964398033E-12)) "CDC37 [cytosol]";
            BioChem.Substances.Substance species_1221657(n(start=9.969822963258214E-13)) "HSP90 [cytosol]";
            BioChem.Substances.Substance species_74680(n(start=1.0063881034435095E-12)) "SHC1 [cytosol]";
            BioChem.Substances.Substance species_109797(n(start=9.955166005281646E-13)) "GRB2-1:SOS1 [cytosol]";
            BioChem.Substances.Substance species_113592(n(start=9.962944414413905E-13)) "ATP [cytosol]";
            BioChem.Substances.Substance species_29370(n(start=1.009847558270426E-12)) "ADP [cytosol]";
            BioChem.Substances.Substance species_179849(n(start=9.9311067493841E-13)) "GRB2:GAB1 [cytosol]";
            BioChem.Substances.Substance species_1806218(n(start=1.000076064507726E-12)) "PIK3CA:PIK3R1 [cytosol]";
            BioChem.Substances.Substance species_112199(n(start=9.978959042058609E-13)) "CBL [cytosol]";
            BioChem.Substances.Substance species_1217511(n(start=9.97723330435853E-13)) "Benzoquinoid ansamycins [cytosol]";
            BioChem.Substances.Substance species_1218829(n(start=1.0106440730651564E-12)) "HSP90:Benzoquinoid ansamycins [cytosol]";
            BioChem.Substances.Substance species_70113(n(start=9.93659295081059E-13)) "Glc [cytosol]";
            BioChem.Substances.Substance species_29356(n(start=1.0116761583352133E-12)) "H2O [cytosol]";
            BioChem.Substances.Substance species_1369066(n(start=1.0073030963524798E-12)) "porphyrin [cytosol]";

            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_5637808(k1=630.4765515611782) "SOS-mediated nucleotide exchange of RAS (mediated by GRB2:SOS1 in complex with phosphorylated SHC1 and p-EGFRvIII)";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_5637766(k1=382.1397299459817) "Binding of SHC1 to p-5Y-EGFRvIII";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_5637764(k1=802.1704866028723) "Binding of GRB2:GAB1 complex to p-EGFRvIII mutant";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_5637765(k1=1229.9612953075484) "Binding of PI3K to complex of GRB2:GAB1 and p-EGFRvIII";
            //WARNING: could not infer reaction type of reaction_5637794
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_5637795(k1=135.7885219376284, s1=4, p1=4) "Phosphorylation of PLC-gamma 1 by p-EGFRvIII mutant";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_5637792(k1=98.16025379381449) "PLC-gamma 1 binds to p-EGFRvIII mutant";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_5637806(k1=1277.0123264518093) "SOS-mediated nucleotide exchange of RAS (mediated by GRB2:SOS1 in complex with p-EGFRvIII)";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_1218824(k1=1548.5829776930577, s1=2) "HSP90 is inactivated by binding to benzaquinoid ansamycins";

        equation

            connect(species_29438.n1, reaction_5637808.s1);
            connect(species_29420.n1, reaction_5637808.p2);

            connect(species_74680.n1, reaction_5637766.s1);

            connect(species_179849.n1, reaction_5637764.s1);

            connect(species_1806218.n1, reaction_5637765.s1);

            connect(species_113592.n1, reaction_5637795.s1);
            connect(species_29370.n1, reaction_5637795.p2);


            connect(species_29438.n1, reaction_5637806.s1);
            connect(species_29420.n1, reaction_5637806.p2);

            connect(species_1217511.n1, reaction_1218824.s2);
            connect(species_1221657.n1, reaction_1218824.s1);
            connect(species_1218829.n1, reaction_1218824.p1);

        end Cytosol;

        model MitochondrialOuterMembrane
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.BoundarySubstance species_5683361(n(start=9.932982494210075E-13)) "ABCB6 mutants [mitochondrial outer membrane]";

            //WARNING: could not infer reaction type of reaction_5683355

        end MitochondrialOuterMembrane;

        model PlasmaMembrane
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_5637704(n(start=1.019690464430819E-12)) "p-5Y-EGFRvIII:p-Y349,Y350-SHC1:GRB2:SOS1 [plasma membrane]";
            BioChem.Substances.Substance species_109796(n(start=1.0153468500064358E-12)) "p21 RAS:GDP [plasma membrane]";
            BioChem.Substances.Substance species_109783(n(start=1.0041806594397547E-12)) "p21 RAS:GTP [plasma membrane]";
            BioChem.Substances.Substance species_1214162(n(start=9.931960586993581E-13)) "EGFRvIII [plasma membrane]";
            BioChem.Substances.Substance species_1248004(n(start=9.802919265965696E-13)) "EGFRvIII mutant:HSP90:CDC37 [plasma membrane]";
            BioChem.Substances.Substance species_1248654(n(start=1.0119251037573697E-12)) "p-5Y-EGFRvIII mutant dimer [plasma membrane]";
            BioChem.Substances.Substance species_5637701(n(start=1.007739909452648E-12)) "p-5Y-EGFRvIII:SHC1 [plasma membrane]";
            BioChem.Substances.Substance species_5637703(n(start=1.0081365156231618E-12)) "p-5Y-EGFRvIII:p-Y349,Y350-SHC1 [plasma membrane]";
            BioChem.Substances.Substance species_1248010(n(start=9.958095354330082E-13)) "EGFRvIII mutant dimer [plasma membrane]";
            BioChem.Substances.Substance species_5637694(n(start=1.0001426566178308E-12)) "p-5Y-EGFRvIII:GRB2:GAB1 [plasma membrane]";
            BioChem.Substances.Substance species_5637693(n(start=9.909847915799985E-13)) "p-5Y-EGFRvIII:GRB2:GAB1:PI3K [plasma membrane]";
            BioChem.Substances.Substance species_5637698(n(start=1.0073678447410738E-12)) "p-5Y-EGFRvIII:PLCG1 [plasma membrane]";
            BioChem.Substances.Substance species_5637706(n(start=1.0007487571805922E-12)) "p-5Y-EGFRvIII:p-Y771,Y783,Y1254-PLCG1 [plasma membrane]";
            BioChem.Substances.Substance species_167682(n(start=9.851358674317623E-13)) "PLCG1 [plasma membrane]";
            BioChem.Substances.Substance species_5637697(n(start=1.001554719534231E-12)) "p-5Y-EGFRvIII:GRB2:SOS1 [plasma membrane]";
            BioChem.Substances.Substance species_167679(n(start=9.94813318721216E-13)) "p-4Y-PLCG1 [plasma membrane]";
            BioChem.Substances.Substance species_179856(n(start=1.006592177864735E-12)) "PI(4,5)P2 [plasma membrane]";
            BioChem.Substances.Substance species_179838(n(start=9.940192140540274E-13)) "PI(3,4,5)P3 [plasma membrane]";

            BioChem.Reactions.MassAction.Irreversible.TriUni.Tui reaction_1247999(k1=1111.4854225096253) "EGFRvIII mutant binds chaperone proteins HSP90 and CDC37.";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_5637798(k1=2465.2211005257855) "Phosphorylated SHC1 in complex with p-5Y-EGFRvIII recruits GRB2:SOS1 complex";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_1248002(k1=1852.006911833825, s1=2) "Ligand-independent dimerization of EGFRvIII mutant";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_5637796(k1=1343.600662528435, s1=2, p1=2) "Phosphorylation of SHC1 by p-5Y-EGFRvIII";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1248655(k1=2011.1274087671186, s1=10, p1=10) "Trans-autophosphorylation of EGFRvIII mutant dimers";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_5637770(k1=640.6979184376239) "Binding of GRB2:SOS1 complex to phosphorylated EGFRvIII";
            BioChem.Reactions.MassAction.Irreversible.UniBi.Ubi reaction_5637800(k1=1573.3109677608145) "Dissociation of phosphorylated PLC-gamma 1 from p-EGFRvIII mutant";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_5637801(k1=644.4525193250802) "Conversion of PIP2 to PIP3 by PI3K bound to phosphorylated EGFRvIII";

        equation

            connect(species_1214162.n1, reaction_1247999.s1);
            connect(species_1248004.n1, reaction_1247999.p1);

            connect(species_5637703.n1, reaction_5637798.s1);
            connect(species_5637704.n1, reaction_5637798.p1);

            connect(species_1248004.n1, reaction_1248002.s1);
            connect(species_1248010.n1, reaction_1248002.p1);

            connect(species_5637701.n1, reaction_5637796.s1);
            connect(species_5637703.n1, reaction_5637796.p2);

            connect(species_1248010.n1, reaction_1248655.s1);
            connect(species_1248654.n1, reaction_1248655.p2);

            connect(species_1248654.n1, reaction_5637770.s1);
            connect(species_5637697.n1, reaction_5637770.p1);

            connect(species_5637706.n1, reaction_5637800.s1);
            connect(species_1248654.n1, reaction_5637800.p1);
            connect(species_167679.n1, reaction_5637800.p2);

            connect(species_179856.n1, reaction_5637801.s1);
            connect(species_179838.n1, reaction_5637801.p2);

        end PlasmaMembrane;

        model ExtracellularRegion
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_179863(n(start=9.937052310136627E-13)) "EGF [extracellular region]";

            //WARNING: could not infer reaction type of reaction_5638137

        end ExtracellularRegion;

        model GolgiMembrane
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_5653885(n(start=9.822503259585001E-13)) "B4GALT1:LALBA [Golgi membrane]";
            BioChem.Substances.BoundarySubstance species_5653881(n(start=9.985651658736901E-13)) "SLC2A1 tetramer [Golgi membrane]";
            BioChem.Substances.Substance species_528072(n(start=9.944073072004538E-13)) "B4GALT1 [Golgi membrane]";


        end GolgiMembrane;

        model GolgiLumen
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_964746(n(start=1.0261662937968615E-12)) "Glc [Golgi lumen]";
            BioChem.Substances.Substance species_735682(n(start=9.914948438665218E-13)) "UDP-Gal [Golgi lumen]";
            BioChem.Substances.Substance species_205687(n(start=9.92738360199983E-13)) "UDP [Golgi lumen]";
            BioChem.Substances.Substance species_5653871(n(start=1.0097874900636573E-12)) "Lac [Golgi lumen]";
            BioChem.Substances.Substance species_5653896(n(start=1.013945917109958E-12)) "LALBA [Golgi lumen]";
            BioChem.Substances.BoundarySubstance species_5653875(n(start=1.0078872839256616E-12)) "Mn2+ [Golgi lumen]";

            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_5653878(k1=662.1744314246039) "B4GALT1:LALBA transfers Gal from UDP-Gal to Glc to form Lac";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_5653873(k1=69.14810621800018) "SLC2A1 tetramer transports Glc from cytosol to Golgi lumen";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_5653886(k1=1535.2839906963268) "B4GALT1 binds LALBA";

        equation

            connect(species_964746.n1, reaction_5653878.s2);
            connect(species_735682.n1, reaction_5653878.s1);
            connect(species_5653871.n1, reaction_5653878.p1);
            connect(species_205687.n1, reaction_5653878.p2);

            connect(species_964746.n1, reaction_5653873.p1);

            connect(species_5653896.n1, reaction_5653886.s1);

        end GolgiLumen;

        Cytosol c_1 "compartment_70101";
        MitochondrialOuterMembrane c_2 "compartment_17906";
        PlasmaMembrane c_3 "compartment_876";
        ExtracellularRegion c_4 "compartment_984";
        GolgiMembrane c_5 "compartment_20699";
        GolgiLumen c_6 "compartment_17963";

    equation

        connect(c_3.species_109783.n1, c_1.reaction_5637808.p1);
        connect(c_3.species_109796.n1, c_1.reaction_5637808.s2);
        connect(c_3.species_5637701.n1, c_1.reaction_5637766.p1);
        connect(c_3.species_1248654.n1, c_1.reaction_5637766.s2);
        connect(c_3.species_5637694.n1, c_1.reaction_5637764.p1);
        connect(c_3.species_1248654.n1, c_1.reaction_5637764.s2);
        connect(c_3.species_5637694.n1, c_1.reaction_5637765.s2);
        connect(c_3.species_5637693.n1, c_1.reaction_5637765.p1);
        connect(c_3.species_5637706.n1, c_1.reaction_5637795.p1);
        connect(c_3.species_5637698.n1, c_1.reaction_5637795.s2);
        connect(c_3.species_5637698.n1, c_1.reaction_5637792.p1);
        connect(c_3.species_167682.n1, c_1.reaction_5637792.s2);
        connect(c_3.species_1248654.n1, c_1.reaction_5637792.s1);
        connect(c_3.species_109796.n1, c_1.reaction_5637806.s2);
        connect(c_3.species_109783.n1, c_1.reaction_5637806.p1);
        connect(c_1.species_1221657.n1, c_3.reaction_1247999.s3);
        connect(c_1.species_1225828.n1, c_3.reaction_1247999.s2);
        connect(c_1.species_109797.n1, c_3.reaction_5637798.s2);
        connect(c_1.species_29370.n1, c_3.reaction_5637796.p1);
        connect(c_1.species_113592.n1, c_3.reaction_5637796.s2);
        connect(c_1.species_113592.n1, c_3.reaction_1248655.s2);
        connect(c_1.species_29370.n1, c_3.reaction_1248655.p1);
        connect(c_1.species_109797.n1, c_3.reaction_5637770.s2);
        connect(c_1.species_113592.n1, c_3.reaction_5637801.s2);
        connect(c_1.species_29370.n1, c_3.reaction_5637801.p1);
        connect(c_1.species_70113.n1, c_6.reaction_5653873.s1);
        connect(c_5.species_528072.n1, c_6.reaction_5653886.s2);
        connect(c_5.species_5653885.n1, c_6.reaction_5653886.p1);

    end Cell;

end BioSystem;
