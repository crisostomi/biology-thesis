import java.util.List;

class Edge {
    private List<Node> sources;
    private List<Node> destinations;

    List<Node> getSources() {
        return sources;
    }

    List<Node> getDestinations() {
        return destinations;
    }

    Edge(List<Node> sources, List<Node> destinations){
        this.sources = sources;
        this.destinations = destinations;
    }

}
