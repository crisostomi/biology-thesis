import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.HashSet;

public class Parser {

    private String filepath;
    private SBMLBuilder builder;

    public Parser(String fp, String sbml_out) throws ParserConfigurationException{
        this.filepath = fp;
        this.builder = new SBMLBuilder(sbml_out);
    }

    public HashSet<Compartment> instantiateCompartments() {
        HashSet<Compartment> result = new HashSet<>();
        File dir = new File(this.filepath);
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
                        this.builder.buildRoot(doc.getElementsByTagName("sbml"));
                        first = false;
                    }

                    this.findCompartments(doc.getElementsByTagName("compartment"), result);


                    this.findSpecies(doc.getElementsByTagName("species"), result);


                    this.findReactions(doc.getElementsByTagName("reaction"), result);

                }
                this.builder.close();
            }
            catch(Exception e){
                System.out.println("Parsing failed. Returning with error");
                return null;
            }
        }
        else{
            System.out.println(this.filepath + "is not a directory. Please specify a directory containing .sbml files derived from Reactome database");
        }
        return result;
    }

    private Compartment searchCompartmentById(HashSet<Compartment> set, String id){
        for(Compartment c : set){
            if(c.getId().equals(id)) return c;
        }
        return null;
    }

    private Species searchSpeciesInBiosystem(HashSet<Compartment> set, String species_id){
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
            name = all_spec.item(i).getAttributes().getNamedItem("name").getNodeValue().replaceAll("\\[.*\\]", "").trim();
            id = all_spec.item(i).getAttributes().getNamedItem("id").getNodeValue();
            Compartment found = searchCompartmentById(result, comp);
            if(found != null && found.searchSpecies(id) == null){
                found.addSpecies(new Species(name, id));
                this.builder.addSpecies(all_spec.item(i));
            }
        }
    }

    private void findReactions(NodeList all_reacts, HashSet<Compartment> result){
        String comp, name, id;
        boolean reversible;
        Reaction r, r_inv;

        for(int i = 0; i < all_reacts.getLength(); i++){
            Node n = all_reacts.item(i);
            comp = n.getAttributes().getNamedItem("compartment").getNodeValue();
            name = n.getAttributes().getNamedItem("name").getNodeValue();
            id = n.getAttributes().getNamedItem("id").getNodeValue();
            reversible = Boolean.parseBoolean(n.getAttributes().getNamedItem("reversible").getNodeValue());
            Compartment found = this.searchCompartmentById(result, comp);
            if(found != null && found.searchReaction(id) == null) this.builder.addReaction(n);
            if(reversible) {
                r = new Reaction(name, id + "_1");
                r_inv = new Reaction(name, id + "_2");
            }
            else{
                r = new Reaction(name, id);
                r_inv = null;
            }

            n = n.getFirstChild();
            String reference;
            while((n = n.getNextSibling()) != null){

                if(n.getNodeName().equals("listOfReactants")) { //find reactants
                    Node m = n.getFirstChild();
                    do {
                        if (m.getNodeName().equals("speciesReference")) {
                            this.builder.addReactantReference(m, id);
                            reference = m.getAttributes().getNamedItem("species").getNodeValue();
                            r.addReactant(searchSpeciesInBiosystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                            if(reversible) r_inv.addProduct(searchSpeciesInBiosystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                        }
                    } while ((m = m.getNextSibling()) != null);
                }

                else if(n.getNodeName().equals("listOfProducts")){ //find products
                    Node m = n.getFirstChild();
                    do {
                        if (m.getNodeName().equals("speciesReference")) {
                            this.builder.addProductReference(m, id);
                            reference = m.getAttributes().getNamedItem("species").getNodeValue();
                            r.addProduct(searchSpeciesInBiosystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                            if(reversible) r_inv.addReactant(searchSpeciesInBiosystem(result, reference), Integer.parseInt(m.getAttributes().getNamedItem("stoichiometry").getNodeValue()));
                        }
                    } while ((m = m.getNextSibling()) != null);
                }

                else if(n.getNodeName().equals("listOfModifiers")){ //find modifiers
                    Node m = n.getFirstChild();
                    do {
                        if (m.getNodeName().equals("modifierSpeciesReference")) {
                            this.builder.addModifierReference(m, id);
                            reference = m.getAttributes().getNamedItem("species").getNodeValue();
                            r.addModifier(searchSpeciesInBiosystem(result, reference));
                            if(reversible) r_inv.addModifier(searchSpeciesInBiosystem(result, reference));
                        }
                    } while ((m = m.getNextSibling()) != null);

                }
            }
            if(found != null && found.searchReaction(id) == null) found.addReaction(r);
        }
    }

}
