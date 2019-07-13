import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ModelBuilder3 {

    private BioSystem B;
    private String output_dir;

    public ModelBuilder3(BioSystem B, String od) {
        this.B = B;
        this.output_dir = od;
    }

    public void buildBiosystem() throws IOException {


        int depth = 0;

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir + "/BioSystem.mo"));
        StringBuilder sb = new StringBuilder();

        sb.append("package BioSystem\n\n");

        sb.append(this.buildCell(depth+1));

        sb.append("end BioSystem;\n");

        bw.write(sb.toString());
        bw.close();

    }

    public String buildCell(int depth){

        String indent = "    ".repeat(depth);

        StringBuilder sb_model = new StringBuilder(indent);
        StringBuilder sb_instance = new StringBuilder();

        sb_model.append("model Cell\n".concat(indent.repeat(2)).concat("extends BioChem.Compartments.MainCompartment;\n\n"));

        int i = 1;
        for(Compartment c : this.B.getCompartments()){
            String[] res = this.buildCompartment(c, depth+1, i);
            sb_model.append(res[0]);
            sb_instance.append(res[1]);
        }

        /*int i = 1;
        for(Compartment c : this.B.getCompartments()){
            sb.append("      ");
            sb.append(ModelBuilder3.toClassName(c.getName()));
            sb.append(" c_"+(i++)+";\n");
        }*/


        return sb_model.toString()+sb_instance.toString()+"\n"+indent+"end Cell;\n\n";
    }

    public String[] buildCompartment(Compartment c, int depth, int number){

        String indent = "    ".repeat(depth);
        StringBuilder sb_model = new StringBuilder(indent);
        StringBuilder sb_instance = new StringBuilder(indent);

        //build Model compartment
        String name = ModelBuilder3.toClassName(c.getName());
        sb_model.append("model ".concat(name).concat("\n"));
        sb_model.append(indent.concat("    extends BioChem.Compartments.Compartment;\n\n"));

        //sb_model.append(indent+"  "+this.buildAllSpecies(c));

        sb_model.append(indent.concat("end ").concat(name).concat(";\n\n"));

        //instantiate compartment
        sb_instance.append(name.concat(" c_").concat(String.valueOf(number)).concat(" \"").concat(c.getId()).concat("\";\n"));
        return new String[]{sb_model.toString(), sb_instance.toString()};
    }

    public String buildAllSpecies(Compartment c){

        StringBuilder sb = new StringBuilder();
        //for(Species s : )

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