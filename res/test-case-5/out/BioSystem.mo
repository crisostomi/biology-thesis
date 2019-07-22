package BioSystem

    model Cell
        extends BioChem.Compartments.MainCompartment(V(start=cell_V));

        inner parameter BioChem.Units.Volume cell_V = 1.0E-11;

        model Cytosol
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_113528(n(start=9.867155898635702E-13)) "CO2 [cytosol]";
            BioChem.Substances.Substance species_73473(n(start=9.97454131583539E-13)) "NADH [cytosol]";
            BioChem.Substances.Substance species_9033251(n(start=9.931871280900744E-13)) "MetHb [cytosol]";
            BioChem.Substances.Substance species_29360(n(start=1.021950669724361E-12)) "NAD+ [cytosol]";
            BioChem.Substances.Substance species_70106(n(start=9.97387291773065E-13)) "H+ [cytosol]";
            BioChem.Substances.Substance species_9033252(n(start=9.998924815959E-13)) "HbA [cytosol]";
            BioChem.Substances.Substance species_111627(n(start=9.979943763617786E-13)) "HCO3- [cytosol]";
            BioChem.Substances.Substance species_352022(n(start=1.0121357313923182E-12)) "Cl- [cytosol]";
            BioChem.Substances.BoundarySubstance species_1475437(n(start=9.974559277380918E-13)) "CA1,2 [cytosol]";
            BioChem.Substances.Substance species_29356(n(start=1.0001276348792783E-12)) "H2O [cytosol]";
            BioChem.Substances.Substance species_1237320(n(start=1.0045558573038509E-12)) "OxyHbA [cytosol]";
            BioChem.Substances.Substance species_1237312(n(start=9.95998912477658E-13)) "Protonated Carbamino DeoxyHbA [cytosol]";
            BioChem.Substances.Substance species_29368(n(start=1.00884674187218E-12)) "O2 [cytosol]";

            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_1237042(k1=1318.841819503715) "AQP1 passively tranlocates carbon dioxide from the extracellular region to the cytosol";
            BioChem.Reactions.MassAction.Irreversible.BiTri.Bti reaction_6806831(k1=134.8248928247299) "CYB5Rs reduce MetHb to HbA";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1237038(k1=346.8625365788208) "SLC4A1 exchanges cytosolic HCO3- for extracellular Cl-";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1475435(k1=1640.379265321876) "Carbonic anhydrase I/II hydrates carbon dioxide";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_1237069(k1=213.6877189378083) "RhAG passively translocates carbon dioxide from the extracellular region to the cytosol";
            BioChem.Reactions.MassAction.Irreversible.TriBi.Tbi reaction_1237325(k1=918.0656301357344, s1=4, s2=4, p1=4) "Hemoglobin A is protonated and carbamated causing release of oxygen";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1247665(k1=371.8176935883969) "Band 3 Anion Exchanger (AE1, SLC4A1) exchanges cytosolic chloride for extracellular bicarbonate";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_1247649(k1=2028.9202619084474) "AQP1 passively translocates carbon dioxide from the cytosol to the extracellular region";
            BioChem.Reactions.MassAction.Irreversible.BiTri.Bti reaction_1247668(k1=136.29834533278438, s1=4, p1=4, p2=4) "Hemoglobin A binds oxygen and releases protons and carbon dioxide";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1475436(k1=1775.0806367719392) "Carbonic anhydrase I/II dehydrates bicarbonate";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uui reaction_1247645(k1=2109.885318576287) "RhAG passively translocates carbon dioxide from the cytosol to the extracellular region";

        equation

            connect(species_113528.n1, reaction_1237042.p1);

            connect(species_9033251.n1, reaction_6806831.s2);
            connect(species_73473.n1, reaction_6806831.s1);
            connect(species_70106.n1, reaction_6806831.p1);
            connect(species_9033252.n1, reaction_6806831.p2);
            connect(species_29360.n1, reaction_6806831.p3);

            connect(species_111627.n1, reaction_1237038.s1);
            connect(species_352022.n1, reaction_1237038.p2);

            connect(species_113528.n1, reaction_1475435.s2);
            connect(species_29356.n1, reaction_1475435.s1);
            connect(species_70106.n1, reaction_1475435.p1);
            connect(species_111627.n1, reaction_1475435.p2);

            connect(species_113528.n1, reaction_1237069.p1);

            connect(species_70106.n1, reaction_1237325.s3);
            connect(species_113528.n1, reaction_1237325.s2);
            connect(species_1237320.n1, reaction_1237325.s1);
            connect(species_1237312.n1, reaction_1237325.p1);
            connect(species_29368.n1, reaction_1237325.p2);

            connect(species_352022.n1, reaction_1247665.s1);
            connect(species_111627.n1, reaction_1247665.p2);

            connect(species_113528.n1, reaction_1247649.s1);

            connect(species_1237312.n1, reaction_1247668.s2);
            connect(species_29368.n1, reaction_1247668.s1);
            connect(species_70106.n1, reaction_1247668.p1);
            connect(species_113528.n1, reaction_1247668.p2);
            connect(species_1237320.n1, reaction_1247668.p3);

            connect(species_70106.n1, reaction_1475436.s2);
            connect(species_111627.n1, reaction_1475436.s1);
            connect(species_113528.n1, reaction_1475436.p1);
            connect(species_29356.n1, reaction_1475436.p2);

            connect(species_113528.n1, reaction_1247645.s1);

        end Cytosol;

        model EndoplasmicReticulumMembrane
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.BoundarySubstance species_6806851(n(start=9.791329916021545E-13)) "CYB5Rs [endoplasmic reticulum membrane]";


        end EndoplasmicReticulumMembrane;

        model PlasmaMembrane
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.BoundarySubstance species_432246(n(start=1.0144482236762628E-12)) "AQP1 tetramer [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_1237308(n(start=1.0049301209911222E-12)) "CA4:Zn2+ [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_1244330(n(start=1.0102894425043848E-12)) "SLC4A1 dimer [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_444408(n(start=9.831713447450901E-13)) "RHAG [plasma membrane]";


        end PlasmaMembrane;

        model ExtracellularRegion
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.Substance species_1237009(n(start=9.977010605944427E-13)) "CO2 [extracellular region]";
            BioChem.Substances.Substance species_109276(n(start=1.009631177955719E-12)) "H2O [extracellular region]";
            BioChem.Substances.Substance species_425425(n(start=1.0098832042411276E-12)) "HCO3- [extracellular region]";
            BioChem.Substances.Substance species_351626(n(start=1.004807074110723E-12)) "H+ [extracellular region]";
            BioChem.Substances.Substance species_188972(n(start=1.0069432124782732E-12)) "Cl- [extracellular region]";

            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1237047(k1=1551.3917067482141) "Carbonic anhydrase IV hydrates carbon dioxide to bicarbonate and a proton";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbi reaction_1237059(k1=1816.273969685999) "Carbonic anhydrase IV dehydrates bicarbonate to water and carbon dioxide";

        equation

            connect(species_1237009.n1, reaction_1237047.s2);
            connect(species_109276.n1, reaction_1237047.s1);
            connect(species_425425.n1, reaction_1237047.p1);
            connect(species_351626.n1, reaction_1237047.p2);

            connect(species_425425.n1, reaction_1237059.s2);
            connect(species_351626.n1, reaction_1237059.s1);
            connect(species_1237009.n1, reaction_1237059.p1);
            connect(species_109276.n1, reaction_1237059.p2);

        end ExtracellularRegion;

        Cytosol c_1 "compartment_70101";
        EndoplasmicReticulumMembrane c_2 "compartment_12045";
        PlasmaMembrane c_3 "compartment_876";
        ExtracellularRegion c_4 "compartment_984";

    equation

        connect(c_4.species_1237009.n1, c_1.reaction_1237042.s1);
        connect(c_4.species_425425.n1, c_1.reaction_1237038.p1);
        connect(c_4.species_188972.n1, c_1.reaction_1237038.s2);
        connect(c_4.species_1237009.n1, c_1.reaction_1237069.s1);
        connect(c_4.species_188972.n1, c_1.reaction_1247665.p1);
        connect(c_4.species_425425.n1, c_1.reaction_1247665.s2);
        connect(c_4.species_1237009.n1, c_1.reaction_1247649.p1);
        connect(c_4.species_1237009.n1, c_1.reaction_1247645.p1);

    end Cell;

end BioSystem;
