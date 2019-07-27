import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

public class ConstraintBuilder {

    private BioSystem B;
    private String outDir;
    private Document document;

    /**
     * Class to build an XML carrying biological constraints information
     * @param b the biosystem built from sbml data
     * @param outDir the output directory where to put the built XML
     */
    public ConstraintBuilder(BioSystem b, String outDir) throws ParserConfigurationException, IOException, SAXException {
        B = b;
        this.outDir = outDir;
        File conf = new File(this.outDir + "/config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.document = dBuilder.parse(conf);
    }

    /**
     * Method to build the XML
     * @throws IOException
     */
    public void build() throws ParserConfigurationException, IOException, TransformerException {

        Node listOfSpecies = this.document.getElementsByTagName("listOfSpecies").item(0);
        Node listOfReactions = this.document.getElementsByTagName("listOfReactions").item(0);

        this.buildAllSpeciesConstraints(listOfSpecies);
        this.buildAllReactionsConstraints(listOfReactions);
        /*Element constraints = this.document.createElement("constraints");

        constraints.appendChild(this.buildAllSpeciesConstraints());
        constraints.appendChild(this.buildAllReactionsConstraints());

        this.document.appendChild(constraints);*/
        this.close();
    }

    private void buildAllSpeciesConstraints(Node listOfSpecies) {
        //Element listOfSpecies = this.document.createElement("listOfSpecies");
        for (Compartment c: this.B.getCompartments()) {
            for (Species s: c.getSpecies()) {
                listOfSpecies.appendChild(this.buildSpeciesConstraint(s));
            }
        }
    }

    private void buildAllReactionsConstraints(Node listOfReactions) {
        //Element listOfReactions = this.document.createElement("listOfReactions");
        for (Compartment c: this.B.getCompartments()) {
            for (SimpleReaction r: c.getReactions()) {
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
        Element constraint = this.document.createElement("species");
        constraint.setAttribute("id", species.getId());
        constraint.setAttribute("name", species.getName());

        constraint.setAttribute("minAmount", "");
        constraint.setAttribute("maxAmount", "");
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
        Element constraint = this.document.createElement(tagName);
        constraint.setAttribute("id", reaction.getId());
        constraint.setAttribute("name", reaction.getName());

        constraint.setAttribute("k1", "");
        constraint.setAttribute("min_k1", "");
        constraint.setAttribute("max_k1", "");

        if (reaction.isReversible()) {
            constraint.setAttribute("k2", "");
            constraint.setAttribute("min_k2", "");
            constraint.setAttribute("max_k2", "");
        }

        return constraint;
    }


    public void close() throws TransformerException {
        this.document.normalizeDocument();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(this.document);
        StreamResult streamResult = new StreamResult(new File(this.outDir +"/config.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(domSource, streamResult);
    }
}
