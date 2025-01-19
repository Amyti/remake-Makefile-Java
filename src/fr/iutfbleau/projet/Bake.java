/**
* La classe <code>Bake</code> est le point d'entrée principal du programme Bake.
* Elle gère la lecture du fichier de configuration, la détection des dépendances circulaires,
* et l'exécution des commandes associées aux cibles spécifiées.
* 
* @version 1.0
* @author Amir Kabbouri - Bamba Top - Athiran Nagathurai
*/
package fr.iutfbleau.projet;

import fr.iutfbleau.projet.utils.*;
import fr.iutfbleau.projet.execute.*;

import java.util.*;
/** *
 * Classe qui coordonne le processus global (lecture, création du graphe, exécution des com-
mandes).
*/
public class Bake {
    /**
    * Nom du fichier de configuration par défaut (Bakefile).
    */
    private String bakefile = "Bakefile";
    /**
    * Instance de <code>GrapheDep</code> pour gérer les dépendances entre cibles.
    */
    private GrapheDep graphe;
    /**
    * Liste ordonnée des cibles à exécuter.
    */
    private List<String> ordre;
    /**
    * Instance de <code>BakeReader</code> pour lire le fichier de configuration.
    */
    private BakeReader read;
    /**
    * Instance de <code>BakeExecute</code> pour exécuter les commandes des cibles.
    */
    private BakeExecute exec;
    /**
    * Constructeur principal de la classe <code>Bake</code>.
    * Initialise la lecture du fichier de configuration, vérifie les dépendances circulaires,
    * et lance l'exécution des commandes associées aux cibles.
    * 
    * @param cibles   Liste des cibles à mettre à jour.
    * @param modeDebug Indique si le mode débogage est activé.
    */
    
    public Bake(List<String> cibles, boolean modeDebug) {
        try {
            
            read = new BakeReader(bakefile);
            graphe = new GrapheDep(read.getCibles());
            
            
            
            ordre = graphe.obtenirOrdreDeConstruction(); 
            exec = new BakeExecute(ordre, read.getCommandes(),read.getPhonyCibles() ,modeDebug);
            
            if(cibles.size() > 0){
                exec.executerCommandesCible(cibles);
            }else{
                if (graphe.detecterCycle()) {
                    System.err.println("Erreur : Dépendances circulaires détectées !");
                    return;
                }
                exec.executerCommandes();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Point d'entrée principal du programme Bake.
    * Analyse les arguments de la ligne de commande pour déterminer les cibles à exécuter
    * et active ou non le mode débogage.
    * 
    * @param args Arguments de la ligne de commande.
    */
    
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