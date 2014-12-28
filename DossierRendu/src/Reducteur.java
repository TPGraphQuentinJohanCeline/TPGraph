package jcq.graph.pack;

/**
 * Réducteur
 * 
 * Lit une instance de CouvertureDesAretes sur l'entrée standard
 * @see GraphReader
 * 
 * Transforme cette instance en une instance de MinCoupeCircuit 
 * ayant la même réponse
 * Affiche l'instance obtenue dans la sortie standard
 * 
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
 */
public class Reducteur {
    
    public static void main(String args[]) {
        
            GraphReader greader = new GraphReader();
            Graph instanceCA = (Graph) greader.lire();
            
            Integer nbNoeuds = instanceCA.nbNoeuds();
            
            DiGraph instanceMin = new DiGraph(2*nbNoeuds);
            instanceMin.setValeurATester(instanceCA.getValeurATester());
            
            for (int i = 0; i < nbNoeuds; i++) {
                instanceMin.makeArete(2*i, 2*i+1, 0);
            }
            
            for (Arete arete : instanceCA.getAretes()) {
                instanceMin.makeArete(2*arete.getSrc().getId() + 1, 2*arete.getDest().getId(),0);
                instanceMin.makeArete(2*arete.getDest().getId() + 1, 2*arete.getSrc().getId(),0);
            }
            
            System.out.println(instanceMin.toString());
        
        System.exit(0);
    }
}
