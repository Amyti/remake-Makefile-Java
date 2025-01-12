package fr.iutfbleau.projet;

import fr.iutfbleau.projet.utils.*;
import fr.iutfbleau.projet.execute.*;

import java.util.*;

public class Bake {
    private String bakefile = "Bakefile";
    private GrapheDep graphe;
    private List<String> ordre;
    private BakeReader read;
    private BakeExecute exec;

    public Bake(List<String> cibles, boolean modeDebug) {
        try {

            read = new BakeReader(bakefile);
            graphe = new GrapheDep(read.getCibles());

            if (graphe.detecterCycle()) {
                System.err.println("Erreur : Dépendances circulaires détectées !");
                return;
            }

            ordre = graphe.obtenirOrdreDeConstruction(); 
            exec = new BakeExecute(ordre, read.getCommandes(),read.getPhonyCibles() ,modeDebug);

            if(cibles.size() > 0){
                exec.executerCommandesCible(cibles);
            }else{
                exec.executerCommandes();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args) {
        boolean modeDebug = false;
        List<String> cibles = new ArrayList<>();
        try {
            for (String arg : args) {
                if (arg.equals("-d")) {
                    modeDebug = true;
                } else {
                    cibles.add(arg); 
                }
            }
            Thread.sleep(1000);
            if (modeDebug) {
                System.out.println("------------- Mode débogage -------------");
            } else {
                
                System.out.println("------------- Exécution en mode normal -------------");
            }

            new Bake(cibles, modeDebug);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
