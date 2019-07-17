import java.util.ArrayList;

public class ComplexReaction extends SimpleReaction {

    private ArrayList<Species> modifiers;

    /**
     * Class representing a complex reaction, that is a reaction with no modifiers
     * @param sr the "base" reaction, to which the modifiers need to be added
     */
    public ComplexReaction(SimpleReaction sr){
        this.modifiers = new ArrayList<>();
        this.setId(sr.getId());
        this.setName(sr.getName());
        this.setReactants(sr.getReactants());
        this.setProducts(sr.getProducts());
    }


    public boolean allBoundaryModifiers(){

        for(Species s : this.modifiers){
            if(!s.isBoundary()) return false;
        }
        return true;
    }

    /**
     * Method to add a modifier to the list of modifiers of the reaction
     * @param s the species to be added as modifier
     */
    public void addModifier(Species s){ this.modifiers.add(s); }

    public ArrayList<Species> getModifiers() { return modifiers; }

    public void setModifiers(ArrayList<Species> modifiers) { this.modifiers = modifiers; }
}
