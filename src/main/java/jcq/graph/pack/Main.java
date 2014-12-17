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
        
        DiGraph g = new DiGraph(5);
        g.makeArete(0, 2, 2);
        g.makeArete(1, 0, 2);
        g.makeArete(2, 1, 1);
        g.makeArete(1, 4, 1);
        g.makeArete(3, 1, 2);
        g.makeArete(4, 3, 1);
        g.makeArete(2, 3, 1);
        System.out.println(g.toString());
        
    }
    
}
