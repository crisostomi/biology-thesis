import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class ModelBuilder {

    private BioSystem B;
    private String output_dir;
    private StringBuilder cell_equation;
    private HashMap<String, Integer> comp_number;       // key: compartmentId, value: progressive number
    private static final String indentation = "    ";   // 4 spaces used for indentation
    private static double cellVolume = 10e-12;

    public ModelBuilder(BioSystem B, String od) {
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

        sb_model.append("model Cell\n".concat(indent.repeat(2).concat("extends BioChem.Compartments.MainCompartment")));
        sb_model.append("(V(start=cell_V));\n\n");
        sb_model.append(indent.repeat(depth+1).concat("inner parameter BioChem.Units.Volume cell_V = " +cellVolume)+";\n\n");

        for(Compartment c : this.B.getCompartments()){
            sb_model.append(this.buildCompartmentModel(c, this.comp_number.get(c.getId()),
                    compartmentVolumePercentage, depth+1));
            sb_instance.append(this.buildCompartmentInstance(c, this.comp_number.get(c.getId()), depth+1));
        }

        if(this.cell_equation.toString().equals(indent.concat("equation\n\n"))) this.cell_equation.delete(0, this.cell_equation.length());

        return sb_model.toString()+sb_instance.toString()+"\n"+this.cell_equation.toString()+"\n"+indent+"end Cell;\n\n";
    }

    /**
     * Method used to declare all the compartments in Modelica as "CompartmentName c_i CompartmentId"
     * @param compartment the name of the compartment
     * @param compIndex progressive number used to declare the compartment
     * @param depth used for indentation purposes
     * @return a String comprised of Modelica code for the declaration of compartments
     */
    public String buildCompartmentInstance(Compartment compartment, int compIndex, int depth){

        String indent = indentation.repeat(depth);
        return indent.concat(
                ModelBuilder.toClassName(compartment.getName()).concat(
                        " c_".concat(
                                String.valueOf(compIndex).concat(
                                        " \"".concat(
                                                compartment.getId().concat("\";\n"))))));
    }

    /**
     * Method used to build the compartments in Modelica, handling the building of their species and reactions
     * @param compartment the compartment to be built
     * @param compIndex the progressive index used in the declaration of the compartment
     * @param cellVolumePercentage the volume of the compartment in proportion to the cell volume
     * @param depth used for indentation purposes
     * @return a string comprised of Modelica code handling a compartment
     */
    public String buildCompartmentModel(Compartment compartment, int compIndex,
                                        double cellVolumePercentage, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder(indent);

        //build Model compartment
        String name = ModelBuilder.toClassName(compartment.getName());
        sb.append("model ".concat(name.concat("\n")));
        sb.append(indent.concat("    extends BioChem.Compartments.Compartment"));
        sb.append("(V(start="+cellVolumePercentage+"*cell_V));\n\n");

        sb.append(indent.concat("    outer parameter BioChem.Units.Volume cell_V;\n\n"));

        sb.append(this.buildAllSpecies(compartment, depth+1));

        sb.append(this.buildAllReactions(compartment, compIndex, depth+1));

        sb.append(indent.concat("end ".concat(name.concat(";\n\n"))));

        return sb.toString();
    }

    public String buildAllReactions(Compartment compartment, int compIndex, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb_instance = new StringBuilder();
        StringBuilder sb_equation = new StringBuilder();
        String mid = indentation.repeat(depth-1)+"equation\n\n";

        for(SimpleReaction react : compartment.getReactions()){
            String instance = ModelBuilder.inferReactionType(react);
            if(instance == null) sb_instance.append(indent.concat("//WARNING: could not infer reaction type of ").concat(react.getId().concat("\n")));
            else{
                sb_instance.append(indent.concat(instance.concat((" \""+react.getName()+"\";\n"))));
                int s = react.getReactants().size(), p = 1/*, m = 1*/;
                for(CompartmentEdge ce : this.B.getCompEdges()){
                    if(ce.getTransport().getId().equals(react.getId())){
                        int comp_number = this.comp_number.get(ce.getCompDstId());
                        if(ce.getExternalReactant() != null){
                            this.cell_equation.append(
                                    buildConnectExternalReactant(comp_number, compIndex, react, ce, s, depth-1)
                            );
                            s--;
                        }
                        else if(ce.getExternalProduct() != null){
                            this.cell_equation.append(
                                    buildConnectExternalProduct(comp_number, compIndex, react, ce, p, depth-1)
                            );
                            p++;
                        }
                        else if(ce.getExternalModifier() != null){
                            this.cell_equation.append(
                                    buildConnectExternalModifier(comp_number, compIndex, react, ce, /*m,*/ depth-1)
                            );
                            //m++;
                        }
                    }
                }
                sb_equation.append(ModelBuilder.buildReactionEquation(react, compartment.getId(), s, p, /*m,*/ depth));
            }
        }
        sb_instance.append("\n");

        if(sb_equation.toString().equals("")) mid = "";
        return sb_instance.toString()+mid+sb_equation.toString();
    }

    private String buildConnectExternalReactant(int compNumber, int compIndex, SimpleReaction react, CompartmentEdge ce, int s, int depth) {
        String res = indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(compNumber).concat("."
                        .concat(ce.getExternalReactant().getId().concat(".n1, c_")
                                .concat(String.valueOf(compIndex).concat("."
                                        .concat(react.getId().concat(".s"))))))));
        if(react.getReactants().size() > 3 || react.getProducts().size() > 3) return res + "[".concat(String.valueOf(s).concat("]);\n"));
        else return res + String.valueOf(s).concat(");\n");
    }

    private String buildConnectExternalProduct(int compNumber, int compIndex, SimpleReaction react, CompartmentEdge ce, int p, int depth) {
        String res = indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(compNumber).concat("."
                        .concat(ce.getExternalProduct().getId().concat(".n1, c_")
                                .concat(String.valueOf(compIndex).concat("."
                                        .concat(react.getId().concat(".p"))))))));
        if(react.getReactants().size() > 3 || react.getProducts().size() > 3) return res + "[".concat(String.valueOf(p).concat("]);\n"));
        else return res + String.valueOf(p).concat(");\n");
    }

    private String buildConnectExternalModifier(int compNumber, int compIndex, SimpleReaction react, CompartmentEdge ce, /*int m,*/ int depth) {

        String type;
        if(((ComplexReaction) react).getModifierType(ce.getExternalModifier()) == ComplexReaction.ModifierType.NEGATIVE_REGULATOR) type = "i";
        else type = "a";
        String res = indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(compNumber).concat("."
                        .concat(ce.getExternalModifier().getId().concat(".n1, c_")
                                .concat(String.valueOf(compIndex).concat("."
                                        .concat(react.getId().concat(".".concat(type
                                                .concat("F1);\n"))))))))));
        if(react.isReversible()) res += indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(compNumber).concat("."
                        .concat(ce.getExternalModifier().getId().concat(".n1, c_")
                                .concat(String.valueOf(compIndex).concat("."
                                        .concat(react.getId().concat(".".concat(type
                                                .concat("B1);\n"))))))))));
        return res;
    }

    /**
     * Method used to assign a reaction to a proper Reaction class from the ones available from
     * the BioChem library, that is detect number of reactants and of products and kinetic type
     * WARNING: for now the method ignores all the ComplexReactions, that is all the reactions with
     * modifiers, and it assumes all the others follow Mass Action kinetic law
     * @param react the reaction
     * @return a String comprised of the declaration of the reaction
     * @see #createReactionInstance(SimpleReaction, boolean)
     */
    private static String inferReactionType(SimpleReaction react){

        String kinetics = "";
        String classname = "";
        String root;
        boolean multi = false;

        //kinetics = "";
        //assuming MassAction kinetics for all SimpleReactions
        //Note: BioChem has MassAction reactions for all combinations of [1,3] reactants/products

        if(react.getProducts().keySet().size() == 0){
            //TODO: Cerca una reazione con gli stessi reagenti ma con almeno un prodotto: se esiste, rendila ininfluente (non deve avvenire)
            return null;
        }
        if(react.getReactants().keySet().size() > 3 || react.getProducts().keySet().size() > 3){
            root = "Reactions.MassAction.";
            classname += "Mm";
            multi = true;
        }
        else {
            root = "BioChem.Reactions.MassAction.";
            switch (react.getReactants().keySet().size()) {
                case 1:
                    kinetics += "Uni";
                    classname += "U";
                    break;
                case 2:
                    kinetics += "Bi";
                    classname += "B";
                    break;
                case 3:
                    kinetics += "Tri";
                    classname += "T";
                    break;
            }
            switch (react.getProducts().keySet().size()) {
                case 1:
                    kinetics += "Uni.";
                    classname += "u";
                    break;
                case 2:
                    kinetics += "Bi.";
                    classname += "b";
                    break;
                case 3:
                    kinetics += "Tri.";
                    classname += "t";
                    break;
            }
        }
        if (react.isReversible()) {
            kinetics = "Reversible." + kinetics;
            classname += "r";
        } else {
            kinetics = "Irreversible." + kinetics;
            classname += "i";
        }
        if(react instanceof ComplexReaction){
            int pos = 0;
            int neg = 0;
            for(Species m : ((ComplexReaction) react).getModifiers().keySet()){
                ComplexReaction.ModifierType t = ((ComplexReaction) react).getModifierType(m);
                if(t != null){
                    if(t == ComplexReaction.ModifierType.CATALYST || t == ComplexReaction.ModifierType.POSITIVE_REGULATOR){
                        if((pos++) < 1) classname += "fa";
                        else return null;
                    }
                    else if(t == ComplexReaction.ModifierType.NEGATIVE_REGULATOR){
                        if((neg++) < 1) classname += "fi";
                        else return null;
                    }
                    if(react.isReversible()){
                        if(pos == 1) classname += "ba";
                        if(neg == 1) classname += "bi";
                    }
                }
            }
        }
        return root+kinetics+classname+" "+ ModelBuilder.createReactionInstance(react, multi);
    }

    private static String createReactionInstance(SimpleReaction react, boolean multi){

        Random r = new Random();
        StringBuilder sb = new StringBuilder(react.getId()+"(");
        double mean = 1e3;
        double std_dev = 1e3;

        sb.append("k1=".concat(String.valueOf(Math.abs(r.nextGaussian()*std_dev+mean))));
        if(react.isReversible()) sb.append(", k2=".concat(String.valueOf(Math.abs(r.nextGaussian()*std_dev+mean))));

        int sub = 1, prod = 1;
        for(Species s : react.getReactants().keySet()){
            if(react.getReactantStoich(s) > 1) sb.append(", nS".concat(String.valueOf(sub++).concat("=".concat(String.valueOf(react.getReactantStoich(s))))));
        }

        for(Species s : react.getProducts().keySet()){
            if(react.getProductStoich(s) > 1) sb.append(", nP".concat(String.valueOf(prod++).concat("=".concat(String.valueOf(react.getProductStoich(s))))));
        }

        if(multi){
            Iterator<Species> it_R = react.getReactants().keySet().iterator();
            sb.append(", dimS=".concat(String.valueOf(react.getReactants().size())));
            sb.append(", nS={".concat(String.valueOf(react.getReactantStoich(it_R.next()))));
            while(it_R.hasNext()){
                Species s = it_R.next();
                sb.append(",".concat(String.valueOf(react.getReactantStoich(s))));
            }
            sb.append("}");

            Iterator<Species> it_P = react.getProducts().keySet().iterator();
            sb.append(", dimP=".concat(String.valueOf(react.getProducts().size())));
            sb.append(", nP={".concat(String.valueOf(react.getProductStoich(it_P.next()))));
            while(it_P.hasNext()){
                Species s = it_P.next();
                sb.append(",".concat(String.valueOf(react.getProductStoich(s))));
            }
            sb.append("}");

        }
        return sb.toString()+")";
    }

    private static String buildReactionEquation(SimpleReaction reaction, String compId, int s, int p, /*int m,*/ int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        for(Species spec : reaction.getReactants().keySet()){
            if(spec.getCompartmentId().equals(compId)) {
                sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(reaction.getId().
                        concat(".s"))))));
                if(reaction.getReactants().size() > 3 || reaction.getProducts().size() > 3) sb.append("[".concat(String.valueOf(s--).concat("]);\n")));
                else sb.append(String.valueOf(s--).concat(");\n"));
            }
        }

        for(Species spec : reaction.getProducts().keySet()) {
            if (spec.getCompartmentId().equals(compId)) {
                sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(reaction.getId().
                        concat(".p"))))));
                if(reaction.getReactants().size() > 3 || reaction.getProducts().size() > 3) sb.append("[".concat(String.valueOf(p++).concat("]);\n")));
                else sb.append(String.valueOf(p++).concat(");\n"));
            }
        }

        if(reaction instanceof ComplexReaction){
            for(Species spec : ((ComplexReaction) reaction).getModifiers().keySet()) {
                if (spec.getCompartmentId().equals(compId)) {
                    String type;
                    if(((ComplexReaction) reaction).getModifierType(spec) == ComplexReaction.ModifierType.NEGATIVE_REGULATOR) type = "i";
                    else type = "a";
                    sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(reaction.getId().
                            concat(".".concat(type.concat("F1);\n"))))))));
                    if(reaction.isReversible()){
                        sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(reaction.getId().
                                concat(".".concat(type.concat("B1);\n"))))))));
                    }
                }
            }
        }

        return sb.toString()+"\n";
    }

    public String buildAllSpecies(Compartment compartment, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        double mean = 1e-12;
        double std_dev = 1e-14;

        for(Species s : compartment.getSpecies()){
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