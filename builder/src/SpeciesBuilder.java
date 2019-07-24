//import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

class SpeciesBuilder {

    private Species s;
    private static String indentation = "    ";
    private static HashMap<String, String> initialAmounts;
    static Document knowledge;
    static int not_assigned;

    SpeciesBuilder(Species s){ this.s = s; }

    String buildSpecies(Compartment compartment, int depth){

        String indent = indentation.repeat(depth);
        //StringBuilder sb = new StringBuilder();
        /*Random r = new Random();
        double mean = 1e-12;
        double std_dev = 1e-14;*/

        String res;
        if(s.isBoundary()) res = indent + "BioChem.Substances.BoundarySubstance ";//.concat(s.getId().concat("\""+s.getName()+"\";\n"))));
        else res = indent + "BioChem.Substances.Substance ";//

        String init = "";
        if(SpeciesBuilder.initialAmounts.containsKey(this.s.getId())){
            init = SpeciesBuilder.initialAmounts.get(this.s.getId());
        }

        if(init.equals("")){
            SpeciesBuilder.not_assigned++;
            init = "init["+SpeciesBuilder.not_assigned+"]";
        }

        res += s.getId() + "(n(start=".concat(init.concat(")) \""+s.getName()+"\";\n"));

        return res;
    }

    static void buildKnowledge(){

        SpeciesBuilder.initialAmounts = new HashMap<>();
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
    }

}