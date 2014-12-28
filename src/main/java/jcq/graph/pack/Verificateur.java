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
public class Verificateur {
    
    public static void main(String args[]) {
        
        try {
            GraphReader greader = new GraphReader("/home/celine/Documents/ProjetsComplets/TPGraph/src/main/resources/VraiInstanceMin15");
            DiGraph instanceP = (DiGraph) greader.lire();
            System.out.println(instanceP);
            
            greader = new GraphReader("/home/celine/Documents/ProjetsComplets/TPGraph/src/main/resources/VraiCertificatMin15");
            DiGraph certificat = (DiGraph) greader.lire();
            
            for (DiArete a : certificat.getAretes()) {
                instanceP.deleteArete(instanceP.findArete(a));
            }
            
            System.out.println(instanceP);
            instanceP.trouverTousLesCycles();
            System.out.println(instanceP.possedeUnCycle());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Verificateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
