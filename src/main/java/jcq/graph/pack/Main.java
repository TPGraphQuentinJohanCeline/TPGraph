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
public class Main {

    public static void main(String args[]) {

        DiGraph dg = new DiGraph(5);
        dg.makeArete(0, 2, 2);
        dg.makeArete(1, 0, 2);
        dg.makeArete(2, 1, 1);
        dg.makeArete(1, 4, 1);
        dg.makeArete(3, 1, 2);
        dg.makeArete(4, 3, 1);
        dg.makeArete(2, 3, 1);
        System.out.println(dg.toString());

        Graph g = new Graph(5);
        g.makeArete(0, 2);
        g.makeArete(1, 0);
        g.makeArete(2, 1);
        g.makeArete(1, 4);
        g.makeArete(3, 1);
        g.makeArete(4, 3);
        g.makeArete(2, 3);
        System.out.println(g.toString());

        try {
            GraphReader reader = new GraphReader();
            Graph glu = (Graph) reader.lire();
            System.out.println(glu.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}