import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BakeReader {
    private String cheminFichier;
    private HashMap<String, String> variables = new HashMap<>();
    private HashMap<String, String[]> cibles = new HashMap<>();
    private HashMap<String, String> commandes = new HashMap<>();
    private Set<String> phonyCibles = new HashSet<>();

    public BakeReader(String cheminFichierS) {
        cheminFichier = cheminFichierS;
        readInformations();
    }


    public HashMap<String, String> getVariables(){
        return variables;
    }


    public HashMap<String, String[]> getCibles(){
        return cibles;
    }

    public HashMap<String, String> getCommandes(){
        return commandes;
    }

    public Set<String> getPhonyCibles(){
        return phonyCibles;
    }


/*-------------------------------------- Lis et met dans des hashmaps les Varibles, cibles et commandes ---------------------------------------------- */
    


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

    private String replaceVariables(String commande) {
        for (String variable : variables.keySet()) {
            if (commande.contains("$(" + variable + ")")) {
                commande = commande.replace("$(" + variable + ")", variables.get(variable));
            }
        }
        return commande;
    }

}
