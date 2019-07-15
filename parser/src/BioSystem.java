import java.util.ArrayList;
import java.util.HashSet;

public class BioSystem {

    private HashSet<Species> sinks;
    private HashSet<Species> sources;
    private HashSet<Compartment> compartments;
    private HashSet<CompartmentEdge> compEdges;

    public BioSystem(HashSet<Compartment> set, HashSet<Species> sinks, HashSet<Species> sources){
        this.compartments = set;
        this.sinks = sinks;
        this.sources = sources;
        this.markBoundaries();
        this.compEdges = this.findCompartmentEdges();
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

    /**
     * Method to find the links between compartments, that is transport reactions that move
     * species from a compartment to the other, making those species I/O for the compartments
     * @return
     */
    public HashSet<CompartmentEdge> findCompartmentEdges() {

        HashSet<CompartmentEdge> compsEdges = new HashSet<>();
        String comp_id;
        for (Compartment comp: compartments) {
            for (SimpleReaction reac: comp.getReactions()) {

                comp_id = comp.getId();

                for(Species s : reac.getReactants().keySet()){
                    CompartmentEdge ce_r;
                    if(!s.getCompartmentId().equals(comp_id)){
                        ce_r = new CompartmentEdge(comp_id, s.getCompartmentId(), reac);
                        ce_r.setExternalReactant(s);
                        compsEdges.add(ce_r);
                    }
                }

                for(Species s : reac.getProducts().keySet()){
                    CompartmentEdge ce_p;
                    if(!s.getCompartmentId().equals(comp_id)){
                        ce_p = new CompartmentEdge(comp_id, s.getCompartmentId(), reac);
                        ce_p.setExternalProduct(s);
                        compsEdges.add(ce_p);
                    }
                }

                if(reac instanceof ComplexReaction){
                    for(Species s : ((ComplexReaction) reac).getModifiers()){
                        CompartmentEdge ce_m;
                        if(!s.getCompartmentId().equals(comp_id)){
                            ce_m = new CompartmentEdge(comp_id, s.getCompartmentId(), reac);
                            ce_m.setExternalModifier(s);
                            compsEdges.add(ce_m);
                        }
                    }
                }
            }
        }

        return compsEdges;
    }

    public HashSet<Compartment> getCompartments(){ return this.compartments; }

    public HashSet<Species> getSinks() {
        return sinks;
    }

    public HashSet<Species> getSources() {
        return sources;
    }

    public HashSet<CompartmentEdge> getCompEdges() {
        return compEdges;
    }

    public void setCompEdges(HashSet<CompartmentEdge> compEdges) {
        this.compEdges = compEdges;
    }
}