import java.util.ArrayList;

class Edge {
    private ArrayList<Node> sources;
    private ArrayList<Node> destinations;

    /**
     * Class that represents a hyper-edge in a graph
     * In models a reaction in the biosystem graph
     * @param sources the list of source nodes
     * @param destinations the list of destination nodes
     * @see Node
     */
    Edge(ArrayList<Node> sources, ArrayList<Node> destinations){
        this.sources = sources;
        this.destinations = destinations;
    }

    ArrayList<Node> getSources() {
        return sources;
    }

    ArrayList<Node> getDestinations() {
        return destinations;
    }

}
