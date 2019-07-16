import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class ModelBuilder3 {

    private BioSystem B;
    private String output_dir;
    private StringBuilder cell_equation;
    private HashMap<String, Integer> comp_number;
    private static final String indentation = "    ";

    public ModelBuilder3(BioSystem B, String od) {
        this.B = B;
        this.output_dir = od;
        this.cell_equation = new StringBuilder();
        this.comp_number = new HashMap<>();
    }

    public void buildBioSystem() throws IOException {


        int depth = 0;

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.output_dir + "/BioSystem.mo"));
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for(Compartment c : this.B.getCompartments()) this.comp_number.put(c.getId(), i++);

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
        this.cell_equation.append(indent.concat("equation\n\n"));

        sb_model.append("model Cell\n".concat(indent.repeat(2).concat("extends BioChem.Compartments.MainCompartment;\n\n")));

        for(Compartment c : this.B.getCompartments()){
            sb_model.append(this.buildCompartmentModel(c, depth+1, this.comp_number.get(c.getId())));
            sb_instance.append(this.buildCompartmentInstance(c, depth+1, this.comp_number.get(c.getId())));
        }

        if(this.cell_equation.toString().equals(indent.concat("equation\n\n"))) this.cell_equation.delete(0, this.cell_equation.length());

        return sb_model.toString()+sb_instance.toString()+"\n"+this.cell_equation.toString()+"\n"+indent+"end Cell;\n\n";
    }

    /*public String buildCompartmentEdges(Compartment c, int depth, int index){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder(indent);


        for(CompartmentEdge ce : this.B.getCompEdges()){ //la reazione sta in ce.compSourceId
            sb.append("connect(");
            if(ce.getCompDstId().equals(c.getId())){
                if(ce.getExternalReactant() != null){
                    sb.append("c_".concat(String.valueOf(index).
                            concat(".".concat("reaction_".
                                    concat(ce.getTransport().getId().
                                            concat(".".concat("s".concat(String.valueOf(s++)))))))));
                }
            }
        }

        return sb.toString();

    }*/

    public String buildCompartmentModel(Compartment c, int depth, int index){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder(indent);

        //build Model compartment
        String name = ModelBuilder3.toClassName(c.getName());
        sb.append("model ".concat(name.concat("\n")));
        sb.append(indent.concat("    extends BioChem.Compartments.Compartment;\n\n"));

        sb.append(this.buildAllSpecies(c, depth+1));

        sb.append(this.buildAllReactions(c, depth+1, index));

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

    public String buildAllReactions(Compartment c, int depth, int comp_index){

        String indent = indentation.repeat(depth);
        StringBuilder sb_instance = new StringBuilder();
        StringBuilder sb_equation = new StringBuilder();
        String mid = "\n"+indentation.repeat(depth-1)+"equation\n\n";

        for(SimpleReaction react : c.getReactions()){
            String instance = ModelBuilder3.inferReactionType(react);
            if(instance == null) sb_instance.append(indent.concat("//WARNING: could not infer reaction type of ").concat(react.getId().concat("\n")));
            else{
                sb_instance.append(indent.concat(instance.concat((" \""+react.getName()+"\";\n"))));
                int s = 1, p = 1, m = 1;
                for(CompartmentEdge ce : this.B.getCompEdges()){
                    if(ce.getTransport().getId().equals(react.getId())){
                        int comp_number = this.comp_number.get(ce.getCompDstId());
                        if(ce.getExternalReactant() != null){
                            this.cell_equation.append(
                                    buildConnectExternalReactant(comp_number, compIndex, react, ce, s, depth)
                            );
                            s++;
                        }
                        else if(ce.getExternalProduct() != null){
                            this.cell_equation.append(
                                    buildConnectExternalProduct(comp_number, compIndex, react, ce, p, depth)
                            );
                            p++;
                        }
                        else if(ce.getExternalModifier() != null){
                            this.cell_equation.append(
                                    buildConnectExternalModifier(comp_number, compIndex, react, ce, m, depth)
                            );
                            m++;
                        }
                    }
                }
                sb_equation.append(ModelBuilder3.buildReactionEquation(react, c.getId(), s, p, m, depth));
            }
        }

        if(sb_equation.toString().equals("")) mid = "";
        return sb_instance.toString()+mid+sb_equation.toString();
    }

    private String buildConnectExternalReactant(int compNumber, int compIndex, SimpleReaction react, CompartmentEdge ce, int s, int depth) {
        return indentation.repeat(depth-2)
                .concat("connect(c_".concat(String.valueOf(compNumber).concat("."
                        .concat(ce.getExternalReactant().getId().concat(".n1, c_")
                                .concat(String.valueOf(compIndex).concat("."
                                        .concat(react.getId().concat(".s"
                                                .concat(String.valueOf(s).concat(");\n"))))))))));
    }

    private String buildConnectExternalProduct(int compNumber, int compIndex, SimpleReaction react, CompartmentEdge ce, int p, int depth) {
        return indentation.repeat(depth-2)
                .concat("connect(c_".concat(String.valueOf(compNumber).concat("."
                        .concat(ce.getExternalProduct().getId().concat(".n1, c_")
                                .concat(String.valueOf(compIndex).concat("."
                                        .concat(react.getId().concat(".p"
                                                .concat(String.valueOf(p).concat(");\n"))))))))));
    }

    private String buildConnectExternalModifier(int compNumber, int compIndex, SimpleReaction react, CompartmentEdge ce, int m, int depth) {
        return indentation.repeat(depth-2)
                .concat("connect(c_".concat(String.valueOf(compNumber).concat("."
                        .concat(ce.getExternalModifier().getId().concat(".n1, c_")
                                .concat(String.valueOf(compIndex).concat("."
                                        .concat(react.getId().concat(".aF"
                                                .concat(String.valueOf(m).concat(");\n"))))))))));
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
            }

            if(r instanceof ComplexReaction) classname = classname+"fa";

        //}

        return "BioChem.Reactions."+kinetics+classname+" "+ModelBuilder3.createReactionInstance(r);
    }

    private static String createReactionInstance(SimpleReaction react){

        Random r = new Random();
        double mean = 1e3;
        double std_dev = 1e3;

        return react.getId() + "(k1=" +/*String.valueOf*/(Math.abs(r.nextGaussian()*std_dev+mean))/*.replace("E", "e")*/ + ")";

    }

    private static String buildReactionEquation(SimpleReaction r, int depth, String comp_id, int s, int p, int m){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        for(Species spec : r.getReactants().keySet()){
            if(spec.getCompartmentId().equals(comp_id)) {
                sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(r.getId().concat(".s".concat(String.valueOf(s++).concat(");\n"))))))));
            }
        }

        for(Species spec : r.getProducts().keySet()) {
            if (spec.getCompartmentId().equals(comp_id)) {
                sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(r.getId().concat(".p".concat(String.valueOf(p++).concat(");\n"))))))));
            }
        }

        if(r instanceof ComplexReaction){
            for(Species spec : ((ComplexReaction) r).getModifiers()) {
                if (spec.getCompartmentId().equals(comp_id)) {
                    sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(r.getId().concat(".aF".concat(String.valueOf(m++).concat(");\n"))))))));
                }
            }
        }

        return sb.toString()+"\n";
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