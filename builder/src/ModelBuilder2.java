import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class ModelBuilder2 {

    private Biosystem B;

    private String output_dir;

    public ModelBuilder2(Biosystem B, String od){
        this.B = B;
        this.output_dir = od;
    }

    public void buildBiosystem() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir+"/biosystem.mo"));
        StringBuilder sb = new StringBuilder();
        StringBuilder equationBuilder = new StringBuilder();
        // env.sink1 = <compartment of sink1>.sink1
        sb.append("class Biosystem\n\n");
        sb.append("type Amount = Real(unit=\"mol*10^(-6)\");\n\n");

        int i = 1;
        String name;
        HashMap<String, String> idToName = new HashMap<>();
        for(Compartment c : this.B.getCompartments()){
            name = this.toClassName(c.getName());
            this.buildCompartment(c, name);
            //id="+ c.getId() + ", num_species=" + c.getNumSpecies() + ", num_reactions=" + c.getNumReactions() + ",
            sb.append(name + " c_"+(i)+";\n");
            idToName.put(c.getId(), "c_"+(i));
            i++;
        }
        equationBuilder.append("Environment env;\n");
        equationBuilder.append("\nequation\n");
        equationBuilder.append("\t// Sinks\n");
        for(Species sinkSpecies: this.B.getSinks()){
            String prefix = sinkSpecies.getCompartmentId()+"__";
            String speciesName = makeLegalName(sinkSpecies.getName());
            equationBuilder.append("\tenv."+prefix+speciesName+" = "+idToName.get(sinkSpecies.getCompartmentId())+"."+speciesName+";\n");
        }
        equationBuilder.append("\n\t// Sources\n");
        for(Species sourceSpecies: this.B.getSources()){
            String prefix = sourceSpecies.getCompartmentId()+"__";
            String speciesName = makeLegalName(sourceSpecies.getName());
            equationBuilder.append("\tenv."+prefix+speciesName+" = "+idToName.get(sourceSpecies.getCompartmentId())+"."+speciesName+";\n");
        }
        sb.append(equationBuilder);
        sb.append("\nend Biosystem;\n\n");
        bw.write(sb.toString());
        bw.close();
        this.buildEnvironment();

    }

    private void buildCompartment(Compartment c, String name) throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir+"/"+name.toLowerCase()+".mo"));
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        sb.append("block "+name+"\n\n");

        sb.append("type Amount = Real(unit=\"mol*10^(-6)\");\n\n");

        //sb.append("parameter String id;\nparameter Integer num_species;\nparameter Integer num_reactions;\n");
        //sb.append("parameter Amount initial_state["+c.getNumSpecies()+"];\n");
        //sb.append("parameter Real rate_constants["+c.getNumReactions()+"];\n\n");

        for(int i = 1; i < c.getNumReactions()+1; i++) sb.append("parameter Real rate_constant_"+i+" = "+(10e-2+r.nextDouble()*(10e5 - 10e-2))+";\n");

        sb.append(this.parseInputs(c));

        for(int i = 0; i < c.getNumSpecies(); i++) sb.append("Amount "+this.makeLegalName(c.getSpecies().get(i).getName())+";\n");



        sb.append("\n");
        for(int i = 0; i < c.getNumReactions(); i++) sb.append("Real "+c.getReactions().get(i).getId()+"_rate;\n");

        sb.append("\ninitial equation\n\n");
        for(int i = 0; i < c.getNumSpecies(); i++) sb.append(this.makeLegalName(c.getSpecies().get(i).getName())+" = "+(10e-10+r.nextDouble()*(10e-5 - 10e-10))+";\n");

        sb.append("\nequation\n\n");
        for(int i = 0; i < c.getNumReactions(); i++){
            sb.append(c.getReactions().get(i).getId()+"_rate = rate_constant_"+(i+1)+" ");
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
            else sb.append(der + "0;\n");
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
        for(Species sinkSpecies:this.B.getSinks()){
            sb.append("input Amount "+sinkSpecies.getCompartmentId()+"__"+makeLegalName(sinkSpecies.getName())+";\n");
        }
        sb.append("// Sources \n");
        for(Species sourceSpecies:this.B.getSources()){
            sb.append("output Amount "+sourceSpecies.getCompartmentId()+"__"+makeLegalName(sourceSpecies.getName())+";\n");
        }


        sb.append("\nend Environment;\n\n");
        bw.write(sb.toString());
        bw.close();
    }

    private String parseInputs(Compartment c){

        StringBuilder sb = new StringBuilder();
        for(SimpleReaction r : c.getReactions()){
            for(Species s : r.getReactants().keySet()) {
                if(c.searchSpecies(s.getId()) == null) sb.append("input Amount "+this.makeLegalName(s.getName())+";\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    private String makeLegalName(String s){
        if (Character.isDigit(s.charAt(0))) {
            String out = "s_";
            return out.concat(s.replaceAll("[^a-zA-Z0-9]+","_"));
        }

        return s.replaceAll("[^a-zA-Z0-9]+","_");

    }

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
