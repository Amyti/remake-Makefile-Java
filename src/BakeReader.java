import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BakeReader {
    private String cheminFichier;
    private HashMap<String, String> variables = new HashMap<>();

    public BakeReader(String cheminFichierS) {
        cheminFichier = cheminFichierS;
    }

    public void readVariable() {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {

                if (ligne.contains("=")) {
                    String[] parts = ligne.split("=", 2); 
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        variables.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public void afficherVariables() {
        for (String key : variables.keySet()) {
            System.out.println(key + " = " + variables.get(key));
        }
    }

    public static void main(String[] args) {
        BakeReader bakeReader = new BakeReader(args[0]);
        bakeReader.readVariable();
        bakeReader.afficherVariables();
    }
}
