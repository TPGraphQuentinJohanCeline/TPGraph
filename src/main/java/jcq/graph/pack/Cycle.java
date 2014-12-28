package jcq.graph.pack;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Cycle
 *
 * Représente un cycle
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
public final class Cycle {

    private final ArrayList<DiArete> aretes;
    private final Integer nbNoeuds;
    private boolean supprime;

    public Cycle(Integer _nbNoeuds) {
        aretes = new ArrayList<>();
        nbNoeuds = _nbNoeuds;
        supprime = false;
    }
    
    public Cycle(Cycle _cycle, DiArete a, Integer _nbNoeuds) {
        this(_nbNoeuds);
        supprime = _cycle.supprime;
        aretes.addAll(_cycle.getAretes());
        ajouterArete(a);
    }
    
    //Lors du parcours du digraph, on trouve des listes de sommets qui reviennent
    //sur eux même :
    //exemple : 1 -> 2 -> 5 -> 3 -> 4 -> 5
    //à partir de cette liste, on doit identifier quel est le cycle : 5,3,4,5
    //C'est le rôle des méthodes trouverRacine et extraire
    
    private Integer trouverRacine() {
        boolean[] vu = new boolean[nbNoeuds];
        for (int i = 0; i < nbNoeuds; i++) { vu[i] = false; }
        
        DiArete a;
        ListIterator<DiArete> aIter = aretes.listIterator();
        while (aIter.hasNext()) {
            a = aIter.next();
        }
        for (; aIter.hasPrevious();) {
            a = aIter.previous();
            vu[a.getDest().getId()] = true;
            if (vu[a.getSrc().getId()]) return a.getSrc().getId();
        }
        
        return -1;
        
    }
    
    public void extraire() {
        Integer racine = trouverRacine();
        
        boolean avantcycle = true;
        boolean aprescycle = false;
        
        ArrayList<DiArete> listToRemove = new ArrayList<>();
        
        for(DiArete a : aretes) {
            if (a.getSrc().getId() == racine) { avantcycle = false; }
            if (avantcycle || aprescycle) { listToRemove.add(a); }
            else { a.plusplus(); }
            if (!avantcycle && a.getDest().getId() == racine) { aprescycle = true; }
        }
        
        for(DiArete a : listToRemove) {
            aretes.remove(a);
        }

        finaliser();
    }

    public void finaliser(){
        for(DiArete a : aretes){
            a.addCycle(this);
        }
    }
    
    /**
     * @return the aretes
     */
    public ArrayList<DiArete> getAretes() {
        return aretes;
    }
    
    public void ajouterArete(DiArete newArete) {
        aretes.add(newArete);
    }
    
    @Override
    public String toString() {
        String retour = "Cycle { ";
        for (DiArete arete : aretes) {
            retour += arete.getSrc().getId() + " -> " + arete.getDest().getId() + " ";
        }
        retour += " }";
        return retour;
    }

    public boolean isSupprime() {
        return supprime;
    }

    public void setSupprime(boolean _supprime){
        this.supprime = _supprime;
    }
}
