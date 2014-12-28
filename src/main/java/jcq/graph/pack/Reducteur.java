/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcq.graph.pack;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author celine
 */
public class Reducteur {
    
    public static void main(String args[]) {
        
        try {
            GraphReader greader = new GraphReader("/home/celine/Documents/graphs/VraiInstanceCA15");
            Graph instanceCA = (Graph) greader.lire();
            System.out.println(instanceCA.toString());
            
            Integer nbNoeuds = instanceCA.nbNoeuds();
            
            DiGraph instanceMin = new DiGraph(2*nbNoeuds);
            instanceMin.setValeurATester(instanceCA.getValeurATester());
            
            for (int i = 0; i < nbNoeuds; i++) {
                instanceMin.makeArete(2*i, 2*i+1, 0);
            }
            
            for (Arete arete : instanceCA.getAretes()) {
                instanceMin.makeArete(2*arete.getSrc().getId() + 1, 2*arete.getDest().getId(),0);
                instanceMin.makeArete(2*arete.getDest().getId() + 1, 2*arete.getSrc().getId(),0);
            }
            
            System.out.println(instanceMin.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.exit(0);
    }
}
