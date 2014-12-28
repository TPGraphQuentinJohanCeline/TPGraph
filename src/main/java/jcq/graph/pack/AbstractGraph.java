package jcq.graph.pack;

import java.util.ArrayList;

/**
 * AbstractGraph
 *
 * Représente un graph orienté ou non
 * Contient les éléments communs aux deux structures :
 * La liste des noeuds, et le nombre max indiqué dans l'instance
 * de CA ou de MinCoupeCircuit 
 * (nombre de sommets à colorier, resp. arêtes à couper)
 *
 * @author Quentin Choullet
 * @author Céline de Roland
 * @author Johan Ravery
 */
public abstract class AbstractGraph {

    protected ArrayList<Noeud> noeuds;
    private int valeurATester;

    public AbstractGraph(int taille) {
        noeuds = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            noeuds.add(new Noeud(i));
        }
    }

    /**
     * @return the noeuds
     */
    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }
    
    public Integer nbNoeuds() {
        return noeuds.size();
    }

    public String listeDeNoeuds() {
        String retour = "";
        for (Noeud noeud : noeuds) {
            retour += noeud.toString() + " ";
        }
        retour += "\n";
        return retour;
    }

    public String entier() {
        String retour = "Entier {\n" + valeurATester + "\n}";
        return retour;
    }
    
    /**
     * @return the valeurATester
     */
    public int getValeurATester() {
        return valeurATester;
    }

    /**
     * @param valeurATester the valeurATester to set
     */
    public void setValeurATester(int valeurATester) {
        this.valeurATester = valeurATester;
    }
}
