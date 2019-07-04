import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class ModelBuilder {

    private Biosystem B;
    private HashSet<Node> sinks;
    private HashSet<Node> sources;
    private String output_dir;

    public ModelBuilder(Biosystem B, String od, HashSet<Node> sinks, HashSet<Node> sources){
        this.B = B;
        this.output_dir = od;
        this.sinks = sinks;
        this.sources = sources;
    }

    public void buildBiosystem() throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir+"/biosystem.mo"));
        StringBuilder sb = new StringBuilder();
        sb.append("class Biosystem\n\n");

        sb.append("type Amount = Real(unit=\"mol*10^(-6)\");\n\n");

        //sb.append("import Data.*;\n\n");

        int i = 1;
        for(Compartment c : this.B.getCompartments()){
            sb.append("parameter Amount init_c_"+(i++)+"["+c.getNumSpecies()+"];\n");
        }
        sb.append("\n");
        i = 1;
        for(Compartment c : this.B.getCompartments()){
            sb.append("parameter Real rates_c_"+(i++)+"["+c.getNumReactions()+"];\n");
        }
        sb.append("\n");

        i = 1;
        String name;
        for(Compartment c : this.B.getCompartments()){
            name = this.toClassName(c.getName());
            this.buildCompartment(c, name);
            //id="+ c.getId() + ", num_species=" + c.getNumSpecies() + ", num_reactions=" + c.getNumReactions() + ",
            sb.append(name + " c_"+(i)+"(initial_state=init_c_"+(i)+ ", rate_constants=rates_c_"+(i++)+ ");\n");
        }

        this.buildEnvironment();
        sb.append("\nend Biosystem;\n\n");
        bw.write(sb.toString());
        bw.close();

    }

    private void buildCompartment(Compartment c, String name) throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir+"/"+name.toLowerCase()+".mo"));
        StringBuilder sb = new StringBuilder();
        sb.append("block "+name+"\n\n");

        sb.append("type Amount = Real(unit=\"mol*10^(-6)\");\n\n");

        //sb.append("parameter String id;\nparameter Integer num_species;\nparameter Integer num_reactions;\n");
        sb.append("parameter Amount initial_state[num_species];\n");
        sb.append("parameter Real rate_constants[num_reactions];\n\n");

        sb.append(this.parseInputs(c));

        for(int i = 0; i < c.getNumSpecies(); i++) sb.append("Amount "+this.makeLegalName(c.getSpecies().get(i).getName())+";\n");

        sb.append("\n");
        for(int i = 0; i < c.getNumReactions(); i++) sb.append("Real "+c.getReactions().get(i).getId()+"_rate;\n");

        sb.append("\ninitial equation\n\n");
        for(int i = 0; i < c.getNumSpecies(); i++) sb.append(this.makeLegalName(c.getSpecies().get(i).getName())+" = initial_state["+(i+1)+"];\n");

        sb.append("\nequation\n\n");
        for(int i = 0; i < c.getNumReactions(); i++){
            sb.append(c.getReactions().get(i).getId()+"_rate = rate_constants["+(i+1)+"] ");
            for(Species s : c.getReactions().get(i).getReactants().keySet()){
                sb.append("* ("+this.makeLegalName(s.getName()) +"^"+c.getReactions().get(i).getReactantStoich(s)+") ");
            }
            sb.append(";\n");
        }
        sb.append("\n");

        StringBuilder der = new StringBuilder();
        boolean first = true;
        for(int i = 0; i < c.getNumSpecies(); i++){
            der.append("der("+this.makeLegalName(c.getSpecies().get(i).getName()) + ") = ");
            for(int j = 0; j < c.getNumReactions(); j++){
                int stoich = c.getReactions().get(j).getProductStoich(c.getSpecies().get(i));
                if(stoich > 0) {
                    if(first) first = false;
                    else der.append("+ ");
                    der.append(stoich+" * "+c.getReactions().get(j).getId() + "_rate ");
                }
                stoich = c.getReactions().get(j).getReactantStoich(c.getSpecies().get(i));
                if(stoich > 0) {
                    if(first) first = false;
                    else der.append("- ");
                    der.append(stoich +" * "+c.getReactions().get(j).getId() + "_rate ");
                }
            }
            if(!der.toString().equals("der("+this.makeLegalName(c.getSpecies().get(i).getName()) + ") = ")) sb.append(der + ";\n");
            der.delete(0, der.length());
            first = true;
        }

        sb.append("\nend "+name+";\n\n");
        bw.write(sb.toString());
        bw.close();
    }

    private void buildEnvironment() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir+"/environment.mo"));
        StringBuilder sb = new StringBuilder();
        sb.append("block Environment\n\n");
        sb.append("type Amount = Real(unit=\"mol*10^(-6)\");\n\n");
        sb.append("// Sinks \n");
        for(Node sink:this.sinks){
            sb.append("input Amount "+sink.getCompartmentId()+"__"+makeLegalName(sink.getSpeciesName())+";\n");
        }
        sb.append("// Sources \n");
        for(Node source:this.sources){
            sb.append("output Amount "+source.getCompartmentId()+"__"+makeLegalName(source.getSpeciesName())+";\n");
        }

        sb.append("\nend Environment;\n\n");
        bw.write(sb.toString());
        bw.close();
    }

    private String parseInputs(Compartment c){

        StringBuilder sb = new StringBuilder();
        for(Reaction r : c.getReactions()){
            for(Species s : r.getReactants().keySet()) {
                if(c.searchSpecies(s.getId()) == null) sb.append("input "+this.makeLegalName(s.getName())+";\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    private String makeLegalName(String s){ return s.replaceAll("[^a-zA-Z0-9]+","_"); }

    private String toClassName(String s){
        String res = "";
        res = res.concat(s.substring(0,1).toUpperCase());
        for(int i = 1; i < s.length(); i++){
            if(s.substring(i,i+1).equals(" ")){
                res = res.concat(s.substring(i+1,i+2).toUpperCase());
                i++;
            }
            else res = res.concat(s.substring(i, i+1));
        }
        return res;
    }

}
