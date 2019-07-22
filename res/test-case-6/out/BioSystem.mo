package BioSystem

    model Cell
        extends BioChem.Compartments.MainCompartment(V(start=cell_V));

        inner parameter BioChem.Units.Volume cell_V = 1.0E-11;

        model Cytosol
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_74016(n(start=1.0016646150801256E-12)) "Ca2+ [cytosol]";
            BioChem.Substances.Substance species_29804(n(start=9.932585235200716E-13)) "K+ [cytosol]";


        end Cytosol;

        model Cytoplasm
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_1132304(n(start=9.973328757830975E-13)) "H+ [cytoplasm]";


        end Cytoplasm;

        model NuclearEnvelope
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_912382(n(start=1.0132472945275593E-12)) "SUN2 [nuclear envelope]";
            BioChem.Substances.Substance species_912378(n(start=9.901699433834025E-13)) "LMNA-2 [nuclear envelope]";
            BioChem.Substances.Substance species_912404(n(start=9.98539041834945E-13)) "SYNE2 [nuclear envelope]";
            BioChem.Substances.Substance species_912352(n(start=1.0087935035243934E-12)) "SYNE1 [nuclear envelope]";
            BioChem.Substances.Substance species_912371(n(start=9.924919106353938E-13)) "SUN1 [nuclear envelope]";
            BioChem.Substances.Substance species_912402(n(start=1.0057994168983884E-12)) "LMNB1(2-586) [nuclear envelope]";
            BioChem.Substances.Substance species_912372(n(start=1.0249657322415492E-12)) "Telomere Attachment Plate [nuclear envelope]";


        end NuclearEnvelope;

        model PlasmaMembrane
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_2534353(n(start=1.0019186571230165E-12)) "Activated CatSper Channel [plasma membrane]";
            BioChem.Substances.Substance species_2534382(n(start=1.005513200728667E-12)) "CatSper Channel [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_2534372(n(start=1.0049214069549173E-12)) "Slo3 Channel [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_2534381(n(start=1.0178642184512102E-12)) "HV1 Channel [plasma membrane]";
            BioChem.Substances.Substance species_1297303(n(start=9.94622939840603E-13)) "B4GALT1 [plasma membrane]";
            BioChem.Substances.Substance species_1297290(n(start=9.908890944256578E-13)) "Adam Complex [plasma membrane]";
            BioChem.Substances.Substance species_1297316(n(start=9.930215240969082E-13)) "SPAM1 [plasma membrane]";
            BioChem.Substances.Substance species_51717(n(start=9.905551068683136E-13)) "CD9 [plasma membrane]";
            BioChem.Substances.Substance species_1297355(n(start=9.949267088891171E-13)) "Izumo [plasma membrane]";
            BioChem.Substances.Substance species_1297262(n(start=1.008701033917611E-12)) "CD9:Izumo [plasma membrane]";

            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_2534359(k1=1238.3819820256997) "CatSper Channel Mediated Calcium Transport";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_2534365(k1=1924.929623556297) "Slo3 Potassium Transport";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_2534378(k1=771.4289210274048) "Hv1 Mediated H+ Permeability";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_2534346(k1=264.2355812263787) "SPAM1 Binds Hyaluronic Acid";

        equation




            connect(species_1297316.n1, reaction_2534346.s1);

        end PlasmaMembrane;

        model ExtracellularRegion
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_74112(n(start=1.0169640097282333E-12)) "Ca2+ [extracellular region]";
            BioChem.Substances.Substance species_1449667(n(start=9.94784029559445E-13)) "P4 [extracellular region]";
            BioChem.Substances.Substance species_74126(n(start=1.0254363115634843E-12)) "K+ [extracellular region]";
            BioChem.Substances.Substance species_351626(n(start=1.0042548349229804E-12)) "H+ [extracellular region]";
            BioChem.Substances.Substance species_1297292(n(start=1.0186118791813097E-12)) "Zona pellucida [extracellular region]";
            BioChem.Substances.Substance species_1297296(n(start=1.0080997648786374E-12)) "Zona pellucida:B4GALT1 [extracellular region]";
            BioChem.Substances.Substance species_1297302(n(start=9.9930938294953E-13)) "HUA [extracellular region]";
            BioChem.Substances.Substance species_1297353(n(start=9.922227608774204E-13)) "SPAM1:Hyaluronic acid [extracellular region]";
            BioChem.Substances.Substance species_1297304(n(start=1.0039868207102054E-12)) "ACR(43-343) [extracellular region]";
            BioChem.Substances.Substance species_1297342(n(start=9.935711839795927E-13)) "ACR(20-42) [extracellular region]";
            BioChem.Substances.Substance species_1297314(n(start=1.0053935725293018E-12)) "Acrosin Heavy:light chain [extracellular region]";
            BioChem.Substances.Substance species_1297346(n(start=1.0024641315662374E-12)) "ACR(20-421) [extracellular region]";

            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_2534388(k1=661.6661232913897) "Progesterone Activation Of CatSper";
            BioChem.Reactions.MassAction.Irreversible.TriUni.Tui reaction_1297338(k1=1826.8546573849308) "Association of ADAM and B4GALT1 With ZP3";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_1297333(k1=2175.9562033191905) "Association of Acrosin Heavy and Light Chain";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_1297275(k1=1426.149158571223) "CD9:Izumo Binding";
            BioChem.Reactions.MassAction.Irreversible.UniBi.Ubi reaction_1297354(k1=2243.93931869683) "Acrosin Cleavage";

        equation

            connect(species_1449667.n1, reaction_2534388.s1);

            connect(species_1297292.n1, reaction_1297338.s1);
            connect(species_1297296.n1, reaction_1297338.p1);

            connect(species_1297304.n1, reaction_1297333.s2);
            connect(species_1297342.n1, reaction_1297333.s1);
            connect(species_1297314.n1, reaction_1297333.p1);


            connect(species_1297346.n1, reaction_1297354.s1);
            connect(species_1297304.n1, reaction_1297354.p1);
            connect(species_1297342.n1, reaction_1297354.p2);

        end ExtracellularRegion;

        model Nucleoplasm
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_912494(n(start=9.982523274061053E-13)) "SYCP1 [nucleoplasm]";
            BioChem.Substances.Substance species_8867779(n(start=9.893118582638051E-13)) "SYCE3 [nucleoplasm]";
            BioChem.Substances.Substance species_912441(n(start=1.0011671347491233E-12)) "TEX12 [nucleoplasm]";
            BioChem.Substances.Substance species_912456(n(start=1.0027590039272109E-12)) "SYCE1 [nucleoplasm]";
            BioChem.Substances.Substance species_914230(n(start=9.960906551107352E-13)) "DIDO1-1 [nucleoplasm]";
            BioChem.Substances.Substance species_912481(n(start=1.0075924670317039E-12)) "UBE2I [nucleoplasm]";
            BioChem.Substances.Substance species_912512(n(start=9.983679558081484E-13)) "FKBP6 [nucleoplasm]";
            BioChem.Substances.Substance species_912480(n(start=1.012513025953413E-12)) "HSPA2 [nucleoplasm]";
            BioChem.Substances.Substance species_912451(n(start=9.950644035668011E-13)) "SYCE2 [nucleoplasm]";
            BioChem.Substances.Substance species_912338(n(start=9.971350086095503E-13)) "Axial-Lateral Element of Synaptonemal Complex [nucleoplasm]";
            BioChem.Substances.Substance species_912422(n(start=9.959849212979455E-13)) "Synaptonemal Complex [nucleoplasm]";
            BioChem.Substances.Substance species_182751(n(start=9.88099737170473E-13)) "Extended And Processed Telomere End and Associated DNA Binding and Packaging Protein Complex Folded Into Higher Order Structure [nucleoplasm]";
            BioChem.Substances.Substance species_912329(n(start=9.790754360201778E-13)) "SYCP3 [nucleoplasm]";
            BioChem.Substances.Substance species_912407(n(start=9.974213667589906E-13)) "SYCP2 [nucleoplasm]";
            BioChem.Substances.Substance species_912384(n(start=9.90423550620119E-13)) "Meiotic Cohesin complex [nucleoplasm]";
            BioChem.Substances.Substance species_912463(n(start=9.949248457189652E-13)) "Synaptonemal Complex:BRCA1:ATR [nucleoplasm]";
            BioChem.Substances.Substance species_975778(n(start=9.988131311292862E-13)) "Unsynapsed chromatin [nucleoplasm]";
            BioChem.Substances.Substance species_29358(n(start=9.980566038208641E-13)) "ATP [nucleoplasm]";
            BioChem.Substances.Substance species_975781(n(start=1.0073957400000412E-12)) "Unsynapsed chromatin with gamma-H2A.x [nucleoplasm]";
            BioChem.Substances.Substance species_113582(n(start=9.926537239136335E-13)) "ADP [nucleoplasm]";
            BioChem.Substances.Substance species_50949(n(start=9.84815095856104E-13)) "BRCA1 [nucleoplasm]";
            BioChem.Substances.Substance species_912476(n(start=9.945522708012384E-13)) "Synaptonemal Complex:BRCA1 [nucleoplasm]";
            BioChem.Substances.Substance species_912443(n(start=9.92642054631743E-13)) "ATR [nucleoplasm]";
            BioChem.Substances.Substance species_912428(n(start=1.0032875426365952E-12)) "Meiotic Holliday Junction [nucleoplasm]";
            BioChem.Substances.Substance species_912495(n(start=1.0087418561991066E-12)) "MLH1 [nucleoplasm]";
            BioChem.Substances.Substance species_912478(n(start=1.024031480522608E-12)) "MLH3 [nucleoplasm]";
            BioChem.Substances.Substance species_68365(n(start=9.954523071779263E-13)) "CDK2 [nucleoplasm]";
            BioChem.Substances.Substance species_113843(n(start=1.0216015359880678E-12)) "CDK4 [nucleoplasm]";
            BioChem.Substances.Substance species_174881(n(start=1.0021211812837073E-12)) "BLM [nucleoplasm]";
            BioChem.Substances.Substance species_50951(n(start=1.0002077237281128E-12)) "BRCA2 [nucleoplasm]";
            BioChem.Substances.Substance species_912452(n(start=9.970992678539082E-13)) "TOP3A [nucleoplasm]";
            BioChem.Substances.Substance species_912455(n(start=1.0050762790297387E-12)) "MSH4 [nucleoplasm]";
            BioChem.Substances.Substance species_913201(n(start=9.831785924029926E-13)) "Cleaved Meiotic Holliday Junction [nucleoplasm]";
            BioChem.Substances.Substance species_912435(n(start=9.876619993173181E-13)) "MSH5 [nucleoplasm]";
            BioChem.Substances.Substance species_68462(n(start=9.94448280653212E-13)) "RPA heterotrimer [nucleoplasm]";
            BioChem.Substances.Substance species_29428(n(start=1.0016219040586093E-12)) "DNA [nucleoplasm]";
            BioChem.Substances.Substance species_912345(n(start=9.783287469885373E-13)) "PRDM9 [nucleoplasm]";
            BioChem.Substances.Substance species_912415(n(start=9.960590653707432E-13)) "PRDM9:DNA [nucleoplasm]";
            BioChem.Substances.Substance species_912507(n(start=1.011340107496251E-12)) "Meiotic single-stranded DNA complex [nucleoplasm]";
            BioChem.Substances.Substance species_912484(n(start=1.0190218925756026E-12)) "Meiotic D-loop complex [nucleoplasm]";
            BioChem.Substances.BoundarySubstance species_913509(n(start=1.0094183073883261E-12)) "HOP2(TBPIP):MND1 [nucleoplasm]";
            BioChem.Substances.Substance species_975775(n(start=1.0094670919692844E-12)) "H2AFX-Nucleosome [nucleoplasm]";
            BioChem.Substances.Substance species_75156(n(start=1.0118887109367182E-12)) "3' overhanging DNA at resected DSB ends [nucleoplasm]";
            BioChem.Substances.Substance species_912490(n(start=9.894554435769687E-13)) "DMC1 [nucleoplasm]";
            BioChem.Substances.Substance species_62637(n(start=9.859907480765271E-13)) "RAD51 [nucleoplasm]";
            BioChem.Substances.Substance species_69484(n(start=9.877810030537366E-13)) "ATM [nucleoplasm]";
            BioChem.Substances.BoundarySubstance species_914159(n(start=9.961096780698578E-13)) "TEX15 [nucleoplasm]";
            BioChem.Substances.BoundarySubstance species_217039(n(start=1.0054844025286142E-12)) "RAD51C [nucleoplasm]";
            BioChem.Substances.BoundarySubstance species_981776(n(start=1.0058221640783936E-12)) "MRN:CtIP [nucleoplasm]";
            BioChem.Substances.Substance species_912365(n(start=1.0192161692821273E-12)) "SPO11:double stand break [nucleoplasm]";
            BioChem.Substances.Substance species_9023945(n(start=9.89542435254408E-13)) "SPO11:double strand break with 3' single strand breaks [nucleoplasm]";
            BioChem.Substances.Substance species_912393(n(start=9.909629225411506E-13)) "SPO11 Dimer [nucleoplasm]";
            BioChem.Substances.Substance species_1214200(n(start=9.947208181718103E-13)) "Nucleosome with H3K4me2 [nucleoplasm]";
            BioChem.Substances.Substance species_77087(n(start=1.0069586562541345E-12)) "AdoMet [nucleoplasm]";
            BioChem.Substances.Substance species_1214169(n(start=9.920724799609649E-13)) "Nucleosome with H3K4me3 [nucleoplasm]";
            BioChem.Substances.Substance species_77502(n(start=9.970763945956261E-13)) "AdoHcy [nucleoplasm]";
            BioChem.Substances.Substance species_912381(n(start=1.0093986517242709E-12)) "SPO11:oligonucleotide [nucleoplasm]";

            Reactions.MassAction.Irreversible.Mmi reaction_912505(k1=2252.693513694373, nS={1,1,1,1,1,1,1,1,1,1}, nP={1}) "Synapsis";
            Reactions.MassAction.Irreversible.Mmi reaction_912408(k1=1576.8971049158718, nS={1,1,1,1,1,1,1,1}, nP={1}) "Telomeres cluster at the nuclear membrane";
            BioChem.Reactions.MassAction.Irreversible.TriUni.Tui reaction_912389(k1=919.8205279099934) "Formation of axial/lateral elements of Synaptonemal Complex";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_912470(k1=1504.8212544725084) "ATR phosphorylates Histone H2A.X at unsynapsed regions";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_912467(k1=1514.8309182219718) "BRCA1 is recruited to unsynapsed regions";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_912450(k1=546.5477719982563) "ATR Kinase is recruited to unsynapsed regions";
            Reactions.MassAction.Irreversible.Mmi reaction_912429(k1=224.19279617359166, nS={1,1,1,1}, nP={1,1,1,1,1,1,1,1}) "Resolution of meiotic holliday junction";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_912363(k1=392.7329615131248) "PRDM9 binds recombination hotspot motifs in DNA";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_912458(k1=869.3823295597267) "Formation of meiotic heteroduplex";
            Reactions.MassAction.Irreversible.Mmi reaction_912503(k1=1483.657119151518, nS={1,1,1,1,1,1,1,1,1}, nP={1}) "Formation of meiotic single-stranded DNA invasion complex";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_9023941(k1=676.8407613142022) "MRN:CtIP endonucleolytically cleaves single-strand DNA 3' to SPO11";
            BioChem.Reactions.MassAction.Irreversible.BiUni.Bui reaction_912368(k1=1406.2760150176985) "SPO11 hydrolyzes DNA forming double-strand breaks";
            Reactions.MassAction.Irreversible.Mmi reaction_912496(k1=727.9385790730832, nS={1,1,1,1,1}, nP={1,1,1}) "Formation of meiotic holliday junction";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1214188(k1=1891.5865054633061, s1=2, p1=2) "PRDM9 trimethylates histone H3";
            BioChem.Reactions.MassAction.Irreversible.UniBi.Ubi reaction_9023943(k1=932.3627975170778) "MRN:CtIP exonucleolytically hydrolyzes DNA 3' to SPO11 and SPO11:double-strand break dissociates to SPO11:oligonucleotide and resected 5' end";

        equation

            connect(species_912480.n1, reaction_912505.s10);
            connect(species_914230.n1, reaction_912505.s9);
            connect(species_912481.n1, reaction_912505.s8);
            connect(species_912441.n1, reaction_912505.s7);
            connect(species_912512.n1, reaction_912505.s6);
            connect(species_8867779.n1, reaction_912505.s5);
            connect(species_912338.n1, reaction_912505.s4);
            connect(species_912494.n1, reaction_912505.s3);
            connect(species_912451.n1, reaction_912505.s2);
            connect(species_912456.n1, reaction_912505.s1);
            connect(species_912422.n1, reaction_912505.p1);

            connect(species_912338.n1, reaction_912408.s2);
            connect(species_182751.n1, reaction_912408.s1);

            connect(species_912329.n1, reaction_912389.s3);
            connect(species_912384.n1, reaction_912389.s2);
            connect(species_912407.n1, reaction_912389.s1);
            connect(species_912338.n1, reaction_912389.p1);

            connect(species_29358.n1, reaction_912470.s2);
            connect(species_975778.n1, reaction_912470.s1);
            connect(species_975781.n1, reaction_912470.p1);
            connect(species_113582.n1, reaction_912470.p2);

            connect(species_975778.n1, reaction_912467.s2);
            connect(species_50949.n1, reaction_912467.s1);
            connect(species_912476.n1, reaction_912467.p1);

            connect(species_912443.n1, reaction_912450.s2);
            connect(species_912476.n1, reaction_912450.s1);
            connect(species_912463.n1, reaction_912450.p1);

            connect(species_912478.n1, reaction_912429.s4);
            connect(species_912495.n1, reaction_912429.s3);
            connect(species_68365.n1, reaction_912429.s2);
            connect(species_912428.n1, reaction_912429.s1);
            connect(species_912455.n1, reaction_912429.p1);
            connect(species_50951.n1, reaction_912429.p2);
            connect(species_113843.n1, reaction_912429.p3);
            connect(species_912452.n1, reaction_912429.p4);
            connect(species_174881.n1, reaction_912429.p5);
            connect(species_68462.n1, reaction_912429.p6);
            connect(species_913201.n1, reaction_912429.p7);
            connect(species_912435.n1, reaction_912429.p8);

            connect(species_912345.n1, reaction_912363.s2);
            connect(species_29428.n1, reaction_912363.s1);
            connect(species_912415.n1, reaction_912363.p1);

            connect(species_912507.n1, reaction_912458.s2);
            connect(species_29428.n1, reaction_912458.s1);
            connect(species_912484.n1, reaction_912458.p1);

            connect(species_75156.n1, reaction_912503.s9);
            connect(species_50951.n1, reaction_912503.s8);
            connect(species_113843.n1, reaction_912503.s7);
            connect(species_62637.n1, reaction_912503.s6);
            connect(species_975775.n1, reaction_912503.s5);
            connect(species_912490.n1, reaction_912503.s4);
            connect(species_68462.n1, reaction_912503.s3);
            connect(species_50949.n1, reaction_912503.s2);
            connect(species_69484.n1, reaction_912503.s1);
            connect(species_912507.n1, reaction_912503.p1);

            connect(species_912365.n1, reaction_9023941.s1);
            connect(species_9023945.n1, reaction_9023941.p1);

            connect(species_912393.n1, reaction_912368.s2);
            connect(species_29428.n1, reaction_912368.s1);
            connect(species_912365.n1, reaction_912368.p1);

            connect(species_912455.n1, reaction_912496.s5);
            connect(species_912452.n1, reaction_912496.s4);
            connect(species_174881.n1, reaction_912496.s3);
            connect(species_912484.n1, reaction_912496.s2);
            connect(species_912435.n1, reaction_912496.s1);
            connect(species_62637.n1, reaction_912496.p1);
            connect(species_912428.n1, reaction_912496.p2);
            connect(species_912490.n1, reaction_912496.p3);

            connect(species_77087.n1, reaction_1214188.s2);
            connect(species_1214200.n1, reaction_1214188.s1);
            connect(species_77502.n1, reaction_1214188.p1);
            connect(species_1214169.n1, reaction_1214188.p2);

            connect(species_9023945.n1, reaction_9023943.s1);
            connect(species_75156.n1, reaction_9023943.p1);
            connect(species_912381.n1, reaction_9023943.p2);

        end Nucleoplasm;

        Cytosol c_1 "compartment_70101";
        Cytoplasm c_2 "compartment_459";
        NuclearEnvelope c_3 "compartment_13231";
        PlasmaMembrane c_4 "compartment_876";
        ExtracellularRegion c_5 "compartment_984";
        Nucleoplasm c_6 "compartment_7660";

    equation

        connect(c_1.species_74016.n1, c_4.reaction_2534359.p1);
        connect(c_5.species_74112.n1, c_4.reaction_2534359.s1);
        connect(c_5.species_74126.n1, c_4.reaction_2534365.p1);
        connect(c_1.species_29804.n1, c_4.reaction_2534365.s1);
        connect(c_2.species_1132304.n1, c_4.reaction_2534378.s1);
        connect(c_5.species_351626.n1, c_4.reaction_2534378.p1);
        connect(c_5.species_1297302.n1, c_4.reaction_2534346.s2);
        connect(c_5.species_1297353.n1, c_4.reaction_2534346.p1);
        connect(c_4.species_2534353.n1, c_5.reaction_2534388.p1);
        connect(c_4.species_2534382.n1, c_5.reaction_2534388.s2);
        connect(c_4.species_1297303.n1, c_5.reaction_1297338.s3);
        connect(c_4.species_1297290.n1, c_5.reaction_1297338.s2);
        connect(c_4.species_51717.n1, c_5.reaction_1297275.s2);
        connect(c_4.species_1297355.n1, c_5.reaction_1297275.s1);
        connect(c_4.species_1297262.n1, c_5.reaction_1297275.p1);
        connect(c_3.species_912371.n1, c_6.reaction_912408.s8);
        connect(c_3.species_912352.n1, c_6.reaction_912408.s7);
        connect(c_3.species_912404.n1, c_6.reaction_912408.s6);
        connect(c_3.species_912402.n1, c_6.reaction_912408.s5);
        connect(c_3.species_912378.n1, c_6.reaction_912408.s4);
        connect(c_3.species_912382.n1, c_6.reaction_912408.s3);
        connect(c_3.species_912372.n1, c_6.reaction_912408.p1);

    end Cell;

end BioSystem;
