/**
* La classe <code>BakeReader</code> permet de lire et d'interpréter un fichier de configuration Bakefile.
* Elle extrait les variables, cibles, commandes et cibles spéciales à partir du fichier.
* 
* @version 1.0
* @author Amir Kabbouri - Bamba Top - Athiran Nagathurai
*/
package fr.iutfbleau.projet.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/** *
 * Classe qui lit et extrait les informations du Bakefile.
*/
public class BakeReader {
    /**
    * Chemin du fichier Bakefile.
    */
    private String cheminFichier;
    /**
    * Contient les variables définies dans le fichier Bakefile.
    */
    private HashMap<String, String> variables = new HashMap<>();
    /**
    * Contient les cibles et leurs dépendances associées.
    */
    private HashMap<String, String[]> cibles = new HashMap<>();
    /**
    * Contient les commandes associées à chaque cible.
    */
    private HashMap<String, String> commandes = new HashMap<>();
    /**
    * Contient les cibles marquées comme .PHONY.
    */
    private Set<String> phonyCibles = new HashSet<>();
    /**
    * Constructeur qui initialise le lecteur de fichier et extrait les informations.
    * 
    * @param cheminFichierS Chemin du fichier Bakefile.
    */
    public BakeReader(String cheminFichierS) {
        cheminFichier = cheminFichierS;
        readInformations();
    }
    
    /**
    * Renvoie les variables définies dans le fichier.
    * 
    * @return un HashMap contenant les variables.
    */
    public HashMap<String, String> getVariables(){
        return variables;
    }
    
    /**
     * Renvoie les cibles et leurs dépendances.
     * 
     * @return un HashMap contenant les cibles et leurs dépendances.
     */
    public HashMap<String, String[]> getCibles(){
        return cibles;
    }
    /**
     * Renvoie les commandes associées aux cibles.
     * 
     * @return un HashMap contenant les commandes.
     */
    public HashMap<String, String> getCommandes(){
        return commandes;
    }
    /**
     * Renvoie les cibles marquées comme .PHONY.
     * 
     * @return un ensemble contenant les cibles .PHONY.
     */
    public Set<String> getPhonyCibles(){
        return phonyCibles;
    }
    
    
    /*-------------------------------------- Lis et met dans des hashmaps les Varibles, cibles et commandes ---------------------------------------------- */
    
    
     /**
     * Lis les informations du fichier Bakefile et remplit les structures de données correspondantes.
     */
    public void readInformations() {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            String cibleAct = null;
            while ((ligne = lecteur.readLine()) != null) {
                ligne = replaceVariables(ligne);
                
                if (ligne.startsWith("#")){
                    continue;
                }
                
                else if (ligne.contains("=")) {
                    String[] parts = ligne.split("=", 2); 
                    if (parts.length == 2) {
                        variables.put(parts[0].trim(), parts[1].trim());
                    }
                }
                
                else if (ligne.startsWith(".PHONY")) {
                    String[] partsPhony = ligne.split(":", 2);
                    if (partsPhony.length == 2) {
                        String[] targets = partsPhony[1].trim().split("\\s+");
                        for (String t : targets) {
                            phonyCibles.add(t);
                        }
                    }
                }
                
                else if (ligne.contains(":") && !ligne.contains("=")) {
                    String[] partsCible = ligne.split(":", 2);
                    if (partsCible.length == 2) {
                        cibleAct = partsCible[0].trim();
                        cibles.put(cibleAct, partsCible[1].trim().split(" "));
                    }
                }
                
                else if (ligne.startsWith("\t")) {
                    String commande = ligne.trim();
                    commandes.put(cibleAct, commande);
                }
                
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier..." );
        }
    }
    /*-------------------------------------- remplace les variables par le contenue des variables ---------------------------------------------- */
    /**
     * Remplace les variables dans une commande par leurs valeurs correspondantes.
     * 
     * @param commande La commande à analyser.
     * @return La commande avec les variables remplacées.
     */
    private String replaceVariables(String commande) {
        for (String variable : variables.keySet()) {
            if (commande.contains("$(" + variable + ")")) {
                commande = commande.replace("$(" + variable + ")", variables.get(variable));
            }
        }
        return commande;
    }
    
}
