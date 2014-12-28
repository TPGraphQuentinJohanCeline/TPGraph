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
public class Graph extends AbstractGraph {

    protected ArrayList<Arete> aretes;

    public Graph(int taille) {
        super(taille);
        aretes = new ArrayList<>();
    }
    
    public void makeArete(Integer id_src, Integer id_dest) {
        aretes.add(new Arete(noeuds.get(id_src),noeuds.get(id_dest)));
    }

    /**
     * @return the aretes
     */
    public ArrayList<Arete> getAretes() {
        return aretes;
    }

    @Override
    public String toString() {
        String retour = "Graph {\n";
        retour += listeDeNoeuds();
        for (Arete arete : aretes) {
            retour += arete.toString() + "\n";
        }
        retour += "}\n";
        retour += entier();
        return retour;
    }
}
