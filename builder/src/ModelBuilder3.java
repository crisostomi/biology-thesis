import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ModelBuilder3 {
    /**
     * Class for building Modelica model of the biosystem using the BioChem library
     */

    private BioSystem B;
    private String output_dir;
    private static final String indentation = "    ";   // 4 spaces for indentation
    private static final double cellVolume = 10e-12;

    /**
     * @param B the BioSystem object containing all the relevant information parsed and processed from the sbml
     * @param od output directory to which the BioSystem.mo file gets written
     */
    public ModelBuilder3(BioSystem B, String od) {
        this.B = B;
        this.output_dir = od;
    }

    /**
     * Main method of the class, used to actually write onto the modelica file
     * @throws IOException if something goes south with the BufferedWriter write() call
     * @see #buildCell(double, int)
     */
    public void buildBioSystem() throws IOException {


        int depth = 0;

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir + "/BioSystem.mo"));
        StringBuilder sb = new StringBuilder();

        sb.append("package BioSystem\n\n");

        sb.append(this.buildCell(cellVolume, depth+1));

        sb.append("end BioSystem;\n");

        bw.write(sb.toString());
        bw.close();

    }

    /**
     * Method to build all the compartments of the cell found in the sbml
     * @param depth used for text indentation purposes
     * @param cellVolume the volume of the cell in litres
     * @return the Modelica code for the BioSystem
     * @see #buildCompartmentModel(Compartment, double, int)
     * @see #buildCompartmentInstance(Compartment, int, int)
     */
    public String buildCell(double cellVolume, int depth){

        double compartmentVolumePercentage = 0.1;
        String indent = indentation.repeat(depth);

        StringBuilder sb_model = new StringBuilder(indent);
        StringBuilder sb_instance = new StringBuilder();

        sb_model.append("model Cell\n".concat(indent.repeat(2).concat("extends BioChem.Compartments.MainCompartment")));
        sb_model.append("(V(start=cell_V));\n\n");
        sb_model.append(indent.repeat(depth+1).concat("inner parameter BioChem.Units.Volume cell_V = "+String.valueOf(cellVolume)+";\n\n"));

        int i = 1;
        for(Compartment c : this.B.getCompartments()){
            sb_model.append(this.buildCompartmentModel(c, compartmentVolumePercentage, depth+1));
            sb_instance.append(this.buildCompartmentInstance(c, (i++) , depth+1));
        }

        return sb_model.toString()+sb_instance.toString()+"\n"+indent+"end Cell;\n\n";
    }

    /**
     * Method to actually build the compartments, which means writing Modelica code for
     * the Species and the Reactions
     * @param c the name of the compartment
     * @param cellVolumePercentage the volume of the compartment in proportion to the cell volume
     * @param depth used for indentation purposes
     * @return a String comprised of Modelica code
     * @see #buildAllSpecies(Compartment, int)
     * @see #buildAllReactions(Compartment, String, int)
     */
    public String buildCompartmentModel(Compartment c, double cellVolumePercentage, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder(indent);

        //build Model compartment
        String name = ModelBuilder3.toClassName(c.getName());
        sb.append("model ".concat(name.concat("\n")));
        sb.append(indent.concat("    extends BioChem.Compartments.Compartment"));
        sb.append("(V(start="+cellVolumePercentage+"*cell_V));\n\n");

        sb.append(indent.concat("    outer parameter BioChem.Units.Volume cell_V;\n\n"));

        sb.append(this.buildAllSpecies(c, depth+1));

        sb.append(this.buildAllReactions(c, indent.concat("equation\n\n"), depth+1));

        sb.append(indent.concat("end ".concat(name.concat(";\n\n"))));

        return sb.toString();
    }

    /**
     * Method used to declare all the compartments inside Modelica
     * @param c the name of the compartment
     * @param depth used for indentation purposes
     * @param number progressive number used to declare the compartment
     * @return a String comprised of Modelica code for the declaration of compartments
     */
    public String buildCompartmentInstance(Compartment c, int number, int depth){

        String indent = indentation.repeat(depth);
        //instantiate compartment
         return indent.concat(
                 ModelBuilder3.toClassName(c.getName()).concat(
                         " c_".concat(
                                 String.valueOf(number).concat(
                                         " \"".concat(
                                                 c.getId().concat("\";\n"))))));
    }

    /**
     * Method used to declare and initialize all the species in a Compartment
     * @param c the Compartment
     * @param depth used for indentation purposes
     * @return a String comprised of Modelica code handling the species
     */
    public String buildAllSpecies(Compartment c, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        double mean = 1e-12;
        double std_dev = 1e-14;

        for(Species s : c.getSpecies()){
            if(s.isBoundary()) sb.append(indent.concat("BioChem.Substances.BoundarySubstance "));//.concat(s.getId().concat("\""+s.getName()+"\";\n"))));
            else sb.append(indent.concat("BioChem.Substances.Substance "));//
            double init = Math.abs(r.nextGaussian()*std_dev+mean);
            sb.append(s.getId().concat("(n(start=".concat(String.valueOf(init)/*.replace("E", "e")*/.concat(")) \""+s.getName()+"\";\n"))));
        }

        return sb.toString().concat("\n");
    }

    /**
     * Method used to build the Modelica code for the reactions in the compartment, that is,
     * declare, initialize and properly link every reaction in the compartment in Modelica
     * WARNING: it ignores all the ComplexReactions, only building a warning in their stead
     * @param c the compartment
     * @param depth used for indentation purposes
     * @param equation a String only containing the "equation" block header (not sure if needed)
     * @return a String comprised of Modelica code handling the reactions
     * @see #inferReactionType(SimpleReaction)
     * @see #buildReactionEquation(SimpleReaction, int)
     */
    public String buildAllReactions(Compartment c, String equation, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb_instance = new StringBuilder();
        StringBuilder sb_equation = new StringBuilder("\n"+equation);

        for(SimpleReaction react : c.getReactions()){
            String instance = ModelBuilder3.inferReactionType(react);
            if(instance == null) sb_instance.append(indent.concat("//WARNING: could not infer reaction type of ").concat(react.getId().concat("\n")));
            else{
                sb_instance.append(indent.concat(instance.concat((" \""+react.getName()+"\";\n"))));
                sb_equation.append(ModelBuilder3.buildReactionEquation(react, depth));
            }
        }

        return sb_instance.toString()+sb_equation.toString();
    }

    /**
     * Method used to assign a reaction to a proper Reaction class from the ones available from
     * the BioChem library, that is detect number of reactants and of products and kinetic type
     * WARNING: for now the method ignores all the ComplexReactions, that is all the reactons with
     * modifiers, and it assumes all the others follow Mass Action kinetic law
     * @param r the reaction
     * @return a String comprised of the declaration of the reaction
     * @see #createReactionInstance(SimpleReaction)
     */
    private static String inferReactionType(SimpleReaction r){

        String kinetics;
        String classname = "";
        if(r instanceof ComplexReaction){
            /*if(r.getReactants().keySet().size() > 1 || r.getReactants().keySet().size() > 1) return null;
            classname += "Uu";

            if (((ComplexReaction) r).allBoundaryModifiers()) kinetics = "MassAction";

            if(r.isReversible()){
                classname += "r";
            }
            else{
                classname += "i";
            }*/
            return null;
        }
        else{
            kinetics = ""; //assuming MassAction kinetics for all SimpleReactions
            //Note: BioChem has MassAction reactions for all combinations of [1,3] reactants/products
            if(r.getReactants().keySet().size() > 3 || r.getProducts().keySet().size() > 3) return null;
            else if(r.getProducts().keySet().size() == 0){
                //Cerca una reazione con gli stessi reagenti ma con almeno un prodotto:
                //Se esiste, rendila ininfluente (non deve avvenire)
                return null;
            }
            switch(r.getReactants().keySet().size()){
                case 1: kinetics += "Uni"; classname += "U"; break;
                case 2: kinetics += "Bi"; classname += "B"; break;
                case 3: kinetics += "Tri"; classname += "T"; break;
            }
            switch(r.getProducts().keySet().size()){
                case 1: kinetics += "Uni."; classname += "u"; break;
                case 2: kinetics += "Bi."; classname += "b"; break;
                case 3: kinetics += "Tri."; classname += "t"; break;
            }
            if(r.isReversible()){
                kinetics = "MassAction.Reversible."+kinetics;
                classname += "r";
            }
            else{
                kinetics = "MassAction.Irreversible."+kinetics;
                classname += "i";
            }

        }

        return "BioChem.Reactions."+kinetics+classname+" "+ModelBuilder3.createReactionInstance(r);
    }

    /**
     * Method used to randomly assign a kinetic constant to the reaction (following Mass Action law)
     * @param react the reaction (must be SimpleReaction for now)
     * @return a String comprised of the name of the reaction (its Reactome ID), plus the parameters needed
     * by the library
     */
    private static String createReactionInstance(SimpleReaction react){

        Random r = new Random();
        double mean = 1e3;
        double std_dev = 1e3;

        return react.getId() + "(k1=" +/*String.valueOf*/(Math.abs(r.nextGaussian()*std_dev+mean))/*.replace("E", "e")*/ + ")";

    }

    /**
     * Method used to link the reactants and products of a reaction to the correct species
     * in Modelica
     * @param r the reaction
     * @param depth used for indentation purposes
     * @return a String comprised of Modelica code to properly link reactions and species in the
     * model
     */
    private static String buildReactionEquation(SimpleReaction r, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for(Species s : r.getReactants().keySet()){
            sb.append(indent.concat("connect(".concat(s.getId().concat(".n1, ".concat(r.getId().concat(".s".concat(String.valueOf(i++).concat(");\n"))))))));
        }

        i = 1;
        for(Species s : r.getProducts().keySet()){
            sb.append(indent.concat("connect(".concat(s.getId().concat(".n1, ".concat(r.getId().concat(".p".concat(String.valueOf(i++).concat(");\n"))))))));
        }

        return sb.toString()+"\n";
    }

    /**
     * Method used to make Modelica class names using CamelCase convention
     * @param s the name of the class
     * @return the "standardized" name of the class
     */
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

    /**
     * Method used to remove all invalid characters from the names of the Species
     * @param s the name of the Species
     * @return a legal name for the Species
     */
    private static String makeLegalName(String s){
        if (Character.isDigit(s.charAt(0))) {
            String out = "s_";
            return out.concat(s.replaceAll("[^a-zA-Z0-9]+","_"));
        }

        return s.replaceAll("[^a-zA-Z0-9]+","_");

    }

}