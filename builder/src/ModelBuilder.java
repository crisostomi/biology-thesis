import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ModelBuilder {

    private BioSystem B;
    private String output_dir;
    private String configDir;
    private StringBuilder cell_equation;
    private HashMap<String, Integer> comp_number;       // key: compartmentId, value: progressive number
    private static final String indentation = "    ";   // 4 spaces used for indentation
    private static double cellVolume = 10e-12;

    public ModelBuilder(BioSystem B, String od, String configDir) {
        this.B = B;
        this.output_dir = od;
        this.cell_equation = new StringBuilder();
        this.comp_number = new HashMap<>();
        this.configDir = configDir;
    }

    public void buildBioSystem() throws IOException {

        int depth = 0;

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir + "/BioSystem.mo"));
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for(Compartment c : this.B.getCompartments()) this.comp_number.put(c.getId(), i++);

        sb.append("package BioSystem\n\n");

        sb.append(this.buildCell(cellVolume, depth+1));

        sb.append("end BioSystem;\n");

        bw.write(sb.toString());
        bw.close();

    }

    public String buildCell(double cellVolume, int depth){

        double compartmentVolumePercentage = 0.1;
        String indent = indentation.repeat(depth);

        StringBuilder sb_model = new StringBuilder(indent);
        StringBuilder sb_instance = new StringBuilder();
        this.cell_equation.append(indent.concat("equation\n\n"));

        ReactionBuilder.setCompEdges(this.B.getCompEdges());
        ReactionBuilder.setCompNumber(this.comp_number);

        try {
            String filename = this.configDir + "/constraints.xml";
            File conf = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            SpeciesBuilder.knowledge = dBuilder.parse(conf);
            ReactionBuilder.knowledge = SpeciesBuilder.knowledge;
        }catch(Exception e){
            System.out.println("Failed to open configuration file. Is "+this.configDir +" a legitimate xml configuration file?");
            return null;
        }

        sb_model.append("model Cell\n".concat(indent.repeat(2).concat("extends BioChem.Compartments.MainCompartment")));
        sb_model.append("(V(start=cell_V));\n\n");
        sb_model.append(indent.repeat(depth+1).concat("inner parameter BioChem.Units.Volume cell_V = " +cellVolume)+";\n\n");

        CompartmentBuilder cb;
        for(Compartment c : this.B.getCompartments()){
            cb = new CompartmentBuilder(c, this.comp_number.get(c.getId()));
            sb_model.append(cb.buildCompartmentModel(compartmentVolumePercentage, depth+1));
            sb_instance.append(cb.buildCompartmentInstance(depth+1));
            this.cell_equation.append(cb.getCompartmentLinks().toString());
        }

        if(this.cell_equation.toString().equals(indent.concat("equation\n\n"))) this.cell_equation.delete(0, this.cell_equation.length());

        return sb_model.toString()+sb_instance.toString()+"\n"+this.cell_equation.toString()+"\n"+indent+"end Cell;\n\n";
    }

    /*private static String makeLegalName(String s){
        if (Character.isDigit(s.charAt(0))) {
            String out = "s_";
            return out.concat(s.replaceAll("[^a-zA-Z0-9]+","_"));
        }
        return s.replaceAll("[^a-zA-Z0-9]+","_");
    }*/

}