import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        /** importiamo un modello composto da compartments, reazioni e specie. Si puo' rappresentare come un iper-grafo diretto in cui i nodi sono le specie e gli iper-archi sono reazioni
         * quello che ci interessa sono le specie che compaiono sempre come prodotti in reazioni ma non compaiono come reagenti (nodi pozzo)
         * e le specie che compaiono sempre come reagenti e mai come prodotti (nodi sorgente)
         */
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

        ModelBuilder mb = new ModelBuilder(B, outDir);
        try {
            mb.buildBiosystem(); //convert Java biosystem model in Modelica
        }catch(IOException e){
            System.out.println("Modelica files creation/writing failed");
        }

        EnvModelBuilder emb = new EnvModelBuilder(sinks, sources, outDir);
        try {
            emb.buildModelicaModel(); //convert Java biosystem model in Modelica
        }catch(IOException e){
            System.out.println("Modelica environment creation/writing failed");
        }

        System.out.println("All done!");

        /*
        TODO: sort out module structure

        TODO: define input values for each block in biosystem.mo

        TODO: create Modelica monitors and .csv configuration file
        -> .xml + simple interface possibly better (uniform technologies), ask Tronci

        data flow: Reactome SBML -> (Single SBML,  config) -> (Modelica blocks, monitors) -> Search output
         */

    }
}
