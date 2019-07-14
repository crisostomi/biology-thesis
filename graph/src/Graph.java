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
    public HashSet<Node> getSinks() {
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
    public HashSet<Node> getSources() {
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

    HashSet<Node> getNodes(){
        return this.nodes;
    }

    HashSet<Edge> getEdges(){
        return this.edges;
    }

}
