import java.util.HashMap;

public class ComplexReaction extends SimpleReaction {

    private HashMap<Species, ModifierType> modifiers;

    /**
     * Class representing a complex reaction, that is a reaction with no modifiers
     * @param sr the "base" reaction, to which the modifiers need to be added
     */
    public ComplexReaction(SimpleReaction sr){
        this.modifiers = new HashMap<>();
        this.setId(sr.getId());
        this.setName(sr.getName());
        this.setReactants(sr.getReactants());
        this.setProducts(sr.getProducts());
    }

    public enum ModifierType{
        CATALYST,
        POSITIVE_REGULATOR,
        NEGATIVE_REGULATOR
    }

    public boolean allBoundaryModifiers(){

        for(Species s : this.modifiers.keySet()){
            if(!s.isBoundary()) return false;
        }
        return true;
    }

    public ModifierType getModifierType(Species s){
        return this.modifiers.get(s);
    }

    /**
     * Method to add a modifier to the list of modifiers of the reaction
     * @param s the species to be added as modifier
     */
    public void addModifier(Species s, ModifierType t){ this.modifiers.put(s, t); }

    public HashMap<Species, ModifierType> getModifiers() { return modifiers; }

    public void setModifiers(HashMap<Species, ModifierType> modifiers) { this.modifiers = modifiers; }
}
