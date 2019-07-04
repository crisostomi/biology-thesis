import java.util.List;

public class Edge {
    private List<Node> sources;
    private List<Node> destinations;

    public List<Node> getSources() {
        return sources;
    }

    public List<Node> getDestinations() {
        return destinations;
    }

    public Edge(List<Node> sources, List<Node> destinations){
        this.sources = sources;
        this.destinations = destinations;
    }


}
