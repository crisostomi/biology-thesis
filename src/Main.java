import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.cli.*;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

    private static String inputDir;
    private static String configFilePath;
    private static String outputSBMLDir;
    private static String outputModelicaDir;

/*    private static boolean configure;
    private static boolean execute;*/

    private static String usage = "java Main inputDir configFilePath outputSBMLDir [outputModelicaDir] " +
            "[-ic <arg> | -sc <arg> ]";

    private static Options options = new Options();
    private static ArrayList<String> ignoreCompartmentList = new ArrayList<>();
    private static ArrayList<String> selectCompartmentList = new ArrayList<>();



    public static void main(String[] args) {

        handleOptions(args);
        handleArguments(args);

        /*if (configure) {
            System.out.println("-c option is deprecated; run with -e");
            System.exit(1);
            //Configure.run(inputDir, outputSBMLDir, configDir);
        } else if (execute) {
            Execute.run(inputDir, outputSBMLDir, outputModelicaDir, configDir);
        }*/

        System.out.println("Beginning execution...");
        HashSet<Compartment> comps;
        Parser P;

        // parse input sbml
        try {
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

        //update config.xml file
        ConfigBuilder cb;
        try {
            cb = new ConfigBuilder(bs, configFilePath);
            cb.build();
        } catch(Exception e){
            System.out.println("Failed to update configuration file.\nIs "+ configFilePath +
                    " a legal configuration file?");
            System.exit(1);
        }

        // convert Java biosystem model in Modelica
        ModelBuilder mb = new ModelBuilder(bs, outputModelicaDir, configFilePath);
        try {
            mb.buildBioSystem();
        } catch(IOException e){
            System.out.println("Modelica files creation/writing failed");
        }

        MonitorBuilder monb = new MonitorBuilder(bs, outputModelicaDir, ConfigBuilder.speciesIndex);
        try {
            monb.build();
        } catch (IOException e) {
            System.out.println("Modelica monitor creation/writing failed");
            e.printStackTrace();
        }

        System.out.println("All done!");

        /*
        TODO: fix ignoring compartments trouble with reaction
         */

    }

    private static void handleArguments(String[] args) {

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            String[] argcopy = cmd.getArgs();

            if (argcopy.length < 3) {
                throw new ParseException("Expected at least 3 arguments but got " + argcopy.length + ".");
            }

            inputDir = argcopy[0];
            configFilePath = argcopy[1];
            outputSBMLDir = argcopy[2];

            if (argcopy.length > 4) {
                throw new ParseException("Expected at most 4 arguments but got " + argcopy.length + ".");
            }
            outputModelicaDir = (argcopy.length > 3) ? argcopy[3] : outputSBMLDir;

            /*if (configure) {
                if (argcopy.length > 3) {
                    throw new ParseException("Too many arguments for specified mode.");
                }
                configDir = (argcopy.length > 2) ? argcopy[2] : outputSBMLDir;


            } else if (execute) {
                if (argcopy.length > 4) {
                    throw new ParseException("Too many arguments for specified mode.");
                }
                outputModelicaDir = (argcopy.length > 2) ? argcopy[2] : outputSBMLDir;
                configDir = (argcopy.length > 3) ? argcopy[3] : outputSBMLDir;

            }*/

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(usage, options);
            System.exit(1);
        }
    }

    private static void handleOptions(String[] args) {

        addOption("ic", "ignoreComp", true,
                "list of compartment ids to be ignored {compId_1, ..., compId_n}", false);

        addOption("sc", "selectComp", true,
                "list of compartment ids to be selected {compId_1, ..., compId_n}", false);

/*        addOption("c", "configure", false,
                "mode \'configure\':  only create the structure of the xml configuration file", false);

        addOption("e", "execute", false,
                "mode \'execute\': execute the building using a configuration file", false);*/

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);

/*            configure = cmd.hasOption("c");
            execute = cmd.hasOption("e");

            if (!configure && !execute) {
                System.out.println("Please specify a mode.");
                formatter.printHelp(usage, options);

                System.exit(1);
            }

            if (configure && execute) {
                System.out.println("Please specify only a mode.");
                formatter.printHelp(usage, options);

                System.exit(1);
            }*/

            if (cmd.hasOption("ic")) ignoreCompartmentList.addAll(parseOptionList(cmd, "ignoreComp"));
            if (cmd.hasOption("sc")) selectCompartmentList.addAll(parseOptionList(cmd, "selectComp"));

            if (cmd.hasOption("ic") && cmd.hasOption("sc")) {
                System.out.println("Cannot ignore and select compartments at once.");
                formatter.printHelp(usage, options);

                System.exit(1);
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(usage, options);

            System.exit(1);
        }
    }

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


}
