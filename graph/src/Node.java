import java.util.Objects;

public class Node {
    private Species species;
    private boolean isOnlyModifier = true;

    /**
     * Class that represents a node in a graph
     * It models a species in the biosystem graph
     * @param species the species the node represents
     */
    Node(Species species){
        this.species = species;
    }

    Species getSpecies() {
        return species;
    }

    /**
     * A species is only modifier if does not take part in any reaction as either
     * reactant or product, but only as modifier
     * @return whether the species is only modifier or not
     */
    boolean isOnlyModifier() {
        return isOnlyModifier;
    }

    void setOnlyModifier(boolean onlyModifier) {
        isOnlyModifier = onlyModifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return isOnlyModifier == node.isOnlyModifier &&
                Objects.equals(species, node.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, isOnlyModifier);
    }

}
