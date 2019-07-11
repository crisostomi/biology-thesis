import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ModelBuilder3 {

    private Biosystem B;
    private String output_dir;

    public ModelBuilder3(Biosystem B, String od) {
        this.B = B;
        this.output_dir = od;
    }

    public void buildBiosystem() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir + "/biosystem.mo"));
        StringBuilder sb = new StringBuilder();

        sb.append("package BioSystem\n\n");

        for(Compartment c : this.B.getCompartments()){
            sb.append("  "+this.buildCompartment(c));
        }

        sb.append("  "+this.buildCell());

        bw.write(sb.toString());
        bw.close();

    }

    public String buildCompartment(Compartment c){

        StringBuilder sb = new StringBuilder();
        sb.append("model "+ModelBuilder3.toClassName(c.getName())+"\n");
        sb.append("  extends BioChem.Compartments.Compartment;\n\n");

        sb.append("  "+this.buildAllSpecies(c));


        return sb.toString();
    }

    public String buildAllSpecies(Compartment c){

        StringBuilder sb = new StringBuilder();
        //for(Species s : )

        return sb.toString();
    }

    public String buildCell(){

        StringBuilder sb = new StringBuilder("  ");
        sb.append("model Cell\n\n");

        int i = 1;
        for(Compartment c : this.B.getCompartments()){
            sb.append("  ");
            sb.append(ModelBuilder3.toClassName(c.getName()));
            sb.append(" c_"+(i++)+";\n");
        }


        return sb.toString();
    }

    private static String toClassName(String s){
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

    private static String makeLegalName(String s){
        if (Character.isDigit(s.charAt(0))) {
            String out = "s_";
            return out.concat(s.replaceAll("[^a-zA-Z0-9]+","_"));
        }

        return s.replaceAll("[^a-zA-Z0-9]+","_");

    }

}