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
public class DiGraph extends Graph {

    protected ArrayList<DiArete> aretes;

    public DiGraph(int taille) {
        super(taille);
        aretes = new ArrayList<>();
    }
    
    public void makeArete(Integer id_src, Integer id_dest, Integer poids) {
        aretes.add(new DiArete(noeuds.get(id_src),noeuds.get(id_dest),poids));
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
