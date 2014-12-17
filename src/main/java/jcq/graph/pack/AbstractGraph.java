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
public abstract class AbstractGraph {
        protected ArrayList<Noeud> noeuds;

      public AbstractGraph(int taille) {
        noeuds = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            noeuds.add(new Noeud(i));
        }
    }

    /**
     * @return the noeuds
     */
    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }


    public String listeDeNoeuds() {
        String retour = "";
        for (Noeud noeud : noeuds) {
            retour += noeud.toString() + " ";
        }
        retour += "\n";
        return retour;
    }  
}
