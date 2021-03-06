package jcq.graph.pack;

import java.util.ArrayList;

/**
 * DiArete
 *
 * @see Arete
 * Représente une arete d'un graph orienté
 * En plus de stoquer le noeud source et le noeud destination,
 * on stoque les cycles trouvés pour cette arête afin de l'utiliser
 * dans le solver.
 *
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
 */
public class DiArete extends Arete {
    
    private Integer poids;
    private final ArrayList<Cycle> cycles;
    private boolean supprime;

    public DiArete(Noeud _src, Noeud _dest, Integer _poids) {
        super(_src,_dest);
        poids = _poids;
        cycles = new ArrayList<>();
        supprime = false;
    }

    public void plusplus() {
        poids++;
    }
    
    public boolean equals(DiArete a) {
        return (a.getSrc().getId() == src.getId() 
                && a.getDest().getId() == dest.getId());
    }

    public void addCycle(Cycle c){
        cycles.add(c);
    }

    public ArrayList<Cycle> getCycles(){ return cycles; }
    
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
        return src.toString() + "->" + dest.toString();
    }

    public void moinsmoins() { poids--; }

    public boolean isSupprime() {
        return supprime;
    }

    public void setSupprime(boolean supprime) {
        this.supprime = supprime;
    }
}
