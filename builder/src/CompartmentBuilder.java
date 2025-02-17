class CompartmentBuilder {

    private Compartment c;
    private static String indentation = "    ";
    private StringBuilder compartment_links;
    //static Document config;

    CompartmentBuilder(Compartment c){
        this.c = c;
        this.compartment_links = new StringBuilder();
    }

    /**
     * Method used to build the compartments in Modelica, handling the building of their species and reactions
     * @param cellVolumePercentage the volume of the compartment in proportion to the cell volume
     * @param depth used for indentation purposes
     * @return a string comprised of Modelica code handling a compartment
     */
    String buildCompartmentModel(double cellVolumePercentage, int depth){

        String indent = indentation.repeat(depth);
        StringBuilder sb = new StringBuilder(indent);

        //build Model compartment
        String name = CompartmentBuilder.toClassName(this.c.getName());
        sb.append("model ".concat(name.concat("\n")));
        sb.append(indent.concat("    extends BioChem.Compartments.Compartment"));
        sb.append("(V(start=".concat("vol_percentage").concat("*cell_V));\n\n"));

        sb.append(indent.concat("    parameter Real vol_percentage(min=0, max=1);\n"));
        sb.append(indent.concat("    outer parameter BioChem.Units.Volume cell_V;\n"));


        //sb.append(indent.concat("    parameter "));

        //sb.append(this.buildAllSpecies(compartment, depth+1));
        //SpeciesBuilder.buildKnowledge();
        StringBuilder species = new StringBuilder();
        //SpeciesBuilder.init_index = 1;
        for(Species s : this.c.getSpecies()) species.append(new SpeciesBuilder(s).buildSpecies(depth+1));
        //sb.append("\n");
        /*if(SpeciesBuilder.getNotAssigned() > 0) sb.append(indent.concat("    parameter BioChem.Units.AmountOfSubstance init["
                .concat(String.valueOf(SpeciesBuilder.getNotAssigned()).concat("];\n"))));*/
        if(this.c.getNumSpecies() > 0) sb.append(indent.concat("    parameter BioChem.Units.AmountOfSubstance init["
                .concat(String.valueOf(this.c.getNumSpecies())).concat("];\n")));

        //sb.append(this.buildAllReactions(compartment, compIndex, depth+1));
        ReactionBuilder rb;
        //ReactionBuilder.buildKnowledge();
        StringBuilder connect = new StringBuilder();
        StringBuilder reactions = new StringBuilder();
        for(SimpleReaction r : this.c.getReactions()){
            rb = new ReactionBuilder(r);
            reactions.append(rb.buildReaction(this.c, depth+1));
            connect.append(rb.equation.toString());
            this.compartment_links.append(rb.transport.toString());
        }
        /*if(ReactionBuilder.getNotAssignedK1() > 0) sb.append(indent.concat("    parameter BioChem.Units.ReactionCoefficient const_k1["
                .concat(String.valueOf(ReactionBuilder.getNotAssignedK1()).concat("];\n"))));
        if(ReactionBuilder.getNotAssignedK2() > 0) sb.append(indent.concat("    parameter BioChem.Units.ReactionCoefficient const_k2["
                .concat(String.valueOf(ReactionBuilder.getNotAssignedK2()).concat("];\n"))));*/
        if(this.c.getNumReactions() > 0){
            sb.append(indent.concat("    parameter BioChem.Units.ReactionCoefficient const_k1["
                    .concat(String.valueOf(this.c.getNumReactions()).concat("];\n"))));
            int rev = this.c.getNumReversibleReactions();
            if(rev > 0) sb.append(indent.concat("    parameter BioChem.Units.ReactionCoefficient const_k2["
                    .concat(String.valueOf(rev).concat("];\n"))));
        }

        sb.append("\n".concat(species.toString().concat("\n")));
        sb.append(reactions.toString().concat("\n"));
        if(!connect.toString().equals("")){
            sb.append("\n".concat(indent.concat("equation\n\n")));
            sb.append(connect.toString());
        }

        sb.append(indent.concat("end ".concat(name.concat(";\n\n"))));

        return sb.toString();
    }

    /**
     * Method used to declare all the compartments in Modelica as "CompartmentName c_i CompartmentId"
     * @param depth used for indentation purposes
     * @return a String comprised of Modelica code for the declaration of compartments
     */
    String buildCompartmentInstance(int depth){
        return indentation.repeat(depth) +
                CompartmentBuilder.toClassName(this.c.getName()) + " " + this.c.getId()+";\n";
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

    public StringBuilder getCompartmentLinks() {
        return compartment_links;
    }

    public void setCompartmentLinks(StringBuilder compLinks) {
        this.compartment_links = compLinks;
    }
}
