import java.io.*;
import java.util.HashMap;

public class MonitorBuilder {

    private BioSystem B;
    private String outDir;
    private HashMap<String, Integer> speciesIndex;

    private static final String indentation = "    ";   // 4 spaces used for indentation

    /**
     * Class to build the Modelica monitor needed for the parameter sweep search
     * @param b the biosystem parsed and built from sbml data
     * @param outDir the path of the output directory where to put the Modelica file
     */
    public MonitorBuilder(BioSystem b, String outDir, HashMap<String, Integer> speciesIndex) {
        B = b;
        this.outDir = outDir;
        this.speciesIndex = speciesIndex;
    }

    /**
     * Main method of the class
     * @throws IOException
     */
    public void build() throws IOException {
        int depth = 0;

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.outDir + "/Monitor.mo"));
        StringBuilder sb = new StringBuilder();

        sb.append("block Monitor\n\n");

        sb.append(buildParametersBlock(depth+1));
        sb.append(buildDeclarationBlock(depth+1) + "\n\n");

        sb.append(buildEquationBlock(depth+1) + "\n\n");

        sb.append("end Monitor;\n");

        bw.write(sb.toString());
        bw.close();
    }

    /**
     * Method to declare all the input and output variables of the Modelica monitor block
     * @param depth for indentation purposes
     * @return a block of Modelica code handling the declaration of variables
     */
    private StringBuilder buildDeclarationBlock(int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        for (Compartment compartment: B.getCompartments()) {
            sb.append("\n" + indent + "// " + compartment.getId() + "\n");

            for (Species species: compartment.getSpecies()) {
                sb.append(declareInput(species, depth));
            }

            for (Species species: compartment.getSpecies()) {
                sb.append(declareOutput(species, depth));
            }

        }

        return sb;
    }

    /**
     * Method to declare an input variable for a species, which carries information about its abundance in the model
     * @param species the species of interest
     * @param depth for indentation purposes
     * @return a line of Modelica code handling the declaration of a species input variable
     */
    private StringBuilder declareInput(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        sb.append(indent + "input BioChem.Units.AmountOfSubstance " + species.getId() + "_amount;\n");

        return sb;
    }

    /**
     * Method to declare an output boolean variable for a species, which carries information about the
     * violation of that species constraints
     * @param species the species of interest
     * @param depth for indentation purposes
     * @return a line of Modelica code handling the declaration of a species output variable
     */
    private StringBuilder declareOutput(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        sb.append(indent + "output Real " + species.getId() + "_monitor;\n");

        return sb;
    }

    /**
     * Method to declare parameter arrays carrying information about constraints
     * @param depth used for indentation purposes
     * @return a block of Modelica code handling the declaration of parameters
     */
    private StringBuilder buildParametersBlock(int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        sb.append(indent.concat("parameter BioChem.Units.AmountOfSubstance ").
                concat("maxAmount").concat("[").concat(String.valueOf(this.B.countSpecies())).concat("];\n"));

        sb.append(indent.concat("parameter BioChem.Units.AmountOfSubstance ").
                concat("minAmount").concat("[").concat(String.valueOf(this.B.countSpecies())).concat("];\n"));

        return sb;
    }


    /**
     * Method to build the equation block for all the species constraints
     * @param depth used for indentation purposes
     * @return a block of Modelica code handling the calculation of all the fitness functions
     */
    private StringBuilder buildEquationBlock(int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();
        sb.append(indent + "equation\n");

        for (Compartment compartment: B.getCompartments()) {
            sb.append("\n" + indent + "    // " + compartment.getId() + "\n");

            for (Species species: compartment.getSpecies()) {
                sb.append(buildSpeciesEquation(species, depth+1));
            }
        }

        return sb;
    }

    /**
     * Method to build the equation for calculating the fitness function of a species
     * @param species the species of interest
     * @param depth used for indentation purposes
     * @return a line of Modelica code handling the calculation of the fitness function of a species
     */
    private StringBuilder buildSpeciesEquation(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        String x = species.getId() + "_amount";
        String b = "minAmount["+this.speciesIndex.get(species.getId())+"]";
        String a = "maxAmount["+this.speciesIndex.get(species.getId())+"]";

        String z = species.getId() + "_monitor";

        sb.append(indent + "der("+ z +") = (("+ x +" - ("+ b +"+"+ a +")/2) / 2)^2;\n");

        return sb;

    }

    /**
     * Method to declare the Monitor inside main Modelica class (BioSystem.mo)
     * @param indent used for indentation purposes
     * @return the declaration of the Monitor in Modelica
     */
    public static StringBuilder declareMonitor(String indent) {
        return new StringBuilder(indent + "Monitor mon;\n");
    }

    /**
     * Method to link all the species in the biosystem with the corresponding input variables in the monitor
     * @param B the biosystem
     * @param compNumberMap the map containing information about the name of the compartment in B
     * @param indent used for indentation purposes
     * @return a block of Modelica code handling the linking of the variables of the monitor
     */
    public static StringBuilder linkMonitor(BioSystem B, HashMap<String, Integer> compNumberMap, String indent) {
        // for every species in the biosystem
        // in the monitor there are variables species_amount that need to be linked to species.n
        StringBuilder sb = new StringBuilder();

        for (Compartment c: B.getCompartments()) {
            for (Species s: c.getSpecies()) {
                String speciesId = s.getId();
                Integer compNumber = compNumberMap.get(c.getId());
                String compName = "c_" + compNumber;
                sb.append(indent + "mon." + speciesId + "_amount = " + compName + "." + speciesId + ".n;\n");
            }
        }

        return sb;
    }

}