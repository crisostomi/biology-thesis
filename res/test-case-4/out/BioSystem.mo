package BioSystem

    model Cell
        extends BioChem.Compartments.MainCompartment(V(start=cell_V));

        inner parameter BioChem.Units.Volume cell_V = 1.0E-11;

        model Cytosol
            extends BioChem.Compartments.Compartment(V(start=0.1*cell_V));

            outer parameter BioChem.Units.Volume cell_V;

            BioChem.Substances.BoundarySubstance species_70356(n(start=9.807561901301316E-13)) "GALT";
            BioChem.Substances.Substance species_29370(n(start=9.897231252459072E-13)) "ADP";
            BioChem.Substances.Substance species_29590(n(start=9.843999689863656E-13)) "Gal";
            BioChem.Substances.Substance species_30170(n(start=9.96710860933125E-13)) "Gal1P";
            BioChem.Substances.Substance species_29410(n(start=1.0032480891953154E-12)) "UDP-Glc";
            BioChem.Substances.BoundarySubstance species_453132(n(start=9.970688471033182E-13)) "PGM:Mg2+";
            BioChem.Substances.Substance species_113592(n(start=9.925511593611183E-13)) "ATP";
            BioChem.Substances.Substance species_29548(n(start=9.948573749537911E-13)) "G1P";
            BioChem.Substances.BoundarySubstance species_70363(n(start=1.0052794086746003E-12)) "GALE:NAD+ dimer";
            BioChem.Substances.Substance species_30537(n(start=1.0005090286223984E-12)) "G6P";
            BioChem.Substances.BoundarySubstance species_70353(n(start=1.0085553094298852E-12)) "GALK1";
            BioChem.Substances.Substance species_29452(n(start=1.0086134308325384E-12)) "UDP-Gal";

            //WARNING: could not infer reaction type of reaction_70361
            //WARNING: could not infer reaction type of reaction_70427
            //WARNING: could not infer reaction type of reaction_70369
            //WARNING: could not infer reaction type of reaction_70355

        equation

        end Cytosol;

        Cytosol c_1 "compartment_70101";

    end Cell;

end BioSystem;
