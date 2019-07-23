import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConstraintBuilder {

    private BioSystem B;
    private String outDir;

    /**
     * Class to build an XML carrying biological constraints information
     * @param b the biosystem built from sbml data
     * @param outDir the output directory where to put the built XML
     */
    public ConstraintBuilder(BioSystem b, String outDir) {
        B = b;
        this.outDir = outDir;
    }

    /**
     * Method to build the XML
     * @throws IOException
     */
    public void build() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.outDir + "/constraints.xml"));
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\'1.0\' encoding=\'utf-8\'?>\n");
        sb.append("<listOfConstraints>\n");

        for (Compartment compartment: B.getCompartments()) {
            for (Species species: compartment.getSpecies()) {
                sb.append(buildSpeciesConstraint(species));
            }
        }

        sb.append("</listOfConstraints>\n");

        bw.write(sb.toString());
        bw.close();
    }

    /**
     * Method to build a constraint for a single species, initially all blank
     * @param species the species of interest
     * @return a line of XML with the definition of the constraint for the species
     */
    private StringBuilder buildSpeciesConstraint(Species species) {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<constraint species=\""+species.getName()+ "\" id=\""+species.getId()+ "\" " +
                "initialAmount=\"1\" minAmount=\"0\" maxAmount=\"10\">");
        sb.append("</constraint>\n");

        return sb;
    }
}
