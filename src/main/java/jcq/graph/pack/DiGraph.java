/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcq.graph.pack;

import java.util.ArrayList;

/**
 *
 * @author celine
 */
public class DiGraph extends AbstractGraph {

    //Toutes les aretes du graphe
    protected ArrayList<DiArete> aretes;
    //Les aretes rangées par sommet
    protected ArrayList<ArrayList<DiArete>> aretesDe;
    //Les cycles du graphe
    private ArrayList<Cycle> cycles;

    public DiGraph(int taille) {
        super(taille);
        aretes = new ArrayList<>();
    }
    
    public void makeArete(Integer id_src, Integer id_dest, Integer poids) {
        aretes.add(new DiArete(noeuds.get(id_src),noeuds.get(id_dest),poids));
    }
    
    //Dès qu'on en a besoin, on range les arêtes par sommet une fois pour toute
    private void voisins() {
        aretesDe = new ArrayList<>();
        
        for (int i = 0; i < nbNoeuds(); i++) {
            aretesDe.add(new ArrayList<DiArete>());
        }
        
        for (DiArete arete : aretes) {
            aretesDe.get(arete.getSrc().getId()).add(arete);
        }
        
    }
    
    //Pour vérifier s'il y a au moins un cycle dans le graphe :
        //On effectue un parcours en profondeur,
        //Si on retombe ne serait-ce qu'une fois sur un
        //noeud déjà vu, c'est qu'il y a un cycle.
    
    public boolean possedeUnCycles() {
        voisins();
        boolean vu[] = new boolean[nbNoeuds()];
        for (int i = 0; i < nbNoeuds(); i++) { vu[i] = false; }
        
        boolean possedeUnCycle = false;
                    
        int premierNonVu = premierNonVu(vu);
        while (premierNonVu(vu) != -1) {
            vu[premierNonVu] = true;
            if ( possedeUnCycleRec(noeuds.get(premierNonVu), vu) ) 
                return true;
        }
        
        return false;
    }
    
    public boolean possedeUnCycleRec(Noeud n, boolean[] vu) {
        for (DiArete a : aretesDe.get(n.getId())) {
            if (!vu[a.getDest().getId()]) {
                vu[a.getDest().getId()] = true;
                return possedeUnCycleRec(a.getDest(),vu);
            }
            return true;
        }
        return false;
    }
    
    //Pour récupérer tous les cycles
        //On effectue là encore un parcours en profondeur
        //On doit pendant le parcours mémoriser le chemin courant
        //afin d'en extraire ensuite un cycle dans le cas
        //où on revient sur un noeud déjà vu.
    
    public void trouverTousLesCycles() {
        voisins();
        boolean vu[] = new boolean[nbNoeuds()];
        for (int i = 0; i < nbNoeuds(); i++) { vu[i] = false; }

        //Pour mémoriser comment revenir au sommet initial d'un cycle
        DiArete suiteAretes[] = new DiArete[nbNoeuds()];
        //Pour mémoriser tous les cycles trouvés
        cycles = new ArrayList<>();

        int premierNonVu = premierNonVu(vu);
        while (premierNonVu(vu) != -1) {
            vu[premierNonVu] = true;
            //cycle à mémoriser.
            Cycle cycle = new Cycle(nbNoeuds());
            trouverTousLesCyclesRec(noeuds.get(premierNonVu),vu, cycle, suiteAretes);
        }
        
    }
    
    public void trouverTousLesCyclesRec(
            Noeud n,
            boolean[] vu, 
            Cycle cycle, 
            DiArete[] suiteAretes) {
        
        for (DiArete a : aretesDe.get(n.getId())) {
            if (!vu[a.getDest().getId()]) {
                vu[a.getDest().getId()] = true;
                //On mémorise le chemin actuel et on continue
                Cycle cycleCourant = new Cycle(cycle, a, nbNoeuds());
                trouverTousLesCyclesRec(a.getDest(),vu,cycleCourant,suiteAretes);
            }
            else {
                //On se souvient du chemin qui permet de revenir en arrière
                suiteAretes[n.getId()] = a;
                
                Cycle cycleCourant = new Cycle(cycle, a, nbNoeuds());
                
                //On revient en arrière pour terminer le cycle.
                DiArete retour = suiteAretes[a.getDest().getId()];
                while (retour != null) {
                    cycleCourant.ajoutArete(retour);
                    retour = suiteAretes[retour.getDest().getId()];
                }
                
                //On extrait le cycle du chemin
                cycleCourant.extraire();
                
                cycles.add(cycleCourant);
            }
        }
        
    }
    
    
    //Utilitaires pour les parcours en profondeur
    private int premierNonVu(boolean[] vu) {
        for (int i = 0; i < vu.length; i++) {
            if (!vu[i]) { return i; }
        }
        return -1;
    }
    
    @Override
    public String toString() {
        String retour = "Digraph {\n";
        retour += listeDeNoeuds();
        for (DiArete arete : aretes) {
            retour += arete.toString() + "\n";
        }
        retour += "}";
        return retour;
    }

}
