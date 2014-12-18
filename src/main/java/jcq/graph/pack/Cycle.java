/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcq.graph.pack;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author celine
 */
public class Cycle {

    private final ArrayList<DiArete> aretes;
    private final Integer nbNoeuds;

    public Cycle(Integer _nbNoeuds) {
        aretes = new ArrayList<>();
        nbNoeuds = _nbNoeuds;
    }
    
    public Cycle(Cycle _cycle, DiArete a, Integer _nbNoeuds) {
        this(_nbNoeuds);
        aretes.addAll(_cycle.getAretes());
        aretes.add(a);
    }
    
    private Integer trouverRacine() {
        boolean[] vu = new boolean[nbNoeuds];
        for (int i = 0; i < nbNoeuds; i++) { vu[i] = false; }
        
        DiArete a = null;
        ListIterator<DiArete> aIter = aretes.listIterator();
        while (aIter.hasNext()) {
            a = aIter.next();
        }
        for (; aIter.hasPrevious();) {
            a = aIter.previous();
            vu[a.getDest().getId()] = true;
            if (vu[a.getSrc().getId()]) return a.getSrc().getId();
        }
        
        for (int i = 0; i < nbNoeuds; i++) { System.out.print(" " + vu[i] + " "); }
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
    }
    
    /**
     * @return the aretes
     */
    public ArrayList<DiArete> getAretes() {
        return aretes;
    }
    
    public void ajoutArete(DiArete newArete) {
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

}
