public class Species {

    private String name;
    private String id;
    private double initial_value;
    private static Unit micromol = new Unit("microMol", "mol*10^(-6)");

    public Species(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getId(){ return this.id; }

    public String getName(){ return this.name; }

    public void printSpecies(){
        System.out.println("    " + this.id + "\n        -Name: "+this.name);
    }

}
