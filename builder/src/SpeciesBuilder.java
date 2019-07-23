//import java.util.Random;

class SpeciesBuilder {

    private Species s;
    private static String indentation = "    ";

    public SpeciesBuilder(Species s){ this.s = s; }

    public String buildSpecies(Compartment compartment, int depth){

        String indent = indentation.repeat(depth);
        //StringBuilder sb = new StringBuilder();
        /*Random r = new Random();
        double mean = 1e-12;
        double std_dev = 1e-14;*/
        String res;
        if(s.isBoundary()) res = indent + "BioChem.Substances.BoundarySubstance ";//.concat(s.getId().concat("\""+s.getName()+"\";\n"))));
        else res = indent + "BioChem.Substances.Substance ";//
        //double init = Math.abs(r.nextGaussian()*std_dev+mean);
        res += s.getId() + "(n(start=".concat(")) \""+s.getName()+"\";\n");

        return res;
    }
}
