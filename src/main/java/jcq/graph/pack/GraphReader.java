/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcq.graph.pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author celine
 */
public class GraphReader {

    String filePath;

    Scanner scanner;
    Integer premier;

    public GraphReader() throws FileNotFoundException {
        this.filePath = "/home/celine/Documents/graphs/in.txt";
        this.scanner = new Scanner(new File(filePath));
    }

    public AbstractGraph lire() {
        String line = scanner.nextLine();
        line = line.trim();
        switch (line) {
            case "Graph {":
                return lireGraph();
            case "Digraph {":
                return lireDiGraph();
            default:
                scanner.close();
                System.exit(1);
        }
        return new Graph(0);
    }

    private Integer lireNbNoeuds() {
        String line = scanner.nextLine();
        String[] noeuds = line.split(" ");

        premier = Integer.parseInt(noeuds[0]);
        
        Integer nbNoeuds
                = Integer.parseInt(noeuds[noeuds.length - 1])
                - Integer.parseInt(noeuds[0])
                + 1;

        return nbNoeuds;
    }

    private Graph lireGraph() {
        Integer nbNoeuds = lireNbNoeuds();
        Graph g = new Graph(nbNoeuds);
        String line = scanner.nextLine().trim();
        while (!line.equals("}")) {
            String[] ar = line.split("--");
            g.makeArete(Integer.parseInt(ar[0]) - premier, Integer.parseInt(ar[1]) - premier);
            line = scanner.nextLine().trim();
        }
        scanner.close();
        return g;
    }

    private DiGraph lireDiGraph() {
        return new DiGraph(lireNbNoeuds());
    }

}
