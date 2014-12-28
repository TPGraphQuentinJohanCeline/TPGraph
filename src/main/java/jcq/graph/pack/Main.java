/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcq.graph.pack;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author celine
 */
public class Main {

    public static void main(String args[]) {

        /*DiGraph dg = new DiGraph(8);
        dg.makeArete(0, 2, 0);
        dg.makeArete(1, 0, 0);
        dg.makeArete(2, 1, 0);
        dg.makeArete(1, 4, 0);
        dg.makeArete(3, 1, 0);
        dg.makeArete(4, 3, 0);
        dg.makeArete(2, 3, 0);
        dg.makeArete(0, 5, 0);
        dg.makeArete(5, 6, 0);
        dg.makeArete(3, 7, 0);*/

        DiGraph dg = new DiGraph(10);
        dg.makeArete(1,2,0);
        dg.makeArete(2,3,0);
        dg.makeArete(3,1,0);
        dg.makeArete(1,4,0);
        dg.makeArete(1,3,0);
        dg.makeArete(2,1,0);
        dg.makeArete(3,8,0);
        dg.makeArete(8,9,0);
        dg.makeArete(9, 3, 0);
        dg.makeArete(5, 6, 0);
        dg.makeArete(6,7,0);
        dg.makeArete(7,5,0);
        System.out.println(dg.toString());


       /*Graph g = new Graph(5);
        g.makeArete(0, 2);
        g.makeArete(1, 0);
        g.makeArete(2, 1);
        g.makeArete(1, 4);
        g.makeArete(3, 1);
        g.makeArete(4, 3);
        g.makeArete(2, 3);
        System.out.println(g.toString());

        try {
            GraphReader greader = new GraphReader("/home/celine/Documents/graphs/inGraph.txt");
            Graph glu = (Graph) greader.lire();
            System.out.println(glu.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            GraphReader dgreader = new GraphReader("/home/celine/Documents/graphs/inDigraph.txt");
            DiGraph dglu = (DiGraph) dgreader.lire();
            System.out.println(dglu.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        System.out.println(Solver.minCoupeCircuit(3, dg));

        
    }

}
