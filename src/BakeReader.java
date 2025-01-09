import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BakeReader {
    private String cheminFichier;
    private HashMap<String, String> variables = new HashMap<>();
    private HashMap<String, String[]> cibles = new HashMap<>();

    public BakeReader(String cheminFichierS) {
        cheminFichier = cheminFichierS;
    }

    public void readInformations() {
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

                if (ligne.contains(":") && !ligne.contains("=")) {
                    String[] parts = ligne.split(":", 2);
                    if (parts.length == 2) {
                        cibles.put(parts[0].trim(), parts[1].trim().split(" "));
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("petit soucis fichier bizzare" );
        }
    }



    public void afficherVariables() {
        for (String key : variables.keySet()) {
            System.out.println(key + " = " + variables.get(key));
        }
    }

    public void afficherTargets() {
        System.out.println("Cibles :");
        for (String key : cibles.keySet()) {
            System.out.print(key + " : ");
            System.out.println(String.join(", ", cibles.get(key)));
        }
    }

/*-----------------------------------------Juste pour test en attendant----------------------------------------- */ 
    public static void main(String[] args) {
        BakeReader bakeReader = new BakeReader(args[0]);
        bakeReader.readInformations();
        bakeReader.afficherVariables();
        bakeReader.afficherTargets();
    }
}
