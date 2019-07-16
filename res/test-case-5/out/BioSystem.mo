package BioSystem

    model Cell
        extends BioChem.Compartments.MainCompartment;

        model Cytosol
            extends BioChem.Compartments.Compartment;

            BioChem.Substances.Substance species_352022(n(start=1)) "Cl- [cytosol]";
            BioChem.Substances.Substance species_111627(n(start=1)) "HCO3- [cytosol]";
            BioChem.Substances.Substance species_113528(n(start=1)) "CO2 [cytosol]";
            BioChem.Substances.Substance species_1237312(n(start=1)) "Protonated Carbamino DeoxyHbA [cytosol]";
            BioChem.Substances.Substance species_29368(n(start=1)) "O2 [cytosol]";
            BioChem.Substances.Substance species_1237320(n(start=1)) "OxyHbA [cytosol]";
            BioChem.Substances.Substance species_70106(n(start=1)) "H+ [cytosol]";
            BioChem.Substances.BoundarySubstance species_1475437(n(start=1)) "CA1,2 [cytosol]";
            BioChem.Substances.Substance species_29356(n(start=1)) "H2O [cytosol]";

            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbifa reaction_1247665(k1=1) "Band 3 Anion Exchanger (AE1, SLC4A1) exchanges cytosolic chloride for extracellular bicarbonate";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uuifa reaction_1247649(k1=1) "AQP1 passively translocates carbon dioxide from the cytosol to the extracellular region";
            BioChem.Reactions.MassAction.Irreversible.BiTri.Bti reaction_1247668(k1=1) "Hemoglobin A binds oxygen and releases protons and carbon dioxide";
            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbifa reaction_1475436(k1=1) "Carbonic anhydrase I/II dehydrates bicarbonate";
            BioChem.Reactions.MassAction.Irreversible.UniUni.Uuifa reaction_1247645(k1=1) "RhAG passively translocates carbon dioxide from the cytosol to the extracellular region";

        equation

            connect(species_352022.n1, reaction_1247665.s2);
            connect(species_111627.n1, reaction_1247665.p2);

            connect(species_113528.n1, reaction_1247649.s1);

            connect(species_1237312.n1, reaction_1247668.s1);
            connect(species_29368.n1, reaction_1247668.s2);
            connect(species_70106.n1, reaction_1247668.p1);
            connect(species_113528.n1, reaction_1247668.p2);
            connect(species_1237320.n1, reaction_1247668.p3);

            connect(species_70106.n1, reaction_1475436.s1);
            connect(species_111627.n1, reaction_1475436.s2);
            connect(species_113528.n1, reaction_1475436.p1);
            connect(species_29356.n1, reaction_1475436.p2);
            connect(species_1475437.n1, reaction_1475436.aF1);

            connect(species_113528.n1, reaction_1247645.s1);

        end Cytosol;

        model PlasmaMembrane
            extends BioChem.Compartments.Compartment;

            BioChem.Substances.BoundarySubstance species_1237308(n(start=1)) "CA4:Zn2+ [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_1244330(n(start=1)) "SLC4A1 dimer [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_432246(n(start=1)) "AQP1 tetramer [plasma membrane]";
            BioChem.Substances.BoundarySubstance species_444408(n(start=1)) "RHAG [plasma membrane]";

        end PlasmaMembrane;

        model ExtracellularRegion
            extends BioChem.Compartments.Compartment;

            BioChem.Substances.Substance species_425425(n(start=1)) "HCO3- [extracellular region]";
            BioChem.Substances.Substance species_351626(n(start=1)) "H+ [extracellular region]";
            BioChem.Substances.Substance species_1237009(n(start=1)) "CO2 [extracellular region]";
            BioChem.Substances.Substance species_109276(n(start=1)) "H2O [extracellular region]";
            BioChem.Substances.Substance species_188972(n(start=1)) "Cl- [extracellular region]";

            BioChem.Reactions.MassAction.Irreversible.BiBi.Bbifa reaction_1237059(k1=1) "Carbonic anhydrase IV dehydrates bicarbonate to water and carbon dioxide";

        equation

            connect(species_425425.n1, reaction_1237059.s1);
            connect(species_351626.n1, reaction_1237059.s2);
            connect(species_1237009.n1, reaction_1237059.p1);
            connect(species_109276.n1, reaction_1237059.p2);

        end ExtracellularRegion;

        Cytosol c_1 "compartment_70101";
        PlasmaMembrane c_2 "compartment_876";
        ExtracellularRegion c_3 "compartment_984";

    equation

    connect(c_2.species_1244330.n1, c_1.reaction_1247665.aF1);
    connect(c_3.species_425425.n1, c_1.reaction_1247665.s1);
    connect(c_3.species_188972.n1, c_1.reaction_1247665.p1);
    connect(c_2.species_432246.n1, c_1.reaction_1247649.aF1);
    connect(c_3.species_1237009.n1, c_1.reaction_1247649.p1);
    connect(c_3.species_1237009.n1, c_1.reaction_1247645.p1);
    connect(c_2.species_444408.n1, c_1.reaction_1247645.aF1);
    connect(c_2.species_1237308.n1, c_3.reaction_1237059.aF1);

    end Cell;

end BioSystem;
