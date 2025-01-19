/**
 * La classe <code>BakeExecute</code> est responsable de l'exécution des commandes
 * pour les cibles spécifiées en respectant leur ordre d'exécution.
 * 
 * @version 1.0
 * @author  Amir Kabbouri - Bamba Top - Athiran Nagathurai
 */
package fr.iutfbleau.projet.execute;

import java.io.*;
import java.util.*;

public class BakeExecute {

    /**
     * Instance de <code>BakeComparator</code> pour vérifier la nécessité d'une recompilation.
     */
    private BakeComparator comparator;

    /**
     * Liste des cibles à exécuter dans leur ordre d'exécution.
     */
    private List<String> ordre;

    /**
     * Commandes associées à chaque cible.
     */
    private HashMap<String, String> commandes;

    /**
     * Indique si le mode débogage est activé.
     */
    private boolean modeDebug;

    /**
     * Ensemble des cibles marquées comme .PHONY.
     */
    private Set<String> phonyCibles;

    /**
     * Constructeur de la classe <code>BakeExecute</code>.
     * 
     * @param ordre       Liste ordonnée des cibles à exécuter.
     * @param commandes   Commandes associées aux cibles.
     * @param phonyCibles Ensemble des cibles .PHONY.
     * @param modeDebug   Indique si le mode débogage est activé.
     */
    public BakeExecute(List<String> ordre, HashMap<String, String> commandes, Set<String> phonyCibles, boolean modeDebug) {
        this.ordre = ordre;
        this.commandes = commandes;
        this.phonyCibles = phonyCibles;
        this.modeDebug = modeDebug;
        this.comparator = new BakeComparator(modeDebug);
    }

    /**
     * Exécute les commandes associées à une liste de cibles spécifiées.
     * 
     * @param cibles Liste des cibles à exécuter.
     */
    public void executerCommandesCible(List<String> cibles) {
        for (String cible : cibles) {
            String commande = commandes.get(cible);
            if (commande == null) {
                System.out.println("Pas de commande pour la cible " + cible);
                continue; 
            }
            System.out.println(commande);
            if (!executerCommande(commande)) {
                System.out.println("Erreur lors de l'exécution de la commande pour la cible : " + cible);
                return; 
            }
        }
    }

    /**
     * Exécute toutes les commandes dans l'ordre d'exécution des cibles.
     */
    public void executerCommandes() {
        try {
            for (String cible : ordre) {
                if (phonyCibles.contains(cible)) {
                    continue;
                }
                String commande = commandes.get(cible);
                if (commande != null) {
                    System.out.println(commande);
                    if (!executerCommande(commande)) {
                        System.err.println("Erreur lors de l'exécution de la commande.");
                        return;
                    }
                }
            }
            System.out.println("--------- Application compilée avec succès ! ---------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exécute une commande donnée en utilisant un <code>ProcessBuilder</code>.
     * 
     * @param commande La commande à exécuter.
     * @return <code>true</code> si l'exécution est réussie, sinon <code>false</code>.
     */
    private boolean executerCommande(String commande) {
        String[] command = commande.split(" ");
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.inheritIO();
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            return false; 
        }
    }
}
