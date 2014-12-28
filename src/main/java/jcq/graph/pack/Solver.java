package jcq.graph.pack;

import java.util.ArrayList;

/**
 *
 */
public class Solver {

    public static int minCoupeCircuit(int k, DiGraph g){
        g.trouverTousLesCycles();

        if(minCoupeCircuitRec(k, g))
            return 0;
        else
            return 1;
    }

    private static boolean minCoupeCircuitRec(int k, DiGraph g){
        if(k <= 0) return false;

        DiArete aMax = g.getAreteDePlusHautPoids();
        ArrayList<Cycle> cyclesSupprimes = new ArrayList<>();
        ArrayList<DiArete> aretesSupprimes = new ArrayList<>();

        while(aMax != null){
            //on "supprime" l'arête de plus haut poids
            aMax.setSupprime(true);
            aretesSupprimes.add(aMax);
            //on "supprime" les cycles contenants aMax
            for(Cycle c : aMax.getCycles()){
                if(!c.isSupprime()) {
                    c.setSupprime(true);
                    cyclesSupprimes.add(c);
                    //on décrémente le poids des arêtes contenues dans le cycle
                    for (DiArete a : c.getAretes()) {
                        a.moinsmoins();
                    }
                }
            }
            if(!g.possedeEncoreCycle()){
                return true;
            }
            else{
                if(minCoupeCircuitRec(k-1, g)) return true;
            }

            // on annule la suppression des cycles
            for(Cycle c : cyclesSupprimes){
                c.setSupprime(false);
                for(DiArete a : c.getAretes()){
                    a.plusplus();
                }
                cyclesSupprimes = new ArrayList<>();
            }
            aMax = g.getAreteDePlusHautPoids();
        }

        // on annule la suppression des arêtes
        for(DiArete a : aretesSupprimes){
            a.setSupprime(false);
        }

        return false;


        /* il n'y a plus de cycles */
        /*
        if(aMax.getPoids() <= 0) return true;

        //on "supprime" l'arête de plus haut poids
        for(Cycle c : aMax.getCycles()){
        if(!c.isSupprime()) {
        c.setSupprime(true);
        cyclesSupprimes.add(c);
        for (DiArete a : c.getAretes()) {
        a.moinsmoins();
        }
        }
        }

        if(!g.possedeEncoreCycle()){
        return true;
        }
        else {
        for (DiArete a : g.getAretes()) {
        if (a.getPoids() > 0) {
        if (minCoupeCircuitRec(k - 1, g)) return true;
        }
        }
        // on annule tous les changements effectués
        for(Cycle c : cyclesSupprimes){
        c.setSupprime(false);
        for(DiArete a : c.getAretes()){
        a.plusplus();
        }
        }
        return false;
        }
        */
    }
}
