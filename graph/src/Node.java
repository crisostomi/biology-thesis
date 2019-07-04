import java.util.Objects;

public class Node {
    private Species species;
    private boolean isModifier = true;

    Node(Species species){
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return isModifier == node.isModifier &&
                Objects.equals(species, node.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, isModifier);
    }

    Species getSpecies() {
        return species;
    }


    boolean isModifier() {
        return isModifier;
    }

    void setModifier(boolean modifier) {
        isModifier = modifier;
    }


}
