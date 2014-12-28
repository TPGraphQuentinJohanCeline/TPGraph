package jcq.graph.pack;

/**
 * Vérificateur
 * 
 * Lit une instance de MinCoupeCircuit sur l'entrée standard
 * Lit un certificat sur l'entrée standard
 * @see GraphReader
 * 
 * Enlève de l'instance de MinCoupeCircuit les aretes du certificat
 * Vérifie que le nouveau graphe formé ne possède pas de cycle
 * @see DiGraph.possedeUnCycle()
 * 
 * Retourne 0 si l'instance de MinCoupeCircuit était vraie, 1 sinon
 * 
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
 */
public class Verificateur {
    
    public static void main(String args[]) {
        
            GraphReader greader = new GraphReader();
            DiGraph instanceP = (DiGraph) greader.lire();
            DiGraph certificat = (DiGraph) greader.lire();
            
            for (DiArete a : certificat.getAretes()) {
                instanceP.deleteArete(instanceP.findArete(a));
            }
            
            boolean resteDesCycles = instanceP.possedeUnCycle();
            if (resteDesCycles) { System.exit(1); }
            else { System.exit(0); }
            
    }
}
