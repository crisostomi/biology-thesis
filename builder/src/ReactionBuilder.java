import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

class ReactionBuilder {

    private SimpleReaction r;
    private static String indentation = "    ";
    private static HashSet<CompartmentEdge> compEdges;
    private static HashMap<String, Integer> comp_number;
    StringBuilder transport;
    StringBuilder equation;


    ReactionBuilder(SimpleReaction r){
        this.r = r;
        this.transport = new StringBuilder();
        this.equation = new StringBuilder();
    }

    String buildReaction(Compartment compartment, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb_instance = new StringBuilder();
        //StringBuilder sb_equation = new StringBuilder();
        //String mid = indentation.repeat(depth-1)+"equation\n\n";

        String instance = this.inferReactionType(this.r);
        if(instance == null) sb_instance.append(indent.concat("//WARNING: could not infer reaction type of ").concat(this.r.getId().concat("\n")));
        else{
            sb_instance.append(indent.concat(instance.concat((" \""+this.r.getName()+"\";\n"))));
            this.equation.append(this.buildReactionEquation(this.r, compartment.getId(), /*s, p, m,*/ depth));
        }

        //if(sb_equation.toString().equals("")) mid = "";
        return sb_instance.toString()/*+mid+sb_equation.toString()*/;
    }

    private String buildReactionEquation(SimpleReaction reaction, String compId, /*int s, int p, int m,*/ int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder();

        int s = 1, p = 1;

        for(Species spec : reaction.getReactants().keySet()){
            if(spec.getCompartmentId().equals(compId)) {
                sb.append(indent.concat("connect(".concat(spec.getId().concat(".n1, ".concat(reaction.getId().
                        concat(".s"))))));
                if(reaction.getReactants().size() > 3 || reaction.getProducts().size() > 3) sb.append("[".concat(String.valueOf(s++).concat("]);\n")));
                else sb.append(String.valueOf(s++).concat(");\n"));
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

        this.transport.append(this.buildTransport(compId, s, p, depth));

        if(!sb.toString().equals("")) sb.append("\n");
        return sb.toString();
    }

    private String buildTransport(String compId, int s, int p, int depth){

        StringBuilder sb = new StringBuilder(/*indentation.repeat(depth)*/);

        int src_number = ReactionBuilder.comp_number.get(compId);
        for(CompartmentEdge ce : ReactionBuilder.getCompEdges()){
            if(ce.getTransport().getId().equals(this.r.getId())){

                int dst_number = ReactionBuilder.comp_number.get(ce.getCompDstId());
                if(ce.getExternalReactant() != null){
                    sb.append(
                            this.buildConnectExternalReactant(src_number, dst_number, this.r, ce, s, depth-1)
                    );
                    s++;
                }
                else if(ce.getExternalProduct() != null){
                    sb.append(
                            this.buildConnectExternalProduct(src_number, dst_number, this.r, ce, p, depth-1)
                    );
                    p++;
                }
                else if(ce.getExternalModifier() != null){
                    sb.append(
                            this.buildConnectExternalModifier(src_number, dst_number, this.r, ce, /*m,*/ depth-1)
                    );
                    //m++;
                }
            }
        }
        return sb.toString();
    }

    private String buildConnectExternalReactant(int srcIndex, int dstIndex, SimpleReaction react, CompartmentEdge ce, int s, int depth) {
        String res = indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(srcIndex).concat("."
                        .concat(ce.getExternalReactant().getId().concat(".n1, c_")
                                .concat(String.valueOf(dstIndex).concat("."
                                        .concat(react.getId().concat(".s"))))))));
        if(react.getReactants().size() > 3 || react.getProducts().size() > 3) return res + "[".concat(String.valueOf(s).concat("]);\n"));
        else return res + String.valueOf(s).concat(");\n");
    }

    private String buildConnectExternalProduct(int srcIndex, int dstIndex, SimpleReaction react, CompartmentEdge ce, int p, int depth) {
        String res = indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(srcIndex).concat("."
                        .concat(ce.getExternalProduct().getId().concat(".n1, c_")
                                .concat(String.valueOf(dstIndex).concat("."
                                        .concat(react.getId().concat(".p"))))))));
        if(react.getReactants().size() > 3 || react.getProducts().size() > 3) return res + "[".concat(String.valueOf(p).concat("]);\n"));
        else return res + String.valueOf(p).concat(");\n");
    }

    private String buildConnectExternalModifier(int srcIndex, int dstIndex, SimpleReaction react, CompartmentEdge ce, /*int m,*/ int depth) {

        String type;
        if(((ComplexReaction) react).getModifierType(ce.getExternalModifier()) == ComplexReaction.ModifierType.NEGATIVE_REGULATOR) type = "i";
        else type = "a";
        String res = indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(srcIndex).concat("."
                        .concat(ce.getExternalModifier().getId().concat(".n1, c_")
                                .concat(String.valueOf(dstIndex).concat("."
                                        .concat(react.getId().concat(".".concat(type
                                                .concat("F1);\n"))))))))));
        if(react.isReversible()) res += indentation.repeat(depth)
                .concat("connect(c_".concat(String.valueOf(srcIndex).concat("."
                        .concat(ce.getExternalModifier().getId().concat(".n1, c_")
                                .concat(String.valueOf(dstIndex).concat("."
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
    private String inferReactionType(SimpleReaction react){

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
        return root+kinetics+classname+" "+ this.createReactionInstance(react, multi);
    }

    private String createReactionInstance(SimpleReaction react, boolean multi){

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

    static HashSet<CompartmentEdge> getCompEdges() {
        return compEdges;
    }

    static void setCompEdges(HashSet<CompartmentEdge> compEdges) {
        ReactionBuilder.compEdges = compEdges;
    }

    public static HashMap<String, Integer> getCompNumber() {
        return comp_number;
    }

    public static void setCompNumber(HashMap<String, Integer> comp_number) {
        ReactionBuilder.comp_number = comp_number;
    }
}
