package jcq.graph.pack;

import java.util.ArrayList;

/**
 * Solver
 * 
 * Lit une instance de MinCoupeCircuit sur l'entrée standard
 * @see GraphReader
 * 
 * Vérifie si l'instance est vraie ou fausse
 * @see DiGraph.trouverTousLesCycles
 * 
 * Renvoie 0 si elle est vraie,
 * 1 sinon
 * 
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
 */
public class Solver {

    public static void main(String args[]) {

            GraphReader greader = new GraphReader();
            DiGraph instanceP = (DiGraph) greader.lire();
						greader.close();

            int mcc = minCoupeCircuit(instanceP.getValeurATester(), instanceP);
            System.exit(mcc);

    }

    public static int minCoupeCircuit(int k, DiGraph g) {
        g.trouverTousLesCycles();

        if (minCoupeCircuitRec(k, g)) {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean minCoupeCircuitRec(int k, DiGraph g) {
        if (k <= 0) {
            return false;
        }

        DiArete aMax = g.getAreteDePlusHautPoids();
        ArrayList<Cycle> cyclesSupprimes = new ArrayList<>();
        ArrayList<DiArete> aretesSupprimes = new ArrayList<>();

        while (aMax != null) {
            //on "supprime" l'arête de plus haut poids
            aMax.setSupprime(true);
            aretesSupprimes.add(aMax);
            //on "supprime" les cycles contenants aMax
            for (Cycle c : aMax.getCycles()) {
                if (!c.isSupprime()) {
                    c.setSupprime(true);
                    cyclesSupprimes.add(c);
                    //on décrémente le poids des arêtes contenues dans le cycle
                    for (DiArete a : c.getAretes()) {
                        a.moinsmoins();
                    }
                }
            }
            if (!g.possedeEncoreCycle()) {
                return true;
            } else {
                if (minCoupeCircuitRec(k - 1, g)) {
                    return true;
                }
            }

            // on annule la suppression des cycles
            for (Cycle c : cyclesSupprimes) {
                c.setSupprime(false);
                for (DiArete a : c.getAretes()) {
                    a.plusplus();
                }
                cyclesSupprimes = new ArrayList<>();
            }
            aMax = g.getAreteDePlusHautPoids();
        }

        // on annule la suppression des arêtes
        for (DiArete a : aretesSupprimes) {
            a.setSupprime(false);
        }

        return false;

    }
}
