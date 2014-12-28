package jcq.graph.pack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * DiGraph
 *
 * @see AbstractGraph
 * Représente un graph orienté
 * Méthodes importantes :
 *  possedeUnCycle : algo polynomial permettant de savoir si le graph 
 * possède ou non un cycle
 *  rouverTousLesCycles : algo exponentiel permettant de trouver la liste de
 * tous les cycles du graphe (utilisé ensuite par le solver)
 *
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
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
    
    public ArrayList<DiArete> getAretes() {
        return aretes;
    }
    
    public void makeArete(Integer id_src, Integer id_dest, Integer poids) {
        aretes.add(new DiArete(noeuds.get(id_src),noeuds.get(id_dest),poids));
    }
    
    public DiArete findArete(DiArete _a) {
        for (DiArete a : aretes) {
            if (a.equals(_a)) {
                return a;
            }
        }
        return null;
    }
    
    public void deleteArete(DiArete a) {
        aretes.remove(a);
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
    
    public boolean possedeUnCycle() {
        voisins();
        int d[] = new int[nbNoeuds()];
        for (int i = 0; i < nbNoeuds(); i++) { d[i] = 0; }
        Stack<Noeud> atraiter = new Stack<>();
        int nbsommets = 0;
        
        for (DiArete a : aretes) {
            d[a.getDest().getId()]++;
        }
        
        for (Noeud n : noeuds) {
            if (d[n.getId()] == 0) {
                atraiter.push(n);
                nbsommets++;
            }
        }
        
        while (!atraiter.isEmpty()) {
            Noeud n = atraiter.pop();
            for (DiArete a : aretesDe.get(n.getId())) {
                d[a.getDest().getId()]--;
                if (d[a.getDest().getId()] == 0) {
                    atraiter.push(a.getDest());
                    nbsommets++;
                }
            }
        }
        
        return (nbsommets != nbNoeuds());
        
        
    }

    // Parcourt le tableau des cycles et regarde s'il existe encore un cycle
    // plus rapide pour le solver que possedeUnCycle()
    public boolean possedeEncoreCycle(){
        for(Cycle c : cycles){
            if(!c.isSupprime()) return true;
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
        boolean explore[] = new boolean[nbNoeuds()];
        for (int i = 0; i < nbNoeuds(); i++) { vu[i] = false; explore[i] = false; }

        /*Pour mémoriser comment revenir au sommet initial d'un cycle
        DiArete suiteAretes[] = new DiArete[nbNoeuds()];*/
        //Pour mémoriser tous les cycles trouvés
        cycles = new ArrayList<>();

        int premierNonVu = premierNonVu(vu);
        while (premierNonVu != -1) {
            vu[premierNonVu] = true;
            //cycle à mémoriser.
            Cycle cycle = new Cycle(nbNoeuds());
            trouverTousLesCyclesRec(noeuds.get(premierNonVu),vu, cycle, explore);
            premierNonVu = premierNonVu(vu);
        }
        
    }
    
    public void trouverTousLesCyclesRec(
            Noeud n,
            boolean[] vu, 
            Cycle cycle, 
            boolean[] explore) {

        vu[n.getId()] = true;
        explore[n.getId()] = true;

        for (DiArete a : aretesDe.get(n.getId())) {
            if (!explore[a.getDest().getId()]) {
                //On mémorise le chemin actuel et on continue
                Cycle cycleCourant = new Cycle(cycle, a, nbNoeuds());
                trouverTousLesCyclesRec(a.getDest(),vu,cycleCourant,explore);
            }
            else {
                Cycle cycleCourant = new Cycle(cycle, a, nbNoeuds());

                //On extrait le cycle du chemin
                cycleCourant.extraire();
                System.out.println("Cycle trouvé : " + cycleCourant);
                cycles.add(cycleCourant);
            }
        }
        explore[n.getId()] = false;
    }

    /*public void pondererAretes(){
        for(Cycle c : cycles){
            for(DiArete a : c.getAretes()){
                a.plusplus();
            }
        }
    }*/

    public DiArete getAreteDePlusHautPoids(){
        if(aretes.size() > 0) {
            DiArete areteMax = aretes.get(0);
            for (DiArete arete : aretes) {
                if (!arete.isSupprime() && arete.getPoids() > areteMax.getPoids()) {
                    areteMax = arete;
                }
            }
            if(areteMax.isSupprime() || areteMax.getPoids() <= 0) return null;

            return areteMax;
        }
        return null;
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
        retour += "}\n";
        retour += entier();
        return retour;
    }

}
