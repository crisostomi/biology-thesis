import java.util.HashSet;

public class Biosystem {
    private HashSet<Species> sinks;
    private HashSet<Species> sources;
    private HashSet<Compartment> compartments;

    public Biosystem(HashSet<Compartment> set, HashSet<Species> sinks, HashSet<Species> sources){
        this.compartments = set;
        this.sinks = sinks;
        this.sources = sources;
    }

    public HashSet<Compartment> getCompartments(){ return this.compartments; }

    public HashSet<Species> getSinks() {
        return sinks;
    }

    public HashSet<Species> getSources() {
        return sources;
    }
}
