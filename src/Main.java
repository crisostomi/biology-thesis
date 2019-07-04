import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        HashSet<Compartment> comps;
        Parser P;

        String test = "res/test-case-3";
        String inDir = test + "/in";
        String outDir = test + "/out";

        try {
            P = new Parser(inDir, outDir);
        } catch(ParserConfigurationException e){
            System.out.println("Parsing fail due to SBMLBuilder instantiation failure");
            return;
        }
        comps = P.instantiateCompartments(); //convert sbml data in a Java biosystem model
        if (comps == null || comps.size() == 0) {
            System.out.println("Compartment instantiation failed. Returning with error");
            return;
        }

        Biosystem B = new Biosystem(comps);
        Graph g = BioToGraph.buildGraph(B);
        HashSet<Node> sources = g.getSources();
        HashSet<Node> sinks = g.getSinks();

        ModelBuilder mb = new ModelBuilder(B, outDir, sinks, sources);
        try {
            mb.buildBiosystem(); //convert Java biosystem model in Modelica
        }catch(IOException e){
            System.out.println("Modelica files creation/writing failed");
        }

        System.out.println("All done!");

        /*
        TODO: refactor the two builders into one builder

        TODO: define input values for each block in biosystem.mo

        TODO: create Modelica monitors and .csv configuration file
        -> .xml + simple interface possibly better (uniform technologies), ask Tronci

        data flow: Reactome SBML -> (Single SBML,  config) -> (Modelica blocks, monitors) -> Search output
         */

    }
}
