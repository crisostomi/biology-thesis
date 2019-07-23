import javax.xml.parsers.ParserConfigurationException;
import java.util.HashSet;

public class Configure {
    public static void run(String inputDir, String outputSBMLDir, String configXMLfile) {
        /*
        0. input folder with sbml files
        1. output folder to write sbml_union
        2. input config file
        */

        System.out.println("Beginning configuration...");

        HashSet<Compartment> comps;
        Parser P;

        // parse input sbml
        try { //TODO: don't generate sbml union in this Configure
            P = new Parser(inputDir, outputSBMLDir);
        } catch(ParserConfigurationException e){
            System.out.println("Parsing fail due to SBMLBuilder instantiation failure");
            return;
        }

        // convert sbml data into a collection of Compartment objects
        comps = P.instantiateCompartments();
        if (comps == null || comps.size() == 0) {
            System.out.println("Compartment instantiation failed. Returning with error");
            return;
        }

        // instantiate the Java model
        BioSystem bs = new BioSystem(comps, null, null);

        try {
            ConstraintBuilder cb = new ConstraintBuilder(bs, configXMLfile);
            cb.build();
        } catch (Exception e) {
            System.out.println("Constraints XML creation/writing failed");
            e.printStackTrace();
        }

        System.out.println("All done!");
    }
}
