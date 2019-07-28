class SpeciesBuilder {

    private Species s;
    private static String indentation = "    ";
    static int init_index = 1;
    //private static HashMap<String, String> initialAmounts;
    //private static int not_assigned;
    //static Document knowledge;

    SpeciesBuilder(Species s){ this.s = s; }

    String buildSpecies(int depth){

        String indent = indentation.repeat(depth);
        //StringBuilder sb = new StringBuilder();
        /*Random r = new Random();
        double mean = 1e-12;
        double std_dev = 1e-14;*/

        String res;
        if(s.isBoundary()) res = indent + "BioChem.Substances.BoundarySubstance ";//.concat(s.getId().concat("\""+s.getName()+"\";\n"))));
        else res = indent + "BioChem.Substances.Substance ";//

        /*String init = "";
        if(SpeciesBuilder.initialAmounts.containsKey(this.s.getId())){
            init = SpeciesBuilder.initialAmounts.get(this.s.getId());
        }

        if(init.equals("")) init = "init["+(++SpeciesBuilder.not_assigned)+"]";*/

        res += s.getId() + "(n(start=init[" + (SpeciesBuilder.init_index++) + "])) \""+s.getName()+"\";\n";

        return res;
    }

    /*static void buildKnowledge(){

        SpeciesBuilder.initialAmounts = new HashMap<>();
        SpeciesBuilder.resetNotAssigned();
        //Node listOfSpecies = knowledge.getElementsByTagName("listOfSpecies").item(0);
        NodeList children = knowledge.getElementsByTagName("listOfSpecies").item(0).getChildNodes();
        String init, id;
        //Node n;
        for(int i = 0; i < children.getLength(); i++){
            if (children.item(i).getTextContent().matches("\n\\s*")) continue;

            id = children.item(i).getAttributes().getNamedItem("id").getNodeValue();
            init = children.item(i).getAttributes().getNamedItem("initialAmount").getNodeValue();
            SpeciesBuilder.initialAmounts.put(id, init);
        }
    }*/

    /*static int getNotAssigned(){ return SpeciesBuilder.not_assigned; }

    static void resetNotAssigned(){ SpeciesBuilder.not_assigned = 0; }*/

}