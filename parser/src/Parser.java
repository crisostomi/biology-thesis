import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Parser {

    private String inputDir;
    private SBMLBuilder builder;

    private boolean ignore = false;
    private boolean select = false;

    private ArrayList<String> ignoreList;
    private ArrayList<String> selectList;

    /**
     * Class used to parse sbml data and pass it down to the SBMLBuilder in order to build the union SBML
     * @param inputDir the directory with the sbml data to be parsed
     * @param sbmlOut the directory where to build the union sbml
     * @throws ParserConfigurationException
     * @see SBMLBuilder
     */
    public Parser(String inputDir, String sbmlOut) throws ParserConfigurationException{
        this.inputDir = inputDir;
        this.builder = new SBMLBuilder(sbmlOut);
    }

    /**
     * Class used to parse sbml data and pass it down to the SBMLBuilder in order to build the union SBML
     * @param inputDir the directory with the sbml data to be parsed
     * @param sbmlOut the directory where to build the union sbml
     * @throws ParserConfigurationException
     * @see SBMLBuilder
     */
    public Parser(String inputDir, String sbmlOut,
                  ArrayList<String> ignoreList, ArrayList<String> selectList) throws ParserConfigurationException{
        this.inputDir = inputDir;
        this.builder = new SBMLBuilder(sbmlOut);
        this.ignoreList = ignoreList;
        this.selectList = selectList;

        if (ignoreList != null) {
            ignore = true;
        } else if (selectList != null){
            select = true;
        }
    }

    /**
     * Method to get out all the information of the input sbml carried by a set of compartments
     * @return the set of compartments in the sbml data
     * @see Compartment
     */
    public HashSet<Compartment> instantiateCompartments() {
        HashSet<Compartment> result = new HashSet<>();
        File dir = new File(this.inputDir);
        File[] dirList = dir.listFiles();
        if(dirList != null){
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            boolean first = true;
            try {
                DocumentBuilder docBuild = dbf.newDocumentBuilder();
                for (File sbml : dirList) {
                    //String id, name;
                    Document doc = docBuild.parse(sbml);
                    if(first){
                        this.builder.buildRoot(doc.getElementsByTagName("sbml").item(0));
                        first = false;
                    }
                    this.builder.annotate(doc.getElementsByTagName("model").item(0));
                    this.findCompartments(doc.getElementsByTagName("compartment"), result);
                    this.findSpecies(doc.getElementsByTagName("species"), result);
                    this.findReactions(doc.getElementsByTagName("reaction"), result);
                }
                this.builder.close();
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("Parsing failed. Returning with error");
                return null;
            }
        }
        else{
            System.out.println(this.inputDir + "is not a directory. Please specify a directory containing .sbml files derived from Reactome database");
        }
        return result;
    }

    private Compartment searchCompartmentById(HashSet<Compartment> set, String id){
        for(Compartment c : set){
            if(c.getId().equals(id)) return c;
        }
        return null;
    }

    private Species searchSpeciesInBioSystem(HashSet<Compartment> set, String species_id){
        for(Compartment c : set){
            Species s = c.searchSpecies(species_id);
            if(s != null) return s;
        }
        return null;
    }

    private void findCompartments(NodeList all_comps, HashSet<Compartment> result){
        String name, id;
        for(int i = 0; i < all_comps.getLength(); i++){
            name = all_comps.item(i).getAttributes().getNamedItem("name").getNodeValue();
            id = all_comps.item(i).getAttributes().getNamedItem("id").getNodeValue();
            if (ignore && this.ignoreList.contains(id)) continue;
            if (select && !this.selectList.contains(id)) continue;

            if(searchCompartmentById(result, id) == null){
                result.add(new Compartment(name, id));
                this.builder.addCompartment(all_comps.item(i));
            }
        }
    }

    private void findSpecies(NodeList all_spec, HashSet<Compartment> result){
        String comp, name, id;
        for(int i = 0; i < all_spec.getLength(); i++){
            comp = all_spec.item(i).getAttributes().getNamedItem("compartment").getNodeValue();

            if (ignore && this.ignoreList.contains(comp)) continue;
            if (select && !this.selectList.contains(comp)) continue;

            name = all_spec.item(i).getAttributes().getNamedItem("name").getNodeValue()/*.replaceAll("\\[.*\\]", "").trim()*/;
            id = all_spec.item(i).getAttributes().getNamedItem("id").getNodeValue();
            Compartment found = searchCompartmentById(result, comp);
            if(found != null && found.searchSpecies(id) == null){
                found.addSpecies(new Species(name, id, found.getId(), found.getName()));
                this.builder.addSpecies(all_spec.item(i));
            }
        }
    }

    private void findReactions(NodeList all_reacts, HashSet<Compartment> result){
        String comp, name, id;
        boolean reversible, complex;
        SimpleReaction r = new SimpleReaction()/*, r_inv = new SimpleReaction()*/;
        ComplexReaction comp_r = new ComplexReaction(r)/*, comp_r_inv = new ComplexReaction(r_inv)*/;

        for(int i = 0; i < all_reacts.getLength(); i++){
            complex = false;
            Node n = all_reacts.item(i);
            comp = n.getAttributes().getNamedItem("compartment").getNodeValue();

            if (ignore && this.ignoreList.contains(comp)) continue;
            if (select && !this.selectList.contains(comp)) continue;

            name = n.getAttributes().getNamedItem("name").getNodeValue();
            id = n.getAttributes().getNamedItem("id").getNodeValue();
            reversible = Boolean.parseBoolean(n.getAttributes().getNamedItem("reversible").getNodeValue());
            Compartment found = this.searchCompartmentById(result, comp);

            if(found != null && found.searchReaction(id) == null) this.builder.addReaction(n); //add node to sbml

            r = new SimpleReaction(name, id);
            r.setReversible(reversible);

            n = n.getFirstChild();
            String reference;
            while((n = n.getNextSibling()) != null){

                if(n.getNodeName().equals("listOfReactants")) { //find reactants
                    Node m = n.getFirstChild();
                    do {
                        if (m.getNodeName().equals("speciesReference")) {
                            this.builder.addReactantReference(m, id);
                            reference = m.getAttributes().getNamedItem("species").getNodeValue();
                            r.addReactant(searchSpeciesInBioSystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                            //if(reversible) r_inv.addProduct(searchSpeciesInBiosystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                        }
                    } while ((m = m.getNextSibling()) != null);
                }

                else if(n.getNodeName().equals("listOfProducts")){ //find products
                    Node m = n.getFirstChild();
                    do {
                        if (m.getNodeName().equals("speciesReference")) {
                            this.builder.addProductReference(m, id);
                            reference = m.getAttributes().getNamedItem("species").getNodeValue();
                            r.addProduct(searchSpeciesInBioSystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                            //if(reversible) r_inv.addReactant(searchSpeciesInBiosystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                        }
                    } while ((m = m.getNextSibling()) != null);
                }

                else if(n.getNodeName().equals("listOfModifiers")){ //find modifiers
                    Node m = n.getFirstChild();
                    String type;
                    do {
                        if (m.getNodeName().equals("modifierSpeciesReference")) {
                            complex = true;
                            this.builder.addModifierReference(m, id);
                            reference = m.getAttributes().getNamedItem("species").getNodeValue();
                            type = m.getAttributes().getNamedItem("id").getNodeValue();
                            comp_r = new ComplexReaction(r);
                            if(type.matches(".*catalyst.*")) comp_r.addModifier(searchSpeciesInBioSystem(result, reference), ComplexReaction.ModifierType.CATALYST);
                            else if(type.matches(".*positiveregulator.*")) comp_r.addModifier(searchSpeciesInBioSystem(result, reference), ComplexReaction.ModifierType.POSITIVE_REGULATOR);
                            else if(type.matches(".*negativeregulator.*")) comp_r.addModifier(searchSpeciesInBioSystem(result, reference), ComplexReaction.ModifierType.NEGATIVE_REGULATOR);
                        }
                    } while ((m = m.getNextSibling()) != null);

                }
            }

            SimpleReaction reac_check = (complex) ? comp_r : r;

            if (!checkReaction(reac_check)) continue;

            if(found != null && found.searchReaction(id) == null){ //add reaction to compartment
                if(complex) found.addReaction(comp_r); //if(reversible) found.addReaction(comp_r_inv);
                else found.addReaction(r); //if(reversible) found.addReaction(r_inv);
            }
        }
    }

    /**
     * @param r the reaction
     * @return whether a reaction has been correctly built
     */
    private boolean checkReaction(SimpleReaction r) {

        for (Species s: r.getProducts().keySet()) {
            if (s == null) return false;
        }

        for (Species s: r.getReactants().keySet()) {
            if (s == null) return false;
        }

        if (r instanceof ComplexReaction) {
            ComplexReaction comp_r = (ComplexReaction) r;

            for (Species s: comp_r.getModifiers().keySet()) {
                if (s == null) return false;
            }
        }

        return true;
    }

}
