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
public class DiGraph {

    private final ArrayList<Noeud> noeuds;
    private final ArrayList<DiArete> aretes;

    public DiGraph(int taille) {
        noeuds = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            noeuds.add(new Noeud(i));
        }
        aretes = new ArrayList<>();
    }
    
    public void makeArete(Integer id_src, Integer id_dest, Integer poids) {
        aretes.add(new DiArete(noeuds.get(id_src),noeuds.get(id_dest),poids));
    }

    /**
     * @return the noeuds
     */
    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    /**
     * @return the aretes
     */
    public ArrayList<DiArete> getAretes() {
        return aretes;
    }

    public void ajouter(DiArete newArete) {
        this.aretes.add(newArete);
    }

    @Override
    public String toString() {
        String retour = "Digraph {\n";
        for (Noeud noeud : noeuds) {
            retour += noeud.toString() + " ";
        }
        retour += "\n";
        for (DiArete arete : aretes) {
            retour += arete.toString() + "\n";
        }
        retour += "}";
        return retour;
    }

}
