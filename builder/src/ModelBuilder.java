import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ModelBuilder {

    private BioSystem B;
    private String outputDir;
    //private Document config;
    private StringBuilder cellEquation;
    private HashMap<String, Integer> compNumber;       // key: compartmentId, value: progressive number
    private static final String indentation = "    ";   // 4 spaces used for indentation
    //private static double cellVolume = 10e-12;

    public ModelBuilder(BioSystem B, String od, String configPath) throws ParserConfigurationException, IOException, SAXException {
        this.B = B;
        this.outputDir = od;
        this.cellEquation = new StringBuilder();
        this.compNumber = new HashMap<>();

        File conf = new File(configPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document config = dBuilder.parse(conf);
        SpeciesBuilder.parseConfig(config);
        ReactionBuilder.parseConfig(config);
        MonitorBuilder.parseConfig(config);
    }

    public void buildBioSystem() throws IOException {

        int depth = 0;

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.outputDir + "/BioSystem.mo"));
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for(Compartment c : this.B.getCompartments()) this.compNumber.put(c.getId(), i++);

        sb.append("package BioSystem\n\n");

        sb.append(this.buildCell(depth+1));

        sb.append("end BioSystem;\n");

        bw.write(sb.toString());
        bw.close();

    }

    private String buildCell(int depth){

        double compartmentVolumePercentage = 0.1;
        String indent = indentation.repeat(depth);

        StringBuilder sb_model = new StringBuilder(indent);
        StringBuilder sb_instance = new StringBuilder();
        this.cellEquation.append(indent.concat("equation\n\n"));

        ReactionBuilder.setCompEdges(this.B.getCompEdges());

        /*try {
            File conf = new File(this.configPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            SpeciesBuilder.knowledge = dBuilder.parse(conf);
            ReactionBuilder.knowledge = SpeciesBuilder.knowledge;
        }catch(Exception e){
            System.out.println("Failed to open configuration file. Is "+ this.configPath +" a legitimate xml configuration file?");
            return null;
        }*/

        sb_model.append("model Cell\n".concat(indent.repeat(2).concat("extends BioChem.Compartments.MainCompartment")));
        sb_model.append("(V(start=cell_V));\n\n");
        sb_model.append(indent.repeat(depth+1).concat("inner parameter BioChem.Units.Volume cell_V;\n\n")); /* = " +cellVolume)+"*/

        CompartmentBuilder cb;
        for(Compartment c : this.B.getCompartments()){
            cb = new CompartmentBuilder(c);
            sb_model.append(cb.buildCompartmentModel(compartmentVolumePercentage, depth+1));
            sb_instance.append(cb.buildCompartmentInstance(depth+1));
            this.cellEquation.append(cb.getCompartmentLinks().toString());
        }

        sb_instance.append(MonitorBuilder.declareMonitor(indent.repeat(depth+1)));
        this.cellEquation.append("\n").append(MonitorBuilder.linkMonitor(this.B, indent.repeat(depth+1)));

        if(this.cellEquation.toString().equals(indent.concat("equation\n\n"))) this.cellEquation.delete(0, this.cellEquation.length());

        return sb_model.toString()+sb_instance.toString()+"\n"+this.cellEquation.toString()+"\n"+indent+"end Cell;\n\n";
    }

    /*private static String makeLegalName(String s){
        if (Character.isDigit(s.charAt(0))) {
            String out = "s_";
            return out.concat(s.replaceAll("[^a-zA-Z0-9]+","_"));
        }
        return s.replaceAll("[^a-zA-Z0-9]+","_");
    }*/

}