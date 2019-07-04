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

public class SBMLBuilder {

    private Document document;
    private String output_dir;

    public SBMLBuilder(String out) throws ParserConfigurationException{
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        this.document = documentBuilder.newDocument();
        this.output_dir = out;
    }

    public void buildRoot(NodeList root) {
        this.document.appendChild(this.document.importNode(root.item(0),false));
        this.document.getFirstChild().appendChild(this.document.createElement("listOfCompartments"));
        this.document.getFirstChild().appendChild(this.document.createElement("listOfSpecies"));
        this.document.getFirstChild().appendChild(this.document.createElement("listOfReactions"));
    }

    public void addCompartment(Node comp){
        this.document.getElementsByTagName("listOfCompartments").item(0).appendChild(this.document.importNode(comp, false));
    }

    public void addSpecies(Node spec){
        this.document.getElementsByTagName("listOfSpecies").item(0).appendChild(this.document.importNode(spec, false));
    }

    public void addReaction(Node react){
        Node r = this.document.importNode(react, false);
        this.document.getElementsByTagName("listOfReactions").item(0).appendChild(r);
        r.appendChild(this.document.createElement("listOfReactants"));
        r.appendChild(this.document.createElement("listOfProducts"));
        r.appendChild(this.document.createElement("listOfModifiers"));
    }

    public void addReactantReference(Node sr, String reactid){
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

    public void addProductReference(Node sr, String reactid){
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

    public void addModifierReference(Node sr, String reactid){
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

    public void close() throws TransformerException {
        this.document.normalizeDocument();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(this.document);
        StreamResult streamResult = new StreamResult(new File(this.output_dir+"/biosystem.sbml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(domSource, streamResult);
    }

}
