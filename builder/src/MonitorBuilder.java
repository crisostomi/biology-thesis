import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MonitorBuilder {

    public static final double MINCONCENTRATION = 0;
    public static final double MAXCONCENTRATION = 10e-6;

    private BioSystem B;
    private String xmlPath;
    private String outDir;

    private static final String indentation = "    ";   // 4 spaces used for indentation

    /**
     * Class to build the Modelica monitor needed for the parameter sweep search
     * @param b the biosystem parsed and built from sbml data
     * @param xmlPath the path of th XML knowledge-base file
     * @param outDir the path of the output directory where to put the Modelica file
     */
    public MonitorBuilder(BioSystem b, String xmlPath, String outDir) {
        B = b;
        this.xmlPath = xmlPath;
        this.outDir = outDir;
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

        sb.append(sb.append(declareAll(depth+1)));

        sb.append("\nend Monitor;\n");

        bw.write(sb.toString());
        bw.close();
    }

    /**
     * Method to declare all the input and output variables of the Modelica monitor block
     * @param depth for indentation purposes
     * @return a block of Modelica code handling the declaration of variables
     */
    private StringBuilder declareAll(int depth) {
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

        sb.append(indent + "input Real " + species.getId() + "_amount;\n");

        return sb;
    }

    /**
     * Method to declare an output variable for a species, which carries information about the violation of that
     * species constraints
     * @param species the species of interest
     * @param depth for indentation purposes
     * @return a line of Modelica code handling the declaration of a species output variable
     */
    private StringBuilder declareOutput(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        sb.append(indent + "output Boolean " + species.getId() + "_monitor;\n");

        return sb;
    }

    /*
    TODO: add initial equation and equation blocks, need parsing of the KB xml file
     */
}
