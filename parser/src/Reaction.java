import java.util.ArrayList;
import java.util.HashMap;

public class Reaction {

    private String id;
    private String name;

    private HashMap<Species, Integer> reactants;
    private HashMap<Species, Integer> products;
    private ArrayList<Species> modifiers;

    public Reaction(Species[] reactants, Species[] products, int[] reac_stoich, int[] prod_stoich){
        this.reactants = new HashMap<>();
        this.products = new HashMap<>();
        for(int i = 0; i < reactants.length; i++){
            this.reactants.put(reactants[i], reac_stoich[i]);
            this.products.put(products[i], prod_stoich[i]);
        }
    }

    public Reaction(String name, String id){
        this.name = name;
        this.id = id;
        this.reactants = new HashMap<>();
        this.products = new HashMap<>();
        this.modifiers = new ArrayList<>();
    }

    public String getId(){ return this.id; }

    public String getName(){ return this.name; }

    public void addReactant(Species s, int stoich){ this.reactants.put(s,stoich); }

    public void addProduct(Species s, int stoich){ this.products.put(s,stoich); }

    public int getReactantStoich(Species s){ return (this.reactants.get(s) == null) ? 0 : this.reactants.get(s); }

    public int getProductStoich(Species s){ return (this.products.get(s) == null) ? 0 : this.products.get(s); }

    public HashMap<Species, Integer> getReactants(){ return this.reactants; }

    public HashMap<Species, Integer> getProducts(){ return this.products; }

    public void addModifier(Species s){ this.modifiers.add(s); }

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
        System.out.println("        -Modifiers:");
        for (Species s : this.modifiers){
            System.out.println("            - "+s.getName());
        }
    }

}
