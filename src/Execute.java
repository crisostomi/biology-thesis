import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

public class Execute {

    public static void run(String inputDir, String outputSBMLDir, String outputModelicaDir, String configDir) {
        System.out.println("Beginning execution...");
        HashSet<Compartment> comps;
        Parser P;

        // parse input sbml
        try {
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

        /*
        create a graph structure in order to find species that only get consumed or produced
        by the system reactions
         */

        Graph g = BioToGraph.buildGraph(comps);
        HashSet<Species> sinks = new HashSet<>();
        for(Node node: g.findSinks()){
            sinks.add(node.getSpecies());
        }
        HashSet<Species> sources = new HashSet<>();
        for(Node node: g.findSources()){
            sources.add(node.getSpecies());
        }

        // instantiate the Java model
        BioSystem bs = new BioSystem(comps, sinks, sources);

        //update config.xml file
        try {
            ConstraintBuilder cb = new ConstraintBuilder(bs, configDir);
            cb.build();
        } catch(Exception e){
            System.out.println("Failed to update config.xml - is "+configDir+"/config.xml a legal configuration file?");
        }

        // convert Java biosystem model in Modelica
        ModelBuilder mb = new ModelBuilder(bs, outputModelicaDir, configDir);
        try {
            mb.buildBioSystem();
        } catch(IOException e){
            System.out.println("Modelica files creation/writing failed");
        }

        MonitorBuilder monb = new MonitorBuilder(bs, configDir, outputModelicaDir);
        try {
            monb.build();
        } catch (IOException e) {
            System.out.println("Modelica monitor creation/writing failed");
            e.printStackTrace();
        }

        System.out.println("All done!");
    }

}
