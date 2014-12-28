package jcq.graph.pack;

/**
 * Noeud
 * 
 * Structure de données représentant un sommet d'un graph
 * 
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
 */
public class Noeud {
        
    private Integer id;

    public Noeud(Integer _id) {
        id = _id;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return id.toString();
    }
}
