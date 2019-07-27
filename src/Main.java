import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.cli.*;

public class Main {

    private static String inputDir;
    private static String outputSBMLDir;
    private static String outputModelicaDir;
    private static String configDir;

    private static boolean configure;
    private static boolean execute;

    private static String usage = "java Main -c inputDir outputSBMLDir [configDir] | " +
        "-e inputDir outputSBMLDir [outputModelicaDir] [configDir] [-ic | -sc]";

    private static Options options = new Options();
    private static ArrayList<String> ignoreCompartmentList = new ArrayList<>();
    private static ArrayList<String> selectCompartmentList = new ArrayList<>();



    public static void main(String[] args) {

        handleOptions(args);
        handleArguments(args);

        if (configure) {
            Configure.run(inputDir, outputSBMLDir, configDir);
        } else if (execute) {
            Execute.run(inputDir, outputSBMLDir, outputModelicaDir, configDir);
        }

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

            if (argcopy.length < 2) {
                throw new ParseException("Too few arguments.");
            }

            inputDir = argcopy[0];
            outputSBMLDir = argcopy[1];

            if (configure) {
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

            }

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

        addOption("c", "configure", false,
                "mode \'configure\':  only create the structure of the xml configuration file", false);

        addOption("e", "execute", false,
                "mode \'execute\': execute the building using a configuration file", false);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);

            configure = cmd.hasOption("c");
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
            }

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
