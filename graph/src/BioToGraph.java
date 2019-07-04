import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BioToGraph {

    public static Graph buildGraph(Biosystem biosystem){
        HashSet<Node> nodes = new HashSet<>();
        HashSet<Edge> edges = new HashSet<>();
        for (Compartment compartment: biosystem.getCompartments()) {
            for (Species species: compartment.getSpecies()) {
                Node node = new Node(species.getId(), compartment.getId(), species.getName());
                nodes.add(node);
            }

            for (Reaction reaction: compartment.getReactions()) {
                List<Node> sources = new ArrayList<>();
                List<Node> destinations = new ArrayList<>();

                for (Species species : reaction.getReactants().keySet()) {
                    Node node = Graph.findNode(nodes, species.getId(), compartment.getId());
                    if (node != null) {
                        node.setModifier(false);
                        sources.add(node);
                    }
                }

                for (Species species : reaction.getProducts().keySet()) {
                    Node node = Graph.findNode(nodes, species.getId(), compartment.getId());
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
