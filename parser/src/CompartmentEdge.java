import java.util.ArrayList;

public class CompartmentEdge {

    private String compSrcId;
    private String compDstId;
    private SimpleReaction transport;
    private Species external_reactant;
    private Species external_product;
    private Species external_modifier;


    /**
     * Class representing a connection between compartments due to a transport reaction
     * @param compSrcId the id of the compartment from which the species gets transported
     * @param compDstId the id of the compartment to which the species gets transported
     * @param transport the transport reaction
     */
    public CompartmentEdge(String compSrcId, String compDstId,
                           SimpleReaction transport)
    {
        this.compSrcId = compSrcId;
        this.compDstId = compDstId;
        this.transport = transport;
    }

    public String getCompSrcId() {
        return compSrcId;
    }

    public String getCompDstId() {
        return compDstId;
    }

    public void setExternalReactant(Species s){ this.external_reactant = s; }

    public void setExternalProduct(Species s){ this.external_product = s; }

    public void setExternalModifier(Species s){ this.external_modifier = s; }

    public Species getExternalReactant() {
        return external_reactant;
    }

    public Species getExternalProduct() {
        return external_product;
    }

    public Species getExternalModifier() {
        return external_modifier;
    }

    public void setCompDstId(String id){ this.compDstId = id; }

    public SimpleReaction getTransport() {
        return transport;
    }

}
