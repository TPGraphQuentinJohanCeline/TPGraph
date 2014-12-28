package jcq.graph.pack;

/**
 * Arete
 *
 * Représente une arete d'un graph (un couple noeud source, noeud dest)
 *
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
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
