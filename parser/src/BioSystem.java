import java.util.ArrayList;
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
        ArrayList<SimpleReaction> all_reacts = new ArrayList<>();
        ArrayList<Species> all_spec = new ArrayList<>();
        for(Compartment c : compartments) all_reacts.addAll(c.getReactions());
        for(Compartment c : compartments) all_spec.addAll(c.getSpecies());

        for(Species s : all_spec){
            mark = true;
            for(SimpleReaction r : all_reacts){
                if (r.getReactants().containsKey(s) || r.getProducts().containsKey(s)){
                    mark = false;
                    break;
                }
            }
            if(mark) s.setBoundary(true);
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
