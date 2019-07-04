import java.util.Objects;

public class Node {
    private String speciesId;
    private String compartmentId;
    private String speciesName;
    private boolean isModifier = true;

    public Node(String speciesId, String compartmentId, String speciesName){
        this.speciesId = speciesId;
        this.compartmentId = compartmentId;
        this.speciesName = speciesName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(speciesId, node.speciesId) &&
                Objects.equals(compartmentId, node.compartmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciesId, compartmentId);
    }

    public String getSpeciesId() {
        return speciesId;
    }

    public String getCompartmentId() {
        return compartmentId;
    }

    public boolean isModifier() {
        return isModifier;
    }

    public void setModifier(boolean modifier) {
        isModifier = modifier;
    }

    public void setCompartmentId(String compartmentId) {
        this.compartmentId = compartmentId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public void setSpeciesId(String speciesId) {
        this.speciesId = speciesId;
    }

}
