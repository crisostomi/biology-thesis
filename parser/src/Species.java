import java.util.Objects;

public class Species {

    private String name;
    private String id;
    private String compartmentId;
    private static Unit micromol = new Unit("microMol", "mol*10^(-6)");

    public Species(String name, String id, String compartmentId){
        this.name = name;
        this.id = id;
        this.compartmentId = compartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return name.equals(species.name) &&
                id.equals(species.id) &&
                compartmentId.equals(species.compartmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, compartmentId);
    }

    public String getId(){ return this.id; }

    public String getName(){ return this.name; }

    public String getCompartmentId(){
        return this.compartmentId;
    }

    public void printSpecies(){
        System.out.println("    " + this.id + "\n        -Name: "+this.name);
    }

}
