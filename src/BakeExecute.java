import java.io.*;
import java.util.*;
import java.lang.Thread;


public class BakeExecute {
    private List<String> ordre = new ArrayList<>();
    private HashMap<String, String> commandes = new HashMap<>();

    public BakeExecute(List<String> ordre, HashMap<String, String> commandes) {
        this.ordre = ordre;
        this.commandes = commandes;
        executerCommandes();
    }

    public void executerCommandes() {
        try {
            for (String cible : ordre) {

                String commande = commandes.get(cible);
                if (commande != null) {
                    if (!executerCommande(commande)) {
                        System.err.println("Erreur lors de l'exécution de la commande.");
                    }
                }
            }
            Thread.sleep(1500);
            System.out.println("--------- Application compilé avec succees ! ---------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean executerCommande(String commande) {
        ProcessBuilder processBuilder = new ProcessBuilder(commande.split(" "));
        try {
            Process process = processBuilder.start();
            
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            return false; 
        }
    }

    
}
