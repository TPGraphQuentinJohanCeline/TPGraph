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
