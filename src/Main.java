import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        /*
        0. input folder
        1. output folder
        */
        String[] argcopy = new String[3];
        System.arraycopy(args, 0, argcopy, 0, args.length);
        if(argcopy[2] == null) argcopy[2] = argcopy[1];

        HashSet<Compartment> comps;
        Parser P;

        // parse input sbml
        try {
            P = new Parser(argcopy[0], argcopy[1]);
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

        /*
        create a graph structure in order to find species that only get consumed or produced
        by the system reactions
         */
        Graph g = BioToGraph.buildGraph(comps);
        HashSet<Species> sinks = new HashSet<>();
        for(Node node: g.getSinks()){
            sinks.add(node.getSpecies());
        }
        HashSet<Species> sources = new HashSet<>();
        for(Node node: g.getSources()){
            sources.add(node.getSpecies());
        }

        // instantiate the Java model
        BioSystem bs = new BioSystem(comps, sinks, sources);

        /*
         detect species that don't get neither consumed nor produced by any reaction in the system
         might be done in the constructor (?)
         */
        bs.markBoundaries();

        // convert Java biosystem model in Modelica
        ModelBuilder3 mb = new ModelBuilder3(bs, argcopy[2]);
        try {
            mb.buildBioSystem();
        }catch(IOException e){
            System.out.println("Modelica files creation/writing failed");
        }

        System.out.println("All done!");

        /*

        TODO: handle transport reactions
        TODO: handle cell volume

         */

    }
}
