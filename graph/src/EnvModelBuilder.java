import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class EnvModelBuilder {
    private HashSet<Node> sinks;
    private HashSet<Node> sources;
    private String outputDir;

    public EnvModelBuilder(HashSet<Node> sinks, HashSet<Node> sources, String outputDir) {
        this.sinks = sinks;
        this.sources = sources;
        this.outputDir = outputDir;
    }

    public void buildModelicaModel() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.outputDir+"/environment.mo"));
        StringBuilder sb = new StringBuilder();
        sb.append("block Environment\n\n");
        sb.append("type Amount = Real(unit=\"mol*10^(-6)\");\n\n");
        sb.append("// Sinks \n");
        for(Node sink:sinks){
            sb.append("input Amount "+sink.getCompartmentId()+"__"+makeLegalName(sink.getSpeciesName())+";\n");
        }
        sb.append("\n");
        sb.append("// Sources \n");
        for(Node source:sources){
            sb.append("output Amount "+source.getCompartmentId()+"__"+makeLegalName(source.getSpeciesName())+";\n");
        }

        sb.append("\nend Environment;\n\n");
        bw.write(sb.toString());
        bw.close();
    }


    public HashSet<Node> getSinks() {
        return sinks;
    }

    public void setSinks(HashSet<Node> sinks) {
        this.sinks = sinks;
    }

    public HashSet<Node> getSources() {
        return sources;
    }

    public void setSources(HashSet<Node> sources) {
        this.sources = sources;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String makeLegalName(String s){ return s.replaceAll("[^a-zA-Z0-9]+","_"); }
}
