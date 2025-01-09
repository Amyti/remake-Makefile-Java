import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BakeReader {
    private String cheminFichier;
    private HashMap<String, String> variables = new HashMap<>();
    private HashMap<String, String[]> cibles = new HashMap<>();
    private HashMap<String, String> commandes = new HashMap<>();

    public BakeReader(String cheminFichierS) {
        cheminFichier = cheminFichierS;
    }


/*-------------------------------------- Lis et met dans des hashmaps les Varibles, cibles et commandes ---------------------------------------------- */


    public void readInformations() {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            String cibleAct = null;
            while ((ligne = lecteur.readLine()) != null) {
                


                if (ligne.contains("=")) {
                    String[] parts = ligne.split("=", 2); 
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        variables.put(key, value);
                    }
                }

                else if (ligne.contains(":") && !ligne.contains("=")) {
                    String[] parts = ligne.split(":", 2);
                    if (parts.length == 2) {
                        cibleAct = parts[0].trim();
                        cibles.put(cibleAct, parts[1].trim().split(" "));
                        
                    }
                }

                else if (ligne.startsWith("\t")) {
                    String commande = ligne.trim();
                    commande = replaceVariables(commande); 
                    commandes.put(cibleAct, commande);
                }



            }
        } catch (IOException e) {
            System.err.println("petit soucis fichier bizzare" );
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

/*-------------------------------------- Afficher pour voir si cca a bien lue le bakefile ---------------------------------------------- */

    public void afficher() {
        for (String key : variables.keySet()) {
            System.out.println(key + " = " + variables.get(key));
        }
        System.out.println("Cibles :");

        for (String key : cibles.keySet()) {
            System.out.print(key + " : ");
            System.out.println(String.join(", ", cibles.get(key)));
        }
        System.out.println("Commandes :");

        for (String target : commandes.keySet()) {
            System.out.println(target + " -> " + commandes.get(target));
        }
    }

   

/*-----------------------------------------Juste pour test en attendant----------------------------------------- */ 
    public static void main(String[] args) {
        BakeReader bakeReader = new BakeReader(args[0]);
        bakeReader.readInformations();
        bakeReader.afficher();
    }
}
