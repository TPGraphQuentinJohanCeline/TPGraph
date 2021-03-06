package jcq.graph.pack;

import java.util.Scanner;

/**
 * GraphReader
 *
 * Lit sur l'entrée standard une instance de CouvertureDesAretes ou de
 * MinCoupeCircuit Crée un objet de type Graph dans le premier cas, DiGraph dans
 * le second cas
 *
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
 */
public class GraphReader {

    Scanner scanner;
    Integer premier;

    public GraphReader() {
        this.scanner = new Scanner(System.in);
    }

		public void close() {
			this.scanner.close();
		}

    public AbstractGraph lire() {

        //La première ligne indique s'il s'agit d'un graph ou d'un digraph
        String line = scanner.nextLine();
        line = line.trim();
				while (!line.equals("Graph {") && !line.equals("Digraph {")) {
		      line = scanner.nextLine();
		      line = line.trim();
				}
        switch (line) {
            case "Graph {":
                return lireGraph();
            case "Digraph {":
                return lireDiGraph();
            default:
								System.err.println("encodage invalide");
                System.exit(1);
        }
        return new Graph(0);
    }

    private Graph lireGraph() {
        //La deuxième ligne indique la liste des noeuds
        //on impose que les noeuds soient des entiers consécutifs
        //dans l'instance de Graph créée, on les renumérote à partir de 0
        Integer nbNoeuds = lireNbNoeuds();
        Graph g = new Graph(nbNoeuds);
        String line = scanner.nextLine().trim();
        
        //Les lignes suivantes donnent les arêtes sous la forme
        //noeud1--noeud2
        while (!line.equals("}")) {
            String[] ar = line.split("--");
            g.makeArete(Integer.parseInt(ar[0]) - premier, Integer.parseInt(ar[1]) - premier);
            line = scanner.nextLine().trim();
        }
        
        //A la fin on lit un entier
        //qui représente le nombre de sommets qu'on souhaite colorier
        g.setValeurATester(lireEntier());
        
        return g;
    }

    private DiGraph lireDiGraph() {
        //La deuxième ligne indique la liste des noeuds
        //on impose que les noeuds soient des entiers consécutifs
        //dans l'instance de Graph créée, on les renumérote à partir de 0
        Integer nbNoeuds = lireNbNoeuds();
        DiGraph g = new DiGraph(nbNoeuds);
        String line = scanner.nextLine().trim();
        
        //Les lignes suivantes donnent les arêtes sous la forme
        //noeud1->noeud2
        while (!line.equals("}")) {
            String[] ar = line.split("->");
            g.makeArete(Integer.parseInt(ar[0]) - premier, Integer.parseInt(ar[1]) - premier, 0);
            line = scanner.nextLine().trim();
        }
        
        //A la fin on lit un entier
        //qui représente le nombre d'arêtes qu'on souhaite couper
        g.setValeurATester(lireEntier());
        return g;
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

}
