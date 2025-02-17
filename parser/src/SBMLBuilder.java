import org.w3c.dom.*;
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

class SBMLBuilder {

    private Document document;      // it stores the sbml being built
    private String outputDir;

    /**
     * Class used to build the union SBML from SBML data
     * @param outputDir the path of the output directory of the union sbml
     * @throws ParserConfigurationException
     */
    SBMLBuilder(String outputDir) throws ParserConfigurationException{
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        this.document = documentBuilder.newDocument();
        this.outputDir = outputDir;
    }

    void buildRoot(Node root) {
        this.document.appendChild(this.document.importNode(root,false));
        Element model = this.document.createElement("model");
        model.setAttribute("id", "pathway_union");
        model.setAttribute("name", "Union of input pathways shown in Annotation");
        Element annotation = this.document.createElement("annotation");
        model.appendChild(annotation);
        model.appendChild(this.document.createElement("listOfCompartments"));
        model.appendChild(this.document.createElement("listOfSpecies"));
        model.appendChild(this.document.createElement("listOfReactions"));
        this.document.getFirstChild().appendChild(model);
    }

    void annotate(Node model){
        Node annotation = this.document.getElementsByTagName("annotation").item(0);
        Node model_tag = this.document.importNode(model, false);
        annotation.appendChild(model_tag);
    }

    void addCompartment(Node comp){
        this.document.getElementsByTagName("listOfCompartments").item(0).appendChild(this.document.importNode(comp, false));
    }

    void addSpecies(Node spec){
        this.document.getElementsByTagName("listOfSpecies").item(0).appendChild(this.document.importNode(spec, false));
    }

    void addReaction(Node react){
        Node r = this.document.importNode(react, false);
        this.document.getElementsByTagName("listOfReactions").item(0).appendChild(r);
        r.appendChild(this.document.createElement("listOfReactants"));
        r.appendChild(this.document.createElement("listOfProducts"));
        r.appendChild(this.document.createElement("listOfModifiers"));
    }

    void addReactantReference(Node sr, String reactid){
        NodeList reactions = this.document.getElementsByTagName("reaction");
        Node react = reactions.item(0);
        for(int i = 0; i < reactions.getLength(); i++){
            if(reactions.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(reactid)) react = reactions.item(i);
        }
        NodeList listOf = react.getChildNodes();
        Node reactants = listOf.item(0);
        for(int i = 0; i < listOf.getLength(); i++){
            if(listOf.item(i).getNodeName().equals("listOfReactants")) reactants = listOf.item(i);
        }
        reactants.appendChild(this.document.importNode(sr, false));
    }

    void addProductReference(Node sr, String reactid){
        NodeList reactions = this.document.getElementsByTagName("reaction");
        Node react = reactions.item(0);
        for(int i = 0; i < reactions.getLength(); i++){
            if(reactions.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(reactid)) react = reactions.item(i);
        }
        NodeList listOf = react.getChildNodes();
        Node products = listOf.item(0);
        for(int i = 0; i < listOf.getLength(); i++){
            if(listOf.item(i).getNodeName().equals("listOfProducts")) products = listOf.item(i);
        }
        products.appendChild(this.document.importNode(sr, false));
    }

    void addModifierReference(Node sr, String reactid){
        NodeList reactions = this.document.getElementsByTagName("reaction");
        Node react = reactions.item(0);
        for(int i = 0; i < reactions.getLength(); i++){
            if(reactions.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(reactid)) react = reactions.item(i);
        }
        NodeList listOf = react.getChildNodes();
        Node modifiers = listOf.item(0);
        for(int i = 0; i < listOf.getLength(); i++){
            if(listOf.item(i).getNodeName().equals("listOfModifiers")) modifiers = listOf.item(i);
        }
        modifiers.appendChild(this.document.importNode(sr, false));
    }

    void close() throws TransformerException {
        this.document.normalizeDocument();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(this.document);
        StreamResult streamResult = new StreamResult(new File(this.outputDir +"/biosystem.sbml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(domSource, streamResult);
    }

}
