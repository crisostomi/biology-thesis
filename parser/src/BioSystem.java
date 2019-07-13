import java.util.HashSet;

public class BioSystem {

    private HashSet<Species> sinks;
    private HashSet<Species> sources;
    private HashSet<Compartment> compartments;

    public BioSystem(HashSet<Compartment> set, HashSet<Species> sinks, HashSet<Species> sources){
        this.compartments = set;
        this.sinks = sinks;
        this.sources = sources;
    }

    public void markBoundaries(){

        boolean mark;
        for(Compartment c : compartments){
            for(Species s : c.getSpecies()){
                mark = true;
                for(SimpleReaction r : c.getReactions()){
                    if (r.getReactants().containsKey(s) || r.getProducts().containsKey(s)){
                        mark = false;
                        break;
                    }
                }
                if(mark) s.setBoundary(true);
            }
        }
    }

    public HashSet<Compartment> getCompartments(){ return this.compartments; }

    public HashSet<Species> getSinks() {
        return sinks;
    }

    public HashSet<Species> getSources() {
        return sources;
    }
}
