import java.util.ArrayList;

public class Compartment {

    private String name;
    private String id;

    private ArrayList<Reaction> reactions;
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

    public void addReaction(Reaction r){ this.reactions.add(r); }

    public Species searchSpecies(String id){
        for(Species s : this.species){
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    public Reaction searchReaction(String id){
        for(Reaction r : this.reactions){
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    public int getNumSpecies(){ return this.species.size(); }

    public int getNumReactions(){ return this.reactions.size(); }

    public ArrayList<Species> getSpecies(){ return this.species; }

    public ArrayList<Reaction> getReactions(){ return this.reactions; }

    public void printCompartment(){
        System.out.println("Compartment: "+this.id);
        System.out.println("-Name: "+this.name);
        System.out.println("-Species:");
        for(Species s : this.species) {
            s.printSpecies();
            System.out.print("\n");
        }
        System.out.println("-Reactions:");
        for(Reaction r : this.reactions) {
            r.printReaction();
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
