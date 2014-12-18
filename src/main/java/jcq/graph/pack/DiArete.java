/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcq.graph.pack;

/**
 *
 * @author celine
 */
public class DiArete extends Arete {
    
    private Integer poids;

    public DiArete(Noeud _src, Noeud _dest, Integer _poids) {
        super(_src,_dest);
        poids = _poids;
    }

    public void plusplus() {
        poids++;
    }
    
    /**
     * @return the poids
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     * @param poids the poids to set
     */
    public void setPoids(Integer poids) {
        this.poids = poids;
    }
    
    @Override
    public String toString() {
        return src.toString() + "->" + dest.toString() + " [label=\" " + poids.toString() + " \"] ";
    }
}
