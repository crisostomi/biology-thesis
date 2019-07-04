import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        HashSet<Compartment> comps;
        Parser P;

        String test = "res/test-case-2";
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

        Graph g = BioToGraph.buildGraph(comps);
        HashSet<Species> sinks = new HashSet<>();
        for(Node node: g.getSinks()){
            sinks.add(node.getSpecies());
        }
        HashSet<Species> sources = new HashSet<>();
        for(Node node: g.getSources()){
            sources.add(node.getSpecies());
        }

        Biosystem bs = new Biosystem(comps, sinks, sources);

        ModelBuilder mb = new ModelBuilder(bs, outDir);
        try {
            mb.buildBiosystem(); //convert Java biosystem model in Modelica
        }catch(IOException e){
            System.out.println("Modelica files creation/writing failed");
        }

        System.out.println("All done!");

        /*

        TODO: define input values for each block in biosystem.mo

        TODO: create Modelica monitors and .csv configuration file
        -> .xml + simple interface possibly better (uniform technologies), ask Tronci

        data flow: Reactome SBML -> (Single SBML,  config) -> (Modelica blocks, monitors) -> Search output
         */

    }
}
