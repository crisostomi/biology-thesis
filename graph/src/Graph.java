import java.util.HashSet;
import java.util.Set;

public class Graph {
    private HashSet<Node> nodes;
    private HashSet<Edge> edges;

    Graph(HashSet<Node> nodes, HashSet<Edge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

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
