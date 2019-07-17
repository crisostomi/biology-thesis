import java.util.HashMap;

public class SimpleReaction {

    private String id;
    private String name;
    private boolean reversible;                     // whether or not the reaction is reversible

    private HashMap<Species, Integer> reactants;    // a map <Reactant, Stoichiometry>
    private HashMap<Species, Integer> products;     // a map <Product, Stoichiometry>

    /**
     * Class that represents a reaction in the biosystem
     * @param name the name of the reaction
     * @param id the unique id of the reaction
     */
    public SimpleReaction(String name, String id){
        this.name = name;
        this.id = id;
        this.reactants = new HashMap<>();
        this.products = new HashMap<>();
    }

    /**
     * Default constructor needed for extensions
     */
    public SimpleReaction(){
        this.name = "";
        this.id = "";
        this.reactants = new HashMap<>();
        this.products = new HashMap<>();
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public String getId(){ return this.id; }

    public String getName(){ return this.name; }

    /**
     * Method to add a reactant to the reaction
     * @param s the species to be added as reactant
     * @param stoich the stoichiometry of the reactant in the reaction
     */
    public void addReactant(Species s, int stoich){ this.reactants.put(s,stoich); }

    /**
     * Method to add a product to the reaction
     * @param s the species to be added as reactant
     * @param stoich the stoichiometry of the reactant in the reaction
     */
    public void addProduct(Species s, int stoich){ this.products.put(s,stoich); }

    public int getReactantStoich(Species s){ return (this.reactants.get(s) == null) ? 0 : this.reactants.get(s); }

    public int getProductStoich(Species s){ return (this.products.get(s) == null) ? 0 : this.products.get(s); }

    public void setReactants(HashMap<Species, Integer> reactants) { this.reactants = reactants; }

    public void setProducts(HashMap<Species, Integer> products) { this.products = products; }

    public HashMap<Species, Integer> getReactants(){ return this.reactants; }

    public HashMap<Species, Integer> getProducts(){ return this.products; }

    public boolean isReversible() {
        return reversible;
    }

    public void setReversible(boolean reversible) {
        this.reversible = reversible;
    }

    /**
     * Method to pretty-print the reaction for debugging purposes
     */
    public void printReaction(){
        System.out.println("    "+this.id + "\n        -Name: "+this.name);
        System.out.println("        -Reactants:");
        for (Species s : this.reactants.keySet()){
            System.out.println("            - "+this.reactants.get(s)+" * ("+s.getName()+")");
        }
        System.out.println("        -Products:");
        for (Species s : this.products.keySet()){
            System.out.println("            - "+this.products.get(s)+" * ("+s.getName()+")");
        }
    }

}
