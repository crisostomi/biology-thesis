import java.util.*;

public class Compartment {

    private String name;
    private String id;

    private ArrayList<SimpleReaction> reactions;    // the list of reactions
    private ArrayList<Species> species;

    /**
     * Class that represents a compartment in the cell, e.g. Cytosol, Mythocondria ecc...
     * @param name the name of the compartment
     * @param id the id of the compartment
     */
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

    /**
     * Method to look for a particular species by id in the compartment
     * @param id the id of the species to look for
     * @return the species with the required id in the compartment, if present, otherwise null
     */
    public Species searchSpecies(String id){
        for(Species s : this.species){
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    /**
     * Method to look for a particular reaction by id in the compartment
     * @param id the id of the reaction to look for
     * @return the reaction with the required id in the compartment, if present, otherwise null
     */
    public SimpleReaction searchReaction(String id){
        for(SimpleReaction r : this.reactions){
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    /**
     * Method to find a compartment by id
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

    public int getNumReversibleReactions(){
        int res = 0;
        for(SimpleReaction r : this.getReactions()){
            if (r.isReversible()) res++;
        }
        return res;
    }

    public ArrayList<Species> getSpecies(){ return this.species; }

    public ArrayList<SimpleReaction> getReactions(){ return this.reactions; }

    /**
     * Method to pretty-print the contents of the compartment for debugging purposes
     * @see SimpleReaction#printReaction()
     */
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
