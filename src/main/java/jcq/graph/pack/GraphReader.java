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

    public GraphReader(String _filePath) throws FileNotFoundException {
        filePath = _filePath;
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

    private int lireEntier() {
        String line = scanner.nextLine().trim();
        int retour = scanner.nextInt();
        return retour;
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
        g.setValeurATester(lireEntier());
        scanner.close();
        return g;
    }

    private DiGraph lireDiGraph() {
        Integer nbNoeuds = lireNbNoeuds();
        DiGraph g = new DiGraph(nbNoeuds);
        String line = scanner.nextLine().trim();
        while (!line.equals("}")) {
            String[] ar = line.split("->");
            g.makeArete(Integer.parseInt(ar[0]) - premier, Integer.parseInt(ar[1]) - premier, 0);
            line = scanner.nextLine().trim();
        }
        g.setValeurATester(lireEntier());
        scanner.close();
        return g;
    }

}
