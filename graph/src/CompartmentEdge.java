public class CompartmentEdge {
    private String compSrcId;
    private String compDstId;
    private SimpleReaction transport;
    private Species speciesSrc;
    private Species speciesDst;

    /**
     * Class representing a connection between compartments due to a transport reaction
     * @param compSrcId the id of the compartment from which the species gets transported
     * @param compDstId the id of the compartment to which the species gets transported
     * @param transport the transport reaction
     * @param speciesSrc the species being moved in the source compartment
     * @param speciesDst the species being moved in the destination compartment
     */
    public CompartmentEdge(String compSrcId, String compDstId,
                           SimpleReaction transport,
                           Species speciesSrc, Species speciesDst)
    {
        this.compSrcId = compSrcId;
        this.compDstId = compDstId;
        this.transport = transport;
        this.speciesSrc = speciesSrc;
        this.speciesDst = speciesDst;
    }

    public String getCompSrcId() {
        return compSrcId;
    }

    public String getCompDstId() {
        return compDstId;
    }

    public SimpleReaction getTransport() {
        return transport;
    }

    public Species getSpeciesSrc() {
        return speciesSrc;
    }

    public Species getSpeciesDst() {
        return speciesDst;
    }
}
