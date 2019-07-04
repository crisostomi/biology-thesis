import java.util.Objects;

public class Node {
    private String speciesId;
    private String compartmentId;
    private String speciesName;
    private boolean isModifier = true;

    Node(String speciesId, String compartmentId, String speciesName){
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.compartmentId = compartmentId;

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

    String getSpeciesId() {
        return speciesId;
    }

    public String getCompartmentId() {
        return compartmentId;
    }

    boolean isModifier() {
        return isModifier;
    }

    void setModifier(boolean modifier) {
        isModifier = modifier;
    }

    public String getSpeciesName() {
        return speciesName;
    }

}
