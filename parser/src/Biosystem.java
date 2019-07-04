import java.util.HashSet;

public class Biosystem {

    private HashSet<Compartment> compartments;

    public Biosystem(HashSet<Compartment> set){
        this.compartments = set;
    }

    public HashSet<Compartment> getCompartments(){ return this.compartments; }
}
