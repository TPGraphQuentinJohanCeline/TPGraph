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
public class Arete {

    protected Noeud src;
    protected Noeud dest;

    public Arete(Noeud _src, Noeud _dest) {
        src = _src;
        dest = _dest;
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

    @Override
    public String toString() {
        return src.toString() + "--" + dest.toString();
    }

}
