import java.util.ArrayList;

public class ComplexReaction extends SimpleReaction {

    private ArrayList<Species> modifiers;

    public ComplexReaction(SimpleReaction sr){
        this.modifiers = new ArrayList<>();
        this.setId(sr.getId());
        this.setName(sr.getName());
        this.setReactants(sr.getReactants());
        this.setProducts(sr.getProducts());
    }

    public void addModifier(Species s){ this.modifiers.add(s); }

    public ArrayList<Species> getModifiers() { return modifiers; }

    public void setModifiers(ArrayList<Species> modifiers) { this.modifiers = modifiers; }
}
