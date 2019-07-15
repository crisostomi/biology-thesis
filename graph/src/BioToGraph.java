import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BioToGraph {

    /**
     * Method used to build a graph object from a set of compartments built from sbml data
     * @param compartments a set of compartments
     * @return a hyper-graph object with species as Nodes and reactions as hyper-edges
     * @see Graph
     */
    public static Graph buildGraph(HashSet<Compartment> compartments){
        HashSet<Node> nodes = new HashSet<>();
        HashSet<Edge> edges = new HashSet<>();
        for (Compartment compartment: compartments) {
            for (Species species: compartment.getSpecies()) {
                Node node = new Node(species);
                nodes.add(node);
            }

            for (SimpleReaction reaction: compartment.getReactions()) {
                List<Node> sources = new ArrayList<>();
                List<Node> destinations = new ArrayList<>();

                for (Species species : reaction.getReactants().keySet()) {
                    Node node = Graph.findNode(nodes, species);
                    if (node != null) {
                        node.setModifier(false);
                        sources.add(node);
                    }
                }

                for (Species species : reaction.getProducts().keySet()) {
                    Node node = Graph.findNode(nodes, species);
                    if (node != null) {
                        node.setModifier(false);
                        destinations.add(node);
                    }
                }
                Edge edge = new Edge(sources, destinations);
                edges.add(edge);
            }
        }
        return new Graph(nodes, edges);
    }

}
