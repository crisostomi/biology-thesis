import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashSet;

public class MonitorBuilder {

    public static final double MINAMOUNT = 0;
    public static final double MAXAMOUNT = 10e-6;

    private BioSystem B;
    private String xmlDir;
    private String outDir;

    private static final String indentation = "    ";   // 4 spaces used for indentation

    /**
     * Class to build the Modelica monitor needed for the parameter sweep search
     * @param b the biosystem parsed and built from sbml data
     * @param xmlDir the path of th XML knowledge-base file
     * @param outDir the path of the output directory where to put the Modelica file
     */
    public MonitorBuilder(BioSystem b, String xmlDir, String outDir) {
        B = b;
        this.xmlDir = xmlDir;
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

        sb.append(buildParametersBlock(depth+1));
        sb.append(buildDeclarationBlock(depth+1) + "\n\n");

        sb.append(buildInitialEquationBlock(depth+1) + "\n\n");

        sb.append(buildEquationBlock(depth+1) + "\n\n");

        sb.append(buildAlgorithmBlock(depth+1) + "\n\n");

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

            for (Species species: compartment.getSpecies()) {
                sb.append(declareAuxiliary(species, depth));
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
     * Method to declare an output boolean variable for a species, which carries information about the
     * violation of that species constraints
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

    /**
     * Method to declare an auxiliary boolean variable for a species, used in the algorithm block
     * @param species the species of interest
     * @param depth used for indentation purposes
     * @return
     */
    private StringBuilder declareAuxiliary(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        sb.append(indent + "Boolean " + species.getId() + "_aux;\n");

        return sb;

    }

    private class Constraint {

        private String speciesId;
        private double minAmount;
        private double maxAmount;

        public Constraint(String speciesId, double minAmount, double maxAmount) {
            this.speciesId = speciesId;
            this.minAmount = minAmount;
            this.maxAmount = maxAmount;
        }

        public String getSpeciesId() {
            return speciesId;
        }

        public double getMinAmount() {
            return minAmount;
        }

        public double getMaxAmount() {
            return maxAmount;
        }
    }

    private HashSet<Constraint> parseXml() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuild = dbf.newDocumentBuilder();

        File xml = new File(this.xmlDir + "/config.xml");
        Document doc = docBuild.parse(xml);
        NodeList elements = doc.getElementsByTagName("constraint");

        HashSet<Constraint> constraints = new HashSet<>();
        for (int i = 0; i < elements.getLength(); i++) { //TODO: this one just works for Species - missing Reactions
            NamedNodeMap attr = elements.item(i).getAttributes();
            String id = attr.getNamedItem("id").getNodeValue();
            String min = attr.getNamedItem("minAmount").getNodeValue();
            String max = attr.getNamedItem("maxAmount").getNodeValue();
            double minAmount, maxAmount;
            if(min.equals("")) minAmount = MINAMOUNT;
            else minAmount = Double.valueOf(min);
            if(max.equals("")) maxAmount = MAXAMOUNT;
            else maxAmount = Double.valueOf(max);

            constraints.add(new Constraint(id, minAmount, maxAmount));
        }

        return constraints;
    }

    /**
     * Method to declare all the parameters carrying information about constraints
     * @param depth used for indentation purposes
     * @return a block of Modelica code handling the declaration of parameters
     */
    private StringBuilder buildParametersBlock(int depth) {

        try {
            HashSet<Constraint> constraints = parseXml();

            StringBuilder sb = new StringBuilder();

            for (Constraint constraint: constraints) {
                sb.append(buildParameter(constraint, depth));
            }

            return sb;
        } catch (Exception e) {
            System.out.println("Parsing of biological knowledge base failed.");
            e.printStackTrace();
            return null;
        }
    }

    private StringBuilder buildParameter(Constraint constraint, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        String speciesId = constraint.getSpeciesId();
        double minAmount = constraint.getMinAmount();
        double maxAmount = constraint.getMaxAmount();

        sb.append(indent + "parameter Real " + speciesId + "_minAmount = " + minAmount +";\n");
        sb.append(indent + "parameter Real " + speciesId + "_maxAmount = " + maxAmount +";\n");

        return sb;

    }

    /**
     * Method to build the initial equation block of the monitor in Modelica
     * @param depth used for indentation purposes
     * @return the Modelica initial equation block
     */
    private StringBuilder buildInitialEquationBlock(int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();
        sb.append(indent + "initial equation\n");

        for (Compartment compartment: B.getCompartments()) {
            sb.append("\n" + indent + "    // " + compartment.getId() + "\n");

            for (Species species: compartment.getSpecies()) {
                sb.append(buildInitialOutputEquation(species, depth+1));
            }

            for (Species species: compartment.getSpecies()) {
                sb.append(buildInitialAuxEquation(species, depth+1));
            }

        }

        return sb;
    }

    private StringBuilder buildInitialOutputEquation(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        sb.append(indent + species.getId() + "_monitor = false;\n");

        return sb;
    }

    private StringBuilder buildInitialAuxEquation(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        sb.append(indent + species.getId() + "_aux = false;\n");
        sb.append(indent + "pre(" + species.getId() + "_aux) = false;\n");

        return sb;
    }

    /**
     * Method to build the equation block of the monitor in Modelica, by parsing the KB
     * @param depth used for indentation purposes
     * @return
     */
    private StringBuilder buildEquationBlock(int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();
        sb.append(indent + "equation\n");

        for (Compartment compartment: B.getCompartments()) {
            sb.append("\n" + indent + "    // " + compartment.getId() + "\n");

            for (Species species: compartment.getSpecies()) {
                sb.append(buildEquationRange(species, depth+1));
            }

        }

        return sb;
    }

    /**
     * Method to build the equations to check whether the amount of a species is inside its range
     * @param species the species of interest
     * @param depth used for indentation purposes
     * @return a line of Modelica code handling the auxiliary variable for the species
     */
    private StringBuilder buildEquationRange(Species species, int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        String aux = species.getId() + "_aux";
        String amount = species.getId() + "_amount";
        String minAmount = species.getId() + "_minAmount";
        String maxAmount = species.getId() + "_maxAmount";

        sb.append(indent + aux + " = (" + amount + " >= " + maxAmount + ") or " +
                "(" + amount + " <= " + minAmount + ");\n");

        return sb;

    }

    /**
     * Method to build the algorithm block for the Modelica monitor, that properly updates the output variables
     * for the species when their amount gets outside of the intended range
     * @param depth used for indentation purposes
     * @return the algorithm block for the Modelica monitor
     */
    private StringBuilder buildAlgorithmBlock(int depth) {
        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();
        sb.append(indent + "algorithm\n");

        for (Compartment compartment: B.getCompartments()) {
            sb.append("\n" + indent + "    // " + compartment.getId() + "\n");

            for (Species species: compartment.getSpecies()) {
                sb.append(buildSpeciesAlgorithm(species, depth+1));
            }

        }

        return sb;

    }

    /**
     * Method to build the Modelica code to handle the monitor variable for a single species
     * @param species the species of interest
     * @param depth used for indentation purposes
     * @return a block of Modelica code handling the update of the output variable of a single species
     */
    private StringBuilder buildSpeciesAlgorithm(Species species, int depth) {

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        String aux = species.getId() + "_aux";
        String mon = species.getId() + "_monitor";

        sb.append(indent + "when edge(" + aux + ") then \n");
        sb.append(indent + "    " + mon + " := true;\n");
        sb.append(indent + "end when;\n");

        return sb;
    }

}
