import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        String[] argcopy = new String[3];
        System.arraycopy(args, 0, argcopy, 0, args.length);
        if(argcopy[2] == null) argcopy[2] = argcopy[1];

        HashSet<Compartment> comps;
        Parser P;

        try {
            P = new Parser(argcopy[0], argcopy[1]);
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

        BioSystem bs = new BioSystem(comps, sinks, sources);

        bs.markBoundaries();

        ModelBuilder3 mb = new ModelBuilder3(bs, argcopy[2]);
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
