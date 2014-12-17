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
public class DiArete {
    
    private Noeud src;
    private Noeud dest;
    private Integer poids;

    public DiArete(Noeud _src, Noeud _dest, Integer _poids) {
        src = _src;
        dest = _dest;
        poids = _poids;
    }
    /**
     * @return the src
     */
    public Noeud getSrc() {
        return src;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(Noeud src) {
        this.src = src;
    }

    /**
     * @return the dest
     */
    public Noeud getDest() {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(Noeud dest) {
        this.dest = dest;
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
