import java.util.*;

public class Compartment {

    private String name;
    private String id;

    private ArrayList<SimpleReaction> reactions;
    private ArrayList<Species> species;

    public Compartment(String name, String id){
        this.name = name;
        this.id = id;
        this.species = new ArrayList<>();
        this.reactions = new ArrayList<>();
    }

    public String getId(){ return this.id; }

    public String getName(){ return this.name; }

    public void addSpecies(Species s){ this.species.add(s); }

    public void addReaction(SimpleReaction r){ this.reactions.add(r); }

    public Species searchSpecies(String id){
        for(Species s : this.species){
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    public SimpleReaction searchReaction(String id){
        for(SimpleReaction r : this.reactions){
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    /**
     * Method to find a compartment by ID
     * @param comps a HashSet of Compartments
     * @param compId the ID of the compartment to look for
     * @return
     */
    public static Compartment getCompartmentById(HashSet<Compartment> comps, String compId) {
        for (Compartment comp: comps) {
            if (comp.getId().equals(compId)) {
                return comp;
            }
        }

        return null;
    }

    public int getNumSpecies(){ return this.species.size(); }

    public int getNumReactions(){ return this.reactions.size(); }

    public ArrayList<Species> getSpecies(){ return this.species; }

    public ArrayList<SimpleReaction> getReactions(){ return this.reactions; }

    public void printCompartment(){
        System.out.println("Compartment: "+this.id);
        System.out.println("-Name: "+this.name);
        System.out.println("-Species:");
        for(Species s : this.species) {
            s.printSpecies();
            System.out.print("\n");
        }
        System.out.println("-Reactions:");
        for(SimpleReaction r : this.reactions) {
            r.printReaction();
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compartment that = (Compartment) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
