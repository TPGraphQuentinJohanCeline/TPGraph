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
        
    }
    
}
