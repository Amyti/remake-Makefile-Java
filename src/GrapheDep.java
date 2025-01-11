import java.util.*;

public class GrapheDep {
    private HashMap<String, String[]> dependances; 

    public GrapheDep(HashMap<String, String[]> dependances) {
        this.dependances = dependances;
    }

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
