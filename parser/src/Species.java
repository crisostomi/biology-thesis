import java.util.Objects;

public class Species {

    private String name;
    private String id;
    private String compartmentId;
    private String compartmentName;
    private boolean isBoundary;

    /**
     * Class that represents a species in the biosystem
     * @param name the name of the species
     * @param id the unique id of the species
     * @param compartmentId the unique id of the compartment in which the species is
     * @param compartmentName the name of the compartment
     */
    public Species(String name, String id, String compartmentId, String compartmentName){
        this.name = name;
        this.id = id;
        this.compartmentId = compartmentId;
        this.compartmentName = compartmentName;
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

    public boolean isBoundary() {
        return isBoundary;
    }

    public void setBoundary(boolean boundary) {
        isBoundary = boundary;
    }

    public void printSpecies(){
        System.out.println("    " + this.id + "\n        -Name: "+this.name);
    }

}
