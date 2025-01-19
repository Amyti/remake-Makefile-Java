/**
 * La classe <code>GrapheDep</code> représente un graphe de dépendances entre cibles.
 * Elle permet de détecter les cycles dans les dépendances et de déterminer un ordre
 * d'exécution des cibles.
 * 
 * @version 1.0
 * @author Amir Kabbouri - Bamba Top - Athiran Nagathurai
 */
package fr.iutfbleau.projet.utils;

import java.util.*;
/** *
 * Classe qui représente un graphe de dépendances entre les cibles et fournit des méthodes
pour détecter les cycles et générer l’ordre de construction
*/
public class GrapheDep {
    /**
     * Map contenant les cibles et leurs dépendances.
     */
    private HashMap<String, String[]> dependances; 
/**
     * Constructeur de la classe <code>GrapheDep</code>.
     * 
     * @param dependances Map des dépendances entre cibles.
     */
    public GrapheDep(HashMap<String, String[]> dependances) {
        this.dependances = dependances;
    }
/**
     * Vérifie si le graphe contient des cycles.
     * 
     * @return <code>true</code> si un cycle est détecté, <code>false</code> sinon.
     */
    public boolean detecterCycle() {
        Set<String> visites = new HashSet<>();
        Set<String> enCours = new HashSet<>();

        for (String cible : dependances.keySet()) {
            if (graphVerifCycle(cible, visites, enCours)) {
                return true; 
            }
        }
        return false;
    }
/**
     * Vérifie récursivement si un cycle est présent à partir d'une cible donnée.
     * 
     * @param cible   La cible actuelle.
     * @param visites Ensemble des cibles déjà visitées.
     * @param enCours Ensemble des cibles en cours de traitement.
     * @return <code>true</code> si un cycle est détecté, <code>false</code> sinon.
     */
    private boolean graphVerifCycle(String cible, Set<String> visites, Set<String> enCours) {
        if (enCours.contains(cible)) {
            return true; 
        }

        if (visites.contains(cible)) {
            return false; 
        }

        enCours.add(cible);
        visites.add(cible);

        String[] dependancesCible = dependances.getOrDefault(cible, new String[0]);
        for (String dependance : dependancesCible) {
            if (graphVerifCycle(dependance, visites, enCours)) {
                return true;
            }
        }

        enCours.remove(cible);
        return false;
    }
 /**
     * Détermine un ordre d'exécution des cibles basé sur leurs dépendances.
     * 
     * @return Une liste ordonnée des cibles.
     */
    public List<String> obtenirOrdreDeConstruction() {
        Set<String> visites = new HashSet<>();
        List<String> ordre = new ArrayList<>();

        for (String cible : dependances.keySet()) {
            if (!visites.contains(cible)) {
                graphOrdre(cible, visites, ordre);
            }
        }

        return ordre;
    }
/**
     * Remplit récursivement l'ordre d'exécution des cibles.
     * 
     * @param cible   La cible actuelle.
     * @param visites Ensemble des cibles déjà visitées.
     * @param ordre   Liste contenant l'ordre des cibles.
     */
    private void graphOrdre(String cible, Set<String> visites, List<String> ordre) {
        if (cible.endsWith(".c") || cible.endsWith(".h") || cible.endsWith(".java")) {
            return;
        }

        visites.add(cible);

        String[] dependancesCible = dependances.getOrDefault(cible, new String[0]);
        for (String dependance : dependancesCible) {
            if (!visites.contains(dependance)) {
                graphOrdre(dependance, visites, ordre);
            }
        }

        ordre.add(cible); 
    }
}
