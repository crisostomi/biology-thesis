import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class ConfigBuilder {

    private BioSystem B;
    private String kbPath;
    private String configPath;
    private Document kb;
    private Document config;
    private static final double MINAMOUNT = 0;
    private static final double MAXAMOUNT = 10e-6;
    private static final double MINREACTION = 0;
    private static final double MAXREACTION = 10e10;
    //public static HashMap<String, Integer> speciesIndex;

    /**
     * Class to build an XML carrying biological constraints information
     * @param b the biosystem built from sbml data
     * @param kbPath the output directory where to put the built XML
     */
    public ConfigBuilder(BioSystem b, String kbPath, String configDir) throws ParserConfigurationException, IOException, SAXException {
        B = b;
        this.kbPath = kbPath;
        this.configPath = configDir;

        File kb = new File(this.kbPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.kb = dBuilder.parse(kb);
        this.config = dBuilder.newDocument();

    }

    /**
     * Method to build the XML
     */
    public void buildKB() throws TransformerException {

        Node listOfSpecies = this.kb.getElementsByTagName("listOfSpecies").item(0);
        Node listOfReactions = this.kb.getElementsByTagName("listOfReactions").item(0);
        Node constraints = this.kb.getElementsByTagName("constraints").item(0);


        this.buildAllSpeciesConstraints(listOfSpecies);
        this.buildAllReactionsConstraints(listOfReactions);

        constraints.appendChild(listOfSpecies);
        constraints.appendChild(listOfReactions);

       // this.kb.appendChild(constraints);
        this.closeKB();
    }

    public void buildConfig() throws TransformerException {

        Node configuration = this.config.createElement("configuration");
        this.config.appendChild(configuration);
        Node listOfCompartments = this.config.createElement("listOfCompartments");
        Node listOfSpecies = this.config.createElement("listOfSpecies");
        Node listOfReactions = this.config.createElement("listOfReactions");
        configuration.appendChild(listOfCompartments);
        configuration.appendChild(listOfSpecies);
        configuration.appendChild(listOfReactions);

        /*ConfigBuilder.speciesIndex = new HashMap<>();
        int i = 1;
        for(Compartment c : this.B.getCompartments()){
            for(Species s : c.getSpecies()) speciesIndex.put(s.getId(), i++);
        }*/

        Element compartment, species, reaction;
        int i = 1, x, y, t;
        for (Compartment c: this.B.getCompartments()) {
            compartment = this.config.createElement("compartment");
            compartment.setAttribute("id", c.getId());
            listOfCompartments.appendChild(compartment);
            x = 1;
            y = 1;
            t = 1;
            for (Species s: c.getSpecies()) {
                species = this.config.createElement("species");
                species.setAttribute("id", s.getId());
                species.setAttribute("compartment", c.getId());
                species.setAttribute("bounds_index", String.valueOf(i++));
                species.setAttribute("init_index", String.valueOf(x++));
                listOfSpecies.appendChild(species);
            }
            for (SimpleReaction r: c.getReactions()) {
                reaction = r.isReversible() ? this.config.createElement("reversible") : this.config.createElement("irreversible");
                reaction.setAttribute("id", r.getId());
                reaction.setAttribute("compartment", c.getId());
                reaction.setAttribute("k1_index", String.valueOf(y++));
                if(r.isReversible()) reaction.setAttribute("k2_index", String.valueOf(t++));
                listOfReactions.appendChild(reaction);
            }
        }

        this.closeConfig();
    }

    private void buildAllSpeciesConstraints(Node listOfSpecies) {
        //Element listOfSpecies = this.document.createElement("listOfSpecies");
        for (Compartment c: this.B.getCompartments()) {
            for (Species s: c.getSpecies()) {
                if(!ConfigBuilder.isSpeciesInDocument(this.kb, s.getId()))
                    listOfSpecies.appendChild(this.buildSpeciesConstraint(s));
            }
        }
    }

    private void buildAllReactionsConstraints(Node listOfReactions) {
        //Element listOfReactions = this.document.createElement("listOfReactions");
        for (Compartment c: this.B.getCompartments()) {
            for (SimpleReaction r: c.getReactions()) {
                if(!ConfigBuilder.isReactionInDocument(this.kb, r.getId(), r.isReversible()))
                    listOfReactions.appendChild(this.buildReactionConstraint(r));
            }
        }
    }

    /**
     * Method to build a constraint for a single species, initially all blank
     * @param species the species of interest
     * @return a line of XML with the definition of the constraint for the species
     */
    private Element buildSpeciesConstraint(Species species) {
        Element constraint = this.kb.createElement("species");
        constraint.setAttribute("id", species.getId());
        constraint.setAttribute("name", species.getName());
        //constraint.setAttribute("index", String.valueOf(speciesIndex.get(species.getId())));

        constraint.setAttribute("minAmount", String.valueOf(MINAMOUNT));
        constraint.setAttribute("maxAmount", String.valueOf(MAXAMOUNT));
        constraint.setAttribute("initialAmount", "");

        return constraint;
    }

    /**
     * Method to build a constraint for a single reaction, initially all blank
     * @param reaction the reaction of interest
     * @return a line of XML with the definition of the constraint for the species
     */
    private Element buildReactionConstraint(SimpleReaction reaction) {
        String tagName = (reaction.isReversible()) ? "reversible" : "irreversible";
        Element constraint = this.kb.createElement(tagName);
        constraint.setAttribute("id", reaction.getId());
        constraint.setAttribute("name", reaction.getName());

        constraint.setAttribute("k1", "");
        constraint.setAttribute("min_k1", String.valueOf(MINREACTION));
        constraint.setAttribute("max_k1", String.valueOf(MAXREACTION));

        if (reaction.isReversible()) {
            constraint.setAttribute("k2", "");
            constraint.setAttribute("min_k2", String.valueOf(MINREACTION));
            constraint.setAttribute("max_k2", String.valueOf(MAXREACTION));
        }

        return constraint;
    }

    private static boolean isSpeciesInDocument(Document doc, String spec_id){

        NodeList species = doc.getElementsByTagName("species");
        for(int i = 0; i < species.getLength(); i++){
            if(species.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(spec_id)) return true;
        }
        return false;
    }

    private static boolean isReactionInDocument(Document doc, String react_id, boolean rev){

        NodeList reactions = rev ? doc.getElementsByTagName("reversible") : doc.getElementsByTagName("irreversible");
        for(int i = 0; i < reactions.getLength(); i++){
            if(reactions.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(react_id)) return true;
        }
        return false;
    }

    public static void trimWhitespace(Node node) {
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if(child.getNodeType() == Node.TEXT_NODE) {
                child.setTextContent(child.getTextContent().trim());
            }
            trimWhitespace(child);
        }
    }


    public void closeKB() throws TransformerException {

        this.kb.normalizeDocument();
        trimWhitespace(this.kb);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(this.kb);
        StreamResult streamResult = new StreamResult(new File(this.kbPath));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(domSource, streamResult);
    }

    public void closeConfig() throws TransformerException {

        this.config.normalizeDocument();
        trimWhitespace(this.config);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(this.config);
        StreamResult streamResult = new StreamResult(new File(this.configPath));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(domSource, streamResult);
    }
}
