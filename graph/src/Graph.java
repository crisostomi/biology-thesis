import java.util.HashSet;
import java.util.Set;

public class Graph {
    private HashSet<Node> nodes;
    private HashSet<Edge> edges;

    /**
     * Class representing the pathway as a hyper-graph, with species as nodes
     * and reactions as hyper-edges
     * @param nodes a set of Nodes
     * @param edges a set of Edges between collections of Nodes
     * @see Node
     * @see Edge
     */
    Graph(HashSet<Node> nodes, HashSet<Edge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    /**
     * Method to find the sinks of the graph, which represent species involved only as products
     * in the pathway, thus being only "produced" by it and possibly regarded as its output
     * @return a set of sinks Nodes
     */
    public HashSet<Node> findSinks() {
        HashSet<Node> sinks = new HashSet<>(Set.copyOf(this.getNodes()));
        HashSet<Node> notSinks = new HashSet<>();
        for(Node node: sinks){
            if (node.isModifier()) {
                notSinks.add(node);
                continue;
            }
            for(Edge edge: this.getEdges()){
                if (edge.getSources().contains(node)){
                    notSinks.add(node);
                    break;
                }
            }
        }
        sinks.removeAll(notSinks);
        return sinks;
    }

    /**
     * Method to find the sources of the graph, which represent species involved only as reactants
     * in the pathway, thus being only "consumed" by it and possibly regarded as its input
     * @return
     */
    public HashSet<Node> findSources() {
        HashSet<Node> sources = new HashSet<>(Set.copyOf(this.getNodes()));
        HashSet<Node> notSources = new HashSet<>();
        for(Node node: sources){
            if (node.isModifier()) {
                notSources.add(node);
                continue;
            }
            for(Edge edge: this.getEdges()){
                if (edge.getDestinations().contains(node)){
                    notSources.add(node);
                    break;
                }
            }
        }
        sources.removeAll(notSources);
        return sources;
    }

    /**
     * Method to find the Node that corresponds to a given Species
     * @param nodes a collection of Nodes in which to search
     * @param species a species for which a Node is to be found
     * @return a Node that corresponds to species, if it exists, otherwise null
     */
    static Node findNode(HashSet<Node> nodes, Species species){
        for(Node node: nodes){
            if ( node.getSpecies().equals(species) ){
                return node;
            }
        }
        return null;
    }

    /**
     * Method to find the links between compartments, that is transport reactions that move
     * species from a compartment to the other, making those species I/O for the compartments
     * @param comps a set of compartments
     * @return
     */
    HashSet<CompartmentEdge> findCompartmentEdges(HashSet<Compartment> comps) {
        HashSet<CompartmentEdge> compsEdges = new HashSet<>();

        for (Compartment comp: comps) {
            for (SimpleReaction reac: comp.getReactions()) {
                // ASSUMPTION: transport reactions only transport a species at a time
                if (reac.getReactants().keySet().size() != 1 ||
                        reac.getProducts().keySet().size() != 1) {

                    continue;
                }

                Species reactant = (Species)reac.getReactants().keySet().toArray()[0];
                Species product = (Species)reac.getProducts().keySet().toArray()[0];

                String srcCompId = reactant.getCompartmentId();
                String dstCompId = product.getCompartmentId();

                if (!srcCompId.equals(dstCompId)) {
                    // transport found

                    CompartmentEdge e = new CompartmentEdge(srcCompId, dstCompId, reac, reactant, product);
                    compsEdges.add(e);
                }
            }
        }

        return compsEdges;
    }

    HashSet<Node> getNodes(){
        return this.nodes;
    }

    HashSet<Edge> getEdges(){
        return this.edges;
    }

}
