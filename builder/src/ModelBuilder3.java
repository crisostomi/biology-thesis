import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ModelBuilder3 {

    private BioSystem B;
    private String output_dir;
    private static final String indentation = "    ";

    public ModelBuilder3(BioSystem B, String od) {
        this.B = B;
        this.output_dir = od;
    }

    public void buildBioSystem() throws IOException {


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

        String indent = indentation.repeat(depth);

        StringBuilder sb_model = new StringBuilder(indent);
        StringBuilder sb_instance = new StringBuilder();

        sb_model.append("model Cell\n".concat(indent.repeat(2).concat("extends BioChem.Compartments.MainCompartment;\n\n")));

        int i = 1;
        for(Compartment c : this.B.getCompartments()){
            sb_model.append(this.buildCompartmentModel(c, depth+1));
            sb_instance.append(this.buildCompartmentInstance(c, depth+1, (i++)));
        }

        return sb_model.toString()+sb_instance.toString()+"\n"+indent+"end Cell;\n\n";
    }

    public String buildCompartmentModel(Compartment c, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder(indent);

        //build Model compartment
        String name = ModelBuilder3.toClassName(c.getName());
        sb.append("model ".concat(name.concat("\n")));
        sb.append(indent.concat("    extends BioChem.Compartments.Compartment;\n\n"));

        sb.append(this.buildAllSpecies(c, depth+1));

        if(c.getReactions().size() > 0) sb.append(this.buildAllReactions(c, depth+1, indent.concat("equation\n\n")));

        sb.append(indent.concat("end ".concat(name.concat(";\n\n"))));

        return sb.toString();
    }

    public String buildCompartmentInstance(Compartment c, int depth, int number){

        String indent = indentation.repeat(depth);
        //instantiate compartment
         return indent.concat(
                 ModelBuilder3.toClassName(c.getName()).concat(
                         " c_".concat(
                                 String.valueOf(number).concat(
                                         " \"".concat(
                                                 c.getId().concat("\";\n"))))));
    }

    public String buildAllReactions(Compartment c, int depth, String equation){

        String indent = indentation.repeat(depth);
        StringBuilder sb_instance = new StringBuilder();
        StringBuilder sb_equation = new StringBuilder("\n"+equation);

        for(SimpleReaction react : c.getReactions()){
            String instance = ModelBuilder3.inferReactionType(react);
            instance = ModelBuilder3.validateTemp(instance, react, c.getId()); //TEMPORARY until we have the pre-processing
            if(instance == null) sb_instance.append(indent.concat("//WARNING: could not infer reaction type of ").concat(react.getId().concat("\n")));
            else{
                sb_instance.append(indent.concat(instance.concat((" \""+react.getName()+"\";\n"))));
                sb_equation.append(ModelBuilder3.buildReactionEquation(react, depth));
            }
        }

        return sb_instance.toString()+sb_equation.toString();
    }

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

    private static String inferReactionType(SimpleReaction r){

        String kinetics;
        String classname = "";
        /*if(r instanceof ComplexReaction){
            if(r.getReactants().keySet().size() > 1 || r.getReactants().keySet().size() > 1) return null;
            classname += "Uu";

            if (((ComplexReaction) r).allBoundaryModifiers()) kinetics = "MassAction";

            if(r.isReversible()){
                classname += "r";
            }
            else{
                classname += "i";
            }
            return null;
        }
        else{*/
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
            //}

        }

        return "BioChem.Reactions."+kinetics+classname+" "+ModelBuilder3.createReactionInstance(r, kinetics);
    }

    private static String createReactionInstance(SimpleReaction react, String kinetics){

        StringBuilder sb = new StringBuilder();
        Random r = new Random(); //manca: assegna coefficienti stechiometrici
        double mean = 1e3;
        double std_dev = 1e3;

        sb.append(react.getId().concat("(k1=".concat(String.valueOf(Math.abs(r.nextGaussian()*std_dev+mean)))));

        int i = 1;
        for(Species s : react.getReactants().keySet()){
            sb.append(", nS".concat(String.valueOf(i++).concat("=".concat(String.valueOf(react.getReactantStoich(s))))));
        }
        i = 1;
        for(Species s : react.getProducts().keySet()){
            sb.append(", nP".concat(String.valueOf(i++).concat("=".concat(String.valueOf(react.getProductStoich(s))))));
        }

        return sb.toString().concat(")");
    }

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

    private static String validateTemp(String instance, SimpleReaction r, String comp_id){
        //this method is necessary until we can do the pre-processing (to-do.2)
        for(Species s : r.getReactants().keySet()){
            if (!s.getCompartmentId().equals(comp_id)) return null;
        }

        for(Species s : r.getProducts().keySet()){
            if (!s.getCompartmentId().equals(comp_id)) return null;
        }

        /*if(r instanceof ComplexReaction){
            for(Species s : ((ComplexReaction) r).getModifiers()){
                if(!s.getCompartmentId().equals(comp_id)) return null;
            }
        }*/

        return instance;
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