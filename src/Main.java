import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.cli.*;

public class Main {

    static private Options options = new Options();
    static private ArrayList<String> ignoreCompartmentList = new ArrayList<>();
    static private ArrayList<String> selectCompartmentList = new ArrayList<>();

    /**
     * Method to add an option to the cmd invocation of the program
     * @param opt short name of the option (generally a letter), called with a -
     * @param longOpt long name of the option, called with a --
     * @param hasArg whether the option is a simple flag or has an argument after it
     * @param description short description of the use and purpose of the option
     * @param isRequired whether or not the option is, well, optional or not
     */
    private static void addOption(String opt, String longOpt, boolean hasArg,
                                  String description, boolean isRequired) {
        Option option = new Option(opt, longOpt, hasArg, description);
        option.setRequired(isRequired);
        options.addOption(option);
    }

    /**
     * Method to parse an option argument shaped as a list
     * @param cmd the command line instance
     * @param optName the name of the option to be parsed
     * @return the list of arguments
     * @throws ParseException
     */
    private static ArrayList<String> parseOptionList(CommandLine cmd, String optName) throws ParseException{
        String optValue = cmd.getOptionValue(optName);

        if (optValue.isBlank()) {
            throw new ParseException("No value found for option " + optName);
        }

        if (!(optValue.startsWith("{") && optValue.endsWith("}"))) {
            throw new ParseException("Could not parse option " + optName + " argument. Maybe missing \"{,}\"?");
        }


        ArrayList<String> out = new ArrayList<>(Arrays.asList(
                optValue.replaceAll("[\\{\\}]", "").split("\\s*,\\s*")
        ));

        return out;
    }

    public static void main(String[] args) {

        /*
        0. input folder with sbml files
        1. output folder to write sbml_union
        2. output folder to write Modelica files (BioSystem + Monitor)
        3. input config file
        */
        String[] argcopy = new String[4];
        System.arraycopy(args, 0, argcopy, 0, args.length);
        if(argcopy[2] == null) argcopy[2] = argcopy[1];

        String inputDir = argcopy[0];
        String outputSBMLDir = argcopy[1];
        String outputModelicaDir = argcopy[2];
        String configXMLfile = argcopy[3];

        /*
        addOption(null, "ignoreComp", true,
                "list of compartment ids to be ignored {compId_1, ..., compId_n}", false);

        addOption(null, "selectComp", true,
                "list of compartment ids to be selected {compId_1, ..., compId_n}", false);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            ignoreCompartmentIdList.addAll(parseOptionList(cmd, "ignoreComp"));
            selectCompartmentIdList.addAll(parseOptionList(cmd, "selectComp"));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
        */

        HashSet<Compartment> comps;
        Parser P;

        // parse input sbml
        try { //TODO: don't generate sbml union in this Main
            P = new Parser(inputDir, outputSBMLDir);
        } catch(ParserConfigurationException e){
            System.out.println("Parsing fail due to SBMLBuilder instantiation failure");
            return;
        }

        // convert sbml data into a collection of Compartment objects
        comps = P.instantiateCompartments();
        if (comps == null || comps.size() == 0) {
            System.out.println("Compartment instantiation failed. Returning with error");
            return;
        }

        /*
        create a graph structure in order to find species that only get consumed or produced
        by the system reactions
         */
        Graph g = BioToGraph.buildGraph(comps);
        HashSet<Species> sinks = new HashSet<>();
        for(Node node: g.findSinks()){
            sinks.add(node.getSpecies());
        }
        HashSet<Species> sources = new HashSet<>();
        for(Node node: g.findSources()){
            sources.add(node.getSpecies());
        }

        // instantiate the Java model
        BioSystem bs = new BioSystem(comps, sinks, sources);

        // convert Java biosystem model in Modelica
        ModelBuilder mb = new ModelBuilder(bs, outputModelicaDir);
        try {
            mb.buildBioSystem();
        } catch(IOException e){
            System.out.println("Modelica files creation/writing failed");
        }

        String xmlOutDir = "/home/scacio/Dropbox/Tesisti/software/development/reactome-compiler/test-case-6/out";

        try {
            ConstraintBuilder cb = new ConstraintBuilder(bs, xmlOutDir);
            cb.build();
        } catch (Exception e) {
            System.out.println("Constraints XML creation/writing failed");
            e.printStackTrace();
        }

        MonitorBuilder monb = new MonitorBuilder(bs, xmlOutDir, outputModelicaDir);
        try {
            monb.build();
        } catch (IOException e) {
            System.out.println("Modelica monitor creation/writing failed");
            e.printStackTrace();
        }

        System.out.println("All done!");


        /*
        TODO: add --configure and --execute operational modes
        TODO: fix ignoring compartments trouble with reaction
         */

    }
}
